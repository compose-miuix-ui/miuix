// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp

/**
 * Squircle stroke around this layout, inset by half the stroke width so it lines up with a
 * same-radius [squircleBackground] / [squircleSurface]. Path-based; no shader required, rebuilt
 * only when size changes. Falls back to `Modifier.border(...)` with `RoundedCornerShape` whenever
 * [isSquircleEnabled] is `false`, keeping borders aligned with the fill APIs' fallback.
 *
 * @param width The stroke width of the border.
 * @param color The stroke [Color] of the border.
 * @param cornerRadius The radius applied uniformly to all four corners.
 * @param extension The corner-tile size as a multiple of [cornerRadius], clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
fun Modifier.squircleBorder(
    width: Dp,
    color: Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    if (!isSquircleEnabled()) {
        return this.border(width, color, RoundedCornerShape(cornerRadius))
    }
    return this.drawWithCache {
        val widthPx = width.toPx()
        val cornerRadiusPx = cornerRadius.toPx()
        val halfStroke = widthPx / 2f
        val innerW = size.width - widthPx
        val innerH = size.height - widthPx
        val path = Path()
        val drawable = widthPx > 0f && innerW > 0f && innerH > 0f
        if (drawable) {
            val innerCornerRadius = (cornerRadiusPx - halfStroke).coerceAtLeast(0f)
            path.addSquircleRect(
                width = innerW,
                height = innerH,
                cornerRadius = innerCornerRadius,
                extension = extension,
            )
        }
        val stroke = Stroke(width = widthPx)
        onDrawBehind {
            if (drawable) {
                translate(halfStroke, halfStroke) {
                    drawPath(path = path, color = color, style = stroke)
                }
            }
        }
    }
}

/**
 * Deferred-read variant of [squircleBorder]. [width] and [color] are sampled inside the draw
 * scope, so animating either via `animateDpAsState` / `animateColorAsState` updates the stroke
 * without recomposing this composable or its subtree. Falls back to a stroked rounded-rect path
 * (rather than [Modifier.border]) when [isSquircleEnabled] is `false`.
 *
 * @param width Lambda returning the stroke width, read inside the draw scope each frame.
 * @param color Lambda returning the stroke [Color], read inside the draw scope each frame.
 * @param cornerRadius The radius applied uniformly to all four corners.
 * @param extension The corner-tile size as a multiple of [cornerRadius], clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
fun Modifier.squircleBorder(
    width: () -> Dp,
    color: () -> Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val squircleEnabled = isSquircleEnabled()
    return this.drawWithCache {
        val cornerRadiusPx = cornerRadius.toPx()
        val path = Path()
        onDrawBehind {
            val widthPx = width().toPx()
            if (widthPx <= 0f) return@onDrawBehind
            val halfStroke = widthPx / 2f
            val innerW = size.width - widthPx
            val innerH = size.height - widthPx
            if (innerW <= 0f || innerH <= 0f) return@onDrawBehind
            val innerCornerRadius = (cornerRadiusPx - halfStroke).coerceAtLeast(0f)
            path.rewind()
            path.addSquircleRect(
                width = innerW,
                height = innerH,
                cornerRadius = innerCornerRadius,
                extension = extension,
                squircleEnabled = squircleEnabled,
            )
            translate(halfStroke, halfStroke) {
                drawPath(path = path, color = color(), style = Stroke(width = widthPx))
            }
        }
    }
}
