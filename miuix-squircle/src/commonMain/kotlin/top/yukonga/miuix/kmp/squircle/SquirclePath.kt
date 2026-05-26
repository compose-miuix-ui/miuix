// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.ui.graphics.Path
import kotlin.math.max
import kotlin.math.min

/** Shared default constants for every squircle API in this module. */
object SquircleDefaults {

    /** Corner-tile size as a multiple of `cornerRadius`. 1.0 = circular arc, 1.1 = continuous corner. */
    val Extension = 1.1f

    /** Cubic Bézier handle ratio. 0.55228 reproduces a quarter circle; 0.63 is the squircle look. */
    val Control = 0.63f

    /** Inclusive lower bound for [Extension]. */
    val ExtensionMin = 1f

    /** Inclusive upper bound for [Extension]. */
    val ExtensionMax = 2f

    /** Inclusive lower bound for [Control]. */
    val ControlMin = 0.3f

    /** Inclusive upper bound for [Control]. */
    val ControlMax = 0.9f
}

/**
 * Appends a squircle-shaped rounded rectangle path. Use this for path-based
 * effects that can't ride the shader pipeline (e.g. `clipPath` reveals
 * rebuilt per frame); for static fills/clips prefer the modifier APIs.
 */
fun Path.addSquircleRect(
    width: Float,
    height: Float,
    cornerRadius: Float,
    extension: Float = SquircleDefaults.Extension,
    control: Float = SquircleDefaults.Control,
) {
    if (width <= 0f || height <= 0f) return
    val extClamped = extension.coerceIn(SquircleDefaults.ExtensionMin, SquircleDefaults.ExtensionMax)
    val ctrlClamped = control.coerceIn(SquircleDefaults.ControlMin, SquircleDefaults.ControlMax)
    val halfMin = min(width, height) * 0.5f
    val tile = max(0f, cornerRadius * extClamped).coerceAtMost(halfMin)
    if (tile <= 0f) {
        addRect(androidx.compose.ui.geometry.Rect(0f, 0f, width, height))
        return
    }
    val handle = tile * (1f - ctrlClamped)
    moveTo(tile, 0f)
    lineTo(width - tile, 0f)
    cubicTo(width - handle, 0f, width, handle, width, tile)
    lineTo(width, height - tile)
    cubicTo(width, height - handle, width - handle, height, width - tile, height)
    lineTo(tile, height)
    cubicTo(handle, height, 0f, height - handle, 0f, height - tile)
    lineTo(0f, tile)
    cubicTo(0f, handle, handle, 0f, tile, 0f)
    close()
}
