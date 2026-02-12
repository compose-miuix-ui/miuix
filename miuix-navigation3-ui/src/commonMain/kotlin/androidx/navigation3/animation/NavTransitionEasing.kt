// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.animation

import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

@Immutable
internal class NavTransitionEasing(
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

    fun inverseTransform(fraction: Float, tolerance: Float = 1e-6f): Float {
        if (fraction <= 0f) return 0f
        if (fraction >= 1f) return 1f

        var low = 0f
        var high = 1f
        var mid = 0f

        repeat(16) {
            mid = (low + high) / 2f
            val value = transform(mid)
            if (abs(value - fraction) < tolerance) return mid

            if (value < fraction) {
                low = mid
            } else {
                high = mid
            }
        }
        return mid
    }
}
