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

val MiuixIcons.Sort: ImageVector
    get() = MiuixIcons.Regular.Sort

val MiuixIcons.Light.Sort: ImageVector
    get() {
        if (_sortLight != null) return _sortLight!!
        _sortLight = ImageVector.Builder(
            name = "Sort.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1149.6f,
            viewportHeight = 1149.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1149.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(277.8f, 970.8f),
                        PathNode.HorizontalTo(255.8f),
                        PathNode.QuadTo(245.8f, 970.8f, 241.8f, 964.8f),
                        PathNode.QuadTo(237.8f, 958.8f, 237.8f, 949.8f),
                        PathNode.VerticalTo(278.8f),
                        PathNode.LineTo(142.8f, 372.8f),
                        PathNode.QuadTo(136.8f, 378.8f, 130.3f, 378.3f),
                        PathNode.QuadTo(123.8f, 377.8f, 118.8f, 372.8f),
                        PathNode.LineTo(101.8f, 355.8f),
                        PathNode.QuadTo(95.8f, 349.8f, 95.8f, 343.8f),
                        PathNode.QuadTo(95.8f, 337.8f, 100.8f, 331.8f),
                        PathNode.LineTo(242.8f, 189.8f),
                        PathNode.QuadTo(253.8f, 178.8f, 267.3f, 179.3f),
                        PathNode.QuadTo(280.8f, 179.8f, 291.8f, 190.8f),
                        PathNode.LineTo(431.8f, 330.8f),
                        PathNode.QuadTo(437.8f, 336.8f, 437.8f, 343.3f),
                        PathNode.QuadTo(437.8f, 349.8f, 430.8f, 355.8f),
                        PathNode.LineTo(414.8f, 372.8f),
                        PathNode.QuadTo(408.8f, 377.8f, 402.3f, 377.8f),
                        PathNode.QuadTo(395.8f, 377.8f, 390.8f, 372.8f),
                        PathNode.LineTo(295.8f, 278.8f),
                        PathNode.VerticalTo(949.8f),
                        PathNode.QuadTo(295.8f, 958.8f, 291.3f, 964.8f),
                        PathNode.QuadTo(286.8f, 970.8f, 277.8f, 970.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1053.8f, 226.8f),
                        PathNode.VerticalTo(248.8f),
                        PathNode.QuadTo(1053.8f, 258.8f, 1047.8f, 263.3f),
                        PathNode.QuadTo(1041.8f, 267.8f, 1033.8f, 267.8f),
                        PathNode.HorizontalTo(507.8f),
                        PathNode.QuadTo(499.8f, 267.8f, 493.8f, 262.8f),
                        PathNode.QuadTo(487.8f, 257.8f, 487.8f, 249.8f),
                        PathNode.VerticalTo(227.8f),
                        PathNode.QuadTo(487.8f, 217.8f, 493.3f, 212.8f),
                        PathNode.QuadTo(498.8f, 207.8f, 507.8f, 207.8f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1041.8f, 207.8f, 1047.8f, 212.3f),
                        PathNode.QuadTo(1053.8f, 216.8f, 1053.8f, 226.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1053.8f, 564.8f),
                        PathNode.VerticalTo(586.8f),
                        PathNode.QuadTo(1053.8f, 596.8f, 1047.8f, 601.3f),
                        PathNode.QuadTo(1041.8f, 605.8f, 1033.8f, 605.8f),
                        PathNode.HorizontalTo(507.8f),
                        PathNode.QuadTo(499.8f, 605.8f, 493.8f, 600.8f),
                        PathNode.QuadTo(487.8f, 595.8f, 487.8f, 587.8f),
                        PathNode.VerticalTo(565.8f),
                        PathNode.QuadTo(487.8f, 555.8f, 493.3f, 550.8f),
                        PathNode.QuadTo(498.8f, 545.8f, 507.8f, 545.8f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1041.8f, 545.8f, 1047.8f, 550.3f),
                        PathNode.QuadTo(1053.8f, 554.8f, 1053.8f, 564.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1053.8f, 901.8f),
                        PathNode.VerticalTo(923.8f),
                        PathNode.QuadTo(1053.8f, 933.8f, 1047.8f, 938.3f),
                        PathNode.QuadTo(1041.8f, 942.8f, 1033.8f, 942.8f),
                        PathNode.HorizontalTo(507.8f),
                        PathNode.QuadTo(499.8f, 942.8f, 493.8f, 937.8f),
                        PathNode.QuadTo(487.8f, 932.8f, 487.8f, 924.8f),
                        PathNode.VerticalTo(902.8f),
                        PathNode.QuadTo(487.8f, 892.8f, 493.3f, 887.8f),
                        PathNode.QuadTo(498.8f, 882.8f, 507.8f, 882.8f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1041.8f, 882.8f, 1047.8f, 887.3f),
                        PathNode.QuadTo(1053.8f, 891.8f, 1053.8f, 901.8f),
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

val MiuixIcons.Normal.Sort: ImageVector
    get() {
        if (_sortNormal != null) return _sortNormal!!
        _sortNormal = ImageVector.Builder(
            name = "Sort.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1157.8f,
            viewportHeight = 1157.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1157.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(287.4f, 980.8f),
                        PathNode.HorizontalTo(259.9f),
                        PathNode.QuadTo(246.5f, 980.8f, 240.8f, 972.7f),
                        PathNode.QuadTo(235.0f, 964.6f, 235.0f, 952.9f),
                        PathNode.VerticalTo(308.0f),
                        PathNode.LineTo(158.6f, 383.5f),
                        PathNode.QuadTo(150.5f, 391.5f, 142.3f, 391.0f),
                        PathNode.QuadTo(134.1f, 390.5f, 127.7f, 384.1f),
                        PathNode.LineTo(104.5f, 361.0f),
                        PathNode.QuadTo(96.5f, 352.9f, 96.8f, 344.8f),
                        PathNode.QuadTo(97.2f, 336.8f, 103.5f, 330.1f),
                        PathNode.LineTo(243.5f, 190.1f),
                        PathNode.QuadTo(256.5f, 177.1f, 273.8f, 177.6f),
                        PathNode.QuadTo(291.1f, 178.1f, 304.9f, 191.8f),
                        PathNode.LineTo(442.1f, 329.1f),
                        PathNode.QuadTo(450.2f, 337.1f, 450.2f, 345.4f),
                        PathNode.QuadTo(450.2f, 353.6f, 441.1f, 361.6f),
                        PathNode.LineTo(419.6f, 383.5f),
                        PathNode.QuadTo(412.2f, 390.5f, 404.0f, 390.9f),
                        PathNode.QuadTo(395.8f, 391.2f, 388.7f, 384.1f),
                        PathNode.LineTo(311.6f, 308.0f),
                        PathNode.VerticalTo(952.9f),
                        PathNode.QuadTo(311.6f, 964.6f, 305.7f, 972.7f),
                        PathNode.QuadTo(299.9f, 980.8f, 287.4f, 980.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1061.4f, 231.3f),
                        PathNode.VerticalTo(258.8f),
                        PathNode.QuadTo(1061.4f, 272.2f, 1053.6f, 278.1f),
                        PathNode.QuadTo(1045.9f, 284.0f, 1033.8f, 284.0f),
                        PathNode.HorizontalTo(522.2f),
                        PathNode.QuadTo(510.8f, 284.0f, 502.7f, 277.6f),
                        PathNode.QuadTo(494.7f, 271.2f, 494.7f, 259.8f),
                        PathNode.VerticalTo(232.3f),
                        PathNode.QuadTo(494.7f, 218.8f, 502.2f, 212.5f),
                        PathNode.QuadTo(509.8f, 206.1f, 522.2f, 206.1f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1045.9f, 206.1f, 1053.6f, 212.0f),
                        PathNode.QuadTo(1061.4f, 217.8f, 1061.4f, 231.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1061.4f, 569.3f),
                        PathNode.VerticalTo(596.8f),
                        PathNode.QuadTo(1061.4f, 610.2f, 1053.6f, 616.1f),
                        PathNode.QuadTo(1045.9f, 622.0f, 1033.8f, 622.0f),
                        PathNode.HorizontalTo(522.2f),
                        PathNode.QuadTo(510.8f, 622.0f, 502.7f, 615.6f),
                        PathNode.QuadTo(494.7f, 609.2f, 494.7f, 597.8f),
                        PathNode.VerticalTo(570.3f),
                        PathNode.QuadTo(494.7f, 556.8f, 502.2f, 550.5f),
                        PathNode.QuadTo(509.8f, 544.1f, 522.2f, 544.1f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1045.9f, 544.1f, 1053.6f, 550.0f),
                        PathNode.QuadTo(1061.4f, 555.8f, 1061.4f, 569.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1061.4f, 906.3f),
                        PathNode.VerticalTo(933.8f),
                        PathNode.QuadTo(1061.4f, 947.2f, 1053.6f, 953.1f),
                        PathNode.QuadTo(1045.9f, 959.0f, 1033.8f, 959.0f),
                        PathNode.HorizontalTo(522.2f),
                        PathNode.QuadTo(510.8f, 959.0f, 502.7f, 952.6f),
                        PathNode.QuadTo(494.7f, 946.2f, 494.7f, 934.8f),
                        PathNode.VerticalTo(907.3f),
                        PathNode.QuadTo(494.7f, 893.8f, 502.2f, 887.5f),
                        PathNode.QuadTo(509.8f, 881.1f, 522.2f, 881.1f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1045.9f, 881.1f, 1053.6f, 887.0f),
                        PathNode.QuadTo(1061.4f, 892.8f, 1061.4f, 906.3f),
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

val MiuixIcons.Regular.Sort: ImageVector
    get() {
        if (_sortRegular != null) return _sortRegular!!
        _sortRegular = ImageVector.Builder(
            name = "Sort.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1161.6f,
            viewportHeight = 1161.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1161.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(291.8f, 985.3f),
                        PathNode.HorizontalTo(261.8f),
                        PathNode.QuadTo(246.8f, 985.3f, 240.3f, 976.3f),
                        PathNode.QuadTo(233.8f, 967.3f, 233.8f, 954.3f),
                        PathNode.VerticalTo(321.3f),
                        PathNode.LineTo(165.8f, 388.3f),
                        PathNode.QuadTo(156.8f, 397.3f, 147.8f, 396.8f),
                        PathNode.QuadTo(138.8f, 396.3f, 131.8f, 389.3f),
                        PathNode.LineTo(105.8f, 363.3f),
                        PathNode.QuadTo(96.8f, 354.3f, 97.3f, 345.3f),
                        PathNode.QuadTo(97.8f, 336.3f, 104.8f, 329.3f),
                        PathNode.LineTo(243.8f, 190.3f),
                        PathNode.QuadTo(257.8f, 176.3f, 276.8f, 176.8f),
                        PathNode.QuadTo(295.8f, 177.3f, 310.8f, 192.3f),
                        PathNode.LineTo(446.8f, 328.3f),
                        PathNode.QuadTo(455.8f, 337.3f, 455.8f, 346.3f),
                        PathNode.QuadTo(455.8f, 355.3f, 445.8f, 364.3f),
                        PathNode.LineTo(421.8f, 388.3f),
                        PathNode.QuadTo(413.8f, 396.3f, 404.8f, 396.8f),
                        PathNode.QuadTo(395.8f, 397.3f, 387.8f, 389.3f),
                        PathNode.LineTo(318.8f, 321.3f),
                        PathNode.VerticalTo(954.3f),
                        PathNode.QuadTo(318.8f, 967.3f, 312.3f, 976.3f),
                        PathNode.QuadTo(305.8f, 985.3f, 291.8f, 985.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1064.8f, 233.3f),
                        PathNode.VerticalTo(263.3f),
                        PathNode.QuadTo(1064.8f, 278.3f, 1056.3f, 284.8f),
                        PathNode.QuadTo(1047.8f, 291.3f, 1033.8f, 291.3f),
                        PathNode.HorizontalTo(528.8f),
                        PathNode.QuadTo(515.8f, 291.3f, 506.8f, 284.3f),
                        PathNode.QuadTo(497.8f, 277.3f, 497.8f, 264.3f),
                        PathNode.VerticalTo(234.3f),
                        PathNode.QuadTo(497.8f, 219.3f, 506.3f, 212.3f),
                        PathNode.QuadTo(514.8f, 205.3f, 528.8f, 205.3f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1047.8f, 205.3f, 1056.3f, 211.8f),
                        PathNode.QuadTo(1064.8f, 218.3f, 1064.8f, 233.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1064.8f, 571.3f),
                        PathNode.VerticalTo(601.3f),
                        PathNode.QuadTo(1064.8f, 616.3f, 1056.3f, 622.8f),
                        PathNode.QuadTo(1047.8f, 629.3f, 1033.8f, 629.3f),
                        PathNode.HorizontalTo(528.8f),
                        PathNode.QuadTo(515.8f, 629.3f, 506.8f, 622.3f),
                        PathNode.QuadTo(497.8f, 615.3f, 497.8f, 602.3f),
                        PathNode.VerticalTo(572.3f),
                        PathNode.QuadTo(497.8f, 557.3f, 506.3f, 550.3f),
                        PathNode.QuadTo(514.8f, 543.3f, 528.8f, 543.3f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1047.8f, 543.3f, 1056.3f, 549.8f),
                        PathNode.QuadTo(1064.8f, 556.3f, 1064.8f, 571.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1064.8f, 908.3f),
                        PathNode.VerticalTo(938.3f),
                        PathNode.QuadTo(1064.8f, 953.3f, 1056.3f, 959.8f),
                        PathNode.QuadTo(1047.8f, 966.3f, 1033.8f, 966.3f),
                        PathNode.HorizontalTo(528.8f),
                        PathNode.QuadTo(515.8f, 966.3f, 506.8f, 959.3f),
                        PathNode.QuadTo(497.8f, 952.3f, 497.8f, 939.3f),
                        PathNode.VerticalTo(909.3f),
                        PathNode.QuadTo(497.8f, 894.3f, 506.3f, 887.3f),
                        PathNode.QuadTo(514.8f, 880.3f, 528.8f, 880.3f),
                        PathNode.HorizontalTo(1033.8f),
                        PathNode.QuadTo(1047.8f, 880.3f, 1056.3f, 886.8f),
                        PathNode.QuadTo(1064.8f, 893.3f, 1064.8f, 908.3f),
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

val MiuixIcons.Medium.Sort: ImageVector
    get() {
        if (_sortMedium != null) return _sortMedium!!
        _sortMedium = ImageVector.Builder(
            name = "Sort.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1184.2f,
            viewportHeight = 1184.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1184.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(303.1f, 1003.4f),
                        PathNode.HorizontalTo(273.1f),
                        PathNode.QuadTo(255.2f, 1003.4f, 246.6f, 992.0f),
                        PathNode.QuadTo(238.0f, 980.7f, 238.0f, 965.3f),
                        PathNode.VerticalTo(347.0f),
                        PathNode.LineTo(178.9f, 405.8f),
                        PathNode.QuadTo(168.1f, 416.5f, 156.2f, 416.3f),
                        PathNode.QuadTo(144.2f, 416.1f, 135.4f, 406.8f),
                        PathNode.LineTo(109.4f, 380.8f),
                        PathNode.QuadTo(98.7f, 370.0f, 98.9f, 358.1f),
                        PathNode.QuadTo(99.1f, 346.1f, 108.4f, 336.8f),
                        PathNode.LineTo(250.4f, 196.6f),
                        PathNode.QuadTo(266.2f, 180.8f, 287.8f, 181.3f),
                        PathNode.QuadTo(309.4f, 181.8f, 326.8f, 198.6f),
                        PathNode.LineTo(465.7f, 335.8f),
                        PathNode.QuadTo(476.5f, 346.5f, 476.5f, 358.5f),
                        PathNode.QuadTo(476.5f, 370.4f, 464.7f, 381.8f),
                        PathNode.LineTo(440.7f, 405.8f),
                        PathNode.QuadTo(431.0f, 416.1f, 418.7f, 416.3f),
                        PathNode.QuadTo(406.5f, 416.5f, 396.7f, 406.8f),
                        PathNode.LineTo(337.2f, 347.0f),
                        PathNode.VerticalTo(965.3f),
                        PathNode.QuadTo(337.2f, 981.8f, 328.6f, 992.6f),
                        PathNode.QuadTo(320.0f, 1003.4f, 303.1f, 1003.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1085.5f, 244.3f),
                        PathNode.VerticalTo(274.3f),
                        PathNode.QuadTo(1085.5f, 292.8f, 1074.4f, 301.7f),
                        PathNode.QuadTo(1063.2f, 310.5f, 1046.3f, 310.5f),
                        PathNode.HorizontalTo(551.9f),
                        PathNode.QuadTo(535.3f, 310.5f, 524.0f, 301.2f),
                        PathNode.QuadTo(512.6f, 291.8f, 512.6f, 275.3f),
                        PathNode.VerticalTo(245.3f),
                        PathNode.QuadTo(512.6f, 226.8f, 523.5f, 217.4f),
                        PathNode.QuadTo(534.3f, 208.1f, 551.9f, 208.1f),
                        PathNode.HorizontalTo(1046.3f),
                        PathNode.QuadTo(1063.2f, 208.1f, 1074.4f, 216.9f),
                        PathNode.QuadTo(1085.5f, 225.8f, 1085.5f, 244.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1085.5f, 582.3f),
                        PathNode.VerticalTo(612.3f),
                        PathNode.QuadTo(1085.5f, 630.8f, 1074.4f, 639.7f),
                        PathNode.QuadTo(1063.2f, 648.5f, 1046.3f, 648.5f),
                        PathNode.HorizontalTo(551.9f),
                        PathNode.QuadTo(535.3f, 648.5f, 524.0f, 639.2f),
                        PathNode.QuadTo(512.6f, 629.8f, 512.6f, 613.3f),
                        PathNode.VerticalTo(583.3f),
                        PathNode.QuadTo(512.6f, 564.8f, 523.5f, 555.4f),
                        PathNode.QuadTo(534.3f, 546.1f, 551.9f, 546.1f),
                        PathNode.HorizontalTo(1046.3f),
                        PathNode.QuadTo(1063.2f, 546.1f, 1074.4f, 554.9f),
                        PathNode.QuadTo(1085.5f, 563.8f, 1085.5f, 582.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1085.5f, 919.3f),
                        PathNode.VerticalTo(949.3f),
                        PathNode.QuadTo(1085.5f, 967.8f, 1074.4f, 976.7f),
                        PathNode.QuadTo(1063.2f, 985.5f, 1046.3f, 985.5f),
                        PathNode.HorizontalTo(551.9f),
                        PathNode.QuadTo(535.3f, 985.5f, 524.0f, 976.2f),
                        PathNode.QuadTo(512.6f, 966.8f, 512.6f, 950.3f),
                        PathNode.VerticalTo(920.3f),
                        PathNode.QuadTo(512.6f, 901.8f, 523.5f, 892.4f),
                        PathNode.QuadTo(534.3f, 883.1f, 551.9f, 883.1f),
                        PathNode.HorizontalTo(1046.3f),
                        PathNode.QuadTo(1063.2f, 883.1f, 1074.4f, 891.9f),
                        PathNode.QuadTo(1085.5f, 900.8f, 1085.5f, 919.3f),
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

val MiuixIcons.Demibold.Sort: ImageVector
    get() {
        if (_sortDemibold != null) return _sortDemibold!!
        _sortDemibold = ImageVector.Builder(
            name = "Sort.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1200.0f,
            viewportHeight = 1200.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1200.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(311.0f, 1016.0f),
                        PathNode.HorizontalTo(281.0f),
                        PathNode.QuadTo(261.0f, 1016.0f, 251.0f, 1003.0f),
                        PathNode.QuadTo(241.0f, 990.0f, 241.0f, 973.0f),
                        PathNode.VerticalTo(365.0f),
                        PathNode.LineTo(188.0f, 418.0f),
                        PathNode.QuadTo(176.0f, 430.0f, 162.0f, 430.0f),
                        PathNode.QuadTo(148.0f, 430.0f, 138.0f, 419.0f),
                        PathNode.LineTo(112.0f, 393.0f),
                        PathNode.QuadTo(100.0f, 381.0f, 100.0f, 367.0f),
                        PathNode.QuadTo(100.0f, 353.0f, 111.0f, 342.0f),
                        PathNode.LineTo(255.0f, 201.0f),
                        PathNode.QuadTo(272.0f, 184.0f, 295.5f, 184.5f),
                        PathNode.QuadTo(319.0f, 185.0f, 338.0f, 203.0f),
                        PathNode.LineTo(479.0f, 341.0f),
                        PathNode.QuadTo(491.0f, 353.0f, 491.0f, 367.0f),
                        PathNode.QuadTo(491.0f, 381.0f, 478.0f, 394.0f),
                        PathNode.LineTo(454.0f, 418.0f),
                        PathNode.QuadTo(443.0f, 430.0f, 428.5f, 430.0f),
                        PathNode.QuadTo(414.0f, 430.0f, 403.0f, 419.0f),
                        PathNode.LineTo(350.0f, 365.0f),
                        PathNode.VerticalTo(973.0f),
                        PathNode.QuadTo(350.0f, 992.0f, 340.0f, 1004.0f),
                        PathNode.QuadTo(330.0f, 1016.0f, 311.0f, 1016.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1100.0f, 252.0f),
                        PathNode.VerticalTo(282.0f),
                        PathNode.QuadTo(1100.0f, 303.0f, 1087.0f, 313.5f),
                        PathNode.QuadTo(1074.0f, 324.0f, 1055.0f, 324.0f),
                        PathNode.HorizontalTo(568.0f),
                        PathNode.QuadTo(549.0f, 324.0f, 536.0f, 313.0f),
                        PathNode.QuadTo(523.0f, 302.0f, 523.0f, 283.0f),
                        PathNode.VerticalTo(253.0f),
                        PathNode.QuadTo(523.0f, 232.0f, 535.5f, 221.0f),
                        PathNode.QuadTo(548.0f, 210.0f, 568.0f, 210.0f),
                        PathNode.HorizontalTo(1055.0f),
                        PathNode.QuadTo(1074.0f, 210.0f, 1087.0f, 220.5f),
                        PathNode.QuadTo(1100.0f, 231.0f, 1100.0f, 252.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1100.0f, 590.0f),
                        PathNode.VerticalTo(620.0f),
                        PathNode.QuadTo(1100.0f, 641.0f, 1087.0f, 651.5f),
                        PathNode.QuadTo(1074.0f, 662.0f, 1055.0f, 662.0f),
                        PathNode.HorizontalTo(568.0f),
                        PathNode.QuadTo(549.0f, 662.0f, 536.0f, 651.0f),
                        PathNode.QuadTo(523.0f, 640.0f, 523.0f, 621.0f),
                        PathNode.VerticalTo(591.0f),
                        PathNode.QuadTo(523.0f, 570.0f, 535.5f, 559.0f),
                        PathNode.QuadTo(548.0f, 548.0f, 568.0f, 548.0f),
                        PathNode.HorizontalTo(1055.0f),
                        PathNode.QuadTo(1074.0f, 548.0f, 1087.0f, 558.5f),
                        PathNode.QuadTo(1100.0f, 569.0f, 1100.0f, 590.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1100.0f, 927.0f),
                        PathNode.VerticalTo(957.0f),
                        PathNode.QuadTo(1100.0f, 978.0f, 1087.0f, 988.5f),
                        PathNode.QuadTo(1074.0f, 999.0f, 1055.0f, 999.0f),
                        PathNode.HorizontalTo(568.0f),
                        PathNode.QuadTo(549.0f, 999.0f, 536.0f, 988.0f),
                        PathNode.QuadTo(523.0f, 977.0f, 523.0f, 958.0f),
                        PathNode.VerticalTo(928.0f),
                        PathNode.QuadTo(523.0f, 907.0f, 535.5f, 896.0f),
                        PathNode.QuadTo(548.0f, 885.0f, 568.0f, 885.0f),
                        PathNode.HorizontalTo(1055.0f),
                        PathNode.QuadTo(1074.0f, 885.0f, 1087.0f, 895.5f),
                        PathNode.QuadTo(1100.0f, 906.0f, 1100.0f, 927.0f),
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
