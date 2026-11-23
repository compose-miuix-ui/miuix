// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle.internal

import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.asComposeShader
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorInfo
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import org.jetbrains.skia.SamplingMode

internal actual fun makeLinearAlphaShader(size: Int, alphaBytes: ByteArray): Shader {
    val info = ImageInfo(
        colorInfo = ColorInfo(ColorType.ALPHA_8, ColorAlphaType.UNPREMUL, null),
        width = size,
        height = size,
    )
    return Image.makeRaster(info, alphaBytes, size)
        .makeShader(FilterTileMode.CLAMP, FilterTileMode.CLAMP, SamplingMode.LINEAR, null)
        .asComposeShader()
}
