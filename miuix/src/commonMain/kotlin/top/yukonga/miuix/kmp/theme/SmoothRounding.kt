// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
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
        if (smooth) RoundedRectangle(cornerRadius) else RoundedCornerShape(cornerRadius)
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
        if (smooth) Capsule() else CircleShape
    }
}

/**
 * Returns an [UnevenRoundedRectangle] shape when smooth rounding is enabled,
 * or a [RoundedCornerShape] with individual corner radii when disabled.
 */
@Composable
fun miuixUnevenShape(
    topStart: Dp = Dp.Unspecified,
    topEnd: Dp = Dp.Unspecified,
    bottomEnd: Dp = Dp.Unspecified,
    bottomStart: Dp = Dp.Unspecified,
): Shape {
    val smooth = MiuixTheme.smoothRounding
    return remember(topStart, topEnd, bottomEnd, bottomStart, smooth) {
        if (smooth) {
            UnevenRoundedRectangle(
                topStart = topStart,
                topEnd = topEnd,
                bottomEnd = bottomEnd,
                bottomStart = bottomStart,
            )
        } else {
            val zero = Dp.Hairline
            RoundedCornerShape(
                topStart = if (topStart == Dp.Unspecified) zero else topStart,
                topEnd = if (topEnd == Dp.Unspecified) zero else topEnd,
                bottomEnd = if (bottomEnd == Dp.Unspecified) zero else bottomEnd,
                bottomStart = if (bottomStart == Dp.Unspecified) zero else bottomStart,
            )
        }
    }
}
