// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.Redo: ImageVector
    get() = MiuixIcons.Glass.Regular.Redo

val MiuixIcons.Glass.Light.Redo: ImageVector
    get() {
        if (_redoLight != null) return _redoLight!!
        _redoLight = ImageVector.Builder(
            name = "Redo.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(162.3f, 115.7f),
                        PathNode.LineTo(162.3f, 211.1f),
                        PathNode.QuadTo(162.3f, 277.1f, 163.3f, 334.1f),
                        PathNode.QuadTo(164.3f, 391.0f, 167.3f, 428.5f),
                        PathNode.QuadTo(173.3f, 504.8f, 195.1f, 549.9f),
                        PathNode.QuadTo(217.0f, 592.7f, 251.0f, 627.0f),
                        PathNode.QuadTo(285.1f, 661.2f, 327.9f, 682.1f),
                        PathNode.QuadTo(351.2f, 694.0f, 379.8f, 700.7f),
                        PathNode.QuadTo(408.4f, 707.5f, 449.3f, 710.5f),
                        PathNode.QuadTo(496.2f, 714.5f, 626.7f, 714.5f),
                        PathNode.LineTo(666.7f, 714.5f),
                        PathNode.LineTo(942.7f, 713.5f),
                        PathNode.LineTo(719.3f, 491.1f),
                        PathNode.QuadTo(707.4f, 479.2f, 707.2f, 471.8f),
                        PathNode.QuadTo(706.9f, 464.3f, 717.2f, 454.0f),
                        PathNode.LineTo(725.1f, 446.1f),
                        PathNode.QuadTo(734.9f, 436.3f, 742.0f, 436.3f),
                        PathNode.QuadTo(749.2f, 436.3f, 761.2f, 448.3f),
                        PathNode.LineTo(1028.1f, 716.2f),
                        PathNode.QuadTo(1039.9f, 728.0f, 1039.9f, 743.3f),
                        PathNode.QuadTo(1039.9f, 758.7f, 1028.1f, 770.0f),
                        PathNode.LineTo(757.9f, 1040.2f),
                        PathNode.QuadTo(749.8f, 1048.3f, 742.3f, 1048.6f),
                        PathNode.QuadTo(734.9f, 1048.9f, 727.3f, 1041.3f),
                        PathNode.LineTo(715.4f, 1029.5f),
                        PathNode.QuadTo(707.9f, 1021.9f, 707.6f, 1014.5f),
                        PathNode.QuadTo(707.3f, 1007.0f, 716.0f, 998.9f),
                        PathNode.LineTo(943.1f, 772.8f),
                        PathNode.LineTo(664.7f, 773.8f),
                        PathNode.QuadTo(502.1f, 773.8f, 444.5f, 769.8f),
                        PathNode.QuadTo(398.4f, 765.8f, 364.3f, 758.0f),
                        PathNode.QuadTo(330.1f, 750.2f, 301.4f, 735.1f),
                        PathNode.QuadTo(250.2f, 709.0f, 209.8f, 668.3f),
                        PathNode.QuadTo(169.3f, 627.6f, 143.2f, 575.9f),
                        PathNode.QuadTo(128.1f, 547.2f, 120.0f, 512.8f),
                        PathNode.QuadTo(112.0f, 478.3f, 108.0f, 432.8f),
                        PathNode.QuadTo(105.0f, 394.2f, 104.0f, 336.2f),
                        PathNode.QuadTo(103.0f, 278.1f, 103.0f, 213.1f),
                        PathNode.LineTo(103.0f, 115.7f),
                        PathNode.QuadTo(103.0f, 104.9f, 107.8f, 99.6f),
                        PathNode.QuadTo(112.5f, 94.2f, 122.8f, 94.2f),
                        PathNode.LineTo(141.4f, 94.2f),
                        PathNode.QuadTo(152.8f, 94.2f, 157.5f, 99.6f),
                        PathNode.QuadTo(162.3f, 104.9f, 162.3f, 115.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _redoLight!!
    }

private var _redoLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Redo: ImageVector
    get() {
        if (_redoNormal != null) return _redoNormal!!
        _redoNormal = ImageVector.Builder(
            name = "Redo.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(171.3f, 117.7f),
                        PathNode.LineTo(171.3f, 207.4f),
                        PathNode.QuadTo(171.3f, 273.4f, 172.3f, 330.0f),
                        PathNode.QuadTo(173.3f, 386.6f, 176.3f, 423.8f),
                        PathNode.QuadTo(182.3f, 499.2f, 202.9f, 542.2f),
                        PathNode.QuadTo(224.2f, 583.7f, 257.2f, 616.8f),
                        PathNode.QuadTo(290.1f, 649.8f, 331.7f, 670.1f),
                        PathNode.QuadTo(354.1f, 681.4f, 382.0f, 688.0f),
                        PathNode.QuadTo(409.8f, 694.5f, 450.1f, 697.5f),
                        PathNode.QuadTo(496.4f, 701.5f, 626.5f, 701.5f),
                        PathNode.LineTo(666.5f, 701.5f),
                        PathNode.LineTo(920.5f, 700.5f),
                        PathNode.LineTo(714.6f, 495.6f),
                        PathNode.QuadTo(697.8f, 478.8f, 697.4f, 469.0f),
                        PathNode.QuadTo(696.9f, 459.1f, 711.9f, 444.2f),
                        PathNode.LineTo(719.1f, 436.9f),
                        PathNode.QuadTo(733.2f, 422.8f, 742.7f, 422.8f),
                        PathNode.QuadTo(752.1f, 422.8f, 769.6f, 440.3f),
                        PathNode.LineTo(1035.9f, 707.6f),
                        PathNode.QuadTo(1049.2f, 720.9f, 1049.2f, 739.4f),
                        PathNode.QuadTo(1049.2f, 758.0f, 1035.9f, 771.1f),
                        PathNode.LineTo(764.4f, 1042.6f),
                        PathNode.QuadTo(753.0f, 1054.1f, 743.1f, 1054.5f),
                        PathNode.QuadTo(733.2f, 1054.9f, 722.6f, 1044.3f),
                        PathNode.LineTo(708.6f, 1030.3f),
                        PathNode.QuadTo(697.9f, 1019.7f, 697.5f, 1009.8f),
                        PathNode.QuadTo(697.1f, 999.9f, 709.4f, 988.4f),
                        PathNode.LineTo(920.6f, 778.2f),
                        PathNode.LineTo(664.5f, 779.2f),
                        PathNode.QuadTo(501.6f, 779.2f, 443.8f, 775.2f),
                        PathNode.QuadTo(397.1f, 771.2f, 362.0f, 763.3f),
                        PathNode.QuadTo(326.9f, 755.3f, 297.3f, 739.6f),
                        PathNode.QuadTo(244.9f, 712.9f, 203.3f, 671.0f),
                        PathNode.QuadTo(161.8f, 629.0f, 135.1f, 576.4f),
                        PathNode.QuadTo(119.4f, 546.8f, 111.0f, 511.3f),
                        PathNode.QuadTo(102.7f, 475.8f, 98.7f, 430.0f),
                        PathNode.QuadTo(95.7f, 391.1f, 94.7f, 332.7f),
                        PathNode.QuadTo(93.7f, 274.4f, 93.7f, 209.4f),
                        PathNode.LineTo(93.7f, 117.7f),
                        PathNode.QuadTo(93.7f, 102.7f, 100.0f, 95.5f),
                        PathNode.QuadTo(106.3f, 88.3f, 120.5f, 88.3f),
                        PathNode.LineTo(142.8f, 88.3f),
                        PathNode.QuadTo(158.7f, 88.3f, 165.0f, 95.5f),
                        PathNode.QuadTo(171.3f, 102.7f, 171.3f, 117.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _redoNormal!!
    }

private var _redoNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Redo: ImageVector
    get() {
        if (_redoRegular != null) return _redoRegular!!
        _redoRegular = ImageVector.Builder(
            name = "Redo.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(175.4f, 118.7f),
                        PathNode.LineTo(175.4f, 205.7f),
                        PathNode.QuadTo(175.4f, 271.7f, 176.4f, 328.2f),
                        PathNode.QuadTo(177.4f, 384.7f, 180.4f, 421.7f),
                        PathNode.QuadTo(186.4f, 496.7f, 206.4f, 538.7f),
                        PathNode.QuadTo(227.4f, 579.7f, 259.9f, 612.2f),
                        PathNode.QuadTo(292.4f, 644.7f, 333.4f, 664.7f),
                        PathNode.QuadTo(355.4f, 675.7f, 382.9f, 682.2f),
                        PathNode.QuadTo(410.4f, 688.7f, 450.4f, 691.7f),
                        PathNode.QuadTo(496.4f, 695.7f, 626.4f, 695.7f),
                        PathNode.LineTo(666.4f, 695.7f),
                        PathNode.LineTo(910.4f, 694.7f),
                        PathNode.LineTo(712.4f, 497.7f),
                        PathNode.QuadTo(693.4f, 478.7f, 692.9f, 467.7f),
                        PathNode.QuadTo(692.4f, 456.7f, 709.4f, 439.7f),
                        PathNode.LineTo(716.4f, 432.7f),
                        PathNode.QuadTo(732.4f, 416.7f, 742.9f, 416.7f),
                        PathNode.QuadTo(753.4f, 416.7f, 773.4f, 436.7f),
                        PathNode.LineTo(1039.4f, 703.7f),
                        PathNode.QuadTo(1053.4f, 717.7f, 1053.4f, 737.7f),
                        PathNode.QuadTo(1053.4f, 757.7f, 1039.4f, 771.7f),
                        PathNode.LineTo(767.4f, 1043.7f),
                        PathNode.QuadTo(754.4f, 1056.7f, 743.4f, 1057.2f),
                        PathNode.QuadTo(732.4f, 1057.7f, 720.4f, 1045.7f),
                        PathNode.LineTo(705.4f, 1030.7f),
                        PathNode.QuadTo(693.4f, 1018.7f, 692.9f, 1007.7f),
                        PathNode.QuadTo(692.4f, 996.7f, 706.4f, 983.7f),
                        PathNode.LineTo(910.4f, 780.7f),
                        PathNode.LineTo(664.4f, 781.7f),
                        PathNode.QuadTo(501.4f, 781.7f, 443.4f, 777.7f),
                        PathNode.QuadTo(396.4f, 773.7f, 360.9f, 765.7f),
                        PathNode.QuadTo(325.4f, 757.7f, 295.4f, 741.7f),
                        PathNode.QuadTo(242.4f, 714.7f, 200.4f, 672.2f),
                        PathNode.QuadTo(158.4f, 629.7f, 131.4f, 576.7f),
                        PathNode.QuadTo(115.4f, 546.7f, 106.9f, 510.7f),
                        PathNode.QuadTo(98.4f, 474.7f, 94.4f, 428.7f),
                        PathNode.QuadTo(91.4f, 389.7f, 90.4f, 331.2f),
                        PathNode.QuadTo(89.4f, 272.7f, 89.4f, 207.7f),
                        PathNode.LineTo(89.4f, 118.7f),
                        PathNode.QuadTo(89.4f, 101.7f, 96.4f, 93.7f),
                        PathNode.QuadTo(103.4f, 85.7f, 119.4f, 85.7f),
                        PathNode.LineTo(143.4f, 85.7f),
                        PathNode.QuadTo(161.4f, 85.7f, 168.4f, 93.7f),
                        PathNode.QuadTo(175.4f, 101.7f, 175.4f, 118.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _redoRegular!!
    }

private var _redoRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Redo: ImageVector
    get() {
        if (_redoMedium != null) return _redoMedium!!
        _redoMedium = ImageVector.Builder(
            name = "Redo.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(184.9f, 118.5f),
                        PathNode.LineTo(184.9f, 205.5f),
                        PathNode.QuadTo(184.9f, 271.3f, 185.9f, 327.6f),
                        PathNode.QuadTo(186.9f, 384.0f, 189.9f, 420.7f),
                        PathNode.QuadTo(195.9f, 494.6f, 214.8f, 534.2f),
                        PathNode.QuadTo(235.3f, 574.1f, 266.7f, 605.4f),
                        PathNode.QuadTo(298.1f, 636.7f, 337.8f, 656.2f),
                        PathNode.QuadTo(358.7f, 666.9f, 385.2f, 673.0f),
                        PathNode.QuadTo(411.8f, 679.1f, 451.2f, 682.1f),
                        PathNode.QuadTo(496.7f, 686.1f, 626.4f, 686.1f),
                        PathNode.LineTo(666.4f, 686.1f),
                        PathNode.LineTo(887.7f, 685.1f),
                        PathNode.LineTo(705.7f, 504.3f),
                        PathNode.QuadTo(683.4f, 482.0f, 683.3f, 467.1f),
                        PathNode.QuadTo(683.2f, 452.2f, 702.7f, 432.8f),
                        PathNode.LineTo(709.7f, 425.8f),
                        PathNode.QuadTo(729.2f, 406.5f, 743.5f, 406.9f),
                        PathNode.QuadTo(757.8f, 407.3f, 780.2f, 429.8f),
                        PathNode.LineTo(1046.2f, 696.8f),
                        PathNode.QuadTo(1062.9f, 713.8f, 1062.9f, 737.5f),
                        PathNode.QuadTo(1062.9f, 761.3f, 1046.2f, 778.3f),
                        PathNode.LineTo(774.2f, 1050.3f),
                        PathNode.QuadTo(758.2f, 1066.3f, 743.4f, 1066.8f),
                        PathNode.QuadTo(728.6f, 1067.3f, 713.7f, 1052.3f),
                        PathNode.LineTo(698.7f, 1037.3f),
                        PathNode.QuadTo(683.7f, 1022.3f, 683.5f, 1007.4f),
                        PathNode.QuadTo(683.2f, 992.5f, 699.9f, 976.8f),
                        PathNode.LineTo(887.5f, 790.0f),
                        PathNode.LineTo(664.4f, 791.0f),
                        PathNode.QuadTo(501.2f, 791.0f, 442.6f, 787.0f),
                        PathNode.QuadTo(395.1f, 783.0f, 358.5f, 774.7f),
                        PathNode.QuadTo(321.9f, 766.5f, 291.1f, 749.9f),
                        PathNode.QuadTo(236.8f, 722.4f, 193.7f, 678.8f),
                        PathNode.QuadTo(150.6f, 635.2f, 123.1f, 580.9f),
                        PathNode.QuadTo(106.8f, 550.0f, 97.9f, 512.8f),
                        PathNode.QuadTo(89.0f, 475.6f, 85.0f, 429.3f),
                        PathNode.QuadTo(82.0f, 390.1f, 81.0f, 331.4f),
                        PathNode.QuadTo(80.0f, 272.8f, 80.0f, 207.5f),
                        PathNode.LineTo(80.0f, 118.5f),
                        PathNode.QuadTo(80.0f, 97.5f, 89.7f, 86.8f),
                        PathNode.QuadTo(99.4f, 76.1f, 119.4f, 76.1f),
                        PathNode.LineTo(143.4f, 76.1f),
                        PathNode.QuadTo(165.5f, 76.1f, 175.2f, 86.8f),
                        PathNode.QuadTo(184.9f, 97.5f, 184.9f, 118.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _redoMedium!!
    }

private var _redoMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Redo: ImageVector
    get() {
        if (_redoDemibold != null) return _redoDemibold!!
        _redoDemibold = ImageVector.Builder(
            name = "Redo.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(191.5f, 118.4f),
                        PathNode.LineTo(191.5f, 205.4f),
                        PathNode.QuadTo(191.5f, 271.0f, 192.5f, 327.3f),
                        PathNode.QuadTo(193.5f, 383.5f, 196.5f, 420.1f),
                        PathNode.QuadTo(202.5f, 493.2f, 220.7f, 531.1f),
                        PathNode.QuadTo(240.8f, 570.3f, 271.4f, 600.7f),
                        PathNode.QuadTo(302.1f, 631.1f, 340.8f, 650.2f),
                        PathNode.QuadTo(360.9f, 660.7f, 386.8f, 666.5f),
                        PathNode.QuadTo(412.7f, 672.4f, 451.8f, 675.4f),
                        PathNode.QuadTo(496.9f, 679.4f, 626.4f, 679.4f),
                        PathNode.LineTo(666.4f, 679.4f),
                        PathNode.LineTo(871.8f, 678.4f),
                        PathNode.LineTo(700.9f, 508.9f),
                        PathNode.QuadTo(676.4f, 484.4f, 676.6f, 466.8f),
                        PathNode.QuadTo(676.8f, 449.1f, 697.9f, 428.0f),
                        PathNode.LineTo(704.9f, 421.0f),
                        PathNode.QuadTo(726.9f, 399.4f, 743.8f, 400.1f),
                        PathNode.QuadTo(760.8f, 400.8f, 784.9f, 425.0f),
                        PathNode.LineTo(1050.9f, 692.0f),
                        PathNode.QuadTo(1069.5f, 711.0f, 1069.5f, 737.4f),
                        PathNode.QuadTo(1069.5f, 763.9f, 1050.9f, 782.9f),
                        PathNode.LineTo(778.9f, 1054.9f),
                        PathNode.QuadTo(760.9f, 1073.0f, 743.4f, 1073.5f),
                        PathNode.QuadTo(726.0f, 1074.0f, 708.9f, 1056.9f),
                        PathNode.LineTo(693.9f, 1041.9f),
                        PathNode.QuadTo(676.9f, 1024.9f, 676.8f, 1007.2f),
                        PathNode.QuadTo(676.8f, 989.5f, 695.4f, 972.0f),
                        PathNode.LineTo(871.4f, 796.5f),
                        PathNode.LineTo(664.4f, 797.5f),
                        PathNode.QuadTo(501.0f, 797.5f, 442.1f, 793.5f),
                        PathNode.QuadTo(394.1f, 789.5f, 356.8f, 781.1f),
                        PathNode.QuadTo(319.5f, 772.6f, 288.1f, 755.7f),
                        PathNode.QuadTo(232.8f, 727.8f, 188.9f, 683.4f),
                        PathNode.QuadTo(145.1f, 639.1f, 117.2f, 583.8f),
                        PathNode.QuadTo(100.7f, 552.4f, 91.5f, 514.3f),
                        PathNode.QuadTo(82.3f, 476.3f, 78.3f, 429.8f),
                        PathNode.QuadTo(75.3f, 390.4f, 74.3f, 331.6f),
                        PathNode.QuadTo(73.3f, 272.9f, 73.3f, 207.4f),
                        PathNode.LineTo(73.3f, 118.4f),
                        PathNode.QuadTo(73.3f, 94.5f, 84.9f, 82.0f),
                        PathNode.QuadTo(96.5f, 69.4f, 119.4f, 69.4f),
                        PathNode.LineTo(143.4f, 69.4f),
                        PathNode.QuadTo(168.3f, 69.4f, 179.9f, 82.0f),
                        PathNode.QuadTo(191.5f, 94.5f, 191.5f, 118.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _redoDemibold!!
    }

private var _redoDemibold: ImageVector? = null
