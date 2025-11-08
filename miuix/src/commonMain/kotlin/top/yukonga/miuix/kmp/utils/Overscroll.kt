// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.LocalPullToRefreshState
import top.yukonga.miuix.kmp.basic.RefreshState
import kotlin.math.abs
import kotlin.math.sign

@Stable
internal val DefaultParabolaScrollEasing: (distance: Float, range: Int) -> Float = { distance, range ->
    val x = (abs(distance) / range).coerceIn(0.0f, 1.0f)
    val dampedFactor = x - x * x + (x * x * x / 3.0f)
    dampedFactor * range * sign(distance)
}

internal const val OutBoundSpringStiff = 280f
internal const val OutBoundSpringDamp = 1f


@Stable
@Composable
fun verticalOverscrollEffect(
    scrollEasing: ((distance: Float, range: Int) -> Float)? = null,
    springStiff: Float = OutBoundSpringStiff,
    springDamp: Float = OutBoundSpringDamp,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): OverscrollEffect? {
    return miuixOverscrollEffect(
        isVertical = true,
        scrollEasing = scrollEasing,
        springStiff = springStiff,
        springDamp = springDamp,
        isEnabled = isEnabled
    )
}

@Stable
@Composable
fun horizontalOverscrollEffect(
    scrollEasing: ((distance: Float, range: Int) -> Float)? = null,
    springStiff: Float = OutBoundSpringStiff,
    springDamp: Float = OutBoundSpringDamp,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): OverscrollEffect? {
    return miuixOverscrollEffect(
        isVertical = false,
        scrollEasing = scrollEasing,
        springStiff = springStiff,
        springDamp = springDamp,
        isEnabled = isEnabled
    )
}

@Stable
@Composable
fun miuixOverscrollEffect(
    isVertical: Boolean = true,
    scrollEasing: ((distance: Float, range: Int) -> Float)? = null,
    springStiff: Float = OutBoundSpringStiff,
    springDamp: Float = OutBoundSpringDamp,
    isEnabled: () -> Boolean = { platform() == Platform.Android || platform() == Platform.IOS }
): OverscrollEffect? {
    if (!isEnabled()) return null
    val pullToRefreshState = LocalPullToRefreshState.current
    val windowSize = getWindowSize()
    val range = if (isVertical) windowSize.height else windowSize.width
    val currentScrollEasing = rememberUpdatedState(scrollEasing ?: DefaultParabolaScrollEasing)
    val currentSpringStiff = rememberUpdatedState(springStiff)
    val currentSpringDamp = rememberUpdatedState(springDamp)
    val scope = rememberCoroutineScope()
    val overScrollState = LocalOverScrollState.current
    return remember(isVertical, range) {
        MiuixOverscrollEffect(
            isVertical = isVertical,
            range = range,
            scrollEasing = currentScrollEasing.value,
            springStiff = currentSpringStiff.value,
            springDamp = currentSpringDamp.value,
            scope = scope,
            pullToRefreshState = pullToRefreshState,
            overScrollState = overScrollState
        )
    }
}


/**
 * MiuixOverscrollEffect renders the current custom overscroll rebound as an [OverscrollEffect].
 * It offsets content along the selected axis using the same damping and spring as the previous
 * modifier-based implementation, but integrates with scrollable containers via `overscrollEffect`.
 */
