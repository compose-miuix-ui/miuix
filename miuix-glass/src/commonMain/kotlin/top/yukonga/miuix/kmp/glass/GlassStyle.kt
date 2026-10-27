// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Complete description of a glass material.
 *
 * @property blend Luminance, saturation, brightness, and shadow treatment applied to the refracted
 *   backdrop.
 * @property inner Tint, inner glow, and final gamma of the glass body.
 * @property edge Rim geometry, including lit-band width and curvature.
 * @property reflect Reflection contribution mixed into the refraction result.
 * @property light Directional lights used to shade the rim.
 * @property refract Index of refraction for the glass body.
 * @property background Wide-blur backdrop treatment supplying low-frequency colour.
 * @property blur Backdrop blur radii sampled by the material.
 */
@Immutable
data class GlassStyle(
    val blend: GlassBlend,
    val inner: GlassInner,
    val edge: GlassEdge,
    val reflect: GlassReflect,
    val light: GlassLight,
    val refract: GlassRefract,
    val background: GlassBackground,
    val blur: GlassBlur,
)

/**
 * Colour treatment of the refracted backdrop.
 *
 * @property curveA Cubic term of the luminance curve.
 * @property curveB Quadratic term of the luminance curve.
 * @property curveC Linear term of the luminance curve.
 * @property curveD Constant term of the luminance curve.
 * @property amount How far the curve result mixes in. 0 leaves luminance unchanged.
 * @property saturation Saturation multiplier. 1 leaves saturation unchanged.
 * @property brightness Additive brightness. 0 leaves brightness unchanged.
 * @property darker Strength of the deep-shadow compression toward a near-black violet.
 * @property darkerStart Luminance at which the compression starts.
 * @property darkerEnd Luminance at which the compression reaches [darker].
 */
@Immutable
data class GlassBlend(
    val curveA: Float,
    val curveB: Float,
    val curveC: Float,
    val curveD: Float,
    val amount: Float,
    val saturation: Float,
    val brightness: Float,
    val darker: Float,
    val darkerStart: Float,
    val darkerEnd: Float,
)

/**
 * Colour and opacity treatment of the glass body.
 *
 * @property bottom Strength of additive glow along the bottom edge.
 * @property tint Glass colour. Its alpha is ignored; [tintStrength] controls the amount.
 * @property tintStrength Amount of backdrop colour moved toward [tint]. Values above 1 are valid.
 * @property colorWhite Amount of wide-blur backdrop moved toward white before tinting.
 * @property colorMix Amount of finished glass colour replacing the refracted backdrop.
 * @property colorPow Final gamma. Values above 1 darken; values below 1 lift.
 * @property alpha Base material opacity.
 */
@Immutable
data class GlassInner(
    val bottom: Float,
    val tint: Color,
    val tintStrength: Float,
    val colorWhite: Float,
    val colorMix: Float,
    val colorPow: Float,
    val alpha: Float,
)

/**
 * Rim geometry, in pixels.
 *
 * @property width Width of the rim band.
 * @property pow Rim-profile curvature exponent. 1 is a soft dome; higher values keep the surface
 *   flat longer before turning at the silhouette.
 * @property thickness Glass thickness controlling refraction displacement.
 * @property reflectOffset Reflection-ray displacement; larger values sample more distant colour.
 */
@Immutable
data class GlassEdge(
    val width: Float,
    val pow: Float,
    val thickness: Float,
    val reflectOffset: Float,
)

/**
 * Reflection-ray parameters.
 *
 * @property lighten Additive reflection strength.
 * @property strength Reflection amount mixed into refraction at the silhouette.
 */
@Immutable
data class GlassReflect(
    val lighten: Float,
    val strength: Float,
)

/**
 * Directional lights used to shade the rim. The second light points opposite the first in X and Y.
 *
 * @property directionX X component of the main light direction.
 * @property directionY Y component of the main light direction.
 * @property directionZ Z component of the main light direction.
 * @property intensity Main light strength.
 * @property oppositeIntensity Opposite light strength.
 * @property angleRange Angular falloff range as a multiple of pi.
 */
@Immutable
data class GlassLight(
    val directionX: Float,
    val directionY: Float,
    val directionZ: Float,
    val intensity: Float,
    val oppositeIntensity: Float,
    val angleRange: Float,
)

/**
 * Index of refraction for the glass body.
 *
 * @property ior Index of refraction. 1 bends nothing; 1.5 approximates window glass.
 */
@Immutable
data class GlassRefract(
    val ior: Float,
)

/**
 * Treatment of the wide-blur backdrop supplying low-frequency glass colour.
 *
 * @property saturation Saturation multiplier. 0 produces grey.
 * @property brightness Additive brightness.
 * @property burn Colour-burn exponent. Values below 0.5 are raised to 0.5.
 * @property unShade Amount collapsed to a flat [GlassInner.tint]. 1 removes material shading.
 */
@Immutable
data class GlassBackground(
    val saturation: Float,
    val brightness: Float,
    val burn: Float,
    val unShade: Float,
)

/**
 * Backdrop blur radii, in pixels at [GlassDefaults.SourceDensity].
 *
 * @property small Refraction blur radius; it preserves structure for the rim's bent backdrop.
 * @property big Colour blur radius; it supplies low-frequency tint.
 */
@Immutable
data class GlassBlur(
    val small: Float,
    val big: Float,
)
