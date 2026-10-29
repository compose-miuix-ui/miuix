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
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.ChevronBackward: ImageVector
    get() = MiuixIcons.Glass.Regular.ChevronBackward

val MiuixIcons.Glass.Light.ChevronBackward: ImageVector
    get() {
        if (_chevronBackwardLight != null) return _chevronBackwardLight!!
        _chevronBackwardLight = ImageVector.Builder(
            name = "ChevronBackward.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = -85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(313.6f, 555.2f),
                        PathNode.LineTo(786.2f, 83.5f),
                        PathNode.QuadTo(792.7f, 77.1f, 799.8f, 77.4f),
                        PathNode.QuadTo(807.0f, 77.6f, 815.7f, 86.3f),
                        PathNode.LineTo(828.8f, 99.4f),
                        PathNode.QuadTo(835.1f, 105.8f, 835.7f, 112.2f),
                        PathNode.QuadTo(836.2f, 118.6f, 830.4f, 124.4f),
                        PathNode.LineTo(384.8f, 570.6f),
                        PathNode.LineTo(830.9f, 1016.3f),
                        PathNode.QuadTo(836.2f, 1021.6f, 835.9f, 1029.1f),
                        PathNode.QuadTo(835.7f, 1036.5f, 830.9f, 1041.9f),
                        PathNode.LineTo(811.2f, 1061.0f),
                        PathNode.QuadTo(806.4f, 1065.8f, 799.6f, 1065.5f),
                        PathNode.QuadTo(792.7f, 1065.2f, 787.3f, 1059.9f),
                        PathNode.LineTo(313.0f, 585.5f),
                        PathNode.QuadTo(306.9f, 579.4f, 306.9f, 570.6f),
                        PathNode.QuadTo(306.9f, 561.9f, 313.6f, 555.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronBackwardLight!!
    }

private var _chevronBackwardLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ChevronBackward: ImageVector
    get() {
        if (_chevronBackwardNormal != null) return _chevronBackwardNormal!!
        _chevronBackwardNormal = ImageVector.Builder(
            name = "ChevronBackward.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = -85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(305.4f, 552.6f),
                        PathNode.LineTo(779.0f, 80.0f),
                        PathNode.QuadTo(787.9f, 71.1f, 797.3f, 71.6f),
                        PathNode.QuadTo(806.8f, 72.0f, 819.1f, 84.3f),
                        PathNode.LineTo(835.6f, 100.8f),
                        PathNode.QuadTo(843.7f, 109.0f, 844.6f, 117.9f),
                        PathNode.QuadTo(845.5f, 126.7f, 838.2f, 134.0f),
                        PathNode.LineTo(402.6f, 570.5f),
                        PathNode.LineTo(838.3f, 1006.1f),
                        PathNode.QuadTo(845.5f, 1013.2f, 845.0f, 1023.1f),
                        PathNode.QuadTo(844.6f, 1033.0f, 838.3f, 1040.2f),
                        PathNode.LineTo(812.2f, 1065.4f),
                        PathNode.QuadTo(805.9f, 1071.7f, 796.9f, 1071.3f),
                        PathNode.QuadTo(787.9f, 1070.9f, 780.7f, 1063.7f),
                        PathNode.LineTo(304.5f, 587.5f),
                        PathNode.QuadTo(297.8f, 580.8f, 297.8f, 570.5f),
                        PathNode.QuadTo(297.8f, 560.2f, 305.4f, 552.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronBackwardNormal!!
    }

private var _chevronBackwardNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ChevronBackward: ImageVector
    get() {
        if (_chevronBackwardRegular != null) return _chevronBackwardRegular!!
        _chevronBackwardRegular = ImageVector.Builder(
            name = "ChevronBackward.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = -85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(301.7f, 551.4f),
                        PathNode.LineTo(775.7f, 78.4f),
                        PathNode.QuadTo(785.7f, 68.4f, 796.2f, 68.9f),
                        PathNode.QuadTo(806.7f, 69.4f, 820.7f, 83.4f),
                        PathNode.LineTo(838.7f, 101.4f),
                        PathNode.QuadTo(847.7f, 110.4f, 848.7f, 120.4f),
                        PathNode.QuadTo(849.7f, 130.4f, 841.7f, 138.4f),
                        PathNode.LineTo(410.7f, 570.4f),
                        PathNode.LineTo(841.7f, 1001.4f),
                        PathNode.QuadTo(849.7f, 1009.4f, 849.2f, 1020.4f),
                        PathNode.QuadTo(848.7f, 1031.4f, 841.7f, 1039.4f),
                        PathNode.LineTo(812.7f, 1067.4f),
                        PathNode.QuadTo(805.7f, 1074.4f, 795.7f, 1073.9f),
                        PathNode.QuadTo(785.7f, 1073.4f, 777.7f, 1065.4f),
                        PathNode.LineTo(300.7f, 588.4f),
                        PathNode.QuadTo(293.7f, 581.4f, 293.7f, 570.4f),
                        PathNode.QuadTo(293.7f, 559.4f, 301.7f, 551.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronBackwardRegular!!
    }

private var _chevronBackwardRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ChevronBackward: ImageVector
    get() {
        if (_chevronBackwardMedium != null) return _chevronBackwardMedium!!
        _chevronBackwardMedium = ImageVector.Builder(
            name = "ChevronBackward.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = -85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(296.0f, 545.8f),
                        PathNode.LineTo(770.0f, 72.8f),
                        PathNode.QuadTo(782.4f, 60.4f, 796.2f, 60.9f),
                        PathNode.QuadTo(809.9f, 61.4f, 826.3f, 77.8f),
                        PathNode.LineTo(844.3f, 95.8f),
                        PathNode.QuadTo(855.8f, 107.2f, 856.8f, 120.5f),
                        PathNode.QuadTo(857.8f, 133.7f, 847.3f, 144.2f),
                        PathNode.LineTo(422.0f, 570.5f),
                        PathNode.LineTo(847.3f, 995.8f),
                        PathNode.QuadTo(857.8f, 1006.0f, 857.3f, 1020.4f),
                        PathNode.QuadTo(856.8f, 1034.7f, 847.3f, 1045.2f),
                        PathNode.LineTo(818.3f, 1073.4f),
                        PathNode.QuadTo(808.6f, 1082.6f, 795.4f, 1082.0f),
                        PathNode.QuadTo(782.2f, 1081.3f, 772.0f, 1071.2f),
                        PathNode.LineTo(295.0f, 594.2f),
                        PathNode.QuadTo(285.6f, 584.7f, 285.6f, 570.4f),
                        PathNode.QuadTo(285.6f, 556.0f, 296.0f, 545.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronBackwardMedium!!
    }

private var _chevronBackwardMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ChevronBackward: ImageVector
    get() {
        if (_chevronBackwardDemibold != null) return _chevronBackwardDemibold!!
        _chevronBackwardDemibold = ImageVector.Builder(
            name = "ChevronBackward.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = -85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(292.0f, 541.9f),
                        PathNode.LineTo(766.0f, 68.9f),
                        PathNode.QuadTo(780.2f, 54.7f, 796.2f, 55.2f),
                        PathNode.QuadTo(812.2f, 55.7f, 830.3f, 73.9f),
                        PathNode.LineTo(848.3f, 91.9f),
                        PathNode.QuadTo(861.5f, 105.0f, 862.5f, 120.5f),
                        PathNode.QuadTo(863.5f, 136.0f, 851.3f, 148.2f),
                        PathNode.LineTo(430.0f, 570.5f),
                        PathNode.LineTo(851.3f, 991.9f),
                        PathNode.QuadTo(863.5f, 1003.6f, 863.0f, 1020.3f),
                        PathNode.QuadTo(862.5f, 1037.0f, 851.3f, 1049.2f),
                        PathNode.LineTo(822.3f, 1077.6f),
                        PathNode.QuadTo(810.7f, 1088.3f, 795.2f, 1087.6f),
                        PathNode.QuadTo(779.7f, 1086.9f, 768.0f, 1075.2f),
                        PathNode.LineTo(291.0f, 598.2f),
                        PathNode.QuadTo(279.9f, 587.0f, 279.9f, 570.3f),
                        PathNode.QuadTo(279.9f, 553.6f, 292.0f, 541.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronBackwardDemibold!!
    }

private var _chevronBackwardDemibold: ImageVector? = null
