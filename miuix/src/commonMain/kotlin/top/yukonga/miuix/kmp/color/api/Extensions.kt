// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.color.api

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.color.core.Transforms
import top.yukonga.miuix.kmp.color.space.Hsv
import top.yukonga.miuix.kmp.color.space.OkLab
import top.yukonga.miuix.kmp.color.space.OkLch

/** Convert Compose Color to user-friendly OkLab. */
fun Color.toOkLab(): OkLab {
    val lab = Transforms.colorToOkLab(this)
    val l = (lab[0] * 100.0).coerceIn(0.0, 100.0)
    val a = (lab[1] / 0.4 * 100.0).coerceIn(-100.0, 100.0)
    val b = (lab[2] / 0.4 * 100.0).coerceIn(-100.0, 100.0)
    return OkLab(l, a, b)
}

/** Convert Compose Color to Hvs. */
fun Color.toHsv(): Hsv {
    val hsvArr = Transforms.colorToHsv(this)
    val h = hsvArr[0].toDouble()
    val s = (hsvArr[1] * 100.0).coerceIn(0.0, 100.0)
    val v = (hsvArr[2] * 100.0).coerceIn(0.0, 100.0)
    return Hsv(h, s, v)
}

/** Convert Compose Color to user-friendly OkLch. */
fun Color.toOkLch(): OkLch {
    val lch = Transforms.colorToOklch(this)
    val l = (lch[0] * 100.0).coerceIn(0.0, 100.0)
    val c = (lch[1] / 0.4 * 100.0).coerceIn(0.0, 100.0)
    val h = lch[2].toDouble() // degrees already normalized
    return OkLch(l, c, h)
}
