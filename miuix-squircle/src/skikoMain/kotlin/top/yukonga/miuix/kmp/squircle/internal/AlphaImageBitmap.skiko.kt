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
        colorInfo = ColorInfo(ColorType.RGBA_8888, ColorAlphaType.PREMUL, null),
        width = size,
        height = size,
    )
    val rowBytes = size * 4
    val pixels = ByteArray(size * size * 4)
    for (i in alphaBytes.indices) {
        val a = alphaBytes[i]
        val base = i * 4
        pixels[base] = a
        pixels[base + 1] = a
        pixels[base + 2] = a
        pixels[base + 3] = a
    }
    val bitmap = Bitmap()
    bitmap.allocPixels(info)
    bitmap.installPixels(info, pixels, rowBytes)
    return Image.makeFromBitmap(bitmap).toComposeImageBitmap()
}
