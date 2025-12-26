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
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -27.899999999999977f, translationY = 1007.2382978723405f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1195.0f, 468.0f),
                        PathNode.QuadTo(1195.0f, 577.0f, 1176.0f, 669.0f),
                        PathNode.QuadTo(1157.0f, 761.0f, 1126.0f, 814.5f),
                        PathNode.QuadTo(1095.0f, 868.0f, 1061.0f, 868.0f),
                        PathNode.QuadTo(1049.0f, 868.0f, 1043.0f, 865.0f),
                        PathNode.QuadTo(1009.0f, 852.0f, 959.0f, 829.0f),
                        PathNode.QuadTo(909.0f, 806.0f, 841.0f, 773.0f),
                        PathNode.QuadTo(715.0f, 712.0f, 590.0f, 685.0f),
                        PathNode.QuadTo(581.0f, 672.0f, 572.0f, 640.0f),
                        PathNode.QuadTo(553.0f, 569.0f, 553.0f, 465.0f),
                        PathNode.QuadTo(553.0f, 361.0f, 573.0f, 291.0f),
                        PathNode.QuadTo(579.0f, 268.0f, 591.0f, 248.0f),
                        PathNode.QuadTo(704.0f, 221.0f, 813.0f, 172.0f),
                        PathNode.QuadTo(906.0f, 128.0f, 961.0f, 103.5f),
                        PathNode.QuadTo(1016.0f, 79.0f, 1047.0f, 69.0f),
                        PathNode.QuadTo(1052.0f, 68.0f, 1061.0f, 68.0f),
                        PathNode.QuadTo(1095.0f, 68.0f, 1126.0f, 121.5f),
                        PathNode.QuadTo(1157.0f, 175.0f, 1176.0f, 267.0f),
                        PathNode.QuadTo(1195.0f, 359.0f, 1195.0f, 468.0f),
                        PathNode.Close,
                        PathNode.MoveTo(607.0f, -37.0f),
                        PathNode.LineTo(527.0f, 267.0f),
                        PathNode.QuadTo(514.0f, 316.0f, 508.0f, 387.5f),
                        PathNode.QuadTo(502.0f, 459.0f, 506.5f, 536.0f),
                        PathNode.QuadTo(511.0f, 613.0f, 529.0f, 677.0f),
                        PathNode.HorizontalTo(346.0f),
                        PathNode.QuadTo(289.0f, 677.0f, 240.0f, 648.5f),
                        PathNode.QuadTo(191.0f, 620.0f, 162.5f, 571.0f),
                        PathNode.QuadTo(134.0f, 522.0f, 134.0f, 465.0f),
                        PathNode.QuadTo(134.0f, 407.0f, 162.5f, 358.0f),
                        PathNode.QuadTo(191.0f, 309.0f, 239.5f, 280.5f),
                        PathNode.QuadTo(288.0f, 252.0f, 346.0f, 252.0f),
                        PathNode.HorizontalTo(388.0f),
                        PathNode.LineTo(468.0f, -72.0f),
                        PathNode.QuadTo(475.0f, -101.0f, 500.5f, -116.5f),
                        PathNode.QuadTo(526.0f, -132.0f, 555.0f, -124.0f),
                        PathNode.QuadTo(585.0f, -117.0f, 600.0f, -91.5f),
                        PathNode.QuadTo(615.0f, -66.0f, 607.0f, -37.0f),
                        PathNode.Close,
                        PathNode.MoveTo(984.0f, 469.0f),
                        PathNode.QuadTo(984.0f, 557.0f, 993.0f, 628.5f),
                        PathNode.QuadTo(1002.0f, 700.0f, 1019.5f, 741.5f),
                        PathNode.QuadTo(1037.0f, 783.0f, 1060.0f, 783.0f),
                        PathNode.QuadTo(1081.0f, 783.0f, 1098.5f, 739.5f),
                        PathNode.QuadTo(1116.0f, 696.0f, 1126.0f, 624.0f),
                        PathNode.QuadTo(1136.0f, 552.0f, 1136.0f, 469.0f),
                        PathNode.QuadTo(1136.0f, 385.0f, 1126.0f, 313.0f),
                        PathNode.QuadTo(1116.0f, 241.0f, 1098.5f, 197.5f),
                        PathNode.QuadTo(1081.0f, 154.0f, 1060.0f, 154.0f),
                        PathNode.QuadTo(1037.0f, 154.0f, 1019.5f, 195.5f),
                        PathNode.QuadTo(1002.0f, 237.0f, 993.0f, 308.5f),
                        PathNode.QuadTo(984.0f, 380.0f, 984.0f, 469.0f),
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
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 3.3999999999999773f, translationY = 1040.1692307692306f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1222.0f, 467.0f),
                        PathNode.QuadTo(1222.0f, 578.0f, 1202.5f, 671.5f),
                        PathNode.QuadTo(1183.0f, 765.0f, 1149.5f, 819.5f),
                        PathNode.QuadTo(1116.0f, 874.0f, 1076.0f, 874.0f),
                        PathNode.QuadTo(1066.0f, 874.0f, 1057.0f, 871.0f),
                        PathNode.QuadTo(1029.0f, 862.0f, 985.0f, 841.5f),
                        PathNode.QuadTo(941.0f, 821.0f, 854.0f, 779.0f),
                        PathNode.QuadTo(733.0f, 720.0f, 605.0f, 692.0f),
                        PathNode.QuadTo(595.0f, 678.0f, 583.0f, 640.0f),
                        PathNode.QuadTo(564.0f, 569.0f, 564.0f, 464.5f),
                        PathNode.QuadTo(564.0f, 360.0f, 584.0f, 290.0f),
                        PathNode.QuadTo(592.0f, 258.0f, 606.0f, 239.0f),
                        PathNode.QuadTo(716.0f, 215.0f, 827.0f, 164.0f),
                        PathNode.QuadTo(922.0f, 119.0f, 975.0f, 95.5f),
                        PathNode.QuadTo(1028.0f, 72.0f, 1059.0f, 62.0f),
                        PathNode.QuadTo(1065.0f, 60.0f, 1076.0f, 60.0f),
                        PathNode.QuadTo(1116.0f, 60.0f, 1149.5f, 114.5f),
                        PathNode.QuadTo(1183.0f, 169.0f, 1202.5f, 262.5f),
                        PathNode.QuadTo(1222.0f, 356.0f, 1222.0f, 467.0f),
                        PathNode.Close,
                        PathNode.MoveTo(599.0f, -25.0f),
                        PathNode.LineTo(520.0f, 273.0f),
                        PathNode.QuadTo(507.0f, 323.0f, 501.0f, 395.5f),
                        PathNode.QuadTo(495.0f, 468.0f, 501.0f, 546.0f),
                        PathNode.QuadTo(507.0f, 624.0f, 528.0f, 686.0f),
                        PathNode.HorizontalTo(330.0f),
                        PathNode.QuadTo(270.0f, 686.0f, 219.0f, 656.0f),
                        PathNode.QuadTo(168.0f, 626.0f, 138.0f, 575.0f),
                        PathNode.QuadTo(108.0f, 524.0f, 108.0f, 464.0f),
                        PathNode.QuadTo(108.0f, 403.0f, 138.0f, 352.0f),
                        PathNode.QuadTo(168.0f, 301.0f, 219.0f, 271.0f),
                        PathNode.QuadTo(270.0f, 241.0f, 330.0f, 241.0f),
                        PathNode.HorizontalTo(360.0f),
                        PathNode.LineTo(436.0f, -66.0f),
                        PathNode.QuadTo(445.0f, -100.0f, 474.5f, -118.0f),
                        PathNode.QuadTo(504.0f, -136.0f, 538.0f, -128.0f),
                        PathNode.QuadTo(572.0f, -119.0f, 590.0f, -89.0f),
                        PathNode.QuadTo(608.0f, -59.0f, 599.0f, -25.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1015.0f, 468.0f),
                        PathNode.QuadTo(1015.0f, 548.0f, 1023.0f, 616.0f),
                        PathNode.QuadTo(1031.0f, 684.0f, 1045.0f, 723.5f),
                        PathNode.QuadTo(1059.0f, 763.0f, 1076.0f, 763.0f),
                        PathNode.QuadTo(1092.0f, 763.0f, 1106.0f, 723.5f),
                        PathNode.QuadTo(1120.0f, 684.0f, 1128.0f, 616.0f),
                        PathNode.QuadTo(1136.0f, 548.0f, 1136.0f, 468.0f),
                        PathNode.QuadTo(1136.0f, 387.0f, 1128.0f, 319.0f),
                        PathNode.QuadTo(1120.0f, 251.0f, 1106.0f, 211.5f),
                        PathNode.QuadTo(1092.0f, 172.0f, 1076.0f, 172.0f),
                        PathNode.QuadTo(1059.0f, 172.0f, 1045.0f, 211.5f),
                        PathNode.QuadTo(1031.0f, 251.0f, 1023.0f, 319.0f),
                        PathNode.QuadTo(1015.0f, 387.0f, 1015.0f, 468.0f),
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

