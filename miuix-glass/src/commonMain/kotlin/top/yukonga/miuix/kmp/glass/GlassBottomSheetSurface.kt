// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.glass.internal.ScrimBackdrop
import top.yukonga.miuix.kmp.layout.BottomSheetSurfaceScope
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassEdgeStrokes
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassMaterials
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.drawEdgeStroke
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Default values for [GlassBottomSheet] and [glassBottomSheetSurface]. */
object GlassBottomSheetDefaults {
    val ScrimColor: Color = GlassDialogDefaults.ScrimColor

    /** Default panel content padding for [GlassBottomSheet]. */
    val InsideMargin: DpSize = DpSize(12.dp, 0.dp)

    /** Maximum blur radius of the progressive header band. */
    val ProgressiveBlurRadius: Dp = 32.dp

    /** Extension of the progressive header band below the title row. */
    val ProgressiveBlurOverhang: Dp = 24.dp

    /** Scroll distance over which the progressive header band fades in. */
    val ProgressiveBlurRampDistance: Dp = 32.dp

    /** Diameter of the glass pill behind a bottom-sheet header button. */
    val ButtonSize: Dp = 44.dp

    /** Bottom-sheet material for the current theme. */
    @Composable
    fun material(): GlassMaterial = dynamicGlassMaterial(
        GlassMaterials.dialogGlass(
            isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        ),
        tint = MiuixTheme.colorScheme.surfaceContainer,
        alpha = 0.72f,
    )

    /** Bottom-sheet bloom stroke for the current theme. */
    @Composable
    @ReadOnlyComposable
    fun stroke(): EdgeStroke = GlassEdgeStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        light = GlassEdgeStrokes.MiddleLight,
        dark = GlassEdgeStrokes.MiddleDark,
    )

    /** Opaque fallback when no scene or runtime shader support is available. */
    @Composable
    @ReadOnlyComposable
    fun containerColor(): Color = MiuixTheme.colorScheme.background

    /** Fallback fill for bottom-sheet header buttons when backdrop material is unavailable. */
    @Composable
    @ReadOnlyComposable
    fun buttonFill(): Color = when {
        MiuixTheme.isDynamicColor -> MiuixTheme.colorScheme.surfaceContainerHigh
        MiuixTheme.colorScheme.background.luminance() < 0.5f -> Color(0xFF2C2C2C)
        else -> Color.White
    }

    /** Default material for bottom-sheet header buttons. */
    @Composable
    fun buttonMaterial(): GlassMaterial = dynamicGlassMaterial(
        GlassMaterials.puredThinGlass(MiuixTheme.colorScheme.background.luminance() < 0.5f),
    )

    /** The small bloom stroke paired with [buttonMaterial]. */
    @Composable
    @ReadOnlyComposable
    fun buttonStroke(): EdgeStroke = GlassEdgeStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        light = GlassEdgeStrokes.SmallLight,
        dark = GlassEdgeStrokes.SmallDark,
    )
}

/**
 * A glass surface for OverlayBottomSheet's `surface` slot, with rounded top corners.
 *
 * The backdrop is dimmed before blur and sampled in the sheet's translated screen coordinates.
 * Upward overscroll extends the material without a stroke. Content is not refracted, and no
 * additional drop shadow is drawn.
 *
 * @param backdrop Page recording including bars, excluding the sheet host and its scrim.
 *   Null or unavailable runtime shaders use the opaque [containerColor] fallback.
 * @param material Shared dialog/sheet blur and ordered blend layers.
 * @param stroke Optional bloom stroke, omitted on the overscroll extension.
 * @param containerColor Opaque fallback fill.
 */
@Composable
fun glassBottomSheetSurface(
    backdrop: Backdrop?,
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    stroke: EdgeStroke? = GlassBottomSheetDefaults.stroke(),
    containerColor: Color = GlassBottomSheetDefaults.containerColor(),
): @Composable BottomSheetSurfaceScope.() -> Modifier = {
    val panel = glassBottomSheetPanelSurface(backdrop, material, containerColor).invoke(this)
    val shape = remember(cornerRadius) {
        GlassShape(topStart = cornerRadius, topEnd = cornerRadius, bottomEnd = 0.dp, bottomStart = 0.dp)
    }
    panel.glassBottomSheetEdgeStroke(shape = shape, stroke = stroke, isOverscroll = isOverscroll)
}

@Composable
internal fun glassBottomSheetPanelSurface(
    backdrop: Backdrop?,
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    containerColor: Color = GlassBottomSheetDefaults.containerColor(),
): @Composable BottomSheetSurfaceScope.() -> Modifier = {
    val effectiveBackdrop = if (backdrop != null && isRuntimeShaderSupported()) {
        remember(backdrop, scrimColor) { ScrimBackdrop(backdrop, scrimColor) }
    } else {
        null
    }
    val shape = remember(cornerRadius) {
        GlassShape(topStart = cornerRadius, topEnd = cornerRadius, bottomEnd = 0.dp, bottomStart = 0.dp)
    }
    Modifier.glassPanel(
        backdrop = effectiveBackdrop,
        shape = shape,
        material = material,
        stroke = null,
        shadow = null,
        shading = false,
        fallback = if (effectiveBackdrop == null) Modifier.clip(shape).background(containerColor) else null,
    )
}

@Composable
internal fun glassBottomSheetForegroundSurface(
    stroke: EdgeStroke? = GlassBottomSheetDefaults.stroke(),
): @Composable BottomSheetSurfaceScope.() -> Modifier = {
    val shape = remember(cornerRadius) {
        GlassShape(topStart = cornerRadius, topEnd = cornerRadius, bottomEnd = 0.dp, bottomStart = 0.dp)
    }
    Modifier
        .clip(shape)
        .glassBottomSheetEdgeStroke(shape = shape, stroke = stroke, isOverscroll = isOverscroll)
}

private fun Modifier.glassBottomSheetEdgeStroke(
    shape: GlassShape,
    stroke: EdgeStroke?,
    isOverscroll: Boolean,
): Modifier = if (isOverscroll || stroke == null) {
    this
} else {
    drawWithCache {
        val extraBottomPx = (stroke.width + stroke.bevel).toPx() + 1f
        val strokeSize = Size(size.width, size.height + extraBottomPx)
        val strokeDrawScope = CanvasDrawScope()
        onDrawWithContent {
            drawContent()
            clipRect {
                drawIntoCanvas { canvas ->
                    strokeDrawScope.draw(
                        density = this,
                        layoutDirection = layoutDirection,
                        canvas = canvas,
                        size = strokeSize,
                    ) {
                        drawEdgeStroke(shape = shape, stroke = stroke)
                    }
                }
            }
        }
    }
}
