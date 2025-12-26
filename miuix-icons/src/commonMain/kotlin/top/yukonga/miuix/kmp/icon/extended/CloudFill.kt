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

val MiuixIcons.CloudFill: ImageVector
    get() = MiuixIcons.Regular.CloudFill

val MiuixIcons.Light.CloudFill: ImageVector
    get() {
        if (_cloudfillLight != null) return _cloudfillLight!!
        _cloudfillLight = ImageVector.Builder(
            name = "CloudFill.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1332.0f,
            viewportHeight = 1332.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 1.0f, translationY = 1077.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1220.0f, 276.0f),
                        PathNode.QuadTo(1220.0f, 352.0f, 1182.5f, 415.5f),
                        PathNode.QuadTo(1145.0f, 479.0f, 1081.5f, 516.5f),
                        PathNode.QuadTo(1018.0f, 554.0f, 943.0f, 554.0f),
                        PathNode.QuadTo(865.0f, 554.0f, 799.5f, 519.5f),
                        PathNode.QuadTo(734.0f, 485.0f, 686.0f, 416.0f),
                        PathNode.QuadTo(681.0f, 408.0f, 675.0f, 407.0f),
                        PathNode.QuadTo(669.0f, 406.0f, 662.0f, 410.0f),
                        PathNode.LineTo(649.0f, 416.0f),
                        PathNode.QuadTo(642.0f, 419.0f, 640.5f, 425.5f),
                        PathNode.QuadTo(639.0f, 432.0f, 643.0f, 439.0f),
                        PathNode.QuadTo(687.0f, 505.0f, 750.0f, 547.0f),
                        PathNode.QuadTo(813.0f, 589.0f, 889.0f, 599.0f),
                        PathNode.QuadTo(879.0f, 662.0f, 841.0f, 713.0f),
                        PathNode.QuadTo(803.0f, 764.0f, 746.5f, 793.5f),
                        PathNode.QuadTo(690.0f, 823.0f, 625.0f, 823.0f),
                        PathNode.QuadTo(552.0f, 823.0f, 489.5f, 786.5f),
                        PathNode.QuadTo(427.0f, 750.0f, 390.5f, 688.0f),
                        PathNode.QuadTo(354.0f, 626.0f, 354.0f, 553.0f),
                        PathNode.QuadTo(354.0f, 547.0f, 356.0f, 527.0f),
                        PathNode.LineTo(358.0f, 507.0f),
                        PathNode.QuadTo(291.0f, 507.0f, 234.0f, 473.0f),
                        PathNode.QuadTo(177.0f, 439.0f, 143.5f, 380.5f),
                        PathNode.QuadTo(110.0f, 322.0f, 110.0f, 253.0f),
                        PathNode.QuadTo(110.0f, 185.0f, 143.0f, 126.5f),
                        PathNode.QuadTo(176.0f, 68.0f, 231.5f, 33.5f),
                        PathNode.QuadTo(287.0f, -1.0f, 352.0f, -1.0f),
                        PathNode.HorizontalTo(943.0f),
                        PathNode.QuadTo(1018.0f, -1.0f, 1081.5f, 36.5f),
                        PathNode.QuadTo(1145.0f, 74.0f, 1182.5f, 137.5f),
                        PathNode.QuadTo(1220.0f, 201.0f, 1220.0f, 276.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudfillLight!!
    }

private var _cloudfillLight: ImageVector? = null

val MiuixIcons.Regular.CloudFill: ImageVector
    get() {
        if (_cloudfillRegular != null) return _cloudfillRegular!!
        _cloudfillRegular = ImageVector.Builder(
            name = "CloudFill.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1356.0f,
            viewportHeight = 1356.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 13.0f, translationY = 1089.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1230.0f, 277.0f),
                        PathNode.QuadTo(1230.0f, 356.0f, 1191.0f, 423.0f),
                        PathNode.QuadTo(1152.0f, 490.0f, 1085.5f, 529.0f),
                        PathNode.QuadTo(1019.0f, 568.0f, 940.0f, 568.0f),
                        PathNode.QuadTo(868.0f, 568.0f, 804.0f, 533.5f),
                        PathNode.QuadTo(740.0f, 499.0f, 696.0f, 436.0f),
                        PathNode.QuadTo(688.0f, 424.0f, 680.0f, 422.0f),
                        PathNode.QuadTo(672.0f, 420.0f, 660.0f, 426.0f),
                        PathNode.LineTo(645.0f, 434.0f),
                        PathNode.QuadTo(636.0f, 439.0f, 633.0f, 448.5f),
                        PathNode.QuadTo(630.0f, 458.0f, 637.0f, 467.0f),
                        PathNode.QuadTo(682.0f, 537.0f, 749.0f, 579.5f),
                        PathNode.QuadTo(816.0f, 622.0f, 896.0f, 632.0f),
                        PathNode.QuadTo(879.0f, 691.0f, 839.0f, 737.0f),
                        PathNode.QuadTo(799.0f, 783.0f, 743.5f, 809.0f),
                        PathNode.QuadTo(688.0f, 835.0f, 625.0f, 835.0f),
                        PathNode.QuadTo(548.0f, 835.0f, 483.0f, 797.0f),
                        PathNode.QuadTo(418.0f, 759.0f, 380.0f, 694.0f),
                        PathNode.QuadTo(342.0f, 629.0f, 342.0f, 552.0f),
                        PathNode.QuadTo(342.0f, 538.0f, 343.0f, 531.0f),
                        PathNode.LineTo(344.0f, 520.0f),
                        PathNode.QuadTo(277.0f, 516.0f, 221.0f, 479.5f),
                        PathNode.QuadTo(165.0f, 443.0f, 132.5f, 383.5f),
                        PathNode.QuadTo(100.0f, 324.0f, 100.0f, 254.0f),
                        PathNode.QuadTo(100.0f, 183.0f, 134.5f, 121.5f),
                        PathNode.QuadTo(169.0f, 60.0f, 228.0f, 23.5f),
                        PathNode.QuadTo(287.0f, -13.0f, 355.0f, -13.0f),
                        PathNode.HorizontalTo(940.0f),
                        PathNode.QuadTo(1019.0f, -13.0f, 1085.5f, 26.0f),
                        PathNode.QuadTo(1152.0f, 65.0f, 1191.0f, 131.5f),
                        PathNode.QuadTo(1230.0f, 198.0f, 1230.0f, 277.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudfillRegular!!
    }

private var _cloudfillRegular: ImageVector? = null

val MiuixIcons.Heavy.CloudFill: ImageVector
    get() {
        if (_cloudfillHeavy != null) return _cloudfillHeavy!!
        _cloudfillHeavy = ImageVector.Builder(
            name = "CloudFill.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1382.3999999999999f,
            viewportHeight = 1382.3999999999999f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 26.199999999999932f, translationY = 1102.1999999999998f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1241.0f, 278.0f),
                        PathNode.QuadTo(1241.0f, 360.0f, 1201.5f, 429.0f),
                        PathNode.QuadTo(1162.0f, 498.0f, 1094.5f, 538.0f),
                        PathNode.QuadTo(1027.0f, 578.0f, 947.0f, 578.0f),
                        PathNode.QuadTo(874.0f, 577.0f, 811.0f, 543.5f),
                        PathNode.QuadTo(748.0f, 510.0f, 704.0f, 447.0f),
                        PathNode.QuadTo(694.0f, 433.0f, 684.0f, 430.5f),
                        PathNode.QuadTo(674.0f, 428.0f, 660.0f, 436.0f),
                        PathNode.LineTo(640.0f, 447.0f),
                        PathNode.QuadTo(628.0f, 455.0f, 624.5f, 467.5f),
                        PathNode.QuadTo(621.0f, 480.0f, 629.0f, 492.0f),
                        PathNode.QuadTo(676.0f, 563.0f, 745.0f, 606.5f),
                        PathNode.QuadTo(814.0f, 650.0f, 896.0f, 660.0f),
                        PathNode.QuadTo(876.0f, 715.0f, 835.5f, 757.5f),
                        PathNode.QuadTo(795.0f, 800.0f, 740.5f, 823.5f),
                        PathNode.QuadTo(686.0f, 847.0f, 625.0f, 847.0f),
                        PathNode.QuadTo(547.0f, 847.0f, 480.5f, 809.0f),
                        PathNode.QuadTo(414.0f, 771.0f, 373.5f, 706.5f),
                        PathNode.QuadTo(333.0f, 642.0f, 330.0f, 565.0f),
                        PathNode.QuadTo(330.0f, 555.0f, 330.0f, 544.0f),
                        PathNode.VerticalTo(533.0f),
                        PathNode.QuadTo(263.0f, 526.0f, 208.0f, 487.0f),
                        PathNode.QuadTo(153.0f, 448.0f, 121.0f, 387.0f),
                        PathNode.QuadTo(89.0f, 326.0f, 89.0f, 255.0f),
                        PathNode.QuadTo(89.0f, 180.0f, 125.5f, 115.5f),
                        PathNode.QuadTo(162.0f, 51.0f, 224.0f, 13.0f),
                        PathNode.QuadTo(286.0f, -25.0f, 358.0f, -25.0f),
                        PathNode.HorizontalTo(938.0f),
                        PathNode.QuadTo(1020.0f, -25.0f, 1089.5f, 16.0f),
                        PathNode.QuadTo(1159.0f, 57.0f, 1200.0f, 126.5f),
                        PathNode.QuadTo(1241.0f, 196.0f, 1241.0f, 278.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudfillHeavy!!
    }

private var _cloudfillHeavy: ImageVector? = null
