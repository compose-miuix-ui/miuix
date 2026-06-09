// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.nav.runtime.NavDriverSpec
import top.yukonga.miuix.kmp.nav.runtime.navBackCommitDecision
import top.yukonga.miuix.kmp.nav.runtime.settleTo
import top.yukonga.miuix.kmp.nav.runtime.snapToFinger
import kotlin.math.abs

/**
 * Interactive horizontal back swipe that drives `animatedTop` directly, finger-following.
 *
 * Attach to the top (non-root) entry. It uses [detectHorizontalDragGestures], so it claims only
 * horizontal drags (after horizontal touch slop) and lets vertical scrolling and taps inside the
 * page through untouched — the swipe is recognised anywhere across the page, not only at the screen
 * edge. While dragging, `animatedTop` is driven via Phase 3's [snapToFinger] to `topIndex - progress`,
 * where `progress` is the horizontal drag distance over layout width (clamped `0f..1f` inside
 * [snapToFinger]) — linear, 1:1 with the finger (§7.1, no interpolation on this axis). On release the
 * decision is delegated to Phase 3's [navBackCommitDecision] (**velocity-first, position-fallback**,
 * §7.2), using the shared thresholds [NavDriverSpec.COMMIT_VELOCITY_THRESHOLD] /
 * [NavDriverSpec.COMMIT_POSITION_THRESHOLD].
 *
 * Release velocity is sampled with a [VelocityTracker] (instantaneous, not a per-event delta proxy)
 * and expressed in **progress-units per second** (pixels/sec ÷ layout width). That value feeds
 * [navBackCommitDecision] directly. For the convergence spring it is negated to the depth axis
 * (`animatedTop = topIndex - progress`, so depth velocity = -progress velocity) and handed to the
 * single shared spring via [settleTo]'s `initialVelocity`, making the snap -> spring handoff
 * velocity-continuous (§0.3 single spring).
 *
 * On commit [onCommit] is invoked **synchronously**, decoupled from the settle (see the `onDragEnd`
 * body for why gating the pop behind the settle is unsafe in a per-entry coroutine scope).
 *
 * @param enabled Whether the swipe is active. Disable on the root entry (nothing to pop).
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
fun Modifier.navEdgeSwipe(
    enabled: Boolean,
    animatedTop: Animatable<Float, AnimationVector1D>,
    topIndex: Int,
    onCommit: () -> Unit,
    onCancel: () -> Unit,
): Modifier = composed {
    if (!enabled) return@composed this

    val scope = rememberCoroutineScope()
    val currentOnCommit = rememberUpdatedState(onCommit)
    val currentOnCancel = rememberUpdatedState(onCancel)

    var layoutSize by remember { mutableStateOf(IntSize.Zero) }

    this
        .onSizeChanged { layoutSize = it }
        .pointerInput(enabled, topIndex, layoutSize.width) {
            val width = layoutSize.width.toFloat().coerceAtLeast(1f)
            // Accumulated horizontal drag in pixels (rightward positive).
            var dragX = 0f
            // Tracks pointer position over time for an accurate instantaneous release velocity.
            val velocityTracker = VelocityTracker()

            detectHorizontalDragGestures(
                onDragStart = {
                    dragX = 0f
                    velocityTracker.resetTracking()
                },
                onHorizontalDrag = { change, dragAmount ->
                    change.consume()
                    dragX += dragAmount
                    velocityTracker.addPosition(change.uptimeMillis, change.position)
                    scope.launch {
                        animatedTop.snapToFinger(topIndex = topIndex, progress = abs(dragX) / width)
                    }
                },
                onDragEnd = {
                    val progress = (abs(dragX) / width).coerceIn(0f, 1f)
                    // Instantaneous horizontal release velocity in pixels/sec, converted to the
                    // progress axis (positive points toward pop for an LTR right-swipe).
                    val progressVelocity = velocityTracker.calculateVelocity().x / width
                    val committing = navBackCommitDecision(progress = progress, velocity = progressVelocity)
                    // Depth axis is the inverse of the progress axis, so negate for the spring handoff.
                    val depthVelocity = -progressVelocity
                    if (committing) {
                        // Commit the back-stack change SYNCHRONOUSLY, never gated behind the settle.
                        // `scope` is this entry's composition scope; the commit settle drives `animatedTop`
                        // to `topIndex - 1`, at which point this (top) entry reaches relativeDepth -1, leaves
                        // the visible window, and its host leaves composition — cancelling `scope`. If the pop
                        // were sequenced AFTER `settleTo` inside that scope (the old ordering), the
                        // cancellation could drop the pop entirely: the back stack would stay unchanged while
                        // the renderer's shared spring pulls `animatedTop` back to the un-popped top, so the
                        // page "springs back" and feels stuck. Popping first lets the renderer own the final
                        // convergence; the settle below only seeds the spring with the release velocity.
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
                },
                onDragCancel = {
                    scope.launch {
                        animatedTop.settleTo(target = topIndex.toFloat(), spec = NavDriverSpec.Default)
                        currentOnCancel.value()
                    }
                },
            )
        }
}
