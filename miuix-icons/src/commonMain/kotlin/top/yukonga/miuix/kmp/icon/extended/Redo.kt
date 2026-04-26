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

val MiuixIcons.Redo: ImageVector
    get() = MiuixIcons.Regular.Redo

val MiuixIcons.Light.Redo: ImageVector
    get() {
        if (_redoLight != null) return _redoLight!!
        _redoLight = ImageVector.Builder(
            name = "Redo.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1146.0f,
            viewportHeight = 1146.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1146.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(104.5f, 117.5f),
                        PathNode.VerticalTo(214.5f),
                        PathNode.QuadTo(104.5f, 377.5f, 109.5f, 434.5f),
                        PathNode.QuadTo(113.5f, 480.5f, 121.5f, 515.0f),
                        PathNode.QuadTo(129.5f, 549.5f, 145.5f, 577.5f),
                        PathNode.QuadTo(170.5f, 629.5f, 211.0f, 670.0f),
                        PathNode.QuadTo(251.5f, 710.5f, 303.5f, 736.5f),
                        PathNode.QuadTo(332.5f, 751.5f, 366.5f, 759.5f),
                        PathNode.QuadTo(400.5f, 767.5f, 446.5f, 771.5f),
                        PathNode.QuadTo(503.5f, 775.5f, 666.5f, 775.5f),
                        PathNode.LineTo(944.5f, 774.5f),
                        PathNode.LineTo(717.5f, 1000.5f),
                        PathNode.QuadTo(708.5f, 1008.5f, 709.0f, 1015.5f),
                        PathNode.QuadTo(709.5f, 1022.5f, 717.5f, 1030.5f),
                        PathNode.LineTo(729.5f, 1042.5f),
                        PathNode.QuadTo(737.5f, 1050.5f, 744.5f, 1050.0f),
                        PathNode.QuadTo(751.5f, 1049.5f, 759.5f, 1041.5f),
                        PathNode.LineTo(1029.5f, 771.5f),
                        PathNode.QuadTo(1041.5f, 759.5f, 1041.5f, 744.5f),
                        PathNode.QuadTo(1041.5f, 729.5f, 1029.5f, 717.5f),
                        PathNode.LineTo(763.5f, 449.5f),
                        PathNode.QuadTo(751.5f, 437.5f, 744.0f, 437.5f),
                        PathNode.QuadTo(736.5f, 437.5f, 726.5f, 447.5f),
                        PathNode.LineTo(719.5f, 455.5f),
                        PathNode.QuadTo(708.5f, 465.5f, 709.0f, 473.0f),
                        PathNode.QuadTo(709.5f, 480.5f, 721.5f, 492.5f),
                        PathNode.LineTo(944.5f, 714.5f),
                        PathNode.LineTo(668.5f, 715.5f),
                        PathNode.HorizontalTo(628.5f),
                        PathNode.QuadTo(498.5f, 715.5f, 451.5f, 711.5f),
                        PathNode.QuadTo(410.5f, 708.5f, 381.5f, 702.0f),
                        PathNode.QuadTo(352.5f, 695.5f, 329.5f, 683.5f),
                        PathNode.QuadTo(287.5f, 662.5f, 253.5f, 628.5f),
                        PathNode.QuadTo(219.5f, 594.5f, 196.5f, 551.5f),
                        PathNode.QuadTo(175.5f, 503.5f, 169.5f, 429.5f),
                        PathNode.QuadTo(166.5f, 392.5f, 165.5f, 335.5f),
                        PathNode.QuadTo(164.5f, 278.5f, 164.5f, 212.5f),
                        PathNode.VerticalTo(117.5f),
                        PathNode.QuadTo(164.5f, 106.5f, 159.5f, 101.0f),
                        PathNode.QuadTo(154.5f, 95.5f, 143.5f, 95.5f),
                        PathNode.HorizontalTo(124.5f),
                        PathNode.QuadTo(114.5f, 95.5f, 109.5f, 101.0f),
                        PathNode.QuadTo(104.5f, 106.5f, 104.5f, 117.5f),
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

val MiuixIcons.Normal.Redo: ImageVector
    get() {
        if (_redoNormal != null) return _redoNormal!!
        _redoNormal = ImageVector.Builder(
            name = "Redo.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1160.0f,
            viewportHeight = 1160.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1160.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(102.2f, 126.2f),
                        PathNode.VerticalTo(217.7f),
                        PathNode.QuadTo(102.2f, 380.7f, 107.2f, 438.4f),
                        PathNode.QuadTo(111.2f, 484.4f, 119.6f, 519.9f),
                        PathNode.QuadTo(127.9f, 555.5f, 143.9f, 584.9f),
                        PathNode.QuadTo(170.3f, 637.5f, 211.8f, 679.4f),
                        PathNode.QuadTo(253.4f, 721.3f, 306.0f, 748.0f),
                        PathNode.QuadTo(335.7f, 763.7f, 370.8f, 771.7f),
                        PathNode.QuadTo(405.8f, 779.7f, 452.5f, 783.7f),
                        PathNode.QuadTo(510.2f, 787.7f, 673.2f, 787.7f),
                        PathNode.LineTo(929.2f, 786.7f),
                        PathNode.LineTo(718.0f, 996.9f),
                        PathNode.QuadTo(705.5f, 1008.3f, 706.0f, 1018.0f),
                        PathNode.QuadTo(706.5f, 1027.8f, 717.3f, 1038.5f),
                        PathNode.LineTo(731.4f, 1052.6f),
                        PathNode.QuadTo(742.1f, 1063.4f, 751.9f, 1062.9f),
                        PathNode.QuadTo(761.6f, 1062.4f, 773.0f, 1050.9f),
                        PathNode.LineTo(1044.4f, 779.5f),
                        PathNode.QuadTo(1057.8f, 766.2f, 1057.8f, 747.7f),
                        PathNode.QuadTo(1057.8f, 729.3f, 1044.4f, 715.9f),
                        PathNode.LineTo(778.4f, 448.6f),
                        PathNode.QuadTo(760.9f, 431.1f, 751.4f, 431.1f),
                        PathNode.QuadTo(741.8f, 431.1f, 727.7f, 445.2f),
                        PathNode.LineTo(720.7f, 452.5f),
                        PathNode.QuadTo(705.5f, 467.4f, 706.0f, 477.3f),
                        PathNode.QuadTo(706.5f, 487.2f, 723.4f, 504.0f),
                        PathNode.LineTo(929.2f, 708.8f),
                        PathNode.LineTo(675.2f, 709.8f),
                        PathNode.HorizontalTo(635.2f),
                        PathNode.QuadTo(505.2f, 709.8f, 458.9f, 705.8f),
                        PathNode.QuadTo(418.5f, 702.8f, 390.6f, 696.3f),
                        PathNode.QuadTo(362.6f, 689.8f, 340.3f, 678.5f),
                        PathNode.QuadTo(299.0f, 658.2f, 266.0f, 625.2f),
                        PathNode.QuadTo(233.0f, 592.2f, 211.4f, 550.6f),
                        PathNode.QuadTo(191.1f, 506.7f, 185.1f, 432.0f),
                        PathNode.QuadTo(182.1f, 395.0f, 181.1f, 338.4f),
                        PathNode.QuadTo(180.1f, 281.7f, 180.1f, 215.7f),
                        PathNode.VerticalTo(126.2f),
                        PathNode.QuadTo(180.1f, 111.1f, 173.7f, 103.9f),
                        PathNode.QuadTo(167.4f, 96.7f, 151.5f, 96.7f),
                        PathNode.HorizontalTo(129.1f),
                        PathNode.QuadTo(115.0f, 96.7f, 108.6f, 103.9f),
                        PathNode.QuadTo(102.2f, 111.1f, 102.2f, 126.2f),
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

val MiuixIcons.Regular.Redo: ImageVector
    get() {
        if (_redoRegular != null) return _redoRegular!!
        _redoRegular = ImageVector.Builder(
            name = "Redo.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1166.4f,
            viewportHeight = 1166.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1166.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(101.2f, 130.2f),
                        PathNode.VerticalTo(219.2f),
                        PathNode.QuadTo(101.2f, 382.2f, 106.2f, 440.2f),
                        PathNode.QuadTo(110.2f, 486.2f, 118.7f, 522.2f),
                        PathNode.QuadTo(127.2f, 558.2f, 143.2f, 588.2f),
                        PathNode.QuadTo(170.2f, 641.2f, 212.2f, 683.7f),
                        PathNode.QuadTo(254.2f, 726.2f, 307.2f, 753.2f),
                        PathNode.QuadTo(337.2f, 769.2f, 372.7f, 777.2f),
                        PathNode.QuadTo(408.2f, 785.2f, 455.2f, 789.2f),
                        PathNode.QuadTo(513.2f, 793.2f, 676.2f, 793.2f),
                        PathNode.LineTo(922.2f, 792.2f),
                        PathNode.LineTo(718.2f, 995.2f),
                        PathNode.QuadTo(704.2f, 1008.2f, 704.7f, 1019.2f),
                        PathNode.QuadTo(705.2f, 1030.2f, 717.2f, 1042.2f),
                        PathNode.LineTo(732.2f, 1057.2f),
                        PathNode.QuadTo(744.2f, 1069.2f, 755.2f, 1068.7f),
                        PathNode.QuadTo(766.2f, 1068.2f, 779.2f, 1055.2f),
                        PathNode.LineTo(1051.2f, 783.2f),
                        PathNode.QuadTo(1065.2f, 769.2f, 1065.2f, 749.2f),
                        PathNode.QuadTo(1065.2f, 729.2f, 1051.2f, 715.2f),
                        PathNode.LineTo(785.2f, 448.2f),
                        PathNode.QuadTo(765.2f, 428.2f, 754.7f, 428.2f),
                        PathNode.QuadTo(744.2f, 428.2f, 728.2f, 444.2f),
                        PathNode.LineTo(721.2f, 451.2f),
                        PathNode.QuadTo(704.2f, 468.2f, 704.7f, 479.2f),
                        PathNode.QuadTo(705.2f, 490.2f, 724.2f, 509.2f),
                        PathNode.LineTo(922.2f, 706.2f),
                        PathNode.LineTo(678.2f, 707.2f),
                        PathNode.HorizontalTo(638.2f),
                        PathNode.QuadTo(508.2f, 707.2f, 462.2f, 703.2f),
                        PathNode.QuadTo(422.2f, 700.2f, 394.7f, 693.7f),
                        PathNode.QuadTo(367.2f, 687.2f, 345.2f, 676.2f),
                        PathNode.QuadTo(304.2f, 656.2f, 271.7f, 623.7f),
                        PathNode.QuadTo(239.2f, 591.2f, 218.2f, 550.2f),
                        PathNode.QuadTo(198.2f, 508.2f, 192.2f, 433.2f),
                        PathNode.QuadTo(189.2f, 396.2f, 188.2f, 339.7f),
                        PathNode.QuadTo(187.2f, 283.2f, 187.2f, 217.2f),
                        PathNode.VerticalTo(130.2f),
                        PathNode.QuadTo(187.2f, 113.2f, 180.2f, 105.2f),
                        PathNode.QuadTo(173.2f, 97.2f, 155.2f, 97.2f),
                        PathNode.HorizontalTo(131.2f),
                        PathNode.QuadTo(115.2f, 97.2f, 108.2f, 105.2f),
                        PathNode.QuadTo(101.2f, 113.2f, 101.2f, 130.2f),
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

val MiuixIcons.Medium.Redo: ImageVector
    get() {
        if (_redoMedium != null) return _redoMedium!!
        _redoMedium = ImageVector.Builder(
            name = "Redo.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1189.0f,
            viewportHeight = 1189.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1189.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(103.1f, 141.5f),
                        PathNode.VerticalTo(230.5f),
                        PathNode.QuadTo(103.1f, 394.1f, 108.1f, 452.1f),
                        PathNode.QuadTo(112.1f, 498.7f, 120.9f, 535.8f),
                        PathNode.QuadTo(129.7f, 573.0f, 146.3f, 603.6f),
                        PathNode.QuadTo(173.8f, 658.4f, 217.0f, 701.8f),
                        PathNode.QuadTo(260.2f, 745.1f, 314.4f, 772.7f),
                        PathNode.QuadTo(345.0f, 789.3f, 381.3f, 797.6f),
                        PathNode.QuadTo(417.7f, 805.9f, 465.9f, 809.9f),
                        PathNode.QuadTo(524.5f, 813.9f, 687.5f, 813.9f),
                        PathNode.LineTo(910.6f, 812.9f),
                        PathNode.LineTo(723.0f, 1000.0f),
                        PathNode.QuadTo(706.7f, 1015.4f, 706.9f, 1030.2f),
                        PathNode.QuadTo(707.1f, 1045.0f, 722.0f, 1060.0f),
                        PathNode.LineTo(737.0f, 1075.0f),
                        PathNode.QuadTo(752.0f, 1089.9f, 766.5f, 1089.7f),
                        PathNode.QuadTo(781.0f, 1089.5f, 797.0f, 1073.0f),
                        PathNode.LineTo(1069.0f, 801.0f),
                        PathNode.QuadTo(1085.9f, 784.0f, 1085.9f, 760.5f),
                        PathNode.QuadTo(1085.9f, 737.0f, 1069.0f, 720.0f),
                        PathNode.LineTo(803.0f, 453.0f),
                        PathNode.QuadTo(780.6f, 430.7f, 766.6f, 430.1f),
                        PathNode.QuadTo(752.6f, 429.5f, 733.0f, 449.0f),
                        PathNode.LineTo(726.0f, 456.0f),
                        PathNode.QuadTo(706.1f, 475.4f, 706.3f, 490.2f),
                        PathNode.QuadTo(706.5f, 505.0f, 729.0f, 527.0f),
                        PathNode.LineTo(910.6f, 708.1f),
                        PathNode.LineTo(689.5f, 709.1f),
                        PathNode.HorizontalTo(649.5f),
                        PathNode.QuadTo(519.5f, 709.1f, 474.1f, 705.1f),
                        PathNode.QuadTo(434.7f, 702.1f, 408.3f, 695.9f),
                        PathNode.QuadTo(382.0f, 689.7f, 360.6f, 679.3f),
                        PathNode.QuadTo(321.4f, 659.8f, 290.1f, 628.5f),
                        PathNode.QuadTo(258.7f, 597.2f, 237.7f, 557.4f),
                        PathNode.QuadTo(218.9f, 517.7f, 212.9f, 443.9f),
                        PathNode.QuadTo(209.9f, 406.9f, 208.9f, 350.7f),
                        PathNode.QuadTo(207.9f, 294.5f, 207.9f, 228.5f),
                        PathNode.VerticalTo(141.5f),
                        PathNode.QuadTo(207.9f, 120.4f, 198.3f, 109.7f),
                        PathNode.QuadTo(188.6f, 99.1f, 166.5f, 99.1f),
                        PathNode.HorizontalTo(142.5f),
                        PathNode.QuadTo(123.0f, 99.1f, 113.0f, 110.0f),
                        PathNode.QuadTo(103.1f, 121.0f, 103.1f, 141.5f),
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

val MiuixIcons.Demibold.Redo: ImageVector
    get() {
        if (_redoDemibold != null) return _redoDemibold!!
        _redoDemibold = ImageVector.Builder(
            name = "Redo.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1204.8f,
            viewportHeight = 1204.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1204.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(104.4f, 149.4f),
                        PathNode.VerticalTo(238.4f),
                        PathNode.QuadTo(104.4f, 402.4f, 109.4f, 460.4f),
                        PathNode.QuadTo(113.4f, 507.4f, 122.4f, 545.4f),
                        PathNode.QuadTo(131.4f, 583.4f, 148.4f, 614.4f),
                        PathNode.QuadTo(176.4f, 670.4f, 220.4f, 714.4f),
                        PathNode.QuadTo(264.4f, 758.4f, 319.4f, 786.4f),
                        PathNode.QuadTo(350.4f, 803.4f, 387.4f, 811.9f),
                        PathNode.QuadTo(424.4f, 820.4f, 473.4f, 824.4f),
                        PathNode.QuadTo(532.4f, 828.4f, 695.4f, 828.4f),
                        PathNode.LineTo(902.4f, 827.4f),
                        PathNode.LineTo(726.4f, 1003.4f),
                        PathNode.QuadTo(708.4f, 1020.4f, 708.4f, 1037.9f),
                        PathNode.QuadTo(708.4f, 1055.4f, 725.4f, 1072.4f),
                        PathNode.LineTo(740.4f, 1087.4f),
                        PathNode.QuadTo(757.4f, 1104.4f, 774.4f, 1104.4f),
                        PathNode.QuadTo(791.4f, 1104.4f, 809.4f, 1085.4f),
                        PathNode.LineTo(1081.4f, 813.4f),
                        PathNode.QuadTo(1100.4f, 794.4f, 1100.4f, 768.4f),
                        PathNode.QuadTo(1100.4f, 742.4f, 1081.4f, 723.4f),
                        PathNode.LineTo(815.4f, 456.4f),
                        PathNode.QuadTo(791.4f, 432.4f, 774.9f, 431.4f),
                        PathNode.QuadTo(758.4f, 430.4f, 736.4f, 452.4f),
                        PathNode.LineTo(729.4f, 459.4f),
                        PathNode.QuadTo(707.4f, 480.4f, 707.4f, 497.9f),
                        PathNode.QuadTo(707.4f, 515.4f, 732.4f, 539.4f),
                        PathNode.LineTo(902.4f, 709.4f),
                        PathNode.LineTo(697.4f, 710.4f),
                        PathNode.HorizontalTo(657.4f),
                        PathNode.QuadTo(527.4f, 710.4f, 482.4f, 706.4f),
                        PathNode.QuadTo(443.4f, 703.4f, 417.9f, 697.4f),
                        PathNode.QuadTo(392.4f, 691.4f, 371.4f, 681.4f),
                        PathNode.QuadTo(333.4f, 662.4f, 302.9f, 631.9f),
                        PathNode.QuadTo(272.4f, 601.4f, 251.4f, 562.4f),
                        PathNode.QuadTo(233.4f, 524.4f, 227.4f, 451.4f),
                        PathNode.QuadTo(224.4f, 414.4f, 223.4f, 358.4f),
                        PathNode.QuadTo(222.4f, 302.4f, 222.4f, 236.4f),
                        PathNode.VerticalTo(149.4f),
                        PathNode.QuadTo(222.4f, 125.4f, 210.9f, 112.9f),
                        PathNode.QuadTo(199.4f, 100.4f, 174.4f, 100.4f),
                        PathNode.HorizontalTo(150.4f),
                        PathNode.QuadTo(128.4f, 100.4f, 116.4f, 113.4f),
                        PathNode.QuadTo(104.4f, 126.4f, 104.4f, 149.4f),
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
