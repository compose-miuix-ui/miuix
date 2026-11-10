// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import top.yukonga.miuix.kmp.material.RuntimeShader

/**
 * Chains [other] after this [RenderEffect]. If this is null, returns [other] directly.
 */
internal expect fun RenderEffect?.chain(other: RenderEffect): RenderEffect

/** Creates a [RenderEffect] whose [RuntimeShader] reads its input from [uniformShaderName]. */
internal expect fun runtimeShaderEffect(
    runtimeShader: RuntimeShader,
    uniformShaderName: String,
): RenderEffect

/**
 * Builds the downscaled progressive **level stack** [RenderEffect]: the lightest level as an
 * unmasked base with the two stronger ones band-masked and SrcOver-stacked on top (≡ the first two
 * mix segments of [PROGRESSIVE_COMPOSITE_SHADER]); the final blend to sharp is a separate
 * full-resolution overlay, so only that band pays full-res cost. Skiko mixes the levels in one
 * multi-child shader; Android builds the equivalent blend-mode DAG.
 *
 * Per-level sigmas are box-compensated in the downscaled layer's space (0 skips a level), the band
 * is in downscaled padded px. [preEffect] wraps each level's input; [postEffect] applies to the
 * stack output faded by the continuous `1 − raw` ramp (weighting it per level would hold it flat for
 * two thirds of the band — a visible knee). The sharp overlay stays untouched, so every effect fades
 * out with the blur.
 *
 * [nativeSharpEnd] binds the clear end to the pre-chained source, so at exp 0 the blend to sharp
 * completes in-stack and the caller skips the overlay. Implementations must keep the stack's exact
 * profile (masks and segment stops): an approximate one pops when an animating radius crosses the
 * downscale boundary. Returns null only where the platform can't express the stack.
 *
 * [scope] supplies the shader cache plus [BackdropEffectScopeImpl.progressiveMaskScratch].
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
