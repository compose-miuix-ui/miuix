// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

/** Builds a separable Blur shader with [tapCount] symmetric tap pairs (1..7). */
internal fun buildBlurShader(tapCount: Int): String {
    require(tapCount in 1..MAX_BLUR_TAPS)
    return """
    uniform shader child;
    uniform float2 in_blurOffset[$tapCount];
    uniform float in_blurWeight[$tapCount];
    uniform float in_centerWeight;
    uniform float2 in_maxCoord;

    half4 main(float2 xy) {
        half4 color = child.eval(clamp(xy, float2(0.5), in_maxCoord)) * half(in_centerWeight);
        for (int i = 0; i < $tapCount; i++) {
            float2 offset = in_blurOffset[i];
            float2 c1 = clamp(xy + offset, float2(0.5), in_maxCoord);
            float2 c2 = clamp(xy - offset, float2(0.5), in_maxCoord);
            color += (child.eval(c1) + child.eval(c2)) * half(in_blurWeight[i]);
        }
        if (color.a > 0.0039) {
            return half4(color.rgb / color.a, 1.0);
        }
        return color;
    }
"""
}

/** Maximum number of tap pairs for the Blur shader. */
internal const val MAX_BLUR_TAPS = 7

/** Pre-built per-tap-count shader sources; index 0 is unused. */
internal val BLUR_SHADER_BY_TAP: Array<String> = Array(MAX_BLUR_TAPS + 1) { i ->
    if (i == 0) "" else buildBlurShader(i)
}

/** Loop bound (± taps) for the progressive Blur shader; covers the max downscaled kernel radius. */
internal const val PROGRESSIVE_BLUR_MAX_TAP = 24

/**
 * Curve specialization index for [buildProgressiveBlurShader]:
 * `0` = linear (`curve == 1`), `1` = `sqrt` (`0.5`), `2` = `sqrt(sqrt)` (`0.25`), `3` = general `pow`.
 */
internal fun progressiveCurveKind(curve: Float): Int = when (curve) {
    1f -> 0
    0.5f -> 1
    0.25f -> 2
    else -> 3
}

/**
 * Builds the separable **progressive** Blur shader: each fragment derives its radius from the
 * two-point gradient and runs a 1D Gaussian up to it (`σ = radius·0.5 + 0.5`). Adjacent taps merge
 * into one bilinear fetch at their weight-averaged offset, sampled as symmetric ± pairs with the
 * center hoisted out. [maxTap] must be an even compile-time constant (AGSL).
 *
 * With [sharpBand], the gradient ramps across `[in_sharpThreshold, 1]` instead of `[0, 1]`. With
 * [sharpMask], the cross-fade weight is folded into the output and fragments below
 * [in_sharpThreshold] exit before sampling. [midDecay] tunes the falloff and fade window for the
 * 2× intermediate bridge tier.
 */
internal fun buildProgressiveBlurShader(
    maxTap: Int,
    curveKind: Int = 3,
    sharpBand: Boolean = false,
    sharpMask: Boolean = false,
    midDecay: Boolean = false,
    unpremul: Boolean = true,
    axis: Int = 0,
    inlineTint: Boolean = false,
): String {
    require(maxTap % 2 == 0) { "maxTap must be even for pair merging" }
    val maxCoordUniform = if (sharpBand) {
        """
    uniform float2 in_maxCoord;
"""
    } else {
        ""
    }
    val curveUniform = if (curveKind == 3) {
        """
    uniform float in_curve;
"""
    } else {
        ""
    }
    val curveExpr = when (curveKind) {
        0 -> ""
        1 -> "\n        smoothRaw = sqrt(smoothRaw);"
        2 -> "\n        smoothRaw = sqrt(sqrt(smoothRaw));"
        else -> "\n        smoothRaw = pow(smoothRaw, in_curve);"
    }
    val sharpUniforms = if (sharpBand) {
        """
    uniform float in_sharpThreshold;
    uniform float in_invSharpSpan;
"""
    } else {
        ""
    }
    val maskSlope = if (midDecay) "1.8181818" else "1.5384615"
    val radiusDecay = if (midDecay) {
        """
        float om = 1.0 - m;
        float radius = in_maxRadius * om * sqrt(om);"""
    } else {
        """
        float radius = in_maxRadius * (1.0 - m * m);"""
    }
    val bandRamp = if (sharpBand && sharpMask) {
        """
        float m = clamp((smoothRaw - in_sharpThreshold) * in_invSharpSpan, 0.0, 1.0);
        half mw = half(clamp(m * $maskSlope, 0.0, 1.0));
        mw = mw * mw * (half(3.0) - half(2.0) * mw);
        if (mw <= half(0.004)) {
            return half4(0.0);
        }$radiusDecay"""
    } else if (sharpBand) {
        """
        float m = clamp((smoothRaw - in_sharpThreshold) * in_invSharpSpan, 0.0, 1.0);$radiusDecay"""
    } else {
        """
        float radius = in_maxRadius * (1.0 - smoothRaw);"""
    }
    val centerCoord = if (sharpBand) "clamp(xy, float2(0.5), in_maxCoord)" else "xy"
    fun axisSampleCoord(sign: String): String = when {
        axis == 0 && sharpBand -> "float2(clamp(xy.x $sign o, 0.5, in_maxCoord.x), xy.y)"
        axis == 0 -> "float2(xy.x $sign o, xy.y)"
        sharpBand -> "float2(xy.x, clamp(xy.y $sign o, 0.5, in_maxCoord.y))"
        else -> "float2(xy.x, xy.y $sign o)"
    }
    val tintUniform = if (inlineTint) "\n    uniform float4 in_tintColor;" else ""
    val maskSuffix = if (sharpMask) " * mw" else ""
    fun finishExpr(varName: String, indent: String): String = when {
        inlineTint ->
            """
${indent}half4 src = ($varName.a > 0.0039 && $varName.a < 0.996) ? half4($varName.rgb / $varName.a, 1.0) : $varName;
${indent}float rwF = 1.0 - smoothRaw;
${indent}half rw = half(rwF * rwF * (3.0 - 2.0 * rwF));
${indent}if (rw > half(0.001)) {
$indent    half4 tint = half4(in_tintColor);
$indent    half4 tinted = tint + (half(1.0) - tint.a) * src;
$indent    return mix(src, tinted, rw);
$indent}
${indent}return src;"""

        unpremul ->
            """
${indent}if ($varName.a > 0.0039 && $varName.a < 0.996) {
$indent    return half4($varName.rgb / $varName.a, 1.0)$maskSuffix;
$indent}
${indent}return $varName$maskSuffix;"""

        else -> "\n${indent}return $varName$maskSuffix;"
    }
    val earlyReturn = if (unpremul || inlineTint) {
        "\n            half4 s = child.eval($centerCoord);${finishExpr("s", "            ")}"
    } else {
        "\n            return child.eval($centerCoord)$maskSuffix;"
    }
    val finalReturn = finishExpr("color", "        ")
    return """
    uniform shader child;$maxCoordUniform
    uniform float in_maxRadius;
    uniform float2 in_gradVec;
    uniform float in_gradOffset;$tintUniform
$curveUniform$sharpUniforms
    half4 main(float2 xy) {
        float raw = clamp(dot(xy, in_gradVec) + in_gradOffset, 0.0, 1.0);
        float smoothRaw = raw * raw * (3.0 - 2.0 * raw);$curveExpr$bandRamp
        if (radius <= 0.004) {$earlyReturn
        }
        float sigma = radius * 0.5 + 0.5;
        float posNegInv2s2Ln2 = -0.7213475 / (sigma * sigma);
        float q = exp2(posNegInv2s2Ln2);
        float q2 = q * q;
        float gStep = q;
        float g = 1.0;
        float r1 = radius + 1.0;
        half4 color = child.eval($centerCoord);
        half wAcc = half(0.0);
        float a = 1.0;
        for (int j = 0; j < ${maxTap / 2}; j++) {
            half ra = half(clamp(r1 - a, 0.0, 1.0));
            if (ra <= half(0.001)) break;
            g *= gStep;
            gStep *= q2;
            half wa = ra * half(g);
            half rb = half(clamp(r1 - (a + 1.0), 0.0, 1.0));
            g *= gStep;
            gStep *= q2;
            half wb = rb * half(g);
            half wsum = wa + wb;
            float o = a + float(wb / wsum);
            color += (child.eval(${axisSampleCoord("+")}) +
                child.eval(${axisSampleCoord("-")})) * wsum;
            wAcc += wsum;
            a += 2.0;
        }
        color = color / (half(1.0) + half(2.0) * wAcc);$finalReturn
    }
"""
}

