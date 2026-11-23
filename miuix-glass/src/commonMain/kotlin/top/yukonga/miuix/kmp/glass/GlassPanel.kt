// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.material.edgeStroke
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
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
 * @param fallback Surface used without a backdrop; `null` clips to [shape].
 */
fun Modifier.glassPanel(
    backdrop: Backdrop?,
    shape: GlassShape,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    material: GlassMaterial? = null,
    stroke: EdgeStroke? = null,
    shadow: GlassShadow? = GlassShadows.Regular,
    shading: Boolean = true,
    fallback: Modifier? = null,
): Modifier = if (backdrop == null || !isRuntimeShaderSupported()) {
    var result: Modifier = this
        .graphicsLayer { this.alpha = alpha }
        .glassShadow(shape, shadow)
        .then(fallback ?: Modifier.clip(shape))
    if (stroke != null) {
        result = result.edgeStroke(shape, stroke, 1f)
    }
    result
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

/**
 * Applies a glass surface and shadow over [backdrop], reading [stroke] during the draw phase so
 * animated or sensor-driven strokes do not trigger recomposition or rebuild backdrop blur effects.
 */
fun Modifier.glassPanel(
    backdrop: Backdrop?,
    shape: GlassShape,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    material: GlassMaterial? = null,
    stroke: () -> EdgeStroke?,
    shadow: GlassShadow? = GlassShadows.Regular,
    shading: Boolean = true,
    fallback: Modifier? = null,
): Modifier = if (backdrop == null || !isRuntimeShaderSupported()) {
    this
        .graphicsLayer { this.alpha = alpha }
        .glassShadow(shape, shadow)
        .then(fallback ?: Modifier.clip(shape))
        .edgeStroke(shape, stroke)
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
