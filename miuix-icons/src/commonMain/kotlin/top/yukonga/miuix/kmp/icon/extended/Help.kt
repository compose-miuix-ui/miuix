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

val MiuixIcons.Help: ImageVector
    get() = MiuixIcons.Regular.Help

val MiuixIcons.Light.Help: ImageVector
    get() {
        if (_helpLight != null) return _helpLight!!
        _helpLight = ImageVector.Builder(
            name = "Help.Light",
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
                        PathNode.MoveTo(227.0f, 374.5093896713615f),
                        PathNode.QuadTo(227.0f, 494.0492957746479f, 286.0f, 594.5246478873239f),
                        PathNode.QuadTo(345.0f, 695.0f, 445.3215434083601f, 754.0f),
                        PathNode.QuadTo(545.6430868167203f, 813.0f, 665.0f, 813.0f),
                        PathNode.QuadTo(784.3569131832797f, 813.0f, 884.6784565916398f, 754.0f),
                        PathNode.QuadTo(985.0f, 695.0f, 1044.0f, 594.5246478873239f),
                        PathNode.QuadTo(1103.0f, 494.0492957746479f, 1103.0f, 374.5093896713615f),
                        PathNode.QuadTo(1103.0f, 256.0f, 1044.1553398058252f, 155.6693548387097f),
                        PathNode.QuadTo(985.3106796116505f, 55.33870967741936f, 884.6553398058252f, -3.8306451612903203f),
                        PathNode.QuadTo(784.0f, -63.0f, 665.0f, -63.0f),
                        PathNode.QuadTo(546.0f, -63.0f, 445.34466019417476f, -3.8306451612903203f),
                        PathNode.QuadTo(344.68932038834953f, 55.33870967741936f, 285.84466019417476f, 155.6693548387097f),
                        PathNode.QuadTo(227.0f, 256.0f, 227.0f, 374.5093896713615f),
                        PathNode.Close,
                        PathNode.MoveTo(662.1428571428571f, 108.0f),
                        PathNode.QuadTo(679.0f, 108.0f, 691.0f, 120.5f),
                        PathNode.QuadTo(703.0f, 133.0f, 703.0f, 150.0f),
                        PathNode.QuadTo(703.0f, 167.0f, 690.9480519480519f, 179.5f),
                        PathNode.QuadTo(678.8961038961039f, 192.0f, 662.3246753246754f, 192.0f),
                        PathNode.QuadTo(645.0f, 192.0f, 632.5f, 179.5f),
                        PathNode.QuadTo(620.0f, 167.0f, 620.0f, 150.0f),
                        PathNode.QuadTo(620.0f, 133.0f, 632.2597402597403f, 120.5f),
                        PathNode.QuadTo(644.5194805194806f, 108.0f, 662.1428571428571f, 108.0f),
                        PathNode.Close,
                        PathNode.MoveTo(690.0f, 272.0f),
                        PathNode.QuadTo(698.0f, 307.0f, 712.5f, 331.0f),
                        PathNode.QuadTo(727.0f, 355.0f, 748.0f, 374.0f),
                        PathNode.LineTo(776.0f, 400.0f),
                        PathNode.QuadTo(823.0f, 445.0f, 823.0f, 506.0f),
                        PathNode.QuadTo(823.0f, 552.0122699386503f, 799.7716535433071f, 586.0613496932515f),
                        PathNode.QuadTo(776.5433070866142f, 620.1104294478528f, 740.7716535433071f, 638.0552147239264f),
                        PathNode.QuadTo(705.0f, 656.0f, 669.0f, 656.0f),
                        PathNode.QuadTo(624.0f, 656.0f, 588.0f, 635.0f),
                        PathNode.QuadTo(552.0f, 614.0f, 531.5f, 580.1136363636364f),
                        PathNode.QuadTo(511.0f, 546.2272727272727f, 511.0f, 509.0f),
                        PathNode.QuadTo(511.0f, 500.16f, 514.5f, 496.08000000000004f),
                        PathNode.QuadTo(518.0f, 492.0f, 525.7f, 492.0f),
                        PathNode.HorizontalTo(553.0f),
                        PathNode.QuadTo(562.0f, 492.0f, 565.8571428571429f, 496.91379310344826f),
                        PathNode.QuadTo(569.7142857142857f, 501.82758620689657f, 571.0f, 511.0f),
                        PathNode.QuadTo(574.0f, 555.0f, 604.0f, 576.0f),
                        PathNode.QuadTo(634.0f, 597.0f, 666.8275862068965f, 597.0f),
                        PathNode.QuadTo(702.0f, 597.0f, 732.5f, 573.578947368421f),
                        PathNode.QuadTo(763.0f, 550.1578947368421f, 763.0f, 508.0f),
                        PathNode.QuadTo(763.0f, 472.0f, 738.0f, 448.0f),
                        PathNode.LineTo(697.0f, 408.0f),
                        PathNode.QuadTo(646.0f, 355.0f, 631.0f, 274.0f),
                        PathNode.QuadTo(630.0f, 267.0f, 634.1408450704225f, 262.5f),
                        PathNode.QuadTo(638.2816901408451f, 258.0f, 645.8732394366198f, 258.0f),
                        PathNode.HorizontalTo(670.7183098591549f),
                        PathNode.QuadTo(679.0f, 258.0f, 684.0f, 261.0f),
                        PathNode.QuadTo(689.0f, 264.0f, 690.0f, 272.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _helpLight!!
    }

private var _helpLight: ImageVector? = null

val MiuixIcons.Regular.Help: ImageVector
    get() {
        if (_helpRegular != null) return _helpRegular!!
        _helpRegular = ImageVector.Builder(
            name = "Help.Regular",
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
                        PathNode.QuadTo(781.0f, 800.0f, 878.5f, 743.0f),
                        PathNode.QuadTo(976.0f, 686.0f, 1033.0f, 588.5f),
                        PathNode.QuadTo(1090.0f, 491.0f, 1090.0f, 375.0f),
                        PathNode.QuadTo(1090.0f, 260.0f, 1033.0f, 162.5f),
                        PathNode.QuadTo(976.0f, 65.0f, 878.5f, 7.5f),
                        PathNode.QuadTo(781.0f, -50.0f, 665.0f, -50.0f),
                        PathNode.QuadTo(549.0f, -50.0f, 451.5f, 7.5f),
                        PathNode.QuadTo(354.0f, 65.0f, 297.0f, 162.5f),
                        PathNode.QuadTo(240.0f, 260.0f, 240.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(662.0f, 95.0f),
                        PathNode.QuadTo(684.0f, 95.0f, 700.0f, 111.0f),
                        PathNode.QuadTo(716.0f, 127.0f, 716.0f, 150.0f),
                        PathNode.QuadTo(716.0f, 173.0f, 700.0f, 189.0f),
                        PathNode.QuadTo(684.0f, 205.0f, 662.0f, 205.0f),
                        PathNode.QuadTo(639.0f, 205.0f, 623.0f, 189.0f),
                        PathNode.QuadTo(607.0f, 173.0f, 607.0f, 150.0f),
                        PathNode.QuadTo(607.0f, 127.0f, 623.0f, 111.0f),
                        PathNode.QuadTo(639.0f, 95.0f, 662.0f, 95.0f),
                        PathNode.Close,
                        PathNode.MoveTo(705.0f, 278.0f),
                        PathNode.QuadTo(713.0f, 306.0f, 726.0f, 327.5f),
                        PathNode.QuadTo(739.0f, 349.0f, 756.0f, 364.0f),
                        PathNode.LineTo(785.0f, 390.0f),
                        PathNode.QuadTo(836.0f, 438.0f, 836.0f, 506.0f),
                        PathNode.QuadTo(836.0f, 556.0f, 811.0f, 593.0f),
                        PathNode.QuadTo(786.0f, 630.0f, 747.5f, 649.5f),
                        PathNode.QuadTo(709.0f, 669.0f, 669.0f, 669.0f),
                        PathNode.QuadTo(621.0f, 669.0f, 581.5f, 646.5f),
                        PathNode.QuadTo(542.0f, 624.0f, 520.0f, 588.5f),
                        PathNode.QuadTo(498.0f, 553.0f, 498.0f, 514.0f),
                        PathNode.QuadTo(498.0f, 501.0f, 503.0f, 495.0f),
                        PathNode.QuadTo(508.0f, 489.0f, 519.0f, 489.0f),
                        PathNode.HorizontalTo(558.0f),
                        PathNode.QuadTo(572.0f, 489.0f, 578.0f, 496.5f),
                        PathNode.QuadTo(584.0f, 504.0f, 586.0f, 518.0f),
                        PathNode.QuadTo(590.0f, 551.0f, 614.5f, 567.5f),
                        PathNode.QuadTo(639.0f, 584.0f, 667.0f, 584.0f),
                        PathNode.QuadTo(697.0f, 584.0f, 723.5f, 564.0f),
                        PathNode.QuadTo(750.0f, 544.0f, 750.0f, 508.0f),
                        PathNode.QuadTo(750.0f, 478.0f, 729.0f, 458.0f),
                        PathNode.LineTo(688.0f, 417.0f),
                        PathNode.QuadTo(634.0f, 363.0f, 619.0f, 282.0f),
                        PathNode.QuadTo(617.0f, 272.0f, 623.0f, 265.0f),
                        PathNode.QuadTo(629.0f, 258.0f, 640.0f, 258.0f),
                        PathNode.HorizontalTo(676.0f),
                        PathNode.QuadTo(688.0f, 258.0f, 695.0f, 262.5f),
                        PathNode.QuadTo(702.0f, 267.0f, 705.0f, 278.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _helpRegular!!
    }

private var _helpRegular: ImageVector? = null

val MiuixIcons.Heavy.Help: ImageVector
    get() {
        if (_helpHeavy != null) return _helpHeavy!!
        _helpHeavy = ImageVector.Builder(
            name = "Help.Heavy",
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
                        PathNode.MoveTo(254.0f, 374.5151515151515f),
                        PathNode.QuadTo(254.0f, 487.0f, 309.10351201478744f, 581.3446601941748f),
                        PathNode.QuadTo(364.2070240295749f, 675.6893203883495f, 458.4630314232902f, 730.8446601941748f),
                        PathNode.QuadTo(552.7190388170055f, 786.0f, 664.8595194085028f, 786.0f),
                        PathNode.QuadTo(777.0f, 786.0f, 871.3446601941748f, 730.8776470588235f),
                        PathNode.QuadTo(965.6893203883495f, 675.755294117647f, 1020.8446601941748f, 581.4670588235294f),
                        PathNode.QuadTo(1076.0f, 487.1788235294118f, 1076.0f, 375.0f),
                        PathNode.QuadTo(1076.0f, 263.0f, 1020.8964879852126f, 168.95967741935485f),
                        PathNode.QuadTo(965.7929759704251f, 74.91935483870968f, 871.5369685767098f, 19.45967741935484f),
                        PathNode.QuadTo(777.2809611829945f, -36.0f, 665.1404805914972f, -36.0f),
                        PathNode.QuadTo(553.0f, -36.0f, 458.65533980582524f, 19.45967741935484f),
                        PathNode.QuadTo(364.31067961165047f, 74.91935483870968f, 309.15533980582524f, 168.95967741935485f),
                        PathNode.QuadTo(254.0f, 263.0f, 254.0f, 374.5151515151515f),
                        PathNode.Close,
                        PathNode.MoveTo(662.0f, 81.0f),
                        PathNode.QuadTo(689.0f, 81.0f, 708.0f, 100.5f),
                        PathNode.QuadTo(727.0f, 120.0f, 727.0f, 147.0f),
                        PathNode.QuadTo(727.0f, 174.0f, 708.0f, 193.5f),
                        PathNode.QuadTo(689.0f, 213.0f, 662.1111111111111f, 213.0f),
                        PathNode.QuadTo(634.0f, 213.0f, 615.0f, 193.5f),
                        PathNode.QuadTo(596.0f, 174.0f, 596.0f, 147.0f),
                        PathNode.QuadTo(596.0f, 120.0f, 615.2f, 100.5f),
                        PathNode.QuadTo(634.4f, 81.0f, 662.0f, 81.0f),
                        PathNode.Close,
                        PathNode.MoveTo(717.0f, 284.0f),
                        PathNode.QuadTo(723.0f, 304.0f, 735.5f, 323.5f),
                        PathNode.QuadTo(748.0f, 343.0f, 763.0f, 357.0f),
                        PathNode.LineTo(792.0f, 383.0f),
                        PathNode.QuadTo(847.0f, 435.0f, 847.0f, 506.45762711864404f),
                        PathNode.QuadTo(847.0f, 559.0f, 820.5f, 598.5f),
                        PathNode.QuadTo(794.0f, 638.0f, 752.5f, 659.0f),
                        PathNode.QuadTo(711.0f, 680.0f, 668.5355450236967f, 680.0f),
                        PathNode.QuadTo(617.5781990521327f, 680.0f, 575.6445497630332f, 655.9032258064516f),
                        PathNode.QuadTo(533.7109004739336f, 631.8064516129032f, 510.3554502369668f, 593.7870967741935f),
                        PathNode.QuadTo(487.0f, 555.7677419354839f, 487.0f, 514.0f),
                        PathNode.QuadTo(487.0f, 497.0f, 495.0f, 490.0f),
                        PathNode.QuadTo(503.0f, 483.0f, 519.0f, 483.0f),
                        PathNode.HorizontalTo(558.0f),
                        PathNode.QuadTo(576.0f, 483.0f, 585.5f, 491.0f),
                        PathNode.QuadTo(595.0f, 499.0f, 597.0f, 517.0f),
                        PathNode.QuadTo(600.4594594594595f, 545.0f, 621.6486486486486f, 559.0f),
                        PathNode.QuadTo(642.8378378378378f, 573.0f, 667.0540540540541f, 573.0f),
                        PathNode.QuadTo(693.0f, 573.0f, 716.0f, 555.5f),
                        PathNode.QuadTo(739.0f, 538.0f, 739.0f, 507.86046511627904f),
                        PathNode.QuadTo(739.0f, 482.74418604651163f, 721.0f, 466.0f),
                        PathNode.LineTo(680.0f, 425.0f),
                        PathNode.QuadTo(623.0f, 366.0f, 609.0f, 289.0f),
                        PathNode.QuadTo(606.0f, 273.0f, 613.5f, 264.5f),
                        PathNode.QuadTo(621.0f, 256.0f, 637.0f, 256.0f),
                        PathNode.HorizontalTo(684.0f),
                        PathNode.QuadTo(698.0f, 256.0f, 705.5f, 262.5f),
                        PathNode.QuadTo(713.0f, 269.0f, 717.0f, 284.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _helpHeavy!!
    }

private var _helpHeavy: ImageVector? = null
