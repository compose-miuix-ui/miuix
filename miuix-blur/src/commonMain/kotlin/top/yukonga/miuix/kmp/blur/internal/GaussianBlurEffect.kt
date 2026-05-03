// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.RuntimeShaderCache
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sqrt

private const val RADIUS_TO_SIGMA = 0.45f

/** Max kernel reach in downsampled pixels — outermost pair reaches ~offset 12.5, rounded up. */
private const val KERNEL_REACH = 14

private const val WEIGHT_THRESHOLD = 0.002

internal data class GaussianBlurResult(
    val renderEffect: RenderEffect,
    val downscaleFactor: Int,
)

/** Creates a separable Gaussian blur [RenderEffect] with independent horizontal / vertical radii. */
internal fun createGaussianBlurEffect(
    radiusX: Float,
    radiusY: Float,
    size: Size,
    shaderCache: RuntimeShaderCache,
): GaussianBlurResult? {
    if (radiusX <= 0f && radiusY <= 0f) return null

    val sigmaX = radiusX * RADIUS_TO_SIGMA
    val sigmaY = radiusY * RADIUS_TO_SIGMA
    val (adjustedVarianceX, downScaleX) = computeDownScaleParams(sigmaX)
    val (adjustedVarianceY, downScaleY) = computeDownScaleParams(sigmaY)
    val paramsX = if (radiusX > 0f) computeGaussianParams(adjustedVarianceX) else null
    val paramsY = if (radiusY > 0f) computeGaussianParams(adjustedVarianceY) else null
    if (paramsX?.tapCount == 0 && paramsY?.tapCount == 0) return null

    val downScale = maxOf(downScaleX, downScaleY)

    // Texture size uses the same integer arithmetic as drawBackdropLayer
    // to match the actual recording dimensions (size includes padding).
    val texW = (size.width.toInt() / downScale).coerceAtLeast(1).toFloat()
    val texH = (size.height.toInt() / downScale).coerceAtLeast(1).toFloat()

    var effect: RenderEffect? = null

    // Horizontal pass — shader specialized to exact tap count
    if (paramsX != null && paramsX.tapCount > 0) {
        val n = paramsX.tapCount
        val hShader = shaderCache.obtainRuntimeShader(
            "LMGauss$n",
            buildGaussianBlurShader(n),
        ).apply {
            val offsets = FloatArray(n * 2)
            for (i in 0 until n) {
                offsets[i] = paramsX.offsets[i]
                offsets[i + n] = 0f
            }
            setFloatUniform("in_blurOffset", offsets)
            setFloatUniform("in_blurWeight", paramsX.weights.copyOf(n))
            setFloatUniform("in_texSize", texW, texH)
        }
        effect = runtimeShaderEffect(hShader, "child")
    }

    // Vertical pass — shader specialized to exact tap count
    if (paramsY != null && paramsY.tapCount > 0) {
        val n = paramsY.tapCount
        val vShader = shaderCache.obtainRuntimeShader(
            "LMGauss$n",
            buildGaussianBlurShader(n),
        ).apply {
            val offsets = FloatArray(n * 2)
            for (i in 0 until n) {
                offsets[i] = 0f
                offsets[i + n] = paramsY.offsets[i]
            }
            setFloatUniform("in_blurOffset", offsets)
            setFloatUniform("in_blurWeight", paramsY.weights.copyOf(n))
            setFloatUniform("in_texSize", texW, texH)
        }
        effect = effect?.chain(runtimeShaderEffect(vShader, "child"))
            ?: runtimeShaderEffect(vShader, "child")
    }

    return effect?.let { GaussianBlurResult(renderEffect = it, downscaleFactor = downScale) }
}

/**
 * Chains a separable Gaussian blur into the scope's [BackdropEffectScope.renderEffect],
 * adjusts [BackdropEffectScope.padding] to cover the kernel reach, and sets
 * [BackdropEffectScope.downscaleFactor]. Non-positive radii skip that axis.
 */
internal fun BackdropEffectScope.gaussianBlur(radiusX: Float, radiusY: Float) {
    if (!isRuntimeShaderSupported()) return

    // Pre-compute downscale factor to set padding before creating the effect.
    val sigmaMax = maxOf(radiusX, radiusY) * RADIUS_TO_SIGMA
    val sf = computeDownScaleParams(sigmaMax).downScale

    // Padding covers the blur kernel's maximum sampling reach in original
    // pixel space. This keeps recording dimensions stable across radius
    // changes within the same downscale level.
    val kernelPadding = (KERNEL_REACH * sf).toFloat()
    if (kernelPadding > padding) {
        padding = kernelPadding
    }

    val paddedSize = Size(size.width + padding * 2f, size.height + padding * 2f)
    val result = createGaussianBlurEffect(radiusX, radiusY, paddedSize, this) ?: return

    // Set adaptive downsampling — effects execute on reduced-area texture
    downscaleFactor = result.downscaleFactor
    renderEffect = renderEffect?.chain(result.renderEffect) ?: result.renderEffect
}

/** Registers a noise dither pass with the given [coefficient]. Non-positive values are ignored. */
internal fun BackdropEffectScope.noiseDither(coefficient: Float) {
    if (coefficient <= 0f) return
    noiseCoefficient = coefficient
}

/**
 * Merged Gaussian taps for one axis. Each pair combines adjacent discrete taps via
 * linear interpolation; the shader samples symmetrically at ±[offsets], so [tapCount]
 * pairs produce 2 × [tapCount] texture fetches.
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

internal data class DownScaleParams(val adjustedVariance: Float, val downScale: Int)

/**
 * Picks an adaptive [DownScaleParams.downScale] (1/2/4/8/16) from σ² and returns the
 * variance compensated for the box-filter pre-filtering applied during downsampling.
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
 * Builds [GaussianParams] from [variance]: generates a 27-tap discrete kernel (-13..+13),
 * normalizes, then merges adjacent pairs (0,1), (2,3), …, (12,13) via linear interpolation
 * to reduce texture fetches. Returns up to [MAX_BLUR_TAPS] pairs.
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

    val offsets = FloatArray(MAX_BLUR_TAPS)
    val weights = FloatArray(MAX_BLUR_TAPS)
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
    while (i < 14 && tapCount < MAX_BLUR_TAPS) {
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
    @Suppress("EmptyRange")
    for (j in 1 until tapCount) pairWeightSum += weights[j]
    weights[0] = (0.5f - pairWeightSum).coerceAtLeast(0f)

    // 6. Validity check: zero out weights outside (0, 1)
    @Suppress("EmptyRange")
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
