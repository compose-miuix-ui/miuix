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

val MiuixIcons.Show: ImageVector
    get() = MiuixIcons.Regular.Show

val MiuixIcons.Light.Show: ImageVector
    get() {
        if (_showLight != null) return _showLight!!
        _showLight = ImageVector.Builder(
            name = "Show.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1346.4f,
            viewportHeight = 1346.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1346.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1224.2f, 627.7f),
                        PathNode.QuadTo(1234.2f, 649.2f, 1234.2f, 673.0f),
                        PathNode.QuadTo(1234.2f, 696.7f, 1224.2f, 718.7f),
                        PathNode.QuadTo(1178.2f, 821.7f, 1091.7f, 902.7f),
                        PathNode.QuadTo(1005.2f, 983.7f, 896.2f, 1029.2f),
                        PathNode.QuadTo(787.2f, 1074.7f, 673.7f, 1074.7f),
                        PathNode.QuadTo(559.2f, 1074.7f, 450.4f, 1029.2f),
                        PathNode.QuadTo(341.5f, 983.8f, 255.4f, 902.7f),
                        PathNode.QuadTo(169.2f, 821.7f, 122.2f, 718.7f),
                        PathNode.QuadTo(112.2f, 697.2f, 112.2f, 673.4f),
                        PathNode.QuadTo(112.2f, 649.7f, 122.2f, 627.7f),
                        PathNode.QuadTo(169.2f, 524.7f, 255.7f, 443.7f),
                        PathNode.QuadTo(342.1f, 362.6f, 451.2f, 317.2f),
                        PathNode.QuadTo(560.2f, 271.7f, 673.2f, 271.7f),
                        PathNode.QuadTo(786.2f, 271.7f, 895.0f, 317.2f),
                        PathNode.QuadTo(1003.9f, 362.6f, 1090.5f, 443.7f),
                        PathNode.QuadTo(1177.2f, 524.7f, 1224.2f, 627.7f),
                        PathNode.Close,
                        PathNode.MoveTo(175.2f, 652.7f),
                        PathNode.QuadTo(166.2f, 673.7f, 175.2f, 693.7f),
                        PathNode.QuadTo(218.2f, 786.7f, 296.1f, 859.8f),
                        PathNode.QuadTo(374.0f, 932.8f, 472.5f, 973.8f),
                        PathNode.QuadTo(571.0f, 1014.7f, 673.1f, 1014.7f),
                        PathNode.QuadTo(775.2f, 1014.7f, 874.2f, 973.7f),
                        PathNode.QuadTo(973.2f, 932.7f, 1051.2f, 859.7f),
                        PathNode.QuadTo(1129.2f, 786.7f, 1171.2f, 693.7f),
                        PathNode.QuadTo(1179.2f, 673.7f, 1171.2f, 652.7f),
                        PathNode.QuadTo(1128.7f, 559.6f, 1050.0f, 486.1f),
                        PathNode.QuadTo(971.2f, 412.7f, 872.5f, 371.7f),
                        PathNode.QuadTo(773.8f, 330.7f, 673.5f, 330.7f),
                        PathNode.QuadTo(572.2f, 330.7f, 473.2f, 371.7f),
                        PathNode.QuadTo(374.2f, 412.7f, 295.8f, 486.1f),
                        PathNode.QuadTo(217.5f, 559.6f, 175.2f, 652.7f),
                        PathNode.Close,
                        PathNode.MoveTo(887.2f, 672.2f),
                        PathNode.QuadTo(887.2f, 731.7f, 858.5f, 780.9f),
                        PathNode.QuadTo(829.9f, 830.0f, 781.0f, 858.9f),
                        PathNode.QuadTo(732.2f, 887.7f, 673.2f, 887.7f),
                        PathNode.QuadTo(614.2f, 887.7f, 565.4f, 858.8f),
                        PathNode.QuadTo(516.5f, 829.9f, 487.9f, 780.7f),
                        PathNode.QuadTo(459.2f, 731.4f, 459.2f, 672.7f),
                        PathNode.QuadTo(459.2f, 613.7f, 487.9f, 564.9f),
                        PathNode.QuadTo(516.5f, 516.0f, 565.4f, 487.4f),
                        PathNode.QuadTo(614.2f, 458.7f, 673.2f, 458.7f),
                        PathNode.QuadTo(732.2f, 458.7f, 781.0f, 487.4f),
                        PathNode.QuadTo(829.9f, 516.0f, 858.5f, 564.9f),
                        PathNode.QuadTo(887.2f, 613.7f, 887.2f, 672.2f),
                        PathNode.Close,
                        PathNode.MoveTo(517.2f, 672.7f),
                        PathNode.QuadTo(517.2f, 714.7f, 538.2f, 750.7f),
                        PathNode.QuadTo(559.2f, 786.6f, 595.2f, 807.7f),
                        PathNode.QuadTo(631.1f, 828.7f, 673.2f, 828.7f),
                        PathNode.QuadTo(715.2f, 828.7f, 751.2f, 807.7f),
                        PathNode.QuadTo(787.1f, 786.7f, 808.2f, 750.7f),
                        PathNode.QuadTo(829.2f, 714.8f, 829.2f, 672.7f),
                        PathNode.QuadTo(829.2f, 630.7f, 808.2f, 594.7f),
                        PathNode.QuadTo(787.2f, 558.8f, 751.2f, 537.7f),
                        PathNode.QuadTo(715.3f, 516.7f, 673.2f, 516.7f),
                        PathNode.QuadTo(631.2f, 516.7f, 595.2f, 537.7f),
                        PathNode.QuadTo(559.3f, 558.7f, 538.2f, 594.7f),
                        PathNode.QuadTo(517.2f, 630.6f, 517.2f, 672.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showLight!!
    }

private var _showLight: ImageVector? = null

val MiuixIcons.Normal.Show: ImageVector
    get() {
        if (_showNormal != null) return _showNormal!!
        _showNormal = ImageVector.Builder(
            name = "Show.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1361.2f,
            viewportHeight = 1361.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1361.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1237.1f, 631.7f),
                        PathNode.QuadTo(1247.8f, 654.9f, 1247.8f, 680.5f),
                        PathNode.QuadTo(1247.8f, 706.2f, 1237.1f, 729.6f),
                        PathNode.QuadTo(1190.4f, 833.9f, 1103.2f, 915.6f),
                        PathNode.QuadTo(1016.1f, 997.3f, 905.7f, 1043.2f),
                        PathNode.QuadTo(795.3f, 1089.0f, 680.8f, 1089.0f),
                        PathNode.QuadTo(565.2f, 1089.0f, 455.3f, 1043.2f),
                        PathNode.QuadTo(345.3f, 997.3f, 258.2f, 915.6f),
                        PathNode.QuadTo(171.1f, 833.9f, 124.1f, 729.6f),
                        PathNode.QuadTo(113.4f, 706.3f, 113.4f, 680.7f),
                        PathNode.QuadTo(113.4f, 655.1f, 124.1f, 631.7f),
                        PathNode.QuadTo(171.1f, 527.3f, 258.6f, 445.6f),
                        PathNode.QuadTo(346.2f, 363.9f, 456.5f, 318.1f),
                        PathNode.QuadTo(566.9f, 272.2f, 680.6f, 272.2f),
                        PathNode.QuadTo(794.3f, 272.2f, 904.3f, 318.1f),
                        PathNode.QuadTo(1014.3f, 363.9f, 1101.9f, 445.6f),
                        PathNode.QuadTo(1189.4f, 527.3f, 1237.1f, 631.7f),
                        PathNode.Close,
                        PathNode.MoveTo(193.6f, 664.2f),
                        PathNode.QuadTo(186.7f, 680.4f, 193.6f, 697.7f),
                        PathNode.QuadTo(235.2f, 788.6f, 311.5f, 859.9f),
                        PathNode.QuadTo(387.7f, 931.2f, 484.2f, 971.2f),
                        PathNode.QuadTo(580.6f, 1011.1f, 680.6f, 1011.1f),
                        PathNode.QuadTo(780.6f, 1011.1f, 877.2f, 971.2f),
                        PathNode.QuadTo(973.8f, 931.2f, 1049.7f, 859.6f),
                        PathNode.QuadTo(1125.6f, 787.9f, 1166.9f, 697.7f),
                        PathNode.QuadTo(1173.6f, 680.4f, 1166.9f, 664.2f),
                        PathNode.QuadTo(1125.5f, 573.3f, 1048.6f, 501.5f),
                        PathNode.QuadTo(971.8f, 429.8f, 875.2f, 389.4f),
                        PathNode.QuadTo(778.7f, 349.1f, 680.7f, 349.1f),
                        PathNode.QuadTo(581.7f, 349.1f, 485.1f, 389.4f),
                        PathNode.QuadTo(388.5f, 429.8f, 311.8f, 501.5f),
                        PathNode.QuadTo(235.0f, 573.3f, 193.6f, 664.2f),
                        PathNode.Close,
                        PathNode.MoveTo(902.9f, 680.0f),
                        PathNode.QuadTo(902.9f, 741.2f, 873.0f, 792.3f),
                        PathNode.QuadTo(843.0f, 843.4f, 792.0f, 873.4f),
                        PathNode.QuadTo(741.0f, 903.4f, 680.6f, 903.4f),
                        PathNode.QuadTo(620.2f, 903.4f, 569.2f, 873.4f),
                        PathNode.QuadTo(518.2f, 843.4f, 488.3f, 792.2f),
                        PathNode.QuadTo(458.4f, 741.1f, 458.4f, 680.1f),
                        PathNode.QuadTo(458.4f, 619.8f, 488.3f, 568.7f),
                        PathNode.QuadTo(518.2f, 517.7f, 569.2f, 487.8f),
                        PathNode.QuadTo(620.2f, 457.9f, 680.6f, 457.9f),
                        PathNode.QuadTo(741.0f, 457.9f, 792.0f, 487.8f),
                        PathNode.QuadTo(843.0f, 517.7f, 873.0f, 568.7f),
                        PathNode.QuadTo(902.9f, 619.8f, 902.9f, 680.0f),
                        PathNode.Close,
                        PathNode.MoveTo(534.9f, 680.1f),
                        PathNode.QuadTo(534.9f, 719.4f, 554.6f, 753.0f),
                        PathNode.QuadTo(574.2f, 786.5f, 607.8f, 806.2f),
                        PathNode.QuadTo(641.4f, 825.8f, 680.6f, 825.8f),
                        PathNode.QuadTo(719.9f, 825.8f, 753.5f, 806.2f),
                        PathNode.QuadTo(787.0f, 786.6f, 806.7f, 753.0f),
                        PathNode.QuadTo(826.3f, 719.4f, 826.3f, 680.1f),
                        PathNode.QuadTo(826.3f, 640.9f, 806.7f, 607.3f),
                        PathNode.QuadTo(787.1f, 573.7f, 753.5f, 554.1f),
                        PathNode.QuadTo(719.9f, 534.4f, 680.6f, 534.4f),
                        PathNode.QuadTo(641.4f, 534.4f, 607.8f, 554.1f),
                        PathNode.QuadTo(574.2f, 573.7f, 554.6f, 607.3f),
                        PathNode.QuadTo(534.9f, 640.9f, 534.9f, 680.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showNormal!!
    }

private var _showNormal: ImageVector? = null

val MiuixIcons.Regular.Show: ImageVector
    get() {
        if (_showRegular != null) return _showRegular!!
        _showRegular = ImageVector.Builder(
            name = "Show.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1368.0f,
            viewportHeight = 1368.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1368.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1243.0f, 633.5f),
                        PathNode.QuadTo(1254.0f, 657.5f, 1254.0f, 684.0f),
                        PathNode.QuadTo(1254.0f, 710.5f, 1243.0f, 734.5f),
                        PathNode.QuadTo(1196.0f, 839.5f, 1108.5f, 921.5f),
                        PathNode.QuadTo(1021.0f, 1003.5f, 910.0f, 1049.5f),
                        PathNode.QuadTo(799.0f, 1095.5f, 684.0f, 1095.5f),
                        PathNode.QuadTo(568.0f, 1095.5f, 457.5f, 1049.5f),
                        PathNode.QuadTo(347.0f, 1003.5f, 259.5f, 921.5f),
                        PathNode.QuadTo(172.0f, 839.5f, 125.0f, 734.5f),
                        PathNode.QuadTo(114.0f, 710.5f, 114.0f, 684.0f),
                        PathNode.QuadTo(114.0f, 657.5f, 125.0f, 633.5f),
                        PathNode.QuadTo(172.0f, 528.5f, 260.0f, 446.5f),
                        PathNode.QuadTo(348.0f, 364.5f, 459.0f, 318.5f),
                        PathNode.QuadTo(570.0f, 272.5f, 684.0f, 272.5f),
                        PathNode.QuadTo(798.0f, 272.5f, 908.5f, 318.5f),
                        PathNode.QuadTo(1019.0f, 364.5f, 1107.0f, 446.5f),
                        PathNode.QuadTo(1195.0f, 528.5f, 1243.0f, 633.5f),
                        PathNode.Close,
                        PathNode.MoveTo(202.0f, 669.5f),
                        PathNode.QuadTo(196.0f, 683.5f, 202.0f, 699.5f),
                        PathNode.QuadTo(243.0f, 789.5f, 318.5f, 860.0f),
                        PathNode.QuadTo(394.0f, 930.5f, 489.5f, 970.0f),
                        PathNode.QuadTo(585.0f, 1009.5f, 684.0f, 1009.5f),
                        PathNode.QuadTo(783.0f, 1009.5f, 878.5f, 970.0f),
                        PathNode.QuadTo(974.0f, 930.5f, 1049.0f, 859.5f),
                        PathNode.QuadTo(1124.0f, 788.5f, 1165.0f, 699.5f),
                        PathNode.QuadTo(1171.0f, 683.5f, 1165.0f, 669.5f),
                        PathNode.QuadTo(1124.0f, 579.5f, 1048.0f, 508.5f),
                        PathNode.QuadTo(972.0f, 437.5f, 876.5f, 397.5f),
                        PathNode.QuadTo(781.0f, 357.5f, 684.0f, 357.5f),
                        PathNode.QuadTo(586.0f, 357.5f, 490.5f, 397.5f),
                        PathNode.QuadTo(395.0f, 437.5f, 319.0f, 508.5f),
                        PathNode.QuadTo(243.0f, 579.5f, 202.0f, 669.5f),
                        PathNode.Close,
                        PathNode.MoveTo(910.0f, 683.5f),
                        PathNode.QuadTo(910.0f, 745.5f, 879.5f, 797.5f),
                        PathNode.QuadTo(849.0f, 849.5f, 797.0f, 880.0f),
                        PathNode.QuadTo(745.0f, 910.5f, 684.0f, 910.5f),
                        PathNode.QuadTo(623.0f, 910.5f, 571.0f, 880.0f),
                        PathNode.QuadTo(519.0f, 849.5f, 488.5f, 797.5f),
                        PathNode.QuadTo(458.0f, 745.5f, 458.0f, 683.5f),
                        PathNode.QuadTo(458.0f, 622.5f, 488.5f, 570.5f),
                        PathNode.QuadTo(519.0f, 518.5f, 571.0f, 488.0f),
                        PathNode.QuadTo(623.0f, 457.5f, 684.0f, 457.5f),
                        PathNode.QuadTo(745.0f, 457.5f, 797.0f, 488.0f),
                        PathNode.QuadTo(849.0f, 518.5f, 879.5f, 570.5f),
                        PathNode.QuadTo(910.0f, 622.5f, 910.0f, 683.5f),
                        PathNode.Close,
                        PathNode.MoveTo(543.0f, 683.5f),
                        PathNode.QuadTo(543.0f, 721.5f, 562.0f, 754.0f),
                        PathNode.QuadTo(581.0f, 786.5f, 613.5f, 805.5f),
                        PathNode.QuadTo(646.0f, 824.5f, 684.0f, 824.5f),
                        PathNode.QuadTo(722.0f, 824.5f, 754.5f, 805.5f),
                        PathNode.QuadTo(787.0f, 786.5f, 806.0f, 754.0f),
                        PathNode.QuadTo(825.0f, 721.5f, 825.0f, 683.5f),
                        PathNode.QuadTo(825.0f, 645.5f, 806.0f, 613.0f),
                        PathNode.QuadTo(787.0f, 580.5f, 754.5f, 561.5f),
                        PathNode.QuadTo(722.0f, 542.5f, 684.0f, 542.5f),
                        PathNode.QuadTo(646.0f, 542.5f, 613.5f, 561.5f),
                        PathNode.QuadTo(581.0f, 580.5f, 562.0f, 613.0f),
                        PathNode.QuadTo(543.0f, 645.5f, 543.0f, 683.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showRegular!!
    }

private var _showRegular: ImageVector? = null

val MiuixIcons.Medium.Show: ImageVector
    get() {
        if (_showMedium != null) return _showMedium!!
        _showMedium = ImageVector.Builder(
            name = "Show.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1382.8f,
            viewportHeight = 1382.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1382.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1256.0f, 637.4f),
                        PathNode.QuadTo(1267.6f, 663.0f, 1267.6f, 691.4f),
                        PathNode.QuadTo(1267.6f, 719.7f, 1256.0f, 745.4f),
                        PathNode.QuadTo(1208.5f, 851.6f, 1120.1f, 934.5f),
                        PathNode.QuadTo(1031.6f, 1017.4f, 919.6f, 1063.7f),
                        PathNode.QuadTo(807.6f, 1110.0f, 691.6f, 1110.0f),
                        PathNode.QuadTo(574.5f, 1110.0f, 462.9f, 1063.7f),
                        PathNode.QuadTo(351.2f, 1017.4f, 263.0f, 934.5f),
                        PathNode.QuadTo(174.8f, 851.6f, 127.4f, 745.4f),
                        PathNode.QuadTo(115.2f, 719.8f, 115.2f, 691.5f),
                        PathNode.QuadTo(115.2f, 663.1f, 127.4f, 637.4f),
                        PathNode.QuadTo(174.8f, 531.2f, 263.5f, 448.3f),
                        PathNode.QuadTo(352.2f, 365.4f, 464.3f, 319.1f),
                        PathNode.QuadTo(576.5f, 272.9f, 691.7f, 272.9f),
                        PathNode.QuadTo(806.9f, 272.9f, 918.5f, 319.3f),
                        PathNode.QuadTo(1030.2f, 365.7f, 1119.1f, 448.5f),
                        PathNode.QuadTo(1208.0f, 531.2f, 1256.0f, 637.4f),
                        PathNode.Close,
                        PathNode.MoveTo(220.9f, 680.4f),
                        PathNode.QuadTo(216.6f, 690.9f, 220.9f, 703.4f),
                        PathNode.QuadTo(261.3f, 791.0f, 335.0f, 859.8f),
                        PathNode.QuadTo(408.7f, 928.6f, 501.9f, 967.2f),
                        PathNode.QuadTo(595.1f, 1005.7f, 691.7f, 1005.7f),
                        PathNode.QuadTo(788.4f, 1005.7f, 881.5f, 967.1f),
                        PathNode.QuadTo(974.6f, 928.5f, 1048.2f, 859.6f),
                        PathNode.QuadTo(1121.7f, 790.6f, 1161.5f, 703.4f),
                        PathNode.QuadTo(1165.8f, 691.5f, 1161.5f, 680.4f),
                        PathNode.QuadTo(1121.1f, 592.8f, 1047.2f, 523.6f),
                        PathNode.QuadTo(973.2f, 454.3f, 879.9f, 415.2f),
                        PathNode.QuadTo(786.6f, 376.1f, 691.8f, 376.1f),
                        PathNode.QuadTo(596.1f, 376.1f, 502.9f, 415.1f),
                        PathNode.QuadTo(409.6f, 454.2f, 335.5f, 523.5f),
                        PathNode.QuadTo(261.3f, 592.8f, 220.9f, 680.4f),
                        PathNode.Close,
                        PathNode.MoveTo(923.6f, 690.7f),
                        PathNode.QuadTo(923.6f, 754.3f, 892.5f, 807.7f),
                        PathNode.QuadTo(861.4f, 861.0f, 807.9f, 892.4f),
                        PathNode.QuadTo(754.4f, 923.8f, 691.7f, 923.8f),
                        PathNode.QuadTo(628.9f, 923.8f, 575.5f, 892.4f),
                        PathNode.QuadTo(522.0f, 861.0f, 490.9f, 807.7f),
                        PathNode.QuadTo(459.8f, 754.3f, 459.8f, 690.7f),
                        PathNode.QuadTo(459.8f, 628.1f, 490.9f, 574.7f),
                        PathNode.QuadTo(522.0f, 521.2f, 575.5f, 490.1f),
                        PathNode.QuadTo(629.0f, 459.0f, 691.7f, 459.0f),
                        PathNode.QuadTo(754.5f, 459.0f, 807.9f, 490.1f),
                        PathNode.QuadTo(861.4f, 521.2f, 892.5f, 574.7f),
                        PathNode.QuadTo(923.6f, 628.1f, 923.6f, 690.7f),
                        PathNode.Close,
                        PathNode.MoveTo(558.4f, 691.0f),
                        PathNode.QuadTo(558.4f, 727.1f, 576.3f, 757.8f),
                        PathNode.QuadTo(594.2f, 788.4f, 624.8f, 806.3f),
                        PathNode.QuadTo(655.5f, 824.3f, 691.6f, 824.3f),
                        PathNode.QuadTo(727.7f, 824.3f, 758.5f, 806.4f),
                        PathNode.QuadTo(789.4f, 788.6f, 807.2f, 757.7f),
                        PathNode.QuadTo(825.1f, 726.9f, 825.1f, 690.8f),
                        PathNode.QuadTo(825.1f, 654.7f, 807.1f, 624.0f),
                        PathNode.QuadTo(789.2f, 593.4f, 758.6f, 575.5f),
                        PathNode.QuadTo(727.9f, 557.6f, 691.8f, 557.6f),
                        PathNode.QuadTo(655.7f, 557.6f, 624.9f, 575.4f),
                        PathNode.QuadTo(594.0f, 593.2f, 576.2f, 624.1f),
                        PathNode.QuadTo(558.4f, 655.0f, 558.4f, 691.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showMedium!!
    }

private var _showMedium: ImageVector? = null

val MiuixIcons.Demibold.Show: ImageVector
    get() {
        if (_showDemibold != null) return _showDemibold!!
        _showDemibold = ImageVector.Builder(
            name = "Show.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1393.2f,
            viewportHeight = 1393.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1393.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1265.1f, 640.1f),
                        PathNode.QuadTo(1277.1f, 666.9f, 1277.1f, 696.5f),
                        PathNode.QuadTo(1277.1f, 726.1f, 1265.1f, 753.1f),
                        PathNode.QuadTo(1217.3f, 860.1f, 1128.2f, 943.6f),
                        PathNode.QuadTo(1039.1f, 1027.1f, 926.4f, 1073.6f),
                        PathNode.QuadTo(813.7f, 1120.1f, 696.9f, 1120.1f),
                        PathNode.QuadTo(579.1f, 1120.1f, 466.6f, 1073.6f),
                        PathNode.QuadTo(354.1f, 1027.1f, 265.4f, 943.6f),
                        PathNode.QuadTo(176.7f, 860.1f, 129.1f, 753.1f),
                        PathNode.QuadTo(116.1f, 726.3f, 116.1f, 696.7f),
                        PathNode.QuadTo(116.1f, 667.1f, 129.1f, 640.1f),
                        PathNode.QuadTo(176.7f, 533.1f, 265.9f, 449.6f),
                        PathNode.QuadTo(355.1f, 366.1f, 468.1f, 319.6f),
                        PathNode.QuadTo(581.0f, 273.1f, 697.1f, 273.1f),
                        PathNode.QuadTo(813.1f, 273.1f, 925.5f, 319.8f),
                        PathNode.QuadTo(1038.0f, 366.5f, 1127.5f, 449.8f),
                        PathNode.QuadTo(1217.1f, 533.1f, 1265.1f, 640.1f),
                        PathNode.Close,
                        PathNode.MoveTo(234.1f, 688.1f),
                        PathNode.QuadTo(231.1f, 696.1f, 234.1f, 706.1f),
                        PathNode.QuadTo(274.1f, 792.1f, 346.5f, 859.7f),
                        PathNode.QuadTo(418.9f, 927.3f, 510.6f, 965.2f),
                        PathNode.QuadTo(602.2f, 1003.1f, 697.1f, 1003.1f),
                        PathNode.QuadTo(792.1f, 1003.1f, 883.6f, 965.1f),
                        PathNode.QuadTo(975.1f, 927.1f, 1047.6f, 859.6f),
                        PathNode.QuadTo(1120.1f, 792.1f, 1159.1f, 706.1f),
                        PathNode.QuadTo(1162.1f, 697.1f, 1159.1f, 688.1f),
                        PathNode.QuadTo(1119.1f, 602.1f, 1046.6f, 534.1f),
                        PathNode.QuadTo(974.1f, 466.1f, 882.3f, 427.6f),
                        PathNode.QuadTo(790.5f, 389.1f, 697.3f, 389.1f),
                        PathNode.QuadTo(603.1f, 389.1f, 511.5f, 427.5f),
                        PathNode.QuadTo(419.9f, 465.9f, 347.0f, 534.0f),
                        PathNode.QuadTo(274.1f, 602.1f, 234.1f, 688.1f),
                        PathNode.Close,
                        PathNode.MoveTo(933.1f, 695.8f),
                        PathNode.QuadTo(933.1f, 760.5f, 901.6f, 814.8f),
                        PathNode.QuadTo(870.1f, 869.1f, 815.6f, 901.1f),
                        PathNode.QuadTo(761.0f, 933.1f, 697.1f, 933.1f),
                        PathNode.QuadTo(633.1f, 933.1f, 578.6f, 901.1f),
                        PathNode.QuadTo(524.1f, 869.1f, 492.6f, 814.8f),
                        PathNode.QuadTo(461.1f, 760.5f, 461.1f, 695.8f),
                        PathNode.QuadTo(461.1f, 632.1f, 492.6f, 577.6f),
                        PathNode.QuadTo(524.1f, 523.1f, 578.6f, 491.6f),
                        PathNode.QuadTo(633.2f, 460.1f, 697.1f, 460.1f),
                        PathNode.QuadTo(761.1f, 460.1f, 815.6f, 491.6f),
                        PathNode.QuadTo(870.1f, 523.1f, 901.6f, 577.6f),
                        PathNode.QuadTo(933.1f, 632.1f, 933.1f, 695.8f),
                        PathNode.Close,
                        PathNode.MoveTo(569.1f, 696.3f),
                        PathNode.QuadTo(569.1f, 731.1f, 586.3f, 760.4f),
                        PathNode.QuadTo(603.4f, 789.8f, 632.8f, 806.9f),
                        PathNode.QuadTo(662.1f, 824.1f, 696.9f, 824.1f),
                        PathNode.QuadTo(731.6f, 824.1f, 761.4f, 807.1f),
                        PathNode.QuadTo(791.1f, 790.1f, 808.1f, 760.4f),
                        PathNode.QuadTo(825.1f, 730.6f, 825.1f, 695.9f),
                        PathNode.QuadTo(825.1f, 661.1f, 807.9f, 631.8f),
                        PathNode.QuadTo(790.8f, 602.4f, 761.4f, 585.3f),
                        PathNode.QuadTo(732.1f, 568.1f, 697.3f, 568.1f),
                        PathNode.QuadTo(662.6f, 568.1f, 632.8f, 585.1f),
                        PathNode.QuadTo(603.1f, 602.1f, 586.1f, 631.8f),
                        PathNode.QuadTo(569.1f, 661.6f, 569.1f, 696.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showDemibold!!
    }

private var _showDemibold: ImageVector? = null
