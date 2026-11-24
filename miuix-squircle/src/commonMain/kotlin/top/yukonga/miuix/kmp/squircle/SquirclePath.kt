// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

// π/4 — squircle → circle blend threshold shared by shader and path silhouettes.
internal const val BLEND_THRESHOLD_RATIO = 0.7853982f

// Standard quarter-circle cubic Bézier handle ratio: 4 * (sqrt(2) - 1) / 3.
private const val CIRCLE_CONTROL = 0.5522848f

// Finite cap for Dp.Infinity so RoundedCornerShape fallback never computes Inf / Inf = NaN.
private const val MAX_SAFE_CORNER_DP = 1_048_576f

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
    if (!(width > 0f && height > 0f)) return
    val halfMin = min(width, height) * 0.5f
    if (!squircleEnabled) {
        addRoundRect(
            RoundRect(
                left = 0f,
                top = 0f,
                right = width,
                bottom = height,
                topLeftCornerRadius = CornerRadius(sanitizeCornerPx(topLeft, halfMin)),
                topRightCornerRadius = CornerRadius(sanitizeCornerPx(topRight, halfMin)),
                bottomRightCornerRadius = CornerRadius(sanitizeCornerPx(bottomRight, halfMin)),
                bottomLeftCornerRadius = CornerRadius(sanitizeCornerPx(bottomLeft, halfMin)),
            ),
        )
        return
    }
    val extClamped = sanitizeExtension(extension)
    val tl = sanitizeCornerPx(topLeft * extClamped, halfMin)
    val tr = sanitizeCornerPx(topRight * extClamped, halfMin)
    val br = sanitizeCornerPx(bottomRight * extClamped, halfMin)
    val bl = sanitizeCornerPx(bottomLeft * extClamped, halfMin)
    addSquircleTilesRect(width, height, tl, tr, br, bl)
}

internal fun sanitizePositiveDp(value: Dp): Dp {
    val raw = value.value
    if (!(raw > 0f)) return 0.dp
    return if (raw <= MAX_SAFE_CORNER_DP) value else MAX_SAFE_CORNER_DP.dp
}

internal fun sanitizeExtension(extension: Float): Float = if (extension.isNaN()) {
    SquircleDefaults.Extension
} else {
    extension.coerceIn(SquircleDefaults.ExtensionMin, SquircleDefaults.ExtensionMax)
}

private fun sanitizeCornerPx(px: Float, limit: Float): Float = if (px > 0f) px.coerceAtMost(limit) else 0f

private fun cornerHandleOffset(tile: Float, threshold: Float, invRange: Float): Float {
    val baseControl = 1f - SQUIRCLE_CONTROL
    if (tile <= threshold) return tile * baseControl
    val weight = if (invRange <= 0f) 1f else ((tile - threshold) * invRange).coerceIn(0f, 1f)
    return tile * (baseControl + weight * (SQUIRCLE_CONTROL - CIRCLE_CONTROL))
}

/**
 * Appends a squircle silhouette from pre-clamped physical corner tile sizes `[0, min(width, height) / 2]`.
 * Zero-radius corners emit straight line joins rather than degenerate zero-length cubic segments;
 * corners above [BLEND_THRESHOLD_RATIO] blend smoothly toward a circular arc to stay aligned with
 * the shader and hardware capsule silhouettes.
 */
internal fun Path.addSquircleTilesRect(
    width: Float,
    height: Float,
    tl: Float,
    tr: Float,
    br: Float,
    bl: Float,
) {
    if (max(max(tl, tr), max(br, bl)) <= 0f) {
        addRect(Rect(0f, 0f, width, height))
        return
    }
    val halfMin = min(width, height) * 0.5f
    val threshold = halfMin * BLEND_THRESHOLD_RATIO
    val range = halfMin - threshold
    val invRange = if (range > 0f) 1f / range else 0f
    moveTo(tl, 0f)
    lineTo(width - tr, 0f)
    if (tr > 0f) {
        val hTr = cornerHandleOffset(tr, threshold, invRange)
        cubicTo(width - hTr, 0f, width, hTr, width, tr)
    }
    lineTo(width, height - br)
    if (br > 0f) {
        val hBr = cornerHandleOffset(br, threshold, invRange)
        cubicTo(width, height - hBr, width - hBr, height, width - br, height)
    }
    lineTo(bl, height)
    if (bl > 0f) {
        val hBl = cornerHandleOffset(bl, threshold, invRange)
        cubicTo(hBl, height, 0f, height - hBl, 0f, height - bl)
    }
    lineTo(0f, tl)
    if (tl > 0f) {
        val hTl = cornerHandleOffset(tl, threshold, invRange)
        cubicTo(0f, hTl, hTl, 0f, tl, 0f)
    }
    close()
}
