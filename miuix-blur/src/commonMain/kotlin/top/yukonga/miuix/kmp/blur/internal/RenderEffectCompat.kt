// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RenderEffect
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
 * Creates a [RenderEffect] that applies a [ColorFilter], optionally chaining
 * onto an existing [renderEffect].
 */
internal expect fun colorFilterEffect(
    renderEffect: RenderEffect? = null,
    colorFilter: ColorFilter,
): RenderEffect
