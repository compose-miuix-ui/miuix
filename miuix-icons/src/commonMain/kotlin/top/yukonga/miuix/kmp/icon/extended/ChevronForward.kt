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

val MiuixIcons.ChevronForward: ImageVector
    get() = MiuixIcons.Regular.ChevronForward

val MiuixIcons.Light.ChevronForward: ImageVector
    get() {
        if (_chevronforwardLight != null) return _chevronforwardLight!!
        _chevronforwardLight = ImageVector.Builder(
            name = "ChevronForward.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1186.8f,
            viewportHeight = 1186.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1186.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(353.4f, 1082.9f),
                        PathNode.LineTo(334.4f, 1063.9f),
                        PathNode.QuadTo(329.4f, 1058.9f, 328.9f, 1051.4f),
                        PathNode.QuadTo(328.4f, 1043.9f, 334.4f, 1038.9f),
                        PathNode.LineTo(780.4f, 592.9f),
                        PathNode.LineTo(334.4f, 146.9f),
                        PathNode.QuadTo(329.4f, 140.9f, 329.4f, 134.4f),
                        PathNode.QuadTo(329.4f, 127.9f, 336.4f, 121.9f),
                        PathNode.LineTo(349.4f, 108.9f),
                        PathNode.QuadTo(358.4f, 99.9f, 365.4f, 99.4f),
                        PathNode.QuadTo(372.4f, 98.9f, 378.4f, 105.9f),
                        PathNode.LineTo(851.4f, 577.9f),
                        PathNode.QuadTo(858.4f, 583.9f, 858.4f, 592.4f),
                        PathNode.QuadTo(858.4f, 600.9f, 852.4f, 607.9f),
                        PathNode.LineTo(377.4f, 1081.9f),
                        PathNode.QuadTo(372.4f, 1086.9f, 365.4f, 1087.4f),
                        PathNode.QuadTo(358.4f, 1087.9f, 353.4f, 1082.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronforwardLight!!
    }

private var _chevronforwardLight: ImageVector? = null

val MiuixIcons.Normal.ChevronForward: ImageVector
    get() {
        if (_chevronforwardNormal != null) return _chevronforwardNormal!!
        _chevronforwardNormal = ImageVector.Builder(
            name = "ChevronForward.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1200.8f,
            viewportHeight = 1200.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1200.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(359.7f, 1094.4f),
                        PathNode.LineTo(333.9f, 1069.2f),
                        PathNode.QuadTo(327.5f, 1062.1f, 327.0f, 1052.2f),
                        PathNode.QuadTo(326.5f, 1042.3f, 333.9f, 1035.3f),
                        PathNode.LineTo(769.5f, 599.6f),
                        PathNode.LineTo(333.9f, 163.2f),
                        PathNode.QuadTo(326.8f, 155.8f, 327.5f, 146.9f),
                        PathNode.QuadTo(328.2f, 138.0f, 336.5f, 129.9f),
                        PathNode.LineTo(353.0f, 113.5f),
                        PathNode.QuadTo(365.4f, 101.1f, 374.8f, 100.6f),
                        PathNode.QuadTo(384.2f, 100.1f, 393.0f, 109.1f),
                        PathNode.LineTo(866.7f, 581.8f),
                        PathNode.QuadTo(874.4f, 589.2f, 874.4f, 599.4f),
                        PathNode.QuadTo(874.4f, 609.6f, 867.7f, 616.6f),
                        PathNode.LineTo(391.3f, 1092.7f),
                        PathNode.QuadTo(384.2f, 1099.8f, 375.2f, 1100.3f),
                        PathNode.QuadTo(366.1f, 1100.8f, 359.7f, 1094.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronforwardNormal!!
    }

private var _chevronforwardNormal: ImageVector? = null

val MiuixIcons.Regular.ChevronForward: ImageVector
    get() {
        if (_chevronforwardRegular != null) return _chevronforwardRegular!!
        _chevronforwardRegular = ImageVector.Builder(
            name = "ChevronForward.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1207.2f,
            viewportHeight = 1207.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1207.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(362.6f, 1099.6f),
                        PathNode.LineTo(333.6f, 1071.6f),
                        PathNode.QuadTo(326.6f, 1063.6f, 326.1f, 1052.6f),
                        PathNode.QuadTo(325.6f, 1041.6f, 333.6f, 1033.6f),
                        PathNode.LineTo(764.6f, 602.6f),
                        PathNode.LineTo(333.6f, 170.6f),
                        PathNode.QuadTo(325.6f, 162.6f, 326.6f, 152.6f),
                        PathNode.QuadTo(327.6f, 142.6f, 336.6f, 133.6f),
                        PathNode.LineTo(354.6f, 115.6f),
                        PathNode.QuadTo(368.6f, 101.6f, 379.1f, 101.1f),
                        PathNode.QuadTo(389.6f, 100.6f, 399.6f, 110.6f),
                        PathNode.LineTo(873.6f, 583.6f),
                        PathNode.QuadTo(881.6f, 591.6f, 881.6f, 602.6f),
                        PathNode.QuadTo(881.6f, 613.6f, 874.6f, 620.6f),
                        PathNode.LineTo(397.6f, 1097.6f),
                        PathNode.QuadTo(389.6f, 1105.6f, 379.6f, 1106.1f),
                        PathNode.QuadTo(369.6f, 1106.6f, 362.6f, 1099.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronforwardRegular!!
    }

private var _chevronforwardRegular: ImageVector? = null

val MiuixIcons.Medium.ChevronForward: ImageVector
    get() {
        if (_chevronforwardMedium != null) return _chevronforwardMedium!!
        _chevronforwardMedium = ImageVector.Builder(
            name = "ChevronForward.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1226.3f,
            viewportHeight = 1226.3f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1226.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(366.2f, 1115.3f),
                        PathNode.LineTo(337.2f, 1087.3f),
                        PathNode.QuadTo(327.9f, 1077.0f, 327.4f, 1062.4f),
                        PathNode.QuadTo(326.9f, 1047.9f, 337.2f, 1037.5f),
                        PathNode.LineTo(763.0f, 612.4f),
                        PathNode.LineTo(337.2f, 186.3f),
                        PathNode.QuadTo(327.5f, 176.0f, 328.2f, 162.4f),
                        PathNode.QuadTo(328.9f, 148.9f, 340.2f, 137.5f),
                        PathNode.LineTo(358.2f, 119.5f),
                        PathNode.QuadTo(375.2f, 103.2f, 388.6f, 102.7f),
                        PathNode.QuadTo(402.1f, 102.2f, 415.0f, 114.5f),
                        PathNode.LineTo(889.0f, 587.5f),
                        PathNode.QuadTo(898.8f, 597.3f, 899.1f, 612.1f),
                        PathNode.QuadTo(899.4f, 627.0f, 890.0f, 636.3f),
                        PathNode.LineTo(413.0f, 1113.3f),
                        PathNode.QuadTo(403.2f, 1123.1f, 389.7f, 1123.6f),
                        PathNode.QuadTo(376.2f, 1124.1f, 366.2f, 1115.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronforwardMedium!!
    }

private var _chevronforwardMedium: ImageVector? = null

val MiuixIcons.Demibold.ChevronForward: ImageVector
    get() {
        if (_chevronforwardDemibold != null) return _chevronforwardDemibold!!
        _chevronforwardDemibold = ImageVector.Builder(
            name = "ChevronForward.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1239.6f,
            viewportHeight = 1239.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1239.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(368.8f, 1126.3f),
                        PathNode.LineTo(339.8f, 1098.3f),
                        PathNode.QuadTo(328.8f, 1086.3f, 328.3f, 1069.3f),
                        PathNode.QuadTo(327.8f, 1052.3f, 339.8f, 1040.3f),
                        PathNode.LineTo(761.8f, 619.3f),
                        PathNode.LineTo(339.8f, 197.3f),
                        PathNode.QuadTo(328.8f, 185.3f, 329.3f, 169.3f),
                        PathNode.QuadTo(329.8f, 153.3f, 342.8f, 140.3f),
                        PathNode.LineTo(360.8f, 122.3f),
                        PathNode.QuadTo(379.8f, 104.3f, 395.3f, 103.8f),
                        PathNode.QuadTo(410.8f, 103.3f, 425.8f, 117.3f),
                        PathNode.LineTo(899.8f, 590.3f),
                        PathNode.QuadTo(910.8f, 601.3f, 911.3f, 618.8f),
                        PathNode.QuadTo(911.8f, 636.3f, 900.8f, 647.3f),
                        PathNode.LineTo(423.8f, 1124.3f),
                        PathNode.QuadTo(412.8f, 1135.3f, 396.8f, 1135.8f),
                        PathNode.QuadTo(380.8f, 1136.3f, 368.8f, 1126.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronforwardDemibold!!
    }

private var _chevronforwardDemibold: ImageVector? = null
