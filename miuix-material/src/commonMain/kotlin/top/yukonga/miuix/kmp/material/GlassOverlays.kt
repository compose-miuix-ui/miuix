// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.material.internal.GLASS_MASK_SHADER
import top.yukonga.miuix.kmp.material.internal.GLASS_MASK_SHADER_KEY
import top.yukonga.miuix.kmp.material.internal.GLASS_RIM_SHADER
import top.yukonga.miuix.kmp.material.internal.GLASS_RIM_SHADER_KEY
import top.yukonga.miuix.kmp.material.internal.GLASS_SHADOW_SHADER
import top.yukonga.miuix.kmp.material.internal.GLASS_SHADOW_SHADER_KEY
import top.yukonga.miuix.kmp.material.internal.glassEdgeShader
import top.yukonga.miuix.kmp.material.internal.setEdgeStrokeUniforms
import top.yukonga.miuix.kmp.material.internal.sharedShaderCache
import kotlin.math.PI
import kotlin.math.min

/**
 * Single-slot cache for an immutable [ShaderBrush] snapshot produced by [RuntimeShader.asBrush]
 * on Skiko. On Android (`!isRuntimeShaderBrushImmutable()`), shaders mutate in place so caching is
 * skipped and the shared brush is reused directly.
 */
class ShaderBrushCache {
    private var width = Float.NaN
    private var height = Float.NaN
    private var density = Float.NaN
    private var layoutDirection: LayoutDirection? = null
    private var r0 = Float.NaN
    private var r1 = Float.NaN
    private var r2 = Float.NaN
    private var r3 = Float.NaN
    private var smoothing = Float.NaN
    private var alpha = Float.NaN
    private var surface = Color.Unspecified
    private var stroke: EdgeStroke? = null
    private var style: GlassStyle? = null
    private var shadow: GlassShadow? = null
    private var fused = false
    private var brush: ShaderBrush? = null

    internal inline fun obtain(
        scope: DrawScope,
        shape: CornerBasedShape,
        smoothing: Float,
        alpha: Float,
        surface: Color = Color.Unspecified,
        stroke: EdgeStroke? = null,
        style: GlassStyle? = null,
        shadow: GlassShadow? = null,
        fused: Boolean = false,
        build: (topStart: Float, topEnd: Float, bottomEnd: Float, bottomStart: Float) -> ShaderBrush,
    ): ShaderBrush {
        val size = scope.size
        val topStart = shape.topStart.toPx(size, scope)
        val topEnd = shape.topEnd.toPx(size, scope)
        val bottomEnd = shape.bottomEnd.toPx(size, scope)
        val bottomStart = shape.bottomStart.toPx(size, scope)
        val clampedSmoothing = smoothing.coerceIn(0f, 1f)
        if (!isRuntimeShaderBrushImmutable()) {
            return build(topStart, topEnd, bottomEnd, bottomStart)
        }
        val cached = brush
        if (cached != null &&
            width == size.width &&
            height == size.height &&
            density == scope.density &&
            layoutDirection == scope.layoutDirection &&
            r0 == topStart &&
            r1 == topEnd &&
            r2 == bottomEnd &&
            r3 == bottomStart &&
            this.smoothing == clampedSmoothing &&
            this.alpha == alpha &&
            this.surface == surface &&
            this.stroke == stroke &&
            this.style == style &&
            this.shadow == shadow &&
            this.fused == fused
        ) {
            return cached
        }
        return build(topStart, topEnd, bottomEnd, bottomStart).also {
            width = size.width
            height = size.height
            density = scope.density
            layoutDirection = scope.layoutDirection
            r0 = topStart
            r1 = topEnd
            r2 = bottomEnd
            r3 = bottomStart
            this.smoothing = clampedSmoothing
            this.alpha = alpha
            this.surface = surface
            this.stroke = stroke
            this.style = style
            this.shadow = shadow
            this.fused = fused
            brush = it
        }
    }
}

/**
 * Draws rim lighting along the silhouette at full resolution. `surface` stands in for what is
 * beneath the rim, and shared shaders set all of their uniforms immediately before each draw.
 */
fun DrawScope.drawGlassRim(
    shape: GlassShape,
    style: GlassStyle,
    surface: Color,
    alpha: Float,
) = drawGlassRim(shape, style, surface, alpha, brushCache = null)

