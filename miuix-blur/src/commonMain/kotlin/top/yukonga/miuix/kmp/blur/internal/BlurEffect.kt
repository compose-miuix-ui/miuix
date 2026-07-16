// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/** Conversion factor from blur radius in pixels to Blur sigma. */
internal const val BLUR_RADIUS_TO_SIGMA = 0.45f

/**
 * Max kernel reach in downsampled pixels: the outermost merged tap pair tops out just below offset
 * 12.5, where bilinear sampling reaches texel 13 — so 13 exactly covers the kernel, no darkening.
 */
internal const val BLUR_KERNEL_REACH = 13

private const val WEIGHT_THRESHOLD = 0.002

/** Highest downscale exponent (downScale = 1 shl exp); covers 1/2/4/8/16. */
private const val MAX_DOWNSCALE_EXP = 4

/**
 * Pre-built Gaussian shader-cache keys, indexed `[tapCount][exp]` (exp = log2(downScale)), so
 * the per-frame rebuild path in [createBlurEffect] avoids allocating a key string per axis.
 */
private val BLUR_H_KEYS: Array<Array<String>> = Array(MAX_BLUR_TAPS + 1) { n ->
    Array(MAX_DOWNSCALE_EXP + 1) { exp -> "LMGauss${n}_H_d${1 shl exp}" }
}
private val BLUR_V_KEYS: Array<Array<String>> = Array(MAX_BLUR_TAPS + 1) { n ->
    Array(MAX_DOWNSCALE_EXP + 1) { exp -> "LMGauss${n}_V_d${1 shl exp}" }
}

/**
 * Builds the separable Blur [RenderEffect] (H then V) for an EXPLICIT [downScale] level using the
 * per-axis variances already compensated for that level's box prefilter (see [adjustedVarianceForExp]).
 * The shader cache keys carry [downScale] so the H/V passes and the cross-fade lo/hi levels each
 * build from a separate shader instance and never alias each other's uniform arrays.
 */
internal fun createBlurEffect(
    radiusX: Float,
    radiusY: Float,
    downScale: Int,
    adjustedVarianceX: Float,
    adjustedVarianceY: Float,
    size: Size,
    scope: BackdropEffectScopeImpl,
): RenderEffect? {
    if (radiusX <= 0f && radiusY <= 0f) return null

    // Texture size uses the same integer arithmetic as drawBackdropLayer
    // to match the actual recording dimensions (size includes padding).
    val texW = (size.width.toInt() / downScale).coerceAtLeast(1).toFloat()
    val texH = (size.height.toInt() / downScale).coerceAtLeast(1).toFloat()

    val rawScratch = scope.blurRawWeights
    val paramOffsets = scope.blurParamOffsets
    val paramWeights = scope.blurParamWeights

    // exp = log2(downScale); downScale is always a power of two in 1..16.
    val exp = downScale.countTrailingZeroBits()

    var effect: RenderEffect? = null

    // H / V use distinct cache keys so each pass builds from its own shader instance; the
    // _d$downScale suffix additionally isolates the cross-fade lo/hi levels from one another.
    if (radiusX > 0f) {
        val n = computeBlurParamsInto(adjustedVarianceX, rawScratch, paramOffsets, paramWeights)
        if (n > 0) {
            val shaderOffsets = scope.obtainShaderOffsetsBuffer(n)
            val shaderWeights = scope.obtainShaderWeightsBuffer(n)
            for (i in 0 until n) {
                shaderOffsets[i * 2] = paramOffsets[i]
                shaderOffsets[i * 2 + 1] = 0f
                shaderWeights[i] = paramWeights[i]
            }
            val hShader = scope.obtainRuntimeShader(
                BLUR_H_KEYS[n][exp],
                BLUR_SHADER_BY_TAP[n],
            ).apply {
                setFloatUniform("in_blurOffset", shaderOffsets)
                setFloatUniform("in_blurWeight", shaderWeights)
                setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
            }
            effect = runtimeShaderEffect(hShader, "child")
        }
    }

    if (radiusY > 0f) {
        val n = computeBlurParamsInto(adjustedVarianceY, rawScratch, paramOffsets, paramWeights)
        if (n > 0) {
            val shaderOffsets = scope.obtainShaderOffsetsBuffer(n)
            val shaderWeights = scope.obtainShaderWeightsBuffer(n)
            for (i in 0 until n) {
                shaderOffsets[i * 2] = 0f
                shaderOffsets[i * 2 + 1] = paramOffsets[i]
                shaderWeights[i] = paramWeights[i]
            }
            val vShader = scope.obtainRuntimeShader(
                BLUR_V_KEYS[n][exp],
                BLUR_SHADER_BY_TAP[n],
            ).apply {
                setFloatUniform("in_blurOffset", shaderOffsets)
                setFloatUniform("in_blurWeight", shaderWeights)
                setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
            }
            effect = effect?.chain(runtimeShaderEffect(vShader, "child"))
                ?: runtimeShaderEffect(vShader, "child")
        }
    }

    return effect
}

