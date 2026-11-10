// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.edge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.EdgeStrokes
import top.yukonga.miuix.kmp.material.rememberTiltLight

/**
 * Resolves an [EdgeStroke] for the given [container] and theme, optionally driving its two
 * light sources from the device tilt sensor.
 *
 * Returns `null` when [container] is [EdgeStrokeConfig.Container.Disabled]. The tilt sensors
 * are always wired (composables cannot be conditionally invoked) — when the container is
 * disabled, a placeholder stroke feeds the sensors and the resulting stroke is gated to
 * null before reaching the caller.
 */
@Composable
internal fun rememberContainerEdgeStroke(
    container: EdgeStrokeConfig.Container,
    isDark: Boolean,
    tiltDriven: Boolean,
    primarySensitivity: Float = 0.15f,
    secondarySensitivity: Float = 0.12f,
): EdgeStroke? {
    val strokeBase = EdgeStrokeConfig.get(container, isDark)
    val baseStroke = strokeBase ?: EdgeStrokes.MiddleLight

    val tiltPrimary = rememberTiltLight(
        base = baseStroke.primary,
        sensitivity = primarySensitivity,
    )
    val tiltSecondary = rememberTiltLight(
        base = baseStroke.secondary,
        sensitivity = secondarySensitivity,
    )

    return remember(strokeBase, baseStroke, tiltDriven, tiltPrimary, tiltSecondary) {
        when {
            strokeBase == null -> null

            tiltDriven -> baseStroke.copy(
                primary = tiltPrimary,
                secondary = tiltSecondary,
            )

            else -> baseStroke
        }
    }
}
