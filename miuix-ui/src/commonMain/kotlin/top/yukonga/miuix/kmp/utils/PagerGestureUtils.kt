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
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.changedToDownIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.input.pointer.util.addPointerInputChange
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.PointerInputModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirstOrNull
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
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

    /**
     * While a child is flinging, the first horizontal swipe stops it without paging.
     */
    TapToHalt("iOS-like"),
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
 * val flingBehavior = PagerDefaults.flingBehavior(pagerState, PagerNavigationSpringSpec)
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
 * @param flingTracker required by [PagerInterceptionMode.TapToHalt] only.
 * @param onTriggered called when the pager takes over an input.
 */
fun Modifier.pagerGestureOverride(
    pagerState: PagerState,
    flingBehavior: FlingBehavior,
    mode: PagerInterceptionMode = PagerInterceptionMode.CrossAxis,
    enabled: Boolean = true,
    flingTracker: PagerFlingTrackerConnection? = null,
    onTriggered: (() -> Unit)? = null,
): Modifier = when (mode) {
    PagerInterceptionMode.CrossAxis ->
        if (enabled) {
            pagerNonTouchScroll(pagerState, flingBehavior).then(PagerPageGestureElement(pagerState, onTriggered))
        } else {
            this
        }

    PagerInterceptionMode.TapToHalt ->
        if (enabled && flingTracker != null) {
            iosStyleMomentumHalt(
                flingTracker = flingTracker,
                enabled = true,
                onHalted = onTriggered,
            )
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
    flingTracker: PagerFlingTrackerConnection? = null,
    onTriggered: (() -> Unit)? = null,
): Modifier = pagerGestureOverride(
    pagerState = pagerState,
    flingBehavior = flingBehavior,
    mode = PagerInterceptionMode.entries.getOrElse(mode) { PagerInterceptionMode.Native },
    enabled = enabled,
    flingTracker = flingTracker,
    onTriggered = onTriggered,
)

/**
 * Applies [mode] to a [HorizontalPager] with a remembered [PagerFlingTrackerConnection].
 *
 * See the [FlingBehavior] overload for the pager configuration this requires.
 */
@Composable
fun Modifier.pagerGestureOverride(
    pagerState: PagerState,
    flingBehavior: FlingBehavior,
    mode: PagerInterceptionMode = PagerInterceptionMode.CrossAxis,
    enabled: Boolean = true,
    onTriggered: (() -> Unit)? = null,
): Modifier {
    val flingTracker = remember { PagerFlingTrackerConnection() }
    return pagerGestureOverride(
        pagerState = pagerState,
        flingBehavior = flingBehavior,
        mode = mode,
        enabled = enabled,
        flingTracker = flingTracker,
        onTriggered = onTriggered,
    )
}

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

/**
 * The pager's touch drag recognizer, installed by `Modifier.pagerGestureOverride`.
 *
 * It is a Foundation [DragGestureNode] on the pager's horizontal axis, so the framework's own angle
 * based arbitration decides between it and the page's children: a horizontal child the drag is
 * aligned with wins, and a drag aligned with the pager, or one that starts outside such a child, is
 * taken here.
 *
 * [startDragImmediately] is the escape hatch for a child that is flinging or overscrolling. Such a
 * child keeps claiming every touch for as long as its animation runs, so the pager has to take the
 * down on the [PointerEventPass.Initial] pass, which ancestors see first, and stop the child.
 * Whether a descendant is in motion is learned from the nested scroll traffic the child leaves over.
 */
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
    }

    // CompositionLocal reads have to happen while the node is attached, and the pager's layout
    // direction can change after that, so both are refreshed from the node's callbacks.
    override fun onAttach() {
        motion.nodeScope = coroutineScope
        refreshEnvironment()
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

    private val dragNode = delegate(PagerPageDragNode(motion, childMotion))
    private val nestedScrollNode = delegate(nestedScrollModifierNode(childMotion, null))
    private val takeoverNode = delegate(PagerPageTakeoverNode(motion, childMotion))

    override fun onPointerEvent(pointerEvent: PointerEvent, pass: PointerEventPass, bounds: IntSize) {
        if (pass == PointerEventPass.Initial) {
            val down = pointerEvent.changes.fastFirstOrNull { it.changedToDownIgnoreConsumed() }
            // A touch that lands during a page transition stops it where it is, whichever node
            // ends up owning the gesture, the same way a scrollable stops when it is touched.
            if (down != null) motion.onTouchDown()
        }
        // Takeover must own the complete pointer cycle. Feeding that same cycle to the Foundation
        // drag node would start a second UserInput mutation; reset the Foundation node when a
        // takeover claims a down so its previous child-owned cycle cannot remain latched.
        val takeoverWasTrackingPointer = takeoverNode.isTrackingPointer
        if (takeoverNode.willTrackPointer(pointerEvent, pass)) {
            dragNode.onCancelPointerInput()
        }
        takeoverNode.onPointerEvent(pointerEvent, pass, bounds)
        if (takeoverWasTrackingPointer && !takeoverNode.isTrackingPointer) {
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
        motion.update(state, onIntercepted)
        refreshEnvironment()
    }
}

/**
 * Takes a touch away from a child that is flinging or overscrolling.
 *
 * The child latched its own drag on the very first down, and because [PointerEventPass.Main] walks
 * children before their parents, it keeps consuming the movement there. Consuming on
 * [PointerEventPass.Initial] — parents first — is the only pass where an ancestor can still take
 * the gesture, so this node drives the pager itself while the child is in motion.
 *
 * It stays out of the way for a fresh gesture, which [PagerPageDragNode] and Compose's angle
 * arbitration handle.
 */
private class PagerPageTakeoverNode(
    private val motion: PagerPageMotion,
    private val childMotion: PagerChildMotionConnection,
) : Modifier.Node(),
    PointerInputModifierNode {
    private var pointerId: PointerId? = null
    private var dragged = false
    private var accumulated = Offset.Zero
    private val velocityTracker = VelocityTracker()

    var isHandlingPointer: Boolean = false
        private set

    val isTrackingPointer: Boolean
        get() = pointerId != null

    fun willTrackPointer(pointerEvent: PointerEvent, pass: PointerEventPass): Boolean = pass == PointerEventPass.Initial &&
        pointerId == null &&
        childMotion.isInMotion &&
        pointerEvent.changes.fastFirstOrNull { it.changedToDownIgnoreConsumed() } != null

    override fun onPointerEvent(pointerEvent: PointerEvent, pass: PointerEventPass, bounds: IntSize) {
        if (pass != PointerEventPass.Initial) return
        val tracked = pointerId
        if (tracked == null) {
            val down = pointerEvent.changes.fastFirstOrNull { it.changedToDownIgnoreConsumed() } ?: return
            if (!childMotion.isInMotion) return
            pointerId = down.id
            isHandlingPointer = false
            dragged = false
            accumulated = Offset.Zero
            velocityTracker.resetTracking()
            velocityTracker.addPointerInputChange(down)
            // The touch itself supersedes the child's old animation. Keep the local tracking
            // decision alive so a later horizontal delta can still be handed to the pager.
            motion.stopChildMotion()
            return
        }
        val change = pointerEvent.changes.fastFirstOrNull { it.id == tracked } ?: return
        if (!change.pressed) {
            if (!isHandlingPointer) {
                pointerId = null
                accumulated = Offset.Zero
                motion.stopChildMotion()
                return
            }
            change.consume()
            val velocity = if (dragged) velocityTracker.calculateVelocity().x else 0f
            pointerId = null
            isHandlingPointer = false
            dragged = false
            accumulated = Offset.Zero
            motion.finishTakeoverDrag(Velocity(velocity, 0f))
            return
        }
        velocityTracker.addPointerInputChange(change)
        val delta = change.position - change.previousPosition
        if (!isHandlingPointer) {
            accumulated += delta
            val x = abs(accumulated.x)
            val y = abs(accumulated.y)
            if (x > motion.touchSlop && x > y) {
                isHandlingPointer = true
                dragged = true
                motion.onDragStarted()
                motion.stopChildMotion()
                motion.startTakeoverDrag()
                change.consume()
                val overSlop = accumulated.x - if (accumulated.x >= 0f) motion.touchSlop else -motion.touchSlop
                motion.dragBy(overSlop)
            } else if (y > motion.touchSlop && y > x) {
                pointerId = null
                accumulated = Offset.Zero
                motion.stopChildMotion()
            }
            return
        }
        change.consume()
        motion.dragBy(delta.x)
    }

    override fun onCancelPointerInput() {
        pointerId = null
        isHandlingPointer = false
        dragged = false
        accumulated = Offset.Zero
        motion.cancelTakeoverDrag()
    }
}

private class PagerPageDragNode(
    private val motion: PagerPageMotion,
    private val childMotion: PagerChildMotionConnection,
) : DragGestureNode(
    canDrag = { it != PointerType.Mouse },
    enabled = true,
    interactionSource = null,
    orientation = Orientation.Horizontal,
) {
    override fun startDragImmediately(): Boolean = false

    override fun onDragStarted(startedPosition: Offset) {
        motion.onDragStarted()
    }

    override suspend fun drag(forEachDelta: suspend ((DragEvent.DragDelta) -> Unit) -> Unit) {
        motion.drag(forEachDelta)
    }

    override fun onDragStopped(event: DragEvent.DragStopped) {
        motion.onDragStopped(event.velocity)
    }
}

/**
 * Reports whether a descendant scrollable is still flinging or overscrolling.
 *
 * `onPreFling` sees the velocity a child leaves over and `onPostFling` runs once the child's
 * animation, rebound included, is done. In between the child holds the gesture, so a new touch has
 * to take it back.
 */
private class PagerChildMotionConnection : NestedScrollConnection {
    var isInMotion: Boolean = false
        private set

    /**
     * Whether the [onPreFling] that opened the current motion came with velocity. A child that ends
     * a drag at its boundary still opens a settling animation with no velocity of its own, and that
     * animation is what has to be interruptible too.
     */
    private var flingHadVelocity = false

    /**
     * A pager takeover can cancel more than one child scrollable. Their cancelled drags can each
     * dispatch a zero-velocity [onPreFling] afterwards, so ignore fling callbacks until the next
     * real user scroll instead of treating a later callback as a fresh settling animation.
     */
    private var suppressMotionUntilUserScroll = false

    fun stop() {
        isInMotion = false
        flingHadVelocity = false
        suppressMotionUntilUserScroll = true
    }

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        // A new user drag supersedes any leftover motion flag.
        if (source == NestedScrollSource.UserInput) {
            isInMotion = false
            flingHadVelocity = false
            suppressMotionUntilUserScroll = false
        }
        return Offset.Zero
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        if (suppressMotionUntilUserScroll) return Velocity.Zero
        flingHadVelocity = available.x != 0f || available.y != 0f
        isInMotion = true
        return Velocity.Zero
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        if (suppressMotionUntilUserScroll) return Velocity.Zero
        // A settling animation runs inside this call, so the flag has to outlive it. Only a fling
        // that carried velocity is done here; an overscroll rebound keeps animating afterwards.
        if (flingHadVelocity) {
            isInMotion = false
            flingHadVelocity = false
        }
        return Velocity.Zero
    }
}

