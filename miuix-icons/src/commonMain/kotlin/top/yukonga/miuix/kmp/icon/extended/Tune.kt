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

val MiuixIcons.Tune: ImageVector
    get() = MiuixIcons.Regular.Tune

val MiuixIcons.Light.Tune: ImageVector
    get() {
        if (_tuneLight != null) return _tuneLight!!
        _tuneLight = ImageVector.Builder(
            name = "Tune.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1183.2f,
            viewportHeight = 1183.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -73.39999999999998f, translationY = 966.6f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(661.0f, 85.26666666666667f),
                PathNode.QuadTo(661.0f, 131.0f, 638.5f, 169.0f),
                PathNode.QuadTo(616.0f, 207.0f, 578.0f, 229.5f),
                PathNode.QuadTo(540.0f, 252.0f, 494.26666666666665f, 252.0f),
                PathNode.QuadTo(448.53333333333336f, 252.0f, 410.26666666666665f, 229.5f),
                PathNode.QuadTo(372.0f, 207.0f, 349.5f, 168.70994475138122f),
                PathNode.QuadTo(327.0f, 130.41988950276243f, 327.0f, 85.20994475138122f),
                PathNode.QuadTo(327.0f, 40.0f, 349.5f, 1.5f),
                PathNode.QuadTo(372.0f, -37.0f, 410.5f, -59.5f),
                PathNode.QuadTo(449.0f, -82.0f, 494.2099447513812f, -82.0f),
                PathNode.QuadTo(539.4198895027624f, -82.0f, 577.7099447513813f, -59.5f),
                PathNode.QuadTo(616.0f, -37.0f, 638.5f, 1.2666666666666657f),
                PathNode.QuadTo(661.0f, 39.53333333333333f, 661.0f, 85.26666666666667f),
                PathNode.Close,
                PathNode.MoveTo(387.0f, 85.0f),
                PathNode.QuadTo(387.0f, 130.0f, 418.19402985074623f, 161.5f),
                PathNode.QuadTo(449.3880597014925f, 193.0f, 493.6268656716418f, 193.0f),
                PathNode.QuadTo(539.0f, 193.0f, 570.5f, 161.5f),
                PathNode.QuadTo(602.0f, 130.0f, 602.0f, 85.0f),
                PathNode.QuadTo(602.0f, 40.0f, 570.6007462686567f, 8.5f),
                PathNode.QuadTo(539.2014925373135f, -23.0f, 493.52985074626866f, -23.0f),
                PathNode.QuadTo(449.0f, -23.0f, 418.0f, 8.5f),
                PathNode.QuadTo(387.0f, 40.0f, 387.0f, 85.0f),
                PathNode.Close,
                PathNode.MoveTo(361.0f, 115.0f),
                PathNode.HorizontalTo(191.0f),
                PathNode.QuadTo(183.0f, 115.0f, 177.5f, 110.25806451612902f),
                PathNode.QuadTo(172.0f, 105.51612903225806f, 172.0f, 94.0f),
                PathNode.VerticalTo(77.0f),
                PathNode.QuadTo(172.0f, 66.0f, 177.42857142857144f, 61.0f),
                PathNode.QuadTo(182.85714285714286f, 56.0f, 191.0f, 56.0f),
                PathNode.HorizontalTo(361.0f),
                PathNode.Close,
                PathNode.MoveTo(1158.0f, 77.0f),
                PathNode.VerticalTo(93.675f),
                PathNode.QuadTo(1158.0f, 106.0f, 1152.5f, 110.5f),
                PathNode.QuadTo(1147.0f, 115.0f, 1139.0f, 115.0f),
                PathNode.HorizontalTo(632.0f),
                PathNode.VerticalTo(55.0f),
                PathNode.LineTo(1139.0f, 56.0f),
                PathNode.QuadTo(1147.142857142857f, 56.0f, 1152.5714285714284f, 61.0f),
                PathNode.QuadTo(1158.0f, 66.0f, 1158.0f, 77.0f),
                PathNode.Close,
                PathNode.MoveTo(1158.0f, 657.0f),
                PathNode.VerticalTo(673.675f),
                PathNode.QuadTo(1158.0f, 686.0f, 1152.5f, 690.5f),
                PathNode.QuadTo(1147.0f, 695.0f, 1139.0f, 695.0f),
                PathNode.HorizontalTo(969.0f),
                PathNode.VerticalTo(636.0f),
                PathNode.HorizontalTo(1139.0f),
                PathNode.QuadTo(1147.142857142857f, 636.0f, 1152.5714285714284f, 641.0f),
                PathNode.QuadTo(1158.0f, 646.0f, 1158.0f, 657.0f),
                PathNode.Close,
                PathNode.MoveTo(694.0f, 695.0f),
                PathNode.HorizontalTo(191.0f),
                PathNode.QuadTo(183.0f, 695.0f, 177.5f, 690.0f),
                PathNode.QuadTo(172.0f, 685.0f, 172.0f, 674.0f),
                PathNode.VerticalTo(657.0f),
                PathNode.QuadTo(172.0f, 646.0f, 177.42857142857144f, 641.0f),
                PathNode.QuadTo(182.85714285714286f, 636.0f, 191.0f, 636.0f),
                PathNode.HorizontalTo(694.0f),
                PathNode.Close,
                PathNode.MoveTo(1001.0f, 665.2666666666667f),
                PathNode.QuadTo(1001.0f, 711.0f, 978.5f, 749.0f),
                PathNode.QuadTo(956.0f, 787.0f, 917.939226519337f, 809.5f),
                PathNode.QuadTo(879.878453038674f, 832.0f, 834.939226519337f, 832.0f),
                PathNode.QuadTo(790.0f, 832.0f, 751.5f, 809.5f),
                PathNode.QuadTo(713.0f, 787.0f, 690.5f, 748.7099447513813f),
                PathNode.QuadTo(668.0f, 710.4198895027624f, 668.0f, 665.2099447513813f),
                PathNode.QuadTo(668.0f, 620.0f, 690.5f, 581.5f),
                PathNode.QuadTo(713.0f, 543.0f, 751.5f, 520.5f),
                PathNode.QuadTo(790.0f, 498.0f, 834.939226519337f, 498.0f),
                PathNode.QuadTo(879.878453038674f, 498.0f, 917.939226519337f, 520.5f),
                PathNode.QuadTo(956.0f, 543.0f, 978.5f, 581.2666666666667f),
                PathNode.QuadTo(1001.0f, 619.5333333333333f, 1001.0f, 665.2666666666667f),
                PathNode.Close,
                PathNode.MoveTo(727.0f, 664.7611940298508f),
                PathNode.QuadTo(727.0f, 709.0f, 758.5957446808511f, 741.0f),
                PathNode.QuadTo(790.1914893617021f, 773.0f, 835.0f, 773.0f),
                PathNode.QuadTo(880.4736842105264f, 773.0f, 911.7368421052631f, 741.2388059701493f),
                PathNode.QuadTo(943.0f, 709.4776119402985f, 943.0f, 665.2388059701492f),
                PathNode.QuadTo(943.0f, 621.0f, 911.6007462686567f, 589.0f),
                PathNode.QuadTo(880.2014925373135f, 557.0f, 834.5298507462686f, 557.0f),
                PathNode.QuadTo(790.0f, 557.0f, 758.5f, 588.7611940298507f),
                PathNode.QuadTo(727.0f, 620.5223880597015f, 727.0f, 664.7611940298508f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _tuneLight!!
    }

private var _tuneLight: ImageVector? = null

val MiuixIcons.Regular.Tune: ImageVector
    get() {
        if (_tuneRegular != null) return _tuneRegular!!
        _tuneRegular = ImageVector.Builder(
            name = "Tune.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1183.2f,
            viewportHeight = 1183.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -73.39999999999998f, translationY = 966.6f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(674.0f, 85.0f),
                PathNode.QuadTo(674.0f, 134.0f, 650.0f, 175.5f),
                PathNode.QuadTo(626.0f, 217.0f, 584.5f, 241.0f),
                PathNode.QuadTo(543.0f, 265.0f, 494.0f, 265.0f),
                PathNode.QuadTo(445.0f, 265.0f, 404.0f, 241.0f),
                PathNode.QuadTo(363.0f, 217.0f, 338.5f, 175.5f),
                PathNode.QuadTo(314.0f, 134.0f, 314.0f, 85.0f),
                PathNode.QuadTo(314.0f, 36.0f, 338.5f, -5.0f),
                PathNode.QuadTo(363.0f, -46.0f, 404.0f, -70.5f),
                PathNode.QuadTo(445.0f, -95.0f, 494.0f, -95.0f),
                PathNode.QuadTo(543.0f, -95.0f, 584.5f, -70.5f),
                PathNode.QuadTo(626.0f, -46.0f, 650.0f, -5.0f),
                PathNode.QuadTo(674.0f, 36.0f, 674.0f, 85.0f),
                PathNode.Close,
                PathNode.MoveTo(400.0f, 85.0f),
                PathNode.QuadTo(400.0f, 124.0f, 427.5f, 152.0f),
                PathNode.QuadTo(455.0f, 180.0f, 494.0f, 180.0f),
                PathNode.QuadTo(534.0f, 180.0f, 561.5f, 152.0f),
                PathNode.QuadTo(589.0f, 124.0f, 589.0f, 85.0f),
                PathNode.QuadTo(589.0f, 46.0f, 561.5f, 18.0f),
                PathNode.QuadTo(534.0f, -10.0f, 494.0f, -10.0f),
                PathNode.QuadTo(455.0f, -10.0f, 427.5f, 18.0f),
                PathNode.QuadTo(400.0f, 46.0f, 400.0f, 85.0f),
                PathNode.Close,
                PathNode.MoveTo(365.0f, 128.0f),
                PathNode.HorizontalTo(200.0f),
                PathNode.QuadTo(189.0f, 128.0f, 180.5f, 121.0f),
                PathNode.QuadTo(172.0f, 114.0f, 172.0f, 97.0f),
                PathNode.VerticalTo(74.0f),
                PathNode.QuadTo(172.0f, 57.0f, 180.0f, 50.0f),
                PathNode.QuadTo(188.0f, 43.0f, 200.0f, 43.0f),
                PathNode.HorizontalTo(365.0f),
                PathNode.Close,
                PathNode.MoveTo(1158.0f, 74.0f),
                PathNode.VerticalTo(97.0f),
                PathNode.QuadTo(1158.0f, 114.0f, 1149.5f, 121.0f),
                PathNode.QuadTo(1141.0f, 128.0f, 1130.0f, 128.0f),
                PathNode.HorizontalTo(632.0f),
                PathNode.VerticalTo(42.0f),
                PathNode.LineTo(1130.0f, 43.0f),
                PathNode.QuadTo(1142.0f, 43.0f, 1150.0f, 50.0f),
                PathNode.QuadTo(1158.0f, 57.0f, 1158.0f, 74.0f),
                PathNode.Close,
                PathNode.MoveTo(1158.0f, 654.0f),
                PathNode.VerticalTo(677.0f),
                PathNode.QuadTo(1158.0f, 694.0f, 1149.5f, 701.0f),
                PathNode.QuadTo(1141.0f, 708.0f, 1130.0f, 708.0f),
                PathNode.HorizontalTo(965.0f),
                PathNode.VerticalTo(623.0f),
                PathNode.HorizontalTo(1130.0f),
                PathNode.QuadTo(1142.0f, 623.0f, 1150.0f, 630.0f),
                PathNode.QuadTo(1158.0f, 637.0f, 1158.0f, 654.0f),
                PathNode.Close,
                PathNode.MoveTo(698.0f, 708.0f),
                PathNode.HorizontalTo(200.0f),
                PathNode.QuadTo(189.0f, 708.0f, 180.5f, 701.0f),
                PathNode.QuadTo(172.0f, 694.0f, 172.0f, 677.0f),
                PathNode.VerticalTo(654.0f),
                PathNode.QuadTo(172.0f, 637.0f, 180.0f, 630.0f),
                PathNode.QuadTo(188.0f, 623.0f, 200.0f, 623.0f),
                PathNode.HorizontalTo(698.0f),
                PathNode.Close,
                PathNode.MoveTo(1015.0f, 665.0f),
                PathNode.QuadTo(1015.0f, 714.0f, 991.0f, 755.5f),
                PathNode.QuadTo(967.0f, 797.0f, 925.5f, 821.0f),
                PathNode.QuadTo(884.0f, 845.0f, 835.0f, 845.0f),
                PathNode.QuadTo(786.0f, 845.0f, 745.0f, 821.0f),
                PathNode.QuadTo(704.0f, 797.0f, 679.5f, 755.5f),
                PathNode.QuadTo(655.0f, 714.0f, 655.0f, 665.0f),
                PathNode.QuadTo(655.0f, 616.0f, 679.5f, 575.0f),
                PathNode.QuadTo(704.0f, 534.0f, 745.0f, 509.5f),
                PathNode.QuadTo(786.0f, 485.0f, 835.0f, 485.0f),
                PathNode.QuadTo(884.0f, 485.0f, 925.5f, 509.5f),
                PathNode.QuadTo(967.0f, 534.0f, 991.0f, 575.0f),
                PathNode.QuadTo(1015.0f, 616.0f, 1015.0f, 665.0f),
                PathNode.Close,
                PathNode.MoveTo(741.0f, 665.0f),
                PathNode.QuadTo(741.0f, 704.0f, 768.5f, 732.0f),
                PathNode.QuadTo(796.0f, 760.0f, 835.0f, 760.0f),
                PathNode.QuadTo(875.0f, 760.0f, 902.5f, 732.0f),
                PathNode.QuadTo(930.0f, 704.0f, 930.0f, 665.0f),
                PathNode.QuadTo(930.0f, 626.0f, 902.5f, 598.0f),
                PathNode.QuadTo(875.0f, 570.0f, 835.0f, 570.0f),
                PathNode.QuadTo(796.0f, 570.0f, 768.5f, 598.0f),
                PathNode.QuadTo(741.0f, 626.0f, 741.0f, 665.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _tuneRegular!!
    }

private var _tuneRegular: ImageVector? = null

val MiuixIcons.Heavy.Tune: ImageVector
    get() {
        if (_tuneHeavy != null) return _tuneHeavy!!
        _tuneHeavy = ImageVector.Builder(
            name = "Tune.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1216.8f,
            viewportHeight = 1216.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -56.60000000000002f, translationY = 983.4f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(688.0f, 85.5f),
                PathNode.QuadTo(688.0f, 138.0f, 662.1333333333333f, 182.66793893129773f),
                PathNode.QuadTo(636.2666666666667f, 227.33587786259542f, 591.5388888888889f, 253.16793893129773f),
                PathNode.QuadTo(546.8111111111111f, 279.0f, 494.0f, 279.0f),
                PathNode.QuadTo(442.0f, 279.0f, 397.55725190839695f, 253.13333333333333f),
                PathNode.QuadTo(353.1145038167939f, 227.26666666666668f, 326.55725190839695f, 182.5388888888889f),
                PathNode.QuadTo(300.0f, 137.8111111111111f, 300.0f, 85.0f),
                PathNode.QuadTo(300.0f, 33.0f, 326.55725190839695f, -11.442748091603054f),
                PathNode.QuadTo(353.1145038167939f, -55.88549618320611f, 397.55725190839695f, -82.44274809160305f),
                PathNode.QuadTo(442.0f, -109.0f, 494.5f, -109.0f),
                PathNode.QuadTo(547.0f, -109.0f, 591.6679389312977f, -82.44274809160305f),
                PathNode.QuadTo(636.3358778625955f, -55.88549618320611f, 662.1679389312977f, -11.442748091603054f),
                PathNode.QuadTo(688.0f, 33.0f, 688.0f, 85.5f),
                PathNode.Close,
                PathNode.MoveTo(412.0f, 84.94776119402985f),
                PathNode.QuadTo(412.0f, 119.0f, 436.01119402985074f, 143.5f),
                PathNode.QuadTo(460.0223880597015f, 168.0f, 494.07462686567163f, 168.0f),
                PathNode.QuadTo(529.0f, 168.0f, 553.0f, 144.0f),
                PathNode.QuadTo(577.0f, 120.0f, 577.0f, 85.5f),
                PathNode.QuadTo(577.0f, 51.0f, 552.9888059701493f, 26.5f),
                PathNode.QuadTo(528.9776119402985f, 2.0f, 494.05223880597015f, 2.0f),
                PathNode.QuadTo(460.0f, 2.0f, 436.0f, 26.44776119402985f),
                PathNode.QuadTo(412.0f, 50.8955223880597f, 412.0f, 84.94776119402985f),
                PathNode.Close,
                PathNode.MoveTo(379.0f, 142.0f),
                PathNode.HorizontalTo(200.0f),
                PathNode.QuadTo(183.5f, 142.0f, 170.75f, 131.0f),
                PathNode.QuadTo(158.0f, 120.0f, 158.0f, 97.0f),
                PathNode.VerticalTo(74.0f),
                PathNode.QuadTo(158.0f, 51.0f, 170.0f, 40.0f),
                PathNode.QuadTo(182.0f, 29.0f, 200.0f, 29.0f),
                PathNode.HorizontalTo(379.0f),
                PathNode.Close,
                PathNode.MoveTo(1172.0f, 74.0f),
                PathNode.VerticalTo(97.0f),
                PathNode.QuadTo(1172.0f, 120.0f, 1159.25f, 131.0f),
                PathNode.QuadTo(1146.5f, 142.0f, 1130.0f, 142.0f),
                PathNode.HorizontalTo(618.0f),
                PathNode.VerticalTo(28.0f),
                PathNode.LineTo(1130.0f, 29.0f),
                PathNode.QuadTo(1148.0f, 29.0f, 1160.0f, 40.0f),
                PathNode.QuadTo(1172.0f, 51.0f, 1172.0f, 74.0f),
                PathNode.Close,
                PathNode.MoveTo(1172.0f, 654.0f),
                PathNode.VerticalTo(677.0f),
                PathNode.QuadTo(1172.0f, 700.0f, 1159.25f, 711.0f),
                PathNode.QuadTo(1146.5f, 722.0f, 1130.0f, 722.0f),
                PathNode.HorizontalTo(951.0f),
                PathNode.VerticalTo(609.0f),
                PathNode.HorizontalTo(1130.0f),
                PathNode.QuadTo(1148.0f, 609.0f, 1160.0f, 620.0f),
                PathNode.QuadTo(1172.0f, 631.0f, 1172.0f, 654.0f),
                PathNode.Close,
                PathNode.MoveTo(712.0f, 722.0f),
                PathNode.HorizontalTo(200.0f),
                PathNode.QuadTo(183.5f, 722.0f, 170.75f, 711.0f),
                PathNode.QuadTo(158.0f, 700.0f, 158.0f, 677.0f),
                PathNode.VerticalTo(654.0f),
                PathNode.QuadTo(158.0f, 631.0f, 170.0f, 620.0f),
                PathNode.QuadTo(182.0f, 609.0f, 200.0f, 609.0f),
                PathNode.HorizontalTo(712.0f),
                PathNode.Close,
                PathNode.MoveTo(1029.0f, 665.5f),
                PathNode.QuadTo(1029.0f, 718.0f, 1003.1333333333333f, 762.6679389312977f),
                PathNode.QuadTo(977.2666666666667f, 807.3358778625955f, 932.5388888888889f, 833.1679389312977f),
                PathNode.QuadTo(887.8111111111111f, 859.0f, 835.0f, 859.0f),
                PathNode.QuadTo(783.0f, 859.0f, 738.5572519083969f, 833.1333333333333f),
                PathNode.QuadTo(694.1145038167939f, 807.2666666666667f, 667.5572519083969f, 762.5388888888889f),
                PathNode.QuadTo(641.0f, 717.8111111111111f, 641.0f, 665.0f),
                PathNode.QuadTo(641.0f, 613.0f, 667.5572519083969f, 568.5572519083969f),
                PathNode.QuadTo(694.1145038167939f, 524.1145038167939f, 738.5572519083969f, 497.55725190839695f),
                PathNode.QuadTo(783.0f, 471.0f, 835.5f, 471.0f),
                PathNode.QuadTo(888.0f, 471.0f, 932.6679389312977f, 497.55725190839695f),
                PathNode.QuadTo(977.3358778625955f, 524.1145038167939f, 1003.1679389312977f, 568.5572519083969f),
                PathNode.QuadTo(1029.0f, 613.0f, 1029.0f, 665.5f),
                PathNode.Close,
                PathNode.MoveTo(753.0f, 664.9477611940298f),
                PathNode.QuadTo(753.0f, 699.0f, 777.0111940298507f, 723.5f),
                PathNode.QuadTo(801.0223880597015f, 748.0f, 835.0746268656717f, 748.0f),
                PathNode.QuadTo(870.0f, 748.0f, 894.0f, 724.0f),
                PathNode.QuadTo(918.0f, 700.0f, 918.0f, 665.5f),
                PathNode.QuadTo(918.0f, 631.0f, 893.9888059701493f, 606.5f),
                PathNode.QuadTo(869.9776119402985f, 582.0f, 835.0522388059702f, 582.0f),
                PathNode.QuadTo(801.0f, 582.0f, 777.0f, 606.4477611940299f),
                PathNode.QuadTo(753.0f, 630.8955223880597f, 753.0f, 664.9477611940298f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _tuneHeavy!!
    }

private var _tuneHeavy: ImageVector? = null
