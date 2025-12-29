// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline.Generic
import androidx.compose.ui.graphics.Outline.Rectangle
import androidx.compose.ui.graphics.Outline.Rounded
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.abs
import kotlin.math.min

private fun PopupPositionProvider.Align.resolve(layoutDirection: LayoutDirection): PopupPositionProvider.Align {
    if (layoutDirection == LayoutDirection.Ltr) return this
    return when (this) {
        PopupPositionProvider.Align.Start -> PopupPositionProvider.Align.End
        PopupPositionProvider.Align.End -> PopupPositionProvider.Align.Start
        PopupPositionProvider.Align.TopStart -> PopupPositionProvider.Align.TopEnd
        PopupPositionProvider.Align.TopEnd -> PopupPositionProvider.Align.TopStart
        PopupPositionProvider.Align.BottomStart -> PopupPositionProvider.Align.BottomEnd
        PopupPositionProvider.Align.BottomEnd -> PopupPositionProvider.Align.BottomStart
    }
}

private const val MAX_ITEMS_FOR_WIDTH = 8
private const val MAX_ITEMS_FOR_HEIGHT = 8

/**
 * A column that automatically aligns the width to the widest item
 * @param content The items
 */
@Composable
fun ListPopupColumn(
    content: @Composable () -> Unit,
) {
    val scrollState = rememberScrollState()

    val measurePolicy = remember {
        object : MeasurePolicy {
            override fun MeasureScope.measure(
                measurables: List<Measurable>,
                constraints: Constraints,
            ): MeasureResult {
                var maxWidth = 0
                measurables.take(min(MAX_ITEMS_FOR_WIDTH, measurables.size)).forEach { measurable ->
                    val w = measurable.maxIntrinsicWidth(constraints.maxHeight)
                    if (w > maxWidth) maxWidth = w
                }
                val listWidth = maxWidth.coerceIn(200.dp.roundToPx(), 288.dp.roundToPx())

                val childConstraints = constraints.copy(minWidth = listWidth, maxWidth = listWidth, minHeight = 0)

                val placeables = ArrayList<Placeable>(measurables.size)
                measurables.forEach { measurable ->
                    val p = measurable.measure(childConstraints)
                    placeables.add(p)
                }
                val listHeight = placeables.sumOf { it.height }

                return layout(listWidth, listHeight) {
                    var currentY = 0
                    placeables.forEach { p ->
                        p.placeRelative(0, currentY)
                        currentY += p.height
                    }
                }
            }

            override fun IntrinsicMeasureScope.minIntrinsicHeight(
                measurables: List<IntrinsicMeasurable>,
                width: Int,
            ): Int {
                var maxWidth = 0
                measurables.take(min(MAX_ITEMS_FOR_WIDTH, measurables.size)).forEach { measurable ->
                    val w = measurable.maxIntrinsicWidth(Int.MAX_VALUE)
                    if (w > maxWidth) maxWidth = w
                }
                val listWidth = maxWidth.coerceIn(200.dp.roundToPx(), 288.dp.roundToPx())

                var height = 0
                measurables.take(min(MAX_ITEMS_FOR_HEIGHT, measurables.size)).forEach { m ->
                    height += m.minIntrinsicHeight(listWidth)
                }
                return height
            }
        }
    }

    Layout(
        content = content,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .verticalScroll(scrollState),
        measurePolicy = measurePolicy,
    )
}

@Stable
interface PopupPositionProvider {
    /**
     * Calculate the position (offset) of Popup
     *
     * @param anchorBounds Bounds of the anchored (parent) component
     * @param windowBounds Bounds of the safe area of window (excluding the [WindowInsets.Companion.statusBars], [WindowInsets.Companion.navigationBars] and [WindowInsets.Companion.captionBar])
     * @param layoutDirection [LayoutDirection]
     * @param popupContentSize Actual size of the popup content
     * @param popupMargin (Extra) Margins for the popup content. See [PopupPositionProvider.getMargins]
     * @param alignment Alignment of the popup (relative to the window). See [PopupPositionProvider.Align]
     */
    fun calculatePosition(
        anchorBounds: IntRect,
        windowBounds: IntRect,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
        popupMargin: IntRect,
        alignment: Align,
    ): IntOffset

    /**
     * (Extra) Margins for the popup content.
     */
    fun getMargins(): PaddingValues

