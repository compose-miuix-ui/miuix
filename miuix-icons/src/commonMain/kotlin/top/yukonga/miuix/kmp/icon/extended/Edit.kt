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

val MiuixIcons.Edit: ImageVector
    get() = MiuixIcons.Regular.Edit

val MiuixIcons.Light.Edit: ImageVector
    get() {
        if (_editLight != null) return _editLight!!
        _editLight = ImageVector.Builder(
            name = "Edit.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1124.3999999999999f,
            viewportHeight = 1124.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -127.06315789473695f, translationY = 926.6999999999999f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1008.0f, -84.0f),
                        PathNode.QuadTo(1059.0f, -58.0f, 1085.0f, -7.0f),
                        PathNode.QuadTo(1099.0f, 19.0f, 1102.0f, 57.0f),
                        PathNode.QuadTo(1105.0f, 95.0f, 1105.0f, 187.0f),
                        PathNode.VerticalTo(529.0f),
                        PathNode.QuadTo(1105.0f, 538.0f, 1100.0f, 542.0f),
                        PathNode.QuadTo(1095.0f, 546.0f, 1088.5f, 545.5f),
                        PathNode.QuadTo(1082.0f, 545.0f, 1078.0f, 541.0f),
                        PathNode.LineTo(1059.0f, 521.0f),
                        PathNode.QuadTo(1051.0f, 514.0f, 1048.0f, 505.0f),
                        PathNode.QuadTo(1045.0f, 496.0f, 1045.0f, 483.0f),
                        PathNode.VerticalTo(144.0f),
                        PathNode.QuadTo(1045.0f, 92.0f, 1042.5f, 66.5f),
                        PathNode.QuadTo(1040.0f, 41.0f, 1032.0f, 23.0f),
                        PathNode.QuadTo(1015.0f, -14.0f, 979.0f, -31.0f),
                        PathNode.QuadTo(961.0f, -40.0f, 936.5f, -42.0f),
                        PathNode.QuadTo(912.0f, -44.0f, 858.0f, -44.0f),
                        PathNode.HorizontalTo(472.0f),
                        PathNode.QuadTo(421.0f, -44.0f, 395.0f, -41.5f),
                        PathNode.QuadTo(369.0f, -39.0f, 351.0f, -31.0f),
                        PathNode.QuadTo(317.0f, -15.0f, 297.0f, 23.0f),
                        PathNode.QuadTo(288.0f, 41.0f, 286.0f, 65.0f),
                        PathNode.QuadTo(284.0f, 89.0f, 284.0f, 144.0f),
                        PathNode.VerticalTo(546.0f),
                        PathNode.QuadTo(284.0f, 601.0f, 286.0f, 625.5f),
                        PathNode.QuadTo(288.0f, 650.0f, 297.0f, 668.0f),
                        PathNode.QuadTo(317.0f, 707.0f, 351.0f, 721.0f),
                        PathNode.QuadTo(369.0f, 730.0f, 393.5f, 732.0f),
                        PathNode.QuadTo(418.0f, 734.0f, 472.0f, 734.0f),
                        PathNode.HorizontalTo(825.0f),
                        PathNode.QuadTo(850.0f, 734.0f, 863.0f, 746.0f),
                        PathNode.LineTo(884.0f, 766.0f),
                        PathNode.QuadTo(891.0f, 773.0f, 891.0f, 779.5f),
                        PathNode.QuadTo(891.0f, 786.0f, 886.0f, 790.0f),
                        PathNode.QuadTo(881.0f, 794.0f, 873.0f, 794.0f),
                        PathNode.HorizontalTo(516.0f),
                        PathNode.QuadTo(426.0f, 794.0f, 387.0f, 790.5f),
                        PathNode.QuadTo(348.0f, 787.0f, 321.0f, 774.0f),
                        PathNode.QuadTo(269.0f, 747.0f, 244.0f, 697.0f),
                        PathNode.QuadTo(231.0f, 671.0f, 228.0f, 633.0f),
                        PathNode.QuadTo(225.0f, 595.0f, 225.0f, 503.0f),
                        PathNode.VerticalTo(187.0f),
                        PathNode.QuadTo(225.0f, 95.0f, 228.0f, 57.0f),
                        PathNode.QuadTo(231.0f, 19.0f, 244.0f, -7.0f),
                        PathNode.QuadTo(271.0f, -58.0f, 321.0f, -84.0f),
                        PathNode.QuadTo(347.0f, -98.0f, 385.0f, -101.0f),
                        PathNode.QuadTo(423.0f, -104.0f, 516.0f, -104.0f),
                        PathNode.HorizontalTo(814.0f),
                        PathNode.QuadTo(906.0f, -104.0f, 944.0f, -101.0f),
                        PathNode.QuadTo(982.0f, -98.0f, 1008.0f, -84.0f),
                        PathNode.Close,
                        PathNode.MoveTo(678.0f, 288.0f),
                        PathNode.LineTo(1145.0f, 756.0f),
                        PathNode.QuadTo(1154.0f, 766.0f, 1153.5f, 780.0f),
                        PathNode.QuadTo(1153.0f, 794.0f, 1146.0f, 802.0f),
                        PathNode.LineTo(1123.0f, 825.0f),
                        PathNode.QuadTo(1115.0f, 833.0f, 1100.5f, 833.0f),
                        PathNode.QuadTo(1086.0f, 833.0f, 1074.0f, 823.0f),
                        PathNode.LineTo(609.0f, 357.0f),
                        PathNode.QuadTo(576.0f, 324.0f, 553.0f, 283.0f),
                        PathNode.LineTo(512.0f, 208.0f),
                        PathNode.QuadTo(509.0f, 202.0f, 510.5f, 197.0f),
                        PathNode.QuadTo(512.0f, 192.0f, 517.0f, 190.5f),
                        PathNode.QuadTo(522.0f, 189.0f, 527.0f, 191.0f),
                        PathNode.LineTo(603.0f, 233.0f),
                        PathNode.QuadTo(641.0f, 254.0f, 678.0f, 288.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editLight!!
    }

private var _editLight: ImageVector? = null

val MiuixIcons.Regular.Edit: ImageVector
    get() {
        if (_editRegular != null) return _editRegular!!
        _editRegular = ImageVector.Builder(
            name = "Edit.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1155.675f,
            viewportHeight = 1155.675f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -111.49008620689654f, translationY = 942.36875f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1014.0f, -96.0f),
                        PathNode.QuadTo(1069.0f, -68.0f, 1097.0f, -13.0f),
                        PathNode.QuadTo(1111.0f, 15.0f, 1114.5f, 54.5f),
                        PathNode.QuadTo(1118.0f, 94.0f, 1118.0f, 187.0f),
                        PathNode.VerticalTo(522.0f),
                        PathNode.QuadTo(1118.0f, 536.0f, 1110.5f, 542.5f),
                        PathNode.QuadTo(1103.0f, 549.0f, 1093.5f, 548.5f),
                        PathNode.QuadTo(1084.0f, 548.0f, 1078.0f, 542.0f),
                        PathNode.LineTo(1051.0f, 514.0f),
                        PathNode.QuadTo(1040.0f, 503.0f, 1036.0f, 490.0f),
                        PathNode.QuadTo(1032.0f, 477.0f, 1032.0f, 458.0f),
                        PathNode.VerticalTo(144.0f),
                        PathNode.QuadTo(1032.0f, 90.0f, 1030.0f, 67.5f),
                        PathNode.QuadTo(1028.0f, 45.0f, 1020.0f, 29.0f),
                        PathNode.QuadTo(1005.0f, -3.0f, 973.0f, -19.0f),
                        PathNode.QuadTo(956.0f, -27.0f, 933.5f, -29.0f),
                        PathNode.QuadTo(911.0f, -31.0f, 858.0f, -31.0f),
                        PathNode.HorizontalTo(472.0f),
                        PathNode.QuadTo(419.0f, -31.0f, 396.0f, -29.0f),
                        PathNode.QuadTo(373.0f, -27.0f, 357.0f, -19.0f),
                        PathNode.QuadTo(327.0f, -4.0f, 309.0f, 29.0f),
                        PathNode.QuadTo(301.0f, 45.0f, 299.0f, 67.5f),
                        PathNode.QuadTo(297.0f, 90.0f, 297.0f, 144.0f),
                        PathNode.VerticalTo(546.0f),
                        PathNode.QuadTo(297.0f, 600.0f, 299.0f, 622.5f),
                        PathNode.QuadTo(301.0f, 645.0f, 309.0f, 662.0f),
                        PathNode.QuadTo(327.0f, 695.0f, 357.0f, 709.0f),
                        PathNode.QuadTo(373.0f, 717.0f, 396.0f, 719.0f),
                        PathNode.QuadTo(419.0f, 721.0f, 472.0f, 721.0f),
                        PathNode.HorizontalTo(799.0f),
                        PathNode.QuadTo(838.0f, 721.0f, 854.0f, 736.0f),
                        PathNode.LineTo(884.0f, 764.0f),
                        PathNode.QuadTo(894.0f, 774.0f, 894.0f, 784.0f),
                        PathNode.QuadTo(894.0f, 794.0f, 886.0f, 800.5f),
                        PathNode.QuadTo(878.0f, 807.0f, 867.0f, 807.0f),
                        PathNode.HorizontalTo(516.0f),
                        PathNode.QuadTo(423.0f, 807.0f, 383.0f, 803.5f),
                        PathNode.QuadTo(343.0f, 800.0f, 315.0f, 786.0f),
                        PathNode.QuadTo(261.0f, 758.0f, 232.0f, 703.0f),
                        PathNode.QuadTo(218.0f, 675.0f, 215.0f, 635.5f),
                        PathNode.QuadTo(212.0f, 596.0f, 212.0f, 503.0f),
                        PathNode.VerticalTo(187.0f),
                        PathNode.QuadTo(212.0f, 94.0f, 215.0f, 54.5f),
                        PathNode.QuadTo(218.0f, 15.0f, 232.0f, -13.0f),
                        PathNode.QuadTo(261.0f, -68.0f, 315.0f, -96.0f),
                        PathNode.QuadTo(343.0f, -110.0f, 383.0f, -113.5f),
                        PathNode.QuadTo(423.0f, -117.0f, 516.0f, -117.0f),
                        PathNode.HorizontalTo(814.0f),
                        PathNode.QuadTo(907.0f, -117.0f, 946.5f, -113.5f),
                        PathNode.QuadTo(986.0f, -110.0f, 1014.0f, -96.0f),
                        PathNode.Close,
                        PathNode.MoveTo(687.0f, 279.0f),
                        PathNode.LineTo(1155.0f, 748.0f),
                        PathNode.QuadTo(1168.0f, 761.0f, 1166.5f, 781.0f),
                        PathNode.QuadTo(1165.0f, 801.0f, 1155.0f, 811.0f),
                        PathNode.LineTo(1132.0f, 834.0f),
                        PathNode.QuadTo(1121.0f, 845.0f, 1100.5f, 846.0f),
                        PathNode.QuadTo(1080.0f, 847.0f, 1065.0f, 832.0f),
                        PathNode.LineTo(600.0f, 366.0f),
                        PathNode.QuadTo(566.0f, 333.0f, 541.0f, 290.0f),
                        PathNode.LineTo(487.0f, 189.0f),
                        PathNode.QuadTo(484.0f, 183.0f, 487.0f, 176.5f),
                        PathNode.QuadTo(490.0f, 170.0f, 496.5f, 167.0f),
                        PathNode.QuadTo(503.0f, 164.0f, 509.0f, 167.0f),
                        PathNode.LineTo(610.0f, 221.0f),
                        PathNode.QuadTo(651.0f, 244.0f, 687.0f, 279.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editRegular!!
    }

private var _editRegular: ImageVector? = null

val MiuixIcons.Heavy.Edit: ImageVector
    get() {
        if (_editHeavy != null) return _editHeavy!!
        _editHeavy = ImageVector.Builder(
            name = "Edit.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1189.26f,
            viewportHeight = 1189.26f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -94.39941176470586f, translationY = 959.155f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1020.0f, -108.0f),
                        PathNode.QuadTo(1080.0f, -79.0f, 1109.0f, -19.0f),
                        PathNode.QuadTo(1125.0f, 11.0f, 1128.5f, 52.5f),
                        PathNode.QuadTo(1132.0f, 94.0f, 1132.0f, 187.0f),
                        PathNode.VerticalTo(485.0f),
                        PathNode.QuadTo(1132.0f, 505.0f, 1120.5f, 515.5f),
                        PathNode.QuadTo(1109.0f, 526.0f, 1093.5f, 525.5f),
                        PathNode.QuadTo(1078.0f, 525.0f, 1068.0f, 515.0f),
                        PathNode.LineTo(1041.0f, 487.0f),
                        PathNode.QuadTo(1028.0f, 474.0f, 1023.0f, 458.0f),
                        PathNode.QuadTo(1018.0f, 442.0f, 1018.0f, 421.0f),
                        PathNode.VerticalTo(144.0f),
                        PathNode.QuadTo(1018.0f, 91.0f, 1016.5f, 70.0f),
                        PathNode.QuadTo(1015.0f, 49.0f, 1008.0f, 35.0f),
                        PathNode.QuadTo(994.0f, 7.0f, 967.0f, -7.0f),
                        PathNode.QuadTo(952.0f, -14.0f, 931.5f, -15.5f),
                        PathNode.QuadTo(911.0f, -17.0f, 858.0f, -17.0f),
                        PathNode.HorizontalTo(472.0f),
                        PathNode.QuadTo(419.0f, -17.0f, 398.0f, -15.5f),
                        PathNode.QuadTo(377.0f, -14.0f, 363.0f, -7.0f),
                        PathNode.QuadTo(336.0f, 7.0f, 321.0f, 35.0f),
                        PathNode.QuadTo(314.0f, 49.0f, 312.5f, 70.0f),
                        PathNode.QuadTo(311.0f, 91.0f, 311.0f, 144.0f),
                        PathNode.VerticalTo(546.0f),
                        PathNode.QuadTo(311.0f, 599.0f, 312.5f, 620.0f),
                        PathNode.QuadTo(314.0f, 641.0f, 321.0f, 656.0f),
                        PathNode.QuadTo(336.0f, 684.0f, 363.0f, 697.0f),
                        PathNode.QuadTo(377.0f, 704.0f, 398.0f, 705.5f),
                        PathNode.QuadTo(419.0f, 707.0f, 472.0f, 707.0f),
                        PathNode.HorizontalTo(754.0f),
                        PathNode.QuadTo(796.0f, 707.0f, 818.0f, 726.0f),
                        PathNode.LineTo(848.0f, 754.0f),
                        PathNode.QuadTo(863.0f, 769.0f, 862.5f, 784.5f),
                        PathNode.QuadTo(862.0f, 800.0f, 850.0f, 810.5f),
                        PathNode.QuadTo(838.0f, 821.0f, 822.0f, 821.0f),
                        PathNode.HorizontalTo(516.0f),
                        PathNode.QuadTo(422.0f, 821.0f, 380.5f, 817.5f),
                        PathNode.QuadTo(339.0f, 814.0f, 309.0f, 798.0f),
                        PathNode.QuadTo(251.0f, 769.0f, 220.0f, 709.0f),
                        PathNode.QuadTo(205.0f, 679.0f, 201.5f, 638.0f),
                        PathNode.QuadTo(198.0f, 597.0f, 198.0f, 503.0f),
                        PathNode.VerticalTo(187.0f),
                        PathNode.QuadTo(198.0f, 94.0f, 201.5f, 52.5f),
                        PathNode.QuadTo(205.0f, 11.0f, 220.0f, -19.0f),
                        PathNode.QuadTo(251.0f, -79.0f, 309.0f, -108.0f),
                        PathNode.QuadTo(339.0f, -124.0f, 380.5f, -127.5f),
                        PathNode.QuadTo(422.0f, -131.0f, 516.0f, -131.0f),
                        PathNode.HorizontalTo(814.0f),
                        PathNode.QuadTo(908.0f, -131.0f, 949.0f, -127.5f),
                        PathNode.QuadTo(990.0f, -124.0f, 1020.0f, -108.0f),
                        PathNode.Close,
                        PathNode.MoveTo(697.0f, 269.0f),
                        PathNode.LineTo(1165.0f, 738.0f),
                        PathNode.QuadTo(1181.0f, 755.0f, 1180.0f, 780.5f),
                        PathNode.QuadTo(1179.0f, 806.0f, 1165.0f, 821.0f),
                        PathNode.LineTo(1142.0f, 844.0f),
                        PathNode.QuadTo(1126.0f, 859.0f, 1100.5f, 860.0f),
                        PathNode.QuadTo(1075.0f, 861.0f, 1055.0f, 842.0f),
                        PathNode.LineTo(590.0f, 376.0f),
                        PathNode.QuadTo(555.0f, 341.0f, 529.0f, 297.0f),
                        PathNode.LineTo(475.0f, 195.0f),
                        PathNode.QuadTo(469.0f, 183.0f, 474.0f, 171.0f),
                        PathNode.QuadTo(479.0f, 159.0f, 491.0f, 154.0f),
                        PathNode.QuadTo(503.0f, 149.0f, 515.0f, 155.0f),
                        PathNode.LineTo(617.0f, 209.0f),
                        PathNode.QuadTo(662.0f, 235.0f, 697.0f, 269.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editHeavy!!
    }

private var _editHeavy: ImageVector? = null
