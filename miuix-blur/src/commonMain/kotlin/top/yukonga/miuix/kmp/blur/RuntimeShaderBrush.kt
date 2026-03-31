// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.graphics.ShaderBrush

/**
 * Converts this [RuntimeShader] to a [ShaderBrush] suitable for drawing.
 *
 * On Android the underlying shader is mutable, so a cached [ShaderBrush] is returned.
 * On Skiko platforms [asComposeShader] produces an immutable snapshot, so a fresh
 * [ShaderBrush] is created on every call to ensure uniform updates are visible.
 */
expect fun RuntimeShader.asBrush(): ShaderBrush
