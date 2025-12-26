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

val MiuixIcons.FileDownloads: ImageVector
    get() = MiuixIcons.Regular.FileDownloads

val MiuixIcons.Light.FileDownloads: ImageVector
    get() {
        if (_filedownloadsLight != null) return _filedownloadsLight!!
        _filedownloadsLight = ImageVector.Builder(
            name = "FileDownloads.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1080.0f,
            viewportHeight = 1080.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -125.0f, translationY = 915.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1033.0f, -59.0f),
                        PathNode.QuadTo(1077.0f, -38.0f, 1099.0f, 6.0f),
                        PathNode.QuadTo(1110.0f, 29.0f, 1112.5f, 61.5f),
                        PathNode.QuadTo(1115.0f, 94.0f, 1115.0f, 174.0f),
                        PathNode.VerticalTo(575.0f),
                        PathNode.QuadTo(1115.0f, 656.0f, 1112.5f, 688.5f),
                        PathNode.QuadTo(1110.0f, 721.0f, 1099.0f, 743.0f),
                        PathNode.QuadTo(1077.0f, 788.0f, 1033.0f, 809.0f),
                        PathNode.QuadTo(1011.0f, 820.0f, 978.5f, 822.5f),
                        PathNode.QuadTo(946.0f, 825.0f, 865.0f, 825.0f),
                        PathNode.HorizontalTo(465.0f),
                        PathNode.QuadTo(385.0f, 825.0f, 352.0f, 822.5f),
                        PathNode.QuadTo(319.0f, 820.0f, 297.0f, 809.0f),
                        PathNode.QuadTo(254.0f, 788.0f, 231.0f, 743.0f),
                        PathNode.QuadTo(220.0f, 721.0f, 217.5f, 688.5f),
                        PathNode.QuadTo(215.0f, 656.0f, 215.0f, 575.0f),
                        PathNode.VerticalTo(174.0f),
                        PathNode.QuadTo(215.0f, 94.0f, 217.5f, 61.5f),
                        PathNode.QuadTo(220.0f, 29.0f, 231.0f, 6.0f),
                        PathNode.QuadTo(253.0f, -38.0f, 297.0f, -59.0f),
                        PathNode.QuadTo(319.0f, -70.0f, 352.0f, -72.5f),
                        PathNode.QuadTo(385.0f, -75.0f, 465.0f, -75.0f),
                        PathNode.HorizontalTo(865.0f),
                        PathNode.QuadTo(946.0f, -75.0f, 978.5f, -72.5f),
                        PathNode.QuadTo(1011.0f, -70.0f, 1033.0f, -59.0f),
                        PathNode.Close,
                        PathNode.MoveTo(465.0f, 340.0f),
                        PathNode.LineTo(473.0f, 348.0f),
                        PathNode.QuadTo(480.0f, 355.0f, 487.5f, 355.0f),
                        PathNode.QuadTo(495.0f, 355.0f, 500.0f, 349.0f),
                        PathNode.LineTo(641.0f, 210.0f),
                        PathNode.VerticalTo(644.0f),
                        PathNode.QuadTo(641.0f, 651.0f, 646.5f, 655.5f),
                        PathNode.QuadTo(652.0f, 660.0f, 659.0f, 660.0f),
                        PathNode.HorizontalTo(671.0f),
                        PathNode.QuadTo(678.0f, 660.0f, 683.5f, 655.5f),
                        PathNode.QuadTo(689.0f, 651.0f, 689.0f, 644.0f),
                        PathNode.VerticalTo(210.0f),
                        PathNode.LineTo(830.0f, 349.0f),
                        PathNode.QuadTo(835.0f, 354.0f, 842.5f, 354.5f),
                        PathNode.QuadTo(850.0f, 355.0f, 855.0f, 349.0f),
                        PathNode.LineTo(865.0f, 340.0f),
                        PathNode.QuadTo(870.0f, 335.0f, 870.0f, 327.5f),
                        PathNode.QuadTo(870.0f, 320.0f, 865.0f, 315.0f),
                        PathNode.LineTo(682.0f, 133.0f),
                        PathNode.QuadTo(674.0f, 124.0f, 665.5f, 124.0f),
                        PathNode.QuadTo(657.0f, 124.0f, 648.0f, 133.0f),
                        PathNode.LineTo(466.0f, 317.0f),
                        PathNode.QuadTo(461.0f, 322.0f, 460.5f, 328.5f),
                        PathNode.QuadTo(460.0f, 335.0f, 465.0f, 340.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _filedownloadsLight!!
    }

private var _filedownloadsLight: ImageVector? = null

val MiuixIcons.Regular.FileDownloads: ImageVector
    get() {
        if (_filedownloadsRegular != null) return _filedownloadsRegular!!
        _filedownloadsRegular = ImageVector.Builder(
            name = "FileDownloads.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1118.3999999999999f,
            viewportHeight = 1118.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -105.80000000000007f, translationY = 934.1999999999999f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1040.0f, -73.0f),
                        PathNode.QuadTo(1088.0f, -49.0f, 1113.0f, -1.0f),
                        PathNode.QuadTo(1125.0f, 24.0f, 1128.0f, 58.5f),
                        PathNode.QuadTo(1131.0f, 93.0f, 1131.0f, 174.0f),
                        PathNode.VerticalTo(575.0f),
                        PathNode.QuadTo(1131.0f, 657.0f, 1128.0f, 691.5f),
                        PathNode.QuadTo(1125.0f, 726.0f, 1113.0f, 750.0f),
                        PathNode.QuadTo(1088.0f, 799.0f, 1040.0f, 823.0f),
                        PathNode.QuadTo(1016.0f, 835.0f, 981.5f, 838.0f),
                        PathNode.QuadTo(947.0f, 841.0f, 865.0f, 841.0f),
                        PathNode.HorizontalTo(465.0f),
                        PathNode.QuadTo(384.0f, 841.0f, 349.0f, 838.0f),
                        PathNode.QuadTo(314.0f, 835.0f, 290.0f, 823.0f),
                        PathNode.QuadTo(242.0f, 799.0f, 217.0f, 750.0f),
                        PathNode.QuadTo(205.0f, 726.0f, 202.0f, 691.5f),
                        PathNode.QuadTo(199.0f, 657.0f, 199.0f, 575.0f),
                        PathNode.VerticalTo(174.0f),
                        PathNode.QuadTo(199.0f, 93.0f, 202.0f, 58.5f),
                        PathNode.QuadTo(205.0f, 24.0f, 217.0f, -1.0f),
                        PathNode.QuadTo(242.0f, -49.0f, 290.0f, -73.0f),
                        PathNode.QuadTo(314.0f, -85.0f, 349.0f, -88.0f),
                        PathNode.QuadTo(384.0f, -91.0f, 465.0f, -91.0f),
                        PathNode.HorizontalTo(865.0f),
                        PathNode.QuadTo(947.0f, -91.0f, 981.5f, -88.0f),
                        PathNode.QuadTo(1016.0f, -85.0f, 1040.0f, -73.0f),
                        PathNode.Close,
                        PathNode.MoveTo(457.0f, 344.0f),
                        PathNode.LineTo(466.0f, 353.0f),
                        PathNode.QuadTo(475.0f, 364.0f, 487.0f, 364.0f),
                        PathNode.QuadTo(499.0f, 364.0f, 506.0f, 356.0f),
                        PathNode.LineTo(632.0f, 231.0f),
                        PathNode.VerticalTo(645.0f),
                        PathNode.QuadTo(632.0f, 656.0f, 640.5f, 662.5f),
                        PathNode.QuadTo(649.0f, 669.0f, 659.0f, 669.0f),
                        PathNode.HorizontalTo(671.0f),
                        PathNode.QuadTo(680.0f, 669.0f, 689.0f, 662.0f),
                        PathNode.QuadTo(698.0f, 655.0f, 698.0f, 645.0f),
                        PathNode.VerticalTo(231.0f),
                        PathNode.LineTo(824.0f, 356.0f),
                        PathNode.QuadTo(832.0f, 364.0f, 842.5f, 364.0f),
                        PathNode.QuadTo(853.0f, 364.0f, 861.0f, 356.0f),
                        PathNode.LineTo(872.0f, 345.0f),
                        PathNode.QuadTo(879.0f, 338.0f, 878.5f, 327.0f),
                        PathNode.QuadTo(878.0f, 316.0f, 871.0f, 309.0f),
                        PathNode.LineTo(686.0f, 124.0f),
                        PathNode.QuadTo(676.0f, 114.0f, 665.0f, 114.5f),
                        PathNode.QuadTo(654.0f, 115.0f, 644.0f, 125.0f),
                        PathNode.LineTo(458.0f, 311.0f),
                        PathNode.QuadTo(451.0f, 318.0f, 451.0f, 328.0f),
                        PathNode.QuadTo(451.0f, 338.0f, 457.0f, 344.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _filedownloadsRegular!!
    }

private var _filedownloadsRegular: ImageVector? = null

val MiuixIcons.Heavy.FileDownloads: ImageVector
    get() {
        if (_filedownloadsHeavy != null) return _filedownloadsHeavy!!
        _filedownloadsHeavy = ImageVector.Builder(
            name = "FileDownloads.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1152.0f,
            viewportHeight = 1152.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -89.0f, translationY = 951.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1046.0f, -85.0f),
                        PathNode.QuadTo(1099.0f, -59.0f, 1125.0f, -7.0f),
                        PathNode.QuadTo(1139.0f, 20.0f, 1142.0f, 56.0f),
                        PathNode.QuadTo(1145.0f, 92.0f, 1145.0f, 174.0f),
                        PathNode.VerticalTo(575.0f),
                        PathNode.QuadTo(1145.0f, 657.0f, 1142.0f, 693.5f),
                        PathNode.QuadTo(1139.0f, 730.0f, 1125.0f, 756.0f),
                        PathNode.QuadTo(1098.0f, 810.0f, 1046.0f, 835.0f),
                        PathNode.QuadTo(1020.0f, 849.0f, 983.5f, 852.0f),
                        PathNode.QuadTo(947.0f, 855.0f, 865.0f, 855.0f),
                        PathNode.HorizontalTo(465.0f),
                        PathNode.QuadTo(383.0f, 855.0f, 346.5f, 852.0f),
                        PathNode.QuadTo(310.0f, 849.0f, 284.0f, 835.0f),
                        PathNode.QuadTo(232.0f, 810.0f, 205.0f, 756.0f),
                        PathNode.QuadTo(191.0f, 730.0f, 188.0f, 693.5f),
                        PathNode.QuadTo(185.0f, 657.0f, 185.0f, 575.0f),
                        PathNode.VerticalTo(174.0f),
                        PathNode.QuadTo(185.0f, 92.0f, 188.0f, 56.0f),
                        PathNode.QuadTo(191.0f, 20.0f, 205.0f, -7.0f),
                        PathNode.QuadTo(231.0f, -59.0f, 284.0f, -85.0f),
                        PathNode.QuadTo(310.0f, -99.0f, 346.5f, -102.0f),
                        PathNode.QuadTo(383.0f, -105.0f, 465.0f, -105.0f),
                        PathNode.HorizontalTo(865.0f),
                        PathNode.QuadTo(947.0f, -105.0f, 983.5f, -102.0f),
                        PathNode.QuadTo(1020.0f, -99.0f, 1046.0f, -85.0f),
                        PathNode.Close,
                        PathNode.MoveTo(445.0f, 350.0f),
                        PathNode.LineTo(460.0f, 367.0f),
                        PathNode.QuadTo(472.0f, 380.0f, 486.0f, 380.5f),
                        PathNode.QuadTo(500.0f, 381.0f, 511.0f, 370.0f),
                        PathNode.LineTo(618.0f, 264.0f),
                        PathNode.VerticalTo(649.0f),
                        PathNode.QuadTo(618.0f, 665.0f, 628.0f, 674.0f),
                        PathNode.QuadTo(638.0f, 683.0f, 653.0f, 683.0f),
                        PathNode.HorizontalTo(677.0f),
                        PathNode.QuadTo(692.0f, 683.0f, 702.0f, 674.0f),
                        PathNode.QuadTo(712.0f, 665.0f, 712.0f, 649.0f),
                        PathNode.VerticalTo(264.0f),
                        PathNode.LineTo(818.0f, 369.0f),
                        PathNode.QuadTo(829.0f, 380.0f, 842.5f, 380.5f),
                        PathNode.QuadTo(856.0f, 381.0f, 867.0f, 369.0f),
                        PathNode.LineTo(885.0f, 352.0f),
                        PathNode.QuadTo(895.0f, 341.0f, 895.0f, 327.5f),
                        PathNode.QuadTo(895.0f, 314.0f, 884.0f, 303.0f),
                        PathNode.LineTo(693.0f, 112.0f),
                        PathNode.QuadTo(680.0f, 99.0f, 666.0f, 99.0f),
                        PathNode.QuadTo(652.0f, 99.0f, 638.0f, 112.0f),
                        PathNode.LineTo(446.0f, 303.0f),
                        PathNode.QuadTo(435.0f, 313.0f, 435.0f, 326.0f),
                        PathNode.QuadTo(435.0f, 339.0f, 445.0f, 350.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _filedownloadsHeavy!!
    }

private var _filedownloadsHeavy: ImageVector? = null
