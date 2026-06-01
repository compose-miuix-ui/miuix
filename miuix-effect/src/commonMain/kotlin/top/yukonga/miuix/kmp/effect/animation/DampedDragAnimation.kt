// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.animation

// Adapted from Kyant0/AndroidLiquidGlass — https://github.com/Kyant0/AndroidLiquidGlass (Apache 2.0).

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.MutatorMutex
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.util.fastFirstOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.time.TimeSource

/**
 * A damped drag animation controller providing animated value tracking, press/release
 * scale transitions, and velocity-aware spring physics.
 *
 * This class manages a multi-property animation system designed for interactive
 * draggable UI elements (e.g., tab indicators, slider thumbs). It coordinates:
 *
 * - **Value animation**: Tracks the primary drag position within a [valueRange],
 *   using critically-damped spring physics for smooth, momentum-based movement.
 * - **Press progress**: An animated `[0, 1]` value indicating press state, useful
 *   for driving visual feedback (e.g., highlight intensity, shadow depth).
 * - **Scale animation**: Separate X/Y scale values that animate to [pressedScale]
 *   on press and back to [initialScale] on release, with independent spring specs
 *   for a tactile "squeeze" feel.
 * - **Velocity tracking**: Continuously updated velocity normalized to the value
 *   range span, enabling velocity-dependent visual effects (e.g., squash-and-stretch).
 *
 * ### Lifecycle
 *
 * 1. Create an instance with callbacks for drag events.
 * 2. Apply [modifier] to the draggable component for gesture handling.
 * 3. Read [value], [pressProgress], [scaleX], [scaleY], and [velocity] to drive
 *    visual transforms in `graphicsLayer {}` or `drawWithContent {}`.
 * 4. Call [animateToValue] to programmatically snap to a target (e.g., on tab selection).
 *
 * ### Usage
 *
 * ```kotlin
 * val dampedDrag = remember(animationScope) {
 *     DampedDragAnimation(
 *         animationScope = animationScope,
 *         initialValue = 0f,
 *         valueRange = 0f..3f,
 *         visibilityThreshold = 0.001f,
 *         initialScale = 1f,
 *         pressedScale = 1.4f,
 *         onDragStopped = {
 *             animateToValue(targetValue.roundToInt().toFloat())
 *         },
 *         onDrag = { _, dragAmount -> updateValue(targetValue + dragAmount.x) },
 *     )
 * }
 *
 * Box(
 *     modifier = Modifier
 *         .then(dampedDrag.modifier)
 *         .graphicsLayer {
 *             scaleX = dampedDrag.scaleX
 *             scaleY = dampedDrag.scaleY
 *         }
 * )
 * ```
 *
 * @param animationScope Coroutine scope for all animation coroutines.
 * @param initialValue Starting value for the position animation.
 * @param valueRange Allowed range for the position value.
 * @param visibilityThreshold Minimum change considered visible (affects spring stiffness).
 * @param initialScale Scale factor when not pressed.
 * @param pressedScale Scale factor when fully pressed.
 * @param canDrag Predicate determining whether a drag starting at the given offset
 *   should be accepted. Return `false` to reject drags outside the interactive area.
 * @param onDragStarted Called when a drag gesture begins.
 * @param onDragStopped Called when a drag gesture ends or is cancelled.
 * @param onDrag Called on each drag event with the component size and drag delta.
 */
