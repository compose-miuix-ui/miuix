// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.copy
import androidx.compose.animation.core.tween
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.util.VelocityTracker1D
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.PointerInputModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastAny
import androidx.compose.ui.util.fastForEach
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.atan2
import kotlin.math.roundToInt
import kotlin.math.sign

/**
 * Platform mouse wheel behavior, mirroring `androidx.compose.foundation.gestures.ScrollConfig`.
 */
internal interface PagerScrollConfig {
    /** Enables animated transition of scroll on mouse wheel events. */
    val isSmoothScrollingEnabled: Boolean
        get() = true

    fun isPreciseWheelScroll(event: PointerEvent): Boolean = false

    fun Density.calculateMouseWheelScroll(event: PointerEvent, bounds: IntSize): Offset
}

internal expect fun CompositionLocalConsumerModifierNode.pagerScrollConfig(): PagerScrollConfig

/**
 * Keeps wheel, Shift+wheel and trackpad input of a [PagerInterceptionMode.CrossAxis] pager on the
 * framework's rails.
 *
 * The mode disables the pager's own scrolling (`userScrollEnabled = false`) so that
 * [Modifier.pagerGestureOverride] can arbitrate touch drags. Non-touch input therefore has to be
 * driven here with the same algorithms, constants and batching Compose Foundation 1.12.0 uses in
 * `ScrollableNode`, `MouseWheelScrollingLogic` and `TrackpadScrollingLogic`.
 */
internal fun Modifier.pagerNonTouchScroll(
    state: PagerState,
    flingBehavior: FlingBehavior,
): Modifier = then(PagerNonTouchScrollElement(state, flingBehavior))

private data class PagerNonTouchScrollElement(
    val state: PagerState,
    val flingBehavior: FlingBehavior,
) : ModifierNodeElement<PagerNonTouchScrollNode>() {
    override fun create(): PagerNonTouchScrollNode = PagerNonTouchScrollNode(state, flingBehavior)

    override fun update(node: PagerNonTouchScrollNode) {
        node.update(state, flingBehavior)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "pagerNonTouchScroll"
        properties["state"] = state
        properties["flingBehavior"] = flingBehavior
    }
}

/**
 * Independent axis access used while dispatching a scroll, mirroring `NestedScrollScope`.
 */
private interface PagerNestedScrollScope {
    fun scrollBy(offset: Offset, source: NestedScrollSource): Offset
}

private data class PagerMouseWheelScrollDelta(
    val value: Offset,
    val timeMillis: Long,
    val shouldApplyImmediately: Boolean,
) {
    operator fun plus(other: PagerMouseWheelScrollDelta) = PagerMouseWheelScrollDelta(
        value = value + other.value,
        // Pick time from last one
        timeMillis = maxOf(timeMillis, other.timeMillis),
        // Ignore [other.shouldApplyImmediately] to avoid false-positive
        // [PagerScrollConfig.isPreciseWheelScroll] detection during animation
        shouldApplyImmediately = shouldApplyImmediately,
    )
}

private class PagerTrackpadScrollDelta(
    val value: Offset,
    val timeMillis: Long,
    val isEnd: Boolean,
) {
    operator fun plus(other: PagerTrackpadScrollDelta) = PagerTrackpadScrollDelta(
        value = value + other.value,
        // Pick time from last one
        timeMillis = maxOf(timeMillis, other.timeMillis),
        // Combine together the flag to end the gesture
        isEnd = isEnd || other.isEnd,
    )
}

