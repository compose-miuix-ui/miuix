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

val MiuixIcons.Pause: ImageVector
    get() = MiuixIcons.Regular.Pause

val MiuixIcons.Light.Pause: ImageVector
    get() {
        if (_pauseLight != null) return _pauseLight!!
        _pauseLight = ImageVector.Builder(
            name = "Pause.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1117.2f,
            viewportHeight = 1117.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1117.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(865.6f, 112.1f),
                        PathNode.VerticalTo(1004.1f),
                        PathNode.QuadTo(865.6f, 1013.4f, 860.3f, 1018.8f),
                        PathNode.QuadTo(854.9f, 1024.1f, 845.6f, 1024.1f),
                        PathNode.HorizontalTo(822.6f),
                        PathNode.QuadTo(813.3f, 1024.1f, 807.9f, 1018.8f),
                        PathNode.QuadTo(802.6f, 1013.4f, 802.6f, 1004.1f),
                        PathNode.VerticalTo(112.1f),
                        PathNode.QuadTo(802.6f, 103.2f, 807.9f, 98.2f),
                        PathNode.QuadTo(813.3f, 93.1f, 822.6f, 93.1f),
                        PathNode.HorizontalTo(845.6f),
                        PathNode.QuadTo(854.9f, 93.1f, 860.3f, 98.2f),
                        PathNode.QuadTo(865.6f, 103.2f, 865.6f, 112.1f),
                        PathNode.Close,
                        PathNode.MoveTo(313.6f, 112.1f),
                        PathNode.VerticalTo(1004.1f),
                        PathNode.QuadTo(313.6f, 1013.4f, 308.6f, 1018.8f),
                        PathNode.QuadTo(303.6f, 1024.1f, 293.6f, 1024.1f),
                        PathNode.HorizontalTo(270.6f),
                        PathNode.QuadTo(261.7f, 1024.1f, 256.7f, 1018.8f),
                        PathNode.QuadTo(251.6f, 1013.4f, 251.6f, 1004.1f),
                        PathNode.VerticalTo(112.1f),
                        PathNode.QuadTo(251.6f, 103.2f, 256.7f, 98.2f),
                        PathNode.QuadTo(261.7f, 93.1f, 270.6f, 93.1f),
                        PathNode.HorizontalTo(293.1f),
                        PathNode.QuadTo(303.6f, 93.1f, 308.6f, 98.2f),
                        PathNode.QuadTo(313.6f, 103.2f, 313.6f, 112.1f),
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

val MiuixIcons.Normal.Pause: ImageVector
    get() {
        if (_pauseNormal != null) return _pauseNormal!!
        _pauseNormal = ImageVector.Builder(
            name = "Pause.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1128.8f,
            viewportHeight = 1128.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1128.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(884.1f, 120.6f),
                        PathNode.VerticalTo(1007.8f),
                        PathNode.QuadTo(884.1f, 1020.4f, 876.9f, 1027.5f),
                        PathNode.QuadTo(869.8f, 1034.7f, 857.2f, 1034.7f),
                        PathNode.HorizontalTo(829.4f),
                        PathNode.QuadTo(816.9f, 1034.7f, 809.7f, 1027.5f),
                        PathNode.QuadTo(802.5f, 1020.4f, 802.5f, 1007.8f),
                        PathNode.VerticalTo(120.6f),
                        PathNode.QuadTo(802.5f, 108.2f, 809.7f, 101.1f),
                        PathNode.QuadTo(816.9f, 94.1f, 829.4f, 94.1f),
                        PathNode.HorizontalTo(857.2f),
                        PathNode.QuadTo(869.8f, 94.1f, 876.9f, 101.1f),
                        PathNode.QuadTo(884.1f, 108.2f, 884.1f, 120.6f),
                        PathNode.Close,
                        PathNode.MoveTo(325.9f, 120.6f),
                        PathNode.VerticalTo(1007.8f),
                        PathNode.QuadTo(325.9f, 1020.4f, 318.8f, 1027.5f),
                        PathNode.QuadTo(311.8f, 1034.7f, 299.0f, 1034.7f),
                        PathNode.HorizontalTo(271.2f),
                        PathNode.QuadTo(258.8f, 1034.7f, 251.7f, 1027.5f),
                        PathNode.QuadTo(244.7f, 1020.4f, 244.7f, 1007.8f),
                        PathNode.VerticalTo(120.6f),
                        PathNode.QuadTo(244.7f, 108.2f, 251.7f, 101.1f),
                        PathNode.QuadTo(258.8f, 94.1f, 271.2f, 94.1f),
                        PathNode.HorizontalTo(298.9f),
                        PathNode.QuadTo(311.8f, 94.1f, 318.8f, 101.1f),
                        PathNode.QuadTo(325.9f, 108.2f, 325.9f, 120.6f),
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

val MiuixIcons.Regular.Pause: ImageVector
    get() {
        if (_pauseRegular != null) return _pauseRegular!!
        _pauseRegular = ImageVector.Builder(
            name = "Pause.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1134.0f,
            viewportHeight = 1134.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1134.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(892.5f, 124.5f),
                        PathNode.VerticalTo(1009.5f),
                        PathNode.QuadTo(892.5f, 1023.5f, 884.5f, 1031.5f),
                        PathNode.QuadTo(876.5f, 1039.5f, 862.5f, 1039.5f),
                        PathNode.HorizontalTo(832.5f),
                        PathNode.QuadTo(818.5f, 1039.5f, 810.5f, 1031.5f),
                        PathNode.QuadTo(802.5f, 1023.5f, 802.5f, 1009.5f),
                        PathNode.VerticalTo(124.5f),
                        PathNode.QuadTo(802.5f, 110.5f, 810.5f, 102.5f),
                        PathNode.QuadTo(818.5f, 94.5f, 832.5f, 94.5f),
                        PathNode.HorizontalTo(862.5f),
                        PathNode.QuadTo(876.5f, 94.5f, 884.5f, 102.5f),
                        PathNode.QuadTo(892.5f, 110.5f, 892.5f, 124.5f),
                        PathNode.Close,
                        PathNode.MoveTo(331.5f, 124.5f),
                        PathNode.VerticalTo(1009.5f),
                        PathNode.QuadTo(331.5f, 1023.5f, 323.5f, 1031.5f),
                        PathNode.QuadTo(315.5f, 1039.5f, 301.5f, 1039.5f),
                        PathNode.HorizontalTo(271.5f),
                        PathNode.QuadTo(257.5f, 1039.5f, 249.5f, 1031.5f),
                        PathNode.QuadTo(241.5f, 1023.5f, 241.5f, 1009.5f),
                        PathNode.VerticalTo(124.5f),
                        PathNode.QuadTo(241.5f, 110.5f, 249.5f, 102.5f),
                        PathNode.QuadTo(257.5f, 94.5f, 271.5f, 94.5f),
                        PathNode.HorizontalTo(301.5f),
                        PathNode.QuadTo(315.5f, 94.5f, 323.5f, 102.5f),
                        PathNode.QuadTo(331.5f, 110.5f, 331.5f, 124.5f),
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

val MiuixIcons.Medium.Pause: ImageVector
    get() {
        if (_pauseMedium != null) return _pauseMedium!!
        _pauseMedium = ImageVector.Builder(
            name = "Pause.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1156.6f,
            viewportHeight = 1156.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1156.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(913.2f, 139.3f),
                        PathNode.VerticalTo(1017.3f),
                        PathNode.QuadTo(912.6f, 1040.1f, 903.4f, 1050.1f),
                        PathNode.QuadTo(894.3f, 1060.2f, 873.2f, 1060.2f),
                        PathNode.HorizontalTo(844.4f),
                        PathNode.QuadTo(823.3f, 1060.2f, 813.9f, 1049.9f),
                        PathNode.QuadTo(804.4f, 1039.5f, 804.4f, 1017.3f),
                        PathNode.VerticalTo(139.3f),
                        PathNode.QuadTo(804.4f, 117.1f, 813.9f, 106.7f),
                        PathNode.QuadTo(823.3f, 96.4f, 843.8f, 96.4f),
                        PathNode.HorizontalTo(872.6f),
                        PathNode.QuadTo(894.9f, 96.4f, 904.0f, 106.7f),
                        PathNode.QuadTo(913.2f, 117.1f, 913.2f, 139.3f),
                        PathNode.Close,
                        PathNode.MoveTo(352.2f, 139.3f),
                        PathNode.VerticalTo(1017.3f),
                        PathNode.QuadTo(351.6f, 1040.7f, 342.4f, 1050.4f),
                        PathNode.QuadTo(333.3f, 1060.2f, 312.2f, 1060.2f),
                        PathNode.HorizontalTo(283.4f),
                        PathNode.QuadTo(261.7f, 1060.2f, 252.6f, 1049.9f),
                        PathNode.QuadTo(243.4f, 1039.5f, 243.4f, 1017.3f),
                        PathNode.VerticalTo(139.3f),
                        PathNode.QuadTo(243.4f, 117.1f, 252.3f, 106.7f),
                        PathNode.QuadTo(261.1f, 96.4f, 283.4f, 96.4f),
                        PathNode.HorizontalTo(312.2f),
                        PathNode.QuadTo(334.4f, 96.4f, 343.3f, 106.7f),
                        PathNode.QuadTo(352.2f, 117.1f, 352.2f, 139.3f),
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

val MiuixIcons.Demibold.Pause: ImageVector
    get() {
        if (_pauseDemibold != null) return _pauseDemibold!!
        _pauseDemibold = ImageVector.Builder(
            name = "Pause.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1172.4f,
            viewportHeight = 1172.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1172.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(927.7f, 149.7f),
                        PathNode.VerticalTo(1022.7f),
                        PathNode.QuadTo(926.7f, 1051.7f, 916.7f, 1063.2f),
                        PathNode.QuadTo(906.7f, 1074.7f, 880.7f, 1074.7f),
                        PathNode.HorizontalTo(852.7f),
                        PathNode.QuadTo(826.7f, 1074.7f, 816.2f, 1062.7f),
                        PathNode.QuadTo(805.7f, 1050.7f, 805.7f, 1022.7f),
                        PathNode.VerticalTo(149.7f),
                        PathNode.QuadTo(805.7f, 121.7f, 816.2f, 109.7f),
                        PathNode.QuadTo(826.7f, 97.7f, 851.7f, 97.7f),
                        PathNode.HorizontalTo(879.7f),
                        PathNode.QuadTo(907.7f, 97.7f, 917.7f, 109.7f),
                        PathNode.QuadTo(927.7f, 121.7f, 927.7f, 149.7f),
                        PathNode.Close,
                        PathNode.MoveTo(366.7f, 149.7f),
                        PathNode.VerticalTo(1022.7f),
                        PathNode.QuadTo(365.7f, 1052.7f, 355.7f, 1063.7f),
                        PathNode.QuadTo(345.7f, 1074.7f, 319.7f, 1074.7f),
                        PathNode.HorizontalTo(291.7f),
                        PathNode.QuadTo(264.7f, 1074.7f, 254.7f, 1062.7f),
                        PathNode.QuadTo(244.7f, 1050.7f, 244.7f, 1022.7f),
                        PathNode.VerticalTo(149.7f),
                        PathNode.QuadTo(244.7f, 121.7f, 254.2f, 109.7f),
                        PathNode.QuadTo(263.7f, 97.7f, 291.7f, 97.7f),
                        PathNode.HorizontalTo(319.7f),
                        PathNode.QuadTo(347.7f, 97.7f, 357.2f, 109.7f),
                        PathNode.QuadTo(366.7f, 121.7f, 366.7f, 149.7f),
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