/**
 * Builds the separable **progressive** Blur [RenderEffect] (H then V): a variable-radius Gaussian
 * whose radius ramps from full to zero along [angleDeg], at the same adaptive [downScale] as
 * [createBlurEffect]. The gradient axis/band ([startFraction]/[endFraction] of the component's
 * projected extent) are converted here into the downscaled, padded layer's coordinate space;
 * [size] is the padded layer size, [compW]/[compH] the unpadded component in full-resolution px.
 */
internal fun createProgressiveBlurEffect(
    radiusX: Float,
    radiusY: Float,
    downScale: Int,
    adjustedVarianceX: Float,
    adjustedVarianceY: Float,
    size: Size,
    padding: Float,
    compW: Float,
    compH: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
    scope: BackdropEffectScopeImpl,
): RenderEffect? {
    if (radiusX <= 0f && radiusY <= 0f) return null

    val texW = (size.width.toInt() / downScale).coerceAtLeast(1).toFloat()
    val texH = (size.height.toInt() / downScale).coerceAtLeast(1).toFloat()

    // Gradient axis + band in the downscaled, padded layer's coordinate space.
    val rad = angleDeg * (PI / 180.0)
    val ax = cos(rad).toFloat()
    val ay = sin(rad).toFloat()
    val projMin = (if (ax > 0f) 0f else ax * compW) + (if (ay > 0f) 0f else ay * compH)
    val projMax = (if (ax > 0f) ax * compW else 0f) + (if (ay > 0f) ay * compH else 0f)
    val span = projMax - projMin
    val projFullComp = projMin + startFraction * span
    val projZeroComp = projMin + endFraction * span
    val padProj = padding * (ax + ay)
    val projFull = (projFullComp + padProj) / downScale
    var projZero = (projZeroComp + padProj) / downScale
    // Degenerate band (start == end, or a zero-extent axis) would divide by zero in the shader.
    if (abs(projZero - projFull) < 1e-3f) projZero = projFull + 1e-3f

    // Shader uses σ = radius·0.5 + 0.5, so radius = 2·σ_ds − 1; capped at the constant loop bound.
    val maxCap = PROGRESSIVE_BLUR_MAX_TAP.toFloat()
    val maxRadiusX = (2f * sqrt(adjustedVarianceX) - 1f).coerceIn(0f, maxCap)
    val maxRadiusY = (2f * sqrt(adjustedVarianceY) - 1f).coerceIn(0f, maxCap)

    var effect: RenderEffect? = null

    val clampedCurve = curve.coerceIn(0.05f, 20f)

    if (radiusX > 0f && maxRadiusX >= 0.5f) {
        val hShader = scope.obtainRuntimeShader("LMPGaussLoop_H_d$downScale", PROGRESSIVE_BLUR_SHADER).apply {
            setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
            setFloatUniform("in_step", 1f, 0f)
            setFloatUniform("in_maxRadius", maxRadiusX)
            setFloatUniform("in_gradAxis", ax, ay)
            setFloatUniform("in_gradBand", projFull, projZero)
            setFloatUniform("in_curve", clampedCurve)
            setFloatUniform("in_noise", 0f)
        }
        effect = runtimeShaderEffect(hShader, "child")
    }

    if (radiusY > 0f && maxRadiusY >= 0.5f) {
        val vShader = scope.obtainRuntimeShader("LMPGaussLoop_V_d$downScale", PROGRESSIVE_BLUR_SHADER).apply {
            setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
            setFloatUniform("in_step", 0f, 1f)
            setFloatUniform("in_maxRadius", maxRadiusY)
            setFloatUniform("in_gradAxis", ax, ay)
            setFloatUniform("in_gradBand", projFull, projZero)
            setFloatUniform("in_curve", clampedCurve)
            setFloatUniform("in_noise", 0f)
        }
        effect = effect?.chain(runtimeShaderEffect(vShader, "child"))
            ?: runtimeShaderEffect(vShader, "child")
    }

    return effect
}

