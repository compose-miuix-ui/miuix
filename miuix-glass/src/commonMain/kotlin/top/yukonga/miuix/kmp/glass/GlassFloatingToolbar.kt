// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassEdgeStrokes
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassMaterials
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Default values for [GlassFloatingToolbar]. */
object GlassFloatingToolbarDefaults {

    /** Default corner radius of the [GlassFloatingToolbar]. */
    val CornerRadius: Dp = 50.dp

    /** Default padding outside the [GlassFloatingToolbar]. */
    val OutSidePadding: PaddingValues = PaddingValues(12.dp, 8.dp)

    /** Default glass material for [GlassFloatingToolbar]. */
    @Composable
    fun material(): GlassMaterial = dynamicGlassMaterial(GlassMaterials.puredThinGlass(isDarkTheme()))

    /** Default bloom stroke for [GlassFloatingToolbar]. */
    @Composable
    @ReadOnlyComposable
    fun stroke(): EdgeStroke = GlassEdgeStrokes.forTheme(isDark = isDarkTheme())

    /** Default fallback fill colour when no [Backdrop] is provided. */
    @Composable
    @ReadOnlyComposable
    fun fill(): Color = MiuixTheme.colorScheme.surfaceContainer

    @Composable
    @ReadOnlyComposable
    private fun isDarkTheme(): Boolean = MiuixTheme.colorScheme.background.luminance() < 0.5f
}

/**
 * A floating toolbar rendered on glass over [backdrop].
 *
 * @param modifier Modifier applied to the toolbar.
 * @param backdrop Optional backdrop sampled behind the toolbar; `null` uses [fill].
 * @param cornerRadius Corner radius used to construct [shape].
 * @param shape Toolbar silhouette.
 * @param outSidePadding Padding outside the toolbar.
 * @param surfaceAlpha Opacity multiplier for the surface and shadow.
 * @param style Glass style forwarded to the renderer.
 * @param material Glass material applied when [backdrop] is provided.
 * @param fill Fallback fill colour when [backdrop] is `null`.
 * @param stroke Optional bloom stroke along the silhouette.
 * @param shadow Shadow beneath the toolbar; `null` disables it.
 * @param content Toolbar content.
 */
@Composable
fun GlassFloatingToolbar(
    modifier: Modifier = Modifier,
    backdrop: Backdrop? = null,
    cornerRadius: Dp = GlassFloatingToolbarDefaults.CornerRadius,
    shape: GlassShape = remember(cornerRadius) { GlassShape(cornerRadius) },
    outSidePadding: PaddingValues = GlassFloatingToolbarDefaults.OutSidePadding,
    surfaceAlpha: Float = 1f,
    style: GlassStyle = GlassDefaults.Style,
    material: GlassMaterial = GlassFloatingToolbarDefaults.material(),
    fill: Color = GlassFloatingToolbarDefaults.fill(),
    stroke: EdgeStroke? = GlassFloatingToolbarDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Regular,
    content: @Composable () -> Unit,
) {
    val opacity = surfaceAlpha.coerceIn(0f, 1f)
    Box(
        modifier = modifier
            .padding(outSidePadding)
            .glassPanel(
                backdrop = backdrop,
                shape = shape,
                style = style,
                alpha = opacity,
                material = material,
                stroke = stroke,
                shadow = shadow,
                shading = false,
                fallback = if (backdrop == null || !isRuntimeShaderSupported()) {
                    Modifier
                        .clip(shape)
                        .background(fill)
                } else {
                    null
                },
            )
            .pointerInput(Unit) {
                detectTapGestures { /* Consume click */ }
            },
    ) {
        content()
    }
}
