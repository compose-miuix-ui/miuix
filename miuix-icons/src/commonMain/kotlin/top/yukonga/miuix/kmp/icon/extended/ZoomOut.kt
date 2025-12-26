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

val MiuixIcons.ZoomOut: ImageVector
    get() = MiuixIcons.Regular.ZoomOut

val MiuixIcons.Light.ZoomOut: ImageVector
    get() {
        if (_zoomoutLight != null) return _zoomoutLight!!
        _zoomoutLight = ImageVector.Builder(
            name = "ZoomOut.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1117.2f,
            viewportHeight = 1117.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -105.89999999999998f, translationY = 909.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(591.0f, 467.0f),
                        PathNode.VerticalTo(796.0f),
                        PathNode.QuadTo(591.0f, 804.4444444444445f, 586.75f, 809.7222222222222f),
                        PathNode.QuadTo(582.5f, 815.0f, 574.0f, 815.0f),
                        PathNode.HorizontalTo(548.16f),
                        PathNode.QuadTo(540.0f, 815.0f, 535.5f, 809.7222222222222f),
                        PathNode.QuadTo(531.0f, 804.4444444444445f, 531.0f, 796.0f),
                        PathNode.VerticalTo(524.0f),
                        PathNode.LineTo(259.0f, 796.0f),
                        PathNode.QuadTo(253.66666666666666f, 802.0f, 246.33333333333331f, 803.0f),
                        PathNode.QuadTo(239.0f, 804.0f, 233.0f, 797.0f),
                        PathNode.LineTo(218.09677419354838f, 782.0967741935484f),
                        PathNode.QuadTo(211.0f, 775.0f, 211.5f, 767.5f),
                        PathNode.QuadTo(212.0f, 760.0f, 218.0f, 755.0f),
                        PathNode.LineTo(490.0f, 483.0f),
                        PathNode.HorizontalTo(217.0f),
                        PathNode.QuadTo(208.69230769230768f, 483.0f, 203.84615384615384f, 478.1095890410959f),
                        PathNode.QuadTo(199.0f, 473.2191780821918f, 199.0f, 466.93150684931504f),
                        PathNode.VerticalTo(438.986301369863f),
                        PathNode.QuadTo(199.0f, 432.0f, 203.84615384615384f, 427.5f),
                        PathNode.QuadTo(208.69230769230768f, 423.0f, 217.0f, 423.0f),
                        PathNode.HorizontalTo(545.0f),
                        PathNode.QuadTo(569.0f, 423.0f, 580.0f, 433.5f),
                        PathNode.QuadTo(591.0f, 444.0f, 591.0f, 467.0f),
                        PathNode.Close,
                        PathNode.MoveTo(738.0f, 234.0f),
                        PathNode.VerticalTo(-95.0f),
                        PathNode.QuadTo(738.0f, -103.44444444444444f, 742.25f, -108.72222222222223f),
                        PathNode.QuadTo(746.5f, -114.0f, 755.0f, -114.0f),
                        PathNode.HorizontalTo(780.84f),
                        PathNode.QuadTo(789.0f, -114.0f, 793.5f, -108.72222222222223f),
                        PathNode.QuadTo(798.0f, -103.44444444444444f, 798.0f, -95.0f),
                        PathNode.VerticalTo(177.0f),
                        PathNode.LineTo(1070.0f, -96.0f),
                        PathNode.QuadTo(1076.0f, -102.0f, 1083.0f, -103.0f),
                        PathNode.QuadTo(1090.0f, -104.0f, 1098.0f, -96.0f),
                        PathNode.LineTo(1112.225806451613f, -81.7741935483871f),
                        PathNode.QuadTo(1119.0f, -75.0f, 1118.5f, -67.5f),
                        PathNode.QuadTo(1118.0f, -60.0f, 1112.0f, -55.0f),
                        PathNode.LineTo(839.0f, 218.0f),
                        PathNode.HorizontalTo(1112.0f),
                        PathNode.QuadTo(1120.3076923076924f, 218.0f, 1125.1538461538462f, 222.8904109589041f),
                        PathNode.QuadTo(1130.0f, 227.78082191780823f, 1130.0f, 234.06849315068493f),
                        PathNode.VerticalTo(262.013698630137f),
                        PathNode.QuadTo(1130.0f, 269.0f, 1125.1538461538462f, 273.5f),
                        PathNode.QuadTo(1120.3076923076924f, 278.0f, 1112.0f, 278.0f),
                        PathNode.HorizontalTo(784.0f),
                        PathNode.QuadTo(760.0f, 278.0f, 749.0f, 267.5f),
                        PathNode.QuadTo(738.0f, 257.0f, 738.0f, 234.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomoutLight!!
    }

private var _zoomoutLight: ImageVector? = null

val MiuixIcons.Regular.ZoomOut: ImageVector
    get() {
        if (_zoomoutRegular != null) return _zoomoutRegular!!
        _zoomoutRegular = ImageVector.Builder(
            name = "ZoomOut.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1126.8f,
            viewportHeight = 1126.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -101.10000000000002f, translationY = 913.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(604.0f, 470.0f),
                        PathNode.VerticalTo(792.0f),
                        PathNode.QuadTo(604.0f, 804.0f, 598.0f, 811.5f),
                        PathNode.QuadTo(592.0f, 819.0f, 580.0f, 819.0f),
                        PathNode.HorizontalTo(542.0f),
                        PathNode.QuadTo(530.0f, 819.0f, 524.0f, 811.5f),
                        PathNode.QuadTo(518.0f, 804.0f, 518.0f, 792.0f),
                        PathNode.VerticalTo(556.0f),
                        PathNode.LineTo(271.0f, 803.0f),
                        PathNode.QuadTo(263.0f, 812.0f, 252.0f, 813.5f),
                        PathNode.QuadTo(241.0f, 815.0f, 231.0f, 805.0f),
                        PathNode.LineTo(210.0f, 784.0f),
                        PathNode.QuadTo(200.0f, 774.0f, 201.0f, 763.0f),
                        PathNode.QuadTo(202.0f, 752.0f, 210.0f, 744.0f),
                        PathNode.LineTo(458.0f, 496.0f),
                        PathNode.HorizontalTo(221.0f),
                        PathNode.QuadTo(209.0f, 496.0f, 202.0f, 489.0f),
                        PathNode.QuadTo(195.0f, 482.0f, 195.0f, 473.0f),
                        PathNode.VerticalTo(433.0f),
                        PathNode.QuadTo(195.0f, 423.0f, 202.0f, 416.5f),
                        PathNode.QuadTo(209.0f, 410.0f, 221.0f, 410.0f),
                        PathNode.HorizontalTo(544.0f),
                        PathNode.QuadTo(573.0f, 410.0f, 588.5f, 425.5f),
                        PathNode.QuadTo(604.0f, 441.0f, 604.0f, 470.0f),
                        PathNode.Close,
                        PathNode.MoveTo(725.0f, 231.0f),
                        PathNode.VerticalTo(-91.0f),
                        PathNode.QuadTo(725.0f, -103.0f, 731.0f, -110.5f),
                        PathNode.QuadTo(737.0f, -118.0f, 749.0f, -118.0f),
                        PathNode.HorizontalTo(787.0f),
                        PathNode.QuadTo(799.0f, -118.0f, 805.0f, -110.5f),
                        PathNode.QuadTo(811.0f, -103.0f, 811.0f, -91.0f),
                        PathNode.VerticalTo(145.0f),
                        PathNode.LineTo(1058.0f, -102.0f),
                        PathNode.QuadTo(1066.0f, -111.0f, 1077.0f, -112.5f),
                        PathNode.QuadTo(1088.0f, -114.0f, 1098.0f, -104.0f),
                        PathNode.LineTo(1119.0f, -83.0f),
                        PathNode.QuadTo(1129.0f, -73.0f, 1128.0f, -62.0f),
                        PathNode.QuadTo(1127.0f, -51.0f, 1119.0f, -43.0f),
                        PathNode.LineTo(871.0f, 205.0f),
                        PathNode.HorizontalTo(1108.0f),
                        PathNode.QuadTo(1120.0f, 205.0f, 1127.0f, 212.0f),
                        PathNode.QuadTo(1134.0f, 219.0f, 1134.0f, 228.0f),
                        PathNode.VerticalTo(268.0f),
                        PathNode.QuadTo(1134.0f, 278.0f, 1127.0f, 284.5f),
                        PathNode.QuadTo(1120.0f, 291.0f, 1108.0f, 291.0f),
                        PathNode.HorizontalTo(785.0f),
                        PathNode.QuadTo(756.0f, 291.0f, 740.5f, 275.5f),
                        PathNode.QuadTo(725.0f, 260.0f, 725.0f, 231.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomoutRegular!!
    }

private var _zoomoutRegular: ImageVector? = null

val MiuixIcons.Heavy.ZoomOut: ImageVector
    get() {
        if (_zoomoutHeavy != null) return _zoomoutHeavy!!
        _zoomoutHeavy = ImageVector.Builder(
            name = "ZoomOut.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1160.3999999999999f,
            viewportHeight = 1160.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -84.30000000000007f, translationY = 930.6999999999999f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(618.0f, 467.0f),
                        PathNode.VerticalTo(792.0f),
                        PathNode.QuadTo(618.0f, 810.0f, 608.0f, 821.5f),
                        PathNode.QuadTo(598.0f, 833.0f, 580.0f, 833.0f),
                        PathNode.HorizontalTo(542.0f),
                        PathNode.QuadTo(524.0f, 833.0f, 514.0f, 821.5f),
                        PathNode.QuadTo(504.0f, 810.0f, 504.0f, 792.0f),
                        PathNode.VerticalTo(589.0f),
                        PathNode.LineTo(281.0f, 813.0f),
                        PathNode.QuadTo(269.0f, 825.0f, 252.5f, 827.0f),
                        PathNode.QuadTo(236.0f, 829.0f, 221.0f, 815.0f),
                        PathNode.LineTo(200.0f, 794.0f),
                        PathNode.QuadTo(186.0f, 779.0f, 187.0f, 762.0f),
                        PathNode.QuadTo(188.0f, 745.0f, 200.0f, 734.0f),
                        PathNode.LineTo(425.0f, 510.0f),
                        PathNode.HorizontalTo(221.0f),
                        PathNode.QuadTo(204.0f, 510.0f, 192.5f, 498.5f),
                        PathNode.QuadTo(181.0f, 487.0f, 181.0f, 473.0f),
                        PathNode.VerticalTo(433.0f),
                        PathNode.QuadTo(181.0f, 418.0f, 192.0f, 407.0f),
                        PathNode.QuadTo(203.0f, 396.0f, 221.0f, 396.0f),
                        PathNode.HorizontalTo(545.0f),
                        PathNode.QuadTo(580.0f, 396.0f, 599.0f, 414.5f),
                        PathNode.QuadTo(618.0f, 433.0f, 618.0f, 467.0f),
                        PathNode.Close,
                        PathNode.MoveTo(711.0f, 234.0f),
                        PathNode.VerticalTo(-91.0f),
                        PathNode.QuadTo(711.0f, -109.0f, 721.0f, -120.5f),
                        PathNode.QuadTo(731.0f, -132.0f, 749.0f, -132.0f),
                        PathNode.HorizontalTo(787.0f),
                        PathNode.QuadTo(805.0f, -132.0f, 815.0f, -120.5f),
                        PathNode.QuadTo(825.0f, -109.0f, 825.0f, -91.0f),
                        PathNode.VerticalTo(112.0f),
                        PathNode.LineTo(1048.0f, -112.0f),
                        PathNode.QuadTo(1060.0f, -124.0f, 1076.5f, -126.0f),
                        PathNode.QuadTo(1093.0f, -128.0f, 1108.0f, -114.0f),
                        PathNode.LineTo(1129.0f, -93.0f),
                        PathNode.QuadTo(1143.0f, -78.0f, 1142.0f, -61.0f),
                        PathNode.QuadTo(1141.0f, -44.0f, 1129.0f, -33.0f),
                        PathNode.LineTo(904.0f, 191.0f),
                        PathNode.HorizontalTo(1108.0f),
                        PathNode.QuadTo(1125.0f, 191.0f, 1136.5f, 202.5f),
                        PathNode.QuadTo(1148.0f, 214.0f, 1148.0f, 228.0f),
                        PathNode.VerticalTo(268.0f),
                        PathNode.QuadTo(1148.0f, 283.0f, 1137.0f, 294.0f),
                        PathNode.QuadTo(1126.0f, 305.0f, 1108.0f, 305.0f),
                        PathNode.HorizontalTo(784.0f),
                        PathNode.QuadTo(749.0f, 305.0f, 730.0f, 286.5f),
                        PathNode.QuadTo(711.0f, 268.0f, 711.0f, 234.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomoutHeavy!!
    }

private var _zoomoutHeavy: ImageVector? = null
