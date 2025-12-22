// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.WindowSize
import kotlin.math.min


/**
 * A column that automatically aligns the width to the widest item
 * @param content The items
 */
@Composable
fun ListPopupColumn(
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()

    SubcomposeLayout(
        modifier = Modifier.verticalScroll(scrollState)
    ) { constraints ->
        var listHeight = 0
        val tempConstraints = constraints.copy(minWidth = 200.dp.roundToPx(), maxWidth = 288.dp.roundToPx(), minHeight = 0)

        // Measure pass to find the widest item
        val listWidth = subcompose("miuixPopupListFake", content).map {
            it.measure(tempConstraints)
        }.maxOfOrNull { it.width }?.coerceIn(200.dp.roundToPx(), 288.dp.roundToPx()) ?: 200.dp.roundToPx()

        val childConstraints = constraints.copy(minWidth = listWidth, maxWidth = listWidth, minHeight = 0)

        // Actual measure and layout pass
        val placeables = subcompose("miuixPopupListReal", content).map {
            val placeable = it.measure(childConstraints)
            listHeight += placeable.height
            placeable
        }
        layout(listWidth, min(constraints.maxHeight, listHeight)) {
            var currentY = 0
            placeables.forEach {
                it.place(0, currentY)
                currentY += it.height
            }
        }
    }
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
        alignment: Align
    ): IntOffset

    /**
     * (Extra) Margins for the popup content.
     */
    fun getMargins(): PaddingValues

    /**
     * Position relative to the window, not relative to the anchor!
     */
    enum class Align {
        Left,
        Right,
        TopLeft,
        TopRight,
        BottomLeft,
        BottomRight
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
            alignment: PopupPositionProvider.Align
        ): IntOffset {
            val offsetX = if (alignment == PopupPositionProvider.Align.Right) {
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
                    (windowBounds.right - popupContentSize.width - popupMargin.right).coerceAtLeast(windowBounds.left)
                ),
                y = offsetY.coerceIn(
                    (windowBounds.top + popupMargin.top).coerceAtMost(windowBounds.bottom - popupContentSize.height - popupMargin.bottom),
                    windowBounds.bottom - popupContentSize.height - popupMargin.bottom
                )
            )
        }

        override fun getMargins(): PaddingValues {
            return PaddingValues(horizontal = 0.dp, vertical = 8.dp)
        }
    }
    val ContextMenuPositionProvider = object : PopupPositionProvider {
        override fun calculatePosition(
            anchorBounds: IntRect,
            windowBounds: IntRect,
            layoutDirection: LayoutDirection,
            popupContentSize: IntSize,
            popupMargin: IntRect,
            alignment: PopupPositionProvider.Align
        ): IntOffset {
            val offsetX: Int
            val offsetY: Int
            when (alignment) {
                PopupPositionProvider.Align.TopLeft -> {
                    offsetX = anchorBounds.left + popupMargin.left
                    offsetY = anchorBounds.bottom + popupMargin.top
                }

                PopupPositionProvider.Align.TopRight -> {
                    offsetX = anchorBounds.right - popupContentSize.width - popupMargin.right
                    offsetY = anchorBounds.bottom + popupMargin.top
                }

                PopupPositionProvider.Align.BottomLeft -> {
                    offsetX = anchorBounds.left + popupMargin.left
                    offsetY = anchorBounds.top - popupContentSize.height - popupMargin.bottom
                }

                PopupPositionProvider.Align.BottomRight -> {
                    offsetX = anchorBounds.right - popupContentSize.width - popupMargin.right
                    offsetY = anchorBounds.top - popupContentSize.height - popupMargin.bottom
                }

                else -> {
                    // Fallback
                    offsetX = if (alignment == PopupPositionProvider.Align.Right) {
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
                    (windowBounds.right - popupContentSize.width - popupMargin.right).coerceAtLeast(windowBounds.left)
                ),
                y = offsetY.coerceIn(
                    (windowBounds.top + popupMargin.top).coerceAtMost(windowBounds.bottom - popupContentSize.height - popupMargin.bottom),
                    windowBounds.bottom - popupContentSize.height - popupMargin.bottom
                )
            )
        }

        override fun getMargins(): PaddingValues {
            return PaddingValues(horizontal = 20.dp, vertical = 0.dp)
        }
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
    val popupLayoutInfo: Triple<Boolean, Boolean, Boolean>
)

@Composable
fun rememberListPopupLayoutInfo(
    windowSize: WindowSize,
    alignment: PopupPositionProvider.Align,
    popupPositionProvider: PopupPositionProvider,
    parentBounds: IntRect,
    popupContentSize: IntSize
): ListPopupLayoutInfo {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val displayCutout = WindowInsets.displayCutout.asPaddingValues()
    val statusBars = WindowInsets.statusBars.asPaddingValues()
    val navigationBars = WindowInsets.navigationBars.asPaddingValues()
    val captionBar = WindowInsets.captionBar.asPaddingValues()

    val popupMargin = remember(windowSize, layoutDirection, density, popupPositionProvider) {
        with(density) {
            IntRect(
                left = popupPositionProvider.getMargins().calculateLeftPadding(layoutDirection).roundToPx(),
                top = popupPositionProvider.getMargins().calculateTopPadding().roundToPx(),
                right = popupPositionProvider.getMargins().calculateRightPadding(layoutDirection).roundToPx(),
                bottom = popupPositionProvider.getMargins().calculateBottomPadding().roundToPx()
            )
        }
    }

    val windowBounds = remember(
        windowSize, layoutDirection, displayCutout,
        statusBars, navigationBars, captionBar, density
    ) {
        with(density) {
            IntRect(
                left = displayCutout.calculateLeftPadding(layoutDirection).roundToPx(),
                top = statusBars.calculateTopPadding().roundToPx(),
                right = windowSize.width - displayCutout.calculateRightPadding(layoutDirection).roundToPx(),
                bottom = windowSize.height - navigationBars.calculateBottomPadding().roundToPx()
                        - captionBar.calculateBottomPadding().roundToPx()
            )
        }
    }

    val predictedTransformOrigin = remember(windowSize, alignment, popupMargin, parentBounds) {
        val xInWindow = when (alignment) {
            PopupPositionProvider.Align.Right,
            PopupPositionProvider.Align.TopRight,
            PopupPositionProvider.Align.BottomRight -> parentBounds.right - popupMargin.right

            else -> parentBounds.left + popupMargin.left
        }
        val yInWindow = when (alignment) {
            PopupPositionProvider.Align.BottomRight, PopupPositionProvider.Align.BottomLeft ->
                parentBounds.top - popupMargin.bottom

            else ->
                parentBounds.bottom + popupMargin.bottom
        }
        safeTransformOrigin(
            xInWindow / windowSize.width.toFloat(),
            yInWindow / windowSize.height.toFloat()
        )
    }

    val popupLayoutInfo = remember(
        popupContentSize,
        windowBounds,
        parentBounds,
        alignment
    ) {
        val isRightAligned = when (alignment) {
            PopupPositionProvider.Align.Right,
            PopupPositionProvider.Align.TopRight,
            PopupPositionProvider.Align.BottomRight -> true

            else -> false
        }

        if (popupContentSize == IntSize.Zero) {
            Triple(false, false, isRightAligned)
        } else {
            val showBelow = (windowBounds.bottom - parentBounds.bottom) > popupContentSize.height
            val showAbove = (parentBounds.top - windowBounds.top) > popupContentSize.height
            Triple(showBelow, showAbove, isRightAligned)
        }
    }

    val effectiveTransformOrigin = remember(
        popupContentSize,
        windowSize,
        alignment,
        layoutDirection,
        popupMargin,
        parentBounds,
        windowBounds,
        popupPositionProvider
    ) {
        if (popupContentSize == IntSize.Zero) {
            predictedTransformOrigin
        } else {
            val calculatedOffset = popupPositionProvider.calculatePosition(
                parentBounds,
                windowBounds,
                layoutDirection,
                popupContentSize,
                popupMargin,
                alignment
            )

            val isRightAligned = when (alignment) {
                PopupPositionProvider.Align.Right,
                PopupPositionProvider.Align.TopRight,
                PopupPositionProvider.Align.BottomRight -> true

                else -> false
            }
            val cornerX = if (isRightAligned) {
                (calculatedOffset.x + popupContentSize.width).toFloat()
            } else {
                calculatedOffset.x.toFloat()
            }

            val showBelow = (windowBounds.bottom - parentBounds.bottom) > popupContentSize.height
            val showAbove = (parentBounds.top - windowBounds.top) > popupContentSize.height
            val showMiddle = !showBelow && !showAbove
            val topLeftY = calculatedOffset.y
            val cornerY = when {
                showMiddle -> (topLeftY + popupContentSize.height / 2f)
                showBelow -> topLeftY.toFloat()
                showAbove -> (topLeftY + popupContentSize.height).toFloat()
                else -> topLeftY.toFloat()
            }

            safeTransformOrigin(
                cornerX / windowSize.width.toFloat(),
                cornerY / windowSize.height.toFloat()
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
            }
        )
    }

    return ListPopupLayoutInfo(
        windowBounds = windowBounds,
        popupMargin = popupMargin,
        effectiveTransformOrigin = effectiveTransformOrigin,
        localTransformOrigin = localTransformOrigin,
        popupLayoutInfo = popupLayoutInfo
    )
}

@Composable
fun ListPopupContent(
    modifier: Modifier = Modifier,
    popupContentSize: IntSize,
    onPopupContentSizeChange: (IntSize) -> Unit,
    animationProgress: () -> Float,
    popupLayoutInfo: Triple<Boolean, Boolean, Boolean>,
    localTransformOrigin: TransformOrigin,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val shadowColor = MiuixTheme.colorScheme.windowDimming

    val startRadiusPx = with(density) { 4.dp.toPx() }
    val endRadiusPx = with(density) { 16.dp.toPx() }

    val clipShape = remember(popupContentSize, density, popupLayoutInfo) {
        val (showBelow, showAbove, isRightAligned) = popupLayoutInfo
        object : Shape {
            val path = Path()

            override fun createOutline(
                size: Size,
                layoutDirection: LayoutDirection,
                density: Density
            ): Outline {
                val progress = animationProgress()
                val currentRadiusPx = startRadiusPx + (endRadiusPx - startRadiusPx) * progress
                val currentWidth = size.width
                val currentHeight = size.height
                val unscaledVisibleWidth = if (currentWidth > 0) {
                    (currentWidth * 0.15f) + (currentWidth - (currentWidth * 0.15f)) * progress
                } else 0f
                val unscaledVisibleHeight = if (currentWidth > 0) {
                    val startRatio = 0.2f
                    val endRatio = currentHeight / currentWidth
                    val currentRatio = startRatio + (endRatio - startRatio) * progress
                    unscaledVisibleWidth * currentRatio
                } else 0f

                val (left, right) = if (isRightAligned) {
                    currentWidth - unscaledVisibleWidth to currentWidth
                } else {
                    0f to unscaledVisibleWidth
                }

                val (top, bottom) = when {
                    showBelow -> 0f to unscaledVisibleHeight
                    showAbove -> currentHeight - unscaledVisibleHeight to currentHeight
                    else -> (currentHeight - unscaledVisibleHeight) / 2f to (currentHeight + unscaledVisibleHeight) / 2f
                }

                val currentRadiusDp = with(density) { currentRadiusPx.toDp() }
                val shape = ContinuousRoundedRectangle(currentRadiusDp)
                val rectSize = Size(right - left, bottom - top)
                return when (val outline = shape.createOutline(rectSize, layoutDirection, density)) {
                    is Outline.Generic -> {
                        path.reset()
                        path.addPath(outline.path, Offset(left, top))
                        Outline.Generic(path)
                    }

                    is Outline.Rounded -> {
                        val r = outline.roundRect
                        Outline.Rounded(
                            RoundRect(
                                left = r.left + left,
                                top = r.top + top,
                                right = r.right + left,
                                bottom = r.bottom + top,
                                topLeftCornerRadius = r.topLeftCornerRadius,
                                topRightCornerRadius = r.topRightCornerRadius,
                                bottomRightCornerRadius = r.bottomRightCornerRadius,
                                bottomLeftCornerRadius = r.bottomLeftCornerRadius
                            )
                        )
                    }

                    is Outline.Rectangle -> {
                        Outline.Rectangle(outline.rect.translate(left, top))
                    }
                }
            }
        }
    }

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                val size = coordinates.size
                if (popupContentSize != size) onPopupContentSizeChange(size)
            }
            .dropShadow(
                shape = clipShape,
                block = {
                    this.radius = 70f
                    this.alpha = 0.4f * animationProgress()
                    this.color = shadowColor
                }
            )
            .graphicsLayer {
                this.clip = true
                this.shape = clipShape
            }
            .background(MiuixTheme.colorScheme.surfaceContainer, clipShape)
    ) {
        Box(
            modifier = Modifier.graphicsLayer {
                val scale = 0.15f + 0.85f * animationProgress()
                this.scaleX = scale
                this.scaleY = scale
                this.transformOrigin = localTransformOrigin
            }
        ) {
            content()
        }
    }
}
