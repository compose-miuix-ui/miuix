// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.bg

import top.yukonga.miuix.kmp.effect.DeviceType

/**
 * Configuration presets for the background gradient effect.
 *
 * Each [Config] defines the visual parameters for a specific combination of
 * [DeviceType] (phone vs. pad) and theme (light vs. dark), for either the
 * OS2 or OS3 effect variant. The configuration controls:
 *
 * - **Control points**: Four 3D points (x, y, radius) that define gradient anchors.
 * - **Color palettes**: Three RGBA color arrays ([colors1], [colors2], [colors3]) that
 *   cycle through animated interpolation, enabling smooth multi-palette transitions.
 * - **Animation timing**: [colorInterpPeriod] controls the half-cycle duration of color
 *   transitions; [pointOffset] controls the oscillation amplitude of control points.
 * - **Post-processing**: [lightOffset] and [saturateOffset] modulate brightness and
 *   saturation based on Perlin noise, producing the organic "breathing" effect.
 *
 * Configurations are selected via [BgEffectConfig.get] and are treated as immutable
 * singletons to enable reference-equality caching in the rendering pipeline.
 */
internal object BgEffectConfig {

    /**
     * A set of visual parameters for the background gradient effect.
     *
     * @property points Control-point positions as a flat `FloatArray` of 12 values
     *   (4 points × 3 components: x, y, radius). Coordinates are in UV space [0, 1].
     * @property colors1 First RGBA color palette (16 floats = 4 colors × 4 components).
     * @property colors2 Second RGBA color palette for interpolation.
     * @property colors3 Third RGBA color palette for interpolation.
     * @property colorInterpPeriod Half-cycle duration (in seconds) for color palette
     *   transitions. A value of `5.0` means each palette-to-palette blend takes ~10s.
     * @property lightOffset Noise-driven brightness shift. Positive values brighten;
     *   negative values darken. Range: typically [-0.1, 0.1].
     * @property saturateOffset Noise-driven saturation reduction strength.
     *   `0.0` = no desaturation; `0.2` = moderate noise-driven desaturation.
     * @property pointOffset Oscillation amplitude for animated control-point positions.
     *   Higher values produce more movement. Range: typically [0.1, 0.4].
     */
    internal class Config(
        val points: FloatArray,
        val colors1: FloatArray,
        val colors2: FloatArray,
        val colors3: FloatArray,
        val colorInterpPeriod: Float,
        val lightOffset: Float,
        val saturateOffset: Float,
        val pointOffset: Float,
    )

    // ── OS2 Presets ────────────────────────────────────────────────────────

    private val OS2_PHONE_LIGHT_COLORS = floatArrayOf(
        0.57f, 0.76f, 0.98f, 1.0f, 0.98f, 0.85f, 0.68f, 1.0f, 0.98f, 0.75f, 0.93f, 1.0f, 0.73f, 0.70f, 0.98f, 1.0f,
    )
    private val OS2_PHONE_LIGHT = Config(
        points = floatArrayOf(0.67f, 0.42f, 1.0f, 0.69f, 0.75f, 1.0f, 0.14f, 0.71f, 0.95f, 0.14f, 0.27f, 0.8f),
        colors1 = OS2_PHONE_LIGHT_COLORS,
        colors2 = OS2_PHONE_LIGHT_COLORS,
        colors3 = OS2_PHONE_LIGHT_COLORS,
        colorInterpPeriod = 100f,
        lightOffset = 0.1f,
        saturateOffset = 0.2f,
        pointOffset = 0.1f,
    )

    private val OS2_PHONE_DARK_COLORS = floatArrayOf(
        0.0f, 0.31f, 0.58f, 1.0f, 0.53f, 0.29f, 0.15f, 1.0f, 0.46f, 0.06f, 0.27f, 1.0f, 0.16f, 0.12f, 0.45f, 1.0f,
    )
    private val OS2_PHONE_DARK = Config(
        points = floatArrayOf(0.63f, 0.50f, 0.88f, 0.69f, 0.75f, 0.80f, 0.17f, 0.66f, 0.81f, 0.14f, 0.24f, 0.72f),
        colors1 = OS2_PHONE_DARK_COLORS,
        colors2 = OS2_PHONE_DARK_COLORS,
        colors3 = OS2_PHONE_DARK_COLORS,
        colorInterpPeriod = 100f,
        lightOffset = -0.1f,
        saturateOffset = 0.2f,
        pointOffset = 0.1f,
    )

