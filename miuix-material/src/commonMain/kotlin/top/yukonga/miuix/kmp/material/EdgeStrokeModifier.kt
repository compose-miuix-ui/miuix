// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.InspectorInfo

/**
 * Draws [stroke] along the silhouette of [shape], on top of the content.
 *
 * Pair it with `Modifier.drawBackdrop` to light the edge of a blurred surface:
 *
 * ```
 * Modifier
 *     .drawBackdrop(backdrop, shape = { shape }, effects = { blur(20f) })
 *     .edgeStroke(shape = shape, stroke = EdgeStrokes.MiddleLight)
 * ```
 *
 * [alpha] is an overall opacity multiplier such as a press progress. The shape is read on each draw,
 * so an animated one stays current.
 */
fun Modifier.edgeStroke(
    shape: Shape,
    stroke: EdgeStroke,
    alpha: Float = 1f,
): Modifier = this then EdgeStrokeElement(shape = shape, stroke = stroke, alpha = alpha)

/**
 * Draws [stroke] along the silhouette of [shape], on top of the content, reading [stroke] and
 * [alpha] during the draw phase so animated or sensor-driven strokes do not trigger recomposition.
 */
fun Modifier.edgeStroke(
    shape: Shape,
    stroke: () -> EdgeStroke?,
    alpha: () -> Float = { 1f },
): Modifier = this then DeferredEdgeStrokeElement(shape = shape, stroke = stroke, alpha = alpha)

private class EdgeStrokeElement(
    val shape: Shape,
    val stroke: EdgeStroke,
    val alpha: Float,
) : ModifierNodeElement<EdgeStrokeNode>() {

    override fun create(): EdgeStrokeNode = EdgeStrokeNode(
        shape = shape,
        stroke = stroke,
        alpha = alpha,
    )

    override fun update(node: EdgeStrokeNode) {
        node.shape = shape
        node.stroke = stroke
        node.alpha = alpha
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "edgeStroke"
        properties["stroke"] = stroke
        properties["alpha"] = alpha
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EdgeStrokeElement) return false
        if (shape != other.shape) return false
        if (stroke != other.stroke) return false
        if (alpha != other.alpha) return false
        return true
    }

    override fun hashCode(): Int {
        var result = shape.hashCode()
        result = 31 * result + stroke.hashCode()
        result = 31 * result + alpha.hashCode()
        return result
    }
}

private class EdgeStrokeNode(
    var shape: Shape,
    var stroke: EdgeStroke,
    var alpha: Float,
) : Modifier.Node(),
    DrawModifierNode {

    private val brushCache = ShaderBrushCache()

    override fun ContentDrawScope.draw() {
        drawContent()
        drawEdgeStroke(shape = shape, stroke = stroke, alpha = alpha, brushCache = brushCache)
    }
}

private class DeferredEdgeStrokeElement(
    val shape: Shape,
    val stroke: () -> EdgeStroke?,
    val alpha: () -> Float,
) : ModifierNodeElement<DeferredEdgeStrokeNode>() {

    override fun create(): DeferredEdgeStrokeNode = DeferredEdgeStrokeNode(
        shape = shape,
        stroke = stroke,
        alpha = alpha,
    )

    override fun update(node: DeferredEdgeStrokeNode) {
        node.shape = shape
        node.stroke = stroke
        node.alpha = alpha
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "edgeStroke"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DeferredEdgeStrokeElement) return false
        if (shape != other.shape) return false
        if (stroke !== other.stroke) return false
        if (alpha !== other.alpha) return false
        return true
    }

    override fun hashCode(): Int {
        var result = shape.hashCode()
        result = 31 * result + stroke.hashCode()
        result = 31 * result + alpha.hashCode()
        return result
    }
}

private class DeferredEdgeStrokeNode(
    var shape: Shape,
    var stroke: () -> EdgeStroke?,
    var alpha: () -> Float,
) : Modifier.Node(),
    DrawModifierNode {

    private val brushCache = ShaderBrushCache()

    override fun ContentDrawScope.draw() {
        drawContent()
        val resolvedStroke = stroke() ?: return
        drawEdgeStroke(shape = shape, stroke = resolvedStroke, alpha = alpha(), brushCache = brushCache)
    }
}
