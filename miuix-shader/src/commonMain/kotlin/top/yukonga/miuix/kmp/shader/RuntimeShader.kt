// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shader

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

/** Cross-platform interface for setting uniforms on a runtime shader. */
interface RuntimeShader {

    /**
     * Sets a single-component `float` uniform.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param value The scalar value to assign to the uniform.
     */
    fun setFloatUniform(name: String, value: Float)

    /**
     * Sets a two-component (`vec2`) `float` uniform.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param value1 The first component of the uniform.
     * @param value2 The second component of the uniform.
     */
    fun setFloatUniform(name: String, value1: Float, value2: Float)

    /**
     * Sets a three-component (`vec3`) `float` uniform.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param value1 The first component of the uniform.
     * @param value2 The second component of the uniform.
     * @param value3 The third component of the uniform.
     */
    fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float)

    /**
     * Sets a four-component (`vec4`) `float` uniform.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param value1 The first component of the uniform.
     * @param value2 The second component of the uniform.
     * @param value3 The third component of the uniform.
     * @param value4 The fourth component of the uniform.
     */
    fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float, value4: Float)

    /**
     * Sets a `float` uniform from an array of components.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param values The array of float components to assign to the uniform.
     */
    fun setFloatUniform(name: String, values: FloatArray)

    /**
     * Sets a single-component `int` uniform.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param value The scalar value to assign to the uniform.
     */
    fun setIntUniform(name: String, value: Int)

    /**
     * Sets a two-component (`int2`) `int` uniform.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param value1 The first component of the uniform.
     * @param value2 The second component of the uniform.
     */
    fun setIntUniform(name: String, value1: Int, value2: Int)

    /**
     * Sets a three-component (`int3`) `int` uniform.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param value1 The first component of the uniform.
     * @param value2 The second component of the uniform.
     * @param value3 The third component of the uniform.
     */
    fun setIntUniform(name: String, value1: Int, value2: Int, value3: Int)

    /**
     * Sets a four-component (`int4`) `int` uniform.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param value1 The first component of the uniform.
     * @param value2 The second component of the uniform.
     * @param value3 The third component of the uniform.
     * @param value4 The fourth component of the uniform.
     */
    fun setIntUniform(name: String, value1: Int, value2: Int, value3: Int, value4: Int)

    /**
     * Sets an `int` uniform from an array of components.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param values The array of int components to assign to the uniform.
     */
    fun setIntUniform(name: String, values: IntArray)

    /**
     * Sets a `vec4` uniform from a [Color], converted to the shader's expected component layout.
     *
     * @param name The name of the uniform as declared in the shader source.
     * @param color The [Color] to assign to the uniform.
     */
    fun setColorUniform(name: String, color: Color)

    /**
     * Binds a [Shader] as a child input shader of this runtime shader.
     *
     * @param name The name of the child shader as declared in the shader source.
     * @param shader The [Shader] to bind as the named input.
     */
    fun setInputShader(name: String, shader: Shader)
}

/** True on Android API 33+ and on every Skia backend. */
expect fun isRuntimeShaderSupported(): Boolean

/**
 * Creates a platform-specific [RuntimeShader] from an AGSL/SkSL shader string.
 *
 * @param shaderString The AGSL (Android) or SkSL (Skia) shader source to compile.
 */
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
