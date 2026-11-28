// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toIntSize

internal fun ContentDrawScope.recordContentLayer(
    layer: GraphicsLayer,
    block: ContentDrawScope.() -> Unit,
) {
    val density = drawContext.density
    layer.record {
        // Compose can install the ContentDrawScope itself as its density while recording.
        // Keep the original Density to prevent a recursive lookup on desktop.
        drawContext.density = density
        this@recordContentLayer.block()
    }
}

context(node: DelegatableNode)
internal fun DrawScope.recordLayer(
    layer: GraphicsLayer,
    size: IntSize = this.size.toIntSize(),
    block: DrawScope.() -> Unit,
) {
    layer.record(node.requireDensity(), layoutDirection, size, block)
}
