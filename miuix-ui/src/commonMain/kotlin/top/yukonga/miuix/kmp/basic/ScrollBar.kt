// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollIndicatorState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.PointerInputModifierNode
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.node.requireLayoutDirection
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirstOrNull
import androidx.compose.ui.util.fastRoundToInt
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.abs

/**
 * Defines how to scroll the scrollable component and how to display a scrollbar for it.
 */
@ExperimentalScrollBarApi
interface ScrollBarAdapter {
    val scrollOffset: Double
    val contentSize: Double
    val viewportSize: Double
    suspend fun scrollTo(scrollOffset: Double)
}

@OptIn(ExperimentalScrollBarApi::class)
internal val ScrollBarAdapter.maxScrollOffset: Double
    get() = (contentSize - viewportSize).coerceAtLeast(0.0)

/**
 * Create and [remember] a [ScrollBarAdapter] backed by [ScrollableState.scrollIndicatorState].
 *
 * Supports [ScrollState], [LazyListState], [LazyGridState], [LazyStaggeredGridState],
 * [PagerState], and any custom [ScrollableState] providing a [ScrollIndicatorState].
 *
 * @param scrollState The [ScrollableState] to observe and scroll.
 * @param reverseLayout Set to `true` only when using [ScrollState] with `reverseScrolling = true`
 *   or a custom [ScrollableState] whose scroll direction is reversed and not exposed via layoutInfo.
 */
@ExperimentalScrollBarApi
@Composable
fun rememberScrollBarAdapter(
    scrollState: ScrollableState,
    reverseLayout: Boolean = false,
): ScrollBarAdapter = remember(scrollState, reverseLayout) {
    ScrollableStateScrollBarAdapter(scrollState, reverseLayout)
}

/**
 * Colors for the scrollbar component.
 *
 * @param thumbColor The color of the scrollbar thumb. [Color.Unspecified] uses theme default.
 * @param trackColor The color of the scrollbar track. [Color.Unspecified] hides the track.
 */
@ExperimentalScrollBarApi
@Immutable
data class ScrollBarColors(
    val thumbColor: Color,
    val trackColor: Color,
)

/**
 * Adds a vertical interactive scrollbar overlay to a scrollable container.
 *
 * For containers using `Modifier.verticalScroll`, apply this modifier before `verticalScroll`
 * so the scrollbar does not scroll with the content.
 *
 * @param state The [ScrollableState] of the scrollable container.
 * @param reverseLayout Reverse scrolling direction hint for [ScrollState] or custom states.
 * @param trackPadding Padding applied to the track to skip content padding areas.
 * @param colors The colors of the scrollbar.
 * @param thumbWidth The resting width of the thumb.
 * @param cornerRadius The corner radius. [Dp.Unspecified] defaults to half of the animated thumb width.
 * @param thumbMinLength The minimum length of the thumb.
 * @param endPadding The padding from the end edge.
 * @param isFadeEnabled Whether the scrollbar fades out after scrolling or interaction stops.
 * @param interactive Whether the scrollbar responds to hover, click, and drag gestures.
 *   When `false`, it acts as a non-interactive scroll indicator.
 * @param topAppBarScrollBehavior Optional [ScrollBehavior] to coordinate top app bar collapse/expand
 *   and blur progress while dragging the scrollbar.
 */
@ExperimentalScrollBarApi
@Composable
fun Modifier.verticalScrollBar(
    state: ScrollableState,
    reverseLayout: Boolean = false,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
    isFadeEnabled: Boolean = true,
    interactive: Boolean = true,
    topAppBarScrollBehavior: ScrollBehavior? = null,
): Modifier = verticalScrollBar(
    adapter = rememberScrollBarAdapter(state, reverseLayout),
    trackPadding = trackPadding,
    colors = colors,
    thumbWidth = thumbWidth,
    cornerRadius = cornerRadius,
    thumbMinLength = thumbMinLength,
    endPadding = endPadding,
    isFadeEnabled = isFadeEnabled,
    interactive = interactive,
    topAppBarScrollBehavior = topAppBarScrollBehavior,
)

/**
 * Adds a vertical interactive scrollbar overlay using a custom [ScrollBarAdapter].
 */
@ExperimentalScrollBarApi
@Composable
fun Modifier.verticalScrollBar(
    adapter: ScrollBarAdapter,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
    isFadeEnabled: Boolean = true,
    interactive: Boolean = true,
    topAppBarScrollBehavior: ScrollBehavior? = null,
): Modifier = scrollBar(
    adapter = adapter,
    orientation = Orientation.Vertical,
    trackPadding = trackPadding,
    colors = colors,
    thumbWidth = thumbWidth,
    cornerRadius = cornerRadius,
    thumbMinLength = thumbMinLength,
    endPadding = endPadding,
    isFadeEnabled = isFadeEnabled,
    interactive = interactive,
    topAppBarScrollBehavior = topAppBarScrollBehavior,
)

/**
 * Adds a horizontal interactive scrollbar overlay to a scrollable container.
 *
 * For containers using `Modifier.horizontalScroll`, apply this modifier before `horizontalScroll`
 * so the scrollbar does not scroll with the content.
 */
@ExperimentalScrollBarApi
@Composable
fun Modifier.horizontalScrollBar(
    state: ScrollableState,
    reverseLayout: Boolean = false,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
    isFadeEnabled: Boolean = true,
    interactive: Boolean = true,
): Modifier = horizontalScrollBar(
    adapter = rememberScrollBarAdapter(state, reverseLayout),
    trackPadding = trackPadding,
    colors = colors,
    thumbWidth = thumbWidth,
    cornerRadius = cornerRadius,
    thumbMinLength = thumbMinLength,
    endPadding = endPadding,
    interactive = interactive,
    isFadeEnabled = isFadeEnabled,
)

/**
 * Adds a horizontal interactive scrollbar overlay using a custom [ScrollBarAdapter].
 */
@ExperimentalScrollBarApi
@Composable
fun Modifier.horizontalScrollBar(
    adapter: ScrollBarAdapter,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
    isFadeEnabled: Boolean = true,
    interactive: Boolean = true,
): Modifier = scrollBar(
    adapter = adapter,
    orientation = Orientation.Horizontal,
    trackPadding = trackPadding,
    colors = colors,
    thumbWidth = thumbWidth,
    cornerRadius = cornerRadius,
    thumbMinLength = thumbMinLength,
    endPadding = endPadding,
    isFadeEnabled = isFadeEnabled,
    interactive = interactive,
    topAppBarScrollBehavior = null,
)

