// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Density
import top.yukonga.miuix.kmp.blur.internal.InverseLayerScope
import kotlin.math.roundToInt

private val DefaultOnDraw: ContentDrawScope.() -> Unit = { drawContent() }

/**
 * Creates and remembers a [LayerBackdrop] that captures content from a [GraphicsLayer].
 *
 * Use [Modifier.layerBackdrop][layerBackdrop] on the content container to capture its
 * rendered output, then pass this [LayerBackdrop] to blur modifiers.
 *
 * @param graphicsLayer The graphics layer to record content into.
 * @param onDraw Custom draw logic for the layer content.
 */
@Composable
fun rememberLayerBackdrop(
    graphicsLayer: GraphicsLayer = rememberGraphicsLayer(),
    onDraw: ContentDrawScope.() -> Unit = DefaultOnDraw,
): LayerBackdrop = remember(graphicsLayer, onDraw) {
    LayerBackdrop(graphicsLayer, onDraw)
}

/**
 * A [Backdrop] that draws from a captured [GraphicsLayer].
 * The layer content is captured via [Modifier.layerBackdrop][layerBackdrop].
 */
@Stable
class LayerBackdrop internal constructor(
    val graphicsLayer: GraphicsLayer,
    internal val onDraw: ContentDrawScope.() -> Unit,
) : Backdrop {

    override val isCoordinatesDependent: Boolean = true

    internal var layerCoordinates: LayoutCoordinates? by mutableStateOf(null)

    override var offsetResidualX: Float = 0f
        internal set
    override var offsetResidualY: Float = 0f
        internal set

    private var inverseLayerScope: InverseLayerScope? = null

    override fun DrawScope.drawBackdrop(
        density: Density,
        coordinates: LayoutCoordinates?,
        layerBlock: (GraphicsLayerScope.() -> Unit)?,
        downscaleFactor: Int,
    ) {
        val coordinates = coordinates ?: return
        val layerCoordinates = layerCoordinates ?: return

        val offset = try {
            layerCoordinates.localPositionOf(coordinates)
        } catch (_: Exception) {
            coordinates.positionInWindow() - layerCoordinates.positionInWindow()
        }

        withTransform({
            if (layerBlock != null) {
                with(obtainInverseLayerScope()) { inverseTransform(density, layerBlock) }
            }
            if (downscaleFactor > 1) {
                val inv = 1f / downscaleFactor
                // Round to nearest even integer in downsampled space for stable
                // rasterization. Even alignment ensures that subsequent cascade 2x
                // steps (scale 0.5) also land on integer pixel boundaries.
                val scaledX = offset.x * inv
                val scaledY = offset.y * inv
                val roundedX = kotlin.math.round(scaledX * 0.5f).toInt().toFloat() * 2f
                val roundedY = kotlin.math.round(scaledY * 0.5f).toInt().toFloat() * 2f
                offsetResidualX = (scaledX - roundedX) * downscaleFactor
                offsetResidualY = (scaledY - roundedY) * downscaleFactor
                translate(-roundedX, -roundedY)
                scale(inv, inv, Offset.Zero)
            } else {
                offsetResidualX = 0f
                offsetResidualY = 0f
                translate(-offset.x, -offset.y)
            }
        }) {
            drawLayer(graphicsLayer)
        }
    }

    /**
     * Utility for [LayoutCoordinates.localPositionOf] that returns the position
     * of [other] relative to this coordinate system.
     */
    private fun LayoutCoordinates.localPositionOf(other: LayoutCoordinates): Offset = localPositionOf(other, Offset.Zero)

    private fun obtainInverseLayerScope(): InverseLayerScope = inverseLayerScope?.apply { reset() }
        ?: InverseLayerScope().also { inverseLayerScope = it }
}
