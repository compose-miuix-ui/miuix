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

val MiuixIcons.Glass.Wallpaper: ImageVector
    get() = MiuixIcons.Glass.Regular.Wallpaper

val MiuixIcons.Glass.Light.Wallpaper: ImageVector
    get() {
        if (_wallpaperSurfaceLight != null) return _wallpaperSurfaceLight!!
        _wallpaperSurfaceLight = buildHyperOSIcon(
            name = "Wallpaper.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(225.3f, 637.1f),
                PathNode.VerticalTo(347.6f),
                PathNode.QuadTo(225.3f, 228.3f, 284.4f, 127.7f),
                PathNode.QuadTo(343.6f, 27.1f, 444.4f, -32f),
                PathNode.QuadTo(545.3f, -91.1f, 665f, -91.1f),
                PathNode.QuadTo(717f, -91.1f, 778f, -77f),
                PathNode.QuadTo(811.3f, 17.7f, 812.2f, 123.8f),
                PathNode.QuadTo(813.1f, 269.3f, 746.7f, 395.7f),
                PathNode.QuadTo(680.3f, 522.1f, 564.4f, 605.8f),
                PathNode.QuadTo(448.6f, 689.4f, 306.9f, 710.9f),
                PathNode.QuadTo(271.3f, 716.6f, 248.3f, 695.3f),
                PathNode.QuadTo(225.3f, 674.1f, 225.3f, 637.1f),
                PathNode.Close,
                PathNode.MoveTo(700.1f, 548.8f),
                PathNode.QuadTo(779.1f, 460.4f, 821.7f, 348.1f),
                PathNode.QuadTo(864.3f, 235.8f, 862.1f, 112.3f),
                PathNode.QuadTo(860.8f, 23.9f, 836.4f, -59.2f),
                PathNode.QuadTo(914.4f, -26.1f, 975.4f, 34.7f),
                PathNode.QuadTo(1036.3f, 95.4f, 1070.3f, 175.7f),
                PathNode.QuadTo(1104.2f, 256f, 1104.2f, 344.6f),
                PathNode.VerticalTo(634.2f),
                PathNode.QuadTo(1104.2f, 670.7f, 1081.2f, 692.1f),
                PathNode.QuadTo(1058.2f, 713.6f, 1022.7f, 707.9f),
                PathNode.QuadTo(930.6f, 693.8f, 848.2f, 652.7f),
                PathNode.QuadTo(765.8f, 611.7f, 700.1f, 548.8f),
                PathNode.Close,
                PathNode.MoveTo(556.3f, 765.6f),
                PathNode.LineTo(526.8f, 737.4f),
                PathNode.QuadTo(514.3f, 726.4f, 516.1f, 712.9f),
                PathNode.QuadTo(517.9f, 699.3f, 531.9f, 691.3f),
                PathNode.QuadTo(605.8f, 650.6f, 664.4f, 592.2f),
                PathNode.QuadTo(730.3f, 654f, 800f, 693.3f),
                PathNode.QuadTo(814f, 701.3f, 816.1f, 715.1f),
                PathNode.QuadTo(818.1f, 728.9f, 805.7f, 739.9f),
                PathNode.LineTo(786.1f, 757.7f),
                PathNode.QuadTo(730.4f, 809.3f, 699.6f, 829.7f),
                PathNode.QuadTo(684.3f, 839.3f, 666.7f, 839.3f),
                PathNode.QuadTo(649.1f, 839.3f, 633.9f, 829.7f),
                PathNode.QuadTo(602.6f, 809.8f, 556.3f, 765.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.8889f,
            translationY = 1040.7777f,
            autoMirror = false,
        )
        return _wallpaperSurfaceLight!!
    }

private var _wallpaperSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Wallpaper: ImageVector
    get() {
        if (_wallpaperSurfaceNormal != null) return _wallpaperSurfaceNormal!!
        _wallpaperSurfaceNormal = buildHyperOSIcon(
            name = "Wallpaper.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(221.7f, 637.7f),
                PathNode.VerticalTo(347.9f),
                PathNode.QuadTo(221.7f, 227.4f, 281.4f, 126.2f),
                PathNode.QuadTo(341.1f, 25f, 442.8f, -34.7f),
                PathNode.QuadTo(544.4f, -94.5f, 665f, -94.5f),
                PathNode.QuadTo(714.3f, -94.5f, 767f, -82.5f),
                PathNode.QuadTo(804.9f, 15.8f, 805.2f, 125.3f),
                PathNode.QuadTo(805.5f, 271.2f, 738.3f, 396.9f),
                PathNode.QuadTo(671.2f, 522.7f, 554.5f, 604.6f),
                PathNode.QuadTo(437.9f, 686.4f, 295.3f, 704.8f),
                PathNode.QuadTo(264.9f, 708.6f, 243.3f, 688.9f),
                PathNode.QuadTo(221.7f, 669.2f, 221.7f, 637.7f),
                PathNode.Close,
                PathNode.MoveTo(709f, 547.6f),
                PathNode.QuadTo(785.2f, 460.1f, 827.1f, 350.1f),
                PathNode.QuadTo(868.9f, 240.1f, 868.2f, 119.7f),
                PathNode.QuadTo(867.8f, 28.8f, 841.6f, -60.4f),
                PathNode.QuadTo(919.6f, -26.7f, 980.3f, 34.2f),
                PathNode.QuadTo(1040.9f, 95.1f, 1074.6f, 175.6f),
                PathNode.QuadTo(1108.2f, 256f, 1108.2f, 344.9f),
                PathNode.VerticalTo(635.4f),
                PathNode.QuadTo(1108.2f, 666.1f, 1086.6f, 685.8f),
                PathNode.QuadTo(1064.9f, 705.6f, 1034.6f, 701.8f),
                PathNode.QuadTo(941.9f, 689.8f, 858.7f, 649.8f),
                PathNode.QuadTo(775.6f, 609.8f, 709f, 547.6f),
                PathNode.Close,
                PathNode.MoveTo(558.2f, 774.1f),
                PathNode.LineTo(531.1f, 748.1f),
                PathNode.QuadTo(518.9f, 737.1f, 520.8f, 721.9f),
                PathNode.QuadTo(522.8f, 706.7f, 536.8f, 698.7f),
                PathNode.QuadTo(607.3f, 659.1f, 664.1f, 604.4f),
                PathNode.QuadTo(726.7f, 662.2f, 794.5f, 700.7f),
                PathNode.QuadTo(808.5f, 708.7f, 810.9f, 724f),
                PathNode.QuadTo(813.2f, 739.3f, 801.1f, 750.3f),
                PathNode.LineTo(789.5f, 761.3f),
                PathNode.QuadTo(732.9f, 813.9f, 702.6f, 833.3f),
                PathNode.QuadTo(686.2f, 843.9f, 666.6f, 843.9f),
                PathNode.QuadTo(647f, 843.9f, 630.5f, 833.3f),
                PathNode.QuadTo(600.1f, 814.1f, 558.2f, 774.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.7361f,
            translationY = 1041.3888f,
            autoMirror = false,
        )
        return _wallpaperSurfaceNormal!!
    }

private var _wallpaperSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Wallpaper: ImageVector
    get() {
        if (_wallpaperSurfaceRegular != null) return _wallpaperSurfaceRegular!!
        _wallpaperSurfaceRegular = buildHyperOSIcon(
            name = "Wallpaper.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(220f, 638f),
                PathNode.VerticalTo(348f),
                PathNode.QuadTo(220f, 227f, 280f, 125.5f),
                PathNode.QuadTo(340f, 24f, 442f, -36f),
                PathNode.QuadTo(544f, -96f, 665f, -96f),
                PathNode.QuadTo(713f, -96f, 762f, -85f),
                PathNode.QuadTo(802f, 15f, 802f, 126f),
                PathNode.QuadTo(802f, 272f, 734.5f, 397.5f),
                PathNode.QuadTo(667f, 523f, 550f, 604f),
                PathNode.QuadTo(433f, 685f, 290f, 702f),
                PathNode.QuadTo(262f, 705f, 241f, 686f),
                PathNode.QuadTo(220f, 667f, 220f, 638f),
                PathNode.Close,
                PathNode.MoveTo(713f, 547f),
                PathNode.QuadTo(788f, 460f, 829.5f, 351f),
                PathNode.QuadTo(871f, 242f, 871f, 123f),
                PathNode.QuadTo(871f, 31f, 844f, -61f),
                PathNode.QuadTo(922f, -27f, 982.5f, 34f),
                PathNode.QuadTo(1043f, 95f, 1076.5f, 175.5f),
                PathNode.QuadTo(1110f, 256f, 1110f, 345f),
                PathNode.VerticalTo(636f),
                PathNode.QuadTo(1110f, 664f, 1089f, 683f),
                PathNode.QuadTo(1068f, 702f, 1040f, 699f),
                PathNode.QuadTo(947f, 688f, 863.5f, 648.5f),
                PathNode.QuadTo(780f, 609f, 713f, 547f),
                PathNode.Close,
                PathNode.MoveTo(559f, 778f),
                PathNode.LineTo(533f, 753f),
                PathNode.QuadTo(521f, 742f, 523f, 726f),
                PathNode.QuadTo(525f, 710f, 539f, 702f),
                PathNode.QuadTo(608f, 663f, 664f, 610f),
                PathNode.QuadTo(725f, 666f, 792f, 704f),
                PathNode.QuadTo(806f, 712f, 808.5f, 728f),
                PathNode.QuadTo(811f, 744f, 799f, 755f),
                PathNode.LineTo(791f, 763f),
                PathNode.QuadTo(734f, 816f, 704f, 835f),
                PathNode.QuadTo(687f, 846f, 666.5f, 846f),
                PathNode.QuadTo(646f, 846f, 629f, 835f),
                PathNode.QuadTo(599f, 816f, 559f, 778f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _wallpaperSurfaceRegular!!
    }

private var _wallpaperSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Wallpaper: ImageVector
    get() {
        if (_wallpaperSurfaceMedium != null) return _wallpaperSurfaceMedium!!
        _wallpaperSurfaceMedium = buildHyperOSIcon(
            name = "Wallpaper.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(217f, 628.3f),
                PathNode.VerticalTo(348.3f),
                PathNode.QuadTo(217f, 226.7f, 277.4f, 124.4f),
                PathNode.QuadTo(337.8f, 22.1f, 440.5f, -38.3f),
                PathNode.QuadTo(543.2f, -98.7f, 665f, -98.7f),
                PathNode.QuadTo(709.8f, -98.7f, 753.1f, -89.6f),
                PathNode.QuadTo(796.3f, 13.6f, 796.3f, 127.4f),
                PathNode.QuadTo(796.3f, 271.7f, 729.5f, 395.6f),
                PathNode.QuadTo(662.7f, 519.5f, 546.9f, 599.3f),
                PathNode.QuadTo(431.1f, 679.1f, 289.7f, 695.5f),
                PathNode.QuadTo(259f, 698.8f, 238f, 679.4f),
                PathNode.QuadTo(217f, 660f, 217f, 628.3f),
                PathNode.Close,
                PathNode.MoveTo(719.8f, 545.6f),
                PathNode.QuadTo(793.4f, 459.2f, 834.2f, 350.9f),
                PathNode.QuadTo(875.1f, 242.5f, 875.1f, 124.4f),
                PathNode.QuadTo(875.1f, 26.9f, 846.7f, -62.6f),
                PathNode.QuadTo(924.7f, -28.4f, 985.2f, 33.1f),
                PathNode.QuadTo(1045.7f, 94.5f, 1079.3f, 175.2f),
                PathNode.QuadTo(1113f, 256f, 1113f, 345.3f),
                PathNode.VerticalTo(625.7f),
                PathNode.QuadTo(1113f, 656.7f, 1092f, 676.2f),
                PathNode.QuadTo(1071f, 695.8f, 1040.3f, 692.5f),
                PathNode.QuadTo(948.9f, 681.8f, 867.3f, 643.8f),
                PathNode.QuadTo(785.7f, 605.8f, 719.8f, 545.6f),
                PathNode.Close,
                PathNode.MoveTo(558.5f, 782.1f),
                PathNode.LineTo(533f, 757.6f),
                PathNode.QuadTo(519.9f, 745.2f, 522.1f, 729.9f),
                PathNode.QuadTo(524.2f, 714.6f, 539.8f, 705.8f),
                PathNode.QuadTo(608.3f, 667.6f, 664f, 615.7f),
                PathNode.QuadTo(723.9f, 670.1f, 791.5f, 708.3f),
                PathNode.QuadTo(806.8f, 717.1f, 809.2f, 732.6f),
                PathNode.QuadTo(811.5f, 748.1f, 798.5f, 759.9f),
                PathNode.LineTo(788.8f, 769.5f),
                PathNode.QuadTo(736.7f, 818.2f, 706.7f, 837.2f),
                PathNode.QuadTo(688.6f, 849f, 666.5f, 849f),
                PathNode.QuadTo(644.4f, 849f, 626.3f, 837.2f),
                PathNode.QuadTo(597.6f, 818.7f, 558.5f, 782.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.8018f,
            autoMirror = false,
        )
        return _wallpaperSurfaceMedium!!
    }

private var _wallpaperSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Wallpaper: ImageVector
    get() {
        if (_wallpaperSurfaceDemibold != null) return _wallpaperSurfaceDemibold!!
        _wallpaperSurfaceDemibold = buildHyperOSIcon(
            name = "Wallpaper.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(214.9f, 621.5f),
                PathNode.VerticalTo(348.5f),
                PathNode.QuadTo(214.9f, 226.5f, 275.6f, 123.7f),
                PathNode.QuadTo(336.3f, 20.8f, 439.5f, -39.9f),
                PathNode.QuadTo(542.6f, -100.6f, 665f, -100.6f),
                PathNode.QuadTo(707.5f, -100.6f, 746.8f, -92.8f),
                PathNode.QuadTo(792.4f, 12.7f, 792.4f, 128.3f),
                PathNode.QuadTo(792.4f, 271.5f, 726f, 394.3f),
                PathNode.QuadTo(659.6f, 517f, 544.7f, 596f),
                PathNode.QuadTo(429.8f, 674.9f, 289.5f, 691f),
                PathNode.QuadTo(256.9f, 694.4f, 235.9f, 674.7f),
                PathNode.QuadTo(214.9f, 655.1f, 214.9f, 621.5f),
                PathNode.Close,
                PathNode.MoveTo(724.5f, 544.7f),
                PathNode.QuadTo(797.2f, 458.6f, 837.5f, 350.8f),
                PathNode.QuadTo(877.9f, 242.9f, 877.9f, 125.3f),
                PathNode.QuadTo(877.9f, 24.1f, 848.6f, -63.8f),
                PathNode.QuadTo(926.6f, -29.3f, 987.1f, 32.4f),
                PathNode.QuadTo(1047.6f, 94.1f, 1081.3f, 175f),
                PathNode.QuadTo(1115.1f, 256f, 1115.1f, 345.5f),
                PathNode.VerticalTo(618.5f),
                PathNode.QuadTo(1115.1f, 651.6f, 1094.1f, 671.5f),
                PathNode.QuadTo(1073.1f, 691.4f, 1040.5f, 688f),
                PathNode.QuadTo(950.2f, 677.4f, 869.9f, 640.5f),
                PathNode.QuadTo(789.6f, 603.5f, 724.5f, 544.7f),
                PathNode.Close,
                PathNode.MoveTo(558.1f, 784.9f),
                PathNode.LineTo(533f, 760.8f),
                PathNode.QuadTo(519.2f, 747.5f, 521.4f, 732.7f),
                PathNode.QuadTo(523.6f, 717.8f, 540.4f, 708.4f),
                PathNode.QuadTo(608.5f, 670.8f, 664f, 619.6f),
                PathNode.QuadTo(723.2f, 672.9f, 791.1f, 711.4f),
                PathNode.QuadTo(807.4f, 720.7f, 809.6f, 735.8f),
                PathNode.QuadTo(811.9f, 750.9f, 798.1f, 763.3f),
                PathNode.LineTo(787.3f, 774f),
                PathNode.QuadTo(738.6f, 819.7f, 708.6f, 838.7f),
                PathNode.QuadTo(689.8f, 851.1f, 666.5f, 851.1f),
                PathNode.QuadTo(643.2f, 851.1f, 624.4f, 838.7f),
                PathNode.QuadTo(596.7f, 820.6f, 558.1f, 784.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.8964f,
            autoMirror = false,
        )
        return _wallpaperSurfaceDemibold!!
    }

private var _wallpaperSurfaceDemibold: ImageVector? = null

