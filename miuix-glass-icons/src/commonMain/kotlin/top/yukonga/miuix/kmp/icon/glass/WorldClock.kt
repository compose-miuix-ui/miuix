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

val MiuixIcons.Glass.WorldClock: ImageVector
    get() = MiuixIcons.Glass.Regular.WorldClock

val MiuixIcons.Glass.Light.WorldClock: ImageVector
    get() {
        if (_worldClockLight != null) return _worldClockLight!!
        _worldClockLight = ImageVector.Builder(
            name = "WorldClock.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1059.3f, 571.6f),
                        PathNode.QuadTo(1059.3f, 703.3f, 993.9f, 815.7f),
                        PathNode.QuadTo(928.4f, 928.0f, 816.5f, 993.7f),
                        PathNode.QuadTo(704.5f, 1059.3f, 571.5f, 1059.3f),
                        PathNode.QuadTo(438.5f, 1059.3f, 326.5f, 993.7f),
                        PathNode.QuadTo(214.4f, 928.0f, 149.0f, 815.6f),
                        PathNode.QuadTo(83.5f, 703.3f, 83.5f, 571.4f),
                        PathNode.QuadTo(83.5f, 439.0f, 149.0f, 326.9f),
                        PathNode.QuadTo(214.5f, 214.8f, 326.5f, 149.1f),
                        PathNode.QuadTo(438.4f, 83.5f, 571.4f, 83.5f),
                        PathNode.QuadTo(704.3f, 83.5f, 816.3f, 149.1f),
                        PathNode.QuadTo(928.3f, 214.8f, 993.8f, 326.9f),
                        PathNode.QuadTo(1059.3f, 439.0f, 1059.3f, 571.6f),
                        PathNode.Close,
                        PathNode.MoveTo(547.3f, 572.4f),
                        PathNode.LineTo(547.3f, 919.0f),
                        PathNode.QuadTo(547.3f, 927.0f, 551.3f, 930.7f),
                        PathNode.QuadTo(555.2f, 934.3f, 563.8f, 934.3f),
                        PathNode.LineTo(579.1f, 934.3f),
                        PathNode.QuadTo(587.7f, 934.3f, 591.6f, 930.7f),
                        PathNode.QuadTo(595.5f, 927.0f, 595.5f, 919.0f),
                        PathNode.LineTo(595.5f, 596.1f),
                        PathNode.LineTo(848.9f, 596.1f),
                        PathNode.QuadTo(856.5f, 596.1f, 860.4f, 591.9f),
                        PathNode.QuadTo(864.2f, 587.7f, 864.2f, 581.3f),
                        PathNode.LineTo(864.2f, 562.1f),
                        PathNode.QuadTo(864.2f, 555.8f, 860.4f, 551.5f),
                        PathNode.QuadTo(856.5f, 547.3f, 848.9f, 547.3f),
                        PathNode.LineTo(565.9f, 547.3f),
                        PathNode.QuadTo(555.0f, 547.3f, 551.2f, 553.2f),
                        PathNode.QuadTo(547.3f, 559.1f, 547.3f, 572.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldClockLight!!
    }

private var _worldClockLight: ImageVector? = null

val MiuixIcons.Glass.Normal.WorldClock: ImageVector
    get() {
        if (_worldClockNormal != null) return _worldClockNormal!!
        _worldClockNormal = ImageVector.Builder(
            name = "WorldClock.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1067.0f, 571.5f),
                        PathNode.QuadTo(1067.0f, 705.5f, 1000.4f, 819.6f),
                        PathNode.QuadTo(933.9f, 933.8f, 820.2f, 1000.4f),
                        PathNode.QuadTo(706.5f, 1067.0f, 571.5f, 1067.0f),
                        PathNode.QuadTo(436.4f, 1067.0f, 322.7f, 1000.4f),
                        PathNode.QuadTo(208.9f, 933.8f, 142.4f, 819.6f),
                        PathNode.QuadTo(75.9f, 705.5f, 75.9f, 571.4f),
                        PathNode.QuadTo(75.9f, 436.5f, 142.4f, 322.8f),
                        PathNode.QuadTo(209.0f, 209.0f, 322.7f, 142.5f),
                        PathNode.QuadTo(436.4f, 75.9f, 571.4f, 75.9f),
                        PathNode.QuadTo(706.5f, 75.9f, 820.2f, 142.5f),
                        PathNode.QuadTo(933.9f, 209.0f, 1000.4f, 322.8f),
                        PathNode.QuadTo(1067.0f, 436.5f, 1067.0f, 571.5f),
                        PathNode.Close,
                        PathNode.MoveTo(541.2f, 572.4f),
                        PathNode.LineTo(541.2f, 908.3f),
                        PathNode.QuadTo(541.2f, 919.0f, 546.2f, 923.6f),
                        PathNode.QuadTo(551.2f, 928.2f, 562.8f, 928.2f),
                        PathNode.LineTo(580.0f, 928.2f),
                        PathNode.QuadTo(591.6f, 928.2f, 596.6f, 923.6f),
                        PathNode.QuadTo(601.7f, 919.0f, 601.7f, 908.3f),
                        PathNode.LineTo(601.7f, 602.5f),
                        PathNode.LineTo(837.6f, 602.5f),
                        PathNode.QuadTo(847.5f, 602.5f, 852.5f, 597.1f),
                        PathNode.QuadTo(857.5f, 591.6f, 857.5f, 583.5f),
                        PathNode.LineTo(857.5f, 560.3f),
                        PathNode.QuadTo(857.5f, 552.1f, 852.5f, 546.7f),
                        PathNode.QuadTo(847.5f, 541.2f, 837.6f, 541.2f),
                        PathNode.LineTo(562.8f, 541.2f),
                        PathNode.QuadTo(549.8f, 541.2f, 545.5f, 548.6f),
                        PathNode.QuadTo(541.2f, 555.9f, 541.2f, 572.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldClockNormal!!
    }

private var _worldClockNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.WorldClock: ImageVector
    get() {
        if (_worldClockRegular != null) return _worldClockRegular!!
        _worldClockRegular = ImageVector.Builder(
            name = "WorldClock.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1070.4f, 571.4f),
                        PathNode.QuadTo(1070.4f, 706.4f, 1003.4f, 821.4f),
                        PathNode.QuadTo(936.4f, 936.4f, 821.9f, 1003.4f),
                        PathNode.QuadTo(707.4f, 1070.4f, 571.4f, 1070.4f),
                        PathNode.QuadTo(435.4f, 1070.4f, 320.9f, 1003.4f),
                        PathNode.QuadTo(206.4f, 936.4f, 139.4f, 821.4f),
                        PathNode.QuadTo(72.4f, 706.4f, 72.4f, 571.4f),
                        PathNode.QuadTo(72.4f, 435.4f, 139.4f, 320.9f),
                        PathNode.QuadTo(206.4f, 206.4f, 320.9f, 139.4f),
                        PathNode.QuadTo(435.4f, 72.4f, 571.4f, 72.4f),
                        PathNode.QuadTo(707.4f, 72.4f, 821.9f, 139.4f),
                        PathNode.QuadTo(936.4f, 206.4f, 1003.4f, 320.9f),
                        PathNode.QuadTo(1070.4f, 435.4f, 1070.4f, 571.4f),
                        PathNode.Close,
                        PathNode.MoveTo(538.4f, 572.4f),
                        PathNode.LineTo(538.4f, 903.4f),
                        PathNode.QuadTo(538.4f, 915.4f, 543.9f, 920.4f),
                        PathNode.QuadTo(549.4f, 925.4f, 562.4f, 925.4f),
                        PathNode.LineTo(580.4f, 925.4f),
                        PathNode.QuadTo(593.4f, 925.4f, 598.9f, 920.4f),
                        PathNode.QuadTo(604.4f, 915.4f, 604.4f, 903.4f),
                        PathNode.LineTo(604.4f, 605.4f),
                        PathNode.LineTo(832.4f, 605.4f),
                        PathNode.QuadTo(843.4f, 605.4f, 848.9f, 599.4f),
                        PathNode.QuadTo(854.4f, 593.4f, 854.4f, 584.4f),
                        PathNode.LineTo(854.4f, 559.4f),
                        PathNode.QuadTo(854.4f, 550.4f, 848.9f, 544.4f),
                        PathNode.QuadTo(843.4f, 538.4f, 832.4f, 538.4f),
                        PathNode.LineTo(561.4f, 538.4f),
                        PathNode.QuadTo(547.4f, 538.4f, 542.9f, 546.4f),
                        PathNode.QuadTo(538.4f, 554.4f, 538.4f, 572.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldClockRegular!!
    }

private var _worldClockRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.WorldClock: ImageVector
    get() {
        if (_worldClockMedium != null) return _worldClockMedium!!
        _worldClockMedium = ImageVector.Builder(
            name = "WorldClock.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1079.9f, 571.7f),
                        PathNode.QuadTo(1079.9f, 709.1f, 1011.5f, 826.2f),
                        PathNode.QuadTo(943.2f, 943.2f, 826.5f, 1011.5f),
                        PathNode.QuadTo(709.9f, 1079.9f, 571.4f, 1079.9f),
                        PathNode.QuadTo(433.0f, 1079.9f, 316.3f, 1011.5f),
                        PathNode.QuadTo(199.7f, 943.2f, 131.3f, 826.2f),
                        PathNode.QuadTo(63.0f, 709.1f, 63.0f, 571.7f),
                        PathNode.QuadTo(63.0f, 433.3f, 131.3f, 316.5f),
                        PathNode.QuadTo(199.7f, 199.7f, 316.3f, 131.3f),
                        PathNode.QuadTo(433.0f, 63.0f, 571.4f, 63.0f),
                        PathNode.QuadTo(709.9f, 63.0f, 826.5f, 131.3f),
                        PathNode.QuadTo(943.2f, 199.7f, 1011.5f, 316.5f),
                        PathNode.QuadTo(1079.9f, 433.3f, 1079.9f, 571.7f),
                        PathNode.Close,
                        PathNode.MoveTo(530.6f, 572.4f),
                        PathNode.LineTo(530.6f, 892.9f),
                        PathNode.QuadTo(530.6f, 906.2f, 536.9f, 912.2f),
                        PathNode.QuadTo(543.2f, 918.1f, 557.3f, 918.1f),
                        PathNode.LineTo(585.6f, 918.1f),
                        PathNode.QuadTo(599.6f, 918.1f, 606.0f, 912.2f),
                        PathNode.QuadTo(612.3f, 906.2f, 612.3f, 892.9f),
                        PathNode.LineTo(612.3f, 613.0f),
                        PathNode.LineTo(822.2f, 613.0f),
                        PathNode.QuadTo(834.8f, 613.0f, 841.1f, 606.3f),
                        PathNode.QuadTo(847.4f, 599.6f, 847.4f, 588.5f),
                        PathNode.LineTo(847.4f, 555.1f),
                        PathNode.QuadTo(847.4f, 543.9f, 841.1f, 537.3f),
                        PathNode.QuadTo(834.8f, 530.6f, 822.2f, 530.6f),
                        PathNode.LineTo(561.4f, 530.6f),
                        PathNode.QuadTo(544.7f, 530.6f, 537.7f, 540.6f),
                        PathNode.QuadTo(530.6f, 550.6f, 530.6f, 572.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldClockMedium!!
    }

private var _worldClockMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.WorldClock: ImageVector
    get() {
        if (_worldClockDemibold != null) return _worldClockDemibold!!
        _worldClockDemibold = ImageVector.Builder(
            name = "WorldClock.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1086.5f, 571.9f),
                        PathNode.QuadTo(1086.5f, 711.0f, 1017.2f, 829.5f),
                        PathNode.QuadTo(947.9f, 947.9f, 829.7f, 1017.2f),
                        PathNode.QuadTo(711.6f, 1086.5f, 571.4f, 1086.5f),
                        PathNode.QuadTo(431.3f, 1086.5f, 313.1f, 1017.2f),
                        PathNode.QuadTo(194.9f, 947.9f, 125.6f, 829.5f),
                        PathNode.QuadTo(56.3f, 711.0f, 56.3f, 571.9f),
                        PathNode.QuadTo(56.3f, 431.8f, 125.6f, 313.3f),
                        PathNode.QuadTo(194.9f, 194.9f, 313.1f, 125.6f),
                        PathNode.QuadTo(431.3f, 56.3f, 571.4f, 56.3f),
                        PathNode.QuadTo(711.6f, 56.3f, 829.7f, 125.6f),
                        PathNode.QuadTo(947.9f, 194.9f, 1017.2f, 313.3f),
                        PathNode.QuadTo(1086.5f, 431.8f, 1086.5f, 571.9f),
                        PathNode.Close,
                        PathNode.MoveTo(525.1f, 572.4f),
                        PathNode.LineTo(525.1f, 885.5f),
                        PathNode.QuadTo(525.1f, 899.8f, 532.0f, 906.4f),
                        PathNode.QuadTo(538.9f, 913.0f, 553.7f, 913.0f),
                        PathNode.LineTo(589.2f, 913.0f),
                        PathNode.QuadTo(604.0f, 913.0f, 610.9f, 906.4f),
                        PathNode.QuadTo(617.8f, 899.8f, 617.8f, 885.5f),
                        PathNode.LineTo(617.8f, 618.3f),
                        PathNode.LineTo(815.0f, 618.3f),
                        PathNode.QuadTo(828.7f, 618.3f, 835.6f, 611.1f),
                        PathNode.QuadTo(842.5f, 604.0f, 842.5f, 591.3f),
                        PathNode.LineTo(842.5f, 552.1f),
                        PathNode.QuadTo(842.5f, 539.4f, 835.6f, 532.3f),
                        PathNode.QuadTo(828.7f, 525.1f, 815.0f, 525.1f),
                        PathNode.LineTo(561.4f, 525.1f),
                        PathNode.QuadTo(542.8f, 525.1f, 534.0f, 536.5f),
                        PathNode.QuadTo(525.1f, 548.0f, 525.1f, 572.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldClockDemibold!!
    }

private var _worldClockDemibold: ImageVector? = null
