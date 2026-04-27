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

val MiuixIcons.Refresh: ImageVector
    get() = MiuixIcons.Regular.Refresh

val MiuixIcons.Light.Refresh: ImageVector
    get() {
        if (_refreshLight != null) return _refreshLight!!
        _refreshLight = ImageVector.Builder(
            name = "Refresh.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1190.4f,
            viewportHeight = 1190.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1190.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(981.7f, 217.2f),
                        PathNode.LineTo(958.7f, 246.2f),
                        PathNode.QuadTo(952.7f, 252.2f, 954.7f, 260.2f),
                        PathNode.QuadTo(956.7f, 268.2f, 968.7f, 284.2f),
                        PathNode.QuadTo(1031.7f, 368.2f, 1059.7f, 463.2f),
                        PathNode.QuadTo(1087.7f, 558.2f, 1076.7f, 654.7f),
                        PathNode.QuadTo(1065.7f, 751.2f, 1015.7f, 837.2f),
                        PathNode.QuadTo(960.7f, 931.2f, 874.2f, 992.2f),
                        PathNode.QuadTo(787.7f, 1053.2f, 681.7f, 1072.2f),
                        PathNode.QuadTo(575.7f, 1091.2f, 465.7f, 1063.2f),
                        PathNode.QuadTo(456.7f, 1061.2f, 453.2f, 1053.2f),
                        PathNode.QuadTo(449.7f, 1045.2f, 453.7f, 1038.2f),
                        PathNode.LineTo(465.7f, 1018.2f),
                        PathNode.QuadTo(469.7f, 1010.2f, 474.7f, 1008.2f),
                        PathNode.QuadTo(479.7f, 1006.2f, 486.7f, 1008.2f),
                        PathNode.QuadTo(582.7f, 1030.2f, 674.7f, 1012.7f),
                        PathNode.QuadTo(766.7f, 995.2f, 842.2f, 942.2f),
                        PathNode.QuadTo(917.7f, 889.2f, 964.7f, 808.2f),
                        PathNode.QuadTo(1008.7f, 732.2f, 1019.2f, 648.2f),
                        PathNode.QuadTo(1029.7f, 564.2f, 1005.2f, 480.7f),
                        PathNode.QuadTo(980.7f, 397.2f, 923.7f, 322.2f),
                        PathNode.QuadTo(916.7f, 314.2f, 912.2f, 313.7f),
                        PathNode.QuadTo(907.7f, 313.2f, 902.7f, 319.2f),
                        PathNode.LineTo(882.7f, 344.2f),
                        PathNode.QuadTo(878.7f, 349.2f, 872.7f, 350.7f),
                        PathNode.QuadTo(866.7f, 352.2f, 861.2f, 348.2f),
                        PathNode.QuadTo(855.7f, 344.2f, 853.7f, 335.2f),
                        PathNode.LineTo(819.7f, 208.2f),
                        PathNode.QuadTo(817.7f, 199.2f, 822.2f, 193.2f),
                        PathNode.QuadTo(826.7f, 187.2f, 835.7f, 187.2f),
                        PathNode.LineTo(970.7f, 191.2f),
                        PathNode.QuadTo(977.7f, 191.2f, 981.7f, 195.7f),
                        PathNode.QuadTo(985.7f, 200.2f, 985.7f, 206.2f),
                        PathNode.QuadTo(985.7f, 212.2f, 981.7f, 217.2f),
                        PathNode.Close,
                        PathNode.MoveTo(721.7f, 147.2f),
                        PathNode.LineTo(710.7f, 168.2f),
                        PathNode.QuadTo(703.7f, 181.2f, 687.7f, 177.2f),
                        PathNode.QuadTo(593.7f, 159.2f, 505.2f, 178.7f),
                        PathNode.QuadTo(416.7f, 198.2f, 344.7f, 250.2f),
                        PathNode.QuadTo(272.7f, 302.2f, 226.7f, 381.2f),
                        PathNode.QuadTo(183.7f, 456.2f, 172.7f, 540.2f),
                        PathNode.QuadTo(161.7f, 624.2f, 184.7f, 706.7f),
                        PathNode.QuadTo(207.7f, 789.2f, 264.7f, 860.2f),
                        PathNode.QuadTo(271.7f, 868.2f, 278.2f, 868.7f),
                        PathNode.QuadTo(284.7f, 869.2f, 289.7f, 863.2f),
                        PathNode.LineTo(311.7f, 837.2f),
                        PathNode.QuadTo(318.7f, 829.2f, 327.2f, 831.2f),
                        PathNode.QuadTo(335.7f, 833.2f, 338.7f, 843.2f),
                        PathNode.LineTo(373.7f, 970.2f),
                        PathNode.QuadTo(375.7f, 982.2f, 371.7f, 988.7f),
                        PathNode.QuadTo(367.7f, 995.2f, 357.7f, 995.2f),
                        PathNode.LineTo(221.7f, 991.2f),
                        PathNode.QuadTo(215.7f, 991.2f, 211.7f, 986.7f),
                        PathNode.QuadTo(207.7f, 982.2f, 207.7f, 976.7f),
                        PathNode.QuadTo(207.7f, 971.2f, 210.7f, 967.2f),
                        PathNode.LineTo(229.7f, 941.2f),
                        PathNode.QuadTo(235.7f, 932.2f, 234.7f, 924.7f),
                        PathNode.QuadTo(233.7f, 917.2f, 223.7f, 905.2f),
                        PathNode.QuadTo(158.7f, 824.2f, 130.7f, 728.7f),
                        PathNode.QuadTo(102.7f, 633.2f, 113.7f, 536.2f),
                        PathNode.QuadTo(124.7f, 439.2f, 175.7f, 352.2f),
                        PathNode.QuadTo(228.7f, 261.2f, 312.2f, 201.2f),
                        PathNode.QuadTo(395.7f, 141.2f, 498.2f, 120.2f),
                        PathNode.QuadTo(600.7f, 99.2f, 708.7f, 121.2f),
                        PathNode.QuadTo(719.7f, 123.2f, 722.7f, 131.2f),
                        PathNode.QuadTo(725.7f, 139.2f, 721.7f, 147.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _refreshLight!!
    }

private var _refreshLight: ImageVector? = null

val MiuixIcons.Normal.Refresh: ImageVector
    get() {
        if (_refreshNormal != null) return _refreshNormal!!
        _refreshNormal = ImageVector.Builder(
            name = "Refresh.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1214.3f,
            viewportHeight = 1214.3f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1214.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1011.5f, 236.4f),
                        PathNode.LineTo(989.9f, 264.0f),
                        PathNode.QuadTo(983.9f, 271.4f, 984.5f, 279.0f),
                        PathNode.QuadTo(985.2f, 286.7f, 993.7f, 297.9f),
                        PathNode.QuadTo(1055.3f, 379.8f, 1082.3f, 475.5f),
                        PathNode.QuadTo(1109.3f, 571.2f, 1097.9f, 669.1f),
                        PathNode.QuadTo(1086.6f, 766.9f, 1035.2f, 854.3f),
                        PathNode.QuadTo(979.5f, 949.7f, 891.3f, 1011.7f),
                        PathNode.QuadTo(803.1f, 1073.8f, 696.1f, 1093.4f),
                        PathNode.QuadTo(589.0f, 1113.1f, 479.0f, 1085.1f),
                        PathNode.QuadTo(466.6f, 1081.8f, 462.1f, 1071.3f),
                        PathNode.QuadTo(457.5f, 1060.9f, 462.9f, 1051.2f),
                        PathNode.LineTo(477.7f, 1026.4f),
                        PathNode.QuadTo(483.0f, 1015.6f, 489.4f, 1013.3f),
                        PathNode.QuadTo(495.8f, 1010.9f, 506.2f, 1012.9f),
                        PathNode.QuadTo(598.1f, 1033.6f, 687.0f, 1016.1f),
                        PathNode.QuadTo(775.9f, 998.6f, 849.4f, 946.9f),
                        PathNode.QuadTo(922.8f, 895.3f, 968.4f, 815.7f),
                        PathNode.QuadTo(1011.0f, 742.4f, 1021.5f, 660.9f),
                        PathNode.QuadTo(1032.0f, 579.3f, 1010.3f, 499.9f),
                        PathNode.QuadTo(988.5f, 420.5f, 938.4f, 353.1f),
                        PathNode.QuadTo(934.2f, 347.8f, 929.7f, 347.7f),
                        PathNode.QuadTo(925.2f, 347.5f, 920.9f, 352.8f),
                        PathNode.LineTo(903.6f, 374.4f),
                        PathNode.QuadTo(898.2f, 380.8f, 890.2f, 382.6f),
                        PathNode.QuadTo(882.1f, 384.4f, 874.9f, 379.8f),
                        PathNode.QuadTo(867.7f, 375.1f, 864.3f, 362.6f),
                        PathNode.LineTo(827.5f, 224.6f),
                        PathNode.QuadTo(824.9f, 213.6f, 830.7f, 205.5f),
                        PathNode.QuadTo(836.6f, 197.4f, 848.4f, 197.4f),
                        PathNode.LineTo(996.4f, 201.4f),
                        PathNode.QuadTo(1006.2f, 201.4f, 1011.5f, 207.3f),
                        PathNode.QuadTo(1016.9f, 213.2f, 1016.9f, 221.6f),
                        PathNode.QuadTo(1016.9f, 230.0f, 1011.5f, 236.4f),
                        PathNode.Close,
                        PathNode.MoveTo(737.1f, 160.2f),
                        PathNode.LineTo(722.7f, 186.0f),
                        PathNode.QuadTo(715.0f, 202.4f, 691.4f, 197.8f),
                        PathNode.QuadTo(602.2f, 181.1f, 516.8f, 200.3f),
                        PathNode.QuadTo(431.4f, 219.4f, 361.1f, 270.4f),
                        PathNode.QuadTo(290.9f, 321.4f, 246.2f, 398.3f),
                        PathNode.QuadTo(204.6f, 470.6f, 193.6f, 551.5f),
                        PathNode.QuadTo(182.6f, 632.4f, 203.2f, 711.1f),
                        PathNode.QuadTo(223.8f, 789.8f, 273.2f, 856.0f),
                        PathNode.QuadTo(278.2f, 861.9f, 284.7f, 862.1f),
                        PathNode.QuadTo(291.2f, 862.3f, 295.5f, 856.9f),
                        PathNode.LineTo(315.4f, 833.0f),
                        PathNode.QuadTo(324.5f, 822.9f, 336.1f, 825.6f),
                        PathNode.QuadTo(347.7f, 828.3f, 351.4f, 841.1f),
                        PathNode.LineTo(389.1f, 978.4f),
                        PathNode.QuadTo(393.2f, 993.8f, 387.8f, 1003.1f),
                        PathNode.QuadTo(382.4f, 1012.3f, 369.0f, 1011.6f),
                        PathNode.LineTo(219.9f, 1007.6f),
                        PathNode.QuadTo(211.2f, 1007.6f, 206.1f, 1001.8f),
                        PathNode.QuadTo(201.1f, 995.9f, 200.8f, 988.3f),
                        PathNode.QuadTo(200.4f, 980.8f, 204.1f, 975.4f),
                        PathNode.LineTo(222.4f, 950.1f),
                        PathNode.QuadTo(229.1f, 941.1f, 229.1f, 933.6f),
                        PathNode.QuadTo(229.2f, 926.1f, 222.6f, 917.5f),
                        PathNode.QuadTo(159.7f, 837.2f, 132.4f, 741.4f),
                        PathNode.QuadTo(105.0f, 645.5f, 116.4f, 546.8f),
                        PathNode.QuadTo(127.7f, 448.1f, 179.4f, 359.7f),
                        PathNode.QuadTo(233.1f, 266.6f, 319.0f, 205.6f),
                        PathNode.QuadTo(404.9f, 144.6f, 508.8f, 122.9f),
                        PathNode.QuadTo(612.7f, 101.2f, 719.3f, 124.6f),
                        PathNode.QuadTo(733.7f, 127.9f, 738.4f, 139.0f),
                        PathNode.QuadTo(743.2f, 150.1f, 737.1f, 160.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _refreshNormal!!
    }

private var _refreshNormal: ImageVector? = null

val MiuixIcons.Regular.Refresh: ImageVector
    get() {
        if (_refreshRegular != null) return _refreshRegular!!
        _refreshRegular = ImageVector.Builder(
            name = "Refresh.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.2f,
            viewportHeight = 1225.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1225.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1025.1f, 245.1f),
                        PathNode.LineTo(1004.1f, 272.1f),
                        PathNode.QuadTo(998.1f, 280.1f, 998.1f, 287.6f),
                        PathNode.QuadTo(998.1f, 295.1f, 1005.1f, 304.1f),
                        PathNode.QuadTo(1066.1f, 385.1f, 1092.6f, 481.1f),
                        PathNode.QuadTo(1119.1f, 577.1f, 1107.6f, 675.6f),
                        PathNode.QuadTo(1096.1f, 774.1f, 1044.1f, 862.1f),
                        PathNode.QuadTo(988.1f, 958.1f, 899.1f, 1020.6f),
                        PathNode.QuadTo(810.1f, 1083.1f, 702.6f, 1103.1f),
                        PathNode.QuadTo(595.1f, 1123.1f, 485.1f, 1095.1f),
                        PathNode.QuadTo(471.1f, 1091.1f, 466.1f, 1079.6f),
                        PathNode.QuadTo(461.1f, 1068.1f, 467.1f, 1057.1f),
                        PathNode.LineTo(483.1f, 1030.1f),
                        PathNode.QuadTo(489.1f, 1018.1f, 496.1f, 1015.6f),
                        PathNode.QuadTo(503.1f, 1013.1f, 515.1f, 1015.1f),
                        PathNode.QuadTo(605.1f, 1035.1f, 692.6f, 1017.6f),
                        PathNode.QuadTo(780.1f, 1000.1f, 852.6f, 949.1f),
                        PathNode.QuadTo(925.1f, 898.1f, 970.1f, 819.1f),
                        PathNode.QuadTo(1012.1f, 747.1f, 1022.6f, 666.6f),
                        PathNode.QuadTo(1033.1f, 586.1f, 1012.6f, 508.6f),
                        PathNode.QuadTo(992.1f, 431.1f, 945.1f, 367.1f),
                        PathNode.QuadTo(942.1f, 363.1f, 937.6f, 363.1f),
                        PathNode.QuadTo(933.1f, 363.1f, 929.1f, 368.1f),
                        PathNode.LineTo(913.1f, 388.1f),
                        PathNode.QuadTo(907.1f, 395.1f, 898.1f, 397.1f),
                        PathNode.QuadTo(889.1f, 399.1f, 881.1f, 394.1f),
                        PathNode.QuadTo(873.1f, 389.1f, 869.1f, 375.1f),
                        PathNode.LineTo(831.1f, 232.1f),
                        PathNode.QuadTo(828.1f, 220.1f, 834.6f, 211.1f),
                        PathNode.QuadTo(841.1f, 202.1f, 854.1f, 202.1f),
                        PathNode.LineTo(1008.1f, 206.1f),
                        PathNode.QuadTo(1019.1f, 206.1f, 1025.1f, 212.6f),
                        PathNode.QuadTo(1031.1f, 219.1f, 1031.1f, 228.6f),
                        PathNode.QuadTo(1031.1f, 238.1f, 1025.1f, 245.1f),
                        PathNode.Close,
                        PathNode.MoveTo(744.1f, 166.1f),
                        PathNode.LineTo(728.1f, 194.1f),
                        PathNode.QuadTo(720.1f, 212.1f, 693.1f, 207.1f),
                        PathNode.QuadTo(606.1f, 191.1f, 522.1f, 210.1f),
                        PathNode.QuadTo(438.1f, 229.1f, 368.6f, 279.6f),
                        PathNode.QuadTo(299.1f, 330.1f, 255.1f, 406.1f),
                        PathNode.QuadTo(214.1f, 477.1f, 203.1f, 556.6f),
                        PathNode.QuadTo(192.1f, 636.1f, 211.6f, 713.1f),
                        PathNode.QuadTo(231.1f, 790.1f, 277.1f, 854.1f),
                        PathNode.QuadTo(281.1f, 859.1f, 287.6f, 859.1f),
                        PathNode.QuadTo(294.1f, 859.1f, 298.1f, 854.1f),
                        PathNode.LineTo(317.1f, 831.1f),
                        PathNode.QuadTo(327.1f, 820.1f, 340.1f, 823.1f),
                        PathNode.QuadTo(353.1f, 826.1f, 357.1f, 840.1f),
                        PathNode.LineTo(396.1f, 982.1f),
                        PathNode.QuadTo(401.1f, 999.1f, 395.1f, 1009.6f),
                        PathNode.QuadTo(389.1f, 1020.1f, 374.1f, 1019.1f),
                        PathNode.LineTo(219.1f, 1015.1f),
                        PathNode.QuadTo(209.1f, 1015.1f, 203.6f, 1008.6f),
                        PathNode.QuadTo(198.1f, 1002.1f, 197.6f, 993.6f),
                        PathNode.QuadTo(197.1f, 985.1f, 201.1f, 979.1f),
                        PathNode.LineTo(219.1f, 954.1f),
                        PathNode.QuadTo(226.1f, 945.1f, 226.6f, 937.6f),
                        PathNode.QuadTo(227.1f, 930.1f, 222.1f, 923.1f),
                        PathNode.QuadTo(160.1f, 843.1f, 133.1f, 747.1f),
                        PathNode.QuadTo(106.1f, 651.1f, 117.6f, 551.6f),
                        PathNode.QuadTo(129.1f, 452.1f, 181.1f, 363.1f),
                        PathNode.QuadTo(235.1f, 269.1f, 322.1f, 207.6f),
                        PathNode.QuadTo(409.1f, 146.1f, 513.6f, 124.1f),
                        PathNode.QuadTo(618.1f, 102.1f, 724.1f, 126.1f),
                        PathNode.QuadTo(740.1f, 130.1f, 745.6f, 142.6f),
                        PathNode.QuadTo(751.1f, 155.1f, 744.1f, 166.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _refreshRegular!!
    }

private var _refreshRegular: ImageVector? = null

val MiuixIcons.Medium.Refresh: ImageVector
    get() {
        if (_refreshMedium != null) return _refreshMedium!!
        _refreshMedium = ImageVector.Builder(
            name = "Refresh.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1243.6f,
            viewportHeight = 1243.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1243.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1045.2f, 265.5f),
                        PathNode.LineTo(1026.5f, 290.1f),
                        PathNode.QuadTo(1021.1f, 296.3f, 1021.1f, 303.0f),
                        PathNode.QuadTo(1021.1f, 309.6f, 1026.9f, 317.4f),
                        PathNode.QuadTo(1085.0f, 397.2f, 1110.6f, 492.6f),
                        PathNode.QuadTo(1136.2f, 588.0f, 1124.4f, 687.1f),
                        PathNode.QuadTo(1112.6f, 786.2f, 1060.0f, 875.4f),
                        PathNode.QuadTo(1003.5f, 972.6f, 915.3f, 1035.4f),
                        PathNode.QuadTo(827.2f, 1098.2f, 721.8f, 1119.0f),
                        PathNode.QuadTo(616.3f, 1139.9f, 508.7f, 1114.9f),
                        PathNode.QuadTo(490.6f, 1110.9f, 484.1f, 1096.7f),
                        PathNode.QuadTo(477.6f, 1082.6f, 485.4f, 1068.0f),
                        PathNode.LineTo(501.4f, 1038.1f),
                        PathNode.QuadTo(508.6f, 1023.7f, 517.9f, 1020.4f),
                        PathNode.QuadTo(527.3f, 1017.0f, 542.2f, 1019.0f),
                        PathNode.QuadTo(624.0f, 1036.0f, 706.5f, 1018.2f),
                        PathNode.QuadTo(789.0f, 1000.5f, 858.8f, 950.6f),
                        PathNode.QuadTo(928.7f, 900.8f, 972.5f, 824.2f),
                        PathNode.QuadTo(1013.3f, 753.9f, 1023.5f, 677.2f),
                        PathNode.QuadTo(1033.7f, 600.6f, 1015.9f, 528.1f),
                        PathNode.QuadTo(998.0f, 455.6f, 957.5f, 396.9f),
                        PathNode.QuadTo(954.5f, 392.3f, 950.6f, 392.3f),
                        PathNode.QuadTo(946.7f, 392.3f, 942.7f, 396.7f),
                        PathNode.LineTo(929.0f, 413.2f),
                        PathNode.QuadTo(921.9f, 420.7f, 911.1f, 422.2f),
                        PathNode.QuadTo(900.3f, 423.6f, 890.3f, 416.2f),
                        PathNode.QuadTo(880.2f, 408.9f, 875.6f, 392.5f),
                        PathNode.LineTo(837.0f, 249.5f),
                        PathNode.QuadTo(832.9f, 234.0f, 842.0f, 221.5f),
                        PathNode.QuadTo(851.2f, 208.9f, 868.3f, 209.5f),
                        PathNode.LineTo(1022.3f, 213.5f),
                        PathNode.QuadTo(1036.8f, 213.5f, 1044.9f, 222.4f),
                        PathNode.QuadTo(1052.9f, 231.2f, 1052.9f, 243.7f),
                        PathNode.QuadTo(1052.9f, 256.1f, 1045.2f, 265.5f),
                        PathNode.Close,
                        PathNode.MoveTo(744.7f, 175.3f),
                        PathNode.LineTo(728.2f, 206.2f),
                        PathNode.QuadTo(718.4f, 228.9f, 684.3f, 223.3f),
                        PathNode.QuadTo(605.6f, 209.7f, 526.9f, 228.4f),
                        PathNode.QuadTo(448.2f, 247.1f, 381.0f, 296.1f),
                        PathNode.QuadTo(313.9f, 345.2f, 271.0f, 419.4f),
                        PathNode.QuadTo(231.2f, 488.6f, 220.5f, 564.3f),
                        PathNode.QuadTo(209.8f, 640.0f, 226.7f, 711.7f),
                        PathNode.QuadTo(243.5f, 783.4f, 283.0f, 842.7f),
                        PathNode.QuadTo(287.0f, 848.3f, 292.7f, 848.9f),
                        PathNode.QuadTo(298.3f, 849.5f, 302.3f, 844.5f),
                        PathNode.LineTo(318.3f, 825.6f),
                        PathNode.QuadTo(330.7f, 812.2f, 347.8f, 816.4f),
                        PathNode.QuadTo(364.9f, 820.6f, 370.1f, 839.3f),
                        PathNode.LineTo(409.1f, 981.3f),
                        PathNode.QuadTo(414.7f, 1002.4f, 406.3f, 1016.1f),
                        PathNode.QuadTo(398.0f, 1029.9f, 378.9f, 1028.9f),
                        PathNode.LineTo(223.9f, 1024.9f),
                        PathNode.QuadTo(210.9f, 1024.3f, 203.1f, 1016.0f),
                        PathNode.QuadTo(195.2f, 1007.7f, 194.4f, 996.3f),
                        PathNode.QuadTo(193.6f, 984.9f, 199.4f, 975.9f),
                        PathNode.LineTo(215.0f, 954.5f),
                        PathNode.QuadTo(221.5f, 946.0f, 221.7f, 938.8f),
                        PathNode.QuadTo(221.9f, 931.6f, 215.7f, 922.9f),
                        PathNode.QuadTo(157.8f, 846.4f, 132.6f, 751.9f),
                        PathNode.QuadTo(107.3f, 657.3f, 119.4f, 557.5f),
                        PathNode.QuadTo(131.5f, 457.7f, 183.5f, 368.2f),
                        PathNode.QuadTo(238.1f, 273.6f, 325.4f, 211.2f),
                        PathNode.QuadTo(412.7f, 148.8f, 515.1f, 126.2f),
                        PathNode.QuadTo(617.6f, 103.6f, 718.3f, 125.9f),
                        PathNode.QuadTo(738.4f, 130.5f, 745.7f, 145.3f),
                        PathNode.QuadTo(752.9f, 160.2f, 744.7f, 175.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _refreshMedium!!
    }

private var _refreshMedium: ImageVector? = null

val MiuixIcons.Demibold.Refresh: ImageVector
    get() {
        if (_refreshDemibold != null) return _refreshDemibold!!
        _refreshDemibold = ImageVector.Builder(
            name = "Refresh.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1256.4f,
            viewportHeight = 1256.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1256.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1059.2f, 279.7f),
                        PathNode.LineTo(1042.2f, 302.7f),
                        PathNode.QuadTo(1037.2f, 307.7f, 1037.2f, 313.7f),
                        PathNode.QuadTo(1037.2f, 319.7f, 1042.2f, 326.7f),
                        PathNode.QuadTo(1098.2f, 405.7f, 1123.2f, 500.7f),
                        PathNode.QuadTo(1148.2f, 595.7f, 1136.2f, 695.2f),
                        PathNode.QuadTo(1124.2f, 794.7f, 1071.2f, 884.7f),
                        PathNode.QuadTo(1014.2f, 982.7f, 926.7f, 1045.7f),
                        PathNode.QuadTo(839.2f, 1108.7f, 735.2f, 1130.2f),
                        PathNode.QuadTo(631.2f, 1151.7f, 525.2f, 1128.7f),
                        PathNode.QuadTo(504.2f, 1124.7f, 496.7f, 1108.7f),
                        PathNode.QuadTo(489.2f, 1092.7f, 498.2f, 1075.7f),
                        PathNode.LineTo(514.2f, 1043.7f),
                        PathNode.QuadTo(522.2f, 1027.7f, 533.2f, 1023.7f),
                        PathNode.QuadTo(544.2f, 1019.7f, 561.2f, 1021.7f),
                        PathNode.QuadTo(637.2f, 1036.7f, 716.2f, 1018.7f),
                        PathNode.QuadTo(795.2f, 1000.7f, 863.2f, 951.7f),
                        PathNode.QuadTo(931.2f, 902.7f, 974.2f, 827.7f),
                        PathNode.QuadTo(1014.2f, 758.7f, 1024.2f, 684.7f),
                        PathNode.QuadTo(1034.2f, 610.7f, 1018.2f, 541.7f),
                        PathNode.QuadTo(1002.2f, 472.7f, 966.2f, 417.7f),
                        PathNode.QuadTo(963.2f, 412.7f, 959.7f, 412.7f),
                        PathNode.QuadTo(956.2f, 412.7f, 952.2f, 416.7f),
                        PathNode.LineTo(940.2f, 430.7f),
                        PathNode.QuadTo(932.2f, 438.7f, 920.2f, 439.7f),
                        PathNode.QuadTo(908.2f, 440.7f, 896.7f, 431.7f),
                        PathNode.QuadTo(885.2f, 422.7f, 880.2f, 404.7f),
                        PathNode.LineTo(841.2f, 261.7f),
                        PathNode.QuadTo(836.2f, 243.7f, 847.2f, 228.7f),
                        PathNode.QuadTo(858.2f, 213.7f, 878.2f, 214.7f),
                        PathNode.LineTo(1032.2f, 218.7f),
                        PathNode.QuadTo(1049.2f, 218.7f, 1058.7f, 229.2f),
                        PathNode.QuadTo(1068.2f, 239.7f, 1068.2f, 254.2f),
                        PathNode.QuadTo(1068.2f, 268.7f, 1059.2f, 279.7f),
                        PathNode.Close,
                        PathNode.MoveTo(745.2f, 181.7f),
                        PathNode.LineTo(728.2f, 214.7f),
                        PathNode.QuadTo(717.2f, 240.7f, 678.2f, 234.7f),
                        PathNode.QuadTo(605.2f, 222.7f, 530.2f, 241.2f),
                        PathNode.QuadTo(455.2f, 259.7f, 389.7f, 307.7f),
                        PathNode.QuadTo(324.2f, 355.7f, 282.2f, 428.7f),
                        PathNode.QuadTo(243.2f, 496.7f, 232.7f, 569.7f),
                        PathNode.QuadTo(222.2f, 642.7f, 237.2f, 710.7f),
                        PathNode.QuadTo(252.2f, 778.7f, 287.2f, 834.7f),
                        PathNode.QuadTo(291.2f, 840.7f, 296.2f, 841.7f),
                        PathNode.QuadTo(301.2f, 842.7f, 305.2f, 837.7f),
                        PathNode.LineTo(319.2f, 821.7f),
                        PathNode.QuadTo(333.2f, 806.7f, 353.2f, 811.7f),
                        PathNode.QuadTo(373.2f, 816.7f, 379.2f, 838.7f),
                        PathNode.LineTo(418.2f, 980.7f),
                        PathNode.QuadTo(424.2f, 1004.7f, 414.2f, 1020.7f),
                        PathNode.QuadTo(404.2f, 1036.7f, 382.2f, 1035.7f),
                        PathNode.LineTo(227.2f, 1031.7f),
                        PathNode.QuadTo(212.2f, 1030.7f, 202.7f, 1021.2f),
                        PathNode.QuadTo(193.2f, 1011.7f, 192.2f, 998.2f),
                        PathNode.QuadTo(191.2f, 984.7f, 198.2f, 973.7f),
                        PathNode.LineTo(212.2f, 954.7f),
                        PathNode.QuadTo(218.2f, 946.7f, 218.2f, 939.7f),
                        PathNode.QuadTo(218.2f, 932.7f, 211.2f, 922.7f),
                        PathNode.QuadTo(156.2f, 848.7f, 132.2f, 755.2f),
                        PathNode.QuadTo(108.2f, 661.7f, 120.7f, 561.7f),
                        PathNode.QuadTo(133.2f, 461.7f, 185.2f, 371.7f),
                        PathNode.QuadTo(240.2f, 276.7f, 327.7f, 213.7f),
                        PathNode.QuadTo(415.2f, 150.7f, 516.2f, 127.7f),
                        PathNode.QuadTo(617.2f, 104.7f, 714.2f, 125.7f),
                        PathNode.QuadTo(737.2f, 130.7f, 745.7f, 147.2f),
                        PathNode.QuadTo(754.2f, 163.7f, 745.2f, 181.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _refreshDemibold!!
    }

private var _refreshDemibold: ImageVector? = null
