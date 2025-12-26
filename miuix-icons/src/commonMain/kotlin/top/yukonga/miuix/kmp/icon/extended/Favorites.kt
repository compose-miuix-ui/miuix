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
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -19.5f, translationY = 1029.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(702.0f, 796.0f),
                        PathNode.LineTo(694.0f, 789.0f),
                        PathNode.LineTo(676.0f, 771.0f),
                        PathNode.QuadTo(664.0f, 758.0f, 654.0f, 770.0f),
                        PathNode.QuadTo(640.0f, 787.0f, 628.0f, 796.0f),
                        PathNode.QuadTo(587.0f, 830.0f, 537.5f, 848.0f),
                        PathNode.QuadTo(488.0f, 866.0f, 433.0f, 866.0f),
                        PathNode.QuadTo(349.0f, 866.0f, 279.0f, 825.0f),
                        PathNode.QuadTo(209.0f, 784.0f, 168.0f, 714.0f),
                        PathNode.QuadTo(127.0f, 644.0f, 127.0f, 561.0f),
                        PathNode.QuadTo(127.0f, 474.0f, 172.0f, 401.0f),
                        PathNode.QuadTo(228.0f, 306.0f, 356.5f, 176.0f),
                        PathNode.QuadTo(485.0f, 46.0f, 616.0f, -70.0f),
                        PathNode.QuadTo(629.0f, -81.0f, 638.0f, -88.0f),
                        PathNode.QuadTo(647.0f, -95.0f, 653.0f, -96.0f),
                        PathNode.QuadTo(667.0f, -100.0f, 678.0f, -96.0f),
                        PathNode.QuadTo(684.0f, -95.0f, 693.5f, -87.5f),
                        PathNode.QuadTo(703.0f, -80.0f, 715.0f, -69.0f),
                        PathNode.QuadTo(841.0f, 43.0f, 964.5f, 167.0f),
                        PathNode.QuadTo(1088.0f, 291.0f, 1150.0f, 389.0f),
                        PathNode.QuadTo(1202.0f, 465.0f, 1202.0f, 561.0f),
                        PathNode.QuadTo(1202.0f, 644.0f, 1161.0f, 714.0f),
                        PathNode.QuadTo(1120.0f, 784.0f, 1050.0f, 825.0f),
                        PathNode.QuadTo(980.0f, 866.0f, 897.0f, 866.0f),
                        PathNode.QuadTo(843.0f, 866.0f, 793.0f, 847.5f),
                        PathNode.QuadTo(743.0f, 829.0f, 702.0f, 796.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1143.0f, 561.0f),
                        PathNode.QuadTo(1143.0f, 486.0f, 1101.0f, 422.0f),
                        PathNode.QuadTo(1052.0f, 347.0f, 962.0f, 252.5f),
                        PathNode.QuadTo(872.0f, 158.0f, 814.0f, 104.0f),
                        PathNode.QuadTo(755.0f, 46.0f, 682.0f, -18.0f),
                        PathNode.QuadTo(672.0f, -27.0f, 665.0f, -27.0f),
                        PathNode.QuadTo(658.0f, -27.0f, 648.0f, -17.0f),
                        PathNode.QuadTo(560.0f, 61.0f, 506.0f, 113.0f),
                        PathNode.QuadTo(287.0f, 322.0f, 223.0f, 434.0f),
                        PathNode.QuadTo(202.0f, 469.0f, 194.5f, 498.0f),
                        PathNode.QuadTo(187.0f, 527.0f, 187.0f, 561.0f),
                        PathNode.QuadTo(187.0f, 628.0f, 220.0f, 684.0f),
                        PathNode.QuadTo(253.0f, 740.0f, 309.5f, 773.0f),
                        PathNode.QuadTo(366.0f, 806.0f, 433.0f, 806.0f),
                        PathNode.QuadTo(485.0f, 806.0f, 531.0f, 786.0f),
                        PathNode.QuadTo(577.0f, 766.0f, 611.0f, 729.0f),
                        PathNode.QuadTo(624.0f, 715.0f, 632.5f, 707.0f),
                        PathNode.QuadTo(641.0f, 699.0f, 648.0f, 695.0f),
                        PathNode.QuadTo(666.0f, 689.0f, 682.0f, 696.0f),
                        PathNode.QuadTo(688.0f, 699.0f, 697.0f, 707.0f),
                        PathNode.QuadTo(706.0f, 715.0f, 719.0f, 729.0f),
                        PathNode.QuadTo(752.0f, 765.0f, 798.5f, 785.5f),
                        PathNode.QuadTo(845.0f, 806.0f, 897.0f, 806.0f),
                        PathNode.QuadTo(964.0f, 806.0f, 1020.5f, 773.0f),
                        PathNode.QuadTo(1077.0f, 740.0f, 1110.0f, 684.0f),
                        PathNode.QuadTo(1143.0f, 628.0f, 1143.0f, 561.0f),
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
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -11.100000000000023f, translationY = 1037.15f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(692.0f, 801.0f),
                        PathNode.LineTo(687.0f, 797.0f),
                        PathNode.LineTo(676.0f, 786.0f),
                        PathNode.QuadTo(664.0f, 774.0f, 653.0f, 786.0f),
                        PathNode.QuadTo(646.0f, 794.0f, 637.0f, 801.0f),
                        PathNode.QuadTo(595.0f, 835.0f, 543.5f, 854.0f),
                        PathNode.QuadTo(492.0f, 873.0f, 436.0f, 873.0f),
                        PathNode.QuadTo(350.0f, 873.0f, 277.5f, 831.0f),
                        PathNode.QuadTo(205.0f, 789.0f, 162.5f, 717.0f),
                        PathNode.QuadTo(120.0f, 645.0f, 120.0f, 559.0f),
                        PathNode.QuadTo(120.0f, 469.0f, 167.0f, 394.0f),
                        PathNode.QuadTo(223.0f, 299.0f, 350.5f, 170.0f),
                        PathNode.QuadTo(478.0f, 41.0f, 608.0f, -74.0f),
                        PathNode.QuadTo(621.0f, -86.0f, 631.0f, -93.5f),
                        PathNode.QuadTo(641.0f, -101.0f, 649.0f, -103.0f),
                        PathNode.QuadTo(666.0f, -108.0f, 682.0f, -103.0f),
                        PathNode.QuadTo(689.0f, -101.0f, 699.5f, -93.0f),
                        PathNode.QuadTo(710.0f, -85.0f, 723.0f, -73.0f),
                        PathNode.QuadTo(847.0f, 38.0f, 970.0f, 161.0f),
                        PathNode.QuadTo(1093.0f, 284.0f, 1155.0f, 381.0f),
                        PathNode.QuadTo(1209.0f, 460.0f, 1209.0f, 559.0f),
                        PathNode.QuadTo(1209.0f, 645.0f, 1166.5f, 717.0f),
                        PathNode.QuadTo(1124.0f, 789.0f, 1052.0f, 831.0f),
                        PathNode.QuadTo(980.0f, 873.0f, 894.0f, 873.0f),
                        PathNode.QuadTo(838.0f, 873.0f, 786.0f, 854.0f),
                        PathNode.QuadTo(734.0f, 835.0f, 692.0f, 801.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1123.0f, 559.0f),
                        PathNode.QuadTo(1123.0f, 488.0f, 1084.0f, 429.0f),
                        PathNode.QuadTo(1037.0f, 357.0f, 952.0f, 267.5f),
                        PathNode.QuadTo(867.0f, 178.0f, 803.0f, 117.0f),
                        PathNode.QuadTo(742.0f, 59.0f, 680.0f, 4.0f),
                        PathNode.QuadTo(670.0f, -5.0f, 665.0f, -5.0f),
                        PathNode.QuadTo(660.0f, -5.0f, 650.0f, 4.0f),
                        PathNode.QuadTo(583.0f, 64.0f, 518.0f, 126.0f),
                        PathNode.QuadTo(303.0f, 331.0f, 240.0f, 440.0f),
                        PathNode.QuadTo(220.0f, 473.0f, 213.5f, 500.0f),
                        PathNode.QuadTo(207.0f, 527.0f, 207.0f, 559.0f),
                        PathNode.QuadTo(207.0f, 621.0f, 237.5f, 673.5f),
                        PathNode.QuadTo(268.0f, 726.0f, 321.0f, 756.5f),
                        PathNode.QuadTo(374.0f, 787.0f, 436.0f, 787.0f),
                        PathNode.QuadTo(484.0f, 787.0f, 527.0f, 768.0f),
                        PathNode.QuadTo(570.0f, 749.0f, 602.0f, 715.0f),
                        PathNode.QuadTo(615.0f, 701.0f, 625.0f, 691.5f),
                        PathNode.QuadTo(635.0f, 682.0f, 643.0f, 679.0f),
                        PathNode.QuadTo(665.0f, 670.0f, 687.0f, 679.0f),
                        PathNode.QuadTo(695.0f, 683.0f, 705.0f, 692.0f),
                        PathNode.QuadTo(715.0f, 701.0f, 728.0f, 715.0f),
                        PathNode.QuadTo(759.0f, 749.0f, 802.5f, 768.0f),
                        PathNode.QuadTo(846.0f, 787.0f, 894.0f, 787.0f),
                        PathNode.QuadTo(956.0f, 787.0f, 1009.0f, 756.5f),
                        PathNode.QuadTo(1062.0f, 726.0f, 1092.5f, 673.5f),
                        PathNode.QuadTo(1123.0f, 621.0f, 1123.0f, 559.0f),
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

