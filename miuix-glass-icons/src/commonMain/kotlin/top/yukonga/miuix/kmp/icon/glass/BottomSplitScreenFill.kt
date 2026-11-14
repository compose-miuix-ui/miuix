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

val MiuixIcons.Glass.BottomSplitScreenFill: ImageVector
    get() = MiuixIcons.Glass.Regular.BottomSplitScreenFill

val MiuixIcons.Glass.Light.BottomSplitScreenFill: ImageVector
    get() {
        if (_bottomSplitScreenSurfaceLight != null) return _bottomSplitScreenSurfaceLight!!
        _bottomSplitScreenSurfaceLight = buildHyperOSIcon(
            name = "BottomSplitScreenFill.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(1000.3f, 346.4f),
                PathNode.HorizontalTo(329.6f),
                PathNode.QuadTo(315.1f, 346.4f, 315.1f, 332f),
                PathNode.VerticalTo(73.6f),
                PathNode.QuadTo(315.1f, 50.1f, 327.8f, 37.4f),
                PathNode.QuadTo(340.6f, 24.7f, 364f, 24.7f),
                PathNode.HorizontalTo(965.9f),
                PathNode.QuadTo(989.9f, 24.7f, 1002.3f, 37.4f),
                PathNode.QuadTo(1014.8f, 50.1f, 1014.8f, 73.6f),
                PathNode.VerticalTo(332f),
                PathNode.QuadTo(1014.8f, 346.4f, 1000.3f, 346.4f),
                PathNode.Close,
                PathNode.MoveTo(973.4f, 854.8f),
                PathNode.HorizontalTo(356.4f),
                PathNode.QuadTo(271.2f, 854.2f, 228f, 810.7f),
                PathNode.QuadTo(184.8f, 767.2f, 184.8f, 681.6f),
                PathNode.VerticalTo(65.6f),
                PathNode.QuadTo(184.8f, -20.1f, 227.8f, -63.1f),
                PathNode.QuadTo(270.8f, -106.1f, 356.4f, -106.1f),
                PathNode.HorizontalTo(973.4f),
                PathNode.QuadTo(1059.1f, -106.1f, 1102.1f, -63.1f),
                PathNode.QuadTo(1145.1f, -20.1f, 1145.1f, 65.6f),
                PathNode.VerticalTo(684.6f),
                PathNode.QuadTo(1145.1f, 769.8f, 1101.9f, 812.3f),
                PathNode.QuadTo(1058.7f, 854.8f, 973.4f, 854.8f),
                PathNode.Close,
                PathNode.MoveTo(341.4f, 795.9f),
                PathNode.HorizontalTo(988.4f),
                PathNode.QuadTo(1034.8f, 795.9f, 1060.3f, 770.4f),
                PathNode.QuadTo(1085.8f, 744.9f, 1085.8f, 698.6f),
                PathNode.VerticalTo(50.6f),
                PathNode.QuadTo(1085.8f, 4.8f, 1060.3f, -21f),
                PathNode.QuadTo(1034.8f, -46.8f, 988.4f, -46.8f),
                PathNode.HorizontalTo(341.4f),
                PathNode.QuadTo(296.1f, -46.8f, 270.1f, -21f),
                PathNode.QuadTo(244.1f, 4.8f, 244.1f, 50.6f),
                PathNode.VerticalTo(698.6f),
                PathNode.QuadTo(244.1f, 744.9f, 270.1f, 770.4f),
                PathNode.QuadTo(296.1f, 795.9f, 341.4f, 795.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.7222f,
            translationY = 1041f,
            autoMirror = false,
        )
        return _bottomSplitScreenSurfaceLight!!
    }

private var _bottomSplitScreenSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.BottomSplitScreenFill: ImageVector
    get() {
        if (_bottomSplitScreenSurfaceNormal != null) return _bottomSplitScreenSurfaceNormal!!
        _bottomSplitScreenSurfaceNormal = buildHyperOSIcon(
            name = "BottomSplitScreenFill.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(993.9f, 343.4f),
                PathNode.HorizontalTo(335.4f),
                PathNode.QuadTo(324f, 343.4f, 324f, 332f),
                PathNode.VerticalTo(76.6f),
                PathNode.QuadTo(324f, 56.2f, 335.2f, 45f),
                PathNode.QuadTo(346.4f, 33.8f, 366.7f, 33.8f),
                PathNode.HorizontalTo(962.5f),
                PathNode.QuadTo(983.8f, 33.8f, 994.5f, 45f),
                PathNode.QuadTo(1005.3f, 56.2f, 1005.3f, 76.6f),
                PathNode.VerticalTo(332f),
                PathNode.QuadTo(1005.3f, 343.4f, 993.9f, 343.4f),
                PathNode.Close,
                PathNode.MoveTo(973.1f, 864.6f),
                PathNode.HorizontalTo(356.1f),
                PathNode.QuadTo(266.9f, 863.7f, 221.1f, 817.4f),
                PathNode.QuadTo(175.3f, 771.2f, 175.3f, 681.9f),
                PathNode.VerticalTo(65.9f),
                PathNode.QuadTo(175.3f, -23.5f, 221.1f, -69.2f),
                PathNode.QuadTo(266.8f, -115f, 356.1f, -115f),
                PathNode.HorizontalTo(973.1f),
                PathNode.QuadTo(1062.5f, -115f, 1108.2f, -69.2f),
                PathNode.QuadTo(1154f, -23.5f, 1154f, 65.9f),
                PathNode.VerticalTo(684.9f),
                PathNode.QuadTo(1154f, 774.1f, 1108.2f, 819.3f),
                PathNode.QuadTo(1062.3f, 864.6f, 973.1f, 864.6f),
                PathNode.Close,
                PathNode.MoveTo(341.1f, 787f),
                PathNode.HorizontalTo(988.1f),
                PathNode.QuadTo(1030.8f, 787f, 1053.6f, 764.3f),
                PathNode.QuadTo(1076.3f, 741.5f, 1076.3f, 698.9f),
                PathNode.VerticalTo(50.9f),
                PathNode.QuadTo(1076.3f, 9.1f, 1053.6f, -14.1f),
                PathNode.QuadTo(1030.8f, -37.3f, 988.1f, -37.3f),
                PathNode.HorizontalTo(341.1f),
                PathNode.QuadTo(299.5f, -37.3f, 276.2f, -14.1f),
                PathNode.QuadTo(253f, 9.1f, 253f, 50.9f),
                PathNode.VerticalTo(698.9f),
                PathNode.QuadTo(253f, 741.5f, 276.2f, 764.3f),
                PathNode.QuadTo(299.5f, 787f, 341.1f, 787f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.0277f,
            translationY = 1041.4583f,
            autoMirror = false,
        )
        return _bottomSplitScreenSurfaceNormal!!
    }

private var _bottomSplitScreenSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.BottomSplitScreenFill: ImageVector
    get() {
        if (_bottomSplitScreenSurfaceRegular != null) return _bottomSplitScreenSurfaceRegular!!
        _bottomSplitScreenSurfaceRegular = buildHyperOSIcon(
            name = "BottomSplitScreenFill.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(991f, 342f),
                PathNode.HorizontalTo(338f),
                PathNode.QuadTo(328f, 342f, 328f, 332f),
                PathNode.VerticalTo(78f),
                PathNode.QuadTo(328f, 59f, 338.5f, 48.5f),
                PathNode.QuadTo(349f, 38f, 368f, 38f),
                PathNode.HorizontalTo(961f),
                PathNode.QuadTo(981f, 38f, 991f, 48.5f),
                PathNode.QuadTo(1001f, 59f, 1001f, 78f),
                PathNode.VerticalTo(332f),
                PathNode.QuadTo(1001f, 342f, 991f, 342f),
                PathNode.Close,
                PathNode.MoveTo(973f, 869f),
                PathNode.HorizontalTo(356f),
                PathNode.QuadTo(265f, 868f, 218f, 820.5f),
                PathNode.QuadTo(171f, 773f, 171f, 682f),
                PathNode.VerticalTo(66f),
                PathNode.QuadTo(171f, -25f, 218f, -72f),
                PathNode.QuadTo(265f, -119f, 356f, -119f),
                PathNode.HorizontalTo(973f),
                PathNode.QuadTo(1064f, -119f, 1111f, -72f),
                PathNode.QuadTo(1158f, -25f, 1158f, 66f),
                PathNode.VerticalTo(685f),
                PathNode.QuadTo(1158f, 776f, 1111f, 822.5f),
                PathNode.QuadTo(1064f, 869f, 973f, 869f),
                PathNode.Close,
                PathNode.MoveTo(341f, 783f),
                PathNode.HorizontalTo(988f),
                PathNode.QuadTo(1029f, 783f, 1050.5f, 761.5f),
                PathNode.QuadTo(1072f, 740f, 1072f, 699f),
                PathNode.VerticalTo(51f),
                PathNode.QuadTo(1072f, 11f, 1050.5f, -11f),
                PathNode.QuadTo(1029f, -33f, 988f, -33f),
                PathNode.HorizontalTo(341f),
                PathNode.QuadTo(301f, -33f, 279f, -11f),
                PathNode.QuadTo(257f, 11f, 257f, 51f),
                PathNode.VerticalTo(699f),
                PathNode.QuadTo(257f, 740f, 279f, 761.5f),
                PathNode.QuadTo(301f, 783f, 341f, 783f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.1667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _bottomSplitScreenSurfaceRegular!!
    }

private var _bottomSplitScreenSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.BottomSplitScreenFill: ImageVector
    get() {
        if (_bottomSplitScreenSurfaceMedium != null) return _bottomSplitScreenSurfaceMedium!!
        _bottomSplitScreenSurfaceMedium = buildHyperOSIcon(
            name = "BottomSplitScreenFill.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(979.9f, 339f),
                PathNode.HorizontalTo(348.5f),
                PathNode.QuadTo(335.8f, 339f, 335.8f, 326.3f),
                PathNode.VerticalTo(88.5f),
                PathNode.QuadTo(335.8f, 67.9f, 347f, 56.9f),
                PathNode.QuadTo(358.2f, 45.8f, 378.5f, 45.8f),
                PathNode.HorizontalTo(949.9f),
                PathNode.QuadTo(971f, 45.8f, 981.8f, 57f),
                PathNode.QuadTo(992.6f, 68.2f, 992.6f, 88.5f),
                PathNode.VerticalTo(326.3f),
                PathNode.QuadTo(992.6f, 339f, 979.9f, 339f),
                PathNode.Close,
                PathNode.MoveTo(973.3f, 876.6f),
                PathNode.HorizontalTo(356.3f),
                PathNode.QuadTo(262f, 875.8f, 212.6f, 825.9f),
                PathNode.QuadTo(163.2f, 776f, 163.2f, 681.7f),
                PathNode.VerticalTo(65.7f),
                PathNode.QuadTo(163.2f, -28.8f, 212.6f, -78.1f),
                PathNode.QuadTo(262f, -127.4f, 356.3f, -127.4f),
                PathNode.HorizontalTo(973.3f),
                PathNode.QuadTo(1067.5f, -127.4f, 1116.9f, -78.1f),
                PathNode.QuadTo(1166.4f, -28.8f, 1166.4f, 65.7f),
                PathNode.VerticalTo(684.7f),
                PathNode.QuadTo(1166.4f, 779f, 1116.9f, 827.8f),
                PathNode.QuadTo(1067.5f, 876.6f, 973.3f, 876.6f),
                PathNode.Close,
                PathNode.MoveTo(348.3f, 774.6f),
                PathNode.HorizontalTo(981.2f),
                PathNode.QuadTo(1021.7f, 774.6f, 1042.9f, 753.4f),
                PathNode.QuadTo(1064.2f, 732.2f, 1064.2f, 691.7f),
                PathNode.VerticalTo(57.8f),
                PathNode.QuadTo(1064.2f, 18f, 1042.9f, -3.6f),
                PathNode.QuadTo(1021.7f, -25.2f, 981.2f, -25.2f),
                PathNode.HorizontalTo(348.3f),
                PathNode.QuadTo(308.6f, -25.2f, 287f, -3.6f),
                PathNode.QuadTo(265.4f, 18f, 265.4f, 57.8f),
                PathNode.VerticalTo(691.7f),
                PathNode.QuadTo(265.4f, 732.2f, 287f, 753.4f),
                PathNode.QuadTo(308.6f, 774.6f, 348.3f, 774.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.8964f,
            translationY = 1041.2613f,
            autoMirror = false,
        )
        return _bottomSplitScreenSurfaceMedium!!
    }

private var _bottomSplitScreenSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.BottomSplitScreenFill: ImageVector
    get() {
        if (_bottomSplitScreenSurfaceDemibold != null) return _bottomSplitScreenSurfaceDemibold!!
        _bottomSplitScreenSurfaceDemibold = buildHyperOSIcon(
            name = "BottomSplitScreenFill.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(972.2f, 336.9f),
                PathNode.HorizontalTo(355.9f),
                PathNode.QuadTo(341.3f, 336.9f, 341.3f, 322.4f),
                PathNode.VerticalTo(95.9f),
                PathNode.QuadTo(341.3f, 74.2f, 353f, 62.7f),
                PathNode.QuadTo(364.6f, 51.3f, 385.9f, 51.3f),
                PathNode.HorizontalTo(942.2f),
                PathNode.QuadTo(964f, 51.3f, 975.4f, 63f),
                PathNode.QuadTo(986.8f, 74.6f, 986.8f, 95.9f),
                PathNode.VerticalTo(322.4f),
                PathNode.QuadTo(986.8f, 336.9f, 972.2f, 336.9f),
                PathNode.Close,
                PathNode.MoveTo(973.5f, 881.9f),
                PathNode.HorizontalTo(356.5f),
                PathNode.QuadTo(259.9f, 881.3f, 208.8f, 829.7f),
                PathNode.QuadTo(157.7f, 778.1f, 157.7f, 681.5f),
                PathNode.VerticalTo(65.5f),
                PathNode.QuadTo(157.7f, -31.4f, 208.8f, -82.3f),
                PathNode.QuadTo(259.9f, -133.2f, 356.5f, -133.2f),
                PathNode.HorizontalTo(973.5f),
                PathNode.QuadTo(1070f, -133.2f, 1121.1f, -82.3f),
                PathNode.QuadTo(1172.2f, -31.4f, 1172.2f, 65.5f),
                PathNode.VerticalTo(684.5f),
                PathNode.QuadTo(1172.2f, 781.1f, 1121.1f, 831.5f),
                PathNode.QuadTo(1070f, 881.9f, 973.5f, 881.9f),
                PathNode.Close,
                PathNode.MoveTo(353.4f, 768.8f),
                PathNode.HorizontalTo(976.5f),
                PathNode.QuadTo(1016.6f, 768.8f, 1037.6f, 747.7f),
                PathNode.QuadTo(1058.7f, 726.7f, 1058.7f, 686.6f),
                PathNode.VerticalTo(62.5f),
                PathNode.QuadTo(1058.7f, 22.9f, 1037.6f, 1.6f),
                PathNode.QuadTo(1016.6f, -19.7f, 976.5f, -19.7f),
                PathNode.HorizontalTo(353.4f),
                PathNode.QuadTo(313.9f, -19.7f, 292.6f, 1.6f),
                PathNode.QuadTo(271.2f, 22.9f, 271.2f, 62.5f),
                PathNode.VerticalTo(686.6f),
                PathNode.QuadTo(271.2f, 726.7f, 292.6f, 747.7f),
                PathNode.QuadTo(313.9f, 768.8f, 353.4f, 768.8f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.7072f,
            translationY = 1040.9774f,
            autoMirror = false,
        )
        return _bottomSplitScreenSurfaceDemibold!!
    }

private var _bottomSplitScreenSurfaceDemibold: ImageVector? = null

