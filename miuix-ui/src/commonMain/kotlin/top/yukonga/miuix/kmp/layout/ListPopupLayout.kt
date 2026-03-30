// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.ListPopupContent
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.rememberListPopupLayoutInfo
import top.yukonga.miuix.kmp.theme.LocalDismissState
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * Internal shared layout logic for [OverlayListPopup] and [WindowListPopup].
 *
 * @param show Whether the popup is currently shown.
 * @param popupHost A composable that provides the popup container (e.g., PopupLayout or Dialog).
 *   It receives the visibility state and the inner content composable.
 * @param popupModifier The modifier to be applied to the popup content area.
 * @param popupPositionProvider The [PopupPositionProvider] for positioning.
 * @param alignment The alignment of the popup.
 * @param enableWindowDim Whether to enable window dimming.
 * @param onDismissRequest The callback when the popup is dismissed.
 * @param maxHeight The maximum height of the popup.
 * @param minWidth The minimum width of the popup.
 * @param content The content of the popup.
 */
@Composable
internal fun ListPopupLayout(
    show: Boolean,
    popupHost: @Composable (visible: Boolean, content: @Composable () -> Unit) -> Unit,
    popupModifier: Modifier = Modifier,
    popupPositionProvider: PopupPositionProvider = ListPopupDefaults.DropdownPositionProvider,
    alignment: PopupPositionProvider.Align = PopupPositionProvider.Align.Start,
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    maxHeight: Dp? = null,
    minWidth: Dp = 200.dp,
    content: @Composable () -> Unit,
) {
    val fractionProgress = remember { Animatable(0f) }
    val alphaProgress = remember { Animatable(0f) }
    val dimProgress = remember { Animatable(0f) }
    val currentOnDismiss by rememberUpdatedState(onDismissRequest)
    val currentOnDismissFinished by rememberUpdatedState(onDismissFinished)
    val coroutineScope = rememberCoroutineScope()
    val internalVisible = remember { mutableStateOf(false) }

    LaunchedEffect(show) {
        if (show) {
            internalVisible.value = true
            launch { fractionProgress.animateTo(1f, ListPopupDefaults.FractionAnimationSpec) }
            launch { alphaProgress.animateTo(1f, ListPopupDefaults.AlphaEnterAnimationSpec) }
            launch { dimProgress.animateTo(1f, ListPopupDefaults.DimEnterAnimationSpec) }
        } else {
            if (!internalVisible.value) return@LaunchedEffect
            launch { fractionProgress.animateTo(0f, ListPopupDefaults.FractionAnimationSpec) }
            launch { dimProgress.animateTo(0f, ListPopupDefaults.DimExitAnimationSpec) }
            alphaProgress.animateTo(0f, ListPopupDefaults.AlphaExitAnimationSpec)
            fractionProgress.stop()
            dimProgress.stop()
            internalVisible.value = false
            currentOnDismissFinished?.invoke()
        }
    }

    if (!show && !internalVisible.value) return

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
                        bottom = positionInWindow.y.toInt() + parentLayoutCoordinates.size.height,
                    )
                }
            },
    )

    if (parentBounds == IntRect.Zero) return

    var popupContentSize by remember { mutableStateOf(IntSize.Zero) }
    val layoutInfo = rememberListPopupLayoutInfo(
        alignment = alignment,
        popupPositionProvider = popupPositionProvider,
        parentBounds = parentBounds,
        popupContentSize = popupContentSize,
    )

    var hostPositionInWindow by remember { mutableStateOf(Offset.Zero) }

    popupHost(internalVisible.value) {
        val navigationEventState = rememberNavigationEventState(currentInfo = NavigationEventInfo.None)
        NavigationBackHandler(
            state = navigationEventState,
            isBackEnabled = show,
            onBackCancelled = {
                coroutineScope.launch {
                    launch { fractionProgress.animateTo(1f, ListPopupDefaults.ResetAnimationSpec) }
                    launch { alphaProgress.animateTo(1f, ListPopupDefaults.AlphaEnterAnimationSpec) }
                    launch { dimProgress.animateTo(1f, ListPopupDefaults.DimEnterAnimationSpec) }
                }
            },
            onBackCompleted = {
                currentOnDismiss?.invoke()
            },
        )

        LaunchedEffect(navigationEventState.transitionState) {
            val transitionState = navigationEventState.transitionState
            if (
                transitionState is NavigationEventTransitionState.InProgress &&
                transitionState.direction == NavigationEventTransitionState.TRANSITIONING_BACK
            ) {
                val progress = transitionState.latestEvent.progress
                fractionProgress.snapTo(1f - progress)
                alphaProgress.snapTo(1f - progress)
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (enableWindowDim) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = dimProgress.value
                        }
                        .background(MiuixTheme.colorScheme.windowDimming),
                )
            }
            Box(
                modifier = popupModifier
                    .fillMaxSize()
                    .onGloballyPositioned { coordinates ->
                        hostPositionInWindow = coordinates.positionInWindow()
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { currentOnDismiss?.invoke() },
                        )
                    }
                    .layout { measurable, constraints ->
                        val windowBounds = layoutInfo.windowBounds
                        val popupMargin = layoutInfo.popupMargin
                        val placeable = measurable.measure(
                            constraints.copy(
                                maxHeight = maxHeight?.roundToPx()?.coerceAtLeast(50.dp.roundToPx())
                                    ?: (windowBounds.height - popupMargin.top - popupMargin.bottom)
                                        .coerceAtLeast(50.dp.roundToPx()),
                                minHeight = if (50.dp.roundToPx() <= constraints.maxHeight) 50.dp.roundToPx() else constraints.maxHeight,
                                maxWidth = constraints.maxWidth,
                                minWidth = minWidth.roundToPx().coerceAtMost(constraints.maxWidth),
                            ),
                        )
                        val measuredSize = IntSize(placeable.width, placeable.height)

                        val calculatedOffset = popupPositionProvider.calculatePosition(
                            parentBounds,
                            windowBounds,
                            layoutDirection,
                            measuredSize,
                            popupMargin,
                            alignment,
                        )

                        val adjustedOffset = IntOffset(
                            x = calculatedOffset.x - hostPositionInWindow.x.toInt(),
                            y = calculatedOffset.y - hostPositionInWindow.y.toInt(),
                        )

                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.place(adjustedOffset)
                        }
                    },
            ) {
                ListPopupContent(
                    popupContentSize = popupContentSize,
                    onPopupContentSizeChange = { popupContentSize = it },
                    fractionProgress = { fractionProgress.value },
                    alphaProgress = { alphaProgress.value },
                    popupLayoutPosition = layoutInfo.popupLayoutPosition,
                    localTransformOrigin = layoutInfo.localTransformOrigin,
                    content = {
                        CompositionLocalProvider(LocalDismissState provides { currentOnDismiss?.invoke() }) {
                            content()
                        }
                    },
                )
            }
        }
    }
}
