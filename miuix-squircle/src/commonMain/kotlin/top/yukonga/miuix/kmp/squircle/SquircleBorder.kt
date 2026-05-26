// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp

/**
 * Squircle stroke around this layout, inset by half the stroke width so it
 * lines up with a same-radius [squircleBackground] / [squircleSurface].
 *
 * Path-based; no shader required. Path is rebuilt only when size changes.
 */
fun Modifier.squircleBorder(
    width: Dp,
    color: Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
    control: Float = SquircleDefaults.Control,
): Modifier = drawWithCache {
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
            control = control,
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
