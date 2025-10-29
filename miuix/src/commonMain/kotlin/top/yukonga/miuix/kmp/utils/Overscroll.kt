// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring.StiffnessMediumLow
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Stable
private fun obtainScrollDistance(distance: Float, range: Int): Float {
    return obtainDampingDistance((abs(distance) / range).coerceIn(0.0f, 1.0f), range) * sign(distance)
}

@Stable
private fun obtainDampingDistance(normalizedValue: Float, range: Int): Float {
    val x = normalizedValue.toDouble().coerceIn(0.0, 1.0)
    val dampedFactor = x - x * x + (x * x * x / 3.0)
    return (dampedFactor * range).toFloat()
}

internal val DefaultParabolaScrollEasing: (distance: Float, isVertical: Boolean) -> Float
    @Composable
    get() {
        val windowSize by rememberUpdatedState(getWindowSize())
        return { distance, isVertical ->
            obtainScrollDistance(distance, if (isVertical) windowSize.height else windowSize.width)
        }
    }

internal const val OutBoundSpringStiff = 280f
internal const val OutBoundSpringDamp = 1f

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollVertical(
    nestedScrollToParent: Boolean = true,
    scrollEasing: ((distance: Float, isVertical: Boolean) -> Float)? = null,
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
    scrollEasing: ((distance: Float, isVertical: Boolean) -> Float)? = null,
    springStiff: Float = OutBoundSpringStiff,
    springDamp: Float = OutBoundSpringDamp,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): Modifier = overScrollOutOfBound(isVertical = false, nestedScrollToParent, scrollEasing, springStiff, springDamp, isEnabled)

/**
 * OverScroll effect for scrollable Composable.
 *
 * form https://github.com/Cormor/ComposeOverscroll
 * @Author: cormor
 * @Email: cangtiansuo@gmail.com
 * @param isVertical is vertical, or horizontal?
 * @param nestedScrollToParent Whether to dispatch nested scroll events to parent.
 * @param scrollEasing u can refer to [DefaultParabolaScrollEasing], The incoming values are the currently existing overscroll Offset
 * and the new offset from the gesture.
 * modify it to cooperate with [springStiff] to customize the sliding damping effect.
 * The current default easing comes from iOS, you don't need to modify it!
 * @param springStiff springStiff for overscroll effect，For better user experience, the new value is not recommended to be higher than [StiffnessMediumLow].
 * @param springDamp springDamp for overscroll effect，generally do not need to set.
 * @param isEnabled Whether to enable the overscroll effect, default is enabled on Android and iOS.
 */