class DampedDragAnimation(
    private val animationScope: CoroutineScope,
    val initialValue: Float,
    val valueRange: ClosedRange<Float>,
    val visibilityThreshold: Float,
    val initialScale: Float,
    val pressedScale: Float,
    val canDrag: (Offset) -> Boolean = { true },
    val onDragStarted: DampedDragAnimation.(position: Offset) -> Unit,
    val onDragStopped: DampedDragAnimation.() -> Unit,
    val onDrag: DampedDragAnimation.(size: IntSize, dragAmount: Offset) -> Unit,
) {

    private val valueAnimationSpec = spring(1f, 1000f, visibilityThreshold)
    private val velocityAnimationSpec = spring(0.5f, 300f, visibilityThreshold * 10f)
    private val pressProgressAnimationSpec = spring(1f, 1000f, 0.001f)
    private val scaleXAnimationSpec = spring(0.6f, 250f, 0.001f)
    private val scaleYAnimationSpec = spring(0.7f, 250f, 0.001f)

    private val valueAnimation = Animatable(initialValue, visibilityThreshold)
    private val velocityAnimation = Animatable(0f, 5f)
    private val pressProgressAnimation = Animatable(0f, 0.001f)
    private val scaleXAnimation = Animatable(initialScale, 0.001f)
    private val scaleYAnimation = Animatable(initialScale, 0.001f)

    private val mutatorMutex = MutatorMutex()

    private var pressJob: Job? = null
    private var releaseJob: Job? = null

    private val velocityTracker = VelocityTracker()

    private val startMark = TimeSource.Monotonic.markNow()

    private fun nowMillis(): Long = startMark.elapsedNow().inWholeMilliseconds

    /** Current animated position value within [valueRange]. */
    val value: Float get() = valueAnimation.value

    /** Target value the position animation is converging toward. */
    val targetValue: Float get() = valueAnimation.targetValue

    /** Current press progress in range `[0, 1]`. `1f` = fully pressed. */
    val pressProgress: Float get() = pressProgressAnimation.value

    /** Current horizontal scale factor. Animates toward [pressedScale] on press. */
    val scaleX: Float get() = scaleXAnimation.value

    /** Current vertical scale factor. Animates toward [pressedScale] on press. */
    val scaleY: Float get() = scaleYAnimation.value

    /** Current velocity, normalized to the [valueRange] span. */
    val velocity: Float get() = velocityAnimation.value

    /**
     * A [Modifier] that captures drag gestures and drives all animations.
     *
     * Must be applied to the interactive component. Handles pointer down (press),
     * drag (value update), and pointer up (release) events.
     */
    val modifier: Modifier = Modifier.pointerInput(Unit) {
        inspectDragGestures(
            onDragStart = { down ->
                onDragStarted(down.position)
                press()
            },
            onDragEnd = {
                onDragStopped()
                release()
            },
            onDragCancel = {
                onDragStopped()
                release()
            },
        ) { change, dragAmount ->
            val isInside = canDrag(change.position)
            val wasInside = canDrag(change.previousPosition)
            if (isInside && wasInside) {
                onDrag(size, dragAmount)
            }
        }
    }

    /**
     * Initiates the press animation, transitioning scale and press progress to their
     * pressed states. Cancels any in-progress release animation.
     */
    fun press() {
        releaseJob?.cancel()
        pressJob?.cancel()
        velocityTracker.resetTracking()
        pressJob = animationScope.launch {
            launch { pressProgressAnimation.animateTo(1f, pressProgressAnimationSpec) }
            launch { scaleXAnimation.animateTo(pressedScale, scaleXAnimationSpec) }
            launch { scaleYAnimation.animateTo(pressedScale, scaleYAnimationSpec) }
        }
    }

    /**
     * Initiates the release animation, waiting for the value to settle before
     * transitioning scale and press progress back to their resting states.
     */
    fun release() {
        releaseJob?.cancel()
        releaseJob = animationScope.launch {
            withFrameMillis { }
            if (value != targetValue) {
                val threshold = (valueRange.endInclusive - valueRange.start) * 0.025f
                snapshotFlow { valueAnimation.value }
                    .filter { abs(it - valueAnimation.targetValue) < threshold }
                    .first()
            }
            launch { pressProgressAnimation.animateTo(0f, pressProgressAnimationSpec) }
            launch { scaleXAnimation.animateTo(initialScale, scaleXAnimationSpec) }
            launch { scaleYAnimation.animateTo(initialScale, scaleYAnimationSpec) }
        }
    }

    /**
     * Animates the position value toward [value], coereced to [valueRange].
     * Uses spring physics with velocity tracking for momentum-based movement.
     */
    fun updateValue(value: Float) {
        val targetValue = value.coerceIn(valueRange)
        animationScope.launch {
            valueAnimation.animateTo(targetValue, valueAnimationSpec) { updateVelocity() }
        }
    }

    /**
     * Programmatically animates to the given [value] (e.g., on tab selection).
     *
     * Presses, animates the position, optionally damps residual velocity, then
     * releases. Uses [MutatorMutex] to cancel conflicting drag gestures.
     */
    fun animateToValue(value: Float) {
        animationScope.launch {
            mutatorMutex.mutate {
                press()
                val targetValue = value.coerceIn(valueRange)
                launch { valueAnimation.animateTo(targetValue, valueAnimationSpec) }
                if (velocity != 0f) {
                    launch { velocityAnimation.animateTo(0f, velocityAnimationSpec) }
                }
                release()
            }
        }
    }

    private fun updateVelocity() {
        velocityTracker.addPosition(nowMillis(), Offset(value, 0f))
        val span = (valueRange.endInclusive - valueRange.start).coerceAtLeast(1e-6f)
        val targetVelocity = velocityTracker.calculateVelocity().x / span
        animationScope.launch(start = CoroutineStart.UNDISPATCHED) {
            velocityAnimation.snapTo(targetVelocity)
        }
    }
}

