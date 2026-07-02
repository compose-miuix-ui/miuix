// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.anim

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import kotlin.math.PI

/**
 * Creates a [SpringSpec] from damping and response parameters.
 *
 * @param damping The damping ratio. 1.0 = critically damped (no overshoot),
 *   < 1.0 = underdamped (oscillates), > 1.0 = overdamped.
 * @param response The response time in seconds. Smaller values = faster animation.
 * @param visibilityThreshold The magnitude below which the animation is considered settled and the
 *   invisible settle tail is cut; null falls back to the framework default displacement threshold.
 */
fun <T> folmeSpring(
    damping: Float,
    response: Float,
    visibilityThreshold: T? = null,
): SpringSpec<T> {
    val stiffness = ((2.0 * PI / response) * (2.0 * PI / response)).toFloat()
    return spring(dampingRatio = damping, stiffness = stiffness, visibilityThreshold = visibilityThreshold)
}
