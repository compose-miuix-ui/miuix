// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

/**
 * Builds a tap-count-specialized LM Gaussian blur shader.
 *
 * Array sizes and loop bounds match the actual [tapCount] to minimize
 * texture samples (2 × tapCount per pixel), with 7 specialized shader
 * variants (1–7 taps).
 *
 * Applied twice (horizontal then vertical) for separable 2D convolution.
 *
 * Coordinates are clamped to `[0.5, in_texSize - 0.5]` to prevent
 * out-of-bounds sampling that may return transparent black on some platforms.
 *
 * The blur accumulation and un-premultiply division use `float` (32-bit)
 * precision to avoid amplifying quantization errors that cause visible
 * color banding on smooth gradients. `half` (16-bit) has only ~3 decimal
 * digits of precision; `float` provides ~7 digits.
 */
internal fun buildGaussianBlurShader(tapCount: Int): String {
    require(tapCount in 1..MAX_BLUR_TAPS)
    val offsetSize = tapCount * 2
    return """
    uniform shader child;
    uniform float in_blurOffset[$offsetSize];
    uniform float in_blurWeight[$tapCount];
    uniform float2 in_texSize;

    half4 main(float2 xy) {
        float4 color = float4(0);
        float2 maxCoord = in_texSize - 0.5;
        for (int i = 0; i < $tapCount; i++) {
            float2 offset = float2(in_blurOffset[i], in_blurOffset[i + $tapCount]);
            float2 c1 = clamp(xy + offset, float2(0.5), maxCoord);
            float2 c2 = clamp(xy - offset, float2(0.5), maxCoord);
            color += (float4(child.eval(c1)) + float4(child.eval(c2))) * in_blurWeight[i];
        }
        if (color.a > 0.001) {
            return half4(color.rgb / color.a, 1.0);
        }
        return half4(color);
    }
"""
}

/** Maximum number of tap pairs for the Gaussian blur shader. */
internal const val MAX_BLUR_TAPS = 7

/**
 * Noise dithering shader — 3-channel pseudo-random anti-banding.
 */
internal const val NOISE_DITHER_SHADER = """
    uniform shader child;
    uniform float noise_coeff;

    half random1(float2 st) {
        return fract(sin(dot(st.xy, float2(6.9898, 78.233))) * 43734.5453);
    }
    half random2(float2 st) {
        return fract(sin(dot(st.xy, float2(7.9898, 78.233))) * 43745.5453);
    }
    half random3(float2 st) {
        return fract(sin(dot(st.xy, float2(8.9898, 78.233))) * 43767.5453);
    }

    half4 main(float2 xy) {
        half noise1 = (random1(xy) - 0.5) * noise_coeff;
        half noise2 = (random2(xy) - 0.5) * noise_coeff;
        half noise3 = (random3(xy) - 0.5) * noise_coeff;
        half4 color = child.eval(xy);
        color.rg += noise1;
        color.rb += noise2;
        color.gb += noise3;
        return color;
    }
"""

/**
 * 2x downsample shader — 4-point box filter.
 *
 * Samples 4 sub-pixel positions at (0.25, 0.25), (0.25, 0.75), (0.75, 0.25),
 * (0.75, 0.75) within each output pixel's footprint, producing a proper
 * area-average that captures all source pixels. Matches libhwui's 2x
 * downsampling shader.
 *
 * Uniforms:
 * - `child`: Input image shader.
 * - `imageWH[2]`: Source image width and height.
 */
internal const val DOWNSAMPLE_2X_SHADER = """
    uniform shader child;
    uniform float imageWH[2];
    half4 main(float2 xy) {
        float2 center = float2(xy.x - 0.5, xy.y - 0.5);
        center = clamp(center, float2(0), float2(imageWH[0] - 1, imageWH[1] - 1));
        half4 color = child.eval(center + float2(0.25, 0.25));
        color += child.eval(center + float2(0.25, 0.75));
        color += child.eval(center + float2(0.75, 0.25));
        color += child.eval(center + float2(0.75, 0.75));
        return color * 0.25;
    }
"""


