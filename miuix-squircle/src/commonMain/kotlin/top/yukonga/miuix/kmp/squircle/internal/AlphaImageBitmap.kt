// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle.internal

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Creates a [size]×[size] [ImageBitmap] whose alpha channel is filled from
 * [alphaBytes]; RGB matches alpha so sampling stays consistent across premul
 * / unpremul consumers. Per-platform because commonMain has no raw pixel
 * writer.
 */
internal expect fun makeAlphaImageBitmap(size: Int, alphaBytes: ByteArray): ImageBitmap
