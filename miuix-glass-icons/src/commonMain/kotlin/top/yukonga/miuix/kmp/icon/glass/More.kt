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

val MiuixIcons.Glass.More: ImageVector
    get() = MiuixIcons.Glass.Regular.More

val MiuixIcons.Glass.Light.More: ImageVector
    get() {
        if (_moreLight != null) return _moreLight!!
        _moreLight = ImageVector.Builder(
            name = "More.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(571.4f, 993.9f),
                        PathNode.QuadTo(547.3f, 993.9f, 529.9f, 976.5f),
                        PathNode.QuadTo(512.4f, 959.0f, 512.4f, 934.9f),
                        PathNode.QuadTo(512.4f, 910.8f, 529.9f, 893.4f),
                        PathNode.QuadTo(547.3f, 875.9f, 571.4f, 875.9f),
                        PathNode.QuadTo(595.5f, 875.9f, 613.0f, 893.4f),
                        PathNode.QuadTo(630.4f, 910.8f, 630.4f, 934.9f),
                        PathNode.QuadTo(630.4f, 959.0f, 613.0f, 976.5f),
                        PathNode.QuadTo(595.5f, 993.9f, 571.4f, 993.9f),
                        PathNode.Close,
                        PathNode.MoveTo(571.4f, 265.9f),
                        PathNode.QuadTo(547.3f, 265.9f, 529.9f, 248.9f),
                        PathNode.QuadTo(512.4f, 231.9f, 512.4f, 206.9f),
                        PathNode.QuadTo(512.4f, 183.3f, 529.9f, 166.1f),
                        PathNode.QuadTo(547.3f, 148.9f, 571.4f, 148.9f),
                        PathNode.QuadTo(595.5f, 148.9f, 613.0f, 166.1f),
                        PathNode.QuadTo(630.4f, 183.3f, 630.4f, 206.9f),
                        PathNode.QuadTo(630.4f, 231.9f, 613.0f, 248.9f),
                        PathNode.QuadTo(595.5f, 265.9f, 571.4f, 265.9f),
                        PathNode.Close,
                        PathNode.MoveTo(571.4f, 629.9f),
                        PathNode.QuadTo(547.3f, 629.9f, 529.9f, 612.5f),
                        PathNode.QuadTo(512.4f, 595.0f, 512.4f, 570.9f),
                        PathNode.QuadTo(512.4f, 546.8f, 529.9f, 529.4f),
                        PathNode.QuadTo(547.3f, 511.9f, 571.4f, 511.9f),
                        PathNode.QuadTo(595.5f, 511.9f, 613.0f, 529.4f),
                        PathNode.QuadTo(630.4f, 546.8f, 630.4f, 570.9f),
                        PathNode.QuadTo(630.4f, 595.0f, 613.0f, 612.5f),
                        PathNode.QuadTo(595.5f, 629.9f, 571.4f, 629.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreLight!!
    }

private var _moreLight: ImageVector? = null

val MiuixIcons.Glass.Normal.More: ImageVector
    get() {
        if (_moreNormal != null) return _moreNormal!!
        _moreNormal = ImageVector.Builder(
            name = "More.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(571.4f, 1002.2f),
                        PathNode.QuadTo(544.0f, 1002.2f, 524.1f, 982.3f),
                        PathNode.QuadTo(504.2f, 962.4f, 504.2f, 934.9f),
                        PathNode.QuadTo(504.2f, 907.5f, 524.1f, 887.6f),
                        PathNode.QuadTo(544.0f, 867.7f, 571.4f, 867.7f),
                        PathNode.QuadTo(598.9f, 867.7f, 618.8f, 887.6f),
                        PathNode.QuadTo(638.7f, 907.5f, 638.7f, 934.9f),
                        PathNode.QuadTo(638.7f, 962.4f, 618.8f, 982.3f),
                        PathNode.QuadTo(598.9f, 1002.2f, 571.4f, 1002.2f),
                        PathNode.Close,
                        PathNode.MoveTo(571.4f, 274.2f),
                        PathNode.QuadTo(544.0f, 274.2f, 524.1f, 254.8f),
                        PathNode.QuadTo(504.2f, 235.3f, 504.2f, 206.9f),
                        PathNode.QuadTo(504.2f, 179.6f, 524.1f, 160.1f),
                        PathNode.QuadTo(544.0f, 140.7f, 571.4f, 140.7f),
                        PathNode.QuadTo(598.9f, 140.7f, 618.8f, 160.1f),
                        PathNode.QuadTo(638.7f, 179.6f, 638.7f, 206.9f),
                        PathNode.QuadTo(638.7f, 235.3f, 618.8f, 254.8f),
                        PathNode.QuadTo(598.9f, 274.2f, 571.4f, 274.2f),
                        PathNode.Close,
                        PathNode.MoveTo(571.4f, 638.2f),
                        PathNode.QuadTo(544.0f, 638.2f, 524.1f, 618.3f),
                        PathNode.QuadTo(504.2f, 598.4f, 504.2f, 570.9f),
                        PathNode.QuadTo(504.2f, 543.5f, 524.1f, 523.6f),
                        PathNode.QuadTo(544.0f, 503.7f, 571.4f, 503.7f),
                        PathNode.QuadTo(598.9f, 503.7f, 618.8f, 523.6f),
                        PathNode.QuadTo(638.7f, 543.5f, 638.7f, 570.9f),
                        PathNode.QuadTo(638.7f, 598.4f, 618.8f, 618.3f),
                        PathNode.QuadTo(598.9f, 638.2f, 571.4f, 638.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreNormal!!
    }

private var _moreNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.More: ImageVector
    get() {
        if (_moreRegular != null) return _moreRegular!!
        _moreRegular = ImageVector.Builder(
            name = "More.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(571.4f, 1005.9f),
                        PathNode.QuadTo(542.4f, 1005.9f, 521.4f, 984.9f),
                        PathNode.QuadTo(500.4f, 963.9f, 500.4f, 934.9f),
                        PathNode.QuadTo(500.4f, 905.9f, 521.4f, 884.9f),
                        PathNode.QuadTo(542.4f, 863.9f, 571.4f, 863.9f),
                        PathNode.QuadTo(600.4f, 863.9f, 621.4f, 884.9f),
                        PathNode.QuadTo(642.4f, 905.9f, 642.4f, 934.9f),
                        PathNode.QuadTo(642.4f, 963.9f, 621.4f, 984.9f),
                        PathNode.QuadTo(600.4f, 1005.9f, 571.4f, 1005.9f),
                        PathNode.Close,
                        PathNode.MoveTo(571.4f, 277.9f),
                        PathNode.QuadTo(542.4f, 277.9f, 521.4f, 257.4f),
                        PathNode.QuadTo(500.4f, 236.9f, 500.4f, 206.9f),
                        PathNode.QuadTo(500.4f, 177.9f, 521.4f, 157.4f),
                        PathNode.QuadTo(542.4f, 136.9f, 571.4f, 136.9f),
                        PathNode.QuadTo(600.4f, 136.9f, 621.4f, 157.4f),
                        PathNode.QuadTo(642.4f, 177.9f, 642.4f, 206.9f),
                        PathNode.QuadTo(642.4f, 236.9f, 621.4f, 257.4f),
                        PathNode.QuadTo(600.4f, 277.9f, 571.4f, 277.9f),
                        PathNode.Close,
                        PathNode.MoveTo(571.4f, 641.9f),
                        PathNode.QuadTo(542.4f, 641.9f, 521.4f, 620.9f),
                        PathNode.QuadTo(500.4f, 599.9f, 500.4f, 570.9f),
                        PathNode.QuadTo(500.4f, 541.9f, 521.4f, 520.9f),
                        PathNode.QuadTo(542.4f, 499.9f, 571.4f, 499.9f),
                        PathNode.QuadTo(600.4f, 499.9f, 621.4f, 520.9f),
                        PathNode.QuadTo(642.4f, 541.9f, 642.4f, 570.9f),
                        PathNode.QuadTo(642.4f, 599.9f, 621.4f, 620.9f),
                        PathNode.QuadTo(600.4f, 641.9f, 571.4f, 641.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreRegular!!
    }

private var _moreRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.More: ImageVector
    get() {
        if (_moreMedium != null) return _moreMedium!!
        _moreMedium = ImageVector.Builder(
            name = "More.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(571.6f, 1015.4f),
                        PathNode.QuadTo(538.6f, 1015.4f, 514.8f, 991.7f),
                        PathNode.QuadTo(491.0f, 968.0f, 491.0f, 934.9f),
                        PathNode.QuadTo(491.0f, 901.9f, 514.7f, 878.2f),
                        PathNode.QuadTo(538.4f, 854.5f, 571.4f, 854.5f),
                        PathNode.QuadTo(604.5f, 854.5f, 628.2f, 878.2f),
                        PathNode.QuadTo(651.9f, 901.9f, 651.9f, 934.9f),
                        PathNode.QuadTo(651.9f, 968.0f, 628.2f, 991.7f),
                        PathNode.QuadTo(604.5f, 1015.4f, 571.6f, 1015.4f),
                        PathNode.Close,
                        PathNode.MoveTo(571.4f, 287.4f),
                        PathNode.QuadTo(538.4f, 287.4f, 514.7f, 264.1f),
                        PathNode.QuadTo(491.0f, 240.7f, 491.0f, 206.9f),
                        PathNode.QuadTo(491.0f, 174.0f, 514.7f, 150.7f),
                        PathNode.QuadTo(538.4f, 127.5f, 571.4f, 127.5f),
                        PathNode.QuadTo(604.5f, 127.5f, 628.2f, 150.7f),
                        PathNode.QuadTo(651.9f, 173.9f, 651.9f, 206.8f),
                        PathNode.QuadTo(651.9f, 240.8f, 628.2f, 264.1f),
                        PathNode.QuadTo(604.5f, 287.4f, 571.4f, 287.4f),
                        PathNode.Close,
                        PathNode.MoveTo(571.6f, 651.4f),
                        PathNode.QuadTo(538.6f, 651.4f, 514.8f, 627.7f),
                        PathNode.QuadTo(491.0f, 604.0f, 491.0f, 570.9f),
                        PathNode.QuadTo(491.0f, 537.9f, 514.7f, 514.2f),
                        PathNode.QuadTo(538.4f, 490.5f, 571.4f, 490.5f),
                        PathNode.QuadTo(604.5f, 490.5f, 628.2f, 514.2f),
                        PathNode.QuadTo(651.9f, 537.9f, 651.9f, 570.9f),
                        PathNode.QuadTo(651.9f, 604.0f, 628.2f, 627.7f),
                        PathNode.QuadTo(604.5f, 651.4f, 571.6f, 651.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreMedium!!
    }

private var _moreMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.More: ImageVector
    get() {
        if (_moreDemibold != null) return _moreDemibold!!
        _moreDemibold = ImageVector.Builder(
            name = "More.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(571.7f, 1022.0f),
                        PathNode.QuadTo(536.0f, 1022.0f, 510.2f, 996.4f),
                        PathNode.QuadTo(484.3f, 970.8f, 484.3f, 934.9f),
                        PathNode.QuadTo(484.3f, 899.0f, 509.9f, 873.4f),
                        PathNode.QuadTo(535.5f, 847.8f, 571.4f, 847.8f),
                        PathNode.QuadTo(607.3f, 847.8f, 632.9f, 873.4f),
                        PathNode.QuadTo(658.5f, 899.0f, 658.5f, 934.9f),
                        PathNode.QuadTo(658.5f, 970.8f, 632.9f, 996.4f),
                        PathNode.QuadTo(607.3f, 1022.0f, 571.7f, 1022.0f),
                        PathNode.Close,
                        PathNode.MoveTo(571.4f, 294.0f),
                        PathNode.QuadTo(535.5f, 294.0f, 509.9f, 268.7f),
                        PathNode.QuadTo(484.3f, 243.4f, 484.3f, 206.9f),
                        PathNode.QuadTo(484.3f, 171.3f, 509.9f, 146.1f),
                        PathNode.QuadTo(535.5f, 120.8f, 571.4f, 120.8f),
                        PathNode.QuadTo(607.3f, 120.8f, 632.9f, 145.9f),
                        PathNode.QuadTo(658.5f, 171.0f, 658.5f, 206.7f),
                        PathNode.QuadTo(658.5f, 243.6f, 632.9f, 268.8f),
                        PathNode.QuadTo(607.3f, 294.0f, 571.4f, 294.0f),
                        PathNode.Close,
                        PathNode.MoveTo(571.7f, 658.0f),
                        PathNode.QuadTo(536.0f, 658.0f, 510.2f, 632.4f),
                        PathNode.QuadTo(484.3f, 606.8f, 484.3f, 570.9f),
                        PathNode.QuadTo(484.3f, 535.0f, 509.9f, 509.4f),
                        PathNode.QuadTo(535.5f, 483.8f, 571.4f, 483.8f),
                        PathNode.QuadTo(607.3f, 483.8f, 632.9f, 509.4f),
                        PathNode.QuadTo(658.5f, 535.0f, 658.5f, 570.9f),
                        PathNode.QuadTo(658.5f, 606.8f, 632.9f, 632.4f),
                        PathNode.QuadTo(607.3f, 658.0f, 571.7f, 658.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreDemibold!!
    }

private var _moreDemibold: ImageVector? = null
