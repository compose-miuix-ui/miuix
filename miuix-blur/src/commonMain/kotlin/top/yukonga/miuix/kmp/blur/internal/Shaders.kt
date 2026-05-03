// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

/** Builds a separable Gaussian blur shader with [tapCount] symmetric tap pairs (1..7). */
internal fun buildGaussianBlurShader(tapCount: Int): String {
    require(tapCount in 1..MAX_BLUR_TAPS)
    val offsetSize = tapCount * 2
    return """
    uniform shader child;
    uniform float in_blurOffset[$offsetSize];
    uniform float in_blurWeight[$tapCount];
    uniform float2 in_texSize;

    half4 main(float2 xy) {
        half4 color = half4(0);
        float2 maxCoord = in_texSize - 0.5;
        for (int i = 0; i < $tapCount; i++) {
            float2 offset = float2(in_blurOffset[i], in_blurOffset[i + $tapCount]);
            float2 c1 = clamp(xy + offset, float2(0.5), maxCoord);
            float2 c2 = clamp(xy - offset, float2(0.5), maxCoord);
            color += (child.eval(c1) + child.eval(c2)) * half(in_blurWeight[i]);
        }
        if (color.a > 0.001) {
            return half4(color.rgb / color.a, 1.0);
        }
        return color;
    }
"""
}

/** Maximum number of tap pairs for the Gaussian blur shader. */
internal const val MAX_BLUR_TAPS = 7

/**
 * Brightness / contrast / saturation adjustment.
 *
 * Brightness is applied in linear (gamma 2.2) space to avoid sRGB hue shift;
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
            c = pow(c, half3(2.2));
            if (in_brightness > 0.0) {
                c = mix(c, half3(1.0), half(in_brightness));
            } else {
                c = c * half(1.0 + in_brightness);
            }
            c = pow(c, half3(0.45454545));
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

/**
 * Noise dithering shader — 3-channel pseudo-random anti-banding.
 */
internal const val NOISE_DITHER_SHADER = """
    uniform shader child;
    uniform float noise_coeff;

    float random1(float2 st) {
        return fract(sin(dot(st.xy, float2(6.9898, 78.233))) * 43734.5453);
    }
    float random2(float2 st) {
        return fract(sin(dot(st.xy, float2(7.9898, 78.233))) * 43745.5453);
    }
    float random3(float2 st) {
        return fract(sin(dot(st.xy, float2(8.9898, 78.233))) * 43767.5453);
    }

    half4 main(float2 xy) {
        float noise1 = (random1(xy) - 0.5) * noise_coeff;
        float noise2 = (random2(xy) - 0.5) * noise_coeff;
        float noise3 = (random3(xy) - 0.5) * noise_coeff;
        half4 color = child.eval(xy);
        color.rg += half2(noise1);
        color.rb += half2(noise2);
        color.gb += half2(noise3);
        return color;
    }
"""

/** 2× downsample with a 4-point box filter (uniforms: `child`, `imageWH[2]`). */
internal const val DOWNSAMPLE_2X_SHADER = """
    uniform shader child;
    uniform float imageWH[2];
    half4 main(float2 xy) {
        float2 center = float2(xy.x - 0.5, xy.y - 0.5);
        center = clamp(center, float2(0), float2(imageWH[0] - 1, imageWH[1] - 1));
        float4 color = float4(child.eval(center + float2(0.25, 0.25)));
        color += float4(child.eval(center + float2(0.25, 0.75)));
        color += float4(child.eval(center + float2(0.75, 0.25)));
        color += float4(child.eval(center + float2(0.75, 0.75)));
        return half4(color * 0.25);
    }
"""

