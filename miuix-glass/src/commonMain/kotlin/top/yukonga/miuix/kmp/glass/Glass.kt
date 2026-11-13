// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.isSpecified
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.blur
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.blur.noiseDither
import top.yukonga.miuix.kmp.glass.internal.GLASS_COLOR_BLEND_SHADER_KEY
import top.yukonga.miuix.kmp.glass.internal.colorBlendEffect
import top.yukonga.miuix.kmp.glass.internal.glassEffect
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassInner
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.material.GlassStyles
import top.yukonga.miuix.kmp.material.drawEdgeStroke
import top.yukonga.miuix.kmp.material.drawGlassEdge
import top.yukonga.miuix.kmp.material.drawGlassMask
import top.yukonga.miuix.kmp.material.drawGlassRim
import top.yukonga.miuix.kmp.material.drawGlassShadow
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported

/**
 * Applies a glass surface over [backdrop].
 *
 * @param backdrop Content behind the glass. This modifier must be outside the subtree recorded by
 *   the backdrop; placing it inside creates a sampling cycle.
 * @param shape Surface silhouette used for clipping, [stroke], and rendering geometry.
 * @param style Glass appearance. Use a predefined style from [GlassStyles] or [GlassDefaults.Style].
 * @param alpha Opacity multiplier for colour layers, shading, and stroke.
 * @param tint Overrides [GlassInner.tint]. Its alpha replaces the style tint strength.
 *   [Color.Unspecified] retains the style tint.
 * @param material Surface body: blur radius and colour layers. Its blur radius overrides [style].
 *   `null` uses the blurred backdrop as the body.
 * @param noiseCoefficient Noise dithering coefficient for backdrop blur. 0 disables dithering.
 * @param stroke Optional bloom stroke along the silhouette. `null` disables it.
 * @param contentBlendMode Blend mode for content drawn over the glass.
 * @param enabled Whether the glass effect is active. When false, content draws without it.
 * @param shading Whether to refract and shade the backdrop. Use `false` for bars and menus whose
 *   body is supplied by [material].
 */
fun Modifier.glass(
    backdrop: Backdrop,
    shape: GlassShape,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    tint: Color = Color.Unspecified,
    material: GlassMaterial? = null,
    noiseCoefficient: Float = GlassDefaults.NoiseCoefficient,
    stroke: EdgeStroke? = null,
    contentBlendMode: BlendMode = BlendMode.SrcOver,
    enabled: Boolean = true,
    shading: Boolean = true,
): Modifier = glassImpl(
    backdrop = backdrop,
    shape = shape,
    style = style,
    alpha = alpha,
    tint = tint,
    material = material,
    underlayMaterial = null,
    noiseCoefficient = noiseCoefficient,
    stroke = stroke,
    contentBlendMode = contentBlendMode,
    enabled = enabled,
    shading = shading,
)

/** Applies a child material over an action bar's blurred and colour-treated surface. */
internal fun Modifier.glassOnActionBar(
    backdrop: Backdrop,
    shape: GlassShape,
    style: GlassStyle,
    alpha: Float,
    material: GlassMaterial?,
    underlayMaterial: GlassMaterial,
    stroke: EdgeStroke?,
    enabled: Boolean = true,
): Modifier = glassImpl(
    backdrop = backdrop,
    shape = shape,
    style = style,
    alpha = alpha,
    tint = Color.Unspecified,
    material = material,
    underlayMaterial = underlayMaterial,
    noiseCoefficient = GlassDefaults.NoiseCoefficient,
    stroke = stroke,
    contentBlendMode = BlendMode.SrcOver,
    enabled = enabled,
    shading = false,
)

/** Shape provider with value equality: `drawBackdrop` compares callback identity. */
private class GlassShapeCallback(private val value: GlassShape) : () -> Shape {
    override fun invoke(): Shape = value

    override fun equals(other: Any?): Boolean = other is GlassShapeCallback && other.value == value

    override fun hashCode(): Int = value.hashCode()
}

/** Effect pipeline with value equality; see [GlassShapeCallback]. */
private class GlassEffectsCallback(
    private val glassShape: GlassShape,
    private val style: GlassStyle,
    private val alpha: Float,
    private val tint: Color,
    private val material: GlassMaterial?,
    private val underlayMaterial: GlassMaterial?,
    private val noiseCoefficient: Float,
    private val shading: Boolean,
) : Function1<BackdropEffectScope, Unit> {
    override fun invoke(p1: BackdropEffectScope) {
        with(p1) {
            noiseDither(noiseCoefficient)
            val radius = material?.blurRadius?.value ?: (style.blur.small / GlassDefaults.SourceDensity)
            blur(radius.coerceIn(0f, BlurDefaults.MaxBlurRadius) * density)
            if (underlayMaterial != null) {
                colorBlendEffect(underlayMaterial, 1f, "${GLASS_COLOR_BLEND_SHADER_KEY}ActionBar")
            }
            if (material != null) colorBlendEffect(material, alpha)
            if (shading) glassEffect(style, glassShape, alpha, tint)
        }
    }

    override fun equals(other: Any?): Boolean = other is GlassEffectsCallback &&
        other.glassShape == glassShape &&
        other.style == style &&
        other.alpha == alpha &&
        other.tint == tint &&
        other.material == material &&
        other.underlayMaterial == underlayMaterial &&
        other.noiseCoefficient == noiseCoefficient &&
        other.shading == shading

    override fun hashCode(): Int {
        var result = glassShape.hashCode()
        result = 31 * result + style.hashCode()
        result = 31 * result + alpha.hashCode()
        result = 31 * result + tint.hashCode()
        result = 31 * result + (material?.hashCode() ?: 0)
        result = 31 * result + (underlayMaterial?.hashCode() ?: 0)
        result = 31 * result + noiseCoefficient.hashCode()
        result = 31 * result + shading.hashCode()
        return result
    }
}

