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

val MiuixIcons.Glass.ChevronForward: ImageVector
    get() = MiuixIcons.Glass.Regular.ChevronForward

val MiuixIcons.Glass.Light.ChevronForward: ImageVector
    get() {
        if (_chevronForwardLight != null) return _chevronForwardLight!!
        _chevronForwardLight = ImageVector.Builder(
            name = "ChevronForward.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(331.6f, 1061.0f),
                        PathNode.LineTo(312.0f, 1041.9f),
                        PathNode.QuadTo(307.2f, 1036.5f, 306.9f, 1029.1f),
                        PathNode.QuadTo(306.6f, 1021.6f, 312.0f, 1016.3f),
                        PathNode.LineTo(758.1f, 570.6f),
                        PathNode.LineTo(312.4f, 124.4f),
                        PathNode.QuadTo(306.6f, 118.6f, 307.2f, 112.2f),
                        PathNode.QuadTo(307.7f, 105.8f, 314.1f, 99.4f),
                        PathNode.LineTo(327.2f, 86.3f),
                        PathNode.QuadTo(335.9f, 77.6f, 343.0f, 77.4f),
                        PathNode.QuadTo(350.2f, 77.1f, 356.6f, 83.5f),
                        PathNode.LineTo(829.3f, 555.2f),
                        PathNode.QuadTo(836.0f, 561.9f, 836.0f, 570.6f),
                        PathNode.QuadTo(836.0f, 579.4f, 829.9f, 585.5f),
                        PathNode.LineTo(355.5f, 1059.9f),
                        PathNode.QuadTo(350.2f, 1065.2f, 343.3f, 1065.5f),
                        PathNode.QuadTo(336.4f, 1065.8f, 331.6f, 1061.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronForwardLight!!
    }

private var _chevronForwardLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ChevronForward: ImageVector
    get() {
        if (_chevronForwardNormal != null) return _chevronForwardNormal!!
        _chevronForwardNormal = ImageVector.Builder(
            name = "ChevronForward.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(330.6f, 1065.4f),
                        PathNode.LineTo(304.6f, 1040.2f),
                        PathNode.QuadTo(298.3f, 1033.0f, 297.8f, 1023.1f),
                        PathNode.QuadTo(297.4f, 1013.2f, 304.6f, 1006.1f),
                        PathNode.LineTo(740.3f, 570.5f),
                        PathNode.LineTo(304.7f, 134.0f),
                        PathNode.QuadTo(297.4f, 126.7f, 298.3f, 117.9f),
                        PathNode.QuadTo(299.1f, 109.0f, 307.3f, 100.8f),
                        PathNode.LineTo(323.8f, 84.3f),
                        PathNode.QuadTo(336.1f, 72.0f, 345.5f, 71.6f),
                        PathNode.QuadTo(355.0f, 71.1f, 363.9f, 80.0f),
                        PathNode.LineTo(837.5f, 552.6f),
                        PathNode.QuadTo(845.1f, 560.2f, 845.1f, 570.5f),
                        PathNode.QuadTo(845.1f, 580.8f, 838.3f, 587.5f),
                        PathNode.LineTo(362.2f, 1063.7f),
                        PathNode.QuadTo(355.0f, 1070.9f, 346.0f, 1071.3f),
                        PathNode.QuadTo(336.9f, 1071.7f, 330.6f, 1065.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronForwardNormal!!
    }

private var _chevronForwardNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ChevronForward: ImageVector
    get() {
        if (_chevronForwardRegular != null) return _chevronForwardRegular!!
        _chevronForwardRegular = ImageVector.Builder(
            name = "ChevronForward.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(330.2f, 1067.4f),
                        PathNode.LineTo(301.2f, 1039.4f),
                        PathNode.QuadTo(294.2f, 1031.4f, 293.7f, 1020.4f),
                        PathNode.QuadTo(293.2f, 1009.4f, 301.2f, 1001.4f),
                        PathNode.LineTo(732.2f, 570.4f),
                        PathNode.LineTo(301.2f, 138.4f),
                        PathNode.QuadTo(293.2f, 130.4f, 294.2f, 120.4f),
                        PathNode.QuadTo(295.2f, 110.4f, 304.2f, 101.4f),
                        PathNode.LineTo(322.2f, 83.4f),
                        PathNode.QuadTo(336.2f, 69.4f, 346.7f, 68.9f),
                        PathNode.QuadTo(357.2f, 68.4f, 367.2f, 78.4f),
                        PathNode.LineTo(841.2f, 551.4f),
                        PathNode.QuadTo(849.2f, 559.4f, 849.2f, 570.4f),
                        PathNode.QuadTo(849.2f, 581.4f, 842.2f, 588.4f),
                        PathNode.LineTo(365.2f, 1065.4f),
                        PathNode.QuadTo(357.2f, 1073.4f, 347.2f, 1073.9f),
                        PathNode.QuadTo(337.2f, 1074.4f, 330.2f, 1067.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronForwardRegular!!
    }

private var _chevronForwardRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ChevronForward: ImageVector
    get() {
        if (_chevronForwardMedium != null) return _chevronForwardMedium!!
        _chevronForwardMedium = ImageVector.Builder(
            name = "ChevronForward.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(324.5f, 1073.4f),
                        PathNode.LineTo(295.5f, 1045.2f),
                        PathNode.QuadTo(286.1f, 1034.7f, 285.6f, 1020.4f),
                        PathNode.QuadTo(285.1f, 1006.0f, 295.5f, 995.8f),
                        PathNode.LineTo(720.8f, 570.5f),
                        PathNode.LineTo(295.5f, 144.2f),
                        PathNode.QuadTo(285.1f, 133.7f, 286.1f, 120.5f),
                        PathNode.QuadTo(287.1f, 107.2f, 298.5f, 95.8f),
                        PathNode.LineTo(316.5f, 77.8f),
                        PathNode.QuadTo(332.9f, 61.4f, 346.7f, 60.9f),
                        PathNode.QuadTo(360.4f, 60.4f, 372.9f, 72.8f),
                        PathNode.LineTo(846.9f, 545.8f),
                        PathNode.QuadTo(857.3f, 556.0f, 857.3f, 570.4f),
                        PathNode.QuadTo(857.3f, 584.7f, 847.9f, 594.2f),
                        PathNode.LineTo(370.9f, 1071.2f),
                        PathNode.QuadTo(360.7f, 1081.3f, 347.5f, 1082.0f),
                        PathNode.QuadTo(334.2f, 1082.6f, 324.5f, 1073.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronForwardMedium!!
    }

private var _chevronForwardMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ChevronForward: ImageVector
    get() {
        if (_chevronForwardDemibold != null) return _chevronForwardDemibold!!
        _chevronForwardDemibold = ImageVector.Builder(
            name = "ChevronForward.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 85.7f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(320.5f, 1077.6f),
                        PathNode.LineTo(291.5f, 1049.2f),
                        PathNode.QuadTo(280.4f, 1037.0f, 279.9f, 1020.3f),
                        PathNode.QuadTo(279.4f, 1003.6f, 291.5f, 991.9f),
                        PathNode.LineTo(712.9f, 570.5f),
                        PathNode.LineTo(291.5f, 148.2f),
                        PathNode.QuadTo(279.4f, 136.0f, 280.4f, 120.5f),
                        PathNode.QuadTo(281.4f, 105.0f, 294.5f, 91.9f),
                        PathNode.LineTo(312.5f, 73.9f),
                        PathNode.QuadTo(330.7f, 55.7f, 346.7f, 55.2f),
                        PathNode.QuadTo(362.7f, 54.7f, 376.8f, 68.9f),
                        PathNode.LineTo(850.8f, 541.9f),
                        PathNode.QuadTo(863.0f, 553.6f, 863.0f, 570.3f),
                        PathNode.QuadTo(863.0f, 587.0f, 851.8f, 598.2f),
                        PathNode.LineTo(374.8f, 1075.2f),
                        PathNode.QuadTo(363.2f, 1086.9f, 347.6f, 1087.6f),
                        PathNode.QuadTo(332.1f, 1088.3f, 320.5f, 1077.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _chevronForwardDemibold!!
    }

private var _chevronForwardDemibold: ImageVector? = null
