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
            viewportHeight = 1014.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -158.0f, translationY = 882.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(665.07f, 798.0f),
                PathNode.QuadTo(641.0f, 798.0f, 623.5f, 780.5699999999999f),
                PathNode.QuadTo(606.0f, 763.14f, 606.0f, 739.07f),
                PathNode.QuadTo(606.0f, 715.0f, 623.4300000000001f, 697.5f),
                PathNode.QuadTo(640.86f, 680.0f, 664.93f, 680.0f),
                PathNode.QuadTo(689.0f, 680.0f, 706.5f, 697.4300000000001f),
                PathNode.QuadTo(724.0f, 714.86f, 724.0f, 738.93f),
                PathNode.QuadTo(724.0f, 763.0f, 706.5699999999999f, 780.5f),
                PathNode.QuadTo(689.14f, 798.0f, 665.07f, 798.0f),
                PathNode.Close,
                PathNode.MoveTo(665.07f, 70.0f),
                PathNode.QuadTo(641.0f, 70.0f, 623.5f, 52.985f),
                PathNode.QuadTo(606.0f, 35.97f, 606.0f, 11.07f),
                PathNode.QuadTo(606.0f, -13.0f, 623.4300000000001f, -30.0f),
                PathNode.QuadTo(640.86f, -47.0f, 664.93f, -47.0f),
                PathNode.QuadTo(689.0f, -47.0f, 706.5f, -29.985f),
                PathNode.QuadTo(724.0f, -12.97f, 724.0f, 11.1f),
                PathNode.QuadTo(724.0f, 36.0f, 706.5699999999999f, 53.0f),
                PathNode.QuadTo(689.14f, 70.0f, 665.07f, 70.0f),
                PathNode.Close,
                PathNode.MoveTo(665.07f, 434.0f),
                PathNode.QuadTo(641.0f, 434.0f, 623.5f, 416.57f),
                PathNode.QuadTo(606.0f, 399.14f, 606.0f, 375.07f),
                PathNode.QuadTo(606.0f, 351.0f, 623.4300000000001f, 333.5f),
                PathNode.QuadTo(640.86f, 316.0f, 664.93f, 316.0f),
                PathNode.QuadTo(689.0f, 316.0f, 706.5f, 333.43f),
                PathNode.QuadTo(724.0f, 350.86f, 724.0f, 374.93f),
                PathNode.QuadTo(724.0f, 399.0f, 706.5699999999999f, 416.5f),
                PathNode.QuadTo(689.14f, 434.0f, 665.07f, 434.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _moreLight!!
    }

private var _moreLight: ImageVector? = null

