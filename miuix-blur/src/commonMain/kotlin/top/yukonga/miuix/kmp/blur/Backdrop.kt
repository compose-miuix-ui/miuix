// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.Density

/**
 * Defines how backdrop content is drawn behind a blurred surface.
 */
@Stable
interface Backdrop {

    /**
     * Whether this backdrop needs layout coordinates to position itself correctly.
     */
    val isCoordinatesDependent: Boolean

    /**
     * Sub-pixel offset residual from rounding, in full-resolution px, compensated when drawing so
     * the recorded content can stay on a stable pixel grid.
     */
    val offsetResidualX: Float get() = 0f

    /** @see offsetResidualX */
    val offsetResidualY: Float get() = 0f

    /**
     * Draws the backdrop content into [DrawScope].
     *
     * @param density The current density for unit conversion.
     * @param coordinates The blur surface's layout coordinates, used for positioning.
     * @param layerBlock Optional graphics layer transformation.
     * @param downscaleFactor Recording scale: 1 = full resolution, `> 1` means draw at
     *   `1 / downscaleFactor`. Fractional, snapped to a 1/4-octave grid.
     */
    fun DrawScope.drawBackdrop(
        density: Density,
        coordinates: LayoutCoordinates?,
        layerBlock: (GraphicsLayerScope.() -> Unit)? = null,
        downscaleFactor: Float = 1f,
    )
}
