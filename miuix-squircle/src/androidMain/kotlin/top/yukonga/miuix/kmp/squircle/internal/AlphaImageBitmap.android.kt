// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle.internal

import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapShader
import android.graphics.Shader.TileMode
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import top.yukonga.miuix.kmp.shader.isRuntimeShaderSupported
import java.nio.ByteBuffer

internal actual fun makeAlphaImageBitmap(size: Int, alphaBytes: ByteArray): ImageBitmap {
    val bitmap = createBitmap(size, size, Bitmap.Config.ALPHA_8)
    bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(alphaBytes))
    return bitmap.asImageBitmap()
}

internal actual fun makeLinearShader(image: ImageBitmap): Shader = BitmapShader(image.asAndroidBitmap(), TileMode.CLAMP, TileMode.CLAMP).apply {
    if (isRuntimeShaderSupported()) {
        filterMode = BitmapShader.FILTER_MODE_LINEAR
    }
}
