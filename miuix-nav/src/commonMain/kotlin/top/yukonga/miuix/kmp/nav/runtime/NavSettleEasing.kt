// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * The step response of an underdamped spring baked into an [Easing], played over a fixed tween
 * duration.
 *
 * This reproduces, point for point, the established enter/exit curve of miuix navigation, so
 * programmatic push/pop keeps the exact same pacing: a brisk middle (≈48% at a fifth of the
 * duration) and a long, gentle tail. The curve is monotone within `0..1` for the shipped
 * parameters (its first crossing of
 * `1` lies past the played window), so it never bounces; `transform(1f)` lands at ≈0.99933 and
 * the tween's final frame snaps the residue.
 *
 * Baking the spring solution into an easing (instead of running a live spring) is what pins the
 * total duration: a live spring's settle time scales with distance and threshold, while a tween
 * always completes in exactly its configured duration — matching the reference feel. Gesture
 * handoffs still use the live shared spring ([settleTo]) because they must seed a release
 * velocity, which a tween cannot do.
 *
 * @param response oscillation period of the underlying spring, in units of the played duration.
 * @param damping damping ratio of the underlying spring (`< 1`, underdamped).
 */
@Immutable
internal class NavSettleEasing(
    response: Float,
    damping: Float,
) : Easing {
    private val r: Float
    private val w: Float
    private val c2: Float

    init {
        val omega = 2.0 * PI / response
        val k = omega * omega
        val c = damping * 4.0 * PI / response

        w = (sqrt(4.0 * k - c * c) / 2.0).toFloat()
        r = (-c / 2.0).toFloat()
        c2 = r / w
    }

    override fun transform(fraction: Float): Float {
        val t = fraction.toDouble()
        val decay = exp(r * t)
        return (decay * (-cos(w * t) + c2 * sin(w * t)) + 1.0).toFloat()
    }
}

/**
 * The single programmatic settle curve (`response = 0.8`, `damping = 0.95`), identical to the
 * `NavAnimationEasing` constant the nav3-based miuix navigation ships. The default
 * `NavMotion.programmatic` curve; public so transition presets can reuse the established curve
 * in custom [top.yukonga.miuix.kmp.nav.transition.NavSettleSpec.Tween]s.
 */
val NavProgrammaticEasing: Easing = NavSettleEasing(response = 0.8f, damping = 0.95f)