// 6 tap bounds (4..24 step 4) × 4 curveKinds × 7 variants × 2 axes = 336 cached shader strings.
private val PROGRESSIVE_SHADER_CACHE = arrayOfNulls<String>(6 * 4 * 7 * 2)

/** The progressive Blur shader source for [tapBound], [curveKind], [axis], and band/unpremul flags. */
internal fun progressiveBlurShaderForTap(
    tapBound: Int,
    curveKind: Int = 3,
    sharpBand: Boolean = false,
    sharpMask: Boolean = false,
    midDecay: Boolean = false,
    unpremul: Boolean = true,
    axis: Int = 0,
    inlineTint: Boolean = false,
): String {
    require(!sharpBand || !inlineTint) { "inlineTint cannot be combined with sharpBand" }
    val tapIdx = ((tapBound / 4) - 1).coerceIn(0, 5)
    val variant = when {
        inlineTint -> 6
        sharpBand && midDecay && sharpMask -> 5
        sharpBand && midDecay -> 4
        sharpBand && sharpMask -> 3
        sharpBand -> 2
        unpremul -> 1
        else -> 0
    }
    val slot = ((tapIdx * 4 + curveKind) * 7 + variant) * 2 + (axis and 1)
    return PROGRESSIVE_SHADER_CACHE[slot] ?: buildProgressiveBlurShader(
        maxTap = tapBound,
        curveKind = curveKind,
        sharpBand = sharpBand,
        sharpMask = sharpMask,
        midDecay = midDecay,
        unpremul = unpremul,
        axis = axis,
        inlineTint = inlineTint,
    ).also { PROGRESSIVE_SHADER_CACHE[slot] = it }
}

/**
 * Compile-time loop bound for [maxRadius]: the next multiple of 4, capped at
 * [PROGRESSIVE_BLUR_MAX_TAP].
 */
internal fun progressiveLoopTapBound(maxRadius: Float): Int {
    val needed = kotlin.math.ceil(maxRadius).toInt()
    return (((needed + 3) / 4) * 4).coerceIn(4, PROGRESSIVE_BLUR_MAX_TAP)
}

private val PROGRESSIVE_LOOP_KEYS: Array<Array<Array<Array<Array<String>>>>> = Array(2) { axis ->
    Array(3) { unpremulMode ->
        Array(4) { curveKind ->
            Array(LEVEL_BUCKETS) { bucket ->
                Array(6) { tapIdx ->
                    val tap = (tapIdx + 1) * 4
                    "LMPGL_${axis}_${unpremulMode}_c${curveKind}_b${bucket}_t$tap"
                }
            }
        }
    }
}

private val PROGRESSIVE_SHARP_KEYS: Array<Array<Array<Array<Array<String>>>>> = Array(2) { mid ->
    Array(2) { axis ->
        Array(2) { masked ->
            Array(4) { curveKind ->
                Array(6) { tapIdx ->
                    val tap = (tapIdx + 1) * 4
                    "LMPSL_${mid}_${axis}_m${masked}_c${curveKind}_t$tap"
                }
            }
        }
    }
}

internal fun progressiveLoopShaderKey(
    axis: Int,
    downScale: Float,
    tap: Int,
    curveKind: Int,
    unpremul: Boolean = true,
    inlineTint: Boolean = false,
): String {
    val mode = if (inlineTint) {
        2
    } else if (unpremul) {
        1
    } else {
        0
    }
    return PROGRESSIVE_LOOP_KEYS[axis][mode][curveKind][levelBucket(downScale)][((tap / 4) - 1).coerceIn(0, 5)]
}

internal fun progressiveSharpShaderKey(
    axis: Int,
    tap: Int,
    curveKind: Int,
    masked: Boolean,
    midDecay: Boolean = false,
): String = PROGRESSIVE_SHARP_KEYS[if (midDecay) 1 else 0][axis][if (masked) 1 else 0][curveKind][((tap / 4) - 1).coerceIn(0, 5)]

/**
 * Single-pass 1-tap alpha-ramp mask for the `1×` clear-end overlay when the underlying `2×`
 * layer is already near-sharp (`σ_1x <= ~3.2px`), avoiding 2 full-res blur passes.
 */
internal fun progressiveSharpMaskShader(curveKind: Int): String = SHARP_MASK_SHADERS[curveKind]
    ?: buildProgressiveSharpMaskShader(curveKind).also { SHARP_MASK_SHADERS[curveKind] = it }

