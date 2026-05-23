// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.blur.internal.BlurResult
import top.yukonga.miuix.kmp.blur.internal.COLOR_CONTROLS_SHADER
import top.yukonga.miuix.kmp.blur.internal.MAX_BLUR_TAPS
import top.yukonga.miuix.kmp.blur.internal.chain
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
 * Applies brightness, contrast, and saturation adjustments to the backdrop.
 *
 * Brightness is applied in linear (gamma 2.2) space via a runtime shader to avoid
 * the hue shift a linear `ColorMatrix` offset would introduce.
 */
fun BackdropEffectScope.colorControls(
    brightness: Float = 0f,
    contrast: Float = 1f,
    saturation: Float = 1f,
) {
    if (brightness == 0f && contrast == 1f && saturation == 1f) return

    runtimeShaderEffect(
        key = "ColorControls",
        shaderString = COLOR_CONTROLS_SHADER,
        uniformShaderName = "child",
    ) {
        setFloatUniform("in_brightness", brightness)
        setFloatUniform("in_contrast", contrast)
        setFloatUniform("in_saturation", saturation)
    }
}

/**
 * Chains an arbitrary [RenderEffect] onto the backdrop effect pipeline.
 */
fun BackdropEffectScope.effect(effect: RenderEffect) {
    renderEffect = renderEffect.chain(effect)
}

/**
 * Applies a custom runtime shader effect to the backdrop.
 *
 * **Pixel-space uniforms must be scaled by [BackdropEffectScope.downscaleFactor].** When chained
 * after [blur] (or any effect that raises [BackdropEffectScope.downscaleFactor]), the backdrop
 * layer is recorded at `1 / downscaleFactor` resolution, and the shader receives `coord` values
 * in the downscaled layer's pixel space. Any uniform that describes a pixel-space distance
 * (size, padding/offset, corner radii, refraction band, …) must be divided by
 * [BackdropEffectScope.downscaleFactor] inside [block], otherwise the geometry will sit far
 * outside the downscaled layer's bounds and every sample will land on transparent black.
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
    val effect = createRuntimeShaderEffect(
        runtimeShader = obtainRuntimeShader(key, shaderString).apply(block),
        uniformShaderName = uniformShaderName,
    )
    renderEffect = renderEffect.chain(effect)
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

    // Scratch reused across updateEffects(); X / Y axes are sequential and share buffers.
    internal val blurRawWeights: DoubleArray = DoubleArray(14)
    internal val blurParamOffsets: FloatArray = FloatArray(MAX_BLUR_TAPS)
    internal val blurParamWeights: FloatArray = FloatArray(MAX_BLUR_TAPS)

    // Per-tapCount uniform buffers — uniform array length must match the shader declaration.
    private val shaderOffsetsByTaps: Array<FloatArray?> = arrayOfNulls(MAX_BLUR_TAPS + 1)
    private val shaderWeightsByTaps: Array<FloatArray?> = arrayOfNulls(MAX_BLUR_TAPS + 1)

    internal fun obtainShaderOffsetsBuffer(tapCount: Int): FloatArray = shaderOffsetsByTaps[tapCount]
        ?: FloatArray(tapCount * 2).also { shaderOffsetsByTaps[tapCount] = it }

    internal fun obtainShaderWeightsBuffer(tapCount: Int): FloatArray = shaderWeightsByTaps[tapCount]
        ?: FloatArray(tapCount).also { shaderWeightsByTaps[tapCount] = it }

    internal val blendModesBuffer: FloatArray = FloatArray(MAX_BLEND_LAYERS)
    internal val blendColorsBuffer: FloatArray = FloatArray(MAX_BLEND_LAYERS * 4)

    // chain() allocates a native RenderEffect — cache last result keyed on inputs.
    internal var cachedBlurRadiusX: Float = Float.NaN
    internal var cachedBlurRadiusY: Float = Float.NaN
    internal var cachedBlurSizeW: Float = Float.NaN
    internal var cachedBlurSizeH: Float = Float.NaN
    internal var cachedBlurResult: BlurResult? = null

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
        cachedBlurRadiusX = Float.NaN
        cachedBlurRadiusY = Float.NaN
        cachedBlurSizeW = Float.NaN
        cachedBlurSizeH = Float.NaN
        cachedBlurResult = null
    }
}

/** [BackdropEffectScope] is sealed with only [BackdropEffectScopeImpl], so this cast is safe. */
internal val BackdropEffectScope.impl: BackdropEffectScopeImpl
    get() = this as BackdropEffectScopeImpl
