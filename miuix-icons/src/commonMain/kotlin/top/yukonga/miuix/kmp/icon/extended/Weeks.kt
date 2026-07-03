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

val MiuixIcons.Weeks: ImageVector
    get() = MiuixIcons.Regular.Weeks

val MiuixIcons.Light.Weeks: ImageVector
    get() {
        if (_weeksLight != null) return _weeksLight!!
        _weeksLight = ImageVector.Builder(
            name = "Weeks.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1176.0f,
            viewportHeight = 1176.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1176.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(843.8f, 621.8f),
                        PathNode.VerticalTo(641.5f),
                        PathNode.QuadTo(843.8f, 653.8f, 838.8f, 659.3f),
                        PathNode.QuadTo(833.9f, 664.8f, 822.7f, 664.8f),
                        PathNode.HorizontalTo(355.5f),
                        PathNode.QuadTo(345.1f, 664.8f, 340.1f, 659.3f),
                        PathNode.QuadTo(335.1f, 653.8f, 335.1f, 641.5f),
                        PathNode.VerticalTo(621.8f),
                        PathNode.QuadTo(335.1f, 609.5f, 339.7f, 604.5f),
                        PathNode.QuadTo(344.3f, 599.4f, 355.5f, 599.4f),
                        PathNode.HorizontalTo(822.7f),
                        PathNode.QuadTo(833.9f, 599.4f, 838.8f, 604.5f),
                        PathNode.QuadTo(843.8f, 609.5f, 843.8f, 621.8f),
                        PathNode.Close,
                        PathNode.MoveTo(965.2f, 180.2f),
                        PathNode.QuadTo(1008.6f, 202.1f, 1028.3f, 243.4f),
                        PathNode.QuadTo(1039.6f, 265.1f, 1042.4f, 296.5f),
                        PathNode.QuadTo(1045.2f, 327.9f, 1045.2f, 403.2f),
                        PathNode.VerticalTo(772.8f),
                        PathNode.QuadTo(1045.2f, 848.1f, 1042.4f, 879.1f),
                        PathNode.QuadTo(1039.6f, 910.0f, 1028.3f, 931.7f),
                        PathNode.QuadTo(1018.1f, 952.4f, 1002.0f, 968.9f),
                        PathNode.QuadTo(985.8f, 985.4f, 965.2f, 995.8f),
                        PathNode.QuadTo(943.5f, 1006.2f, 911.4f, 1008.9f),
                        PathNode.QuadTo(879.3f, 1011.7f, 806.3f, 1011.7f),
                        PathNode.HorizontalTo(369.7f),
                        PathNode.QuadTo(296.7f, 1011.7f, 264.6f, 1008.9f),
                        PathNode.QuadTo(232.5f, 1006.2f, 210.8f, 995.8f),
                        PathNode.QuadTo(190.2f, 985.4f, 174.0f, 968.9f),
                        PathNode.QuadTo(157.9f, 952.4f, 147.7f, 931.7f),
                        PathNode.QuadTo(136.4f, 910.0f, 133.6f, 879.1f),
                        PathNode.QuadTo(130.8f, 848.1f, 130.8f, 772.8f),
                        PathNode.VerticalTo(403.2f),
                        PathNode.QuadTo(130.8f, 327.9f, 133.6f, 296.5f),
                        PathNode.QuadTo(136.4f, 265.1f, 147.7f, 243.4f),
                        PathNode.QuadTo(167.4f, 202.1f, 210.8f, 180.2f),
                        PathNode.QuadTo(232.5f, 169.8f, 264.6f, 167.1f),
                        PathNode.QuadTo(296.7f, 164.3f, 369.7f, 164.3f),
                        PathNode.HorizontalTo(806.3f),
                        PathNode.QuadTo(879.3f, 164.3f, 911.4f, 167.1f),
                        PathNode.QuadTo(943.5f, 169.8f, 965.2f, 180.2f),
                        PathNode.Close,
                        PathNode.MoveTo(243.5f, 241.5f),
                        PathNode.QuadTo(220.8f, 252.5f, 208.2f, 276.9f),
                        PathNode.QuadTo(201.8f, 287.9f, 200.5f, 303.9f),
                        PathNode.QuadTo(199.1f, 320.0f, 199.1f, 356.7f),
                        PathNode.VerticalTo(695.7f),
                        PathNode.QuadTo(199.1f, 732.7f, 200.5f, 748.2f),
                        PathNode.QuadTo(201.8f, 763.8f, 208.2f, 775.6f),
                        PathNode.QuadTo(220.8f, 799.9f, 243.5f, 810.9f),
                        PathNode.QuadTo(255.3f, 818.0f, 271.0f, 819.4f),
                        PathNode.QuadTo(286.6f, 820.8f, 323.4f, 820.8f),
                        PathNode.HorizontalTo(852.7f),
                        PathNode.QuadTo(889.5f, 820.8f, 905.1f, 819.4f),
                        PathNode.QuadTo(920.7f, 818.0f, 932.5f, 810.9f),
                        PathNode.QuadTo(955.3f, 799.9f, 967.8f, 775.6f),
                        PathNode.QuadTo(974.2f, 763.8f, 975.5f, 748.2f),
                        PathNode.QuadTo(976.9f, 732.7f, 976.9f, 695.7f),
                        PathNode.VerticalTo(356.7f),
                        PathNode.QuadTo(976.9f, 320.0f, 975.5f, 303.9f),
                        PathNode.QuadTo(974.2f, 287.9f, 967.8f, 276.9f),
                        PathNode.QuadTo(955.3f, 252.5f, 932.5f, 241.5f),
                        PathNode.QuadTo(920.7f, 235.2f, 904.3f, 233.4f),
                        PathNode.QuadTo(888.0f, 231.6f, 852.7f, 231.6f),
                        PathNode.HorizontalTo(323.4f),
                        PathNode.QuadTo(288.2f, 231.6f, 271.7f, 233.4f),
                        PathNode.QuadTo(255.3f, 235.2f, 243.5f, 241.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _weeksLight!!
    }

private var _weeksLight: ImageVector? = null

val MiuixIcons.Normal.Weeks: ImageVector
    get() {
        if (_weeksNormal != null) return _weeksNormal!!
        _weeksNormal = ImageVector.Builder(
            name = "Weeks.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1190.8f,
            viewportHeight = 1190.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1190.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(850.9f, 626.4f),
                        PathNode.VerticalTo(650.9f),
                        PathNode.QuadTo(850.9f, 668.0f, 844.6f, 675.2f),
                        PathNode.QuadTo(838.3f, 682.5f, 822.9f, 682.5f),
                        PathNode.HorizontalTo(371.4f),
                        PathNode.QuadTo(356.2f, 682.5f, 349.8f, 675.2f),
                        PathNode.QuadTo(343.5f, 668.0f, 343.5f, 650.9f),
                        PathNode.VerticalTo(626.4f),
                        PathNode.QuadTo(343.5f, 609.3f, 349.8f, 602.5f),
                        PathNode.QuadTo(356.1f, 595.8f, 371.4f, 595.8f),
                        PathNode.HorizontalTo(822.9f),
                        PathNode.QuadTo(838.3f, 595.8f, 844.6f, 602.5f),
                        PathNode.QuadTo(850.9f, 609.3f, 850.9f, 626.4f),
                        PathNode.Close,
                        PathNode.MoveTo(974.3f, 182.5f),
                        PathNode.QuadTo(1020.4f, 206.5f, 1040.8f, 249.0f),
                        PathNode.QuadTo(1052.8f, 272.1f, 1055.6f, 304.5f),
                        PathNode.QuadTo(1058.4f, 336.9f, 1058.4f, 412.2f),
                        PathNode.VerticalTo(778.6f),
                        PathNode.QuadTo(1058.4f, 853.9f, 1055.6f, 885.8f),
                        PathNode.QuadTo(1052.8f, 917.8f, 1040.8f, 940.9f),
                        PathNode.QuadTo(1030.6f, 962.1f, 1013.1f, 979.7f),
                        PathNode.QuadTo(995.6f, 997.2f, 974.3f, 1008.3f),
                        PathNode.QuadTo(951.2f, 1019.4f, 919.2f, 1022.1f),
                        PathNode.QuadTo(887.1f, 1024.9f, 812.1f, 1024.9f),
                        PathNode.HorizontalTo(378.7f),
                        PathNode.QuadTo(303.7f, 1024.9f, 271.6f, 1022.1f),
                        PathNode.QuadTo(239.6f, 1019.4f, 216.5f, 1008.3f),
                        PathNode.QuadTo(195.2f, 997.2f, 177.7f, 979.7f),
                        PathNode.QuadTo(160.2f, 962.1f, 150.0f, 940.9f),
                        PathNode.QuadTo(138.0f, 917.8f, 135.2f, 885.8f),
                        PathNode.QuadTo(132.4f, 853.9f, 132.4f, 778.6f),
                        PathNode.VerticalTo(412.2f),
                        PathNode.QuadTo(132.4f, 336.9f, 135.2f, 304.5f),
                        PathNode.QuadTo(138.0f, 272.1f, 150.0f, 249.0f),
                        PathNode.QuadTo(170.4f, 206.5f, 216.5f, 182.5f),
                        PathNode.QuadTo(239.6f, 171.4f, 271.6f, 168.7f),
                        PathNode.QuadTo(303.7f, 165.9f, 378.7f, 165.9f),
                        PathNode.HorizontalTo(812.1f),
                        PathNode.QuadTo(887.1f, 165.9f, 919.2f, 168.7f),
                        PathNode.QuadTo(951.2f, 171.4f, 974.3f, 182.5f),
                        PathNode.Close,
                        PathNode.MoveTo(258.2f, 259.7f),
                        PathNode.QuadTo(237.5f, 270.0f, 227.1f, 290.8f),
                        PathNode.QuadTo(221.4f, 301.1f, 220.0f, 316.1f),
                        PathNode.QuadTo(218.6f, 331.1f, 218.6f, 365.7f),
                        PathNode.VerticalTo(702.3f),
                        PathNode.QuadTo(218.6f, 737.8f, 220.0f, 752.3f),
                        PathNode.QuadTo(221.4f, 766.9f, 227.1f, 777.2f),
                        PathNode.QuadTo(237.5f, 798.1f, 258.2f, 808.4f),
                        PathNode.QuadTo(268.6f, 814.1f, 283.5f, 815.5f),
                        PathNode.QuadTo(298.5f, 816.9f, 333.1f, 816.9f),
                        PathNode.HorizontalTo(857.7f),
                        PathNode.QuadTo(893.2f, 816.9f, 907.7f, 815.5f),
                        PathNode.QuadTo(922.2f, 814.1f, 932.6f, 808.4f),
                        PathNode.QuadTo(953.3f, 798.1f, 963.7f, 777.2f),
                        PathNode.QuadTo(969.4f, 766.9f, 970.8f, 752.3f),
                        PathNode.QuadTo(972.2f, 737.8f, 972.2f, 702.3f),
                        PathNode.VerticalTo(365.7f),
                        PathNode.QuadTo(972.2f, 331.1f, 970.8f, 316.1f),
                        PathNode.QuadTo(969.4f, 301.1f, 963.7f, 290.8f),
                        PathNode.QuadTo(953.3f, 270.0f, 932.6f, 259.7f),
                        PathNode.QuadTo(922.2f, 254.0f, 907.6f, 252.6f),
                        PathNode.QuadTo(893.0f, 251.1f, 857.7f, 251.1f),
                        PathNode.HorizontalTo(333.1f),
                        PathNode.QuadTo(298.7f, 251.1f, 283.6f, 252.6f),
                        PathNode.QuadTo(268.6f, 254.0f, 258.2f, 259.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _weeksNormal!!
    }

private var _weeksNormal: ImageVector? = null

val MiuixIcons.Regular.Weeks: ImageVector
    get() {
        if (_weeksRegular != null) return _weeksRegular!!
        _weeksRegular = ImageVector.Builder(
            name = "Weeks.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1197.6f,
            viewportHeight = 1197.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1197.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(851.5f, 630.0f),
                        PathNode.VerticalTo(655.1f),
                        PathNode.QuadTo(851.5f, 673.9f, 844.0f, 682.1f),
                        PathNode.QuadTo(836.6f, 690.2f, 820.1f, 690.2f),
                        PathNode.HorizontalTo(381.0f),
                        PathNode.QuadTo(364.5f, 690.2f, 357.0f, 682.1f),
                        PathNode.QuadTo(349.6f, 673.9f, 349.6f, 655.1f),
                        PathNode.VerticalTo(630.0f),
                        PathNode.QuadTo(349.6f, 611.2f, 357.0f, 603.5f),
                        PathNode.QuadTo(364.5f, 595.8f, 381.0f, 595.8f),
                        PathNode.HorizontalTo(820.1f),
                        PathNode.QuadTo(836.8f, 595.8f, 844.2f, 603.5f),
                        PathNode.QuadTo(851.5f, 611.2f, 851.5f, 630.0f),
                        PathNode.Close,
                        PathNode.MoveTo(978.3f, 182.9f),
                        PathNode.QuadTo(1025.4f, 207.8f, 1046.5f, 251.4f),
                        PathNode.QuadTo(1058.8f, 275.1f, 1061.6f, 307.9f),
                        PathNode.QuadTo(1064.4f, 340.8f, 1064.4f, 415.9f),
                        PathNode.VerticalTo(781.7f),
                        PathNode.QuadTo(1064.4f, 856.9f, 1061.6f, 889.3f),
                        PathNode.QuadTo(1058.8f, 921.8f, 1046.5f, 945.5f),
                        PathNode.QuadTo(1035.9f, 967.3f, 1018.0f, 985.3f),
                        PathNode.QuadTo(1000.1f, 1003.3f, 978.3f, 1014.7f),
                        PathNode.QuadTo(954.6f, 1026.1f, 922.1f, 1028.8f),
                        PathNode.QuadTo(889.6f, 1031.6f, 814.5f, 1031.6f),
                        PathNode.HorizontalTo(383.1f),
                        PathNode.QuadTo(308.0f, 1031.6f, 275.5f, 1028.8f),
                        PathNode.QuadTo(243.0f, 1026.1f, 219.3f, 1014.7f),
                        PathNode.QuadTo(197.5f, 1003.3f, 179.6f, 985.3f),
                        PathNode.QuadTo(161.8f, 967.3f, 151.1f, 945.5f),
                        PathNode.QuadTo(138.8f, 921.8f, 136.0f, 889.3f),
                        PathNode.QuadTo(133.2f, 856.9f, 133.2f, 781.7f),
                        PathNode.VerticalTo(415.9f),
                        PathNode.QuadTo(133.2f, 340.8f, 136.0f, 307.9f),
                        PathNode.QuadTo(138.8f, 275.1f, 151.1f, 251.4f),
                        PathNode.QuadTo(172.2f, 207.8f, 219.3f, 182.9f),
                        PathNode.QuadTo(243.0f, 171.5f, 275.5f, 168.8f),
                        PathNode.QuadTo(308.0f, 166.0f, 383.1f, 166.0f),
                        PathNode.HorizontalTo(814.5f),
                        PathNode.QuadTo(889.6f, 166.0f, 922.1f, 168.8f),
                        PathNode.QuadTo(954.6f, 171.5f, 978.3f, 182.9f),
                        PathNode.Close,
                        PathNode.MoveTo(265.6f, 268.3f),
                        PathNode.QuadTo(246.4f, 277.8f, 236.5f, 297.5f),
                        PathNode.QuadTo(231.4f, 307.2f, 230.0f, 321.7f),
                        PathNode.QuadTo(228.6f, 336.2f, 228.6f, 369.8f),
                        PathNode.VerticalTo(705.8f),
                        PathNode.QuadTo(228.6f, 740.1f, 230.0f, 754.3f),
                        PathNode.QuadTo(231.4f, 768.4f, 236.5f, 778.1f),
                        PathNode.QuadTo(246.2f, 797.3f, 265.6f, 807.1f),
                        PathNode.QuadTo(275.1f, 812.4f, 289.3f, 813.6f),
                        PathNode.QuadTo(303.6f, 814.9f, 337.9f, 814.9f),
                        PathNode.HorizontalTo(859.7f),
                        PathNode.QuadTo(894.7f, 814.9f, 908.6f, 813.6f),
                        PathNode.QuadTo(922.5f, 812.4f, 932.0f, 807.1f),
                        PathNode.QuadTo(951.4f, 797.3f, 961.2f, 778.1f),
                        PathNode.QuadTo(966.2f, 768.4f, 967.6f, 754.2f),
                        PathNode.QuadTo(969.0f, 739.9f, 969.0f, 705.8f),
                        PathNode.VerticalTo(369.5f),
                        PathNode.QuadTo(969.0f, 335.9f, 967.6f, 321.6f),
                        PathNode.QuadTo(966.2f, 307.2f, 961.2f, 297.5f),
                        PathNode.QuadTo(951.2f, 277.8f, 932.0f, 268.3f),
                        PathNode.QuadTo(922.5f, 263.3f, 907.5f, 262.0f),
                        PathNode.QuadTo(892.5f, 260.7f, 859.7f, 260.7f),
                        PathNode.HorizontalTo(337.9f),
                        PathNode.QuadTo(305.8f, 260.7f, 290.4f, 262.0f),
                        PathNode.QuadTo(275.1f, 263.3f, 265.6f, 268.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _weeksRegular!!
    }

private var _weeksRegular: ImageVector? = null

val MiuixIcons.Medium.Weeks: ImageVector
    get() {
        if (_weeksMedium != null) return _weeksMedium!!
        _weeksMedium = ImageVector.Builder(
            name = "Weeks.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1213.1f,
            viewportHeight = 1213.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1213.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(853.0f, 639.2f),
                        PathNode.VerticalTo(664.2f),
                        PathNode.QuadTo(853.0f, 686.0f, 843.2f, 696.0f),
                        PathNode.QuadTo(833.4f, 705.9f, 815.1f, 705.9f),
                        PathNode.HorizontalTo(400.9f),
                        PathNode.QuadTo(382.6f, 705.9f, 372.8f, 696.0f),
                        PathNode.QuadTo(362.9f, 686.0f, 362.9f, 664.2f),
                        PathNode.VerticalTo(639.2f),
                        PathNode.QuadTo(362.9f, 617.4f, 372.8f, 608.0f),
                        PathNode.QuadTo(382.6f, 598.5f, 400.9f, 598.5f),
                        PathNode.HorizontalTo(815.1f),
                        PathNode.QuadTo(834.2f, 598.5f, 843.6f, 608.0f),
                        PathNode.QuadTo(853.0f, 617.4f, 853.0f, 639.2f),
                        PathNode.Close,
                        PathNode.MoveTo(988.0f, 183.7f),
                        PathNode.QuadTo(1036.9f, 210.3f, 1059.7f, 256.3f),
                        PathNode.QuadTo(1072.6f, 281.1f, 1075.4f, 314.8f),
                        PathNode.QuadTo(1078.2f, 348.5f, 1078.2f, 423.6f),
                        PathNode.VerticalTo(789.5f),
                        PathNode.QuadTo(1078.2f, 864.6f, 1075.4f, 898.2f),
                        PathNode.QuadTo(1072.6f, 931.9f, 1059.7f, 956.7f),
                        PathNode.QuadTo(1047.9f, 979.7f, 1029.4f, 998.6f),
                        PathNode.QuadTo(1011.0f, 1017.4f, 988.0f, 1029.4f),
                        PathNode.QuadTo(963.2f, 1041.4f, 929.5f, 1044.1f),
                        PathNode.QuadTo(895.9f, 1046.9f, 820.8f, 1046.9f),
                        PathNode.HorizontalTo(392.3f),
                        PathNode.QuadTo(317.2f, 1046.9f, 283.6f, 1044.1f),
                        PathNode.QuadTo(249.9f, 1041.4f, 225.1f, 1029.4f),
                        PathNode.QuadTo(202.1f, 1017.4f, 183.7f, 998.6f),
                        PathNode.QuadTo(165.2f, 979.7f, 153.4f, 956.7f),
                        PathNode.QuadTo(140.5f, 931.9f, 137.7f, 898.2f),
                        PathNode.QuadTo(134.9f, 864.6f, 134.9f, 789.5f),
                        PathNode.VerticalTo(423.6f),
                        PathNode.QuadTo(134.9f, 348.5f, 137.7f, 314.8f),
                        PathNode.QuadTo(140.5f, 281.1f, 153.4f, 256.3f),
                        PathNode.QuadTo(176.2f, 210.3f, 225.1f, 183.7f),
                        PathNode.QuadTo(249.9f, 171.7f, 283.6f, 169.0f),
                        PathNode.QuadTo(317.2f, 166.2f, 392.3f, 166.2f),
                        PathNode.HorizontalTo(820.8f),
                        PathNode.QuadTo(895.9f, 166.2f, 929.5f, 169.0f),
                        PathNode.QuadTo(963.2f, 171.7f, 988.0f, 183.7f),
                        PathNode.Close,
                        PathNode.MoveTo(280.3f, 285.1f),
                        PathNode.QuadTo(264.1f, 292.7f, 254.7f, 310.6f),
                        PathNode.QuadTo(250.8f, 319.1f, 249.4f, 332.7f),
                        PathNode.QuadTo(248.0f, 346.3f, 248.0f, 378.1f),
                        PathNode.VerticalTo(714.4f),
                        PathNode.QuadTo(248.0f, 746.3f, 249.4f, 759.8f),
                        PathNode.QuadTo(250.8f, 773.3f, 254.7f, 781.8f),
                        PathNode.QuadTo(263.2f, 798.0f, 280.3f, 806.6f),
                        PathNode.QuadTo(288.0f, 811.3f, 300.7f, 812.2f),
                        PathNode.QuadTo(313.4f, 813.2f, 347.7f, 813.2f),
                        PathNode.HorizontalTo(865.4f),
                        PathNode.QuadTo(899.8f, 813.2f, 912.4f, 812.2f),
                        PathNode.QuadTo(925.2f, 811.3f, 932.8f, 806.6f),
                        PathNode.QuadTo(949.9f, 798.0f, 958.4f, 781.8f),
                        PathNode.QuadTo(962.3f, 773.3f, 963.7f, 759.4f),
                        PathNode.QuadTo(965.1f, 745.4f, 965.1f, 714.4f),
                        PathNode.VerticalTo(377.2f),
                        PathNode.QuadTo(965.1f, 345.4f, 963.7f, 332.3f),
                        PathNode.QuadTo(962.3f, 319.1f, 958.4f, 310.6f),
                        PathNode.QuadTo(949.0f, 292.7f, 932.8f, 285.1f),
                        PathNode.QuadTo(925.2f, 281.2f, 908.7f, 280.2f),
                        PathNode.QuadTo(892.2f, 279.2f, 865.4f, 279.2f),
                        PathNode.HorizontalTo(347.7f),
                        PathNode.QuadTo(321.0f, 279.2f, 304.5f, 280.2f),
                        PathNode.QuadTo(288.0f, 281.2f, 280.3f, 285.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _weeksMedium!!
    }

private var _weeksMedium: ImageVector? = null

val MiuixIcons.Demibold.Weeks: ImageVector
    get() {
        if (_weeksDemibold != null) return _weeksDemibold!!
        _weeksDemibold = ImageVector.Builder(
            name = "Weeks.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1224.0f,
            viewportHeight = 1224.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1224.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(854.1f, 645.7f),
                        PathNode.VerticalTo(670.7f),
                        PathNode.QuadTo(854.1f, 694.5f, 842.7f, 705.7f),
                        PathNode.QuadTo(831.2f, 716.9f, 811.7f, 716.9f),
                        PathNode.HorizontalTo(414.8f),
                        PathNode.QuadTo(395.2f, 716.9f, 383.8f, 705.7f),
                        PathNode.QuadTo(372.2f, 694.5f, 372.2f, 670.7f),
                        PathNode.VerticalTo(645.7f),
                        PathNode.QuadTo(372.2f, 621.8f, 383.8f, 611.2f),
                        PathNode.QuadTo(395.2f, 600.4f, 414.8f, 600.4f),
                        PathNode.HorizontalTo(811.7f),
                        PathNode.QuadTo(832.4f, 600.4f, 843.3f, 611.2f),
                        PathNode.QuadTo(854.1f, 621.8f, 854.1f, 645.7f),
                        PathNode.Close,
                        PathNode.MoveTo(994.8f, 184.3f),
                        PathNode.QuadTo(1044.9f, 212.1f, 1069.0f, 259.7f),
                        PathNode.QuadTo(1082.3f, 285.3f, 1085.1f, 319.6f),
                        PathNode.QuadTo(1087.9f, 353.9f, 1087.9f, 428.9f),
                        PathNode.VerticalTo(795.0f),
                        PathNode.QuadTo(1087.9f, 870.0f, 1085.1f, 904.5f),
                        PathNode.QuadTo(1082.3f, 939.0f, 1069.0f, 964.6f),
                        PathNode.QuadTo(1056.3f, 988.4f, 1037.5f, 1007.9f),
                        PathNode.QuadTo(1018.6f, 1027.4f, 994.8f, 1039.7f),
                        PathNode.QuadTo(969.2f, 1052.1f, 934.7f, 1054.9f),
                        PathNode.QuadTo(900.3f, 1057.7f, 825.3f, 1057.7f),
                        PathNode.HorizontalTo(398.7f),
                        PathNode.QuadTo(323.7f, 1057.7f, 289.2f, 1054.9f),
                        PathNode.QuadTo(254.8f, 1052.1f, 229.2f, 1039.7f),
                        PathNode.QuadTo(205.4f, 1027.4f, 186.5f, 1007.9f),
                        PathNode.QuadTo(167.6f, 988.4f, 155.0f, 964.6f),
                        PathNode.QuadTo(141.7f, 939.0f, 138.9f, 904.5f),
                        PathNode.QuadTo(136.1f, 870.0f, 136.1f, 795.0f),
                        PathNode.VerticalTo(428.9f),
                        PathNode.QuadTo(136.1f, 353.9f, 138.9f, 319.6f),
                        PathNode.QuadTo(141.7f, 285.3f, 155.0f, 259.7f),
                        PathNode.QuadTo(179.1f, 212.1f, 229.2f, 184.3f),
                        PathNode.QuadTo(254.8f, 171.9f, 289.2f, 169.1f),
                        PathNode.QuadTo(323.7f, 166.3f, 398.7f, 166.3f),
                        PathNode.HorizontalTo(825.3f),
                        PathNode.QuadTo(900.3f, 166.3f, 934.7f, 169.1f),
                        PathNode.QuadTo(969.2f, 171.9f, 994.8f, 184.3f),
                        PathNode.Close,
                        PathNode.MoveTo(290.5f, 296.7f),
                        PathNode.QuadTo(276.4f, 303.2f, 267.4f, 319.8f),
                        PathNode.QuadTo(264.4f, 327.5f, 263.0f, 340.4f),
                        PathNode.QuadTo(261.6f, 353.4f, 261.6f, 383.8f),
                        PathNode.VerticalTo(720.4f),
                        PathNode.QuadTo(261.6f, 750.5f, 263.0f, 763.7f),
                        PathNode.QuadTo(264.4f, 776.8f, 267.4f, 784.5f),
                        PathNode.QuadTo(275.1f, 798.6f, 290.5f, 806.3f),
                        PathNode.QuadTo(296.9f, 810.6f, 308.6f, 811.4f),
                        PathNode.QuadTo(320.3f, 812.1f, 354.6f, 812.1f),
                        PathNode.HorizontalTo(869.4f),
                        PathNode.QuadTo(903.3f, 812.1f, 915.2f, 811.4f),
                        PathNode.QuadTo(927.0f, 810.6f, 933.5f, 806.3f),
                        PathNode.QuadTo(948.9f, 798.6f, 956.5f, 784.5f),
                        PathNode.QuadTo(959.6f, 776.8f, 961.0f, 763.0f),
                        PathNode.QuadTo(962.4f, 749.3f, 962.4f, 720.4f),
                        PathNode.VerticalTo(382.6f),
                        PathNode.QuadTo(962.4f, 352.1f, 961.0f, 339.8f),
                        PathNode.QuadTo(959.6f, 327.5f, 956.5f, 319.8f),
                        PathNode.QuadTo(947.6f, 303.2f, 933.5f, 296.7f),
                        PathNode.QuadTo(927.0f, 293.7f, 909.5f, 292.9f),
                        PathNode.QuadTo(892.1f, 292.1f, 869.4f, 292.1f),
                        PathNode.HorizontalTo(354.6f),
                        PathNode.QuadTo(331.6f, 292.1f, 314.3f, 292.9f),
                        PathNode.QuadTo(296.9f, 293.7f, 290.5f, 296.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _weeksDemibold!!
    }

private var _weeksDemibold: ImageVector? = null
