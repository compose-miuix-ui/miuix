// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.OverscrollFactory
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidatePlacement
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.CoroutineScope
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
 * A Miuix implementation of [OverscrollFactory] that creates [OverscrollEffect] instances
 * with the same spring-based overscroll physics as [overScrollVertical] and [overScrollHorizontal].
 *
 * Provide this as [androidx.compose.foundation.LocalOverscrollFactory] in
 * [top.yukonga.miuix.kmp.theme.MiuixTheme] to automatically apply the Miuix overscroll
 * effect to all Compose scrollable components.
 */
object MiuixOverscrollFactory : OverscrollFactory {
    override fun createOverscrollEffect(): OverscrollEffect = MiuixOverscrollEffect()

    override fun equals(other: Any?): Boolean = this === other

    override fun hashCode(): Int = 1
}

/**
 * A Miuix [OverscrollEffect] implementing spring-based overscroll physics identical to
 * [overScrollVertical] and [overScrollHorizontal].
 *
 * Uses the same damping formula, spring parameters, and PullToRefresh coordination as the
 * modifier-based implementation. Both X and Y axes are handled independently.
 */
class MiuixOverscrollEffect : OverscrollEffect {
    private val offsetThreshold = 1f

    internal var offsetX = 0f
        private set(value) {
            if (field != value) {
                field = value
                invalidateNodePlacement?.invoke()
            }
        }

    internal var offsetY = 0f
        private set(value) {
            if (field != value) {
                field = value
                invalidateNodePlacement?.invoke()
            }
        }

    // Injected by MiuixOverscrollEffectNode when attached
    internal var invalidateNodePlacement: (() -> Unit)? = null

    private var rawTouchAccumulationX = 0f
    private var rawTouchAccumulationY = 0f

    internal var scrollRangeV = 0f
    internal var scrollRangeH = 0f

    private val springEngineX = SpringEngine()
    private val springEngineY = SpringEngine()
    private var animationJobX: Job? = null
    private var animationJobY: Job? = null

    // Injected by MiuixOverscrollEffectNode when attached
    internal var launchAnimation: ((suspend CoroutineScope.() -> Unit) -> Job)? = null
    internal var getPullToRefreshState: (() -> PullToRefreshState?)? = null
    internal var getOverScrollState: (() -> OverScrollState?)? = null

    override val isInProgress: Boolean
        get() = abs(offsetX) > offsetThreshold || abs(offsetY) > offsetThreshold

    override val node: DelegatableNode = MiuixOverscrollEffectNode(this)

    private fun shouldBypassForPullToRefreshY(): Boolean {
        val pts = getPullToRefreshState?.invoke() ?: return false
        return pts.refreshState != RefreshState.Idle
    }

    internal fun resetAll() {
        offsetX = 0f
        offsetY = 0f
        rawTouchAccumulationX = 0f
        rawTouchAccumulationY = 0f
        getOverScrollState?.invoke()?.let { state ->
            if (state.isOverScrollActive) state.isOverScrollActive = false
        }
    }

    private fun resetStateX() {
        offsetX = 0f
        rawTouchAccumulationX = 0f
    }

    private fun resetStateY() {
        offsetY = 0f
        rawTouchAccumulationY = 0f
    }

    private fun applyDragX(delta: Float) {
        if (delta == 0f || scrollRangeH == 0f) return
        rawTouchAccumulationX += delta
        rawTouchAccumulationX = rawTouchAccumulationX.coerceIn(-scrollRangeH, scrollRangeH)
        val normalized = min(abs(rawTouchAccumulationX) / scrollRangeH, 1.0f)
        offsetX = sign(rawTouchAccumulationX) * SpringMath.obtainDampingDistance(normalized, scrollRangeH)
    }

    private fun applyDragY(delta: Float) {
        if (delta == 0f || scrollRangeV == 0f) return
        rawTouchAccumulationY += delta
        rawTouchAccumulationY = rawTouchAccumulationY.coerceIn(-scrollRangeV, scrollRangeV)
        val normalized = min(abs(rawTouchAccumulationY) / scrollRangeV, 1.0f)
        offsetY = sign(rawTouchAccumulationY) * SpringMath.obtainDampingDistance(normalized, scrollRangeV)
    }

