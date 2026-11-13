// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.DragEvent
import androidx.compose.foundation.gestures.DragGestureNode
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.changedToDownIgnoreConsumed
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.input.pointer.util.addPointerInputChange
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.PointerInputModifierNode
import androidx.compose.ui.node.TraversableNode.Companion.TraverseDescendantsAction
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.node.requireLayoutCoordinates
import androidx.compose.ui.node.traverseDescendants
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastAll
import androidx.compose.ui.util.fastAny
import androidx.compose.ui.util.fastFirstOrNull
import androidx.compose.ui.util.fastForEach
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * Horizontal pager behavior when a child scrollable is flinging or overscrolling.
 */
enum class PagerInterceptionMode(val title: String) {
    /** Uses Compose's native gestures. */
    Native("Default"),

    /**
     * Lets horizontal swipes interrupt page transitions and child scrolling.
     */
    CrossAxis("Cross-Axis"),
}

/**
 * Spring spec shared by pager tab navigation and snap fling.
 */
val PagerNavigationSpringSpec: SpringSpec<Float> = spring(
    stiffness = 322.2f,
    dampingRatio = 32.31f / (2f * kotlin.math.sqrt(322.2f)),
    visibilityThreshold = 0.5f,
)

/**
 * Animates to [target] using [PagerNavigationSpringSpec] after the first layout.
 * Uses [MutatePriority.UserInput] so focus scrolling cannot interrupt the animation.
 */
suspend fun PagerState.springAnimateToPage(target: Int) {
    if (target !in 0 until pageCount) return
    scroll(MutatePriority.UserInput) {
        animateToPage(this, target)
    }
}

private suspend fun PagerState.animateToPage(
    scrollScope: ScrollScope,
    target: Int,
    initialVelocity: Float = 0f,
) = with(scrollScope) {
    if (pageCount == 0) return@with
    val destination = target.coerceIn(0, pageCount - 1)
    val pageSize = layoutInfo.pageSize + layoutInfo.pageSpacing
    updateTargetPage(destination)
    if (pageSize > 0) {
        val distance = (destination - currentPage - currentPageOffsetFraction) * pageSize
        var previousValue = 0f
        animate(
            initialValue = 0f,
            targetValue = distance,
            initialVelocity = initialVelocity,
            animationSpec = PagerNavigationSpringSpec,
        ) { currentValue, _ ->
            previousValue += scrollBy(currentValue - previousValue)
        }
    }
    // Complete within this mutation so cancellation cannot snap a newer gesture to an old target.
    if (pageCount > 0) updateCurrentPage(destination.coerceAtMost(pageCount - 1))
}

/**
 * Leaves vertical scroll and velocity to page content, including between pages.
 *
 * Use as [HorizontalPager]'s pageNestedScrollConnection with [Modifier.pagerGestureOverride].
 */
object PagerGestureNestedScrollConnection : NestedScrollConnection {
    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity = Velocity(available.x, 0f)
}

/**
 * Applies [mode] to a [HorizontalPager].
 *
 * A Cross-Axis pager replaces the pager's own touch scrolling with the recognizer this modifier
 * installs, so it has to be configured like this:
 *
 * ```kotlin
 * val flingBehavior = PagerDefaults.flingBehavior(pagerState, snapAnimationSpec = PagerNavigationSpringSpec)
 * HorizontalPager(
 *     state = pagerState,
 *     modifier = Modifier.pagerGestureOverride(pagerState, flingBehavior),
 *     userScrollEnabled = false,
 *     flingBehavior = flingBehavior,
 *     pageNestedScrollConnection = PagerGestureNestedScrollConnection,
 * ) { page ->
 *     LazyColumn(Modifier.fillMaxSize()) { ... }
 * }
 * ```
 *
 * With `userScrollEnabled = false` the pager keeps no touch recognizer of its own. This modifier
 * then restores the input that is not touch: wheel, Shift+wheel and trackpad events, driven with
 * the pager's own [flingBehavior] so the snapping matches a native pager.
 *
 * @param pagerState the pager being configured.
 * @param flingBehavior the same [FlingBehavior] passed to [HorizontalPager].
 * @param mode the interception mode; [PagerInterceptionMode.CrossAxis] is the default.
 * @param enabled when false the modifier is a no-op.
 * @param onTriggered called when the pager takes over an input.
 */