private val SHARP_MASK_SHADERS = arrayOfNulls<String>(4)

private val SHARP_MASK_KEYS = arrayOf(
    "LMPSM_c0",
    "LMPSM_c1",
    "LMPSM_c2",
    "LMPSM_c3",
)

internal fun progressiveSharpMaskShaderKey(curveKind: Int): String = SHARP_MASK_KEYS[curveKind]

private fun buildProgressiveSharpMaskShader(curveKind: Int): String {
    val curveUniform = if (curveKind == 3) "\n    uniform float in_curve;" else ""
    val curveExpr = when (curveKind) {
        0 -> ""
        1 -> "\n        smoothRaw = sqrt(smoothRaw);"
        2 -> "\n        smoothRaw = sqrt(sqrt(smoothRaw));"
        else -> "\n        smoothRaw = pow(smoothRaw, in_curve);"
    }
    return """
    uniform shader child;
    uniform float2 in_gradVec;
    uniform float in_gradOffset;$curveUniform
    uniform float in_sharpThreshold;
    uniform float in_invSharpSpan;

    half4 main(float2 xy) {
        float raw = clamp(dot(xy, in_gradVec) + in_gradOffset, 0.0, 1.0);
        float smoothRaw = raw * raw * (3.0 - 2.0 * raw);$curveExpr
        float m = clamp((smoothRaw - in_sharpThreshold) * in_invSharpSpan, 0.0, 1.0);
        half mw = half(clamp(m * 1.5384615, 0.0, 1.0));
        mw = mw * mw * (half(3.0) - half(2.0) * mw);
        if (mw <= half(0.004)) {
            return half4(0.0);
        }
        half4 s = child.eval(xy);
        if (s.a > 0.0039 && s.a < 0.996) {
            return half4(s.rgb / s.a, 1.0) * mw;
        }
        return s * mw;
    }
"""
}

/**
 * Brightness / contrast / saturation adjustment.
 *
 * Brightness is applied in approximate linear (gamma 2.0) space to avoid sRGB hue shift;
 * contrast and saturation are applied in sRGB space.
 *
 * Uniforms: `in_brightness ∈ [-1, +1]`, `in_contrast ∈ [0, +∞)`, `in_saturation ∈ [0, +∞)`.
 */
internal const val COLOR_CONTROLS_SHADER = """
    uniform shader child;
    uniform float in_brightness;
    uniform float in_contrast;
    uniform float in_saturation;

    half4 main(float2 xy) {
        half4 src = child.eval(xy);
        half a = src.a;
        if (a < 0.001) return src;

        half3 c = src.rgb / a;

        if (in_brightness != 0.0) {
            c = max(c, half3(0.0));
            c = c * c;
            if (in_brightness > 0.0) {
                c = mix(c, half3(1.0), half(in_brightness));
            } else {
                c = c * half(1.0 + in_brightness);
            }
            c = sqrt(c);
        }

        if (in_contrast != 1.0) {
            c = (c - 0.5) * half(in_contrast) + 0.5;
        }

        if (in_saturation != 1.0) {
            half lum = dot(c, half3(0.2126, 0.7152, 0.0722));
            c = mix(half3(lum), c, half(in_saturation));
        }

        c = clamp(c, half3(0.0), half3(1.0));
        return half4(c * a, a);
    }
"""

/** Noise dithering shader — 3-channel sin-free hash anti-banding. */
internal const val NOISE_DITHER_SHADER = """
    uniform shader child;
    uniform float noise_coeff;

    half4 main(float2 xy) {
        half4 color = child.eval(xy);
        if (color.a <= 0.001) return color;
        float3 p3 = fract(float3(xy.xyx) * float3(0.1031, 0.1030, 0.0973));
        p3 += dot(p3, p3.yxz + 33.33);
        float3 n = (fract((p3.xxy + p3.yzz) * p3.zyx) - 0.5) * noise_coeff * float(color.a);
        color.rg += half2(n.x);
        color.rb += half2(n.y);
        color.gb += half2(n.z);
        color.rgb = clamp(color.rgb, half3(0.0), half3(color.a));
        return color;
    }
"""

/** 2× downsample — 4-tap ±0.25 dual-filter, 9-tap tent footprint. */
internal const val DOWNSAMPLE_2X_SHADER = """
    uniform shader child;
    uniform float2 maxCoord;
    half4 main(float2 xy) {
        float2 minCoord = float2(0.5);
        half4 color = half4(0);
        color += child.eval(clamp(xy + float2(-0.25, -0.25), minCoord, maxCoord));
        color += child.eval(clamp(xy + float2( 0.25, -0.25), minCoord, maxCoord));
        color += child.eval(clamp(xy + float2(-0.25,  0.25), minCoord, maxCoord));
        color += child.eval(clamp(xy + float2( 0.25,  0.25), minCoord, maxCoord));
        return color * 0.25;
    }
"""

/** 4× downsample — sample every texel in the 4×4 footprint before decimation. */
internal const val DOWNSAMPLE_4X_SHADER = """
    uniform shader child;
    uniform float2 maxCoord;
    half4 main(float2 xy) {
        float2 minCoord = float2(0.5);
        half4 color = half4(0);
        color += child.eval(clamp(xy + float2(-1.0, -1.0), minCoord, maxCoord));
        color += child.eval(clamp(xy + float2( 1.0, -1.0), minCoord, maxCoord));
        color += child.eval(clamp(xy + float2(-1.0,  1.0), minCoord, maxCoord));
        color += child.eval(clamp(xy + float2( 1.0,  1.0), minCoord, maxCoord));
        return color * 0.25;
    }
"""

/**
 * Builds the multi-layer blend mode shader.
 *
 * - `includeExtended = false` (all modes ≤ 28): only the Porter-Duff / separable / non-separable HSL
 *   functions, the `doBlend` dispatch and a flat `main`. Dropping the Lab helpers and the
 *   `getBlendModeColor` dispatch shrinks the program and frees the register budget the driver would
 *   reserve for the longest path — this is the common case, and keeping it lean lifts occupancy on
 *   Adreno / Mali.
 * - `includeExtended = true` (any mode > 28): the full set.
 * - `rampMix = true`: the progressive composite's folded post pass — one child eval, ramp-mixed over
 *   the untouched source instead of the SrcOver branch form that evaluates the stack twice.
 */