/**
 * Custom drag gesture detector that provides drag start, drag, drag end, and drag
 * cancel callbacks with proper multi-pointer handling.
 *
 * This differs from the standard `detectDragGestures` in that it:
 * - Calls `onDragStart` with the initial down event (not a separate `onPress`).
 * - Passes the initial down event to `onDrag` with `Offset.Zero` for immediate
 *   visual feedback on pointer-down.
 * - Supports pointer ID remapping when the original pointer goes up and another
 *   pointer is already down.
 *
 * @param onDragStart Called with the first pointer-down event.
 * @param onDragEnd Called when the active pointer goes up.
 * @param onDragCancel Called when the gesture is cancelled (e.g., pointer consumed).
 * @param onDrag Called on each drag event with the pointer change and drag delta.
 */
suspend fun PointerInputScope.inspectDragGestures(
    onDragStart: (down: PointerInputChange) -> Unit = {},
    onDragEnd: (change: PointerInputChange) -> Unit = {},
    onDragCancel: () -> Unit = {},
    onDrag: (change: PointerInputChange, dragAmount: Offset) -> Unit,
) {
    awaitEachGesture {
        val initialDown = awaitFirstDown(false, PointerEventPass.Initial)
        val down = awaitFirstDown(false)
        onDragStart(down)
        onDrag(initialDown, Offset.Zero)
        val upEvent = drag(
            pointerId = initialDown.id,
            onDrag = { onDrag(it, it.positionChange()) },
        )
        if (upEvent == null) {
            onDragCancel()
        } else {
            onDragEnd(upEvent)
        }
    }
}

private suspend inline fun AwaitPointerEventScope.drag(
    pointerId: PointerId,
    onDrag: (PointerInputChange) -> Unit,
): PointerInputChange? {
    val isPointerUp = currentEvent.changes.fastFirstOrNull { it.id == pointerId }?.pressed != true
    if (isPointerUp) return null
    var pointer = pointerId
    while (true) {
        val change = awaitDragOrUp(pointer) ?: return null
        if (change.isConsumed) return null
        if (change.changedToUpIgnoreConsumed()) return change
        onDrag(change)
        pointer = change.id
    }
}

private suspend inline fun AwaitPointerEventScope.awaitDragOrUp(
    pointerId: PointerId,
): PointerInputChange? {
    var pointer = pointerId
    while (true) {
        val event = awaitPointerEvent()
        val dragEvent = event.changes.fastFirstOrNull { it.id == pointer } ?: return null
        if (dragEvent.changedToUpIgnoreConsumed()) {
            val otherDown = event.changes.fastFirstOrNull { it.pressed }
            if (otherDown == null) {
                return dragEvent
            } else {
                pointer = otherDown.id
            }
        } else {
            val hasDragged = dragEvent.previousPosition != dragEvent.position
            if (hasDragged) return dragEvent
        }
    }
}
