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
                        PathNode.MoveTo(926.7f, 137.8f),
                        PathNode.QuadTo(976.1f, 162.1f, 1001.3f, 212.4f),
                        PathNode.QuadTo(1013.9f, 236.7f, 1016.8f, 273.8f),
                        PathNode.QuadTo(1019.8f, 311.0f, 1019.8f, 399.6f),
                        PathNode.VerticalTo(953.5f),
                        PathNode.QuadTo(1019.8f, 997.9f, 1018.8f, 1015.3f),
                        PathNode.QuadTo(1017.7f, 1032.8f, 1011.9f, 1044.5f),
                        PathNode.QuadTo(1000.3f, 1066.9f, 976.7f, 1078.8f),
                        PathNode.QuadTo(965.9f, 1084.6f, 948.5f, 1086.1f),
                        PathNode.QuadTo(931.0f, 1087.5f, 886.7f, 1087.5f),
                        PathNode.HorizontalTo(467.4f),
                        PathNode.QuadTo(377.9f, 1087.5f, 340.7f, 1084.6f),
                        PathNode.QuadTo(303.5f, 1081.7f, 279.3f, 1068.2f),
                        PathNode.QuadTo(229.9f, 1043.9f, 204.7f, 993.6f),
                        PathNode.QuadTo(192.1f, 969.3f, 189.2f, 932.1f),
                        PathNode.QuadTo(186.2f, 895.0f, 186.2f, 806.4f),
                        PathNode.VerticalTo(252.5f),
                        PathNode.QuadTo(186.2f, 208.1f, 187.3f, 190.7f),
                        PathNode.QuadTo(188.3f, 173.2f, 194.1f, 161.5f),
                        PathNode.QuadTo(206.7f, 137.2f, 229.3f, 127.2f),
                        PathNode.QuadTo(240.1f, 121.4f, 257.6f, 119.9f),
                        PathNode.QuadTo(275.1f, 118.5f, 319.3f, 118.5f),
                        PathNode.HorizontalTo(738.6f),
                        PathNode.QuadTo(828.1f, 118.5f, 865.3f, 121.4f),
                        PathNode.QuadTo(902.5f, 124.3f, 926.7f, 137.8f),
                        PathNode.Close,
                        PathNode.MoveTo(368.7f, 597.8f),
                        PathNode.VerticalTo(608.3f),
                        PathNode.QuadTo(368.7f, 627.3f, 386.8f, 627.3f),
                        PathNode.HorizontalTo(820.2f),
                        PathNode.QuadTo(837.3f, 627.3f, 837.3f, 608.3f),
                        PathNode.VerticalTo(597.8f),
                        PathNode.QuadTo(837.3f, 587.7f, 833.3f, 582.8f),
                        PathNode.QuadTo(829.3f, 577.8f, 819.2f, 577.8f),
                        PathNode.HorizontalTo(386.8f),
                        PathNode.QuadTo(377.7f, 577.8f, 373.2f, 582.8f),
                        PathNode.QuadTo(368.7f, 587.7f, 368.7f, 597.8f),
                        PathNode.Close,
                        PathNode.MoveTo(368.7f, 367.9f),
                        PathNode.VerticalTo(379.3f),
                        PathNode.QuadTo(368.7f, 397.5f, 386.8f, 397.5f),
                        PathNode.HorizontalTo(639.4f),
                        PathNode.QuadTo(656.6f, 397.5f, 656.6f, 379.3f),
                        PathNode.VerticalTo(367.9f),
                        PathNode.QuadTo(656.6f, 358.8f, 652.1f, 353.8f),
                        PathNode.QuadTo(647.7f, 348.8f, 638.5f, 348.8f),
                        PathNode.HorizontalTo(386.8f),
                        PathNode.QuadTo(377.7f, 348.8f, 373.2f, 353.4f),
                        PathNode.QuadTo(368.7f, 357.9f, 368.7f, 367.9f),
                        PathNode.Close,
                        PathNode.MoveTo(368.7f, 826.7f),
                        PathNode.VerticalTo(838.1f),
                        PathNode.QuadTo(368.7f, 856.2f, 386.8f, 856.2f),
                        PathNode.HorizontalTo(820.2f),
                        PathNode.QuadTo(837.3f, 856.2f, 837.3f, 838.1f),
                        PathNode.VerticalTo(826.7f),
                        PathNode.QuadTo(837.3f, 817.5f, 832.9f, 812.5f),
                        PathNode.QuadTo(828.4f, 807.5f, 819.2f, 807.5f),
                        PathNode.HorizontalTo(386.8f),
                        PathNode.QuadTo(377.7f, 807.5f, 373.2f, 812.1f),
                        PathNode.QuadTo(368.7f, 816.6f, 368.7f, 826.7f),
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
                        PathNode.MoveTo(938.3f, 140.3f),
                        PathNode.QuadTo(990.5f, 166.7f, 1017.0f, 219.0f),
                        PathNode.QuadTo(1030.3f, 244.6f, 1033.6f, 282.7f),
                        PathNode.QuadTo(1036.8f, 321.0f, 1036.8f, 410.2f),
                        PathNode.VerticalTo(960.3f),
                        PathNode.QuadTo(1036.8f, 1005.3f, 1035.5f, 1023.8f),
                        PathNode.QuadTo(1034.1f, 1042.3f, 1027.6f, 1055.3f),
                        PathNode.QuadTo(1015.3f, 1079.8f, 988.3f, 1094.4f),
                        PathNode.QuadTo(975.5f, 1101.0f, 956.9f, 1102.4f),
                        PathNode.QuadTo(938.5f, 1103.8f, 893.5f, 1103.8f),
                        PathNode.HorizontalTo(477.3f),
                        PathNode.QuadTo(387.8f, 1103.8f, 349.6f, 1100.6f),
                        PathNode.QuadTo(311.4f, 1097.3f, 285.8f, 1083.8f),
                        PathNode.QuadTo(233.7f, 1057.5f, 207.1f, 1005.1f),
                        PathNode.QuadTo(193.8f, 978.8f, 190.6f, 941.0f),
                        PathNode.QuadTo(187.3f, 903.2f, 187.3f, 813.9f),
                        PathNode.VerticalTo(263.8f),
                        PathNode.QuadTo(187.3f, 218.8f, 188.7f, 200.3f),
                        PathNode.QuadTo(190.0f, 181.9f, 196.5f, 168.8f),
                        PathNode.QuadTo(209.8f, 142.4f, 235.8f, 129.7f),
                        PathNode.QuadTo(248.7f, 123.1f, 267.6f, 121.7f),
                        PathNode.QuadTo(286.4f, 120.3f, 330.6f, 120.3f),
                        PathNode.HorizontalTo(746.9f),
                        PathNode.QuadTo(836.4f, 120.3f, 874.5f, 123.6f),
                        PathNode.QuadTo(912.7f, 126.8f, 938.3f, 140.3f),
                        PathNode.Close,
                        PathNode.MoveTo(382.0f, 607.6f),
                        PathNode.VerticalTo(617.4f),
                        PathNode.QuadTo(382.0f, 642.6f, 406.3f, 642.6f),
                        PathNode.HorizontalTo(818.8f),
                        PathNode.QuadTo(842.1f, 642.6f, 842.1f, 617.4f),
                        PathNode.VerticalTo(607.6f),
                        PathNode.QuadTo(842.1f, 594.0f, 836.7f, 587.7f),
                        PathNode.QuadTo(831.3f, 581.4f, 817.8f, 581.4f),
                        PathNode.HorizontalTo(406.3f),
                        PathNode.QuadTo(393.8f, 581.4f, 387.9f, 587.7f),
                        PathNode.QuadTo(382.0f, 594.0f, 382.0f, 607.6f),
                        PathNode.Close,
                        PathNode.MoveTo(382.0f, 379.2f),
                        PathNode.VerticalTo(389.2f),
                        PathNode.QuadTo(382.0f, 414.3f, 406.3f, 414.3f),
                        PathNode.HorizontalTo(639.5f),
                        PathNode.QuadTo(662.8f, 414.3f, 662.8f, 389.2f),
                        PathNode.VerticalTo(379.2f),
                        PathNode.QuadTo(662.8f, 365.9f, 657.3f, 359.6f),
                        PathNode.QuadTo(651.9f, 353.2f, 638.5f, 353.2f),
                        PathNode.HorizontalTo(406.3f),
                        PathNode.QuadTo(393.8f, 353.2f, 387.9f, 359.5f),
                        PathNode.QuadTo(382.0f, 365.7f, 382.0f, 379.2f),
                        PathNode.Close,
                        PathNode.MoveTo(382.0f, 834.9f),
                        PathNode.VerticalTo(844.9f),
                        PathNode.QuadTo(382.0f, 870.0f, 406.3f, 870.0f),
                        PathNode.HorizontalTo(818.8f),
                        PathNode.QuadTo(842.1f, 870.0f, 842.1f, 844.9f),
                        PathNode.VerticalTo(834.9f),
                        PathNode.QuadTo(842.1f, 821.6f, 836.6f, 815.3f),
                        PathNode.QuadTo(831.1f, 808.9f, 817.8f, 808.9f),
                        PathNode.HorizontalTo(406.3f),
                        PathNode.QuadTo(393.8f, 808.9f, 387.9f, 815.1f),
                        PathNode.QuadTo(382.0f, 821.4f, 382.0f, 834.9f),
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
                        PathNode.MoveTo(943.5f, 141.5f),
                        PathNode.QuadTo(997.1f, 168.8f, 1024.1f, 222.2f),
                        PathNode.QuadTo(1037.8f, 248.6f, 1041.2f, 287.2f),
                        PathNode.QuadTo(1044.6f, 325.8f, 1044.6f, 415.3f),
                        PathNode.VerticalTo(963.0f),
                        PathNode.QuadTo(1044.6f, 1008.1f, 1043.1f, 1027.2f),
                        PathNode.QuadTo(1041.6f, 1046.3f, 1034.7f, 1060.1f),
                        PathNode.QuadTo(1022.1f, 1085.6f, 993.5f, 1101.3f),
                        PathNode.QuadTo(979.6f, 1108.3f, 960.6f, 1109.8f),
                        PathNode.QuadTo(941.5f, 1111.3f, 896.3f, 1111.3f),
                        PathNode.HorizontalTo(481.9f),
                        PathNode.QuadTo(392.6f, 1111.3f, 353.9f, 1108.0f),
                        PathNode.QuadTo(315.2f, 1104.6f, 288.9f, 1090.9f),
                        PathNode.QuadTo(235.3f, 1063.6f, 208.2f, 1010.1f),
                        PathNode.QuadTo(194.6f, 982.9f, 191.2f, 944.7f),
                        PathNode.QuadTo(187.8f, 906.5f, 187.8f, 817.1f),
                        PathNode.VerticalTo(269.4f),
                        PathNode.QuadTo(187.8f, 224.3f, 189.3f, 205.2f),
                        PathNode.QuadTo(190.8f, 186.1f, 197.7f, 172.3f),
                        PathNode.QuadTo(211.3f, 145.0f, 238.9f, 130.9f),
                        PathNode.QuadTo(252.8f, 124.1f, 272.3f, 122.6f),
                        PathNode.QuadTo(291.8f, 121.1f, 336.1f, 121.1f),
                        PathNode.HorizontalTo(750.5f),
                        PathNode.QuadTo(839.8f, 121.1f, 878.5f, 124.4f),
                        PathNode.QuadTo(917.2f, 127.8f, 943.5f, 141.5f),
                        PathNode.Close,
                        PathNode.MoveTo(388.0f, 611.4f),
                        PathNode.VerticalTo(621.9f),
                        PathNode.QuadTo(388.0f, 649.9f, 415.2f, 649.9f),
                        PathNode.HorizontalTo(817.5f),
                        PathNode.QuadTo(843.8f, 649.9f, 843.8f, 621.9f),
                        PathNode.VerticalTo(611.4f),
                        PathNode.QuadTo(843.8f, 596.6f, 837.6f, 589.5f),
                        PathNode.QuadTo(831.5f, 582.5f, 816.7f, 582.5f),
                        PathNode.HorizontalTo(415.2f),
                        PathNode.QuadTo(401.3f, 582.5f, 394.6f, 589.5f),
                        PathNode.QuadTo(388.0f, 596.4f, 388.0f, 611.4f),
                        PathNode.Close,
                        PathNode.MoveTo(388.0f, 384.1f),
                        PathNode.VerticalTo(394.5f),
                        PathNode.QuadTo(388.0f, 422.6f, 415.2f, 422.6f),
                        PathNode.HorizontalTo(639.0f),
                        PathNode.QuadTo(665.4f, 422.6f, 665.4f, 394.5f),
                        PathNode.VerticalTo(384.1f),
                        PathNode.QuadTo(665.4f, 369.2f, 659.2f, 362.2f),
                        PathNode.QuadTo(653.1f, 355.1f, 638.1f, 355.1f),
                        PathNode.HorizontalTo(415.2f),
                        PathNode.QuadTo(401.3f, 355.1f, 394.6f, 362.2f),
                        PathNode.QuadTo(388.0f, 369.2f, 388.0f, 384.1f),
                        PathNode.Close,
                        PathNode.MoveTo(388.0f, 837.8f),
                        PathNode.VerticalTo(848.3f),
                        PathNode.QuadTo(388.0f, 876.3f, 415.2f, 876.3f),
                        PathNode.HorizontalTo(817.5f),
                        PathNode.QuadTo(843.8f, 876.3f, 843.8f, 848.3f),
                        PathNode.VerticalTo(837.8f),
                        PathNode.QuadTo(843.8f, 823.0f, 837.6f, 815.9f),
                        PathNode.QuadTo(831.5f, 808.9f, 816.7f, 808.9f),
                        PathNode.HorizontalTo(415.2f),
                        PathNode.QuadTo(401.3f, 808.9f, 394.6f, 815.9f),
                        PathNode.QuadTo(388.0f, 822.8f, 388.0f, 837.8f),
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
                        PathNode.MoveTo(957.4f, 144.7f),
                        PathNode.QuadTo(1014.5f, 173.8f, 1042.1f, 230.1f),
                        PathNode.QuadTo(1057.0f, 258.2f, 1060.4f, 297.7f),
                        PathNode.QuadTo(1063.7f, 337.2f, 1063.7f, 427.2f),
                        PathNode.VerticalTo(971.6f),
                        PathNode.QuadTo(1063.7f, 1016.7f, 1061.9f, 1037.2f),
                        PathNode.QuadTo(1060.1f, 1057.7f, 1052.7f, 1073.3f),
                        PathNode.QuadTo(1039.5f, 1101.2f, 1007.3f, 1118.6f),
                        PathNode.QuadTo(991.8f, 1126.8f, 971.2f, 1128.6f),
                        PathNode.QuadTo(950.7f, 1130.4f, 904.9f, 1130.4f),
                        PathNode.HorizontalTo(493.2f),
                        PathNode.QuadTo(403.9f, 1130.4f, 364.1f, 1127.1f),
                        PathNode.QuadTo(324.2f, 1123.7f, 296.2f, 1108.8f),
                        PathNode.QuadTo(239.0f, 1079.8f, 211.4f, 1023.4f),
                        PathNode.QuadTo(196.6f, 994.4f, 193.2f, 955.0f),
                        PathNode.QuadTo(189.9f, 915.7f, 189.9f, 826.4f),
                        PathNode.VerticalTo(282.0f),
                        PathNode.QuadTo(189.9f, 236.9f, 191.7f, 216.4f),
                        PathNode.QuadTo(193.5f, 195.9f, 200.9f, 180.2f),
                        PathNode.QuadTo(215.0f, 151.2f, 246.3f, 134.2f),
                        PathNode.QuadTo(261.8f, 126.8f, 282.8f, 125.0f),
                        PathNode.QuadTo(303.9f, 123.2f, 348.7f, 123.2f),
                        PathNode.HorizontalTo(760.4f),
                        PathNode.QuadTo(849.7f, 123.2f, 889.5f, 126.5f),
                        PathNode.QuadTo(929.4f, 129.9f, 957.4f, 144.7f),
                        PathNode.Close,
                        PathNode.MoveTo(400.6f, 619.6f),
                        PathNode.VerticalTo(634.3f),
                        PathNode.QuadTo(400.6f, 667.6f, 433.6f, 667.6f),
                        PathNode.HorizontalTo(817.3f),
                        PathNode.QuadTo(849.5f, 667.6f, 849.5f, 634.3f),
                        PathNode.VerticalTo(619.6f),
                        PathNode.QuadTo(849.5f, 603.1f, 841.5f, 594.5f),
                        PathNode.QuadTo(833.6f, 586.0f, 817.1f, 586.0f),
                        PathNode.HorizontalTo(433.6f),
                        PathNode.QuadTo(417.3f, 586.0f, 408.9f, 594.2f),
                        PathNode.QuadTo(400.6f, 602.3f, 400.6f, 619.6f),
                        PathNode.Close,
                        PathNode.MoveTo(400.6f, 394.3f),
                        PathNode.VerticalTo(408.2f),
                        PathNode.QuadTo(400.6f, 441.5f, 433.6f, 441.5f),
                        PathNode.HorizontalTo(639.5f),
                        PathNode.QuadTo(672.4f, 441.5f, 672.4f, 408.2f),
                        PathNode.VerticalTo(394.3f),
                        PathNode.QuadTo(672.4f, 377.0f, 664.5f, 368.5f),
                        PathNode.QuadTo(656.5f, 360.0f, 639.3f, 360.0f),
                        PathNode.HorizontalTo(433.6f),
                        PathNode.QuadTo(417.3f, 360.0f, 408.9f, 368.5f),
                        PathNode.QuadTo(400.6f, 377.0f, 400.6f, 394.3f),
                        PathNode.Close,
                        PathNode.MoveTo(400.6f, 844.7f),
                        PathNode.VerticalTo(859.3f),
                        PathNode.QuadTo(400.6f, 892.7f, 433.6f, 892.7f),
                        PathNode.HorizontalTo(817.3f),
                        PathNode.QuadTo(849.5f, 892.7f, 849.5f, 859.3f),
                        PathNode.VerticalTo(844.7f),
                        PathNode.QuadTo(849.5f, 828.1f, 841.5f, 819.6f),
                        PathNode.QuadTo(833.6f, 811.1f, 817.1f, 811.1f),
                        PathNode.HorizontalTo(433.6f),
                        PathNode.QuadTo(417.3f, 811.1f, 408.9f, 819.2f),
                        PathNode.QuadTo(400.6f, 827.4f, 400.6f, 844.7f),
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
                        PathNode.MoveTo(967.2f, 147.0f),
                        PathNode.QuadTo(1026.7f, 177.3f, 1054.7f, 235.7f),
                        PathNode.QuadTo(1070.4f, 265.0f, 1073.8f, 305.1f),
                        PathNode.QuadTo(1077.1f, 345.1f, 1077.1f, 435.5f),
                        PathNode.VerticalTo(977.6f),
                        PathNode.QuadTo(1077.1f, 1022.7f, 1075.1f, 1044.2f),
                        PathNode.QuadTo(1073.1f, 1065.7f, 1065.3f, 1082.6f),
                        PathNode.QuadTo(1051.7f, 1112.0f, 1017.1f, 1130.8f),
                        PathNode.QuadTo(1000.2f, 1139.8f, 978.7f, 1141.8f),
                        PathNode.QuadTo(957.1f, 1143.8f, 910.9f, 1143.8f),
                        PathNode.HorizontalTo(501.0f),
                        PathNode.QuadTo(411.8f, 1143.8f, 371.2f, 1140.4f),
                        PathNode.QuadTo(330.6f, 1137.1f, 301.2f, 1121.4f),
                        PathNode.QuadTo(241.7f, 1091.1f, 213.7f, 1032.7f),
                        PathNode.QuadTo(198.0f, 1002.4f, 194.6f, 962.3f),
                        PathNode.QuadTo(191.3f, 922.1f, 191.3f, 832.9f),
                        PathNode.VerticalTo(290.8f),
                        PathNode.QuadTo(191.3f, 245.7f, 193.3f, 224.2f),
                        PathNode.QuadTo(195.3f, 202.7f, 203.2f, 185.9f),
                        PathNode.QuadTo(217.7f, 155.6f, 251.3f, 136.5f),
                        PathNode.QuadTo(268.2f, 128.6f, 290.2f, 126.6f),
                        PathNode.QuadTo(312.2f, 124.6f, 357.5f, 124.6f),
                        PathNode.HorizontalTo(767.4f),
                        PathNode.QuadTo(856.6f, 124.6f, 897.2f, 128.0f),
                        PathNode.QuadTo(937.8f, 131.3f, 967.2f, 147.0f),
                        PathNode.Close,
                        PathNode.MoveTo(409.3f, 625.4f),
                        PathNode.VerticalTo(642.9f),
                        PathNode.QuadTo(409.3f, 680.0f, 446.5f, 680.0f),
                        PathNode.HorizontalTo(817.2f),
                        PathNode.QuadTo(853.4f, 680.0f, 853.4f, 642.9f),
                        PathNode.VerticalTo(625.4f),
                        PathNode.QuadTo(853.4f, 607.6f, 844.3f, 598.0f),
                        PathNode.QuadTo(835.1f, 588.5f, 817.3f, 588.5f),
                        PathNode.HorizontalTo(446.5f),
                        PathNode.QuadTo(428.6f, 588.5f, 418.9f, 597.5f),
                        PathNode.QuadTo(409.3f, 606.4f, 409.3f, 625.4f),
                        PathNode.Close,
                        PathNode.MoveTo(409.3f, 401.4f),
                        PathNode.VerticalTo(417.8f),
                        PathNode.QuadTo(409.3f, 454.9f, 446.5f, 454.9f),
                        PathNode.HorizontalTo(639.9f),
                        PathNode.QuadTo(677.3f, 454.9f, 677.3f, 417.8f),
                        PathNode.VerticalTo(401.4f),
                        PathNode.QuadTo(677.3f, 382.5f, 668.1f, 372.9f),
                        PathNode.QuadTo(659.0f, 363.4f, 640.0f, 363.4f),
                        PathNode.HorizontalTo(446.5f),
                        PathNode.QuadTo(428.6f, 363.4f, 418.9f, 372.9f),
                        PathNode.QuadTo(409.3f, 382.5f, 409.3f, 401.4f),
                        PathNode.Close,
                        PathNode.MoveTo(409.3f, 849.5f),
                        PathNode.VerticalTo(867.0f),
                        PathNode.QuadTo(409.3f, 904.1f, 446.5f, 904.1f),
                        PathNode.HorizontalTo(817.2f),
                        PathNode.QuadTo(853.4f, 904.1f, 853.4f, 867.0f),
                        PathNode.VerticalTo(849.5f),
                        PathNode.QuadTo(853.4f, 831.7f, 844.3f, 822.2f),
                        PathNode.QuadTo(835.1f, 812.6f, 817.3f, 812.6f),
                        PathNode.HorizontalTo(446.5f),
                        PathNode.QuadTo(428.6f, 812.6f, 418.9f, 821.6f),
                        PathNode.QuadTo(409.3f, 830.6f, 409.3f, 849.5f),
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
