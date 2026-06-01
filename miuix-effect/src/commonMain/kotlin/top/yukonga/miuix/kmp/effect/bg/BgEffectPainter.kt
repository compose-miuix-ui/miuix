// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.bg

import top.yukonga.miuix.kmp.blur.RuntimeShader
import top.yukonga.miuix.kmp.blur.asBrush
import kotlin.math.cos
import kotlin.math.sin

/**
 * Manages the runtime shader and GPU uniform state for the background gradient effect.
 *
 * This class acts as the bridge between the Kotlin animation layer and the GLSL/SkSL
 * shader pipeline. It owns a [RuntimeShader] instance and provides cached update methods
 * for each uniform category (resolution, animation time, colors, control points, bounds).
 *
 * ### Caching Strategy
 *
 * Each `update*` method compares incoming values against cached state and skips the
 * GPU uniform upload when nothing has changed. This avoids redundant shader calls during
 * frames where only a subset of uniforms change (e.g., animation time advances but
 * colors remain static).
 *
 * ### Thread Safety
 *
 * This class is **not** thread-safe. It is designed to be called exclusively from the
 * Compose draw phase (`ContentDrawScope.draw()`), which runs on the main thread.
 *
 * @param isOs3 `true` to use the HyperOS 3 shader variant, `false` for HyperOS 2.
 */
