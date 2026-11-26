// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.util.fastCoerceAtLeast
import androidx.compose.ui.util.fastCoerceAtMost
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.fastRoundToInt
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/** Conversion factor from blur radius in pixels to Blur sigma. */
internal const val BLUR_RADIUS_TO_SIGMA = 0.45f

/** Kernel reach in level texels: the outermost tap pair tops out below 12.5, bilinear reaches 13. */
internal const val BLUR_KERNEL_REACH = 13

private const val WEIGHT_THRESHOLD = 0.002

/** ln(2), for converting a natural log back to an octave count. */
private const val LN2 = 0.6931472f

/** Minimum σ a level carries, i.e. `level = σ / MIN_LEVEL_SIGMA`; the ladder's thresholds sit where
 * that ratio crosses a power of two. */
private const val MIN_LEVEL_SIGMA = 2.5f

/**
 * Level grid, 1/4 octave: snapping keeps the recording's size and sampling phase stable between
 * steps (~19% in scale, a fifth of the old 2× jump). A per-radius level resamples every frame.
 */
private const val BUCKETS_PER_OCTAVE = 4

/** Number of levels on the 1/4-octave grid, i.e. log2(16) × [BUCKETS_PER_OCTAVE] + 1. */
internal const val LEVEL_BUCKETS = 17

/**
 * Gaussian shader-cache keys, `[tapCount][bucket]`: lazily populated so unused combinations do not
 * allocate at startup.
 */
private val BLUR_H_KEYS = arrayOfNulls<String>((MAX_BLUR_TAPS + 1) * 2)
private val BLUR_V_KEYS = arrayOfNulls<String>(MAX_BLUR_TAPS + 1)

private fun blurHShaderKey(tapCount: Int, unpremul: Boolean): String {
    val slot = tapCount * 2 + if (unpremul) 1 else 0
    return BLUR_H_KEYS[slot] ?: (if (unpremul) "LMGauss${tapCount}_H_u" else "LMGauss${tapCount}_H").also { BLUR_H_KEYS[slot] = it }
}

private fun blurVShaderKey(tapCount: Int): String = BLUR_V_KEYS[tapCount] ?: "LMGauss${tapCount}_V".also { BLUR_V_KEYS[tapCount] = it }

private val LEVEL_SCALES = FloatArray(LEVEL_BUCKETS) { b ->
    2f.pow(b.toFloat() / BUCKETS_PER_OCTAVE)
}

/** Cache-key bucket for a level: log2(scale) in [BUCKETS_PER_OCTAVE]-ths, i.e. the grid index. */
internal fun levelBucket(scale: Float): Int = ((ln(scale.fastCoerceIn(1f, 16f)) / LN2) * BUCKETS_PER_OCTAVE).fastRoundToInt().fastCoerceIn(0, LEVEL_BUCKETS - 1)

/**
 * Builds the separable Blur [RenderEffect] (H then V) at [downScale] from variances already
 * box-compensated for that level (see [adjustedVarianceForScale]).
 */
