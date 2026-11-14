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

val MiuixIcons.Glass.NotificationFill: ImageVector
    get() = MiuixIcons.Glass.Regular.NotificationFill

val MiuixIcons.Glass.Light.NotificationFill: ImageVector
    get() {
        if (_notificationRemindersSurfaceLight != null) return _notificationRemindersSurfaceLight!!
        _notificationRemindersSurfaceLight = buildHyperOSIcon(
            name = "NotificationFill.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(395f, 590.4f),
                PathNode.LineTo(383f, 386.7f),
                PathNode.QuadTo(380f, 332.9f, 357.7f, 281.3f),
                PathNode.QuadTo(335.3f, 229.7f, 298f, 188f),
                PathNode.QuadTo(276.2f, 164.7f, 263.3f, 149.8f),
                PathNode.QuadTo(242.8f, 126.7f, 238.1f, 112.3f),
                PathNode.QuadTo(235.8f, 102.9f, 239.7f, 93.9f),
                PathNode.QuadTo(243.6f, 84.9f, 252.2f, 79.6f),
                PathNode.QuadTo(261.8f, 74.2f, 282.8f, 72.7f),
                PathNode.QuadTo(303.9f, 71.1f, 350.7f, 71.1f),
                PathNode.HorizontalTo(980.2f),
                PathNode.QuadTo(1027f, 71.1f, 1048.9f, 72.4f),
                PathNode.QuadTo(1070.9f, 73.8f, 1078.7f, 79.6f),
                PathNode.QuadTo(1086.4f, 85.3f, 1090.1f, 93.9f),
                PathNode.QuadTo(1093.8f, 102.4f, 1092.8f, 111.9f),
                PathNode.QuadTo(1091.8f, 121.3f, 1079.3f, 137.4f),
                PathNode.QuadTo(1066.8f, 153.4f, 1032.9f, 189.8f),
                PathNode.QuadTo(995.1f, 230.6f, 973.3f, 281.7f),
                PathNode.QuadTo(951.4f, 332.9f, 947.9f, 386.7f),
                PathNode.LineTo(934.9f, 590.4f),
                PathNode.QuadTo(930.3f, 660.3f, 893.4f, 718.9f),
                PathNode.QuadTo(856.4f, 777.4f, 795.9f, 811.1f),
                PathNode.QuadTo(735.3f, 844.8f, 665.4f, 844.8f),
                PathNode.QuadTo(594.6f, 844.8f, 534.2f, 811.1f),
                PathNode.QuadTo(473.9f, 777.4f, 436.4f, 719.1f),
                PathNode.QuadTo(399f, 660.8f, 395f, 590.4f),
                PathNode.Close,
                PathNode.MoveTo(790f, -25.9f),
                PathNode.QuadTo(794f, -17.2f, 790.7f, -11.9f),
                PathNode.QuadTo(787.3f, -6.6f, 778.1f, -6.6f),
                PathNode.HorizontalTo(553.3f),
                PathNode.QuadTo(544.1f, -6.6f, 540.6f, -11.7f),
                PathNode.QuadTo(537f, -16.8f, 541f, -25.4f),
                PathNode.QuadTo(558.9f, -61.2f, 591.8f, -82.4f),
                PathNode.QuadTo(624.7f, -103.7f, 665.4f, -103.7f),
                PathNode.QuadTo(707.2f, -103.7f, 739.6f, -82.7f),
                PathNode.QuadTo(772f, -61.7f, 790f, -25.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.5526f,
            translationY = 1037.2221f,
            autoMirror = false,
        )
        return _notificationRemindersSurfaceLight!!
    }

private var _notificationRemindersSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.NotificationFill: ImageVector
    get() {
        if (_notificationRemindersSurfaceNormal != null) return _notificationRemindersSurfaceNormal!!
        _notificationRemindersSurfaceNormal = buildHyperOSIcon(
            name = "NotificationFill.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(386.8f, 595.6f),
                PathNode.LineTo(374.8f, 395.8f),
                PathNode.QuadTo(371.8f, 343.3f, 350.3f, 293.8f),
                PathNode.QuadTo(328.9f, 244.3f, 292.5f, 204.5f),
                PathNode.QuadTo(266.4f, 176.6f, 254.2f, 162.3f),
                PathNode.QuadTo(233.3f, 138.6f, 230.5f, 122.4f),
                PathNode.QuadTo(229.1f, 110.5f, 233.7f, 100.2f),
                PathNode.QuadTo(238.4f, 89.8f, 247.9f, 82.6f),
                PathNode.QuadTo(257.8f, 75.4f, 279.2f, 73.6f),
                PathNode.QuadTo(300.5f, 71.7f, 351.6f, 71.7f),
                PathNode.HorizontalTo(978.7f),
                PathNode.QuadTo(1029.7f, 71.7f, 1051.4f, 73.5f),
                PathNode.QuadTo(1073f, 75.3f, 1082.3f, 82.6f),
                PathNode.QuadTo(1091.6f, 89.9f, 1096.2f, 100.2f),
                PathNode.QuadTo(1100.8f, 110.4f, 1099.8f, 122.3f),
                PathNode.QuadTo(1098.8f, 134.2f, 1086.3f, 150.5f),
                PathNode.QuadTo(1073.8f, 166.9f, 1037.8f, 205.1f),
                PathNode.QuadTo(1001.2f, 244.6f, 980.3f, 293.9f),
                PathNode.QuadTo(959.4f, 343.3f, 955.5f, 395.8f),
                PathNode.LineTo(942.5f, 595.6f),
                PathNode.QuadTo(937.7f, 667.7f, 899.7f, 727.9f),
                PathNode.QuadTo(861.6f, 788.1f, 799.4f, 822.7f),
                PathNode.QuadTo(737.2f, 857.3f, 665.1f, 857.3f),
                PathNode.QuadTo(592.1f, 857.3f, 529.9f, 822.7f),
                PathNode.QuadTo(467.8f, 788.1f, 429.3f, 728f),
                PathNode.QuadTo(390.8f, 667.8f, 386.8f, 595.6f),
                PathNode.Close,
                PathNode.MoveTo(795.5f, -28f),
                PathNode.QuadTo(799.5f, -18.4f, 795.7f, -12.7f),
                PathNode.QuadTo(791.9f, -6.9f, 781.5f, -6.9f),
                PathNode.HorizontalTo(549.7f),
                PathNode.QuadTo(539.2f, -6.9f, 535.4f, -12.6f),
                PathNode.QuadTo(531.5f, -18.3f, 535.5f, -27.9f),
                PathNode.QuadTo(552.8f, -65.2f, 587.8f, -87.6f),
                PathNode.QuadTo(622.8f, -110.1f, 665.1f, -110.1f),
                PathNode.QuadTo(708.4f, -110.1f, 743f, -87.7f),
                PathNode.QuadTo(777.5f, -65.3f, 795.5f, -28f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6035f,
            translationY = 1040.2775f,
            autoMirror = false,
        )
        return _notificationRemindersSurfaceNormal!!
    }

private var _notificationRemindersSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.NotificationFill: ImageVector
    get() {
        if (_notificationRemindersSurfaceRegular != null) return _notificationRemindersSurfaceRegular!!
        _notificationRemindersSurfaceRegular = buildHyperOSIcon(
            name = "NotificationFill.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(383f, 598f),
                PathNode.LineTo(371f, 400f),
                PathNode.QuadTo(368f, 348f, 347f, 299.5f),
                PathNode.QuadTo(326f, 251f, 290f, 212f),
                PathNode.QuadTo(262f, 182f, 250f, 168f),
                PathNode.QuadTo(229f, 144f, 227f, 127f),
                PathNode.QuadTo(226f, 114f, 231f, 103f),
                PathNode.QuadTo(236f, 92f, 246f, 84f),
                PathNode.QuadTo(256f, 76f, 277.5f, 74f),
                PathNode.QuadTo(299f, 72f, 352f, 72f),
                PathNode.HorizontalTo(978f),
                PathNode.QuadTo(1031f, 72f, 1052.5f, 74f),
                PathNode.QuadTo(1074f, 76f, 1084f, 84f),
                PathNode.QuadTo(1094f, 92f, 1099f, 103f),
                PathNode.QuadTo(1104f, 114f, 1103f, 127f),
                PathNode.QuadTo(1102f, 140f, 1089.5f, 156.5f),
                PathNode.QuadTo(1077f, 173f, 1040f, 212f),
                PathNode.QuadTo(1004f, 251f, 983.5f, 299.5f),
                PathNode.QuadTo(963f, 348f, 959f, 400f),
                PathNode.LineTo(946f, 598f),
                PathNode.QuadTo(941f, 671f, 902.5f, 732f),
                PathNode.QuadTo(864f, 793f, 801f, 828f),
                PathNode.QuadTo(738f, 863f, 665f, 863f),
                PathNode.QuadTo(591f, 863f, 528f, 828f),
                PathNode.QuadTo(465f, 793f, 426f, 732f),
                PathNode.QuadTo(387f, 671f, 383f, 598f),
                PathNode.Close,
                PathNode.MoveTo(798f, -29f),
                PathNode.QuadTo(802f, -19f, 798f, -13f),
                PathNode.QuadTo(794f, -7f, 783f, -7f),
                PathNode.HorizontalTo(548f),
                PathNode.QuadTo(537f, -7f, 533f, -13f),
                PathNode.QuadTo(529f, -19f, 533f, -29f),
                PathNode.QuadTo(550f, -67f, 586f, -90f),
                PathNode.QuadTo(622f, -113f, 665f, -113f),
                PathNode.QuadTo(709f, -113f, 744.5f, -90f),
                PathNode.QuadTo(780f, -67f, 798f, -29f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _notificationRemindersSurfaceRegular!!
    }

private var _notificationRemindersSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.NotificationFill: ImageVector
    get() {
        if (_notificationRemindersSurfaceMedium != null) return _notificationRemindersSurfaceMedium!!
        _notificationRemindersSurfaceMedium = buildHyperOSIcon(
            name = "NotificationFill.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(376.8f, 600.4f),
                PathNode.LineTo(364.8f, 406.2f),
                PathNode.QuadTo(361.8f, 355.8f, 341.5f, 309f),
                PathNode.QuadTo(321.1f, 262.1f, 286.2f, 224.2f),
                PathNode.QuadTo(259.6f, 195.8f, 245.1f, 178.8f),
                PathNode.QuadTo(223.3f, 153.2f, 221.6f, 134.8f),
                PathNode.QuadTo(220.6f, 120.5f, 226.1f, 107.7f),
                PathNode.QuadTo(231.7f, 95f, 243.3f, 85.6f),
                PathNode.QuadTo(254.9f, 76.5f, 277f, 74.3f),
                PathNode.QuadTo(299f, 72f, 353.6f, 72f),
                PathNode.HorizontalTo(976.4f),
                PathNode.QuadTo(1031f, 72f, 1053f, 74.3f),
                PathNode.QuadTo(1075.1f, 76.5f, 1086.7f, 85.6f),
                PathNode.QuadTo(1098.3f, 95f, 1103.9f, 107.7f),
                PathNode.QuadTo(1109.4f, 120.5f, 1108.4f, 134.8f),
                PathNode.QuadTo(1107.4f, 149.7f, 1094.2f, 167.2f),
                PathNode.QuadTo(1081.1f, 184.6f, 1043.8f, 224.2f),
                PathNode.QuadTo(1008.9f, 262.1f, 988.9f, 309f),
                PathNode.QuadTo(968.9f, 355.8f, 965.2f, 406.2f),
                PathNode.LineTo(952.2f, 600.4f),
                PathNode.QuadTo(946.9f, 675.6f, 907.6f, 737.8f),
                PathNode.QuadTo(868.3f, 800f, 804.1f, 835.7f),
                PathNode.QuadTo(739.9f, 871.4f, 665f, 871.4f),
                PathNode.QuadTo(589.4f, 871.4f, 525f, 835.7f),
                PathNode.QuadTo(460.7f, 800f, 420.9f, 737.8f),
                PathNode.QuadTo(381.1f, 675.6f, 376.8f, 600.4f),
                PathNode.Close,
                PathNode.MoveTo(803.4f, -31.4f),
                PathNode.QuadTo(807.9f, -20.1f, 803f, -12.6f),
                PathNode.QuadTo(798.1f, -5.1f, 786f, -5.1f),
                PathNode.HorizontalTo(545.3f),
                PathNode.QuadTo(533.5f, -5.1f, 528.5f, -12.5f),
                PathNode.QuadTo(523.6f, -19.8f, 527.9f, -31.4f),
                PathNode.QuadTo(544.6f, -71.3f, 582.1f, -96.1f),
                PathNode.QuadTo(619.6f, -120.8f, 665f, -120.8f),
                PathNode.QuadTo(711.4f, -120.8f, 748.7f, -96.1f),
                PathNode.QuadTo(785.9f, -71.3f, 803.4f, -31.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.9369f,
            autoMirror = false,
        )
        return _notificationRemindersSurfaceMedium!!
    }

private var _notificationRemindersSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.NotificationFill: ImageVector
    get() {
        if (_notificationRemindersSurfaceDemibold != null) return _notificationRemindersSurfaceDemibold!!
        _notificationRemindersSurfaceDemibold = buildHyperOSIcon(
            name = "NotificationFill.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(372.4f, 602.1f),
                PathNode.LineTo(360.4f, 410.6f),
                PathNode.QuadTo(357.4f, 361.3f, 337.6f, 315.6f),
                PathNode.QuadTo(317.7f, 269.8f, 283.6f, 232.7f),
                PathNode.QuadTo(257.9f, 205.4f, 241.7f, 186.4f),
                PathNode.QuadTo(219.4f, 159.6f, 217.8f, 140.3f),
                PathNode.QuadTo(216.8f, 125f, 222.7f, 111f),
                PathNode.QuadTo(228.6f, 97.1f, 241.4f, 86.8f),
                PathNode.QuadTo(254.2f, 76.9f, 276.6f, 74.5f),
                PathNode.QuadTo(299f, 72f, 354.8f, 72f),
                PathNode.HorizontalTo(975.2f),
                PathNode.QuadTo(1031f, 72f, 1053.4f, 74.5f),
                PathNode.QuadTo(1075.8f, 76.9f, 1088.6f, 86.8f),
                PathNode.QuadTo(1101.4f, 97.1f, 1107.3f, 111f),
                PathNode.QuadTo(1113.2f, 125f, 1112.2f, 140.3f),
                PathNode.QuadTo(1111.2f, 156.5f, 1097.5f, 174.6f),
                PathNode.QuadTo(1083.9f, 192.8f, 1046.4f, 232.7f),
                PathNode.QuadTo(1012.3f, 269.8f, 992.7f, 315.6f),
                PathNode.QuadTo(973.1f, 361.3f, 969.6f, 410.6f),
                PathNode.LineTo(956.6f, 602.1f),
                PathNode.QuadTo(951.1f, 678.8f, 911.2f, 741.9f),
                PathNode.QuadTo(871.4f, 804.9f, 806.3f, 841.1f),
                PathNode.QuadTo(741.2f, 877.2f, 665f, 877.2f),
                PathNode.QuadTo(588.2f, 877.2f, 522.9f, 841.1f),
                PathNode.QuadTo(457.6f, 804.9f, 417.3f, 741.9f),
                PathNode.QuadTo(376.9f, 678.8f, 372.4f, 602.1f),
                PathNode.Close,
                PathNode.MoveTo(807.2f, -33.1f),
                PathNode.QuadTo(812.1f, -20.8f, 806.5f, -12.3f),
                PathNode.QuadTo(800.9f, -3.8f, 788.1f, -3.8f),
                PathNode.HorizontalTo(543.4f),
                PathNode.QuadTo(531f, -3.8f, 525.4f, -12.1f),
                PathNode.QuadTo(519.8f, -20.4f, 524.3f, -33.1f),
                PathNode.QuadTo(540.8f, -74.4f, 579.3f, -100.3f),
                PathNode.QuadTo(617.9f, -126.3f, 665f, -126.3f),
                PathNode.QuadTo(713.1f, -126.3f, 751.6f, -100.3f),
                PathNode.QuadTo(790.1f, -74.4f, 807.2f, -33.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1042.1261f,
            autoMirror = false,
        )
        return _notificationRemindersSurfaceDemibold!!
    }

private var _notificationRemindersSurfaceDemibold: ImageVector? = null

