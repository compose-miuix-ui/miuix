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
                        PathNode.MoveTo(1001.2f, 199.5f),
                        PathNode.QuadTo(1054.5f, 226.4f, 1081.4f, 279.7f),
                        PathNode.QuadTo(1095.1f, 306.8f, 1098.5f, 344.8f),
                        PathNode.QuadTo(1101.8f, 382.8f, 1101.8f, 471.9f),
                        PathNode.VerticalTo(752.1f),
                        PathNode.QuadTo(1101.8f, 841.2f, 1098.5f, 879.2f),
                        PathNode.QuadTo(1095.1f, 917.2f, 1081.4f, 944.3f),
                        PathNode.QuadTo(1054.5f, 997.6f, 1001.2f, 1024.5f),
                        PathNode.QuadTo(974.1f, 1038.2f, 936.1f, 1041.6f),
                        PathNode.QuadTo(898.1f, 1044.9f, 809.0f, 1044.9f),
                        PathNode.HorizontalTo(415.0f),
                        PathNode.QuadTo(325.9f, 1044.9f, 287.4f, 1041.6f),
                        PathNode.QuadTo(248.9f, 1038.2f, 221.9f, 1024.5f),
                        PathNode.QuadTo(169.6f, 997.6f, 141.6f, 944.3f),
                        PathNode.QuadTo(128.1f, 917.2f, 125.1f, 879.2f),
                        PathNode.QuadTo(122.2f, 841.2f, 122.2f, 752.1f),
                        PathNode.VerticalTo(471.9f),
                        PathNode.QuadTo(122.2f, 382.8f, 125.1f, 344.8f),
                        PathNode.QuadTo(128.1f, 306.8f, 141.6f, 279.7f),
                        PathNode.QuadTo(169.6f, 226.4f, 221.9f, 199.5f),
                        PathNode.QuadTo(248.9f, 185.8f, 287.4f, 182.4f),
                        PathNode.QuadTo(325.9f, 179.1f, 415.0f, 179.1f),
                        PathNode.HorizontalTo(809.0f),
                        PathNode.QuadTo(898.1f, 179.1f, 936.1f, 182.4f),
                        PathNode.QuadTo(974.1f, 185.8f, 1001.2f, 199.5f),
                        PathNode.Close,
                        PathNode.MoveTo(263.7f, 275.6f),
                        PathNode.QuadTo(234.7f, 291.6f, 218.7f, 320.7f),
                        PathNode.QuadTo(211.2f, 335.7f, 209.3f, 357.4f),
                        PathNode.QuadTo(207.5f, 379.1f, 207.5f, 429.8f),
                        PathNode.VerticalTo(793.3f),
                        PathNode.QuadTo(207.5f, 844.8f, 209.3f, 866.1f),
                        PathNode.QuadTo(211.2f, 887.4f, 218.7f, 902.4f),
                        PathNode.QuadTo(235.5f, 933.4f, 263.7f, 947.4f),
                        PathNode.QuadTo(278.8f, 955.0f, 300.5f, 956.8f),
                        PathNode.QuadTo(322.2f, 958.6f, 372.9f, 958.6f),
                        PathNode.HorizontalTo(521.0f),
                        PathNode.VerticalTo(264.4f),
                        PathNode.HorizontalTo(372.9f),
                        PathNode.QuadTo(322.2f, 264.4f, 300.5f, 266.3f),
                        PathNode.QuadTo(278.8f, 268.1f, 263.7f, 275.6f),
                        PathNode.Close,
                        PathNode.MoveTo(445.6f, 830.9f),
                        PathNode.VerticalTo(857.3f),
                        PathNode.QuadTo(445.6f, 871.1f, 438.1f, 878.6f),
                        PathNode.QuadTo(430.7f, 886.0f, 416.8f, 886.0f),
                        PathNode.HorizontalTo(316.5f),
                        PathNode.QuadTo(304.3f, 886.0f, 295.6f, 879.5f),
                        PathNode.QuadTo(286.8f, 872.9f, 286.8f, 859.1f),
                        PathNode.VerticalTo(829.1f),
                        PathNode.QuadTo(286.8f, 815.2f, 295.6f, 808.7f),
                        PathNode.QuadTo(304.3f, 802.2f, 316.5f, 802.2f),
                        PathNode.HorizontalTo(416.8f),
                        PathNode.QuadTo(430.7f, 802.2f, 438.1f, 810.0f),
                        PathNode.QuadTo(445.6f, 817.9f, 445.6f, 830.9f),
                        PathNode.Close,
                        PathNode.MoveTo(606.4f, 958.6f),
                        PathNode.HorizontalTo(850.2f),
                        PathNode.QuadTo(901.7f, 958.6f, 923.0f, 956.8f),
                        PathNode.QuadTo(944.3f, 955.0f, 960.3f, 947.4f),
                        PathNode.QuadTo(974.3f, 939.9f, 986.0f, 928.6f),
                        PathNode.QuadTo(997.7f, 917.3f, 1004.4f, 902.4f),
                        PathNode.QuadTo(1011.9f, 887.4f, 1013.7f, 866.1f),
                        PathNode.QuadTo(1015.5f, 844.8f, 1015.5f, 793.3f),
                        PathNode.VerticalTo(429.8f),
                        PathNode.QuadTo(1015.5f, 379.1f, 1013.7f, 357.4f),
                        PathNode.QuadTo(1011.9f, 335.7f, 1004.4f, 320.7f),
                        PathNode.QuadTo(991.1f, 292.4f, 960.3f, 275.6f),
                        PathNode.QuadTo(944.3f, 268.1f, 923.0f, 266.3f),
                        PathNode.QuadTo(901.7f, 264.4f, 850.2f, 264.4f),
                        PathNode.HorizontalTo(606.4f),
                        PathNode.Close,
                        PathNode.MoveTo(445.6f, 685.8f),
                        PathNode.VerticalTo(712.2f),
                        PathNode.QuadTo(445.6f, 726.0f, 438.1f, 733.4f),
                        PathNode.QuadTo(430.7f, 740.9f, 416.8f, 740.9f),
                        PathNode.HorizontalTo(316.5f),
                        PathNode.QuadTo(304.3f, 740.9f, 295.6f, 734.4f),
                        PathNode.QuadTo(286.8f, 727.9f, 286.8f, 713.9f),
                        PathNode.VerticalTo(684.0f),
                        PathNode.QuadTo(286.8f, 670.2f, 295.6f, 663.6f),
                        PathNode.QuadTo(304.3f, 657.0f, 316.5f, 657.0f),
                        PathNode.HorizontalTo(416.8f),
                        PathNode.QuadTo(430.7f, 657.0f, 438.1f, 664.9f),
                        PathNode.QuadTo(445.6f, 672.7f, 445.6f, 685.8f),
                        PathNode.Close,
                        PathNode.MoveTo(445.6f, 541.7f),
                        PathNode.VerticalTo(568.0f),
                        PathNode.QuadTo(445.6f, 581.9f, 438.1f, 589.4f),
                        PathNode.QuadTo(430.7f, 596.9f, 416.8f, 596.9f),
                        PathNode.HorizontalTo(316.5f),
                        PathNode.QuadTo(304.3f, 596.9f, 295.6f, 590.3f),
                        PathNode.QuadTo(286.8f, 583.7f, 286.8f, 569.9f),
                        PathNode.VerticalTo(539.9f),
                        PathNode.QuadTo(286.8f, 526.0f, 295.6f, 519.4f),
                        PathNode.QuadTo(304.3f, 512.8f, 316.5f, 512.8f),
                        PathNode.HorizontalTo(416.8f),
                        PathNode.QuadTo(430.7f, 512.8f, 438.1f, 520.8f),
                        PathNode.QuadTo(445.6f, 528.7f, 445.6f, 541.7f),
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