@Stable
@Suppress("NAME_SHADOWING")
fun Modifier.overScrollOutOfBound(
    isVertical: Boolean = true,
    nestedScrollToParent: Boolean = true,
    scrollEasing: ((distance: Float, isVertical: Boolean) -> Float)?,
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
    val currentWindowSize by rememberUpdatedState(getWindowSize())
    val dispatcher = remember { NestedScrollDispatcher() }
    var offset by remember { mutableFloatStateOf(0f) }

    val nestedConnection = remember {
        object : NestedScrollConnection {
            /**
             * If the offset is less than this value, we consider the animation to end.
             */
            val visibilityThreshold = 1f
            var currentTouch by mutableStateOf(0f)
            lateinit var lastFlingAnimator: Animatable<Float, AnimationVector1D>

            private fun shouldBypassForPullToRefresh(availableY: Float): Boolean {
                return pullToRefreshState != null &&
                        pullToRefreshState.refreshState != RefreshState.Idle &&
                        currentIsVertical &&
                        availableY > 0
            }

            private fun touchToDamped(distance: Float): Float {
                return currentScrollEasing(distance, currentIsVertical)
            }

            private fun addTouchDelta(deltaTouch: Float): Float {
                val maxTouch = (if (currentIsVertical) currentWindowSize.height else currentWindowSize.width).toFloat()
                val target = currentTouch + deltaTouch
                val overflow =
                    when {
                        target > maxTouch -> target - maxTouch
                        target < -maxTouch -> target + maxTouch
                        else -> 0f
                    }
                currentTouch = target.coerceIn(-maxTouch, maxTouch)
                return overflow
            }

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // println("Overscroll !!!onPreScroll!!! available >> $available source >> $source")
                // Check if overScroll should be disabled for drop-down direction
                overScrollState.isOverScrollActive = abs(offset) > visibilityThreshold
                if (shouldBypassForPullToRefresh(available.y)) {
                    return dispatcher.dispatchPreScroll(available, source)
                }
                // Found fling behavior in the wrong direction.
                if (source != NestedScrollSource.UserInput) {
                    return dispatcher.dispatchPreScroll(available, source)
                }
                if (::lastFlingAnimator.isInitialized && lastFlingAnimator.isRunning) {
                    dispatcher.coroutineScope.launch {
                        lastFlingAnimator.stop()
                    }
                }
                val realAvailable = when {
                    currentNestedScrollToParent -> available - dispatcher.dispatchPreScroll(available, source)
                    else -> available
                }
                val realOffset = if (currentIsVertical) realAvailable.y else realAvailable.x
                val isSameDirection = sign(realOffset) == sign(currentTouch)
                if (abs(currentTouch) <= visibilityThreshold || isSameDirection) {
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
                // println("Overscroll !!!onPostScroll!!! consumed >> $consumed available >> $available source >>> $source")
                // Check if overScroll should be disabled for drop-down direction
                overScrollState.isOverScrollActive = abs(offset) > visibilityThreshold
                if (shouldBypassForPullToRefresh(available.y)) {
                    return dispatcher.dispatchPostScroll(consumed, available, source)
                }
                // Found fling behavior in the wrong direction.
                if (source != NestedScrollSource.UserInput) {
                    return dispatcher.dispatchPostScroll(consumed, available, source)
                }
                if (::lastFlingAnimator.isInitialized && lastFlingAnimator.isRunning) {
                    dispatcher.coroutineScope.launch {
                        lastFlingAnimator.stop()
                    }
                }
                val realAvailable = when {
                    currentNestedScrollToParent -> available - dispatcher.dispatchPostScroll(consumed, available, source)
                    else -> available
                }
                val realOffset = if (currentIsVertical) realAvailable.y else realAvailable.x
                val overflow = addTouchDelta(realOffset)
                offset = touchToDamped(currentTouch)
                return if (currentIsVertical) {
                    Offset(available.x - realAvailable.x, available.y - overflow)
                } else {
                    Offset(available.x - overflow, available.y - realAvailable.y)
                }
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                // println("Overscroll !!!onPreFling!!! available >> $available")
                // Check if overScroll should be disabled for drop-down direction
                overScrollState.isOverScrollActive = abs(offset) > visibilityThreshold
                if (shouldBypassForPullToRefresh(available.y)) {
                    return dispatcher.dispatchPreFling(available)
                }
                if (::lastFlingAnimator.isInitialized && lastFlingAnimator.isRunning) {
                    lastFlingAnimator.stop()
                }
                val parentConsumed = when {
                    currentNestedScrollToParent -> dispatcher.dispatchPreFling(available)
                    else -> Velocity.Zero
                }
                val realAvailable = available - parentConsumed
                var realVelocity = if (currentIsVertical) realAvailable.y else realAvailable.x

                if (abs(currentTouch) >= visibilityThreshold && sign(realVelocity) != sign(currentTouch)) {
                    lastFlingAnimator = Animatable(currentTouch).apply {
                        when {
                            realVelocity < 0 -> updateBounds(lowerBound = 0f)
                            realVelocity > 0 -> updateBounds(upperBound = 0f)
                        }
                    }
                    realVelocity = lastFlingAnimator.animateTo(
                        0.0f,
                        spring(currentSpringDamp, currentSpringStiff, visibilityThreshold),
                        realVelocity
                    ) {
                        currentTouch = value
                        offset = touchToDamped(currentTouch)
                    }.endState.velocity
                }

                return if (currentIsVertical) {
                    Velocity(parentConsumed.x, y = available.y - realVelocity)
                } else {
                    Velocity(x = available.x - realVelocity, y = parentConsumed.y)
                }
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                // println("Overscroll !!!onPostFling!!! consumed >> $consumed available >> $available")
                // Check if overScroll should be disabled for drop-down direction
                overScrollState.isOverScrollActive = abs(offset) > visibilityThreshold
                if (shouldBypassForPullToRefresh(available.y)) {
                    return dispatcher.dispatchPostFling(consumed, available)
                }
                if (::lastFlingAnimator.isInitialized && lastFlingAnimator.isRunning) {
                    lastFlingAnimator.stop()
                }
                val realAvailable = when {
                    currentNestedScrollToParent -> available - dispatcher.dispatchPostFling(consumed, available)
                    else -> available
                }
                val initialVelocity = if (currentIsVertical) realAvailable.y else realAvailable.x

                lastFlingAnimator = Animatable(currentTouch)
                lastFlingAnimator.animateTo(
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
@Stable
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
