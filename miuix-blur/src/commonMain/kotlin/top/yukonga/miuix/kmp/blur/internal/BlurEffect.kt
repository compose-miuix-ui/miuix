// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.buildRampBlendColorsEffect
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToInt
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
 * Gaussian shader-cache keys, `[tapCount][bucket]`: the bucket separates levels live in the same
 * frame without giving every level value its own shader instance.
 */
private val BLUR_H_KEYS: Array<Array<String>> = Array(MAX_BLUR_TAPS + 1) { n ->
    Array(LEVEL_BUCKETS) { b -> "LMGauss${n}_H_b$b" }
}
private val BLUR_V_KEYS: Array<Array<String>> = Array(MAX_BLUR_TAPS + 1) { n ->
    Array(LEVEL_BUCKETS) { b -> "LMGauss${n}_V_b$b" }
}

/** Cache-key bucket for a level: log2(scale) in [BUCKETS_PER_OCTAVE]-ths, i.e. the grid index. */
internal fun levelBucket(scale: Float): Int = ((ln(scale.coerceIn(1f, 16f)) / LN2) * BUCKETS_PER_OCTAVE).roundToInt().coerceIn(0, LEVEL_BUCKETS - 1)

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
    val texW = (size.width / downScale).coerceAtLeast(1f)
    val texH = (size.height / downScale).coerceAtLeast(1f)

    val rawScratch = scope.blurRawWeights
    val paramOffsets = scope.blurParamOffsets
    val paramWeights = scope.blurParamWeights

    val bucket = levelBucket(downScale)

    var effect: RenderEffect? = null

    // H / V use distinct cache keys so each pass builds from its own shader instance; the
    // _d$downScale suffix additionally isolates one downscale level from another.
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
                BLUR_H_KEYS[n][bucket],
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
                BLUR_V_KEYS[n][bucket],
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
 * Builds the separable **progressive** Blur [RenderEffect]: radius ramps to zero along [angleDeg] at
 * [downScale]. The gradient band is converted here into the downscaled, padded layer's space; [size]
 * is that padded size, [compW]/[compH] the unpadded component in full-resolution px.
 */
internal fun createProgressiveBlurEffect(
    radiusX: Float,
    radiusY: Float,
    downScale: Float,
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

    val texW = (size.width / downScale).coerceAtLeast(1f)
    val texH = (size.height / downScale).coerceAtLeast(1f)

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
        val tap = progressiveLoopTapBound(maxRadiusX)
        val hShader = scope.obtainRuntimeShader(
            progressiveLoopShaderKey(axis = 0, downScale = downScale, tap = tap),
            progressiveBlurShaderForTap(tap),
        ).apply {
            setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
            setFloatUniform("in_step", 1f, 0f)
            setFloatUniform("in_maxRadius", maxRadiusX)
            setFloatUniform("in_gradAxis", ax, ay)
            setFloatUniform("in_gradBand", projFull, projZero)
            setFloatUniform("in_curve", clampedCurve)
        }
        effect = runtimeShaderEffect(hShader, "child")
    }

    if (radiusY > 0f && maxRadiusY >= 0.5f) {
        val tap = progressiveLoopTapBound(maxRadiusY)
        val vShader = scope.obtainRuntimeShader(
            progressiveLoopShaderKey(axis = 1, downScale = downScale, tap = tap),
            progressiveBlurShaderForTap(tap),
        ).apply {
            setFloatUniform("in_maxCoord", texW - 0.5f, texH - 0.5f)
            setFloatUniform("in_step", 0f, 1f)
            setFloatUniform("in_maxRadius", maxRadiusY)
            setFloatUniform("in_gradAxis", ax, ay)
            setFloatUniform("in_gradBand", projFull, projZero)
            setFloatUniform("in_curve", clampedCurve)
        }
        effect = effect?.chain(runtimeShaderEffect(vShader, "child"))
            ?: runtimeShaderEffect(vShader, "child")
    }

    return effect
}