    private fun startSpringAnimationX(initialVelocity: Float = 0f) {
        if (abs(offsetX) <= offsetThreshold && initialVelocity == 0f) {
            resetStateX()
            return
        }
        animationJobX?.cancel()
        animationJobX = launchAnimation?.invoke {
            springEngineX.start(startValue = offsetX, targetValue = 0f, initialVel = initialVelocity)
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
                        val finished = springEngineX.step(dt)
                        offsetX = springEngineX.currentPos.toFloat()
                        rawTouchAccumulationX = sign(offsetX) * SpringMath.obtainTouchDistance(offsetX, scrollRangeH)
                        finished
                    }
                }
            } finally {
                if (abs(offsetX) <= offsetThreshold) resetStateX()
            }
        }
    }

    private fun startSpringAnimationY(initialVelocity: Float = 0f) {
        if (abs(offsetY) <= offsetThreshold && initialVelocity == 0f) {
            resetStateY()
            return
        }
        animationJobY?.cancel()
        animationJobY = launchAnimation?.invoke {
            springEngineY.start(startValue = offsetY, targetValue = 0f, initialVel = initialVelocity)
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
                        val finished = springEngineY.step(dt)
                        offsetY = springEngineY.currentPos.toFloat()
                        rawTouchAccumulationY = sign(offsetY) * SpringMath.obtainTouchDistance(offsetY, scrollRangeV)
                        finished
                    }
                }
            } finally {
                if (abs(offsetY) <= offsetThreshold) resetStateY()
            }
        }
    }

    private fun updateOverScrollState() {
        val state = getOverScrollState?.invoke() ?: return
        val isActive = isInProgress
        if (state.isOverScrollActive != isActive) {
            state.isOverScrollActive = isActive
        }
    }

    override fun applyToScroll(
        delta: Offset,
        source: NestedScrollSource,
        performScroll: (Offset) -> Offset,
    ): Offset {
        if (source != NestedScrollSource.UserInput) {
            val consumed = performScroll(delta)
            updateOverScrollState()
            return consumed
        }

        val bypassY = shouldBypassForPullToRefreshY()

        // Cancel running animations for non-bypassed axes
        if (!bypassY) animationJobY?.cancel()
        animationJobX?.cancel()

        // Y-axis pre-scroll: consume from overscroll first when scrolling back toward center
        var performScrollDeltaY = delta.y
        var extraConsumedY = 0f
        if (!bypassY && abs(offsetY) > offsetThreshold && delta.y != 0f && sign(delta.y) != sign(rawTouchAccumulationY)) {
            val canConsumeY = if (abs(rawTouchAccumulationY) <= abs(delta.y)) {
                -rawTouchAccumulationY
            } else {
                delta.y
            }
            if (abs(rawTouchAccumulationY) <= abs(delta.y)) {
                resetStateY()
                performScrollDeltaY = delta.y - canConsumeY
                extraConsumedY = canConsumeY
            } else {
                applyDragY(canConsumeY)
                performScrollDeltaY = 0f
                extraConsumedY = delta.y
            }
        }

        // X-axis pre-scroll: consume from overscroll first when scrolling back toward center
        var performScrollDeltaX = delta.x
        var extraConsumedX = 0f
        if (abs(offsetX) > offsetThreshold && delta.x != 0f && sign(delta.x) != sign(rawTouchAccumulationX)) {
            val canConsumeX = if (abs(rawTouchAccumulationX) <= abs(delta.x)) {
                -rawTouchAccumulationX
            } else {
                delta.x
            }
            if (abs(rawTouchAccumulationX) <= abs(delta.x)) {
                resetStateX()
                performScrollDeltaX = delta.x - canConsumeX
                extraConsumedX = canConsumeX
            } else {
                applyDragX(canConsumeX)
                performScrollDeltaX = 0f
                extraConsumedX = delta.x
            }
        }

        // Post-scroll: call performScroll, apply any unconsumed remainder to overscroll
        val adjustedDelta = Offset(performScrollDeltaX, performScrollDeltaY)
        val scrollConsumed = performScroll(adjustedDelta)
        val scrollRemaining = adjustedDelta - scrollConsumed

        if (scrollRemaining.y != 0f && !bypassY) applyDragY(scrollRemaining.y)
        if (scrollRemaining.x != 0f) applyDragX(scrollRemaining.x)

        updateOverScrollState()

        // Total consumed = overscroll reduction + scroll consumed + overscroll-absorbed remainder
        return Offset(
            x = extraConsumedX + scrollConsumed.x + (if (scrollRemaining.x != 0f) scrollRemaining.x else 0f),
            y = extraConsumedY + scrollConsumed.y + (if (scrollRemaining.y != 0f && !bypassY) scrollRemaining.y else 0f),
        )
    }

    override suspend fun applyToFling(
        velocity: Velocity,
        performFling: suspend (Velocity) -> Velocity,
    ) {
        val isActiveY = abs(offsetY) > offsetThreshold
        val isActiveX = abs(offsetX) > offsetThreshold
        val bypassY = shouldBypassForPullToRefreshY() && !isActiveY

        animationJobY?.cancel()
        animationJobX?.cancel()

        var performVelocity = velocity

        // Y-axis: when overscrolled, spring absorbs velocity before scroll
        if (!bypassY && isActiveY && velocity.y != 0f) {
            if (sign(velocity.y) == sign(offsetY)) {
                // Same direction fling: spring absorbs all velocity, scroll gets nothing
                startSpringAnimationY(velocity.y)
                performVelocity = Velocity(performVelocity.x, 0f)
            } else {
                // Opposite direction fling: spring starts with full velocity, scroll gets attenuated
                startSpringAnimationY(velocity.y)
                performVelocity = Velocity(performVelocity.x, velocity.y / 2.13333f)
            }
        }

        // X-axis: same logic
        if (isActiveX && velocity.x != 0f) {
            if (sign(velocity.x) == sign(offsetX)) {
                startSpringAnimationX(velocity.x)
                performVelocity = Velocity(0f, performVelocity.y)
            } else {
                startSpringAnimationX(velocity.x)
                performVelocity = Velocity(velocity.x / 2.13333f, performVelocity.y)
            }
        }

        val consumed = performFling(performVelocity)
        val remaining = performVelocity - consumed

        // Post-fling: always restart spring with attenuated remaining velocity (mirrors onPostFling)
        if (!bypassY) {
            startSpringAnimationY(remaining.y / 1.53333f)
        }
        startSpringAnimationX(remaining.x / 1.53333f)

        updateOverScrollState()
    }
}

