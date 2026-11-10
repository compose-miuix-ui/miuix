// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.material.sensor.rememberDeviceTilt

/**
 * Returns [base] with its UV position shifted by the live device tilt, so a stroked edge follows the
 * device orientation. Platforms without a tilt sensor keep the light at [base]. [sensitivity] is the
 * fraction of the stroked bounds shifted per radian of tilt.
 */
@Composable
fun rememberTiltLight(
    base: EdgeLightSource,
    @FloatRange(from = 0.0) sensitivity: Float = 0.1f,
): EdgeLightSource {
    val tilt by rememberDeviceTilt()
    return remember(tilt, base, sensitivity) {
        base.copy(
            x = base.x + sensitivity * tilt.roll,
            y = base.y - sensitivity * tilt.pitch,
        )
    }
}
