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

val MiuixIcons.Glass.ExpandMore: ImageVector
    get() = MiuixIcons.Glass.Regular.ExpandMore

val MiuixIcons.Glass.Light.ExpandMore: ImageVector
    get() {
        if (_expandMoreLight != null) return _expandMoreLight!!
        _expandMoreLight = ImageVector.Builder(
            name = "ExpandMore.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(179.7f, 677.9f),
                        PathNode.LineTo(179.7f, 958.2f),
                        PathNode.QuadTo(179.7f, 960.6f, 181.0f, 961.9f),
                        PathNode.QuadTo(182.3f, 963.2f, 184.7f, 963.2f),
                        PathNode.LineTo(464.9f, 963.2f),
                        PathNode.QuadTo(472.3f, 963.2f, 477.7f, 968.2f),
                        PathNode.QuadTo(483.0f, 973.3f, 483.0f, 980.7f),
                        PathNode.LineTo(483.0f, 1006.4f),
                        PathNode.QuadTo(483.0f, 1014.0f, 477.7f, 1019.4f),
                        PathNode.QuadTo(472.3f, 1024.7f, 464.9f, 1024.7f),
                        PathNode.LineTo(158.2f, 1024.7f),
                        PathNode.QuadTo(139.4f, 1024.7f, 128.8f, 1014.1f),
                        PathNode.QuadTo(118.2f, 1003.5f, 118.2f, 984.7f),
                        PathNode.LineTo(118.2f, 677.9f),
                        PathNode.QuadTo(118.2f, 670.5f, 123.5f, 665.2f),
                        PathNode.QuadTo(128.9f, 659.8f, 136.3f, 659.8f),
                        PathNode.LineTo(162.0f, 659.8f),
                        PathNode.QuadTo(169.6f, 659.8f, 174.7f, 665.2f),
                        PathNode.QuadTo(179.7f, 670.5f, 179.7f, 677.9f),
                        PathNode.Close,
                        PathNode.MoveTo(651.7f, 570.8f),
                        PathNode.QuadTo(651.7f, 592.9f, 640.7f, 611.7f),
                        PathNode.QuadTo(629.6f, 630.4f, 611.3f, 641.1f),
                        PathNode.QuadTo(593.0f, 651.7f, 570.9f, 651.7f),
                        PathNode.QuadTo(537.2f, 651.7f, 513.7f, 628.2f),
                        PathNode.QuadTo(490.2f, 604.7f, 490.2f, 570.9f),
                        PathNode.QuadTo(490.2f, 548.8f, 500.8f, 530.5f),
                        PathNode.QuadTo(511.4f, 512.3f, 530.1f, 501.2f),
                        PathNode.QuadTo(548.8f, 490.2f, 570.9f, 490.2f),
                        PathNode.QuadTo(593.0f, 490.2f, 611.3f, 501.2f),
                        PathNode.QuadTo(629.6f, 512.2f, 640.7f, 530.5f),
                        PathNode.QuadTo(651.7f, 548.8f, 651.7f, 570.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1024.7f, 158.2f),
                        PathNode.LineTo(1024.7f, 451.6f),
                        PathNode.QuadTo(1024.7f, 459.0f, 1019.3f, 464.3f),
                        PathNode.QuadTo(1014.0f, 469.7f, 1006.6f, 469.7f),
                        PathNode.LineTo(980.9f, 469.7f),
                        PathNode.QuadTo(973.3f, 469.7f, 967.9f, 464.3f),
                        PathNode.QuadTo(962.6f, 459.0f, 962.6f, 451.6f),
                        PathNode.LineTo(962.6f, 185.6f),
                        PathNode.QuadTo(962.6f, 182.7f, 961.6f, 181.2f),
                        PathNode.QuadTo(960.6f, 179.7f, 957.7f, 179.7f),
                        PathNode.LineTo(677.4f, 179.7f),
                        PathNode.QuadTo(670.6f, 179.7f, 665.2f, 174.3f),
                        PathNode.QuadTo(659.8f, 168.9f, 659.8f, 162.2f),
                        PathNode.LineTo(659.8f, 135.9f),
                        PathNode.QuadTo(659.8f, 128.3f, 665.2f, 123.2f),
                        PathNode.QuadTo(670.6f, 118.2f, 677.4f, 118.2f),
                        PathNode.LineTo(984.7f, 118.2f),
                        PathNode.QuadTo(1004.0f, 118.2f, 1014.4f, 128.8f),
                        PathNode.QuadTo(1024.7f, 139.4f, 1024.7f, 158.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandMoreLight!!
    }

private var _expandMoreLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ExpandMore: ImageVector
    get() {
        if (_expandMoreNormal != null) return _expandMoreNormal!!
        _expandMoreNormal = ImageVector.Builder(
            name = "ExpandMore.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(189.5f, 683.4f),
                        PathNode.LineTo(189.5f, 948.4f),
                        PathNode.QuadTo(189.5f, 950.5f, 190.9f, 951.9f),
                        PathNode.QuadTo(192.3f, 953.4f, 194.5f, 953.4f),
                        PathNode.LineTo(459.4f, 953.4f),
                        PathNode.QuadTo(469.3f, 953.4f, 476.5f, 960.1f),
                        PathNode.QuadTo(483.7f, 966.9f, 483.7f, 976.7f),
                        PathNode.LineTo(483.7f, 1010.2f),
                        PathNode.QuadTo(483.7f, 1020.2f, 476.5f, 1027.3f),
                        PathNode.QuadTo(469.3f, 1034.5f, 459.4f, 1034.5f),
                        PathNode.LineTo(162.1f, 1034.5f),
                        PathNode.QuadTo(136.3f, 1034.5f, 122.3f, 1020.5f),
                        PathNode.QuadTo(108.4f, 1006.5f, 108.4f, 980.7f),
                        PathNode.LineTo(108.4f, 683.4f),
                        PathNode.QuadTo(108.4f, 673.6f, 115.6f, 666.4f),
                        PathNode.QuadTo(122.7f, 659.2f, 132.6f, 659.2f),
                        PathNode.LineTo(166.1f, 659.2f),
                        PathNode.QuadTo(176.0f, 659.2f, 182.7f, 666.4f),
                        PathNode.QuadTo(189.5f, 673.6f, 189.5f, 683.4f),
                        PathNode.Close,
                        PathNode.MoveTo(661.5f, 570.9f),
                        PathNode.QuadTo(661.5f, 595.7f, 649.1f, 616.7f),
                        PathNode.QuadTo(636.7f, 637.6f, 616.2f, 649.6f),
                        PathNode.QuadTo(595.7f, 661.5f, 570.9f, 661.5f),
                        PathNode.QuadTo(532.9f, 661.5f, 506.6f, 635.2f),
                        PathNode.QuadTo(480.4f, 609.0f, 480.4f, 570.9f),
                        PathNode.QuadTo(480.4f, 546.1f, 492.3f, 525.7f),
                        PathNode.QuadTo(504.2f, 505.2f, 525.2f, 492.8f),
                        PathNode.QuadTo(546.1f, 480.4f, 570.9f, 480.4f),
                        PathNode.QuadTo(595.7f, 480.4f, 616.2f, 492.8f),
                        PathNode.QuadTo(636.7f, 505.2f, 649.1f, 525.6f),
                        PathNode.QuadTo(661.5f, 546.1f, 661.5f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1034.5f, 162.1f),
                        PathNode.LineTo(1034.5f, 455.3f),
                        PathNode.QuadTo(1034.5f, 465.1f, 1027.3f, 472.3f),
                        PathNode.QuadTo(1020.1f, 479.5f, 1010.3f, 479.5f),
                        PathNode.LineTo(976.8f, 479.5f),
                        PathNode.QuadTo(966.8f, 479.5f, 959.7f, 472.3f),
                        PathNode.QuadTo(952.5f, 465.1f, 952.5f, 455.3f),
                        PathNode.LineTo(952.5f, 194.8f),
                        PathNode.QuadTo(952.5f, 192.5f, 951.5f, 191.0f),
                        PathNode.QuadTo(950.5f, 189.5f, 948.2f, 189.5f),
                        PathNode.LineTo(682.6f, 189.5f),
                        PathNode.QuadTo(673.6f, 189.5f, 666.4f, 182.3f),
                        PathNode.QuadTo(659.2f, 175.1f, 659.2f, 166.1f),
                        PathNode.LineTo(659.2f, 131.8f),
                        PathNode.QuadTo(659.2f, 121.8f, 666.4f, 115.1f),
                        PathNode.QuadTo(673.6f, 108.4f, 682.6f, 108.4f),
                        PathNode.LineTo(980.7f, 108.4f),
                        PathNode.QuadTo(1006.7f, 108.4f, 1020.6f, 122.3f),
                        PathNode.QuadTo(1034.5f, 136.3f, 1034.5f, 162.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandMoreNormal!!
    }

private var _expandMoreNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ExpandMore: ImageVector
    get() {
        if (_expandMoreRegular != null) return _expandMoreRegular!!
        _expandMoreRegular = ImageVector.Builder(
            name = "ExpandMore.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(193.9f, 685.9f),
                        PathNode.LineTo(193.9f, 943.9f),
                        PathNode.QuadTo(193.9f, 945.9f, 195.4f, 947.4f),
                        PathNode.QuadTo(196.9f, 948.9f, 198.9f, 948.9f),
                        PathNode.LineTo(456.9f, 948.9f),
                        PathNode.QuadTo(467.9f, 948.9f, 475.9f, 956.4f),
                        PathNode.QuadTo(483.9f, 963.9f, 483.9f, 974.9f),
                        PathNode.LineTo(483.9f, 1011.9f),
                        PathNode.QuadTo(483.9f, 1022.9f, 475.9f, 1030.9f),
                        PathNode.QuadTo(467.9f, 1038.9f, 456.9f, 1038.9f),
                        PathNode.LineTo(163.9f, 1038.9f),
                        PathNode.QuadTo(134.9f, 1038.9f, 119.4f, 1023.4f),
                        PathNode.QuadTo(103.9f, 1007.9f, 103.9f, 978.9f),
                        PathNode.LineTo(103.9f, 685.9f),
                        PathNode.QuadTo(103.9f, 674.9f, 111.9f, 666.9f),
                        PathNode.QuadTo(119.9f, 658.9f, 130.9f, 658.9f),
                        PathNode.LineTo(167.9f, 658.9f),
                        PathNode.QuadTo(178.9f, 658.9f, 186.4f, 666.9f),
                        PathNode.QuadTo(193.9f, 674.9f, 193.9f, 685.9f),
                        PathNode.Close,
                        PathNode.MoveTo(665.9f, 570.9f),
                        PathNode.QuadTo(665.9f, 596.9f, 652.9f, 618.9f),
                        PathNode.QuadTo(639.9f, 640.9f, 618.4f, 653.4f),
                        PathNode.QuadTo(596.9f, 665.9f, 570.9f, 665.9f),
                        PathNode.QuadTo(530.9f, 665.9f, 503.4f, 638.4f),
                        PathNode.QuadTo(475.9f, 610.9f, 475.9f, 570.9f),
                        PathNode.QuadTo(475.9f, 544.9f, 488.4f, 523.4f),
                        PathNode.QuadTo(500.9f, 501.9f, 522.9f, 488.9f),
                        PathNode.QuadTo(544.9f, 475.9f, 570.9f, 475.9f),
                        PathNode.QuadTo(596.9f, 475.9f, 618.4f, 488.9f),
                        PathNode.QuadTo(639.9f, 501.9f, 652.9f, 523.4f),
                        PathNode.QuadTo(665.9f, 544.9f, 665.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1038.9f, 163.9f),
                        PathNode.LineTo(1038.9f, 456.9f),
                        PathNode.QuadTo(1038.9f, 467.9f, 1030.9f, 475.9f),
                        PathNode.QuadTo(1022.9f, 483.9f, 1011.9f, 483.9f),
                        PathNode.LineTo(974.9f, 483.9f),
                        PathNode.QuadTo(963.9f, 483.9f, 955.9f, 475.9f),
                        PathNode.QuadTo(947.9f, 467.9f, 947.9f, 456.9f),
                        PathNode.LineTo(947.9f, 198.9f),
                        PathNode.QuadTo(947.9f, 196.9f, 946.9f, 195.4f),
                        PathNode.QuadTo(945.9f, 193.9f, 943.9f, 193.9f),
                        PathNode.LineTo(684.9f, 193.9f),
                        PathNode.QuadTo(674.9f, 193.9f, 666.9f, 185.9f),
                        PathNode.QuadTo(658.9f, 177.9f, 658.9f, 167.9f),
                        PathNode.LineTo(658.9f, 129.9f),
                        PathNode.QuadTo(658.9f, 118.9f, 666.9f, 111.4f),
                        PathNode.QuadTo(674.9f, 103.9f, 684.9f, 103.9f),
                        PathNode.LineTo(978.9f, 103.9f),
                        PathNode.QuadTo(1007.9f, 103.9f, 1023.4f, 119.4f),
                        PathNode.QuadTo(1038.9f, 134.9f, 1038.9f, 163.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandMoreRegular!!
    }

private var _expandMoreRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ExpandMore: ImageVector
    get() {
        if (_expandMoreMedium != null) return _expandMoreMedium!!
        _expandMoreMedium = ImageVector.Builder(
            name = "ExpandMore.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(202.0f, 685.9f),
                        PathNode.LineTo(202.0f, 935.9f),
                        PathNode.QuadTo(202.0f, 937.8f, 203.5f, 939.3f),
                        PathNode.QuadTo(205.0f, 940.8f, 207.0f, 940.8f),
                        PathNode.LineTo(456.9f, 940.8f),
                        PathNode.QuadTo(471.2f, 940.8f, 481.6f, 950.8f),
                        PathNode.QuadTo(492.0f, 960.7f, 492.0f, 974.9f),
                        PathNode.LineTo(492.0f, 1011.9f),
                        PathNode.QuadTo(492.0f, 1026.2f, 481.6f, 1036.6f),
                        PathNode.QuadTo(471.2f, 1047.0f, 456.9f, 1047.0f),
                        PathNode.LineTo(155.0f, 1047.0f),
                        PathNode.QuadTo(127.6f, 1047.0f, 111.7f, 1031.3f),
                        PathNode.QuadTo(95.8f, 1015.5f, 95.8f, 988.4f),
                        PathNode.LineTo(95.8f, 685.9f),
                        PathNode.QuadTo(95.8f, 671.6f, 106.2f, 661.2f),
                        PathNode.QuadTo(116.6f, 650.8f, 130.9f, 650.8f),
                        PathNode.LineTo(167.9f, 650.8f),
                        PathNode.QuadTo(182.2f, 650.8f, 192.1f, 661.2f),
                        PathNode.QuadTo(202.0f, 671.6f, 202.0f, 685.9f),
                        PathNode.Close,
                        PathNode.MoveTo(674.0f, 571.1f),
                        PathNode.QuadTo(674.0f, 599.1f, 659.9f, 623.0f),
                        PathNode.QuadTo(645.8f, 646.9f, 622.5f, 660.5f),
                        PathNode.QuadTo(599.1f, 674.0f, 570.9f, 674.0f),
                        PathNode.QuadTo(527.7f, 674.0f, 497.8f, 644.1f),
                        PathNode.QuadTo(467.8f, 614.2f, 467.8f, 570.9f),
                        PathNode.QuadTo(467.8f, 542.8f, 481.4f, 519.4f),
                        PathNode.QuadTo(495.0f, 496.1f, 518.8f, 481.9f),
                        PathNode.QuadTo(542.7f, 467.8f, 570.9f, 467.8f),
                        PathNode.QuadTo(598.8f, 467.8f, 622.3f, 481.9f),
                        PathNode.QuadTo(645.9f, 496.0f, 660.0f, 519.5f),
                        PathNode.QuadTo(674.0f, 543.0f, 674.0f, 571.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1047.0f, 154.7f),
                        PathNode.LineTo(1047.0f, 456.9f),
                        PathNode.QuadTo(1047.0f, 471.2f, 1036.6f, 481.6f),
                        PathNode.QuadTo(1026.2f, 492.0f, 1011.9f, 492.0f),
                        PathNode.LineTo(974.9f, 492.0f),
                        PathNode.QuadTo(960.6f, 492.0f, 950.2f, 481.6f),
                        PathNode.QuadTo(939.8f, 471.2f, 939.8f, 456.9f),
                        PathNode.LineTo(939.8f, 207.0f),
                        PathNode.QuadTo(939.8f, 205.0f, 938.9f, 203.5f),
                        PathNode.QuadTo(937.9f, 202.0f, 935.9f, 202.0f),
                        PathNode.LineTo(684.9f, 202.0f),
                        PathNode.QuadTo(671.8f, 202.0f, 661.3f, 191.5f),
                        PathNode.QuadTo(650.8f, 181.0f, 650.8f, 167.9f),
                        PathNode.LineTo(650.8f, 129.9f),
                        PathNode.QuadTo(650.8f, 115.7f, 661.3f, 105.8f),
                        PathNode.QuadTo(671.8f, 95.8f, 684.9f, 95.8f),
                        PathNode.LineTo(988.4f, 95.8f),
                        PathNode.QuadTo(1015.8f, 96.4f, 1031.4f, 112.0f),
                        PathNode.QuadTo(1047.0f, 127.6f, 1047.0f, 154.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandMoreMedium!!
    }

private var _expandMoreMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ExpandMore: ImageVector
    get() {
        if (_expandMoreDemibold != null) return _expandMoreDemibold!!
        _expandMoreDemibold = ImageVector.Builder(
            name = "ExpandMore.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(207.7f, 685.9f),
                        PathNode.LineTo(207.7f, 930.3f),
                        PathNode.QuadTo(207.7f, 932.1f, 209.2f, 933.6f),
                        PathNode.QuadTo(210.7f, 935.1f, 212.7f, 935.1f),
                        PathNode.LineTo(456.9f, 935.1f),
                        PathNode.QuadTo(473.5f, 935.1f, 485.6f, 946.8f),
                        PathNode.QuadTo(497.7f, 958.4f, 497.7f, 974.9f),
                        PathNode.LineTo(497.7f, 1011.9f),
                        PathNode.QuadTo(497.7f, 1028.5f, 485.6f, 1040.6f),
                        PathNode.QuadTo(473.5f, 1052.7f, 456.9f, 1052.7f),
                        PathNode.LineTo(148.8f, 1052.7f),
                        PathNode.QuadTo(122.5f, 1052.7f, 106.3f, 1036.8f),
                        PathNode.QuadTo(90.1f, 1020.8f, 90.1f, 995.0f),
                        PathNode.LineTo(90.1f, 685.9f),
                        PathNode.QuadTo(90.1f, 669.3f, 102.2f, 657.2f),
                        PathNode.QuadTo(114.3f, 645.1f, 130.9f, 645.1f),
                        PathNode.LineTo(167.9f, 645.1f),
                        PathNode.QuadTo(184.4f, 645.1f, 196.1f, 657.2f),
                        PathNode.QuadTo(207.7f, 669.3f, 207.7f, 685.9f),
                        PathNode.Close,
                        PathNode.MoveTo(679.7f, 571.2f),
                        PathNode.QuadTo(679.7f, 600.6f, 664.8f, 625.8f),
                        PathNode.QuadTo(649.9f, 651.1f, 625.3f, 665.4f),
                        PathNode.QuadTo(600.7f, 679.7f, 570.9f, 679.7f),
                        PathNode.QuadTo(525.4f, 679.7f, 493.8f, 648.1f),
                        PathNode.QuadTo(462.1f, 616.4f, 462.1f, 570.9f),
                        PathNode.QuadTo(462.1f, 541.3f, 476.5f, 516.6f),
                        PathNode.QuadTo(490.8f, 492.0f, 516.0f, 477.0f),
                        PathNode.QuadTo(541.2f, 462.1f, 570.9f, 462.1f),
                        PathNode.QuadTo(600.1f, 462.1f, 625.1f, 477.0f),
                        PathNode.QuadTo(650.0f, 491.8f, 664.9f, 516.8f),
                        PathNode.QuadTo(679.7f, 541.7f, 679.7f, 571.2f),
                        PathNode.Close,
                        PathNode.MoveTo(1052.7f, 148.3f),
                        PathNode.LineTo(1052.7f, 456.9f),
                        PathNode.QuadTo(1052.7f, 473.5f, 1040.6f, 485.6f),
                        PathNode.QuadTo(1028.5f, 497.7f, 1011.9f, 497.7f),
                        PathNode.LineTo(974.9f, 497.7f),
                        PathNode.QuadTo(958.3f, 497.7f, 946.2f, 485.6f),
                        PathNode.QuadTo(934.1f, 473.5f, 934.1f, 456.9f),
                        PathNode.LineTo(934.1f, 212.6f),
                        PathNode.QuadTo(934.1f, 210.7f, 933.2f, 209.2f),
                        PathNode.QuadTo(932.2f, 207.7f, 930.4f, 207.7f),
                        PathNode.LineTo(684.9f, 207.7f),
                        PathNode.QuadTo(669.6f, 207.7f, 657.4f, 195.5f),
                        PathNode.QuadTo(645.1f, 183.2f, 645.1f, 167.9f),
                        PathNode.LineTo(645.1f, 129.9f),
                        PathNode.QuadTo(645.1f, 113.4f, 657.4f, 101.8f),
                        PathNode.QuadTo(669.6f, 90.1f, 684.9f, 90.1f),
                        PathNode.LineTo(995.0f, 90.1f),
                        PathNode.QuadTo(1021.3f, 91.1f, 1037.0f, 106.8f),
                        PathNode.QuadTo(1052.7f, 122.5f, 1052.7f, 148.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandMoreDemibold!!
    }

private var _expandMoreDemibold: ImageVector? = null
