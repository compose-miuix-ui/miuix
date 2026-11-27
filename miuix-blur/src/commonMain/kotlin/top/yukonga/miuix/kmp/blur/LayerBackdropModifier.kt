// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.requireGraphicsContext
import androidx.compose.ui.platform.InspectorInfo

/**
 * Captures the content of this composable into the given [LayerBackdrop]'s graphics layer.
 * Place this modifier on the container whose content should appear as the blurred background.
 *
 * @param backdrop The [LayerBackdrop] whose graphics layer records this composable's content.
 */
fun Modifier.layerBackdrop(backdrop: LayerBackdrop): Modifier = this then LayerBackdropElement(backdrop)

private class LayerBackdropElement(
    val backdrop: LayerBackdrop,
) : ModifierNodeElement<LayerBackdropNode>() {

    override fun create(): LayerBackdropNode = LayerBackdropNode(backdrop)

    override fun update(node: LayerBackdropNode) {
        if (node.backdrop !== backdrop) {
            node.backdrop.layerCoordinates = null
            node.backdrop = backdrop
            node.invalidateDraw()
        }
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "layerBackdrop"
        properties["backdrop"] = backdrop
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LayerBackdropElement) return false
        return backdrop == other.backdrop
    }

    override fun hashCode(): Int = backdrop.hashCode()
}

private class LayerBackdropNode(
    var backdrop: LayerBackdrop,
) : Modifier.Node(),
    DrawModifierNode,
    GlobalPositionAwareModifierNode {

    private var contentLayer: GraphicsLayer? = null
    private var activeUnderlay: (DrawScope.() -> Unit)? = null

    private val contentOnlyRecordBlock: DrawScope.() -> Unit = {
        (this as? ContentDrawScope)?.drawContent()
    }

    private val underlayCompositeRecordBlock: DrawScope.() -> Unit = {
        val bg = backdrop.backgroundColor()
        if (bg.isSpecified) {
            drawRect(bg)
        }
        activeUnderlay?.invoke(this)
        contentLayer?.let { drawLayer(it) }
    }

    private val defaultRecordBlock: DrawScope.() -> Unit = {
        val bg = backdrop.backgroundColor()
        if (bg.isSpecified) {
            drawRect(bg)
        }
        (this as? ContentDrawScope)?.drawContent()
    }

    private val recordBlock: DrawScope.() -> Unit = {
        (this as? ContentDrawScope)?.let(backdrop.onDraw)
    }

    override fun ContentDrawScope.draw() {
        if (backdrop.isDefaultOnDraw()) {
            val underlay = backdrop.onDrawUnderlay()
            if (underlay != null) {
                val layer = contentLayer ?: requireGraphicsContext().createGraphicsLayer().also { contentLayer = it }
                with(layer) {
                    record(block = contentOnlyRecordBlock)
                }
                drawLayer(layer)
                activeUnderlay = underlay
                with(backdrop.graphicsLayer) {
                    record(block = underlayCompositeRecordBlock)
                }
                activeUnderlay = null
            } else {
                with(backdrop.graphicsLayer) {
                    record(block = defaultRecordBlock)
                }
                drawLayer(backdrop.graphicsLayer)
            }
        } else {
            drawContent()
            with(backdrop.graphicsLayer) {
                record(block = recordBlock)
            }
        }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        if (coordinates.isAttached) {
            backdrop.layerCoordinates = coordinates
        }
    }

    override fun onDetach() {
        backdrop.layerCoordinates = null
        contentLayer?.let { requireGraphicsContext().releaseGraphicsLayer(it) }
        contentLayer = null
        activeUnderlay = null
    }
}