    /**
     * Position relative to the window, not relative to the anchor!
     */
    enum class Align {
        Start,
        End,
        TopStart,
        TopEnd,
        BottomStart,
        BottomEnd,
    }
}

object ListPopupDefaults {
    val DropdownPositionProvider = object : PopupPositionProvider {
        override fun calculatePosition(
            anchorBounds: IntRect,
            windowBounds: IntRect,
            layoutDirection: LayoutDirection,
            popupContentSize: IntSize,
            popupMargin: IntRect,
            alignment: PopupPositionProvider.Align,
        ): IntOffset {
            val offsetX = if (alignment.resolve(layoutDirection) == PopupPositionProvider.Align.End) {
                anchorBounds.right - popupContentSize.width - popupMargin.right
            } else {
                anchorBounds.left + popupMargin.left
            }
            val offsetY = if (windowBounds.bottom - anchorBounds.bottom > popupContentSize.height) {
                // Show below
                anchorBounds.bottom + popupMargin.bottom
            } else if (anchorBounds.top - windowBounds.top > popupContentSize.height) {
                // Show above
                anchorBounds.top - popupContentSize.height - popupMargin.top
            } else {
                // Middle
                anchorBounds.top + anchorBounds.height / 2 - popupContentSize.height / 2
            }
            return IntOffset(
                x = offsetX.coerceIn(
                    windowBounds.left,
                    (windowBounds.right - popupContentSize.width - popupMargin.right).coerceAtLeast(windowBounds.left),
                ),
                y = offsetY.coerceIn(
                    (windowBounds.top + popupMargin.top).coerceAtMost(windowBounds.bottom - popupContentSize.height - popupMargin.bottom),
                    windowBounds.bottom - popupContentSize.height - popupMargin.bottom,
                ),
            )
        }

        override fun getMargins(): PaddingValues = PaddingValues(horizontal = 0.dp, vertical = 8.dp)
    }
    val ContextMenuPositionProvider = object : PopupPositionProvider {
        override fun calculatePosition(
            anchorBounds: IntRect,
            windowBounds: IntRect,
            layoutDirection: LayoutDirection,
            popupContentSize: IntSize,
            popupMargin: IntRect,
            alignment: PopupPositionProvider.Align,
        ): IntOffset {
            val offsetX: Int
            val offsetY: Int
            when (alignment.resolve(layoutDirection)) {
                PopupPositionProvider.Align.TopStart -> {
                    offsetX = anchorBounds.left + popupMargin.left
                    offsetY = anchorBounds.bottom + popupMargin.top
                }

                PopupPositionProvider.Align.TopEnd -> {
                    offsetX = anchorBounds.right - popupContentSize.width - popupMargin.right
                    offsetY = anchorBounds.bottom + popupMargin.top
                }

                PopupPositionProvider.Align.BottomStart -> {
                    offsetX = anchorBounds.left + popupMargin.left
                    offsetY = anchorBounds.top - popupContentSize.height - popupMargin.bottom
                }

                PopupPositionProvider.Align.BottomEnd -> {
                    offsetX = anchorBounds.right - popupContentSize.width - popupMargin.right
                    offsetY = anchorBounds.top - popupContentSize.height - popupMargin.bottom
                }

                else -> {
                    // Fallback
                    offsetX = if (alignment.resolve(layoutDirection) == PopupPositionProvider.Align.End) {
                        anchorBounds.right - popupContentSize.width - popupMargin.right
                    } else {
                        anchorBounds.left + popupMargin.left
                    }
                    offsetY = if (windowBounds.bottom - anchorBounds.bottom > popupContentSize.height) {
                        // Show below
                        anchorBounds.bottom + popupMargin.bottom
                    } else if (anchorBounds.top - windowBounds.top > popupContentSize.height) {
                        // Show above
                        anchorBounds.top - popupContentSize.height - popupMargin.top
                    } else {
                        // Middle
                        anchorBounds.top + anchorBounds.height / 2 - popupContentSize.height / 2
                    }
                }
            }
            return IntOffset(
                x = offsetX.coerceIn(
                    windowBounds.left,
                    (windowBounds.right - popupContentSize.width - popupMargin.right).coerceAtLeast(windowBounds.left),
                ),
                y = offsetY.coerceIn(
                    (windowBounds.top + popupMargin.top).coerceAtMost(windowBounds.bottom - popupContentSize.height - popupMargin.bottom),
                    windowBounds.bottom - popupContentSize.height - popupMargin.bottom,
                ),
            )
        }

        override fun getMargins(): PaddingValues = PaddingValues(horizontal = 20.dp, vertical = 0.dp)
    }
}