/**
 * Converts radii + gradient into the [progressiveStackEffect] inputs: per-level box-compensated
 * sigmas in the downscaled layer's space and the band in downscaled, padded px. Zero radii are
 * valid — the levels collapse to identity while the pre/post chains keep riding the ramp;
 * returning null instead would poison the caller's supported flag.
 */
internal fun createProgressiveStackEffect(
    radiusX: Float,
    radiusY: Float,
    exp: Int,
    padding: Float,
    compW: Float,
    compH: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
    preEffect: RenderEffect?,
    postEffect: RenderEffect?,
    scope: BackdropEffectScopeImpl,
): RenderEffect? {
    val downScale = 1 shl exp
    val sigmaX = radiusX.coerceAtLeast(0f) * BLUR_RADIUS_TO_SIGMA
    val sigmaY = radiusY.coerceAtLeast(0f) * BLUR_RADIUS_TO_SIGMA

    val rad = angleDeg * (PI / 180.0)
    val ax = cos(rad).toFloat()
    val ay = sin(rad).toFloat()
    val projMin = (if (ax > 0f) 0f else ax * compW) + (if (ay > 0f) 0f else ay * compH)
    val projMax = (if (ax > 0f) ax * compW else 0f) + (if (ay > 0f) ay * compH else 0f)
    val span = projMax - projMin
    val padProj = padding * (ax + ay)
    val projFull = (projMin + startFraction * span + padProj) / downScale
    var projZero = (projMin + endFraction * span + padProj) / downScale
    if (abs(projZero - projFull) < 1e-3f) projZero = projFull + 1e-3f

    // Downscaled-space sigma supplying the variance the pyramid's box prefilter doesn't already
    // cover; 0 (skip the blur) when the prefilter alone reaches the level's target.
    fun levelSigma(sigma: Float, fraction: Float): Float {
        val target = sigma * fraction
        if (target <= 0f) return 0f
        val residual = (target * target - IMPLIED_BOX_VARIANCE[exp]) / (downScale * downScale)
        return if (residual > 0.01f) sqrt(residual) else 0f
    }

    return progressiveStackEffect(
        scope,
        levelSigma(sigmaX, PROGRESSIVE_LEVEL_FRACTION_0),
        levelSigma(sigmaY, PROGRESSIVE_LEVEL_FRACTION_0),
        levelSigma(sigmaX, PROGRESSIVE_LEVEL_FRACTION_1),
        levelSigma(sigmaY, PROGRESSIVE_LEVEL_FRACTION_1),
        levelSigma(sigmaX, PROGRESSIVE_LEVEL_FRACTION_2),
        levelSigma(sigmaY, PROGRESSIVE_LEVEL_FRACTION_2),
        ax,
        ay,
        projFull,
        projZero,
        curve.coerceIn(0.05f, 20f),
        preEffect,
        postEffect,
    )
}

/**
 * Mask keeping only the ramp's clear-end cross-fade, `clamp(3·raw − 2, 0, 1)`
 * (via [PROGRESSIVE_LEVEL_MASK_SHADER] with `in_level = −2`, `in_slope = −3`): applied to the
 * full-resolution sharp layer drawn over the upscaled level stack, it reproduces the composite's
 * final `mix(blur2, clear, …)` segment with a genuinely sharp end. Band in unpadded component px.
 * [originX]/[originY] — the sharp layer's top-left in component space (it records only the band's
 * bbox, see [progressiveSharpBandBounds]) — is folded into the band so layer-local coords line up.
 */
