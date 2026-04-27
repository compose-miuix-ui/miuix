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

val MiuixIcons.FavoritesFill: ImageVector
    get() = MiuixIcons.Regular.FavoritesFill

val MiuixIcons.Light.FavoritesFill: ImageVector
    get() {
        if (_favoritesfillLight != null) return _favoritesfillLight!!
        _favoritesfillLight = ImageVector.Builder(
            name = "FavoritesFill.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1290.0f,
            viewportHeight = 1290.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1290.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(682.5f, 1058.0f),
                        PathNode.LineTo(674.4f, 1051.3f),
                        PathNode.LineTo(656.5f, 1033.0f),
                        PathNode.QuadTo(644.5f, 1020.0f, 634.5f, 1032.0f),
                        PathNode.QuadTo(620.5f, 1049.0f, 608.5f, 1058.0f),
                        PathNode.QuadTo(567.5f, 1092.0f, 518.0f, 1110.0f),
                        PathNode.QuadTo(468.5f, 1128.0f, 413.7f, 1128.0f),
                        PathNode.QuadTo(329.5f, 1128.0f, 259.5f, 1087.1f),
                        PathNode.QuadTo(189.5f, 1046.2f, 148.5f, 976.1f),
                        PathNode.QuadTo(107.5f, 906.0f, 107.5f, 822.7f),
                        PathNode.QuadTo(107.5f, 735.6f, 152.5f, 663.0f),
                        PathNode.QuadTo(208.5f, 568.0f, 337.0f, 438.0f),
                        PathNode.QuadTo(465.5f, 307.9f, 596.5f, 192.0f),
                        PathNode.QuadTo(609.5f, 181.0f, 618.5f, 174.0f),
                        PathNode.QuadTo(627.5f, 167.0f, 633.5f, 166.0f),
                        PathNode.QuadTo(647.5f, 162.0f, 658.5f, 166.0f),
                        PathNode.QuadTo(664.5f, 167.0f, 674.1f, 174.4f),
                        PathNode.QuadTo(683.6f, 181.9f, 695.5f, 193.0f),
                        PathNode.QuadTo(821.5f, 305.0f, 944.9f, 429.1f),
                        PathNode.QuadTo(1068.3f, 553.2f, 1130.5f, 651.0f),
                        PathNode.QuadTo(1182.5f, 727.3f, 1182.5f, 822.9f),
                        PathNode.QuadTo(1182.5f, 906.0f, 1141.4f, 976.1f),
                        PathNode.QuadTo(1100.2f, 1046.2f, 1030.6f, 1087.1f),
                        PathNode.QuadTo(960.9f, 1128.0f, 877.7f, 1128.0f),
                        PathNode.QuadTo(823.5f, 1128.0f, 773.3f, 1109.5f),
                        PathNode.QuadTo(723.1f, 1091.1f, 682.5f, 1058.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesfillLight!!
    }

private var _favoritesfillLight: ImageVector? = null

val MiuixIcons.Normal.FavoritesFill: ImageVector
    get() {
        if (_favoritesfillNormal != null) return _favoritesfillNormal!!
        _favoritesfillNormal = ImageVector.Builder(
            name = "FavoritesFill.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1301.5f,
            viewportHeight = 1301.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1301.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(681.4f, 1067.6f),
                        PathNode.LineTo(675.4f, 1062.7f),
                        PathNode.LineTo(662.3f, 1049.4f),
                        PathNode.QuadTo(650.3f, 1037.1f, 639.6f, 1049.1f),
                        PathNode.QuadTo(630.4f, 1059.9f, 620.5f, 1067.6f),
                        PathNode.QuadTo(578.8f, 1101.6f, 527.9f, 1120.2f),
                        PathNode.QuadTo(477.0f, 1138.9f, 421.4f, 1138.9f),
                        PathNode.QuadTo(336.0f, 1138.9f, 264.3f, 1097.3f),
                        PathNode.QuadTo(192.5f, 1055.6f, 150.5f, 984.2f),
                        PathNode.QuadTo(108.5f, 912.8f, 108.5f, 827.7f),
                        PathNode.QuadTo(108.5f, 738.6f, 154.8f, 664.3f),
                        PathNode.QuadTo(210.8f, 569.3f, 338.6f, 440.0f),
                        PathNode.QuadTo(466.5f, 310.7f, 596.8f, 195.4f),
                        PathNode.QuadTo(609.8f, 183.7f, 619.5f, 176.3f),
                        PathNode.QuadTo(629.1f, 169.0f, 636.5f, 167.3f),
                        PathNode.QuadTo(652.6f, 162.6f, 667.0f, 167.3f),
                        PathNode.QuadTo(673.7f, 169.0f, 683.9f, 176.8f),
                        PathNode.QuadTo(694.1f, 184.6f, 706.8f, 196.4f),
                        PathNode.QuadTo(831.4f, 307.7f, 954.5f, 431.0f),
                        PathNode.QuadTo(1077.6f, 554.4f, 1139.7f, 651.6f),
                        PathNode.QuadTo(1193.1f, 729.8f, 1193.1f, 827.7f),
                        PathNode.QuadTo(1193.1f, 912.8f, 1151.0f, 984.2f),
                        PathNode.QuadTo(1108.9f, 1055.6f, 1037.7f, 1097.3f),
                        PathNode.QuadTo(966.4f, 1138.9f, 881.3f, 1138.9f),
                        PathNode.QuadTo(825.8f, 1138.9f, 774.4f, 1120.1f),
                        PathNode.QuadTo(723.0f, 1101.3f, 681.4f, 1067.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesfillNormal!!
    }

private var _favoritesfillNormal: ImageVector? = null

val MiuixIcons.Regular.FavoritesFill: ImageVector
    get() {
        if (_favoritesfillRegular != null) return _favoritesfillRegular!!
        _favoritesfillRegular = ImageVector.Builder(
            name = "FavoritesFill.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1306.8f,
            viewportHeight = 1306.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1306.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(680.9f, 1071.9f),
                        PathNode.LineTo(675.9f, 1067.9f),
                        PathNode.LineTo(664.9f, 1056.9f),
                        PathNode.QuadTo(652.9f, 1044.9f, 641.9f, 1056.9f),
                        PathNode.QuadTo(634.9f, 1064.9f, 625.9f, 1071.9f),
                        PathNode.QuadTo(583.9f, 1105.9f, 532.4f, 1124.9f),
                        PathNode.QuadTo(480.9f, 1143.9f, 424.9f, 1143.9f),
                        PathNode.QuadTo(338.9f, 1143.9f, 266.4f, 1101.9f),
                        PathNode.QuadTo(193.9f, 1059.9f, 151.4f, 987.9f),
                        PathNode.QuadTo(108.9f, 915.9f, 108.9f, 829.9f),
                        PathNode.QuadTo(108.9f, 739.9f, 155.9f, 664.9f),
                        PathNode.QuadTo(211.9f, 569.9f, 339.4f, 440.9f),
                        PathNode.QuadTo(466.9f, 311.9f, 596.9f, 196.9f),
                        PathNode.QuadTo(609.9f, 184.9f, 619.9f, 177.4f),
                        PathNode.QuadTo(629.9f, 169.9f, 637.9f, 167.9f),
                        PathNode.QuadTo(654.9f, 162.9f, 670.9f, 167.9f),
                        PathNode.QuadTo(677.9f, 169.9f, 688.4f, 177.9f),
                        PathNode.QuadTo(698.9f, 185.9f, 711.9f, 197.9f),
                        PathNode.QuadTo(835.9f, 308.9f, 958.9f, 431.9f),
                        PathNode.QuadTo(1081.9f, 554.9f, 1143.9f, 651.9f),
                        PathNode.QuadTo(1197.9f, 730.9f, 1197.9f, 829.9f),
                        PathNode.QuadTo(1197.9f, 915.9f, 1155.4f, 987.9f),
                        PathNode.QuadTo(1112.9f, 1059.9f, 1040.9f, 1101.9f),
                        PathNode.QuadTo(968.9f, 1143.9f, 882.9f, 1143.9f),
                        PathNode.QuadTo(826.9f, 1143.9f, 774.9f, 1124.9f),
                        PathNode.QuadTo(722.9f, 1105.9f, 680.9f, 1071.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesfillRegular!!
    }

private var _favoritesfillRegular: ImageVector? = null

val MiuixIcons.Medium.FavoritesFill: ImageVector
    get() {
        if (_favoritesfillMedium != null) return _favoritesfillMedium!!
        _favoritesfillMedium = ImageVector.Builder(
            name = "FavoritesFill.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1318.1f,
            viewportHeight = 1318.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1318.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(684.2f, 1084.0f),
                        PathNode.LineTo(678.6f, 1079.4f),
                        PathNode.LineTo(668.2f, 1069.6f),
                        PathNode.QuadTo(659.1f, 1060.5f, 649.9f, 1069.6f),
                        PathNode.QuadTo(645.8f, 1074.7f, 635.1f, 1083.4f),
                        PathNode.QuadTo(593.1f, 1116.8f, 541.3f, 1135.8f),
                        PathNode.QuadTo(489.5f, 1154.8f, 432.3f, 1154.8f),
                        PathNode.QuadTo(344.5f, 1154.8f, 270.6f, 1112.0f),
                        PathNode.QuadTo(196.6f, 1069.1f, 153.2f, 995.6f),
                        PathNode.QuadTo(109.8f, 922.1f, 109.8f, 834.4f),
                        PathNode.QuadTo(109.8f, 741.4f, 157.4f, 665.8f),
                        PathNode.QuadTo(213.4f, 570.8f, 339.8f, 443.0f),
                        PathNode.QuadTo(466.1f, 315.2f, 596.7f, 199.0f),
                        PathNode.QuadTo(610.3f, 187.0f, 621.1f, 178.9f),
                        PathNode.QuadTo(632.0f, 170.8f, 641.2f, 168.3f),
                        PathNode.QuadTo(661.1f, 163.3f, 678.9f, 168.8f),
                        PathNode.QuadTo(687.1f, 170.8f, 698.5f, 179.4f),
                        PathNode.QuadTo(709.8f, 188.0f, 723.4f, 200.0f),
                        PathNode.QuadTo(848.6f, 312.2f, 970.1f, 434.0f),
                        PathNode.QuadTo(1091.7f, 555.8f, 1153.1f, 652.3f),
                        PathNode.QuadTo(1208.3f, 733.6f, 1208.3f, 834.4f),
                        PathNode.QuadTo(1208.3f, 922.1f, 1164.9f, 995.6f),
                        PathNode.QuadTo(1121.5f, 1069.1f, 1048.0f, 1112.0f),
                        PathNode.QuadTo(974.5f, 1154.8f, 886.8f, 1154.8f),
                        PathNode.QuadTo(829.6f, 1154.8f, 777.3f, 1136.1f),
                        PathNode.QuadTo(725.0f, 1117.4f, 684.2f, 1084.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesfillMedium!!
    }

private var _favoritesfillMedium: ImageVector? = null

val MiuixIcons.Demibold.FavoritesFill: ImageVector
    get() {
        if (_favoritesfillDemibold != null) return _favoritesfillDemibold!!
        _favoritesfillDemibold = ImageVector.Builder(
            name = "FavoritesFill.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1326.0f,
            viewportHeight = 1326.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1326.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(686.5f, 1092.5f),
                        PathNode.LineTo(680.5f, 1087.5f),
                        PathNode.LineTo(670.5f, 1078.5f),
                        PathNode.QuadTo(663.5f, 1071.5f, 655.5f, 1078.5f),
                        PathNode.QuadTo(653.5f, 1081.5f, 641.5f, 1091.5f),
                        PathNode.QuadTo(599.5f, 1124.5f, 547.5f, 1143.5f),
                        PathNode.QuadTo(495.5f, 1162.5f, 437.5f, 1162.5f),
                        PathNode.QuadTo(348.5f, 1162.5f, 273.5f, 1119.0f),
                        PathNode.QuadTo(198.5f, 1075.5f, 154.5f, 1001.0f),
                        PathNode.QuadTo(110.5f, 926.5f, 110.5f, 837.5f),
                        PathNode.QuadTo(110.5f, 742.5f, 158.5f, 666.5f),
                        PathNode.QuadTo(214.5f, 571.5f, 340.0f, 444.5f),
                        PathNode.QuadTo(465.5f, 317.5f, 596.5f, 200.5f),
                        PathNode.QuadTo(610.5f, 188.5f, 622.0f, 180.0f),
                        PathNode.QuadTo(633.5f, 171.5f, 643.5f, 168.5f),
                        PathNode.QuadTo(665.5f, 163.5f, 684.5f, 169.5f),
                        PathNode.QuadTo(693.5f, 171.5f, 705.5f, 180.5f),
                        PathNode.QuadTo(717.5f, 189.5f, 731.5f, 201.5f),
                        PathNode.QuadTo(857.5f, 314.5f, 978.0f, 435.5f),
                        PathNode.QuadTo(1098.5f, 556.5f, 1159.5f, 652.5f),
                        PathNode.QuadTo(1215.5f, 735.5f, 1215.5f, 837.5f),
                        PathNode.QuadTo(1215.5f, 926.5f, 1171.5f, 1001.0f),
                        PathNode.QuadTo(1127.5f, 1075.5f, 1053.0f, 1119.0f),
                        PathNode.QuadTo(978.5f, 1162.5f, 889.5f, 1162.5f),
                        PathNode.QuadTo(831.5f, 1162.5f, 779.0f, 1144.0f),
                        PathNode.QuadTo(726.5f, 1125.5f, 686.5f, 1092.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesfillDemibold!!
    }

private var _favoritesfillDemibold: ImageVector? = null
