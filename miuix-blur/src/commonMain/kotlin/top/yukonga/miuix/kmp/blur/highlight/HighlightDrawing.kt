// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.highlight

import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.util.fastCoerceAtMost
import top.yukonga.miuix.kmp.blur.RuntimeShaderCache

/**
 * Paints [highlight] over the current draw region, shaped by [shape]. Skips when the highlight
 * would be invisible (zero width / alpha, empty bounds, or no shader on this platform).
 */
internal fun ContentDrawScope.drawHighlight(
    highlight: Highlight,
    shape: Shape,
    runtimeShaderCache: RuntimeShaderCache,
    paint: Paint,
) {
    if (highlight.width.value <= 0f || highlight.alpha <= 0f) return

    val sizePx = size
    if (sizePx.width <= 0f || sizePx.height <= 0f) return

    val strokeWidthPx = highlight.width.toPx().fastCoerceAtMost(sizePx.minDimension / 2f)
    val shader = with(highlight.style) {
        createShader(
            shape = shape,
            strokeWidthPx = strokeWidthPx,
            highlightAlpha = highlight.alpha,
            runtimeShaderCache = runtimeShaderCache,
        )
    } ?: return

    paint.shader = shader
    paint.blendMode = highlight.style.blendMode

    drawIntoCanvas { canvas ->
        canvas.drawRect(
            left = 0f,
            top = 0f,
            right = sizePx.width,
            bottom = sizePx.height,
            paint = paint,
        )
    }

    paint.shader = null
}
