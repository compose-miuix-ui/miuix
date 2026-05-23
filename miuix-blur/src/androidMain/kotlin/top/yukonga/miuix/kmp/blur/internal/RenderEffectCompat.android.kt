// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import top.yukonga.miuix.kmp.blur.RuntimeShader
import top.yukonga.miuix.kmp.blur.asAndroidRuntimeShader

internal actual fun RenderEffect?.chain(other: RenderEffect): RenderEffect = if (this != null) {
    android.graphics.RenderEffect.createChainEffect(
        other.asAndroidRenderEffect(),
        this.asAndroidRenderEffect(),
    ).asComposeRenderEffect()
} else {
    other
}

internal actual fun runtimeShaderEffect(
    runtimeShader: RuntimeShader,
    uniformShaderName: String,
): RenderEffect = android.graphics.RenderEffect.createRuntimeShaderEffect(
    runtimeShader.asAndroidRuntimeShader(),
    uniformShaderName,
).asComposeRenderEffect()
