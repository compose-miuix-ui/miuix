// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.RenderEffect
import top.yukonga.miuix.kmp.material.RuntimeShader

internal expect fun Canvas.rejectsTile(bounds: Rect): Boolean

/** Runs the effect in full-surface coordinates with tile-local input and output. */
internal expect fun RenderEffect.withTileOrigin(x: Float, y: Float): RenderEffect

/**
 * Chains [other] after this [RenderEffect]. If this is null, returns [other] directly.
 */
internal expect fun RenderEffect?.chain(other: RenderEffect): RenderEffect

/** Creates a [RenderEffect] whose [RuntimeShader] reads its input from [uniformShaderName]. */
internal expect fun runtimeShaderEffect(
    runtimeShader: RuntimeShader,
    uniformShaderName: String,
): RenderEffect