internal fun createBlurEffect(
    radiusX: Float,
    radiusY: Float,
    downScale: Float,
    adjustedVarianceX: Float,
    adjustedVarianceY: Float,
    size: Size,
    scope: BackdropEffectScopeImpl,
): RenderEffect? {
    if (radiusX <= 0f && radiusY <= 0f) return null

    // `size / downScale` is the recording's texel space, which is the space the shader samples in;
    // the clamp sits just inside it.
    val texW = (size.width / downScale).fastCoerceAtLeast(1f)
    val texH = (size.height / downScale).fastCoerceAtLeast(1f)

    val rawScratch = scope.blurRawWeights
    val paramOffsets = scope.blurParamOffsets
    val paramWeights = scope.blurParamWeights

    var effect: RenderEffect? = null

    // H / V keep separate keys (each pass owns its shader instance); the bucket splits the levels
    // that can share a frame.
    val hN = if (radiusX > 0f) computeBlurParamsInto(adjustedVarianceX, rawScratch, paramOffsets, paramWeights) else 0
    var hOffsets: FloatArray? = null
    var hWeights: FloatArray? = null
    var hPairWeightSum = 0f
    if (hN > 0) {
        val shaderOffsets = scope.obtainShaderOffsetsBuffer(hN)
        val shaderWeights = scope.obtainShaderWeightsBuffer(hN)
        var pairWeightSum = 0f
        for (i in 0 until hN) {
            shaderOffsets[i] = paramOffsets[i]
            shaderWeights[i] = paramWeights[i]
            pairWeightSum += paramWeights[i]
        }
        hOffsets = shaderOffsets
        hWeights = shaderWeights
        hPairWeightSum = pairWeightSum
    }

    val reuseFromX = radiusY > 0f && hN > 0 && adjustedVarianceX == adjustedVarianceY
    val vN = when {
        radiusY <= 0f -> 0
        reuseFromX -> hN
        else -> computeBlurParamsInto(adjustedVarianceY, rawScratch, paramOffsets, paramWeights)
    }

    if (hN > 0) {
        val hUnpremul = vN <= 0
        val hShader = scope.obtainRuntimeShader(
            blurHShaderKey(hN, hUnpremul),
            blurShaderForTap(hN, axis = 0, unpremul = hUnpremul),
        ).apply {
            setFloatUniform("in_blurOffset", hOffsets!!)
            setFloatUniform("in_blurWeight", hWeights!!)
            setFloatUniform("in_centerWeight", (1f - 2f * hPairWeightSum).fastCoerceAtLeast(0f))
            setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
        }
        effect = runtimeShaderEffect(hShader, "child")
    }

    if (vN > 0) {
        val shaderOffsets: FloatArray
        val shaderWeights: FloatArray
        var pairWeightSum: Float
        if (reuseFromX) {
            shaderOffsets = hOffsets!!
            shaderWeights = hWeights!!
            pairWeightSum = hPairWeightSum
        } else {
            shaderOffsets = scope.obtainShaderOffsetsBuffer(vN)
            shaderWeights = scope.obtainShaderWeightsBuffer(vN)
            pairWeightSum = 0f
            for (i in 0 until vN) {
                shaderOffsets[i] = paramOffsets[i]
                shaderWeights[i] = paramWeights[i]
                pairWeightSum += paramWeights[i]
            }
        }
        val vShader = scope.obtainRuntimeShader(
            blurVShaderKey(vN),
            blurShaderForTap(vN, axis = 1, unpremul = true),
        ).apply {
            setFloatUniform("in_blurOffset", shaderOffsets)
            setFloatUniform("in_blurWeight", shaderWeights)
            setFloatUniform("in_centerWeight", (1f - 2f * pairWeightSum).fastCoerceAtLeast(0f))
            setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
        }
        effect = effect?.chain(runtimeShaderEffect(vShader, "child"))
            ?: runtimeShaderEffect(vShader, "child")
    }
    return effect
}

/**
 * Computes normalized gradient uniforms in the downscaled, padded layer's coordinate space and
 * stores them on [scope].
 */
internal fun computeProgressiveGradient(
    downScale: Float,
    padLeft: Float,
    padTop: Float,
    compW: Float,
    compH: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
    scope: BackdropEffectScopeImpl,
) {
    val rad = angleDeg * (PI / 180.0)
    val ax = cos(rad).toFloat()
    val ay = sin(rad).toFloat()
    val projMin = (if (ax > 0f) 0f else ax * compW) + (if (ay > 0f) 0f else ay * compH)
    val projMax = (if (ax > 0f) ax * compW else 0f) + (if (ay > 0f) ay * compH else 0f)
    val span = projMax - projMin
    val projFullComp = projMin + startFraction * span
    val projZeroComp = projMin + endFraction * span
    val padProj = padLeft * ax + padTop * ay
    val projFull = (projFullComp + padProj) / downScale
    var projZero = (projZeroComp + padProj) / downScale
    // Degenerate band (start == end, or a zero-extent axis) would divide by zero when normalizing.
    if (abs(projZero - projFull) < 1e-3f) projZero = projFull + 1e-3f
    val invGradSpan = 1f / (projZero - projFull)
    scope.progGradVecX = ax * invGradSpan
    scope.progGradVecY = ay * invGradSpan
    scope.progGradOffset = -projFull * invGradSpan
    scope.progClampedCurve = curve.fastCoerceIn(0.05f, 20f)
}

