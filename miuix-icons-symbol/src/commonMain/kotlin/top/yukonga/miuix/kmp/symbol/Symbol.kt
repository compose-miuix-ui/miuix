// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.symbol

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws a built-in continuously variable [VariableSymbol] at any [weight] in `[150f..700f]`.
 *
 * When [tint] is [Color.Unspecified], symbols with a [VariableSymbol.defaultColor] render in
 * their native color and monochrome symbols render in [Color.Black]. Pass `LocalContentColor.current`
 * (or use Miuix `Icon(painter = rememberSymbolPainter(...), ...)`) to inherit theme content color.
 *
 * @param symbol The [VariableSymbol] to render.
 * @param contentDescription Accessibility description, or `null` for decorative icons.
 * @param modifier Modifier applied to the icon box. Defaults to `24.dp` if no size is specified.
 * @param weight Continuous `wght` coordinate (`150f..700f`). Defaults to [VariableSymbol.defaultWeight].
 * @param tint Tint color applied to the symbol.
 */
@Composable
fun Symbol(
    symbol: VariableSymbol,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    weight: Float = symbol.defaultWeight,
    tint: Color = Color.Unspecified,
) {
    val state = remember(symbol) { DrawPathCache() }
    val semanticsModifier = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .then(DefaultSymbolSizeModifier)
            .drawBehind {
                val currentWeight = weight.coerceIn(symbol.minWeight, symbol.maxWeight)
                if (
                    currentWeight != state.weight ||
                    size.width != state.width ||
                    size.height != state.height
                ) {
                    symbol.outline().writeToPath(
                        targetPath = state.path,
                        symbol = symbol,
                        weight = currentWeight,
                        boxWidth = size.width,
                        boxHeight = size.height,
                    )
                    state.weight = currentWeight
                    state.width = size.width
                    state.height = size.height
                }
                val baseColor = when {
                    tint != Color.Unspecified -> tint
                    symbol.defaultColor != Color.Unspecified -> symbol.defaultColor
                    else -> Color.Black
                }
                drawPath(path = state.path, color = baseColor)
            }
            .then(semanticsModifier),
    )
}

/**
 * Animation-friendly [Symbol] overload whose [weight] and [tint] lambdas are read strictly in the
 * draw phase so continuous animations run without recomposing.
 *
 * @param symbol The [VariableSymbol] to render.
 * @param contentDescription Accessibility description, or `null` for decorative icons.
 * @param weight Lambda producing the continuous `wght` coordinate (`150f..700f`) at draw time.
 * @param modifier Modifier applied to the icon box. Defaults to `24.dp` if no size is specified.
 * @param tint Optional [ColorProducer] read at draw time. If `null` or returning [Color.Unspecified],
 *   falls back to [VariableSymbol.defaultColor] or [Color.Black].
 */
@Composable
fun Symbol(
    symbol: VariableSymbol,
    contentDescription: String?,
    weight: () -> Float,
    modifier: Modifier = Modifier,
    tint: ColorProducer? = null,
) {
    val state = remember(symbol) { DrawPathCache() }
    val semanticsModifier = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .then(DefaultSymbolSizeModifier)
            .drawBehind {
                val currentWeight = weight().coerceIn(symbol.minWeight, symbol.maxWeight)
                if (
                    currentWeight != state.weight ||
                    size.width != state.width ||
                    size.height != state.height
                ) {
                    symbol.outline().writeToPath(
                        targetPath = state.path,
                        symbol = symbol,
                        weight = currentWeight,
                        boxWidth = size.width,
                        boxHeight = size.height,
                    )
                    state.weight = currentWeight
                    state.width = size.width
                    state.height = size.height
                }

                val resolvedTint = tint?.invoke() ?: Color.Unspecified
                val baseColor = when {
                    resolvedTint != Color.Unspecified -> resolvedTint
                    symbol.defaultColor != Color.Unspecified -> symbol.defaultColor
                    else -> Color.Black
                }
                drawPath(path = state.path, color = baseColor)
            }
            .then(semanticsModifier),
    )
}

/**
 * Materializes a static Compose [ImageVector] snapshot of this [VariableSymbol] at the specified
 * [weight]. Useful for APIs that strictly require an [ImageVector].
 */
fun VariableSymbol.toImageVector(
    weight: Float = defaultWeight,
    defaultSize: Dp = 24.dp,
): ImageVector {
    val clamped = weight.coerceIn(minWeight, maxWeight)
    val vpWidth = advanceWidthAt(clamped).coerceAtLeast(1f)
    val vpHeight = emHeight.toFloat().coerceAtLeast(1f)
    val fillColor = if (defaultColor != Color.Unspecified) defaultColor else Color.Black
    val nodes = outline().toPathNodes(this, clamped)

    return ImageVector.Builder(
        name = "$name@${clamped.toInt()}",
        defaultWidth = defaultSize * (vpWidth / vpHeight),
        defaultHeight = defaultSize,
        viewportWidth = vpWidth,
        viewportHeight = vpHeight,
        autoMirror = false,
    ).apply {
        addPath(
            pathData = nodes,
            pathFillType = PathFillType.NonZero,
            fill = SolidColor(fillColor),
        )
    }.build()
}

private class DrawPathCache {
    val path: Path = Path()
    var weight: Float = Float.NaN
    var width: Float = Float.NaN
    var height: Float = Float.NaN
}

private val DefaultSymbolSizeModifier = Modifier.size(24.dp)
