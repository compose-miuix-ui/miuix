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

val MiuixIcons.Glass.SearchDevice: ImageVector
    get() = MiuixIcons.Glass.Regular.SearchDevice

val MiuixIcons.Glass.Light.SearchDevice: ImageVector
    get() {
        if (_searchDeviceLight != null) return _searchDeviceLight!!
        _searchDeviceLight = ImageVector.Builder(
            name = "SearchDevice.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(923.5f, 220.1f),
                        PathNode.QuadTo(997.2f, 294.4f, 1034.8f, 391.9f),
                        PathNode.QuadTo(1072.5f, 489.5f, 1068.0f, 595.6f),
                        PathNode.QuadTo(1063.6f, 701.8f, 1013.6f, 800.2f),
                        PathNode.QuadTo(1009.8f, 808.2f, 1001.3f, 809.1f),
                        PathNode.QuadTo(992.8f, 809.9f, 986.9f, 804.0f),
                        PathNode.LineTo(970.7f, 788.4f),
                        PathNode.QuadTo(964.8f, 783.0f, 964.0f, 777.2f),
                        PathNode.QuadTo(963.1f, 771.4f, 965.2f, 766.0f),
                        PathNode.QuadTo(1006.8f, 677.2f, 1010.2f, 585.6f),
                        PathNode.QuadTo(1013.6f, 493.9f, 980.0f, 410.6f),
                        PathNode.QuadTo(946.3f, 327.2f, 881.1f, 262.5f),
                        PathNode.QuadTo(796.9f, 177.8f, 683.8f, 148.4f),
                        PathNode.QuadTo(570.8f, 119.0f, 458.2f, 148.4f),
                        PathNode.QuadTo(345.7f, 177.8f, 261.5f, 262.5f),
                        PathNode.QuadTo(176.8f, 346.7f, 147.4f, 459.7f),
                        PathNode.QuadTo(118.0f, 572.8f, 147.4f, 685.4f),
                        PathNode.QuadTo(176.8f, 797.9f, 261.5f, 882.1f),
                        PathNode.QuadTo(342.5f, 962.6f, 450.0f, 993.0f),
                        PathNode.QuadTo(557.5f, 1023.5f, 666.1f, 999.9f),
                        PathNode.QuadTo(774.7f, 976.4f, 859.3f, 901.2f),
                        PathNode.LineTo(607.5f, 649.4f),
                        PathNode.QuadTo(600.3f, 642.2f, 593.1f, 644.1f),
                        PathNode.QuadTo(583.3f, 646.9f, 571.8f, 647.5f),
                        PathNode.QuadTo(551.3f, 647.5f, 534.0f, 637.2f),
                        PathNode.QuadTo(516.6f, 627.0f, 506.3f, 609.6f),
                        PathNode.QuadTo(496.1f, 592.2f, 496.1f, 571.8f),
                        PathNode.QuadTo(496.1f, 540.6f, 518.1f, 518.9f),
                        PathNode.QuadTo(540.1f, 497.1f, 571.8f, 497.1f),
                        PathNode.QuadTo(603.5f, 497.1f, 625.0f, 518.6f),
                        PathNode.QuadTo(646.5f, 540.1f, 646.5f, 571.8f),
                        PathNode.QuadTo(646.5f, 582.0f, 643.6f, 593.8f),
                        PathNode.QuadTo(642.0f, 600.7f, 648.2f, 606.9f),
                        PathNode.LineTo(929.6f, 888.2f),
                        PathNode.QuadTo(935.8f, 894.5f, 935.8f, 901.7f),
                        PathNode.QuadTo(935.8f, 909.0f, 930.7f, 915.2f),
                        PathNode.LineTo(923.0f, 923.5f),
                        PathNode.QuadTo(827.7f, 1018.8f, 699.5f, 1051.9f),
                        PathNode.QuadTo(571.3f, 1085.0f, 443.4f, 1051.9f),
                        PathNode.QuadTo(315.5f, 1018.8f, 220.1f, 923.5f),
                        PathNode.QuadTo(124.8f, 828.1f, 91.2f, 700.0f),
                        PathNode.QuadTo(57.6f, 571.8f, 91.2f, 443.9f),
                        PathNode.QuadTo(124.8f, 315.9f, 220.1f, 220.1f),
                        PathNode.QuadTo(315.9f, 124.8f, 443.8f, 91.2f),
                        PathNode.QuadTo(571.8f, 57.6f, 700.0f, 91.2f),
                        PathNode.QuadTo(828.1f, 124.8f, 923.5f, 220.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchDeviceLight!!
    }

private var _searchDeviceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.SearchDevice: ImageVector
    get() {
        if (_searchDeviceNormal != null) return _searchDeviceNormal!!
        _searchDeviceNormal = ImageVector.Builder(
            name = "SearchDevice.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(929.8f, 213.7f),
                        PathNode.QuadTo(1005.1f, 289.2f, 1043.3f, 388.4f),
                        PathNode.QuadTo(1081.5f, 487.6f, 1077.4f, 594.7f),
                        PathNode.QuadTo(1073.2f, 701.8f, 1023.2f, 799.9f),
                        PathNode.QuadTo(1017.9f, 810.7f, 1006.7f, 812.0f),
                        PathNode.QuadTo(995.4f, 813.3f, 987.4f, 805.2f),
                        PathNode.LineTo(967.2f, 785.9f),
                        PathNode.QuadTo(959.2f, 778.7f, 957.9f, 771.1f),
                        PathNode.QuadTo(956.6f, 763.4f, 959.3f, 756.2f),
                        PathNode.QuadTo(998.4f, 671.4f, 1001.2f, 583.0f),
                        PathNode.QuadTo(1004.0f, 494.5f, 971.1f, 413.5f),
                        PathNode.QuadTo(938.3f, 332.4f, 874.6f, 268.9f),
                        PathNode.QuadTo(791.9f, 186.1f, 681.3f, 157.3f),
                        PathNode.QuadTo(570.7f, 128.5f, 460.6f, 157.3f),
                        PathNode.QuadTo(350.4f, 186.1f, 267.8f, 268.9f),
                        PathNode.QuadTo(184.9f, 351.6f, 156.1f, 462.2f),
                        PathNode.QuadTo(127.4f, 572.8f, 156.1f, 682.9f),
                        PathNode.QuadTo(184.9f, 793.0f, 267.8f, 875.7f),
                        PathNode.QuadTo(346.0f, 953.1f, 449.4f, 983.2f),
                        PathNode.QuadTo(552.8f, 1013.4f, 657.9f, 992.3f),
                        PathNode.QuadTo(762.9f, 971.2f, 845.8f, 900.9f),
                        PathNode.LineTo(605.5f, 660.7f),
                        PathNode.QuadTo(597.8f, 652.9f, 589.3f, 654.2f),
                        PathNode.QuadTo(580.8f, 655.8f, 571.7f, 656.6f),
                        PathNode.QuadTo(548.8f, 656.6f, 529.3f, 645.2f),
                        PathNode.QuadTo(509.7f, 633.7f, 498.3f, 614.2f),
                        PathNode.QuadTo(486.8f, 594.7f, 486.8f, 571.8f),
                        PathNode.QuadTo(486.8f, 536.6f, 511.6f, 512.3f),
                        PathNode.QuadTo(536.3f, 488.0f, 571.7f, 488.0f),
                        PathNode.QuadTo(607.0f, 488.0f, 631.3f, 512.2f),
                        PathNode.QuadTo(655.5f, 536.5f, 655.5f, 571.8f),
                        PathNode.QuadTo(655.5f, 580.5f, 653.2f, 591.1f),
                        PathNode.QuadTo(651.4f, 597.3f, 658.8f, 604.8f),
                        PathNode.LineTo(939.2f, 885.2f),
                        PathNode.QuadTo(946.7f, 892.6f, 946.7f, 901.4f),
                        PathNode.QuadTo(946.7f, 910.2f, 940.9f, 917.7f),
                        PathNode.LineTo(929.6f, 929.9f),
                        PathNode.QuadTo(832.4f, 1027.1f, 702.0f, 1060.8f),
                        PathNode.QuadTo(571.5f, 1094.5f, 441.1f, 1060.8f),
                        PathNode.QuadTo(310.8f, 1027.1f, 213.6f, 929.9f),
                        PathNode.QuadTo(116.4f, 832.7f, 82.2f, 702.3f),
                        PathNode.QuadTo(48.0f, 571.8f, 82.2f, 441.4f),
                        PathNode.QuadTo(116.4f, 311.0f, 213.6f, 213.7f),
                        PathNode.QuadTo(310.9f, 116.6f, 441.3f, 82.3f),
                        PathNode.QuadTo(571.7f, 48.1f, 702.1f, 82.3f),
                        PathNode.QuadTo(832.6f, 116.6f, 929.8f, 213.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchDeviceNormal!!
    }

private var _searchDeviceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.SearchDevice: ImageVector
    get() {
        if (_searchDeviceRegular != null) return _searchDeviceRegular!!
        _searchDeviceRegular = ImageVector.Builder(
            name = "SearchDevice.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(932.6f, 210.8f),
                        PathNode.QuadTo(1008.6f, 286.8f, 1047.1f, 386.8f),
                        PathNode.QuadTo(1085.6f, 486.8f, 1081.6f, 594.3f),
                        PathNode.QuadTo(1077.6f, 701.8f, 1027.6f, 799.8f),
                        PathNode.QuadTo(1021.6f, 811.8f, 1009.1f, 813.3f),
                        PathNode.QuadTo(996.6f, 814.8f, 987.6f, 805.8f),
                        PathNode.LineTo(965.6f, 784.8f),
                        PathNode.QuadTo(956.6f, 776.8f, 955.1f, 768.3f),
                        PathNode.QuadTo(953.6f, 759.8f, 956.6f, 751.8f),
                        PathNode.QuadTo(994.6f, 668.8f, 997.1f, 581.8f),
                        PathNode.QuadTo(999.6f, 494.8f, 967.1f, 414.8f),
                        PathNode.QuadTo(934.6f, 334.8f, 871.6f, 271.8f),
                        PathNode.QuadTo(789.6f, 189.8f, 680.1f, 161.3f),
                        PathNode.QuadTo(570.6f, 132.8f, 461.6f, 161.3f),
                        PathNode.QuadTo(352.6f, 189.8f, 270.6f, 271.8f),
                        PathNode.QuadTo(188.6f, 353.8f, 160.1f, 463.3f),
                        PathNode.QuadTo(131.6f, 572.8f, 160.1f, 681.8f),
                        PathNode.QuadTo(188.6f, 790.8f, 270.6f, 872.8f),
                        PathNode.QuadTo(347.6f, 948.8f, 449.1f, 978.8f),
                        PathNode.QuadTo(550.6f, 1008.8f, 654.1f, 988.8f),
                        PathNode.QuadTo(757.6f, 968.8f, 839.6f, 900.8f),
                        PathNode.LineTo(604.6f, 665.8f),
                        PathNode.QuadTo(596.6f, 657.8f, 587.6f, 658.8f),
                        PathNode.QuadTo(579.6f, 659.8f, 571.6f, 660.8f),
                        PathNode.QuadTo(547.6f, 660.8f, 527.1f, 648.8f),
                        PathNode.QuadTo(506.6f, 636.8f, 494.6f, 616.3f),
                        PathNode.QuadTo(482.6f, 595.8f, 482.6f, 571.8f),
                        PathNode.QuadTo(482.6f, 534.8f, 508.6f, 509.3f),
                        PathNode.QuadTo(534.6f, 483.8f, 571.6f, 483.8f),
                        PathNode.QuadTo(608.6f, 483.8f, 634.1f, 509.3f),
                        PathNode.QuadTo(659.6f, 534.8f, 659.6f, 571.8f),
                        PathNode.QuadTo(659.6f, 579.8f, 657.6f, 589.8f),
                        PathNode.QuadTo(655.6f, 595.8f, 663.6f, 603.8f),
                        PathNode.LineTo(943.6f, 883.8f),
                        PathNode.QuadTo(951.6f, 891.8f, 951.6f, 901.3f),
                        PathNode.QuadTo(951.6f, 910.8f, 945.6f, 918.8f),
                        PathNode.LineTo(932.6f, 932.8f),
                        PathNode.QuadTo(834.6f, 1030.8f, 703.1f, 1064.8f),
                        PathNode.QuadTo(571.6f, 1098.8f, 440.1f, 1064.8f),
                        PathNode.QuadTo(308.6f, 1030.8f, 210.6f, 932.8f),
                        PathNode.QuadTo(112.6f, 834.8f, 78.1f, 703.3f),
                        PathNode.QuadTo(43.6f, 571.8f, 78.1f, 440.3f),
                        PathNode.QuadTo(112.6f, 308.8f, 210.6f, 210.8f),
                        PathNode.QuadTo(308.6f, 112.8f, 440.1f, 78.3f),
                        PathNode.QuadTo(571.6f, 43.8f, 703.1f, 78.3f),
                        PathNode.QuadTo(834.6f, 112.8f, 932.6f, 210.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchDeviceRegular!!
    }

private var _searchDeviceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.SearchDevice: ImageVector
    get() {
        if (_searchDeviceMedium != null) return _searchDeviceMedium!!
        _searchDeviceMedium = ImageVector.Builder(
            name = "SearchDevice.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(943.3f, 210.7f),
                        PathNode.QuadTo(1018.5f, 288.0f, 1055.8f, 387.9f),
                        PathNode.QuadTo(1093.1f, 487.8f, 1089.8f, 591.5f),
                        PathNode.QuadTo(1086.4f, 695.2f, 1041.8f, 784.0f),
                        PathNode.QuadTo(1034.2f, 799.5f, 1020.5f, 802.0f),
                        PathNode.QuadTo(1006.8f, 804.4f, 995.9f, 794.1f),
                        PathNode.LineTo(967.9f, 768.7f),
                        PathNode.QuadTo(956.0f, 758.6f, 954.3f, 749.3f),
                        PathNode.QuadTo(952.7f, 740.0f, 956.5f, 727.9f),
                        PathNode.QuadTo(987.5f, 655.7f, 988.9f, 576.0f),
                        PathNode.QuadTo(990.3f, 496.3f, 960.0f, 419.8f),
                        PathNode.QuadTo(929.6f, 343.3f, 869.6f, 281.7f),
                        PathNode.QuadTo(790.3f, 200.2f, 683.4f, 170.9f),
                        PathNode.QuadTo(576.5f, 141.6f, 469.1f, 168.1f),
                        PathNode.QuadTo(361.7f, 194.5f, 280.2f, 273.6f),
                        PathNode.QuadTo(198.8f, 352.9f, 169.5f, 459.9f),
                        PathNode.QuadTo(140.2f, 567.0f, 166.6f, 674.4f),
                        PathNode.QuadTo(193.1f, 781.8f, 272.4f, 863.2f),
                        PathNode.QuadTo(345.6f, 937.6f, 443.2f, 968.5f),
                        PathNode.QuadTo(540.8f, 999.5f, 641.0f, 982.7f),
                        PathNode.QuadTo(741.3f, 966.0f, 822.5f, 903.1f),
                        PathNode.LineTo(598.8f, 673.5f),
                        PathNode.QuadTo(591.9f, 666.6f, 583.7f, 667.3f),
                        PathNode.QuadTo(577.1f, 668.1f, 570.4f, 668.8f),
                        PathNode.QuadTo(544.3f, 668.5f, 522.0f, 654.9f),
                        PathNode.QuadTo(499.8f, 641.3f, 486.9f, 618.8f),
                        PathNode.QuadTo(474.1f, 596.2f, 474.4f, 570.1f),
                        PathNode.QuadTo(475.2f, 529.8f, 504.0f, 502.4f),
                        PathNode.QuadTo(532.9f, 475.0f, 573.1f, 475.8f),
                        PathNode.QuadTo(613.4f, 476.4f, 640.8f, 504.9f),
                        PathNode.QuadTo(668.2f, 533.3f, 667.6f, 573.6f),
                        PathNode.QuadTo(667.6f, 580.8f, 665.6f, 589.7f),
                        PathNode.QuadTo(663.9f, 595.1f, 670.5f, 601.8f),
                        PathNode.LineTo(944.9f, 883.7f),
                        PathNode.QuadTo(954.8f, 894.1f, 954.8f, 906.9f),
                        PathNode.QuadTo(954.8f, 919.6f, 946.3f, 929.8f),
                        PathNode.LineTo(932.8f, 943.8f),
                        PathNode.QuadTo(831.8f, 1041.8f, 697.7f, 1074.4f),
                        PathNode.QuadTo(563.7f, 1107.1f, 430.5f, 1070.5f),
                        PathNode.QuadTo(297.4f, 1033.9f, 199.4f, 933.0f),
                        PathNode.QuadTo(101.4f, 832.0f, 68.4f, 698.0f),
                        PathNode.QuadTo(35.4f, 564.1f, 72.3f, 431.0f),
                        PathNode.QuadTo(109.3f, 297.9f, 210.2f, 199.9f),
                        PathNode.QuadTo(311.2f, 101.9f, 445.3f, 68.7f),
                        PathNode.QuadTo(579.3f, 35.6f, 712.5f, 72.5f),
                        PathNode.QuadTo(845.6f, 109.4f, 943.3f, 210.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchDeviceMedium!!
    }

private var _searchDeviceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.SearchDevice: ImageVector
    get() {
        if (_searchDeviceDemibold != null) return _searchDeviceDemibold!!
        _searchDeviceDemibold = ImageVector.Builder(
            name = "SearchDevice.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(950.8f, 210.6f),
                        PathNode.QuadTo(1025.5f, 288.9f, 1061.9f, 388.7f),
                        PathNode.QuadTo(1098.3f, 488.4f, 1095.5f, 589.5f),
                        PathNode.QuadTo(1092.6f, 690.6f, 1051.8f, 772.9f),
                        PathNode.QuadTo(1043.1f, 790.9f, 1028.5f, 794.0f),
                        PathNode.QuadTo(1013.9f, 797.1f, 1001.7f, 785.8f),
                        PathNode.LineTo(969.6f, 757.5f),
                        PathNode.QuadTo(955.5f, 745.8f, 953.8f, 735.9f),
                        PathNode.QuadTo(952.1f, 726.1f, 956.5f, 711.2f),
                        PathNode.QuadTo(982.5f, 646.5f, 983.2f, 571.9f),
                        PathNode.QuadTo(983.8f, 497.4f, 955.0f, 423.3f),
                        PathNode.QuadTo(926.2f, 349.3f, 868.2f, 288.6f),
                        PathNode.QuadTo(790.8f, 207.5f, 685.7f, 177.6f),
                        PathNode.QuadTo(580.6f, 147.8f, 474.3f, 172.8f),
                        PathNode.QuadTo(368.1f, 197.9f, 287.0f, 274.8f),
                        PathNode.QuadTo(205.9f, 352.2f, 176.0f, 457.6f),
                        PathNode.QuadTo(146.2f, 562.9f, 171.2f, 669.2f),
                        PathNode.QuadTo(196.3f, 775.4f, 273.7f, 856.5f),
                        PathNode.QuadTo(344.2f, 929.8f, 439.1f, 961.4f),
                        PathNode.QuadTo(533.9f, 993.0f, 631.9f, 978.5f),
                        PathNode.QuadTo(729.9f, 964.0f, 810.5f, 904.7f),
                        PathNode.LineTo(594.8f, 678.9f),
                        PathNode.QuadTo(588.7f, 672.8f, 581.0f, 673.3f),
                        PathNode.QuadTo(575.3f, 673.8f, 569.6f, 674.4f),
                        PathNode.QuadTo(541.9f, 673.9f, 518.5f, 659.2f),
                        PathNode.QuadTo(495.0f, 644.4f, 481.6f, 620.5f),
                        PathNode.QuadTo(468.2f, 596.5f, 468.7f, 568.8f),
                        PathNode.QuadTo(470.1f, 526.3f, 500.9f, 497.6f),
                        PathNode.QuadTo(531.7f, 468.9f, 574.2f, 470.3f),
                        PathNode.QuadTo(616.7f, 471.2f, 645.4f, 501.7f),
                        PathNode.QuadTo(674.2f, 532.3f, 673.2f, 574.8f),
                        PathNode.QuadTo(673.2f, 581.4f, 671.2f, 589.6f),
                        PathNode.QuadTo(669.7f, 594.7f, 675.4f, 600.4f),
                        PathNode.LineTo(945.8f, 883.6f),
                        PathNode.QuadTo(957.0f, 895.7f, 957.0f, 910.7f),
                        PathNode.QuadTo(957.0f, 925.8f, 946.8f, 937.4f),
                        PathNode.LineTo(932.9f, 951.4f),
                        PathNode.QuadTo(829.9f, 1049.4f, 694.0f, 1081.1f),
                        PathNode.QuadTo(558.1f, 1112.8f, 423.9f, 1074.5f),
                        PathNode.QuadTo(289.6f, 1036.1f, 191.6f, 933.1f),
                        PathNode.QuadTo(93.6f, 830.0f, 61.7f, 694.4f),
                        PathNode.QuadTo(29.7f, 558.7f, 68.3f, 424.5f),
                        PathNode.QuadTo(106.9f, 290.2f, 210.0f, 192.2f),
                        PathNode.QuadTo(313.1f, 94.2f, 448.9f, 62.0f),
                        PathNode.QuadTo(584.8f, 29.8f, 719.0f, 68.4f),
                        PathNode.QuadTo(853.3f, 107.1f, 950.8f, 210.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchDeviceDemibold!!
    }

private var _searchDeviceDemibold: ImageVector? = null
