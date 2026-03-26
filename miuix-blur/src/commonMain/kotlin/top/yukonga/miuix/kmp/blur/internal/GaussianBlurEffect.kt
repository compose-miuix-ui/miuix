// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.blur
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.runtimeShaderEffect
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sqrt

/**
 * Maximum number of tap pairs for the Gaussian blur shader.
 * Matches Xiaomi's libhwui which supports up to 7 shader variants (1-7 pairs).
 */
private const val MAX_TAPS = 7

/** Xiaomi's radius-to-sigma conversion coefficient for Gaussian blur. */
private const val RADIUS_TO_SIGMA = 0.45f

/** Minimum combined weight for a merged pair to be considered valid. */
private const val WEIGHT_THRESHOLD = 0.002

/**
 * Applies LM-style separable Gaussian blur using Xiaomi's algorithm:
 * 1. Compute adaptive downscale factor and adjusted variance from sigma
 * 2. Horizontal pass with N-tap symmetric sampling (up to 7 pairs)
 * 3. Vertical pass with N-tap symmetric sampling (up to 7 pairs)
 *
 * Falls back to standard Compose [blur] when RuntimeShader is unavailable.
 *
 * @param radius The blur radius in pixels.
 */
internal fun BackdropEffectScope.gaussianBlur(radius: Float) {
    if (!isRuntimeShaderSupported()) {
        blur(radius)
        return
    }
    if (radius <= 0f) return

    val sigma = radius * RADIUS_TO_SIGMA
    val (adjustedVariance, downScale) = computeDownScaleParams(sigma)
    val params = computeGaussianParams(adjustedVariance)
    if (params.tapCount == 0) return

    // Extend capture area so the blur has real background content at edges.
    // Align padding to the downscale factor to avoid fractional offset when
    // converting between original and downsampled pixel space.
    val rawPadding = radius.toInt()
    val alignedPadding = if (downScale > 1) {
        ((rawPadding + downScale - 1) / downScale) * downScale
    } else {
        rawPadding
    }
    if (alignedPadding > padding) {
        padding = alignedPadding.toFloat()
    }

    // Set adaptive downsampling — effects execute on reduced-area texture
    downscaleFactor = downScale

    // Texture size must use the same integer arithmetic as drawBackdropLayer
    // to avoid precision mismatch where the shader samples beyond the texture bounds.
    val fullWidth = size.width.toInt() + alignedPadding * 2
    val fullHeight = size.height.toInt() + alignedPadding * 2
    val texW = (if (downScale > 1) (fullWidth / downScale).coerceAtLeast(1) else fullWidth).toFloat()
    val texH = (if (downScale > 1) (fullHeight / downScale).coerceAtLeast(1) else fullHeight).toFloat()

    // Horizontal pass
    runtimeShaderEffect(
        key = "LMGaussH",
        shaderString = LM_GAUSSIAN_BLUR_SHADER,
        uniformShaderName = "child",
    ) {
        val offsets = FloatArray(MAX_TAPS * 2)
        for (i in 0 until params.tapCount) {
            offsets[i] = params.offsets[i]
            offsets[i + MAX_TAPS] = 0f
        }
        setFloatUniform("in_blurOffset", offsets)
        setFloatUniform("in_blurWeight", params.weights.copyOf(MAX_TAPS))
        setFloatUniform("in_texSize", texW, texH)
    }

    // Vertical pass
    runtimeShaderEffect(
        key = "LMGaussV",
        shaderString = LM_GAUSSIAN_BLUR_SHADER,
        uniformShaderName = "child",
    ) {
        val offsets = FloatArray(MAX_TAPS * 2)
        for (i in 0 until params.tapCount) {
            offsets[i] = 0f
            offsets[i + MAX_TAPS] = params.offsets[i]
        }
        setFloatUniform("in_blurOffset", offsets)
        setFloatUniform("in_blurWeight", params.weights.copyOf(MAX_TAPS))
        setFloatUniform("in_texSize", texW, texH)
    }
}

/**
 * Precomputed Gaussian blur parameters: tap offsets and weights.
 */
internal class GaussianParams(
    val offsets: FloatArray,
    val weights: FloatArray,
    val tapCount: Int,
) {
    companion object {
        val EMPTY = GaussianParams(FloatArray(0), FloatArray(0), 0)
    }
}

/**
 * Result of adaptive downscale computation.
 *
 * @param adjustedVariance Gaussian variance adjusted for the downsampled space.
 * @param downScale Downsampling factor (1, 2, 4, 8, or 16).
 */
internal data class DownScaleParams(val adjustedVariance: Float, val downScale: Int)

