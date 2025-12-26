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
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -90.20000000000005f, translationY = 937.0391304347826f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(368.0f, 758.0f),
                        PathNode.HorizontalTo(346.0f),
                        PathNode.QuadTo(336.0f, 758.0f, 332.0f, 752.0f),
                        PathNode.QuadTo(328.0f, 746.0f, 328.0f, 737.0f),
                        PathNode.VerticalTo(66.0f),
                        PathNode.LineTo(233.0f, 160.0f),
                        PathNode.QuadTo(227.0f, 166.0f, 220.5f, 165.5f),
                        PathNode.QuadTo(214.0f, 165.0f, 209.0f, 160.0f),
                        PathNode.LineTo(192.0f, 143.0f),
                        PathNode.QuadTo(186.0f, 137.0f, 186.0f, 131.0f),
                        PathNode.QuadTo(186.0f, 125.0f, 191.0f, 119.0f),
                        PathNode.LineTo(333.0f, -23.0f),
                        PathNode.QuadTo(344.0f, -34.0f, 357.5f, -33.5f),
                        PathNode.QuadTo(371.0f, -33.0f, 382.0f, -22.0f),
                        PathNode.LineTo(522.0f, 118.0f),
                        PathNode.QuadTo(528.0f, 124.0f, 528.0f, 130.5f),
                        PathNode.QuadTo(528.0f, 137.0f, 521.0f, 143.0f),
                        PathNode.LineTo(505.0f, 160.0f),
                        PathNode.QuadTo(499.0f, 165.0f, 492.5f, 165.0f),
                        PathNode.QuadTo(486.0f, 165.0f, 481.0f, 160.0f),
                        PathNode.LineTo(386.0f, 66.0f),
                        PathNode.VerticalTo(737.0f),
                        PathNode.QuadTo(386.0f, 746.0f, 381.5f, 752.0f),
                        PathNode.QuadTo(377.0f, 758.0f, 368.0f, 758.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1144.0f, 14.0f),
                        PathNode.VerticalTo(36.0f),
                        PathNode.QuadTo(1144.0f, 46.0f, 1138.0f, 50.5f),
                        PathNode.QuadTo(1132.0f, 55.0f, 1124.0f, 55.0f),
                        PathNode.HorizontalTo(598.0f),
                        PathNode.QuadTo(590.0f, 55.0f, 584.0f, 50.0f),
                        PathNode.QuadTo(578.0f, 45.0f, 578.0f, 37.0f),
                        PathNode.VerticalTo(15.0f),
                        PathNode.QuadTo(578.0f, 5.0f, 583.5f, 0.0f),
                        PathNode.QuadTo(589.0f, -5.0f, 598.0f, -5.0f),
                        PathNode.HorizontalTo(1124.0f),
                        PathNode.QuadTo(1132.0f, -5.0f, 1138.0f, -0.5f),
                        PathNode.QuadTo(1144.0f, 4.0f, 1144.0f, 14.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1144.0f, 352.0f),
                        PathNode.VerticalTo(374.0f),
                        PathNode.QuadTo(1144.0f, 384.0f, 1138.0f, 388.5f),
                        PathNode.QuadTo(1132.0f, 393.0f, 1124.0f, 393.0f),
                        PathNode.HorizontalTo(598.0f),
                        PathNode.QuadTo(590.0f, 393.0f, 584.0f, 388.0f),
                        PathNode.QuadTo(578.0f, 383.0f, 578.0f, 375.0f),
                        PathNode.VerticalTo(353.0f),
                        PathNode.QuadTo(578.0f, 343.0f, 583.5f, 338.0f),
                        PathNode.QuadTo(589.0f, 333.0f, 598.0f, 333.0f),
                        PathNode.HorizontalTo(1124.0f),
                        PathNode.QuadTo(1132.0f, 333.0f, 1138.0f, 337.5f),
                        PathNode.QuadTo(1144.0f, 342.0f, 1144.0f, 352.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1144.0f, 689.0f),
                        PathNode.VerticalTo(711.0f),
                        PathNode.QuadTo(1144.0f, 721.0f, 1138.0f, 725.5f),
                        PathNode.QuadTo(1132.0f, 730.0f, 1124.0f, 730.0f),
                        PathNode.HorizontalTo(598.0f),
                        PathNode.QuadTo(590.0f, 730.0f, 584.0f, 725.0f),
                        PathNode.QuadTo(578.0f, 720.0f, 578.0f, 712.0f),
                        PathNode.VerticalTo(690.0f),
                        PathNode.QuadTo(578.0f, 680.0f, 583.5f, 675.0f),
                        PathNode.QuadTo(589.0f, 670.0f, 598.0f, 670.0f),
                        PathNode.HorizontalTo(1124.0f),
                        PathNode.QuadTo(1132.0f, 670.0f, 1138.0f, 674.5f),
                        PathNode.QuadTo(1144.0f, 679.0f, 1144.0f, 689.0f),
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

