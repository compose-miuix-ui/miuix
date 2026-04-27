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

val MiuixIcons.Reset: ImageVector
    get() = MiuixIcons.Regular.Reset

val MiuixIcons.Light.Reset: ImageVector
    get() {
        if (_resetLight != null) return _resetLight!!
        _resetLight = ImageVector.Builder(
            name = "Reset.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1298.4f,
            viewportHeight = 1298.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1298.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(118.2f, 820.2f),
                        PathNode.LineTo(393.2f, 546.2f),
                        PathNode.QuadTo(400.2f, 539.2f, 407.2f, 539.2f),
                        PathNode.QuadTo(414.2f, 539.2f, 421.2f, 546.2f),
                        PathNode.LineTo(434.2f, 559.2f),
                        PathNode.QuadTo(441.2f, 566.2f, 441.2f, 573.2f),
                        PathNode.QuadTo(441.2f, 580.2f, 434.2f, 587.2f),
                        PathNode.LineTo(206.2f, 814.2f),
                        PathNode.HorizontalTo(828.2f),
                        PathNode.QuadTo(912.2f, 814.2f, 982.2f, 771.7f),
                        PathNode.QuadTo(1052.2f, 729.2f, 1091.7f, 657.2f),
                        PathNode.QuadTo(1131.2f, 585.2f, 1128.2f, 501.2f),
                        PathNode.QuadTo(1125.2f, 420.2f, 1084.2f, 353.7f),
                        PathNode.QuadTo(1043.2f, 287.2f, 974.7f, 248.7f),
                        PathNode.QuadTo(906.2f, 210.2f, 826.2f, 210.2f),
                        PathNode.HorizontalTo(323.2f),
                        PathNode.QuadTo(314.2f, 210.2f, 308.7f, 204.7f),
                        PathNode.QuadTo(303.2f, 199.2f, 303.2f, 190.2f),
                        PathNode.VerticalTo(170.2f),
                        PathNode.QuadTo(303.2f, 161.2f, 308.7f, 155.7f),
                        PathNode.QuadTo(314.2f, 150.2f, 323.2f, 150.2f),
                        PathNode.HorizontalTo(826.2f),
                        PathNode.QuadTo(921.2f, 150.2f, 1003.2f, 196.7f),
                        PathNode.QuadTo(1085.2f, 243.2f, 1134.7f, 322.7f),
                        PathNode.QuadTo(1184.2f, 402.2f, 1187.2f, 497.2f),
                        PathNode.QuadTo(1190.2f, 598.2f, 1143.2f, 685.2f),
                        PathNode.QuadTo(1096.2f, 772.2f, 1012.2f, 823.2f),
                        PathNode.QuadTo(928.2f, 874.2f, 828.2f, 874.2f),
                        PathNode.HorizontalTo(206.2f),
                        PathNode.LineTo(434.2f, 1100.2f),
                        PathNode.QuadTo(441.2f, 1107.2f, 440.7f, 1114.2f),
                        PathNode.QuadTo(440.2f, 1121.2f, 433.2f, 1128.2f),
                        PathNode.LineTo(419.2f, 1141.2f),
                        PathNode.QuadTo(413.2f, 1148.2f, 406.2f, 1148.2f),
                        PathNode.QuadTo(399.2f, 1148.2f, 392.2f, 1141.2f),
                        PathNode.LineTo(118.2f, 869.2f),
                        PathNode.QuadTo(108.2f, 859.2f, 108.2f, 845.2f),
                        PathNode.QuadTo(108.2f, 831.2f, 118.2f, 820.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _resetLight!!
    }

private var _resetLight: ImageVector? = null

val MiuixIcons.Normal.Reset: ImageVector
    get() {
        if (_resetNormal != null) return _resetNormal!!
        _resetNormal = ImageVector.Builder(
            name = "Reset.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1321.5f,
            viewportHeight = 1321.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1321.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(122.9f, 824.9f),
                        PathNode.LineTo(398.6f, 549.5f),
                        PathNode.QuadTo(407.6f, 540.4f, 417.4f, 540.4f),
                        PathNode.QuadTo(427.1f, 540.4f, 436.2f, 549.5f),
                        PathNode.LineTo(452.6f, 565.9f),
                        PathNode.QuadTo(462.4f, 575.0f, 462.4f, 584.8f),
                        PathNode.QuadTo(462.4f, 594.5f, 453.3f, 603.6f),
                        PathNode.LineTo(240.4f, 816.1f),
                        PathNode.HorizontalTo(839.1f),
                        PathNode.QuadTo(920.3f, 816.1f, 988.6f, 775.0f),
                        PathNode.QuadTo(1056.9f, 733.9f, 1095.3f, 663.6f),
                        PathNode.QuadTo(1133.8f, 593.3f, 1130.8f, 512.1f),
                        PathNode.QuadTo(1127.8f, 433.8f, 1087.8f, 369.4f),
                        PathNode.QuadTo(1047.9f, 304.9f, 981.4f, 267.5f),
                        PathNode.QuadTo(915.0f, 230.0f, 837.1f, 230.0f),
                        PathNode.HorizontalTo(338.2f),
                        PathNode.QuadTo(325.8f, 230.0f, 318.5f, 222.8f),
                        PathNode.QuadTo(311.3f, 215.6f, 311.3f, 203.1f),
                        PathNode.VerticalTo(179.0f),
                        PathNode.QuadTo(311.3f, 166.6f, 318.5f, 159.3f),
                        PathNode.QuadTo(325.8f, 152.1f, 338.2f, 152.1f),
                        PathNode.HorizontalTo(837.1f),
                        PathNode.QuadTo(934.8f, 152.1f, 1018.9f, 199.7f),
                        PathNode.QuadTo(1102.9f, 247.2f, 1153.5f, 328.8f),
                        PathNode.QuadTo(1204.0f, 410.3f, 1207.7f, 508.1f),
                        PathNode.QuadTo(1211.4f, 611.8f, 1162.7f, 700.5f),
                        PathNode.QuadTo(1113.9f, 789.2f, 1027.9f, 841.6f),
                        PathNode.QuadTo(941.8f, 894.0f, 839.1f, 894.0f),
                        PathNode.HorizontalTo(239.8f),
                        PathNode.LineTo(453.3f, 1106.2f),
                        PathNode.QuadTo(462.4f, 1115.3f, 462.2f, 1125.1f),
                        PathNode.QuadTo(462.1f, 1134.8f, 452.3f, 1143.9f),
                        PathNode.LineTo(435.6f, 1160.3f),
                        PathNode.QuadTo(426.8f, 1169.4f, 417.1f, 1169.4f),
                        PathNode.QuadTo(407.3f, 1169.4f, 398.2f, 1160.3f),
                        PathNode.LineTo(122.9f, 886.2f),
                        PathNode.QuadTo(110.1f, 873.5f, 110.1f, 855.7f),
                        PathNode.QuadTo(110.1f, 837.9f, 122.9f, 824.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _resetNormal!!
    }

private var _resetNormal: ImageVector? = null

val MiuixIcons.Regular.Reset: ImageVector
    get() {
        if (_resetRegular != null) return _resetRegular!!
        _resetRegular = ImageVector.Builder(
            name = "Reset.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1332.0f,
            viewportHeight = 1332.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1332.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(125.0f, 827.0f),
                        PathNode.LineTo(401.0f, 551.0f),
                        PathNode.QuadTo(411.0f, 541.0f, 422.0f, 541.0f),
                        PathNode.QuadTo(433.0f, 541.0f, 443.0f, 551.0f),
                        PathNode.LineTo(461.0f, 569.0f),
                        PathNode.QuadTo(472.0f, 579.0f, 472.0f, 590.0f),
                        PathNode.QuadTo(472.0f, 601.0f, 462.0f, 611.0f),
                        PathNode.LineTo(256.0f, 817.0f),
                        PathNode.HorizontalTo(844.0f),
                        PathNode.QuadTo(924.0f, 817.0f, 991.5f, 776.5f),
                        PathNode.QuadTo(1059.0f, 736.0f, 1097.0f, 666.5f),
                        PathNode.QuadTo(1135.0f, 597.0f, 1132.0f, 517.0f),
                        PathNode.QuadTo(1129.0f, 440.0f, 1089.5f, 376.5f),
                        PathNode.QuadTo(1050.0f, 313.0f, 984.5f, 276.0f),
                        PathNode.QuadTo(919.0f, 239.0f, 842.0f, 239.0f),
                        PathNode.HorizontalTo(345.0f),
                        PathNode.QuadTo(331.0f, 239.0f, 323.0f, 231.0f),
                        PathNode.QuadTo(315.0f, 223.0f, 315.0f, 209.0f),
                        PathNode.VerticalTo(183.0f),
                        PathNode.QuadTo(315.0f, 169.0f, 323.0f, 161.0f),
                        PathNode.QuadTo(331.0f, 153.0f, 345.0f, 153.0f),
                        PathNode.HorizontalTo(842.0f),
                        PathNode.QuadTo(941.0f, 153.0f, 1026.0f, 201.0f),
                        PathNode.QuadTo(1111.0f, 249.0f, 1162.0f, 331.5f),
                        PathNode.QuadTo(1213.0f, 414.0f, 1217.0f, 513.0f),
                        PathNode.QuadTo(1221.0f, 618.0f, 1171.5f, 707.5f),
                        PathNode.QuadTo(1122.0f, 797.0f, 1035.0f, 850.0f),
                        PathNode.QuadTo(948.0f, 903.0f, 844.0f, 903.0f),
                        PathNode.HorizontalTo(255.0f),
                        PathNode.LineTo(462.0f, 1109.0f),
                        PathNode.QuadTo(472.0f, 1119.0f, 472.0f, 1130.0f),
                        PathNode.QuadTo(472.0f, 1141.0f, 461.0f, 1151.0f),
                        PathNode.LineTo(443.0f, 1169.0f),
                        PathNode.QuadTo(433.0f, 1179.0f, 422.0f, 1179.0f),
                        PathNode.QuadTo(411.0f, 1179.0f, 401.0f, 1169.0f),
                        PathNode.LineTo(125.0f, 894.0f),
                        PathNode.QuadTo(111.0f, 880.0f, 111.0f, 860.5f),
                        PathNode.QuadTo(111.0f, 841.0f, 125.0f, 827.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _resetRegular!!
    }

private var _resetRegular: ImageVector? = null

val MiuixIcons.Medium.Reset: ImageVector
    get() {
        if (_resetMedium != null) return _resetMedium!!
        _resetMedium = ImageVector.Builder(
            name = "Reset.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1358.8f,
            viewportHeight = 1358.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1358.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(130.8f, 833.1f),
                        PathNode.LineTo(406.8f, 557.1f),
                        PathNode.QuadTo(419.1f, 544.1f, 435.1f, 544.1f),
                        PathNode.QuadTo(451.1f, 544.1f, 464.6f, 557.1f),
                        PathNode.LineTo(482.6f, 575.1f),
                        PathNode.QuadTo(496.6f, 587.4f, 496.6f, 602.5f),
                        PathNode.QuadTo(496.6f, 617.6f, 483.1f, 631.2f),
                        PathNode.LineTo(294.1f, 820.1f),
                        PathNode.HorizontalTo(857.4f),
                        PathNode.QuadTo(934.5f, 820.1f, 999.3f, 780.8f),
                        PathNode.QuadTo(1064.2f, 741.5f, 1100.7f, 674.9f),
                        PathNode.QuadTo(1137.2f, 608.4f, 1134.2f, 530.7f),
                        PathNode.QuadTo(1131.8f, 456.6f, 1093.8f, 395.2f),
                        PathNode.QuadTo(1055.8f, 333.8f, 992.6f, 297.9f),
                        PathNode.QuadTo(929.5f, 262.1f, 855.4f, 262.1f),
                        PathNode.HorizontalTo(358.4f),
                        PathNode.QuadTo(339.7f, 262.1f, 328.5f, 251.5f),
                        PathNode.QuadTo(317.2f, 240.8f, 317.2f, 222.1f),
                        PathNode.VerticalTo(196.1f),
                        PathNode.QuadTo(317.2f, 177.4f, 328.5f, 166.8f),
                        PathNode.QuadTo(339.7f, 156.1f, 358.4f, 156.1f),
                        PathNode.HorizontalTo(855.4f),
                        PathNode.QuadTo(957.9f, 156.1f, 1045.0f, 205.3f),
                        PathNode.QuadTo(1132.1f, 254.5f, 1184.8f, 339.3f),
                        PathNode.QuadTo(1237.6f, 424.2f, 1241.6f, 526.1f),
                        PathNode.QuadTo(1245.6f, 633.5f, 1194.9f, 725.3f),
                        PathNode.QuadTo(1144.2f, 817.2f, 1054.6f, 871.6f),
                        PathNode.QuadTo(964.9f, 926.1f, 857.4f, 926.1f),
                        PathNode.HorizontalTo(293.1f),
                        PathNode.LineTo(483.1f, 1115.1f),
                        PathNode.QuadTo(496.6f, 1128.6f, 496.6f, 1143.7f),
                        PathNode.QuadTo(496.6f, 1158.8f, 482.6f, 1171.2f),
                        PathNode.LineTo(464.1f, 1189.2f),
                        PathNode.QuadTo(451.1f, 1202.1f, 435.7f, 1202.4f),
                        PathNode.QuadTo(420.3f, 1202.7f, 406.8f, 1189.2f),
                        PathNode.LineTo(130.8f, 914.2f),
                        PathNode.QuadTo(113.2f, 897.2f, 113.2f, 873.9f),
                        PathNode.QuadTo(113.2f, 850.6f, 130.8f, 833.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _resetMedium!!
    }

private var _resetMedium: ImageVector? = null

val MiuixIcons.Demibold.Reset: ImageVector
    get() {
        if (_resetDemibold != null) return _resetDemibold!!
        _resetDemibold = ImageVector.Builder(
            name = "Reset.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1377.6f,
            viewportHeight = 1377.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1377.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(134.8f, 837.3f),
                        PathNode.LineTo(410.8f, 561.3f),
                        PathNode.QuadTo(424.8f, 546.3f, 444.3f, 546.3f),
                        PathNode.QuadTo(463.8f, 546.3f, 479.8f, 561.3f),
                        PathNode.LineTo(497.8f, 579.3f),
                        PathNode.QuadTo(513.8f, 593.3f, 513.8f, 611.3f),
                        PathNode.QuadTo(513.8f, 629.3f, 497.8f, 645.3f),
                        PathNode.LineTo(320.8f, 822.3f),
                        PathNode.HorizontalTo(866.8f),
                        PathNode.QuadTo(941.8f, 822.3f, 1004.8f, 783.8f),
                        PathNode.QuadTo(1067.8f, 745.3f, 1103.3f, 680.8f),
                        PathNode.QuadTo(1138.8f, 616.3f, 1135.8f, 540.3f),
                        PathNode.QuadTo(1133.8f, 468.3f, 1096.8f, 408.3f),
                        PathNode.QuadTo(1059.8f, 348.3f, 998.3f, 313.3f),
                        PathNode.QuadTo(936.8f, 278.3f, 864.8f, 278.3f),
                        PathNode.HorizontalTo(367.8f),
                        PathNode.QuadTo(345.8f, 278.3f, 332.3f, 265.8f),
                        PathNode.QuadTo(318.8f, 253.3f, 318.8f, 231.3f),
                        PathNode.VerticalTo(205.3f),
                        PathNode.QuadTo(318.8f, 183.3f, 332.3f, 170.8f),
                        PathNode.QuadTo(345.8f, 158.3f, 367.8f, 158.3f),
                        PathNode.HorizontalTo(864.8f),
                        PathNode.QuadTo(969.8f, 158.3f, 1058.3f, 208.3f),
                        PathNode.QuadTo(1146.8f, 258.3f, 1200.8f, 344.8f),
                        PathNode.QuadTo(1254.8f, 431.3f, 1258.8f, 535.3f),
                        PathNode.QuadTo(1262.8f, 644.3f, 1211.3f, 737.8f),
                        PathNode.QuadTo(1159.8f, 831.3f, 1068.3f, 886.8f),
                        PathNode.QuadTo(976.8f, 942.3f, 866.8f, 942.3f),
                        PathNode.HorizontalTo(319.8f),
                        PathNode.LineTo(497.8f, 1119.3f),
                        PathNode.QuadTo(513.8f, 1135.3f, 513.8f, 1153.3f),
                        PathNode.QuadTo(513.8f, 1171.3f, 497.8f, 1185.3f),
                        PathNode.LineTo(478.8f, 1203.3f),
                        PathNode.QuadTo(463.8f, 1218.3f, 445.3f, 1218.8f),
                        PathNode.QuadTo(426.8f, 1219.3f, 410.8f, 1203.3f),
                        PathNode.LineTo(134.8f, 928.3f),
                        PathNode.QuadTo(114.8f, 909.3f, 114.8f, 883.3f),
                        PathNode.QuadTo(114.8f, 857.3f, 134.8f, 837.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _resetDemibold!!
    }

private var _resetDemibold: ImageVector? = null
