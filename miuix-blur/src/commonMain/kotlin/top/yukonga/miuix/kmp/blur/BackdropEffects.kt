// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import top.yukonga.miuix.kmp.blur.internal.BLEND_MODE_SHADER_EXTENDED
import top.yukonga.miuix.kmp.blur.internal.BLEND_MODE_SHADER_STANDARD
import top.yukonga.miuix.kmp.blur.internal.BLUR_KERNEL_REACH
import top.yukonga.miuix.kmp.blur.internal.BLUR_RADIUS_TO_SIGMA
import top.yukonga.miuix.kmp.blur.internal.PROGRESSIVE_BLUR_MAX_TAP
import top.yukonga.miuix.kmp.blur.internal.adjustedVarianceForExp
import top.yukonga.miuix.kmp.blur.internal.adjustedVarianceForScale
import top.yukonga.miuix.kmp.blur.internal.chain
import top.yukonga.miuix.kmp.blur.internal.computeProgressiveGradient
import top.yukonga.miuix.kmp.blur.internal.createBlurEffect
import top.yukonga.miuix.kmp.blur.internal.createProgressiveBlurEffect
import top.yukonga.miuix.kmp.blur.internal.progressiveCurveKind
import top.yukonga.miuix.kmp.blur.internal.quantizedLevelScale
import top.yukonga.miuix.kmp.blur.internal.rampBlendModeShader
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import kotlin.math.ceil
import kotlin.math.sqrt
import top.yukonga.miuix.kmp.blur.internal.runtimeShaderEffect as createRuntimeShaderEffect

/** Maximum number of blend layers supported by [blendColors]. Extra entries are dropped. */
internal const val MAX_BLEND_LAYERS = 8

/**
 * Chains a separable Blur into [BackdropEffectScope.renderEffect], raises
 * [BackdropEffectScope.padding] to the kernel reach and sets
 * [BackdropEffectScope.downscaleFactor]. Non-positive radii skip that axis.
 *
 * ```kotlin
 * Modifier.drawBackdrop(backdrop, shape = { shape }, effects = { blur(20f * density) })
 * ```
 *
 * @param radiusX Horizontal blur radius in pixels.
 * @param radiusY Vertical blur radius in pixels. Defaults to [radiusX].
 */
fun BackdropEffectScope.blur(radiusX: Float, radiusY: Float = radiusX) {
    if (!isRuntimeShaderSupported() || (radiusX <= 0f && radiusY <= 0f)) return
    val scope = impl
    val sigmaX = radiusX * BLUR_RADIUS_TO_SIGMA
    val sigmaY = radiusY * BLUR_RADIUS_TO_SIGMA

    // 1/4-octave grid: a step is ~19% in scale (a fifth of the old 2× jump), while size and
    // sampling phase hold between steps — a per-radius level resampled every frame.
    val sigma = maxOf(sigmaX, sigmaY)
    val scale = quantizedLevelScale(sigma * sigma)

    // Padding covers the kernel reach in source pixels; typical radii (adjVar <= 9) only reach 9 texels.
    val adjVar = adjustedVarianceForScale(sigma * sigma, scale)
    val kernelReach = if (adjVar <= 9f) 9 else BLUR_KERNEL_REACH
    val kernelPadding = kernelReach * scale
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
        scope.cachedBlurScale == scale
    ) {
        scope.cachedBlurResult
    } else {
        createBlurEffect(
            radiusX,
            radiusY,
            scale,
            adjustedVarianceForScale(sigmaX * sigmaX, scale),
            adjustedVarianceForScale(sigmaY * sigmaY, scale),
            Size(paddedW, paddedH),
            scope,
        ).also {
            scope.cachedBlurRadiusX = radiusX
            scope.cachedBlurRadiusY = radiusY
            scope.cachedBlurSizeW = paddedW
            scope.cachedBlurSizeH = paddedH
            scope.cachedBlurScale = scale
            scope.cachedBlurResult = it
        }
    } ?: return

    downscaleFactor = scale
    renderEffect = renderEffect?.chain(effect) ?: effect
}