val MiuixIcons.Heavy.Promotions: ImageVector
    get() {
        if (_promotionsHeavy != null) return _promotionsHeavy!!
        _promotionsHeavy = ImageVector.Builder(
            name = "Promotions.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1405.2f,
            viewportHeight = 1405.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 38.10000000000002f, translationY = 1074.4870967741936f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1250.0f, 467.0f),
                        PathNode.QuadTo(1250.0f, 581.0f, 1229.5f, 677.5f),
                        PathNode.QuadTo(1209.0f, 774.0f, 1172.5f, 831.0f),
                        PathNode.QuadTo(1136.0f, 888.0f, 1091.0f, 888.0f),
                        PathNode.QuadTo(1080.0f, 888.0f, 1068.0f, 884.0f),
                        PathNode.QuadTo(1040.0f, 875.0f, 997.0f, 855.5f),
                        PathNode.QuadTo(954.0f, 836.0f, 863.0f, 791.0f),
                        PathNode.QuadTo(739.0f, 732.0f, 612.0f, 704.0f),
                        PathNode.QuadTo(599.0f, 689.0f, 584.0f, 644.0f),
                        PathNode.QuadTo(565.0f, 571.0f, 565.0f, 464.5f),
                        PathNode.QuadTo(565.0f, 358.0f, 585.0f, 286.0f),
                        PathNode.QuadTo(597.0f, 247.0f, 613.0f, 227.0f),
                        PathNode.QuadTo(722.0f, 203.0f, 836.0f, 152.0f),
                        PathNode.QuadTo(935.0f, 105.0f, 987.0f, 82.0f),
                        PathNode.QuadTo(1039.0f, 59.0f, 1070.0f, 49.0f),
                        PathNode.QuadTo(1079.0f, 46.0f, 1091.0f, 46.0f),
                        PathNode.QuadTo(1136.0f, 46.0f, 1172.5f, 103.0f),
                        PathNode.QuadTo(1209.0f, 160.0f, 1229.5f, 256.5f),
                        PathNode.QuadTo(1250.0f, 353.0f, 1250.0f, 467.0f),
                        PathNode.Close,
                        PathNode.MoveTo(597.0f, -21.0f),
                        PathNode.LineTo(518.0f, 277.0f),
                        PathNode.QuadTo(504.0f, 328.0f, 498.5f, 404.0f),
                        PathNode.QuadTo(493.0f, 480.0f, 501.0f, 559.5f),
                        PathNode.QuadTo(509.0f, 639.0f, 533.0f, 700.0f),
                        PathNode.HorizontalTo(315.0f),
                        PathNode.QuadTo(251.0f, 700.0f, 197.0f, 668.0f),
                        PathNode.QuadTo(143.0f, 636.0f, 111.0f, 581.5f),
                        PathNode.QuadTo(79.0f, 527.0f, 79.0f, 464.0f),
                        PathNode.QuadTo(79.0f, 400.0f, 110.5f, 345.5f),
                        PathNode.QuadTo(142.0f, 291.0f, 196.5f, 259.0f),
                        PathNode.QuadTo(251.0f, 227.0f, 315.0f, 227.0f),
                        PathNode.HorizontalTo(334.0f),
                        PathNode.LineTo(408.0f, -69.0f),
                        PathNode.QuadTo(418.0f, -109.0f, 452.5f, -130.0f),
                        PathNode.QuadTo(487.0f, -151.0f, 526.0f, -141.0f),
                        PathNode.QuadTo(566.0f, -130.0f, 586.5f, -95.5f),
                        PathNode.QuadTo(607.0f, -61.0f, 597.0f, -21.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1046.0f, 468.0f),
                        PathNode.QuadTo(1046.0f, 547.0f, 1053.0f, 612.0f),
                        PathNode.QuadTo(1060.0f, 677.0f, 1071.0f, 714.5f),
                        PathNode.QuadTo(1082.0f, 752.0f, 1095.0f, 752.0f),
                        PathNode.QuadTo(1107.0f, 752.0f, 1118.5f, 715.0f),
                        PathNode.QuadTo(1130.0f, 678.0f, 1137.0f, 612.5f),
                        PathNode.QuadTo(1144.0f, 547.0f, 1144.0f, 468.0f),
                        PathNode.QuadTo(1144.0f, 389.0f, 1137.5f, 324.0f),
                        PathNode.QuadTo(1131.0f, 259.0f, 1119.5f, 221.5f),
                        PathNode.QuadTo(1108.0f, 184.0f, 1096.0f, 184.0f),
                        PathNode.QuadTo(1083.0f, 184.0f, 1071.5f, 221.5f),
                        PathNode.QuadTo(1060.0f, 259.0f, 1053.0f, 324.0f),
                        PathNode.QuadTo(1046.0f, 389.0f, 1046.0f, 468.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsHeavy!!
    }

private var _promotionsHeavy: ImageVector? = null