val MiuixIcons.Heavy.Favorites: ImageVector
    get() {
        if (_favoritesHeavy != null) return _favoritesHeavy!!
        _favoritesHeavy = ImageVector.Builder(
            name = "Favorites.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1326.0f,
            viewportHeight = 1326.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -1.5f, translationY = 1046.8636363636365f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(688.0f, 812.0f),
                        PathNode.LineTo(682.0f, 807.0f),
                        PathNode.LineTo(672.0f, 798.0f),
                        PathNode.QuadTo(665.0f, 791.0f, 657.0f, 798.0f),
                        PathNode.QuadTo(655.0f, 801.0f, 643.0f, 811.0f),
                        PathNode.QuadTo(601.0f, 844.0f, 549.0f, 863.0f),
                        PathNode.QuadTo(497.0f, 882.0f, 439.0f, 882.0f),
                        PathNode.QuadTo(350.0f, 882.0f, 275.0f, 838.5f),
                        PathNode.QuadTo(200.0f, 795.0f, 156.0f, 720.5f),
                        PathNode.QuadTo(112.0f, 646.0f, 112.0f, 557.0f),
                        PathNode.QuadTo(112.0f, 462.0f, 160.0f, 386.0f),
                        PathNode.QuadTo(216.0f, 291.0f, 341.5f, 164.0f),
                        PathNode.QuadTo(467.0f, 37.0f, 598.0f, -80.0f),
                        PathNode.QuadTo(612.0f, -92.0f, 623.5f, -100.5f),
                        PathNode.QuadTo(635.0f, -109.0f, 645.0f, -112.0f),
                        PathNode.QuadTo(667.0f, -117.0f, 686.0f, -111.0f),
                        PathNode.QuadTo(695.0f, -109.0f, 707.0f, -100.0f),
                        PathNode.QuadTo(719.0f, -91.0f, 733.0f, -79.0f),
                        PathNode.QuadTo(859.0f, 34.0f, 979.5f, 155.0f),
                        PathNode.QuadTo(1100.0f, 276.0f, 1161.0f, 372.0f),
                        PathNode.QuadTo(1217.0f, 455.0f, 1217.0f, 557.0f),
                        PathNode.QuadTo(1217.0f, 646.0f, 1173.0f, 720.5f),
                        PathNode.QuadTo(1129.0f, 795.0f, 1054.5f, 838.5f),
                        PathNode.QuadTo(980.0f, 882.0f, 891.0f, 882.0f),
                        PathNode.QuadTo(833.0f, 882.0f, 780.5f, 863.5f),
                        PathNode.QuadTo(728.0f, 845.0f, 688.0f, 812.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1101.0f, 557.0f),
                        PathNode.QuadTo(1101.0f, 492.0f, 1065.0f, 437.0f),
                        PathNode.QuadTo(1019.0f, 367.0f, 935.0f, 278.0f),
                        PathNode.QuadTo(851.0f, 189.0f, 791.0f, 132.0f),
                        PathNode.QuadTo(705.0f, 51.0f, 676.0f, 27.0f),
                        PathNode.QuadTo(669.0f, 20.0f, 665.0f, 20.0f),
                        PathNode.QuadTo(661.0f, 20.0f, 654.0f, 27.0f),
                        PathNode.QuadTo(613.0f, 62.0f, 531.0f, 141.0f),
                        PathNode.QuadTo(319.0f, 344.0f, 259.0f, 447.0f),
                        PathNode.QuadTo(241.0f, 478.0f, 235.0f, 502.5f),
                        PathNode.QuadTo(229.0f, 527.0f, 229.0f, 557.0f),
                        PathNode.QuadTo(229.0f, 614.0f, 257.0f, 662.0f),
                        PathNode.QuadTo(285.0f, 710.0f, 333.5f, 738.0f),
                        PathNode.QuadTo(382.0f, 766.0f, 439.0f, 766.0f),
                        PathNode.QuadTo(483.0f, 766.0f, 522.5f, 748.5f),
                        PathNode.QuadTo(562.0f, 731.0f, 591.0f, 700.0f),
                        PathNode.QuadTo(605.0f, 685.0f, 616.0f, 675.0f),
                        PathNode.QuadTo(627.0f, 665.0f, 637.0f, 661.0f),
                        PathNode.QuadTo(665.0f, 649.0f, 693.0f, 661.0f),
                        PathNode.QuadTo(702.0f, 665.0f, 712.0f, 673.5f),
                        PathNode.QuadTo(722.0f, 682.0f, 739.0f, 700.0f),
                        PathNode.QuadTo(767.0f, 731.0f, 806.5f, 748.5f),
                        PathNode.QuadTo(846.0f, 766.0f, 891.0f, 766.0f),
                        PathNode.QuadTo(948.0f, 766.0f, 996.5f, 738.0f),
                        PathNode.QuadTo(1045.0f, 710.0f, 1073.0f, 662.0f),
                        PathNode.QuadTo(1101.0f, 614.0f, 1101.0f, 557.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesHeavy!!
    }

private var _favoritesHeavy: ImageVector? = null