private class PagerNonTouchScrollNode(
    state: PagerState,
    flingBehavior: FlingBehavior,
) : DelegatingNode(),
    PointerInputModifierNode,
    CompositionLocalConsumerModifierNode {
    private val nestedScrollDispatcher = NestedScrollDispatcher()

    private val scrollLogic = PagerScrollingLogic(
        pagerState = state,
        flingBehavior = flingBehavior,
        nestedScrollDispatcher = nestedScrollDispatcher,
    )

    private var createdMouseWheelScrollingLogic = false
    private var createdTrackpadScrollingLogic = false

    private var mouseWheelScrollingLogic: PagerMouseWheelScrollingLogic? = null
    private var trackpadScrollingLogic: PagerTrackpadScrollingLogic? = null

    init {
        delegate(nestedScrollModifierNode(PagerNestedScrollConnection, nestedScrollDispatcher))
    }

    override fun onAttach() {
        updateScrollDirection()
        mouseWheelScrollingLogic?.updateDensity(requireDensity())
        trackpadScrollingLogic?.updateDensity(requireDensity())
    }

    override fun onDensityChange() {
        mouseWheelScrollingLogic?.updateDensity(requireDensity())
        trackpadScrollingLogic?.updateDensity(requireDensity())
    }

    override fun onPointerEvent(
        pointerEvent: PointerEvent,
        pass: PointerEventPass,
        bounds: IntSize,
    ) {
        updateScrollDirection()

        if (pass == PointerEventPass.Initial && pointerEvent.type == PointerEventType.Scroll) {
            initializeMouseWheelScrollingLogic()
        }
        mouseWheelScrollingLogic?.onPointerEvent(pointerEvent, pass, bounds)

        if (
            pass == PointerEventPass.Initial &&
            (
                pointerEvent.type == PointerEventType.PanStart ||
                    pointerEvent.type == PointerEventType.PanMove ||
                    pointerEvent.type == PointerEventType.PanEnd
                )
        ) {
            initializeTrackpadScrollingLogic()
        }
        trackpadScrollingLogic?.onPointerEvent(pointerEvent, pass, bounds)
    }

    override fun onCancelPointerInput() = Unit

    fun update(state: PagerState, flingBehavior: FlingBehavior) {
        scrollLogic.update(state, flingBehavior)
    }

    /**
     * Resolves the same `reverseDirection` a horizontal `ScrollableNode` would use, from the pager's
     * `reverseLayout` and the current layout direction.
     */
    private fun updateScrollDirection() {
        scrollLogic.reverseDirection = ScrollableDefaults.reverseDirection(
            layoutDirection = currentValueOf(LocalLayoutDirection),
            orientation = Orientation.Horizontal,
            reverseScrolling = scrollLogic.pagerState.layoutInfo.reverseLayout,
        )
    }

    private fun initializeMouseWheelScrollingLogic() {
        if (!createdMouseWheelScrollingLogic) {
            mouseWheelScrollingLogic = PagerMouseWheelScrollingLogic(
                scrollingLogic = scrollLogic,
                mouseWheelScrollConfig = pagerScrollConfig(),
                onScrollStopped = { velocity ->
                    nestedScrollDispatcher.coroutineScope.launch {
                        scrollLogic.flingWithVelocity(velocity)
                    }
                },
                density = requireDensity(),
            )
            createdMouseWheelScrollingLogic = true
        }

        mouseWheelScrollingLogic?.startReceivingEvents(coroutineScope)
    }

    private fun initializeTrackpadScrollingLogic() {
        if (!createdTrackpadScrollingLogic) {
            trackpadScrollingLogic = PagerTrackpadScrollingLogic(
                scrollingLogic = scrollLogic,
                onScrollStopped = { velocity ->
                    nestedScrollDispatcher.coroutineScope.launch {
                        scrollLogic.flingWithVelocity(velocity)
                    }
                },
                density = requireDensity(),
            )
            createdTrackpadScrollingLogic = true
        }

        trackpadScrollingLogic?.startReceivingEvents(coroutineScope)
    }
}

/**
 * Leaves the scroll and fling that the pager's pages leave over with those pages, matching the touch
 * behavior of [Modifier.pagerGestureOverride].
 *
 * This is the native behavior of a pager whose scrolling is turned off: `Pager` only passes
 * `userScrollEnabled` to the `ScrollableNode` inside `scrollableArea`, so its
 * `ScrollableNestedScrollConnection` runs with `enabled = false` and consumes nothing in either
 * `onPostScroll` or `onPostFling`. The pager's own connection then only drops the velocity on the
 * pager's axis, which [PagerGestureNestedScrollConnection] mirrors. Forwarding the leftover to this
 * pager here would hand a gesture back to the pager after touch arbitration had already selected a
 * child gesture.
 */
private object PagerNestedScrollConnection : NestedScrollConnection {
    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource,
    ): Offset = Offset.Zero

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity = Velocity.Zero
}

