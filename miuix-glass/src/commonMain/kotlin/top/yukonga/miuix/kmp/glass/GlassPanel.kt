// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.graphicsLayer
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.glass.internal.drawGlassStroke

/**
 * Applies a glass surface and shadow over [backdrop].
 *
 * If runtime shaders are unavailable or [backdrop] is `null`, [fallback] defines the surface.
 *
 * @param backdrop Backdrop behind the glass. `null` uses [fallback].
 * @param shape Surface silhouette used for the shadow, clip, and rendering geometry.
 * @param style Glass appearance.
 * @param alpha Opacity multiplier for the surface and shadow.
 * @param material Surface body. `null` uses the blurred backdrop as the body.
 * @param stroke Optional bloom stroke along the silhouette.
 * @param shadow Shadow configuration. `null` disables the shadow.
 * @param shading Whether to refract and shade the backdrop. Use `false` for bars and menus.
 * @param fallback Surface modifier used without a backdrop. The default clips to [shape] and
 *   leaves subsequent caller content as the body.
 */
fun Modifier.glassPanel(
    backdrop: Backdrop?,
    shape: GlassShape,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    material: GlassMaterial? = null,
    stroke: GlassStroke? = null,
    shadow: GlassShadow? = GlassShadows.Regular,
    shading: Boolean = true,
    fallback: Modifier = Modifier.clip(shape),
): Modifier = if (backdrop == null) {
    this
        .graphicsLayer { this.alpha = alpha }
        .glassShadow(shape, shadow)
        .then(fallback)
        .drawWithContent {
            drawContent()
            stroke?.let { drawGlassStroke(shape, layoutDirection, it, 1f) }
        }
} else {
    this
        .glassShadow(shape, shadow, alpha)
        .glass(
            backdrop = backdrop,
            shape = shape,
            style = style,
            alpha = alpha,
            material = material,
            stroke = stroke,
            shading = shading,
        )
}
