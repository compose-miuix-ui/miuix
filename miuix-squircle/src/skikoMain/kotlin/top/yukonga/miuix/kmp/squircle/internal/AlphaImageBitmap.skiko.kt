// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle.internal

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorInfo
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo

internal actual fun makeAlphaImageBitmap(size: Int, alphaBytes: ByteArray): ImageBitmap {
    val info = ImageInfo(
        colorInfo = ColorInfo(ColorType.ALPHA_8, ColorAlphaType.UNPREMUL, null),
        width = size,
        height = size,
    )
    val bitmap = Bitmap()
    bitmap.allocPixels(info)
    bitmap.installPixels(info, alphaBytes, size)
    return Image.makeFromBitmap(bitmap).toComposeImageBitmap()
}
