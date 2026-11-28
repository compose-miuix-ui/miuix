// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.nativeCanvas
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.asAndroidRuntimeShader

internal actual fun Canvas.rejectsTile(bounds: Rect): Boolean = nativeCanvas.quickReject(bounds.left, bounds.top, bounds.right, bounds.bottom)

internal actual fun RenderEffect.withTileOrigin(x: Float, y: Float): RenderEffect {
    val source = android.graphics.RenderEffect.createOffsetEffect(x, y)
    val effect = android.graphics.RenderEffect.createChainEffect(asAndroidRenderEffect(), source)
    return android.graphics.RenderEffect.createOffsetEffect(-x, -y, effect).asComposeRenderEffect()
}

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
