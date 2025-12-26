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

val MiuixIcons.Carrier: ImageVector
    get() = MiuixIcons.Regular.Carrier

val MiuixIcons.Light.Carrier: ImageVector
    get() {
        if (_carrierLight != null) return _carrierLight!!
        _carrierLight = ImageVector.Builder(
            name = "Carrier.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1110.0f,
            viewportHeight = 1110.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -110.00103519668741f, translationY = 929.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1015.0263157894736f, -69.66666666666667f),
                        PathNode.QuadTo(1062.0f, -45.0f, 1082.0f, 4.0f),
                        PathNode.QuadTo(1092.0f, 28.0f, 1091.5f, 64.0f),
                        PathNode.QuadTo(1091.0f, 100.0f, 1082.0f, 188.69565217391303f),
                        PathNode.LineTo(1040.0f, 610.0f),
                        PathNode.QuadTo(1033.0f, 680.0f, 1027.5f, 712.0f),
                        PathNode.QuadTo(1022.0f, 744.0f, 1009.0f, 765.0f),
                        PathNode.QuadTo(987.0f, 802.0f, 945.3088235294117f, 822.6666666666666f),
                        PathNode.QuadTo(924.0f, 833.0f, 893.5f, 835.0f),
                        PathNode.QuadTo(863.0f, 837.0f, 788.6243654822335f, 837.0f),
                        PathNode.HorizontalTo(541.3756345177665f),
                        PathNode.QuadTo(467.0f, 837.0f, 436.5f, 835.0f),
                        PathNode.QuadTo(406.0f, 833.0f, 384.69117647058823f, 822.6666666666666f),
                        PathNode.QuadTo(343.0f, 802.0f, 321.0f, 765.0f),
                        PathNode.QuadTo(308.0f, 744.0f, 302.5f, 712.0f),
                        PathNode.QuadTo(297.0f, 680.0f, 290.0f, 610.0f),
                        PathNode.LineTo(248.0f, 188.69565217391303f),
                        PathNode.QuadTo(239.0f, 100.0f, 238.5f, 64.0f),
                        PathNode.QuadTo(238.0f, 28.0f, 249.0f, 4.0f),
                        PathNode.QuadTo(267.0f, -45.0f, 314.94805194805195f, -69.66666666666667f),
                        PathNode.QuadTo(338.0f, -82.0f, 374.5f, -85.0f),
                        PathNode.QuadTo(411.0f, -88.0f, 500.3517786561265f, -88.0f),
                        PathNode.HorizontalTo(829.6482213438735f),
                        PathNode.QuadTo(919.0f, -88.0f, 955.5f, -85.0f),
                        PathNode.QuadTo(992.0f, -82.0f, 1015.0263157894736f, -69.66666666666667f),
                        PathNode.Close,
                        PathNode.MoveTo(467.0f, 642.0f),
                        PathNode.VerticalTo(698.0f),
                        PathNode.QuadTo(467.0f, 705.48f, 471.41999999999996f, 710.24f),
                        PathNode.QuadTo(475.84f, 715.0f, 484.0f, 715.0f),
                        PathNode.HorizontalTo(498.0f),
                        PathNode.QuadTo(507.0f, 715.0f, 511.5f, 710.5799999999999f),
                        PathNode.QuadTo(516.0f, 706.16f, 516.0f, 698.0f),
                        PathNode.VerticalTo(642.0f),
                        PathNode.QuadTo(516.0f, 602.3188405797101f, 535.8309352517986f, 568.0f),
                        PathNode.QuadTo(555.6618705035971f, 533.6811594202899f, 589.9640287769785f, 513.840579710145f),
                        PathNode.QuadTo(624.2661870503597f, 494.0f, 665.0f, 494.0f),
                        PathNode.QuadTo(705.7338129496403f, 494.0f, 740.0359712230215f, 513.840579710145f),
                        PathNode.QuadTo(774.3381294964029f, 533.6811594202899f, 794.1690647482014f, 568.0f),
                        PathNode.QuadTo(814.0f, 602.3188405797101f, 814.0f, 642.0f),
                        PathNode.VerticalTo(698.0f),
                        PathNode.QuadTo(814.0f, 706.16f, 819.0f, 710.5799999999999f),
                        PathNode.QuadTo(824.0f, 715.0f, 832.0f, 715.0f),
                        PathNode.HorizontalTo(845.0f),
                        PathNode.QuadTo(854.0f, 715.0f, 858.5f, 710.5799999999999f),
                        PathNode.QuadTo(863.0f, 706.16f, 863.0f, 698.0f),
                        PathNode.VerticalTo(642.0f),
                        PathNode.QuadTo(863.0f, 588.1853658536585f, 836.448275862069f, 543.0195121951219f),
                        PathNode.QuadTo(809.8965517241379f, 497.8536585365854f, 764.5172413793103f, 471.4268292682927f),
                        PathNode.QuadTo(719.1379310344828f, 445.0f, 665.0689655172414f, 445.0f),
                        PathNode.QuadTo(611.0f, 445.0f, 565.5771812080536f, 471.4268292682927f),
                        PathNode.QuadTo(520.1543624161073f, 497.8536585365854f, 493.5771812080537f, 543.0195121951219f),
                        PathNode.QuadTo(467.0f, 588.1853658536585f, 467.0f, 642.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierLight!!
    }

private var _carrierLight: ImageVector? = null

val MiuixIcons.Regular.Carrier: ImageVector
    get() {
        if (_carrierRegular != null) return _carrierRegular!!
        _carrierRegular = ImageVector.Builder(
            name = "Carrier.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1138.8f,
            viewportHeight = 1138.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -95.60086956521738f, translationY = 943.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1019.0f, -80.0f),
                        PathNode.QuadTo(1070.0f, -52.0f, 1093.0f, 1.0f),
                        PathNode.QuadTo(1104.0f, 28.0f, 1103.5f, 66.0f),
                        PathNode.QuadTo(1103.0f, 104.0f, 1094.0f, 192.0f),
                        PathNode.LineTo(1052.0f, 610.0f),
                        PathNode.QuadTo(1045.0f, 681.0f, 1039.0f, 714.0f),
                        PathNode.QuadTo(1033.0f, 747.0f, 1020.0f, 769.0f),
                        PathNode.QuadTo(994.0f, 811.0f, 949.0f, 833.0f),
                        PathNode.QuadTo(926.0f, 844.0f, 894.0f, 846.5f),
                        PathNode.QuadTo(862.0f, 849.0f, 788.0f, 849.0f),
                        PathNode.HorizontalTo(542.0f),
                        PathNode.QuadTo(468.0f, 849.0f, 436.0f, 846.5f),
                        PathNode.QuadTo(404.0f, 844.0f, 381.0f, 833.0f),
                        PathNode.QuadTo(336.0f, 811.0f, 310.0f, 769.0f),
                        PathNode.QuadTo(297.0f, 747.0f, 291.0f, 714.0f),
                        PathNode.QuadTo(285.0f, 681.0f, 278.0f, 610.0f),
                        PathNode.LineTo(236.0f, 192.0f),
                        PathNode.QuadTo(227.0f, 104.0f, 226.5f, 66.0f),
                        PathNode.QuadTo(226.0f, 28.0f, 238.0f, 1.0f),
                        PathNode.QuadTo(259.0f, -52.0f, 311.0f, -80.0f),
                        PathNode.QuadTo(336.0f, -94.0f, 374.0f, -97.0f),
                        PathNode.QuadTo(412.0f, -100.0f, 501.0f, -100.0f),
                        PathNode.HorizontalTo(829.0f),
                        PathNode.QuadTo(918.0f, -100.0f, 956.0f, -97.0f),
                        PathNode.QuadTo(994.0f, -94.0f, 1019.0f, -80.0f),
                        PathNode.Close,
                        PathNode.MoveTo(460.0f, 641.0f),
                        PathNode.VerticalTo(686.0f),
                        PathNode.QuadTo(460.0f, 697.0f, 466.5f, 704.0f),
                        PathNode.QuadTo(473.0f, 711.0f, 485.0f, 711.0f),
                        PathNode.HorizontalTo(500.0f),
                        PathNode.QuadTo(513.0f, 711.0f, 519.5f, 704.5f),
                        PathNode.QuadTo(526.0f, 698.0f, 526.0f, 686.0f),
                        PathNode.VerticalTo(641.0f),
                        PathNode.QuadTo(526.0f, 604.0f, 544.5f, 572.0f),
                        PathNode.QuadTo(563.0f, 540.0f, 595.0f, 521.5f),
                        PathNode.QuadTo(627.0f, 503.0f, 665.0f, 503.0f),
                        PathNode.QuadTo(703.0f, 503.0f, 735.0f, 521.5f),
                        PathNode.QuadTo(767.0f, 540.0f, 785.5f, 572.0f),
                        PathNode.QuadTo(804.0f, 604.0f, 804.0f, 641.0f),
                        PathNode.VerticalTo(686.0f),
                        PathNode.QuadTo(804.0f, 698.0f, 810.5f, 704.5f),
                        PathNode.QuadTo(817.0f, 711.0f, 830.0f, 711.0f),
                        PathNode.HorizontalTo(844.0f),
                        PathNode.QuadTo(857.0f, 711.0f, 863.5f, 704.5f),
                        PathNode.QuadTo(870.0f, 698.0f, 870.0f, 686.0f),
                        PathNode.VerticalTo(641.0f),
                        PathNode.QuadTo(870.0f, 585.0f, 842.5f, 538.0f),
                        PathNode.QuadTo(815.0f, 491.0f, 768.0f, 463.5f),
                        PathNode.QuadTo(721.0f, 436.0f, 665.0f, 436.0f),
                        PathNode.QuadTo(609.0f, 436.0f, 562.0f, 463.5f),
                        PathNode.QuadTo(515.0f, 491.0f, 487.5f, 538.0f),
                        PathNode.QuadTo(460.0f, 585.0f, 460.0f, 641.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierRegular!!
    }

private var _carrierRegular: ImageVector? = null

val MiuixIcons.Heavy.Carrier: ImageVector
    get() {
        if (_carrierHeavy != null) return _carrierHeavy!!
        _carrierHeavy = ImageVector.Builder(
            name = "Carrier.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1177.2f,
            viewportHeight = 1177.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -76.4007407407407f, translationY = 963.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1027.0f, -94.0f),
                        PathNode.QuadTo(1083.0f, -63.0f, 1108.0f, -5.0f),
                        PathNode.QuadTo(1120.0f, 24.0f, 1119.5f, 63.0f),
                        PathNode.QuadTo(1119.0f, 102.0f, 1110.0f, 193.0f),
                        PathNode.LineTo(1068.0f, 611.0f),
                        PathNode.QuadTo(1061.0f, 684.0f, 1055.0f, 718.5f),
                        PathNode.QuadTo(1049.0f, 753.0f, 1034.0f, 777.0f),
                        PathNode.QuadTo(1006.0f, 823.0f, 956.0f, 848.0f),
                        PathNode.QuadTo(930.0f, 860.0f, 896.5f, 862.5f),
                        PathNode.QuadTo(863.0f, 865.0f, 788.0f, 865.0f),
                        PathNode.HorizontalTo(542.0f),
                        PathNode.QuadTo(467.0f, 865.0f, 433.5f, 862.5f),
                        PathNode.QuadTo(400.0f, 860.0f, 374.0f, 848.0f),
                        PathNode.QuadTo(324.0f, 823.0f, 296.0f, 777.0f),
                        PathNode.QuadTo(281.0f, 753.0f, 275.0f, 718.5f),
                        PathNode.QuadTo(269.0f, 684.0f, 262.0f, 611.0f),
                        PathNode.LineTo(220.0f, 193.0f),
                        PathNode.QuadTo(211.0f, 102.0f, 210.5f, 63.0f),
                        PathNode.QuadTo(210.0f, 24.0f, 223.0f, -5.0f),
                        PathNode.QuadTo(247.0f, -63.0f, 303.0f, -94.0f),
                        PathNode.QuadTo(331.0f, -109.0f, 371.0f, -112.5f),
                        PathNode.QuadTo(411.0f, -116.0f, 501.0f, -116.0f),
                        PathNode.HorizontalTo(829.0f),
                        PathNode.QuadTo(919.0f, -116.0f, 959.0f, -112.5f),
                        PathNode.QuadTo(999.0f, -109.0f, 1027.0f, -94.0f),
                        PathNode.Close,
                        PathNode.MoveTo(446.0f, 633.0f),
                        PathNode.VerticalTo(681.0f),
                        PathNode.QuadTo(446.0f, 697.0f, 455.0f, 706.5f),
                        PathNode.QuadTo(464.0f, 716.0f, 480.0f, 716.0f),
                        PathNode.HorizontalTo(505.0f),
                        PathNode.QuadTo(522.0f, 716.0f, 531.0f, 707.0f),
                        PathNode.QuadTo(540.0f, 698.0f, 540.0f, 681.0f),
                        PathNode.VerticalTo(633.0f),
                        PathNode.QuadTo(540.0f, 603.0f, 556.5f, 576.0f),
                        PathNode.QuadTo(573.0f, 549.0f, 602.0f, 532.5f),
                        PathNode.QuadTo(631.0f, 516.0f, 665.0f, 516.0f),
                        PathNode.QuadTo(699.0f, 516.0f, 728.0f, 532.5f),
                        PathNode.QuadTo(757.0f, 549.0f, 773.5f, 576.0f),
                        PathNode.QuadTo(790.0f, 603.0f, 790.0f, 633.0f),
                        PathNode.VerticalTo(681.0f),
                        PathNode.QuadTo(790.0f, 698.0f, 799.0f, 707.0f),
                        PathNode.QuadTo(808.0f, 716.0f, 825.0f, 716.0f),
                        PathNode.HorizontalTo(849.0f),
                        PathNode.QuadTo(866.0f, 716.0f, 875.0f, 707.0f),
                        PathNode.QuadTo(884.0f, 698.0f, 884.0f, 681.0f),
                        PathNode.VerticalTo(633.0f),
                        PathNode.QuadTo(884.0f, 578.0f, 854.5f, 529.5f),
                        PathNode.QuadTo(825.0f, 481.0f, 774.5f, 452.0f),
                        PathNode.QuadTo(724.0f, 423.0f, 665.0f, 423.0f),
                        PathNode.QuadTo(606.0f, 423.0f, 555.5f, 452.0f),
                        PathNode.QuadTo(505.0f, 481.0f, 475.5f, 529.5f),
                        PathNode.QuadTo(446.0f, 578.0f, 446.0f, 633.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierHeavy!!
    }

private var _carrierHeavy: ImageVector? = null
