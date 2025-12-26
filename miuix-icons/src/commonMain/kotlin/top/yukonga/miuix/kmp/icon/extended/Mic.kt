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

val MiuixIcons.Mic: ImageVector
    get() = MiuixIcons.Regular.Mic

val MiuixIcons.Light.Mic: ImageVector
    get() {
        if (_micLight != null) return _micLight!!
        _micLight = ImageVector.Builder(
            name = "Mic.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1335.6f,
            viewportHeight = 1335.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 3.2999999999999545f, translationY = 1038.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1089.0f, 426.0f),
                        PathNode.VerticalTo(480.0f),
                        PathNode.QuadTo(1089.0f, 489.03225806451616f, 1084.0f, 494.51612903225805f),
                        PathNode.QuadTo(1079.0f, 500.0f, 1069.0f, 500.0f),
                        PathNode.HorizontalTo(1046.0f),
                        PathNode.QuadTo(1037.0f, 500.0f, 1032.0f, 494.51612903225805f),
                        PathNode.QuadTo(1027.0f, 489.03225806451616f, 1027.0f, 480.0f),
                        PathNode.VerticalTo(426.0f),
                        PathNode.QuadTo(1027.0f, 326.9019607843137f, 978.5f, 243.45098039215685f),
                        PathNode.QuadTo(930.0f, 160.0f, 846.5616045845272f, 111.5f),
                        PathNode.QuadTo(763.1232091690545f, 63.0f, 665.0830945558739f, 63.0f),
                        PathNode.QuadTo(566.0f, 63.0f, 482.5f, 111.5f),
                        PathNode.QuadTo(399.0f, 160.0f, 350.5f, 243.45098039215685f),
                        PathNode.QuadTo(302.0f, 326.9019607843137f, 302.0f, 426.0f),
                        PathNode.VerticalTo(480.0f),
                        PathNode.QuadTo(302.0f, 488.6666666666667f, 297.0f, 494.33333333333337f),
                        PathNode.QuadTo(292.0f, 500.0f, 281.0f, 500.0f),
                        PathNode.HorizontalTo(262.0f),
                        PathNode.QuadTo(250.0f, 500.0f, 245.0f, 494.33333333333337f),
                        PathNode.QuadTo(240.0f, 488.6666666666667f, 240.0f, 480.0f),
                        PathNode.VerticalTo(426.0f),
                        PathNode.QuadTo(240.0f, 310.1121495327103f, 297.0f, 213.05607476635515f),
                        PathNode.QuadTo(354.0f, 116.0f, 451.30227272727274f, 59.0f),
                        PathNode.QuadTo(548.6045454545455f, 2.0f, 664.7863636363636f, 2.0f),
                        PathNode.QuadTo(780.0f, 2.0f, 877.5f, 59.0f),
                        PathNode.QuadTo(975.0f, 116.0f, 1032.0f, 213.0f),
                        PathNode.QuadTo(1089.0f, 310.0f, 1089.0f, 426.0f),
                        PathNode.Close,
                        PathNode.MoveTo(694.0f, -167.0f),
                        PathNode.VerticalTo(39.0f),
                        PathNode.HorizontalTo(635.0f),
                        PathNode.VerticalTo(-167.0f),
                        PathNode.QuadTo(635.0f, -174.74074074074073f, 640.5652173913044f, -180.37037037037038f),
                        PathNode.QuadTo(646.1304347826087f, -186.0f, 653.0869565217391f, -186.0f),
                        PathNode.HorizontalTo(676.0434782608695f),
                        PathNode.QuadTo(683.0f, -186.0f, 688.5f, -180.37037037037038f),
                        PathNode.QuadTo(694.0f, -174.74074074074073f, 694.0f, -167.0f),
                        PathNode.Close,
                        PathNode.MoveTo(918.0f, 430.0f),
                        PathNode.VerticalTo(675.0f),
                        PathNode.QuadTo(918.0f, 743.2886597938144f, 884.0f, 801.1443298969073f),
                        PathNode.QuadTo(850.0f, 859.0f, 791.9700374531835f, 893.0f),
                        PathNode.QuadTo(733.940074906367f, 927.0f, 665.4456928838952f, 927.0f),
                        PathNode.QuadTo(596.0f, 927.0f, 537.8445595854922f, 893.1554404145078f),
                        PathNode.QuadTo(479.68911917098444f, 859.3108808290156f, 445.8445595854922f, 801.1554404145078f),
                        PathNode.QuadTo(412.0f, 743.0f, 412.0f, 675.0f),
                        PathNode.VerticalTo(430.0f),
                        PathNode.QuadTo(412.0f, 361.0f, 446.0f, 303.0f),
                        PathNode.QuadTo(480.0f, 245.0f, 538.0f, 211.0f),
                        PathNode.QuadTo(596.0f, 177.0f, 665.3392330383481f, 177.0f),
                        PathNode.QuadTo(733.7286135693215f, 177.0f, 791.669616519174f, 211.1443298969072f),
                        PathNode.QuadTo(849.6106194690266f, 245.28865979381445f, 883.8053097345132f, 303.1443298969072f),
                        PathNode.QuadTo(918.0f, 361.0f, 918.0f, 430.0f),
                        PathNode.Close,
                        PathNode.MoveTo(471.0f, 430.0f),
                        PathNode.VerticalTo(675.0f),
                        PathNode.QuadTo(471.0f, 727.7404580152672f, 497.0f, 771.8702290076336f),
                        PathNode.QuadTo(523.0f, 816.0f, 567.6439393939394f, 841.5f),
                        PathNode.QuadTo(612.2878787878788f, 867.0f, 665.0f, 867.0f),
                        PathNode.QuadTo(717.0f, 867.0f, 761.5f, 841.5f),
                        PathNode.QuadTo(806.0f, 816.0f, 832.0f, 771.8702290076336f),
                        PathNode.QuadTo(858.0f, 727.7404580152672f, 858.0f, 675.0f),
                        PathNode.VerticalTo(430.0f),
                        PathNode.QuadTo(858.0f, 377.2878787878788f, 832.0f, 332.6439393939394f),
                        PathNode.QuadTo(806.0f, 288.0f, 761.5f, 262.5f),
                        PathNode.QuadTo(717.0f, 237.0f, 664.5f, 237.0f),
                        PathNode.QuadTo(612.0f, 237.0f, 567.6704545454545f, 262.64885496183206f),
                        PathNode.QuadTo(523.3409090909091f, 288.2977099236641f, 497.17045454545456f, 332.64885496183206f),
                        PathNode.QuadTo(471.0f, 377.0f, 471.0f, 430.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _micLight!!
    }

private var _micLight: ImageVector? = null

val MiuixIcons.Regular.Mic: ImageVector
    get() {
        if (_micRegular != null) return _micRegular!!
        _micRegular = ImageVector.Builder(
            name = "Mic.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1356.0f,
            viewportHeight = 1356.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 13.5f, translationY = 1053.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1103.0f, 426.0f),
                        PathNode.VerticalTo(471.0f),
                        PathNode.QuadTo(1103.0f, 485.0f, 1096.0f, 493.5f),
                        PathNode.QuadTo(1089.0f, 502.0f, 1073.0f, 502.0f),
                        PathNode.HorizontalTo(1042.0f),
                        PathNode.QuadTo(1027.0f, 502.0f, 1020.0f, 493.5f),
                        PathNode.QuadTo(1013.0f, 485.0f, 1013.0f, 471.0f),
                        PathNode.VerticalTo(426.0f),
                        PathNode.QuadTo(1013.0f, 331.0f, 966.0f, 251.0f),
                        PathNode.QuadTo(919.0f, 171.0f, 839.0f, 124.0f),
                        PathNode.QuadTo(759.0f, 77.0f, 665.0f, 77.0f),
                        PathNode.QuadTo(570.0f, 77.0f, 490.0f, 124.0f),
                        PathNode.QuadTo(410.0f, 171.0f, 363.0f, 251.0f),
                        PathNode.QuadTo(316.0f, 331.0f, 316.0f, 426.0f),
                        PathNode.VerticalTo(472.0f),
                        PathNode.QuadTo(316.0f, 485.0f, 309.0f, 493.5f),
                        PathNode.QuadTo(302.0f, 502.0f, 284.0f, 502.0f),
                        PathNode.HorizontalTo(259.0f),
                        PathNode.QuadTo(241.0f, 502.0f, 233.5f, 493.5f),
                        PathNode.QuadTo(226.0f, 485.0f, 226.0f, 472.0f),
                        PathNode.VerticalTo(426.0f),
                        PathNode.QuadTo(226.0f, 306.0f, 285.0f, 205.5f),
                        PathNode.QuadTo(344.0f, 105.0f, 444.5f, 46.5f),
                        PathNode.QuadTo(545.0f, -12.0f, 665.0f, -12.0f),
                        PathNode.QuadTo(784.0f, -12.0f, 884.5f, 46.5f),
                        PathNode.QuadTo(985.0f, 105.0f, 1044.0f, 206.0f),
                        PathNode.QuadTo(1103.0f, 307.0f, 1103.0f, 426.0f),
                        PathNode.Close,
                        PathNode.MoveTo(707.0f, -163.0f),
                        PathNode.VerticalTo(39.0f),
                        PathNode.HorizontalTo(622.0f),
                        PathNode.VerticalTo(-163.0f),
                        PathNode.QuadTo(622.0f, -174.0f, 630.0f, -182.0f),
                        PathNode.QuadTo(638.0f, -190.0f, 648.0f, -190.0f),
                        PathNode.HorizontalTo(681.0f),
                        PathNode.QuadTo(691.0f, -190.0f, 699.0f, -182.0f),
                        PathNode.QuadTo(707.0f, -174.0f, 707.0f, -163.0f),
                        PathNode.Close,
                        PathNode.MoveTo(931.0f, 430.0f),
                        PathNode.VerticalTo(675.0f),
                        PathNode.QuadTo(931.0f, 747.0f, 895.0f, 808.0f),
                        PathNode.QuadTo(859.0f, 869.0f, 798.0f, 904.5f),
                        PathNode.QuadTo(737.0f, 940.0f, 665.0f, 940.0f),
                        PathNode.QuadTo(592.0f, 940.0f, 531.0f, 904.5f),
                        PathNode.QuadTo(470.0f, 869.0f, 434.5f, 808.0f),
                        PathNode.QuadTo(399.0f, 747.0f, 399.0f, 675.0f),
                        PathNode.VerticalTo(430.0f),
                        PathNode.QuadTo(399.0f, 358.0f, 434.5f, 296.5f),
                        PathNode.QuadTo(470.0f, 235.0f, 531.0f, 199.5f),
                        PathNode.QuadTo(592.0f, 164.0f, 665.0f, 164.0f),
                        PathNode.QuadTo(737.0f, 164.0f, 798.0f, 200.0f),
                        PathNode.QuadTo(859.0f, 236.0f, 895.0f, 297.0f),
                        PathNode.QuadTo(931.0f, 358.0f, 931.0f, 430.0f),
                        PathNode.Close,
                        PathNode.MoveTo(484.0f, 430.0f),
                        PathNode.VerticalTo(675.0f),
                        PathNode.QuadTo(484.0f, 724.0f, 508.5f, 765.0f),
                        PathNode.QuadTo(533.0f, 806.0f, 574.5f, 830.0f),
                        PathNode.QuadTo(616.0f, 854.0f, 665.0f, 854.0f),
                        PathNode.QuadTo(714.0f, 854.0f, 755.0f, 830.0f),
                        PathNode.QuadTo(796.0f, 806.0f, 820.5f, 765.0f),
                        PathNode.QuadTo(845.0f, 724.0f, 845.0f, 675.0f),
                        PathNode.VerticalTo(430.0f),
                        PathNode.QuadTo(845.0f, 381.0f, 820.5f, 339.5f),
                        PathNode.QuadTo(796.0f, 298.0f, 755.0f, 274.0f),
                        PathNode.QuadTo(714.0f, 250.0f, 665.0f, 250.0f),
                        PathNode.QuadTo(616.0f, 250.0f, 574.5f, 274.0f),
                        PathNode.QuadTo(533.0f, 298.0f, 508.5f, 339.5f),
                        PathNode.QuadTo(484.0f, 381.0f, 484.0f, 430.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _micRegular!!
    }

private var _micRegular: ImageVector? = null

val MiuixIcons.Heavy.Mic: ImageVector
    get() {
        if (_micHeavy != null) return _micHeavy!!
        _micHeavy = ImageVector.Builder(
            name = "Mic.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1376.3999999999999f,
            viewportHeight = 1376.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 23.699999999999932f, translationY = 1062.6999999999998f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1122.0f, 421.0f),
                        PathNode.VerticalTo(465.0f),
                        PathNode.QuadTo(1122.0f, 485.3225806451613f, 1111.0f, 497.6612903225806f),
                        PathNode.QuadTo(1100.0f, 510.0f, 1079.0f, 510.0f),
                        PathNode.HorizontalTo(1048.0f),
                        PathNode.QuadTo(1027.0f, 510.0f, 1016.0f, 497.6612903225806f),
                        PathNode.QuadTo(1005.0f, 485.3225806451613f, 1005.0f, 465.0f),
                        PathNode.VerticalTo(421.0f),
                        PathNode.QuadTo(1005.0f, 330.0980392156863f, 960.0f, 253.54901960784315f),
                        PathNode.QuadTo(915.0f, 177.0f, 837.0f, 132.5f),
                        PathNode.QuadTo(759.0f, 88.0f, 665.0f, 88.0f),
                        PathNode.QuadTo(570.0f, 88.0f, 492.0f, 132.5f),
                        PathNode.QuadTo(414.0f, 177.0f, 369.0f, 253.54901960784315f),
                        PathNode.QuadTo(324.0f, 330.0980392156863f, 324.0f, 421.0f),
                        PathNode.VerticalTo(466.0f),
                        PathNode.QuadTo(324.0f, 485.06666666666666f, 313.0f, 497.5333333333333f),
                        PathNode.QuadTo(302.0f, 510.0f, 278.0f, 510.0f),
                        PathNode.HorizontalTo(253.0f),
                        PathNode.QuadTo(229.0f, 510.0f, 218.0f, 497.0f),
                        PathNode.QuadTo(207.0f, 484.0f, 207.0f, 466.0f),
                        PathNode.VerticalTo(421.0f),
                        PathNode.QuadTo(207.0f, 299.0f, 268.0f, 196.0f),
                        PathNode.QuadTo(329.0f, 93.0f, 434.196261682243f, 33.0f),
                        PathNode.QuadTo(539.392523364486f, -27.0f, 665.0f, -27.0f),
                        PathNode.QuadTo(791.0f, -27.0f, 895.5f, 33.0f),
                        PathNode.QuadTo(1000.0f, 93.0f, 1061.0f, 196.202492211838f),
                        PathNode.QuadTo(1122.0f, 299.404984423676f, 1122.0f, 421.0f),
                        PathNode.Close,
                        PathNode.MoveTo(720.0f, -158.0f),
                        PathNode.VerticalTo(55.0f),
                        PathNode.HorizontalTo(609.0f),
                        PathNode.VerticalTo(-158.0f),
                        PathNode.QuadTo(609.0f, -174.7037037037037f, 620.5f, -186.85185185185185f),
                        PathNode.QuadTo(632.0f, -199.0f, 648.0f, -199.0f),
                        PathNode.HorizontalTo(681.0f),
                        PathNode.QuadTo(697.0f, -199.0f, 708.5f, -186.85185185185185f),
                        PathNode.QuadTo(720.0f, -174.7037037037037f, 720.0f, -158.0f),
                        PathNode.Close,
                        PathNode.MoveTo(942.0f, 438.0f),
                        PathNode.VerticalTo(672.0f),
                        PathNode.QuadTo(942.0f, 746.988679245283f, 904.5132743362832f, 810.5207547169812f),
                        PathNode.QuadTo(867.0265486725664f, 874.0528301886792f, 803.5073746312685f, 911.0264150943397f),
                        PathNode.QuadTo(739.9882005899705f, 948.0f, 665.0147492625368f, 948.0f),
                        PathNode.QuadTo(589.0f, 948.0f, 525.4715025906736f, 911.0264150943397f),
                        PathNode.QuadTo(461.94300518134713f, 874.0528301886792f, 424.9715025906736f, 810.5207547169812f),
                        PathNode.QuadTo(388.0f, 746.988679245283f, 388.0f, 672.0f),
                        PathNode.VerticalTo(438.0f),
                        PathNode.QuadTo(388.0f, 363.02255639097746f, 424.9704142011834f, 298.9793233082707f),
                        PathNode.QuadTo(461.9408284023669f, 234.9360902255639f, 525.4674556213018f, 197.96804511278197f),
                        PathNode.QuadTo(588.9940828402367f, 161.0f, 665.0177514792899f, 161.0f),
                        PathNode.QuadTo(740.0f, 161.0f, 803.5154639175257f, 198.48872180451127f),
                        PathNode.QuadTo(867.0309278350516f, 235.97744360902254f, 904.5154639175257f, 299.5f),
                        PathNode.QuadTo(942.0f, 363.02255639097746f, 942.0f, 438.0f),
                        PathNode.Close,
                        PathNode.MoveTo(499.0f, 438.0f),
                        PathNode.VerticalTo(672.0f),
                        PathNode.QuadTo(499.0f, 716.8938547486033f, 521.4696132596684f, 754.4581005586592f),
                        PathNode.QuadTo(543.939226519337f, 792.022346368715f, 582.0f, 814.0111731843575f),
                        PathNode.QuadTo(620.060773480663f, 836.0f, 665.0f, 836.0f),
                        PathNode.QuadTo(709.0f, 836.0f, 747.0f, 814.0f),
                        PathNode.QuadTo(785.0f, 792.0f, 807.0f, 754.4427480916031f),
                        PathNode.QuadTo(829.0f, 716.8854961832061f, 829.0f, 672.0f),
                        PathNode.VerticalTo(438.0f),
                        PathNode.QuadTo(829.0f, 393.0833333333333f, 807.0f, 355.04166666666663f),
                        PathNode.QuadTo(785.0f, 317.0f, 747.0f, 295.0f),
                        PathNode.QuadTo(709.0f, 273.0f, 664.5f, 273.0f),
                        PathNode.QuadTo(620.0f, 273.0f, 581.9583333333333f, 295.0f),
                        PathNode.QuadTo(543.9166666666666f, 317.0f, 521.4583333333333f, 355.04166666666663f),
                        PathNode.QuadTo(499.0f, 393.0833333333333f, 499.0f, 438.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _micHeavy!!
    }

private var _micHeavy: ImageVector? = null
