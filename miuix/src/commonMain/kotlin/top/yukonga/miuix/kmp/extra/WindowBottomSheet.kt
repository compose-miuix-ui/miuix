// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.RemovePlatformDialogDefaultEffects
import top.yukonga.miuix.kmp.utils.platformDialogProperties

/**
 * A bottom sheet that slides up from the bottom of the screen, rendered at window level without `Scaffold`.
 *
 * Use [LocalWindowBottomSheetState] inside `content` to request dismissal from inner composables.
 *
 * @param show The show state of the [WindowBottomSheet].
 * @param modifier The modifier to be applied to the [WindowBottomSheet].
 * @param title Optional title to display at the top of the [WindowBottomSheet].
 * @param startAction Optional [Composable] to display on the start side of the title (e.g. a close button).
 * @param endAction Optional [Composable] to display on the end side of the title (e.g. a submit button).
 * @param backgroundColor The background color of the [WindowBottomSheet].
 * @param enableWindowDim Whether to dim the window behind the [WindowBottomSheet].
 * @param cornerRadius The corner radius of the top corners of the [WindowBottomSheet].
 * @param sheetMaxWidth The maximum width of the [WindowBottomSheet].
 * @param onDismissRequest Will called when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * @param onDismissFinished The callback when the [SuperDialog] is completely dismissed.
 * @param outsideMargin The margin outside the [WindowBottomSheet].
 * @param insideMargin The margin inside the [WindowBottomSheet].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param dragHandleColor The color of the drag handle at the top.
 * @param allowDismiss Whether to allow dismissing the sheet via drag or back gesture.
 * @param enableNestedScroll Whether to enable nested scrolling for the content.
 * @param content The [Composable] content of the [WindowBottomSheet].
 */