val MiuixIcons.Regular.Sort: ImageVector
    get() {
        if (_sortRegular != null) return _sortRegular!!
        _sortRegular = ImageVector.Builder(
            name = "Sort.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1161.0315789473684f,
            viewportHeight = 1161.0315789473684f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -83.72105263157894f, translationY = 938.2571687840291f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(375.0f, 762.0f),
                        PathNode.HorizontalTo(345.0f),
                        PathNode.QuadTo(330.0f, 762.0f, 323.5f, 753.0f),
                        PathNode.QuadTo(317.0f, 744.0f, 317.0f, 731.0f),
                        PathNode.VerticalTo(98.0f),
                        PathNode.LineTo(249.0f, 165.0f),
                        PathNode.QuadTo(240.0f, 174.0f, 231.0f, 173.5f),
                        PathNode.QuadTo(222.0f, 173.0f, 215.0f, 166.0f),
                        PathNode.LineTo(189.0f, 140.0f),
                        PathNode.QuadTo(180.0f, 131.0f, 180.5f, 122.0f),
                        PathNode.QuadTo(181.0f, 113.0f, 188.0f, 106.0f),
                        PathNode.LineTo(327.0f, -33.0f),
                        PathNode.QuadTo(341.0f, -47.0f, 360.0f, -46.5f),
                        PathNode.QuadTo(379.0f, -46.0f, 394.0f, -31.0f),
                        PathNode.LineTo(530.0f, 105.0f),
                        PathNode.QuadTo(539.0f, 114.0f, 539.0f, 123.0f),
                        PathNode.QuadTo(539.0f, 132.0f, 529.0f, 141.0f),
                        PathNode.LineTo(505.0f, 165.0f),
                        PathNode.QuadTo(497.0f, 173.0f, 488.0f, 173.5f),
                        PathNode.QuadTo(479.0f, 174.0f, 471.0f, 166.0f),
                        PathNode.LineTo(402.0f, 98.0f),
                        PathNode.VerticalTo(731.0f),
                        PathNode.QuadTo(402.0f, 744.0f, 395.5f, 753.0f),
                        PathNode.QuadTo(389.0f, 762.0f, 375.0f, 762.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1148.0f, 10.0f),
                        PathNode.VerticalTo(40.0f),
                        PathNode.QuadTo(1148.0f, 55.0f, 1139.5f, 61.5f),
                        PathNode.QuadTo(1131.0f, 68.0f, 1117.0f, 68.0f),
                        PathNode.HorizontalTo(612.0f),
                        PathNode.QuadTo(599.0f, 68.0f, 590.0f, 61.0f),
                        PathNode.QuadTo(581.0f, 54.0f, 581.0f, 41.0f),
                        PathNode.VerticalTo(11.0f),
                        PathNode.QuadTo(581.0f, -4.0f, 589.5f, -11.0f),
                        PathNode.QuadTo(598.0f, -18.0f, 612.0f, -18.0f),
                        PathNode.HorizontalTo(1117.0f),
                        PathNode.QuadTo(1131.0f, -18.0f, 1139.5f, -11.5f),
                        PathNode.QuadTo(1148.0f, -5.0f, 1148.0f, 10.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1148.0f, 348.0f),
                        PathNode.VerticalTo(378.0f),
                        PathNode.QuadTo(1148.0f, 393.0f, 1139.5f, 399.5f),
                        PathNode.QuadTo(1131.0f, 406.0f, 1117.0f, 406.0f),
                        PathNode.HorizontalTo(612.0f),
                        PathNode.QuadTo(599.0f, 406.0f, 590.0f, 399.0f),
                        PathNode.QuadTo(581.0f, 392.0f, 581.0f, 379.0f),
                        PathNode.VerticalTo(349.0f),
                        PathNode.QuadTo(581.0f, 334.0f, 589.5f, 327.0f),
                        PathNode.QuadTo(598.0f, 320.0f, 612.0f, 320.0f),
                        PathNode.HorizontalTo(1117.0f),
                        PathNode.QuadTo(1131.0f, 320.0f, 1139.5f, 326.5f),
                        PathNode.QuadTo(1148.0f, 333.0f, 1148.0f, 348.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1148.0f, 685.0f),
                        PathNode.VerticalTo(715.0f),
                        PathNode.QuadTo(1148.0f, 730.0f, 1139.5f, 736.5f),
                        PathNode.QuadTo(1131.0f, 743.0f, 1117.0f, 743.0f),
                        PathNode.HorizontalTo(612.0f),
                        PathNode.QuadTo(599.0f, 743.0f, 590.0f, 736.0f),
                        PathNode.QuadTo(581.0f, 729.0f, 581.0f, 716.0f),
                        PathNode.VerticalTo(686.0f),
                        PathNode.QuadTo(581.0f, 671.0f, 589.5f, 664.0f),
                        PathNode.QuadTo(598.0f, 657.0f, 612.0f, 657.0f),
                        PathNode.HorizontalTo(1117.0f),
                        PathNode.QuadTo(1131.0f, 657.0f, 1139.5f, 663.5f),
                        PathNode.QuadTo(1148.0f, 670.0f, 1148.0f, 685.0f),
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

val MiuixIcons.Heavy.Sort: ImageVector
    get() {
        if (_sortHeavy != null) return _sortHeavy!!
        _sortHeavy = ImageVector.Builder(
            name = "Sort.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1200.0f,
            viewportHeight = 1200.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -65.0f, translationY = 958.2428571428571f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(376.0f, 774.0f),
                        PathNode.HorizontalTo(346.0f),
                        PathNode.QuadTo(326.0f, 774.0f, 316.0f, 761.0f),
                        PathNode.QuadTo(306.0f, 748.0f, 306.0f, 731.0f),
                        PathNode.VerticalTo(123.0f),
                        PathNode.LineTo(253.0f, 176.0f),
                        PathNode.QuadTo(241.0f, 188.0f, 227.0f, 188.0f),
                        PathNode.QuadTo(213.0f, 188.0f, 203.0f, 177.0f),
                        PathNode.LineTo(177.0f, 151.0f),
                        PathNode.QuadTo(165.0f, 139.0f, 165.0f, 125.0f),
                        PathNode.QuadTo(165.0f, 111.0f, 176.0f, 100.0f),
                        PathNode.LineTo(320.0f, -41.0f),
                        PathNode.QuadTo(337.0f, -58.0f, 360.5f, -57.5f),
                        PathNode.QuadTo(384.0f, -57.0f, 403.0f, -39.0f),
                        PathNode.LineTo(544.0f, 99.0f),
                        PathNode.QuadTo(556.0f, 111.0f, 556.0f, 125.0f),
                        PathNode.QuadTo(556.0f, 139.0f, 543.0f, 152.0f),
                        PathNode.LineTo(519.0f, 176.0f),
                        PathNode.QuadTo(508.0f, 188.0f, 493.5f, 188.0f),
                        PathNode.QuadTo(479.0f, 188.0f, 468.0f, 177.0f),
                        PathNode.LineTo(415.0f, 123.0f),
                        PathNode.VerticalTo(731.0f),
                        PathNode.QuadTo(415.0f, 750.0f, 405.0f, 762.0f),
                        PathNode.QuadTo(395.0f, 774.0f, 376.0f, 774.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1165.0f, 10.0f),
                        PathNode.VerticalTo(40.0f),
                        PathNode.QuadTo(1165.0f, 61.0f, 1152.0f, 71.5f),
                        PathNode.QuadTo(1139.0f, 82.0f, 1120.0f, 82.0f),
                        PathNode.HorizontalTo(633.0f),
                        PathNode.QuadTo(614.0f, 82.0f, 601.0f, 71.0f),
                        PathNode.QuadTo(588.0f, 60.0f, 588.0f, 41.0f),
                        PathNode.VerticalTo(11.0f),
                        PathNode.QuadTo(588.0f, -10.0f, 600.5f, -21.0f),
                        PathNode.QuadTo(613.0f, -32.0f, 633.0f, -32.0f),
                        PathNode.HorizontalTo(1120.0f),
                        PathNode.QuadTo(1139.0f, -32.0f, 1152.0f, -21.5f),
                        PathNode.QuadTo(1165.0f, -11.0f, 1165.0f, 10.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1165.0f, 348.0f),
                        PathNode.VerticalTo(378.0f),
                        PathNode.QuadTo(1165.0f, 399.0f, 1152.0f, 409.5f),
                        PathNode.QuadTo(1139.0f, 420.0f, 1120.0f, 420.0f),
                        PathNode.HorizontalTo(633.0f),
                        PathNode.QuadTo(614.0f, 420.0f, 601.0f, 409.0f),
                        PathNode.QuadTo(588.0f, 398.0f, 588.0f, 379.0f),
                        PathNode.VerticalTo(349.0f),
                        PathNode.QuadTo(588.0f, 328.0f, 600.5f, 317.0f),
                        PathNode.QuadTo(613.0f, 306.0f, 633.0f, 306.0f),
                        PathNode.HorizontalTo(1120.0f),
                        PathNode.QuadTo(1139.0f, 306.0f, 1152.0f, 316.5f),
                        PathNode.QuadTo(1165.0f, 327.0f, 1165.0f, 348.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1165.0f, 685.0f),
                        PathNode.VerticalTo(715.0f),
                        PathNode.QuadTo(1165.0f, 736.0f, 1152.0f, 746.5f),
                        PathNode.QuadTo(1139.0f, 757.0f, 1120.0f, 757.0f),
                        PathNode.HorizontalTo(633.0f),
                        PathNode.QuadTo(614.0f, 757.0f, 601.0f, 746.0f),
                        PathNode.QuadTo(588.0f, 735.0f, 588.0f, 716.0f),
                        PathNode.VerticalTo(686.0f),
                        PathNode.QuadTo(588.0f, 665.0f, 600.5f, 654.0f),
                        PathNode.QuadTo(613.0f, 643.0f, 633.0f, 643.0f),
                        PathNode.HorizontalTo(1120.0f),
                        PathNode.QuadTo(1139.0f, 643.0f, 1152.0f, 653.5f),
                        PathNode.QuadTo(1165.0f, 664.0f, 1165.0f, 685.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sortHeavy!!
    }

private var _sortHeavy: ImageVector? = null