    private val OS2_PAD_LIGHT_COLORS = floatArrayOf(
        0.57f, 0.76f, 0.98f, 1.0f, 0.98f, 0.85f, 0.68f, 1.0f, 0.98f, 0.75f, 0.93f, 0.95f, 0.73f, 0.70f, 0.98f, 0.90f,
    )
    private val OS2_PAD_LIGHT = Config(
        points = floatArrayOf(0.67f, 0.37f, 0.88f, 0.54f, 0.66f, 1.0f, 0.37f, 0.71f, 0.68f, 0.28f, 0.26f, 0.62f),
        colors1 = OS2_PAD_LIGHT_COLORS,
        colors2 = OS2_PAD_LIGHT_COLORS,
        colors3 = OS2_PAD_LIGHT_COLORS,
        colorInterpPeriod = 100f,
        lightOffset = 0.1f,
        saturateOffset = 0f,
        pointOffset = 0.1f,
    )

    private val OS2_PAD_DARK_COLORS = floatArrayOf(
        0.0f, 0.31f, 0.58f, 1.0f, 0.53f, 0.29f, 0.15f, 1.0f, 0.46f, 0.06f, 0.27f, 1.0f, 0.16f, 0.12f, 0.45f, 1.0f,
    )
    private val OS2_PAD_DARK = Config(
        points = floatArrayOf(0.55f, 0.42f, 1.0f, 0.56f, 0.75f, 1.0f, 0.40f, 0.59f, 0.71f, 0.43f, 0.09f, 0.75f),
        colors1 = OS2_PAD_DARK_COLORS,
        colors2 = OS2_PAD_DARK_COLORS,
        colors3 = OS2_PAD_DARK_COLORS,
        colorInterpPeriod = 100f,
        lightOffset = -0.1f,
        saturateOffset = 0.2f,
        pointOffset = 0.1f,
    )

    // ── OS3 Presets ────────────────────────────────────────────────────────

    private val OS3_PHONE_LIGHT = Config(
        points = floatArrayOf(0.8f, 0.2f, 1.0f, 0.8f, 0.9f, 1.0f, 0.2f, 0.9f, 1.0f, 0.2f, 0.2f, 1.0f),
        colors1 = floatArrayOf(1.0f, 0.9f, 0.94f, 1.0f, 1.0f, 0.84f, 0.89f, 1.0f, 0.97f, 0.73f, 0.82f, 1.0f, 0.64f, 0.65f, 0.98f, 1.0f),
        colors2 = floatArrayOf(0.58f, 0.74f, 1.0f, 1.0f, 1.0f, 0.9f, 0.93f, 1.0f, 0.74f, 0.76f, 1.0f, 1.0f, 0.97f, 0.77f, 0.84f, 1.0f),
        colors3 = floatArrayOf(0.98f, 0.86f, 0.9f, 1.0f, 0.6f, 0.73f, 0.98f, 1.0f, 0.92f, 0.93f, 1.0f, 1.0f, 0.56f, 0.69f, 1.0f, 1.0f),
        colorInterpPeriod = 5.0f,
        lightOffset = 0.1f,
        saturateOffset = 0.2f,
        pointOffset = 0.2f,
    )

    private val OS3_PHONE_DARK = Config(
        points = floatArrayOf(0.8f, 0.2f, 1.0f, 0.8f, 0.9f, 1.0f, 0.2f, 0.9f, 1.0f, 0.2f, 0.2f, 1.0f),
        colors1 = floatArrayOf(0.2f, 0.06f, 0.88f, 0.4f, 0.3f, 0.14f, 0.55f, 0.5f, 0.0f, 0.64f, 0.96f, 0.5f, 0.11f, 0.16f, 0.83f, 0.4f),
        colors2 = floatArrayOf(0.07f, 0.15f, 0.79f, 0.5f, 0.62f, 0.21f, 0.67f, 0.5f, 0.06f, 0.25f, 0.84f, 0.5f, 0.0f, 0.2f, 0.78f, 0.5f),
        colors3 = floatArrayOf(0.58f, 0.3f, 0.74f, 0.4f, 0.27f, 0.18f, 0.6f, 0.5f, 0.66f, 0.26f, 0.62f, 0.5f, 0.12f, 0.16f, 0.7f, 0.6f),
        colorInterpPeriod = 8.0f,
        lightOffset = 0.0f,
        saturateOffset = 0.17f,
        pointOffset = 0.4f,
    )

