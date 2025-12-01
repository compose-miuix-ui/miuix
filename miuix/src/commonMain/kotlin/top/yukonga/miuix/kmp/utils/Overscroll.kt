// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.LocalPullToRefreshState
import top.yukonga.miuix.kmp.basic.RefreshState
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sign

private object SpringMath {
    const val MAX_FRAME_DELTA_SECONDS = 0.016f
    const val MIN_FRAME_DELTA_SECONDS = 0.001f
    const val HIGH_VELOCITY_THRESHOLD = 5000.0
    const val CRITICAL_DAMPING_RATIO = 1.0f
    const val STANDARD_SPRING_PERIOD = 0.4f
    const val SLOWER_SPRING_PERIOD_FOR_HIGH_VELOCITY = 0.55f

    /**
     * Obtain the movable distance after converting damping
     *
     * Damping formula: x - x^2 + x^3/3
     * */
    fun obtainDampingDistance(normalizedInput: Float, range: Float): Float {
        val x = max(0.0f, min(normalizedInput, 1.0f)).toDouble()
        val dampedFactor = x - x.pow(2.0) + (x.pow(3.0) / 3.0)
        return (dampedFactor * range).toFloat()
    }

    /**
     * Retrieve the damping distance and restore it to the original movement distance
     *
     * Restore formula: range - (range^(2/3)) * (range - 3 * absPixelOffset)^(1/3)
     * */
    fun obtainTouchDistance(currentPixelOffset: Float, range: Float): Float {
        val absPixelOffset = abs(currentPixelOffset)
        val absMaxOffset = abs(obtainDampingDistance(1.0f, range))

        if (absPixelOffset >= absMaxOffset) return absMaxOffset
        if (absPixelOffset <= 0f) return 0f

        val part2 = range.toDouble().pow(2.0 / 3.0) * (range - (3.0 * absPixelOffset)).pow(1.0 / 3.0)
        return (range - part2).toFloat()
    }
}

private class SpringOperator(dampingRatio: Float, naturalPeriod: Float) {
    private val dampingCoefficient: Double
    private val stiffnessOverMass: Double

    init {
        val angularFrequency = (2.0 * PI) / naturalPeriod
        stiffnessOverMass = angularFrequency * angularFrequency // k/m = ω^2
        dampingCoefficient = 2.0 * dampingRatio * angularFrequency // c/m = 2 * ζ * ω
    }

    /**
     * Calculate the new velocity using Euler's formula
     * */
    fun updateVelocity(
        currentVelocity: Double,
        deltaTime: Float,
        currentPosition: Double,
        targetPosition: Double
    ): Double {
        val velocityDecayFactor = 1.0 - dampingCoefficient * deltaTime
        val velocityIncreaseFromSpring = stiffnessOverMass * (targetPosition - currentPosition) * deltaTime
        return currentVelocity * velocityDecayFactor + velocityIncreaseFromSpring
    }
}

private class SpringEngine {
    private var springOperator: SpringOperator? = null
    var velocity: Double = 0.0
    var currentPos: Double = 0.0
    private var targetPos: Double = 0.0
    private var initialPos: Double = 0.0
    private var initialVelocity: Double = 0.0

    private fun isAtEquilibrium(initial: Double, current: Double): Boolean {
        if (sign(initial) < 0 && current > 0) return true // transcend
        if (sign(initial) > 0 && current < 0) return true // transcend
        return false
    }

    fun start(startValue: Float, targetValue: Float, initialVel: Float) {
        currentPos = startValue.toDouble()
        initialPos = startValue.toDouble()
        targetPos = targetValue.toDouble()
        velocity = initialVel.toDouble()
        initialVelocity = velocity

        springOperator = SpringOperator(
            SpringMath.CRITICAL_DAMPING_RATIO,
            if (abs(initialVel) > SpringMath.HIGH_VELOCITY_THRESHOLD) {
                SpringMath.SLOWER_SPRING_PERIOD_FOR_HIGH_VELOCITY
            } else {
                SpringMath.STANDARD_SPRING_PERIOD
            }
        )
    }