internal fun buildBlendModeShader(
    includeExtended: Boolean,
    rampMix: Boolean = false,
    curveKind: Int = 3,
): String = buildString {
    append(BLEND_MODE_SHADER_HEADER)
    if (includeExtended) append(BLEND_MODE_SHADER_EXTENDED_HELPERS)
    if (rampMix) append(buildBlendModeRampUniforms(curveKind))
    append(
        when {
            rampMix && includeExtended -> BLEND_MODE_SHADER_MAIN_EXTENDED_RAMP
            rampMix -> BLEND_MODE_SHADER_MAIN_STANDARD_RAMP
            includeExtended -> BLEND_MODE_SHADER_MAIN_EXTENDED
            else -> BLEND_MODE_SHADER_MAIN_STANDARD
        },
    )
}

internal val BLEND_MODE_SHADER_STANDARD: String = buildBlendModeShader(includeExtended = false)
internal val BLEND_MODE_SHADER_EXTENDED: String = buildBlendModeShader(includeExtended = true)

private val BLEND_MODE_RAMP_SHADERS = arrayOfNulls<String>(8)

internal fun rampBlendModeShader(includeExtended: Boolean, curveKind: Int): String {
    val slot = (if (includeExtended) 4 else 0) + curveKind
    return BLEND_MODE_RAMP_SHADERS[slot]
        ?: buildBlendModeShader(includeExtended = includeExtended, rampMix = true, curveKind = curveKind)
            .also { BLEND_MODE_RAMP_SHADERS[slot] = it }
}