/**
 * Builds the separable **progressive** Blur [RenderEffect]: radius ramps to zero along [angleDeg] at
 * [downScale]. The gradient band is converted here into the downscaled, padded layer's space.
 */
internal fun createProgressiveBlurEffect(
    radiusX: Float,
    radiusY: Float,
    downScale: Float,
    adjustedVarianceX: Float,
    adjustedVarianceY: Float,
    padLeft: Float,
    padTop: Float,
    padRight: Float,
    padBottom: Float,
    compW: Float,
    compH: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
    scope: BackdropEffectScopeImpl,
    tintColor: Color = Color.Unspecified,
    ditherCoeff: Float = 0f,
): RenderEffect? {
    computeProgressiveGradient(
        downScale = downScale,
        padLeft = padLeft,
        padTop = padTop,
        compW = compW,
        compH = compH,
        angleDeg = angleDeg,
        startFraction = startFraction,
        endFraction = endFraction,
        curve = curve,
        scope = scope,
    )
    if (radiusX <= 0f && radiusY <= 0f) return null

    val gradVecX = scope.progGradVecX
    val gradVecY = scope.progGradVecY
    val gradOffset = scope.progGradOffset
    val clampedCurve = scope.progClampedCurve
    val texW = ((compW + padLeft + padRight) / downScale).fastCoerceAtLeast(1f)
    val texH = ((compH + padTop + padBottom) / downScale).fastCoerceAtLeast(1f)
    val curveKind = progressiveCurveKind(clampedCurve)

    // Shader uses σ = radius·0.5 + 0.5, so radius = 2·σ_ds − 1; capped at the constant loop bound.
    val maxCap = PROGRESSIVE_BLUR_MAX_TAP.toFloat()
    val maxRadiusX = (2f * sqrt(adjustedVarianceX) - 1f).fastCoerceIn(0f, maxCap)
    val maxRadiusY = (2f * sqrt(adjustedVarianceY) - 1f).fastCoerceIn(0f, maxCap)

    var effect: RenderEffect? = null

    val hasH = radiusX > 0f && maxRadiusX >= 0.5f
    val hasV = radiusY > 0f && maxRadiusY >= 0.5f
    val hasInlineTint = tintColor != Color.Unspecified
    var tintR = 0f
    var tintG = 0f
    var tintB = 0f
    var tintA = 0f
    if (hasInlineTint && (hasH || hasV)) {
        val srgb = tintColor.convert(ColorSpaces.Srgb)
        tintA = srgb.alpha
        tintR = srgb.red * tintA
        tintG = srgb.green * tintA
        tintB = srgb.blue * tintA
    }

    if (hasH) {
        val tap = progressiveLoopTapBound(maxRadiusX)
        val hUnpremul = !hasV
        val hInlineTint = hasInlineTint && !hasV
        val hInlineNoise = ditherCoeff > 0f && !hasV
        val hShader = scope.obtainRuntimeShader(
            progressiveLoopShaderKey(
                axis = 0,
                downScale = downScale,
                tap = tap,
                curveKind = curveKind,
                unpremul = hUnpremul,
                inlineTint = hInlineTint,
                inlineNoise = hInlineNoise,
            ),
            progressiveBlurShaderForTap(
                tap,
                curveKind = curveKind,
                unpremul = hUnpremul,
                axis = 0,
                inlineTint = hInlineTint,
                inlineNoise = hInlineNoise,
            ),
        ).apply {
            setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
            setFloatUniform("in_maxRadius", maxRadiusX)
            setFloatUniform("in_gradVec", gradVecX, gradVecY)
            setFloatUniform("in_gradOffset", gradOffset)
            if (curveKind == 3) setFloatUniform("in_curve", clampedCurve)
            if (hInlineTint) setFloatUniform("in_tintColor", tintR, tintG, tintB, tintA)
            if (hInlineNoise) setFloatUniform("noise_coeff", ditherCoeff)
        }
        effect = runtimeShaderEffect(hShader, "child")
    }

    if (hasV) {
        val tap = progressiveLoopTapBound(maxRadiusY)
        val vInlineNoise = ditherCoeff > 0f
        val vShader = scope.obtainRuntimeShader(
            progressiveLoopShaderKey(
                axis = 1,
                downScale = downScale,
                tap = tap,
                curveKind = curveKind,
                unpremul = true,
                inlineTint = hasInlineTint,
                inlineNoise = vInlineNoise,
            ),
            progressiveBlurShaderForTap(
                tap,
                curveKind = curveKind,
                unpremul = true,
                axis = 1,
                inlineTint = hasInlineTint,
                inlineNoise = vInlineNoise,
            ),
        ).apply {
            setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
            setFloatUniform("in_maxRadius", maxRadiusY)
            setFloatUniform("in_gradVec", gradVecX, gradVecY)
            setFloatUniform("in_gradOffset", gradOffset)
            if (curveKind == 3) setFloatUniform("in_curve", clampedCurve)
            if (hasInlineTint) setFloatUniform("in_tintColor", tintR, tintG, tintB, tintA)
            if (vInlineNoise) setFloatUniform("noise_coeff", ditherCoeff)
        }
        effect = effect?.chain(runtimeShaderEffect(vShader, "child"))
            ?: runtimeShaderEffect(vShader, "child")
    }

    return effect
}

