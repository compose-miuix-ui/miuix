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

val MiuixIcons.Glass.Tasks: ImageVector
    get() = MiuixIcons.Glass.Regular.Tasks

val MiuixIcons.Glass.Light.Tasks: ImageVector
    get() {
        if (_tasksLight != null) return _tasksLight!!
        _tasksLight = ImageVector.Builder(
            name = "Tasks.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(939.3f, 137.2f),
                        PathNode.QuadTo(982.9f, 159.0f, 1005.7f, 202.5f),
                        PathNode.QuadTo(1016.8f, 224.9f, 1019.3f, 257.9f),
                        PathNode.QuadTo(1021.9f, 290.9f, 1021.9f, 371.0f),
                        PathNode.LineTo(1021.9f, 771.4f),
                        PathNode.QuadTo(1021.9f, 852.1f, 1019.3f, 884.8f),
                        PathNode.QuadTo(1016.8f, 917.5f, 1005.7f, 939.9f),
                        PathNode.QuadTo(982.9f, 983.9f, 939.3f, 1005.7f),
                        PathNode.QuadTo(917.5f, 1016.8f, 884.8f, 1019.3f),
                        PathNode.QuadTo(852.1f, 1021.9f, 771.4f, 1021.9f),
                        PathNode.LineTo(371.4f, 1021.9f),
                        PathNode.QuadTo(290.8f, 1021.9f, 258.0f, 1019.3f),
                        PathNode.QuadTo(225.3f, 1016.8f, 203.0f, 1005.7f),
                        PathNode.QuadTo(160.0f, 983.3f, 137.2f, 939.9f),
                        PathNode.QuadTo(126.1f, 917.5f, 123.5f, 884.8f),
                        PathNode.QuadTo(121.0f, 852.1f, 121.0f, 771.4f),
                        PathNode.LineTo(121.0f, 371.0f),
                        PathNode.QuadTo(121.0f, 290.9f, 123.5f, 257.9f),
                        PathNode.QuadTo(126.1f, 224.9f, 137.2f, 202.5f),
                        PathNode.QuadTo(160.0f, 159.5f, 203.0f, 137.2f),
                        PathNode.QuadTo(225.3f, 126.1f, 258.0f, 123.5f),
                        PathNode.QuadTo(290.8f, 121.0f, 371.4f, 121.0f),
                        PathNode.LineTo(771.4f, 121.0f),
                        PathNode.QuadTo(852.1f, 121.0f, 884.8f, 123.5f),
                        PathNode.QuadTo(917.5f, 126.1f, 939.3f, 137.2f),
                        PathNode.Close,
                        PathNode.MoveTo(492.3f, 370.7f),
                        PathNode.LineTo(301.0f, 563.3f),
                        PathNode.QuadTo(296.8f, 567.5f, 296.8f, 573.7f),
                        PathNode.QuadTo(296.8f, 579.8f, 301.0f, 584.4f),
                        PathNode.LineTo(311.8f, 595.7f),
                        PathNode.QuadTo(316.5f, 600.9f, 322.6f, 600.7f),
                        PathNode.QuadTo(328.7f, 600.4f, 333.4f, 595.7f),
                        PathNode.LineTo(506.9f, 421.3f),
                        PathNode.LineTo(813.2f, 820.5f),
                        PathNode.QuadTo(816.9f, 825.3f, 823.2f, 826.3f),
                        PathNode.QuadTo(829.5f, 827.3f, 834.3f, 823.7f),
                        PathNode.LineTo(846.7f, 813.4f),
                        PathNode.QuadTo(851.4f, 810.2f, 852.2f, 803.9f),
                        PathNode.QuadTo(853.0f, 797.5f, 849.3f, 792.8f),
                        PathNode.LineTo(526.8f, 372.8f),
                        PathNode.QuadTo(519.5f, 363.9f, 510.1f, 363.1f),
                        PathNode.QuadTo(500.7f, 362.3f, 492.3f, 370.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _tasksLight!!
    }

private var _tasksLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Tasks: ImageVector
    get() {
        if (_tasksNormal != null) return _tasksNormal!!
        _tasksNormal = ImageVector.Builder(
            name = "Tasks.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(944.2f, 127.7f),
                        PathNode.QuadTo(990.8f, 151.0f, 1015.1f, 197.7f),
                        PathNode.QuadTo(1026.8f, 221.8f, 1029.7f, 256.2f),
                        PathNode.QuadTo(1032.6f, 290.6f, 1032.6f, 371.3f),
                        PathNode.LineTo(1032.6f, 771.4f),
                        PathNode.QuadTo(1032.6f, 853.0f, 1029.7f, 887.0f),
                        PathNode.QuadTo(1026.8f, 920.9f, 1015.1f, 945.1f),
                        PathNode.QuadTo(990.8f, 991.8f, 944.2f, 1015.1f),
                        PathNode.QuadTo(920.9f, 1026.8f, 887.0f, 1029.7f),
                        PathNode.QuadTo(853.0f, 1032.6f, 771.4f, 1032.6f),
                        PathNode.LineTo(371.4f, 1032.6f),
                        PathNode.QuadTo(289.8f, 1032.6f, 255.9f, 1029.7f),
                        PathNode.QuadTo(222.0f, 1026.8f, 197.8f, 1015.1f),
                        PathNode.QuadTo(152.0f, 991.0f, 127.7f, 945.1f),
                        PathNode.QuadTo(116.0f, 920.9f, 113.2f, 887.0f),
                        PathNode.QuadTo(110.3f, 853.0f, 110.3f, 771.4f),
                        PathNode.LineTo(110.3f, 371.3f),
                        PathNode.QuadTo(110.3f, 290.6f, 113.2f, 256.2f),
                        PathNode.QuadTo(116.0f, 221.8f, 127.7f, 197.7f),
                        PathNode.QuadTo(152.0f, 151.9f, 197.8f, 127.7f),
                        PathNode.QuadTo(222.0f, 116.0f, 255.9f, 113.2f),
                        PathNode.QuadTo(289.8f, 110.3f, 371.4f, 110.3f),
                        PathNode.LineTo(771.4f, 110.3f),
                        PathNode.QuadTo(853.0f, 110.3f, 887.0f, 113.2f),
                        PathNode.QuadTo(920.9f, 116.0f, 944.2f, 127.7f),
                        PathNode.Close,
                        PathNode.MoveTo(489.0f, 371.9f),
                        PathNode.LineTo(304.0f, 557.2f),
                        PathNode.QuadTo(298.6f, 562.7f, 298.6f, 570.7f),
                        PathNode.QuadTo(298.6f, 578.8f, 304.0f, 584.4f),
                        PathNode.LineTo(316.3f, 596.9f),
                        PathNode.QuadTo(322.7f, 603.3f, 330.4f, 603.2f),
                        PathNode.QuadTo(338.1f, 603.2f, 344.4f, 596.9f),
                        PathNode.LineTo(506.6f, 434.5f),
                        PathNode.LineTo(801.0f, 818.4f),
                        PathNode.QuadTo(805.6f, 824.7f, 813.7f, 825.7f),
                        PathNode.QuadTo(821.9f, 826.7f, 828.2f, 822.1f),
                        PathNode.LineTo(842.4f, 810.7f),
                        PathNode.QuadTo(848.7f, 806.2f, 849.6f, 798.1f),
                        PathNode.QuadTo(850.5f, 789.9f, 846.0f, 783.6f),
                        PathNode.LineTo(531.3f, 374.6f),
                        PathNode.QuadTo(522.9f, 363.6f, 511.0f, 362.6f),
                        PathNode.QuadTo(499.1f, 361.7f, 489.0f, 371.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _tasksNormal!!
    }

private var _tasksNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Tasks: ImageVector
    get() {
        if (_tasksRegular != null) return _tasksRegular!!
        _tasksRegular = ImageVector.Builder(
            name = "Tasks.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(946.4f, 123.4f),
                        PathNode.QuadTo(994.4f, 147.4f, 1019.4f, 195.4f),
                        PathNode.QuadTo(1031.4f, 220.4f, 1034.4f, 255.4f),
                        PathNode.QuadTo(1037.4f, 290.4f, 1037.4f, 371.4f),
                        PathNode.LineTo(1037.4f, 771.4f),
                        PathNode.QuadTo(1037.4f, 853.4f, 1034.4f, 887.9f),
                        PathNode.QuadTo(1031.4f, 922.4f, 1019.4f, 947.4f),
                        PathNode.QuadTo(994.4f, 995.4f, 946.4f, 1019.4f),
                        PathNode.QuadTo(922.4f, 1031.4f, 887.9f, 1034.4f),
                        PathNode.QuadTo(853.4f, 1037.4f, 771.4f, 1037.4f),
                        PathNode.LineTo(371.4f, 1037.4f),
                        PathNode.QuadTo(289.4f, 1037.4f, 254.9f, 1034.4f),
                        PathNode.QuadTo(220.4f, 1031.4f, 195.4f, 1019.4f),
                        PathNode.QuadTo(148.4f, 994.4f, 123.4f, 947.4f),
                        PathNode.QuadTo(111.4f, 922.4f, 108.4f, 887.9f),
                        PathNode.QuadTo(105.4f, 853.4f, 105.4f, 771.4f),
                        PathNode.LineTo(105.4f, 371.4f),
                        PathNode.QuadTo(105.4f, 290.4f, 108.4f, 255.4f),
                        PathNode.QuadTo(111.4f, 220.4f, 123.4f, 195.4f),
                        PathNode.QuadTo(148.4f, 148.4f, 195.4f, 123.4f),
                        PathNode.QuadTo(220.4f, 111.4f, 254.9f, 108.4f),
                        PathNode.QuadTo(289.4f, 105.4f, 371.4f, 105.4f),
                        PathNode.LineTo(771.4f, 105.4f),
                        PathNode.QuadTo(853.4f, 105.4f, 887.9f, 108.4f),
                        PathNode.QuadTo(922.4f, 111.4f, 946.4f, 123.4f),
                        PathNode.Close,
                        PathNode.MoveTo(487.4f, 372.4f),
                        PathNode.LineTo(305.4f, 554.4f),
                        PathNode.QuadTo(299.4f, 560.4f, 299.4f, 569.4f),
                        PathNode.QuadTo(299.4f, 578.4f, 305.4f, 584.4f),
                        PathNode.LineTo(318.4f, 597.4f),
                        PathNode.QuadTo(325.4f, 604.4f, 333.9f, 604.4f),
                        PathNode.QuadTo(342.4f, 604.4f, 349.4f, 597.4f),
                        PathNode.LineTo(506.4f, 440.4f),
                        PathNode.LineTo(795.4f, 817.4f),
                        PathNode.QuadTo(800.4f, 824.4f, 809.4f, 825.4f),
                        PathNode.QuadTo(818.4f, 826.4f, 825.4f, 821.4f),
                        PathNode.LineTo(840.4f, 809.4f),
                        PathNode.QuadTo(847.4f, 804.4f, 848.4f, 795.4f),
                        PathNode.QuadTo(849.4f, 786.4f, 844.4f, 779.4f),
                        PathNode.LineTo(533.4f, 375.4f),
                        PathNode.QuadTo(524.4f, 363.4f, 511.4f, 362.4f),
                        PathNode.QuadTo(498.4f, 361.4f, 487.4f, 372.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _tasksRegular!!
    }

private var _tasksRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Tasks: ImageVector
    get() {
        if (_tasksMedium != null) return _tasksMedium!!
        _tasksMedium = ImageVector.Builder(
            name = "Tasks.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(949.1f, 116.4f),
                        PathNode.QuadTo(1000.1f, 141.8f, 1026.5f, 192.7f),
                        PathNode.QuadTo(1039.3f, 219.1f, 1042.4f, 255.2f),
                        PathNode.QuadTo(1045.5f, 291.2f, 1045.5f, 372.5f),
                        PathNode.LineTo(1045.5f, 770.3f),
                        PathNode.QuadTo(1045.5f, 852.6f, 1042.4f, 888.2f),
                        PathNode.QuadTo(1039.3f, 923.8f, 1026.5f, 950.1f),
                        PathNode.QuadTo(1000.1f, 1001.1f, 949.1f, 1026.5f),
                        PathNode.QuadTo(923.8f, 1039.3f, 888.2f, 1042.4f),
                        PathNode.QuadTo(852.6f, 1045.5f, 770.3f, 1045.5f),
                        PathNode.LineTo(372.5f, 1045.5f),
                        PathNode.QuadTo(290.2f, 1045.5f, 254.7f, 1042.4f),
                        PathNode.QuadTo(219.1f, 1039.3f, 192.7f, 1026.5f),
                        PathNode.QuadTo(142.5f, 1000.4f, 116.4f, 950.1f),
                        PathNode.QuadTo(103.6f, 923.8f, 100.5f, 888.2f),
                        PathNode.QuadTo(97.3f, 852.6f, 97.3f, 770.3f),
                        PathNode.LineTo(97.3f, 372.5f),
                        PathNode.QuadTo(97.3f, 291.2f, 100.5f, 255.2f),
                        PathNode.QuadTo(103.6f, 219.1f, 116.4f, 192.7f),
                        PathNode.QuadTo(142.5f, 142.5f, 192.7f, 116.4f),
                        PathNode.QuadTo(219.1f, 103.6f, 254.7f, 100.5f),
                        PathNode.QuadTo(290.2f, 97.3f, 372.5f, 97.3f),
                        PathNode.LineTo(770.3f, 97.3f),
                        PathNode.QuadTo(852.6f, 97.3f, 888.2f, 100.5f),
                        PathNode.QuadTo(923.8f, 103.6f, 949.1f, 116.4f),
                        PathNode.Close,
                        PathNode.MoveTo(480.4f, 366.8f),
                        PathNode.LineTo(310.3f, 536.3f),
                        PathNode.QuadTo(301.6f, 545.0f, 301.5f, 557.5f),
                        PathNode.QuadTo(301.3f, 570.1f, 310.0f, 578.8f),
                        PathNode.LineTo(324.4f, 593.1f),
                        PathNode.QuadTo(333.8f, 602.5f, 345.8f, 602.4f),
                        PathNode.QuadTo(357.8f, 602.3f, 367.5f, 592.6f),
                        PathNode.LineTo(505.6f, 455.0f),
                        PathNode.LineTo(778.7f, 810.9f),
                        PathNode.QuadTo(785.8f, 820.6f, 798.8f, 822.2f),
                        PathNode.QuadTo(811.7f, 823.7f, 821.4f, 816.3f),
                        PathNode.LineTo(836.4f, 804.3f),
                        PathNode.QuadTo(846.3f, 797.1f, 847.8f, 784.5f),
                        PathNode.QuadTo(849.2f, 771.8f, 841.7f, 761.9f),
                        PathNode.LineTo(540.5f, 370.8f),
                        PathNode.QuadTo(528.8f, 355.6f, 511.4f, 354.3f),
                        PathNode.QuadTo(494.1f, 353.1f, 480.4f, 366.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _tasksMedium!!
    }

private var _tasksMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Tasks: ImageVector
    get() {
        if (_tasksDemibold != null) return _tasksDemibold!!
        _tasksDemibold = ImageVector.Builder(
            name = "Tasks.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(951.0f, 111.5f),
                        PathNode.QuadTo(1004.1f, 137.8f, 1031.4f, 190.8f),
                        PathNode.QuadTo(1044.8f, 218.1f, 1048.0f, 255.0f),
                        PathNode.QuadTo(1051.2f, 291.8f, 1051.2f, 373.3f),
                        PathNode.LineTo(1051.2f, 769.6f),
                        PathNode.QuadTo(1051.2f, 852.1f, 1048.0f, 888.4f),
                        PathNode.QuadTo(1044.8f, 924.7f, 1031.4f, 952.0f),
                        PathNode.QuadTo(1004.1f, 1005.1f, 951.0f, 1031.4f),
                        PathNode.QuadTo(924.7f, 1044.8f, 888.4f, 1048.0f),
                        PathNode.QuadTo(852.1f, 1051.2f, 769.6f, 1051.2f),
                        PathNode.LineTo(373.3f, 1051.2f),
                        PathNode.QuadTo(290.8f, 1051.2f, 254.5f, 1048.0f),
                        PathNode.QuadTo(218.1f, 1044.8f, 190.8f, 1031.4f),
                        PathNode.QuadTo(138.3f, 1004.5f, 111.5f, 952.0f),
                        PathNode.QuadTo(98.1f, 924.7f, 94.9f, 888.4f),
                        PathNode.QuadTo(91.6f, 852.1f, 91.6f, 769.6f),
                        PathNode.LineTo(91.6f, 373.3f),
                        PathNode.QuadTo(91.6f, 291.8f, 94.9f, 255.0f),
                        PathNode.QuadTo(98.1f, 218.1f, 111.5f, 190.8f),
                        PathNode.QuadTo(138.3f, 138.3f, 190.8f, 111.5f),
                        PathNode.QuadTo(218.1f, 98.1f, 254.5f, 94.9f),
                        PathNode.QuadTo(290.8f, 91.6f, 373.3f, 91.6f),
                        PathNode.LineTo(769.6f, 91.6f),
                        PathNode.QuadTo(852.1f, 91.6f, 888.4f, 94.9f),
                        PathNode.QuadTo(924.7f, 98.1f, 951.0f, 111.5f),
                        PathNode.Close,
                        PathNode.MoveTo(475.5f, 362.8f),
                        PathNode.LineTo(313.7f, 523.6f),
                        PathNode.QuadTo(303.1f, 534.2f, 302.9f, 549.2f),
                        PathNode.QuadTo(302.6f, 564.2f, 313.2f, 574.8f),
                        PathNode.LineTo(328.5f, 590.1f),
                        PathNode.QuadTo(339.7f, 601.2f, 354.1f, 601.0f),
                        PathNode.QuadTo(368.6f, 600.8f, 380.2f, 589.2f),
                        PathNode.LineTo(505.1f, 465.2f),
                        PathNode.LineTo(766.9f, 806.4f),
                        PathNode.QuadTo(775.6f, 818.0f, 791.3f, 819.9f),
                        PathNode.QuadTo(806.9f, 821.8f, 818.5f, 812.7f),
                        PathNode.LineTo(833.5f, 800.7f),
                        PathNode.QuadTo(845.6f, 792.0f, 847.3f, 776.8f),
                        PathNode.QuadTo(849.0f, 761.6f, 839.8f, 749.6f),
                        PathNode.LineTo(545.4f, 367.6f),
                        PathNode.QuadTo(531.8f, 350.1f, 511.4f, 348.6f),
                        PathNode.QuadTo(491.1f, 347.2f, 475.5f, 362.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _tasksDemibold!!
    }

private var _tasksDemibold: ImageVector? = null
