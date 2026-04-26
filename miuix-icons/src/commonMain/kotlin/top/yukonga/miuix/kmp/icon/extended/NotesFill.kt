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

val MiuixIcons.NotesFill: ImageVector
    get() = MiuixIcons.Regular.NotesFill

val MiuixIcons.Light.NotesFill: ImageVector
    get() {
        if (_notesfillLight != null) return _notesfillLight!!
        _notesfillLight = ImageVector.Builder(
            name = "NotesFill.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1206.0f,
            viewportHeight = 1206.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1206.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(939.0f, 120.5f),
                        PathNode.QuadTo(990.0f, 145.5f, 1016.0f, 197.5f),
                        PathNode.QuadTo(1029.0f, 222.5f, 1032.0f, 261.0f),
                        PathNode.QuadTo(1035.0f, 299.5f, 1035.0f, 391.5f),
                        PathNode.VerticalTo(967.5f),
                        PathNode.QuadTo(1035.0f, 1013.5f, 1034.0f, 1031.5f),
                        PathNode.QuadTo(1033.0f, 1049.5f, 1027.0f, 1061.5f),
                        PathNode.QuadTo(1015.0f, 1084.5f, 991.0f, 1096.5f),
                        PathNode.QuadTo(980.0f, 1102.5f, 962.0f, 1104.0f),
                        PathNode.QuadTo(944.0f, 1105.5f, 898.0f, 1105.5f),
                        PathNode.HorizontalTo(462.0f),
                        PathNode.QuadTo(369.0f, 1105.5f, 330.5f, 1102.5f),
                        PathNode.QuadTo(292.0f, 1099.5f, 267.0f, 1085.5f),
                        PathNode.QuadTo(216.0f, 1060.5f, 190.0f, 1008.5f),
                        PathNode.QuadTo(177.0f, 983.5f, 174.0f, 945.0f),
                        PathNode.QuadTo(171.0f, 906.5f, 171.0f, 814.5f),
                        PathNode.VerticalTo(238.5f),
                        PathNode.QuadTo(171.0f, 192.5f, 172.0f, 174.5f),
                        PathNode.QuadTo(173.0f, 156.5f, 179.0f, 144.5f),
                        PathNode.QuadTo(192.0f, 119.5f, 215.0f, 109.5f),
                        PathNode.QuadTo(226.0f, 103.5f, 244.0f, 102.0f),
                        PathNode.QuadTo(262.0f, 100.5f, 308.0f, 100.5f),
                        PathNode.HorizontalTo(744.0f),
                        PathNode.QuadTo(837.0f, 100.5f, 875.5f, 103.5f),
                        PathNode.QuadTo(914.0f, 106.5f, 939.0f, 120.5f),
                        PathNode.Close,
                        PathNode.MoveTo(359.0f, 597.5f),
                        PathNode.VerticalTo(608.5f),
                        PathNode.QuadTo(359.0f, 627.5f, 377.0f, 627.5f),
                        PathNode.HorizontalTo(830.0f),
                        PathNode.QuadTo(847.0f, 627.5f, 847.0f, 608.5f),
                        PathNode.VerticalTo(597.5f),
                        PathNode.QuadTo(847.0f, 587.5f, 843.0f, 582.5f),
                        PathNode.QuadTo(839.0f, 577.5f, 829.0f, 577.5f),
                        PathNode.HorizontalTo(377.0f),
                        PathNode.QuadTo(368.0f, 577.5f, 363.5f, 582.5f),
                        PathNode.QuadTo(359.0f, 587.5f, 359.0f, 597.5f),
                        PathNode.Close,
                        PathNode.MoveTo(359.0f, 358.5f),
                        PathNode.VerticalTo(370.5f),
                        PathNode.QuadTo(359.0f, 388.5f, 377.0f, 388.5f),
                        PathNode.HorizontalTo(642.0f),
                        PathNode.QuadTo(659.0f, 388.5f, 659.0f, 370.5f),
                        PathNode.VerticalTo(358.5f),
                        PathNode.QuadTo(659.0f, 349.5f, 654.5f, 344.5f),
                        PathNode.QuadTo(650.0f, 339.5f, 641.0f, 339.5f),
                        PathNode.HorizontalTo(377.0f),
                        PathNode.QuadTo(368.0f, 339.5f, 363.5f, 344.0f),
                        PathNode.QuadTo(359.0f, 348.5f, 359.0f, 358.5f),
                        PathNode.Close,
                        PathNode.MoveTo(359.0f, 835.5f),
                        PathNode.VerticalTo(847.5f),
                        PathNode.QuadTo(359.0f, 865.5f, 377.0f, 865.5f),
                        PathNode.HorizontalTo(830.0f),
                        PathNode.QuadTo(847.0f, 865.5f, 847.0f, 847.5f),
                        PathNode.VerticalTo(835.5f),
                        PathNode.QuadTo(847.0f, 826.5f, 842.5f, 821.5f),
                        PathNode.QuadTo(838.0f, 816.5f, 829.0f, 816.5f),
                        PathNode.HorizontalTo(377.0f),
                        PathNode.QuadTo(368.0f, 816.5f, 363.5f, 821.0f),
                        PathNode.QuadTo(359.0f, 825.5f, 359.0f, 835.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notesfillLight!!
    }

private var _notesfillLight: ImageVector? = null

val MiuixIcons.Normal.NotesFill: ImageVector
    get() {
        if (_notesfillNormal != null) return _notesfillNormal!!
        _notesfillNormal = ImageVector.Builder(
            name = "NotesFill.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1224.1f,
            viewportHeight = 1224.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1224.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(950.8f, 122.7f),
                        PathNode.QuadTo(1004.6f, 149.8f, 1031.9f, 203.8f),
                        PathNode.QuadTo(1045.6f, 230.2f, 1049.0f, 269.7f),
                        PathNode.QuadTo(1052.3f, 309.3f, 1052.3f, 401.9f),
                        PathNode.VerticalTo(974.5f),
                        PathNode.QuadTo(1052.3f, 1021.2f, 1051.0f, 1040.2f),
                        PathNode.QuadTo(1049.6f, 1059.3f, 1042.9f, 1072.6f),
                        PathNode.QuadTo(1030.3f, 1097.7f, 1002.8f, 1112.4f),
                        PathNode.QuadTo(989.8f, 1119.1f, 970.7f, 1120.6f),
                        PathNode.QuadTo(951.7f, 1122.1f, 905.0f, 1122.1f),
                        PathNode.HorizontalTo(471.8f),
                        PathNode.QuadTo(378.8f, 1122.1f, 339.2f, 1118.8f),
                        PathNode.QuadTo(299.7f, 1115.4f, 273.3f, 1101.4f),
                        PathNode.QuadTo(219.6f, 1074.4f, 192.2f, 1020.3f),
                        PathNode.QuadTo(178.5f, 993.3f, 175.2f, 954.1f),
                        PathNode.QuadTo(171.8f, 914.9f, 171.8f, 822.2f),
                        PathNode.VerticalTo(249.6f),
                        PathNode.QuadTo(171.8f, 202.9f, 173.2f, 183.9f),
                        PathNode.QuadTo(174.5f, 164.9f, 181.2f, 151.5f),
                        PathNode.QuadTo(194.9f, 124.4f, 221.3f, 111.7f),
                        PathNode.QuadTo(234.4f, 105.0f, 253.8f, 103.5f),
                        PathNode.QuadTo(273.1f, 102.0f, 319.1f, 102.0f),
                        PathNode.HorizontalTo(752.4f),
                        PathNode.QuadTo(845.4f, 102.0f, 884.9f, 105.4f),
                        PathNode.QuadTo(924.4f, 108.7f, 950.8f, 122.7f),
                        PathNode.Close,
                        PathNode.MoveTo(372.2f, 607.3f),
                        PathNode.VerticalTo(617.6f),
                        PathNode.QuadTo(372.2f, 642.8f, 396.4f, 642.8f),
                        PathNode.HorizontalTo(828.8f),
                        PathNode.QuadTo(851.9f, 642.8f, 851.9f, 617.6f),
                        PathNode.VerticalTo(607.3f),
                        PathNode.QuadTo(851.9f, 593.8f, 846.6f, 587.4f),
                        PathNode.QuadTo(841.2f, 581.1f, 827.8f, 581.1f),
                        PathNode.HorizontalTo(396.4f),
                        PathNode.QuadTo(383.9f, 581.1f, 378.1f, 587.4f),
                        PathNode.QuadTo(372.2f, 593.8f, 372.2f, 607.3f),
                        PathNode.Close,
                        PathNode.MoveTo(372.2f, 369.6f),
                        PathNode.VerticalTo(380.3f),
                        PathNode.QuadTo(372.2f, 405.1f, 396.4f, 405.1f),
                        PathNode.HorizontalTo(642.1f),
                        PathNode.QuadTo(665.3f, 405.1f, 665.3f, 380.3f),
                        PathNode.VerticalTo(369.6f),
                        PathNode.QuadTo(665.3f, 356.5f, 659.8f, 350.1f),
                        PathNode.QuadTo(654.3f, 343.8f, 641.1f, 343.8f),
                        PathNode.HorizontalTo(396.4f),
                        PathNode.QuadTo(383.9f, 343.8f, 378.1f, 350.0f),
                        PathNode.QuadTo(372.2f, 356.2f, 372.2f, 369.6f),
                        PathNode.Close,
                        PathNode.MoveTo(372.2f, 843.9f),
                        PathNode.VerticalTo(854.5f),
                        PathNode.QuadTo(372.2f, 879.4f, 396.4f, 879.4f),
                        PathNode.HorizontalTo(828.8f),
                        PathNode.QuadTo(851.9f, 879.4f, 851.9f, 854.5f),
                        PathNode.VerticalTo(843.9f),
                        PathNode.QuadTo(851.9f, 830.8f, 846.4f, 824.4f),
                        PathNode.QuadTo(840.9f, 818.0f, 827.8f, 818.0f),
                        PathNode.HorizontalTo(396.4f),
                        PathNode.QuadTo(383.9f, 818.0f, 378.1f, 824.2f),
                        PathNode.QuadTo(372.2f, 830.4f, 372.2f, 843.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notesfillNormal!!
    }

private var _notesfillNormal: ImageVector? = null

val MiuixIcons.Regular.NotesFill: ImageVector
    get() {
        if (_notesfillRegular != null) return _notesfillRegular!!
        _notesfillRegular = ImageVector.Builder(
            name = "NotesFill.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1232.4f,
            viewportHeight = 1232.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1232.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(956.2f, 123.7f),
                        PathNode.QuadTo(1011.2f, 151.7f, 1039.2f, 206.7f),
                        PathNode.QuadTo(1053.2f, 233.7f, 1056.7f, 273.7f),
                        PathNode.QuadTo(1060.2f, 313.7f, 1060.2f, 406.7f),
                        PathNode.VerticalTo(977.7f),
                        PathNode.QuadTo(1060.2f, 1024.7f, 1058.7f, 1044.2f),
                        PathNode.QuadTo(1057.2f, 1063.7f, 1050.2f, 1077.7f),
                        PathNode.QuadTo(1037.2f, 1103.7f, 1008.2f, 1119.7f),
                        PathNode.QuadTo(994.2f, 1126.7f, 974.7f, 1128.2f),
                        PathNode.QuadTo(955.2f, 1129.7f, 908.2f, 1129.7f),
                        PathNode.HorizontalTo(476.2f),
                        PathNode.QuadTo(383.2f, 1129.7f, 343.2f, 1126.2f),
                        PathNode.QuadTo(303.2f, 1122.7f, 276.2f, 1108.7f),
                        PathNode.QuadTo(221.2f, 1080.7f, 193.2f, 1025.7f),
                        PathNode.QuadTo(179.2f, 997.7f, 175.7f, 958.2f),
                        PathNode.QuadTo(172.2f, 918.7f, 172.2f, 825.7f),
                        PathNode.VerticalTo(254.7f),
                        PathNode.QuadTo(172.2f, 207.7f, 173.7f, 188.2f),
                        PathNode.QuadTo(175.2f, 168.7f, 182.2f, 154.7f),
                        PathNode.QuadTo(196.2f, 126.7f, 224.2f, 112.7f),
                        PathNode.QuadTo(238.2f, 105.7f, 258.2f, 104.2f),
                        PathNode.QuadTo(278.2f, 102.7f, 324.2f, 102.7f),
                        PathNode.HorizontalTo(756.2f),
                        PathNode.QuadTo(849.2f, 102.7f, 889.2f, 106.2f),
                        PathNode.QuadTo(929.2f, 109.7f, 956.2f, 123.7f),
                        PathNode.Close,
                        PathNode.MoveTo(378.2f, 611.7f),
                        PathNode.VerticalTo(621.7f),
                        PathNode.QuadTo(378.2f, 649.7f, 405.2f, 649.7f),
                        PathNode.HorizontalTo(828.2f),
                        PathNode.QuadTo(854.2f, 649.7f, 854.2f, 621.7f),
                        PathNode.VerticalTo(611.7f),
                        PathNode.QuadTo(854.2f, 596.7f, 848.2f, 589.7f),
                        PathNode.QuadTo(842.2f, 582.7f, 827.2f, 582.7f),
                        PathNode.HorizontalTo(405.2f),
                        PathNode.QuadTo(391.2f, 582.7f, 384.7f, 589.7f),
                        PathNode.QuadTo(378.2f, 596.7f, 378.2f, 611.7f),
                        PathNode.Close,
                        PathNode.MoveTo(378.2f, 374.7f),
                        PathNode.VerticalTo(384.7f),
                        PathNode.QuadTo(378.2f, 412.7f, 405.2f, 412.7f),
                        PathNode.HorizontalTo(642.2f),
                        PathNode.QuadTo(668.2f, 412.7f, 668.2f, 384.7f),
                        PathNode.VerticalTo(374.7f),
                        PathNode.QuadTo(668.2f, 359.7f, 662.2f, 352.7f),
                        PathNode.QuadTo(656.2f, 345.7f, 641.2f, 345.7f),
                        PathNode.HorizontalTo(405.2f),
                        PathNode.QuadTo(391.2f, 345.7f, 384.7f, 352.7f),
                        PathNode.QuadTo(378.2f, 359.7f, 378.2f, 374.7f),
                        PathNode.Close,
                        PathNode.MoveTo(378.2f, 847.7f),
                        PathNode.VerticalTo(857.7f),
                        PathNode.QuadTo(378.2f, 885.7f, 405.2f, 885.7f),
                        PathNode.HorizontalTo(828.2f),
                        PathNode.QuadTo(854.2f, 885.7f, 854.2f, 857.7f),
                        PathNode.VerticalTo(847.7f),
                        PathNode.QuadTo(854.2f, 832.7f, 848.2f, 825.7f),
                        PathNode.QuadTo(842.2f, 818.7f, 827.2f, 818.7f),
                        PathNode.HorizontalTo(405.2f),
                        PathNode.QuadTo(391.2f, 818.7f, 384.7f, 825.7f),
                        PathNode.QuadTo(378.2f, 832.7f, 378.2f, 847.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notesfillRegular!!
    }

private var _notesfillRegular: ImageVector? = null

val MiuixIcons.Medium.NotesFill: ImageVector
    get() {
        if (_notesfillMedium != null) return _notesfillMedium!!
        _notesfillMedium = ImageVector.Builder(
            name = "NotesFill.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1253.6f,
            viewportHeight = 1253.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1253.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(970.3f, 126.6f),
                        PathNode.QuadTo(1028.8f, 156.4f, 1057.4f, 214.3f),
                        PathNode.QuadTo(1072.6f, 243.1f, 1076.1f, 284.0f),
                        PathNode.QuadTo(1079.6f, 324.9f, 1079.6f, 418.5f),
                        PathNode.VerticalTo(986.5f),
                        PathNode.QuadTo(1079.6f, 1033.5f, 1077.8f, 1054.5f),
                        PathNode.QuadTo(1076.0f, 1075.5f, 1068.4f, 1091.2f),
                        PathNode.QuadTo(1054.8f, 1119.6f, 1022.3f, 1137.3f),
                        PathNode.QuadTo(1006.6f, 1145.5f, 985.6f, 1147.3f),
                        PathNode.QuadTo(964.6f, 1149.1f, 917.0f, 1149.1f),
                        PathNode.HorizontalTo(487.4f),
                        PathNode.QuadTo(394.4f, 1149.1f, 353.2f, 1145.6f),
                        PathNode.QuadTo(312.0f, 1142.1f, 283.3f, 1126.9f),
                        PathNode.QuadTo(224.7f, 1097.2f, 196.1f, 1039.2f),
                        PathNode.QuadTo(181.0f, 1009.5f, 177.5f, 968.8f),
                        PathNode.QuadTo(174.0f, 928.1f, 174.0f, 835.1f),
                        PathNode.VerticalTo(267.1f),
                        PathNode.QuadTo(174.0f, 220.1f, 175.8f, 199.1f),
                        PathNode.QuadTo(177.6f, 178.1f, 185.1f, 162.3f),
                        PathNode.QuadTo(199.7f, 132.6f, 231.3f, 115.6f),
                        PathNode.QuadTo(247.0f, 108.1f, 268.5f, 106.3f),
                        PathNode.QuadTo(290.0f, 104.5f, 336.6f, 104.5f),
                        PathNode.HorizontalTo(766.2f),
                        PathNode.QuadTo(859.2f, 104.5f, 900.4f, 108.0f),
                        PathNode.QuadTo(941.6f, 111.5f, 970.3f, 126.6f),
                        PathNode.Close,
                        PathNode.MoveTo(390.6f, 619.9f),
                        PathNode.VerticalTo(634.1f),
                        PathNode.QuadTo(390.6f, 667.3f, 423.4f, 667.3f),
                        PathNode.HorizontalTo(828.2f),
                        PathNode.QuadTo(860.1f, 667.3f, 860.1f, 634.1f),
                        PathNode.VerticalTo(619.9f),
                        PathNode.QuadTo(860.1f, 603.2f, 852.3f, 594.7f),
                        PathNode.QuadTo(844.6f, 586.2f, 827.8f, 586.2f),
                        PathNode.HorizontalTo(423.4f),
                        PathNode.QuadTo(407.1f, 586.2f, 398.8f, 594.4f),
                        PathNode.QuadTo(390.6f, 602.6f, 390.6f, 619.9f),
                        PathNode.Close,
                        PathNode.MoveTo(390.6f, 384.7f),
                        PathNode.VerticalTo(398.2f),
                        PathNode.QuadTo(390.6f, 431.5f, 423.4f, 431.5f),
                        PathNode.HorizontalTo(642.8f),
                        PathNode.QuadTo(675.3f, 431.5f, 675.3f, 398.2f),
                        PathNode.VerticalTo(384.7f),
                        PathNode.QuadTo(675.3f, 367.3f, 667.5f, 358.9f),
                        PathNode.QuadTo(659.7f, 350.4f, 642.4f, 350.4f),
                        PathNode.HorizontalTo(423.4f),
                        PathNode.QuadTo(407.1f, 350.4f, 398.8f, 358.9f),
                        PathNode.QuadTo(390.6f, 367.3f, 390.6f, 384.7f),
                        PathNode.Close,
                        PathNode.MoveTo(390.6f, 854.8f),
                        PathNode.VerticalTo(868.9f),
                        PathNode.QuadTo(390.6f, 902.2f, 423.4f, 902.2f),
                        PathNode.HorizontalTo(828.2f),
                        PathNode.QuadTo(860.1f, 902.2f, 860.1f, 868.9f),
                        PathNode.VerticalTo(854.8f),
                        PathNode.QuadTo(860.1f, 838.0f, 852.3f, 829.5f),
                        PathNode.QuadTo(844.6f, 821.1f, 827.8f, 821.1f),
                        PathNode.HorizontalTo(423.4f),
                        PathNode.QuadTo(407.1f, 821.1f, 398.8f, 829.2f),
                        PathNode.QuadTo(390.6f, 837.4f, 390.6f, 854.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notesfillMedium!!
    }

private var _notesfillMedium: ImageVector? = null

val MiuixIcons.Demibold.NotesFill: ImageVector
    get() {
        if (_notesfillDemibold != null) return _notesfillDemibold!!
        _notesfillDemibold = ImageVector.Builder(
            name = "NotesFill.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1268.4f,
            viewportHeight = 1268.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1268.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(980.2f, 128.7f),
                        PathNode.QuadTo(1041.2f, 159.7f, 1070.2f, 219.7f),
                        PathNode.QuadTo(1086.2f, 249.7f, 1089.7f, 291.2f),
                        PathNode.QuadTo(1093.2f, 332.7f, 1093.2f, 426.7f),
                        PathNode.VerticalTo(992.7f),
                        PathNode.QuadTo(1093.2f, 1039.7f, 1091.2f, 1061.7f),
                        PathNode.QuadTo(1089.2f, 1083.7f, 1081.2f, 1100.7f),
                        PathNode.QuadTo(1067.2f, 1130.7f, 1032.2f, 1149.7f),
                        PathNode.QuadTo(1015.2f, 1158.7f, 993.2f, 1160.7f),
                        PathNode.QuadTo(971.2f, 1162.7f, 923.2f, 1162.7f),
                        PathNode.HorizontalTo(495.2f),
                        PathNode.QuadTo(402.2f, 1162.7f, 360.2f, 1159.2f),
                        PathNode.QuadTo(318.2f, 1155.7f, 288.2f, 1139.7f),
                        PathNode.QuadTo(227.2f, 1108.7f, 198.2f, 1048.7f),
                        PathNode.QuadTo(182.2f, 1017.7f, 178.7f, 976.2f),
                        PathNode.QuadTo(175.2f, 934.7f, 175.2f, 841.7f),
                        PathNode.VerticalTo(275.7f),
                        PathNode.QuadTo(175.2f, 228.7f, 177.2f, 206.7f),
                        PathNode.QuadTo(179.2f, 184.7f, 187.2f, 167.7f),
                        PathNode.QuadTo(202.2f, 136.7f, 236.2f, 117.7f),
                        PathNode.QuadTo(253.2f, 109.7f, 275.7f, 107.7f),
                        PathNode.QuadTo(298.2f, 105.7f, 345.2f, 105.7f),
                        PathNode.HorizontalTo(773.2f),
                        PathNode.QuadTo(866.2f, 105.7f, 908.2f, 109.2f),
                        PathNode.QuadTo(950.2f, 112.7f, 980.2f, 128.7f),
                        PathNode.Close,
                        PathNode.MoveTo(399.2f, 625.7f),
                        PathNode.VerticalTo(642.7f),
                        PathNode.QuadTo(399.2f, 679.7f, 436.2f, 679.7f),
                        PathNode.HorizontalTo(828.2f),
                        PathNode.QuadTo(864.2f, 679.7f, 864.2f, 642.7f),
                        PathNode.VerticalTo(625.7f),
                        PathNode.QuadTo(864.2f, 607.7f, 855.2f, 598.2f),
                        PathNode.QuadTo(846.2f, 588.7f, 828.2f, 588.7f),
                        PathNode.HorizontalTo(436.2f),
                        PathNode.QuadTo(418.2f, 588.7f, 408.7f, 597.7f),
                        PathNode.QuadTo(399.2f, 606.7f, 399.2f, 625.7f),
                        PathNode.Close,
                        PathNode.MoveTo(399.2f, 391.7f),
                        PathNode.VerticalTo(407.7f),
                        PathNode.QuadTo(399.2f, 444.7f, 436.2f, 444.7f),
                        PathNode.HorizontalTo(643.2f),
                        PathNode.QuadTo(680.2f, 444.7f, 680.2f, 407.7f),
                        PathNode.VerticalTo(391.7f),
                        PathNode.QuadTo(680.2f, 372.7f, 671.2f, 363.2f),
                        PathNode.QuadTo(662.2f, 353.7f, 643.2f, 353.7f),
                        PathNode.HorizontalTo(436.2f),
                        PathNode.QuadTo(418.2f, 353.7f, 408.7f, 363.2f),
                        PathNode.QuadTo(399.2f, 372.7f, 399.2f, 391.7f),
                        PathNode.Close,
                        PathNode.MoveTo(399.2f, 859.7f),
                        PathNode.VerticalTo(876.7f),
                        PathNode.QuadTo(399.2f, 913.7f, 436.2f, 913.7f),
                        PathNode.HorizontalTo(828.2f),
                        PathNode.QuadTo(864.2f, 913.7f, 864.2f, 876.7f),
                        PathNode.VerticalTo(859.7f),
                        PathNode.QuadTo(864.2f, 841.7f, 855.2f, 832.2f),
                        PathNode.QuadTo(846.2f, 822.7f, 828.2f, 822.7f),
                        PathNode.HorizontalTo(436.2f),
                        PathNode.QuadTo(418.2f, 822.7f, 408.7f, 831.7f),
                        PathNode.QuadTo(399.2f, 840.7f, 399.2f, 859.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notesfillDemibold!!
    }

private var _notesfillDemibold: ImageVector? = null
