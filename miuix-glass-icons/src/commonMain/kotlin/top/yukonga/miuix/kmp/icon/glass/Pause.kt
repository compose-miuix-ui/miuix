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

val MiuixIcons.Glass.Pause: ImageVector
    get() = MiuixIcons.Glass.Regular.Pause

val MiuixIcons.Glass.Light.Pause: ImageVector
    get() {
        if (_pauseLight != null) return _pauseLight!!
        _pauseLight = ImageVector.Builder(
            name = "Pause.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(878.5f, 125.6f),
                        PathNode.LineTo(878.5f, 1017.3f),
                        PathNode.QuadTo(878.5f, 1026.5f, 873.2f, 1031.8f),
                        PathNode.QuadTo(867.9f, 1037.0f, 858.7f, 1037.0f),
                        PathNode.LineTo(835.8f, 1037.0f),
                        PathNode.QuadTo(826.6f, 1037.0f, 821.3f, 1031.8f),
                        PathNode.QuadTo(816.0f, 1026.5f, 816.0f, 1017.3f),
                        PathNode.LineTo(816.0f, 125.6f),
                        PathNode.QuadTo(816.0f, 116.4f, 821.3f, 111.1f),
                        PathNode.QuadTo(826.6f, 105.8f, 835.8f, 105.8f),
                        PathNode.LineTo(858.7f, 105.8f),
                        PathNode.QuadTo(864.6f, 105.8f, 869.3f, 108.1f),
                        PathNode.QuadTo(873.9f, 110.4f, 876.2f, 115.1f),
                        PathNode.QuadTo(878.5f, 119.7f, 878.5f, 125.6f),
                        PathNode.Close,
                        PathNode.MoveTo(326.8f, 125.6f),
                        PathNode.LineTo(326.8f, 1017.3f),
                        PathNode.QuadTo(326.8f, 1023.2f, 324.5f, 1027.8f),
                        PathNode.QuadTo(322.2f, 1032.4f, 317.6f, 1034.7f),
                        PathNode.QuadTo(312.9f, 1037.0f, 307.0f, 1037.0f),
                        PathNode.LineTo(284.2f, 1037.0f),
                        PathNode.QuadTo(274.9f, 1037.0f, 269.6f, 1031.8f),
                        PathNode.QuadTo(264.4f, 1026.5f, 264.4f, 1017.3f),
                        PathNode.LineTo(264.4f, 125.6f),
                        PathNode.QuadTo(264.4f, 119.7f, 266.7f, 115.0f),
                        PathNode.QuadTo(269.0f, 110.4f, 273.6f, 108.1f),
                        PathNode.QuadTo(278.3f, 105.8f, 284.2f, 105.8f),
                        PathNode.LineTo(307.0f, 105.8f),
                        PathNode.QuadTo(313.0f, 105.8f, 317.6f, 108.1f),
                        PathNode.QuadTo(322.2f, 110.4f, 324.5f, 115.1f),
                        PathNode.QuadTo(326.8f, 119.7f, 326.8f, 125.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _pauseLight!!
    }

private var _pauseLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Pause: ImageVector
    get() {
        if (_pauseNormal != null) return _pauseNormal!!
        _pauseNormal = ImageVector.Builder(
            name = "Pause.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(891.2f, 127.9f),
                        PathNode.LineTo(891.2f, 1015.0f),
                        PathNode.QuadTo(891.2f, 1027.5f, 884.0f, 1034.6f),
                        PathNode.QuadTo(876.9f, 1041.8f, 864.4f, 1041.8f),
                        PathNode.LineTo(836.6f, 1041.8f),
                        PathNode.QuadTo(824.1f, 1041.8f, 816.9f, 1034.6f),
                        PathNode.QuadTo(809.8f, 1027.5f, 809.8f, 1015.0f),
                        PathNode.LineTo(809.8f, 127.9f),
                        PathNode.QuadTo(809.8f, 115.4f, 816.9f, 108.2f),
                        PathNode.QuadTo(824.1f, 101.1f, 836.6f, 101.1f),
                        PathNode.LineTo(864.4f, 101.1f),
                        PathNode.QuadTo(872.4f, 101.1f, 878.7f, 104.2f),
                        PathNode.QuadTo(884.9f, 107.3f, 888.0f, 113.6f),
                        PathNode.QuadTo(891.2f, 119.9f, 891.2f, 127.9f),
                        PathNode.Close,
                        PathNode.MoveTo(333.1f, 127.9f),
                        PathNode.LineTo(333.1f, 1015.0f),
                        PathNode.QuadTo(333.1f, 1023.0f, 330.0f, 1029.3f),
                        PathNode.QuadTo(326.8f, 1035.5f, 320.6f, 1038.6f),
                        PathNode.QuadTo(314.3f, 1041.8f, 306.3f, 1041.8f),
                        PathNode.LineTo(278.5f, 1041.8f),
                        PathNode.QuadTo(266.0f, 1041.8f, 258.8f, 1034.6f),
                        PathNode.QuadTo(251.7f, 1027.5f, 251.7f, 1015.0f),
                        PathNode.LineTo(251.7f, 127.9f),
                        PathNode.QuadTo(251.7f, 119.8f, 254.8f, 113.6f),
                        PathNode.QuadTo(258.0f, 107.3f, 264.2f, 104.2f),
                        PathNode.QuadTo(270.5f, 101.1f, 278.5f, 101.1f),
                        PathNode.LineTo(306.3f, 101.1f),
                        PathNode.QuadTo(314.3f, 101.1f, 320.6f, 104.2f),
                        PathNode.QuadTo(326.8f, 107.3f, 330.0f, 113.6f),
                        PathNode.QuadTo(333.1f, 119.9f, 333.1f, 127.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _pauseNormal!!
    }

private var _pauseNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Pause: ImageVector
    get() {
        if (_pauseRegular != null) return _pauseRegular!!
        _pauseRegular = ImageVector.Builder(
            name = "Pause.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(896.9f, 128.9f),
                        PathNode.LineTo(896.9f, 1013.9f),
                        PathNode.QuadTo(896.9f, 1027.9f, 888.9f, 1035.9f),
                        PathNode.QuadTo(880.9f, 1043.9f, 866.9f, 1043.9f),
                        PathNode.LineTo(836.9f, 1043.9f),
                        PathNode.QuadTo(822.9f, 1043.9f, 814.9f, 1035.9f),
                        PathNode.QuadTo(806.9f, 1027.9f, 806.9f, 1013.9f),
                        PathNode.LineTo(806.9f, 128.9f),
                        PathNode.QuadTo(806.9f, 114.9f, 814.9f, 106.9f),
                        PathNode.QuadTo(822.9f, 98.9f, 836.9f, 98.9f),
                        PathNode.LineTo(866.9f, 98.9f),
                        PathNode.QuadTo(875.9f, 98.9f, 882.9f, 102.4f),
                        PathNode.QuadTo(889.9f, 105.9f, 893.4f, 112.9f),
                        PathNode.QuadTo(896.9f, 119.9f, 896.9f, 128.9f),
                        PathNode.Close,
                        PathNode.MoveTo(335.9f, 128.9f),
                        PathNode.LineTo(335.9f, 1013.9f),
                        PathNode.QuadTo(335.9f, 1022.9f, 332.4f, 1029.9f),
                        PathNode.QuadTo(328.9f, 1036.9f, 321.9f, 1040.4f),
                        PathNode.QuadTo(314.9f, 1043.9f, 305.9f, 1043.9f),
                        PathNode.LineTo(275.9f, 1043.9f),
                        PathNode.QuadTo(261.9f, 1043.9f, 253.9f, 1035.9f),
                        PathNode.QuadTo(245.9f, 1027.9f, 245.9f, 1013.9f),
                        PathNode.LineTo(245.9f, 128.9f),
                        PathNode.QuadTo(245.9f, 119.9f, 249.4f, 112.9f),
                        PathNode.QuadTo(252.9f, 105.9f, 259.9f, 102.4f),
                        PathNode.QuadTo(266.9f, 98.9f, 275.9f, 98.9f),
                        PathNode.LineTo(305.9f, 98.9f),
                        PathNode.QuadTo(314.9f, 98.9f, 321.9f, 102.4f),
                        PathNode.QuadTo(328.9f, 105.9f, 332.4f, 112.9f),
                        PathNode.QuadTo(335.9f, 119.9f, 335.9f, 128.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _pauseRegular!!
    }

private var _pauseRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Pause: ImageVector
    get() {
        if (_pauseMedium != null) return _pauseMedium!!
        _pauseMedium = ImageVector.Builder(
            name = "Pause.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(906.4f, 132.2f),
                        PathNode.LineTo(906.4f, 1010.4f),
                        PathNode.QuadTo(906.1f, 1033.1f, 896.5f, 1043.2f),
                        PathNode.QuadTo(886.9f, 1053.4f, 866.4f, 1053.4f),
                        PathNode.LineTo(837.7f, 1053.4f),
                        PathNode.QuadTo(816.4f, 1053.4f, 807.0f, 1043.1f),
                        PathNode.QuadTo(797.5f, 1032.8f, 797.5f, 1010.4f),
                        PathNode.LineTo(797.5f, 132.2f),
                        PathNode.QuadTo(797.5f, 110.1f, 807.0f, 99.8f),
                        PathNode.QuadTo(816.4f, 89.5f, 837.2f, 89.5f),
                        PathNode.LineTo(865.8f, 89.5f),
                        PathNode.QuadTo(880.8f, 89.5f, 889.8f, 94.1f),
                        PathNode.QuadTo(898.8f, 98.6f, 902.5f, 107.9f),
                        PathNode.QuadTo(906.1f, 117.2f, 906.4f, 132.2f),
                        PathNode.Close,
                        PathNode.MoveTo(345.4f, 132.2f),
                        PathNode.LineTo(345.4f, 1010.4f),
                        PathNode.QuadTo(345.1f, 1025.9f, 340.9f, 1035.5f),
                        PathNode.QuadTo(336.8f, 1045.0f, 328.0f, 1049.2f),
                        PathNode.QuadTo(319.3f, 1053.4f, 305.4f, 1053.4f),
                        PathNode.LineTo(276.7f, 1053.4f),
                        PathNode.QuadTo(255.2f, 1053.4f, 245.8f, 1043.0f),
                        PathNode.QuadTo(236.5f, 1032.5f, 236.5f, 1010.4f),
                        PathNode.LineTo(236.5f, 132.2f),
                        PathNode.QuadTo(236.5f, 117.5f, 240.1f, 108.2f),
                        PathNode.QuadTo(243.7f, 98.9f, 252.8f, 94.2f),
                        PathNode.QuadTo(261.8f, 89.5f, 276.7f, 89.5f),
                        PathNode.LineTo(305.4f, 89.5f),
                        PathNode.QuadTo(320.3f, 89.5f, 329.2f, 94.3f),
                        PathNode.QuadTo(338.1f, 99.2f, 341.8f, 108.3f),
                        PathNode.QuadTo(345.4f, 117.5f, 345.4f, 132.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _pauseMedium!!
    }

private var _pauseMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Pause: ImageVector
    get() {
        if (_pauseDemibold != null) return _pauseDemibold!!
        _pauseDemibold = ImageVector.Builder(
            name = "Pause.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(913.0f, 134.4f),
                        PathNode.LineTo(913.0f, 1008.0f),
                        PathNode.QuadTo(912.6f, 1036.7f, 901.8f, 1048.3f),
                        PathNode.QuadTo(891.0f, 1060.0f, 866.0f, 1060.0f),
                        PathNode.LineTo(838.3f, 1060.0f),
                        PathNode.QuadTo(811.9f, 1060.0f, 801.4f, 1048.1f),
                        PathNode.QuadTo(790.8f, 1036.2f, 790.8f, 1008.0f),
                        PathNode.LineTo(790.8f, 134.4f),
                        PathNode.QuadTo(790.8f, 106.7f, 801.4f, 94.8f),
                        PathNode.QuadTo(811.9f, 82.8f, 837.4f, 82.8f),
                        PathNode.LineTo(865.1f, 82.8f),
                        PathNode.QuadTo(884.2f, 82.8f, 894.6f, 88.2f),
                        PathNode.QuadTo(905.1f, 93.5f, 908.8f, 104.4f),
                        PathNode.QuadTo(912.6f, 115.3f, 913.0f, 134.4f),
                        PathNode.Close,
                        PathNode.MoveTo(352.0f, 134.4f),
                        PathNode.LineTo(352.0f, 1008.0f),
                        PathNode.QuadTo(351.6f, 1028.0f, 346.9f, 1039.3f),
                        PathNode.QuadTo(342.3f, 1050.7f, 332.3f, 1055.4f),
                        PathNode.QuadTo(322.3f, 1060.0f, 305.0f, 1060.0f),
                        PathNode.LineTo(277.3f, 1060.0f),
                        PathNode.QuadTo(250.4f, 1060.0f, 240.1f, 1047.9f),
                        PathNode.QuadTo(229.8f, 1035.7f, 229.8f, 1008.0f),
                        PathNode.LineTo(229.8f, 134.4f),
                        PathNode.QuadTo(229.8f, 115.8f, 233.6f, 104.9f),
                        PathNode.QuadTo(237.3f, 94.0f, 247.8f, 88.4f),
                        PathNode.QuadTo(258.2f, 82.8f, 277.3f, 82.8f),
                        PathNode.LineTo(305.0f, 82.8f),
                        PathNode.QuadTo(324.1f, 82.8f, 334.3f, 88.6f),
                        PathNode.QuadTo(344.6f, 94.4f, 348.3f, 105.1f),
                        PathNode.QuadTo(352.0f, 115.8f, 352.0f, 134.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _pauseDemibold!!
    }

private var _pauseDemibold: ImageVector? = null
