// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.color.core

import androidx.annotation.IntRange
import androidx.annotation.Size
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.color.space.Hsv
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cbrt
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

private fun toDegrees(rad: Double): Double = rad * 180.0 / PI
private fun toRadians(deg: Double): Double = deg * PI / 180.0

/**
 * Core color processing and transform implementations migrated from ColorUtils.
 * Method names, parameters, and behavior remain unchanged.
 */
object Transforms {
    /**
     * Convert an RGB color-space vector (sRGB, 0..1) to OkLab color space.
     * @param color RGB vector [r, g, b] with each component in 0..1
     * @return OkLab vector [L, a, b], where L is approximately 0..1
     */
    fun rgbToOkLab(color: FloatArray): FloatArray {
        val l = cbrt(0.4122214708f * color[0] + 0.5363325363f * color[1] + 0.0514459929f * color[2])
        val m = cbrt(0.2119034982f * color[0] + 0.6806995451f * color[1] + 0.1073969566f * color[2])
        val s = cbrt(0.0883024619f * color[0] + 0.2817188376f * color[1] + 0.6299787005f * color[2])

        return floatArrayOf(
            0.2104542553f * l + 0.7936177850f * m - 0.0040720468f * s,
            1.9779984951f * l - 2.4285922050f * m + 0.4505937099f * s,
            0.0259040371f * l + 0.7827717662f * m - 0.8086757660f * s,
        )
    }

    /**
     * Convert an OkLab color-space vector to sRGB.
     * @param color OkLab vector [l, a, b]
     * @return sRGB vector [r, g, b], each component clamped to 0..1
     */
    fun okLabToRgb(color: FloatArray): FloatArray {
        val l1 = color[0] + 0.3963377774f * color[1] + 0.2158037573f * color[2]
        val m1 = color[0] - 0.1055613458f * color[1] - 0.0638541728f * color[2]
        val s1 = color[0] - 0.0894841775f * color[1] - 1.2914855480f * color[2]

        val l = l1 * l1 * l1
        val m = m1 * m1 * m1
        val s = s1 * s1 * s1

        return floatArrayOf(
            (+4.0767416621f * l - 3.3077115913f * m + 0.2309699292f * s).coerceIn(0f, 1f),
            (-1.2684380046f * l + 2.6097574011f * m - 0.3413193965f * s).coerceIn(0f, 1f),
            (-0.0041960863f * l - 0.7034186147f * m + 1.7076147010f * s).coerceIn(0f, 1f),
        )
    }

    /**
     * Convert OkLab [l,a,b] to OkLCH [l,c,h].
     */
    fun okLabToOklch(lab: FloatArray): FloatArray {
        val l = lab[0]
        val a = lab[1]
        val b = lab[2]
        val c = kotlin.math.sqrt(a * a + b * b)
        var h = toDegrees(atan2(b.toDouble(), a.toDouble())).toFloat()
        if (h < 0f) h += 360f
        return floatArrayOf(l, c, h)
    }

    /**
     * Convert OkLCH [l,c,h] to OkLab [l,a,b].
     */
    fun oklchToOkLab(lch: FloatArray): FloatArray {
        val l = lch[0]
        val c = lch[1]
        val hRad = toRadians(lch[2].toDouble()).toFloat()
        val a = c * cos(hRad)
        val b = c * sin(hRad)
        return floatArrayOf(l, a, b)
    }

    /**
     * Convert an OkLab color vector to a Compose Color (sRGB).
     * @param okLab OkLab vector [L, a, b]
     * @param alpha Alpha in 0..1
     * @return Compose Color in sRGB
     */
    fun okLabToColor(okLab: FloatArray, alpha: Float = 1f): Color {
        val rgb = okLabToRgb(okLab)
        return Color(rgb[0], rgb[1], rgb[2], alpha)
    }

    /**
     * Convert OkLCH to Compose Color (sRGB).
     * @param l Lightness in 0..1
     * @param c Chroma in 0..0.4 (typical sRGB gamut)
     * @param h Hue in degrees [0,360)
     */
    fun oklchToColor(l: Float, c: Float, h: Float, alpha: Float = 1f): Color {
        val norm = normalizeOklch(l, c, h)
        val lab = oklchToOkLab(norm)
        return okLabToColor(lab, alpha)
    }

    /**
     * Convert a Compose Color (sRGB) to OkLab.
     * @param color Compose Color in sRGB
     * @return OkLab vector [l, a, b]
     */
    fun colorToOkLab(color: Color): FloatArray {
        val rgb = floatArrayOf(color.red, color.green, color.blue)
        return rgbToOkLab(rgb)
    }

