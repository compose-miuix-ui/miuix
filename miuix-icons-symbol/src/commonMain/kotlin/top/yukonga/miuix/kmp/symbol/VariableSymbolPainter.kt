// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.symbol

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A [Painter] that renders a [VariableSymbol] at any continuous `wght` coordinate (`150f..700f`).
 *
 * Reuses a single [Path] instance and only rebuilds geometry when [weight] or draw bounds change,
 * avoiding allocations during continuous weight animations.
 *
 * Works seamlessly with Miuix `Icon(painter = rememberSymbolPainter(...), ...)`, respecting both
 * `tint` (via [ColorFilter]) and `alpha`.
 */
@Stable
class VariableSymbolPainter(
    val symbol: VariableSymbol,
    private val defaultSizePx: Float,
    initialWeight: Float = symbol.defaultWeight,
    private val weightProvider: (() -> Float)? = null,
) : Painter() {
    var weight: Float by mutableFloatStateOf(initialWeight)

    private var alpha: Float = 1f
    private var colorFilter: ColorFilter? = null

    private val path: Path = Path()
    private var cachedWeight: Float = Float.NaN
    private var cachedWidth: Float = Float.NaN
    private var cachedHeight: Float = Float.NaN

    override val intrinsicSize: Size
        get() = Size(defaultSizePx * symbol.widthRatio, defaultSizePx)

    override fun applyAlpha(alpha: Float): Boolean {
        this.alpha = alpha
        return true
    }

    override fun applyColorFilter(colorFilter: ColorFilter?): Boolean {
        this.colorFilter = colorFilter
        return true
    }

    override fun DrawScope.onDraw() {
        val currentWeight = (weightProvider?.invoke() ?: weight).coerceIn(symbol.minWeight, symbol.maxWeight)
        if (currentWeight != cachedWeight || size.width != cachedWidth || size.height != cachedHeight) {
            symbol.outline().writeToPath(
                targetPath = path,
                symbol = symbol,
                weight = currentWeight,
                boxWidth = size.width,
                boxHeight = size.height,
            )
            cachedWeight = currentWeight
            cachedWidth = size.width
            cachedHeight = size.height
        }

        val drawColor = if (symbol.defaultColor != Color.Unspecified) {
            symbol.defaultColor
        } else {
            Color.Black
        }
        drawPath(
            path = path,
            color = drawColor,
            alpha = alpha,
            colorFilter = colorFilter,
        )
    }
}

/**
 * Creates and remembers a [VariableSymbolPainter] for [symbol] at the given [weight].
 *
 * Changing [weight] updates the existing painter in place without recreating the [Path] cache.
 */
@Composable
fun rememberSymbolPainter(
    symbol: VariableSymbol,
    weight: Float = symbol.defaultWeight,
    defaultSize: Dp = 24.dp,
): VariableSymbolPainter {
    val defaultSizePx = with(LocalDensity.current) { defaultSize.toPx() }
    val painter = remember(symbol, defaultSizePx) {
        VariableSymbolPainter(
            symbol = symbol,
            defaultSizePx = defaultSizePx,
            initialWeight = weight,
        )
    }
    if (painter.weight != weight) painter.weight = weight
    return painter
}

/**
 * Creates and remembers a [VariableSymbolPainter] whose [weight] lambda is read strictly in the
 * draw phase, avoiding recomposition during continuous weight animations.
 */
@Composable
fun rememberSymbolPainter(
    symbol: VariableSymbol,
    weight: () -> Float,
    defaultSize: Dp = 24.dp,
): VariableSymbolPainter {
    val defaultSizePx = with(LocalDensity.current) { defaultSize.toPx() }
    return remember(symbol, defaultSizePx, weight) {
        VariableSymbolPainter(
            symbol = symbol,
            defaultSizePx = defaultSizePx,
            weightProvider = weight,
        )
    }
}
