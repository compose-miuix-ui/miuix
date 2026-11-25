// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.ProgressiveBlur
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.internal.GlassHeaderContext
import top.yukonga.miuix.kmp.glass.internal.LocalGlassHeaderContext
import top.yukonga.miuix.kmp.layout.BottomSheetDefaults
import top.yukonga.miuix.kmp.layout.BottomSheetOverlayLayout
import top.yukonga.miuix.kmp.layout.BottomSheetSurfaceScope
import top.yukonga.miuix.kmp.layout.SheetMotion
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported

private val SheetHeaderProgressiveGradient = ProgressiveBlur.Top.copy(curve = 0.25f)

/**
 * A Scaffold-hosted glass bottom sheet with an animated scrim, drag dismissal and predictive Back.
 *
 * The [backdrop] must include the page and bars but exclude the sheet host and its scrim.
 * A `null` backdrop or unavailable runtime shaders use [containerColor]. The glass extends to the
 * screen edge and fills upward overscroll. Apply navigation-bar padding inside scrollable content
 * when that spacing should scroll with the content.
 *
 * When [progressiveBlur] is enabled, the header overlays the sheet body with a progressive blur band
 * during scroll; apply the provided [PaddingValues] to the scrollable container so initial content
 * clears the header.
 *
 * It opens, closes and settles with [SheetMotion.Glass], the HyperOS launcher's sheet motion.
 *
 * @param onDismissRequest Called on an allowed outside tap, drag dismissal or back gesture.
 * @param show Whether the sheet is shown.
 * @param backdrop Page backdrop; `null` uses an opaque fill.
 * @param modifier Modifier applied to the sheet panel.
 * @param enableWindowDim Whether to draw and sample the animated [dimColor].
 * @param defaultWindowInsetsPadding Whether to apply the host's default window insets, including IME padding.
 * @param allowDismiss Whether outside taps, drags and back gestures may dismiss the sheet.
 * @param enableNestedScroll Whether child scrolling can transfer motion to the sheet.
 * @param renderInRootScaffold Whether the outermost Scaffold hosts the sheet.
 * @param progressiveBlur Overlay the header with a progressive blur band while scrolling.
 * @param title Optional title; header shows if any of [title], [startAction], or [endAction] is non-null.
 * @param startAction Optional leading header action.
 * @param endAction Optional trailing header action.
 * @param headerBottomContent Content pinned below the title inside the header.
 * @param cornerRadius Top-corner radius; bottom corners remain square.
 * @param sheetMaxWidth Maximum panel width.
 * @param outsideMargin Panel external margins.
 * @param insideMargin Panel content padding.
 * @param dragHandleColor Handle color before its press opacity is applied.
 * @param material Blur input and ordered color layers.
 * @param stroke Optional bloom stroke, omitted on the overscroll extension.
 * @param containerColor Opaque fallback fill.
 * @param dimColor Scrim color before the host applies animation progress.
 * @param onDismissFinished Called after the hide animation completes.
 * @param scrollOffset External scroll offset in pixels (e.g. active pager page); `null` tracks nested scroll.
 * @param content Sheet content receiving top header padding as [PaddingValues].
 */