internal fun createProgressiveSharpRampEffect(
    compW: Float,
    compH: Float,
    originX: Float,
    originY: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
    scope: BackdropEffectScopeImpl,
): RenderEffect {
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

    val shader = scope.obtainRuntimeShader("ProgSharpRamp", PROGRESSIVE_LEVEL_MASK_SHADER).apply {
        setFloatUniform("in_gradAxis", ax, ay)
        setFloatUniform("in_gradBand", projFull - originProj, projZero - originProj)
        setFloatUniform("in_curve", curve.coerceIn(0.05f, 20f))
        setFloatUniform("in_level", -2f)
        setFloatUniform("in_slope", -3f)
    }
    return runtimeShaderEffect(shader, "child")
}

/**
 * Component-space bounding box of the region where [createProgressiveSharpRampEffect]'s mask is
 * non-zero (`pow(smoothstep(raw), curve) > 2/3`, inverted analytically to a threshold line on the
 * gradient projection, clipped to the component rect with a 1px rounding margin). The sharp
 * overlay records and draws only this sub-rect instead of the whole component. Null = the band
 * lies entirely outside the component and the overlay can be skipped outright.
 */
internal fun progressiveSharpBandBounds(
    compW: Float,
    compH: Float,
    angleDeg: Float,
    startFraction: Float,
    endFraction: Float,
    curve: Float,
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

    // raw′ > 2/3 ⟺ smoothstep(raw) > (2/3)^(1/curve) ⟺ raw > rawThr, via the analytic
    // smoothstep inverse t = 0.5 − sin(asin(1 − 2y) / 3).
    val ssThr = (2.0 / 3.0).pow(1.0 / curve.coerceIn(0.05f, 20f))
    val rawThr = (0.5 - sin(asin(1.0 - 2.0 * ssThr) / 3.0)).toFloat()
    val pThr = projFull + rawThr * (projZero - projFull)
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

    val xs = floatArrayOf(0f, compW, compW, 0f)
    val ys = floatArrayOf(0f, 0f, compH, compH)
    for (i in 0..3) {
        val j = (i + 1) and 3
        val di = (xs[i] * ax + ys[i] * ay - pThr) * sign
        val dj = (xs[j] * ax + ys[j] * ay - pThr) * sign
        if (di >= 0f) include(xs[i], ys[i])
        if ((di < 0f) != (dj < 0f)) {
            val t = di / (di - dj)
            include(xs[i] + (xs[j] - xs[i]) * t, ys[i] + (ys[j] - ys[i]) * t)
        }
    }
    if (minX > maxX || minY > maxY) return null

    return Rect(
        (minX - 1f).coerceAtLeast(0f),
        (minY - 1f).coerceAtLeast(0f),
        (maxX + 1f).coerceAtMost(compW),
        (maxY + 1f).coerceAtMost(compH),
    )
}

/**
 * Implied box-prefilter variance (full-resolution px²) absorbed by the downsample at each level;
 * index = log2(downScale). The separable Gaussian only supplies the remaining variance:
 * `adjustedVariance = (σ² - impliedBox) / downScale²`. These values reproduce the original
 * branch/halving constants exactly, and are calibrated against the pop-free 4×→8× boundary.
 */
private val IMPLIED_BOX_VARIANCE = floatArrayOf(0f, 3.0265f, 7.5625f, 9.0f, 202.696f)

/** Downscale exponent (downScale = `1 shl exp`, i.e. 1/2/4/8/16) chosen for a given σ². */
internal fun downScaleExpFor(sigmaSquared: Float): Int = when {
    sigmaSquared >= 1945f -> 4
    sigmaSquared > 400f -> 3
    sigmaSquared >= 90.25f -> 2
    sigmaSquared >= 12.6f -> 1
    else -> 0
}

/** Box-compensated Gaussian variance (in downscaled px²) for [sigmaSquared] at downscale [exp]. */
internal fun adjustedVarianceForExp(sigmaSquared: Float, exp: Int): Float {
    val ds = (1 shl exp).toFloat()
    return ((sigmaSquared - IMPLIED_BOX_VARIANCE[exp]) / (ds * ds)).coerceAtLeast(0.1f)
}

