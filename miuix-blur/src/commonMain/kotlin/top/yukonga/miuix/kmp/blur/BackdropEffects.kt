// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import top.yukonga.miuix.kmp.blur.internal.BLEND_MODE_SHADER_EXTENDED
import top.yukonga.miuix.kmp.blur.internal.BLEND_MODE_SHADER_STANDARD
import top.yukonga.miuix.kmp.blur.internal.BLUR_KERNEL_REACH
import top.yukonga.miuix.kmp.blur.internal.BLUR_RADIUS_TO_SIGMA
import top.yukonga.miuix.kmp.blur.internal.adjustedVarianceForExp
import top.yukonga.miuix.kmp.blur.internal.chain
import top.yukonga.miuix.kmp.blur.internal.computeDownScaleBlend
import top.yukonga.miuix.kmp.blur.internal.createBlurEffect
import top.yukonga.miuix.kmp.blur.internal.createProgressiveBlurEffect
import top.yukonga.miuix.kmp.blur.internal.downScaleExpFor
import top.yukonga.miuix.kmp.blur.internal.runtimeShaderEffect as createRuntimeShaderEffect

/** Maximum number of blend layers supported by [blendColors]. Extra entries are dropped. */
internal const val MAX_BLEND_LAYERS = 8

/**
 * Chains a separable Blur into the scope's [BackdropEffectScope.renderEffect],
 * adjusts [BackdropEffectScope.padding] to cover the kernel reach, and updates
 * [BackdropEffectScope.downscaleFactor]. Non-positive radii skip that axis.
 *
 * Raising [BackdropEffectScope.padding] after [blur] is safe (the effect block re-runs once
 * with the final padding), but raising it beforehand avoids the rebuild.
 *
 * Typical use:
 * ```
 * Modifier.drawBackdrop(backdrop, shape = { shape }, effects = {
 *     blur(20f * density)
 * })
 * ```
 *
 * @param radiusX Horizontal blur radius in pixels.
 * @param radiusY Vertical blur radius in pixels. Defaults to [radiusX] for isotropic blur.
 */
fun BackdropEffectScope.blur(radiusX: Float, radiusY: Float = radiusX) {
    if (!isRuntimeShaderSupported()) return
    val scope = impl
    val sigmaX = radiusX * BLUR_RADIUS_TO_SIGMA
    val sigmaY = radiusY * BLUR_RADIUS_TO_SIGMA

    // Pick the downscale exponent: forced for the cross-fade lo/hi passes, otherwise the adaptive
    // choice — which also records the transition-band bracket for the node to read and cross-fade.
    val exp = if (scope.forcedDownscaleExp >= 0) {
        scope.forcedDownscaleExp
    } else {
        val bracket = computeDownScaleBlend(maxOf(sigmaX, sigmaY))
        scope.blurBlendExpLo = bracket.expLo
        scope.blurBlendExpHi = bracket.expHi
        scope.blurBlendFactor = bracket.blend
        bracket.expLo
    }
    val sf = 1 shl exp

    // Padding covers the kernel reach in source pixels so recording size stays
    // stable across radius changes within the same downscale level.
    val kernelPadding = (BLUR_KERNEL_REACH * sf).toFloat()
    if (kernelPadding > padding) {
        padding = kernelPadding
    }

    val paddedW = size.width + padding * 2f
    val paddedH = size.height + padding * 2f

    // apply() compares these against the final padding to detect a stale sampling clamp.
    scope.blurBuiltPaddedW = paddedW
    scope.blurBuiltPaddedH = paddedH

    val effect = if (scope.cachedBlurResult != null &&
        scope.cachedBlurRadiusX == radiusX &&
        scope.cachedBlurRadiusY == radiusY &&
        scope.cachedBlurSizeW == paddedW &&
        scope.cachedBlurSizeH == paddedH &&
        scope.cachedBlurExp == exp
    ) {
        scope.cachedBlurResult
    } else {
        createBlurEffect(
            radiusX,
            radiusY,
            sf,
            adjustedVarianceForExp(sigmaX * sigmaX, exp),
            adjustedVarianceForExp(sigmaY * sigmaY, exp),
            Size(paddedW, paddedH),
            scope,
        ).also {
            scope.cachedBlurRadiusX = radiusX
            scope.cachedBlurRadiusY = radiusY
            scope.cachedBlurSizeW = paddedW
            scope.cachedBlurSizeH = paddedH
            scope.cachedBlurExp = exp
            scope.cachedBlurResult = it
        }
    } ?: return

    downscaleFactor = sf
    renderEffect = renderEffect?.chain(effect) ?: effect
}

