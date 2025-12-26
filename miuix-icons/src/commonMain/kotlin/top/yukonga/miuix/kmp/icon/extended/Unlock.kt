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

val MiuixIcons.Unlock: ImageVector
    get() = MiuixIcons.Regular.Unlock

val MiuixIcons.Light.Unlock: ImageVector
    get() {
        if (_unlockLight != null) return _unlockLight!!
        _unlockLight = ImageVector.Builder(
            name = "Unlock.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1261.2f,
            viewportHeight = 1261.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -34.39999999999998f, translationY = 985.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1000.0f, -154.0f),
                        PathNode.QuadTo(1043.0f, -132.0f, 1064.0f, -91.0f),
                        PathNode.QuadTo(1074.0f, -70.0f, 1076.5f, -38.5f),
                        PathNode.QuadTo(1079.0f, -7.0f, 1079.0f, 69.0f),
                        PathNode.VerticalTo(277.0f),
                        PathNode.QuadTo(1079.0f, 354.0f, 1076.5f, 385.0f),
                        PathNode.QuadTo(1074.0f, 416.0f, 1063.0f, 437.0f),
                        PathNode.QuadTo(1041.0f, 480.0f, 999.0f, 500.0f),
                        PathNode.QuadTo(978.0f, 511.0f, 947.0f, 513.5f),
                        PathNode.QuadTo(916.0f, 516.0f, 839.0f, 516.0f),
                        PathNode.HorizontalTo(499.0f),
                        PathNode.QuadTo(485.0f, 516.0f, 479.0f, 522.0f),
                        PathNode.QuadTo(473.0f, 528.0f, 473.0f, 543.0f),
                        PathNode.VerticalTo(626.0f),
                        PathNode.QuadTo(473.0f, 679.0f, 499.5f, 724.0f),
                        PathNode.QuadTo(526.0f, 769.0f, 570.5f, 795.5f),
                        PathNode.QuadTo(615.0f, 822.0f, 668.0f, 822.0f),
                        PathNode.QuadTo(715.0f, 822.0f, 756.5f, 801.5f),
                        PathNode.QuadTo(798.0f, 781.0f, 826.5f, 740.5f),
                        PathNode.QuadTo(855.0f, 700.0f, 862.0f, 643.0f),
                        PathNode.QuadTo(864.0f, 633.0f, 869.5f, 628.0f),
                        PathNode.QuadTo(875.0f, 623.0f, 887.0f, 623.0f),
                        PathNode.HorizontalTo(901.0f),
                        PathNode.QuadTo(913.0f, 623.0f, 917.5f, 629.0f),
                        PathNode.QuadTo(922.0f, 635.0f, 920.0f, 647.0f),
                        PathNode.QuadTo(913.0f, 717.0f, 877.5f, 770.0f),
                        PathNode.QuadTo(842.0f, 823.0f, 786.5f, 851.5f),
                        PathNode.QuadTo(731.0f, 880.0f, 668.0f, 880.0f),
                        PathNode.QuadTo(599.0f, 880.0f, 540.5f, 846.0f),
                        PathNode.QuadTo(482.0f, 812.0f, 448.0f, 753.5f),
                        PathNode.QuadTo(414.0f, 695.0f, 414.0f, 626.0f),
                        PathNode.VerticalTo(515.0f),
                        PathNode.QuadTo(382.0f, 513.0f, 362.0f, 510.0f),
                        PathNode.QuadTo(342.0f, 507.0f, 329.0f, 500.0f),
                        PathNode.QuadTo(290.0f, 481.0f, 266.0f, 438.0f),
                        PathNode.QuadTo(256.0f, 417.0f, 253.5f, 385.5f),
                        PathNode.QuadTo(251.0f, 354.0f, 251.0f, 277.0f),
                        PathNode.VerticalTo(69.0f),
                        PathNode.QuadTo(251.0f, -7.0f, 253.5f, -38.5f),
                        PathNode.QuadTo(256.0f, -70.0f, 266.0f, -91.0f),
                        PathNode.QuadTo(288.0f, -134.0f, 329.0f, -154.0f),
                        PathNode.QuadTo(351.0f, -165.0f, 384.0f, -168.0f),
                        PathNode.QuadTo(417.0f, -171.0f, 491.0f, -171.0f),
                        PathNode.HorizontalTo(839.0f),
                        PathNode.QuadTo(913.0f, -171.0f, 946.0f, -168.0f),
                        PathNode.QuadTo(979.0f, -165.0f, 1000.0f, -154.0f),
                        PathNode.Close,
                        PathNode.MoveTo(361.0f, -101.0f),
                        PathNode.QuadTo(334.0f, -90.0f, 319.0f, -60.0f),
                        PathNode.QuadTo(313.0f, -46.0f, 311.5f, -27.5f),
                        PathNode.QuadTo(310.0f, -9.0f, 310.0f, 33.0f),
                        PathNode.VerticalTo(314.0f),
                        PathNode.VerticalTo(325.0f),
                        PathNode.QuadTo(310.0f, 360.0f, 311.5f, 377.0f),
                        PathNode.QuadTo(313.0f, 394.0f, 319.0f, 406.0f),
                        PathNode.QuadTo(332.0f, 433.0f, 361.0f, 447.0f),
                        PathNode.QuadTo(375.0f, 454.0f, 394.0f, 455.5f),
                        PathNode.QuadTo(413.0f, 457.0f, 453.0f, 457.0f),
                        PathNode.HorizontalTo(876.0f),
                        PathNode.QuadTo(917.0f, 457.0f, 936.0f, 455.5f),
                        PathNode.QuadTo(955.0f, 454.0f, 969.0f, 447.0f),
                        PathNode.QuadTo(996.0f, 434.0f, 1010.0f, 407.0f),
                        PathNode.QuadTo(1016.0f, 393.0f, 1018.0f, 372.5f),
                        PathNode.QuadTo(1020.0f, 352.0f, 1020.0f, 314.0f),
                        PathNode.VerticalTo(33.0f),
                        PathNode.QuadTo(1020.0f, -9.0f, 1018.5f, -27.5f),
                        PathNode.QuadTo(1017.0f, -46.0f, 1011.0f, -60.0f),
                        PathNode.QuadTo(996.0f, -88.0f, 968.0f, -101.0f),
                        PathNode.QuadTo(954.0f, -109.0f, 935.5f, -110.5f),
                        PathNode.QuadTo(917.0f, -112.0f, 876.0f, -112.0f),
                        PathNode.HorizontalTo(453.0f),
                        PathNode.QuadTo(415.0f, -112.0f, 395.5f, -110.0f),
                        PathNode.QuadTo(376.0f, -108.0f, 361.0f, -101.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _unlockLight!!
    }

private var _unlockLight: ImageVector? = null

val MiuixIcons.Regular.Unlock: ImageVector
    get() {
        if (_unlockRegular != null) return _unlockRegular!!
        _unlockRegular = ImageVector.Builder(
            name = "Unlock.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1281.6f,
            viewportHeight = 1281.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -24.200000000000045f, translationY = 995.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1003.0f, -162.0f),
                        PathNode.QuadTo(1049.0f, -139.0f, 1072.0f, -93.0f),
                        PathNode.QuadTo(1084.0f, -70.0f, 1086.5f, -37.5f),
                        PathNode.QuadTo(1089.0f, -5.0f, 1089.0f, 72.0f),
                        PathNode.VerticalTo(278.0f),
                        PathNode.QuadTo(1089.0f, 355.0f, 1086.5f, 387.5f),
                        PathNode.QuadTo(1084.0f, 420.0f, 1072.0f, 443.0f),
                        PathNode.QuadTo(1049.0f, 488.0f, 1003.0f, 511.0f),
                        PathNode.QuadTo(980.0f, 523.0f, 947.5f, 525.5f),
                        PathNode.QuadTo(915.0f, 528.0f, 838.0f, 528.0f),
                        PathNode.HorizontalTo(524.0f),
                        PathNode.QuadTo(502.0f, 528.0f, 493.5f, 536.0f),
                        PathNode.QuadTo(485.0f, 544.0f, 485.0f, 564.0f),
                        PathNode.VerticalTo(624.0f),
                        PathNode.QuadTo(485.0f, 673.0f, 509.5f, 714.0f),
                        PathNode.QuadTo(534.0f, 755.0f, 575.0f, 779.5f),
                        PathNode.QuadTo(616.0f, 804.0f, 665.0f, 804.0f),
                        PathNode.QuadTo(707.0f, 804.0f, 744.5f, 785.5f),
                        PathNode.QuadTo(782.0f, 767.0f, 808.0f, 733.0f),
                        PathNode.QuadTo(834.0f, 699.0f, 842.0f, 655.0f),
                        PathNode.QuadTo(845.0f, 639.0f, 852.5f, 632.5f),
                        PathNode.QuadTo(860.0f, 626.0f, 879.0f, 626.0f),
                        PathNode.HorizontalTo(900.0f),
                        PathNode.QuadTo(918.0f, 626.0f, 924.0f, 635.0f),
                        PathNode.QuadTo(930.0f, 644.0f, 927.0f, 662.0f),
                        PathNode.QuadTo(917.0f, 727.0f, 879.5f, 779.0f),
                        PathNode.QuadTo(842.0f, 831.0f, 786.0f, 860.0f),
                        PathNode.QuadTo(730.0f, 889.0f, 665.0f, 889.0f),
                        PathNode.QuadTo(593.0f, 889.0f, 532.0f, 853.5f),
                        PathNode.QuadTo(471.0f, 818.0f, 435.5f, 757.0f),
                        PathNode.QuadTo(400.0f, 696.0f, 400.0f, 624.0f),
                        PathNode.VerticalTo(527.0f),
                        PathNode.QuadTo(375.0f, 525.0f, 357.5f, 521.5f),
                        PathNode.QuadTo(340.0f, 518.0f, 326.0f, 511.0f),
                        PathNode.QuadTo(281.0f, 488.0f, 258.0f, 443.0f),
                        PathNode.QuadTo(246.0f, 420.0f, 243.5f, 387.5f),
                        PathNode.QuadTo(241.0f, 355.0f, 241.0f, 278.0f),
                        PathNode.VerticalTo(72.0f),
                        PathNode.QuadTo(241.0f, -5.0f, 243.5f, -37.5f),
                        PathNode.QuadTo(246.0f, -70.0f, 258.0f, -93.0f),
                        PathNode.QuadTo(281.0f, -139.0f, 326.0f, -162.0f),
                        PathNode.QuadTo(349.0f, -174.0f, 382.0f, -176.5f),
                        PathNode.QuadTo(415.0f, -179.0f, 492.0f, -179.0f),
                        PathNode.HorizontalTo(838.0f),
                        PathNode.QuadTo(915.0f, -179.0f, 947.5f, -176.5f),
                        PathNode.QuadTo(980.0f, -174.0f, 1003.0f, -162.0f),
                        PathNode.Close,
                        PathNode.MoveTo(370.0f, -85.0f),
                        PathNode.QuadTo(347.0f, -73.0f, 335.0f, -50.0f),
                        PathNode.QuadTo(329.0f, -38.0f, 327.5f, -21.0f),
                        PathNode.QuadTo(326.0f, -4.0f, 326.0f, 36.0f),
                        PathNode.VerticalTo(314.0f),
                        PathNode.VerticalTo(325.0f),
                        PathNode.QuadTo(326.0f, 356.0f, 327.5f, 372.0f),
                        PathNode.QuadTo(329.0f, 388.0f, 335.0f, 399.0f),
                        PathNode.QuadTo(347.0f, 422.0f, 370.0f, 434.0f),
                        PathNode.QuadTo(382.0f, 440.0f, 399.0f, 441.5f),
                        PathNode.QuadTo(416.0f, 443.0f, 455.0f, 443.0f),
                        PathNode.HorizontalTo(874.0f),
                        PathNode.QuadTo(914.0f, 443.0f, 930.5f, 441.5f),
                        PathNode.QuadTo(947.0f, 440.0f, 959.0f, 434.0f),
                        PathNode.QuadTo(983.0f, 422.0f, 995.0f, 399.0f),
                        PathNode.QuadTo(1000.0f, 387.0f, 1001.5f, 370.5f),
                        PathNode.QuadTo(1003.0f, 354.0f, 1003.0f, 314.0f),
                        PathNode.VerticalTo(36.0f),
                        PathNode.QuadTo(1003.0f, -4.0f, 1002.0f, -21.0f),
                        PathNode.QuadTo(1001.0f, -38.0f, 995.0f, -50.0f),
                        PathNode.QuadTo(983.0f, -73.0f, 959.0f, -85.0f),
                        PathNode.QuadTo(947.0f, -91.0f, 930.5f, -92.5f),
                        PathNode.QuadTo(914.0f, -94.0f, 874.0f, -94.0f),
                        PathNode.HorizontalTo(455.0f),
                        PathNode.QuadTo(416.0f, -94.0f, 399.0f, -92.5f),
                        PathNode.QuadTo(382.0f, -91.0f, 370.0f, -85.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _unlockRegular!!
    }

private var _unlockRegular: ImageVector? = null

val MiuixIcons.Heavy.Unlock: ImageVector
    get() {
        if (_unlockHeavy != null) return _unlockHeavy!!
        _unlockHeavy = ImageVector.Builder(
            name = "Unlock.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1312.8f,
            viewportHeight = 1312.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -8.600000000000023f, translationY = 1011.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1008.0f, -173.0f),
                        PathNode.QuadTo(1059.0f, -148.0f, 1084.0f, -97.0f),
                        PathNode.QuadTo(1097.0f, -72.0f, 1100.0f, -37.5f),
                        PathNode.QuadTo(1103.0f, -3.0f, 1103.0f, 75.0f),
                        PathNode.VerticalTo(279.0f),
                        PathNode.QuadTo(1103.0f, 356.0f, 1100.0f, 390.5f),
                        PathNode.QuadTo(1097.0f, 425.0f, 1084.0f, 450.0f),
                        PathNode.QuadTo(1060.0f, 499.0f, 1008.0f, 526.0f),
                        PathNode.QuadTo(983.0f, 539.0f, 948.5f, 542.0f),
                        PathNode.QuadTo(914.0f, 545.0f, 837.0f, 545.0f),
                        PathNode.HorizontalTo(534.0f),
                        PathNode.QuadTo(518.0f, 545.0f, 511.5f, 551.0f),
                        PathNode.QuadTo(505.0f, 557.0f, 505.0f, 573.0f),
                        PathNode.VerticalTo(622.0f),
                        PathNode.QuadTo(505.0f, 665.0f, 526.5f, 702.0f),
                        PathNode.QuadTo(548.0f, 739.0f, 585.5f, 760.5f),
                        PathNode.QuadTo(623.0f, 782.0f, 667.0f, 782.0f),
                        PathNode.QuadTo(705.0f, 782.0f, 736.5f, 768.0f),
                        PathNode.QuadTo(768.0f, 754.0f, 789.0f, 729.0f),
                        PathNode.QuadTo(810.0f, 704.0f, 818.0f, 671.0f),
                        PathNode.QuadTo(822.0f, 655.0f, 833.0f, 648.5f),
                        PathNode.QuadTo(844.0f, 642.0f, 869.0f, 642.0f),
                        PathNode.HorizontalTo(898.0f),
                        PathNode.QuadTo(923.0f, 642.0f, 933.0f, 654.0f),
                        PathNode.QuadTo(943.0f, 666.0f, 940.0f, 687.0f),
                        PathNode.QuadTo(931.0f, 745.0f, 892.5f, 794.5f),
                        PathNode.QuadTo(854.0f, 844.0f, 794.5f, 873.0f),
                        PathNode.QuadTo(735.0f, 902.0f, 667.0f, 902.0f),
                        PathNode.QuadTo(589.0f, 902.0f, 524.5f, 864.5f),
                        PathNode.QuadTo(460.0f, 827.0f, 422.0f, 762.5f),
                        PathNode.QuadTo(384.0f, 698.0f, 384.0f, 622.0f),
                        PathNode.VerticalTo(542.0f),
                        PathNode.QuadTo(363.0f, 540.0f, 348.0f, 536.0f),
                        PathNode.QuadTo(333.0f, 532.0f, 321.0f, 526.0f),
                        PathNode.QuadTo(270.0f, 499.0f, 246.0f, 450.0f),
                        PathNode.QuadTo(233.0f, 425.0f, 230.0f, 390.5f),
                        PathNode.QuadTo(227.0f, 356.0f, 227.0f, 279.0f),
                        PathNode.VerticalTo(75.0f),
                        PathNode.QuadTo(227.0f, -3.0f, 230.0f, -37.5f),
                        PathNode.QuadTo(233.0f, -72.0f, 246.0f, -97.0f),
                        PathNode.QuadTo(271.0f, -148.0f, 321.0f, -173.0f),
                        PathNode.QuadTo(347.0f, -186.0f, 381.5f, -189.0f),
                        PathNode.QuadTo(416.0f, -192.0f, 493.0f, -192.0f),
                        PathNode.HorizontalTo(837.0f),
                        PathNode.QuadTo(914.0f, -192.0f, 948.5f, -189.0f),
                        PathNode.QuadTo(983.0f, -186.0f, 1008.0f, -173.0f),
                        PathNode.Close,
                        PathNode.MoveTo(381.0f, -65.0f),
                        PathNode.QuadTo(363.0f, -56.0f, 354.0f, -38.0f),
                        PathNode.QuadTo(349.0f, -28.0f, 348.0f, -13.5f),
                        PathNode.QuadTo(347.0f, 1.0f, 347.0f, 39.0f),
                        PathNode.VerticalTo(314.0f),
                        PathNode.VerticalTo(325.0f),
                        PathNode.QuadTo(347.0f, 354.0f, 348.5f, 368.0f),
                        PathNode.QuadTo(350.0f, 382.0f, 354.0f, 390.0f),
                        PathNode.QuadTo(363.0f, 408.0f, 381.0f, 417.0f),
                        PathNode.QuadTo(390.0f, 422.0f, 404.5f, 423.0f),
                        PathNode.QuadTo(419.0f, 424.0f, 457.0f, 424.0f),
                        PathNode.HorizontalTo(872.0f),
                        PathNode.QuadTo(910.0f, 424.0f, 925.0f, 423.0f),
                        PathNode.QuadTo(940.0f, 422.0f, 948.0f, 417.0f),
                        PathNode.QuadTo(966.0f, 408.0f, 976.0f, 391.0f),
                        PathNode.QuadTo(980.0f, 381.0f, 981.0f, 362.0f),
                        PathNode.QuadTo(982.0f, 343.0f, 982.0f, 314.0f),
                        PathNode.VerticalTo(39.0f),
                        PathNode.QuadTo(982.0f, 10.0f, 981.0f, -9.5f),
                        PathNode.QuadTo(980.0f, -29.0f, 976.0f, -39.0f),
                        PathNode.QuadTo(966.0f, -56.0f, 948.0f, -65.0f),
                        PathNode.QuadTo(940.0f, -70.0f, 925.0f, -71.0f),
                        PathNode.QuadTo(910.0f, -72.0f, 872.0f, -72.0f),
                        PathNode.HorizontalTo(457.0f),
                        PathNode.QuadTo(419.0f, -72.0f, 404.5f, -71.0f),
                        PathNode.QuadTo(390.0f, -70.0f, 381.0f, -65.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _unlockHeavy!!
    }

private var _unlockHeavy: ImageVector? = null