/**
 * Ensure TransformOrigin is available.
 */
fun safeTransformOrigin(x: Float, y: Float): TransformOrigin {
    val safeX = if (x.isNaN() || x < 0f) 0f else x
    val safeY = if (y.isNaN() || y < 0f) 0f else y
    return TransformOrigin(safeX, safeY)
}

data class ListPopupLayoutInfo(
    val windowBounds: IntRect,
    val popupMargin: IntRect,
    val effectiveTransformOrigin: TransformOrigin,
    val localTransformOrigin: TransformOrigin,
    val popupLayoutInfo: Triple<Boolean, Boolean, Boolean>,
)

@Composable
fun rememberListPopupLayoutInfo(
    alignment: PopupPositionProvider.Align,
    popupPositionProvider: PopupPositionProvider,
    parentBounds: IntRect,
    popupContentSize: IntSize,
): ListPopupLayoutInfo {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val layoutDirection = LocalLayoutDirection.current
    val displayCutout = WindowInsets.displayCutout
    val statusBars = WindowInsets.statusBars
    val navigationBars = WindowInsets.navigationBars
    val captionBar = WindowInsets.captionBar

    val popupMargin = remember(layoutDirection, density, popupPositionProvider) {
        with(density) {
            IntRect(
                left = popupPositionProvider.getMargins().calculateLeftPadding(layoutDirection).roundToPx(),
                top = popupPositionProvider.getMargins().calculateTopPadding().roundToPx(),
                right = popupPositionProvider.getMargins().calculateRightPadding(layoutDirection).roundToPx(),
                bottom = popupPositionProvider.getMargins().calculateBottomPadding().roundToPx(),
            )
        }
    }

    val windowBounds = remember(
        layoutDirection,
        density,
        displayCutout,
        statusBars,
        navigationBars,
        captionBar,
    ) {
        with(density) {
            IntRect(
                left = displayCutout.getLeft(this, layoutDirection),
                top = statusBars.getTop(this),
                right = windowInfo.containerSize.width - displayCutout.getRight(this, layoutDirection),
                bottom = windowInfo.containerSize.height - navigationBars.getBottom(this) - captionBar.getBottom(this),
            )
        }
    }

    val predictedTransformOrigin = remember(alignment, popupMargin, parentBounds, layoutDirection) {
        val xInWindow = when (alignment.resolve(layoutDirection)) {
            PopupPositionProvider.Align.End,
            PopupPositionProvider.Align.TopEnd,
            PopupPositionProvider.Align.BottomEnd,
            -> parentBounds.right - popupMargin.right

            else -> parentBounds.left + popupMargin.left
        }
        val yInWindow = when (alignment.resolve(layoutDirection)) {
            PopupPositionProvider.Align.BottomEnd, PopupPositionProvider.Align.BottomStart ->
                parentBounds.top - popupMargin.bottom

            else ->
                parentBounds.bottom + popupMargin.bottom
        }
        safeTransformOrigin(
            xInWindow / windowInfo.containerSize.width.toFloat(),
            yInWindow / windowInfo.containerSize.height.toFloat(),
        )
    }

    val calculatedOffset = remember(
        popupContentSize,
        windowBounds,
        parentBounds,
        alignment,
        layoutDirection,
        popupMargin,
        popupPositionProvider,
    ) {
        if (popupContentSize == IntSize.Zero) {
            IntOffset.Zero
        } else {
            popupPositionProvider.calculatePosition(
                parentBounds,
                windowBounds,
                layoutDirection,
                popupContentSize,
                popupMargin,
                alignment,
            )
        }
    }

    val popupLayoutInfo = remember(
        popupContentSize,
        windowBounds,
        parentBounds,
        alignment,
        calculatedOffset,
        layoutDirection,
    ) {
        if (popupContentSize == IntSize.Zero) {
            val isRightAligned = when (alignment.resolve(layoutDirection)) {
                PopupPositionProvider.Align.End,
                PopupPositionProvider.Align.TopEnd,
                PopupPositionProvider.Align.BottomEnd,
                -> true

                else -> false
            }
            Triple(false, false, isRightAligned)
        } else {
            val popupCenterY = calculatedOffset.y + popupContentSize.height / 2
            val anchorCenterY = parentBounds.top + parentBounds.height / 2
            val showBelow = popupCenterY > anchorCenterY
            val showAbove = popupCenterY < anchorCenterY

            val distLeft = abs(calculatedOffset.x - parentBounds.left)
            val distRight = abs((calculatedOffset.x + popupContentSize.width) - parentBounds.right)
            val isRightAligned = distRight < distLeft

            Triple(showBelow, showAbove, isRightAligned)
        }
    }

    val effectiveTransformOrigin = remember(
        popupContentSize,
        alignment,
        layoutDirection,
        popupMargin,
        parentBounds,
        windowBounds,
        popupPositionProvider,
        calculatedOffset,
        popupLayoutInfo,
    ) {
        if (popupContentSize == IntSize.Zero) {
            predictedTransformOrigin
        } else {
            val (showBelow, showAbove, isRightAligned) = popupLayoutInfo
            val cornerX = if (isRightAligned) {
                (calculatedOffset.x + popupContentSize.width).toFloat()
            } else {
                calculatedOffset.x.toFloat()
            }

            val showMiddle = !showBelow && !showAbove
            val topLeftY = calculatedOffset.y
            val cornerY = when {
                showMiddle -> (topLeftY + popupContentSize.height / 2f)
                showBelow -> topLeftY.toFloat()
                showAbove -> (topLeftY + popupContentSize.height).toFloat()
                else -> topLeftY.toFloat()
            }

            safeTransformOrigin(
                cornerX / windowInfo.containerSize.width.toFloat(),
                cornerY / windowInfo.containerSize.height.toFloat(),
            )
        }
    }

    val localTransformOrigin = remember(popupLayoutInfo) {
        val (showBelow, showAbove, isRightAligned) = popupLayoutInfo
        val showMiddle = !showBelow && !showAbove

        TransformOrigin(
            pivotFractionX = if (isRightAligned) 1f else 0f,
            pivotFractionY = when {
                showMiddle -> 0.5f
                showBelow -> 0f
                showAbove -> 1f
                else -> 0f
            },
        )
    }

    return ListPopupLayoutInfo(
        windowBounds = windowBounds,
        popupMargin = popupMargin,
        effectiveTransformOrigin = effectiveTransformOrigin,
        localTransformOrigin = localTransformOrigin,
        popupLayoutInfo = popupLayoutInfo,
    )
}

