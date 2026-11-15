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

val MiuixIcons.Glass.Ai: ImageVector
    get() = MiuixIcons.Glass.Regular.Ai

val MiuixIcons.Glass.Light.Ai: ImageVector
    get() {
        if (_aiSurfaceLight != null) return _aiSurfaceLight!!
        _aiSurfaceLight = buildHyperOSIcon(
            name = "Ai.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(276f, -18f),
                PathNode.LineTo(363.7f, 224.3f),
                PathNode.HorizontalTo(715.3f),
                PathNode.LineTo(802.4f, -16.3f),
                PathNode.QuadTo(805.1f, -24.2f, 811.1f, -28.2f),
                PathNode.QuadTo(817.1f, -32.1f, 824.4f, -32.1f),
                PathNode.HorizontalTo(882.3f),
                PathNode.QuadTo(891.8f, -32.1f, 895.7f, -25.6f),
                PathNode.QuadTo(899.6f, -19f, 895.9f, -10.1f),
                PathNode.LineTo(602.6f, 788.3f),
                PathNode.QuadTo(599.9f, 796.8f, 594.7f, 800.2f),
                PathNode.QuadTo(589.6f, 803.6f, 581.1f, 803.6f),
                PathNode.HorizontalTo(498.9f),
                PathNode.QuadTo(489.9f, 803.6f, 485f, 799.9f),
                PathNode.QuadTo(480.1f, 796.2f, 476.9f, 787.8f),
                PathNode.LineTo(183f, -13.4f),
                PathNode.QuadTo(180.4f, -20.1f, 184.6f, -26.1f),
                PathNode.QuadTo(188.8f, -32.1f, 198.2f, -32.1f),
                PathNode.HorizontalTo(254f),
                PathNode.QuadTo(270.8f, -32.1f, 276f, -18f),
                PathNode.Close,
                PathNode.MoveTo(540f, 706.1f),
                PathNode.LineTo(681.9f, 316.2f),
                PathNode.HorizontalTo(397.1f),
                PathNode.Close,
                PathNode.MoveTo(1075.8f, 799.1f),
                PathNode.HorizontalTo(1023.3f),
                PathNode.QuadTo(1013.3f, 799.1f, 1007.8f, 793.6f),
                PathNode.QuadTo(1002.2f, 788f, 1002.2f, 778f),
                PathNode.VerticalTo(-11f),
                PathNode.QuadTo(1002.2f, -21f, 1007.8f, -26.6f),
                PathNode.QuadTo(1013.3f, -32.1f, 1023.3f, -32.1f),
                PathNode.HorizontalTo(1075.8f),
                PathNode.QuadTo(1085.8f, -32.1f, 1091.3f, -26.6f),
                PathNode.QuadTo(1096.9f, -21f, 1096.9f, -11f),
                PathNode.VerticalTo(778f),
                PathNode.QuadTo(1096.9f, 788f, 1091.3f, 793.6f),
                PathNode.QuadTo(1085.8f, 799.1f, 1075.8f, 799.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 27.208f,
            translationY = 1052.3889f,
            autoMirror = false,
        )
        return _aiSurfaceLight!!
    }

private var _aiSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Ai: ImageVector
    get() {
        if (_aiSurfaceNormal != null) return _aiSurfaceNormal!!
        _aiSurfaceNormal = buildHyperOSIcon(
            name = "Ai.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(284.2f, -20.7f),
                PathNode.LineTo(370.1f, 215.2f),
                PathNode.HorizontalTo(708.9f),
                PathNode.LineTo(793.9f, -18.2f),
                PathNode.QuadTo(797.5f, -28.2f, 804.8f, -33.2f),
                PathNode.QuadTo(812.2f, -38.2f, 821.4f, -38.2f),
                PathNode.HorizontalTo(889.7f),
                PathNode.QuadTo(901.6f, -38.2f, 906.2f, -30f),
                PathNode.QuadTo(910.9f, -21.7f, 906.3f, -10.7f),
                PathNode.LineTo(613.9f, 787.4f),
                PathNode.QuadTo(610.3f, 798.3f, 604.2f, 802.5f),
                PathNode.QuadTo(598.1f, 806.6f, 587.2f, 806.6f),
                PathNode.HorizontalTo(492.8f),
                PathNode.QuadTo(481f, 806.6f, 475.4f, 802f),
                PathNode.QuadTo(469.7f, 797.4f, 465.3f, 786.6f),
                PathNode.LineTo(172f, -15.9f),
                PathNode.QuadTo(169.1f, -23.5f, 174.2f, -30.8f),
                PathNode.QuadTo(179.3f, -38.2f, 191.2f, -38.2f),
                PathNode.HorizontalTo(256.7f),
                PathNode.QuadTo(277.8f, -38.2f, 284.2f, -20.7f),
                PathNode.Close,
                PathNode.MoveTo(540f, 679.2f),
                PathNode.LineTo(667.5f, 328.4f),
                PathNode.HorizontalTo(411.5f),
                PathNode.Close,
                PathNode.MoveTo(1080.1f, 805.2f),
                PathNode.HorizontalTo(1019.7f),
                PathNode.QuadTo(1006.9f, 805.2f, 999.7f, 798f),
                PathNode.QuadTo(992.4f, 790.7f, 992.4f, 778f),
                PathNode.VerticalTo(-11f),
                PathNode.QuadTo(992.4f, -23.7f, 999.7f, -31f),
                PathNode.QuadTo(1006.9f, -38.2f, 1019.7f, -38.2f),
                PathNode.HorizontalTo(1080.1f),
                PathNode.QuadTo(1092.8f, -38.2f, 1100f, -31f),
                PathNode.QuadTo(1107.3f, -23.7f, 1107.3f, -11f),
                PathNode.VerticalTo(778f),
                PathNode.QuadTo(1107.3f, 790.7f, 1100f, 798f),
                PathNode.QuadTo(1092.8f, 805.2f, 1080.1f, 805.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 27.5429f,
            translationY = 1050.8612f,
            autoMirror = false,
        )
        return _aiSurfaceNormal!!
    }

private var _aiSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Ai: ImageVector
    get() {
        if (_aiSurfaceRegular != null) return _aiSurfaceRegular!!
        _aiSurfaceRegular = buildHyperOSIcon(
            name = "Ai.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(288f, -22f),
                PathNode.LineTo(373f, 211f),
                PathNode.HorizontalTo(706f),
                PathNode.LineTo(790f, -19f),
                PathNode.QuadTo(794f, -30f, 802f, -35.5f),
                PathNode.QuadTo(810f, -41f, 820f, -41f),
                PathNode.HorizontalTo(893f),
                PathNode.QuadTo(906f, -41f, 911f, -32f),
                PathNode.QuadTo(916f, -23f, 911f, -11f),
                PathNode.LineTo(619f, 787f),
                PathNode.QuadTo(615f, 799f, 608.5f, 803.5f),
                PathNode.QuadTo(602f, 808f, 590f, 808f),
                PathNode.HorizontalTo(490f),
                PathNode.QuadTo(477f, 808f, 471f, 803f),
                PathNode.QuadTo(465f, 798f, 460f, 786f),
                PathNode.LineTo(167f, -17f),
                PathNode.QuadTo(164f, -25f, 169.5f, -33f),
                PathNode.QuadTo(175f, -41f, 188f, -41f),
                PathNode.HorizontalTo(258f),
                PathNode.QuadTo(281f, -41f, 288f, -22f),
                PathNode.Close,
                PathNode.MoveTo(540f, 667f),
                PathNode.LineTo(661f, 334f),
                PathNode.HorizontalTo(418f),
                PathNode.Close,
                PathNode.MoveTo(1082f, 808f),
                PathNode.HorizontalTo(1018f),
                PathNode.QuadTo(1004f, 808f, 996f, 800f),
                PathNode.QuadTo(988f, 792f, 988f, 778f),
                PathNode.VerticalTo(-11f),
                PathNode.QuadTo(988f, -25f, 996f, -33f),
                PathNode.QuadTo(1004f, -41f, 1018f, -41f),
                PathNode.HorizontalTo(1082f),
                PathNode.QuadTo(1096f, -41f, 1104f, -33f),
                PathNode.QuadTo(1112f, -25f, 1112f, -11f),
                PathNode.VerticalTo(778f),
                PathNode.QuadTo(1112f, 792f, 1104f, 800f),
                PathNode.QuadTo(1096f, 808f, 1082f, 808f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 27.6961f,
            translationY = 1050.1667f,
            autoMirror = false,
        )
        return _aiSurfaceRegular!!
    }

private var _aiSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Ai: ImageVector
    get() {
        if (_aiSurfaceMedium != null) return _aiSurfaceMedium!!
        _aiSurfaceMedium = buildHyperOSIcon(
            name = "Ai.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(290.7f, -23.9f),
                PathNode.LineTo(374.4f, 205.9f),
                PathNode.HorizontalTo(700.3f),
                PathNode.LineTo(783f, -20.6f),
                PathNode.QuadTo(787.2f, -33f, 796.6f, -39.6f),
                PathNode.QuadTo(805.9f, -46.1f, 817.8f, -46.1f),
                PathNode.HorizontalTo(890.8f),
                PathNode.QuadTo(906.5f, -46.1f, 913f, -35.1f),
                PathNode.QuadTo(919.5f, -24.1f, 913.7f, -9.1f),
                PathNode.LineTo(621.7f, 788.9f),
                PathNode.QuadTo(616.9f, 802.5f, 609.2f, 807.8f),
                PathNode.QuadTo(601.5f, 813.1f, 587.8f, 813.1f),
                PathNode.HorizontalTo(487.8f),
                PathNode.QuadTo(473.2f, 813.1f, 465.7f, 807.2f),
                PathNode.QuadTo(458.2f, 801.2f, 453f, 787.9f),
                PathNode.LineTo(160f, -15.1f),
                PathNode.QuadTo(155.9f, -25.8f, 162.9f, -36f),
                PathNode.QuadTo(169.9f, -46.1f, 185.8f, -46.1f),
                PathNode.HorizontalTo(255.8f),
                PathNode.QuadTo(282.4f, -46.1f, 290.7f, -23.9f),
                PathNode.Close,
                PathNode.MoveTo(537.8f, 654.3f),
                PathNode.LineTo(651.5f, 339.1f),
                PathNode.HorizontalTo(423.1f),
                PathNode.Close,
                PathNode.MoveTo(1087.1f, 813.1f),
                PathNode.HorizontalTo(1018.3f),
                PathNode.QuadTo(1002.9f, 813.1f, 994.2f, 804.6f),
                PathNode.QuadTo(985.6f, 796.1f, 985.6f, 780.4f),
                PathNode.VerticalTo(-13.4f),
                PathNode.QuadTo(985.6f, -29.1f, 994.2f, -37.6f),
                PathNode.QuadTo(1002.9f, -46.1f, 1018.3f, -46.1f),
                PathNode.HorizontalTo(1087.1f),
                PathNode.QuadTo(1102.8f, -46.1f, 1111.3f, -37.6f),
                PathNode.QuadTo(1119.8f, -29.1f, 1119.8f, -13.4f),
                PathNode.VerticalTo(780.4f),
                PathNode.QuadTo(1119.8f, 796.1f, 1111.3f, 804.6f),
                PathNode.QuadTo(1102.8f, 813.1f, 1087.1f, 813.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 27.5137f,
            translationY = 1050.1667f,
            autoMirror = false,
        )
        return _aiSurfaceMedium!!
    }

private var _aiSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Ai: ImageVector
    get() {
        if (_aiSurfaceDemibold != null) return _aiSurfaceDemibold!!
        _aiSurfaceDemibold = buildHyperOSIcon(
            name = "Ai.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(292.6f, -25.2f),
                PathNode.LineTo(375.3f, 202.3f),
                PathNode.HorizontalTo(696.4f),
                PathNode.LineTo(778.1f, -21.8f),
                PathNode.QuadTo(782.5f, -35.1f, 792.8f, -42.4f),
                PathNode.QuadTo(803.1f, -49.7f, 816.3f, -49.7f),
                PathNode.HorizontalTo(889.3f),
                PathNode.QuadTo(906.9f, -49.7f, 914.4f, -37.3f),
                PathNode.QuadTo(922f, -24.8f, 915.6f, -7.8f),
                PathNode.LineTo(623.6f, 790.2f),
                PathNode.QuadTo(618.2f, 805f, 609.6f, 810.9f),
                PathNode.QuadTo(601.1f, 816.7f, 586.3f, 816.7f),
                PathNode.HorizontalTo(486.3f),
                PathNode.QuadTo(470.6f, 816.7f, 462f, 810.1f),
                PathNode.QuadTo(453.5f, 803.5f, 448.1f, 789.2f),
                PathNode.LineTo(155.1f, -13.8f),
                PathNode.QuadTo(150.2f, -26.4f, 158.2f, -38.1f),
                PathNode.QuadTo(166.3f, -49.7f, 184.3f, -49.7f),
                PathNode.HorizontalTo(254.3f),
                PathNode.QuadTo(283.3f, -49.7f, 292.6f, -25.2f),
                PathNode.Close,
                PathNode.MoveTo(536.3f, 645.4f),
                PathNode.LineTo(644.9f, 342.7f),
                PathNode.HorizontalTo(426.7f),
                PathNode.Close,
                PathNode.MoveTo(1090.7f, 816.7f),
                PathNode.HorizontalTo(1018.5f),
                PathNode.QuadTo(1002.2f, 816.7f, 993f, 807.8f),
                PathNode.QuadTo(983.9f, 798.9f, 983.9f, 782.1f),
                PathNode.VerticalTo(-15.1f),
                PathNode.QuadTo(983.9f, -31.9f, 993f, -40.8f),
                PathNode.QuadTo(1002.2f, -49.7f, 1018.5f, -49.7f),
                PathNode.HorizontalTo(1090.7f),
                PathNode.QuadTo(1107.5f, -49.7f, 1116.4f, -40.8f),
                PathNode.QuadTo(1125.3f, -31.9f, 1125.3f, -15.1f),
                PathNode.VerticalTo(782.1f),
                PathNode.QuadTo(1125.3f, 798.9f, 1116.4f, 807.8f),
                PathNode.QuadTo(1107.5f, 816.7f, 1090.7f, 816.7f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 27.3871f,
            translationY = 1050.1667f,
            autoMirror = false,
        )
        return _aiSurfaceDemibold!!
    }

private var _aiSurfaceDemibold: ImageVector? = null

