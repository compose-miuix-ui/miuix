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
            viewportWidth = 1003.2f,
            viewportHeight = 1003.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1003.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(501.9f, 548.4f),
                        PathNode.LineTo(823.5f, 871.1f),
                        PathNode.QuadTo(829.8f, 878.2f, 836.9f, 878.2f),
                        PathNode.QuadTo(844.1f, 878.2f, 851.0f, 871.1f),
                        PathNode.LineTo(866.6f, 855.4f),
                        PathNode.QuadTo(873.5f, 848.5f, 873.5f, 841.5f),
                        PathNode.QuadTo(873.5f, 834.4f, 866.6f, 827.3f),
                        PathNode.LineTo(545.7f, 504.6f),
                        PathNode.LineTo(867.5f, 182.8f),
                        PathNode.QuadTo(874.7f, 175.9f, 874.7f, 168.7f),
                        PathNode.QuadTo(874.7f, 161.5f, 867.5f, 154.6f),
                        PathNode.LineTo(851.9f, 139.0f),
                        PathNode.QuadTo(837.8f, 124.9f, 823.7f, 139.0f),
                        PathNode.LineTo(501.9f, 460.8f),
                        PathNode.LineTo(179.9f, 139.9f),
                        PathNode.QuadTo(173.0f, 133.0f, 165.5f, 133.0f),
                        PathNode.QuadTo(158.0f, 133.0f, 151.1f, 139.9f),
                        PathNode.LineTo(135.5f, 155.5f),
                        PathNode.QuadTo(128.5f, 162.4f, 128.5f, 169.6f),
                        PathNode.QuadTo(128.5f, 176.7f, 135.5f, 183.7f),
                        PathNode.LineTo(458.2f, 504.6f),
                        PathNode.LineTo(136.4f, 827.3f),
                        PathNode.QuadTo(129.4f, 834.4f, 129.4f, 841.5f),
                        PathNode.QuadTo(129.4f, 848.5f, 136.4f, 855.4f),
                        PathNode.LineTo(152.0f, 871.1f),
                        PathNode.QuadTo(159.1f, 878.2f, 166.2f, 878.2f),
                        PathNode.QuadTo(173.2f, 878.2f, 179.5f, 871.1f),
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

