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

val MiuixIcons.Stopwatch: ImageVector
    get() = MiuixIcons.Regular.Stopwatch

val MiuixIcons.Light.Stopwatch: ImageVector
    get() {
        if (_stopwatchLight != null) return _stopwatchLight!!
        _stopwatchLight = ImageVector.Builder(
            name = "Stopwatch.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1292.4f,
            viewportHeight = 1292.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1292.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1120.2f, 581.7f),
                        PathNode.QuadTo(1120.2f, 709.7f, 1056.7f, 818.7f),
                        PathNode.QuadTo(993.2f, 927.7f, 884.2f, 991.7f),
                        PathNode.QuadTo(775.2f, 1055.7f, 646.2f, 1055.7f),
                        PathNode.QuadTo(517.2f, 1055.7f, 408.2f, 991.7f),
                        PathNode.QuadTo(299.2f, 927.7f, 235.7f, 818.7f),
                        PathNode.QuadTo(172.2f, 709.7f, 172.2f, 581.7f),
                        PathNode.QuadTo(172.2f, 453.7f, 235.7f, 344.7f),
                        PathNode.QuadTo(299.2f, 235.7f, 408.2f, 171.7f),
                        PathNode.QuadTo(517.2f, 107.7f, 646.2f, 107.7f),
                        PathNode.QuadTo(775.2f, 107.7f, 884.2f, 171.7f),
                        PathNode.QuadTo(993.2f, 235.7f, 1056.7f, 344.7f),
                        PathNode.QuadTo(1120.2f, 453.7f, 1120.2f, 581.7f),
                        PathNode.Close,
                        PathNode.MoveTo(622.2f, 544.7f),
                        PathNode.VerticalTo(897.7f),
                        PathNode.QuadTo(622.2f, 905.7f, 626.2f, 910.2f),
                        PathNode.QuadTo(630.2f, 914.7f, 639.2f, 914.7f),
                        PathNode.HorizontalTo(653.2f),
                        PathNode.QuadTo(663.2f, 914.7f, 666.7f, 910.7f),
                        PathNode.QuadTo(670.2f, 906.7f, 670.2f, 897.7f),
                        PathNode.VerticalTo(544.7f),
                        PathNode.QuadTo(670.2f, 536.7f, 665.7f, 532.7f),
                        PathNode.QuadTo(661.2f, 528.7f, 653.2f, 528.7f),
                        PathNode.HorizontalTo(639.2f),
                        PathNode.QuadTo(622.2f, 528.7f, 622.2f, 544.7f),
                        PathNode.Close,
                        PathNode.MoveTo(815.2f, 1133.7f),
                        PathNode.VerticalTo(1154.7f),
                        PathNode.QuadTo(815.2f, 1170.7f, 807.7f, 1177.7f),
                        PathNode.QuadTo(800.2f, 1184.7f, 786.2f, 1184.7f),
                        PathNode.HorizontalTo(509.2f),
                        PathNode.QuadTo(479.2f, 1184.7f, 479.2f, 1155.7f),
                        PathNode.VerticalTo(1133.7f),
                        PathNode.QuadTo(479.2f, 1117.7f, 486.7f, 1111.2f),
                        PathNode.QuadTo(494.2f, 1104.7f, 509.2f, 1104.7f),
                        PathNode.HorizontalTo(786.2f),
                        PathNode.QuadTo(800.2f, 1104.7f, 807.7f, 1111.2f),
                        PathNode.QuadTo(815.2f, 1117.7f, 815.2f, 1133.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchLight!!
    }

private var _stopwatchLight: ImageVector? = null

val MiuixIcons.Normal.Stopwatch: ImageVector
    get() {
        if (_stopwatchNormal != null) return _stopwatchNormal!!
        _stopwatchNormal = ImageVector.Builder(
            name = "Stopwatch.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1303.1f,
            viewportHeight = 1303.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1303.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1125.6f, 581.9f),
                        PathNode.QuadTo(1125.6f, 710.6f, 1062.1f, 819.6f),
                        PathNode.QuadTo(998.6f, 928.6f, 889.6f, 992.2f),
                        PathNode.QuadTo(780.6f, 1055.9f, 651.6f, 1055.9f),
                        PathNode.QuadTo(522.6f, 1055.9f, 413.6f, 992.2f),
                        PathNode.QuadTo(304.6f, 928.6f, 241.1f, 819.6f),
                        PathNode.QuadTo(177.6f, 710.6f, 177.6f, 581.9f),
                        PathNode.QuadTo(177.6f, 453.9f, 241.1f, 344.9f),
                        PathNode.QuadTo(304.6f, 235.9f, 413.6f, 172.2f),
                        PathNode.QuadTo(522.6f, 108.6f, 651.6f, 108.6f),
                        PathNode.QuadTo(780.6f, 108.6f, 889.6f, 172.2f),
                        PathNode.QuadTo(998.6f, 235.9f, 1062.1f, 344.9f),
                        PathNode.QuadTo(1125.6f, 453.9f, 1125.6f, 581.9f),
                        PathNode.Close,
                        PathNode.MoveTo(621.4f, 549.0f),
                        PathNode.VerticalTo(884.8f),
                        PathNode.QuadTo(621.4f, 896.3f, 626.4f, 901.8f),
                        PathNode.QuadTo(631.4f, 907.3f, 643.9f, 907.3f),
                        PathNode.HorizontalTo(659.2f),
                        PathNode.QuadTo(672.7f, 907.3f, 677.2f, 902.0f),
                        PathNode.QuadTo(681.8f, 896.6f, 681.8f, 884.8f),
                        PathNode.VerticalTo(549.0f),
                        PathNode.QuadTo(681.8f, 539.0f, 675.5f, 533.6f),
                        PathNode.QuadTo(669.3f, 528.2f, 659.2f, 528.2f),
                        PathNode.HorizontalTo(643.9f),
                        PathNode.QuadTo(621.4f, 528.2f, 621.4f, 549.0f),
                        PathNode.Close,
                        PathNode.MoveTo(823.3f, 1137.3f),
                        PathNode.VerticalTo(1160.4f),
                        PathNode.QuadTo(823.3f, 1179.8f, 814.8f, 1187.2f),
                        PathNode.QuadTo(806.2f, 1194.5f, 789.5f, 1194.5f),
                        PathNode.HorizontalTo(514.6f),
                        PathNode.QuadTo(480.4f, 1194.5f, 480.4f, 1160.7f),
                        PathNode.VerticalTo(1137.3f),
                        PathNode.QuadTo(480.4f, 1117.2f, 488.6f, 1110.7f),
                        PathNode.QuadTo(496.8f, 1104.2f, 514.6f, 1104.2f),
                        PathNode.HorizontalTo(789.5f),
                        PathNode.QuadTo(806.2f, 1104.2f, 814.8f, 1111.1f),
                        PathNode.QuadTo(823.3f, 1117.9f, 823.3f, 1137.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchNormal!!
    }

private var _stopwatchNormal: ImageVector? = null

val MiuixIcons.Regular.Stopwatch: ImageVector
    get() {
        if (_stopwatchRegular != null) return _stopwatchRegular!!
        _stopwatchRegular = ImageVector.Builder(
            name = "Stopwatch.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1308.0f,
            viewportHeight = 1308.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1308.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1128.0f, 582.0f),
                        PathNode.QuadTo(1128.0f, 711.0f, 1064.5f, 820.0f),
                        PathNode.QuadTo(1001.0f, 929.0f, 892.0f, 992.5f),
                        PathNode.QuadTo(783.0f, 1056.0f, 654.0f, 1056.0f),
                        PathNode.QuadTo(525.0f, 1056.0f, 416.0f, 992.5f),
                        PathNode.QuadTo(307.0f, 929.0f, 243.5f, 820.0f),
                        PathNode.QuadTo(180.0f, 711.0f, 180.0f, 582.0f),
                        PathNode.QuadTo(180.0f, 454.0f, 243.5f, 345.0f),
                        PathNode.QuadTo(307.0f, 236.0f, 416.0f, 172.5f),
                        PathNode.QuadTo(525.0f, 109.0f, 654.0f, 109.0f),
                        PathNode.QuadTo(783.0f, 109.0f, 892.0f, 172.5f),
                        PathNode.QuadTo(1001.0f, 236.0f, 1064.5f, 345.0f),
                        PathNode.QuadTo(1128.0f, 454.0f, 1128.0f, 582.0f),
                        PathNode.Close,
                        PathNode.MoveTo(621.0f, 551.0f),
                        PathNode.VerticalTo(879.0f),
                        PathNode.QuadTo(621.0f, 892.0f, 626.5f, 898.0f),
                        PathNode.QuadTo(632.0f, 904.0f, 646.0f, 904.0f),
                        PathNode.HorizontalTo(662.0f),
                        PathNode.QuadTo(677.0f, 904.0f, 682.0f, 898.0f),
                        PathNode.QuadTo(687.0f, 892.0f, 687.0f, 879.0f),
                        PathNode.VerticalTo(551.0f),
                        PathNode.QuadTo(687.0f, 540.0f, 680.0f, 534.0f),
                        PathNode.QuadTo(673.0f, 528.0f, 662.0f, 528.0f),
                        PathNode.HorizontalTo(646.0f),
                        PathNode.QuadTo(621.0f, 528.0f, 621.0f, 551.0f),
                        PathNode.Close,
                        PathNode.MoveTo(827.0f, 1139.0f),
                        PathNode.VerticalTo(1163.0f),
                        PathNode.QuadTo(827.0f, 1184.0f, 818.0f, 1191.5f),
                        PathNode.QuadTo(809.0f, 1199.0f, 791.0f, 1199.0f),
                        PathNode.HorizontalTo(517.0f),
                        PathNode.QuadTo(481.0f, 1199.0f, 481.0f, 1163.0f),
                        PathNode.VerticalTo(1139.0f),
                        PathNode.QuadTo(481.0f, 1117.0f, 489.5f, 1110.5f),
                        PathNode.QuadTo(498.0f, 1104.0f, 517.0f, 1104.0f),
                        PathNode.HorizontalTo(791.0f),
                        PathNode.QuadTo(809.0f, 1104.0f, 818.0f, 1111.0f),
                        PathNode.QuadTo(827.0f, 1118.0f, 827.0f, 1139.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchRegular!!
    }

private var _stopwatchRegular: ImageVector? = null

val MiuixIcons.Medium.Stopwatch: ImageVector
    get() {
        if (_stopwatchMedium != null) return _stopwatchMedium!!
        _stopwatchMedium = ImageVector.Builder(
            name = "Stopwatch.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1341.2f,
            viewportHeight = 1341.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1341.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1151.1f, 591.2f),
                        PathNode.QuadTo(1151.1f, 722.0f, 1086.7f, 832.5f),
                        PathNode.QuadTo(1022.3f, 942.9f, 911.8f, 1007.6f),
                        PathNode.QuadTo(801.3f, 1072.3f, 670.6f, 1072.3f),
                        PathNode.QuadTo(539.8f, 1072.3f, 429.4f, 1007.6f),
                        PathNode.QuadTo(318.9f, 942.9f, 254.5f, 832.5f),
                        PathNode.QuadTo(190.1f, 722.0f, 190.1f, 591.2f),
                        PathNode.QuadTo(190.1f, 461.5f, 254.5f, 351.0f),
                        PathNode.QuadTo(318.9f, 240.5f, 429.3f, 176.1f),
                        PathNode.QuadTo(539.8f, 111.8f, 670.6f, 111.8f),
                        PathNode.QuadTo(801.4f, 111.8f, 911.8f, 176.1f),
                        PathNode.QuadTo(1022.3f, 240.5f, 1086.7f, 351.0f),
                        PathNode.QuadTo(1151.1f, 461.5f, 1151.1f, 591.2f),
                        PathNode.Close,
                        PathNode.MoveTo(629.4f, 574.4f),
                        PathNode.VerticalTo(885.9f),
                        PathNode.QuadTo(629.4f, 900.6f, 636.3f, 907.8f),
                        PathNode.QuadTo(643.3f, 915.0f, 658.5f, 915.0f),
                        PathNode.HorizontalTo(682.7f),
                        PathNode.QuadTo(698.9f, 915.0f, 705.4f, 907.8f),
                        PathNode.QuadTo(711.8f, 900.6f, 711.8f, 885.9f),
                        PathNode.VerticalTo(574.4f),
                        PathNode.QuadTo(711.8f, 561.1f, 703.9f, 553.9f),
                        PathNode.QuadTo(696.1f, 546.6f, 682.7f, 546.6f),
                        PathNode.HorizontalTo(658.5f),
                        PathNode.QuadTo(629.4f, 546.6f, 629.4f, 574.4f),
                        PathNode.Close,
                        PathNode.MoveTo(844.8f, 1160.6f),
                        PathNode.VerticalTo(1191.6f),
                        PathNode.QuadTo(844.8f, 1212.6f, 835.5f, 1221.0f),
                        PathNode.QuadTo(826.2f, 1229.4f, 807.6f, 1229.4f),
                        PathNode.HorizontalTo(533.6f),
                        PathNode.QuadTo(496.4f, 1229.4f, 496.4f, 1192.2f),
                        PathNode.VerticalTo(1160.6f),
                        PathNode.QuadTo(496.4f, 1139.8f, 505.2f, 1132.1f),
                        PathNode.QuadTo(514.0f, 1124.4f, 533.6f, 1124.4f),
                        PathNode.HorizontalTo(807.6f),
                        PathNode.QuadTo(826.2f, 1124.4f, 835.5f, 1132.3f),
                        PathNode.QuadTo(844.8f, 1140.2f, 844.8f, 1160.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchMedium!!
    }

private var _stopwatchMedium: ImageVector? = null

val MiuixIcons.Demibold.Stopwatch: ImageVector
    get() {
        if (_stopwatchDemibold != null) return _stopwatchDemibold!!
        _stopwatchDemibold = ImageVector.Builder(
            name = "Stopwatch.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1364.4f,
            viewportHeight = 1364.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1364.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1167.2f, 597.7f),
                        PathNode.QuadTo(1167.2f, 729.7f, 1102.2f, 841.2f),
                        PathNode.QuadTo(1037.2f, 952.7f, 925.7f, 1018.2f),
                        PathNode.QuadTo(814.2f, 1083.7f, 682.2f, 1083.7f),
                        PathNode.QuadTo(550.2f, 1083.7f, 438.7f, 1018.2f),
                        PathNode.QuadTo(327.2f, 952.7f, 262.2f, 841.2f),
                        PathNode.QuadTo(197.2f, 729.7f, 197.2f, 597.7f),
                        PathNode.QuadTo(197.2f, 466.7f, 262.2f, 355.2f),
                        PathNode.QuadTo(327.1f, 243.6f, 438.7f, 178.7f),
                        PathNode.QuadTo(550.2f, 113.7f, 682.2f, 113.7f),
                        PathNode.QuadTo(814.2f, 113.7f, 925.7f, 178.7f),
                        PathNode.QuadTo(1037.3f, 243.7f, 1102.2f, 355.2f),
                        PathNode.QuadTo(1167.2f, 466.7f, 1167.2f, 597.7f),
                        PathNode.Close,
                        PathNode.MoveTo(635.2f, 590.7f),
                        PathNode.VerticalTo(890.7f),
                        PathNode.QuadTo(635.2f, 906.7f, 643.2f, 914.7f),
                        PathNode.QuadTo(651.2f, 922.7f, 667.2f, 922.7f),
                        PathNode.HorizontalTo(697.2f),
                        PathNode.QuadTo(714.2f, 922.7f, 721.7f, 914.7f),
                        PathNode.QuadTo(729.2f, 906.7f, 729.2f, 890.7f),
                        PathNode.VerticalTo(590.7f),
                        PathNode.QuadTo(729.2f, 575.9f, 720.7f, 567.8f),
                        PathNode.QuadTo(712.2f, 559.7f, 697.2f, 559.7f),
                        PathNode.HorizontalTo(667.2f),
                        PathNode.QuadTo(635.2f, 559.7f, 635.2f, 590.7f),
                        PathNode.Close,
                        PathNode.MoveTo(857.2f, 1175.7f),
                        PathNode.VerticalTo(1211.7f),
                        PathNode.QuadTo(857.2f, 1232.7f, 847.7f, 1241.7f),
                        PathNode.QuadTo(838.2f, 1250.7f, 819.2f, 1250.7f),
                        PathNode.HorizontalTo(545.2f),
                        PathNode.QuadTo(507.2f, 1250.7f, 507.2f, 1212.7f),
                        PathNode.VerticalTo(1175.7f),
                        PathNode.QuadTo(507.2f, 1155.7f, 516.2f, 1147.2f),
                        PathNode.QuadTo(525.1f, 1138.7f, 545.2f, 1138.7f),
                        PathNode.HorizontalTo(819.2f),
                        PathNode.QuadTo(838.2f, 1138.7f, 847.7f, 1147.2f),
                        PathNode.QuadTo(857.2f, 1155.7f, 857.2f, 1175.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchDemibold!!
    }

private var _stopwatchDemibold: ImageVector? = null
