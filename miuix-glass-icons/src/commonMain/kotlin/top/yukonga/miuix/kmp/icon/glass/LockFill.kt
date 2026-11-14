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

val MiuixIcons.Glass.LockFill: ImageVector
    get() = MiuixIcons.Glass.Regular.LockFill

val MiuixIcons.Glass.Light.LockFill: ImageVector
    get() {
        if (_lockScreenSurfaceLight != null) return _lockScreenSurfaceLight!!
        _lockScreenSurfaceLight = buildHyperOSIcon(
            name = "LockFill.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(847.3f, 652f),
                PathNode.VerticalTo(518.7f),
                PathNode.HorizontalTo(482.7f),
                PathNode.VerticalTo(652f),
                PathNode.QuadTo(482.7f, 701.6f, 507.4f, 743.7f),
                PathNode.QuadTo(532.2f, 785.8f, 573.8f, 810.1f),
                PathNode.QuadTo(615.4f, 834.3f, 665f, 834.3f),
                PathNode.QuadTo(714.6f, 834.3f, 756.2f, 810.1f),
                PathNode.QuadTo(797.8f, 785.8f, 822.6f, 743.7f),
                PathNode.QuadTo(847.3f, 701.6f, 847.3f, 652f),
                PathNode.Close,
                PathNode.MoveTo(413.3f, 652f),
                PathNode.VerticalTo(516f),
                PathNode.QuadTo(334.7f, 507.4f, 300f, 440.8f),
                PathNode.QuadTo(288.9f, 419.6f, 286.6f, 388.3f),
                PathNode.QuadTo(284.3f, 357.1f, 284.3f, 281f),
                PathNode.VerticalTo(123f),
                PathNode.QuadTo(284.3f, 46.9f, 286.6f, 16.2f),
                PathNode.QuadTo(288.9f, -14.6f, 300f, -35.8f),
                PathNode.QuadTo(321.2f, -77.2f, 363.2f, -98f),
                PathNode.QuadTo(384f, -109.1f, 414.9f, -111.9f),
                PathNode.QuadTo(445.9f, -114.7f, 522f, -114.7f),
                PathNode.HorizontalTo(808f),
                PathNode.QuadTo(884.1f, -114.7f, 915.1f, -111.9f),
                PathNode.QuadTo(946f, -109.1f, 966.8f, -98f),
                PathNode.QuadTo(1009.2f, -77.2f, 1030f, -35.8f),
                PathNode.QuadTo(1041.1f, -14.6f, 1043.4f, 16.2f),
                PathNode.QuadTo(1045.7f, 46.9f, 1045.7f, 123f),
                PathNode.VerticalTo(281f),
                PathNode.QuadTo(1045.7f, 357.1f, 1043.4f, 388.3f),
                PathNode.QuadTo(1041.1f, 419.6f, 1030f, 440.8f),
                PathNode.QuadTo(1013.4f, 474.3f, 984.9f, 492.8f),
                PathNode.QuadTo(956.4f, 511.2f, 916.7f, 516f),
                PathNode.VerticalTo(652f),
                PathNode.QuadTo(916.7f, 720.4f, 882.9f, 778.3f),
                PathNode.QuadTo(849.2f, 836.2f, 791.3f, 869.9f),
                PathNode.QuadTo(733.4f, 903.7f, 665f, 903.7f),
                PathNode.QuadTo(596.6f, 903.7f, 538.7f, 869.9f),
                PathNode.QuadTo(480.8f, 836.2f, 447.1f, 778.3f),
                PathNode.QuadTo(413.3f, 720.4f, 413.3f, 652f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1061.1667f,
            autoMirror = false,
        )
        return _lockScreenSurfaceLight!!
    }

private var _lockScreenSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.LockFill: ImageVector
    get() {
        if (_lockScreenSurfaceNormal != null) return _lockScreenSurfaceNormal!!
        _lockScreenSurfaceNormal = buildHyperOSIcon(
            name = "LockFill.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(838.2f, 652f),
                PathNode.VerticalTo(527.8f),
                PathNode.HorizontalTo(491.8f),
                PathNode.VerticalTo(652f),
                PathNode.QuadTo(491.8f, 699.1f, 515.4f, 739.1f),
                PathNode.QuadTo(538.9f, 779.1f, 578.4f, 802.1f),
                PathNode.QuadTo(617.9f, 825.2f, 665f, 825.2f),
                PathNode.QuadTo(712.1f, 825.2f, 751.6f, 802.1f),
                PathNode.QuadTo(791.1f, 779.1f, 814.6f, 739.1f),
                PathNode.QuadTo(838.2f, 699.1f, 838.2f, 652f),
                PathNode.Close,
                PathNode.MoveTo(404.2f, 652f),
                PathNode.VerticalTo(524.2f),
                PathNode.QuadTo(327.3f, 512.6f, 291.8f, 445.1f),
                PathNode.QuadTo(280f, 422.6f, 277.6f, 390.2f),
                PathNode.QuadTo(275.2f, 357.7f, 275.2f, 281f),
                PathNode.VerticalTo(123f),
                PathNode.QuadTo(275.2f, 46.3f, 277.6f, 14.3f),
                PathNode.QuadTo(280f, -17.6f, 291.8f, -40.1f),
                PathNode.QuadTo(314.2f, -83.9f, 358.9f, -106.2f),
                PathNode.QuadTo(381.3f, -118f, 413.3f, -120.9f),
                PathNode.QuadTo(445.3f, -123.8f, 522f, -123.8f),
                PathNode.HorizontalTo(808f),
                PathNode.QuadTo(884.7f, -123.8f, 916.7f, -120.9f),
                PathNode.QuadTo(948.7f, -118f, 971.1f, -106.2f),
                PathNode.QuadTo(1015.9f, -83.9f, 1038.2f, -40.1f),
                PathNode.QuadTo(1050f, -17.6f, 1052.4f, 14.3f),
                PathNode.QuadTo(1054.8f, 46.3f, 1054.8f, 123f),
                PathNode.VerticalTo(281f),
                PathNode.QuadTo(1054.8f, 357.7f, 1052.4f, 390.2f),
                PathNode.QuadTo(1050f, 422.6f, 1038.2f, 445.1f),
                PathNode.QuadTo(1021.4f, 478.9f, 992.9f, 498.4f),
                PathNode.QuadTo(964.4f, 517.9f, 925.8f, 524.2f),
                PathNode.VerticalTo(652f),
                PathNode.QuadTo(925.8f, 722.9f, 890.9f, 782.9f),
                PathNode.QuadTo(855.9f, 842.9f, 795.9f, 877.9f),
                PathNode.QuadTo(735.9f, 912.8f, 665f, 912.8f),
                PathNode.QuadTo(594.1f, 912.8f, 534.1f, 877.9f),
                PathNode.QuadTo(474.1f, 842.9f, 439.1f, 782.9f),
                PathNode.QuadTo(404.2f, 722.9f, 404.2f, 652f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1061.1667f,
            autoMirror = false,
        )
        return _lockScreenSurfaceNormal!!
    }

private var _lockScreenSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.LockFill: ImageVector
    get() {
        if (_lockScreenSurfaceRegular != null) return _lockScreenSurfaceRegular!!
        _lockScreenSurfaceRegular = buildHyperOSIcon(
            name = "LockFill.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(834f, 652f),
                PathNode.VerticalTo(532f),
                PathNode.HorizontalTo(496f),
                PathNode.VerticalTo(652f),
                PathNode.QuadTo(496f, 698f, 519f, 737f),
                PathNode.QuadTo(542f, 776f, 580.5f, 798.5f),
                PathNode.QuadTo(619f, 821f, 665f, 821f),
                PathNode.QuadTo(711f, 821f, 749.5f, 798.5f),
                PathNode.QuadTo(788f, 776f, 811f, 737f),
                PathNode.QuadTo(834f, 698f, 834f, 652f),
                PathNode.Close,
                PathNode.MoveTo(400f, 652f),
                PathNode.VerticalTo(528f),
                PathNode.QuadTo(324f, 515f, 288f, 447f),
                PathNode.QuadTo(276f, 424f, 273.5f, 391f),
                PathNode.QuadTo(271f, 358f, 271f, 281f),
                PathNode.VerticalTo(123f),
                PathNode.QuadTo(271f, 46f, 273.5f, 13.5f),
                PathNode.QuadTo(276f, -19f, 288f, -42f),
                PathNode.QuadTo(311f, -87f, 357f, -110f),
                PathNode.QuadTo(380f, -122f, 412.5f, -125f),
                PathNode.QuadTo(445f, -128f, 522f, -128f),
                PathNode.HorizontalTo(808f),
                PathNode.QuadTo(885f, -128f, 917.5f, -125f),
                PathNode.QuadTo(950f, -122f, 973f, -110f),
                PathNode.QuadTo(1019f, -87f, 1042f, -42f),
                PathNode.QuadTo(1054f, -19f, 1056.5f, 13.5f),
                PathNode.QuadTo(1059f, 46f, 1059f, 123f),
                PathNode.VerticalTo(281f),
                PathNode.QuadTo(1059f, 358f, 1056.5f, 391f),
                PathNode.QuadTo(1054f, 424f, 1042f, 447f),
                PathNode.QuadTo(1025f, 481f, 996.5f, 501f),
                PathNode.QuadTo(968f, 521f, 930f, 528f),
                PathNode.VerticalTo(652f),
                PathNode.QuadTo(930f, 724f, 894.5f, 785f),
                PathNode.QuadTo(859f, 846f, 798f, 881.5f),
                PathNode.QuadTo(737f, 917f, 665f, 917f),
                PathNode.QuadTo(593f, 917f, 532f, 881.5f),
                PathNode.QuadTo(471f, 846f, 435.5f, 785f),
                PathNode.QuadTo(400f, 724f, 400f, 652f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1061.1667f,
            autoMirror = false,
        )
        return _lockScreenSurfaceRegular!!
    }

private var _lockScreenSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.LockFill: ImageVector
    get() {
        if (_lockScreenSurfaceMedium != null) return _lockScreenSurfaceMedium!!
        _lockScreenSurfaceMedium = buildHyperOSIcon(
            name = "LockFill.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(825.6f, 651.2f),
                PathNode.VerticalTo(539.6f),
                PathNode.HorizontalTo(504.4f),
                PathNode.VerticalTo(651.2f),
                PathNode.QuadTo(504.4f, 695f, 526.2f, 732f),
                PathNode.QuadTo(547.9f, 769f, 584.7f, 790.4f),
                PathNode.QuadTo(621.4f, 811.8f, 665f, 811.8f),
                PathNode.QuadTo(708.6f, 811.8f, 745.3f, 790.4f),
                PathNode.QuadTo(782.1f, 769f, 803.8f, 732f),
                PathNode.QuadTo(825.6f, 695f, 825.6f, 651.2f),
                PathNode.Close,
                PathNode.MoveTo(392.7f, 651.2f),
                PathNode.VerticalTo(533.9f),
                PathNode.QuadTo(318.1f, 519.1f, 281.8f, 450.5f),
                PathNode.QuadTo(269.2f, 426.4f, 266.6f, 392.5f),
                PathNode.QuadTo(264f, 358.5f, 264f, 281.3f),
                PathNode.VerticalTo(123.8f),
                PathNode.QuadTo(264f, 46.5f, 266.6f, 13.1f),
                PathNode.QuadTo(269.2f, -20.4f, 281.8f, -44.4f),
                PathNode.QuadTo(306.1f, -91.9f, 354f, -115.7f),
                PathNode.QuadTo(378.1f, -128.5f, 411.6f, -131.6f),
                PathNode.QuadTo(445f, -134.8f, 522.3f, -134.8f),
                PathNode.HorizontalTo(807.7f),
                PathNode.QuadTo(885f, -134.8f, 918.4f, -131.6f),
                PathNode.QuadTo(951.9f, -128.5f, 976f, -115.7f),
                PathNode.QuadTo(1023.9f, -91.9f, 1048.2f, -44.4f),
                PathNode.QuadTo(1060.8f, -20.4f, 1063.4f, 13.1f),
                PathNode.QuadTo(1066f, 46.5f, 1066f, 123.8f),
                PathNode.VerticalTo(281.3f),
                PathNode.QuadTo(1066f, 358.5f, 1063.4f, 392.5f),
                PathNode.QuadTo(1060.8f, 426.4f, 1048.2f, 450.5f),
                PathNode.QuadTo(1031.2f, 484.5f, 1003f, 505.3f),
                PathNode.QuadTo(974.8f, 526.1f, 937.3f, 533.9f),
                PathNode.VerticalTo(651.2f),
                PathNode.QuadTo(937.3f, 725.4f, 900.7f, 788.1f),
                PathNode.QuadTo(864.1f, 850.9f, 801.5f, 887.3f),
                PathNode.QuadTo(738.9f, 923.8f, 665f, 923.8f),
                PathNode.QuadTo(591.1f, 923.8f, 528.5f, 887.3f),
                PathNode.QuadTo(465.9f, 850.9f, 429.3f, 788.1f),
                PathNode.QuadTo(392.7f, 725.4f, 392.7f, 651.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1061.1667f,
            autoMirror = false,
        )
        return _lockScreenSurfaceMedium!!
    }

private var _lockScreenSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.LockFill: ImageVector
    get() {
        if (_lockScreenSurfaceDemibold != null) return _lockScreenSurfaceDemibold!!
        _lockScreenSurfaceDemibold = buildHyperOSIcon(
            name = "LockFill.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(819.8f, 650.6f),
                PathNode.VerticalTo(544.9f),
                PathNode.HorizontalTo(510.2f),
                PathNode.VerticalTo(650.6f),
                PathNode.QuadTo(510.2f, 692.9f, 531.2f, 728.5f),
                PathNode.QuadTo(552.1f, 764.1f, 587.6f, 784.7f),
                PathNode.QuadTo(623.1f, 805.4f, 665f, 805.4f),
                PathNode.QuadTo(706.9f, 805.4f, 742.4f, 784.7f),
                PathNode.QuadTo(777.9f, 764.1f, 798.8f, 728.5f),
                PathNode.QuadTo(819.8f, 692.9f, 819.8f, 650.6f),
                PathNode.Close,
                PathNode.MoveTo(387.6f, 650.6f),
                PathNode.VerticalTo(538.1f),
                PathNode.QuadTo(313.9f, 521.9f, 277.4f, 453f),
                PathNode.QuadTo(264.5f, 428.1f, 261.8f, 393.5f),
                PathNode.QuadTo(259.1f, 358.9f, 259.1f, 281.5f),
                PathNode.VerticalTo(124.4f),
                PathNode.QuadTo(259.1f, 46.9f, 261.8f, 12.8f),
                PathNode.QuadTo(264.5f, -21.3f, 277.4f, -46.1f),
                PathNode.QuadTo(302.7f, -95.3f, 351.9f, -119.6f),
                PathNode.QuadTo(376.8f, -133f, 410.9f, -136.3f),
                PathNode.QuadTo(445f, -139.5f, 522.5f, -139.5f),
                PathNode.HorizontalTo(807.5f),
                PathNode.QuadTo(885f, -139.5f, 919.1f, -136.3f),
                PathNode.QuadTo(953.2f, -133f, 978.1f, -119.6f),
                PathNode.QuadTo(1027.3f, -95.3f, 1052.6f, -46.1f),
                PathNode.QuadTo(1065.5f, -21.3f, 1068.2f, 12.8f),
                PathNode.QuadTo(1070.9f, 46.9f, 1070.9f, 124.4f),
                PathNode.VerticalTo(281.5f),
                PathNode.QuadTo(1070.9f, 358.9f, 1068.2f, 393.5f),
                PathNode.QuadTo(1065.5f, 428.1f, 1052.6f, 453f),
                PathNode.QuadTo(1035.6f, 487f, 1007.5f, 508.4f),
                PathNode.QuadTo(979.5f, 529.7f, 942.4f, 538.1f),
                PathNode.VerticalTo(650.6f),
                PathNode.QuadTo(942.4f, 726.3f, 905.1f, 790.3f),
                PathNode.QuadTo(867.7f, 854.3f, 804f, 891.4f),
                PathNode.QuadTo(740.2f, 928.5f, 665f, 928.5f),
                PathNode.QuadTo(589.8f, 928.5f, 526f, 891.4f),
                PathNode.QuadTo(462.3f, 854.3f, 424.9f, 790.3f),
                PathNode.QuadTo(387.6f, 726.3f, 387.6f, 650.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1061.1667f,
            autoMirror = false,
        )
        return _lockScreenSurfaceDemibold!!
    }

private var _lockScreenSurfaceDemibold: ImageVector? = null

