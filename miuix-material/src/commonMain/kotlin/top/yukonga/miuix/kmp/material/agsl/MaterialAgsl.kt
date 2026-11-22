// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.agsl

/**
 * AGSL fragments shared by the material shaders. Both are written against the same uniforms —
 * `in_size`, `in_radii` (top-left, top-right, bottom-right, bottom-left) and `in_smoothing` — so one
 * shader can include either or both.
 */
object MaterialAgsl {
    /** Continuous-corner tile size as a multiple of the corner radius. */
    private const val TILE_SCALE = 1.5286649465560913f

    private const val FIT_4 = -0.7391197269f
    private const val FIT_3 = 2.4034927648f
    private const val FIT_2 = -2.4907319173f
    private const val FIT_1 = 0.4768708960f
    private const val FIT_0 = 0.4747847594f

    /** Minimum continuous-corner radius; smaller radii use circular corners. */
    private const val MIN_SUPERCIRCLE_RADIUS = 0.5f

    /**
     * Silhouette distance field: a rounded box whose corners can turn continuous.
     *
     * Provides `roundedBoxSdf`, `supercircleSdf`, `pickRadius`, `sdfShape` and `silhouetteSdf`
     * (negative inside the shape).
     */
    val SilhouetteSdf: String = """
uniform float2 in_size;         // component size
uniform float4 in_radii;        // topLeft, topRight, bottomRight, bottomLeft
uniform float in_smoothing;     // 0 = circular corner, 1 = continuous corner

float roundedBoxSdf(float2 p, float2 b, float r) {
    float2 d = p - b + r;
    return min(max(d.x, d.y), 0.0) + length(max(d, float2(0.0))) - r;
}

float supercircleSdf(float2 off, float tile) {
    float2 q = max(float2(0.0), (float2(tile) + off) / tile);
    float hi = max(q.x, q.y);
    float ratio = (hi == 0.0) ? 0.0 : clamp(min(q.x, q.y) / hi, 0.0, 1.0);
    float fit = (((($FIT_4 * ratio + $FIT_3) * ratio + $FIT_2) * ratio + $FIT_1) * ratio + $FIT_0);
    float len = length(q);
    float distBase = (len + 1.0) - 1.0 / (1.0 - ratio * ratio * clamp(len, 0.0, 1.0) * fit);
    return min(max(tile + off.x, tile + off.y), 0.0) + tile * (distBase - 1.0);
}

float pickRadius(float2 local, float2 b) {
    float top = (local.x < b.x) ? in_radii.x : in_radii.y;
    float bottom = (local.x < b.x) ? in_radii.w : in_radii.z;
    return min((local.y < b.y) ? top : bottom, min(b.x, b.y));
}

float sdfShape(float2 p, float2 b, float r) {
    float box = roundedBoxSdf(p, b, r);
    float minHalf = min(b.x, b.y);
    if (in_smoothing <= 0.001 || r < $MIN_SUPERCIRCLE_RADIUS || (minHalf - r) <= 1.0) {
        return box;
    }
    float tile = min($TILE_SCALE * r, minHalf);
    return mix(box, supercircleSdf(p - b, tile), in_smoothing);
}

// Distance to the silhouette from a point in component space. Negative inside.
float silhouetteSdf(float2 local) {
    float2 b = in_size * 0.5;
    return sdfShape(abs(local - b), b, pickRadius(local, b));
}
"""

