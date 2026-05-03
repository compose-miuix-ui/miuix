// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.highlight

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.blur.highlight.BloomStroke
import top.yukonga.miuix.kmp.blur.highlight.Highlight
import top.yukonga.miuix.kmp.blur.highlight.rememberTiltLight

/**
 * Resolves a [Highlight] for the given [container] and theme, optionally driving its two
 * light sources from the device tilt sensor.
 *
 * Returns `null` when [container] is [HighlightConfig.Container.Disabled]. The tilt sensors
 * are always wired (composables cannot be conditionally invoked) — when the container is
 * disabled, a placeholder style feeds the sensors and the resulting highlight is gated to
 * null before reaching the caller.
 */
@Composable
internal fun rememberContainerHighlight(
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
