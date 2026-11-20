// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.HomeFill: ImageVector
    get() = MiuixIcons.Glass.Regular.HomeFill

val MiuixIcons.Glass.Light.HomeFill: ImageVector
    get() {
        if (_homeScreenSurfaceLight != null) return _homeScreenSurfaceLight!!
        _homeScreenSurfaceLight = buildHyperOSIcon(
            name = "HomeFill.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(1045.7f, 554.1f),
                PathNode.LineTo(743f, 808.3f),
                PathNode.QuadTo(704f, 841.2f, 665.2f, 841.2f),
                PathNode.QuadTo(626.4f, 841.2f, 587f, 808.3f),
                PathNode.LineTo(284.3f, 554.1f),
                PathNode.QuadTo(247.1f, 522.4f, 230.7f, 487.6f),
                PathNode.QuadTo(214.3f, 452.8f, 214.3f, 404f),
                PathNode.VerticalTo(25.9f),
                PathNode.QuadTo(214.3f, -31.8f, 244.3f, -61.7f),
                PathNode.QuadTo(274.2f, -91.7f, 331.9f, -91.7f),
                PathNode.HorizontalTo(478.3f),
                PathNode.QuadTo(499f, -91.7f, 510.6f, -80.6f),
                PathNode.QuadTo(522.2f, -69.4f, 522.2f, -47.8f),
                PathNode.VerticalTo(168f),
                PathNode.QuadTo(522.2f, 216.8f, 548.1f, 242.4f),
                PathNode.QuadTo(574f, 268f, 622.2f, 268f),
                PathNode.HorizontalTo(707.8f),
                PathNode.QuadTo(756f, 268f, 781.9f, 242.4f),
                PathNode.QuadTo(807.8f, 216.8f, 807.8f, 168f),
                PathNode.VerticalTo(-47.8f),
                PathNode.QuadTo(807.8f, -69.4f, 819.4f, -80.6f),
                PathNode.QuadTo(831f, -91.7f, 851.7f, -91.7f),
                PathNode.HorizontalTo(998.1f),
                PathNode.QuadTo(1056.2f, -91.7f, 1085.9f, -62.2f),
                PathNode.QuadTo(1115.7f, -32.8f, 1115.7f, 25.9f),
                PathNode.VerticalTo(404f),
                PathNode.QuadTo(1115.7f, 452.8f, 1099.3f, 487.6f),
                PathNode.QuadTo(1082.9f, 522.4f, 1045.7f, 554.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.4444f,
            autoMirror = false,
        )
        return _homeScreenSurfaceLight!!
    }

private var _homeScreenSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.HomeFill: ImageVector
    get() {
        if (_homeScreenSurfaceNormal != null) return _homeScreenSurfaceNormal!!
        _homeScreenSurfaceNormal = buildHyperOSIcon(
            name = "HomeFill.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(1049.3f, 560.2f),
                PathNode.LineTo(748.5f, 812.9f),
                PathNode.QuadTo(706.7f, 847.9f, 665.1f, 847.9f),
                PathNode.QuadTo(623.4f, 847.9f, 581.5f, 812.9f),
                PathNode.LineTo(280.7f, 560.2f),
                PathNode.QuadTo(242.2f, 527.6f, 225.1f, 491f),
                PathNode.QuadTo(207.9f, 454.3f, 207.9f, 404f),
                PathNode.VerticalTo(28f),
                PathNode.QuadTo(207.9f, -33.3f, 240.3f, -65.7f),
                PathNode.QuadTo(272.7f, -98.1f, 334f, -98.1f),
                PathNode.HorizontalTo(474.7f),
                PathNode.QuadTo(499f, -98.1f, 512.6f, -85f),
                PathNode.QuadTo(526.2f, -71.9f, 526.2f, -46.6f),
                PathNode.VerticalTo(159.8f),
                PathNode.QuadTo(526.2f, 210.1f, 552.8f, 236.3f),
                PathNode.QuadTo(579.5f, 262.5f, 628.9f, 262.5f),
                PathNode.HorizontalTo(701.1f),
                PathNode.QuadTo(750.5f, 262.5f, 777.2f, 236.3f),
                PathNode.QuadTo(803.8f, 210.1f, 803.8f, 159.8f),
                PathNode.VerticalTo(-46.6f),
                PathNode.QuadTo(803.8f, -71.9f, 817.4f, -85f),
                PathNode.QuadTo(831f, -98.1f, 855.3f, -98.1f),
                PathNode.HorizontalTo(996f),
                PathNode.QuadTo(1057.4f, -98.1f, 1089.8f, -66.2f),
                PathNode.QuadTo(1122.1f, -34.3f, 1122.1f, 28f),
                PathNode.VerticalTo(404f),
                PathNode.QuadTo(1122.1f, 454.3f, 1104.9f, 491f),
                PathNode.QuadTo(1087.8f, 527.6f, 1049.3f, 560.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.5972f,
            autoMirror = false,
        )
        return _homeScreenSurfaceNormal!!
    }

private var _homeScreenSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.HomeFill: ImageVector
    get() {
        if (_homeScreenSurfaceRegular != null) return _homeScreenSurfaceRegular!!
        _homeScreenSurfaceRegular = buildHyperOSIcon(
            name = "HomeFill.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(1051f, 563f),
                PathNode.LineTo(751f, 815f),
                PathNode.QuadTo(708f, 851f, 665f, 851f),
                PathNode.QuadTo(622f, 851f, 579f, 815f),
                PathNode.LineTo(279f, 563f),
                PathNode.QuadTo(240f, 530f, 222.5f, 492.5f),
                PathNode.QuadTo(205f, 455f, 205f, 404f),
                PathNode.VerticalTo(29f),
                PathNode.QuadTo(205f, -34f, 238.5f, -67.5f),
                PathNode.QuadTo(272f, -101f, 335f, -101f),
                PathNode.HorizontalTo(473f),
                PathNode.QuadTo(499f, -101f, 513.5f, -87f),
                PathNode.QuadTo(528f, -73f, 528f, -46f),
                PathNode.VerticalTo(156f),
                PathNode.QuadTo(528f, 207f, 555f, 233.5f),
                PathNode.QuadTo(582f, 260f, 632f, 260f),
                PathNode.HorizontalTo(698f),
                PathNode.QuadTo(748f, 260f, 775f, 233.5f),
                PathNode.QuadTo(802f, 207f, 802f, 156f),
                PathNode.VerticalTo(-46f),
                PathNode.QuadTo(802f, -73f, 816.5f, -87f),
                PathNode.QuadTo(831f, -101f, 857f, -101f),
                PathNode.HorizontalTo(995f),
                PathNode.QuadTo(1058f, -101f, 1091.5f, -68f),
                PathNode.QuadTo(1125f, -35f, 1125f, 29f),
                PathNode.VerticalTo(404f),
                PathNode.QuadTo(1125f, 455f, 1107.5f, 492.5f),
                PathNode.QuadTo(1090f, 530f, 1051f, 563f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _homeScreenSurfaceRegular!!
    }

private var _homeScreenSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.HomeFill: ImageVector
    get() {
        if (_homeScreenSurfaceMedium != null) return _homeScreenSurfaceMedium!!
        _homeScreenSurfaceMedium = buildHyperOSIcon(
            name = "HomeFill.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(1054f, 568.1f),
                PathNode.LineTo(755.6f, 818.8f),
                PathNode.QuadTo(710.4f, 856.7f, 665f, 856.7f),
                PathNode.QuadTo(619.6f, 856.7f, 574.4f, 818.8f),
                PathNode.LineTo(276f, 568.1f),
                PathNode.QuadTo(235.9f, 534.3f, 217.8f, 495.3f),
                PathNode.QuadTo(199.6f, 456.4f, 199.6f, 404f),
                PathNode.VerticalTo(26.3f),
                PathNode.QuadTo(199.6f, -38.1f, 233.8f, -72.2f),
                PathNode.QuadTo(267.9f, -106.4f, 332.3f, -106.4f),
                PathNode.HorizontalTo(483f),
                PathNode.QuadTo(508.5f, -106.4f, 522.6f, -92.8f),
                PathNode.QuadTo(536.6f, -79.2f, 536.6f, -52.8f),
                PathNode.VerticalTo(157.1f),
                PathNode.QuadTo(536.6f, 204.6f, 561.2f, 228.6f),
                PathNode.QuadTo(585.8f, 252.7f, 632.3f, 252.7f),
                PathNode.HorizontalTo(697.7f),
                PathNode.QuadTo(744.5f, 252.7f, 768.9f, 228.6f),
                PathNode.QuadTo(793.4f, 204.6f, 793.4f, 157.1f),
                PathNode.VerticalTo(-52.8f),
                PathNode.QuadTo(793.4f, -79.2f, 807.4f, -92.8f),
                PathNode.QuadTo(821.5f, -106.4f, 847f, -106.4f),
                PathNode.HorizontalTo(997.7f),
                PathNode.QuadTo(1062.1f, -106.4f, 1096.2f, -72.6f),
                PathNode.QuadTo(1130.4f, -38.8f, 1130.4f, 26.3f),
                PathNode.VerticalTo(404f),
                PathNode.QuadTo(1130.4f, 456.4f, 1112.2f, 495.3f),
                PathNode.QuadTo(1094.1f, 534.3f, 1054f, 568.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.8018f,
            autoMirror = false,
        )
        return _homeScreenSurfaceMedium!!
    }

private var _homeScreenSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.HomeFill: ImageVector
    get() {
        if (_homeScreenSurfaceDemibold != null) return _homeScreenSurfaceDemibold!!
        _homeScreenSurfaceDemibold = buildHyperOSIcon(
            name = "HomeFill.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(1056.1f, 571.7f),
                PathNode.LineTo(758.8f, 821.4f),
                PathNode.QuadTo(712.1f, 860.6f, 665f, 860.6f),
                PathNode.QuadTo(617.9f, 860.6f, 571.2f, 821.4f),
                PathNode.LineTo(273.9f, 571.7f),
                PathNode.QuadTo(233.1f, 537.4f, 214.5f, 497.3f),
                PathNode.QuadTo(195.8f, 457.3f, 195.8f, 404f),
                PathNode.VerticalTo(24.4f),
                PathNode.QuadTo(195.8f, -40.9f, 230.5f, -75.5f),
                PathNode.QuadTo(265.1f, -110.2f, 330.4f, -110.2f),
                PathNode.HorizontalTo(490f),
                PathNode.QuadTo(515.1f, -110.2f, 528.9f, -96.9f),
                PathNode.QuadTo(542.7f, -83.6f, 542.7f, -57.5f),
                PathNode.VerticalTo(157.8f),
                PathNode.QuadTo(542.7f, 202.9f, 565.6f, 225.2f),
                PathNode.QuadTo(588.4f, 247.6f, 632.5f, 247.6f),
                PathNode.HorizontalTo(697.5f),
                PathNode.QuadTo(742f, 247.6f, 764.7f, 225.2f),
                PathNode.QuadTo(787.3f, 202.9f, 787.3f, 157.8f),
                PathNode.VerticalTo(-57.5f),
                PathNode.QuadTo(787.3f, -83.6f, 801.1f, -96.9f),
                PathNode.QuadTo(814.9f, -110.2f, 840f, -110.2f),
                PathNode.HorizontalTo(999.6f),
                PathNode.QuadTo(1064.9f, -110.2f, 1099.5f, -75.8f),
                PathNode.QuadTo(1134.2f, -41.4f, 1134.2f, 24.4f),
                PathNode.VerticalTo(404f),
                PathNode.QuadTo(1134.2f, 457.3f, 1115.5f, 497.3f),
                PathNode.QuadTo(1096.9f, 537.4f, 1056.1f, 571.7f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.8964f,
            autoMirror = false,
        )
        return _homeScreenSurfaceDemibold!!
    }

private var _homeScreenSurfaceDemibold: ImageVector? = null