    /**
     * Rim curvature and directional lighting along the silhouette field.
     *
     * Provides `luma`, `smooth5Map`, `edgeCurve`, `edgeCurveSlope`, `sdfGradient`, `liftToward`,
     * `dynamicAdd`, `lightFalloff` and `rimLighten`.
     */
    val RimLighting: String = """
const float3 kLumaWeights = float3(0.2126, 0.7152, 0.0722);

float luma(float3 color) {
    return dot(color, kLumaWeights);
}

float smooth5Map(float t) {
    t = clamp(mix(0.5, 1.0, t), 0.0, 1.0);
    t = t * t * t * (t * (t * 6.0 - 15.0) + 10.0);
    return (t - 0.5) * 2.0;
}

// Depth 0 at the silhouette, 1 once the surface is flat. `p` shapes how fast it gets there.
float edgeCurve(float x, float p) {
    if (x >= 0.85) return 1.0;
    float c = smooth5Map(sqrt(clamp(x, 0.0, 1.0)));
    return 1.0 - pow(1.0 - c, p);
}

// Slope of `edgeCurve` in `x`.
//
// The rim profile is differentiated in closed form. A one-pixel step would be a sixth of the band
// on the downscaled layer this pass runs on, and a step that coarse averages the turn away: the
// tilt it reports inside the band comes out several times the real one, and the rim reads as a dome
// rather than an edge.
float edgeCurveSlope(float x, float p) {
    if (x >= 0.85) return 0.0;
    float t = sqrt(clamp(x, 0.000001, 1.0));
    float u = 0.5 + 0.5 * t;
    float um = u - 1.0;
    float c = smooth5Map(t);
    // d(smooth5Map(sqrt x))/dx, the quintic's derivative carried through the square root.
    float slope = 15.0 * u * u * um * um / t;
    return p * pow(max(1.0 - c, 0.0), p - 1.0) * slope;
}

// Direction the distance field grows in: a unit vector pointing out of the shape. The unshaped
// field is locally a plane, so a half-pixel difference reads it exactly.
float2 sdfGradient(float2 local) {
    float dx = silhouetteSdf(local + float2(0.5, 0.0)) - silhouetteSdf(local - float2(0.5, 0.0));
    float dy = silhouetteSdf(local + float2(0.0, 0.5)) - silhouetteSdf(local - float2(0.0, 0.5));
    float2 gradient = float2(dx, dy);
    float length2d = length(gradient);
    return (length2d < 0.0001) ? float2(0.0) : gradient / length2d;
}

// Lifts a colour toward white without clipping: the gain is applied around a luminance-dependent
// pivot, so bright pixels move less than dark ones.
float3 liftToward(float3 color, float amount) {
    float v = luma(color);
    float w = smoothstep(0.0, 0.5, v);
    float k = mix(1.0 - v, v, w);
    float gain = 1.0 + smoothstep(0.0, 1.0, amount) * mix(0.75, 0.4, w);
    return (color + k) * gain - k;
}

float dynamicAdd(float3 color) {
    float toWhite = smoothstep(0.2, 1.0, distance(float3(1.0), color));
    return mix(0.8, mix(0.2, 1.0, toWhite), luma(color));
}

// acos is not worth its cost here; this is the usual sqrt-based polynomial fit.
float lightFalloff(float3 normal, float3 direction, float intensity, float angleRange) {
    float dp = dot(normal, direction);
    float d = clamp(dp, -1.0, 1.0);
    float u = 1.0 - d;
    float positive = sqrt(u) * (u * (u * 0.04587603 + 0.10884186) + 1.41502593);
    float angle = (d < 0.0) ? (3.14159265 - positive) : positive;
    return max(dp, 0.0) * max(intensity * (1.0 - angle / max(angleRange, 0.0001)), 0.0);
}

// How much light the rim carries where neither directional light reaches. Calibrated so the sides
// between the two lit arcs come out about a dozen levels above the surface.
const float kEdgeAmbient = 0.24;

// How much of the rim band the light rides, measured from the silhouette inward. The band itself
// is what bends the refraction ray; the highlight drawn on it is a hairline.
const float kRimBandFraction = 0.33;

// How far the two lights lift a surface of `color`, in `[0, 1]`. The second light points the
// opposite way in x and y, so one direction lights both sides of the shape.
float rimLighten(float3 normal, float4 lightDir, float2 amounts, float3 color) {
    float3 lit = float3(normal.x, -normal.y, normal.z);
    float primary = lightFalloff(lit, lightDir.xyz, amounts.x, lightDir.w);
    float opposite = lightFalloff(lit, lightDir.xyz * float3(-1.0, -1.0, 1.0), amounts.y, lightDir.w);
    // Two opposed lights alone leave two lit arcs and two dark ones. An ambient term proportional
    // to how far the rim has turned away from the viewer joins them up.
    float edgeFactor = clamp(length(lit.xy), 0.0, 1.0);
    float raw = clamp(primary + opposite + kEdgeAmbient * edgeFactor, 0.0, 1.0) * dynamicAdd(color);
    raw = pow(clamp(raw, 0.0, 1.0), 0.85);
    // A soft knee, tighter over a bright backdrop: full light lifts the rim about half way, never
    // to white. Without it the whole edge band clips and the hairline has nothing left to sit on.
    float knee = mix(1.0, 0.7, smoothstep(0.0, 0.5, luma(color)));
    return raw / (raw + knee);
}

// The steepest the bevel is allowed to get, as a rise over a run.
//
// The closed-form slope runs to the thousands at the silhouette, which stands the normal on end and
// hands both lights everything they can take. The cap keeps the highlight a hairline.
const float kMaxBevelSlope = 1.7;
"""
}
