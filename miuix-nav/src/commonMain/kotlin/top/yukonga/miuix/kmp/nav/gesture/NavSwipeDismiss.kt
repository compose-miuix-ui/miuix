// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.nav.runtime.NavDriverSpec
import top.yukonga.miuix.kmp.nav.runtime.navBackCommitDecision
import top.yukonga.miuix.kmp.nav.runtime.settleTo
import top.yukonga.miuix.kmp.nav.runtime.snapToFinger
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection

/**
 * Interactive swipe-to-dismiss that drives `animatedTop` directly, finger-following, along the axis
 * and direction the current transition declares ([NavSwipeDirection]).
 *
 * Attach to the top (non-root) entry. The gesture follows the same motion as the transition: a
 * horizontal slide ([NavSwipeDirection.LeftToRight] / [NavSwipeDirection.RightToLeft]) is dismissed
 * by a horizontal swipe via [detectHorizontalDragGestures]; a bottom-up modal
 * ([NavSwipeDirection.TopToBottom] / [NavSwipeDirection.BottomToTop]) by a vertical swipe via
 * [detectVerticalDragGestures]. Using the orientation-locked detectors means the swipe claims only
 * its own axis and lets the page's cross-axis scrolling and taps through untouched.
 * [NavSwipeDirection.None] disables the gesture entirely (pop via back button / system back).
 *
 * While dragging, `animatedTop` is driven via Phase 3's [snapToFinger] to `topIndex - progress`,
 * where `progress` is the finger travel **in the dismiss direction** over the layout extent on that
 * axis (clamped `0f..1f`) — linear, 1:1 with the finger (§7.1, no interpolation on this axis).
 * Travel opposite the dismiss direction yields `0` progress (it never drags the entry "forward").
 * On release the decision is delegated to Phase 3's [navBackCommitDecision] (**velocity-first,
 * position-fallback**, §7.2), using the shared thresholds [NavDriverSpec.COMMIT_VELOCITY_THRESHOLD] /
 * [NavDriverSpec.COMMIT_POSITION_THRESHOLD].
 *
 * Release velocity is sampled with a [VelocityTracker] (instantaneous, not a per-event delta proxy)
 * and expressed in **progress-units per second** (axis pixels/sec ÷ layout extent, signed toward the
 * dismiss direction). That value feeds [navBackCommitDecision] directly. For the convergence spring it
 * is negated to the depth axis (`animatedTop = topIndex - progress`, so depth velocity = -progress
 * velocity) and handed to the single shared spring via [settleTo]'s `initialVelocity`, making the snap
 * -> spring handoff velocity-continuous (§0.3 single spring).
 *
 * On commit [onCommit] is invoked **synchronously**, decoupled from the settle (see the `onDragEnd`
 * body for why gating the pop behind the settle is unsafe in a per-entry coroutine scope).
 *
 * @param enabled Whether the swipe is active. Disable on the root entry (nothing to pop).
 * @param direction The dismiss axis + direction; [NavSwipeDirection.None] disables the gesture.
 * @param animatedTop The single shared depth driver.
 * @param topIndex Current top entry index being peeled away.
 * @param onCommit Invoked synchronously the moment a release is classified as a commit (pop the back
 *   stack here); the shared spring then settles `animatedTop` to the new top.
 * @param onCancel Invoked once after the cancel spring settles back to [topIndex].
 */