/**
 * A vertical scrollbar.
 *
 * @param adapter [ScrollBarAdapter] that communicates with the scrollable component.
 * @param modifier The modifier to apply to this layout.
 * @param reverseLayout Deprecated; visual scroll direction is resolved automatically via [ScrollIndicatorState].
 * @param trackPadding Padding applied to the track to skip content padding areas.
 * @param colors The colors of the scrollbar.
 * @param thumbWidth The width of the thumb.
 * @param cornerRadius The corner radius. [Dp.Unspecified] defaults to half of the animated thumb width.
 * @param thumbMinLength The minimum length of the thumb.
 * @param endPadding The padding from the end edge.
 * @param isFadeEnabled Whether the scrollbar fades out after scrolling or interaction stops.
 * @param interactive Whether the scrollbar responds to hover, click, and drag gestures.
 *   When `false`, it acts as a non-interactive scroll indicator.
 * @param topAppBarScrollBehavior Optional [ScrollBehavior] to coordinate top app bar collapse/expand
 *   and blur progress while dragging the scrollbar.
 */
@ExperimentalScrollBarApi
@Composable
fun VerticalScrollBar(
    adapter: ScrollBarAdapter,
    modifier: Modifier = Modifier,
    @Suppress("UNUSED_PARAMETER")
    reverseLayout: Boolean = false,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
    isFadeEnabled: Boolean = true,
    interactive: Boolean = true,
    topAppBarScrollBehavior: ScrollBehavior? = null,
) {
    val measurePolicy = remember(thumbWidth, endPadding) {
        verticalMeasurePolicy(endPadding = endPadding, thumbWidth = thumbWidth)
    }
    Layout(
        content = {},
        modifier = modifier.scrollBar(
            adapter = adapter,
            orientation = Orientation.Vertical,
            trackPadding = trackPadding,
            colors = colors,
            thumbWidth = thumbWidth,
            cornerRadius = cornerRadius,
            thumbMinLength = thumbMinLength,
            endPadding = endPadding,
            isFadeEnabled = isFadeEnabled,
            interactive = interactive,
            topAppBarScrollBehavior = topAppBarScrollBehavior,
        ),
        measurePolicy = measurePolicy,
    )
}

/**
 * A horizontal scrollbar.
 *
 * @param adapter [ScrollBarAdapter] that communicates with the scrollable component.
 * @param modifier The modifier to apply to this layout.
 * @param reverseLayout Deprecated; visual scroll direction is resolved automatically via [ScrollIndicatorState].
 * @param trackPadding Padding applied to the track to skip content padding areas.
 * @param colors The colors of the scrollbar.
 * @param thumbWidth The width of the thumb.
 * @param cornerRadius The corner radius. [Dp.Unspecified] defaults to half of the animated thumb width.
 * @param thumbMinLength The minimum length of the thumb.
 * @param endPadding The padding from the end edge.
 * @param isFadeEnabled Whether the scrollbar fades out after scrolling or interaction stops.
 * @param interactive Whether the scrollbar responds to hover, click, and drag gestures.
 *   When `false`, it acts as a non-interactive scroll indicator.
 */
@ExperimentalScrollBarApi
@Composable
fun HorizontalScrollBar(
    adapter: ScrollBarAdapter,
    modifier: Modifier = Modifier,
    @Suppress("UNUSED_PARAMETER")
    reverseLayout: Boolean = false,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
    isFadeEnabled: Boolean = true,
    interactive: Boolean = true,
) {
    val measurePolicy = remember(thumbWidth, endPadding) {
        horizontalMeasurePolicy(endPadding = endPadding, thumbWidth = thumbWidth)
    }
    Layout(
        content = {},
        modifier = modifier.scrollBar(
            adapter = adapter,
            orientation = Orientation.Horizontal,
            trackPadding = trackPadding,
            colors = colors,
            thumbWidth = thumbWidth,
            cornerRadius = cornerRadius,
            thumbMinLength = thumbMinLength,
            endPadding = endPadding,
            isFadeEnabled = isFadeEnabled,
            interactive = interactive,
            topAppBarScrollBehavior = null,
        ),
        measurePolicy = measurePolicy,
    )
}

@ExperimentalScrollBarApi
object ScrollBarDefaults {
    val ThumbWidth: Dp = 3.64.dp
    val EndPadding: Dp = 3.46.dp
    val ThumbMinLength: Dp = 36.dp
    val CornerRadius: Dp = Dp.Unspecified
    val FadeDelayMillis = 1000
    val FadeDurationMillis = 500
    val TouchTargetWidth: Dp = 24.dp
    val DragThumbWidth: Dp = 6.dp
    val ThumbAlpha = 0.1f
    val DragThumbAlpha = 0.3f
    val DragAnimationDurationMillis = 150

    fun scrollBarColors(
        thumbColor: Color = Color.Unspecified,
        trackColor: Color = Color.Unspecified,
    ): ScrollBarColors = ScrollBarColors(
        thumbColor = thumbColor,
        trackColor = trackColor,
    )
}

@OptIn(ExperimentalScrollBarApi::class)
@Composable
private fun Modifier.scrollBar(
    adapter: ScrollBarAdapter,
    orientation: Orientation,
    trackPadding: PaddingValues,
    colors: ScrollBarColors,
    thumbWidth: Dp,
    cornerRadius: Dp,
    thumbMinLength: Dp,
    endPadding: Dp,
    isFadeEnabled: Boolean,
    interactive: Boolean,
    topAppBarScrollBehavior: ScrollBehavior?,
): Modifier {
    val hasCustomThumbColor = colors.thumbColor != Color.Unspecified
    val resolvedThumbColor = if (hasCustomThumbColor) colors.thumbColor else MiuixTheme.colorScheme.onSurface
    val resolvedDefaultAlpha = if (hasCustomThumbColor) colors.thumbColor.alpha else ScrollBarDefaults.ThumbAlpha
    val resolvedHighlightAlpha = if (hasCustomThumbColor) colors.thumbColor.alpha else ScrollBarDefaults.DragThumbAlpha

    return this.then(
        ScrollBarElement(
            adapter = adapter,
            orientation = orientation,
            trackPadding = trackPadding,
            thumbColor = resolvedThumbColor,
            defaultThumbAlpha = resolvedDefaultAlpha,
            highlightThumbAlpha = resolvedHighlightAlpha,
            trackColor = colors.trackColor,
            thumbWidth = thumbWidth,
            cornerRadius = cornerRadius,
            thumbMinLength = thumbMinLength,
            endPadding = endPadding,
            isFadeEnabled = isFadeEnabled,
            interactive = interactive,
            topAppBarScrollBehavior = topAppBarScrollBehavior,
        ),
    )
}

