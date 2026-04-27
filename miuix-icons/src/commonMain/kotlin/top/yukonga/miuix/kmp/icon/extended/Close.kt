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
                        PathNode.MoveTo(502.1f, 548.6f),
                        PathNode.LineTo(865.1f, 912.6f),
                        PathNode.QuadTo(871.1f, 919.6f, 878.1f, 919.6f),
                        PathNode.QuadTo(885.1f, 919.6f, 892.1f, 912.6f),
                        PathNode.LineTo(908.1f, 896.6f),
                        PathNode.QuadTo(915.1f, 889.6f, 915.1f, 882.6f),
                        PathNode.QuadTo(915.1f, 875.6f, 908.1f, 868.6f),
                        PathNode.LineTo(546.1f, 504.6f),
                        PathNode.LineTo(909.1f, 141.6f),
                        PathNode.QuadTo(916.1f, 134.6f, 916.1f, 127.6f),
                        PathNode.QuadTo(916.1f, 120.6f, 909.1f, 113.6f),
                        PathNode.LineTo(893.1f, 97.6f),
                        PathNode.QuadTo(879.1f, 83.6f, 865.1f, 97.6f),
                        PathNode.LineTo(502.1f, 460.6f),
                        PathNode.LineTo(139.1f, 98.6f),
                        PathNode.QuadTo(132.1f, 91.6f, 124.6f, 91.6f),
                        PathNode.QuadTo(117.1f, 91.6f, 110.1f, 98.6f),
                        PathNode.LineTo(94.1f, 114.6f),
                        PathNode.QuadTo(87.1f, 121.6f, 87.1f, 128.6f),
                        PathNode.QuadTo(87.1f, 135.6f, 94.1f, 142.6f),
                        PathNode.LineTo(458.1f, 504.6f),
                        PathNode.LineTo(95.1f, 868.6f),
                        PathNode.QuadTo(88.1f, 875.6f, 88.1f, 882.6f),
                        PathNode.QuadTo(88.1f, 889.6f, 95.1f, 896.6f),
                        PathNode.LineTo(111.1f, 912.6f),
                        PathNode.QuadTo(118.1f, 919.6f, 125.1f, 919.6f),
                        PathNode.QuadTo(132.1f, 919.6f, 138.1f, 912.6f),
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
                        PathNode.MoveTo(517.0f, 578.6f),
                        PathNode.LineTo(874.5f, 937.8f),
                        PathNode.QuadTo(883.3f, 947.6f, 893.0f, 947.6f),
                        PathNode.QuadTo(902.8f, 947.6f, 911.8f, 937.8f),
                        PathNode.LineTo(932.0f, 917.7f),
                        PathNode.QuadTo(941.0f, 908.6f, 941.0f, 899.2f),
                        PathNode.QuadTo(941.0f, 889.8f, 932.0f, 880.1f),
                        PathNode.LineTo(574.8f, 520.9f),
                        PathNode.LineTo(933.0f, 162.7f),
                        PathNode.QuadTo(942.7f, 153.6f, 942.7f, 143.9f),
                        PathNode.QuadTo(942.7f, 134.1f, 933.0f, 125.1f),
                        PathNode.LineTo(912.8f, 105.0f),
                        PathNode.QuadTo(894.0f, 86.1f, 875.2f, 105.0f),
                        PathNode.LineTo(517.0f, 463.1f),
                        PathNode.LineTo(158.1f, 106.0f),
                        PathNode.QuadTo(149.1f, 96.9f, 139.2f, 96.9f),
                        PathNode.QuadTo(129.3f, 96.9f, 120.2f, 106.0f),
                        PathNode.LineTo(100.1f, 126.1f),
                        PathNode.QuadTo(91.0f, 135.1f, 91.0f, 144.9f),
                        PathNode.QuadTo(91.0f, 154.6f, 100.1f, 163.7f),
                        PathNode.LineTo(459.3f, 520.9f),
                        PathNode.LineTo(101.1f, 880.1f),
                        PathNode.QuadTo(92.0f, 889.8f, 92.0f, 899.2f),
                        PathNode.QuadTo(92.0f, 908.6f, 101.1f, 917.7f),
                        PathNode.LineTo(121.2f, 937.8f),
                        PathNode.QuadTo(131.0f, 947.6f, 140.4f, 947.6f),
                        PathNode.QuadTo(149.8f, 947.6f, 158.5f, 937.8f),
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
                        PathNode.MoveTo(523.8f, 592.3f),
                        PathNode.LineTo(878.8f, 949.3f),
                        PathNode.QuadTo(888.8f, 960.3f, 899.8f, 960.3f),
                        PathNode.QuadTo(910.8f, 960.3f, 920.8f, 949.3f),
                        PathNode.LineTo(942.8f, 927.3f),
                        PathNode.QuadTo(952.8f, 917.3f, 952.8f, 906.8f),
                        PathNode.QuadTo(952.8f, 896.3f, 942.8f, 885.3f),
                        PathNode.LineTo(587.8f, 528.3f),
                        PathNode.LineTo(943.8f, 172.3f),
                        PathNode.QuadTo(954.8f, 162.3f, 954.8f, 151.3f),
                        PathNode.QuadTo(954.8f, 140.3f, 943.8f, 130.3f),
                        PathNode.LineTo(921.8f, 108.3f),
                        PathNode.QuadTo(900.8f, 87.3f, 879.8f, 108.3f),
                        PathNode.LineTo(523.8f, 464.3f),
                        PathNode.LineTo(166.8f, 109.3f),
                        PathNode.QuadTo(156.8f, 99.3f, 145.8f, 99.3f),
                        PathNode.QuadTo(134.8f, 99.3f, 124.8f, 109.3f),
                        PathNode.LineTo(102.8f, 131.3f),
                        PathNode.QuadTo(92.8f, 141.3f, 92.8f, 152.3f),
                        PathNode.QuadTo(92.8f, 163.3f, 102.8f, 173.3f),
                        PathNode.LineTo(459.8f, 528.3f),
                        PathNode.LineTo(103.8f, 885.3f),
                        PathNode.QuadTo(93.8f, 896.3f, 93.8f, 906.8f),
                        PathNode.QuadTo(93.8f, 917.3f, 103.8f, 927.3f),
                        PathNode.LineTo(125.8f, 949.3f),
                        PathNode.QuadTo(136.8f, 960.3f, 147.3f, 960.3f),
                        PathNode.QuadTo(157.8f, 960.3f, 167.8f, 949.3f),
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
                        PathNode.MoveTo(536.6f, 620.9f),
                        PathNode.LineTo(882.2f, 968.5f),
                        PathNode.QuadTo(898.0f, 984.2f, 912.9f, 983.9f),
                        PathNode.QuadTo(927.7f, 983.7f, 943.0f, 968.5f),
                        PathNode.LineTo(959.7f, 951.8f),
                        PathNode.QuadTo(975.0f, 936.5f, 975.3f, 921.9f),
                        PathNode.QuadTo(975.6f, 907.3f, 959.7f, 891.0f),
                        PathNode.LineTo(614.1f, 543.4f),
                        PathNode.LineTo(961.3f, 196.2f),
                        PathNode.QuadTo(976.4f, 181.5f, 977.0f, 167.0f),
                        PathNode.QuadTo(977.6f, 152.5f, 961.3f, 136.6f),
                        PathNode.LineTo(942.2f, 117.5f),
                        PathNode.QuadTo(914.2f, 89.5f, 883.2f, 119.3f),
                        PathNode.LineTo(536.6f, 465.9f),
                        PathNode.LineTo(189.0f, 120.3f),
                        PathNode.QuadTo(174.3f, 105.0f, 159.2f, 104.7f),
                        PathNode.QuadTo(144.0f, 104.4f, 128.2f, 120.3f),
                        PathNode.LineTo(112.0f, 137.0f),
                        PathNode.QuadTo(96.2f, 151.7f, 96.2f, 166.8f),
                        PathNode.QuadTo(96.2f, 181.9f, 111.4f, 197.2f),
                        PathNode.LineTo(459.0f, 543.4f),
                        PathNode.LineTo(112.4f, 891.0f),
                        PathNode.QuadTo(97.2f, 907.3f, 97.2f, 921.9f),
                        PathNode.QuadTo(97.2f, 936.5f, 112.4f, 951.8f),
                        PathNode.LineTo(129.2f, 968.5f),
                        PathNode.QuadTo(145.4f, 984.2f, 159.8f, 984.2f),
                        PathNode.QuadTo(174.1f, 984.2f, 190.0f, 968.5f),
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
                        PathNode.MoveTo(545.5f, 641.0f),
                        PathNode.LineTo(884.5f, 982.0f),
                        PathNode.QuadTo(904.5f, 1001.0f, 922.0f, 1000.5f),
                        PathNode.QuadTo(939.5f, 1000.0f, 958.5f, 982.0f),
                        PathNode.LineTo(971.5f, 969.0f),
                        PathNode.QuadTo(990.5f, 950.0f, 991.0f, 932.5f),
                        PathNode.QuadTo(991.5f, 915.0f, 971.5f, 895.0f),
                        PathNode.LineTo(632.5f, 554.0f),
                        PathNode.LineTo(973.5f, 213.0f),
                        PathNode.QuadTo(991.5f, 195.0f, 992.5f, 178.0f),
                        PathNode.QuadTo(993.5f, 161.0f, 973.5f, 141.0f),
                        PathNode.LineTo(956.5f, 124.0f),
                        PathNode.QuadTo(923.5f, 91.0f, 885.5f, 127.0f),
                        PathNode.LineTo(545.5f, 467.0f),
                        PathNode.LineTo(204.5f, 128.0f),
                        PathNode.QuadTo(186.5f, 109.0f, 168.5f, 108.5f),
                        PathNode.QuadTo(150.5f, 108.0f, 130.5f, 128.0f),
                        PathNode.LineTo(118.5f, 141.0f),
                        PathNode.QuadTo(98.5f, 159.0f, 98.5f, 177.0f),
                        PathNode.QuadTo(98.5f, 195.0f, 117.5f, 214.0f),
                        PathNode.LineTo(458.5f, 554.0f),
                        PathNode.LineTo(118.5f, 895.0f),
                        PathNode.QuadTo(99.5f, 915.0f, 99.5f, 932.5f),
                        PathNode.QuadTo(99.5f, 950.0f, 118.5f, 969.0f),
                        PathNode.LineTo(131.5f, 982.0f),
                        PathNode.QuadTo(151.5f, 1001.0f, 168.5f, 1001.0f),
                        PathNode.QuadTo(185.5f, 1001.0f, 205.5f, 982.0f),
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
