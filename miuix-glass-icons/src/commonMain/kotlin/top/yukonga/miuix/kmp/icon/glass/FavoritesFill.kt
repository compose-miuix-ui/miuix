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

val MiuixIcons.Glass.FavoritesFill: ImageVector
    get() = MiuixIcons.Glass.Regular.FavoritesFill

val MiuixIcons.Glass.Light.FavoritesFill: ImageVector
    get() {
        if (_favoritesFillLight != null) return _favoritesFillLight!!
        _favoritesFillLight = ImageVector.Builder(
            name = "FavoritesFill.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(609.2f, 983.7f),
                        PathNode.QuadTo(598.0f, 974.0f, 583.4f, 958.5f),
                        PathNode.QuadTo(576.9f, 951.6f, 571.4f, 951.3f),
                        PathNode.QuadTo(565.8f, 951.1f, 560.8f, 957.6f),
                        PathNode.QuadTo(553.3f, 966.6f, 546.4f, 973.2f),
                        PathNode.QuadTo(539.5f, 979.8f, 535.0f, 983.2f),
                        PathNode.QuadTo(493.9f, 1016.8f, 444.0f, 1035.1f),
                        PathNode.QuadTo(394.0f, 1053.5f, 339.8f, 1053.5f),
                        PathNode.QuadTo(256.5f, 1053.5f, 186.2f, 1012.8f),
                        PathNode.QuadTo(115.9f, 972.1f, 74.8f, 902.1f),
                        PathNode.QuadTo(33.6f, 832.1f, 33.6f, 748.8f),
                        PathNode.QuadTo(33.6f, 705.1f, 45.2f, 664.7f),
                        PathNode.QuadTo(56.7f, 624.2f, 79.3f, 588.7f),
                        PathNode.QuadTo(135.3f, 493.7f, 263.7f, 363.8f),
                        PathNode.QuadTo(392.0f, 233.9f, 523.4f, 117.6f),
                        PathNode.QuadTo(535.9f, 106.0f, 544.6f, 99.4f),
                        PathNode.QuadTo(553.3f, 92.8f, 559.5f, 91.2f),
                        PathNode.QuadTo(573.8f, 87.6f, 585.4f, 91.2f),
                        PathNode.QuadTo(590.6f, 92.8f, 599.8f, 99.9f),
                        PathNode.QuadTo(608.9f, 107.0f, 621.5f, 118.6f),
                        PathNode.QuadTo(747.3f, 230.9f, 870.9f, 354.8f),
                        PathNode.QuadTo(994.6f, 478.7f, 1057.0f, 576.1f),
                        PathNode.QuadTo(1109.3f, 652.5f, 1109.3f, 748.8f),
                        PathNode.QuadTo(1109.3f, 832.1f, 1068.1f, 902.1f),
                        PathNode.QuadTo(1026.9f, 972.1f, 957.2f, 1012.8f),
                        PathNode.QuadTo(887.4f, 1053.5f, 804.0f, 1053.5f),
                        PathNode.QuadTo(749.8f, 1053.5f, 700.0f, 1035.1f),
                        PathNode.QuadTo(650.3f, 1016.8f, 609.2f, 983.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesFillLight!!
    }

private var _favoritesFillLight: ImageVector? = null

val MiuixIcons.Glass.Normal.FavoritesFill: ImageVector
    get() {
        if (_favoritesFillNormal != null) return _favoritesFillNormal!!
        _favoritesFillNormal = ImageVector.Builder(
            name = "FavoritesFill.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(602.1f, 987.1f),
                        PathNode.QuadTo(593.2f, 979.3f, 583.1f, 968.9f),
                        PathNode.QuadTo(576.9f, 962.6f, 571.1f, 962.6f),
                        PathNode.QuadTo(565.2f, 962.5f, 560.2f, 968.6f),
                        PathNode.QuadTo(555.1f, 974.9f, 550.2f, 979.4f),
                        PathNode.QuadTo(545.3f, 983.8f, 541.2f, 987.0f),
                        PathNode.QuadTo(499.4f, 1020.8f, 448.4f, 1039.6f),
                        PathNode.QuadTo(397.4f, 1058.4f, 342.0f, 1058.4f),
                        PathNode.QuadTo(256.8f, 1058.4f, 185.0f, 1016.8f),
                        PathNode.QuadTo(113.2f, 975.3f, 71.1f, 903.9f),
                        PathNode.QuadTo(29.0f, 832.5f, 29.0f, 747.3f),
                        PathNode.QuadTo(29.0f, 702.8f, 40.9f, 661.2f),
                        PathNode.QuadTo(52.7f, 619.7f, 75.6f, 583.9f),
                        PathNode.QuadTo(131.6f, 488.9f, 259.4f, 359.6f),
                        PathNode.QuadTo(387.2f, 230.3f, 517.6f, 114.9f),
                        PathNode.QuadTo(530.4f, 103.0f, 540.0f, 95.8f),
                        PathNode.QuadTo(549.6f, 88.6f, 557.0f, 86.7f),
                        PathNode.QuadTo(573.2f, 82.1f, 587.8f, 86.7f),
                        PathNode.QuadTo(594.3f, 88.6f, 604.3f, 96.3f),
                        PathNode.QuadTo(614.4f, 104.0f, 627.3f, 115.9f),
                        PathNode.QuadTo(751.8f, 227.3f, 875.1f, 350.6f),
                        PathNode.QuadTo(998.3f, 473.9f, 1060.4f, 571.0f),
                        PathNode.QuadTo(1113.8f, 649.2f, 1113.8f, 747.3f),
                        PathNode.QuadTo(1113.8f, 832.5f, 1071.8f, 903.9f),
                        PathNode.QuadTo(1029.7f, 975.3f, 958.4f, 1016.8f),
                        PathNode.QuadTo(887.1f, 1058.4f, 801.9f, 1058.4f),
                        PathNode.QuadTo(746.5f, 1058.4f, 695.2f, 1039.6f),
                        PathNode.QuadTo(643.8f, 1020.8f, 602.1f, 987.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesFillNormal!!
    }

private var _favoritesFillNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.FavoritesFill: ImageVector
    get() {
        if (_favoritesFillRegular != null) return _favoritesFillRegular!!
        _favoritesFillRegular = ImageVector.Builder(
            name = "FavoritesFill.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(598.9f, 988.7f),
                        PathNode.QuadTo(590.9f, 981.7f, 582.9f, 973.7f),
                        PathNode.QuadTo(576.9f, 967.7f, 570.9f, 967.7f),
                        PathNode.QuadTo(564.9f, 967.7f, 559.9f, 973.7f),
                        PathNode.QuadTo(555.9f, 978.7f, 551.9f, 982.2f),
                        PathNode.QuadTo(547.9f, 985.7f, 543.9f, 988.7f),
                        PathNode.QuadTo(501.9f, 1022.7f, 450.4f, 1041.7f),
                        PathNode.QuadTo(398.9f, 1060.7f, 342.9f, 1060.7f),
                        PathNode.QuadTo(256.9f, 1060.7f, 184.4f, 1018.7f),
                        PathNode.QuadTo(111.9f, 976.7f, 69.4f, 904.7f),
                        PathNode.QuadTo(26.9f, 832.7f, 26.9f, 746.7f),
                        PathNode.QuadTo(26.9f, 701.7f, 38.9f, 659.7f),
                        PathNode.QuadTo(50.9f, 617.7f, 73.9f, 581.7f),
                        PathNode.QuadTo(129.9f, 486.7f, 257.4f, 357.7f),
                        PathNode.QuadTo(384.9f, 228.7f, 514.9f, 113.7f),
                        PathNode.QuadTo(527.9f, 101.7f, 537.9f, 94.2f),
                        PathNode.QuadTo(547.9f, 86.7f, 555.9f, 84.7f),
                        PathNode.QuadTo(572.9f, 79.7f, 588.9f, 84.7f),
                        PathNode.QuadTo(595.9f, 86.7f, 606.4f, 94.7f),
                        PathNode.QuadTo(616.9f, 102.7f, 629.9f, 114.7f),
                        PathNode.QuadTo(753.9f, 225.7f, 876.9f, 348.7f),
                        PathNode.QuadTo(999.9f, 471.7f, 1061.9f, 568.7f),
                        PathNode.QuadTo(1115.9f, 647.7f, 1115.9f, 746.7f),
                        PathNode.QuadTo(1115.9f, 832.7f, 1073.4f, 904.7f),
                        PathNode.QuadTo(1030.9f, 976.7f, 958.9f, 1018.7f),
                        PathNode.QuadTo(886.9f, 1060.7f, 800.9f, 1060.7f),
                        PathNode.QuadTo(744.9f, 1060.7f, 692.9f, 1041.7f),
                        PathNode.QuadTo(640.9f, 1022.7f, 598.9f, 988.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesFillRegular!!
    }

private var _favoritesFillRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.FavoritesFill: ImageVector
    get() {
        if (_favoritesFillMedium != null) return _favoritesFillMedium!!
        _favoritesFillMedium = ImageVector.Builder(
            name = "FavoritesFill.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(596.5f, 995.4f),
                        PathNode.QuadTo(588.2f, 988.4f, 580.8f, 980.9f),
                        PathNode.QuadTo(576.1f, 976.3f, 571.2f, 976.3f),
                        PathNode.QuadTo(566.3f, 976.3f, 562.4f, 980.9f),
                        PathNode.QuadTo(558.4f, 985.4f, 554.9f, 988.5f),
                        PathNode.QuadTo(551.4f, 991.6f, 547.4f, 994.6f),
                        PathNode.QuadTo(505.4f, 1028.1f, 453.4f, 1047.1f),
                        PathNode.QuadTo(401.4f, 1066.1f, 344.8f, 1066.1f),
                        PathNode.QuadTo(256.9f, 1066.1f, 182.8f, 1023.1f),
                        PathNode.QuadTo(108.7f, 980.2f, 65.4f, 906.5f),
                        PathNode.QuadTo(22.1f, 832.9f, 22.1f, 745.3f),
                        PathNode.QuadTo(22.1f, 699.5f, 34.3f, 656.4f),
                        PathNode.QuadTo(46.6f, 613.3f, 69.9f, 576.8f),
                        PathNode.QuadTo(125.9f, 481.8f, 253.1f, 353.1f),
                        PathNode.QuadTo(380.3f, 224.3f, 509.3f, 110.4f),
                        PathNode.QuadTo(523.6f, 97.1f, 534.0f, 89.6f),
                        PathNode.QuadTo(544.4f, 82.1f, 553.5f, 79.5f),
                        PathNode.QuadTo(573.2f, 74.0f, 591.4f, 79.8f),
                        PathNode.QuadTo(599.7f, 82.1f, 610.3f, 89.9f),
                        PathNode.QuadTo(621.0f, 97.8f, 635.6f, 111.4f),
                        PathNode.QuadTo(758.8f, 221.3f, 881.3f, 344.1f),
                        PathNode.QuadTo(1003.7f, 466.8f, 1065.7f, 563.5f),
                        PathNode.QuadTo(1120.8f, 644.7f, 1120.8f, 745.3f),
                        PathNode.QuadTo(1120.8f, 832.9f, 1077.5f, 906.5f),
                        PathNode.QuadTo(1034.2f, 980.2f, 960.6f, 1023.1f),
                        PathNode.QuadTo(886.9f, 1066.1f, 799.0f, 1066.1f),
                        PathNode.QuadTo(742.2f, 1066.1f, 689.8f, 1047.2f),
                        PathNode.QuadTo(637.4f, 1028.3f, 596.5f, 995.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesFillMedium!!
    }

private var _favoritesFillMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.FavoritesFill: ImageVector
    get() {
        if (_favoritesFillDemibold != null) return _favoritesFillDemibold!!
        _favoritesFillDemibold = ImageVector.Builder(
            name = "FavoritesFill.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(594.8f, 1000.1f),
                        PathNode.QuadTo(586.3f, 993.1f, 579.3f, 986.0f),
                        PathNode.QuadTo(575.6f, 982.3f, 571.4f, 982.3f),
                        PathNode.QuadTo(567.2f, 982.3f, 564.1f, 986.0f),
                        PathNode.QuadTo(560.1f, 990.1f, 557.0f, 992.9f),
                        PathNode.QuadTo(553.9f, 995.7f, 549.9f, 998.7f),
                        PathNode.QuadTo(507.9f, 1031.8f, 455.5f, 1050.8f),
                        PathNode.QuadTo(403.1f, 1069.8f, 346.1f, 1069.8f),
                        PathNode.QuadTo(256.9f, 1069.8f, 181.7f, 1026.2f),
                        PathNode.QuadTo(106.4f, 982.6f, 62.5f, 907.8f),
                        PathNode.QuadTo(18.7f, 833.1f, 18.7f, 744.3f),
                        PathNode.QuadTo(18.7f, 697.9f, 31.1f, 654.1f),
                        PathNode.QuadTo(43.6f, 610.3f, 67.0f, 573.4f),
                        PathNode.QuadTo(123.0f, 478.4f, 250.1f, 349.8f),
                        PathNode.QuadTo(377.1f, 221.3f, 505.3f, 108.1f),
                        PathNode.QuadTo(520.6f, 93.8f, 531.3f, 86.3f),
                        PathNode.QuadTo(542.0f, 78.8f, 551.8f, 75.9f),
                        PathNode.QuadTo(573.4f, 70.0f, 593.1f, 76.4f),
                        PathNode.QuadTo(602.4f, 78.8f, 613.1f, 86.6f),
                        PathNode.QuadTo(623.8f, 94.4f, 639.6f, 109.1f),
                        PathNode.QuadTo(762.2f, 218.3f, 884.3f, 340.8f),
                        PathNode.QuadTo(1006.4f, 463.4f, 1068.4f, 559.9f),
                        PathNode.QuadTo(1124.2f, 642.6f, 1124.2f, 744.3f),
                        PathNode.QuadTo(1124.2f, 833.1f, 1080.3f, 907.8f),
                        PathNode.QuadTo(1036.4f, 982.6f, 961.7f, 1026.2f),
                        PathNode.QuadTo(886.9f, 1069.8f, 797.7f, 1069.8f),
                        PathNode.QuadTo(740.3f, 1069.8f, 687.6f, 1051.0f),
                        PathNode.QuadTo(635.0f, 1032.3f, 594.8f, 1000.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _favoritesFillDemibold!!
    }

private var _favoritesFillDemibold: ImageVector? = null
