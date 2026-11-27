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
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.requireGraphicsContext
import androidx.compose.ui.platform.InspectorInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.blur.internal.recordContentLayer
import top.yukonga.miuix.kmp.material.internal.InternalGraphicsResourceApi
import top.yukonga.miuix.kmp.material.internal.awaitGraphicsResourceRefresh
import top.yukonga.miuix.kmp.material.internal.graphicsResourceLifecycle
import top.yukonga.miuix.kmp.material.internal.observeGraphicsResourceResets

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
        node.updateBackdrop(backdrop)
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

@OptIn(InternalGraphicsResourceApi::class)
private class LayerBackdropNode(
    var backdrop: LayerBackdrop,
) : Modifier.Node(),
    DrawModifierNode,
    GlobalPositionAwareModifierNode,
    CompositionLocalConsumerModifierNode {

    private var stopObservingResets: (() -> Unit)? = null
    private var resumeJob: Job? = null
    private var coordinates: LayoutCoordinates? = null

    fun updateBackdrop(backdrop: LayerBackdrop) {
        if (this.backdrop === backdrop) return
        this.backdrop.layerCoordinates = null
        this.backdrop = backdrop
        backdrop.layerCoordinates = coordinates
        invalidateDraw()
    }

    private var contentLayer: GraphicsLayer? = null
    private var activeUnderlay: (DrawScope.() -> Unit)? = null

    private val contentOnlyRecordBlock: ContentDrawScope.() -> Unit = { drawContent() }

    private val underlayCompositeRecordBlock: ContentDrawScope.() -> Unit = {
        val bg = backdrop.backgroundColor()
        if (bg.isSpecified) {
            drawRect(bg)
        }
        activeUnderlay?.invoke(this)
        contentLayer?.let { drawLayer(it) }
    }

    private val defaultRecordBlock: ContentDrawScope.() -> Unit = {
        val bg = backdrop.backgroundColor()
        if (bg.isSpecified) {
            drawRect(bg)
        }
        drawContent()
    }

    override fun onAttach() {
        val ownerLifecycle = graphicsResourceLifecycle() ?: return
        stopObservingResets = observeGraphicsResourceResets(ownerLifecycle) { generation ->
            resumeJob?.cancel()
            recreateGraphicsResources()
            resumeJob = coroutineScope.launch {
                if (awaitGraphicsResourceRefresh(ownerLifecycle, generation) != null) recreateGraphicsResources()
            }
        }
    }

    private val recordBlock: ContentDrawScope.() -> Unit = { backdrop.onDraw(this) }

    private fun recreateGraphicsResources() {
        releaseContentLayer()
        invalidateDraw()
    }

    override fun ContentDrawScope.draw() {
        if (backdrop.isDefaultOnDraw()) {
            val underlay = backdrop.onDrawUnderlay()
            if (underlay != null) {
                val layer = contentLayer ?: requireGraphicsContext().createGraphicsLayer().also { contentLayer = it }
                recordContentLayer(layer, contentOnlyRecordBlock)
                drawLayer(layer)
                activeUnderlay = underlay
                try {
                    recordContentLayer(backdrop.graphicsLayer, underlayCompositeRecordBlock)
                } finally {
                    activeUnderlay = null
                }
            } else {
                recordContentLayer(backdrop.graphicsLayer, defaultRecordBlock)
                drawLayer(backdrop.graphicsLayer)
            }
        } else {
            drawContent()
            recordContentLayer(backdrop.graphicsLayer, recordBlock)
        }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        if (coordinates.isAttached) {
            this.coordinates = coordinates
            backdrop.layerCoordinates = coordinates
        }
    }

    override fun onDetach() {
        resumeJob?.cancel()
        resumeJob = null
        stopObservingResets?.invoke()
        stopObservingResets = null
        coordinates = null
        backdrop.layerCoordinates = null
        releaseContentLayer()
        activeUnderlay = null
    }

    private fun releaseContentLayer() {
        contentLayer?.let { requireGraphicsContext().releaseGraphicsLayer(it) }
        contentLayer = null
    }
}