/** Cached overload of [drawGlassRim] reusing immutable Skiko [ShaderBrush] instances via [brushCache]. */
fun DrawScope.drawGlassRim(
    shape: GlassShape,
    style: GlassStyle,
    surface: Color,
    alpha: Float,
    brushCache: ShaderBrushCache?,
) {
    if (alpha <= 0f || size.width <= 0f || size.height <= 0f) return
    if (style.background.unShade >= 0.999f) return
    if (!isRuntimeShaderSupported()) return

    val brush = if (brushCache != null) {
        brushCache.obtain(this, shape, shape.smoothing, alpha, surface = surface, style = style) { ts, te, be, bs ->
            buildGlassRimBrush(shape, ts, te, be, bs, style, surface, alpha)
        }
    } else {
        buildGlassRimBrush(
            shape,
            shape.topStart.toPx(size, this),
            shape.topEnd.toPx(size, this),
            shape.bottomEnd.toPx(size, this),
            shape.bottomStart.toPx(size, this),
            style,
            surface,
            alpha,
        )
    }
    drawRect(brush = brush, blendMode = BlendMode.Plus)
}

private fun DrawScope.buildGlassRimBrush(
    shape: GlassShape,
    topStart: Float,
    topEnd: Float,
    bottomEnd: Float,
    bottomStart: Float,
    style: GlassStyle,
    surface: Color,
    alpha: Float,
): ShaderBrush {
    val shader = sharedShaderCache.obtainRuntimeShader(GLASS_RIM_SHADER_KEY, GLASS_RIM_SHADER)
    // Tiny surfaces take precedence over the nominal minimum band width.
    val edge = (style.edge.width * density / GlassDefaults.SourceDensity)
        .coerceAtLeast(1f)
        .coerceAtMost(size.minDimension * 0.5f)

    shader.setSilhouetteRadiiUniforms(layoutDirection, size, topStart, topEnd, bottomEnd, bottomStart, shape.smoothing)
    shader.setRimUniforms(style, surface, alpha, edge)
    return shader.asBrush()
}

/**
 * Draws the rim light and the edge stroke in one pass, replacing [drawEdgeStroke] + [drawGlassRim]:
 * both are additive light along the silhouette. [drawGlassMask] still runs after it.
 */
fun DrawScope.drawGlassEdge(
    shape: GlassShape,
    stroke: EdgeStroke,
    style: GlassStyle,
    surface: Color,
    alpha: Float,
) = drawGlassEdge(shape, stroke, style, surface, alpha, brushCache = null)

/** Cached overload of [drawGlassEdge] reusing immutable Skiko [ShaderBrush] instances via [brushCache]. */
fun DrawScope.drawGlassEdge(
    shape: GlassShape,
    stroke: EdgeStroke,
    style: GlassStyle,
    surface: Color,
    alpha: Float,
    brushCache: ShaderBrushCache?,
) {
    if (alpha <= 0f || size.width <= 0f || size.height <= 0f) return
    if (!isRuntimeShaderSupported()) return

    val brush = if (brushCache != null) {
        brushCache.obtain(this, shape, shape.smoothing, alpha, surface = surface, stroke = stroke, style = style, fused = true) { ts, te, be, bs ->
            buildGlassEdgeBrush(shape, ts, te, be, bs, stroke, style, surface, alpha)
        }
    } else {
        buildGlassEdgeBrush(
            shape,
            shape.topStart.toPx(size, this),
            shape.topEnd.toPx(size, this),
            shape.bottomEnd.toPx(size, this),
            shape.bottomStart.toPx(size, this),
            stroke,
            style,
            surface,
            alpha,
        )
    }
    drawRect(brush = brush, blendMode = BlendMode.Plus)
}