/**
 * Scrolls a single axis of [pagerState] with nested scroll dispatch and the pager's snapping fling,
 * mirroring the parts of `ScrollingLogic` that non-touch input relies on.
 */
private class PagerScrollingLogic(
    pagerState: PagerState,
    flingBehavior: FlingBehavior,
    private val nestedScrollDispatcher: NestedScrollDispatcher,
) {
    var pagerState: PagerState = pagerState
        private set

    var reverseDirection: Boolean = false

    private var flingBehavior: FlingBehavior = flingBehavior
    private var resolvedFlingBehavior: FlingBehavior = resolveFlingBehavior(flingBehavior, pagerState)

    val nestedScrollScope: PagerNestedScrollScope =
        object : PagerNestedScrollScope {
            override fun scrollBy(offset: Offset, source: NestedScrollSource): Offset = with(outerStateScope) { performScroll(offset, source) }
        }

    private var outerStateScope: ScrollScope = NoOpScrollScope

    fun update(pagerState: PagerState, flingBehavior: FlingBehavior) {
        val stateChanged = this.pagerState !== pagerState
        val behaviorChanged = this.flingBehavior !== flingBehavior
        if (stateChanged) {
            this.pagerState = pagerState
        }
        if (behaviorChanged) {
            this.flingBehavior = flingBehavior
        }
        if (stateChanged || behaviorChanged) {
            resolvedFlingBehavior = resolveFlingBehavior(this.flingBehavior, this.pagerState)
        }
    }

    fun Float.reverseIfNeeded(): Float = if (reverseDirection) this * -1 else this

    fun Offset.reverseIfNeeded(): Offset = if (reverseDirection) this * -1f else this

    fun Float.toOffset(): Offset = if (this == 0f) Offset.Zero else Offset(this, 0f)

    fun Float.toVelocity(): Velocity = if (this == 0f) Velocity.Zero else Velocity(this, 0f)

    fun Velocity.toFloat(): Float = x

    fun Velocity.singleAxisVelocity(): Velocity = copy(y = 0f)

    fun Velocity.update(value: Float): Velocity = copy(x = value)

    fun Offset.singleAxisOffset(): Offset = copy(y = 0f)

    fun Offset.toFloat(): Float = x

    /**
     * Converts this offset to a single axis delta based on the derived angle from the x and y
     * deltas, mirroring `ScrollingLogic.toSingleAxisDeltaFromAngle`.
     */
    fun Offset.toSingleAxisDeltaFromAngle(): Float {
        val angle = atan2(y.absoluteValue, x.absoluteValue)
        return if (angle >= VERTICAL_AXIS_THRESHOLD_ANGLE) 0f else x
    }

    /** Applies [delta] through nested scrolling to the pager state itself. */
    fun PagerNestedScrollScope.dispatchScroll(delta: Float, source: NestedScrollSource): Float {
        val offset = delta.reverseIfNeeded().toOffset()
        val consumed = scrollBy(offset, source)
        return consumed.reverseIfNeeded().toFloat()
    }

    private fun ScrollScope.performScroll(delta: Offset, source: NestedScrollSource): Offset {
        val consumedByPreScroll = nestedScrollDispatcher.dispatchPreScroll(delta, source)

        val scrollAvailableAfterPreScroll = delta - consumedByPreScroll

        val singleAxisDeltaForSelfScroll =
            scrollAvailableAfterPreScroll.singleAxisOffset().reverseIfNeeded().toFloat()

        // Consume on a single axis.
        val consumedBySelfScroll =
            scrollBy(singleAxisDeltaForSelfScroll).toOffset().reverseIfNeeded()

        val deltaAvailableAfterScroll = scrollAvailableAfterPreScroll - consumedBySelfScroll
        val consumedByPostScroll =
            nestedScrollDispatcher.dispatchPostScroll(consumedBySelfScroll, deltaAvailableAfterScroll, source)
        return consumedByPreScroll + consumedBySelfScroll + consumedByPostScroll
    }

    suspend fun scroll(
        scrollPriority: MutatePriority,
        block: suspend PagerNestedScrollScope.() -> Unit,
    ) {
        pagerState.scroll(scrollPriority) {
            outerStateScope = this
            nestedScrollScope.block()
        }
    }

    /**
     * Ends a non-touch scroll by flinging with [initialVelocity]. A pager always flings here: its
     * fling behavior is a snapping one, which `ScrollableNode` also triggers for mouse wheel input.
     *
     * Named apart from the [PagerNonTouchScrollingLogic.onScrollStopped] callback on purpose: an
     * extension function on [PagerScrollingLogic] resolves same-named members of the extension
     * receiver first, which would call this directly and run the fling inside the event loop
     * instead of through the callback.
     */
    suspend fun flingWithVelocity(initialVelocity: Velocity) {
        val availableVelocity = initialVelocity.singleAxisVelocity()

        val performFling: suspend (Velocity) -> Velocity = { velocity ->
            val preConsumedByParent = nestedScrollDispatcher.dispatchPreFling(velocity)
            val available = velocity - preConsumedByParent

            val velocityLeft = doFlingAnimation(available)

            val consumedPost =
                nestedScrollDispatcher.dispatchPostFling(available - velocityLeft, velocityLeft)
            val totalLeft = velocityLeft - consumedPost
            velocity - totalLeft
        }

        performFling(availableVelocity)
    }

    private suspend fun doFlingAnimation(available: Velocity): Velocity {
        var result: Velocity = available
        pagerState.scroll(MutatePriority.Default) {
            val reverseScope =
                object : ScrollScope {
                    override fun scrollBy(pixels: Float): Float = with(nestedScrollScope) {
                        dispatchScroll(pixels, NestedScrollSource.SideEffect)
                    }
                }
            with(reverseScope) {
                with(resolvedFlingBehavior) {
                    result =
                        result.update(
                            performFling(available.toFloat().reverseIfNeeded()).reverseIfNeeded(),
                        )
                }
            }
        }
        return result
    }
}

