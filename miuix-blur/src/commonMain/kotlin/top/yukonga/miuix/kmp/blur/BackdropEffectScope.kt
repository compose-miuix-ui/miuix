// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.blur.internal.COLOR_CONTROLS_SHADER
import top.yukonga.miuix.kmp.blur.internal.MAX_BLUR_TAPS
import top.yukonga.miuix.kmp.blur.internal.chain
import top.yukonga.miuix.kmp.material.DefaultRuntimeShaderCache
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.RuntimeShaderCache
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.internal.runtimeShaderEffect as createRuntimeShaderEffect

/** Scope for chaining effects onto a backdrop layer via [renderEffect]. */
sealed interface BackdropEffectScope :
    Density,
    RuntimeShaderCache {

    val size: Size
    val layoutDirection: LayoutDirection
    val shape: Shape

    /**
     * Extra padding for blur overflow; effects may only raise it. Raise it before the effect that
     * reads it — a raise after [blur] works but re-runs the block once.
     */
    var padding: Float

    /** The accumulated render effect chain. */
    var renderEffect: RenderEffect?

    /**
     * Downscale level for the backdrop layer recording: 1 = full resolution, 4 = 1/16 area.
     * Fractional, snapped to a 1/4-octave grid.
     */
    var downscaleFactor: Float

    /**
     * Noise dithering coefficient. When [downscaleFactor] > 1 the noise is applied at full
     * resolution after upscaling, so each screen pixel dithers independently. The progressive
     * composite folds its own dither into the downscaled stack and skips that pass while the
     * coefficient is low enough for the grain to be imperceptible.
     */
    var noiseCoefficient: Float
}

/**
 * Applies brightness, contrast and saturation. Brightness works in linear (gamma 2.2) space via a
 * runtime shader — a linear `ColorMatrix` offset would shift hue.
 *
 * @param brightness Brightness in linear space; 0 leaves it unchanged.
 * @param contrast Contrast multiplier; 1 leaves it unchanged.
 * @param saturation Saturation multiplier; 1 leaves it unchanged.
 */
fun BackdropEffectScope.colorControls(
    brightness: Float = 0f,
    contrast: Float = 1f,
    saturation: Float = 1f,
) {
    if (brightness == 0f && contrast == 1f && saturation == 1f) return
    if (!isRuntimeShaderSupported()) return
    val scope = impl

    // Reuse the cached effect while the three values are unchanged.
    val cached = scope.cachedColorResult
    val effect = if (cached != null &&
        scope.cachedColorBrightness == brightness &&
        scope.cachedColorContrast == contrast &&
        scope.cachedColorSaturation == saturation
    ) {
        cached
    } else {
        val shader = obtainRuntimeShader("ColorControls", COLOR_CONTROLS_SHADER).apply {
            setFloatUniform("in_brightness", brightness)
            setFloatUniform("in_contrast", contrast)
            setFloatUniform("in_saturation", saturation)
        }
        createRuntimeShaderEffect(shader, "child").also {
            scope.cachedColorBrightness = brightness
            scope.cachedColorContrast = contrast
            scope.cachedColorSaturation = saturation
            scope.cachedColorResult = it
        }
    }

    renderEffect = renderEffect.chain(effect)
}

/** Chains an arbitrary [RenderEffect] onto [BackdropEffectScope.renderEffect]. */
fun BackdropEffectScope.effect(effect: RenderEffect) {
    renderEffect = renderEffect.chain(effect)
}