/**
 * Normalized `smoothRaw` threshold where the 2× intermediate sharp band takes over from
 * [primary] when `exp >= 2`, bridging 4×/8×/16× down to a narrow 1× sharp end.
 */
internal fun progressiveMidSharpThreshold(exp: Int): Float = when {
    exp <= 2 -> 0.60f
    exp == 3 -> 0.68f
    else -> 0.72f
}

/** Normalized `smoothRaw` threshold where the 1× sharp band takes over for [exp]. */
internal fun progressiveSharpThreshold(exp: Int): Float = when (exp) {
    1 -> 0.84f
    2 -> 0.92f
    else -> 0.97f
}

private fun progressivePrimarySigmaAt(radius: Float, exp: Int, threshold: Float): Float {
    val downScale = (1 shl exp).toFloat()
    val sigma = radius * BLUR_RADIUS_TO_SIGMA
    val adjVar = adjustedVarianceForExp(sigma * sigma, exp)
    val maxRDs = (2f * sqrt(adjVar) - 1f).fastCoerceIn(0f, PROGRESSIVE_BLUR_MAX_TAP.toFloat())
    val rThrDs = maxRDs * (1f - threshold)
    val sigmaDsThr = if (rThrDs > 0.004f) rThrDs * 0.5f + 0.5f else 0f
    val impliedVar = IMPLIED_BOX_VARIANCE[exp.fastCoerceIn(0, 4)]
    return sqrt((sigmaDsThr * downScale) * (sigmaDsThr * downScale) + impliedVar)
}

/**
 * 2× loop-shader radius matching the downscaled primary pass at [progressiveMidSharpThreshold]
 * when `exp >= 2`; 0 for `exp <= 1` that bridge straight to 1×.
 */
internal fun progressiveMidSharpLoopRadius(radius: Float, exp: Int): Float {
    if (radius <= 0f || exp < 2) return 0f
    val effSigma = progressivePrimarySigmaAt(radius, exp, progressiveMidSharpThreshold(exp))
    val adjVar2x = adjustedVarianceForExp(effSigma * effSigma, 1)
    return (2f * sqrt(adjVar2x) - 1f).fastCoerceIn(0f, PROGRESSIVE_BLUR_MAX_TAP.toFloat())
}

