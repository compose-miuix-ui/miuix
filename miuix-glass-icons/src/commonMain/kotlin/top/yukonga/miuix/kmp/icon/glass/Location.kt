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

val MiuixIcons.Glass.Location: ImageVector
    get() = MiuixIcons.Glass.Regular.Location

val MiuixIcons.Glass.Light.Location: ImageVector
    get() {
        if (_locationLight != null) return _locationLight!!
        _locationLight = ImageVector.Builder(
            name = "Location.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(606.1f, 237.3f),
                        PathNode.QuadTo(674.1f, 321.8f, 752.8f, 439.5f),
                        PathNode.QuadTo(831.5f, 557.2f, 870.5f, 648.2f),
                        PathNode.QuadTo(901.7f, 720.4f, 901.7f, 786.7f),
                        PathNode.QuadTo(901.7f, 874.0f, 858.5f, 948.1f),
                        PathNode.QuadTo(815.3f, 1022.2f, 741.5f, 1065.6f),
                        PathNode.QuadTo(667.7f, 1109.0f, 580.3f, 1109.0f),
                        PathNode.QuadTo(492.0f, 1109.0f, 417.9f, 1065.6f),
                        PathNode.QuadTo(343.9f, 1022.2f, 300.4f, 948.4f),
                        PathNode.QuadTo(257.0f, 874.6f, 257.0f, 786.7f),
                        PathNode.QuadTo(257.0f, 720.8f, 289.1f, 645.7f),
                        PathNode.QuadTo(326.1f, 557.7f, 404.5f, 443.0f),
                        PathNode.QuadTo(483.0f, 328.3f, 556.0f, 237.3f),
                        PathNode.QuadTo(562.3f, 228.8f, 571.8f, 225.7f),
                        PathNode.QuadTo(581.3f, 222.6f, 590.5f, 225.7f),
                        PathNode.QuadTo(599.8f, 228.8f, 606.1f, 237.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1051.8f, 202.8f),
                        PathNode.QuadTo(1051.8f, 250.2f, 983.1f, 290.0f),
                        PathNode.QuadTo(914.4f, 329.9f, 786.8f, 356.2f),
                        PathNode.QuadTo(777.7f, 358.3f, 772.1f, 356.2f),
                        PathNode.QuadTo(766.5f, 354.0f, 762.8f, 347.7f),
                        PathNode.LineTo(747.1f, 324.0f),
                        PathNode.QuadTo(742.9f, 317.3f, 744.7f, 312.8f),
                        PathNode.QuadTo(746.5f, 308.3f, 756.1f, 306.7f),
                        PathNode.QuadTo(860.3f, 289.3f, 920.5f, 261.9f),
                        PathNode.QuadTo(980.7f, 234.6f, 980.7f, 202.8f),
                        PathNode.QuadTo(980.7f, 172.3f, 925.5f, 146.4f),
                        PathNode.QuadTo(870.4f, 120.6f, 776.8f, 105.4f),
                        PathNode.QuadTo(683.2f, 90.2f, 573.3f, 90.2f),
                        PathNode.QuadTo(463.0f, 90.2f, 368.9f, 105.2f),
                        PathNode.QuadTo(274.8f, 120.2f, 219.4f, 146.2f),
                        PathNode.QuadTo(164.1f, 172.3f, 164.1f, 202.8f),
                        PathNode.QuadTo(164.1f, 235.7f, 227.5f, 263.0f),
                        PathNode.QuadTo(291.0f, 290.4f, 406.5f, 306.9f),
                        PathNode.QuadTo(415.5f, 307.9f, 416.5f, 314.0f),
                        PathNode.QuadTo(417.5f, 320.2f, 411.1f, 329.2f),
                        PathNode.LineTo(396.7f, 347.9f),
                        PathNode.QuadTo(392.3f, 354.4f, 387.3f, 356.2f),
                        PathNode.QuadTo(382.3f, 357.9f, 371.0f, 355.8f),
                        PathNode.QuadTo(237.7f, 332.9f, 164.4f, 292.3f),
                        PathNode.QuadTo(91.1f, 251.7f, 91.1f, 202.8f),
                        PathNode.QuadTo(91.1f, 156.9f, 154.9f, 118.3f),
                        PathNode.QuadTo(218.8f, 79.7f, 329.4f, 56.8f),
                        PathNode.QuadTo(440.1f, 33.8f, 573.3f, 33.8f),
                        PathNode.QuadTo(706.0f, 33.8f, 815.7f, 56.8f),
                        PathNode.QuadTo(925.4f, 79.7f, 988.6f, 118.3f),
                        PathNode.QuadTo(1051.8f, 156.9f, 1051.8f, 202.8f),
                        PathNode.Close,
                        PathNode.MoveTo(465.1f, 783.7f),
                        PathNode.QuadTo(465.1f, 814.9f, 480.7f, 841.3f),
                        PathNode.QuadTo(496.3f, 867.7f, 522.7f, 883.3f),
                        PathNode.QuadTo(549.1f, 898.9f, 580.3f, 898.9f),
                        PathNode.QuadTo(611.0f, 898.9f, 637.4f, 883.3f),
                        PathNode.QuadTo(663.9f, 867.7f, 679.7f, 841.0f),
                        PathNode.QuadTo(695.5f, 814.4f, 695.5f, 783.7f),
                        PathNode.QuadTo(695.5f, 752.5f, 679.9f, 726.1f),
                        PathNode.QuadTo(664.3f, 699.7f, 637.9f, 684.1f),
                        PathNode.QuadTo(611.5f, 668.5f, 580.3f, 668.5f),
                        PathNode.QuadTo(549.1f, 668.5f, 522.4f, 683.8f),
                        PathNode.QuadTo(495.8f, 699.2f, 480.4f, 725.8f),
                        PathNode.QuadTo(465.1f, 752.5f, 465.1f, 783.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationLight!!
    }

private var _locationLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Location: ImageVector
    get() {
        if (_locationNormal != null) return _locationNormal!!
        _locationNormal = ImageVector.Builder(
            name = "Location.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(612.5f, 233.6f),
                        PathNode.QuadTo(680.5f, 318.5f, 759.1f, 435.7f),
                        PathNode.QuadTo(837.7f, 552.9f, 876.7f, 643.9f),
                        PathNode.QuadTo(908.4f, 717.3f, 908.4f, 785.5f),
                        PathNode.QuadTo(908.4f, 874.7f, 864.3f, 950.4f),
                        PathNode.QuadTo(820.2f, 1026.1f, 744.5f, 1070.6f),
                        PathNode.QuadTo(668.9f, 1115.2f, 579.7f, 1115.2f),
                        PathNode.QuadTo(489.5f, 1115.2f, 413.8f, 1070.6f),
                        PathNode.QuadTo(338.1f, 1026.1f, 293.6f, 950.8f),
                        PathNode.QuadTo(249.0f, 875.5f, 249.0f, 785.5f),
                        PathNode.QuadTo(249.0f, 717.5f, 281.8f, 641.7f),
                        PathNode.QuadTo(318.8f, 553.7f, 396.9f, 439.2f),
                        PathNode.QuadTo(475.0f, 324.6f, 548.0f, 233.6f),
                        PathNode.QuadTo(556.2f, 222.7f, 568.5f, 219.0f),
                        PathNode.QuadTo(580.7f, 215.3f, 592.5f, 219.0f),
                        PathNode.QuadTo(604.3f, 222.7f, 612.5f, 233.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1061.8f, 205.0f),
                        PathNode.QuadTo(1061.8f, 254.1f, 990.9f, 296.2f),
                        PathNode.QuadTo(919.9f, 338.2f, 796.8f, 362.9f),
                        PathNode.QuadTo(784.4f, 365.6f, 777.1f, 362.9f),
                        PathNode.QuadTo(769.9f, 360.2f, 764.6f, 352.0f),
                        PathNode.LineTo(742.5f, 319.2f),
                        PathNode.QuadTo(737.1f, 310.8f, 739.4f, 305.0f),
                        PathNode.QuadTo(741.7f, 299.1f, 754.3f, 297.2f),
                        PathNode.QuadTo(851.5f, 282.8f, 908.4f, 257.8f),
                        PathNode.QuadTo(965.4f, 232.8f, 965.4f, 205.0f),
                        PathNode.QuadTo(965.4f, 176.8f, 912.4f, 153.3f),
                        PathNode.QuadTo(859.4f, 129.8f, 769.3f, 116.1f),
                        PathNode.QuadTo(679.2f, 102.4f, 572.7f, 102.4f),
                        PathNode.QuadTo(466.0f, 102.4f, 375.4f, 116.0f),
                        PathNode.QuadTo(284.8f, 129.6f, 231.8f, 153.2f),
                        PathNode.QuadTo(178.8f, 176.8f, 178.8f, 205.0f),
                        PathNode.QuadTo(178.8f, 234.5f, 239.1f, 259.5f),
                        PathNode.QuadTo(299.5f, 284.6f, 407.2f, 298.7f),
                        PathNode.QuadTo(418.9f, 299.7f, 419.9f, 307.8f),
                        PathNode.QuadTo(420.9f, 315.9f, 412.0f, 327.6f),
                        PathNode.LineTo(392.4f, 353.4f),
                        PathNode.QuadTo(386.2f, 362.3f, 379.8f, 364.2f),
                        PathNode.QuadTo(373.5f, 366.2f, 357.5f, 363.5f),
                        PathNode.QuadTo(230.6f, 341.2f, 155.8f, 298.6f),
                        PathNode.QuadTo(81.0f, 256.0f, 81.0f, 205.0f),
                        PathNode.QuadTo(81.0f, 156.9f, 146.7f, 116.3f),
                        PathNode.QuadTo(212.3f, 75.7f, 325.3f, 51.7f),
                        PathNode.QuadTo(438.3f, 27.7f, 572.7f, 27.7f),
                        PathNode.QuadTo(706.3f, 27.7f, 818.6f, 51.7f),
                        PathNode.QuadTo(930.9f, 75.7f, 996.4f, 116.3f),
                        PathNode.QuadTo(1061.8f, 156.9f, 1061.8f, 205.0f),
                        PathNode.Close,
                        PathNode.MoveTo(460.5f, 782.5f),
                        PathNode.QuadTo(460.5f, 814.9f, 476.7f, 842.1f),
                        PathNode.QuadTo(493.0f, 869.2f, 520.1f, 885.5f),
                        PathNode.QuadTo(547.3f, 901.7f, 579.7f, 901.7f),
                        PathNode.QuadTo(611.3f, 901.7f, 638.8f, 885.5f),
                        PathNode.QuadTo(666.3f, 869.2f, 682.6f, 841.7f),
                        PathNode.QuadTo(698.9f, 814.1f, 698.9f, 782.5f),
                        PathNode.QuadTo(698.9f, 750.0f, 682.7f, 722.9f),
                        PathNode.QuadTo(666.5f, 695.7f, 639.3f, 679.5f),
                        PathNode.QuadTo(612.2f, 663.3f, 579.7f, 663.3f),
                        PathNode.QuadTo(547.3f, 663.3f, 519.7f, 679.1f),
                        PathNode.QuadTo(492.1f, 694.9f, 476.3f, 722.5f),
                        PathNode.QuadTo(460.5f, 750.0f, 460.5f, 782.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationNormal!!
    }

private var _locationNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Location: ImageVector
    get() {
        if (_locationRegular != null) return _locationRegular!!
        _locationRegular = ImageVector.Builder(
            name = "Location.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(615.4f, 231.9f),
                        PathNode.QuadTo(683.4f, 316.9f, 761.9f, 433.9f),
                        PathNode.QuadTo(840.4f, 550.9f, 879.4f, 641.9f),
                        PathNode.QuadTo(911.4f, 715.9f, 911.4f, 784.9f),
                        PathNode.QuadTo(911.4f, 874.9f, 866.9f, 951.4f),
                        PathNode.QuadTo(822.4f, 1027.9f, 745.9f, 1072.9f),
                        PathNode.QuadTo(669.4f, 1117.9f, 579.4f, 1117.9f),
                        PathNode.QuadTo(488.4f, 1117.9f, 411.9f, 1072.9f),
                        PathNode.QuadTo(335.4f, 1027.9f, 290.4f, 951.9f),
                        PathNode.QuadTo(245.4f, 875.9f, 245.4f, 784.9f),
                        PathNode.QuadTo(245.4f, 715.9f, 278.4f, 639.9f),
                        PathNode.QuadTo(315.4f, 551.9f, 393.4f, 437.4f),
                        PathNode.QuadTo(471.4f, 322.9f, 544.4f, 231.9f),
                        PathNode.QuadTo(553.4f, 219.9f, 566.9f, 215.9f),
                        PathNode.QuadTo(580.4f, 211.9f, 593.4f, 215.9f),
                        PathNode.QuadTo(606.4f, 219.9f, 615.4f, 231.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1066.4f, 205.9f),
                        PathNode.QuadTo(1066.4f, 255.9f, 994.4f, 298.9f),
                        PathNode.QuadTo(922.4f, 341.9f, 801.4f, 365.9f),
                        PathNode.QuadTo(787.4f, 368.9f, 779.4f, 365.9f),
                        PathNode.QuadTo(771.4f, 362.9f, 765.4f, 353.9f),
                        PathNode.LineTo(740.4f, 316.9f),
                        PathNode.QuadTo(734.4f, 307.9f, 736.9f, 301.4f),
                        PathNode.QuadTo(739.4f, 294.9f, 753.4f, 292.9f),
                        PathNode.QuadTo(847.4f, 279.9f, 902.9f, 255.9f),
                        PathNode.QuadTo(958.4f, 231.9f, 958.4f, 205.9f),
                        PathNode.QuadTo(958.4f, 178.9f, 906.4f, 156.4f),
                        PathNode.QuadTo(854.4f, 133.9f, 765.9f, 120.9f),
                        PathNode.QuadTo(677.4f, 107.9f, 572.4f, 107.9f),
                        PathNode.QuadTo(467.4f, 107.9f, 378.4f, 120.9f),
                        PathNode.QuadTo(289.4f, 133.9f, 237.4f, 156.4f),
                        PathNode.QuadTo(185.4f, 178.9f, 185.4f, 205.9f),
                        PathNode.QuadTo(185.4f, 233.9f, 244.4f, 257.9f),
                        PathNode.QuadTo(303.4f, 281.9f, 407.4f, 294.9f),
                        PathNode.QuadTo(420.4f, 295.9f, 421.4f, 304.9f),
                        PathNode.QuadTo(422.4f, 313.9f, 412.4f, 326.9f),
                        PathNode.LineTo(390.4f, 355.9f),
                        PathNode.QuadTo(383.4f, 365.9f, 376.4f, 367.9f),
                        PathNode.QuadTo(369.4f, 369.9f, 351.4f, 366.9f),
                        PathNode.QuadTo(227.4f, 344.9f, 151.9f, 301.4f),
                        PathNode.QuadTo(76.4f, 257.9f, 76.4f, 205.9f),
                        PathNode.QuadTo(76.4f, 156.9f, 142.9f, 115.4f),
                        PathNode.QuadTo(209.4f, 73.9f, 323.4f, 49.4f),
                        PathNode.QuadTo(437.4f, 24.9f, 572.4f, 24.9f),
                        PathNode.QuadTo(706.4f, 24.9f, 819.9f, 49.4f),
                        PathNode.QuadTo(933.4f, 73.9f, 999.9f, 115.4f),
                        PathNode.QuadTo(1066.4f, 156.9f, 1066.4f, 205.9f),
                        PathNode.Close,
                        PathNode.MoveTo(458.4f, 781.9f),
                        PathNode.QuadTo(458.4f, 814.9f, 474.9f, 842.4f),
                        PathNode.QuadTo(491.4f, 869.9f, 518.9f, 886.4f),
                        PathNode.QuadTo(546.4f, 902.9f, 579.4f, 902.9f),
                        PathNode.QuadTo(611.4f, 902.9f, 639.4f, 886.4f),
                        PathNode.QuadTo(667.4f, 869.9f, 683.9f, 841.9f),
                        PathNode.QuadTo(700.4f, 813.9f, 700.4f, 781.9f),
                        PathNode.QuadTo(700.4f, 748.9f, 683.9f, 721.4f),
                        PathNode.QuadTo(667.4f, 693.9f, 639.9f, 677.4f),
                        PathNode.QuadTo(612.4f, 660.9f, 579.4f, 660.9f),
                        PathNode.QuadTo(546.4f, 660.9f, 518.4f, 676.9f),
                        PathNode.QuadTo(490.4f, 692.9f, 474.4f, 720.9f),
                        PathNode.QuadTo(458.4f, 748.9f, 458.4f, 781.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationRegular!!
    }

private var _locationRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Location: ImageVector
    get() {
        if (_locationMedium != null) return _locationMedium!!
        _locationMedium = ImageVector.Builder(
            name = "Location.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(621.9f, 227.1f),
                        PathNode.QuadTo(688.3f, 304.5f, 766.9f, 423.4f),
                        PathNode.QuadTo(845.6f, 542.3f, 887.0f, 638.7f),
                        PathNode.QuadTo(919.5f, 713.8f, 919.5f, 784.9f),
                        PathNode.QuadTo(919.5f, 877.1f, 873.8f, 955.5f),
                        PathNode.QuadTo(828.1f, 1033.9f, 749.8f, 1080.0f),
                        PathNode.QuadTo(671.6f, 1126.0f, 579.4f, 1126.0f),
                        PathNode.QuadTo(486.3f, 1126.0f, 407.9f, 1080.0f),
                        PathNode.QuadTo(329.5f, 1033.9f, 283.4f, 955.8f),
                        PathNode.QuadTo(237.3f, 877.8f, 237.3f, 784.9f),
                        PathNode.QuadTo(237.3f, 715.1f, 270.9f, 636.7f),
                        PathNode.QuadTo(308.1f, 548.1f, 386.5f, 433.1f),
                        PathNode.QuadTo(464.9f, 318.1f, 537.9f, 227.1f),
                        PathNode.QuadTo(548.6f, 212.9f, 564.5f, 208.1f),
                        PathNode.QuadTo(580.4f, 203.3f, 595.9f, 208.1f),
                        PathNode.QuadTo(611.3f, 212.9f, 621.9f, 227.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1074.5f, 205.9f),
                        PathNode.QuadTo(1074.5f, 258.9f, 1005.5f, 302.3f),
                        PathNode.QuadTo(936.5f, 345.7f, 827.4f, 368.6f),
                        PathNode.QuadTo(810.9f, 372.4f, 800.5f, 368.6f),
                        PathNode.QuadTo(790.1f, 364.8f, 783.0f, 353.7f),
                        PathNode.LineTo(758.0f, 315.6f),
                        PathNode.QuadTo(749.8f, 303.1f, 754.0f, 293.3f),
                        PathNode.QuadTo(758.1f, 283.6f, 776.7f, 280.2f),
                        PathNode.QuadTo(852.0f, 269.4f, 901.2f, 248.0f),
                        PathNode.QuadTo(950.3f, 226.5f, 950.3f, 205.9f),
                        PathNode.QuadTo(950.3f, 183.5f, 899.4f, 162.6f),
                        PathNode.QuadTo(848.5f, 141.8f, 761.9f, 128.9f),
                        PathNode.QuadTo(675.3f, 116.0f, 572.4f, 116.0f),
                        PathNode.QuadTo(469.6f, 116.0f, 382.5f, 128.9f),
                        PathNode.QuadTo(295.4f, 141.8f, 244.5f, 162.6f),
                        PathNode.QuadTo(193.5f, 183.5f, 193.5f, 205.9f),
                        PathNode.QuadTo(193.5f, 228.3f, 245.8f, 249.8f),
                        PathNode.QuadTo(298.0f, 271.4f, 385.5f, 283.3f),
                        PathNode.QuadTo(403.1f, 285.7f, 406.4f, 297.1f),
                        PathNode.QuadTo(409.7f, 308.5f, 397.0f, 325.6f),
                        PathNode.LineTo(373.9f, 356.5f),
                        PathNode.QuadTo(365.3f, 368.6f, 356.4f, 371.7f),
                        PathNode.QuadTo(347.5f, 374.8f, 327.1f, 370.7f),
                        PathNode.QuadTo(214.2f, 349.0f, 141.3f, 304.9f),
                        PathNode.QuadTo(68.3f, 260.9f, 68.3f, 205.9f),
                        PathNode.QuadTo(68.3f, 153.4f, 136.2f, 110.2f),
                        PathNode.QuadTo(204.0f, 66.9f, 319.8f, 41.9f),
                        PathNode.QuadTo(435.5f, 16.8f, 572.4f, 16.8f),
                        PathNode.QuadTo(708.3f, 16.8f, 823.7f, 41.9f),
                        PathNode.QuadTo(939.1f, 66.9f, 1006.8f, 110.2f),
                        PathNode.QuadTo(1074.5f, 153.4f, 1074.5f, 205.9f),
                        PathNode.Close,
                        PathNode.MoveTo(452.5f, 781.9f),
                        PathNode.QuadTo(452.5f, 816.3f, 469.8f, 845.3f),
                        PathNode.QuadTo(487.1f, 874.3f, 516.0f, 891.6f),
                        PathNode.QuadTo(544.8f, 908.9f, 579.4f, 908.9f),
                        PathNode.QuadTo(613.1f, 908.9f, 642.4f, 891.6f),
                        PathNode.QuadTo(671.8f, 874.3f, 689.1f, 844.9f),
                        PathNode.QuadTo(706.4f, 815.6f, 706.4f, 781.9f),
                        PathNode.QuadTo(706.4f, 747.3f, 689.1f, 718.5f),
                        PathNode.QuadTo(671.8f, 689.6f, 642.8f, 672.3f),
                        PathNode.QuadTo(613.8f, 655.0f, 579.4f, 655.0f),
                        PathNode.QuadTo(544.8f, 655.0f, 515.5f, 671.8f),
                        PathNode.QuadTo(486.1f, 688.6f, 469.3f, 718.0f),
                        PathNode.QuadTo(452.5f, 747.3f, 452.5f, 781.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationMedium!!
    }

private var _locationMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Location: ImageVector
    get() {
        if (_locationDemibold != null) return _locationDemibold!!
        _locationDemibold = ImageVector.Builder(
            name = "Location.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(626.5f, 223.7f),
                        PathNode.QuadTo(691.7f, 295.8f, 770.4f, 416.0f),
                        PathNode.QuadTo(849.2f, 536.2f, 892.3f, 636.4f),
                        PathNode.QuadTo(925.2f, 712.3f, 925.2f, 784.9f),
                        PathNode.QuadTo(925.2f, 878.6f, 878.6f, 958.3f),
                        PathNode.QuadTo(832.1f, 1038.0f, 752.6f, 1084.9f),
                        PathNode.QuadTo(673.1f, 1131.7f, 579.4f, 1131.7f),
                        PathNode.QuadTo(484.8f, 1131.7f, 405.0f, 1084.9f),
                        PathNode.QuadTo(325.3f, 1038.0f, 278.5f, 958.6f),
                        PathNode.QuadTo(231.6f, 879.1f, 231.6f, 784.9f),
                        PathNode.QuadTo(231.6f, 714.6f, 265.6f, 634.4f),
                        PathNode.QuadTo(303.0f, 545.5f, 381.7f, 430.1f),
                        PathNode.QuadTo(460.4f, 314.7f, 533.4f, 223.7f),
                        PathNode.QuadTo(545.2f, 208.0f, 562.8f, 202.6f),
                        PathNode.QuadTo(580.4f, 197.2f, 597.6f, 202.6f),
                        PathNode.QuadTo(614.7f, 208.0f, 626.5f, 223.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1080.2f, 205.9f),
                        PathNode.QuadTo(1080.2f, 261.0f, 1013.3f, 304.7f),
                        PathNode.QuadTo(946.3f, 348.4f, 845.5f, 370.5f),
                        PathNode.QuadTo(827.4f, 374.9f, 815.3f, 370.5f),
                        PathNode.QuadTo(803.1f, 366.1f, 795.3f, 353.5f),
                        PathNode.LineTo(770.3f, 314.6f),
                        PathNode.QuadTo(760.6f, 299.7f, 765.9f, 287.6f),
                        PathNode.QuadTo(771.1f, 275.6f, 792.9f, 271.3f),
                        PathNode.QuadTo(855.2f, 262.0f, 899.9f, 242.4f),
                        PathNode.QuadTo(944.6f, 222.7f, 944.6f, 205.9f),
                        PathNode.QuadTo(944.6f, 186.7f, 894.5f, 167.0f),
                        PathNode.QuadTo(844.3f, 147.3f, 759.0f, 134.5f),
                        PathNode.QuadTo(673.8f, 121.7f, 572.4f, 121.7f),
                        PathNode.QuadTo(471.1f, 121.7f, 385.3f, 134.5f),
                        PathNode.QuadTo(299.5f, 147.3f, 249.4f, 167.0f),
                        PathNode.QuadTo(199.2f, 186.7f, 199.2f, 205.9f),
                        PathNode.QuadTo(199.2f, 224.3f, 246.7f, 244.1f),
                        PathNode.QuadTo(294.2f, 264.0f, 370.2f, 275.2f),
                        PathNode.QuadTo(391.0f, 278.5f, 395.9f, 291.6f),
                        PathNode.QuadTo(400.8f, 304.7f, 386.2f, 324.6f),
                        PathNode.LineTo(362.4f, 356.8f),
                        PathNode.QuadTo(352.6f, 370.5f, 342.4f, 374.4f),
                        PathNode.QuadTo(332.2f, 378.2f, 310.1f, 373.4f),
                        PathNode.QuadTo(204.9f, 351.8f, 133.8f, 307.4f),
                        PathNode.QuadTo(62.6f, 263.0f, 62.6f, 205.9f),
                        PathNode.QuadTo(62.6f, 151.0f, 131.4f, 106.5f),
                        PathNode.QuadTo(200.2f, 62.0f, 317.2f, 36.6f),
                        PathNode.QuadTo(434.2f, 11.1f, 572.4f, 11.1f),
                        PathNode.QuadTo(709.6f, 11.1f, 826.4f, 36.6f),
                        PathNode.QuadTo(943.1f, 62.0f, 1011.6f, 106.5f),
                        PathNode.QuadTo(1080.2f, 151.0f, 1080.2f, 205.9f),
                        PathNode.Close,
                        PathNode.MoveTo(448.3f, 781.9f),
                        PathNode.QuadTo(448.3f, 817.2f, 466.2f, 847.3f),
                        PathNode.QuadTo(484.1f, 877.3f, 513.9f, 895.2f),
                        PathNode.QuadTo(543.7f, 913.0f, 579.4f, 913.0f),
                        PathNode.QuadTo(614.2f, 913.0f, 644.5f, 895.2f),
                        PathNode.QuadTo(674.8f, 877.3f, 692.7f, 847.0f),
                        PathNode.QuadTo(710.5f, 816.7f, 710.5f, 781.9f),
                        PathNode.QuadTo(710.5f, 746.2f, 692.7f, 716.4f),
                        PathNode.QuadTo(674.8f, 686.6f, 644.8f, 668.7f),
                        PathNode.QuadTo(614.7f, 650.8f, 579.4f, 650.8f),
                        PathNode.QuadTo(543.7f, 650.8f, 513.4f, 668.2f),
                        PathNode.QuadTo(483.1f, 685.6f, 465.7f, 715.9f),
                        PathNode.QuadTo(448.3f, 746.2f, 448.3f, 781.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationDemibold!!
    }

private var _locationDemibold: ImageVector? = null