fun Modifier.pagerGestureOverride(
    pagerState: PagerState,
    flingBehavior: FlingBehavior,
    mode: PagerInterceptionMode = PagerInterceptionMode.CrossAxis,
    enabled: Boolean = true,
    onTriggered: (() -> Unit)? = null,
): Modifier = when (mode) {
    PagerInterceptionMode.CrossAxis ->
        if (enabled) {
            pagerNonTouchScroll(pagerState, flingBehavior).then(PagerPageGestureElement(pagerState, onTriggered))
        } else {
            this
        }

    PagerInterceptionMode.Native -> this
}

/**
 * Accepts a stored mode ordinal; invalid values select [PagerInterceptionMode.Native].
 */
fun Modifier.pagerGestureOverride(
    pagerState: PagerState,
    flingBehavior: FlingBehavior,
    mode: Int,
    enabled: Boolean = true,
    onTriggered: (() -> Unit)? = null,
): Modifier = pagerGestureOverride(
    pagerState = pagerState,
    flingBehavior = flingBehavior,
    mode = PagerInterceptionMode.entries.getOrElse(mode) { PagerInterceptionMode.Native },
    enabled = enabled,
    onTriggered = onTriggered,
)

private data class PagerPageGestureElement(
    val pagerState: PagerState,
    val onIntercepted: (() -> Unit)?,
) : ModifierNodeElement<PagerPageGestureNode>() {
    override fun create(): PagerPageGestureNode = PagerPageGestureNode(pagerState, onIntercepted)

    override fun update(node: PagerPageGestureNode) {
        node.update(pagerState, onIntercepted)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "pagerGestureOverride"
        properties["pagerState"] = pagerState
    }
}

/** Arbitrates page drags and horizontal takeover while a child is moving. */
private class PagerPageGestureNode(
    private var pagerState: PagerState,
    private var onIntercepted: (() -> Unit)?,
) : DelegatingNode(),
    CompositionLocalConsumerModifierNode,
    PointerInputModifierNode {

    private val motion = PagerPageMotion(pagerState, onIntercepted)
    private val childMotion = PagerChildMotionConnection()

    init {
        motion.childMotion = childMotion
        // Delegated rather than stored: the connection must join Compose's nested scroll tree.
        delegate(nestedScrollModifierNode(childMotion, null))
    }

    // CompositionLocal reads and layout direction are only authoritative while attached.
    override fun onAttach() {
        motion.nodeScope = coroutineScope
        refreshEnvironment()
    }

    override fun onDetach() {
        motion.detach()
        takeoverNode.reset()
        childMotion.stop()
    }

    override fun onLayoutDirectionChange() = refreshEnvironment()

    override fun onDensityChange() = refreshEnvironment()

    private fun refreshEnvironment() {
        if (!isAttached) return
        motion.minimumVelocity = with(requireDensity()) { 400.dp.toPx() }
        motion.touchSlop = currentValueOf(LocalViewConfiguration).touchSlop
        motion.directionSign = if (
            (currentValueOf(LocalLayoutDirection) == LayoutDirection.Rtl) xor
            pagerState.layoutInfo.reverseLayout
        ) {
            1f
        } else {
            -1f
        }
    }

    private fun childMotionAt(position: Offset): PagerChildMotion {
        if (childMotion.direction == Orientation.Horizontal) return PagerChildMotion.Horizontal
        val coordinates = requireLayoutCoordinates()
        var vertical = childMotion.direction == Orientation.Vertical
        var horizontal = false
        traverseDescendants(OverscrollMotionNode.Key) { descendant ->
            if (descendant is OverscrollMotionNode &&
                coordinates.localBoundingBoxOf(descendant.requireLayoutCoordinates()).contains(position)
            ) {
                vertical = vertical || descendant.isVerticallyRebounding
                horizontal = horizontal || descendant.isHorizontallyRebounding
            }
            if (horizontal) TraverseDescendantsAction.CancelTraversal else TraverseDescendantsAction.ContinueTraversal
        }
        return when {
            horizontal -> PagerChildMotion.Horizontal
            vertical -> PagerChildMotion.Vertical
            else -> PagerChildMotion.Idle
        }
    }

    private var dragNode = delegate(PagerPageDragNode(motion))
    private val takeoverNode = delegate(PagerPageTakeoverNode(motion, childMotion, ::childMotionAt))

    override fun onPointerEvent(pointerEvent: PointerEvent, pass: PointerEventPass, bounds: IntSize) {
        if (pointerEvent.changes.fastAll { it.type == PointerType.Mouse }) {
            return
        }
        if (pass == PointerEventPass.Initial && pointerEvent.changes.fastAny { it.changedToDownIgnoreConsumed() }) {
            // reverseLayout is only authoritative after measurement.
            refreshEnvironment()
        }
        // Takeover owns a whole pointer cycle; Foundation only sees the cycles it does not own.
        val takeoverWasTrackingPointer = takeoverNode.isTrackingPointer
        takeoverNode.prepareDown(pointerEvent, pass)
        takeoverNode.onPointerEvent(pointerEvent, pass, bounds)
        if (takeoverWasTrackingPointer != takeoverNode.isTrackingPointer) {
            dragNode.onCancelPointerInput()
        }
        if (!takeoverWasTrackingPointer && !takeoverNode.isTrackingPointer) {
            dragNode.onPointerEvent(pointerEvent, pass, bounds)
        }
    }

    override fun onCancelPointerInput() {
        takeoverNode.onCancelPointerInput()
        dragNode.onCancelPointerInput()
    }

    fun update(state: PagerState, onIntercepted: (() -> Unit)?) {
        if (pagerState !== state) {
            // A queued stop from the old Foundation recognizer must never settle the new state.
            motion.cancel()
            takeoverNode.reset()
            undelegate(dragNode)
            pagerState = state
            motion.update(state, onIntercepted)
            dragNode = delegate(PagerPageDragNode(motion))
            childMotion.stop()
        } else {
            motion.update(state, onIntercepted)
        }
        refreshEnvironment()
    }
}

