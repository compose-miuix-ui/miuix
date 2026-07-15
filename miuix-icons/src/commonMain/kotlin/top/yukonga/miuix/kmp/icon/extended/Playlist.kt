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

val MiuixIcons.Playlist: ImageVector
    get() = MiuixIcons.Regular.Playlist

val MiuixIcons.Light.Playlist: ImageVector
    get() {
        if (_playlistLight != null) return _playlistLight!!
        _playlistLight = ImageVector.Builder(
            name = "Playlist.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1072.8f,
            viewportHeight = 1072.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1072.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(345.9f, 819.0f),
                        PathNode.LineTo(162.0f, 924.6f),
                        PathNode.QuadTo(154.1f, 929.4f, 145.7f, 928.0f),
                        PathNode.QuadTo(137.4f, 926.5f, 131.9f, 919.9f),
                        PathNode.QuadTo(126.5f, 913.4f, 126.5f, 903.8f),
                        PathNode.VerticalTo(691.7f),
                        PathNode.QuadTo(126.5f, 683.0f, 131.9f, 676.5f),
                        PathNode.QuadTo(137.4f, 669.9f, 145.7f, 668.5f),
                        PathNode.QuadTo(154.1f, 667.0f, 162.0f, 671.8f),
                        PathNode.LineTo(345.9f, 777.4f),
                        PathNode.QuadTo(353.8f, 782.2f, 356.7f, 790.2f),
                        PathNode.QuadTo(359.6f, 798.2f, 356.7f, 806.2f),
                        PathNode.QuadTo(353.8f, 814.2f, 345.9f, 819.0f),
                        PathNode.Close,
                        PathNode.MoveTo(953.3f, 162.8f),
                        PathNode.VerticalTo(183.8f),
                        PathNode.QuadTo(953.3f, 194.3f, 947.5f, 198.8f),
                        PathNode.QuadTo(941.7f, 203.4f, 932.2f, 203.4f),
                        PathNode.HorizontalTo(140.6f),
                        PathNode.QuadTo(131.8f, 203.4f, 125.6f, 198.4f),
                        PathNode.QuadTo(119.5f, 193.4f, 119.5f, 185.2f),
                        PathNode.VerticalTo(163.4f),
                        PathNode.QuadTo(119.5f, 153.0f, 125.3f, 148.2f),
                        PathNode.QuadTo(131.1f, 143.3f, 140.6f, 143.3f),
                        PathNode.HorizontalTo(932.2f),
                        PathNode.QuadTo(941.7f, 143.3f, 947.5f, 147.9f),
                        PathNode.QuadTo(953.3f, 152.3f, 953.3f, 162.8f),
                        PathNode.Close,
                        PathNode.MoveTo(953.3f, 479.5f),
                        PathNode.VerticalTo(500.5f),
                        PathNode.QuadTo(953.3f, 511.0f, 947.5f, 515.5f),
                        PathNode.QuadTo(941.7f, 520.0f, 932.2f, 520.0f),
                        PathNode.HorizontalTo(140.6f),
                        PathNode.QuadTo(131.8f, 520.0f, 125.6f, 515.0f),
                        PathNode.QuadTo(119.5f, 510.0f, 119.5f, 501.8f),
                        PathNode.VerticalTo(480.0f),
                        PathNode.QuadTo(119.5f, 469.7f, 125.3f, 464.8f),
                        PathNode.QuadTo(131.1f, 460.0f, 140.6f, 460.0f),
                        PathNode.HorizontalTo(932.2f),
                        PathNode.QuadTo(941.7f, 460.0f, 947.5f, 464.5f),
                        PathNode.QuadTo(953.3f, 469.0f, 953.3f, 479.5f),
                        PathNode.Close,
                        PathNode.MoveTo(953.3f, 793.3f),
                        PathNode.VerticalTo(814.3f),
                        PathNode.QuadTo(953.3f, 824.8f, 947.5f, 829.3f),
                        PathNode.QuadTo(941.7f, 833.8f, 932.2f, 833.8f),
                        PathNode.HorizontalTo(502.0f),
                        PathNode.QuadTo(493.1f, 833.8f, 487.0f, 828.8f),
                        PathNode.QuadTo(480.8f, 823.9f, 480.8f, 815.6f),
                        PathNode.VerticalTo(793.9f),
                        PathNode.QuadTo(480.8f, 783.5f, 486.7f, 778.6f),
                        PathNode.QuadTo(492.4f, 773.8f, 502.0f, 773.8f),
                        PathNode.HorizontalTo(932.2f),
                        PathNode.QuadTo(941.7f, 773.8f, 947.5f, 778.3f),
                        PathNode.QuadTo(953.3f, 782.8f, 953.3f, 793.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playlistLight!!
    }

private var _playlistLight: ImageVector? = null

val MiuixIcons.Normal.Playlist: ImageVector
    get() {
        if (_playlistNormal != null) return _playlistNormal!!
        _playlistNormal = ImageVector.Builder(
            name = "Playlist.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1079.4f,
            viewportHeight = 1079.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1079.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(358.9f, 826.4f),
                        PathNode.LineTo(165.5f, 937.4f),
                        PathNode.QuadTo(155.5f, 942.9f, 145.0f, 941.1f),
                        PathNode.QuadTo(134.5f, 939.3f, 127.7f, 931.1f),
                        PathNode.QuadTo(120.9f, 922.9f, 120.9f, 911.1f),
                        PathNode.VerticalTo(688.0f),
                        PathNode.QuadTo(120.9f, 677.1f, 127.7f, 668.9f),
                        PathNode.QuadTo(134.5f, 660.7f, 145.0f, 658.9f),
                        PathNode.QuadTo(155.5f, 657.1f, 165.5f, 662.6f),
                        PathNode.LineTo(358.9f, 773.6f),
                        PathNode.QuadTo(368.9f, 779.2f, 372.1f, 789.6f),
                        PathNode.QuadTo(375.4f, 800.0f, 372.1f, 810.4f),
                        PathNode.QuadTo(368.9f, 820.9f, 358.9f, 826.4f),
                        PathNode.Close,
                        PathNode.MoveTo(959.1f, 161.8f),
                        PathNode.VerticalTo(189.1f),
                        PathNode.QuadTo(959.1f, 202.7f, 951.4f, 208.6f),
                        PathNode.QuadTo(943.8f, 214.5f, 931.1f, 214.5f),
                        PathNode.HorizontalTo(148.2f),
                        PathNode.QuadTo(136.5f, 214.5f, 128.3f, 208.1f),
                        PathNode.QuadTo(120.2f, 201.7f, 120.2f, 190.0f),
                        PathNode.VerticalTo(162.7f),
                        PathNode.QuadTo(120.2f, 149.1f, 127.9f, 142.8f),
                        PathNode.QuadTo(135.6f, 136.5f, 148.2f, 136.5f),
                        PathNode.HorizontalTo(931.1f),
                        PathNode.QuadTo(943.8f, 136.5f, 951.4f, 142.4f),
                        PathNode.QuadTo(959.1f, 148.2f, 959.1f, 161.8f),
                        PathNode.Close,
                        PathNode.MoveTo(959.1f, 478.3f),
                        PathNode.VerticalTo(505.5f),
                        PathNode.QuadTo(959.1f, 519.1f, 951.4f, 525.0f),
                        PathNode.QuadTo(943.8f, 530.9f, 931.1f, 530.9f),
                        PathNode.HorizontalTo(148.2f),
                        PathNode.QuadTo(136.5f, 530.9f, 128.3f, 524.6f),
                        PathNode.QuadTo(120.2f, 518.2f, 120.2f, 506.5f),
                        PathNode.VerticalTo(479.2f),
                        PathNode.QuadTo(120.2f, 465.6f, 127.9f, 459.3f),
                        PathNode.QuadTo(135.6f, 452.9f, 148.2f, 452.9f),
                        PathNode.HorizontalTo(931.1f),
                        PathNode.QuadTo(943.8f, 452.9f, 951.4f, 458.8f),
                        PathNode.QuadTo(959.1f, 464.7f, 959.1f, 478.3f),
                        PathNode.Close,
                        PathNode.MoveTo(959.1f, 792.0f),
                        PathNode.VerticalTo(819.2f),
                        PathNode.QuadTo(959.1f, 832.8f, 951.4f, 838.7f),
                        PathNode.QuadTo(943.8f, 844.6f, 931.1f, 844.6f),
                        PathNode.HorizontalTo(509.4f),
                        PathNode.QuadTo(497.6f, 844.6f, 489.5f, 838.2f),
                        PathNode.QuadTo(481.3f, 831.9f, 481.3f, 820.2f),
                        PathNode.VerticalTo(792.9f),
                        PathNode.QuadTo(481.3f, 779.3f, 489.0f, 772.9f),
                        PathNode.QuadTo(496.7f, 766.6f, 509.4f, 766.6f),
                        PathNode.HorizontalTo(931.1f),
                        PathNode.QuadTo(943.8f, 766.6f, 951.4f, 772.5f),
                        PathNode.QuadTo(959.1f, 778.4f, 959.1f, 792.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playlistNormal!!
    }

private var _playlistNormal: ImageVector? = null

val MiuixIcons.Regular.Playlist: ImageVector
    get() {
        if (_playlistRegular != null) return _playlistRegular!!
        _playlistRegular = ImageVector.Builder(
            name = "Playlist.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1082.4f,
            viewportHeight = 1082.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1082.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(364.4f, 829.4f),
                        PathNode.LineTo(171.0f, 940.4f),
                        PathNode.QuadTo(159.8f, 946.7f, 148.0f, 944.6f),
                        PathNode.QuadTo(136.2f, 942.5f, 128.4f, 933.2f),
                        PathNode.QuadTo(120.6f, 923.9f, 120.6f, 910.7f),
                        PathNode.VerticalTo(687.7f),
                        PathNode.QuadTo(120.6f, 675.4f, 128.4f, 666.1f),
                        PathNode.QuadTo(136.2f, 656.8f, 148.0f, 654.8f),
                        PathNode.QuadTo(159.8f, 652.7f, 171.0f, 658.9f),
                        PathNode.LineTo(364.4f, 769.9f),
                        PathNode.QuadTo(375.5f, 776.2f, 379.2f, 787.9f),
                        PathNode.QuadTo(383.0f, 799.7f, 379.2f, 811.4f),
                        PathNode.QuadTo(375.5f, 823.2f, 364.4f, 829.4f),
                        PathNode.Close,
                        PathNode.MoveTo(961.8f, 165.0f),
                        PathNode.VerticalTo(192.8f),
                        PathNode.QuadTo(961.8f, 208.1f, 952.9f, 215.1f),
                        PathNode.QuadTo(943.9f, 222.1f, 929.8f, 222.1f),
                        PathNode.HorizontalTo(152.7f),
                        PathNode.QuadTo(139.2f, 222.1f, 129.9f, 214.7f),
                        PathNode.QuadTo(120.6f, 207.2f, 120.6f, 193.7f),
                        PathNode.VerticalTo(166.0f),
                        PathNode.QuadTo(120.6f, 150.6f, 129.4f, 143.2f),
                        PathNode.QuadTo(138.3f, 135.8f, 152.7f, 135.8f),
                        PathNode.HorizontalTo(929.8f),
                        PathNode.QuadTo(943.9f, 135.8f, 952.9f, 142.7f),
                        PathNode.QuadTo(961.8f, 149.7f, 961.8f, 165.0f),
                        PathNode.Close,
                        PathNode.MoveTo(961.8f, 479.6f),
                        PathNode.VerticalTo(507.3f),
                        PathNode.QuadTo(961.8f, 522.7f, 952.9f, 529.7f),
                        PathNode.QuadTo(943.9f, 536.6f, 929.8f, 536.6f),
                        PathNode.HorizontalTo(152.7f),
                        PathNode.QuadTo(139.2f, 536.6f, 129.9f, 529.2f),
                        PathNode.QuadTo(120.6f, 521.7f, 120.6f, 508.3f),
                        PathNode.VerticalTo(480.5f),
                        PathNode.QuadTo(120.6f, 465.2f, 129.4f, 457.7f),
                        PathNode.QuadTo(138.3f, 450.3f, 152.7f, 450.3f),
                        PathNode.HorizontalTo(929.8f),
                        PathNode.QuadTo(943.9f, 450.3f, 952.9f, 457.3f),
                        PathNode.QuadTo(961.8f, 464.3f, 961.8f, 479.6f),
                        PathNode.Close,
                        PathNode.MoveTo(961.8f, 791.4f),
                        PathNode.VerticalTo(819.1f),
                        PathNode.QuadTo(961.8f, 834.4f, 952.9f, 841.4f),
                        PathNode.QuadTo(943.9f, 848.4f, 929.8f, 848.4f),
                        PathNode.HorizontalTo(515.0f),
                        PathNode.QuadTo(501.5f, 848.4f, 492.2f, 841.0f),
                        PathNode.QuadTo(482.9f, 833.5f, 482.9f, 820.0f),
                        PathNode.VerticalTo(792.3f),
                        PathNode.QuadTo(482.9f, 777.0f, 491.7f, 769.5f),
                        PathNode.QuadTo(500.6f, 762.1f, 515.0f, 762.1f),
                        PathNode.HorizontalTo(929.8f),
                        PathNode.QuadTo(943.9f, 762.1f, 952.9f, 769.0f),
                        PathNode.QuadTo(961.8f, 776.0f, 961.8f, 791.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playlistRegular!!
    }

private var _playlistRegular: ImageVector? = null

val MiuixIcons.Medium.Playlist: ImageVector
    get() {
        if (_playlistMedium != null) return _playlistMedium!!
        _playlistMedium = ImageVector.Builder(
            name = "Playlist.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1102.2f,
            viewportHeight = 1102.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1102.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(378.6f, 845.7f),
                        PathNode.LineTo(185.5f, 956.6f),
                        PathNode.QuadTo(172.0f, 964.6f, 157.2f, 961.9f),
                        PathNode.QuadTo(142.4f, 959.3f, 132.6f, 947.6f),
                        PathNode.QuadTo(122.7f, 935.9f, 122.7f, 919.8f),
                        PathNode.VerticalTo(697.1f),
                        PathNode.QuadTo(122.7f, 681.9f, 132.6f, 670.2f),
                        PathNode.QuadTo(142.4f, 658.5f, 157.2f, 655.9f),
                        PathNode.QuadTo(172.0f, 653.2f, 185.5f, 661.2f),
                        PathNode.LineTo(378.6f, 772.1f),
                        PathNode.QuadTo(392.1f, 780.1f, 397.0f, 794.5f),
                        PathNode.QuadTo(401.9f, 808.9f, 397.0f, 823.3f),
                        PathNode.QuadTo(392.1f, 837.7f, 378.6f, 845.7f),
                        PathNode.Close,
                        PathNode.MoveTo(979.4f, 175.2f),
                        PathNode.VerticalTo(202.9f),
                        PathNode.QuadTo(979.4f, 221.8f, 967.8f, 231.1f),
                        PathNode.QuadTo(956.2f, 240.5f, 939.1f, 240.5f),
                        PathNode.HorizontalTo(163.1f),
                        PathNode.QuadTo(146.0f, 240.5f, 134.4f, 230.7f),
                        PathNode.QuadTo(122.7f, 220.8f, 122.7f, 203.8f),
                        PathNode.VerticalTo(176.1f),
                        PathNode.QuadTo(122.7f, 157.2f, 133.9f, 147.4f),
                        PathNode.QuadTo(145.1f, 137.6f, 163.1f, 137.6f),
                        PathNode.HorizontalTo(939.1f),
                        PathNode.QuadTo(956.2f, 137.6f, 967.8f, 147.0f),
                        PathNode.QuadTo(979.4f, 156.3f, 979.4f, 175.2f),
                        PathNode.Close,
                        PathNode.MoveTo(979.4f, 489.3f),
                        PathNode.VerticalTo(517.0f),
                        PathNode.QuadTo(979.4f, 535.9f, 967.8f, 545.2f),
                        PathNode.QuadTo(956.2f, 554.6f, 939.1f, 554.6f),
                        PathNode.HorizontalTo(163.1f),
                        PathNode.QuadTo(146.0f, 554.6f, 134.4f, 544.8f),
                        PathNode.QuadTo(122.7f, 534.9f, 122.7f, 517.9f),
                        PathNode.VerticalTo(490.2f),
                        PathNode.QuadTo(122.7f, 471.3f, 133.9f, 461.5f),
                        PathNode.QuadTo(145.1f, 451.7f, 163.1f, 451.7f),
                        PathNode.HorizontalTo(939.1f),
                        PathNode.QuadTo(956.2f, 451.7f, 967.8f, 461.1f),
                        PathNode.QuadTo(979.4f, 470.4f, 979.4f, 489.3f),
                        PathNode.Close,
                        PathNode.MoveTo(979.4f, 800.6f),
                        PathNode.VerticalTo(828.3f),
                        PathNode.QuadTo(979.4f, 847.2f, 967.8f, 856.5f),
                        PathNode.QuadTo(956.2f, 865.9f, 939.1f, 865.9f),
                        PathNode.HorizontalTo(533.2f),
                        PathNode.QuadTo(516.2f, 865.9f, 504.5f, 856.1f),
                        PathNode.QuadTo(492.9f, 846.3f, 492.9f, 829.2f),
                        PathNode.VerticalTo(801.5f),
                        PathNode.QuadTo(492.9f, 782.7f, 504.0f, 772.8f),
                        PathNode.QuadTo(515.2f, 763.0f, 533.2f, 763.0f),
                        PathNode.HorizontalTo(939.1f),
                        PathNode.QuadTo(956.2f, 763.0f, 967.8f, 772.4f),
                        PathNode.QuadTo(979.4f, 781.7f, 979.4f, 800.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playlistMedium!!
    }

private var _playlistMedium: ImageVector? = null

val MiuixIcons.Demibold.Playlist: ImageVector
    get() {
        if (_playlistDemibold != null) return _playlistDemibold!!
        _playlistDemibold = ImageVector.Builder(
            name = "Playlist.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1116.0f,
            viewportHeight = 1116.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1116.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(388.6f, 857.2f),
                        PathNode.LineTo(195.7f, 967.9f),
                        PathNode.QuadTo(180.6f, 977.2f, 163.7f, 974.1f),
                        PathNode.QuadTo(146.8f, 971.0f, 135.6f, 957.6f),
                        PathNode.QuadTo(124.3f, 944.4f, 124.3f, 926.1f),
                        PathNode.VerticalTo(703.7f),
                        PathNode.QuadTo(124.3f, 686.4f, 135.6f, 673.1f),
                        PathNode.QuadTo(146.8f, 659.8f, 163.7f, 656.6f),
                        PathNode.QuadTo(180.6f, 653.5f, 195.7f, 662.9f),
                        PathNode.LineTo(388.6f, 773.6f),
                        PathNode.QuadTo(403.7f, 782.9f, 409.5f, 799.1f),
                        PathNode.QuadTo(415.2f, 815.3f, 409.5f, 831.6f),
                        PathNode.QuadTo(403.7f, 847.8f, 388.6f, 857.2f),
                        PathNode.Close,
                        PathNode.MoveTo(991.7f, 182.3f),
                        PathNode.VerticalTo(210.0f),
                        PathNode.QuadTo(991.7f, 231.3f, 978.2f, 242.3f),
                        PathNode.QuadTo(964.8f, 253.3f, 945.6f, 253.3f),
                        PathNode.HorizontalTo(170.4f),
                        PathNode.QuadTo(150.8f, 253.3f, 137.6f, 241.8f),
                        PathNode.QuadTo(124.3f, 230.4f, 124.3f, 210.9f),
                        PathNode.VerticalTo(183.2f),
                        PathNode.QuadTo(124.3f, 161.8f, 137.1f, 150.4f),
                        PathNode.QuadTo(149.9f, 138.9f, 170.4f, 138.9f),
                        PathNode.HorizontalTo(945.6f),
                        PathNode.QuadTo(964.8f, 138.9f, 978.2f, 149.9f),
                        PathNode.QuadTo(991.7f, 160.9f, 991.7f, 182.3f),
                        PathNode.Close,
                        PathNode.MoveTo(991.7f, 496.0f),
                        PathNode.VerticalTo(523.7f),
                        PathNode.QuadTo(991.7f, 545.1f, 978.2f, 556.1f),
                        PathNode.QuadTo(964.8f, 567.1f, 945.6f, 567.1f),
                        PathNode.HorizontalTo(170.4f),
                        PathNode.QuadTo(150.8f, 567.1f, 137.6f, 555.6f),
                        PathNode.QuadTo(124.3f, 544.2f, 124.3f, 524.7f),
                        PathNode.VerticalTo(497.0f),
                        PathNode.QuadTo(124.3f, 475.6f, 137.1f, 464.2f),
                        PathNode.QuadTo(149.9f, 452.7f, 170.4f, 452.7f),
                        PathNode.HorizontalTo(945.6f),
                        PathNode.QuadTo(964.8f, 452.7f, 978.2f, 463.7f),
                        PathNode.QuadTo(991.7f, 474.7f, 991.7f, 496.0f),
                        PathNode.Close,
                        PathNode.MoveTo(991.7f, 807.0f),
                        PathNode.VerticalTo(834.7f),
                        PathNode.QuadTo(991.7f, 856.1f, 978.2f, 867.1f),
                        PathNode.QuadTo(964.8f, 878.1f, 945.6f, 878.1f),
                        PathNode.HorizontalTo(546.0f),
                        PathNode.QuadTo(526.4f, 878.1f, 513.2f, 866.6f),
                        PathNode.QuadTo(499.8f, 855.2f, 499.8f, 835.6f),
                        PathNode.VerticalTo(808.0f),
                        PathNode.QuadTo(499.8f, 786.6f, 512.7f, 775.2f),
                        PathNode.QuadTo(525.5f, 763.7f, 546.0f, 763.7f),
                        PathNode.HorizontalTo(945.6f),
                        PathNode.QuadTo(964.8f, 763.7f, 978.2f, 774.7f),
                        PathNode.QuadTo(991.7f, 785.7f, 991.7f, 807.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playlistDemibold!!
    }

private var _playlistDemibold: ImageVector? = null
