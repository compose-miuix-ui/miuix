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

val MiuixIcons.Close: ImageVector
    get() = MiuixIcons.Regular.Close

val MiuixIcons.Light.Close: ImageVector
    get() {
        if (_closeLight != null) return _closeLight!!
        _closeLight = ImageVector.Builder(
            name = "Close.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 994.8f,
            viewportHeight = 994.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -167.10000000000002f, translationY = 874.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(665.0f, 421.0f),
                        PathNode.LineTo(1028.0f, 785.0f),
                        PathNode.QuadTo(1034.0f, 792.0f, 1041.0f, 792.0f),
                        PathNode.QuadTo(1048.0f, 792.0f, 1055.0f, 785.0f),
                        PathNode.LineTo(1071.0f, 769.0f),
                        PathNode.QuadTo(1078.0f, 762.0f, 1078.0f, 755.0f),
                        PathNode.QuadTo(1078.0f, 748.0f, 1071.0f, 741.0f),
                        PathNode.LineTo(709.0f, 377.0f),
                        PathNode.LineTo(1072.0f, 14.0f),
                        PathNode.QuadTo(1079.0f, 7.0f, 1079.0f, 0.0f),
                        PathNode.QuadTo(1079.0f, -7.0f, 1072.0f, -14.0f),
                        PathNode.LineTo(1056.0f, -30.0f),
                        PathNode.QuadTo(1042.0f, -44.0f, 1028.0f, -30.0f),
                        PathNode.LineTo(665.0f, 333.0f),
                        PathNode.LineTo(302.0f, -29.0f),
                        PathNode.QuadTo(295.0f, -36.0f, 287.5f, -36.0f),
                        PathNode.QuadTo(280.0f, -36.0f, 273.0f, -29.0f),
                        PathNode.LineTo(257.0f, -13.0f),
                        PathNode.QuadTo(250.0f, -6.0f, 250.0f, 1.0f),
                        PathNode.QuadTo(250.0f, 8.0f, 257.0f, 15.0f),
                        PathNode.LineTo(621.0f, 377.0f),
                        PathNode.LineTo(258.0f, 741.0f),
                        PathNode.QuadTo(251.0f, 748.0f, 251.0f, 755.0f),
                        PathNode.QuadTo(251.0f, 762.0f, 258.0f, 769.0f),
                        PathNode.LineTo(274.0f, 785.0f),
                        PathNode.QuadTo(281.0f, 792.0f, 288.0f, 792.0f),
                        PathNode.QuadTo(295.0f, 792.0f, 301.0f, 785.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeLight!!
    }

private var _closeLight: ImageVector? = null

val MiuixIcons.Regular.Close: ImageVector
    get() {
        if (_closeRegular != null) return _closeRegular!!
        _closeRegular = ImageVector.Builder(
            name = "Close.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1035.0f,
            viewportHeight = 1035.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -147.5f, translationY = 895.25f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(665.0f, 441.0f),
                        PathNode.LineTo(1020.0f, 798.0f),
                        PathNode.QuadTo(1030.0f, 809.0f, 1041.0f, 809.0f),
                        PathNode.QuadTo(1052.0f, 809.0f, 1062.0f, 798.0f),
                        PathNode.LineTo(1084.0f, 776.0f),
                        PathNode.QuadTo(1094.0f, 766.0f, 1094.0f, 755.5f),
                        PathNode.QuadTo(1094.0f, 745.0f, 1084.0f, 734.0f),
                        PathNode.LineTo(729.0f, 377.0f),
                        PathNode.LineTo(1085.0f, 21.0f),
                        PathNode.QuadTo(1096.0f, 11.0f, 1096.0f, 0.0f),
                        PathNode.QuadTo(1096.0f, -11.0f, 1085.0f, -21.0f),
                        PathNode.LineTo(1063.0f, -43.0f),
                        PathNode.QuadTo(1042.0f, -64.0f, 1021.0f, -43.0f),
                        PathNode.LineTo(665.0f, 313.0f),
                        PathNode.LineTo(308.0f, -42.0f),
                        PathNode.QuadTo(298.0f, -52.0f, 287.0f, -52.0f),
                        PathNode.QuadTo(276.0f, -52.0f, 266.0f, -42.0f),
                        PathNode.LineTo(244.0f, -20.0f),
                        PathNode.QuadTo(234.0f, -10.0f, 234.0f, 1.0f),
                        PathNode.QuadTo(234.0f, 12.0f, 244.0f, 22.0f),
                        PathNode.LineTo(601.0f, 377.0f),
                        PathNode.LineTo(245.0f, 734.0f),
                        PathNode.QuadTo(235.0f, 745.0f, 235.0f, 755.5f),
                        PathNode.QuadTo(235.0f, 766.0f, 245.0f, 776.0f),
                        PathNode.LineTo(267.0f, 798.0f),
                        PathNode.QuadTo(278.0f, 809.0f, 288.5f, 809.0f),
                        PathNode.QuadTo(299.0f, 809.0f, 309.0f, 798.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeRegular!!
    }

private var _closeRegular: ImageVector? = null

val MiuixIcons.Heavy.Close: ImageVector
    get() {
        if (_closeHeavy != null) return _closeHeavy!!
        _closeHeavy = ImageVector.Builder(
            name = "Close.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1072.857142857143f,
            viewportHeight = 1072.857142857143f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -128.59523809523807f, translationY = 914.0372670807453f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(665.0f, 464.0f),
                        PathNode.LineTo(1004.0f, 805.0f),
                        PathNode.QuadTo(1024.0f, 824.0f, 1041.5f, 823.5f),
                        PathNode.QuadTo(1059.0f, 823.0f, 1078.0f, 805.0f),
                        PathNode.LineTo(1091.0f, 792.0f),
                        PathNode.QuadTo(1110.0f, 773.0f, 1110.5f, 755.5f),
                        PathNode.QuadTo(1111.0f, 738.0f, 1091.0f, 718.0f),
                        PathNode.LineTo(752.0f, 377.0f),
                        PathNode.LineTo(1093.0f, 36.0f),
                        PathNode.QuadTo(1111.0f, 18.0f, 1112.0f, 1.0f),
                        PathNode.QuadTo(1113.0f, -16.0f, 1093.0f, -36.0f),
                        PathNode.LineTo(1076.0f, -53.0f),
                        PathNode.QuadTo(1043.0f, -86.0f, 1005.0f, -50.0f),
                        PathNode.LineTo(665.0f, 290.0f),
                        PathNode.LineTo(324.0f, -49.0f),
                        PathNode.QuadTo(306.0f, -68.0f, 288.0f, -68.5f),
                        PathNode.QuadTo(270.0f, -69.0f, 250.0f, -49.0f),
                        PathNode.LineTo(238.0f, -36.0f),
                        PathNode.QuadTo(218.0f, -18.0f, 218.0f, 0.0f),
                        PathNode.QuadTo(218.0f, 18.0f, 237.0f, 37.0f),
                        PathNode.LineTo(578.0f, 377.0f),
                        PathNode.LineTo(238.0f, 718.0f),
                        PathNode.QuadTo(219.0f, 738.0f, 219.0f, 755.5f),
                        PathNode.QuadTo(219.0f, 773.0f, 238.0f, 792.0f),
                        PathNode.LineTo(251.0f, 805.0f),
                        PathNode.QuadTo(271.0f, 824.0f, 288.0f, 824.0f),
                        PathNode.QuadTo(305.0f, 824.0f, 325.0f, 805.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeHeavy!!
    }

private var _closeHeavy: ImageVector? = null
