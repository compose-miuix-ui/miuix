// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.sensor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State

/**
 * Device tilt as Euler angles plus the screen-plane projection of the unit gravity vector.
 *
 * @property pitch Tilt around the device X axis (radians); positive = top tilts away.
 * @property roll Tilt around the device Y axis (radians); positive = right tilts toward.
 * @property gravityX Gravity X in device space (`+X` = right). Sweeps the unit circle
 *  with `gravityY` as the device rotates around its screen normal — info Euler angles
 *  lose. `0` when no sensor.
 * @property gravityY Gravity Y in device space (`+Y` = top). `0` when no sensor.
 */
@Immutable
data class DeviceTilt(
    val pitch: Float,
    val roll: Float,
    val gravityX: Float = 0f,
    val gravityY: Float = 0f,
) {

    companion object {

        @Stable
        val Zero: DeviceTilt = DeviceTilt(0f, 0f, 0f, 0f)
    }
}

/**
 * Returns a live [DeviceTilt] driven by the platform's rotation sensor; on platforms
 * without sensor support (Desktop / Web / iOS / macOS), returns [DeviceTilt.Zero].
 *
 * @param smoothing Low-pass alpha applied to each sensor sample (0 < a ≤ 1).
 *  1.0 = no smoothing (raw samples), 0.1 = heavy smoothing. Default 0.15.
 */
@Composable
expect fun rememberDeviceTilt(smoothing: Float = 0.15f): State<DeviceTilt>
