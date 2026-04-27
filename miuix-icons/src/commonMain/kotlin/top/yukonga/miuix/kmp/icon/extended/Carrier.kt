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

val MiuixIcons.Carrier: ImageVector
    get() = MiuixIcons.Regular.Carrier

val MiuixIcons.Light.Carrier: ImageVector
    get() {
        if (_carrierLight != null) return _carrierLight!!
        _carrierLight = ImageVector.Builder(
            name = "Carrier.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1110.0f,
            viewportHeight = 1110.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1110.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(905.0f, 110.8f),
                        PathNode.QuadTo(952.0f, 135.5f, 972.0f, 184.5f),
                        PathNode.QuadTo(982.0f, 208.5f, 981.5f, 244.5f),
                        PathNode.QuadTo(981.0f, 280.5f, 972.0f, 369.2f),
                        PathNode.LineTo(930.0f, 790.5f),
                        PathNode.QuadTo(923.0f, 860.5f, 917.5f, 892.5f),
                        PathNode.QuadTo(912.0f, 924.5f, 899.0f, 945.5f),
                        PathNode.QuadTo(877.0f, 982.5f, 835.3f, 1003.2f),
                        PathNode.QuadTo(814.0f, 1013.5f, 783.5f, 1015.5f),
                        PathNode.QuadTo(753.0f, 1017.5f, 678.6f, 1017.5f),
                        PathNode.HorizontalTo(431.4f),
                        PathNode.QuadTo(357.0f, 1017.5f, 326.5f, 1015.5f),
                        PathNode.QuadTo(296.0f, 1013.5f, 274.7f, 1003.2f),
                        PathNode.QuadTo(233.0f, 982.5f, 211.0f, 945.5f),
                        PathNode.QuadTo(198.0f, 924.5f, 192.5f, 892.5f),
                        PathNode.QuadTo(187.0f, 860.5f, 180.0f, 790.5f),
                        PathNode.LineTo(138.0f, 369.2f),
                        PathNode.QuadTo(129.0f, 280.5f, 128.5f, 244.5f),
                        PathNode.QuadTo(128.0f, 208.5f, 139.0f, 184.5f),
                        PathNode.QuadTo(157.0f, 135.5f, 204.9f, 110.8f),
                        PathNode.QuadTo(228.0f, 98.5f, 264.5f, 95.5f),
                        PathNode.QuadTo(301.0f, 92.5f, 390.4f, 92.5f),
                        PathNode.HorizontalTo(719.6f),
                        PathNode.QuadTo(809.0f, 92.5f, 845.5f, 95.5f),
                        PathNode.QuadTo(882.0f, 98.5f, 905.0f, 110.8f),
                        PathNode.Close,
                        PathNode.MoveTo(357.0f, 822.5f),
                        PathNode.VerticalTo(878.5f),
                        PathNode.QuadTo(357.0f, 886.0f, 361.4f, 890.7f),
                        PathNode.QuadTo(365.8f, 895.5f, 374.0f, 895.5f),
                        PathNode.HorizontalTo(388.0f),
                        PathNode.QuadTo(397.0f, 895.5f, 401.5f, 891.1f),
                        PathNode.QuadTo(406.0f, 886.7f, 406.0f, 878.5f),
                        PathNode.VerticalTo(822.5f),
                        PathNode.QuadTo(406.0f, 782.8f, 425.8f, 748.5f),
                        PathNode.QuadTo(445.7f, 714.2f, 480.0f, 694.3f),
                        PathNode.QuadTo(514.3f, 674.5f, 555.0f, 674.5f),
                        PathNode.QuadTo(595.7f, 674.5f, 630.0f, 694.3f),
                        PathNode.QuadTo(664.3f, 714.2f, 684.2f, 748.5f),
                        PathNode.QuadTo(704.0f, 782.8f, 704.0f, 822.5f),
                        PathNode.VerticalTo(878.5f),
                        PathNode.QuadTo(704.0f, 886.7f, 709.0f, 891.1f),
                        PathNode.QuadTo(714.0f, 895.5f, 722.0f, 895.5f),
                        PathNode.HorizontalTo(735.0f),
                        PathNode.QuadTo(744.0f, 895.5f, 748.5f, 891.1f),
                        PathNode.QuadTo(753.0f, 886.7f, 753.0f, 878.5f),
                        PathNode.VerticalTo(822.5f),
                        PathNode.QuadTo(753.0f, 768.7f, 726.4f, 723.5f),
                        PathNode.QuadTo(699.9f, 678.4f, 654.5f, 651.9f),
                        PathNode.QuadTo(609.1f, 625.5f, 555.1f, 625.5f),
                        PathNode.QuadTo(501.0f, 625.5f, 455.6f, 651.9f),
                        PathNode.QuadTo(410.2f, 678.4f, 383.6f, 723.5f),
                        PathNode.QuadTo(357.0f, 768.7f, 357.0f, 822.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierLight!!
    }

private var _carrierLight: ImageVector? = null

val MiuixIcons.Normal.Carrier: ImageVector
    get() {
        if (_carrierNormal != null) return _carrierNormal!!
        _carrierNormal = ImageVector.Builder(
            name = "Carrier.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1129.8f,
            viewportHeight = 1129.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1129.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(917.7f, 113.6f),
                        PathNode.QuadTo(967.4f, 140.6f, 989.5f, 192.3f),
                        PathNode.QuadTo(1000.1f, 218.4f, 999.6f, 255.8f),
                        PathNode.QuadTo(999.1f, 293.1f, 990.1f, 381.4f),
                        PathNode.LineTo(948.1f, 800.4f),
                        PathNode.QuadTo(941.1f, 871.1f, 935.3f, 903.8f),
                        PathNode.QuadTo(929.5f, 936.5f, 916.5f, 958.1f),
                        PathNode.QuadTo(891.7f, 998.6f, 847.7f, 1020.2f),
                        PathNode.QuadTo(825.3f, 1031.0f, 793.7f, 1033.3f),
                        PathNode.QuadTo(762.2f, 1035.7f, 688.1f, 1035.7f),
                        PathNode.HorizontalTo(441.7f),
                        PathNode.QuadTo(367.6f, 1035.7f, 336.1f, 1033.3f),
                        PathNode.QuadTo(304.5f, 1031.0f, 282.1f, 1020.2f),
                        PathNode.QuadTo(238.1f, 998.6f, 213.3f, 958.1f),
                        PathNode.QuadTo(200.3f, 936.5f, 194.5f, 903.8f),
                        PathNode.QuadTo(188.6f, 871.1f, 181.6f, 800.4f),
                        PathNode.LineTo(139.6f, 381.4f),
                        PathNode.QuadTo(130.6f, 293.1f, 130.1f, 255.8f),
                        PathNode.QuadTo(129.6f, 218.4f, 141.3f, 192.3f),
                        PathNode.QuadTo(161.4f, 140.6f, 212.1f, 113.6f),
                        PathNode.QuadTo(236.5f, 100.1f, 274.1f, 97.1f),
                        PathNode.QuadTo(311.6f, 94.1f, 400.7f, 94.1f),
                        PathNode.HorizontalTo(729.1f),
                        PathNode.QuadTo(818.2f, 94.1f, 855.7f, 97.1f),
                        PathNode.QuadTo(893.3f, 100.1f, 917.7f, 113.6f),
                        PathNode.Close,
                        PathNode.MoveTo(362.1f, 831.7f),
                        PathNode.VerticalTo(880.1f),
                        PathNode.QuadTo(362.1f, 890.0f, 367.9f, 896.4f),
                        PathNode.QuadTo(373.8f, 902.6f, 384.6f, 902.6f),
                        PathNode.HorizontalTo(399.3f),
                        PathNode.QuadTo(411.0f, 902.6f, 416.9f, 896.8f),
                        PathNode.QuadTo(422.8f, 890.9f, 422.8f, 880.1f),
                        PathNode.VerticalTo(831.7f),
                        PathNode.QuadTo(422.8f, 793.9f, 441.7f, 761.1f),
                        PathNode.QuadTo(460.6f, 728.4f, 493.3f, 709.5f),
                        PathNode.QuadTo(526.0f, 690.6f, 564.9f, 690.6f),
                        PathNode.QuadTo(603.8f, 690.6f, 636.5f, 709.5f),
                        PathNode.QuadTo(669.2f, 728.4f, 688.1f, 761.1f),
                        PathNode.QuadTo(707.0f, 793.9f, 707.0f, 831.7f),
                        PathNode.VerticalTo(880.1f),
                        PathNode.QuadTo(707.0f, 890.9f, 713.1f, 896.8f),
                        PathNode.QuadTo(719.1f, 902.6f, 730.5f, 902.6f),
                        PathNode.HorizontalTo(744.2f),
                        PathNode.QuadTo(756.0f, 902.6f, 761.8f, 896.8f),
                        PathNode.QuadTo(767.7f, 890.9f, 767.7f, 880.1f),
                        PathNode.VerticalTo(831.7f),
                        PathNode.QuadTo(767.7f, 776.4f, 740.5f, 730.0f),
                        PathNode.QuadTo(713.3f, 683.5f, 666.8f, 656.4f),
                        PathNode.QuadTo(620.3f, 629.2f, 564.9f, 629.2f),
                        PathNode.QuadTo(509.5f, 629.2f, 463.0f, 656.4f),
                        PathNode.QuadTo(416.5f, 683.5f, 389.3f, 730.0f),
                        PathNode.QuadTo(362.1f, 776.4f, 362.1f, 831.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierNormal!!
    }

private var _carrierNormal: ImageVector? = null

val MiuixIcons.Regular.Carrier: ImageVector
    get() {
        if (_carrierRegular != null) return _carrierRegular!!
        _carrierRegular = ImageVector.Builder(
            name = "Carrier.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1138.8f,
            viewportHeight = 1138.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1138.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(923.4f, 114.9f),
                        PathNode.QuadTo(974.4f, 142.9f, 997.4f, 195.9f),
                        PathNode.QuadTo(1008.4f, 222.9f, 1007.9f, 260.9f),
                        PathNode.QuadTo(1007.4f, 298.9f, 998.4f, 386.9f),
                        PathNode.LineTo(956.4f, 804.9f),
                        PathNode.QuadTo(949.4f, 875.9f, 943.4f, 908.9f),
                        PathNode.QuadTo(937.4f, 941.9f, 924.4f, 963.9f),
                        PathNode.QuadTo(898.4f, 1005.9f, 853.4f, 1027.9f),
                        PathNode.QuadTo(830.4f, 1038.9f, 798.4f, 1041.4f),
                        PathNode.QuadTo(766.4f, 1043.9f, 692.4f, 1043.9f),
                        PathNode.HorizontalTo(446.4f),
                        PathNode.QuadTo(372.4f, 1043.9f, 340.4f, 1041.4f),
                        PathNode.QuadTo(308.4f, 1038.9f, 285.4f, 1027.9f),
                        PathNode.QuadTo(240.4f, 1005.9f, 214.4f, 963.9f),
                        PathNode.QuadTo(201.4f, 941.9f, 195.4f, 908.9f),
                        PathNode.QuadTo(189.4f, 875.9f, 182.4f, 804.9f),
                        PathNode.LineTo(140.4f, 386.9f),
                        PathNode.QuadTo(131.4f, 298.9f, 130.9f, 260.9f),
                        PathNode.QuadTo(130.4f, 222.9f, 142.4f, 195.9f),
                        PathNode.QuadTo(163.4f, 142.9f, 215.4f, 114.9f),
                        PathNode.QuadTo(240.4f, 100.9f, 278.4f, 97.9f),
                        PathNode.QuadTo(316.4f, 94.9f, 405.4f, 94.9f),
                        PathNode.HorizontalTo(733.4f),
                        PathNode.QuadTo(822.4f, 94.9f, 860.4f, 97.9f),
                        PathNode.QuadTo(898.4f, 100.9f, 923.4f, 114.9f),
                        PathNode.Close,
                        PathNode.MoveTo(364.4f, 835.9f),
                        PathNode.VerticalTo(880.9f),
                        PathNode.QuadTo(364.4f, 891.9f, 370.9f, 898.9f),
                        PathNode.QuadTo(377.4f, 905.9f, 389.4f, 905.9f),
                        PathNode.HorizontalTo(404.4f),
                        PathNode.QuadTo(417.4f, 905.9f, 423.9f, 899.4f),
                        PathNode.QuadTo(430.4f, 892.9f, 430.4f, 880.9f),
                        PathNode.VerticalTo(835.9f),
                        PathNode.QuadTo(430.4f, 798.9f, 448.9f, 766.9f),
                        PathNode.QuadTo(467.4f, 734.9f, 499.4f, 716.4f),
                        PathNode.QuadTo(531.4f, 697.9f, 569.4f, 697.9f),
                        PathNode.QuadTo(607.4f, 697.9f, 639.4f, 716.4f),
                        PathNode.QuadTo(671.4f, 734.9f, 689.9f, 766.9f),
                        PathNode.QuadTo(708.4f, 798.9f, 708.4f, 835.9f),
                        PathNode.VerticalTo(880.9f),
                        PathNode.QuadTo(708.4f, 892.9f, 714.9f, 899.4f),
                        PathNode.QuadTo(721.4f, 905.9f, 734.4f, 905.9f),
                        PathNode.HorizontalTo(748.4f),
                        PathNode.QuadTo(761.4f, 905.9f, 767.9f, 899.4f),
                        PathNode.QuadTo(774.4f, 892.9f, 774.4f, 880.9f),
                        PathNode.VerticalTo(835.9f),
                        PathNode.QuadTo(774.4f, 779.9f, 746.9f, 732.9f),
                        PathNode.QuadTo(719.4f, 685.9f, 672.4f, 658.4f),
                        PathNode.QuadTo(625.4f, 630.9f, 569.4f, 630.9f),
                        PathNode.QuadTo(513.4f, 630.9f, 466.4f, 658.4f),
                        PathNode.QuadTo(419.4f, 685.9f, 391.9f, 732.9f),
                        PathNode.QuadTo(364.4f, 779.9f, 364.4f, 835.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierRegular!!
    }

private var _carrierRegular: ImageVector? = null

val MiuixIcons.Medium.Carrier: ImageVector
    get() {
        if (_carrierMedium != null) return _carrierMedium!!
        _carrierMedium = ImageVector.Builder(
            name = "Carrier.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1161.4f,
            viewportHeight = 1161.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1161.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(939.4f, 118.0f),
                        PathNode.QuadTo(993.3f, 147.7f, 1017.5f, 203.7f),
                        PathNode.QuadTo(1029.1f, 231.8f, 1028.6f, 270.4f),
                        PathNode.QuadTo(1028.1f, 309.0f, 1019.1f, 398.8f),
                        PathNode.LineTo(977.1f, 816.8f),
                        PathNode.QuadTo(970.1f, 889.0f, 964.1f, 922.8f),
                        PathNode.QuadTo(958.1f, 956.7f, 943.9f, 979.9f),
                        PathNode.QuadTo(916.8f, 1024.3f, 868.8f, 1048.0f),
                        PathNode.QuadTo(844.0f, 1059.6f, 811.2f, 1062.1f),
                        PathNode.QuadTo(778.3f, 1064.6f, 703.7f, 1064.6f),
                        PathNode.HorizontalTo(457.7f),
                        PathNode.QuadTo(383.1f, 1064.6f, 350.2f, 1062.1f),
                        PathNode.QuadTo(317.3f, 1059.6f, 292.6f, 1048.0f),
                        PathNode.QuadTo(244.6f, 1024.3f, 217.5f, 979.9f),
                        PathNode.QuadTo(203.3f, 956.7f, 197.3f, 922.8f),
                        PathNode.QuadTo(191.3f, 889.0f, 184.3f, 816.8f),
                        PathNode.LineTo(142.3f, 398.8f),
                        PathNode.QuadTo(133.3f, 309.0f, 132.8f, 270.4f),
                        PathNode.QuadTo(132.3f, 231.8f, 144.9f, 203.7f),
                        PathNode.QuadTo(167.6f, 147.7f, 222.0f, 118.0f),
                        PathNode.QuadTo(248.8f, 103.4f, 287.9f, 100.1f),
                        PathNode.QuadTo(327.1f, 96.8f, 416.7f, 96.8f),
                        PathNode.HorizontalTo(744.7f),
                        PathNode.QuadTo(834.3f, 96.8f, 873.5f, 100.1f),
                        PathNode.QuadTo(912.6f, 103.4f, 939.4f, 118.0f),
                        PathNode.Close,
                        PathNode.MoveTo(367.5f, 842.5f),
                        PathNode.VerticalTo(889.3f),
                        PathNode.QuadTo(367.5f, 903.2f, 375.4f, 911.7f),
                        PathNode.QuadTo(383.4f, 920.1f, 397.8f, 920.1f),
                        PathNode.HorizontalTo(418.6f),
                        PathNode.QuadTo(434.0f, 920.1f, 442.0f, 912.2f),
                        PathNode.QuadTo(449.9f, 904.2f, 449.9f, 889.3f),
                        PathNode.VerticalTo(842.5f),
                        PathNode.QuadTo(449.9f, 809.6f, 467.3f, 780.5f),
                        PathNode.QuadTo(484.6f, 751.5f, 514.8f, 734.2f),
                        PathNode.QuadTo(545.0f, 716.8f, 580.7f, 716.8f),
                        PathNode.QuadTo(616.3f, 716.8f, 646.6f, 734.2f),
                        PathNode.QuadTo(676.8f, 751.5f, 694.1f, 780.5f),
                        PathNode.QuadTo(711.5f, 809.6f, 711.5f, 842.5f),
                        PathNode.VerticalTo(889.3f),
                        PathNode.QuadTo(711.5f, 904.2f, 719.4f, 912.2f),
                        PathNode.QuadTo(727.4f, 920.1f, 742.8f, 920.1f),
                        PathNode.HorizontalTo(762.6f),
                        PathNode.QuadTo(778.0f, 920.1f, 786.0f, 912.2f),
                        PathNode.QuadTo(793.9f, 904.2f, 793.9f, 889.3f),
                        PathNode.VerticalTo(842.5f),
                        PathNode.QuadTo(793.9f, 787.1f, 765.3f, 739.2f),
                        PathNode.QuadTo(736.6f, 691.3f, 687.5f, 662.9f),
                        PathNode.QuadTo(638.5f, 634.5f, 580.7f, 634.5f),
                        PathNode.QuadTo(522.9f, 634.5f, 473.9f, 662.9f),
                        PathNode.QuadTo(424.8f, 691.3f, 396.1f, 739.2f),
                        PathNode.QuadTo(367.5f, 787.1f, 367.5f, 842.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierMedium!!
    }

private var _carrierMedium: ImageVector? = null

val MiuixIcons.Demibold.Carrier: ImageVector
    get() {
        if (_carrierDemibold != null) return _carrierDemibold!!
        _carrierDemibold = ImageVector.Builder(
            name = "Carrier.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1177.2f,
            viewportHeight = 1177.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1177.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(950.6f, 120.1f),
                        PathNode.QuadTo(1006.6f, 151.1f, 1031.6f, 209.1f),
                        PathNode.QuadTo(1043.6f, 238.1f, 1043.1f, 277.1f),
                        PathNode.QuadTo(1042.6f, 316.1f, 1033.6f, 407.1f),
                        PathNode.LineTo(991.6f, 825.1f),
                        PathNode.QuadTo(984.6f, 898.1f, 978.6f, 932.6f),
                        PathNode.QuadTo(972.6f, 967.1f, 957.6f, 991.1f),
                        PathNode.QuadTo(929.6f, 1037.1f, 879.6f, 1062.1f),
                        PathNode.QuadTo(853.6f, 1074.1f, 820.1f, 1076.6f),
                        PathNode.QuadTo(786.6f, 1079.1f, 711.6f, 1079.1f),
                        PathNode.HorizontalTo(465.6f),
                        PathNode.QuadTo(390.6f, 1079.1f, 357.1f, 1076.6f),
                        PathNode.QuadTo(323.6f, 1074.1f, 297.6f, 1062.1f),
                        PathNode.QuadTo(247.6f, 1037.1f, 219.6f, 991.1f),
                        PathNode.QuadTo(204.6f, 967.1f, 198.6f, 932.6f),
                        PathNode.QuadTo(192.6f, 898.1f, 185.6f, 825.1f),
                        PathNode.LineTo(143.6f, 407.1f),
                        PathNode.QuadTo(134.6f, 316.1f, 134.1f, 277.1f),
                        PathNode.QuadTo(133.6f, 238.1f, 146.6f, 209.1f),
                        PathNode.QuadTo(170.6f, 151.1f, 226.6f, 120.1f),
                        PathNode.QuadTo(254.6f, 105.1f, 294.6f, 101.6f),
                        PathNode.QuadTo(334.6f, 98.1f, 424.6f, 98.1f),
                        PathNode.HorizontalTo(752.6f),
                        PathNode.QuadTo(842.6f, 98.1f, 882.6f, 101.6f),
                        PathNode.QuadTo(922.6f, 105.1f, 950.6f, 120.1f),
                        PathNode.Close,
                        PathNode.MoveTo(369.6f, 847.1f),
                        PathNode.VerticalTo(895.1f),
                        PathNode.QuadTo(369.6f, 911.1f, 378.6f, 920.6f),
                        PathNode.QuadTo(387.6f, 930.1f, 403.6f, 930.1f),
                        PathNode.HorizontalTo(428.6f),
                        PathNode.QuadTo(445.6f, 930.1f, 454.6f, 921.1f),
                        PathNode.QuadTo(463.6f, 912.1f, 463.6f, 895.1f),
                        PathNode.VerticalTo(847.1f),
                        PathNode.QuadTo(463.6f, 817.1f, 480.1f, 790.1f),
                        PathNode.QuadTo(496.6f, 763.1f, 525.6f, 746.6f),
                        PathNode.QuadTo(554.6f, 730.1f, 588.6f, 730.1f),
                        PathNode.QuadTo(622.6f, 730.1f, 651.6f, 746.6f),
                        PathNode.QuadTo(680.6f, 763.1f, 697.1f, 790.1f),
                        PathNode.QuadTo(713.6f, 817.1f, 713.6f, 847.1f),
                        PathNode.VerticalTo(895.1f),
                        PathNode.QuadTo(713.6f, 912.1f, 722.6f, 921.1f),
                        PathNode.QuadTo(731.6f, 930.1f, 748.6f, 930.1f),
                        PathNode.HorizontalTo(772.6f),
                        PathNode.QuadTo(789.6f, 930.1f, 798.6f, 921.1f),
                        PathNode.QuadTo(807.6f, 912.1f, 807.6f, 895.1f),
                        PathNode.VerticalTo(847.1f),
                        PathNode.QuadTo(807.6f, 792.1f, 778.1f, 743.6f),
                        PathNode.QuadTo(748.6f, 695.1f, 698.1f, 666.1f),
                        PathNode.QuadTo(647.6f, 637.1f, 588.6f, 637.1f),
                        PathNode.QuadTo(529.6f, 637.1f, 479.1f, 666.1f),
                        PathNode.QuadTo(428.6f, 695.1f, 399.1f, 743.6f),
                        PathNode.QuadTo(369.6f, 792.1f, 369.6f, 847.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierDemibold!!
    }

private var _carrierDemibold: ImageVector? = null
