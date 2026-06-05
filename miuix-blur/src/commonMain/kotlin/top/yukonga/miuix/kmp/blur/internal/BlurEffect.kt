// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import kotlin.math.exp

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
 * The shader cache keys carry [downScale] so two adjacent levels rendered in the same frame (the
 * cross-fade lo/hi passes) never share a Gaussian shader instance and corrupt each other's kernel.
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

    // H / V use distinct cache keys so each pass holds its own uniform state; the _d$downScale
    // suffix additionally isolates the cross-fade lo/hi levels from one another.
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
