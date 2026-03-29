// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.asSkiaColorFilter
import org.jetbrains.skia.ImageFilter
import top.yukonga.miuix.kmp.blur.RuntimeShader
import top.yukonga.miuix.kmp.blur.asSkikoRuntimeShader

internal actual fun RenderEffect?.chain(other: RenderEffect): RenderEffect = if (this != null) {
    ImageFilter.makeCompose(
        other.asSkiaImageFilter(),
        this.asSkiaImageFilter(),
    ).asComposeRenderEffect()
} else {
    other
}

internal actual fun runtimeShaderEffect(
    runtimeShader: RuntimeShader,
    uniformShaderName: String,
): RenderEffect = ImageFilter.makeRuntimeShader(
    runtimeShader.asSkikoRuntimeShader(),
    uniformShaderName,
    null,
).asComposeRenderEffect()

internal actual fun colorFilterEffect(
    renderEffect: RenderEffect?,
    colorFilter: ColorFilter,
): RenderEffect = ImageFilter.makeColorFilter(
    colorFilter.asSkiaColorFilter(),
    renderEffect?.asSkiaImageFilter(),
    null,
).asComposeRenderEffect()
