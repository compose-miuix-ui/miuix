// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.window.Dialog
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.safeTransformOrigin
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getWindowSize
import top.yukonga.miuix.kmp.utils.platformDialogProperties
import top.yukonga.miuix.kmp.utils.removePlatformDialogDefaultEffects

/**
 * A popup with a list of items, rendered at window level without `Scaffold`.
 *
 * Use [LocalWindowListPopupState] inside `content` to request dismissal from inner composables.
 *
 * @param show The show state of the [WindowListPopup].
 * @param popupModifier The modifier to be applied to the [WindowListPopup].
 * @param popupPositionProvider The [PopupPositionProvider] of the [WindowListPopup].
 * @param alignment The alignment of the [WindowListPopup].
 * @param enableWindowDim Whether to enable window dimming when the [WindowListPopup] is shown.
 * @param shadowElevation The elevation of the shadow of the [WindowListPopup].
 * @param onDismissRequest The callback when the [WindowListPopup] is dismissed.
 * @param maxHeight The maximum height of the [WindowListPopup]. If null, the height will be calculated automatically.
 * @param minWidth The minimum width of the [WindowListPopup].
 * @param content The [Composable] content of the [WindowListPopup]. You should use the [ListPopupColumn] in general.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WindowListPopup(
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
    val dimAlpha = remember { mutableFloatStateOf(1f) }

    val internalVisible = remember { MutableTransitionState(false) }
    val dismissPending = remember { mutableStateOf(false) }
    val outsideDismissDeferred = remember { mutableStateOf(false) }

    val requestDismiss: () -> Unit = remember {
        {
            dismissPending.value = true
            internalVisible.targetState = false
        }
    }

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

    Dialog(
        onDismissRequest = {
            requestDismiss()
        },
        properties = platformDialogProperties()
    ) {
        removePlatformDialogDefaultEffects()

        if (!internalVisible.currentState && !internalVisible.targetState) {
            internalVisible.targetState = true
        }

        val windowWidth by remember(windowSize, density) {
            derivedStateOf { windowSize.width.dp / density.density }
        }
        val windowHeight by remember(windowSize, density) {
            derivedStateOf { windowSize.height.dp / density.density }
        }

        AnimatedVisibility(
            visibleState = internalVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 400, easing = DecelerateEasing())),
            exit = fadeOut(animationSpec = tween(durationMillis = 400, easing = DecelerateEasing()))
        ) {
            val baseColor = MiuixTheme.colorScheme.windowDimming
            val dimColor = if (enableWindowDim) baseColor.copy(alpha = (baseColor.alpha * dimAlpha.floatValue)) else Color.Transparent
            Box(
                modifier = Modifier
                    .widthIn(min = windowWidth, max = windowWidth)
                    .heightIn(min = windowHeight, max = windowHeight)
                    .pointerInput(internalVisible.currentState) {
                        detectTapGestures(
                            onTap = {
                                if (internalVisible.currentState) {
                                    requestDismiss()
                                } else {
                                    outsideDismissDeferred.value = true
                                }
                            }
                        )
                    }
                    .background(dimColor)
            )
        }

        val transition = rememberTransition(transitionState = internalVisible, label = "PopupTransition")
        val scale by transition.animateFloat(
            transitionSpec = {
                spring(dampingRatio = 0.82f, stiffness = 450f, visibilityThreshold = 0.001f)
            },
            label = "Scale"
        ) { isShown ->
            if (isShown) 1f else 0.15f
        }

        val alpha by transition.animateFloat(
            transitionSpec = {
                if (targetState) {
                    tween(durationMillis = 150, easing = DecelerateEasing(1.5f))
                } else {
                    tween(durationMillis = 140, easing = DecelerateEasing(1.5f))
                }
            },
            label = "Alpha"
        ) { isShown ->
            if (isShown) 1f else 0f
        }

        val baseCornerRadiusPx = with(density) { 16.dp.toPx() }
        val appliedCornerDp = with(density) {
            (baseCornerRadiusPx / scale.coerceAtLeast(0.001f)).toDp()
        }
        val shape = ContinuousRoundedRectangle(appliedCornerDp)

        if (internalVisible.currentState || internalVisible.targetState) {
            Box(
                modifier = popupModifier
                    .widthIn(min = windowWidth, max = windowWidth)
                    .heightIn(min = windowHeight, max = windowHeight)
                    .graphicsLayer {
                        this.alpha = alpha
                        this.transformOrigin = effectiveTransformOrigin
                        this.scaleX = scale
                        this.scaleY = scale
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                if (internalVisible.currentState) {
                                    requestDismiss()
                                } else {
                                    outsideDismissDeferred.value = true
                                }
                            }
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
                        .pointerInput(Unit) {
                            detectTapGestures { /* Consume click to prevent dismissal */ }
                        }
                ) {
                    CompositionLocalProvider(LocalWindowListPopupState provides { requestDismiss() }) {
                        content()
                    }
                }
            }
        }
        BackHandler(
            enabled = show.value
        ) {
            if (internalVisible.currentState) {
                requestDismiss()
            } else {
                outsideDismissDeferred.value = true
            }
        }
        LaunchedEffect(internalVisible.currentState, outsideDismissDeferred.value) {
            if (internalVisible.currentState && outsideDismissDeferred.value) {
                outsideDismissDeferred.value = false
                requestDismiss()
            }
        }
    }

    if (!internalVisible.currentState && !internalVisible.targetState && dismissPending.value) {
        dismissPending.value = false
        show.value = false
        currentOnDismiss?.invoke()
    }
}

val LocalWindowListPopupState = staticCompositionLocalOf<() -> Unit> {
    error("LocalWindowListPopupState not provided")
}