private val NoOpScrollScope: ScrollScope =
    object : ScrollScope {
        override fun scrollBy(pixels: Float): Float = pixels
    }

/**
 * Wraps the pager's fling behavior with the target page bookkeeping the pager itself applies to
 * flings, mirroring `PagerWrapperFlingBehavior`.
 */
private class PagerSnapFlingBehavior(
    private val originalFlingBehavior: TargetedFlingBehavior,
    private val pagerState: PagerState,
) : FlingBehavior {
    override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
        val scope: ScrollScope = this
        val resultVelocity =
            with(originalFlingBehavior) {
                performFling(initialVelocity) { remainingScrollOffset ->
                    val pageSizeWithSpacing = pagerState.layoutInfo.pageSize + pagerState.layoutInfo.pageSpacing
                    val flingPageDisplacement =
                        if (pageSizeWithSpacing != 0) {
                            remainingScrollOffset / pageSizeWithSpacing
                        } else {
                            0f
                        }
                    val targetPage = flingPageDisplacement.roundToInt() + pagerState.currentPage
                    with(pagerState) { scope.updateTargetPage(targetPage) }
                }
            }

        // fling finished, correct snapping for rounding
        if (
            pagerState.currentPageOffsetFraction != 0.0f &&
            abs(pagerState.currentPageOffsetFraction) < 1e-3
        ) {
            pagerState.requestScrollToPage(pagerState.currentPage)
        }

        return resultVelocity
    }
}

private fun resolveFlingBehavior(flingBehavior: FlingBehavior, pagerState: PagerState): FlingBehavior = if (flingBehavior is TargetedFlingBehavior) {
    PagerSnapFlingBehavior(flingBehavior, pagerState)
} else {
    flingBehavior
}

/** Velocity tracking shared by the mouse wheel and trackpad logic. */
private class PagerDifferentialVelocityTracker {
    private val xVelocityTracker = VelocityTracker1D(isDataDifferential = true)
    private val yVelocityTracker = VelocityTracker1D(isDataDifferential = true)

    fun addDelta(timeMillis: Long, delta: Offset) {
        xVelocityTracker.addDataPoint(timeMillis, delta.x)
        yVelocityTracker.addDataPoint(timeMillis, delta.y)
    }

    fun calculateVelocity(): Velocity {
        val velocityX = xVelocityTracker.calculateVelocity(Float.MAX_VALUE)
        val velocityY = yVelocityTracker.calculateVelocity(Float.MAX_VALUE)
        return Velocity(velocityX, velocityY)
    }
}

