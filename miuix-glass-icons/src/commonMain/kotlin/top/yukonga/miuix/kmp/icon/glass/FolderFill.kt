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

val MiuixIcons.Glass.FolderFill: ImageVector
    get() = MiuixIcons.Glass.Regular.FolderFill

val MiuixIcons.Glass.Light.FolderFill: ImageVector
    get() {
        if (_folderFillLight != null) return _folderFillLight!!
        _folderFillLight = ImageVector.Builder(
            name = "FolderFill.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(978.9f, 175.4f),
                        PathNode.QuadTo(1000.6f, 186.0f, 1017.8f, 203.4f),
                        PathNode.QuadTo(1035.0f, 220.9f, 1046.2f, 242.1f),
                        PathNode.QuadTo(1057.3f, 264.9f, 1060.0f, 298.0f),
                        PathNode.QuadTo(1062.8f, 331.2f, 1062.8f, 413.7f),
                        PathNode.LineTo(1062.8f, 674.5f),
                        PathNode.LineTo(80.0f, 674.5f),
                        PathNode.LineTo(80.0f, 413.7f),
                        PathNode.QuadTo(80.0f, 331.2f, 82.8f, 298.0f),
                        PathNode.QuadTo(85.6f, 264.9f, 96.7f, 242.1f),
                        PathNode.QuadTo(107.8f, 220.9f, 125.3f, 203.4f),
                        PathNode.QuadTo(142.7f, 186.0f, 163.9f, 175.4f),
                        PathNode.QuadTo(186.7f, 163.8f, 220.1f, 161.0f),
                        PathNode.QuadTo(253.5f, 158.2f, 335.9f, 158.2f),
                        PathNode.LineTo(807.9f, 158.2f),
                        PathNode.QuadTo(889.8f, 158.2f, 923.0f, 161.0f),
                        PathNode.QuadTo(956.2f, 163.8f, 978.9f, 175.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1047.5f, 811.4f),
                        PathNode.QuadTo(1025.7f, 854.9f, 980.3f, 877.7f),
                        PathNode.QuadTo(957.5f, 888.8f, 924.3f, 891.5f),
                        PathNode.QuadTo(891.2f, 894.3f, 810.2f, 894.3f),
                        PathNode.LineTo(611.8f, 894.3f),
                        PathNode.QuadTo(581.7f, 894.3f, 572.3f, 896.8f),
                        PathNode.QuadTo(547.5f, 900.7f, 528.2f, 915.9f),
                        PathNode.QuadTo(518.9f, 921.8f, 499.5f, 941.7f),
                        PathNode.LineTo(489.5f, 951.7f),
                        PathNode.QuadTo(477.6f, 963.5f, 471.7f, 967.3f),
                        PathNode.QuadTo(451.5f, 980.5f, 433.6f, 982.7f),
                        PathNode.QuadTo(424.9f, 984.7f, 395.8f, 984.7f),
                        PathNode.LineTo(335.9f, 984.7f),
                        PathNode.QuadTo(253.5f, 984.7f, 220.1f, 982.1f),
                        PathNode.QuadTo(186.7f, 979.5f, 163.9f, 968.4f),
                        PathNode.QuadTo(118.5f, 944.8f, 96.7f, 900.8f),
                        PathNode.QuadTo(86.6f, 881.4f, 83.5f, 851.2f),
                        PathNode.QuadTo(80.5f, 821.0f, 80.0f, 753.4f),
                        PathNode.LineTo(80.0f, 723.8f),
                        PathNode.LineTo(1062.3f, 723.8f),
                        PathNode.QuadTo(1061.2f, 756.1f, 1058.1f, 775.9f),
                        PathNode.QuadTo(1055.0f, 795.8f, 1047.5f, 811.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderFillLight!!
    }

private var _folderFillLight: ImageVector? = null

val MiuixIcons.Glass.Normal.FolderFill: ImageVector
    get() {
        if (_folderFillNormal != null) return _folderFillNormal!!
        _folderFillNormal = ImageVector.Builder(
            name = "FolderFill.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(978.9f, 164.4f),
                        PathNode.QuadTo(1001.5f, 175.3f, 1020.0f, 193.8f),
                        PathNode.QuadTo(1038.4f, 212.3f, 1050.1f, 234.8f),
                        PathNode.QuadTo(1061.8f, 259.1f, 1064.8f, 293.2f),
                        PathNode.QuadTo(1067.7f, 327.2f, 1067.7f, 409.4f),
                        PathNode.LineTo(1067.7f, 669.7f),
                        PathNode.LineTo(75.2f, 669.7f),
                        PathNode.LineTo(75.2f, 409.4f),
                        PathNode.QuadTo(75.2f, 327.2f, 78.1f, 293.2f),
                        PathNode.QuadTo(81.0f, 259.1f, 92.7f, 234.8f),
                        PathNode.QuadTo(104.5f, 212.3f, 123.0f, 193.8f),
                        PathNode.QuadTo(141.5f, 175.3f, 163.9f, 164.4f),
                        PathNode.QuadTo(188.2f, 151.8f, 222.4f, 148.9f),
                        PathNode.QuadTo(256.5f, 146.0f, 338.7f, 146.0f),
                        PathNode.LineTo(805.2f, 146.0f),
                        PathNode.QuadTo(886.5f, 146.0f, 920.5f, 148.9f),
                        PathNode.QuadTo(954.6f, 151.8f, 978.9f, 164.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1050.5f, 819.7f),
                        PathNode.QuadTo(1027.2f, 865.6f, 979.3f, 889.9f),
                        PathNode.QuadTo(955.0f, 901.6f, 921.0f, 904.5f),
                        PathNode.QuadTo(886.9f, 907.5f, 805.9f, 907.5f),
                        PathNode.LineTo(611.2f, 907.5f),
                        PathNode.QuadTo(583.2f, 907.5f, 574.1f, 909.6f),
                        PathNode.QuadTo(553.3f, 912.9f, 534.9f, 926.6f),
                        PathNode.QuadTo(527.2f, 931.8f, 508.0f, 951.1f),
                        PathNode.LineTo(498.0f, 961.1f),
                        PathNode.QuadTo(486.8f, 972.4f, 478.7f, 977.7f),
                        PathNode.QuadTo(457.3f, 992.1f, 437.3f, 994.9f),
                        PathNode.QuadTo(427.7f, 996.9f, 398.0f, 996.9f),
                        PathNode.LineTo(338.7f, 996.9f),
                        PathNode.QuadTo(256.5f, 996.9f, 222.4f, 994.0f),
                        PathNode.QuadTo(188.2f, 991.1f, 163.9f, 979.4f),
                        PathNode.QuadTo(116.0f, 954.8f, 92.7f, 908.1f),
                        PathNode.QuadTo(82.0f, 886.9f, 78.7f, 855.5f),
                        PathNode.QuadTo(75.3f, 824.0f, 75.2f, 758.9f),
                        PathNode.LineTo(75.2f, 731.1f),
                        PathNode.LineTo(1066.8f, 731.1f),
                        PathNode.QuadTo(1065.1f, 762.5f, 1061.8f, 782.8f),
                        PathNode.QuadTo(1058.4f, 803.1f, 1050.5f, 819.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderFillNormal!!
    }

private var _folderFillNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.FolderFill: ImageVector
    get() {
        if (_folderFillRegular != null) return _folderFillRegular!!
        _folderFillRegular = ImageVector.Builder(
            name = "FolderFill.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(978.9f, 159.4f),
                        PathNode.QuadTo(1001.9f, 170.4f, 1020.9f, 189.4f),
                        PathNode.QuadTo(1039.9f, 208.4f, 1051.9f, 231.4f),
                        PathNode.QuadTo(1063.9f, 256.4f, 1066.9f, 290.9f),
                        PathNode.QuadTo(1069.9f, 325.4f, 1069.9f, 407.4f),
                        PathNode.LineTo(1069.9f, 667.4f),
                        PathNode.LineTo(72.9f, 667.4f),
                        PathNode.LineTo(72.9f, 407.4f),
                        PathNode.QuadTo(72.9f, 325.4f, 75.9f, 290.9f),
                        PathNode.QuadTo(78.9f, 256.4f, 90.9f, 231.4f),
                        PathNode.QuadTo(102.9f, 208.4f, 121.9f, 189.4f),
                        PathNode.QuadTo(140.9f, 170.4f, 163.9f, 159.4f),
                        PathNode.QuadTo(188.9f, 146.4f, 223.4f, 143.4f),
                        PathNode.QuadTo(257.9f, 140.4f, 339.9f, 140.4f),
                        PathNode.LineTo(803.9f, 140.4f),
                        PathNode.QuadTo(884.9f, 140.4f, 919.4f, 143.4f),
                        PathNode.QuadTo(953.9f, 146.4f, 978.9f, 159.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1051.9f, 823.4f),
                        PathNode.QuadTo(1027.9f, 870.4f, 978.9f, 895.4f),
                        PathNode.QuadTo(953.9f, 907.4f, 919.4f, 910.4f),
                        PathNode.QuadTo(884.9f, 913.4f, 803.9f, 913.4f),
                        PathNode.LineTo(610.9f, 913.4f),
                        PathNode.QuadTo(583.9f, 913.4f, 574.9f, 915.4f),
                        PathNode.QuadTo(555.9f, 918.4f, 537.9f, 931.4f),
                        PathNode.QuadTo(530.9f, 936.4f, 511.9f, 955.4f),
                        PathNode.LineTo(501.9f, 965.4f),
                        PathNode.QuadTo(490.9f, 976.4f, 481.9f, 982.4f),
                        PathNode.QuadTo(459.9f, 997.4f, 438.9f, 1000.4f),
                        PathNode.QuadTo(428.9f, 1002.4f, 398.9f, 1002.4f),
                        PathNode.LineTo(339.9f, 1002.4f),
                        PathNode.QuadTo(257.9f, 1002.4f, 223.4f, 999.4f),
                        PathNode.QuadTo(188.9f, 996.4f, 163.9f, 984.4f),
                        PathNode.QuadTo(114.9f, 959.4f, 90.9f, 911.4f),
                        PathNode.QuadTo(79.9f, 889.4f, 76.4f, 857.4f),
                        PathNode.QuadTo(72.9f, 825.4f, 72.9f, 761.4f),
                        PathNode.LineTo(72.9f, 734.4f),
                        PathNode.LineTo(1068.9f, 734.4f),
                        PathNode.QuadTo(1066.9f, 765.4f, 1063.4f, 785.9f),
                        PathNode.QuadTo(1059.9f, 806.4f, 1051.9f, 823.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderFillRegular!!
    }

private var _folderFillRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.FolderFill: ImageVector
    get() {
        if (_folderFillMedium != null) return _folderFillMedium!!
        _folderFillMedium = ImageVector.Builder(
            name = "FolderFill.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(981.1f, 148.1f),
                        PathNode.QuadTo(1005.2f, 159.6f, 1025.1f, 179.6f),
                        PathNode.QuadTo(1045.1f, 199.5f, 1057.6f, 223.6f),
                        PathNode.QuadTo(1070.4f, 249.9f, 1073.6f, 285.4f),
                        PathNode.QuadTo(1076.7f, 320.8f, 1076.7f, 403.1f),
                        PathNode.LineTo(1076.7f, 660.9f),
                        PathNode.LineTo(66.2f, 660.9f),
                        PathNode.LineTo(66.2f, 403.1f),
                        PathNode.QuadTo(66.2f, 320.8f, 69.3f, 285.4f),
                        PathNode.QuadTo(72.4f, 249.9f, 85.3f, 223.6f),
                        PathNode.QuadTo(97.8f, 199.5f, 117.7f, 179.6f),
                        PathNode.QuadTo(137.7f, 159.6f, 161.8f, 148.1f),
                        PathNode.QuadTo(187.8f, 134.5f, 223.4f, 131.4f),
                        PathNode.QuadTo(259.0f, 128.3f, 341.3f, 128.3f),
                        PathNode.LineTo(802.6f, 128.3f),
                        PathNode.QuadTo(883.8f, 128.3f, 919.4f, 131.4f),
                        PathNode.QuadTo(955.0f, 134.5f, 981.1f, 148.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1057.6f, 831.5f),
                        PathNode.QuadTo(1032.3f, 881.0f, 981.1f, 907.3f),
                        PathNode.QuadTo(955.0f, 919.9f, 919.4f, 923.0f),
                        PathNode.QuadTo(883.8f, 926.1f, 802.6f, 926.1f),
                        PathNode.LineTo(610.7f, 926.1f),
                        PathNode.QuadTo(584.7f, 926.1f, 576.6f, 927.9f),
                        PathNode.QuadTo(558.6f, 930.6f, 543.3f, 942.2f),
                        PathNode.QuadTo(535.5f, 948.9f, 519.0f, 965.4f),
                        PathNode.LineTo(508.7f, 975.4f),
                        PathNode.QuadTo(496.1f, 987.8f, 487.3f, 993.2f),
                        PathNode.QuadTo(464.5f, 1009.6f, 441.1f, 1012.9f),
                        PathNode.QuadTo(430.6f, 1014.6f, 399.7f, 1014.6f),
                        PathNode.LineTo(341.3f, 1014.6f),
                        PathNode.QuadTo(259.0f, 1014.6f, 223.4f, 1011.5f),
                        PathNode.QuadTo(187.8f, 1008.3f, 161.8f, 995.8f),
                        PathNode.QuadTo(110.6f, 969.4f, 85.3f, 919.3f),
                        PathNode.QuadTo(73.2f, 895.6f, 69.7f, 862.4f),
                        PathNode.QuadTo(66.2f, 829.2f, 66.2f, 763.3f),
                        PathNode.LineTo(66.2f, 736.3f),
                        PathNode.LineTo(1076.0f, 736.3f),
                        PathNode.QuadTo(1074.2f, 767.9f, 1070.5f, 790.3f),
                        PathNode.QuadTo(1066.7f, 812.6f, 1057.6f, 831.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderFillMedium!!
    }

private var _folderFillMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.FolderFill: ImageVector
    get() {
        if (_folderFillDemibold != null) return _folderFillDemibold!!
        _folderFillDemibold = ImageVector.Builder(
            name = "FolderFill.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(982.6f, 140.1f),
                        PathNode.QuadTo(1007.4f, 152.0f, 1028.1f, 172.7f),
                        PathNode.QuadTo(1048.7f, 193.3f, 1061.6f, 218.1f),
                        PathNode.QuadTo(1075.0f, 245.4f, 1078.2f, 281.5f),
                        PathNode.QuadTo(1081.4f, 317.6f, 1081.4f, 400.1f),
                        PathNode.LineTo(1081.4f, 656.4f),
                        PathNode.LineTo(61.4f, 656.4f),
                        PathNode.LineTo(61.4f, 400.1f),
                        PathNode.QuadTo(61.4f, 317.6f, 64.7f, 281.5f),
                        PathNode.QuadTo(67.9f, 245.4f, 81.3f, 218.1f),
                        PathNode.QuadTo(94.2f, 193.3f, 114.8f, 172.7f),
                        PathNode.QuadTo(135.4f, 152.0f, 160.3f, 140.1f),
                        PathNode.QuadTo(187.1f, 126.2f, 223.4f, 123.0f),
                        PathNode.QuadTo(259.8f, 119.8f, 342.2f, 119.8f),
                        PathNode.LineTo(801.6f, 119.8f),
                        PathNode.QuadTo(883.1f, 119.8f, 919.4f, 123.0f),
                        PathNode.QuadTo(955.8f, 126.2f, 982.6f, 140.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1061.6f, 837.2f),
                        PathNode.QuadTo(1035.3f, 888.3f, 982.6f, 915.6f),
                        PathNode.QuadTo(955.8f, 928.6f, 919.4f, 931.8f),
                        PathNode.QuadTo(883.1f, 935.0f, 801.6f, 935.0f),
                        PathNode.LineTo(610.5f, 935.0f),
                        PathNode.QuadTo(585.3f, 935.0f, 577.7f, 936.6f),
                        PathNode.QuadTo(560.5f, 939.1f, 547.1f, 949.8f),
                        PathNode.QuadTo(538.7f, 957.6f, 523.9f, 972.4f),
                        PathNode.LineTo(513.4f, 982.4f),
                        PathNode.QuadTo(499.7f, 995.7f, 491.1f, 1000.8f),
                        PathNode.QuadTo(467.7f, 1018.1f, 442.6f, 1021.6f),
                        PathNode.QuadTo(431.7f, 1023.1f, 400.3f, 1023.1f),
                        PathNode.LineTo(342.2f, 1023.1f),
                        PathNode.QuadTo(259.8f, 1023.1f, 223.4f, 1019.9f),
                        PathNode.QuadTo(187.1f, 1016.6f, 160.3f, 1003.7f),
                        PathNode.QuadTo(107.6f, 976.4f, 81.3f, 924.8f),
                        PathNode.QuadTo(68.4f, 900.0f, 64.9f, 865.9f),
                        PathNode.QuadTo(61.4f, 831.9f, 61.4f, 764.6f),
                        PathNode.LineTo(61.4f, 737.6f),
                        PathNode.LineTo(1080.9f, 737.6f),
                        PathNode.QuadTo(1079.3f, 769.6f, 1075.4f, 793.3f),
                        PathNode.QuadTo(1071.4f, 817.0f, 1061.6f, 837.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _folderFillDemibold!!
    }

private var _folderFillDemibold: ImageVector? = null
