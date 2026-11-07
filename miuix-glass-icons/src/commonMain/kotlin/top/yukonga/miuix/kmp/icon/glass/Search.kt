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

val MiuixIcons.Glass.Search: ImageVector
    get() = MiuixIcons.Glass.Regular.Search

val MiuixIcons.Glass.Light.Search: ImageVector
    get() {
        if (_searchLight != null) return _searchLight!!
        _searchLight = ImageVector.Builder(
            name = "Search.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(708.8f, 355.9f),
                        PathNode.QuadTo(722.2f, 363.9f, 728.2f, 363.7f),
                        PathNode.QuadTo(734.2f, 363.4f, 742.8f, 354.3f),
                        PathNode.LineTo(1012.5f, 84.2f),
                        PathNode.QuadTo(1019.7f, 77.2f, 1026.5f, 76.9f),
                        PathNode.QuadTo(1033.4f, 76.6f, 1040.9f, 84.2f),
                        PathNode.LineTo(1055.3f, 98.5f),
                        PathNode.QuadTo(1062.4f, 106.0f, 1062.4f, 112.0f),
                        PathNode.QuadTo(1062.4f, 117.9f, 1055.3f, 125.5f),
                        PathNode.LineTo(783.0f, 398.3f),
                        PathNode.QuadTo(775.5f, 406.3f, 775.5f, 411.6f),
                        PathNode.QuadTo(775.6f, 416.8f, 783.7f, 428.7f),
                        PathNode.QuadTo(827.9f, 488.8f, 847.8f, 548.5f),
                        PathNode.QuadTo(867.6f, 608.1f, 867.6f, 672.4f),
                        PathNode.QuadTo(867.6f, 779.1f, 814.7f, 869.7f),
                        PathNode.QuadTo(761.8f, 960.2f, 671.3f, 1013.1f),
                        PathNode.QuadTo(580.7f, 1065.9f, 474.0f, 1065.9f),
                        PathNode.QuadTo(367.3f, 1065.9f, 276.7f, 1013.1f),
                        PathNode.QuadTo(186.2f, 960.2f, 133.3f, 869.6f),
                        PathNode.QuadTo(80.5f, 779.1f, 80.5f, 672.3f),
                        PathNode.QuadTo(80.5f, 565.6f, 133.4f, 475.1f),
                        PathNode.QuadTo(186.2f, 384.5f, 276.8f, 331.7f),
                        PathNode.QuadTo(367.3f, 278.8f, 474.0f, 278.8f),
                        PathNode.QuadTo(535.3f, 278.8f, 592.9f, 297.2f),
                        PathNode.QuadTo(650.6f, 315.5f, 708.8f, 355.9f),
                        PathNode.Close,
                        PathNode.MoveTo(139.3f, 672.4f),
                        PathNode.QuadTo(139.3f, 763.3f, 184.2f, 840.4f),
                        PathNode.QuadTo(229.0f, 917.4f, 306.1f, 962.3f),
                        PathNode.QuadTo(383.3f, 1007.2f, 474.1f, 1007.2f),
                        PathNode.QuadTo(564.9f, 1007.2f, 641.6f, 962.3f),
                        PathNode.QuadTo(718.3f, 917.4f, 763.3f, 840.3f),
                        PathNode.QuadTo(808.3f, 763.2f, 808.3f, 672.3f),
                        PathNode.QuadTo(808.3f, 581.5f, 763.3f, 504.4f),
                        PathNode.QuadTo(718.4f, 427.4f, 641.7f, 382.5f),
                        PathNode.QuadTo(565.0f, 337.6f, 474.1f, 337.6f),
                        PathNode.QuadTo(383.2f, 337.6f, 306.1f, 382.5f),
                        PathNode.QuadTo(229.0f, 427.4f, 184.1f, 504.5f),
                        PathNode.QuadTo(139.3f, 581.6f, 139.3f, 672.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchLight!!
    }

private var _searchLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Search: ImageVector
    get() {
        if (_searchNormal != null) return _searchNormal!!
        _searchNormal = ImageVector.Builder(
            name = "Search.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(700.0f, 341.7f),
                        PathNode.QuadTo(717.9f, 352.4f, 725.2f, 352.0f),
                        PathNode.QuadTo(732.6f, 351.6f, 745.0f, 339.1f),
                        PathNode.LineTo(1001.8f, 82.1f),
                        PathNode.QuadTo(1011.6f, 72.4f, 1021.0f, 72.0f),
                        PathNode.QuadTo(1030.3f, 71.5f, 1040.9f, 82.1f),
                        PathNode.LineTo(1057.1f, 98.3f),
                        PathNode.QuadTo(1067.6f, 108.9f, 1067.6f, 117.3f),
                        PathNode.QuadTo(1067.6f, 125.7f, 1057.1f, 136.3f),
                        PathNode.LineTo(797.4f, 396.8f),
                        PathNode.QuadTo(786.8f, 407.6f, 787.1f, 414.4f),
                        PathNode.QuadTo(787.5f, 421.2f, 799.0f, 438.0f),
                        PathNode.QuadTo(836.2f, 490.5f, 855.1f, 549.3f),
                        PathNode.QuadTo(874.0f, 608.2f, 874.0f, 671.6f),
                        PathNode.QuadTo(874.0f, 779.8f, 820.4f, 871.7f),
                        PathNode.QuadTo(766.7f, 963.6f, 674.8f, 1017.3f),
                        PathNode.QuadTo(582.9f, 1070.9f, 474.6f, 1070.9f),
                        PathNode.QuadTo(366.3f, 1070.9f, 274.5f, 1017.3f),
                        PathNode.QuadTo(182.6f, 963.6f, 128.9f, 871.7f),
                        PathNode.QuadTo(75.3f, 779.8f, 75.3f, 671.5f),
                        PathNode.QuadTo(75.3f, 563.3f, 128.9f, 471.4f),
                        PathNode.QuadTo(182.6f, 379.5f, 274.5f, 325.8f),
                        PathNode.QuadTo(366.4f, 272.2f, 474.7f, 272.2f),
                        PathNode.QuadTo(535.0f, 272.2f, 591.9f, 289.6f),
                        PathNode.QuadTo(648.8f, 307.0f, 700.0f, 341.7f),
                        PathNode.Close,
                        PathNode.MoveTo(152.1f, 671.6f),
                        PathNode.QuadTo(152.1f, 759.1f, 195.3f, 833.4f),
                        PathNode.QuadTo(238.6f, 907.6f, 312.9f, 950.9f),
                        PathNode.QuadTo(387.2f, 994.1f, 474.7f, 994.1f),
                        PathNode.QuadTo(562.2f, 994.1f, 636.0f, 950.9f),
                        PathNode.QuadTo(709.8f, 907.6f, 753.1f, 833.3f),
                        PathNode.QuadTo(796.3f, 759.1f, 796.3f, 671.5f),
                        PathNode.QuadTo(796.3f, 584.0f, 753.1f, 509.8f),
                        PathNode.QuadTo(709.8f, 435.5f, 636.0f, 392.2f),
                        PathNode.QuadTo(562.2f, 349.0f, 474.7f, 349.0f),
                        PathNode.QuadTo(387.1f, 349.0f, 312.8f, 392.3f),
                        PathNode.QuadTo(238.6f, 435.5f, 195.3f, 509.8f),
                        PathNode.QuadTo(152.1f, 584.1f, 152.1f, 671.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchNormal!!
    }

private var _searchNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Search: ImageVector
    get() {
        if (_searchRegular != null) return _searchRegular!!
        _searchRegular = ImageVector.Builder(
            name = "Search.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(695.9f, 335.2f),
                        PathNode.QuadTo(715.9f, 347.2f, 723.9f, 346.7f),
                        PathNode.QuadTo(731.9f, 346.2f, 745.9f, 332.2f),
                        PathNode.LineTo(996.9f, 81.2f),
                        PathNode.QuadTo(1007.9f, 70.2f, 1018.4f, 69.7f),
                        PathNode.QuadTo(1028.9f, 69.2f, 1040.9f, 81.2f),
                        PathNode.LineTo(1057.9f, 98.2f),
                        PathNode.QuadTo(1069.9f, 110.2f, 1069.9f, 119.7f),
                        PathNode.QuadTo(1069.9f, 129.2f, 1057.9f, 141.2f),
                        PathNode.LineTo(803.9f, 396.2f),
                        PathNode.QuadTo(791.9f, 408.2f, 792.4f, 415.7f),
                        PathNode.QuadTo(792.9f, 423.2f, 805.9f, 442.2f),
                        PathNode.QuadTo(839.9f, 491.2f, 858.4f, 549.7f),
                        PathNode.QuadTo(876.9f, 608.2f, 876.9f, 671.2f),
                        PathNode.QuadTo(876.9f, 780.2f, 822.9f, 872.7f),
                        PathNode.QuadTo(768.9f, 965.2f, 676.4f, 1019.2f),
                        PathNode.QuadTo(583.9f, 1073.2f, 474.9f, 1073.2f),
                        PathNode.QuadTo(365.9f, 1073.2f, 273.4f, 1019.2f),
                        PathNode.QuadTo(180.9f, 965.2f, 126.9f, 872.7f),
                        PathNode.QuadTo(72.9f, 780.2f, 72.9f, 671.2f),
                        PathNode.QuadTo(72.9f, 562.2f, 126.9f, 469.7f),
                        PathNode.QuadTo(180.9f, 377.2f, 273.4f, 323.2f),
                        PathNode.QuadTo(365.9f, 269.2f, 474.9f, 269.2f),
                        PathNode.QuadTo(534.9f, 269.2f, 591.4f, 286.2f),
                        PathNode.QuadTo(647.9f, 303.2f, 695.9f, 335.2f),
                        PathNode.Close,
                        PathNode.MoveTo(157.9f, 671.2f),
                        PathNode.QuadTo(157.9f, 757.2f, 200.4f, 830.2f),
                        PathNode.QuadTo(242.9f, 903.2f, 315.9f, 945.7f),
                        PathNode.QuadTo(388.9f, 988.2f, 474.9f, 988.2f),
                        PathNode.QuadTo(560.9f, 988.2f, 633.4f, 945.7f),
                        PathNode.QuadTo(705.9f, 903.2f, 748.4f, 830.2f),
                        PathNode.QuadTo(790.9f, 757.2f, 790.9f, 671.2f),
                        PathNode.QuadTo(790.9f, 585.2f, 748.4f, 512.2f),
                        PathNode.QuadTo(705.9f, 439.2f, 633.4f, 396.7f),
                        PathNode.QuadTo(560.9f, 354.2f, 474.9f, 354.2f),
                        PathNode.QuadTo(388.9f, 354.2f, 315.9f, 396.7f),
                        PathNode.QuadTo(242.9f, 439.2f, 200.4f, 512.2f),
                        PathNode.QuadTo(157.9f, 585.2f, 157.9f, 671.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchRegular!!
    }

private var _searchRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Search: ImageVector
    get() {
        if (_searchMedium != null) return _searchMedium!!
        _searchMedium = ImageVector.Builder(
            name = "Search.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(701.0f, 327.2f),
                        PathNode.QuadTo(716.4f, 336.5f, 723.0f, 335.9f),
                        PathNode.QuadTo(729.7f, 335.2f, 741.0f, 323.9f),
                        PathNode.LineTo(990.1f, 74.6f),
                        PathNode.QuadTo(1004.1f, 60.6f, 1018.4f, 60.1f),
                        PathNode.QuadTo(1032.6f, 59.6f, 1047.6f, 74.6f),
                        PathNode.LineTo(1064.6f, 91.6f),
                        PathNode.QuadTo(1079.3f, 106.3f, 1079.5f, 119.7f),
                        PathNode.QuadTo(1079.6f, 133.1f, 1064.6f, 148.1f),
                        PathNode.LineTo(812.5f, 401.2f),
                        PathNode.QuadTo(803.2f, 410.5f, 803.6f, 416.5f),
                        PathNode.QuadTo(803.9f, 422.5f, 813.7f, 436.9f),
                        PathNode.QuadTo(848.5f, 487.3f, 867.4f, 547.1f),
                        PathNode.QuadTo(886.3f, 607.0f, 886.3f, 671.3f),
                        PathNode.QuadTo(886.3f, 783.0f, 831.0f, 877.6f),
                        PathNode.QuadTo(775.6f, 972.1f, 681.1f, 1027.4f),
                        PathNode.QuadTo(586.6f, 1082.8f, 474.9f, 1082.8f),
                        PathNode.QuadTo(363.2f, 1082.8f, 268.6f, 1027.4f),
                        PathNode.QuadTo(174.1f, 972.1f, 118.8f, 877.6f),
                        PathNode.QuadTo(63.4f, 783.0f, 63.4f, 671.3f),
                        PathNode.QuadTo(63.4f, 559.6f, 118.8f, 465.1f),
                        PathNode.QuadTo(174.1f, 370.6f, 268.6f, 315.2f),
                        PathNode.QuadTo(363.2f, 259.9f, 474.9f, 259.9f),
                        PathNode.QuadTo(536.2f, 259.9f, 594.1f, 277.3f),
                        PathNode.QuadTo(651.9f, 294.7f, 701.0f, 327.2f),
                        PathNode.Close,
                        PathNode.MoveTo(167.3f, 671.3f),
                        PathNode.QuadTo(167.3f, 754.9f, 208.6f, 825.6f),
                        PathNode.QuadTo(249.9f, 896.3f, 320.6f, 937.6f),
                        PathNode.QuadTo(391.3f, 978.9f, 474.9f, 978.9f),
                        PathNode.QuadTo(558.4f, 978.9f, 628.8f, 937.6f),
                        PathNode.QuadTo(699.1f, 896.3f, 740.3f, 825.6f),
                        PathNode.QuadTo(781.4f, 754.9f, 781.4f, 671.3f),
                        PathNode.QuadTo(781.4f, 587.8f, 740.3f, 517.1f),
                        PathNode.QuadTo(699.1f, 446.3f, 628.8f, 405.1f),
                        PathNode.QuadTo(558.4f, 363.8f, 474.9f, 363.8f),
                        PathNode.QuadTo(391.3f, 363.8f, 320.6f, 405.1f),
                        PathNode.QuadTo(249.9f, 446.3f, 208.6f, 517.1f),
                        PathNode.QuadTo(167.3f, 587.8f, 167.3f, 671.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchMedium!!
    }

private var _searchMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Search: ImageVector
    get() {
        if (_searchDemibold != null) return _searchDemibold!!
        _searchDemibold = ImageVector.Builder(
            name = "Search.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(704.5f, 321.6f),
                        PathNode.QuadTo(716.7f, 329.0f, 722.4f, 328.3f),
                        PathNode.QuadTo(728.1f, 327.6f, 737.5f, 318.2f),
                        PathNode.LineTo(985.3f, 69.9f),
                        PathNode.QuadTo(1001.4f, 53.9f, 1018.3f, 53.4f),
                        PathNode.QuadTo(1035.2f, 52.9f, 1052.3f, 69.9f),
                        PathNode.LineTo(1069.3f, 86.9f),
                        PathNode.QuadTo(1085.9f, 103.5f, 1086.1f, 119.7f),
                        PathNode.QuadTo(1086.4f, 135.8f, 1069.3f, 152.9f),
                        PathNode.LineTo(818.5f, 404.7f),
                        PathNode.QuadTo(811.1f, 412.1f, 811.4f, 417.1f),
                        PathNode.QuadTo(811.7f, 422.0f, 819.1f, 433.2f),
                        PathNode.QuadTo(854.5f, 484.5f, 873.7f, 545.3f),
                        PathNode.QuadTo(892.9f, 606.1f, 892.9f, 671.4f),
                        PathNode.QuadTo(892.9f, 785.0f, 836.6f, 881.0f),
                        PathNode.QuadTo(780.3f, 976.9f, 684.4f, 1033.2f),
                        PathNode.QuadTo(588.4f, 1089.5f, 474.8f, 1089.5f),
                        PathNode.QuadTo(361.2f, 1089.5f, 265.3f, 1033.2f),
                        PathNode.QuadTo(169.3f, 976.9f, 113.0f, 881.0f),
                        PathNode.QuadTo(56.7f, 785.0f, 56.7f, 671.4f),
                        PathNode.QuadTo(56.7f, 557.8f, 113.0f, 461.9f),
                        PathNode.QuadTo(169.3f, 365.9f, 265.3f, 309.6f),
                        PathNode.QuadTo(361.2f, 253.3f, 474.8f, 253.3f),
                        PathNode.QuadTo(537.1f, 253.3f, 595.9f, 271.0f),
                        PathNode.QuadTo(654.7f, 288.7f, 704.5f, 321.6f),
                        PathNode.Close,
                        PathNode.MoveTo(173.9f, 671.4f),
                        PathNode.QuadTo(173.9f, 753.3f, 214.3f, 822.4f),
                        PathNode.QuadTo(254.8f, 891.5f, 323.9f, 931.9f),
                        PathNode.QuadTo(392.9f, 972.3f, 474.8f, 972.3f),
                        PathNode.QuadTo(556.7f, 972.3f, 625.5f, 931.9f),
                        PathNode.QuadTo(694.3f, 891.5f, 734.5f, 822.4f),
                        PathNode.QuadTo(774.7f, 753.3f, 774.7f, 671.4f),
                        PathNode.QuadTo(774.7f, 589.6f, 734.5f, 520.5f),
                        PathNode.QuadTo(694.3f, 451.4f, 625.5f, 410.9f),
                        PathNode.QuadTo(556.7f, 370.5f, 474.8f, 370.5f),
                        PathNode.QuadTo(392.9f, 370.5f, 323.9f, 410.9f),
                        PathNode.QuadTo(254.8f, 451.4f, 214.3f, 520.5f),
                        PathNode.QuadTo(173.9f, 589.6f, 173.9f, 671.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchDemibold!!
    }

private var _searchDemibold: ImageVector? = null