/** Uniforms, standard mode helpers (0-28), and `doBlend` dispatch — shared by both families. */
private const val BLEND_MODE_SHADER_HEADER = """
    uniform shader child;

    uniform float layerCount;
    uniform float blendModes[8];
    uniform vec4 layerColors[8]; // premultiplied sRGB, set as flat float[32]

    // ================================================================
    // Standard blend modes (0-28) — premultiplied alpha
    // ================================================================

    const half kMinNormalHalf = 0.00006103515625;
    const float kGuardedDivideEpsilon = 0.00000001;

    half guarded_divide(half n, half d) {
        return n / (d + kGuardedDivideEpsilon);
    }

    half3 guarded_divide(half3 n, half d) {
        return n / (d + kGuardedDivideEpsilon);
    }

    // Porter-Duff modes
    half4 mi_blend_clear(half4 src, half4 dst) { return half4(0); }
    half4 mi_blend_src(half4 src, half4 dst) { return src; }
    half4 mi_blend_dst(half4 src, half4 dst) { return dst; }
    half4 mi_blend_src_over(half4 src, half4 dst) { return src + (1 - src.a)*dst; }
    half4 mi_blend_dst_over(half4 src, half4 dst) { return (1 - dst.a)*src + dst; }
    half4 mi_blend_src_in(half4 src, half4 dst) { return src*dst.a; }
    half4 mi_blend_dst_in(half4 src, half4 dst) { return dst*src.a; }
    half4 mi_blend_src_out(half4 src, half4 dst) { return (1 - dst.a)*src; }
    half4 mi_blend_dst_out(half4 src, half4 dst) { return (1 - src.a)*dst; }
    half4 mi_blend_src_atop(half4 src, half4 dst) { return dst.a*src + (1 - src.a)*dst; }
    half4 mi_blend_dst_atop(half4 src, half4 dst) { return (1 - dst.a)*src + src.a*dst; }
    half4 mi_blend_xor(half4 src, half4 dst) { return (1 - dst.a)*src + (1 - src.a)*dst; }
    half4 mi_blend_plus(half4 src, half4 dst) { return min(src + dst, 1); }
    half4 mi_blend_modulate(half4 src, half4 dst) { return src*dst; }

    // Separable blend modes
    half4 mi_blend_screen(half4 src, half4 dst) { return src + (1 - src)*dst; }

    half mi_blend_overlay_component(half2 s, half2 d) {
        return (2*d.x <= d.y) ? 2*s.x*d.x
                              : s.y*d.y - 2*(d.y - d.x)*(s.y - s.x);
    }

    half4 mi_blend_overlay(half4 src, half4 dst) {
        half4 result = half4(mi_blend_overlay_component(src.ra, dst.ra),
                            mi_blend_overlay_component(src.ga, dst.ga),
                            mi_blend_overlay_component(src.ba, dst.ba),
                            src.a + (1 - src.a)*dst.a);
        result.rgb += dst.rgb*(1 - src.a) + src.rgb*(1 - dst.a);
        return result;
    }

    half4 mi_blend_darken(half4 src, half4 dst) {
        half4 a = mi_blend_src_over(src, dst);
        half3 b = (1 - dst.a) * src.rgb + dst.rgb;
        a.rgb = min(a.rgb, b.rgb);
        return a;
    }

    half4 mi_blend_lighten(half4 src, half4 dst) {
        half4 result = mi_blend_src_over(src, dst);
        result.rgb = max(result.rgb, (1 - dst.a)*src.rgb + dst.rgb);
        return result;
    }

    half color_dodge_component(half2 s, half2 d) {
        half delta = min(d.y, abs(s.y - s.x) >= kMinNormalHalf
                                        ? guarded_divide(d.x * s.y, s.y - s.x)
                                        : d.y);
        return delta*s.y + s.x*(1 - d.y) + d.x*(1 - s.y);
    }

    half4 mi_blend_color_dodge(half4 src, half4 dst) {
        return half4(color_dodge_component(src.ra, dst.ra),
                    color_dodge_component(src.ga, dst.ga),
                    color_dodge_component(src.ba, dst.ba),
                    src.a + (1 - src.a)*dst.a);
    }

    half color_burn_component(half2 s, half2 d) {
        half delta = abs(s.x) >= kMinNormalHalf
                            ? d.y - min(d.y, guarded_divide((d.y - d.x) * s.y, s.x))
                            : 0;
        return delta*s.y + s.x*(1 - d.y) + d.x*(1 - s.y);
    }

    half4 mi_blend_color_burn(half4 src, half4 dst) {
        return half4(color_burn_component(src.ra, dst.ra),
                    color_burn_component(src.ga, dst.ga),
                    color_burn_component(src.ba, dst.ba),
                    src.a + (1 - src.a)*dst.a);
    }

    half4 mi_blend_hard_light(half4 src, half4 dst) {
        return mi_blend_overlay(dst, src);
    }

    float soft_light_component(float2 s, float2 d) {
        if (2*s.x <= s.y) {
            return float(guarded_divide(half(d.x*d.x*(s.y - 2*s.x)), half(d.y))) + (1 - d.y)*s.x + d.x*(-s.y + 2*s.x + 1);
        } else if (4.0 * d.x <= d.y) {
            float DSqd = d.x*d.x;
            float DCub = DSqd*d.x;
            float DaSqd = d.y*d.y;
            float DaCub = DaSqd*d.y;
            return float(guarded_divide(half(DaSqd*(s.x - d.x*(3*s.y - 6*s.x - 1)) + 12*d.y*DSqd*(s.y - 2*s.x)
                                - 16*DCub * (s.y - 2*s.x) - DaCub*s.x), half(DaSqd)));
        } else {
            return d.x*(s.y - 2*s.x + 1) + s.x - sqrt(d.y*d.x)*(s.y - 2*s.x) - d.y*s.x;
        }
    }

    half4 mi_blend_soft_light(half4 src, half4 dst) {
        return (dst.a == 0) ? src : half4(half(soft_light_component(float2(src.ra), float2(dst.ra))),
                                        half(soft_light_component(float2(src.ga), float2(dst.ga))),
                                        half(soft_light_component(float2(src.ba), float2(dst.ba))),
                                        src.a + (1 - src.a)*dst.a);
    }

    half4 mi_blend_difference(half4 src, half4 dst) {
        return half4(src.rgb + dst.rgb - 2*min(src.rgb*dst.a, dst.rgb*src.a),
                    src.a + (1 - src.a)*dst.a);
    }

    half4 mi_blend_exclusion(half4 src, half4 dst) {
        return half4(dst.rgb + src.rgb - 2*dst.rgb*src.rgb, src.a + (1 - src.a)*dst.a);
    }

    half4 mi_blend_multiply(half4 src, half4 dst) {
        return half4((1 - src.a)*dst.rgb + (1 - dst.a)*src.rgb + src.rgb*dst.rgb,
                    src.a + (1 - src.a)*dst.a);
    }

    // Non-separable (HSL) blend modes
    half mi_blend_color_luminance(half3 color) { return dot(half3(0.3, 0.59, 0.11), color); }

    half3 mi_blend_set_color_luminance(half3 hueSatColor, half alpha, half3 lumColor) {
        half lum = mi_blend_color_luminance(lumColor);
        half3 result = lum - mi_blend_color_luminance(hueSatColor) + hueSatColor;
        half minComp = min(min(result.r, result.g), result.b);
        half maxComp = max(max(result.r, result.g), result.b);
        if (minComp < 0 && lum != minComp) {
            result = lum + (result - lum) * guarded_divide(lum, (lum - minComp) + kMinNormalHalf);
        }
        if (maxComp > alpha && maxComp != lum) {
            result = lum + guarded_divide((result - lum) * (alpha - lum), (maxComp - lum) + kMinNormalHalf);
        }
        return result;
    }

    half mi_blend_color_saturation(half3 color) {
        return max(max(color.r, color.g), color.b) - min(min(color.r, color.g), color.b);
    }

    half3 mi_blend_set_color_saturation(half3 color, half3 satColor) {
        half mn = min(min(color.r, color.g), color.b);
        half mx = max(max(color.r, color.g), color.b);
        return (mx > mn) ? ((color - mn) * mi_blend_color_saturation(satColor)) / (mx - mn) : half3(0);
    }

    half4 mi_blend_hslc(half2 flipSat, half4 src, half4 dst) {
        half alpha = dst.a * src.a;
        half3 sda = src.rgb * dst.a;
        half3 dsa = dst.rgb * src.a;
        half3 l = bool(flipSat.x) ? dsa : sda;
        half3 r = bool(flipSat.x) ? sda : dsa;
        if (bool(flipSat.y)) {
            l = mi_blend_set_color_saturation(l, r);
            r = dsa;
        }
        return half4(mi_blend_set_color_luminance(l, alpha, r) + dst.rgb - dsa + src.rgb - sda,
                    src.a + dst.a - alpha);
    }

    half4 mi_blend_hue(half4 src, half4 dst) { return mi_blend_hslc(half2(0, 1), src, dst); }
    half4 mi_blend_saturation(half4 src, half4 dst) { return mi_blend_hslc(half2(1), src, dst); }
    half4 mi_blend_color(half4 src, half4 dst) { return mi_blend_hslc(half2(0), src, dst); }
    half4 mi_blend_luminosity(half4 src, half4 dst) { return mi_blend_hslc(half2(1, 0), src, dst); }

    // Standard mode dispatch (0-28)
    half4 doBlend(int mode, half4 blendColor, half4 background) {
        if (mode == 0)  { background = mi_blend_clear(blendColor, background); }
        else if (mode == 1)  { background = mi_blend_src(blendColor, background); }
        else if (mode == 2)  { background = mi_blend_dst(blendColor, background); }
        else if (mode == 3)  { background = mi_blend_src_over(blendColor, background); }
        else if (mode == 4)  { background = mi_blend_dst_over(blendColor, background); }
        else if (mode == 5)  { background = mi_blend_src_in(blendColor, background); }
        else if (mode == 6)  { background = mi_blend_dst_in(blendColor, background); }
        else if (mode == 7)  { background = mi_blend_src_out(blendColor, background); }
        else if (mode == 8)  { background = mi_blend_dst_out(blendColor, background); }
        else if (mode == 9)  { background = mi_blend_src_atop(blendColor, background); }
        else if (mode == 10) { background = mi_blend_dst_atop(blendColor, background); }
        else if (mode == 11) { background = mi_blend_xor(blendColor, background); }
        else if (mode == 12) { background = mi_blend_plus(blendColor, background); }
        else if (mode == 13) { background = mi_blend_modulate(blendColor, background); }
        else if (mode == 14) { background = mi_blend_screen(blendColor, background); }
        else if (mode == 15) { background = mi_blend_overlay(blendColor, background); }
        else if (mode == 16) { background = mi_blend_darken(blendColor, background); }
        else if (mode == 17) { background = mi_blend_lighten(blendColor, background); }
        else if (mode == 18) { background = mi_blend_color_dodge(blendColor, background); }
        else if (mode == 19) { background = mi_blend_color_burn(blendColor, background); }
        else if (mode == 20) { background = mi_blend_hard_light(blendColor, background); }
        else if (mode == 21) { background = mi_blend_soft_light(blendColor, background); }
        else if (mode == 22) { background = mi_blend_difference(blendColor, background); }
        else if (mode == 23) { background = mi_blend_exclusion(blendColor, background); }
        else if (mode == 24) { background = mi_blend_multiply(blendColor, background); }
        else if (mode == 25) { background = mi_blend_hue(blendColor, background); }
        else if (mode == 26) { background = mi_blend_saturation(blendColor, background); }
        else if (mode == 27) { background = mi_blend_color(blendColor, background); }
        else if (mode == 28) { background = mi_blend_luminosity(blendColor, background); }
        return background;
    }

"""

