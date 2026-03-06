// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * Internal shared layout logic for [SuperBottomSheet] and [WindowBottomSheet].
 *
 * @param show Whether the bottom sheet is currently shown.
 * @param modifier The modifier to be applied to the bottom sheet content.
 * @param title Optional title to display at the top of the bottom sheet.
 * @param startAction Optional [Composable] to display on the start side of the title.
 * @param endAction Optional [Composable] to display on the end side of the title.
 * @param backgroundColor The background color of the bottom sheet.
 * @param enableWindowDim Whether to dim the window behind the bottom sheet.
 * @param cornerRadius The corner radius of the top corners of the bottom sheet.
 * @param sheetMaxWidth The maximum width of the bottom sheet.
 * @param onDismissRequest The callback when the user tries to dismiss the bottom sheet.
 * @param onDismissFinished The callback when the bottom sheet is completely dismissed.
 * @param outsideMargin The margin outside the bottom sheet.
 * @param insideMargin The margin inside the bottom sheet.
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param dragHandleColor The color of the drag handle.
 * @param allowDismiss Whether to allow dismissing the sheet via drag or back gesture.
 * @param enableNestedScroll Whether to enable nested scrolling for the content.
 * @param topInset Optional top inset override. If null, calculated from window insets.
 * @param popupHost A composable that provides the container (e.g., DialogLayout or Dialog).
 *   It receives the visibility state and the inner content composable.
 * @param content The content of the bottom sheet.
 */
@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
internal fun BottomSheetContentLayout(
    show: Boolean,
    modifier: Modifier = Modifier,
    title: String? = null,
    startAction: @Composable (() -> Unit)? = null,
    endAction: @Composable (() -> Unit)? = null,
    backgroundColor: Color,
    enableWindowDim: Boolean = true,
    cornerRadius: Dp,
    sheetMaxWidth: Dp,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean = true,
    dragHandleColor: Color,
    allowDismiss: Boolean = true,
    enableNestedScroll: Boolean = true,
    topInset: Dp? = null,
    popupHost: @Composable (visible: Boolean, content: @Composable () -> Unit) -> Unit,
    content: @Composable () -> Unit,
) {
    val animationProgress = remember { Animatable(0f, visibilityThreshold = 0.0001f) }
    val currentOnDismissFinished by rememberUpdatedState(onDismissFinished)
    val internalVisible = remember { mutableStateOf(false) }

    LaunchedEffect(show) {
        if (show) {
            internalVisible.value = true
            animationProgress.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 450, easing = DecelerateEasing(1.5f)),
            )
        } else {
            animationProgress.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 450, easing = DecelerateEasing(0.8f)),
            )
            internalVisible.value = false
            currentOnDismissFinished?.invoke()
        }
    }

    if (!show && !internalVisible.value) return

    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val coroutineScope = rememberCoroutineScope()
    val sheetHeightPx = remember { mutableIntStateOf(0) }
    val dragOffsetY = remember { Animatable(0f) }
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dragSnapChannel = remember { Channel<Float>(capacity = Channel.CONFLATED) }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)

    val requestDismiss: () -> Unit = remember {
        { currentOnDismissRequest?.invoke() }
    }

    val resetGesture: suspend () -> Unit = {
        dragOffsetY.animateTo(0f, animationSpec = tween(durationMillis = 150))
        animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
            dimAlpha.floatValue = value
        }
    }

    LaunchedEffect(dragOffsetY) {
        for (target in dragSnapChannel) dragOffsetY.snapTo(target)
    }

    popupHost(internalVisible.value) {
        val navigationEventState = rememberNavigationEventState(currentInfo = NavigationEventInfo.None)
        NavigationBackHandler(
            state = navigationEventState,
            isBackEnabled = show,
            onBackCancelled = { coroutineScope.launch { resetGesture() } },
            onBackCompleted = {
                if (allowDismiss) {
                    requestDismiss()
                } else {
                    coroutineScope.launch { resetGesture() }
                }
            },
        )

        LaunchedEffect(navigationEventState.transitionState, allowDismiss) {
            val transitionState = navigationEventState.transitionState
            if (
                transitionState is NavigationEventTransitionState.InProgress &&
                transitionState.direction == NavigationEventTransitionState.TRANSITIONING_BACK
            ) {
                val maxOffset = if (sheetHeightPx.intValue > 0) sheetHeightPx.intValue.toFloat() else 500f
                val offset = transitionState.latestEvent.progress * maxOffset
                val finalOffset = if (!allowDismiss) offset * 0.1f else offset
                dragSnapChannel.trySend(finalOffset)
                if (allowDismiss) dimAlpha.floatValue = 1f - transitionState.latestEvent.progress
            }
        }

        if (enableWindowDim) {
            val progress = animationProgress.value
            val baseColor = MiuixTheme.colorScheme.windowDimming
            val dimColor = baseColor.copy(alpha = baseColor.alpha * dimAlpha.floatValue * progress)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(dimColor),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            if (allowDismiss) {
                                requestDismiss()
                            }
                        },
                    )
                },
            contentAlignment = Alignment.BottomCenter,
        ) {
            val progress = animationProgress.value
            val sheetModifier = modifier.graphicsLayer {
                val currentHeight = sheetHeightPx.intValue.toFloat()
                val windowHeightPx = with(density) { windowInfo.containerDpSize.height.toPx() }
                val baseOffset = if (currentHeight > 0) currentHeight else windowHeightPx
                translationY = baseOffset * (1f - progress) + dragOffsetY.value
            }

            SuperBottomSheetContent(
                title = title,
                backgroundColor = backgroundColor,
                cornerRadius = cornerRadius,
                sheetMaxWidth = sheetMaxWidth,
                outsideMargin = outsideMargin,
                insideMargin = insideMargin,
                defaultWindowInsetsPadding = defaultWindowInsetsPadding,
                dragHandleColor = dragHandleColor,
                allowDismiss = allowDismiss,
                sheetHeightPx = sheetHeightPx,
                dragOffsetY = dragOffsetY,
                dimAlpha = dimAlpha,
                dragSnapChannel = dragSnapChannel,
                onDismissRequest = {
                    if (allowDismiss) {
                        requestDismiss()
                    }
                },
                modifier = sheetModifier,
                topInset = topInset,
                enableNestedScroll = enableNestedScroll,
                startAction = startAction,
                endAction = endAction,
                content = {
                    CompositionLocalProvider(LocalDismissState provides requestDismiss) {
                        content()
                    }
                },
            )
        }
    }
}
