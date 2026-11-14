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

val MiuixIcons.Glass.Sort: ImageVector
    get() = MiuixIcons.Glass.Regular.Sort

val MiuixIcons.Glass.Light.Sort: ImageVector
    get() {
        if (_sortLineLight != null) return _sortLineLight!!
        _sortLineLight = buildHyperOSIcon(
            name = "Sort.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(524.9f, -104f),
                PathNode.VerticalTo(856f),
                PathNode.QuadTo(524.9f, 867.9f, 519.1f, 873.4f),
                PathNode.QuadTo(513.3f, 878.9f, 502f, 878.9f),
                PathNode.HorizontalTo(477f),
                PathNode.QuadTo(465.7f, 878.9f, 459.9f, 873.4f),
                PathNode.QuadTo(454.1f, 867.9f, 454.1f, 856f),
                PathNode.VerticalTo(56.9f),
                PathNode.HorizontalTo(366f),
                PathNode.QuadTo(341.6f, 56.9f, 330.8f, 56.1f),
                PathNode.QuadTo(320f, 55.3f, 314.9f, 51.7f),
                PathNode.QuadTo(308.1f, 46.4f, 308.1f, 38f),
                PathNode.QuadTo(308.1f, 31.2f, 315.3f, 22.5f),
                PathNode.QuadTo(322.6f, 13.8f, 340.9f, -4.1f),
                PathNode.LineTo(460f, -123.2f),
                PathNode.QuadTo(465.7f, -128.9f, 477f, -128.9f),
                PathNode.HorizontalTo(501f),
                PathNode.QuadTo(514.4f, -128.9f, 519.7f, -123.1f),
                PathNode.QuadTo(524.9f, -117.3f, 524.9f, -104f),
                PathNode.Close,
                PathNode.MoveTo(805.1f, 854f),
                PathNode.VerticalTo(-106f),
                PathNode.QuadTo(805.1f, -117.9f, 810.9f, -123.4f),
                PathNode.QuadTo(816.7f, -128.9f, 828f, -128.9f),
                PathNode.HorizontalTo(853f),
                PathNode.QuadTo(864.3f, -128.9f, 870.1f, -123.4f),
                PathNode.QuadTo(875.9f, -117.9f, 875.9f, -106f),
                PathNode.VerticalTo(693.1f),
                PathNode.HorizontalTo(964f),
                PathNode.QuadTo(988.4f, 693.1f, 999.2f, 693.9f),
                PathNode.QuadTo(1010f, 694.7f, 1015.1f, 698.3f),
                PathNode.QuadTo(1021.9f, 702.7f, 1021.9f, 711.6f),
                PathNode.QuadTo(1021.9f, 718.3f, 1014.7f, 727.3f),
                PathNode.QuadTo(1007.4f, 736.2f, 989.1f, 754.1f),
                PathNode.LineTo(870f, 873.2f),
                PathNode.QuadTo(864.3f, 878.9f, 853f, 878.9f),
                PathNode.HorizontalTo(829f),
                PathNode.QuadTo(815.6f, 878.9f, 810.3f, 873.1f),
                PathNode.QuadTo(805.1f, 867.3f, 805.1f, 854f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _sortLineLight!!
    }

private var _sortLineLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Sort: ImageVector
    get() {
        if (_sortLineNormal != null) return _sortLineNormal!!
        _sortLineNormal = buildHyperOSIcon(
            name = "Sort.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(529.8f, -104f),
                PathNode.VerticalTo(856f),
                PathNode.QuadTo(529.8f, 870f, 522.5f, 876.9f),
                PathNode.QuadTo(515.2f, 883.8f, 502f, 883.8f),
                PathNode.HorizontalTo(477f),
                PathNode.QuadTo(463.8f, 883.8f, 456.5f, 876.9f),
                PathNode.QuadTo(449.2f, 870f, 449.2f, 856f),
                PathNode.VerticalTo(61.8f),
                PathNode.HorizontalTo(366f),
                PathNode.QuadTo(339.1f, 61.8f, 328.2f, 60.8f),
                PathNode.QuadTo(317.3f, 59.9f, 311.5f, 55.3f),
                PathNode.QuadTo(303.2f, 48.9f, 303.2f, 38f),
                PathNode.QuadTo(303.2f, 29.7f, 310.3f, 21.1f),
                PathNode.QuadTo(317.4f, 12.6f, 337.5f, -7.5f),
                PathNode.LineTo(457.3f, -127.2f),
                PathNode.QuadTo(463.8f, -133.8f, 477f, -133.8f),
                PathNode.HorizontalTo(501f),
                PathNode.QuadTo(516.9f, -133.8f, 523.3f, -126.5f),
                PathNode.QuadTo(529.8f, -119.2f, 529.8f, -104f),
                PathNode.Close,
                PathNode.MoveTo(800.2f, 854f),
                PathNode.VerticalTo(-106f),
                PathNode.QuadTo(800.2f, -120f, 807.5f, -126.9f),
                PathNode.QuadTo(814.8f, -133.8f, 828f, -133.8f),
                PathNode.HorizontalTo(853f),
                PathNode.QuadTo(866.2f, -133.8f, 873.5f, -126.9f),
                PathNode.QuadTo(880.8f, -120f, 880.8f, -106f),
                PathNode.VerticalTo(688.2f),
                PathNode.HorizontalTo(964f),
                PathNode.QuadTo(990.9f, 688.2f, 1001.8f, 689.2f),
                PathNode.QuadTo(1012.7f, 690.1f, 1018.5f, 694.7f),
                PathNode.QuadTo(1026.8f, 700.8f, 1026.8f, 711.9f),
                PathNode.QuadTo(1026.8f, 720.2f, 1019.7f, 728.8f),
                PathNode.QuadTo(1012.6f, 737.4f, 992.5f, 757.5f),
                PathNode.LineTo(872.7f, 877.2f),
                PathNode.QuadTo(866.2f, 883.8f, 853f, 883.8f),
                PathNode.HorizontalTo(829f),
                PathNode.QuadTo(813.1f, 883.8f, 806.7f, 876.5f),
                PathNode.QuadTo(800.2f, 869.2f, 800.2f, 854f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _sortLineNormal!!
    }

private var _sortLineNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Sort: ImageVector
    get() {
        if (_sortLineRegular != null) return _sortLineRegular!!
        _sortLineRegular = buildHyperOSIcon(
            name = "Sort.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(532f, -104f),
                PathNode.VerticalTo(856f),
                PathNode.QuadTo(532f, 871f, 524f, 878.5f),
                PathNode.QuadTo(516f, 886f, 502f, 886f),
                PathNode.HorizontalTo(477f),
                PathNode.QuadTo(463f, 886f, 455f, 878.5f),
                PathNode.QuadTo(447f, 871f, 447f, 856f),
                PathNode.VerticalTo(64f),
                PathNode.HorizontalTo(366f),
                PathNode.QuadTo(338f, 64f, 327f, 63f),
                PathNode.QuadTo(316f, 62f, 310f, 57f),
                PathNode.QuadTo(301f, 50f, 301f, 38f),
                PathNode.QuadTo(301f, 29f, 308f, 20.5f),
                PathNode.QuadTo(315f, 12f, 336f, -9f),
                PathNode.LineTo(456f, -129f),
                PathNode.QuadTo(463f, -136f, 477f, -136f),
                PathNode.HorizontalTo(501f),
                PathNode.QuadTo(518f, -136f, 525f, -128f),
                PathNode.QuadTo(532f, -120f, 532f, -104f),
                PathNode.Close,
                PathNode.MoveTo(798f, 854f),
                PathNode.VerticalTo(-106f),
                PathNode.QuadTo(798f, -121f, 806f, -128.5f),
                PathNode.QuadTo(814f, -136f, 828f, -136f),
                PathNode.HorizontalTo(853f),
                PathNode.QuadTo(867f, -136f, 875f, -128.5f),
                PathNode.QuadTo(883f, -121f, 883f, -106f),
                PathNode.VerticalTo(686f),
                PathNode.HorizontalTo(964f),
                PathNode.QuadTo(992f, 686f, 1003f, 687f),
                PathNode.QuadTo(1014f, 688f, 1020f, 693f),
                PathNode.QuadTo(1029f, 700f, 1029f, 712f),
                PathNode.QuadTo(1029f, 721f, 1022f, 729.5f),
                PathNode.QuadTo(1015f, 738f, 994f, 759f),
                PathNode.LineTo(874f, 879f),
                PathNode.QuadTo(867f, 886f, 853f, 886f),
                PathNode.HorizontalTo(829f),
                PathNode.QuadTo(812f, 886f, 805f, 878f),
                PathNode.QuadTo(798f, 870f, 798f, 854f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _sortLineRegular!!
    }

private var _sortLineRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Sort: ImageVector
    get() {
        if (_sortLineMedium != null) return _sortLineMedium!!
        _sortLineMedium = buildHyperOSIcon(
            name = "Sort.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(540.1f, -104f),
                PathNode.VerticalTo(861.4f),
                PathNode.QuadTo(540.1f, 877.8f, 531.6f, 885.9f),
                PathNode.QuadTo(523f, 894.1f, 507.4f, 894.1f),
                PathNode.HorizontalTo(471.6f),
                PathNode.QuadTo(456.2f, 894.1f, 447.6f, 885.9f),
                PathNode.QuadTo(438.9f, 877.8f, 438.9f, 861.4f),
                PathNode.VerticalTo(72.1f),
                PathNode.HorizontalTo(366f),
                PathNode.QuadTo(336.6f, 72.1f, 324.6f, 70.7f),
                PathNode.QuadTo(312.5f, 69.3f, 305.1f, 63.5f),
                PathNode.QuadTo(292.9f, 54.3f, 292.9f, 38f),
                PathNode.QuadTo(292.9f, 26.8f, 300.4f, 17.3f),
                PathNode.QuadTo(308f, 7.7f, 330.3f, -14.7f),
                PathNode.LineTo(450.3f, -134.7f),
                PathNode.QuadTo(459.8f, -144.1f, 477f, -144.1f),
                PathNode.HorizontalTo(501f),
                PathNode.QuadTo(521.2f, -144.1f, 530.7f, -133.7f),
                PathNode.QuadTo(540.1f, -123.2f, 540.1f, -104f),
                PathNode.Close,
                PathNode.MoveTo(789.9f, 854f),
                PathNode.VerticalTo(-111.4f),
                PathNode.QuadTo(789.9f, -127.8f, 798.6f, -135.9f),
                PathNode.QuadTo(807.2f, -144.1f, 822.6f, -144.1f),
                PathNode.HorizontalTo(858.4f),
                PathNode.QuadTo(874f, -144.1f, 882.6f, -135.9f),
                PathNode.QuadTo(891.1f, -127.8f, 891.1f, -111.4f),
                PathNode.VerticalTo(677.9f),
                PathNode.HorizontalTo(964f),
                PathNode.QuadTo(993.4f, 677.9f, 1005.4f, 679.3f),
                PathNode.QuadTo(1017.5f, 680.7f, 1024.9f, 686.5f),
                PathNode.QuadTo(1037.1f, 695.7f, 1037.1f, 712f),
                PathNode.QuadTo(1037.1f, 723.2f, 1029.6f, 732.7f),
                PathNode.QuadTo(1022f, 742.3f, 999.7f, 764.7f),
                PathNode.LineTo(879.7f, 884.7f),
                PathNode.QuadTo(870.2f, 894.1f, 853f, 894.1f),
                PathNode.HorizontalTo(829f),
                PathNode.QuadTo(808.8f, 894.1f, 799.3f, 883.7f),
                PathNode.QuadTo(789.9f, 873.2f, 789.9f, 854f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _sortLineMedium!!
    }

private var _sortLineMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Sort: ImageVector
    get() {
        if (_sortLineDemibold != null) return _sortLineDemibold!!
        _sortLineDemibold = buildHyperOSIcon(
            name = "Sort.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(545.8f, -104f),
                PathNode.VerticalTo(865.2f),
                PathNode.QuadTo(545.8f, 882.5f, 536.9f, 891.1f),
                PathNode.QuadTo(527.9f, 899.8f, 511.2f, 899.8f),
                PathNode.HorizontalTo(467.8f),
                PathNode.QuadTo(451.5f, 899.8f, 442.4f, 891.1f),
                PathNode.QuadTo(433.2f, 882.5f, 433.2f, 865.2f),
                PathNode.VerticalTo(77.8f),
                PathNode.HorizontalTo(366f),
                PathNode.QuadTo(335.7f, 77.8f, 322.9f, 76.1f),
                PathNode.QuadTo(310f, 74.4f, 301.7f, 68f),
                PathNode.QuadTo(287.2f, 57.4f, 287.2f, 38f),
                PathNode.QuadTo(287.2f, 25.3f, 295.1f, 15f),
                PathNode.QuadTo(303.1f, 4.6f, 326.4f, -18.6f),
                PathNode.LineTo(446.4f, -138.6f),
                PathNode.QuadTo(457.5f, -149.8f, 477f, -149.8f),
                PathNode.HorizontalTo(501f),
                PathNode.QuadTo(523.5f, -149.8f, 534.6f, -137.6f),
                PathNode.QuadTo(545.8f, -125.5f, 545.8f, -104f),
                PathNode.Close,
                PathNode.MoveTo(784.2f, 854f),
                PathNode.VerticalTo(-115.2f),
                PathNode.QuadTo(784.2f, -132.5f, 793.4f, -141.1f),
                PathNode.QuadTo(802.5f, -149.8f, 818.8f, -149.8f),
                PathNode.HorizontalTo(862.2f),
                PathNode.QuadTo(878.9f, -149.8f, 887.9f, -141.1f),
                PathNode.QuadTo(896.8f, -132.5f, 896.8f, -115.2f),
                PathNode.VerticalTo(672.2f),
                PathNode.HorizontalTo(964f),
                PathNode.QuadTo(994.3f, 672.2f, 1007.1f, 673.9f),
                PathNode.QuadTo(1020f, 675.6f, 1028.3f, 682f),
                PathNode.QuadTo(1042.8f, 692.6f, 1042.8f, 712f),
                PathNode.QuadTo(1042.8f, 724.7f, 1034.9f, 735f),
                PathNode.QuadTo(1026.9f, 745.4f, 1003.6f, 768.6f),
                PathNode.LineTo(883.6f, 888.6f),
                PathNode.QuadTo(872.5f, 899.8f, 853f, 899.8f),
                PathNode.HorizontalTo(829f),
                PathNode.QuadTo(806.5f, 899.8f, 795.4f, 887.6f),
                PathNode.QuadTo(784.2f, 875.5f, 784.2f, 854f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _sortLineDemibold!!
    }

private var _sortLineDemibold: ImageVector? = null

