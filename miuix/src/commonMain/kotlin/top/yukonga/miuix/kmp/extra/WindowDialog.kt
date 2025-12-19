// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
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
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.anim.SinOutEasing
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getWindowSize
import top.yukonga.miuix.kmp.utils.platformDialogProperties
import top.yukonga.miuix.kmp.utils.removePlatformDialogDefaultEffects

/**
 * A dialog with a title, a summary, and other contents, rendered at window level without `Scaffold`.
 *
 * Use [LocalWindowDialogState] inside `content` to request dismissal from inner composables.
 *
 * @param show The show state of the [WindowDialog].
 * @param modifier The modifier to be applied to the [WindowDialog].
 * @param title The title of the [WindowDialog].
 * @param titleColor The color of the title.
 * @param summary The summary of the [WindowDialog].
 * @param summaryColor The color of the summary.
 * @param backgroundColor The background color of the [WindowDialog].
 * @param onDismissRequest The callback when the [WindowDialog] is dismissed.
 * @param outsideMargin The margin outside the [WindowDialog].
 * @param insideMargin The margin inside the [WindowDialog].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding to the [WindowDialog].
 * @param content The [Composable] content of the [WindowDialog].
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WindowDialog(
    show: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: Color = WindowDialogDefaults.titleColor(),
    summary: String? = null,
    summaryColor: Color = WindowDialogDefaults.summaryColor(),
    backgroundColor: Color = WindowDialogDefaults.backgroundColor(),
    onDismissRequest: (() -> Unit)? = null,
    outsideMargin: DpSize = WindowDialogDefaults.outsideMargin,
    insideMargin: DpSize = WindowDialogDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    content: @Composable () -> Unit
) {
    val internalVisible = remember { MutableTransitionState(false) }

    if (!show.value && !internalVisible.currentState && !internalVisible.targetState) return

    val coroutineScope = rememberCoroutineScope()
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dialogHeightPx = remember { mutableIntStateOf(0) }
    val backProgress = remember { Animatable(0f) }

    val density = LocalDensity.current
    val imeInsets = WindowInsets.ime
    val imeBottom = imeInsets.getBottom(density)
    val isLargeScreen = WindowDialogDefaults.isLargeScreen()
    val enterTransition = rememberDefaultDialogEnterTransition(isLargeScreen)
    val exitTransitionNullable: ExitTransition? = remember(isLargeScreen, imeBottom) {
        if (!isLargeScreen && imeBottom > 0) {
            slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(350, easing = DecelerateEasing(0.8f))
            )
        } else {
            null
        }
    }
    val effectiveExitTransition = exitTransitionNullable ?: rememberDefaultDialogExitTransition(isLargeScreen)

    val dismissPending = remember { mutableStateOf(false) }
    val outsideDismissDeferred = remember { mutableStateOf(false) }
    val currentOnDismissInternal by rememberUpdatedState(onDismissRequest)

    LaunchedEffect(show.value) {
        internalVisible.targetState = show.value
    }

    val requestDismiss: () -> Unit = remember {
        {
            dismissPending.value = true
            internalVisible.targetState = false
            currentOnDismissInternal?.invoke()
        }
    }

    Dialog(
        onDismissRequest = {
            requestDismiss()
        },
        properties = platformDialogProperties()
    ) {
        removePlatformDialogDefaultEffects()
        val windowSize = getWindowSize()
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

        AnimatedVisibility(
            visibleState = internalVisible,
            enter = enterTransition,
            exit = effectiveExitTransition
        ) {
            SuperDialogContent(
                modifier = modifier,
                title = title,
                titleColor = titleColor,
                summary = summary,
                summaryColor = summaryColor,
                backgroundColor = backgroundColor,
                outsideMargin = outsideMargin,
                insideMargin = insideMargin,
                defaultWindowInsetsPadding = defaultWindowInsetsPadding,
                backProgress = backProgress,
                dialogHeightPx = dialogHeightPx,
                onDismissRequest = {
                    if (internalVisible.currentState) {
                        requestDismiss()
                    } else {
                        outsideDismissDeferred.value = true
                    }
                },
                content = {
                    CompositionLocalProvider(LocalWindowDialogState provides { requestDismiss() }) {
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
                    backProgress.snapTo(event.progress)
                    dimAlpha.floatValue = 1f - event.progress
                }
                // Flow completed normally
                requestDismiss()
            } catch (_: CancellationException) {
                // Flow cancelled
                coroutineScope.launch {
                    backProgress.animateTo(0f, animationSpec = tween(durationMillis = 150))
                    animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
                        dimAlpha.floatValue = value
                    }
                }
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

@Composable
private fun rememberDefaultDialogEnterTransition(largeScreen: Boolean): EnterTransition {
    return remember(largeScreen) {
        if (largeScreen) {
            fadeIn(
                animationSpec = spring(dampingRatio = 0.82f, stiffness = 800f)
            ) + scaleIn(
                initialScale = 0.8f,
                animationSpec = spring(dampingRatio = 0.73f, stiffness = 800f)
            )
        } else {
            slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = spring(dampingRatio = 0.88f, stiffness = 450f)
            )
        }
    }
}

@Composable
private fun rememberDefaultDialogExitTransition(largeScreen: Boolean): ExitTransition {
    return remember(largeScreen) {
        if (largeScreen) {
            fadeOut(
                animationSpec = tween(200, easing = DecelerateEasing(1.5f))
            ) + scaleOut(
                targetScale = 0.8f,
                animationSpec = tween(200, easing = DecelerateEasing(1.5f))
            )
        } else {
            slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(300, easing = DecelerateEasing(0.8f))
            )
        }
    }
}

private val DialogDimEnter: EnterTransition =
    fadeIn(animationSpec = tween(300, easing = SinOutEasing))

private val DialogDimExit: ExitTransition =
    fadeOut(animationSpec = tween(250, easing = SinOutEasing))

object WindowDialogDefaults {
    @Composable
    internal fun isLargeScreen(): Boolean {
        val density = LocalDensity.current
        val windowSize = getWindowSize()
        val windowWidth by remember(windowSize, density) {
            derivedStateOf { windowSize.width.dp / density.density }
        }
        val windowHeight by remember(windowSize, density) {
            derivedStateOf { windowSize.height.dp / density.density }
        }
        val largeScreen by remember(windowWidth, windowHeight) {
            derivedStateOf { (windowHeight >= 480.dp && windowWidth >= 840.dp) }
        }
        return largeScreen
    }

    /**
     * The default color of the title.
     */
    @Composable
    fun titleColor() = MiuixTheme.colorScheme.onBackground

    /**
     * The default color of the summary.
     */
    @Composable
    fun summaryColor() = MiuixTheme.colorScheme.onSurfaceSecondary

    /**
     * The default background color of the [WindowDialog].
     */
    @Composable
    fun backgroundColor() = MiuixTheme.colorScheme.background

    /**
     * The default margin outside the [WindowDialog].
     */
    val outsideMargin = DpSize(12.dp, 12.dp)

    /**
     * The default margin inside the [WindowDialog].
     */
    val insideMargin = DpSize(24.dp, 24.dp)
}

val LocalWindowDialogState = staticCompositionLocalOf<() -> Unit> {
    error("LocalWindowDialogState not provided")
}
