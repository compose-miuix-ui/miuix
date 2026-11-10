// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass.internal

import top.yukonga.miuix.kmp.material.agsl.MaterialAgsl

/** Cache key of the compiled refraction shader. */
internal const val GLASS_SHADER_KEY = "MiuixGlass"

/** Cache key of the compiled colour-blend shader. */
internal const val GLASS_COLOR_BLEND_SHADER_KEY = "MiuixGlassColorBlend"

/** Glass material shader applied to the blurred backdrop. */
internal val GLASS_SHADER: String = """
uniform shader child;

uniform float2 in_pad;          // component origin inside the padded layer
uniform float2 in_maxCoord;     // sampling clamp of the padded layer
uniform float4 in_alphaEdge;    // alpha, edge width, thickness, reflect offset
uniform float4 in_iorRefl;      // ior, reflect strength, reflect lighten, colour gamma
uniform float4 in_tint;         // tint rgb, tint strength
uniform float4 in_whiteMixBg;   // colour white, colour mix, backdrop saturation, backdrop brightness
uniform float4 in_darker;       // darker start, darker end, darker, inner bottom
uniform float4 in_lightDir;     // direction xyz, angle range * pi
uniform float4 in_lightAmt;     // intensity, opposite intensity, luminance amount, overspill
uniform float4 in_lumCurve;     // cubic coefficients A, B, C, D
uniform float4 in_satBri;       // saturation, brightness, burn, unshade
uniform float4 in_edgePow;      // edge pow, wide-sample radius

const float3 kCurveWeights = float3(0.2125, 0.7153, 0.0721);
const float3 kShadowTint = float3(0.07874, 0.02848, 0.09278);

// ------------------------------------------------- silhouette and edge profile
${MaterialAgsl.SilhouetteSdf}
${MaterialAgsl.RimLighting}
// ------------------------------------------------------------------ sampling

float4 tapLayer(float2 local) {
    return float4(child.eval(clamp(local + in_pad, float2(0.5), in_maxCoord)));
}

// The rays reach past the silhouette, and the recorded layer only carries backdrop as far as the
// blur padding. A tap beyond it returns transparent black, which would ring the shape in a dark
// halo, so fall back to the nearest tap inside the surface.
float4 sampleBackdrop(float2 local) {
    float4 col = tapLayer(local);
    if (col.a < 0.02) {
        col = tapLayer(clamp(local, float2(0.5), in_size - 0.5));
    }
    if (col.a > 0.004) {
        col = float4(col.rgb / col.a, 1.0);
    }
    float t = smoothstep(in_darker.x, in_darker.y, luma(col.rgb)) * in_darker.z;
    col.rgb = mix(col.rgb, kShadowTint, t);
    return col;
}

// Stands in for the second, far wider blur.
//
// The taps sit on a Vogel spiral, which covers the disc evenly instead of leaving the middle empty;
// a sparse ring would paint the pattern it leaves behind onto a bright object. Taps are averaged by
// coverage, so one that lands past the recorded backdrop contributes nothing rather than pulling
// the colour toward black.
float4 sampleWide(float2 local) {
    float2 outer = in_edgePow.yz;
    if (outer.x <= 0.5 || outer.y <= 0.5) return sampleBackdrop(local);
    // The colour texture is blurred at a radius far larger than any surface it sits on, so across a
    // bar or a button it is very nearly one colour. `in_edgePow.w` says how completely: at 1 the
    // disc is centred on the surface and every pixel reads the same average.
    float2 origin = mix(local, in_size * 0.5, in_edgePow.w);
    float3 sum = float3(0.0);
    float coverage = 0.0;
    for (int i = 0; i < 25; i++) {
        float index = float(i);
        float radius = sqrt((index + 0.5) * 0.04);
        float angle = index * 2.39996323;
        float4 tap = tapLayer(origin + float2(cos(angle), sin(angle)) * radius * outer);
        sum += tap.rgb;
        coverage += tap.a;
    }
    if (coverage < 0.0001) return sampleBackdrop(local);
    float3 color = sum / coverage;
    float t = smoothstep(in_darker.x, in_darker.y, luma(color)) * in_darker.z;
    return float4(mix(color, kShadowTint, t), 1.0);
}

// ------------------------------------------------------------------- grading

float4 adjustColor(float4 color, float saturation, float brightness) {
    float l = dot(color.rgb, kCurveWeights);
    return float4(mix(float3(l), color.rgb, saturation) + float3(brightness * color.a), color.a);
}

float4 luminanceCurve(float4 color) {
    float a = max(color.a, 0.0001);
    float3 straight = color.rgb / a;
    float l = clamp(dot(straight, kCurveWeights), 0.0, 1.0);
    float adjusted = ((in_lumCurve.x * l + in_lumCurve.y) * l + in_lumCurve.z) * l + in_lumCurve.w;
    adjusted = clamp(adjusted, 0.0, 1.0);
    float scale = adjusted / max(l, 0.01) * smoothstep(0.0, 0.1, l);
    return float4(mix(straight, straight * scale, in_lightAmt.z) * a, color.a);
}

float4 processColor(float4 color) {
    color = luminanceCurve(color);
    color.rgb = adjustColor(color, in_satBri.x, in_satBri.y).rgb;
    return color;
}

float4 processGlassColor(float4 material, float2 local) {
    float4 wide = adjustColor(sampleWide(local), in_whiteMixBg.z, in_whiteMixBg.w);
    wide.rgb = mix(wide.rgb, float3(1.0), in_whiteMixBg.x);

    float lumin = clamp(luma(wide.rgb), 0.0, 1.0);
    float burn = pow(lumin, max(in_satBri.z, 0.5)) - 0.5;
    float colorRatio = 0.8 * mix(lumin, 1.0, (1.587 * burn * burn * burn) + 0.5);

    float3 ratio = mix(float3(1.0), wide.rgb, colorRatio);
    float mean = (ratio.r + ratio.g + ratio.b) * 0.33333333;
    // Pull near-grey backdrops all the way to grey so the glass never picks up a false cast.
    ratio = mix(float3(mean), ratio, smoothstep(0.0, 0.4, distance(wide.rgb, float3(mean))));
    ratio = mix(ratio, in_tint.rgb, in_tint.a);

    material.rgb *= ratio;
    material.rgb = mix(material.rgb, ratio, in_whiteMixBg.y * colorRatio);
    return material;
}

float3 addLight(float3 color, float3 lightColor, float strength) {
    float toWhite = smoothstep(0.2, 1.0, distance(float3(1.0), color));
    return color + lightColor * strength * mix(0.3, 1.0, toWhite);
}

// ---------------------------------------------------------------------- main

// The material at one point, given how deep into the rim it is and which way the rim faces.
//
// `d` is passed in rather than measured: the caller walks it across a pixel, and over that distance
// the distance field is a plane, so stepping the value is exact and free.
float4 shadeMaterial(float2 local, float d, float2 gradient, float edge) {
    float depth = clamp(-d / edge, 0.0, 1.0);
    float shaped = edgeCurve(depth, in_edgePow.x);
    // The reflection and the added light ride the same outer third of the band the rim light does.
    // Spread across the whole band they come out as a soft halo several times wider than the edge
    // the edge, because this pass cannot resolve where the turn actually is.
    float nmlZ = edgeCurve(min(depth / kRimBandFraction, 1.0), 1.0);

    bool isFlat = shaped >= 1.0;
    float3 normal = isFlat
        ? float3(0.0, 0.0, 1.0)
        : normalize(float3(gradient * edgeCurveSlope(depth, in_edgePow.x), 1.0));

    float2 refractUv = local;
    if (!isFlat) {
        float3 bent = refract(float3(0.0, 0.0, -1.0), normal, 1.0 / max(in_iorRefl.x, 1.0));
        float thickness = in_alphaEdge.z;
        refractUv += bent.xy * mix((thickness - edge) * 2.0, thickness * 2.0, shaped);
    }
    float4 material = sampleBackdrop(refractUv);

    float rimZ = 1.0 - nmlZ;
    float4 mirrored = float4(0.0);
    if (rimZ > 0.000001) {
        float3 bounced = reflect(float3(0.0, 0.0, -1.0), normal);
        mirrored = sampleBackdrop(local + bounced.xy * (in_alphaEdge.w * (1.0 - shaped)));
        material = mix(material, mirrored, clamp(rimZ * in_iorRefl.y, 0.0, 1.0));
    }
    material.rgb = addLight(material.rgb, mirrored.rgb, (1.0 - shaped) * in_iorRefl.z);

    material = processColor(material);
    material = processGlassColor(material, local);

    // Additive glow along the bottom edge.
    float bottom = smoothstep(1.0, 0.0, 1.0 - local.y / max(in_size.y, 1.0));
    material.rgb += bottom * bottom * in_darker.w;


    material = pow(max(material, float4(0.0)), float4(in_iorRefl.w));
    material.rgb = mix(clamp(material.rgb, 0.0, 1.0), in_tint.rgb, in_satBri.w);
    return material;
}

// ---------------------------------------------------------------------- main

half4 main(float2 coord) {
    float2 local = coord - in_pad;
    float d = silhouetteSdf(local);
    // Overspill the silhouette: the mask pass trims the edge at full resolution, so this one only
    // has to reach past it.
    if (d >= in_lightAmt.w) return half4(0.0);

    // A fully unshaded style is a flat tinted shape; skip the whole material.
    if ((1.0 - in_satBri.w) <= 0.000001) {
        float flatAlpha = in_alphaEdge.x;
        return half4(half3(in_tint.rgb * flatAlpha), half(flatAlpha));
    }

    float edge = max(in_alphaEdge.y, 1.0);
    float2 gradient = sdfGradient(local);
    float4 material;

    // Now that the normal is exact, the lit part of the rim is a few display pixels wide — less
    // than one pixel of this layer. Read once per pixel it would alias into a staircase, so inside
    // the band the material is integrated across the pixel instead. One dimension is enough: the
    // rim varies along the distance field's gradient and is constant across it.
    if (-d < edge && length(gradient) > 0.5) {
        material = shadeMaterial(local - gradient * 0.375, d - 0.375, gradient, edge) * 0.25;
        material += shadeMaterial(local, d, gradient, edge) * 0.5;
        material += shadeMaterial(local + gradient * 0.375, d + 0.375, gradient, edge) * 0.25;
    } else {
        material = shadeMaterial(local, d, gradient, edge);
    }

    float alpha = material.a * in_alphaEdge.x;
    return half4(half3(material.rgb * alpha), half(alpha));
}
"""

