// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle.internal

import androidx.compose.ui.graphics.Shader

/**
 * Creates a hardware-LINEAR-sampled [Shader] backed by a [size]×[size] alpha-only image built from
 * [alphaBytes] (one byte per pixel in `.a`; RGB is zero). Per-platform because commonMain has neither
 * a raw single-channel pixel writer nor a linear-sampling `ImageShader` factory.
 */
internal expect fun makeLinearAlphaShaderFactory(size: Int, alphaBytes: ByteArray): () -> Shader
