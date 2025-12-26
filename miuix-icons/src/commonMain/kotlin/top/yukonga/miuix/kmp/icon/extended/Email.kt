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

val MiuixIcons.Email: ImageVector
    get() = MiuixIcons.Regular.Email

val MiuixIcons.Light.Email: ImageVector
    get() {
        if (_emailLight != null) return _emailLight!!
        _emailLight = ImageVector.Builder(
            name = "Email.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1248.0f,
            viewportHeight = 1248.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -41.0f, translationY = 1000.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1090.0f, -24.0f),
                        PathNode.QuadTo(1139.0f, 2.0f, 1163.0f, 49.0f),
                        PathNode.QuadTo(1178.0f, 76.0f, 1181.5f, 115.0f),
                        PathNode.QuadTo(1185.0f, 154.0f, 1185.0f, 249.0f),
                        PathNode.VerticalTo(503.0f),
                        PathNode.QuadTo(1185.0f, 596.0f, 1181.5f, 636.0f),
                        PathNode.QuadTo(1178.0f, 676.0f, 1163.0f, 703.0f),
                        PathNode.QuadTo(1141.0f, 750.0f, 1090.0f, 777.0f),
                        PathNode.QuadTo(1063.0f, 791.0f, 1024.0f, 794.5f),
                        PathNode.QuadTo(985.0f, 798.0f, 889.0f, 798.0f),
                        PathNode.HorizontalTo(440.0f),
                        PathNode.QuadTo(344.0f, 798.0f, 304.5f, 794.5f),
                        PathNode.QuadTo(265.0f, 791.0f, 239.0f, 777.0f),
                        PathNode.QuadTo(192.0f, 752.0f, 166.0f, 703.0f),
                        PathNode.QuadTo(152.0f, 677.0f, 148.5f, 638.0f),
                        PathNode.QuadTo(145.0f, 599.0f, 145.0f, 503.0f),
                        PathNode.VerticalTo(249.0f),
                        PathNode.QuadTo(145.0f, 154.0f, 148.5f, 115.0f),
                        PathNode.QuadTo(152.0f, 76.0f, 166.0f, 49.0f),
                        PathNode.QuadTo(192.0f, 1.0f, 239.0f, -24.0f),
                        PathNode.QuadTo(265.0f, -38.0f, 304.5f, -41.5f),
                        PathNode.QuadTo(344.0f, -45.0f, 440.0f, -45.0f),
                        PathNode.HorizontalTo(889.0f),
                        PathNode.QuadTo(985.0f, -45.0f, 1024.0f, -41.5f),
                        PathNode.QuadTo(1063.0f, -38.0f, 1090.0f, -24.0f),
                        PathNode.Close,
                        PathNode.MoveTo(269.0f, 28.0f),
                        PathNode.QuadTo(238.0f, 44.0f, 218.0f, 78.0f),
                        PathNode.QuadTo(208.0f, 97.0f, 206.0f, 122.5f),
                        PathNode.QuadTo(204.0f, 148.0f, 204.0f, 204.0f),
                        PathNode.VerticalTo(548.0f),
                        PathNode.LineTo(205.0f, 608.0f),
                        PathNode.QuadTo(206.0f, 641.0f, 214.5f, 646.0f),
                        PathNode.QuadTo(223.0f, 651.0f, 239.0f, 638.0f),
                        PathNode.LineTo(560.0f, 366.0f),
                        PathNode.QuadTo(597.0f, 337.0f, 611.0f, 326.5f),
                        PathNode.QuadTo(625.0f, 316.0f, 639.0f, 312.0f),
                        PathNode.QuadTo(651.0f, 308.0f, 665.0f, 308.0f),
                        PathNode.QuadTo(679.0f, 308.0f, 691.0f, 312.0f),
                        PathNode.QuadTo(704.0f, 316.0f, 718.0f, 326.0f),
                        PathNode.QuadTo(732.0f, 336.0f, 769.0f, 366.0f),
                        PathNode.LineTo(1091.0f, 638.0f),
                        PathNode.QuadTo(1109.0f, 653.0f, 1116.5f, 646.5f),
                        PathNode.QuadTo(1124.0f, 640.0f, 1125.0f, 608.0f),
                        PathNode.VerticalTo(548.0f),
                        PathNode.VerticalTo(204.0f),
                        PathNode.QuadTo(1125.0f, 148.0f, 1123.0f, 122.5f),
                        PathNode.QuadTo(1121.0f, 97.0f, 1111.0f, 79.0f),
                        PathNode.QuadTo(1093.0f, 46.0f, 1060.0f, 28.0f),
                        PathNode.QuadTo(1042.0f, 18.0f, 1016.5f, 16.0f),
                        PathNode.QuadTo(991.0f, 14.0f, 935.0f, 14.0f),
                        PathNode.HorizontalTo(394.0f),
                        PathNode.QuadTo(339.0f, 14.0f, 313.5f, 16.0f),
                        PathNode.QuadTo(288.0f, 18.0f, 269.0f, 28.0f),
                        PathNode.Close,
                        PathNode.MoveTo(259.0f, 716.0f),
                        PathNode.QuadTo(277.0f, 731.0f, 302.0f, 734.5f),
                        PathNode.QuadTo(327.0f, 738.0f, 385.0f, 738.0f),
                        PathNode.HorizontalTo(944.0f),
                        PathNode.QuadTo(999.0f, 738.0f, 1023.0f, 735.5f),
                        PathNode.QuadTo(1047.0f, 733.0f, 1065.0f, 721.0f),
                        PathNode.QuadTo(1075.0f, 714.0f, 1076.5f, 709.5f),
                        PathNode.QuadTo(1078.0f, 705.0f, 1073.0f, 700.0f),
                        PathNode.LineTo(689.0f, 377.0f),
                        PathNode.QuadTo(679.0f, 368.0f, 664.0f, 368.5f),
                        PathNode.QuadTo(649.0f, 369.0f, 638.0f, 379.0f),
                        PathNode.LineTo(255.0f, 700.0f),
                        PathNode.QuadTo(251.0f, 703.0f, 252.0f, 707.5f),
                        PathNode.QuadTo(253.0f, 712.0f, 259.0f, 716.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _emailLight!!
    }

private var _emailLight: ImageVector? = null

val MiuixIcons.Regular.Email: ImageVector
    get() {
        if (_emailRegular != null) return _emailRegular!!
        _emailRegular = ImageVector.Builder(
            name = "Email.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1279.2f,
            viewportHeight = 1279.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -25.399999999999977f, translationY = 1016.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1096.0f, -36.0f),
                        PathNode.QuadTo(1149.0f, -8.0f, 1175.0f, 43.0f),
                        PathNode.QuadTo(1191.0f, 72.0f, 1194.5f, 112.5f),
                        PathNode.QuadTo(1198.0f, 153.0f, 1198.0f, 249.0f),
                        PathNode.VerticalTo(503.0f),
                        PathNode.QuadTo(1198.0f, 599.0f, 1194.5f, 640.0f),
                        PathNode.QuadTo(1191.0f, 681.0f, 1175.0f, 709.0f),
                        PathNode.QuadTo(1150.0f, 759.0f, 1096.0f, 789.0f),
                        PathNode.QuadTo(1067.0f, 804.0f, 1026.0f, 807.5f),
                        PathNode.QuadTo(985.0f, 811.0f, 889.0f, 811.0f),
                        PathNode.HorizontalTo(440.0f),
                        PathNode.QuadTo(344.0f, 811.0f, 302.5f, 807.5f),
                        PathNode.QuadTo(261.0f, 804.0f, 233.0f, 789.0f),
                        PathNode.QuadTo(181.0f, 760.0f, 154.0f, 709.0f),
                        PathNode.QuadTo(139.0f, 681.0f, 135.5f, 640.0f),
                        PathNode.QuadTo(132.0f, 599.0f, 132.0f, 503.0f),
                        PathNode.VerticalTo(249.0f),
                        PathNode.QuadTo(132.0f, 153.0f, 135.5f, 112.5f),
                        PathNode.QuadTo(139.0f, 72.0f, 154.0f, 43.0f),
                        PathNode.QuadTo(183.0f, -9.0f, 233.0f, -36.0f),
                        PathNode.QuadTo(261.0f, -51.0f, 302.5f, -54.5f),
                        PathNode.QuadTo(344.0f, -58.0f, 440.0f, -58.0f),
                        PathNode.HorizontalTo(889.0f),
                        PathNode.QuadTo(985.0f, -58.0f, 1026.0f, -54.5f),
                        PathNode.QuadTo(1067.0f, -51.0f, 1096.0f, -36.0f),
                        PathNode.Close,
                        PathNode.MoveTo(275.0f, 40.0f),
                        PathNode.QuadTo(248.0f, 54.0f, 230.0f, 85.0f),
                        PathNode.QuadTo(221.0f, 101.0f, 219.0f, 125.0f),
                        PathNode.QuadTo(217.0f, 149.0f, 217.0f, 204.0f),
                        PathNode.VerticalTo(548.0f),
                        PathNode.LineTo(218.0f, 608.0f),
                        PathNode.QuadTo(218.0f, 623.0f, 225.0f, 624.5f),
                        PathNode.QuadTo(232.0f, 626.0f, 242.0f, 618.0f),
                        PathNode.LineTo(552.0f, 356.0f),
                        PathNode.QuadTo(586.0f, 328.0f, 602.5f, 316.5f),
                        PathNode.QuadTo(619.0f, 305.0f, 635.0f, 299.0f),
                        PathNode.QuadTo(649.0f, 294.0f, 665.0f, 294.0f),
                        PathNode.QuadTo(681.0f, 294.0f, 695.0f, 299.0f),
                        PathNode.QuadTo(710.0f, 305.0f, 726.5f, 316.5f),
                        PathNode.QuadTo(743.0f, 328.0f, 777.0f, 356.0f),
                        PathNode.LineTo(1088.0f, 618.0f),
                        PathNode.QuadTo(1100.0f, 628.0f, 1106.0f, 625.5f),
                        PathNode.QuadTo(1112.0f, 623.0f, 1112.0f, 608.0f),
                        PathNode.VerticalTo(548.0f),
                        PathNode.VerticalTo(204.0f),
                        PathNode.QuadTo(1112.0f, 149.0f, 1110.0f, 125.0f),
                        PathNode.QuadTo(1108.0f, 101.0f, 1099.0f, 85.0f),
                        PathNode.QuadTo(1083.0f, 56.0f, 1054.0f, 40.0f),
                        PathNode.QuadTo(1038.0f, 31.0f, 1014.0f, 29.0f),
                        PathNode.QuadTo(990.0f, 27.0f, 935.0f, 27.0f),
                        PathNode.HorizontalTo(394.0f),
                        PathNode.QuadTo(340.0f, 27.0f, 316.0f, 29.0f),
                        PathNode.QuadTo(292.0f, 31.0f, 275.0f, 40.0f),
                        PathNode.Close,
                        PathNode.MoveTo(279.0f, 714.0f),
                        PathNode.QuadTo(293.0f, 722.0f, 316.5f, 723.5f),
                        PathNode.QuadTo(340.0f, 725.0f, 394.0f, 725.0f),
                        PathNode.HorizontalTo(935.0f),
                        PathNode.QuadTo(990.0f, 725.0f, 1013.0f, 723.5f),
                        PathNode.QuadTo(1036.0f, 722.0f, 1050.0f, 714.0f),
                        PathNode.QuadTo(1056.0f, 711.0f, 1056.5f, 707.5f),
                        PathNode.QuadTo(1057.0f, 704.0f, 1053.0f, 700.0f),
                        PathNode.LineTo(681.0f, 388.0f),
                        PathNode.QuadTo(673.0f, 382.0f, 663.5f, 382.0f),
                        PathNode.QuadTo(654.0f, 382.0f, 646.0f, 389.0f),
                        PathNode.LineTo(275.0f, 701.0f),
                        PathNode.QuadTo(272.0f, 703.0f, 273.0f, 707.0f),
                        PathNode.QuadTo(274.0f, 711.0f, 279.0f, 714.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _emailRegular!!
    }

private var _emailRegular: ImageVector? = null

val MiuixIcons.Heavy.Email: ImageVector
    get() {
        if (_emailHeavy != null) return _emailHeavy!!
        _emailHeavy = ImageVector.Builder(
            name = "Email.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1312.8f,
            viewportHeight = 1312.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -8.600000000000023f, translationY = 1032.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1102.0f, -48.0f),
                        PathNode.QuadTo(1159.0f, -19.0f, 1187.0f, 37.0f),
                        PathNode.QuadTo(1204.0f, 68.0f, 1208.0f, 110.0f),
                        PathNode.QuadTo(1212.0f, 152.0f, 1212.0f, 249.0f),
                        PathNode.VerticalTo(503.0f),
                        PathNode.QuadTo(1212.0f, 600.0f, 1208.0f, 642.5f),
                        PathNode.QuadTo(1204.0f, 685.0f, 1187.0f, 715.0f),
                        PathNode.QuadTo(1161.0f, 769.0f, 1102.0f, 801.0f),
                        PathNode.QuadTo(1071.0f, 817.0f, 1028.5f, 821.0f),
                        PathNode.QuadTo(986.0f, 825.0f, 889.0f, 825.0f),
                        PathNode.HorizontalTo(440.0f),
                        PathNode.QuadTo(343.0f, 825.0f, 300.0f, 821.0f),
                        PathNode.QuadTo(257.0f, 817.0f, 227.0f, 801.0f),
                        PathNode.QuadTo(170.0f, 770.0f, 142.0f, 715.0f),
                        PathNode.QuadTo(126.0f, 685.0f, 122.0f, 642.5f),
                        PathNode.QuadTo(118.0f, 600.0f, 118.0f, 503.0f),
                        PathNode.VerticalTo(249.0f),
                        PathNode.QuadTo(118.0f, 152.0f, 122.0f, 110.0f),
                        PathNode.QuadTo(126.0f, 68.0f, 142.0f, 37.0f),
                        PathNode.QuadTo(172.0f, -20.0f, 227.0f, -48.0f),
                        PathNode.QuadTo(257.0f, -64.0f, 300.0f, -68.0f),
                        PathNode.QuadTo(343.0f, -72.0f, 440.0f, -72.0f),
                        PathNode.HorizontalTo(889.0f),
                        PathNode.QuadTo(986.0f, -72.0f, 1028.5f, -68.0f),
                        PathNode.QuadTo(1071.0f, -64.0f, 1102.0f, -48.0f),
                        PathNode.Close,
                        PathNode.MoveTo(281.0f, 52.0f),
                        PathNode.QuadTo(258.0f, 65.0f, 242.0f, 92.0f),
                        PathNode.QuadTo(234.0f, 106.0f, 232.5f, 128.0f),
                        PathNode.QuadTo(231.0f, 150.0f, 231.0f, 204.0f),
                        PathNode.VerticalTo(548.0f),
                        PathNode.VerticalTo(584.0f),
                        PathNode.QuadTo(231.0f, 596.0f, 237.0f, 598.0f),
                        PathNode.QuadTo(243.0f, 600.0f, 251.0f, 593.0f),
                        PathNode.LineTo(543.0f, 345.0f),
                        PathNode.QuadTo(578.0f, 316.0f, 595.5f, 303.5f),
                        PathNode.QuadTo(613.0f, 291.0f, 631.0f, 286.0f),
                        PathNode.QuadTo(648.0f, 281.0f, 665.5f, 281.0f),
                        PathNode.QuadTo(683.0f, 281.0f, 699.0f, 286.0f),
                        PathNode.QuadTo(716.0f, 291.0f, 733.5f, 303.5f),
                        PathNode.QuadTo(751.0f, 316.0f, 786.0f, 345.0f),
                        PathNode.LineTo(1078.0f, 593.0f),
                        PathNode.QuadTo(1087.0f, 601.0f, 1092.5f, 598.5f),
                        PathNode.QuadTo(1098.0f, 596.0f, 1098.0f, 584.0f),
                        PathNode.VerticalTo(548.0f),
                        PathNode.VerticalTo(204.0f),
                        PathNode.QuadTo(1098.0f, 150.0f, 1096.5f, 127.5f),
                        PathNode.QuadTo(1095.0f, 105.0f, 1087.0f, 91.0f),
                        PathNode.QuadTo(1073.0f, 66.0f, 1048.0f, 52.0f),
                        PathNode.QuadTo(1034.0f, 44.0f, 1012.0f, 42.5f),
                        PathNode.QuadTo(990.0f, 41.0f, 935.0f, 41.0f),
                        PathNode.HorizontalTo(394.0f),
                        PathNode.QuadTo(340.0f, 41.0f, 318.0f, 42.5f),
                        PathNode.QuadTo(296.0f, 44.0f, 281.0f, 52.0f),
                        PathNode.Close,
                        PathNode.MoveTo(313.0f, 705.0f),
                        PathNode.QuadTo(327.0f, 709.0f, 354.0f, 710.0f),
                        PathNode.QuadTo(381.0f, 711.0f, 417.0f, 711.0f),
                        PathNode.HorizontalTo(908.0f),
                        PathNode.QuadTo(946.0f, 711.0f, 974.0f, 710.0f),
                        PathNode.QuadTo(1002.0f, 709.0f, 1016.0f, 705.0f),
                        PathNode.QuadTo(1023.0f, 704.0f, 1024.0f, 699.5f),
                        PathNode.QuadTo(1025.0f, 695.0f, 1020.0f, 690.0f),
                        PathNode.LineTo(679.0f, 404.0f),
                        PathNode.QuadTo(672.0f, 398.0f, 664.0f, 398.0f),
                        PathNode.QuadTo(656.0f, 398.0f, 648.0f, 404.0f),
                        PathNode.LineTo(309.0f, 691.0f),
                        PathNode.QuadTo(305.0f, 695.0f, 306.0f, 699.5f),
                        PathNode.QuadTo(307.0f, 704.0f, 313.0f, 705.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _emailHeavy!!
    }

private var _emailHeavy: ImageVector? = null
