// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.blur.RuntimeShader
import top.yukonga.miuix.kmp.blur.RuntimeShaderCache

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
 * Builds the multi-level progressive composite [RenderEffect]: a sharp base plus three graduated
 * native Gaussian levels of the same source, cross-faded along the two-point gradient `(ax, ay)`
 * between [projFull] and [projZero] — [PROGRESSIVE_COMPOSITE_SHADER]'s continuous-LOD ramp. Skiko
 * mixes the levels in one multi-child runtime shader; Android builds the equivalent blend-mode DAG.
 *
 * [preEffect] wraps each Gaussian level's input (`pre → blur`); [postEffect] applies to the
 * composite output, faded by the continuous `1 − raw` ramp — weighting it per level instead would
 * hold it flat for two thirds of the band (a visible knee). The sharp base stays untouched, so
 * every effect fades out with the blur toward the clear end.
 *
 * Returns null only where the platform cannot express the composite; callers then fall back to
 * the uniform draw path.
 */
internal expect fun progressiveCompositeEffect(
    shaderCache: RuntimeShaderCache,
    sigmaX: Float,
    sigmaY: Float,
    ax: Float,
    ay: Float,
    projFull: Float,
    projZero: Float,
    curve: Float,
    preEffect: RenderEffect?,
    postEffect: RenderEffect?,
): RenderEffect?
