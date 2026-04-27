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

val MiuixIcons.FolderFill: ImageVector
    get() = MiuixIcons.Regular.FolderFill

val MiuixIcons.Light.FolderFill: ImageVector
    get() {
        if (_folderfillLight != null) return _folderfillLight!!
        _folderfillLight = ImageVector.Builder(
            name = "FolderFill.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1179.6f,
            viewportHeight = 1179.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1179.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(182.3f, 193.8f),
                        PathNode.QuadTo(160.3f, 203.8f, 143.3f, 221.3f),
                        PathNode.QuadTo(126.3f, 238.8f, 115.3f, 260.8f),
                        PathNode.QuadTo(103.3f, 283.8f, 100.8f, 316.8f),
                        PathNode.QuadTo(98.3f, 349.8f, 98.3f, 431.8f),
                        PathNode.VerticalTo(692.8f),
                        PathNode.HorizontalTo(1081.3f),
                        PathNode.VerticalTo(431.8f),
                        PathNode.QuadTo(1081.3f, 349.8f, 1078.8f, 316.8f),
                        PathNode.QuadTo(1076.3f, 283.8f, 1064.3f, 260.8f),
                        PathNode.QuadTo(1054.3f, 238.8f, 1036.8f, 221.3f),
                        PathNode.QuadTo(1019.3f, 203.8f, 997.3f, 193.8f),
                        PathNode.QuadTo(974.3f, 181.8f, 941.3f, 179.3f),
                        PathNode.QuadTo(908.3f, 176.8f, 826.3f, 176.8f),
                        PathNode.HorizontalTo(354.3f),
                        PathNode.QuadTo(271.3f, 176.8f, 238.3f, 179.3f),
                        PathNode.QuadTo(205.3f, 181.8f, 182.3f, 193.8f),
                        PathNode.Close,
                        PathNode.MoveTo(98.3f, 766.8f),
                        PathNode.QuadTo(98.3f, 837.8f, 101.3f, 868.3f),
                        PathNode.QuadTo(104.3f, 898.8f, 115.3f, 918.8f),
                        PathNode.QuadTo(136.3f, 962.8f, 182.3f, 986.8f),
                        PathNode.QuadTo(205.3f, 997.8f, 238.8f, 1000.3f),
                        PathNode.QuadTo(272.3f, 1002.8f, 354.3f, 1002.8f),
                        PathNode.HorizontalTo(414.3f),
                        PathNode.QuadTo(443.3f, 1002.8f, 452.3f, 1000.8f),
                        PathNode.QuadTo(468.3f, 999.8f, 490.3f, 985.8f),
                        PathNode.QuadTo(496.3f, 981.8f, 518.3f, 959.8f),
                        PathNode.QuadTo(540.3f, 937.8f, 546.3f, 933.8f),
                        PathNode.QuadTo(567.3f, 918.8f, 590.3f, 914.8f),
                        PathNode.QuadTo(600.3f, 912.8f, 630.3f, 912.8f),
                        PathNode.HorizontalTo(828.3f),
                        PathNode.QuadTo(909.3f, 912.8f, 942.3f, 910.3f),
                        PathNode.QuadTo(975.3f, 907.8f, 998.3f, 895.8f),
                        PathNode.QuadTo(1043.3f, 873.8f, 1066.3f, 829.8f),
                        PathNode.QuadTo(1073.3f, 814.8f, 1076.3f, 794.8f),
                        PathNode.QuadTo(1079.3f, 774.8f, 1080.3f, 741.8f),
                        PathNode.HorizontalTo(98.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderfillLight!!
    }

private var _folderfillLight: ImageVector? = null

val MiuixIcons.Normal.FolderFill: ImageVector
    get() {
        if (_folderfillNormal != null) return _folderfillNormal!!
        _folderfillNormal = ImageVector.Builder(
            name = "FolderFill.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1191.1f,
            viewportHeight = 1191.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1191.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(188.1f, 188.6f),
                        PathNode.QuadTo(165.4f, 199.3f, 147.0f, 217.8f),
                        PathNode.QuadTo(128.6f, 236.3f, 116.9f, 259.0f),
                        PathNode.QuadTo(104.9f, 283.4f, 102.1f, 317.4f),
                        PathNode.QuadTo(99.3f, 351.4f, 99.3f, 433.4f),
                        PathNode.VerticalTo(693.8f),
                        PathNode.HorizontalTo(1091.9f),
                        PathNode.VerticalTo(433.4f),
                        PathNode.QuadTo(1091.9f, 351.4f, 1089.0f, 317.4f),
                        PathNode.QuadTo(1086.2f, 283.4f, 1074.2f, 259.0f),
                        PathNode.QuadTo(1062.8f, 236.3f, 1044.3f, 217.8f),
                        PathNode.QuadTo(1025.8f, 199.3f, 1003.1f, 188.6f),
                        PathNode.QuadTo(978.7f, 175.9f, 944.7f, 173.0f),
                        PathNode.QuadTo(910.6f, 170.2f, 829.3f, 170.2f),
                        PathNode.HorizontalTo(362.8f),
                        PathNode.QuadTo(280.5f, 170.2f, 246.5f, 173.0f),
                        PathNode.QuadTo(212.4f, 175.9f, 188.1f, 188.6f),
                        PathNode.Close,
                        PathNode.MoveTo(99.3f, 778.8f),
                        PathNode.QuadTo(99.3f, 847.7f, 102.3f, 879.2f),
                        PathNode.QuadTo(105.3f, 910.8f, 116.9f, 932.1f),
                        PathNode.QuadTo(140.0f, 978.9f, 188.1f, 1003.6f),
                        PathNode.QuadTo(212.4f, 1015.3f, 246.6f, 1018.1f),
                        PathNode.QuadTo(280.8f, 1020.9f, 362.8f, 1020.9f),
                        PathNode.HorizontalTo(422.1f),
                        PathNode.QuadTo(451.8f, 1020.9f, 461.5f, 1018.9f),
                        PathNode.QuadTo(480.9f, 1016.6f, 502.9f, 1001.9f),
                        PathNode.QuadTo(510.3f, 997.2f, 532.3f, 975.2f),
                        PathNode.QuadTo(552.3f, 955.3f, 558.9f, 950.6f),
                        PathNode.QuadTo(577.9f, 936.9f, 598.1f, 933.6f),
                        PathNode.QuadTo(607.4f, 931.6f, 635.4f, 931.6f),
                        PathNode.HorizontalTo(829.9f),
                        PathNode.QuadTo(910.9f, 931.6f, 945.0f, 928.8f),
                        PathNode.QuadTo(979.0f, 925.9f, 1003.4f, 913.9f),
                        PathNode.QuadTo(1051.1f, 889.9f, 1074.8f, 843.8f),
                        PathNode.QuadTo(1082.5f, 827.4f, 1085.9f, 807.1f),
                        PathNode.QuadTo(1089.2f, 786.8f, 1090.9f, 755.1f),
                        PathNode.HorizontalTo(99.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderfillNormal!!
    }

private var _folderfillNormal: ImageVector? = null

val MiuixIcons.Regular.FolderFill: ImageVector
    get() {
        if (_folderfillRegular != null) return _folderfillRegular!!
        _folderfillRegular = ImageVector.Builder(
            name = "FolderFill.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1196.4f,
            viewportHeight = 1196.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1196.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(190.7f, 186.2f),
                        PathNode.QuadTo(167.7f, 197.2f, 148.7f, 216.2f),
                        PathNode.QuadTo(129.7f, 235.2f, 117.7f, 258.2f),
                        PathNode.QuadTo(105.7f, 283.2f, 102.7f, 317.7f),
                        PathNode.QuadTo(99.7f, 352.2f, 99.7f, 434.2f),
                        PathNode.VerticalTo(694.2f),
                        PathNode.HorizontalTo(1096.7f),
                        PathNode.VerticalTo(434.2f),
                        PathNode.QuadTo(1096.7f, 352.2f, 1093.7f, 317.7f),
                        PathNode.QuadTo(1090.7f, 283.2f, 1078.7f, 258.2f),
                        PathNode.QuadTo(1066.7f, 235.2f, 1047.7f, 216.2f),
                        PathNode.QuadTo(1028.7f, 197.2f, 1005.7f, 186.2f),
                        PathNode.QuadTo(980.7f, 173.2f, 946.2f, 170.2f),
                        PathNode.QuadTo(911.7f, 167.2f, 830.7f, 167.2f),
                        PathNode.HorizontalTo(366.7f),
                        PathNode.QuadTo(284.7f, 167.2f, 250.2f, 170.2f),
                        PathNode.QuadTo(215.7f, 173.2f, 190.7f, 186.2f),
                        PathNode.Close,
                        PathNode.MoveTo(99.7f, 784.2f),
                        PathNode.QuadTo(99.7f, 852.2f, 102.7f, 884.2f),
                        PathNode.QuadTo(105.7f, 916.2f, 117.7f, 938.2f),
                        PathNode.QuadTo(141.7f, 986.2f, 190.7f, 1011.2f),
                        PathNode.QuadTo(215.7f, 1023.2f, 250.2f, 1026.2f),
                        PathNode.QuadTo(284.7f, 1029.2f, 366.7f, 1029.2f),
                        PathNode.HorizontalTo(425.7f),
                        PathNode.QuadTo(455.7f, 1029.2f, 465.7f, 1027.2f),
                        PathNode.QuadTo(486.7f, 1024.2f, 508.7f, 1009.2f),
                        PathNode.QuadTo(516.7f, 1004.2f, 538.7f, 982.2f),
                        PathNode.QuadTo(557.7f, 963.2f, 564.7f, 958.2f),
                        PathNode.QuadTo(582.7f, 945.2f, 601.7f, 942.2f),
                        PathNode.QuadTo(610.7f, 940.2f, 637.7f, 940.2f),
                        PathNode.HorizontalTo(830.7f),
                        PathNode.QuadTo(911.7f, 940.2f, 946.2f, 937.2f),
                        PathNode.QuadTo(980.7f, 934.2f, 1005.7f, 922.2f),
                        PathNode.QuadTo(1054.7f, 897.2f, 1078.7f, 850.2f),
                        PathNode.QuadTo(1086.7f, 833.2f, 1090.2f, 812.7f),
                        PathNode.QuadTo(1093.7f, 792.2f, 1095.7f, 761.2f),
                        PathNode.HorizontalTo(99.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderfillRegular!!
    }

private var _folderfillRegular: ImageVector? = null

val MiuixIcons.Medium.FolderFill: ImageVector
    get() {
        if (_folderfillMedium != null) return _folderfillMedium!!
        _folderfillMedium = ImageVector.Builder(
            name = "FolderFill.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1211.9f,
            viewportHeight = 1211.9f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1211.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(196.1f, 182.5f),
                        PathNode.QuadTo(172.5f, 194.1f, 152.6f, 214.0f),
                        PathNode.QuadTo(132.8f, 233.8f, 119.6f, 258.0f),
                        PathNode.QuadTo(107.0f, 284.2f, 104.0f, 319.9f),
                        PathNode.QuadTo(101.0f, 355.6f, 101.0f, 437.6f),
                        PathNode.VerticalTo(695.2f),
                        PathNode.HorizontalTo(1110.9f),
                        PathNode.VerticalTo(437.6f),
                        PathNode.QuadTo(1110.9f, 355.6f, 1107.9f, 319.9f),
                        PathNode.QuadTo(1104.9f, 284.2f, 1092.3f, 258.0f),
                        PathNode.QuadTo(1079.2f, 233.8f, 1059.3f, 214.0f),
                        PathNode.QuadTo(1039.4f, 194.1f, 1015.8f, 182.5f),
                        PathNode.QuadTo(989.6f, 168.9f, 954.0f, 165.9f),
                        PathNode.QuadTo(918.3f, 162.9f, 837.3f, 162.9f),
                        PathNode.HorizontalTo(375.6f),
                        PathNode.QuadTo(293.6f, 162.9f, 258.0f, 165.9f),
                        PathNode.QuadTo(222.3f, 168.9f, 196.1f, 182.5f),
                        PathNode.Close,
                        PathNode.MoveTo(101.0f, 794.0f),
                        PathNode.QuadTo(101.0f, 864.4f, 104.0f, 897.0f),
                        PathNode.QuadTo(107.0f, 929.6f, 119.6f, 953.9f),
                        PathNode.QuadTo(145.3f, 1003.1f, 196.1f, 1030.4f),
                        PathNode.QuadTo(222.3f, 1043.0f, 258.0f, 1046.0f),
                        PathNode.QuadTo(293.6f, 1049.0f, 375.6f, 1049.0f),
                        PathNode.HorizontalTo(434.1f),
                        PathNode.QuadTo(465.2f, 1049.0f, 475.8f, 1047.6f),
                        PathNode.QuadTo(499.8f, 1044.0f, 521.8f, 1027.8f),
                        PathNode.QuadTo(529.2f, 1023.4f, 553.5f, 999.7f),
                        PathNode.QuadTo(569.0f, 983.6f, 577.8f, 976.8f),
                        PathNode.QuadTo(592.8f, 965.0f, 611.2f, 962.6f),
                        PathNode.QuadTo(619.1f, 960.6f, 645.5f, 960.6f),
                        PathNode.HorizontalTo(837.3f),
                        PathNode.QuadTo(918.3f, 960.6f, 954.0f, 957.6f),
                        PathNode.QuadTo(989.6f, 954.6f, 1015.8f, 942.0f),
                        PathNode.QuadTo(1066.6f, 914.7f, 1092.3f, 865.9f),
                        PathNode.QuadTo(1101.5f, 847.7f, 1105.0f, 825.8f),
                        PathNode.QuadTo(1108.5f, 803.8f, 1110.5f, 771.0f),
                        PathNode.HorizontalTo(101.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderfillMedium!!
    }

private var _folderfillMedium: ImageVector? = null

val MiuixIcons.Demibold.FolderFill: ImageVector
    get() {
        if (_folderfillDemibold != null) return _folderfillDemibold!!
        _folderfillDemibold = ImageVector.Builder(
            name = "FolderFill.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1222.8f,
            viewportHeight = 1222.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1222.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(199.9f, 179.9f),
                        PathNode.QuadTo(175.9f, 191.9f, 155.4f, 212.4f),
                        PathNode.QuadTo(134.9f, 232.9f, 120.9f, 257.9f),
                        PathNode.QuadTo(107.9f, 284.9f, 104.9f, 321.4f),
                        PathNode.QuadTo(101.9f, 357.9f, 101.9f, 439.9f),
                        PathNode.VerticalTo(695.9f),
                        PathNode.HorizontalTo(1120.9f),
                        PathNode.VerticalTo(439.9f),
                        PathNode.QuadTo(1120.9f, 357.9f, 1117.9f, 321.4f),
                        PathNode.QuadTo(1114.9f, 284.9f, 1101.9f, 257.9f),
                        PathNode.QuadTo(1087.9f, 232.9f, 1067.4f, 212.4f),
                        PathNode.QuadTo(1046.9f, 191.9f, 1022.9f, 179.9f),
                        PathNode.QuadTo(995.9f, 165.9f, 959.4f, 162.9f),
                        PathNode.QuadTo(922.9f, 159.9f, 841.9f, 159.9f),
                        PathNode.HorizontalTo(381.9f),
                        PathNode.QuadTo(299.9f, 159.9f, 263.4f, 162.9f),
                        PathNode.QuadTo(226.9f, 165.9f, 199.9f, 179.9f),
                        PathNode.Close,
                        PathNode.MoveTo(101.9f, 800.9f),
                        PathNode.QuadTo(101.9f, 872.9f, 104.9f, 905.9f),
                        PathNode.QuadTo(107.9f, 938.9f, 120.9f, 964.9f),
                        PathNode.QuadTo(147.9f, 1014.9f, 199.9f, 1043.9f),
                        PathNode.QuadTo(226.9f, 1056.9f, 263.4f, 1059.9f),
                        PathNode.QuadTo(299.9f, 1062.9f, 381.9f, 1062.9f),
                        PathNode.HorizontalTo(439.9f),
                        PathNode.QuadTo(471.9f, 1062.9f, 482.9f, 1061.9f),
                        PathNode.QuadTo(508.9f, 1057.9f, 530.9f, 1040.9f),
                        PathNode.QuadTo(537.9f, 1036.9f, 563.9f, 1011.9f),
                        PathNode.QuadTo(576.9f, 997.9f, 586.9f, 989.9f),
                        PathNode.QuadTo(599.9f, 978.9f, 617.9f, 976.9f),
                        PathNode.QuadTo(624.9f, 974.9f, 650.9f, 974.9f),
                        PathNode.HorizontalTo(841.9f),
                        PathNode.QuadTo(922.9f, 974.9f, 959.4f, 971.9f),
                        PathNode.QuadTo(995.9f, 968.9f, 1022.9f, 955.9f),
                        PathNode.QuadTo(1074.9f, 926.9f, 1101.9f, 876.9f),
                        PathNode.QuadTo(1111.9f, 857.9f, 1115.4f, 834.9f),
                        PathNode.QuadTo(1118.9f, 811.9f, 1120.9f, 777.9f),
                        PathNode.HorizontalTo(101.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderfillDemibold!!
    }

private var _folderfillDemibold: ImageVector? = null
