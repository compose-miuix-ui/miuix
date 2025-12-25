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

val MiuixIcons.Folder: ImageVector
    get() = MiuixIcons.Regular.Folder

val MiuixIcons.Light.Folder: ImageVector
    get() {
        if (_folderLight != null) return _folderLight!!
        _folderLight = ImageVector.Builder(
            name = "Folder.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1196.3999999999999f,
            viewportHeight = 1196.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -66.30000000000007f, translationY = 969.1999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1067.0f, -43.0f),
                PathNode.QuadTo(1117.0f, -17.0f, 1144.0f, 34.0f),
                PathNode.QuadTo(1157.0f, 60.0f, 1160.0f, 98.0f),
                PathNode.QuadTo(1163.0f, 136.0f, 1163.0f, 228.0f),
                PathNode.VerticalTo(413.0f),
                PathNode.QuadTo(1163.0f, 506.0f, 1160.0f, 544.0f),
                PathNode.QuadTo(1157.0f, 582.0f, 1144.0f, 608.0f),
                PathNode.QuadTo(1118.0f, 659.0f, 1067.0f, 685.0f),
                PathNode.QuadTo(1041.0f, 698.0f, 1003.0f, 701.0f),
                PathNode.QuadTo(965.0f, 704.0f, 873.0f, 704.0f),
                PathNode.HorizontalTo(670.0f),
                PathNode.QuadTo(649.0f, 704.0f, 640.0f, 707.5f),
                PathNode.QuadTo(631.0f, 711.0f, 618.0f, 725.0f),
                PathNode.LineTo(587.0f, 756.0f),
                PathNode.QuadTo(564.0f, 781.0f, 553.0f, 790.0f),
                PathNode.QuadTo(539.0f, 799.0f, 525.0f, 802.0f),
                PathNode.QuadTo(514.0f, 805.0f, 478.0f, 805.0f),
                PathNode.HorizontalTo(457.0f),
                PathNode.QuadTo(364.0f, 805.0f, 326.0f, 802.0f),
                PathNode.QuadTo(288.0f, 799.0f, 262.0f, 785.0f),
                PathNode.QuadTo(210.0f, 758.0f, 185.0f, 708.0f),
                PathNode.QuadTo(172.0f, 682.0f, 169.0f, 642.5f),
                PathNode.QuadTo(166.0f, 603.0f, 166.0f, 514.0f),
                PathNode.VerticalTo(228.0f),
                PathNode.QuadTo(166.0f, 136.0f, 169.0f, 98.0f),
                PathNode.QuadTo(172.0f, 60.0f, 185.0f, 34.0f),
                PathNode.QuadTo(212.0f, -17.0f, 262.0f, -43.0f),
                PathNode.QuadTo(288.0f, -57.0f, 326.0f, -60.0f),
                PathNode.QuadTo(364.0f, -63.0f, 457.0f, -63.0f),
                PathNode.HorizontalTo(873.0f),
                PathNode.QuadTo(965.0f, -63.0f, 1003.0f, -60.0f),
                PathNode.QuadTo(1041.0f, -57.0f, 1067.0f, -43.0f),
                PathNode.Close,
                PathNode.MoveTo(292.0f, 10.0f),
                PathNode.QuadTo(258.0f, 26.0f, 238.0f, 63.0f),
                PathNode.QuadTo(229.0f, 81.0f, 227.0f, 105.5f),
                PathNode.QuadTo(225.0f, 130.0f, 225.0f, 184.0f),
                PathNode.VerticalTo(447.0f),
                PathNode.HorizontalTo(1104.0f),
                PathNode.VerticalTo(184.0f),
                PathNode.QuadTo(1104.0f, 130.0f, 1102.0f, 105.5f),
                PathNode.QuadTo(1100.0f, 81.0f, 1091.0f, 63.0f),
                PathNode.QuadTo(1083.0f, 46.0f, 1068.5f, 32.0f),
                PathNode.QuadTo(1054.0f, 18.0f, 1037.0f, 10.0f),
                PathNode.QuadTo(1019.0f, 1.0f, 994.5f, -1.0f),
                PathNode.QuadTo(970.0f, -3.0f, 916.0f, -3.0f),
                PathNode.HorizontalTo(413.0f),
                PathNode.QuadTo(359.0f, -3.0f, 334.5f, -1.0f),
                PathNode.QuadTo(310.0f, 1.0f, 292.0f, 10.0f),
                PathNode.Close,
                PathNode.MoveTo(238.0f, 679.0f),
                PathNode.QuadTo(256.0f, 716.0f, 292.0f, 732.0f),
                PathNode.QuadTo(309.0f, 741.0f, 334.0f, 743.0f),
                PathNode.QuadTo(359.0f, 745.0f, 413.0f, 745.0f),
                PathNode.HorizontalTo(484.0f),
                PathNode.QuadTo(499.0f, 745.0f, 509.0f, 741.5f),
                PathNode.QuadTo(519.0f, 738.0f, 528.0f, 728.0f),
                PathNode.LineTo(561.0f, 692.0f),
                PathNode.LineTo(566.0f, 687.0f),
                PathNode.QuadTo(576.0f, 676.0f, 583.0f, 669.5f),
                PathNode.QuadTo(590.0f, 663.0f, 596.0f, 660.0f),
                PathNode.QuadTo(606.0f, 651.0f, 623.0f, 648.0f),
                PathNode.QuadTo(633.0f, 645.0f, 669.0f, 645.0f),
                PathNode.HorizontalTo(916.0f),
                PathNode.QuadTo(970.0f, 645.0f, 994.5f, 643.0f),
                PathNode.QuadTo(1019.0f, 641.0f, 1037.0f, 632.0f),
                PathNode.QuadTo(1075.0f, 612.0f, 1091.0f, 578.0f),
                PathNode.QuadTo(1101.0f, 556.0f, 1104.0f, 506.0f),
                PathNode.HorizontalTo(1089.0f),
                PathNode.HorizontalTo(225.0f),
                PathNode.VerticalTo(558.0f),
                PathNode.QuadTo(225.0f, 612.0f, 227.0f, 636.5f),
                PathNode.QuadTo(229.0f, 661.0f, 238.0f, 679.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _folderLight!!
    }

private var _folderLight: ImageVector? = null

val MiuixIcons.Regular.Folder: ImageVector
    get() {
        if (_folderRegular != null) return _folderRegular!!
        _folderRegular = ImageVector.Builder(
            name = "Folder.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1227.6f,
            viewportHeight = 1227.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -50.700000000000045f, translationY = 984.8f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1073.0f, -55.0f),
                PathNode.QuadTo(1127.0f, -27.0f, 1156.0f, 28.0f),
                PathNode.QuadTo(1170.0f, 56.0f, 1173.0f, 95.5f),
                PathNode.QuadTo(1176.0f, 135.0f, 1176.0f, 228.0f),
                PathNode.VerticalTo(413.0f),
                PathNode.QuadTo(1176.0f, 507.0f, 1173.0f, 546.5f),
                PathNode.QuadTo(1170.0f, 586.0f, 1156.0f, 614.0f),
                PathNode.QuadTo(1127.0f, 668.0f, 1073.0f, 697.0f),
                PathNode.QuadTo(1045.0f, 711.0f, 1005.5f, 714.0f),
                PathNode.QuadTo(966.0f, 717.0f, 873.0f, 717.0f),
                PathNode.HorizontalTo(677.0f),
                PathNode.QuadTo(656.0f, 717.0f, 647.0f, 721.0f),
                PathNode.QuadTo(638.0f, 725.0f, 622.0f, 741.0f),
                PathNode.LineTo(599.0f, 765.0f),
                PathNode.QuadTo(574.0f, 793.0f, 561.0f, 801.0f),
                PathNode.QuadTo(547.0f, 810.0f, 530.0f, 815.0f),
                PathNode.QuadTo(518.0f, 818.0f, 478.0f, 818.0f),
                PathNode.HorizontalTo(457.0f),
                PathNode.QuadTo(364.0f, 818.0f, 324.0f, 814.5f),
                PathNode.QuadTo(284.0f, 811.0f, 256.0f, 797.0f),
                PathNode.QuadTo(202.0f, 769.0f, 173.0f, 714.0f),
                PathNode.QuadTo(159.0f, 687.0f, 156.0f, 647.0f),
                PathNode.QuadTo(153.0f, 607.0f, 153.0f, 514.0f),
                PathNode.VerticalTo(228.0f),
                PathNode.QuadTo(153.0f, 135.0f, 156.0f, 95.5f),
                PathNode.QuadTo(159.0f, 56.0f, 173.0f, 28.0f),
                PathNode.QuadTo(202.0f, -27.0f, 256.0f, -55.0f),
                PathNode.QuadTo(284.0f, -69.0f, 324.0f, -72.5f),
                PathNode.QuadTo(364.0f, -76.0f, 457.0f, -76.0f),
                PathNode.HorizontalTo(873.0f),
                PathNode.QuadTo(966.0f, -76.0f, 1005.5f, -72.5f),
                PathNode.QuadTo(1045.0f, -69.0f, 1073.0f, -55.0f),
                PathNode.Close,
                PathNode.MoveTo(298.0f, 22.0f),
                PathNode.QuadTo(268.0f, 36.0f, 250.0f, 69.0f),
                PathNode.QuadTo(242.0f, 86.0f, 240.0f, 108.5f),
                PathNode.QuadTo(238.0f, 131.0f, 238.0f, 184.0f),
                PathNode.VerticalTo(434.0f),
                PathNode.HorizontalTo(1091.0f),
                PathNode.VerticalTo(184.0f),
                PathNode.QuadTo(1091.0f, 131.0f, 1089.0f, 108.5f),
                PathNode.QuadTo(1087.0f, 86.0f, 1079.0f, 69.0f),
                PathNode.QuadTo(1071.0f, 54.0f, 1059.0f, 41.5f),
                PathNode.QuadTo(1047.0f, 29.0f, 1031.0f, 22.0f),
                PathNode.QuadTo(1015.0f, 14.0f, 992.5f, 12.0f),
                PathNode.QuadTo(970.0f, 10.0f, 916.0f, 10.0f),
                PathNode.HorizontalTo(413.0f),
                PathNode.QuadTo(360.0f, 10.0f, 337.0f, 12.0f),
                PathNode.QuadTo(314.0f, 14.0f, 298.0f, 22.0f),
                PathNode.Close,
                PathNode.MoveTo(250.0f, 673.0f),
                PathNode.QuadTo(268.0f, 706.0f, 298.0f, 720.0f),
                PathNode.QuadTo(314.0f, 728.0f, 337.0f, 730.0f),
                PathNode.QuadTo(360.0f, 732.0f, 413.0f, 732.0f),
                PathNode.HorizontalTo(475.0f),
                PathNode.QuadTo(491.0f, 732.0f, 501.5f, 728.0f),
                PathNode.QuadTo(512.0f, 724.0f, 521.0f, 714.0f),
                PathNode.LineTo(549.0f, 683.0f),
                PathNode.LineTo(554.0f, 677.0f),
                PathNode.QuadTo(564.0f, 667.0f, 571.5f, 659.5f),
                PathNode.QuadTo(579.0f, 652.0f, 586.0f, 648.0f),
                PathNode.QuadTo(598.0f, 640.0f, 617.0f, 635.0f),
                PathNode.QuadTo(629.0f, 632.0f, 669.0f, 632.0f),
                PathNode.HorizontalTo(916.0f),
                PathNode.QuadTo(970.0f, 632.0f, 992.5f, 630.0f),
                PathNode.QuadTo(1015.0f, 628.0f, 1031.0f, 620.0f),
                PathNode.QuadTo(1064.0f, 602.0f, 1079.0f, 572.0f),
                PathNode.QuadTo(1089.0f, 554.0f, 1090.0f, 519.0f),
                PathNode.HorizontalTo(1089.0f),
                PathNode.HorizontalTo(238.0f),
                PathNode.VerticalTo(558.0f),
                PathNode.QuadTo(238.0f, 611.0f, 240.0f, 633.5f),
                PathNode.QuadTo(242.0f, 656.0f, 250.0f, 673.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _folderRegular!!
    }

private var _folderRegular: ImageVector? = null

val MiuixIcons.Heavy.Folder: ImageVector
    get() {
        if (_folderHeavy != null) return _folderHeavy!!
        _folderHeavy = ImageVector.Builder(
            name = "Folder.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1261.2f,
            viewportHeight = 1261.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -33.89999999999998f, translationY = 1002.6f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1079.0f, -67.0f),
                PathNode.QuadTo(1137.0f, -38.0f, 1168.0f, 22.0f),
                PathNode.QuadTo(1183.0f, 52.0f, 1186.5f, 93.5f),
                PathNode.QuadTo(1190.0f, 135.0f, 1190.0f, 228.0f),
                PathNode.VerticalTo(415.0f),
                PathNode.QuadTo(1190.0f, 510.0f, 1186.5f, 551.0f),
                PathNode.QuadTo(1183.0f, 592.0f, 1168.0f, 622.0f),
                PathNode.QuadTo(1137.0f, 680.0f, 1079.0f, 711.0f),
                PathNode.QuadTo(1049.0f, 726.0f, 1008.0f, 729.5f),
                PathNode.QuadTo(967.0f, 733.0f, 873.0f, 733.0f),
                PathNode.HorizontalTo(686.0f),
                PathNode.QuadTo(665.0f, 733.0f, 654.0f, 737.5f),
                PathNode.QuadTo(643.0f, 742.0f, 629.0f, 757.0f),
                PathNode.LineTo(610.0f, 777.0f),
                PathNode.QuadTo(582.0f, 806.0f, 568.0f, 815.0f),
                PathNode.QuadTo(554.0f, 824.0f, 534.0f, 831.0f),
                PathNode.QuadTo(520.0f, 834.0f, 478.0f, 834.0f),
                PathNode.HorizontalTo(457.0f),
                PathNode.QuadTo(363.0f, 834.0f, 321.5f, 830.5f),
                PathNode.QuadTo(280.0f, 827.0f, 250.0f, 812.0f),
                PathNode.QuadTo(192.0f, 781.0f, 161.0f, 723.0f),
                PathNode.QuadTo(146.0f, 694.0f, 142.5f, 652.0f),
                PathNode.QuadTo(139.0f, 610.0f, 139.0f, 516.0f),
                PathNode.VerticalTo(228.0f),
                PathNode.QuadTo(139.0f, 135.0f, 142.5f, 93.5f),
                PathNode.QuadTo(146.0f, 52.0f, 161.0f, 22.0f),
                PathNode.QuadTo(192.0f, -38.0f, 250.0f, -67.0f),
                PathNode.QuadTo(280.0f, -83.0f, 321.5f, -86.5f),
                PathNode.QuadTo(363.0f, -90.0f, 457.0f, -90.0f),
                PathNode.HorizontalTo(873.0f),
                PathNode.QuadTo(967.0f, -90.0f, 1008.0f, -86.5f),
                PathNode.QuadTo(1049.0f, -83.0f, 1079.0f, -67.0f),
                PathNode.Close,
                PathNode.MoveTo(304.0f, 34.0f),
                PathNode.QuadTo(277.0f, 47.0f, 262.0f, 75.0f),
                PathNode.QuadTo(255.0f, 90.0f, 253.5f, 111.0f),
                PathNode.QuadTo(252.0f, 132.0f, 252.0f, 184.0f),
                PathNode.VerticalTo(419.0f),
                PathNode.HorizontalTo(1077.0f),
                PathNode.VerticalTo(184.0f),
                PathNode.QuadTo(1077.0f, 132.0f, 1075.5f, 111.0f),
                PathNode.QuadTo(1074.0f, 90.0f, 1067.0f, 75.0f),
                PathNode.QuadTo(1060.0f, 62.0f, 1049.0f, 51.5f),
                PathNode.QuadTo(1038.0f, 41.0f, 1025.0f, 34.0f),
                PathNode.QuadTo(1011.0f, 27.0f, 990.0f, 25.5f),
                PathNode.QuadTo(969.0f, 24.0f, 916.0f, 24.0f),
                PathNode.HorizontalTo(413.0f),
                PathNode.QuadTo(360.0f, 24.0f, 339.0f, 25.5f),
                PathNode.QuadTo(318.0f, 27.0f, 304.0f, 34.0f),
                PathNode.Close,
                PathNode.MoveTo(262.0f, 669.0f),
                PathNode.QuadTo(277.0f, 697.0f, 304.0f, 710.0f),
                PathNode.QuadTo(318.0f, 718.0f, 339.0f, 719.5f),
                PathNode.QuadTo(360.0f, 721.0f, 413.0f, 721.0f),
                PathNode.HorizontalTo(465.0f),
                PathNode.QuadTo(483.0f, 721.0f, 494.5f, 716.5f),
                PathNode.QuadTo(506.0f, 712.0f, 516.0f, 701.0f),
                PathNode.LineTo(539.0f, 676.0f),
                PathNode.LineTo(544.0f, 671.0f),
                PathNode.QuadTo(554.0f, 661.0f, 562.5f, 652.0f),
                PathNode.QuadTo(571.0f, 643.0f, 579.0f, 638.0f),
                PathNode.QuadTo(593.0f, 629.0f, 613.0f, 624.0f),
                PathNode.QuadTo(627.0f, 621.0f, 669.0f, 621.0f),
                PathNode.HorizontalTo(916.0f),
                PathNode.QuadTo(969.0f, 621.0f, 990.0f, 619.5f),
                PathNode.QuadTo(1011.0f, 618.0f, 1025.0f, 610.0f),
                PathNode.QuadTo(1054.0f, 595.0f, 1067.0f, 568.0f),
                PathNode.QuadTo(1074.0f, 556.0f, 1076.0f, 530.0f),
                PathNode.LineTo(1075.0f, 528.0f),
                PathNode.HorizontalTo(252.0f),
                PathNode.VerticalTo(560.0f),
                PathNode.QuadTo(252.0f, 613.0f, 253.5f, 633.5f),
                PathNode.QuadTo(255.0f, 654.0f, 262.0f, 669.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _folderHeavy!!
    }

private var _folderHeavy: ImageVector? = null
