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

val MiuixIcons.Tasks: ImageVector
    get() = MiuixIcons.Regular.Tasks

val MiuixIcons.Light.Tasks: ImageVector
    get() {
        if (_tasksLight != null) return _tasksLight!!
        _tasksLight = ImageVector.Builder(
            name = "Tasks.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1080.0f,
            viewportHeight = 1080.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1080.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(908.0f, 106.0f),
                        PathNode.QuadTo(952.0f, 127.0f, 974.0f, 171.0f),
                        PathNode.QuadTo(985.0f, 194.0f, 987.5f, 227.0f),
                        PathNode.QuadTo(990.0f, 260.0f, 990.0f, 340.0f),
                        PathNode.VerticalTo(740.0f),
                        PathNode.QuadTo(990.0f, 821.0f, 987.5f, 853.5f),
                        PathNode.QuadTo(985.0f, 886.0f, 974.0f, 908.0f),
                        PathNode.QuadTo(953.0f, 953.0f, 908.0f, 974.0f),
                        PathNode.QuadTo(886.0f, 985.0f, 853.5f, 987.5f),
                        PathNode.QuadTo(821.0f, 990.0f, 740.0f, 990.0f),
                        PathNode.HorizontalTo(340.0f),
                        PathNode.QuadTo(260.0f, 990.0f, 227.0f, 987.5f),
                        PathNode.QuadTo(194.0f, 985.0f, 172.0f, 974.0f),
                        PathNode.QuadTo(127.0f, 953.0f, 106.0f, 908.0f),
                        PathNode.QuadTo(95.0f, 886.0f, 92.5f, 853.5f),
                        PathNode.QuadTo(90.0f, 821.0f, 90.0f, 740.0f),
                        PathNode.VerticalTo(340.0f),
                        PathNode.QuadTo(90.0f, 260.0f, 92.5f, 227.0f),
                        PathNode.QuadTo(95.0f, 194.0f, 106.0f, 171.0f),
                        PathNode.QuadTo(128.0f, 127.0f, 172.0f, 106.0f),
                        PathNode.QuadTo(194.0f, 95.0f, 227.0f, 92.5f),
                        PathNode.QuadTo(260.0f, 90.0f, 340.0f, 90.0f),
                        PathNode.HorizontalTo(740.0f),
                        PathNode.QuadTo(821.0f, 90.0f, 853.5f, 92.5f),
                        PathNode.QuadTo(886.0f, 95.0f, 908.0f, 106.0f),
                        PathNode.Close,
                        PathNode.MoveTo(461.0f, 339.0f),
                        PathNode.LineTo(270.0f, 532.0f),
                        PathNode.QuadTo(266.0f, 537.0f, 266.0f, 543.0f),
                        PathNode.QuadTo(266.0f, 549.0f, 270.0f, 553.0f),
                        PathNode.LineTo(280.0f, 564.0f),
                        PathNode.QuadTo(285.0f, 569.0f, 291.5f, 569.0f),
                        PathNode.QuadTo(298.0f, 569.0f, 302.0f, 564.0f),
                        PathNode.LineTo(475.0f, 390.0f),
                        PathNode.LineTo(782.0f, 789.0f),
                        PathNode.QuadTo(786.0f, 794.0f, 792.5f, 795.0f),
                        PathNode.QuadTo(799.0f, 796.0f, 803.0f, 792.0f),
                        PathNode.LineTo(815.0f, 782.0f),
                        PathNode.QuadTo(820.0f, 778.0f, 821.0f, 772.0f),
                        PathNode.QuadTo(822.0f, 766.0f, 818.0f, 761.0f),
                        PathNode.LineTo(495.0f, 341.0f),
                        PathNode.QuadTo(488.0f, 332.0f, 478.5f, 331.5f),
                        PathNode.QuadTo(469.0f, 331.0f, 461.0f, 339.0f),
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

val MiuixIcons.Normal.Tasks: ImageVector
    get() {
        if (_tasksNormal != null) return _tasksNormal!!
        _tasksNormal = ImageVector.Builder(
            name = "Tasks.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1106.4f,
            viewportHeight = 1106.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1106.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(926.0f, 109.6f),
                        PathNode.QuadTo(972.8f, 132.6f, 996.8f, 179.4f),
                        PathNode.QuadTo(1008.5f, 203.8f, 1011.4f, 238.1f),
                        PathNode.QuadTo(1014.2f, 272.5f, 1014.2f, 353.2f),
                        PathNode.VerticalTo(753.2f),
                        PathNode.QuadTo(1014.2f, 834.9f, 1011.4f, 868.8f),
                        PathNode.QuadTo(1008.5f, 902.6f, 996.8f, 926.7f),
                        PathNode.QuadTo(973.1f, 973.8f, 926.0f, 996.8f),
                        PathNode.QuadTo(902.6f, 1008.5f, 868.8f, 1011.4f),
                        PathNode.QuadTo(834.9f, 1014.2f, 753.2f, 1014.2f),
                        PathNode.HorizontalTo(353.2f),
                        PathNode.QuadTo(271.8f, 1014.2f, 237.8f, 1011.4f),
                        PathNode.QuadTo(203.8f, 1008.5f, 179.7f, 996.8f),
                        PathNode.QuadTo(133.3f, 973.1f, 109.6f, 926.7f),
                        PathNode.QuadTo(97.9f, 902.6f, 95.0f, 868.8f),
                        PathNode.QuadTo(92.2f, 834.9f, 92.2f, 753.2f),
                        PathNode.VerticalTo(353.2f),
                        PathNode.QuadTo(92.2f, 272.5f, 95.0f, 238.1f),
                        PathNode.QuadTo(97.9f, 203.8f, 109.6f, 179.4f),
                        PathNode.QuadTo(133.6f, 133.3f, 179.7f, 109.6f),
                        PathNode.QuadTo(203.8f, 97.9f, 237.8f, 95.0f),
                        PathNode.QuadTo(271.8f, 92.2f, 353.2f, 92.2f),
                        PathNode.HorizontalTo(753.2f),
                        PathNode.QuadTo(834.9f, 92.2f, 868.8f, 95.0f),
                        PathNode.QuadTo(902.6f, 97.9f, 926.0f, 109.6f),
                        PathNode.Close,
                        PathNode.MoveTo(470.8f, 353.6f),
                        PathNode.LineTo(285.9f, 539.0f),
                        PathNode.QuadTo(280.6f, 544.7f, 280.6f, 552.8f),
                        PathNode.QuadTo(280.6f, 560.8f, 285.9f, 566.2f),
                        PathNode.LineTo(298.0f, 578.6f),
                        PathNode.QuadTo(304.4f, 584.9f, 312.3f, 584.9f),
                        PathNode.QuadTo(320.1f, 584.9f, 326.2f, 578.6f),
                        PathNode.LineTo(488.2f, 416.3f),
                        PathNode.LineTo(782.8f, 800.1f),
                        PathNode.QuadTo(787.5f, 806.5f, 795.7f, 807.5f),
                        PathNode.QuadTo(803.9f, 808.5f, 810.0f, 803.8f),
                        PathNode.LineTo(824.1f, 792.4f),
                        PathNode.QuadTo(830.4f, 787.8f, 831.4f, 779.7f),
                        PathNode.QuadTo(832.4f, 771.6f, 827.8f, 765.3f),
                        PathNode.LineTo(513.0f, 356.3f),
                        PathNode.QuadTo(504.6f, 345.2f, 492.7f, 344.4f),
                        PathNode.QuadTo(480.8f, 343.5f, 470.8f, 353.6f),
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

val MiuixIcons.Regular.Tasks: ImageVector
    get() {
        if (_tasksRegular != null) return _tasksRegular!!
        _tasksRegular = ImageVector.Builder(
            name = "Tasks.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1118.4f,
            viewportHeight = 1118.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1118.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(934.2f, 111.2f),
                        PathNode.QuadTo(982.2f, 135.2f, 1007.2f, 183.2f),
                        PathNode.QuadTo(1019.2f, 208.2f, 1022.2f, 243.2f),
                        PathNode.QuadTo(1025.2f, 278.2f, 1025.2f, 359.2f),
                        PathNode.VerticalTo(759.2f),
                        PathNode.QuadTo(1025.2f, 841.2f, 1022.2f, 875.7f),
                        PathNode.QuadTo(1019.2f, 910.2f, 1007.2f, 935.2f),
                        PathNode.QuadTo(982.2f, 983.2f, 934.2f, 1007.2f),
                        PathNode.QuadTo(910.2f, 1019.2f, 875.7f, 1022.2f),
                        PathNode.QuadTo(841.2f, 1025.2f, 759.2f, 1025.2f),
                        PathNode.HorizontalTo(359.2f),
                        PathNode.QuadTo(277.2f, 1025.2f, 242.7f, 1022.2f),
                        PathNode.QuadTo(208.2f, 1019.2f, 183.2f, 1007.2f),
                        PathNode.QuadTo(136.2f, 982.2f, 111.2f, 935.2f),
                        PathNode.QuadTo(99.2f, 910.2f, 96.2f, 875.7f),
                        PathNode.QuadTo(93.2f, 841.2f, 93.2f, 759.2f),
                        PathNode.VerticalTo(359.2f),
                        PathNode.QuadTo(93.2f, 278.2f, 96.2f, 243.2f),
                        PathNode.QuadTo(99.2f, 208.2f, 111.2f, 183.2f),
                        PathNode.QuadTo(136.2f, 136.2f, 183.2f, 111.2f),
                        PathNode.QuadTo(208.2f, 99.2f, 242.7f, 96.2f),
                        PathNode.QuadTo(277.2f, 93.2f, 359.2f, 93.2f),
                        PathNode.HorizontalTo(759.2f),
                        PathNode.QuadTo(841.2f, 93.2f, 875.7f, 96.2f),
                        PathNode.QuadTo(910.2f, 99.2f, 934.2f, 111.2f),
                        PathNode.Close,
                        PathNode.MoveTo(475.2f, 360.2f),
                        PathNode.LineTo(293.2f, 542.2f),
                        PathNode.QuadTo(287.2f, 548.2f, 287.2f, 557.2f),
                        PathNode.QuadTo(287.2f, 566.2f, 293.2f, 572.2f),
                        PathNode.LineTo(306.2f, 585.2f),
                        PathNode.QuadTo(313.2f, 592.2f, 321.7f, 592.2f),
                        PathNode.QuadTo(330.2f, 592.2f, 337.2f, 585.2f),
                        PathNode.LineTo(494.2f, 428.2f),
                        PathNode.LineTo(783.2f, 805.2f),
                        PathNode.QuadTo(788.2f, 812.2f, 797.2f, 813.2f),
                        PathNode.QuadTo(806.2f, 814.2f, 813.2f, 809.2f),
                        PathNode.LineTo(828.2f, 797.2f),
                        PathNode.QuadTo(835.2f, 792.2f, 836.2f, 783.2f),
                        PathNode.QuadTo(837.2f, 774.2f, 832.2f, 767.2f),
                        PathNode.LineTo(521.2f, 363.2f),
                        PathNode.QuadTo(512.2f, 351.2f, 499.2f, 350.2f),
                        PathNode.QuadTo(486.2f, 349.2f, 475.2f, 360.2f),
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

val MiuixIcons.Medium.Tasks: ImageVector
    get() {
        if (_tasksMedium != null) return _tasksMedium!!
        _tasksMedium = ImageVector.Builder(
            name = "Tasks.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1138.2f,
            viewportHeight = 1138.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1138.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(947.0f, 114.0f),
                        PathNode.QuadTo(998.0f, 139.2f, 1024.1f, 190.1f),
                        PathNode.QuadTo(1037.3f, 216.3f, 1040.3f, 252.5f),
                        PathNode.QuadTo(1043.3f, 288.7f, 1043.3f, 370.3f),
                        PathNode.VerticalTo(767.9f),
                        PathNode.QuadTo(1043.3f, 849.9f, 1040.3f, 885.9f),
                        PathNode.QuadTo(1037.3f, 921.8f, 1024.1f, 948.0f),
                        PathNode.QuadTo(998.0f, 999.0f, 947.0f, 1024.1f),
                        PathNode.QuadTo(921.3f, 1037.3f, 885.6f, 1040.3f),
                        PathNode.QuadTo(849.9f, 1043.3f, 767.9f, 1043.3f),
                        PathNode.HorizontalTo(370.3f),
                        PathNode.QuadTo(287.7f, 1043.3f, 252.0f, 1040.3f),
                        PathNode.QuadTo(216.3f, 1037.3f, 190.1f, 1024.1f),
                        PathNode.QuadTo(139.6f, 998.6f, 114.0f, 948.0f),
                        PathNode.QuadTo(100.8f, 921.8f, 97.8f, 885.9f),
                        PathNode.QuadTo(94.8f, 849.9f, 94.8f, 767.9f),
                        PathNode.VerticalTo(370.3f),
                        PathNode.QuadTo(94.8f, 288.7f, 97.8f, 252.5f),
                        PathNode.QuadTo(100.8f, 216.3f, 114.0f, 190.1f),
                        PathNode.QuadTo(139.6f, 139.6f, 190.1f, 114.0f),
                        PathNode.QuadTo(216.3f, 100.8f, 252.0f, 97.8f),
                        PathNode.QuadTo(287.7f, 94.8f, 370.3f, 94.8f),
                        PathNode.HorizontalTo(767.9f),
                        PathNode.QuadTo(849.9f, 94.8f, 885.6f, 97.8f),
                        PathNode.QuadTo(921.3f, 100.8f, 947.0f, 114.0f),
                        PathNode.Close,
                        PathNode.MoveTo(478.0f, 364.2f),
                        PathNode.LineTo(307.8f, 533.8f),
                        PathNode.QuadTo(299.4f, 542.8f, 299.4f, 555.3f),
                        PathNode.QuadTo(299.4f, 567.8f, 307.8f, 576.2f),
                        PathNode.LineTo(322.0f, 591.0f),
                        PathNode.QuadTo(331.3f, 600.3f, 343.6f, 600.0f),
                        PathNode.QuadTo(356.0f, 599.7f, 365.3f, 590.4f),
                        PathNode.LineTo(503.5f, 452.8f),
                        PathNode.LineTo(776.6f, 808.6f),
                        PathNode.QuadTo(783.4f, 818.0f, 796.5f, 819.6f),
                        PathNode.QuadTo(809.6f, 821.1f, 819.0f, 813.8f),
                        PathNode.LineTo(834.0f, 801.8f),
                        PathNode.QuadTo(844.5f, 794.4f, 845.8f, 781.9f),
                        PathNode.QuadTo(847.1f, 769.4f, 839.1f, 759.4f),
                        PathNode.LineTo(538.1f, 368.4f),
                        PathNode.QuadTo(526.8f, 353.4f, 509.1f, 352.1f),
                        PathNode.QuadTo(491.4f, 350.8f, 478.0f, 364.2f),
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

val MiuixIcons.Demibold.Tasks: ImageVector
    get() {
        if (_tasksDemibold != null) return _tasksDemibold!!
        _tasksDemibold = ImageVector.Builder(
            name = "Tasks.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1152.0f,
            viewportHeight = 1152.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1152.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(956.0f, 116.0f),
                        PathNode.QuadTo(1009.0f, 142.0f, 1036.0f, 195.0f),
                        PathNode.QuadTo(1050.0f, 222.0f, 1053.0f, 259.0f),
                        PathNode.QuadTo(1056.0f, 296.0f, 1056.0f, 378.0f),
                        PathNode.VerticalTo(774.0f),
                        PathNode.QuadTo(1056.0f, 856.0f, 1053.0f, 893.0f),
                        PathNode.QuadTo(1050.0f, 930.0f, 1036.0f, 957.0f),
                        PathNode.QuadTo(1009.0f, 1010.0f, 956.0f, 1036.0f),
                        PathNode.QuadTo(929.0f, 1050.0f, 892.5f, 1053.0f),
                        PathNode.QuadTo(856.0f, 1056.0f, 774.0f, 1056.0f),
                        PathNode.HorizontalTo(378.0f),
                        PathNode.QuadTo(295.0f, 1056.0f, 258.5f, 1053.0f),
                        PathNode.QuadTo(222.0f, 1050.0f, 195.0f, 1036.0f),
                        PathNode.QuadTo(142.0f, 1010.0f, 116.0f, 957.0f),
                        PathNode.QuadTo(102.0f, 930.0f, 99.0f, 893.0f),
                        PathNode.QuadTo(96.0f, 856.0f, 96.0f, 774.0f),
                        PathNode.VerticalTo(378.0f),
                        PathNode.QuadTo(96.0f, 296.0f, 99.0f, 259.0f),
                        PathNode.QuadTo(102.0f, 222.0f, 116.0f, 195.0f),
                        PathNode.QuadTo(142.0f, 142.0f, 195.0f, 116.0f),
                        PathNode.QuadTo(222.0f, 102.0f, 258.5f, 99.0f),
                        PathNode.QuadTo(295.0f, 96.0f, 378.0f, 96.0f),
                        PathNode.HorizontalTo(774.0f),
                        PathNode.QuadTo(856.0f, 96.0f, 892.5f, 99.0f),
                        PathNode.QuadTo(929.0f, 102.0f, 956.0f, 116.0f),
                        PathNode.Close,
                        PathNode.MoveTo(480.0f, 367.0f),
                        PathNode.LineTo(318.0f, 528.0f),
                        PathNode.QuadTo(308.0f, 539.0f, 308.0f, 554.0f),
                        PathNode.QuadTo(308.0f, 569.0f, 318.0f, 579.0f),
                        PathNode.LineTo(333.0f, 595.0f),
                        PathNode.QuadTo(344.0f, 606.0f, 359.0f, 605.5f),
                        PathNode.QuadTo(374.0f, 605.0f, 385.0f, 594.0f),
                        PathNode.LineTo(510.0f, 470.0f),
                        PathNode.LineTo(772.0f, 811.0f),
                        PathNode.QuadTo(780.0f, 822.0f, 796.0f, 824.0f),
                        PathNode.QuadTo(812.0f, 826.0f, 823.0f, 817.0f),
                        PathNode.LineTo(838.0f, 805.0f),
                        PathNode.QuadTo(851.0f, 796.0f, 852.5f, 781.0f),
                        PathNode.QuadTo(854.0f, 766.0f, 844.0f, 754.0f),
                        PathNode.LineTo(550.0f, 372.0f),
                        PathNode.QuadTo(537.0f, 355.0f, 516.0f, 353.5f),
                        PathNode.QuadTo(495.0f, 352.0f, 480.0f, 367.0f),
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
