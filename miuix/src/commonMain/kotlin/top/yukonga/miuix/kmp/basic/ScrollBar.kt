// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.abs
import kotlin.math.roundToInt

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

internal val ScrollBarAdapter.maxScrollOffset: Double
    get() = (contentSize - viewportSize).coerceAtLeast(0.0)

/**
 * Create and [remember] [ScrollBarAdapter] for [ScrollState].
 */
@ExperimentalScrollBarApi
@Composable
fun rememberScrollBarAdapter(
    scrollState: ScrollState,
): ScrollBarAdapter = remember(scrollState) {
    ScrollStateAdapter(scrollState)
}

/**
 * Create and [remember] [ScrollBarAdapter] for [LazyListState].
 */
@ExperimentalScrollBarApi
@Composable
fun rememberScrollBarAdapter(
    scrollState: LazyListState,
): ScrollBarAdapter = remember(scrollState) {
    LazyListAdapter(scrollState)
}

/**
 * Create and [remember] [ScrollBarAdapter] for [LazyGridState].
 */
@ExperimentalScrollBarApi
@Composable
fun rememberScrollBarAdapter(
    scrollState: LazyGridState,
): ScrollBarAdapter = remember(scrollState) {
    LazyGridAdapter(scrollState)
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
 * A vertical scrollbar.
 *
 * @param adapter [ScrollBarAdapter] that communicates with the scrollable component.
 * @param modifier The modifier to apply to this layout.
 * @param reverseLayout Reverse the direction of scrolling and layout.
 * @param trackPadding Padding applied to the track to skip content padding areas.
 * @param colors The colors of the scrollbar.
 * @param thumbWidth The width of the thumb.
 * @param cornerRadius The corner radius. [Dp.Unspecified] defaults to half of [thumbWidth].
 * @param thumbMinLength The minimum length of the thumb.
 * @param endPadding The padding from the end edge.
 */
@ExperimentalScrollBarApi
@Composable
fun VerticalScrollBar(
    adapter: ScrollBarAdapter,
    modifier: Modifier = Modifier,
    reverseLayout: Boolean = false,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
) {
    ScrollBar(
        adapter = adapter,
        modifier = modifier,
        isVertical = true,
        reverseLayout = reverseLayout,
        trackPadding = trackPadding,
        colors = colors,
        thumbWidth = thumbWidth,
        cornerRadius = cornerRadius,
        thumbMinLength = thumbMinLength,
        endPadding = endPadding,
    )
}

/**
 * A horizontal scrollbar.
 *
 * @param adapter [ScrollBarAdapter] that communicates with the scrollable component.
 * @param modifier The modifier to apply to this layout.
 * @param reverseLayout Reverse the direction of scrolling and layout.
 * @param trackPadding Padding applied to the track to skip content padding areas.
 * @param colors The colors of the scrollbar.
 * @param thumbWidth The width of the thumb.
 * @param cornerRadius The corner radius. [Dp.Unspecified] defaults to half of [thumbWidth].
 * @param thumbMinLength The minimum length of the thumb.
 * @param endPadding The padding from the end edge.
 */
@ExperimentalScrollBarApi
@Composable
fun HorizontalScrollBar(
    adapter: ScrollBarAdapter,
    modifier: Modifier = Modifier,
    reverseLayout: Boolean = false,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
) {
    ScrollBar(
        adapter = adapter,
        modifier = modifier,
        isVertical = false,
        reverseLayout = reverseLayout,
        trackPadding = trackPadding,
        colors = colors,
        thumbWidth = thumbWidth,
        cornerRadius = cornerRadius,
        thumbMinLength = thumbMinLength,
        endPadding = endPadding,
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
    val TouchTargetWidth: Dp = 48.dp
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
private fun ScrollBar(
    adapter: ScrollBarAdapter,
    isVertical: Boolean,
    modifier: Modifier = Modifier,
    reverseLayout: Boolean = false,
    trackPadding: PaddingValues = PaddingValues(0.dp),
    colors: ScrollBarColors = ScrollBarDefaults.scrollBarColors(),
    thumbWidth: Dp = ScrollBarDefaults.ThumbWidth,
    cornerRadius: Dp = ScrollBarDefaults.CornerRadius,
    thumbMinLength: Dp = ScrollBarDefaults.ThumbMinLength,
    endPadding: Dp = ScrollBarDefaults.EndPadding,
) {
    val density = LocalDensity.current
    val baseThumbColor = if (colors.thumbColor != Color.Unspecified) colors.thumbColor else MiuixTheme.colorScheme.onSurface

    val minThumbPx = with(density) { thumbMinLength.toPx() }
    val coroutineScope = rememberCoroutineScope()

    val beforeTrackPaddingPx = with(density) {
        if (isVertical) {
            trackPadding.calculateTopPadding()
                .toPx()
        } else {
            trackPadding.calculateLeftPadding(androidx.compose.ui.unit.LayoutDirection.Ltr).toPx()
        }
    }
    val afterTrackPaddingPx = with(density) {
        if (isVertical) {
            trackPadding.calculateBottomPadding()
                .toPx()
        } else {
            trackPadding.calculateRightPadding(androidx.compose.ui.unit.LayoutDirection.Ltr).toPx()
        }
    }
    val totalTrackPaddingPx = beforeTrackPaddingPx + afterTrackPaddingPx

    var containerSize by remember { mutableIntStateOf(0) }

    val sliderAdapter = remember(adapter, minThumbPx, reverseLayout, isVertical, coroutineScope) {
        SliderAdapter(adapter, 0, minThumbPx, reverseLayout, isVertical, coroutineScope)
    }
    LaunchedEffect(containerSize, totalTrackPaddingPx) {
        sliderAdapter.trackSize = (containerSize - totalTrackPaddingPx.roundToInt()).coerceAtLeast(0)
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    var opacity by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    var hideJob by remember { mutableStateOf<Job?>(null) }
    val isHighlighted = isHovered || isDragging

    val highlightAnimSpec = tween<Float>(ScrollBarDefaults.DragAnimationDurationMillis)
    val animatedThumbWidthPx by animateFloatAsState(
        targetValue = with(density) { if (isHighlighted) ScrollBarDefaults.DragThumbWidth.toPx() else thumbWidth.toPx() },
        animationSpec = highlightAnimSpec,
    )
    val defaultAlpha = if (colors.thumbColor != Color.Unspecified) colors.thumbColor.alpha else ScrollBarDefaults.ThumbAlpha
    val highlightAlpha = if (colors.thumbColor != Color.Unspecified) colors.thumbColor.alpha else ScrollBarDefaults.DragThumbAlpha
    val animatedThumbAlpha by animateFloatAsState(
        targetValue = if (isHighlighted) highlightAlpha else defaultAlpha,
        animationSpec = highlightAnimSpec,
    )

    var displayedThumbLength by remember { mutableFloatStateOf(0f) }
    var thumbLengthAnimJob by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(isHighlighted) {
        if (isHighlighted) {
            hideJob?.cancel()
            opacity = 1f
        } else if (opacity > 0f) {
            hideJob?.cancel()
            hideJob = coroutineScope.launch {
                delay(ScrollBarDefaults.FadeDelayMillis.toLong())
                animate(
                    initialValue = 1f,
                    targetValue = 0f,
                    animationSpec = tween(ScrollBarDefaults.FadeDurationMillis),
                ) { value, _ ->
                    opacity = value
                }
            }
        }
    }

    LaunchedEffect(adapter) {
        snapshotFlow { adapter.scrollOffset }
            .drop(1)
            .collect {
                opacity = 1f
                if (!isHighlighted) {
                    hideJob?.cancel()
                    hideJob = coroutineScope.launch {
                        delay(ScrollBarDefaults.FadeDelayMillis.toLong())
                        animate(
                            initialValue = 1f,
                            targetValue = 0f,
                            animationSpec = tween(ScrollBarDefaults.FadeDurationMillis),
                        ) { value, _ ->
                            opacity = value
                        }
                    }
                }
            }
    }

    val measurePolicy = if (isVertical) {
        verticalMeasurePolicy(
            setContainerSize = { containerSize = it },
            density = density,
            endPadding = endPadding,
            thumbWidth = thumbWidth,
        )
    } else {
        horizontalMeasurePolicy(
            setContainerSize = { containerSize = it },
            density = density,
            endPadding = endPadding,
            thumbWidth = thumbWidth,
        )
    }

    Layout(
        content = {},
        modifier = modifier
            .hoverable(interactionSource = interactionSource)
            .pointerInput(isVertical, sliderAdapter, beforeTrackPaddingPx) {
                val touchTargetPx = ScrollBarDefaults.TouchTargetWidth.toPx()

                awaitEachGesture {
                    val down = awaitFirstDown(requireUnconsumed = false)

                    val inStrip = if (isVertical) {
                        down.position.x >= size.width - touchTargetPx
                    } else {
                        down.position.y >= size.height - touchTargetPx
                    }
                    if (!inStrip) return@awaitEachGesture

                    val thumbRange = sliderAdapter.thumbPixelRange
                    val touchPos = if (isVertical) down.position.y else down.position.x
                    val adjustedTouchPos = touchPos - beforeTrackPaddingPx
                    if (adjustedTouchPos < thumbRange.first || adjustedTouchPos > thumbRange.last) {
                        return@awaitEachGesture
                    }

                    isDragging = true
                    opacity = 1f
                    hideJob?.cancel()
                    down.consume()

                    sliderAdapter.onDragStarted()

                    drag(down.id) { change ->
                        sliderAdapter.onDragDelta(change.positionChange())
                        change.consume()
                    }

                    isDragging = false
                }
            }
            .drawBehind {
                if (containerSize == 0 || opacity <= 0f) return@drawBehind
                if (sliderAdapter.thumbSize >= sliderAdapter.trackSize) return@drawBehind

                val thumbWidthPx = animatedThumbWidthPx
                val endPaddingPx = endPadding.toPx()
                val cornerRadiusPx = if (cornerRadius == Dp.Unspecified) thumbWidthPx / 2 else cornerRadius.toPx()
                val cr = CornerRadius(cornerRadiusPx)

                if (colors.trackColor != Color.Unspecified) {
                    val trackColor = colors.trackColor.copy(alpha = colors.trackColor.alpha * opacity)
                    if (isVertical) {
                        drawRoundRect(
                            color = trackColor,
                            topLeft = Offset(size.width - thumbWidthPx - endPaddingPx, beforeTrackPaddingPx),
                            size = Size(thumbWidthPx, size.height - totalTrackPaddingPx),
                            cornerRadius = cr,
                        )
                    } else {
                        drawRoundRect(
                            color = trackColor,
                            topLeft = Offset(beforeTrackPaddingPx, size.height - thumbWidthPx - endPaddingPx),
                            size = Size(size.width - totalTrackPaddingPx, thumbWidthPx),
                            cornerRadius = cr,
                        )
                    }
                }

                val targetThumbLength = sliderAdapter.thumbSize.toFloat()
                if (displayedThumbLength == 0f) {
                    displayedThumbLength = targetThumbLength
                } else if (abs(targetThumbLength - displayedThumbLength) >= 1f) {
                    if (thumbLengthAnimJob?.isActive != true) {
                        val startValue = displayedThumbLength
                        thumbLengthAnimJob = coroutineScope.launch {
                            animate(
                                initialValue = startValue,
                                targetValue = targetThumbLength,
                                animationSpec = tween(durationMillis = 150),
                            ) { value, _ ->
                                displayedThumbLength = value
                            }
                        }
                    }
                } else {
                    displayedThumbLength = targetThumbLength
                }

                val color = baseThumbColor.copy(alpha = animatedThumbAlpha * opacity)
                val thumbLength = displayedThumbLength
                val thumbOffset = beforeTrackPaddingPx + sliderAdapter.position.toFloat()

                if (isVertical) {
                    drawRoundRect(
                        color = color,
                        topLeft = Offset(size.width - thumbWidthPx - endPaddingPx, thumbOffset),
                        size = Size(thumbWidthPx, thumbLength),
                        cornerRadius = cr,
                    )
                } else {
                    drawRoundRect(
                        color = color,
                        topLeft = Offset(thumbOffset, size.height - thumbWidthPx - endPaddingPx),
                        size = Size(thumbLength, thumbWidthPx),
                        cornerRadius = cr,
                    )
                }
            },
        measurePolicy = measurePolicy,
    )
}

private fun verticalMeasurePolicy(
    setContainerSize: (Int) -> Unit,
    density: androidx.compose.ui.unit.Density,
    endPadding: Dp,
    thumbWidth: Dp,
) = androidx.compose.ui.layout.MeasurePolicy { _, constraints ->
    setContainerSize(constraints.maxHeight)
    val width = with(density) {
        (thumbWidth + endPadding * 2).roundToPx()
    }.coerceAtMost(constraints.maxWidth)
    layout(width, constraints.maxHeight) {}
}

private fun horizontalMeasurePolicy(
    setContainerSize: (Int) -> Unit,
    density: androidx.compose.ui.unit.Density,
    endPadding: Dp,
    thumbWidth: Dp,
) = androidx.compose.ui.layout.MeasurePolicy { _, constraints ->
    setContainerSize(constraints.maxWidth)
    val height = with(density) {
        (thumbWidth + endPadding * 2).roundToPx()
    }.coerceAtMost(constraints.maxHeight)
    layout(constraints.maxWidth, height) {}
}

@OptIn(ExperimentalScrollBarApi::class)
@Stable
private class SliderAdapter(
    val adapter: ScrollBarAdapter,
    var trackSize: Int,
    private val minThumbSize: Float,
    private val reverseLayout: Boolean,
    private val isVertical: Boolean,
    private val coroutineScope: CoroutineScope,
) {

    private val contentSize get() = adapter.contentSize

    private val visiblePart: Double
        get() {
            val cs = contentSize
            return if (cs == 0.0) 1.0 else (adapter.viewportSize / cs).coerceAtMost(1.0)
        }

    val thumbSize: Double
        get() = (trackSize * visiblePart).coerceAtLeast(minThumbSize.toDouble())

    private val scrollScale: Double
        get() {
            val extraBar = trackSize - thumbSize
            val extraContent = adapter.maxScrollOffset
            return if (extraContent == 0.0) 1.0 else extraBar / extraContent
        }

    private val rawPosition: Double
        get() = scrollScale * adapter.scrollOffset

    val position: Double
        get() = if (reverseLayout) trackSize - thumbSize - rawPosition else rawPosition

    val bounds get() = position..position + thumbSize

    private var unscrolledDragDistance = 0.0
    private val dragMutex = Mutex()

    fun onDragStarted() {
        unscrolledDragDistance = 0.0
    }

    fun onDragDelta(offset: Offset) {
        coroutineScope.launch(start = CoroutineStart.UNDISPATCHED) {
            dragMutex.withLock {
                val dragDelta = if (isVertical) offset.y else offset.x
                val maxPosition = adapter.maxScrollOffset * scrollScale
                val currentPosition = position
                val targetPosition =
                    (currentPosition + dragDelta + unscrolledDragDistance).coerceIn(0.0, maxPosition)
                val sliderDelta = targetPosition - currentPosition

                setPosition(currentPosition + sliderDelta)
                unscrolledDragDistance += dragDelta - sliderDelta
            }
        }
    }

    private suspend fun setPosition(value: Double) {
        val rawPosition = if (reverseLayout) trackSize - thumbSize - value else value
        adapter.scrollTo(rawPosition / scrollScale)
    }
}

private val SliderAdapter.thumbPixelRange: IntRange
    get() {
        val start = position.roundToInt()
        val endExclusive = start + thumbSize.roundToInt()
        return start until endExclusive
    }

@OptIn(ExperimentalScrollBarApi::class)
private class ScrollStateAdapter(
    private val scrollState: ScrollState,
) : ScrollBarAdapter {

    override val scrollOffset: Double get() = scrollState.value.toDouble()

    override val contentSize: Double get() = scrollState.maxValue + viewportSize

    override val viewportSize: Double get() = scrollState.viewportSize.toDouble()

    override suspend fun scrollTo(scrollOffset: Double) {
        scrollState.scrollTo(scrollOffset.roundToInt())
    }
}

@OptIn(ExperimentalScrollBarApi::class)
private abstract class LazyLineContentAdapter : ScrollBarAdapter {

    data class VisibleLine(val index: Int, val offset: Int)

    abstract fun firstVisibleLine(): VisibleLine?
    abstract fun totalLineCount(): Int
    abstract fun contentPadding(): Int
    abstract suspend fun snapToLine(lineIndex: Int, scrollOffset: Int)
    abstract suspend fun scrollBy(value: Float)
    abstract fun averageVisibleLineSize(): Double
    abstract val lineSpacing: Int

    private val averageVisibleLineSize: Double
        get() = if (totalLineCount() == 0) 0.0 else averageVisibleLineSize()

    private val averageVisibleLineSizeWithSpacing: Double
        get() = averageVisibleLineSize + lineSpacing

    override val scrollOffset: Double
        get() {
            val firstVisibleLine = firstVisibleLine()
            return if (firstVisibleLine == null) {
                0.0
            } else {
                firstVisibleLine.index * averageVisibleLineSizeWithSpacing - firstVisibleLine.offset
            }
        }

    override val contentSize: Double
        get() {
            val totalLineCount = totalLineCount()
            return averageVisibleLineSize * totalLineCount +
                lineSpacing * (totalLineCount - 1).coerceAtLeast(0) +
                contentPadding()
        }

    override suspend fun scrollTo(scrollOffset: Double) {
        val distance = scrollOffset - this.scrollOffset
        if (abs(distance) <= viewportSize) {
            scrollBy(distance.toFloat())
        } else {
            snapTo(scrollOffset)
        }
    }

    private suspend fun snapTo(scrollOffset: Double) {
        val scrollOffsetCoerced = scrollOffset.coerceIn(0.0, maxScrollOffset)
        val avgWithSpacing = averageVisibleLineSizeWithSpacing
        if (avgWithSpacing <= 0.0) return

        val index = (scrollOffsetCoerced / avgWithSpacing)
            .toInt()
            .coerceAtLeast(0)
            .coerceAtMost(totalLineCount() - 1)

        val offset = (scrollOffsetCoerced - index * avgWithSpacing)
            .toInt()
            .coerceAtLeast(0)

        snapToLine(lineIndex = index, scrollOffset = offset)
    }
}

private class LazyListAdapter(
    private val scrollState: LazyListState,
) : LazyLineContentAdapter() {

    override val viewportSize: Double
        get() = with(scrollState.layoutInfo) {
            if (orientation == Orientation.Vertical) viewportSize.height else viewportSize.width
        }.toDouble()

    override val lineSpacing: Int get() = scrollState.layoutInfo.mainAxisItemSpacing

    private fun firstFloatingVisibleItemIndex(): Int? = with(scrollState.layoutInfo.visibleItemsInfo) {
        when (size) {
            0 -> null

            1 -> 0

            else -> {
                val first = this[0]
                val second = this[1]
                if ((first.index < second.index - 1) ||
                    (first.offset + first.size + lineSpacing > second.offset)
                ) {
                    1
                } else {
                    0
                }
            }
        }
    }

    override fun firstVisibleLine(): VisibleLine? {
        val idx = firstFloatingVisibleItemIndex() ?: return null
        val item = scrollState.layoutInfo.visibleItemsInfo[idx]
        return VisibleLine(index = item.index, offset = item.offset)
    }

    override fun totalLineCount(): Int = scrollState.layoutInfo.totalItemsCount

    override fun contentPadding(): Int = with(scrollState.layoutInfo) {
        beforeContentPadding + afterContentPadding
    }

    override suspend fun snapToLine(lineIndex: Int, scrollOffset: Int) {
        scrollState.scrollToItem(lineIndex, scrollOffset)
    }

    override suspend fun scrollBy(value: Float) {
        scrollState.scrollBy(value)
    }

    override fun averageVisibleLineSize(): Double {
        return with(scrollState.layoutInfo.visibleItemsInfo) {
            val firstIdx = firstFloatingVisibleItemIndex() ?: return@with 0.0
            val first = this[firstIdx]
            val last = last()
            val count = size - firstIdx
            (last.offset + last.size - first.offset - (count - 1) * lineSpacing).toDouble() / count
        }
    }
}

private class LazyGridAdapter(
    private val scrollState: LazyGridState,
) : LazyLineContentAdapter() {

    private val isVertical = scrollState.layoutInfo.orientation == Orientation.Vertical

    override val viewportSize: Double
        get() = with(scrollState.layoutInfo) {
            if (orientation == Orientation.Vertical) viewportSize.height else viewportSize.width
        }.toDouble()

    override val lineSpacing: Int get() = scrollState.layoutInfo.mainAxisItemSpacing

    private val unknownLine: Int
        get() = with(LazyGridItemInfo) {
            if (isVertical) UnknownRow else UnknownColumn
        }

    private fun LazyGridItemInfo.line(): Int = if (isVertical) row else column

    private fun LazyGridItemInfo.mainAxisSize(): Int = if (isVertical) size.height else size.width

    private fun LazyGridItemInfo.mainAxisOffset(): Int = if (isVertical) offset.y else offset.x

    private fun lineOfIndex(index: Int) = index / scrollState.slotsPerLine

    private fun indexOfFirstInLine(line: Int) = line * scrollState.slotsPerLine

    override fun firstVisibleLine(): VisibleLine? {
        val items = scrollState.layoutInfo.visibleItemsInfo
        val firstItem = items.firstOrNull { it.line() != unknownLine } ?: return null
        return VisibleLine(index = firstItem.line(), offset = firstItem.mainAxisOffset())
    }

    override fun totalLineCount(): Int {
        val itemCount = scrollState.layoutInfo.totalItemsCount
        return if (itemCount == 0) 0 else lineOfIndex(itemCount - 1) + 1
    }

    override fun contentPadding(): Int = with(scrollState.layoutInfo) {
        beforeContentPadding + afterContentPadding
    }

    override suspend fun snapToLine(lineIndex: Int, scrollOffset: Int) {
        scrollState.scrollToItem(
            index = indexOfFirstInLine(lineIndex),
            scrollOffset = scrollOffset,
        )
    }

    override suspend fun scrollBy(value: Float) {
        scrollState.scrollBy(value)
    }

    override fun averageVisibleLineSize(): Double {
        val items = scrollState.layoutInfo.visibleItemsInfo
        val firstKnownIdx = items.indexOfFirst { it.line() != unknownLine }
        if (firstKnownIdx == -1) return 0.0
        val realItems = items.subList(firstKnownIdx, items.size)

        val lastLine = realItems.last().line()
        val lastLineSize = realItems.asReversed().asSequence()
            .takeWhile { it.line() == lastLine }
            .maxOf { it.mainAxisSize() }

        val first = realItems.first()
        val last = realItems.last()
        val lineCount = last.line() - first.line() + 1
        val spacingSum = (lineCount - 1) * lineSpacing
        return (last.mainAxisOffset() + lastLineSize - first.mainAxisOffset() - spacingSum).toDouble() / lineCount
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
