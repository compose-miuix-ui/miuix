// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asAndroidColorFilter
import androidx.compose.ui.graphics.asComposeRenderEffect
import top.yukonga.miuix.kmp.blur.RuntimeShader
import top.yukonga.miuix.kmp.blur.asAndroidRuntimeShader

@RequiresApi(Build.VERSION_CODES.S)
internal actual fun RenderEffect?.chain(other: RenderEffect): RenderEffect = if (this != null) {
    android.graphics.RenderEffect.createChainEffect(
        other.asAndroidRenderEffect(),
        this.asAndroidRenderEffect(),
    ).asComposeRenderEffect()
} else {
    other
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
internal actual fun runtimeShaderEffect(
    runtimeShader: RuntimeShader,
    uniformShaderName: String,
): RenderEffect = android.graphics.RenderEffect.createRuntimeShaderEffect(
    runtimeShader.asAndroidRuntimeShader(),
    uniformShaderName,
).asComposeRenderEffect()

@RequiresApi(Build.VERSION_CODES.S)
internal actual fun colorFilterEffect(
    renderEffect: RenderEffect?,
    colorFilter: ColorFilter,
): RenderEffect = if (renderEffect != null) {
    android.graphics.RenderEffect.createColorFilterEffect(
        colorFilter.asAndroidColorFilter(),
        renderEffect.asAndroidRenderEffect(),
    ).asComposeRenderEffect()
} else {
    android.graphics.RenderEffect.createColorFilterEffect(
        colorFilter.asAndroidColorFilter(),
    ).asComposeRenderEffect()
}
