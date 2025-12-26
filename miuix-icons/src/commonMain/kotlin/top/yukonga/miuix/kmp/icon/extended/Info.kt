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

val MiuixIcons.Info: ImageVector
    get() = MiuixIcons.Regular.Info

val MiuixIcons.Light.Info: ImageVector
    get() {
        if (_infoLight != null) return _infoLight!!
        _infoLight = ImageVector.Builder(
            name = "Info.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1194.0f,
            viewportHeight = 1194.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -67.5f, translationY = 972.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1162.0f, 374.80078125f),
                        PathNode.QuadTo(1162.0f, 510.0f, 1095.0f, 624.5f),
                        PathNode.QuadTo(1028.0f, 739.0f, 914.19921875f, 806.0f),
                        PathNode.QuadTo(800.3984375f, 873.0f, 665.19921875f, 873.0f),
                        PathNode.QuadTo(530.0f, 873.0f, 415.5f, 806.0f),
                        PathNode.QuadTo(301.0f, 739.0f, 234.0f, 624.5f),
                        PathNode.QuadTo(167.0f, 510.0f, 167.0f, 374.80078125f),
                        PathNode.QuadTo(167.0f, 239.6015625f, 234.0f, 125.80078125f),
                        PathNode.QuadTo(301.0f, 12.0f, 415.5f, -55.0f),
                        PathNode.QuadTo(530.0f, -122.0f, 665.19921875f, -122.0f),
                        PathNode.QuadTo(800.3984375f, -122.0f, 914.19921875f, -55.0f),
                        PathNode.QuadTo(1028.0f, 12.0f, 1095.0f, 125.80078125f),
                        PathNode.QuadTo(1162.0f, 239.6015625f, 1162.0f, 374.80078125f),
                        PathNode.Close,
                        PathNode.MoveTo(227.0f, 375.0f),
                        PathNode.QuadTo(227.0f, 494.35691318327974f, 286.0f, 594.6784565916398f),
                        PathNode.QuadTo(345.0f, 695.0f, 445.47535211267603f, 754.0f),
                        PathNode.QuadTo(545.9507042253521f, 813.0f, 665.4906103286385f, 813.0f),
                        PathNode.QuadTo(784.0f, 813.0f, 884.3306451612902f, 754.1553398058252f),
                        PathNode.QuadTo(984.6612903225806f, 695.3106796116505f, 1043.8306451612902f, 594.6553398058252f),
                        PathNode.QuadTo(1103.0f, 494.0f, 1103.0f, 375.0f),
                        PathNode.QuadTo(1103.0f, 256.0f, 1043.8306451612902f, 155.34466019417476f),
                        PathNode.QuadTo(984.6612903225806f, 54.689320388349515f, 884.3306451612902f, -4.155339805825243f),
                        PathNode.QuadTo(784.0f, -63.0f, 665.4906103286385f, -63.0f),
                        PathNode.QuadTo(545.9507042253521f, -63.0f, 445.47535211267603f, -4.0f),
                        PathNode.QuadTo(345.0f, 55.0f, 286.0f, 155.32154340836013f),
                        PathNode.QuadTo(227.0f, 255.64308681672026f, 227.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(706.0f, 611.0f),
                        PathNode.QuadTo(706.0f, 628.0f, 694.2314814814815f, 640.0f),
                        PathNode.QuadTo(682.4629629629629f, 652.0f, 665.0f, 652.0f),
                        PathNode.QuadTo(648.0f, 652.0f, 635.5f, 639.7894736842105f),
                        PathNode.QuadTo(623.0f, 627.578947368421f, 623.0f, 610.7894736842105f),
                        PathNode.QuadTo(623.0f, 594.0f, 635.6428571428571f, 581.5f),
                        PathNode.QuadTo(648.2857142857143f, 569.0f, 665.1428571428571f, 569.0f),
                        PathNode.QuadTo(682.0f, 569.0f, 694.0f, 581.5f),
                        PathNode.QuadTo(706.0f, 594.0f, 706.0f, 611.0f),
                        PathNode.Close,
                        PathNode.MoveTo(694.0f, 106.0f),
                        PathNode.VerticalTo(480.0f),
                        PathNode.QuadTo(694.0f, 488.44444444444446f, 689.1f, 493.72222222222223f),
                        PathNode.QuadTo(684.2f, 499.0f, 673.7f, 499.0f),
                        PathNode.HorizontalTo(654.1f),
                        PathNode.QuadTo(645.0f, 499.0f, 640.0f, 493.3703703703704f),
                        PathNode.QuadTo(635.0f, 487.74074074074076f, 635.0f, 480.0f),
                        PathNode.VerticalTo(106.0f),
                        PathNode.QuadTo(635.0f, 98.0f, 640.6140350877192f, 93.0f),
                        PathNode.QuadTo(646.2280701754386f, 88.0f, 655.3508771929825f, 88.0f),
                        PathNode.HorizontalTo(675.0f),
                        PathNode.QuadTo(683.0f, 88.0f, 688.5f, 93.0f),
                        PathNode.QuadTo(694.0f, 98.0f, 694.0f, 106.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _infoLight!!
    }

private var _infoLight: ImageVector? = null

val MiuixIcons.Regular.Info: ImageVector
    get() {
        if (_infoRegular != null) return _infoRegular!!
        _infoRegular = ImageVector.Builder(
            name = "Info.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.2f,
            viewportHeight = 1225.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -51.89999999999998f, translationY = 988.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1175.0f, 375.0f),
                        PathNode.QuadTo(1175.0f, 514.0f, 1106.5f, 631.0f),
                        PathNode.QuadTo(1038.0f, 748.0f, 921.0f, 817.0f),
                        PathNode.QuadTo(804.0f, 886.0f, 665.0f, 886.0f),
                        PathNode.QuadTo(526.0f, 886.0f, 409.0f, 817.0f),
                        PathNode.QuadTo(292.0f, 748.0f, 223.0f, 631.0f),
                        PathNode.QuadTo(154.0f, 514.0f, 154.0f, 375.0f),
                        PathNode.QuadTo(154.0f, 236.0f, 223.0f, 119.0f),
                        PathNode.QuadTo(292.0f, 2.0f, 409.0f, -66.5f),
                        PathNode.QuadTo(526.0f, -135.0f, 665.0f, -135.0f),
                        PathNode.QuadTo(804.0f, -135.0f, 921.0f, -66.5f),
                        PathNode.QuadTo(1038.0f, 2.0f, 1106.5f, 119.0f),
                        PathNode.QuadTo(1175.0f, 236.0f, 1175.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(240.0f, 375.0f),
                        PathNode.QuadTo(240.0f, 491.0f, 297.0f, 588.5f),
                        PathNode.QuadTo(354.0f, 686.0f, 451.5f, 743.0f),
                        PathNode.QuadTo(549.0f, 800.0f, 665.0f, 800.0f),
                        PathNode.QuadTo(780.0f, 800.0f, 877.5f, 743.0f),
                        PathNode.QuadTo(975.0f, 686.0f, 1032.5f, 588.5f),
                        PathNode.QuadTo(1090.0f, 491.0f, 1090.0f, 375.0f),
                        PathNode.QuadTo(1090.0f, 259.0f, 1032.5f, 161.5f),
                        PathNode.QuadTo(975.0f, 64.0f, 877.5f, 7.0f),
                        PathNode.QuadTo(780.0f, -50.0f, 665.0f, -50.0f),
                        PathNode.QuadTo(549.0f, -50.0f, 451.5f, 7.0f),
                        PathNode.QuadTo(354.0f, 64.0f, 297.0f, 161.5f),
                        PathNode.QuadTo(240.0f, 259.0f, 240.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(719.0f, 611.0f),
                        PathNode.QuadTo(719.0f, 634.0f, 703.5f, 649.5f),
                        PathNode.QuadTo(688.0f, 665.0f, 665.0f, 665.0f),
                        PathNode.QuadTo(642.0f, 665.0f, 626.0f, 649.0f),
                        PathNode.QuadTo(610.0f, 633.0f, 610.0f, 611.0f),
                        PathNode.QuadTo(610.0f, 589.0f, 626.5f, 572.5f),
                        PathNode.QuadTo(643.0f, 556.0f, 665.0f, 556.0f),
                        PathNode.QuadTo(687.0f, 556.0f, 703.0f, 572.0f),
                        PathNode.QuadTo(719.0f, 588.0f, 719.0f, 611.0f),
                        PathNode.Close,
                        PathNode.MoveTo(707.0f, 113.0f),
                        PathNode.VerticalTo(472.0f),
                        PathNode.QuadTo(707.0f, 484.0f, 700.0f, 491.5f),
                        PathNode.QuadTo(693.0f, 499.0f, 678.0f, 499.0f),
                        PathNode.HorizontalTo(650.0f),
                        PathNode.QuadTo(637.0f, 499.0f, 629.5f, 491.0f),
                        PathNode.QuadTo(622.0f, 483.0f, 622.0f, 472.0f),
                        PathNode.VerticalTo(113.0f),
                        PathNode.QuadTo(622.0f, 100.0f, 630.0f, 93.0f),
                        PathNode.QuadTo(638.0f, 86.0f, 651.0f, 86.0f),
                        PathNode.HorizontalTo(679.0f),
                        PathNode.QuadTo(692.0f, 86.0f, 699.5f, 93.0f),
                        PathNode.QuadTo(707.0f, 100.0f, 707.0f, 113.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _infoRegular!!
    }

private var _infoRegular: ImageVector? = null

val MiuixIcons.Heavy.Info: ImageVector
    get() {
        if (_infoHeavy != null) return _infoHeavy!!
        _infoHeavy = ImageVector.Builder(
            name = "Info.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1258.8f,
            viewportHeight = 1258.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -35.10000000000002f, translationY = 1004.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1189.0f, 375.0f),
                        PathNode.QuadTo(1189.0f, 517.0f, 1118.4690026954177f, 637.4596774193549f),
                        PathNode.QuadTo(1047.9380053908355f, 757.9193548387096f, 927.4690026954178f, 828.9596774193549f),
                        PathNode.QuadTo(807.0f, 900.0f, 665.0f, 900.0f),
                        PathNode.QuadTo(523.0f, 900.0f, 402.5403225806451f, 828.9596774193549f),
                        PathNode.QuadTo(282.0806451612903f, 757.9193548387096f, 211.04032258064515f, 637.4596774193549f),
                        PathNode.QuadTo(140.0f, 517.0f, 140.0f, 375.0f),
                        PathNode.QuadTo(140.0f, 233.0f, 211.04032258064515f, 112.53099730458221f),
                        PathNode.QuadTo(282.0806451612903f, -7.938005390835579f, 402.5403225806451f, -78.46900269541779f),
                        PathNode.QuadTo(523.0f, -149.0f, 665.0f, -149.0f),
                        PathNode.QuadTo(807.0f, -149.0f, 927.4690026954178f, -78.46900269541779f),
                        PathNode.QuadTo(1047.9380053908355f, -7.938005390835579f, 1118.4690026954177f, 112.53099730458221f),
                        PathNode.QuadTo(1189.0f, 233.0f, 1189.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(254.0f, 374.85951940850276f),
                        PathNode.QuadTo(254.0f, 487.0f, 309.1223529411765f, 581.3446601941748f),
                        PathNode.QuadTo(364.24470588235295f, 675.6893203883495f, 458.5329411764706f, 730.8446601941748f),
                        PathNode.QuadTo(552.8211764705883f, 786.0f, 665.0f, 786.0f),
                        PathNode.QuadTo(777.0f, 786.0f, 871.0403225806451f, 730.8964879852126f),
                        PathNode.QuadTo(965.0806451612904f, 675.7929759704251f, 1020.5403225806451f, 581.5369685767098f),
                        PathNode.QuadTo(1076.0f, 487.2809611829945f, 1076.0f, 375.14048059149724f),
                        PathNode.QuadTo(1076.0f, 263.0f, 1020.5403225806451f, 168.65533980582524f),
                        PathNode.QuadTo(965.0806451612904f, 74.31067961165049f, 871.0403225806451f, 19.155339805825243f),
                        PathNode.QuadTo(777.0f, -36.0f, 665.4848484848485f, -36.0f),
                        PathNode.QuadTo(553.0f, -36.0f, 458.65533980582524f, 19.103512014787434f),
                        PathNode.QuadTo(364.31067961165047f, 74.20702402957487f, 309.15533980582524f, 168.4630314232902f),
                        PathNode.QuadTo(254.0f, 262.7190388170055f, 254.0f, 374.85951940850276f),
                        PathNode.Close,
                        PathNode.MoveTo(733.0f, 611.0f),
                        PathNode.QuadTo(733.0f, 639.0f, 713.0f, 659.0f),
                        PathNode.QuadTo(693.0f, 679.0f, 664.5f, 679.0f),
                        PathNode.QuadTo(636.0f, 679.0f, 616.0f, 658.5f),
                        PathNode.QuadTo(596.0f, 638.0f, 596.0f, 610.5714285714286f),
                        PathNode.QuadTo(596.0f, 583.1428571428571f, 616.5714285714286f, 562.5714285714286f),
                        PathNode.QuadTo(637.1428571428571f, 542.0f, 664.5714285714286f, 542.0f),
                        PathNode.QuadTo(692.0f, 542.0f, 712.5f, 562.0727272727272f),
                        PathNode.QuadTo(733.0f, 582.1454545454545f, 733.0f, 611.0f),
                        PathNode.Close,
                        PathNode.MoveTo(721.0f, 122.0f),
                        PathNode.VerticalTo(449.0f),
                        PathNode.QuadTo(721.0f, 467.22222222222223f, 710.0f, 478.6111111111111f),
                        PathNode.QuadTo(699.0f, 490.0f, 678.0f, 490.0f),
                        PathNode.HorizontalTo(650.0f),
                        PathNode.QuadTo(630.5f, 490.0f, 619.25f, 477.85185185185185f),
                        PathNode.QuadTo(608.0f, 465.7037037037037f, 608.0f, 449.0f),
                        PathNode.VerticalTo(122.0f),
                        PathNode.QuadTo(608.0f, 104.0f, 620.5f, 92.5f),
                        PathNode.QuadTo(633.0f, 81.0f, 651.0f, 81.0f),
                        PathNode.HorizontalTo(679.0f),
                        PathNode.QuadTo(697.0f, 81.0f, 709.0f, 92.5f),
                        PathNode.QuadTo(721.0f, 104.0f, 721.0f, 122.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _infoHeavy!!
    }

private var _infoHeavy: ImageVector? = null