/** 4× downsample with a 16-tap 4×4 grid box filter (uniforms: `child`, `imageWH[2]`). */
internal const val DOWNSAMPLE_4X_SHADER = """
    uniform shader child;
    uniform float imageWH[2];
    half4 main(float2 xy) {
        float2 center = float2(xy.x - 0.5, xy.y - 0.5);
        center = clamp(center, float2(0), float2(imageWH[0] - 1, imageWH[1] - 1));
        float4 color = float4(0);
        color += float4(child.eval(center + float2(0.125, 0.125)));
        color += float4(child.eval(center + float2(0.125, 0.375)));
        color += float4(child.eval(center + float2(0.125, 0.625)));
        color += float4(child.eval(center + float2(0.125, 0.875)));
        color += float4(child.eval(center + float2(0.375, 0.125)));
        color += float4(child.eval(center + float2(0.375, 0.375)));
        color += float4(child.eval(center + float2(0.375, 0.625)));
        color += float4(child.eval(center + float2(0.375, 0.875)));
        color += float4(child.eval(center + float2(0.625, 0.125)));
        color += float4(child.eval(center + float2(0.625, 0.375)));
        color += float4(child.eval(center + float2(0.625, 0.625)));
        color += float4(child.eval(center + float2(0.625, 0.875)));
        color += float4(child.eval(center + float2(0.875, 0.125)));
        color += float4(child.eval(center + float2(0.875, 0.375)));
        color += float4(child.eval(center + float2(0.875, 0.625)));
        color += float4(child.eval(center + float2(0.875, 0.875)));
        return half4(color * (1.0/16.0));
    }
"""

/**
 * Edge bloom stroke shader. A rounded-rect SDF defines an outer mask + a stroke band,
 * then a 3D hemispheric normal is built along the rounded edge so that two directional
 * lights paint the inner halo. The two lights split the upper / lower hemisphere via
 * a `dot((0, ∓1, 0), n)` cosine factor.
 *
 * Light directions are pre-normalized on the CPU side: `dir = normalize((srcX-0.5,
 * srcY-0.7, srcZ))`, where `(0.5, 0.7, 0)` is the reference origin.
 *
 * Output is suited for overlay compositing: `result.rgb` carries premultiplied bloom
 * contributions, `result.a = outMask`, all multiplied by the rounded-rect outer mask.
 * Compose `BlendMode.Plus` then adds these contributions onto the surface below.
 */
internal const val BLOOM_STROKE_SHADER = """
uniform float2 size;
uniform float4 cornerRadii;
uniform float strokeWidth;
uniform float innerBlurRadius;
uniform float highlightAlpha;

layout(color) uniform half4 strokeColor;
uniform float strokeAlphaMul;

uniform float3 lightDir1;
layout(color) uniform half4 lightColor1;
uniform float lightIntensity1;

uniform float3 lightDir2;
layout(color) uniform half4 lightColor2;
uniform float lightIntensity2;

float pickRadius(float2 fragCoord, float2 halfView, float4 radii) {
    float2 up = fragCoord.y < halfView.y ? radii.xy : radii.zw;
    return fragCoord.x < halfView.x ? up.x : up.y;
}

float roundedBoxSDF(float2 pos, float2 halfSize, float radius) {
    radius = min(radius, min(halfSize.x, halfSize.y));
    float2 d = abs(pos) - halfSize + radius;
    return length(max(d, 0.0)) + min(max(d.x, d.y), 0.0) - radius;
}

float3 getNormal(float2 fragCoord, float2 halfView, float sdf, float R, float innerR) {
    float2 xy = fragCoord - floor(halfView);
    float2 xy_a = abs(xy);
    float t = smoothstep(-innerR, 0.0, sdf);
    float z = sqrt(max(innerR * innerR - t * t, 0.0));
    float3 coord = float3(xy_a, -z);

    float2 corner = halfView - R;
    corner.x = min(corner.x, xy_a.x);
    corner.y = min(corner.y, xy_a.y);

    float2 dir = normalize(coord.xy - corner.xy);
    corner += dir * (R - innerR);

    if (any(lessThan(xy_a, corner))) {
        return float3(0.0, 0.0, -1.0);
    }

    float2 signal = sign(xy);
    float3 n = normalize(coord - float3(corner, 0.0));
    n.xy *= signal;
    return n;
}

half4 main(float2 fragCoord) {
    float2 halfView = size * 0.5;
    float2 xy = abs(fragCoord - halfView);

    float originRadius = pickRadius(fragCoord, halfView, cornerRadii);
    float R = max(originRadius, innerBlurRadius);

    if (all(lessThan(xy, halfView - R))) {
        return half4(0.0);
    }

    float sdf = roundedBoxSDF(xy, halfView, originRadius);
    half outMask = half(smoothstep(0.0, -1.0, sdf));
    float strokeAlpha = smoothstep(-strokeWidth, -strokeWidth + 1.0, sdf);

    // Native: stroke = uStrokeColor * sa; result += stroke.rgb * stroke.a
    //       = strokeColor.rgb * strokeColor.a * sa^2
    half3 rgb = strokeColor.rgb * half(strokeAlphaMul * strokeAlpha * strokeAlpha);

    float3 n = getNormal(fragCoord, halfView, sdf, R, innerBlurRadius);

    // Native: c1.rgb = light1 * Lcolor.rgb * Lcolor.a; c1.a = light1
    //         result += c1.rgb * c1.a = light1^2 * Lcolor.rgb * Lcolor.a
    float falloff1 = max(dot(float3(0.0, -1.0, 0.0), n), 0.0);
    float light1 = clamp(dot(n, lightDir1) * falloff1, 0.0, 1.0);
    rgb += half(light1 * light1 * lightIntensity1) * lightColor1.rgb;

    float falloff2 = max(dot(float3(0.0, 1.0, 0.0), n), 0.0);
    float light2 = clamp(dot(n, lightDir2) * falloff2, 0.0, 1.0);
    rgb += half(light2 * light2 * lightIntensity2) * lightColor2.rgb;

    return half4(rgb * half(highlightAlpha), 1.0) * outMask;
}
"""

