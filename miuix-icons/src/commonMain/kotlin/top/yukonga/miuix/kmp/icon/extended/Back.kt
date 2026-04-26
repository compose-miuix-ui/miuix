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

val MiuixIcons.Back: ImageVector
    get() = MiuixIcons.Regular.Back

val MiuixIcons.Light.Back: ImageVector
    get() {
        if (_backLight != null) return _backLight!!
        _backLight = ImageVector.Builder(
            name = "Back.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1297.2f,
            viewportHeight = 1297.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1297.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(206.1f, 619.1f),
                        PathNode.HorizontalTo(1168.1f),
                        PathNode.QuadTo(1178.1f, 619.1f, 1183.6f, 624.1f),
                        PathNode.QuadTo(1189.1f, 629.1f, 1189.1f, 639.1f),
                        PathNode.VerticalTo(660.1f),
                        PathNode.QuadTo(1189.1f, 669.1f, 1183.6f, 674.1f),
                        PathNode.QuadTo(1178.1f, 679.1f, 1168.1f, 679.1f),
                        PathNode.HorizontalTo(206.1f),
                        PathNode.LineTo(510.1f, 983.1f),
                        PathNode.QuadTo(516.1f, 989.1f, 516.1f, 996.1f),
                        PathNode.QuadTo(516.1f, 1003.1f, 509.1f, 1010.1f),
                        PathNode.LineTo(495.1f, 1024.1f),
                        PathNode.QuadTo(487.1f, 1032.1f, 480.6f, 1032.1f),
                        PathNode.QuadTo(474.1f, 1032.1f, 467.1f, 1024.1f),
                        PathNode.LineTo(123.1f, 680.1f),
                        PathNode.QuadTo(108.1f, 664.1f, 108.1f, 649.1f),
                        PathNode.QuadTo(108.1f, 634.1f, 124.1f, 618.1f),
                        PathNode.LineTo(467.1f, 274.1f),
                        PathNode.QuadTo(475.1f, 266.1f, 481.1f, 265.6f),
                        PathNode.QuadTo(487.1f, 265.1f, 495.1f, 274.1f),
                        PathNode.LineTo(510.1f, 289.1f),
                        PathNode.QuadTo(517.1f, 296.1f, 517.1f, 302.1f),
                        PathNode.QuadTo(517.1f, 308.1f, 510.1f, 315.1f),
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

val MiuixIcons.Normal.Back: ImageVector
    get() {
        if (_backNormal != null) return _backNormal!!
        _backNormal = ImageVector.Builder(
            name = "Back.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1320.3f,
            viewportHeight = 1320.3f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1320.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(240.3f, 621.4f),
                        PathNode.HorizontalTo(1181.7f),
                        PathNode.QuadTo(1195.8f, 621.4f, 1203.1f, 628.4f),
                        PathNode.QuadTo(1210.3f, 635.5f, 1210.3f, 648.9f),
                        PathNode.VerticalTo(674.1f),
                        PathNode.QuadTo(1210.3f, 685.8f, 1202.7f, 692.5f),
                        PathNode.QuadTo(1195.2f, 699.2f, 1181.7f, 699.2f),
                        PathNode.HorizontalTo(240.3f),
                        PathNode.LineTo(530.6f, 989.5f),
                        PathNode.QuadTo(539.3f, 998.2f, 539.3f, 1007.3f),
                        PathNode.QuadTo(539.3f, 1016.4f, 528.9f, 1026.8f),
                        PathNode.LineTo(512.8f, 1042.9f),
                        PathNode.QuadTo(502.1f, 1053.6f, 493.2f, 1053.6f),
                        PathNode.QuadTo(484.3f, 1053.6f, 473.8f, 1042.9f),
                        PathNode.LineTo(127.8f, 696.1f),
                        PathNode.QuadTo(110.7f, 678.7f, 110.4f, 660.6f),
                        PathNode.QuadTo(110.0f, 642.6f, 128.8f, 623.8f),
                        PathNode.LineTo(473.8f, 278.4f),
                        PathNode.QuadTo(484.6f, 267.7f, 493.0f, 267.2f),
                        PathNode.QuadTo(501.4f, 266.7f, 512.8f, 278.4f),
                        PathNode.LineTo(530.6f, 296.2f),
                        PathNode.QuadTo(540.3f, 305.9f, 540.3f, 313.6f),
                        PathNode.QuadTo(540.3f, 321.4f, 529.9f, 331.8f),
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

val MiuixIcons.Regular.Back: ImageVector
    get() {
        if (_backRegular != null) return _backRegular!!
        _backRegular = ImageVector.Builder(
            name = "Back.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1330.8f,
            viewportHeight = 1330.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1330.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(255.9f, 622.4f),
                        PathNode.HorizontalTo(1187.9f),
                        PathNode.QuadTo(1203.9f, 622.4f, 1211.9f, 630.4f),
                        PathNode.QuadTo(1219.9f, 638.4f, 1219.9f, 653.4f),
                        PathNode.VerticalTo(680.4f),
                        PathNode.QuadTo(1219.9f, 693.4f, 1211.4f, 700.9f),
                        PathNode.QuadTo(1202.9f, 708.4f, 1187.9f, 708.4f),
                        PathNode.HorizontalTo(255.9f),
                        PathNode.LineTo(539.9f, 992.4f),
                        PathNode.QuadTo(549.9f, 1002.4f, 549.9f, 1012.4f),
                        PathNode.QuadTo(549.9f, 1022.4f, 537.9f, 1034.4f),
                        PathNode.LineTo(520.9f, 1051.4f),
                        PathNode.QuadTo(508.9f, 1063.4f, 498.9f, 1063.4f),
                        PathNode.QuadTo(488.9f, 1063.4f, 476.9f, 1051.4f),
                        PathNode.LineTo(129.9f, 703.4f),
                        PathNode.QuadTo(111.9f, 685.4f, 111.4f, 665.9f),
                        PathNode.QuadTo(110.9f, 646.4f, 130.9f, 626.4f),
                        PathNode.LineTo(476.9f, 280.4f),
                        PathNode.QuadTo(488.9f, 268.4f, 498.4f, 267.9f),
                        PathNode.QuadTo(507.9f, 267.4f, 520.9f, 280.4f),
                        PathNode.LineTo(539.9f, 299.4f),
                        PathNode.QuadTo(550.9f, 310.4f, 550.9f, 318.9f),
                        PathNode.QuadTo(550.9f, 327.4f, 538.9f, 339.4f),
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

val MiuixIcons.Medium.Back: ImageVector
    get() {
        if (_backMedium != null) return _backMedium!!
        _backMedium = ImageVector.Builder(
            name = "Back.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1356.9f,
            viewportHeight = 1356.9f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1356.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(294.5f, 624.6f),
                        PathNode.HorizontalTo(1201.3f),
                        PathNode.QuadTo(1221.4f, 624.6f, 1232.6f, 635.5f),
                        PathNode.QuadTo(1243.8f, 646.5f, 1243.8f, 666.2f),
                        PathNode.VerticalTo(693.2f),
                        PathNode.QuadTo(1243.8f, 710.9f, 1232.4f, 721.3f),
                        PathNode.QuadTo(1221.0f, 731.8f, 1201.3f, 731.8f),
                        PathNode.HorizontalTo(294.5f),
                        PathNode.LineTo(560.3f, 997.5f),
                        PathNode.QuadTo(573.8f, 1010.5f, 573.8f, 1024.9f),
                        PathNode.QuadTo(573.8f, 1039.3f, 558.3f, 1054.8f),
                        PathNode.LineTo(541.9f, 1071.8f),
                        PathNode.QuadTo(527.0f, 1087.3f, 512.3f, 1087.0f),
                        PathNode.QuadTo(497.5f, 1086.8f, 482.6f, 1071.8f),
                        PathNode.LineTo(135.6f, 723.8f),
                        PathNode.QuadTo(114.1f, 702.3f, 113.6f, 678.4f),
                        PathNode.QuadTo(113.1f, 654.5f, 136.6f, 631.5f),
                        PathNode.LineTo(482.6f, 285.5f),
                        PathNode.QuadTo(497.5f, 270.6f, 511.5f, 270.1f),
                        PathNode.QuadTo(525.4f, 269.6f, 541.3f, 285.5f),
                        PathNode.LineTo(560.3f, 304.5f),
                        PathNode.QuadTo(574.8f, 318.5f, 574.8f, 331.7f),
                        PathNode.QuadTo(574.8f, 344.9f, 559.3f, 359.8f),
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

val MiuixIcons.Demibold.Back: ImageVector
    get() {
        if (_backDemibold != null) return _backDemibold!!
        _backDemibold = ImageVector.Builder(
            name = "Back.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1375.2f,
            viewportHeight = 1375.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1375.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(321.6f, 626.1f),
                        PathNode.HorizontalTo(1210.6f),
                        PathNode.QuadTo(1233.6f, 626.1f, 1247.1f, 639.1f),
                        PathNode.QuadTo(1260.6f, 652.1f, 1260.6f, 675.1f),
                        PathNode.VerticalTo(702.1f),
                        PathNode.QuadTo(1260.6f, 723.1f, 1247.1f, 735.6f),
                        PathNode.QuadTo(1233.6f, 748.1f, 1210.6f, 748.1f),
                        PathNode.HorizontalTo(321.6f),
                        PathNode.LineTo(574.6f, 1001.1f),
                        PathNode.QuadTo(590.6f, 1016.1f, 590.6f, 1033.6f),
                        PathNode.QuadTo(590.6f, 1051.1f, 572.6f, 1069.1f),
                        PathNode.LineTo(556.6f, 1086.1f),
                        PathNode.QuadTo(539.6f, 1104.1f, 521.6f, 1103.6f),
                        PathNode.QuadTo(503.6f, 1103.1f, 486.6f, 1086.1f),
                        PathNode.LineTo(139.6f, 738.1f),
                        PathNode.QuadTo(115.6f, 714.1f, 115.1f, 687.1f),
                        PathNode.QuadTo(114.6f, 660.1f, 140.6f, 635.1f),
                        PathNode.LineTo(486.6f, 289.1f),
                        PathNode.QuadTo(503.6f, 272.1f, 520.6f, 271.6f),
                        PathNode.QuadTo(537.6f, 271.1f, 555.6f, 289.1f),
                        PathNode.LineTo(574.6f, 308.1f),
                        PathNode.QuadTo(591.6f, 324.1f, 591.6f, 340.6f),
                        PathNode.QuadTo(591.6f, 357.1f, 573.6f, 374.1f),
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
