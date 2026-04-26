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
            viewportWidth = 1124.4f,
            viewportHeight = 1124.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1124.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(880.7f, 113.7f),
                        PathNode.QuadTo(931.7f, 139.7f, 957.7f, 190.7f),
                        PathNode.QuadTo(971.7f, 216.7f, 974.7f, 254.7f),
                        PathNode.QuadTo(977.7f, 292.7f, 977.7f, 384.7f),
                        PathNode.VerticalTo(726.7f),
                        PathNode.QuadTo(977.7f, 735.7f, 972.7f, 739.7f),
                        PathNode.QuadTo(967.7f, 743.7f, 961.2f, 743.2f),
                        PathNode.QuadTo(954.7f, 742.7f, 950.7f, 738.7f),
                        PathNode.LineTo(931.7f, 718.7f),
                        PathNode.QuadTo(923.7f, 711.7f, 920.7f, 702.7f),
                        PathNode.QuadTo(917.7f, 693.7f, 917.7f, 680.7f),
                        PathNode.VerticalTo(341.7f),
                        PathNode.QuadTo(917.7f, 289.7f, 915.2f, 264.2f),
                        PathNode.QuadTo(912.7f, 238.7f, 904.7f, 220.7f),
                        PathNode.QuadTo(887.7f, 183.7f, 851.7f, 166.7f),
                        PathNode.QuadTo(833.7f, 157.7f, 809.2f, 155.7f),
                        PathNode.QuadTo(784.7f, 153.7f, 730.7f, 153.7f),
                        PathNode.HorizontalTo(344.7f),
                        PathNode.QuadTo(293.7f, 153.7f, 267.7f, 156.2f),
                        PathNode.QuadTo(241.7f, 158.7f, 223.7f, 166.7f),
                        PathNode.QuadTo(189.7f, 182.7f, 169.7f, 220.7f),
                        PathNode.QuadTo(160.7f, 238.7f, 158.7f, 262.7f),
                        PathNode.QuadTo(156.7f, 286.7f, 156.7f, 341.7f),
                        PathNode.VerticalTo(743.7f),
                        PathNode.QuadTo(156.7f, 798.7f, 158.7f, 823.2f),
                        PathNode.QuadTo(160.7f, 847.7f, 169.7f, 865.7f),
                        PathNode.QuadTo(189.7f, 904.7f, 223.7f, 918.7f),
                        PathNode.QuadTo(241.7f, 927.7f, 266.2f, 929.7f),
                        PathNode.QuadTo(290.7f, 931.7f, 344.7f, 931.7f),
                        PathNode.HorizontalTo(697.7f),
                        PathNode.QuadTo(722.7f, 931.7f, 735.7f, 943.7f),
                        PathNode.LineTo(756.7f, 963.7f),
                        PathNode.QuadTo(763.7f, 970.7f, 763.7f, 977.2f),
                        PathNode.QuadTo(763.7f, 983.7f, 758.7f, 987.7f),
                        PathNode.QuadTo(753.7f, 991.7f, 745.7f, 991.7f),
                        PathNode.HorizontalTo(388.7f),
                        PathNode.QuadTo(298.7f, 991.7f, 259.7f, 988.2f),
                        PathNode.QuadTo(220.7f, 984.7f, 193.7f, 971.7f),
                        PathNode.QuadTo(141.7f, 944.7f, 116.7f, 894.7f),
                        PathNode.QuadTo(103.7f, 868.7f, 100.7f, 830.7f),
                        PathNode.QuadTo(97.7f, 792.7f, 97.7f, 700.7f),
                        PathNode.VerticalTo(384.7f),
                        PathNode.QuadTo(97.7f, 292.7f, 100.7f, 254.7f),
                        PathNode.QuadTo(103.7f, 216.7f, 116.7f, 190.7f),
                        PathNode.QuadTo(143.7f, 139.7f, 193.7f, 113.7f),
                        PathNode.QuadTo(219.7f, 99.7f, 257.7f, 96.7f),
                        PathNode.QuadTo(295.7f, 93.7f, 388.7f, 93.7f),
                        PathNode.HorizontalTo(686.7f),
                        PathNode.QuadTo(778.7f, 93.7f, 816.7f, 96.7f),
                        PathNode.QuadTo(854.7f, 99.7f, 880.7f, 113.7f),
                        PathNode.Close,
                        PathNode.MoveTo(550.7f, 485.7f),
                        PathNode.LineTo(1017.7f, 953.7f),
                        PathNode.QuadTo(1026.7f, 963.7f, 1026.2f, 977.7f),
                        PathNode.QuadTo(1025.7f, 991.7f, 1018.7f, 999.7f),
                        PathNode.LineTo(995.7f, 1022.7f),
                        PathNode.QuadTo(987.7f, 1030.7f, 973.2f, 1030.7f),
                        PathNode.QuadTo(958.7f, 1030.7f, 946.7f, 1020.7f),
                        PathNode.LineTo(481.7f, 554.7f),
                        PathNode.QuadTo(448.7f, 521.7f, 425.7f, 480.7f),
                        PathNode.LineTo(384.7f, 405.7f),
                        PathNode.QuadTo(381.7f, 399.7f, 383.2f, 394.7f),
                        PathNode.QuadTo(384.7f, 389.7f, 389.7f, 388.2f),
                        PathNode.QuadTo(394.7f, 386.7f, 399.7f, 388.7f),
                        PathNode.LineTo(475.7f, 430.7f),
                        PathNode.QuadTo(513.7f, 451.7f, 550.7f, 485.7f),
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

