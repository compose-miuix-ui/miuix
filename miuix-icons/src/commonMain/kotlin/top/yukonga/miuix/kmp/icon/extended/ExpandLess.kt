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

val MiuixIcons.ExpandLess: ImageVector
    get() = MiuixIcons.Regular.ExpandLess

val MiuixIcons.Light.ExpandLess: ImageVector
    get() {
        if (_expandlessLight != null) return _expandlessLight!!
        _expandlessLight = ImageVector.Builder(
            name = "ExpandLess.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1122.0f,
            viewportHeight = 1122.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -103.5f, translationY = 937.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(829.0f, -74.0f),
                PathNode.VerticalTo(206.0f),
                PathNode.QuadTo(829.0f, 209.0f, 830.25f, 210.5f),
                PathNode.QuadTo(831.5f, 212.0f, 834.0f, 212.0f),
                PathNode.HorizontalTo(1114.0f),
                PathNode.QuadTo(1121.6153846153845f, 212.0f, 1126.8076923076924f, 217.27027027027026f),
                PathNode.QuadTo(1132.0f, 222.54054054054055f, 1132.0f, 230.27027027027026f),
                PathNode.VerticalTo(256.27027027027026f),
                PathNode.QuadTo(1132.0f, 264.0f, 1126.8076923076924f, 269.0f),
                PathNode.QuadTo(1121.6153846153845f, 274.0f, 1114.0f, 274.0f),
                PathNode.HorizontalTo(807.0f),
                PathNode.QuadTo(787.6666666666666f, 274.0f, 777.3333333333333f, 263.6666666666667f),
                PathNode.QuadTo(767.0f, 253.33333333333334f, 767.0f, 234.0f),
                PathNode.VerticalTo(-74.0f),
                PathNode.QuadTo(767.0f, -81.1923076923077f, 772.44f, -86.09615384615384f),
                PathNode.QuadTo(777.88f, -91.0f, 785.36f, -91.0f),
                PathNode.HorizontalTo(810.52f),
                PathNode.QuadTo(818.0f, -91.0f, 823.5f, -86.09615384615384f),
                PathNode.QuadTo(829.0f, -81.1923076923077f, 829.0f, -74.0f),
                PathNode.Close,
                PathNode.MoveTo(562.0f, 519.0f),
                PathNode.VerticalTo(826.0f),
                PathNode.QuadTo(562.0f, 833.1923076923077f, 557.0961538461538f, 838.0961538461538f),
                PathNode.QuadTo(552.1923076923077f, 843.0f, 545.0f, 843.0f),
                PathNode.HorizontalTo(518.7916666666666f),
                PathNode.QuadTo(511.0f, 843.0f, 506.0f, 838.0961538461538f),
                PathNode.QuadTo(501.0f, 833.1923076923077f, 501.0f, 826.0f),
                PathNode.VerticalTo(547.0f),
                PathNode.QuadTo(501.0f, 541.0f, 495.0f, 541.0f),
                PathNode.HorizontalTo(216.0f),
                PathNode.QuadTo(208.25925925925927f, 541.0f, 202.62962962962962f, 536.0f),
                PathNode.QuadTo(197.0f, 531.0f, 197.0f, 523.1694915254237f),
                PathNode.VerticalTo(496.8305084745763f),
                PathNode.QuadTo(197.0f, 489.0f, 202.62962962962962f, 484.0f),
                PathNode.QuadTo(208.25925925925927f, 479.0f, 216.0f, 479.0f),
                PathNode.HorizontalTo(522.0f),
                PathNode.QuadTo(541.3333333333334f, 479.0f, 551.6666666666667f, 489.33333333333337f),
                PathNode.QuadTo(562.0f, 499.6666666666667f, 562.0f, 519.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _expandlessLight!!
    }

private var _expandlessLight: ImageVector? = null

val MiuixIcons.Regular.ExpandLess: ImageVector
    get() {
        if (_expandlessRegular != null) return _expandlessRegular!!
        _expandlessRegular = ImageVector.Builder(
            name = "ExpandLess.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1122.0f,
            viewportHeight = 1122.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -103.5f, translationY = 936.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(843.0f, -66.0f),
                PathNode.VerticalTo(193.0f),
                PathNode.QuadTo(843.0f, 195.0f, 844.0f, 196.5f),
                PathNode.QuadTo(845.0f, 198.0f, 847.0f, 198.0f),
                PathNode.HorizontalTo(1106.0f),
                PathNode.QuadTo(1117.0f, 198.0f, 1124.5f, 205.5f),
                PathNode.QuadTo(1132.0f, 213.0f, 1132.0f, 224.0f),
                PathNode.VerticalTo(261.0f),
                PathNode.QuadTo(1132.0f, 272.0f, 1124.5f, 280.0f),
                PathNode.QuadTo(1117.0f, 288.0f, 1106.0f, 288.0f),
                PathNode.HorizontalTo(812.0f),
                PathNode.QuadTo(783.0f, 288.0f, 767.5f, 272.5f),
                PathNode.QuadTo(752.0f, 257.0f, 752.0f, 228.0f),
                PathNode.VerticalTo(-66.0f),
                PathNode.QuadTo(752.0f, -77.0f, 760.0f, -84.5f),
                PathNode.QuadTo(768.0f, -92.0f, 779.0f, -92.0f),
                PathNode.HorizontalTo(816.0f),
                PathNode.QuadTo(827.0f, -92.0f, 835.0f, -84.5f),
                PathNode.QuadTo(843.0f, -77.0f, 843.0f, -66.0f),
                PathNode.Close,
                PathNode.MoveTo(577.0f, 524.0f),
                PathNode.VerticalTo(817.0f),
                PathNode.QuadTo(577.0f, 828.0f, 569.5f, 835.5f),
                PathNode.QuadTo(562.0f, 843.0f, 551.0f, 843.0f),
                PathNode.HorizontalTo(514.0f),
                PathNode.QuadTo(503.0f, 843.0f, 495.0f, 835.5f),
                PathNode.QuadTo(487.0f, 828.0f, 487.0f, 817.0f),
                PathNode.VerticalTo(559.0f),
                PathNode.QuadTo(487.0f, 554.0f, 482.0f, 554.0f),
                PathNode.HorizontalTo(224.0f),
                PathNode.QuadTo(213.0f, 554.0f, 205.0f, 546.0f),
                PathNode.QuadTo(197.0f, 538.0f, 197.0f, 527.0f),
                PathNode.VerticalTo(490.0f),
                PathNode.QuadTo(197.0f, 479.0f, 205.0f, 471.5f),
                PathNode.QuadTo(213.0f, 464.0f, 224.0f, 464.0f),
                PathNode.HorizontalTo(517.0f),
                PathNode.QuadTo(546.0f, 464.0f, 561.5f, 479.5f),
                PathNode.QuadTo(577.0f, 495.0f, 577.0f, 524.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _expandlessRegular!!
    }

private var _expandlessRegular: ImageVector? = null

val MiuixIcons.Heavy.ExpandLess: ImageVector
    get() {
        if (_expandlessHeavy != null) return _expandlessHeavy!!
        _expandlessHeavy = ImageVector.Builder(
            name = "ExpandLess.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1153.2f,
            viewportHeight = 1153.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -87.89999999999998f, translationY = 952.1f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(856.0f, -66.0f),
                PathNode.VerticalTo(179.0f),
                PathNode.QuadTo(856.0f, 181.0f, 857.5f, 183.0f),
                PathNode.QuadTo(859.0f, 185.0f, 861.0f, 185.0f),
                PathNode.HorizontalTo(1106.0f),
                PathNode.QuadTo(1122.0f, 185.0f, 1133.5f, 196.5f),
                PathNode.QuadTo(1145.0f, 208.0f, 1145.0f, 224.0f),
                PathNode.VerticalTo(261.0f),
                PathNode.QuadTo(1145.0f, 277.0f, 1133.5f, 289.0f),
                PathNode.QuadTo(1122.0f, 301.0f, 1106.0f, 301.0f),
                PathNode.HorizontalTo(796.0f),
                PathNode.QuadTo(770.0f, 301.0f, 754.5f, 285.0f),
                PathNode.QuadTo(739.0f, 269.0f, 739.0f, 243.0f),
                PathNode.VerticalTo(-66.0f),
                PathNode.QuadTo(739.0f, -82.0f, 751.0f, -93.5f),
                PathNode.QuadTo(763.0f, -105.0f, 779.0f, -105.0f),
                PathNode.HorizontalTo(816.0f),
                PathNode.QuadTo(832.0f, -105.0f, 844.0f, -93.5f),
                PathNode.QuadTo(856.0f, -82.0f, 856.0f, -66.0f),
                PathNode.Close,
                PathNode.MoveTo(590.0f, 508.0f),
                PathNode.VerticalTo(817.0f),
                PathNode.QuadTo(590.0f, 833.0f, 578.5f, 844.5f),
                PathNode.QuadTo(567.0f, 856.0f, 551.0f, 856.0f),
                PathNode.HorizontalTo(514.0f),
                PathNode.QuadTo(497.0f, 856.0f, 485.5f, 844.5f),
                PathNode.QuadTo(474.0f, 833.0f, 474.0f, 817.0f),
                PathNode.VerticalTo(573.0f),
                PathNode.QuadTo(474.0f, 567.0f, 468.0f, 567.0f),
                PathNode.HorizontalTo(224.0f),
                PathNode.QuadTo(208.0f, 567.0f, 196.0f, 555.5f),
                PathNode.QuadTo(184.0f, 544.0f, 184.0f, 527.0f),
                PathNode.VerticalTo(490.0f),
                PathNode.QuadTo(184.0f, 474.0f, 195.5f, 462.5f),
                PathNode.QuadTo(207.0f, 451.0f, 224.0f, 451.0f),
                PathNode.HorizontalTo(533.0f),
                PathNode.QuadTo(559.0f, 451.0f, 574.5f, 467.0f),
                PathNode.QuadTo(590.0f, 483.0f, 590.0f, 508.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _expandlessHeavy!!
    }

private var _expandlessHeavy: ImageVector? = null