val MiuixIcons.Regular.More: ImageVector
    get() {
        if (_moreRegular != null) return _moreRegular!!
        _moreRegular = ImageVector.Builder(
            name = "More.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1042.8f,
            viewportHeight = 1042.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -143.60000000000002f, translationY = 896.9f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(665.0f, 810.0f),
                PathNode.QuadTo(636.0f, 810.0f, 615.0f, 789.0f),
                PathNode.QuadTo(594.0f, 768.0f, 594.0f, 739.0f),
                PathNode.QuadTo(594.0f, 710.0f, 615.0f, 689.0f),
                PathNode.QuadTo(636.0f, 668.0f, 665.0f, 668.0f),
                PathNode.QuadTo(694.0f, 668.0f, 715.0f, 689.0f),
                PathNode.QuadTo(736.0f, 710.0f, 736.0f, 739.0f),
                PathNode.QuadTo(736.0f, 768.0f, 715.0f, 789.0f),
                PathNode.QuadTo(694.0f, 810.0f, 665.0f, 810.0f),
                PathNode.Close,
                PathNode.MoveTo(665.0f, 82.0f),
                PathNode.QuadTo(636.0f, 82.0f, 615.0f, 61.5f),
                PathNode.QuadTo(594.0f, 41.0f, 594.0f, 11.0f),
                PathNode.QuadTo(594.0f, -18.0f, 615.0f, -38.5f),
                PathNode.QuadTo(636.0f, -59.0f, 665.0f, -59.0f),
                PathNode.QuadTo(694.0f, -59.0f, 715.0f, -38.5f),
                PathNode.QuadTo(736.0f, -18.0f, 736.0f, 11.0f),
                PathNode.QuadTo(736.0f, 41.0f, 715.0f, 61.5f),
                PathNode.QuadTo(694.0f, 82.0f, 665.0f, 82.0f),
                PathNode.Close,
                PathNode.MoveTo(665.0f, 446.0f),
                PathNode.QuadTo(636.0f, 446.0f, 615.0f, 425.0f),
                PathNode.QuadTo(594.0f, 404.0f, 594.0f, 375.0f),
                PathNode.QuadTo(594.0f, 346.0f, 615.0f, 325.0f),
                PathNode.QuadTo(636.0f, 304.0f, 665.0f, 304.0f),
                PathNode.QuadTo(694.0f, 304.0f, 715.0f, 325.0f),
                PathNode.QuadTo(736.0f, 346.0f, 736.0f, 375.0f),
                PathNode.QuadTo(736.0f, 404.0f, 715.0f, 425.0f),
                PathNode.QuadTo(694.0f, 446.0f, 665.0f, 446.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _moreRegular!!
    }

private var _moreRegular: ImageVector? = null

val MiuixIcons.Heavy.More: ImageVector
    get() {
        if (_moreHeavy != null) return _moreHeavy!!
        _moreHeavy = ImageVector.Builder(
            name = "More.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1081.2f,
            viewportHeight = 1081.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -124.39999999999998f, translationY = 916.1f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(665.0f, 826.0f),
                PathNode.QuadTo(629.4647887323944f, 826.0f, 603.7323943661972f, 800.2676056338028f),
                PathNode.QuadTo(578.0f, 774.5352112676056f, 578.0f, 739.0f),
                PathNode.QuadTo(578.0f, 703.4647887323944f, 603.7323943661972f, 677.7323943661972f),
                PathNode.QuadTo(629.4647887323944f, 652.0f, 665.0f, 652.0f),
                PathNode.QuadTo(700.5352112676056f, 652.0f, 726.2676056338028f, 677.7323943661972f),
                PathNode.QuadTo(752.0f, 703.4647887323944f, 752.0f, 739.0f),
                PathNode.QuadTo(752.0f, 774.5352112676056f, 726.2676056338028f, 800.2676056338028f),
                PathNode.QuadTo(700.5352112676056f, 826.0f, 665.0f, 826.0f),
                PathNode.Close,
                PathNode.MoveTo(665.0f, 98.0f),
                PathNode.QuadTo(629.4647887323944f, 98.0f, 603.7323943661972f, 72.785f),
                PathNode.QuadTo(578.0f, 47.57f, 578.0f, 10.67f),
                PathNode.QuadTo(578.0f, -25.0f, 603.7323943661972f, -50.0f),
                PathNode.QuadTo(629.4647887323944f, -75.0f, 665.0f, -75.0f),
                PathNode.QuadTo(700.5352112676056f, -75.0f, 726.2676056338028f, -49.785f),
                PathNode.QuadTo(752.0f, -24.57f, 752.0f, 11.100000000000001f),
                PathNode.QuadTo(752.0f, 48.0f, 726.2676056338028f, 73.0f),
                PathNode.QuadTo(700.5352112676056f, 98.0f, 665.0f, 98.0f),
                PathNode.Close,
                PathNode.MoveTo(665.0f, 462.0f),
                PathNode.QuadTo(629.4647887323944f, 462.0f, 603.7323943661972f, 436.2676056338028f),
                PathNode.QuadTo(578.0f, 410.53521126760563f, 578.0f, 375.0f),
                PathNode.QuadTo(578.0f, 339.46478873239437f, 603.7323943661972f, 313.7323943661972f),
                PathNode.QuadTo(629.4647887323944f, 288.0f, 665.0f, 288.0f),
                PathNode.QuadTo(700.5352112676056f, 288.0f, 726.2676056338028f, 313.7323943661972f),
                PathNode.QuadTo(752.0f, 339.46478873239437f, 752.0f, 375.0f),
                PathNode.QuadTo(752.0f, 410.53521126760563f, 726.2676056338028f, 436.2676056338028f),
                PathNode.QuadTo(700.5352112676056f, 462.0f, 665.0f, 462.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _moreHeavy!!
    }

private var _moreHeavy: ImageVector? = null
