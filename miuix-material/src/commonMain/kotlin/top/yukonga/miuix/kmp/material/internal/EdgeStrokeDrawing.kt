// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.internal

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import top.yukonga.miuix.kmp.material.DefaultRuntimeShaderCache
import top.yukonga.miuix.kmp.material.EdgeLightSource
import top.yukonga.miuix.kmp.material.EdgePeak
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.asBrush
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * One cache for the material's own shaders: they are few, hold only a compiled program, and do not
 * depend on any composition.
 */
internal val sharedShaderCache: DefaultRuntimeShaderCache = DefaultRuntimeShaderCache()

/** Reference origin (in normalized UV) used to convert an [EdgeLightSource] into a 3D direction. */
internal const val LIGHT_REF_X = 0.5f
internal const val LIGHT_REF_Y = 0.7f

/**
 * Paints [stroke] with already-resolved corner radii, in pixels, ordered top-left, top-right,
 * bottom-right, bottom-left. [smoothing] of `0` keeps circular corners.
 */
internal fun DrawScope.drawEdgeStrokeShape(
    topLeft: Float,
    topRight: Float,
    bottomRight: Float,
    bottomLeft: Float,
    smoothing: Float,
    stroke: EdgeStroke,
    alpha: Float,
) {
    if (stroke.width.value <= 0f || alpha <= 0f) return
    if (!isRuntimeShaderSupported()) return
    val widthPx = size.width
    val heightPx = size.height
    if (widthPx <= 0f || heightPx <= 0f) return

    val shader = sharedShaderCache.obtainRuntimeShader(
        edgeStrokeShaderKey(stroke.peak),
        edgeStrokeShaderSource(stroke.peak),
    )
    shader.setEdgeStrokeUniforms(
        topLeft = topLeft,
        topRight = topRight,
        bottomRight = bottomRight,
        bottomLeft = bottomLeft,
        smoothing = smoothing,
        widthPx = widthPx,
        heightPx = heightPx,
        stroke = stroke,
        alpha = alpha,
        density = this,
    )

    drawRect(brush = shader.asBrush(), blendMode = stroke.blendMode)
}

/**
 * Sets the uniforms every edge-stroke program reads — silhouette, band, colour, the two lights —
 * so the standalone pass and the fused rim + stroke pass describe the stroke identically.
 */
internal fun RuntimeShader.setEdgeStrokeUniforms(
    topLeft: Float,
    topRight: Float,
    bottomRight: Float,
    bottomLeft: Float,
    smoothing: Float,
    widthPx: Float,
    heightPx: Float,
    stroke: EdgeStroke,
    alpha: Float,
    density: Density,
) {
    val halfW = widthPx * 0.5f
    val halfH = heightPx * 0.5f
    val maxRadius = halfW.coerceAtMost(halfH)
    setFloatUniform("in_size", widthPx, heightPx)
    setFloatUniform("in_halfViewFloor", floor(halfW), floor(halfH))
    setFloatUniform(
        "in_radii",
        topLeft.coerceIn(0f, maxRadius),
        topRight.coerceIn(0f, maxRadius),
        bottomRight.coerceIn(0f, maxRadius),
        bottomLeft.coerceIn(0f, maxRadius),
    )
    setFloatUniform("in_smoothing", smoothing.coerceIn(0f, 1f))
    val band = with(density) { stroke.width.toPx() }
    val bevel = with(density) { stroke.bevel.toPx() }
    // Preserve the half-pixel stroke-width bias without rounding to an integer.
    setFloatUniform(
        "in_strokeBand",
        (band + 0.5f).coerceAtMost(maxRadius),
        bevel + 0.5f,
    )
    val color = stroke.color
    setFloatUniform("in_strokeColor", color.red, color.green, color.blue, color.alpha)
    setFloatUniform("in_strokeAlpha", alpha)
    setEdgeLightUniform("in_light1", "in_light1Color", stroke.primary)
    setEdgeLightUniform("in_light2", "in_light2Color", stroke.secondary)
    when (stroke.peak) {
        EdgePeak.Vertical, EdgePeak.Dual -> Unit

        EdgePeak.Axial -> {
            setEdgeAxisUniform("axis1", stroke.primary, -1f)
            setEdgeAxisUniform("axis2", stroke.secondary, 1f)
        }
    }
}

/** Converts a light's UV position into a unit direction and folds its intensity into `w`. */
internal fun RuntimeShader.setEdgeLightUniform(
    directionName: String,
    colorName: String,
    light: EdgeLightSource,
) {
    val dx = light.x - LIGHT_REF_X
    val dy = light.y - LIGHT_REF_Y
    val dz = light.z
    val length = sqrt(dx * dx + dy * dy + dz * dz).coerceAtLeast(1e-6f)
    setFloatUniform(
        directionName,
        dx / length,
        dy / length,
        dz / length,
        light.color.alpha * light.intensity,
    )
    setFloatUniform(colorName, light.color.red, light.color.green, light.color.blue, 0f)
}

/** Sets the projected XY gate of an axial light, falling back to a vertical axis. */
internal fun RuntimeShader.setEdgeAxisUniform(name: String, light: EdgeLightSource, fallbackY: Float) {
    val dx = light.x - LIGHT_REF_X
    val dy = light.y - LIGHT_REF_Y
    val length = sqrt(dx * dx + dy * dy)
    if (length > 1e-3f) {
        setFloatUniform(name, dx / length, dy / length)
    } else {
        // The light points purely along Z: fall back to the canonical upper / lower split.
        setFloatUniform(name, 0f, fallbackY)
    }
}