/**
 * Chains a separable **progressive** (gradient) Blur into the scope's
 * [BackdropEffectScope.renderEffect]: a true variable-radius blur whose radius ramps continuously
 * from full to zero along [gradient]. It uses the same adaptive downscale as [blur], so it never
 * blocks/aliases at large radii — but that single downscale also means the `intensity → 0` end
 * samples the downscaled backdrop (soft, not pixel-sharp).
 *
 * Paired with `drawBackdrop`'s `progressiveGradient` (as [Modifier.progressiveTextureBlur] wires),
 * the draw path renders the multi-level composite instead — with a genuinely sharp clear end — and
 * this call only records the target radii for it.
 *
 * @param radiusX Horizontal blur radius in pixels at full strength.
 * @param radiusY Vertical blur radius in pixels at full strength. Defaults to [radiusX].
 * @param gradient Direction and band controlling where the blur is full vs zero.
 */
fun BackdropEffectScope.progressiveBlur(
    radiusX: Float,
    radiusY: Float = radiusX,
    gradient: ProgressiveBlur = ProgressiveBlur.Top,
) {
    if (!isRuntimeShaderSupported()) return
    val scope = impl
    if (scope.progressiveCompositeActive) {
        // The composite consumes only the radii, so skip building the never-drawn loop shader.
        // Radius 0 stays here (levels collapse to identity, effects keep riding the ramp).
        scope.cachedProgRadiusX = radiusX.coerceAtLeast(0f)
        scope.cachedProgRadiusY = radiusY.coerceAtLeast(0f)
        scope.cachedProgResult = null
        // Split the effects block here: chained-so-far → pre-blur, what follows → post-blur.
        scope.progressivePreEffect = renderEffect
        renderEffect = null
        return
    }
    if (radiusX <= 0f && radiusY <= 0f) return
    val sigmaX = radiusX * BLUR_RADIUS_TO_SIGMA
    val sigmaY = radiusY * BLUR_RADIUS_TO_SIGMA

    // Same adaptive downscale as blur(): the box prefilter at each level is what keeps the fixed
    // 7-tap kernel from aliasing (blocky banding) at large radii. Capping it would defeat that.
    val exp = downScaleExpFor(maxOf(sigmaX * sigmaX, sigmaY * sigmaY))
    val sf = 1 shl exp

    val kernelPadding = (BLUR_KERNEL_REACH * sf).toFloat()
    if (kernelPadding > padding) {
        padding = kernelPadding
    }

    val compW = size.width
    val compH = size.height
    val paddedW = compW + padding * 2f
    val paddedH = compH + padding * 2f

    val effect = if (scope.cachedProgResult != null &&
        scope.cachedProgRadiusX == radiusX &&
        scope.cachedProgRadiusY == radiusY &&
        scope.cachedProgSizeW == paddedW &&
        scope.cachedProgSizeH == paddedH &&
        scope.cachedProgExp == exp &&
        scope.cachedProgAngle == gradient.angle &&
        scope.cachedProgStart == gradient.startFraction &&
        scope.cachedProgEnd == gradient.endFraction &&
        scope.cachedProgCurve == gradient.curve
    ) {
        scope.cachedProgResult
    } else {
        createProgressiveBlurEffect(
            radiusX,
            radiusY,
            sf,
            adjustedVarianceForExp(sigmaX * sigmaX, exp),
            adjustedVarianceForExp(sigmaY * sigmaY, exp),
            Size(paddedW, paddedH),
            padding,
            compW,
            compH,
            gradient.angle,
            gradient.startFraction,
            gradient.endFraction,
            gradient.curve,
            scope,
        ).also {
            scope.cachedProgRadiusX = radiusX
            scope.cachedProgRadiusY = radiusY
            scope.cachedProgSizeW = paddedW
            scope.cachedProgSizeH = paddedH
            scope.cachedProgExp = exp
            scope.cachedProgAngle = gradient.angle
            scope.cachedProgStart = gradient.startFraction
            scope.cachedProgEnd = gradient.endFraction
            scope.cachedProgCurve = gradient.curve
            scope.cachedProgResult = it
        }
    } ?: return

    downscaleFactor = sf
    renderEffect = renderEffect?.chain(effect) ?: effect
}

