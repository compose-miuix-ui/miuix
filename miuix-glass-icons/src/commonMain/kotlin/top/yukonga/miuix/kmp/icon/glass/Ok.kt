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

val MiuixIcons.Glass.Ok: ImageVector
    get() = MiuixIcons.Glass.Regular.Ok

val MiuixIcons.Glass.Light.Ok: ImageVector
    get() {
        if (_okLight != null) return _okLight!!
        _okLight = ImageVector.Builder(
            name = "Ok.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1085.4f, 983.0f),
                        PathNode.LineTo(1064.5f, 999.8f),
                        PathNode.QuadTo(1058.2f, 1004.6f, 1051.0f, 1003.5f),
                        PathNode.QuadTo(1043.8f, 1002.5f, 1039.0f, 996.1f),
                        PathNode.LineTo(442.0f, 220.6f),
                        PathNode.QuadTo(438.7f, 215.8f, 435.0f, 215.8f),
                        PathNode.QuadTo(431.3f, 215.8f, 428.0f, 220.1f),
                        PathNode.LineTo(102.5f, 639.0f),
                        PathNode.QuadTo(97.8f, 644.8f, 90.5f, 645.6f),
                        PathNode.QuadTo(83.3f, 646.5f, 77.4f, 641.7f),
                        PathNode.LineTo(57.4f, 625.8f),
                        PathNode.QuadTo(51.5f, 621.0f, 50.7f, 613.8f),
                        PathNode.QuadTo(49.9f, 606.6f, 54.2f, 601.2f),
                        PathNode.LineTo(399.1f, 158.7f),
                        PathNode.QuadTo(408.7f, 146.1f, 421.2f, 141.6f),
                        PathNode.QuadTo(433.7f, 137.0f, 445.9f, 141.0f),
                        PathNode.QuadTo(458.2f, 145.0f, 467.2f, 156.6f),
                        PathNode.LineTo(1088.2f, 957.9f),
                        PathNode.QuadTo(1093.0f, 963.8f, 1092.2f, 971.0f),
                        PathNode.QuadTo(1091.3f, 978.2f, 1085.4f, 983.0f),
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

val MiuixIcons.Glass.Normal.Ok: ImageVector
    get() {
        if (_okNormal != null) return _okNormal!!
        _okNormal = ImageVector.Builder(
            name = "Ok.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1093.7f, 989.8f),
                        PathNode.LineTo(1067.9f, 1010.9f),
                        PathNode.QuadTo(1059.7f, 1017.2f, 1049.9f, 1015.8f),
                        PathNode.QuadTo(1040.1f, 1014.5f, 1033.8f, 1006.3f),
                        PathNode.LineTo(439.5f, 233.2f),
                        PathNode.QuadTo(437.1f, 229.6f, 433.9f, 229.6f),
                        PathNode.QuadTo(430.7f, 229.6f, 428.3f, 233.1f),
                        PathNode.LineTo(108.7f, 645.8f),
                        PathNode.QuadTo(102.3f, 653.1f, 92.5f, 654.4f),
                        PathNode.QuadTo(82.7f, 655.7f, 74.7f, 649.4f),
                        PathNode.LineTo(49.2f, 628.6f),
                        PathNode.QuadTo(41.2f, 622.3f, 39.9f, 612.5f),
                        PathNode.QuadTo(38.6f, 602.7f, 44.7f, 595.5f),
                        PathNode.LineTo(394.5f, 147.2f),
                        PathNode.QuadTo(404.4f, 134.3f, 418.3f, 129.4f),
                        PathNode.QuadTo(432.1f, 124.6f, 445.9f, 128.6f),
                        PathNode.QuadTo(459.7f, 132.6f, 468.7f, 144.4f),
                        PathNode.LineTo(1098.0f, 955.9f),
                        PathNode.QuadTo(1104.3f, 963.9f, 1103.0f, 973.7f),
                        PathNode.QuadTo(1101.7f, 983.5f, 1093.7f, 989.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okNormal!!
    }

private var _okNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Ok: ImageVector
    get() {
        if (_okRegular != null) return _okRegular!!
        _okRegular = ImageVector.Builder(
            name = "Ok.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1097.4f, 992.9f),
                        PathNode.LineTo(1069.4f, 1015.9f),
                        PathNode.QuadTo(1060.4f, 1022.9f, 1049.4f, 1021.4f),
                        PathNode.QuadTo(1038.4f, 1019.9f, 1031.4f, 1010.9f),
                        PathNode.LineTo(438.4f, 238.9f),
                        PathNode.QuadTo(436.4f, 235.9f, 433.4f, 235.9f),
                        PathNode.QuadTo(430.4f, 235.9f, 428.4f, 238.9f),
                        PathNode.LineTo(111.4f, 648.9f),
                        PathNode.QuadTo(104.4f, 656.9f, 93.4f, 658.4f),
                        PathNode.QuadTo(82.4f, 659.9f, 73.4f, 652.9f),
                        PathNode.LineTo(45.4f, 629.9f),
                        PathNode.QuadTo(36.4f, 622.9f, 34.9f, 611.9f),
                        PathNode.QuadTo(33.4f, 600.9f, 40.4f, 592.9f),
                        PathNode.LineTo(392.4f, 141.9f),
                        PathNode.QuadTo(402.4f, 128.9f, 416.9f, 123.9f),
                        PathNode.QuadTo(431.4f, 118.9f, 445.9f, 122.9f),
                        PathNode.QuadTo(460.4f, 126.9f, 469.4f, 138.9f),
                        PathNode.LineTo(1102.4f, 954.9f),
                        PathNode.QuadTo(1109.4f, 963.9f, 1107.9f, 974.9f),
                        PathNode.QuadTo(1106.4f, 985.9f, 1097.4f, 992.9f),
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

val MiuixIcons.Glass.Medium.Ok: ImageVector
    get() {
        if (_okMedium != null) return _okMedium!!
        _okMedium = ImageVector.Builder(
            name = "Ok.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1104.2f, 1000.3f),
                        PathNode.LineTo(1076.2f, 1023.3f),
                        PathNode.QuadTo(1063.9f, 1032.7f, 1049.2f, 1030.8f),
                        PathNode.QuadTo(1034.4f, 1028.9f, 1024.7f, 1016.7f),
                        PathNode.LineTo(441.7f, 258.2f),
                        PathNode.QuadTo(438.9f, 254.4f, 434.9f, 254.3f),
                        PathNode.QuadTo(431.0f, 254.1f, 427.9f, 258.2f),
                        PathNode.LineTo(117.9f, 657.1f),
                        PathNode.QuadTo(108.5f, 668.6f, 93.7f, 670.7f),
                        PathNode.QuadTo(78.9f, 672.7f, 66.9f, 663.3f),
                        PathNode.LineTo(38.7f, 640.0f),
                        PathNode.QuadTo(26.7f, 630.9f, 24.8f, 616.1f),
                        PathNode.QuadTo(22.9f, 601.3f, 32.3f, 590.0f),
                        PathNode.LineTo(386.2f, 135.8f),
                        PathNode.QuadTo(398.1f, 120.6f, 415.2f, 114.8f),
                        PathNode.QuadTo(432.2f, 109.0f, 449.3f, 114.0f),
                        PathNode.QuadTo(466.4f, 118.9f, 477.5f, 133.3f),
                        PathNode.LineTo(1110.8f, 949.3f),
                        PathNode.QuadTo(1120.0f, 961.3f, 1118.1f, 976.1f),
                        PathNode.QuadTo(1116.2f, 990.9f, 1104.2f, 1000.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okMedium!!
    }

private var _okMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Ok: ImageVector
    get() {
        if (_okDemibold != null) return _okDemibold!!
        _okDemibold = ImageVector.Builder(
            name = "Ok.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1108.9f, 1005.5f),
                        PathNode.LineTo(1080.9f, 1028.5f),
                        PathNode.QuadTo(1066.4f, 1039.6f, 1049.0f, 1037.4f),
                        PathNode.QuadTo(1031.5f, 1035.2f, 1019.9f, 1020.7f),
                        PathNode.LineTo(443.9f, 271.7f),
                        PathNode.QuadTo(440.6f, 267.3f, 435.9f, 267.1f),
                        PathNode.QuadTo(431.3f, 266.9f, 427.5f, 271.7f),
                        PathNode.LineTo(122.4f, 662.9f),
                        PathNode.QuadTo(111.3f, 676.8f, 93.9f, 679.3f),
                        PathNode.QuadTo(76.4f, 681.7f, 62.4f, 670.5f),
                        PathNode.LineTo(33.9f, 647.1f),
                        PathNode.QuadTo(19.9f, 636.4f, 17.7f, 619.0f),
                        PathNode.QuadTo(15.5f, 601.5f, 26.6f, 588.0f),
                        PathNode.LineTo(381.9f, 131.5f),
                        PathNode.QuadTo(395.1f, 114.8f, 413.9f, 108.5f),
                        PathNode.QuadTo(432.8f, 102.1f, 451.7f, 107.7f),
                        PathNode.QuadTo(470.5f, 113.3f, 483.2f, 129.4f),
                        PathNode.LineTo(1116.7f, 945.4f),
                        PathNode.QuadTo(1127.3f, 959.5f, 1125.2f, 976.9f),
                        PathNode.QuadTo(1123.0f, 994.4f, 1108.9f, 1005.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okDemibold!!
    }

private var _okDemibold: ImageVector? = null