@Composable
fun ListPopupContent(
    popupContentSize: IntSize,
    onPopupContentSizeChange: (IntSize) -> Unit,
    animationProgress: () -> Float,
    popupLayoutInfo: Triple<Boolean, Boolean, Boolean>,
    localTransformOrigin: TransformOrigin,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current

    val shape = ContinuousRoundedRectangle(16.dp)

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                val size = coordinates.size
                if (popupContentSize != size) onPopupContentSizeChange(size)
            }
            .graphicsLayer {
                val progress = animationProgress()
                val scale = 0.15f + 0.85f * progress
                scaleX = scale
                scaleY = scale
                alpha = progress
                transformOrigin = localTransformOrigin
            },
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    this.shape = shape
                    clip = true
                }
                .drawWithContent {
                    val progress = animationProgress()
                    val (showBelow, showAbove, _) = popupLayoutInfo
                    val size = this.size

                    val clipTop = when {
                        showAbove -> size.height * (1f - progress)
                        else -> 0f
                    }
                    val clipBottom = when {
                        showBelow -> size.height * progress
                        showAbove -> size.height
                        else -> size.height * (0.5f + 0.5f * progress)
                    }
                    val clipStart = if (!showBelow && !showAbove) size.height * (0.5f - 0.5f * progress) else clipTop
                    val currentHeight = clipBottom - clipStart

                    if (currentHeight > 0f) {
                        val visibleSize = Size(size.width, currentHeight)
                        val outline = shape.createOutline(visibleSize, layoutDirection, density)

                        translate(top = clipStart) {
                            val path = when (outline) {
                                is Rectangle -> Path().apply { addRect(outline.rect) }
                                is Rounded -> Path().apply { addRoundRect(outline.roundRect) }
                                is Generic -> outline.path
                            }

                            clipPath(path) {
                                translate(top = -clipStart) {
                                    this@drawWithContent.drawContent()
                                }
                            }
                        }
                    }
                }
                .background(MiuixTheme.colorScheme.surfaceContainer, shape),
        ) {
            content()
        }
    }
}
