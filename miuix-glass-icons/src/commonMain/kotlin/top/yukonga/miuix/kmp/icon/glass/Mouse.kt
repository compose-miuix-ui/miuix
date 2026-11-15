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

val MiuixIcons.Glass.Mouse: ImageVector
    get() = MiuixIcons.Glass.Regular.Mouse

val MiuixIcons.Glass.Light.Mouse: ImageVector
    get() {
        if (_mouseSurfaceLight != null) return _mouseSurfaceLight!!
        _mouseSurfaceLight = buildHyperOSIcon(
            name = "Mouse.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(274.2f, 550.3f),
                PathNode.VerticalTo(199.7f),
                PathNode.QuadTo(274.2f, 102.9f, 322.1f, 12.3f),
                PathNode.QuadTo(370f, -78.2f, 459.1f, -135.4f),
                PathNode.QuadTo(548.1f, -192.6f, 665f, -192.6f),
                PathNode.QuadTo(777.9f, -192.6f, 867.4f, -135.9f),
                PathNode.QuadTo(957f, -79.2f, 1006.4f, 7.2f),
                PathNode.QuadTo(1055.8f, 93.7f, 1055.8f, 178.2f),
                PathNode.VerticalTo(550.3f),
                PathNode.QuadTo(1055.8f, 656.7f, 1003.3f, 746.5f),
                PathNode.QuadTo(950.9f, 836.2f, 861.1f, 888.9f),
                PathNode.QuadTo(771.4f, 941.6f, 665f, 941.6f),
                PathNode.QuadTo(557.7f, 941.6f, 467.9f, 888.9f),
                PathNode.QuadTo(378.1f, 836.2f, 326.2f, 746.7f),
                PathNode.QuadTo(274.2f, 657.1f, 274.2f, 550.3f),
                PathNode.Close,
                PathNode.MoveTo(699.1f, 773.3f),
                PathNode.VerticalTo(458.7f),
                PathNode.QuadTo(699.1f, 446.6f, 692.8f, 440f),
                PathNode.QuadTo(686.4f, 433.3f, 673.8f, 433.3f),
                PathNode.HorizontalTo(656.2f),
                PathNode.QuadTo(644.2f, 433.3f, 637.5f, 440f),
                PathNode.QuadTo(630.9f, 446.6f, 630.9f, 458.7f),
                PathNode.VerticalTo(773.3f),
                PathNode.QuadTo(630.9f, 786f, 637.5f, 792.3f),
                PathNode.QuadTo(644.2f, 798.7f, 656.2f, 798.7f),
                PathNode.HorizontalTo(673.8f),
                PathNode.QuadTo(686.4f, 798.7f, 692.8f, 792.3f),
                PathNode.QuadTo(699.1f, 786f, 699.1f, 773.3f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.1667f,
            autoMirror = false,
        )
        return _mouseSurfaceLight!!
    }

private var _mouseSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Mouse: ImageVector
    get() {
        if (_mouseSurfaceNormal != null) return _mouseSurfaceNormal!!
        _mouseSurfaceNormal = buildHyperOSIcon(
            name = "Mouse.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(267.2f, 549.4f),
                PathNode.VerticalTo(200.6f),
                PathNode.QuadTo(267.2f, 102.3f, 315.8f, 10f),
                PathNode.QuadTo(364.5f, -82.2f, 455.2f, -140.3f),
                PathNode.QuadTo(546f, -198.4f, 665f, -198.4f),
                PathNode.QuadTo(780f, -198.4f, 871.3f, -140.8f),
                PathNode.QuadTo(962.5f, -83.2f, 1012.7f, 5f),
                PathNode.QuadTo(1062.8f, 93.2f, 1062.8f, 179.4f),
                PathNode.VerticalTo(549.4f),
                PathNode.QuadTo(1062.8f, 657.6f, 1009.3f, 748.9f),
                PathNode.QuadTo(955.8f, 840.2f, 864.5f, 893.8f),
                PathNode.QuadTo(773.2f, 947.4f, 665f, 947.4f),
                PathNode.QuadTo(555.8f, 947.4f, 464.5f, 893.8f),
                PathNode.QuadTo(373.2f, 840.2f, 320.2f, 749f),
                PathNode.QuadTo(267.2f, 657.7f, 267.2f, 549.4f),
                PathNode.Close,
                PathNode.MoveTo(710.7f, 772.4f),
                PathNode.VerticalTo(459.6f),
                PathNode.QuadTo(710.7f, 442.8f, 701.9f, 433.5f),
                PathNode.QuadTo(693f, 424.2f, 675.3f, 424.2f),
                PathNode.HorizontalTo(654.7f),
                PathNode.QuadTo(637.9f, 424.2f, 628.6f, 433.5f),
                PathNode.QuadTo(619.3f, 442.8f, 619.3f, 459.6f),
                PathNode.VerticalTo(772.4f),
                PathNode.QuadTo(619.3f, 790.1f, 628.6f, 799f),
                PathNode.QuadTo(637.9f, 807.8f, 654.7f, 807.8f),
                PathNode.HorizontalTo(675.3f),
                PathNode.QuadTo(693f, 807.8f, 701.9f, 799f),
                PathNode.QuadTo(710.7f, 790.1f, 710.7f, 772.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.1667f,
            autoMirror = false,
        )
        return _mouseSurfaceNormal!!
    }

private var _mouseSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Mouse: ImageVector
    get() {
        if (_mouseSurfaceRegular != null) return _mouseSurfaceRegular!!
        _mouseSurfaceRegular = buildHyperOSIcon(
            name = "Mouse.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(264f, 549f),
                PathNode.VerticalTo(201f),
                PathNode.QuadTo(264f, 102f, 313f, 9f),
                PathNode.QuadTo(362f, -84f, 453.5f, -142.5f),
                PathNode.QuadTo(545f, -201f, 665f, -201f),
                PathNode.QuadTo(781f, -201f, 873f, -143f),
                PathNode.QuadTo(965f, -85f, 1015.5f, 4f),
                PathNode.QuadTo(1066f, 93f, 1066f, 180f),
                PathNode.VerticalTo(549f),
                PathNode.QuadTo(1066f, 658f, 1012f, 750f),
                PathNode.QuadTo(958f, 842f, 866f, 896f),
                PathNode.QuadTo(774f, 950f, 665f, 950f),
                PathNode.QuadTo(555f, 950f, 463f, 896f),
                PathNode.QuadTo(371f, 842f, 317.5f, 750f),
                PathNode.QuadTo(264f, 658f, 264f, 549f),
                PathNode.Close,
                PathNode.MoveTo(716f, 772f),
                PathNode.VerticalTo(460f),
                PathNode.QuadTo(716f, 441f, 706f, 430.5f),
                PathNode.QuadTo(696f, 420f, 676f, 420f),
                PathNode.HorizontalTo(654f),
                PathNode.QuadTo(635f, 420f, 624.5f, 430.5f),
                PathNode.QuadTo(614f, 441f, 614f, 460f),
                PathNode.VerticalTo(772f),
                PathNode.QuadTo(614f, 792f, 624.5f, 802f),
                PathNode.QuadTo(635f, 812f, 654f, 812f),
                PathNode.HorizontalTo(676f),
                PathNode.QuadTo(696f, 812f, 706f, 802f),
                PathNode.QuadTo(716f, 792f, 716f, 772f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.1667f,
            autoMirror = false,
        )
        return _mouseSurfaceRegular!!
    }

private var _mouseSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Mouse: ImageVector
    get() {
        if (_mouseSurfaceMedium != null) return _mouseSurfaceMedium!!
        _mouseSurfaceMedium = buildHyperOSIcon(
            name = "Mouse.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(258.1f, 548.2f),
                PathNode.VerticalTo(201.8f),
                PathNode.QuadTo(258.1f, 101.5f, 307.9f, 7.3f),
                PathNode.QuadTo(357.7f, -87f, 450.4f, -146.4f),
                PathNode.QuadTo(543.1f, -205.9f, 665f, -205.9f),
                PathNode.QuadTo(782.9f, -205.9f, 876.1f, -146.9f),
                PathNode.QuadTo(969.3f, -88f, 1020.6f, 2.4f),
                PathNode.QuadTo(1071.9f, 92.8f, 1071.9f, 181.1f),
                PathNode.VerticalTo(548.2f),
                PathNode.QuadTo(1071.9f, 658.7f, 1017.1f, 752f),
                PathNode.QuadTo(962.3f, 845.3f, 869f, 900.1f),
                PathNode.QuadTo(775.6f, 954.9f, 665f, 954.9f),
                PathNode.QuadTo(553.4f, 954.9f, 460f, 900.2f),
                PathNode.QuadTo(366.7f, 845.5f, 312.4f, 752.2f),
                PathNode.QuadTo(258.1f, 658.8f, 258.1f, 548.2f),
                PathNode.Close,
                PathNode.MoveTo(723.6f, 772.3f),
                PathNode.VerticalTo(457.3f),
                PathNode.QuadTo(723.6f, 435.7f, 712.1f, 423.8f),
                PathNode.QuadTo(700.6f, 411.9f, 678.2f, 411.9f),
                PathNode.HorizontalTo(651.8f),
                PathNode.QuadTo(630.3f, 411.9f, 618.4f, 423.8f),
                PathNode.QuadTo(606.4f, 435.7f, 606.4f, 457.3f),
                PathNode.VerticalTo(772.3f),
                PathNode.QuadTo(606.4f, 794.7f, 618.4f, 806.2f),
                PathNode.QuadTo(630.3f, 817.7f, 651.8f, 817.7f),
                PathNode.HorizontalTo(678.2f),
                PathNode.QuadTo(700.6f, 817.7f, 712.1f, 806.2f),
                PathNode.QuadTo(723.6f, 794.7f, 723.6f, 772.3f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.1667f,
            autoMirror = false,
        )
        return _mouseSurfaceMedium!!
    }

private var _mouseSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Mouse: ImageVector
    get() {
        if (_mouseSurfaceDemibold != null) return _mouseSurfaceDemibold!!
        _mouseSurfaceDemibold = buildHyperOSIcon(
            name = "Mouse.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(253.9f, 547.6f),
                PathNode.VerticalTo(202.4f),
                PathNode.QuadTo(253.9f, 101.1f, 304.3f, 6f),
                PathNode.QuadTo(354.6f, -89.1f, 448.2f, -149.2f),
                PathNode.QuadTo(541.8f, -209.3f, 665.1f, -209.3f),
                PathNode.QuadTo(784.2f, -209.3f, 878.3f, -149.7f),
                PathNode.QuadTo(972.4f, -90.1f, 1024.2f, 1.3f),
                PathNode.QuadTo(1076.1f, 92.6f, 1076.1f, 181.8f),
                PathNode.VerticalTo(547.6f),
                PathNode.QuadTo(1076.1f, 659.2f, 1020.7f, 753.5f),
                PathNode.QuadTo(965.4f, 847.7f, 871.1f, 903f),
                PathNode.QuadTo(776.8f, 958.3f, 665f, 958.3f),
                PathNode.QuadTo(552.2f, 958.3f, 457.9f, 903.1f),
                PathNode.QuadTo(363.6f, 848f, 308.8f, 753.7f),
                PathNode.QuadTo(253.9f, 659.4f, 253.9f, 547.6f),
                PathNode.Close,
                PathNode.MoveTo(728.9f, 772.5f),
                PathNode.VerticalTo(455.4f),
                PathNode.QuadTo(728.9f, 432f, 716.3f, 419.1f),
                PathNode.QuadTo(703.8f, 406.2f, 679.7f, 406.2f),
                PathNode.HorizontalTo(650.3f),
                PathNode.QuadTo(627f, 406.2f, 614f, 419.1f),
                PathNode.QuadTo(601.1f, 432f, 601.1f, 455.4f),
                PathNode.VerticalTo(772.5f),
                PathNode.QuadTo(601.1f, 796.6f, 614f, 809.1f),
                PathNode.QuadTo(627f, 821.6f, 650.3f, 821.6f),
                PathNode.HorizontalTo(679.7f),
                PathNode.QuadTo(703.8f, 821.6f, 716.3f, 809.1f),
                PathNode.QuadTo(728.9f, 796.6f, 728.9f, 772.5f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.1667f,
            autoMirror = false,
        )
        return _mouseSurfaceDemibold!!
    }

private var _mouseSurfaceDemibold: ImageVector? = null

