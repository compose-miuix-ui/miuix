// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.skiaCanvas
import androidx.compose.ui.graphics.skiaImageFilter
import org.jetbrains.skia.ImageFilter
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.asSkikoRuntimeShader

internal actual fun Canvas.rejectsTile(bounds: Rect): Boolean {
    val clip = skiaCanvas.localClipBounds
    return clip.isEmpty || bounds.right <= clip.left || bounds.bottom <= clip.top || bounds.left >= clip.right || bounds.top >= clip.bottom
}

internal actual fun RenderEffect.withTileOrigin(x: Float, y: Float): RenderEffect {
    val source = ImageFilter.makeOffset(x, y, null, null)
    val effect = ImageFilter.makeCompose(skiaImageFilter, source)
    return ImageFilter.makeOffset(-x, -y, effect, null).asComposeRenderEffect()
}

internal actual fun RenderEffect?.chain(other: RenderEffect): RenderEffect = if (this != null) {
    ImageFilter.makeCompose(
        other.skiaImageFilter,
        this.skiaImageFilter,
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
