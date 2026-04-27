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

val MiuixIcons.SearchDevice: ImageVector
    get() = MiuixIcons.Regular.SearchDevice

val MiuixIcons.Light.SearchDevice: ImageVector
    get() {
        if (_searchdeviceLight != null) return _searchdeviceLight!!
        _searchdeviceLight = ImageVector.Builder(
            name = "SearchDevice.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1232.4f,
            viewportHeight = 1232.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1232.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(974.7f, 264.7f),
                        PathNode.QuadTo(1047.7f, 338.7f, 1085.7f, 436.2f),
                        PathNode.QuadTo(1123.7f, 533.7f, 1119.2f, 639.7f),
                        PathNode.QuadTo(1114.7f, 745.7f, 1064.7f, 844.7f),
                        PathNode.QuadTo(1060.7f, 852.7f, 1052.2f, 853.7f),
                        PathNode.QuadTo(1043.7f, 854.7f, 1037.7f, 848.7f),
                        PathNode.LineTo(1021.7f, 833.7f),
                        PathNode.QuadTo(1015.7f, 827.7f, 1014.2f, 822.2f),
                        PathNode.QuadTo(1012.7f, 816.7f, 1015.7f, 810.7f),
                        PathNode.QuadTo(1057.7f, 722.7f, 1060.7f, 630.7f),
                        PathNode.QuadTo(1063.7f, 538.7f, 1030.2f, 455.2f),
                        PathNode.QuadTo(996.7f, 371.7f, 931.7f, 307.7f),
                        PathNode.QuadTo(847.7f, 222.7f, 734.7f, 193.2f),
                        PathNode.QuadTo(621.7f, 163.7f, 509.2f, 193.2f),
                        PathNode.QuadTo(396.7f, 222.7f, 312.7f, 307.7f),
                        PathNode.QuadTo(227.7f, 391.7f, 198.2f, 504.7f),
                        PathNode.QuadTo(168.7f, 617.7f, 198.2f, 730.2f),
                        PathNode.QuadTo(227.7f, 842.7f, 312.7f, 926.7f),
                        PathNode.QuadTo(393.7f, 1007.7f, 501.2f, 1038.2f),
                        PathNode.QuadTo(608.7f, 1068.7f, 717.2f, 1044.7f),
                        PathNode.QuadTo(825.7f, 1020.7f, 910.7f, 945.7f),
                        PathNode.LineTo(658.7f, 694.7f),
                        PathNode.QuadTo(652.7f, 687.7f, 643.7f, 688.7f),
                        PathNode.QuadTo(631.7f, 692.7f, 622.7f, 692.7f),
                        PathNode.QuadTo(591.7f, 692.7f, 569.2f, 670.7f),
                        PathNode.QuadTo(546.7f, 648.7f, 546.7f, 616.7f),
                        PathNode.QuadTo(546.7f, 585.7f, 569.2f, 563.7f),
                        PathNode.QuadTo(591.7f, 541.7f, 622.7f, 541.7f),
                        PathNode.QuadTo(654.7f, 541.7f, 676.2f, 563.7f),
                        PathNode.QuadTo(697.7f, 585.7f, 697.7f, 616.7f),
                        PathNode.QuadTo(697.7f, 620.7f, 694.7f, 638.7f),
                        PathNode.QuadTo(692.7f, 645.7f, 698.7f, 651.7f),
                        PathNode.LineTo(980.7f, 932.7f),
                        PathNode.QuadTo(986.7f, 939.7f, 986.7f, 946.7f),
                        PathNode.QuadTo(986.7f, 953.7f, 981.7f, 959.7f),
                        PathNode.LineTo(973.7f, 968.7f),
                        PathNode.QuadTo(878.7f, 1063.7f, 750.2f, 1096.7f),
                        PathNode.QuadTo(621.7f, 1129.7f, 493.7f, 1096.7f),
                        PathNode.QuadTo(365.7f, 1063.7f, 270.7f, 968.7f),
                        PathNode.QuadTo(175.7f, 873.7f, 142.2f, 745.2f),
                        PathNode.QuadTo(108.7f, 616.7f, 142.2f, 488.7f),
                        PathNode.QuadTo(175.7f, 360.7f, 270.7f, 264.7f),
                        PathNode.QuadTo(365.7f, 169.7f, 494.2f, 136.2f),
                        PathNode.QuadTo(622.7f, 102.7f, 751.2f, 136.2f),
                        PathNode.QuadTo(879.7f, 169.7f, 974.7f, 264.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchdeviceLight!!
    }

private var _searchdeviceLight: ImageVector? = null

val MiuixIcons.Normal.SearchDevice: ImageVector
    get() {
        if (_searchdeviceNormal != null) return _searchdeviceNormal!!
        _searchdeviceNormal = ImageVector.Builder(
            name = "SearchDevice.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1255.5f,
            viewportHeight = 1255.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1255.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(992.8f, 270.1f),
                        PathNode.QuadTo(1067.8f, 345.4f, 1106.2f, 444.7f),
                        PathNode.QuadTo(1144.5f, 543.9f, 1140.4f, 650.9f),
                        PathNode.QuadTo(1136.2f, 757.9f, 1086.2f, 856.2f),
                        PathNode.QuadTo(1080.8f, 867.0f, 1069.6f, 868.3f),
                        PathNode.QuadTo(1058.3f, 869.7f, 1050.3f, 861.6f),
                        PathNode.LineTo(1030.2f, 842.5f),
                        PathNode.QuadTo(1022.1f, 835.1f, 1020.6f, 827.6f),
                        PathNode.QuadTo(1019.1f, 820.0f, 1022.1f, 812.6f),
                        PathNode.QuadTo(1061.3f, 728.1f, 1064.0f, 639.5f),
                        PathNode.QuadTo(1066.7f, 550.9f, 1033.8f, 469.8f),
                        PathNode.QuadTo(1001.0f, 388.8f, 937.4f, 325.4f),
                        PathNode.QuadTo(854.8f, 242.5f, 744.2f, 213.7f),
                        PathNode.QuadTo(633.6f, 184.9f, 523.5f, 213.7f),
                        PathNode.QuadTo(413.4f, 242.5f, 330.8f, 325.4f),
                        PathNode.QuadTo(247.8f, 408.1f, 219.0f, 518.7f),
                        PathNode.QuadTo(190.2f, 629.2f, 219.0f, 739.3f),
                        PathNode.QuadTo(247.8f, 849.4f, 330.8f, 932.1f),
                        PathNode.QuadTo(409.0f, 1009.6f, 512.4f, 1039.8f),
                        PathNode.QuadTo(615.8f, 1069.9f, 720.8f, 1048.7f),
                        PathNode.QuadTo(825.9f, 1027.4f, 908.8f, 957.2f),
                        PathNode.LineTo(668.5f, 717.2f),
                        PathNode.QuadTo(661.2f, 709.6f, 652.2f, 710.6f),
                        PathNode.QuadTo(642.9f, 712.5f, 634.6f, 713.2f),
                        PathNode.QuadTo(599.5f, 713.2f, 574.6f, 688.4f),
                        PathNode.QuadTo(549.7f, 663.7f, 549.7f, 628.2f),
                        PathNode.QuadTo(549.7f, 593.1f, 574.6f, 568.7f),
                        PathNode.QuadTo(599.5f, 544.3f, 634.6f, 544.3f),
                        PathNode.QuadTo(670.0f, 544.3f, 694.3f, 568.7f),
                        PathNode.QuadTo(718.5f, 593.1f, 718.5f, 628.2f),
                        PathNode.QuadTo(718.5f, 635.0f, 716.2f, 647.5f),
                        PathNode.QuadTo(714.2f, 653.8f, 721.6f, 661.2f),
                        PathNode.LineTo(1002.2f, 941.5f),
                        PathNode.QuadTo(1009.6f, 949.2f, 1009.6f, 957.9f),
                        PathNode.QuadTo(1009.6f, 966.6f, 1003.9f, 974.0f),
                        PathNode.LineTo(992.5f, 986.4f),
                        PathNode.QuadTo(895.4f, 1083.5f, 764.8f, 1117.2f),
                        PathNode.QuadTo(634.3f, 1150.9f, 503.9f, 1117.2f),
                        PathNode.QuadTo(373.5f, 1083.5f, 276.4f, 986.4f),
                        PathNode.QuadTo(179.3f, 889.4f, 145.2f, 758.8f),
                        PathNode.QuadTo(111.0f, 628.2f, 145.2f, 497.8f),
                        PathNode.QuadTo(179.3f, 367.4f, 276.4f, 270.1f),
                        PathNode.QuadTo(373.5f, 173.0f, 504.0f, 138.8f),
                        PathNode.QuadTo(634.6f, 104.6f, 765.2f, 138.8f),
                        PathNode.QuadTo(895.7f, 173.0f, 992.8f, 270.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchdeviceNormal!!
    }

private var _searchdeviceNormal: ImageVector? = null

val MiuixIcons.Regular.SearchDevice: ImageVector
    get() {
        if (_searchdeviceRegular != null) return _searchdeviceRegular!!
        _searchdeviceRegular = ImageVector.Builder(
            name = "SearchDevice.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1266.0f,
            viewportHeight = 1266.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1266.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1001.0f, 272.5f),
                        PathNode.QuadTo(1077.0f, 348.5f, 1115.5f, 448.5f),
                        PathNode.QuadTo(1154.0f, 548.5f, 1150.0f, 656.0f),
                        PathNode.QuadTo(1146.0f, 763.5f, 1096.0f, 861.5f),
                        PathNode.QuadTo(1090.0f, 873.5f, 1077.5f, 875.0f),
                        PathNode.QuadTo(1065.0f, 876.5f, 1056.0f, 867.5f),
                        PathNode.LineTo(1034.0f, 846.5f),
                        PathNode.QuadTo(1025.0f, 838.5f, 1023.5f, 830.0f),
                        PathNode.QuadTo(1022.0f, 821.5f, 1025.0f, 813.5f),
                        PathNode.QuadTo(1063.0f, 730.5f, 1065.5f, 643.5f),
                        PathNode.QuadTo(1068.0f, 556.5f, 1035.5f, 476.5f),
                        PathNode.QuadTo(1003.0f, 396.5f, 940.0f, 333.5f),
                        PathNode.QuadTo(858.0f, 251.5f, 748.5f, 223.0f),
                        PathNode.QuadTo(639.0f, 194.5f, 530.0f, 223.0f),
                        PathNode.QuadTo(421.0f, 251.5f, 339.0f, 333.5f),
                        PathNode.QuadTo(257.0f, 415.5f, 228.5f, 525.0f),
                        PathNode.QuadTo(200.0f, 634.5f, 228.5f, 743.5f),
                        PathNode.QuadTo(257.0f, 852.5f, 339.0f, 934.5f),
                        PathNode.QuadTo(416.0f, 1010.5f, 517.5f, 1040.5f),
                        PathNode.QuadTo(619.0f, 1070.5f, 722.5f, 1050.5f),
                        PathNode.QuadTo(826.0f, 1030.5f, 908.0f, 962.5f),
                        PathNode.LineTo(673.0f, 727.5f),
                        PathNode.QuadTo(665.0f, 719.5f, 656.0f, 720.5f),
                        PathNode.QuadTo(648.0f, 721.5f, 640.0f, 722.5f),
                        PathNode.QuadTo(603.0f, 722.5f, 577.0f, 696.5f),
                        PathNode.QuadTo(551.0f, 670.5f, 551.0f, 633.5f),
                        PathNode.QuadTo(551.0f, 596.5f, 577.0f, 571.0f),
                        PathNode.QuadTo(603.0f, 545.5f, 640.0f, 545.5f),
                        PathNode.QuadTo(677.0f, 545.5f, 702.5f, 571.0f),
                        PathNode.QuadTo(728.0f, 596.5f, 728.0f, 633.5f),
                        PathNode.QuadTo(728.0f, 641.5f, 726.0f, 651.5f),
                        PathNode.QuadTo(724.0f, 657.5f, 732.0f, 665.5f),
                        PathNode.LineTo(1012.0f, 945.5f),
                        PathNode.QuadTo(1020.0f, 953.5f, 1020.0f, 963.0f),
                        PathNode.QuadTo(1020.0f, 972.5f, 1014.0f, 980.5f),
                        PathNode.LineTo(1001.0f, 994.5f),
                        PathNode.QuadTo(903.0f, 1092.5f, 771.5f, 1126.5f),
                        PathNode.QuadTo(640.0f, 1160.5f, 508.5f, 1126.5f),
                        PathNode.QuadTo(377.0f, 1092.5f, 279.0f, 994.5f),
                        PathNode.QuadTo(181.0f, 896.5f, 146.5f, 765.0f),
                        PathNode.QuadTo(112.0f, 633.5f, 146.5f, 502.0f),
                        PathNode.QuadTo(181.0f, 370.5f, 279.0f, 272.5f),
                        PathNode.QuadTo(377.0f, 174.5f, 508.5f, 140.0f),
                        PathNode.QuadTo(640.0f, 105.5f, 771.5f, 140.0f),
                        PathNode.QuadTo(903.0f, 174.5f, 1001.0f, 272.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchdeviceRegular!!
    }

private var _searchdeviceRegular: ImageVector? = null

val MiuixIcons.Medium.SearchDevice: ImageVector
    get() {
        if (_searchdeviceMedium != null) return _searchdeviceMedium!!
        _searchdeviceMedium = ImageVector.Builder(
            name = "SearchDevice.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1286.5f,
            viewportHeight = 1286.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1286.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1022.1f, 282.4f),
                        PathNode.QuadTo(1096.9f, 359.6f, 1134.6f, 459.6f),
                        PathNode.QuadTo(1172.2f, 559.6f, 1168.8f, 663.3f),
                        PathNode.QuadTo(1165.4f, 767.0f, 1120.6f, 855.6f),
                        PathNode.QuadTo(1113.5f, 871.1f, 1099.5f, 873.8f),
                        PathNode.QuadTo(1085.5f, 876.4f, 1074.8f, 865.7f),
                        PathNode.LineTo(1046.9f, 840.6f),
                        PathNode.QuadTo(1034.9f, 830.2f, 1033.4f, 820.8f),
                        PathNode.QuadTo(1031.9f, 811.4f, 1035.5f, 799.9f),
                        PathNode.QuadTo(1067.1f, 728.1f, 1068.1f, 648.1f),
                        PathNode.QuadTo(1069.1f, 568.2f, 1039.0f, 491.4f),
                        PathNode.QuadTo(1008.8f, 414.7f, 948.8f, 353.4f),
                        PathNode.QuadTo(869.7f, 272.0f, 762.6f, 242.6f),
                        PathNode.QuadTo(655.4f, 213.3f, 548.2f, 239.7f),
                        PathNode.QuadTo(440.9f, 266.1f, 359.5f, 345.2f),
                        PathNode.QuadTo(278.1f, 424.3f, 248.4f, 531.4f),
                        PathNode.QuadTo(218.8f, 638.6f, 245.5f, 746.1f),
                        PathNode.QuadTo(272.2f, 853.6f, 351.3f, 935.0f),
                        PathNode.QuadTo(424.8f, 1009.3f, 522.1f, 1040.1f),
                        PathNode.QuadTo(619.5f, 1071.0f, 719.8f, 1054.3f),
                        PathNode.QuadTo(820.1f, 1037.5f, 901.5f, 974.8f),
                        PathNode.LineTo(676.5f, 743.3f),
                        PathNode.QuadTo(671.4f, 738.3f, 665.9f, 739.3f),
                        PathNode.QuadTo(659.7f, 740.3f, 649.4f, 740.7f),
                        PathNode.QuadTo(608.8f, 739.5f, 580.8f, 710.9f),
                        PathNode.QuadTo(552.7f, 682.2f, 553.3f, 641.7f),
                        PathNode.QuadTo(554.5f, 601.7f, 583.1f, 574.5f),
                        PathNode.QuadTo(611.8f, 547.2f, 652.3f, 547.8f),
                        PathNode.QuadTo(692.2f, 548.4f, 719.8f, 576.8f),
                        PathNode.QuadTo(747.4f, 605.3f, 746.8f, 645.2f),
                        PathNode.QuadTo(746.8f, 655.0f, 744.8f, 662.6f),
                        PathNode.QuadTo(743.4f, 667.4f, 747.8f, 671.9f),
                        PathNode.LineTo(1023.7f, 955.4f),
                        PathNode.QuadTo(1034.1f, 965.8f, 1033.8f, 978.5f),
                        PathNode.QuadTo(1033.5f, 991.3f, 1025.1f, 1001.6f),
                        PathNode.LineTo(1011.5f, 1015.6f),
                        PathNode.QuadTo(910.6f, 1113.6f, 776.4f, 1146.4f),
                        PathNode.QuadTo(642.3f, 1179.3f, 509.3f, 1142.3f),
                        PathNode.QuadTo(376.4f, 1105.4f, 278.4f, 1004.4f),
                        PathNode.QuadTo(180.4f, 903.5f, 147.3f, 769.6f),
                        PathNode.QuadTo(114.3f, 635.8f, 151.1f, 502.8f),
                        PathNode.QuadTo(188.0f, 369.9f, 289.5f, 271.9f),
                        PathNode.QuadTo(390.5f, 173.9f, 524.6f, 140.5f),
                        PathNode.QuadTo(658.8f, 107.2f, 791.7f, 144.1f),
                        PathNode.QuadTo(924.7f, 180.9f, 1022.1f, 282.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchdeviceMedium!!
    }

private var _searchdeviceMedium: ImageVector? = null

val MiuixIcons.Demibold.SearchDevice: ImageVector
    get() {
        if (_searchdeviceDemibold != null) return _searchdeviceDemibold!!
        _searchdeviceDemibold = ImageVector.Builder(
            name = "SearchDevice.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1300.8f,
            viewportHeight = 1300.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1300.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1036.9f, 289.4f),
                        PathNode.QuadTo(1110.9f, 367.4f, 1147.9f, 467.4f),
                        PathNode.QuadTo(1184.9f, 567.4f, 1181.9f, 668.4f),
                        PathNode.QuadTo(1178.9f, 769.4f, 1137.9f, 851.4f),
                        PathNode.QuadTo(1129.9f, 869.4f, 1114.9f, 872.9f),
                        PathNode.QuadTo(1099.9f, 876.4f, 1087.9f, 864.4f),
                        PathNode.LineTo(1055.9f, 836.4f),
                        PathNode.QuadTo(1041.9f, 824.4f, 1040.4f, 814.4f),
                        PathNode.QuadTo(1038.9f, 804.4f, 1042.9f, 790.4f),
                        PathNode.QuadTo(1069.9f, 726.4f, 1069.9f, 651.4f),
                        PathNode.QuadTo(1069.9f, 576.4f, 1041.4f, 501.9f),
                        PathNode.QuadTo(1012.9f, 427.4f, 954.9f, 367.4f),
                        PathNode.QuadTo(877.9f, 286.4f, 772.4f, 256.4f),
                        PathNode.QuadTo(666.9f, 226.4f, 560.9f, 251.4f),
                        PathNode.QuadTo(454.9f, 276.4f, 373.9f, 353.4f),
                        PathNode.QuadTo(292.9f, 430.4f, 262.4f, 535.9f),
                        PathNode.QuadTo(231.9f, 641.4f, 257.4f, 747.9f),
                        PathNode.QuadTo(282.9f, 854.4f, 359.9f, 935.4f),
                        PathNode.QuadTo(430.9f, 1008.4f, 525.4f, 1039.9f),
                        PathNode.QuadTo(619.9f, 1071.4f, 717.9f, 1056.9f),
                        PathNode.QuadTo(815.9f, 1042.4f, 896.9f, 983.4f),
                        PathNode.LineTo(678.9f, 754.4f),
                        PathNode.QuadTo(675.9f, 751.4f, 672.9f, 752.4f),
                        PathNode.QuadTo(667.9f, 753.4f, 655.9f, 753.4f),
                        PathNode.QuadTo(612.9f, 751.4f, 583.4f, 720.9f),
                        PathNode.QuadTo(553.9f, 690.4f, 554.9f, 647.4f),
                        PathNode.QuadTo(556.9f, 605.4f, 587.4f, 576.9f),
                        PathNode.QuadTo(617.9f, 548.4f, 660.9f, 549.4f),
                        PathNode.QuadTo(702.9f, 550.4f, 731.9f, 580.9f),
                        PathNode.QuadTo(760.9f, 611.4f, 759.9f, 653.4f),
                        PathNode.QuadTo(759.9f, 664.4f, 757.9f, 670.4f),
                        PathNode.QuadTo(756.9f, 674.4f, 758.9f, 676.4f),
                        PathNode.LineTo(1031.9f, 962.4f),
                        PathNode.QuadTo(1043.9f, 974.4f, 1043.4f, 989.4f),
                        PathNode.QuadTo(1042.9f, 1004.4f, 1032.9f, 1016.4f),
                        PathNode.LineTo(1018.9f, 1030.4f),
                        PathNode.QuadTo(915.9f, 1128.4f, 779.9f, 1160.4f),
                        PathNode.QuadTo(643.9f, 1192.4f, 509.9f, 1153.4f),
                        PathNode.QuadTo(375.9f, 1114.4f, 277.9f, 1011.4f),
                        PathNode.QuadTo(179.9f, 908.4f, 147.9f, 772.9f),
                        PathNode.QuadTo(115.9f, 637.4f, 154.4f, 503.4f),
                        PathNode.QuadTo(192.9f, 369.4f, 296.9f, 271.4f),
                        PathNode.QuadTo(399.9f, 173.4f, 535.9f, 140.9f),
                        PathNode.QuadTo(671.9f, 108.4f, 805.9f, 146.9f),
                        PathNode.QuadTo(939.9f, 185.4f, 1036.9f, 289.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchdeviceDemibold!!
    }

private var _searchdeviceDemibold: ImageVector? = null
