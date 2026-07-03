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
                        PathNode.MoveTo(869.6f, 150.0f),
                        PathNode.QuadTo(909.7f, 169.4f, 930.0f, 209.5f),
                        PathNode.QuadTo(940.1f, 230.5f, 942.4f, 259.9f),
                        PathNode.QuadTo(944.8f, 289.4f, 944.8f, 360.9f),
                        PathNode.VerticalTo(718.2f),
                        PathNode.QuadTo(944.8f, 790.6f, 942.4f, 820.1f),
                        PathNode.QuadTo(940.1f, 849.5f, 930.0f, 869.6f),
                        PathNode.QuadTo(909.7f, 910.6f, 869.6f, 930.0f),
                        PathNode.QuadTo(849.5f, 940.1f, 820.1f, 942.4f),
                        PathNode.QuadTo(790.6f, 944.8f, 718.2f, 944.8f),
                        PathNode.HorizontalTo(361.8f),
                        PathNode.QuadTo(290.2f, 944.8f, 260.4f, 942.4f),
                        PathNode.QuadTo(230.5f, 940.1f, 210.4f, 930.0f),
                        PathNode.QuadTo(170.9f, 910.6f, 150.0f, 869.6f),
                        PathNode.QuadTo(139.9f, 849.5f, 137.6f, 820.1f),
                        PathNode.QuadTo(135.2f, 790.6f, 135.2f, 718.2f),
                        PathNode.VerticalTo(360.9f),
                        PathNode.QuadTo(135.2f, 289.4f, 137.6f, 259.9f),
                        PathNode.QuadTo(139.9f, 230.5f, 150.0f, 209.5f),
                        PathNode.QuadTo(170.3f, 169.4f, 210.4f, 150.0f),
                        PathNode.QuadTo(230.5f, 139.9f, 260.4f, 137.6f),
                        PathNode.QuadTo(290.2f, 135.2f, 361.8f, 135.2f),
                        PathNode.HorizontalTo(718.2f),
                        PathNode.QuadTo(790.6f, 135.2f, 820.1f, 137.6f),
                        PathNode.QuadTo(849.5f, 139.9f, 869.6f, 150.0f),
                        PathNode.Close,
                        PathNode.MoveTo(359.9f, 509.7f),
                        PathNode.LineTo(367.3f, 517.1f),
                        PathNode.QuadTo(374.0f, 524.3f, 381.7f, 524.3f),
                        PathNode.QuadTo(389.4f, 524.3f, 394.4f, 518.5f),
                        PathNode.LineTo(516.5f, 397.9f),
                        PathNode.VerticalTo(780.0f),
                        PathNode.QuadTo(516.5f, 787.1f, 522.1f, 791.6f),
                        PathNode.QuadTo(527.7f, 796.1f, 534.7f, 796.1f),
                        PathNode.HorizontalTo(545.3f),
                        PathNode.QuadTo(552.1f, 796.1f, 557.8f, 791.5f),
                        PathNode.QuadTo(563.5f, 786.9f, 563.5f, 780.0f),
                        PathNode.VerticalTo(397.9f),
                        PathNode.LineTo(685.6f, 518.5f),
                        PathNode.QuadTo(690.8f, 523.6f, 698.2f, 523.9f),
                        PathNode.QuadTo(705.6f, 524.3f, 710.7f, 518.5f),
                        PathNode.LineTo(719.9f, 510.0f),
                        PathNode.QuadTo(724.8f, 505.1f, 724.7f, 497.6f),
                        PathNode.QuadTo(724.6f, 490.0f, 719.6f, 485.1f),
                        PathNode.LineTo(556.1f, 322.2f),
                        PathNode.QuadTo(548.5f, 314.0f, 540.3f, 314.1f),
                        PathNode.QuadTo(532.2f, 314.2f, 523.9f, 322.5f),
                        PathNode.LineTo(360.8f, 486.9f),
                        PathNode.QuadTo(355.9f, 491.8f, 355.6f, 498.5f),
                        PathNode.QuadTo(355.2f, 505.1f, 359.9f, 509.7f),
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
                        PathNode.MoveTo(886.7f, 154.6f),
                        PathNode.QuadTo(929.5f, 176.0f, 951.8f, 218.8f),
                        PathNode.QuadTo(962.5f, 241.1f, 965.2f, 271.8f),
                        PathNode.QuadTo(967.8f, 302.6f, 967.8f, 374.6f),
                        PathNode.VerticalTo(730.9f),
                        PathNode.QuadTo(967.8f, 803.8f, 965.2f, 834.5f),
                        PathNode.QuadTo(962.5f, 865.3f, 951.8f, 886.7f),
                        PathNode.QuadTo(929.5f, 930.4f, 886.7f, 951.8f),
                        PathNode.QuadTo(865.3f, 962.5f, 834.5f, 965.2f),
                        PathNode.QuadTo(803.8f, 967.8f, 730.9f, 967.8f),
                        PathNode.HorizontalTo(375.5f),
                        PathNode.QuadTo(303.5f, 967.8f, 272.3f, 965.2f),
                        PathNode.QuadTo(241.1f, 962.5f, 219.7f, 951.8f),
                        PathNode.QuadTo(176.9f, 930.4f, 154.6f, 886.7f),
                        PathNode.QuadTo(143.9f, 865.3f, 141.2f, 834.5f),
                        PathNode.QuadTo(138.6f, 803.8f, 138.6f, 730.9f),
                        PathNode.VerticalTo(374.6f),
                        PathNode.QuadTo(138.6f, 302.6f, 141.2f, 271.8f),
                        PathNode.QuadTo(143.9f, 241.1f, 154.6f, 218.8f),
                        PathNode.QuadTo(176.9f, 176.0f, 219.7f, 154.6f),
                        PathNode.QuadTo(241.1f, 143.9f, 272.3f, 141.2f),
                        PathNode.QuadTo(303.5f, 138.6f, 375.5f, 138.6f),
                        PathNode.HorizontalTo(730.9f),
                        PathNode.QuadTo(803.8f, 138.6f, 834.5f, 141.2f),
                        PathNode.QuadTo(865.3f, 143.9f, 886.7f, 154.6f),
                        PathNode.Close,
                        PathNode.MoveTo(367.9f, 525.9f),
                        PathNode.LineTo(376.1f, 534.2f),
                        PathNode.QuadTo(384.2f, 544.0f, 395.0f, 544.0f),
                        PathNode.QuadTo(405.7f, 544.1f, 412.1f, 536.8f),
                        PathNode.LineTo(523.3f, 426.5f),
                        PathNode.VerticalTo(793.3f),
                        PathNode.QuadTo(523.3f, 803.3f, 531.0f, 809.1f),
                        PathNode.QuadTo(538.6f, 815.0f, 547.6f, 815.0f),
                        PathNode.HorizontalTo(558.8f),
                        PathNode.QuadTo(567.0f, 815.0f, 575.0f, 808.7f),
                        PathNode.QuadTo(583.1f, 802.4f, 583.1f, 793.3f),
                        PathNode.VerticalTo(426.5f),
                        PathNode.LineTo(694.3f, 536.8f),
                        PathNode.QuadTo(701.5f, 544.0f, 710.9f, 544.0f),
                        PathNode.QuadTo(720.4f, 544.1f, 727.6f, 536.8f),
                        PathNode.LineTo(737.6f, 526.8f),
                        PathNode.QuadTo(744.0f, 520.4f, 743.6f, 510.6f),
                        PathNode.QuadTo(743.1f, 500.7f, 736.8f, 494.3f),
                        PathNode.LineTo(572.1f, 329.7f),
                        PathNode.QuadTo(563.1f, 320.7f, 553.2f, 321.1f),
                        PathNode.QuadTo(543.4f, 321.5f, 534.3f, 330.5f),
                        PathNode.LineTo(368.8f, 496.0f),
                        PathNode.QuadTo(362.4f, 502.4f, 362.4f, 511.4f),
                        PathNode.QuadTo(362.4f, 520.4f, 367.9f, 525.9f),
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
                        PathNode.MoveTo(894.6f, 156.8f),
                        PathNode.QuadTo(939.1f, 178.9f, 961.6f, 223.0f),
                        PathNode.QuadTo(973.0f, 245.9f, 975.7f, 277.1f),
                        PathNode.QuadTo(978.3f, 308.3f, 978.3f, 380.6f),
                        PathNode.VerticalTo(736.9f),
                        PathNode.QuadTo(978.3f, 809.8f, 975.7f, 841.2f),
                        PathNode.QuadTo(973.0f, 872.5f, 961.6f, 894.6f),
                        PathNode.QuadTo(938.7f, 940.0f, 894.6f, 961.6f),
                        PathNode.QuadTo(872.5f, 973.0f, 841.2f, 975.7f),
                        PathNode.QuadTo(809.8f, 978.3f, 736.9f, 978.3f),
                        PathNode.HorizontalTo(381.5f),
                        PathNode.QuadTo(309.2f, 978.3f, 277.5f, 975.7f),
                        PathNode.QuadTo(245.9f, 973.0f, 223.9f, 961.6f),
                        PathNode.QuadTo(179.7f, 940.0f, 156.8f, 894.6f),
                        PathNode.QuadTo(145.4f, 872.5f, 142.7f, 841.2f),
                        PathNode.QuadTo(140.1f, 809.8f, 140.1f, 736.9f),
                        PathNode.VerticalTo(380.6f),
                        PathNode.QuadTo(140.1f, 308.3f, 142.7f, 277.1f),
                        PathNode.QuadTo(145.4f, 245.9f, 156.8f, 223.0f),
                        PathNode.QuadTo(179.4f, 178.9f, 223.9f, 156.8f),
                        PathNode.QuadTo(245.9f, 145.4f, 277.5f, 142.7f),
                        PathNode.QuadTo(309.2f, 140.1f, 381.5f, 140.1f),
                        PathNode.HorizontalTo(736.9f),
                        PathNode.QuadTo(809.8f, 140.1f, 841.2f, 142.7f),
                        PathNode.QuadTo(872.5f, 145.4f, 894.6f, 156.8f),
                        PathNode.Close,
                        PathNode.MoveTo(370.0f, 533.8f),
                        PathNode.LineTo(380.3f, 544.7f),
                        PathNode.QuadTo(389.3f, 555.3f, 400.7f, 555.4f),
                        PathNode.QuadTo(412.1f, 555.6f, 419.7f, 547.4f),
                        PathNode.LineTo(524.8f, 443.2f),
                        PathNode.VerticalTo(800.6f),
                        PathNode.QuadTo(524.8f, 812.2f, 532.9f, 818.8f),
                        PathNode.QuadTo(541.0f, 825.5f, 551.7f, 825.5f),
                        PathNode.HorizontalTo(566.7f),
                        PathNode.QuadTo(576.9f, 825.5f, 585.2f, 818.6f),
                        PathNode.QuadTo(593.6f, 811.7f, 593.6f, 800.6f),
                        PathNode.VerticalTo(443.2f),
                        PathNode.LineTo(698.3f, 547.0f),
                        PathNode.QuadTo(706.5f, 555.3f, 716.9f, 555.4f),
                        PathNode.QuadTo(727.3f, 555.6f, 735.5f, 547.0f),
                        PathNode.LineTo(747.8f, 535.1f),
                        PathNode.QuadTo(755.2f, 527.4f, 754.9f, 516.8f),
                        PathNode.QuadTo(754.7f, 506.1f, 747.0f, 498.4f),
                        PathNode.LineTo(580.4f, 331.8f),
                        PathNode.QuadTo(570.4f, 321.9f, 559.6f, 322.2f),
                        PathNode.QuadTo(548.7f, 322.4f, 538.4f, 332.3f),
                        PathNode.LineTo(370.9f, 499.5f),
                        PathNode.QuadTo(363.3f, 506.7f, 363.3f, 516.7f),
                        PathNode.QuadTo(363.3f, 526.7f, 370.0f, 533.8f),
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
                        PathNode.MoveTo(907.3f, 160.4f),
                        PathNode.QuadTo(954.6f, 183.5f, 977.7f, 229.9f),
                        PathNode.QuadTo(990.3f, 254.0f, 993.0f, 286.0f),
                        PathNode.QuadTo(995.6f, 318.1f, 995.6f, 390.8f),
                        PathNode.VerticalTo(746.4f),
                        PathNode.QuadTo(995.6f, 819.1f, 993.0f, 851.6f),
                        PathNode.QuadTo(990.3f, 884.1f, 977.7f, 907.3f),
                        PathNode.QuadTo(953.6f, 955.5f, 907.3f, 977.7f),
                        PathNode.QuadTo(884.1f, 990.3f, 851.6f, 993.0f),
                        PathNode.QuadTo(819.1f, 995.6f, 746.4f, 995.6f),
                        PathNode.HorizontalTo(391.7f),
                        PathNode.QuadTo(319.0f, 995.6f, 286.5f, 993.0f),
                        PathNode.QuadTo(254.0f, 990.3f, 230.8f, 977.7f),
                        PathNode.QuadTo(184.4f, 955.5f, 160.4f, 907.3f),
                        PathNode.QuadTo(147.8f, 884.1f, 145.1f, 851.6f),
                        PathNode.QuadTo(142.5f, 819.1f, 142.5f, 746.4f),
                        PathNode.VerticalTo(390.8f),
                        PathNode.QuadTo(142.5f, 318.1f, 145.1f, 286.0f),
                        PathNode.QuadTo(147.8f, 254.0f, 160.4f, 229.9f),
                        PathNode.QuadTo(183.5f, 183.5f, 230.8f, 160.4f),
                        PathNode.QuadTo(254.0f, 147.8f, 286.5f, 145.1f),
                        PathNode.QuadTo(319.0f, 142.5f, 391.7f, 142.5f),
                        PathNode.HorizontalTo(746.4f),
                        PathNode.QuadTo(819.1f, 142.5f, 851.6f, 145.1f),
                        PathNode.QuadTo(884.1f, 147.8f, 907.3f, 160.4f),
                        PathNode.Close,
                        PathNode.MoveTo(373.1f, 547.3f),
                        PathNode.LineTo(386.9f, 563.0f),
                        PathNode.QuadTo(397.7f, 574.6f, 410.3f, 575.1f),
                        PathNode.QuadTo(422.8f, 575.6f, 432.9f, 565.6f),
                        PathNode.LineTo(526.4f, 473.0f),
                        PathNode.VerticalTo(812.3f),
                        PathNode.QuadTo(526.4f, 826.8f, 535.4f, 835.0f),
                        PathNode.QuadTo(544.3f, 843.1f, 558.0f, 843.1f),
                        PathNode.HorizontalTo(580.1f),
                        PathNode.QuadTo(593.8f, 843.1f, 602.8f, 835.0f),
                        PathNode.QuadTo(611.7f, 826.9f, 611.7f, 812.3f),
                        PathNode.VerticalTo(473.0f),
                        PathNode.LineTo(704.3f, 564.7f),
                        PathNode.QuadTo(714.2f, 574.6f, 726.4f, 575.1f),
                        PathNode.QuadTo(738.6f, 575.6f, 748.6f, 564.7f),
                        PathNode.LineTo(765.0f, 549.2f),
                        PathNode.QuadTo(774.1f, 539.1f, 774.1f, 527.0f),
                        PathNode.QuadTo(774.2f, 514.8f, 764.1f, 504.8f),
                        PathNode.LineTo(594.4f, 335.0f),
                        PathNode.QuadTo(582.6f, 323.3f, 570.0f, 323.2f),
                        PathNode.QuadTo(557.4f, 323.2f, 544.7f, 334.9f),
                        PathNode.LineTo(374.0f, 504.6f),
                        PathNode.QuadTo(364.0f, 513.7f, 364.0f, 525.5f),
                        PathNode.QuadTo(364.0f, 537.2f, 373.1f, 547.3f),
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
                        PathNode.MoveTo(916.4f, 162.9f),
                        PathNode.QuadTo(965.5f, 186.8f, 989.1f, 234.6f),
                        PathNode.QuadTo(1002.4f, 259.5f, 1005.1f, 292.1f),
                        PathNode.QuadTo(1007.7f, 324.7f, 1007.7f, 397.8f),
                        PathNode.VerticalTo(753.2f),
                        PathNode.QuadTo(1007.7f, 825.9f, 1005.1f, 859.1f),
                        PathNode.QuadTo(1002.4f, 892.5f, 989.1f, 916.4f),
                        PathNode.QuadTo(964.2f, 966.4f, 916.4f, 989.1f),
                        PathNode.QuadTo(892.5f, 1002.4f, 859.1f, 1005.1f),
                        PathNode.QuadTo(825.9f, 1007.7f, 753.2f, 1007.7f),
                        PathNode.HorizontalTo(398.7f),
                        PathNode.QuadTo(325.6f, 1007.7f, 292.5f, 1005.1f),
                        PathNode.QuadTo(259.5f, 1002.4f, 235.5f, 989.1f),
                        PathNode.QuadTo(187.7f, 966.4f, 162.9f, 916.4f),
                        PathNode.QuadTo(149.6f, 892.5f, 146.9f, 859.1f),
                        PathNode.QuadTo(144.3f, 825.9f, 144.3f, 753.2f),
                        PathNode.VerticalTo(397.8f),
                        PathNode.QuadTo(144.3f, 324.7f, 146.9f, 292.1f),
                        PathNode.QuadTo(149.6f, 259.5f, 162.9f, 234.6f),
                        PathNode.QuadTo(186.3f, 186.8f, 235.5f, 162.9f),
                        PathNode.QuadTo(259.5f, 149.6f, 292.5f, 146.9f),
                        PathNode.QuadTo(325.6f, 144.3f, 398.7f, 144.3f),
                        PathNode.HorizontalTo(753.2f),
                        PathNode.QuadTo(825.9f, 144.3f, 859.1f, 146.9f),
                        PathNode.QuadTo(892.5f, 149.6f, 916.4f, 162.9f),
                        PathNode.Close,
                        PathNode.MoveTo(375.6f, 556.5f),
                        PathNode.LineTo(391.5f, 575.2f),
                        PathNode.QuadTo(403.6f, 587.6f, 416.8f, 588.2f),
                        PathNode.QuadTo(430.1f, 588.9f, 441.7f, 577.8f),
                        PathNode.LineTo(528.1f, 492.4f),
                        PathNode.VerticalTo(820.7f),
                        PathNode.QuadTo(528.1f, 837.0f, 537.6f, 846.2f),
                        PathNode.QuadTo(547.1f, 855.3f, 562.6f, 855.3f),
                        PathNode.HorizontalTo(589.3f),
                        PathNode.QuadTo(605.3f, 855.3f, 614.6f, 846.4f),
                        PathNode.QuadTo(623.9f, 837.5f, 623.9f, 820.7f),
                        PathNode.VerticalTo(492.4f),
                        PathNode.LineTo(708.8f, 576.5f),
                        PathNode.QuadTo(720.0f, 587.6f, 733.3f, 588.2f),
                        PathNode.QuadTo(746.7f, 588.9f, 757.7f, 576.5f),
                        PathNode.LineTo(776.8f, 558.7f),
                        PathNode.QuadTo(787.0f, 547.2f, 787.2f, 534.1f),
                        PathNode.QuadTo(787.5f, 521.0f, 776.0f, 509.4f),
                        PathNode.LineTo(603.9f, 337.5f),
                        PathNode.QuadTo(591.1f, 324.5f, 577.3f, 324.3f),
                        PathNode.QuadTo(563.5f, 324.1f, 549.3f, 337.0f),
                        PathNode.LineTo(376.5f, 508.5f),
                        PathNode.QuadTo(364.9f, 518.8f, 364.9f, 531.6f),
                        PathNode.QuadTo(364.9f, 544.5f, 375.6f, 556.5f),
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