/** Lab / linear light / plus / adjustments + `getBlendModeColor` dispatch for modes > 28. */
private const val BLEND_MODE_SHADER_EXTENDED_HELPERS = """
    uniform float uSaturation;
    uniform float uBrightness;
    uniform float uLuminanceAmount;
    uniform vec4 uLuminanceValues;

    // ================================================================
    // Extended blend modes (100+)
    // ================================================================

    // SrcOver compositing (used by custom modes only)
    half4 blendSrcOver(half4 src, half4 dst) {
        if (src.a == 0.0) return dst;
        half srcAlpha = src.a;
        half dstAlpha = dst.a * (1.0 - srcAlpha);
        half outAlpha = srcAlpha + dstAlpha;
        if (outAlpha == 0.0) return half4(0, 0, 0, 0);
        half4 outColor = (src * srcAlpha + dst * dstAlpha) / outAlpha;
        return half4(outColor.rgb, outAlpha);
    }

    // Color Burn (unpremultiplied, used by custom modes 118/119)
    float blendColorBurn(float base, float blend) {
        return (blend == 0.0) ? blend : max((1.0 - ((1.0 - base) / blend)), 0.0);
    }
    half3 blendColorBurn(half3 base, half3 blend) {
        return half3(blendColorBurn(float(base.r), float(blend.r)), blendColorBurn(float(base.g), float(blend.g)), blendColorBurn(float(base.b), float(blend.b)));
    }
    half3 blendColorBurn(half3 base, half3 blend, half opacity) {
        return (blendColorBurn(base, blend) * opacity + base * (1.0 - opacity));
    }
    half4 blendColorBurn(half4 src, half4 dst) {
        return half4(blendColorBurn(dst.rgb, src.rgb, src.a), dst.a);
    }

    // Color Dodge (unpremultiplied, used by custom modes 118/119)
    float blendColorDodge(float base, float blend) {
        return (blend == 1.0) ? blend : min(base / (1.0 - blend), 1.0);
    }
    half3 blendColorDodge(half3 base, half3 blend) {
        return half3(blendColorDodge(float(base.r), float(blend.r)), blendColorDodge(float(base.g), float(blend.g)), blendColorDodge(float(base.b), float(blend.b)));
    }
    half3 blendColorDodge(half3 base, half3 blend, half opacity) {
        return (blendColorDodge(base, blend) * opacity + base * (1.0 - opacity));
    }
    half4 blendColorDodge(half4 src, half4 dst) {
        return half4(blendColorDodge(dst.rgb, src.rgb, src.a), dst.a);
    }

    // Linear Dodge / Burn / Light
    half blendLinearDodge(half base, half blend) { return min(base + blend, 1.0); }
    half3 blendLinearDodge(half3 base, half3 blend) { return min(base + blend, half3(1.0)); }
    half3 blendLinearDodge(half3 base, half3 blend, half opacity) {
        return (blendLinearDodge(base, blend) * opacity + base * (1.0 - opacity));
    }

    half blendLinearBurn(half base, half blend) { return max(base + blend - 1.0, 0.0); }
    half3 blendLinearBurn(half3 base, half3 blend) { return max(base + blend - half3(1.0), half3(0.0)); }
    half3 blendLinearBurn(half3 base, half3 blend, half opacity) {
        return (blendLinearBurn(base, blend) * opacity + base * (1.0 - opacity));
    }

    half blendLinearLight(half base, half blend) {
        return blend < 0.5 ? blendLinearBurn(base, 2.0 * blend) : blendLinearDodge(base, 2.0 * (blend - 0.5));
    }
    half3 blendLinearLight(half3 base, half3 blend) {
        return half3(blendLinearLight(base.r, blend.r), blendLinearLight(base.g, blend.g), blendLinearLight(base.b, blend.b));
    }
    half3 blendLinearLight(half3 base, half3 blend, half opacity) {
        return (blendLinearLight(base, blend) * opacity + base * (1.0 - opacity));
    }
    half4 blendLinearLight(half4 src, half4 dst) {
        return half4(blendLinearLight(dst.rgb, src.rgb, src.a), dst.a);
    }

    // Greyscale
    half greyscale(half3 color) { return 0.3 * color.r + 0.59 * color.g + 0.11 * color.b; }

    // Lab Color Space (sRGB <-> XYZ <-> Lab, D65)
    // All conversions use float precision to avoid error amplification
    // in pow(), large-scale multiplication, and matrix operations.
    float3 rgb2xyz(float3 c) {
        c.r = c.r > 0.04045 ? pow((c.r + 0.055) / 1.055, 2.4) : c.r / 12.92;
        c.g = c.g > 0.04045 ? pow((c.g + 0.055) / 1.055, 2.4) : c.g / 12.92;
        c.b = c.b > 0.04045 ? pow((c.b + 0.055) / 1.055, 2.4) : c.b / 12.92;
        c *= 100.0;
        return float3(
            c.r * 0.4124 + c.g * 0.3576 + c.b * 0.1805,
            c.r * 0.2126 + c.g * 0.7152 + c.b * 0.0722,
            c.r * 0.0193 + c.g * 0.1192 + c.b * 0.9505);
    }

    float3 xyz2lab(float3 c) {
        float3 w = float3(95.047, 100.0, 108.883);
        c /= w;
        c.x = c.x > 0.008856 ? pow(c.x, 1.0/3.0) : 7.787 * c.x + 16.0/116.0;
        c.y = c.y > 0.008856 ? pow(c.y, 1.0/3.0) : 7.787 * c.y + 16.0/116.0;
        c.z = c.z > 0.008856 ? pow(c.z, 1.0/3.0) : 7.787 * c.z + 16.0/116.0;
        return float3(116.0 * c.y - 16.0, 500.0 * (c.x - c.y), 200.0 * (c.y - c.z));
    }

    float3 rgb2lab(float3 c) { return xyz2lab(rgb2xyz(c)); }

    float3 lab2xyz(float3 lab) {
        float y = (lab.x + 16.0) / 116.0;
        float x = lab.y / 500.0 + y;
        float z = y - lab.z / 200.0;
        y = pow(y, 3.0) > 0.008856 ? pow(y, 3.0) : (y - 16.0/116.0) / 7.787;
        x = pow(x, 3.0) > 0.008856 ? pow(x, 3.0) : (x - 16.0/116.0) / 7.787;
        z = pow(z, 3.0) > 0.008856 ? pow(z, 3.0) : (z - 16.0/116.0) / 7.787;
        float3 w = float3(95.047, 100.0, 108.883);
        return float3(x * w.x, y * w.y, z * w.z);
    }

    float3 xyz2rgb(float3 xyz) {
        xyz /= 100.0;
        float r = xyz.x *  3.2406 + xyz.y * -1.5372 + xyz.z * -0.4986;
        float g = xyz.x * -0.9689 + xyz.y *  1.8758 + xyz.z *  0.0415;
        float b = xyz.x *  0.0557 + xyz.y * -0.2040 + xyz.z *  1.0570;
        r = r > 0.0031308 ? 1.055 * pow(r, 1.0/2.4) - 0.055 : 12.92 * r;
        g = g > 0.0031308 ? 1.055 * pow(g, 1.0/2.4) - 0.055 : 12.92 * g;
        b = b > 0.0031308 ? 1.055 * pow(b, 1.0/2.4) - 0.055 : 12.92 * b;
        return float3(clamp(r, 0.0, 1.0), clamp(g, 0.0, 1.0), clamp(b, 0.0, 1.0));
    }

    float3 lab2rgb(float3 lab) { return xyz2rgb(lab2xyz(lab)); }

    half4 labLighten(half4 c, half a) {
        float3 lab = rgb2lab(float3(c.rgb));
        lab.r = (100.0 - float(a)) / 55.0 * lab.r + (100.0 * float(a) - 4500.0) / 55.0;
        return half4(half3(lab2rgb(lab)), c.a);
    }

    half4 labDarken(half4 c, half a) {
        float3 lab = rgb2lab(float3(c.rgb));
        lab.r = float(a) / 45.0 * lab.r;
        return half4(half3(lab2rgb(lab)), c.a);
    }

    half4 labColor(half4 c, half m, half n) {
        float3 lab = rgb2lab(float3(c.rgb));
        lab.r = (float(n) - float(m)) * lab.r + float(m) * 100.0;
        return half4(half3(lab2rgb(lab)), c.a);
    }

    float3 labNormalized(float3 lab) { return float3(lab.r / 100.0, (lab.g + 128.0) / 255.0, (lab.b + 128.0) / 255.0); }
    float3 labDenormalized(float3 lab) { return float3(lab.r * 100.0, lab.g * 255.0 - 128.0, lab.b * 255.0 - 128.0); }

    // float-precision linear light to avoid half truncation before labDenormalized
    float blendLinearLightF(float base, float blend) {
        return blend < 0.5 ? max(base + 2.0 * blend - 1.0, 0.0) : min(base + 2.0 * (blend - 0.5), 1.0);
    }
    float3 blendLinearLightF(float3 base, float3 blend) {
        return float3(blendLinearLightF(base.r, blend.r), blendLinearLightF(base.g, blend.g), blendLinearLightF(base.b, blend.b));
    }

    half4 blendLinearLightLab(half4 src, half4 dst) {
        float3 labSrc = labNormalized(rgb2lab(float3(src.rgb)));
        float3 labDst = labNormalized(rgb2lab(float3(dst.rgb)));
        return half4(half3(lab2rgb(labDenormalized(blendLinearLightF(labDst, labSrc)))), 1.0);
    }

    half3 blendDifference(half3 base, half3 blend) { return abs(base - blend); }
    half3 blendDifference(half3 base, half3 blend, half opacity) {
        return (blendDifference(base, blend) * opacity + base * (1.0 - opacity));
    }

    // Plus Darker / Lighter (V2 alpha-aware)
    half4 blendPlusDarker(half4 src, half4 dst) {
        src.rgb *= src.a; dst.rgb *= dst.a;
        half3 c = max(half3(0.0), 1.0 - ((1.0 - dst.rgb) * dst.a + (1.0 - src.rgb) * src.a));
        half a = src.a + dst.a * (1.0 - src.a);
        return half4(c / a, a);
    }

    half4 blendPlusLighter(half4 src, half4 dst) {
        src.rgb *= src.a; dst.rgb *= dst.a;
        half3 c = min(src.rgb + dst.rgb, half3(1.0));
        half a = src.a + (1.0 - src.a) * dst.a;
        return half4(c / a, a);
    }

    // Color Adjustment (modes 201-203)
    half4 adjust_saturation(half4 c, float sa) {
        half lum = dot(c.rgb, half3(0.2125, 0.7153, 0.0721));
        return half4(sa * c.rgb + (1.0 - sa) * lum, c.a);
    }

    half4 adjust_brightness(half4 c, float br) {
        return half4(c.rgb + half3(br * c.a), c.a);
    }

    const mat4 adjustment_matrix = mat4(
        -1,  3, -3,  1,
         3, -6,  3,  0,
        -3,  3,  0,  0,
         1,  0,  0,  0);

    half4 luminance_curve(half4 c, vec4 factors, float mix_factor) {
        vec4 fa = adjustment_matrix * factors;
        half alpha = max(c.a, 0.0001);
        half3 sc = c.rgb / alpha;
        float lum = clamp(dot(sc, vec3(0.2125, 0.7153, 0.0721)), 0.0, 1.0);
        float adj = clamp(((lum * fa.r + fa.g) * lum + fa.b) * lum + fa.a, 0.0, 1.0);
        return half4(mix(sc, half3(adj), mix_factor) * alpha, c.a);
    }

    // ================================================================
    // Main Dispatch
    // ================================================================

    half4 getBlendModeColor(half4 bg, half4 ch, int mode, half4 bc) {
        // Standard modes (0-28): premultiplied alpha, direct result
        if (mode <= 28) return doBlend(mode, bc, bg);
        // Custom modes (100+): unpremultiplied inputs, alpha handled internally
        if (mode == 100) return blendLinearLight(bc, bg);
        if (mode == 101) { half g = greyscale(ch.rgb); return mix(ch, blendLinearLight(half4(bc.rgb, g), bg), bc.a); }
        if (mode == 102) return half4(blendDifference(bg.rgb, bc.rgb, bc.a), bg.a);
        if (mode == 103) { half g = greyscale(ch.rgb) * 100.0; return mix(ch, labLighten(bg, 0.4 * g + 45.0), bc.a); }
        if (mode == 105) { half g = greyscale(ch.rgb) * 100.0; return mix(ch, labDarken(bg, 45.0 - 0.15 * g), bc.a); }
        if (mode == 106) return mix(ch, labColor(bg, bc.r, bc.g), bc.a);
        if (mode == 107) return mix(ch, blendLinearLightLab(bc, bg), bc.a);
        if (mode == 118) return blendColorDodge(bc, bg);
        if (mode == 119) return blendColorBurn(bc, bg);
        if (mode == 120) return blendPlusDarker(bc, bg);
        if (mode == 121) return blendPlusLighter(bc, bg);
        if (mode == 200) { half4 r = (1.0 - bc.a) * bg + bc.a * bc; half a = (1.0 - bc.a) * (1.0 - ch.r) + ch.r; return half4(r.xyz * a, bg.a); }
        if (mode == 201) { half4 adj = adjust_saturation(bg, uSaturation); return half4(mix(bg.rgb, adj.rgb, bc.a), bg.a); }
        if (mode == 202) { half4 adj = adjust_brightness(bg, uBrightness); return half4(mix(bg.rgb, adj.rgb, bc.a), bg.a); }
        if (mode == 203) { half4 adj = luminance_curve(bg, uLuminanceValues, uLuminanceAmount); return half4(mix(bg.rgb, adj.rgb, bc.a), bg.a); }
        return blendSrcOver(bc, bg);
    }
"""