    /** Convert Compose Color to OkLCH [l,c,h]. */
    fun colorToOklch(color: Color): FloatArray {
        val lab = colorToOkLab(color)
        val lch = okLabToOklch(lab)
        // Clamp c to [0,0.4] for typical sRGB and normalize hue
        val c = lch[1].coerceIn(0f, 0.4f)
        var h = lch[2] % 360f
        if (h < 0f) h += 360f
        return floatArrayOf(lch[0].coerceIn(0f, 1f), c, h)
    }

    /**
     * Convert 8-bit sRGB (0..255) to HSV color space.
     * @param r Red 0..255
     * @param g Green 0..255
     * @param b Blue 0..255
     * @param hsv Output array [h, s, v]: h in degrees 0..360, s in 0..1, v in 0..1
     */
    fun rgbToHsv(
        @IntRange(0, 255) r: Int,
        @IntRange(0, 255) g: Int,
        @IntRange(0, 255) b: Int,
        @Size(3) hsv: FloatArray,
    ) {
        val rf = r / 255f
        val gf = g / 255f
        val bf = b / 255f

        val max = maxOf(rf, gf, bf)
        val min = minOf(rf, gf, bf)
        val delta = max - min

        val hue = when {
            delta == 0f -> 0f
            max == rf -> (60f * ((gf - bf) / delta) + 360f) % 360f
            max == gf -> (60f * ((bf - rf) / delta) + 120f) % 360f
            else -> (60f * ((rf - gf) / delta) + 240f) % 360f
        }

        hsv[0] = hue.coerceIn(0f, 360f)
        hsv[1] = (if (max > 0f) delta / max else 0f).coerceIn(0f, 1f)
        hsv[2] = max.coerceIn(0f, 1f)
    }

    /**
     * Convert a Compose Color (sRGB) to HSV.
     * @param color Color in sRGB
     * @return HSV array [h, s, v]: h in degrees 0..360, s in 0..1, v in 0..1
     */
    fun colorToHsv(color: Color): FloatArray {
        val hsvArr = FloatArray(3)
        rgbToHsv(
            (color.red * 255).toInt(),
            (color.green * 255).toInt(),
            (color.blue * 255).toInt(),
            hsvArr,
        )
        return hsvArr
    }

    /**
     * Convert sRGB (0..1) to OkHSV color space.
     * @return OkHSV array [h, s, v] with h in 0..1, s in 0..1, v in 0..1
     */
    fun srgbToOkhsv(r: Float, g: Float, b: Float): FloatArray {
        val lab = linearSrgbToOklab(
            srgbTransferFunctionInv(r),
            srgbTransferFunctionInv(g),
            srgbTransferFunctionInv(b),
        )

        val c = kotlin.math.sqrt(lab[1] * lab[1] + lab[2] * lab[2])
        val a = if (c == 0f) 0f else lab[1] / c
        val b = if (c == 0f) 0f else lab[2] / c

        val l = lab[0]
        val h = 0.5f + (0.5f * atan2(-lab[2], -lab[1])) / PI.toFloat()

        val stMax = getSTMax(a, b)
        val sMax = stMax[0]
        val s0 = 0.5f
        val tMax = stMax[1]
        val k = 1f - s0 / sMax

        val t = tMax / (c + l * tMax)
        val lv = t * l
        val cv = t * c

        val lvt = toeInv(lv)
        val cvt = (cv * lvt) / lv

        val rgbScale = oklabToLinearSrgb(lvt, a * cvt, b * cvt)
        val scaleL = cbrt(
            1f / maxOf(rgbScale[0], rgbScale[1], rgbScale[2], 0f),
        )

        var l2 = l / scaleL
        l2 = toe(l2)

        val v = l2 / lv
        val s = ((s0 + tMax) * cv) / (tMax * s0 + tMax * k * cv)

        return floatArrayOf(h, s, v)
    }

    /**
     * Convert OkHSV to sRGB color space.
     * @param h Hue in 0..1
     * @param s Saturation in 0..1
     * @param v Value in 0..1
     * @return sRGB array [r, g, b] in 0..1
     */
    fun okhsvToSrgb(h: Float, s: Float, v: Float): FloatArray {
        val a = cos(2f * PI.toFloat() * h)
        val b = sin(2f * PI.toFloat() * h)

        val stMax = getSTMax(a, b)
        val sMax = stMax[0]
        val s0 = 0.5f
        val tMax = stMax[1]
        val k = 1f - s0 / sMax

        val lv = 1f - (s * s0) / (s0 + tMax - tMax * k * s)
        val cv = (s * tMax * s0) / (s0 + tMax - tMax * k * s)

        var l = v * lv
        var c = v * cv

        val lvt = toeInv(lv)
        val cvt = (cv * lvt) / lv

        val lNew = toeInv(l)
        c = (c * lNew) / l
        l = lNew

        val rgbScale = oklabToLinearSrgb(lvt, a * cvt, b * cvt)
        val scaleL = cbrt(
            1f / maxOf(rgbScale[0], rgbScale[1], rgbScale[2], 0f),
        )

        l *= scaleL
        c *= scaleL

        val rgb = oklabToLinearSrgb(l, c * a, c * b)
        return floatArrayOf(
            srgbTransferFunction(rgb[0]),
            srgbTransferFunction(rgb[1]),
            srgbTransferFunction(rgb[2]),
        )
    }