/**
 * Converts radii + gradient into [progressiveStackEffect] inputs: per-level box-compensated sigmas
 * in the downscaled layer's space, the band in downscaled padded px. Zero radii are valid (levels
 * collapse to identity, the pre/post chains keep riding the ramp); at `exp == 0` the stack binds its
 * clear end to the native source and the caller skips the sharp overlay.
 *
 * [postBlend] is the recorded blend-only post chain folded into one ramp-mixing pass
 * ([buildRampBlendColorsEffect]); [postEffect] is the general branch, and at most one is non-null.
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
    postBlend: BlurColors?,
    scope: BackdropEffectScopeImpl,
): RenderEffect? {
    val downScale = (1 shl exp).toFloat()
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

    // Downscaled-space sigma supplying the variance the downscale cascade's box prefilter doesn't
    // already cover; 0 (skip the blur) when the prefilter alone reaches the level's target.
    fun levelSigma(sigma: Float, fraction: Float): Float {
        val target = sigma * fraction
        if (target <= 0f) return 0f
        val residual = (target * target - impliedBoxVarianceForScale(downScale)) / (downScale * downScale)
        return if (residual > 0.01f) sqrt(residual) else 0f
    }

    val clampedCurve = curve.coerceIn(0.05f, 20f)
    val stack = progressiveStackEffect(
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
        clampedCurve,
        preEffect,
        postEffect,
        nativeSharpEnd = exp == 0,
    ) ?: return null
    return if (postBlend != null) {
        stack.chain(buildRampBlendColorsEffect(scope, postBlend, ax, ay, projFull, projZero, clampedCurve))
    } else {
        stack
    }
}

/**
 * Loop-shader radius (`2σ − 1`) of `blur2`'s effective Gaussian at [exp]: the larger of its target
 * sigma and the cascade's box floor, capped at the tap bound. The sharp overlay starts here so the
 * handoff is seamless.
 */
internal fun progressiveSharpLoopRadius(radius: Float, exp: Int): Float {
    val sigma = radius.coerceAtLeast(0f) * BLUR_RADIUS_TO_SIGMA * PROGRESSIVE_LEVEL_FRACTION_2
    val effSigma = sqrt(maxOf(sigma * sigma, impliedBoxVarianceForScale((1 shl exp).toFloat())))
    return (2f * effSigma - 1f).coerceIn(0f, PROGRESSIVE_BLUR_MAX_TAP.toFloat())
}

/** Linear-raw position of the sharp band's onset (`pow(smoothstep(raw), curve) = 2/3`). */
private fun progressiveSharpRawThreshold(curve: Float): Float {
    val ssThr = (2.0 / 3.0).pow(1.0 / curve.coerceIn(0.05f, 20f))
    // Analytic smoothstep inverse: t = 0.5 − sin(asin(1 − 2y) / 3).
    return (0.5 - sin(asin(1.0 - 2.0 * ssThr) / 3.0)).toFloat()
}

/**
 * Full-resolution sharp overlay: an optional variable loop blur (H then V) with the ramp's clear-end
 * mask (`clamp(3·raw − 2, 0, 1)`) folded into the last pass. The radius ramps from
 * [progressiveSharpLoopRadius] at the band's onset to zero at the clear end, so the handoff reads as
 * one continuous blur instead of sharp content alpha-faded over a still-blurred base.
 *
 * [originX]/[originY] place the band-only layer in component space (see
 * [progressiveSharpBandBounds]), [bandW]/[bandH] clamp its sampling, [exp] is the stack's exponent.
 */
internal fun createProgressiveSharpOverlayEffect(
    radiusX: Float,
    radiusY: Float,
    exp: Int,
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
    val clampedCurve = curve.coerceIn(0.05f, 20f)

    // The loop blur ramps across the sharp band only: full strength at the band's onset
    // (`pThr`, where the stack below is pure blur2), zero at the ramp's clear end.
    val pThr = projFull + progressiveSharpRawThreshold(curve) * (projZero - projFull)
    val loopFull = pThr - originProj
    var loopZero = projZero - originProj
    if (abs(loopZero - loopFull) < 1e-3f) loopZero = loopFull + 1e-3f

    val maxRadiusX = progressiveSharpLoopRadius(radiusX, exp)
    val maxRadiusY = progressiveSharpLoopRadius(radiusY, exp)

    val hasH = maxRadiusX >= 0.5f
    val hasV = maxRadiusY >= 0.5f

    fun loopPass(axis: Int, maxRadius: Float, stepX: Float, stepY: Float, masked: Boolean): RenderEffect {
        val tap = progressiveLoopTapBound(maxRadius)
        val shader = scope.obtainRuntimeShader(
            progressiveSharpShaderKey(axis = axis, tap = tap, masked = masked),
            progressiveBlurShaderForTap(tap, masked),
        ).apply {
            setFloatUniform("in_maxCoord", bandW - 0.5f, bandH - 0.5f)
            setFloatUniform("in_step", stepX, stepY)
            setFloatUniform("in_maxRadius", maxRadius)
            setFloatUniform("in_gradAxis", ax, ay)
            setFloatUniform("in_gradBand", loopFull, loopZero)
            setFloatUniform("in_curve", 1f)
            if (masked) {
                setFloatUniform("in_maskBand", projFull - originProj, projZero - originProj)
                setFloatUniform("in_maskCurve", clampedCurve)
            }
        }
        return runtimeShaderEffect(shader, "child")
    }

    // The ramp mask is folded into the last loop pass (one fewer full-resolution band pass);
    // only the no-blur case runs it standalone.
    return when {
        hasH && hasV -> loopPass(0, maxRadiusX, 1f, 0f, masked = false)
            .chain(loopPass(1, maxRadiusY, 0f, 1f, masked = true))

        hasH -> loopPass(0, maxRadiusX, 1f, 0f, masked = true)

        hasV -> loopPass(1, maxRadiusY, 0f, 1f, masked = true)

        else -> {
            val mask = scope.obtainRuntimeShader("ProgSharpRamp", PROGRESSIVE_LEVEL_MASK_SHADER).apply {
                setFloatUniform("in_gradAxis", ax, ay)
                setFloatUniform("in_gradBand", projFull - originProj, projZero - originProj)
                setFloatUniform("in_curve", clampedCurve)
                setFloatUniform("in_level", -2f)
                setFloatUniform("in_slope", -3f)
            }
            runtimeShaderEffect(mask, "child")
        }
    }
}

