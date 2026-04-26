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

val MiuixIcons.Favorites: ImageVector
    get() = MiuixIcons.Regular.Favorites

val MiuixIcons.Light.Favorites: ImageVector
    get() {
        if (_favoritesLight != null) return _favoritesLight!!
        _favoritesLight = ImageVector.Builder(
            name = "Favorites.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1290.0f,
            viewportHeight = 1290.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1290.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(682.5f, 1058.0f),
                        PathNode.LineTo(674.5f, 1051.0f),
                        PathNode.LineTo(656.5f, 1033.0f),
                        PathNode.QuadTo(644.5f, 1020.0f, 634.5f, 1032.0f),
                        PathNode.QuadTo(620.5f, 1049.0f, 608.5f, 1058.0f),
                        PathNode.QuadTo(567.5f, 1092.0f, 518.0f, 1110.0f),
                        PathNode.QuadTo(468.5f, 1128.0f, 413.5f, 1128.0f),
                        PathNode.QuadTo(329.5f, 1128.0f, 259.5f, 1087.0f),
                        PathNode.QuadTo(189.5f, 1046.0f, 148.5f, 976.0f),
                        PathNode.QuadTo(107.5f, 906.0f, 107.5f, 823.0f),
                        PathNode.QuadTo(107.5f, 736.0f, 152.5f, 663.0f),
                        PathNode.QuadTo(208.5f, 568.0f, 337.0f, 438.0f),
                        PathNode.QuadTo(465.5f, 308.0f, 596.5f, 192.0f),
                        PathNode.QuadTo(609.5f, 181.0f, 618.5f, 174.0f),
                        PathNode.QuadTo(627.5f, 167.0f, 633.5f, 166.0f),
                        PathNode.QuadTo(647.5f, 162.0f, 658.5f, 166.0f),
                        PathNode.QuadTo(664.5f, 167.0f, 674.0f, 174.5f),
                        PathNode.QuadTo(683.5f, 182.0f, 695.5f, 193.0f),
                        PathNode.QuadTo(821.5f, 305.0f, 945.0f, 429.0f),
                        PathNode.QuadTo(1068.5f, 553.0f, 1130.5f, 651.0f),
                        PathNode.QuadTo(1182.5f, 727.0f, 1182.5f, 823.0f),
                        PathNode.QuadTo(1182.5f, 906.0f, 1141.5f, 976.0f),
                        PathNode.QuadTo(1100.5f, 1046.0f, 1030.5f, 1087.0f),
                        PathNode.QuadTo(960.5f, 1128.0f, 877.5f, 1128.0f),
                        PathNode.QuadTo(823.5f, 1128.0f, 773.5f, 1109.5f),
                        PathNode.QuadTo(723.5f, 1091.0f, 682.5f, 1058.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1123.5f, 823.0f),
                        PathNode.QuadTo(1123.5f, 748.0f, 1081.5f, 684.0f),
                        PathNode.QuadTo(1032.5f, 609.0f, 942.5f, 514.5f),
                        PathNode.QuadTo(852.5f, 420.0f, 794.5f, 366.0f),
                        PathNode.QuadTo(735.5f, 308.0f, 662.5f, 244.0f),
                        PathNode.QuadTo(652.5f, 235.0f, 645.5f, 235.0f),
                        PathNode.QuadTo(638.5f, 235.0f, 628.5f, 245.0f),
                        PathNode.QuadTo(540.5f, 323.0f, 486.5f, 375.0f),
                        PathNode.QuadTo(267.5f, 584.0f, 203.5f, 696.0f),
                        PathNode.QuadTo(182.5f, 731.0f, 175.0f, 760.0f),
                        PathNode.QuadTo(167.5f, 789.0f, 167.5f, 823.0f),
                        PathNode.QuadTo(167.5f, 890.0f, 200.5f, 946.0f),
                        PathNode.QuadTo(233.5f, 1002.0f, 290.0f, 1035.0f),
                        PathNode.QuadTo(346.5f, 1068.0f, 413.5f, 1068.0f),
                        PathNode.QuadTo(465.5f, 1068.0f, 511.5f, 1048.0f),
                        PathNode.QuadTo(557.5f, 1028.0f, 591.5f, 991.0f),
                        PathNode.QuadTo(604.5f, 977.0f, 613.0f, 969.0f),
                        PathNode.QuadTo(621.5f, 961.0f, 628.5f, 957.0f),
                        PathNode.QuadTo(646.5f, 951.0f, 662.5f, 958.0f),
                        PathNode.QuadTo(668.5f, 961.0f, 677.5f, 969.0f),
                        PathNode.QuadTo(686.5f, 977.0f, 699.5f, 991.0f),
                        PathNode.QuadTo(732.5f, 1027.0f, 779.0f, 1047.5f),
                        PathNode.QuadTo(825.5f, 1068.0f, 877.5f, 1068.0f),
                        PathNode.QuadTo(944.5f, 1068.0f, 1001.0f, 1035.0f),
                        PathNode.QuadTo(1057.5f, 1002.0f, 1090.5f, 946.0f),
                        PathNode.QuadTo(1123.5f, 890.0f, 1123.5f, 823.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesLight!!
    }

private var _favoritesLight: ImageVector? = null

val MiuixIcons.Normal.Favorites: ImageVector
    get() {
        if (_favoritesNormal != null) return _favoritesNormal!!
        _favoritesNormal = ImageVector.Builder(
            name = "Favorites.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1301.5f,
            viewportHeight = 1301.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1301.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(681.4f, 1067.6f),
                        PathNode.LineTo(675.5f, 1062.6f),
                        PathNode.LineTo(662.3f, 1049.4f),
                        PathNode.QuadTo(650.3f, 1037.1f, 639.6f, 1049.1f),
                        PathNode.QuadTo(630.4f, 1059.9f, 620.5f, 1067.6f),
                        PathNode.QuadTo(578.8f, 1101.6f, 527.9f, 1120.2f),
                        PathNode.QuadTo(477.0f, 1138.9f, 421.3f, 1138.9f),
                        PathNode.QuadTo(336.0f, 1138.9f, 264.2f, 1097.2f),
                        PathNode.QuadTo(192.5f, 1055.6f, 150.5f, 984.2f),
                        PathNode.QuadTo(108.5f, 912.8f, 108.5f, 827.7f),
                        PathNode.QuadTo(108.5f, 738.7f, 154.8f, 664.3f),
                        PathNode.QuadTo(210.8f, 569.3f, 338.6f, 440.0f),
                        PathNode.QuadTo(466.5f, 310.7f, 596.8f, 195.4f),
                        PathNode.QuadTo(609.8f, 183.7f, 619.5f, 176.3f),
                        PathNode.QuadTo(629.1f, 169.0f, 636.5f, 167.3f),
                        PathNode.QuadTo(652.6f, 162.6f, 667.0f, 167.3f),
                        PathNode.QuadTo(673.7f, 169.0f, 683.9f, 176.8f),
                        PathNode.QuadTo(694.1f, 184.7f, 706.8f, 196.4f),
                        PathNode.QuadTo(831.4f, 307.7f, 954.6f, 431.0f),
                        PathNode.QuadTo(1077.7f, 554.3f, 1139.7f, 651.6f),
                        PathNode.QuadTo(1193.1f, 729.7f, 1193.1f, 827.7f),
                        PathNode.QuadTo(1193.1f, 912.8f, 1151.1f, 984.2f),
                        PathNode.QuadTo(1109.0f, 1055.6f, 1037.7f, 1097.2f),
                        PathNode.QuadTo(966.3f, 1138.9f, 881.2f, 1138.9f),
                        PathNode.QuadTo(825.8f, 1138.9f, 774.5f, 1120.1f),
                        PathNode.QuadTo(723.1f, 1101.2f, 681.4f, 1067.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1115.5f, 827.7f),
                        PathNode.QuadTo(1115.5f, 755.5f, 1075.6f, 694.9f),
                        PathNode.QuadTo(1028.0f, 622.0f, 941.4f, 530.9f),
                        PathNode.QuadTo(854.8f, 439.9f, 792.7f, 381.1f),
                        PathNode.QuadTo(732.3f, 323.1f, 666.9f, 265.2f),
                        PathNode.QuadTo(656.9f, 256.2f, 651.3f, 256.2f),
                        PathNode.QuadTo(645.6f, 256.2f, 635.6f, 265.6f),
                        PathNode.QuadTo(562.1f, 331.2f, 500.5f, 390.1f),
                        PathNode.QuadTo(284.3f, 596.3f, 221.0f, 706.2f),
                        PathNode.QuadTo(200.6f, 739.9f, 193.8f, 767.5f),
                        PathNode.QuadTo(187.0f, 795.1f, 187.0f, 827.7f),
                        PathNode.QuadTo(187.0f, 891.3f, 218.3f, 944.9f),
                        PathNode.QuadTo(249.6f, 998.5f, 303.7f, 1029.8f),
                        PathNode.QuadTo(357.8f, 1061.1f, 421.3f, 1061.1f),
                        PathNode.QuadTo(470.6f, 1061.1f, 514.5f, 1041.7f),
                        PathNode.QuadTo(558.5f, 1022.4f, 591.1f, 987.5f),
                        PathNode.QuadTo(604.1f, 973.5f, 613.6f, 964.5f),
                        PathNode.QuadTo(623.1f, 955.4f, 630.8f, 952.1f),
                        PathNode.QuadTo(651.6f, 944.1f, 671.7f, 952.4f),
                        PathNode.QuadTo(679.1f, 956.1f, 688.8f, 964.8f),
                        PathNode.QuadTo(698.5f, 973.5f, 711.5f, 987.5f),
                        PathNode.QuadTo(743.1f, 1022.1f, 787.5f, 1041.6f),
                        PathNode.QuadTo(832.0f, 1061.1f, 881.2f, 1061.1f),
                        PathNode.QuadTo(944.8f, 1061.1f, 998.9f, 1029.8f),
                        PathNode.QuadTo(1053.0f, 998.5f, 1084.2f, 944.9f),
                        PathNode.QuadTo(1115.5f, 891.3f, 1115.5f, 827.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesNormal!!
    }

private var _favoritesNormal: ImageVector? = null

val MiuixIcons.Regular.Favorites: ImageVector
    get() {
        if (_favoritesRegular != null) return _favoritesRegular!!
        _favoritesRegular = ImageVector.Builder(
            name = "Favorites.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1306.8f,
            viewportHeight = 1306.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1306.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(680.9f, 1071.9f),
                        PathNode.LineTo(675.9f, 1067.9f),
                        PathNode.LineTo(664.9f, 1056.9f),
                        PathNode.QuadTo(652.9f, 1044.9f, 641.9f, 1056.9f),
                        PathNode.QuadTo(634.9f, 1064.9f, 625.9f, 1071.9f),
                        PathNode.QuadTo(583.9f, 1105.9f, 532.4f, 1124.9f),
                        PathNode.QuadTo(480.9f, 1143.9f, 424.9f, 1143.9f),
                        PathNode.QuadTo(338.9f, 1143.9f, 266.4f, 1101.9f),
                        PathNode.QuadTo(193.9f, 1059.9f, 151.4f, 987.9f),
                        PathNode.QuadTo(108.9f, 915.9f, 108.9f, 829.9f),
                        PathNode.QuadTo(108.9f, 739.9f, 155.9f, 664.9f),
                        PathNode.QuadTo(211.9f, 569.9f, 339.4f, 440.9f),
                        PathNode.QuadTo(466.9f, 311.9f, 596.9f, 196.9f),
                        PathNode.QuadTo(609.9f, 184.9f, 619.9f, 177.4f),
                        PathNode.QuadTo(629.9f, 169.9f, 637.9f, 167.9f),
                        PathNode.QuadTo(654.9f, 162.9f, 670.9f, 167.9f),
                        PathNode.QuadTo(677.9f, 169.9f, 688.4f, 177.9f),
                        PathNode.QuadTo(698.9f, 185.9f, 711.9f, 197.9f),
                        PathNode.QuadTo(835.9f, 308.9f, 958.9f, 431.9f),
                        PathNode.QuadTo(1081.9f, 554.9f, 1143.9f, 651.9f),
                        PathNode.QuadTo(1197.9f, 730.9f, 1197.9f, 829.9f),
                        PathNode.QuadTo(1197.9f, 915.9f, 1155.4f, 987.9f),
                        PathNode.QuadTo(1112.9f, 1059.9f, 1040.9f, 1101.9f),
                        PathNode.QuadTo(968.9f, 1143.9f, 882.9f, 1143.9f),
                        PathNode.QuadTo(826.9f, 1143.9f, 774.9f, 1124.9f),
                        PathNode.QuadTo(722.9f, 1105.9f, 680.9f, 1071.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1111.9f, 829.9f),
                        PathNode.QuadTo(1111.9f, 758.9f, 1072.9f, 699.9f),
                        PathNode.QuadTo(1025.9f, 627.9f, 940.9f, 538.4f),
                        PathNode.QuadTo(855.9f, 448.9f, 791.9f, 387.9f),
                        PathNode.QuadTo(730.9f, 329.9f, 668.9f, 274.9f),
                        PathNode.QuadTo(658.9f, 265.9f, 653.9f, 265.9f),
                        PathNode.QuadTo(648.9f, 265.9f, 638.9f, 274.9f),
                        PathNode.QuadTo(571.9f, 334.9f, 506.9f, 396.9f),
                        PathNode.QuadTo(291.9f, 601.9f, 228.9f, 710.9f),
                        PathNode.QuadTo(208.9f, 743.9f, 202.4f, 770.9f),
                        PathNode.QuadTo(195.9f, 797.9f, 195.9f, 829.9f),
                        PathNode.QuadTo(195.9f, 891.9f, 226.4f, 944.4f),
                        PathNode.QuadTo(256.9f, 996.9f, 309.9f, 1027.4f),
                        PathNode.QuadTo(362.9f, 1057.9f, 424.9f, 1057.9f),
                        PathNode.QuadTo(472.9f, 1057.9f, 515.9f, 1038.9f),
                        PathNode.QuadTo(558.9f, 1019.9f, 590.9f, 985.9f),
                        PathNode.QuadTo(603.9f, 971.9f, 613.9f, 962.4f),
                        PathNode.QuadTo(623.9f, 952.9f, 631.9f, 949.9f),
                        PathNode.QuadTo(653.9f, 940.9f, 675.9f, 949.9f),
                        PathNode.QuadTo(683.9f, 953.9f, 693.9f, 962.9f),
                        PathNode.QuadTo(703.9f, 971.9f, 716.9f, 985.9f),
                        PathNode.QuadTo(747.9f, 1019.9f, 791.4f, 1038.9f),
                        PathNode.QuadTo(834.9f, 1057.9f, 882.9f, 1057.9f),
                        PathNode.QuadTo(944.9f, 1057.9f, 997.9f, 1027.4f),
                        PathNode.QuadTo(1050.9f, 996.9f, 1081.4f, 944.4f),
                        PathNode.QuadTo(1111.9f, 891.9f, 1111.9f, 829.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesRegular!!
    }

private var _favoritesRegular: ImageVector? = null

val MiuixIcons.Medium.Favorites: ImageVector
    get() {
        if (_favoritesMedium != null) return _favoritesMedium!!
        _favoritesMedium = ImageVector.Builder(
            name = "Favorites.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1318.1f,
            viewportHeight = 1318.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1318.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(684.2f, 1084.0f),
                        PathNode.LineTo(678.6f, 1079.4f),
                        PathNode.LineTo(668.2f, 1069.6f),
                        PathNode.QuadTo(659.1f, 1060.5f, 649.9f, 1069.6f),
                        PathNode.QuadTo(645.8f, 1074.7f, 635.1f, 1083.4f),
                        PathNode.QuadTo(593.1f, 1116.8f, 541.3f, 1135.8f),
                        PathNode.QuadTo(489.5f, 1154.8f, 432.3f, 1154.8f),
                        PathNode.QuadTo(344.5f, 1154.8f, 270.6f, 1112.0f),
                        PathNode.QuadTo(196.6f, 1069.1f, 153.2f, 995.6f),
                        PathNode.QuadTo(109.8f, 922.1f, 109.8f, 834.4f),
                        PathNode.QuadTo(109.8f, 741.4f, 157.4f, 665.8f),
                        PathNode.QuadTo(213.4f, 570.8f, 339.8f, 443.0f),
                        PathNode.QuadTo(466.1f, 315.2f, 596.7f, 199.0f),
                        PathNode.QuadTo(610.3f, 187.0f, 621.1f, 178.9f),
                        PathNode.QuadTo(632.0f, 170.8f, 641.2f, 168.3f),
                        PathNode.QuadTo(661.1f, 163.3f, 678.9f, 168.8f),
                        PathNode.QuadTo(687.1f, 170.8f, 698.5f, 179.4f),
                        PathNode.QuadTo(709.8f, 188.0f, 723.4f, 200.0f),
                        PathNode.QuadTo(848.6f, 312.2f, 970.1f, 434.0f),
                        PathNode.QuadTo(1091.7f, 555.8f, 1153.1f, 652.3f),
                        PathNode.QuadTo(1208.3f, 733.6f, 1208.3f, 834.4f),
                        PathNode.QuadTo(1208.3f, 922.1f, 1164.9f, 995.6f),
                        PathNode.QuadTo(1121.5f, 1069.1f, 1048.0f, 1112.0f),
                        PathNode.QuadTo(974.5f, 1154.8f, 886.8f, 1154.8f),
                        PathNode.QuadTo(829.6f, 1154.8f, 777.3f, 1136.1f),
                        PathNode.QuadTo(725.0f, 1117.4f, 684.2f, 1084.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1104.6f, 834.4f),
                        PathNode.QuadTo(1104.6f, 766.9f, 1067.4f, 710.3f),
                        PathNode.QuadTo(1021.0f, 639.4f, 936.5f, 550.2f),
                        PathNode.QuadTo(852.1f, 461.0f, 790.5f, 402.4f),
                        PathNode.QuadTo(714.8f, 330.8f, 672.2f, 294.1f),
                        PathNode.QuadTo(664.0f, 286.3f, 659.5f, 286.3f),
                        PathNode.QuadTo(655.1f, 286.3f, 646.9f, 294.1f),
                        PathNode.QuadTo(595.2f, 339.4f, 520.2f, 411.4f),
                        PathNode.QuadTo(307.0f, 615.2f, 245.7f, 720.7f),
                        PathNode.QuadTo(226.9f, 752.5f, 220.7f, 778.0f),
                        PathNode.QuadTo(214.5f, 803.5f, 214.5f, 834.4f),
                        PathNode.QuadTo(214.5f, 893.4f, 243.5f, 943.3f),
                        PathNode.QuadTo(272.5f, 993.1f, 322.9f, 1022.2f),
                        PathNode.QuadTo(373.3f, 1051.2f, 432.3f, 1051.2f),
                        PathNode.QuadTo(478.0f, 1051.2f, 518.9f, 1033.1f),
                        PathNode.QuadTo(559.8f, 1015.0f, 590.1f, 982.7f),
                        PathNode.QuadTo(603.7f, 968.1f, 614.3f, 958.3f),
                        PathNode.QuadTo(624.8f, 948.5f, 634.0f, 945.0f),
                        PathNode.QuadTo(659.5f, 934.2f, 685.1f, 945.0f),
                        PathNode.QuadTo(693.7f, 949.0f, 703.7f, 957.7f),
                        PathNode.QuadTo(713.7f, 966.4f, 729.0f, 982.7f),
                        PathNode.QuadTo(758.3f, 1015.0f, 799.4f, 1033.1f),
                        PathNode.QuadTo(840.5f, 1051.2f, 886.8f, 1051.2f),
                        PathNode.QuadTo(945.8f, 1051.2f, 996.2f, 1022.2f),
                        PathNode.QuadTo(1046.5f, 993.1f, 1075.6f, 943.3f),
                        PathNode.QuadTo(1104.6f, 893.4f, 1104.6f, 834.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesMedium!!
    }

private var _favoritesMedium: ImageVector? = null

val MiuixIcons.Demibold.Favorites: ImageVector
    get() {
        if (_favoritesDemibold != null) return _favoritesDemibold!!
        _favoritesDemibold = ImageVector.Builder(
            name = "Favorites.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1326.0f,
            viewportHeight = 1326.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1326.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(686.5f, 1092.5f),
                        PathNode.LineTo(680.5f, 1087.5f),
                        PathNode.LineTo(670.5f, 1078.5f),
                        PathNode.QuadTo(663.5f, 1071.5f, 655.5f, 1078.5f),
                        PathNode.QuadTo(653.5f, 1081.5f, 641.5f, 1091.5f),
                        PathNode.QuadTo(599.5f, 1124.5f, 547.5f, 1143.5f),
                        PathNode.QuadTo(495.5f, 1162.5f, 437.5f, 1162.5f),
                        PathNode.QuadTo(348.5f, 1162.5f, 273.5f, 1119.0f),
                        PathNode.QuadTo(198.5f, 1075.5f, 154.5f, 1001.0f),
                        PathNode.QuadTo(110.5f, 926.5f, 110.5f, 837.5f),
                        PathNode.QuadTo(110.5f, 742.5f, 158.5f, 666.5f),
                        PathNode.QuadTo(214.5f, 571.5f, 340.0f, 444.5f),
                        PathNode.QuadTo(465.5f, 317.5f, 596.5f, 200.5f),
                        PathNode.QuadTo(610.5f, 188.5f, 622.0f, 180.0f),
                        PathNode.QuadTo(633.5f, 171.5f, 643.5f, 168.5f),
                        PathNode.QuadTo(665.5f, 163.5f, 684.5f, 169.5f),
                        PathNode.QuadTo(693.5f, 171.5f, 705.5f, 180.5f),
                        PathNode.QuadTo(717.5f, 189.5f, 731.5f, 201.5f),
                        PathNode.QuadTo(857.5f, 314.5f, 978.0f, 435.5f),
                        PathNode.QuadTo(1098.5f, 556.5f, 1159.5f, 652.5f),
                        PathNode.QuadTo(1215.5f, 735.5f, 1215.5f, 837.5f),
                        PathNode.QuadTo(1215.5f, 926.5f, 1171.5f, 1001.0f),
                        PathNode.QuadTo(1127.5f, 1075.5f, 1053.0f, 1119.0f),
                        PathNode.QuadTo(978.5f, 1162.5f, 889.5f, 1162.5f),
                        PathNode.QuadTo(831.5f, 1162.5f, 779.0f, 1144.0f),
                        PathNode.QuadTo(726.5f, 1125.5f, 686.5f, 1092.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1099.5f, 837.5f),
                        PathNode.QuadTo(1099.5f, 772.5f, 1063.5f, 717.5f),
                        PathNode.QuadTo(1017.5f, 647.5f, 933.5f, 558.5f),
                        PathNode.QuadTo(849.5f, 469.5f, 789.5f, 412.5f),
                        PathNode.QuadTo(703.5f, 331.5f, 674.5f, 307.5f),
                        PathNode.QuadTo(667.5f, 300.5f, 663.5f, 300.5f),
                        PathNode.QuadTo(659.5f, 300.5f, 652.5f, 307.5f),
                        PathNode.QuadTo(611.5f, 342.5f, 529.5f, 421.5f),
                        PathNode.QuadTo(317.5f, 624.5f, 257.5f, 727.5f),
                        PathNode.QuadTo(239.5f, 758.5f, 233.5f, 783.0f),
                        PathNode.QuadTo(227.5f, 807.5f, 227.5f, 837.5f),
                        PathNode.QuadTo(227.5f, 894.5f, 255.5f, 942.5f),
                        PathNode.QuadTo(283.5f, 990.5f, 332.0f, 1018.5f),
                        PathNode.QuadTo(380.5f, 1046.5f, 437.5f, 1046.5f),
                        PathNode.QuadTo(481.5f, 1046.5f, 521.0f, 1029.0f),
                        PathNode.QuadTo(560.5f, 1011.5f, 589.5f, 980.5f),
                        PathNode.QuadTo(603.5f, 965.5f, 614.5f, 955.5f),
                        PathNode.QuadTo(625.5f, 945.5f, 635.5f, 941.5f),
                        PathNode.QuadTo(663.5f, 929.5f, 691.5f, 941.5f),
                        PathNode.QuadTo(700.5f, 945.5f, 710.5f, 954.0f),
                        PathNode.QuadTo(720.5f, 962.5f, 737.5f, 980.5f),
                        PathNode.QuadTo(765.5f, 1011.5f, 805.0f, 1029.0f),
                        PathNode.QuadTo(844.5f, 1046.5f, 889.5f, 1046.5f),
                        PathNode.QuadTo(946.5f, 1046.5f, 995.0f, 1018.5f),
                        PathNode.QuadTo(1043.5f, 990.5f, 1071.5f, 942.5f),
                        PathNode.QuadTo(1099.5f, 894.5f, 1099.5f, 837.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesDemibold!!
    }

private var _favoritesDemibold: ImageVector? = null