@OptIn(ExperimentalScrollBarApi::class)
private class ScrollBarElement(
    private val adapter: ScrollBarAdapter,
    private val orientation: Orientation,
    private val trackPadding: PaddingValues,
    private val thumbColor: Color,
    private val defaultThumbAlpha: Float,
    private val highlightThumbAlpha: Float,
    private val trackColor: Color,
    private val thumbWidth: Dp,
    private val cornerRadius: Dp,
    private val thumbMinLength: Dp,
    private val endPadding: Dp,
    private val isFadeEnabled: Boolean,
    private val interactive: Boolean,
    private val topAppBarScrollBehavior: ScrollBehavior?,
) : ModifierNodeElement<ScrollBarNode>() {

    override fun create(): ScrollBarNode = ScrollBarNode(
        adapter = adapter,
        orientation = orientation,
        trackPadding = trackPadding,
        thumbColor = thumbColor,
        defaultThumbAlpha = defaultThumbAlpha,
        highlightThumbAlpha = highlightThumbAlpha,
        trackColor = trackColor,
        thumbWidth = thumbWidth,
        cornerRadius = cornerRadius,
        thumbMinLength = thumbMinLength,
        endPadding = endPadding,
        isFadeEnabled = isFadeEnabled,
        interactive = interactive,
        topAppBarScrollBehavior = topAppBarScrollBehavior,
    )

    override fun update(node: ScrollBarNode) {
        node.update(
            adapter = adapter,
            orientation = orientation,
            trackPadding = trackPadding,
            thumbColor = thumbColor,
            defaultThumbAlpha = defaultThumbAlpha,
            highlightThumbAlpha = highlightThumbAlpha,
            trackColor = trackColor,
            thumbWidth = thumbWidth,
            cornerRadius = cornerRadius,
            thumbMinLength = thumbMinLength,
            endPadding = endPadding,
            isFadeEnabled = isFadeEnabled,
            interactive = interactive,
            topAppBarScrollBehavior = topAppBarScrollBehavior,
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScrollBarElement) return false
        if (adapter != other.adapter) return false
        if (orientation != other.orientation) return false
        if (trackPadding != other.trackPadding) return false
        if (thumbColor != other.thumbColor) return false
        if (defaultThumbAlpha != other.defaultThumbAlpha) return false
        if (highlightThumbAlpha != other.highlightThumbAlpha) return false
        if (trackColor != other.trackColor) return false
        if (thumbWidth != other.thumbWidth) return false
        if (cornerRadius != other.cornerRadius) return false
        if (thumbMinLength != other.thumbMinLength) return false
        if (endPadding != other.endPadding) return false
        if (isFadeEnabled != other.isFadeEnabled) return false
        if (interactive != other.interactive) return false
        if (topAppBarScrollBehavior != other.topAppBarScrollBehavior) return false
        return true
    }

    override fun hashCode(): Int {
        var result = adapter.hashCode()
        result = 31 * result + orientation.hashCode()
        result = 31 * result + trackPadding.hashCode()
        result = 31 * result + thumbColor.hashCode()
        result = 31 * result + defaultThumbAlpha.hashCode()
        result = 31 * result + highlightThumbAlpha.hashCode()
        result = 31 * result + trackColor.hashCode()
        result = 31 * result + thumbWidth.hashCode()
        result = 31 * result + cornerRadius.hashCode()
        result = 31 * result + thumbMinLength.hashCode()
        result = 31 * result + endPadding.hashCode()
        result = 31 * result + isFadeEnabled.hashCode()
        result = 31 * result + interactive.hashCode()
        result = 31 * result + (topAppBarScrollBehavior?.hashCode() ?: 0)
        return result
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "scrollBar"
        properties["adapter"] = adapter
        properties["orientation"] = orientation
        properties["trackPadding"] = trackPadding
        properties["thumbColor"] = thumbColor
        properties["trackColor"] = trackColor
        properties["thumbWidth"] = thumbWidth
        properties["cornerRadius"] = cornerRadius
        properties["thumbMinLength"] = thumbMinLength
        properties["endPadding"] = endPadding
        properties["isFadeEnabled"] = isFadeEnabled
        properties["interactive"] = interactive
        properties["topAppBarScrollBehavior"] = topAppBarScrollBehavior
    }
}

