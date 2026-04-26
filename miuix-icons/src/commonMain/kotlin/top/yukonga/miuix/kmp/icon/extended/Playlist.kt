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
                        PathNode.MoveTo(329.4f, 838.9f),
                        PathNode.LineTo(134.4f, 950.9f),
                        PathNode.QuadTo(126.4f, 955.9f, 117.9f, 954.4f),
                        PathNode.QuadTo(109.5f, 952.9f, 103.9f, 946.2f),
                        PathNode.QuadTo(98.4f, 939.6f, 98.4f, 929.9f),
                        PathNode.VerticalTo(704.9f),
                        PathNode.QuadTo(98.4f, 696.1f, 103.9f, 689.5f),
                        PathNode.QuadTo(109.5f, 682.8f, 117.9f, 681.4f),
                        PathNode.QuadTo(126.4f, 679.9f, 134.4f, 684.9f),
                        PathNode.LineTo(329.4f, 796.9f),
                        PathNode.QuadTo(337.4f, 801.9f, 340.4f, 809.9f),
                        PathNode.QuadTo(343.4f, 817.9f, 340.4f, 825.9f),
                        PathNode.QuadTo(337.4f, 833.9f, 329.4f, 838.9f),
                        PathNode.Close,
                        PathNode.MoveTo(983.4f, 136.4f),
                        PathNode.VerticalTo(157.4f),
                        PathNode.QuadTo(983.4f, 167.9f, 977.6f, 172.4f),
                        PathNode.QuadTo(971.9f, 176.9f, 962.4f, 176.9f),
                        PathNode.HorizontalTo(110.4f),
                        PathNode.QuadTo(101.6f, 176.9f, 95.5f, 171.9f),
                        PathNode.QuadTo(89.4f, 166.9f, 89.4f, 158.9f),
                        PathNode.VerticalTo(136.9f),
                        PathNode.QuadTo(89.4f, 126.6f, 95.2f, 121.7f),
                        PathNode.QuadTo(100.9f, 116.9f, 110.4f, 116.9f),
                        PathNode.HorizontalTo(962.4f),
                        PathNode.QuadTo(971.9f, 116.9f, 977.6f, 121.4f),
                        PathNode.QuadTo(983.4f, 125.9f, 983.4f, 136.4f),
                        PathNode.Close,
                        PathNode.MoveTo(983.4f, 476.4f),
                        PathNode.VerticalTo(497.4f),
                        PathNode.QuadTo(983.4f, 507.9f, 977.6f, 512.4f),
                        PathNode.QuadTo(971.9f, 516.9f, 962.4f, 516.9f),
                        PathNode.HorizontalTo(110.4f),
                        PathNode.QuadTo(101.6f, 516.9f, 95.5f, 511.9f),
                        PathNode.QuadTo(89.4f, 506.9f, 89.4f, 498.9f),
                        PathNode.VerticalTo(476.9f),
                        PathNode.QuadTo(89.4f, 466.6f, 95.2f, 461.7f),
                        PathNode.QuadTo(100.9f, 456.9f, 110.4f, 456.9f),
                        PathNode.HorizontalTo(962.4f),
                        PathNode.QuadTo(971.9f, 456.9f, 977.6f, 461.4f),
                        PathNode.QuadTo(983.4f, 465.9f, 983.4f, 476.4f),
                        PathNode.Close,
                        PathNode.MoveTo(983.4f, 813.4f),
                        PathNode.VerticalTo(834.4f),
                        PathNode.QuadTo(983.4f, 844.9f, 977.6f, 849.4f),
                        PathNode.QuadTo(971.9f, 853.9f, 962.4f, 853.9f),
                        PathNode.HorizontalTo(498.4f),
                        PathNode.QuadTo(489.6f, 853.9f, 483.5f, 848.9f),
                        PathNode.QuadTo(477.4f, 843.9f, 477.4f, 835.9f),
                        PathNode.VerticalTo(813.9f),
                        PathNode.QuadTo(477.4f, 803.6f, 483.2f, 798.7f),
                        PathNode.QuadTo(488.9f, 793.9f, 498.4f, 793.9f),
                        PathNode.HorizontalTo(962.4f),
                        PathNode.QuadTo(971.9f, 793.9f, 977.6f, 798.4f),
                        PathNode.QuadTo(983.4f, 802.9f, 983.4f, 813.4f),
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
                        PathNode.MoveTo(342.3f, 846.3f),
                        PathNode.LineTo(137.7f, 963.8f),
                        PathNode.QuadTo(127.6f, 969.5f, 117.1f, 967.7f),
                        PathNode.QuadTo(106.5f, 965.8f, 99.6f, 957.6f),
                        PathNode.QuadTo(92.8f, 949.3f, 92.8f, 937.3f),
                        PathNode.VerticalTo(701.3f),
                        PathNode.QuadTo(92.8f, 690.3f, 99.6f, 682.1f),
                        PathNode.QuadTo(106.5f, 673.8f, 117.1f, 672.0f),
                        PathNode.QuadTo(127.6f, 670.1f, 137.7f, 675.8f),
                        PathNode.LineTo(342.3f, 793.3f),
                        PathNode.QuadTo(352.4f, 799.0f, 355.7f, 809.4f),
                        PathNode.QuadTo(359.1f, 819.8f, 355.7f, 830.2f),
                        PathNode.QuadTo(352.4f, 840.6f, 342.3f, 846.3f),
                        PathNode.Close,
                        PathNode.MoveTo(989.4f, 135.2f),
                        PathNode.VerticalTo(162.4f),
                        PathNode.QuadTo(989.4f, 176.0f, 981.8f, 181.9f),
                        PathNode.QuadTo(974.2f, 187.8f, 961.6f, 187.8f),
                        PathNode.HorizontalTo(117.8f),
                        PathNode.QuadTo(106.1f, 187.8f, 98.0f, 181.4f),
                        PathNode.QuadTo(89.9f, 175.0f, 89.9f, 163.6f),
                        PathNode.VerticalTo(136.1f),
                        PathNode.QuadTo(89.9f, 122.5f, 97.6f, 116.2f),
                        PathNode.QuadTo(105.2f, 109.9f, 117.8f, 109.9f),
                        PathNode.HorizontalTo(961.6f),
                        PathNode.QuadTo(974.2f, 109.9f, 981.8f, 115.8f),
                        PathNode.QuadTo(989.4f, 121.6f, 989.4f, 135.2f),
                        PathNode.Close,
                        PathNode.MoveTo(989.4f, 475.2f),
                        PathNode.VerticalTo(502.4f),
                        PathNode.QuadTo(989.4f, 516.0f, 981.8f, 521.9f),
                        PathNode.QuadTo(974.2f, 527.8f, 961.6f, 527.8f),
                        PathNode.HorizontalTo(117.8f),
                        PathNode.QuadTo(106.1f, 527.8f, 98.0f, 521.4f),
                        PathNode.QuadTo(89.9f, 515.0f, 89.9f, 503.6f),
                        PathNode.VerticalTo(476.1f),
                        PathNode.QuadTo(89.9f, 462.5f, 97.6f, 456.2f),
                        PathNode.QuadTo(105.2f, 449.9f, 117.8f, 449.9f),
                        PathNode.HorizontalTo(961.6f),
                        PathNode.QuadTo(974.2f, 449.9f, 981.8f, 455.8f),
                        PathNode.QuadTo(989.4f, 461.6f, 989.4f, 475.2f),
                        PathNode.Close,
                        PathNode.MoveTo(989.4f, 812.2f),
                        PathNode.VerticalTo(839.4f),
                        PathNode.QuadTo(989.4f, 853.0f, 981.8f, 858.9f),
                        PathNode.QuadTo(974.2f, 864.8f, 961.6f, 864.8f),
                        PathNode.HorizontalTo(505.8f),
                        PathNode.QuadTo(494.1f, 864.8f, 486.0f, 858.4f),
                        PathNode.QuadTo(477.9f, 852.0f, 477.9f, 840.6f),
                        PathNode.VerticalTo(813.1f),
                        PathNode.QuadTo(477.9f, 799.5f, 485.6f, 793.2f),
                        PathNode.QuadTo(493.2f, 786.9f, 505.8f, 786.9f),
                        PathNode.HorizontalTo(961.6f),
                        PathNode.QuadTo(974.2f, 786.9f, 981.8f, 792.8f),
                        PathNode.QuadTo(989.4f, 798.6f, 989.4f, 812.2f),
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
                        PathNode.MoveTo(348.2f, 849.7f),
                        PathNode.LineTo(139.2f, 969.7f),
                        PathNode.QuadTo(128.2f, 975.7f, 116.7f, 973.7f),
                        PathNode.QuadTo(105.2f, 971.7f, 97.7f, 962.7f),
                        PathNode.QuadTo(90.2f, 953.7f, 90.2f, 940.7f),
                        PathNode.VerticalTo(699.7f),
                        PathNode.QuadTo(90.2f, 687.7f, 97.7f, 678.7f),
                        PathNode.QuadTo(105.2f, 669.7f, 116.7f, 667.7f),
                        PathNode.QuadTo(128.2f, 665.7f, 139.2f, 671.7f),
                        PathNode.LineTo(348.2f, 791.7f),
                        PathNode.QuadTo(359.2f, 797.7f, 362.7f, 809.2f),
                        PathNode.QuadTo(366.2f, 820.7f, 362.7f, 832.2f),
                        PathNode.QuadTo(359.2f, 843.7f, 348.2f, 849.7f),
                        PathNode.Close,
                        PathNode.MoveTo(992.2f, 134.7f),
                        PathNode.VerticalTo(164.7f),
                        PathNode.QuadTo(992.2f, 179.7f, 983.7f, 186.2f),
                        PathNode.QuadTo(975.2f, 192.7f, 961.2f, 192.7f),
                        PathNode.HorizontalTo(121.2f),
                        PathNode.QuadTo(108.2f, 192.7f, 99.2f, 185.7f),
                        PathNode.QuadTo(90.2f, 178.7f, 90.2f, 165.7f),
                        PathNode.VerticalTo(135.7f),
                        PathNode.QuadTo(90.2f, 120.7f, 98.7f, 113.7f),
                        PathNode.QuadTo(107.2f, 106.7f, 121.2f, 106.7f),
                        PathNode.HorizontalTo(961.2f),
                        PathNode.QuadTo(975.2f, 106.7f, 983.7f, 113.2f),
                        PathNode.QuadTo(992.2f, 119.7f, 992.2f, 134.7f),
                        PathNode.Close,
                        PathNode.MoveTo(992.2f, 474.7f),
                        PathNode.VerticalTo(504.7f),
                        PathNode.QuadTo(992.2f, 519.7f, 983.7f, 526.2f),
                        PathNode.QuadTo(975.2f, 532.7f, 961.2f, 532.7f),
                        PathNode.HorizontalTo(121.2f),
                        PathNode.QuadTo(108.2f, 532.7f, 99.2f, 525.7f),
                        PathNode.QuadTo(90.2f, 518.7f, 90.2f, 505.7f),
                        PathNode.VerticalTo(475.7f),
                        PathNode.QuadTo(90.2f, 460.7f, 98.7f, 453.7f),
                        PathNode.QuadTo(107.2f, 446.7f, 121.2f, 446.7f),
                        PathNode.HorizontalTo(961.2f),
                        PathNode.QuadTo(975.2f, 446.7f, 983.7f, 453.2f),
                        PathNode.QuadTo(992.2f, 459.7f, 992.2f, 474.7f),
                        PathNode.Close,
                        PathNode.MoveTo(992.2f, 811.7f),
                        PathNode.VerticalTo(841.7f),
                        PathNode.QuadTo(992.2f, 856.7f, 983.7f, 863.2f),
                        PathNode.QuadTo(975.2f, 869.7f, 961.2f, 869.7f),
                        PathNode.HorizontalTo(509.2f),
                        PathNode.QuadTo(496.2f, 869.7f, 487.2f, 862.7f),
                        PathNode.QuadTo(478.2f, 855.7f, 478.2f, 842.7f),
                        PathNode.VerticalTo(812.7f),
                        PathNode.QuadTo(478.2f, 797.7f, 486.7f, 790.7f),
                        PathNode.QuadTo(495.2f, 783.7f, 509.2f, 783.7f),
                        PathNode.HorizontalTo(961.2f),
                        PathNode.QuadTo(975.2f, 783.7f, 983.7f, 790.2f),
                        PathNode.QuadTo(992.2f, 796.7f, 992.2f, 811.7f),
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
                        PathNode.MoveTo(362.2f, 866.3f),
                        PathNode.LineTo(153.2f, 986.3f),
                        PathNode.QuadTo(139.8f, 994.1f, 125.4f, 991.5f),
                        PathNode.QuadTo(111.0f, 988.9f, 101.4f, 977.6f),
                        PathNode.QuadTo(91.8f, 966.2f, 91.8f, 950.3f),
                        PathNode.VerticalTo(709.3f),
                        PathNode.QuadTo(91.8f, 694.3f, 101.4f, 683.0f),
                        PathNode.QuadTo(111.0f, 671.6f, 125.4f, 669.1f),
                        PathNode.QuadTo(139.8f, 666.5f, 153.2f, 674.2f),
                        PathNode.LineTo(362.2f, 794.2f),
                        PathNode.QuadTo(375.6f, 802.0f, 380.2f, 816.1f),
                        PathNode.QuadTo(384.9f, 830.3f, 380.2f, 844.4f),
                        PathNode.QuadTo(375.6f, 858.6f, 362.2f, 866.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1010.3f, 144.3f),
                        PathNode.VerticalTo(174.3f),
                        PathNode.QuadTo(1010.3f, 192.8f, 999.2f, 201.7f),
                        PathNode.QuadTo(988.0f, 210.5f, 971.1f, 210.5f),
                        PathNode.HorizontalTo(131.1f),
                        PathNode.QuadTo(114.6f, 210.5f, 103.2f, 201.2f),
                        PathNode.QuadTo(91.8f, 191.8f, 91.8f, 175.3f),
                        PathNode.VerticalTo(145.3f),
                        PathNode.QuadTo(91.8f, 126.8f, 102.7f, 117.4f),
                        PathNode.QuadTo(113.6f, 108.1f, 131.1f, 108.1f),
                        PathNode.HorizontalTo(971.1f),
                        PathNode.QuadTo(988.0f, 108.1f, 999.2f, 116.9f),
                        PathNode.QuadTo(1010.3f, 125.8f, 1010.3f, 144.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1010.3f, 484.3f),
                        PathNode.VerticalTo(514.3f),
                        PathNode.QuadTo(1010.3f, 532.8f, 999.2f, 541.7f),
                        PathNode.QuadTo(988.0f, 550.5f, 971.1f, 550.5f),
                        PathNode.HorizontalTo(131.1f),
                        PathNode.QuadTo(114.6f, 550.5f, 103.2f, 541.2f),
                        PathNode.QuadTo(91.8f, 531.8f, 91.8f, 515.3f),
                        PathNode.VerticalTo(485.3f),
                        PathNode.QuadTo(91.8f, 466.8f, 102.7f, 457.4f),
                        PathNode.QuadTo(113.6f, 448.1f, 131.1f, 448.1f),
                        PathNode.HorizontalTo(971.1f),
                        PathNode.QuadTo(988.0f, 448.1f, 999.2f, 456.9f),
                        PathNode.QuadTo(1010.3f, 465.8f, 1010.3f, 484.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1010.3f, 821.3f),
                        PathNode.VerticalTo(851.3f),
                        PathNode.QuadTo(1010.3f, 869.8f, 999.2f, 878.7f),
                        PathNode.QuadTo(988.0f, 887.5f, 971.1f, 887.5f),
                        PathNode.HorizontalTo(527.3f),
                        PathNode.QuadTo(510.8f, 887.5f, 499.4f, 878.2f),
                        PathNode.QuadTo(488.1f, 868.8f, 488.1f, 852.3f),
                        PathNode.VerticalTo(822.3f),
                        PathNode.QuadTo(488.1f, 803.8f, 498.9f, 794.4f),
                        PathNode.QuadTo(509.8f, 785.1f, 527.3f, 785.1f),
                        PathNode.HorizontalTo(971.1f),
                        PathNode.QuadTo(988.0f, 785.1f, 999.2f, 793.9f),
                        PathNode.QuadTo(1010.3f, 802.8f, 1010.3f, 821.3f),
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
                        PathNode.MoveTo(372.0f, 878.0f),
                        PathNode.LineTo(163.0f, 998.0f),
                        PathNode.QuadTo(148.0f, 1007.0f, 131.5f, 1004.0f),
                        PathNode.QuadTo(115.0f, 1001.0f, 104.0f, 988.0f),
                        PathNode.QuadTo(93.0f, 975.0f, 93.0f, 957.0f),
                        PathNode.VerticalTo(716.0f),
                        PathNode.QuadTo(93.0f, 699.0f, 104.0f, 686.0f),
                        PathNode.QuadTo(115.0f, 673.0f, 131.5f, 670.0f),
                        PathNode.QuadTo(148.0f, 667.0f, 163.0f, 676.0f),
                        PathNode.LineTo(372.0f, 796.0f),
                        PathNode.QuadTo(387.0f, 805.0f, 392.5f, 821.0f),
                        PathNode.QuadTo(398.0f, 837.0f, 392.5f, 853.0f),
                        PathNode.QuadTo(387.0f, 869.0f, 372.0f, 878.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1023.0f, 151.0f),
                        PathNode.VerticalTo(181.0f),
                        PathNode.QuadTo(1023.0f, 202.0f, 1010.0f, 212.5f),
                        PathNode.QuadTo(997.0f, 223.0f, 978.0f, 223.0f),
                        PathNode.HorizontalTo(138.0f),
                        PathNode.QuadTo(119.0f, 223.0f, 106.0f, 212.0f),
                        PathNode.QuadTo(93.0f, 201.0f, 93.0f, 182.0f),
                        PathNode.VerticalTo(152.0f),
                        PathNode.QuadTo(93.0f, 131.0f, 105.5f, 120.0f),
                        PathNode.QuadTo(118.0f, 109.0f, 138.0f, 109.0f),
                        PathNode.HorizontalTo(978.0f),
                        PathNode.QuadTo(997.0f, 109.0f, 1010.0f, 119.5f),
                        PathNode.QuadTo(1023.0f, 130.0f, 1023.0f, 151.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1023.0f, 491.0f),
                        PathNode.VerticalTo(521.0f),
                        PathNode.QuadTo(1023.0f, 542.0f, 1010.0f, 552.5f),
                        PathNode.QuadTo(997.0f, 563.0f, 978.0f, 563.0f),
                        PathNode.HorizontalTo(138.0f),
                        PathNode.QuadTo(119.0f, 563.0f, 106.0f, 552.0f),
                        PathNode.QuadTo(93.0f, 541.0f, 93.0f, 522.0f),
                        PathNode.VerticalTo(492.0f),
                        PathNode.QuadTo(93.0f, 471.0f, 105.5f, 460.0f),
                        PathNode.QuadTo(118.0f, 449.0f, 138.0f, 449.0f),
                        PathNode.HorizontalTo(978.0f),
                        PathNode.QuadTo(997.0f, 449.0f, 1010.0f, 459.5f),
                        PathNode.QuadTo(1023.0f, 470.0f, 1023.0f, 491.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1023.0f, 828.0f),
                        PathNode.VerticalTo(858.0f),
                        PathNode.QuadTo(1023.0f, 879.0f, 1010.0f, 889.5f),
                        PathNode.QuadTo(997.0f, 900.0f, 978.0f, 900.0f),
                        PathNode.HorizontalTo(540.0f),
                        PathNode.QuadTo(521.0f, 900.0f, 508.0f, 889.0f),
                        PathNode.QuadTo(495.0f, 878.0f, 495.0f, 859.0f),
                        PathNode.VerticalTo(829.0f),
                        PathNode.QuadTo(495.0f, 808.0f, 507.5f, 797.0f),
                        PathNode.QuadTo(520.0f, 786.0f, 540.0f, 786.0f),
                        PathNode.HorizontalTo(978.0f),
                        PathNode.QuadTo(997.0f, 786.0f, 1010.0f, 796.5f),
                        PathNode.QuadTo(1023.0f, 807.0f, 1023.0f, 828.0f),
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
