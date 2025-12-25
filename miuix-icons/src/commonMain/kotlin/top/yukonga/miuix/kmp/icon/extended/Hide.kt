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

val MiuixIcons.Hide: ImageVector
    get() = MiuixIcons.Regular.Hide

val MiuixIcons.Light.Hide: ImageVector
    get() {
        if (_hideLight != null) return _hideLight!!
        _hideLight = ImageVector.Builder(
            name = "Hide.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1345.9485714285713f,
            viewportHeight = 1345.9485714285713f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 8.686190476190404f, translationY = 1053.7389915966387f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1064.0f, -35.0f),
                PathNode.LineTo(1077.0f, -21.0f),
                PathNode.QuadTo(1084.0f, -14.0f, 1083.5f, -7.0f),
                PathNode.QuadTo(1083.0f, 0.0f, 1076.0f, 7.0f),
                PathNode.LineTo(287.0f, 796.0f),
                PathNode.QuadTo(281.0f, 803.0f, 273.5f, 803.5f),
                PathNode.QuadTo(266.0f, 804.0f, 259.0f, 796.0f),
                PathNode.LineTo(247.0f, 784.0f),
                PathNode.QuadTo(239.0f, 777.0f, 238.5f, 770.0f),
                PathNode.QuadTo(238.0f, 763.0f, 247.0f, 754.0f),
                PathNode.LineTo(1035.0f, -35.0f),
                PathNode.QuadTo(1042.0f, -42.0f, 1049.0f, -42.0f),
                PathNode.QuadTo(1056.0f, -42.0f, 1064.0f, -35.0f),
                PathNode.Close,
                PathNode.MoveTo(841.0f, 4.0f),
                PathNode.LineTo(794.0f, 50.0f),
                PathNode.QuadTo(726.0f, 33.0f, 660.0f, 33.0f),
                PathNode.QuadTo(560.0f, 33.0f, 462.0f, 74.5f),
                PathNode.QuadTo(364.0f, 116.0f, 286.0f, 190.0f),
                PathNode.QuadTo(208.0f, 264.0f, 166.0f, 356.0f),
                PathNode.QuadTo(156.0f, 377.0f, 166.0f, 396.0f),
                PathNode.QuadTo(188.0f, 444.0f, 221.5f, 488.5f),
                PathNode.QuadTo(255.0f, 533.0f, 306.0f, 579.0f),
                PathNode.LineTo(264.0f, 621.0f),
                PathNode.QuadTo(210.0f, 572.0f, 174.0f, 523.5f),
                PathNode.QuadTo(138.0f, 475.0f, 114.0f, 422.0f),
                PathNode.QuadTo(104.0f, 400.0f, 103.5f, 376.5f),
                PathNode.QuadTo(103.0f, 353.0f, 113.0f, 332.0f),
                PathNode.QuadTo(160.0f, 229.0f, 245.5f, 147.5f),
                PathNode.QuadTo(331.0f, 66.0f, 440.0f, 20.0f),
                PathNode.QuadTo(549.0f, -26.0f, 662.0f, -26.0f),
                PathNode.QuadTo(753.0f, -26.0f, 841.0f, 4.0f),
                PathNode.Close,
                PathNode.MoveTo(707.0f, 165.0f),
                PathNode.LineTo(651.0f, 223.0f),
                PathNode.QuadTo(616.0f, 225.0f, 591.5f, 238.0f),
                PathNode.QuadTo(567.0f, 251.0f, 548.0f, 273.0f),
                PathNode.QuadTo(515.0f, 311.0f, 511.0f, 361.0f),
                PathNode.LineTo(455.0f, 419.0f),
                PathNode.QuadTo(450.0f, 394.0f, 450.0f, 376.0f),
                PathNode.QuadTo(450.0f, 318.0f, 477.0f, 270.5f),
                PathNode.QuadTo(504.0f, 223.0f, 550.0f, 194.0f),
                PathNode.QuadTo(596.0f, 165.0f, 649.0f, 162.0f),
                PathNode.QuadTo(688.0f, 162.0f, 707.0f, 165.0f),
                PathNode.Close,
                PathNode.MoveTo(471.0f, 743.0f),
                PathNode.LineTo(518.0f, 696.0f),
                PathNode.QuadTo(593.0f, 718.0f, 666.0f, 718.0f),
                PathNode.QuadTo(766.0f, 718.0f, 864.5f, 677.0f),
                PathNode.QuadTo(963.0f, 636.0f, 1041.5f, 563.5f),
                PathNode.QuadTo(1120.0f, 491.0f, 1161.0f, 399.0f),
                PathNode.QuadTo(1171.0f, 374.0f, 1164.0f, 357.0f),
                PathNode.QuadTo(1125.0f, 272.0f, 1049.0f, 196.0f),
                PathNode.LineTo(1090.0f, 153.0f),
                PathNode.QuadTo(1174.0f, 237.0f, 1217.0f, 333.0f),
                PathNode.QuadTo(1226.0f, 354.0f, 1225.0f, 378.0f),
                PathNode.QuadTo(1224.0f, 402.0f, 1215.0f, 425.0f),
                PathNode.QuadTo(1168.0f, 527.0f, 1082.5f, 607.0f),
                PathNode.QuadTo(997.0f, 687.0f, 888.5f, 732.0f),
                PathNode.QuadTo(780.0f, 777.0f, 668.0f, 777.0f),
                PathNode.QuadTo(568.0f, 777.0f, 471.0f, 743.0f),
                PathNode.Close,
                PathNode.MoveTo(625.0f, 586.0f),
                PathNode.LineTo(684.0f, 526.0f),
                PathNode.QuadTo(736.0f, 521.0f, 769.0f, 491.0f),
                PathNode.QuadTo(789.0f, 474.0f, 801.5f, 450.0f),
                PathNode.QuadTo(814.0f, 426.0f, 817.0f, 397.0f),
                PathNode.LineTo(876.0f, 336.0f),
                PathNode.QuadTo(881.0f, 357.0f, 879.0f, 387.0f),
                PathNode.QuadTo(877.0f, 442.0f, 849.0f, 487.5f),
                PathNode.QuadTo(821.0f, 533.0f, 774.5f, 560.5f),
                PathNode.QuadTo(728.0f, 588.0f, 673.0f, 589.0f),
                PathNode.QuadTo(646.0f, 590.0f, 625.0f, 586.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _hideLight!!
    }

private var _hideLight: ImageVector? = null

val MiuixIcons.Regular.Hide: ImageVector
    get() {
        if (_hideRegular != null) return _hideRegular!!
        _hideRegular = ImageVector.Builder(
            name = "Hide.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1368.0f,
            viewportHeight = 1368.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 19.0f, translationY = 1063.4980952380952f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1072.0f, -47.0f),
                PathNode.LineTo(1090.0f, -29.0f),
                PathNode.QuadTo(1101.0f, -18.0f, 1100.0f, -8.0f),
                PathNode.QuadTo(1099.0f, 2.0f, 1088.0f, 13.0f),
                PathNode.LineTo(295.0f, 807.0f),
                PathNode.QuadTo(285.0f, 817.0f, 274.5f, 817.5f),
                PathNode.QuadTo(264.0f, 818.0f, 252.0f, 806.0f),
                PathNode.LineTo(237.0f, 791.0f),
                PathNode.QuadTo(225.0f, 779.0f, 224.0f, 768.5f),
                PathNode.QuadTo(223.0f, 758.0f, 236.0f, 745.0f),
                PathNode.LineTo(1030.0f, -49.0f),
                PathNode.QuadTo(1040.0f, -59.0f, 1050.5f, -58.5f),
                PathNode.QuadTo(1061.0f, -58.0f, 1072.0f, -47.0f),
                PathNode.Close,
                PathNode.MoveTo(869.0f, 1.0f),
                PathNode.LineTo(802.0f, 69.0f),
                PathNode.QuadTo(733.0f, 49.0f, 665.0f, 49.0f),
                PathNode.QuadTo(567.0f, 49.0f, 471.5f, 89.0f),
                PathNode.QuadTo(376.0f, 129.0f, 300.0f, 199.5f),
                PathNode.QuadTo(224.0f, 270.0f, 183.0f, 360.0f),
                PathNode.QuadTo(177.0f, 376.0f, 183.0f, 390.0f),
                PathNode.QuadTo(205.0f, 438.0f, 237.0f, 480.5f),
                PathNode.QuadTo(269.0f, 523.0f, 310.0f, 560.0f),
                PathNode.LineTo(250.0f, 621.0f),
                PathNode.QuadTo(204.0f, 579.0f, 167.0f, 530.0f),
                PathNode.QuadTo(130.0f, 481.0f, 106.0f, 426.0f),
                PathNode.QuadTo(95.0f, 402.0f, 95.0f, 375.5f),
                PathNode.QuadTo(95.0f, 349.0f, 106.0f, 325.0f),
                PathNode.QuadTo(153.0f, 220.0f, 241.0f, 137.5f),
                PathNode.QuadTo(329.0f, 55.0f, 440.0f, 9.0f),
                PathNode.QuadTo(551.0f, -37.0f, 665.0f, -37.0f),
                PathNode.QuadTo(765.0f, -37.0f, 869.0f, 1.0f),
                PathNode.Close,
                PathNode.MoveTo(715.0f, 156.0f),
                PathNode.LineTo(632.0f, 240.0f),
                PathNode.QuadTo(611.0f, 244.0f, 593.0f, 255.0f),
                PathNode.QuadTo(575.0f, 266.0f, 561.0f, 281.0f),
                PathNode.QuadTo(536.0f, 307.0f, 527.0f, 344.0f),
                PathNode.LineTo(444.0f, 428.0f),
                PathNode.QuadTo(438.0f, 403.0f, 438.0f, 377.0f),
                PathNode.QuadTo(438.0f, 315.0f, 468.5f, 263.0f),
                PathNode.QuadTo(499.0f, 211.0f, 551.0f, 180.5f),
                PathNode.QuadTo(603.0f, 150.0f, 664.0f, 150.0f),
                PathNode.QuadTo(691.0f, 150.0f, 715.0f, 156.0f),
                PathNode.Close,
                PathNode.MoveTo(467.0f, 752.0f),
                PathNode.LineTo(536.0f, 684.0f),
                PathNode.QuadTo(605.0f, 702.0f, 665.0f, 702.0f),
                PathNode.QuadTo(764.0f, 702.0f, 859.5f, 662.5f),
                PathNode.QuadTo(955.0f, 623.0f, 1030.0f, 552.0f),
                PathNode.QuadTo(1105.0f, 481.0f, 1146.0f, 391.0f),
                PathNode.QuadTo(1152.0f, 377.0f, 1146.0f, 361.0f),
                PathNode.QuadTo(1105.0f, 271.0f, 1024.0f, 196.0f),
                PathNode.LineTo(1084.0f, 135.0f),
                PathNode.QuadTo(1174.0f, 218.0f, 1224.0f, 326.0f),
                PathNode.QuadTo(1235.0f, 350.0f, 1235.0f, 376.5f),
                PathNode.QuadTo(1235.0f, 403.0f, 1224.0f, 427.0f),
                PathNode.QuadTo(1177.0f, 531.0f, 1089.5f, 613.0f),
                PathNode.QuadTo(1002.0f, 695.0f, 891.0f, 741.0f),
                PathNode.QuadTo(780.0f, 787.0f, 665.0f, 787.0f),
                PathNode.QuadTo(564.0f, 787.0f, 467.0f, 752.0f),
                PathNode.Close,
                PathNode.MoveTo(622.0f, 597.0f),
                PathNode.LineTo(711.0f, 508.0f),
                PathNode.QuadTo(743.0f, 496.0f, 762.0f, 477.0f),
                PathNode.QuadTo(775.0f, 465.0f, 784.5f, 450.5f),
                PathNode.QuadTo(794.0f, 436.0f, 799.0f, 419.0f),
                PathNode.LineTo(887.0f, 330.0f),
                PathNode.QuadTo(892.0f, 355.0f, 892.0f, 375.0f),
                PathNode.QuadTo(892.0f, 436.0f, 861.5f, 488.0f),
                PathNode.QuadTo(831.0f, 540.0f, 779.0f, 570.5f),
                PathNode.QuadTo(727.0f, 601.0f, 665.0f, 601.0f),
                PathNode.QuadTo(641.0f, 601.0f, 622.0f, 597.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _hideRegular!!
    }

private var _hideRegular: ImageVector? = null

val MiuixIcons.Heavy.Hide: ImageVector
    get() {
        if (_hideHeavy != null) return _hideHeavy!!
        _hideHeavy = ImageVector.Builder(
            name = "Hide.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1393.2f,
            viewportHeight = 1393.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 32.10000000000002f, translationY = 1076.825892857143f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1075.0f, -55.0f),
                PathNode.LineTo(1093.0f, -37.0f),
                PathNode.QuadTo(1108.0f, -22.0f, 1107.0f, -5.5f),
                PathNode.QuadTo(1106.0f, 11.0f, 1091.0f, 27.0f),
                PathNode.LineTo(310.0f, 816.0f),
                PathNode.QuadTo(295.0f, 831.0f, 278.5f, 831.5f),
                PathNode.QuadTo(262.0f, 832.0f, 245.0f, 815.0f),
                PathNode.LineTo(230.0f, 800.0f),
                PathNode.QuadTo(213.0f, 784.0f, 212.0f, 767.0f),
                PathNode.QuadTo(211.0f, 750.0f, 229.0f, 732.0f),
                PathNode.LineTo(1011.0f, -57.0f),
                PathNode.QuadTo(1026.0f, -72.0f, 1043.0f, -71.0f),
                PathNode.QuadTo(1060.0f, -70.0f, 1075.0f, -55.0f),
                PathNode.Close,
                PathNode.MoveTo(857.0f, -15.0f),
                PathNode.LineTo(767.0f, 81.0f),
                PathNode.QuadTo(713.0f, 68.0f, 665.0f, 68.0f),
                PathNode.QuadTo(571.0f, 68.0f, 479.0f, 106.0f),
                PathNode.QuadTo(387.0f, 144.0f, 314.0f, 212.5f),
                PathNode.QuadTo(241.0f, 281.0f, 202.0f, 367.0f),
                PathNode.QuadTo(199.0f, 376.0f, 202.0f, 384.0f),
                PathNode.QuadTo(221.0f, 427.0f, 250.5f, 466.0f),
                PathNode.QuadTo(280.0f, 505.0f, 313.0f, 536.0f),
                PathNode.LineTo(232.0f, 619.0f),
                PathNode.QuadTo(197.0f, 588.0f, 159.0f, 536.0f),
                PathNode.QuadTo(121.0f, 484.0f, 97.0f, 432.0f),
                PathNode.QuadTo(84.0f, 406.0f, 84.0f, 376.0f),
                PathNode.QuadTo(84.0f, 346.0f, 97.0f, 319.0f),
                PathNode.QuadTo(145.0f, 213.0f, 234.0f, 129.5f),
                PathNode.QuadTo(323.0f, 46.0f, 436.0f, -1.0f),
                PathNode.QuadTo(549.0f, -48.0f, 665.0f, -48.0f),
                PathNode.QuadTo(772.0f, -48.0f, 857.0f, -15.0f),
                PathNode.Close,
                PathNode.MoveTo(710.0f, 145.0f),
                PathNode.LineTo(611.0f, 247.0f),
                PathNode.QuadTo(597.0f, 252.0f, 584.0f, 260.5f),
                PathNode.QuadTo(571.0f, 269.0f, 561.0f, 279.0f),
                PathNode.QuadTo(542.0f, 300.0f, 533.0f, 323.0f),
                PathNode.LineTo(437.0f, 422.0f),
                PathNode.QuadTo(430.0f, 400.0f, 430.0f, 372.0f),
                PathNode.QuadTo(430.0f, 309.0f, 461.5f, 256.0f),
                PathNode.QuadTo(493.0f, 203.0f, 546.0f, 172.0f),
                PathNode.QuadTo(599.0f, 141.0f, 662.0f, 141.0f),
                PathNode.QuadTo(695.0f, 141.0f, 710.0f, 145.0f),
                PathNode.Close,
                PathNode.MoveTo(473.0f, 765.0f),
                PathNode.LineTo(565.0f, 671.0f),
                PathNode.QuadTo(624.0f, 684.0f, 670.0f, 684.0f),
                PathNode.QuadTo(763.0f, 684.0f, 853.5f, 645.5f),
                PathNode.QuadTo(944.0f, 607.0f, 1016.0f, 539.0f),
                PathNode.QuadTo(1088.0f, 471.0f, 1127.0f, 385.0f),
                PathNode.QuadTo(1130.0f, 377.0f, 1127.0f, 368.0f),
                PathNode.QuadTo(1085.0f, 276.0f, 1017.0f, 215.0f),
                PathNode.LineTo(1098.0f, 132.0f),
                PathNode.QuadTo(1181.0f, 205.0f, 1233.0f, 320.0f),
                PathNode.QuadTo(1245.0f, 347.0f, 1245.0f, 377.0f),
                PathNode.QuadTo(1245.0f, 407.0f, 1233.0f, 433.0f),
                PathNode.QuadTo(1185.0f, 539.0f, 1096.0f, 622.5f),
                PathNode.QuadTo(1007.0f, 706.0f, 894.5f, 752.5f),
                PathNode.QuadTo(782.0f, 799.0f, 665.0f, 799.0f),
                PathNode.QuadTo(565.0f, 799.0f, 473.0f, 765.0f),
                PathNode.Close,
                PathNode.MoveTo(624.0f, 605.0f),
                PathNode.LineTo(727.0f, 501.0f),
                PathNode.QuadTo(751.0f, 489.0f, 766.0f, 474.0f),
                PathNode.QuadTo(776.0f, 464.0f, 783.5f, 454.0f),
                PathNode.QuadTo(791.0f, 444.0f, 796.0f, 432.0f),
                PathNode.LineTo(895.0f, 331.0f),
                PathNode.QuadTo(901.0f, 354.0f, 901.0f, 377.0f),
                PathNode.QuadTo(901.0f, 440.0f, 869.5f, 493.0f),
                PathNode.QuadTo(838.0f, 546.0f, 784.5f, 577.0f),
                PathNode.QuadTo(731.0f, 608.0f, 669.0f, 608.0f),
                PathNode.QuadTo(641.0f, 608.0f, 624.0f, 605.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _hideHeavy!!
    }

private var _hideHeavy: ImageVector? = null
