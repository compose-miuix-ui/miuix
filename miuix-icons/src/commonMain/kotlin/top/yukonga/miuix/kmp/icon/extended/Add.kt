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

val MiuixIcons.Add: ImageVector
    get() = MiuixIcons.Regular.Add

val MiuixIcons.Light.Add: ImageVector
    get() {
        if (_addLight != null) return _addLight!!
        _addLight = ImageVector.Builder(
            name = "Add.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1137.6f,
            viewportHeight = 1137.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1137.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(601.3f, 112.8f),
                        PathNode.VerticalTo(1024.8f),
                        PathNode.QuadTo(601.3f, 1031.8f, 596.3f, 1037.3f),
                        PathNode.QuadTo(591.3f, 1042.8f, 581.3f, 1042.8f),
                        PathNode.HorizontalTo(557.3f),
                        PathNode.QuadTo(548.3f, 1042.8f, 543.3f, 1037.3f),
                        PathNode.QuadTo(538.3f, 1031.8f, 538.3f, 1024.8f),
                        PathNode.VerticalTo(112.8f),
                        PathNode.QuadTo(538.3f, 104.8f, 543.8f, 99.8f),
                        PathNode.QuadTo(549.3f, 94.8f, 558.3f, 94.8f),
                        PathNode.HorizontalTo(582.3f),
                        PathNode.QuadTo(590.3f, 94.8f, 595.8f, 99.8f),
                        PathNode.QuadTo(601.3f, 104.8f, 601.3f, 112.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1024.3f, 600.8f),
                        PathNode.HorizontalTo(113.3f),
                        PathNode.QuadTo(106.3f, 600.8f, 100.8f, 595.8f),
                        PathNode.QuadTo(95.3f, 590.8f, 95.3f, 580.8f),
                        PathNode.VerticalTo(555.8f),
                        PathNode.QuadTo(95.3f, 546.8f, 100.8f, 541.8f),
                        PathNode.QuadTo(106.3f, 536.8f, 113.3f, 536.8f),
                        PathNode.HorizontalTo(1024.3f),
                        PathNode.QuadTo(1032.3f, 536.8f, 1037.3f, 542.3f),
                        PathNode.QuadTo(1042.3f, 547.8f, 1042.3f, 555.8f),
                        PathNode.VerticalTo(581.8f),
                        PathNode.QuadTo(1042.3f, 589.8f, 1037.3f, 595.3f),
                        PathNode.QuadTo(1032.3f, 600.8f, 1024.3f, 600.8f),
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

val MiuixIcons.Normal.Add: ImageVector
    get() {
        if (_addNormal != null) return _addNormal!!
        _addNormal = ImageVector.Builder(
            name = "Add.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1159.0f,
            viewportHeight = 1159.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1159.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(621.0f, 120.8f),
                        PathNode.VerticalTo(1038.3f),
                        PathNode.QuadTo(621.0f, 1048.7f, 614.6f, 1055.6f),
                        PathNode.QuadTo(608.2f, 1062.5f, 594.8f, 1062.5f),
                        PathNode.HorizontalTo(565.3f),
                        PathNode.QuadTo(553.5f, 1062.5f, 546.8f, 1055.2f),
                        PathNode.QuadTo(540.1f, 1048.0f, 540.1f, 1038.3f),
                        PathNode.VerticalTo(120.8f),
                        PathNode.QuadTo(540.1f, 109.3f, 547.3f, 103.0f),
                        PathNode.QuadTo(554.5f, 96.6f, 566.3f, 96.6f),
                        PathNode.HorizontalTo(595.8f),
                        PathNode.QuadTo(607.2f, 96.6f, 614.1f, 103.0f),
                        PathNode.QuadTo(621.0f, 109.3f, 621.0f, 120.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1037.8f, 620.5f),
                        PathNode.HorizontalTo(121.3f),
                        PathNode.QuadTo(110.8f, 620.5f, 104.0f, 614.1f),
                        PathNode.QuadTo(97.1f, 607.7f, 97.1f, 594.3f),
                        PathNode.VerticalTo(563.1f),
                        PathNode.QuadTo(97.1f, 551.3f, 104.3f, 544.6f),
                        PathNode.QuadTo(111.5f, 537.9f, 121.3f, 537.9f),
                        PathNode.HorizontalTo(1037.8f),
                        PathNode.QuadTo(1049.2f, 537.9f, 1055.6f, 545.1f),
                        PathNode.QuadTo(1062.0f, 552.3f, 1062.0f, 563.8f),
                        PathNode.VerticalTo(595.3f),
                        PathNode.QuadTo(1062.0f, 606.7f, 1055.6f, 613.6f),
                        PathNode.QuadTo(1049.2f, 620.5f, 1037.8f, 620.5f),
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

val MiuixIcons.Regular.Add: ImageVector
    get() {
        if (_addRegular != null) return _addRegular!!
        _addRegular = ImageVector.Builder(
            name = "Add.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1168.8f,
            viewportHeight = 1168.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1168.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(629.9f, 124.4f),
                        PathNode.VerticalTo(1044.4f),
                        PathNode.QuadTo(629.9f, 1056.4f, 622.9f, 1063.9f),
                        PathNode.QuadTo(615.9f, 1071.4f, 600.9f, 1071.4f),
                        PathNode.HorizontalTo(568.9f),
                        PathNode.QuadTo(555.9f, 1071.4f, 548.4f, 1063.4f),
                        PathNode.QuadTo(540.9f, 1055.4f, 540.9f, 1044.4f),
                        PathNode.VerticalTo(124.4f),
                        PathNode.QuadTo(540.9f, 111.4f, 548.9f, 104.4f),
                        PathNode.QuadTo(556.9f, 97.4f, 569.9f, 97.4f),
                        PathNode.HorizontalTo(601.9f),
                        PathNode.QuadTo(614.9f, 97.4f, 622.4f, 104.4f),
                        PathNode.QuadTo(629.9f, 111.4f, 629.9f, 124.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1043.9f, 629.4f),
                        PathNode.HorizontalTo(124.9f),
                        PathNode.QuadTo(112.9f, 629.4f, 105.4f, 622.4f),
                        PathNode.QuadTo(97.9f, 615.4f, 97.9f, 600.4f),
                        PathNode.VerticalTo(566.4f),
                        PathNode.QuadTo(97.9f, 553.4f, 105.9f, 545.9f),
                        PathNode.QuadTo(113.9f, 538.4f, 124.9f, 538.4f),
                        PathNode.HorizontalTo(1043.9f),
                        PathNode.QuadTo(1056.9f, 538.4f, 1063.9f, 546.4f),
                        PathNode.QuadTo(1070.9f, 554.4f, 1070.9f, 567.4f),
                        PathNode.VerticalTo(601.4f),
                        PathNode.QuadTo(1070.9f, 614.4f, 1063.9f, 621.9f),
                        PathNode.QuadTo(1056.9f, 629.4f, 1043.9f, 629.4f),
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

val MiuixIcons.Medium.Add: ImageVector
    get() {
        if (_addMedium != null) return _addMedium!!
        _addMedium = ImageVector.Builder(
            name = "Add.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1188.6f,
            viewportHeight = 1188.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1188.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(648.0f, 134.3f),
                        PathNode.VerticalTo(1054.3f),
                        PathNode.QuadTo(648.0f, 1069.8f, 638.7f, 1079.7f),
                        PathNode.QuadTo(629.3f, 1089.5f, 610.8f, 1089.5f),
                        PathNode.HorizontalTo(578.8f),
                        PathNode.QuadTo(562.3f, 1089.5f, 552.4f, 1079.2f),
                        PathNode.QuadTo(542.5f, 1068.8f, 542.5f, 1054.3f),
                        PathNode.VerticalTo(134.3f),
                        PathNode.QuadTo(542.5f, 118.3f, 553.2f, 108.7f),
                        PathNode.QuadTo(563.8f, 99.0f, 579.8f, 99.0f),
                        PathNode.HorizontalTo(611.8f),
                        PathNode.QuadTo(627.7f, 99.0f, 637.9f, 108.7f),
                        PathNode.QuadTo(648.0f, 118.3f, 648.0f, 134.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1053.8f, 647.5f),
                        PathNode.HorizontalTo(134.8f),
                        PathNode.QuadTo(119.3f, 647.5f, 109.4f, 638.2f),
                        PathNode.QuadTo(99.5f, 628.8f, 99.5f, 610.3f),
                        PathNode.VerticalTo(576.3f),
                        PathNode.QuadTo(99.5f, 559.8f, 109.9f, 549.9f),
                        PathNode.QuadTo(120.3f, 540.0f, 134.8f, 540.0f),
                        PathNode.HorizontalTo(1053.8f),
                        PathNode.QuadTo(1069.7f, 540.0f, 1079.4f, 550.7f),
                        PathNode.QuadTo(1089.0f, 561.3f, 1089.0f, 577.3f),
                        PathNode.VerticalTo(611.3f),
                        PathNode.QuadTo(1089.0f, 627.2f, 1079.4f, 637.4f),
                        PathNode.QuadTo(1069.7f, 647.5f, 1053.8f, 647.5f),
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

val MiuixIcons.Demibold.Add: ImageVector
    get() {
        if (_addDemibold != null) return _addDemibold!!
        _addDemibold = ImageVector.Builder(
            name = "Add.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1202.4f,
            viewportHeight = 1202.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1202.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(660.7f, 141.2f),
                        PathNode.VerticalTo(1061.2f),
                        PathNode.QuadTo(660.7f, 1079.2f, 649.7f, 1090.7f),
                        PathNode.QuadTo(638.7f, 1102.2f, 617.7f, 1102.2f),
                        PathNode.HorizontalTo(585.7f),
                        PathNode.QuadTo(566.7f, 1102.2f, 555.2f, 1090.2f),
                        PathNode.QuadTo(543.7f, 1078.2f, 543.7f, 1061.2f),
                        PathNode.VerticalTo(141.2f),
                        PathNode.QuadTo(543.7f, 123.2f, 556.2f, 111.7f),
                        PathNode.QuadTo(568.7f, 100.2f, 586.7f, 100.2f),
                        PathNode.HorizontalTo(618.7f),
                        PathNode.QuadTo(636.7f, 100.2f, 648.7f, 111.7f),
                        PathNode.QuadTo(660.7f, 123.2f, 660.7f, 141.2f),
                        PathNode.Close,
                        PathNode.MoveTo(1060.7f, 660.2f),
                        PathNode.HorizontalTo(141.7f),
                        PathNode.QuadTo(123.7f, 660.2f, 112.2f, 649.2f),
                        PathNode.QuadTo(100.7f, 638.2f, 100.7f, 617.2f),
                        PathNode.VerticalTo(583.2f),
                        PathNode.QuadTo(100.7f, 564.2f, 112.7f, 552.7f),
                        PathNode.QuadTo(124.7f, 541.2f, 141.7f, 541.2f),
                        PathNode.HorizontalTo(1060.7f),
                        PathNode.QuadTo(1078.7f, 541.2f, 1090.2f, 553.7f),
                        PathNode.QuadTo(1101.7f, 566.2f, 1101.7f, 584.2f),
                        PathNode.VerticalTo(618.2f),
                        PathNode.QuadTo(1101.7f, 636.2f, 1090.2f, 648.2f),
                        PathNode.QuadTo(1078.7f, 660.2f, 1060.7f, 660.2f),
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
