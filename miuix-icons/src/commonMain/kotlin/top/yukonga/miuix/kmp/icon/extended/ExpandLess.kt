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

val MiuixIcons.ExpandLess: ImageVector
    get() = MiuixIcons.Regular.ExpandLess

val MiuixIcons.Light.ExpandLess: ImageVector
    get() {
        if (_expandlessLight != null) return _expandlessLight!!
        _expandlessLight = ImageVector.Builder(
            name = "ExpandLess.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1122.0f,
            viewportHeight = 1122.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1122.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(725.5f, 111.0f),
                        PathNode.VerticalTo(391.0f),
                        PathNode.QuadTo(725.5f, 394.0f, 726.8f, 395.5f),
                        PathNode.QuadTo(728.0f, 397.0f, 730.5f, 397.0f),
                        PathNode.HorizontalTo(1010.5f),
                        PathNode.QuadTo(1018.1f, 397.0f, 1023.3f, 402.3f),
                        PathNode.QuadTo(1028.5f, 407.5f, 1028.5f, 415.3f),
                        PathNode.VerticalTo(441.3f),
                        PathNode.QuadTo(1028.5f, 449.0f, 1023.3f, 454.0f),
                        PathNode.QuadTo(1018.1f, 459.0f, 1010.5f, 459.0f),
                        PathNode.HorizontalTo(703.5f),
                        PathNode.QuadTo(684.2f, 459.0f, 673.8f, 448.7f),
                        PathNode.QuadTo(663.5f, 438.3f, 663.5f, 419.0f),
                        PathNode.VerticalTo(111.0f),
                        PathNode.QuadTo(663.5f, 103.8f, 668.9f, 98.9f),
                        PathNode.QuadTo(674.4f, 94.0f, 681.9f, 94.0f),
                        PathNode.HorizontalTo(707.0f),
                        PathNode.QuadTo(714.5f, 94.0f, 720.0f, 98.9f),
                        PathNode.QuadTo(725.5f, 103.8f, 725.5f, 111.0f),
                        PathNode.Close,
                        PathNode.MoveTo(458.5f, 704.0f),
                        PathNode.VerticalTo(1011.0f),
                        PathNode.QuadTo(458.5f, 1018.2f, 453.6f, 1023.1f),
                        PathNode.QuadTo(448.7f, 1028.0f, 441.5f, 1028.0f),
                        PathNode.HorizontalTo(415.3f),
                        PathNode.QuadTo(407.5f, 1028.0f, 402.5f, 1023.1f),
                        PathNode.QuadTo(397.5f, 1018.2f, 397.5f, 1011.0f),
                        PathNode.VerticalTo(732.0f),
                        PathNode.QuadTo(397.5f, 726.0f, 391.5f, 726.0f),
                        PathNode.HorizontalTo(112.5f),
                        PathNode.QuadTo(104.8f, 726.0f, 99.1f, 721.0f),
                        PathNode.QuadTo(93.5f, 716.0f, 93.5f, 708.2f),
                        PathNode.VerticalTo(681.8f),
                        PathNode.QuadTo(93.5f, 674.0f, 99.1f, 669.0f),
                        PathNode.QuadTo(104.8f, 664.0f, 112.5f, 664.0f),
                        PathNode.HorizontalTo(418.5f),
                        PathNode.QuadTo(437.8f, 664.0f, 448.2f, 674.3f),
                        PathNode.QuadTo(458.5f, 684.7f, 458.5f, 704.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandlessLight!!
    }

private var _expandlessLight: ImageVector? = null

val MiuixIcons.Normal.ExpandLess: ImageVector
    get() {
        if (_expandlessNormal != null) return _expandlessNormal!!
        _expandlessNormal = ImageVector.Builder(
            name = "ExpandLess.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1122.0f,
            viewportHeight = 1122.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1122.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(735.1f, 116.8f),
                        PathNode.VerticalTo(382.4f),
                        PathNode.QuadTo(735.1f, 384.7f, 736.2f, 386.2f),
                        PathNode.QuadTo(737.3f, 387.7f, 739.4f, 387.7f),
                        PathNode.HorizontalTo(1005.0f),
                        PathNode.QuadTo(1014.9f, 387.7f, 1021.7f, 394.5f),
                        PathNode.QuadTo(1028.5f, 401.3f, 1028.5f, 411.3f),
                        PathNode.VerticalTo(444.9f),
                        PathNode.QuadTo(1028.5f, 454.8f, 1021.7f, 461.9f),
                        PathNode.QuadTo(1014.9f, 469.0f, 1005.0f, 469.0f),
                        PathNode.HorizontalTo(706.9f),
                        PathNode.QuadTo(681.0f, 469.0f, 667.1f, 455.1f),
                        PathNode.QuadTo(653.2f, 441.2f, 653.2f, 415.2f),
                        PathNode.VerticalTo(116.8f),
                        PathNode.QuadTo(653.2f, 107.0f, 660.4f, 100.3f),
                        PathNode.QuadTo(667.6f, 93.7f, 677.5f, 93.7f),
                        PathNode.HorizontalTo(710.8f),
                        PathNode.QuadTo(720.7f, 93.7f, 727.9f, 100.3f),
                        PathNode.QuadTo(735.1f, 107.0f, 735.1f, 116.8f),
                        PathNode.Close,
                        PathNode.MoveTo(468.8f, 707.8f),
                        PathNode.VerticalTo(1005.2f),
                        PathNode.QuadTo(468.8f, 1015.0f, 462.1f, 1021.7f),
                        PathNode.QuadTo(455.4f, 1028.3f, 445.6f, 1028.3f),
                        PathNode.HorizontalTo(412.0f),
                        PathNode.QuadTo(402.0f, 1028.3f, 394.9f, 1021.7f),
                        PathNode.QuadTo(387.9f, 1015.0f, 387.9f, 1005.2f),
                        PathNode.VerticalTo(740.6f),
                        PathNode.QuadTo(387.9f, 735.3f, 382.6f, 735.3f),
                        PathNode.HorizontalTo(118.0f),
                        PathNode.QuadTo(108.0f, 735.3f, 100.8f, 728.2f),
                        PathNode.QuadTo(93.5f, 721.2f, 93.5f, 711.1f),
                        PathNode.VerticalTo(677.5f),
                        PathNode.QuadTo(93.5f, 667.5f, 100.8f, 660.8f),
                        PathNode.QuadTo(108.0f, 654.0f, 118.0f, 654.0f),
                        PathNode.HorizontalTo(415.1f),
                        PathNode.QuadTo(441.0f, 654.0f, 454.9f, 667.9f),
                        PathNode.QuadTo(468.8f, 681.8f, 468.8f, 707.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandlessNormal!!
    }

private var _expandlessNormal: ImageVector? = null

val MiuixIcons.Regular.ExpandLess: ImageVector
    get() {
        if (_expandlessRegular != null) return _expandlessRegular!!
        _expandlessRegular = ImageVector.Builder(
            name = "ExpandLess.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1122.0f,
            viewportHeight = 1122.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1122.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(739.5f, 119.5f),
                        PathNode.VerticalTo(378.5f),
                        PathNode.QuadTo(739.5f, 380.5f, 740.5f, 382.0f),
                        PathNode.QuadTo(741.5f, 383.5f, 743.5f, 383.5f),
                        PathNode.HorizontalTo(1002.5f),
                        PathNode.QuadTo(1013.5f, 383.5f, 1021.0f, 391.0f),
                        PathNode.QuadTo(1028.5f, 398.5f, 1028.5f, 409.5f),
                        PathNode.VerticalTo(446.5f),
                        PathNode.QuadTo(1028.5f, 457.5f, 1021.0f, 465.5f),
                        PathNode.QuadTo(1013.5f, 473.5f, 1002.5f, 473.5f),
                        PathNode.HorizontalTo(708.5f),
                        PathNode.QuadTo(679.5f, 473.5f, 664.0f, 458.0f),
                        PathNode.QuadTo(648.5f, 442.5f, 648.5f, 413.5f),
                        PathNode.VerticalTo(119.5f),
                        PathNode.QuadTo(648.5f, 108.5f, 656.5f, 101.0f),
                        PathNode.QuadTo(664.5f, 93.5f, 675.5f, 93.5f),
                        PathNode.HorizontalTo(712.5f),
                        PathNode.QuadTo(723.5f, 93.5f, 731.5f, 101.0f),
                        PathNode.QuadTo(739.5f, 108.5f, 739.5f, 119.5f),
                        PathNode.Close,
                        PathNode.MoveTo(473.5f, 709.5f),
                        PathNode.VerticalTo(1002.5f),
                        PathNode.QuadTo(473.5f, 1013.5f, 466.0f, 1021.0f),
                        PathNode.QuadTo(458.5f, 1028.5f, 447.5f, 1028.5f),
                        PathNode.HorizontalTo(410.5f),
                        PathNode.QuadTo(399.5f, 1028.5f, 391.5f, 1021.0f),
                        PathNode.QuadTo(383.5f, 1013.5f, 383.5f, 1002.5f),
                        PathNode.VerticalTo(744.5f),
                        PathNode.QuadTo(383.5f, 739.5f, 378.5f, 739.5f),
                        PathNode.HorizontalTo(120.5f),
                        PathNode.QuadTo(109.5f, 739.5f, 101.5f, 731.5f),
                        PathNode.QuadTo(93.5f, 723.5f, 93.5f, 712.5f),
                        PathNode.VerticalTo(675.5f),
                        PathNode.QuadTo(93.5f, 664.5f, 101.5f, 657.0f),
                        PathNode.QuadTo(109.5f, 649.5f, 120.5f, 649.5f),
                        PathNode.HorizontalTo(413.5f),
                        PathNode.QuadTo(442.5f, 649.5f, 458.0f, 665.0f),
                        PathNode.QuadTo(473.5f, 680.5f, 473.5f, 709.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandlessRegular!!
    }

private var _expandlessRegular: ImageVector? = null

val MiuixIcons.Medium.ExpandLess: ImageVector
    get() {
        if (_expandlessMedium != null) return _expandlessMedium!!
        _expandlessMedium = ImageVector.Builder(
            name = "ExpandLess.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1140.4f,
            viewportHeight = 1140.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1140.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(756.3f, 128.7f),
                        PathNode.VerticalTo(379.4f),
                        PathNode.QuadTo(756.3f, 381.4f, 757.6f, 383.2f),
                        PathNode.QuadTo(758.9f, 385.0f, 760.9f, 385.0f),
                        PathNode.HorizontalTo(1011.7f),
                        PathNode.QuadTo(1025.6f, 385.0f, 1035.5f, 394.9f),
                        PathNode.QuadTo(1045.3f, 404.7f, 1045.3f, 418.7f),
                        PathNode.VerticalTo(455.7f),
                        PathNode.QuadTo(1045.3f, 469.6f, 1035.5f, 480.0f),
                        PathNode.QuadTo(1025.6f, 490.3f, 1011.7f, 490.3f),
                        PathNode.HorizontalTo(708.3f),
                        PathNode.QuadTo(681.0f, 490.3f, 665.5f, 474.5f),
                        PathNode.QuadTo(650.0f, 458.7f, 650.0f, 431.5f),
                        PathNode.VerticalTo(128.7f),
                        PathNode.QuadTo(650.0f, 114.7f, 660.4f, 104.9f),
                        PathNode.QuadTo(670.7f, 95.0f, 684.7f, 95.0f),
                        PathNode.HorizontalTo(721.7f),
                        PathNode.QuadTo(735.6f, 95.0f, 746.0f, 104.9f),
                        PathNode.QuadTo(756.3f, 114.7f, 756.3f, 128.7f),
                        PathNode.Close,
                        PathNode.MoveTo(490.3f, 709.3f),
                        PathNode.VerticalTo(1011.7f),
                        PathNode.QuadTo(490.3f, 1025.6f, 480.5f, 1035.5f),
                        PathNode.QuadTo(470.6f, 1045.3f, 456.7f, 1045.3f),
                        PathNode.HorizontalTo(419.7f),
                        PathNode.QuadTo(405.1f, 1045.3f, 395.1f, 1035.5f),
                        PathNode.QuadTo(385.0f, 1025.6f, 385.0f, 1011.7f),
                        PathNode.VerticalTo(761.9f),
                        PathNode.QuadTo(385.0f, 756.3f, 379.4f, 756.3f),
                        PathNode.HorizontalTo(129.7f),
                        PathNode.QuadTo(115.7f, 756.3f, 105.4f, 746.3f),
                        PathNode.QuadTo(95.0f, 736.2f, 95.0f, 721.7f),
                        PathNode.VerticalTo(684.7f),
                        PathNode.QuadTo(95.0f, 670.7f, 105.1f, 660.9f),
                        PathNode.QuadTo(115.1f, 651.0f, 129.7f, 651.0f),
                        PathNode.HorizontalTo(432.1f),
                        PathNode.QuadTo(459.3f, 651.0f, 474.8f, 666.8f),
                        PathNode.QuadTo(490.3f, 682.6f, 490.3f, 709.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandlessMedium!!
    }

private var _expandlessMedium: ImageVector? = null

val MiuixIcons.Demibold.ExpandLess: ImageVector
    get() {
        if (_expandlessDemibold != null) return _expandlessDemibold!!
        _expandlessDemibold = ImageVector.Builder(
            name = "ExpandLess.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1153.2f,
            viewportHeight = 1153.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1153.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(768.1f, 135.1f),
                        PathNode.VerticalTo(380.1f),
                        PathNode.QuadTo(768.1f, 382.1f, 769.6f, 384.1f),
                        PathNode.QuadTo(771.1f, 386.1f, 773.1f, 386.1f),
                        PathNode.HorizontalTo(1018.1f),
                        PathNode.QuadTo(1034.1f, 386.1f, 1045.6f, 397.6f),
                        PathNode.QuadTo(1057.1f, 409.1f, 1057.1f, 425.1f),
                        PathNode.VerticalTo(462.1f),
                        PathNode.QuadTo(1057.1f, 478.1f, 1045.6f, 490.1f),
                        PathNode.QuadTo(1034.1f, 502.1f, 1018.1f, 502.1f),
                        PathNode.HorizontalTo(708.1f),
                        PathNode.QuadTo(682.1f, 502.1f, 666.6f, 486.1f),
                        PathNode.QuadTo(651.1f, 470.1f, 651.1f, 444.1f),
                        PathNode.VerticalTo(135.1f),
                        PathNode.QuadTo(651.1f, 119.1f, 663.1f, 107.6f),
                        PathNode.QuadTo(675.1f, 96.1f, 691.1f, 96.1f),
                        PathNode.HorizontalTo(728.1f),
                        PathNode.QuadTo(744.1f, 96.1f, 756.1f, 107.6f),
                        PathNode.QuadTo(768.1f, 119.1f, 768.1f, 135.1f),
                        PathNode.Close,
                        PathNode.MoveTo(502.1f, 709.1f),
                        PathNode.VerticalTo(1018.1f),
                        PathNode.QuadTo(502.1f, 1034.1f, 490.6f, 1045.6f),
                        PathNode.QuadTo(479.1f, 1057.1f, 463.1f, 1057.1f),
                        PathNode.HorizontalTo(426.1f),
                        PathNode.QuadTo(409.1f, 1057.1f, 397.6f, 1045.6f),
                        PathNode.QuadTo(386.1f, 1034.1f, 386.1f, 1018.1f),
                        PathNode.VerticalTo(774.1f),
                        PathNode.QuadTo(386.1f, 768.1f, 380.1f, 768.1f),
                        PathNode.HorizontalTo(136.1f),
                        PathNode.QuadTo(120.1f, 768.1f, 108.1f, 756.6f),
                        PathNode.QuadTo(96.1f, 745.1f, 96.1f, 728.1f),
                        PathNode.VerticalTo(691.1f),
                        PathNode.QuadTo(96.1f, 675.1f, 107.6f, 663.6f),
                        PathNode.QuadTo(119.1f, 652.1f, 136.1f, 652.1f),
                        PathNode.HorizontalTo(445.1f),
                        PathNode.QuadTo(471.1f, 652.1f, 486.6f, 668.1f),
                        PathNode.QuadTo(502.1f, 684.1f, 502.1f, 709.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandlessDemibold!!
    }

private var _expandlessDemibold: ImageVector? = null