    fun step(deltaTime: Float): Boolean {
        val operator = springOperator ?: return false
        val dt = deltaTime.coerceIn(SpringMath.MIN_FRAME_DELTA_SECONDS, SpringMath.MAX_FRAME_DELTA_SECONDS)
        velocity = operator.updateVelocity(velocity, dt, currentPos, targetPos)
        currentPos += dt * velocity

        if (isAtEquilibrium(initialPos, currentPos)) {
            currentPos = targetPos
            return true
        }

        return false
    }
}

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollVertical(
    nestedScrollToParent: Boolean = true,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): Modifier = overScrollOutOfBound(
    isVertical = true,
    nestedScrollToParent = nestedScrollToParent,
    isEnabled = isEnabled
)

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollHorizontal(
    nestedScrollToParent: Boolean = true,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): Modifier = overScrollOutOfBound(
    isVertical = false,
    nestedScrollToParent = nestedScrollToParent,
    isEnabled = isEnabled
)

/**
 * Overscroll effect when scrolling to the boundary.
 *
 * @param isVertical Whether the overscroll effect is vertical or horizontal.
 * @param nestedScrollToParent Whether to dispatch nested scroll events to parent.
 * @param isEnabled Whether the overscroll effect is enabled. Default is enabled on Android and iOS only.
 */
