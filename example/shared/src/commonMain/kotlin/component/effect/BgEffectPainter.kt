// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.effect

import androidx.compose.ui.graphics.Brush
import top.yukonga.miuix.kmp.blur.RuntimeShader
import top.yukonga.miuix.kmp.blur.asBrush

class BgEffectPainter(
    private val isOs3: Boolean = true,
) {

    val runtimeShader by lazy {
        val shaderCode = if (isOs3) OS3_BG_FRAG else OS2_BG_FRAG
        RuntimeShader(shaderCode).also {
            initStaticUniforms(it)
        }
    }

    val brush: Brush by lazy { runtimeShader.asBrush() }

    private val resolution = FloatArray(2)
    private val bound = FloatArray(4)

    private var animTime = Float.NaN
    private var isDarkCached: Boolean? = null
    private var deviceTypeCached: DeviceType? = null

    private var presetApplied = false

    private var deviceType = DeviceType.PHONE

    companion object {

        private const val U_TRANSLATE_Y = 0f
        private const val U_ALPHA_MULTI = 1f
        private const val U_NOISE_SCALE = 1.5f
        private const val U_POINT_RADIUS_MULTI = 1f
        private const val U_ALPHA_OFFSET = 0.1f
        private const val U_SHADOW_OFFSET = 0.01f
    }

    private fun initStaticUniforms(shader: RuntimeShader) {
        shader.setFloatUniform("uTranslateY", U_TRANSLATE_Y)
        shader.setFloatUniform("uNoiseScale", U_NOISE_SCALE)
        shader.setFloatUniform("uPointRadiusMulti", U_POINT_RADIUS_MULTI)
        shader.setFloatUniform("uAlphaMulti", U_ALPHA_MULTI)

        if (isOs3) {
            shader.setFloatUniform("uAlphaOffset", U_ALPHA_OFFSET)
            shader.setFloatUniform("uShadowOffset", U_SHADOW_OFFSET)
        }
    }

    fun setDeviceType(type: DeviceType) {
        if (deviceType == type) return
        deviceType = type
        presetApplied = false
    }

    fun updateResolution(width: Float, height: Float) {
        if (resolution[0] == width && resolution[1] == height) return
        resolution[0] = width
        resolution[1] = height
        runtimeShader.setFloatUniform("uResolution", resolution)
    }

    fun updateAnimTime(time: Float) {
        if (animTime == time) return
        animTime = time
        runtimeShader.setFloatUniform("uAnimTime", animTime)
    }

    fun updateColors(colors: FloatArray) {
        runtimeShader.setFloatUniform("uColors", colors)
    }

    fun updatePresetIfNeeded(
        logoHeight: Float,
        height: Float,
        width: Float,
        isDark: Boolean,
    ) {
        if (presetApplied && isDarkCached == isDark && deviceTypeCached == deviceType) return

        updateBound(logoHeight, height, width)
        applyPreset(isDark)

        isDarkCached = isDark
        deviceTypeCached = deviceType
        presetApplied = true
    }

    private fun applyPreset(isDark: Boolean) {
        val preset = BgEffectConfig.get(deviceType, isDark, isOs3)

        runtimeShader.setFloatUniform("uPoints", preset.points)
        runtimeShader.setFloatUniform("uPointOffset", preset.pointOffset)
        runtimeShader.setFloatUniform("uLightOffset", preset.lightOffset)
        runtimeShader.setFloatUniform("uSaturateOffset", preset.saturateOffset)
        runtimeShader.setFloatUniform("uBound", bound)

        if (isOs3) {
            runtimeShader.setFloatUniform("uShadowColorMulti", preset.shadowColorMulti)
            runtimeShader.setFloatUniform("uShadowColorOffset", preset.shadowColorOffset)
            runtimeShader.setFloatUniform("uShadowNoiseScale", preset.shadowNoiseScale)
        }
    }

    private fun updateBound(
        logoHeight: Float,
        totalHeight: Float,
        totalWidth: Float,
    ) {
        val heightRatio = logoHeight / totalHeight
        if (totalWidth <= totalHeight) {
            bound[0] = 0f
            bound[1] = 1f - heightRatio
            bound[2] = 1f
            bound[3] = heightRatio
        } else {
            val aspectRatio = totalWidth / totalHeight
            val contentCenterY = 1f - heightRatio / 2f
            bound[0] = 0f
            bound[1] = contentCenterY - aspectRatio / 2f
            bound[2] = 1f
            bound[3] = aspectRatio
        }
    }
}
