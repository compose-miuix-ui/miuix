// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.material.sensor.DeviceTilt
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
): EdgeLightSource = rememberTiltLightState(base = base, sensitivity = sensitivity).value

/**
 * Returns [this] shifted by [tilt] at [sensitivity] fraction of the stroked bounds per radian.
 */
fun EdgeLightSource.withTilt(
    tilt: DeviceTilt,
    @FloatRange(from = 0.0) sensitivity: Float = 0.1f,
): EdgeLightSource = copy(
    x = x + sensitivity * tilt.roll,
    y = y - sensitivity * tilt.pitch,
)

/**
 * Returns a [State] holding [base] with its UV position shifted by the live device tilt, so
 * callers can defer reading the live sensor value to the draw phase (for example inside
 * [Modifier.edgeStroke]).
 */
@Composable
fun rememberTiltLightState(
    base: EdgeLightSource,
    @FloatRange(from = 0.0) sensitivity: Float = 0.1f,
): State<EdgeLightSource> {
    val tilt = rememberDeviceTilt()
    return remember(tilt, base, sensitivity) {
        computedStateOf {
            base.withTilt(tilt = tilt.value, sensitivity = sensitivity)
        }
    }
}
