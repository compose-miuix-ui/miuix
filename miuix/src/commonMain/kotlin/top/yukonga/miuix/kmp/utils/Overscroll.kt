// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidatePlacement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.LocalPullToRefreshState
import top.yukonga.miuix.kmp.basic.PullToRefreshState
import top.yukonga.miuix.kmp.basic.RefreshState
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sign

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollVertical(
    nestedScrollToParent: Boolean = true,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS },
): Modifier = overScrollOutOfBound(
    isVertical = true,
    nestedScrollToParent = nestedScrollToParent,
    isEnabled = isEnabled,
)

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollHorizontal(
    nestedScrollToParent: Boolean = true,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS },
): Modifier = overScrollOutOfBound(
    isVertical = false,
    nestedScrollToParent = nestedScrollToParent,
    isEnabled = isEnabled,
)

/**
 * Overscroll effect when scrolling to the boundary.
 *
 * @param isVertical Whether the overscroll effect is vertical or horizontal.
 * @param nestedScrollToParent Whether to dispatch nested scroll events to parent.
 * @param isEnabled Whether the overscroll effect is enabled. Default is enabled on Android and iOS only.
 */
@Stable
fun Modifier.overScrollOutOfBound(
    isVertical: Boolean = true,
    nestedScrollToParent: Boolean = true,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS },
): Modifier {
    if (!isEnabled()) return this

    return this then OverscrollElement(
        isVertical = isVertical,
        nestedScrollToParent = nestedScrollToParent,
    )
}

private data class OverscrollElement(
    val isVertical: Boolean,
    val nestedScrollToParent: Boolean,
) : ModifierNodeElement<OverscrollNode>() {
    override fun create(): OverscrollNode = OverscrollNode(
        isVertical = isVertical,
        nestedScrollToParent = nestedScrollToParent,
    )

    override fun update(node: OverscrollNode) {
        node.update(
            isVertical = isVertical,
            nestedScrollToParent = nestedScrollToParent,
        )
        node.invalidatePlacement()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "overScrollOutOfBound"
        properties["isVertical"] = isVertical
        properties["nestedScrollToParent"] = nestedScrollToParent
    }
}

private class OverscrollNode(
    var isVertical: Boolean,
    var nestedScrollToParent: Boolean,
) : DelegatingNode(),
    CompositionLocalConsumerModifierNode,
    LayoutModifierNode,
    NestedScrollConnection {
    private val density: Density
        get() = currentValueOf(LocalDensity)

    private val windowInfo: WindowInfo
        get() = currentValueOf(LocalWindowInfo)

    private val overScrollState: OverScrollState
        get() = currentValueOf(LocalOverScrollState)

    private val pullToRefreshState: PullToRefreshState?
        get() = currentValueOf(LocalPullToRefreshState)

    private val dispatcher = NestedScrollDispatcher()
    private val springEngine = SpringEngine()
    private var animationJob: Job? = null
    private val offsetThreshold = 1f

    var offset by mutableFloatStateOf(0f)
        private set

    private var rawTouchAccumulation = 0f
    private var scrollRange: Float = 0f

    override fun onAttach() {
        super.onAttach()
        updateScrollRange()
        delegate(nestedScrollModifierNode(this, dispatcher))
    }

    fun update(
        isVertical: Boolean,
        nestedScrollToParent: Boolean,
    ) {
        var rangeChanged = false
        if (this.isVertical != isVertical) {
            rangeChanged = true
        }

        this.isVertical = isVertical
        this.nestedScrollToParent = nestedScrollToParent

        if (rangeChanged && isAttached) {
            updateScrollRange()
        }
    }

    private fun updateScrollRange() {
        scrollRange = with(density) {
            if (isVertical) {
                windowInfo.containerDpSize.height.toPx()
            } else {
                windowInfo.containerDpSize.width.toPx()
            }
        }
    }

    private fun resetState() {
        offset = 0f
        rawTouchAccumulation = 0f
        if (isAttached) {
            overScrollState.isOverScrollActive = false
        }
    }

    private fun startSpringAnimation(initialVelocity: Float = 0f) {
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

    private fun shouldBypassForPullToRefresh(): Boolean {
        // When pull-to-refresh is active (not Idle), always bypass.
        return pullToRefreshState != null && pullToRefreshState?.refreshState != RefreshState.Idle && isVertical
    }

    private fun applyDrag(delta: Float) {
        if (delta == 0f) return
        rawTouchAccumulation += delta
        rawTouchAccumulation = rawTouchAccumulation.coerceIn(-scrollRange, scrollRange)

        val normalized = min(abs(rawTouchAccumulation) / scrollRange, 1.0f)
        val dampedDist = SpringMath.obtainDampingDistance(normalized, scrollRange)
        offset = sign(rawTouchAccumulation) * dampedDist
    }

    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        updateScrollRange()
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            placeable.placeWithLayer(0, 0) {
                if (isVertical) {
                    translationY = offset
                } else {
                    translationX = offset
                }
                clip = true
            }
        }
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

        val parentConsumed = if (nestedScrollToParent) {
            dispatcher.dispatchPreScroll(available, source)
        } else {
            Offset.Zero
        }

        val realAvailable = available - parentConsumed
        val delta = if (isVertical) realAvailable.y else realAvailable.x

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

            return if (isVertical) {
                Offset(parentConsumed.x, actualConsumed + parentConsumed.y)
            } else {
                Offset(actualConsumed + parentConsumed.x, parentConsumed.y)
            }
        }

        applyDrag(delta)
        return if (isVertical) Offset(parentConsumed.x, available.y) else Offset(available.x, parentConsumed.y)
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

        val parentConsumed = if (nestedScrollToParent) {
            dispatcher.dispatchPostScroll(consumed, available, source)
        } else {
            Offset.Zero
        }

        val realAvailable = available - parentConsumed
        val delta = if (isVertical) realAvailable.y else realAvailable.x

        applyDrag(delta)
        return if (isVertical) Offset(parentConsumed.x, available.y) else Offset(available.x, parentConsumed.y)
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

        val parentConsumed = if (nestedScrollToParent) {
            dispatcher.dispatchPreFling(available)
        } else {
            Velocity.Zero
        }

        val realAvailable = available - parentConsumed
        val velocity = if (isVertical) realAvailable.y else realAvailable.x

        if (abs(offset) > offsetThreshold) {
            if (sign(velocity) != sign(offset)) {
                startSpringAnimation(velocity)
                // Optimize speed and feel to prevent violent throwing
                return parentConsumed + if (isVertical) {
                    Velocity(
                        0f,
                        realAvailable.y / 2.13333f,
                    )
                } else {
                    Velocity(realAvailable.x / 2.13333f, 0f)
                }
            } else {
                startSpringAnimation(velocity)
                return parentConsumed + if (isVertical) Velocity(0f, realAvailable.y) else Velocity(realAvailable.x, 0f)
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

        val parentConsumed = if (nestedScrollToParent) {
            dispatcher.dispatchPostFling(consumed, available)
        } else {
            Velocity.Zero
        }

        val realAvailable = available - parentConsumed
        val velocity = (if (isVertical) realAvailable.y else realAvailable.x) / 1.53333f // attenuation speed
        startSpringAnimation(velocity)

        return parentConsumed + if (isVertical) Velocity(0f, velocity) else Velocity(velocity, 0f)
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
