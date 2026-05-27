// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Path
import top.yukonga.miuix.kmp.shader.isRuntimeShaderSupported

/**
 * Global opt-out for squircle visuals (default `true`). When `false`, every squircle modifier in
 * this module falls back to the matching `RoundedCornerShape` rendering even where shaders are
 * available. [Path.addSquircleRect] cannot read CompositionLocals — callers should branch on
 * [isSquircleEnabled] and forward the flag.
 */
val LocalSquircleEnabled = staticCompositionLocalOf { true }

/**
 * Whether shader-backed squircle silhouettes are active in the current composition — combines
 * [LocalSquircleEnabled] with the platform's [isRuntimeShaderSupported] check. Forward the result
 * to [Path.addSquircleRect] when mixing the path builder with the modifier APIs.
 */
@Composable
@ReadOnlyComposable
fun isSquircleEnabled(): Boolean = LocalSquircleEnabled.current && isRuntimeShaderSupported()