@Stable
@Suppress("NAME_SHADOWING")
fun Modifier.overScrollOutOfBound(
    isVertical: Boolean = true,
    nestedScrollToParent: Boolean = true,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): Modifier = composed {
    if (!isEnabled()) return@composed this

    val offsetThreshold = 1f

    val overScrollState = LocalOverScrollState.current
    val pullToRefreshState = LocalPullToRefreshState.current
    val currentNestedScrollToParent by rememberUpdatedState(nestedScrollToParent)
    val currentIsVertical by rememberUpdatedState(isVertical)

    val windowSize = getWindowSize()
    val scrollRange = with(LocalDensity.current) {
        (if (isVertical) windowSize.height.toDp() else windowSize.width.toDp()).toPx()
    }

    val dispatcher = remember { NestedScrollDispatcher() }
    val coroutineScope = rememberCoroutineScope()

    var offset by remember { mutableFloatStateOf(0f) }
    var rawTouchAccumulation by remember { mutableFloatStateOf(0f) }

    val springEngine = remember { SpringEngine() }
    var animationJob by remember { mutableStateOf<Job?>(null) }

    fun resetState() {
        offset = 0f
        rawTouchAccumulation = 0f
        overScrollState.isOverScrollActive = false
    }

    fun startSpringAnimation(initialVelocity: Float = 0f) {
        if (abs(offset) <= offsetThreshold && initialVelocity == 0f) {
            resetState()
            return
        }

        animationJob?.cancel()
        animationJob = coroutineScope.launch {
            springEngine.start(
                startValue = offset,
                targetValue = 0.0f,
                initialVel = initialVelocity,
            )

            var lastFrameTimeNanos = -1L
            var isFinished = false

            try {
                while (!isFinished && isActive) {
                    isFinished = withFrameNanos { frameTimeNanos ->
                        if (lastFrameTimeNanos == -1L) {
                            lastFrameTimeNanos = frameTimeNanos
                            return@withFrameNanos false
                        }
                        val dt = (frameTimeNanos - lastFrameTimeNanos) / 1_000_000_000f
                        lastFrameTimeNanos = frameTimeNanos

                        val finished = springEngine.step(dt)

                        offset = springEngine.currentPos.toFloat()
                        rawTouchAccumulation = sign(offset) * SpringMath.obtainTouchDistance(offset, scrollRange)

                        finished
                    }
                }
            } finally {
                if (abs(offset) <= offsetThreshold) {
                    resetState()
                }
            }
        }
    }

    val nestedConnection = remember(scrollRange) {
        object : NestedScrollConnection {

            private fun shouldBypassForPullToRefresh(): Boolean {
                // When pull-to-refresh is active (not Idle), always bypass.
                return pullToRefreshState != null && pullToRefreshState.refreshState != RefreshState.Idle && currentIsVertical
            }

            private fun applyDrag(delta: Float) {
                if (delta == 0f) return
                rawTouchAccumulation += delta
                rawTouchAccumulation = rawTouchAccumulation.coerceIn(-scrollRange, scrollRange)

                val normalized = min(abs(rawTouchAccumulation) / scrollRange, 1.0f)
                val dampedDist = SpringMath.obtainDampingDistance(normalized, scrollRange)
                offset = sign(rawTouchAccumulation) * dampedDist
            }

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val isActive = abs(offset) > offsetThreshold
                if (overScrollState.isOverScrollActive != isActive) {
                    overScrollState.isOverScrollActive = isActive
                }

                if (shouldBypassForPullToRefresh() || source != NestedScrollSource.UserInput) {
                    return dispatcher.dispatchPreScroll(available, source)
                }

                animationJob?.cancel()

                val parentConsumed = if (currentNestedScrollToParent) {
                    dispatcher.dispatchPreScroll(available, source)
                } else Offset.Zero

                val realAvailable = available - parentConsumed
                val delta = if (currentIsVertical) realAvailable.y else realAvailable.x

                if (abs(offset) <= offsetThreshold || sign(delta) == sign(rawTouchAccumulation)) {
                    return parentConsumed
                }

                if (sign(delta) != sign(rawTouchAccumulation)) { // opposite direction
                    val actualConsumed = if (abs(rawTouchAccumulation) <= abs(delta)) {
                        -rawTouchAccumulation // can be fully consumed
                    } else {
                        delta
                    }

                    if (abs(rawTouchAccumulation) <= abs(delta)) {
                        resetState() // reset directly after complete consumption
                    } else {
                        applyDrag(actualConsumed)
                    }

                    return if (currentIsVertical) Offset(0f, actualConsumed) else Offset(actualConsumed, 0f)
                }

                applyDrag(delta)
                return if (currentIsVertical) Offset(0f, realAvailable.y) else Offset(realAvailable.x, 0f)
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                val isActive = abs(offset) > offsetThreshold
                if (overScrollState.isOverScrollActive != isActive) {
                    overScrollState.isOverScrollActive = isActive
                }

                if (shouldBypassForPullToRefresh() || source != NestedScrollSource.UserInput) {
                    return dispatcher.dispatchPostScroll(consumed, available, source)
                }

                animationJob?.cancel()

                val parentConsumed = if (currentNestedScrollToParent) {
                    dispatcher.dispatchPostScroll(consumed, available, source)
                } else Offset.Zero

                val realAvailable = available - parentConsumed
                val delta = if (currentIsVertical) realAvailable.y else realAvailable.x

                applyDrag(delta)
                return realAvailable
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                val isActive = abs(offset) > offsetThreshold
                if (overScrollState.isOverScrollActive != isActive) {
                    overScrollState.isOverScrollActive = isActive
                }

                if (shouldBypassForPullToRefresh() && !overScrollState.isOverScrollActive) {
                    return dispatcher.dispatchPreFling(available)
                }

                animationJob?.cancel()

                val parentConsumed = if (currentNestedScrollToParent) {
                    dispatcher.dispatchPreFling(available)
                } else Velocity.Zero

                val realAvailable = available - parentConsumed
                val velocity = if (currentIsVertical) realAvailable.y else realAvailable.x

                if (abs(offset) > offsetThreshold) {
                    if (sign(velocity) != sign(offset)) {
                        startSpringAnimation(velocity)
                        // Optimize speed and feel to prevent violent throwing
                        return if (currentIsVertical) Velocity(0f, realAvailable.y / 2.13333f) else Velocity(realAvailable.x / 2.13333f, 0f)
                    } else {
                        startSpringAnimation(velocity)
                        return if (currentIsVertical) Velocity(0f, realAvailable.y) else Velocity(realAvailable.x, 0f)
                    }
                }

                return parentConsumed
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                val isActive = abs(offset) > offsetThreshold
                if (overScrollState.isOverScrollActive != isActive) {
                    overScrollState.isOverScrollActive = isActive
                }

                if (shouldBypassForPullToRefresh() && !overScrollState.isOverScrollActive) {
                    return dispatcher.dispatchPostFling(consumed, available)
                }

                animationJob?.cancel()

                val parentConsumed = if (currentNestedScrollToParent) {
                    dispatcher.dispatchPostFling(consumed, available)
                } else Velocity.Zero

                val realAvailable = available - parentConsumed
                val velocity = (if (currentIsVertical) realAvailable.y else realAvailable.x) / 1.53333f // attenuation speed
                startSpringAnimation(velocity)

                return if (currentIsVertical) Velocity(0f, realAvailable.y - velocity) else Velocity(realAvailable.x - velocity, 0f)
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