@Suppress("ktlint:compose:modifier-not-used-at-root")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WindowBottomSheet(
    show: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    title: String? = null,
    startAction: @Composable (() -> Unit)? = null,
    endAction: @Composable (() -> Unit)? = null,
    backgroundColor: Color = WindowBottomSheetDefaults.backgroundColor(),
    enableWindowDim: Boolean = true,
    cornerRadius: Dp = WindowBottomSheetDefaults.cornerRadius,
    sheetMaxWidth: Dp = WindowBottomSheetDefaults.maxWidth,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    outsideMargin: DpSize = WindowBottomSheetDefaults.outsideMargin,
    insideMargin: DpSize = WindowBottomSheetDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    dragHandleColor: Color = WindowBottomSheetDefaults.dragHandleColor(),
    allowDismiss: Boolean = true,
    enableNestedScroll: Boolean = true,
    content: @Composable () -> Unit,
) {
    val internalVisible = remember { MutableTransitionState(false) }

    val statusBarsPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val captionBarPadding = WindowInsets.captionBar.asPaddingValues().calculateTopPadding()
    val displayCutoutPadding = WindowInsets.displayCutout.asPaddingValues().calculateTopPadding()

    val safeTopInset = remember(statusBarsPadding, captionBarPadding, displayCutoutPadding) {
        maxOf(statusBarsPadding, captionBarPadding, displayCutoutPadding)
    }

    if (!show.value && !internalVisible.currentState && !internalVisible.targetState) return

    val coroutineScope = rememberCoroutineScope()
    val sheetHeightPx = remember { mutableIntStateOf(0) }
    val dragOffsetY = remember { Animatable(0f) }
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dragSnapChannel = remember { Channel<Float>(capacity = Channel.CONFLATED) }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)
    val currentOnDismissFinished by rememberUpdatedState(onDismissFinished)

    val dismissPending = remember { mutableStateOf(false) }
    val outsideDismissDeferred = remember { mutableStateOf(false) }

    LaunchedEffect(internalVisible.currentState, show.value) {
        if (!internalVisible.currentState && !show.value) {
            currentOnDismissFinished?.invoke()
        }
    }

    LaunchedEffect(show.value) {
        internalVisible.targetState = show.value
    }

    val requestDismiss: () -> Unit = remember {
        {
            dismissPending.value = true
            internalVisible.targetState = false
            currentOnDismissRequest?.invoke()
        }
    }

    val resetGesture: suspend () -> Unit = {
        dragOffsetY.animateTo(0f, animationSpec = tween(durationMillis = 150))
        animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
            dimAlpha.floatValue = value
        }
    }

    LaunchedEffect(dragOffsetY) {
        for (target in dragSnapChannel) {
            dragOffsetY.snapTo(target)
        }
    }

    Dialog(
        onDismissRequest = {
            if (allowDismiss) {
                requestDismiss()
            }
        },
        properties = platformDialogProperties(),
    ) {
        RemovePlatformDialogDefaultEffects()

        val navigationEventState = rememberNavigationEventState(currentInfo = NavigationEventInfo.None)
        NavigationBackHandler(
            state = navigationEventState,
            isBackEnabled = show.value,
            onBackCancelled = {
                coroutineScope.launch {
                    resetGesture()
                }
            },
            onBackCompleted = {
                if (allowDismiss) {
                    currentOnDismissRequest?.invoke()
                } else {
                    coroutineScope.launch {
                        resetGesture()
                    }
                }
            },
        )

        LaunchedEffect(navigationEventState.transitionState, allowDismiss) {
            val transitionState = navigationEventState.transitionState
            if (
                transitionState is NavigationEventTransitionState.InProgress &&
                transitionState.direction == NavigationEventTransitionState.TRANSITIONING_BACK
            ) {
                val maxOffset = if (sheetHeightPx.intValue > 0) {
                    sheetHeightPx.intValue.toFloat()
                } else {
                    500f
                }
                val offset = transitionState.latestEvent.progress * maxOffset
                val finalOffset = if (!allowDismiss) {
                    offset * 0.1f
                } else {
                    offset
                }
                dragOffsetY.snapTo(finalOffset)
                dimAlpha.floatValue = 1f - transitionState.latestEvent.progress
            }
        }

        AnimatedVisibility(
            visibleState = internalVisible,
            enter = fadeIn(animationSpec = tween(300, easing = DecelerateEasing(1.5f))),
            exit = fadeOut(animationSpec = tween(300, easing = DecelerateEasing(1.5f))),
        ) {
            val baseColor = MiuixTheme.colorScheme.windowDimming
            val dimColor = if (enableWindowDim) baseColor.copy(alpha = (baseColor.alpha * dimAlpha.floatValue)) else Color.Transparent
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(dimColor),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(internalVisible.currentState) {
                    detectTapGestures(
                        onTap = {
                            if (internalVisible.currentState) {
                                requestDismiss()
                            } else {
                                outsideDismissDeferred.value = true
                            }
                        },
                    )
                },

            ) {
            AnimatedVisibility(
                visibleState = internalVisible,
                enter = rememberDefaultSheetEnterTransition(),
                exit = rememberDefaultSheetExitTransition(),
            ) {
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
                        if (internalVisible.currentState) {
                            requestDismiss()
                        } else {
                            outsideDismissDeferred.value = true
                        }
                    },
                    modifier = modifier,
                    topInset = safeTopInset,
                    enableNestedScroll = enableNestedScroll,
                    startAction = startAction,
                    endAction = endAction,
                    content = {
                        CompositionLocalProvider(LocalWindowBottomSheetState provides { requestDismiss() }) {
                            content()
                        }
                    },
                )
            }
        }

        LaunchedEffect(internalVisible.currentState, outsideDismissDeferred.value) {
            if (internalVisible.currentState && outsideDismissDeferred.value) {
                outsideDismissDeferred.value = false
                if (allowDismiss) requestDismiss()
            }
        }
    }
}

@Composable
private fun rememberDefaultSheetEnterTransition(): EnterTransition = remember {
    slideInVertically(
        initialOffsetY = { fullHeight -> fullHeight },
        animationSpec = tween(450, easing = DecelerateEasing(1.5f)),
    )
}

@Composable
private fun rememberDefaultSheetExitTransition(): ExitTransition = remember {
    slideOutVertically(
        targetOffsetY = { fullHeight -> fullHeight },
        animationSpec = tween(300, easing = DecelerateEasing(0.8f)),
    )
}

object WindowBottomSheetDefaults {

    /**
     * The default background color of the [WindowBottomSheet].
     */
    @Composable
    fun backgroundColor() = MiuixTheme.colorScheme.background

    /**
     * The default color of the drag handle.
     */
    @Composable
    fun dragHandleColor() = MiuixTheme.colorScheme.onSurfaceVariantSummary.copy(alpha = 0.2f)

    /**
     * The default corner radius of the [WindowBottomSheet].
     */
    val cornerRadius = 28.dp

    /**
     * The default maximum width of the [WindowBottomSheet].
     */
    val maxWidth = 640.dp

    /**
     * The default margin outside the [WindowBottomSheet].
     */
    val outsideMargin = DpSize(0.dp, 0.dp)

    /**
     * The default margin inside the [WindowBottomSheet].
     */
    val insideMargin = DpSize(24.dp, 0.dp)
}

/**
 * CompositionLocal that provides a dismiss request function for [WindowBottomSheet].
 *
 * Call the provided function to request dismissal from inside bottom sheet content.
 */
val LocalWindowBottomSheetState = staticCompositionLocalOf<(() -> Unit)?> { null }