/**
 * Bracket of two adjacent downscale levels to cross-fade between, plus a smoothstep [blend]
 * weight of the higher level. Outside any transition band [expLo] == [expHi] and [blend] is 0.
 */
internal data class DownScaleBlend(val expLo: Int, val expHi: Int, val blend: Float)

/** Sigma at each downscale boundary (= √ of the σ² thresholds in [downScaleExpFor]). */
private val BOUNDARY_SIGMA = floatArrayOf(3.5496478f, 9.5f, 20f, 44.10215f)

/** Half-width of each cross-fade transition band, as a fraction of the boundary radius/sigma. */
private const val BLEND_BAND_FRACTION = 0.12f

/**
 * Within ±[BLEND_BAND_FRACTION] of a downscale boundary, returns the two bracketing levels and a
 * smoothstep blend weight (0 at the band's lower edge → 1 at the upper edge); elsewhere returns a
 * single level with `blend == 0`. Cross-fading the two fully-rendered levels across the band turns
 * the otherwise-instant downscale switch into a continuous transition, hiding the "pop".
 */
internal fun computeDownScaleBlend(sigma: Float): DownScaleBlend {
    for (b in BOUNDARY_SIGMA.indices) {
        val lo = BOUNDARY_SIGMA[b] * (1f - BLEND_BAND_FRACTION)
        val hi = BOUNDARY_SIGMA[b] * (1f + BLEND_BAND_FRACTION)
        if (sigma > lo && sigma < hi) {
            val tRaw = ((sigma - lo) / (hi - lo)).coerceIn(0f, 1f)
            return DownScaleBlend(expLo = b, expHi = b + 1, blend = tRaw * tRaw * (3f - 2f * tRaw))
        }
    }
    val exp = downScaleExpFor(sigma * sigma)
    return DownScaleBlend(expLo = exp, expHi = exp, blend = 0f)
}

/**
 * Generates a 27-tap discrete kernel (-13..+13) from [variance], normalizes, then merges
 * adjacent pairs (0,1), (2,3), …, (12,13) via linear interpolation into [outOffsets] /
 * [outWeights] (writes the first [MAX_BLUR_TAPS] slots), and returns the actual tap count.
 *
 * Returns 0 (no taps produced) when [variance] is too small to contribute or when no pair
 * survives the weight threshold. Caller-provided scratch arrays let this run with zero
 * heap allocation across observe-driven invocations.
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

    var tapCount = 0

    // 3. Pair 0: merge center (offset 0) with offset 1.
    //    In symmetric sampling, the center pixel is sampled twice (at +ε and -ε),
    //    so its effective per-sample weight is halved.
    //    Combined offset = w[1] / (w[0]/2 + w[1])
    val halfCenter = rawScratch[0] * 0.5
    val w1 = rawScratch[1]
    if (halfCenter + w1 > 1e-6) {
        outOffsets[0] = (w1 / (halfCenter + w1)).toFloat()
        tapCount = 1
    }

    // 4. Pairs 1-6: merge (2,3), (4,5), ..., (12,13)
    var i = 2
    while (i < 14 && tapCount < MAX_BLUR_TAPS) {
        val wa = rawScratch[i]
        val wb = if (i + 1 < 14) rawScratch[i + 1] else 0.0
        val combined = wa + wb
        if (combined < WEIGHT_THRESHOLD) break
        outOffsets[tapCount] = ((wa * i + wb * (i + 1)) / combined).toFloat()
        outWeights[tapCount] = combined.toFloat()
        tapCount++
        i += 2
    }

    // 5. Center weight = residual ensuring sum(weights) = 0.5
    //    (each weight is counted twice due to symmetric sampling, so 2×0.5 = 1.0)
    var pairWeightSum = 0f
    for (j in 1 until tapCount) pairWeightSum += outWeights[j]
    outWeights[0] = (0.5f - pairWeightSum).coerceAtLeast(0f)

    // 6. Validity check: zero out weights outside (0, 1)
    for (j in 0 until tapCount) {
        if (outWeights[j] <= 0f || outWeights[j] >= 1f) {
            outWeights[j] = 0f
        }
    }

    return tapCount
}