/**
 * 1× loop-shader radius (`2σ_eff − 1`) matching the underlying tier (`primary` for `exp == 1`,
 * `2×` mid-sharp for `exp >= 2`) at [progressiveSharpThreshold].
 */
internal fun progressiveSharpLoopRadius(radius: Float, exp: Int): Float {
    if (radius <= 0f || exp <= 0) return 0f
    if (exp < 2) {
        val effSigma = progressivePrimarySigmaAt(radius, exp, progressiveSharpThreshold(exp))
        return (2f * effSigma - 1f).fastCoerceIn(0f, PROGRESSIVE_BLUR_MAX_TAP.toFloat())
    }
    val midMaxR = progressiveMidSharpLoopRadius(radius, exp)
    val midThr = progressiveMidSharpThreshold(exp)
    val sharpThr = progressiveSharpThreshold(exp)
    val m = ((sharpThr - midThr) / (1f - midThr)).fastCoerceIn(0f, 1f)
    val rMidAtSharp = midMaxR * (1f - m)
    val sigma2x = if (rMidAtSharp > 0.004f) rMidAtSharp * 0.5f + 0.5f else 0f
    val effSigma = sqrt((sigma2x * 2f) * (sigma2x * 2f) + IMPLIED_BOX_VARIANCE[1])
    return (2f * effSigma - 1f).fastCoerceIn(0f, PROGRESSIVE_BLUR_MAX_TAP.toFloat())
}

/** Linear-raw position of the band's onset (`pow(raw, curve) = threshold`). */
private fun progressiveSharpRawThreshold(curve: Float, threshold: Float): Float = threshold.toDouble().pow(1.0 / curve.fastCoerceIn(0.05f, 20f)).coerceIn(0.0, 1.0).toFloat()

/** Maximum 1× handoff radius (in dp) allowed to fall back to the blur-free alpha mask shader. */
internal const val PROGRESSIVE_SHARP_MASK_MAX_DP = 1.2f

/**
 * Band-limited progressive blur overlay for `[threshold, 1]` at [downScale] (`2f` for the
 * intermediate bridge on `exp >= 3`, `1f` for the pixel-sharp clear end).
 */
