// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
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
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.computedStateOf
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
import androidx.compose.ui.layout.onSizeChanged
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
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
 * @param dimColor The unanimated dimming color.
 * @param motion The enter and exit motion.
 * @param surface Optional replacement for the default surface.
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
    dimColor: Color = MiuixTheme.colorScheme.windowDimming,
    motion: DialogMotion = DialogMotion.Default,
    surface: (@Composable DialogSurfaceScope.() -> Modifier)? = null,
    content: @Composable () -> Unit,
) {
    // Slide (bottom-attached) or scale (centered) progress, 0 = hidden and 1 = at rest.
    val animationProgress = remember { Animatable(0f, visibilityThreshold = DIALOG_PROGRESS_THRESHOLD) }
    // Opacity of the centered panel; the bottom-attached panel stays opaque.
    val alphaProgress = remember { Animatable(0f, visibilityThreshold = DIALOG_PROGRESS_THRESHOLD) }
    val dimProgress = remember { Animatable(0f) }
    val currentOnDismissFinished by rememberUpdatedState(onDismissFinished)
    val currentMotion by rememberUpdatedState(motion)
    val internalVisible = remember { mutableStateOf(false) }
    // Whether the last opening animation ran to rest; a close before that is an interruption.
    val enterSettled = remember { mutableStateOf(false) }
    val blockTouches = remember { mutableStateOf(false) }

    val density = LocalDensity.current
    val imeInsets = WindowInsets.ime
    val keyboardController = LocalSoftwareKeyboardController.current
    val isLargeScreen = largeScreen ?: DialogDefaults.isLargeScreen()

    LaunchedEffect(show) {
        // Snapshot at launch so a window-resize crossing the breakpoint mid-animation does not
        // swap the spec or relaunch the effect.
        val largeAtStart = isLargeScreen
        val motionAtStart = currentMotion
        if (show) {
            internalVisible.value = true
            blockTouches.value = true
            enterSettled.value = false
            if (enableWindowDim) {
                launch { dimProgress.animateTo(1f, motionAtStart.dimEnterSpec()) }
            }
            val enterSpec = motionAtStart.enterSpec(largeAtStart)
            if (largeAtStart) {
                val fadeSpec = if (motionAtStart.isGlass) motionAtStart.glassFadeInSpec() else enterSpec
                launch { alphaProgress.animateTo(1f, fadeSpec) }
            } else {
                alphaProgress.snapTo(1f)
            }
            animationProgress.animateTo(targetValue = 1f, animationSpec = enterSpec)
            enterSettled.value = true
        } else {
            if (!internalVisible.value) return@LaunchedEffect
            if (imeInsets.getBottom(density) > 0) {
                keyboardController?.hide()
            }
            // The scrim may outlast the panel (the default motion fades it over 350 ms against a
            // 200 ms panel); like the platform dialog, it is cut when the panel finishes.
            val dimJob = if (enableWindowDim) {
                launch { dimProgress.animateTo(0f, motionAtStart.dimExitSpec()) }
            } else {
                null
            }
            val exitSpec = motionAtStart.exitSpec(interrupted = !enterSettled.value)
            val touchReleaseJob = launch {
                snapshotFlow {
                    if (largeAtStart && motionAtStart.isGlass) {
                        alphaProgress.value <= 0.33f
                    } else {
                        animationProgress.value <= 0.02f
                    }
                }.first { it }
                blockTouches.value = false
            }
            coroutineScope {
                when {
                    // The glass panel fades out at its current size.
                    largeAtStart && motionAtStart.isGlass -> alphaProgress.animateTo(0f, motionAtStart.glassFadeOutSpec())

                    largeAtStart -> {
                        launch { alphaProgress.animateTo(0f, exitSpec) }
                        animationProgress.animateTo(0f, exitSpec)
                    }

                    else -> animationProgress.animateTo(0f, exitSpec)
                }
            }
            touchReleaseJob.cancel()
            blockTouches.value = false
            dimJob?.cancel()
            dimProgress.snapTo(0f)
            animationProgress.snapTo(0f)
            alphaProgress.snapTo(0f)
            internalVisible.value = false
            currentOnDismissFinished?.invoke()
        }
    }

    if (!show && !internalVisible.value) return

    val coroutineScope = rememberCoroutineScope()
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dialogHeightPx = remember { mutableIntStateOf(0) }
    val dialogWidthPx = remember { mutableIntStateOf(0) }
    val backProgress = remember { Animatable(0f) }
    val scrimColor = remember(dimColor, enableWindowDim, dimAlpha, dimProgress) {
        computedStateOf {
            if (enableWindowDim) {
                dimColor.copy(alpha = (dimColor.alpha * dimAlpha.floatValue * dimProgress.value).coerceIn(0f, 1f))
            } else {
                Color.Transparent
            }
        }
    }
    // Scale of the centered panel's opening/closing animation, excluding predictive back.
    val enterScale = remember(isLargeScreen, animationProgress) {
        computedStateOf {
            if (isLargeScreen) {
                val start = currentMotion.startScale(dialogWidthPx.intValue, dialogHeightPx.intValue)
                start + (1f - start) * animationProgress.value
            } else {
                1f
            }
        }
    }
    val surfaceScale = remember(isLargeScreen, enterScale, backProgress) {
        computedStateOf {
            if (isLargeScreen) enterScale.value * (1f - 0.2f * backProgress.value) else 1f
        }
    }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(scrimColor.value)
                    },
            )
        }

        DialogContent(
            show = blockTouches.value,
            title = title,
            titleColor = titleColor,
            summary = summary,
            summaryColor = summaryColor,
            backgroundColor = backgroundColor,
            outsideMargin = outsideMargin,
            insideMargin = insideMargin,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            backProgress = backProgress,
            animationProgress = animationProgress,
            alphaProgress = alphaProgress,
            enterScale = enterScale,
            dialogHeightPx = dialogHeightPx,
            dialogWidthPx = dialogWidthPx,
            onDismissRequest = requestDismiss,
            isLargeScreen = isLargeScreen,
            modifier = modifier,
            topInset = topInset,
            maxWidth = maxWidth,
            cornerRadius = cornerRadius,
            scrimColor = scrimColor,
            surfaceScale = surfaceScale,
            surface = surface,
            content = {
                CompositionLocalProvider(LocalDismissState provides requestDismiss) {
                    content()
                }
            },
        )
    }
}