/**
 * Chains a **progressive** (gradient) Blur: radius ramps from full to zero along [gradient] on an
 * adaptive downscaled pass.
 *
 * Paired with `drawBackdrop`'s `progressiveGradient` (as [Modifier.progressiveTextureBlur] wires),
 * the draw path folds post-blur [blendColors] and dithering into the downscaled pass and overlays a
 * narrow 1× clear-end band when downscaled for a pixel-sharp transition.
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
    val rX = radiusX.coerceAtLeast(0f)
    val rY = radiusY.coerceAtLeast(0f)
    if (!scope.progressiveRampActive && rX <= 0f && rY <= 0f) return

    val sigmaX = rX * BLUR_RADIUS_TO_SIGMA
    val sigmaY = rY * BLUR_RADIUS_TO_SIGMA
    val sigma = maxOf(sigmaX, sigmaY)
    val exp = when {
        sigma < 4.5f -> 0
        sigma < 12f -> 1
        sigma < 32f -> 2
        sigma < 115f -> 3
        else -> 4
    }
    val scale = (1 shl exp).toFloat()

    if (rX > 0f || rY > 0f) {
        val maxAdjVar = adjustedVarianceForExp(sigma * sigma, exp)
        val maxLoopR = (2f * sqrt(maxAdjVar) - 1f).coerceIn(0f, PROGRESSIVE_BLUR_MAX_TAP.toFloat())
        if (maxLoopR >= 0.5f) {
            val kernelPadding = (ceil(maxLoopR).toInt() + 1) * scale
            if (kernelPadding > padding) {
                padding = kernelPadding
            }
        }
    }

    val compW = size.width
    val compH = size.height
    val paddedW = compW + padding * 2f
    val paddedH = compH + padding * 2f

    // apply() compares these against the final padding to detect a stale sampling clamp.
    scope.blurBuiltPaddedW = paddedW
    scope.blurBuiltPaddedH = paddedH

    if (scope.progressiveRampActive) {
        scope.progRecordedRadiusX = rX
        scope.progRecordedRadiusY = rY
        scope.progRecordedExp = exp
        scope.progressivePreEffect = renderEffect
        renderEffect = null
        downscaleFactor = scale
        return
    }

    val effect = obtainProgressiveBlurEffect(scope, rX, rY, exp, gradient) ?: return
    downscaleFactor = scale
    renderEffect = renderEffect?.chain(effect) ?: effect
}

internal fun obtainProgressiveBlurEffect(
    scope: BackdropEffectScopeImpl,
    radiusX: Float,
    radiusY: Float,
    exp: Int,
    gradient: ProgressiveBlur,
    padLeft: Float = scope.padding,
    padTop: Float = scope.padding,
    padRight: Float = scope.padding,
    padBottom: Float = scope.padding,
    ditherCoeff: Float = 0f,
    tintColor: Color = Color.Unspecified,
): RenderEffect? {
    val scale = (1 shl exp).toFloat()
    val compW = scope.size.width
    val compH = scope.size.height
    val paddedW = compW + padLeft + padRight
    val paddedH = compH + padTop + padBottom
    if (radiusX <= 0f && radiusY <= 0f) {
        computeProgressiveGradient(
            downScale = scale,
            padLeft = padLeft,
            padTop = padTop,
            compW = compW,
            compH = compH,
            angleDeg = gradient.angle,
            startFraction = gradient.startFraction,
            endFraction = gradient.endFraction,
            curve = gradient.curve,
            scope = scope,
        )
        return null
    }
    val cached = scope.cachedProgResult
    if (cached != null &&
        scope.cachedProgRadiusX == radiusX &&
        scope.cachedProgRadiusY == radiusY &&
        scope.cachedProgSizeW == paddedW &&
        scope.cachedProgSizeH == paddedH &&
        scope.cachedProgPadLeft == padLeft &&
        scope.cachedProgPadTop == padTop &&
        scope.cachedProgCompW == compW &&
        scope.cachedProgCompH == compH &&
        scope.cachedProgExp == exp &&
        scope.cachedProgAngle == gradient.angle &&
        scope.cachedProgStart == gradient.startFraction &&
        scope.cachedProgEnd == gradient.endFraction &&
        scope.cachedProgCurve == gradient.curve &&
        scope.cachedProgTintColor == tintColor &&
        scope.cachedProgDitherCoeff == ditherCoeff
    ) {
        return cached
    }
    val sigmaX = radiusX * BLUR_RADIUS_TO_SIGMA
    val sigmaY = radiusY * BLUR_RADIUS_TO_SIGMA
    return createProgressiveBlurEffect(
        radiusX,
        radiusY,
        scale,
        adjustedVarianceForExp(sigmaX * sigmaX, exp),
        adjustedVarianceForExp(sigmaY * sigmaY, exp),
        padLeft,
        padTop,
        compW,
        compH,
        gradient.angle,
        gradient.startFraction,
        gradient.endFraction,
        gradient.curve,
        scope,
        tintColor = tintColor,
        ditherCoeff = ditherCoeff,
    ).also {
        scope.cachedProgRadiusX = radiusX
        scope.cachedProgRadiusY = radiusY
        scope.cachedProgSizeW = paddedW
        scope.cachedProgSizeH = paddedH
        scope.cachedProgPadLeft = padLeft
        scope.cachedProgPadTop = padTop
        scope.cachedProgCompW = compW
        scope.cachedProgCompH = compH
        scope.cachedProgExp = exp
        scope.cachedProgAngle = gradient.angle
        scope.cachedProgStart = gradient.startFraction
        scope.cachedProgEnd = gradient.endFraction
        scope.cachedProgCurve = gradient.curve
        scope.cachedProgTintColor = tintColor
        scope.cachedProgDitherCoeff = ditherCoeff
        scope.cachedProgResult = it
    }
}

/**
 * Registers a noise dither pass. Applied at full resolution after upscaling, so each screen pixel
 * dithers independently.
 *
 * @param coefficient Dither strength stored in [BackdropEffectScope.noiseCoefficient]; ≤ 0 is ignored.
 */
