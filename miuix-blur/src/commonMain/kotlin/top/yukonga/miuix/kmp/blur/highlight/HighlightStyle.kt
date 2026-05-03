// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.highlight

import androidx.annotation.FloatRange
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceAtMost
import top.yukonga.miuix.kmp.blur.RuntimeShader
import top.yukonga.miuix.kmp.blur.RuntimeShaderCache
import top.yukonga.miuix.kmp.blur.asComposeShader
import top.yukonga.miuix.kmp.blur.internal.BLOOM_STROKE_SHADER
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import kotlin.math.sqrt

/** Reference origin (in normalized UV) used to convert a [LightPosition] into a 3D direction. */
private const val LIGHT_REF_X = 0.5f
private const val LIGHT_REF_Y = 0.7f

/**
 * Pluggable shading model for an edge highlight stroke. Implement this interface to
 * provide a custom shader; the built-in [BloomStroke] paints a dual-light bloom stroke.
 */
@Immutable
interface HighlightStyle {

    /** Tint applied when no [createShader] is available (legacy / unsupported platforms). */
    val color: Color

    /** Blend mode used when compositing the highlight layer over the content. */
    val blendMode: BlendMode

    /**
     * Returns a runtime [Shader] that paints each pixel of the highlight bounds. Return
     * `null` to fall back to a flat [color] stroke.
     *
     * @param strokeWidthPx Stroke band width in pixels (already coerced).
     * @param highlightAlpha Overall opacity from [Highlight.alpha], folded into the shader uniforms.
     */
    fun DrawScope.createShader(
        shape: Shape,
        strokeWidthPx: Float,
        highlightAlpha: Float,
        runtimeShaderCache: RuntimeShaderCache,
    ): Shader?

    companion object {

        /** Default style — aliases [BloomStroke.GlassStrokeMiddleLight]. */
        @Stable
        val Default: BloomStroke = BloomStroke.GlassStrokeMiddleLight
    }
}

/**
 * 3D position of a light source for [BloomStroke] shading.
 *
 * The shader normalizes the 3D vector `(x - 0.5, y - 0.7, z)` to obtain a unit direction; thus:
 * - [x], [y] in `[0, 1]` mark the light's UV position relative to the highlight bounds;
 *   `(0.5, 0.7)` is the reference origin (so a light placed there contributes nothing).
 * - [z] is signed depth in the same units. Negative `z` places the light behind the
 *   surface plane and illuminates the inward-facing edge.
 */
@Immutable
data class LightPosition(
    val x: Float,
    val y: Float,
    val z: Float,
)

/** A directional light contributing to a [BloomStroke]. */
@Immutable
data class LightSource(
    val position: LightPosition,
    val color: Color = Color.White,
    @param:FloatRange(from = 0.0) val intensity: Float = 1f,
)

/**
 * Edge bloom stroke shading model for [HighlightStyle].
 *
 * The shader builds a rounded-rect SDF, derives a 3D hemispheric normal along the
 * rounded edge, and paints two directional lights — one occupying the upper hemisphere
 * and one the lower (via `dot((0, ∓1, 0), n)` cosine factors). A flat stroke color is
 * added on top to form the thin glassy outline.
 *
 * @property color Base color of the stroke; alpha drives the stroke brightness.
 * @property blendMode Compositing mode for the highlight layer; default is additive.
 * @property innerBlurRadius Soft halo extent in dp — the depth the lighting reaches
 *  inward from the rounded edge. Larger values produce wider, softer halos.
 * @property primaryLight Upper-hemisphere light.
 * @property secondaryLight Lower-hemisphere light.
 */
