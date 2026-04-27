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

val MiuixIcons.Promotions: ImageVector
    get() = MiuixIcons.Regular.Promotions

val MiuixIcons.Light.Promotions: ImageVector
    get() {
        if (_promotionsLight != null) return _promotionsLight!!
        _promotionsLight = ImageVector.Builder(
            name = "Promotions.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1273.2f,
            viewportHeight = 1273.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1273.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1167.1f, 736.6f),
                        PathNode.QuadTo(1167.1f, 845.6f, 1148.1f, 937.6f),
                        PathNode.QuadTo(1129.1f, 1029.6f, 1098.1f, 1083.1f),
                        PathNode.QuadTo(1067.1f, 1136.6f, 1033.1f, 1136.6f),
                        PathNode.QuadTo(1021.1f, 1136.6f, 1015.1f, 1133.6f),
                        PathNode.QuadTo(981.1f, 1120.6f, 931.1f, 1097.6f),
                        PathNode.QuadTo(881.1f, 1074.6f, 813.1f, 1041.6f),
                        PathNode.QuadTo(687.1f, 980.6f, 562.1f, 953.6f),
                        PathNode.QuadTo(553.1f, 940.6f, 544.1f, 908.6f),
                        PathNode.QuadTo(525.1f, 837.6f, 525.1f, 733.6f),
                        PathNode.QuadTo(525.1f, 629.6f, 545.1f, 559.6f),
                        PathNode.QuadTo(551.1f, 536.6f, 563.1f, 516.6f),
                        PathNode.QuadTo(676.1f, 489.6f, 785.1f, 440.6f),
                        PathNode.QuadTo(878.1f, 396.6f, 933.1f, 372.1f),
                        PathNode.QuadTo(988.1f, 347.6f, 1019.1f, 337.6f),
                        PathNode.QuadTo(1024.1f, 336.6f, 1033.1f, 336.6f),
                        PathNode.QuadTo(1067.1f, 336.6f, 1098.1f, 390.1f),
                        PathNode.QuadTo(1129.1f, 443.6f, 1148.1f, 535.6f),
                        PathNode.QuadTo(1167.1f, 627.6f, 1167.1f, 736.6f),
                        PathNode.Close,
                        PathNode.MoveTo(579.1f, 231.6f),
                        PathNode.LineTo(499.1f, 535.6f),
                        PathNode.QuadTo(486.1f, 584.6f, 480.1f, 656.1f),
                        PathNode.QuadTo(474.1f, 727.6f, 478.6f, 804.6f),
                        PathNode.QuadTo(483.1f, 881.6f, 501.1f, 945.6f),
                        PathNode.HorizontalTo(318.1f),
                        PathNode.QuadTo(261.1f, 945.6f, 212.1f, 917.1f),
                        PathNode.QuadTo(163.1f, 888.6f, 134.6f, 839.6f),
                        PathNode.QuadTo(106.1f, 790.6f, 106.1f, 733.6f),
                        PathNode.QuadTo(106.1f, 675.6f, 134.6f, 626.6f),
                        PathNode.QuadTo(163.1f, 577.6f, 211.6f, 549.1f),
                        PathNode.QuadTo(260.1f, 520.6f, 318.1f, 520.6f),
                        PathNode.HorizontalTo(360.1f),
                        PathNode.LineTo(440.1f, 196.6f),
                        PathNode.QuadTo(447.1f, 167.6f, 472.6f, 152.1f),
                        PathNode.QuadTo(498.1f, 136.6f, 527.1f, 144.6f),
                        PathNode.QuadTo(557.1f, 151.6f, 572.1f, 177.1f),
                        PathNode.QuadTo(587.1f, 202.6f, 579.1f, 231.6f),
                        PathNode.Close,
                        PathNode.MoveTo(956.1f, 737.6f),
                        PathNode.QuadTo(956.1f, 825.6f, 965.1f, 897.1f),
                        PathNode.QuadTo(974.1f, 968.6f, 991.6f, 1010.1f),
                        PathNode.QuadTo(1009.1f, 1051.6f, 1032.1f, 1051.6f),
                        PathNode.QuadTo(1053.1f, 1051.6f, 1070.6f, 1008.1f),
                        PathNode.QuadTo(1088.1f, 964.6f, 1098.1f, 892.6f),
                        PathNode.QuadTo(1108.1f, 820.6f, 1108.1f, 737.6f),
                        PathNode.QuadTo(1108.1f, 653.6f, 1098.1f, 581.6f),
                        PathNode.QuadTo(1088.1f, 509.6f, 1070.6f, 466.1f),
                        PathNode.QuadTo(1053.1f, 422.6f, 1032.1f, 422.6f),
                        PathNode.QuadTo(1009.1f, 422.6f, 991.6f, 464.1f),
                        PathNode.QuadTo(974.1f, 505.6f, 965.1f, 577.1f),
                        PathNode.QuadTo(956.1f, 648.6f, 956.1f, 737.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsLight!!
    }

private var _promotionsLight: ImageVector? = null

val MiuixIcons.Normal.Promotions: ImageVector
    get() {
        if (_promotionsNormal != null) return _promotionsNormal!!
        _promotionsNormal = ImageVector.Builder(
            name = "Promotions.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1316.9f,
            viewportHeight = 1316.9f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1316.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1207.2f, 757.1f),
                        PathNode.QuadTo(1207.2f, 867.5f, 1187.8f, 960.5f),
                        PathNode.QuadTo(1168.5f, 1053.5f, 1135.8f, 1107.7f),
                        PathNode.QuadTo(1103.1f, 1161.9f, 1064.9f, 1161.9f),
                        PathNode.QuadTo(1054.3f, 1161.9f, 1046.2f, 1158.9f),
                        PathNode.QuadTo(1016.4f, 1148.7f, 970.5f, 1127.4f),
                        PathNode.QuadTo(924.6f, 1106.1f, 843.6f, 1066.9f),
                        PathNode.QuadTo(721.0f, 1007.3f, 593.9f, 979.6f),
                        PathNode.QuadTo(584.2f, 965.9f, 573.2f, 929.8f),
                        PathNode.QuadTo(554.2f, 858.8f, 554.2f, 754.4f),
                        PathNode.QuadTo(554.2f, 650.1f, 574.2f, 580.1f),
                        PathNode.QuadTo(581.6f, 550.9f, 594.9f, 531.6f),
                        PathNode.QuadTo(705.9f, 506.6f, 816.2f, 456.3f),
                        PathNode.QuadTo(910.6f, 411.6f, 964.2f, 387.8f),
                        PathNode.QuadTo(1017.9f, 364.0f, 1048.9f, 354.0f),
                        PathNode.QuadTo(1054.6f, 352.3f, 1064.9f, 352.3f),
                        PathNode.QuadTo(1103.1f, 352.3f, 1135.8f, 406.5f),
                        PathNode.QuadTo(1168.5f, 460.6f, 1187.8f, 553.7f),
                        PathNode.QuadTo(1207.2f, 646.7f, 1207.2f, 757.1f),
                        PathNode.Close,
                        PathNode.MoveTo(595.1f, 261.0f),
                        PathNode.LineTo(515.8f, 560.9f),
                        PathNode.QuadTo(502.8f, 610.6f, 496.8f, 682.8f),
                        PathNode.QuadTo(490.8f, 755.0f, 496.3f, 832.6f),
                        PathNode.QuadTo(501.9f, 910.3f, 521.9f, 973.0f),
                        PathNode.HorizontalTo(328.6f),
                        PathNode.QuadTo(269.6f, 973.0f, 219.2f, 943.4f),
                        PathNode.QuadTo(168.8f, 913.9f, 139.3f, 863.5f),
                        PathNode.QuadTo(109.7f, 813.1f, 109.7f, 754.1f),
                        PathNode.QuadTo(109.7f, 694.0f, 139.3f, 643.6f),
                        PathNode.QuadTo(168.8f, 593.3f, 219.0f, 563.7f),
                        PathNode.QuadTo(269.2f, 534.2f, 328.6f, 534.2f),
                        PathNode.HorizontalTo(362.4f),
                        PathNode.LineTo(439.6f, 221.9f),
                        PathNode.QuadTo(448.0f, 189.5f, 476.2f, 172.2f),
                        PathNode.QuadTo(504.5f, 155.0f, 536.9f, 163.0f),
                        PathNode.QuadTo(569.7f, 171.4f, 586.7f, 200.0f),
                        PathNode.QuadTo(603.8f, 228.6f, 595.1f, 261.0f),
                        PathNode.Close,
                        PathNode.MoveTo(998.9f, 758.1f),
                        PathNode.QuadTo(998.9f, 840.6f, 1007.2f, 909.7f),
                        PathNode.QuadTo(1015.6f, 978.8f, 1030.7f, 1018.9f),
                        PathNode.QuadTo(1045.7f, 1059.0f, 1064.6f, 1059.0f),
                        PathNode.QuadTo(1082.2f, 1059.0f, 1097.3f, 1018.3f),
                        PathNode.QuadTo(1112.4f, 977.5f, 1121.0f, 908.3f),
                        PathNode.QuadTo(1129.6f, 839.0f, 1129.6f, 758.1f),
                        PathNode.QuadTo(1129.6f, 676.1f, 1121.0f, 606.9f),
                        PathNode.QuadTo(1112.4f, 537.6f, 1097.3f, 496.9f),
                        PathNode.QuadTo(1082.2f, 456.1f, 1064.6f, 456.1f),
                        PathNode.QuadTo(1045.7f, 456.1f, 1030.7f, 496.3f),
                        PathNode.QuadTo(1015.6f, 536.4f, 1007.2f, 605.5f),
                        PathNode.QuadTo(998.9f, 674.6f, 998.9f, 758.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsNormal!!
    }

private var _promotionsNormal: ImageVector? = null

val MiuixIcons.Regular.Promotions: ImageVector
    get() {
        if (_promotionsRegular != null) return _promotionsRegular!!
        _promotionsRegular = ImageVector.Builder(
            name = "Promotions.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1336.8f,
            viewportHeight = 1336.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1336.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1225.4f, 766.4f),
                        PathNode.QuadTo(1225.4f, 877.4f, 1205.9f, 970.9f),
                        PathNode.QuadTo(1186.4f, 1064.4f, 1152.9f, 1118.9f),
                        PathNode.QuadTo(1119.4f, 1173.4f, 1079.4f, 1173.4f),
                        PathNode.QuadTo(1069.4f, 1173.4f, 1060.4f, 1170.4f),
                        PathNode.QuadTo(1032.4f, 1161.4f, 988.4f, 1140.9f),
                        PathNode.QuadTo(944.4f, 1120.4f, 857.4f, 1078.4f),
                        PathNode.QuadTo(736.4f, 1019.4f, 608.4f, 991.4f),
                        PathNode.QuadTo(598.4f, 977.4f, 586.4f, 939.4f),
                        PathNode.QuadTo(567.4f, 868.4f, 567.4f, 763.9f),
                        PathNode.QuadTo(567.4f, 659.4f, 587.4f, 589.4f),
                        PathNode.QuadTo(595.4f, 557.4f, 609.4f, 538.4f),
                        PathNode.QuadTo(719.4f, 514.4f, 830.4f, 463.4f),
                        PathNode.QuadTo(925.4f, 418.4f, 978.4f, 394.9f),
                        PathNode.QuadTo(1031.4f, 371.4f, 1062.4f, 361.4f),
                        PathNode.QuadTo(1068.4f, 359.4f, 1079.4f, 359.4f),
                        PathNode.QuadTo(1119.4f, 359.4f, 1152.9f, 413.9f),
                        PathNode.QuadTo(1186.4f, 468.4f, 1205.9f, 561.9f),
                        PathNode.QuadTo(1225.4f, 655.4f, 1225.4f, 766.4f),
                        PathNode.Close,
                        PathNode.MoveTo(602.4f, 274.4f),
                        PathNode.LineTo(523.4f, 572.4f),
                        PathNode.QuadTo(510.4f, 622.4f, 504.4f, 694.9f),
                        PathNode.QuadTo(498.4f, 767.4f, 504.4f, 845.4f),
                        PathNode.QuadTo(510.4f, 923.4f, 531.4f, 985.4f),
                        PathNode.HorizontalTo(333.4f),
                        PathNode.QuadTo(273.4f, 985.4f, 222.4f, 955.4f),
                        PathNode.QuadTo(171.4f, 925.4f, 141.4f, 874.4f),
                        PathNode.QuadTo(111.4f, 823.4f, 111.4f, 763.4f),
                        PathNode.QuadTo(111.4f, 702.4f, 141.4f, 651.4f),
                        PathNode.QuadTo(171.4f, 600.4f, 222.4f, 570.4f),
                        PathNode.QuadTo(273.4f, 540.4f, 333.4f, 540.4f),
                        PathNode.HorizontalTo(363.4f),
                        PathNode.LineTo(439.4f, 233.4f),
                        PathNode.QuadTo(448.4f, 199.4f, 477.9f, 181.4f),
                        PathNode.QuadTo(507.4f, 163.4f, 541.4f, 171.4f),
                        PathNode.QuadTo(575.4f, 180.4f, 593.4f, 210.4f),
                        PathNode.QuadTo(611.4f, 240.4f, 602.4f, 274.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1018.4f, 767.4f),
                        PathNode.QuadTo(1018.4f, 847.4f, 1026.4f, 915.4f),
                        PathNode.QuadTo(1034.4f, 983.4f, 1048.4f, 1022.9f),
                        PathNode.QuadTo(1062.4f, 1062.4f, 1079.4f, 1062.4f),
                        PathNode.QuadTo(1095.4f, 1062.4f, 1109.4f, 1022.9f),
                        PathNode.QuadTo(1123.4f, 983.4f, 1131.4f, 915.4f),
                        PathNode.QuadTo(1139.4f, 847.4f, 1139.4f, 767.4f),
                        PathNode.QuadTo(1139.4f, 686.4f, 1131.4f, 618.4f),
                        PathNode.QuadTo(1123.4f, 550.4f, 1109.4f, 510.9f),
                        PathNode.QuadTo(1095.4f, 471.4f, 1079.4f, 471.4f),
                        PathNode.QuadTo(1062.4f, 471.4f, 1048.4f, 510.9f),
                        PathNode.QuadTo(1034.4f, 550.4f, 1026.4f, 618.4f),
                        PathNode.QuadTo(1018.4f, 686.4f, 1018.4f, 767.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsRegular!!
    }

private var _promotionsRegular: ImageVector? = null

val MiuixIcons.Medium.Promotions: ImageVector
    get() {
        if (_promotionsMedium != null) return _promotionsMedium!!
        _promotionsMedium = ImageVector.Builder(
            name = "Promotions.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1377.0f,
            viewportHeight = 1377.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1377.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1262.3f, 786.8f),
                        PathNode.QuadTo(1262.3f, 899.6f, 1242.2f, 994.8f),
                        PathNode.QuadTo(1222.1f, 1090.1f, 1186.8f, 1146.1f),
                        PathNode.QuadTo(1151.6f, 1202.0f, 1108.6f, 1202.0f),
                        PathNode.QuadTo(1098.0f, 1202.0f, 1087.3f, 1198.5f),
                        PathNode.QuadTo(1059.3f, 1189.5f, 1015.9f, 1169.5f),
                        PathNode.QuadTo(972.5f, 1149.6f, 883.1f, 1105.9f),
                        PathNode.QuadTo(760.3f, 1046.9f, 632.9f, 1018.9f),
                        PathNode.QuadTo(621.2f, 1004.3f, 607.4f, 962.2f),
                        PathNode.QuadTo(588.4f, 890.0f, 588.4f, 784.3f),
                        PathNode.QuadTo(588.4f, 678.6f, 608.4f, 607.5f),
                        PathNode.QuadTo(618.8f, 571.3f, 633.9f, 551.8f),
                        PathNode.QuadTo(743.3f, 527.8f, 856.1f, 476.8f),
                        PathNode.QuadTo(953.5f, 430.6f, 1005.9f, 407.4f),
                        PathNode.QuadTo(1058.3f, 384.2f, 1089.3f, 374.2f),
                        PathNode.QuadTo(1097.0f, 371.6f, 1108.6f, 371.6f),
                        PathNode.QuadTo(1151.6f, 371.6f, 1186.8f, 427.5f),
                        PathNode.QuadTo(1222.1f, 483.5f, 1242.2f, 578.8f),
                        PathNode.QuadTo(1262.3f, 674.0f, 1262.3f, 786.8f),
                        PathNode.Close,
                        PathNode.MoveTo(621.6f, 297.2f),
                        PathNode.LineTo(542.6f, 595.2f),
                        PathNode.QuadTo(529.0f, 645.8f, 523.3f, 720.3f),
                        PathNode.QuadTo(517.6f, 794.9f, 524.8f, 873.8f),
                        PathNode.QuadTo(532.0f, 952.6f, 554.8f, 1014.0f),
                        PathNode.HorizontalTo(345.0f),
                        PathNode.QuadTo(282.6f, 1014.0f, 229.9f, 982.9f),
                        PathNode.QuadTo(177.1f, 951.7f, 145.9f, 898.6f),
                        PathNode.QuadTo(114.8f, 845.6f, 114.8f, 783.8f),
                        PathNode.QuadTo(114.8f, 721.0f, 145.6f, 668.0f),
                        PathNode.QuadTo(176.5f, 614.9f, 229.6f, 583.8f),
                        PathNode.QuadTo(282.6f, 552.6f, 345.0f, 552.6f),
                        PathNode.HorizontalTo(368.5f),
                        PathNode.LineTo(443.3f, 252.0f),
                        PathNode.QuadTo(452.9f, 214.5f, 485.4f, 194.8f),
                        PathNode.QuadTo(517.8f, 175.0f, 554.8f, 184.2f),
                        PathNode.QuadTo(592.3f, 194.3f, 611.8f, 227.0f),
                        PathNode.QuadTo(631.2f, 259.6f, 621.6f, 297.2f),
                        PathNode.Close,
                        PathNode.MoveTo(1057.0f, 787.8f),
                        PathNode.QuadTo(1057.0f, 867.2f, 1064.5f, 933.5f),
                        PathNode.QuadTo(1071.9f, 999.7f, 1084.1f, 1038.0f),
                        PathNode.QuadTo(1096.3f, 1076.3f, 1111.0f, 1076.3f),
                        PathNode.QuadTo(1124.6f, 1076.3f, 1137.2f, 1038.3f),
                        PathNode.QuadTo(1149.7f, 1000.3f, 1157.1f, 933.8f),
                        PathNode.QuadTo(1164.5f, 867.2f, 1164.5f, 787.8f),
                        PathNode.QuadTo(1164.5f, 708.0f, 1157.4f, 641.8f),
                        PathNode.QuadTo(1150.3f, 575.5f, 1137.8f, 537.2f),
                        PathNode.QuadTo(1125.2f, 498.9f, 1111.6f, 498.9f),
                        PathNode.QuadTo(1096.9f, 498.9f, 1084.4f, 537.2f),
                        PathNode.QuadTo(1071.9f, 575.5f, 1064.5f, 641.8f),
                        PathNode.QuadTo(1057.0f, 708.0f, 1057.0f, 787.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsMedium!!
    }

private var _promotionsMedium: ImageVector? = null

val MiuixIcons.Demibold.Promotions: ImageVector
    get() {
        if (_promotionsDemibold != null) return _promotionsDemibold!!
        _promotionsDemibold = ImageVector.Builder(
            name = "Promotions.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1405.2f,
            viewportHeight = 1405.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1405.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1288.1f, 801.1f),
                        PathNode.QuadTo(1288.1f, 915.1f, 1267.6f, 1011.6f),
                        PathNode.QuadTo(1247.1f, 1108.1f, 1210.6f, 1165.1f),
                        PathNode.QuadTo(1174.1f, 1222.1f, 1129.1f, 1222.1f),
                        PathNode.QuadTo(1118.1f, 1222.1f, 1106.1f, 1218.1f),
                        PathNode.QuadTo(1078.1f, 1209.1f, 1035.1f, 1189.6f),
                        PathNode.QuadTo(992.1f, 1170.1f, 901.1f, 1125.1f),
                        PathNode.QuadTo(777.1f, 1066.1f, 650.1f, 1038.1f),
                        PathNode.QuadTo(637.1f, 1023.1f, 622.1f, 978.1f),
                        PathNode.QuadTo(603.1f, 905.1f, 603.1f, 798.6f),
                        PathNode.QuadTo(603.1f, 692.1f, 623.1f, 620.1f),
                        PathNode.QuadTo(635.1f, 581.1f, 651.1f, 561.1f),
                        PathNode.QuadTo(760.1f, 537.1f, 874.1f, 486.1f),
                        PathNode.QuadTo(973.1f, 439.1f, 1025.1f, 416.1f),
                        PathNode.QuadTo(1077.1f, 393.1f, 1108.1f, 383.1f),
                        PathNode.QuadTo(1117.1f, 380.1f, 1129.1f, 380.1f),
                        PathNode.QuadTo(1174.1f, 380.1f, 1210.6f, 437.1f),
                        PathNode.QuadTo(1247.1f, 494.1f, 1267.6f, 590.6f),
                        PathNode.QuadTo(1288.1f, 687.1f, 1288.1f, 801.1f),
                        PathNode.Close,
                        PathNode.MoveTo(635.1f, 313.1f),
                        PathNode.LineTo(556.1f, 611.1f),
                        PathNode.QuadTo(542.1f, 662.1f, 536.6f, 738.1f),
                        PathNode.QuadTo(531.1f, 814.1f, 539.1f, 893.6f),
                        PathNode.QuadTo(547.1f, 973.1f, 571.1f, 1034.1f),
                        PathNode.HorizontalTo(353.1f),
                        PathNode.QuadTo(289.1f, 1034.1f, 235.1f, 1002.1f),
                        PathNode.QuadTo(181.1f, 970.1f, 149.1f, 915.6f),
                        PathNode.QuadTo(117.1f, 861.1f, 117.1f, 798.1f),
                        PathNode.QuadTo(117.1f, 734.1f, 148.6f, 679.6f),
                        PathNode.QuadTo(180.1f, 625.1f, 234.6f, 593.1f),
                        PathNode.QuadTo(289.1f, 561.1f, 353.1f, 561.1f),
                        PathNode.HorizontalTo(372.1f),
                        PathNode.LineTo(446.1f, 265.1f),
                        PathNode.QuadTo(456.1f, 225.1f, 490.6f, 204.1f),
                        PathNode.QuadTo(525.1f, 183.1f, 564.1f, 193.1f),
                        PathNode.QuadTo(604.1f, 204.1f, 624.6f, 238.6f),
                        PathNode.QuadTo(645.1f, 273.1f, 635.1f, 313.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1084.1f, 802.1f),
                        PathNode.QuadTo(1084.1f, 881.1f, 1091.1f, 946.1f),
                        PathNode.QuadTo(1098.1f, 1011.1f, 1109.1f, 1048.6f),
                        PathNode.QuadTo(1120.1f, 1086.1f, 1133.1f, 1086.1f),
                        PathNode.QuadTo(1145.1f, 1086.1f, 1156.6f, 1049.1f),
                        PathNode.QuadTo(1168.1f, 1012.1f, 1175.1f, 946.6f),
                        PathNode.QuadTo(1182.1f, 881.1f, 1182.1f, 802.1f),
                        PathNode.QuadTo(1182.1f, 723.1f, 1175.6f, 658.1f),
                        PathNode.QuadTo(1169.1f, 593.1f, 1157.6f, 555.6f),
                        PathNode.QuadTo(1146.1f, 518.1f, 1134.1f, 518.1f),
                        PathNode.QuadTo(1121.1f, 518.1f, 1109.6f, 555.6f),
                        PathNode.QuadTo(1098.1f, 593.1f, 1091.1f, 658.1f),
                        PathNode.QuadTo(1084.1f, 723.1f, 1084.1f, 802.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsDemibold!!
    }

private var _promotionsDemibold: ImageVector? = null
