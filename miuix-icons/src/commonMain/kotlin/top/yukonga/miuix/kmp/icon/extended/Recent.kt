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

val MiuixIcons.Recent: ImageVector
    get() = MiuixIcons.Regular.Recent

val MiuixIcons.Light.Recent: ImageVector
    get() {
        if (_recentLight != null) return _recentLight!!
        _recentLight = ImageVector.Builder(
            name = "Recent.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1171.2f,
            viewportHeight = 1171.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1171.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1073.6f, 586.6f),
                        PathNode.QuadTo(1073.6f, 718.6f, 1008.1f, 830.8f),
                        PathNode.QuadTo(942.6f, 942.9f, 830.6f, 1008.3f),
                        PathNode.QuadTo(718.6f, 1073.6f, 585.6f, 1073.6f),
                        PathNode.QuadTo(452.6f, 1073.6f, 340.6f, 1008.2f),
                        PathNode.QuadTo(228.6f, 942.8f, 163.1f, 830.5f),
                        PathNode.QuadTo(97.6f, 718.2f, 97.6f, 586.4f),
                        PathNode.QuadTo(97.6f, 453.6f, 163.1f, 341.6f),
                        PathNode.QuadTo(228.6f, 229.6f, 340.6f, 163.6f),
                        PathNode.QuadTo(452.6f, 97.6f, 585.6f, 97.6f),
                        PathNode.QuadTo(718.6f, 97.6f, 830.6f, 163.6f),
                        PathNode.QuadTo(942.6f, 229.6f, 1008.1f, 341.6f),
                        PathNode.QuadTo(1073.6f, 453.6f, 1073.6f, 586.6f),
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
        return _recentLight!!
    }

private var _recentLight: ImageVector? = null

