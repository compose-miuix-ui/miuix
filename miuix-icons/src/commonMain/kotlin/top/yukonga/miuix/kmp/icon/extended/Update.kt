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

val MiuixIcons.Update: ImageVector
    get() = MiuixIcons.Regular.Update

val MiuixIcons.Light.Update: ImageVector
    get() {
        if (_updateLight != null) return _updateLight!!
        _updateLight = ImageVector.Builder(
            name = "Update.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1194.0f,
            viewportHeight = 1194.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -67.5f, translationY = 971.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1162.0f, 373.80078125f),
                        PathNode.QuadTo(1162.0f, 509.0f, 1095.0f, 623.5f),
                        PathNode.QuadTo(1028.0f, 738.0f, 914.19921875f, 805.0f),
                        PathNode.QuadTo(800.3984375f, 872.0f, 665.19921875f, 872.0f),
                        PathNode.QuadTo(530.0f, 872.0f, 415.5f, 805.0f),
                        PathNode.QuadTo(301.0f, 738.0f, 234.0f, 623.5f),
                        PathNode.QuadTo(167.0f, 509.0f, 167.0f, 373.80078125f),
                        PathNode.QuadTo(167.0f, 238.6015625f, 234.0f, 124.80078125f),
                        PathNode.QuadTo(301.0f, 11.0f, 415.5f, -56.0f),
                        PathNode.QuadTo(530.0f, -123.0f, 665.19921875f, -123.0f),
                        PathNode.QuadTo(800.3984375f, -123.0f, 914.19921875f, -56.0f),
                        PathNode.QuadTo(1028.0f, 11.0f, 1095.0f, 124.80078125f),
                        PathNode.QuadTo(1162.0f, 238.6015625f, 1162.0f, 373.80078125f),
                        PathNode.Close,
                        PathNode.MoveTo(227.0f, 373.5093896713615f),
                        PathNode.QuadTo(227.0f, 493.0492957746479f, 286.0f, 593.5246478873239f),
                        PathNode.QuadTo(345.0f, 694.0f, 445.3215434083601f, 753.0f),
                        PathNode.QuadTo(545.6430868167203f, 812.0f, 665.0f, 812.0f),
                        PathNode.QuadTo(784.3569131832797f, 812.0f, 884.6784565916398f, 753.0f),
                        PathNode.QuadTo(985.0f, 694.0f, 1044.0f, 593.5246478873239f),
                        PathNode.QuadTo(1103.0f, 493.0492957746479f, 1103.0f, 373.5093896713615f),
                        PathNode.QuadTo(1103.0f, 255.0f, 1044.1553398058252f, 154.6693548387097f),
                        PathNode.QuadTo(985.3106796116505f, 54.33870967741936f, 884.6553398058252f, -4.83064516129032f),
                        PathNode.QuadTo(784.0f, -64.0f, 665.0f, -64.0f),
                        PathNode.QuadTo(546.0f, -64.0f, 445.34466019417476f, -4.83064516129032f),
                        PathNode.QuadTo(344.68932038834953f, 54.33870967741936f, 285.84466019417476f, 154.6693548387097f),
                        PathNode.QuadTo(227.0f, 255.0f, 227.0f, 373.5093896713615f),
                        PathNode.Close,
                        PathNode.MoveTo(695.0f, 133.0f),
                        PathNode.VerticalTo(529.0f),
                        PathNode.LineTo(846.0f, 378.0f),
                        PathNode.QuadTo(851.0f, 372.0f, 858.5f, 371.5f),
                        PathNode.QuadTo(866.0f, 371.0f, 872.0f, 378.0f),
                        PathNode.LineTo(887.125f, 393.125f),
                        PathNode.QuadTo(894.0f, 400.0f, 892.95f, 407.125f),
                        PathNode.QuadTo(891.9f, 414.25f, 887.0f, 419.0f),
                        PathNode.LineTo(691.0f, 616.0f),
                        PathNode.QuadTo(680.0f, 628.0f, 665.5f, 628.0f),
                        PathNode.QuadTo(651.0f, 628.0f, 640.0f, 617.0f),
                        PathNode.LineTo(443.0f, 419.0f),
                        PathNode.QuadTo(437.6666666666667f, 413.6666666666667f, 437.33333333333337f, 407.33333333333337f),
                        PathNode.QuadTo(437.0f, 401.0f, 443.2307692307692f, 394.0232558139535f),
                        PathNode.LineTo(457.0769230769231f, 378.6744186046512f),
                        PathNode.QuadTo(464.0f, 371.0f, 471.5f, 371.5f),
                        PathNode.QuadTo(479.0f, 372.0f, 484.0f, 378.0f),
                        PathNode.LineTo(635.0f, 529.0f),
                        PathNode.VerticalTo(133.0f),
                        PathNode.QuadTo(635.0f, 125.0f, 640.0892857142858f, 119.5f),
                        PathNode.QuadTo(645.1785714285714f, 114.0f, 654.0f, 114.0f),
                        PathNode.HorizontalTo(675.0f),
                        PathNode.QuadTo(685.0f, 114.0f, 690.0f, 119.5f),
                        PathNode.QuadTo(695.0f, 125.0f, 695.0f, 133.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _updateLight!!
    }

private var _updateLight: ImageVector? = null

val MiuixIcons.Regular.Update: ImageVector
    get() {
        if (_updateRegular != null) return _updateRegular!!
        _updateRegular = ImageVector.Builder(
            name = "Update.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.2f,
            viewportHeight = 1225.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -51.89999999999998f, translationY = 987.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1175.0f, 374.0f),
                        PathNode.QuadTo(1175.0f, 513.0f, 1106.5f, 630.0f),
                        PathNode.QuadTo(1038.0f, 747.0f, 921.0f, 816.0f),
                        PathNode.QuadTo(804.0f, 885.0f, 665.0f, 885.0f),
                        PathNode.QuadTo(526.0f, 885.0f, 409.0f, 816.0f),
                        PathNode.QuadTo(292.0f, 747.0f, 223.0f, 630.0f),
                        PathNode.QuadTo(154.0f, 513.0f, 154.0f, 374.0f),
                        PathNode.QuadTo(154.0f, 235.0f, 223.0f, 118.0f),
                        PathNode.QuadTo(292.0f, 1.0f, 409.0f, -67.5f),
                        PathNode.QuadTo(526.0f, -136.0f, 665.0f, -136.0f),
                        PathNode.QuadTo(804.0f, -136.0f, 921.0f, -67.5f),
                        PathNode.QuadTo(1038.0f, 1.0f, 1106.5f, 118.0f),
                        PathNode.QuadTo(1175.0f, 235.0f, 1175.0f, 374.0f),
                        PathNode.Close,
                        PathNode.MoveTo(240.0f, 374.0f),
                        PathNode.QuadTo(240.0f, 490.0f, 297.0f, 587.5f),
                        PathNode.QuadTo(354.0f, 685.0f, 451.5f, 742.0f),
                        PathNode.QuadTo(549.0f, 799.0f, 665.0f, 799.0f),
                        PathNode.QuadTo(781.0f, 799.0f, 878.5f, 742.0f),
                        PathNode.QuadTo(976.0f, 685.0f, 1033.0f, 587.5f),
                        PathNode.QuadTo(1090.0f, 490.0f, 1090.0f, 374.0f),
                        PathNode.QuadTo(1090.0f, 259.0f, 1033.0f, 161.5f),
                        PathNode.QuadTo(976.0f, 64.0f, 878.5f, 6.5f),
                        PathNode.QuadTo(781.0f, -51.0f, 665.0f, -51.0f),
                        PathNode.QuadTo(549.0f, -51.0f, 451.5f, 6.5f),
                        PathNode.QuadTo(354.0f, 64.0f, 297.0f, 161.5f),
                        PathNode.QuadTo(240.0f, 259.0f, 240.0f, 374.0f),
                        PathNode.Close,
                        PathNode.MoveTo(708.0f, 137.0f),
                        PathNode.VerticalTo(497.0f),
                        PathNode.LineTo(834.0f, 371.0f),
                        PathNode.QuadTo(842.0f, 363.0f, 852.5f, 361.5f),
                        PathNode.QuadTo(863.0f, 360.0f, 873.0f, 370.0f),
                        PathNode.LineTo(895.0f, 392.0f),
                        PathNode.QuadTo(905.0f, 402.0f, 903.5f, 412.5f),
                        PathNode.QuadTo(902.0f, 423.0f, 895.0f, 430.0f),
                        PathNode.LineTo(700.0f, 626.0f),
                        PathNode.QuadTo(685.0f, 641.0f, 665.5f, 641.5f),
                        PathNode.QuadTo(646.0f, 642.0f, 632.0f, 628.0f),
                        PathNode.LineTo(435.0f, 430.0f),
                        PathNode.QuadTo(427.0f, 422.0f, 426.5f, 412.5f),
                        PathNode.QuadTo(426.0f, 403.0f, 435.0f, 393.0f),
                        PathNode.LineTo(455.0f, 371.0f),
                        PathNode.QuadTo(465.0f, 360.0f, 476.0f, 361.0f),
                        PathNode.QuadTo(487.0f, 362.0f, 496.0f, 371.0f),
                        PathNode.LineTo(622.0f, 497.0f),
                        PathNode.VerticalTo(137.0f),
                        PathNode.QuadTo(622.0f, 123.0f, 629.5f, 115.0f),
                        PathNode.QuadTo(637.0f, 107.0f, 650.0f, 107.0f),
                        PathNode.HorizontalTo(678.0f),
                        PathNode.QuadTo(693.0f, 107.0f, 700.5f, 115.0f),
                        PathNode.QuadTo(708.0f, 123.0f, 708.0f, 137.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _updateRegular!!
    }

private var _updateRegular: ImageVector? = null

val MiuixIcons.Heavy.Update: ImageVector
    get() {
        if (_updateHeavy != null) return _updateHeavy!!
        _updateHeavy = ImageVector.Builder(
            name = "Update.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1258.8f,
            viewportHeight = 1258.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -35.10000000000002f, translationY = 1003.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1189.0f, 374.0f),
                        PathNode.QuadTo(1189.0f, 516.0f, 1118.5f, 636.5f),
                        PathNode.QuadTo(1048.0f, 757.0f, 927.5f, 828.0f),
                        PathNode.QuadTo(807.0f, 899.0f, 665.0f, 899.0f),
                        PathNode.QuadTo(523.0f, 899.0f, 402.5f, 828.0f),
                        PathNode.QuadTo(282.0f, 757.0f, 211.0f, 636.5f),
                        PathNode.QuadTo(140.0f, 516.0f, 140.0f, 374.0f),
                        PathNode.QuadTo(140.0f, 232.0f, 211.0f, 111.5f),
                        PathNode.QuadTo(282.0f, -9.0f, 402.5f, -79.5f),
                        PathNode.QuadTo(523.0f, -150.0f, 665.0f, -150.0f),
                        PathNode.QuadTo(807.0f, -150.0f, 927.5f, -79.5f),
                        PathNode.QuadTo(1048.0f, -9.0f, 1118.5f, 111.5f),
                        PathNode.QuadTo(1189.0f, 232.0f, 1189.0f, 374.0f),
                        PathNode.Close,
                        PathNode.MoveTo(254.0f, 374.0f),
                        PathNode.QuadTo(254.0f, 486.0f, 309.0f, 580.5f),
                        PathNode.QuadTo(364.0f, 675.0f, 458.5f, 730.0f),
                        PathNode.QuadTo(553.0f, 785.0f, 665.0f, 785.0f),
                        PathNode.QuadTo(777.0f, 785.0f, 871.5f, 730.0f),
                        PathNode.QuadTo(966.0f, 675.0f, 1021.0f, 580.5f),
                        PathNode.QuadTo(1076.0f, 486.0f, 1076.0f, 374.0f),
                        PathNode.QuadTo(1076.0f, 262.0f, 1021.0f, 168.0f),
                        PathNode.QuadTo(966.0f, 74.0f, 871.5f, 18.5f),
                        PathNode.QuadTo(777.0f, -37.0f, 665.0f, -37.0f),
                        PathNode.QuadTo(553.0f, -37.0f, 458.5f, 18.5f),
                        PathNode.QuadTo(364.0f, 74.0f, 309.0f, 168.0f),
                        PathNode.QuadTo(254.0f, 262.0f, 254.0f, 374.0f),
                        PathNode.Close,
                        PathNode.MoveTo(722.0f, 144.0f),
                        PathNode.VerticalTo(464.0f),
                        PathNode.LineTo(824.0f, 361.0f),
                        PathNode.QuadTo(836.0f, 349.0f, 852.5f, 348.0f),
                        PathNode.QuadTo(869.0f, 347.0f, 883.0f, 360.0f),
                        PathNode.LineTo(905.0f, 382.0f),
                        PathNode.QuadTo(919.0f, 397.0f, 917.5f, 412.5f),
                        PathNode.QuadTo(916.0f, 428.0f, 905.0f, 440.0f),
                        PathNode.LineTo(710.0f, 636.0f),
                        PathNode.QuadTo(691.0f, 655.0f, 665.0f, 655.0f),
                        PathNode.QuadTo(639.0f, 655.0f, 622.0f, 638.0f),
                        PathNode.LineTo(425.0f, 440.0f),
                        PathNode.QuadTo(413.0f, 428.0f, 412.5f, 412.5f),
                        PathNode.QuadTo(412.0f, 397.0f, 425.0f, 384.0f),
                        PathNode.LineTo(445.0f, 362.0f),
                        PathNode.QuadTo(459.0f, 347.0f, 476.0f, 348.0f),
                        PathNode.QuadTo(493.0f, 349.0f, 506.0f, 361.0f),
                        PathNode.LineTo(608.0f, 464.0f),
                        PathNode.VerticalTo(144.0f),
                        PathNode.QuadTo(608.0f, 124.0f, 619.5f, 112.0f),
                        PathNode.QuadTo(631.0f, 100.0f, 650.0f, 100.0f),
                        PathNode.HorizontalTo(678.0f),
                        PathNode.QuadTo(698.0f, 100.0f, 710.0f, 112.5f),
                        PathNode.QuadTo(722.0f, 125.0f, 722.0f, 144.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _updateHeavy!!
    }

private var _updateHeavy: ImageVector? = null
