// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shader

/**
 * Creates a platform-specific [RuntimeShader] from the given AGSL/SkSL shader string.
 */
expect fun RuntimeShader(shaderString: String): RuntimeShader

/**
 * Converts this [RuntimeShader] to a Compose [Shader] for use with Paint.
 */
expect fun RuntimeShader.asComposeShader(): Shader

/**
 * Cross-platform interface for setting uniforms on a runtime shader.
 */
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
