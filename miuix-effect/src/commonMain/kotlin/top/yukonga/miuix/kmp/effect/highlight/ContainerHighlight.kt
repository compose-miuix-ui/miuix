// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.highlight

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.blur.highlight.BloomStroke
import top.yukonga.miuix.kmp.blur.highlight.Highlight
import top.yukonga.miuix.kmp.blur.highlight.rememberTiltLight

/**
 * Resolves a [Highlight] for the given container size and theme, optionally driving
 * its light sources from the device tilt sensor.
 *
 * This composable combines [HighlightConfig.get] with sensor-driven [rememberTiltLight]
 * to produce a highlight that responds to device orientation. When [tiltDriven] is `true`,
 * both the primary and secondary light positions are updated in real time based on the
 * device's accelerometer/gyroscope data, creating a parallax-like lighting effect.
 *
 * ### Sensor Lifecycle
 *
 * The tilt sensors are **always** wired regardless of the [container] value (composables
 * cannot be conditionally invoked in Compose). When [container] is
 * [HighlightConfig.Container.Disabled], a placeholder [BloomStroke] feeds the sensors
 * and the resulting highlight is gated to `null` before reaching the caller. This
 * ensures zero visual impact when disabled while avoiding Compose runtime errors.
 *
 * ### Usage
 *
 * ```kotlin
 * val highlight = rememberContainerHighlight(
 *     container = HighlightConfig.Container.Medium,
 *     isDark = isSystemInDarkTheme(),
 *     tiltDriven = true,
 * )
 *
 * Box(
 *     modifier = Modifier.textureBlur(
 *         backdrop = backdrop,
 *         shape = RoundedCornerShape(16.dp),
 *         highlight = highlight,
 *     )
 * )
 * ```
 *
 * @param container The size category determining the base highlight preset.
 * @param isDark `true` for dark theme, `false` for light theme.
 * @param tiltDriven When `true`, light positions follow device tilt. When `false`,
 *   the static preset is returned without sensor integration.
 * @param primarySensitivity UV offset per radian of tilt for the primary light.
 *   Higher values amplify the parallax effect. Default: `0.15f`.
 * @param secondarySensitivity UV offset per radian of tilt for the secondary light.
 *   Default: `0.12f`.
 * @return A [Highlight] instance, or `null` when [container] is [HighlightConfig.Container.Disabled].
 */
@Composable
fun rememberContainerHighlight(
    container: HighlightConfig.Container,
    isDark: Boolean,
    tiltDriven: Boolean,
    primarySensitivity: Float = 0.15f,
    secondarySensitivity: Float = 0.12f,
): Highlight? {
    val highlightBase = HighlightConfig.get(container, isDark)
    val baseStyle = (highlightBase?.style as? BloomStroke) ?: BloomStroke.GlassStrokeMiddleLight

    val tiltPrimary = rememberTiltLight(
        basePosition = baseStyle.primaryLight.position,
        color = baseStyle.primaryLight.color,
        intensity = baseStyle.primaryLight.intensity,
        sensitivity = primarySensitivity,
    )
    val tiltSecondary = rememberTiltLight(
        basePosition = baseStyle.secondaryLight.position,
        color = baseStyle.secondaryLight.color,
        intensity = baseStyle.secondaryLight.intensity,
        sensitivity = secondarySensitivity,
    )

    return remember(highlightBase, baseStyle, tiltDriven, tiltPrimary, tiltSecondary) {
        when {
            highlightBase == null -> null

            tiltDriven -> highlightBase.copy(
                style = baseStyle.copy(
                    primaryLight = tiltPrimary,
                    secondaryLight = tiltSecondary,
                ),
            )

            else -> highlightBase
        }
    }
}
