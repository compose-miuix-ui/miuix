// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

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
    shader.setFloatUniform("in_rimEdge", edge, style.edge.pow.coerceAtLeast(0.01f), alpha, 0f)
    shader.setFloatUniform(
        "in_lightDir",
        style.light.directionX,
        style.light.directionY,
        style.light.directionZ,
        style.light.angleRange * PI.toFloat(),
    )
    shader.setFloatUniform("in_lightAmt", style.light.intensity, style.light.oppositeIntensity, 0f, 0f)
    shader.setFloatUniform("in_surface", surface.red, surface.green, surface.blue, 1f)

    drawRect(brush = shader.asBrush(), blendMode = BlendMode.Plus)
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

/** Sets the size, corner radii and smoothing uniforms for the shared distance field. */
private fun RuntimeShader.setSilhouetteUniforms(
    shape: GlassShape,
    layoutDirection: LayoutDirection,
    size: Size,
    density: Density,
) {
    val halfMin = min(size.width, size.height) * 0.5f
    val isLtr = layoutDirection == LayoutDirection.Ltr
    val start = shape.topStart.toPx(size, density)
    val end = shape.topEnd.toPx(size, density)
    val bottomEnd = shape.bottomEnd.toPx(size, density)
    val bottomStart = shape.bottomStart.toPx(size, density)

    setFloatUniform("in_size", size.width, size.height)
    setFloatUniform(
        "in_radii",
        (if (isLtr) start else end).coerceIn(0f, halfMin),
        (if (isLtr) end else start).coerceIn(0f, halfMin),
        (if (isLtr) bottomEnd else bottomStart).coerceIn(0f, halfMin),
        (if (isLtr) bottomStart else bottomEnd).coerceIn(0f, halfMin),
    )
    setFloatUniform("in_smoothing", shape.smoothing.coerceIn(0f, 1f))
}