/** Ramp band uniforms + weight for the progressive composite's folded post pass. */
private fun buildBlendModeRampUniforms(curveKind: Int): String {
    val curveUniform = if (curveKind == 3) "\n    uniform float in_curve;" else ""
    val curveExpr = when (curveKind) {
        0 -> ""
        1 -> "\n        raw = sqrt(raw);"
        2 -> "\n        raw = sqrt(sqrt(raw));"
        else -> "\n        raw = pow(raw, in_curve);"
    }
    return """
    uniform float2 in_gradVec;
    uniform float in_gradOffset;$curveUniform

    float evalRampRaw(float2 fragCoord) {
        float raw = clamp(dot(fragCoord, in_gradVec) + in_gradOffset, 0.0, 1.0);
        raw = raw * raw * (3.0 - 2.0 * raw);$curveExpr
        return raw;
    }

"""
}

/** Flat `main` — standard family only, no unpremul/premul roundtrip. */
private const val BLEND_MODE_SHADER_MAIN_STANDARD = """
    half4 main(float2 fragCoord) {
        half4 currentColor = child.eval(fragCoord);
        int count = int(layerCount);

        for (int i = 0; i < 8; i++) {
            if (i >= count) break;
            half4 layerColor = layerColors[i];
            int mode = int(blendModes[i]);
            currentColor = doBlend(mode, layerColor, currentColor);
        }
        return currentColor;
    }
"""

