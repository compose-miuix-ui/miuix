// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
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
import kotlin.math.floor
import kotlin.math.min

/**
 * Draws rim lighting along the silhouette at full resolution. `surface` stands in for what is
 * beneath the rim, and shared shaders set all of their uniforms immediately before each draw.
 */
fun DrawScope.drawGlassRim(
    shape: GlassShape,
    style: GlassStyle,
    surface: Color,
    alpha: Float,
) {
    if (alpha <= 0f || size.width <= 0f || size.height <= 0f) return
    if (style.background.unShade >= 0.999f) return
    if (!isRuntimeShaderSupported()) return
    val shader = sharedShaderCache.obtainRuntimeShader(GLASS_RIM_SHADER_KEY, GLASS_RIM_SHADER)

    // Tiny surfaces take precedence over the nominal minimum band width.
    val edge = (style.edge.width * density / GlassDefaults.SourceDensity)
        .coerceAtLeast(1f)
        .coerceAtMost(size.minDimension * 0.5f)

    shader.setSilhouetteUniforms(shape, layoutDirection, size, this)
    shader.setRimUniforms(style, surface, alpha, edge)

    drawRect(brush = shader.asBrush(), blendMode = BlendMode.Plus)
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
) {
    if (alpha <= 0f || size.width <= 0f || size.height <= 0f) return
    if (!isRuntimeShaderSupported()) return
    val source = glassEdgeShader(stroke.peak)
    val shader = sharedShaderCache.obtainRuntimeShader(source.key, source.source)

    shader.setSilhouetteUniforms(shape, layoutDirection, size, this)
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

    drawRect(brush = shader.asBrush(), blendMode = BlendMode.Plus)
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
fun DrawScope.drawGlassMask(shape: GlassShape) {
    if (size.width <= 0f || size.height <= 0f) return
    if (!isRuntimeShaderSupported()) return
    val shader = sharedShaderCache.obtainRuntimeShader(GLASS_MASK_SHADER_KEY, GLASS_MASK_SHADER)
    shader.setSilhouetteUniforms(shape, layoutDirection, size, this)
    drawRect(brush = shader.asBrush(), blendMode = BlendMode.DstIn)
}

/** Draws [shadow] beyond the bounds of [shape]. */
fun DrawScope.drawGlassShadow(
    shape: GlassShape,
    shadow: GlassShadow,
    alpha: Float,
) {
    if (alpha <= 0f || size.width <= 0f || size.height <= 0f) return
    if (!isRuntimeShaderSupported()) return
    val shader = sharedShaderCache.obtainRuntimeShader(GLASS_SHADOW_SHADER_KEY, GLASS_SHADOW_SHADER)

    val sourceScale = density / GlassDefaults.SourceDensity
    val reach = (shadow.radius * sourceScale).coerceAtLeast(1f)
    val offsetX = shadow.offsetX * sourceScale
    val offsetY = shadow.offsetY * sourceScale

    shader.setSilhouetteUniforms(shape, layoutDirection, size, this)
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

    drawRect(
        brush = shader.asBrush(),
        topLeft = Offset(-reach + offsetX, -reach + offsetY),
        size = Size(size.width + reach * 2f, size.height + reach * 2f),
    )
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
    val halfMin = min(size.width, size.height) * 0.5f
    val isLtr = layoutDirection == LayoutDirection.Ltr
    val start = shape.topStart.toPx(size, density)
    val end = shape.topEnd.toPx(size, density)
    val bottomEnd = shape.bottomEnd.toPx(size, density)
    val bottomStart = shape.bottomStart.toPx(size, density)

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