internal fun createProgressiveSharpOverlayEffect(
    maxRadiusX: Float,
    maxRadiusY: Float,
    downScale: Float,
    threshold: Float,
    midDecay: Boolean,
    compW: Float,
    compH: Float,
    originX: Float,
    originY: Float,
    bandW: Float,
    bandH: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
    preEffect: RenderEffect?,
    scope: BackdropEffectScopeImpl,
): RenderEffect? {
    val rad = angleDeg * (PI / 180.0)
    val ax = cos(rad).toFloat()
    val ay = sin(rad).toFloat()
    val projMin = (if (ax > 0f) 0f else ax * compW) + (if (ay > 0f) 0f else ay * compH)
    val projMax = (if (ax > 0f) ax * compW else 0f) + (if (ay > 0f) ay * compH else 0f)
    val span = projMax - projMin
    val projFull = projMin + startFraction * span
    var projZero = projMin + endFraction * span
    if (abs(projZero - projFull) < 1e-3f) projZero = projFull + 1e-3f
    val originProj = originX * ax + originY * ay
    val bandFull = projFull - originProj
    val bandZero = projZero - originProj
    val invSpan = 1f / (bandZero - bandFull)
    val gradVecX = ax * invSpan * downScale
    val gradVecY = ay * invSpan * downScale
    val gradOffset = -bandFull * invSpan
    val clampedCurve = curve.fastCoerceIn(0.05f, 20f)
    val curveKind = progressiveCurveKind(clampedCurve)
    val invSharpSpan = 1f / (1f - threshold)

    val hasH = maxRadiusX >= 0.5f
    val hasV = maxRadiusY >= 0.5f
    if (!hasH && !hasV) return null

    val maskThresholdPx = PROGRESSIVE_SHARP_MASK_MAX_DP * scope.density
    if (!midDecay && downScale == 1f && maxRadiusX < maskThresholdPx && maxRadiusY < maskThresholdPx) {
        val shader = scope.obtainRuntimeShader(
            progressiveSharpMaskShaderKey(curveKind),
            progressiveSharpMaskShader(curveKind),
        ).apply {
            setFloatUniform("in_gradVec", gradVecX, gradVecY)
            setFloatUniform("in_gradOffset", gradOffset)
            if (curveKind == 3) setFloatUniform("in_curve", clampedCurve)
            setFloatUniform("in_sharpThreshold", threshold)
            setFloatUniform("in_invSharpSpan", invSharpSpan)
        }
        val maskEffect = runtimeShaderEffect(shader, "child")
        return preEffect?.chain(maskEffect) ?: maskEffect
    }

    fun loopPass(axis: Int, maxRadius: Float, masked: Boolean, unpremul: Boolean): RenderEffect {
        val tap = progressiveLoopTapBound(maxRadius)
        val shader = scope.obtainRuntimeShader(
            progressiveSharpShaderKey(axis = axis, tap = tap, curveKind = curveKind, masked = masked, midDecay = midDecay),
            progressiveBlurShaderForTap(
                tap,
                curveKind = curveKind,
                sharpBand = true,
                sharpMask = masked,
                midDecay = midDecay,
                unpremul = unpremul,
                axis = axis,
            ),
        ).apply {
            setFloatUniform("in_maxCoord", bandW - 0.5f, bandH - 0.5f)
            setFloatUniform("in_maxRadius", maxRadius)
            setFloatUniform("in_gradVec", gradVecX, gradVecY)
            setFloatUniform("in_gradOffset", gradOffset)
            if (curveKind == 3) setFloatUniform("in_curve", clampedCurve)
            setFloatUniform("in_sharpThreshold", threshold)
            setFloatUniform("in_invSharpSpan", invSharpSpan)
        }
        return runtimeShaderEffect(shader, "child")
    }

    val blurEffect = when {
        hasH && hasV -> loopPass(0, maxRadiusX, masked = false, unpremul = false)
            .chain(loopPass(1, maxRadiusY, masked = true, unpremul = true))

        hasH -> loopPass(0, maxRadiusX, masked = true, unpremul = true)

        else -> loopPass(1, maxRadiusY, masked = true, unpremul = true)
    }
    return preEffect?.chain(blurEffect) ?: blurEffect
}

/**
 * Component-space bbox of the region where the 1× sharp overlay is non-zero (`smoothRaw >=
 * [progressiveSharpThreshold]`), widened by [margin] for tap reach and rounding. Null if the band
 * misses the component.
 */
internal fun progressiveSharpBandBounds(
    threshold: Float,
    compW: Float,
    compH: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
    margin: Float,
): Rect? {
    val rad = angleDeg * (PI / 180.0)
    val ax = cos(rad).toFloat()
    val ay = sin(rad).toFloat()
    val projMin = (if (ax > 0f) 0f else ax * compW) + (if (ay > 0f) 0f else ay * compH)
    val projMax = (if (ax > 0f) ax * compW else 0f) + (if (ay > 0f) ay * compH else 0f)
    val span = projMax - projMin
    val projFull = projMin + startFraction * span
    var projZero = projMin + endFraction * span
    if (abs(projZero - projFull) < 1e-3f) projZero = projFull + 1e-3f

    val pThr = projFull + progressiveSharpRawThreshold(curve, threshold) * (projZero - projFull)
    val sign = if (projZero >= projFull) 1f else -1f
    var minX = Float.POSITIVE_INFINITY
    var minY = Float.POSITIVE_INFINITY
    var maxX = Float.NEGATIVE_INFINITY
    var maxY = Float.NEGATIVE_INFINITY
    fun include(x: Float, y: Float) {
        if (x < minX) minX = x
        if (y < minY) minY = y
        if (x > maxX) maxX = x
        if (y > maxY) maxY = y
    }

    fun edge(x0: Float, y0: Float, x1: Float, y1: Float) {
        val d0 = (x0 * ax + y0 * ay - pThr) * sign
        val d1 = (x1 * ax + y1 * ay - pThr) * sign
        if (d0 >= 0f) include(x0, y0)
        if ((d0 < 0f) != (d1 < 0f)) {
            val t = d0 / (d0 - d1)
            include(x0 + (x1 - x0) * t, y0 + (y1 - y0) * t)
        }
    }
    edge(0f, 0f, compW, 0f)
    edge(compW, 0f, compW, compH)
    edge(compW, compH, 0f, compH)
    edge(0f, compH, 0f, 0f)
    if (minX > maxX || minY > maxY) return null

    return Rect(
        (minX - margin).fastCoerceAtLeast(0f),
        (minY - margin).fastCoerceAtLeast(0f),
        (maxX + margin).fastCoerceAtMost(compW),
        (maxY + margin).fastCoerceAtMost(compH),
    )
}

