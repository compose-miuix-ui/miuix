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

val MiuixIcons.FavoritesFill: ImageVector
    get() = MiuixIcons.Regular.FavoritesFill

val MiuixIcons.Light.FavoritesFill: ImageVector
    get() {
        if (_favoritesfillLight != null) return _favoritesfillLight!!
        _favoritesfillLight = ImageVector.Builder(
            name = "FavoritesFill.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1290.0f,
            viewportHeight = 1290.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -19.5f, translationY = 1029.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(702.0f, 796.0f),
                PathNode.LineTo(693.875f, 789.3333333333334f),
                PathNode.LineTo(676.0f, 771.0f),
                PathNode.QuadTo(664.0f, 758.0f, 654.0f, 770.0f),
                PathNode.QuadTo(640.0f, 787.0f, 628.0f, 796.0f),
                PathNode.QuadTo(587.0f, 830.0f, 537.5f, 848.0f),
                PathNode.QuadTo(488.0f, 866.0f, 433.1830985915493f, 866.0f),
                PathNode.QuadTo(349.0f, 866.0f, 279.02173913043475f, 825.1052631578948f),
                PathNode.QuadTo(209.04347826086956f, 784.2105263157895f, 168.02173913043478f, 714.1052631578948f),
                PathNode.QuadTo(127.0f, 644.0f, 127.0f, 560.7410358565737f),
                PathNode.QuadTo(127.0f, 473.60956175298804f, 172.0f, 401.0f),
                PathNode.QuadTo(228.0f, 306.0f, 356.4935064935065f, 175.96246648793567f),
                PathNode.QuadTo(484.987012987013f, 45.924932975871315f, 616.0f, -70.0f),
                PathNode.QuadTo(629.0f, -81.0f, 638.0f, -88.0f),
                PathNode.QuadTo(647.0f, -95.0f, 653.0f, -96.0f),
                PathNode.QuadTo(667.0f, -100.0f, 678.0f, -96.0f),
                PathNode.QuadTo(684.0f, -95.0f, 693.5735294117646f, -87.57142857142857f),
                PathNode.QuadTo(703.1470588235294f, -80.14285714285714f, 715.0f, -69.0f),
                PathNode.QuadTo(841.0f, 43.0f, 964.3993506493506f, 167.07580174927114f),
                PathNode.QuadTo(1087.7987012987012f, 291.1516034985423f, 1150.0f, 389.0f),
                PathNode.QuadTo(1202.0f, 465.3068181818182f, 1202.0f, 560.9318181818181f),
                PathNode.QuadTo(1202.0f, 644.0f, 1160.8746630727762f, 714.1052631578948f),
                PathNode.QuadTo(1119.7493261455525f, 784.2105263157895f, 1050.078167115903f, 825.1052631578948f),
                PathNode.QuadTo(980.4070080862533f, 866.0f, 897.188679245283f, 866.0f),
                PathNode.QuadTo(843.0f, 866.0f, 792.7808219178082f, 847.5277777777778f),
                PathNode.QuadTo(742.5616438356165f, 829.0555555555555f, 702.0f, 796.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _favoritesfillLight!!
    }

private var _favoritesfillLight: ImageVector? = null

val MiuixIcons.Regular.FavoritesFill: ImageVector
    get() {
        if (_favoritesfillRegular != null) return _favoritesfillRegular!!
        _favoritesfillRegular = ImageVector.Builder(
            name = "FavoritesFill.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1306.8f,
            viewportHeight = 1306.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -11.100000000000023f, translationY = 1037.15f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(692.0f, 801.0f),
                PathNode.LineTo(687.0f, 797.0f),
                PathNode.LineTo(676.0f, 786.0f),
                PathNode.QuadTo(664.0f, 774.0f, 653.0f, 786.0f),
                PathNode.QuadTo(646.0f, 794.0f, 637.0f, 801.0f),
                PathNode.QuadTo(595.0f, 835.0f, 543.5f, 854.0f),
                PathNode.QuadTo(492.0f, 873.0f, 436.0f, 873.0f),
                PathNode.QuadTo(350.0f, 873.0f, 277.5f, 831.0f),
                PathNode.QuadTo(205.0f, 789.0f, 162.5f, 717.0f),
                PathNode.QuadTo(120.0f, 645.0f, 120.0f, 559.0f),
                PathNode.QuadTo(120.0f, 469.0f, 167.0f, 394.0f),
                PathNode.QuadTo(223.0f, 299.0f, 350.5f, 170.0f),
                PathNode.QuadTo(478.0f, 41.0f, 608.0f, -74.0f),
                PathNode.QuadTo(621.0f, -86.0f, 631.0f, -93.5f),
                PathNode.QuadTo(641.0f, -101.0f, 649.0f, -103.0f),
                PathNode.QuadTo(666.0f, -108.0f, 682.0f, -103.0f),
                PathNode.QuadTo(689.0f, -101.0f, 699.5f, -93.0f),
                PathNode.QuadTo(710.0f, -85.0f, 723.0f, -73.0f),
                PathNode.QuadTo(847.0f, 38.0f, 970.0f, 161.0f),
                PathNode.QuadTo(1093.0f, 284.0f, 1155.0f, 381.0f),
                PathNode.QuadTo(1209.0f, 460.0f, 1209.0f, 559.0f),
                PathNode.QuadTo(1209.0f, 645.0f, 1166.5f, 717.0f),
                PathNode.QuadTo(1124.0f, 789.0f, 1052.0f, 831.0f),
                PathNode.QuadTo(980.0f, 873.0f, 894.0f, 873.0f),
                PathNode.QuadTo(838.0f, 873.0f, 786.0f, 854.0f),
                PathNode.QuadTo(734.0f, 835.0f, 692.0f, 801.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _favoritesfillRegular!!
    }

private var _favoritesfillRegular: ImageVector? = null

val MiuixIcons.Heavy.FavoritesFill: ImageVector
    get() {
        if (_favoritesfillHeavy != null) return _favoritesfillHeavy!!
        _favoritesfillHeavy = ImageVector.Builder(
            name = "FavoritesFill.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1326.0f,
            viewportHeight = 1326.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -1.5f, translationY = 1046.8636363636365f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(688.0f, 812.0f),
                PathNode.LineTo(682.0f, 807.0f),
                PathNode.LineTo(672.0f, 798.0f),
                PathNode.QuadTo(665.0f, 791.0f, 657.0f, 798.0f),
                PathNode.QuadTo(655.0f, 801.0f, 643.0f, 811.0f),
                PathNode.QuadTo(601.0f, 844.0f, 549.0f, 863.0f),
                PathNode.QuadTo(497.0f, 882.0f, 439.0f, 882.0f),
                PathNode.QuadTo(350.0f, 882.0f, 275.0f, 838.5f),
                PathNode.QuadTo(200.0f, 795.0f, 156.0f, 720.5f),
                PathNode.QuadTo(112.0f, 646.0f, 112.0f, 557.0f),
                PathNode.QuadTo(112.0f, 462.0f, 160.0f, 386.0f),
                PathNode.QuadTo(216.0f, 291.0f, 341.5f, 164.0f),
                PathNode.QuadTo(467.0f, 37.0f, 598.0f, -80.0f),
                PathNode.QuadTo(612.0f, -92.0f, 623.5f, -100.5f),
                PathNode.QuadTo(635.0f, -109.0f, 645.0f, -112.0f),
                PathNode.QuadTo(667.0f, -117.0f, 686.0f, -111.0f),
                PathNode.QuadTo(695.0f, -109.0f, 707.0f, -100.0f),
                PathNode.QuadTo(719.0f, -91.0f, 733.0f, -79.0f),
                PathNode.QuadTo(859.0f, 34.0f, 979.5f, 155.0f),
                PathNode.QuadTo(1100.0f, 276.0f, 1161.0f, 372.0f),
                PathNode.QuadTo(1217.0f, 455.0f, 1217.0f, 557.0f),
                PathNode.QuadTo(1217.0f, 646.0f, 1173.0f, 720.5f),
                PathNode.QuadTo(1129.0f, 795.0f, 1054.5f, 838.5f),
                PathNode.QuadTo(980.0f, 882.0f, 891.0f, 882.0f),
                PathNode.QuadTo(833.0f, 882.0f, 780.5f, 863.5f),
                PathNode.QuadTo(728.0f, 845.0f, 688.0f, 812.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _favoritesfillHeavy!!
    }

private var _favoritesfillHeavy: ImageVector? = null
