// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.toArgb
import org.intellij.lang.annotations.Language

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
actual fun RuntimeShader(@Language("AGSL") shaderString: String): RuntimeShader = AndroidRuntimeShader(android.graphics.RuntimeShader(shaderString))

actual fun RuntimeShader.asComposeShader(): Shader = asAndroidRuntimeShader()

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
actual fun RuntimeShader.asBrush(): ShaderBrush = (this as AndroidRuntimeShader).brush

internal fun RuntimeShader.asAndroidRuntimeShader(): android.graphics.RuntimeShader = (this as AndroidRuntimeShader).shader

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private class AndroidRuntimeShader(val shader: android.graphics.RuntimeShader) : RuntimeShader {

    val brush: ShaderBrush = ShaderBrush(shader)

    override fun setFloatUniform(name: String, value: Float) {
        shader.setFloatUniform(name, value)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float) {
        shader.setFloatUniform(name, value1, value2)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float) {
        shader.setFloatUniform(name, value1, value2, value3)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float, value4: Float) {
        shader.setFloatUniform(name, value1, value2, value3, value4)
    }

    override fun setFloatUniform(name: String, values: FloatArray) {
        shader.setFloatUniform(name, values)
    }

    override fun setIntUniform(name: String, value: Int) {
        shader.setIntUniform(name, value)
    }

    override fun setIntUniform(name: String, value1: Int, value2: Int) {
        shader.setIntUniform(name, value1, value2)
    }

    override fun setIntUniform(name: String, value1: Int, value2: Int, value3: Int) {
        shader.setIntUniform(name, value1, value2, value3)
    }

    override fun setIntUniform(name: String, value1: Int, value2: Int, value3: Int, value4: Int) {
        shader.setIntUniform(name, value1, value2, value3, value4)
    }

    override fun setIntUniform(name: String, values: IntArray) {
        shader.setIntUniform(name, values)
    }

    override fun setColorUniform(name: String, color: Color) {
        shader.setColorUniform(name, color.toArgb())
    }

    override fun setInputShader(name: String, shader: Shader) {
        this.shader.setInputShader(name, shader)
    }
}
