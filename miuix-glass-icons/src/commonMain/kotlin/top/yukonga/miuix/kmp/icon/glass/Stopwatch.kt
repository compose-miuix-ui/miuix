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

val MiuixIcons.Glass.Stopwatch: ImageVector
    get() = MiuixIcons.Glass.Regular.Stopwatch

val MiuixIcons.Glass.Light.Stopwatch: ImageVector
    get() {
        if (_stopwatchLight != null) return _stopwatchLight!!
        _stopwatchLight = ImageVector.Builder(
            name = "Stopwatch.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1045.4f, 506.5f),
                        PathNode.QuadTo(1045.4f, 635.1f, 981.9f, 744.1f),
                        PathNode.QuadTo(918.4f, 853.1f, 809.4f, 916.8f),
                        PathNode.QuadTo(700.4f, 980.5f, 571.4f, 980.5f),
                        PathNode.QuadTo(442.4f, 980.5f, 333.4f, 916.8f),
                        PathNode.QuadTo(224.4f, 853.1f, 160.9f, 744.1f),
                        PathNode.QuadTo(97.4f, 635.1f, 97.4f, 506.5f),
                        PathNode.QuadTo(97.4f, 378.5f, 160.9f, 269.5f),
                        PathNode.QuadTo(224.4f, 160.5f, 333.4f, 96.8f),
                        PathNode.QuadTo(442.4f, 33.1f, 571.4f, 33.1f),
                        PathNode.QuadTo(700.4f, 33.1f, 809.4f, 96.8f),
                        PathNode.QuadTo(918.4f, 160.5f, 981.9f, 269.5f),
                        PathNode.QuadTo(1045.4f, 378.5f, 1045.4f, 506.5f),
                        PathNode.Close,
                        PathNode.MoveTo(547.3f, 469.3f),
                        PathNode.LineTo(547.3f, 823.1f),
                        PathNode.QuadTo(547.3f, 831.7f, 551.3f, 835.9f),
                        PathNode.QuadTo(555.2f, 840.1f, 564.3f, 840.1f),
                        PathNode.LineTo(578.5f, 840.1f),
                        PathNode.QuadTo(588.2f, 840.1f, 591.9f, 835.9f),
                        PathNode.QuadTo(595.5f, 831.7f, 595.5f, 823.1f),
                        PathNode.LineTo(595.5f, 469.3f),
                        PathNode.QuadTo(595.5f, 461.9f, 590.8f, 457.7f),
                        PathNode.QuadTo(586.0f, 453.4f, 578.5f, 453.4f),
                        PathNode.LineTo(564.3f, 453.4f),
                        PathNode.QuadTo(547.3f, 453.4f, 547.3f, 469.3f),
                        PathNode.Close,
                        PathNode.MoveTo(740.9f, 1058.7f),
                        PathNode.LineTo(740.9f, 1079.5f),
                        PathNode.QuadTo(740.9f, 1095.7f, 733.2f, 1102.7f),
                        PathNode.QuadTo(725.5f, 1109.8f, 711.1f, 1109.8f),
                        PathNode.LineTo(434.4f, 1109.8f),
                        PathNode.QuadTo(420.5f, 1109.8f, 412.6f, 1102.9f),
                        PathNode.QuadTo(404.7f, 1096.0f, 404.7f, 1080.4f),
                        PathNode.LineTo(404.7f, 1058.7f),
                        PathNode.QuadTo(404.7f, 1042.4f, 412.0f, 1036.2f),
                        PathNode.QuadTo(419.4f, 1029.9f, 434.4f, 1029.9f),
                        PathNode.LineTo(711.1f, 1029.9f),
                        PathNode.QuadTo(725.5f, 1029.9f, 733.2f, 1036.4f),
                        PathNode.QuadTo(740.9f, 1043.0f, 740.9f, 1058.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchLight!!
    }

private var _stopwatchLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Stopwatch: ImageVector
    get() {
        if (_stopwatchNormal != null) return _stopwatchNormal!!
        _stopwatchNormal = ImageVector.Builder(
            name = "Stopwatch.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1045.4f, 501.7f),
                        PathNode.QuadTo(1045.4f, 630.5f, 981.9f, 739.5f),
                        PathNode.QuadTo(918.4f, 848.5f, 809.4f, 912.1f),
                        PathNode.QuadTo(700.4f, 975.7f, 571.4f, 975.7f),
                        PathNode.QuadTo(442.4f, 975.7f, 333.4f, 912.1f),
                        PathNode.QuadTo(224.4f, 848.5f, 160.9f, 739.5f),
                        PathNode.QuadTo(97.4f, 630.5f, 97.4f, 501.7f),
                        PathNode.QuadTo(97.4f, 373.7f, 160.9f, 264.7f),
                        PathNode.QuadTo(224.4f, 155.7f, 333.4f, 92.1f),
                        PathNode.QuadTo(442.4f, 28.5f, 571.4f, 28.5f),
                        PathNode.QuadTo(700.4f, 28.5f, 809.4f, 92.1f),
                        PathNode.QuadTo(918.4f, 155.7f, 981.9f, 264.7f),
                        PathNode.QuadTo(1045.4f, 373.7f, 1045.4f, 501.7f),
                        PathNode.Close,
                        PathNode.MoveTo(541.2f, 468.7f),
                        PathNode.LineTo(541.2f, 804.8f),
                        PathNode.QuadTo(541.2f, 816.4f, 546.2f, 821.8f),
                        PathNode.QuadTo(551.2f, 827.3f, 563.7f, 827.3f),
                        PathNode.LineTo(579.2f, 827.3f),
                        PathNode.QuadTo(592.5f, 827.3f, 597.1f, 821.8f),
                        PathNode.QuadTo(601.7f, 816.4f, 601.7f, 804.8f),
                        PathNode.LineTo(601.7f, 468.7f),
                        PathNode.QuadTo(601.7f, 458.8f, 595.3f, 453.4f),
                        PathNode.QuadTo(589.0f, 447.9f, 579.2f, 447.9f),
                        PathNode.LineTo(563.7f, 447.9f),
                        PathNode.QuadTo(541.2f, 447.9f, 541.2f, 468.7f),
                        PathNode.Close,
                        PathNode.MoveTo(743.3f, 1057.1f),
                        PathNode.LineTo(743.3f, 1080.2f),
                        PathNode.QuadTo(743.3f, 1099.6f, 734.7f, 1107.0f),
                        PathNode.QuadTo(726.2f, 1114.3f, 709.3f, 1114.3f),
                        PathNode.LineTo(434.4f, 1114.3f),
                        PathNode.QuadTo(418.4f, 1114.3f, 409.4f, 1106.7f),
                        PathNode.QuadTo(400.4f, 1099.0f, 400.4f, 1080.4f),
                        PathNode.LineTo(400.4f, 1057.1f),
                        PathNode.QuadTo(400.4f, 1036.9f, 408.5f, 1030.5f),
                        PathNode.QuadTo(416.7f, 1024.1f, 434.4f, 1024.1f),
                        PathNode.LineTo(709.3f, 1024.1f),
                        PathNode.QuadTo(726.2f, 1024.1f, 734.7f, 1030.9f),
                        PathNode.QuadTo(743.3f, 1037.8f, 743.3f, 1057.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchNormal!!
    }

private var _stopwatchNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Stopwatch: ImageVector
    get() {
        if (_stopwatchRegular != null) return _stopwatchRegular!!
        _stopwatchRegular = ImageVector.Builder(
            name = "Stopwatch.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1045.4f, 499.4f),
                        PathNode.QuadTo(1045.4f, 628.4f, 981.9f, 737.4f),
                        PathNode.QuadTo(918.4f, 846.4f, 809.4f, 909.9f),
                        PathNode.QuadTo(700.4f, 973.4f, 571.4f, 973.4f),
                        PathNode.QuadTo(442.4f, 973.4f, 333.4f, 909.9f),
                        PathNode.QuadTo(224.4f, 846.4f, 160.9f, 737.4f),
                        PathNode.QuadTo(97.4f, 628.4f, 97.4f, 499.4f),
                        PathNode.QuadTo(97.4f, 371.4f, 160.9f, 262.4f),
                        PathNode.QuadTo(224.4f, 153.4f, 333.4f, 89.9f),
                        PathNode.QuadTo(442.4f, 26.4f, 571.4f, 26.4f),
                        PathNode.QuadTo(700.4f, 26.4f, 809.4f, 89.9f),
                        PathNode.QuadTo(918.4f, 153.4f, 981.9f, 262.4f),
                        PathNode.QuadTo(1045.4f, 371.4f, 1045.4f, 499.4f),
                        PathNode.Close,
                        PathNode.MoveTo(538.4f, 468.4f),
                        PathNode.LineTo(538.4f, 796.4f),
                        PathNode.QuadTo(538.4f, 809.4f, 543.9f, 815.4f),
                        PathNode.QuadTo(549.4f, 821.4f, 563.4f, 821.4f),
                        PathNode.LineTo(579.4f, 821.4f),
                        PathNode.QuadTo(594.4f, 821.4f, 599.4f, 815.4f),
                        PathNode.QuadTo(604.4f, 809.4f, 604.4f, 796.4f),
                        PathNode.LineTo(604.4f, 468.4f),
                        PathNode.QuadTo(604.4f, 457.4f, 597.4f, 451.4f),
                        PathNode.QuadTo(590.4f, 445.4f, 579.4f, 445.4f),
                        PathNode.LineTo(563.4f, 445.4f),
                        PathNode.QuadTo(538.4f, 445.4f, 538.4f, 468.4f),
                        PathNode.Close,
                        PathNode.MoveTo(744.4f, 1056.4f),
                        PathNode.LineTo(744.4f, 1080.4f),
                        PathNode.QuadTo(744.4f, 1101.4f, 735.4f, 1108.9f),
                        PathNode.QuadTo(726.4f, 1116.4f, 708.4f, 1116.4f),
                        PathNode.LineTo(434.4f, 1116.4f),
                        PathNode.QuadTo(417.4f, 1116.4f, 407.9f, 1108.4f),
                        PathNode.QuadTo(398.4f, 1100.4f, 398.4f, 1080.4f),
                        PathNode.LineTo(398.4f, 1056.4f),
                        PathNode.QuadTo(398.4f, 1034.4f, 406.9f, 1027.9f),
                        PathNode.QuadTo(415.4f, 1021.4f, 434.4f, 1021.4f),
                        PathNode.LineTo(708.4f, 1021.4f),
                        PathNode.QuadTo(726.4f, 1021.4f, 735.4f, 1028.4f),
                        PathNode.QuadTo(744.4f, 1035.4f, 744.4f, 1056.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchRegular!!
    }

private var _stopwatchRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Stopwatch: ImageVector
    get() {
        if (_stopwatchMedium != null) return _stopwatchMedium!!
        _stopwatchMedium = ImageVector.Builder(
            name = "Stopwatch.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1052.2f, 492.4f),
                        PathNode.QuadTo(1052.2f, 623.3f, 987.7f, 733.8f),
                        PathNode.QuadTo(923.3f, 844.3f, 812.7f, 908.7f),
                        PathNode.QuadTo(702.1f, 973.2f, 571.4f, 973.2f),
                        PathNode.QuadTo(440.8f, 973.2f, 330.2f, 908.7f),
                        PathNode.QuadTo(219.6f, 844.3f, 155.1f, 733.8f),
                        PathNode.QuadTo(90.7f, 623.3f, 90.7f, 492.4f),
                        PathNode.QuadTo(90.7f, 362.5f, 155.3f, 252.0f),
                        PathNode.QuadTo(219.8f, 141.5f, 330.3f, 77.1f),
                        PathNode.QuadTo(440.8f, 12.6f, 571.4f, 12.6f),
                        PathNode.QuadTo(702.1f, 12.6f, 812.5f, 77.1f),
                        PathNode.QuadTo(923.0f, 141.5f, 987.6f, 252.0f),
                        PathNode.QuadTo(1052.2f, 362.5f, 1052.2f, 492.4f),
                        PathNode.Close,
                        PathNode.MoveTo(530.3f, 475.5f),
                        PathNode.LineTo(530.3f, 787.0f),
                        PathNode.QuadTo(530.3f, 801.9f, 537.2f, 808.9f),
                        PathNode.QuadTo(544.0f, 816.0f, 559.4f, 816.0f),
                        PathNode.LineTo(583.5f, 816.0f),
                        PathNode.QuadTo(599.8f, 816.0f, 606.2f, 808.9f),
                        PathNode.QuadTo(612.5f, 801.9f, 612.5f, 787.0f),
                        PathNode.LineTo(612.5f, 475.5f),
                        PathNode.QuadTo(612.5f, 462.3f, 604.7f, 455.1f),
                        PathNode.QuadTo(596.9f, 447.9f, 583.5f, 447.9f),
                        PathNode.LineTo(559.4f, 447.9f),
                        PathNode.QuadTo(530.3f, 447.9f, 530.3f, 475.5f),
                        PathNode.Close,
                        PathNode.MoveTo(745.8f, 1061.8f),
                        PathNode.LineTo(745.8f, 1092.9f),
                        PathNode.QuadTo(745.8f, 1113.6f, 736.5f, 1121.9f),
                        PathNode.QuadTo(727.2f, 1130.2f, 708.7f, 1130.2f),
                        PathNode.LineTo(434.2f, 1130.2f),
                        PathNode.QuadTo(416.6f, 1130.2f, 406.8f, 1121.7f),
                        PathNode.QuadTo(397.1f, 1113.1f, 397.1f, 1093.1f),
                        PathNode.LineTo(397.1f, 1061.8f),
                        PathNode.QuadTo(397.1f, 1040.6f, 406.1f, 1033.1f),
                        PathNode.QuadTo(415.2f, 1025.5f, 434.2f, 1025.5f),
                        PathNode.LineTo(708.7f, 1025.5f),
                        PathNode.QuadTo(727.2f, 1025.5f, 736.5f, 1033.4f),
                        PathNode.QuadTo(745.8f, 1041.4f, 745.8f, 1061.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchMedium!!
    }

private var _stopwatchMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Stopwatch: ImageVector
    get() {
        if (_stopwatchDemibold != null) return _stopwatchDemibold!!
        _stopwatchDemibold = ImageVector.Builder(
            name = "Stopwatch.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1056.9f, 487.5f),
                        PathNode.QuadTo(1056.9f, 619.7f, 991.8f, 731.2f),
                        PathNode.QuadTo(926.7f, 842.8f, 814.9f, 907.9f),
                        PathNode.QuadTo(703.2f, 973.0f, 571.4f, 973.0f),
                        PathNode.QuadTo(439.7f, 973.0f, 327.9f, 907.9f),
                        PathNode.QuadTo(216.2f, 842.8f, 151.0f, 731.2f),
                        PathNode.QuadTo(85.9f, 619.7f, 85.9f, 487.5f),
                        PathNode.QuadTo(85.9f, 356.3f, 151.3f, 244.7f),
                        PathNode.QuadTo(216.6f, 133.2f, 328.1f, 68.1f),
                        PathNode.QuadTo(439.7f, 3.0f, 571.4f, 3.0f),
                        PathNode.QuadTo(703.2f, 3.0f, 814.7f, 68.1f),
                        PathNode.QuadTo(926.2f, 133.2f, 991.6f, 244.7f),
                        PathNode.QuadTo(1056.9f, 356.3f, 1056.9f, 487.5f),
                        PathNode.Close,
                        PathNode.MoveTo(524.6f, 480.4f),
                        PathNode.LineTo(524.6f, 780.3f),
                        PathNode.QuadTo(524.6f, 796.6f, 532.4f, 804.4f),
                        PathNode.QuadTo(540.2f, 812.2f, 556.5f, 812.2f),
                        PathNode.LineTo(586.3f, 812.2f),
                        PathNode.QuadTo(603.6f, 812.2f, 610.9f, 804.4f),
                        PathNode.QuadTo(618.2f, 796.6f, 618.2f, 780.3f),
                        PathNode.LineTo(618.2f, 480.4f),
                        PathNode.QuadTo(618.2f, 465.7f, 609.8f, 457.6f),
                        PathNode.QuadTo(601.5f, 449.6f, 586.3f, 449.6f),
                        PathNode.LineTo(556.5f, 449.6f),
                        PathNode.QuadTo(524.6f, 449.6f, 524.6f, 480.4f),
                        PathNode.Close,
                        PathNode.MoveTo(746.7f, 1065.6f),
                        PathNode.LineTo(746.7f, 1101.6f),
                        PathNode.QuadTo(746.7f, 1122.1f, 737.3f, 1131.0f),
                        PathNode.QuadTo(727.8f, 1139.9f, 708.9f, 1139.9f),
                        PathNode.LineTo(434.0f, 1139.9f),
                        PathNode.QuadTo(416.1f, 1139.9f, 406.1f, 1130.9f),
                        PathNode.QuadTo(396.1f, 1122.0f, 396.1f, 1102.0f),
                        PathNode.LineTo(396.1f, 1065.6f),
                        PathNode.QuadTo(396.1f, 1045.0f, 405.6f, 1036.7f),
                        PathNode.QuadTo(415.0f, 1028.3f, 434.0f, 1028.3f),
                        PathNode.LineTo(708.9f, 1028.3f),
                        PathNode.QuadTo(727.8f, 1028.3f, 737.3f, 1036.9f),
                        PathNode.QuadTo(746.7f, 1045.5f, 746.7f, 1065.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchDemibold!!
    }

private var _stopwatchDemibold: ImageVector? = null