    private val OS3_PAD_LIGHT = Config(
        points = floatArrayOf(0.8f, 0.2f, 1.0f, 0.8f, 0.9f, 1.0f, 0.2f, 0.9f, 1.0f, 0.2f, 0.2f, 1.0f),
        colors1 = floatArrayOf(0.99f, 0.77f, 0.86f, 1.0f, 0.74f, 0.76f, 1.0f, 1.0f, 0.72f, 0.74f, 1.0f, 1.0f, 0.98f, 0.76f, 0.8f, 1.0f),
        colors2 = floatArrayOf(0.66f, 0.75f, 1.0f, 1.0f, 1.0f, 0.86f, 0.91f, 1.0f, 0.74f, 0.76f, 1.0f, 1.0f, 0.97f, 0.77f, 0.84f, 1.0f),
        colors3 = floatArrayOf(0.97f, 0.79f, 0.85f, 1.0f, 0.65f, 0.68f, 0.98f, 1.0f, 0.66f, 0.77f, 1.0f, 1.0f, 0.72f, 0.73f, 0.98f, 1.0f),
        colorInterpPeriod = 7.0f,
        lightOffset = 0.1f,
        saturateOffset = 0.2f,
        pointOffset = 0.2f,
    )

    private val OS3_PAD_DARK = Config(
        points = floatArrayOf(0.8f, 0.2f, 1.0f, 0.8f, 0.9f, 1.0f, 0.2f, 0.9f, 1.0f, 0.2f, 0.2f, 1.0f),
        colors1 = floatArrayOf(0.66f, 0.26f, 0.62f, 0.4f, 0.06f, 0.25f, 0.84f, 0.5f, 0.0f, 0.64f, 0.96f, 0.5f, 0.14f, 0.18f, 0.55f, 0.5f),
        colors2 = floatArrayOf(0.07f, 0.15f, 0.79f, 0.5f, 0.11f, 0.16f, 0.83f, 0.5f, 0.06f, 0.25f, 0.84f, 0.5f, 0.66f, 0.26f, 0.62f, 0.5f),
        colors3 = floatArrayOf(0.58f, 0.3f, 0.74f, 0.5f, 0.11f, 0.16f, 0.83f, 0.5f, 0.66f, 0.26f, 0.62f, 0.5f, 0.27f, 0.18f, 0.6f, 0.6f),
        colorInterpPeriod = 7.0f,
        lightOffset = 0.0f,
        saturateOffset = 0.0f,
        pointOffset = 0.2f,
    )

    /**
     * Returns the appropriate [Config] for the given device type, theme, and OS version.
     *
     * @param deviceType The form factor (phone or tablet).
     * @param isDark Whether the system is in dark theme.
     * @param isOs3 `true` for HyperOS 3 effect variant, `false` for HyperOS 2.
     * @return A singleton [Config] instance. Callers may use reference equality (`===`)
     *   to detect configuration changes.
     */
    internal fun get(
        deviceType: DeviceType,
        isDark: Boolean,
        isOs3: Boolean,
    ): Config = if (!isOs3) {
        when (deviceType) {
            DeviceType.PHONE -> if (!isDark) OS2_PHONE_LIGHT else OS2_PHONE_DARK
            DeviceType.PAD -> if (!isDark) OS2_PAD_LIGHT else OS2_PAD_DARK
        }
    } else {
        when (deviceType) {
            DeviceType.PHONE -> if (!isDark) OS3_PHONE_LIGHT else OS3_PHONE_DARK
            DeviceType.PAD -> if (!isDark) OS3_PAD_LIGHT else OS3_PAD_DARK
        }
    }
}