/**
 * Registers a noise dither pass with the given [coefficient]. Non-positive values are ignored.
 * Noise is applied at full resolution after upscaling so each screen pixel gets independent
 * dithering, which prevents banding visible at low blur radii.
 *
 * @param coefficient The noise dithering strength stored in [BackdropEffectScope.noiseCoefficient].
 *   Values at or below 0 are ignored.
 */
fun BackdropEffectScope.noiseDither(coefficient: Float) {
    if (coefficient <= 0f) return
    noiseCoefficient = coefficient
}

/**
 * Chains all blend color layers from [colors] as a single runtime shader pass. Up to
 * [MAX_BLEND_LAYERS] entries are honored. Brightness and saturation in [colors] are
 * folded into the blend shader's uniforms (separate from any [colorControls] you may
 * have already chained).
 *
 * @param colors The [BlurColors] whose blend layers and brightness/saturation drive the shader.
 *   An empty blend-layer list is a no-op.
 */
fun BackdropEffectScope.blendColors(colors: BlurColors) {
    if (colors.blendColors.isEmpty()) return
    if (!isRuntimeShaderSupported()) return
    val scope = impl

    // Reuse the cached blend effect on an unchanged config — a hit skips the color conversions,
    // uniform uploads and effect creation, leaving only the chain. Keyed on the BlurColors value,
    // so callers that rebuild it (or its list) each frame still hit.
    val cached = scope.cachedBlendResult
    val effect = if (cached != null && scope.cachedBlendColors == colors) {
        cached
    } else {
        buildBlendColorsEffect(scope, colors).also {
            scope.cachedBlendColors = colors
            scope.cachedBlendResult = it
        }
    }

    renderEffect = renderEffect.chain(effect)
}

/** Builds the blend-layer [RenderEffect] for [colors] (no chaining); see [blendColors] for caching. */
private fun buildBlendColorsEffect(scope: BackdropEffectScopeImpl, colors: BlurColors): RenderEffect {
    val layerList = colors.blendColors
    val layerCount = minOf(layerList.size, MAX_BLEND_LAYERS)
    val modes = scope.blendModesBuffer
    val colorData = scope.blendColorsBuffer

    val needsExtended = (0 until layerCount).any { layerList[it].mode.value >= 100 }
    val shaderKey = if (needsExtended) "MiBlendModesExt" else "MiBlendModesStd"
    val shaderSource = if (needsExtended) BLEND_MODE_SHADER_EXTENDED else BLEND_MODE_SHADER_STANDARD

    val shader = scope.obtainRuntimeShader(shaderKey, shaderSource).apply {
        setFloatUniform("layerCount", layerCount.toFloat())

        // Skiko lacks IntArray / array-indexed Color uniform — pack into flat float arrays.
        // Zero unused trailing slots to avoid stale state from a previous invocation.
        for (i in 0 until layerCount) {
            val entry = layerList[i]
            modes[i] = entry.mode.value.toFloat()
            val c = entry.color.convert(ColorSpaces.Srgb)
            val a = c.alpha
            colorData[i * 4] = c.red * a
            colorData[i * 4 + 1] = c.green * a
            colorData[i * 4 + 2] = c.blue * a
            colorData[i * 4 + 3] = a
        }
        for (i in layerCount until MAX_BLEND_LAYERS) {
            modes[i] = 0f
            colorData[i * 4] = 0f
            colorData[i * 4 + 1] = 0f
            colorData[i * 4 + 2] = 0f
            colorData[i * 4 + 3] = 0f
        }
        setFloatUniform("blendModes", modes)
        setFloatUniform("layerColors", colorData)
        // Standard family doesn't declare these uniforms; setting them would error.
        if (needsExtended) {
            setFloatUniform("uSaturation", colors.saturation)
            setFloatUniform("uBrightness", colors.brightness)
            setFloatUniform("uLuminanceAmount", 0f)
            setFloatUniform("uLuminanceValues", 0f, 0f, 0f, 0f)
        }
    }
    return createRuntimeShaderEffect(shader, "child")
}

