// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.anim

import androidx.compose.runtime.Stable
import kotlin.math.abs
import kotlin.math.sign

@Stable
internal val ParabolaScrollEasing: (distance: Float, range: Int) -> Float = { distance, range ->
    val x = (abs(distance) / range).coerceIn(0.0f, 1.0f)
    val dampedFactor = x - x * x + (x * x * x / 3.0f)
    dampedFactor * range * sign(distance)
}