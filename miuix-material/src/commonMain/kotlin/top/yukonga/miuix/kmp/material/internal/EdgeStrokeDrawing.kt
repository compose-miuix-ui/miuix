// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.internal

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.util.fastCoerceAtLeast
import androidx.compose.ui.util.fastCoerceAtMost
import top.yukonga.miuix.kmp.material.DefaultRuntimeShaderCache
import top.yukonga.miuix.kmp.material.EdgeLightSource
import top.yukonga.miuix.kmp.material.EdgePeak
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.ShaderBrushCache
import top.yukonga.miuix.kmp.material.asBrush
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.material.setSilhouetteRadiiUniforms
import top.yukonga.miuix.kmp.material.setSilhouetteUniforms
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
 * Paints [stroke] along [shape]'s silhouette, sharing [setSilhouetteUniforms] with the mask.
 */
internal fun DrawScope.drawEdgeStrokeShape(
    shape: CornerBasedShape,
    smoothing: Float,
    stroke: EdgeStroke,
    alpha: Float,
    brushCache: ShaderBrushCache? = null,
) {
    if (stroke.width.value <= 0f || alpha <= 0f) return
    if (!isRuntimeShaderSupported()) return
    val widthPx = size.width
    val heightPx = size.height
    if (widthPx <= 0f || heightPx <= 0f) return

    val brush = if (brushCache != null) {
        brushCache.obtain(this, shape, smoothing, alpha, stroke = stroke) { ts, te, be, bs ->
            buildEdgeStrokeBrush(ts, te, be, bs, smoothing, widthPx, heightPx, stroke, alpha)
        }
    } else {
        buildEdgeStrokeBrush(
            shape.topStart.toPx(size, this),
            shape.topEnd.toPx(size, this),
            shape.bottomEnd.toPx(size, this),
            shape.bottomStart.toPx(size, this),
            smoothing,
            widthPx,
            heightPx,
            stroke,
            alpha,
        )
    }

    drawRect(brush = brush, blendMode = stroke.blendMode)
}

private fun DrawScope.buildEdgeStrokeBrush(
    topStart: Float,
    topEnd: Float,
    bottomEnd: Float,
    bottomStart: Float,
    smoothing: Float,
    widthPx: Float,
    heightPx: Float,
    stroke: EdgeStroke,
    alpha: Float,
): ShaderBrush {
    val source = edgeStrokeShader(stroke.peak)
    val shader = sharedShaderCache.obtainRuntimeShader(source.key, source.source)
    shader.setSilhouetteRadiiUniforms(layoutDirection, size, topStart, topEnd, bottomEnd, bottomStart, smoothing)
    shader.setEdgeStrokeUniforms(
        widthPx = widthPx,
        heightPx = heightPx,
        stroke = stroke,
        alpha = alpha,
        density = this,
    )
    return shader.asBrush()
}

/**
 * Sets the stroke's own uniforms — band, colour, the two lights. The silhouette comes from
 * `setSilhouetteUniforms`, which must run first.
 */
internal fun RuntimeShader.setEdgeStrokeUniforms(
    widthPx: Float,
    heightPx: Float,
    stroke: EdgeStroke,
    alpha: Float,
    density: Density,
) {
    val maxRadius = (widthPx * 0.5f).fastCoerceAtMost(heightPx * 0.5f)
    setFloatUniform("in_halfViewFloor", floor(widthPx * 0.5f), floor(heightPx * 0.5f))
    val band = with(density) { stroke.width.toPx() }
    val bevel = with(density) { stroke.bevel.toPx() }
    // Preserve the half-pixel stroke-width bias without rounding to an integer.
    setFloatUniform(
        "in_strokeBand",
        (band + 0.5f).fastCoerceAtMost(maxRadius),
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
    val length = sqrt(dx * dx + dy * dy + dz * dz).fastCoerceAtLeast(1e-6f)
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
