// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.extended

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Add: ImageVector
    get() = MiuixIcons.Regular.Add

val MiuixIcons.Light.Add: ImageVector
    get() {
        if (_addLight != null) return _addLight!!
        _addLight = ImageVector.Builder(
            name = "Add.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1137.6f,
            viewportHeight = 1137.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -95.70000000000005f, translationY = 943.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(697.0f, -81.0f),
                        PathNode.VerticalTo(831.0f),
                        PathNode.QuadTo(697.0f, 838.0f, 692.0f, 843.5f),
                        PathNode.QuadTo(687.0f, 849.0f, 677.0f, 849.0f),
                        PathNode.HorizontalTo(653.0f),
                        PathNode.QuadTo(644.0f, 849.0f, 639.0f, 843.5f),
                        PathNode.QuadTo(634.0f, 838.0f, 634.0f, 831.0f),
                        PathNode.VerticalTo(-81.0f),
                        PathNode.QuadTo(634.0f, -89.0f, 639.5f, -94.0f),
                        PathNode.QuadTo(645.0f, -99.0f, 654.0f, -99.0f),
                        PathNode.HorizontalTo(678.0f),
                        PathNode.QuadTo(686.0f, -99.0f, 691.5f, -94.0f),
                        PathNode.QuadTo(697.0f, -89.0f, 697.0f, -81.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1120.0f, 407.0f),
                        PathNode.HorizontalTo(209.0f),
                        PathNode.QuadTo(202.0f, 407.0f, 196.5f, 402.0f),
                        PathNode.QuadTo(191.0f, 397.0f, 191.0f, 387.0f),
                        PathNode.VerticalTo(362.0f),
                        PathNode.QuadTo(191.0f, 353.0f, 196.5f, 348.0f),
                        PathNode.QuadTo(202.0f, 343.0f, 209.0f, 343.0f),
                        PathNode.HorizontalTo(1120.0f),
                        PathNode.QuadTo(1128.0f, 343.0f, 1133.0f, 348.5f),
                        PathNode.QuadTo(1138.0f, 354.0f, 1138.0f, 362.0f),
                        PathNode.VerticalTo(388.0f),
                        PathNode.QuadTo(1138.0f, 396.0f, 1133.0f, 401.5f),
                        PathNode.QuadTo(1128.0f, 407.0f, 1120.0f, 407.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addLight!!
    }

private var _addLight: ImageVector? = null

val MiuixIcons.Regular.Add: ImageVector
    get() {
        if (_addRegular != null) return _addRegular!!
        _addRegular = ImageVector.Builder(
            name = "Add.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1168.8f,
            viewportHeight = 1168.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -80.10000000000002f, translationY = 959.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(710.0f, -85.0f),
                        PathNode.VerticalTo(835.0f),
                        PathNode.QuadTo(710.0f, 847.0f, 703.0f, 854.5f),
                        PathNode.QuadTo(696.0f, 862.0f, 681.0f, 862.0f),
                        PathNode.HorizontalTo(649.0f),
                        PathNode.QuadTo(636.0f, 862.0f, 628.5f, 854.0f),
                        PathNode.QuadTo(621.0f, 846.0f, 621.0f, 835.0f),
                        PathNode.VerticalTo(-85.0f),
                        PathNode.QuadTo(621.0f, -98.0f, 629.0f, -105.0f),
                        PathNode.QuadTo(637.0f, -112.0f, 650.0f, -112.0f),
                        PathNode.HorizontalTo(682.0f),
                        PathNode.QuadTo(695.0f, -112.0f, 702.5f, -105.0f),
                        PathNode.QuadTo(710.0f, -98.0f, 710.0f, -85.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1124.0f, 420.0f),
                        PathNode.HorizontalTo(205.0f),
                        PathNode.QuadTo(193.0f, 420.0f, 185.5f, 413.0f),
                        PathNode.QuadTo(178.0f, 406.0f, 178.0f, 391.0f),
                        PathNode.VerticalTo(357.0f),
                        PathNode.QuadTo(178.0f, 344.0f, 186.0f, 336.5f),
                        PathNode.QuadTo(194.0f, 329.0f, 205.0f, 329.0f),
                        PathNode.HorizontalTo(1124.0f),
                        PathNode.QuadTo(1137.0f, 329.0f, 1144.0f, 337.0f),
                        PathNode.QuadTo(1151.0f, 345.0f, 1151.0f, 358.0f),
                        PathNode.VerticalTo(392.0f),
                        PathNode.QuadTo(1151.0f, 405.0f, 1144.0f, 412.5f),
                        PathNode.QuadTo(1137.0f, 420.0f, 1124.0f, 420.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addRegular!!
    }

private var _addRegular: ImageVector? = null

val MiuixIcons.Heavy.Add: ImageVector
    get() {
        if (_addHeavy != null) return _addHeavy!!
        _addHeavy = ImageVector.Builder(
            name = "Add.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1202.3999999999999f,
            viewportHeight = 1202.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -63.30000000000007f, translationY = 976.1999999999999f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(724.0f, -85.0f),
                        PathNode.VerticalTo(835.0f),
                        PathNode.QuadTo(724.0f, 853.0f, 713.0f, 864.5f),
                        PathNode.QuadTo(702.0f, 876.0f, 681.0f, 876.0f),
                        PathNode.HorizontalTo(649.0f),
                        PathNode.QuadTo(630.0f, 876.0f, 618.5f, 864.0f),
                        PathNode.QuadTo(607.0f, 852.0f, 607.0f, 835.0f),
                        PathNode.VerticalTo(-85.0f),
                        PathNode.QuadTo(607.0f, -103.0f, 619.5f, -114.5f),
                        PathNode.QuadTo(632.0f, -126.0f, 650.0f, -126.0f),
                        PathNode.HorizontalTo(682.0f),
                        PathNode.QuadTo(700.0f, -126.0f, 712.0f, -114.5f),
                        PathNode.QuadTo(724.0f, -103.0f, 724.0f, -85.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1124.0f, 434.0f),
                        PathNode.HorizontalTo(205.0f),
                        PathNode.QuadTo(187.0f, 434.0f, 175.5f, 423.0f),
                        PathNode.QuadTo(164.0f, 412.0f, 164.0f, 391.0f),
                        PathNode.VerticalTo(357.0f),
                        PathNode.QuadTo(164.0f, 338.0f, 176.0f, 326.5f),
                        PathNode.QuadTo(188.0f, 315.0f, 205.0f, 315.0f),
                        PathNode.HorizontalTo(1124.0f),
                        PathNode.QuadTo(1142.0f, 315.0f, 1153.5f, 327.5f),
                        PathNode.QuadTo(1165.0f, 340.0f, 1165.0f, 358.0f),
                        PathNode.VerticalTo(392.0f),
                        PathNode.QuadTo(1165.0f, 410.0f, 1153.5f, 422.0f),
                        PathNode.QuadTo(1142.0f, 434.0f, 1124.0f, 434.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addHeavy!!
    }

private var _addHeavy: ImageVector? = null
