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
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.input.pointer.PointerEventType
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
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.util.fastAny
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.LocalPullToRefreshState
import top.yukonga.miuix.kmp.basic.PullToRefreshState
import top.yukonga.miuix.kmp.basic.RefreshState
import top.yukonga.miuix.kmp.layout.LocalBottomSheetNestedScrollConnection
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.round
import kotlin.math.sign

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollVertical(
    onOffsetChanged: ((Float) -> Unit)? = null,
    isEnabled: () -> Boolean = { true },
): Modifier = overScrollOutOfBound(
    isVertical = true,
    onOffsetChanged = onOffsetChanged,
    isEnabled = isEnabled,
)

/**
 * @see overScrollOutOfBound
 */
@Stable
fun Modifier.overScrollHorizontal(
    onOffsetChanged: ((Float) -> Unit)? = null,
    isEnabled: () -> Boolean = { true },
): Modifier = overScrollOutOfBound(
    isVertical = false,
    onOffsetChanged = onOffsetChanged,
    isEnabled = isEnabled,
)

/**
 * Overscroll effect when scrolling to the boundary.
 *
 * Responds to press/pan gestures, not wheel or keyboard input. Desktop/macOS trackpads send wheel events.
 *
 * @param isVertical Whether the overscroll effect is vertical or horizontal.
 * @param onOffsetChanged Pixel-coalesced damped offset, including spring frames; not consumed scroll.
 * Reset, detach, and replacement clear the old receiver to zero; new callbacks receive the current offset.
 */
@Stable
fun Modifier.overScrollOutOfBound(
    isVertical: Boolean = true,
    onOffsetChanged: ((Float) -> Unit)? = null,
    isEnabled: () -> Boolean = { true },
): Modifier {
    if (!isEnabled()) return this

    return this
        .clipToBounds()
        .then(
            OverscrollElement(
                isVertical = isVertical,
                onOffsetChanged = onOffsetChanged,
            ),
        )
}

private data class OverscrollElement(
    val isVertical: Boolean,
    val onOffsetChanged: ((Float) -> Unit)?,
) : ModifierNodeElement<OverscrollNode>() {
    override fun create(): OverscrollNode = OverscrollNode(
        isVertical = isVertical,
        onOffsetChanged = onOffsetChanged,
    )

    override fun update(node: OverscrollNode) {
        node.update(
            isVertical = isVertical,
            onOffsetChanged = onOffsetChanged,
        )
        node.invalidatePlacement()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "overScrollOutOfBound"
        properties["isVertical"] = isVertical
        properties["onOffsetChanged"] = onOffsetChanged
    }
}

