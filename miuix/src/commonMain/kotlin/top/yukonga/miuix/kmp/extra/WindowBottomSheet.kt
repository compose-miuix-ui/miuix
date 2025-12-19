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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.backhandler.PredictiveBackHandler
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.anim.SinOutEasing
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getWindowSize
import top.yukonga.miuix.kmp.utils.platformDialogProperties
import top.yukonga.miuix.kmp.utils.removePlatformDialogDefaultEffects

/**
 * A bottom sheet that slides up from the bottom of the screen, rendered at window level without `Scaffold`.
 *
 * Use [LocalWindowBottomSheetState] inside `content` to request dismissal from inner composables.
 *
 * @param show The show state of the [WindowBottomSheet].
 * @param modifier The modifier to be applied to the [WindowBottomSheet].
 * @param title Optional title to display at the top of the [WindowBottomSheet].
 * @param leftAction Optional [Composable] to display on the left side of the title (e.g. a close button).
 * @param rightAction Optional [Composable] to display on the right side of the title (e.g. a submit button).
 * @param backgroundColor The background color of the [WindowBottomSheet].
 * @param enableWindowDim Whether to dim the window behind the [WindowBottomSheet].
 * @param cornerRadius The corner radius of the top corners of the [WindowBottomSheet].
 * @param sheetMaxWidth The maximum width of the [WindowBottomSheet].
 * @param onDismissRequest The callback when the [WindowBottomSheet] is dismissed.
 * @param outsideMargin The margin outside the [WindowBottomSheet].
 * @param insideMargin The margin inside the [WindowBottomSheet].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param dragHandleColor The color of the drag handle at the top.
 * @param allowDismiss Whether to allow dismissing the sheet via drag or back gesture.
 * @param content The [Composable] content of the [WindowBottomSheet].
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WindowBottomSheet(
    show: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    title: String? = null,
    leftAction: @Composable (() -> Unit)? = null,
    rightAction: @Composable (() -> Unit)? = null,
    backgroundColor: Color = WindowBottomSheetDefaults.backgroundColor(),
    enableWindowDim: Boolean = true,
    cornerRadius: Dp = WindowBottomSheetDefaults.cornerRadius,
    sheetMaxWidth: Dp = WindowBottomSheetDefaults.maxWidth,
    onDismissRequest: (() -> Unit)? = null,
    outsideMargin: DpSize = WindowBottomSheetDefaults.outsideMargin,
    insideMargin: DpSize = WindowBottomSheetDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    dragHandleColor: Color = WindowBottomSheetDefaults.dragHandleColor(),
    allowDismiss: Boolean = true,
    content: @Composable () -> Unit
) {
    val internalVisible = remember { MutableTransitionState(false) }

    if (!show.value && !internalVisible.currentState && !internalVisible.targetState) return

    val coroutineScope = rememberCoroutineScope()
    val sheetHeightPx = remember { mutableIntStateOf(0) }
    val dragOffsetY = remember { Animatable(0f) }
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dragSnapChannel = remember { Channel<Float>(capacity = Channel.CONFLATED) }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)

    val dismissPending = remember { mutableStateOf(false) }
    val outsideDismissDeferred = remember { mutableStateOf(false) }

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
        properties = platformDialogProperties()
    ) {
        removePlatformDialogDefaultEffects()
        val windowSize = getWindowSize()
        val density = LocalDensity.current
        val windowWidth by remember(windowSize, density) {
            derivedStateOf { windowSize.width.dp / density.density }
        }
        val windowHeight by remember(windowSize, density) {
            derivedStateOf { windowSize.height.dp / density.density }
        }

        if (!internalVisible.currentState && !internalVisible.targetState) {
            internalVisible.targetState = true
        }

        AnimatedVisibility(
            visibleState = internalVisible,
            enter = DialogDimEnter,
            exit = DialogDimExit
        ) {
            if (enableWindowDim) {
                val baseColor = MiuixTheme.colorScheme.windowDimming
                val dimColor = baseColor.copy(alpha = (baseColor.alpha * dimAlpha.floatValue))
                Box(
                    modifier = Modifier
                        .widthIn(min = windowWidth, max = windowWidth)
                        .heightIn(min = windowHeight, max = windowHeight)
                        .pointerInput(internalVisible.currentState) {
                            detectTapGestures(
                                onTap = {
                                    if (internalVisible.currentState) {
                                        if (allowDismiss) requestDismiss()
                                    } else {
                                        outsideDismissDeferred.value = true
                                    }
                                }
                            )
                        }
                        .background(dimColor)
                )
            }
        }

        val enterTransition = rememberDefaultSheetEnterTransition()
        val exitTransition = rememberDefaultSheetExitTransition()

        AnimatedVisibility(
            visibleState = internalVisible,
            enter = enterTransition,
            exit = exitTransition
        ) {
            SuperBottomSheetContent(
                modifier = modifier,
                title = title,
                leftAction = leftAction,
                rightAction = rightAction,
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
                content = {
                    CompositionLocalProvider(LocalWindowBottomSheetState provides { requestDismiss() }) {
                        content()
                    }
                }
            )
        }

        PredictiveBackHandler(
            enabled = show.value
        ) { progress ->
            try {
                progress.collect { event ->
                    // Calculate the offset based on progress
                    val maxOffset = if (sheetHeightPx.intValue > 0) {
                        sheetHeightPx.intValue.toFloat()
                    } else {
                        500f
                    }
                    val offset = event.progress * maxOffset

                    // Apply damping if dismiss is not allowed
                    val finalOffset = if (!allowDismiss) {
                        offset * 0.1f
                    } else {
                        offset
                    }
                    // Send target to snap channel
                    dragSnapChannel.trySend(finalOffset)

                    // Update dim alpha
                    if (allowDismiss) {
                        dimAlpha.floatValue = 1f - event.progress
                    }
                }
                if (allowDismiss) {
                    // Flow completed normally
                    requestDismiss()
                } else {
                    // Reset to original position
                    dragOffsetY.animateTo(0f, animationSpec = tween(durationMillis = 150))
                    animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
                        dimAlpha.floatValue = value
                    }
                }
            } catch (_: CancellationException) {
                // Flow cancelled
                coroutineScope.launch {
                    dragOffsetY.animateTo(0f, animationSpec = tween(durationMillis = 150))
                    animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
                        dimAlpha.floatValue = value
                    }
                }
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
private fun rememberDefaultSheetEnterTransition(): EnterTransition {
    return remember {
        slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(450, easing = DecelerateEasing(1.5f))
        )
    }
}

@Composable
private fun rememberDefaultSheetExitTransition(): ExitTransition {
    return remember {
        slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(450, easing = DecelerateEasing(0.8f))
        )
    }
}

private val DialogDimEnter: EnterTransition =
    fadeIn(animationSpec = tween(300, easing = SinOutEasing))

private val DialogDimExit: ExitTransition =
    fadeOut(animationSpec = tween(250, easing = SinOutEasing))


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