private class MiuixOverscrollEffectNode(
    val effect: MiuixOverscrollEffect,
) : Modifier.Node(),
    CompositionLocalConsumerModifierNode,
    LayoutModifierNode {

    override fun onAttach() {
        super.onAttach()
        updateScrollRange()
        effect.launchAnimation = { block -> coroutineScope.launch(block = block) }
        effect.getPullToRefreshState = { currentValueOf(LocalPullToRefreshState) }
        effect.getOverScrollState = { currentValueOf(LocalOverScrollState) }
        effect.invalidateNodePlacement = { invalidatePlacement() }
    }

    override fun onDetach() {
        super.onDetach()
        effect.launchAnimation = null
        effect.getPullToRefreshState = null
        effect.getOverScrollState = null
        effect.invalidateNodePlacement = null
        effect.resetAll()
    }

    private fun updateScrollRange() {
        val density = currentValueOf(LocalDensity)
        val windowInfo = currentValueOf(LocalWindowInfo)
        with(density) {
            effect.scrollRangeV = windowInfo.containerDpSize.height.toPx()
            effect.scrollRangeH = windowInfo.containerDpSize.width.toPx()
        }
    }

    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        updateScrollRange()
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            placeable.placeWithLayer(0, 0) {
                translationX = effect.offsetX
                translationY = effect.offsetY
                clip = true
            }
        }
    }
}