/**
 * Drives one pager drag and its settling snap.
 *
 * The drag runs under `MutatePriority.UserInput` and the settle under `MutatePriority.Default`, the
 * split a scrollable uses: a new touch cancels the settle and takes over from the displayed
 * position, while the settle is interrupted only by real user input.
 */
private class PagerPageMotion(
    pagerState: PagerState,
    onIntercepted: (() -> Unit)?,
) {
    private var pagerState: PagerState = pagerState
    private var onIntercepted: (() -> Unit)? = onIntercepted
    private var settleJob: Job? = null
    private var takeoverJob: Job? = null
    private var takeoverChannel: Channel<Float>? = null
    private var interruptJob: Job? = null

    var childMotion: PagerChildMotionConnection? = null
    lateinit var nodeScope: CoroutineScope
    var minimumVelocity: Float = 0f
    var touchSlop: Float = 0f
    var directionSign: Float = -1f

    fun update(state: PagerState, intercepted: (() -> Unit)?) {
        pagerState = state
        onIntercepted = intercepted
    }

    /**
     * Stops a running page transition without moving the pager, so that a gesture the page's child
     * owns leaves the pager where the transition had reached.
     */
    fun onTouchDown() {
        if (!pagerState.isScrollInProgress) return
        settleJob?.cancel()
        settleJob = null
        interruptJob?.cancel()
        interruptJob = nodeScope.launch(start = CoroutineStart.UNDISPATCHED) {
            pagerState.scroll(MutatePriority.UserInput) { /* preempt only */ }
        }
    }

    fun onDragStarted() {
        settleJob?.cancel()
        settleJob = null
        onIntercepted?.invoke()
    }

    fun stopChildMotion() {
        childMotion?.stop()
    }

    /** Starts one continuous mutation for the takeover gesture. */
    fun startTakeoverDrag() {
        cancelTakeoverDrag()
        val channel = Channel<Float>(Channel.UNLIMITED)
        takeoverChannel = channel
        takeoverJob = nodeScope.launch(start = CoroutineStart.UNDISPATCHED) {
            try {
                pagerState.scroll(MutatePriority.UserInput) {
                    for (delta in channel) {
                        scrollBy(delta * directionSign)
                    }
                }
            } finally {
                channel.cancel()
            }
        }
    }

    /** Queues one delta into the takeover mutation. */
    fun dragBy(deltaX: Float) {
        takeoverChannel?.trySend(deltaX)
    }

    /** Closes the takeover stream after all queued deltas, then starts the snap. */
    fun finishTakeoverDrag(velocity: Velocity) {
        val channel = takeoverChannel
        val job = takeoverJob
        takeoverChannel = null
        takeoverJob = null
        if (channel == null) {
            onDragStopped(velocity)
            return
        }

        channel.close()
        settleJob?.cancel()
        settleJob = nodeScope.launch {
            job?.join()
            settle(velocity)
        }
    }

    /** Cancels an interrupted takeover without leaving a live pager mutation behind. */
    fun cancelTakeoverDrag() {
        takeoverChannel?.cancel()
        takeoverChannel = null
        takeoverJob?.cancel()
        takeoverJob = null
    }

    suspend fun drag(forEachDelta: suspend ((DragEvent.DragDelta) -> Unit) -> Unit) {
        val sign = directionSign
        pagerState.scroll(MutatePriority.UserInput) {
            forEachDelta { dragDelta ->
                scrollBy(dragDelta.delta.x * sign)
            }
        }
    }

    fun onDragStopped(velocity: Velocity) {
        settleJob?.cancel()
        settleJob = nodeScope.launch { settle(velocity) }
    }

    private suspend fun settle(velocity: Velocity) {
        val state = pagerState
        val sign = directionSign
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

/**
 * Tracks child vertical flings and coordinates [PagerInterceptionMode.TapToHalt] requests.
 */
class PagerFlingTrackerConnection : NestedScrollConnection {
    var isChildFlinging by mutableStateOf(false)
        internal set

    var haltFling by mutableStateOf(false)
        internal set

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        if (source == NestedScrollSource.UserInput && available.y != 0f) {
            // A new drag supersedes the pending halt.
            haltFling = false
            isChildFlinging = false
        } else if (source == NestedScrollSource.SideEffect && available.y != 0f && isChildFlinging && haltFling) {
            isChildFlinging = false
            // Cancel the fling; consuming its delta would only hide a frame.
            throw CancellationException("Child fling halted by pager gesture")
        }
        return Offset.Zero
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        // The pending halt applies only to the previous fling.
        haltFling = false
        isChildFlinging = available.y != 0f
        return Velocity.Zero
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        // Keep halted momentum from reaching an ancestor.
        val haltedVelocity = if (haltFling) Velocity(0f, available.y) else Velocity.Zero
        isChildFlinging = false
        haltFling = false
        return haltedVelocity
    }
}

/**
 * Requests a child fling halt on touch down and consumes that gesture's horizontal drag.
 * When no child fling is active, leaves horizontal swipes to the native pager.
 */
fun Modifier.iosStyleMomentumHalt(
    flingTracker: PagerFlingTrackerConnection,
    enabled: Boolean = true,
    onHalted: (() -> Unit)? = null,
): Modifier = if (!enabled) {
    this
} else {
    this.nestedScroll(flingTracker).pointerInput(flingTracker, enabled, onHalted) {
        val touchSlop = viewConfiguration.touchSlop

        awaitEachGesture {
            val down = awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)
            val downPos = down.position
            val hadMomentum = flingTracker.isChildFlinging
            if (!hadMomentum) {
                return@awaitEachGesture
            }

            flingTracker.haltFling = true
            onHalted?.invoke()
            var isHalting = false

            while (true) {
                val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                val change = event.changes.fastFirstOrNull { it.id == down.id } ?: break
                if (!change.pressed) break

                val totalDx = abs(change.position.x - downPos.x)
                val totalDy = abs(change.position.y - downPos.y)

                if (!isHalting && totalDx > touchSlop && totalDx > totalDy * 2f) {
                    isHalting = true
                }

                if (isHalting) {
                    change.consume()
                } else if (totalDy > touchSlop && totalDy > totalDx) {
                    flingTracker.haltFling = false
                    break
                }
            }
        }
    }
}