// These State parameters are deliberately read by the surface during drawing, not composition.
@Suppress("ktlint:compose:modifier-not-used-at-root", "ktlint:compose:state-param-check")
@Composable
internal fun DialogContent(
    show: Boolean,
    title: String?,
    titleColor: Color,
    summary: String?,
    summaryColor: Color,
    backgroundColor: Color,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    backProgress: Animatable<Float, *>,
    animationProgress: Animatable<Float, *>,
    alphaProgress: Animatable<Float, *>,
    enterScale: State<Float>,
    dialogHeightPx: MutableIntState,
    dialogWidthPx: MutableIntState,
    onDismissRequest: (() -> Unit)?,
    isLargeScreen: Boolean,
    scrimColor: State<Color>,
    surfaceScale: State<Float>,
    modifier: Modifier = Modifier,
    topInset: Dp? = null,
    maxWidth: Dp = DialogDefaults.MaxWidth,
    cornerRadius: Dp? = null,
    surface: (@Composable DialogSurfaceScope.() -> Modifier)? = null,
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
    val surfaceScope = remember(resolvedCornerRadius, scrimColor, surfaceScale) {
        DialogSurfaceScope(resolvedCornerRadius, scrimColor, surfaceScale)
    }
    val surfaceModifier = surface?.invoke(surfaceScope)
        ?: Modifier.squircleSurface(color = backgroundColor, cornerRadius = resolvedCornerRadius)

    val calculatedTopInset = topInset ?: with(density) {
        maxOf(
            WindowInsets.statusBars.getTop(this),
            WindowInsets.captionBar.getTop(this),
            WindowInsets.displayCutout.getTop(this),
        ).toDp()
    }

    // Slide distance and predictive-back translation pad (small-screen only). Pre-converted to px
    // so the per-frame graphicsLayer block does not call toPx() each invalidation.
    val outsideMarginBottomPx = remember(density, outsideMargin.height, isLargeScreen) {
        if (isLargeScreen) 0f else with(density) { outsideMargin.height.toPx() }
    }

    val imeInsets = WindowInsets.ime
    val navBarInsets = WindowInsets.navigationBars
    val captionBarInsets = WindowInsets.captionBar
    val contentModifier = Modifier
        .graphicsLayer {
            // Apply the open/close and predictive back animations; branch inside the block so the
            // modifier chain produces a single graphicsLayer node instead of swapping nodes per
            // recomposition. Must precede the user modifier so its draw modifiers follow the
            // transform (issue #373).
            if (isLargeScreen) {
                val scale = enterScale.value * (1f - backProgress.value * 0.2f)
                scaleX = scale
                scaleY = scale
                alpha = alphaProgress.value.coerceIn(0f, 1f)
            } else {
                // Rest the panel just below the screen edge when hidden, as the platform dialog
                // does: its own height plus the margin and insets beneath it, keyboard included.
                val anim = animationProgress.value
                val back = backProgress.value
                if (anim >= 1f && back <= 0f) {
                    translationY = 0f
                } else {
                    val insetBottom = if (defaultWindowInsetsPadding) {
                        maxOf(
                            imeInsets.getBottom(this),
                            navBarInsets.getBottom(this) + captionBarInsets.getBottom(this),
                        )
                    } else {
                        0
                    }
                    val offScreen = size.height + outsideMarginBottomPx + insetBottom
                    translationY = (1f - anim) * offScreen + back * offScreen
                }
                alpha = 1f
            }
        }
        .then(modifier)
        .widthIn(max = maxWidth)
        .heightIn(max = if (isLargeScreen) windowHeight * (2f / 3f) else Dp.Unspecified)
        .onSizeChanged { size ->
            dialogHeightPx.intValue = size.height
            dialogWidthPx.intValue = size.width
        }
        .then(
            if (show) {
                Modifier.pointerInput(Unit) {
                    detectTapGestures { /* Consume click */ }
                }
            } else {
                Modifier
            },
        )
        .then(surfaceModifier)
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
            .then(
                if (show) {
                    Modifier
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
                } else {
                    Modifier
                },
            )
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
    @ReadOnlyComposable
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
    @ReadOnlyComposable
    fun titleColor() = MiuixTheme.colorScheme.onBackground

    /**
     * The default color of the summary.
     */
    @Composable
    @ReadOnlyComposable
    fun summaryColor() = MiuixTheme.colorScheme.onSurfaceSecondary

    /**
     * The default background color of the dialog.
     */
    @Composable
    @ReadOnlyComposable
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