private fun DrawScope.buildGlassEdgeBrush(
    shape: GlassShape,
    topStart: Float,
    topEnd: Float,
    bottomEnd: Float,
    bottomStart: Float,
    stroke: EdgeStroke,
    style: GlassStyle,
    surface: Color,
    alpha: Float,
): ShaderBrush {
    val source = glassEdgeShader(stroke.peak)
    val shader = sharedShaderCache.obtainRuntimeShader(source.key, source.source)

    shader.setSilhouetteRadiiUniforms(layoutDirection, size, topStart, topEnd, bottomEnd, bottomStart, shape.smoothing)
    shader.setEdgeStrokeUniforms(
        stroke = stroke,
        alpha = alpha,
        density = this,
        widthPx = size.width,
        heightPx = size.height,
    )
    // Tiny surfaces take precedence over the nominal minimum band width.
    val edge = (style.edge.width * density / GlassDefaults.SourceDensity)
        .coerceAtLeast(1f)
        .coerceAtMost(size.minDimension * 0.5f)
    shader.setRimUniforms(style, surface, alpha, edge)
    return shader.asBrush()
}

/** Sets the rim-light uniforms the glass rim programs read. */
private fun RuntimeShader.setRimUniforms(
    style: GlassStyle,
    surface: Color,
    alpha: Float,
    edge: Float,
) {
    // A fully unshaded style renders no rim; zero the light instead of dropping the whole pass, so
    // the stroke still draws.
    val rimAlpha = if (style.background.unShade >= 0.999f) 0f else alpha
    setFloatUniform("in_rimEdge", edge, style.edge.pow.coerceAtLeast(0.01f), rimAlpha, 0f)
    setFloatUniform(
        "in_lightDir",
        style.light.directionX,
        style.light.directionY,
        style.light.directionZ,
        style.light.angleRange * PI.toFloat(),
    )
    setFloatUniform("in_lightAmt", style.light.intensity, style.light.oppositeIntensity, 0f, 0f)
    setFloatUniform("in_surface", surface.red, surface.green, surface.blue, 1f)
}

/** Trims the surface to [shape] at full resolution. */
fun DrawScope.drawGlassMask(shape: GlassShape) = drawGlassMask(shape, brushCache = null)

/** Cached overload of [drawGlassMask] reusing immutable Skiko [ShaderBrush] instances via [brushCache]. */
fun DrawScope.drawGlassMask(shape: GlassShape, brushCache: ShaderBrushCache?) {
    if (size.width <= 0f || size.height <= 0f) return
    if (!isRuntimeShaderSupported()) return
    val brush = if (brushCache != null) {
        brushCache.obtain(this, shape, shape.smoothing, 1f) { ts, te, be, bs ->
            buildGlassMaskBrush(shape, ts, te, be, bs)
        }
    } else {
        buildGlassMaskBrush(
            shape,
            shape.topStart.toPx(size, this),
            shape.topEnd.toPx(size, this),
            shape.bottomEnd.toPx(size, this),
            shape.bottomStart.toPx(size, this),
        )
    }
    drawRect(brush = brush, blendMode = BlendMode.DstIn)
}

private fun DrawScope.buildGlassMaskBrush(
    shape: GlassShape,
    topStart: Float,
    topEnd: Float,
    bottomEnd: Float,
    bottomStart: Float,
): ShaderBrush {
    val shader = sharedShaderCache.obtainRuntimeShader(GLASS_MASK_SHADER_KEY, GLASS_MASK_SHADER)
    shader.setSilhouetteRadiiUniforms(layoutDirection, size, topStart, topEnd, bottomEnd, bottomStart, shape.smoothing)
    return shader.asBrush()
}

/** Draws [shadow] beyond the bounds of [shape]. */
fun DrawScope.drawGlassShadow(
    shape: GlassShape,
    shadow: GlassShadow,
    alpha: Float,
) = drawGlassShadow(shape, shadow, alpha, brushCache = null)

/** Cached overload of [drawGlassShadow] reusing immutable Skiko [ShaderBrush] instances via [brushCache]. */
fun DrawScope.drawGlassShadow(
    shape: GlassShape,
    shadow: GlassShadow,
    alpha: Float,
    brushCache: ShaderBrushCache?,
) {
    if (alpha <= 0f || size.width <= 0f || size.height <= 0f) return
    if (!isRuntimeShaderSupported()) return

    val sourceScale = density / GlassDefaults.SourceDensity
    val reach = (shadow.radius * sourceScale).coerceAtLeast(1f)
    val offsetX = shadow.offsetX * sourceScale
    val offsetY = shadow.offsetY * sourceScale
    val brush = if (brushCache != null) {
        brushCache.obtain(this, shape, shape.smoothing, alpha, shadow = shadow) { ts, te, be, bs ->
            buildGlassShadowBrush(shape, ts, te, be, bs, shadow, alpha, reach, offsetX, offsetY)
        }
    } else {
        buildGlassShadowBrush(
            shape,
            shape.topStart.toPx(size, this),
            shape.topEnd.toPx(size, this),
            shape.bottomEnd.toPx(size, this),
            shape.bottomStart.toPx(size, this),
            shadow,
            alpha,
            reach,
            offsetX,
            offsetY,
        )
    }

    drawRect(
        brush = brush,
        topLeft = Offset(-reach + offsetX, -reach + offsetY),
        size = Size(size.width + reach * 2f, size.height + reach * 2f),
    )
}