// composed { } is required here: the swipe needs composition-scoped state (coroutine scope,
// remembered layout size). A Modifier.Node rewrite is a deferred optimization, so suppress the
// no-composed lint locally rather than disabling the performance rule project-wide.
@Suppress("ktlint:compose:modifier-composed-check")
fun Modifier.navSwipeDismiss(
    enabled: Boolean,
    direction: NavSwipeDirection,
    animatedTop: Animatable<Float, AnimationVector1D>,
    topIndex: Int,
    onCommit: () -> Unit,
    onCancel: () -> Unit,
): Modifier = composed {
    if (!enabled || direction == NavSwipeDirection.None) return@composed this

    val scope = rememberCoroutineScope()
    val currentOnCommit = rememberUpdatedState(onCommit)
    val currentOnCancel = rememberUpdatedState(onCancel)

    var layoutSize by remember { mutableStateOf(IntSize.Zero) }

    val isHorizontal = direction == NavSwipeDirection.LeftToRight || direction == NavSwipeDirection.RightToLeft
    // Sign mapping a raw axis delta onto "progress toward dismiss": +1 when the dismiss direction is the
    // axis-positive one (right / down), -1 when it is axis-negative (left / up).
    val dismissSign = when (direction) {
        NavSwipeDirection.LeftToRight, NavSwipeDirection.TopToBottom -> 1f
        NavSwipeDirection.RightToLeft, NavSwipeDirection.BottomToTop -> -1f
        NavSwipeDirection.None -> 0f // unreachable (guarded above)
    }

    this
        .onSizeChanged { layoutSize = it }
        .pointerInput(enabled, direction, topIndex, layoutSize) {
            // Layout extent along the gesture axis, used to normalise finger travel into 0f..1f progress.
            val extent = (if (isHorizontal) layoutSize.width else layoutSize.height).toFloat().coerceAtLeast(1f)
            // Accumulated drag along the gesture axis (raw signed pixels).
            var drag = 0f
            // Tracks pointer position over time for an accurate instantaneous release velocity.
            val velocityTracker = VelocityTracker()

            val onStart = {
                drag = 0f
                velocityTracker.resetTracking()
            }
            val onDrag = { change: PointerInputChange, amount: Float ->
                change.consume()
                drag += amount
                velocityTracker.addPosition(change.uptimeMillis, change.position)
                scope.launch {
                    animatedTop.snapToFinger(topIndex = topIndex, progress = (dismissSign * drag / extent).coerceIn(0f, 1f))
                }
            }
            val onEnd = {
                val progress = (dismissSign * drag / extent).coerceIn(0f, 1f)
                // Instantaneous release velocity along the axis, in pixels/sec, projected onto the
                // dismiss direction and normalised to progress-units/sec (positive points toward pop).
                val velocity = velocityTracker.calculateVelocity()
                val axisVelocity = if (isHorizontal) velocity.x else velocity.y
                val progressVelocity = dismissSign * axisVelocity / extent
                val committing = navBackCommitDecision(progress = progress, velocity = progressVelocity)
                // Depth axis is the inverse of the progress axis, so negate for the spring handoff.
                val depthVelocity = -progressVelocity
                if (committing) {
                    // Commit the back-stack change SYNCHRONOUSLY, never gated behind the settle. `scope`
                    // is this entry's composition scope; the commit settle drives `animatedTop` to
                    // `topIndex - 1`, at which point this (top) entry reaches relativeDepth -1, leaves the
                    // visible window, and its host leaves composition -- cancelling `scope`. If the pop
                    // were sequenced AFTER `settleTo` inside that scope, the cancellation could drop the
                    // pop entirely: the back stack would stay unchanged while the renderer's shared spring
                    // pulls `animatedTop` back to the un-popped top, so the page "springs back" and feels
                    // stuck. Popping first lets the renderer own the final convergence; the settle below
                    // only seeds the spring with the release velocity.
                    currentOnCommit.value()
                    scope.launch {
                        animatedTop.settleTo(
                            target = (topIndex - 1).toFloat(),
                            spec = NavDriverSpec.Default,
                            initialVelocity = depthVelocity,
                        )
                    }
                } else {
                    // Cancel keeps the entry in place (never marked removing), so its scope survives the
                    // settle and onCancel can safely run after it.
                    scope.launch {
                        animatedTop.settleTo(
                            target = topIndex.toFloat(),
                            spec = NavDriverSpec.Default,
                            initialVelocity = depthVelocity,
                        )
                        currentOnCancel.value()
                    }
                }
            }
            val onCancelDrag = {
                scope.launch {
                    animatedTop.settleTo(target = topIndex.toFloat(), spec = NavDriverSpec.Default)
                    currentOnCancel.value()
                }
                Unit
            }

            if (isHorizontal) {
                detectHorizontalDragGestures(
                    onDragStart = { onStart() },
                    onHorizontalDrag = { change, dragAmount -> onDrag(change, dragAmount) },
                    onDragEnd = { onEnd() },
                    onDragCancel = { onCancelDrag() },
                )
            } else {
                detectVerticalDragGestures(
                    onDragStart = { onStart() },
                    onVerticalDrag = { change, dragAmount -> onDrag(change, dragAmount) },
                    onDragEnd = { onEnd() },
                    onDragCancel = { onCancelDrag() },
                )
            }
        }
}
