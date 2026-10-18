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
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.glass.internal.ScrimBackdrop
import top.yukonga.miuix.kmp.layout.BottomSheetSurfaceScope
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Defaults for the launcher's attached bottom-sheet material. */
object GlassBottomSheetDefaults {
    val ScrimColor: Color = GlassDialogDefaults.ScrimColor

    /** Native sheets and dialogs reference the same 60dp blur and three color layers. */
    @Composable
    fun material(): GlassMaterial = GlassMaterials.dialogGlass(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** Initial approximation; native sheet bloom uses a different config from dialogs. */
    @Composable
    fun stroke(): GlassStroke = GlassStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        light = GlassStrokes.MiddleLight,
        dark = GlassStrokes.MiddleDark,
    )

    /** Opaque fallback when no scene or runtime shader support is available. */
    @Composable
    fun containerColor(): Color = MiuixTheme.colorScheme.background
}

/**
 * A blur/blend material for OverlayBottomSheet's `surface` slot, with rounded top corners.
 *
 * The host keeps ownership of sizing, insets, the handle and gestures. The supplied clean [backdrop]
 * is dimmed locally before blur, and sampling follows the sheet's translated screen coordinates.
 * During upward overscroll the exposed bottom edge gets the same material without a stroke.
 * There is no refraction or additional drop shadow. Native blur-kernel conversion, scrim sampling
 * order and bloom still need visual calibration.
 *
 * @param backdrop Page recording including bars, excluding the sheet host and its scrim.
 *   Null or unavailable runtime shaders use the opaque [containerColor] fallback.
 * @param material Shared dialog/sheet blur and ordered blend layers.
 * @param stroke Optional bloom approximation; omitted on the overscroll extension.
 * @param containerColor Opaque fallback fill.
 */
@Composable
fun glassBottomSheetSurface(
    backdrop: Backdrop?,
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    stroke: GlassStroke? = GlassBottomSheetDefaults.stroke(),
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
        stroke = if (isOverscroll) null else stroke,
        shadow = null,
        shading = false,
        fallback = Modifier.clip(shape).background(containerColor),
    )
}
