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

val MiuixIcons.Glass.NotificationsStatusBar: ImageVector
    get() = MiuixIcons.Glass.Regular.NotificationsStatusBar

val MiuixIcons.Glass.Light.NotificationsStatusBar: ImageVector
    get() {
        if (_notificationsStatusBarSurfaceLight != null) return _notificationsStatusBarSurfaceLight!!
        _notificationsStatusBarSurfaceLight = buildHyperOSIcon(
            name = "NotificationsStatusBar.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(877.3f, 707f),
                PathNode.QuadTo(877.3f, 671.6f, 894.6f, 642.2f),
                PathNode.QuadTo(911.8f, 612.8f, 941.2f, 595.6f),
                PathNode.QuadTo(970.6f, 578.3f, 1006f, 578.3f),
                PathNode.QuadTo(1041.4f, 578.3f, 1071.3f, 595.6f),
                PathNode.QuadTo(1101.2f, 612.8f, 1118.9f, 642.4f),
                PathNode.QuadTo(1136.7f, 672f, 1136.7f, 707f),
                PathNode.QuadTo(1136.7f, 742.4f, 1118.9f, 772.3f),
                PathNode.QuadTo(1101.2f, 802.2f, 1071.3f, 819.9f),
                PathNode.QuadTo(1041.4f, 837.7f, 1006f, 837.7f),
                PathNode.QuadTo(971f, 837.7f, 941.4f, 819.9f),
                PathNode.QuadTo(911.8f, 802.2f, 894.6f, 772.3f),
                PathNode.QuadTo(877.3f, 742.4f, 877.3f, 707f),
                PathNode.Close,
                PathNode.MoveTo(315.2f, 791f),
                PathNode.QuadTo(271.8f, 768.2f, 249f, 724.8f),
                PathNode.QuadTo(236.9f, 702f, 234.1f, 669.1f),
                PathNode.QuadTo(231.3f, 636.1f, 231.3f, 555f),
                PathNode.VerticalTo(185f),
                PathNode.QuadTo(231.3f, 104.4f, 234.1f, 71.2f),
                PathNode.QuadTo(236.9f, 38f, 249f, 16.2f),
                PathNode.QuadTo(270.8f, -28.2f, 315.2f, -51f),
                PathNode.QuadTo(338f, -62.1f, 370.9f, -64.9f),
                PathNode.QuadTo(403.9f, -67.7f, 484f, -67.7f),
                PathNode.HorizontalTo(855f),
                PathNode.QuadTo(936.1f, -67.7f, 969.1f, -64.9f),
                PathNode.QuadTo(1002f, -62.1f, 1023.8f, -51f),
                PathNode.QuadTo(1069.2f, -28.2f, 1091f, 16.2f),
                PathNode.QuadTo(1102.1f, 38f, 1104.9f, 71.2f),
                PathNode.QuadTo(1107.7f, 104.4f, 1107.7f, 185f),
                PathNode.VerticalTo(561.9f),
                PathNode.QuadTo(1060.9f, 530.1f, 1006.6f, 530.1f),
                PathNode.QuadTo(957.3f, 530.1f, 916.7f, 553.9f),
                PathNode.QuadTo(876.1f, 577.7f, 852.6f, 618.6f),
                PathNode.QuadTo(829.1f, 659.4f, 829.1f, 707.6f),
                PathNode.QuadTo(829.1f, 734.4f, 837.4f, 760.7f),
                PathNode.QuadTo(845.8f, 786.9f, 860.9f, 808.7f),
                PathNode.HorizontalTo(484f),
                PathNode.QuadTo(403.9f, 808.7f, 370.9f, 805.9f),
                PathNode.QuadTo(338f, 803.1f, 315.2f, 791f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -17.3333f,
            translationY = 1051.6667f,
            autoMirror = false,
        )
        return _notificationsStatusBarSurfaceLight!!
    }

private var _notificationsStatusBarSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.NotificationsStatusBar: ImageVector
    get() {
        if (_notificationsStatusBarSurfaceNormal != null) return _notificationsStatusBarSurfaceNormal!!
        _notificationsStatusBarSurfaceNormal = buildHyperOSIcon(
            name = "NotificationsStatusBar.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(868.2f, 707f),
                PathNode.QuadTo(868.2f, 669.1f, 886.6f, 637.6f),
                PathNode.QuadTo(905.1f, 606.1f, 936.6f, 587.6f),
                PathNode.QuadTo(968.1f, 569.2f, 1006f, 569.2f),
                PathNode.QuadTo(1043.9f, 569.2f, 1075.9f, 587.6f),
                PathNode.QuadTo(1107.9f, 606.1f, 1126.9f, 637.7f),
                PathNode.QuadTo(1145.8f, 669.3f, 1145.8f, 707f),
                PathNode.QuadTo(1145.8f, 744.9f, 1126.9f, 776.9f),
                PathNode.QuadTo(1107.9f, 808.9f, 1075.9f, 827.9f),
                PathNode.QuadTo(1043.9f, 846.8f, 1006f, 846.8f),
                PathNode.QuadTo(968.3f, 846.8f, 936.7f, 827.9f),
                PathNode.QuadTo(905.1f, 808.9f, 886.6f, 776.9f),
                PathNode.QuadTo(868.2f, 744.9f, 868.2f, 707f),
                PathNode.Close,
                PathNode.MoveTo(310.9f, 799.2f),
                PathNode.QuadTo(265.1f, 774.9f, 240.8f, 729.1f),
                PathNode.QuadTo(228f, 704.7f, 225.1f, 670.7f),
                PathNode.QuadTo(222.2f, 636.7f, 222.2f, 555f),
                PathNode.VerticalTo(185f),
                PathNode.QuadTo(222.2f, 104.1f, 225.1f, 69.7f),
                PathNode.QuadTo(228f, 35.3f, 240.8f, 11.9f),
                PathNode.QuadTo(264.1f, -34.9f, 310.9f, -59.2f),
                PathNode.QuadTo(335.3f, -71f, 369.3f, -73.9f),
                PathNode.QuadTo(403.3f, -76.8f, 484f, -76.8f),
                PathNode.HorizontalTo(855f),
                PathNode.QuadTo(936.7f, -76.8f, 970.7f, -73.9f),
                PathNode.QuadTo(1004.7f, -71f, 1028.1f, -59.2f),
                PathNode.QuadTo(1075.9f, -34.9f, 1099.2f, 11.9f),
                PathNode.QuadTo(1111f, 35.3f, 1113.9f, 69.7f),
                PathNode.QuadTo(1116.8f, 104.1f, 1116.8f, 185f),
                PathNode.VerticalTo(542f),
                PathNode.QuadTo(1065.8f, 508.7f, 1006.9f, 508.7f),
                PathNode.QuadTo(950.9f, 508.7f, 905.6f, 535.4f),
                PathNode.QuadTo(860.2f, 562.1f, 834f, 607.9f),
                PathNode.QuadTo(807.7f, 653.6f, 807.7f, 707.9f),
                PathNode.QuadTo(807.7f, 736.9f, 816.5f, 765.7f),
                PathNode.QuadTo(825.3f, 794.5f, 841f, 817.8f),
                PathNode.HorizontalTo(484f),
                PathNode.QuadTo(403.3f, 817.8f, 369.3f, 814.9f),
                PathNode.QuadTo(335.3f, 812f, 310.9f, 799.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -17.3333f,
            translationY = 1051.6667f,
            autoMirror = false,
        )
        return _notificationsStatusBarSurfaceNormal!!
    }

private var _notificationsStatusBarSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.NotificationsStatusBar: ImageVector
    get() {
        if (_notificationsStatusBarSurfaceRegular != null) return _notificationsStatusBarSurfaceRegular!!
        _notificationsStatusBarSurfaceRegular = buildHyperOSIcon(
            name = "NotificationsStatusBar.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(864f, 707f),
                PathNode.QuadTo(864f, 668f, 883f, 635.5f),
                PathNode.QuadTo(902f, 603f, 934.5f, 584f),
                PathNode.QuadTo(967f, 565f, 1006f, 565f),
                PathNode.QuadTo(1045f, 565f, 1078f, 584f),
                PathNode.QuadTo(1111f, 603f, 1130.5f, 635.5f),
                PathNode.QuadTo(1150f, 668f, 1150f, 707f),
                PathNode.QuadTo(1150f, 746f, 1130.5f, 779f),
                PathNode.QuadTo(1111f, 812f, 1078f, 831.5f),
                PathNode.QuadTo(1045f, 851f, 1006f, 851f),
                PathNode.QuadTo(967f, 851f, 934.5f, 831.5f),
                PathNode.QuadTo(902f, 812f, 883f, 779f),
                PathNode.QuadTo(864f, 746f, 864f, 707f),
                PathNode.Close,
                PathNode.MoveTo(309f, 803f),
                PathNode.QuadTo(262f, 778f, 237f, 731f),
                PathNode.QuadTo(224f, 706f, 221f, 671.5f),
                PathNode.QuadTo(218f, 637f, 218f, 555f),
                PathNode.VerticalTo(185f),
                PathNode.QuadTo(218f, 104f, 221f, 69f),
                PathNode.QuadTo(224f, 34f, 237f, 10f),
                PathNode.QuadTo(261f, -38f, 309f, -63f),
                PathNode.QuadTo(334f, -75f, 368.5f, -78f),
                PathNode.QuadTo(403f, -81f, 484f, -81f),
                PathNode.HorizontalTo(855f),
                PathNode.QuadTo(937f, -81f, 971.5f, -78f),
                PathNode.QuadTo(1006f, -75f, 1030f, -63f),
                PathNode.QuadTo(1079f, -38f, 1103f, 10f),
                PathNode.QuadTo(1115f, 34f, 1118f, 69f),
                PathNode.QuadTo(1121f, 104f, 1121f, 185f),
                PathNode.VerticalTo(533f),
                PathNode.QuadTo(1068f, 499f, 1007f, 499f),
                PathNode.QuadTo(948f, 499f, 900.5f, 527f),
                PathNode.QuadTo(853f, 555f, 825.5f, 603f),
                PathNode.QuadTo(798f, 651f, 798f, 708f),
                PathNode.QuadTo(798f, 738f, 807f, 768f),
                PathNode.QuadTo(816f, 798f, 832f, 822f),
                PathNode.HorizontalTo(484f),
                PathNode.QuadTo(403f, 822f, 368.5f, 819f),
                PathNode.QuadTo(334f, 816f, 309f, 803f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -17.3333f,
            translationY = 1051.6667f,
            autoMirror = false,
        )
        return _notificationsStatusBarSurfaceRegular!!
    }

private var _notificationsStatusBarSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.NotificationsStatusBar: ImageVector
    get() {
        if (_notificationsStatusBarSurfaceMedium != null) return _notificationsStatusBarSurfaceMedium!!
        _notificationsStatusBarSurfaceMedium = buildHyperOSIcon(
            name = "NotificationsStatusBar.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(855.9f, 707f),
                PathNode.QuadTo(855.9f, 665.8f, 876f, 631.4f),
                PathNode.QuadTo(896.1f, 597.1f, 930.4f, 577f),
                PathNode.QuadTo(964.8f, 556.9f, 1006f, 556.9f),
                PathNode.QuadTo(1047.2f, 556.9f, 1082.1f, 577f),
                PathNode.QuadTo(1116.9f, 597.1f, 1137.5f, 631.4f),
                PathNode.QuadTo(1158.1f, 665.8f, 1158.1f, 707f),
                PathNode.QuadTo(1158.1f, 748.2f, 1137.5f, 783.1f),
                PathNode.QuadTo(1116.9f, 817.9f, 1082.1f, 838.5f),
                PathNode.QuadTo(1047.2f, 859.1f, 1006f, 859.1f),
                PathNode.QuadTo(964.8f, 859.1f, 930.4f, 838.5f),
                PathNode.QuadTo(896.1f, 817.9f, 876f, 783.1f),
                PathNode.QuadTo(855.9f, 748.2f, 855.9f, 707f),
                PathNode.Close,
                PathNode.MoveTo(305.2f, 810.3f),
                PathNode.QuadTo(255.8f, 784.2f, 229.7f, 734.8f),
                PathNode.QuadTo(216.2f, 708.7f, 213f, 673.1f),
                PathNode.QuadTo(209.9f, 637.5f, 209.9f, 555f),
                PathNode.VerticalTo(185f),
                PathNode.QuadTo(209.9f, 103.5f, 213f, 67.4f),
                PathNode.QuadTo(216.2f, 31.3f, 229.7f, 6.2f),
                PathNode.QuadTo(254.8f, -44.2f, 305.2f, -70.3f),
                PathNode.QuadTo(331.3f, -82.8f, 366.9f, -86f),
                PathNode.QuadTo(402.5f, -89.1f, 484f, -89.1f),
                PathNode.HorizontalTo(855f),
                PathNode.QuadTo(937.5f, -89.1f, 973.1f, -86f),
                PathNode.QuadTo(1008.7f, -82.8f, 1033.8f, -70.3f),
                PathNode.QuadTo(1085.2f, -43.7f, 1110.3f, 6.2f),
                PathNode.QuadTo(1122.8f, 31.3f, 1126f, 67.4f),
                PathNode.QuadTo(1129.1f, 103.5f, 1129.1f, 185f),
                PathNode.VerticalTo(524.1f),
                PathNode.QuadTo(1072.6f, 487.1f, 1006.7f, 487.1f),
                PathNode.QuadTo(945.3f, 487.1f, 895f, 516.7f),
                PathNode.QuadTo(844.6f, 546.4f, 815.4f, 597.1f),
                PathNode.QuadTo(786.1f, 647.8f, 786.1f, 707.7f),
                PathNode.QuadTo(786.1f, 740.2f, 795.9f, 772.2f),
                PathNode.QuadTo(805.7f, 804.2f, 823.1f, 830.1f),
                PathNode.HorizontalTo(484f),
                PathNode.QuadTo(402.5f, 830.1f, 366.9f, 827f),
                PathNode.QuadTo(331.3f, 823.8f, 305.2f, 810.3f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -17.3333f,
            translationY = 1051.6667f,
            autoMirror = false,
        )
        return _notificationsStatusBarSurfaceMedium!!
    }

private var _notificationsStatusBarSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.NotificationsStatusBar: ImageVector
    get() {
        if (_notificationsStatusBarSurfaceDemibold != null) return _notificationsStatusBarSurfaceDemibold!!
        _notificationsStatusBarSurfaceDemibold = buildHyperOSIcon(
            name = "NotificationsStatusBar.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(850.2f, 707f),
                PathNode.QuadTo(850.2f, 664.3f, 871.1f, 628.6f),
                PathNode.QuadTo(891.9f, 592.9f, 927.6f, 572.1f),
                PathNode.QuadTo(963.3f, 551.2f, 1006f, 551.2f),
                PathNode.QuadTo(1048.7f, 551.2f, 1084.9f, 572.1f),
                PathNode.QuadTo(1121.1f, 592.9f, 1142.4f, 628.6f),
                PathNode.QuadTo(1163.8f, 664.3f, 1163.8f, 707f),
                PathNode.QuadTo(1163.8f, 749.7f, 1142.4f, 785.9f),
                PathNode.QuadTo(1121.1f, 822.1f, 1084.9f, 843.4f),
                PathNode.QuadTo(1048.7f, 864.8f, 1006f, 864.8f),
                PathNode.QuadTo(963.3f, 864.8f, 927.6f, 843.4f),
                PathNode.QuadTo(891.9f, 822.1f, 871.1f, 785.9f),
                PathNode.QuadTo(850.2f, 749.7f, 850.2f, 707f),
                PathNode.Close,
                PathNode.MoveTo(302.6f, 815.4f),
                PathNode.QuadTo(251.4f, 788.6f, 224.6f, 737.4f),
                PathNode.QuadTo(210.7f, 710.6f, 207.4f, 674.3f),
                PathNode.QuadTo(204.2f, 637.9f, 204.2f, 555f),
                PathNode.VerticalTo(185f),
                PathNode.QuadTo(204.2f, 103.1f, 207.4f, 66.2f),
                PathNode.QuadTo(210.7f, 29.4f, 224.6f, 3.6f),
                PathNode.QuadTo(250.4f, -48.6f, 302.6f, -75.4f),
                PathNode.QuadTo(329.4f, -88.3f, 365.7f, -91.6f),
                PathNode.QuadTo(402.1f, -94.8f, 484f, -94.8f),
                PathNode.HorizontalTo(855f),
                PathNode.QuadTo(937.9f, -94.8f, 974.3f, -91.6f),
                PathNode.QuadTo(1010.6f, -88.3f, 1036.4f, -75.4f),
                PathNode.QuadTo(1089.6f, -47.6f, 1115.4f, 3.6f),
                PathNode.QuadTo(1128.3f, 29.4f, 1131.6f, 66.2f),
                PathNode.QuadTo(1134.8f, 103.1f, 1134.8f, 185f),
                PathNode.VerticalTo(517.8f),
                PathNode.QuadTo(1075.8f, 478.8f, 1006.5f, 478.8f),
                PathNode.QuadTo(943.4f, 478.8f, 891.1f, 509.5f),
                PathNode.QuadTo(838.8f, 540.3f, 808.3f, 592.9f),
                PathNode.QuadTo(777.8f, 645.5f, 777.8f, 707.5f),
                PathNode.QuadTo(777.8f, 741.7f, 788.2f, 775.1f),
                PathNode.QuadTo(798.5f, 808.6f, 816.8f, 835.8f),
                PathNode.HorizontalTo(484f),
                PathNode.QuadTo(402.1f, 835.8f, 365.7f, 832.6f),
                PathNode.QuadTo(329.4f, 829.3f, 302.6f, 815.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -17.3333f,
            translationY = 1051.6667f,
            autoMirror = false,
        )
        return _notificationsStatusBarSurfaceDemibold!!
    }

private var _notificationsStatusBarSurfaceDemibold: ImageVector? = null