/**
 * Component-space bbox of the region where the overlay mask is non-zero (`smoothstep(raw)^curve >
 * 2/3`, inverted to a threshold line on the gradient, clipped with a [margin] for rounding and tap
 * reach). Only this sub-rect is recorded; null means the band misses the component.
 */
internal fun progressiveSharpBandBounds(
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

    // Mask > 0 ⟺ raw > rawThr; the mask's weight smoothstep keeps the zero crossing at the same
    // threshold (s(0) = 0), so only [margin] widens the box.
    val pThr = projFull + progressiveSharpRawThreshold(curve) * (projZero - projFull)
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
        (minX - margin).coerceAtLeast(0f),
        (minY - margin).coerceAtLeast(0f),
        (maxX + margin).coerceAtMost(compW),
        (maxY + margin).coerceAtMost(compH),
    )
}

/**
 * Box-prefilter variance (source px²) the downscale absorbs, anchored at 1/2/4/8/16 and interpolated
 * in log2 between them. The kernel supplies the rest: `adjusted = (σ² − implied) / level²`. Values
 * reproduce the original branch/halving constants, calibrated on the pop-free 4×→8× boundary.
 */
private val IMPLIED_BOX_VARIANCE = floatArrayOf(0f, 3.0265f, 7.5625f, 9.0f, 202.696f)

/** Downscale exponent (downScale = `1 shl exp`) of the power-of-two ladder used by the progressive stack. */
internal fun downScaleExpFor(sigmaSquared: Float): Int = when {
    sigmaSquared >= 1945f -> 4
    sigmaSquared > 400f -> 3
    sigmaSquared >= 90.25f -> 2
    sigmaSquared >= 12.6f -> 1
    else -> 0
}

/** Level for a blur of variance [sigmaSquared]: `σ / [MIN_LEVEL_SIGMA]`, clamped to 1..16. */
private fun levelScaleFor(sigmaSquared: Float): Float = (sqrt(sigmaSquared.coerceAtLeast(0f)) / MIN_LEVEL_SIGMA).coerceIn(1f, 16f)

/** Recording level for [sigmaSquared], snapped to the grid in [BUCKETS_PER_OCTAVE] (17 levels). */
internal fun quantizedLevelScale(sigmaSquared: Float): Float = 2f.pow(levelBucket(levelScaleFor(sigmaSquared)).toFloat() / BUCKETS_PER_OCTAVE)

/** Implied box-prefilter variance (source px²) at level [scale]. */
internal fun impliedBoxVarianceForScale(scale: Float): Float {
    val pos = (ln(scale.coerceIn(1f, 16f)) / LN2).coerceIn(0f, 4f)
    val index = pos.toInt().coerceAtMost(3)
    val t = pos - index
    return IMPLIED_BOX_VARIANCE[index] + (IMPLIED_BOX_VARIANCE[index + 1] - IMPLIED_BOX_VARIANCE[index]) * t
}

/** Box-compensated Gaussian variance (in level px²) for [sigmaSquared] at level [scale]. */
internal fun adjustedVarianceForScale(sigmaSquared: Float, scale: Float): Float = ((sigmaSquared - impliedBoxVarianceForScale(scale)) / (scale * scale)).coerceAtLeast(0.1f)

/** [adjustedVarianceForScale] at a ladder level (the progressive stack's route). */
internal fun adjustedVarianceForExp(sigmaSquared: Float, exp: Int): Float = adjustedVarianceForScale(sigmaSquared, (1 shl exp).toFloat())

/**
 * Generates a 27-tap discrete kernel (-13..+13) from [variance], normalizes it, and merges adjacent
 * pairs into [outOffsets]/[outWeights] (first [MAX_BLUR_TAPS] slots), returning the tap count.
 * Returns 0 when the variance is too small or no pair survives the weight threshold. Scratch arrays
 * keep the observe-driven path allocation-free.
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

    // Pair 0: the center is sampled twice (±ε), so its per-sample weight is halved.
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