/** Colour-blend shader applied to the blurred backdrop. */
internal val GLASS_COLOR_BLEND_SHADER: String = """
uniform shader child;

uniform float4 in_blend0;     // layer 0 colour, straight; w = amount
uniform float4 in_blend1;     // layer 1
uniform float4 in_blend2;     // layer 2
uniform float4 in_blendMode;  // mode of layers 0..2; w = how many layers are live

const float3 kLumWeights = float3(0.3, 0.59, 0.11);

float lumOf(float3 c) {
    return dot(c, kLumWeights);
}

// Pulls a colour back inside the unit cube without moving its luminance, which is what keeps a
// non-separable blend from clipping to a different tone than it computed.
float3 clipColor(float3 c) {
    float l = lumOf(c);
    float n = min(min(c.r, c.g), c.b);
    float x = max(max(c.r, c.g), c.b);
    if (n < 0.0) c = l + (c - l) * l / max(l - n, 0.0001);
    if (x > 1.0) c = l + (c - l) * (1.0 - l) / max(x - l, 0.0001);
    return c;
}

float3 setLum(float3 c, float l) {
    return clipColor(c + (l - lumOf(c)));
}

float softLightChannel(float d, float s) {
    float dd = (d <= 0.25) ? ((16.0 * d - 12.0) * d + 4.0) * d : sqrt(d);
    return (s <= 0.5)
        ? d - (1.0 - 2.0 * s) * d * (1.0 - d)
        : d + (2.0 * s - 1.0) * (dd - d);
}

float3 blendLayer(float3 d, float4 layer, float mode) {
    float3 s = layer.rgb;
    float a = layer.a;
    int m = int(mode + 0.5);
    float3 b;
    if (m == 1) {
        // plus darker: subtractive, so it floors the backdrop instead of greying it
        return clamp(d - a * (1.0 - s), 0.0, 1.0);
    } else if (m == 2) {
        // plus lighter
        return clamp(d + a * s, 0.0, 1.0);
    } else if (m == 3) {
        b = float3(
            softLightChannel(d.r, s.r),
            softLightChannel(d.g, s.g),
            softLightChannel(d.b, s.b)
        );
    } else if (m == 4) {
        // hard light: the layer decides
        b = mix(1.0 - 2.0 * (1.0 - s) * (1.0 - d), 2.0 * s * d, step(s, float3(0.5)));
    } else if (m == 5) {
        // overlay: the backdrop decides
        b = mix(1.0 - 2.0 * (1.0 - d) * (1.0 - s), 2.0 * d * s, step(d, float3(0.5)));
    } else if (m == 6) {
        b = setLum(d, lumOf(s));
    } else if (m == 7) {
        b = min(float3(1.0), d / max(1.0 - s, float3(0.0001)));
    } else if (m == 8) {
        b = 1.0 - min(float3(1.0), (1.0 - d) / max(s, float3(0.0001)));
    } else {
        b = s;
    }
    return clamp(mix(d, b, a), 0.0, 1.0);
}

half4 main(float2 coord) {
    half4 src = child.eval(coord);
    float a = float(src.a);
    if (a <= 0.0) return src;
    float3 d = float3(src.rgb) / a;
    d = blendLayer(d, in_blend0, in_blendMode.x);
    if (in_blendMode.w > 1.5) d = blendLayer(d, in_blend1, in_blendMode.y);
    if (in_blendMode.w > 2.5) d = blendLayer(d, in_blend2, in_blendMode.z);
    return half4(half3(d * a), src.a);
}
"""
