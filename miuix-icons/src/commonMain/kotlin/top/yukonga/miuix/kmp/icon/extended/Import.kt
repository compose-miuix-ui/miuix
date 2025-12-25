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

val MiuixIcons.Import: ImageVector
    get() = MiuixIcons.Regular.Import

val MiuixIcons.Light.Import: ImageVector
    get() {
        if (_importLight != null) return _importLight!!
        _importLight = ImageVector.Builder(
            name = "Import.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1201.2f,
            viewportHeight = 1201.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -64.89999999999998f, translationY = 951.6f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1072.0f, -95.0f),
                PathNode.QuadTo(1122.0f, -68.0f, 1146.0f, -22.0f),
                PathNode.QuadTo(1160.0f, 5.0f, 1163.0f, 44.5f),
                PathNode.QuadTo(1166.0f, 84.0f, 1166.0f, 179.0f),
                PathNode.VerticalTo(524.0f),
                PathNode.QuadTo(1166.0f, 620.0f, 1163.0f, 659.0f),
                PathNode.QuadTo(1160.0f, 698.0f, 1146.0f, 724.0f),
                PathNode.QuadTo(1121.0f, 772.0f, 1072.0f, 797.0f),
                PathNode.QuadTo(1046.0f, 811.0f, 1007.0f, 814.5f),
                PathNode.QuadTo(968.0f, 818.0f, 872.0f, 818.0f),
                PathNode.HorizontalTo(695.0f),
                PathNode.QuadTo(600.0f, 818.0f, 560.5f, 814.5f),
                PathNode.QuadTo(521.0f, 811.0f, 494.0f, 797.0f),
                PathNode.QuadTo(445.0f, 772.0f, 420.0f, 724.0f),
                PathNode.QuadTo(406.0f, 698.0f, 403.0f, 662.0f),
                PathNode.QuadTo(400.0f, 626.0f, 400.0f, 546.0f),
                PathNode.VerticalTo(510.0f),
                PathNode.QuadTo(400.0f, 502.0f, 405.5f, 497.0f),
                PathNode.QuadTo(411.0f, 492.0f, 419.0f, 492.0f),
                PathNode.HorizontalTo(441.0f),
                PathNode.QuadTo(449.0f, 492.0f, 454.0f, 497.0f),
                PathNode.QuadTo(459.0f, 502.0f, 459.0f, 510.0f),
                PathNode.VerticalTo(589.0f),
                PathNode.QuadTo(459.0f, 630.0f, 461.5f, 653.5f),
                PathNode.QuadTo(464.0f, 677.0f, 473.0f, 694.0f),
                PathNode.QuadTo(493.0f, 729.0f, 524.0f, 745.0f),
                PathNode.QuadTo(543.0f, 755.0f, 568.5f, 757.0f),
                PathNode.QuadTo(594.0f, 759.0f, 650.0f, 759.0f),
                PathNode.HorizontalTo(917.0f),
                PathNode.QuadTo(974.0f, 759.0f, 998.5f, 757.0f),
                PathNode.QuadTo(1023.0f, 755.0f, 1042.0f, 745.0f),
                PathNode.QuadTo(1073.0f, 730.0f, 1093.0f, 694.0f),
                PathNode.QuadTo(1103.0f, 676.0f, 1105.0f, 650.5f),
                PathNode.QuadTo(1107.0f, 625.0f, 1107.0f, 569.0f),
                PathNode.VerticalTo(133.0f),
                PathNode.QuadTo(1107.0f, 77.0f, 1105.0f, 51.5f),
                PathNode.QuadTo(1103.0f, 26.0f, 1093.0f, 8.0f),
                PathNode.QuadTo(1073.0f, -28.0f, 1042.0f, -43.0f),
                PathNode.QuadTo(1023.0f, -53.0f, 998.5f, -55.0f),
                PathNode.QuadTo(974.0f, -57.0f, 917.0f, -57.0f),
                PathNode.HorizontalTo(650.0f),
                PathNode.QuadTo(594.0f, -57.0f, 568.5f, -55.0f),
                PathNode.QuadTo(543.0f, -53.0f, 524.0f, -43.0f),
                PathNode.QuadTo(493.0f, -27.0f, 473.0f, 8.0f),
                PathNode.QuadTo(464.0f, 25.0f, 461.5f, 48.5f),
                PathNode.QuadTo(459.0f, 72.0f, 459.0f, 113.0f),
                PathNode.VerticalTo(193.0f),
                PathNode.QuadTo(459.0f, 201.0f, 454.5f, 206.0f),
                PathNode.QuadTo(450.0f, 211.0f, 440.0f, 211.0f),
                PathNode.HorizontalTo(419.0f),
                PathNode.QuadTo(410.0f, 211.0f, 405.0f, 205.5f),
                PathNode.QuadTo(400.0f, 200.0f, 400.0f, 193.0f),
                PathNode.VerticalTo(159.0f),
                PathNode.QuadTo(400.0f, 79.0f, 403.5f, 42.0f),
                PathNode.QuadTo(407.0f, 5.0f, 421.0f, -22.0f),
                PathNode.QuadTo(444.0f, -67.0f, 494.0f, -95.0f),
                PathNode.QuadTo(521.0f, -109.0f, 560.5f, -112.5f),
                PathNode.QuadTo(600.0f, -116.0f, 697.0f, -116.0f),
                PathNode.HorizontalTo(872.0f),
                PathNode.QuadTo(968.0f, -116.0f, 1007.0f, -112.5f),
                PathNode.QuadTo(1046.0f, -109.0f, 1072.0f, -95.0f),
                PathNode.Close,
                PathNode.MoveTo(906.0f, 376.0f),
                PathNode.LineTo(694.0f, 588.0f),
                PathNode.QuadTo(689.0f, 593.0f, 682.0f, 594.0f),
                PathNode.QuadTo(675.0f, 595.0f, 668.0f, 588.0f),
                PathNode.LineTo(653.0f, 573.0f),
                PathNode.QuadTo(647.0f, 567.0f, 647.0f, 560.5f),
                PathNode.QuadTo(647.0f, 554.0f, 652.0f, 548.0f),
                PathNode.LineTo(818.0f, 381.0f),
                PathNode.HorizontalTo(183.0f),
                PathNode.QuadTo(176.0f, 381.0f, 170.5f, 376.0f),
                PathNode.QuadTo(165.0f, 371.0f, 165.0f, 363.0f),
                PathNode.VerticalTo(340.0f),
                PathNode.QuadTo(165.0f, 333.0f, 170.5f, 327.5f),
                PathNode.QuadTo(176.0f, 322.0f, 183.0f, 322.0f),
                PathNode.HorizontalTo(819.0f),
                PathNode.LineTo(654.0f, 156.0f),
                PathNode.QuadTo(648.0f, 150.0f, 647.5f, 143.5f),
                PathNode.QuadTo(647.0f, 137.0f, 653.0f, 130.0f),
                PathNode.LineTo(666.0f, 116.0f),
                PathNode.QuadTo(674.0f, 108.0f, 681.5f, 108.0f),
                PathNode.QuadTo(689.0f, 108.0f, 695.0f, 114.0f),
                PathNode.LineTo(905.0f, 325.0f),
                PathNode.QuadTo(919.0f, 338.0f, 919.5f, 350.5f),
                PathNode.QuadTo(920.0f, 363.0f, 906.0f, 376.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _importLight!!
    }

private var _importLight: ImageVector? = null

val MiuixIcons.Regular.Import: ImageVector
    get() {
        if (_importRegular != null) return _importRegular!!
        _importRegular = ImageVector.Builder(
            name = "Import.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1218.0f,
            viewportHeight = 1218.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -56.5f, translationY = 960.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1072.0f, -107.0f),
                PathNode.QuadTo(1124.0f, -78.0f, 1151.0f, -28.0f),
                PathNode.QuadTo(1166.0f, 1.0f, 1169.5f, 42.0f),
                PathNode.QuadTo(1173.0f, 83.0f, 1173.0f, 179.0f),
                PathNode.VerticalTo(524.0f),
                PathNode.QuadTo(1173.0f, 620.0f, 1169.5f, 661.0f),
                PathNode.QuadTo(1166.0f, 702.0f, 1151.0f, 730.0f),
                PathNode.QuadTo(1123.0f, 783.0f, 1072.0f, 809.0f),
                PathNode.QuadTo(1043.0f, 824.0f, 1002.0f, 827.5f),
                PathNode.QuadTo(961.0f, 831.0f, 865.0f, 831.0f),
                PathNode.HorizontalTo(688.0f),
                PathNode.QuadTo(592.0f, 831.0f, 551.0f, 827.5f),
                PathNode.QuadTo(510.0f, 824.0f, 481.0f, 809.0f),
                PathNode.QuadTo(430.0f, 783.0f, 402.0f, 730.0f),
                PathNode.QuadTo(387.0f, 702.0f, 383.5f, 664.0f),
                PathNode.QuadTo(380.0f, 626.0f, 380.0f, 546.0f),
                PathNode.VerticalTo(519.0f),
                PathNode.QuadTo(380.0f, 506.0f, 388.0f, 499.0f),
                PathNode.QuadTo(396.0f, 492.0f, 409.0f, 492.0f),
                PathNode.HorizontalTo(438.0f),
                PathNode.QuadTo(451.0f, 492.0f, 458.5f, 499.0f),
                PathNode.QuadTo(466.0f, 506.0f, 466.0f, 519.0f),
                PathNode.VerticalTo(589.0f),
                PathNode.QuadTo(466.0f, 629.0f, 468.0f, 650.5f),
                PathNode.QuadTo(470.0f, 672.0f, 478.0f, 688.0f),
                PathNode.QuadTo(495.0f, 718.0f, 524.0f, 733.0f),
                PathNode.QuadTo(541.0f, 742.0f, 564.0f, 744.0f),
                PathNode.QuadTo(587.0f, 746.0f, 643.0f, 746.0f),
                PathNode.HorizontalTo(910.0f),
                PathNode.QuadTo(966.0f, 746.0f, 989.0f, 744.0f),
                PathNode.QuadTo(1012.0f, 742.0f, 1029.0f, 733.0f),
                PathNode.QuadTo(1057.0f, 719.0f, 1075.0f, 688.0f),
                PathNode.QuadTo(1083.0f, 672.0f, 1085.0f, 648.0f),
                PathNode.QuadTo(1087.0f, 624.0f, 1087.0f, 569.0f),
                PathNode.VerticalTo(133.0f),
                PathNode.QuadTo(1087.0f, 78.0f, 1085.0f, 54.0f),
                PathNode.QuadTo(1083.0f, 30.0f, 1075.0f, 14.0f),
                PathNode.QuadTo(1057.0f, -17.0f, 1029.0f, -31.0f),
                PathNode.QuadTo(1012.0f, -40.0f, 989.0f, -42.0f),
                PathNode.QuadTo(966.0f, -44.0f, 910.0f, -44.0f),
                PathNode.HorizontalTo(643.0f),
                PathNode.QuadTo(587.0f, -44.0f, 564.0f, -42.0f),
                PathNode.QuadTo(541.0f, -40.0f, 524.0f, -31.0f),
                PathNode.QuadTo(495.0f, -16.0f, 478.0f, 14.0f),
                PathNode.QuadTo(470.0f, 30.0f, 468.0f, 51.5f),
                PathNode.QuadTo(466.0f, 73.0f, 466.0f, 113.0f),
                PathNode.VerticalTo(184.0f),
                PathNode.QuadTo(466.0f, 196.0f, 459.0f, 203.5f),
                PathNode.QuadTo(452.0f, 211.0f, 437.0f, 211.0f),
                PathNode.HorizontalTo(408.0f),
                PathNode.QuadTo(395.0f, 211.0f, 387.5f, 203.0f),
                PathNode.QuadTo(380.0f, 195.0f, 380.0f, 184.0f),
                PathNode.VerticalTo(159.0f),
                PathNode.QuadTo(380.0f, 78.0f, 383.5f, 39.5f),
                PathNode.QuadTo(387.0f, 1.0f, 402.0f, -28.0f),
                PathNode.QuadTo(429.0f, -78.0f, 481.0f, -107.0f),
                PathNode.QuadTo(510.0f, -122.0f, 551.0f, -125.5f),
                PathNode.QuadTo(592.0f, -129.0f, 690.0f, -129.0f),
                PathNode.HorizontalTo(865.0f),
                PathNode.QuadTo(961.0f, -129.0f, 1002.0f, -125.5f),
                PathNode.QuadTo(1043.0f, -122.0f, 1072.0f, -107.0f),
                PathNode.Close,
                PathNode.MoveTo(911.0f, 383.0f),
                PathNode.LineTo(703.0f, 591.0f),
                PathNode.QuadTo(695.0f, 599.0f, 684.5f, 600.5f),
                PathNode.QuadTo(674.0f, 602.0f, 664.0f, 592.0f),
                PathNode.LineTo(643.0f, 571.0f),
                PathNode.QuadTo(633.0f, 561.0f, 633.5f, 551.0f),
                PathNode.QuadTo(634.0f, 541.0f, 641.0f, 533.0f),
                PathNode.LineTo(779.0f, 394.0f),
                PathNode.HorizontalTo(185.0f),
                PathNode.QuadTo(174.0f, 394.0f, 166.0f, 386.0f),
                PathNode.QuadTo(158.0f, 378.0f, 158.0f, 367.0f),
                PathNode.VerticalTo(335.0f),
                PathNode.QuadTo(158.0f, 324.0f, 166.0f, 316.5f),
                PathNode.QuadTo(174.0f, 309.0f, 185.0f, 309.0f),
                PathNode.HorizontalTo(780.0f),
                PathNode.LineTo(644.0f, 172.0f),
                PathNode.QuadTo(636.0f, 164.0f, 634.5f, 153.5f),
                PathNode.QuadTo(633.0f, 143.0f, 643.0f, 133.0f),
                PathNode.LineTo(660.0f, 115.0f),
                PathNode.QuadTo(672.0f, 102.0f, 684.0f, 102.5f),
                PathNode.QuadTo(696.0f, 103.0f, 704.0f, 111.0f),
                PathNode.LineTo(910.0f, 318.0f),
                PathNode.QuadTo(928.0f, 335.0f, 927.5f, 351.5f),
                PathNode.QuadTo(927.0f, 368.0f, 911.0f, 383.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _importRegular!!
    }

private var _importRegular: ImageVector? = null

val MiuixIcons.Heavy.Import: ImageVector
    get() {
        if (_importHeavy != null) return _importHeavy!!
        _importHeavy = ImageVector.Builder(
            name = "Import.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1251.6f,
            viewportHeight = 1251.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -39.700000000000045f, translationY = 976.8f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1078.0f, -119.0f),
                PathNode.QuadTo(1135.0f, -89.0f, 1163.0f, -34.0f),
                PathNode.QuadTo(1179.0f, -3.0f, 1183.0f, 39.5f),
                PathNode.QuadTo(1187.0f, 82.0f, 1187.0f, 179.0f),
                PathNode.VerticalTo(524.0f),
                PathNode.QuadTo(1187.0f, 621.0f, 1183.0f, 663.5f),
                PathNode.QuadTo(1179.0f, 706.0f, 1163.0f, 736.0f),
                PathNode.QuadTo(1134.0f, 793.0f, 1078.0f, 821.0f),
                PathNode.QuadTo(1047.0f, 837.0f, 1004.5f, 841.0f),
                PathNode.QuadTo(962.0f, 845.0f, 865.0f, 845.0f),
                PathNode.HorizontalTo(688.0f),
                PathNode.QuadTo(591.0f, 845.0f, 548.5f, 841.0f),
                PathNode.QuadTo(506.0f, 837.0f, 475.0f, 821.0f),
                PathNode.QuadTo(419.0f, 793.0f, 390.0f, 736.0f),
                PathNode.QuadTo(374.0f, 706.0f, 370.0f, 669.0f),
                PathNode.QuadTo(366.0f, 632.0f, 366.0f, 564.0f),
                PathNode.VerticalTo(547.0f),
                PathNode.QuadTo(366.0f, 529.0f, 378.5f, 517.5f),
                PathNode.QuadTo(391.0f, 506.0f, 409.0f, 506.0f),
                PathNode.HorizontalTo(438.0f),
                PathNode.QuadTo(456.0f, 506.0f, 468.0f, 517.5f),
                PathNode.QuadTo(480.0f, 529.0f, 480.0f, 547.0f),
                PathNode.VerticalTo(594.0f),
                PathNode.QuadTo(480.0f, 630.0f, 481.5f, 648.5f),
                PathNode.QuadTo(483.0f, 667.0f, 490.0f, 682.0f),
                PathNode.QuadTo(506.0f, 707.0f, 530.0f, 721.0f),
                PathNode.QuadTo(545.0f, 729.0f, 566.5f, 730.5f),
                PathNode.QuadTo(588.0f, 732.0f, 643.0f, 732.0f),
                PathNode.HorizontalTo(910.0f),
                PathNode.QuadTo(965.0f, 732.0f, 986.5f, 730.5f),
                PathNode.QuadTo(1008.0f, 729.0f, 1023.0f, 721.0f),
                PathNode.QuadTo(1048.0f, 708.0f, 1063.0f, 682.0f),
                PathNode.QuadTo(1070.0f, 668.0f, 1071.5f, 646.0f),
                PathNode.QuadTo(1073.0f, 624.0f, 1073.0f, 569.0f),
                PathNode.VerticalTo(133.0f),
                PathNode.QuadTo(1073.0f, 79.0f, 1071.5f, 56.5f),
                PathNode.QuadTo(1070.0f, 34.0f, 1063.0f, 20.0f),
                PathNode.QuadTo(1048.0f, -6.0f, 1023.0f, -19.0f),
                PathNode.QuadTo(1008.0f, -27.0f, 986.5f, -28.5f),
                PathNode.QuadTo(965.0f, -30.0f, 910.0f, -30.0f),
                PathNode.HorizontalTo(643.0f),
                PathNode.QuadTo(588.0f, -30.0f, 566.5f, -28.5f),
                PathNode.QuadTo(545.0f, -27.0f, 530.0f, -19.0f),
                PathNode.QuadTo(506.0f, -5.0f, 490.0f, 20.0f),
                PathNode.QuadTo(483.0f, 35.0f, 481.5f, 54.0f),
                PathNode.QuadTo(480.0f, 73.0f, 480.0f, 108.0f),
                PathNode.VerticalTo(156.0f),
                PathNode.QuadTo(480.0f, 174.0f, 469.0f, 185.5f),
                PathNode.QuadTo(458.0f, 197.0f, 437.0f, 197.0f),
                PathNode.HorizontalTo(408.0f),
                PathNode.QuadTo(389.0f, 197.0f, 377.5f, 185.0f),
                PathNode.QuadTo(366.0f, 173.0f, 366.0f, 156.0f),
                PathNode.VerticalTo(141.0f),
                PathNode.QuadTo(366.0f, 73.0f, 370.0f, 34.5f),
                PathNode.QuadTo(374.0f, -4.0f, 390.0f, -34.0f),
                PathNode.QuadTo(418.0f, -89.0f, 475.0f, -119.0f),
                PathNode.QuadTo(506.0f, -135.0f, 549.0f, -139.0f),
                PathNode.QuadTo(592.0f, -143.0f, 690.0f, -143.0f),
                PathNode.HorizontalTo(865.0f),
                PathNode.QuadTo(962.0f, -143.0f, 1004.5f, -139.0f),
                PathNode.QuadTo(1047.0f, -135.0f, 1078.0f, -119.0f),
                PathNode.Close,
                PathNode.MoveTo(921.0f, 393.0f),
                PathNode.LineTo(714.0f, 602.0f),
                PathNode.QuadTo(702.0f, 614.0f, 686.0f, 615.5f),
                PathNode.QuadTo(670.0f, 617.0f, 656.0f, 603.0f),
                PathNode.LineTo(635.0f, 582.0f),
                PathNode.QuadTo(621.0f, 568.0f, 621.0f, 552.0f),
                PathNode.QuadTo(621.0f, 536.0f, 632.0f, 524.0f),
                PathNode.LineTo(748.0f, 408.0f),
                PathNode.HorizontalTo(185.0f),
                PathNode.QuadTo(169.0f, 408.0f, 156.5f, 396.0f),
                PathNode.QuadTo(144.0f, 384.0f, 144.0f, 367.0f),
                PathNode.VerticalTo(335.0f),
                PathNode.QuadTo(144.0f, 319.0f, 156.0f, 307.0f),
                PathNode.QuadTo(168.0f, 295.0f, 185.0f, 295.0f),
                PathNode.HorizontalTo(749.0f),
                PathNode.LineTo(636.0f, 181.0f),
                PathNode.QuadTo(623.0f, 168.0f, 621.5f, 152.5f),
                PathNode.QuadTo(620.0f, 137.0f, 634.0f, 122.0f),
                PathNode.LineTo(651.0f, 105.0f),
                PathNode.QuadTo(666.0f, 88.0f, 684.5f, 88.0f),
                PathNode.QuadTo(703.0f, 88.0f, 715.0f, 100.0f),
                PathNode.LineTo(920.0f, 308.0f),
                PathNode.QuadTo(940.0f, 328.0f, 940.5f, 350.5f),
                PathNode.QuadTo(941.0f, 373.0f, 921.0f, 393.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _importHeavy!!
    }

private var _importHeavy: ImageVector? = null
