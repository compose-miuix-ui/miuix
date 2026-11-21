// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import kotlin.math.max
import kotlin.math.min

/** Shared default constants for every squircle API in this module. */
object SquircleDefaults {

    /** Corner-tile size as a multiple of `cornerRadius`. 1.0 = circular arc, 1.1 = continuous corner. */
    val Extension = 1.1f

    /** Inclusive lower bound for [Extension]. */
    val ExtensionMin = 1f

    /** Inclusive upper bound for [Extension]. */
    val ExtensionMax = 2f
}

/**
 * Cubic Bézier handle ratio used by [addSquircleRect]. Must stay in lock-step with the value the
 * pre-baked SDF was generated for so path-based and shader-backed silhouettes line up.
 */
internal const val SQUIRCLE_CONTROL = 0.643f

/**
 * Appends a squircle-shaped rounded rectangle path. Use this for path-based effects that can't
 * ride the shader pipeline (e.g. `clipPath` reveals rebuilt per frame); for static fills/clips
 * prefer the modifier APIs.
 *
 * Pass `squircleEnabled = false` (typically forwarded from [isSquircleEnabled]) to append a
 * plain rounded rectangle of the same dimensions instead — useful when the surrounding visuals
 * use the shader-backed modifiers' fallback path.
 *
 * @param width The width of the rectangle in pixels; nothing is appended when not positive.
 * @param height The height of the rectangle in pixels; nothing is appended when not positive.
 * @param cornerRadius The corner radius in pixels, clamped to half the smaller side.
 * @param extension The corner-tile size as a multiple of [cornerRadius], clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 * @param squircleEnabled When `false`, appends a plain rounded rectangle instead of the squircle
 *   silhouette; typically forwarded from [isSquircleEnabled].
 */
fun Path.addSquircleRect(
    width: Float,
    height: Float,
    cornerRadius: Float,
    extension: Float = SquircleDefaults.Extension,
    squircleEnabled: Boolean = true,
) = addSquircleRect(width, height, cornerRadius, cornerRadius, cornerRadius, cornerRadius, extension, squircleEnabled)

/** Physical-corner variant of [addSquircleRect], for grouped surfaces with square joining edges. */
fun Path.addSquircleRect(
    width: Float,
    height: Float,
    topLeft: Float,
    topRight: Float,
    bottomRight: Float,
    bottomLeft: Float,
    extension: Float = SquircleDefaults.Extension,
    squircleEnabled: Boolean = true,
) {
    if (width <= 0f || height <= 0f) return
    if (!squircleEnabled) {
        val limit = min(width, height) * 0.5f
        addRoundRect(
            RoundRect(
                left = 0f,
                top = 0f,
                right = width,
                bottom = height,
                topLeftCornerRadius = CornerRadius(topLeft.coerceIn(0f, limit)),
                topRightCornerRadius = CornerRadius(topRight.coerceIn(0f, limit)),
                bottomRightCornerRadius = CornerRadius(bottomRight.coerceIn(0f, limit)),
                bottomLeftCornerRadius = CornerRadius(bottomLeft.coerceIn(0f, limit)),
            ),
        )
        return
    }
    val extClamped = extension.coerceIn(SquircleDefaults.ExtensionMin, SquircleDefaults.ExtensionMax)
    val halfMin = min(width, height) * 0.5f
    val tl = max(0f, topLeft * extClamped).coerceAtMost(halfMin)
    val tr = max(0f, topRight * extClamped).coerceAtMost(halfMin)
    val br = max(0f, bottomRight * extClamped).coerceAtMost(halfMin)
    val bl = max(0f, bottomLeft * extClamped).coerceAtMost(halfMin)
    if (max(max(tl, tr), max(br, bl)) <= 0f) {
        addRect(Rect(0f, 0f, width, height))
        return
    }
    val control = 1f - SQUIRCLE_CONTROL
    moveTo(tl, 0f)
    lineTo(width - tr, 0f)
    cubicTo(width - tr * control, 0f, width, tr * control, width, tr)
    lineTo(width, height - br)
    cubicTo(width, height - br * control, width - br * control, height, width - br, height)
    lineTo(bl, height)
    cubicTo(bl * control, height, 0f, height - bl * control, 0f, height - bl)
    lineTo(0f, tl)
    cubicTo(0f, tl * control, tl * control, 0f, tl, 0f)
    close()
}