/** Shared base of the mouse wheel and trackpad logics, mirroring `NonTouchScrollingLogic`. */
private abstract class PagerNonTouchScrollingLogic(
    protected val scrollingLogic: PagerScrollingLogic,
    protected val onScrollStopped: suspend (velocity: Velocity) -> Unit,
    protected var density: Density,
) {
    fun updateDensity(density: Density) {
        this.density = density
    }

    internal var isScrolling = false

    internal suspend fun userScroll(block: suspend PagerNestedScrollScope.() -> Unit) {
        isScrolling = true
        // Run it in supervisorScope to ignore cancellations from scrolls with higher MutatePriority
        supervisorScope { scrollingLogic.scroll(MutatePriority.UserInput, block) }
        isScrolling = false
    }

    val velocityTracker = PagerDifferentialVelocityTracker()

    /** Forwards the given [pointerEvent] for processing by this scroll logic. */
    abstract fun onPointerEvent(pointerEvent: PointerEvent, pass: PointerEventPass, bounds: IntSize)

    /** Begins processing of events sent to [onPointerEvent] using the given [coroutineScope]. */
    abstract fun startReceivingEvents(coroutineScope: CoroutineScope)
}

/**
 * Mouse wheel support, mirroring `MouseWheelScrollingLogic`.
 *
 * Wheel deltas are batched while they keep arriving, applied with the framework's smooth scroll
 * animation, and followed by the pager's snapping fling once the wheel stops.
 */
