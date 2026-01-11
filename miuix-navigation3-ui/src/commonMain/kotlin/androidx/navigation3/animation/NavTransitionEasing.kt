package androidx.navigation3.animation

import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Immutable
internal class NavTransitionEasing(
    response: Float,
    damping: Float,
) : Easing {
    private val c: Float
    private val w: Float
    private val r: Float
    private val c2: Float

    init {
        val k = (6.283185307179586 / response).pow(2.0).toFloat()
        c = ((damping * 12.566370614359172) / response).toFloat()
        w = sqrt((4.0f * k) - (c * c)) / 2.0f
        r = -(c / 2.0f)
        c2 = (r * 1.0f) / w
    }

    override fun transform(fraction: Float): Float {
        return ((2.718281828459045.pow(r * fraction.toDouble()) * ((-1.0f * cos(w * fraction)) + (c2 * sin(w * fraction)))) + 1.0).toFloat()
    }
}