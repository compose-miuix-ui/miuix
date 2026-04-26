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

val MiuixIcons.WorldClock: ImageVector
    get() = MiuixIcons.Regular.WorldClock

val MiuixIcons.Light.WorldClock: ImageVector
    get() {
        if (_worldclockLight != null) return _worldclockLight!!
        _worldclockLight = ImageVector.Builder(
            name = "WorldClock.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1171.2f,
            viewportHeight = 1171.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1171.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1073.6f, 586.1f),
                        PathNode.QuadTo(1073.6f, 717.6f, 1008.1f, 830.1f),
                        PathNode.QuadTo(942.6f, 942.5f, 830.6f, 1008.1f),
                        PathNode.QuadTo(718.6f, 1073.6f, 585.6f, 1073.6f),
                        PathNode.QuadTo(452.6f, 1073.6f, 340.6f, 1008.1f),
                        PathNode.QuadTo(228.6f, 942.6f, 163.1f, 830.1f),
                        PathNode.QuadTo(97.6f, 717.6f, 97.6f, 585.6f),
                        PathNode.QuadTo(97.6f, 453.6f, 163.1f, 341.3f),
                        PathNode.QuadTo(228.6f, 229.0f, 340.6f, 163.3f),
                        PathNode.QuadTo(452.6f, 97.6f, 585.6f, 97.6f),
                        PathNode.QuadTo(718.6f, 97.6f, 830.6f, 163.3f),
                        PathNode.QuadTo(942.6f, 229.0f, 1008.1f, 341.3f),
                        PathNode.QuadTo(1073.6f, 453.6f, 1073.6f, 586.1f),
                        PathNode.Close,
                        PathNode.MoveTo(561.6f, 586.6f),
                        PathNode.VerticalTo(933.6f),
                        PathNode.QuadTo(561.6f, 941.8f, 565.6f, 945.2f),
                        PathNode.QuadTo(569.6f, 948.6f, 577.6f, 948.6f),
                        PathNode.HorizontalTo(593.6f),
                        PathNode.QuadTo(601.6f, 948.6f, 605.6f, 945.2f),
                        PathNode.QuadTo(609.6f, 941.8f, 609.6f, 933.6f),
                        PathNode.VerticalTo(610.6f),
                        PathNode.HorizontalTo(862.6f),
                        PathNode.QuadTo(870.6f, 610.6f, 874.6f, 606.3f),
                        PathNode.QuadTo(878.6f, 602.0f, 878.6f, 595.6f),
                        PathNode.VerticalTo(576.5f),
                        PathNode.QuadTo(878.6f, 569.6f, 874.6f, 565.6f),
                        PathNode.QuadTo(870.6f, 561.6f, 862.6f, 561.6f),
                        PathNode.HorizontalTo(579.6f),
                        PathNode.QuadTo(569.6f, 561.6f, 565.6f, 567.5f),
                        PathNode.QuadTo(561.6f, 573.4f, 561.6f, 586.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldclockLight!!
    }

private var _worldclockLight: ImageVector? = null

val MiuixIcons.Normal.WorldClock: ImageVector
    get() {
        if (_worldclockNormal != null) return _worldclockNormal!!
        _worldclockNormal = ImageVector.Builder(
            name = "WorldClock.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1189.3f,
            viewportHeight = 1189.3f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1189.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1090.2f, 594.8f),
                        PathNode.QuadTo(1090.2f, 728.7f, 1023.7f, 842.9f),
                        PathNode.QuadTo(957.2f, 957.2f, 843.4f, 1023.7f),
                        PathNode.QuadTo(729.7f, 1090.2f, 594.7f, 1090.2f),
                        PathNode.QuadTo(459.6f, 1090.2f, 345.9f, 1023.7f),
                        PathNode.QuadTo(232.2f, 957.2f, 165.7f, 843.0f),
                        PathNode.QuadTo(99.1f, 728.7f, 99.1f, 594.7f),
                        PathNode.QuadTo(99.1f, 459.9f, 165.7f, 346.1f),
                        PathNode.QuadTo(232.2f, 232.3f, 345.9f, 165.7f),
                        PathNode.QuadTo(459.6f, 99.1f, 594.7f, 99.1f),
                        PathNode.QuadTo(729.7f, 99.1f, 843.4f, 165.7f),
                        PathNode.QuadTo(957.2f, 232.3f, 1023.7f, 346.1f),
                        PathNode.QuadTo(1090.2f, 459.9f, 1090.2f, 594.8f),
                        PathNode.Close,
                        PathNode.MoveTo(564.5f, 595.7f),
                        PathNode.VerticalTo(931.7f),
                        PathNode.QuadTo(564.5f, 942.5f, 569.5f, 947.0f),
                        PathNode.QuadTo(574.5f, 951.5f, 586.0f, 951.5f),
                        PathNode.HorizontalTo(603.4f),
                        PathNode.QuadTo(614.8f, 951.5f, 619.8f, 947.0f),
                        PathNode.QuadTo(624.9f, 942.5f, 624.9f, 931.7f),
                        PathNode.VerticalTo(625.9f),
                        PathNode.HorizontalTo(860.7f),
                        PathNode.QuadTo(870.7f, 625.9f, 875.8f, 620.4f),
                        PathNode.QuadTo(880.8f, 614.9f, 880.8f, 606.7f),
                        PathNode.VerticalTo(583.6f),
                        PathNode.QuadTo(880.8f, 575.2f, 875.8f, 569.9f),
                        PathNode.QuadTo(870.7f, 564.5f, 860.7f, 564.5f),
                        PathNode.HorizontalTo(585.9f),
                        PathNode.QuadTo(573.2f, 564.5f, 568.8f, 571.8f),
                        PathNode.QuadTo(564.5f, 579.2f, 564.5f, 595.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldclockNormal!!
    }

private var _worldclockNormal: ImageVector? = null

val MiuixIcons.Regular.WorldClock: ImageVector
    get() {
        if (_worldclockRegular != null) return _worldclockRegular!!
        _worldclockRegular = ImageVector.Builder(
            name = "WorldClock.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1197.6f,
            viewportHeight = 1197.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1197.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1097.8f, 598.8f),
                        PathNode.QuadTo(1097.8f, 733.8f, 1030.8f, 848.8f),
                        PathNode.QuadTo(963.8f, 963.8f, 849.3f, 1030.8f),
                        PathNode.QuadTo(734.8f, 1097.8f, 598.8f, 1097.8f),
                        PathNode.QuadTo(462.8f, 1097.8f, 348.3f, 1030.8f),
                        PathNode.QuadTo(233.8f, 963.8f, 166.8f, 848.8f),
                        PathNode.QuadTo(99.8f, 733.8f, 99.8f, 598.8f),
                        PathNode.QuadTo(99.8f, 462.8f, 166.8f, 348.3f),
                        PathNode.QuadTo(233.8f, 233.8f, 348.3f, 166.8f),
                        PathNode.QuadTo(462.8f, 99.8f, 598.8f, 99.8f),
                        PathNode.QuadTo(734.8f, 99.8f, 849.3f, 166.8f),
                        PathNode.QuadTo(963.8f, 233.8f, 1030.8f, 348.3f),
                        PathNode.QuadTo(1097.8f, 462.8f, 1097.8f, 598.8f),
                        PathNode.Close,
                        PathNode.MoveTo(565.8f, 599.8f),
                        PathNode.VerticalTo(930.8f),
                        PathNode.QuadTo(565.8f, 942.8f, 571.3f, 947.8f),
                        PathNode.QuadTo(576.8f, 952.8f, 589.8f, 952.8f),
                        PathNode.HorizontalTo(607.8f),
                        PathNode.QuadTo(620.8f, 952.8f, 626.3f, 947.8f),
                        PathNode.QuadTo(631.8f, 942.8f, 631.8f, 930.8f),
                        PathNode.VerticalTo(632.8f),
                        PathNode.HorizontalTo(859.8f),
                        PathNode.QuadTo(870.8f, 632.8f, 876.3f, 626.8f),
                        PathNode.QuadTo(881.8f, 620.8f, 881.8f, 611.8f),
                        PathNode.VerticalTo(586.8f),
                        PathNode.QuadTo(881.8f, 577.8f, 876.3f, 571.8f),
                        PathNode.QuadTo(870.8f, 565.8f, 859.8f, 565.8f),
                        PathNode.HorizontalTo(588.8f),
                        PathNode.QuadTo(574.8f, 565.8f, 570.3f, 573.8f),
                        PathNode.QuadTo(565.8f, 581.8f, 565.8f, 599.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldclockRegular!!
    }

private var _worldclockRegular: ImageVector? = null

val MiuixIcons.Medium.WorldClock: ImageVector
    get() {
        if (_worldclockMedium != null) return _worldclockMedium!!
        _worldclockMedium = ImageVector.Builder(
            name = "WorldClock.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1220.2f,
            viewportHeight = 1220.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1220.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1118.5f, 610.1f),
                        PathNode.QuadTo(1118.5f, 748.0f, 1050.3f, 864.8f),
                        PathNode.QuadTo(982.2f, 981.6f, 865.3f, 1050.0f),
                        PathNode.QuadTo(748.4f, 1118.5f, 610.1f, 1118.5f),
                        PathNode.QuadTo(471.7f, 1118.5f, 354.9f, 1050.0f),
                        PathNode.QuadTo(238.0f, 981.6f, 169.9f, 864.8f),
                        PathNode.QuadTo(101.7f, 748.0f, 101.7f, 610.1f),
                        PathNode.QuadTo(101.7f, 471.7f, 169.9f, 355.2f),
                        PathNode.QuadTo(238.0f, 238.6f, 354.9f, 170.2f),
                        PathNode.QuadTo(471.7f, 101.7f, 610.1f, 101.7f),
                        PathNode.QuadTo(748.4f, 101.7f, 865.3f, 170.2f),
                        PathNode.QuadTo(982.2f, 238.6f, 1050.3f, 355.2f),
                        PathNode.QuadTo(1118.5f, 471.7f, 1118.5f, 610.1f),
                        PathNode.Close,
                        PathNode.MoveTo(569.4f, 611.1f),
                        PathNode.VerticalTo(931.5f),
                        PathNode.QuadTo(569.4f, 944.7f, 575.5f, 950.9f),
                        PathNode.QuadTo(581.6f, 957.0f, 595.8f, 957.0f),
                        PathNode.HorizontalTo(624.4f),
                        PathNode.QuadTo(638.6f, 957.0f, 644.7f, 950.9f),
                        PathNode.QuadTo(650.7f, 944.7f, 650.7f, 931.5f),
                        PathNode.VerticalTo(651.7f),
                        PathNode.HorizontalTo(861.1f),
                        PathNode.QuadTo(873.3f, 651.7f, 879.7f, 645.2f),
                        PathNode.QuadTo(886.0f, 638.6f, 886.0f, 627.2f),
                        PathNode.VerticalTo(594.0f),
                        PathNode.QuadTo(886.0f, 582.6f, 879.7f, 576.0f),
                        PathNode.QuadTo(873.3f, 569.4f, 861.1f, 569.4f),
                        PathNode.HorizontalTo(600.1f),
                        PathNode.QuadTo(583.2f, 569.4f, 576.3f, 579.2f),
                        PathNode.QuadTo(569.4f, 589.0f, 569.4f, 611.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldclockMedium!!
    }

private var _worldclockMedium: ImageVector? = null

val MiuixIcons.Demibold.WorldClock: ImageVector
    get() {
        if (_worldclockDemibold != null) return _worldclockDemibold!!
        _worldclockDemibold = ImageVector.Builder(
            name = "WorldClock.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1236.0f,
            viewportHeight = 1236.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1236.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1133.0f, 618.0f),
                        PathNode.QuadTo(1133.0f, 758.0f, 1064.0f, 876.0f),
                        PathNode.QuadTo(995.0f, 994.0f, 876.5f, 1063.5f),
                        PathNode.QuadTo(758.0f, 1133.0f, 618.0f, 1133.0f),
                        PathNode.QuadTo(478.0f, 1133.0f, 359.5f, 1063.5f),
                        PathNode.QuadTo(241.0f, 994.0f, 172.0f, 876.0f),
                        PathNode.QuadTo(103.0f, 758.0f, 103.0f, 618.0f),
                        PathNode.QuadTo(103.0f, 478.0f, 172.0f, 360.0f),
                        PathNode.QuadTo(241.0f, 242.0f, 359.5f, 172.5f),
                        PathNode.QuadTo(478.0f, 103.0f, 618.0f, 103.0f),
                        PathNode.QuadTo(758.0f, 103.0f, 876.5f, 172.5f),
                        PathNode.QuadTo(995.0f, 242.0f, 1064.0f, 360.0f),
                        PathNode.QuadTo(1133.0f, 478.0f, 1133.0f, 618.0f),
                        PathNode.Close,
                        PathNode.MoveTo(572.0f, 619.0f),
                        PathNode.VerticalTo(932.0f),
                        PathNode.QuadTo(572.0f, 946.0f, 578.5f, 953.0f),
                        PathNode.QuadTo(585.0f, 960.0f, 600.0f, 960.0f),
                        PathNode.HorizontalTo(636.0f),
                        PathNode.QuadTo(651.0f, 960.0f, 657.5f, 953.0f),
                        PathNode.QuadTo(664.0f, 946.0f, 664.0f, 932.0f),
                        PathNode.VerticalTo(665.0f),
                        PathNode.HorizontalTo(862.0f),
                        PathNode.QuadTo(875.0f, 665.0f, 882.0f, 658.0f),
                        PathNode.QuadTo(889.0f, 651.0f, 889.0f, 638.0f),
                        PathNode.VerticalTo(599.0f),
                        PathNode.QuadTo(889.0f, 586.0f, 882.0f, 579.0f),
                        PathNode.QuadTo(875.0f, 572.0f, 862.0f, 572.0f),
                        PathNode.HorizontalTo(608.0f),
                        PathNode.QuadTo(589.0f, 572.0f, 580.5f, 583.0f),
                        PathNode.QuadTo(572.0f, 594.0f, 572.0f, 619.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldclockDemibold!!
    }

private var _worldclockDemibold: ImageVector? = null
