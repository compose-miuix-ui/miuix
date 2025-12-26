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

val MiuixIcons.Pause: ImageVector
    get() = MiuixIcons.Regular.Pause

val MiuixIcons.Light.Pause: ImageVector
    get() {
        if (_pauseLight != null) return _pauseLight!!
        _pauseLight = ImageVector.Builder(
            name = "Pause.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1117.2f,
            viewportHeight = 1117.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -106.39999999999998f, translationY = 934.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(972.0f, -71.0f),
                        PathNode.VerticalTo(821.0f),
                        PathNode.QuadTo(972.0f, 830.3333333333334f, 966.6666666666667f, 835.6666666666667f),
                        PathNode.QuadTo(961.3333333333334f, 841.0f, 952.0f, 841.0f),
                        PathNode.HorizontalTo(929.0f),
                        PathNode.QuadTo(919.6666666666666f, 841.0f, 914.3333333333333f, 835.6666666666667f),
                        PathNode.QuadTo(909.0f, 830.3333333333334f, 909.0f, 821.0f),
                        PathNode.VerticalTo(-71.0f),
                        PathNode.QuadTo(909.0f, -79.86666666666666f, 914.3333333333333f, -84.93333333333334f),
                        PathNode.QuadTo(919.6666666666666f, -90.0f, 929.0f, -90.0f),
                        PathNode.HorizontalTo(952.0f),
                        PathNode.QuadTo(961.3333333333334f, -90.0f, 966.6666666666667f, -84.93333333333334f),
                        PathNode.QuadTo(972.0f, -79.86666666666666f, 972.0f, -71.0f),
                        PathNode.Close,
                        PathNode.MoveTo(420.0f, -71.0f),
                        PathNode.VerticalTo(821.0f),
                        PathNode.QuadTo(420.0f, 830.3333333333334f, 415.0f, 835.6666666666667f),
                        PathNode.QuadTo(410.0f, 841.0f, 400.0f, 841.0f),
                        PathNode.HorizontalTo(377.0f),
                        PathNode.QuadTo(368.1333333333333f, 841.0f, 363.06666666666666f, 835.6666666666667f),
                        PathNode.QuadTo(358.0f, 830.3333333333334f, 358.0f, 821.0f),
                        PathNode.VerticalTo(-71.0f),
                        PathNode.QuadTo(358.0f, -79.86666666666666f, 363.06666666666666f, -84.93333333333334f),
                        PathNode.QuadTo(368.1333333333333f, -90.0f, 377.0f, -90.0f),
                        PathNode.HorizontalTo(399.5f),
                        PathNode.QuadTo(410.0f, -90.0f, 415.0f, -84.93333333333334f),
                        PathNode.QuadTo(420.0f, -79.86666666666666f, 420.0f, -71.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _pauseLight!!
    }

private var _pauseLight: ImageVector? = null

val MiuixIcons.Regular.Pause: ImageVector
    get() {
        if (_pauseRegular != null) return _pauseRegular!!
        _pauseRegular = ImageVector.Builder(
            name = "Pause.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1134.0f,
            viewportHeight = 1134.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -97.5f, translationY = 942.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(990.0f, -67.0f),
                        PathNode.VerticalTo(818.0f),
                        PathNode.QuadTo(990.0f, 832.0f, 982.0f, 840.0f),
                        PathNode.QuadTo(974.0f, 848.0f, 960.0f, 848.0f),
                        PathNode.HorizontalTo(930.0f),
                        PathNode.QuadTo(916.0f, 848.0f, 908.0f, 840.0f),
                        PathNode.QuadTo(900.0f, 832.0f, 900.0f, 818.0f),
                        PathNode.VerticalTo(-67.0f),
                        PathNode.QuadTo(900.0f, -81.0f, 908.0f, -89.0f),
                        PathNode.QuadTo(916.0f, -97.0f, 930.0f, -97.0f),
                        PathNode.HorizontalTo(960.0f),
                        PathNode.QuadTo(974.0f, -97.0f, 982.0f, -89.0f),
                        PathNode.QuadTo(990.0f, -81.0f, 990.0f, -67.0f),
                        PathNode.Close,
                        PathNode.MoveTo(429.0f, -67.0f),
                        PathNode.VerticalTo(818.0f),
                        PathNode.QuadTo(429.0f, 832.0f, 421.0f, 840.0f),
                        PathNode.QuadTo(413.0f, 848.0f, 399.0f, 848.0f),
                        PathNode.HorizontalTo(369.0f),
                        PathNode.QuadTo(355.0f, 848.0f, 347.0f, 840.0f),
                        PathNode.QuadTo(339.0f, 832.0f, 339.0f, 818.0f),
                        PathNode.VerticalTo(-67.0f),
                        PathNode.QuadTo(339.0f, -81.0f, 347.0f, -89.0f),
                        PathNode.QuadTo(355.0f, -97.0f, 369.0f, -97.0f),
                        PathNode.HorizontalTo(399.0f),
                        PathNode.QuadTo(413.0f, -97.0f, 421.0f, -89.0f),
                        PathNode.QuadTo(429.0f, -81.0f, 429.0f, -67.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _pauseRegular!!
    }

private var _pauseRegular: ImageVector? = null

val MiuixIcons.Heavy.Pause: ImageVector
    get() {
        if (_pauseHeavy != null) return _pauseHeavy!!
        _pauseHeavy = ImageVector.Builder(
            name = "Pause.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1172.3999999999999f,
            viewportHeight = 1172.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -78.30000000000007f, translationY = 961.6999999999999f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1006.0f, -61.0f),
                        PathNode.VerticalTo(812.0f),
                        PathNode.QuadTo(1005.0f, 841.0f, 995.0f, 852.5f),
                        PathNode.QuadTo(985.0f, 864.0f, 959.0f, 864.0f),
                        PathNode.HorizontalTo(931.0f),
                        PathNode.QuadTo(905.0f, 864.0f, 894.5f, 852.0f),
                        PathNode.QuadTo(884.0f, 840.0f, 884.0f, 812.0f),
                        PathNode.VerticalTo(-61.0f),
                        PathNode.QuadTo(884.0f, -89.0f, 894.5f, -101.0f),
                        PathNode.QuadTo(905.0f, -113.0f, 930.0f, -113.0f),
                        PathNode.HorizontalTo(958.0f),
                        PathNode.QuadTo(986.0f, -113.0f, 996.0f, -101.0f),
                        PathNode.QuadTo(1006.0f, -89.0f, 1006.0f, -61.0f),
                        PathNode.Close,
                        PathNode.MoveTo(445.0f, -61.0f),
                        PathNode.VerticalTo(812.0f),
                        PathNode.QuadTo(444.0f, 842.0f, 434.0f, 853.0f),
                        PathNode.QuadTo(424.0f, 864.0f, 398.0f, 864.0f),
                        PathNode.HorizontalTo(370.0f),
                        PathNode.QuadTo(343.0f, 864.0f, 333.0f, 852.0f),
                        PathNode.QuadTo(323.0f, 840.0f, 323.0f, 812.0f),
                        PathNode.VerticalTo(-61.0f),
                        PathNode.QuadTo(323.0f, -89.0f, 332.5f, -101.0f),
                        PathNode.QuadTo(342.0f, -113.0f, 370.0f, -113.0f),
                        PathNode.HorizontalTo(398.0f),
                        PathNode.QuadTo(426.0f, -113.0f, 435.5f, -101.0f),
                        PathNode.QuadTo(445.0f, -89.0f, 445.0f, -61.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _pauseHeavy!!
    }

private var _pauseHeavy: ImageVector? = null