/**
 * Computes the minimum per-edge padding `[left, top, right, bottom]` required so the progressive
 * blur kernel does not sample outside the recorded region along non-clear edges, while keeping
 * zero padding on clear edges whose blur reach stays inside the component.
 */
internal fun computeProgressiveEdgePadding(
    compW: Float,
    compH: Float,
    reachX: Float,
    reachY: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
    outPadding: FloatArray,
) {
    if (compW <= 0f || compH <= 0f || (reachX <= 0f && reachY <= 0f)) {
        outPadding[0] = 0f
        outPadding[1] = 0f
        outPadding[2] = 0f
        outPadding[3] = 0f
        return
    }
    val rad = angleDeg * (PI / 180.0)
    val ax = cos(rad).toFloat()
    val ay = sin(rad).toFloat()
    val projMin = (if (ax > 0f) 0f else ax * compW) + (if (ay > 0f) 0f else ay * compH)
    val projMax = (if (ax > 0f) ax * compW else 0f) + (if (ay > 0f) ay * compH else 0f)
    val span = projMax - projMin
    val projFull = projMin + startFraction * span
    var projZero = projMin + endFraction * span
    if (abs(projZero - projFull) < 1e-3f) projZero = projFull + 1e-3f
    val invSpan = 1f / (projZero - projFull)
    val clampedCurve = curve.fastCoerceIn(0.05f, 20f)

    var pLeft = 0f
    var pTop = 0f
    var pRight = 0f
    var pBottom = 0f

    val steps = 8
    for (iy in 0..steps) {
        val y = compH * iy / steps
        for (ix in 0..steps) {
            val x = compW * ix / steps
            val raw = ((x * ax + y * ay - projFull) * invSpan).fastCoerceIn(0f, 1f)
            val smoothRaw = if (clampedCurve == 1f) raw else raw.pow(clampedCurve)
            val strength = 1f - smoothRaw
            if (strength > 0f) {
                val rx = reachX * strength
                val ry = reachY * strength
                val outL = rx - x
                if (outL > pLeft) pLeft = outL
                val outR = x + rx - compW
                if (outR > pRight) pRight = outR
                val outT = ry - y
                if (outT > pTop) pTop = outT
                val outB = y + ry - compH
                if (outB > pBottom) pBottom = outB
            }
        }
    }
    outPadding[0] = pLeft.fastCoerceIn(0f, reachX)
    outPadding[1] = pTop.fastCoerceIn(0f, reachY)
    outPadding[2] = pRight.fastCoerceIn(0f, reachX)
    outPadding[3] = pBottom.fastCoerceIn(0f, reachY)
}

/**
 * Box-prefilter variance (source px²) the downscale absorbs, anchored at 1/2/4/8/16 and interpolated
 * in log2 between them. The kernel supplies the rest: `adjusted = (σ² − implied) / level²`. Values
 * reproduce the original branch/halving constants, calibrated on the pop-free 4×→8× boundary.
 */
