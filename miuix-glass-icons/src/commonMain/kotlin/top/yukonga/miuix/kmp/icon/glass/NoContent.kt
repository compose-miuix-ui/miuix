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

val MiuixIcons.Glass.NoContent: ImageVector
    get() = MiuixIcons.Glass.Regular.NoContent

val MiuixIcons.Glass.Light.NoContent: ImageVector
    get() {
        if (_noContentSurfaceLight != null) return _noContentSurfaceLight!!
        _noContentSurfaceLight = buildHyperOSIcon(
            name = "NoContent.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(331f, 850f),
                PathNode.QuadTo(293.2f, 831.9f, 273f, 792f),
                PathNode.QuadTo(262.4f, 771.9f, 260.2f, 743.7f),
                PathNode.QuadTo(257.9f, 715.6f, 257.9f, 646f),
                PathNode.VerticalTo(104f),
                PathNode.QuadTo(257.9f, 34.4f, 260.2f, 6.1f),
                PathNode.QuadTo(262.4f, -22.3f, 273f, -42f),
                PathNode.QuadTo(293.2f, -81.9f, 331f, -100f),
                PathNode.QuadTo(351.1f, -110.6f, 379.8f, -112.8f),
                PathNode.QuadTo(408.4f, -115.1f, 477f, -115.1f),
                PathNode.HorizontalTo(853f),
                PathNode.QuadTo(921.6f, -115.1f, 950.2f, -112.8f),
                PathNode.QuadTo(978.9f, -110.6f, 999f, -100f),
                PathNode.QuadTo(1036.8f, -81.9f, 1057f, -42f),
                PathNode.QuadTo(1067.6f, -22.3f, 1069.8f, 6.1f),
                PathNode.QuadTo(1072.1f, 34.4f, 1072.1f, 104f),
                PathNode.VerticalTo(523.3f),
                PathNode.HorizontalTo(736.1f),
                PathNode.QuadTo(725.2f, 523.3f, 718.8f, 529.6f),
                PathNode.QuadTo(712.3f, 535.8f, 712.3f, 546.7f),
                PathNode.VerticalTo(865.1f),
                PathNode.HorizontalTo(477f),
                PathNode.QuadTo(408.4f, 865.1f, 379.8f, 862.8f),
                PathNode.QuadTo(351.1f, 860.6f, 331f, 850f),
                PathNode.Close,
                PathNode.MoveTo(1069.1f, 618.2f),
                PathNode.QuadTo(1067f, 632.1f, 1057.9f, 643.7f),
                PathNode.QuadTo(1050.1f, 653f, 1025.2f, 676.3f),
                PathNode.LineTo(859.8f, 827.3f),
                PathNode.QuadTo(836.8f, 848.8f, 828.6f, 853.4f),
                PathNode.QuadTo(817.4f, 860f, 805.7f, 862.1f),
                PathNode.QuadTo(798.3f, 863.1f, 785.5f, 863.9f),
                PathNode.QuadTo(772.7f, 864.7f, 761.1f, 864.7f),
                PathNode.VerticalTo(580.8f),
                PathNode.QuadTo(761.1f, 572.1f, 769.8f, 572.1f),
                PathNode.HorizontalTo(1071.7f),
                PathNode.QuadTo(1071.7f, 605.4f, 1069.1f, 618.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _noContentSurfaceLight!!
    }

private var _noContentSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.NoContent: ImageVector
    get() {
        if (_noContentSurfaceNormal != null) return _noContentSurfaceNormal!!
        _noContentSurfaceNormal = buildHyperOSIcon(
            name = "NoContent.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(328.3f, 855.5f),
                PathNode.QuadTo(288.9f, 836.8f, 267.5f, 794.7f),
                PathNode.QuadTo(256.6f, 774f, 254.2f, 744.9f),
                PathNode.QuadTo(251.8f, 715.9f, 251.8f, 646f),
                PathNode.VerticalTo(104f),
                PathNode.QuadTo(251.8f, 34.1f, 254.2f, 5f),
                PathNode.QuadTo(256.6f, -24.2f, 267.5f, -44.7f),
                PathNode.QuadTo(288.9f, -86.8f, 328.3f, -105.5f),
                PathNode.QuadTo(349f, -116.4f, 378.6f, -118.8f),
                PathNode.QuadTo(408.1f, -121.2f, 477f, -121.2f),
                PathNode.HorizontalTo(853f),
                PathNode.QuadTo(921.9f, -121.2f, 951.4f, -118.8f),
                PathNode.QuadTo(981f, -116.4f, 1001.7f, -105.5f),
                PathNode.QuadTo(1041.1f, -86.8f, 1062.5f, -44.7f),
                PathNode.QuadTo(1073.4f, -24.2f, 1075.8f, 5f),
                PathNode.QuadTo(1078.2f, 34.1f, 1078.2f, 104f),
                PathNode.VerticalTo(516.9f),
                PathNode.HorizontalTo(734f),
                PathNode.QuadTo(720.9f, 516.9f, 713.4f, 524.4f),
                PathNode.QuadTo(705.9f, 531.8f, 705.9f, 544.8f),
                PathNode.VerticalTo(871.2f),
                PathNode.HorizontalTo(477f),
                PathNode.QuadTo(408.1f, 871.2f, 378.6f, 868.8f),
                PathNode.QuadTo(349f, 866.4f, 328.3f, 855.5f),
                PathNode.Close,
                PathNode.MoveTo(1075.2f, 619.4f),
                PathNode.QuadTo(1072.5f, 635.5f, 1062.8f, 647.3f),
                PathNode.QuadTo(1053.5f, 658.5f, 1029.2f, 680.9f),
                PathNode.LineTo(864.1f, 831.9f),
                PathNode.QuadTo(841.1f, 853.1f, 831.6f, 858.6f),
                PathNode.QuadTo(819.9f, 865.5f, 806.6f, 868.2f),
                PathNode.QuadTo(800.2f, 869.2f, 789.6f, 870.2f),
                PathNode.QuadTo(779.1f, 871.1f, 767.2f, 871.1f),
                PathNode.VerticalTo(587.8f),
                PathNode.QuadTo(767.2f, 578.2f, 776.8f, 578.2f),
                PathNode.HorizontalTo(1078.1f),
                PathNode.QuadTo(1078.1f, 605.1f, 1075.2f, 619.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _noContentSurfaceNormal!!
    }

private var _noContentSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.NoContent: ImageVector
    get() {
        if (_noContentSurfaceRegular != null) return _noContentSurfaceRegular!!
        _noContentSurfaceRegular = buildHyperOSIcon(
            name = "NoContent.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(327f, 858f),
                PathNode.QuadTo(287f, 839f, 265f, 796f),
                PathNode.QuadTo(254f, 775f, 251.5f, 745.5f),
                PathNode.QuadTo(249f, 716f, 249f, 646f),
                PathNode.VerticalTo(104f),
                PathNode.QuadTo(249f, 34f, 251.5f, 4.5f),
                PathNode.QuadTo(254f, -25f, 265f, -46f),
                PathNode.QuadTo(287f, -89f, 327f, -108f),
                PathNode.QuadTo(348f, -119f, 378f, -121.5f),
                PathNode.QuadTo(408f, -124f, 477f, -124f),
                PathNode.HorizontalTo(853f),
                PathNode.QuadTo(922f, -124f, 952f, -121.5f),
                PathNode.QuadTo(982f, -119f, 1003f, -108f),
                PathNode.QuadTo(1043f, -89f, 1065f, -46f),
                PathNode.QuadTo(1076f, -25f, 1078.5f, 4.5f),
                PathNode.QuadTo(1081f, 34f, 1081f, 104f),
                PathNode.VerticalTo(514f),
                PathNode.HorizontalTo(733f),
                PathNode.QuadTo(719f, 514f, 711f, 522f),
                PathNode.QuadTo(703f, 530f, 703f, 544f),
                PathNode.VerticalTo(874f),
                PathNode.HorizontalTo(477f),
                PathNode.QuadTo(408f, 874f, 378f, 871.5f),
                PathNode.QuadTo(348f, 869f, 327f, 858f),
                PathNode.Close,
                PathNode.MoveTo(1078f, 620f),
                PathNode.QuadTo(1075f, 637f, 1065f, 649f),
                PathNode.QuadTo(1055f, 661f, 1031f, 683f),
                PathNode.LineTo(866f, 834f),
                PathNode.QuadTo(843f, 855f, 833f, 861f),
                PathNode.QuadTo(821f, 868f, 807f, 871f),
                PathNode.QuadTo(801f, 872f, 791.5f, 873f),
                PathNode.QuadTo(782f, 874f, 770f, 874f),
                PathNode.VerticalTo(591f),
                PathNode.QuadTo(770f, 581f, 780f, 581f),
                PathNode.HorizontalTo(1081f),
                PathNode.QuadTo(1081f, 605f, 1078f, 620f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _noContentSurfaceRegular!!
    }

private var _noContentSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.NoContent: ImageVector
    get() {
        if (_noContentSurfaceMedium != null) return _noContentSurfaceMedium!!
        _noContentSurfaceMedium = buildHyperOSIcon(
            name = "NoContent.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(324.6f, 862.9f),
                PathNode.QuadTo(283.2f, 843.1f, 260.1f, 798.4f),
                PathNode.QuadTo(248.9f, 776.6f, 246.2f, 746.4f),
                PathNode.QuadTo(243.6f, 716.3f, 243.6f, 646f),
                PathNode.VerticalTo(104f),
                PathNode.QuadTo(243.6f, 33.7f, 246.2f, 3.4f),
                PathNode.QuadTo(248.9f, -26.9f, 260.1f, -48.4f),
                PathNode.QuadTo(283.2f, -93.1f, 324.6f, -112.9f),
                PathNode.QuadTo(346.4f, -124.1f, 377.1f, -126.8f),
                PathNode.QuadTo(407.7f, -129.4f, 477f, -129.4f),
                PathNode.HorizontalTo(853f),
                PathNode.QuadTo(922.5f, -129.4f, 953.1f, -126.8f),
                PathNode.QuadTo(983.6f, -124.1f, 1005.4f, -112.9f),
                PathNode.QuadTo(1046.8f, -93.1f, 1069.9f, -48.4f),
                PathNode.QuadTo(1081.1f, -26.9f, 1083.8f, 3.4f),
                PathNode.QuadTo(1086.4f, 33.7f, 1086.4f, 104f),
                PathNode.VerticalTo(512.4f),
                PathNode.HorizontalTo(729.8f),
                PathNode.QuadTo(715.8f, 512.4f, 707.8f, 520.4f),
                PathNode.QuadTo(699.8f, 528.4f, 699.8f, 542.4f),
                PathNode.VerticalTo(879.4f),
                PathNode.HorizontalTo(477f),
                PathNode.QuadTo(407.7f, 879.4f, 377.1f, 876.8f),
                PathNode.QuadTo(346.4f, 874.1f, 324.6f, 862.9f),
                PathNode.Close,
                PathNode.MoveTo(1083.4f, 621.1f),
                PathNode.QuadTo(1079.6f, 639.7f, 1069.3f, 652.2f),
                PathNode.QuadTo(1058.2f, 665.9f, 1034.5f, 687.1f),
                PathNode.LineTo(869.8f, 838.1f),
                PathNode.QuadTo(847.1f, 859.1f, 835.7f, 865.6f),
                PathNode.QuadTo(822.6f, 873.7f, 807.8f, 876.4f),
                PathNode.QuadTo(800.5f, 877.4f, 791.1f, 878.4f),
                PathNode.QuadTo(781.7f, 879.4f, 771.9f, 879.4f),
                PathNode.VerticalTo(596.1f),
                PathNode.QuadTo(771.9f, 584.5f, 783.5f, 584.5f),
                PathNode.HorizontalTo(1086.4f),
                PathNode.QuadTo(1086.9f, 603.1f, 1083.4f, 621.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6309f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _noContentSurfaceMedium!!
    }

private var _noContentSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.NoContent: ImageVector
    get() {
        if (_noContentSurfaceDemibold != null) return _noContentSurfaceDemibold!!
        _noContentSurfaceDemibold = buildHyperOSIcon(
            name = "NoContent.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(322.9f, 866.3f),
                PathNode.QuadTo(280.6f, 845.9f, 256.7f, 800.1f),
                PathNode.QuadTo(245.3f, 777.8f, 242.5f, 747.1f),
                PathNode.QuadTo(239.8f, 716.5f, 239.8f, 646f),
                PathNode.VerticalTo(104f),
                PathNode.QuadTo(239.8f, 33.5f, 242.5f, 2.7f),
                PathNode.QuadTo(245.3f, -28.2f, 256.7f, -50.1f),
                PathNode.QuadTo(280.6f, -95.9f, 322.9f, -116.3f),
                PathNode.QuadTo(345.2f, -127.7f, 376.4f, -130.5f),
                PathNode.QuadTo(407.5f, -133.2f, 477f, -133.2f),
                PathNode.HorizontalTo(853f),
                PathNode.QuadTo(922.9f, -133.2f, 953.8f, -130.5f),
                PathNode.QuadTo(984.8f, -127.7f, 1007.1f, -116.3f),
                PathNode.QuadTo(1049.4f, -95.9f, 1073.3f, -50.1f),
                PathNode.QuadTo(1084.7f, -28.2f, 1087.5f, 2.7f),
                PathNode.QuadTo(1090.2f, 33.5f, 1090.2f, 104f),
                PathNode.VerticalTo(511.2f),
                PathNode.HorizontalTo(727.5f),
                PathNode.QuadTo(713.5f, 511.2f, 705.5f, 519.2f),
                PathNode.QuadTo(697.5f, 527.2f, 697.5f, 541.2f),
                PathNode.VerticalTo(883.2f),
                PathNode.HorizontalTo(477f),
                PathNode.QuadTo(407.5f, 883.2f, 376.4f, 880.5f),
                PathNode.QuadTo(345.2f, 877.7f, 322.9f, 866.3f),
                PathNode.Close,
                PathNode.MoveTo(1087.2f, 621.8f),
                PathNode.QuadTo(1082.8f, 641.6f, 1072.4f, 654.5f),
                PathNode.QuadTo(1060.5f, 669.3f, 1037f, 689.9f),
                PathNode.LineTo(872.4f, 840.9f),
                PathNode.QuadTo(849.9f, 861.9f, 837.6f, 868.8f),
                PathNode.QuadTo(823.8f, 877.6f, 808.4f, 880.2f),
                PathNode.QuadTo(800.1f, 881.2f, 790.8f, 882.2f),
                PathNode.QuadTo(781.5f, 883.2f, 773.2f, 883.2f),
                PathNode.VerticalTo(599.7f),
                PathNode.QuadTo(773.2f, 587f, 786f, 587f),
                PathNode.HorizontalTo(1090.2f),
                PathNode.QuadTo(1091.1f, 601.8f, 1087.2f, 621.8f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.5794f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _noContentSurfaceDemibold!!
    }

private var _noContentSurfaceDemibold: ImageVector? = null

