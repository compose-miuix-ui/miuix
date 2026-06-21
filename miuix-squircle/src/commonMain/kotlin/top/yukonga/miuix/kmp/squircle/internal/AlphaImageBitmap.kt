// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle.internal

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Creates a [size]×[size] alpha-only [ImageBitmap] from [alphaBytes] (one byte per pixel). The value
 * lives in the alpha channel only — consumers must read it via `.a` (RGB is zero). Per-platform
 * because commonMain has no raw pixel writer.
 */
internal expect fun makeAlphaImageBitmap(size: Int, alphaBytes: ByteArray): ImageBitmap
