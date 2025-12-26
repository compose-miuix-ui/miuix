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

val MiuixIcons.Music: ImageVector
    get() = MiuixIcons.Regular.Music

val MiuixIcons.Light.Music: ImageVector
    get() {
        if (_musicLight != null) return _musicLight!!
        _musicLight = ImageVector.Builder(
            name = "Music.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1161.6f,
            viewportHeight = 1161.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -91.20000000000005f, translationY = 930.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(493.0f, 65.0f),
                        PathNode.LineTo(568.0f, 598.0f),
                        PathNode.QuadTo(572.0f, 633.0f, 577.0f, 656.0f),
                        PathNode.QuadTo(581.0f, 676.0f, 591.0f, 688.5f),
                        PathNode.QuadTo(601.0f, 701.0f, 618.0f, 709.0f),
                        PathNode.QuadTo(628.0f, 714.0f, 647.1612903225807f, 715.0f),
                        PathNode.QuadTo(666.3225806451613f, 716.0f, 682.0f, 716.0f),
                        PathNode.HorizontalTo(687.0f),
                        PathNode.HorizontalTo(990.0f),
                        PathNode.HorizontalTo(997.0f),
                        PathNode.QuadTo(1014.0f, 716.0f, 1031.5f, 715.0f),
                        PathNode.QuadTo(1049.0f, 714.0f, 1062.0f, 709.0f),
                        PathNode.QuadTo(1079.0f, 701.5957446808511f, 1086.8461538461538f, 687.4042553191489f),
                        PathNode.QuadTo(1094.6923076923076f, 673.2127659574468f, 1096.0f, 651.0f),
                        PathNode.QuadTo(1097.0f, 632.4193548387096f, 1094.0f, 587.0f),
                        PathNode.LineTo(1073.047619047619f, 207.46927374301677f),
                        PathNode.QuadTo(1072.0f, 201.0f, 1069.0f, 199.5f),
                        PathNode.QuadTo(1066.0f, 198.0f, 1060.0f, 202.0f),
                        PathNode.QuadTo(1015.0f, 232.0f, 972.0f, 232.0f),
                        PathNode.QuadTo(930.3471074380166f, 232.0f, 895.1735537190083f, 211.5f),
                        PathNode.QuadTo(860.0f, 191.0f, 839.5f, 155.82644628099172f),
                        PathNode.QuadTo(819.0f, 120.65289256198346f, 819.0f, 79.0f),
                        PathNode.QuadTo(819.0f, 37.34710743801653f, 839.5f, 2.1735537190082646f),
                        PathNode.QuadTo(860.0f, -33.0f, 895.20245398773f, -53.5f),
                        PathNode.QuadTo(930.4049079754601f, -74.0f, 972.0920245398773f, -74.0f),
                        PathNode.QuadTo(1011.0f, -74.0f, 1044.9421487603306f, -55.46788990825688f),
                        PathNode.QuadTo(1078.8842975206612f, -36.93577981651376f, 1100.4421487603306f, -4.967889908256879f),
                        PathNode.QuadTo(1122.0f, 27.0f, 1124.0f, 65.0f),
                        PathNode.LineTo(1153.0f, 585.0f),
                        PathNode.QuadTo(1156.0f, 624.0f, 1156.0f, 652.0f),
                        PathNode.QuadTo(1154.0f, 683.0f, 1145.0526315789473f, 702.9756097560976f),
                        PathNode.QuadTo(1137.0f, 722.0f, 1122.5f, 736.5f),
                        PathNode.QuadTo(1108.0f, 751.0f, 1089.0f, 760.0f),
                        PathNode.QuadTo(1066.0f, 774.0f, 1032.0f, 774.0f),
                        PathNode.HorizontalTo(651.0f),
                        PathNode.QuadTo(616.0f, 774.0f, 590.0f, 761.0f),
                        PathNode.QuadTo(556.0f, 745.0f, 536.0f, 713.0f),
                        PathNode.QuadTo(521.0f, 687.0f, 518.0f, 656.0f),
                        PathNode.LineTo(453.03125f, 197.21380846325167f),
                        PathNode.QuadTo(452.0f, 191.0f, 448.5f, 190.0f),
                        PathNode.QuadTo(445.0f, 189.0f, 440.0f, 193.0f),
                        PathNode.QuadTo(395.0f, 232.0f, 341.0f, 232.0f),
                        PathNode.QuadTo(299.3471074380165f, 232.0f, 264.1735537190083f, 211.5f),
                        PathNode.QuadTo(229.0f, 191.0f, 208.5f, 155.82644628099172f),
                        PathNode.QuadTo(188.0f, 120.65289256198346f, 188.0f, 79.0f),
                        PathNode.QuadTo(188.0f, 37.34710743801653f, 208.5f, 2.1735537190082646f),
                        PathNode.QuadTo(229.0f, -33.0f, 264.1735537190083f, -53.5f),
                        PathNode.QuadTo(299.3471074380165f, -74.0f, 341.0f, -74.0f),
                        PathNode.QuadTo(379.8301886792453f, -74.0f, 412.6509433962264f, -55.48148148148148f),
                        PathNode.QuadTo(445.47169811320754f, -36.96296296296296f, 466.7358490566038f, -5.481481481481481f),
                        PathNode.QuadTo(488.0f, 26.0f, 493.0f, 65.0f),
                        PathNode.Close,
                        PathNode.MoveTo(247.0f, 78.78947368421052f),
                        PathNode.QuadTo(247.0f, 117.0f, 274.7894736842105f, 145.0f),
                        PathNode.QuadTo(302.57894736842104f, 173.0f, 340.7894736842105f, 173.0f),
                        PathNode.QuadTo(379.0f, 173.0f, 407.0f, 145.32432432432432f),
                        PathNode.QuadTo(435.0f, 117.64864864864865f, 435.0f, 79.5945945945946f),
                        PathNode.QuadTo(435.0f, 45.0f, 408.9473684210526f, 15.0f),
                        PathNode.QuadTo(382.89473684210526f, -15.0f, 341.2105263157895f, -15.0f),
                        PathNode.QuadTo(303.0f, -15.0f, 275.0f, 12.789473684210527f),
                        PathNode.QuadTo(247.0f, 40.578947368421055f, 247.0f, 78.78947368421052f),
                        PathNode.Close,
                        PathNode.MoveTo(878.0f, 79.0f),
                        PathNode.QuadTo(878.0f, 118.0f, 905.0f, 145.5f),
                        PathNode.QuadTo(932.0f, 173.0f, 972.0895522388059f, 173.0f),
                        PathNode.QuadTo(1011.0f, 173.0f, 1038.0f, 145.5f),
                        PathNode.QuadTo(1065.0f, 118.0f, 1065.0f, 79.45132743362832f),
                        PathNode.QuadTo(1065.0f, 40.902654867256636f, 1037.5833333333333f, 13.451327433628318f),
                        PathNode.QuadTo(1010.1666666666666f, -14.0f, 971.6666666666666f, -14.0f),
                        PathNode.QuadTo(932.0f, -14.0f, 905.0f, 13.0f),
                        PathNode.QuadTo(878.0f, 40.0f, 878.0f, 79.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _musicLight!!
    }

private var _musicLight: ImageVector? = null

val MiuixIcons.Regular.Music: ImageVector
    get() {
        if (_musicRegular != null) return _musicRegular!!
        _musicRegular = ImageVector.Builder(
            name = "Music.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1192.8f,
            viewportHeight = 1192.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -75.60000000000002f, translationY = 946.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(506.0f, 63.0f),
                        PathNode.LineTo(581.0f, 596.0f),
                        PathNode.QuadTo(585.0f, 629.0f, 590.0f, 653.0f),
                        PathNode.QuadTo(594.0f, 670.0f, 602.0f, 680.0f),
                        PathNode.QuadTo(610.0f, 690.0f, 624.0f, 697.0f),
                        PathNode.QuadTo(633.0f, 702.0f, 655.0f, 702.5f),
                        PathNode.QuadTo(677.0f, 703.0f, 695.0f, 703.0f),
                        PathNode.HorizontalTo(704.0f),
                        PathNode.HorizontalTo(972.0f),
                        PathNode.HorizontalTo(985.0f),
                        PathNode.QuadTo(1004.0f, 703.0f, 1024.5f, 702.5f),
                        PathNode.QuadTo(1045.0f, 702.0f, 1057.0f, 697.0f),
                        PathNode.QuadTo(1070.0f, 691.0f, 1076.0f, 679.5f),
                        PathNode.QuadTo(1082.0f, 668.0f, 1083.0f, 650.0f),
                        PathNode.QuadTo(1084.0f, 632.0f, 1081.0f, 588.0f),
                        PathNode.LineTo(1061.0f, 236.0f),
                        PathNode.QuadTo(1060.0f, 230.0f, 1055.5f, 227.5f),
                        PathNode.QuadTo(1051.0f, 225.0f, 1044.0f, 229.0f),
                        PathNode.QuadTo(1009.0f, 245.0f, 972.0f, 245.0f),
                        PathNode.QuadTo(927.0f, 245.0f, 889.0f, 222.5f),
                        PathNode.QuadTo(851.0f, 200.0f, 828.5f, 162.0f),
                        PathNode.QuadTo(806.0f, 124.0f, 806.0f, 79.0f),
                        PathNode.QuadTo(806.0f, 34.0f, 828.5f, -4.0f),
                        PathNode.QuadTo(851.0f, -42.0f, 889.0f, -64.5f),
                        PathNode.QuadTo(927.0f, -87.0f, 972.0f, -87.0f),
                        PathNode.QuadTo(1014.0f, -87.0f, 1051.0f, -67.0f),
                        PathNode.QuadTo(1088.0f, -47.0f, 1111.5f, -12.5f),
                        PathNode.QuadTo(1135.0f, 22.0f, 1137.0f, 64.0f),
                        PathNode.LineTo(1166.0f, 584.0f),
                        PathNode.QuadTo(1169.0f, 625.0f, 1169.0f, 652.0f),
                        PathNode.QuadTo(1167.0f, 688.0f, 1157.0f, 709.0f),
                        PathNode.QuadTo(1148.0f, 729.0f, 1132.5f, 745.0f),
                        PathNode.QuadTo(1117.0f, 761.0f, 1096.0f, 772.0f),
                        PathNode.QuadTo(1071.0f, 787.0f, 1041.0f, 787.0f),
                        PathNode.HorizontalTo(642.0f),
                        PathNode.QuadTo(615.0f, 787.0f, 588.0f, 775.0f),
                        PathNode.QuadTo(549.0f, 756.0f, 525.0f, 720.0f),
                        PathNode.QuadTo(509.0f, 693.0f, 506.0f, 671.0f),
                        PathNode.LineTo(443.0f, 228.0f),
                        PathNode.QuadTo(442.0f, 222.0f, 438.0f, 220.0f),
                        PathNode.QuadTo(434.0f, 218.0f, 428.0f, 221.0f),
                        PathNode.QuadTo(385.0f, 245.0f, 341.0f, 245.0f),
                        PathNode.QuadTo(296.0f, 245.0f, 258.0f, 222.5f),
                        PathNode.QuadTo(220.0f, 200.0f, 197.5f, 162.0f),
                        PathNode.QuadTo(175.0f, 124.0f, 175.0f, 79.0f),
                        PathNode.QuadTo(175.0f, 34.0f, 197.5f, -4.0f),
                        PathNode.QuadTo(220.0f, -42.0f, 258.0f, -64.5f),
                        PathNode.QuadTo(296.0f, -87.0f, 341.0f, -87.0f),
                        PathNode.QuadTo(383.0f, -87.0f, 418.5f, -67.0f),
                        PathNode.QuadTo(454.0f, -47.0f, 477.0f, -13.0f),
                        PathNode.QuadTo(500.0f, 21.0f, 506.0f, 63.0f),
                        PathNode.Close,
                        PathNode.MoveTo(260.0f, 79.0f),
                        PathNode.QuadTo(260.0f, 112.0f, 284.0f, 136.0f),
                        PathNode.QuadTo(308.0f, 160.0f, 341.0f, 160.0f),
                        PathNode.QuadTo(374.0f, 160.0f, 398.0f, 136.0f),
                        PathNode.QuadTo(422.0f, 112.0f, 422.0f, 79.0f),
                        PathNode.QuadTo(422.0f, 49.0f, 399.5f, 23.5f),
                        PathNode.QuadTo(377.0f, -2.0f, 341.0f, -2.0f),
                        PathNode.QuadTo(308.0f, -2.0f, 284.0f, 22.0f),
                        PathNode.QuadTo(260.0f, 46.0f, 260.0f, 79.0f),
                        PathNode.Close,
                        PathNode.MoveTo(891.0f, 79.0f),
                        PathNode.QuadTo(891.0f, 112.0f, 914.5f, 136.0f),
                        PathNode.QuadTo(938.0f, 160.0f, 972.0f, 160.0f),
                        PathNode.QuadTo(1005.0f, 160.0f, 1028.5f, 136.0f),
                        PathNode.QuadTo(1052.0f, 112.0f, 1052.0f, 79.0f),
                        PathNode.QuadTo(1052.0f, 46.0f, 1028.5f, 22.5f),
                        PathNode.QuadTo(1005.0f, -1.0f, 972.0f, -1.0f),
                        PathNode.QuadTo(938.0f, -1.0f, 914.5f, 22.5f),
                        PathNode.QuadTo(891.0f, 46.0f, 891.0f, 79.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _musicRegular!!
    }

private var _musicRegular: ImageVector? = null

val MiuixIcons.Heavy.Music: ImageVector
    get() {
        if (_musicHeavy != null) return _musicHeavy!!
        _musicHeavy = ImageVector.Builder(
            name = "Music.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1226.3999999999999f,
            viewportHeight = 1226.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -58.80000000000007f, translationY = 963.1999999999999f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(520.0f, 61.0f),
                        PathNode.LineTo(595.0f, 594.0f),
                        PathNode.QuadTo(600.0f, 634.0f, 603.0f, 650.0f),
                        PathNode.QuadTo(606.0f, 664.0f, 612.0f, 671.5f),
                        PathNode.QuadTo(618.0f, 679.0f, 630.0f, 685.0f),
                        PathNode.QuadTo(636.0f, 688.0f, 655.5f, 688.5f),
                        PathNode.QuadTo(675.0f, 689.0f, 692.0f, 689.0f),
                        PathNode.HorizontalTo(704.0f),
                        PathNode.HorizontalTo(972.0f),
                        PathNode.HorizontalTo(986.0f),
                        PathNode.QuadTo(1004.0f, 689.0f, 1023.0f, 688.5f),
                        PathNode.QuadTo(1042.0f, 688.0f, 1052.0f, 684.0f),
                        PathNode.QuadTo(1061.0f, 680.0f, 1064.5f, 672.0f),
                        PathNode.QuadTo(1068.0f, 664.0f, 1069.0f, 649.0f),
                        PathNode.QuadTo(1070.0f, 633.0f, 1067.0f, 589.0f),
                        PathNode.LineTo(1048.0f, 253.0f),
                        PathNode.QuadTo(1048.0f, 247.0f, 1045.0f, 245.5f),
                        PathNode.QuadTo(1042.0f, 244.0f, 1036.0f, 246.0f),
                        PathNode.QuadTo(1008.0f, 259.0f, 972.0f, 259.0f),
                        PathNode.QuadTo(923.0f, 259.0f, 882.0f, 234.5f),
                        PathNode.QuadTo(841.0f, 210.0f, 816.5f, 169.0f),
                        PathNode.QuadTo(792.0f, 128.0f, 792.0f, 79.0f),
                        PathNode.QuadTo(792.0f, 30.0f, 816.5f, -11.0f),
                        PathNode.QuadTo(841.0f, -52.0f, 882.0f, -76.5f),
                        PathNode.QuadTo(923.0f, -101.0f, 972.0f, -101.0f),
                        PathNode.QuadTo(1018.0f, -101.0f, 1058.0f, -79.0f),
                        PathNode.QuadTo(1098.0f, -57.0f, 1123.0f, -19.5f),
                        PathNode.QuadTo(1148.0f, 18.0f, 1151.0f, 63.0f),
                        PathNode.LineTo(1180.0f, 583.0f),
                        PathNode.QuadTo(1183.0f, 625.0f, 1183.0f, 652.0f),
                        PathNode.QuadTo(1181.0f, 690.0f, 1169.0f, 715.0f),
                        PathNode.QuadTo(1159.0f, 737.0f, 1142.0f, 755.0f),
                        PathNode.QuadTo(1125.0f, 773.0f, 1103.0f, 784.0f),
                        PathNode.QuadTo(1074.0f, 801.0f, 1041.0f, 801.0f),
                        PathNode.HorizontalTo(642.0f),
                        PathNode.QuadTo(613.0f, 801.0f, 582.0f, 787.0f),
                        PathNode.QuadTo(537.0f, 765.0f, 514.0f, 727.0f),
                        PathNode.QuadTo(496.0f, 698.0f, 492.0f, 673.0f),
                        PathNode.LineTo(432.0f, 248.0f),
                        PathNode.QuadTo(431.0f, 241.0f, 427.5f, 239.5f),
                        PathNode.QuadTo(424.0f, 238.0f, 419.0f, 241.0f),
                        PathNode.QuadTo(383.0f, 259.0f, 341.0f, 259.0f),
                        PathNode.QuadTo(292.0f, 259.0f, 251.0f, 234.5f),
                        PathNode.QuadTo(210.0f, 210.0f, 185.5f, 169.0f),
                        PathNode.QuadTo(161.0f, 128.0f, 161.0f, 79.0f),
                        PathNode.QuadTo(161.0f, 30.0f, 185.5f, -11.0f),
                        PathNode.QuadTo(210.0f, -52.0f, 251.0f, -76.5f),
                        PathNode.QuadTo(292.0f, -101.0f, 341.0f, -101.0f),
                        PathNode.QuadTo(386.0f, -101.0f, 425.0f, -79.5f),
                        PathNode.QuadTo(464.0f, -58.0f, 489.0f, -21.0f),
                        PathNode.QuadTo(514.0f, 16.0f, 520.0f, 61.0f),
                        PathNode.Close,
                        PathNode.MoveTo(274.0f, 79.0f),
                        PathNode.QuadTo(274.0f, 107.0f, 293.5f, 126.5f),
                        PathNode.QuadTo(313.0f, 146.0f, 341.0f, 146.0f),
                        PathNode.QuadTo(369.0f, 146.0f, 388.5f, 126.5f),
                        PathNode.QuadTo(408.0f, 107.0f, 408.0f, 79.0f),
                        PathNode.QuadTo(408.0f, 54.0f, 389.5f, 33.0f),
                        PathNode.QuadTo(371.0f, 12.0f, 341.0f, 12.0f),
                        PathNode.QuadTo(313.0f, 12.0f, 293.5f, 31.5f),
                        PathNode.QuadTo(274.0f, 51.0f, 274.0f, 79.0f),
                        PathNode.Close,
                        PathNode.MoveTo(905.0f, 79.0f),
                        PathNode.QuadTo(905.0f, 107.0f, 924.5f, 126.5f),
                        PathNode.QuadTo(944.0f, 146.0f, 972.0f, 146.0f),
                        PathNode.QuadTo(999.0f, 146.0f, 1018.5f, 126.5f),
                        PathNode.QuadTo(1038.0f, 107.0f, 1038.0f, 79.0f),
                        PathNode.QuadTo(1038.0f, 51.0f, 1019.0f, 32.0f),
                        PathNode.QuadTo(1000.0f, 13.0f, 972.0f, 13.0f),
                        PathNode.QuadTo(944.0f, 13.0f, 924.5f, 32.0f),
                        PathNode.QuadTo(905.0f, 51.0f, 905.0f, 79.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _musicHeavy!!
    }

private var _musicHeavy: ImageVector? = null
