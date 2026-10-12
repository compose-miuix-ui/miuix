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

val MiuixIcons.Glass.Read: ImageVector
    get() = MiuixIcons.Glass.Regular.Read

val MiuixIcons.Glass.Light.Read: ImageVector
    get() {
        if (_readLight != null) return _readLight!!
        _readLight = ImageVector.Builder(
            name = "Read.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(562.3f, 452.1f),
                        PathNode.LineTo(802.4f, 715.8f),
                        PathNode.QuadTo(809.4f, 724.5f, 809.7f, 731.6f),
                        PathNode.QuadTo(810.0f, 738.6f, 803.1f, 744.9f),
                        PathNode.LineTo(787.2f, 759.6f),
                        PathNode.QuadTo(780.3f, 766.6f, 773.0f, 765.6f),
                        PathNode.QuadTo(765.7f, 764.5f, 758.1f, 756.1f),
                        PathNode.LineTo(544.8f, 520.0f),
                        PathNode.QuadTo(539.7f, 514.3f, 535.8f, 514.3f),
                        PathNode.QuadTo(532.0f, 514.3f, 526.9f, 520.0f),
                        PathNode.LineTo(408.4f, 656.2f),
                        PathNode.QuadTo(402.2f, 663.5f, 395.0f, 664.1f),
                        PathNode.QuadTo(387.9f, 664.6f, 381.1f, 658.4f),
                        PathNode.LineTo(365.4f, 644.5f),
                        PathNode.QuadTo(358.7f, 638.2f, 358.4f, 631.5f),
                        PathNode.QuadTo(358.1f, 624.9f, 363.4f, 618.5f),
                        PathNode.LineTo(508.9f, 451.5f),
                        PathNode.QuadTo(519.8f, 438.5f, 535.0f, 438.8f),
                        PathNode.QuadTo(550.3f, 439.1f, 562.3f, 452.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1084.2f, 622.8f),
                        PathNode.QuadTo(1084.2f, 741.3f, 1014.7f, 837.2f),
                        PathNode.QuadTo(945.2f, 933.1f, 827.5f, 987.8f),
                        PathNode.QuadTo(709.9f, 1042.5f, 571.3f, 1042.5f),
                        PathNode.QuadTo(442.5f, 1042.5f, 324.1f, 990.3f),
                        PathNode.QuadTo(205.7f, 938.1f, 132.2f, 842.2f),
                        PathNode.QuadTo(58.7f, 746.3f, 58.7f, 622.9f),
                        PathNode.QuadTo(58.7f, 536.1f, 97.6f, 461.4f),
                        PathNode.QuadTo(136.5f, 386.6f, 203.5f, 330.6f),
                        PathNode.QuadTo(270.5f, 274.6f, 353.3f, 242.1f),
                        PathNode.QuadTo(365.7f, 238.5f, 371.8f, 226.8f),
                        PathNode.QuadTo(378.0f, 215.1f, 373.8f, 201.8f),
                        PathNode.LineTo(344.7f, 110.0f),
                        PathNode.QuadTo(343.7f, 107.0f, 344.9f, 104.6f),
                        PathNode.QuadTo(346.1f, 102.3f, 348.6f, 101.0f),
                        PathNode.QuadTo(351.1f, 99.8f, 354.4f, 100.8f),
                        PathNode.LineTo(676.9f, 193.2f),
                        PathNode.QuadTo(863.8f, 246.8f, 974.0f, 348.2f),
                        PathNode.QuadTo(1084.2f, 449.5f, 1084.2f, 622.8f),
                        PathNode.Close,
                        PathNode.MoveTo(375.1f, 300.4f),
                        PathNode.QuadTo(305.4f, 326.7f, 247.7f, 373.9f),
                        PathNode.QuadTo(190.0f, 421.2f, 155.6f, 485.1f),
                        PathNode.QuadTo(121.2f, 549.1f, 121.2f, 623.0f),
                        PathNode.QuadTo(121.2f, 729.1f, 186.2f, 810.8f),
                        PathNode.QuadTo(251.2f, 892.4f, 355.0f, 936.7f),
                        PathNode.QuadTo(458.8f, 981.0f, 571.4f, 981.0f),
                        PathNode.QuadTo(699.4f, 981.0f, 802.2f, 931.6f),
                        PathNode.QuadTo(904.9f, 882.3f, 963.3f, 800.3f),
                        PathNode.QuadTo(1021.7f, 718.2f, 1021.7f, 622.8f),
                        PathNode.QuadTo(1021.7f, 518.2f, 974.5f, 445.8f),
                        PathNode.QuadTo(927.4f, 373.3f, 848.3f, 328.6f),
                        PathNode.QuadTo(769.2f, 283.9f, 658.9f, 252.1f),
                        PathNode.LineTo(431.0f, 185.4f),
                        PathNode.QuadTo(441.9f, 224.4f, 426.4f, 255.7f),
                        PathNode.QuadTo(410.9f, 287.0f, 375.1f, 300.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _readLight!!
    }

private var _readLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Read: ImageVector
    get() {
        if (_readNormal != null) return _readNormal!!
        _readNormal = ImageVector.Builder(
            name = "Read.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(570.0f, 447.6f),
                        PathNode.LineTo(805.2f, 706.7f),
                        PathNode.QuadTo(814.9f, 718.4f, 815.3f, 727.8f),
                        PathNode.QuadTo(815.8f, 737.3f, 806.8f, 745.4f),
                        PathNode.LineTo(786.0f, 764.4f),
                        PathNode.QuadTo(777.0f, 773.5f, 767.5f, 772.1f),
                        PathNode.QuadTo(758.0f, 770.8f, 748.0f, 759.9f),
                        PathNode.LineTo(546.6f, 536.9f),
                        PathNode.QuadTo(540.9f, 530.3f, 536.6f, 530.3f),
                        PathNode.QuadTo(532.3f, 530.3f, 526.6f, 536.9f),
                        PathNode.LineTo(419.4f, 660.6f),
                        PathNode.QuadTo(412.0f, 669.8f, 402.5f, 670.6f),
                        PathNode.QuadTo(393.1f, 671.5f, 384.1f, 663.4f),
                        PathNode.LineTo(363.4f, 645.3f),
                        PathNode.QuadTo(354.4f, 637.1f, 353.9f, 628.2f),
                        PathNode.QuadTo(353.5f, 619.2f, 360.7f, 611.0f),
                        PathNode.LineTo(503.1f, 446.8f),
                        PathNode.QuadTo(516.1f, 431.0f, 535.7f, 431.5f),
                        PathNode.QuadTo(555.2f, 431.9f, 570.0f, 447.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1094.0f, 624.7f),
                        PathNode.QuadTo(1094.0f, 746.3f, 1023.1f, 844.4f),
                        PathNode.QuadTo(952.2f, 942.4f, 832.3f, 998.3f),
                        PathNode.QuadTo(712.3f, 1054.3f, 571.4f, 1054.3f),
                        PathNode.QuadTo(440.4f, 1054.3f, 319.5f, 1000.8f),
                        PathNode.QuadTo(198.6f, 947.4f, 123.7f, 849.4f),
                        PathNode.QuadTo(48.9f, 751.3f, 48.9f, 624.8f),
                        PathNode.QuadTo(48.9f, 535.8f, 88.9f, 459.1f),
                        PathNode.QuadTo(128.9f, 382.5f, 197.3f, 325.1f),
                        PathNode.QuadTo(265.7f, 267.8f, 350.0f, 234.9f),
                        PathNode.QuadTo(358.6f, 232.4f, 363.0f, 224.3f),
                        PathNode.QuadTo(367.3f, 216.1f, 364.6f, 206.5f),
                        PathNode.LineTo(332.1f, 99.4f),
                        PathNode.QuadTo(331.1f, 96.4f, 332.5f, 93.6f),
                        PathNode.QuadTo(334.0f, 90.8f, 336.8f, 89.4f),
                        PathNode.QuadTo(339.6f, 88.0f, 343.4f, 89.0f),
                        PathNode.LineTo(682.1f, 186.6f),
                        PathNode.QuadTo(871.1f, 241.2f, 982.5f, 344.4f),
                        PathNode.QuadTo(1094.0f, 447.5f, 1094.0f, 624.7f),
                        PathNode.Close,
                        PathNode.MoveTo(378.8f, 311.6f),
                        PathNode.QuadTo(310.9f, 337.0f, 254.7f, 382.7f),
                        PathNode.QuadTo(198.5f, 428.4f, 164.8f, 490.6f),
                        PathNode.QuadTo(131.0f, 552.9f, 131.0f, 624.8f),
                        PathNode.QuadTo(131.0f, 728.1f, 194.6f, 807.6f),
                        PathNode.QuadTo(258.2f, 887.1f, 359.8f, 930.1f),
                        PathNode.QuadTo(461.3f, 973.2f, 571.4f, 973.2f),
                        PathNode.QuadTo(696.7f, 973.2f, 797.2f, 925.1f),
                        PathNode.QuadTo(897.7f, 877.0f, 954.8f, 797.1f),
                        PathNode.QuadTo(1011.9f, 717.1f, 1011.9f, 624.7f),
                        PathNode.QuadTo(1011.9f, 522.6f, 965.5f, 451.9f),
                        PathNode.QuadTo(919.2f, 381.1f, 842.2f, 337.9f),
                        PathNode.QuadTo(765.2f, 294.7f, 658.6f, 264.1f),
                        PathNode.LineTo(445.0f, 202.1f),
                        PathNode.QuadTo(449.8f, 238.3f, 431.6f, 268.4f),
                        PathNode.QuadTo(413.3f, 298.4f, 378.8f, 311.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _readNormal!!
    }

private var _readNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Read: ImageVector
    get() {
        if (_readRegular != null) return _readRegular!!
        _readRegular = ImageVector.Builder(
            name = "Read.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(573.4f, 445.6f),
                        PathNode.LineTo(806.4f, 702.6f),
                        PathNode.QuadTo(817.4f, 715.6f, 817.9f, 726.1f),
                        PathNode.QuadTo(818.4f, 736.6f, 808.4f, 745.6f),
                        PathNode.LineTo(785.4f, 766.6f),
                        PathNode.QuadTo(775.4f, 776.6f, 764.9f, 775.1f),
                        PathNode.QuadTo(754.4f, 773.6f, 743.4f, 761.6f),
                        PathNode.LineTo(547.4f, 544.6f),
                        PathNode.QuadTo(541.4f, 537.6f, 536.9f, 537.6f),
                        PathNode.QuadTo(532.4f, 537.6f, 526.4f, 544.6f),
                        PathNode.LineTo(424.4f, 662.6f),
                        PathNode.QuadTo(416.4f, 672.6f, 405.9f, 673.6f),
                        PathNode.QuadTo(395.4f, 674.6f, 385.4f, 665.6f),
                        PathNode.LineTo(362.4f, 645.6f),
                        PathNode.QuadTo(352.4f, 636.6f, 351.9f, 626.6f),
                        PathNode.QuadTo(351.4f, 616.6f, 359.4f, 607.6f),
                        PathNode.LineTo(500.4f, 444.6f),
                        PathNode.QuadTo(514.4f, 427.6f, 535.9f, 428.1f),
                        PathNode.QuadTo(557.4f, 428.6f, 573.4f, 445.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1098.4f, 625.6f),
                        PathNode.QuadTo(1098.4f, 748.6f, 1026.9f, 847.6f),
                        PathNode.QuadTo(955.4f, 946.6f, 834.4f, 1003.1f),
                        PathNode.QuadTo(713.4f, 1059.6f, 571.4f, 1059.6f),
                        PathNode.QuadTo(439.4f, 1059.6f, 317.4f, 1005.6f),
                        PathNode.QuadTo(195.4f, 951.6f, 119.9f, 852.6f),
                        PathNode.QuadTo(44.4f, 753.6f, 44.4f, 625.6f),
                        PathNode.QuadTo(44.4f, 535.6f, 84.9f, 458.1f),
                        PathNode.QuadTo(125.4f, 380.6f, 194.4f, 322.6f),
                        PathNode.QuadTo(263.4f, 264.6f, 348.4f, 231.6f),
                        PathNode.QuadTo(355.4f, 229.6f, 358.9f, 223.1f),
                        PathNode.QuadTo(362.4f, 216.6f, 360.4f, 208.6f),
                        PathNode.LineTo(326.4f, 94.6f),
                        PathNode.QuadTo(325.4f, 91.6f, 326.9f, 88.6f),
                        PathNode.QuadTo(328.4f, 85.6f, 331.4f, 84.1f),
                        PathNode.QuadTo(334.4f, 82.6f, 338.4f, 83.6f),
                        PathNode.LineTo(684.4f, 183.6f),
                        PathNode.QuadTo(874.4f, 238.6f, 986.4f, 342.6f),
                        PathNode.QuadTo(1098.4f, 446.6f, 1098.4f, 625.6f),
                        PathNode.Close,
                        PathNode.MoveTo(380.4f, 316.6f),
                        PathNode.QuadTo(313.4f, 341.6f, 257.9f, 386.6f),
                        PathNode.QuadTo(202.4f, 431.6f, 168.9f, 493.1f),
                        PathNode.QuadTo(135.4f, 554.6f, 135.4f, 625.6f),
                        PathNode.QuadTo(135.4f, 727.6f, 198.4f, 806.1f),
                        PathNode.QuadTo(261.4f, 884.6f, 361.9f, 927.1f),
                        PathNode.QuadTo(462.4f, 969.6f, 571.4f, 969.6f),
                        PathNode.QuadTo(695.4f, 969.6f, 794.9f, 922.1f),
                        PathNode.QuadTo(894.4f, 874.6f, 950.9f, 795.6f),
                        PathNode.QuadTo(1007.4f, 716.6f, 1007.4f, 625.6f),
                        PathNode.QuadTo(1007.4f, 524.6f, 961.4f, 454.6f),
                        PathNode.QuadTo(915.4f, 384.6f, 839.4f, 342.1f),
                        PathNode.QuadTo(763.4f, 299.6f, 658.4f, 269.6f),
                        PathNode.LineTo(451.4f, 209.6f),
                        PathNode.QuadTo(453.4f, 244.6f, 433.9f, 274.1f),
                        PathNode.QuadTo(414.4f, 303.6f, 380.4f, 316.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _readRegular!!
    }

private var _readRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Read: ImageVector
    get() {
        if (_readMedium != null) return _readMedium!!
        _readMedium = ImageVector.Builder(
            name = "Read.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(577.5f, 441.1f),
                        PathNode.LineTo(810.8f, 698.1f),
                        PathNode.QuadTo(823.9f, 713.5f, 824.0f, 726.8f),
                        PathNode.QuadTo(824.1f, 740.2f, 812.2f, 750.8f),
                        PathNode.LineTo(789.2f, 771.5f),
                        PathNode.QuadTo(776.8f, 783.7f, 763.4f, 781.9f),
                        PathNode.QuadTo(750.1f, 780.1f, 737.5f, 766.3f),
                        PathNode.LineTo(544.7f, 552.8f),
                        PathNode.QuadTo(539.8f, 547.1f, 536.1f, 547.1f),
                        PathNode.QuadTo(532.4f, 547.1f, 527.5f, 552.8f),
                        PathNode.LineTo(428.8f, 667.0f),
                        PathNode.QuadTo(419.1f, 678.9f, 405.9f, 680.4f),
                        PathNode.QuadTo(392.7f, 682.0f, 380.3f, 670.8f),
                        PathNode.LineTo(357.3f, 650.8f),
                        PathNode.QuadTo(345.1f, 640.2f, 344.4f, 627.5f),
                        PathNode.QuadTo(343.6f, 614.8f, 353.5f, 603.3f),
                        PathNode.LineTo(494.5f, 440.3f),
                        PathNode.QuadTo(510.6f, 421.2f, 535.1f, 421.5f),
                        PathNode.QuadTo(559.6f, 421.9f, 577.5f, 441.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1106.5f, 625.7f),
                        PathNode.QuadTo(1106.5f, 751.1f, 1033.8f, 852.0f),
                        PathNode.QuadTo(961.1f, 952.9f, 838.2f, 1010.3f),
                        PathNode.QuadTo(715.3f, 1067.8f, 571.4f, 1067.8f),
                        PathNode.QuadTo(437.5f, 1067.8f, 313.8f, 1012.8f),
                        PathNode.QuadTo(190.0f, 957.9f, 113.2f, 857.1f),
                        PathNode.QuadTo(36.3f, 756.4f, 36.3f, 625.7f),
                        PathNode.QuadTo(36.3f, 534.6f, 76.7f, 456.3f),
                        PathNode.QuadTo(117.1f, 378.0f, 186.2f, 319.1f),
                        PathNode.QuadTo(255.3f, 260.3f, 341.1f, 226.0f),
                        PathNode.QuadTo(347.3f, 224.0f, 350.1f, 218.6f),
                        PathNode.QuadTo(353.0f, 213.1f, 351.0f, 205.7f),
                        PathNode.LineTo(318.6f, 96.8f),
                        PathNode.QuadTo(316.8f, 90.6f, 319.5f, 85.0f),
                        PathNode.QuadTo(322.2f, 79.4f, 327.9f, 76.7f),
                        PathNode.QuadTo(333.6f, 74.0f, 340.6f, 75.8f),
                        PathNode.LineTo(687.4f, 176.1f),
                        PathNode.QuadTo(879.3f, 231.6f, 992.9f, 337.8f),
                        PathNode.QuadTo(1106.5f, 444.0f, 1106.5f, 625.7f),
                        PathNode.Close,
                        PathNode.MoveTo(383.4f, 324.2f),
                        PathNode.QuadTo(318.0f, 348.7f, 263.6f, 392.6f),
                        PathNode.QuadTo(209.2f, 436.5f, 176.4f, 496.4f),
                        PathNode.QuadTo(143.5f, 556.3f, 143.5f, 625.7f),
                        PathNode.QuadTo(143.5f, 725.2f, 205.2f, 801.8f),
                        PathNode.QuadTo(266.8f, 878.4f, 365.6f, 920.0f),
                        PathNode.QuadTo(464.3f, 961.5f, 571.4f, 961.5f),
                        PathNode.QuadTo(693.3f, 961.5f, 791.0f, 915.1f),
                        PathNode.QuadTo(888.8f, 868.7f, 944.0f, 791.6f),
                        PathNode.QuadTo(999.3f, 714.5f, 999.3f, 625.7f),
                        PathNode.QuadTo(999.3f, 526.3f, 953.5f, 457.9f),
                        PathNode.QuadTo(907.6f, 389.5f, 831.9f, 347.8f),
                        PathNode.QuadTo(756.1f, 306.1f, 649.8f, 275.6f),
                        PathNode.LineTo(458.5f, 220.2f),
                        PathNode.QuadTo(457.8f, 254.7f, 437.3f, 283.1f),
                        PathNode.QuadTo(416.9f, 311.5f, 383.4f, 324.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _readMedium!!
    }

private var _readMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Read: ImageVector
    get() {
        if (_readDemibold != null) return _readDemibold!!
        _readDemibold = ImageVector.Builder(
            name = "Read.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(580.3f, 437.9f),
                        PathNode.LineTo(813.8f, 694.9f),
                        PathNode.QuadTo(828.5f, 712.0f, 828.3f, 727.3f),
                        PathNode.QuadTo(828.1f, 742.6f, 814.9f, 754.4f),
                        PathNode.LineTo(791.9f, 774.9f),
                        PathNode.QuadTo(777.7f, 788.6f, 762.4f, 786.7f),
                        PathNode.QuadTo(747.1f, 784.7f, 733.3f, 769.5f),
                        PathNode.LineTo(542.8f, 558.5f),
                        PathNode.QuadTo(538.7f, 553.8f, 535.6f, 553.8f),
                        PathNode.QuadTo(532.4f, 553.8f, 528.3f, 558.5f),
                        PathNode.LineTo(431.8f, 670.0f),
                        PathNode.QuadTo(421.0f, 683.2f, 405.9f, 685.2f),
                        PathNode.QuadTo(390.8f, 687.1f, 376.7f, 674.4f),
                        PathNode.LineTo(353.7f, 654.4f),
                        PathNode.QuadTo(340.0f, 642.6f, 339.1f, 628.1f),
                        PathNode.QuadTo(338.1f, 613.5f, 349.3f, 600.3f),
                        PathNode.LineTo(490.3f, 437.3f),
                        PathNode.QuadTo(508.0f, 416.6f, 534.6f, 416.9f),
                        PathNode.QuadTo(561.1f, 417.2f, 580.3f, 437.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1112.2f, 625.7f),
                        PathNode.QuadTo(1112.2f, 752.8f, 1038.6f, 855.0f),
                        PathNode.QuadTo(965.1f, 957.2f, 840.9f, 1015.4f),
                        PathNode.QuadTo(716.6f, 1073.5f, 571.4f, 1073.5f),
                        PathNode.QuadTo(436.2f, 1073.5f, 311.2f, 1017.9f),
                        PathNode.QuadTo(186.2f, 962.2f, 108.4f, 860.3f),
                        PathNode.QuadTo(30.6f, 758.3f, 30.6f, 625.7f),
                        PathNode.QuadTo(30.6f, 533.8f, 70.9f, 455.0f),
                        PathNode.QuadTo(111.2f, 376.1f, 180.4f, 316.7f),
                        PathNode.QuadTo(249.6f, 257.3f, 336.0f, 222.0f),
                        PathNode.QuadTo(341.6f, 220.0f, 344.0f, 215.4f),
                        PathNode.QuadTo(346.3f, 210.7f, 344.3f, 203.6f),
                        PathNode.LineTo(313.1f, 98.4f),
                        PathNode.QuadTo(310.7f, 89.8f, 314.3f, 82.5f),
                        PathNode.QuadTo(317.9f, 75.1f, 325.5f, 71.5f),
                        PathNode.QuadTo(333.1f, 68.0f, 342.1f, 70.3f),
                        PathNode.LineTo(689.5f, 170.8f),
                        PathNode.QuadTo(882.7f, 226.7f, 997.5f, 334.4f),
                        PathNode.QuadTo(1112.2f, 442.1f, 1112.2f, 625.7f),
                        PathNode.Close,
                        PathNode.MoveTo(385.5f, 329.5f),
                        PathNode.QuadTo(321.2f, 353.6f, 267.6f, 396.8f),
                        PathNode.QuadTo(213.9f, 439.9f, 181.6f, 498.7f),
                        PathNode.QuadTo(149.2f, 557.4f, 149.2f, 625.7f),
                        PathNode.QuadTo(149.2f, 723.5f, 209.9f, 798.8f),
                        PathNode.QuadTo(270.6f, 874.1f, 368.1f, 915.0f),
                        PathNode.QuadTo(465.6f, 955.9f, 571.4f, 955.9f),
                        PathNode.QuadTo(691.8f, 955.9f, 788.3f, 910.2f),
                        PathNode.QuadTo(884.8f, 864.6f, 939.2f, 788.8f),
                        PathNode.QuadTo(993.6f, 713.0f, 993.6f, 625.7f),
                        PathNode.QuadTo(993.6f, 527.4f, 947.9f, 460.2f),
                        PathNode.QuadTo(902.1f, 392.9f, 826.6f, 351.8f),
                        PathNode.QuadTo(751.0f, 310.7f, 643.7f, 279.8f),
                        PathNode.LineTo(463.4f, 227.6f),
                        PathNode.QuadTo(460.8f, 261.7f, 439.7f, 289.3f),
                        PathNode.QuadTo(418.6f, 317.0f, 385.5f, 329.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _readDemibold!!
    }

private var _readDemibold: ImageVector? = null
