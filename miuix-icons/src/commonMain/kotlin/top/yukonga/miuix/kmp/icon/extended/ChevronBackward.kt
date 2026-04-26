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

val MiuixIcons.ChevronBackward: ImageVector
    get() = MiuixIcons.Regular.ChevronBackward

val MiuixIcons.Light.ChevronBackward: ImageVector
    get() {
        if (_chevronbackwardLight != null) return _chevronbackwardLight!!
        _chevronbackwardLight = ImageVector.Builder(
            name = "ChevronBackward.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1186.8f,
            viewportHeight = 1186.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1186.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(335.4f, 577.9f),
                        PathNode.LineTo(808.4f, 105.9f),
                        PathNode.QuadTo(814.4f, 98.9f, 821.4f, 99.4f),
                        PathNode.QuadTo(828.4f, 99.9f, 837.4f, 108.9f),
                        PathNode.LineTo(850.4f, 121.9f),
                        PathNode.QuadTo(857.4f, 127.9f, 857.4f, 134.4f),
                        PathNode.QuadTo(857.4f, 140.9f, 852.4f, 146.9f),
                        PathNode.LineTo(406.4f, 592.9f),
                        PathNode.LineTo(852.4f, 1038.9f),
                        PathNode.QuadTo(858.4f, 1043.9f, 857.9f, 1051.4f),
                        PathNode.QuadTo(857.4f, 1058.9f, 852.4f, 1063.9f),
                        PathNode.LineTo(833.4f, 1082.9f),
                        PathNode.QuadTo(828.4f, 1087.9f, 821.4f, 1087.4f),
                        PathNode.QuadTo(814.4f, 1086.9f, 809.4f, 1081.9f),
                        PathNode.LineTo(334.4f, 607.9f),
                        PathNode.QuadTo(328.4f, 600.9f, 328.4f, 592.4f),
                        PathNode.QuadTo(328.4f, 583.9f, 335.4f, 577.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronbackwardLight!!
    }

private var _chevronbackwardLight: ImageVector? = null

val MiuixIcons.Normal.ChevronBackward: ImageVector
    get() {
        if (_chevronbackwardNormal != null) return _chevronbackwardNormal!!
        _chevronbackwardNormal = ImageVector.Builder(
            name = "ChevronBackward.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1200.8f,
            viewportHeight = 1200.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1200.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(334.2f, 581.8f),
                        PathNode.LineTo(807.9f, 109.1f),
                        PathNode.QuadTo(816.6f, 100.1f, 826.0f, 100.6f),
                        PathNode.QuadTo(835.4f, 101.1f, 847.9f, 113.5f),
                        PathNode.LineTo(864.3f, 129.9f),
                        PathNode.QuadTo(872.7f, 138.0f, 873.4f, 146.9f),
                        PathNode.QuadTo(874.0f, 155.8f, 867.0f, 163.2f),
                        PathNode.LineTo(431.3f, 599.6f),
                        PathNode.LineTo(867.0f, 1035.3f),
                        PathNode.QuadTo(874.4f, 1042.3f, 873.9f, 1052.2f),
                        PathNode.QuadTo(873.4f, 1062.1f, 867.0f, 1069.2f),
                        PathNode.LineTo(841.1f, 1094.4f),
                        PathNode.QuadTo(834.7f, 1100.8f, 825.7f, 1100.3f),
                        PathNode.QuadTo(816.6f, 1099.8f, 809.5f, 1092.7f),
                        PathNode.LineTo(333.2f, 616.6f),
                        PathNode.QuadTo(326.5f, 609.6f, 326.5f, 599.4f),
                        PathNode.QuadTo(326.5f, 589.2f, 334.2f, 581.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronbackwardNormal!!
    }

private var _chevronbackwardNormal: ImageVector? = null

val MiuixIcons.Regular.ChevronBackward: ImageVector
    get() {
        if (_chevronbackwardRegular != null) return _chevronbackwardRegular!!
        _chevronbackwardRegular = ImageVector.Builder(
            name = "ChevronBackward.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1207.2f,
            viewportHeight = 1207.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1207.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(333.6f, 583.6f),
                        PathNode.LineTo(807.6f, 110.6f),
                        PathNode.QuadTo(817.6f, 100.6f, 828.1f, 101.1f),
                        PathNode.QuadTo(838.6f, 101.6f, 852.6f, 115.6f),
                        PathNode.LineTo(870.6f, 133.6f),
                        PathNode.QuadTo(879.6f, 142.6f, 880.6f, 152.6f),
                        PathNode.QuadTo(881.6f, 162.6f, 873.6f, 170.6f),
                        PathNode.LineTo(442.6f, 602.6f),
                        PathNode.LineTo(873.6f, 1033.6f),
                        PathNode.QuadTo(881.6f, 1041.6f, 881.1f, 1052.6f),
                        PathNode.QuadTo(880.6f, 1063.6f, 873.6f, 1071.6f),
                        PathNode.LineTo(844.6f, 1099.6f),
                        PathNode.QuadTo(837.6f, 1106.6f, 827.6f, 1106.1f),
                        PathNode.QuadTo(817.6f, 1105.6f, 809.6f, 1097.6f),
                        PathNode.LineTo(332.6f, 620.6f),
                        PathNode.QuadTo(325.6f, 613.6f, 325.6f, 602.6f),
                        PathNode.QuadTo(325.6f, 591.6f, 333.6f, 583.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronbackwardRegular!!
    }

private var _chevronbackwardRegular: ImageVector? = null

val MiuixIcons.Medium.ChevronBackward: ImageVector
    get() {
        if (_chevronbackwardMedium != null) return _chevronbackwardMedium!!
        _chevronbackwardMedium = ImageVector.Builder(
            name = "ChevronBackward.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1226.3f,
            viewportHeight = 1226.3f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1226.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(337.2f, 587.5f),
                        PathNode.LineTo(811.2f, 114.5f),
                        PathNode.QuadTo(824.2f, 102.2f, 837.6f, 102.7f),
                        PathNode.QuadTo(851.1f, 103.2f, 868.0f, 119.5f),
                        PathNode.LineTo(886.0f, 137.5f),
                        PathNode.QuadTo(897.4f, 148.9f, 898.1f, 162.4f),
                        PathNode.QuadTo(898.8f, 176.0f, 889.0f, 186.3f),
                        PathNode.LineTo(463.3f, 612.4f),
                        PathNode.LineTo(889.0f, 1037.5f),
                        PathNode.QuadTo(899.4f, 1047.9f, 898.9f, 1062.4f),
                        PathNode.QuadTo(898.4f, 1077.0f, 889.0f, 1087.3f),
                        PathNode.LineTo(860.0f, 1115.3f),
                        PathNode.QuadTo(850.1f, 1124.1f, 836.5f, 1123.6f),
                        PathNode.QuadTo(823.0f, 1123.1f, 813.2f, 1113.3f),
                        PathNode.LineTo(336.2f, 636.3f),
                        PathNode.QuadTo(326.9f, 627.0f, 327.2f, 612.1f),
                        PathNode.QuadTo(327.5f, 597.3f, 337.2f, 587.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronbackwardMedium!!
    }

private var _chevronbackwardMedium: ImageVector? = null

val MiuixIcons.Demibold.ChevronBackward: ImageVector
    get() {
        if (_chevronbackwardDemibold != null) return _chevronbackwardDemibold!!
        _chevronbackwardDemibold = ImageVector.Builder(
            name = "ChevronBackward.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1239.6f,
            viewportHeight = 1239.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1239.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(339.8f, 590.3f),
                        PathNode.LineTo(813.8f, 117.3f),
                        PathNode.QuadTo(828.8f, 103.3f, 844.3f, 103.8f),
                        PathNode.QuadTo(859.8f, 104.3f, 878.8f, 122.3f),
                        PathNode.LineTo(896.8f, 140.3f),
                        PathNode.QuadTo(909.8f, 153.3f, 910.3f, 169.3f),
                        PathNode.QuadTo(910.8f, 185.3f, 899.8f, 197.3f),
                        PathNode.LineTo(477.8f, 619.3f),
                        PathNode.LineTo(899.8f, 1040.3f),
                        PathNode.QuadTo(911.8f, 1052.3f, 911.3f, 1069.3f),
                        PathNode.QuadTo(910.8f, 1086.3f, 899.8f, 1098.3f),
                        PathNode.LineTo(870.8f, 1126.3f),
                        PathNode.QuadTo(858.8f, 1136.3f, 842.8f, 1135.8f),
                        PathNode.QuadTo(826.8f, 1135.3f, 815.8f, 1124.3f),
                        PathNode.LineTo(338.8f, 647.3f),
                        PathNode.QuadTo(327.8f, 636.3f, 328.3f, 618.8f),
                        PathNode.QuadTo(328.8f, 601.3f, 339.8f, 590.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronbackwardDemibold!!
    }

private var _chevronbackwardDemibold: ImageVector? = null
