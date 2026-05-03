// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.highlight

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.blur.sensor.rememberDeviceTilt

/**
 * Returns a [LightSource] whose UV position shifts with the live device tilt, producing
 * a parallax-like edge bloom that "follows" the device orientation. On platforms without
 * tilt sensors the returned light stays anchored at [basePosition].
 *
 * @param basePosition Position at zero tilt, in normalized UV (see [LightPosition]).
 * @param color Light color. Alpha is folded into the contribution weight.
 * @param intensity Overall scale on the light's contribution.
 * @param sensitivity UV offset applied per radian of tilt. Higher = more dramatic
 *  parallax. `0.1f` shifts the light by 10% of the highlight bounds at 1 rad of tilt.
 */
@Composable
fun rememberTiltLight(
    basePosition: LightPosition,
    color: Color = Color.White,
    @FloatRange(from = 0.0) intensity: Float = 1f,
    @FloatRange(from = 0.0) sensitivity: Float = 0.1f,
): LightSource {
    val tilt by rememberDeviceTilt()
    return remember(tilt, basePosition, color, intensity, sensitivity) {
        LightSource(
            position = LightPosition(
                x = basePosition.x + sensitivity * tilt.roll,
                y = basePosition.y - sensitivity * tilt.pitch,
                z = basePosition.z,
            ),
            color = color,
            intensity = intensity,
        )
    }
}
