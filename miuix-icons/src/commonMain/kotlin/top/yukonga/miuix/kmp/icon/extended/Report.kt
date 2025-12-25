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

val MiuixIcons.Report: ImageVector
    get() = MiuixIcons.Regular.Report

val MiuixIcons.Light.Report: ImageVector
    get() {
        if (_reportLight != null) return _reportLight!!
        _reportLight = ImageVector.Builder(
            name = "Report.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1270.8f,
            viewportHeight = 1270.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -29.10000000000005f, translationY = 1009.4f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(682.0f, -154.0f),
                PathNode.QuadTo(694.0f, -151.0f, 709.0f, -143.0f),
                PathNode.LineTo(716.0f, -140.0f),
                PathNode.QuadTo(801.0f, -97.0f, 868.5f, -50.0f),
                PathNode.QuadTo(936.0f, -3.0f, 992.0f, 58.0f),
                PathNode.QuadTo(1040.0f, 111.0f, 1069.5f, 168.0f),
                PathNode.QuadTo(1099.0f, 225.0f, 1105.0f, 280.0f),
                PathNode.QuadTo(1116.0f, 390.0f, 1126.0f, 617.0f),
                PathNode.QuadTo(1127.0f, 633.0f, 1126.5f, 649.0f),
                PathNode.QuadTo(1126.0f, 665.0f, 1122.0f, 677.0f),
                PathNode.QuadTo(1115.0f, 699.0f, 1097.0f, 718.0f),
                PathNode.QuadTo(1088.0f, 727.0f, 1074.5f, 734.0f),
                PathNode.QuadTo(1061.0f, 741.0f, 1046.0f, 747.0f),
                PathNode.LineTo(738.0f, 883.0f),
                PathNode.QuadTo(701.0f, 899.0f, 686.0f, 902.0f),
                PathNode.QuadTo(664.0f, 905.0f, 644.0f, 902.0f),
                PathNode.QuadTo(627.0f, 898.0f, 591.0f, 883.0f),
                PathNode.LineTo(284.0f, 747.0f),
                PathNode.LineTo(277.0f, 744.0f),
                PathNode.QuadTo(245.0f, 730.0f, 232.0f, 718.0f),
                PathNode.QuadTo(214.0f, 699.0f, 207.0f, 677.0f),
                PathNode.QuadTo(203.0f, 666.0f, 202.5f, 650.0f),
                PathNode.QuadTo(202.0f, 634.0f, 203.0f, 617.0f),
                PathNode.QuadTo(212.0f, 415.0f, 225.0f, 280.0f),
                PathNode.QuadTo(237.0f, 168.0f, 338.0f, 58.0f),
                PathNode.QuadTo(384.0f, 7.0f, 449.5f, -41.5f),
                PathNode.QuadTo(515.0f, -90.0f, 613.0f, -140.0f),
                PathNode.QuadTo(635.0f, -151.0f, 647.0f, -154.0f),
                PathNode.QuadTo(665.0f, -157.0f, 682.0f, -154.0f),
                PathNode.Close,
                PathNode.MoveTo(381.0f, 98.0f),
                PathNode.QuadTo(336.0f, 146.0f, 312.0f, 194.0f),
                PathNode.QuadTo(288.0f, 242.0f, 284.0f, 286.0f),
                PathNode.QuadTo(273.0f, 401.0f, 262.0f, 637.0f),
                PathNode.QuadTo(261.0f, 657.0f, 267.5f, 667.5f),
                PathNode.QuadTo(274.0f, 678.0f, 292.0f, 686.0f),
                PathNode.LineTo(631.0f, 836.0f),
                PathNode.QuadTo(651.0f, 845.0f, 665.5f, 844.5f),
                PathNode.QuadTo(680.0f, 844.0f, 702.0f, 834.0f),
                PathNode.LineTo(1038.0f, 686.0f),
                PathNode.QuadTo(1055.0f, 679.0f, 1062.0f, 668.0f),
                PathNode.QuadTo(1069.0f, 657.0f, 1068.0f, 639.0f),
                PathNode.QuadTo(1066.0f, 609.0f, 1066.0f, 592.0f),
                PathNode.QuadTo(1062.0f, 511.0f, 1056.5f, 424.0f),
                PathNode.QuadTo(1051.0f, 337.0f, 1046.0f, 286.0f),
                PathNode.QuadTo(1041.0f, 242.0f, 1017.0f, 194.0f),
                PathNode.QuadTo(993.0f, 146.0f, 949.0f, 98.0f),
                PathNode.QuadTo(853.0f, -8.0f, 682.0f, -91.0f),
                PathNode.QuadTo(672.0f, -96.0f, 665.0f, -96.0f),
                PathNode.QuadTo(658.0f, -96.0f, 648.0f, -91.0f),
                PathNode.QuadTo(478.0f, -9.0f, 381.0f, 98.0f),
                PathNode.Close,
                PathNode.MoveTo(707.0f, 166.0f),
                PathNode.QuadTo(707.0f, 183.0f, 695.0f, 195.5f),
                PathNode.QuadTo(683.0f, 208.0f, 666.0f, 208.0f),
                PathNode.QuadTo(649.0f, 208.0f, 636.5f, 195.5f),
                PathNode.QuadTo(624.0f, 183.0f, 624.0f, 166.0f),
                PathNode.QuadTo(624.0f, 149.0f, 636.5f, 137.0f),
                PathNode.QuadTo(649.0f, 125.0f, 666.0f, 125.0f),
                PathNode.QuadTo(683.0f, 125.0f, 695.0f, 137.0f),
                PathNode.QuadTo(707.0f, 149.0f, 707.0f, 166.0f),
                PathNode.Close,
                PathNode.MoveTo(694.0f, 308.0f),
                PathNode.VerticalTo(642.0f),
                PathNode.QuadTo(694.0f, 649.0f, 689.0f, 654.5f),
                PathNode.QuadTo(684.0f, 660.0f, 674.0f, 660.0f),
                PathNode.HorizontalTo(654.0f),
                PathNode.QuadTo(645.0f, 660.0f, 640.0f, 654.5f),
                PathNode.QuadTo(635.0f, 649.0f, 635.0f, 642.0f),
                PathNode.VerticalTo(308.0f),
                PathNode.QuadTo(635.0f, 299.0f, 640.5f, 294.0f),
                PathNode.QuadTo(646.0f, 289.0f, 655.0f, 289.0f),
                PathNode.HorizontalTo(675.0f),
                PathNode.QuadTo(683.0f, 289.0f, 688.5f, 294.0f),
                PathNode.QuadTo(694.0f, 299.0f, 694.0f, 308.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _reportLight!!
    }

private var _reportLight: ImageVector? = null

val MiuixIcons.Regular.Report: ImageVector
    get() {
        if (_reportRegular != null) return _reportRegular!!
        _reportRegular = ImageVector.Builder(
            name = "Report.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1302.0f,
            viewportHeight = 1302.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -13.500000000000028f, translationY = 1025.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(684.0f, -167.0f),
                PathNode.QuadTo(698.0f, -164.0f, 715.0f, -155.0f),
                PathNode.LineTo(722.0f, -152.0f),
                PathNode.QuadTo(807.0f, -110.0f, 875.5f, -62.0f),
                PathNode.QuadTo(944.0f, -14.0f, 1002.0f, 49.0f),
                PathNode.QuadTo(1051.0f, 102.0f, 1081.5f, 161.5f),
                PathNode.QuadTo(1112.0f, 221.0f, 1118.0f, 279.0f),
                PathNode.QuadTo(1129.0f, 388.0f, 1139.0f, 617.0f),
                PathNode.QuadTo(1140.0f, 633.0f, 1139.5f, 650.5f),
                PathNode.QuadTo(1139.0f, 668.0f, 1135.0f, 681.0f),
                PathNode.QuadTo(1126.0f, 707.0f, 1106.0f, 727.0f),
                PathNode.QuadTo(1096.0f, 737.0f, 1084.0f, 743.5f),
                PathNode.QuadTo(1072.0f, 750.0f, 1051.0f, 759.0f),
                PathNode.LineTo(743.0f, 895.0f),
                PathNode.QuadTo(706.0f, 911.0f, 688.0f, 915.0f),
                PathNode.QuadTo(664.0f, 918.0f, 642.0f, 915.0f),
                PathNode.QuadTo(622.0f, 911.0f, 586.0f, 895.0f),
                PathNode.LineTo(279.0f, 759.0f),
                PathNode.LineTo(272.0f, 756.0f),
                PathNode.QuadTo(236.0f, 740.0f, 223.0f, 727.0f),
                PathNode.QuadTo(203.0f, 707.0f, 195.0f, 681.0f),
                PathNode.QuadTo(190.0f, 668.0f, 189.5f, 653.5f),
                PathNode.QuadTo(189.0f, 639.0f, 190.0f, 617.0f),
                PathNode.QuadTo(199.0f, 415.0f, 212.0f, 279.0f),
                PathNode.QuadTo(224.0f, 162.0f, 328.0f, 49.0f),
                PathNode.QuadTo(375.0f, -3.0f, 441.0f, -52.0f),
                PathNode.QuadTo(507.0f, -101.0f, 607.0f, -152.0f),
                PathNode.QuadTo(631.0f, -164.0f, 645.0f, -167.0f),
                PathNode.QuadTo(665.0f, -170.0f, 684.0f, -167.0f),
                PathNode.Close,
                PathNode.MoveTo(391.0f, 107.0f),
                PathNode.QuadTo(348.0f, 154.0f, 324.5f, 199.5f),
                PathNode.QuadTo(301.0f, 245.0f, 297.0f, 287.0f),
                PathNode.QuadTo(286.0f, 403.0f, 275.0f, 637.0f),
                PathNode.QuadTo(274.0f, 653.0f, 278.5f, 660.5f),
                PathNode.QuadTo(283.0f, 668.0f, 297.0f, 674.0f),
                PathNode.LineTo(636.0f, 824.0f),
                PathNode.QuadTo(653.0f, 832.0f, 665.0f, 831.5f),
                PathNode.QuadTo(677.0f, 831.0f, 697.0f, 822.0f),
                PathNode.LineTo(1033.0f, 674.0f),
                PathNode.QuadTo(1046.0f, 669.0f, 1051.0f, 661.5f),
                PathNode.QuadTo(1056.0f, 654.0f, 1055.0f, 640.0f),
                PathNode.QuadTo(1054.0f, 619.0f, 1053.0f, 598.0f),
                PathNode.QuadTo(1049.0f, 516.0f, 1043.5f, 427.0f),
                PathNode.QuadTo(1038.0f, 338.0f, 1033.0f, 287.0f),
                PathNode.QuadTo(1028.0f, 245.0f, 1004.5f, 199.5f),
                PathNode.QuadTo(981.0f, 154.0f, 939.0f, 107.0f),
                PathNode.QuadTo(846.0f, 5.0f, 676.0f, -79.0f),
                PathNode.QuadTo(669.0f, -82.0f, 665.0f, -82.0f),
                PathNode.QuadTo(661.0f, -82.0f, 653.0f, -79.0f),
                PathNode.QuadTo(484.0f, 4.0f, 391.0f, 107.0f),
                PathNode.Close,
                PathNode.MoveTo(720.0f, 166.0f),
                PathNode.QuadTo(720.0f, 189.0f, 704.5f, 205.0f),
                PathNode.QuadTo(689.0f, 221.0f, 666.0f, 221.0f),
                PathNode.QuadTo(644.0f, 221.0f, 627.5f, 205.0f),
                PathNode.QuadTo(611.0f, 189.0f, 611.0f, 166.0f),
                PathNode.QuadTo(611.0f, 144.0f, 627.0f, 128.0f),
                PathNode.QuadTo(643.0f, 112.0f, 666.0f, 112.0f),
                PathNode.QuadTo(689.0f, 112.0f, 704.5f, 127.5f),
                PathNode.QuadTo(720.0f, 143.0f, 720.0f, 166.0f),
                PathNode.Close,
                PathNode.MoveTo(707.0f, 312.0f),
                PathNode.VerticalTo(639.0f),
                PathNode.QuadTo(707.0f, 651.0f, 700.0f, 658.5f),
                PathNode.QuadTo(693.0f, 666.0f, 678.0f, 666.0f),
                PathNode.HorizontalTo(650.0f),
                PathNode.QuadTo(637.0f, 666.0f, 629.5f, 658.0f),
                PathNode.QuadTo(622.0f, 650.0f, 622.0f, 639.0f),
                PathNode.VerticalTo(312.0f),
                PathNode.QuadTo(622.0f, 299.0f, 630.0f, 292.0f),
                PathNode.QuadTo(638.0f, 285.0f, 651.0f, 285.0f),
                PathNode.HorizontalTo(679.0f),
                PathNode.QuadTo(692.0f, 285.0f, 699.5f, 292.0f),
                PathNode.QuadTo(707.0f, 299.0f, 707.0f, 312.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _reportRegular!!
    }

private var _reportRegular: ImageVector? = null

val MiuixIcons.Heavy.Report: ImageVector
    get() {
        if (_reportHeavy != null) return _reportHeavy!!
        _reportHeavy = ImageVector.Builder(
            name = "Report.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1335.342857142857f,
            viewportHeight = 1335.342857142857f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 3.088095238095093f, translationY = 1041.7785714285715f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(687.0f, -180.0f),
                PathNode.QuadTo(699.0f, -178.0f, 721.0f, -167.0f),
                PathNode.LineTo(728.0f, -164.0f),
                PathNode.QuadTo(814.0f, -122.0f, 884.0f, -73.0f),
                PathNode.QuadTo(954.0f, -24.0f, 1012.0f, 40.0f),
                PathNode.QuadTo(1063.0f, 94.0f, 1094.0f, 155.5f),
                PathNode.QuadTo(1125.0f, 217.0f, 1132.0f, 278.0f),
                PathNode.QuadTo(1143.0f, 386.0f, 1153.0f, 617.0f),
                PathNode.QuadTo(1154.0f, 639.0f, 1153.5f, 654.5f),
                PathNode.QuadTo(1153.0f, 670.0f, 1148.0f, 685.0f),
                PathNode.QuadTo(1137.0f, 714.0f, 1116.0f, 737.0f),
                PathNode.QuadTo(1104.0f, 748.0f, 1090.0f, 756.0f),
                PathNode.QuadTo(1076.0f, 764.0f, 1057.0f, 772.0f),
                PathNode.LineTo(749.0f, 907.0f),
                PathNode.QuadTo(716.0f, 922.0f, 690.0f, 929.0f),
                PathNode.QuadTo(664.0f, 932.0f, 640.0f, 929.0f),
                PathNode.QuadTo(613.0f, 922.0f, 580.0f, 907.0f),
                PathNode.LineTo(273.0f, 772.0f),
                PathNode.LineTo(266.0f, 769.0f),
                PathNode.QuadTo(228.0f, 752.0f, 213.0f, 737.0f),
                PathNode.QuadTo(191.0f, 713.0f, 182.0f, 685.0f),
                PathNode.QuadTo(177.0f, 670.0f, 176.0f, 654.5f),
                PathNode.QuadTo(175.0f, 639.0f, 176.0f, 617.0f),
                PathNode.QuadTo(185.0f, 415.0f, 198.0f, 278.0f),
                PathNode.QuadTo(211.0f, 156.0f, 318.0f, 40.0f),
                PathNode.QuadTo(365.0f, -13.0f, 432.0f, -63.0f),
                PathNode.QuadTo(499.0f, -113.0f, 601.0f, -164.0f),
                PathNode.QuadTo(628.0f, -177.0f, 643.0f, -181.0f),
                PathNode.QuadTo(667.0f, -184.0f, 687.0f, -180.0f),
                PathNode.Close,
                PathNode.MoveTo(401.0f, 116.0f),
                PathNode.QuadTo(359.0f, 161.0f, 336.5f, 205.0f),
                PathNode.QuadTo(314.0f, 249.0f, 311.0f, 288.0f),
                PathNode.QuadTo(300.0f, 397.0f, 289.0f, 630.0f),
                PathNode.QuadTo(288.0f, 644.0f, 292.0f, 651.0f),
                PathNode.QuadTo(296.0f, 658.0f, 308.0f, 664.0f),
                PathNode.LineTo(642.0f, 812.0f),
                PathNode.QuadTo(655.0f, 817.0f, 665.0f, 817.0f),
                PathNode.QuadTo(675.0f, 817.0f, 691.0f, 810.0f),
                PathNode.LineTo(1023.0f, 663.0f),
                PathNode.QuadTo(1033.0f, 659.0f, 1037.5f, 653.0f),
                PathNode.QuadTo(1042.0f, 647.0f, 1041.0f, 636.0f),
                PathNode.QuadTo(1040.0f, 615.0f, 1039.0f, 593.0f),
                PathNode.QuadTo(1035.0f, 513.0f, 1030.0f, 428.0f),
                PathNode.QuadTo(1025.0f, 343.0f, 1019.0f, 288.0f),
                PathNode.QuadTo(1015.0f, 249.0f, 992.5f, 205.0f),
                PathNode.QuadTo(970.0f, 161.0f, 929.0f, 116.0f),
                PathNode.QuadTo(838.0f, 18.0f, 676.0f, -63.0f),
                PathNode.QuadTo(669.0f, -67.0f, 665.0f, -67.0f),
                PathNode.QuadTo(661.0f, -67.0f, 653.0f, -64.0f),
                PathNode.QuadTo(494.0f, 15.0f, 401.0f, 116.0f),
                PathNode.Close,
                PathNode.MoveTo(734.0f, 166.0f),
                PathNode.QuadTo(734.0f, 195.0f, 714.0f, 215.0f),
                PathNode.QuadTo(694.0f, 235.0f, 666.0f, 235.0f),
                PathNode.QuadTo(638.0f, 235.0f, 617.5f, 214.5f),
                PathNode.QuadTo(597.0f, 194.0f, 597.0f, 166.0f),
                PathNode.QuadTo(597.0f, 139.0f, 617.0f, 118.5f),
                PathNode.QuadTo(637.0f, 98.0f, 666.0f, 98.0f),
                PathNode.QuadTo(695.0f, 98.0f, 714.5f, 118.0f),
                PathNode.QuadTo(734.0f, 138.0f, 734.0f, 166.0f),
                PathNode.Close,
                PathNode.MoveTo(721.0f, 330.0f),
                PathNode.VerticalTo(630.0f),
                PathNode.QuadTo(721.0f, 648.0f, 710.0f, 659.5f),
                PathNode.QuadTo(699.0f, 671.0f, 678.0f, 671.0f),
                PathNode.HorizontalTo(650.0f),
                PathNode.QuadTo(631.0f, 671.0f, 619.5f, 659.0f),
                PathNode.QuadTo(608.0f, 647.0f, 608.0f, 630.0f),
                PathNode.VerticalTo(330.0f),
                PathNode.QuadTo(608.0f, 312.0f, 620.5f, 300.5f),
                PathNode.QuadTo(633.0f, 289.0f, 651.0f, 289.0f),
                PathNode.HorizontalTo(679.0f),
                PathNode.QuadTo(697.0f, 289.0f, 709.0f, 300.5f),
                PathNode.QuadTo(721.0f, 312.0f, 721.0f, 330.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _reportHeavy!!
    }

private var _reportHeavy: ImageVector? = null
