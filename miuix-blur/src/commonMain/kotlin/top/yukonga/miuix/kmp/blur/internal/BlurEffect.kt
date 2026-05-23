// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import kotlin.math.exp

/** Conversion factor from blur radius in pixels to Blur sigma. */
internal const val BLUR_RADIUS_TO_SIGMA = 0.45f

/** Max kernel reach in downsampled pixels — outermost pair reaches ~offset 12.5, rounded up. */
internal const val BLUR_KERNEL_REACH = 14

private const val WEIGHT_THRESHOLD = 0.002

internal data class BlurResult(
    val renderEffect: RenderEffect,
    val downscaleFactor: Int,
)

/** Creates a separable Blur [RenderEffect] with independent horizontal / vertical radii. */
internal fun createBlurEffect(
    radiusX: Float,
    radiusY: Float,
    size: Size,
    scope: BackdropEffectScopeImpl,
): BlurResult? {
    if (radiusX <= 0f && radiusY <= 0f) return null

    val sigmaX = radiusX * BLUR_RADIUS_TO_SIGMA
    val sigmaY = radiusY * BLUR_RADIUS_TO_SIGMA
    val (adjustedVarianceX, downScaleX) = computeDownScaleParams(sigmaX)
    val (adjustedVarianceY, downScaleY) = computeDownScaleParams(sigmaY)

    val downScale = maxOf(downScaleX, downScaleY)

    // Texture size uses the same integer arithmetic as drawBackdropLayer
    // to match the actual recording dimensions (size includes padding).
    val texW = (size.width.toInt() / downScale).coerceAtLeast(1).toFloat()
    val texH = (size.height.toInt() / downScale).coerceAtLeast(1).toFloat()

    val rawScratch = scope.blurRawWeights
    val paramOffsets = scope.blurParamOffsets
    val paramWeights = scope.blurParamWeights

    var effect: RenderEffect? = null

    // H / V use distinct cache keys so each pass holds its own uniform state.
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
                "LMGauss${n}_H",
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
                "LMGauss${n}_V",
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

    return effect?.let { BlurResult(renderEffect = it, downscaleFactor = downScale) }
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
    @Suppress("EmptyRange")
    for (j in 1 until tapCount) pairWeightSum += outWeights[j]
    outWeights[0] = (0.5f - pairWeightSum).coerceAtLeast(0f)

    // 6. Validity check: zero out weights outside (0, 1)
    @Suppress("EmptyRange")
    for (j in 0 until tapCount) {
        if (outWeights[j] <= 0f || outWeights[j] >= 1f) {
            outWeights[j] = 0f
        }
    }

    return tapCount
}
