// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.Density

/**
 * Defines how backdrop content is drawn behind a blurred surface.
 */
interface Backdrop {

    /**
     * Whether this backdrop needs layout coordinates to position itself correctly.
     */
    val isCoordinatesDependent: Boolean

    /**
     * Draws the backdrop content into the given [DrawScope].
     *
     * @param density The current density for unit conversion.
     * @param coordinates The layout coordinates of the blur surface, used for positioning.
     * @param layerBlock Optional graphics layer transformation.
     * @param downscaleFactor Scale factor for downsampled recording. 1 = full resolution.
     *   When > 1, the backdrop should scale its drawing by 1/downscaleFactor to fit
     *   into a smaller recording surface.
     */
    fun DrawScope.drawBackdrop(
        density: Density,
        coordinates: LayoutCoordinates?,
        layerBlock: (GraphicsLayerScope.() -> Unit)? = null,
        downscaleFactor: Int = 1,
    )
}
