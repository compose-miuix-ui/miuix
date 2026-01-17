// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.RemovePlatformDialogDefaultEffects
import top.yukonga.miuix.kmp.utils.platformDialogProperties

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
 * @param onDismissRequest Will called when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * @param onDismissFinished The callback when the [SuperDialog] is completely dismissed.
 * @param outsideMargin The margin outside the [WindowDialog].
 * @param insideMargin The margin inside the [WindowDialog].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding to the [WindowDialog].
 * @param content The [Composable] content of the [WindowDialog].
 */
@Suppress("ktlint:compose:modifier-not-used-at-root")
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
    onDismissFinished: (() -> Unit)? = null,
    outsideMargin: DpSize = WindowDialogDefaults.outsideMargin,
    insideMargin: DpSize = WindowDialogDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    content: @Composable () -> Unit,
) {
    val statusBarsPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val captionBarPadding = WindowInsets.captionBar.asPaddingValues().calculateTopPadding()
    val displayCutoutPadding = WindowInsets.displayCutout.asPaddingValues().calculateTopPadding()
    val safeTopInset = remember(statusBarsPadding, captionBarPadding, displayCutoutPadding) {
        maxOf(statusBarsPadding, captionBarPadding, displayCutoutPadding)
    }

    val animationProgress = remember { Animatable(0f, visibilityThreshold = 0.0001f) }
    var isAnimating by remember { mutableStateOf(false) }

    if (!show.value && !isAnimating && animationProgress.value == 0f) return

    val coroutineScope = rememberCoroutineScope()
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dialogHeightPx = remember { mutableIntStateOf(0) }
    val backProgress = remember { Animatable(0f) }

    val density = LocalDensity.current
    val imeInsets = WindowInsets.ime
    val isLargeScreen = WindowDialogDefaults.isLargeScreen()
    val windowInfo = LocalWindowInfo.current
    val windowHeightPx = with(density) { windowInfo.containerDpSize.height.toPx() }

    val currentOnDismissInternal by rememberUpdatedState(onDismissRequest)
    val currentOnDismissFinished by rememberUpdatedState(onDismissFinished)
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(show.value) {
        if (!show.value) {
            if (imeInsets.getBottom(density) > 0) {
                keyboardController?.hide()
            }
        }
        isAnimating = true
        val target = if (show.value) 1f else 0f
        animationProgress.animateTo(
            targetValue = target,
            animationSpec = if (show.value) {
                if (isLargeScreen) {
                    tween(durationMillis = 300, easing = DecelerateEasing(1.5f))
                } else {
                    spring(dampingRatio = 0.86f, stiffness = 450f, visibilityThreshold = 0.0001f)
                }
            } else {
                if (isLargeScreen) {
                    tween(durationMillis = 200, easing = DecelerateEasing(1.5f))
                } else {
                    tween(durationMillis = 150, easing = DecelerateEasing(0.8f))
                }
            },
        )
        isAnimating = false
        if (!show.value && animationProgress.value == 0f) {
            currentOnDismissFinished?.invoke()
        }
    }

    val requestDismiss: () -> Unit = remember {
        {
            currentOnDismissInternal?.invoke()
        }
    }

    val resetGesture: suspend () -> Unit = {
        backProgress.animateTo(0f, animationSpec = tween(durationMillis = 150))
        animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
            dimAlpha.floatValue = value
        }
    }

    Dialog(
        onDismissRequest = {
            requestDismiss()
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
                requestDismiss()
            },
        )

        LaunchedEffect(navigationEventState.transitionState) {
            val transitionState = navigationEventState.transitionState
            if (
                transitionState is NavigationEventTransitionState.InProgress &&
                transitionState.direction == NavigationEventTransitionState.TRANSITIONING_BACK
            ) {
                val progress = transitionState.latestEvent.progress
                backProgress.snapTo(progress)
                dimAlpha.floatValue = 1f - progress
            }
        }

        val progress = animationProgress.value
        val baseColor = MiuixTheme.colorScheme.windowDimming
        val dimColor = baseColor.copy(alpha = (baseColor.alpha * dimAlpha.floatValue * progress))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(dimColor),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(show.value) {
                    detectTapGestures(
                        onTap = {
                            requestDismiss()
                        },
                    )
                },

        ) {
            val contentModifier = modifier.graphicsLayer {
                if (isLargeScreen) {
                    val scale = 0.8f + 0.2f * progress
                    scaleX = scale
                    scaleY = scale
                    alpha = progress
                } else {
                    translationY = (1f - progress) * windowHeightPx
                    alpha = 1f
                }
            }
            SuperDialogContent(
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
                    requestDismiss()
                },
                modifier = contentModifier,
                topInset = safeTopInset,
                content = {
                    CompositionLocalProvider(LocalWindowDialogState provides { requestDismiss() }) {
                        content()
                    }
                },
            )
        }
    }
}

object WindowDialogDefaults {
    @Composable
    internal fun isLargeScreen(): Boolean {
        val windowInfo = LocalWindowInfo.current
        val windowWidth = windowInfo.containerDpSize.width
        val windowHeight = windowInfo.containerDpSize.height
        return windowHeight >= 480.dp && windowWidth >= 840.dp
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
