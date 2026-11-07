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

val MiuixIcons.Glass.Clear: ImageVector
    get() = MiuixIcons.Glass.Regular.Clear

val MiuixIcons.Glass.Light.Clear: ImageVector
    get() {
        if (_clearLight != null) return _clearLight!!
        _clearLight = ImageVector.Builder(
            name = "Clear.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(644.6f, 295.1f),
                        PathNode.LineTo(989.6f, 640.1f),
                        PathNode.QuadTo(1009.0f, 658.9f, 1015.6f, 683.8f),
                        PathNode.QuadTo(1022.2f, 708.8f, 1015.6f, 733.2f),
                        PathNode.QuadTo(1009.0f, 757.7f, 989.6f, 776.5f),
                        PathNode.LineTo(754.6f, 1013.5f),
                        PathNode.QuadTo(735.9f, 1032.2f, 711.1f, 1038.6f),
                        PathNode.QuadTo(686.4f, 1044.9f, 661.7f, 1038.6f),
                        PathNode.QuadTo(637.1f, 1032.2f, 618.3f, 1013.5f),
                        PathNode.LineTo(167.3f, 562.5f),
                        PathNode.QuadTo(148.1f, 542.8f, 141.5f, 518.8f),
                        PathNode.QuadTo(135.0f, 494.8f, 141.5f, 470.8f),
                        PathNode.QuadTo(148.1f, 446.8f, 167.3f, 427.1f),
                        PathNode.LineTo(299.3f, 295.1f),
                        PathNode.QuadTo(313.0f, 281.9f, 330.5f, 274.5f),
                        PathNode.QuadTo(348.1f, 267.1f, 367.0f, 267.1f),
                        PathNode.LineTo(577.9f, 267.1f),
                        PathNode.QuadTo(597.6f, 267.1f, 613.9f, 273.8f),
                        PathNode.QuadTo(630.1f, 280.6f, 644.6f, 295.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1018.0f, 118.7f),
                        PathNode.LineTo(1018.0f, 141.9f),
                        PathNode.QuadTo(1018.0f, 149.3f, 1012.6f, 154.4f),
                        PathNode.QuadTo(1007.3f, 159.5f, 999.9f, 159.5f),
                        PathNode.LineTo(142.1f, 159.5f),
                        PathNode.QuadTo(134.1f, 159.5f, 129.0f, 154.4f),
                        PathNode.QuadTo(124.0f, 149.3f, 124.0f, 141.9f),
                        PathNode.LineTo(124.0f, 118.7f),
                        PathNode.QuadTo(124.0f, 111.2f, 129.0f, 106.2f),
                        PathNode.QuadTo(134.1f, 101.1f, 142.1f, 101.1f),
                        PathNode.LineTo(999.9f, 101.1f),
                        PathNode.QuadTo(1007.3f, 101.1f, 1012.6f, 106.2f),
                        PathNode.QuadTo(1018.0f, 111.2f, 1018.0f, 118.7f),
                        PathNode.Close,
                        PathNode.MoveTo(333.6f, 344.0f),
                        PathNode.LineTo(224.4f, 453.7f),
                        PathNode.QuadTo(203.3f, 474.8f, 203.1f, 494.8f),
                        PathNode.QuadTo(202.9f, 514.8f, 224.4f, 535.9f),
                        PathNode.LineTo(339.5f, 650.0f),
                        PathNode.QuadTo(348.5f, 659.0f, 356.5f, 650.5f),
                        PathNode.LineTo(628.6f, 378.3f),
                        PathNode.QuadTo(637.6f, 369.9f, 628.2f, 361.3f),
                        PathNode.LineTo(607.6f, 341.8f),
                        PathNode.QuadTo(590.6f, 326.5f, 571.4f, 326.5f),
                        PathNode.LineTo(377.0f, 326.5f),
                        PathNode.QuadTo(350.7f, 326.5f, 333.6f, 344.0f),
                        PathNode.Close,
                        PathNode.MoveTo(669.9f, 420.2f),
                        PathNode.LineTo(398.3f, 691.8f),
                        PathNode.QuadTo(394.3f, 696.2f, 394.3f, 700.3f),
                        PathNode.QuadTo(394.3f, 704.3f, 398.3f, 708.3f),
                        PathNode.LineTo(644.9f, 956.3f),
                        PathNode.QuadTo(665.5f, 977.0f, 686.2f, 977.0f),
                        PathNode.QuadTo(707.0f, 977.0f, 727.1f, 956.3f),
                        PathNode.LineTo(934.5f, 748.9f),
                        PathNode.QuadTo(953.9f, 730.6f, 953.9f, 708.8f),
                        PathNode.QuadTo(953.9f, 687.0f, 934.5f, 667.7f),
                        PathNode.LineTo(685.5f, 420.7f),
                        PathNode.QuadTo(681.5f, 416.7f, 677.5f, 416.7f),
                        PathNode.QuadTo(673.4f, 416.7f, 669.9f, 420.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearLight!!
    }

private var _clearLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Clear: ImageVector
    get() {
        if (_clearNormal != null) return _clearNormal!!
        _clearNormal = ImageVector.Builder(
            name = "Clear.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(646.5f, 289.0f),
                        PathNode.LineTo(991.5f, 634.0f),
                        PathNode.QuadTo(1012.6f, 654.3f, 1019.9f, 681.7f),
                        PathNode.QuadTo(1027.1f, 709.1f, 1019.9f, 735.9f),
                        PathNode.QuadTo(1012.6f, 762.8f, 991.5f, 783.1f),
                        PathNode.LineTo(756.5f, 1020.1f),
                        PathNode.QuadTo(736.2f, 1040.4f, 708.8f, 1047.2f),
                        PathNode.QuadTo(681.5f, 1054.0f, 654.6f, 1047.2f),
                        PathNode.QuadTo(627.6f, 1040.4f, 607.3f, 1020.1f),
                        PathNode.LineTo(156.3f, 569.1f),
                        PathNode.QuadTo(135.9f, 548.6f, 129.0f, 521.8f),
                        PathNode.QuadTo(122.1f, 495.1f, 129.0f, 468.3f),
                        PathNode.QuadTo(135.9f, 441.6f, 156.3f, 421.0f),
                        PathNode.LineTo(288.3f, 289.0f),
                        PathNode.QuadTo(302.9f, 274.5f, 322.1f, 266.4f),
                        PathNode.QuadTo(341.4f, 258.2f, 362.4f, 258.2f),
                        PathNode.LineTo(572.7f, 258.2f),
                        PathNode.QuadTo(594.0f, 258.2f, 612.8f, 266.2f),
                        PathNode.QuadTo(631.6f, 274.1f, 646.5f, 289.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1013.4f, 115.6f),
                        PathNode.LineTo(1013.4f, 145.5f),
                        PathNode.QuadTo(1013.4f, 155.4f, 1006.2f, 162.2f),
                        PathNode.QuadTo(999.1f, 168.9f, 989.2f, 168.9f),
                        PathNode.LineTo(143.6f, 168.9f),
                        PathNode.QuadTo(132.9f, 168.9f, 126.1f, 162.2f),
                        PathNode.QuadTo(119.4f, 155.4f, 119.4f, 145.5f),
                        PathNode.LineTo(119.4f, 115.6f),
                        PathNode.QuadTo(119.4f, 105.7f, 126.1f, 99.0f),
                        PathNode.QuadTo(132.9f, 92.2f, 143.6f, 92.2f),
                        PathNode.LineTo(989.2f, 92.2f),
                        PathNode.QuadTo(999.1f, 92.2f, 1006.2f, 99.0f),
                        PathNode.QuadTo(1013.4f, 105.7f, 1013.4f, 115.6f),
                        PathNode.Close,
                        PathNode.MoveTo(335.5f, 351.0f),
                        PathNode.LineTo(227.8f, 458.8f),
                        PathNode.QuadTo(211.6f, 475.1f, 211.5f, 495.1f),
                        PathNode.QuadTo(211.4f, 515.1f, 227.8f, 531.3f),
                        PathNode.LineTo(335.2f, 637.8f),
                        PathNode.QuadTo(344.2f, 646.8f, 352.2f, 637.9f),
                        PathNode.LineTo(611.2f, 378.9f),
                        PathNode.QuadTo(620.2f, 370.8f, 611.1f, 361.9f),
                        PathNode.LineTo(598.5f, 350.3f),
                        PathNode.QuadTo(581.5f, 335.9f, 563.8f, 335.9f),
                        PathNode.LineTo(372.4f, 335.9f),
                        PathNode.QuadTo(350.4f, 335.9f, 335.5f, 351.0f),
                        PathNode.Close,
                        PathNode.MoveTo(664.7f, 433.9f),
                        PathNode.LineTo(406.6f, 692.1f),
                        PathNode.QuadTo(402.6f, 696.2f, 402.6f, 700.6f),
                        PathNode.QuadTo(402.6f, 704.9f, 406.6f, 708.9f),
                        PathNode.LineTo(645.2f, 948.7f),
                        PathNode.QuadTo(661.2f, 964.8f, 681.8f, 964.8f),
                        PathNode.QuadTo(702.4f, 964.8f, 717.6f, 948.7f),
                        PathNode.LineTo(922.0f, 744.3f),
                        PathNode.QuadTo(937.7f, 729.6f, 937.7f, 709.1f),
                        PathNode.QuadTo(937.7f, 688.5f, 922.0f, 672.8f),
                        PathNode.LineTo(681.2f, 434.1f),
                        PathNode.QuadTo(677.2f, 430.1f, 672.9f, 430.1f),
                        PathNode.QuadTo(668.5f, 430.1f, 664.7f, 433.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearNormal!!
    }

private var _clearNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Clear: ImageVector
    get() {
        if (_clearRegular != null) return _clearRegular!!
        _clearRegular = ImageVector.Builder(
            name = "Clear.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(647.3f, 286.2f),
                        PathNode.LineTo(992.3f, 631.2f),
                        PathNode.QuadTo(1014.3f, 652.2f, 1021.8f, 680.7f),
                        PathNode.QuadTo(1029.3f, 709.2f, 1021.8f, 737.2f),
                        PathNode.QuadTo(1014.3f, 765.2f, 992.3f, 786.2f),
                        PathNode.LineTo(757.3f, 1023.2f),
                        PathNode.QuadTo(736.3f, 1044.2f, 707.8f, 1051.2f),
                        PathNode.QuadTo(679.3f, 1058.2f, 651.3f, 1051.2f),
                        PathNode.QuadTo(623.3f, 1044.2f, 602.3f, 1023.2f),
                        PathNode.LineTo(151.3f, 572.2f),
                        PathNode.QuadTo(130.3f, 551.2f, 123.3f, 523.2f),
                        PathNode.QuadTo(116.3f, 495.2f, 123.3f, 467.2f),
                        PathNode.QuadTo(130.3f, 439.2f, 151.3f, 418.2f),
                        PathNode.LineTo(283.3f, 286.2f),
                        PathNode.QuadTo(298.3f, 271.2f, 318.3f, 262.7f),
                        PathNode.QuadTo(338.3f, 254.2f, 360.3f, 254.2f),
                        PathNode.LineTo(570.3f, 254.2f),
                        PathNode.QuadTo(592.3f, 254.2f, 612.3f, 262.7f),
                        PathNode.QuadTo(632.3f, 271.2f, 647.3f, 286.2f),
                        PathNode.Close,
                        PathNode.MoveTo(1011.3f, 114.2f),
                        PathNode.LineTo(1011.3f, 147.2f),
                        PathNode.QuadTo(1011.3f, 158.2f, 1003.3f, 165.7f),
                        PathNode.QuadTo(995.3f, 173.2f, 984.3f, 173.2f),
                        PathNode.LineTo(144.3f, 173.2f),
                        PathNode.QuadTo(132.3f, 173.2f, 124.8f, 165.7f),
                        PathNode.QuadTo(117.3f, 158.2f, 117.3f, 147.2f),
                        PathNode.LineTo(117.3f, 114.2f),
                        PathNode.QuadTo(117.3f, 103.2f, 124.8f, 95.7f),
                        PathNode.QuadTo(132.3f, 88.2f, 144.3f, 88.2f),
                        PathNode.LineTo(984.3f, 88.2f),
                        PathNode.QuadTo(995.3f, 88.2f, 1003.3f, 95.7f),
                        PathNode.QuadTo(1011.3f, 103.2f, 1011.3f, 114.2f),
                        PathNode.Close,
                        PathNode.MoveTo(336.3f, 354.2f),
                        PathNode.LineTo(229.3f, 461.2f),
                        PathNode.QuadTo(215.3f, 475.2f, 215.3f, 495.2f),
                        PathNode.QuadTo(215.3f, 515.2f, 229.3f, 529.2f),
                        PathNode.LineTo(333.3f, 632.2f),
                        PathNode.QuadTo(342.3f, 641.2f, 350.3f, 632.2f),
                        PathNode.LineTo(603.3f, 379.2f),
                        PathNode.QuadTo(612.3f, 371.2f, 603.3f, 362.2f),
                        PathNode.LineTo(594.3f, 354.2f),
                        PathNode.QuadTo(577.3f, 340.2f, 560.3f, 340.2f),
                        PathNode.LineTo(370.3f, 340.2f),
                        PathNode.QuadTo(350.3f, 340.2f, 336.3f, 354.2f),
                        PathNode.Close,
                        PathNode.MoveTo(662.3f, 440.2f),
                        PathNode.LineTo(410.3f, 692.2f),
                        PathNode.QuadTo(406.3f, 696.2f, 406.3f, 700.7f),
                        PathNode.QuadTo(406.3f, 705.2f, 410.3f, 709.2f),
                        PathNode.LineTo(645.3f, 945.2f),
                        PathNode.QuadTo(659.3f, 959.2f, 679.8f, 959.2f),
                        PathNode.QuadTo(700.3f, 959.2f, 713.3f, 945.2f),
                        PathNode.LineTo(916.3f, 742.2f),
                        PathNode.QuadTo(930.3f, 729.2f, 930.3f, 709.2f),
                        PathNode.QuadTo(930.3f, 689.2f, 916.3f, 675.2f),
                        PathNode.LineTo(679.3f, 440.2f),
                        PathNode.QuadTo(675.3f, 436.2f, 670.8f, 436.2f),
                        PathNode.QuadTo(666.3f, 436.2f, 662.3f, 440.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearRegular!!
    }

private var _clearRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Clear: ImageVector
    get() {
        if (_clearMedium != null) return _clearMedium!!
        _clearMedium = ImageVector.Builder(
            name = "Clear.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(653.0f, 283.1f),
                        PathNode.LineTo(998.0f, 628.1f),
                        PathNode.QuadTo(1021.6f, 650.7f, 1029.6f, 681.2f),
                        PathNode.QuadTo(1037.7f, 711.7f, 1029.6f, 741.8f),
                        PathNode.QuadTo(1021.6f, 771.8f, 998.0f, 794.4f),
                        PathNode.LineTo(763.0f, 1031.4f),
                        PathNode.QuadTo(740.4f, 1054.0f, 709.8f, 1061.6f),
                        PathNode.QuadTo(679.3f, 1069.1f, 649.1f, 1061.6f),
                        PathNode.QuadTo(619.0f, 1054.0f, 596.6f, 1031.4f),
                        PathNode.LineTo(145.6f, 580.4f),
                        PathNode.QuadTo(123.0f, 558.1f, 115.5f, 527.9f),
                        PathNode.QuadTo(107.9f, 497.7f, 115.5f, 467.6f),
                        PathNode.QuadTo(123.0f, 437.4f, 145.6f, 415.1f),
                        PathNode.LineTo(277.6f, 283.1f),
                        PathNode.QuadTo(293.7f, 267.0f, 315.2f, 257.8f),
                        PathNode.QuadTo(336.7f, 248.6f, 360.3f, 248.6f),
                        PathNode.LineTo(570.3f, 248.6f),
                        PathNode.QuadTo(593.9f, 248.6f, 615.4f, 257.8f),
                        PathNode.QuadTo(636.9f, 267.0f, 653.0f, 283.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1019.4f, 111.6f),
                        PathNode.LineTo(1019.4f, 144.6f),
                        PathNode.QuadTo(1019.4f, 158.9f, 1009.0f, 168.8f),
                        PathNode.QuadTo(998.5f, 178.7f, 984.3f, 178.7f),
                        PathNode.LineTo(144.3f, 178.7f),
                        PathNode.QuadTo(129.1f, 178.7f, 119.1f, 168.8f),
                        PathNode.QuadTo(109.2f, 158.9f, 109.2f, 144.6f),
                        PathNode.LineTo(109.2f, 111.6f),
                        PathNode.QuadTo(109.2f, 97.4f, 119.1f, 87.4f),
                        PathNode.QuadTo(129.1f, 77.5f, 144.3f, 77.5f),
                        PathNode.LineTo(984.3f, 77.5f),
                        PathNode.QuadTo(998.5f, 77.5f, 1009.0f, 87.4f),
                        PathNode.QuadTo(1019.4f, 97.4f, 1019.4f, 111.6f),
                        PathNode.Close,
                        PathNode.MoveTo(340.4f, 364.3f),
                        PathNode.LineTo(235.0f, 469.4f),
                        PathNode.QuadTo(223.4f, 481.0f, 223.4f, 497.7f),
                        PathNode.QuadTo(223.4f, 514.5f, 235.0f, 526.1f),
                        PathNode.LineTo(333.0f, 623.4f),
                        PathNode.QuadTo(342.0f, 632.4f, 350.0f, 623.4f),
                        PathNode.LineTo(592.2f, 381.2f),
                        PathNode.QuadTo(599.6f, 374.6f, 592.2f, 367.4f),
                        PathNode.LineTo(585.1f, 361.3f),
                        PathNode.QuadTo(572.2f, 350.9f, 557.3f, 350.9f),
                        PathNode.LineTo(373.0f, 350.9f),
                        PathNode.QuadTo(353.8f, 350.9f, 340.4f, 364.3f),
                        PathNode.Close,
                        PathNode.MoveTo(660.1f, 456.3f),
                        PathNode.LineTo(421.7f, 694.7f),
                        PathNode.QuadTo(417.7f, 698.7f, 417.7f, 703.2f),
                        PathNode.QuadTo(417.7f, 707.7f, 421.7f, 711.7f),
                        PathNode.LineTo(651.0f, 942.1f),
                        PathNode.QuadTo(662.5f, 953.6f, 679.7f, 953.6f),
                        PathNode.QuadTo(696.8f, 953.6f, 707.6f, 942.1f),
                        PathNode.LineTo(910.6f, 739.1f),
                        PathNode.QuadTo(922.2f, 728.2f, 922.2f, 711.6f),
                        PathNode.QuadTo(922.2f, 695.0f, 910.6f, 683.4f),
                        PathNode.LineTo(681.2f, 456.0f),
                        PathNode.QuadTo(676.4f, 451.2f, 670.8f, 451.2f),
                        PathNode.QuadTo(665.2f, 451.2f, 660.1f, 456.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearMedium!!
    }

private var _clearMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Clear: ImageVector
    get() {
        if (_clearDemibold != null) return _clearDemibold!!
        _clearDemibold = ImageVector.Builder(
            name = "Clear.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(657.0f, 280.9f),
                        PathNode.LineTo(1002.0f, 625.9f),
                        PathNode.QuadTo(1026.7f, 649.7f, 1035.1f, 681.6f),
                        PathNode.QuadTo(1043.5f, 713.5f, 1035.1f, 745.0f),
                        PathNode.QuadTo(1026.7f, 776.4f, 1002.0f, 800.2f),
                        PathNode.LineTo(767.0f, 1037.2f),
                        PathNode.QuadTo(743.2f, 1060.9f, 711.2f, 1068.9f),
                        PathNode.QuadTo(679.3f, 1076.8f, 647.6f, 1068.9f),
                        PathNode.QuadTo(616.0f, 1060.9f, 592.7f, 1037.2f),
                        PathNode.LineTo(141.7f, 586.2f),
                        PathNode.QuadTo(117.9f, 562.9f, 110.0f, 531.2f),
                        PathNode.QuadTo(102.1f, 499.5f, 110.0f, 467.9f),
                        PathNode.QuadTo(117.9f, 436.2f, 141.7f, 412.9f),
                        PathNode.LineTo(273.7f, 280.9f),
                        PathNode.QuadTo(290.5f, 264.1f, 313.0f, 254.4f),
                        PathNode.QuadTo(335.5f, 244.8f, 360.3f, 244.8f),
                        PathNode.LineTo(570.3f, 244.8f),
                        PathNode.QuadTo(595.1f, 244.8f, 617.6f, 254.4f),
                        PathNode.QuadTo(640.1f, 264.1f, 657.0f, 280.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1025.1f, 109.8f),
                        PathNode.LineTo(1025.1f, 142.8f),
                        PathNode.QuadTo(1025.1f, 159.3f, 1013.0f, 171.0f),
                        PathNode.QuadTo(1000.8f, 182.6f, 984.3f, 182.6f),
                        PathNode.LineTo(144.3f, 182.6f),
                        PathNode.QuadTo(126.8f, 182.6f, 115.2f, 171.0f),
                        PathNode.QuadTo(103.5f, 159.3f, 103.5f, 142.8f),
                        PathNode.LineTo(103.5f, 109.8f),
                        PathNode.QuadTo(103.5f, 93.3f, 115.2f, 81.7f),
                        PathNode.QuadTo(126.8f, 70.0f, 144.3f, 70.0f),
                        PathNode.LineTo(984.3f, 70.0f),
                        PathNode.QuadTo(1000.8f, 70.0f, 1013.0f, 81.7f),
                        PathNode.QuadTo(1025.1f, 93.3f, 1025.1f, 109.8f),
                        PathNode.Close,
                        PathNode.MoveTo(343.2f, 371.4f),
                        PathNode.LineTo(239.0f, 475.2f),
                        PathNode.QuadTo(229.1f, 485.1f, 229.1f, 499.5f),
                        PathNode.QuadTo(229.1f, 514.0f, 239.0f, 523.9f),
                        PathNode.LineTo(332.8f, 617.2f),
                        PathNode.QuadTo(341.8f, 626.2f, 349.8f, 617.2f),
                        PathNode.LineTo(584.5f, 382.6f),
                        PathNode.QuadTo(590.7f, 376.9f, 584.5f, 371.1f),
                        PathNode.LineTo(578.7f, 366.4f),
                        PathNode.QuadTo(568.6f, 358.3f, 555.2f, 358.3f),
                        PathNode.LineTo(374.9f, 358.3f),
                        PathNode.QuadTo(356.3f, 358.3f, 343.2f, 371.4f),
                        PathNode.Close,
                        PathNode.MoveTo(658.6f, 467.5f),
                        PathNode.LineTo(429.6f, 696.5f),
                        PathNode.QuadTo(425.6f, 700.5f, 425.6f, 705.0f),
                        PathNode.QuadTo(425.6f, 709.5f, 429.6f, 713.5f),
                        PathNode.LineTo(655.0f, 939.9f),
                        PathNode.QuadTo(664.8f, 949.8f, 679.6f, 949.8f),
                        PathNode.QuadTo(694.3f, 949.8f, 703.7f, 939.9f),
                        PathNode.LineTo(906.7f, 736.9f),
                        PathNode.QuadTo(916.5f, 727.6f, 916.5f, 713.3f),
                        PathNode.QuadTo(916.5f, 699.1f, 906.7f, 689.2f),
                        PathNode.LineTo(682.5f, 467.1f),
                        PathNode.QuadTo(677.1f, 461.7f, 670.8f, 461.7f),
                        PathNode.QuadTo(664.5f, 461.7f, 658.6f, 467.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearDemibold!!
    }

private var _clearDemibold: ImageVector? = null