private fun DrawScope.buildGlassShadowBrush(
    shape: GlassShape,
    topStart: Float,
    topEnd: Float,
    bottomEnd: Float,
    bottomStart: Float,
    shadow: GlassShadow,
    alpha: Float,
    reach: Float,
    offsetX: Float,
    offsetY: Float,
): ShaderBrush {
    val shader = sharedShaderCache.obtainRuntimeShader(GLASS_SHADOW_SHADER_KEY, GLASS_SHADOW_SHADER)
    shader.setSilhouetteRadiiUniforms(layoutDirection, size, topStart, topEnd, bottomEnd, bottomStart, shape.smoothing)
    shader.setFloatUniform("in_shadowOffset", offsetX, offsetY)
    shader.setFloatUniform("in_shadowShape", reach, shadow.dispersion)
    val color = shadow.color
    shader.setFloatUniform(
        "in_shadowColor",
        color.red,
        color.green,
        color.blue,
        color.alpha * alpha,
    )
    return shader.asBrush()
}

/**
 * Sets the size, corner radii and smoothing uniforms of the shared distance field for a silhouette
 * drawn at [scale] (1 = full resolution; > 1 = a downscaled layer, where every pixel-space value
 * divides by the level). Rim, edge stroke, mask, shadow and the refraction shader in `miuix-glass`
 * all resolve them here: start/end swap under RTL, radii clamp to half the smaller side, smoothing
 * to 0..1.
 */
fun RuntimeShader.setSilhouetteUniforms(
    shape: GlassShape,
    layoutDirection: LayoutDirection,
    size: Size,
    density: Density,
    scale: Float = 1f,
) = setSilhouetteUniforms(
    shape = shape,
    layoutDirection = layoutDirection,
    size = size,
    density = density,
    smoothing = shape.smoothing,
    scale = scale,
)

/** [setSilhouetteUniforms] for any rounded-corner shape; [smoothing] is 0 for circular corners. */
internal fun RuntimeShader.setSilhouetteUniforms(
    shape: CornerBasedShape,
    layoutDirection: LayoutDirection,
    size: Size,
    density: Density,
    smoothing: Float,
    scale: Float = 1f,
) {
    val start = shape.topStart.toPx(size, density)
    val end = shape.topEnd.toPx(size, density)
    val bottomEnd = shape.bottomEnd.toPx(size, density)
    val bottomStart = shape.bottomStart.toPx(size, density)
    setSilhouetteRadiiUniforms(layoutDirection, size, start, end, bottomEnd, bottomStart, smoothing, scale)
}

internal fun RuntimeShader.setSilhouetteRadiiUniforms(
    layoutDirection: LayoutDirection,
    size: Size,
    start: Float,
    end: Float,
    bottomEnd: Float,
    bottomStart: Float,
    smoothing: Float,
    scale: Float = 1f,
) {
    val halfMin = min(size.width, size.height) * 0.5f
    val isLtr = layoutDirection == LayoutDirection.Ltr
    setFloatUniform("in_size", size.width / scale, size.height / scale)
    setFloatUniform(
        "in_radii",
        (if (isLtr) start else end).coerceIn(0f, halfMin) / scale,
        (if (isLtr) end else start).coerceIn(0f, halfMin) / scale,
        (if (isLtr) bottomEnd else bottomStart).coerceIn(0f, halfMin) / scale,
        (if (isLtr) bottomStart else bottomEnd).coerceIn(0f, halfMin) / scale,
    )
    setFloatUniform("in_smoothing", smoothing.coerceIn(0f, 1f))
}
