// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
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
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.anim.SinOutEasing
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getRoundedCorner
import top.yukonga.miuix.kmp.utils.getWindowSize
import top.yukonga.miuix.kmp.utils.platformDialogProperties
import top.yukonga.miuix.kmp.utils.removePlatformDialogDefaultEffects

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

    val isLargeScreen = SuperDialogDefaults.isLargeScreen()
    val enterTransition = rememberDefaultDialogEnterTransition(isLargeScreen)
    val exitTransition = rememberDefaultDialogExitTransition(isLargeScreen)

    val internalVisible = remember { MutableTransitionState(false) }
    val dismissPending = remember { mutableStateOf(false) }
    val currentOnDismiss by rememberUpdatedState(onDismissRequest)

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

        if (!internalVisible.currentState && !internalVisible.targetState) {
            internalVisible.targetState = true
        }

        AnimatedVisibility(
            visibleState = internalVisible,
            enter = DialogDimEnter,
            exit = DialogDimExit
        ) {
            Box(
                modifier = Modifier
                    .widthIn(min = windowWidth, max = windowWidth)
                    .heightIn(min = windowHeight, max = windowHeight)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                requestDismiss()
                            }
                        )
                    }
                    .background(MiuixTheme.colorScheme.windowDimming)
            )
        }

        AnimatedVisibility(
            visibleState = internalVisible,
            enter = enterTransition,
            exit = exitTransition
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
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            requestDismiss()
                        }
                    )
                }
                .padding(horizontal = outsideMargin.width)
                .padding(bottom = outsideMargin.height)

            val columnModifier = modifier
                .widthIn(max = 420.dp)
                .heightIn(max = if (largeScreen) windowHeight * (2f / 3f) else Dp.Unspecified)
                .onGloballyPositioned { }
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
                    content()
                }
            }
        }
    }

    if (!internalVisible.currentState && !internalVisible.targetState && dismissPending.value) {
        dismissPending.value = false
        show.value = false
        currentOnDismiss?.invoke()
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