@Immutable
data class BloomStroke(
    override val color: Color = Color.White.copy(alpha = 0.05f),
    override val blendMode: BlendMode = BlendMode.Plus,
    val innerBlurRadius: Dp = 2.8.dp,
    val primaryLight: LightSource = LightSource(
        position = LightPosition(0.5f, 0.5f, -0.5f),
        intensity = 0.4f,
    ),
    val secondaryLight: LightSource = LightSource(
        position = LightPosition(0.5f, 0.8f, -0.5f),
        intensity = 0.25f,
    ),
) : HighlightStyle {

    override fun DrawScope.createShader(
        shape: Shape,
        strokeWidthPx: Float,
        highlightAlpha: Float,
        runtimeShaderCache: RuntimeShaderCache,
    ): Shader? {
        if (!isRuntimeShaderSupported()) return null
        val sizePx = size
        val shader = runtimeShaderCache.obtainRuntimeShader("BloomStroke", BLOOM_STROKE_SHADER)
        shader.setFloatUniform("size", sizePx.width, sizePx.height)
        shader.setFloatUniform("cornerRadii", getCornerRadii(shape))
        shader.setFloatUniform("strokeWidth", strokeWidthPx)
        shader.setFloatUniform("innerBlurRadius", innerBlurRadius.toPx())
        shader.setFloatUniform("highlightAlpha", highlightAlpha)
        shader.setColorUniform("strokeColor", color.copy(alpha = 1f))
        shader.setFloatUniform("strokeAlphaMul", color.alpha)
        applyLightUniforms(shader, "1", primaryLight)
        applyLightUniforms(shader, "2", secondaryLight)
        return shader.asComposeShader()
    }

    companion object {

        /** Large card preset, light theme. */
        @Stable
        val GlassStrokeBigLight: BloomStroke = BloomStroke(
            color = Color.White.copy(alpha = 0.05f),
            innerBlurRadius = 3.5.dp,
            primaryLight = LightSource(
                position = LightPosition(0.5f, 0.5f, -0.5f),
                intensity = 0.3f,
            ),
            secondaryLight = LightSource(
                position = LightPosition(0.5f, 0.6f, -0.5f),
                intensity = 0.2f,
            ),
        )

        /** Standard card preset, light theme. */
        @Stable
        val GlassStrokeMiddleLight: BloomStroke = BloomStroke(
            color = Color.White.copy(alpha = 0.05f),
            innerBlurRadius = 2.8.dp,
            primaryLight = LightSource(
                position = LightPosition(0.5f, 0.5f, -0.5f),
                intensity = 0.4f,
            ),
            secondaryLight = LightSource(
                position = LightPosition(0.5f, 0.8f, -0.5f),
                intensity = 0.25f,
            ),
        )

        /** Compact card preset, light theme. */
        @Stable
        val GlassStrokeSmallLight: BloomStroke = BloomStroke(
            color = Color.White.copy(alpha = 0.05f),
            innerBlurRadius = 2.6.dp,
            primaryLight = LightSource(
                position = LightPosition(0.5f, 0.5f, -0.5f),
                intensity = 0.6f,
            ),
            secondaryLight = LightSource(
                position = LightPosition(0.5f, 0.95f, -0.5f),
                intensity = 0.35f,
            ),
        )

        /** Large card preset, dark theme. */
        @Stable
        val GlassStrokeBigDark: BloomStroke = BloomStroke(
            color = Color.White.copy(alpha = 0.05f),
            innerBlurRadius = 1.7.dp,
            primaryLight = LightSource(
                position = LightPosition(0.5f, 0.5f, -0.5f),
                intensity = 0.4f,
            ),
            secondaryLight = LightSource(
                position = LightPosition(0.5f, 0.6f, -0.5f),
                intensity = 0.25f,
            ),
        )

        /** Standard card preset, dark theme. */
        @Stable
        val GlassStrokeMiddleDark: BloomStroke = BloomStroke(
            color = Color.White.copy(alpha = 0.06f),
            innerBlurRadius = 2.0.dp,
            primaryLight = LightSource(
                position = LightPosition(0.5f, 0.5f, -0.5f),
                intensity = 0.5f,
            ),
            secondaryLight = LightSource(
                position = LightPosition(0.5f, 0.8f, -0.5f),
                intensity = 0.25f,
            ),
        )

        /** Compact card preset, dark theme. */
        @Stable
        val GlassStrokeSmallDark: BloomStroke = BloomStroke(
            color = Color.White.copy(alpha = 0.08f),
            innerBlurRadius = 2.3.dp,
            primaryLight = LightSource(
                position = LightPosition(0.5f, 0.5f, -0.5f),
                intensity = 0.6f,
            ),
            secondaryLight = LightSource(
                position = LightPosition(0.5f, 0.95f, -0.36f),
                intensity = 0.25f,
            ),
        )
    }
}

internal fun applyLightUniforms(
    shader: RuntimeShader,
    suffix: String,
    light: LightSource,
) {
    val dx = light.position.x - LIGHT_REF_X
    val dy = light.position.y - LIGHT_REF_Y
    val dz = light.position.z
    val len = sqrt(dx * dx + dy * dy + dz * dz).coerceAtLeast(1e-6f)
    shader.setFloatUniform("lightDir$suffix", dx / len, dy / len, dz / len)
    shader.setColorUniform("lightColor$suffix", light.color.copy(alpha = 1f))
    shader.setFloatUniform("lightIntensity$suffix", light.color.alpha * light.intensity)
}

internal fun DrawScope.getCornerRadii(shape: Shape): FloatArray {
    val sizePx = size
    val maxRadius = sizePx.minDimension / 2f
    val cornerShape = shape as? CornerBasedShape ?: return FloatArray(4) { maxRadius }
    val isLtr = layoutDirection == LayoutDirection.Ltr
    val topLeft =
        if (isLtr) cornerShape.topStart.toPx(sizePx, this) else cornerShape.topEnd.toPx(sizePx, this)
    val topRight =
        if (isLtr) cornerShape.topEnd.toPx(sizePx, this) else cornerShape.topStart.toPx(sizePx, this)
    val bottomRight =
        if (isLtr) cornerShape.bottomEnd.toPx(sizePx, this) else cornerShape.bottomStart.toPx(sizePx, this)
    val bottomLeft =
        if (isLtr) cornerShape.bottomStart.toPx(sizePx, this) else cornerShape.bottomEnd.toPx(sizePx, this)
    // Returned in [TL, TR, BL, BR] order, matching the shader's cornerRadii layout.
    return floatArrayOf(
        topLeft.fastCoerceAtMost(maxRadius),
        topRight.fastCoerceAtMost(maxRadius),
        bottomLeft.fastCoerceAtMost(maxRadius),
        bottomRight.fastCoerceAtMost(maxRadius),
    )
}
