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

val MiuixIcons.Search: ImageVector
    get() = MiuixIcons.Regular.Search

val MiuixIcons.Light.Search: ImageVector
    get() {
        if (_searchLight != null) return _searchLight!!
        _searchLight = ImageVector.Builder(
            name = "Search.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1188.0f,
            viewportHeight = 1188.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1188.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(732.0f, 379.0f),
                        PathNode.QuadTo(744.0f, 387.0f, 750.5f, 386.5f),
                        PathNode.QuadTo(757.0f, 386.0f, 766.0f, 377.0f),
                        PathNode.LineTo(1035.0f, 107.0f),
                        PathNode.QuadTo(1042.2f, 99.7f, 1049.1f, 99.3f),
                        PathNode.QuadTo(1056.0f, 99.0f, 1064.0f, 107.0f),
                        PathNode.LineTo(1078.0f, 121.0f),
                        PathNode.QuadTo(1085.0f, 128.7f, 1085.0f, 134.9f),
                        PathNode.QuadTo(1085.0f, 141.0f, 1078.0f, 148.0f),
                        PathNode.LineTo(806.0f, 421.0f),
                        PathNode.QuadTo(798.0f, 429.4f, 798.5f, 434.7f),
                        PathNode.QuadTo(799.0f, 440.0f, 807.0f, 452.0f),
                        PathNode.QuadTo(851.0f, 511.0f, 870.5f, 570.5f),
                        PathNode.QuadTo(890.0f, 630.0f, 890.0f, 695.0f),
                        PathNode.QuadTo(890.0f, 801.8f, 837.2f, 892.5f),
                        PathNode.QuadTo(784.4f, 983.1f, 694.0f, 1036.1f),
                        PathNode.QuadTo(603.6f, 1089.0f, 497.0f, 1089.0f),
                        PathNode.QuadTo(390.2f, 1089.0f, 299.5f, 1036.1f),
                        PathNode.QuadTo(208.9f, 983.1f, 155.9f, 892.5f),
                        PathNode.QuadTo(103.0f, 801.8f, 103.0f, 695.0f),
                        PathNode.QuadTo(103.0f, 588.4f, 155.9f, 498.0f),
                        PathNode.QuadTo(208.9f, 407.6f, 299.5f, 354.8f),
                        PathNode.QuadTo(390.2f, 302.0f, 497.0f, 302.0f),
                        PathNode.QuadTo(559.0f, 302.0f, 616.0f, 320.5f),
                        PathNode.QuadTo(673.0f, 339.0f, 732.0f, 379.0f),
                        PathNode.Close,
                        PathNode.MoveTo(162.0f, 695.0f),
                        PathNode.QuadTo(162.0f, 785.9f, 206.9f, 863.0f),
                        PathNode.QuadTo(251.9f, 940.2f, 329.0f, 985.1f),
                        PathNode.QuadTo(406.2f, 1030.0f, 497.1f, 1030.0f),
                        PathNode.QuadTo(588.0f, 1030.0f, 664.6f, 985.1f),
                        PathNode.QuadTo(741.2f, 940.2f, 786.1f, 863.0f),
                        PathNode.QuadTo(831.0f, 785.9f, 831.0f, 695.0f),
                        PathNode.QuadTo(831.0f, 604.4f, 786.1f, 527.5f),
                        PathNode.QuadTo(741.1f, 450.6f, 664.5f, 405.8f),
                        PathNode.QuadTo(587.8f, 361.0f, 496.9f, 361.0f),
                        PathNode.QuadTo(406.0f, 361.0f, 328.9f, 405.8f),
                        PathNode.QuadTo(251.8f, 450.6f, 206.9f, 527.5f),
                        PathNode.QuadTo(162.0f, 604.4f, 162.0f, 695.0f),
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

val MiuixIcons.Normal.Search: ImageVector
    get() {
        if (_searchNormal != null) return _searchNormal!!
        _searchNormal = ImageVector.Builder(
            name = "Search.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1199.5f,
            viewportHeight = 1199.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1199.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(728.5f, 370.3f),
                        PathNode.QuadTo(746.0f, 381.1f, 753.5f, 380.6f),
                        PathNode.QuadTo(761.1f, 380.1f, 773.5f, 367.6f),
                        PathNode.LineTo(1030.1f, 110.7f),
                        PathNode.QuadTo(1039.9f, 100.9f, 1049.3f, 100.4f),
                        PathNode.QuadTo(1058.7f, 100.0f, 1069.4f, 110.7f),
                        PathNode.LineTo(1085.5f, 126.8f),
                        PathNode.QuadTo(1095.9f, 137.4f, 1095.9f, 145.9f),
                        PathNode.QuadTo(1095.9f, 154.3f, 1085.5f, 164.8f),
                        PathNode.LineTo(825.9f, 425.4f),
                        PathNode.QuadTo(815.1f, 436.3f, 815.6f, 443.1f),
                        PathNode.QuadTo(816.1f, 449.9f, 827.6f, 466.7f),
                        PathNode.QuadTo(864.7f, 518.8f, 883.5f, 577.6f),
                        PathNode.QuadTo(902.3f, 636.5f, 902.3f, 700.1f),
                        PathNode.QuadTo(902.3f, 808.4f, 848.7f, 900.3f),
                        PathNode.QuadTo(795.1f, 992.3f, 703.2f, 1045.9f),
                        PathNode.QuadTo(611.4f, 1099.6f, 503.1f, 1099.6f),
                        PathNode.QuadTo(394.8f, 1099.6f, 302.9f, 1045.9f),
                        PathNode.QuadTo(210.9f, 992.3f, 157.3f, 900.3f),
                        PathNode.QuadTo(103.6f, 808.4f, 103.6f, 700.1f),
                        PathNode.QuadTo(103.6f, 591.9f, 157.3f, 500.0f),
                        PathNode.QuadTo(210.9f, 408.1f, 302.9f, 354.5f),
                        PathNode.QuadTo(394.8f, 300.9f, 503.1f, 300.9f),
                        PathNode.QuadTo(563.7f, 300.9f, 620.4f, 318.4f),
                        PathNode.QuadTo(677.1f, 335.8f, 728.5f, 370.3f),
                        PathNode.Close,
                        PathNode.MoveTo(180.5f, 700.1f),
                        PathNode.QuadTo(180.5f, 787.6f, 223.8f, 861.9f),
                        PathNode.QuadTo(267.0f, 936.2f, 341.3f, 979.5f),
                        PathNode.QuadTo(415.6f, 1022.7f, 503.1f, 1022.7f),
                        PathNode.QuadTo(590.7f, 1022.7f, 664.5f, 979.5f),
                        PathNode.QuadTo(738.2f, 936.2f, 781.5f, 861.9f),
                        PathNode.QuadTo(824.7f, 787.6f, 824.7f, 700.1f),
                        PathNode.QuadTo(824.7f, 612.6f, 781.5f, 538.4f),
                        PathNode.QuadTo(738.2f, 464.2f, 664.4f, 421.0f),
                        PathNode.QuadTo(590.6f, 377.8f, 503.1f, 377.8f),
                        PathNode.QuadTo(415.6f, 377.8f, 341.3f, 421.0f),
                        PathNode.QuadTo(267.0f, 464.2f, 223.7f, 538.4f),
                        PathNode.QuadTo(180.5f, 612.6f, 180.5f, 700.1f),
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

val MiuixIcons.Regular.Search: ImageVector
    get() {
        if (_searchRegular != null) return _searchRegular!!
        _searchRegular = ImageVector.Builder(
            name = "Search.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1204.8f,
            viewportHeight = 1204.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1204.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(726.9f, 366.4f),
                        PathNode.QuadTo(746.9f, 378.4f, 754.9f, 377.9f),
                        PathNode.QuadTo(762.9f, 377.4f, 776.9f, 363.4f),
                        PathNode.LineTo(1027.9f, 112.4f),
                        PathNode.QuadTo(1038.9f, 101.4f, 1049.4f, 100.9f),
                        PathNode.QuadTo(1059.9f, 100.4f, 1071.9f, 112.4f),
                        PathNode.LineTo(1088.9f, 129.4f),
                        PathNode.QuadTo(1100.9f, 141.4f, 1100.9f, 150.9f),
                        PathNode.QuadTo(1100.9f, 160.4f, 1088.9f, 172.4f),
                        PathNode.LineTo(834.9f, 427.4f),
                        PathNode.QuadTo(822.9f, 439.4f, 823.4f, 446.9f),
                        PathNode.QuadTo(823.9f, 454.4f, 836.9f, 473.4f),
                        PathNode.QuadTo(870.9f, 522.4f, 889.4f, 580.9f),
                        PathNode.QuadTo(907.9f, 639.4f, 907.9f, 702.4f),
                        PathNode.QuadTo(907.9f, 811.4f, 853.9f, 903.9f),
                        PathNode.QuadTo(799.9f, 996.4f, 707.4f, 1050.4f),
                        PathNode.QuadTo(614.9f, 1104.4f, 505.9f, 1104.4f),
                        PathNode.QuadTo(396.9f, 1104.4f, 304.4f, 1050.4f),
                        PathNode.QuadTo(211.9f, 996.4f, 157.9f, 903.9f),
                        PathNode.QuadTo(103.9f, 811.4f, 103.9f, 702.4f),
                        PathNode.QuadTo(103.9f, 593.4f, 157.9f, 500.9f),
                        PathNode.QuadTo(211.9f, 408.4f, 304.4f, 354.4f),
                        PathNode.QuadTo(396.9f, 300.4f, 505.9f, 300.4f),
                        PathNode.QuadTo(565.9f, 300.4f, 622.4f, 317.4f),
                        PathNode.QuadTo(678.9f, 334.4f, 726.9f, 366.4f),
                        PathNode.Close,
                        PathNode.MoveTo(188.9f, 702.4f),
                        PathNode.QuadTo(188.9f, 788.4f, 231.4f, 861.4f),
                        PathNode.QuadTo(273.9f, 934.4f, 346.9f, 976.9f),
                        PathNode.QuadTo(419.9f, 1019.4f, 505.9f, 1019.4f),
                        PathNode.QuadTo(591.9f, 1019.4f, 664.4f, 976.9f),
                        PathNode.QuadTo(736.9f, 934.4f, 779.4f, 861.4f),
                        PathNode.QuadTo(821.9f, 788.4f, 821.9f, 702.4f),
                        PathNode.QuadTo(821.9f, 616.4f, 779.4f, 543.4f),
                        PathNode.QuadTo(736.9f, 470.4f, 664.4f, 427.9f),
                        PathNode.QuadTo(591.9f, 385.4f, 505.9f, 385.4f),
                        PathNode.QuadTo(419.9f, 385.4f, 346.9f, 427.9f),
                        PathNode.QuadTo(273.9f, 470.4f, 231.4f, 543.4f),
                        PathNode.QuadTo(188.9f, 616.4f, 188.9f, 702.4f),
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

val MiuixIcons.Medium.Search: ImageVector
    get() {
        if (_searchMedium != null) return _searchMedium!!
        _searchMedium = ImageVector.Builder(
            name = "Search.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1227.4f,
            viewportHeight = 1227.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1227.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(743.2f, 369.5f),
                        PathNode.QuadTo(758.5f, 379.1f, 764.7f, 378.9f),
                        PathNode.QuadTo(771.0f, 378.7f, 781.4f, 368.2f),
                        PathNode.LineTo(1032.4f, 117.2f),
                        PathNode.QuadTo(1046.4f, 103.3f, 1060.4f, 102.8f),
                        PathNode.QuadTo(1074.4f, 102.3f, 1089.4f, 117.2f),
                        PathNode.LineTo(1106.4f, 134.2f),
                        PathNode.QuadTo(1121.3f, 148.6f, 1121.6f, 161.9f),
                        PathNode.QuadTo(1121.9f, 175.2f, 1106.4f, 190.2f),
                        PathNode.LineTo(852.4f, 445.2f),
                        PathNode.QuadTo(843.9f, 453.6f, 844.7f, 459.4f),
                        PathNode.QuadTo(845.5f, 465.1f, 855.5f, 479.4f),
                        PathNode.QuadTo(890.7f, 529.6f, 909.5f, 589.5f),
                        PathNode.QuadTo(928.3f, 649.4f, 928.3f, 714.0f),
                        PathNode.QuadTo(928.3f, 825.6f, 873.1f, 920.2f),
                        PathNode.QuadTo(817.9f, 1014.7f, 723.4f, 1069.9f),
                        PathNode.QuadTo(628.8f, 1125.1f, 516.9f, 1125.1f),
                        PathNode.QuadTo(405.0f, 1125.1f, 310.4f, 1069.9f),
                        PathNode.QuadTo(215.9f, 1014.7f, 160.7f, 920.2f),
                        PathNode.QuadTo(105.5f, 825.6f, 105.5f, 713.7f),
                        PathNode.QuadTo(105.5f, 601.8f, 160.7f, 507.2f),
                        PathNode.QuadTo(216.0f, 412.7f, 310.6f, 357.5f),
                        PathNode.QuadTo(405.2f, 302.3f, 516.7f, 302.3f),
                        PathNode.QuadTo(578.1f, 302.3f, 636.0f, 319.6f),
                        PathNode.QuadTo(694.0f, 336.9f, 743.2f, 369.5f),
                        PathNode.Close,
                        PathNode.MoveTo(209.3f, 713.7f),
                        PathNode.QuadTo(209.3f, 797.1f, 250.6f, 868.0f),
                        PathNode.QuadTo(291.8f, 938.8f, 362.6f, 980.0f),
                        PathNode.QuadTo(433.5f, 1021.3f, 516.9f, 1021.3f),
                        PathNode.QuadTo(600.3f, 1021.3f, 670.7f, 980.0f),
                        PathNode.QuadTo(741.0f, 938.8f, 782.3f, 868.0f),
                        PathNode.QuadTo(823.5f, 797.1f, 823.5f, 713.7f),
                        PathNode.QuadTo(823.5f, 630.2f, 782.3f, 559.4f),
                        PathNode.QuadTo(741.0f, 488.6f, 670.7f, 447.3f),
                        PathNode.QuadTo(600.3f, 406.1f, 516.9f, 406.1f),
                        PathNode.QuadTo(433.5f, 406.1f, 362.6f, 447.3f),
                        PathNode.QuadTo(291.8f, 488.6f, 250.6f, 559.4f),
                        PathNode.QuadTo(209.3f, 630.2f, 209.3f, 713.7f),
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

val MiuixIcons.Demibold.Search: ImageVector
    get() {
        if (_searchDemibold != null) return _searchDemibold!!
        _searchDemibold = ImageVector.Builder(
            name = "Search.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1243.2f,
            viewportHeight = 1243.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1243.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(754.6f, 371.6f),
                        PathNode.QuadTo(766.6f, 379.6f, 771.6f, 379.6f),
                        PathNode.QuadTo(776.6f, 379.6f, 784.6f, 371.6f),
                        PathNode.LineTo(1035.6f, 120.6f),
                        PathNode.QuadTo(1051.6f, 104.6f, 1068.1f, 104.1f),
                        PathNode.QuadTo(1084.6f, 103.6f, 1101.6f, 120.6f),
                        PathNode.LineTo(1118.6f, 137.6f),
                        PathNode.QuadTo(1135.6f, 153.6f, 1136.1f, 169.6f),
                        PathNode.QuadTo(1136.6f, 185.6f, 1118.6f, 202.6f),
                        PathNode.LineTo(864.6f, 457.6f),
                        PathNode.QuadTo(858.6f, 463.6f, 859.6f, 468.1f),
                        PathNode.QuadTo(860.6f, 472.6f, 868.6f, 483.6f),
                        PathNode.QuadTo(904.6f, 534.6f, 923.6f, 595.5f),
                        PathNode.QuadTo(942.6f, 656.5f, 942.6f, 722.1f),
                        PathNode.QuadTo(942.6f, 835.6f, 886.6f, 931.6f),
                        PathNode.QuadTo(830.5f, 1027.5f, 734.6f, 1083.6f),
                        PathNode.QuadTo(638.6f, 1139.6f, 524.6f, 1139.6f),
                        PathNode.QuadTo(410.6f, 1139.6f, 314.6f, 1083.6f),
                        PathNode.QuadTo(218.7f, 1027.5f, 162.6f, 931.6f),
                        PathNode.QuadTo(106.6f, 835.6f, 106.6f, 721.6f),
                        PathNode.QuadTo(106.6f, 607.6f, 162.7f, 511.6f),
                        PathNode.QuadTo(218.8f, 415.7f, 314.9f, 359.6f),
                        PathNode.QuadTo(411.0f, 303.6f, 524.3f, 303.6f),
                        PathNode.QuadTo(586.6f, 303.6f, 645.6f, 321.1f),
                        PathNode.QuadTo(704.5f, 338.6f, 754.6f, 371.6f),
                        PathNode.Close,
                        PathNode.MoveTo(223.6f, 721.6f),
                        PathNode.QuadTo(223.6f, 803.3f, 264.0f, 872.6f),
                        PathNode.QuadTo(304.3f, 941.9f, 373.6f, 982.2f),
                        PathNode.QuadTo(442.9f, 1022.6f, 524.6f, 1022.6f),
                        PathNode.QuadTo(606.2f, 1022.6f, 675.1f, 982.2f),
                        PathNode.QuadTo(743.9f, 941.9f, 784.3f, 872.6f),
                        PathNode.QuadTo(824.6f, 803.3f, 824.6f, 721.6f),
                        PathNode.QuadTo(824.6f, 639.9f, 784.3f, 570.6f),
                        PathNode.QuadTo(743.9f, 501.3f, 675.1f, 461.0f),
                        PathNode.QuadTo(606.2f, 420.6f, 524.6f, 420.6f),
                        PathNode.QuadTo(442.9f, 420.6f, 373.6f, 461.0f),
                        PathNode.QuadTo(304.3f, 501.3f, 264.0f, 570.6f),
                        PathNode.QuadTo(223.6f, 639.9f, 223.6f, 721.6f),
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