    /**
     * Convert a Compose Color (sRGB) to OkHSV.
     * @return OkHSV array [h, s, v] with h in 0..1
     */
    fun colorToOkhsv(color: Color): FloatArray = srgbToOkhsv(color.red, color.green, color.blue)

    /**
     * Convert OkHSV to Compose Color (sRGB).
     * @param h Hue in 0..1
     * @param s Saturation in 0..1
     * @param v Value in 0..1
     * @param alpha Alpha in 0..1
     */
    fun okhsvToColor(h: Float, s: Float, v: Float, alpha: Float = 1f): Color {
        val srgb = okhsvToSrgb(h, s, v)
        return Color(srgb[0], srgb[1], srgb[2], alpha)
    }

    private fun srgbTransferFunction(a: Float): Float = if (0.0031308f >= a) {
        12.92f * a
    } else {
        1.055f * a.pow(0.4166666666666667f) - 0.055f
    }

    private fun srgbTransferFunctionInv(a: Float): Float = if (0.04045f < a) {
        ((a + 0.055f) / 1.055f).pow(2.4f)
    } else {
        a / 12.92f
    }

    private fun linearSrgbToOklab(r: Float, g: Float, b: Float): FloatArray {
        val l = 0.4122214708f * r + 0.5363325363f * g + 0.0514459929f * b
        val m = 0.2119034982f * r + 0.6806995451f * g + 0.1073969566f * b
        val s = 0.0883024619f * r + 0.2817188376f * g + 0.6299787005f * b

        val lCbrt = cbrt(l)
        val mCbrt = cbrt(m)
        val sCbrt = cbrt(s)

        return floatArrayOf(
            0.2104542553f * lCbrt + 0.793617785f * mCbrt - 0.0040720468f * sCbrt,
            1.9779984951f * lCbrt - 2.428592205f * mCbrt + 0.4505937099f * sCbrt,
            0.0259040371f * lCbrt + 0.7827717662f * mCbrt - 0.808675766f * sCbrt,
        )
    }

    private fun oklabToLinearSrgb(l: Float, a: Float, b: Float): FloatArray {
        val lCbrt = l + 0.3963377774f * a + 0.2158037573f * b
        val mCbrt = l - 0.1055613458f * a - 0.0638541728f * b
        val sCbrt = l - 0.0894841775f * a - 1.291485548f * b

        val lLin = lCbrt * lCbrt * lCbrt
        val mLin = mCbrt * mCbrt * mCbrt
        val sLin = sCbrt * sCbrt * sCbrt

        return floatArrayOf(
            4.0767416621f * lLin - 3.3077115913f * mLin + 0.2309699292f * sLin,
            -1.2684380046f * lLin + 2.6097574011f * mLin - 0.3413193965f * sLin,
            -0.0041960863f * lLin - 0.7034186147f * mLin + 1.707614701f * sLin,
        )
    }

    private fun toe(x: Float): Float {
        val k1 = 0.206f
        val k2 = 0.03f
        val k3 = (1f + k1) / (1f + k2)

        return 0.5f * (k3 * x - k1 + kotlin.math.sqrt((k3 * x - k1) * (k3 * x - k1) + 4f * k2 * k3 * x))
    }

    private fun toeInv(x: Float): Float {
        val k1 = 0.206f
        val k2 = 0.03f
        val k3 = (1f + k1) / (1f + k2)
        return (x * x + k1 * x) / (k3 * (x + k2))
    }

