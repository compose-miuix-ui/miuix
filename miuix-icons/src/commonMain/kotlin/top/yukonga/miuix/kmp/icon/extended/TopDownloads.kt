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

val MiuixIcons.TopDownloads: ImageVector
    get() = MiuixIcons.Regular.TopDownloads

val MiuixIcons.Light.TopDownloads: ImageVector
    get() {
        if (_topdownloadsLight != null) return _topdownloadsLight!!
        _topdownloadsLight = ImageVector.Builder(
            name = "TopDownloads.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1257.6f,
            viewportHeight = 1257.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -36.200000000000045f, translationY = 973.3f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(344.0f, -103.0f),
                PathNode.QuadTo(347.0f, -78.0f, 364.0f, -60.5f),
                PathNode.QuadTo(381.0f, -43.0f, 407.0f, -39.0f),
                PathNode.LineTo(529.0f, -20.0f),
                PathNode.QuadTo(551.0f, -17.0f, 561.5f, -15.0f),
                PathNode.QuadTo(572.0f, -13.0f, 579.0f, -7.0f),
                PathNode.QuadTo(595.0f, 4.0f, 602.0f, 19.0f),
                PathNode.QuadTo(605.0f, 27.0f, 606.0f, 39.5f),
                PathNode.QuadTo(607.0f, 52.0f, 607.0f, 69.0f),
                PathNode.VerticalTo(151.0f),
                PathNode.QuadTo(521.0f, 164.0f, 450.5f, 215.5f),
                PathNode.QuadTo(380.0f, 267.0f, 340.0f, 344.0f),
                PathNode.QuadTo(300.0f, 421.0f, 300.0f, 510.0f),
                PathNode.VerticalTo(781.0f),
                PathNode.QuadTo(300.0f, 792.0f, 307.5f, 799.0f),
                PathNode.QuadTo(315.0f, 806.0f, 325.0f, 806.0f),
                PathNode.HorizontalTo(1006.0f),
                PathNode.QuadTo(1017.0f, 806.0f, 1024.0f, 799.0f),
                PathNode.QuadTo(1031.0f, 792.0f, 1031.0f, 781.0f),
                PathNode.VerticalTo(510.0f),
                PathNode.QuadTo(1031.0f, 419.0f, 988.5f, 340.0f),
                PathNode.QuadTo(946.0f, 261.0f, 872.5f, 210.0f),
                PathNode.QuadTo(799.0f, 159.0f, 710.0f, 149.0f),
                PathNode.VerticalTo(69.0f),
                PathNode.QuadTo(710.0f, 34.0f, 715.0f, 21.0f),
                PathNode.QuadTo(721.0f, 4.0f, 738.0f, -7.0f),
                PathNode.QuadTo(746.0f, -13.0f, 756.0f, -15.0f),
                PathNode.QuadTo(766.0f, -17.0f, 787.0f, -20.0f),
                PathNode.LineTo(911.0f, -38.0f),
                PathNode.QuadTo(938.0f, -42.0f, 955.0f, -60.5f),
                PathNode.QuadTo(972.0f, -79.0f, 973.0f, -103.0f),
                PathNode.QuadTo(974.0f, -109.0f, 970.0f, -113.0f),
                PathNode.QuadTo(966.0f, -117.0f, 961.0f, -117.0f),
                PathNode.HorizontalTo(357.0f),
                PathNode.QuadTo(351.0f, -117.0f, 347.5f, -113.0f),
                PathNode.QuadTo(344.0f, -109.0f, 344.0f, -103.0f),
                PathNode.Close,
                PathNode.MoveTo(141.0f, 585.0f),
                PathNode.VerticalTo(719.0f),
                PathNode.QuadTo(141.0f, 733.0f, 151.5f, 743.5f),
                PathNode.QuadTo(162.0f, 754.0f, 176.0f, 754.0f),
                PathNode.HorizontalTo(220.0f),
                PathNode.QuadTo(231.0f, 754.0f, 240.5f, 745.0f),
                PathNode.QuadTo(250.0f, 736.0f, 250.0f, 724.0f),
                PathNode.VerticalTo(499.0f),
                PathNode.QuadTo(250.0f, 493.0f, 247.0f, 489.5f),
                PathNode.QuadTo(244.0f, 486.0f, 239.0f, 486.0f),
                PathNode.QuadTo(214.0f, 486.0f, 191.5f, 499.0f),
                PathNode.QuadTo(169.0f, 512.0f, 155.0f, 534.5f),
                PathNode.QuadTo(141.0f, 557.0f, 141.0f, 585.0f),
                PathNode.Close,
                PathNode.MoveTo(1081.0f, 499.0f),
                PathNode.VerticalTo(724.0f),
                PathNode.QuadTo(1081.0f, 736.0f, 1090.0f, 745.0f),
                PathNode.QuadTo(1099.0f, 754.0f, 1111.0f, 754.0f),
                PathNode.HorizontalTo(1154.0f),
                PathNode.QuadTo(1169.0f, 754.0f, 1179.0f, 743.5f),
                PathNode.QuadTo(1189.0f, 733.0f, 1189.0f, 719.0f),
                PathNode.VerticalTo(585.0f),
                PathNode.QuadTo(1189.0f, 557.0f, 1175.5f, 534.5f),
                PathNode.QuadTo(1162.0f, 512.0f, 1139.5f, 499.0f),
                PathNode.QuadTo(1117.0f, 486.0f, 1091.0f, 486.0f),
                PathNode.QuadTo(1086.0f, 486.0f, 1083.5f, 489.5f),
                PathNode.QuadTo(1081.0f, 493.0f, 1081.0f, 499.0f),
                PathNode.Close,
                PathNode.MoveTo(662.0f, 327.0f),
                PathNode.HorizontalTo(691.0f),
                PathNode.QuadTo(696.0f, 327.0f, 699.5f, 331.0f),
                PathNode.QuadTo(703.0f, 335.0f, 703.0f, 339.0f),
                PathNode.VerticalTo(649.0f),
                PathNode.QuadTo(703.0f, 655.0f, 699.5f, 658.0f),
                PathNode.QuadTo(696.0f, 661.0f, 691.0f, 661.0f),
                PathNode.HorizontalTo(669.0f),
                PathNode.QuadTo(659.0f, 661.0f, 654.0f, 658.0f),
                PathNode.LineTo(587.0f, 623.0f),
                PathNode.QuadTo(583.0f, 621.0f, 581.0f, 617.0f),
                PathNode.QuadTo(579.0f, 613.0f, 579.0f, 609.0f),
                PathNode.VerticalTo(580.0f),
                PathNode.QuadTo(579.0f, 573.0f, 583.5f, 570.5f),
                PathNode.QuadTo(588.0f, 568.0f, 594.0f, 571.0f),
                PathNode.LineTo(650.0f, 601.0f),
                PathNode.VerticalTo(339.0f),
                PathNode.QuadTo(650.0f, 335.0f, 654.0f, 331.0f),
                PathNode.QuadTo(658.0f, 327.0f, 662.0f, 327.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _topdownloadsLight!!
    }

private var _topdownloadsLight: ImageVector? = null

val MiuixIcons.Regular.TopDownloads: ImageVector
    get() {
        if (_topdownloadsRegular != null) return _topdownloadsRegular!!
        _topdownloadsRegular = ImageVector.Builder(
            name = "TopDownloads.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1324.8f,
            viewportHeight = 1324.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -2.6000000000000227f, translationY = 1006.9f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(332.0f, -108.0f),
                PathNode.QuadTo(332.0f, -75.0f, 353.0f, -50.5f),
                PathNode.QuadTo(374.0f, -26.0f, 407.0f, -22.0f),
                PathNode.LineTo(529.0f, -4.0f),
                PathNode.QuadTo(544.0f, -2.0f, 555.0f, 0.5f),
                PathNode.QuadTo(566.0f, 3.0f, 572.0f, 7.0f),
                PathNode.QuadTo(585.0f, 15.0f, 590.0f, 27.0f),
                PathNode.QuadTo(593.0f, 33.0f, 593.5f, 44.5f),
                PathNode.QuadTo(594.0f, 56.0f, 594.0f, 71.0f),
                PathNode.VerticalTo(141.0f),
                PathNode.QuadTo(508.0f, 157.0f, 438.5f, 210.0f),
                PathNode.QuadTo(369.0f, 263.0f, 329.5f, 341.0f),
                PathNode.QuadTo(290.0f, 419.0f, 290.0f, 509.0f),
                PathNode.VerticalTo(782.0f),
                PathNode.QuadTo(290.0f, 796.0f, 299.5f, 805.5f),
                PathNode.QuadTo(309.0f, 815.0f, 322.0f, 815.0f),
                PathNode.HorizontalTo(1008.0f),
                PathNode.QuadTo(1022.0f, 815.0f, 1031.5f, 805.5f),
                PathNode.QuadTo(1041.0f, 796.0f, 1041.0f, 782.0f),
                PathNode.VerticalTo(509.0f),
                PathNode.QuadTo(1041.0f, 417.0f, 999.0f, 337.0f),
                PathNode.QuadTo(957.0f, 257.0f, 884.5f, 204.5f),
                PathNode.QuadTo(812.0f, 152.0f, 723.0f, 139.0f),
                PathNode.VerticalTo(71.0f),
                PathNode.QuadTo(723.0f, 40.0f, 727.0f, 29.0f),
                PathNode.QuadTo(732.0f, 15.0f, 745.0f, 7.0f),
                PathNode.QuadTo(751.0f, 3.0f, 762.0f, 0.5f),
                PathNode.QuadTo(773.0f, -2.0f, 788.0f, -4.0f),
                PathNode.LineTo(911.0f, -22.0f),
                PathNode.QuadTo(943.0f, -27.0f, 964.0f, -51.5f),
                PathNode.QuadTo(985.0f, -76.0f, 985.0f, -108.0f),
                PathNode.QuadTo(985.0f, -115.0f, 980.0f, -120.5f),
                PathNode.QuadTo(975.0f, -126.0f, 968.0f, -126.0f),
                PathNode.HorizontalTo(350.0f),
                PathNode.QuadTo(343.0f, -126.0f, 337.5f, -121.0f),
                PathNode.QuadTo(332.0f, -116.0f, 332.0f, -108.0f),
                PathNode.Close,
                PathNode.MoveTo(113.0f, 592.0f),
                PathNode.VerticalTo(715.0f),
                PathNode.QuadTo(113.0f, 735.0f, 127.0f, 749.5f),
                PathNode.QuadTo(141.0f, 764.0f, 161.0f, 764.0f),
                PathNode.HorizontalTo(200.0f),
                PathNode.QuadTo(217.0f, 764.0f, 230.0f, 751.0f),
                PathNode.QuadTo(243.0f, 738.0f, 243.0f, 720.0f),
                PathNode.VerticalTo(487.0f),
                PathNode.QuadTo(243.0f, 482.0f, 239.5f, 479.0f),
                PathNode.QuadTo(236.0f, 476.0f, 231.0f, 476.0f),
                PathNode.QuadTo(199.0f, 476.0f, 172.0f, 491.5f),
                PathNode.QuadTo(145.0f, 507.0f, 129.0f, 534.0f),
                PathNode.QuadTo(113.0f, 561.0f, 113.0f, 592.0f),
                PathNode.Close,
                PathNode.MoveTo(1087.0f, 487.0f),
                PathNode.VerticalTo(720.0f),
                PathNode.QuadTo(1087.0f, 738.0f, 1100.0f, 751.0f),
                PathNode.QuadTo(1113.0f, 764.0f, 1131.0f, 764.0f),
                PathNode.HorizontalTo(1169.0f),
                PathNode.QuadTo(1189.0f, 764.0f, 1203.0f, 749.5f),
                PathNode.QuadTo(1217.0f, 735.0f, 1217.0f, 715.0f),
                PathNode.VerticalTo(592.0f),
                PathNode.QuadTo(1217.0f, 560.0f, 1201.5f, 533.5f),
                PathNode.QuadTo(1186.0f, 507.0f, 1158.5f, 491.5f),
                PathNode.QuadTo(1131.0f, 476.0f, 1099.0f, 476.0f),
                PathNode.QuadTo(1094.0f, 476.0f, 1090.5f, 479.0f),
                PathNode.QuadTo(1087.0f, 482.0f, 1087.0f, 487.0f),
                PathNode.Close,
                PathNode.MoveTo(657.0f, 327.0f),
                PathNode.HorizontalTo(700.0f),
                PathNode.QuadTo(707.0f, 327.0f, 711.0f, 331.0f),
                PathNode.QuadTo(715.0f, 335.0f, 715.0f, 342.0f),
                PathNode.VerticalTo(652.0f),
                PathNode.QuadTo(715.0f, 659.0f, 711.0f, 663.0f),
                PathNode.QuadTo(707.0f, 667.0f, 700.0f, 667.0f),
                PathNode.HorizontalTo(664.0f),
                PathNode.QuadTo(653.0f, 667.0f, 645.0f, 663.0f),
                PathNode.LineTo(577.0f, 628.0f),
                PathNode.QuadTo(572.0f, 625.0f, 569.0f, 620.5f),
                PathNode.QuadTo(566.0f, 616.0f, 566.0f, 610.0f),
                PathNode.VerticalTo(566.0f),
                PathNode.QuadTo(566.0f, 557.0f, 571.0f, 554.0f),
                PathNode.QuadTo(576.0f, 551.0f, 584.0f, 555.0f),
                PathNode.LineTo(642.0f, 585.0f),
                PathNode.VerticalTo(342.0f),
                PathNode.QuadTo(642.0f, 335.0f, 646.0f, 331.0f),
                PathNode.QuadTo(650.0f, 327.0f, 657.0f, 327.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _topdownloadsRegular!!
    }

private var _topdownloadsRegular: ImageVector? = null

val MiuixIcons.Heavy.TopDownloads: ImageVector
    get() {
        if (_topdownloadsHeavy != null) return _topdownloadsHeavy!!
        _topdownloadsHeavy = ImageVector.Builder(
            name = "TopDownloads.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1411.2f,
            viewportHeight = 1411.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 40.60000000000002f, translationY = 1050.1f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(321.0f, -104.0f),
                PathNode.QuadTo(321.0f, -67.0f, 345.5f, -38.5f),
                PathNode.QuadTo(370.0f, -10.0f, 407.0f, -5.0f),
                PathNode.LineTo(528.0f, 13.0f),
                PathNode.QuadTo(543.0f, 15.0f, 552.5f, 16.5f),
                PathNode.QuadTo(562.0f, 18.0f, 565.0f, 21.0f),
                PathNode.QuadTo(573.0f, 26.0f, 578.0f, 35.0f),
                PathNode.QuadTo(580.0f, 40.0f, 580.5f, 49.5f),
                PathNode.QuadTo(581.0f, 59.0f, 581.0f, 73.0f),
                PathNode.VerticalTo(132.0f),
                PathNode.QuadTo(495.0f, 151.0f, 426.5f, 205.0f),
                PathNode.QuadTo(358.0f, 259.0f, 319.0f, 338.5f),
                PathNode.QuadTo(280.0f, 418.0f, 280.0f, 508.0f),
                PathNode.VerticalTo(778.0f),
                PathNode.QuadTo(280.0f, 797.0f, 293.0f, 810.5f),
                PathNode.QuadTo(306.0f, 824.0f, 325.0f, 824.0f),
                PathNode.HorizontalTo(1005.0f),
                PathNode.QuadTo(1024.0f, 824.0f, 1037.5f, 810.5f),
                PathNode.QuadTo(1051.0f, 797.0f, 1051.0f, 778.0f),
                PathNode.VerticalTo(508.0f),
                PathNode.QuadTo(1051.0f, 415.0f, 1010.0f, 334.5f),
                PathNode.QuadTo(969.0f, 254.0f, 897.0f, 200.0f),
                PathNode.QuadTo(825.0f, 146.0f, 736.0f, 130.0f),
                PathNode.VerticalTo(73.0f),
                PathNode.QuadTo(736.0f, 47.0f, 739.0f, 36.0f),
                PathNode.QuadTo(743.0f, 26.0f, 751.0f, 21.0f),
                PathNode.QuadTo(756.0f, 18.0f, 765.5f, 16.5f),
                PathNode.QuadTo(775.0f, 15.0f, 789.0f, 13.0f),
                PathNode.LineTo(911.0f, -5.0f),
                PathNode.QuadTo(947.0f, -10.0f, 971.5f, -38.5f),
                PathNode.QuadTo(996.0f, -67.0f, 996.0f, -104.0f),
                PathNode.QuadTo(996.0f, -117.0f, 987.0f, -126.0f),
                PathNode.QuadTo(978.0f, -135.0f, 965.0f, -135.0f),
                PathNode.HorizontalTo(353.0f),
                PathNode.QuadTo(340.0f, -135.0f, 330.5f, -126.0f),
                PathNode.QuadTo(321.0f, -117.0f, 321.0f, -104.0f),
                PathNode.Close,
                PathNode.MoveTo(77.0f, 590.0f),
                PathNode.VerticalTo(720.0f),
                PathNode.QuadTo(77.0f, 744.0f, 92.0f, 759.0f),
                PathNode.QuadTo(107.0f, 774.0f, 131.0f, 774.0f),
                PathNode.HorizontalTo(187.0f),
                PathNode.QuadTo(207.0f, 774.0f, 220.0f, 761.0f),
                PathNode.QuadTo(233.0f, 748.0f, 233.0f, 727.0f),
                PathNode.VerticalTo(486.0f),
                PathNode.QuadTo(233.0f, 476.0f, 226.0f, 468.5f),
                PathNode.QuadTo(219.0f, 461.0f, 208.0f, 461.0f),
                PathNode.QuadTo(173.0f, 461.0f, 142.5f, 478.5f),
                PathNode.QuadTo(112.0f, 496.0f, 94.5f, 525.5f),
                PathNode.QuadTo(77.0f, 555.0f, 77.0f, 590.0f),
                PathNode.Close,
                PathNode.MoveTo(1097.0f, 486.0f),
                PathNode.VerticalTo(727.0f),
                PathNode.QuadTo(1097.0f, 748.0f, 1110.0f, 761.0f),
                PathNode.QuadTo(1123.0f, 774.0f, 1144.0f, 774.0f),
                PathNode.HorizontalTo(1199.0f),
                PathNode.QuadTo(1224.0f, 774.0f, 1238.5f, 759.0f),
                PathNode.QuadTo(1253.0f, 744.0f, 1253.0f, 720.0f),
                PathNode.VerticalTo(590.0f),
                PathNode.QuadTo(1253.0f, 555.0f, 1235.5f, 525.5f),
                PathNode.QuadTo(1218.0f, 496.0f, 1188.0f, 478.5f),
                PathNode.QuadTo(1158.0f, 461.0f, 1122.0f, 461.0f),
                PathNode.QuadTo(1112.0f, 461.0f, 1104.5f, 468.0f),
                PathNode.QuadTo(1097.0f, 475.0f, 1097.0f, 486.0f),
                PathNode.Close,
                PathNode.MoveTo(657.0f, 318.0f),
                PathNode.HorizontalTo(700.0f),
                PathNode.QuadTo(711.0f, 318.0f, 717.5f, 325.0f),
                PathNode.QuadTo(724.0f, 332.0f, 724.0f, 342.0f),
                PathNode.VerticalTo(652.0f),
                PathNode.QuadTo(724.0f, 663.0f, 717.5f, 669.5f),
                PathNode.QuadTo(711.0f, 676.0f, 700.0f, 676.0f),
                PathNode.HorizontalTo(664.0f),
                PathNode.QuadTo(650.0f, 676.0f, 640.0f, 671.0f),
                PathNode.LineTo(572.0f, 636.0f),
                PathNode.QuadTo(565.0f, 632.0f, 561.0f, 625.0f),
                PathNode.QuadTo(557.0f, 618.0f, 557.0f, 610.0f),
                PathNode.VerticalTo(566.0f),
                PathNode.QuadTo(557.0f, 552.0f, 566.5f, 546.0f),
                PathNode.QuadTo(576.0f, 540.0f, 589.0f, 547.0f),
                PathNode.LineTo(633.0f, 570.0f),
                PathNode.VerticalTo(342.0f),
                PathNode.QuadTo(633.0f, 332.0f, 640.0f, 325.0f),
                PathNode.QuadTo(647.0f, 318.0f, 657.0f, 318.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _topdownloadsHeavy!!
    }

private var _topdownloadsHeavy: ImageVector? = null