fun BackdropEffectScope.noiseDither(coefficient: Float) {
    if (coefficient <= 0f) return
    noiseCoefficient = coefficient
}

/**
 * Chains all blend layers of [colors] as one runtime shader pass (≤ [MAX_BLEND_LAYERS] entries).
 * Its brightness/saturation go into the same pass, independent of any [colorControls].
 *
 * @param colors Blend layers plus brightness/saturation; an empty layer list is a no-op.
 */
fun BackdropEffectScope.blendColors(colors: BlurColors) {
    if (colors.blendColors.isEmpty()) return
    if (!isRuntimeShaderSupported()) return
    val scope = impl

    // Ramp mode: a blend directly following progressiveBlur is recorded for the single ramp pass.
    if (scope.progressiveRampActive && !scope.progRecordedRadiusX.isNaN() &&
        renderEffect == null && scope.progressivePostBlend == null
    ) {
        scope.progressivePostBlend = colors
        return
    }

    renderEffect = renderEffect.chain(obtainBlendColorsEffect(scope, colors))
}

/** Blend-layer [RenderEffect] for [colors], cached by value so per-frame rebuilds still hit. */
internal fun obtainBlendColorsEffect(scope: BackdropEffectScopeImpl, colors: BlurColors): RenderEffect {
    val cached = scope.cachedBlendResult
    if (cached != null && scope.cachedBlendColors == colors) return cached

    val needsExtended = colors.needsExtendedBlend()
    val shader = scope.obtainRuntimeShader(
        if (needsExtended) "MiBlendModesExt" else "MiBlendModesStd",
        if (needsExtended) BLEND_MODE_SHADER_EXTENDED else BLEND_MODE_SHADER_STANDARD,
    )
    shader.setBlendLayerUniforms(scope, colors, needsExtended)
    return createRuntimeShaderEffect(shader, "child").also {
        scope.cachedBlendColors = colors
        scope.cachedBlendResult = it
    }
}

/**
 * Folded post pass for the progressive composite: the blend chain ramp-mixed over the untouched
 * source in one pass, so the stack below is evaluated once. Band inputs are in the stack's
 * downscaled padded space. Uncached — the caller caches the assembled stack.
 */