    private fun computeMaxSaturation(a: Float, b: Float): Float {
        var k0: Float
        var k1: Float
        var k2: Float
        var k3: Float
        var k4: Float
        var wl: Float
        var wm: Float
        var ws: Float

        if (-1.88170328f * a - 0.80936493f * b > 1f) {
            // Red component
            k0 = 1.19086277f
            k1 = 1.76576728f
            k2 = 0.59662641f
            k3 = 0.75515197f
            k4 = 0.56771245f
            wl = 4.0767416621f
            wm = -3.3077115913f
            ws = 0.2309699292f
        } else if (1.81444104f * a - 1.19445276f * b > 1f) {
            // Green component
            k0 = 0.73956515f
            k1 = -0.45954404f
            k2 = 0.08285427f
            k3 = 0.1254107f
            k4 = 0.14503204f
            wl = -1.2684380046f
            wm = 2.6097574011f
            ws = -0.3413193965f
        } else {
            // Blue component
            k0 = 1.35733652f
            k1 = -0.00915799f
            k2 = -1.1513021f
            k3 = -0.50559606f
            k4 = 0.00692167f
            wl = -0.0041960863f
            wm = -0.7034186147f
            ws = 1.707614701f
        }

        var s = k0 + k1 * a + k2 * b + k3 * a * a + k4 * a * b

        val kL = 0.3963377774f * a + 0.2158037573f * b
        val kM = -0.1055613458f * a - 0.0638541728f * b
        val kS = -0.0894841775f * a - 1.291485548f * b

        val lCbrt = 1f + s * kL
        val mCbrt = 1f + s * kM
        val sCbrt = 1f + s * kS

        val lLin = lCbrt * lCbrt * lCbrt
        val mLin = mCbrt * mCbrt * mCbrt
        val sLin = sCbrt * sCbrt * sCbrt

        val lDs = 3f * kL * lCbrt * lCbrt
        val mDs = 3f * kM * mCbrt * mCbrt
        val sDs = 3f * kS * sCbrt * sCbrt

        val lDs2 = 6f * kL * kL * lCbrt
        val mDs2 = 6f * kM * kM * mCbrt
        val sDs2 = 6f * kS * kS * sCbrt

        val f = wl * lLin + wm * mLin + ws * sLin
        val f1 = wl * lDs + wm * mDs + ws * sDs
        val f2 = wl * lDs2 + wm * mDs2 + ws * sDs2

        s -= (f * f1) / (f1 * f1 - 0.5f * f * f2)

        return s
    }

    private fun findCusp(a: Float, b: Float): FloatArray {
        val sCusp = computeMaxSaturation(a, b)
        val rgbAtMax = oklabToLinearSrgb(1f, sCusp * a, sCusp * b)
        val lCusp = cbrt(
            1f / maxOf(rgbAtMax[0], rgbAtMax[1], rgbAtMax[2]),
        )
        val cCusp = lCusp * sCusp
        return floatArrayOf(lCusp, cCusp)
    }

    private fun getSTMax(a: Float, b: Float, c: FloatArray? = null): FloatArray {
        val cuspActual = c ?: findCusp(a, b)
        val l = cuspActual[0]
        val c = cuspActual[1]
        return floatArrayOf(c / l, c / (1f - l))
    }

    /**
     * Generate HSV hue colors for the hue slider (using Hsv API, bright and saturated).
     */
    fun generateHsvHueColors(): List<Color> {
        val steps = 36
        return (0 until steps).map { i ->
            val hue = i.toDouble() / steps.toDouble() * 360.0
            Hsv(hue, 100.0, 100.0).toColor()
        }
    }

    /**
     * Generate OkHSV hue colors for the hue slider (perceptual smoothing).
     */
    fun generateOkHsvHueColors(): List<Color> {
        val steps = 36
        return (0 until steps).map { i ->
            val hue = i.toFloat() / steps.toFloat()
            okhsvToColor(hue, 1f, 1f)
        }
    }

    // Simple cache for OkLCH hue colors keyed by rounded L and C
    private val okLchHueCache = mutableMapOf<String, List<Color>>()

    /**
     * Generate OkLCH hue colors for a hue slider at given lightness and chroma.
     * @param l Lightness in 0..1
     * @param c Chroma proportion 0..1 (scaled to 0..0.4 internally)
     * @param steps Number of hue samples
     */
    fun generateOkLchHueColors(l: Float, c: Float, steps: Int = 36): List<Color> {
        val lClamped = l.coerceIn(0f, 1f)
        val cClamped = c.coerceIn(0f, 1f)
        val key = "${(lClamped * 100).toInt()}:${(cClamped * 100).toInt()}:$steps"
        return okLchHueCache.getOrPut(key) {
            val cInternal = cClamped * 0.4f
            (0 until steps).map { i ->
                val hDeg = i.toFloat() / steps.toFloat() * 360f
                oklchToColor(lClamped, cInternal, hDeg)
            }
        }
    }

    /** Normalize OkLCH input to safe ranges and canonical hue degrees. */
    fun normalizeOklch(l: Float, c: Float, h: Float): FloatArray {
        val ln = l.coerceIn(0f, 1f)
        val cn = c.coerceIn(0f, 0.4f)
        var hn = h % 360f
        if (hn < 0f) hn += 360f
        return floatArrayOf(ln, cn, hn)
    }
}
