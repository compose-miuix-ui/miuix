// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.LocalPullToRefreshState
import top.yukonga.miuix.kmp.basic.RefreshState
import kotlin.math.abs
import kotlin.math.sign

// Based on https://github.com/Cormor/ComposeOverscroll

@Stable
internal val DefaultParabolaScrollEasing: (distance: Float, range: Int) -> Float = { distance, range ->
    val alpha = 0.3f
    val x = (abs(distance) / range).coerceIn(0.0f, 1.0f)
    val orig = x - x * x + (x * x * x / 3.0f)
    val current = (2f * x - x * x) / 3.0f
    val dampedFactor = (1f - alpha) * orig + alpha * current
    dampedFactor * range * sign(distance)
}

internal const val OutBoundSpringStiff = 300f
internal const val OutBoundSpringDamp = 1f

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollVertical(
    nestedScrollToParent: Boolean = true,
    scrollEasing: ((distance: Float, range: Int) -> Float)? = null,
    springStiff: Float = OutBoundSpringStiff,
    springDamp: Float = OutBoundSpringDamp,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): Modifier = overScrollOutOfBound(isVertical = true, nestedScrollToParent, scrollEasing, springStiff, springDamp, isEnabled)

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollHorizontal(
    nestedScrollToParent: Boolean = true,
    scrollEasing: ((distance: Float, range: Int) -> Float)? = null,
    springStiff: Float = OutBoundSpringStiff,
    springDamp: Float = OutBoundSpringDamp,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): Modifier = overScrollOutOfBound(isVertical = false, nestedScrollToParent, scrollEasing, springStiff, springDamp, isEnabled)

/**
 * Overscroll effect when scrolling to the boundary.
 *
 * @param isVertical Whether the overscroll effect is vertical or horizontal.
 * @param nestedScrollToParent Whether to dispatch nested scroll events to parent.
 * @param scrollEasing Easing function for overscroll effect, default is a parabolic easing.
 * @param springStiff springStiff for overscroll effect，generally do not need to set.
 * @param springDamp springDamp for overscroll effect，generally do not need to set.
 * @param isEnabled Whether the overscroll effect is enabled. Default is enabled on Android and iOS only.
 */
