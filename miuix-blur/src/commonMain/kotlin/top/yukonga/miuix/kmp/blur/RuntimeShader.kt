// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("FunctionName")

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import top.yukonga.miuix.kmp.shader.asBrush as coreAsBrush
import top.yukonga.miuix.kmp.shader.asComposeShader as coreAsComposeShader
import top.yukonga.miuix.kmp.shader.isRuntimeShaderSupported as coreIsRuntimeShaderSupported

/** Back-compat re-export. New code should use `top.yukonga.miuix.kmp.shader.RuntimeShader`. */
typealias RuntimeShader = top.yukonga.miuix.kmp.shader.RuntimeShader

/**
 * Back-compat re-export.
 *
 * @param shaderString The AGSL/SkSL shader source code to compile into the [RuntimeShader].
 */
fun RuntimeShader(shaderString: String): RuntimeShader = top.yukonga.miuix.kmp.shader.RuntimeShader(shaderString)

/** Back-compat re-export. */
fun RuntimeShader.asComposeShader(): Shader = coreAsComposeShader()

/** Back-compat re-export. */
fun RuntimeShader.asBrush(): ShaderBrush = coreAsBrush()

/** Back-compat re-export. */
fun isRuntimeShaderSupported(): Boolean = coreIsRuntimeShaderSupported()