internal class BgEffectPainter(
    private val isOs3: Boolean = true,
) {

    /** The underlying runtime shader, lazily initialized on first access. */
    val runtimeShader by lazy {
        val shaderCode = if (isOs3) OS3_BG_FRAG else OS2_BG_FRAG
        RuntimeShader(shaderCode).also {
            initStaticUniforms(it)
        }
    }

    /** A [ShaderBrush] wrapping the current [runtimeShader]. Fresh on Skiko platforms. */
    val brush get() = runtimeShader.asBrush()

    private val resolution = FloatArray(2)
    private val bound = FloatArray(4)
    private val colorsBuffer = FloatArray(16)
    private val pointsAnimBuffer = FloatArray(8)

    private var animTime = Float.NaN
    private var isDarkCached: Boolean? = null
    private var deviceTypeCached: DeviceType? = null

    private var presetApplied = false

    private var cachedLogoHeight = Float.NaN
    private var cachedTotalHeight = Float.NaN
    private var cachedTotalWidth = Float.NaN

    private var cachedColorStage = Float.NaN
    private var cachedColorsPreset: BgEffectConfig.Config? = null

    private var cachedPointsAnimTime = Float.NaN
    private var cachedPointsAnimPreset: BgEffectConfig.Config? = null

    companion object {
        private const val U_TRANSLATE_Y = 0f
        private const val U_ALPHA_MULTI = 1f
        private const val U_NOISE_SCALE = 1.5f
        private const val U_POINT_RADIUS_MULTI = 1f
    }

    private fun initStaticUniforms(shader: RuntimeShader) {
        shader.setFloatUniform("uTranslateY", U_TRANSLATE_Y)
        shader.setFloatUniform("uNoiseScale", U_NOISE_SCALE)
        shader.setFloatUniform("uPointRadiusMulti", U_POINT_RADIUS_MULTI)
        shader.setFloatUniform("uAlphaMulti", U_ALPHA_MULTI)
    }

    /**
     * Updates the viewport resolution uniform if the dimensions have changed.
     *
     * @param width Viewport width in pixels.
     * @param height Viewport height in pixels.
     */
    fun updateResolution(width: Float, height: Float) {
        if (resolution[0] == width && resolution[1] == height) return
        resolution[0] = width
        resolution[1] = height
        runtimeShader.setFloatUniform("uResolution", resolution)
    }

    /**
     * Updates the animation time uniform if it has changed.
     *
     * @param time Elapsed animation time in seconds.
     */
    fun updateAnimTime(time: Float) {
        if (animTime == time) return
        animTime = time
        runtimeShader.setFloatUniform("uAnimTime", animTime)
    }

    /**
     * Computes animated control-point positions and uploads them to the shader.
     *
     * Each control point oscillates around its base position using sin/cos functions
     * modulated by [BgEffectConfig.Config.pointOffset]. The animation produces a
     * gentle, organic drift that avoids repetitive motion patterns.
     *
     * Results are cached by (`time`, `preset`) identity to skip redundant computation.
     *
     * @param time Current animation time in seconds.
     * @param preset The active visual configuration.
     */
    fun updatePointsAnim(time: Float, preset: BgEffectConfig.Config) {
        if (cachedPointsAnimTime == time && cachedPointsAnimPreset === preset) return

        val offset = preset.pointOffset
        var i = 0
        while (i < 4) {
            val srcX = preset.points[i * 3]
            val srcY = preset.points[i * 3 + 1]
            val animX = srcX + sin(time + srcY) * offset
            val animY = srcY + cos(time + animX) * offset
            pointsAnimBuffer[i * 2] = animX
            pointsAnimBuffer[i * 2 + 1] = animY
            i++
        }
        runtimeShader.setFloatUniform("uPointsAnim", pointsAnimBuffer)

        cachedPointsAnimTime = time
        cachedPointsAnimPreset = preset
    }

    /**
     * Interpolates between the three color palettes at the given animation stage
     * and uploads the result to the shader.
     *
     * The [stage] value is decomposed into an integer cycle index and a fractional
     * interpolation weight. The three palettes cycle in a repeating pattern of 4
     * (via `mod(4)`), producing smooth, non-repeating color transitions.
     *
     * @param preset The active visual configuration containing color palettes.
     * @param stage Continuous color animation stage (advances by 1 per transition).
     */
    fun updateColors(preset: BgEffectConfig.Config, stage: Float) {
        if (cachedColorsPreset === preset && cachedColorStage == stage) return

        val base = stage.toInt()
        val fraction = stage - base
        val start = colorsForCycleIndex(preset, base)
        val end = colorsForCycleIndex(preset, base + 1)
        for (i in 0 until 16) {
            colorsBuffer[i] = start[i] + (end[i] - start[i]) * fraction
        }
        runtimeShader.setFloatUniform("uColors", colorsBuffer)

        cachedColorsPreset = preset
        cachedColorStage = stage
    }

    /**
     * Returns the color palette for the given cycle index.
     *
     * The three palettes cycle with period 4:
     * - Index 0, 2, 4, ... → [colors2]
     * - Index 1, 5, 9, ... → [colors1]
     * - Index 3, 7, 11, ... → [colors3]
     *
     * This produces the sequence: colors2 → colors1 → colors2 → colors3 → repeat.
     */
    private fun colorsForCycleIndex(preset: BgEffectConfig.Config, index: Int): FloatArray = when (index.mod(4)) {
        1 -> preset.colors1
        3 -> preset.colors3
        else -> preset.colors2
    }

    /**
     * Updates the UV-space bounding box uniform if dimensions have changed.
     *
     * The bounding box defines the region of UV space where the gradient is rendered,
     * adapting the layout for portrait vs. landscape orientations.
     *
     * @param logoHeight Height of the content area in pixels.
     * @param totalHeight Total viewport height in pixels.
     * @param totalWidth Total viewport width in pixels.
     */
    fun updateBoundIfNeeded(
        logoHeight: Float,
        totalHeight: Float,
        totalWidth: Float,
    ) {
        if (cachedLogoHeight == logoHeight &&
            cachedTotalHeight == totalHeight &&
            cachedTotalWidth == totalWidth
        ) {
            return
        }

        updateBound(logoHeight, totalHeight, totalWidth)
        runtimeShader.setFloatUniform("uBound", bound)

        cachedLogoHeight = logoHeight
        cachedTotalHeight = totalHeight
        cachedTotalWidth = totalWidth
    }

    /**
     * Applies the shader preset uniforms if the device type or theme has changed.
     *
     * This updates the static control-point positions, light offset, and saturation
     * offset. Color palettes are updated separately via [updateColors].
     *
     * @param deviceType The form factor (phone or tablet).
     * @param isDark Whether the system is in dark theme.
     */
    fun updatePresetIfNeeded(deviceType: DeviceType, isDark: Boolean) {
        if (presetApplied && isDarkCached == isDark && deviceTypeCached == deviceType) return

        applyPreset(deviceType, isDark)

        isDarkCached = isDark
        deviceTypeCached = deviceType
        presetApplied = true
    }

    private fun applyPreset(deviceType: DeviceType, isDark: Boolean) {
        val preset = BgEffectConfig.get(deviceType, isDark, isOs3)

        runtimeShader.setFloatUniform("uPoints", preset.points)
        runtimeShader.setFloatUniform("uLightOffset", preset.lightOffset)
        runtimeShader.setFloatUniform("uSaturateOffset", preset.saturateOffset)
    }

    /**
     * Computes the UV-space bounding box for the gradient rendering area.
     *
     * In portrait orientation (width ≤ height), the gradient fills the full width
     * and occupies the bottom portion of the viewport. In landscape orientation,
     * the gradient is centered vertically and scaled to maintain aspect ratio.
     *
     * @param logoHeight Height of the content area in pixels.
     * @param totalHeight Total viewport height in pixels.
     * @param totalWidth Total viewport width in pixels.
     */
    private fun updateBound(
        logoHeight: Float,
        totalHeight: Float,
        totalWidth: Float,
    ) {
        if (totalHeight == 0f) return
        val heightRatio = logoHeight / totalHeight
        if (totalWidth <= totalHeight) {
            bound[0] = 0f
            bound[1] = 1f - heightRatio
            bound[2] = 1f
            bound[3] = heightRatio
        } else {
            if (totalHeight == 0f) return
            val aspectRatio = totalWidth / totalHeight
            val contentCenterY = 1f - heightRatio / 2f
            bound[0] = 0f
            bound[1] = contentCenterY - aspectRatio / 2f
            bound[2] = 1f
            bound[3] = aspectRatio
        }
    }
}