class MiuixOverscrollEffect(
    private val isVertical: Boolean,
    private val range: Int,
    private val scrollEasing: (distance: Float, range: Int) -> Float,
    private val springStiff: Float,
    private val springDamp: Float,
    private val scope: CoroutineScope,
    private val pullToRefreshState: top.yukonga.miuix.kmp.basic.PullToRefreshState?,
    private val overScrollState: OverScrollState
) : OverscrollEffect {
    private val visibilityThreshold = 1f
    private var currentTouch by mutableStateOf(0f)
    private var offset by mutableStateOf(0f)
    private var lastFlingAnimator: Animatable<Float, AnimationVector1D>? = null

    override val isInProgress: Boolean
        get() = abs(offset) > visibilityThreshold

    private fun touchTo(value: Float) {
        val maxTouch = range.toFloat()
        currentTouch = value.coerceIn(-maxTouch, maxTouch)
        offset = scrollEasing(currentTouch, range)
    }

    private suspend fun snapToTouch(value: Float) {
        lastFlingAnimator?.let { if (it.isRunning) it.stop() }
        touchTo(value)
    }

    private fun startRelaxAnimation() {
        val existing = lastFlingAnimator
        if (existing != null && existing.isRunning) return
        val animator = Animatable(currentTouch)
        lastFlingAnimator = animator
        scope.launch {
            animator.animateTo(
                0.0f,
                spring(springDamp, springStiff, visibilityThreshold)
            ) {
                touchTo(value)
            }
        }
    }

    override fun applyToScroll(
        delta: Offset,
        source: NestedScrollSource,
        performScroll: (Offset) -> Offset,
    ): Offset {
        // If pull-to-refresh is active, don't accumulate overscroll; softly relax existing offset.
        if (pullToRefreshState != null && pullToRefreshState.refreshState != RefreshState.Idle) {
            if (isInProgress && source == NestedScrollSource.UserInput) {
                startRelaxAnimation()
            }
            val consumedByScroll = performScroll(delta)
            overScrollState.isOverScrollActive = isInProgress
            return consumedByScroll
        }
        val axisDelta = if (isVertical) delta.y else delta.x
        val sameDirection = sign(axisDelta) == sign(currentTouch)

        // Relax overscroll first when dragging in the opposite direction.
        val consumedByPreScroll = if (abs(currentTouch) > visibilityThreshold && !sameDirection && source == NestedScrollSource.UserInput) {
            val prev = currentTouch
            val newVal = prev + axisDelta
            if (sign(prev) != sign(newVal)) {
                scope.launch { snapToTouch(0f) }
                if (isVertical) Offset(0f, axisDelta + prev) else Offset(axisDelta + prev, 0f)
            } else {
                scope.launch { snapToTouch(newVal) }
                if (isVertical) Offset(0f, axisDelta) else Offset(axisDelta, 0f)
            }
        } else Offset.Zero

        val leftForScroll = delta - consumedByPreScroll
        val consumedByScroll = performScroll(leftForScroll)
        val overscrollDelta = leftForScroll - consumedByScroll
        val axisOverscroll = if (isVertical) overscrollDelta.y else overscrollDelta.x

        // For direct user input, accumulate leftover into overscroll.
        if (abs(axisOverscroll) > 0.5f && source == NestedScrollSource.UserInput) {
            scope.launch { snapToTouch(currentTouch + axisOverscroll) }
        }
        overScrollState.isOverScrollActive = isInProgress
        return consumedByPreScroll + consumedByScroll
    }

    override suspend fun applyToFling(
        velocity: Velocity,
        performFling: suspend (Velocity) -> Velocity,
    ) {
        lastFlingAnimator?.let { if (it.isRunning) it.stop() }
        val consumed = performFling(velocity)
        val remainingAxis = if (isVertical) velocity.y - consumed.y else velocity.x - consumed.x

        val animator = Animatable(currentTouch)
        lastFlingAnimator = animator
        animator.animateTo(
            0.0f,
            spring(springDamp, springStiff, visibilityThreshold),
            initialVelocity = remainingAxis
        ) {
            touchTo(value)
        }
    }

    override val node: DelegatableNode = object : Modifier.Node(), LayoutModifierNode {
        override fun MeasureScope.measure(
            measurable: Measurable,
            constraints: Constraints,
        ): MeasureResult {
            val placeable = measurable.measure(constraints)
            return layout(placeable.width, placeable.height) {
                val dx = if (isVertical) 0 else offset.toInt()
                val dy = if (isVertical) offset.toInt() else 0
                placeable.placeRelativeWithLayer(IntOffset(dx, dy).x, IntOffset(dx, dy).y)
            }
        }
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