private enum class PagerChildMotion { Idle, Vertical, Horizontal }

/**
 * Takes a touch away from a child that is flinging or overscrolling.
 *
 * The child latched its own drag on the first down and keeps consuming it on
 * [PointerEventPass.Main], where children run first, so only [PointerEventPass.Initial] can still
 * take the gesture. Only motion the child already owns is taken over; a fresh gesture, including
 * one on an incoming page mid-settle, is arbitrated on the Main pass.
 */
private class PagerPageTakeoverNode(
    private val motion: PagerPageMotion,
    private val childMotion: PagerChildMotionConnection,
    private val childMotionAt: (Offset) -> PagerChildMotion,
) : Modifier.Node(),
    PointerInputModifierNode {
    private var pointerId: PointerId? = null
    private var accumulated = Offset.Zero
    private val velocityTracker = VelocityTracker()
    private var takeOverOnDown = false
    private var ignoreUntilAllUp = false
    private var isHandlingPointer = false

    val isTrackingPointer: Boolean
        get() = pointerId != null || ignoreUntilAllUp

    fun prepareDown(pointerEvent: PointerEvent, pass: PointerEventPass) {
        if (pass != PointerEventPass.Initial || isTrackingPointer) return
        val down = pointerEvent.changes.fastFirstOrNull {
            it.changedToDownIgnoreConsumed() && !it.isConsumed && it.type != PointerType.Mouse
        } ?: return
        // Only owned motion is taken over; a fresh gesture stays with the child's Main-pass arbitration.
        takeOverOnDown = childMotionAt(down.position) == PagerChildMotion.Vertical
    }

    override fun onPointerEvent(pointerEvent: PointerEvent, pass: PointerEventPass, bounds: IntSize) {
        if (pass != PointerEventPass.Initial) return
        if (ignoreUntilAllUp) {
            ignoreUntilAllUp = pointerEvent.changes.fastAny { it.pressed }
            return
        }
        // On Initial, ancestors run first: a consumed change is theirs, even mid-takeover.
        if (pointerEvent.changes.fastAny { it.isConsumed }) {
            onCancelPointerInput()
            ignoreUntilAllUp = pointerEvent.changes.fastAny { it.pressed }
            return
        }
        val tracked = pointerId
        if (tracked == null) {
            val down = pointerEvent.changes.fastFirstOrNull {
                it.changedToDownIgnoreConsumed() && it.type != PointerType.Mouse
            } ?: return
            if (!takeOverOnDown) return
            takeOverOnDown = false
            pointerId = down.id
            accumulated = Offset.Zero
            velocityTracker.resetTracking()
            velocityTracker.addPointerInputChange(down)
            if (childMotion.isInMotion) motion.stopChildMotion()
            return
        }
        val change = pointerEvent.changes.fastFirstOrNull { it.id == tracked } ?: return
        velocityTracker.addPointerInputChange(change)
        if (!change.pressed) {
            val replacement = pointerEvent.changes.fastFirstOrNull { it.pressed && it.type != PointerType.Mouse }
            if (replacement != null) {
                pointerId = replacement.id
                // Fingers have unrelated coordinates; neither slop nor velocity may include
                // the jump between them. Preserve accumulated movement, but seed a new tracker.
                velocityTracker.resetTracking()
                velocityTracker.addPointerInputChange(replacement)
                if (isHandlingPointer) pointerEvent.changes.fastForEach { it.consume() }
                return
            }
            if (isHandlingPointer) {
                pointerEvent.changes.fastForEach { it.consume() }
                motion.finishTakeoverDrag(Velocity(velocityTracker.calculateVelocity().x, 0f))
            }
            reset()
            return
        }
        val delta = change.position - change.previousPosition
        if (!isHandlingPointer) {
            accumulated += delta
            val x = abs(accumulated.x)
            val y = abs(accumulated.y)
            if (x > motion.touchSlop && x > y) {
                isHandlingPointer = true
                motion.onDragStarted()
                motion.stopChildMotion()
                motion.startTakeoverDrag()
                pointerEvent.changes.fastForEach { it.consume() }
                val overSlop = accumulated.x - if (accumulated.x >= 0f) motion.touchSlop else -motion.touchSlop
                motion.dragBy(overSlop)
            } else if (y > motion.touchSlop && y > x) {
                reset()
                ignoreUntilAllUp = true
                motion.stopChildMotion()
            }
            return
        }
        pointerEvent.changes.fastForEach { it.consume() }
        motion.dragBy(delta.x)
    }

    fun reset() {
        pointerId = null
        isHandlingPointer = false
        accumulated = Offset.Zero
        takeOverOnDown = false
        ignoreUntilAllUp = false
        velocityTracker.resetTracking()
    }

    override fun onCancelPointerInput() {
        if (isHandlingPointer) motion.finishTakeoverDrag(Velocity.Zero)
        reset()
    }
}

