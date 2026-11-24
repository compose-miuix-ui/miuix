// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.blur.internal.recordLayer
import top.yukonga.miuix.kmp.material.internal.awaitGraphicsResourceRefresh
import top.yukonga.miuix.kmp.material.internal.graphicsResourceLifecycle
import top.yukonga.miuix.kmp.material.internal.graphicsResourcesResetOnResume
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
        val resetOnResume = graphicsResourcesResetOnResume
        if (!resetOnResume && this.backdrop === backdrop) return
        if (this.backdrop !== backdrop) {
            this.backdrop.layerCoordinates = null
            this.backdrop = backdrop
            if (resetOnResume) backdrop.layerCoordinates = coordinates
        }
        invalidateDraw()
    }

    override fun onAttach() {
        val ownerLifecycle = graphicsResourceLifecycle() ?: return
        stopObservingResets = observeGraphicsResourceResets(ownerLifecycle) { generation ->
            resumeJob?.cancel()
            invalidateDraw()
            resumeJob = coroutineScope.launch {
                if (awaitGraphicsResourceRefresh(ownerLifecycle, generation) != null) invalidateDraw()
            }
        }
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        recordLayer(backdrop.graphicsLayer) { backdrop.onDraw(this@draw) }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        if (coordinates.isAttached) {
            if (graphicsResourcesResetOnResume) this.coordinates = coordinates
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
    }
}
