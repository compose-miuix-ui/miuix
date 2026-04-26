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

val MiuixIcons.ZoomOut: ImageVector
    get() = MiuixIcons.Regular.ZoomOut

val MiuixIcons.Light.ZoomOut: ImageVector
    get() {
        if (_zoomoutLight != null) return _zoomoutLight!!
        _zoomoutLight = ImageVector.Builder(
            name = "ZoomOut.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1117.2f,
            viewportHeight = 1117.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1117.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(485.1f, 675.1f),
                        PathNode.VerticalTo(1004.1f),
                        PathNode.QuadTo(485.1f, 1012.5f, 480.9f, 1017.8f),
                        PathNode.QuadTo(476.6f, 1023.1f, 468.1f, 1023.1f),
                        PathNode.HorizontalTo(442.3f),
                        PathNode.QuadTo(434.1f, 1023.1f, 429.6f, 1017.8f),
                        PathNode.QuadTo(425.1f, 1012.5f, 425.1f, 1004.1f),
                        PathNode.VerticalTo(732.1f),
                        PathNode.LineTo(153.1f, 1004.1f),
                        PathNode.QuadTo(147.8f, 1010.1f, 140.4f, 1011.1f),
                        PathNode.QuadTo(133.1f, 1012.1f, 127.1f, 1005.1f),
                        PathNode.LineTo(112.2f, 990.2f),
                        PathNode.QuadTo(105.1f, 983.1f, 105.6f, 975.6f),
                        PathNode.QuadTo(106.1f, 968.1f, 112.1f, 963.1f),
                        PathNode.LineTo(384.1f, 691.1f),
                        PathNode.HorizontalTo(111.1f),
                        PathNode.QuadTo(102.8f, 691.1f, 97.9f, 686.2f),
                        PathNode.QuadTo(93.1f, 681.3f, 93.1f, 675.0f),
                        PathNode.VerticalTo(647.1f),
                        PathNode.QuadTo(93.1f, 640.1f, 97.9f, 635.6f),
                        PathNode.QuadTo(102.8f, 631.1f, 111.1f, 631.1f),
                        PathNode.HorizontalTo(439.1f),
                        PathNode.QuadTo(463.1f, 631.1f, 474.1f, 641.6f),
                        PathNode.QuadTo(485.1f, 652.1f, 485.1f, 675.1f),
                        PathNode.Close,
                        PathNode.MoveTo(632.1f, 442.1f),
                        PathNode.VerticalTo(113.1f),
                        PathNode.QuadTo(632.1f, 104.7f, 636.4f, 99.4f),
                        PathNode.QuadTo(640.6f, 94.1f, 649.1f, 94.1f),
                        PathNode.HorizontalTo(674.9f),
                        PathNode.QuadTo(683.1f, 94.1f, 687.6f, 99.4f),
                        PathNode.QuadTo(692.1f, 104.7f, 692.1f, 113.1f),
                        PathNode.VerticalTo(385.1f),
                        PathNode.LineTo(964.1f, 112.1f),
                        PathNode.QuadTo(970.1f, 106.1f, 977.1f, 105.1f),
                        PathNode.QuadTo(984.1f, 104.1f, 992.1f, 112.1f),
                        PathNode.LineTo(1006.3f, 126.3f),
                        PathNode.QuadTo(1013.1f, 133.1f, 1012.6f, 140.6f),
                        PathNode.QuadTo(1012.1f, 148.1f, 1006.1f, 153.1f),
                        PathNode.LineTo(733.1f, 426.1f),
                        PathNode.HorizontalTo(1006.1f),
                        PathNode.QuadTo(1014.4f, 426.1f, 1019.3f, 431.0f),
                        PathNode.QuadTo(1024.1f, 435.9f, 1024.1f, 442.2f),
                        PathNode.VerticalTo(470.1f),
                        PathNode.QuadTo(1024.1f, 477.1f, 1019.3f, 481.6f),
                        PathNode.QuadTo(1014.4f, 486.1f, 1006.1f, 486.1f),
                        PathNode.HorizontalTo(678.1f),
                        PathNode.QuadTo(654.1f, 486.1f, 643.1f, 475.6f),
                        PathNode.QuadTo(632.1f, 465.1f, 632.1f, 442.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomoutLight!!
    }

private var _zoomoutLight: ImageVector? = null

val MiuixIcons.Normal.ZoomOut: ImageVector
    get() {
        if (_zoomoutNormal != null) return _zoomoutNormal!!
        _zoomoutNormal = ImageVector.Builder(
            name = "ZoomOut.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1123.8f,
            viewportHeight = 1123.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1123.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(497.3f, 680.5f),
                        PathNode.VerticalTo(1004.6f),
                        PathNode.QuadTo(497.3f, 1015.5f, 491.9f, 1022.3f),
                        PathNode.QuadTo(486.4f, 1029.2f, 475.5f, 1029.2f),
                        PathNode.HorizontalTo(441.3f),
                        PathNode.QuadTo(430.5f, 1029.2f, 425.0f, 1022.3f),
                        PathNode.QuadTo(419.5f, 1015.5f, 419.5f, 1004.6f),
                        PathNode.VerticalTo(757.4f),
                        PathNode.LineTo(164.6f, 1012.2f),
                        PathNode.QuadTo(157.5f, 1020.3f, 147.6f, 1021.6f),
                        PathNode.QuadTo(137.8f, 1023.0f, 129.0f, 1013.9f),
                        PathNode.LineTo(109.9f, 994.8f),
                        PathNode.QuadTo(100.8f, 985.7f, 101.7f, 975.8f),
                        PathNode.QuadTo(102.5f, 965.9f, 109.9f, 958.8f),
                        PathNode.LineTo(365.4f, 703.3f),
                        PathNode.HorizontalTo(117.1f),
                        PathNode.QuadTo(106.3f, 703.3f, 100.0f, 697.0f),
                        PathNode.QuadTo(93.6f, 690.7f, 93.6f, 682.5f),
                        PathNode.VerticalTo(646.3f),
                        PathNode.QuadTo(93.6f, 637.2f, 100.0f, 631.3f),
                        PathNode.QuadTo(106.3f, 625.5f, 117.1f, 625.5f),
                        PathNode.HorizontalTo(441.7f),
                        PathNode.QuadTo(469.1f, 625.5f, 483.2f, 639.4f),
                        PathNode.QuadTo(497.3f, 653.3f, 497.3f, 680.5f),
                        PathNode.Close,
                        PathNode.MoveTo(626.5f, 443.3f),
                        PathNode.VerticalTo(119.1f),
                        PathNode.QuadTo(626.5f, 108.3f, 631.9f, 101.5f),
                        PathNode.QuadTo(637.4f, 94.6f, 648.3f, 94.6f),
                        PathNode.HorizontalTo(682.5f),
                        PathNode.QuadTo(693.3f, 94.6f, 698.8f, 101.5f),
                        PathNode.QuadTo(704.3f, 108.3f, 704.3f, 119.1f),
                        PathNode.VerticalTo(366.4f),
                        PathNode.LineTo(959.1f, 111.3f),
                        PathNode.QuadTo(966.5f, 103.2f, 976.3f, 101.9f),
                        PathNode.QuadTo(986.0f, 100.5f, 995.4f, 109.9f),
                        PathNode.LineTo(1014.3f, 128.8f),
                        PathNode.QuadTo(1023.3f, 137.8f, 1022.4f, 147.7f),
                        PathNode.QuadTo(1021.6f, 157.6f, 1014.2f, 164.6f),
                        PathNode.LineTo(758.4f, 420.5f),
                        PathNode.HorizontalTo(1006.6f),
                        PathNode.QuadTo(1017.5f, 420.5f, 1023.8f, 426.8f),
                        PathNode.QuadTo(1030.2f, 433.1f, 1030.2f, 441.3f),
                        PathNode.VerticalTo(477.5f),
                        PathNode.QuadTo(1030.2f, 486.6f, 1023.8f, 492.5f),
                        PathNode.QuadTo(1017.5f, 498.3f, 1006.6f, 498.3f),
                        PathNode.HorizontalTo(682.1f),
                        PathNode.QuadTo(654.6f, 498.3f, 640.6f, 484.4f),
                        PathNode.QuadTo(626.5f, 470.5f, 626.5f, 443.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomoutNormal!!
    }

private var _zoomoutNormal: ImageVector? = null

val MiuixIcons.Regular.ZoomOut: ImageVector
    get() {
        if (_zoomoutRegular != null) return _zoomoutRegular!!
        _zoomoutRegular = ImageVector.Builder(
            name = "ZoomOut.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1126.8f,
            viewportHeight = 1126.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1126.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(502.9f, 682.9f),
                        PathNode.VerticalTo(1004.9f),
                        PathNode.QuadTo(502.9f, 1016.9f, 496.9f, 1024.4f),
                        PathNode.QuadTo(490.9f, 1031.9f, 478.9f, 1031.9f),
                        PathNode.HorizontalTo(440.9f),
                        PathNode.QuadTo(428.9f, 1031.9f, 422.9f, 1024.4f),
                        PathNode.QuadTo(416.9f, 1016.9f, 416.9f, 1004.9f),
                        PathNode.VerticalTo(768.9f),
                        PathNode.LineTo(169.9f, 1015.9f),
                        PathNode.QuadTo(161.9f, 1024.9f, 150.9f, 1026.4f),
                        PathNode.QuadTo(139.9f, 1027.9f, 129.9f, 1017.9f),
                        PathNode.LineTo(108.9f, 996.9f),
                        PathNode.QuadTo(98.9f, 986.9f, 99.9f, 975.9f),
                        PathNode.QuadTo(100.9f, 964.9f, 108.9f, 956.9f),
                        PathNode.LineTo(356.9f, 708.9f),
                        PathNode.HorizontalTo(119.9f),
                        PathNode.QuadTo(107.9f, 708.9f, 100.9f, 701.9f),
                        PathNode.QuadTo(93.9f, 694.9f, 93.9f, 685.9f),
                        PathNode.VerticalTo(645.9f),
                        PathNode.QuadTo(93.9f, 635.9f, 100.9f, 629.4f),
                        PathNode.QuadTo(107.9f, 622.9f, 119.9f, 622.9f),
                        PathNode.HorizontalTo(442.9f),
                        PathNode.QuadTo(471.9f, 622.9f, 487.4f, 638.4f),
                        PathNode.QuadTo(502.9f, 653.9f, 502.9f, 682.9f),
                        PathNode.Close,
                        PathNode.MoveTo(623.9f, 443.9f),
                        PathNode.VerticalTo(121.9f),
                        PathNode.QuadTo(623.9f, 109.9f, 629.9f, 102.4f),
                        PathNode.QuadTo(635.9f, 94.9f, 647.9f, 94.9f),
                        PathNode.HorizontalTo(685.9f),
                        PathNode.QuadTo(697.9f, 94.9f, 703.9f, 102.4f),
                        PathNode.QuadTo(709.9f, 109.9f, 709.9f, 121.9f),
                        PathNode.VerticalTo(357.9f),
                        PathNode.LineTo(956.9f, 110.9f),
                        PathNode.QuadTo(964.9f, 101.9f, 975.9f, 100.4f),
                        PathNode.QuadTo(986.9f, 98.9f, 996.9f, 108.9f),
                        PathNode.LineTo(1017.9f, 129.9f),
                        PathNode.QuadTo(1027.9f, 139.9f, 1026.9f, 150.9f),
                        PathNode.QuadTo(1025.9f, 161.9f, 1017.9f, 169.9f),
                        PathNode.LineTo(769.9f, 417.9f),
                        PathNode.HorizontalTo(1006.9f),
                        PathNode.QuadTo(1018.9f, 417.9f, 1025.9f, 424.9f),
                        PathNode.QuadTo(1032.9f, 431.9f, 1032.9f, 440.9f),
                        PathNode.VerticalTo(480.9f),
                        PathNode.QuadTo(1032.9f, 490.9f, 1025.9f, 497.4f),
                        PathNode.QuadTo(1018.9f, 503.9f, 1006.9f, 503.9f),
                        PathNode.HorizontalTo(683.9f),
                        PathNode.QuadTo(654.9f, 503.9f, 639.4f, 488.4f),
                        PathNode.QuadTo(623.9f, 472.9f, 623.9f, 443.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomoutRegular!!
    }

private var _zoomoutRegular: ImageVector? = null

val MiuixIcons.Medium.ZoomOut: ImageVector
    get() {
        if (_zoomoutMedium != null) return _zoomoutMedium!!
        _zoomoutMedium = ImageVector.Builder(
            name = "ZoomOut.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1146.6f,
            viewportHeight = 1146.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1146.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(521.0f, 691.0f),
                        PathNode.VerticalTo(1014.8f),
                        PathNode.QuadTo(521.0f, 1030.3f, 512.7f, 1040.2f),
                        PathNode.QuadTo(504.3f, 1050.0f, 488.8f, 1050.0f),
                        PathNode.HorizontalTo(450.8f),
                        PathNode.QuadTo(435.3f, 1050.0f, 426.9f, 1040.2f),
                        PathNode.QuadTo(418.5f, 1030.3f, 418.5f, 1014.8f),
                        PathNode.VerticalTo(798.2f),
                        PathNode.LineTo(185.7f, 1031.7f),
                        PathNode.QuadTo(175.3f, 1042.4f, 161.1f, 1044.2f),
                        PathNode.QuadTo(146.8f, 1046.0f, 133.9f, 1033.7f),
                        PathNode.LineTo(112.9f, 1012.7f),
                        PathNode.QuadTo(100.5f, 999.7f, 101.5f, 985.2f),
                        PathNode.QuadTo(102.5f, 970.7f, 112.9f, 960.9f),
                        PathNode.LineTo(347.4f, 727.0f),
                        PathNode.HorizontalTo(129.8f),
                        PathNode.QuadTo(114.8f, 727.0f, 105.2f, 717.4f),
                        PathNode.QuadTo(95.5f, 707.7f, 95.5f, 695.8f),
                        PathNode.VerticalTo(655.8f),
                        PathNode.QuadTo(95.5f, 642.8f, 104.9f, 633.7f),
                        PathNode.QuadTo(114.3f, 624.5f, 129.8f, 624.5f),
                        PathNode.HorizontalTo(453.4f),
                        PathNode.QuadTo(485.9f, 624.5f, 503.5f, 641.8f),
                        PathNode.QuadTo(521.0f, 659.1f, 521.0f, 691.0f),
                        PathNode.Close,
                        PathNode.MoveTo(625.5f, 455.5f),
                        PathNode.VerticalTo(131.8f),
                        PathNode.QuadTo(625.5f, 116.3f, 633.9f, 106.4f),
                        PathNode.QuadTo(642.3f, 96.5f, 657.8f, 96.5f),
                        PathNode.HorizontalTo(695.8f),
                        PathNode.QuadTo(711.3f, 96.5f, 719.7f, 106.4f),
                        PathNode.QuadTo(728.0f, 116.3f, 728.0f, 131.8f),
                        PathNode.VerticalTo(348.4f),
                        PathNode.LineTo(960.9f, 114.9f),
                        PathNode.QuadTo(971.3f, 104.1f, 985.5f, 102.3f),
                        PathNode.QuadTo(999.7f, 100.5f, 1012.7f, 112.9f),
                        PathNode.LineTo(1033.7f, 133.9f),
                        PathNode.QuadTo(1046.0f, 146.8f, 1045.0f, 161.4f),
                        PathNode.QuadTo(1044.0f, 175.9f, 1033.7f, 185.7f),
                        PathNode.LineTo(799.2f, 419.5f),
                        PathNode.HorizontalTo(1016.8f),
                        PathNode.QuadTo(1031.7f, 419.5f, 1041.4f, 429.2f),
                        PathNode.QuadTo(1051.0f, 438.8f, 1051.0f, 450.8f),
                        PathNode.VerticalTo(490.8f),
                        PathNode.QuadTo(1051.0f, 503.7f, 1041.7f, 512.9f),
                        PathNode.QuadTo(1032.3f, 522.0f, 1016.8f, 522.0f),
                        PathNode.HorizontalTo(693.2f),
                        PathNode.QuadTo(660.7f, 522.0f, 643.1f, 504.8f),
                        PathNode.QuadTo(625.5f, 487.5f, 625.5f, 455.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomoutMedium!!
    }

private var _zoomoutMedium: ImageVector? = null

val MiuixIcons.Demibold.ZoomOut: ImageVector
    get() {
        if (_zoomoutDemibold != null) return _zoomoutDemibold!!
        _zoomoutDemibold = ImageVector.Builder(
            name = "ZoomOut.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1160.4f,
            viewportHeight = 1160.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1160.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(533.7f, 696.7f),
                        PathNode.VerticalTo(1021.7f),
                        PathNode.QuadTo(533.7f, 1039.7f, 523.7f, 1051.2f),
                        PathNode.QuadTo(513.7f, 1062.7f, 495.7f, 1062.7f),
                        PathNode.HorizontalTo(457.7f),
                        PathNode.QuadTo(439.7f, 1062.7f, 429.7f, 1051.2f),
                        PathNode.QuadTo(419.7f, 1039.7f, 419.7f, 1021.7f),
                        PathNode.VerticalTo(818.7f),
                        PathNode.LineTo(196.7f, 1042.7f),
                        PathNode.QuadTo(184.7f, 1054.7f, 168.2f, 1056.7f),
                        PathNode.QuadTo(151.7f, 1058.7f, 136.7f, 1044.7f),
                        PathNode.LineTo(115.7f, 1023.7f),
                        PathNode.QuadTo(101.7f, 1008.7f, 102.7f, 991.7f),
                        PathNode.QuadTo(103.7f, 974.7f, 115.7f, 963.7f),
                        PathNode.LineTo(340.7f, 739.7f),
                        PathNode.HorizontalTo(136.7f),
                        PathNode.QuadTo(119.7f, 739.7f, 108.2f, 728.2f),
                        PathNode.QuadTo(96.7f, 716.7f, 96.7f, 702.7f),
                        PathNode.VerticalTo(662.7f),
                        PathNode.QuadTo(96.7f, 647.7f, 107.7f, 636.7f),
                        PathNode.QuadTo(118.7f, 625.7f, 136.7f, 625.7f),
                        PathNode.HorizontalTo(460.7f),
                        PathNode.QuadTo(495.7f, 625.7f, 514.7f, 644.2f),
                        PathNode.QuadTo(533.7f, 662.7f, 533.7f, 696.7f),
                        PathNode.Close,
                        PathNode.MoveTo(626.7f, 463.7f),
                        PathNode.VerticalTo(138.7f),
                        PathNode.QuadTo(626.7f, 120.7f, 636.7f, 109.2f),
                        PathNode.QuadTo(646.7f, 97.7f, 664.7f, 97.7f),
                        PathNode.HorizontalTo(702.7f),
                        PathNode.QuadTo(720.7f, 97.7f, 730.7f, 109.2f),
                        PathNode.QuadTo(740.7f, 120.7f, 740.7f, 138.7f),
                        PathNode.VerticalTo(341.7f),
                        PathNode.LineTo(963.7f, 117.7f),
                        PathNode.QuadTo(975.7f, 105.7f, 992.2f, 103.7f),
                        PathNode.QuadTo(1008.7f, 101.7f, 1023.7f, 115.7f),
                        PathNode.LineTo(1044.7f, 136.7f),
                        PathNode.QuadTo(1058.7f, 151.7f, 1057.7f, 168.7f),
                        PathNode.QuadTo(1056.7f, 185.7f, 1044.7f, 196.7f),
                        PathNode.LineTo(819.7f, 420.7f),
                        PathNode.HorizontalTo(1023.7f),
                        PathNode.QuadTo(1040.7f, 420.7f, 1052.2f, 432.2f),
                        PathNode.QuadTo(1063.7f, 443.7f, 1063.7f, 457.7f),
                        PathNode.VerticalTo(497.7f),
                        PathNode.QuadTo(1063.7f, 512.7f, 1052.7f, 523.7f),
                        PathNode.QuadTo(1041.7f, 534.7f, 1023.7f, 534.7f),
                        PathNode.HorizontalTo(699.7f),
                        PathNode.QuadTo(664.7f, 534.7f, 645.7f, 516.2f),
                        PathNode.QuadTo(626.7f, 497.7f, 626.7f, 463.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomoutDemibold!!
    }

private var _zoomoutDemibold: ImageVector? = null
