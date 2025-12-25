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

val MiuixIcons.Playlist: ImageVector
    get() = MiuixIcons.Regular.Playlist

val MiuixIcons.Light.Playlist: ImageVector
    get() {
        if (_playlistLight != null) return _playlistLight!!
        _playlistLight = ImageVector.Builder(
            name = "Playlist.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1072.8f,
            viewportHeight = 1072.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -128.60000000000002f, translationY = 897.3273127753304f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(458.0f, 664.0f),
                PathNode.LineTo(263.0f, 776.0f),
                PathNode.QuadTo(255.0f, 781.0f, 246.5263157894737f, 779.5142857142857f),
                PathNode.QuadTo(238.05263157894737f, 778.0285714285715f, 232.5263157894737f, 771.3428571428572f),
                PathNode.QuadTo(227.0f, 764.6571428571428f, 227.0f, 755.0f),
                PathNode.VerticalTo(530.0f),
                PathNode.QuadTo(227.0f, 521.1764705882352f, 232.5263157894737f, 514.5588235294117f),
                PathNode.QuadTo(238.05263157894737f, 507.94117647058823f, 246.5263157894737f, 506.47058823529414f),
                PathNode.QuadTo(255.0f, 505.0f, 263.0f, 510.0f),
                PathNode.LineTo(458.0f, 622.0f),
                PathNode.QuadTo(466.0f, 627.0f, 469.0f, 635.0f),
                PathNode.QuadTo(472.0f, 643.0f, 469.0f, 651.0f),
                PathNode.QuadTo(466.0f, 659.0f, 458.0f, 664.0f),
                PathNode.Close,
                PathNode.MoveTo(1112.0f, -38.5f),
                PathNode.VerticalTo(-17.5f),
                PathNode.QuadTo(1112.0f, -7.0f, 1106.241935483871f, -2.5f),
                PathNode.QuadTo(1100.483870967742f, 2.0f, 1091.0f, 2.0f),
                PathNode.HorizontalTo(239.0f),
                PathNode.QuadTo(230.19354838709677f, 2.0f, 224.09677419354838f, -3.0f),
                PathNode.QuadTo(218.0f, -8.0f, 218.0f, -16.0f),
                PathNode.VerticalTo(-38.0f),
                PathNode.QuadTo(218.0f, -48.3448275862069f, 223.75806451612902f, -53.172413793103445f),
                PathNode.QuadTo(229.51612903225808f, -58.0f, 239.0f, -58.0f),
                PathNode.HorizontalTo(1091.0f),
                PathNode.QuadTo(1100.483870967742f, -58.0f, 1106.241935483871f, -53.5f),
                PathNode.QuadTo(1112.0f, -49.0f, 1112.0f, -38.5f),
                PathNode.Close,
                PathNode.MoveTo(1112.0f, 301.5f),
                PathNode.VerticalTo(322.5f),
                PathNode.QuadTo(1112.0f, 333.0f, 1106.241935483871f, 337.5f),
                PathNode.QuadTo(1100.483870967742f, 342.0f, 1091.0f, 342.0f),
                PathNode.HorizontalTo(239.0f),
                PathNode.QuadTo(230.19354838709677f, 342.0f, 224.09677419354838f, 337.0f),
                PathNode.QuadTo(218.0f, 332.0f, 218.0f, 324.0f),
                PathNode.VerticalTo(302.0f),
                PathNode.QuadTo(218.0f, 291.6551724137931f, 223.75806451612902f, 286.8275862068965f),
                PathNode.QuadTo(229.51612903225808f, 282.0f, 239.0f, 282.0f),
                PathNode.HorizontalTo(1091.0f),
                PathNode.QuadTo(1100.483870967742f, 282.0f, 1106.241935483871f, 286.5f),
                PathNode.QuadTo(1112.0f, 291.0f, 1112.0f, 301.5f),
                PathNode.Close,
                PathNode.MoveTo(1112.0f, 638.5f),
                PathNode.VerticalTo(659.5f),
                PathNode.QuadTo(1112.0f, 670.0f, 1106.241935483871f, 674.5f),
                PathNode.QuadTo(1100.483870967742f, 679.0f, 1091.0f, 679.0f),
                PathNode.HorizontalTo(627.0f),
                PathNode.QuadTo(618.1935483870968f, 679.0f, 612.0967741935484f, 674.0f),
                PathNode.QuadTo(606.0f, 669.0f, 606.0f, 661.0f),
                PathNode.VerticalTo(639.0f),
                PathNode.QuadTo(606.0f, 628.6551724137931f, 611.758064516129f, 623.8275862068965f),
                PathNode.QuadTo(617.516129032258f, 619.0f, 627.0f, 619.0f),
                PathNode.HorizontalTo(1091.0f),
                PathNode.QuadTo(1100.483870967742f, 619.0f, 1106.241935483871f, 623.5f),
                PathNode.QuadTo(1112.0f, 628.0f, 1112.0f, 638.5f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _playlistLight!!
    }

private var _playlistLight: ImageVector? = null

val MiuixIcons.Regular.Playlist: ImageVector
    get() {
        if (_playlistRegular != null) return _playlistRegular!!
        _playlistRegular = ImageVector.Builder(
            name = "Playlist.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1082.3999999999999f,
            viewportHeight = 1082.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -123.80000000000007f, translationY = 903.9499999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(472.0f, 672.0f),
                PathNode.LineTo(263.0f, 792.0f),
                PathNode.QuadTo(252.0f, 798.0f, 240.5f, 796.0f),
                PathNode.QuadTo(229.0f, 794.0f, 221.5f, 785.0f),
                PathNode.QuadTo(214.0f, 776.0f, 214.0f, 763.0f),
                PathNode.VerticalTo(522.0f),
                PathNode.QuadTo(214.0f, 510.0f, 221.5f, 501.0f),
                PathNode.QuadTo(229.0f, 492.0f, 240.5f, 490.0f),
                PathNode.QuadTo(252.0f, 488.0f, 263.0f, 494.0f),
                PathNode.LineTo(472.0f, 614.0f),
                PathNode.QuadTo(483.0f, 620.0f, 486.5f, 631.5f),
                PathNode.QuadTo(490.0f, 643.0f, 486.5f, 654.5f),
                PathNode.QuadTo(483.0f, 666.0f, 472.0f, 672.0f),
                PathNode.Close,
                PathNode.MoveTo(1116.0f, -43.0f),
                PathNode.VerticalTo(-13.0f),
                PathNode.QuadTo(1116.0f, 2.0f, 1107.5f, 8.5f),
                PathNode.QuadTo(1099.0f, 15.0f, 1085.0f, 15.0f),
                PathNode.HorizontalTo(245.0f),
                PathNode.QuadTo(232.0f, 15.0f, 223.0f, 8.0f),
                PathNode.QuadTo(214.0f, 1.0f, 214.0f, -12.0f),
                PathNode.VerticalTo(-42.0f),
                PathNode.QuadTo(214.0f, -57.0f, 222.5f, -64.0f),
                PathNode.QuadTo(231.0f, -71.0f, 245.0f, -71.0f),
                PathNode.HorizontalTo(1085.0f),
                PathNode.QuadTo(1099.0f, -71.0f, 1107.5f, -64.5f),
                PathNode.QuadTo(1116.0f, -58.0f, 1116.0f, -43.0f),
                PathNode.Close,
                PathNode.MoveTo(1116.0f, 297.0f),
                PathNode.VerticalTo(327.0f),
                PathNode.QuadTo(1116.0f, 342.0f, 1107.5f, 348.5f),
                PathNode.QuadTo(1099.0f, 355.0f, 1085.0f, 355.0f),
                PathNode.HorizontalTo(245.0f),
                PathNode.QuadTo(232.0f, 355.0f, 223.0f, 348.0f),
                PathNode.QuadTo(214.0f, 341.0f, 214.0f, 328.0f),
                PathNode.VerticalTo(298.0f),
                PathNode.QuadTo(214.0f, 283.0f, 222.5f, 276.0f),
                PathNode.QuadTo(231.0f, 269.0f, 245.0f, 269.0f),
                PathNode.HorizontalTo(1085.0f),
                PathNode.QuadTo(1099.0f, 269.0f, 1107.5f, 275.5f),
                PathNode.QuadTo(1116.0f, 282.0f, 1116.0f, 297.0f),
                PathNode.Close,
                PathNode.MoveTo(1116.0f, 634.0f),
                PathNode.VerticalTo(664.0f),
                PathNode.QuadTo(1116.0f, 679.0f, 1107.5f, 685.5f),
                PathNode.QuadTo(1099.0f, 692.0f, 1085.0f, 692.0f),
                PathNode.HorizontalTo(633.0f),
                PathNode.QuadTo(620.0f, 692.0f, 611.0f, 685.0f),
                PathNode.QuadTo(602.0f, 678.0f, 602.0f, 665.0f),
                PathNode.VerticalTo(635.0f),
                PathNode.QuadTo(602.0f, 620.0f, 610.5f, 613.0f),
                PathNode.QuadTo(619.0f, 606.0f, 633.0f, 606.0f),
                PathNode.HorizontalTo(1085.0f),
                PathNode.QuadTo(1099.0f, 606.0f, 1107.5f, 612.5f),
                PathNode.QuadTo(1116.0f, 619.0f, 1116.0f, 634.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _playlistRegular!!
    }

private var _playlistRegular: ImageVector? = null

val MiuixIcons.Heavy.Playlist: ImageVector
    get() {
        if (_playlistHeavy != null) return _playlistHeavy!!
        _playlistHeavy = ImageVector.Builder(
            name = "Playlist.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1116.0f,
            viewportHeight = 1116.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -107.0f, translationY = 920.875f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(479.0f, 684.0f),
                PathNode.LineTo(270.0f, 804.0f),
                PathNode.QuadTo(255.0f, 813.0f, 238.5f, 810.0f),
                PathNode.QuadTo(222.0f, 807.0f, 211.0f, 794.0f),
                PathNode.QuadTo(200.0f, 781.0f, 200.0f, 763.0f),
                PathNode.VerticalTo(522.0f),
                PathNode.QuadTo(200.0f, 505.0f, 211.0f, 492.0f),
                PathNode.QuadTo(222.0f, 479.0f, 238.5f, 476.0f),
                PathNode.QuadTo(255.0f, 473.0f, 270.0f, 482.0f),
                PathNode.LineTo(479.0f, 602.0f),
                PathNode.QuadTo(494.0f, 611.0f, 499.5f, 627.0f),
                PathNode.QuadTo(505.0f, 643.0f, 499.5f, 659.0f),
                PathNode.QuadTo(494.0f, 675.0f, 479.0f, 684.0f),
                PathNode.Close,
                PathNode.MoveTo(1130.0f, -43.0f),
                PathNode.VerticalTo(-13.0f),
                PathNode.QuadTo(1130.0f, 8.0f, 1117.0f, 18.5f),
                PathNode.QuadTo(1104.0f, 29.0f, 1085.0f, 29.0f),
                PathNode.HorizontalTo(245.0f),
                PathNode.QuadTo(226.0f, 29.0f, 213.0f, 18.0f),
                PathNode.QuadTo(200.0f, 7.0f, 200.0f, -12.0f),
                PathNode.VerticalTo(-42.0f),
                PathNode.QuadTo(200.0f, -63.0f, 212.5f, -74.0f),
                PathNode.QuadTo(225.0f, -85.0f, 245.0f, -85.0f),
                PathNode.HorizontalTo(1085.0f),
                PathNode.QuadTo(1104.0f, -85.0f, 1117.0f, -74.5f),
                PathNode.QuadTo(1130.0f, -64.0f, 1130.0f, -43.0f),
                PathNode.Close,
                PathNode.MoveTo(1130.0f, 297.0f),
                PathNode.VerticalTo(327.0f),
                PathNode.QuadTo(1130.0f, 348.0f, 1117.0f, 358.5f),
                PathNode.QuadTo(1104.0f, 369.0f, 1085.0f, 369.0f),
                PathNode.HorizontalTo(245.0f),
                PathNode.QuadTo(226.0f, 369.0f, 213.0f, 358.0f),
                PathNode.QuadTo(200.0f, 347.0f, 200.0f, 328.0f),
                PathNode.VerticalTo(298.0f),
                PathNode.QuadTo(200.0f, 277.0f, 212.5f, 266.0f),
                PathNode.QuadTo(225.0f, 255.0f, 245.0f, 255.0f),
                PathNode.HorizontalTo(1085.0f),
                PathNode.QuadTo(1104.0f, 255.0f, 1117.0f, 265.5f),
                PathNode.QuadTo(1130.0f, 276.0f, 1130.0f, 297.0f),
                PathNode.Close,
                PathNode.MoveTo(1130.0f, 634.0f),
                PathNode.VerticalTo(664.0f),
                PathNode.QuadTo(1130.0f, 685.0f, 1117.0f, 695.5f),
                PathNode.QuadTo(1104.0f, 706.0f, 1085.0f, 706.0f),
                PathNode.HorizontalTo(647.0f),
                PathNode.QuadTo(628.0f, 706.0f, 615.0f, 695.0f),
                PathNode.QuadTo(602.0f, 684.0f, 602.0f, 665.0f),
                PathNode.VerticalTo(635.0f),
                PathNode.QuadTo(602.0f, 614.0f, 614.5f, 603.0f),
                PathNode.QuadTo(627.0f, 592.0f, 647.0f, 592.0f),
                PathNode.HorizontalTo(1085.0f),
                PathNode.QuadTo(1104.0f, 592.0f, 1117.0f, 602.5f),
                PathNode.QuadTo(1130.0f, 613.0f, 1130.0f, 634.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _playlistHeavy!!
    }

private var _playlistHeavy: ImageVector? = null
