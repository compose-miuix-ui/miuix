// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.basic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Basic.Sidebar: ImageVector
    get() {
        if (_sidebar != null) return _sidebar!!
        _sidebar = ImageVector.Builder(
            name = "Sidebar",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1224.0f,
            viewportHeight = 1224.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1224.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1018.0f, 182.5f),
                        PathNode.QuadTo(1073.0f, 210.5f, 1101.0f, 265.5f),
                        PathNode.QuadTo(1115.0f, 293.5f, 1118.5f, 333.0f),
                        PathNode.QuadTo(1122.0f, 372.5f, 1122.0f, 465.5f),
                        PathNode.VerticalTo(758.5f),
                        PathNode.QuadTo(1122.0f, 851.5f, 1118.5f, 891.0f),
                        PathNode.QuadTo(1115.0f, 930.5f, 1101.0f, 958.5f),
                        PathNode.QuadTo(1073.0f, 1013.5f, 1018.0f, 1041.5f),
                        PathNode.QuadTo(990.0f, 1055.5f, 950.5f, 1059.0f),
                        PathNode.QuadTo(911.0f, 1062.5f, 818.0f, 1062.5f),
                        PathNode.HorizontalTo(406.0f),
                        PathNode.QuadTo(313.0f, 1062.5f, 273.0f, 1059.0f),
                        PathNode.QuadTo(233.0f, 1055.5f, 205.0f, 1041.5f),
                        PathNode.QuadTo(151.0f, 1013.5f, 122.0f, 958.5f),
                        PathNode.QuadTo(108.0f, 930.5f, 105.0f, 891.0f),
                        PathNode.QuadTo(102.0f, 851.5f, 102.0f, 758.5f),
                        PathNode.VerticalTo(465.5f),
                        PathNode.QuadTo(102.0f, 372.5f, 105.0f, 333.0f),
                        PathNode.QuadTo(108.0f, 293.5f, 122.0f, 265.5f),
                        PathNode.QuadTo(151.0f, 210.5f, 205.0f, 182.5f),
                        PathNode.QuadTo(233.0f, 168.5f, 273.0f, 165.0f),
                        PathNode.QuadTo(313.0f, 161.5f, 406.0f, 161.5f),
                        PathNode.HorizontalTo(818.0f),
                        PathNode.QuadTo(911.0f, 161.5f, 950.5f, 165.0f),
                        PathNode.QuadTo(990.0f, 168.5f, 1018.0f, 182.5f),
                        PathNode.Close,
                        PathNode.MoveTo(247.0f, 258.5f),
                        PathNode.QuadTo(216.0f, 275.5f, 199.0f, 306.5f),
                        PathNode.QuadTo(191.0f, 322.5f, 189.0f, 345.5f),
                        PathNode.QuadTo(187.0f, 368.5f, 187.0f, 421.5f),
                        PathNode.VerticalTo(801.5f),
                        PathNode.QuadTo(187.0f, 855.5f, 189.0f, 878.0f),
                        PathNode.QuadTo(191.0f, 900.5f, 199.0f, 916.5f),
                        PathNode.QuadTo(217.0f, 949.5f, 247.0f, 964.5f),
                        PathNode.QuadTo(263.0f, 972.5f, 286.0f, 974.5f),
                        PathNode.QuadTo(309.0f, 976.5f, 362.0f, 976.5f),
                        PathNode.HorizontalTo(519.0f),
                        PathNode.VerticalTo(246.5f),
                        PathNode.HorizontalTo(362.0f),
                        PathNode.QuadTo(309.0f, 246.5f, 286.0f, 248.5f),
                        PathNode.QuadTo(263.0f, 250.5f, 247.0f, 258.5f),
                        PathNode.Close,
                        PathNode.MoveTo(438.0f, 842.5f),
                        PathNode.VerticalTo(869.5f),
                        PathNode.QuadTo(438.0f, 883.5f, 430.5f, 891.0f),
                        PathNode.QuadTo(423.0f, 898.5f, 409.0f, 898.5f),
                        PathNode.HorizontalTo(302.0f),
                        PathNode.QuadTo(290.0f, 898.5f, 281.0f, 892.0f),
                        PathNode.QuadTo(272.0f, 885.5f, 272.0f, 871.5f),
                        PathNode.VerticalTo(840.5f),
                        PathNode.QuadTo(272.0f, 826.5f, 281.0f, 820.0f),
                        PathNode.QuadTo(290.0f, 813.5f, 302.0f, 813.5f),
                        PathNode.HorizontalTo(409.0f),
                        PathNode.QuadTo(423.0f, 813.5f, 430.5f, 821.5f),
                        PathNode.QuadTo(438.0f, 829.5f, 438.0f, 842.5f),
                        PathNode.Close,
                        PathNode.MoveTo(604.0f, 976.5f),
                        PathNode.HorizontalTo(861.0f),
                        PathNode.QuadTo(915.0f, 976.5f, 937.5f, 974.5f),
                        PathNode.QuadTo(960.0f, 972.5f, 977.0f, 964.5f),
                        PathNode.QuadTo(992.0f, 956.5f, 1004.5f, 944.5f),
                        PathNode.QuadTo(1017.0f, 932.5f, 1024.0f, 916.5f),
                        PathNode.QuadTo(1032.0f, 900.5f, 1034.0f, 878.0f),
                        PathNode.QuadTo(1036.0f, 855.5f, 1036.0f, 801.5f),
                        PathNode.VerticalTo(421.5f),
                        PathNode.QuadTo(1036.0f, 368.5f, 1034.0f, 345.5f),
                        PathNode.QuadTo(1032.0f, 322.5f, 1024.0f, 306.5f),
                        PathNode.QuadTo(1010.0f, 276.5f, 977.0f, 258.5f),
                        PathNode.QuadTo(960.0f, 250.5f, 937.5f, 248.5f),
                        PathNode.QuadTo(915.0f, 246.5f, 861.0f, 246.5f),
                        PathNode.HorizontalTo(604.0f),
                        PathNode.Close,
                        PathNode.MoveTo(438.0f, 691.5f),
                        PathNode.VerticalTo(718.5f),
                        PathNode.QuadTo(438.0f, 732.5f, 430.5f, 740.0f),
                        PathNode.QuadTo(423.0f, 747.5f, 409.0f, 747.5f),
                        PathNode.HorizontalTo(302.0f),
                        PathNode.QuadTo(290.0f, 747.5f, 281.0f, 741.0f),
                        PathNode.QuadTo(272.0f, 734.5f, 272.0f, 720.5f),
                        PathNode.VerticalTo(689.5f),
                        PathNode.QuadTo(272.0f, 675.5f, 281.0f, 669.0f),
                        PathNode.QuadTo(290.0f, 662.5f, 302.0f, 662.5f),
                        PathNode.HorizontalTo(409.0f),
                        PathNode.QuadTo(423.0f, 662.5f, 430.5f, 670.5f),
                        PathNode.QuadTo(438.0f, 678.5f, 438.0f, 691.5f),
                        PathNode.Close,
                        PathNode.MoveTo(438.0f, 541.5f),
                        PathNode.VerticalTo(568.5f),
                        PathNode.QuadTo(438.0f, 582.5f, 430.5f, 590.0f),
                        PathNode.QuadTo(423.0f, 597.5f, 409.0f, 597.5f),
                        PathNode.HorizontalTo(302.0f),
                        PathNode.QuadTo(290.0f, 597.5f, 281.0f, 591.0f),
                        PathNode.QuadTo(272.0f, 584.5f, 272.0f, 570.5f),
                        PathNode.VerticalTo(539.5f),
                        PathNode.QuadTo(272.0f, 525.5f, 281.0f, 519.0f),
                        PathNode.QuadTo(290.0f, 512.5f, 302.0f, 512.5f),
                        PathNode.HorizontalTo(409.0f),
                        PathNode.QuadTo(423.0f, 512.5f, 430.5f, 520.5f),
                        PathNode.QuadTo(438.0f, 528.5f, 438.0f, 541.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sidebar!!
    }

private var _sidebar: ImageVector? = null
