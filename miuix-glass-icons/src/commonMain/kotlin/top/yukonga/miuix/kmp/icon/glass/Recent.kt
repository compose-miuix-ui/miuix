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

val MiuixIcons.Glass.Recent: ImageVector
    get() = MiuixIcons.Glass.Regular.Recent

val MiuixIcons.Glass.Light.Recent: ImageVector
    get() {
        if (_recentLight != null) return _recentLight!!
        _recentLight = ImageVector.Builder(
            name = "Recent.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1059.3f, 571.9f),
                        PathNode.QuadTo(1059.3f, 703.6f, 993.9f, 815.9f),
                        PathNode.QuadTo(928.4f, 928.3f, 816.5f, 993.9f),
                        PathNode.QuadTo(704.5f, 1059.6f, 571.5f, 1059.6f),
                        PathNode.QuadTo(438.5f, 1059.6f, 326.5f, 993.9f),
                        PathNode.QuadTo(214.4f, 928.3f, 149.0f, 815.9f),
                        PathNode.QuadTo(83.5f, 703.6f, 83.5f, 571.7f),
                        PathNode.QuadTo(83.5f, 439.3f, 149.0f, 327.3f),
                        PathNode.QuadTo(214.5f, 215.3f, 326.5f, 149.3f),
                        PathNode.QuadTo(438.4f, 83.3f, 571.4f, 83.3f),
                        PathNode.QuadTo(704.3f, 83.3f, 816.3f, 149.3f),
                        PathNode.QuadTo(928.3f, 215.3f, 993.8f, 327.3f),
                        PathNode.QuadTo(1059.3f, 439.3f, 1059.3f, 571.9f),
                        PathNode.Close,
                        PathNode.MoveTo(547.3f, 572.2f),
                        PathNode.LineTo(547.3f, 918.7f),
                        PathNode.QuadTo(547.3f, 926.7f, 551.3f, 930.4f),
                        PathNode.QuadTo(555.2f, 934.0f, 563.8f, 934.0f),
                        PathNode.LineTo(579.1f, 934.0f),
                        PathNode.QuadTo(587.7f, 934.0f, 591.6f, 930.4f),
                        PathNode.QuadTo(595.5f, 926.7f, 595.5f, 918.7f),
                        PathNode.LineTo(595.5f, 595.8f),
                        PathNode.LineTo(848.9f, 595.8f),
                        PathNode.QuadTo(856.5f, 595.8f, 860.4f, 591.6f),
                        PathNode.QuadTo(864.2f, 587.4f, 864.2f, 581.0f),
                        PathNode.LineTo(864.2f, 561.8f),
                        PathNode.QuadTo(864.2f, 555.5f, 860.4f, 551.3f),
                        PathNode.QuadTo(856.5f, 547.0f, 848.9f, 547.0f),
                        PathNode.LineTo(565.9f, 547.0f),
                        PathNode.QuadTo(555.0f, 547.0f, 551.2f, 552.9f),
                        PathNode.QuadTo(547.3f, 558.9f, 547.3f, 572.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentLight!!
    }

private var _recentLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Recent: ImageVector
    get() {
        if (_recentNormal != null) return _recentNormal!!
        _recentNormal = ImageVector.Builder(
            name = "Recent.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1067.0f, 571.9f),
                        PathNode.QuadTo(1067.0f, 705.9f, 1000.4f, 820.1f),
                        PathNode.QuadTo(933.9f, 934.2f, 820.2f, 1000.8f),
                        PathNode.QuadTo(706.5f, 1067.4f, 571.5f, 1067.4f),
                        PathNode.QuadTo(436.4f, 1067.4f, 322.7f, 1000.8f),
                        PathNode.QuadTo(208.9f, 934.2f, 142.4f, 820.1f),
                        PathNode.QuadTo(75.9f, 705.9f, 75.9f, 571.9f),
                        PathNode.QuadTo(75.9f, 437.0f, 142.4f, 323.3f),
                        PathNode.QuadTo(209.0f, 209.5f, 322.7f, 142.5f),
                        PathNode.QuadTo(436.4f, 75.5f, 571.4f, 75.5f),
                        PathNode.QuadTo(706.5f, 75.5f, 820.2f, 142.5f),
                        PathNode.QuadTo(933.9f, 209.5f, 1000.4f, 323.3f),
                        PathNode.QuadTo(1067.0f, 437.0f, 1067.0f, 571.9f),
                        PathNode.Close,
                        PathNode.MoveTo(541.2f, 572.0f),
                        PathNode.LineTo(541.2f, 907.9f),
                        PathNode.QuadTo(541.2f, 918.6f, 546.2f, 923.2f),
                        PathNode.QuadTo(551.2f, 927.8f, 562.8f, 927.8f),
                        PathNode.LineTo(580.0f, 927.8f),
                        PathNode.QuadTo(591.6f, 927.8f, 596.6f, 923.2f),
                        PathNode.QuadTo(601.7f, 918.6f, 601.7f, 907.9f),
                        PathNode.LineTo(601.7f, 602.1f),
                        PathNode.LineTo(837.6f, 602.1f),
                        PathNode.QuadTo(847.5f, 602.1f, 852.5f, 596.6f),
                        PathNode.QuadTo(857.5f, 591.2f, 857.5f, 583.0f),
                        PathNode.LineTo(857.5f, 559.8f),
                        PathNode.QuadTo(857.5f, 551.7f, 852.5f, 546.2f),
                        PathNode.QuadTo(847.5f, 540.8f, 837.6f, 540.8f),
                        PathNode.LineTo(562.8f, 540.8f),
                        PathNode.QuadTo(549.8f, 540.8f, 545.5f, 548.1f),
                        PathNode.QuadTo(541.2f, 555.5f, 541.2f, 572.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentNormal!!
    }

private var _recentNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Recent: ImageVector
    get() {
        if (_recentRegular != null) return _recentRegular!!
        _recentRegular = ImageVector.Builder(
            name = "Recent.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1070.4f, 571.9f),
                        PathNode.QuadTo(1070.4f, 706.9f, 1003.4f, 821.9f),
                        PathNode.QuadTo(936.4f, 936.9f, 821.9f, 1003.9f),
                        PathNode.QuadTo(707.4f, 1070.9f, 571.4f, 1070.9f),
                        PathNode.QuadTo(435.4f, 1070.9f, 320.9f, 1003.9f),
                        PathNode.QuadTo(206.4f, 936.9f, 139.4f, 821.9f),
                        PathNode.QuadTo(72.4f, 706.9f, 72.4f, 571.9f),
                        PathNode.QuadTo(72.4f, 435.9f, 139.4f, 321.4f),
                        PathNode.QuadTo(206.4f, 206.9f, 320.9f, 139.4f),
                        PathNode.QuadTo(435.4f, 71.9f, 571.4f, 71.9f),
                        PathNode.QuadTo(707.4f, 71.9f, 821.9f, 139.4f),
                        PathNode.QuadTo(936.4f, 206.9f, 1003.4f, 321.4f),
                        PathNode.QuadTo(1070.4f, 435.9f, 1070.4f, 571.9f),
                        PathNode.Close,
                        PathNode.MoveTo(538.4f, 571.9f),
                        PathNode.LineTo(538.4f, 902.9f),
                        PathNode.QuadTo(538.4f, 914.9f, 543.9f, 919.9f),
                        PathNode.QuadTo(549.4f, 924.9f, 562.4f, 924.9f),
                        PathNode.LineTo(580.4f, 924.9f),
                        PathNode.QuadTo(593.4f, 924.9f, 598.9f, 919.9f),
                        PathNode.QuadTo(604.4f, 914.9f, 604.4f, 902.9f),
                        PathNode.LineTo(604.4f, 604.9f),
                        PathNode.LineTo(832.4f, 604.9f),
                        PathNode.QuadTo(843.4f, 604.9f, 848.9f, 598.9f),
                        PathNode.QuadTo(854.4f, 592.9f, 854.4f, 583.9f),
                        PathNode.LineTo(854.4f, 558.9f),
                        PathNode.QuadTo(854.4f, 549.9f, 848.9f, 543.9f),
                        PathNode.QuadTo(843.4f, 537.9f, 832.4f, 537.9f),
                        PathNode.LineTo(561.4f, 537.9f),
                        PathNode.QuadTo(547.4f, 537.9f, 542.9f, 545.9f),
                        PathNode.QuadTo(538.4f, 553.9f, 538.4f, 571.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentRegular!!
    }

private var _recentRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Recent: ImageVector
    get() {
        if (_recentMedium != null) return _recentMedium!!
        _recentMedium = ImageVector.Builder(
            name = "Recent.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1079.9f, 571.8f),
                        PathNode.QuadTo(1079.9f, 709.5f, 1011.5f, 826.5f),
                        PathNode.QuadTo(943.2f, 943.6f, 826.5f, 1011.9f),
                        PathNode.QuadTo(709.9f, 1080.3f, 571.4f, 1080.3f),
                        PathNode.QuadTo(433.0f, 1080.3f, 316.3f, 1011.9f),
                        PathNode.QuadTo(199.7f, 943.6f, 131.3f, 826.5f),
                        PathNode.QuadTo(63.0f, 709.5f, 63.0f, 571.8f),
                        PathNode.QuadTo(63.0f, 433.4f, 131.3f, 316.7f),
                        PathNode.QuadTo(199.7f, 200.0f, 316.3f, 131.3f),
                        PathNode.QuadTo(433.0f, 62.6f, 571.4f, 62.6f),
                        PathNode.QuadTo(709.9f, 62.6f, 826.5f, 131.3f),
                        PathNode.QuadTo(943.2f, 200.0f, 1011.5f, 316.7f),
                        PathNode.QuadTo(1079.9f, 433.4f, 1079.9f, 571.8f),
                        PathNode.Close,
                        PathNode.MoveTo(530.6f, 572.1f),
                        PathNode.LineTo(530.6f, 892.5f),
                        PathNode.QuadTo(530.6f, 905.9f, 536.9f, 911.8f),
                        PathNode.QuadTo(543.2f, 917.8f, 557.3f, 917.8f),
                        PathNode.LineTo(585.6f, 917.8f),
                        PathNode.QuadTo(599.6f, 917.8f, 606.0f, 911.8f),
                        PathNode.QuadTo(612.3f, 905.9f, 612.3f, 892.5f),
                        PathNode.LineTo(612.3f, 612.6f),
                        PathNode.LineTo(822.2f, 612.6f),
                        PathNode.QuadTo(834.8f, 612.6f, 841.1f, 606.0f),
                        PathNode.QuadTo(847.4f, 599.3f, 847.4f, 588.1f),
                        PathNode.LineTo(847.4f, 554.7f),
                        PathNode.QuadTo(847.4f, 543.6f, 841.1f, 536.9f),
                        PathNode.QuadTo(834.8f, 530.2f, 822.2f, 530.2f),
                        PathNode.LineTo(561.4f, 530.2f),
                        PathNode.QuadTo(544.7f, 530.2f, 537.7f, 540.3f),
                        PathNode.QuadTo(530.6f, 550.3f, 530.6f, 572.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentMedium!!
    }

private var _recentMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Recent: ImageVector
    get() {
        if (_recentDemibold != null) return _recentDemibold!!
        _recentDemibold = ImageVector.Builder(
            name = "Recent.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1086.5f, 571.7f),
                        PathNode.QuadTo(1086.5f, 711.3f, 1017.2f, 829.7f),
                        PathNode.QuadTo(947.9f, 948.2f, 829.7f, 1017.5f),
                        PathNode.QuadTo(711.6f, 1086.8f, 571.4f, 1086.8f),
                        PathNode.QuadTo(431.3f, 1086.8f, 313.1f, 1017.5f),
                        PathNode.QuadTo(194.9f, 948.2f, 125.6f, 829.7f),
                        PathNode.QuadTo(56.3f, 711.3f, 56.3f, 571.7f),
                        PathNode.QuadTo(56.3f, 431.6f, 125.6f, 313.4f),
                        PathNode.QuadTo(194.9f, 195.2f, 313.1f, 125.6f),
                        PathNode.QuadTo(431.3f, 56.1f, 571.4f, 56.1f),
                        PathNode.QuadTo(711.6f, 56.1f, 829.7f, 125.6f),
                        PathNode.QuadTo(947.9f, 195.2f, 1017.2f, 313.4f),
                        PathNode.QuadTo(1086.5f, 431.6f, 1086.5f, 571.7f),
                        PathNode.Close,
                        PathNode.MoveTo(525.1f, 572.2f),
                        PathNode.LineTo(525.1f, 885.2f),
                        PathNode.QuadTo(525.1f, 899.5f, 532.0f, 906.1f),
                        PathNode.QuadTo(538.9f, 912.8f, 553.7f, 912.8f),
                        PathNode.LineTo(589.2f, 912.8f),
                        PathNode.QuadTo(604.0f, 912.8f, 610.9f, 906.1f),
                        PathNode.QuadTo(617.8f, 899.5f, 617.8f, 885.2f),
                        PathNode.LineTo(617.8f, 618.0f),
                        PathNode.LineTo(815.0f, 618.0f),
                        PathNode.QuadTo(828.7f, 618.0f, 835.6f, 610.9f),
                        PathNode.QuadTo(842.5f, 603.7f, 842.5f, 591.1f),
                        PathNode.LineTo(842.5f, 551.8f),
                        PathNode.QuadTo(842.5f, 539.1f, 835.6f, 532.0f),
                        PathNode.QuadTo(828.7f, 524.8f, 815.0f, 524.8f),
                        PathNode.LineTo(561.4f, 524.8f),
                        PathNode.QuadTo(542.8f, 524.8f, 534.0f, 536.3f),
                        PathNode.QuadTo(525.1f, 547.7f, 525.1f, 572.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentDemibold!!
    }

private var _recentDemibold: ImageVector? = null
