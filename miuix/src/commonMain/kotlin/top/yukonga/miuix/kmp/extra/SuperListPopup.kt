// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.safeTransformOrigin
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.PopupLayout
import top.yukonga.miuix.kmp.utils.getWindowSize

/**
 * A popup with a list of items.
 *
 * @param show The show state of the [SuperListPopup].
 * @param popupModifier The modifier to be applied to the [SuperListPopup].
 * @param popupPositionProvider The [PopupPositionProvider] of the [SuperListPopup].
 * @param alignment The alignment of the [SuperListPopup].
 * @param enableWindowDim Whether to enable window dimming when the [SuperListPopup] is shown.
 * @param shadowElevation The elevation of the shadow of the [SuperListPopup].
 * @param onDismissRequest The callback when the [SuperListPopup] is dismissed.
 * @param maxHeight The maximum height of the [SuperListPopup]. If null, the height will be calculated automatically.
 * @param minWidth The minimum width of the [SuperListPopup].
 * @param content The [Composable] content of the [SuperListPopup]. You should use the [ListPopupColumn] in general.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SuperListPopup(
    show: MutableState<Boolean>,
    popupModifier: Modifier = Modifier,
    popupPositionProvider: PopupPositionProvider = ListPopupDefaults.DropdownPositionProvider,
    alignment: PopupPositionProvider.Align = PopupPositionProvider.Align.Right,
    enableWindowDim: Boolean = true,
    shadowElevation: Dp = 11.dp,
    onDismissRequest: (() -> Unit)? = null,
    maxHeight: Dp? = null,
    minWidth: Dp = 200.dp,
    content: @Composable () -> Unit
) {
    if (!show.value) return

    val currentOnDismiss by rememberUpdatedState(onDismissRequest)

    val windowSize = getWindowSize()
    var parentBounds by remember { mutableStateOf(IntRect.Zero) }

    Layout(
        modifier = Modifier
            .onGloballyPositioned { childCoordinates ->
                childCoordinates.parentLayoutCoordinates?.let { parentLayoutCoordinates ->
                    val positionInWindow = parentLayoutCoordinates.positionInWindow()
                    parentBounds = IntRect(
                        left = positionInWindow.x.toInt(),
                        top = positionInWindow.y.toInt(),
                        right = positionInWindow.x.toInt() + parentLayoutCoordinates.size.width,
                        bottom = positionInWindow.y.toInt() + parentLayoutCoordinates.size.height
                    )
                }
            }
    ) { _, _ -> layout(0, 0) {} }
    if (parentBounds == IntRect.Zero) return

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val displayCutout = WindowInsets.displayCutout.asPaddingValues()
    val statusBars = WindowInsets.statusBars.asPaddingValues()
    val navigationBars = WindowInsets.navigationBars.asPaddingValues()
    val captionBar = WindowInsets.captionBar.asPaddingValues()

    val popupMargin by remember(windowSize, layoutDirection, density) {
        derivedStateOf {
            with(density) {
                IntRect(
                    left = popupPositionProvider.getMargins().calculateLeftPadding(layoutDirection).roundToPx(),
                    top = popupPositionProvider.getMargins().calculateTopPadding().roundToPx(),
                    right = popupPositionProvider.getMargins().calculateRightPadding(layoutDirection).roundToPx(),
                    bottom = popupPositionProvider.getMargins().calculateBottomPadding().roundToPx()
                )
            }
        }
    }

    val windowBounds by remember(windowSize, layoutDirection, displayCutout, statusBars, navigationBars, captionBar, density) {
        derivedStateOf {
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
    }

    val predictedTransformOrigin by remember(windowSize, alignment, popupMargin, parentBounds) {
        derivedStateOf {
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
    }

    var popupContentSize by remember { mutableStateOf(IntSize.Zero) }
    val effectiveTransformOrigin by remember(
        popupContentSize,
        windowSize,
        alignment,
        layoutDirection,
        popupMargin,
        parentBounds,
        windowBounds,
        popupPositionProvider
    ) {
        derivedStateOf {
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
    }

    PopupLayout(
        visible = show,
        enableWindowDim = enableWindowDim,
        transformOrigin = { effectiveTransformOrigin },
    ) {
        val transition = updateTransition(targetState = show.value)
        val scale by transition.animateFloat(
            transitionSpec = {
                spring(dampingRatio = 0.82f, stiffness = 450f, visibilityThreshold = 0.001f)
            }
        ) { isShown ->
            if (isShown) 1f else 0.25f
        }

        val baseCornerRadiusPx = with(density) { 16.dp.toPx() }
        val appliedCornerDp = with(density) {
            (baseCornerRadiusPx / scale.coerceAtLeast(0.001f)).toDp()
        }
        val shape = ContinuousRoundedRectangle(appliedCornerDp)

        Box(
            modifier = popupModifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { currentOnDismiss?.invoke() }
                    )
                }
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.copy(
                            minWidth = if (minWidth.roundToPx() <= windowSize.width) minWidth.roundToPx() else windowSize.width,
                            minHeight = if (50.dp.roundToPx() <= windowSize.height) 50.dp.roundToPx() else windowSize.height,
                            maxHeight = maxHeight?.roundToPx()?.coerceAtLeast(50.dp.roundToPx())
                                ?: (windowBounds.height - popupMargin.top - popupMargin.bottom).coerceAtLeast(
                                    50.dp.roundToPx()
                                ),
                            maxWidth = if (minWidth.roundToPx() <= windowSize.width) windowSize.width else minWidth.roundToPx()
                        )
                    )
                    val measuredSize = IntSize(placeable.width, placeable.height)

                    val calculatedOffset = popupPositionProvider.calculatePosition(
                        parentBounds,
                        windowBounds,
                        layoutDirection,
                        measuredSize,
                        popupMargin,
                        alignment
                    )

                    layout(constraints.maxWidth, constraints.maxHeight) {
                        placeable.place(calculatedOffset)
                    }
                }
        ) {
            val shadowColor = MiuixTheme.colorScheme.windowDimming
            Box(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        val size = coordinates.size
                        if (popupContentSize != size) popupContentSize = size
                    }
                    .dropShadow(
                        shape = shape,
                        block = {
                            this.radius = 70f
                            this.spread = 0f
                            this.alpha = 0.6f
                            this.color = shadowColor
                        }
                    )
                    .graphicsLayer(
                        clip = true,
                        shape = shape
                    )
                    .background(MiuixTheme.colorScheme.surfaceContainer)
            ) {
                content()
            }
        }
    }

    BackHandler(enabled = show.value) {
        currentOnDismiss?.invoke()
    }
}