private class PagerPageDragNode(
    private val motion: PagerPageMotion,
) : DragGestureNode(
    canDrag = { it != PointerType.Mouse },
    enabled = true,
    interactionSource = null,
    orientation = Orientation.Horizontal,
) {
    private var gesture = -1L

    override fun startDragImmediately(): Boolean = false

    override fun onDragStarted(startedPosition: Offset) {
        gesture = motion.onDragStarted()
    }

    override suspend fun drag(forEachDelta: suspend ((DragEvent.DragDelta) -> Unit) -> Unit) {
        motion.drag(gesture, forEachDelta)
    }

    override fun onDragStopped(event: DragEvent.DragStopped) {
        if (isAttached) motion.onDragStopped(gesture, event.velocity)
    }
}

/** Tracks the nested fling; independent rebound jobs are queried on pointer down. */
private class PagerChildMotionConnection : NestedScrollConnection {
    var direction: Orientation? = null
        private set

    val isInMotion: Boolean
        get() = direction != null

    // Ignore cancelled child gestures until the next user scroll.
    private var suppressMotionUntilUserScroll = false

    fun stop() {
        direction = null
        suppressMotionUntilUserScroll = true
    }

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        if (source == NestedScrollSource.UserInput) {
            direction = null
            suppressMotionUntilUserScroll = false
        }
        return Offset.Zero
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        if (!suppressMotionUntilUserScroll) {
            direction = when {
                abs(available.y) > abs(available.x) -> Orientation.Vertical
                available.x != 0f -> Orientation.Horizontal
                else -> null
            }
        }
        return Velocity.Zero
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        if (!suppressMotionUntilUserScroll) direction = null
        return Velocity.Zero
    }
}