private class OverscrollNode(
    var isVertical: Boolean,
    var onOffsetChanged: ((Float) -> Unit)?,
) : DelegatingNode(),
    CompositionLocalConsumerModifierNode,
    LayoutModifierNode,
    OverscrollMotionNode,
    NestedScrollConnection {

    private val overScrollState: OverScrollState
        get() = currentValueOf(LocalOverScrollState)

    private val pullToRefreshState: PullToRefreshState?
        get() = currentValueOf(LocalPullToRefreshState)

    private val springEngine = SpringEngine()
    private var animationJob: Job? = null
    private val offsetThreshold = 1f

    override val isVerticallyRebounding: Boolean
        get() = isVertical && animationJob?.isActive == true

    override val isHorizontallyRebounding: Boolean
        get() = !isVertical && animationJob?.isActive == true

    // Track press/pan sessions, including custom mouse drags. Wheel-only input must not
    // accumulate overscroll because it may never dispatch a fling to settle the offset.
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
                            else -> event.changes.fastAny { it.pressed }
                        }
                        if (gestureActive && !active && animationJob?.isActive != true && abs(offset) > offsetThreshold) {
                            // Settle gestures without a fling; onPreFling replaces this fallback when needed.
                            startSpringAnimation()
                        }
                        gestureActive = active
                    }
                }
            },
        )
    }

    private var lastPlacedOffset = 0f
    private var lastNotifiedOffset = 0f
    private var lastNotifiedRoundedOffset = 0f
    var offset = 0f
        private set(value) {
            if (field == value) return
            field = value
            val rounded = round(value)
            if (rounded != lastNotifiedRoundedOffset) {
                lastNotifiedRoundedOffset = rounded
                lastNotifiedOffset = value
                onOffsetChanged?.invoke(value)
            }
            // Placement rounds to pixels, so invalidate only when the rounded offset changes.
            if (rounded != lastPlacedOffset) {
                lastPlacedOffset = rounded
                if (isAttached) invalidatePlacement()
            }
        }

    private fun notifyResetOffset() {
        if (lastNotifiedOffset != 0f) {
            lastNotifiedRoundedOffset = 0f
            lastNotifiedOffset = 0f
            onOffsetChanged?.invoke(0f)
        }
    }

    private var rawTouchAccumulation = 0f
    private var scrollRange: Float = 0f

    override fun onAttach() {
        super.onAttach()
        updateScrollRange()
        delegate(nestedScrollModifierNode(this, null))
    }

    override fun onDetach() {
        super.onDetach()
        gestureActive = false
        resetState()
    }

    fun update(
        isVertical: Boolean,
        onOffsetChanged: ((Float) -> Unit)?,
    ) {
        if (this.isVertical != isVertical) {
            animationJob?.cancel()
            animationJob = null
            resetState()
            this.isVertical = isVertical
            if (isAttached) updateScrollRange()
        }
        if (this.onOffsetChanged !== onOffsetChanged) {
            if (lastNotifiedOffset != 0f) this.onOffsetChanged?.invoke(0f)
            this.onOffsetChanged = onOffsetChanged
            onOffsetChanged?.invoke(lastNotifiedOffset)
        }
    }

    private fun updateScrollRange() {
        val size = currentValueOf(LocalWindowInfo).containerSize
        scrollRange = (if (isVertical) size.height else size.width).toFloat()
    }

    private fun resetState() {
        offset = 0f
        notifyResetOffset()
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

    private fun shouldBypassForPullToRefresh(): Boolean = pullToRefreshState != null && pullToRefreshState?.refreshState != RefreshState.Idle && isVertical

    private fun applyDrag(delta: Float) {
        if (delta == 0f || scrollRange == 0f) return
        rawTouchAccumulation += delta
        rawTouchAccumulation = rawTouchAccumulation.coerceIn(-scrollRange, scrollRange)
        val normalized = min(abs(rawTouchAccumulation) / scrollRange, 1.0f)
        val dampedDist = SpringMath.obtainDampingDistance(normalized, scrollRange)
        offset = sign(rawTouchAccumulation) * dampedDist
    }

    /** Invert the damping curve when a drag takes over a spring. */
    private fun syncRawAccumulationFromOffset() {
        rawTouchAccumulation = sign(offset) * SpringMath.obtainTouchDistance(offset, scrollRange)
    }

    /** Release stale overscroll once the child can scroll again, preserving the next fling. */
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
                translationY = if (isVertical) round(offset) else 0f
                translationX = if (isVertical) 0f else round(offset)
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
            return Offset.Zero
        }

        // The framework has already subtracted ancestor pre-consumption.
        val delta = if (isVertical) available.y else available.x
        if (delta == 0f) return Offset.Zero

        // Resync raw accumulation when a drag takes over a running spring.
        if (animationJob?.isActive == true) syncRawAccumulationFromOffset()
        animationJob?.cancel()

        if (abs(offset) <= offsetThreshold || sign(delta) == sign(rawTouchAccumulation)) {
            return Offset.Zero
        }

        val actualConsumed = if (abs(rawTouchAccumulation) <= abs(delta)) -rawTouchAccumulation else delta
        if (abs(rawTouchAccumulation) <= abs(delta)) {
            resetState()
        } else {
            applyDrag(actualConsumed)
        }
        return if (isVertical) Offset(0f, actualConsumed) else Offset(actualConsumed, 0f)
    }

    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
        if (!isAttached) return Offset.Zero
        val isActive = abs(offset) > offsetThreshold
        if (overScrollState.isOverScrollActive != isActive) {
            overScrollState.isOverScrollActive = isActive
        }

        // Yield before consuming a delta the ancestor can handle.
        val handOff = isVertical && (
            pullToRefreshState?.canPullDown(available.y, source) == true ||
                currentValueOf(LocalBottomSheetNestedScrollConnection)?.canConsumePostScroll(consumed, available, source) == true
            )
        if (handOff || shouldBypassForPullToRefresh() || source != NestedScrollSource.UserInput || !gestureActive) {
            // Leave active springs to settle themselves.
            if (animationJob?.isActive != true) {
                unwindStaleOffset(if (isVertical) consumed.y else consumed.x)
            }
            return Offset.Zero
        }

        val delta = if (isVertical) available.y else available.x
        val consumedDelta = if (isVertical) consumed.y else consumed.x
        if (delta == 0f && consumedDelta == 0f) return Offset.Zero
        if (animationJob?.isActive == true) syncRawAccumulationFromOffset()
        animationJob?.cancel()
        unwindStaleOffset(consumedDelta)

        applyDrag(delta)
        return if (isVertical) Offset(0f, available.y) else Offset(available.x, 0f)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        if (!isAttached) return Velocity.Zero
        val isActive = abs(offset) > offsetThreshold
        if (overScrollState.isOverScrollActive != isActive) {
            overScrollState.isOverScrollActive = isActive
        }

        if (shouldBypassForPullToRefresh() && !overScrollState.isOverScrollActive) {
            return Velocity.Zero
        }

        animationJob?.cancel()

        val velocity = if (isVertical) available.y else available.x

        if (abs(offset) > offsetThreshold) {
            if (sign(velocity) != sign(offset)) {
                startSpringAnimation(velocity)
                // Attenuate velocity when flinging back toward the scrollable content.
                return if (isVertical) {
                    Velocity(
                        0f,
                        available.y / 2.13333f,
                    )
                } else {
                    Velocity(available.x / 2.13333f, 0f)
                }
            } else {
                startSpringAnimation(velocity)
                return if (isVertical) Velocity(0f, available.y) else Velocity(available.x, 0f)
            }
        }

        return Velocity.Zero
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        if (!isAttached) return Velocity.Zero
        val isActive = abs(offset) > offsetThreshold
        if (overScrollState.isOverScrollActive != isActive) {
            overScrollState.isOverScrollActive = isActive
        }

        if (shouldBypassForPullToRefresh() && !overScrollState.isOverScrollActive) {
            return Velocity.Zero
        }

        animationJob?.cancel()
        val velocity = (if (isVertical) available.y else available.x) / 1.53333f
        startSpringAnimation(velocity)

        return if (isVertical) Velocity(0f, velocity) else Velocity(velocity, 0f)
    }
}

/**
 * Tracks whether overscroll is active.
 */
class OverScrollState {
    var isOverScrollActive by mutableStateOf(false)
        internal set
}

/**
 * Shared overscroll state for scroll and pull-to-refresh coordination.
 */
val LocalOverScrollState = compositionLocalOf { OverScrollState() }
