// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.anim.folmeSpring
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.overlay.OverlayDialog
import top.yukonga.miuix.kmp.squircle.squircleSurface
import top.yukonga.miuix.kmp.theme.LocalDismissState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getRoundedCorner
import top.yukonga.miuix.kmp.window.WindowDialog

/**
 * Internal shared layout logic for [OverlayDialog] and [WindowDialog].
 *
 * @param show Whether the dialog is currently shown.
 * @param titleColor The color of the title.
 * @param summaryColor The color of the summary.
 * @param backgroundColor The background color of the dialog.
 * @param outsideMargin The margin outside the dialog.
 * @param insideMargin The margin inside the dialog.
 * @param popupHost A composable that provides the dialog container (e.g., DialogLayout or Dialog).
 *   It receives the visibility state and the inner content composable.
 * @param modifier The modifier to be applied to the dialog content.
 * @param title The title of the dialog.
 * @param summary The summary of the dialog.
 * @param enableWindowDim Whether to enable window dimming.
 * @param onDismissRequest The callback when the dialog is dismissed.
 * @param onDismissFinished Invoked when the hide animation completes; not invoked if the hide
 *   is cancelled mid-flight (e.g., by [show] toggling back to true).
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param topInset Optional top inset override. If null, calculated from window insets.
 * @param maxWidth The maximum width of the dialog.
 * @param largeScreen Optional override for the large-screen presentation (centered scale/fade
 *   instead of bottom slide-in). If null, detected from the window size.
 * @param cornerRadius Optional corner radius override. If null, [DialogDefaults.CornerRadius]
 *   for the centered presentation, or derived from the screen corner radius (clamped to
 *   32dp..48dp) when bottom-attached.
 * @param content The content of the dialog.
 */
@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
internal fun DialogContentLayout(
    show: Boolean,
    titleColor: Color,
    summaryColor: Color,
    backgroundColor: Color,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    popupHost: @Composable (visible: Boolean, content: @Composable () -> Unit) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    summary: String? = null,
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    defaultWindowInsetsPadding: Boolean = true,
    topInset: Dp? = null,
    maxWidth: Dp = DialogDefaults.MaxWidth,
    largeScreen: Boolean? = null,
    cornerRadius: Dp? = null,
    content: @Composable () -> Unit,
) {
    val animationProgress = remember { Animatable(0f, visibilityThreshold = 0.0001f) }
    val dimProgress = remember { Animatable(0f) }
    val currentOnDismissFinished by rememberUpdatedState(onDismissFinished)
    val internalVisible = remember { mutableStateOf(false) }

    val density = LocalDensity.current
    val imeInsets = WindowInsets.ime
    val keyboardController = LocalSoftwareKeyboardController.current
    val isLargeScreen = largeScreen ?: DialogDefaults.isLargeScreen()

    LaunchedEffect(show) {
        // Snapshot at launch so a window-resize crossing the breakpoint mid-animation does not
        // swap the spec or relaunch the effect.
        val largeAtStart = isLargeScreen
        if (show) {
            internalVisible.value = true
            if (enableWindowDim) {
                launch { dimProgress.animateTo(1f, tween(300, easing = DecelerateEasing(1.5f))) }
            }
            animationProgress.animateTo(
                targetValue = 1f,
                animationSpec = if (largeAtStart) {
                    folmeSpring(damping = 0.9f, response = 0.3f)
                } else {
                    spring(dampingRatio = 0.88f, stiffness = 450f, visibilityThreshold = 0.0001f)
                },
            )
        } else {
            if (!internalVisible.value) return@LaunchedEffect
            if (imeInsets.getBottom(density) > 0) {
                keyboardController?.hide()
            }
            if (enableWindowDim) {
                launch { dimProgress.animateTo(0f, tween(250, easing = DecelerateEasing(1.5f))) }
            }
            animationProgress.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 260, easing = DecelerateEasing(1.5f)),
            )
            dimProgress.snapTo(0f)
            internalVisible.value = false
            currentOnDismissFinished?.invoke()
        }
    }

    if (!show && !internalVisible.value) return

    val coroutineScope = rememberCoroutineScope()
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dialogHeightPx = remember { mutableIntStateOf(0) }
    val backProgress = remember { Animatable(0f) }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)

    val windowInfo = LocalWindowInfo.current

    val requestDismiss: () -> Unit = remember {
        { currentOnDismissRequest?.invoke() }
    }

    val resetGesture: suspend () -> Unit = remember {
        {
            backProgress.animateTo(0f, animationSpec = tween(durationMillis = 150))
            animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
                dimAlpha.floatValue = value
            }
        }
    }

    popupHost(internalVisible.value) {
        val navigationEventState = rememberNavigationEventState(currentInfo = NavigationEventInfo.None)
        NavigationBackHandler(
            state = navigationEventState,
            isBackEnabled = show,
            onBackCancelled = {
                coroutineScope.launch { resetGesture() }
            },
            onBackCompleted = { requestDismiss() },
        )

        LaunchedEffect(Unit) {
            // Collect inside a single coroutine so the per-frame `transitionState` ticks during a
            // back gesture do not cancel/relaunch the LaunchedEffect on every progress update.
            snapshotFlow { navigationEventState.transitionState }
                .collect { transitionState ->
                    if (
                        transitionState is NavigationEventTransitionState.InProgress &&
                        transitionState.direction == NavigationEventTransitionState.TRANSITIONING_BACK
                    ) {
                        val progress = transitionState.latestEvent.progress
                        backProgress.snapTo(progress)
                        dimAlpha.floatValue = 1f - progress
                    }
                }
        }

        if (enableWindowDim) {
            val baseColor = MiuixTheme.colorScheme.windowDimming
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(baseColor.copy(alpha = baseColor.alpha * dimAlpha.floatValue * dimProgress.value))
                    },
            )
        }

        // Must precede the user modifier: draw modifiers before a graphicsLayer don't follow its transform (issue #373).
        val contentModifier = Modifier
            .graphicsLayer {
                val progress = animationProgress.value
                if (isLargeScreen) {
                    val scale = 0.8f + 0.2f * progress
                    scaleX = scale
                    scaleY = scale
                    alpha = progress
                } else {
                    translationY = (1f - progress) * windowInfo.containerDpSize.height.toPx()
                    alpha = 1f
                }
            }
            .then(modifier)

        DialogContent(
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
            onDismissRequest = requestDismiss,
            isLargeScreen = isLargeScreen,
            modifier = contentModifier,
            topInset = topInset,
            maxWidth = maxWidth,
            cornerRadius = cornerRadius,
            content = {
                CompositionLocalProvider(LocalDismissState provides requestDismiss) {
                    content()
                }
            },
        )
    }
}