private class PagerMouseWheelScrollingLogic(
    scrollingLogic: PagerScrollingLogic,
    private val mouseWheelScrollConfig: PagerScrollConfig,
    onScrollStopped: suspend (velocity: Velocity) -> Unit,
    density: Density,
) : PagerNonTouchScrollingLogic(scrollingLogic, onScrollStopped, density) {
    override fun onPointerEvent(
        pointerEvent: PointerEvent,
        pass: PointerEventPass,
        bounds: IntSize,
    ) {
        if (pointerEvent.type != PointerEventType.Scroll) return
        if (pointerEvent.isConsumed) return

        // If this scrollable is already scrolling from a previous interaction, consume immediately
        // to give it priority.
        if (pass == PointerEventPass.Initial && isScrolling) {
            onMouseWheel(pointerEvent, bounds)
            pointerEvent.consume()
        }

        // During the main pass. If this scrollable is not scrolling, decide if it should based on
        // the consumption. If the scrollable is scrolling we don't need to worry because it
        // consumed during the initial pass.
        if (pass == PointerEventPass.Main && !isScrolling) {
            val consumed = onMouseWheel(pointerEvent, bounds)
            if (consumed) {
                pointerEvent.consume()
            }
        }
    }

    private val channel = Channel<PagerMouseWheelScrollDelta>(capacity = Channel.UNLIMITED)

    private var receivingMouseWheelEventsJob: Job? = null

    override fun startReceivingEvents(coroutineScope: CoroutineScope) {
        if (receivingMouseWheelEventsJob == null) {
            receivingMouseWheelEventsJob =
                coroutineScope.launch {
                    try {
                        while (coroutineContext.isActive) {
                            val scrollDelta = channel.receive()
                            val threshold = with(density) { AnimationThreshold.toPx() }
                            val speed = with(density) { AnimationSpeed.toPx() }
                            scrollingLogic.dispatchMouseWheelScroll(scrollDelta, threshold, speed)
                        }
                    } finally {
                        receivingMouseWheelEventsJob = null
                    }
                }
        }
    }

    private fun onMouseWheel(pointerEvent: PointerEvent, bounds: IntSize): Boolean {
        val scrollDelta =
            with(mouseWheelScrollConfig) {
                with(density) { calculateMouseWheelScroll(pointerEvent, bounds) }
            }
        return if (scrollingLogic.canConsumeDelta(scrollDelta)) {
            channel
                .trySend(
                    PagerMouseWheelScrollDelta(
                        value = scrollDelta,
                        timeMillis = pointerEvent.changes.first().uptimeMillis,
                        shouldApplyImmediately = !mouseWheelScrollConfig.isSmoothScrollingEnabled ||
                            // In case of high-resolution wheel, such as a freely rotating wheel
                            // with no notches or trackpads, delta should apply immediately,
                            // without any delays.
                            mouseWheelScrollConfig.isPreciseWheelScroll(pointerEvent),
                    ),
                )
                .isSuccess
        } else {
            isScrolling
        }
    }

    private fun Channel<PagerMouseWheelScrollDelta>.sumOrNull(): PagerMouseWheelScrollDelta? {
        var sum: PagerMouseWheelScrollDelta? = null
        for (i in untilNull { tryReceive().getOrNull() }) {
            sum = if (sum == null) i else sum + i
        }
        return sum
    }

    private fun PagerScrollingLogic.canConsumeDelta(scrollDelta: Offset): Boolean {
        // Mouse wheel scroll deltas may come as 2 dimensional values. We use the angle to decide
        // which axis in the delta is more important and should be triggered.
        val delta = scrollDelta.reverseIfNeeded().toSingleAxisDeltaFromAngle()
        return if (delta == 0f) {
            false // It means that it's for another axis and cannot be consumed
        } else if (delta > 0f) {
            pagerState.canScrollForward
        } else {
            pagerState.canScrollBackward
        }
    }

    private fun trackVelocity(scrollDelta: PagerMouseWheelScrollDelta) {
        velocityTracker.addDelta(scrollDelta.timeMillis, scrollDelta.value)
    }

    private suspend fun PagerScrollingLogic.dispatchMouseWheelScroll(
        scrollDelta: PagerMouseWheelScrollDelta,
        threshold: Float, // px
        speed: Float, // px / ms
    ) {
        var targetScrollDelta = scrollDelta
        trackVelocity(scrollDelta)
        // Sum delta from all pending events to avoid multiple animation restarts.
        val summed = channel.sumOrNull()
        if (summed != null) {
            trackVelocity(summed)
            targetScrollDelta += summed
        }
        var targetValue = targetScrollDelta.value.reverseIfNeeded().toFloat()
        if (targetValue.isLowScrollingDelta()) {
            return
        }
        var animationState = AnimationState(0f)

        suspend fun waitNextScrollDelta(timeoutMillis: Long): Boolean {
            if (timeoutMillis < 0) return false
            return withTimeoutOrNull(timeoutMillis) { channel.busyReceive() }
                ?.let {
                    // Keep this value unchanged during animation.
                    // Currently, isPreciseWheelScroll might be unstable in case if a precise value
                    // is almost equal to a regular one.
                    val previousDeltaShouldApplyImmediately = targetScrollDelta.shouldApplyImmediately
                    targetScrollDelta =
                        it.copy(shouldApplyImmediately = previousDeltaShouldApplyImmediately)
                    targetValue = targetScrollDelta.value.reverseIfNeeded().toSingleAxisDeltaFromAngle()
                    animationState = AnimationState(0f) // Reset previous animation leftover
                    trackVelocity(it)

                    !targetValue.isLowScrollingDelta()
                } ?: false
        }

        userScroll {
            var requiredAnimation = true
            while (requiredAnimation) {
                requiredAnimation = false
                val targetValueLeftover = targetValue - animationState.value
                if (
                    targetScrollDelta.shouldApplyImmediately ||
                    abs(targetValueLeftover) < threshold
                ) {
                    dispatchMouseWheelScroll(targetValueLeftover)
                    requiredAnimation = waitNextScrollDelta(SCROLL_PROGRESS_TIMEOUT)
                } else {
                    // Animation will start only on the next frame,
                    // so apply threshold immediately to avoid delays.
                    val instantDelta = sign(targetValueLeftover) * threshold
                    dispatchMouseWheelScroll(instantDelta)
                    animationState =
                        animationState.copy(value = animationState.value + instantDelta)

                    val durationMillis =
                        (abs(targetValue - animationState.value) / speed)
                            .roundToInt()
                            .coerceAtMost(MAX_ANIMATION_DURATION)
                    animateMouseWheelScroll(animationState, targetValue, durationMillis) { lastValue ->
                        // Sum delta from all pending events to avoid multiple animation restarts.
                        val nextScrollDelta = channel.sumOrNull()
                        if (nextScrollDelta != null) {
                            trackVelocity(nextScrollDelta)
                            targetScrollDelta += nextScrollDelta
                            targetValue =
                                targetScrollDelta.value
                                    .reverseIfNeeded()
                                    .toSingleAxisDeltaFromAngle()

                            requiredAnimation = !(targetValue - lastValue).isLowScrollingDelta()
                        }
                        nextScrollDelta != null
                    }
                    if (!requiredAnimation) {
                        // If it's completed, wait the next event with timeout before resetting
                        // progress flag
                        requiredAnimation =
                            waitNextScrollDelta(SCROLL_PROGRESS_TIMEOUT - durationMillis)
                    }
                }
            }
        }

        var velocity = velocityTracker.calculateVelocity()
        if (velocity == Velocity.Zero) {
            // In case of single data point use animation speed and delta direction
            val velocityPxInMs = minOf(abs(targetValue) / MAX_ANIMATION_DURATION, speed)
            velocity = (sign(targetValue).reverseIfNeeded() * velocityPxInMs * 1000).toVelocity()
        }
        onScrollStopped(velocity)
    }

    private suspend fun PagerNestedScrollScope.animateMouseWheelScroll(
        animationState: AnimationState<Float, AnimationVector1D>,
        targetValue: Float,
        durationMillis: Int,
        shouldCancelAnimation: (lastValue: Float) -> Boolean,
    ) {
        var lastValue = animationState.value
        animationState.animateTo(
            targetValue,
            animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing),
            sequentialAnimation = true,
        ) {
            val delta = value - lastValue
            if (!delta.isLowScrollingDelta()) {
                val consumedDelta = dispatchMouseWheelScroll(delta)
                if (!(delta - consumedDelta).isLowScrollingDelta()) {
                    cancelAnimation()
                    return@animateTo
                }
                lastValue += delta
            }
            if (shouldCancelAnimation(lastValue)) {
                cancelAnimation()
            }
        }
    }

    private fun PagerNestedScrollScope.dispatchMouseWheelScroll(delta: Float): Float = with(scrollingLogic) { dispatchScroll(delta, NestedScrollSource.UserInput) }
}

