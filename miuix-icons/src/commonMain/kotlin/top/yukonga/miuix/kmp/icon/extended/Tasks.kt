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
            viewportHeight = 1080.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -125.0f, translationY = 915.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1033.0f, -59.0f),
                PathNode.QuadTo(1077.0f, -38.0f, 1099.0f, 6.0f),
                PathNode.QuadTo(1110.0f, 29.0f, 1112.5f, 62.0f),
                PathNode.QuadTo(1115.0f, 95.0f, 1115.0f, 175.0f),
                PathNode.VerticalTo(575.0f),
                PathNode.QuadTo(1115.0f, 656.0f, 1112.5f, 688.5f),
                PathNode.QuadTo(1110.0f, 721.0f, 1099.0f, 743.0f),
                PathNode.QuadTo(1078.0f, 788.0f, 1033.0f, 809.0f),
                PathNode.QuadTo(1011.0f, 820.0f, 978.5f, 822.5f),
                PathNode.QuadTo(946.0f, 825.0f, 865.0f, 825.0f),
                PathNode.HorizontalTo(465.0f),
                PathNode.QuadTo(385.0f, 825.0f, 352.0f, 822.5f),
                PathNode.QuadTo(319.0f, 820.0f, 297.0f, 809.0f),
                PathNode.QuadTo(252.0f, 788.0f, 231.0f, 743.0f),
                PathNode.QuadTo(220.0f, 721.0f, 217.5f, 688.5f),
                PathNode.QuadTo(215.0f, 656.0f, 215.0f, 575.0f),
                PathNode.VerticalTo(175.0f),
                PathNode.QuadTo(215.0f, 95.0f, 217.5f, 62.0f),
                PathNode.QuadTo(220.0f, 29.0f, 231.0f, 6.0f),
                PathNode.QuadTo(253.0f, -38.0f, 297.0f, -59.0f),
                PathNode.QuadTo(319.0f, -70.0f, 352.0f, -72.5f),
                PathNode.QuadTo(385.0f, -75.0f, 465.0f, -75.0f),
                PathNode.HorizontalTo(865.0f),
                PathNode.QuadTo(946.0f, -75.0f, 978.5f, -72.5f),
                PathNode.QuadTo(1011.0f, -70.0f, 1033.0f, -59.0f),
                PathNode.Close,
                PathNode.MoveTo(586.0f, 174.0f),
                PathNode.LineTo(395.0f, 367.0f),
                PathNode.QuadTo(391.0f, 372.0f, 391.0f, 378.0f),
                PathNode.QuadTo(391.0f, 384.0f, 395.0f, 388.0f),
                PathNode.LineTo(405.0f, 399.0f),
                PathNode.QuadTo(410.0f, 404.0f, 416.5f, 404.0f),
                PathNode.QuadTo(423.0f, 404.0f, 427.0f, 399.0f),
                PathNode.LineTo(600.0f, 225.0f),
                PathNode.LineTo(907.0f, 624.0f),
                PathNode.QuadTo(911.0f, 629.0f, 917.5f, 630.0f),
                PathNode.QuadTo(924.0f, 631.0f, 928.0f, 627.0f),
                PathNode.LineTo(940.0f, 617.0f),
                PathNode.QuadTo(945.0f, 613.0f, 946.0f, 607.0f),
                PathNode.QuadTo(947.0f, 601.0f, 943.0f, 596.0f),
                PathNode.LineTo(620.0f, 176.0f),
                PathNode.QuadTo(613.0f, 167.0f, 603.5f, 166.5f),
                PathNode.QuadTo(594.0f, 166.0f, 586.0f, 174.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _tasksLight!!
    }

private var _tasksLight: ImageVector? = null

