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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.PredictiveBackHandler
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.anim.SinOutEasing
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getRoundedCorner
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
    titleColor: Color = SuperDialogDefaults.titleColor(),
    summary: String? = null,
    summaryColor: Color = SuperDialogDefaults.summaryColor(),
    backgroundColor: Color = SuperDialogDefaults.backgroundColor(),
    onDismissRequest: (() -> Unit)? = null,
    outsideMargin: DpSize = SuperDialogDefaults.outsideMargin,
    insideMargin: DpSize = SuperDialogDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    content: @Composable () -> Unit
) {
    if (!show.value) return

    val coroutineScope = rememberCoroutineScope()
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dialogHeightPx = remember { mutableIntStateOf(0) }
    val backProgress = remember { Animatable(0f) }

    val density = LocalDensity.current
    val imeInsets = WindowInsets.ime
    val imeBottom = imeInsets.getBottom(density)
    val isLargeScreen = SuperDialogDefaults.isLargeScreen()
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

    val internalVisible = remember { MutableTransitionState(false) }
    val dismissPending = remember { mutableStateOf(false) }
    val outsideDismissDeferred = remember { mutableStateOf(false) }
    val currentOnDismissInternal by rememberUpdatedState(onDismissRequest)

    fun requestDismiss() {
        dismissPending.value = true
        internalVisible.targetState = false
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
        val largeScreen by remember(windowWidth, windowHeight) {
            derivedStateOf { (windowHeight >= 480.dp && windowWidth >= 840.dp) }
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
            val roundedCorner = getRoundedCorner()
            val bottomCornerRadius = if (roundedCorner != 0.dp) {
                roundedCorner - outsideMargin.width
            } else {
                32.dp
            }

            val rootBoxModifier = Modifier
                .then(
                    if (defaultWindowInsetsPadding)
                        Modifier
                            .imePadding()
                            .navigationBarsPadding()
                            .captionBarPadding()
                    else
                        Modifier
                )
                .fillMaxSize()
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
                .padding(horizontal = outsideMargin.width)
                .padding(bottom = outsideMargin.height)

            val columnModifier = modifier
                .widthIn(max = 420.dp)
                .heightIn(max = if (largeScreen) windowHeight * (2f / 3f) else Dp.Unspecified)
                .onGloballyPositioned { coordinates ->
                    dialogHeightPx.value = coordinates.size.height
                }
                .then(
                    if (largeScreen) {
                        Modifier.graphicsLayer {
                            val scale = 1f - (backProgress.value * 0.2f)
                            scaleX = scale
                            scaleY = scale
                        }
                    } else {
                        val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
                                WindowInsets.captionBar.asPaddingValues().calculateBottomPadding()
                        val extraBottomPadding by remember(bottomPadding, outsideMargin.height) {
                            derivedStateOf {
                                bottomPadding + outsideMargin.height
                            }
                        }
                        Modifier.graphicsLayer {
                            val maxOffset = if (dialogHeightPx.value > 0) {
                                dialogHeightPx.value.toFloat() + extraBottomPadding.toPx()
                            } else {
                                500f
                            }
                            translationY = backProgress.value * maxOffset
                        }
                    }
                )
                .pointerInput(Unit) {
                    detectTapGestures { /* Consume click to prevent dismissal */ }
                }
                .clip(ContinuousRoundedRectangle(bottomCornerRadius))
                .background(backgroundColor)
                .padding(horizontal = insideMargin.width, vertical = insideMargin.height)

            Box(
                modifier = rootBoxModifier
            ) {
                Column(modifier = columnModifier.align(if (largeScreen) Alignment.Center else Alignment.BottomCenter)) {
                    title?.let {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                            text = it,
                            fontSize = MiuixTheme.textStyles.title4.fontSize,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = titleColor
                        )
                    }
                    summary?.let {
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                            text = it,
                            fontSize = MiuixTheme.textStyles.body1.fontSize,
                            textAlign = TextAlign.Center,
                            color = summaryColor
                        )
                    }
                    CompositionLocalProvider(LocalWindowDialogState provides { requestDismiss() }) {
                        content()
                    }
                }
            }
        }
        PredictiveBackHandler(
            enabled = show.value
        ) { progress ->
            try {
                progress.collect { event ->
                    backProgress.snapTo(event.progress)
                    dimAlpha.floatValue = 1f - event.progress
                }
                requestDismiss()
            } catch (_: CancellationException) {
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

    if (!internalVisible.currentState && !internalVisible.targetState && dismissPending.value) {
        dismissPending.value = false
        show.value = false
        currentOnDismissInternal?.invoke()
    }
}

/**
 * Remembers the default enter transition for [WindowDialog].
 *
 * - Large screen: fade + scale-in.
 * - Small screen: slide-in from bottom.
 *
 * @param largeScreen Whether current window is considered large screen.
 * @return The enter [EnterTransition] for dialog content.
 */
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

/**
 * Remembers the default exit transition for [WindowDialog].
 *
 * - Large screen: fade + scale-out.
 * - Small screen: slide-out to bottom.
 *
 * @param largeScreen Whether current window is considered large screen.
 * @return The exit [ExitTransition] for dialog content.
 */
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

/**
 * Default enter transition for dim layer of [WindowDialog].
 */
private val DialogDimEnter: EnterTransition =
    fadeIn(animationSpec = tween(300, easing = SinOutEasing))
/**
 * Default exit transition for dim layer of [WindowDialog].
 */
private val DialogDimExit: ExitTransition =
    fadeOut(animationSpec = tween(250, easing = SinOutEasing))

/**
 * CompositionLocal that provides a dismiss request function for [WindowDialog].
 *
 * Call the provided function to request dismissal from inside dialog content.
 */
val LocalWindowDialogState = staticCompositionLocalOf<(() -> Unit)?> { null }
