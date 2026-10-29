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

val MiuixIcons.Glass.Back: ImageVector
    get() = MiuixIcons.Glass.Regular.Back

val MiuixIcons.Glass.Light.Back: ImageVector
    get() {
        if (_backLight != null) return _backLight!!
        _backLight = ImageVector.Builder(
            name = "Back.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(128.8f, 541.9f),
                        PathNode.LineTo(1091.5f, 541.9f),
                        PathNode.QuadTo(1101.7f, 541.9f, 1106.8f, 547.0f),
                        PathNode.QuadTo(1111.9f, 552.1f, 1111.9f, 561.7f),
                        PathNode.LineTo(1111.9f, 583.0f),
                        PathNode.QuadTo(1111.9f, 591.5f, 1106.5f, 596.3f),
                        PathNode.QuadTo(1101.1f, 601.2f, 1091.5f, 601.2f),
                        PathNode.LineTo(128.8f, 601.2f),
                        PathNode.LineTo(433.2f, 905.6f),
                        PathNode.QuadTo(439.7f, 912.1f, 439.7f, 918.5f),
                        PathNode.QuadTo(439.7f, 925.0f, 432.1f, 932.5f),
                        PathNode.LineTo(417.8f, 946.8f),
                        PathNode.QuadTo(410.2f, 954.4f, 403.8f, 954.4f),
                        PathNode.QuadTo(397.3f, 954.4f, 389.8f, 946.8f),
                        PathNode.LineTo(46.3f, 602.4f),
                        PathNode.QuadTo(31.0f, 587.1f, 31.0f, 571.8f),
                        PathNode.QuadTo(30.9f, 556.5f, 46.9f, 540.1f),
                        PathNode.LineTo(390.7f, 196.3f),
                        PathNode.QuadTo(398.2f, 188.7f, 404.4f, 188.5f),
                        PathNode.QuadTo(410.6f, 188.2f, 418.7f, 196.3f),
                        PathNode.LineTo(433.7f, 211.7f),
                        PathNode.QuadTo(440.7f, 218.7f, 440.7f, 224.3f),
                        PathNode.QuadTo(440.7f, 230.0f, 433.1f, 237.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _backLight!!
    }

private var _backLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Back: ImageVector
    get() {
        if (_backNormal != null) return _backNormal!!
        _backNormal = ImageVector.Builder(
            name = "Back.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(151.4f, 532.5f),
                        PathNode.LineTo(1093.0f, 532.5f),
                        PathNode.QuadTo(1107.2f, 532.5f, 1114.3f, 539.6f),
                        PathNode.QuadTo(1121.4f, 546.7f, 1121.4f, 560.0f),
                        PathNode.LineTo(1121.4f, 585.2f),
                        PathNode.QuadTo(1121.4f, 596.8f, 1113.8f, 603.5f),
                        PathNode.QuadTo(1106.3f, 610.1f, 1093.0f, 610.1f),
                        PathNode.LineTo(151.4f, 610.1f),
                        PathNode.LineTo(441.8f, 900.5f),
                        PathNode.QuadTo(450.7f, 909.4f, 450.7f, 918.3f),
                        PathNode.QuadTo(450.7f, 927.2f, 440.1f, 937.8f),
                        PathNode.LineTo(423.9f, 954.0f),
                        PathNode.QuadTo(413.3f, 964.6f, 404.4f, 964.6f),
                        PathNode.QuadTo(395.5f, 964.6f, 384.9f, 954.0f),
                        PathNode.LineTo(39.0f, 607.1f),
                        PathNode.QuadTo(21.8f, 589.9f, 21.5f, 571.7f),
                        PathNode.QuadTo(21.1f, 553.5f, 39.9f, 534.7f),
                        PathNode.LineTo(385.2f, 189.3f),
                        PathNode.QuadTo(395.8f, 178.7f, 404.3f, 178.3f),
                        PathNode.QuadTo(412.7f, 177.9f, 424.2f, 189.3f),
                        PathNode.LineTo(441.9f, 207.2f),
                        PathNode.QuadTo(451.7f, 217.0f, 451.7f, 224.6f),
                        PathNode.QuadTo(451.7f, 232.2f, 441.1f, 242.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _backNormal!!
    }

private var _backNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Back: ImageVector
    get() {
        if (_backRegular != null) return _backRegular!!
        _backRegular = ImageVector.Builder(
            name = "Back.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(161.7f, 528.2f),
                        PathNode.LineTo(1093.7f, 528.2f),
                        PathNode.QuadTo(1109.7f, 528.2f, 1117.7f, 536.2f),
                        PathNode.QuadTo(1125.7f, 544.2f, 1125.7f, 559.2f),
                        PathNode.LineTo(1125.7f, 586.2f),
                        PathNode.QuadTo(1125.7f, 599.2f, 1117.2f, 606.7f),
                        PathNode.QuadTo(1108.7f, 614.2f, 1093.7f, 614.2f),
                        PathNode.LineTo(161.7f, 614.2f),
                        PathNode.LineTo(445.7f, 898.2f),
                        PathNode.QuadTo(455.7f, 908.2f, 455.7f, 918.2f),
                        PathNode.QuadTo(455.7f, 928.2f, 443.7f, 940.2f),
                        PathNode.LineTo(426.7f, 957.2f),
                        PathNode.QuadTo(414.7f, 969.2f, 404.7f, 969.2f),
                        PathNode.QuadTo(394.7f, 969.2f, 382.7f, 957.2f),
                        PathNode.LineTo(35.7f, 609.2f),
                        PathNode.QuadTo(17.7f, 591.2f, 17.2f, 571.7f),
                        PathNode.QuadTo(16.7f, 552.2f, 36.7f, 532.2f),
                        PathNode.LineTo(382.7f, 186.2f),
                        PathNode.QuadTo(394.7f, 174.2f, 404.2f, 173.7f),
                        PathNode.QuadTo(413.7f, 173.2f, 426.7f, 186.2f),
                        PathNode.LineTo(445.7f, 205.2f),
                        PathNode.QuadTo(456.7f, 216.2f, 456.7f, 224.7f),
                        PathNode.QuadTo(456.7f, 233.2f, 444.7f, 245.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _backRegular!!
    }

private var _backRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Back: ImageVector
    get() {
        if (_backMedium != null) return _backMedium!!
        _backMedium = ImageVector.Builder(
            name = "Back.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(187.4f, 517.4f),
                        PathNode.LineTo(1093.7f, 517.4f),
                        PathNode.QuadTo(1114.0f, 517.4f, 1125.3f, 528.5f),
                        PathNode.QuadTo(1136.5f, 539.6f, 1136.5f, 559.2f),
                        PathNode.LineTo(1136.5f, 586.2f),
                        PathNode.QuadTo(1136.5f, 603.8f, 1124.9f, 614.4f),
                        PathNode.QuadTo(1113.3f, 625.0f, 1093.7f, 625.0f),
                        PathNode.LineTo(187.4f, 625.0f),
                        PathNode.LineTo(453.3f, 890.6f),
                        PathNode.QuadTo(466.5f, 903.9f, 466.5f, 918.2f),
                        PathNode.QuadTo(466.5f, 932.5f, 451.3f, 947.8f),
                        PathNode.LineTo(434.5f, 964.8f),
                        PathNode.QuadTo(419.3f, 980.0f, 404.8f, 980.0f),
                        PathNode.QuadTo(390.4f, 980.0f, 375.1f, 964.8f),
                        PathNode.LineTo(28.1f, 616.8f),
                        PathNode.QuadTo(6.9f, 595.5f, 6.4f, 571.6f),
                        PathNode.QuadTo(5.9f, 547.6f, 29.1f, 524.6f),
                        PathNode.LineTo(375.1f, 178.6f),
                        PathNode.QuadTo(390.4f, 163.4f, 404.2f, 162.9f),
                        PathNode.QuadTo(418.0f, 162.4f, 434.3f, 178.6f),
                        PathNode.LineTo(453.3f, 197.6f),
                        PathNode.QuadTo(467.5f, 211.6f, 467.5f, 224.6f),
                        PathNode.QuadTo(467.5f, 237.5f, 452.3f, 252.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _backMedium!!
    }

private var _backMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Back: ImageVector
    get() {
        if (_backDemibold != null) return _backDemibold!!
        _backDemibold = ImageVector.Builder(
            name = "Back.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(205.3f, 509.8f),
                        PathNode.LineTo(1093.7f, 509.8f),
                        PathNode.QuadTo(1117.0f, 509.8f, 1130.5f, 523.1f),
                        PathNode.QuadTo(1144.1f, 536.4f, 1144.1f, 559.2f),
                        PathNode.LineTo(1144.1f, 586.2f),
                        PathNode.QuadTo(1144.1f, 607.0f, 1130.3f, 619.8f),
                        PathNode.QuadTo(1116.5f, 632.6f, 1093.7f, 632.6f),
                        PathNode.LineTo(205.3f, 632.6f),
                        PathNode.LineTo(458.5f, 885.3f),
                        PathNode.QuadTo(474.1f, 900.8f, 474.1f, 918.2f),
                        PathNode.QuadTo(474.1f, 935.5f, 456.5f, 953.1f),
                        PathNode.LineTo(440.0f, 970.1f),
                        PathNode.QuadTo(422.5f, 987.6f, 404.9f, 987.6f),
                        PathNode.QuadTo(387.3f, 987.6f, 369.8f, 970.1f),
                        PathNode.LineTo(22.8f, 622.1f),
                        PathNode.QuadTo(-0.7f, 598.5f, -1.2f, 571.5f),
                        PathNode.QuadTo(-1.7f, 544.4f, 23.8f, 519.3f),
                        PathNode.LineTo(369.8f, 173.3f),
                        PathNode.QuadTo(387.3f, 155.8f, 404.2f, 155.3f),
                        PathNode.QuadTo(421.0f, 154.8f, 439.5f, 173.3f),
                        PathNode.LineTo(458.5f, 192.3f),
                        PathNode.QuadTo(475.1f, 208.4f, 475.1f, 224.5f),
                        PathNode.QuadTo(475.1f, 240.5f, 457.5f, 258.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _backDemibold!!
    }

private var _backDemibold: ImageVector? = null
