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

val MiuixIcons.Alarm: ImageVector
    get() = MiuixIcons.Regular.Alarm

val MiuixIcons.Light.Alarm: ImageVector
    get() {
        if (_alarmLight != null) return _alarmLight!!
        _alarmLight = ImageVector.Builder(
            name = "Alarm.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1214.2103773584906f,
            viewportHeight = 1214.2103773584906f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -57.87397798742134f, translationY = 980.0261792452831f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(912.0f, -12.0f),
                        PathNode.LineTo(980.0f, -116.0f),
                        PathNode.QuadTo(986.4285714285714f, -126.81818181818181f, 992.1428571428571f, -129.9090909090909f),
                        PathNode.QuadTo(997.8571428571429f, -133.0f, 1008.5714285714286f, -133.0f),
                        PathNode.HorizontalTo(1040.0f),
                        PathNode.QuadTo(1050.0f, -133.0f, 1053.0f, -126.5f),
                        PathNode.QuadTo(1056.0f, -120.0f, 1051.0f, -112.0f),
                        PathNode.LineTo(962.0f, 26.0f),
                        PathNode.QuadTo(1031.8510638297873f, 89.0f, 1070.4255319148938f, 174.5f),
                        PathNode.QuadTo(1109.0f, 260.0f, 1109.0f, 357.0f),
                        PathNode.QuadTo(1109.0f, 478.0f, 1049.4743202416919f, 579.9819277108434f),
                        PathNode.QuadTo(989.9486404833837f, 681.9638554216867f, 887.9743202416919f, 741.9819277108434f),
                        PathNode.QuadTo(786.0f, 802.0f, 665.0f, 802.0f),
                        PathNode.QuadTo(544.0f, 802.0f, 442.0256797583081f, 741.9819277108434f),
                        PathNode.QuadTo(340.0513595166163f, 681.9638554216867f, 280.5256797583081f, 579.9819277108434f),
                        PathNode.QuadTo(221.0f, 478.0f, 221.0f, 357.0f),
                        PathNode.QuadTo(221.0f, 260.0f, 259.31205673758865f, 174.5f),
                        PathNode.QuadTo(297.6241134751773f, 89.0f, 367.0f, 26.0f),
                        PathNode.LineTo(280.0f, -111.0f),
                        PathNode.QuadTo(273.0f, -122.0f, 277.0f, -127.5f),
                        PathNode.QuadTo(281.0f, -133.0f, 290.04918032786884f, -133.0f),
                        PathNode.HorizontalTo(327.0f),
                        PathNode.QuadTo(340.125f, -133.0f, 348.0f, -120.0f),
                        PathNode.LineTo(418.0f, -12.0f),
                        PathNode.QuadTo(528.0f, -88.0f, 665.0f, -88.0f),
                        PathNode.QuadTo(800.0f, -88.0f, 912.0f, -12.0f),
                        PathNode.Close,
                        PathNode.MoveTo(254.0f, 663.0f),
                        PathNode.LineTo(467.0f, 817.0f),
                        PathNode.QuadTo(476.0f, 823.5454545454545f, 476.0f, 829.2727272727273f),
                        PathNode.QuadTo(476.0f, 835.0f, 467.0f, 842.0f),
                        PathNode.QuadTo(450.0f, 855.0f, 431.96610169491527f, 864.0f),
                        PathNode.QuadTo(413.93220338983053f, 873.0f, 394.0f, 877.0f),
                        PathNode.QuadTo(354.0f, 883.0f, 316.5f, 869.0f),
                        PathNode.QuadTo(279.0f, 855.0f, 254.0f, 824.0f),
                        PathNode.QuadTo(229.0f, 793.0f, 223.0f, 753.4819277108434f),
                        PathNode.QuadTo(216.0f, 713.0f, 233.0f, 668.0f),
                        PathNode.QuadTo(237.0f, 660.0f, 241.5f, 659.0f),
                        PathNode.QuadTo(246.0f, 658.0f, 254.0f, 663.0f),
                        PathNode.Close,
                        PathNode.MoveTo(640.0f, 345.0f),
                        PathNode.VerticalTo(655.0f),
                        PathNode.QuadTo(640.0f, 672.0f, 660.0f, 672.0f),
                        PathNode.HorizontalTo(670.0f),
                        PathNode.QuadTo(679.5f, 672.0f, 684.25f, 667.5799999999999f),
                        PathNode.QuadTo(689.0f, 663.16f, 689.0f, 655.0f),
                        PathNode.VerticalTo(376.0f),
                        PathNode.HorizontalTo(908.0f),
                        PathNode.QuadTo(915.8461538461538f, 376.0f, 920.4230769230769f, 371.4230769230769f),
                        PathNode.QuadTo(925.0f, 366.84615384615387f, 925.0f, 359.0f),
                        PathNode.VerticalTo(345.0f),
                        PathNode.QuadTo(925.0f, 338.0f, 920.4230769230769f, 333.0f),
                        PathNode.QuadTo(915.8461538461538f, 328.0f, 908.0f, 328.0f),
                        PathNode.HorizontalTo(658.0f),
                        PathNode.QuadTo(650.0f, 328.0f, 645.0f, 332.41999999999996f),
                        PathNode.QuadTo(640.0f, 336.84f, 640.0f, 345.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1094.0f, 667.0f),
                        PathNode.QuadTo(1113.0f, 711.0f, 1106.1f, 752.5172413793103f),
                        PathNode.QuadTo(1100.1857142857143f, 793.0459770114943f, 1074.557142857143f, 823.6896551724138f),
                        PathNode.QuadTo(1048.9285714285713f, 854.3333333333334f, 1011.9642857142857f, 868.6666666666667f),
                        PathNode.QuadTo(975.0f, 883.0f, 935.0603448275862f, 877.1428571428571f),
                        PathNode.QuadTo(895.1206896551724f, 870.3095238095239f, 862.0f, 842.0f),
                        PathNode.QuadTo(854.0f, 834.0f, 853.0f, 828.75f),
                        PathNode.QuadTo(852.0f, 823.5f, 859.0f, 819.0f),
                        PathNode.LineTo(1078.0f, 661.0f),
                        PathNode.QuadTo(1083.0f, 658.0f, 1087.4f, 659.6363636363636f),
                        PathNode.QuadTo(1091.8f, 661.2727272727273f, 1094.0f, 667.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _alarmLight!!
    }

private var _alarmLight: ImageVector? = null

val MiuixIcons.Regular.Alarm: ImageVector
    get() {
        if (_alarmRegular != null) return _alarmRegular!!
        _alarmRegular = ImageVector.Builder(
            name = "Alarm.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1235.7073170731708f,
            viewportHeight = 1235.7073170731708f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -47.146341463414615f, translationY = 994.7317073170732f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(906.0f, -33.0f),
                        PathNode.LineTo(960.0f, -116.0f),
                        PathNode.QuadTo(969.0f, -130.0f, 977.0f, -134.0f),
                        PathNode.QuadTo(985.0f, -138.0f, 1000.0f, -138.0f),
                        PathNode.HorizontalTo(1044.0f),
                        PathNode.QuadTo(1059.0f, -138.0f, 1064.5f, -129.0f),
                        PathNode.QuadTo(1070.0f, -120.0f, 1063.0f, -109.0f),
                        PathNode.LineTo(978.0f, 22.0f),
                        PathNode.QuadTo(1045.0f, 85.0f, 1082.0f, 170.5f),
                        PathNode.QuadTo(1119.0f, 256.0f, 1119.0f, 352.0f),
                        PathNode.QuadTo(1119.0f, 475.0f, 1058.0f, 579.5f),
                        PathNode.QuadTo(997.0f, 684.0f, 892.5f, 745.5f),
                        PathNode.QuadTo(788.0f, 807.0f, 665.0f, 807.0f),
                        PathNode.QuadTo(542.0f, 807.0f, 437.5f, 745.5f),
                        PathNode.QuadTo(333.0f, 684.0f, 272.0f, 579.5f),
                        PathNode.QuadTo(211.0f, 475.0f, 211.0f, 352.0f),
                        PathNode.QuadTo(211.0f, 256.0f, 248.0f, 170.5f),
                        PathNode.QuadTo(285.0f, 85.0f, 352.0f, 22.0f),
                        PathNode.LineTo(268.0f, -107.0f),
                        PathNode.QuadTo(259.0f, -122.0f, 266.5f, -130.0f),
                        PathNode.QuadTo(274.0f, -138.0f, 286.0f, -138.0f),
                        PathNode.HorizontalTo(335.0f),
                        PathNode.QuadTo(355.0f, -138.0f, 367.0f, -120.0f),
                        PathNode.LineTo(424.0f, -33.0f),
                        PathNode.QuadTo(534.0f, -102.0f, 665.0f, -102.0f),
                        PathNode.QuadTo(796.0f, -102.0f, 906.0f, -33.0f),
                        PathNode.Close,
                        PathNode.MoveTo(250.0f, 671.0f),
                        PathNode.LineTo(462.0f, 824.0f),
                        PathNode.QuadTo(472.0f, 832.0f, 472.0f, 839.0f),
                        PathNode.QuadTo(472.0f, 846.0f, 462.0f, 854.0f),
                        PathNode.QuadTo(446.0f, 868.0f, 427.0f, 877.0f),
                        PathNode.QuadTo(408.0f, 886.0f, 387.0f, 890.0f),
                        PathNode.QuadTo(346.0f, 896.0f, 308.5f, 881.5f),
                        PathNode.QuadTo(271.0f, 867.0f, 245.0f, 836.0f),
                        PathNode.QuadTo(219.0f, 805.0f, 213.0f, 764.0f),
                        PathNode.QuadTo(206.0f, 722.0f, 224.0f, 678.0f),
                        PathNode.QuadTo(228.0f, 668.0f, 234.5f, 666.5f),
                        PathNode.QuadTo(241.0f, 665.0f, 250.0f, 671.0f),
                        PathNode.Close,
                        PathNode.MoveTo(632.0f, 344.0f),
                        PathNode.VerticalTo(635.0f),
                        PathNode.QuadTo(632.0f, 660.0f, 661.0f, 660.0f),
                        PathNode.HorizontalTo(670.0f),
                        PathNode.QuadTo(684.0f, 660.0f, 691.0f, 653.5f),
                        PathNode.QuadTo(698.0f, 647.0f, 698.0f, 635.0f),
                        PathNode.VerticalTo(385.0f),
                        PathNode.HorizontalTo(886.0f),
                        PathNode.QuadTo(898.0f, 385.0f, 905.0f, 378.0f),
                        PathNode.QuadTo(912.0f, 371.0f, 912.0f, 359.0f),
                        PathNode.VerticalTo(345.0f),
                        PathNode.QuadTo(912.0f, 333.0f, 905.0f, 326.0f),
                        PathNode.QuadTo(898.0f, 319.0f, 886.0f, 319.0f),
                        PathNode.HorizontalTo(658.0f),
                        PathNode.QuadTo(645.0f, 319.0f, 638.5f, 325.5f),
                        PathNode.QuadTo(632.0f, 332.0f, 632.0f, 344.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1105.0f, 676.0f),
                        PathNode.QuadTo(1124.0f, 722.0f, 1117.0f, 764.0f),
                        PathNode.QuadTo(1111.0f, 805.0f, 1085.0f, 836.0f),
                        PathNode.QuadTo(1059.0f, 867.0f, 1021.5f, 881.5f),
                        PathNode.QuadTo(984.0f, 896.0f, 943.0f, 890.0f),
                        PathNode.QuadTo(902.0f, 883.0f, 868.0f, 854.0f),
                        PathNode.QuadTo(859.0f, 846.0f, 858.0f, 839.0f),
                        PathNode.QuadTo(857.0f, 832.0f, 866.0f, 826.0f),
                        PathNode.LineTo(1083.0f, 669.0f),
                        PathNode.QuadTo(1090.0f, 665.0f, 1096.0f, 667.0f),
                        PathNode.QuadTo(1102.0f, 669.0f, 1105.0f, 676.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _alarmRegular!!
    }

private var _alarmRegular: ImageVector? = null

val MiuixIcons.Heavy.Alarm: ImageVector
    get() {
        if (_alarmHeavy != null) return _alarmHeavy!!
        _alarmHeavy = ImageVector.Builder(
            name = "Alarm.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1303.3565217391304f,
            viewportHeight = 1303.3565217391304f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -12.85204216073788f, translationY = 1029.7434782608696f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(899.0f, -66.0f),
                        PathNode.LineTo(947.0f, -141.0f),
                        PathNode.QuadTo(957.0f, -155.0f, 966.5f, -160.0f),
                        PathNode.QuadTo(976.0f, -165.0f, 991.0f, -165.0f),
                        PathNode.HorizontalTo(1057.0f),
                        PathNode.QuadTo(1077.0f, -165.0f, 1084.0f, -152.5f),
                        PathNode.QuadTo(1091.0f, -140.0f, 1080.0f, -124.0f),
                        PathNode.LineTo(995.0f, 7.0f),
                        PathNode.QuadTo(1059.0f, 72.0f, 1094.5f, 157.0f),
                        PathNode.QuadTo(1130.0f, 242.0f, 1130.0f, 336.0f),
                        PathNode.QuadTo(1130.0f, 463.0f, 1067.5f, 570.0f),
                        PathNode.QuadTo(1005.0f, 677.0f, 898.0f, 740.0f),
                        PathNode.QuadTo(791.0f, 803.0f, 665.0f, 803.0f),
                        PathNode.QuadTo(538.0f, 803.0f, 431.0f, 740.0f),
                        PathNode.QuadTo(324.0f, 677.0f, 261.5f, 570.0f),
                        PathNode.QuadTo(199.0f, 463.0f, 199.0f, 336.0f),
                        PathNode.QuadTo(199.0f, 242.0f, 234.5f, 157.0f),
                        PathNode.QuadTo(270.0f, 72.0f, 334.0f, 7.0f),
                        PathNode.LineTo(249.0f, -123.0f),
                        PathNode.QuadTo(237.0f, -141.0f, 245.5f, -153.0f),
                        PathNode.QuadTo(254.0f, -165.0f, 273.0f, -165.0f),
                        PathNode.HorizontalTo(341.0f),
                        PathNode.QuadTo(365.0f, -165.0f, 380.0f, -143.0f),
                        PathNode.LineTo(431.0f, -66.0f),
                        PathNode.QuadTo(540.0f, -129.0f, 665.0f, -129.0f),
                        PathNode.QuadTo(790.0f, -129.0f, 899.0f, -66.0f),
                        PathNode.Close,
                        PathNode.MoveTo(243.0f, 669.0f),
                        PathNode.LineTo(464.0f, 829.0f),
                        PathNode.QuadTo(477.0f, 838.0f, 478.5f, 848.0f),
                        PathNode.QuadTo(480.0f, 858.0f, 470.0f, 870.0f),
                        PathNode.QuadTo(453.0f, 888.0f, 429.0f, 901.0f),
                        PathNode.QuadTo(405.0f, 914.0f, 378.0f, 919.0f),
                        PathNode.QuadTo(333.0f, 926.0f, 292.0f, 910.0f),
                        PathNode.QuadTo(251.0f, 894.0f, 222.5f, 860.0f),
                        PathNode.QuadTo(194.0f, 826.0f, 187.0f, 781.0f),
                        PathNode.QuadTo(179.0f, 732.0f, 204.0f, 678.0f),
                        PathNode.QuadTo(211.0f, 664.0f, 221.0f, 662.0f),
                        PathNode.QuadTo(231.0f, 660.0f, 243.0f, 669.0f),
                        PathNode.Close,
                        PathNode.MoveTo(618.0f, 328.0f),
                        PathNode.VerticalTo(619.0f),
                        PathNode.QuadTo(618.0f, 650.0f, 652.0f, 650.0f),
                        PathNode.HorizontalTo(677.0f),
                        PathNode.QuadTo(693.0f, 650.0f, 701.5f, 642.0f),
                        PathNode.QuadTo(710.0f, 634.0f, 710.0f, 619.0f),
                        PathNode.VerticalTo(382.0f),
                        PathNode.HorizontalTo(886.0f),
                        PathNode.QuadTo(901.0f, 382.0f, 909.5f, 373.5f),
                        PathNode.QuadTo(918.0f, 365.0f, 918.0f, 350.0f),
                        PathNode.VerticalTo(323.0f),
                        PathNode.QuadTo(918.0f, 307.0f, 909.5f, 299.0f),
                        PathNode.QuadTo(901.0f, 291.0f, 886.0f, 291.0f),
                        PathNode.HorizontalTo(657.0f),
                        PathNode.QuadTo(639.0f, 291.0f, 628.5f, 301.0f),
                        PathNode.QuadTo(618.0f, 311.0f, 618.0f, 328.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1126.0f, 678.0f),
                        PathNode.QuadTo(1150.0f, 733.0f, 1142.0f, 781.0f),
                        PathNode.QuadTo(1135.0f, 826.0f, 1107.0f, 860.0f),
                        PathNode.QuadTo(1079.0f, 894.0f, 1037.5f, 910.0f),
                        PathNode.QuadTo(996.0f, 926.0f, 951.0f, 919.0f),
                        PathNode.QuadTo(896.0f, 909.0f, 859.0f, 870.0f),
                        PathNode.QuadTo(849.0f, 859.0f, 850.5f, 848.5f),
                        PathNode.QuadTo(852.0f, 838.0f, 864.0f, 830.0f),
                        PathNode.LineTo(1088.0f, 668.0f),
                        PathNode.QuadTo(1099.0f, 660.0f, 1109.5f, 662.5f),
                        PathNode.QuadTo(1120.0f, 665.0f, 1126.0f, 678.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _alarmHeavy!!
    }

private var _alarmHeavy: ImageVector? = null
