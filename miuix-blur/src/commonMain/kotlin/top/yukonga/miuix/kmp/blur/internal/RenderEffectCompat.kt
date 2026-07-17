// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import top.yukonga.miuix.kmp.blur.RuntimeShader

/**
 * Chains [other] after this [RenderEffect]. If this is null, returns [other] directly.
 */
internal expect fun RenderEffect?.chain(other: RenderEffect): RenderEffect

/**
 * Creates a [RenderEffect] from a [RuntimeShader] that reads its input image
 * from the uniform named [uniformShaderName].
 */
internal expect fun runtimeShaderEffect(
    runtimeShader: RuntimeShader,
    uniformShaderName: String,
): RenderEffect

/**
 * Builds the downscaled progressive **level stack** [RenderEffect]: the lightest Gaussian level as
 * an unmasked base with the two stronger levels band-masked and SrcOver-stacked on top ≡ the first
 * two mix segments of [PROGRESSIVE_COMPOSITE_SHADER]; the ramp's final blend to sharp is drawn
 * separately as a full-resolution overlay (see `createProgressiveSharpOverlayEffect`) so only that
 * band pays full-res cost. Skiko mixes the levels in one multi-child runtime shader; Android
 * builds the equivalent blend-mode DAG.
 *
 * Per-level sigmas are box-compensated, in the downscaled layer's space (0 skips that level's
 * blur); the band is in downscaled, padded px. [preEffect] wraps each level's input
 * (`pre → blur`); [postEffect] applies to the stack output, faded by the continuous `1 − raw`
 * ramp — weighting it per level instead would hold it flat for two thirds of the band (a visible
 * knee). The sharp overlay stays untouched, so every effect fades out with the blur.
 *
 * [nativeSharpEnd] binds the ramp's clear end to the (pre-chained) source instead of leaving the
 * last mix segment degenerate: at full resolution (exp == 0) the source is already native pixels,
 * so the blend to sharp completes in-stack and the caller skips the sharp overlay. Implementations
 * must keep the stack's exact profile (same masks, same segment stops) — an approximate profile
 * visibly pops when an animating radius crosses the downscale boundary.
 *
 * Returns null only where the platform cannot express the stack; callers then fall back to the
 * uniform draw path.
 *
 * [scope] supplies the shader cache plus [BackdropEffectScopeImpl.progressiveMaskScratch] for
 * radius-independent platform caches.
 */
internal expect fun progressiveStackEffect(
    scope: BackdropEffectScopeImpl,
    sigma0X: Float,
    sigma0Y: Float,
    sigma1X: Float,
    sigma1Y: Float,
    sigma2X: Float,
    sigma2Y: Float,
    ax: Float,
    ay: Float,
    projFull: Float,
    projZero: Float,
    curve: Float,
    preEffect: RenderEffect?,
    postEffect: RenderEffect?,
    nativeSharpEnd: Boolean,
): RenderEffect?
