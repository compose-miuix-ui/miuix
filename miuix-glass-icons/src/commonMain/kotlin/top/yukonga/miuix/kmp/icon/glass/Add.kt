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

val MiuixIcons.Glass.Add: ImageVector
    get() = MiuixIcons.Glass.Regular.Add

val MiuixIcons.Glass.Light.Add: ImageVector
    get() {
        if (_addLight != null) return _addLight!!
        _addLight = ImageVector.Builder(
            name = "Add.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(603.6f, 115.9f),
                        PathNode.LineTo(603.6f, 1027.0f),
                        PathNode.QuadTo(603.6f, 1035.0f, 598.8f, 1040.1f),
                        PathNode.QuadTo(594.0f, 1045.1f, 584.4f, 1045.1f),
                        PathNode.LineTo(559.9f, 1045.1f),
                        PathNode.QuadTo(551.3f, 1045.1f, 546.3f, 1039.7f),
                        PathNode.QuadTo(541.3f, 1034.4f, 541.3f, 1027.0f),
                        PathNode.LineTo(541.3f, 115.9f),
                        PathNode.QuadTo(541.3f, 107.2f, 546.6f, 102.5f),
                        PathNode.QuadTo(551.9f, 97.8f, 560.5f, 97.8f),
                        PathNode.LineTo(584.9f, 97.8f),
                        PathNode.QuadTo(593.6f, 97.8f, 598.6f, 102.5f),
                        PathNode.QuadTo(603.6f, 107.2f, 603.6f, 115.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1026.5f, 603.1f),
                        PathNode.LineTo(116.4f, 603.1f),
                        PathNode.QuadTo(108.3f, 603.1f, 103.3f, 598.3f),
                        PathNode.QuadTo(98.3f, 593.5f, 98.3f, 583.9f),
                        PathNode.LineTo(98.3f, 558.3f),
                        PathNode.QuadTo(98.3f, 549.7f, 103.6f, 544.7f),
                        PathNode.QuadTo(109.0f, 539.7f, 116.4f, 539.7f),
                        PathNode.LineTo(1026.5f, 539.7f),
                        PathNode.QuadTo(1035.2f, 539.7f, 1039.9f, 545.0f),
                        PathNode.QuadTo(1044.6f, 550.3f, 1044.6f, 558.9f),
                        PathNode.LineTo(1044.6f, 584.4f),
                        PathNode.QuadTo(1044.6f, 593.1f, 1039.9f, 598.1f),
                        PathNode.QuadTo(1035.2f, 603.1f, 1026.5f, 603.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addLight!!
    }

private var _addLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Add: ImageVector
    get() {
        if (_addNormal != null) return _addNormal!!
        _addNormal = ImageVector.Builder(
            name = "Add.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(612.8f, 112.8f),
                        PathNode.LineTo(612.8f, 1030.0f),
                        PathNode.QuadTo(612.8f, 1040.8f, 606.5f, 1047.5f),
                        PathNode.QuadTo(600.2f, 1054.3f, 586.8f, 1054.3f),
                        PathNode.LineTo(557.2f, 1054.3f),
                        PathNode.QuadTo(545.5f, 1054.3f, 538.8f, 1047.1f),
                        PathNode.QuadTo(532.1f, 1039.9f, 532.1f, 1030.0f),
                        PathNode.LineTo(532.1f, 112.8f),
                        PathNode.QuadTo(532.1f, 101.2f, 539.3f, 94.9f),
                        PathNode.QuadTo(546.4f, 88.6f, 558.0f, 88.6f),
                        PathNode.LineTo(587.7f, 88.6f),
                        PathNode.QuadTo(599.3f, 88.6f, 606.0f, 94.9f),
                        PathNode.QuadTo(612.8f, 101.2f, 612.8f, 112.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1029.5f, 612.3f),
                        PathNode.LineTo(113.3f, 612.3f),
                        PathNode.QuadTo(102.6f, 612.3f, 95.8f, 606.0f),
                        PathNode.QuadTo(89.1f, 599.7f, 89.1f, 586.3f),
                        PathNode.LineTo(89.1f, 555.0f),
                        PathNode.QuadTo(89.1f, 543.3f, 96.3f, 536.6f),
                        PathNode.QuadTo(103.4f, 529.9f, 113.3f, 529.9f),
                        PathNode.LineTo(1029.5f, 529.9f),
                        PathNode.QuadTo(1041.2f, 529.9f, 1047.5f, 537.0f),
                        PathNode.QuadTo(1053.8f, 544.2f, 1053.8f, 555.8f),
                        PathNode.LineTo(1053.8f, 587.2f),
                        PathNode.QuadTo(1053.8f, 598.8f, 1047.5f, 605.5f),
                        PathNode.QuadTo(1041.2f, 612.3f, 1029.5f, 612.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addNormal!!
    }

private var _addNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Add: ImageVector
    get() {
        if (_addRegular != null) return _addRegular!!
        _addRegular = ImageVector.Builder(
            name = "Add.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(616.9f, 111.4f),
                        PathNode.LineTo(616.9f, 1031.4f),
                        PathNode.QuadTo(616.9f, 1043.4f, 609.9f, 1050.9f),
                        PathNode.QuadTo(602.9f, 1058.4f, 587.9f, 1058.4f),
                        PathNode.LineTo(555.9f, 1058.4f),
                        PathNode.QuadTo(542.9f, 1058.4f, 535.4f, 1050.4f),
                        PathNode.QuadTo(527.9f, 1042.4f, 527.9f, 1031.4f),
                        PathNode.LineTo(527.9f, 111.4f),
                        PathNode.QuadTo(527.9f, 98.4f, 535.9f, 91.4f),
                        PathNode.QuadTo(543.9f, 84.4f, 556.9f, 84.4f),
                        PathNode.LineTo(588.9f, 84.4f),
                        PathNode.QuadTo(601.9f, 84.4f, 609.4f, 91.4f),
                        PathNode.QuadTo(616.9f, 98.4f, 616.9f, 111.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1030.9f, 616.4f),
                        PathNode.LineTo(111.9f, 616.4f),
                        PathNode.QuadTo(99.9f, 616.4f, 92.4f, 609.4f),
                        PathNode.QuadTo(84.9f, 602.4f, 84.9f, 587.4f),
                        PathNode.LineTo(84.9f, 553.4f),
                        PathNode.QuadTo(84.9f, 540.4f, 92.9f, 532.9f),
                        PathNode.QuadTo(100.9f, 525.4f, 111.9f, 525.4f),
                        PathNode.LineTo(1030.9f, 525.4f),
                        PathNode.QuadTo(1043.9f, 525.4f, 1050.9f, 533.4f),
                        PathNode.QuadTo(1057.9f, 541.4f, 1057.9f, 554.4f),
                        PathNode.LineTo(1057.9f, 588.4f),
                        PathNode.QuadTo(1057.9f, 601.4f, 1050.9f, 608.9f),
                        PathNode.QuadTo(1043.9f, 616.4f, 1030.9f, 616.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addRegular!!
    }

private var _addRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Add: ImageVector
    get() {
        if (_addMedium != null) return _addMedium!!
        _addMedium = ImageVector.Builder(
            name = "Add.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(625.0f, 111.4f),
                        PathNode.LineTo(625.0f, 1031.4f),
                        PathNode.QuadTo(625.0f, 1046.7f, 615.7f, 1056.6f),
                        PathNode.QuadTo(606.4f, 1066.5f, 587.9f, 1066.5f),
                        PathNode.LineTo(555.9f, 1066.5f),
                        PathNode.QuadTo(539.7f, 1066.5f, 529.8f, 1056.1f),
                        PathNode.QuadTo(519.8f, 1045.7f, 519.8f, 1031.4f),
                        PathNode.LineTo(519.8f, 111.4f),
                        PathNode.QuadTo(519.8f, 95.2f, 530.3f, 85.8f),
                        PathNode.QuadTo(540.7f, 76.3f, 556.9f, 76.3f),
                        PathNode.LineTo(588.9f, 76.3f),
                        PathNode.QuadTo(605.2f, 76.3f, 615.1f, 85.8f),
                        PathNode.QuadTo(625.0f, 95.2f, 625.0f, 111.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1030.9f, 624.5f),
                        PathNode.LineTo(111.9f, 624.5f),
                        PathNode.QuadTo(96.7f, 624.5f, 86.8f, 615.2f),
                        PathNode.QuadTo(76.8f, 605.9f, 76.8f, 587.4f),
                        PathNode.LineTo(76.8f, 553.4f),
                        PathNode.QuadTo(76.8f, 537.2f, 87.3f, 527.3f),
                        PathNode.QuadTo(97.7f, 517.3f, 111.9f, 517.3f),
                        PathNode.LineTo(1030.9f, 517.3f),
                        PathNode.QuadTo(1047.2f, 517.3f, 1056.6f, 527.8f),
                        PathNode.QuadTo(1066.0f, 538.2f, 1066.0f, 554.4f),
                        PathNode.LineTo(1066.0f, 588.4f),
                        PathNode.QuadTo(1066.0f, 604.7f, 1056.6f, 614.6f),
                        PathNode.QuadTo(1047.2f, 624.5f, 1030.9f, 624.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addMedium!!
    }

private var _addMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Add: ImageVector
    get() {
        if (_addDemibold != null) return _addDemibold!!
        _addDemibold = ImageVector.Builder(
            name = "Add.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(630.7f, 111.4f),
                        PathNode.LineTo(630.7f, 1031.4f),
                        PathNode.QuadTo(630.7f, 1048.9f, 619.8f, 1060.6f),
                        PathNode.QuadTo(608.9f, 1072.2f, 587.9f, 1072.2f),
                        PathNode.LineTo(555.9f, 1072.2f),
                        PathNode.QuadTo(537.4f, 1072.2f, 525.8f, 1060.1f),
                        PathNode.QuadTo(514.1f, 1047.9f, 514.1f, 1031.4f),
                        PathNode.LineTo(514.1f, 111.4f),
                        PathNode.QuadTo(514.1f, 92.9f, 526.3f, 81.8f),
                        PathNode.QuadTo(538.4f, 70.6f, 556.9f, 70.6f),
                        PathNode.LineTo(588.9f, 70.6f),
                        PathNode.QuadTo(607.4f, 70.6f, 619.1f, 81.8f),
                        PathNode.QuadTo(630.7f, 92.9f, 630.7f, 111.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1030.9f, 630.2f),
                        PathNode.LineTo(111.9f, 630.2f),
                        PathNode.QuadTo(94.4f, 630.2f, 82.8f, 619.3f),
                        PathNode.QuadTo(71.1f, 608.4f, 71.1f, 587.4f),
                        PathNode.LineTo(71.1f, 553.4f),
                        PathNode.QuadTo(71.1f, 534.9f, 83.3f, 523.3f),
                        PathNode.QuadTo(95.4f, 511.6f, 111.9f, 511.6f),
                        PathNode.LineTo(1030.9f, 511.6f),
                        PathNode.QuadTo(1049.4f, 511.6f, 1060.6f, 523.8f),
                        PathNode.QuadTo(1071.7f, 535.9f, 1071.7f, 554.4f),
                        PathNode.LineTo(1071.7f, 588.4f),
                        PathNode.QuadTo(1071.7f, 606.9f, 1060.6f, 618.6f),
                        PathNode.QuadTo(1049.4f, 630.2f, 1030.9f, 630.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addDemibold!!
    }

private var _addDemibold: ImageVector? = null