@Composable
fun GlassBottomSheet(
    onDismissRequest: () -> Unit,
    show: Boolean,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    enableWindowDim: Boolean = true,
    defaultWindowInsetsPadding: Boolean = true,
    allowDismiss: Boolean = true,
    enableNestedScroll: Boolean = true,
    renderInRootScaffold: Boolean = true,
    progressiveBlur: Boolean = true,
    title: String? = null,
    startAction: @Composable (() -> Unit)? = null,
    endAction: @Composable (() -> Unit)? = null,
    headerBottomContent: @Composable () -> Unit = {},
    cornerRadius: Dp = BottomSheetDefaults.cornerRadius,
    sheetMaxWidth: Dp = BottomSheetDefaults.maxWidth,
    outsideMargin: DpSize = BottomSheetDefaults.outsideMargin,
    insideMargin: DpSize = GlassBottomSheetDefaults.InsideMargin,
    dragHandleColor: Color = BottomSheetDefaults.dragHandleColor(),
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    stroke: EdgeStroke? = GlassBottomSheetDefaults.stroke(),
    containerColor: Color = GlassBottomSheetDefaults.containerColor(),
    dimColor: Color = GlassBottomSheetDefaults.ScrimColor,
    onDismissFinished: (() -> Unit)? = null,
    scrollOffset: (() -> Float)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    if (!progressiveBlur) {
        BottomSheetOverlayLayout(
            show = show,
            modifier = modifier,
            title = title,
            startAction = startAction,
            endAction = endAction,
            headerBottomContent = headerBottomContent,
            backgroundColor = containerColor,
            enableWindowDim = enableWindowDim,
            cornerRadius = cornerRadius,
            sheetMaxWidth = sheetMaxWidth,
            onDismissRequest = onDismissRequest,
            onDismissFinished = onDismissFinished,
            outsideMargin = outsideMargin,
            insideMargin = insideMargin,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            dragHandleColor = dragHandleColor,
            allowDismiss = allowDismiss,
            enableNestedScroll = enableNestedScroll,
            renderInRootScaffold = renderInRootScaffold,
            dimColor = dimColor,
            motion = SheetMotion.Glass,
            overlayHeader = false,
            surface = glassBottomSheetSurface(backdrop, material, stroke, containerColor),
            content = content,
        )
        return
    }

    val blurActive = backdrop != null && isRuntimeShaderSupported()
    val sheetSurfaceBackdrop = if (blurActive) rememberLayerBackdrop() else null
    var bodyCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }
    val sheetSceneBackdrop = if (blurActive && sheetSurfaceBackdrop != null) {
        rememberLayerBackdrop {
            val coords = bodyCoordinates?.takeIf { it.isAttached }
            with(sheetSurfaceBackdrop) {
                drawBackdrop(this@rememberLayerBackdrop, coords)
            }
            drawContent()
        }
    } else {
        null
    }
    val density = LocalDensity.current
    val rampDistancePx = with(density) { GlassBottomSheetDefaults.ProgressiveBlurRampDistance.toPx() }
    val overhangPx = with(density) { GlassBottomSheetDefaults.ProgressiveBlurOverhang.roundToPx().coerceAtLeast(0) }
    val blendColors = remember(containerColor) {
        listOf(BlendColorEntry(containerColor.copy(alpha = 0.2f)))
    }
    val scrimBrush = remember(containerColor) {
        Brush.verticalGradient(
            0f to containerColor.copy(alpha = 0.50f),
            0.25f to containerColor.copy(alpha = 0.44f),
            0.5f to containerColor.copy(alpha = 0.32f),
            0.75f to containerColor.copy(alpha = 0.16f),
            0.9f to containerColor.copy(alpha = 0.05f),
            1f to Color.Transparent,
        )
    }
    var isContentScrolled by remember { mutableStateOf(false) }
    val headerTransition = updateTransition(isContentScrolled, label = "glassBottomSheetHeaderFloat")
    val materialProgress = headerTransition.animateFloat(
        transitionSpec = { GlassMotion.topBarButtonFloat() },
        label = "glassBottomSheetHeaderMaterial",
    ) { if (it) 1f else 0f }
    val keepMaterial by remember(isContentScrolled, materialProgress) {
        computedStateOf { isContentScrolled || materialProgress.value > 0f }
    }
    val buttonSize = GlassBottomSheetDefaults.ButtonSize
    val buttonShape = remember(buttonSize) { GlassShape(buttonSize / 2) }
    val buttonMaterial = GlassBottomSheetDefaults.buttonMaterial()
    val buttonFill = GlassBottomSheetDefaults.buttonFill()
    val buttonStroke = GlassBottomSheetDefaults.buttonStroke()
    val headerContext = remember(
        sheetSceneBackdrop,
        backdrop,
        buttonMaterial,
        isContentScrolled,
        materialProgress,
        keepMaterial,
        buttonSize,
        buttonShape,
        buttonFill,
        buttonStroke,
    ) {
        GlassHeaderContext(
            backdrop = sheetSceneBackdrop ?: backdrop,
            buttonBackdrop = sheetSceneBackdrop ?: backdrop,
            material = buttonMaterial,
            underlayMaterial = null,
            buttonUnderlayMaterial = null,
            floating = isContentScrolled,
            alpha = 1f,
            materialProgress = materialProgress,
            keepMaterial = keepMaterial,
            style = GlassDefaults.Style,
            size = buttonSize,
            shape = buttonShape,
            fill = buttonFill,
            stroke = buttonStroke,
            shadow = GlassShadows.Regular,
        )
    }
    val wrappedStartAction: (@Composable () -> Unit)? = remember(startAction, headerContext) {
        startAction?.let { action ->
            {
                CompositionLocalProvider(LocalGlassHeaderContext provides headerContext) {
                    action()
                }
            }
        }
    }
    val wrappedEndAction: (@Composable () -> Unit)? = remember(endAction, headerContext) {
        endAction?.let { action ->
            {
                CompositionLocalProvider(LocalGlassHeaderContext provides headerContext) {
                    action()
                }
            }
        }
    }
    val wrappedHeaderBottomContent: @Composable () -> Unit = remember(headerBottomContent, headerContext) {
        {
            CompositionLocalProvider(LocalGlassHeaderContext provides headerContext) {
                headerBottomContent()
            }
        }
    }
    val blurColors = BlurDefaults.blurColors(blendColors = blendColors)

    val panelSurface: @Composable BottomSheetSurfaceScope.() -> Modifier =
        glassBottomSheetPanelSurface(backdrop, material, containerColor)
    val foregroundSurface: @Composable BottomSheetSurfaceScope.() -> Modifier =
        glassBottomSheetForegroundSurface(stroke)
    val backgroundModifier: @Composable (scrollOffset: () -> Float) -> Modifier =
        remember(sheetSurfaceBackdrop) {
            { _ ->
                if (sheetSurfaceBackdrop != null) {
                    Modifier.layerBackdrop(sheetSurfaceBackdrop)
                } else {
                    Modifier
                }
            }
        }
    val bodyModifier: @Composable (scrollOffset: () -> Float) -> Modifier =
        remember(sheetSceneBackdrop) {
            { _ ->
                if (sheetSceneBackdrop != null) {
                    Modifier
                        .onGloballyPositioned { bodyCoordinates = it }
                        .layerBackdrop(sheetSceneBackdrop)
                } else {
                    Modifier
                }
            }
        }
    val headerBackground: @Composable (scrollOffset: () -> Float, headerHeightPx: () -> Int) -> Unit =
        remember(sheetSceneBackdrop, rampDistancePx, overhangPx, blurColors, containerColor, scrimBrush) {
            { scrollOffset, headerHeightPx ->
                LaunchedEffect(scrollOffset) {
                    snapshotFlow { scrollOffset() > 1f }
                        .collect { isContentScrolled = it }
                }
                val bandActive by remember(rampDistancePx) {
                    computedStateOf { (scrollOffset() / rampDistancePx).coerceIn(0f, 1f) > 0.001f }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .layout { measurable, constraints ->
                            val heightPx = (headerHeightPx() + overhangPx).coerceIn(0, constraints.maxHeight)
                            val placeable = measurable.measure(constraints.copy(minHeight = heightPx, maxHeight = heightPx))
                            layout(placeable.width, heightPx) {
                                placeable.placeRelative(0, 0)
                            }
                        }
                        .graphicsLayer {
                            alpha = (scrollOffset() / rampDistancePx).coerceIn(0f, 1f)
                        }
                        .then(
                            if (sheetSceneBackdrop != null) {
                                Modifier
                                    .progressiveTextureBlur(
                                        backdrop = sheetSceneBackdrop,
                                        shape = RectangleShape,
                                        gradient = SheetHeaderProgressiveGradient,
                                        blurRadius = GlassBottomSheetDefaults.ProgressiveBlurRadius.value,
                                        colors = blurColors,
                                        enabled = bandActive,
                                    )
                                    .drawWithContent {
                                        drawContent()
                                        if (bandActive) {
                                            drawRect(brush = scrimBrush)
                                        }
                                    }
                            } else {
                                Modifier.drawWithCache {
                                    val headerFraction = (headerHeightPx().toFloat() / size.height.coerceAtLeast(1f)).coerceIn(0f, 0.9f)
                                    val fadeBrush = Brush.verticalGradient(
                                        0f to containerColor,
                                        (headerFraction * 0.75f) to containerColor,
                                        headerFraction to containerColor.copy(alpha = 0.85f),
                                        1f to Color.Transparent,
                                    )
                                    onDrawBehind {
                                        if (bandActive) drawRect(fadeBrush)
                                    }
                                }
                            },
                        ),
                )
            }
        }

    BottomSheetOverlayLayout(
        show = show,
        modifier = modifier,
        title = title,
        startAction = wrappedStartAction,
        endAction = wrappedEndAction,
        headerBottomContent = wrappedHeaderBottomContent,
        enableWindowDim = enableWindowDim,
        cornerRadius = cornerRadius,
        sheetMaxWidth = sheetMaxWidth,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        outsideMargin = outsideMargin,
        insideMargin = insideMargin,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        dragHandleColor = dragHandleColor,
        allowDismiss = allowDismiss,
        enableNestedScroll = enableNestedScroll,
        renderInRootScaffold = renderInRootScaffold,
        dimColor = dimColor,
        motion = SheetMotion.Glass,
        overlayHeader = true,
        surface = panelSurface,
        foregroundSurface = foregroundSurface,
        headerBackground = headerBackground,
        backgroundModifier = backgroundModifier,
        bodyModifier = bodyModifier,
        scrollOffset = scrollOffset,
        content = content,
    )
}