@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
internal fun DialogContent(
    title: String?,
    titleColor: Color,
    summary: String?,
    summaryColor: Color,
    backgroundColor: Color,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    backProgress: Animatable<Float, *>,
    dialogHeightPx: MutableIntState,
    onDismissRequest: (() -> Unit)?,
    isLargeScreen: Boolean,
    modifier: Modifier = Modifier,
    topInset: Dp? = null,
    maxWidth: Dp = DialogDefaults.MaxWidth,
    cornerRadius: Dp? = null,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val windowHeight = windowInfo.containerDpSize.height
    val contentAlignment = remember(isLargeScreen) {
        if (isLargeScreen) Alignment.Center else Alignment.BottomCenter
    }
    val roundedCorner = getRoundedCorner()
    val resolvedCornerRadius = cornerRadius ?: remember(roundedCorner, outsideMargin.width, isLargeScreen) {
        if (isLargeScreen) {
            // A centered dialog does not hug the screen corners, so the concentric
            // screen-radius derivation below does not apply.
            DialogDefaults.CornerRadius
        } else {
            (roundedCorner - outsideMargin.width).coerceIn(32.dp, 48.dp)
        }
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

    // Predictive-back translation pad (small-screen only). Pre-converted to px so the per-frame
    // graphicsLayer block does not call asPaddingValues() / toPx() each invalidation.
    val bottomPadding = if (isLargeScreen) {
        0.dp
    } else {
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
            WindowInsets.captionBar.asPaddingValues().calculateBottomPadding()
    }
    val extraBottomPaddingPx = remember(density, bottomPadding, outsideMargin.height, isLargeScreen) {
        if (isLargeScreen) 0f else with(density) { (bottomPadding + outsideMargin.height).toPx() }
    }

    val contentModifier = Modifier
        .graphicsLayer {
            // Apply predictive back animation; branch inside the block so the modifier chain
            // produces a single graphicsLayer node instead of swapping nodes per recomposition.
            // Must precede the user modifier so its draw modifiers follow the transform (issue #373).
            if (isLargeScreen) {
                val scale = 1f - (backProgress.value * 0.2f)
                scaleX = scale
                scaleY = scale
            } else {
                val maxOffset = if (dialogHeightPx.intValue > 0) {
                    dialogHeightPx.intValue.toFloat() + extraBottomPaddingPx
                } else {
                    500f
                }
                translationY = backProgress.value * maxOffset
            }
        }
        .then(modifier)
        .widthIn(max = maxWidth)
        .heightIn(max = if (isLargeScreen) windowHeight * (2f / 3f) else Dp.Unspecified)
        .onGloballyPositioned { coordinates ->
            dialogHeightPx.intValue = coordinates.size.height
        }
        .pointerInput(Unit) {
            detectTapGestures { /* Consume click */ }
        }
        .squircleSurface(color = backgroundColor, cornerRadius = resolvedCornerRadius)
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
            .semantics {
                onClick(label = "Dismiss") {
                    currentOnDismiss?.invoke()
                    true
                }
            }
            .padding(horizontal = outsideMargin.width)
            .padding(top = calculatedTopInset, bottom = outsideMargin.height),
    ) {
        Column(
            modifier = contentModifier.align(contentAlignment),
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

object DialogDefaults {
    /**
     * Window-size threshold above which the dialog is centered (instead of bottom-aligned)
     * and uses scale-in transitions. Roughly aligns with the Window Size Class
     * compact -> expanded boundary (840 dp width / 480 dp height).
     */
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
     * The default background color of the dialog.
     */
    @Composable
    fun backgroundColor() = MiuixTheme.colorScheme.background

    /**
     * The default upper bound on dialog content width. Keeps dialogs from stretching across
     * tablet / desktop windows.
     */
    val MaxWidth = 420.dp

    /**
     * The default corner radius of the dialog in the centered (large-screen) presentation.
     * The bottom-attached presentation derives its radius from the screen corner radius instead.
     */
    val CornerRadius = 32.dp

    /**
     * The default margin outside the dialog.
     */
    val outsideMargin = DpSize(12.dp, 12.dp)

    /**
     * The default margin inside the dialog.
     */
    val insideMargin = DpSize(24.dp, 24.dp)
}
