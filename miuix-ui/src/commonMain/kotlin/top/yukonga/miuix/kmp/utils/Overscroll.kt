// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode
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
import androidx.compose.ui.util.fastAny
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.LocalPullToRefreshState
import top.yukonga.miuix.kmp.basic.PullToRefreshState
import top.yukonga.miuix.kmp.basic.RefreshState
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.round
import kotlin.math.sign

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollVertical(
    nestedScrollToParent: Boolean = true,
    isEnabled: () -> Boolean = { true },
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
    isEnabled: () -> Boolean = { true },
): Modifier = overScrollOutOfBound(
    isVertical = false,
    nestedScrollToParent = nestedScrollToParent,
    isEnabled = isEnabled,
)

/**
 * Overscroll effect when scrolling to the boundary.
 *
 * The effect engages only during a press or pan gesture session (touch drag; trackpad pan on
 * Android). Mouse wheel and keyboard scrolling pass through untouched, as does desktop/macOS
 * trackpad scrolling (delivered as wheel events).
 *
 * @param isVertical Whether the overscroll effect is vertical or horizontal.
 * @param nestedScrollToParent Whether to dispatch nested scroll events to parent. Pass-through
 * deltas (non-gesture sources such as mouse wheel, and while pull-to-refresh is active) are
 * forwarded to ancestors regardless of this flag.
 * @param isEnabled Whether the overscroll effect is enabled.
 */
@Stable
fun Modifier.overScrollOutOfBound(
    isVertical: Boolean = true,
    nestedScrollToParent: Boolean = true,
    isEnabled: () -> Boolean = { true },
): Modifier {
    if (!isEnabled()) return this

    return this
        .clipToBounds()
        .then(
            OverscrollElement(
                isVertical = isVertical,
                nestedScrollToParent = nestedScrollToParent,
            ),
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

    // Drag accumulation engages only inside a press/pan session. Scroll events never alter it
    // (with the default FlingBehavior a wheel produces no fling callbacks, so a wheel-driven
    // offset would latch unsettled); mouse presses never open it (a mouse press cannot drag a
    // scrollable).
    private var gestureActive = false

    init {
        delegate(
            SuspendingPointerInputModifierNode {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        val active = when (event.type) {
                            PointerEventType.PanStart, PointerEventType.PanMove -> true
                            PointerEventType.PanEnd -> false
                            PointerEventType.Scroll -> gestureActive
                            else -> event.changes.fastAny { it.pressed && it.type != PointerType.Mouse }
                        }
                        if (gestureActive && !active && animationJob?.isActive != true && abs(offset) > offsetThreshold) {
                            // Settle a session that ends without a fling; a real gesture's fling
                            // supersedes this spring via onPreFling.
                            startSpringAnimation()
                        }
                        gestureActive = active
                    }
                }
            },
        )
    }

    private var lastPlacedOffset = 0f
    var offset = 0f
        private set(value) {
            if (field != value) {
                field = value
                // Placement pixel-snaps via round(), so only re-place when the whole-pixel value changes.
                val rounded = round(value)
                if (rounded != lastPlacedOffset) {
                    lastPlacedOffset = rounded
                    if (isAttached) invalidatePlacement()
                }
            }
        }

    private var rawTouchAccumulation = 0f
    private var scrollRange: Float = 0f
    private var cachedScrollRangeDensity: Density? = null
    private var cachedScrollRangeWindowInfo: WindowInfo? = null

    override fun onAttach() {
        super.onAttach()
        updateScrollRange()
        delegate(nestedScrollModifierNode(this, dispatcher))
    }

    override fun onDetach() {
        super.onDetach()
        gestureActive = false
        resetState()
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
        val currentDensity = density
        val currentWindowInfo = windowInfo
        if (currentDensity == cachedScrollRangeDensity && currentWindowInfo == cachedScrollRangeWindowInfo) return
        cachedScrollRangeDensity = currentDensity
        cachedScrollRangeWindowInfo = currentWindowInfo
        scrollRange = with(currentDensity) {
            if (isVertical) {
                currentWindowInfo.containerDpSize.height.toPx()
            } else {
                currentWindowInfo.containerDpSize.width.toPx()
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
            springEngine.runSettleAnimation(
                startValue = offset,
                initialVelocity = initialVelocity,
                onFrame = { currentPos ->
                    offset = currentPos
                },
                onSettle = {
                    if (abs(offset) <= offsetThreshold) resetState()
                },
            )
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

    /** Inverse of the damping curve: re-derive [rawTouchAccumulation] from [offset] when a drag takes over a spring. */
    private fun syncRawAccumulationFromOffset() {
        rawTouchAccumulation = sign(offset) * SpringMath.obtainTouchDistance(offset, scrollRange)
    }

    /** Reclaims a stale offset once the child can scroll again in the accumulated direction (e.g. pagination); otherwise [onPreFling] swallows the next fling. */
    private fun unwindStaleOffset(consumedDelta: Float) {
        if (abs(offset) <= offsetThreshold || consumedDelta == 0f) return
        if (rawTouchAccumulation == 0f) syncRawAccumulationFromOffset()
        if (sign(consumedDelta) != sign(rawTouchAccumulation)) return
        if (abs(rawTouchAccumulation) <= abs(consumedDelta)) {
            resetState()
        } else {
            applyDrag(-consumedDelta)
        }
    }

    override fun MeasureScope.measure(measurable: Measurable, constraints: Constraints): MeasureResult {
        updateScrollRange()
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            placeable.placeWithLayer(0, 0) {
                if (isVertical) {
                    translationY = round(offset)
                } else {
                    translationX = round(offset)
                }
                clip = true
            }
        }
    }

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        if (!isAttached) return Offset.Zero
        val isActive = abs(offset) > offsetThreshold
        if (overScrollState.isOverScrollActive != isActive) {
            overScrollState.isOverScrollActive = isActive
        }

        if (shouldBypassForPullToRefresh() || source != NestedScrollSource.UserInput || !gestureActive) {
            return dispatcher.dispatchPreScroll(available, source)
        }

        // Resync raw accumulation when a drag takes over a running spring.
        if (animationJob?.isActive == true) syncRawAccumulationFromOffset()
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
        if (!isAttached) return Offset.Zero
        val isActive = abs(offset) > offsetThreshold
        if (overScrollState.isOverScrollActive != isActive) {
            overScrollState.isOverScrollActive = isActive
        }

        if (shouldBypassForPullToRefresh() || source != NestedScrollSource.UserInput || !gestureActive) {
            // A running spring settles to zero on its own; only reclaim when it was interrupted.
            if (animationJob?.isActive != true) {
                unwindStaleOffset(if (isVertical) consumed.y else consumed.x)
            }
            return dispatcher.dispatchPostScroll(consumed, available, source)
        }

        animationJob?.cancel()
        unwindStaleOffset(if (isVertical) consumed.y else consumed.x)

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
        if (!isAttached) return Velocity.Zero
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
        if (!isAttached) return Velocity.Zero
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
