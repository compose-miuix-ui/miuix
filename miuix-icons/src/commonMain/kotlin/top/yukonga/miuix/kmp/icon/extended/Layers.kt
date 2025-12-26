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

val MiuixIcons.Layers: ImageVector
    get() = MiuixIcons.Regular.Layers

val MiuixIcons.Light.Layers: ImageVector
    get() {
        if (_layersLight != null) return _layersLight!!
        _layersLight = ImageVector.Builder(
            name = "Layers.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1176.3272727272727f,
            viewportHeight = 1176.3272727272727f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -76.70000000000002f, translationY = 972.6636363636363f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1155.0f, 740.2f),
                        PathNode.VerticalTo(763.3f),
                        PathNode.QuadTo(1155.0f, 771.0f, 1150.0f, 776.0f),
                        PathNode.QuadTo(1145.0f, 781.0f, 1137.0f, 781.0f),
                        PathNode.HorizontalTo(426.0f),
                        PathNode.QuadTo(418.6666666666667f, 781.0f, 413.33333333333337f, 776.0f),
                        PathNode.QuadTo(408.0f, 771.0f, 408.0f, 763.3f),
                        PathNode.VerticalTo(740.2f),
                        PathNode.QuadTo(408.0f, 732.5f, 413.33333333333337f, 727.25f),
                        PathNode.QuadTo(418.6666666666667f, 722.0f, 426.0f, 722.0f),
                        PathNode.HorizontalTo(1137.0f),
                        PathNode.QuadTo(1145.0f, 722.0f, 1150.0f, 727.25f),
                        PathNode.QuadTo(1155.0f, 732.5f, 1155.0f, 740.2f),
                        PathNode.Close,
                        PathNode.MoveTo(456.0f, -2.7415730337078656f),
                        PathNode.QuadTo(456.0f, 18.0f, 441.44943820224717f, 32.5f),
                        PathNode.QuadTo(426.8988764044944f, 47.0f, 406.44943820224717f, 47.0f),
                        PathNode.QuadTo(386.0f, 47.0f, 371.0f, 32.0f),
                        PathNode.QuadTo(356.0f, 17.0f, 356.0f, -3.0f),
                        PathNode.QuadTo(356.0f, -23.0f, 371.1573033707865f, -38.0f),
                        PathNode.QuadTo(386.314606741573f, -53.0f, 406.2584269662921f, -53.0f),
                        PathNode.QuadTo(427.0f, -53.0f, 441.5f, -38.241573033707866f),
                        PathNode.QuadTo(456.0f, -23.48314606741573f, 456.0f, -2.7415730337078656f),
                        PathNode.Close,
                        PathNode.MoveTo(456.0f, 374.2584269662921f),
                        PathNode.QuadTo(456.0f, 395.0f, 441.44943820224717f, 409.5f),
                        PathNode.QuadTo(426.8988764044944f, 424.0f, 406.44943820224717f, 424.0f),
                        PathNode.QuadTo(386.0f, 424.0f, 371.0f, 409.0f),
                        PathNode.QuadTo(356.0f, 394.0f, 356.0f, 373.55056179775283f),
                        PathNode.QuadTo(356.0f, 353.8876404494382f, 371.1573033707865f, 338.9438202247191f),
                        PathNode.QuadTo(386.314606741573f, 324.0f, 406.2584269662921f, 324.0f),
                        PathNode.QuadTo(427.0f, 324.0f, 441.5f, 338.7584269662922f),
                        PathNode.QuadTo(456.0f, 353.5168539325843f, 456.0f, 374.2584269662921f),
                        PathNode.Close,
                        PathNode.MoveTo(1154.0f, -15.055555555555555f),
                        PathNode.VerticalTo(8.055555555555555f),
                        PathNode.QuadTo(1154.0f, 16.0f, 1149.0f, 21.0f),
                        PathNode.QuadTo(1144.0f, 26.0f, 1136.0f, 26.0f),
                        PathNode.HorizontalTo(563.0f),
                        PathNode.QuadTo(555.6666666666666f, 26.0f, 550.3333333333333f, 20.807692307692307f),
                        PathNode.QuadTo(545.0f, 15.615384615384615f, 545.0f, 8.0f),
                        PathNode.VerticalTo(-15.069767441860465f),
                        PathNode.QuadTo(545.0f, -23.0f, 550.3333333333333f, -28.0f),
                        PathNode.QuadTo(555.6666666666666f, -33.0f, 563.0f, -33.0f),
                        PathNode.HorizontalTo(1136.0f),
                        PathNode.QuadTo(1144.0f, -33.0f, 1149.0f, -28.0f),
                        PathNode.QuadTo(1154.0f, -23.0f, 1154.0f, -15.055555555555555f),
                        PathNode.Close,
                        PathNode.MoveTo(264.0f, 701.0f),
                        PathNode.LineTo(324.0f, 798.0f),
                        PathNode.QuadTo(329.0f, 806.0f, 324.25f, 814.0f),
                        PathNode.QuadTo(319.5f, 822.0f, 310.0f, 822.0f),
                        PathNode.HorizontalTo(190.0f),
                        PathNode.QuadTo(184.24f, 822.0f, 180.07999999999998f, 818.0689655172414f),
                        PathNode.QuadTo(175.92f, 814.1379310344828f, 174.95999999999998f, 808.5689655172414f),
                        PathNode.QuadTo(174.0f, 803.0f, 177.0f, 798.0f),
                        PathNode.LineTo(237.0f, 701.0f),
                        PathNode.QuadTo(241.52941176470588f, 693.0f, 250.26470588235293f, 693.0f),
                        PathNode.QuadTo(259.0f, 693.0f, 264.0f, 701.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1154.0f, 362.0f),
                        PathNode.VerticalTo(385.8139534883721f),
                        PathNode.QuadTo(1154.0f, 394.0f, 1149.0f, 399.0f),
                        PathNode.QuadTo(1144.0f, 404.0f, 1136.0f, 404.0f),
                        PathNode.HorizontalTo(563.0f),
                        PathNode.QuadTo(555.6666666666666f, 404.0f, 550.3333333333333f, 399.0f),
                        PathNode.QuadTo(545.0f, 394.0f, 545.0f, 385.8139534883721f),
                        PathNode.VerticalTo(362.0f),
                        PathNode.QuadTo(545.0f, 354.6666666666667f, 550.3333333333333f, 349.33333333333337f),
                        PathNode.QuadTo(555.6666666666666f, 344.0f, 563.0f, 344.0f),
                        PathNode.HorizontalTo(1136.0f),
                        PathNode.QuadTo(1144.0f, 344.0f, 1149.0f, 349.33333333333337f),
                        PathNode.QuadTo(1154.0f, 354.6666666666667f, 1154.0f, 362.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _layersLight!!
    }

private var _layersLight: ImageVector? = null

val MiuixIcons.Regular.Layers: ImageVector
    get() {
        if (_layersRegular != null) return _layersRegular!!
        _layersRegular = ImageVector.Builder(
            name = "Layers.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1197.4153846153845f,
            viewportHeight = 1197.4153846153845f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -66.36923076923082f, translationY = 983.2076923076922f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1164.0f, 735.0f),
                        PathNode.VerticalTo(768.0f),
                        PathNode.QuadTo(1164.0f, 779.0f, 1156.0f, 786.5f),
                        PathNode.QuadTo(1148.0f, 794.0f, 1137.0f, 794.0f),
                        PathNode.HorizontalTo(444.0f),
                        PathNode.QuadTo(433.0f, 794.0f, 425.0f, 786.5f),
                        PathNode.QuadTo(417.0f, 779.0f, 417.0f, 768.0f),
                        PathNode.VerticalTo(735.0f),
                        PathNode.QuadTo(417.0f, 724.0f, 425.0f, 716.5f),
                        PathNode.QuadTo(433.0f, 709.0f, 444.0f, 709.0f),
                        PathNode.HorizontalTo(1137.0f),
                        PathNode.QuadTo(1148.0f, 709.0f, 1156.0f, 716.5f),
                        PathNode.QuadTo(1164.0f, 724.0f, 1164.0f, 735.0f),
                        PathNode.Close,
                        PathNode.MoveTo(478.0f, -3.0f),
                        PathNode.QuadTo(478.0f, 23.0f, 459.5f, 41.5f),
                        PathNode.QuadTo(441.0f, 60.0f, 415.0f, 60.0f),
                        PathNode.QuadTo(389.0f, 60.0f, 370.5f, 41.5f),
                        PathNode.QuadTo(352.0f, 23.0f, 352.0f, -3.0f),
                        PathNode.QuadTo(352.0f, -29.0f, 371.0f, -47.5f),
                        PathNode.QuadTo(390.0f, -66.0f, 415.0f, -66.0f),
                        PathNode.QuadTo(441.0f, -66.0f, 459.5f, -47.5f),
                        PathNode.QuadTo(478.0f, -29.0f, 478.0f, -3.0f),
                        PathNode.Close,
                        PathNode.MoveTo(478.0f, 374.0f),
                        PathNode.QuadTo(478.0f, 400.0f, 459.5f, 418.5f),
                        PathNode.QuadTo(441.0f, 437.0f, 415.0f, 437.0f),
                        PathNode.QuadTo(389.0f, 437.0f, 370.5f, 418.5f),
                        PathNode.QuadTo(352.0f, 400.0f, 352.0f, 374.0f),
                        PathNode.QuadTo(352.0f, 349.0f, 371.0f, 330.0f),
                        PathNode.QuadTo(390.0f, 311.0f, 415.0f, 311.0f),
                        PathNode.QuadTo(441.0f, 311.0f, 459.5f, 329.5f),
                        PathNode.QuadTo(478.0f, 348.0f, 478.0f, 374.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1163.0f, -19.0f),
                        PathNode.VerticalTo(13.0f),
                        PathNode.QuadTo(1163.0f, 24.0f, 1155.0f, 31.5f),
                        PathNode.QuadTo(1147.0f, 39.0f, 1136.0f, 39.0f),
                        PathNode.HorizontalTo(581.0f),
                        PathNode.QuadTo(570.0f, 39.0f, 562.0f, 31.5f),
                        PathNode.QuadTo(554.0f, 24.0f, 554.0f, 13.0f),
                        PathNode.VerticalTo(-19.0f),
                        PathNode.QuadTo(554.0f, -30.0f, 562.0f, -38.0f),
                        PathNode.QuadTo(570.0f, -46.0f, 581.0f, -46.0f),
                        PathNode.HorizontalTo(1136.0f),
                        PathNode.QuadTo(1147.0f, -46.0f, 1155.0f, -38.0f),
                        PathNode.QuadTo(1163.0f, -30.0f, 1163.0f, -19.0f),
                        PathNode.Close,
                        PathNode.MoveTo(280.0f, 686.0f),
                        PathNode.LineTo(349.0f, 799.0f),
                        PathNode.QuadTo(356.0f, 810.0f, 349.0f, 822.5f),
                        PathNode.QuadTo(342.0f, 835.0f, 328.0f, 835.0f),
                        PathNode.HorizontalTo(190.0f),
                        PathNode.QuadTo(181.0f, 835.0f, 174.5f, 829.0f),
                        PathNode.QuadTo(168.0f, 823.0f, 166.5f, 814.5f),
                        PathNode.QuadTo(165.0f, 806.0f, 170.0f, 799.0f),
                        PathNode.LineTo(239.0f, 686.0f),
                        PathNode.QuadTo(246.0f, 674.0f, 259.5f, 674.0f),
                        PathNode.QuadTo(273.0f, 674.0f, 280.0f, 686.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1163.0f, 358.0f),
                        PathNode.VerticalTo(390.0f),
                        PathNode.QuadTo(1163.0f, 401.0f, 1155.0f, 409.0f),
                        PathNode.QuadTo(1147.0f, 417.0f, 1136.0f, 417.0f),
                        PathNode.HorizontalTo(581.0f),
                        PathNode.QuadTo(570.0f, 417.0f, 562.0f, 409.0f),
                        PathNode.QuadTo(554.0f, 401.0f, 554.0f, 390.0f),
                        PathNode.VerticalTo(358.0f),
                        PathNode.QuadTo(554.0f, 347.0f, 562.0f, 339.0f),
                        PathNode.QuadTo(570.0f, 331.0f, 581.0f, 331.0f),
                        PathNode.HorizontalTo(1136.0f),
                        PathNode.QuadTo(1147.0f, 331.0f, 1155.0f, 339.0f),
                        PathNode.QuadTo(1163.0f, 347.0f, 1163.0f, 358.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _layersRegular!!
    }

private var _layersRegular: ImageVector? = null

val MiuixIcons.Heavy.Layers: ImageVector
    get() {
        if (_layersHeavy != null) return _layersHeavy!!
        _layersHeavy = ImageVector.Builder(
            name = "Layers.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1238.5142857142857f,
            viewportHeight = 1238.5142857142857f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -45.69523809523807f, translationY = 1003.7571428571429f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1181.0f, 735.0f),
                        PathNode.VerticalTo(768.0f),
                        PathNode.QuadTo(1181.0f, 784.0f, 1168.8518518518517f, 796.0f),
                        PathNode.QuadTo(1156.7037037037037f, 808.0f, 1140.0f, 808.0f),
                        PathNode.HorizontalTo(464.0f),
                        PathNode.QuadTo(447.2962962962963f, 808.0f, 435.14814814814815f, 796.0f),
                        PathNode.QuadTo(423.0f, 784.0f, 423.0f, 768.0f),
                        PathNode.VerticalTo(735.0f),
                        PathNode.QuadTo(423.0f, 719.0f, 435.14814814814815f, 707.0f),
                        PathNode.QuadTo(447.2962962962963f, 695.0f, 464.0f, 695.0f),
                        PathNode.HorizontalTo(1140.0f),
                        PathNode.QuadTo(1156.7037037037037f, 695.0f, 1168.8518518518517f, 707.0f),
                        PathNode.QuadTo(1181.0f, 719.0f, 1181.0f, 735.0f),
                        PathNode.Close,
                        PathNode.MoveTo(489.0f, -2.5f),
                        PathNode.QuadTo(489.0f, 29.0f, 466.3426966292135f, 51.5f),
                        PathNode.QuadTo(443.685393258427f, 74.0f, 411.8426966292135f, 74.0f),
                        PathNode.QuadTo(380.0f, 74.0f, 357.5f, 51.388888888888886f),
                        PathNode.QuadTo(335.0f, 28.77777777777778f, 335.0f, -3.0f),
                        PathNode.QuadTo(335.0f, -34.0f, 358.22222222222223f, -57.0f),
                        PathNode.QuadTo(381.44444444444446f, -80.0f, 412.0f, -80.0f),
                        PathNode.QuadTo(443.77777777777777f, -80.0f, 466.3888888888889f, -57.0f),
                        PathNode.QuadTo(489.0f, -34.0f, 489.0f, -2.5f),
                        PathNode.Close,
                        PathNode.MoveTo(489.0f, 374.5f),
                        PathNode.QuadTo(489.0f, 406.0f, 466.3426966292135f, 428.5f),
                        PathNode.QuadTo(443.685393258427f, 451.0f, 411.8426966292135f, 451.0f),
                        PathNode.QuadTo(380.0f, 451.0f, 357.5f, 428.2954545454545f),
                        PathNode.QuadTo(335.0f, 405.59090909090907f, 335.0f, 373.6818181818182f),
                        PathNode.QuadTo(335.0f, 343.0f, 358.22222222222223f, 320.0f),
                        PathNode.QuadTo(381.44444444444446f, 297.0f, 412.0f, 297.0f),
                        PathNode.QuadTo(443.77777777777777f, 297.0f, 466.3888888888889f, 320.0f),
                        PathNode.QuadTo(489.0f, 343.0f, 489.0f, 374.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1180.0f, -19.0f),
                        PathNode.VerticalTo(13.0f),
                        PathNode.QuadTo(1180.0f, 29.923076923076923f, 1167.8518518518517f, 41.46153846153846f),
                        PathNode.QuadTo(1155.7037037037037f, 53.0f, 1139.0f, 53.0f),
                        PathNode.HorizontalTo(601.0f),
                        PathNode.QuadTo(584.2962962962963f, 53.0f, 572.1481481481482f, 41.46153846153846f),
                        PathNode.QuadTo(560.0f, 29.923076923076923f, 560.0f, 13.0f),
                        PathNode.VerticalTo(-19.0f),
                        PathNode.QuadTo(560.0f, -35.7037037037037f, 572.1481481481482f, -47.85185185185185f),
                        PathNode.QuadTo(584.2962962962963f, -60.0f, 601.0f, -60.0f),
                        PathNode.HorizontalTo(1139.0f),
                        PathNode.QuadTo(1155.7037037037037f, -60.0f, 1167.8518518518517f, -47.85185185185185f),
                        PathNode.QuadTo(1180.0f, -35.7037037037037f, 1180.0f, -19.0f),
                        PathNode.Close,
                        PathNode.MoveTo(289.0f, 679.0f),
                        PathNode.LineTo(358.0f, 792.0f),
                        PathNode.QuadTo(369.0f, 810.0f, 358.0f, 829.5f),
                        PathNode.QuadTo(347.0f, 849.0f, 325.0f, 849.0f),
                        PathNode.HorizontalTo(187.0f),
                        PathNode.QuadTo(172.6818181818182f, 849.0f, 162.3409090909091f, 840.0f),
                        PathNode.QuadTo(152.0f, 831.0f, 149.5f, 817.5f),
                        PathNode.QuadTo(147.0f, 804.0f, 155.0f, 792.0f),
                        PathNode.LineTo(224.0f, 679.0f),
                        PathNode.QuadTo(234.91176470588235f, 660.0f, 255.95588235294116f, 660.0f),
                        PathNode.QuadTo(277.0f, 660.0f, 289.0f, 679.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1180.0f, 358.0f),
                        PathNode.VerticalTo(390.0f),
                        PathNode.QuadTo(1180.0f, 406.7037037037037f, 1167.8518518518517f, 418.85185185185185f),
                        PathNode.QuadTo(1155.7037037037037f, 431.0f, 1139.0f, 431.0f),
                        PathNode.HorizontalTo(601.0f),
                        PathNode.QuadTo(584.2962962962963f, 431.0f, 572.1481481481482f, 418.85185185185185f),
                        PathNode.QuadTo(560.0f, 406.7037037037037f, 560.0f, 390.0f),
                        PathNode.VerticalTo(358.0f),
                        PathNode.QuadTo(560.0f, 341.2962962962963f, 572.1481481481482f, 329.14814814814815f),
                        PathNode.QuadTo(584.2962962962963f, 317.0f, 601.0f, 317.0f),
                        PathNode.HorizontalTo(1139.0f),
                        PathNode.QuadTo(1155.7037037037037f, 317.0f, 1167.8518518518517f, 329.14814814814815f),
                        PathNode.QuadTo(1180.0f, 341.2962962962963f, 1180.0f, 358.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _layersHeavy!!
    }

private var _layersHeavy: ImageVector? = null