internal fun buildRampBlendColorsEffect(
    scope: BackdropEffectScopeImpl,
    colors: BlurColors,
    gradVecX: Float,
    gradVecY: Float,
    gradOffset: Float,
    curve: Float,
): RenderEffect {
    val cached = scope.cachedRampBlendResult
    if (cached != null &&
        scope.cachedRampBlendColors == colors &&
        scope.cachedRampBlendGradVecX == gradVecX &&
        scope.cachedRampBlendGradVecY == gradVecY &&
        scope.cachedRampBlendGradOffset == gradOffset &&
        scope.cachedRampBlendCurve == curve
    ) {
        return cached
    }
    val needsExtended = colors.needsExtendedBlend()
    val curveKind = progressiveCurveKind(curve)
    val shader = scope.obtainRuntimeShader(
        if (needsExtended) "MiBlendModesExtRamp_c$curveKind" else "MiBlendModesStdRamp_c$curveKind",
        rampBlendModeShader(includeExtended = needsExtended, curveKind = curveKind),
    )
    shader.setBlendLayerUniforms(scope, colors, needsExtended)
    shader.setFloatUniform("in_gradVec", gradVecX, gradVecY)
    shader.setFloatUniform("in_gradOffset", gradOffset)
    if (curveKind == 3) shader.setFloatUniform("in_curve", curve)
    return createRuntimeShaderEffect(shader, "child").also {
        scope.cachedRampBlendColors = colors
        scope.cachedRampBlendGradVecX = gradVecX
        scope.cachedRampBlendGradVecY = gradVecY
        scope.cachedRampBlendGradOffset = gradOffset
        scope.cachedRampBlendCurve = curve
        scope.cachedRampBlendResult = it
    }
}

private fun BlurColors.needsExtendedBlend(): Boolean {
    val layerCount = minOf(blendColors.size, MAX_BLEND_LAYERS)
    return (0 until layerCount).any { blendColors[it].mode.value >= 100 }
}

private fun RuntimeShader.setBlendLayerUniforms(scope: BackdropEffectScopeImpl, colors: BlurColors, needsExtended: Boolean) {
    val layerList = colors.blendColors
    val layerCount = minOf(layerList.size, MAX_BLEND_LAYERS)
    val modes = scope.blendModesBuffer
    val colorData = scope.blendColorsBuffer

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

/**
 * The standard texture-blur chain ([Modifier.textureBlur]'s internals) for a custom `drawBackdrop`
 * block: [noiseDither] → [colorControls] (linear space) → [blur] → [blendColors].
 *
 * ```kotlin
 * effects = { textureBlurEffect(blurRadius = 30f, colors = colors); /* chain more */ }
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
 * [textureBlurEffect] with [progressiveBlur] in place of [blur], so the radius ramps to zero along
 * [gradient]. Pair it with `drawBackdrop`'s `progressiveGradient` (same [gradient]) to fold
 * [colors] into the progressive ramp and keep a sharp 1× clear end.
 *
 * @param blurRadiusX Horizontal blur radius in dp at full strength.
 * @param blurRadiusY Vertical blur radius in dp at full strength. Defaults to [blurRadiusX].
 * @param gradient Direction and band controlling where the blur is full vs zero.
 * @param noiseCoefficient Noise dithering coefficient. 0 (the default) disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 */
fun BackdropEffectScope.progressiveTextureBlurEffect(
    blurRadiusX: Float,
    blurRadiusY: Float = blurRadiusX,
    gradient: ProgressiveBlur = ProgressiveBlur.Top,
    noiseCoefficient: Float = BlurDefaults.ProgressiveNoiseCoefficient,
    colors: BlurColors = BlurColors(),
) {
    val clampedX = blurRadiusX.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    val clampedY = blurRadiusY.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    noiseDither(noiseCoefficient)
    colorControls(colors.brightness, colors.contrast, colors.saturation)
    progressiveBlur(clampedX * density, clampedY * density, gradient)
    blendColors(colors)
}
