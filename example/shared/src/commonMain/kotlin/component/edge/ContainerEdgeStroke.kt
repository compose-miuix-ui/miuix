// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.edge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.EdgeStrokes
import top.yukonga.miuix.kmp.material.sensor.rememberDeviceTilt
import top.yukonga.miuix.kmp.material.withTilt

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
): EdgeStroke? = rememberContainerEdgeStrokeState(
    container = container,
    isDark = isDark,
    tiltDriven = tiltDriven,
    primarySensitivity = primarySensitivity,
    secondarySensitivity = secondarySensitivity,
).value

@Composable
internal fun rememberContainerEdgeStrokeState(
    container: EdgeStrokeConfig.Container,
    isDark: Boolean,
    tiltDriven: Boolean,
    primarySensitivity: Float = 0.15f,
    secondarySensitivity: Float = 0.12f,
): State<EdgeStroke?> {
    val strokeBase = EdgeStrokeConfig.get(container, isDark)
    val baseStroke = strokeBase ?: EdgeStrokes.MiddleLight
    val tilt = rememberDeviceTilt()

    return remember(strokeBase, baseStroke, tiltDriven, primarySensitivity, secondarySensitivity, tilt) {
        computedStateOf {
            when {
                strokeBase == null -> null

                tiltDriven -> {
                    val sample = tilt.value
                    baseStroke.copy(
                        primary = baseStroke.primary.withTilt(sample, primarySensitivity),
                        secondary = baseStroke.secondary.withTilt(sample, secondarySensitivity),
                    )
                }

                else -> baseStroke
            }
        }
    }
}
