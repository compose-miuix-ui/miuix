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

val MiuixIcons.Stopwatch: ImageVector
    get() = MiuixIcons.Regular.Stopwatch

val MiuixIcons.Light.Stopwatch: ImageVector
    get() {
        if (_stopwatchLight != null) return _stopwatchLight!!
        _stopwatchLight = ImageVector.Builder(
            name = "Stopwatch.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1292.3999999999999f,
            viewportHeight = 1292.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -18.800000000000068f, translationY = 1007.6999999999999f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1139.0f, 297.0f),
                        PathNode.QuadTo(1139.0f, 425.0f, 1075.5f, 534.0f),
                        PathNode.QuadTo(1012.0f, 643.0f, 903.0f, 707.0f),
                        PathNode.QuadTo(794.0f, 771.0f, 665.0f, 771.0f),
                        PathNode.QuadTo(536.0f, 771.0f, 427.0f, 707.0f),
                        PathNode.QuadTo(318.0f, 643.0f, 254.5f, 534.0f),
                        PathNode.QuadTo(191.0f, 425.0f, 191.0f, 297.0f),
                        PathNode.QuadTo(191.0f, 169.0f, 254.5f, 60.0f),
                        PathNode.QuadTo(318.0f, -49.0f, 427.0f, -113.0f),
                        PathNode.QuadTo(536.0f, -177.0f, 665.0f, -177.0f),
                        PathNode.QuadTo(794.0f, -177.0f, 903.0f, -113.0f),
                        PathNode.QuadTo(1012.0f, -49.0f, 1075.5f, 60.0f),
                        PathNode.QuadTo(1139.0f, 169.0f, 1139.0f, 297.0f),
                        PathNode.Close,
                        PathNode.MoveTo(641.0f, 260.0f),
                        PathNode.VerticalTo(613.0f),
                        PathNode.QuadTo(641.0f, 621.0f, 645.0f, 625.5f),
                        PathNode.QuadTo(649.0f, 630.0f, 658.0f, 630.0f),
                        PathNode.HorizontalTo(672.0f),
                        PathNode.QuadTo(682.0f, 630.0f, 685.5f, 626.0f),
                        PathNode.QuadTo(689.0f, 622.0f, 689.0f, 613.0f),
                        PathNode.VerticalTo(260.0f),
                        PathNode.QuadTo(689.0f, 252.0f, 684.5f, 248.0f),
                        PathNode.QuadTo(680.0f, 244.0f, 672.0f, 244.0f),
                        PathNode.HorizontalTo(658.0f),
                        PathNode.QuadTo(641.0f, 244.0f, 641.0f, 260.0f),
                        PathNode.Close,
                        PathNode.MoveTo(834.0f, 849.0f),
                        PathNode.VerticalTo(870.0f),
                        PathNode.QuadTo(834.0f, 886.0f, 826.5f, 893.0f),
                        PathNode.QuadTo(819.0f, 900.0f, 805.0f, 900.0f),
                        PathNode.HorizontalTo(528.0f),
                        PathNode.QuadTo(498.0f, 900.0f, 498.0f, 871.0f),
                        PathNode.VerticalTo(849.0f),
                        PathNode.QuadTo(498.0f, 833.0f, 505.5f, 826.5f),
                        PathNode.QuadTo(513.0f, 820.0f, 528.0f, 820.0f),
                        PathNode.HorizontalTo(805.0f),
                        PathNode.QuadTo(819.0f, 820.0f, 826.5f, 826.5f),
                        PathNode.QuadTo(834.0f, 833.0f, 834.0f, 849.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchLight!!
    }

private var _stopwatchLight: ImageVector? = null

val MiuixIcons.Regular.Stopwatch: ImageVector
    get() {
        if (_stopwatchRegular != null) return _stopwatchRegular!!
        _stopwatchRegular = ImageVector.Builder(
            name = "Stopwatch.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1308.0f,
            viewportHeight = 1308.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -11.0f, translationY = 1029.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1139.0f, 303.0f),
                        PathNode.QuadTo(1139.0f, 432.0f, 1075.5f, 541.0f),
                        PathNode.QuadTo(1012.0f, 650.0f, 903.0f, 713.5f),
                        PathNode.QuadTo(794.0f, 777.0f, 665.0f, 777.0f),
                        PathNode.QuadTo(536.0f, 777.0f, 427.0f, 713.5f),
                        PathNode.QuadTo(318.0f, 650.0f, 254.5f, 541.0f),
                        PathNode.QuadTo(191.0f, 432.0f, 191.0f, 303.0f),
                        PathNode.QuadTo(191.0f, 175.0f, 254.5f, 66.0f),
                        PathNode.QuadTo(318.0f, -43.0f, 427.0f, -106.5f),
                        PathNode.QuadTo(536.0f, -170.0f, 665.0f, -170.0f),
                        PathNode.QuadTo(794.0f, -170.0f, 903.0f, -106.5f),
                        PathNode.QuadTo(1012.0f, -43.0f, 1075.5f, 66.0f),
                        PathNode.QuadTo(1139.0f, 175.0f, 1139.0f, 303.0f),
                        PathNode.Close,
                        PathNode.MoveTo(632.0f, 272.0f),
                        PathNode.VerticalTo(600.0f),
                        PathNode.QuadTo(632.0f, 613.0f, 637.5f, 619.0f),
                        PathNode.QuadTo(643.0f, 625.0f, 657.0f, 625.0f),
                        PathNode.HorizontalTo(673.0f),
                        PathNode.QuadTo(688.0f, 625.0f, 693.0f, 619.0f),
                        PathNode.QuadTo(698.0f, 613.0f, 698.0f, 600.0f),
                        PathNode.VerticalTo(272.0f),
                        PathNode.QuadTo(698.0f, 261.0f, 691.0f, 255.0f),
                        PathNode.QuadTo(684.0f, 249.0f, 673.0f, 249.0f),
                        PathNode.HorizontalTo(657.0f),
                        PathNode.QuadTo(632.0f, 249.0f, 632.0f, 272.0f),
                        PathNode.Close,
                        PathNode.MoveTo(838.0f, 860.0f),
                        PathNode.VerticalTo(884.0f),
                        PathNode.QuadTo(838.0f, 905.0f, 829.0f, 912.5f),
                        PathNode.QuadTo(820.0f, 920.0f, 802.0f, 920.0f),
                        PathNode.HorizontalTo(528.0f),
                        PathNode.QuadTo(492.0f, 920.0f, 492.0f, 884.0f),
                        PathNode.VerticalTo(860.0f),
                        PathNode.QuadTo(492.0f, 838.0f, 500.5f, 831.5f),
                        PathNode.QuadTo(509.0f, 825.0f, 528.0f, 825.0f),
                        PathNode.HorizontalTo(802.0f),
                        PathNode.QuadTo(820.0f, 825.0f, 829.0f, 832.0f),
                        PathNode.QuadTo(838.0f, 839.0f, 838.0f, 860.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchRegular!!
    }

private var _stopwatchRegular: ImageVector? = null

val MiuixIcons.Heavy.Stopwatch: ImageVector
    get() {
        if (_stopwatchHeavy != null) return _stopwatchHeavy!!
        _stopwatchHeavy = ImageVector.Builder(
            name = "Stopwatch.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1364.3999999999999f,
            viewportHeight = 1364.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 17.199999999999932f, translationY = 1056.6999999999998f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1150.0f, 290.0f),
                        PathNode.QuadTo(1150.0f, 422.0f, 1085.0f, 533.5f),
                        PathNode.QuadTo(1020.0f, 645.0f, 908.4810924369748f, 710.5f),
                        PathNode.QuadTo(796.9621848739496f, 776.0f, 664.9810924369748f, 776.0f),
                        PathNode.QuadTo(533.0f, 776.0f, 421.5f, 710.5f),
                        PathNode.QuadTo(310.0f, 645.0f, 245.0f, 533.4757894736842f),
                        PathNode.QuadTo(180.0f, 421.95157894736843f, 180.0f, 289.9642105263158f),
                        PathNode.QuadTo(180.0f, 159.0f, 244.9742951907131f, 47.472463768115944f),
                        PathNode.QuadTo(309.9485903814262f, -64.05507246376811f, 421.4792703150912f, -129.02753623188406f),
                        PathNode.QuadTo(533.0099502487562f, -194.0f, 665.0049751243781f, -194.0f),
                        PathNode.QuadTo(797.0f, -194.0f, 908.5275362318841f, -129.02325581395348f),
                        PathNode.QuadTo(1020.0550724637682f, -64.04651162790698f, 1085.0275362318841f, 47.48837209302325f),
                        PathNode.QuadTo(1150.0f, 159.02325581395348f, 1150.0f, 290.0f),
                        PathNode.Close,
                        PathNode.MoveTo(618.0f, 283.0f),
                        PathNode.VerticalTo(583.0f),
                        PathNode.QuadTo(618.0f, 599.0f, 626.0f, 607.0f),
                        PathNode.QuadTo(634.0f, 615.0f, 650.0f, 615.0f),
                        PathNode.HorizontalTo(680.0f),
                        PathNode.QuadTo(697.0f, 615.0f, 704.5f, 607.0f),
                        PathNode.QuadTo(712.0f, 599.0f, 712.0f, 583.0f),
                        PathNode.VerticalTo(283.0f),
                        PathNode.QuadTo(712.0f, 268.17391304347825f, 703.5f, 260.0869565217391f),
                        PathNode.QuadTo(695.0f, 252.0f, 680.0f, 252.0f),
                        PathNode.HorizontalTo(650.0f),
                        PathNode.QuadTo(618.0f, 252.0f, 618.0f, 283.0f),
                        PathNode.Close,
                        PathNode.MoveTo(840.0f, 868.0f),
                        PathNode.VerticalTo(904.0f),
                        PathNode.QuadTo(840.0f, 925.0f, 830.5f, 934.0f),
                        PathNode.QuadTo(821.0f, 943.0f, 802.0f, 943.0f),
                        PathNode.HorizontalTo(528.0f),
                        PathNode.QuadTo(490.0f, 943.0f, 490.0f, 905.0f),
                        PathNode.VerticalTo(868.0f),
                        PathNode.QuadTo(490.0f, 848.0f, 498.97222222222223f, 839.5f),
                        PathNode.QuadTo(507.94444444444446f, 831.0f, 528.0f, 831.0f),
                        PathNode.HorizontalTo(802.0f),
                        PathNode.QuadTo(821.0f, 831.0f, 830.5f, 839.5f),
                        PathNode.QuadTo(840.0f, 848.0f, 840.0f, 868.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _stopwatchHeavy!!
    }

private var _stopwatchHeavy: ImageVector? = null
