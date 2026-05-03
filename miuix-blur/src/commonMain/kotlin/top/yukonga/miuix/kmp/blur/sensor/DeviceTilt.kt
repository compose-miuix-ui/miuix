// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.sensor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State

/**
 * Device tilt expressed as Euler angles in radians.
 *
 * @property pitch Forward/backward tilt around the device X axis (in radians).
 *  Positive when the top edge tilts away from the user.
 * @property roll  Left/right tilt around the device Y axis (in radians).
 *  Positive when the right edge tilts toward the user.
 */
@Immutable
data class DeviceTilt(
    val pitch: Float,
    val roll: Float,
) {

    companion object {

        @Stable
        val Zero: DeviceTilt = DeviceTilt(0f, 0f)
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