@OptIn(ExperimentalScrollBarApi::class)
private class ScrollBarNode(
    private var adapter: ScrollBarAdapter,
    private var orientation: Orientation,
    private var trackPadding: PaddingValues,
    private var thumbColor: Color,
    private var defaultThumbAlpha: Float,
    private var highlightThumbAlpha: Float,
    private var trackColor: Color,
    private var thumbWidth: Dp,
    private var cornerRadius: Dp,
    private var thumbMinLength: Dp,
    private var endPadding: Dp,
    private var isFadeEnabled: Boolean,
    private var interactive: Boolean,
    private var topAppBarScrollBehavior: ScrollBehavior?,
) : DelegatingNode(),
    DrawModifierNode,
    PointerInputModifierNode {

    private val alpha = Animatable(if (isFadeEnabled) 0f else 1f)
    private val highlightProgress = Animatable(0f)

    private var fadeJob: Job? = null
    private var scrollEvents: Channel<Unit>? = null
    private var highlightJob: Job? = null
    private var thumbLengthAnimJob: Job? = null
    private var scrollJob: Job? = null
    private var settleJob: Job? = null
    private var topAppBarAnimJob: Job? = null
    private var topAppBarTarget: Float = Float.NaN

    private var lastScrollOffset: Double = Double.NaN
    private var displayedThumbLength: Float = 0f
    private var thumbLengthAnimTarget: Float = 0f
    private var dragTrackLengthPx: Float = 0f

    private var isHovered: Boolean = false
    private var isDragging: Boolean = false
    private var capturedPointerId: PointerId? = null
    private var lastDragPointerPosition: Offset = Offset.Unspecified
    private var unscrolledDragDistancePx: Double = 0.0
    private var pendingDragDeltaPx: Float = 0f
    private var pendingTargetScrollOffset: Double? = null

    // Geometry cached from latest layout/draw for hit testing.
    private var cachedBounds: IntSize = IntSize.Zero
    private var cachedBeforePaddingPx: Float = 0f
    private var cachedTrackLengthPx: Float = 0f
    private var cachedThumbLengthPx: Float = 0f
    private var cachedThumbWidthPx: Float = 0f
    private var cachedEndPaddingPx: Float = 0f
    private var isScrollable: Boolean = false

    private val isVertical: Boolean
        get() = orientation == Orientation.Vertical

    private val isHighlighted: Boolean
        get() = isHovered || isDragging

    private val maxTrackOffsetPx: Float
        get() = (cachedTrackLengthPx - cachedThumbLengthPx).coerceAtLeast(0f)

    fun update(
        adapter: ScrollBarAdapter,
        orientation: Orientation,
        trackPadding: PaddingValues,
        thumbColor: Color,
        defaultThumbAlpha: Float,
        highlightThumbAlpha: Float,
        trackColor: Color,
        thumbWidth: Dp,
        cornerRadius: Dp,
        thumbMinLength: Dp,
        endPadding: Dp,
        isFadeEnabled: Boolean,
        interactive: Boolean,
        topAppBarScrollBehavior: ScrollBehavior?,
    ) {
        val adapterChanged = this.adapter != adapter
        val fadeChanged = this.isFadeEnabled != isFadeEnabled
        val interactiveChanged = this.interactive != interactive
        val behaviorChanged = this.topAppBarScrollBehavior != topAppBarScrollBehavior
        this.orientation = orientation
        this.trackPadding = trackPadding
        this.thumbColor = thumbColor
        this.defaultThumbAlpha = defaultThumbAlpha
        this.highlightThumbAlpha = highlightThumbAlpha
        this.trackColor = trackColor
        this.thumbWidth = thumbWidth
        this.cornerRadius = cornerRadius
        this.thumbMinLength = thumbMinLength
        this.endPadding = endPadding
        this.isFadeEnabled = isFadeEnabled
        this.interactive = interactive
        this.topAppBarScrollBehavior = topAppBarScrollBehavior

        if (adapterChanged) {
            lastScrollOffset = Double.NaN
            displayedThumbLength = 0f
            thumbLengthAnimJob?.cancel()
            thumbLengthAnimJob = null
        }
        if (behaviorChanged) {
            topAppBarAnimJob?.cancel()
            topAppBarAnimJob = null
            topAppBarTarget = Float.NaN
        }

        if (fadeChanged) {
            fadeJob?.cancel()
            fadeJob = null
            if (!isFadeEnabled) {
                coroutineScope.launch { alpha.snapTo(1f) }
            } else if (!isHighlighted) {
                scheduleFadeOut(snapVisibleFirst = false)
            }
        }
        if (interactiveChanged && !interactive) {
            finishPointerInteraction(cancelled = true)
        }

        invalidateDraw()
    }

    override fun onAttach() {
        super.onAttach()
        lastScrollOffset = Double.NaN
        displayedThumbLength = 0f
        if (!isFadeEnabled) {
            coroutineScope.launch { alpha.snapTo(1f) }
        }
    }

    override fun onDetach() {
        fadeJob?.cancel()
        fadeJob = null
        scrollEvents?.close()
        scrollEvents = null
        highlightJob?.cancel()
        highlightJob = null
        thumbLengthAnimJob?.cancel()
        thumbLengthAnimJob = null
        scrollJob?.cancel()
        scrollJob = null
        settleJob?.cancel()
        settleJob = null
        topAppBarAnimJob?.cancel()
        topAppBarAnimJob = null
        topAppBarTarget = Float.NaN
        dragTrackLengthPx = 0f
        pendingTargetScrollOffset = null
        pendingDragDeltaPx = 0f
        unscrolledDragDistancePx = 0.0
        capturedPointerId = null
        isHovered = false
        isDragging = false
        lastDragPointerPosition = Offset.Unspecified
        super.onDetach()
    }

    private fun updateHighlight() {
        val target = if (isHighlighted) 1f else 0f
        if (highlightProgress.targetValue == target && highlightJob?.isActive == true) return
        if (highlightProgress.value == target && highlightJob?.isActive != true) return
        highlightJob?.cancel()
        highlightJob = coroutineScope.launch {
            highlightProgress.animateTo(
                targetValue = target,
                animationSpec = tween(ScrollBarDefaults.DragAnimationDurationMillis),
            )
        }
    }

    private fun fadeInImmediate() {
        if (!isFadeEnabled) return
        fadeJob?.cancel()
        fadeJob = null
        if (alpha.value < 1f || alpha.targetValue < 1f) {
            coroutineScope.launch { alpha.snapTo(1f) }
        }
    }

    private fun scheduleFadeOut(snapVisibleFirst: Boolean = true) {
        if (!isFadeEnabled) return
        if (isHighlighted) {
            fadeInImmediate()
            return
        }

        var events = scrollEvents
        if (events == null) {
            events = Channel(Channel.CONFLATED)
            scrollEvents = events
        }
        events.trySend(Unit)

        if (fadeJob?.isActive == true && alpha.targetValue == 1f) return
        if (fadeJob?.isActive == true) return

        fadeJob = coroutineScope.launch {
            if (snapVisibleFirst && alpha.value < 1f) {
                alpha.snapTo(1f)
            }
            while (true) {
                events.tryReceive()
                val interrupted = withTimeoutOrNull(ScrollBarDefaults.FadeDelayMillis.toLong()) {
                    events.receive()
                }
                if (interrupted == null) break
                if (isHighlighted) return@launch
            }
            if (!isHighlighted) {
                alpha.animateTo(0f, tween(ScrollBarDefaults.FadeDurationMillis))
            }
        }
    }

    private fun scheduleFadeOutAfterInteraction() {
        if (!isFadeEnabled || isHighlighted) return
        fadeJob?.cancel()
        fadeJob = coroutineScope.launch {
            delay(ScrollBarDefaults.FadeDelayMillis.toLong())
            if (!isHighlighted) {
                alpha.animateTo(0f, tween(ScrollBarDefaults.FadeDurationMillis))
            }
        }
    }

    private fun currentThumbOffsetOnTrack(): Float {
        val maxScroll = adapter.maxScrollOffset
        if (maxScroll <= 0.0) return 0f
        return ((adapter.scrollOffset / maxScroll) * maxTrackOffsetPx)
            .toFloat()
            .coerceIn(0f, maxTrackOffsetPx)
    }

    private fun getTrackAxisPosition(
        position: Offset,
        beforePaddingPx: Float = cachedBeforePaddingPx,
    ): Float {
        val raw = if (isVertical) {
            position.y - beforePaddingPx
        } else if (requireLayoutDirection() == LayoutDirection.Rtl) {
            cachedBounds.width - beforePaddingPx - position.x
        } else {
            position.x - beforePaddingPx
        }
        return raw
    }

    private fun isPointInHoverStrip(position: Offset): Boolean {
        if (!isScrollable || cachedBounds == IntSize.Zero) return false
        val hoverStripWidth = with(requireDensity()) {
            (maxOf(thumbWidth, ScrollBarDefaults.DragThumbWidth) + endPadding * 2).toPx()
        }
        val mainAxisPos = getTrackAxisPosition(position)
        if (mainAxisPos < 0f || mainAxisPos > cachedTrackLengthPx) return false

        return if (isVertical) {
            if (requireLayoutDirection() == LayoutDirection.Rtl) {
                position.x in 0f..hoverStripWidth
            } else {
                position.x >= cachedBounds.width - hoverStripWidth && position.x <= cachedBounds.width.toFloat()
            }
        } else {
            position.y >= cachedBounds.height - hoverStripWidth && position.y <= cachedBounds.height.toFloat()
        }
    }

    private fun isPointInTouchStrip(position: Offset): Boolean {
        if (!isScrollable || cachedBounds == IntSize.Zero) return false
        val touchStripWidth = with(requireDensity()) {
            maxOf(ScrollBarDefaults.TouchTargetWidth, maxOf(thumbWidth, ScrollBarDefaults.DragThumbWidth) + endPadding * 2).toPx()
        }
        val mainAxisPos = getTrackAxisPosition(position)
        if (mainAxisPos < 0f || mainAxisPos > cachedTrackLengthPx) return false

        return if (isVertical) {
            if (requireLayoutDirection() == LayoutDirection.Rtl) {
                position.x in 0f..touchStripWidth
            } else {
                position.x >= cachedBounds.width - touchStripWidth && position.x <= cachedBounds.width.toFloat()
            }
        } else {
            position.y >= cachedBounds.height - touchStripWidth && position.y <= cachedBounds.height.toFloat()
        }
    }

    private fun isPointInThumb(position: Offset, pointerType: PointerType): Boolean {
        val inStrip = if (pointerType == PointerType.Touch) {
            isPointInTouchStrip(position)
        } else {
            isPointInHoverStrip(position)
        }
        if (!inStrip) return false

        val pointerOnTrack = getTrackAxisPosition(position)
        val thumbStart = currentThumbOffsetOnTrack()
        val thumbEnd = thumbStart + cachedThumbLengthPx
        return pointerOnTrack in thumbStart..thumbEnd
    }

    private fun requestScrollTo(targetScrollOffset: Double) {
        pendingTargetScrollOffset = targetScrollOffset
        pendingDragDeltaPx = 0f
        launchScrollJobIfNeeded()
    }

    private fun requestDragDelta(deltaPx: Float) {
        pendingDragDeltaPx += deltaPx
        launchScrollJobIfNeeded()
    }

    private fun launchScrollJobIfNeeded() {
        if (scrollJob?.isActive == true) return
        scrollJob = coroutineScope.launch(start = CoroutineStart.UNDISPATCHED) {
            while (true) {
                val jumpTarget = pendingTargetScrollOffset
                if (jumpTarget != null) {
                    pendingTargetScrollOffset = null
                    adapter.scrollTo(jumpTarget)
                    topAppBarScrollBehavior?.let { behavior ->
                        syncTopAppBarOnScroll(
                            behavior = behavior,
                            currentScrollOffset = adapter.scrollOffset,
                        )
                    }
                    continue
                }

                val delta = pendingDragDeltaPx
                if (delta == 0f) break
                pendingDragDeltaPx = 0f

                val maxScroll = adapter.maxScrollOffset
                val maxTrack = (dragTrackLengthPx - cachedThumbLengthPx).coerceAtLeast(0f).toDouble()
                if (maxScroll <= 0.0 || maxTrack <= 0.0) continue

                val scrollScale = maxTrack / maxScroll
                val currentThumbPos = scrollScale * adapter.scrollOffset
                val targetThumbPos = (currentThumbPos + delta + unscrolledDragDistancePx)
                    .coerceIn(0.0, maxTrack)
                val clampedDelta = targetThumbPos - currentThumbPos
                unscrolledDragDistancePx += delta - clampedDelta

                val targetScrollOffset = (targetThumbPos / scrollScale).coerceIn(0.0, maxScroll)
                adapter.scrollTo(targetScrollOffset)
                topAppBarScrollBehavior?.let { behavior ->
                    syncTopAppBarOnScroll(
                        behavior = behavior,
                        currentScrollOffset = adapter.scrollOffset,
                    )
                }
            }
        }
    }

    private fun syncTopAppBarOnScroll(
        behavior: ScrollBehavior,
        currentScrollOffset: Double,
    ) {
        if (behavior.isPinned) return
        val state = behavior.state
        val limit = state.heightOffsetLimit

        if (currentScrollOffset <= 0.5) {
            state.contentOffset = 0f
            if (limit < 0f) {
                animateTopAppBarHeightOffset(behavior, 0f)
            }
            return
        }

        state.contentOffset = -currentScrollOffset.toFloat()
        if (limit < 0f) {
            animateTopAppBarHeightOffset(behavior, limit)
        }
    }

    private fun animateTopAppBarHeightOffset(
        behavior: ScrollBehavior,
        targetHeightOffset: Float,
    ) {
        val state = behavior.state
        if (abs(targetHeightOffset - topAppBarTarget) < 0.5f && topAppBarAnimJob?.isActive == true) return
        if (abs(state.heightOffset - targetHeightOffset) < 0.5f && topAppBarAnimJob?.isActive != true) {
            state.heightOffset = targetHeightOffset
            topAppBarTarget = targetHeightOffset
            return
        }
        topAppBarTarget = targetHeightOffset
        topAppBarAnimJob?.cancel()
        topAppBarAnimJob = coroutineScope.launch {
            AnimationState(initialValue = state.heightOffset).animateTo(
                targetValue = targetHeightOffset,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMediumLow,
                ),
            ) {
                state.heightOffset = value
            }
        }
    }

    private fun settleTopAppBarIfNeeded() {
        val behavior = topAppBarScrollBehavior ?: return
        if (behavior.isPinned) return
        settleJob?.cancel()
        settleJob = coroutineScope.launch {
            scrollJob?.join()
            topAppBarAnimJob?.join()
            val atTop = adapter.scrollOffset <= 0.5
            behavior.nestedScrollConnection.onPostFling(
                consumed = Velocity.Zero,
                available = Velocity(0f, if (atTop) 1f else 0f),
            )
        }
    }

    override fun sharePointerInputWithSiblings(): Boolean = true

    override fun onPointerEvent(
        pointerEvent: PointerEvent,
        pass: PointerEventPass,
        bounds: IntSize,
    ) {
        if (!interactive) return
        if (pass != PointerEventPass.Initial) return
        cachedBounds = bounds
        val changes = pointerEvent.changes
        if (changes.isEmpty()) return

        val type = pointerEvent.type
        if (type == PointerEventType.Enter || type == PointerEventType.Move) {
            val hoverChange = changes.fastFirstOrNull { !it.pressed }
            if (hoverChange != null) {
                val overStrip = isPointInHoverStrip(hoverChange.position)
                if (overStrip != isHovered) {
                    isHovered = overStrip
                    updateHighlight()
                    if (isHovered) {
                        fadeInImmediate()
                    } else if (!isDragging) {
                        scheduleFadeOutAfterInteraction()
                    }
                    invalidateDraw()
                }
            }
        } else if (type == PointerEventType.Exit) {
            if (isHovered) {
                isHovered = false
                updateHighlight()
                if (!isDragging) {
                    scheduleFadeOutAfterInteraction()
                }
                invalidateDraw()
            }
        }

        if (capturedPointerId == null) {
            val isVisibleOrHovered = !isFadeEnabled || alpha.value > 0f || isHovered
            if (!isVisibleOrHovered || !isScrollable) return

            val downChange = changes.fastFirstOrNull { change ->
                if (change.isConsumed || !change.changedToDown()) return@fastFirstOrNull false
                if (change.type == PointerType.Touch) {
                    isPointInThumb(change.position, change.type)
                } else {
                    isPointInHoverStrip(change.position)
                }
            }

            if (downChange != null) {
                settleJob?.cancel()
                capturedPointerId = downChange.id
                downChange.consume()
                fadeInImmediate()

                dragTrackLengthPx = cachedTrackLengthPx
                lastDragPointerPosition = downChange.position
                unscrolledDragDistancePx = 0.0
                pendingDragDeltaPx = 0f

                if (isPointInThumb(downChange.position, downChange.type)) {
                    isDragging = true
                    updateHighlight()
                    invalidateDraw()
                } else {
                    // Mouse track click: center thumb at cursor and allow immediate drag scrubbing.
                    isDragging = true
                    val centeredThumbOffset = (getTrackAxisPosition(downChange.position) - cachedThumbLengthPx / 2f)
                        .coerceIn(0f, maxTrackOffsetPx)
                    val fraction = if (maxTrackOffsetPx > 0f) centeredThumbOffset / maxTrackOffsetPx else 0f
                    requestScrollTo(fraction * adapter.maxScrollOffset)
                    updateHighlight()
                    invalidateDraw()
                }
            }
        } else {
            val activeChange = changes.fastFirstOrNull { it.id == capturedPointerId }
            if (activeChange == null || activeChange.isConsumed) {
                finishPointerInteraction(cancelled = true)
            } else if (!activeChange.pressed) {
                finishPointerInteraction(cancelled = false)
            } else if (activeChange.positionChanged()) {
                activeChange.consume()
                fadeInImmediate()
                val previousPosition = if (lastDragPointerPosition != Offset.Unspecified) {
                    lastDragPointerPosition
                } else {
                    activeChange.previousPosition
                }
                val deltaPx = getTrackAxisPosition(activeChange.position, 0f) -
                    getTrackAxisPosition(previousPosition, 0f)
                lastDragPointerPosition = activeChange.position
                if (deltaPx != 0f) {
                    requestDragDelta(deltaPx)
                }
            }
        }
    }

    private fun finishPointerInteraction(cancelled: Boolean) {
        capturedPointerId = null
        lastDragPointerPosition = Offset.Unspecified
        unscrolledDragDistancePx = 0.0
        pendingDragDeltaPx = 0f
        pendingTargetScrollOffset = null
        dragTrackLengthPx = 0f
        if (isDragging) {
            isDragging = false
            updateHighlight()
            if (!cancelled) {
                settleTopAppBarIfNeeded()
            }
        }
        if (cancelled && isHovered) {
            isHovered = false
            updateHighlight()
        }
        scheduleFadeOutAfterInteraction()
        invalidateDraw()
    }

    override fun onCancelPointerInput() {
        finishPointerInteraction(cancelled = true)
    }

    override fun ContentDrawScope.draw() {
        drawContent()

        val contentSize = adapter.contentSize
        val viewportSize = adapter.viewportSize
        if (contentSize <= viewportSize || viewportSize <= 0.0 || contentSize <= 0.0) {
            isScrollable = false
            cachedTrackLengthPx = 0f
            cachedThumbLengthPx = 0f
            return
        }

        val layoutDirection = requireLayoutDirection()
        val beforeTrackPaddingPx = if (isVertical) {
            trackPadding.calculateTopPadding().toPx()
        } else if (layoutDirection == LayoutDirection.Rtl) {
            trackPadding.calculateRightPadding(layoutDirection).toPx()
        } else {
            trackPadding.calculateLeftPadding(layoutDirection).toPx()
        }
        val afterTrackPaddingPx = if (isVertical) {
            trackPadding.calculateBottomPadding().toPx()
        } else if (layoutDirection == LayoutDirection.Rtl) {
            trackPadding.calculateLeftPadding(layoutDirection).toPx()
        } else {
            trackPadding.calculateRightPadding(layoutDirection).toPx()
        }

        val containerMainSizePx = if (isVertical) size.height else size.width
        val trackLengthPx = (containerMainSizePx - beforeTrackPaddingPx - afterTrackPaddingPx).coerceAtLeast(0f)
        val minThumbPx = thumbMinLength.toPx()

        if (trackLengthPx <= 0f || trackLengthPx < minThumbPx) {
            isScrollable = false
            cachedTrackLengthPx = 0f
            cachedThumbLengthPx = 0f
            return
        }

        val visiblePart = (viewportSize / contentSize).coerceIn(0.0, 1.0)
        val targetThumbLength = (trackLengthPx * visiblePart.toFloat()).coerceIn(minThumbPx, trackLengthPx)
        if (targetThumbLength >= trackLengthPx) {
            isScrollable = false
            cachedTrackLengthPx = 0f
            cachedThumbLengthPx = 0f
            return
        }

        isScrollable = true
        cachedBounds = IntSize(size.width.fastRoundToInt(), size.height.fastRoundToInt())
        cachedBeforePaddingPx = beforeTrackPaddingPx
        cachedTrackLengthPx = trackLengthPx

        val currentOffset = adapter.scrollOffset
        if (lastScrollOffset.isNaN()) {
            lastScrollOffset = currentOffset
        } else if (currentOffset != lastScrollOffset) {
            lastScrollOffset = currentOffset
            if (isFadeEnabled && !isHighlighted) {
                scheduleFadeOut(snapVisibleFirst = true)
            }
        }

        if (displayedThumbLength == 0f) {
            displayedThumbLength = targetThumbLength
            thumbLengthAnimTarget = targetThumbLength
        } else if (abs(targetThumbLength - thumbLengthAnimTarget) >= 1f) {
            thumbLengthAnimTarget = targetThumbLength
            thumbLengthAnimJob?.cancel()
            val startValue = displayedThumbLength
            thumbLengthAnimJob = coroutineScope.launch {
                animate(
                    initialValue = startValue,
                    targetValue = targetThumbLength,
                    animationSpec = tween(durationMillis = 150),
                ) { value, _ ->
                    displayedThumbLength = value
                    invalidateDraw()
                }
            }
        } else if (thumbLengthAnimJob?.isActive != true) {
            displayedThumbLength = targetThumbLength
            thumbLengthAnimTarget = targetThumbLength
        }

        val thumbLength = displayedThumbLength.coerceIn(minThumbPx, trackLengthPx)
        cachedThumbLengthPx = thumbLength

        val currentOpacity = if (isFadeEnabled) alpha.value else 1f
        if (currentOpacity <= 0f) return

        val highlight = highlightProgress.value
        val restingThumbWidthPx = thumbWidth.toPx()
        val dragThumbWidthPx = ScrollBarDefaults.DragThumbWidth.toPx()
        val thumbWidthPx = lerp(restingThumbWidthPx, dragThumbWidthPx, highlight)
        val endPaddingPx = endPadding.toPx()
        cachedThumbWidthPx = thumbWidthPx
        cachedEndPaddingPx = endPaddingPx

        val cornerRadiusPx = if (cornerRadius == Dp.Unspecified) thumbWidthPx / 2f else cornerRadius.toPx()
        val cr = CornerRadius(cornerRadiusPx)

        if (trackColor != Color.Unspecified) {
            val resolvedTrackColor = trackColor.copy(alpha = trackColor.alpha * currentOpacity)
            if (isVertical) {
                val trackX = if (layoutDirection == LayoutDirection.Rtl) {
                    endPaddingPx
                } else {
                    size.width - thumbWidthPx - endPaddingPx
                }
                drawRoundRect(
                    color = resolvedTrackColor,
                    topLeft = Offset(trackX, beforeTrackPaddingPx),
                    size = Size(thumbWidthPx, trackLengthPx),
                    cornerRadius = cr,
                )
            } else {
                val trackY = size.height - thumbWidthPx - endPaddingPx
                val trackX = if (layoutDirection == LayoutDirection.Rtl) {
                    afterTrackPaddingPx
                } else {
                    beforeTrackPaddingPx
                }
                drawRoundRect(
                    color = resolvedTrackColor,
                    topLeft = Offset(trackX, trackY),
                    size = Size(trackLengthPx, thumbWidthPx),
                    cornerRadius = cr,
                )
            }
        }

        val thumbAlpha = lerp(defaultThumbAlpha, highlightThumbAlpha, highlight) * currentOpacity
        val resolvedThumbColor = thumbColor.copy(alpha = thumbAlpha)
        val maxScroll = (contentSize - viewportSize).coerceAtLeast(0.0)
        val maxThumbOffset = (trackLengthPx - thumbLength).coerceAtLeast(0f)
        val thumbOffset = if (maxScroll > 0.0) {
            ((currentOffset / maxScroll) * maxThumbOffset).toFloat().coerceIn(0f, maxThumbOffset)
        } else {
            0f
        }

        if (isVertical) {
            val thumbX = if (layoutDirection == LayoutDirection.Rtl) {
                endPaddingPx
            } else {
                size.width - thumbWidthPx - endPaddingPx
            }
            val thumbY = beforeTrackPaddingPx + thumbOffset
            drawRoundRect(
                color = resolvedThumbColor,
                topLeft = Offset(thumbX, thumbY),
                size = Size(thumbWidthPx, thumbLength),
                cornerRadius = cr,
            )
        } else {
            val thumbY = size.height - thumbWidthPx - endPaddingPx
            val thumbX = if (layoutDirection == LayoutDirection.Rtl) {
                size.width - beforeTrackPaddingPx - thumbLength - thumbOffset
            } else {
                beforeTrackPaddingPx + thumbOffset
            }
            drawRoundRect(
                color = resolvedThumbColor,
                topLeft = Offset(thumbX, thumbY),
                size = Size(thumbLength, thumbWidthPx),
                cornerRadius = cr,
            )
        }
    }
}

