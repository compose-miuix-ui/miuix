// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

/**
 * Returns true if the current platform supports [RenderEffect]-based blur and color filters.
 * On Android, this requires API 31 (S) or higher.
 */
expect fun isRenderEffectSupported(): Boolean

/**
 * Returns true if the current platform supports [RuntimeShader] for custom AGSL/SkSL shaders.
 * On Android, this requires API 33 (Tiramisu) or higher.
 */
expect fun isRuntimeShaderSupported(): Boolean
