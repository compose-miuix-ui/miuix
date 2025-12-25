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

val MiuixIcons.Filter: ImageVector
    get() = MiuixIcons.Regular.Filter

val MiuixIcons.Light.Filter: ImageVector
    get() {
        if (_filterLight != null) return _filterLight!!
        _filterLight = ImageVector.Builder(
            name = "Filter.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1111.6363636363635f,
            viewportHeight = 1111.6363636363635f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -108.29946524064178f, translationY = 928.6363636363636f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(676.0f, -53.0f),
                PathNode.VerticalTo(468.0f),
                PathNode.QuadTo(676.0f, 500.0f, 694.0f, 529.0f),
                PathNode.LineTo(865.0f, 783.0f),
                PathNode.QuadTo(873.0f, 794.0f, 871.0f, 806.5f),
                PathNode.QuadTo(869.0f, 819.0f, 860.0f, 827.5f),
                PathNode.QuadTo(851.0f, 836.0f, 837.0f, 836.0f),
                PathNode.HorizontalTo(248.0f),
                PathNode.QuadTo(235.0f, 836.0f, 225.5f, 827.5f),
                PathNode.QuadTo(216.0f, 819.0f, 214.5f, 806.5f),
                PathNode.QuadTo(213.0f, 794.0f, 220.0f, 783.0f),
                PathNode.LineTo(391.0f, 529.0f),
                PathNode.QuadTo(411.0f, 501.0f, 411.0f, 468.0f),
                PathNode.VerticalTo(95.0f),
                PathNode.QuadTo(411.0f, 73.0f, 420.0f, 56.0f),
                PathNode.QuadTo(429.0f, 39.0f, 447.0f, 27.0f),
                PathNode.LineTo(618.0f, -83.0f),
                PathNode.QuadTo(631.0f, -92.0f, 644.5f, -90.0f),
                PathNode.QuadTo(658.0f, -88.0f, 667.0f, -78.0f),
                PathNode.QuadTo(676.0f, -68.0f, 676.0f, -53.0f),
                PathNode.Close,
                PathNode.MoveTo(469.0f, 111.0f),
                PathNode.VerticalTo(471.0f),
                PathNode.QuadTo(469.0f, 520.0f, 442.0f, 559.0f),
                PathNode.LineTo(305.0f, 763.0f),
                PathNode.QuadTo(301.0f, 769.0f, 303.0f, 773.0f),
                PathNode.QuadTo(305.0f, 777.0f, 312.0f, 777.0f),
                PathNode.HorizontalTo(775.0f),
                PathNode.QuadTo(781.0f, 777.0f, 783.5f, 773.0f),
                PathNode.QuadTo(786.0f, 769.0f, 782.0f, 763.0f),
                PathNode.LineTo(644.0f, 559.0f),
                PathNode.QuadTo(617.0f, 518.0f, 617.0f, 471.0f),
                PathNode.VerticalTo(0.0f),
                PathNode.QuadTo(617.0f, -13.0f, 604.0f, -5.0f),
                PathNode.LineTo(494.0f, 67.0f),
                PathNode.QuadTo(480.0f, 76.0f, 474.5f, 85.5f),
                PathNode.QuadTo(469.0f, 95.0f, 469.0f, 111.0f),
                PathNode.Close,
                PathNode.MoveTo(1114.0f, 301.0f),
                PathNode.VerticalTo(319.0f),
                PathNode.QuadTo(1114.0f, 330.0f, 1108.5f, 334.5f),
                PathNode.QuadTo(1103.0f, 339.0f, 1092.0f, 339.0f),
                PathNode.HorizontalTo(872.0f),
                PathNode.QuadTo(860.0f, 339.0f, 855.0f, 334.5f),
                PathNode.QuadTo(850.0f, 330.0f, 850.0f, 318.0f),
                PathNode.VerticalTo(301.0f),
                PathNode.QuadTo(850.0f, 290.0f, 855.5f, 285.0f),
                PathNode.QuadTo(861.0f, 280.0f, 872.0f, 280.0f),
                PathNode.HorizontalTo(1092.0f),
                PathNode.QuadTo(1103.0f, 280.0f, 1108.5f, 285.0f),
                PathNode.QuadTo(1114.0f, 290.0f, 1114.0f, 301.0f),
                PathNode.Close,
                PathNode.MoveTo(1114.0f, 83.0f),
                PathNode.VerticalTo(101.0f),
                PathNode.QuadTo(1114.0f, 112.0f, 1108.5f, 116.5f),
                PathNode.QuadTo(1103.0f, 121.0f, 1092.0f, 121.0f),
                PathNode.HorizontalTo(872.0f),
                PathNode.QuadTo(860.0f, 121.0f, 855.0f, 116.5f),
                PathNode.QuadTo(850.0f, 112.0f, 850.0f, 100.0f),
                PathNode.VerticalTo(83.0f),
                PathNode.QuadTo(850.0f, 72.0f, 855.5f, 67.0f),
                PathNode.QuadTo(861.0f, 62.0f, 872.0f, 62.0f),
                PathNode.HorizontalTo(1092.0f),
                PathNode.QuadTo(1103.0f, 62.0f, 1108.5f, 67.0f),
                PathNode.QuadTo(1114.0f, 72.0f, 1114.0f, 83.0f),
                PathNode.Close,
                PathNode.MoveTo(1114.0f, 504.0f),
                PathNode.VerticalTo(522.0f),
                PathNode.QuadTo(1114.0f, 533.0f, 1108.5f, 537.5f),
                PathNode.QuadTo(1103.0f, 542.0f, 1092.0f, 542.0f),
                PathNode.HorizontalTo(872.0f),
                PathNode.QuadTo(860.0f, 542.0f, 855.0f, 537.5f),
                PathNode.QuadTo(850.0f, 533.0f, 850.0f, 521.0f),
                PathNode.VerticalTo(504.0f),
                PathNode.QuadTo(850.0f, 493.0f, 855.5f, 488.0f),
                PathNode.QuadTo(861.0f, 483.0f, 872.0f, 483.0f),
                PathNode.HorizontalTo(1092.0f),
                PathNode.QuadTo(1103.0f, 483.0f, 1108.5f, 488.0f),
                PathNode.QuadTo(1114.0f, 493.0f, 1114.0f, 504.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _filterLight!!
    }

private var _filterLight: ImageVector? = null

val MiuixIcons.Regular.Filter: ImageVector
    get() {
        if (_filterRegular != null) return _filterRegular!!
        _filterRegular = ImageVector.Builder(
            name = "Filter.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1146.0f,
            viewportHeight = 1146.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -91.5f, translationY = 944.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(696.0f, -59.0f),
                PathNode.VerticalTo(468.0f),
                PathNode.QuadTo(696.0f, 497.0f, 712.0f, 521.0f),
                PathNode.LineTo(883.0f, 775.0f),
                PathNode.QuadTo(894.0f, 790.0f, 891.5f, 807.5f),
                PathNode.QuadTo(889.0f, 825.0f, 875.5f, 837.0f),
                PathNode.QuadTo(862.0f, 849.0f, 844.0f, 849.0f),
                PathNode.HorizontalTo(255.0f),
                PathNode.QuadTo(237.0f, 849.0f, 224.0f, 837.0f),
                PathNode.QuadTo(211.0f, 825.0f, 208.5f, 808.0f),
                PathNode.QuadTo(206.0f, 791.0f, 216.0f, 775.0f),
                PathNode.LineTo(387.0f, 521.0f),
                PathNode.QuadTo(404.0f, 498.0f, 404.0f, 468.0f),
                PathNode.VerticalTo(95.0f),
                PathNode.QuadTo(404.0f, 71.0f, 415.5f, 50.0f),
                PathNode.QuadTo(427.0f, 29.0f, 447.0f, 16.0f),
                PathNode.LineTo(623.0f, -98.0f),
                PathNode.QuadTo(638.0f, -108.0f, 655.0f, -105.5f),
                PathNode.QuadTo(672.0f, -103.0f, 684.0f, -90.0f),
                PathNode.QuadTo(696.0f, -77.0f, 696.0f, -59.0f),
                PathNode.Close,
                PathNode.MoveTo(489.0f, 116.0f),
                PathNode.VerticalTo(471.0f),
                PathNode.QuadTo(489.0f, 523.0f, 460.0f, 567.0f),
                PathNode.LineTo(337.0f, 749.0f),
                PathNode.QuadTo(334.0f, 754.0f, 336.5f, 759.0f),
                PathNode.QuadTo(339.0f, 764.0f, 345.0f, 764.0f),
                PathNode.HorizontalTo(755.0f),
                PathNode.QuadTo(760.0f, 764.0f, 763.0f, 758.5f),
                PathNode.QuadTo(766.0f, 753.0f, 763.0f, 749.0f),
                PathNode.LineTo(640.0f, 567.0f),
                PathNode.QuadTo(610.0f, 521.0f, 610.0f, 471.0f),
                PathNode.VerticalTo(26.0f),
                PathNode.QuadTo(610.0f, 12.0f, 596.0f, 21.0f),
                PathNode.LineTo(511.0f, 76.0f),
                PathNode.QuadTo(501.0f, 83.0f, 495.0f, 93.5f),
                PathNode.QuadTo(489.0f, 104.0f, 489.0f, 116.0f),
                PathNode.Close,
                PathNode.MoveTo(1121.0f, 298.0f),
                PathNode.VerticalTo(323.0f),
                PathNode.QuadTo(1121.0f, 339.0f, 1112.5f, 345.5f),
                PathNode.QuadTo(1104.0f, 352.0f, 1087.0f, 352.0f),
                PathNode.HorizontalTo(891.0f),
                PathNode.QuadTo(873.0f, 352.0f, 865.0f, 345.5f),
                PathNode.QuadTo(857.0f, 339.0f, 857.0f, 321.0f),
                PathNode.VerticalTo(298.0f),
                PathNode.QuadTo(857.0f, 281.0f, 865.0f, 274.0f),
                PathNode.QuadTo(873.0f, 267.0f, 891.0f, 267.0f),
                PathNode.HorizontalTo(1087.0f),
                PathNode.QuadTo(1105.0f, 267.0f, 1113.0f, 274.0f),
                PathNode.QuadTo(1121.0f, 281.0f, 1121.0f, 298.0f),
                PathNode.Close,
                PathNode.MoveTo(1121.0f, 80.0f),
                PathNode.VerticalTo(105.0f),
                PathNode.QuadTo(1121.0f, 121.0f, 1112.5f, 127.5f),
                PathNode.QuadTo(1104.0f, 134.0f, 1087.0f, 134.0f),
                PathNode.HorizontalTo(891.0f),
                PathNode.QuadTo(873.0f, 134.0f, 865.0f, 127.5f),
                PathNode.QuadTo(857.0f, 121.0f, 857.0f, 103.0f),
                PathNode.VerticalTo(80.0f),
                PathNode.QuadTo(857.0f, 63.0f, 865.0f, 56.0f),
                PathNode.QuadTo(873.0f, 49.0f, 891.0f, 49.0f),
                PathNode.HorizontalTo(1087.0f),
                PathNode.QuadTo(1105.0f, 49.0f, 1113.0f, 56.0f),
                PathNode.QuadTo(1121.0f, 63.0f, 1121.0f, 80.0f),
                PathNode.Close,
                PathNode.MoveTo(1121.0f, 501.0f),
                PathNode.VerticalTo(526.0f),
                PathNode.QuadTo(1121.0f, 542.0f, 1112.5f, 548.5f),
                PathNode.QuadTo(1104.0f, 555.0f, 1087.0f, 555.0f),
                PathNode.HorizontalTo(891.0f),
                PathNode.QuadTo(873.0f, 555.0f, 865.0f, 548.5f),
                PathNode.QuadTo(857.0f, 542.0f, 857.0f, 524.0f),
                PathNode.VerticalTo(501.0f),
                PathNode.QuadTo(857.0f, 484.0f, 865.0f, 477.0f),
                PathNode.QuadTo(873.0f, 470.0f, 891.0f, 470.0f),
                PathNode.HorizontalTo(1087.0f),
                PathNode.QuadTo(1105.0f, 470.0f, 1113.0f, 477.0f),
                PathNode.QuadTo(1121.0f, 484.0f, 1121.0f, 501.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _filterRegular!!
    }

private var _filterRegular: ImageVector? = null

val MiuixIcons.Heavy.Filter: ImageVector
    get() {
        if (_filterHeavy != null) return _filterHeavy!!
        _filterHeavy = ImageVector.Builder(
            name = "Filter.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1178.6909090909091f,
            viewportHeight = 1178.6909090909091f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -75.37329545454543f, translationY = 961.2242424242424f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(710.0f, -59.0f),
                PathNode.VerticalTo(468.0f),
                PathNode.QuadTo(710.0f, 492.0f, 723.0f, 513.0f),
                PathNode.LineTo(894.0f, 767.0f),
                PathNode.QuadTo(908.0f, 787.0f, 904.5f, 809.5f),
                PathNode.QuadTo(901.0f, 832.0f, 884.5f, 847.5f),
                PathNode.QuadTo(868.0f, 863.0f, 844.0f, 863.0f),
                PathNode.HorizontalTo(255.0f),
                PathNode.QuadTo(231.0f, 863.0f, 214.5f, 847.5f),
                PathNode.QuadTo(198.0f, 832.0f, 195.0f, 809.5f),
                PathNode.QuadTo(192.0f, 787.0f, 205.0f, 767.0f),
                PathNode.LineTo(376.0f, 513.0f),
                PathNode.QuadTo(390.0f, 493.0f, 390.0f, 468.0f),
                PathNode.VerticalTo(95.0f),
                PathNode.QuadTo(390.0f, 67.0f, 403.5f, 43.0f),
                PathNode.QuadTo(417.0f, 19.0f, 440.0f, 5.0f),
                PathNode.LineTo(616.0f, -109.0f),
                PathNode.QuadTo(635.0f, -122.0f, 657.5f, -118.5f),
                PathNode.QuadTo(680.0f, -115.0f, 695.0f, -98.5f),
                PathNode.QuadTo(710.0f, -82.0f, 710.0f, -59.0f),
                PathNode.Close,
                PathNode.MoveTo(503.0f, 116.0f),
                PathNode.VerticalTo(471.0f),
                PathNode.QuadTo(503.0f, 528.0f, 471.0f, 575.0f),
                PathNode.LineTo(361.0f, 738.0f),
                PathNode.QuadTo(358.0f, 743.0f, 360.0f, 746.5f),
                PathNode.QuadTo(362.0f, 750.0f, 368.0f, 750.0f),
                PathNode.HorizontalTo(732.0f),
                PathNode.QuadTo(737.0f, 750.0f, 739.5f, 746.0f),
                PathNode.QuadTo(742.0f, 742.0f, 739.0f, 738.0f),
                PathNode.LineTo(629.0f, 575.0f),
                PathNode.QuadTo(596.0f, 526.0f, 596.0f, 471.0f),
                PathNode.VerticalTo(49.0f),
                PathNode.QuadTo(596.0f, 36.0f, 585.0f, 44.0f),
                PathNode.LineTo(518.0f, 87.0f),
                PathNode.QuadTo(511.0f, 92.0f, 507.0f, 100.0f),
                PathNode.QuadTo(503.0f, 108.0f, 503.0f, 116.0f),
                PathNode.Close,
                PathNode.MoveTo(1135.0f, 298.0f),
                PathNode.VerticalTo(323.0f),
                PathNode.QuadTo(1135.0f, 345.0f, 1123.0f, 355.5f),
                PathNode.QuadTo(1111.0f, 366.0f, 1087.0f, 366.0f),
                PathNode.HorizontalTo(896.0f),
                PathNode.QuadTo(871.0f, 366.0f, 859.5f, 355.0f),
                PathNode.QuadTo(848.0f, 344.0f, 848.0f, 321.0f),
                PathNode.VerticalTo(298.0f),
                PathNode.QuadTo(848.0f, 275.0f, 859.5f, 264.0f),
                PathNode.QuadTo(871.0f, 253.0f, 896.0f, 253.0f),
                PathNode.HorizontalTo(1087.0f),
                PathNode.QuadTo(1112.0f, 253.0f, 1123.5f, 264.0f),
                PathNode.QuadTo(1135.0f, 275.0f, 1135.0f, 298.0f),
                PathNode.Close,
                PathNode.MoveTo(1135.0f, 75.0f),
                PathNode.VerticalTo(100.0f),
                PathNode.QuadTo(1135.0f, 122.0f, 1123.0f, 132.5f),
                PathNode.QuadTo(1111.0f, 143.0f, 1087.0f, 143.0f),
                PathNode.HorizontalTo(896.0f),
                PathNode.QuadTo(870.0f, 143.0f, 859.0f, 132.0f),
                PathNode.QuadTo(848.0f, 121.0f, 848.0f, 98.0f),
                PathNode.VerticalTo(75.0f),
                PathNode.QuadTo(848.0f, 53.0f, 860.0f, 42.0f),
                PathNode.QuadTo(872.0f, 31.0f, 896.0f, 31.0f),
                PathNode.HorizontalTo(1087.0f),
                PathNode.QuadTo(1111.0f, 31.0f, 1123.0f, 42.0f),
                PathNode.QuadTo(1135.0f, 53.0f, 1135.0f, 75.0f),
                PathNode.Close,
                PathNode.MoveTo(1135.0f, 506.0f),
                PathNode.VerticalTo(531.0f),
                PathNode.QuadTo(1135.0f, 552.0f, 1123.0f, 562.5f),
                PathNode.QuadTo(1111.0f, 573.0f, 1087.0f, 573.0f),
                PathNode.HorizontalTo(896.0f),
                PathNode.QuadTo(870.0f, 573.0f, 859.0f, 562.0f),
                PathNode.QuadTo(848.0f, 551.0f, 848.0f, 529.0f),
                PathNode.VerticalTo(506.0f),
                PathNode.QuadTo(848.0f, 483.0f, 859.5f, 472.0f),
                PathNode.QuadTo(871.0f, 461.0f, 896.0f, 461.0f),
                PathNode.HorizontalTo(1087.0f),
                PathNode.QuadTo(1112.0f, 461.0f, 1123.5f, 472.0f),
                PathNode.QuadTo(1135.0f, 483.0f, 1135.0f, 506.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _filterHeavy!!
    }

private var _filterHeavy: ImageVector? = null
