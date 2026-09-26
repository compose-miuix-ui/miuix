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

val MiuixIcons.Glass.FileDownloads: ImageVector
    get() = MiuixIcons.Glass.Regular.FileDownloads

val MiuixIcons.Glass.Light.FileDownloads: ImageVector
    get() {
        if (_fileDownloadsLight != null) return _fileDownloadsLight!!
        _fileDownloadsLight = ImageVector.Builder(
            name = "FileDownloads.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(939.3f, 137.2f),
                        PathNode.QuadTo(982.9f, 159.0f, 1005.7f, 202.5f),
                        PathNode.QuadTo(1016.8f, 224.9f, 1019.3f, 257.6f),
                        PathNode.QuadTo(1021.9f, 290.3f, 1021.9f, 370.4f),
                        PathNode.LineTo(1021.9f, 771.4f),
                        PathNode.QuadTo(1021.9f, 852.1f, 1019.3f, 884.8f),
                        PathNode.QuadTo(1016.8f, 917.5f, 1005.7f, 939.3f),
                        PathNode.QuadTo(982.9f, 983.9f, 939.3f, 1005.7f),
                        PathNode.QuadTo(917.5f, 1016.8f, 884.8f, 1019.3f),
                        PathNode.QuadTo(852.1f, 1021.9f, 771.4f, 1021.9f),
                        PathNode.LineTo(371.4f, 1021.9f),
                        PathNode.QuadTo(291.3f, 1021.9f, 258.3f, 1019.3f),
                        PathNode.QuadTo(225.3f, 1016.8f, 203.5f, 1005.7f),
                        PathNode.QuadTo(160.0f, 983.9f, 137.2f, 939.3f),
                        PathNode.QuadTo(126.1f, 917.5f, 123.5f, 884.8f),
                        PathNode.QuadTo(121.0f, 852.1f, 121.0f, 771.4f),
                        PathNode.LineTo(121.0f, 370.4f),
                        PathNode.QuadTo(121.0f, 290.3f, 123.5f, 257.6f),
                        PathNode.QuadTo(126.1f, 224.9f, 137.2f, 202.5f),
                        PathNode.QuadTo(160.0f, 159.0f, 203.5f, 137.2f),
                        PathNode.QuadTo(225.3f, 126.1f, 258.3f, 123.5f),
                        PathNode.QuadTo(291.3f, 121.0f, 371.4f, 121.0f),
                        PathNode.LineTo(771.4f, 121.0f),
                        PathNode.QuadTo(852.1f, 121.0f, 884.8f, 123.5f),
                        PathNode.QuadTo(917.5f, 126.1f, 939.3f, 137.2f),
                        PathNode.Close,
                        PathNode.MoveTo(371.4f, 536.0f),
                        PathNode.LineTo(379.5f, 544.1f),
                        PathNode.QuadTo(385.4f, 551.1f, 393.4f, 551.1f),
                        PathNode.QuadTo(401.4f, 551.1f, 406.2f, 545.8f),
                        PathNode.LineTo(547.3f, 406.1f),
                        PathNode.LineTo(547.3f, 840.1f),
                        PathNode.QuadTo(547.3f, 847.5f, 552.9f, 852.0f),
                        PathNode.QuadTo(558.5f, 856.5f, 565.4f, 856.5f),
                        PathNode.LineTo(577.4f, 856.5f),
                        PathNode.QuadTo(584.2f, 856.5f, 589.9f, 851.8f),
                        PathNode.QuadTo(595.5f, 847.0f, 595.5f, 840.1f),
                        PathNode.LineTo(595.5f, 406.1f),
                        PathNode.LineTo(736.7f, 545.8f),
                        PathNode.QuadTo(742.0f, 551.1f, 749.2f, 551.1f),
                        PathNode.QuadTo(756.3f, 551.1f, 761.7f, 545.8f),
                        PathNode.LineTo(771.8f, 536.1f),
                        PathNode.QuadTo(776.5f, 531.3f, 776.3f, 523.9f),
                        PathNode.QuadTo(776.0f, 516.4f, 771.2f, 511.7f),
                        PathNode.LineTo(588.9f, 329.3f),
                        PathNode.QuadTo(580.2f, 320.7f, 571.9f, 320.7f),
                        PathNode.QuadTo(563.5f, 320.8f, 554.9f, 329.4f),
                        PathNode.LineTo(372.0f, 513.2f),
                        PathNode.QuadTo(367.2f, 518.4f, 367.2f, 525.1f),
                        PathNode.QuadTo(367.2f, 531.8f, 371.4f, 536.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _fileDownloadsLight!!
    }

private var _fileDownloadsLight: ImageVector? = null

val MiuixIcons.Glass.Normal.FileDownloads: ImageVector
    get() {
        if (_fileDownloadsNormal != null) return _fileDownloadsNormal!!
        _fileDownloadsNormal = ImageVector.Builder(
            name = "FileDownloads.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(944.2f, 127.7f),
                        PathNode.QuadTo(990.8f, 151.0f, 1015.1f, 197.7f),
                        PathNode.QuadTo(1026.8f, 221.8f, 1029.7f, 255.8f),
                        PathNode.QuadTo(1032.6f, 289.7f, 1032.6f, 370.4f),
                        PathNode.LineTo(1032.6f, 771.4f),
                        PathNode.QuadTo(1032.6f, 853.0f, 1029.7f, 887.0f),
                        PathNode.QuadTo(1026.8f, 920.9f, 1015.1f, 944.2f),
                        PathNode.QuadTo(990.8f, 991.8f, 944.2f, 1015.1f),
                        PathNode.QuadTo(920.9f, 1026.8f, 887.0f, 1029.7f),
                        PathNode.QuadTo(853.0f, 1032.6f, 771.4f, 1032.6f),
                        PathNode.LineTo(371.4f, 1032.6f),
                        PathNode.QuadTo(290.7f, 1032.6f, 256.3f, 1029.7f),
                        PathNode.QuadTo(222.0f, 1026.8f, 198.7f, 1015.1f),
                        PathNode.QuadTo(152.0f, 991.8f, 127.7f, 944.2f),
                        PathNode.QuadTo(116.0f, 920.9f, 113.2f, 887.0f),
                        PathNode.QuadTo(110.3f, 853.0f, 110.3f, 771.4f),
                        PathNode.LineTo(110.3f, 370.4f),
                        PathNode.QuadTo(110.3f, 289.7f, 113.2f, 255.8f),
                        PathNode.QuadTo(116.0f, 221.8f, 127.7f, 197.7f),
                        PathNode.QuadTo(152.0f, 151.0f, 198.7f, 127.7f),
                        PathNode.QuadTo(222.0f, 116.0f, 256.3f, 113.2f),
                        PathNode.QuadTo(290.7f, 110.3f, 371.4f, 110.3f),
                        PathNode.LineTo(771.4f, 110.3f),
                        PathNode.QuadTo(853.0f, 110.3f, 887.0f, 113.2f),
                        PathNode.QuadTo(920.9f, 116.0f, 944.2f, 127.7f),
                        PathNode.Close,
                        PathNode.MoveTo(365.9f, 539.0f),
                        PathNode.LineTo(374.7f, 547.8f),
                        PathNode.QuadTo(382.7f, 557.5f, 393.4f, 557.5f),
                        PathNode.QuadTo(404.2f, 557.5f, 410.5f, 550.3f),
                        PathNode.LineTo(541.2f, 420.8f),
                        PathNode.LineTo(541.2f, 841.0f),
                        PathNode.QuadTo(541.2f, 850.9f, 548.8f, 856.8f),
                        PathNode.QuadTo(556.4f, 862.7f, 565.4f, 862.7f),
                        PathNode.LineTo(577.4f, 862.7f),
                        PathNode.QuadTo(585.7f, 862.7f, 593.7f, 856.3f),
                        PathNode.QuadTo(601.7f, 850.0f, 601.7f, 841.0f),
                        PathNode.LineTo(601.7f, 420.8f),
                        PathNode.LineTo(732.4f, 550.3f),
                        PathNode.QuadTo(739.5f, 557.5f, 749.0f, 557.5f),
                        PathNode.QuadTo(758.5f, 557.5f, 765.6f, 550.3f),
                        PathNode.LineTo(776.3f, 539.8f),
                        PathNode.QuadTo(782.7f, 533.5f, 782.2f, 523.6f),
                        PathNode.QuadTo(781.8f, 513.7f, 775.5f, 507.4f),
                        PathNode.LineTo(591.3f, 323.2f),
                        PathNode.QuadTo(581.7f, 313.6f, 571.6f, 314.0f),
                        PathNode.QuadTo(561.4f, 314.3f, 551.8f, 323.9f),
                        PathNode.LineTo(366.8f, 509.2f),
                        PathNode.QuadTo(360.5f, 515.7f, 360.5f, 524.6f),
                        PathNode.QuadTo(360.5f, 533.6f, 365.9f, 539.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _fileDownloadsNormal!!
    }

private var _fileDownloadsNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.FileDownloads: ImageVector
    get() {
        if (_fileDownloadsRegular != null) return _fileDownloadsRegular!!
        _fileDownloadsRegular = ImageVector.Builder(
            name = "FileDownloads.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(946.4f, 123.4f),
                        PathNode.QuadTo(994.4f, 147.4f, 1019.4f, 195.4f),
                        PathNode.QuadTo(1031.4f, 220.4f, 1034.4f, 254.9f),
                        PathNode.QuadTo(1037.4f, 289.4f, 1037.4f, 370.4f),
                        PathNode.LineTo(1037.4f, 771.4f),
                        PathNode.QuadTo(1037.4f, 853.4f, 1034.4f, 887.9f),
                        PathNode.QuadTo(1031.4f, 922.4f, 1019.4f, 946.4f),
                        PathNode.QuadTo(994.4f, 995.4f, 946.4f, 1019.4f),
                        PathNode.QuadTo(922.4f, 1031.4f, 887.9f, 1034.4f),
                        PathNode.QuadTo(853.4f, 1037.4f, 771.4f, 1037.4f),
                        PathNode.LineTo(371.4f, 1037.4f),
                        PathNode.QuadTo(290.4f, 1037.4f, 255.4f, 1034.4f),
                        PathNode.QuadTo(220.4f, 1031.4f, 196.4f, 1019.4f),
                        PathNode.QuadTo(148.4f, 995.4f, 123.4f, 946.4f),
                        PathNode.QuadTo(111.4f, 922.4f, 108.4f, 887.9f),
                        PathNode.QuadTo(105.4f, 853.4f, 105.4f, 771.4f),
                        PathNode.LineTo(105.4f, 370.4f),
                        PathNode.QuadTo(105.4f, 289.4f, 108.4f, 254.9f),
                        PathNode.QuadTo(111.4f, 220.4f, 123.4f, 195.4f),
                        PathNode.QuadTo(148.4f, 147.4f, 196.4f, 123.4f),
                        PathNode.QuadTo(220.4f, 111.4f, 255.4f, 108.4f),
                        PathNode.QuadTo(290.4f, 105.4f, 371.4f, 105.4f),
                        PathNode.LineTo(771.4f, 105.4f),
                        PathNode.QuadTo(853.4f, 105.4f, 887.9f, 108.4f),
                        PathNode.QuadTo(922.4f, 111.4f, 946.4f, 123.4f),
                        PathNode.Close,
                        PathNode.MoveTo(363.4f, 540.4f),
                        PathNode.LineTo(372.4f, 549.4f),
                        PathNode.QuadTo(381.4f, 560.4f, 393.4f, 560.4f),
                        PathNode.QuadTo(405.4f, 560.4f, 412.4f, 552.4f),
                        PathNode.LineTo(538.4f, 427.4f),
                        PathNode.LineTo(538.4f, 841.4f),
                        PathNode.QuadTo(538.4f, 852.4f, 546.9f, 858.9f),
                        PathNode.QuadTo(555.4f, 865.4f, 565.4f, 865.4f),
                        PathNode.LineTo(577.4f, 865.4f),
                        PathNode.QuadTo(586.4f, 865.4f, 595.4f, 858.4f),
                        PathNode.QuadTo(604.4f, 851.4f, 604.4f, 841.4f),
                        PathNode.LineTo(604.4f, 427.4f),
                        PathNode.LineTo(730.4f, 552.4f),
                        PathNode.QuadTo(738.4f, 560.4f, 748.9f, 560.4f),
                        PathNode.QuadTo(759.4f, 560.4f, 767.4f, 552.4f),
                        PathNode.LineTo(778.4f, 541.4f),
                        PathNode.QuadTo(785.4f, 534.4f, 784.9f, 523.4f),
                        PathNode.QuadTo(784.4f, 512.4f, 777.4f, 505.4f),
                        PathNode.LineTo(592.4f, 320.4f),
                        PathNode.QuadTo(582.4f, 310.4f, 571.4f, 310.9f),
                        PathNode.QuadTo(560.4f, 311.4f, 550.4f, 321.4f),
                        PathNode.LineTo(364.4f, 507.4f),
                        PathNode.QuadTo(357.4f, 514.4f, 357.4f, 524.4f),
                        PathNode.QuadTo(357.4f, 534.4f, 363.4f, 540.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _fileDownloadsRegular!!
    }

private var _fileDownloadsRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.FileDownloads: ImageVector
    get() {
        if (_fileDownloadsMedium != null) return _fileDownloadsMedium!!
        _fileDownloadsMedium = ImageVector.Builder(
            name = "FileDownloads.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(950.2f, 116.1f),
                        PathNode.QuadTo(1000.4f, 141.5f, 1026.7f, 191.6f),
                        PathNode.QuadTo(1039.3f, 217.7f, 1042.4f, 253.3f),
                        PathNode.QuadTo(1045.5f, 288.9f, 1045.5f, 370.4f),
                        PathNode.LineTo(1045.5f, 771.4f),
                        PathNode.QuadTo(1045.5f, 853.7f, 1042.4f, 889.3f),
                        PathNode.QuadTo(1039.3f, 924.9f, 1026.7f, 950.2f),
                        PathNode.QuadTo(1000.4f, 1001.4f, 950.2f, 1026.7f),
                        PathNode.QuadTo(924.9f, 1039.3f, 889.3f, 1042.4f),
                        PathNode.QuadTo(853.7f, 1045.5f, 771.4f, 1045.5f),
                        PathNode.LineTo(371.4f, 1045.5f),
                        PathNode.QuadTo(289.9f, 1045.5f, 253.9f, 1042.4f),
                        PathNode.QuadTo(218.0f, 1039.3f, 192.6f, 1026.7f),
                        PathNode.QuadTo(142.5f, 1001.4f, 116.1f, 950.2f),
                        PathNode.QuadTo(103.6f, 924.9f, 100.5f, 889.3f),
                        PathNode.QuadTo(97.3f, 853.7f, 97.3f, 771.4f),
                        PathNode.LineTo(97.3f, 370.4f),
                        PathNode.QuadTo(97.3f, 288.9f, 100.5f, 253.3f),
                        PathNode.QuadTo(103.6f, 217.7f, 116.1f, 191.6f),
                        PathNode.QuadTo(142.5f, 141.5f, 192.6f, 116.1f),
                        PathNode.QuadTo(218.0f, 103.6f, 253.9f, 100.5f),
                        PathNode.QuadTo(289.9f, 97.3f, 371.4f, 97.3f),
                        PathNode.LineTo(771.4f, 97.3f),
                        PathNode.QuadTo(853.7f, 97.3f, 889.3f, 100.5f),
                        PathNode.QuadTo(924.9f, 103.6f, 950.2f, 116.1f),
                        PathNode.Close,
                        PathNode.MoveTo(356.1f, 544.2f),
                        PathNode.LineTo(368.9f, 557.8f),
                        PathNode.QuadTo(379.3f, 570.4f, 392.6f, 570.7f),
                        PathNode.QuadTo(406.0f, 571.0f, 415.4f, 560.8f),
                        PathNode.LineTo(530.3f, 446.9f),
                        PathNode.LineTo(530.3f, 843.9f),
                        PathNode.QuadTo(530.3f, 857.8f, 539.6f, 865.7f),
                        PathNode.QuadTo(548.9f, 873.5f, 562.2f, 873.5f),
                        PathNode.LineTo(580.7f, 873.5f),
                        PathNode.QuadTo(593.2f, 873.5f, 602.9f, 865.3f),
                        PathNode.QuadTo(612.5f, 857.1f, 612.5f, 843.9f),
                        PathNode.LineTo(612.5f, 446.9f),
                        PathNode.LineTo(726.9f, 560.3f),
                        PathNode.QuadTo(737.1f, 570.4f, 749.1f, 570.4f),
                        PathNode.QuadTo(761.1f, 570.4f, 771.2f, 560.3f),
                        PathNode.LineTo(786.0f, 545.5f),
                        PathNode.QuadTo(795.2f, 536.3f, 794.8f, 523.7f),
                        PathNode.QuadTo(794.4f, 511.1f, 785.3f, 501.9f),
                        PathNode.LineTo(596.5f, 313.1f),
                        PathNode.QuadTo(584.6f, 301.2f, 571.7f, 301.6f),
                        PathNode.QuadTo(558.8f, 302.0f, 546.9f, 313.9f),
                        PathNode.LineTo(357.4f, 502.8f),
                        PathNode.QuadTo(348.2f, 512.0f, 348.1f, 523.8f),
                        PathNode.QuadTo(348.0f, 535.5f, 356.1f, 544.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _fileDownloadsMedium!!
    }

private var _fileDownloadsMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.FileDownloads: ImageVector
    get() {
        if (_fileDownloadsDemibold != null) return _fileDownloadsDemibold!!
        _fileDownloadsDemibold = ImageVector.Builder(
            name = "FileDownloads.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(952.9f, 111.0f),
                        PathNode.QuadTo(1004.5f, 137.3f, 1031.8f, 189.0f),
                        PathNode.QuadTo(1044.8f, 215.8f, 1048.0f, 252.2f),
                        PathNode.QuadTo(1051.2f, 288.5f, 1051.2f, 370.4f),
                        PathNode.LineTo(1051.2f, 771.4f),
                        PathNode.QuadTo(1051.2f, 853.9f, 1048.0f, 890.2f),
                        PathNode.QuadTo(1044.8f, 926.6f, 1031.8f, 952.9f),
                        PathNode.QuadTo(1004.5f, 1005.5f, 952.9f, 1031.8f),
                        PathNode.QuadTo(926.6f, 1044.8f, 890.2f, 1048.0f),
                        PathNode.QuadTo(853.9f, 1051.2f, 771.4f, 1051.2f),
                        PathNode.LineTo(371.4f, 1051.2f),
                        PathNode.QuadTo(289.5f, 1051.2f, 252.9f, 1048.0f),
                        PathNode.QuadTo(216.3f, 1044.8f, 190.0f, 1031.8f),
                        PathNode.QuadTo(138.3f, 1005.5f, 111.0f, 952.9f),
                        PathNode.QuadTo(98.1f, 926.6f, 94.9f, 890.2f),
                        PathNode.QuadTo(91.6f, 853.9f, 91.6f, 771.4f),
                        PathNode.LineTo(91.6f, 370.4f),
                        PathNode.QuadTo(91.6f, 288.5f, 94.9f, 252.2f),
                        PathNode.QuadTo(98.1f, 215.8f, 111.0f, 189.0f),
                        PathNode.QuadTo(138.3f, 137.3f, 190.0f, 111.0f),
                        PathNode.QuadTo(216.3f, 98.1f, 252.9f, 94.9f),
                        PathNode.QuadTo(289.5f, 91.6f, 371.4f, 91.6f),
                        PathNode.LineTo(771.4f, 91.6f),
                        PathNode.QuadTo(853.9f, 91.6f, 890.2f, 94.9f),
                        PathNode.QuadTo(926.6f, 98.1f, 952.9f, 111.0f),
                        PathNode.Close,
                        PathNode.MoveTo(351.0f, 546.9f),
                        PathNode.LineTo(366.5f, 563.7f),
                        PathNode.QuadTo(377.8f, 577.4f, 392.1f, 577.9f),
                        PathNode.QuadTo(406.3f, 578.3f, 417.5f, 566.7f),
                        PathNode.LineTo(524.6f, 460.5f),
                        PathNode.LineTo(524.6f, 845.6f),
                        PathNode.QuadTo(524.6f, 861.6f, 534.5f, 870.4f),
                        PathNode.QuadTo(544.4f, 879.2f, 559.9f, 879.2f),
                        PathNode.LineTo(582.9f, 879.2f),
                        PathNode.QuadTo(597.9f, 879.2f, 608.1f, 870.1f),
                        PathNode.QuadTo(618.2f, 861.1f, 618.2f, 845.6f),
                        PathNode.LineTo(618.2f, 460.5f),
                        PathNode.LineTo(724.5f, 565.8f),
                        PathNode.QuadTo(736.1f, 577.4f, 749.2f, 577.4f),
                        PathNode.QuadTo(762.2f, 577.4f, 773.9f, 565.8f),
                        PathNode.LineTo(791.3f, 548.3f),
                        PathNode.QuadTo(802.0f, 537.6f, 801.7f, 523.9f),
                        PathNode.QuadTo(801.4f, 510.1f, 790.8f, 499.5f),
                        PathNode.LineTo(599.3f, 308.0f),
                        PathNode.QuadTo(586.1f, 294.8f, 571.9f, 295.1f),
                        PathNode.QuadTo(557.7f, 295.3f, 544.5f, 308.6f),
                        PathNode.LineTo(352.5f, 499.6f),
                        PathNode.QuadTo(341.8f, 510.3f, 341.6f, 523.3f),
                        PathNode.QuadTo(341.3f, 536.3f, 351.0f, 546.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _fileDownloadsDemibold!!
    }

private var _fileDownloadsDemibold: ImageVector? = null
