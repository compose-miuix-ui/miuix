// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp

internal fun buildHyperOSIcon(
    name: String,
    pathData: List<PathNode>,
    scale: Float,
    translationX: Float,
    translationY: Float,
    autoMirror: Boolean,
): ImageVector = ImageVector.Builder(
    name = name,
    defaultWidth = 28.0f.dp,
    defaultHeight = 28.0f.dp,
    viewportWidth = 1333.3333f,
    viewportHeight = 1333.3333f,
    autoMirror = autoMirror,
).apply {
    group(scaleX = scale, scaleY = -scale, translationX = translationX, translationY = translationY) {
        addPath(
            pathData = pathData,
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            pathFillType = PathFillType.NonZero,
        )
    }
}.build()

