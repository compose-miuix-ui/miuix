// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shader

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asComposeShader
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.graphics.skiaShader
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import org.jetbrains.skia.RuntimeEffect
import org.jetbrains.skia.RuntimeShaderBuilder

actual fun isRuntimeShaderSupported(): Boolean = true

/**
 * Creates a platform-specific [RuntimeShader] from an AGSL/SkSL shader string.
 *
 * @param shaderString The SkSL shader source to compile.
 */
actual fun RuntimeShader(shaderString: String): RuntimeShader = SkikoRuntimeShader(RuntimeShaderBuilder(obtainRuntimeEffect(shaderString)))

// RuntimeEffect is immutable; cache to avoid recompiling SkSL per brush.
@OptIn(InternalCoroutinesApi::class)
private val effectCacheLock = SynchronizedObject()
private val effectCache = mutableMapOf<String, RuntimeEffect>()

@OptIn(InternalCoroutinesApi::class)
private fun obtainRuntimeEffect(shaderString: String): RuntimeEffect = synchronized(effectCacheLock) {
    effectCache.getOrPut(shaderString) { RuntimeEffect.makeForShader(shaderString) }
}

actual fun RuntimeShader.asComposeShader(): Shader = asSkikoRuntimeShader().makeShader().asComposeShader()

actual fun RuntimeShader.asBrush(): ShaderBrush = ShaderBrush(asComposeShader())

/** Returns the underlying Skia [RuntimeShaderBuilder] for interop with native shader APIs. */
fun RuntimeShader.asSkikoRuntimeShader(): RuntimeShaderBuilder = (this as SkikoRuntimeShader).shader

private class SkikoRuntimeShader(val shader: RuntimeShaderBuilder) : RuntimeShader {

    override fun setFloatUniform(name: String, value: Float) {
        shader.uniform(name, value)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float) {
        shader.uniform(name, value1, value2)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float) {
        shader.uniform(name, value1, value2, value3)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float, value4: Float) {
        shader.uniform(name, value1, value2, value3, value4)
    }

    override fun setFloatUniform(name: String, values: FloatArray) {
        shader.uniform(name, values)
    }

    override fun setIntUniform(name: String, value: Int) {
        shader.uniform(name, value)
    }

    override fun setIntUniform(name: String, value1: Int, value2: Int) {
        shader.uniform(name, value1, value2)
    }

    override fun setIntUniform(name: String, value1: Int, value2: Int, value3: Int) {
        shader.uniform(name, value1, value2, value3)
    }

    override fun setIntUniform(name: String, value1: Int, value2: Int, value3: Int, value4: Int) {
        shader.uniform(name, value1, value2, value3, value4)
    }

    override fun setIntUniform(name: String, values: IntArray) {
        val floats = FloatArray(values.size) { values[it].toFloat() }
        shader.uniform(name, floats)
    }

    override fun setColorUniform(name: String, color: Color) {
        val srgb = color.convert(ColorSpaces.Srgb)
        val a = srgb.alpha
        shader.uniform(name, srgb.red * a, srgb.green * a, srgb.blue * a, a)
    }

    override fun setInputShader(name: String, shader: Shader) {
        this.shader.child(name, shader.skiaShader)
    }
}