@OptIn(ExperimentalScrollBarApi::class)
private fun verticalMeasurePolicy(
    endPadding: Dp,
    thumbWidth: Dp,
): MeasurePolicy = MeasurePolicy { _, constraints ->
    val stripWidth = maxOf(
        ScrollBarDefaults.TouchTargetWidth,
        maxOf(thumbWidth, ScrollBarDefaults.DragThumbWidth) + endPadding * 2,
    )
    val width = stripWidth.roundToPx().coerceAtMost(constraints.maxWidth)
    layout(width, constraints.maxHeight) {}
}

@OptIn(ExperimentalScrollBarApi::class)
private fun horizontalMeasurePolicy(
    endPadding: Dp,
    thumbWidth: Dp,
): MeasurePolicy = MeasurePolicy { _, constraints ->
    val stripHeight = maxOf(
        ScrollBarDefaults.TouchTargetWidth,
        maxOf(thumbWidth, ScrollBarDefaults.DragThumbWidth) + endPadding * 2,
    )
    val height = stripHeight.roundToPx().coerceAtMost(constraints.maxHeight)
    layout(constraints.maxWidth, height) {}
}

@OptIn(ExperimentalScrollBarApi::class)
private class ScrollableStateScrollBarAdapter(
    private val scrollState: ScrollableState,
    private val reverseLayout: Boolean = false,
) : ScrollBarAdapter {

    private val indicator: ScrollIndicatorState?
        get() = scrollState.scrollIndicatorState

    override val viewportSize: Double
        get() {
            val v = indicator?.viewportSize ?: return 0.0
            return if (v == Int.MAX_VALUE || v <= 0) 0.0 else v.toDouble()
        }

    override val contentSize: Double
        get() {
            val c = indicator?.contentSize ?: return 0.0
            return if (c == Int.MAX_VALUE || c <= 0) 0.0 else c.toDouble()
        }

    override val scrollOffset: Double
        get() {
            val o = indicator?.scrollOffset ?: return 0.0
            val max = maxScrollOffset
            return if (o == Int.MAX_VALUE || o < 0) 0.0 else o.toDouble().coerceAtMost(max)
        }

    override suspend fun scrollTo(scrollOffset: Double) {
        val max = maxScrollOffset
        if (max <= 0.0) return
        val clampedTarget = scrollOffset.coerceIn(0.0, max)

        when (scrollState) {
            is ScrollState -> {
                val targetValue = if (reverseLayout) max - clampedTarget else clampedTarget
                scrollState.scrollTo(targetValue.fastRoundToInt())
            }

            is LazyListState -> scrollLazyListTo(scrollState, clampedTarget, max)

            is LazyGridState -> scrollLazyGridTo(scrollState, clampedTarget, max)

            is LazyStaggeredGridState -> scrollLazyStaggeredGridTo(scrollState, clampedTarget, max)

            is PagerState -> {
                val reversed = scrollState.layoutInfo.reverseLayout || reverseLayout
                val visualDelta = clampedTarget - this.scrollOffset
                val logicalDelta = if (reversed) -visualDelta else visualDelta
                scrollState.scrollBy(logicalDelta.toFloat())
            }

            else -> {
                val visualDelta = clampedTarget - this.scrollOffset
                val logicalDelta = if (reverseLayout) -visualDelta else visualDelta
                scrollState.scrollBy(logicalDelta.toFloat())
            }
        }
    }

    private suspend fun scrollLazyListTo(
        state: LazyListState,
        clampedTarget: Double,
        max: Double,
    ) {
        val layoutInfo = state.layoutInfo
        val reversed = layoutInfo.reverseLayout || reverseLayout
        val visualDelta = clampedTarget - this.scrollOffset
        val logicalDelta = if (reversed) -visualDelta else visualDelta
        if (abs(logicalDelta) <= viewportSize) {
            state.scrollBy(logicalDelta.toFloat())
            return
        }

        val totalItems = layoutInfo.totalItemsCount
        if (totalItems == 0) return
        val visibleItems = layoutInfo.visibleItemsInfo
        if (visibleItems.isEmpty()) return

        val lineSpacing = layoutInfo.mainAxisItemSpacing
        val firstIdx = if (visibleItems.size > 1 &&
            (
                visibleItems[0].index < visibleItems[1].index - 1 ||
                    visibleItems[0].offset + visibleItems[0].size + lineSpacing > visibleItems[1].offset
                )
        ) {
            1
        } else {
            0
        }
        val first = visibleItems[firstIdx]
        val last = visibleItems.last()
        val count = visibleItems.size - firstIdx
        val avgSize = (last.offset + last.size - first.offset - (count - 1) * lineSpacing).toDouble() / count
        val avgWithSpacing = avgSize + lineSpacing
        if (avgWithSpacing <= 0.0) return

        val logicalTarget = if (reversed) max - clampedTarget else clampedTarget
        val index = (logicalTarget / avgWithSpacing).toInt().coerceIn(0, totalItems - 1)
        val offset = (logicalTarget - index * avgWithSpacing).toInt().coerceAtLeast(0)
        state.scrollToItem(index, offset)
    }

    private suspend fun scrollLazyGridTo(
        state: LazyGridState,
        clampedTarget: Double,
        max: Double,
    ) {
        val layoutInfo = state.layoutInfo
        val reversed = layoutInfo.reverseLayout || reverseLayout
        val visualDelta = clampedTarget - this.scrollOffset
        val logicalDelta = if (reversed) -visualDelta else visualDelta
        if (abs(logicalDelta) <= viewportSize) {
            state.scrollBy(logicalDelta.toFloat())
            return
        }

        val totalItems = layoutInfo.totalItemsCount
        if (totalItems == 0) return
        val visibleItems = layoutInfo.visibleItemsInfo
        if (visibleItems.isEmpty()) return

        val isVertical = layoutInfo.orientation == Orientation.Vertical
        val slotsPerLine = state.slotsPerLine
        val totalLines = (totalItems - 1) / slotsPerLine + 1
        val lineSpacing = layoutInfo.mainAxisItemSpacing
        val unknownLine = if (isVertical) LazyGridItemInfo.UnknownRow else LazyGridItemInfo.UnknownColumn

        var firstKnownIdx = 0
        while (firstKnownIdx < visibleItems.size) {
            val line = if (isVertical) visibleItems[firstKnownIdx].row else visibleItems[firstKnownIdx].column
            if (line != unknownLine) break
            firstKnownIdx++
        }
        if (firstKnownIdx >= visibleItems.size) return

        val first = visibleItems[firstKnownIdx]
        val last = visibleItems.last()
        val firstLine = if (isVertical) first.row else first.column
        val lastLine = if (isVertical) last.row else last.column
        val firstOffset = if (isVertical) first.offset.y else first.offset.x
        val lastOffset = if (isVertical) last.offset.y else last.offset.x
        var lastLineSize = if (isVertical) last.size.height else last.size.width
        var idx = visibleItems.lastIndex - 1
        while (idx >= firstKnownIdx) {
            val item = visibleItems[idx]
            val itemLine = if (isVertical) item.row else item.column
            if (itemLine != lastLine) break
            val itemSize = if (isVertical) item.size.height else item.size.width
            if (itemSize > lastLineSize) lastLineSize = itemSize
            idx--
        }
        val lineCount = (lastLine - firstLine + 1).coerceAtLeast(1)
        val avgLineSize = (lastOffset + lastLineSize - firstOffset - (lineCount - 1) * lineSpacing).toDouble() / lineCount
        val avgWithSpacing = avgLineSize + lineSpacing
        if (avgWithSpacing <= 0.0) return

        val logicalTarget = if (reversed) max - clampedTarget else clampedTarget
        val targetLine = (logicalTarget / avgWithSpacing).toInt().coerceIn(0, totalLines - 1)
        val offset = (logicalTarget - targetLine * avgWithSpacing).toInt().coerceAtLeast(0)
        state.scrollToItem(index = targetLine * slotsPerLine, scrollOffset = offset)
    }

    private suspend fun scrollLazyStaggeredGridTo(
        state: LazyStaggeredGridState,
        clampedTarget: Double,
        max: Double,
    ) {
        val layoutInfo = state.layoutInfo
        val reversed = layoutInfo.reverseLayout || reverseLayout
        val visualDelta = clampedTarget - this.scrollOffset
        val logicalDelta = if (reversed) -visualDelta else visualDelta
        if (abs(logicalDelta) <= viewportSize) {
            state.scrollBy(logicalDelta.toFloat())
            return
        }

        val totalItems = layoutInfo.totalItemsCount
        if (totalItems == 0) return
        val visibleItems = layoutInfo.visibleItemsInfo
        if (visibleItems.isEmpty()) return

        val isVertical = layoutInfo.orientation == Orientation.Vertical
        val avgItemSize = visibleItems.sumOf { if (isVertical) it.size.height else it.size.width }.toDouble() /
            visibleItems.size + layoutInfo.mainAxisItemSpacing
        if (avgItemSize <= 0.0) return

        val laneCount = visibleItems.maxOf { it.lane }.plus(1).coerceAtLeast(1)
        val logicalTarget = if (reversed) max - clampedTarget else clampedTarget
        val index = ((logicalTarget * laneCount) / avgItemSize).toInt().coerceIn(0, totalItems - 1)
        val estimatedRowStart = (index / laneCount) * avgItemSize
        val offset = (logicalTarget - estimatedRowStart).toInt().coerceAtLeast(0)
        state.scrollToItem(index, offset)
    }
}

private val LazyGridState.slotsPerLine: Int
    get() = with(layoutInfo) {
        var count = 0
        for (item in visibleItemsInfo) {
            val index = if (orientation == Orientation.Vertical) item.column else item.row
            if (index == -1) break
            if (count == index) count += 1 else break
        }
        count.coerceAtLeast(1)
    }
