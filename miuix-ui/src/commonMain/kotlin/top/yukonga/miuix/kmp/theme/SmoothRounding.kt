// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.kyant.shapes.Capsule
import com.kyant.shapes.RoundedRectangle
import com.kyant.shapes.UnevenRoundedRectangle

/**
 * CompositionLocal to control whether Miuix components use G2-continuity smooth rounded corners
 * (from com.kyant.shapes) or standard [RoundedCornerShape].
 *
 * When `true` (default), components use [RoundedRectangle] / [Capsule] for smoother corners.
 * When `false`, components fall back to [RoundedCornerShape] / [CircleShape] for better HWUI performance.
 */
internal val LocalSmoothRounding = staticCompositionLocalOf { true }

/**
 * Returns a [RoundedRectangle] shape when smooth rounding is enabled,
 * or a [RoundedCornerShape] when disabled.
 */
@Composable
fun miuixShape(cornerRadius: Dp): Shape {
    val smooth = MiuixTheme.smoothRounding
    return remember(cornerRadius, smooth) {
        if (smooth) CachedOutlineShape(RoundedRectangle(cornerRadius)) else RoundedCornerShape(cornerRadius)
    }
}

/**
 * Returns a [Capsule] shape when smooth rounding is enabled,
 * or a [CircleShape] when disabled.
 */
@Composable
fun miuixCapsuleShape(): Shape {
    val smooth = MiuixTheme.smoothRounding
    return remember(smooth) {
        if (smooth) CachedOutlineShape(Capsule()) else CircleShape
    }
}

/**
 * Returns an [UnevenRoundedRectangle] shape when smooth rounding is enabled,
 * or a [RoundedCornerShape] with individual corner radii when disabled.
 */
@Composable
fun miuixUnevenShape(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp,
): Shape {
    val smooth = MiuixTheme.smoothRounding
    return remember(topStart, topEnd, bottomEnd, bottomStart, smooth) {
        if (smooth) {
            CachedOutlineShape(
                UnevenRoundedRectangle(
                    topStart = topStart,
                    topEnd = topEnd,
                    bottomEnd = bottomEnd,
                    bottomStart = bottomStart,
                ),
            )
        } else {
            RoundedCornerShape(
                topStart = topStart,
                topEnd = topEnd,
                bottomEnd = bottomEnd,
                bottomStart = bottomStart,
            )
        }
    }
}

/**
 * A [Shape] wrapper that caches the [Outline] produced by [delegate],
 * avoiding expensive recomputation when size, layout direction and density have not changed.
 */
@Stable
private class CachedOutlineShape(private val delegate: Shape) : Shape {
    private var cachedOutline: Outline? = null
    private var cachedSize: Size = Size.Unspecified
    private var cachedLayoutDirection: LayoutDirection? = null
    private var cachedDensity: Float = Float.NaN

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val currentDensity = density.density
        if (cachedOutline == null ||
            cachedSize != size ||
            cachedLayoutDirection != layoutDirection ||
            cachedDensity != currentDensity
        ) {
            cachedSize = size
            cachedLayoutDirection = layoutDirection
            cachedDensity = currentDensity
            cachedOutline = delegate.createOutline(size, layoutDirection, density)
        }
        return cachedOutline!!
    }
}
