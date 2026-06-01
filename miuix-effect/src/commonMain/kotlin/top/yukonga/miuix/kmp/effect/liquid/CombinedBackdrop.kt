// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.liquid

// Adapted from Kyant0/AndroidLiquidGlass — https://github.com/Kyant0/AndroidLiquidGlass (Apache 2.0).

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.Density
import top.yukonga.miuix.kmp.blur.Backdrop

/**
 * A [Backdrop] that composites two backdrop layers by drawing [first] then [second]
 * in sequence, producing a layered backdrop that can be sampled as a single source.
 *
 * This is useful when you need to combine a base backdrop (e.g., the app's main
 * content) with a tinted or overlaid backdrop (e.g., a tab bar's local recording)
 * so that subsequent effects (blur, lens, etc.) sample from the merged result.
 *
 * ### Coordinate Dependency
 *
 * The combined backdrop is coordinate-dependent if **either** child is coordinate-
 * dependent. Residual offsets are taken from the [first] backdrop.
 *
 * ### Usage
 *
 * ```kotlin
 * val baseBackdrop = rememberLayerBackdrop()
 * val tabsBackdrop = rememberLayerBackdrop()
 * val combined = rememberCombinedBackdrop(baseBackdrop, tabsBackdrop)
 *
 * // Use `combined` as a single backdrop source for effects
 * Modifier.drawBackdrop(
 *     backdrop = combined,
 *     shape = { pillShape },
 *     effects = { lens(...) },
 * )
 * ```
 *
 * @property first The base backdrop drawn first (typically the main content layer).
 * @property second The overlay backdrop drawn on top of [first] (typically a local recording).
 */
@Stable
class CombinedBackdrop(
    val first: Backdrop,
    val second: Backdrop,
) : Backdrop {

    override val isCoordinatesDependent: Boolean = first.isCoordinatesDependent || second.isCoordinatesDependent

    override val offsetResidualX: Float get() = first.offsetResidualX
    override val offsetResidualY: Float get() = first.offsetResidualY

    override fun DrawScope.drawBackdrop(
        density: Density,
        coordinates: LayoutCoordinates?,
        layerBlock: (GraphicsLayerScope.() -> Unit)?,
        downscaleFactor: Int,
    ) {
        with(first) { drawBackdrop(density, coordinates, layerBlock, downscaleFactor) }
        with(second) { drawBackdrop(density, coordinates, layerBlock, downscaleFactor) }
    }
}

/**
 * Creates and remembers a [CombinedBackdrop] from two child backdrops.
 *
 * The returned [Backdrop] is keyed on [first] and [second] identity; if either
 * changes, a new [CombinedBackdrop] is created.
 *
 * @param first The base backdrop (typically the main content layer).
 * @param second The overlay backdrop (typically a local recording).
 * @return A [Backdrop] that draws both children in sequence.
 */
@Composable
fun rememberCombinedBackdrop(first: Backdrop, second: Backdrop): Backdrop = remember(first, second) { CombinedBackdrop(first, second) }
