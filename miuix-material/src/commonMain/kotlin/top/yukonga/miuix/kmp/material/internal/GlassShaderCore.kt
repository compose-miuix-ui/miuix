// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.internal

import top.yukonga.miuix.kmp.material.agsl.MaterialAgsl

internal const val GLASS_RIM_SHADER_KEY = "MiuixGlassRim"

/** Rim lighting along the silhouette, at full resolution. */
internal val GLASS_RIM_SHADER: String = """
${MaterialAgsl.SilhouetteSdf}
${MaterialAgsl.RimLighting}
uniform float4 in_rimEdge;      // edge width, edge pow, surface opacity, unused
uniform float4 in_lightDir;     // direction xyz, angle range * pi
uniform float4 in_lightAmt;     // intensity, opposite intensity, unused, unused
uniform float4 in_surface;      // the material's own colour, standing in for what is underneath

half4 main(float2 coord) {
    float d = silhouetteSdf(coord);
    // The light rides the outer third of the rim, not all of it. The whole band is what bends the
    // refraction ray, but a light spread over it reads as a bevel rather than an edge.
    float edge = max(in_rimEdge.x * kRimBandFraction, 1.0);
    // Past 85% of the band the surface is flat and faces away from both lights.
    if (d > 0.5 || -d >= edge * 0.85) return half4(0.0);

    float2 gradient = sdfGradient(coord);
    if (length(gradient) < 0.5) return half4(0.0);

    float depth = clamp(-d / edge, 0.0, 1.0);
    float slope = min(edgeCurveSlope(depth, in_rimEdge.y), kMaxBevelSlope);
    float3 normal = normalize(float3(gradient * slope, 1.0));
    float lighten = rimLighten(normal, in_lightDir, in_lightAmt.xy, in_surface.rgb);
    float3 added = max(liftToward(in_surface.rgb, lighten) - in_surface.rgb, float3(0.0));

    float coverage = clamp(0.5 - d, 0.0, 1.0);
    float3 light = added * (in_rimEdge.z * coverage);
    // Carry an alpha equal to the light, not zero. A shader returns a *premultiplied* colour, and
    // one whose channels sit above its own alpha is clamped away — which is what silently drew
    // nothing for this pass and for the bloom stroke.
    return half4(half3(light), half(clamp(max(max(light.r, light.g), light.b), 0.0, 1.0)));
}
"""

internal const val GLASS_MASK_SHADER_KEY = "MiuixGlassMask"

/** Trims the surface to the silhouette, at full resolution. */
internal val GLASS_MASK_SHADER: String = """
${MaterialAgsl.SilhouetteSdf}
half4 main(float2 coord) {
    // One pixel of coverage either side of the boundary is all the anti-aliasing an edge needs.
    return half4(half(clamp(0.5 - silhouetteSdf(coord), 0.0, 1.0)));
}
"""

internal const val GLASS_SHADOW_SHADER_KEY = "MiuixGlassShadow"

/** Surface shadow cast beyond the silhouette. */
internal val GLASS_SHADOW_SHADER: String = """
${MaterialAgsl.SilhouetteSdf}
uniform float2 in_shadowOffset;  // displacement in pixels
uniform float2 in_shadowShape;   // reach, dispersion
uniform float4 in_shadowColor;   // rgb, strength

half4 main(float2 coord) {
    float d = silhouetteSdf(coord - in_shadowOffset);
    float reach = max(in_shadowShape.x, 0.5);
    float t = clamp(1.0 - d / reach, 0.0, 1.0);
    // Dispersion reshapes the falloff: below a half it hugs the edge, above it spreads and thins.
    float falloff = pow(t, 1.0 / max(in_shadowShape.y, 0.05));
    float a = falloff * in_shadowColor.a;
    return half4(half3(in_shadowColor.rgb * a), half(a));
}
"""