/**
 * Runs the standard texture-blur preset chain inside a custom [drawBackdrop] effect block.
 *
 * Equivalent to what [Modifier.textureBlur] applies internally:
 * 1. [noiseDither] for anti-banding
 * 2. [colorControls] for brightness/contrast/saturation in linear (gamma 2.2) space
 * 3. [blur] with the given radii in dp (multiplied by [BackdropEffectScope.density])
 * 4. [blendColors] for the layered tinting
 *
 * Use this to compose the standard preset with additional custom effects:
 * ```
 * Modifier.drawBackdrop(backdrop, shape = { shape }, effects = {
 *     textureBlurEffect(blurRadius = 30f, colors = colors)
 *     // ...then chain your own effect on top
 * })
 * ```
 *
 * @param blurRadiusX Horizontal blur radius in dp.
 * @param blurRadiusY Vertical blur radius in dp. Defaults to [blurRadiusX].
 * @param noiseCoefficient Noise dithering coefficient. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 */
fun BackdropEffectScope.textureBlurEffect(
    blurRadiusX: Float,
    blurRadiusY: Float = blurRadiusX,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
) {
    val clampedX = blurRadiusX.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    val clampedY = blurRadiusY.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    noiseDither(noiseCoefficient)
    colorControls(colors.brightness, colors.contrast, colors.saturation)
    blur(clampedX * density, clampedY * density)
    blendColors(colors)
}

/**
 * Runs the progressive (gradient) texture-blur preset chain inside a custom [drawBackdrop] effect
 * block: like [textureBlurEffect] but with [progressiveBlur] in place of [blur], so the blur radius
 * ramps from full to zero along [gradient].
 *
 * Pair this with `drawBackdrop`'s `progressiveGradient` (same [gradient]) so the draw path renders
 * the multi-level composite with a genuinely sharp full-resolution clear end —
 * [Modifier.progressiveTextureBlur] wires both together.
 *
 * @param blurRadiusX Horizontal blur radius in dp at full strength.
 * @param blurRadiusY Vertical blur radius in dp at full strength. Defaults to [blurRadiusX].
 * @param gradient Direction and band controlling where the blur is full vs zero.
 * @param noiseCoefficient Noise dithering coefficient. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 */
fun BackdropEffectScope.progressiveTextureBlurEffect(
    blurRadiusX: Float,
    blurRadiusY: Float = blurRadiusX,
    gradient: ProgressiveBlur = ProgressiveBlur.Top,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
) {
    val clampedX = blurRadiusX.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    val clampedY = blurRadiusY.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    noiseDither(noiseCoefficient)
    colorControls(colors.brightness, colors.contrast, colors.saturation)
    progressiveBlur(clampedX * density, clampedY * density, gradient)
    blendColors(colors)
}