/** [BLEND_MODE_SHADER_MAIN_STANDARD] with the blended result ramp-mixed over the source. */
private const val BLEND_MODE_SHADER_MAIN_STANDARD_RAMP = """
    half4 main(float2 fragCoord) {
        half4 src = child.eval(fragCoord);
        if (src.a > 0.0039 && src.a < 0.996) {
            src = half4(src.rgb / src.a, 1.0);
        }
        float w = 1.0 - evalRampRaw(fragCoord);
        half rw = half(w * w * (3.0 - 2.0 * w));
        if (rw <= half(0.001)) {
            return src;
        }
        half4 currentColor = src;
        int count = int(layerCount);

        for (int i = 0; i < 8; i++) {
            if (i >= count) break;
            half4 layerColor = layerColors[i];
            int mode = int(blendModes[i]);
            currentColor = doBlend(mode, layerColor, currentColor);
        }
        return mix(src, currentColor, rw);
    }
"""

/** [BLEND_MODE_SHADER_MAIN_EXTENDED] with the blended result ramp-mixed over the source. */
private const val BLEND_MODE_SHADER_MAIN_EXTENDED_RAMP = """
    half4 main(float2 fragCoord) {
        half4 src = child.eval(fragCoord);
        if (src.a > 0.0039 && src.a < 0.996) {
            src = half4(src.rgb / src.a, 1.0);
        }
        float w = 1.0 - evalRampRaw(fragCoord);
        half rw = half(w * w * (3.0 - 2.0 * w));
        if (rw <= half(0.001)) {
            return src;
        }
        float4 currentColor = float4(src);
        int count = int(layerCount);

        for (int i = 0; i < 8; i++) {
            if (i >= count) break;
            half4 layerColor = layerColors[i];
            int mode = int(blendModes[i]);
            // Standard modes produce premultiplied result directly
            if (mode <= 28) {
                currentColor = float4(doBlend(mode, layerColor, half4(currentColor)));
            } else {
                // Unpremultiply for custom modes (they expect straight alpha)
                float bgA = currentColor.a;
                float3 bgStr = bgA > 0.0001 ? currentColor.rgb / bgA : float3(0.0);
                half lcA = layerColor.a;
                half3 lcStr = lcA > 0.0 ? layerColor.rgb / lcA : half3(0.0);

                half4 bg = half4(half3(bgStr), half(bgA));
                half4 lc = half4(lcStr, lcA);

                // getBlendModeColor handles alpha internally, returns unpremultiplied result
                half4 blended = getBlendModeColor(bg, bg, mode, lc);

                // Re-premultiply
                float outA = float(blended.a);
                currentColor = float4(float3(blended.rgb) * outA, outA);
            }
        }
        return mix(src, half4(currentColor), rw);
    }
"""

/** Branching `main` — extended family with unpremul/premul roundtrip for custom modes. */
private const val BLEND_MODE_SHADER_MAIN_EXTENDED = """
    half4 main(float2 fragCoord) {
        float4 currentColor = float4(child.eval(fragCoord));
        int count = int(layerCount);

        for (int i = 0; i < 8; i++) {
            if (i >= count) break;
            half4 layerColor = layerColors[i];
            int mode = int(blendModes[i]);
            // Standard modes produce premultiplied result directly
            if (mode <= 28) {
                currentColor = float4(doBlend(mode, layerColor, half4(currentColor)));
            } else {
                // Unpremultiply for custom modes (they expect straight alpha)
                float bgA = currentColor.a;
                float3 bgStr = bgA > 0.0001 ? currentColor.rgb / bgA : float3(0.0);
                half lcA = layerColor.a;
                half3 lcStr = lcA > 0.0 ? layerColor.rgb / lcA : half3(0.0);

                half4 bg = half4(half3(bgStr), half(bgA));
                half4 lc = half4(lcStr, lcA);

                // getBlendModeColor handles alpha internally, returns unpremultiplied result
                half4 blended = getBlendModeColor(bg, bg, mode, lc);

                // Re-premultiply
                float outA = float(blended.a);
                currentColor = float4(float3(blended.rgb) * outA, outA);
            }
        }
        return half4(currentColor);
    }
"""
