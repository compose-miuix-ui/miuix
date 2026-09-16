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

val MiuixIcons.Glass.ZoomOut: ImageVector
    get() = MiuixIcons.Glass.Regular.ZoomOut

val MiuixIcons.Glass.Light.ZoomOut: ImageVector
    get() {
        if (_zoomOutLight != null) return _zoomOutLight!!
        _zoomOutLight = ImageVector.Builder(
            name = "ZoomOut.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(497.6f, 687.8f),
                        PathNode.LineTo(497.6f, 1017.4f),
                        PathNode.QuadTo(497.6f, 1025.4f, 493.4f, 1030.5f),
                        PathNode.QuadTo(489.3f, 1035.5f, 481.0f, 1035.5f),
                        PathNode.LineTo(454.7f, 1035.5f),
                        PathNode.QuadTo(446.5f, 1035.5f, 442.4f, 1030.5f),
                        PathNode.QuadTo(438.3f, 1025.4f, 438.3f, 1017.4f),
                        PathNode.LineTo(438.3f, 744.9f),
                        PathNode.LineTo(166.4f, 1016.8f),
                        PathNode.QuadTo(161.0f, 1022.7f, 153.6f, 1023.5f),
                        PathNode.QuadTo(146.2f, 1024.4f, 139.7f, 1017.9f),
                        PathNode.LineTo(125.0f, 1003.3f),
                        PathNode.QuadTo(118.0f, 996.3f, 118.7f, 988.8f),
                        PathNode.QuadTo(119.3f, 981.4f, 124.5f, 976.0f),
                        PathNode.LineTo(396.9f, 703.6f),
                        PathNode.LineTo(123.9f, 703.6f),
                        PathNode.QuadTo(115.8f, 703.6f, 111.1f, 698.8f),
                        PathNode.QuadTo(106.4f, 693.9f, 106.4f, 687.7f),
                        PathNode.LineTo(106.4f, 660.2f),
                        PathNode.QuadTo(106.4f, 653.3f, 111.1f, 648.8f),
                        PathNode.QuadTo(115.8f, 644.3f, 123.9f, 644.3f),
                        PathNode.LineTo(452.3f, 644.3f),
                        PathNode.QuadTo(475.5f, 644.3f, 486.5f, 654.9f),
                        PathNode.QuadTo(497.6f, 665.5f, 497.6f, 687.8f),
                        PathNode.Close,
                        PathNode.MoveTo(645.3f, 455.0f),
                        PathNode.LineTo(645.3f, 125.5f),
                        PathNode.QuadTo(645.3f, 117.4f, 649.4f, 112.4f),
                        PathNode.QuadTo(653.6f, 107.4f, 661.9f, 107.4f),
                        PathNode.LineTo(688.2f, 107.4f),
                        PathNode.QuadTo(696.4f, 107.4f, 700.5f, 112.4f),
                        PathNode.QuadTo(704.6f, 117.4f, 704.6f, 125.5f),
                        PathNode.LineTo(704.6f, 397.9f),
                        PathNode.LineTo(977.4f, 125.2f),
                        PathNode.QuadTo(982.7f, 119.3f, 990.2f, 118.4f),
                        PathNode.QuadTo(997.6f, 117.6f, 1004.6f, 124.6f),
                        PathNode.LineTo(1019.3f, 139.3f),
                        PathNode.QuadTo(1025.7f, 145.7f, 1025.1f, 153.1f),
                        PathNode.QuadTo(1024.4f, 160.5f, 1019.3f, 165.9f),
                        PathNode.LineTo(745.9f, 439.3f),
                        PathNode.LineTo(1018.9f, 439.3f),
                        PathNode.QuadTo(1027.0f, 439.3f, 1031.8f, 444.1f),
                        PathNode.QuadTo(1036.5f, 448.9f, 1036.5f, 455.1f),
                        PathNode.LineTo(1036.5f, 482.7f),
                        PathNode.QuadTo(1036.5f, 489.6f, 1031.8f, 494.1f),
                        PathNode.QuadTo(1027.0f, 498.6f, 1018.9f, 498.6f),
                        PathNode.LineTo(690.6f, 498.6f),
                        PathNode.QuadTo(667.4f, 498.6f, 656.3f, 488.0f),
                        PathNode.QuadTo(645.3f, 477.4f, 645.3f, 455.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomOutLight!!
    }

private var _zoomOutLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ZoomOut: ImageVector
    get() {
        if (_zoomOutNormal != null) return _zoomOutNormal!!
        _zoomOutNormal = ImageVector.Builder(
            name = "ZoomOut.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(506.8f, 690.0f),
                        PathNode.LineTo(506.8f, 1014.3f),
                        PathNode.QuadTo(506.8f, 1025.1f, 501.3f, 1031.8f),
                        PathNode.QuadTo(495.9f, 1038.5f, 485.1f, 1038.5f),
                        PathNode.LineTo(450.7f, 1038.5f),
                        PathNode.QuadTo(439.9f, 1038.5f, 434.5f, 1031.8f),
                        PathNode.QuadTo(429.1f, 1025.1f, 429.1f, 1014.3f),
                        PathNode.LineTo(429.1f, 766.9f),
                        PathNode.LineTo(174.3f, 1021.7f),
                        PathNode.QuadTo(167.2f, 1029.7f, 157.3f, 1031.0f),
                        PathNode.QuadTo(147.4f, 1032.3f, 138.5f, 1023.4f),
                        PathNode.LineTo(119.5f, 1004.4f),
                        PathNode.QuadTo(110.4f, 995.3f, 111.3f, 985.5f),
                        PathNode.QuadTo(112.2f, 975.6f, 119.3f, 968.4f),
                        PathNode.LineTo(374.9f, 712.8f),
                        PathNode.LineTo(126.7f, 712.8f),
                        PathNode.QuadTo(115.9f, 712.8f, 109.6f, 706.4f),
                        PathNode.QuadTo(103.3f, 700.1f, 103.3f, 692.0f),
                        PathNode.LineTo(103.3f, 655.9f),
                        PathNode.QuadTo(103.3f, 646.8f, 109.6f, 641.0f),
                        PathNode.QuadTo(115.9f, 635.1f, 126.7f, 635.1f),
                        PathNode.LineTo(451.3f, 635.1f),
                        PathNode.QuadTo(478.5f, 635.1f, 492.7f, 649.1f),
                        PathNode.QuadTo(506.8f, 663.0f, 506.8f, 690.0f),
                        PathNode.Close,
                        PathNode.MoveTo(636.1f, 452.9f),
                        PathNode.LineTo(636.1f, 128.5f),
                        PathNode.QuadTo(636.1f, 117.8f, 641.5f, 111.0f),
                        PathNode.QuadTo(646.9f, 104.3f, 657.8f, 104.3f),
                        PathNode.LineTo(692.1f, 104.3f),
                        PathNode.QuadTo(702.9f, 104.3f, 708.4f, 111.0f),
                        PathNode.QuadTo(713.8f, 117.8f, 713.8f, 128.5f),
                        PathNode.LineTo(713.8f, 375.9f),
                        PathNode.LineTo(968.8f, 120.9f),
                        PathNode.QuadTo(976.0f, 112.8f, 985.9f, 111.6f),
                        PathNode.QuadTo(995.8f, 110.3f, 1004.8f, 119.3f),
                        PathNode.LineTo(1023.8f, 138.3f),
                        PathNode.QuadTo(1032.7f, 147.2f, 1031.8f, 157.1f),
                        PathNode.QuadTo(1031.0f, 167.0f, 1023.8f, 174.2f),
                        PathNode.LineTo(767.9f, 430.1f),
                        PathNode.LineTo(1016.2f, 430.1f),
                        PathNode.QuadTo(1027.0f, 430.1f, 1033.3f, 436.4f),
                        PathNode.QuadTo(1039.5f, 442.7f, 1039.5f, 450.9f),
                        PathNode.LineTo(1039.5f, 487.0f),
                        PathNode.QuadTo(1039.5f, 496.0f, 1033.3f, 501.9f),
                        PathNode.QuadTo(1027.0f, 507.8f, 1016.2f, 507.8f),
                        PathNode.LineTo(691.5f, 507.8f),
                        PathNode.QuadTo(664.3f, 507.8f, 650.2f, 493.8f),
                        PathNode.QuadTo(636.1f, 479.8f, 636.1f, 452.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomOutNormal!!
    }

private var _zoomOutNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ZoomOut: ImageVector
    get() {
        if (_zoomOutRegular != null) return _zoomOutRegular!!
        _zoomOutRegular = ImageVector.Builder(
            name = "ZoomOut.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(510.9f, 690.9f),
                        PathNode.LineTo(510.9f, 1012.9f),
                        PathNode.QuadTo(510.9f, 1024.9f, 504.9f, 1032.4f),
                        PathNode.QuadTo(498.9f, 1039.9f, 486.9f, 1039.9f),
                        PathNode.LineTo(448.9f, 1039.9f),
                        PathNode.QuadTo(436.9f, 1039.9f, 430.9f, 1032.4f),
                        PathNode.QuadTo(424.9f, 1024.9f, 424.9f, 1012.9f),
                        PathNode.LineTo(424.9f, 776.9f),
                        PathNode.LineTo(177.9f, 1023.9f),
                        PathNode.QuadTo(169.9f, 1032.9f, 158.9f, 1034.4f),
                        PathNode.QuadTo(147.9f, 1035.9f, 137.9f, 1025.9f),
                        PathNode.LineTo(116.9f, 1004.9f),
                        PathNode.QuadTo(106.9f, 994.9f, 107.9f, 983.9f),
                        PathNode.QuadTo(108.9f, 972.9f, 116.9f, 964.9f),
                        PathNode.LineTo(364.9f, 716.9f),
                        PathNode.LineTo(127.9f, 716.9f),
                        PathNode.QuadTo(115.9f, 716.9f, 108.9f, 709.9f),
                        PathNode.QuadTo(101.9f, 702.9f, 101.9f, 693.9f),
                        PathNode.LineTo(101.9f, 653.9f),
                        PathNode.QuadTo(101.9f, 643.9f, 108.9f, 637.4f),
                        PathNode.QuadTo(115.9f, 630.9f, 127.9f, 630.9f),
                        PathNode.LineTo(450.9f, 630.9f),
                        PathNode.QuadTo(479.9f, 630.9f, 495.4f, 646.4f),
                        PathNode.QuadTo(510.9f, 661.9f, 510.9f, 690.9f),
                        PathNode.Close,
                        PathNode.MoveTo(631.9f, 451.9f),
                        PathNode.LineTo(631.9f, 129.9f),
                        PathNode.QuadTo(631.9f, 117.9f, 637.9f, 110.4f),
                        PathNode.QuadTo(643.9f, 102.9f, 655.9f, 102.9f),
                        PathNode.LineTo(693.9f, 102.9f),
                        PathNode.QuadTo(705.9f, 102.9f, 711.9f, 110.4f),
                        PathNode.QuadTo(717.9f, 117.9f, 717.9f, 129.9f),
                        PathNode.LineTo(717.9f, 365.9f),
                        PathNode.LineTo(964.9f, 118.9f),
                        PathNode.QuadTo(972.9f, 109.9f, 983.9f, 108.4f),
                        PathNode.QuadTo(994.9f, 106.9f, 1004.9f, 116.9f),
                        PathNode.LineTo(1025.9f, 137.9f),
                        PathNode.QuadTo(1035.9f, 147.9f, 1034.9f, 158.9f),
                        PathNode.QuadTo(1033.9f, 169.9f, 1025.9f, 177.9f),
                        PathNode.LineTo(777.9f, 425.9f),
                        PathNode.LineTo(1014.9f, 425.9f),
                        PathNode.QuadTo(1026.9f, 425.9f, 1033.9f, 432.9f),
                        PathNode.QuadTo(1040.9f, 439.9f, 1040.9f, 448.9f),
                        PathNode.LineTo(1040.9f, 488.9f),
                        PathNode.QuadTo(1040.9f, 498.9f, 1033.9f, 505.4f),
                        PathNode.QuadTo(1026.9f, 511.9f, 1014.9f, 511.9f),
                        PathNode.LineTo(691.9f, 511.9f),
                        PathNode.QuadTo(662.9f, 511.9f, 647.4f, 496.4f),
                        PathNode.QuadTo(631.9f, 480.9f, 631.9f, 451.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomOutRegular!!
    }

private var _zoomOutRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ZoomOut: ImageVector
    get() {
        if (_zoomOutMedium != null) return _zoomOutMedium!!
        _zoomOutMedium = ImageVector.Builder(
            name = "ZoomOut.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(519.0f, 689.0f),
                        PathNode.LineTo(519.0f, 1012.9f),
                        PathNode.QuadTo(519.0f, 1028.4f, 510.6f, 1038.2f),
                        PathNode.QuadTo(502.2f, 1048.0f, 486.9f, 1048.0f),
                        PathNode.LineTo(448.9f, 1048.0f),
                        PathNode.QuadTo(433.7f, 1048.0f, 425.3f, 1038.2f),
                        PathNode.QuadTo(416.8f, 1028.4f, 416.8f, 1012.9f),
                        PathNode.LineTo(416.8f, 796.4f),
                        PathNode.LineTo(183.9f, 1029.6f),
                        PathNode.QuadTo(173.4f, 1041.0f, 159.1f, 1042.5f),
                        PathNode.QuadTo(144.7f, 1044.0f, 132.3f, 1031.6f),
                        PathNode.LineTo(111.3f, 1010.6f),
                        PathNode.QuadTo(98.8f, 998.2f, 99.8f, 983.8f),
                        PathNode.QuadTo(100.8f, 969.4f, 111.3f, 959.0f),
                        PathNode.LineTo(345.5f, 725.0f),
                        PathNode.LineTo(127.9f, 725.0f),
                        PathNode.QuadTo(112.7f, 725.0f, 103.3f, 715.6f),
                        PathNode.QuadTo(93.8f, 706.2f, 93.8f, 693.9f),
                        PathNode.LineTo(93.8f, 653.9f),
                        PathNode.QuadTo(93.8f, 640.7f, 103.3f, 631.8f),
                        PathNode.QuadTo(112.7f, 622.8f, 127.9f, 622.8f),
                        PathNode.LineTo(451.7f, 622.8f),
                        PathNode.QuadTo(484.0f, 622.8f, 501.5f, 640.1f),
                        PathNode.QuadTo(519.0f, 657.3f, 519.0f, 689.0f),
                        PathNode.Close,
                        PathNode.MoveTo(623.8f, 453.8f),
                        PathNode.LineTo(623.8f, 129.9f),
                        PathNode.QuadTo(623.8f, 114.4f, 632.3f, 104.6f),
                        PathNode.QuadTo(640.7f, 94.8f, 655.9f, 94.8f),
                        PathNode.LineTo(693.9f, 94.8f),
                        PathNode.QuadTo(709.2f, 94.8f, 717.6f, 104.6f),
                        PathNode.QuadTo(726.0f, 114.4f, 726.0f, 129.9f),
                        PathNode.LineTo(726.0f, 346.5f),
                        PathNode.LineTo(959.0f, 113.3f),
                        PathNode.QuadTo(969.4f, 101.8f, 983.8f, 100.3f),
                        PathNode.QuadTo(998.2f, 98.8f, 1010.6f, 111.3f),
                        PathNode.LineTo(1031.6f, 132.3f),
                        PathNode.QuadTo(1044.0f, 144.7f, 1043.0f, 159.1f),
                        PathNode.QuadTo(1042.0f, 173.4f, 1031.6f, 183.9f),
                        PathNode.LineTo(797.4f, 417.8f),
                        PathNode.LineTo(1014.9f, 417.8f),
                        PathNode.QuadTo(1030.2f, 417.8f, 1039.6f, 427.3f),
                        PathNode.QuadTo(1049.0f, 436.7f, 1049.0f, 448.9f),
                        PathNode.LineTo(1049.0f, 488.9f),
                        PathNode.QuadTo(1049.0f, 502.2f, 1039.6f, 511.1f),
                        PathNode.QuadTo(1030.2f, 520.0f, 1014.9f, 520.0f),
                        PathNode.LineTo(691.1f, 520.0f),
                        PathNode.QuadTo(658.9f, 520.0f, 641.3f, 502.8f),
                        PathNode.QuadTo(623.8f, 485.5f, 623.8f, 453.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomOutMedium!!
    }

private var _zoomOutMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ZoomOut: ImageVector
    get() {
        if (_zoomOutDemibold != null) return _zoomOutDemibold!!
        _zoomOutDemibold = ImageVector.Builder(
            name = "ZoomOut.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(524.7f, 687.7f),
                        PathNode.LineTo(524.7f, 1012.9f),
                        PathNode.QuadTo(524.7f, 1030.9f, 514.6f, 1042.3f),
                        PathNode.QuadTo(504.4f, 1053.7f, 486.9f, 1053.7f),
                        PathNode.LineTo(448.9f, 1053.7f),
                        PathNode.QuadTo(431.4f, 1053.7f, 421.3f, 1042.3f),
                        PathNode.QuadTo(411.1f, 1030.9f, 411.1f, 1012.9f),
                        PathNode.LineTo(411.1f, 810.0f),
                        PathNode.LineTo(188.0f, 1033.6f),
                        PathNode.QuadTo(175.9f, 1046.7f, 159.2f, 1048.2f),
                        PathNode.QuadTo(142.4f, 1049.7f, 128.3f, 1035.6f),
                        PathNode.LineTo(107.3f, 1014.6f),
                        PathNode.QuadTo(93.1f, 1000.4f, 94.1f, 983.7f),
                        PathNode.QuadTo(95.1f, 967.0f, 107.3f, 954.8f),
                        PathNode.LineTo(331.8f, 730.7f),
                        PathNode.LineTo(127.9f, 730.7f),
                        PathNode.QuadTo(110.4f, 730.7f, 99.3f, 719.6f),
                        PathNode.QuadTo(88.1f, 708.4f, 88.1f, 693.9f),
                        PathNode.LineTo(88.1f, 653.9f),
                        PathNode.QuadTo(88.1f, 638.4f, 99.3f, 627.8f),
                        PathNode.QuadTo(110.4f, 617.1f, 127.9f, 617.1f),
                        PathNode.LineTo(452.3f, 617.1f),
                        PathNode.QuadTo(486.8f, 617.1f, 505.8f, 635.6f),
                        PathNode.QuadTo(524.7f, 654.1f, 524.7f, 687.7f),
                        PathNode.Close,
                        PathNode.MoveTo(618.1f, 455.1f),
                        PathNode.LineTo(618.1f, 129.9f),
                        PathNode.QuadTo(618.1f, 112.0f, 628.3f, 100.5f),
                        PathNode.QuadTo(638.4f, 89.1f, 655.9f, 89.1f),
                        PathNode.LineTo(693.9f, 89.1f),
                        PathNode.QuadTo(711.4f, 89.1f, 721.6f, 100.5f),
                        PathNode.QuadTo(731.7f, 112.0f, 731.7f, 129.9f),
                        PathNode.LineTo(731.7f, 332.8f),
                        PathNode.LineTo(954.8f, 109.3f),
                        PathNode.QuadTo(967.0f, 96.1f, 983.7f, 94.6f),
                        PathNode.QuadTo(1000.4f, 93.1f, 1014.6f, 107.3f),
                        PathNode.LineTo(1035.6f, 128.3f),
                        PathNode.QuadTo(1049.7f, 142.4f, 1048.7f, 159.2f),
                        PathNode.QuadTo(1047.7f, 175.9f, 1035.6f, 188.0f),
                        PathNode.LineTo(811.0f, 412.1f),
                        PathNode.LineTo(1014.9f, 412.1f),
                        PathNode.QuadTo(1032.4f, 412.1f, 1043.6f, 423.3f),
                        PathNode.QuadTo(1054.7f, 434.4f, 1054.7f, 448.9f),
                        PathNode.LineTo(1054.7f, 488.9f),
                        PathNode.QuadTo(1054.7f, 504.4f, 1043.6f, 515.1f),
                        PathNode.QuadTo(1032.4f, 525.7f, 1014.9f, 525.7f),
                        PathNode.LineTo(690.6f, 525.7f),
                        PathNode.QuadTo(656.0f, 525.7f, 637.1f, 507.2f),
                        PathNode.QuadTo(618.1f, 488.7f, 618.1f, 455.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _zoomOutDemibold!!
    }

private var _zoomOutDemibold: ImageVector? = null