/** Foreground stroke, mask and rim pass with value equality; see [GlassShapeCallback]. */
private class GlassFrontCallback(
    private val glassShape: GlassShape,
    private val stroke: EdgeStroke?,
    private val style: GlassStyle,
    private val tint: Color,
    private val alpha: Float,
    private val shading: Boolean,
) : Function1<DrawScope, Unit> {
    override fun invoke(p1: DrawScope) {
        with(p1) {
            // Stroke and rim share one silhouette pass; the mask trims it afterwards.
            val current = stroke
            val fuse = current != null && shading &&
                current.width.value > 0f &&
                current.blendMode == BlendMode.Plus
            if (current != null && fuse) {
                drawGlassEdge(
                    shape = glassShape,
                    stroke = current,
                    style = style,
                    surface = if (tint.isSpecified) tint else style.inner.tint,
                    alpha = alpha,
                )
                drawGlassMask(glassShape)
            } else {
                if (current != null) drawEdgeStroke(glassShape, current, alpha)
                drawGlassMask(glassShape)
                if (shading) {
                    drawGlassRim(glassShape, style, if (tint.isSpecified) tint else style.inner.tint, alpha)
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean = other is GlassFrontCallback &&
        other.glassShape == glassShape &&
        other.stroke == stroke &&
        other.style == style &&
        other.tint == tint &&
        other.alpha == alpha &&
        other.shading == shading

    override fun hashCode(): Int {
        var result = glassShape.hashCode()
        result = 31 * result + (stroke?.hashCode() ?: 0)
        result = 31 * result + style.hashCode()
        result = 31 * result + tint.hashCode()
        result = 31 * result + alpha.hashCode()
        result = 31 * result + shading.hashCode()
        return result
    }
}

private fun Modifier.glassImpl(
    backdrop: Backdrop,
    shape: GlassShape,
    style: GlassStyle,
    alpha: Float,
    tint: Color,
    material: GlassMaterial?,
    underlayMaterial: GlassMaterial?,
    noiseCoefficient: Float,
    stroke: EdgeStroke?,
    contentBlendMode: BlendMode,
    enabled: Boolean,
    shading: Boolean,
): Modifier = this.drawBackdrop(
    backdrop = backdrop,
    shape = GlassShapeCallback(shape),
    effects = GlassEffectsCallback(
        glassShape = shape,
        style = style,
        alpha = alpha,
        tint = tint,
        material = material,
        underlayMaterial = underlayMaterial,
        noiseCoefficient = noiseCoefficient,
        shading = shading,
    ),
    onDrawFront = GlassFrontCallback(
        glassShape = shape,
        stroke = stroke,
        style = style,
        tint = tint,
        alpha = alpha,
        shading = shading,
    ),
    contentBlendMode = contentBlendMode,
    enabled = enabled,
)

/**
 * Draws [shadow] beneath this composable using [shape].
 *
 * @param shape Shadow silhouette. Use the same shape as the corresponding [glass] surface.
 * @param shadow Shadow configuration. Use a value from [GlassShadows], or `null` to disable it.
 * @param alpha Opacity multiplier for the shadow.
 */
fun Modifier.glassShadow(
    shape: GlassShape,
    shadow: GlassShadow? = GlassShadows.Regular,
    alpha: Float = 1f,
): Modifier = glassShadow(shape, shadow) { alpha }

internal fun Modifier.glassShadow(
    shape: GlassShape,
    shadow: GlassShadow?,
    alpha: () -> Float,
): Modifier = if (shadow == null) {
    this
} else if (isRuntimeShaderSupported()) {
    this.drawBehind { drawGlassShadow(shape, shadow, alpha()) }
} else {
    fallbackGlassShadow(shape, shadow, alpha)
}

/** Draw-based fallback; the blur kernel differs from the shader's dispersion curve. */
internal fun Modifier.fallbackGlassShadow(
    shape: GlassShape,
    shadow: GlassShadow,
    alpha: () -> Float,
): Modifier = dropShadow(shape) {
    val sourceScale = density / GlassDefaults.SourceDensity
    radius = (shadow.radius * sourceScale).coerceAtLeast(1f)
    offset = Offset(shadow.offsetX * sourceScale, shadow.offsetY * sourceScale)
    color = shadow.color
    this.alpha = alpha().coerceIn(0f, 1f)
}
