// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.glass.internal.ScrimBackdrop
import top.yukonga.miuix.kmp.layout.DialogSurfaceScope
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Defaults for a blur/blend surface hosted by OverlayDialog. */
object GlassDialogDefaults {

    /** Route scrim at rest. The host applies the opening/closing and back-gesture progress. */
    val ScrimColor: Color = Color(0x33000000)

    /** The dialog's three color layers and blur input, selected from the current theme. */
    @Composable
    fun material(): GlassMaterial = GlassMaterials.dialogGlass(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** Initial bloom approximation, pending calibration of the native dialog's bloom fields. */
    @Composable
    fun stroke(): GlassStroke = GlassStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        light = GlassStrokes.SmallLight,
        dark = GlassStrokes.SmallDark,
    )

    /** Opaque fill used when there is no backdrop or runtime shader support. */
    @Composable
    fun containerColor(): Color = MiuixTheme.colorScheme.background
}

/**
 * A dialog-specific blur/blend surface for OverlayDialog's `surface` slot.
 *
 * The host supplies the radius and animated scrim/scale. The clean [backdrop] is dimmed locally
 * before blurring and stays screen-aligned as the dialog scales. Content is drawn separately,
 * with no refraction. The native scrim sampling order and blur-kernel width still need visual
 * calibration; [stroke] defaults to an initial bloom approximation.
 *
 * @param backdrop Page recording, including bars but excluding the dialog host and its scrim.
 *   Null uses [containerColor]. Unsupported runtime shaders also use this opaque fallback.
 * @param material The dialog's blur input and ordered color layers.
 * @param stroke Optional full-resolution bloom stroke.
 * @param containerColor Opaque fallback fill.
 */
@Composable
fun glassDialogSurface(
    backdrop: Backdrop?,
    material: GlassMaterial = GlassDialogDefaults.material(),
    stroke: GlassStroke? = GlassDialogDefaults.stroke(),
    containerColor: Color = GlassDialogDefaults.containerColor(),
): @Composable DialogSurfaceScope.() -> Modifier = {
    val effectiveBackdrop = if (backdrop != null && isRuntimeShaderSupported()) {
        remember(backdrop, scrimColor, scale) { ScrimBackdrop(backdrop, scrimColor, scale) }
    } else {
        null
    }
    val shape = remember(cornerRadius) { GlassShape(cornerRadius) }
    Modifier.glassPanel(
        backdrop = effectiveBackdrop,
        shape = shape,
        material = material,
        stroke = stroke,
        shadow = null,
        shading = false,
        fallback = Modifier.clip(shape).background(containerColor),
    )
}
