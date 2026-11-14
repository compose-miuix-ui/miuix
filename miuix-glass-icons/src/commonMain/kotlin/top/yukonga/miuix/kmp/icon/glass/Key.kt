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

val MiuixIcons.Glass.Key: ImageVector
    get() = MiuixIcons.Glass.Regular.Key

val MiuixIcons.Glass.Light.Key: ImageVector
    get() {
        if (_keySurfaceLight != null) return _keySurfaceLight!!
        _keySurfaceLight = buildHyperOSIcon(
            name = "Key.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(700.8f, -214.8f),
                PathNode.LineTo(787.9f, -96.7f),
                PathNode.QuadTo(791.6f, -91.3f, 791.8f, -84.8f),
                PathNode.QuadTo(792.1f, -78.2f, 789.4f, -72.4f),
                PathNode.LineTo(739.9f, 8.8f),
                PathNode.QuadTo(735.8f, 16.3f, 739.9f, 23f),
                PathNode.LineTo(801.9f, 110.3f),
                PathNode.QuadTo(806.1f, 116.1f, 805.6f, 123f),
                PathNode.QuadTo(805.1f, 129.9f, 800.9f, 135.7f),
                PathNode.LineTo(735.6f, 223.8f),
                PathNode.QuadTo(730.8f, 230.3f, 730.8f, 239f),
                PathNode.VerticalTo(303.3f),
                PathNode.QuadTo(730.8f, 309.6f, 733.1f, 312.1f),
                PathNode.QuadTo(735.3f, 314.7f, 741f, 316.2f),
                PathNode.QuadTo(802.1f, 332.9f, 850.9f, 374.1f),
                PathNode.QuadTo(899.7f, 415.2f, 927.4f, 473.3f),
                PathNode.QuadTo(955.1f, 531.4f, 955.1f, 597f),
                PathNode.QuadTo(955.1f, 675.8f, 915.9f, 742.3f),
                PathNode.QuadTo(876.8f, 808.8f, 810.3f, 847.9f),
                PathNode.QuadTo(743.8f, 887.1f, 665f, 887.1f),
                PathNode.QuadTo(586.2f, 887.1f, 519.7f, 847.9f),
                PathNode.QuadTo(453.2f, 808.8f, 414.1f, 742.3f),
                PathNode.QuadTo(374.9f, 675.8f, 374.9f, 597f),
                PathNode.QuadTo(374.9f, 534.4f, 400.1f, 478.6f),
                PathNode.QuadTo(425.3f, 422.8f, 470.6f, 382.1f),
                PathNode.QuadTo(515.9f, 341.4f, 573.4f, 322.2f),
                PathNode.QuadTo(579.1f, 320.1f, 581.1f, 317.3f),
                PathNode.QuadTo(583.1f, 314.6f, 583.1f, 308.9f),
                PathNode.LineTo(584.9f, -115f),
                PathNode.QuadTo(584.9f, -125.4f, 592.2f, -132.8f),
                PathNode.LineTo(670.2f, -214.3f),
                PathNode.QuadTo(677.6f, -221.7f, 686.3f, -221.9f),
                PathNode.QuadTo(695f, -222.1f, 700.8f, -214.8f),
                PathNode.Close,
                PathNode.MoveTo(589.4f, 707f),
                PathNode.QuadTo(589.4f, 738.2f, 611.8f, 760.4f),
                PathNode.QuadTo(634.2f, 782.6f, 665f, 782.6f),
                PathNode.QuadTo(696.2f, 782.6f, 718.4f, 760.4f),
                PathNode.QuadTo(740.6f, 738.2f, 740.6f, 707f),
                PathNode.QuadTo(740.6f, 676.2f, 718.4f, 653.8f),
                PathNode.QuadTo(696.2f, 631.4f, 665f, 631.4f),
                PathNode.QuadTo(634.2f, 631.4f, 611.8f, 653.8f),
                PathNode.QuadTo(589.4f, 676.2f, 589.4f, 707f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 999.2745f,
            autoMirror = false,
        )
        return _keySurfaceLight!!
    }

private var _keySurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Key: ImageVector
    get() {
        if (_keySurfaceNormal != null) return _keySurfaceNormal!!
        _keySurfaceNormal = buildHyperOSIcon(
            name = "Key.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(705.1f, -219.1f),
                PathNode.LineTo(792.8f, -100.3f),
                PathNode.QuadTo(797.4f, -93.2f, 797.8f, -84.9f),
                PathNode.QuadTo(798.2f, -76.7f, 794.6f, -69.4f),
                PathNode.LineTo(747.5f, 7.6f),
                PathNode.QuadTo(742.8f, 15.4f, 747.5f, 23f),
                PathNode.LineTo(806.8f, 106.7f),
                PathNode.QuadTo(812.2f, 114f, 811.7f, 123f),
                PathNode.QuadTo(811.2f, 132f, 805.8f, 139.3f),
                PathNode.LineTo(738.6f, 228.1f),
                PathNode.QuadTo(735.1f, 232.2f, 735.1f, 239f),
                PathNode.VerticalTo(296.9f),
                PathNode.QuadTo(735.1f, 304.4f, 737.5f, 307.2f),
                PathNode.QuadTo(739.9f, 310.1f, 746.5f, 311.9f),
                PathNode.QuadTo(808.2f, 329.5f, 857.2f, 371.6f),
                PathNode.QuadTo(906.1f, 413.7f, 933.7f, 472.4f),
                PathNode.QuadTo(961.2f, 531.1f, 961.2f, 597f),
                PathNode.QuadTo(961.2f, 677.3f, 921.1f, 745.2f),
                PathNode.QuadTo(881.1f, 813.1f, 813.2f, 853.1f),
                PathNode.QuadTo(745.3f, 893.2f, 665f, 893.2f),
                PathNode.QuadTo(584.7f, 893.2f, 516.8f, 853.1f),
                PathNode.QuadTo(448.9f, 813.1f, 408.9f, 745.2f),
                PathNode.QuadTo(368.8f, 677.3f, 368.8f, 597f),
                PathNode.QuadTo(368.8f, 534.1f, 393.8f, 477.8f),
                PathNode.QuadTo(418.9f, 421.6f, 464.3f, 380f),
                PathNode.QuadTo(509.8f, 338.4f, 567.6f, 317.9f),
                PathNode.QuadTo(574.2f, 315.2f, 576.2f, 312.3f),
                PathNode.QuadTo(578.2f, 309.4f, 578.2f, 302.8f),
                PathNode.LineTo(578.8f, -115f),
                PathNode.QuadTo(578.8f, -127.9f, 587.9f, -137.1f),
                PathNode.LineTo(665.9f, -218.9f),
                PathNode.QuadTo(675.1f, -228.1f, 686.4f, -228.2f),
                PathNode.QuadTo(697.7f, -228.2f, 705.1f, -219.1f),
                PathNode.Close,
                PathNode.MoveTo(586.4f, 707f),
                PathNode.QuadTo(586.4f, 739.4f, 609.5f, 762.5f),
                PathNode.QuadTo(632.7f, 785.6f, 665f, 785.6f),
                PathNode.QuadTo(697.4f, 785.6f, 720.5f, 762.5f),
                PathNode.QuadTo(743.6f, 739.4f, 743.6f, 707f),
                PathNode.QuadTo(743.6f, 674.7f, 720.5f, 651.5f),
                PathNode.QuadTo(697.4f, 628.4f, 665f, 628.4f),
                PathNode.QuadTo(632.7f, 628.4f, 609.5f, 651.5f),
                PathNode.QuadTo(586.4f, 674.7f, 586.4f, 707f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 999.2011f,
            autoMirror = false,
        )
        return _keySurfaceNormal!!
    }

private var _keySurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Key: ImageVector
    get() {
        if (_keySurfaceRegular != null) return _keySurfaceRegular!!
        _keySurfaceRegular = buildHyperOSIcon(
            name = "Key.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(707f, -221f),
                PathNode.LineTo(795f, -102f),
                PathNode.QuadTo(800f, -94f, 800.5f, -85f),
                PathNode.QuadTo(801f, -76f, 797f, -68f),
                PathNode.LineTo(751f, 7f),
                PathNode.QuadTo(746f, 15f, 751f, 23f),
                PathNode.LineTo(809f, 105f),
                PathNode.QuadTo(815f, 113f, 814.5f, 123f),
                PathNode.QuadTo(814f, 133f, 808f, 141f),
                PathNode.LineTo(740f, 230f),
                PathNode.QuadTo(737f, 233f, 737f, 239f),
                PathNode.VerticalTo(294f),
                PathNode.QuadTo(737f, 302f, 739.5f, 305f),
                PathNode.QuadTo(742f, 308f, 749f, 310f),
                PathNode.QuadTo(811f, 328f, 860f, 370.5f),
                PathNode.QuadTo(909f, 413f, 936.5f, 472f),
                PathNode.QuadTo(964f, 531f, 964f, 597f),
                PathNode.QuadTo(964f, 678f, 923.5f, 746.5f),
                PathNode.QuadTo(883f, 815f, 814.5f, 855.5f),
                PathNode.QuadTo(746f, 896f, 665f, 896f),
                PathNode.QuadTo(584f, 896f, 515.5f, 855.5f),
                PathNode.QuadTo(447f, 815f, 406.5f, 746.5f),
                PathNode.QuadTo(366f, 678f, 366f, 597f),
                PathNode.QuadTo(366f, 534f, 391f, 477.5f),
                PathNode.QuadTo(416f, 421f, 461.5f, 379f),
                PathNode.QuadTo(507f, 337f, 565f, 316f),
                PathNode.QuadTo(572f, 313f, 574f, 310f),
                PathNode.QuadTo(576f, 307f, 576f, 300f),
                PathNode.VerticalTo(-115f),
                PathNode.QuadTo(576f, -129f, 586f, -139f),
                PathNode.LineTo(664f, -221f),
                PathNode.QuadTo(674f, -231f, 686.5f, -231f),
                PathNode.QuadTo(699f, -231f, 707f, -221f),
                PathNode.Close,
                PathNode.MoveTo(585f, 707f),
                PathNode.QuadTo(585f, 740f, 608.5f, 763.5f),
                PathNode.QuadTo(632f, 787f, 665f, 787f),
                PathNode.QuadTo(698f, 787f, 721.5f, 763.5f),
                PathNode.QuadTo(745f, 740f, 745f, 707f),
                PathNode.QuadTo(745f, 674f, 721.5f, 650.5f),
                PathNode.QuadTo(698f, 627f, 665f, 627f),
                PathNode.QuadTo(632f, 627f, 608.5f, 650.5f),
                PathNode.QuadTo(585f, 674f, 585f, 707f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 999.1667f,
            autoMirror = false,
        )
        return _keySurfaceRegular!!
    }

private var _keySurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Key: ImageVector
    get() {
        if (_keySurfaceMedium != null) return _keySurfaceMedium!!
        _keySurfaceMedium = buildHyperOSIcon(
            name = "Key.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(712.1f, -226.7f),
                PathNode.LineTo(801.5f, -106.6f),
                PathNode.QuadTo(808.1f, -96.7f, 808.9f, -85.3f),
                PathNode.QuadTo(809.6f, -73.8f, 804f, -63.9f),
                PathNode.LineTo(759.1f, 8.1f),
                PathNode.QuadTo(754.6f, 15.3f, 759.1f, 21.9f),
                PathNode.LineTo(815.5f, 100.4f),
                PathNode.QuadTo(823.1f, 110.6f, 822.6f, 123.3f),
                PathNode.QuadTo(822.1f, 136f, 814.5f, 145.9f),
                PathNode.LineTo(748.1f, 233f),
                PathNode.QuadTo(745.1f, 236.5f, 745.1f, 241.4f),
                PathNode.VerticalTo(289.9f),
                PathNode.QuadTo(745.1f, 296.3f, 747.3f, 299.1f),
                PathNode.QuadTo(749.6f, 301.8f, 755.2f, 303.5f),
                PathNode.QuadTo(817.8f, 322.6f, 867.2f, 366.2f),
                PathNode.QuadTo(916.6f, 409.8f, 944.3f, 469.7f),
                PathNode.QuadTo(972.1f, 529.6f, 972.1f, 597f),
                PathNode.QuadTo(972.1f, 680.2f, 930.5f, 750.6f),
                PathNode.QuadTo(888.9f, 820.9f, 818.6f, 862.5f),
                PathNode.QuadTo(748.2f, 904.1f, 665f, 904.1f),
                PathNode.QuadTo(581.8f, 904.1f, 511.4f, 862.5f),
                PathNode.QuadTo(441.1f, 820.9f, 399.5f, 750.6f),
                PathNode.QuadTo(357.9f, 680.2f, 357.9f, 597f),
                PathNode.QuadTo(357.9f, 532.9f, 383.2f, 475.3f),
                PathNode.QuadTo(408.4f, 417.8f, 454.2f, 374.7f),
                PathNode.QuadTo(500f, 331.6f, 558.5f, 309.8f),
                PathNode.QuadTo(564.4f, 307.3f, 566.2f, 304.6f),
                PathNode.QuadTo(567.9f, 301.9f, 567.9f, 296.2f),
                PathNode.VerticalTo(-115f),
                PathNode.QuadTo(567.9f, -132.2f, 580.3f, -144.7f),
                PathNode.LineTo(658.1f, -226.7f),
                PathNode.QuadTo(670.5f, -239.6f, 686.4f, -239.5f),
                PathNode.QuadTo(702.2f, -239.4f, 712.1f, -226.7f),
                PathNode.Close,
                PathNode.MoveTo(581.2f, 707f),
                PathNode.QuadTo(581.2f, 741.6f, 605.8f, 766.2f),
                PathNode.QuadTo(630.4f, 790.8f, 665f, 790.8f),
                PathNode.QuadTo(699.6f, 790.8f, 724.2f, 766.2f),
                PathNode.QuadTo(748.8f, 741.6f, 748.8f, 707f),
                PathNode.QuadTo(748.8f, 672.4f, 724.2f, 647.8f),
                PathNode.QuadTo(699.6f, 623.2f, 665f, 623.2f),
                PathNode.QuadTo(630.4f, 623.2f, 605.8f, 647.8f),
                PathNode.QuadTo(581.2f, 672.4f, 581.2f, 707f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 998.9633f,
            autoMirror = false,
        )
        return _keySurfaceMedium!!
    }

private var _keySurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Key: ImageVector
    get() {
        if (_keySurfaceDemibold != null) return _keySurfaceDemibold!!
        _keySurfaceDemibold = buildHyperOSIcon(
            name = "Key.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(715.7f, -230.6f),
                PathNode.LineTo(806f, -109.8f),
                PathNode.QuadTo(813.8f, -98.6f, 814.7f, -85.5f),
                PathNode.QuadTo(815.7f, -72.3f, 808.9f, -61.1f),
                PathNode.LineTo(764.8f, 8.8f),
                PathNode.QuadTo(760.7f, 15.5f, 764.8f, 21.2f),
                PathNode.LineTo(820f, 97.2f),
                PathNode.QuadTo(828.8f, 108.9f, 828.3f, 123.5f),
                PathNode.QuadTo(827.8f, 138.1f, 819f, 149.3f),
                PathNode.LineTo(753.8f, 235.1f),
                PathNode.QuadTo(750.8f, 239f, 750.8f, 243.1f),
                PathNode.VerticalTo(287.1f),
                PathNode.QuadTo(750.8f, 292.4f, 752.8f, 294.9f),
                PathNode.QuadTo(754.9f, 297.4f, 759.6f, 299f),
                PathNode.QuadTo(822.5f, 318.8f, 872.2f, 363.1f),
                PathNode.QuadTo(921.9f, 407.5f, 949.8f, 468.1f),
                PathNode.QuadTo(977.8f, 528.7f, 977.8f, 597f),
                PathNode.QuadTo(977.8f, 681.7f, 935.4f, 753.4f),
                PathNode.QuadTo(893.1f, 825.1f, 821.4f, 867.4f),
                PathNode.QuadTo(749.7f, 909.8f, 665f, 909.8f),
                PathNode.QuadTo(580.3f, 909.8f, 508.6f, 867.4f),
                PathNode.QuadTo(436.9f, 825.1f, 394.6f, 753.4f),
                PathNode.QuadTo(352.2f, 681.7f, 352.2f, 597f),
                PathNode.QuadTo(352.2f, 532.2f, 377.7f, 473.8f),
                PathNode.QuadTo(403.1f, 415.5f, 449.1f, 371.6f),
                PathNode.QuadTo(495.1f, 327.8f, 554f, 305.4f),
                PathNode.QuadTo(559.1f, 303.4f, 560.7f, 300.8f),
                PathNode.QuadTo(562.2f, 298.3f, 562.2f, 293.6f),
                PathNode.VerticalTo(-115f),
                PathNode.QuadTo(562.2f, -134.5f, 576.4f, -148.6f),
                PathNode.LineTo(653.9f, -230.6f),
                PathNode.QuadTo(668f, -245.7f, 686.3f, -245.5f),
                PathNode.QuadTo(704.5f, -245.2f, 715.7f, -230.6f),
                PathNode.Close,
                PathNode.MoveTo(578.6f, 707f),
                PathNode.QuadTo(578.6f, 742.8f, 603.9f, 768.1f),
                PathNode.QuadTo(629.2f, 793.4f, 665f, 793.4f),
                PathNode.QuadTo(700.8f, 793.4f, 726.1f, 768.1f),
                PathNode.QuadTo(751.4f, 742.8f, 751.4f, 707f),
                PathNode.QuadTo(751.4f, 671.2f, 726.1f, 645.9f),
                PathNode.QuadTo(700.8f, 620.6f, 665f, 620.6f),
                PathNode.QuadTo(629.2f, 620.6f, 603.9f, 645.9f),
                PathNode.QuadTo(578.6f, 671.2f, 578.6f, 707f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 998.8203f,
            autoMirror = false,
        )
        return _keySurfaceDemibold!!
    }

private var _keySurfaceDemibold: ImageVector? = null

