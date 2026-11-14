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

val MiuixIcons.Glass.AppData: ImageVector
    get() = MiuixIcons.Glass.Regular.AppData

val MiuixIcons.Glass.Light.AppData: ImageVector
    get() {
        if (_appDataSurfaceLight != null) return _appDataSurfaceLight!!
        _appDataSurfaceLight = buildHyperOSIcon(
            name = "AppData.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(1152.3f, 350.9f),
                PathNode.HorizontalTo(659.4f),
                PathNode.QuadTo(648.6f, 350.9f, 644.7f, 356.9f),
                PathNode.QuadTo(640.9f, 362.9f, 640.9f, 376f),
                PathNode.VerticalTo(862.9f),
                PathNode.QuadTo(513.2f, 857.1f, 406.9f, 789.9f),
                PathNode.QuadTo(300.7f, 722.7f, 238.9f, 613.2f),
                PathNode.QuadTo(177.1f, 503.8f, 177.1f, 375.6f),
                PathNode.QuadTo(177.1f, 243.1f, 242.6f, 131.1f),
                PathNode.QuadTo(308f, 19f, 420.1f, -46.9f),
                PathNode.QuadTo(532.1f, -112.9f, 665f, -112.9f),
                PathNode.QuadTo(793.7f, -112.9f, 903.1f, -50.9f),
                PathNode.QuadTo(1012.6f, 11.1f, 1079.3f, 117.4f),
                PathNode.QuadTo(1146f, 223.8f, 1152.3f, 350.9f),
                PathNode.Close,
                PathNode.MoveTo(689.1f, 862.9f),
                PathNode.VerticalTo(399.7f),
                PathNode.HorizontalTo(1152.3f),
                PathNode.QuadTo(1146.6f, 523.1f, 1083.5f, 627.1f),
                PathNode.QuadTo(1020.4f, 731f, 916.5f, 794.1f),
                PathNode.QuadTo(812.6f, 857.1f, 689.1f, 862.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.9444f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _appDataSurfaceLight!!
    }

private var _appDataSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.AppData: ImageVector
    get() {
        if (_appDataSurfaceNormal != null) return _appDataSurfaceNormal!!
        _appDataSurfaceNormal = buildHyperOSIcon(
            name = "AppData.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(1159.7f, 344.8f),
                PathNode.HorizontalTo(656.4f),
                PathNode.QuadTo(643.4f, 344.8f, 639.1f, 352.2f),
                PathNode.QuadTo(634.8f, 359.5f, 634.8f, 376f),
                PathNode.VerticalTo(870.5f),
                PathNode.QuadTo(506.2f, 863.2f, 399.8f, 794.8f),
                PathNode.QuadTo(293.3f, 726.3f, 231.4f, 615.8f),
                PathNode.QuadTo(169.5f, 505.3f, 169.5f, 375.9f),
                PathNode.QuadTo(169.5f, 241f, 236f, 127.2f),
                PathNode.QuadTo(302.5f, 13.5f, 416.2f, -53.5f),
                PathNode.QuadTo(530f, -120.5f, 665f, -120.5f),
                PathNode.QuadTo(794.6f, -120.5f, 905.1f, -58.5f),
                PathNode.QuadTo(1015.6f, 3.5f, 1083.6f, 110.3f),
                PathNode.QuadTo(1151.5f, 217.1f, 1159.7f, 344.8f),
                PathNode.Close,
                PathNode.MoveTo(695.2f, 870.5f),
                PathNode.VerticalTo(406.1f),
                PathNode.HorizontalTo(1159.7f),
                PathNode.QuadTo(1152.4f, 529.2f, 1089f, 632.9f),
                PathNode.QuadTo(1025.6f, 736.5f, 922f, 799.9f),
                PathNode.QuadTo(818.4f, 863.2f, 695.2f, 870.5f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.0972f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _appDataSurfaceNormal!!
    }

private var _appDataSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.AppData: ImageVector
    get() {
        if (_appDataSurfaceRegular != null) return _appDataSurfaceRegular!!
        _appDataSurfaceRegular = buildHyperOSIcon(
            name = "AppData.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(1163f, 342f),
                PathNode.HorizontalTo(655f),
                PathNode.QuadTo(641f, 342f, 636.5f, 350f),
                PathNode.QuadTo(632f, 358f, 632f, 376f),
                PathNode.VerticalTo(874f),
                PathNode.QuadTo(503f, 866f, 396.5f, 797f),
                PathNode.QuadTo(290f, 728f, 228f, 617f),
                PathNode.QuadTo(166f, 506f, 166f, 376f),
                PathNode.QuadTo(166f, 240f, 233f, 125.5f),
                PathNode.QuadTo(300f, 11f, 414.5f, -56.5f),
                PathNode.QuadTo(529f, -124f, 665f, -124f),
                PathNode.QuadTo(795f, -124f, 906f, -62f),
                PathNode.QuadTo(1017f, 0f, 1085.5f, 107f),
                PathNode.QuadTo(1154f, 214f, 1163f, 342f),
                PathNode.Close,
                PathNode.MoveTo(698f, 874f),
                PathNode.VerticalTo(409f),
                PathNode.HorizontalTo(1163f),
                PathNode.QuadTo(1155f, 532f, 1091.5f, 635.5f),
                PathNode.QuadTo(1028f, 739f, 924.5f, 802.5f),
                PathNode.QuadTo(821f, 866f, 698f, 874f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.1667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _appDataSurfaceRegular!!
    }

private var _appDataSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.AppData: ImageVector
    get() {
        if (_appDataSurfaceMedium != null) return _appDataSurfaceMedium!!
        _appDataSurfaceMedium = buildHyperOSIcon(
            name = "AppData.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(1171.6f, 334.2f),
                PathNode.HorizontalTo(655f),
                PathNode.QuadTo(638.3f, 334.2f, 631.2f, 344.2f),
                PathNode.QuadTo(624.2f, 354.2f, 624.2f, 376f),
                PathNode.VerticalTo(882.4f),
                PathNode.QuadTo(494.6f, 872.5f, 387.7f, 802f),
                PathNode.QuadTo(280.8f, 731.5f, 218.7f, 619.2f),
                PathNode.QuadTo(156.5f, 506.8f, 156.5f, 375.7f),
                PathNode.QuadTo(156.5f, 237.3f, 224.9f, 120.6f),
                PathNode.QuadTo(293.2f, 4f, 409.9f, -64.7f),
                PathNode.QuadTo(526.6f, -133.5f, 665f, -133.5f),
                PathNode.QuadTo(796.4f, -133.5f, 908.7f, -71.3f),
                PathNode.QuadTo(1021.1f, -9.2f, 1091f, 98.1f),
                PathNode.QuadTo(1161f, 205.4f, 1171.6f, 334.2f),
                PathNode.Close,
                PathNode.MoveTo(705.8f, 882.4f),
                PathNode.VerticalTo(416.6f),
                PathNode.HorizontalTo(1171.6f),
                PathNode.QuadTo(1162f, 539f, 1098.3f, 642.1f),
                PathNode.QuadTo(1034.5f, 745.2f, 931.4f, 809f),
                PathNode.QuadTo(828.3f, 872.8f, 705.8f, 882.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.572f,
            translationY = 1041.1261f,
            autoMirror = false,
        )
        return _appDataSurfaceMedium!!
    }

private var _appDataSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.AppData: ImageVector
    get() {
        if (_appDataSurfaceDemibold != null) return _appDataSurfaceDemibold!!
        _appDataSurfaceDemibold = buildHyperOSIcon(
            name = "AppData.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(1177.7f, 328.7f),
                PathNode.HorizontalTo(655f),
                PathNode.QuadTo(636.4f, 328.7f, 627.5f, 340.1f),
                PathNode.QuadTo(618.7f, 351.6f, 618.7f, 376f),
                PathNode.VerticalTo(888.2f),
                PathNode.QuadTo(488.8f, 877f, 381.6f, 805.5f),
                PathNode.QuadTo(274.4f, 734f, 212.1f, 620.7f),
                PathNode.QuadTo(149.9f, 507.4f, 149.9f, 375.5f),
                PathNode.QuadTo(149.9f, 235.4f, 219.2f, 117.2f),
                PathNode.QuadTo(288.5f, -0.9f, 406.7f, -70.5f),
                PathNode.QuadTo(524.9f, -140.1f, 665f, -140.1f),
                PathNode.QuadTo(797.3f, -140.1f, 910.6f, -77.9f),
                PathNode.QuadTo(1023.9f, -15.6f, 1094.9f, 91.8f),
                PathNode.QuadTo(1165.9f, 199.3f, 1177.7f, 328.7f),
                PathNode.Close,
                PathNode.MoveTo(711.3f, 888.2f),
                PathNode.VerticalTo(421.9f),
                PathNode.HorizontalTo(1177.7f),
                PathNode.QuadTo(1166.9f, 543.9f, 1103f, 646.8f),
                PathNode.QuadTo(1039f, 749.6f, 936.2f, 813.5f),
                PathNode.QuadTo(833.4f, 877.5f, 711.3f, 888.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.8559f,
            translationY = 1040.7477f,
            autoMirror = false,
        )
        return _appDataSurfaceDemibold!!
    }

private var _appDataSurfaceDemibold: ImageVector? = null

