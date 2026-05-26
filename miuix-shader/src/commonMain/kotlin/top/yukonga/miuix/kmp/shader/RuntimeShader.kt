// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shader

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

/** Cross-platform interface for setting uniforms on a runtime shader. */
interface RuntimeShader {

    fun setFloatUniform(name: String, value: Float)
    fun setFloatUniform(name: String, value1: Float, value2: Float)
    fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float)
    fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float, value4: Float)
    fun setFloatUniform(name: String, values: FloatArray)

    fun setIntUniform(name: String, value: Int)
    fun setIntUniform(name: String, value1: Int, value2: Int)
    fun setIntUniform(name: String, value1: Int, value2: Int, value3: Int)
    fun setIntUniform(name: String, value1: Int, value2: Int, value3: Int, value4: Int)
    fun setIntUniform(name: String, values: IntArray)

    fun setColorUniform(name: String, color: Color)

    fun setInputShader(name: String, shader: Shader)
}

/** True on Android API 33+ and on every Skia backend. */
expect fun isRuntimeShaderSupported(): Boolean

/** Creates a platform-specific [RuntimeShader] from an AGSL/SkSL shader string. */
expect fun RuntimeShader(shaderString: String): RuntimeShader

/** Returns the [RuntimeShader] as a Compose [Shader] suitable for use with Paint. */
expect fun RuntimeShader.asComposeShader(): Shader

/**
 * Wraps this [RuntimeShader] as a [ShaderBrush]. On Android the underlying
 * shader is mutable, so a cached [ShaderBrush] is returned; on Skiko
 * [asComposeShader] produces an immutable snapshot, so a fresh [ShaderBrush]
 * is created on every call to ensure uniform updates are visible.
 */
expect fun RuntimeShader.asBrush(): ShaderBrush