/**
 * Trackpad support, mirroring `TrackpadScrollingLogic`.
 *
 * Pan deltas are applied while the gesture is alive and flung with its tracked velocity once it
 * ends.
 */
private class PagerTrackpadScrollingLogic(
    scrollingLogic: PagerScrollingLogic,
    onScrollStopped: suspend (velocity: Velocity) -> Unit,
    density: Density,
) : PagerNonTouchScrollingLogic(scrollingLogic, onScrollStopped, density) {
    override fun onPointerEvent(
        pointerEvent: PointerEvent,
        pass: PointerEventPass,
        bounds: IntSize,
    ) {
        if (
            pointerEvent.type != PointerEventType.PanStart &&
            pointerEvent.type != PointerEventType.PanMove &&
            pointerEvent.type != PointerEventType.PanEnd
        ) {
            return
        }
        if (pointerEvent.isConsumed) return

        // If this scrollable is already scrolling from a previous interaction, consume immediately
        // to give it priority.
        if (pass == PointerEventPass.Initial && isScrolling) {
            onPan(pointerEvent)
            pointerEvent.consume()
        }

        // During the main pass. If this scrollable is not scrolling, decide if it should based on
        // the consumption. If the scrollable is scrolling we don't need to worry because it
        // consumed during the initial pass.
        if (pass == PointerEventPass.Main && !isScrolling) {
            val consumed = onPan(pointerEvent)
            if (consumed) {
                pointerEvent.consume()
            }
        }
    }

    private val channel = Channel<PagerTrackpadScrollDelta>(capacity = Channel.UNLIMITED)

    private var receivingPanEventsJob: Job? = null

    override fun startReceivingEvents(coroutineScope: CoroutineScope) {
        if (receivingPanEventsJob == null) {
            receivingPanEventsJob =
                coroutineScope.launch {
                    try {
                        while (coroutineContext.isActive) {
                            scrollingLogic.dispatchTrackpadScroll(channel.receive())
                        }
                    } finally {
                        receivingPanEventsJob = null
                    }
                }
        }
    }

    private fun onPan(pointerEvent: PointerEvent): Boolean {
        var sent = false

        pointerEvent.changes.firstOrNull()?.let { change ->
            change.historical.fastForEach { historicalChange ->
                val delta = -historicalChange.panOffset
                if (scrollingLogic.canConsumeDelta(delta)) {
                    sent =
                        channel
                            .trySend(
                                PagerTrackpadScrollDelta(
                                    value = delta,
                                    timeMillis = historicalChange.uptimeMillis,
                                    isEnd = false,
                                ),
                            )
                            .isSuccess || sent
                }
            }
            val delta = -change.panOffset
            val isPanEnd = pointerEvent.type == PointerEventType.PanEnd
            if (scrollingLogic.canConsumeDelta(delta) || isPanEnd) {
                sent =
                    channel
                        .trySend(
                            PagerTrackpadScrollDelta(
                                value = delta,
                                timeMillis = change.uptimeMillis,
                                isEnd = isPanEnd,
                            ),
                        )
                        .isSuccess || sent
            }
        }

        return sent || isScrolling
    }

    private fun Channel<PagerTrackpadScrollDelta>.sumOrNull(): PagerTrackpadScrollDelta? {
        var sum: PagerTrackpadScrollDelta? = null
        for (i in untilNull { tryReceive().getOrNull() }) {
            sum = if (sum == null) i else sum + i
        }
        return sum
    }

    private fun PagerScrollingLogic.canConsumeDelta(scrollDelta: Offset): Boolean = scrollDelta.reverseIfNeeded().toSingleAxisDeltaFromAngle() != 0f

    private fun trackVelocity(scrollDelta: PagerTrackpadScrollDelta) {
        velocityTracker.addDelta(scrollDelta.timeMillis, scrollDelta.value)
    }

    private suspend fun PagerScrollingLogic.dispatchTrackpadScroll(scrollDelta: PagerTrackpadScrollDelta) {
        var targetScrollDelta = scrollDelta
        trackVelocity(scrollDelta)
        // Sum delta from all pending events to drain the channel.
        channel.sumOrNull()?.let {
            trackVelocity(it)
            targetScrollDelta += it
        }

        userScroll {
            dispatchTrackpadScroll(
                targetScrollDelta.value.reverseIfNeeded().toSingleAxisDeltaFromAngle(),
            )
            while (!targetScrollDelta.isEnd) {
                targetScrollDelta = channel.busyReceive()
                trackVelocity(targetScrollDelta)
                channel.sumOrNull()?.let {
                    trackVelocity(it)
                    targetScrollDelta += it
                }
                dispatchTrackpadScroll(
                    targetScrollDelta.value.reverseIfNeeded().toSingleAxisDeltaFromAngle(),
                )
            }
        }

        onScrollStopped(velocityTracker.calculateVelocity())
    }

    private fun PagerNestedScrollScope.dispatchTrackpadScroll(delta: Float) {
        with(scrollingLogic) { dispatchScroll(delta, NestedScrollSource.UserInput) }
    }
}