private val IMPLIED_BOX_VARIANCE = floatArrayOf(0f, 3.0265f, 7.5625f, 9.0f, 202.696f)

/** Level for a blur of variance [sigmaSquared]: `σ / [MIN_LEVEL_SIGMA]`, clamped to 1..16. */
private fun levelScaleFor(sigmaSquared: Float): Float = (sqrt(sigmaSquared.fastCoerceAtLeast(0f)) / MIN_LEVEL_SIGMA).fastCoerceIn(1f, 16f)

/**
 * Recording level for [sigmaSquared], snapped to the 1/4-octave grid. The first step (1.19×) stays:
 * one full-resolution noise layer is still ~0.4 ms cheaper than the full-resolution blur.
 */
internal fun quantizedLevelScale(sigmaSquared: Float): Float = LEVEL_SCALES[levelBucket(levelScaleFor(sigmaSquared))]

/** Implied box-prefilter variance (source px²) at level [scale]. */
internal fun impliedBoxVarianceForScale(scale: Float): Float {
    val pos = (ln(scale.fastCoerceIn(1f, 16f)) / LN2).fastCoerceIn(0f, 4f)
    val index = pos.toInt().fastCoerceAtMost(3)
    val t = pos - index
    return IMPLIED_BOX_VARIANCE[index] + (IMPLIED_BOX_VARIANCE[index + 1] - IMPLIED_BOX_VARIANCE[index]) * t
}

/** Box-compensated Gaussian variance (in level px²) for [sigmaSquared] at level [scale]. */
internal fun adjustedVarianceForScale(sigmaSquared: Float, scale: Float): Float = ((sigmaSquared - impliedBoxVarianceForScale(scale)) / (scale * scale)).fastCoerceAtLeast(0.1f)

/** [adjustedVarianceForScale] at a ladder level. */
internal fun adjustedVarianceForExp(sigmaSquared: Float, exp: Int): Float {
    val clampedExp = exp.fastCoerceIn(0, 4)
    val scale = (1 shl clampedExp).toFloat()
    return ((sigmaSquared - IMPLIED_BOX_VARIANCE[clampedExp]) / (scale * scale)).fastCoerceAtLeast(0.1f)
}

/**
 * Generates a 27-tap discrete kernel (-13..+13) from [variance], normalizes it, and merges
 * symmetric side pairs into [outOffsets]/[outWeights] (first [MAX_BLUR_TAPS] slots), returning the
 * pair count (center tap 0 is sampled once outside the loop). Returns 0 when the variance is too
 * small or no pair survives the weight threshold. Scratch arrays keep the observe-driven path
 * allocation-free.
 */
internal fun computeBlurParamsInto(
    variance: Float,
    rawScratch: DoubleArray,
    outOffsets: FloatArray,
    outWeights: FloatArray,
): Int {
    if (variance <= 0.25f) return 0

    val v = variance.toDouble()

    // 1. Generate raw Blur weights for offsets 0..13 into the scratch buffer.
    for (i in 0..13) {
        rawScratch[i] = exp(-0.5 * i.toDouble() * i.toDouble() / v)
    }

    // 2. Normalize so all weights sum to 1.0 (accounting for symmetry)
    var total = rawScratch[0]
    for (i in 1..13) total += rawScratch[i] * 2.0
    for (i in 0..13) rawScratch[i] /= total

    // Pairs 0..6: merge (1,2), (3,4), ..., (11,12), (13); center (0) is sampled once outside the loop.
    var tapCount = 0
    var i = 1
    while (i < 14 && tapCount < MAX_BLUR_TAPS) {
        val wa = rawScratch[i]
        val wb = if (i + 1 < 14) rawScratch[i + 1] else 0.0
        val combined = wa + wb
        if (combined < WEIGHT_THRESHOLD) break
        val w = combined.toFloat()
        if (w > 0f && w < 1f) {
            outOffsets[tapCount] = ((wa * i + wb * (i + 1)) / combined).toFloat()
            outWeights[tapCount] = w
            tapCount++
        }
        i += 2
    }

    return tapCount
}
