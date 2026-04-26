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

val MiuixIcons.More: ImageVector
    get() = MiuixIcons.Regular.More

val MiuixIcons.Light.More: ImageVector
    get() {
        if (_moreLight != null) return _moreLight!!
        _moreLight = ImageVector.Builder(
            name = "More.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1014.0f,
            viewportHeight = 1014.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1014.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(507.1f, 929.5f),
                        PathNode.QuadTo(483.0f, 929.5f, 465.5f, 912.1f),
                        PathNode.QuadTo(448.0f, 894.6f, 448.0f, 870.6f),
                        PathNode.QuadTo(448.0f, 846.5f, 465.4f, 829.0f),
                        PathNode.QuadTo(482.9f, 811.5f, 506.9f, 811.5f),
                        PathNode.QuadTo(531.0f, 811.5f, 548.5f, 828.9f),
                        PathNode.QuadTo(566.0f, 846.4f, 566.0f, 870.4f),
                        PathNode.QuadTo(566.0f, 894.5f, 548.6f, 912.0f),
                        PathNode.QuadTo(531.1f, 929.5f, 507.1f, 929.5f),
                        PathNode.Close,
                        PathNode.MoveTo(507.1f, 201.5f),
                        PathNode.QuadTo(483.0f, 201.5f, 465.5f, 184.5f),
                        PathNode.QuadTo(448.0f, 167.5f, 448.0f, 142.6f),
                        PathNode.QuadTo(448.0f, 118.5f, 465.4f, 101.5f),
                        PathNode.QuadTo(482.9f, 84.5f, 506.9f, 84.5f),
                        PathNode.QuadTo(531.0f, 84.5f, 548.5f, 101.5f),
                        PathNode.QuadTo(566.0f, 118.5f, 566.0f, 142.6f),
                        PathNode.QuadTo(566.0f, 167.5f, 548.6f, 184.5f),
                        PathNode.QuadTo(531.1f, 201.5f, 507.1f, 201.5f),
                        PathNode.Close,
                        PathNode.MoveTo(507.1f, 565.5f),
                        PathNode.QuadTo(483.0f, 565.5f, 465.5f, 548.1f),
                        PathNode.QuadTo(448.0f, 530.6f, 448.0f, 506.6f),
                        PathNode.QuadTo(448.0f, 482.5f, 465.4f, 465.0f),
                        PathNode.QuadTo(482.9f, 447.5f, 506.9f, 447.5f),
                        PathNode.QuadTo(531.0f, 447.5f, 548.5f, 464.9f),
                        PathNode.QuadTo(566.0f, 482.4f, 566.0f, 506.4f),
                        PathNode.QuadTo(566.0f, 530.5f, 548.6f, 548.0f),
                        PathNode.QuadTo(531.1f, 565.5f, 507.1f, 565.5f),
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

val MiuixIcons.Normal.More: ImageVector
    get() {
        if (_moreNormal != null) return _moreNormal!!
        _moreNormal = ImageVector.Builder(
            name = "More.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1033.8f,
            viewportHeight = 1033.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1033.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(516.9f, 947.6f),
                        PathNode.QuadTo(489.5f, 947.6f, 469.6f, 927.8f),
                        PathNode.QuadTo(449.6f, 907.9f, 449.6f, 880.4f),
                        PathNode.QuadTo(449.6f, 853.0f, 469.5f, 833.1f),
                        PathNode.QuadTo(489.4f, 813.1f, 516.9f, 813.1f),
                        PathNode.QuadTo(544.3f, 813.1f, 564.2f, 833.0f),
                        PathNode.QuadTo(584.1f, 852.9f, 584.1f, 880.4f),
                        PathNode.QuadTo(584.1f, 907.8f, 564.3f, 927.7f),
                        PathNode.QuadTo(544.4f, 947.6f, 516.9f, 947.6f),
                        PathNode.Close,
                        PathNode.MoveTo(516.9f, 219.6f),
                        PathNode.QuadTo(489.5f, 219.6f, 469.6f, 200.2f),
                        PathNode.QuadTo(449.6f, 180.8f, 449.6f, 152.4f),
                        PathNode.QuadTo(449.6f, 125.0f, 469.5f, 105.6f),
                        PathNode.QuadTo(489.4f, 86.1f, 516.9f, 86.1f),
                        PathNode.QuadTo(544.3f, 86.1f, 564.2f, 105.6f),
                        PathNode.QuadTo(584.1f, 125.0f, 584.1f, 152.4f),
                        PathNode.QuadTo(584.1f, 180.8f, 564.3f, 200.2f),
                        PathNode.QuadTo(544.4f, 219.6f, 516.9f, 219.6f),
                        PathNode.Close,
                        PathNode.MoveTo(516.9f, 583.6f),
                        PathNode.QuadTo(489.5f, 583.6f, 469.6f, 563.8f),
                        PathNode.QuadTo(449.6f, 543.9f, 449.6f, 516.4f),
                        PathNode.QuadTo(449.6f, 489.0f, 469.5f, 469.1f),
                        PathNode.QuadTo(489.4f, 449.1f, 516.9f, 449.1f),
                        PathNode.QuadTo(544.3f, 449.1f, 564.2f, 469.0f),
                        PathNode.QuadTo(584.1f, 488.9f, 584.1f, 516.4f),
                        PathNode.QuadTo(584.1f, 543.8f, 564.3f, 563.7f),
                        PathNode.QuadTo(544.4f, 583.6f, 516.9f, 583.6f),
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

val MiuixIcons.Regular.More: ImageVector
    get() {
        if (_moreRegular != null) return _moreRegular!!
        _moreRegular = ImageVector.Builder(
            name = "More.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1042.8f,
            viewportHeight = 1042.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1042.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(521.4f, 955.9f),
                        PathNode.QuadTo(492.4f, 955.9f, 471.4f, 934.9f),
                        PathNode.QuadTo(450.4f, 913.9f, 450.4f, 884.9f),
                        PathNode.QuadTo(450.4f, 855.9f, 471.4f, 834.9f),
                        PathNode.QuadTo(492.4f, 813.9f, 521.4f, 813.9f),
                        PathNode.QuadTo(550.4f, 813.9f, 571.4f, 834.9f),
                        PathNode.QuadTo(592.4f, 855.9f, 592.4f, 884.9f),
                        PathNode.QuadTo(592.4f, 913.9f, 571.4f, 934.9f),
                        PathNode.QuadTo(550.4f, 955.9f, 521.4f, 955.9f),
                        PathNode.Close,
                        PathNode.MoveTo(521.4f, 227.9f),
                        PathNode.QuadTo(492.4f, 227.9f, 471.4f, 207.4f),
                        PathNode.QuadTo(450.4f, 186.9f, 450.4f, 156.9f),
                        PathNode.QuadTo(450.4f, 127.9f, 471.4f, 107.4f),
                        PathNode.QuadTo(492.4f, 86.9f, 521.4f, 86.9f),
                        PathNode.QuadTo(550.4f, 86.9f, 571.4f, 107.4f),
                        PathNode.QuadTo(592.4f, 127.9f, 592.4f, 156.9f),
                        PathNode.QuadTo(592.4f, 186.9f, 571.4f, 207.4f),
                        PathNode.QuadTo(550.4f, 227.9f, 521.4f, 227.9f),
                        PathNode.Close,
                        PathNode.MoveTo(521.4f, 591.9f),
                        PathNode.QuadTo(492.4f, 591.9f, 471.4f, 570.9f),
                        PathNode.QuadTo(450.4f, 549.9f, 450.4f, 520.9f),
                        PathNode.QuadTo(450.4f, 491.9f, 471.4f, 470.9f),
                        PathNode.QuadTo(492.4f, 449.9f, 521.4f, 449.9f),
                        PathNode.QuadTo(550.4f, 449.9f, 571.4f, 470.9f),
                        PathNode.QuadTo(592.4f, 491.9f, 592.4f, 520.9f),
                        PathNode.QuadTo(592.4f, 549.9f, 571.4f, 570.9f),
                        PathNode.QuadTo(550.4f, 591.9f, 521.4f, 591.9f),
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

val MiuixIcons.Medium.More: ImageVector
    get() {
        if (_moreMedium != null) return _moreMedium!!
        _moreMedium = ImageVector.Builder(
            name = "More.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1065.4f,
            viewportHeight = 1065.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1065.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(532.7f, 976.6f),
                        PathNode.QuadTo(499.9f, 976.6f, 476.1f, 952.8f),
                        PathNode.QuadTo(452.3f, 929.0f, 452.3f, 896.2f),
                        PathNode.QuadTo(452.3f, 863.4f, 476.1f, 839.6f),
                        PathNode.QuadTo(499.9f, 815.8f, 532.7f, 815.8f),
                        PathNode.QuadTo(565.5f, 815.8f, 589.3f, 839.6f),
                        PathNode.QuadTo(613.1f, 863.4f, 613.1f, 896.2f),
                        PathNode.QuadTo(613.1f, 929.0f, 589.3f, 952.8f),
                        PathNode.QuadTo(565.5f, 976.6f, 532.7f, 976.6f),
                        PathNode.Close,
                        PathNode.MoveTo(532.7f, 248.6f),
                        PathNode.QuadTo(499.9f, 248.6f, 476.1f, 225.3f),
                        PathNode.QuadTo(452.3f, 202.1f, 452.3f, 168.0f),
                        PathNode.QuadTo(452.3f, 135.1f, 476.1f, 111.9f),
                        PathNode.QuadTo(499.9f, 88.8f, 532.7f, 88.8f),
                        PathNode.QuadTo(565.5f, 88.8f, 589.3f, 112.1f),
                        PathNode.QuadTo(613.1f, 135.3f, 613.1f, 168.3f),
                        PathNode.QuadTo(613.1f, 202.3f, 589.3f, 225.5f),
                        PathNode.QuadTo(565.5f, 248.6f, 532.7f, 248.6f),
                        PathNode.Close,
                        PathNode.MoveTo(532.7f, 612.6f),
                        PathNode.QuadTo(499.9f, 612.6f, 476.1f, 588.8f),
                        PathNode.QuadTo(452.3f, 565.0f, 452.3f, 532.2f),
                        PathNode.QuadTo(452.3f, 499.4f, 476.1f, 475.6f),
                        PathNode.QuadTo(499.9f, 451.8f, 532.7f, 451.8f),
                        PathNode.QuadTo(565.5f, 451.8f, 589.3f, 475.6f),
                        PathNode.QuadTo(613.1f, 499.4f, 613.1f, 532.2f),
                        PathNode.QuadTo(613.1f, 565.0f, 589.3f, 588.8f),
                        PathNode.QuadTo(565.5f, 612.6f, 532.7f, 612.6f),
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

val MiuixIcons.Demibold.More: ImageVector
    get() {
        if (_moreDemibold != null) return _moreDemibold!!
        _moreDemibold = ImageVector.Builder(
            name = "More.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1081.2f,
            viewportHeight = 1081.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1081.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(540.6f, 991.1f),
                        PathNode.QuadTo(505.1f, 991.1f, 479.3f, 965.4f),
                        PathNode.QuadTo(453.6f, 939.6f, 453.6f, 904.1f),
                        PathNode.QuadTo(453.6f, 868.6f, 479.3f, 842.8f),
                        PathNode.QuadTo(505.1f, 817.1f, 540.6f, 817.1f),
                        PathNode.QuadTo(576.1f, 817.1f, 601.9f, 842.8f),
                        PathNode.QuadTo(627.6f, 868.6f, 627.6f, 904.1f),
                        PathNode.QuadTo(627.6f, 939.6f, 601.9f, 965.4f),
                        PathNode.QuadTo(576.1f, 991.1f, 540.6f, 991.1f),
                        PathNode.Close,
                        PathNode.MoveTo(540.6f, 263.1f),
                        PathNode.QuadTo(505.1f, 263.1f, 479.3f, 237.9f),
                        PathNode.QuadTo(453.6f, 212.7f, 453.6f, 175.8f),
                        PathNode.QuadTo(453.6f, 140.1f, 479.3f, 115.1f),
                        PathNode.QuadTo(505.1f, 90.1f, 540.6f, 90.1f),
                        PathNode.QuadTo(576.1f, 90.1f, 601.9f, 115.3f),
                        PathNode.QuadTo(627.6f, 140.5f, 627.6f, 176.2f),
                        PathNode.QuadTo(627.6f, 213.1f, 601.9f, 238.1f),
                        PathNode.QuadTo(576.1f, 263.1f, 540.6f, 263.1f),
                        PathNode.Close,
                        PathNode.MoveTo(540.6f, 627.1f),
                        PathNode.QuadTo(505.1f, 627.1f, 479.3f, 601.4f),
                        PathNode.QuadTo(453.6f, 575.6f, 453.6f, 540.1f),
                        PathNode.QuadTo(453.6f, 504.6f, 479.3f, 478.8f),
                        PathNode.QuadTo(505.1f, 453.1f, 540.6f, 453.1f),
                        PathNode.QuadTo(576.1f, 453.1f, 601.9f, 478.8f),
                        PathNode.QuadTo(627.6f, 504.6f, 627.6f, 540.1f),
                        PathNode.QuadTo(627.6f, 575.6f, 601.9f, 601.4f),
                        PathNode.QuadTo(576.1f, 627.1f, 540.6f, 627.1f),
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
