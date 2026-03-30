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
    val l = (lab[0] * 100f).coerceIn(0f, 100f)
    val a = (lab[1] / 0.4f * 100f).coerceIn(-100f, 100f)
    val b = (lab[2] / 0.4f * 100f).coerceIn(-100f, 100f)
    return OkLab(l, a, b)
}

/** Convert Compose Color to Hvs. */
fun Color.toHsv(): Hsv {
    val hsvArr = Transforms.colorToHsv(this)
    val h = hsvArr[0]
    val s = (hsvArr[1] * 100f).coerceIn(0f, 100f)
    val v = (hsvArr[2] * 100f).coerceIn(0f, 100f)
    return Hsv(h, s, v)
}

/** Convert Compose Color to user-friendly OkLch. */
fun Color.toOkLch(): OkLch {
    val lch = Transforms.colorToOklch(this)
    val l = (lch[0] * 100f).coerceIn(0f, 100f)
    val c = (lch[1] / 0.4f * 100f).coerceIn(0f, 100f)
    val h = lch[2] // degrees already normalized
    return OkLch(l, c, h)
}
