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
                        PathNode.MoveTo(893.7f, 124.8f),
                        PathNode.QuadTo(939.4f, 148.9f, 959.0f, 196.6f),
                        PathNode.QuadTo(968.7f, 220.0f, 968.2f, 254.9f),
                        PathNode.QuadTo(967.8f, 289.9f, 959.1f, 375.6f),
                        PathNode.LineTo(918.4f, 782.7f),
                        PathNode.QuadTo(911.7f, 850.4f, 906.3f, 881.5f),
                        PathNode.QuadTo(901.0f, 912.5f, 888.4f, 932.8f),
                        PathNode.QuadTo(866.8f, 969.0f, 826.3f, 989.1f),
                        PathNode.QuadTo(805.6f, 999.1f, 776.0f, 1001.1f),
                        PathNode.QuadTo(746.4f, 1003.0f, 674.5f, 1003.0f),
                        PathNode.HorizontalTo(435.5f),
                        PathNode.QuadTo(363.6f, 1003.0f, 334.0f, 1001.1f),
                        PathNode.QuadTo(304.4f, 999.1f, 283.7f, 989.1f),
                        PathNode.QuadTo(243.2f, 969.0f, 221.6f, 932.8f),
                        PathNode.QuadTo(209.0f, 912.5f, 203.7f, 881.5f),
                        PathNode.QuadTo(198.3f, 850.4f, 191.6f, 782.7f),
                        PathNode.LineTo(150.9f, 375.6f),
                        PathNode.QuadTo(142.2f, 289.9f, 141.8f, 254.9f),
                        PathNode.QuadTo(141.3f, 220.0f, 152.0f, 196.6f),
                        PathNode.QuadTo(169.6f, 148.9f, 216.2f, 124.8f),
                        PathNode.QuadTo(238.7f, 112.8f, 274.1f, 109.9f),
                        PathNode.QuadTo(309.5f, 107.0f, 395.9f, 107.0f),
                        PathNode.HorizontalTo(714.1f),
                        PathNode.QuadTo(800.5f, 107.0f, 835.9f, 109.9f),
                        PathNode.QuadTo(871.3f, 112.8f, 893.7f, 124.8f),
                        PathNode.Close,
                        PathNode.MoveTo(363.1f, 813.6f),
                        PathNode.VerticalTo(866.9f),
                        PathNode.QuadTo(363.1f, 874.4f, 367.5f, 879.1f),
                        PathNode.QuadTo(371.9f, 883.9f, 380.1f, 883.9f),
                        PathNode.HorizontalTo(393.7f),
                        PathNode.QuadTo(402.7f, 883.9f, 407.2f, 879.5f),
                        PathNode.QuadTo(411.7f, 875.1f, 411.7f, 866.9f),
                        PathNode.VerticalTo(813.6f),
                        PathNode.QuadTo(411.7f, 775.4f, 430.7f, 742.4f),
                        PathNode.QuadTo(449.9f, 709.4f, 482.9f, 690.2f),
                        PathNode.QuadTo(515.8f, 671.2f, 555.0f, 671.2f),
                        PathNode.QuadTo(594.2f, 671.2f, 627.2f, 690.2f),
                        PathNode.QuadTo(660.1f, 709.4f, 679.3f, 742.4f),
                        PathNode.QuadTo(698.3f, 775.4f, 698.3f, 813.6f),
                        PathNode.VerticalTo(866.9f),
                        PathNode.QuadTo(698.3f, 875.1f, 703.3f, 879.5f),
                        PathNode.QuadTo(708.2f, 883.9f, 716.3f, 883.9f),
                        PathNode.HorizontalTo(729.0f),
                        PathNode.QuadTo(738.0f, 883.9f, 742.5f, 879.5f),
                        PathNode.QuadTo(747.0f, 875.1f, 747.0f, 866.9f),
                        PathNode.VerticalTo(813.6f),
                        PathNode.QuadTo(747.0f, 761.4f, 721.2f, 717.6f),
                        PathNode.QuadTo(695.5f, 673.8f, 651.5f, 648.1f),
                        PathNode.QuadTo(607.4f, 622.5f, 555.1f, 622.5f),
                        PathNode.QuadTo(502.6f, 622.5f, 458.6f, 648.1f),
                        PathNode.QuadTo(414.6f, 673.8f, 388.8f, 717.6f),
                        PathNode.QuadTo(363.1f, 761.4f, 363.1f, 813.6f),
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
                        PathNode.MoveTo(906.2f, 127.8f),
                        PathNode.QuadTo(954.6f, 154.2f, 976.2f, 204.6f),
                        PathNode.QuadTo(986.6f, 230.1f, 986.1f, 266.4f),
                        PathNode.QuadTo(985.6f, 302.7f, 976.9f, 387.9f),
                        PathNode.LineTo(936.3f, 792.5f),
                        PathNode.QuadTo(929.6f, 860.9f, 923.9f, 892.5f),
                        PathNode.QuadTo(918.2f, 924.2f, 905.7f, 945.2f),
                        PathNode.QuadTo(881.3f, 984.8f, 838.5f, 1005.8f),
                        PathNode.QuadTo(816.7f, 1016.3f, 786.0f, 1018.6f),
                        PathNode.QuadTo(755.4f, 1021.0f, 683.9f, 1021.0f),
                        PathNode.HorizontalTo(445.9f),
                        PathNode.QuadTo(374.3f, 1021.0f, 343.7f, 1018.6f),
                        PathNode.QuadTo(313.1f, 1016.3f, 291.3f, 1005.8f),
                        PathNode.QuadTo(248.4f, 984.8f, 224.1f, 945.2f),
                        PathNode.QuadTo(211.5f, 924.2f, 205.9f, 892.5f),
                        PathNode.QuadTo(200.1f, 860.9f, 193.4f, 792.5f),
                        PathNode.LineTo(152.8f, 387.9f),
                        PathNode.QuadTo(144.1f, 302.7f, 143.6f, 266.4f),
                        PathNode.QuadTo(143.1f, 230.1f, 154.5f, 204.6f),
                        PathNode.QuadTo(174.2f, 154.2f, 223.6f, 127.8f),
                        PathNode.QuadTo(247.4f, 114.6f, 283.8f, 111.7f),
                        PathNode.QuadTo(320.2f, 108.8f, 406.3f, 108.8f),
                        PathNode.HorizontalTo(723.5f),
                        PathNode.QuadTo(809.5f, 108.8f, 845.9f, 111.7f),
                        PathNode.QuadTo(882.4f, 114.6f, 906.2f, 127.8f),
                        PathNode.Close,
                        PathNode.MoveTo(368.2f, 822.6f),
                        PathNode.VerticalTo(868.3f),
                        PathNode.QuadTo(368.2f, 878.2f, 374.1f, 884.6f),
                        PathNode.QuadTo(380.0f, 890.8f, 390.8f, 890.8f),
                        PathNode.HorizontalTo(405.1f),
                        PathNode.QuadTo(416.8f, 890.8f, 422.7f, 885.0f),
                        PathNode.QuadTo(428.6f, 879.1f, 428.6f, 868.3f),
                        PathNode.VerticalTo(822.6f),
                        PathNode.QuadTo(428.6f, 786.3f, 446.7f, 754.9f),
                        PathNode.QuadTo(464.8f, 723.5f, 496.2f, 705.4f),
                        PathNode.QuadTo(527.6f, 687.2f, 564.9f, 687.2f),
                        PathNode.QuadTo(602.2f, 687.2f, 633.6f, 705.4f),
                        PathNode.QuadTo(664.9f, 723.5f, 683.1f, 754.9f),
                        PathNode.QuadTo(701.2f, 786.3f, 701.2f, 822.6f),
                        PathNode.VerticalTo(868.3f),
                        PathNode.QuadTo(701.2f, 879.1f, 707.2f, 885.0f),
                        PathNode.QuadTo(713.2f, 890.8f, 724.7f, 890.8f),
                        PathNode.HorizontalTo(738.0f),
                        PathNode.QuadTo(749.8f, 890.8f, 755.6f, 885.0f),
                        PathNode.QuadTo(761.5f, 879.1f, 761.5f, 868.3f),
                        PathNode.VerticalTo(822.6f),
                        PathNode.QuadTo(761.5f, 768.9f, 735.2f, 723.9f),
                        PathNode.QuadTo(708.8f, 678.8f, 663.7f, 652.5f),
                        PathNode.QuadTo(618.6f, 626.2f, 564.9f, 626.2f),
                        PathNode.QuadTo(511.2f, 626.2f, 466.1f, 652.5f),
                        PathNode.QuadTo(421.0f, 678.8f, 394.6f, 723.9f),
                        PathNode.QuadTo(368.2f, 768.9f, 368.2f, 822.6f),
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
                        PathNode.MoveTo(911.9f, 129.3f),
                        PathNode.QuadTo(961.7f, 156.6f, 984.1f, 208.3f),
                        PathNode.QuadTo(994.8f, 234.6f, 994.3f, 271.4f),
                        PathNode.QuadTo(993.8f, 308.2f, 985.2f, 393.4f),
                        PathNode.LineTo(944.6f, 796.8f),
                        PathNode.QuadTo(937.9f, 865.5f, 932.1f, 897.5f),
                        PathNode.QuadTo(926.3f, 929.5f, 913.5f, 951.0f),
                        PathNode.QuadTo(888.2f, 992.0f, 844.2f, 1013.5f),
                        PathNode.QuadTo(821.7f, 1024.2f, 790.7f, 1026.7f),
                        PathNode.QuadTo(759.6f, 1029.1f, 688.1f, 1029.1f),
                        PathNode.HorizontalTo(450.7f),
                        PathNode.QuadTo(379.2f, 1029.1f, 348.1f, 1026.7f),
                        PathNode.QuadTo(317.1f, 1024.2f, 294.6f, 1013.5f),
                        PathNode.QuadTo(250.6f, 992.0f, 225.3f, 951.0f),
                        PathNode.QuadTo(212.5f, 929.5f, 206.7f, 897.5f),
                        PathNode.QuadTo(200.9f, 865.5f, 194.2f, 796.8f),
                        PathNode.LineTo(153.6f, 393.4f),
                        PathNode.QuadTo(145.0f, 308.2f, 144.5f, 271.4f),
                        PathNode.QuadTo(144.0f, 234.6f, 155.7f, 208.3f),
                        PathNode.QuadTo(176.3f, 156.6f, 226.9f, 129.3f),
                        PathNode.QuadTo(251.4f, 115.6f, 288.3f, 112.7f),
                        PathNode.QuadTo(325.2f, 109.7f, 411.1f, 109.7f),
                        PathNode.HorizontalTo(727.7f),
                        PathNode.QuadTo(813.6f, 109.7f, 850.5f, 112.7f),
                        PathNode.QuadTo(887.4f, 115.6f, 911.9f, 129.3f),
                        PathNode.Close,
                        PathNode.MoveTo(370.0f, 825.7f),
                        PathNode.VerticalTo(869.4f),
                        PathNode.QuadTo(370.0f, 880.6f, 376.6f, 887.6f),
                        PathNode.QuadTo(383.1f, 894.7f, 395.2f, 894.7f),
                        PathNode.HorizontalTo(410.7f),
                        PathNode.QuadTo(423.7f, 894.7f, 430.3f, 888.1f),
                        PathNode.QuadTo(436.8f, 881.6f, 436.8f, 869.4f),
                        PathNode.VerticalTo(825.7f),
                        PathNode.QuadTo(436.8f, 790.7f, 454.5f, 760.4f),
                        PathNode.QuadTo(472.1f, 730.1f, 502.6f, 712.5f),
                        PathNode.QuadTo(533.2f, 694.8f, 569.4f, 694.8f),
                        PathNode.QuadTo(605.6f, 694.8f, 636.2f, 712.5f),
                        PathNode.QuadTo(666.7f, 730.1f, 684.3f, 760.4f),
                        PathNode.QuadTo(702.0f, 790.7f, 702.0f, 825.7f),
                        PathNode.VerticalTo(869.4f),
                        PathNode.QuadTo(702.0f, 881.6f, 708.5f, 888.1f),
                        PathNode.QuadTo(715.1f, 894.7f, 728.1f, 894.7f),
                        PathNode.HorizontalTo(742.7f),
                        PathNode.QuadTo(755.7f, 894.7f, 762.2f, 888.1f),
                        PathNode.QuadTo(768.8f, 881.6f, 768.8f, 869.4f),
                        PathNode.VerticalTo(825.7f),
                        PathNode.QuadTo(768.8f, 771.7f, 742.0f, 726.2f),
                        PathNode.QuadTo(715.3f, 680.7f, 669.5f, 654.0f),
                        PathNode.QuadTo(623.8f, 627.3f, 569.4f, 627.3f),
                        PathNode.QuadTo(515.0f, 627.3f, 469.3f, 654.0f),
                        PathNode.QuadTo(423.5f, 680.7f, 396.8f, 726.2f),
                        PathNode.QuadTo(370.0f, 771.7f, 370.0f, 825.7f),
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
                        PathNode.MoveTo(927.7f, 132.6f),
                        PathNode.QuadTo(980.3f, 161.7f, 1003.9f, 216.3f),
                        PathNode.QuadTo(1015.2f, 243.7f, 1014.8f, 281.1f),
                        PathNode.QuadTo(1014.3f, 318.4f, 1005.6f, 405.4f),
                        PathNode.LineTo(965.1f, 808.5f),
                        PathNode.QuadTo(958.4f, 878.4f, 952.6f, 911.2f),
                        PathNode.QuadTo(946.8f, 944.1f, 932.8f, 966.7f),
                        PathNode.QuadTo(906.4f, 1010.1f, 859.5f, 1033.3f),
                        PathNode.QuadTo(835.2f, 1044.7f, 803.3f, 1047.1f),
                        PathNode.QuadTo(771.4f, 1049.5f, 699.3f, 1049.5f),
                        PathNode.HorizontalTo(462.1f),
                        PathNode.QuadTo(390.0f, 1049.5f, 358.1f, 1047.1f),
                        PathNode.QuadTo(326.2f, 1044.7f, 301.9f, 1033.3f),
                        PathNode.QuadTo(255.0f, 1010.1f, 228.6f, 966.7f),
                        PathNode.QuadTo(214.6f, 944.1f, 208.8f, 911.2f),
                        PathNode.QuadTo(203.0f, 878.4f, 196.3f, 808.5f),
                        PathNode.LineTo(155.8f, 405.4f),
                        PathNode.QuadTo(147.1f, 318.4f, 146.6f, 281.1f),
                        PathNode.QuadTo(146.2f, 243.7f, 158.4f, 216.3f),
                        PathNode.QuadTo(180.7f, 161.7f, 233.7f, 132.6f),
                        PathNode.QuadTo(260.0f, 118.4f, 298.0f, 115.2f),
                        PathNode.QuadTo(336.0f, 111.9f, 422.6f, 111.9f),
                        PathNode.HorizontalTo(738.8f),
                        PathNode.QuadTo(825.4f, 111.9f, 863.4f, 115.2f),
                        PathNode.QuadTo(901.4f, 118.4f, 927.7f, 132.6f),
                        PathNode.Close,
                        PathNode.MoveTo(373.2f, 832.1f),
                        PathNode.VerticalTo(877.6f),
                        PathNode.QuadTo(373.2f, 891.7f, 381.2f, 900.2f),
                        PathNode.QuadTo(389.2f, 908.7f, 403.6f, 908.7f),
                        PathNode.HorizontalTo(425.1f),
                        PathNode.QuadTo(440.5f, 908.7f, 448.5f, 900.7f),
                        PathNode.QuadTo(456.5f, 892.6f, 456.5f, 877.6f),
                        PathNode.VerticalTo(832.1f),
                        PathNode.QuadTo(456.5f, 801.3f, 473.0f, 773.9f),
                        PathNode.QuadTo(489.4f, 746.6f, 518.1f, 730.2f),
                        PathNode.QuadTo(546.8f, 713.7f, 580.7f, 713.7f),
                        PathNode.QuadTo(614.5f, 713.7f, 643.3f, 730.2f),
                        PathNode.QuadTo(672.0f, 746.6f, 688.4f, 773.9f),
                        PathNode.QuadTo(704.9f, 801.3f, 704.9f, 832.1f),
                        PathNode.VerticalTo(877.6f),
                        PathNode.QuadTo(704.9f, 892.6f, 712.9f, 900.7f),
                        PathNode.QuadTo(720.9f, 908.7f, 736.3f, 908.7f),
                        PathNode.HorizontalTo(756.8f),
                        PathNode.QuadTo(772.2f, 908.7f, 780.2f, 900.7f),
                        PathNode.QuadTo(788.2f, 892.6f, 788.2f, 877.6f),
                        PathNode.VerticalTo(832.1f),
                        PathNode.QuadTo(788.2f, 778.8f, 760.3f, 732.4f),
                        PathNode.QuadTo(732.4f, 686.0f, 684.6f, 658.4f),
                        PathNode.QuadTo(636.8f, 630.8f, 580.7f, 630.8f),
                        PathNode.QuadTo(524.6f, 630.8f, 476.8f, 658.4f),
                        PathNode.QuadTo(429.0f, 686.0f, 401.1f, 732.4f),
                        PathNode.QuadTo(373.2f, 778.8f, 373.2f, 832.1f),
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
                        PathNode.MoveTo(938.7f, 134.9f),
                        PathNode.QuadTo(993.5f, 165.3f, 1017.8f, 221.9f),
                        PathNode.QuadTo(1029.6f, 250.2f, 1029.1f, 287.9f),
                        PathNode.QuadTo(1028.6f, 325.7f, 1019.9f, 413.8f),
                        PathNode.LineTo(979.4f, 816.7f),
                        PathNode.QuadTo(972.7f, 887.3f, 966.9f, 920.8f),
                        PathNode.QuadTo(961.1f, 954.3f, 946.4f, 977.7f),
                        PathNode.QuadTo(919.1f, 1022.7f, 870.1f, 1047.2f),
                        PathNode.QuadTo(844.6f, 1059.0f, 812.1f, 1061.4f),
                        PathNode.QuadTo(779.6f, 1063.8f, 707.1f, 1063.8f),
                        PathNode.HorizontalTo(470.1f),
                        PathNode.QuadTo(397.6f, 1063.8f, 365.1f, 1061.4f),
                        PathNode.QuadTo(332.6f, 1059.0f, 307.1f, 1047.2f),
                        PathNode.QuadTo(258.1f, 1022.7f, 230.8f, 977.7f),
                        PathNode.QuadTo(216.1f, 954.3f, 210.3f, 920.8f),
                        PathNode.QuadTo(204.5f, 887.3f, 197.8f, 816.7f),
                        PathNode.LineTo(157.3f, 413.8f),
                        PathNode.QuadTo(148.6f, 325.7f, 148.1f, 287.9f),
                        PathNode.QuadTo(147.6f, 250.2f, 160.3f, 221.9f),
                        PathNode.QuadTo(183.9f, 165.3f, 238.5f, 134.9f),
                        PathNode.QuadTo(265.9f, 120.3f, 304.8f, 116.9f),
                        PathNode.QuadTo(343.7f, 113.4f, 430.5f, 113.4f),
                        PathNode.HorizontalTo(746.7f),
                        PathNode.QuadTo(833.5f, 113.4f, 872.4f, 116.9f),
                        PathNode.QuadTo(911.3f, 120.3f, 938.7f, 134.9f),
                        PathNode.Close,
                        PathNode.MoveTo(375.4f, 836.5f),
                        PathNode.VerticalTo(883.2f),
                        PathNode.QuadTo(375.4f, 899.4f, 384.5f, 908.9f),
                        PathNode.QuadTo(393.5f, 918.5f, 409.5f, 918.5f),
                        PathNode.HorizontalTo(435.2f),
                        PathNode.QuadTo(452.1f, 918.5f, 461.2f, 909.4f),
                        PathNode.QuadTo(470.3f, 900.4f, 470.3f, 883.2f),
                        PathNode.VerticalTo(836.5f),
                        PathNode.QuadTo(470.3f, 808.7f, 485.8f, 783.4f),
                        PathNode.QuadTo(501.4f, 758.1f, 529.0f, 742.5f),
                        PathNode.QuadTo(556.5f, 727.0f, 588.6f, 727.0f),
                        PathNode.QuadTo(620.8f, 727.0f, 648.2f, 742.5f),
                        PathNode.QuadTo(675.8f, 758.1f, 691.4f, 783.4f),
                        PathNode.QuadTo(706.9f, 808.7f, 706.9f, 836.5f),
                        PathNode.VerticalTo(883.2f),
                        PathNode.QuadTo(706.9f, 900.4f, 716.0f, 909.4f),
                        PathNode.QuadTo(725.1f, 918.5f, 742.0f, 918.5f),
                        PathNode.HorizontalTo(766.7f),
                        PathNode.QuadTo(783.7f, 918.5f, 792.7f, 909.4f),
                        PathNode.QuadTo(801.8f, 900.4f, 801.8f, 883.2f),
                        PathNode.VerticalTo(836.5f),
                        PathNode.QuadTo(801.8f, 783.7f, 773.0f, 736.7f),
                        PathNode.QuadTo(744.3f, 689.7f, 695.1f, 661.6f),
                        PathNode.QuadTo(645.9f, 633.4f, 588.6f, 633.4f),
                        PathNode.QuadTo(531.3f, 633.4f, 482.1f, 661.6f),
                        PathNode.QuadTo(432.9f, 689.7f, 404.2f, 736.7f),
                        PathNode.QuadTo(375.4f, 783.7f, 375.4f, 836.5f),
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
