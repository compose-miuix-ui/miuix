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

val MiuixIcons.Ok: ImageVector
    get() = MiuixIcons.Regular.Ok

val MiuixIcons.Light.Ok: ImageVector
    get() {
        if (_okLight != null) return _okLight!!
        _okLight = ImageVector.Builder(
            name = "Ok.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1243.2f,
            viewportHeight = 1243.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1243.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(425.6f, 198.1f),
                        PathNode.LineTo(107.6f, 670.1f),
                        PathNode.QuadTo(103.6f, 676.1f, 104.6f, 682.6f),
                        PathNode.QuadTo(105.6f, 689.1f, 111.6f, 693.1f),
                        PathNode.LineTo(133.6f, 708.1f),
                        PathNode.QuadTo(139.6f, 712.1f, 146.1f, 711.1f),
                        PathNode.QuadTo(152.6f, 710.1f, 157.6f, 704.1f),
                        PathNode.LineTo(453.6f, 264.1f),
                        PathNode.QuadTo(457.6f, 259.1f, 462.1f, 259.1f),
                        PathNode.QuadTo(466.6f, 259.1f, 470.6f, 264.1f),
                        PathNode.LineTo(1084.6f, 1058.1f),
                        PathNode.QuadTo(1088.6f, 1065.1f, 1096.1f, 1066.1f),
                        PathNode.QuadTo(1103.6f, 1067.1f, 1110.6f, 1062.1f),
                        PathNode.LineTo(1131.6f, 1045.1f),
                        PathNode.QuadTo(1137.6f, 1040.1f, 1138.6f, 1033.1f),
                        PathNode.QuadTo(1139.6f, 1026.1f, 1134.6f, 1020.1f),
                        PathNode.LineTo(494.6f, 195.1f),
                        PathNode.QuadTo(485.6f, 183.1f, 472.6f, 179.6f),
                        PathNode.QuadTo(459.6f, 176.1f, 447.1f, 180.6f),
                        PathNode.QuadTo(434.6f, 185.1f, 425.6f, 198.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okLight!!
    }

private var _okLight: ImageVector? = null

val MiuixIcons.Normal.Ok: ImageVector
    get() {
        if (_okNormal != null) return _okNormal!!
        _okNormal = ImageVector.Builder(
            name = "Ok.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1270.4f,
            viewportHeight = 1270.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1270.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(436.8f, 200.4f),
                        PathNode.LineTo(111.2f, 681.3f),
                        PathNode.QuadTo(105.9f, 689.4f, 107.6f, 698.6f),
                        PathNode.QuadTo(109.2f, 707.9f, 117.3f, 713.2f),
                        PathNode.LineTo(144.8f, 733.1f),
                        PathNode.QuadTo(152.9f, 738.4f, 162.5f, 736.7f),
                        PathNode.QuadTo(172.1f, 735.1f, 177.7f, 727.0f),
                        PathNode.LineTo(468.2f, 293.9f),
                        PathNode.QuadTo(471.6f, 289.6f, 476.4f, 289.2f),
                        PathNode.QuadTo(481.2f, 288.9f, 484.6f, 293.2f),
                        PathNode.LineTo(1093.7f, 1081.7f),
                        PathNode.QuadTo(1099.8f, 1090.1f, 1109.7f, 1091.4f),
                        PathNode.QuadTo(1119.6f, 1092.7f, 1128.0f, 1086.4f),
                        PathNode.LineTo(1153.8f, 1065.2f),
                        PathNode.QuadTo(1161.9f, 1058.9f, 1163.2f, 1049.1f),
                        PathNode.QuadTo(1164.6f, 1039.4f, 1158.2f, 1031.3f),
                        PathNode.LineTo(511.3f, 197.4f),
                        PathNode.QuadTo(501.6f, 185.4f, 487.6f, 181.5f),
                        PathNode.QuadTo(473.6f, 177.7f, 459.7f, 182.5f),
                        PathNode.QuadTo(445.8f, 187.4f, 436.8f, 200.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okNormal!!
    }

private var _okNormal: ImageVector? = null

val MiuixIcons.Regular.Ok: ImageVector
    get() {
        if (_okRegular != null) return _okRegular!!
        _okRegular = ImageVector.Builder(
            name = "Ok.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1282.8f,
            viewportHeight = 1282.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1282.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(441.9f, 201.4f),
                        PathNode.LineTo(112.9f, 686.4f),
                        PathNode.QuadTo(106.9f, 695.4f, 108.9f, 705.9f),
                        PathNode.QuadTo(110.9f, 716.4f, 119.9f, 722.4f),
                        PathNode.LineTo(149.9f, 744.4f),
                        PathNode.QuadTo(158.9f, 750.4f, 169.9f, 748.4f),
                        PathNode.QuadTo(180.9f, 746.4f, 186.9f, 737.4f),
                        PathNode.LineTo(474.9f, 307.4f),
                        PathNode.QuadTo(477.9f, 303.4f, 482.9f, 302.9f),
                        PathNode.QuadTo(487.9f, 302.4f, 490.9f, 306.4f),
                        PathNode.LineTo(1097.9f, 1092.4f),
                        PathNode.QuadTo(1104.9f, 1101.4f, 1115.9f, 1102.9f),
                        PathNode.QuadTo(1126.9f, 1104.4f, 1135.9f, 1097.4f),
                        PathNode.LineTo(1163.9f, 1074.4f),
                        PathNode.QuadTo(1172.9f, 1067.4f, 1174.4f, 1056.4f),
                        PathNode.QuadTo(1175.9f, 1045.4f, 1168.9f, 1036.4f),
                        PathNode.LineTo(518.9f, 198.4f),
                        PathNode.QuadTo(508.9f, 186.4f, 494.4f, 182.4f),
                        PathNode.QuadTo(479.9f, 178.4f, 465.4f, 183.4f),
                        PathNode.QuadTo(450.9f, 188.4f, 441.9f, 201.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okRegular!!
    }

private var _okRegular: ImageVector? = null

val MiuixIcons.Medium.Ok: ImageVector
    get() {
        if (_okMedium != null) return _okMedium!!
        _okMedium = ImageVector.Builder(
            name = "Ok.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1306.8f,
            viewportHeight = 1306.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1306.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(446.3f, 208.4f),
                        PathNode.LineTo(117.3f, 693.4f),
                        PathNode.QuadTo(108.9f, 705.3f, 111.5f, 719.7f),
                        PathNode.QuadTo(114.1f, 734.0f, 126.6f, 742.3f),
                        PathNode.LineTo(156.6f, 764.3f),
                        PathNode.QuadTo(168.5f, 772.7f, 183.4f, 770.1f),
                        PathNode.QuadTo(198.2f, 767.5f, 206.5f, 755.0f),
                        PathNode.LineTo(488.7f, 336.2f),
                        PathNode.QuadTo(492.3f, 331.6f, 496.7f, 331.1f),
                        PathNode.QuadTo(501.1f, 330.6f, 504.7f, 335.2f),
                        PathNode.LineTo(1102.3f, 1110.6f),
                        PathNode.QuadTo(1111.6f, 1122.5f, 1126.7f, 1124.3f),
                        PathNode.QuadTo(1141.8f, 1126.1f, 1153.8f, 1116.8f),
                        PathNode.LineTo(1181.8f, 1093.8f),
                        PathNode.QuadTo(1193.7f, 1084.4f, 1195.8f, 1069.6f),
                        PathNode.QuadTo(1197.9f, 1054.8f, 1188.5f, 1042.8f),
                        PathNode.LineTo(538.0f, 204.8f),
                        PathNode.QuadTo(526.2f, 190.5f, 508.8f, 185.6f),
                        PathNode.QuadTo(491.3f, 180.7f, 474.2f, 186.6f),
                        PathNode.QuadTo(457.0f, 192.5f, 446.3f, 208.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okMedium!!
    }

private var _okMedium: ImageVector? = null

val MiuixIcons.Demibold.Ok: ImageVector
    get() {
        if (_okDemibold != null) return _okDemibold!!
        _okDemibold = ImageVector.Builder(
            name = "Ok.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1323.6f,
            viewportHeight = 1323.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1323.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(449.3f, 213.3f),
                        PathNode.LineTo(120.3f, 698.3f),
                        PathNode.QuadTo(110.3f, 712.3f, 113.3f, 729.3f),
                        PathNode.QuadTo(116.3f, 746.3f, 131.3f, 756.3f),
                        PathNode.LineTo(161.3f, 778.3f),
                        PathNode.QuadTo(175.3f, 788.3f, 192.8f, 785.3f),
                        PathNode.QuadTo(210.3f, 782.3f, 220.3f, 767.3f),
                        PathNode.LineTo(498.3f, 356.3f),
                        PathNode.QuadTo(502.3f, 351.3f, 506.3f, 350.8f),
                        PathNode.QuadTo(510.3f, 350.3f, 514.3f, 355.3f),
                        PathNode.LineTo(1105.3f, 1123.3f),
                        PathNode.QuadTo(1116.3f, 1137.3f, 1134.3f, 1139.3f),
                        PathNode.QuadTo(1152.3f, 1141.3f, 1166.3f, 1130.3f),
                        PathNode.LineTo(1194.3f, 1107.3f),
                        PathNode.QuadTo(1208.3f, 1096.3f, 1210.8f, 1078.8f),
                        PathNode.QuadTo(1213.3f, 1061.3f, 1202.3f, 1047.3f),
                        PathNode.LineTo(551.3f, 209.3f),
                        PathNode.QuadTo(538.3f, 193.3f, 518.8f, 187.8f),
                        PathNode.QuadTo(499.3f, 182.3f, 480.3f, 188.8f),
                        PathNode.QuadTo(461.3f, 195.3f, 449.3f, 213.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _okDemibold!!
    }

private var _okDemibold: ImageVector? = null
