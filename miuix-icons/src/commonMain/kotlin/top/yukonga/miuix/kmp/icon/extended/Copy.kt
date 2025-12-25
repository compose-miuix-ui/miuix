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

val MiuixIcons.Copy: ImageVector
    get() = MiuixIcons.Regular.Copy

val MiuixIcons.Light.Copy: ImageVector
    get() {
        if (_copyLight != null) return _copyLight!!
        _copyLight = ImageVector.Builder(
            name = "Copy.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1220.3999999999999f,
            viewportHeight = 1220.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -54.80000000000007f, translationY = 985.6999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(837.0f, -112.0f),
                PathNode.QuadTo(889.0f, -85.0f, 910.0f, -39.0f),
                PathNode.QuadTo(923.0f, -11.0f, 927.0f, 30.5f),
                PathNode.QuadTo(931.0f, 72.0f, 931.0f, 162.0f),
                PathNode.VerticalTo(347.0f),
                PathNode.QuadTo(931.0f, 443.0f, 927.5f, 482.0f),
                PathNode.QuadTo(924.0f, 521.0f, 910.0f, 548.0f),
                PathNode.QuadTo(885.0f, 596.0f, 837.0f, 620.0f),
                PathNode.QuadTo(810.0f, 635.0f, 770.0f, 638.5f),
                PathNode.QuadTo(730.0f, 642.0f, 637.0f, 642.0f),
                PathNode.HorizontalTo(452.0f),
                PathNode.QuadTo(362.0f, 642.0f, 320.5f, 638.0f),
                PathNode.QuadTo(279.0f, 634.0f, 251.0f, 620.0f),
                PathNode.QuadTo(205.0f, 598.0f, 178.0f, 548.0f),
                PathNode.QuadTo(164.0f, 521.0f, 160.5f, 482.0f),
                PathNode.QuadTo(157.0f, 443.0f, 157.0f, 347.0f),
                PathNode.VerticalTo(162.0f),
                PathNode.QuadTo(157.0f, 67.0f, 160.5f, 27.5f),
                PathNode.QuadTo(164.0f, -12.0f, 178.0f, -39.0f),
                PathNode.QuadTo(203.0f, -87.0f, 251.0f, -112.0f),
                PathNode.QuadTo(278.0f, -126.0f, 317.5f, -129.5f),
                PathNode.QuadTo(357.0f, -133.0f, 452.0f, -133.0f),
                PathNode.HorizontalTo(637.0f),
                PathNode.QuadTo(733.0f, -133.0f, 772.0f, -129.5f),
                PathNode.QuadTo(811.0f, -126.0f, 837.0f, -112.0f),
                PathNode.Close,
                PathNode.MoveTo(280.0f, -60.0f),
                PathNode.QuadTo(245.0f, -42.0f, 230.0f, -9.0f),
                PathNode.QuadTo(220.0f, 10.0f, 218.0f, 35.0f),
                PathNode.QuadTo(216.0f, 60.0f, 216.0f, 116.0f),
                PathNode.VerticalTo(392.0f),
                PathNode.QuadTo(216.0f, 448.0f, 218.0f, 473.5f),
                PathNode.QuadTo(220.0f, 499.0f, 230.0f, 517.0f),
                PathNode.QuadTo(237.0f, 533.0f, 250.5f, 546.5f),
                PathNode.QuadTo(264.0f, 560.0f, 280.0f, 568.0f),
                PathNode.QuadTo(299.0f, 577.0f, 326.0f, 579.5f),
                PathNode.QuadTo(353.0f, 582.0f, 406.0f, 582.0f),
                PathNode.HorizontalTo(682.0f),
                PathNode.QuadTo(735.0f, 582.0f, 761.5f, 579.5f),
                PathNode.QuadTo(788.0f, 577.0f, 807.0f, 568.0f),
                PathNode.QuadTo(824.0f, 561.0f, 837.5f, 547.5f),
                PathNode.QuadTo(851.0f, 534.0f, 858.0f, 517.0f),
                PathNode.QuadTo(867.0f, 498.0f, 869.5f, 471.5f),
                PathNode.QuadTo(872.0f, 445.0f, 872.0f, 392.0f),
                PathNode.VerticalTo(116.0f),
                PathNode.QuadTo(872.0f, 60.0f, 870.0f, 35.0f),
                PathNode.QuadTo(868.0f, 10.0f, 858.0f, -8.0f),
                PathNode.QuadTo(840.0f, -45.0f, 807.0f, -60.0f),
                PathNode.QuadTo(788.0f, -69.0f, 761.5f, -71.5f),
                PathNode.QuadTo(735.0f, -74.0f, 682.0f, -74.0f),
                PathNode.HorizontalTo(406.0f),
                PathNode.QuadTo(353.0f, -74.0f, 326.0f, -71.5f),
                PathNode.QuadTo(299.0f, -69.0f, 280.0f, -60.0f),
                PathNode.Close,
                PathNode.MoveTo(996.0f, 884.0f),
                PathNode.HorizontalTo(628.0f),
                PathNode.QuadTo(569.0f, 884.0f, 522.5f, 850.0f),
                PathNode.QuadTo(476.0f, 816.0f, 454.0f, 749.0f),
                PathNode.QuadTo(450.0f, 738.0f, 456.0f, 731.5f),
                PathNode.QuadTo(462.0f, 725.0f, 472.0f, 725.0f),
                PathNode.HorizontalTo(495.0f),
                PathNode.QuadTo(503.0f, 725.0f, 506.5f, 728.0f),
                PathNode.QuadTo(510.0f, 731.0f, 517.0f, 743.0f),
                PathNode.QuadTo(535.0f, 787.0f, 565.0f, 806.0f),
                PathNode.QuadTo(595.0f, 825.0f, 633.0f, 825.0f),
                PathNode.HorizontalTo(992.0f),
                PathNode.QuadTo(1043.0f, 825.0f, 1078.5f, 789.0f),
                PathNode.QuadTo(1114.0f, 753.0f, 1114.0f, 702.0f),
                PathNode.VerticalTo(343.0f),
                PathNode.QuadTo(1114.0f, 309.0f, 1096.5f, 280.5f),
                PathNode.QuadTo(1079.0f, 252.0f, 1042.0f, 232.0f),
                PathNode.QuadTo(1040.0f, 231.0f, 1037.0f, 229.0f),
                PathNode.QuadTo(1027.0f, 223.0f, 1025.0f, 219.5f),
                PathNode.QuadTo(1023.0f, 216.0f, 1023.0f, 203.0f),
                PathNode.VerticalTo(184.0f),
                PathNode.QuadTo(1023.0f, 174.0f, 1031.5f, 169.5f),
                PathNode.QuadTo(1040.0f, 165.0f, 1050.0f, 170.0f),
                PathNode.QuadTo(1110.0f, 194.0f, 1141.5f, 238.0f),
                PathNode.QuadTo(1173.0f, 282.0f, 1173.0f, 339.0f),
                PathNode.VerticalTo(707.0f),
                PathNode.QuadTo(1173.0f, 755.0f, 1149.0f, 795.5f),
                PathNode.QuadTo(1125.0f, 836.0f, 1084.5f, 860.0f),
                PathNode.QuadTo(1044.0f, 884.0f, 996.0f, 884.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _copyLight!!
    }

private var _copyLight: ImageVector? = null

val MiuixIcons.Regular.Copy: ImageVector
    get() {
        if (_copyRegular != null) return _copyRegular!!
        _copyRegular = ImageVector.Builder(
            name = "Copy.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1251.6f,
            viewportHeight = 1251.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -39.200000000000045f, translationY = 1001.3f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(843.0f, -124.0f),
                PathNode.QuadTo(897.0f, -96.0f, 922.0f, -45.0f),
                PathNode.QuadTo(937.0f, -16.0f, 940.5f, 25.0f),
                PathNode.QuadTo(944.0f, 66.0f, 944.0f, 162.0f),
                PathNode.VerticalTo(347.0f),
                PathNode.QuadTo(944.0f, 443.0f, 940.5f, 484.0f),
                PathNode.QuadTo(937.0f, 525.0f, 922.0f, 554.0f),
                PathNode.QuadTo(895.0f, 606.0f, 843.0f, 632.0f),
                PathNode.QuadTo(815.0f, 648.0f, 774.0f, 651.5f),
                PathNode.QuadTo(733.0f, 655.0f, 637.0f, 655.0f),
                PathNode.HorizontalTo(452.0f),
                PathNode.QuadTo(356.0f, 655.0f, 315.0f, 651.5f),
                PathNode.QuadTo(274.0f, 648.0f, 245.0f, 632.0f),
                PathNode.QuadTo(195.0f, 607.0f, 166.0f, 554.0f),
                PathNode.QuadTo(151.0f, 525.0f, 147.5f, 484.0f),
                PathNode.QuadTo(144.0f, 443.0f, 144.0f, 347.0f),
                PathNode.VerticalTo(162.0f),
                PathNode.QuadTo(144.0f, 66.0f, 147.5f, 25.0f),
                PathNode.QuadTo(151.0f, -16.0f, 166.0f, -45.0f),
                PathNode.QuadTo(193.0f, -97.0f, 245.0f, -124.0f),
                PathNode.QuadTo(274.0f, -139.0f, 315.0f, -142.5f),
                PathNode.QuadTo(356.0f, -146.0f, 452.0f, -146.0f),
                PathNode.HorizontalTo(637.0f),
                PathNode.QuadTo(733.0f, -146.0f, 774.0f, -142.5f),
                PathNode.QuadTo(815.0f, -139.0f, 843.0f, -124.0f),
                PathNode.Close,
                PathNode.MoveTo(287.0f, -48.0f),
                PathNode.QuadTo(257.0f, -32.0f, 242.0f, -2.0f),
                PathNode.QuadTo(233.0f, 15.0f, 231.0f, 38.0f),
                PathNode.QuadTo(229.0f, 61.0f, 229.0f, 116.0f),
                PathNode.VerticalTo(392.0f),
                PathNode.QuadTo(229.0f, 447.0f, 231.0f, 471.0f),
                PathNode.QuadTo(233.0f, 495.0f, 242.0f, 511.0f),
                PathNode.QuadTo(250.0f, 525.0f, 261.5f, 537.0f),
                PathNode.QuadTo(273.0f, 549.0f, 287.0f, 556.0f),
                PathNode.QuadTo(303.0f, 565.0f, 327.0f, 567.0f),
                PathNode.QuadTo(351.0f, 569.0f, 406.0f, 569.0f),
                PathNode.HorizontalTo(682.0f),
                PathNode.QuadTo(737.0f, 569.0f, 761.0f, 567.0f),
                PathNode.QuadTo(785.0f, 565.0f, 801.0f, 556.0f),
                PathNode.QuadTo(815.0f, 549.0f, 827.0f, 537.0f),
                PathNode.QuadTo(839.0f, 525.0f, 846.0f, 511.0f),
                PathNode.QuadTo(855.0f, 495.0f, 857.0f, 471.0f),
                PathNode.QuadTo(859.0f, 447.0f, 859.0f, 392.0f),
                PathNode.VerticalTo(116.0f),
                PathNode.QuadTo(859.0f, 61.0f, 857.0f, 38.0f),
                PathNode.QuadTo(855.0f, 15.0f, 846.0f, -2.0f),
                PathNode.QuadTo(831.0f, -32.0f, 801.0f, -48.0f),
                PathNode.QuadTo(785.0f, -57.0f, 761.0f, -59.0f),
                PathNode.QuadTo(737.0f, -61.0f, 682.0f, -61.0f),
                PathNode.HorizontalTo(406.0f),
                PathNode.QuadTo(351.0f, -61.0f, 327.0f, -59.0f),
                PathNode.QuadTo(303.0f, -57.0f, 287.0f, -48.0f),
                PathNode.Close,
                PathNode.MoveTo(996.0f, 897.0f),
                PathNode.HorizontalTo(628.0f),
                PathNode.QuadTo(568.0f, 897.0f, 519.0f, 863.0f),
                PathNode.QuadTo(470.0f, 829.0f, 448.0f, 769.0f),
                PathNode.QuadTo(442.0f, 752.0f, 450.5f, 741.5f),
                PathNode.QuadTo(459.0f, 731.0f, 476.0f, 731.0f),
                PathNode.HorizontalTo(505.0f),
                PathNode.QuadTo(516.0f, 731.0f, 522.0f, 735.5f),
                PathNode.QuadTo(528.0f, 740.0f, 539.0f, 759.0f),
                PathNode.QuadTo(554.0f, 783.0f, 579.0f, 797.5f),
                PathNode.QuadTo(604.0f, 812.0f, 633.0f, 812.0f),
                PathNode.HorizontalTo(992.0f),
                PathNode.QuadTo(1037.0f, 812.0f, 1069.0f, 780.0f),
                PathNode.QuadTo(1101.0f, 748.0f, 1101.0f, 702.0f),
                PathNode.VerticalTo(343.0f),
                PathNode.QuadTo(1101.0f, 314.0f, 1087.0f, 290.0f),
                PathNode.QuadTo(1073.0f, 266.0f, 1050.0f, 251.0f),
                PathNode.QuadTo(1046.0f, 248.0f, 1041.0f, 245.0f),
                PathNode.QuadTo(1026.0f, 236.0f, 1023.0f, 230.5f),
                PathNode.QuadTo(1020.0f, 225.0f, 1020.0f, 205.0f),
                PathNode.VerticalTo(182.0f),
                PathNode.QuadTo(1020.0f, 168.0f, 1032.5f, 160.0f),
                PathNode.QuadTo(1045.0f, 152.0f, 1060.0f, 159.0f),
                PathNode.QuadTo(1119.0f, 182.0f, 1152.5f, 230.0f),
                PathNode.QuadTo(1186.0f, 278.0f, 1186.0f, 339.0f),
                PathNode.VerticalTo(707.0f),
                PathNode.QuadTo(1186.0f, 759.0f, 1160.5f, 802.5f),
                PathNode.QuadTo(1135.0f, 846.0f, 1091.5f, 871.5f),
                PathNode.QuadTo(1048.0f, 897.0f, 996.0f, 897.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _copyRegular!!
    }

private var _copyRegular: ImageVector? = null

val MiuixIcons.Heavy.Copy: ImageVector
    get() {
        if (_copyHeavy != null) return _copyHeavy!!
        _copyHeavy = ImageVector.Builder(
            name = "Copy.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1294.8f,
            viewportHeight = 1294.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -18.600000000000023f, translationY = 1022.9f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(846.0f, -141.0f),
                PathNode.QuadTo(902.0f, -112.0f, 931.0f, -56.0f),
                PathNode.QuadTo(947.0f, -25.0f, 950.5f, 17.5f),
                PathNode.QuadTo(954.0f, 60.0f, 954.0f, 157.0f),
                PathNode.VerticalTo(342.0f),
                PathNode.QuadTo(954.0f, 439.0f, 950.5f, 482.0f),
                PathNode.QuadTo(947.0f, 525.0f, 931.0f, 556.0f),
                PathNode.QuadTo(901.0f, 611.0f, 846.0f, 640.0f),
                PathNode.QuadTo(816.0f, 656.0f, 773.0f, 660.0f),
                PathNode.QuadTo(730.0f, 664.0f, 633.0f, 664.0f),
                PathNode.HorizontalTo(448.0f),
                PathNode.QuadTo(351.0f, 664.0f, 308.5f, 660.0f),
                PathNode.QuadTo(266.0f, 656.0f, 235.0f, 640.0f),
                PathNode.QuadTo(180.0f, 611.0f, 150.0f, 556.0f),
                PathNode.QuadTo(134.0f, 525.0f, 130.5f, 482.0f),
                PathNode.QuadTo(127.0f, 439.0f, 127.0f, 342.0f),
                PathNode.VerticalTo(157.0f),
                PathNode.QuadTo(127.0f, 60.0f, 130.5f, 17.5f),
                PathNode.QuadTo(134.0f, -25.0f, 150.0f, -56.0f),
                PathNode.QuadTo(179.0f, -112.0f, 235.0f, -141.0f),
                PathNode.QuadTo(266.0f, -157.0f, 308.5f, -160.5f),
                PathNode.QuadTo(351.0f, -164.0f, 448.0f, -164.0f),
                PathNode.HorizontalTo(633.0f),
                PathNode.QuadTo(730.0f, -164.0f, 773.0f, -160.5f),
                PathNode.QuadTo(816.0f, -157.0f, 846.0f, -141.0f),
                PathNode.Close,
                PathNode.MoveTo(290.0f, -41.0f),
                PathNode.QuadTo(263.0f, -27.0f, 251.0f, 0.0f),
                PathNode.QuadTo(243.0f, 15.0f, 241.0f, 36.5f),
                PathNode.QuadTo(239.0f, 58.0f, 239.0f, 111.0f),
                PathNode.VerticalTo(387.0f),
                PathNode.QuadTo(239.0f, 442.0f, 240.5f, 464.0f),
                PathNode.QuadTo(242.0f, 486.0f, 250.0f, 500.0f),
                PathNode.QuadTo(257.0f, 513.0f, 267.0f, 522.5f),
                PathNode.QuadTo(277.0f, 532.0f, 290.0f, 539.0f),
                PathNode.QuadTo(304.0f, 547.0f, 326.0f, 549.0f),
                PathNode.QuadTo(348.0f, 551.0f, 402.0f, 551.0f),
                PathNode.HorizontalTo(678.0f),
                PathNode.QuadTo(733.0f, 551.0f, 755.0f, 549.0f),
                PathNode.QuadTo(777.0f, 547.0f, 791.0f, 539.0f),
                PathNode.QuadTo(804.0f, 532.0f, 813.5f, 522.5f),
                PathNode.QuadTo(823.0f, 513.0f, 830.0f, 500.0f),
                PathNode.QuadTo(838.0f, 486.0f, 840.0f, 464.0f),
                PathNode.QuadTo(842.0f, 442.0f, 842.0f, 387.0f),
                PathNode.VerticalTo(111.0f),
                PathNode.QuadTo(842.0f, 58.0f, 840.0f, 36.5f),
                PathNode.QuadTo(838.0f, 15.0f, 830.0f, 0.0f),
                PathNode.QuadTo(816.0f, -28.0f, 791.0f, -41.0f),
                PathNode.QuadTo(777.0f, -49.0f, 755.0f, -50.5f),
                PathNode.QuadTo(733.0f, -52.0f, 678.0f, -52.0f),
                PathNode.HorizontalTo(402.0f),
                PathNode.QuadTo(348.0f, -52.0f, 326.0f, -50.5f),
                PathNode.QuadTo(304.0f, -49.0f, 290.0f, -41.0f),
                PathNode.Close,
                PathNode.MoveTo(1002.0f, 915.0f),
                PathNode.HorizontalTo(634.0f),
                PathNode.QuadTo(572.0f, 915.0f, 521.5f, 881.5f),
                PathNode.QuadTo(471.0f, 848.0f, 448.0f, 797.0f),
                PathNode.QuadTo(435.0f, 770.0f, 443.5f, 754.5f),
                PathNode.QuadTo(452.0f, 739.0f, 477.0f, 739.0f),
                PathNode.HorizontalTo(520.0f),
                PathNode.QuadTo(534.0f, 739.0f, 542.5f, 744.0f),
                PathNode.QuadTo(551.0f, 749.0f, 561.0f, 765.0f),
                PathNode.QuadTo(572.0f, 783.0f, 590.5f, 793.0f),
                PathNode.QuadTo(609.0f, 803.0f, 634.0f, 803.0f),
                PathNode.HorizontalTo(998.0f),
                PathNode.QuadTo(1037.0f, 803.0f, 1065.0f, 775.0f),
                PathNode.QuadTo(1093.0f, 747.0f, 1093.0f, 707.0f),
                PathNode.VerticalTo(347.0f),
                PathNode.QuadTo(1093.0f, 322.0f, 1084.0f, 304.5f),
                PathNode.QuadTo(1075.0f, 287.0f, 1059.0f, 276.0f),
                PathNode.QuadTo(1055.0f, 273.0f, 1051.0f, 270.0f),
                PathNode.QuadTo(1038.0f, 260.0f, 1034.0f, 251.5f),
                PathNode.QuadTo(1030.0f, 243.0f, 1030.0f, 222.0f),
                PathNode.VerticalTo(187.0f),
                PathNode.QuadTo(1030.0f, 163.0f, 1046.0f, 154.0f),
                PathNode.QuadTo(1062.0f, 145.0f, 1087.0f, 159.0f),
                PathNode.QuadTo(1142.0f, 185.0f, 1173.5f, 231.5f),
                PathNode.QuadTo(1205.0f, 278.0f, 1205.0f, 344.0f),
                PathNode.VerticalTo(712.0f),
                PathNode.QuadTo(1205.0f, 767.0f, 1177.5f, 813.5f),
                PathNode.QuadTo(1150.0f, 860.0f, 1103.5f, 887.5f),
                PathNode.QuadTo(1057.0f, 915.0f, 1002.0f, 915.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _copyHeavy!!
    }

private var _copyHeavy: ImageVector? = null