val MiuixIcons.Normal.Edit: ImageVector
    get() {
        if (_editNormal != null) return _editNormal!!
        _editNormal = ImageVector.Builder(
            name = "Edit.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1146.7f,
            viewportHeight = 1146.7f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1146.7f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(895.6f, 116.2f),
                        PathNode.QuadTo(949.4f, 143.6f, 976.7f, 197.4f),
                        PathNode.QuadTo(990.7f, 224.7f, 994.1f, 263.8f),
                        PathNode.QuadTo(997.4f, 302.8f, 997.4f, 395.5f),
                        PathNode.VerticalTo(732.7f),
                        PathNode.QuadTo(997.4f, 745.1f, 990.7f, 750.8f),
                        PathNode.QuadTo(984.0f, 756.6f, 975.4f, 756.1f),
                        PathNode.QuadTo(966.9f, 755.6f, 961.5f, 750.2f),
                        PathNode.LineTo(937.0f, 724.7f),
                        PathNode.QuadTo(926.9f, 714.9f, 923.2f, 703.2f),
                        PathNode.QuadTo(919.6f, 691.4f, 919.6f, 674.3f),
                        PathNode.VerticalTo(352.5f),
                        PathNode.QuadTo(919.6f, 299.1f, 917.4f, 275.7f),
                        PathNode.QuadTo(915.2f, 252.2f, 907.2f, 235.6f),
                        PathNode.QuadTo(891.6f, 202.1f, 858.4f, 185.7f),
                        PathNode.QuadTo(841.1f, 177.4f, 817.9f, 175.4f),
                        PathNode.QuadTo(794.8f, 173.4f, 741.5f, 173.4f),
                        PathNode.HorizontalTo(355.5f),
                        PathNode.QuadTo(303.1f, 173.4f, 279.2f, 175.6f),
                        PathNode.QuadTo(255.2f, 177.7f, 238.6f, 185.7f),
                        PathNode.QuadTo(207.4f, 201.1f, 188.7f, 235.6f),
                        PathNode.QuadTo(180.4f, 252.2f, 178.4f, 275.2f),
                        PathNode.QuadTo(176.4f, 298.2f, 176.4f, 352.5f),
                        PathNode.VerticalTo(754.5f),
                        PathNode.QuadTo(176.4f, 808.8f, 178.4f, 831.9f),
                        PathNode.QuadTo(180.4f, 855.1f, 188.7f, 872.4f),
                        PathNode.QuadTo(207.4f, 907.2f, 238.6f, 921.2f),
                        PathNode.QuadTo(255.2f, 929.6f, 278.7f, 931.6f),
                        PathNode.QuadTo(302.2f, 933.6f, 355.5f, 933.6f),
                        PathNode.HorizontalTo(690.6f),
                        PathNode.QuadTo(725.2f, 933.6f, 740.3f, 947.6f),
                        PathNode.LineTo(767.5f, 973.1f),
                        PathNode.QuadTo(776.6f, 982.2f, 776.6f, 991.1f),
                        PathNode.QuadTo(776.6f, 1000.0f, 769.5f, 1005.7f),
                        PathNode.QuadTo(762.4f, 1011.4f, 752.4f, 1011.4f),
                        PathNode.HorizontalTo(399.5f),
                        PathNode.QuadTo(307.4f, 1011.4f, 267.7f, 1007.9f),
                        PathNode.QuadTo(228.1f, 1004.4f, 200.4f, 990.7f),
                        PathNode.QuadTo(147.0f, 963.1f, 119.2f, 909.6f),
                        PathNode.QuadTo(105.6f, 882.2f, 102.6f, 843.2f),
                        PathNode.QuadTo(99.6f, 804.2f, 99.6f, 711.5f),
                        PathNode.VerticalTo(395.5f),
                        PathNode.QuadTo(99.6f, 302.8f, 102.6f, 263.8f),
                        PathNode.QuadTo(105.6f, 224.7f, 119.2f, 197.4f),
                        PathNode.QuadTo(147.6f, 143.6f, 200.4f, 116.2f),
                        PathNode.QuadTo(227.7f, 102.2f, 267.1f, 98.9f),
                        PathNode.QuadTo(306.5f, 95.6f, 399.5f, 95.6f),
                        PathNode.HorizontalTo(697.5f),
                        PathNode.QuadTo(790.2f, 95.6f, 829.2f, 98.9f),
                        PathNode.QuadTo(868.2f, 102.2f, 895.6f, 116.2f),
                        PathNode.Close,
                        PathNode.MoveTo(567.7f, 490.3f),
                        PathNode.LineTo(1035.4f, 959.0f),
                        PathNode.QuadTo(1047.1f, 971.1f, 1045.9f, 989.2f),
                        PathNode.QuadTo(1044.7f, 1007.3f, 1035.7f, 1016.7f),
                        PathNode.LineTo(1012.7f, 1039.7f),
                        PathNode.QuadTo(1002.6f, 1049.7f, 984.0f, 1050.4f),
                        PathNode.QuadTo(965.4f, 1051.1f, 951.3f, 1037.7f),
                        PathNode.LineTo(486.3f, 571.7f),
                        PathNode.QuadTo(452.6f, 538.7f, 428.2f, 496.3f),
                        PathNode.LineTo(378.3f, 403.4f),
                        PathNode.QuadTo(375.3f, 397.4f, 377.8f, 391.4f),
                        PathNode.QuadTo(380.4f, 385.4f, 386.4f, 382.8f),
                        PathNode.QuadTo(392.4f, 380.3f, 398.1f, 383.0f),
                        PathNode.LineTo(491.3f, 433.2f),
                        PathNode.QuadTo(531.4f, 455.6f, 567.7f, 490.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editNormal!!
    }

private var _editNormal: ImageVector? = null

val MiuixIcons.Regular.Edit: ImageVector
    get() {
        if (_editRegular != null) return _editRegular!!
        _editRegular = ImageVector.Builder(
            name = "Edit.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1156.8f,
            viewportHeight = 1156.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1156.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(902.4f, 117.4f),
                        PathNode.QuadTo(957.4f, 145.4f, 985.4f, 200.4f),
                        PathNode.QuadTo(999.4f, 228.4f, 1002.9f, 267.9f),
                        PathNode.QuadTo(1006.4f, 307.4f, 1006.4f, 400.4f),
                        PathNode.VerticalTo(735.4f),
                        PathNode.QuadTo(1006.4f, 749.4f, 998.9f, 755.9f),
                        PathNode.QuadTo(991.4f, 762.4f, 981.9f, 761.9f),
                        PathNode.QuadTo(972.4f, 761.4f, 966.4f, 755.4f),
                        PathNode.LineTo(939.4f, 727.4f),
                        PathNode.QuadTo(928.4f, 716.4f, 924.4f, 703.4f),
                        PathNode.QuadTo(920.4f, 690.4f, 920.4f, 671.4f),
                        PathNode.VerticalTo(357.4f),
                        PathNode.QuadTo(920.4f, 303.4f, 918.4f, 280.9f),
                        PathNode.QuadTo(916.4f, 258.4f, 908.4f, 242.4f),
                        PathNode.QuadTo(893.4f, 210.4f, 861.4f, 194.4f),
                        PathNode.QuadTo(844.4f, 186.4f, 821.9f, 184.4f),
                        PathNode.QuadTo(799.4f, 182.4f, 746.4f, 182.4f),
                        PathNode.HorizontalTo(360.4f),
                        PathNode.QuadTo(307.4f, 182.4f, 284.4f, 184.4f),
                        PathNode.QuadTo(261.4f, 186.4f, 245.4f, 194.4f),
                        PathNode.QuadTo(215.4f, 209.4f, 197.4f, 242.4f),
                        PathNode.QuadTo(189.4f, 258.4f, 187.4f, 280.9f),
                        PathNode.QuadTo(185.4f, 303.4f, 185.4f, 357.4f),
                        PathNode.VerticalTo(759.4f),
                        PathNode.QuadTo(185.4f, 813.4f, 187.4f, 835.9f),
                        PathNode.QuadTo(189.4f, 858.4f, 197.4f, 875.4f),
                        PathNode.QuadTo(215.4f, 908.4f, 245.4f, 922.4f),
                        PathNode.QuadTo(261.4f, 930.4f, 284.4f, 932.4f),
                        PathNode.QuadTo(307.4f, 934.4f, 360.4f, 934.4f),
                        PathNode.HorizontalTo(687.4f),
                        PathNode.QuadTo(726.4f, 934.4f, 742.4f, 949.4f),
                        PathNode.LineTo(772.4f, 977.4f),
                        PathNode.QuadTo(782.4f, 987.4f, 782.4f, 997.4f),
                        PathNode.QuadTo(782.4f, 1007.4f, 774.4f, 1013.9f),
                        PathNode.QuadTo(766.4f, 1020.4f, 755.4f, 1020.4f),
                        PathNode.HorizontalTo(404.4f),
                        PathNode.QuadTo(311.4f, 1020.4f, 271.4f, 1016.9f),
                        PathNode.QuadTo(231.4f, 1013.4f, 203.4f, 999.4f),
                        PathNode.QuadTo(149.4f, 971.4f, 120.4f, 916.4f),
                        PathNode.QuadTo(106.4f, 888.4f, 103.4f, 848.9f),
                        PathNode.QuadTo(100.4f, 809.4f, 100.4f, 716.4f),
                        PathNode.VerticalTo(400.4f),
                        PathNode.QuadTo(100.4f, 307.4f, 103.4f, 267.9f),
                        PathNode.QuadTo(106.4f, 228.4f, 120.4f, 200.4f),
                        PathNode.QuadTo(149.4f, 145.4f, 203.4f, 117.4f),
                        PathNode.QuadTo(231.4f, 103.4f, 271.4f, 99.9f),
                        PathNode.QuadTo(311.4f, 96.4f, 404.4f, 96.4f),
                        PathNode.HorizontalTo(702.4f),
                        PathNode.QuadTo(795.4f, 96.4f, 834.9f, 99.9f),
                        PathNode.QuadTo(874.4f, 103.4f, 902.4f, 117.4f),
                        PathNode.Close,
                        PathNode.MoveTo(575.4f, 492.4f),
                        PathNode.LineTo(1043.4f, 961.4f),
                        PathNode.QuadTo(1056.4f, 974.4f, 1054.9f, 994.4f),
                        PathNode.QuadTo(1053.4f, 1014.4f, 1043.4f, 1024.4f),
                        PathNode.LineTo(1020.4f, 1047.4f),
                        PathNode.QuadTo(1009.4f, 1058.4f, 988.9f, 1059.4f),
                        PathNode.QuadTo(968.4f, 1060.4f, 953.4f, 1045.4f),
                        PathNode.LineTo(488.4f, 579.4f),
                        PathNode.QuadTo(454.4f, 546.4f, 429.4f, 503.4f),
                        PathNode.LineTo(375.4f, 402.4f),
                        PathNode.QuadTo(372.4f, 396.4f, 375.4f, 389.9f),
                        PathNode.QuadTo(378.4f, 383.4f, 384.9f, 380.4f),
                        PathNode.QuadTo(391.4f, 377.4f, 397.4f, 380.4f),
                        PathNode.LineTo(498.4f, 434.4f),
                        PathNode.QuadTo(539.4f, 457.4f, 575.4f, 492.4f),
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

val MiuixIcons.Medium.Edit: ImageVector
    get() {
        if (_editMedium != null) return _editMedium!!
        _editMedium = ImageVector.Builder(
            name = "Edit.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1176.6f,
            viewportHeight = 1176.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1176.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(916.1f, 120.2f),
                        PathNode.QuadTo(974.0f, 148.8f, 1002.6f, 206.8f),
                        PathNode.QuadTo(1017.8f, 235.9f, 1021.3f, 276.6f),
                        PathNode.QuadTo(1024.8f, 317.3f, 1024.8f, 410.3f),
                        PathNode.VerticalTo(723.5f),
                        PathNode.QuadTo(1024.8f, 741.0f, 1015.0f, 749.9f),
                        PathNode.QuadTo(1005.1f, 758.8f, 992.1f, 758.3f),
                        PathNode.QuadTo(979.0f, 757.8f, 970.7f, 749.4f),
                        PathNode.LineTo(943.7f, 721.4f),
                        PathNode.QuadTo(931.5f, 709.2f, 926.9f, 694.5f),
                        PathNode.QuadTo(922.3f, 679.7f, 922.3f, 659.5f),
                        PathNode.VerticalTo(367.3f),
                        PathNode.QuadTo(922.3f, 313.9f, 920.6f, 292.3f),
                        PathNode.QuadTo(918.9f, 270.6f, 911.5f, 255.8f),
                        PathNode.QuadTo(897.1f, 226.2f, 868.0f, 211.3f),
                        PathNode.QuadTo(852.2f, 203.9f, 830.9f, 202.2f),
                        PathNode.QuadTo(809.6f, 200.5f, 756.6f, 200.5f),
                        PathNode.HorizontalTo(370.6f),
                        PathNode.QuadTo(317.6f, 200.5f, 295.8f, 202.2f),
                        PathNode.QuadTo(273.9f, 203.9f, 259.1f, 211.3f),
                        PathNode.QuadTo(230.9f, 225.8f, 214.6f, 255.8f),
                        PathNode.QuadTo(207.2f, 270.6f, 205.5f, 292.3f),
                        PathNode.QuadTo(203.8f, 313.9f, 203.8f, 367.3f),
                        PathNode.VerticalTo(769.3f),
                        PathNode.QuadTo(203.8f, 822.7f, 205.5f, 844.3f),
                        PathNode.QuadTo(207.2f, 865.9f, 214.6f, 881.8f),
                        PathNode.QuadTo(230.9f, 911.8f, 259.1f, 925.2f),
                        PathNode.QuadTo(273.9f, 932.6f, 295.8f, 934.3f),
                        PathNode.QuadTo(317.6f, 936.0f, 370.6f, 936.0f),
                        PathNode.HorizontalTo(671.1f),
                        PathNode.QuadTo(711.9f, 936.0f, 731.4f, 953.4f),
                        PathNode.LineTo(761.4f, 981.4f),
                        PathNode.QuadTo(774.3f, 994.3f, 774.0f, 1007.6f),
                        PathNode.QuadTo(773.8f, 1020.8f, 763.4f, 1029.7f),
                        PathNode.QuadTo(753.0f, 1038.5f, 739.1f, 1038.5f),
                        PathNode.HorizontalTo(414.6f),
                        PathNode.QuadTo(321.0f, 1038.5f, 280.1f, 1035.0f),
                        PathNode.QuadTo(239.2f, 1031.5f, 210.0f, 1016.3f),
                        PathNode.QuadTo(153.7f, 987.8f, 123.5f, 929.8f),
                        PathNode.QuadTo(108.9f, 900.6f, 105.6f, 860.3f),
                        PathNode.QuadTo(102.3f, 819.9f, 102.3f, 726.3f),
                        PathNode.VerticalTo(410.3f),
                        PathNode.QuadTo(102.3f, 317.3f, 105.6f, 276.6f),
                        PathNode.QuadTo(108.9f, 235.9f, 123.5f, 206.8f),
                        PathNode.QuadTo(153.7f, 148.8f, 210.0f, 120.2f),
                        PathNode.QuadTo(239.2f, 105.0f, 280.1f, 101.5f),
                        PathNode.QuadTo(321.0f, 98.0f, 414.6f, 98.0f),
                        PathNode.HorizontalTo(712.6f),
                        PathNode.QuadTo(806.2f, 98.0f, 846.5f, 101.5f),
                        PathNode.QuadTo(886.9f, 105.0f, 916.1f, 120.2f),
                        PathNode.Close,
                        PathNode.MoveTo(591.5f, 496.4f),
                        PathNode.LineTo(1059.5f, 965.4f),
                        PathNode.QuadTo(1074.2f, 980.8f, 1073.0f, 1004.0f),
                        PathNode.QuadTo(1071.8f, 1027.2f, 1059.5f, 1040.2f),
                        PathNode.LineTo(1036.5f, 1063.2f),
                        PathNode.QuadTo(1022.5f, 1076.5f, 999.1f, 1077.5f),
                        PathNode.QuadTo(975.6f, 1078.5f, 957.7f, 1061.2f),
                        PathNode.LineTo(492.7f, 595.2f),
                        PathNode.QuadTo(458.1f, 561.0f, 432.5f, 517.4f),
                        PathNode.LineTo(378.5f, 415.8f),
                        PathNode.QuadTo(373.8f, 406.3f, 377.9f, 396.5f),
                        PathNode.QuadTo(382.1f, 386.8f, 391.8f, 382.6f),
                        PathNode.QuadTo(401.6f, 378.5f, 411.1f, 383.2f),
                        PathNode.LineTo(512.7f, 437.2f),
                        PathNode.QuadTo(556.0f, 462.0f, 591.5f, 496.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editMedium!!
    }

private var _editMedium: ImageVector? = null

val MiuixIcons.Demibold.Edit: ImageVector
    get() {
        if (_editDemibold != null) return _editDemibold!!
        _editDemibold = ImageVector.Builder(
            name = "Edit.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1190.4f,
            viewportHeight = 1190.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1190.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(925.7f, 122.2f),
                        PathNode.QuadTo(985.7f, 151.2f, 1014.7f, 211.2f),
                        PathNode.QuadTo(1030.7f, 241.2f, 1034.2f, 282.7f),
                        PathNode.QuadTo(1037.7f, 324.2f, 1037.7f, 417.2f),
                        PathNode.VerticalTo(715.2f),
                        PathNode.QuadTo(1037.7f, 735.2f, 1026.2f, 745.7f),
                        PathNode.QuadTo(1014.7f, 756.2f, 999.2f, 755.7f),
                        PathNode.QuadTo(983.7f, 755.2f, 973.7f, 745.2f),
                        PathNode.LineTo(946.7f, 717.2f),
                        PathNode.QuadTo(933.7f, 704.2f, 928.7f, 688.2f),
                        PathNode.QuadTo(923.7f, 672.2f, 923.7f, 651.2f),
                        PathNode.VerticalTo(374.2f),
                        PathNode.QuadTo(923.7f, 321.2f, 922.2f, 300.2f),
                        PathNode.QuadTo(920.7f, 279.2f, 913.7f, 265.2f),
                        PathNode.QuadTo(899.7f, 237.2f, 872.7f, 223.2f),
                        PathNode.QuadTo(857.7f, 216.2f, 837.2f, 214.7f),
                        PathNode.QuadTo(816.7f, 213.2f, 763.7f, 213.2f),
                        PathNode.HorizontalTo(377.7f),
                        PathNode.QuadTo(324.7f, 213.2f, 303.7f, 214.7f),
                        PathNode.QuadTo(282.7f, 216.2f, 268.7f, 223.2f),
                        PathNode.QuadTo(241.7f, 237.2f, 226.7f, 265.2f),
                        PathNode.QuadTo(219.7f, 279.2f, 218.2f, 300.2f),
                        PathNode.QuadTo(216.7f, 321.2f, 216.7f, 374.2f),
                        PathNode.VerticalTo(776.2f),
                        PathNode.QuadTo(216.7f, 829.2f, 218.2f, 850.2f),
                        PathNode.QuadTo(219.7f, 871.2f, 226.7f, 886.2f),
                        PathNode.QuadTo(241.7f, 914.2f, 268.7f, 927.2f),
                        PathNode.QuadTo(282.7f, 934.2f, 303.7f, 935.7f),
                        PathNode.QuadTo(324.7f, 937.2f, 377.7f, 937.2f),
                        PathNode.HorizontalTo(659.7f),
                        PathNode.QuadTo(701.7f, 937.2f, 723.7f, 956.2f),
                        PathNode.LineTo(753.7f, 984.2f),
                        PathNode.QuadTo(768.7f, 999.2f, 768.2f, 1014.7f),
                        PathNode.QuadTo(767.7f, 1030.2f, 755.7f, 1040.7f),
                        PathNode.QuadTo(743.7f, 1051.2f, 727.7f, 1051.2f),
                        PathNode.HorizontalTo(421.7f),
                        PathNode.QuadTo(327.7f, 1051.2f, 286.2f, 1047.7f),
                        PathNode.QuadTo(244.7f, 1044.2f, 214.7f, 1028.2f),
                        PathNode.QuadTo(156.7f, 999.2f, 125.7f, 939.2f),
                        PathNode.QuadTo(110.7f, 909.2f, 107.2f, 868.2f),
                        PathNode.QuadTo(103.7f, 827.2f, 103.7f, 733.2f),
                        PathNode.VerticalTo(417.2f),
                        PathNode.QuadTo(103.7f, 324.2f, 107.2f, 282.7f),
                        PathNode.QuadTo(110.7f, 241.2f, 125.7f, 211.2f),
                        PathNode.QuadTo(156.7f, 151.2f, 214.7f, 122.2f),
                        PathNode.QuadTo(244.7f, 106.2f, 286.2f, 102.7f),
                        PathNode.QuadTo(327.7f, 99.2f, 421.7f, 99.2f),
                        PathNode.HorizontalTo(719.7f),
                        PathNode.QuadTo(813.7f, 99.2f, 854.7f, 102.7f),
                        PathNode.QuadTo(895.7f, 106.2f, 925.7f, 122.2f),
                        PathNode.Close,
                        PathNode.MoveTo(602.7f, 499.2f),
                        PathNode.LineTo(1070.7f, 968.2f),
                        PathNode.QuadTo(1086.7f, 985.2f, 1085.7f, 1010.7f),
                        PathNode.QuadTo(1084.7f, 1036.2f, 1070.7f, 1051.2f),
                        PathNode.LineTo(1047.7f, 1074.2f),
                        PathNode.QuadTo(1031.7f, 1089.2f, 1006.2f, 1090.2f),
                        PathNode.QuadTo(980.7f, 1091.2f, 960.7f, 1072.2f),
                        PathNode.LineTo(495.7f, 606.2f),
                        PathNode.QuadTo(460.7f, 571.2f, 434.7f, 527.2f),
                        PathNode.LineTo(380.7f, 425.2f),
                        PathNode.QuadTo(374.7f, 413.2f, 379.7f, 401.2f),
                        PathNode.QuadTo(384.7f, 389.2f, 396.7f, 384.2f),
                        PathNode.QuadTo(408.7f, 379.2f, 420.7f, 385.2f),
                        PathNode.LineTo(522.7f, 439.2f),
                        PathNode.QuadTo(567.7f, 465.2f, 602.7f, 499.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editDemibold!!
    }

private var _editDemibold: ImageVector? = null
