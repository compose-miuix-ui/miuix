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

val MiuixIcons.FileDownloads: ImageVector
    get() = MiuixIcons.Regular.FileDownloads

val MiuixIcons.Light.FileDownloads: ImageVector
    get() {
        if (_filedownloadsLight != null) return _filedownloadsLight!!
        _filedownloadsLight = ImageVector.Builder(
            name = "FileDownloads.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1080.0f,
            viewportHeight = 1080.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1080.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(908.0f, 106.0f),
                        PathNode.QuadTo(952.0f, 127.0f, 974.0f, 171.0f),
                        PathNode.QuadTo(985.0f, 194.0f, 987.5f, 226.5f),
                        PathNode.QuadTo(990.0f, 259.0f, 990.0f, 339.0f),
                        PathNode.VerticalTo(740.0f),
                        PathNode.QuadTo(990.0f, 821.0f, 987.5f, 853.5f),
                        PathNode.QuadTo(985.0f, 886.0f, 974.0f, 908.0f),
                        PathNode.QuadTo(952.0f, 953.0f, 908.0f, 974.0f),
                        PathNode.QuadTo(886.0f, 985.0f, 853.5f, 987.5f),
                        PathNode.QuadTo(821.0f, 990.0f, 740.0f, 990.0f),
                        PathNode.HorizontalTo(340.0f),
                        PathNode.QuadTo(260.0f, 990.0f, 227.0f, 987.5f),
                        PathNode.QuadTo(194.0f, 985.0f, 172.0f, 974.0f),
                        PathNode.QuadTo(129.0f, 953.0f, 106.0f, 908.0f),
                        PathNode.QuadTo(95.0f, 886.0f, 92.5f, 853.5f),
                        PathNode.QuadTo(90.0f, 821.0f, 90.0f, 740.0f),
                        PathNode.VerticalTo(339.0f),
                        PathNode.QuadTo(90.0f, 259.0f, 92.5f, 226.5f),
                        PathNode.QuadTo(95.0f, 194.0f, 106.0f, 171.0f),
                        PathNode.QuadTo(128.0f, 127.0f, 172.0f, 106.0f),
                        PathNode.QuadTo(194.0f, 95.0f, 227.0f, 92.5f),
                        PathNode.QuadTo(260.0f, 90.0f, 340.0f, 90.0f),
                        PathNode.HorizontalTo(740.0f),
                        PathNode.QuadTo(821.0f, 90.0f, 853.5f, 92.5f),
                        PathNode.QuadTo(886.0f, 95.0f, 908.0f, 106.0f),
                        PathNode.Close,
                        PathNode.MoveTo(340.0f, 505.0f),
                        PathNode.LineTo(348.0f, 513.0f),
                        PathNode.QuadTo(355.0f, 520.0f, 362.5f, 520.0f),
                        PathNode.QuadTo(370.0f, 520.0f, 375.0f, 514.0f),
                        PathNode.LineTo(516.0f, 375.0f),
                        PathNode.VerticalTo(809.0f),
                        PathNode.QuadTo(516.0f, 816.0f, 521.5f, 820.5f),
                        PathNode.QuadTo(527.0f, 825.0f, 534.0f, 825.0f),
                        PathNode.HorizontalTo(546.0f),
                        PathNode.QuadTo(553.0f, 825.0f, 558.5f, 820.5f),
                        PathNode.QuadTo(564.0f, 816.0f, 564.0f, 809.0f),
                        PathNode.VerticalTo(375.0f),
                        PathNode.LineTo(705.0f, 514.0f),
                        PathNode.QuadTo(710.0f, 519.0f, 717.5f, 519.5f),
                        PathNode.QuadTo(725.0f, 520.0f, 730.0f, 514.0f),
                        PathNode.LineTo(740.0f, 505.0f),
                        PathNode.QuadTo(745.0f, 500.0f, 745.0f, 492.5f),
                        PathNode.QuadTo(745.0f, 485.0f, 740.0f, 480.0f),
                        PathNode.LineTo(557.0f, 298.0f),
                        PathNode.QuadTo(549.0f, 289.0f, 540.5f, 289.0f),
                        PathNode.QuadTo(532.0f, 289.0f, 523.0f, 298.0f),
                        PathNode.LineTo(341.0f, 482.0f),
                        PathNode.QuadTo(336.0f, 487.0f, 335.5f, 493.5f),
                        PathNode.QuadTo(335.0f, 500.0f, 340.0f, 505.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _filedownloadsLight!!
    }

private var _filedownloadsLight: ImageVector? = null

val MiuixIcons.Normal.FileDownloads: ImageVector
    get() {
        if (_filedownloadsNormal != null) return _filedownloadsNormal!!
        _filedownloadsNormal = ImageVector.Builder(
            name = "FileDownloads.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1106.4f,
            viewportHeight = 1106.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1106.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(926.0f, 109.6f),
                        PathNode.QuadTo(972.8f, 132.6f, 996.8f, 179.4f),
                        PathNode.QuadTo(1008.5f, 203.8f, 1011.4f, 237.6f),
                        PathNode.QuadTo(1014.2f, 271.5f, 1014.2f, 352.2f),
                        PathNode.VerticalTo(753.2f),
                        PathNode.QuadTo(1014.2f, 834.9f, 1011.4f, 868.8f),
                        PathNode.QuadTo(1008.5f, 902.6f, 996.8f, 926.0f),
                        PathNode.QuadTo(972.8f, 973.8f, 926.0f, 996.8f),
                        PathNode.QuadTo(902.6f, 1008.5f, 868.8f, 1011.4f),
                        PathNode.QuadTo(834.9f, 1014.2f, 753.2f, 1014.2f),
                        PathNode.HorizontalTo(353.2f),
                        PathNode.QuadTo(272.5f, 1014.2f, 238.1f, 1011.4f),
                        PathNode.QuadTo(203.8f, 1008.5f, 180.4f, 996.8f),
                        PathNode.QuadTo(133.9f, 973.8f, 109.6f, 926.0f),
                        PathNode.QuadTo(97.9f, 902.6f, 95.0f, 868.8f),
                        PathNode.QuadTo(92.2f, 834.9f, 92.2f, 753.2f),
                        PathNode.VerticalTo(352.2f),
                        PathNode.QuadTo(92.2f, 271.5f, 95.0f, 237.6f),
                        PathNode.QuadTo(97.9f, 203.8f, 109.6f, 179.4f),
                        PathNode.QuadTo(133.6f, 132.6f, 180.4f, 109.6f),
                        PathNode.QuadTo(203.8f, 97.9f, 238.1f, 95.0f),
                        PathNode.QuadTo(272.5f, 92.2f, 353.2f, 92.2f),
                        PathNode.HorizontalTo(753.2f),
                        PathNode.QuadTo(834.9f, 92.2f, 868.8f, 95.0f),
                        PathNode.QuadTo(902.6f, 97.9f, 926.0f, 109.6f),
                        PathNode.Close,
                        PathNode.MoveTo(347.7f, 520.9f),
                        PathNode.LineTo(356.4f, 529.6f),
                        PathNode.QuadTo(364.8f, 539.4f, 375.4f, 539.4f),
                        PathNode.QuadTo(385.9f, 539.4f, 392.3f, 532.0f),
                        PathNode.LineTo(523.0f, 402.6f),
                        PathNode.VerticalTo(822.9f),
                        PathNode.QuadTo(523.0f, 832.6f, 530.6f, 838.5f),
                        PathNode.QuadTo(538.1f, 844.4f, 547.2f, 844.4f),
                        PathNode.HorizontalTo(559.2f),
                        PathNode.QuadTo(567.6f, 844.4f, 575.5f, 838.2f),
                        PathNode.QuadTo(583.4f, 831.9f, 583.4f, 822.9f),
                        PathNode.VerticalTo(402.6f),
                        PathNode.LineTo(714.1f, 532.0f),
                        PathNode.QuadTo(721.1f, 539.1f, 730.7f, 539.2f),
                        PathNode.QuadTo(740.3f, 539.4f, 747.3f, 532.0f),
                        PathNode.LineTo(758.0f, 521.6f),
                        PathNode.QuadTo(764.4f, 515.3f, 764.0f, 505.4f),
                        PathNode.QuadTo(763.7f, 495.4f, 757.3f, 489.1f),
                        PathNode.LineTo(572.9f, 305.0f),
                        PathNode.QuadTo(563.6f, 295.3f, 553.4f, 295.7f),
                        PathNode.QuadTo(543.1f, 296.0f, 533.4f, 305.7f),
                        PathNode.LineTo(348.7f, 491.1f),
                        PathNode.QuadTo(342.3f, 497.4f, 342.2f, 506.4f),
                        PathNode.QuadTo(342.0f, 515.3f, 347.7f, 520.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _filedownloadsNormal!!
    }

private var _filedownloadsNormal: ImageVector? = null

val MiuixIcons.Regular.FileDownloads: ImageVector
    get() {
        if (_filedownloadsRegular != null) return _filedownloadsRegular!!
        _filedownloadsRegular = ImageVector.Builder(
            name = "FileDownloads.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1118.4f,
            viewportHeight = 1118.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1118.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(934.2f, 111.2f),
                        PathNode.QuadTo(982.2f, 135.2f, 1007.2f, 183.2f),
                        PathNode.QuadTo(1019.2f, 208.2f, 1022.2f, 242.7f),
                        PathNode.QuadTo(1025.2f, 277.2f, 1025.2f, 358.2f),
                        PathNode.VerticalTo(759.2f),
                        PathNode.QuadTo(1025.2f, 841.2f, 1022.2f, 875.7f),
                        PathNode.QuadTo(1019.2f, 910.2f, 1007.2f, 934.2f),
                        PathNode.QuadTo(982.2f, 983.2f, 934.2f, 1007.2f),
                        PathNode.QuadTo(910.2f, 1019.2f, 875.7f, 1022.2f),
                        PathNode.QuadTo(841.2f, 1025.2f, 759.2f, 1025.2f),
                        PathNode.HorizontalTo(359.2f),
                        PathNode.QuadTo(278.2f, 1025.2f, 243.2f, 1022.2f),
                        PathNode.QuadTo(208.2f, 1019.2f, 184.2f, 1007.2f),
                        PathNode.QuadTo(136.2f, 983.2f, 111.2f, 934.2f),
                        PathNode.QuadTo(99.2f, 910.2f, 96.2f, 875.7f),
                        PathNode.QuadTo(93.2f, 841.2f, 93.2f, 759.2f),
                        PathNode.VerticalTo(358.2f),
                        PathNode.QuadTo(93.2f, 277.2f, 96.2f, 242.7f),
                        PathNode.QuadTo(99.2f, 208.2f, 111.2f, 183.2f),
                        PathNode.QuadTo(136.2f, 135.2f, 184.2f, 111.2f),
                        PathNode.QuadTo(208.2f, 99.2f, 243.2f, 96.2f),
                        PathNode.QuadTo(278.2f, 93.2f, 359.2f, 93.2f),
                        PathNode.HorizontalTo(759.2f),
                        PathNode.QuadTo(841.2f, 93.2f, 875.7f, 96.2f),
                        PathNode.QuadTo(910.2f, 99.2f, 934.2f, 111.2f),
                        PathNode.Close,
                        PathNode.MoveTo(351.2f, 528.2f),
                        PathNode.LineTo(360.2f, 537.2f),
                        PathNode.QuadTo(369.2f, 548.2f, 381.2f, 548.2f),
                        PathNode.QuadTo(393.2f, 548.2f, 400.2f, 540.2f),
                        PathNode.LineTo(526.2f, 415.2f),
                        PathNode.VerticalTo(829.2f),
                        PathNode.QuadTo(526.2f, 840.2f, 534.7f, 846.7f),
                        PathNode.QuadTo(543.2f, 853.2f, 553.2f, 853.2f),
                        PathNode.HorizontalTo(565.2f),
                        PathNode.QuadTo(574.2f, 853.2f, 583.2f, 846.2f),
                        PathNode.QuadTo(592.2f, 839.2f, 592.2f, 829.2f),
                        PathNode.VerticalTo(415.2f),
                        PathNode.LineTo(718.2f, 540.2f),
                        PathNode.QuadTo(726.2f, 548.2f, 736.7f, 548.2f),
                        PathNode.QuadTo(747.2f, 548.2f, 755.2f, 540.2f),
                        PathNode.LineTo(766.2f, 529.2f),
                        PathNode.QuadTo(773.2f, 522.2f, 772.7f, 511.2f),
                        PathNode.QuadTo(772.2f, 500.2f, 765.2f, 493.2f),
                        PathNode.LineTo(580.2f, 308.2f),
                        PathNode.QuadTo(570.2f, 298.2f, 559.2f, 298.7f),
                        PathNode.QuadTo(548.2f, 299.2f, 538.2f, 309.2f),
                        PathNode.LineTo(352.2f, 495.2f),
                        PathNode.QuadTo(345.2f, 502.2f, 345.2f, 512.2f),
                        PathNode.QuadTo(345.2f, 522.2f, 351.2f, 528.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _filedownloadsRegular!!
    }

private var _filedownloadsRegular: ImageVector? = null

val MiuixIcons.Medium.FileDownloads: ImageVector
    get() {
        if (_filedownloadsMedium != null) return _filedownloadsMedium!!
        _filedownloadsMedium = ImageVector.Builder(
            name = "FileDownloads.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1138.2f,
            viewportHeight = 1138.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1138.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(947.6f, 114.0f),
                        PathNode.QuadTo(998.6f, 139.2f, 1024.1f, 189.6f),
                        PathNode.QuadTo(1037.3f, 215.7f, 1040.3f, 251.1f),
                        PathNode.QuadTo(1043.3f, 286.5f, 1043.3f, 368.1f),
                        PathNode.VerticalTo(769.1f),
                        PathNode.QuadTo(1043.3f, 851.1f, 1040.3f, 886.8f),
                        PathNode.QuadTo(1037.3f, 922.4f, 1024.1f, 947.6f),
                        PathNode.QuadTo(998.0f, 999.6f, 947.6f, 1024.1f),
                        PathNode.QuadTo(922.4f, 1037.3f, 886.8f, 1040.3f),
                        PathNode.QuadTo(851.1f, 1043.3f, 769.1f, 1043.3f),
                        PathNode.HorizontalTo(369.1f),
                        PathNode.QuadTo(287.5f, 1043.3f, 251.6f, 1040.3f),
                        PathNode.QuadTo(215.7f, 1037.3f, 190.6f, 1024.1f),
                        PathNode.QuadTo(140.2f, 999.6f, 114.0f, 947.6f),
                        PathNode.QuadTo(100.8f, 922.4f, 97.8f, 886.8f),
                        PathNode.QuadTo(94.8f, 851.1f, 94.8f, 769.1f),
                        PathNode.VerticalTo(368.1f),
                        PathNode.QuadTo(94.8f, 286.5f, 97.8f, 251.1f),
                        PathNode.QuadTo(100.8f, 215.7f, 114.0f, 189.6f),
                        PathNode.QuadTo(139.6f, 139.2f, 190.6f, 114.0f),
                        PathNode.QuadTo(215.7f, 100.8f, 251.6f, 97.8f),
                        PathNode.QuadTo(287.5f, 94.8f, 369.1f, 94.8f),
                        PathNode.HorizontalTo(769.1f),
                        PathNode.QuadTo(851.1f, 94.8f, 886.8f, 97.8f),
                        PathNode.QuadTo(922.4f, 100.8f, 947.6f, 114.0f),
                        PathNode.Close,
                        PathNode.MoveTo(354.0f, 541.6f),
                        PathNode.LineTo(366.6f, 555.3f),
                        PathNode.QuadTo(377.3f, 567.5f, 390.5f, 567.8f),
                        PathNode.QuadTo(403.7f, 568.1f, 413.0f, 558.3f),
                        PathNode.LineTo(527.8f, 444.5f),
                        PathNode.VerticalTo(841.4f),
                        PathNode.QuadTo(527.8f, 855.4f, 537.2f, 863.3f),
                        PathNode.QuadTo(546.6f, 871.3f, 559.6f, 871.3f),
                        PathNode.HorizontalTo(578.6f),
                        PathNode.QuadTo(591.1f, 871.3f, 600.7f, 863.1f),
                        PathNode.QuadTo(610.3f, 855.0f, 610.3f, 841.4f),
                        PathNode.VerticalTo(444.5f),
                        PathNode.LineTo(724.6f, 557.7f),
                        PathNode.QuadTo(734.3f, 567.5f, 746.6f, 567.8f),
                        PathNode.QuadTo(758.8f, 568.1f, 768.6f, 557.7f),
                        PathNode.LineTo(783.7f, 543.2f),
                        PathNode.QuadTo(792.5f, 533.8f, 792.3f, 521.4f),
                        PathNode.QuadTo(792.1f, 508.9f, 782.7f, 499.6f),
                        PathNode.LineTo(594.2f, 311.0f),
                        PathNode.QuadTo(582.4f, 299.3f, 569.7f, 299.5f),
                        PathNode.QuadTo(556.9f, 299.7f, 544.6f, 311.4f),
                        PathNode.LineTo(355.0f, 500.4f),
                        PathNode.QuadTo(345.7f, 509.1f, 345.7f, 520.9f),
                        PathNode.QuadTo(345.7f, 532.7f, 354.0f, 541.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _filedownloadsMedium!!
    }

private var _filedownloadsMedium: ImageVector? = null

val MiuixIcons.Demibold.FileDownloads: ImageVector
    get() {
        if (_filedownloadsDemibold != null) return _filedownloadsDemibold!!
        _filedownloadsDemibold = ImageVector.Builder(
            name = "FileDownloads.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1152.0f,
            viewportHeight = 1152.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1152.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(957.0f, 116.0f),
                        PathNode.QuadTo(1010.0f, 142.0f, 1036.0f, 194.0f),
                        PathNode.QuadTo(1050.0f, 221.0f, 1053.0f, 257.0f),
                        PathNode.QuadTo(1056.0f, 293.0f, 1056.0f, 375.0f),
                        PathNode.VerticalTo(776.0f),
                        PathNode.QuadTo(1056.0f, 858.0f, 1053.0f, 894.5f),
                        PathNode.QuadTo(1050.0f, 931.0f, 1036.0f, 957.0f),
                        PathNode.QuadTo(1009.0f, 1011.0f, 957.0f, 1036.0f),
                        PathNode.QuadTo(931.0f, 1050.0f, 894.5f, 1053.0f),
                        PathNode.QuadTo(858.0f, 1056.0f, 776.0f, 1056.0f),
                        PathNode.HorizontalTo(376.0f),
                        PathNode.QuadTo(294.0f, 1056.0f, 257.5f, 1053.0f),
                        PathNode.QuadTo(221.0f, 1050.0f, 195.0f, 1036.0f),
                        PathNode.QuadTo(143.0f, 1011.0f, 116.0f, 957.0f),
                        PathNode.QuadTo(102.0f, 931.0f, 99.0f, 894.5f),
                        PathNode.QuadTo(96.0f, 858.0f, 96.0f, 776.0f),
                        PathNode.VerticalTo(375.0f),
                        PathNode.QuadTo(96.0f, 293.0f, 99.0f, 257.0f),
                        PathNode.QuadTo(102.0f, 221.0f, 116.0f, 194.0f),
                        PathNode.QuadTo(142.0f, 142.0f, 195.0f, 116.0f),
                        PathNode.QuadTo(221.0f, 102.0f, 257.5f, 99.0f),
                        PathNode.QuadTo(294.0f, 96.0f, 376.0f, 96.0f),
                        PathNode.HorizontalTo(776.0f),
                        PathNode.QuadTo(858.0f, 96.0f, 894.5f, 99.0f),
                        PathNode.QuadTo(931.0f, 102.0f, 957.0f, 116.0f),
                        PathNode.Close,
                        PathNode.MoveTo(356.0f, 551.0f),
                        PathNode.LineTo(371.0f, 568.0f),
                        PathNode.QuadTo(383.0f, 581.0f, 397.0f, 581.5f),
                        PathNode.QuadTo(411.0f, 582.0f, 422.0f, 571.0f),
                        PathNode.LineTo(529.0f, 465.0f),
                        PathNode.VerticalTo(850.0f),
                        PathNode.QuadTo(529.0f, 866.0f, 539.0f, 875.0f),
                        PathNode.QuadTo(549.0f, 884.0f, 564.0f, 884.0f),
                        PathNode.HorizontalTo(588.0f),
                        PathNode.QuadTo(603.0f, 884.0f, 613.0f, 875.0f),
                        PathNode.QuadTo(623.0f, 866.0f, 623.0f, 850.0f),
                        PathNode.VerticalTo(465.0f),
                        PathNode.LineTo(729.0f, 570.0f),
                        PathNode.QuadTo(740.0f, 581.0f, 753.5f, 581.5f),
                        PathNode.QuadTo(767.0f, 582.0f, 778.0f, 570.0f),
                        PathNode.LineTo(796.0f, 553.0f),
                        PathNode.QuadTo(806.0f, 542.0f, 806.0f, 528.5f),
                        PathNode.QuadTo(806.0f, 515.0f, 795.0f, 504.0f),
                        PathNode.LineTo(604.0f, 313.0f),
                        PathNode.QuadTo(591.0f, 300.0f, 577.0f, 300.0f),
                        PathNode.QuadTo(563.0f, 300.0f, 549.0f, 313.0f),
                        PathNode.LineTo(357.0f, 504.0f),
                        PathNode.QuadTo(346.0f, 514.0f, 346.0f, 527.0f),
                        PathNode.QuadTo(346.0f, 540.0f, 356.0f, 551.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _filedownloadsDemibold!!
    }

private var _filedownloadsDemibold: ImageVector? = null
