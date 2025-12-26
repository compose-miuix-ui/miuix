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

val MiuixIcons.AddCircle: ImageVector
    get() = MiuixIcons.Regular.AddCircle

val MiuixIcons.Light.AddCircle: ImageVector
    get() {
        if (_addcircleLight != null) return _addcircleLight!!
        _addcircleLight = ImageVector.Builder(
            name = "AddCircle.Light",
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
                        PathNode.MoveTo(694.0f, 131.0f),
                        PathNode.VerticalTo(620.0f),
                        PathNode.QuadTo(694.0f, 627.0f, 689.1f, 632.5f),
                        PathNode.QuadTo(684.2f, 638.0f, 673.7f, 638.0f),
                        PathNode.HorizontalTo(654.1f),
                        PathNode.QuadTo(645.0f, 638.0f, 640.0f, 632.6666666666667f),
                        PathNode.QuadTo(635.0f, 627.3333333333334f, 635.0f, 620.0f),
                        PathNode.VerticalTo(131.0f),
                        PathNode.QuadTo(635.0f, 121.85185185185185f, 640.6140350877192f, 116.92592592592592f),
                        PathNode.QuadTo(646.2280701754386f, 112.0f, 655.3508771929825f, 112.0f),
                        PathNode.HorizontalTo(675.0f),
                        PathNode.QuadTo(683.0f, 112.0f, 688.5f, 116.92592592592592f),
                        PathNode.QuadTo(694.0f, 121.85185185185185f, 694.0f, 131.0f),
                        PathNode.Close,
                        PathNode.MoveTo(909.0f, 405.0f),
                        PathNode.HorizontalTo(420.0f),
                        PathNode.QuadTo(413.0f, 405.0f, 407.5f, 400.1f),
                        PathNode.QuadTo(402.0f, 395.2f, 402.0f, 384.7f),
                        PathNode.VerticalTo(365.1f),
                        PathNode.QuadTo(402.0f, 356.0f, 407.33333333333337f, 351.0f),
                        PathNode.QuadTo(412.6666666666667f, 346.0f, 420.0f, 346.0f),
                        PathNode.HorizontalTo(909.0f),
                        PathNode.QuadTo(918.1481481481482f, 346.0f, 923.0740740740741f, 351.6140350877193f),
                        PathNode.QuadTo(928.0f, 357.2280701754386f, 928.0f, 366.35087719298247f),
                        PathNode.VerticalTo(386.0f),
                        PathNode.QuadTo(928.0f, 394.0f, 923.0740740740741f, 399.5f),
                        PathNode.QuadTo(918.1481481481482f, 405.0f, 909.0f, 405.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addcircleLight!!
    }

private var _addcircleLight: ImageVector? = null

val MiuixIcons.Regular.AddCircle: ImageVector
    get() {
        if (_addcircleRegular != null) return _addcircleRegular!!
        _addcircleRegular = ImageVector.Builder(
            name = "AddCircle.Regular",
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
                        PathNode.MoveTo(707.0f, 135.0f),
                        PathNode.VerticalTo(615.0f),
                        PathNode.QuadTo(707.0f, 627.0f, 700.0f, 634.5f),
                        PathNode.QuadTo(693.0f, 642.0f, 678.0f, 642.0f),
                        PathNode.HorizontalTo(650.0f),
                        PathNode.QuadTo(637.0f, 642.0f, 629.5f, 634.0f),
                        PathNode.QuadTo(622.0f, 626.0f, 622.0f, 615.0f),
                        PathNode.VerticalTo(135.0f),
                        PathNode.QuadTo(622.0f, 122.0f, 630.0f, 115.0f),
                        PathNode.QuadTo(638.0f, 108.0f, 651.0f, 108.0f),
                        PathNode.HorizontalTo(679.0f),
                        PathNode.QuadTo(692.0f, 108.0f, 699.5f, 115.0f),
                        PathNode.QuadTo(707.0f, 122.0f, 707.0f, 135.0f),
                        PathNode.Close,
                        PathNode.MoveTo(905.0f, 418.0f),
                        PathNode.HorizontalTo(425.0f),
                        PathNode.QuadTo(413.0f, 418.0f, 405.5f, 411.0f),
                        PathNode.QuadTo(398.0f, 404.0f, 398.0f, 389.0f),
                        PathNode.VerticalTo(361.0f),
                        PathNode.QuadTo(398.0f, 348.0f, 406.0f, 340.5f),
                        PathNode.QuadTo(414.0f, 333.0f, 425.0f, 333.0f),
                        PathNode.HorizontalTo(905.0f),
                        PathNode.QuadTo(918.0f, 333.0f, 925.0f, 341.0f),
                        PathNode.QuadTo(932.0f, 349.0f, 932.0f, 362.0f),
                        PathNode.VerticalTo(390.0f),
                        PathNode.QuadTo(932.0f, 403.0f, 925.0f, 410.5f),
                        PathNode.QuadTo(918.0f, 418.0f, 905.0f, 418.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addcircleRegular!!
    }

private var _addcircleRegular: ImageVector? = null

val MiuixIcons.Heavy.AddCircle: ImageVector
    get() {
        if (_addcircleHeavy != null) return _addcircleHeavy!!
        _addcircleHeavy = ImageVector.Builder(
            name = "AddCircle.Heavy",
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
                        PathNode.MoveTo(721.0f, 144.0f),
                        PathNode.VerticalTo(606.0f),
                        PathNode.QuadTo(721.0f, 624.2222222222222f, 710.0f, 635.6111111111111f),
                        PathNode.QuadTo(699.0f, 647.0f, 678.0f, 647.0f),
                        PathNode.HorizontalTo(650.0f),
                        PathNode.QuadTo(630.5f, 647.0f, 619.25f, 634.8518518518518f),
                        PathNode.QuadTo(608.0f, 622.7037037037037f, 608.0f, 606.0f),
                        PathNode.VerticalTo(144.0f),
                        PathNode.QuadTo(608.0f, 126.0f, 620.5f, 114.5f),
                        PathNode.QuadTo(633.0f, 103.0f, 651.0f, 103.0f),
                        PathNode.HorizontalTo(679.0f),
                        PathNode.QuadTo(697.0f, 103.0f, 709.0f, 114.5f),
                        PathNode.QuadTo(721.0f, 126.0f, 721.0f, 144.0f),
                        PathNode.Close,
                        PathNode.MoveTo(896.0f, 432.0f),
                        PathNode.HorizontalTo(434.0f),
                        PathNode.QuadTo(415.77777777777777f, 432.0f, 404.3888888888889f, 421.0f),
                        PathNode.QuadTo(393.0f, 410.0f, 393.0f, 389.0f),
                        PathNode.VerticalTo(361.0f),
                        PathNode.QuadTo(393.0f, 341.5f, 405.14814814814815f, 330.25f),
                        PathNode.QuadTo(417.2962962962963f, 319.0f, 434.0f, 319.0f),
                        PathNode.HorizontalTo(896.0f),
                        PathNode.QuadTo(914.0f, 319.0f, 925.5f, 331.5f),
                        PathNode.QuadTo(937.0f, 344.0f, 937.0f, 362.0f),
                        PathNode.VerticalTo(390.0f),
                        PathNode.QuadTo(937.0f, 408.0f, 925.5f, 420.0f),
                        PathNode.QuadTo(914.0f, 432.0f, 896.0f, 432.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addcircleHeavy!!
    }

private var _addcircleHeavy: ImageVector? = null