/**
 * Computes the adaptive downscale factor and adjusted Gaussian variance
 * from the input sigma, following Xiaomi's `getSampleCountFromSigma` algorithm.
 *
 * The downscale factor is chosen based on sigma² thresholds to balance
 * performance and quality. The variance is adjusted with a piecewise-linear
 * transform that compensates for the box-filter pre-filtering during downsampling.
 *
 * @param sigma The Gaussian sigma (standard deviation) in original pixel space.
 * @return [DownScaleParams] with adjusted variance and downscale factor.
 */
internal fun computeDownScaleParams(sigma: Float): DownScaleParams {
    val sigmaSquared = sigma * sigma
    var adjustedVariance: Float
    var downScaleExp: Int

    when {
        sigmaSquared > 400f -> {
            // Very large blur: 8x base downscale
            adjustedVariance = 0.015625f * sigmaSquared - 0.140625f
            downScaleExp = 3
        }

        sigmaSquared >= 90.25f -> {
            // Medium-large blur: 4x base downscale
            adjustedVariance = 0.0625f * sigmaSquared - 0.47265625f
            downScaleExp = 2
        }

        else -> {
            // Small blur: no base downscale
            adjustedVariance = sigmaSquared
            downScaleExp = 0
        }
    }

    // If adjusted variance is still too large, halve the scale again
    val threshold = if (sigmaSquared < 100f) 12.6f else 30.25f
    if (adjustedVariance >= threshold) {
        downScaleExp++
        adjustedVariance = adjustedVariance * 0.25f - 0.756625f
    }

    return DownScaleParams(
        adjustedVariance = adjustedVariance.coerceAtLeast(0.1f),
        downScale = (1 shl downScaleExp).coerceAtLeast(1),
    )
}

/**
 * Computes Gaussian blur sampling parameters from variance, following Xiaomi's
 * `getRadiusFromSigma` algorithm.
 *
 * Generates a discrete Gaussian kernel over 27 taps (-13..+13), normalizes it,
 * then merges adjacent weight pairs using linear interpolation to reduce the
 * number of texture fetches while maintaining accuracy.
 *
 * The first pair merges center (offset 0) with offset 1 — the shader samples
 * symmetrically at ±offset, so center weight is automatically captured.
 * Subsequent pairs merge (2,3), (4,5), ..., (12,13).
 *
 * @param variance The Gaussian variance (sigma²) in downsampled pixel space.
 * @return [GaussianParams] with up to [MAX_TAPS] offset/weight pairs.
 */
internal fun computeGaussianParams(variance: Float): GaussianParams {
    if (variance <= 0.25f) return GaussianParams.EMPTY

    val v = variance.toDouble()

    // 1. Generate raw Gaussian weights for offsets 0..13
    val coeff = 1.0 / sqrt(2.0 * PI * v)
    val raw = DoubleArray(14) { i ->
        coeff * exp(-0.5 * i.toDouble() * i.toDouble() / v)
    }

    // 2. Normalize so all weights sum to 1.0 (accounting for symmetry)
    var total = raw[0]
    for (i in 1..13) total += raw[i] * 2.0
    for (i in 0..13) raw[i] /= total

    val offsets = FloatArray(MAX_TAPS)
    val weights = FloatArray(MAX_TAPS)
    var tapCount = 0

    // 3. Pair 0: merge center (offset 0) with offset 1.
    //    In symmetric sampling, the center pixel is sampled twice (at +ε and -ε),
    //    so its effective per-sample weight is halved.
    //    Combined offset = w[1] / (w[0]/2 + w[1])
    val halfCenter = raw[0] * 0.5
    val w1 = raw[1]
    if (halfCenter + w1 > 1e-6) {
        offsets[0] = (w1 / (halfCenter + w1)).toFloat()
        tapCount = 1
    }

    // 4. Pairs 1-6: merge (2,3), (4,5), ..., (12,13)
    var i = 2
    while (i < 14 && tapCount < MAX_TAPS) {
        val wa = raw[i]
        val wb = if (i + 1 < 14) raw[i + 1] else 0.0
        val combined = wa + wb
        if (combined < WEIGHT_THRESHOLD) break
        offsets[tapCount] = ((wa * i + wb * (i + 1)) / combined).toFloat()
        weights[tapCount] = combined.toFloat()
        tapCount++
        i += 2
    }

    // 5. Center weight = residual ensuring sum(weights) = 0.5
    //    (each weight is counted twice due to symmetric sampling, so 2×0.5 = 1.0)
    var pairWeightSum = 0f
    for (j in 1 until tapCount) pairWeightSum += weights[j]
    weights[0] = (0.5f - pairWeightSum).coerceAtLeast(0f)

    // 6. Validity check: zero out weights outside (0, 1)
    for (j in 0 until tapCount) {
        if (weights[j] <= 0f || weights[j] >= 1f) {
            weights[j] = 0f
        }
    }

    return GaussianParams(
        offsets = offsets,
        weights = weights,
        tapCount = tapCount,
    )
}