/**
 * Drives one pager drag and its settling snap.
 *
 * The drag runs under `MutatePriority.UserInput` and the settle under `MutatePriority.Default`, the
 * split a scrollable uses: a new touch cancels the settle, which only real user input interrupts.
 */
private class PagerPageMotion(
    private var pagerState: PagerState,
    private var onIntercepted: (() -> Unit)?,
) {
    private var settleJob: Job? = null
    private var takeoverJob: Job? = null
    private var dragJob: Job? = null
    private var takeoverChannel: Channel<Float>? = null
    private var releaseVelocity = Velocity.Zero
    private var generation = 0L
    var childMotion: PagerChildMotionConnection? = null
    var nodeScope: CoroutineScope? = null
    var minimumVelocity: Float = 0f
    var touchSlop: Float = 0f
    var directionSign: Float = -1f

    fun update(state: PagerState, intercepted: (() -> Unit)?) {
        pagerState = state
        onIntercepted = intercepted
    }

    fun cancel() {
        generation++
        settleJob?.cancel()
        settleJob = null
        takeoverChannel?.cancel()
        takeoverChannel = null
        takeoverJob?.cancel()
        takeoverJob = null
        dragJob?.cancel()
        dragJob = null
    }

    fun detach() {
        nodeScope = null
        cancel()
    }

    fun onDragStarted(): Long {
        cancel()
        onIntercepted?.invoke()
        return generation
    }

    fun stopChildMotion() {
        childMotion?.stop()
    }

    /** One job owns both the mutation and its release, with no asynchronous join-to-snap gap. */
    fun startTakeoverDrag() {
        val scope = nodeScope ?: return
        val state = pagerState
        val sign = directionSign
        val gesture = generation
        val channel = Channel<Float>(Channel.UNLIMITED)
        takeoverChannel = channel
        takeoverJob = scope.launch(start = CoroutineStart.UNDISPATCHED) {
            try {
                state.scroll(MutatePriority.UserInput) {
                    for (delta in channel) scrollBy(delta * sign)
                }
                // Cancellation by another state mutation exits above, without snapping over it.
                if (gesture == generation) settle(state, sign, releaseVelocity)
            } finally {
                channel.cancel()
            }
        }
    }

    fun dragBy(deltaX: Float) {
        takeoverChannel?.trySend(deltaX)
    }

    /** Flushes the queued deltas, then the drag settles with [velocity]. */
    fun finishTakeoverDrag(velocity: Velocity) {
        val channel = takeoverChannel ?: return
        releaseVelocity = velocity
        takeoverChannel = null
        settleJob = takeoverJob
        takeoverJob = null
        channel.close()
    }

    suspend fun drag(gesture: Long, forEachDelta: suspend ((DragEvent.DragDelta) -> Unit) -> Unit) = coroutineScope {
        if (gesture != generation) return@coroutineScope
        val state = pagerState
        val sign = directionSign
        val job = currentCoroutineContext()[Job]
        dragJob = job
        try {
            state.scroll(MutatePriority.UserInput) {
                forEachDelta { dragDelta -> scrollBy(dragDelta.delta.x * sign) }
            }
        } catch (cancelled: CancellationException) {
            // DragStopped(Zero) also arrives for mutation cancellation; only a normal return snaps.
            if (gesture == generation) generation++
            throw cancelled
        } finally {
            if (dragJob === job) dragJob = null
        }
    }

    fun onDragStopped(gesture: Long, velocity: Velocity) {
        if (gesture != generation) return
        val scope = nodeScope ?: return
        val state = pagerState
        val sign = directionSign
        settleJob?.cancel()
        settleJob = scope.launch(start = CoroutineStart.UNDISPATCHED) { settle(state, sign, velocity) }
    }

    private suspend fun settle(state: PagerState, sign: Float, velocity: Velocity) {
        val minimum = minimumVelocity
        state.scroll(MutatePriority.Default) {
            val position = state.currentPage + state.currentPageOffsetFraction
            val signedVelocity = velocity.x * sign
            val target = when {
                signedVelocity > minimum -> ceil(position).toInt()
                signedVelocity < -minimum -> floor(position).toInt()
                else -> position.roundToInt()
            }
            state.animateToPage(this, target, signedVelocity)
        }
    }
}
