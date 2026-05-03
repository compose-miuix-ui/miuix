// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ColorMatrixColorFilter
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.blur.internal.COLOR_CONTROLS_SHADER
import top.yukonga.miuix.kmp.blur.internal.chain
import top.yukonga.miuix.kmp.blur.internal.colorFilterEffect
import top.yukonga.miuix.kmp.blur.internal.runtimeShaderEffect as createRuntimeShaderEffect

/**
 * Scope for applying effects to a backdrop layer.
 * Effects are chained via [renderEffect] and applied to the backdrop's [GraphicsLayer].
 */
sealed interface BackdropEffectScope :
    Density,
    RuntimeShaderCache {

    val size: Size
    val layoutDirection: LayoutDirection
    val shape: Shape

    /** Extra padding to extend the backdrop layer to accommodate blur overflow. */
    var padding: Float

    /** The accumulated render effect chain. */
    var renderEffect: RenderEffect?

    /** Downscale factor for the backdrop layer recording. 1 = full resolution, 4 = 1/16 area. */
    var downscaleFactor: Int

    /**
     * Noise dithering coefficient for full-resolution application.
     * When [downscaleFactor] > 1, noise is applied at full resolution after upscaling
     * rather than in the RenderEffect chain, so that each screen pixel gets independent noise.
     */
    var noiseCoefficient: Float
}

/**
 * Applies a [ColorFilter] effect to the backdrop.
 */
fun BackdropEffectScope.colorFilter(colorFilter: ColorFilter) {
    if (!isRenderEffectSupported()) return
    renderEffect = colorFilterEffect(renderEffect, colorFilter)
}

/**
 * Applies brightness, contrast, and saturation adjustments to the backdrop.
 *
 * Brightness is applied in linear (gamma 2.2) space when runtime shader support is
 * available, avoiding the hue shift of a linear `ColorMatrix` offset. Falls back to
 * a `ColorMatrix` (sRGB-space) implementation otherwise.
 */
fun BackdropEffectScope.colorControls(
    brightness: Float = 0f,
    contrast: Float = 1f,
    saturation: Float = 1f,
) {
    if (brightness == 0f && contrast == 1f && saturation == 1f) return

    if (isRuntimeShaderSupported()) {
        runtimeShaderEffect(
            key = "ColorControls",
            shaderString = COLOR_CONTROLS_SHADER,
            uniformShaderName = "child",
        ) {
            setFloatUniform("in_brightness", brightness)
            setFloatUniform("in_contrast", contrast)
            setFloatUniform("in_saturation", saturation)
        }
    } else {
        colorFilter(colorControlsColorFilter(brightness, contrast, saturation))
    }
}

/**
 * Chains an arbitrary [RenderEffect] onto the backdrop effect pipeline.
 */
fun BackdropEffectScope.effect(effect: RenderEffect) {
    if (!isRenderEffectSupported()) return
    renderEffect = renderEffect.chain(effect)
}

/**
 * Applies a custom runtime shader effect to the backdrop.
 *
 * @param key Cache key for the compiled shader.
 * @param shaderString The AGSL/SkSL shader source code.
 * @param uniformShaderName The name of the shader uniform that receives the input image.
 * @param block Lambda to set uniforms on the shader before rendering.
 */
fun BackdropEffectScope.runtimeShaderEffect(
    key: String,
    shaderString: String,
    uniformShaderName: String,
    block: RuntimeShader.() -> Unit,
) {
    if (!isRuntimeShaderSupported()) return

    val effect = createRuntimeShaderEffect(
        runtimeShader = obtainRuntimeShader(key, shaderString).apply(block),
        uniformShaderName = uniformShaderName,
    )
    renderEffect = renderEffect.chain(effect)
}

// endregion

// region Internal implementation

private fun colorControlsColorFilter(
    brightness: Float = 0f,
    contrast: Float = 1f,
    saturation: Float = 1f,
): ColorFilter {
    val invSat = 1f - saturation
    val r = 0.213f * invSat
    val g = 0.715f * invSat
    val b = 0.072f * invSat

    val c = contrast
    val t = (0.5f - c * 0.5f + brightness) * 255f
    val s = saturation

    val cr = c * r
    val cg = c * g
    val cb = c * b
    val cs = c * s

    val colorMatrix = ColorMatrix(
        floatArrayOf(
            cr + cs, cg, cb, 0f, t,
            cr, cg + cs, cb, 0f, t,
            cr, cg, cb + cs, 0f, t,
            0f, 0f, 0f, 1f, 0f,
        ),
    )
    return ColorMatrixColorFilter(colorMatrix)
}

internal abstract class BackdropEffectScopeImpl :
    BackdropEffectScope,
    RuntimeShaderCache {

    override var density: Float = 1f
    override var fontScale: Float = 1f
    override var size: Size = Size.Unspecified
    override var layoutDirection: LayoutDirection = LayoutDirection.Ltr
    override var padding: Float = 0f
    override var renderEffect: RenderEffect? = null
    override var downscaleFactor: Int = 1
    override var noiseCoefficient: Float = 0f

    var runtimeShaderCache: RuntimeShaderCache = RuntimeShaderCacheImpl()

    override fun obtainRuntimeShader(key: String, string: String): RuntimeShader = runtimeShaderCache.obtainRuntimeShader(key, string)

    fun update(scope: DrawScope): Boolean {
        val newDensity = scope.density
        val newFontScale = scope.fontScale
        val newSize = scope.size
        val newLayoutDirection = scope.layoutDirection

        val changed = newDensity != density ||
            newFontScale != fontScale ||
            newSize != size ||
            newLayoutDirection != layoutDirection

        if (changed) {
            density = newDensity
            fontScale = newFontScale
            size = newSize
            layoutDirection = newLayoutDirection
        }

        return changed
    }

    fun apply(effects: BackdropEffectScope.() -> Unit) {
        padding = 0f
        renderEffect = null
        downscaleFactor = 1
        noiseCoefficient = 0f
        effects()
    }

    fun reset() {
        density = 1f
        fontScale = 1f
        size = Size.Unspecified
        layoutDirection = LayoutDirection.Ltr
        padding = 0f
        renderEffect = null
        downscaleFactor = 1
        noiseCoefficient = 0f
    }
}

// endregion