val MiuixIcons.Normal.Close: ImageVector
    get() {
        if (_closeNormal != null) return _closeNormal!!
        _closeNormal = ImageVector.Builder(
            name = "Close.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1033.7f,
            viewportHeight = 1033.7f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1033.7f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(516.9f, 578.5f),
                        PathNode.LineTo(831.5f, 894.9f),
                        PathNode.QuadTo(840.7f, 904.9f, 850.7f, 904.9f),
                        PathNode.QuadTo(860.6f, 904.9f, 869.8f, 894.9f),
                        PathNode.LineTo(889.0f, 875.6f),
                        PathNode.QuadTo(898.2f, 866.5f, 898.2f, 856.9f),
                        PathNode.QuadTo(898.2f, 847.4f, 889.0f, 837.3f),
                        PathNode.LineTo(574.4f, 520.9f),
                        PathNode.LineTo(890.0f, 205.3f),
                        PathNode.QuadTo(899.9f, 196.2f, 900.0f, 186.3f),
                        PathNode.QuadTo(900.0f, 176.3f, 890.0f, 167.1f),
                        PathNode.LineTo(870.6f, 147.7f),
                        PathNode.QuadTo(851.6f, 128.7f, 832.4f, 147.8f),
                        PathNode.LineTo(516.9f, 463.4f),
                        PathNode.LineTo(200.4f, 148.7f),
                        PathNode.QuadTo(191.3f, 139.6f, 181.3f, 139.5f),
                        PathNode.QuadTo(171.3f, 139.5f, 162.1f, 148.7f),
                        PathNode.LineTo(142.9f, 168.0f),
                        PathNode.QuadTo(133.7f, 177.1f, 133.7f, 187.1f),
                        PathNode.QuadTo(133.7f, 197.1f, 142.9f, 206.2f),
                        PathNode.LineTo(459.3f, 520.9f),
                        PathNode.LineTo(143.8f, 837.3f),
                        PathNode.QuadTo(134.6f, 847.4f, 134.6f, 856.9f),
                        PathNode.QuadTo(134.6f, 866.5f, 143.8f, 875.6f),
                        PathNode.LineTo(163.0f, 894.9f),
                        PathNode.QuadTo(173.1f, 904.9f, 182.6f, 904.9f),
                        PathNode.QuadTo(192.1f, 904.9f, 201.3f, 894.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeNormal!!
    }

private var _closeNormal: ImageVector? = null

val MiuixIcons.Regular.Close: ImageVector
    get() {
        if (_closeRegular != null) return _closeRegular!!
        _closeRegular = ImageVector.Builder(
            name = "Close.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1047.6f,
            viewportHeight = 1047.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1047.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(523.8f, 593.3f),
                        PathNode.LineTo(832.7f, 903.9f),
                        PathNode.QuadTo(845.0f, 916.5f, 857.1f, 916.3f),
                        PathNode.QuadTo(869.2f, 916.2f, 881.2f, 903.9f),
                        PathNode.LineTo(897.5f, 887.6f),
                        PathNode.QuadTo(909.6f, 875.6f, 909.7f, 863.8f),
                        PathNode.QuadTo(909.9f, 852.0f, 897.5f, 839.1f),
                        PathNode.LineTo(588.6f, 528.4f),
                        PathNode.LineTo(898.8f, 218.3f),
                        PathNode.QuadTo(911.0f, 206.6f, 911.3f, 194.8f),
                        PathNode.QuadTo(911.7f, 182.9f, 898.8f, 170.5f),
                        PathNode.LineTo(881.0f, 152.7f),
                        PathNode.QuadTo(858.2f, 129.9f, 833.6f, 153.8f),
                        PathNode.LineTo(523.8f, 463.6f),
                        PathNode.LineTo(213.1f, 154.7f),
                        PathNode.QuadTo(201.5f, 142.7f, 189.2f, 142.5f),
                        PathNode.QuadTo(177.0f, 142.3f, 164.6f, 154.7f),
                        PathNode.LineTo(148.6f, 171.0f),
                        PathNode.QuadTo(136.3f, 182.7f, 136.3f, 194.9f),
                        PathNode.QuadTo(136.3f, 207.1f, 148.2f, 219.2f),
                        PathNode.LineTo(458.9f, 528.4f),
                        PathNode.LineTo(149.1f, 839.1f),
                        PathNode.QuadTo(137.1f, 852.0f, 137.1f, 863.8f),
                        PathNode.QuadTo(137.1f, 875.6f, 149.1f, 887.6f),
                        PathNode.LineTo(165.5f, 903.9f),
                        PathNode.QuadTo(178.4f, 916.5f, 190.0f, 916.5f),
                        PathNode.QuadTo(201.6f, 916.5f, 214.0f, 903.9f),
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

val MiuixIcons.Medium.Close: ImageVector
    get() {
        if (_closeMedium != null) return _closeMedium!!
        _closeMedium = ImageVector.Builder(
            name = "Close.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1073.7f,
            viewportHeight = 1073.7f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1073.7f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(536.6f, 621.9f),
                        PathNode.LineTo(835.0f, 922.1f),
                        PathNode.QuadTo(853.3f, 939.4f, 869.2f, 938.9f),
                        PathNode.QuadTo(885.1f, 938.4f, 902.4f, 922.1f),
                        PathNode.LineTo(913.3f, 911.2f),
                        PathNode.QuadTo(930.7f, 893.8f, 931.2f, 877.9f),
                        PathNode.QuadTo(931.6f, 862.0f, 913.3f, 843.7f),
                        PathNode.LineTo(614.9f, 543.5f),
                        PathNode.LineTo(915.2f, 243.3f),
                        PathNode.QuadTo(931.5f, 226.9f, 932.5f, 211.5f),
                        PathNode.QuadTo(933.4f, 196.1f, 915.2f, 177.8f),
                        PathNode.LineTo(900.5f, 163.1f),
                        PathNode.QuadTo(870.6f, 133.1f, 835.9f, 165.9f),
                        PathNode.LineTo(536.6f, 465.2f),
                        PathNode.LineTo(236.4f, 166.8f),
                        PathNode.QuadTo(220.0f, 149.4f, 203.6f, 149.0f),
                        PathNode.QuadTo(187.3f, 148.5f, 169.0f, 166.8f),
                        PathNode.LineTo(159.0f, 177.7f),
                        PathNode.QuadTo(140.7f, 194.1f, 140.7f, 210.5f),
                        PathNode.QuadTo(140.7f, 226.8f, 158.1f, 244.2f),
                        PathNode.LineTo(458.3f, 543.5f),
                        PathNode.LineTo(159.0f, 843.7f),
                        PathNode.QuadTo(141.6f, 862.0f, 141.6f, 877.9f),
                        PathNode.QuadTo(141.6f, 893.8f, 159.0f, 911.2f),
                        PathNode.LineTo(169.9f, 922.1f),
                        PathNode.QuadTo(188.1f, 939.4f, 203.5f, 939.4f),
                        PathNode.QuadTo(219.0f, 939.4f, 237.3f, 922.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeMedium!!
    }

private var _closeMedium: ImageVector? = null

val MiuixIcons.Demibold.Close: ImageVector
    get() {
        if (_closeDemibold != null) return _closeDemibold!!
        _closeDemibold = ImageVector.Builder(
            name = "Close.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1092.0f,
            viewportHeight = 1092.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1092.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(545.5f, 641.5f),
                        PathNode.LineTo(837.4f, 935.2f),
                        PathNode.QuadTo(859.7f, 955.6f, 878.0f, 955.0f),
                        PathNode.QuadTo(896.4f, 954.2f, 917.3f, 935.2f),
                        PathNode.LineTo(924.7f, 927.8f),
                        PathNode.QuadTo(945.5f, 907.0f, 946.2f, 888.4f),
                        PathNode.QuadTo(946.8f, 869.7f, 924.7f, 848.0f),
                        PathNode.LineTo(632.7f, 554.3f),
                        PathNode.LineTo(926.9f, 260.1f),
                        PathNode.QuadTo(946.0f, 240.6f, 947.3f, 222.9f),
                        PathNode.QuadTo(948.6f, 205.1f, 926.9f, 182.9f),
                        PathNode.LineTo(914.2f, 170.3f),
                        PathNode.QuadTo(879.6f, 135.6f, 838.3f, 174.2f),
                        PathNode.LineTo(545.5f, 467.1f),
                        PathNode.LineTo(251.8f, 175.1f),
                        PathNode.QuadTo(232.3f, 154.3f, 213.2f, 153.6f),
                        PathNode.QuadTo(194.2f, 152.9f, 171.9f, 175.1f),
                        PathNode.LineTo(166.0f, 182.5f),
                        PathNode.QuadTo(143.7f, 202.0f, 143.7f, 221.1f),
                        PathNode.QuadTo(143.7f, 240.2f, 164.6f, 261.0f),
                        PathNode.LineTo(458.4f, 554.3f),
                        PathNode.LineTo(165.5f, 848.0f),
                        PathNode.QuadTo(144.6f, 869.7f, 144.6f, 888.4f),
                        PathNode.QuadTo(144.6f, 907.0f, 165.5f, 927.8f),
                        PathNode.LineTo(172.8f, 935.2f),
                        PathNode.QuadTo(194.7f, 955.6f, 212.5f, 955.6f),
                        PathNode.QuadTo(230.5f, 955.6f, 252.7f, 935.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeDemibold!!
    }

private var _closeDemibold: ImageVector? = null
