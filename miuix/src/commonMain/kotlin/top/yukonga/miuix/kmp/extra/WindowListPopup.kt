// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupContent
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.rememberListPopupLayoutInfo
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
    onDismissRequest: (() -> Unit)? = null,
    maxHeight: Dp? = null,
    minWidth: Dp = 200.dp,
    content: @Composable () -> Unit
) {
    val internalVisible = remember { MutableTransitionState(false) }
    var isAnimating by remember { mutableStateOf(false) }
    val animationProgress = remember { Animatable(0f) }

    if (!show.value && !internalVisible.currentState && !internalVisible.targetState && !isAnimating) return

    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)
    val dimAlpha = remember { mutableFloatStateOf(1f) }

    val dismissPending = remember { mutableStateOf(false) }
    val outsideDismissDeferred = remember { mutableStateOf(false) }

    LaunchedEffect(show.value) {
        internalVisible.targetState = show.value
        if (show.value) {
            isAnimating = true
            animationProgress.animateTo(
                targetValue = 1f,
                animationSpec = spring(dampingRatio = 0.82f, stiffness = 362.5f, visibilityThreshold = 0.001f)
            )
        } else {
            animationProgress.animateTo(
                targetValue = 0f,
                animationSpec = tween(300, easing = DecelerateEasing(1.5f))
            )
            isAnimating = false
        }
    }

    val requestDismiss: () -> Unit = remember {
        {
            dismissPending.value = true
            internalVisible.targetState = false
            currentOnDismissRequest?.invoke()
        }
    }

    val windowSize = getWindowSize()
    var parentBounds by remember { mutableStateOf(IntRect.Zero) }

    Spacer(
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
    )

    if (parentBounds == IntRect.Zero) return

    val density = LocalDensity.current
    var popupContentSize by remember { mutableStateOf(IntSize.Zero) }

    val layoutInfo = rememberListPopupLayoutInfo(
        windowSize = windowSize,
        alignment = alignment,
        popupPositionProvider = popupPositionProvider,
        parentBounds = parentBounds,
        popupContentSize = popupContentSize
    )

    Dialog(
        onDismissRequest = {
            requestDismiss()
        },
        properties = platformDialogProperties()
    ) {
        removePlatformDialogDefaultEffects()

        val windowWidth = remember(windowSize, density) {
            windowSize.width.dp / density.density
        }
        val windowHeight = remember(windowSize, density) {
            windowSize.height.dp / density.density
        }

        AnimatedVisibility(
            visibleState = internalVisible,
            enter = fadeIn(animationSpec = tween(300, easing = DecelerateEasing(1.5f))),
            exit = fadeOut(animationSpec = tween(250, easing = DecelerateEasing(1.5f)))
        ) {
            val baseColor = MiuixTheme.colorScheme.windowDimming
            val dimColor = if (enableWindowDim) baseColor.copy(alpha = (baseColor.alpha * dimAlpha.floatValue)) else Color.Transparent
            Box(
                modifier = Modifier
                    .widthIn(min = windowWidth)
                    .heightIn(min = windowHeight)
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

        if (internalVisible.currentState || internalVisible.targetState || isAnimating) {
            Box(
                modifier = popupModifier
                    .widthIn(min = windowWidth)
                    .heightIn(min = windowHeight)
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
                        val minWidthPx = minWidth.roundToPx().coerceAtMost(windowSize.width)
                        val classicMinHeightPx = with(density) { (((16.dp.roundToPx() * 2) + 50.dp.roundToPx()) * 2) }
                        val safeWindowMaxHeightPx = with(density) { 416.dp.roundToPx() }
                        val availableBelow = layoutInfo.windowBounds.bottom - parentBounds.bottom - layoutInfo.popupMargin.bottom
                        val availableAbove = parentBounds.top - layoutInfo.windowBounds.top - layoutInfo.popupMargin.top
                        val sideConstrainedMax = maxOf(availableBelow, availableAbove).coerceAtLeast(0)
                        val finalMaxHeightPx = listOf(
                            maxHeight?.roundToPx() ?: Int.MAX_VALUE,
                            safeWindowMaxHeightPx,
                            sideConstrainedMax,
                            windowSize.height
                        ).min().coerceAtLeast(classicMinHeightPx.coerceAtMost(windowSize.height))
                        val placeable = measurable.measure(
                            constraints.copy(
                                minWidth = minWidthPx,
                                minHeight = classicMinHeightPx.coerceAtMost(windowSize.height),
                                maxHeight = finalMaxHeightPx,
                                maxWidth = windowSize.width
                            )
                        )
                        val measuredSize = IntSize(placeable.width, placeable.height)

                        val calculatedOffset = popupPositionProvider.calculatePosition(
                            parentBounds,
                            layoutInfo.windowBounds,
                            layoutDirection,
                            measuredSize,
                            layoutInfo.popupMargin,
                            alignment
                        )

                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.place(calculatedOffset)
                        }
                    }
            ) {
                val popupAlphaAnim = remember { Animatable(0f) }
                LaunchedEffect(internalVisible.targetState) {
                    if (internalVisible.targetState) {
                        popupAlphaAnim.animateTo(
                            1f,
                            tween(durationMillis = 150, easing = DecelerateEasing(1.5f))
                        )
                    } else {
                        popupAlphaAnim.animateTo(
                            0f,
                            tween(durationMillis = 300, easing = DecelerateEasing(1.5f))
                        )
                    }
                }

                ListPopupContent(
                    modifier = Modifier.graphicsLayer {
                        this.alpha = popupAlphaAnim.value
                    },
                    popupContentSize = popupContentSize,
                    onPopupContentSizeChange = { popupContentSize = it },
                    animationProgress = { animationProgress.value },
                    popupLayoutInfo = layoutInfo.popupLayoutInfo,
                    localTransformOrigin = layoutInfo.localTransformOrigin,
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
}

val LocalWindowListPopupState = staticCompositionLocalOf<() -> Unit> {
    error("LocalWindowListPopupState not provided")
}
