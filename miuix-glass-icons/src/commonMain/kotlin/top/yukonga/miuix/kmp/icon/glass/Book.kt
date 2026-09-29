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

val MiuixIcons.Glass.Book: ImageVector
    get() = MiuixIcons.Glass.Regular.Book

val MiuixIcons.Glass.Light.Book: ImageVector
    get() {
        if (_bookLight != null) return _bookLight!!
        _bookLight = ImageVector.Builder(
            name = "Book.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(541.3f, 160.8f),
                        PathNode.LineTo(541.3f, 848.5f),
                        PathNode.QuadTo(541.3f, 898.2f, 514.0f, 925.9f),
                        PathNode.QuadTo(452.9f, 985.0f, 387.2f, 1004.8f),
                        PathNode.QuadTo(321.5f, 1024.7f, 218.4f, 1024.7f),
                        PathNode.QuadTo(174.4f, 1024.7f, 155.8f, 1023.2f),
                        PathNode.QuadTo(137.2f, 1021.7f, 124.0f, 1015.1f),
                        PathNode.QuadTo(96.8f, 1001.5f, 82.3f, 973.8f),
                        PathNode.QuadTo(76.3f, 960.7f, 75.0f, 942.3f),
                        PathNode.QuadTo(73.7f, 924.0f, 73.7f, 880.5f),
                        PathNode.LineTo(73.7f, 295.5f),
                        PathNode.QuadTo(73.7f, 278.3f, 74.7f, 273.3f),
                        PathNode.QuadTo(77.3f, 241.6f, 99.7f, 219.5f),
                        PathNode.QuadTo(122.0f, 197.3f, 154.3f, 193.8f),
                        PathNode.QuadTo(158.8f, 192.8f, 177.4f, 192.8f),
                        PathNode.LineTo(276.5f, 192.8f),
                        PathNode.QuadTo(330.4f, 192.8f, 381.1f, 175.8f),
                        PathNode.QuadTo(431.8f, 158.8f, 473.8f, 126.3f),
                        PathNode.QuadTo(487.5f, 116.7f, 503.5f, 118.5f),
                        PathNode.QuadTo(519.5f, 120.2f, 530.4f, 131.9f),
                        PathNode.QuadTo(541.3f, 143.6f, 541.3f, 160.8f),
                        PathNode.Close,
                        PathNode.MoveTo(866.4f, 192.8f),
                        PathNode.LineTo(966.5f, 192.8f),
                        PathNode.QuadTo(985.0f, 192.8f, 989.6f, 193.8f),
                        PathNode.QuadTo(1020.8f, 197.3f, 1043.0f, 219.2f),
                        PathNode.QuadTo(1065.2f, 241.1f, 1069.2f, 273.3f),
                        PathNode.LineTo(1069.2f, 295.5f),
                        PathNode.LineTo(1069.2f, 880.5f),
                        PathNode.QuadTo(1069.2f, 924.0f, 1067.9f, 942.3f),
                        PathNode.QuadTo(1066.6f, 960.7f, 1060.6f, 973.8f),
                        PathNode.QuadTo(1046.0f, 1001.5f, 1018.4f, 1015.1f),
                        PathNode.QuadTo(1006.2f, 1021.7f, 987.8f, 1023.2f),
                        PathNode.QuadTo(969.5f, 1024.7f, 924.5f, 1024.7f),
                        PathNode.QuadTo(821.4f, 1024.7f, 755.7f, 1004.8f),
                        PathNode.QuadTo(689.9f, 985.0f, 628.8f, 925.9f),
                        PathNode.QuadTo(613.7f, 910.8f, 608.2f, 891.9f),
                        PathNode.QuadTo(602.6f, 873.0f, 602.6f, 848.5f),
                        PathNode.LineTo(602.6f, 160.8f),
                        PathNode.QuadTo(602.6f, 143.6f, 613.5f, 131.9f),
                        PathNode.QuadTo(624.4f, 120.2f, 640.1f, 118.5f),
                        PathNode.QuadTo(655.8f, 116.7f, 669.0f, 126.3f),
                        PathNode.QuadTo(712.0f, 158.8f, 762.3f, 175.8f),
                        PathNode.QuadTo(812.5f, 192.8f, 866.4f, 192.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bookLight!!
    }

private var _bookLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Book: ImageVector
    get() {
        if (_bookNormal != null) return _bookNormal!!
        _bookNormal = ImageVector.Builder(
            name = "Book.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(536.2f, 158.6f),
                        PathNode.LineTo(536.2f, 849.9f),
                        PathNode.QuadTo(536.2f, 901.2f, 507.8f, 929.8f),
                        PathNode.QuadTo(446.1f, 989.5f, 379.1f, 1009.8f),
                        PathNode.QuadTo(312.2f, 1030.1f, 208.4f, 1030.1f),
                        PathNode.QuadTo(164.4f, 1030.1f, 145.2f, 1028.6f),
                        PathNode.QuadTo(126.0f, 1027.1f, 112.3f, 1020.3f),
                        PathNode.QuadTo(83.8f, 1005.7f, 69.0f, 977.1f),
                        PathNode.QuadTo(63.0f, 963.4f, 61.5f, 944.6f),
                        PathNode.QuadTo(60.1f, 925.8f, 60.1f, 881.9f),
                        PathNode.LineTo(60.1f, 294.2f),
                        PathNode.QuadTo(60.1f, 276.4f, 61.1f, 271.4f),
                        PathNode.QuadTo(64.0f, 238.1f, 87.1f, 215.4f),
                        PathNode.QuadTo(110.3f, 192.7f, 143.7f, 188.8f),
                        PathNode.QuadTo(148.6f, 187.8f, 167.4f, 187.8f),
                        PathNode.LineTo(267.2f, 187.8f),
                        PathNode.QuadTo(320.4f, 187.8f, 371.0f, 170.8f),
                        PathNode.QuadTo(421.6f, 153.8f, 463.6f, 121.7f),
                        PathNode.QuadTo(478.2f, 111.1f, 495.5f, 113.0f),
                        PathNode.QuadTo(512.9f, 115.0f, 524.6f, 127.6f),
                        PathNode.QuadTo(536.2f, 140.1f, 536.2f, 158.6f),
                        PathNode.Close,
                        PathNode.MoveTo(875.7f, 187.8f),
                        PathNode.LineTo(976.4f, 187.8f),
                        PathNode.QuadTo(995.3f, 187.8f, 1000.1f, 188.8f),
                        PathNode.QuadTo(1032.6f, 192.7f, 1055.7f, 215.3f),
                        PathNode.QuadTo(1078.7f, 238.0f, 1082.7f, 271.4f),
                        PathNode.LineTo(1082.7f, 294.2f),
                        PathNode.LineTo(1082.7f, 881.9f),
                        PathNode.QuadTo(1082.7f, 925.8f, 1081.3f, 944.6f),
                        PathNode.QuadTo(1079.9f, 963.4f, 1073.9f, 977.1f),
                        PathNode.QuadTo(1059.0f, 1005.7f, 1030.4f, 1020.3f),
                        PathNode.QuadTo(1017.0f, 1027.1f, 998.2f, 1028.6f),
                        PathNode.QuadTo(979.4f, 1030.1f, 934.4f, 1030.1f),
                        PathNode.QuadTo(830.7f, 1030.1f, 763.7f, 1009.8f),
                        PathNode.QuadTo(696.8f, 989.5f, 635.1f, 929.8f),
                        PathNode.QuadTo(619.4f, 914.1f, 613.5f, 894.4f),
                        PathNode.QuadTo(607.6f, 874.8f, 607.6f, 849.9f),
                        PathNode.LineTo(607.6f, 158.6f),
                        PathNode.QuadTo(607.6f, 140.1f, 619.3f, 127.6f),
                        PathNode.QuadTo(630.9f, 115.0f, 647.9f, 113.0f),
                        PathNode.QuadTo(664.8f, 111.1f, 679.3f, 121.7f),
                        PathNode.QuadTo(722.3f, 153.8f, 772.3f, 170.8f),
                        PathNode.QuadTo(822.4f, 187.8f, 875.7f, 187.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bookNormal!!
    }

private var _bookNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Book: ImageVector
    get() {
        if (_bookRegular != null) return _bookRegular!!
        _bookRegular = ImageVector.Builder(
            name = "Book.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(533.9f, 157.6f),
                        PathNode.LineTo(533.9f, 850.6f),
                        PathNode.QuadTo(533.9f, 902.6f, 504.9f, 931.6f),
                        PathNode.QuadTo(442.9f, 991.6f, 375.4f, 1012.1f),
                        PathNode.QuadTo(307.9f, 1032.6f, 203.9f, 1032.6f),
                        PathNode.QuadTo(159.9f, 1032.6f, 140.4f, 1031.1f),
                        PathNode.QuadTo(120.9f, 1029.6f, 106.9f, 1022.6f),
                        PathNode.QuadTo(77.9f, 1007.6f, 62.9f, 978.6f),
                        PathNode.QuadTo(56.9f, 964.6f, 55.4f, 945.6f),
                        PathNode.QuadTo(53.9f, 926.6f, 53.9f, 882.6f),
                        PathNode.LineTo(53.9f, 293.6f),
                        PathNode.QuadTo(53.9f, 275.6f, 54.9f, 270.6f),
                        PathNode.QuadTo(57.9f, 236.6f, 81.4f, 213.6f),
                        PathNode.QuadTo(104.9f, 190.6f, 138.9f, 186.6f),
                        PathNode.QuadTo(143.9f, 185.6f, 162.9f, 185.6f),
                        PathNode.LineTo(262.9f, 185.6f),
                        PathNode.QuadTo(315.9f, 185.6f, 366.4f, 168.6f),
                        PathNode.QuadTo(416.9f, 151.6f, 458.9f, 119.6f),
                        PathNode.QuadTo(473.9f, 108.6f, 491.9f, 110.6f),
                        PathNode.QuadTo(509.9f, 112.6f, 521.9f, 125.6f),
                        PathNode.QuadTo(533.9f, 138.6f, 533.9f, 157.6f),
                        PathNode.Close,
                        PathNode.MoveTo(879.9f, 185.6f),
                        PathNode.LineTo(980.9f, 185.6f),
                        PathNode.QuadTo(999.9f, 185.6f, 1004.9f, 186.6f),
                        PathNode.QuadTo(1037.9f, 190.6f, 1061.4f, 213.6f),
                        PathNode.QuadTo(1084.9f, 236.6f, 1088.9f, 270.6f),
                        PathNode.LineTo(1088.9f, 293.6f),
                        PathNode.LineTo(1088.9f, 882.6f),
                        PathNode.QuadTo(1088.9f, 926.6f, 1087.4f, 945.6f),
                        PathNode.QuadTo(1085.9f, 964.6f, 1079.9f, 978.6f),
                        PathNode.QuadTo(1064.9f, 1007.6f, 1035.9f, 1022.6f),
                        PathNode.QuadTo(1021.9f, 1029.6f, 1002.9f, 1031.1f),
                        PathNode.QuadTo(983.9f, 1032.6f, 938.9f, 1032.6f),
                        PathNode.QuadTo(834.9f, 1032.6f, 767.4f, 1012.1f),
                        PathNode.QuadTo(699.9f, 991.6f, 637.9f, 931.6f),
                        PathNode.QuadTo(621.9f, 915.6f, 615.9f, 895.6f),
                        PathNode.QuadTo(609.9f, 875.6f, 609.9f, 850.6f),
                        PathNode.LineTo(609.9f, 157.6f),
                        PathNode.QuadTo(609.9f, 138.6f, 621.9f, 125.6f),
                        PathNode.QuadTo(633.9f, 112.6f, 651.4f, 110.6f),
                        PathNode.QuadTo(668.9f, 108.6f, 683.9f, 119.6f),
                        PathNode.QuadTo(726.9f, 151.6f, 776.9f, 168.6f),
                        PathNode.QuadTo(826.9f, 185.6f, 879.9f, 185.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bookRegular!!
    }

private var _bookRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Book: ImageVector
    get() {
        if (_bookMedium != null) return _bookMedium!!
        _bookMedium = ImageVector.Builder(
            name = "Book.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(531.2f, 157.5f),
                        PathNode.LineTo(531.2f, 850.5f),
                        PathNode.QuadTo(531.2f, 904.7f, 500.9f, 935.0f),
                        PathNode.QuadTo(438.3f, 995.6f, 370.8f, 1016.5f),
                        PathNode.QuadTo(303.3f, 1037.4f, 203.9f, 1037.4f),
                        PathNode.QuadTo(159.7f, 1037.4f, 139.6f, 1035.8f),
                        PathNode.QuadTo(119.6f, 1034.1f, 104.8f, 1026.8f),
                        PathNode.QuadTo(73.9f, 1010.8f, 58.6f, 980.7f),
                        PathNode.QuadTo(52.3f, 966.1f, 50.7f, 946.5f),
                        PathNode.QuadTo(49.1f, 926.8f, 49.1f, 882.5f),
                        PathNode.LineTo(49.1f, 293.5f),
                        PathNode.QuadTo(49.1f, 275.3f, 50.1f, 270.0f),
                        PathNode.QuadTo(53.3f, 234.6f, 78.1f, 210.3f),
                        PathNode.QuadTo(102.8f, 185.9f, 138.1f, 181.7f),
                        PathNode.QuadTo(143.7f, 180.7f, 162.9f, 180.7f),
                        PathNode.LineTo(258.1f, 180.7f),
                        PathNode.QuadTo(308.6f, 180.7f, 357.9f, 163.9f),
                        PathNode.QuadTo(407.2f, 147.2f, 448.4f, 115.7f),
                        PathNode.QuadTo(465.0f, 103.7f, 484.8f, 105.8f),
                        PathNode.QuadTo(504.5f, 107.9f, 517.9f, 122.3f),
                        PathNode.QuadTo(531.2f, 136.6f, 531.2f, 157.5f),
                        PathNode.Close,
                        PathNode.MoveTo(884.8f, 180.7f),
                        PathNode.LineTo(980.9f, 180.7f),
                        PathNode.QuadTo(1000.2f, 180.7f, 1005.7f, 181.7f),
                        PathNode.QuadTo(1040.4f, 185.9f, 1065.1f, 210.3f),
                        PathNode.QuadTo(1089.8f, 234.6f, 1093.8f, 270.3f),
                        PathNode.LineTo(1093.8f, 293.5f),
                        PathNode.LineTo(1093.8f, 882.5f),
                        PathNode.QuadTo(1093.8f, 926.8f, 1092.3f, 946.5f),
                        PathNode.QuadTo(1090.8f, 966.1f, 1084.3f, 980.7f),
                        PathNode.QuadTo(1069.0f, 1010.8f, 1038.1f, 1026.8f),
                        PathNode.QuadTo(1023.6f, 1034.1f, 1003.7f, 1035.8f),
                        PathNode.QuadTo(983.9f, 1037.4f, 938.4f, 1037.4f),
                        PathNode.QuadTo(839.3f, 1037.4f, 772.0f, 1016.3f),
                        PathNode.QuadTo(704.8f, 995.3f, 642.3f, 934.8f),
                        PathNode.QuadTo(625.4f, 918.2f, 619.0f, 897.3f),
                        PathNode.QuadTo(612.6f, 876.3f, 612.6f, 850.5f),
                        PathNode.LineTo(612.6f, 157.5f),
                        PathNode.QuadTo(612.6f, 136.6f, 626.0f, 122.3f),
                        PathNode.QuadTo(639.3f, 107.9f, 658.7f, 105.8f),
                        PathNode.QuadTo(678.1f, 103.7f, 694.5f, 115.7f),
                        PathNode.QuadTo(736.7f, 147.2f, 785.4f, 163.9f),
                        PathNode.QuadTo(834.2f, 180.7f, 884.8f, 180.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bookMedium!!
    }

private var _bookMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Book: ImageVector
    get() {
        if (_bookDemibold != null) return _bookDemibold!!
        _bookDemibold = ImageVector.Builder(
            name = "Book.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(529.3f, 157.5f),
                        PathNode.LineTo(529.3f, 850.5f),
                        PathNode.QuadTo(529.3f, 906.2f, 498.0f, 937.5f),
                        PathNode.QuadTo(435.1f, 998.4f, 367.6f, 1019.6f),
                        PathNode.QuadTo(300.1f, 1040.7f, 203.9f, 1040.7f),
                        PathNode.QuadTo(159.5f, 1040.7f, 139.1f, 1039.0f),
                        PathNode.QuadTo(118.6f, 1037.3f, 103.3f, 1029.8f),
                        PathNode.QuadTo(71.0f, 1013.0f, 55.6f, 982.2f),
                        PathNode.QuadTo(49.1f, 967.2f, 47.4f, 947.1f),
                        PathNode.QuadTo(45.7f, 926.9f, 45.7f, 882.5f),
                        PathNode.LineTo(45.7f, 293.5f),
                        PathNode.QuadTo(45.7f, 275.0f, 46.7f, 269.6f),
                        PathNode.QuadTo(50.1f, 233.3f, 75.7f, 208.0f),
                        PathNode.QuadTo(101.3f, 182.7f, 137.6f, 178.2f),
                        PathNode.QuadTo(143.5f, 177.2f, 162.9f, 177.2f),
                        PathNode.LineTo(254.7f, 177.2f),
                        PathNode.QuadTo(303.5f, 177.2f, 352.0f, 160.7f),
                        PathNode.QuadTo(400.4f, 144.1f, 441.0f, 113.0f),
                        PathNode.QuadTo(458.8f, 100.2f, 479.8f, 102.4f),
                        PathNode.QuadTo(500.7f, 104.7f, 515.0f, 120.0f),
                        PathNode.QuadTo(529.3f, 135.3f, 529.3f, 157.5f),
                        PathNode.Close,
                        PathNode.MoveTo(888.2f, 177.2f),
                        PathNode.LineTo(980.9f, 177.2f),
                        PathNode.QuadTo(1000.4f, 177.2f, 1006.3f, 178.2f),
                        PathNode.QuadTo(1042.1f, 182.7f, 1067.6f, 208.0f),
                        PathNode.QuadTo(1093.2f, 233.3f, 1097.2f, 270.0f),
                        PathNode.LineTo(1097.2f, 293.5f),
                        PathNode.LineTo(1097.2f, 882.5f),
                        PathNode.QuadTo(1097.2f, 926.9f, 1095.7f, 947.1f),
                        PathNode.QuadTo(1094.2f, 967.2f, 1087.3f, 982.2f),
                        PathNode.QuadTo(1071.8f, 1013.0f, 1039.6f, 1029.8f),
                        PathNode.QuadTo(1024.7f, 1037.3f, 1004.3f, 1039.0f),
                        PathNode.QuadTo(983.9f, 1040.7f, 938.0f, 1040.7f),
                        PathNode.QuadTo(842.3f, 1040.7f, 775.2f, 1019.3f),
                        PathNode.QuadTo(708.2f, 997.9f, 645.3f, 937.0f),
                        PathNode.QuadTo(627.9f, 920.1f, 621.2f, 898.5f),
                        PathNode.QuadTo(614.5f, 876.9f, 614.5f, 850.5f),
                        PathNode.LineTo(614.5f, 157.5f),
                        PathNode.QuadTo(614.5f, 135.3f, 628.8f, 120.0f),
                        PathNode.QuadTo(643.1f, 104.7f, 663.8f, 102.4f),
                        PathNode.QuadTo(684.6f, 100.2f, 701.8f, 113.0f),
                        PathNode.QuadTo(743.5f, 144.1f, 791.4f, 160.7f),
                        PathNode.QuadTo(839.3f, 177.2f, 888.2f, 177.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bookDemibold!!
    }

private var _bookDemibold: ImageVector? = null
