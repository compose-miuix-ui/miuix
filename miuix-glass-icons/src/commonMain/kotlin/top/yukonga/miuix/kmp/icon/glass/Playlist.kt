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

val MiuixIcons.Glass.Playlist: ImageVector
    get() = MiuixIcons.Glass.Regular.Playlist

val MiuixIcons.Glass.Light.Playlist: ImageVector
    get() {
        if (_playlistLight != null) return _playlistLight!!
        _playlistLight = ImageVector.Builder(
            name = "Playlist.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(364.7f, 874.8f),
                        PathNode.LineTo(169.9f, 986.4f),
                        PathNode.QuadTo(161.5f, 991.0f, 152.9f, 989.9f),
                        PathNode.QuadTo(144.3f, 988.8f, 139.0f, 982.0f),
                        PathNode.QuadTo(133.8f, 975.3f, 133.8f, 965.4f),
                        PathNode.LineTo(133.8f, 740.8f),
                        PathNode.QuadTo(133.8f, 731.5f, 139.0f, 724.7f),
                        PathNode.QuadTo(144.3f, 717.9f, 152.9f, 716.8f),
                        PathNode.QuadTo(161.5f, 715.7f, 169.9f, 720.4f),
                        PathNode.LineTo(364.7f, 831.9f),
                        PathNode.QuadTo(373.0f, 836.6f, 375.8f, 845.0f),
                        PathNode.QuadTo(378.7f, 853.4f, 375.8f, 861.8f),
                        PathNode.QuadTo(373.0f, 870.2f, 364.7f, 874.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1018.0f, 171.4f),
                        PathNode.LineTo(1018.0f, 193.4f),
                        PathNode.QuadTo(1018.0f, 203.0f, 1012.4f, 207.5f),
                        PathNode.QuadTo(1006.8f, 212.0f, 997.7f, 212.0f),
                        PathNode.LineTo(145.2f, 212.0f),
                        PathNode.QuadTo(136.7f, 212.0f, 130.8f, 207.3f),
                        PathNode.QuadTo(124.9f, 202.5f, 124.9f, 193.9f),
                        PathNode.LineTo(124.9f, 171.9f),
                        PathNode.QuadTo(124.9f, 162.3f, 130.5f, 157.5f),
                        PathNode.QuadTo(136.1f, 152.7f, 145.2f, 152.7f),
                        PathNode.LineTo(997.7f, 152.7f),
                        PathNode.QuadTo(1006.8f, 152.7f, 1012.4f, 157.2f),
                        PathNode.QuadTo(1018.0f, 161.7f, 1018.0f, 171.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1018.0f, 511.4f),
                        PathNode.LineTo(1018.0f, 533.4f),
                        PathNode.QuadTo(1018.0f, 543.0f, 1012.4f, 547.5f),
                        PathNode.QuadTo(1006.8f, 552.0f, 997.7f, 552.0f),
                        PathNode.LineTo(145.2f, 552.0f),
                        PathNode.QuadTo(136.7f, 552.0f, 130.8f, 547.3f),
                        PathNode.QuadTo(124.9f, 542.5f, 124.9f, 533.9f),
                        PathNode.LineTo(124.9f, 511.9f),
                        PathNode.QuadTo(124.9f, 502.3f, 130.5f, 497.5f),
                        PathNode.QuadTo(136.1f, 492.7f, 145.2f, 492.7f),
                        PathNode.LineTo(997.7f, 492.7f),
                        PathNode.QuadTo(1006.8f, 492.7f, 1012.4f, 497.2f),
                        PathNode.QuadTo(1018.0f, 501.7f, 1018.0f, 511.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1018.0f, 848.4f),
                        PathNode.LineTo(1018.0f, 870.4f),
                        PathNode.QuadTo(1018.0f, 880.0f, 1012.4f, 884.5f),
                        PathNode.QuadTo(1006.8f, 889.0f, 997.7f, 889.0f),
                        PathNode.LineTo(533.2f, 889.0f),
                        PathNode.QuadTo(524.7f, 889.0f, 518.8f, 884.3f),
                        PathNode.QuadTo(512.9f, 879.5f, 512.9f, 870.9f),
                        PathNode.LineTo(512.9f, 848.9f),
                        PathNode.QuadTo(512.9f, 839.3f, 518.5f, 834.5f),
                        PathNode.QuadTo(524.1f, 829.7f, 533.2f, 829.7f),
                        PathNode.LineTo(997.7f, 829.7f),
                        PathNode.QuadTo(1006.8f, 829.7f, 1012.4f, 834.2f),
                        PathNode.QuadTo(1018.0f, 838.7f, 1018.0f, 848.4f),
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

val MiuixIcons.Glass.Normal.Playlist: ImageVector
    get() {
        if (_playlistNormal != null) return _playlistNormal!!
        _playlistNormal = ImageVector.Builder(
            name = "Playlist.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(374.1f, 878.9f),
                        PathNode.LineTo(169.6f, 996.2f),
                        PathNode.QuadTo(159.4f, 1001.8f, 148.8f, 1000.1f),
                        PathNode.QuadTo(138.2f, 998.3f, 131.4f, 990.0f),
                        PathNode.QuadTo(124.6f, 981.7f, 124.6f, 969.7f),
                        PathNode.LineTo(124.6f, 733.9f),
                        PathNode.QuadTo(124.6f, 722.7f, 131.4f, 714.4f),
                        PathNode.QuadTo(138.2f, 706.1f, 148.8f, 704.4f),
                        PathNode.QuadTo(159.4f, 702.6f, 169.6f, 708.2f),
                        PathNode.LineTo(374.1f, 825.6f),
                        PathNode.QuadTo(384.3f, 831.2f, 387.6f, 841.7f),
                        PathNode.QuadTo(390.9f, 852.2f, 387.6f, 862.7f),
                        PathNode.QuadTo(384.3f, 873.3f, 374.1f, 878.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1021.0f, 167.5f),
                        PathNode.LineTo(1021.0f, 195.0f),
                        PathNode.QuadTo(1021.0f, 208.3f, 1013.4f, 214.2f),
                        PathNode.QuadTo(1005.8f, 220.0f, 993.4f, 220.0f),
                        PathNode.LineTo(149.5f, 220.0f),
                        PathNode.QuadTo(137.9f, 220.0f, 129.8f, 213.7f),
                        PathNode.QuadTo(121.8f, 207.4f, 121.8f, 195.8f),
                        PathNode.LineTo(121.8f, 168.3f),
                        PathNode.QuadTo(121.8f, 155.0f, 129.4f, 148.7f),
                        PathNode.QuadTo(137.0f, 142.4f, 149.5f, 142.4f),
                        PathNode.LineTo(993.4f, 142.4f),
                        PathNode.QuadTo(1005.8f, 142.4f, 1013.4f, 148.3f),
                        PathNode.QuadTo(1021.0f, 154.1f, 1021.0f, 167.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1021.0f, 507.5f),
                        PathNode.LineTo(1021.0f, 535.0f),
                        PathNode.QuadTo(1021.0f, 548.3f, 1013.4f, 554.2f),
                        PathNode.QuadTo(1005.8f, 560.0f, 993.4f, 560.0f),
                        PathNode.LineTo(149.5f, 560.0f),
                        PathNode.QuadTo(137.9f, 560.0f, 129.8f, 553.7f),
                        PathNode.QuadTo(121.8f, 547.4f, 121.8f, 535.8f),
                        PathNode.LineTo(121.8f, 508.3f),
                        PathNode.QuadTo(121.8f, 495.0f, 129.4f, 488.7f),
                        PathNode.QuadTo(137.0f, 482.4f, 149.5f, 482.4f),
                        PathNode.LineTo(993.4f, 482.4f),
                        PathNode.QuadTo(1005.8f, 482.4f, 1013.4f, 488.3f),
                        PathNode.QuadTo(1021.0f, 494.1f, 1021.0f, 507.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1021.0f, 844.5f),
                        PathNode.LineTo(1021.0f, 872.0f),
                        PathNode.QuadTo(1021.0f, 885.3f, 1013.4f, 891.2f),
                        PathNode.QuadTo(1005.8f, 897.0f, 993.4f, 897.0f),
                        PathNode.LineTo(537.5f, 897.0f),
                        PathNode.QuadTo(525.9f, 897.0f, 517.8f, 890.7f),
                        PathNode.QuadTo(509.8f, 884.4f, 509.8f, 872.8f),
                        PathNode.LineTo(509.8f, 845.3f),
                        PathNode.QuadTo(509.8f, 832.0f, 517.4f, 825.7f),
                        PathNode.QuadTo(525.0f, 819.4f, 537.5f, 819.4f),
                        PathNode.LineTo(993.4f, 819.4f),
                        PathNode.QuadTo(1005.8f, 819.4f, 1013.4f, 825.3f),
                        PathNode.QuadTo(1021.0f, 831.1f, 1021.0f, 844.5f),
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

val MiuixIcons.Glass.Regular.Playlist: ImageVector
    get() {
        if (_playlistRegular != null) return _playlistRegular!!
        _playlistRegular = ImageVector.Builder(
            name = "Playlist.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(378.4f, 880.7f),
                        PathNode.LineTo(169.4f, 1000.7f),
                        PathNode.QuadTo(158.4f, 1006.7f, 146.9f, 1004.7f),
                        PathNode.QuadTo(135.4f, 1002.7f, 127.9f, 993.7f),
                        PathNode.QuadTo(120.4f, 984.7f, 120.4f, 971.7f),
                        PathNode.LineTo(120.4f, 730.7f),
                        PathNode.QuadTo(120.4f, 718.7f, 127.9f, 709.7f),
                        PathNode.QuadTo(135.4f, 700.7f, 146.9f, 698.7f),
                        PathNode.QuadTo(158.4f, 696.7f, 169.4f, 702.7f),
                        PathNode.LineTo(378.4f, 822.7f),
                        PathNode.QuadTo(389.4f, 828.7f, 392.9f, 840.2f),
                        PathNode.QuadTo(396.4f, 851.7f, 392.9f, 863.2f),
                        PathNode.QuadTo(389.4f, 874.7f, 378.4f, 880.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 165.7f),
                        PathNode.LineTo(1022.4f, 195.7f),
                        PathNode.QuadTo(1022.4f, 210.7f, 1013.9f, 217.2f),
                        PathNode.QuadTo(1005.4f, 223.7f, 991.4f, 223.7f),
                        PathNode.LineTo(151.4f, 223.7f),
                        PathNode.QuadTo(138.4f, 223.7f, 129.4f, 216.7f),
                        PathNode.QuadTo(120.4f, 209.7f, 120.4f, 196.7f),
                        PathNode.LineTo(120.4f, 166.7f),
                        PathNode.QuadTo(120.4f, 151.7f, 128.9f, 144.7f),
                        PathNode.QuadTo(137.4f, 137.7f, 151.4f, 137.7f),
                        PathNode.LineTo(991.4f, 137.7f),
                        PathNode.QuadTo(1005.4f, 137.7f, 1013.9f, 144.2f),
                        PathNode.QuadTo(1022.4f, 150.7f, 1022.4f, 165.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 505.7f),
                        PathNode.LineTo(1022.4f, 535.7f),
                        PathNode.QuadTo(1022.4f, 550.7f, 1013.9f, 557.2f),
                        PathNode.QuadTo(1005.4f, 563.7f, 991.4f, 563.7f),
                        PathNode.LineTo(151.4f, 563.7f),
                        PathNode.QuadTo(138.4f, 563.7f, 129.4f, 556.7f),
                        PathNode.QuadTo(120.4f, 549.7f, 120.4f, 536.7f),
                        PathNode.LineTo(120.4f, 506.7f),
                        PathNode.QuadTo(120.4f, 491.7f, 128.9f, 484.7f),
                        PathNode.QuadTo(137.4f, 477.7f, 151.4f, 477.7f),
                        PathNode.LineTo(991.4f, 477.7f),
                        PathNode.QuadTo(1005.4f, 477.7f, 1013.9f, 484.2f),
                        PathNode.QuadTo(1022.4f, 490.7f, 1022.4f, 505.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 842.7f),
                        PathNode.LineTo(1022.4f, 872.7f),
                        PathNode.QuadTo(1022.4f, 887.7f, 1013.9f, 894.2f),
                        PathNode.QuadTo(1005.4f, 900.7f, 991.4f, 900.7f),
                        PathNode.LineTo(539.4f, 900.7f),
                        PathNode.QuadTo(526.4f, 900.7f, 517.4f, 893.7f),
                        PathNode.QuadTo(508.4f, 886.7f, 508.4f, 873.7f),
                        PathNode.LineTo(508.4f, 843.7f),
                        PathNode.QuadTo(508.4f, 828.7f, 516.9f, 821.7f),
                        PathNode.QuadTo(525.4f, 814.7f, 539.4f, 814.7f),
                        PathNode.LineTo(991.4f, 814.7f),
                        PathNode.QuadTo(1005.4f, 814.7f, 1013.9f, 821.2f),
                        PathNode.QuadTo(1022.4f, 827.7f, 1022.4f, 842.7f),
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

val MiuixIcons.Glass.Medium.Playlist: ImageVector
    get() {
        if (_playlistMedium != null) return _playlistMedium!!
        _playlistMedium = ImageVector.Builder(
            name = "Playlist.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(382.5f, 887.6f),
                        PathNode.LineTo(173.5f, 1007.6f),
                        PathNode.QuadTo(160.1f, 1015.3f, 145.6f, 1012.7f),
                        PathNode.QuadTo(131.1f, 1010.2f, 121.7f, 998.9f),
                        PathNode.QuadTo(112.3f, 987.6f, 112.3f, 971.6f),
                        PathNode.LineTo(112.3f, 730.6f),
                        PathNode.QuadTo(112.3f, 715.6f, 121.7f, 704.3f),
                        PathNode.QuadTo(131.1f, 693.0f, 145.6f, 690.5f),
                        PathNode.QuadTo(160.1f, 688.0f, 173.5f, 695.6f),
                        PathNode.LineTo(382.5f, 815.6f),
                        PathNode.QuadTo(395.9f, 823.2f, 400.5f, 837.4f),
                        PathNode.QuadTo(405.1f, 851.6f, 400.5f, 865.8f),
                        PathNode.QuadTo(395.9f, 880.0f, 382.5f, 887.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1030.5f, 165.6f),
                        PathNode.LineTo(1030.5f, 195.6f),
                        PathNode.QuadTo(1030.5f, 214.1f, 1019.6f, 222.9f),
                        PathNode.QuadTo(1008.7f, 231.7f, 991.4f, 231.7f),
                        PathNode.LineTo(151.4f, 231.7f),
                        PathNode.QuadTo(134.9f, 231.7f, 123.6f, 222.4f),
                        PathNode.QuadTo(112.3f, 213.1f, 112.3f, 196.6f),
                        PathNode.LineTo(112.3f, 166.6f),
                        PathNode.QuadTo(112.3f, 148.1f, 123.3f, 138.8f),
                        PathNode.QuadTo(134.2f, 129.5f, 151.4f, 129.5f),
                        PathNode.LineTo(991.4f, 129.5f),
                        PathNode.QuadTo(1008.7f, 129.5f, 1019.6f, 138.3f),
                        PathNode.QuadTo(1030.5f, 147.1f, 1030.5f, 165.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1030.5f, 505.6f),
                        PathNode.LineTo(1030.5f, 535.6f),
                        PathNode.QuadTo(1030.5f, 554.1f, 1019.6f, 562.9f),
                        PathNode.QuadTo(1008.7f, 571.7f, 991.4f, 571.7f),
                        PathNode.LineTo(151.4f, 571.7f),
                        PathNode.QuadTo(134.9f, 571.7f, 123.6f, 562.4f),
                        PathNode.QuadTo(112.3f, 553.1f, 112.3f, 536.6f),
                        PathNode.LineTo(112.3f, 506.6f),
                        PathNode.QuadTo(112.3f, 488.1f, 123.3f, 478.8f),
                        PathNode.QuadTo(134.2f, 469.5f, 151.4f, 469.5f),
                        PathNode.LineTo(991.4f, 469.5f),
                        PathNode.QuadTo(1008.7f, 469.5f, 1019.6f, 478.3f),
                        PathNode.QuadTo(1030.5f, 487.1f, 1030.5f, 505.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1030.5f, 842.6f),
                        PathNode.LineTo(1030.5f, 872.6f),
                        PathNode.QuadTo(1030.5f, 891.1f, 1019.6f, 899.9f),
                        PathNode.QuadTo(1008.7f, 908.7f, 991.4f, 908.7f),
                        PathNode.LineTo(547.5f, 908.7f),
                        PathNode.QuadTo(531.0f, 908.7f, 519.7f, 899.4f),
                        PathNode.QuadTo(508.4f, 890.1f, 508.4f, 873.6f),
                        PathNode.LineTo(508.4f, 843.6f),
                        PathNode.QuadTo(508.4f, 825.1f, 519.4f, 815.8f),
                        PathNode.QuadTo(530.3f, 806.5f, 547.5f, 806.5f),
                        PathNode.LineTo(991.4f, 806.5f),
                        PathNode.QuadTo(1008.7f, 806.5f, 1019.6f, 815.3f),
                        PathNode.QuadTo(1030.5f, 824.1f, 1030.5f, 842.6f),
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

val MiuixIcons.Glass.Demibold.Playlist: ImageVector
    get() {
        if (_playlistDemibold != null) return _playlistDemibold!!
        _playlistDemibold = ImageVector.Builder(
            name = "Playlist.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(385.3f, 892.5f),
                        PathNode.LineTo(176.3f, 1012.5f),
                        PathNode.QuadTo(161.2f, 1021.3f, 144.6f, 1018.3f),
                        PathNode.QuadTo(128.1f, 1015.4f, 117.4f, 1002.5f),
                        PathNode.QuadTo(106.6f, 989.6f, 106.6f, 971.6f),
                        PathNode.LineTo(106.6f, 730.6f),
                        PathNode.QuadTo(106.6f, 713.5f, 117.4f, 700.6f),
                        PathNode.QuadTo(128.1f, 687.7f, 144.6f, 684.8f),
                        PathNode.QuadTo(161.2f, 681.9f, 176.3f, 690.6f),
                        PathNode.LineTo(385.3f, 810.6f),
                        PathNode.QuadTo(400.5f, 819.4f, 405.8f, 835.5f),
                        PathNode.QuadTo(411.1f, 851.6f, 405.8f, 867.7f),
                        PathNode.QuadTo(400.5f, 883.8f, 385.3f, 892.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1036.2f, 165.6f),
                        PathNode.LineTo(1036.2f, 195.6f),
                        PathNode.QuadTo(1036.2f, 216.5f, 1023.6f, 226.9f),
                        PathNode.QuadTo(1010.9f, 237.3f, 991.4f, 237.3f),
                        PathNode.LineTo(151.4f, 237.3f),
                        PathNode.QuadTo(132.5f, 237.3f, 119.5f, 226.4f),
                        PathNode.QuadTo(106.6f, 215.5f, 106.6f, 196.6f),
                        PathNode.LineTo(106.6f, 166.6f),
                        PathNode.QuadTo(106.6f, 145.6f, 119.3f, 134.7f),
                        PathNode.QuadTo(131.9f, 123.8f, 151.4f, 123.8f),
                        PathNode.LineTo(991.4f, 123.8f),
                        PathNode.QuadTo(1010.9f, 123.8f, 1023.6f, 134.2f),
                        PathNode.QuadTo(1036.2f, 144.6f, 1036.2f, 165.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1036.2f, 505.6f),
                        PathNode.LineTo(1036.2f, 535.6f),
                        PathNode.QuadTo(1036.2f, 556.5f, 1023.6f, 566.9f),
                        PathNode.QuadTo(1010.9f, 577.3f, 991.4f, 577.3f),
                        PathNode.LineTo(151.4f, 577.3f),
                        PathNode.QuadTo(132.5f, 577.3f, 119.5f, 566.4f),
                        PathNode.QuadTo(106.6f, 555.5f, 106.6f, 536.6f),
                        PathNode.LineTo(106.6f, 506.6f),
                        PathNode.QuadTo(106.6f, 485.6f, 119.3f, 474.7f),
                        PathNode.QuadTo(131.9f, 463.8f, 151.4f, 463.8f),
                        PathNode.LineTo(991.4f, 463.8f),
                        PathNode.QuadTo(1010.9f, 463.8f, 1023.6f, 474.2f),
                        PathNode.QuadTo(1036.2f, 484.6f, 1036.2f, 505.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1036.2f, 842.6f),
                        PathNode.LineTo(1036.2f, 872.6f),
                        PathNode.QuadTo(1036.2f, 893.5f, 1023.6f, 903.9f),
                        PathNode.QuadTo(1010.9f, 914.3f, 991.4f, 914.3f),
                        PathNode.LineTo(553.2f, 914.3f),
                        PathNode.QuadTo(534.2f, 914.3f, 521.3f, 903.4f),
                        PathNode.QuadTo(508.4f, 892.5f, 508.4f, 873.6f),
                        PathNode.LineTo(508.4f, 843.6f),
                        PathNode.QuadTo(508.4f, 822.6f, 521.1f, 811.7f),
                        PathNode.QuadTo(533.7f, 800.8f, 553.2f, 800.8f),
                        PathNode.LineTo(991.4f, 800.8f),
                        PathNode.QuadTo(1010.9f, 800.8f, 1023.6f, 811.2f),
                        PathNode.QuadTo(1036.2f, 821.6f, 1036.2f, 842.6f),
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