/**
 * Applies a custom runtime shader effect.
 *
 * **Pixel-space uniforms must be divided by [BackdropEffectScope.downscaleFactor].** After [blur]
 * (or any effect raising it) the layer is recorded at `1 / downscaleFactor`, and the shader gets
 * `coord` in that downscaled space — size, padding, corner radii, band widths etc. must follow,
 * or the geometry lands outside the layer and samples transparent black.
 *
 * @param key Cache key for the compiled shader.
 * @param shaderString The AGSL/SkSL shader source code.
 * @param uniformShaderName The uniform that receives the input image.
 * @param block Sets uniforms on the shader before rendering.
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

internal abstract class BackdropEffectScopeImpl :
    BackdropEffectScope,
    RuntimeShaderCache {

    override var density: Float = 1f
    override var fontScale: Float = 1f
    override var size: Size = Size.Unspecified
    override var layoutDirection: LayoutDirection = LayoutDirection.Ltr
    override var padding: Float = 0f
    override var renderEffect: RenderEffect? = null
    override var downscaleFactor: Float = 1f
    override var noiseCoefficient: Float = 0f

    var runtimeShaderCache: RuntimeShaderCache = DefaultRuntimeShaderCache()

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

    // Padded size blur() built its sampling clamp from in the current apply(); NaN = no blur ran.
    internal var blurBuiltPaddedW: Float = Float.NaN
    internal var blurBuiltPaddedH: Float = Float.NaN

    // chain() allocates a native RenderEffect — cache last result keyed on inputs (incl. level).
    internal var cachedBlurRadiusX: Float = Float.NaN
    internal var cachedBlurRadiusY: Float = Float.NaN
    internal var cachedBlurSizeW: Float = Float.NaN
    internal var cachedBlurSizeH: Float = Float.NaN
    internal var cachedBlurScale: Float = -1f
    internal var cachedBlurResult: RenderEffect? = null

    // blendColors()/colorControls() don't depend on the animating radius, so cache them: a fixed
    // tint rebuilds once. A built effect captured its uniforms, so sharing the RuntimeShader is safe.
    internal var cachedBlendColors: BlurColors? = null
    internal var cachedBlendResult: RenderEffect? = null
    internal var cachedColorBrightness: Float = Float.NaN
    internal var cachedColorContrast: Float = Float.NaN
    internal var cachedColorSaturation: Float = Float.NaN
    internal var cachedColorResult: RenderEffect? = null

    // progressiveBlur() builds a gradient-scaled variant; cache it on the same inputs plus the
    // gradient (angle/start/end) so an animating radius over a fixed gradient rebuilds once per size.
    internal var cachedProgRadiusX: Float = Float.NaN
    internal var cachedProgRadiusY: Float = Float.NaN
    internal var cachedProgSizeW: Float = Float.NaN
    internal var cachedProgSizeH: Float = Float.NaN

    // Builders also read raw padding and component size; padded dimensions alone cannot tell them
    // apart when a custom effects block changes padding independently of the radius.
    internal var cachedProgPad: Float = Float.NaN
    internal var cachedProgCompW: Float = Float.NaN
    internal var cachedProgCompH: Float = Float.NaN
    internal var cachedProgExp: Int = -1
    internal var cachedProgAngle: Float = Float.NaN
    internal var cachedProgStart: Float = Float.NaN
    internal var cachedProgEnd: Float = Float.NaN
    internal var cachedProgCurve: Float = Float.NaN
    internal var cachedProgResult: RenderEffect? = null

    /**
     * True while the draw path renders the ramp as a multi-level composite: [progressiveBlur] then
     * only records radii and skips its (never drawn) effect. Cleared when the composite is
     * unsupported, so the fallback builds fully.
     */
    internal var progressiveCompositeActive: Boolean = false

    /** Effects chained before [progressiveBlur] in composite mode; later ones stay post-blur. */
    internal var progressivePreEffect: RenderEffect? = null

    /**
     * A [blendColors] call directly after [progressiveBlur] in composite mode, recorded instead of
     * built: the composite folds it into the stack's single ramp-weighted pass, where the SrcOver
     * branch form would evaluate the level stack twice.
     */
    internal var progressivePostBlend: BlurColors? = null

    /** Platform scratch for the composite's radius-independent caches; dropped on [reset]. */
    internal var progressiveMaskScratch: Any? = null

    // Full-res noise pass cache, keyed on chain input identity + coefficient: keeps the chained
    // result stable across updateEffects() runs so the composite's post-chain identity check hits.
    internal var cachedNoiseChainInput: RenderEffect? = null
    internal var cachedNoiseChainCoeff: Float = Float.NaN
    internal var cachedNoiseChainResult: RenderEffect? = null

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
        downscaleFactor = 1f
        noiseCoefficient = 0f
        blurBuiltPaddedW = Float.NaN
        blurBuiltPaddedH = Float.NaN
        // Stale pre-blur stash must not survive an effects block that stops calling progressiveBlur.
        progressivePreEffect = null
        progressivePostBlend = null
        // Composite mode: clear the recorded radii so a stale ramp can't outlive an effects block
        // that stops calling progressiveBlur; standalone mode keeps them (loop-effect cache key).
        if (progressiveCompositeActive) {
            cachedProgRadiusX = Float.NaN
            cachedProgRadiusY = Float.NaN
        }
        effects()
        // blur() bakes its sampling clamp from the padding at its call site. If a later effect
        // raised the padding, re-run once (padding only grows, so one pass converges) — otherwise
        // the clamp sits inside the recording and the bottom/right edge smears.
        if (!blurBuiltPaddedW.isNaN() &&
            (blurBuiltPaddedW != size.width + padding * 2f || blurBuiltPaddedH != size.height + padding * 2f)
        ) {
            val finalPadding = padding
            renderEffect = null
            downscaleFactor = 1f
            noiseCoefficient = 0f
            blurBuiltPaddedW = Float.NaN
            blurBuiltPaddedH = Float.NaN
            progressivePreEffect = null
            progressivePostBlend = null
            if (progressiveCompositeActive) {
                cachedProgRadiusX = Float.NaN
                cachedProgRadiusY = Float.NaN
            }
            padding = finalPadding
            effects()
        }
    }

    fun reset() {
        density = 1f
        fontScale = 1f
        size = Size.Unspecified
        layoutDirection = LayoutDirection.Ltr
        padding = 0f
        renderEffect = null
        downscaleFactor = 1f
        noiseCoefficient = 0f
        blurBuiltPaddedW = Float.NaN
        blurBuiltPaddedH = Float.NaN
        cachedBlurRadiusX = Float.NaN
        cachedBlurRadiusY = Float.NaN
        cachedBlurSizeW = Float.NaN
        cachedBlurSizeH = Float.NaN
        cachedBlurScale = -1f
        cachedBlurResult = null
        cachedBlendColors = null
        cachedBlendResult = null
        cachedColorBrightness = Float.NaN
        cachedColorContrast = Float.NaN
        cachedColorSaturation = Float.NaN
        cachedColorResult = null
        cachedProgRadiusX = Float.NaN
        cachedProgRadiusY = Float.NaN
        cachedProgSizeW = Float.NaN
        cachedProgSizeH = Float.NaN
        cachedProgPad = Float.NaN
        cachedProgCompW = Float.NaN
        cachedProgCompH = Float.NaN
        cachedProgExp = -1
        cachedProgAngle = Float.NaN
        cachedProgStart = Float.NaN
        cachedProgEnd = Float.NaN
        cachedProgCurve = Float.NaN
        cachedProgResult = null
        progressiveCompositeActive = false
        progressivePreEffect = null
        progressivePostBlend = null
        progressiveMaskScratch = null
        cachedNoiseChainInput = null
        cachedNoiseChainCoeff = Float.NaN
        cachedNoiseChainResult = null
    }
}

/** [BackdropEffectScope] is sealed with only [BackdropEffectScopeImpl], so this cast is safe. */
internal val BackdropEffectScope.impl: BackdropEffectScopeImpl
    get() = this as BackdropEffectScopeImpl
