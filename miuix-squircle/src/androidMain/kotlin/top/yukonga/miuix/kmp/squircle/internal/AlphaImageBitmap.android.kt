// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle.internal

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

internal actual fun makeAlphaImageBitmap(size: Int, alphaBytes: ByteArray): ImageBitmap {
    val pixels = IntArray(size * size)
    for (i in alphaBytes.indices) {
        val a = alphaBytes[i].toInt() and 0xFF
        pixels[i] = (a shl 24) or (a shl 16) or (a shl 8) or a
    }
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    bitmap.setPixels(pixels, 0, size, 0, 0, size, size)
    return bitmap.asImageBitmap()
}
