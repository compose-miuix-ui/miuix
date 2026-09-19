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

val MiuixIcons.Glass.Sort: ImageVector
    get() = MiuixIcons.Glass.Regular.Sort

val MiuixIcons.Glass.Light.Sort: ImageVector
    get() {
        if (_sortLight != null) return _sortLight!!
        _sortLight = ImageVector.Builder(
            name = "Sort.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(274.3f, 966.9f),
                        PathNode.LineTo(252.7f, 966.9f),
                        PathNode.QuadTo(243.1f, 966.9f, 238.6f, 961.0f),
                        PathNode.QuadTo(234.1f, 955.1f, 234.1f, 946.6f),
                        PathNode.LineTo(234.1f, 275.3f),
                        PathNode.LineTo(139.0f, 369.0f),
                        PathNode.QuadTo(133.1f, 374.9f, 126.7f, 374.6f),
                        PathNode.QuadTo(120.4f, 374.3f, 115.6f, 369.6f),
                        PathNode.LineTo(98.1f, 352.0f),
                        PathNode.QuadTo(92.2f, 346.1f, 92.5f, 340.0f),
                        PathNode.QuadTo(92.7f, 333.9f, 97.5f, 328.7f),
                        PathNode.LineTo(239.6f, 186.1f),
                        PathNode.QuadTo(250.1f, 175.7f, 263.5f, 176.0f),
                        PathNode.QuadTo(277.0f, 176.2f, 288.4f, 187.7f),
                        PathNode.LineTo(428.4f, 327.7f),
                        PathNode.QuadTo(434.3f, 333.6f, 434.5f, 339.7f),
                        PathNode.QuadTo(434.7f, 345.8f, 427.9f, 352.1f),
                        PathNode.LineTo(411.0f, 369.0f),
                        PathNode.QuadTo(405.6f, 374.3f, 399.3f, 374.6f),
                        PathNode.QuadTo(393.0f, 374.9f, 387.6f, 369.6f),
                        PathNode.LineTo(292.4f, 275.3f),
                        PathNode.LineTo(292.4f, 946.6f),
                        PathNode.QuadTo(292.4f, 955.1f, 287.9f, 961.0f),
                        PathNode.QuadTo(283.4f, 966.9f, 274.3f, 966.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1050.4f, 223.3f),
                        PathNode.LineTo(1050.4f, 245.3f),
                        PathNode.QuadTo(1050.4f, 255.0f, 1044.8f, 259.5f),
                        PathNode.QuadTo(1039.2f, 264.0f, 1030.1f, 264.0f),
                        PathNode.LineTo(504.6f, 264.0f),
                        PathNode.QuadTo(496.1f, 264.0f, 490.2f, 259.2f),
                        PathNode.QuadTo(484.3f, 254.5f, 484.3f, 245.9f),
                        PathNode.LineTo(484.3f, 223.9f),
                        PathNode.QuadTo(484.3f, 214.2f, 489.9f, 209.5f),
                        PathNode.QuadTo(495.5f, 204.7f, 504.6f, 204.7f),
                        PathNode.LineTo(1030.1f, 204.7f),
                        PathNode.QuadTo(1039.2f, 204.7f, 1044.8f, 209.2f),
                        PathNode.QuadTo(1050.4f, 213.7f, 1050.4f, 223.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1050.4f, 561.3f),
                        PathNode.LineTo(1050.4f, 583.3f),
                        PathNode.QuadTo(1050.4f, 593.0f, 1044.8f, 597.5f),
                        PathNode.QuadTo(1039.2f, 602.0f, 1030.1f, 602.0f),
                        PathNode.LineTo(504.6f, 602.0f),
                        PathNode.QuadTo(496.1f, 602.0f, 490.2f, 597.2f),
                        PathNode.QuadTo(484.3f, 592.5f, 484.3f, 583.9f),
                        PathNode.LineTo(484.3f, 561.9f),
                        PathNode.QuadTo(484.3f, 552.2f, 489.9f, 547.5f),
                        PathNode.QuadTo(495.5f, 542.7f, 504.6f, 542.7f),
                        PathNode.LineTo(1030.1f, 542.7f),
                        PathNode.QuadTo(1039.2f, 542.7f, 1044.8f, 547.2f),
                        PathNode.QuadTo(1050.4f, 551.7f, 1050.4f, 561.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1050.4f, 898.3f),
                        PathNode.LineTo(1050.4f, 920.3f),
                        PathNode.QuadTo(1050.4f, 930.0f, 1044.8f, 934.5f),
                        PathNode.QuadTo(1039.2f, 939.0f, 1030.1f, 939.0f),
                        PathNode.LineTo(504.6f, 939.0f),
                        PathNode.QuadTo(496.1f, 939.0f, 490.2f, 934.2f),
                        PathNode.QuadTo(484.3f, 929.5f, 484.3f, 920.9f),
                        PathNode.LineTo(484.3f, 898.9f),
                        PathNode.QuadTo(484.3f, 889.2f, 489.9f, 884.5f),
                        PathNode.QuadTo(495.5f, 879.7f, 504.6f, 879.7f),
                        PathNode.LineTo(1030.1f, 879.7f),
                        PathNode.QuadTo(1039.2f, 879.7f, 1044.8f, 884.2f),
                        PathNode.QuadTo(1050.4f, 888.7f, 1050.4f, 898.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sortLight!!
    }

private var _sortLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Sort: ImageVector
    get() {
        if (_sortNormal != null) return _sortNormal!!
        _sortNormal = ImageVector.Builder(
            name = "Sort.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(279.7f, 972.9f),
                        PathNode.LineTo(252.4f, 972.9f),
                        PathNode.QuadTo(239.0f, 972.9f, 233.2f, 964.9f),
                        PathNode.QuadTo(227.3f, 956.9f, 227.3f, 945.3f),
                        PathNode.LineTo(227.3f, 300.3f),
                        PathNode.LineTo(150.8f, 375.7f),
                        PathNode.QuadTo(142.8f, 383.7f, 134.6f, 383.3f),
                        PathNode.QuadTo(126.4f, 382.8f, 120.1f, 376.5f),
                        PathNode.LineTo(96.8f, 353.2f),
                        PathNode.QuadTo(88.8f, 345.1f, 89.2f, 337.0f),
                        PathNode.QuadTo(89.6f, 328.9f, 95.9f, 322.5f),
                        PathNode.LineTo(235.9f, 182.4f),
                        PathNode.QuadTo(248.8f, 169.5f, 266.0f, 169.9f),
                        PathNode.QuadTo(283.3f, 170.4f, 297.2f, 184.2f),
                        PathNode.LineTo(434.4f, 321.5f),
                        PathNode.QuadTo(442.5f, 329.5f, 442.5f, 337.6f),
                        PathNode.QuadTo(442.6f, 345.7f, 433.6f, 353.9f),
                        PathNode.LineTo(411.8f, 375.7f),
                        PathNode.QuadTo(404.6f, 382.8f, 396.5f, 383.3f),
                        PathNode.QuadTo(388.3f, 383.7f, 381.1f, 376.5f),
                        PathNode.LineTo(303.9f, 300.3f),
                        PathNode.LineTo(303.9f, 945.3f),
                        PathNode.QuadTo(303.9f, 956.9f, 298.1f, 964.9f),
                        PathNode.QuadTo(292.2f, 972.9f, 279.7f, 972.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1053.7f, 223.6f),
                        PathNode.LineTo(1053.7f, 251.1f),
                        PathNode.QuadTo(1053.7f, 264.4f, 1046.1f, 270.3f),
                        PathNode.QuadTo(1038.5f, 276.2f, 1026.0f, 276.2f),
                        PathNode.LineTo(514.6f, 276.2f),
                        PathNode.QuadTo(503.0f, 276.2f, 495.0f, 269.9f),
                        PathNode.QuadTo(487.0f, 263.6f, 487.0f, 251.9f),
                        PathNode.LineTo(487.0f, 224.4f),
                        PathNode.QuadTo(487.0f, 211.1f, 494.6f, 204.8f),
                        PathNode.QuadTo(502.2f, 198.5f, 514.6f, 198.5f),
                        PathNode.LineTo(1026.0f, 198.5f),
                        PathNode.QuadTo(1038.5f, 198.5f, 1046.1f, 204.4f),
                        PathNode.QuadTo(1053.7f, 210.2f, 1053.7f, 223.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1053.7f, 561.6f),
                        PathNode.LineTo(1053.7f, 589.1f),
                        PathNode.QuadTo(1053.7f, 602.4f, 1046.1f, 608.3f),
                        PathNode.QuadTo(1038.5f, 614.2f, 1026.0f, 614.2f),
                        PathNode.LineTo(514.6f, 614.2f),
                        PathNode.QuadTo(503.0f, 614.2f, 495.0f, 607.9f),
                        PathNode.QuadTo(487.0f, 601.6f, 487.0f, 589.9f),
                        PathNode.LineTo(487.0f, 562.4f),
                        PathNode.QuadTo(487.0f, 549.1f, 494.6f, 542.8f),
                        PathNode.QuadTo(502.2f, 536.5f, 514.6f, 536.5f),
                        PathNode.LineTo(1026.0f, 536.5f),
                        PathNode.QuadTo(1038.5f, 536.5f, 1046.1f, 542.4f),
                        PathNode.QuadTo(1053.7f, 548.2f, 1053.7f, 561.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1053.7f, 898.6f),
                        PathNode.LineTo(1053.7f, 926.1f),
                        PathNode.QuadTo(1053.7f, 939.4f, 1046.1f, 945.3f),
                        PathNode.QuadTo(1038.5f, 951.2f, 1026.0f, 951.2f),
                        PathNode.LineTo(514.6f, 951.2f),
                        PathNode.QuadTo(503.0f, 951.2f, 495.0f, 944.9f),
                        PathNode.QuadTo(487.0f, 938.6f, 487.0f, 926.9f),
                        PathNode.LineTo(487.0f, 899.4f),
                        PathNode.QuadTo(487.0f, 886.1f, 494.6f, 879.8f),
                        PathNode.QuadTo(502.2f, 873.5f, 514.6f, 873.5f),
                        PathNode.LineTo(1026.0f, 873.5f),
                        PathNode.QuadTo(1038.5f, 873.5f, 1046.1f, 879.4f),
                        PathNode.QuadTo(1053.7f, 885.2f, 1053.7f, 898.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sortNormal!!
    }

private var _sortNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Sort: ImageVector
    get() {
        if (_sortRegular != null) return _sortRegular!!
        _sortRegular = ImageVector.Builder(
            name = "Sort.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(282.2f, 975.7f),
                        PathNode.LineTo(252.2f, 975.7f),
                        PathNode.QuadTo(237.2f, 975.7f, 230.7f, 966.7f),
                        PathNode.QuadTo(224.2f, 957.7f, 224.2f, 944.7f),
                        PathNode.LineTo(224.2f, 311.7f),
                        PathNode.LineTo(156.2f, 378.7f),
                        PathNode.QuadTo(147.2f, 387.7f, 138.2f, 387.2f),
                        PathNode.QuadTo(129.2f, 386.7f, 122.2f, 379.7f),
                        PathNode.LineTo(96.2f, 353.7f),
                        PathNode.QuadTo(87.2f, 344.7f, 87.7f, 335.7f),
                        PathNode.QuadTo(88.2f, 326.7f, 95.2f, 319.7f),
                        PathNode.LineTo(234.2f, 180.7f),
                        PathNode.QuadTo(248.2f, 166.7f, 267.2f, 167.2f),
                        PathNode.QuadTo(286.2f, 167.7f, 301.2f, 182.7f),
                        PathNode.LineTo(437.2f, 318.7f),
                        PathNode.QuadTo(446.2f, 327.7f, 446.2f, 336.7f),
                        PathNode.QuadTo(446.2f, 345.7f, 436.2f, 354.7f),
                        PathNode.LineTo(412.2f, 378.7f),
                        PathNode.QuadTo(404.2f, 386.7f, 395.2f, 387.2f),
                        PathNode.QuadTo(386.2f, 387.7f, 378.2f, 379.7f),
                        PathNode.LineTo(309.2f, 311.7f),
                        PathNode.LineTo(309.2f, 944.7f),
                        PathNode.QuadTo(309.2f, 957.7f, 302.7f, 966.7f),
                        PathNode.QuadTo(296.2f, 975.7f, 282.2f, 975.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1055.2f, 223.7f),
                        PathNode.LineTo(1055.2f, 253.7f),
                        PathNode.QuadTo(1055.2f, 268.7f, 1046.7f, 275.2f),
                        PathNode.QuadTo(1038.2f, 281.7f, 1024.2f, 281.7f),
                        PathNode.LineTo(519.2f, 281.7f),
                        PathNode.QuadTo(506.2f, 281.7f, 497.2f, 274.7f),
                        PathNode.QuadTo(488.2f, 267.7f, 488.2f, 254.7f),
                        PathNode.LineTo(488.2f, 224.7f),
                        PathNode.QuadTo(488.2f, 209.7f, 496.7f, 202.7f),
                        PathNode.QuadTo(505.2f, 195.7f, 519.2f, 195.7f),
                        PathNode.LineTo(1024.2f, 195.7f),
                        PathNode.QuadTo(1038.2f, 195.7f, 1046.7f, 202.2f),
                        PathNode.QuadTo(1055.2f, 208.7f, 1055.2f, 223.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1055.2f, 561.7f),
                        PathNode.LineTo(1055.2f, 591.7f),
                        PathNode.QuadTo(1055.2f, 606.7f, 1046.7f, 613.2f),
                        PathNode.QuadTo(1038.2f, 619.7f, 1024.2f, 619.7f),
                        PathNode.LineTo(519.2f, 619.7f),
                        PathNode.QuadTo(506.2f, 619.7f, 497.2f, 612.7f),
                        PathNode.QuadTo(488.2f, 605.7f, 488.2f, 592.7f),
                        PathNode.LineTo(488.2f, 562.7f),
                        PathNode.QuadTo(488.2f, 547.7f, 496.7f, 540.7f),
                        PathNode.QuadTo(505.2f, 533.7f, 519.2f, 533.7f),
                        PathNode.LineTo(1024.2f, 533.7f),
                        PathNode.QuadTo(1038.2f, 533.7f, 1046.7f, 540.2f),
                        PathNode.QuadTo(1055.2f, 546.7f, 1055.2f, 561.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1055.2f, 898.7f),
                        PathNode.LineTo(1055.2f, 928.7f),
                        PathNode.QuadTo(1055.2f, 943.7f, 1046.7f, 950.2f),
                        PathNode.QuadTo(1038.2f, 956.7f, 1024.2f, 956.7f),
                        PathNode.LineTo(519.2f, 956.7f),
                        PathNode.QuadTo(506.2f, 956.7f, 497.2f, 949.7f),
                        PathNode.QuadTo(488.2f, 942.7f, 488.2f, 929.7f),
                        PathNode.LineTo(488.2f, 899.7f),
                        PathNode.QuadTo(488.2f, 884.7f, 496.7f, 877.7f),
                        PathNode.QuadTo(505.2f, 870.7f, 519.2f, 870.7f),
                        PathNode.LineTo(1024.2f, 870.7f),
                        PathNode.QuadTo(1038.2f, 870.7f, 1046.7f, 877.2f),
                        PathNode.QuadTo(1055.2f, 883.7f, 1055.2f, 898.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sortRegular!!
    }

private var _sortRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Sort: ImageVector
    get() {
        if (_sortMedium != null) return _sortMedium!!
        _sortMedium = ImageVector.Builder(
            name = "Sort.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(282.6f, 982.5f),
                        PathNode.LineTo(252.6f, 982.5f),
                        PathNode.QuadTo(234.6f, 982.5f, 226.1f, 971.3f),
                        PathNode.QuadTo(217.6f, 960.2f, 217.6f, 944.5f),
                        PathNode.LineTo(217.6f, 326.3f),
                        PathNode.LineTo(158.5f, 384.7f),
                        PathNode.QuadTo(147.3f, 395.9f, 135.6f, 395.4f),
                        PathNode.QuadTo(123.9f, 394.9f, 114.8f, 385.7f),
                        PathNode.LineTo(88.8f, 359.7f),
                        PathNode.QuadTo(77.6f, 349.1f, 78.1f, 337.1f),
                        PathNode.QuadTo(78.6f, 325.1f, 87.8f, 316.0f),
                        PathNode.LineTo(229.7f, 175.6f),
                        PathNode.QuadTo(245.9f, 160.0f, 267.6f, 160.4f),
                        PathNode.QuadTo(289.3f, 160.7f, 306.5f, 177.6f),
                        PathNode.LineTo(445.2f, 315.0f),
                        PathNode.QuadTo(456.1f, 325.9f, 456.2f, 337.8f),
                        PathNode.QuadTo(456.3f, 349.8f, 444.2f, 360.7f),
                        PathNode.LineTo(420.2f, 384.7f),
                        PathNode.QuadTo(410.3f, 394.9f, 398.3f, 395.4f),
                        PathNode.QuadTo(386.3f, 395.9f, 376.4f, 385.7f),
                        PathNode.LineTo(316.6f, 326.3f),
                        PathNode.LineTo(316.6f, 944.5f),
                        PathNode.QuadTo(316.6f, 960.5f, 308.1f, 971.5f),
                        PathNode.QuadTo(299.6f, 982.5f, 282.6f, 982.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1064.8f, 223.5f),
                        PathNode.LineTo(1064.8f, 253.5f),
                        PathNode.QuadTo(1064.8f, 272.0f, 1053.9f, 280.8f),
                        PathNode.QuadTo(1042.9f, 289.6f, 1025.7f, 289.6f),
                        PathNode.LineTo(531.5f, 289.6f),
                        PathNode.QuadTo(515.0f, 289.6f, 503.7f, 280.3f),
                        PathNode.QuadTo(492.4f, 271.0f, 492.4f, 254.5f),
                        PathNode.LineTo(492.4f, 224.5f),
                        PathNode.QuadTo(492.4f, 206.0f, 503.3f, 196.7f),
                        PathNode.QuadTo(514.2f, 187.4f, 531.5f, 187.4f),
                        PathNode.LineTo(1025.7f, 187.4f),
                        PathNode.QuadTo(1042.9f, 187.4f, 1053.9f, 196.2f),
                        PathNode.QuadTo(1064.8f, 205.0f, 1064.8f, 223.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1064.8f, 561.5f),
                        PathNode.LineTo(1064.8f, 591.5f),
                        PathNode.QuadTo(1064.8f, 610.0f, 1053.9f, 618.8f),
                        PathNode.QuadTo(1042.9f, 627.6f, 1025.7f, 627.6f),
                        PathNode.LineTo(531.5f, 627.6f),
                        PathNode.QuadTo(515.0f, 627.6f, 503.7f, 618.3f),
                        PathNode.QuadTo(492.4f, 609.0f, 492.4f, 592.5f),
                        PathNode.LineTo(492.4f, 562.5f),
                        PathNode.QuadTo(492.4f, 544.0f, 503.3f, 534.7f),
                        PathNode.QuadTo(514.2f, 525.4f, 531.5f, 525.4f),
                        PathNode.LineTo(1025.7f, 525.4f),
                        PathNode.QuadTo(1042.9f, 525.4f, 1053.9f, 534.2f),
                        PathNode.QuadTo(1064.8f, 543.0f, 1064.8f, 561.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1064.8f, 898.5f),
                        PathNode.LineTo(1064.8f, 928.5f),
                        PathNode.QuadTo(1064.8f, 947.0f, 1053.9f, 955.8f),
                        PathNode.QuadTo(1042.9f, 964.6f, 1025.7f, 964.6f),
                        PathNode.LineTo(531.5f, 964.6f),
                        PathNode.QuadTo(515.0f, 964.6f, 503.7f, 955.3f),
                        PathNode.QuadTo(492.4f, 946.0f, 492.4f, 929.5f),
                        PathNode.LineTo(492.4f, 899.5f),
                        PathNode.QuadTo(492.4f, 881.0f, 503.3f, 871.7f),
                        PathNode.QuadTo(514.2f, 862.4f, 531.5f, 862.4f),
                        PathNode.LineTo(1025.7f, 862.4f),
                        PathNode.QuadTo(1042.9f, 862.4f, 1053.9f, 871.2f),
                        PathNode.QuadTo(1064.8f, 880.0f, 1064.8f, 898.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sortMedium!!
    }

private var _sortMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Sort: ImageVector
    get() {
        if (_sortDemibold != null) return _sortDemibold!!
        _sortDemibold = ImageVector.Builder(
            name = "Sort.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(282.9f, 987.3f),
                        PathNode.LineTo(252.9f, 987.3f),
                        PathNode.QuadTo(232.8f, 987.3f, 222.9f, 974.6f),
                        PathNode.QuadTo(212.9f, 961.9f, 212.9f, 944.3f),
                        PathNode.LineTo(212.9f, 336.6f),
                        PathNode.LineTo(160.1f, 388.9f),
                        PathNode.QuadTo(147.4f, 401.6f, 133.8f, 401.1f),
                        PathNode.QuadTo(120.2f, 400.6f, 109.6f, 389.9f),
                        PathNode.LineTo(83.6f, 363.9f),
                        PathNode.QuadTo(70.9f, 352.1f, 71.4f, 338.1f),
                        PathNode.QuadTo(71.9f, 324.0f, 82.6f, 313.4f),
                        PathNode.LineTo(226.6f, 172.1f),
                        PathNode.QuadTo(244.3f, 155.3f, 267.9f, 155.6f),
                        PathNode.QuadTo(291.5f, 155.8f, 310.1f, 174.1f),
                        PathNode.LineTo(450.7f, 312.4f),
                        PathNode.QuadTo(463.0f, 324.6f, 463.2f, 338.6f),
                        PathNode.QuadTo(463.4f, 352.7f, 449.7f, 364.9f),
                        PathNode.LineTo(425.7f, 388.9f),
                        PathNode.QuadTo(414.5f, 400.6f, 400.5f, 401.1f),
                        PathNode.QuadTo(386.4f, 401.6f, 375.2f, 389.9f),
                        PathNode.LineTo(321.8f, 336.6f),
                        PathNode.LineTo(321.8f, 944.3f),
                        PathNode.QuadTo(321.8f, 962.4f, 311.9f, 974.8f),
                        PathNode.QuadTo(301.9f, 987.3f, 282.9f, 987.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1071.5f, 223.3f),
                        PathNode.LineTo(1071.5f, 253.3f),
                        PathNode.QuadTo(1071.5f, 274.3f, 1058.9f, 284.7f),
                        PathNode.QuadTo(1046.2f, 295.1f, 1026.7f, 295.1f),
                        PathNode.LineTo(540.1f, 295.1f),
                        PathNode.QuadTo(521.1f, 295.1f, 508.2f, 284.2f),
                        PathNode.QuadTo(495.3f, 273.3f, 495.3f, 254.3f),
                        PathNode.LineTo(495.3f, 224.3f),
                        PathNode.QuadTo(495.3f, 203.4f, 507.9f, 192.5f),
                        PathNode.QuadTo(520.6f, 181.6f, 540.1f, 181.6f),
                        PathNode.LineTo(1026.7f, 181.6f),
                        PathNode.QuadTo(1046.2f, 181.6f, 1058.9f, 192.0f),
                        PathNode.QuadTo(1071.5f, 202.4f, 1071.5f, 223.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1071.5f, 561.3f),
                        PathNode.LineTo(1071.5f, 591.3f),
                        PathNode.QuadTo(1071.5f, 612.3f, 1058.9f, 622.7f),
                        PathNode.QuadTo(1046.2f, 633.1f, 1026.7f, 633.1f),
                        PathNode.LineTo(540.1f, 633.1f),
                        PathNode.QuadTo(521.1f, 633.1f, 508.2f, 622.2f),
                        PathNode.QuadTo(495.3f, 611.3f, 495.3f, 592.3f),
                        PathNode.LineTo(495.3f, 562.3f),
                        PathNode.QuadTo(495.3f, 541.4f, 507.9f, 530.5f),
                        PathNode.QuadTo(520.6f, 519.6f, 540.1f, 519.6f),
                        PathNode.LineTo(1026.7f, 519.6f),
                        PathNode.QuadTo(1046.2f, 519.6f, 1058.9f, 530.0f),
                        PathNode.QuadTo(1071.5f, 540.4f, 1071.5f, 561.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1071.5f, 898.3f),
                        PathNode.LineTo(1071.5f, 928.3f),
                        PathNode.QuadTo(1071.5f, 949.3f, 1058.9f, 959.7f),
                        PathNode.QuadTo(1046.2f, 970.1f, 1026.7f, 970.1f),
                        PathNode.LineTo(540.1f, 970.1f),
                        PathNode.QuadTo(521.1f, 970.1f, 508.2f, 959.2f),
                        PathNode.QuadTo(495.3f, 948.3f, 495.3f, 929.3f),
                        PathNode.LineTo(495.3f, 899.3f),
                        PathNode.QuadTo(495.3f, 878.4f, 507.9f, 867.5f),
                        PathNode.QuadTo(520.6f, 856.6f, 540.1f, 856.6f),
                        PathNode.LineTo(1026.7f, 856.6f),
                        PathNode.QuadTo(1046.2f, 856.6f, 1058.9f, 867.0f),
                        PathNode.QuadTo(1071.5f, 877.4f, 1071.5f, 898.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sortDemibold!!
    }

private var _sortDemibold: ImageVector? = null
