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

val MiuixIcons.Glass.CoverDisplay: ImageVector
    get() = MiuixIcons.Glass.Regular.CoverDisplay

val MiuixIcons.Glass.Light.CoverDisplay: ImageVector
    get() {
        if (_coverDisplaySurfaceLight != null) return _coverDisplaySurfaceLight!!
        _coverDisplaySurfaceLight = buildHyperOSIcon(
            name = "CoverDisplay.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(274.1f, 673.1f),
                PathNode.LineTo(233.6f, 223.2f),
                PathNode.QuadTo(225.6f, 133.1f, 270.2f, 85.2f),
                PathNode.QuadTo(314.8f, 37.2f, 405.3f, 37.2f),
                PathNode.HorizontalTo(924.2f),
                PathNode.QuadTo(1014.8f, 37.2f, 1059.2f, 85.2f),
                PathNode.QuadTo(1103.6f, 133.1f, 1096f, 223.2f),
                PathNode.LineTo(1056.3f, 673.1f),
                PathNode.QuadTo(1049.3f, 754.6f, 1004.9f, 795.2f),
                PathNode.QuadTo(960.6f, 835.8f, 879.6f, 835.8f),
                PathNode.HorizontalTo(450.4f),
                PathNode.QuadTo(369.4f, 835.8f, 325.3f, 795.2f),
                PathNode.QuadTo(281.1f, 754.6f, 274.1f, 673.1f),
                PathNode.Close,
                PathNode.MoveTo(367.8f, -7.9f),
                PathNode.QuadTo(334.8f, -4.3f, 313.9f, 1.3f),
                PathNode.QuadTo(293f, 6.9f, 271.8f, 21.2f),
                PathNode.QuadTo(279.4f, -8.8f, 300.7f, -35.6f),
                PathNode.QuadTo(321.9f, -62.4f, 347.7f, -75.8f),
                PathNode.QuadTo(370.3f, -87.8f, 403.9f, -90.6f),
                PathNode.QuadTo(437.4f, -93.3f, 518.3f, -93.3f),
                PathNode.HorizontalTo(812.7f),
                PathNode.QuadTo(893.6f, -93.3f, 927.1f, -90.6f),
                PathNode.QuadTo(960.7f, -87.8f, 983.3f, -75.8f),
                PathNode.QuadTo(1008.7f, -62.3f, 1029.9f, -35.5f),
                PathNode.QuadTo(1051.1f, -8.7f, 1058.8f, 20.8f),
                PathNode.QuadTo(1035.7f, 6.3f, 1012.7f, 0.5f),
                PathNode.QuadTo(989.7f, -5.3f, 962.2f, -7.9f),
                PathNode.QuadTo(928.9f, -9.9f, 842.1f, -9.9f),
                PathNode.HorizontalTo(487.9f),
                PathNode.QuadTo(401.1f, -9.9f, 367.8f, -7.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.9476f,
            translationY = 1037.8888f,
            autoMirror = false,
        )
        return _coverDisplaySurfaceLight!!
    }

private var _coverDisplaySurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.CoverDisplay: ImageVector
    get() {
        if (_coverDisplaySurfaceNormal != null) return _coverDisplaySurfaceNormal!!
        _coverDisplaySurfaceNormal = buildHyperOSIcon(
            name = "CoverDisplay.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(269.2f, 676.5f),
                PathNode.LineTo(231.1f, 243.7f),
                PathNode.QuadTo(223.1f, 150.2f, 269.7f, 99.8f),
                PathNode.QuadTo(316.3f, 49.4f, 409.9f, 49.4f),
                PathNode.HorizontalTo(919.9f),
                PathNode.QuadTo(1013.6f, 49.4f, 1060.1f, 99.8f),
                PathNode.QuadTo(1106.6f, 150.2f, 1098.7f, 243.7f),
                PathNode.LineTo(1060.9f, 676.5f),
                PathNode.QuadTo(1053.9f, 760.4f, 1007.4f, 803f),
                PathNode.QuadTo(960.9f, 845.6f, 877.1f, 845.6f),
                PathNode.HorizontalTo(452.9f),
                PathNode.QuadTo(369.1f, 845.6f, 322.7f, 803f),
                PathNode.QuadTo(276.2f, 760.4f, 269.2f, 676.5f),
                PathNode.Close,
                PathNode.MoveTo(372.1f, -7.3f),
                PathNode.QuadTo(336.3f, -3.4f, 314.7f, 2.8f),
                PathNode.QuadTo(293f, 9f, 267.8f, 25.2f),
                PathNode.QuadTo(273.6f, -7.6f, 296.1f, -36.2f),
                PathNode.QuadTo(318.5f, -64.9f, 348.6f, -80.1f),
                PathNode.QuadTo(372.2f, -92.1f, 406f, -95f),
                PathNode.QuadTo(439.9f, -97.9f, 520.2f, -97.9f),
                PathNode.HorizontalTo(810.8f),
                PathNode.QuadTo(891.1f, -97.9f, 925f, -95f),
                PathNode.QuadTo(958.8f, -92.1f, 982.4f, -80.1f),
                PathNode.QuadTo(1012.3f, -64.2f, 1034.8f, -35.5f),
                PathNode.QuadTo(1057.2f, -6.8f, 1063.1f, 25.1f),
                PathNode.QuadTo(1036.6f, 8.2f, 1012.2f, 1.9f),
                PathNode.QuadTo(987.8f, -4.4f, 957.9f, -7.3f),
                PathNode.QuadTo(925.5f, -9.3f, 840f, -9.3f),
                PathNode.HorizontalTo(490f),
                PathNode.QuadTo(404.5f, -9.3f, 372.1f, -7.3f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.7541f,
            translationY = 1040.4859f,
            autoMirror = false,
        )
        return _coverDisplaySurfaceNormal!!
    }

private var _coverDisplaySurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.CoverDisplay: ImageVector
    get() {
        if (_coverDisplaySurfaceRegular != null) return _coverDisplaySurfaceRegular!!
        _coverDisplaySurfaceRegular = buildHyperOSIcon(
            name = "CoverDisplay.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(267f, 678f),
                PathNode.LineTo(230f, 253f),
                PathNode.QuadTo(222f, 158f, 269.5f, 106.5f),
                PathNode.QuadTo(317f, 55f, 412f, 55f),
                PathNode.HorizontalTo(918f),
                PathNode.QuadTo(1013f, 55f, 1060.5f, 106.5f),
                PathNode.QuadTo(1108f, 158f, 1100f, 253f),
                PathNode.LineTo(1063f, 678f),
                PathNode.QuadTo(1056f, 763f, 1008.5f, 806.5f),
                PathNode.QuadTo(961f, 850f, 876f, 850f),
                PathNode.HorizontalTo(454f),
                PathNode.QuadTo(369f, 850f, 321.5f, 806.5f),
                PathNode.QuadTo(274f, 763f, 267f, 678f),
                PathNode.Close,
                PathNode.MoveTo(374f, -7f),
                PathNode.QuadTo(337f, -3f, 315f, 3.5f),
                PathNode.QuadTo(293f, 10f, 266f, 27f),
                PathNode.QuadTo(271f, -7f, 294f, -36.5f),
                PathNode.QuadTo(317f, -66f, 349f, -82f),
                PathNode.QuadTo(373f, -94f, 407f, -97f),
                PathNode.QuadTo(441f, -100f, 521f, -100f),
                PathNode.HorizontalTo(810f),
                PathNode.QuadTo(890f, -100f, 924f, -97f),
                PathNode.QuadTo(958f, -94f, 982f, -82f),
                PathNode.QuadTo(1014f, -65f, 1037f, -35.5f),
                PathNode.QuadTo(1060f, -6f, 1065f, 27f),
                PathNode.QuadTo(1037f, 9f, 1012f, 2.5f),
                PathNode.QuadTo(987f, -4f, 956f, -7f),
                PathNode.QuadTo(924f, -9f, 839f, -9f),
                PathNode.HorizontalTo(491f),
                PathNode.QuadTo(406f, -9f, 374f, -7f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _coverDisplaySurfaceRegular!!
    }

private var _coverDisplaySurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.CoverDisplay: ImageVector
    get() {
        if (_coverDisplaySurfaceMedium != null) return _coverDisplaySurfaceMedium!!
        _coverDisplaySurfaceMedium = buildHyperOSIcon(
            name = "CoverDisplay.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(261.6f, 678.5f),
                PathNode.LineTo(225.4f, 264.9f),
                PathNode.QuadTo(217.1f, 167.7f, 266.3f, 114.3f),
                PathNode.QuadTo(315.4f, 60.9f, 412.8f, 60.9f),
                PathNode.HorizontalTo(917.2f),
                PathNode.QuadTo(1014.6f, 60.9f, 1063.7f, 114.3f),
                PathNode.QuadTo(1112.9f, 167.7f, 1104.6f, 264.9f),
                PathNode.LineTo(1068.4f, 678.5f),
                PathNode.QuadTo(1061.1f, 765.7f, 1012.1f, 810.6f),
                PathNode.QuadTo(963.2f, 855.4f, 876f, 855.4f),
                PathNode.HorizontalTo(454f),
                PathNode.QuadTo(366.8f, 855.4f, 317.9f, 810.6f),
                PathNode.QuadTo(268.9f, 765.7f, 261.6f, 678.5f),
                PathNode.Close,
                PathNode.MoveTo(374.5f, -7f),
                PathNode.QuadTo(335.4f, -3f, 310.9f, 5.1f),
                PathNode.QuadTo(286.5f, 13.2f, 260.6f, 31.3f),
                PathNode.QuadTo(264.2f, -6.5f, 287.4f, -36f),
                PathNode.QuadTo(310.5f, -65.5f, 346.6f, -83.6f),
                PathNode.QuadTo(371.4f, -96.2f, 406.1f, -99.2f),
                PathNode.QuadTo(440.7f, -102.2f, 521f, -102.2f),
                PathNode.HorizontalTo(810f),
                PathNode.QuadTo(890.3f, -102.2f, 924.9f, -99.2f),
                PathNode.QuadTo(959.6f, -96.2f, 984.4f, -83.6f),
                PathNode.QuadTo(1020.5f, -64.5f, 1043.4f, -35.2f),
                PathNode.QuadTo(1066.2f, -6f, 1070.1f, 30.2f),
                PathNode.QuadTo(1041.9f, 11.2f, 1015.1f, 3.4f),
                PathNode.QuadTo(988.4f, -4.3f, 955.5f, -7f),
                PathNode.QuadTo(923.7f, -9f, 839f, -9f),
                PathNode.HorizontalTo(491f),
                PathNode.QuadTo(406.3f, -9f, 374.5f, -7f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1043.2882f,
            autoMirror = false,
        )
        return _coverDisplaySurfaceMedium!!
    }

private var _coverDisplaySurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.CoverDisplay: ImageVector
    get() {
        if (_coverDisplaySurfaceDemibold != null) return _coverDisplaySurfaceDemibold!!
        _coverDisplaySurfaceDemibold = buildHyperOSIcon(
            name = "CoverDisplay.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(257.8f, 678.9f),
                PathNode.LineTo(222.2f, 273.2f),
                PathNode.QuadTo(213.7f, 174.5f, 264f, 119.8f),
                PathNode.QuadTo(314.2f, 65.1f, 413.4f, 65.1f),
                PathNode.HorizontalTo(916.6f),
                PathNode.QuadTo(1015.8f, 65.1f, 1066f, 119.8f),
                PathNode.QuadTo(1116.3f, 174.5f, 1107.8f, 273.2f),
                PathNode.LineTo(1072.2f, 678.9f),
                PathNode.QuadTo(1064.7f, 767.6f, 1014.7f, 813.4f),
                PathNode.QuadTo(964.7f, 859.2f, 876f, 859.2f),
                PathNode.HorizontalTo(454f),
                PathNode.QuadTo(365.3f, 859.2f, 315.3f, 813.4f),
                PathNode.QuadTo(265.3f, 767.6f, 257.8f, 678.9f),
                PathNode.Close,
                PathNode.MoveTo(374.9f, -7f),
                PathNode.QuadTo(334.2f, -3f, 308.1f, 6.3f),
                PathNode.QuadTo(282f, 15.5f, 256.8f, 34.4f),
                PathNode.QuadTo(259.5f, -6.1f, 282.7f, -35.6f),
                PathNode.QuadTo(306f, -65.1f, 344.9f, -84.8f),
                PathNode.QuadTo(370.2f, -97.7f, 405.4f, -100.7f),
                PathNode.QuadTo(440.5f, -103.7f, 521f, -103.7f),
                PathNode.HorizontalTo(810f),
                PathNode.QuadTo(890.5f, -103.7f, 925.6f, -100.7f),
                PathNode.QuadTo(960.8f, -97.7f, 986.1f, -84.8f),
                PathNode.QuadTo(1025f, -64.1f, 1047.8f, -35f),
                PathNode.QuadTo(1070.6f, -6f, 1073.7f, 32.5f),
                PathNode.QuadTo(1045.3f, 12.7f, 1017.3f, 4.1f),
                PathNode.QuadTo(989.3f, -4.5f, 955.1f, -7f),
                PathNode.QuadTo(923.5f, -9f, 839f, -9f),
                PathNode.HorizontalTo(491f),
                PathNode.QuadTo(406.5f, -9f, 374.9f, -7f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1044.4235f,
            autoMirror = false,
        )
        return _coverDisplaySurfaceDemibold!!
    }

private var _coverDisplaySurfaceDemibold: ImageVector? = null