val MiuixIcons.Normal.Recent: ImageVector
    get() {
        if (_recentNormal != null) return _recentNormal!!
        _recentNormal = ImageVector.Builder(
            name = "Recent.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1190.2f,
            viewportHeight = 1190.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1190.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1090.7f, 595.7f),
                        PathNode.QuadTo(1090.7f, 729.8f, 1024.1f, 843.9f),
                        PathNode.QuadTo(957.6f, 958.0f, 843.9f, 1024.5f),
                        PathNode.QuadTo(730.2f, 1091.0f, 595.1f, 1091.0f),
                        PathNode.QuadTo(460.0f, 1091.0f, 346.3f, 1024.5f),
                        PathNode.QuadTo(232.6f, 958.0f, 166.1f, 843.8f),
                        PathNode.QuadTo(99.5f, 729.7f, 99.5f, 595.7f),
                        PathNode.QuadTo(99.5f, 460.7f, 166.1f, 347.0f),
                        PathNode.QuadTo(232.6f, 233.3f, 346.3f, 166.2f),
                        PathNode.QuadTo(460.0f, 99.2f, 595.1f, 99.2f),
                        PathNode.QuadTo(730.1f, 99.2f, 843.9f, 166.2f),
                        PathNode.QuadTo(957.6f, 233.3f, 1024.1f, 347.0f),
                        PathNode.QuadTo(1090.7f, 460.7f, 1090.7f, 595.7f),
                        PathNode.Close,
                        PathNode.MoveTo(564.9f, 595.7f),
                        PathNode.VerticalTo(931.7f),
                        PathNode.QuadTo(564.9f, 942.6f, 569.9f, 947.1f),
                        PathNode.QuadTo(575.0f, 951.6f, 586.4f, 951.6f),
                        PathNode.HorizontalTo(603.8f),
                        PathNode.QuadTo(615.2f, 951.6f, 620.2f, 947.1f),
                        PathNode.QuadTo(625.3f, 942.6f, 625.3f, 931.7f),
                        PathNode.VerticalTo(625.9f),
                        PathNode.HorizontalTo(861.1f),
                        PathNode.QuadTo(871.1f, 625.9f, 876.2f, 620.5f),
                        PathNode.QuadTo(881.2f, 615.0f, 881.2f, 606.8f),
                        PathNode.VerticalTo(583.6f),
                        PathNode.QuadTo(881.2f, 575.3f, 876.2f, 569.9f),
                        PathNode.QuadTo(871.1f, 564.6f, 861.1f, 564.6f),
                        PathNode.HorizontalTo(586.3f),
                        PathNode.QuadTo(573.6f, 564.6f, 569.2f, 571.9f),
                        PathNode.QuadTo(564.9f, 579.2f, 564.9f, 595.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentNormal!!
    }

private var _recentNormal: ImageVector? = null

val MiuixIcons.Regular.Recent: ImageVector
    get() {
        if (_recentRegular != null) return _recentRegular!!
        _recentRegular = ImageVector.Builder(
            name = "Recent.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1198.8f,
            viewportHeight = 1198.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1198.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1098.4f, 599.9f),
                        PathNode.QuadTo(1098.4f, 734.9f, 1031.4f, 849.9f),
                        PathNode.QuadTo(964.4f, 964.9f, 849.9f, 1031.9f),
                        PathNode.QuadTo(735.4f, 1098.9f, 599.4f, 1098.9f),
                        PathNode.QuadTo(463.4f, 1098.9f, 348.9f, 1031.9f),
                        PathNode.QuadTo(234.4f, 964.9f, 167.4f, 849.9f),
                        PathNode.QuadTo(100.4f, 734.9f, 100.4f, 599.9f),
                        PathNode.QuadTo(100.4f, 463.9f, 167.4f, 349.4f),
                        PathNode.QuadTo(234.4f, 234.9f, 348.9f, 167.4f),
                        PathNode.QuadTo(463.4f, 99.9f, 599.4f, 99.9f),
                        PathNode.QuadTo(735.4f, 99.9f, 849.9f, 167.4f),
                        PathNode.QuadTo(964.4f, 234.9f, 1031.4f, 349.4f),
                        PathNode.QuadTo(1098.4f, 463.9f, 1098.4f, 599.9f),
                        PathNode.Close,
                        PathNode.MoveTo(566.4f, 599.9f),
                        PathNode.VerticalTo(930.9f),
                        PathNode.QuadTo(566.4f, 942.9f, 571.9f, 947.9f),
                        PathNode.QuadTo(577.4f, 952.9f, 590.4f, 952.9f),
                        PathNode.HorizontalTo(608.4f),
                        PathNode.QuadTo(621.4f, 952.9f, 626.9f, 947.9f),
                        PathNode.QuadTo(632.4f, 942.9f, 632.4f, 930.9f),
                        PathNode.VerticalTo(632.9f),
                        PathNode.HorizontalTo(860.4f),
                        PathNode.QuadTo(871.4f, 632.9f, 876.9f, 626.9f),
                        PathNode.QuadTo(882.4f, 620.9f, 882.4f, 611.9f),
                        PathNode.VerticalTo(586.9f),
                        PathNode.QuadTo(882.4f, 577.9f, 876.9f, 571.9f),
                        PathNode.QuadTo(871.4f, 565.9f, 860.4f, 565.9f),
                        PathNode.HorizontalTo(589.4f),
                        PathNode.QuadTo(575.4f, 565.9f, 570.9f, 573.9f),
                        PathNode.QuadTo(566.4f, 581.9f, 566.4f, 599.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentRegular!!
    }

private var _recentRegular: ImageVector? = null

val MiuixIcons.Medium.Recent: ImageVector
    get() {
        if (_recentMedium != null) return _recentMedium!!
        _recentMedium = ImageVector.Builder(
            name = "Recent.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1221.4f,
            viewportHeight = 1221.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1221.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1119.1f, 611.2f),
                        PathNode.QuadTo(1119.1f, 748.6f, 1050.9f, 865.6f),
                        PathNode.QuadTo(982.8f, 982.7f, 866.0f, 1051.1f),
                        PathNode.QuadTo(749.3f, 1119.6f, 610.7f, 1119.6f),
                        PathNode.QuadTo(472.1f, 1119.6f, 355.3f, 1051.1f),
                        PathNode.QuadTo(238.6f, 982.7f, 170.5f, 865.6f),
                        PathNode.QuadTo(102.3f, 748.6f, 102.3f, 611.2f),
                        PathNode.QuadTo(102.3f, 472.6f, 170.5f, 356.0f),
                        PathNode.QuadTo(238.8f, 239.3f, 355.5f, 170.6f),
                        PathNode.QuadTo(472.1f, 101.8f, 610.7f, 101.8f),
                        PathNode.QuadTo(749.3f, 101.8f, 865.9f, 170.6f),
                        PathNode.QuadTo(982.6f, 239.3f, 1050.8f, 356.0f),
                        PathNode.QuadTo(1119.1f, 472.6f, 1119.1f, 611.2f),
                        PathNode.Close,
                        PathNode.MoveTo(570.0f, 611.2f),
                        PathNode.VerticalTo(931.6f),
                        PathNode.QuadTo(570.0f, 944.8f, 576.1f, 951.0f),
                        PathNode.QuadTo(582.1f, 957.1f, 596.4f, 957.1f),
                        PathNode.HorizontalTo(625.0f),
                        PathNode.QuadTo(639.3f, 957.1f, 645.3f, 951.0f),
                        PathNode.QuadTo(651.3f, 944.8f, 651.3f, 931.6f),
                        PathNode.VerticalTo(651.8f),
                        PathNode.HorizontalTo(861.7f),
                        PathNode.QuadTo(874.2f, 651.8f, 880.4f, 645.3f),
                        PathNode.QuadTo(886.6f, 638.7f, 886.6f, 627.3f),
                        PathNode.VerticalTo(594.1f),
                        PathNode.QuadTo(886.6f, 582.7f, 880.4f, 576.1f),
                        PathNode.QuadTo(874.2f, 569.5f, 861.7f, 569.5f),
                        PathNode.HorizontalTo(600.7f),
                        PathNode.QuadTo(583.8f, 569.5f, 576.9f, 579.3f),
                        PathNode.QuadTo(570.0f, 589.1f, 570.0f, 611.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentMedium!!
    }

private var _recentMedium: ImageVector? = null

val MiuixIcons.Demibold.Recent: ImageVector
    get() {
        if (_recentDemibold != null) return _recentDemibold!!
        _recentDemibold = ImageVector.Builder(
            name = "Recent.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1237.2f,
            viewportHeight = 1237.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1237.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1133.6f, 619.1f),
                        PathNode.QuadTo(1133.6f, 758.2f, 1064.6f, 876.6f),
                        PathNode.QuadTo(995.6f, 995.1f, 877.3f, 1064.6f),
                        PathNode.QuadTo(759.1f, 1134.1f, 618.6f, 1134.1f),
                        PathNode.QuadTo(478.1f, 1134.1f, 359.9f, 1064.6f),
                        PathNode.QuadTo(241.6f, 995.1f, 172.6f, 876.6f),
                        PathNode.QuadTo(103.6f, 758.2f, 103.6f, 619.1f),
                        PathNode.QuadTo(103.6f, 478.7f, 172.7f, 360.6f),
                        PathNode.QuadTo(241.9f, 242.4f, 360.1f, 172.8f),
                        PathNode.QuadTo(478.2f, 103.1f, 618.6f, 103.1f),
                        PathNode.QuadTo(759.0f, 103.1f, 877.1f, 172.8f),
                        PathNode.QuadTo(995.3f, 242.4f, 1064.5f, 360.6f),
                        PathNode.QuadTo(1133.6f, 478.7f, 1133.6f, 619.1f),
                        PathNode.Close,
                        PathNode.MoveTo(572.6f, 619.1f),
                        PathNode.VerticalTo(932.1f),
                        PathNode.QuadTo(572.6f, 946.1f, 579.0f, 953.1f),
                        PathNode.QuadTo(585.4f, 960.1f, 600.6f, 960.1f),
                        PathNode.HorizontalTo(636.6f),
                        PathNode.QuadTo(651.8f, 960.1f, 658.2f, 953.1f),
                        PathNode.QuadTo(664.6f, 946.1f, 664.6f, 932.1f),
                        PathNode.VerticalTo(665.1f),
                        PathNode.HorizontalTo(862.6f),
                        PathNode.QuadTo(876.1f, 665.1f, 882.9f, 658.1f),
                        PathNode.QuadTo(889.6f, 651.1f, 889.6f, 638.1f),
                        PathNode.VerticalTo(599.1f),
                        PathNode.QuadTo(889.6f, 586.1f, 882.9f, 579.1f),
                        PathNode.QuadTo(876.1f, 572.1f, 862.6f, 572.1f),
                        PathNode.HorizontalTo(608.6f),
                        PathNode.QuadTo(589.6f, 572.1f, 581.1f, 583.2f),
                        PathNode.QuadTo(572.6f, 594.2f, 572.6f, 619.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentDemibold!!
    }

private var _recentDemibold: ImageVector? = null
