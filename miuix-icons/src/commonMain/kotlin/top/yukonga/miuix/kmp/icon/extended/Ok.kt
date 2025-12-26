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

val MiuixIcons.Ok: ImageVector
    get() = MiuixIcons.Regular.Ok

val MiuixIcons.Light.Ok: ImageVector
    get() {
        if (_okLight != null) return _okLight!!
        _okLight = ImageVector.Builder(
            name = "Ok.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1241.24f,
            viewportHeight = 1241.24f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -65.3633333333334f, translationY = 996.6877083333334f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(490.0f, -48.0f),
                        PathNode.LineTo(172.0f, 424.0f),
                        PathNode.QuadTo(168.0f, 430.0f, 169.0f, 436.5f),
                        PathNode.QuadTo(170.0f, 443.0f, 176.0f, 447.0f),
                        PathNode.LineTo(198.0f, 462.0f),
                        PathNode.QuadTo(204.0f, 466.0f, 210.5f, 465.0f),
                        PathNode.QuadTo(217.0f, 464.0f, 222.0f, 458.0f),
                        PathNode.LineTo(518.0f, 18.0f),
                        PathNode.QuadTo(522.0f, 13.0f, 526.5f, 13.0f),
                        PathNode.QuadTo(531.0f, 13.0f, 535.0f, 18.0f),
                        PathNode.LineTo(1149.0f, 812.0f),
                        PathNode.QuadTo(1153.0f, 819.0f, 1160.5f, 820.0f),
                        PathNode.QuadTo(1168.0f, 821.0f, 1175.0f, 816.0f),
                        PathNode.LineTo(1196.0f, 799.0f),
                        PathNode.QuadTo(1202.0f, 794.0f, 1203.0f, 787.0f),
                        PathNode.QuadTo(1204.0f, 780.0f, 1199.0f, 774.0f),
                        PathNode.LineTo(559.0f, -51.0f),
                        PathNode.QuadTo(550.0f, -63.0f, 537.0f, -66.5f),
                        PathNode.QuadTo(524.0f, -70.0f, 511.5f, -65.5f),
                        PathNode.QuadTo(499.0f, -61.0f, 490.0f, -48.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okLight!!
    }

private var _okLight: ImageVector? = null

val MiuixIcons.Regular.Ok: ImageVector
    get() {
        if (_okRegular != null) return _okRegular!!
        _okRegular = ImageVector.Builder(
            name = "Ok.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1279.5176470588235f,
            viewportHeight = 1279.5176470588235f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -45.87352941176471f, translationY = 1015.2522875816993f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(486.0f, -65.0f),
                        PathNode.LineTo(157.0f, 420.0f),
                        PathNode.QuadTo(151.0f, 429.0f, 153.0f, 439.5f),
                        PathNode.QuadTo(155.0f, 450.0f, 164.0f, 456.0f),
                        PathNode.LineTo(194.0f, 478.0f),
                        PathNode.QuadTo(203.0f, 484.0f, 214.0f, 482.0f),
                        PathNode.QuadTo(225.0f, 480.0f, 231.0f, 471.0f),
                        PathNode.LineTo(519.0f, 41.0f),
                        PathNode.QuadTo(522.0f, 37.0f, 527.0f, 36.5f),
                        PathNode.QuadTo(532.0f, 36.0f, 535.0f, 40.0f),
                        PathNode.LineTo(1142.0f, 826.0f),
                        PathNode.QuadTo(1149.0f, 835.0f, 1160.0f, 836.5f),
                        PathNode.QuadTo(1171.0f, 838.0f, 1180.0f, 831.0f),
                        PathNode.LineTo(1208.0f, 808.0f),
                        PathNode.QuadTo(1217.0f, 801.0f, 1218.5f, 790.0f),
                        PathNode.QuadTo(1220.0f, 779.0f, 1213.0f, 770.0f),
                        PathNode.LineTo(563.0f, -68.0f),
                        PathNode.QuadTo(553.0f, -80.0f, 538.5f, -84.0f),
                        PathNode.QuadTo(524.0f, -88.0f, 509.5f, -83.0f),
                        PathNode.QuadTo(495.0f, -78.0f, 486.0f, -65.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okRegular!!
    }

private var _okRegular: ImageVector? = null

val MiuixIcons.Heavy.Ok: ImageVector
    get() {
        if (_okHeavy != null) return _okHeavy!!
        _okHeavy = ImageVector.Builder(
            name = "Ok.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1318.3863247863248f,
            viewportHeight = 1318.3863247863248f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -26.44216524216523f, translationY = 1034.336591880342f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(473.0f, -74.0f),
                        PathNode.LineTo(144.0f, 411.0f),
                        PathNode.QuadTo(134.0f, 425.0f, 137.0f, 442.0f),
                        PathNode.QuadTo(140.0f, 459.0f, 155.0f, 469.0f),
                        PathNode.LineTo(185.0f, 491.0f),
                        PathNode.QuadTo(199.0f, 501.0f, 216.5f, 498.0f),
                        PathNode.QuadTo(234.0f, 495.0f, 244.0f, 480.0f),
                        PathNode.LineTo(522.0f, 69.0f),
                        PathNode.QuadTo(526.0f, 64.0f, 530.0f, 63.5f),
                        PathNode.QuadTo(534.0f, 63.0f, 538.0f, 68.0f),
                        PathNode.LineTo(1129.0f, 836.0f),
                        PathNode.QuadTo(1140.0f, 850.0f, 1158.0f, 852.0f),
                        PathNode.QuadTo(1176.0f, 854.0f, 1190.0f, 843.0f),
                        PathNode.LineTo(1218.0f, 820.0f),
                        PathNode.QuadTo(1232.0f, 809.0f, 1234.5f, 791.5f),
                        PathNode.QuadTo(1237.0f, 774.0f, 1226.0f, 760.0f),
                        PathNode.LineTo(575.0f, -78.0f),
                        PathNode.QuadTo(562.0f, -94.0f, 542.5f, -99.5f),
                        PathNode.QuadTo(523.0f, -105.0f, 504.0f, -98.5f),
                        PathNode.QuadTo(485.0f, -92.0f, 473.0f, -74.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okHeavy!!
    }

private var _okHeavy: ImageVector? = null
