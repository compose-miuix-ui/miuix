// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.internal

import androidx.compose.ui.graphics.BlendMode
import top.yukonga.miuix.kmp.material.EdgePeak
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

internal const val GLASS_EDGE_SHADER_KEY = "MiuixGlassEdge"

/** Cache key of the fused edge pass for [peak]. */
internal fun glassEdgeShaderKey(peak: EdgePeak): String = GLASS_EDGE_SHADER_KEY + peak.name

/**
 * Rim light and edge stroke in one pass, as AGSL source.
 *
 * Both are additive light along the silhouette read from the same distance field, so one pass does
 * the work of two. [BlendMode.Plus] on both.
 *
 * The mask still runs after this pass, so the rim picks up its coverage here instead of after: a
 * sub-pixel difference confined to the anti-aliased ring.
 */
internal fun glassEdgeShaderSource(peak: EdgePeak): String {
    val axisUniforms = if (peak == EdgePeak.Axial) {
        """
uniform float2 axis1;
uniform float2 axis2;
"""
    } else {
        ""
    }
    return """
${MaterialAgsl.SilhouetteSdf}
${MaterialAgsl.RimLighting}
uniform float2 in_halfViewFloor; // half size, floored, so the fold lands on a pixel boundary
uniform float2 in_strokeBand;    // stroke width, bevel
uniform float4 in_strokeColor;   // stroke rgb, stroke opacity
uniform float in_strokeAlpha;    // stroke opacity multiplier
uniform float4 in_light1;        // direction xyz, intensity
uniform float4 in_light1Color;   // colour rgb, unused
uniform float4 in_light2;        // direction xyz, intensity
uniform float4 in_light2Color;   // colour rgb, unused
$axisUniforms
uniform float4 in_rimEdge;      // edge width, edge pow, surface opacity, unused
uniform float4 in_lightDir;     // direction xyz, angle range * pi
uniform float4 in_lightAmt;     // intensity, opposite intensity, unused, unused
uniform float4 in_surface;      // the material's own colour, standing in for what is underneath

${edgeStrokeNormalSource()}
${edgeStrokeLightsSource(peak)}

half4 main(float2 coord) {
    float d = silhouetteSdf(coord);
    float cov = clamp(0.5 - d, 0.0, 1.0);

    float2 half2d = in_size * 0.5;
    float2 folded = abs(coord - half2d);
    float radius = pickRadius(coord, half2d);
    float inner = max(in_strokeBand.y, 0.5);
    float bevel = max(radius, inner);
    float edge = max(in_rimEdge.x * kRimBandFraction, 1.0);

    // The stroke lights the band and a ring outside it, the rim a hairline of the band; everything
    // else is the flat interior.
    bool strokeInterior = folded.x < half2d.x - bevel && folded.y < half2d.y - bevel;
    bool rimInterior = d > 0.5 || -d >= edge * 0.85;
    if (strokeInterior && rimInterior) return half4(0.0);

    float3 rgb = float3(0.0);
    float alpha = 0.0;

    if (!strokeInterior) {
        float width = max(in_strokeBand.x, 0.75);
        float band = smoothstep(-width, -width + 1.0, d);
        float3 base = in_strokeColor.rgb * (in_strokeColor.a * band * band);
        float3 normal = rimNormal(coord, half2d, d, radius, inner);
        float3 light = (base + edgeStrokeLights(normal)) * (in_strokeAlpha * cov) * cov;
        rgb += light;
        alpha += clamp(max(max(light.r, light.g), light.b), 0.0, 1.0);
    }

    if (!rimInterior) {
        float2 gradient = sdfGradient(coord);
        if (length(gradient) >= 0.5) {
            float depth = clamp(-d / edge, 0.0, 1.0);
            float slope = min(edgeCurveSlope(depth, in_rimEdge.y), kMaxBevelSlope);
            float3 normal = normalize(float3(gradient * slope, 1.0));
            float lighten = rimLighten(normal, in_lightDir, in_lightAmt.xy, in_surface.rgb);
            float3 added = max(liftToward(in_surface.rgb, lighten) - in_surface.rgb, float3(0.0));
            float3 light = added * (in_rimEdge.z * cov);
            rgb += light;
            alpha += clamp(max(max(light.r, light.g), light.b), 0.0, 1.0);
        }
    }

    // Premultiplied; the alpha carries the sum of both lights, as the two passes did.
    return half4(half3(rgb), half(clamp(alpha, 0.0, 1.0)));
}
"""
}

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
