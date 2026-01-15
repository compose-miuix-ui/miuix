// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.DialogLayout
import top.yukonga.miuix.kmp.utils.getRoundedCorner

/**
 * A dialog with a title, a summary, and other contents.
 *
 * @param show The show state of the [SuperDialog].
 * @param modifier The modifier to be applied to the [SuperDialog].
 * @param title The title of the [SuperDialog].
 * @param titleColor The color of the title.
 * @param summary The summary of the [SuperDialog].
 * @param summaryColor The color of the summary.
 * @param backgroundColor The background color of the [SuperDialog].
 * @param onDismissRequest Will called when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * @param onDismissFinished The callback when the [SuperDialog] is completely dismissed.
 * @param outsideMargin The margin outside the [SuperDialog].
 * @param insideMargin The margin inside the [SuperDialog].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding to the [SuperDialog].
 * @param content The [Composable] content of the [SuperDialog].
 */
@Suppress("ktlint:compose:modifier-not-used-at-root")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SuperDialog(
    show: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: Color = SuperDialogDefaults.titleColor(),
    summary: String? = null,
    summaryColor: Color = SuperDialogDefaults.summaryColor(),
    backgroundColor: Color = SuperDialogDefaults.backgroundColor(),
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    outsideMargin: DpSize = SuperDialogDefaults.outsideMargin,
    insideMargin: DpSize = SuperDialogDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    content: @Composable () -> Unit,
) {
    if (!show.value) return

    val coroutineScope = rememberCoroutineScope()
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dialogHeightPx = remember { mutableIntStateOf(0) }
    val backProgress = remember { Animatable(0f) }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)

    val resetGesture: suspend () -> Unit = {
        backProgress.animateTo(0f, animationSpec = tween(durationMillis = 150))
        animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
            dimAlpha.floatValue = value
        }
    }

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
            currentOnDismissRequest?.invoke()
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

    val density = LocalDensity.current
    val imeInsets = WindowInsets.ime
    val imeBottom = imeInsets.getBottom(density)
    val isLargeScreen = SuperDialogDefaults.isLargeScreen()
    val exitTransition: ExitTransition? = remember(isLargeScreen, imeBottom) {
        if (!isLargeScreen && imeBottom > 0) {
            slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(350, easing = DecelerateEasing(0.8f)),
            )
        } else {
            null
        }
    }

    DialogLayout(
        visible = show,
        enableWindowDim = enableWindowDim,
        dimAlpha = dimAlpha,
        exitTransition = exitTransition,
        onDismissFinished = onDismissFinished,
    ) {
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
            onDismissRequest = currentOnDismissRequest,
            modifier = modifier,
            content = content,
        )
    }
}

@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
internal fun SuperDialogContent(
    title: String?,
    titleColor: Color,
    summary: String?,
    summaryColor: Color,
    backgroundColor: Color,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    backProgress: Animatable<Float, *>,
    dialogHeightPx: MutableState<Int>,
    onDismissRequest: (() -> Unit)?,
    modifier: Modifier = Modifier,
    topInset: Dp? = null,
    content: @Composable () -> Unit,
) {
    val windowInfo = LocalWindowInfo.current
    val windowHeight = windowInfo.containerDpSize.height
    val roundedCorner = getRoundedCorner()
    val bottomCornerRadius by remember(roundedCorner, outsideMargin.width) {
        derivedStateOf {
            if (roundedCorner != 0.dp) roundedCorner - outsideMargin.width else 32.dp
        }
    }
    val isLargeScreen = SuperDialogDefaults.isLargeScreen()
    val contentAlignment by remember(isLargeScreen) {
        derivedStateOf { if (isLargeScreen) Alignment.Center else Alignment.BottomCenter }
    }

    val currentOnDismiss by rememberUpdatedState(onDismissRequest)

    val calculatedTopInset = if (topInset != null) {
        topInset
    } else {
        val statusBars = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        val captionBar = WindowInsets.captionBar.asPaddingValues().calculateTopPadding()
        val displayCutout = WindowInsets.displayCutout.asPaddingValues().calculateTopPadding()
        maxOf(statusBars, captionBar, displayCutout)
    }

    val modifier = modifier
        .widthIn(max = 420.dp)
        .heightIn(max = if (isLargeScreen) windowHeight * (2f / 3f) else Dp.Unspecified)
        .onGloballyPositioned { coordinates ->
            dialogHeightPx.value = coordinates.size.height
        }
        .then(
            // Apply predictive back animation
            if (isLargeScreen) {
                // Large screen
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
                // Small screen
                Modifier.graphicsLayer {
                    val maxOffset = if (dialogHeightPx.value > 0) {
                        dialogHeightPx.value.toFloat() + extraBottomPadding.toPx()
                    } else {
                        500f
                    }
                    translationY = backProgress.value * maxOffset
                }
            },
        )
        .pointerInput(Unit) {
            detectTapGestures { /* Consume click */ }
        }
        .clip(ContinuousRoundedRectangle(bottomCornerRadius))
        .background(backgroundColor)
        .padding(horizontal = insideMargin.width, vertical = insideMargin.height)

    Box(
        modifier = Modifier
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier
                        .imePadding()
                        .navigationBarsPadding()
                        .captionBarPadding()
                } else {
                    Modifier
                },
            )
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { currentOnDismiss?.invoke() },
                )
            }
            .padding(horizontal = outsideMargin.width)
            .padding(top = calculatedTopInset, bottom = outsideMargin.height),
    ) {
        Column(
            modifier = modifier.align(contentAlignment),
        ) {
            title?.let {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    text = it,
                    fontSize = MiuixTheme.textStyles.title4.fontSize,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = titleColor,
                )
            }
            summary?.let {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    text = it,
                    fontSize = MiuixTheme.textStyles.body1.fontSize,
                    textAlign = TextAlign.Center,
                    color = summaryColor,
                )
            }
            content()
        }
    }
}

object SuperDialogDefaults {
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
     * The default background color of the [SuperDialog].
     */
    @Composable
    fun backgroundColor() = MiuixTheme.colorScheme.background

    /**
     * The default margin outside the [SuperDialog].
     */
    val outsideMargin = DpSize(12.dp, 12.dp)

    /**
     * The default margin inside the [SuperDialog].
     */
    val insideMargin = DpSize(24.dp, 24.dp)
}
