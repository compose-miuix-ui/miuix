// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.highlight

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.util.fastCoerceAtMost
import top.yukonga.miuix.kmp.blur.LocalRuntimeShaderCache
import top.yukonga.miuix.kmp.blur.RuntimeShaderCache

/**
 * Draws an edge highlight on top of this composable.
 *
 * The highlight is shaped by [shape] and styled by the provided [highlight]; pass `null`
 * (or a [Highlight] with `width = 0.dp`) to skip drawing. Combine with `Modifier.drawBackdrop`
 * to layer the highlight over a blurred backdrop.
 */
fun Modifier.highlight(
    shape: Shape,
    highlight: Highlight? = Highlight.Default,
): Modifier = this then HighlightElement(shape, highlight)

private class HighlightElement(
    val shape: Shape,
    val highlight: Highlight?,
) : ModifierNodeElement<HighlightNode>() {

    override fun create(): HighlightNode = HighlightNode(shape, highlight)

    override fun update(node: HighlightNode) {
        node.shape = shape
        node.highlight = highlight
        node.invalidateDraw()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "highlight"
        properties["shape"] = shape
        properties["highlight"] = highlight
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HighlightElement) return false
        if (shape != other.shape) return false
        if (highlight != other.highlight) return false
        return true
    }

    override fun hashCode(): Int {
        var result = shape.hashCode()
        result = 31 * result + (highlight?.hashCode() ?: 0)
        return result
    }
}

private class HighlightNode(
    var shape: Shape,
    var highlight: Highlight?,
) : Modifier.Node(),
    DrawModifierNode,
    CompositionLocalConsumerModifierNode {

    override val shouldAutoInvalidate: Boolean = false

    private val paint = Paint()

    private val runtimeShaderCache: RuntimeShaderCache
        get() = currentValueOf(LocalRuntimeShaderCache)

    override fun ContentDrawScope.draw() {
        drawContent()

        val highlight = highlight ?: return
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
}