private val PointerEvent.isConsumed: Boolean
    get() = changes.fastAny { it.isConsumed }

private fun PointerEvent.consume() = changes.fastForEach { it.consume() }

/**
 * Replacement of regular [Channel.receive] that schedules an invalidation each frame. It avoids
 * entering an idle state while waiting for [SCROLL_PROGRESS_TIMEOUT]. It's important for tests that
 * attempt to trigger another scroll after a mouse wheel event.
 */
private suspend fun <T> Channel<T>.busyReceive(): T = coroutineScope {
    val job = launch {
        while (coroutineContext.isActive) {
            withFrameNanos {}
        }
    }
    try {
        receive()
    } finally {
        job.cancel()
    }
}

private fun <E> untilNull(builderAction: () -> E?) = sequence<E> {
    do {
        val element = builderAction()?.also { yield(it) }
    } while (element != null)
}

private fun Float.isLowScrollingDelta(): Boolean = isNaN() || abs(this) < 0.5f

private val AnimationThreshold = 6.dp // (AnimationSpeed * MAX_ANIMATION_DURATION) / (1000ms / 60Hz)
private val AnimationSpeed = 1.dp // dp / ms
private const val MAX_ANIMATION_DURATION = 100 // ms
private const val SCROLL_PROGRESS_TIMEOUT = 50L // ms
private const val VERTICAL_AXIS_THRESHOLD_ANGLE = PI / 4
