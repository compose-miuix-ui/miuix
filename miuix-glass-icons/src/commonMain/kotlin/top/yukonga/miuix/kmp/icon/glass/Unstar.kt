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

val MiuixIcons.Glass.Unstar: ImageVector
    get() = MiuixIcons.Glass.Regular.Unstar

val MiuixIcons.Glass.Light.Unstar: ImageVector
    get() {
        if (_unstarLight != null) return _unstarLight!!
        _unstarLight = ImageVector.Builder(
            name = "Unstar.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(232.5f, 80.0f),
                        PathNode.QuadTo(229.4f, 86.8f, 229.9f, 96.9f),
                        PathNode.QuadTo(230.4f, 107.0f, 233.4f, 126.9f),
                        PathNode.LineTo(272.4f, 369.9f),
                        PathNode.QuadTo(276.9f, 399.6f, 272.2f, 419.2f),
                        PathNode.QuadTo(267.5f, 438.8f, 250.9f, 456.6f),
                        PathNode.LineTo(88.9f, 619.6f),
                        PathNode.LineTo(77.9f, 630.6f),
                        PathNode.QuadTo(59.4f, 649.0f, 50.5f, 659.0f),
                        PathNode.QuadTo(41.5f, 669.0f, 39.0f, 677.8f),
                        PathNode.QuadTo(36.9f, 686.2f, 39.3f, 694.1f),
                        PathNode.QuadTo(41.7f, 702.0f, 46.9f, 707.8f),
                        PathNode.QuadTo(57.7f, 717.7f, 92.3f, 722.8f),
                        PathNode.LineTo(333.8f, 759.8f),
                        PathNode.QuadTo(365.9f, 765.7f, 384.0f, 778.0f),
                        PathNode.QuadTo(402.2f, 790.2f, 414.1f, 813.9f),
                        PathNode.LineTo(514.1f, 1012.9f),
                        PathNode.QuadTo(531.8f, 1047.1f, 540.4f, 1060.6f),
                        PathNode.QuadTo(549.1f, 1074.1f, 556.4f, 1077.7f),
                        PathNode.QuadTo(563.2f, 1081.3f, 571.5f, 1081.3f),
                        PathNode.QuadTo(579.9f, 1081.3f, 586.7f, 1077.2f),
                        PathNode.QuadTo(593.4f, 1073.8f, 600.3f, 1063.4f),
                        PathNode.QuadTo(607.1f, 1053.0f, 622.0f, 1026.3f),
                        PathNode.LineTo(629.0f, 1012.9f),
                        PathNode.LineTo(720.0f, 830.9f),
                        PathNode.QuadTo(733.9f, 803.1f, 745.4f, 789.8f),
                        PathNode.QuadTo(757.0f, 776.5f, 773.3f, 769.8f),
                        PathNode.QuadTo(789.5f, 763.2f, 819.8f, 758.8f),
                        PathNode.LineTo(1019.8f, 726.8f),
                        PathNode.QuadTo(1055.0f, 721.7f, 1072.5f, 717.8f),
                        PathNode.QuadTo(1090.0f, 714.0f, 1096.2f, 707.8f),
                        PathNode.QuadTo(1101.4f, 702.0f, 1103.8f, 692.8f),
                        PathNode.QuadTo(1106.2f, 683.6f, 1103.1f, 675.7f),
                        PathNode.QuadTo(1101.0f, 667.9f, 1092.3f, 658.1f),
                        PathNode.QuadTo(1083.5f, 648.3f, 1066.2f, 631.6f),
                        PathNode.LineTo(1054.2f, 619.6f),
                        PathNode.LineTo(892.2f, 456.6f),
                        PathNode.QuadTo(876.0f, 439.3f, 870.8f, 421.5f),
                        PathNode.QuadTo(865.7f, 403.6f, 869.7f, 378.5f),
                        PathNode.LineTo(904.7f, 156.9f),
                        PathNode.QuadTo(910.2f, 121.8f, 911.7f, 104.1f),
                        PathNode.QuadTo(913.1f, 86.5f, 910.5f, 78.7f),
                        PathNode.QuadTo(907.9f, 71.7f, 902.0f, 66.9f),
                        PathNode.QuadTo(896.1f, 62.1f, 889.7f, 61.6f),
                        PathNode.QuadTo(875.1f, 60.6f, 842.3f, 77.2f),
                        PathNode.LineTo(625.3f, 187.7f),
                        PathNode.QuadTo(594.8f, 203.5f, 571.5f, 203.5f),
                        PathNode.QuadTo(548.2f, 203.5f, 516.8f, 187.2f),
                        PathNode.LineTo(300.8f, 77.2f),
                        PathNode.QuadTo(281.5f, 67.1f, 272.3f, 64.0f),
                        PathNode.QuadTo(263.0f, 60.9f, 255.2f, 62.6f),
                        PathNode.QuadTo(248.3f, 63.7f, 242.0f, 69.0f),
                        PathNode.QuadTo(235.7f, 74.2f, 232.5f, 80.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _unstarLight!!
    }

private var _unstarLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Unstar: ImageVector
    get() {
        if (_unstarNormal != null) return _unstarNormal!!
        _unstarNormal = ImageVector.Builder(
            name = "Unstar.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(224.3f, 76.2f),
                        PathNode.QuadTo(220.6f, 84.5f, 221.1f, 95.2f),
                        PathNode.QuadTo(221.6f, 105.9f, 224.6f, 128.0f),
                        PathNode.LineTo(263.6f, 371.0f),
                        PathNode.QuadTo(267.7f, 398.8f, 263.5f, 416.3f),
                        PathNode.QuadTo(259.3f, 433.7f, 244.5f, 450.3f),
                        PathNode.LineTo(82.5f, 613.3f),
                        PathNode.LineTo(71.5f, 624.3f),
                        PathNode.QuadTo(50.6f, 645.2f, 41.9f, 655.2f),
                        PathNode.QuadTo(33.3f, 665.2f, 30.4f, 675.5f),
                        PathNode.QuadTo(27.7f, 686.4f, 30.9f, 696.4f),
                        PathNode.QuadTo(34.0f, 706.4f, 40.5f, 713.7f),
                        PathNode.QuadTo(52.8f, 725.8f, 91.1f, 731.5f),
                        PathNode.LineTo(332.2f, 768.5f),
                        PathNode.QuadTo(362.2f, 773.8f, 378.6f, 784.8f),
                        PathNode.QuadTo(394.9f, 795.9f, 406.2f, 817.7f),
                        PathNode.LineTo(506.2f, 1016.7f),
                        PathNode.QuadTo(524.7f, 1052.2f, 534.3f, 1067.0f),
                        PathNode.QuadTo(543.9f, 1081.9f, 553.1f, 1085.8f),
                        PathNode.QuadTo(561.4f, 1090.4f, 571.6f, 1090.4f),
                        PathNode.QuadTo(581.7f, 1090.4f, 590.0f, 1085.6f),
                        PathNode.QuadTo(598.3f, 1082.5f, 605.6f, 1071.3f),
                        PathNode.QuadTo(612.9f, 1060.2f, 629.9f, 1029.9f),
                        PathNode.LineTo(636.9f, 1016.7f),
                        PathNode.LineTo(727.9f, 834.7f),
                        PathNode.QuadTo(741.2f, 808.2f, 751.7f, 795.7f),
                        PathNode.QuadTo(762.2f, 783.3f, 777.2f, 777.5f),
                        PathNode.QuadTo(792.3f, 771.6f, 821.0f, 767.5f),
                        PathNode.LineTo(1021.0f, 735.5f),
                        PathNode.QuadTo(1060.2f, 729.8f, 1077.7f, 725.5f),
                        PathNode.QuadTo(1095.2f, 721.2f, 1102.6f, 713.7f),
                        PathNode.QuadTo(1109.1f, 706.4f, 1112.2f, 694.6f),
                        PathNode.QuadTo(1115.4f, 682.8f, 1111.7f, 672.8f),
                        PathNode.QuadTo(1108.9f, 663.5f, 1100.4f, 653.5f),
                        PathNode.QuadTo(1091.8f, 643.6f, 1072.6f, 625.3f),
                        PathNode.LineTo(1060.6f, 613.3f),
                        PathNode.LineTo(898.6f, 450.3f),
                        PathNode.QuadTo(883.9f, 434.6f, 879.2f, 418.7f),
                        PathNode.QuadTo(874.5f, 402.8f, 878.5f, 379.8f),
                        PathNode.LineTo(913.5f, 158.0f),
                        PathNode.QuadTo(919.4f, 119.5f, 920.5f, 102.3f),
                        PathNode.QuadTo(921.7f, 85.1f, 918.8f, 75.8f),
                        PathNode.QuadTo(915.2f, 66.0f, 907.2f, 59.7f),
                        PathNode.QuadTo(899.2f, 53.4f, 890.3f, 52.6f),
                        PathNode.QuadTo(872.7f, 51.6f, 838.4f, 69.1f),
                        PathNode.LineTo(621.4f, 179.3f),
                        PathNode.QuadTo(593.2f, 193.8f, 571.2f, 193.8f),
                        PathNode.QuadTo(549.1f, 193.8f, 520.7f, 179.1f),
                        PathNode.LineTo(304.7f, 69.1f),
                        PathNode.QuadTo(284.3f, 58.4f, 273.5f, 54.7f),
                        PathNode.QuadTo(262.7f, 51.0f, 253.4f, 53.6f),
                        PathNode.QuadTo(244.4f, 55.3f, 236.2f, 62.1f),
                        PathNode.QuadTo(228.0f, 68.9f, 224.3f, 76.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _unstarNormal!!
    }

private var _unstarNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Unstar: ImageVector
    get() {
        if (_unstarRegular != null) return _unstarRegular!!
        _unstarRegular = ImageVector.Builder(
            name = "Unstar.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(220.6f, 74.6f),
                        PathNode.QuadTo(216.6f, 83.6f, 217.1f, 94.6f),
                        PathNode.QuadTo(217.6f, 105.6f, 220.6f, 128.6f),
                        PathNode.LineTo(259.6f, 371.6f),
                        PathNode.QuadTo(263.6f, 398.6f, 259.6f, 415.1f),
                        PathNode.QuadTo(255.6f, 431.6f, 241.6f, 447.6f),
                        PathNode.LineTo(79.6f, 610.6f),
                        PathNode.LineTo(68.6f, 621.6f),
                        PathNode.QuadTo(46.6f, 643.6f, 38.1f, 653.6f),
                        PathNode.QuadTo(29.6f, 663.6f, 26.6f, 674.6f),
                        PathNode.QuadTo(23.6f, 686.6f, 27.1f, 697.6f),
                        PathNode.QuadTo(30.6f, 708.6f, 37.6f, 716.6f),
                        PathNode.QuadTo(50.6f, 729.6f, 90.6f, 735.6f),
                        PathNode.LineTo(331.6f, 772.6f),
                        PathNode.QuadTo(360.6f, 777.6f, 376.1f, 788.1f),
                        PathNode.QuadTo(391.6f, 798.6f, 402.6f, 819.6f),
                        PathNode.LineTo(502.6f, 1018.6f),
                        PathNode.QuadTo(521.6f, 1054.6f, 531.6f, 1070.1f),
                        PathNode.QuadTo(541.6f, 1085.6f, 551.6f, 1089.6f),
                        PathNode.QuadTo(560.6f, 1094.6f, 571.6f, 1094.6f),
                        PathNode.QuadTo(582.6f, 1094.6f, 591.6f, 1089.6f),
                        PathNode.QuadTo(600.6f, 1086.6f, 608.1f, 1075.1f),
                        PathNode.QuadTo(615.6f, 1063.6f, 633.6f, 1031.6f),
                        PathNode.LineTo(640.6f, 1018.6f),
                        PathNode.LineTo(731.6f, 836.6f),
                        PathNode.QuadTo(744.6f, 810.6f, 754.6f, 798.6f),
                        PathNode.QuadTo(764.6f, 786.6f, 779.1f, 781.1f),
                        PathNode.QuadTo(793.6f, 775.6f, 821.6f, 771.6f),
                        PathNode.LineTo(1021.6f, 739.6f),
                        PathNode.QuadTo(1062.6f, 733.6f, 1080.1f, 729.1f),
                        PathNode.QuadTo(1097.6f, 724.6f, 1105.6f, 716.6f),
                        PathNode.QuadTo(1112.6f, 708.6f, 1116.1f, 695.6f),
                        PathNode.QuadTo(1119.6f, 682.6f, 1115.6f, 671.6f),
                        PathNode.QuadTo(1112.6f, 661.6f, 1104.1f, 651.6f),
                        PathNode.QuadTo(1095.6f, 641.6f, 1075.6f, 622.6f),
                        PathNode.LineTo(1063.6f, 610.6f),
                        PathNode.LineTo(901.6f, 447.6f),
                        PathNode.QuadTo(887.6f, 432.6f, 883.1f, 417.6f),
                        PathNode.QuadTo(878.6f, 402.6f, 882.6f, 380.6f),
                        PathNode.LineTo(917.6f, 158.6f),
                        PathNode.QuadTo(923.6f, 118.6f, 924.6f, 101.6f),
                        PathNode.QuadTo(925.6f, 84.6f, 922.6f, 74.6f),
                        PathNode.QuadTo(918.6f, 63.6f, 909.6f, 56.6f),
                        PathNode.QuadTo(900.6f, 49.6f, 890.6f, 48.6f),
                        PathNode.QuadTo(871.6f, 47.6f, 836.6f, 65.6f),
                        PathNode.LineTo(619.6f, 175.6f),
                        PathNode.QuadTo(592.6f, 189.6f, 571.1f, 189.6f),
                        PathNode.QuadTo(549.6f, 189.6f, 522.6f, 175.6f),
                        PathNode.LineTo(306.6f, 65.6f),
                        PathNode.QuadTo(285.6f, 54.6f, 274.1f, 50.6f),
                        PathNode.QuadTo(262.6f, 46.6f, 252.6f, 49.6f),
                        PathNode.QuadTo(242.6f, 51.6f, 233.6f, 59.1f),
                        PathNode.QuadTo(224.6f, 66.6f, 220.6f, 74.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _unstarRegular!!
    }

private var _unstarRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Unstar: ImageVector
    get() {
        if (_unstarMedium != null) return _unstarMedium!!
        _unstarMedium = ImageVector.Builder(
            name = "Unstar.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(213.0f, 71.4f),
                        PathNode.QuadTo(208.7f, 81.5f, 209.0f, 93.6f),
                        PathNode.QuadTo(209.2f, 105.7f, 212.5f, 130.0f),
                        PathNode.LineTo(251.5f, 372.7f),
                        PathNode.QuadTo(255.2f, 398.1f, 251.9f, 412.9f),
                        PathNode.QuadTo(248.5f, 427.6f, 235.6f, 442.3f),
                        PathNode.LineTo(73.9f, 605.0f),
                        PathNode.LineTo(63.4f, 615.4f),
                        PathNode.QuadTo(40.6f, 638.3f, 31.3f, 649.3f),
                        PathNode.QuadTo(22.0f, 660.4f, 18.7f, 672.8f),
                        PathNode.QuadTo(15.2f, 686.4f, 19.1f, 699.4f),
                        PathNode.QuadTo(23.0f, 712.4f, 31.6f, 722.3f),
                        PathNode.QuadTo(46.5f, 737.2f, 89.5f, 743.8f),
                        PathNode.LineTo(330.2f, 780.8f),
                        PathNode.QuadTo(357.3f, 785.5f, 371.3f, 794.8f),
                        PathNode.QuadTo(385.4f, 804.1f, 395.3f, 823.2f),
                        PathNode.LineTo(495.3f, 1022.4f),
                        PathNode.QuadTo(515.4f, 1060.3f, 525.9f, 1076.2f),
                        PathNode.QuadTo(536.4f, 1092.2f, 548.3f, 1097.0f),
                        PathNode.QuadTo(558.7f, 1102.5f, 571.4f, 1102.5f),
                        PathNode.QuadTo(584.2f, 1102.5f, 594.8f, 1097.0f),
                        PathNode.QuadTo(605.4f, 1092.9f, 613.9f, 1080.6f),
                        PathNode.QuadTo(622.3f, 1068.3f, 640.3f, 1036.3f),
                        PathNode.LineTo(647.9f, 1022.4f),
                        PathNode.LineTo(738.9f, 840.4f),
                        PathNode.QuadTo(751.6f, 815.5f, 760.5f, 804.5f),
                        PathNode.QuadTo(769.4f, 793.4f, 782.7f, 788.5f),
                        PathNode.QuadTo(796.0f, 783.5f, 822.7f, 779.8f),
                        PathNode.LineTo(1022.9f, 747.8f),
                        PathNode.QuadTo(1065.0f, 741.8f, 1083.6f, 736.7f),
                        PathNode.QuadTo(1102.2f, 731.7f, 1111.5f, 722.3f),
                        PathNode.QuadTo(1119.9f, 712.7f, 1123.9f, 697.6f),
                        PathNode.QuadTo(1127.9f, 682.4f, 1123.4f, 669.2f),
                        PathNode.QuadTo(1119.9f, 658.2f, 1111.0f, 647.5f),
                        PathNode.QuadTo(1102.1f, 636.8f, 1081.5f, 617.3f),
                        PathNode.LineTo(1069.2f, 605.0f),
                        PathNode.LineTo(907.5f, 442.0f),
                        PathNode.QuadTo(894.6f, 428.3f, 890.8f, 415.4f),
                        PathNode.QuadTo(886.9f, 402.4f, 890.7f, 382.0f),
                        PathNode.LineTo(925.7f, 160.0f),
                        PathNode.QuadTo(931.7f, 119.2f, 932.7f, 101.1f),
                        PathNode.QuadTo(933.7f, 83.0f, 930.1f, 72.0f),
                        PathNode.QuadTo(925.6f, 58.8f, 914.6f, 50.3f),
                        PathNode.QuadTo(903.5f, 41.8f, 891.1f, 40.6f),
                        PathNode.QuadTo(870.2f, 39.6f, 832.8f, 58.4f),
                        PathNode.LineTo(616.1f, 168.4f),
                        PathNode.QuadTo(590.4f, 181.6f, 571.1f, 181.6f),
                        PathNode.QuadTo(551.7f, 181.6f, 526.4f, 168.4f),
                        PathNode.LineTo(310.4f, 58.4f),
                        PathNode.QuadTo(288.0f, 46.6f, 275.2f, 42.6f),
                        PathNode.QuadTo(262.3f, 38.6f, 250.7f, 41.8f),
                        PathNode.QuadTo(238.8f, 44.4f, 228.3f, 52.9f),
                        PathNode.QuadTo(217.8f, 61.5f, 213.0f, 71.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _unstarMedium!!
    }

private var _unstarMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Unstar: ImageVector
    get() {
        if (_unstarDemibold != null) return _unstarDemibold!!
        _unstarDemibold = ImageVector.Builder(
            name = "Unstar.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(207.7f, 69.2f),
                        PathNode.QuadTo(203.3f, 80.1f, 203.3f, 92.9f),
                        PathNode.QuadTo(203.3f, 105.7f, 206.8f, 131.0f),
                        PathNode.LineTo(245.8f, 373.6f),
                        PathNode.QuadTo(249.3f, 397.8f, 246.5f, 411.3f),
                        PathNode.QuadTo(243.6f, 424.8f, 231.5f, 438.5f),
                        PathNode.LineTo(69.9f, 601.1f),
                        PathNode.LineTo(59.9f, 611.2f),
                        PathNode.QuadTo(36.5f, 634.5f, 26.6f, 646.4f),
                        PathNode.QuadTo(16.7f, 658.2f, 13.3f, 671.5f),
                        PathNode.QuadTo(9.3f, 686.3f, 13.5f, 700.7f),
                        PathNode.QuadTo(17.7f, 715.2f, 27.5f, 726.4f),
                        PathNode.QuadTo(43.7f, 742.6f, 88.7f, 749.5f),
                        PathNode.LineTo(329.3f, 786.5f),
                        PathNode.QuadTo(355.1f, 791.1f, 368.0f, 799.5f),
                        PathNode.QuadTo(381.0f, 807.9f, 390.2f, 825.7f),
                        PathNode.LineTo(490.2f, 1025.2f),
                        PathNode.QuadTo(511.0f, 1064.4f, 521.9f, 1080.6f),
                        PathNode.QuadTo(532.9f, 1096.8f, 546.1f, 1102.1f),
                        PathNode.QuadTo(557.4f, 1108.1f, 571.4f, 1108.1f),
                        PathNode.QuadTo(585.3f, 1108.1f, 597.1f, 1102.1f),
                        PathNode.QuadTo(608.9f, 1097.3f, 618.0f, 1084.4f),
                        PathNode.QuadTo(627.1f, 1071.5f, 645.1f, 1039.5f),
                        PathNode.LineTo(653.0f, 1025.2f),
                        PathNode.LineTo(744.0f, 843.2f),
                        PathNode.QuadTo(756.5f, 819.0f, 764.7f, 808.6f),
                        PathNode.QuadTo(772.9f, 798.2f, 785.3f, 793.6f),
                        PathNode.QuadTo(797.7f, 789.1f, 823.4f, 785.5f),
                        PathNode.LineTo(1023.9f, 753.5f),
                        PathNode.QuadTo(1066.7f, 747.5f, 1086.1f, 742.1f),
                        PathNode.QuadTo(1105.4f, 736.7f, 1115.7f, 726.4f),
                        PathNode.QuadTo(1125.0f, 715.6f, 1129.4f, 698.9f),
                        PathNode.QuadTo(1133.8f, 682.3f, 1128.9f, 667.6f),
                        PathNode.QuadTo(1125.0f, 655.8f, 1115.8f, 644.6f),
                        PathNode.QuadTo(1106.6f, 633.5f, 1085.7f, 613.5f),
                        PathNode.LineTo(1073.2f, 601.1f),
                        PathNode.LineTo(911.7f, 438.1f),
                        PathNode.QuadTo(899.5f, 425.4f, 896.2f, 413.8f),
                        PathNode.QuadTo(892.8f, 402.3f, 896.4f, 383.0f),
                        PathNode.LineTo(931.4f, 161.0f),
                        PathNode.QuadTo(937.4f, 119.6f, 938.4f, 100.8f),
                        PathNode.QuadTo(939.4f, 82.0f, 935.4f, 70.1f),
                        PathNode.QuadTo(930.5f, 55.5f, 918.1f, 45.9f),
                        PathNode.QuadTo(905.6f, 36.4f, 891.5f, 34.9f),
                        PathNode.QuadTo(869.3f, 33.9f, 830.1f, 53.3f),
                        PathNode.LineTo(613.6f, 163.3f),
                        PathNode.QuadTo(588.9f, 175.9f, 571.1f, 175.9f),
                        PathNode.QuadTo(553.3f, 175.9f, 529.0f, 163.3f),
                        PathNode.LineTo(313.0f, 53.3f),
                        PathNode.QuadTo(289.7f, 40.9f, 275.9f, 36.9f),
                        PathNode.QuadTo(262.1f, 32.9f, 249.4f, 36.4f),
                        PathNode.QuadTo(236.1f, 39.3f, 224.6f, 48.7f),
                        PathNode.QuadTo(213.1f, 58.0f, 207.7f, 69.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _unstarDemibold!!
    }

private var _unstarDemibold: ImageVector? = null
