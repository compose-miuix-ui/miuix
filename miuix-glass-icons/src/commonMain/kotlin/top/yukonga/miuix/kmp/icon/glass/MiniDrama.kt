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

val MiuixIcons.Glass.MiniDrama: ImageVector
    get() = MiuixIcons.Glass.Regular.MiniDrama

val MiuixIcons.Glass.Light.MiniDrama: ImageVector
    get() {
        if (_miniDramaLight != null) return _miniDramaLight!!
        _miniDramaLight = ImageVector.Builder(
            name = "MiniDrama.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(890.2f, 103.4f),
                        PathNode.QuadTo(911.4f, 113.5f, 928.9f, 130.8f),
                        PathNode.QuadTo(946.3f, 148.0f, 957.4f, 169.7f),
                        PathNode.QuadTo(968.5f, 192.4f, 971.3f, 225.4f),
                        PathNode.QuadTo(974.1f, 258.3f, 974.1f, 338.4f),
                        PathNode.LineTo(974.1f, 804.4f),
                        PathNode.QuadTo(974.1f, 885.0f, 971.3f, 917.7f),
                        PathNode.QuadTo(968.5f, 950.4f, 957.4f, 973.2f),
                        PathNode.QuadTo(935.7f, 1016.7f, 890.2f, 1039.4f),
                        PathNode.QuadTo(867.4f, 1051.5f, 834.7f, 1054.3f),
                        PathNode.QuadTo(802.0f, 1057.1f, 721.4f, 1057.1f),
                        PathNode.LineTo(421.4f, 1057.1f),
                        PathNode.QuadTo(341.3f, 1057.1f, 308.4f, 1054.3f),
                        PathNode.QuadTo(275.4f, 1051.5f, 252.7f, 1039.4f),
                        PathNode.QuadTo(207.2f, 1016.7f, 185.4f, 973.2f),
                        PathNode.QuadTo(174.3f, 950.4f, 171.5f, 917.7f),
                        PathNode.QuadTo(168.8f, 885.0f, 168.8f, 804.4f),
                        PathNode.LineTo(168.8f, 338.4f),
                        PathNode.QuadTo(168.8f, 258.3f, 171.5f, 225.4f),
                        PathNode.QuadTo(174.3f, 192.4f, 185.4f, 169.7f),
                        PathNode.QuadTo(196.5f, 148.0f, 214.0f, 130.8f),
                        PathNode.QuadTo(231.4f, 113.5f, 252.7f, 103.4f),
                        PathNode.QuadTo(275.4f, 91.3f, 308.4f, 88.5f),
                        PathNode.QuadTo(341.3f, 85.8f, 421.4f, 85.8f),
                        PathNode.LineTo(721.4f, 85.8f),
                        PathNode.QuadTo(802.0f, 85.8f, 834.7f, 88.5f),
                        PathNode.QuadTo(867.4f, 91.3f, 890.2f, 103.4f),
                        PathNode.Close,
                        PathNode.MoveTo(447.5f, 416.8f),
                        PathNode.QuadTo(443.3f, 421.5f, 442.3f, 434.8f),
                        PathNode.QuadTo(441.3f, 448.0f, 441.3f, 476.4f),
                        PathNode.LineTo(441.3f, 666.4f),
                        PathNode.QuadTo(441.3f, 694.4f, 442.6f, 707.9f),
                        PathNode.QuadTo(443.9f, 721.3f, 447.5f, 726.1f),
                        PathNode.QuadTo(450.7f, 731.8f, 456.9f, 734.9f),
                        PathNode.QuadTo(463.1f, 738.0f, 469.8f, 738.5f),
                        PathNode.QuadTo(476.1f, 739.5f, 487.9f, 734.0f),
                        PathNode.QuadTo(499.8f, 728.5f, 524.0f, 713.9f),
                        PathNode.LineTo(689.0f, 618.9f),
                        PathNode.QuadTo(696.0f, 614.9f, 702.0f, 610.9f),
                        PathNode.QuadTo(718.3f, 601.2f, 726.8f, 595.2f),
                        PathNode.QuadTo(735.3f, 589.1f, 737.4f, 583.9f),
                        PathNode.QuadTo(744.1f, 571.4f, 737.4f, 559.0f),
                        PathNode.QuadTo(735.3f, 553.8f, 726.8f, 547.7f),
                        PathNode.QuadTo(718.3f, 541.7f, 702.0f, 532.0f),
                        PathNode.QuadTo(696.0f, 528.0f, 689.0f, 524.0f),
                        PathNode.LineTo(524.0f, 428.0f),
                        PathNode.QuadTo(500.1f, 414.2f, 488.3f, 408.8f),
                        PathNode.QuadTo(476.5f, 403.3f, 469.8f, 404.3f),
                        PathNode.QuadTo(463.1f, 404.9f, 456.9f, 408.0f),
                        PathNode.QuadTo(450.7f, 411.1f, 447.5f, 416.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _miniDramaLight!!
    }

private var _miniDramaLight: ImageVector? = null

val MiuixIcons.Glass.Normal.MiniDrama: ImageVector
    get() {
        if (_miniDramaNormal != null) return _miniDramaNormal!!
        _miniDramaNormal = ImageVector.Builder(
            name = "MiniDrama.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(894.5f, 95.2f),
                        PathNode.QuadTo(916.9f, 105.9f, 935.4f, 124.3f),
                        PathNode.QuadTo(954.0f, 142.8f, 965.7f, 165.4f),
                        PathNode.QuadTo(977.4f, 189.7f, 980.3f, 223.7f),
                        PathNode.QuadTo(983.3f, 257.7f, 983.3f, 338.4f),
                        PathNode.LineTo(983.3f, 804.4f),
                        PathNode.QuadTo(983.3f, 885.3f, 980.3f, 919.2f),
                        PathNode.QuadTo(977.4f, 953.2f, 965.7f, 977.5f),
                        PathNode.QuadTo(942.4f, 1023.4f, 894.5f, 1047.7f),
                        PathNode.QuadTo(870.2f, 1060.4f, 836.2f, 1063.3f),
                        PathNode.QuadTo(802.3f, 1066.3f, 721.4f, 1066.3f),
                        PathNode.LineTo(421.4f, 1066.3f),
                        PathNode.QuadTo(340.7f, 1066.3f, 306.7f, 1063.3f),
                        PathNode.QuadTo(272.7f, 1060.4f, 248.4f, 1047.7f),
                        PathNode.QuadTo(200.5f, 1023.4f, 177.2f, 977.5f),
                        PathNode.QuadTo(165.5f, 953.2f, 162.5f, 919.2f),
                        PathNode.QuadTo(159.6f, 885.3f, 159.6f, 804.4f),
                        PathNode.LineTo(159.6f, 338.4f),
                        PathNode.QuadTo(159.6f, 257.7f, 162.5f, 223.7f),
                        PathNode.QuadTo(165.5f, 189.7f, 177.2f, 165.4f),
                        PathNode.QuadTo(188.9f, 142.8f, 207.4f, 124.3f),
                        PathNode.QuadTo(225.9f, 105.9f, 248.4f, 95.2f),
                        PathNode.QuadTo(272.7f, 82.5f, 306.7f, 79.5f),
                        PathNode.QuadTo(340.7f, 76.6f, 421.4f, 76.6f),
                        PathNode.LineTo(721.4f, 76.6f),
                        PathNode.QuadTo(802.3f, 76.6f, 836.2f, 79.5f),
                        PathNode.QuadTo(870.2f, 82.5f, 894.5f, 95.2f),
                        PathNode.Close,
                        PathNode.MoveTo(442.7f, 413.1f),
                        PathNode.QuadTo(437.2f, 419.4f, 436.2f, 432.5f),
                        PathNode.QuadTo(435.2f, 445.5f, 435.2f, 476.4f),
                        PathNode.LineTo(435.2f, 666.4f),
                        PathNode.QuadTo(435.2f, 697.2f, 436.6f, 710.3f),
                        PathNode.QuadTo(438.1f, 723.5f, 442.7f, 729.8f),
                        PathNode.QuadTo(446.4f, 736.3f, 453.8f, 740.1f),
                        PathNode.QuadTo(461.3f, 743.8f, 468.8f, 744.7f),
                        PathNode.QuadTo(477.0f, 745.7f, 489.3f, 740.2f),
                        PathNode.QuadTo(501.6f, 734.7f, 527.0f, 719.1f),
                        PathNode.LineTo(692.0f, 624.1f),
                        PathNode.QuadTo(699.0f, 620.1f, 705.0f, 616.1f),
                        PathNode.QuadTo(723.2f, 605.5f, 731.7f, 599.1f),
                        PathNode.QuadTo(740.2f, 592.8f, 742.9f, 586.3f),
                        PathNode.QuadTo(750.5f, 571.4f, 742.9f, 556.5f),
                        PathNode.QuadTo(740.2f, 550.1f, 731.7f, 543.7f),
                        PathNode.QuadTo(723.2f, 537.4f, 705.0f, 526.8f),
                        PathNode.QuadTo(699.0f, 522.8f, 692.0f, 518.8f),
                        PathNode.LineTo(527.0f, 422.8f),
                        PathNode.QuadTo(501.0f, 407.5f, 489.1f, 402.3f),
                        PathNode.QuadTo(477.2f, 397.2f, 468.8f, 398.2f),
                        PathNode.QuadTo(461.3f, 399.1f, 453.8f, 402.8f),
                        PathNode.QuadTo(446.4f, 406.5f, 442.7f, 413.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _miniDramaNormal!!
    }

private var _miniDramaNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.MiniDrama: ImageVector
    get() {
        if (_miniDramaRegular != null) return _miniDramaRegular!!
        _miniDramaRegular = ImageVector.Builder(
            name = "MiniDrama.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(896.4f, 91.4f),
                        PathNode.QuadTo(919.4f, 102.4f, 938.4f, 121.4f),
                        PathNode.QuadTo(957.4f, 140.4f, 969.4f, 163.4f),
                        PathNode.QuadTo(981.4f, 188.4f, 984.4f, 222.9f),
                        PathNode.QuadTo(987.4f, 257.4f, 987.4f, 338.4f),
                        PathNode.LineTo(987.4f, 804.4f),
                        PathNode.QuadTo(987.4f, 885.4f, 984.4f, 919.9f),
                        PathNode.QuadTo(981.4f, 954.4f, 969.4f, 979.4f),
                        PathNode.QuadTo(945.4f, 1026.4f, 896.4f, 1051.4f),
                        PathNode.QuadTo(871.4f, 1064.4f, 836.9f, 1067.4f),
                        PathNode.QuadTo(802.4f, 1070.4f, 721.4f, 1070.4f),
                        PathNode.LineTo(421.4f, 1070.4f),
                        PathNode.QuadTo(340.4f, 1070.4f, 305.9f, 1067.4f),
                        PathNode.QuadTo(271.4f, 1064.4f, 246.4f, 1051.4f),
                        PathNode.QuadTo(197.4f, 1026.4f, 173.4f, 979.4f),
                        PathNode.QuadTo(161.4f, 954.4f, 158.4f, 919.9f),
                        PathNode.QuadTo(155.4f, 885.4f, 155.4f, 804.4f),
                        PathNode.LineTo(155.4f, 338.4f),
                        PathNode.QuadTo(155.4f, 257.4f, 158.4f, 222.9f),
                        PathNode.QuadTo(161.4f, 188.4f, 173.4f, 163.4f),
                        PathNode.QuadTo(185.4f, 140.4f, 204.4f, 121.4f),
                        PathNode.QuadTo(223.4f, 102.4f, 246.4f, 91.4f),
                        PathNode.QuadTo(271.4f, 78.4f, 305.9f, 75.4f),
                        PathNode.QuadTo(340.4f, 72.4f, 421.4f, 72.4f),
                        PathNode.LineTo(721.4f, 72.4f),
                        PathNode.QuadTo(802.4f, 72.4f, 836.9f, 75.4f),
                        PathNode.QuadTo(871.4f, 78.4f, 896.4f, 91.4f),
                        PathNode.Close,
                        PathNode.MoveTo(440.4f, 411.4f),
                        PathNode.QuadTo(434.4f, 418.4f, 433.4f, 431.4f),
                        PathNode.QuadTo(432.4f, 444.4f, 432.4f, 476.4f),
                        PathNode.LineTo(432.4f, 666.4f),
                        PathNode.QuadTo(432.4f, 698.4f, 433.9f, 711.4f),
                        PathNode.QuadTo(435.4f, 724.4f, 440.4f, 731.4f),
                        PathNode.QuadTo(444.4f, 738.4f, 452.4f, 742.4f),
                        PathNode.QuadTo(460.4f, 746.4f, 468.4f, 747.4f),
                        PathNode.QuadTo(477.4f, 748.4f, 489.9f, 742.9f),
                        PathNode.QuadTo(502.4f, 737.4f, 528.4f, 721.4f),
                        PathNode.LineTo(693.4f, 626.4f),
                        PathNode.QuadTo(700.4f, 622.4f, 706.4f, 618.4f),
                        PathNode.QuadTo(725.4f, 607.4f, 733.9f, 600.9f),
                        PathNode.QuadTo(742.4f, 594.4f, 745.4f, 587.4f),
                        PathNode.QuadTo(753.4f, 571.4f, 745.4f, 555.4f),
                        PathNode.QuadTo(742.4f, 548.4f, 733.9f, 541.9f),
                        PathNode.QuadTo(725.4f, 535.4f, 706.4f, 524.4f),
                        PathNode.QuadTo(700.4f, 520.4f, 693.4f, 516.4f),
                        PathNode.LineTo(528.4f, 420.4f),
                        PathNode.QuadTo(501.4f, 404.4f, 489.4f, 399.4f),
                        PathNode.QuadTo(477.4f, 394.4f, 468.4f, 395.4f),
                        PathNode.QuadTo(460.4f, 396.4f, 452.4f, 400.4f),
                        PathNode.QuadTo(444.4f, 404.4f, 440.4f, 411.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _miniDramaRegular!!
    }

private var _miniDramaRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.MiniDrama: ImageVector
    get() {
        if (_miniDramaMedium != null) return _miniDramaMedium!!
        _miniDramaMedium = ImageVector.Builder(
            name = "MiniDrama.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(900.2f, 84.1f),
                        PathNode.QuadTo(924.3f, 95.7f, 944.2f, 115.6f),
                        PathNode.QuadTo(964.2f, 135.6f, 976.7f, 159.6f),
                        PathNode.QuadTo(989.3f, 185.7f, 992.4f, 221.3f),
                        PathNode.QuadTo(995.5f, 256.9f, 995.5f, 338.4f),
                        PathNode.LineTo(995.5f, 804.4f),
                        PathNode.QuadTo(995.5f, 886.0f, 992.4f, 921.6f),
                        PathNode.QuadTo(989.3f, 957.1f, 976.7f, 983.2f),
                        PathNode.QuadTo(951.4f, 1032.4f, 900.2f, 1058.7f),
                        PathNode.QuadTo(874.1f, 1072.3f, 838.6f, 1075.4f),
                        PathNode.QuadTo(803.0f, 1078.5f, 721.4f, 1078.5f),
                        PathNode.LineTo(421.4f, 1078.5f),
                        PathNode.QuadTo(339.9f, 1078.5f, 304.3f, 1075.4f),
                        PathNode.QuadTo(268.7f, 1072.3f, 242.6f, 1058.7f),
                        PathNode.QuadTo(191.5f, 1032.4f, 166.1f, 983.2f),
                        PathNode.QuadTo(153.6f, 957.1f, 150.5f, 921.6f),
                        PathNode.QuadTo(147.3f, 886.0f, 147.3f, 804.4f),
                        PathNode.LineTo(147.3f, 338.4f),
                        PathNode.QuadTo(147.3f, 256.9f, 150.5f, 221.3f),
                        PathNode.QuadTo(153.6f, 185.7f, 166.1f, 159.6f),
                        PathNode.QuadTo(178.7f, 135.6f, 198.6f, 115.6f),
                        PathNode.QuadTo(218.6f, 95.7f, 242.6f, 84.1f),
                        PathNode.QuadTo(268.7f, 70.6f, 304.3f, 67.5f),
                        PathNode.QuadTo(339.9f, 64.3f, 421.4f, 64.3f),
                        PathNode.LineTo(721.4f, 64.3f),
                        PathNode.QuadTo(803.0f, 64.3f, 838.6f, 67.5f),
                        PathNode.QuadTo(874.1f, 70.6f, 900.2f, 84.1f),
                        PathNode.Close,
                        PathNode.MoveTo(436.1f, 408.2f),
                        PathNode.QuadTo(429.6f, 416.0f, 428.3f, 429.7f),
                        PathNode.QuadTo(427.0f, 443.3f, 427.0f, 476.4f),
                        PathNode.LineTo(427.0f, 666.4f),
                        PathNode.QuadTo(427.0f, 699.5f, 428.7f, 713.2f),
                        PathNode.QuadTo(430.3f, 726.9f, 436.1f, 734.7f),
                        PathNode.QuadTo(440.6f, 742.2f, 449.6f, 747.0f),
                        PathNode.QuadTo(458.5f, 751.8f, 467.9f, 752.8f),
                        PathNode.QuadTo(478.0f, 754.1f, 491.0f, 748.3f),
                        PathNode.QuadTo(504.1f, 742.6f, 531.1f, 726.0f),
                        PathNode.LineTo(696.1f, 631.0f),
                        PathNode.QuadTo(703.4f, 627.0f, 709.7f, 622.8f),
                        PathNode.QuadTo(728.9f, 611.5f, 738.0f, 604.4f),
                        PathNode.QuadTo(747.0f, 597.4f, 750.3f, 589.6f),
                        PathNode.QuadTo(759.1f, 571.4f, 750.3f, 553.3f),
                        PathNode.QuadTo(747.0f, 545.5f, 738.0f, 538.4f),
                        PathNode.QuadTo(728.9f, 531.4f, 709.7f, 520.1f),
                        PathNode.QuadTo(703.4f, 516.1f, 696.1f, 511.8f),
                        PathNode.LineTo(531.1f, 415.8f),
                        PathNode.QuadTo(503.6f, 399.6f, 490.8f, 394.3f),
                        PathNode.QuadTo(478.0f, 389.0f, 467.9f, 390.0f),
                        PathNode.QuadTo(458.5f, 391.0f, 449.6f, 395.8f),
                        PathNode.QuadTo(440.6f, 400.6f, 436.1f, 408.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _miniDramaMedium!!
    }

private var _miniDramaMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.MiniDrama: ImageVector
    get() {
        if (_miniDramaDemibold != null) return _miniDramaDemibold!!
        _miniDramaDemibold = ImageVector.Builder(
            name = "MiniDrama.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(902.9f, 79.0f),
                        PathNode.QuadTo(927.7f, 90.9f, 948.3f, 111.5f),
                        PathNode.QuadTo(968.9f, 132.2f, 981.8f, 157.0f),
                        PathNode.QuadTo(994.8f, 183.8f, 998.0f, 220.2f),
                        PathNode.QuadTo(1001.2f, 256.5f, 1001.2f, 338.4f),
                        PathNode.LineTo(1001.2f, 804.4f),
                        PathNode.QuadTo(1001.2f, 886.3f, 998.0f, 922.7f),
                        PathNode.QuadTo(994.8f, 959.0f, 981.8f, 985.9f),
                        PathNode.QuadTo(955.5f, 1036.5f, 902.9f, 1063.8f),
                        PathNode.QuadTo(876.0f, 1077.8f, 839.7f, 1081.0f),
                        PathNode.QuadTo(803.3f, 1084.2f, 721.4f, 1084.2f),
                        PathNode.LineTo(421.4f, 1084.2f),
                        PathNode.QuadTo(339.5f, 1084.2f, 303.2f, 1081.0f),
                        PathNode.QuadTo(266.8f, 1077.8f, 240.0f, 1063.8f),
                        PathNode.QuadTo(187.3f, 1036.5f, 161.0f, 985.9f),
                        PathNode.QuadTo(148.1f, 959.0f, 144.9f, 922.7f),
                        PathNode.QuadTo(141.6f, 886.3f, 141.6f, 804.4f),
                        PathNode.LineTo(141.6f, 338.4f),
                        PathNode.QuadTo(141.6f, 256.5f, 144.9f, 220.2f),
                        PathNode.QuadTo(148.1f, 183.8f, 161.0f, 157.0f),
                        PathNode.QuadTo(173.9f, 132.2f, 194.5f, 111.5f),
                        PathNode.QuadTo(215.2f, 90.9f, 240.0f, 79.0f),
                        PathNode.QuadTo(266.8f, 65.1f, 303.2f, 61.9f),
                        PathNode.QuadTo(339.5f, 58.6f, 421.4f, 58.6f),
                        PathNode.LineTo(721.4f, 58.6f),
                        PathNode.QuadTo(803.3f, 58.6f, 839.7f, 61.9f),
                        PathNode.QuadTo(876.0f, 65.1f, 902.9f, 79.0f),
                        PathNode.Close,
                        PathNode.MoveTo(433.1f, 405.9f),
                        PathNode.QuadTo(426.2f, 414.3f, 424.7f, 428.4f),
                        PathNode.QuadTo(423.2f, 442.6f, 423.2f, 476.4f),
                        PathNode.LineTo(423.2f, 666.4f),
                        PathNode.QuadTo(423.2f, 700.3f, 425.0f, 714.4f),
                        PathNode.QuadTo(426.7f, 728.6f, 433.1f, 736.9f),
                        PathNode.QuadTo(438.0f, 744.9f, 447.6f, 750.2f),
                        PathNode.QuadTo(457.2f, 755.6f, 467.5f, 756.6f),
                        PathNode.QuadTo(478.3f, 758.1f, 491.8f, 752.1f),
                        PathNode.QuadTo(505.2f, 746.2f, 533.0f, 729.2f),
                        PathNode.LineTo(698.0f, 634.2f),
                        PathNode.QuadTo(705.5f, 630.2f, 711.9f, 625.8f),
                        PathNode.QuadTo(731.4f, 614.3f, 740.8f, 606.9f),
                        PathNode.QuadTo(750.2f, 599.5f, 753.7f, 591.1f),
                        PathNode.QuadTo(763.1f, 571.4f, 753.7f, 551.8f),
                        PathNode.QuadTo(750.2f, 543.4f, 740.8f, 536.0f),
                        PathNode.QuadTo(731.4f, 528.5f, 711.9f, 517.1f),
                        PathNode.QuadTo(705.5f, 513.1f, 698.0f, 508.6f),
                        PathNode.LineTo(533.0f, 412.6f),
                        PathNode.QuadTo(505.1f, 396.2f, 491.7f, 390.7f),
                        PathNode.QuadTo(478.3f, 385.2f, 467.5f, 386.2f),
                        PathNode.QuadTo(457.2f, 387.2f, 447.6f, 392.6f),
                        PathNode.QuadTo(438.0f, 398.0f, 433.1f, 405.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _miniDramaDemibold!!
    }

private var _miniDramaDemibold: ImageVector? = null
