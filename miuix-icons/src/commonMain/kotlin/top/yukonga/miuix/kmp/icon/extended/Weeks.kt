// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.extended

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Weeks: ImageVector
    get() = MiuixIcons.Regular.Weeks

val MiuixIcons.Light.Weeks: ImageVector
    get() {
        if (_weeksLight != null) return _weeksLight!!
        _weeksLight = ImageVector.Builder(
            name = "Weeks.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1176.0f,
            viewportHeight = 1176.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -77.0f, translationY = 971.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(940.0f, 420.0f),
                PathNode.VerticalTo(440.0f),
                PathNode.QuadTo(940.0f, 452.0f, 935.0f, 457.5f),
                PathNode.QuadTo(930.0f, 463.0f, 919.0f, 463.0f),
                PathNode.HorizontalTo(413.0f),
                PathNode.QuadTo(403.0f, 463.0f, 398.0f, 457.5f),
                PathNode.QuadTo(393.0f, 452.0f, 393.0f, 440.0f),
                PathNode.VerticalTo(420.0f),
                PathNode.QuadTo(393.0f, 408.0f, 397.5f, 403.0f),
                PathNode.QuadTo(402.0f, 398.0f, 413.0f, 398.0f),
                PathNode.HorizontalTo(919.0f),
                PathNode.QuadTo(930.0f, 398.0f, 935.0f, 403.0f),
                PathNode.QuadTo(940.0f, 408.0f, 940.0f, 420.0f),
                PathNode.Close,
                PathNode.MoveTo(1070.0f, -54.0f),
                PathNode.QuadTo(1116.0f, -31.0f, 1137.0f, 13.0f),
                PathNode.QuadTo(1149.0f, 36.0f, 1152.0f, 69.5f),
                PathNode.QuadTo(1155.0f, 103.0f, 1155.0f, 184.0f),
                PathNode.VerticalTo(582.0f),
                PathNode.QuadTo(1155.0f, 663.0f, 1152.0f, 696.0f),
                PathNode.QuadTo(1149.0f, 729.0f, 1137.0f, 752.0f),
                PathNode.QuadTo(1126.0f, 774.0f, 1109.0f, 791.5f),
                PathNode.QuadTo(1092.0f, 809.0f, 1070.0f, 820.0f),
                PathNode.QuadTo(1047.0f, 831.0f, 1012.5f, 834.0f),
                PathNode.QuadTo(978.0f, 837.0f, 900.0f, 837.0f),
                PathNode.HorizontalTo(430.0f),
                PathNode.QuadTo(352.0f, 837.0f, 317.5f, 834.0f),
                PathNode.QuadTo(283.0f, 831.0f, 260.0f, 820.0f),
                PathNode.QuadTo(238.0f, 809.0f, 221.0f, 791.5f),
                PathNode.QuadTo(204.0f, 774.0f, 193.0f, 752.0f),
                PathNode.QuadTo(181.0f, 729.0f, 178.0f, 696.0f),
                PathNode.QuadTo(175.0f, 663.0f, 175.0f, 582.0f),
                PathNode.VerticalTo(184.0f),
                PathNode.QuadTo(175.0f, 103.0f, 178.0f, 69.5f),
                PathNode.QuadTo(181.0f, 36.0f, 193.0f, 13.0f),
                PathNode.QuadTo(214.0f, -31.0f, 260.0f, -54.0f),
                PathNode.QuadTo(283.0f, -65.0f, 317.5f, -68.0f),
                PathNode.QuadTo(352.0f, -71.0f, 430.0f, -71.0f),
                PathNode.HorizontalTo(900.0f),
                PathNode.QuadTo(978.0f, -71.0f, 1012.5f, -68.0f),
                PathNode.QuadTo(1047.0f, -65.0f, 1070.0f, -54.0f),
                PathNode.Close,
                PathNode.MoveTo(293.0f, 8.0f),
                PathNode.QuadTo(268.0f, 20.0f, 254.0f, 47.0f),
                PathNode.QuadTo(247.0f, 59.0f, 245.5f, 76.5f),
                PathNode.QuadTo(244.0f, 94.0f, 244.0f, 134.0f),
                PathNode.VerticalTo(499.0f),
                PathNode.QuadTo(244.0f, 539.0f, 245.5f, 556.0f),
                PathNode.QuadTo(247.0f, 573.0f, 254.0f, 586.0f),
                PathNode.QuadTo(268.0f, 613.0f, 293.0f, 625.0f),
                PathNode.QuadTo(306.0f, 633.0f, 323.0f, 634.5f),
                PathNode.QuadTo(340.0f, 636.0f, 380.0f, 636.0f),
                PathNode.HorizontalTo(950.0f),
                PathNode.QuadTo(990.0f, 636.0f, 1007.0f, 634.5f),
                PathNode.QuadTo(1024.0f, 633.0f, 1037.0f, 625.0f),
                PathNode.QuadTo(1062.0f, 613.0f, 1076.0f, 586.0f),
                PathNode.QuadTo(1083.0f, 573.0f, 1084.5f, 556.0f),
                PathNode.QuadTo(1086.0f, 539.0f, 1086.0f, 499.0f),
                PathNode.VerticalTo(134.0f),
                PathNode.QuadTo(1086.0f, 94.0f, 1084.5f, 76.5f),
                PathNode.QuadTo(1083.0f, 59.0f, 1076.0f, 47.0f),
                PathNode.QuadTo(1062.0f, 20.0f, 1037.0f, 8.0f),
                PathNode.QuadTo(1024.0f, 1.0f, 1006.0f, -1.0f),
                PathNode.QuadTo(988.0f, -3.0f, 950.0f, -3.0f),
                PathNode.HorizontalTo(380.0f),
                PathNode.QuadTo(342.0f, -3.0f, 324.0f, -1.0f),
                PathNode.QuadTo(306.0f, 1.0f, 293.0f, 8.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _weeksLight!!
    }

private var _weeksLight: ImageVector? = null

val MiuixIcons.Regular.Weeks: ImageVector
    get() {
        if (_weeksRegular != null) return _weeksRegular!!
        _weeksRegular = ImageVector.Builder(
            name = "Weeks.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1197.6f,
            viewportHeight = 1197.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -66.20000000000005f, translationY = 981.8f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(940.0f, 416.0f),
                PathNode.VerticalTo(443.0f),
                PathNode.QuadTo(940.0f, 462.0f, 933.0f, 470.0f),
                PathNode.QuadTo(926.0f, 478.0f, 909.0f, 478.0f),
                PathNode.HorizontalTo(425.0f),
                PathNode.QuadTo(408.0f, 478.0f, 401.0f, 470.0f),
                PathNode.QuadTo(394.0f, 462.0f, 394.0f, 443.0f),
                PathNode.VerticalTo(416.0f),
                PathNode.QuadTo(394.0f, 397.0f, 401.0f, 389.5f),
                PathNode.QuadTo(408.0f, 382.0f, 425.0f, 382.0f),
                PathNode.HorizontalTo(909.0f),
                PathNode.QuadTo(926.0f, 382.0f, 933.0f, 389.5f),
                PathNode.QuadTo(940.0f, 397.0f, 940.0f, 416.0f),
                PathNode.Close,
                PathNode.MoveTo(1073.0f, -62.0f),
                PathNode.QuadTo(1123.0f, -36.0f, 1145.0f, 10.0f),
                PathNode.QuadTo(1158.0f, 35.0f, 1161.0f, 70.0f),
                PathNode.QuadTo(1164.0f, 105.0f, 1164.0f, 186.0f),
                PathNode.VerticalTo(580.0f),
                PathNode.QuadTo(1164.0f, 661.0f, 1161.0f, 695.5f),
                PathNode.QuadTo(1158.0f, 730.0f, 1145.0f, 755.0f),
                PathNode.QuadTo(1134.0f, 778.0f, 1115.0f, 797.0f),
                PathNode.QuadTo(1096.0f, 816.0f, 1073.0f, 828.0f),
                PathNode.QuadTo(1048.0f, 840.0f, 1013.5f, 843.0f),
                PathNode.QuadTo(979.0f, 846.0f, 898.0f, 846.0f),
                PathNode.HorizontalTo(432.0f),
                PathNode.QuadTo(351.0f, 846.0f, 316.5f, 843.0f),
                PathNode.QuadTo(282.0f, 840.0f, 257.0f, 828.0f),
                PathNode.QuadTo(234.0f, 816.0f, 215.0f, 797.0f),
                PathNode.QuadTo(196.0f, 778.0f, 185.0f, 755.0f),
                PathNode.QuadTo(172.0f, 730.0f, 169.0f, 695.5f),
                PathNode.QuadTo(166.0f, 661.0f, 166.0f, 580.0f),
                PathNode.VerticalTo(186.0f),
                PathNode.QuadTo(166.0f, 105.0f, 169.0f, 70.0f),
                PathNode.QuadTo(172.0f, 35.0f, 185.0f, 10.0f),
                PathNode.QuadTo(207.0f, -36.0f, 257.0f, -62.0f),
                PathNode.QuadTo(282.0f, -74.0f, 316.5f, -77.0f),
                PathNode.QuadTo(351.0f, -80.0f, 432.0f, -80.0f),
                PathNode.HorizontalTo(898.0f),
                PathNode.QuadTo(979.0f, -80.0f, 1013.5f, -77.0f),
                PathNode.QuadTo(1048.0f, -74.0f, 1073.0f, -62.0f),
                PathNode.Close,
                PathNode.MoveTo(303.0f, 23.0f),
                PathNode.QuadTo(281.0f, 34.0f, 270.0f, 56.0f),
                PathNode.QuadTo(264.0f, 67.0f, 262.5f, 83.0f),
                PathNode.QuadTo(261.0f, 99.0f, 261.0f, 136.0f),
                PathNode.VerticalTo(498.0f),
                PathNode.QuadTo(261.0f, 536.0f, 262.5f, 551.5f),
                PathNode.QuadTo(264.0f, 567.0f, 270.0f, 578.0f),
                PathNode.QuadTo(281.0f, 600.0f, 303.0f, 611.0f),
                PathNode.QuadTo(314.0f, 617.0f, 330.0f, 618.5f),
                PathNode.QuadTo(346.0f, 620.0f, 383.0f, 620.0f),
                PathNode.HorizontalTo(947.0f),
                PathNode.QuadTo(985.0f, 620.0f, 1000.5f, 618.5f),
                PathNode.QuadTo(1016.0f, 617.0f, 1027.0f, 611.0f),
                PathNode.QuadTo(1049.0f, 600.0f, 1060.0f, 578.0f),
                PathNode.QuadTo(1066.0f, 567.0f, 1067.5f, 551.5f),
                PathNode.QuadTo(1069.0f, 536.0f, 1069.0f, 498.0f),
                PathNode.VerticalTo(136.0f),
                PathNode.QuadTo(1069.0f, 99.0f, 1067.5f, 83.0f),
                PathNode.QuadTo(1066.0f, 67.0f, 1060.0f, 56.0f),
                PathNode.QuadTo(1049.0f, 34.0f, 1027.0f, 23.0f),
                PathNode.QuadTo(1016.0f, 17.0f, 1000.5f, 15.5f),
                PathNode.QuadTo(985.0f, 14.0f, 947.0f, 14.0f),
                PathNode.HorizontalTo(383.0f),
                PathNode.QuadTo(346.0f, 14.0f, 330.0f, 15.5f),
                PathNode.QuadTo(314.0f, 17.0f, 303.0f, 23.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _weeksRegular!!
    }

private var _weeksRegular: ImageVector? = null

val MiuixIcons.Heavy.Weeks: ImageVector
    get() {
        if (_weeksHeavy != null) return _weeksHeavy!!
        _weeksHeavy = ImageVector.Builder(
            name = "Weeks.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1224.0f,
            viewportHeight = 1224.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -53.0f, translationY = 992.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(930.0f, 416.0f),
                PathNode.VerticalTo(443.0f),
                PathNode.QuadTo(930.0f, 467.0f, 919.0f, 478.0f),
                PathNode.QuadTo(908.0f, 489.0f, 888.0f, 489.0f),
                PathNode.HorizontalTo(445.0f),
                PathNode.QuadTo(425.0f, 489.0f, 414.0f, 478.0f),
                PathNode.QuadTo(403.0f, 467.0f, 403.0f, 443.0f),
                PathNode.VerticalTo(416.0f),
                PathNode.QuadTo(403.0f, 392.0f, 414.0f, 381.5f),
                PathNode.QuadTo(425.0f, 371.0f, 445.0f, 371.0f),
                PathNode.HorizontalTo(888.0f),
                PathNode.QuadTo(909.0f, 371.0f, 919.5f, 381.5f),
                PathNode.QuadTo(930.0f, 392.0f, 930.0f, 416.0f),
                PathNode.Close,
                PathNode.MoveTo(1077.0f, -77.0f),
                PathNode.QuadTo(1130.0f, -48.0f, 1155.0f, 2.0f),
                PathNode.QuadTo(1169.0f, 29.0f, 1172.0f, 65.5f),
                PathNode.QuadTo(1175.0f, 102.0f, 1175.0f, 183.0f),
                PathNode.VerticalTo(578.0f),
                PathNode.QuadTo(1175.0f, 659.0f, 1172.0f, 695.5f),
                PathNode.QuadTo(1169.0f, 732.0f, 1155.0f, 759.0f),
                PathNode.QuadTo(1142.0f, 784.0f, 1122.0f, 804.5f),
                PathNode.QuadTo(1102.0f, 825.0f, 1077.0f, 838.0f),
                PathNode.QuadTo(1050.0f, 851.0f, 1013.5f, 854.0f),
                PathNode.QuadTo(977.0f, 857.0f, 896.0f, 857.0f),
                PathNode.HorizontalTo(434.0f),
                PathNode.QuadTo(353.0f, 857.0f, 316.5f, 854.0f),
                PathNode.QuadTo(280.0f, 851.0f, 253.0f, 838.0f),
                PathNode.QuadTo(228.0f, 825.0f, 208.0f, 804.5f),
                PathNode.QuadTo(188.0f, 784.0f, 175.0f, 759.0f),
                PathNode.QuadTo(161.0f, 732.0f, 158.0f, 695.5f),
                PathNode.QuadTo(155.0f, 659.0f, 155.0f, 578.0f),
                PathNode.VerticalTo(183.0f),
                PathNode.QuadTo(155.0f, 102.0f, 158.0f, 65.5f),
                PathNode.QuadTo(161.0f, 29.0f, 175.0f, 2.0f),
                PathNode.QuadTo(200.0f, -48.0f, 253.0f, -77.0f),
                PathNode.QuadTo(280.0f, -90.0f, 316.5f, -93.0f),
                PathNode.QuadTo(353.0f, -96.0f, 434.0f, -96.0f),
                PathNode.HorizontalTo(896.0f),
                PathNode.QuadTo(977.0f, -96.0f, 1013.5f, -93.0f),
                PathNode.QuadTo(1050.0f, -90.0f, 1077.0f, -77.0f),
                PathNode.Close,
                PathNode.MoveTo(314.0f, 35.0f),
                PathNode.QuadTo(297.0f, 43.0f, 287.0f, 62.0f),
                PathNode.QuadTo(283.0f, 71.0f, 281.5f, 85.5f),
                PathNode.QuadTo(280.0f, 100.0f, 280.0f, 134.0f),
                PathNode.VerticalTo(497.0f),
                PathNode.QuadTo(280.0f, 531.0f, 281.5f, 545.5f),
                PathNode.QuadTo(283.0f, 560.0f, 287.0f, 569.0f),
                PathNode.QuadTo(296.0f, 586.0f, 314.0f, 595.0f),
                PathNode.QuadTo(322.0f, 600.0f, 335.5f, 601.0f),
                PathNode.QuadTo(349.0f, 602.0f, 386.0f, 602.0f),
                PathNode.HorizontalTo(944.0f),
                PathNode.QuadTo(981.0f, 602.0f, 994.5f, 601.0f),
                PathNode.QuadTo(1008.0f, 600.0f, 1016.0f, 595.0f),
                PathNode.QuadTo(1034.0f, 586.0f, 1043.0f, 569.0f),
                PathNode.QuadTo(1047.0f, 560.0f, 1048.5f, 545.0f),
                PathNode.QuadTo(1050.0f, 530.0f, 1050.0f, 497.0f),
                PathNode.VerticalTo(133.0f),
                PathNode.QuadTo(1050.0f, 99.0f, 1048.5f, 85.0f),
                PathNode.QuadTo(1047.0f, 71.0f, 1043.0f, 62.0f),
                PathNode.QuadTo(1033.0f, 43.0f, 1016.0f, 35.0f),
                PathNode.QuadTo(1008.0f, 31.0f, 990.0f, 30.0f),
                PathNode.QuadTo(972.0f, 29.0f, 944.0f, 29.0f),
                PathNode.HorizontalTo(386.0f),
                PathNode.QuadTo(358.0f, 29.0f, 340.0f, 30.0f),
                PathNode.QuadTo(322.0f, 31.0f, 314.0f, 35.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _weeksHeavy!!
    }

private var _weeksHeavy: ImageVector? = null
