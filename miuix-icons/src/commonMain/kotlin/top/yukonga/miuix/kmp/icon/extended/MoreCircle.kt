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

val MiuixIcons.MoreCircle: ImageVector
    get() = MiuixIcons.Regular.MoreCircle

val MiuixIcons.Light.MoreCircle: ImageVector
    get() {
        if (_morecircleLight != null) return _morecircleLight!!
        _morecircleLight = ImageVector.Builder(
            name = "MoreCircle.Light",
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
                        PathNode.MoveTo(496.0f, 374.6753246753247f),
                        PathNode.QuadTo(496.0f, 392.0f, 483.7894736842105f, 404.5f),
                        PathNode.QuadTo(471.57894736842104f, 417.0f, 454.7894736842105f, 417.0f),
                        PathNode.QuadTo(438.0f, 417.0f, 425.5f, 404.7402597402597f),
                        PathNode.QuadTo(413.0f, 392.4805194805195f, 413.0f, 374.85714285714283f),
                        PathNode.QuadTo(413.0f, 358.0f, 425.5f, 346.0f),
                        PathNode.QuadTo(438.0f, 334.0f, 455.0f, 334.0f),
                        PathNode.QuadTo(472.0f, 334.0f, 484.0f, 346.05194805194805f),
                        PathNode.QuadTo(496.0f, 358.1038961038961f, 496.0f, 374.6753246753247f),
                        PathNode.Close,
                        PathNode.MoveTo(706.0f, 374.6753246753247f),
                        PathNode.QuadTo(706.0f, 392.0f, 693.7894736842105f, 404.5f),
                        PathNode.QuadTo(681.578947368421f, 417.0f, 664.7894736842105f, 417.0f),
                        PathNode.QuadTo(648.0f, 417.0f, 635.5f, 404.7402597402597f),
                        PathNode.QuadTo(623.0f, 392.4805194805195f, 623.0f, 374.85714285714283f),
                        PathNode.QuadTo(623.0f, 358.0f, 635.5f, 346.0f),
                        PathNode.QuadTo(648.0f, 334.0f, 665.0f, 334.0f),
                        PathNode.QuadTo(682.0f, 334.0f, 694.0f, 346.05194805194805f),
                        PathNode.QuadTo(706.0f, 358.1038961038961f, 706.0f, 374.6753246753247f),
                        PathNode.Close,
                        PathNode.MoveTo(916.0f, 374.6753246753247f),
                        PathNode.QuadTo(916.0f, 392.0f, 903.9480519480519f, 404.5f),
                        PathNode.QuadTo(891.8961038961039f, 417.0f, 875.3246753246754f, 417.0f),
                        PathNode.QuadTo(858.0f, 417.0f, 845.5f, 404.5f),
                        PathNode.QuadTo(833.0f, 392.0f, 833.0f, 375.0f),
                        PathNode.QuadTo(833.0f, 358.0f, 845.1025641025641f, 346.0f),
                        PathNode.QuadTo(857.2051282051282f, 334.0f, 874.6025641025641f, 334.0f),
                        PathNode.QuadTo(892.0f, 334.0f, 904.0f, 346.05194805194805f),
                        PathNode.QuadTo(916.0f, 358.1038961038961f, 916.0f, 374.6753246753247f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _morecircleLight!!
    }

private var _morecircleLight: ImageVector? = null

val MiuixIcons.Regular.MoreCircle: ImageVector
    get() {
        if (_morecircleRegular != null) return _morecircleRegular!!
        _morecircleRegular = ImageVector.Builder(
            name = "MoreCircle.Regular",
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
                        PathNode.MoveTo(509.0f, 375.0f),
                        PathNode.QuadTo(509.0f, 398.0f, 493.0f, 414.0f),
                        PathNode.QuadTo(477.0f, 430.0f, 455.0f, 430.0f),
                        PathNode.QuadTo(433.0f, 430.0f, 416.5f, 414.0f),
                        PathNode.QuadTo(400.0f, 398.0f, 400.0f, 375.0f),
                        PathNode.QuadTo(400.0f, 353.0f, 416.0f, 337.0f),
                        PathNode.QuadTo(432.0f, 321.0f, 455.0f, 321.0f),
                        PathNode.QuadTo(478.0f, 321.0f, 493.5f, 337.0f),
                        PathNode.QuadTo(509.0f, 353.0f, 509.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(719.0f, 375.0f),
                        PathNode.QuadTo(719.0f, 398.0f, 703.0f, 414.0f),
                        PathNode.QuadTo(687.0f, 430.0f, 665.0f, 430.0f),
                        PathNode.QuadTo(643.0f, 430.0f, 626.5f, 414.0f),
                        PathNode.QuadTo(610.0f, 398.0f, 610.0f, 375.0f),
                        PathNode.QuadTo(610.0f, 353.0f, 626.0f, 337.0f),
                        PathNode.QuadTo(642.0f, 321.0f, 665.0f, 321.0f),
                        PathNode.QuadTo(688.0f, 321.0f, 703.5f, 337.0f),
                        PathNode.QuadTo(719.0f, 353.0f, 719.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(929.0f, 375.0f),
                        PathNode.QuadTo(929.0f, 398.0f, 913.0f, 414.0f),
                        PathNode.QuadTo(897.0f, 430.0f, 875.0f, 430.0f),
                        PathNode.QuadTo(852.0f, 430.0f, 836.0f, 414.0f),
                        PathNode.QuadTo(820.0f, 398.0f, 820.0f, 375.0f),
                        PathNode.QuadTo(820.0f, 352.0f, 836.0f, 336.5f),
                        PathNode.QuadTo(852.0f, 321.0f, 875.0f, 321.0f),
                        PathNode.QuadTo(898.0f, 321.0f, 913.5f, 337.0f),
                        PathNode.QuadTo(929.0f, 353.0f, 929.0f, 375.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _morecircleRegular!!
    }

private var _morecircleRegular: ImageVector? = null

val MiuixIcons.Heavy.MoreCircle: ImageVector
    get() {
        if (_morecircleHeavy != null) return _morecircleHeavy!!
        _morecircleHeavy = ImageVector.Builder(
            name = "MoreCircle.Heavy",
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
                        PathNode.MoveTo(523.0f, 375.02597402597405f),
                        PathNode.QuadTo(523.0f, 404.0f, 502.5f, 424.0f),
                        PathNode.QuadTo(482.0f, 444.0f, 454.57142857142856f, 444.0f),
                        PathNode.QuadTo(427.14285714285717f, 444.0f, 406.57142857142856f, 423.5f),
                        PathNode.QuadTo(386.0f, 403.0f, 386.0f, 374.8888888888889f),
                        PathNode.QuadTo(386.0f, 348.0f, 406.07272727272726f, 327.5f),
                        PathNode.QuadTo(426.1454545454545f, 307.0f, 455.0f, 307.0f),
                        PathNode.QuadTo(483.0f, 307.0f, 503.0f, 327.1558441558442f),
                        PathNode.QuadTo(523.0f, 347.31168831168833f, 523.0f, 375.02597402597405f),
                        PathNode.Close,
                        PathNode.MoveTo(733.0f, 375.02597402597405f),
                        PathNode.QuadTo(733.0f, 404.0f, 712.5f, 424.0f),
                        PathNode.QuadTo(692.0f, 444.0f, 664.5714285714286f, 444.0f),
                        PathNode.QuadTo(637.1428571428571f, 444.0f, 616.5714285714286f, 423.5f),
                        PathNode.QuadTo(596.0f, 403.0f, 596.0f, 374.8888888888889f),
                        PathNode.QuadTo(596.0f, 348.0f, 616.0727272727272f, 327.5f),
                        PathNode.QuadTo(636.1454545454545f, 307.0f, 665.0f, 307.0f),
                        PathNode.QuadTo(693.0f, 307.0f, 713.0f, 327.1558441558442f),
                        PathNode.QuadTo(733.0f, 347.31168831168833f, 733.0f, 375.02597402597405f),
                        PathNode.Close,
                        PathNode.MoveTo(943.0f, 375.02597402597405f),
                        PathNode.QuadTo(943.0f, 404.0f, 922.5f, 424.0f),
                        PathNode.QuadTo(902.0f, 444.0f, 874.5714285714286f, 444.0f),
                        PathNode.QuadTo(845.8961038961039f, 444.0f, 825.9480519480519f, 423.5f),
                        PathNode.QuadTo(806.0f, 403.0f, 806.0f, 375.0f),
                        PathNode.QuadTo(806.0f, 347.0f, 826.0727272727272f, 327.0f),
                        PathNode.QuadTo(846.1454545454545f, 307.0f, 875.0f, 307.0f),
                        PathNode.QuadTo(903.0f, 307.0f, 923.0f, 327.1558441558442f),
                        PathNode.QuadTo(943.0f, 347.31168831168833f, 943.0f, 375.02597402597405f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _morecircleHeavy!!
    }

private var _morecircleHeavy: ImageVector? = null