val MiuixIcons.Regular.Tasks: ImageVector
    get() {
        if (_tasksRegular != null) return _tasksRegular!!
        _tasksRegular = ImageVector.Builder(
            name = "Tasks.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1118.3999999999999f,
            viewportHeight = 1118.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -105.80000000000007f, translationY = 934.1999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1040.0f, -73.0f),
                PathNode.QuadTo(1088.0f, -49.0f, 1113.0f, -1.0f),
                PathNode.QuadTo(1125.0f, 24.0f, 1128.0f, 59.0f),
                PathNode.QuadTo(1131.0f, 94.0f, 1131.0f, 175.0f),
                PathNode.VerticalTo(575.0f),
                PathNode.QuadTo(1131.0f, 657.0f, 1128.0f, 691.5f),
                PathNode.QuadTo(1125.0f, 726.0f, 1113.0f, 751.0f),
                PathNode.QuadTo(1088.0f, 799.0f, 1040.0f, 823.0f),
                PathNode.QuadTo(1016.0f, 835.0f, 981.5f, 838.0f),
                PathNode.QuadTo(947.0f, 841.0f, 865.0f, 841.0f),
                PathNode.HorizontalTo(465.0f),
                PathNode.QuadTo(383.0f, 841.0f, 348.5f, 838.0f),
                PathNode.QuadTo(314.0f, 835.0f, 289.0f, 823.0f),
                PathNode.QuadTo(242.0f, 798.0f, 217.0f, 751.0f),
                PathNode.QuadTo(205.0f, 726.0f, 202.0f, 691.5f),
                PathNode.QuadTo(199.0f, 657.0f, 199.0f, 575.0f),
                PathNode.VerticalTo(175.0f),
                PathNode.QuadTo(199.0f, 94.0f, 202.0f, 59.0f),
                PathNode.QuadTo(205.0f, 24.0f, 217.0f, -1.0f),
                PathNode.QuadTo(242.0f, -48.0f, 289.0f, -73.0f),
                PathNode.QuadTo(314.0f, -85.0f, 348.5f, -88.0f),
                PathNode.QuadTo(383.0f, -91.0f, 465.0f, -91.0f),
                PathNode.HorizontalTo(865.0f),
                PathNode.QuadTo(947.0f, -91.0f, 981.5f, -88.0f),
                PathNode.QuadTo(1016.0f, -85.0f, 1040.0f, -73.0f),
                PathNode.Close,
                PathNode.MoveTo(581.0f, 176.0f),
                PathNode.LineTo(399.0f, 358.0f),
                PathNode.QuadTo(393.0f, 364.0f, 393.0f, 373.0f),
                PathNode.QuadTo(393.0f, 382.0f, 399.0f, 388.0f),
                PathNode.LineTo(412.0f, 401.0f),
                PathNode.QuadTo(419.0f, 408.0f, 427.5f, 408.0f),
                PathNode.QuadTo(436.0f, 408.0f, 443.0f, 401.0f),
                PathNode.LineTo(600.0f, 244.0f),
                PathNode.LineTo(889.0f, 621.0f),
                PathNode.QuadTo(894.0f, 628.0f, 903.0f, 629.0f),
                PathNode.QuadTo(912.0f, 630.0f, 919.0f, 625.0f),
                PathNode.LineTo(934.0f, 613.0f),
                PathNode.QuadTo(941.0f, 608.0f, 942.0f, 599.0f),
                PathNode.QuadTo(943.0f, 590.0f, 938.0f, 583.0f),
                PathNode.LineTo(627.0f, 179.0f),
                PathNode.QuadTo(618.0f, 167.0f, 605.0f, 166.0f),
                PathNode.QuadTo(592.0f, 165.0f, 581.0f, 176.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _tasksRegular!!
    }

private var _tasksRegular: ImageVector? = null

val MiuixIcons.Heavy.Tasks: ImageVector
    get() {
        if (_tasksHeavy != null) return _tasksHeavy!!
        _tasksHeavy = ImageVector.Builder(
            name = "Tasks.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1152.0f,
            viewportHeight = 1152.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -89.0f, translationY = 951.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1045.0f, -85.0f),
                PathNode.QuadTo(1098.0f, -59.0f, 1125.0f, -6.0f),
                PathNode.QuadTo(1139.0f, 21.0f, 1142.0f, 58.0f),
                PathNode.QuadTo(1145.0f, 95.0f, 1145.0f, 177.0f),
                PathNode.VerticalTo(573.0f),
                PathNode.QuadTo(1145.0f, 655.0f, 1142.0f, 692.0f),
                PathNode.QuadTo(1139.0f, 729.0f, 1125.0f, 756.0f),
                PathNode.QuadTo(1098.0f, 809.0f, 1045.0f, 835.0f),
                PathNode.QuadTo(1018.0f, 849.0f, 981.5f, 852.0f),
                PathNode.QuadTo(945.0f, 855.0f, 863.0f, 855.0f),
                PathNode.HorizontalTo(467.0f),
                PathNode.QuadTo(384.0f, 855.0f, 347.5f, 852.0f),
                PathNode.QuadTo(311.0f, 849.0f, 284.0f, 835.0f),
                PathNode.QuadTo(231.0f, 809.0f, 205.0f, 756.0f),
                PathNode.QuadTo(191.0f, 729.0f, 188.0f, 692.0f),
                PathNode.QuadTo(185.0f, 655.0f, 185.0f, 573.0f),
                PathNode.VerticalTo(177.0f),
                PathNode.QuadTo(185.0f, 95.0f, 188.0f, 58.0f),
                PathNode.QuadTo(191.0f, 21.0f, 205.0f, -6.0f),
                PathNode.QuadTo(231.0f, -59.0f, 284.0f, -85.0f),
                PathNode.QuadTo(311.0f, -99.0f, 347.5f, -102.0f),
                PathNode.QuadTo(384.0f, -105.0f, 467.0f, -105.0f),
                PathNode.HorizontalTo(863.0f),
                PathNode.QuadTo(945.0f, -105.0f, 981.5f, -102.0f),
                PathNode.QuadTo(1018.0f, -99.0f, 1045.0f, -85.0f),
                PathNode.Close,
                PathNode.MoveTo(569.0f, 166.0f),
                PathNode.LineTo(407.0f, 327.0f),
                PathNode.QuadTo(397.0f, 338.0f, 397.0f, 353.0f),
                PathNode.QuadTo(397.0f, 368.0f, 407.0f, 378.0f),
                PathNode.LineTo(422.0f, 394.0f),
                PathNode.QuadTo(433.0f, 405.0f, 448.0f, 404.5f),
                PathNode.QuadTo(463.0f, 404.0f, 474.0f, 393.0f),
                PathNode.LineTo(599.0f, 269.0f),
                PathNode.LineTo(861.0f, 610.0f),
                PathNode.QuadTo(869.0f, 621.0f, 885.0f, 623.0f),
                PathNode.QuadTo(901.0f, 625.0f, 912.0f, 616.0f),
                PathNode.LineTo(927.0f, 604.0f),
                PathNode.QuadTo(940.0f, 595.0f, 941.5f, 580.0f),
                PathNode.QuadTo(943.0f, 565.0f, 933.0f, 553.0f),
                PathNode.LineTo(639.0f, 171.0f),
                PathNode.QuadTo(626.0f, 154.0f, 605.0f, 152.5f),
                PathNode.QuadTo(584.0f, 151.0f, 569.0f, 166.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _tasksHeavy!!
    }

private var _tasksHeavy: ImageVector? = null
