// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

/**
 * LM Gaussian blur shader — N-tap symmetric bidirectional sampling.
 * Uses up to 7 tap pairs (14 samples total) with precomputed offsets and weights.
 * Applied twice (horizontal then vertical) for separable 2D convolution.
 *
 * Uniforms:
 * - `child`: Input image shader.
 * - `in_blurOffset[14]`: Sampling offsets. [0..6]=X components, [7..13]=Y components.
 * - `in_blurWeight[7]`: Sampling weights for each tap pair.
 * Edge handling: Out-of-bounds samples return black. We mirror the
 * offset to reflect back into the texture.
 */
internal const val LM_GAUSSIAN_BLUR_SHADER = """
    uniform shader child;
    uniform float in_blurOffset[14];
    uniform float in_blurWeight[7];
    uniform float2 in_texSize;

    // Mirror coordinate into valid texture range.
    // Clamps to [0.5, size-0.5] (pixel centers) to avoid sampling
    // beyond the texture boundary where GPUs may return black.
    float2 mirror(float2 coord, float2 size) {
        float2 limit = size - 0.5;
        coord = abs(coord);
        coord = limit - abs(coord - limit);
        return clamp(coord, float2(0.5), limit);
    }

    half4 main(float2 xy) {
        half4 color = half4(0);
        for (int i = 0; i < 7; i++) {
            float2 offset = float2(in_blurOffset[i], in_blurOffset[i + 7]);
            float2 c1 = mirror(xy + offset, in_texSize);
            float2 c2 = mirror(xy - offset, in_texSize);
            color += (child.eval(c1) + child.eval(c2)) * in_blurWeight[i];
        }
        // Un-premultiply alpha to recover the true blurred color, then force opaque.
        if (color.a > 0.001) {
            return half4(color.rgb / color.a, 1.0);
        }
        return color;
    }
"""

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
 * Custom blend mode shader — complete `getBlendModeColor` dispatch.
 *
 * Supports all custom blend modes (100-121, 200-203) including
 * Lab color space operations, linear light blending, and alpha-aware
 * plus darker/lighter operations.
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

    // ======== SrcOver compositing ========

    half4 blendSrcOver(half4 src, half4 dst) {
        if (src.a == 0.0) return dst;
        half srcAlpha = src.a;
        half dstAlpha = dst.a * (1.0 - srcAlpha);
        half outAlpha = srcAlpha + dstAlpha;
        if (outAlpha == 0.0) return half4(0, 0, 0, 0);
        half4 outColor = (src * srcAlpha + dst * dstAlpha) / outAlpha;
        return half4(outColor.rgb, outAlpha);
    }

    // ======== Color Burn ========

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

    // ======== Color Dodge ========

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

    // ======== Linear Dodge / Burn / Light ========

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

    // ======== Greyscale ========

    half greyscale(half3 color) { return 0.3 * color.r + 0.59 * color.g + 0.11 * color.b; }

    // ======== Lab Color Space (sRGB ↔ XYZ ↔ Lab, D65) ========

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

    // ======== Lab Blend Functions ========

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

    // ======== Difference ========

    half3 blendDifference(half3 base, half3 blend) { return abs(base - blend); }
    half3 blendDifference(half3 base, half3 blend, half opacity) {
        return (blendDifference(base, blend) * opacity + base * (1.0 - opacity));
    }

    // ======== Plus Darker / Lighter (V2 alpha-aware) ========

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

    // ======== Color Adjustment (modes 201-203) ========

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

    // ======== Main Dispatch ========

    half4 getBlendModeColor(half4 bg, half4 ch, int mode, half4 bc) {
        if (mode == 3)   return blendSrcOver(bc, bg).xyz1;
        if (mode == 100) return blendLinearLight(bc, bg).xyz1;
        if (mode == 101) { half g = greyscale(ch.rgb); return mix(ch, blendLinearLight(half4(bc.rgb, g), bg), bc.a); }
        if (mode == 102) return half4(blendDifference(bg.rgb, bc.rgb, bc.a), 1.0);
        if (mode == 103) { half g = greyscale(ch.rgb) * 100.0; return mix(ch, labLighten(bg, 0.4 * g + 45.0), bc.a); }
        if (mode == 105) { half g = greyscale(ch.rgb) * 100.0; return mix(ch, labDarken(bg, 45.0 - 0.15 * g), bc.a); }
        if (mode == 106) return mix(ch, labColor(bg, bc.r, bc.g), bc.a);
        if (mode == 107) return mix(ch, blendLinearLightLab(bc, bg), bc.a);
        if (mode == 118) return blendColorDodge(bc, bg);
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
            half4 blended = getBlendModeColor(currentColor, currentColor, mode, layerColor);
            half3 dstRgb = currentColor.rgb;
            half dstA = currentColor.a;
            half srcA = layerColor.a;
            half3 resultRgb = blended.rgb * srcA + dstRgb * (1.0 - srcA);
            half resultA = srcA + dstA * (1.0 - srcA);
            currentColor = half4(resultRgb, resultA);
        }
        return currentColor;
    }
"""
