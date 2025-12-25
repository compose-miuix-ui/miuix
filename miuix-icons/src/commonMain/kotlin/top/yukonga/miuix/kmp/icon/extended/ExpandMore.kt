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

val MiuixIcons.ExpandMore: ImageVector
    get() = MiuixIcons.Regular.ExpandMore

val MiuixIcons.Light.ExpandMore: ImageVector
    get() {
        if (_expandmoreLight != null) return _expandmoreLight!!
        _expandmoreLight = ImageVector.Builder(
            name = "ExpandMore.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1088.3999999999999f,
            viewportHeight = 1088.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -121.30000000000007f, translationY = 919.6999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(274.0f, 482.0f),
                PathNode.VerticalTo(761.5817490494296f),
                PathNode.QuadTo(274.0f, 763.7490494296578f, 275.6254752851711f, 765.3745247148289f),
                PathNode.QuadTo(277.2509505703422f, 767.0f, 279.4182509505703f, 767.0f),
                PathNode.HorizontalTo(559.0f),
                PathNode.QuadTo(567.0f, 767.0f, 572.0f, 772.2702702702702f),
                PathNode.QuadTo(577.0f, 777.5405405405405f, 577.0f, 785.2702702702703f),
                PathNode.VerticalTo(811.2702702702703f),
                PathNode.QuadTo(577.0f, 819.0f, 572.0f, 824.0f),
                PathNode.QuadTo(567.0f, 829.0f, 559.0f, 829.0f),
                PathNode.HorizontalTo(252.0f),
                PathNode.QuadTo(232.66666666666666f, 829.0f, 222.33333333333331f, 818.6666666666667f),
                PathNode.QuadTo(212.0f, 808.3333333333334f, 212.0f, 789.0f),
                PathNode.VerticalTo(482.0f),
                PathNode.QuadTo(212.0f, 474.6666666666667f, 217.33333333333331f, 469.33333333333337f),
                PathNode.QuadTo(222.66666666666666f, 464.0f, 230.0f, 464.0f),
                PathNode.HorizontalTo(255.84126984126985f),
                PathNode.QuadTo(263.5238095238095f, 464.0f, 268.76190476190476f, 469.33333333333337f),
                PathNode.QuadTo(274.0f, 474.6666666666667f, 274.0f, 482.0f),
                PathNode.Close,
                PathNode.MoveTo(746.0f, 374.9259259259259f),
                PathNode.QuadTo(746.0f, 409.0f, 722.1481481481482f, 432.5f),
                PathNode.QuadTo(698.2962962962963f, 456.0f, 665.074074074074f, 456.0f),
                PathNode.QuadTo(631.0f, 456.0f, 607.5f, 432.6044776119403f),
                PathNode.QuadTo(584.0f, 409.2089552238806f, 584.0f, 375.17910447761193f),
                PathNode.QuadTo(584.0f, 342.0f, 607.3955223880597f, 318.0f),
                PathNode.QuadTo(630.7910447761194f, 294.0f, 664.820895522388f, 294.0f),
                PathNode.QuadTo(698.0f, 294.0f, 722.0f, 317.85185185185185f),
                PathNode.QuadTo(746.0f, 341.7037037037037f, 746.0f, 374.9259259259259f),
                PathNode.Close,
                PathNode.MoveTo(1119.0f, -38.0f),
                PathNode.VerticalTo(256.0f),
                PathNode.QuadTo(1119.0f, 264.0f, 1114.0f, 269.0f),
                PathNode.QuadTo(1109.0f, 274.0f, 1101.2083333333333f, 274.0f),
                PathNode.HorizontalTo(1075.0f),
                PathNode.QuadTo(1067.6666666666667f, 274.0f, 1062.3333333333335f, 269.0f),
                PathNode.QuadTo(1057.0f, 264.0f, 1057.0f, 256.0f),
                PathNode.VerticalTo(-10.0f),
                PathNode.QuadTo(1057.0f, -16.0f, 1052.0f, -16.0f),
                PathNode.HorizontalTo(771.0f),
                PathNode.QuadTo(764.4615384615385f, -16.0f, 759.2307692307693f, -21.439999999999998f),
                PathNode.QuadTo(754.0f, -26.88f, 754.0f, -33.68f),
                PathNode.VerticalTo(-59.519999999999996f),
                PathNode.QuadTo(754.0f, -67.0f, 759.2307692307693f, -72.5f),
                PathNode.QuadTo(764.4615384615385f, -78.0f, 771.0f, -78.0f),
                PathNode.HorizontalTo(1079.0f),
                PathNode.QuadTo(1098.3333333333333f, -78.0f, 1108.6666666666665f, -67.66666666666666f),
                PathNode.QuadTo(1119.0f, -57.33333333333333f, 1119.0f, -38.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _expandmoreLight!!
    }

private var _expandmoreLight: ImageVector? = null

val MiuixIcons.Regular.ExpandMore: ImageVector
    get() {
        if (_expandmoreRegular != null) return _expandmoreRegular!!
        _expandmoreRegular = ImageVector.Builder(
            name = "ExpandMore.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1122.0f,
            viewportHeight = 1122.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -104.5f, translationY = 936.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(288.0f, 490.0f),
                PathNode.VerticalTo(748.0f),
                PathNode.QuadTo(288.0f, 750.0f, 289.5f, 751.5f),
                PathNode.QuadTo(291.0f, 753.0f, 293.0f, 753.0f),
                PathNode.HorizontalTo(551.0f),
                PathNode.QuadTo(562.0f, 753.0f, 570.0f, 760.5f),
                PathNode.QuadTo(578.0f, 768.0f, 578.0f, 779.0f),
                PathNode.VerticalTo(816.0f),
                PathNode.QuadTo(578.0f, 827.0f, 570.0f, 835.0f),
                PathNode.QuadTo(562.0f, 843.0f, 551.0f, 843.0f),
                PathNode.HorizontalTo(258.0f),
                PathNode.QuadTo(229.0f, 843.0f, 213.5f, 827.5f),
                PathNode.QuadTo(198.0f, 812.0f, 198.0f, 783.0f),
                PathNode.VerticalTo(490.0f),
                PathNode.QuadTo(198.0f, 479.0f, 206.0f, 471.0f),
                PathNode.QuadTo(214.0f, 463.0f, 225.0f, 463.0f),
                PathNode.HorizontalTo(262.0f),
                PathNode.QuadTo(273.0f, 463.0f, 280.5f, 471.0f),
                PathNode.QuadTo(288.0f, 479.0f, 288.0f, 490.0f),
                PathNode.Close,
                PathNode.MoveTo(760.0f, 375.0f),
                PathNode.QuadTo(760.0f, 415.0f, 732.0f, 442.5f),
                PathNode.QuadTo(704.0f, 470.0f, 665.0f, 470.0f),
                PathNode.QuadTo(625.0f, 470.0f, 597.5f, 442.5f),
                PathNode.QuadTo(570.0f, 415.0f, 570.0f, 375.0f),
                PathNode.QuadTo(570.0f, 336.0f, 597.5f, 308.0f),
                PathNode.QuadTo(625.0f, 280.0f, 665.0f, 280.0f),
                PathNode.QuadTo(704.0f, 280.0f, 732.0f, 308.0f),
                PathNode.QuadTo(760.0f, 336.0f, 760.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(1133.0f, -32.0f),
                PathNode.VerticalTo(261.0f),
                PathNode.QuadTo(1133.0f, 272.0f, 1125.0f, 280.0f),
                PathNode.QuadTo(1117.0f, 288.0f, 1106.0f, 288.0f),
                PathNode.HorizontalTo(1069.0f),
                PathNode.QuadTo(1058.0f, 288.0f, 1050.0f, 280.0f),
                PathNode.QuadTo(1042.0f, 272.0f, 1042.0f, 261.0f),
                PathNode.VerticalTo(3.0f),
                PathNode.QuadTo(1042.0f, -2.0f, 1038.0f, -2.0f),
                PathNode.HorizontalTo(779.0f),
                PathNode.QuadTo(769.0f, -2.0f, 761.0f, -10.0f),
                PathNode.QuadTo(753.0f, -18.0f, 753.0f, -28.0f),
                PathNode.VerticalTo(-66.0f),
                PathNode.QuadTo(753.0f, -77.0f, 761.0f, -84.5f),
                PathNode.QuadTo(769.0f, -92.0f, 779.0f, -92.0f),
                PathNode.HorizontalTo(1073.0f),
                PathNode.QuadTo(1102.0f, -92.0f, 1117.5f, -76.5f),
                PathNode.QuadTo(1133.0f, -61.0f, 1133.0f, -32.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _expandmoreRegular!!
    }

private var _expandmoreRegular: ImageVector? = null

val MiuixIcons.Heavy.ExpandMore: ImageVector
    get() {
        if (_expandmoreHeavy != null) return _expandmoreHeavy!!
        _expandmoreHeavy = ImageVector.Builder(
            name = "ExpandMore.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1155.6f,
            viewportHeight = 1155.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -87.70000000000005f, translationY = 953.3f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(302.0f, 490.0f),
                PathNode.VerticalTo(734.0f),
                PathNode.QuadTo(302.0f, 736.0f, 303.5f, 737.5f),
                PathNode.QuadTo(305.0f, 739.0f, 307.0f, 739.0f),
                PathNode.HorizontalTo(551.0f),
                PathNode.QuadTo(567.0f, 739.0f, 579.5f, 751.0f),
                PathNode.QuadTo(592.0f, 763.0f, 592.0f, 779.0f),
                PathNode.VerticalTo(816.0f),
                PathNode.QuadTo(592.0f, 833.0f, 580.0f, 845.0f),
                PathNode.QuadTo(568.0f, 857.0f, 551.0f, 857.0f),
                PathNode.HorizontalTo(243.0f),
                PathNode.QuadTo(217.0f, 857.0f, 200.5f, 841.0f),
                PathNode.QuadTo(184.0f, 825.0f, 184.0f, 799.0f),
                PathNode.VerticalTo(490.0f),
                PathNode.QuadTo(184.0f, 474.0f, 196.5f, 461.5f),
                PathNode.QuadTo(209.0f, 449.0f, 225.0f, 449.0f),
                PathNode.HorizontalTo(262.0f),
                PathNode.QuadTo(278.0f, 449.0f, 290.0f, 461.0f),
                PathNode.QuadTo(302.0f, 473.0f, 302.0f, 490.0f),
                PathNode.Close,
                PathNode.MoveTo(774.0f, 375.0f),
                PathNode.QuadTo(774.0f, 420.0f, 742.0f, 452.0f),
                PathNode.QuadTo(710.0f, 484.0f, 665.0f, 484.0f),
                PathNode.QuadTo(620.0f, 484.0f, 588.0f, 452.0f),
                PathNode.QuadTo(556.0f, 420.0f, 556.0f, 375.0f),
                PathNode.QuadTo(556.0f, 330.0f, 588.0f, 298.0f),
                PathNode.QuadTo(620.0f, 266.0f, 665.0f, 266.0f),
                PathNode.QuadTo(709.0f, 266.0f, 741.5f, 298.5f),
                PathNode.QuadTo(774.0f, 331.0f, 774.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(1147.0f, -48.0f),
                PathNode.VerticalTo(261.0f),
                PathNode.QuadTo(1147.0f, 278.0f, 1135.0f, 290.0f),
                PathNode.QuadTo(1123.0f, 302.0f, 1106.0f, 302.0f),
                PathNode.HorizontalTo(1069.0f),
                PathNode.QuadTo(1053.0f, 302.0f, 1040.5f, 290.0f),
                PathNode.QuadTo(1028.0f, 278.0f, 1028.0f, 261.0f),
                PathNode.VerticalTo(17.0f),
                PathNode.QuadTo(1028.0f, 12.0f, 1024.0f, 12.0f),
                PathNode.HorizontalTo(779.0f),
                PathNode.QuadTo(763.0f, 12.0f, 751.0f, 0.0f),
                PathNode.QuadTo(739.0f, -12.0f, 739.0f, -28.0f),
                PathNode.VerticalTo(-66.0f),
                PathNode.QuadTo(739.0f, -82.0f, 751.0f, -94.0f),
                PathNode.QuadTo(763.0f, -106.0f, 779.0f, -106.0f),
                PathNode.HorizontalTo(1089.0f),
                PathNode.QuadTo(1115.0f, -105.0f, 1131.0f, -89.5f),
                PathNode.QuadTo(1147.0f, -74.0f, 1147.0f, -48.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _expandmoreHeavy!!
    }

private var _expandmoreHeavy: ImageVector? = null
