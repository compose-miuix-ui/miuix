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
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import top.yukonga.miuix.kmp.blur.internal.recordLayer

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

    private var lifecycle: Lifecycle? = null
    private var lifecycleObserver: LifecycleEventObserver? = null
    private var coordinates: LayoutCoordinates? = null

    fun updateBackdrop(backdrop: LayerBackdrop) {
        if (this.backdrop !== backdrop) {
            this.backdrop.layerCoordinates = null
            this.backdrop = backdrop
            backdrop.layerCoordinates = coordinates
        }
        invalidateDraw()
    }

    override fun onAttach() {
        val ownerLifecycle = currentValueOf(LocalLifecycleOwner).lifecycle
        lifecycle = ownerLifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Force recordLayer to capture a fresh recording after the platform graphics
                // resources were invalidated while the app was paused.
                invalidateDraw()
            }
        }
        lifecycleObserver = observer
        ownerLifecycle.addObserver(observer)
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        recordLayer(backdrop.graphicsLayer) { backdrop.onDraw(this@draw) }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        if (coordinates.isAttached) {
            this.coordinates = coordinates
            backdrop.layerCoordinates = coordinates
        }
    }

    override fun onDetach() {
        lifecycleObserver?.let { observer -> lifecycle?.removeObserver(observer) }
        lifecycleObserver = null
        lifecycle = null
        coordinates = null
        backdrop.layerCoordinates = null
    }
}
