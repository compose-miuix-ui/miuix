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
                        PathNode.MoveTo(869.6f, 150.0f),
                        PathNode.QuadTo(909.7f, 169.4f, 930.0f, 209.6f),
                        PathNode.QuadTo(940.0f, 230.5f, 942.4f, 260.4f),
                        PathNode.QuadTo(944.7f, 290.3f, 944.7f, 361.8f),
                        PathNode.VerticalTo(718.2f),
                        PathNode.QuadTo(944.7f, 790.6f, 942.4f, 820.1f),
                        PathNode.QuadTo(940.0f, 849.5f, 930.0f, 869.8f),
                        PathNode.QuadTo(910.4f, 910.6f, 869.6f, 930.0f),
                        PathNode.QuadTo(849.5f, 940.0f, 820.1f, 942.4f),
                        PathNode.QuadTo(790.6f, 944.7f, 718.2f, 944.7f),
                        PathNode.HorizontalTo(361.8f),
                        PathNode.QuadTo(290.0f, 944.7f, 260.3f, 942.4f),
                        PathNode.QuadTo(230.5f, 940.0f, 210.2f, 930.0f),
                        PathNode.QuadTo(169.6f, 910.4f, 150.0f, 869.8f),
                        PathNode.QuadTo(140.0f, 849.5f, 137.6f, 820.1f),
                        PathNode.QuadTo(135.3f, 790.6f, 135.3f, 718.2f),
                        PathNode.VerticalTo(361.8f),
                        PathNode.QuadTo(135.3f, 290.3f, 137.6f, 260.4f),
                        PathNode.QuadTo(140.0f, 230.5f, 150.0f, 209.6f),
                        PathNode.QuadTo(170.3f, 169.6f, 210.2f, 150.0f),
                        PathNode.QuadTo(230.5f, 140.0f, 260.3f, 137.6f),
                        PathNode.QuadTo(290.0f, 135.3f, 361.8f, 135.3f),
                        PathNode.HorizontalTo(718.2f),
                        PathNode.QuadTo(790.6f, 135.3f, 820.1f, 137.6f),
                        PathNode.QuadTo(849.5f, 140.0f, 869.6f, 150.0f),
                        PathNode.Close,
                        PathNode.MoveTo(468.4f, 361.4f),
                        PathNode.LineTo(300.3f, 530.8f),
                        PathNode.QuadTo(296.3f, 535.5f, 296.3f, 541.5f),
                        PathNode.QuadTo(296.3f, 547.5f, 300.3f, 551.6f),
                        PathNode.LineTo(309.9f, 561.9f),
                        PathNode.QuadTo(314.9f, 566.8f, 321.1f, 566.8f),
                        PathNode.QuadTo(327.4f, 566.8f, 331.7f, 561.9f),
                        PathNode.LineTo(482.1f, 410.8f),
                        PathNode.LineTo(751.4f, 761.2f),
                        PathNode.QuadTo(755.2f, 766.1f, 761.6f, 767.0f),
                        PathNode.QuadTo(768.0f, 767.9f, 772.2f, 764.1f),
                        PathNode.LineTo(783.7f, 754.7f),
                        PathNode.QuadTo(788.6f, 750.9f, 789.4f, 744.9f),
                        PathNode.QuadTo(790.3f, 738.8f, 786.6f, 733.9f),
                        PathNode.LineTo(501.5f, 363.4f),
                        PathNode.QuadTo(494.8f, 354.7f, 485.5f, 354.1f),
                        PathNode.QuadTo(476.3f, 353.5f, 468.4f, 361.4f),
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
                        PathNode.MoveTo(886.6f, 154.6f),
                        PathNode.QuadTo(929.5f, 176.0f, 951.8f, 218.9f),
                        PathNode.QuadTo(962.5f, 241.2f, 965.2f, 272.4f),
                        PathNode.QuadTo(967.8f, 303.5f, 967.8f, 375.6f),
                        PathNode.VerticalTo(730.9f),
                        PathNode.QuadTo(967.8f, 803.7f, 965.2f, 834.5f),
                        PathNode.QuadTo(962.5f, 865.2f, 951.8f, 887.5f),
                        PathNode.QuadTo(929.5f, 930.4f, 886.6f, 951.8f),
                        PathNode.QuadTo(865.2f, 962.5f, 834.5f, 965.2f),
                        PathNode.QuadTo(803.7f, 967.8f, 730.9f, 967.8f),
                        PathNode.HorizontalTo(375.6f),
                        PathNode.QuadTo(302.6f, 967.8f, 271.9f, 965.2f),
                        PathNode.QuadTo(241.2f, 962.5f, 218.9f, 951.8f),
                        PathNode.QuadTo(176.9f, 929.5f, 154.6f, 887.5f),
                        PathNode.QuadTo(143.9f, 865.2f, 141.2f, 834.5f),
                        PathNode.QuadTo(138.6f, 803.7f, 138.6f, 730.9f),
                        PathNode.VerticalTo(375.6f),
                        PathNode.QuadTo(138.6f, 303.5f, 141.2f, 272.4f),
                        PathNode.QuadTo(143.9f, 241.2f, 154.6f, 218.9f),
                        PathNode.QuadTo(176.9f, 176.9f, 218.9f, 154.6f),
                        PathNode.QuadTo(241.2f, 143.9f, 271.9f, 141.2f),
                        PathNode.QuadTo(302.6f, 138.6f, 375.6f, 138.6f),
                        PathNode.HorizontalTo(730.9f),
                        PathNode.QuadTo(803.7f, 138.6f, 834.5f, 141.2f),
                        PathNode.QuadTo(865.2f, 143.9f, 886.6f, 154.6f),
                        PathNode.Close,
                        PathNode.MoveTo(478.1f, 376.0f),
                        PathNode.LineTo(317.1f, 536.9f),
                        PathNode.QuadTo(311.6f, 542.4f, 311.6f, 550.7f),
                        PathNode.QuadTo(311.6f, 558.9f, 317.1f, 564.4f),
                        PathNode.LineTo(328.8f, 576.0f),
                        PathNode.QuadTo(335.1f, 582.4f, 342.9f, 582.4f),
                        PathNode.QuadTo(350.7f, 582.4f, 357.1f, 576.0f),
                        PathNode.LineTo(495.4f, 437.7f),
                        PathNode.LineTo(751.2f, 771.4f),
                        PathNode.QuadTo(755.8f, 777.8f, 764.0f, 778.7f),
                        PathNode.QuadTo(772.3f, 779.6f, 778.7f, 775.0f),
                        PathNode.LineTo(792.0f, 764.4f),
                        PathNode.QuadTo(798.4f, 759.8f, 799.3f, 751.5f),
                        PathNode.QuadTo(800.2f, 743.3f, 795.6f, 736.9f),
                        PathNode.LineTo(519.9f, 378.7f),
                        PathNode.QuadTo(511.7f, 367.9f, 499.9f, 367.0f),
                        PathNode.QuadTo(488.0f, 366.1f, 478.1f, 376.0f),
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
                        PathNode.MoveTo(894.2f, 156.8f),
                        PathNode.QuadTo(938.7f, 178.9f, 961.6f, 223.3f),
                        PathNode.QuadTo(973.0f, 246.3f, 975.7f, 278.1f),
                        PathNode.QuadTo(978.3f, 309.9f, 978.3f, 382.3f),
                        PathNode.VerticalTo(736.2f),
                        PathNode.QuadTo(978.3f, 809.0f, 975.7f, 840.6f),
                        PathNode.QuadTo(973.0f, 872.1f, 961.6f, 895.1f),
                        PathNode.QuadTo(938.7f, 939.6f, 894.2f, 961.6f),
                        PathNode.QuadTo(871.8f, 973.0f, 840.4f, 975.7f),
                        PathNode.QuadTo(809.0f, 978.3f, 736.2f, 978.3f),
                        PathNode.HorizontalTo(382.3f),
                        PathNode.QuadTo(309.0f, 978.3f, 277.7f, 975.7f),
                        PathNode.QuadTo(246.3f, 973.0f, 223.3f, 961.6f),
                        PathNode.QuadTo(179.4f, 939.1f, 156.8f, 895.1f),
                        PathNode.QuadTo(145.4f, 872.1f, 142.7f, 840.6f),
                        PathNode.QuadTo(140.1f, 809.0f, 140.1f, 736.2f),
                        PathNode.VerticalTo(382.3f),
                        PathNode.QuadTo(140.1f, 309.9f, 142.7f, 278.1f),
                        PathNode.QuadTo(145.4f, 246.3f, 156.8f, 223.3f),
                        PathNode.QuadTo(179.4f, 179.4f, 223.3f, 156.8f),
                        PathNode.QuadTo(246.3f, 145.4f, 277.7f, 142.7f),
                        PathNode.QuadTo(309.0f, 140.1f, 382.3f, 140.1f),
                        PathNode.HorizontalTo(736.2f),
                        PathNode.QuadTo(809.0f, 140.1f, 840.4f, 142.7f),
                        PathNode.QuadTo(871.8f, 145.4f, 894.2f, 156.8f),
                        PathNode.Close,
                        PathNode.MoveTo(480.2f, 378.8f),
                        PathNode.LineTo(325.8f, 532.8f),
                        PathNode.QuadTo(319.0f, 540.0f, 319.0f, 550.2f),
                        PathNode.QuadTo(319.0f, 560.3f, 325.8f, 567.1f),
                        PathNode.LineTo(338.1f, 579.8f),
                        PathNode.QuadTo(345.7f, 587.4f, 355.6f, 587.3f),
                        PathNode.QuadTo(365.6f, 587.1f, 373.2f, 579.4f),
                        PathNode.LineTo(501.1f, 451.9f),
                        PathNode.LineTo(748.1f, 773.8f),
                        PathNode.QuadTo(753.6f, 781.5f, 764.2f, 782.7f),
                        PathNode.QuadTo(774.7f, 783.9f, 782.4f, 778.1f),
                        PathNode.LineTo(795.7f, 767.4f),
                        PathNode.QuadTo(804.1f, 761.5f, 805.2f, 751.3f),
                        PathNode.QuadTo(806.2f, 741.2f, 799.9f, 733.1f),
                        PathNode.LineTo(529.8f, 382.2f),
                        PathNode.QuadTo(520.4f, 369.7f, 505.9f, 368.6f),
                        PathNode.QuadTo(491.5f, 367.5f, 480.2f, 378.8f),
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
                        PathNode.MoveTo(906.3f, 160.4f),
                        PathNode.QuadTo(953.6f, 183.6f, 977.7f, 230.9f),
                        PathNode.QuadTo(990.3f, 255.0f, 992.9f, 287.9f),
                        PathNode.QuadTo(995.6f, 320.9f, 995.6f, 393.6f),
                        PathNode.VerticalTo(744.4f),
                        PathNode.QuadTo(995.6f, 817.1f, 992.9f, 850.1f),
                        PathNode.QuadTo(990.3f, 883.1f, 977.7f, 907.2f),
                        PathNode.QuadTo(953.6f, 954.5f, 906.3f, 977.7f),
                        PathNode.QuadTo(882.1f, 990.3f, 849.6f, 992.9f),
                        PathNode.QuadTo(817.1f, 995.6f, 744.4f, 995.6f),
                        PathNode.HorizontalTo(393.6f),
                        PathNode.QuadTo(320.0f, 995.6f, 287.5f, 992.9f),
                        PathNode.QuadTo(255.0f, 990.3f, 230.9f, 977.7f),
                        PathNode.QuadTo(183.5f, 954.6f, 160.4f, 907.2f),
                        PathNode.QuadTo(147.8f, 883.1f, 145.2f, 850.1f),
                        PathNode.QuadTo(142.5f, 817.1f, 142.5f, 744.4f),
                        PathNode.VerticalTo(393.6f),
                        PathNode.QuadTo(142.5f, 320.9f, 145.2f, 287.9f),
                        PathNode.QuadTo(147.8f, 255.0f, 160.4f, 230.9f),
                        PathNode.QuadTo(183.5f, 183.5f, 230.9f, 160.4f),
                        PathNode.QuadTo(255.0f, 147.8f, 287.5f, 145.2f),
                        PathNode.QuadTo(320.0f, 142.5f, 393.6f, 142.5f),
                        PathNode.HorizontalTo(744.4f),
                        PathNode.QuadTo(817.1f, 142.5f, 849.6f, 145.2f),
                        PathNode.QuadTo(882.1f, 147.8f, 906.3f, 160.4f),
                        PathNode.Close,
                        PathNode.MoveTo(483.1f, 383.0f),
                        PathNode.LineTo(340.9f, 524.3f),
                        PathNode.QuadTo(331.7f, 534.4f, 331.7f, 548.1f),
                        PathNode.QuadTo(331.7f, 561.8f, 340.9f, 571.0f),
                        PathNode.LineTo(354.3f, 585.4f),
                        PathNode.QuadTo(364.4f, 595.4f, 378.1f, 595.0f),
                        PathNode.QuadTo(391.9f, 594.5f, 401.9f, 584.4f),
                        PathNode.LineTo(510.5f, 476.9f),
                        PathNode.LineTo(740.8f, 776.6f),
                        PathNode.QuadTo(748.1f, 786.6f, 762.8f, 788.5f),
                        PathNode.QuadTo(777.5f, 790.3f, 787.5f, 782.1f),
                        PathNode.LineTo(800.8f, 771.4f),
                        PathNode.QuadTo(812.8f, 763.2f, 814.1f, 749.4f),
                        PathNode.QuadTo(815.5f, 735.7f, 806.3f, 724.7f),
                        PathNode.LineTo(546.9f, 387.6f),
                        PathNode.QuadTo(535.0f, 372.2f, 515.8f, 370.8f),
                        PathNode.QuadTo(496.7f, 369.5f, 483.1f, 383.0f),
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
                        PathNode.MoveTo(915.0f, 162.9f),
                        PathNode.QuadTo(964.2f, 186.8f, 989.1f, 236.1f),
                        PathNode.QuadTo(1002.4f, 260.9f, 1005.1f, 294.5f),
                        PathNode.QuadTo(1007.7f, 328.2f, 1007.7f, 401.3f),
                        PathNode.VerticalTo(750.6f),
                        PathNode.QuadTo(1007.7f, 823.2f, 1005.1f, 857.1f),
                        PathNode.QuadTo(1002.4f, 891.1f, 989.1f, 915.9f),
                        PathNode.QuadTo(964.2f, 965.1f, 915.0f, 989.1f),
                        PathNode.QuadTo(889.7f, 1002.4f, 856.5f, 1005.1f),
                        PathNode.QuadTo(823.2f, 1007.7f, 750.6f, 1007.7f),
                        PathNode.HorizontalTo(401.3f),
                        PathNode.QuadTo(327.3f, 1007.7f, 294.1f, 1005.1f),
                        PathNode.QuadTo(260.9f, 1002.4f, 236.1f, 989.1f),
                        PathNode.QuadTo(186.4f, 965.5f, 162.9f, 915.9f),
                        PathNode.QuadTo(149.6f, 891.1f, 146.9f, 857.1f),
                        PathNode.QuadTo(144.3f, 823.2f, 144.3f, 750.6f),
                        PathNode.VerticalTo(401.3f),
                        PathNode.QuadTo(144.3f, 328.2f, 146.9f, 294.5f),
                        PathNode.QuadTo(149.6f, 260.9f, 162.9f, 236.1f),
                        PathNode.QuadTo(186.4f, 186.4f, 236.1f, 162.9f),
                        PathNode.QuadTo(260.9f, 149.6f, 294.1f, 146.9f),
                        PathNode.QuadTo(327.3f, 144.3f, 401.3f, 144.3f),
                        PathNode.HorizontalTo(750.6f),
                        PathNode.QuadTo(823.2f, 144.3f, 856.5f, 146.9f),
                        PathNode.QuadTo(889.7f, 149.6f, 915.0f, 162.9f),
                        PathNode.Close,
                        PathNode.MoveTo(485.5f, 386.2f),
                        PathNode.LineTo(350.9f, 519.5f),
                        PathNode.QuadTo(340.3f, 531.5f, 340.3f, 547.5f),
                        PathNode.QuadTo(340.3f, 563.5f, 350.9f, 574.1f),
                        PathNode.LineTo(365.0f, 589.6f),
                        PathNode.QuadTo(376.7f, 601.2f, 392.9f, 600.6f),
                        PathNode.QuadTo(409.0f, 599.9f, 420.6f, 588.3f),
                        PathNode.LineTo(517.0f, 493.2f),
                        PathNode.LineTo(737.1f, 779.3f),
                        PathNode.QuadTo(745.5f, 790.8f, 762.9f, 793.0f),
                        PathNode.QuadTo(780.2f, 795.3f, 791.7f, 785.5f),
                        PathNode.LineTo(805.0f, 774.9f),
                        PathNode.QuadTo(819.2f, 765.1f, 820.8f, 749.1f),
                        PathNode.QuadTo(822.3f, 733.1f, 811.3f, 720.3f),
                        PathNode.LineTo(558.4f, 391.5f),
                        PathNode.QuadTo(545.0f, 374.3f, 522.8f, 372.7f),
                        PathNode.QuadTo(500.5f, 371.2f, 485.5f, 386.2f),
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
