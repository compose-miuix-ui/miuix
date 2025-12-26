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
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -17.899999999999977f, translationY = 1022.3368421052633f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(224.0f, 344.0f),
                        PathNode.HorizontalTo(1186.0f),
                        PathNode.QuadTo(1196.0f, 344.0f, 1201.5f, 349.0f),
                        PathNode.QuadTo(1207.0f, 354.0f, 1207.0f, 364.0f),
                        PathNode.VerticalTo(385.0f),
                        PathNode.QuadTo(1207.0f, 394.0f, 1201.5f, 399.0f),
                        PathNode.QuadTo(1196.0f, 404.0f, 1186.0f, 404.0f),
                        PathNode.HorizontalTo(224.0f),
                        PathNode.LineTo(528.0f, 708.0f),
                        PathNode.QuadTo(534.0f, 714.0f, 534.0f, 721.0f),
                        PathNode.QuadTo(534.0f, 728.0f, 527.0f, 735.0f),
                        PathNode.LineTo(513.0f, 749.0f),
                        PathNode.QuadTo(505.0f, 757.0f, 498.5f, 757.0f),
                        PathNode.QuadTo(492.0f, 757.0f, 485.0f, 749.0f),
                        PathNode.LineTo(141.0f, 405.0f),
                        PathNode.QuadTo(126.0f, 389.0f, 126.0f, 374.0f),
                        PathNode.QuadTo(126.0f, 359.0f, 142.0f, 343.0f),
                        PathNode.LineTo(485.0f, -1.0f),
                        PathNode.QuadTo(493.0f, -9.0f, 499.0f, -9.5f),
                        PathNode.QuadTo(505.0f, -10.0f, 513.0f, -1.0f),
                        PathNode.LineTo(528.0f, 14.0f),
                        PathNode.QuadTo(535.0f, 21.0f, 535.0f, 27.0f),
                        PathNode.QuadTo(535.0f, 33.0f, 528.0f, 40.0f),
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

val MiuixIcons.Regular.Back: ImageVector
    get() {
        if (_backRegular != null) return _backRegular!!
        _backRegular = ImageVector.Builder(
            name = "Back.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1330.2146341463415f,
            viewportHeight = 1330.2146341463415f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -0.6365853658536338f, translationY = 1039.3480578139115f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(256.0f, 331.0f),
                        PathNode.HorizontalTo(1188.0f),
                        PathNode.QuadTo(1204.0f, 331.0f, 1212.0f, 339.0f),
                        PathNode.QuadTo(1220.0f, 347.0f, 1220.0f, 362.0f),
                        PathNode.VerticalTo(389.0f),
                        PathNode.QuadTo(1220.0f, 402.0f, 1211.5f, 409.5f),
                        PathNode.QuadTo(1203.0f, 417.0f, 1188.0f, 417.0f),
                        PathNode.HorizontalTo(256.0f),
                        PathNode.LineTo(540.0f, 701.0f),
                        PathNode.QuadTo(550.0f, 711.0f, 550.0f, 721.0f),
                        PathNode.QuadTo(550.0f, 731.0f, 538.0f, 743.0f),
                        PathNode.LineTo(521.0f, 760.0f),
                        PathNode.QuadTo(509.0f, 772.0f, 499.0f, 772.0f),
                        PathNode.QuadTo(489.0f, 772.0f, 477.0f, 760.0f),
                        PathNode.LineTo(130.0f, 412.0f),
                        PathNode.QuadTo(112.0f, 394.0f, 111.5f, 374.5f),
                        PathNode.QuadTo(111.0f, 355.0f, 131.0f, 335.0f),
                        PathNode.LineTo(477.0f, -11.0f),
                        PathNode.QuadTo(489.0f, -23.0f, 498.5f, -23.5f),
                        PathNode.QuadTo(508.0f, -24.0f, 521.0f, -11.0f),
                        PathNode.LineTo(540.0f, 8.0f),
                        PathNode.QuadTo(551.0f, 19.0f, 551.0f, 27.5f),
                        PathNode.QuadTo(551.0f, 36.0f, 539.0f, 48.0f),
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

val MiuixIcons.Heavy.Back: ImageVector
    get() {
        if (_backHeavy != null) return _backHeavy!!
        _backHeavy = ImageVector.Builder(
            name = "Back.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1374.611320754717f,
            viewportHeight = 1374.611320754717f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 22.060377358490598f, translationY = 1061.8056603773584f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(299.0f, 313.0f),
                        PathNode.HorizontalTo(1188.0f),
                        PathNode.QuadTo(1211.0f, 313.0f, 1224.5f, 326.0f),
                        PathNode.QuadTo(1238.0f, 339.0f, 1238.0f, 362.0f),
                        PathNode.VerticalTo(389.0f),
                        PathNode.QuadTo(1238.0f, 410.0f, 1224.5f, 422.5f),
                        PathNode.QuadTo(1211.0f, 435.0f, 1188.0f, 435.0f),
                        PathNode.HorizontalTo(299.0f),
                        PathNode.LineTo(552.0f, 688.0f),
                        PathNode.QuadTo(568.0f, 703.0f, 568.0f, 720.5f),
                        PathNode.QuadTo(568.0f, 738.0f, 550.0f, 756.0f),
                        PathNode.LineTo(534.0f, 773.0f),
                        PathNode.QuadTo(517.0f, 791.0f, 499.0f, 790.5f),
                        PathNode.QuadTo(481.0f, 790.0f, 464.0f, 773.0f),
                        PathNode.LineTo(117.0f, 425.0f),
                        PathNode.QuadTo(93.0f, 401.0f, 92.5f, 374.0f),
                        PathNode.QuadTo(92.0f, 347.0f, 118.0f, 322.0f),
                        PathNode.LineTo(464.0f, -24.0f),
                        PathNode.QuadTo(481.0f, -41.0f, 498.0f, -41.5f),
                        PathNode.QuadTo(515.0f, -42.0f, 533.0f, -24.0f),
                        PathNode.LineTo(552.0f, -5.0f),
                        PathNode.QuadTo(569.0f, 11.0f, 569.0f, 27.5f),
                        PathNode.QuadTo(569.0f, 44.0f, 551.0f, 61.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _backHeavy!!
    }

private var _backHeavy: ImageVector? = null