/**
 * Blend mode shader — complete dispatch for all standard (0-31) and custom (100+) modes.
 *
 * Standard modes (0-31) use premultiplied-alpha formulas from libhwui/Skia.
 * Custom modes (100-121, 200-203) implement Lab color space operations,
 * linear light blending, and alpha-aware plus darker/lighter operations.
 *
 * Uniforms:
 * - `child`: Input blurred image shader.
 * - `layerCount`: Number of active blend layers (max 8).
 * - `blendModes[8]`: Blend mode ID for each layer.
 * - `layerColors[8]`: RGBA color for each layer.
 * - `uSaturation`: Saturation factor for mode 201.
 * - `uBrightness`: Brightness offset for mode 202.
 * - `uLuminanceAmount`: Luminance mix factor for mode 203.
 * - `uLuminanceValues`: Luminance curve control points for mode 203.
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
    // Standard blend modes (0-31) — premultiplied alpha, from libhwui
    // ================================================================

    const half kMinNormalHalf = 0.00006103515625;
    const half kGuardedDivideEpsilon = 0.00000001;

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

    half soft_light_component(half2 s, half2 d) {
        if (2*s.x <= s.y) {
            return guarded_divide(d.x*d.x*(s.y - 2*s.x), d.y) + (1 - d.y)*s.x + d.x*(-s.y + 2*s.x + 1);
        } else if (4.0 * d.x <= d.y) {
            half DSqd = d.x*d.x;
            half DCub = DSqd*d.x;
            half DaSqd = d.y*d.y;
            half DaCub = DaSqd*d.y;
            return guarded_divide(DaSqd*(s.x - d.x*(3*s.y - 6*s.x - 1)) + 12*d.y*DSqd*(s.y - 2*s.x)
                                - 16*DCub * (s.y - 2*s.x) - DaCub*s.x, DaSqd);
        } else {
            return d.x*(s.y - 2*s.x + 1) + s.x - sqrt(d.y*d.x)*(s.y - 2*s.x) - d.y*s.x;
        }
    }

    half4 mi_blend_soft_light(half4 src, half4 dst) {
        return (dst.a == 0) ? src : half4(soft_light_component(src.ra, dst.ra),
                                        soft_light_component(src.ga, dst.ga),
                                        soft_light_component(src.ba, dst.ba),
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
    // Custom blend modes (100+) — Mi extensions
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
    half blendColorBurn(half base, half blend) {
        return (blend == 0.0) ? blend : max((1.0 - ((1.0 - base) / blend)), 0.0);
    }
    half3 blendColorBurn(half3 base, half3 blend) {
        return half3(blendColorBurn(base.r, blend.r), blendColorBurn(base.g, blend.g), blendColorBurn(base.b, blend.b));
    }
    half3 blendColorBurn(half3 base, half3 blend, half opacity) {
        return (blendColorBurn(base, blend) * opacity + base * (1.0 - opacity));
    }
    half4 blendColorBurn(half4 src, half4 dst) {
        return half4(blendColorBurn(dst.rgb, src.rgb, src.a), dst.a);
    }

    // Color Dodge (unpremultiplied, used by custom modes 118/119)
    half blendColorDodge(half base, half blend) {
        return (blend == 1.0) ? blend : min(base / (1.0 - blend), 1.0);
    }
    half3 blendColorDodge(half3 base, half3 blend) {
        return half3(blendColorDodge(base.r, blend.r), blendColorDodge(base.g, blend.g), blendColorDodge(base.b, blend.b));
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
    half3 rgb2xyz(half3 c) {
        c.r = c.r > 0.04045 ? pow((c.r + 0.055) / 1.055, 2.4) : c.r / 12.92;
        c.g = c.g > 0.04045 ? pow((c.g + 0.055) / 1.055, 2.4) : c.g / 12.92;
        c.b = c.b > 0.04045 ? pow((c.b + 0.055) / 1.055, 2.4) : c.b / 12.92;
        c *= 100.0;
        return half3(
            c.r * 0.4124 + c.g * 0.3576 + c.b * 0.1805,
            c.r * 0.2126 + c.g * 0.7152 + c.b * 0.0722,
            c.r * 0.0193 + c.g * 0.1192 + c.b * 0.9505);
    }

    half3 xyz2lab(half3 c) {
        half3 w = half3(95.047, 100.0, 108.883);
        c /= w;
        c.x = c.x > 0.008856 ? pow(c.x, 1.0/3.0) : 7.787 * c.x + 16.0/116.0;
        c.y = c.y > 0.008856 ? pow(c.y, 1.0/3.0) : 7.787 * c.y + 16.0/116.0;
        c.z = c.z > 0.008856 ? pow(c.z, 1.0/3.0) : 7.787 * c.z + 16.0/116.0;
        return half3(116.0 * c.y - 16.0, 500.0 * (c.x - c.y), 200.0 * (c.y - c.z));
    }

    half3 rgb2lab(half3 c) { return xyz2lab(rgb2xyz(c)); }

    half3 lab2xyz(half3 lab) {
        half y = (lab.x + 16.0) / 116.0;
        half x = lab.y / 500.0 + y;
        half z = y - lab.z / 200.0;
        y = pow(y, 3.0) > 0.008856 ? pow(y, 3.0) : (y - 16.0/116.0) / 7.787;
        x = pow(x, 3.0) > 0.008856 ? pow(x, 3.0) : (x - 16.0/116.0) / 7.787;
        z = pow(z, 3.0) > 0.008856 ? pow(z, 3.0) : (z - 16.0/116.0) / 7.787;
        half3 w = half3(95.047, 100.0, 108.883);
        return half3(x * w.x, y * w.y, z * w.z);
    }

    half3 xyz2rgb(half3 xyz) {
        xyz /= 100.0;
        half r = xyz.x *  3.2406 + xyz.y * -1.5372 + xyz.z * -0.4986;
        half g = xyz.x * -0.9689 + xyz.y *  1.8758 + xyz.z *  0.0415;
        half b = xyz.x *  0.0557 + xyz.y * -0.2040 + xyz.z *  1.0570;
        r = r > 0.0031308 ? 1.055 * pow(r, 1.0/2.4) - 0.055 : 12.92 * r;
        g = g > 0.0031308 ? 1.055 * pow(g, 1.0/2.4) - 0.055 : 12.92 * g;
        b = b > 0.0031308 ? 1.055 * pow(b, 1.0/2.4) - 0.055 : 12.92 * b;
        return half3(clamp(r, 0.0, 1.0), clamp(g, 0.0, 1.0), clamp(b, 0.0, 1.0));
    }

    half3 lab2rgb(half3 lab) { return xyz2rgb(lab2xyz(lab)); }

    half4 labLighten(half4 c, half a) {
        half3 lab = rgb2lab(c.rgb);
        lab.r = (100.0 - a) / 55.0 * lab.r + (100.0 * a - 4500.0) / 55.0;
        return half4(lab2rgb(lab), c.a);
    }

    half4 labDarken(half4 c, half a) {
        half3 lab = rgb2lab(c.rgb);
        lab.r = a / 45.0 * lab.r;
        return half4(lab2rgb(lab), c.a);
    }

    half4 labColor(half4 c, half m, half n) {
        half3 lab = rgb2lab(c.rgb);
        lab.r = (n - m) * lab.r + m * 100.0;
        return half4(lab2rgb(lab), c.a);
    }

    half3 labNormalized(half3 lab) { return half3(lab.r / 100.0, (lab.g + 128.0) / 255.0, (lab.b + 128.0) / 255.0); }
    half3 labDenormalized(half3 lab) { return half3(lab.r * 100.0, lab.g * 255.0 - 128.0, lab.b * 255.0 - 128.0); }

    half4 blendLinearLightLab(half4 src, half4 dst) {
        half3 labSrc = labNormalized(rgb2lab(src.rgb));
        half3 labDst = labNormalized(rgb2lab(dst.rgb));
        return half4(lab2rgb(labDenormalized(blendLinearLight(labDst, labSrc))), 1.0);
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
        // Custom modes (100+): unpremultiplied formulas
        if (mode == 100) return blendLinearLight(bc, bg).xyz1;
        if (mode == 101) { half g = greyscale(ch.rgb); return mix(ch, blendLinearLight(half4(bc.rgb, g), bg), bc.a); }
        if (mode == 102) return half4(blendDifference(bg.rgb, bc.rgb, bc.a), 1.0);
        if (mode == 103) { half g = greyscale(ch.rgb) * 100.0; return mix(ch, labLighten(bg, 0.4 * g + 45.0), bc.a); }
        if (mode == 105) { half g = greyscale(ch.rgb) * 100.0; return mix(ch, labDarken(bg, 45.0 - 0.15 * g), bc.a); }
        if (mode == 106) return mix(ch, labColor(bg, bc.r, bc.g), bc.a);
        if (mode == 107) return mix(ch, blendLinearLightLab(bc, bg), bc.a);
        if (mode == 118) return blendColorDodge(bc, bg).xyz1;
        if (mode == 119) return blendColorBurn(bc, bg).xyz1;
        if (mode == 120) return blendPlusDarker(bc, bg);
        if (mode == 121) return blendPlusLighter(bc, bg);
        if (mode == 200) { half4 r = (1.0 - bc.a) * bg + bc.a * bc; half a = (1.0 - bc.a) * (1.0 - ch.r) + ch.r; return half4(r.xyz * a, 1.0); }
        if (mode == 201) return adjust_saturation(bg, uSaturation);
        if (mode == 202) return adjust_brightness(bg, uBrightness);
        if (mode == 203) return luminance_curve(bg, uLuminanceValues, uLuminanceAmount);
        return blendSrcOver(bc, bg).xyz1;
    }

    // ======== Main ========

    half4 main(float2 fragCoord) {
        half4 currentColor = child.eval(fragCoord);
        int count = int(layerCount);

        for (int i = 0; i < 8; i++) {
            if (i >= count) break;
            half4 layerColor = layerColors[i];
            int mode = int(blendModes[i]);
            // Standard modes produce premultiplied result directly
            if (mode <= 31) {
                currentColor = doBlend(mode, layerColor, currentColor);
            } else {
                half4 blended = getBlendModeColor(currentColor, currentColor, mode, layerColor);
                half3 dstRgb = currentColor.rgb;
                half dstA = currentColor.a;
                half srcA = layerColor.a;
                half3 resultRgb = blended.rgb * srcA + dstRgb * (1.0 - srcA);
                half resultA = srcA + dstA * (1.0 - srcA);
                currentColor = half4(resultRgb, resultA);
            }
        }
        return currentColor;
    }
"""