@Stable
@Suppress("NAME_SHADOWING")
fun Modifier.overScrollOutOfBound(
    isVertical: Boolean = true,
    nestedScrollToParent: Boolean = true,
    scrollEasing: ((distance: Float, range: Int) -> Float)?,
    springStiff: Float = OutBoundSpringStiff,
    springDamp: Float = OutBoundSpringDamp,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): Modifier = composed {
    if (!isEnabled()) return@composed this

    val overScrollState = LocalOverScrollState.current
    val pullToRefreshState = LocalPullToRefreshState.current
    val currentNestedScrollToParent by rememberUpdatedState(nestedScrollToParent)
    val currentScrollEasing by rememberUpdatedState(scrollEasing ?: DefaultParabolaScrollEasing)
    val currentSpringStiff by rememberUpdatedState(springStiff)
    val currentSpringDamp by rememberUpdatedState(springDamp)
    val currentIsVertical by rememberUpdatedState(isVertical)
    val windowSize = getWindowSize()
    val dispatcher = remember { NestedScrollDispatcher() }
    val coroutineScope = rememberCoroutineScope()
    var offset by remember { mutableFloatStateOf(0f) }

    // Reused Animatable instance for all fling animations to avoid creating many instances
    // and to ensure consistent, smooth continuation between interactions.
    val flingAnimatable = remember { Animatable(0f) }

    val nestedConnection = remember {
        object : NestedScrollConnection {
            /**
             * If the offset is less than this value, we consider the animation to end.
             */
            val visibilityThreshold = 1f
            var currentTouch by mutableStateOf(0f)

            private fun shouldBypassForPullToRefresh(): Boolean {
                // When pull-to-refresh is active (not Idle), always bypass.
                return pullToRefreshState != null && pullToRefreshState.refreshState != RefreshState.Idle && currentIsVertical
            }

            private fun touchToDamped(distance: Float): Float {
                val range = if (currentIsVertical) windowSize.height else windowSize.width
                return currentScrollEasing(distance, range)
            }

            /**
             * Add delta to the current touch tracking value and update offset immediately.
             * Return overflow part which cannot be consumed by overscroll handling.
             *
             * This routine also updates offset synchronously to reduce visual jumps and ensure
             * immediate response to touch deltas.
             */
            private fun addTouchDelta(deltaTouch: Float): Float {
                val maxTouch = (if (currentIsVertical) windowSize.height else windowSize.width).toFloat()
                val target = currentTouch + deltaTouch
                val overflow =
                    when {
                        target > maxTouch -> target - maxTouch
                        target < -maxTouch -> target + maxTouch
                        else -> 0f
                    }
                currentTouch = target.coerceIn(-maxTouch, maxTouch)
                // Update offset immediately to keep UI smooth and prevent stuttering.
                offset = touchToDamped(currentTouch)
                return overflow
            }

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // Check if overScroll should be disabled for drop-down direction
                val newActivePreScroll = abs(offset) > visibilityThreshold
                overScrollState.isOverScrollActive = newActivePreScroll
                if (shouldBypassForPullToRefresh()) {
                    return dispatcher.dispatchPreScroll(available, source)
                }
                // If non-user input (fling/etc), hand over to parent dispatcher
                if (source != NestedScrollSource.UserInput) {
                    return dispatcher.dispatchPreScroll(available, source)
                }
                // If a fling animation is running, request it to stop to blend with user input.
                if (flingAnimatable.isRunning) {
                    coroutineScope.launch {
                        // stop() is suspend; we call it in coroutine context to stop smoothly.
                        flingAnimatable.stop()
                    }
                }
                val realAvailable = when {
                    currentNestedScrollToParent -> available - dispatcher.dispatchPreScroll(available, source)
                    else -> available
                }
                val realOffset = if (currentIsVertical) realAvailable.y else realAvailable.x
                val isSameDirection = sign(realOffset) == sign(currentTouch)
                if (abs(currentTouch) <= visibilityThreshold || isSameDirection) {
                    // No overscroll or continuing in same direction: don't consume here.
                    return available - realAvailable
                }

                val overflow = addTouchDelta(realOffset)
                val offsetAtLast = touchToDamped(currentTouch)
                // sign changed, coerce to start scrolling and exit
                if (sign(currentTouch) != sign(offsetAtLast)) {
                    offset = 0.0f
                    currentTouch = 0.0f
                } else {
                    offset = offsetAtLast
                }
                return if (currentIsVertical) {
                    Offset(available.x - realAvailable.x, available.y - overflow)
                } else {
                    Offset(available.x - overflow, available.y - realAvailable.y)
                }
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                // Check if overScroll should be disabled for drop-down direction
                val newActivePostScroll = abs(offset) > visibilityThreshold
                overScrollState.isOverScrollActive = newActivePostScroll
                if (shouldBypassForPullToRefresh()) {
                    return dispatcher.dispatchPostScroll(consumed, available, source)
                }
                // If non-user input (fling/etc), hand over to parent dispatcher
                if (source != NestedScrollSource.UserInput) {
                    return dispatcher.dispatchPostScroll(consumed, available, source)
                }
                if (flingAnimatable.isRunning) {
                    coroutineScope.launch {
                        flingAnimatable.stop()
                    }
                }
                val realAvailable = when {
                    currentNestedScrollToParent -> available - dispatcher.dispatchPostScroll(consumed, available, source)
                    else -> available
                }
                val realOffset = if (currentIsVertical) realAvailable.y else realAvailable.x
                val overflow = addTouchDelta(realOffset)
                return if (currentIsVertical) {
                    Offset(available.x - realAvailable.x, available.y - overflow)
                } else {
                    Offset(available.x - overflow, available.y - realAvailable.y)
                }
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                // Check if overScroll should be disabled for drop-down direction
                val newActivePreFling = abs(offset) > visibilityThreshold
                overScrollState.isOverScrollActive = newActivePreFling
                if (shouldBypassForPullToRefresh() && !overScrollState.isOverScrollActive) {
                    return dispatcher.dispatchPreFling(available)
                }
                // Stop any in-progress reused animatable to take over cleanly.
                if (flingAnimatable.isRunning) {
                    flingAnimatable.stop()
                }
                val parentConsumed = when {
                    currentNestedScrollToParent -> dispatcher.dispatchPreFling(available)
                    else -> Velocity.Zero
                }
                val realAvailable = available - parentConsumed
                var realVelocity = if (currentIsVertical) realAvailable.y else realAvailable.x

                if (abs(currentTouch) >= visibilityThreshold && sign(realVelocity) != sign(currentTouch)) {
                    // Reuse the shared animatable; ensure it starts from currentTouch for a smooth continuation.
                    flingAnimatable.snapTo(currentTouch)
                    when {
                        realVelocity < 0 -> flingAnimatable.updateBounds(lowerBound = 0f)
                        realVelocity > 0 -> flingAnimatable.updateBounds(upperBound = 0f)
                        else -> {
                            // no-op
                        }
                    }
                    // Animate back to zero with spring, providing initial velocity.
                    val endVelocity = flingAnimatable.animateTo(
                        0.0f,
                        spring(currentSpringDamp, currentSpringStiff, visibilityThreshold),
                        realVelocity
                    ) {
                        currentTouch = value
                        offset = touchToDamped(currentTouch)
                    }.endState.velocity
                    realVelocity = endVelocity
                }

                return if (currentIsVertical) {
                    Velocity(parentConsumed.x, y = available.y - realVelocity)
                } else {
                    Velocity(x = available.x - realVelocity, y = parentConsumed.y)
                }
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                // Check if overScroll should be disabled for drop-down direction
                val newActivePostFling = abs(offset) > visibilityThreshold
                overScrollState.isOverScrollActive = newActivePostFling
                if (shouldBypassForPullToRefresh() && !overScrollState.isOverScrollActive) {
                    return dispatcher.dispatchPostFling(consumed, available)
                }
                // Stop any in-progress reused animatable to take over cleanly.
                if (flingAnimatable.isRunning) {
                    flingAnimatable.stop()
                }
                val realAvailable = when {
                    currentNestedScrollToParent -> available - dispatcher.dispatchPostFling(consumed, available)
                    else -> available
                }
                val initialVelocity = if (currentIsVertical) realAvailable.y else realAvailable.x

                // Start a spring animation that brings the overscroll back to zero.
                flingAnimatable.snapTo(currentTouch)
                flingAnimatable.updateBounds(lowerBound = Float.NEGATIVE_INFINITY, upperBound = Float.POSITIVE_INFINITY)
                flingAnimatable.animateTo(
                    0.0f,
                    spring(currentSpringDamp, currentSpringStiff, visibilityThreshold),
                    initialVelocity
                ) {
                    currentTouch = value
                    offset = touchToDamped(currentTouch)
                }

                return available
            }
        }
    }

    this
        .clipToBounds()
        .nestedScroll(nestedConnection, dispatcher)
        .graphicsLayer {
            if (currentIsVertical) translationY = offset else translationX = offset
        }
}


/**
 * OverScrollState is used to control the overscroll effect.
 *
 * @param isOverScrollActive Whether the overscroll effect is active.
 */
class OverScrollState {
    var isOverScrollActive by mutableStateOf(false)
        internal set
}

/**
 * [LocalOverScrollState] is used to provide the [OverScrollState] instance to the composition.
 *
 * @see OverScrollState
 */
val LocalOverScrollState = compositionLocalOf { OverScrollState() }