/**
 * Multi-layer blend mode dispatch. Standard Porter-Duff / separable / non-separable modes
 * (0-31) plus extended modes (100-121, 200-203) covering Lab operations, linear light,
 * alpha-aware plus darker/lighter, and brightness/saturation/luminance curves.
 */
internal const val MI_BLEND_MODE_SHADER = """
    uniform shader child;

    uniform float layerCount;
    uniform float blendModes[8];
    uniform vec4 layerColors[8]; // premultiplied sRGB, set as flat float[32]

    uniform float uSaturation;
    uniform float uBrightness;
    uniform float uLuminanceAmount;
    uniform vec4 uLuminanceValues;

    // ================================================================
    // Standard blend modes (0-31) — premultiplied alpha
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

    // Standard mode dispatch (0-31)
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
        else if (mode == 14 || mode == 29) { background = mi_blend_screen(blendColor, background); }
        else if (mode == 15) { background = mi_blend_overlay(blendColor, background); }
        else if (mode == 16) { background = mi_blend_darken(blendColor, background); }
        else if (mode == 17) { background = mi_blend_lighten(blendColor, background); }
        else if (mode == 18) { background = mi_blend_color_dodge(blendColor, background); }
        else if (mode == 19) { background = mi_blend_color_burn(blendColor, background); }
        else if (mode == 20) { background = mi_blend_hard_light(blendColor, background); }
        else if (mode == 21) { background = mi_blend_soft_light(blendColor, background); }
        else if (mode == 22) { background = mi_blend_difference(blendColor, background); }
        else if (mode == 23) { background = mi_blend_exclusion(blendColor, background); }
        else if (mode == 24 || mode == 30) { background = mi_blend_multiply(blendColor, background); }
        else if (mode == 25) { background = mi_blend_hue(blendColor, background); }
        else if (mode == 26) { background = mi_blend_saturation(blendColor, background); }
        else if (mode == 27) { background = mi_blend_color(blendColor, background); }
        else if (mode == 28 || mode == 31) { background = mi_blend_luminosity(blendColor, background); }
        return background;
    }

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
        // Standard modes (0-31): premultiplied alpha, direct result
        if (mode <= 31) return doBlend(mode, bc, bg);
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

    // ======== Main ========

    half4 main(float2 fragCoord) {
        float4 currentColor = float4(child.eval(fragCoord));
        int count = int(layerCount);

        for (int i = 0; i < 8; i++) {
            if (i >= count) break;
            half4 layerColor = layerColors[i];
            int mode = int(blendModes[i]);
            // Standard modes produce premultiplied result directly
            if (mode <= 31) {
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
