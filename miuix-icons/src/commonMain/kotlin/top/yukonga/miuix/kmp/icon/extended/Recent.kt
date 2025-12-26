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

val MiuixIcons.Recent: ImageVector
    get() = MiuixIcons.Regular.Recent

val MiuixIcons.Light.Recent: ImageVector
    get() {
        if (_recentLight != null) return _recentLight!!
        _recentLight = ImageVector.Builder(
            name = "Recent.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1171.2f,
            viewportHeight = 1171.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -79.39999999999998f, translationY = 960.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1153.0f, 375.9763779527559f),
                        PathNode.QuadTo(1153.0f, 508.0f, 1087.4771653543307f, 620.1565934065934f),
                        PathNode.QuadTo(1021.9543307086615f, 732.3131868131868f, 909.9787401574804f, 797.6565934065934f),
                        PathNode.QuadTo(798.0031496062992f, 863.0f, 665.0015748031497f, 863.0f),
                        PathNode.QuadTo(532.0f, 863.0f, 420.02341597796146f, 797.5826771653543f),
                        PathNode.QuadTo(308.04683195592287f, 732.1653543307086f, 242.52341597796143f, 619.8818897637796f),
                        PathNode.QuadTo(177.0f, 507.59842519685037f, 177.0f, 375.78740157480314f),
                        PathNode.QuadTo(177.0f, 243.0f, 242.5228346456693f, 131.0164835164835f),
                        PathNode.QuadTo(308.0456692913386f, 19.032967032967033f, 420.0212598425197f, -46.98351648351648f),
                        PathNode.QuadTo(531.9968503937008f, -113.0f, 664.9984251968503f, -113.0f),
                        PathNode.QuadTo(798.0f, -113.0f, 909.9765840220387f, -46.988188976377955f),
                        PathNode.QuadTo(1021.9531680440772f, 19.023622047244096f, 1087.4765840220387f, 130.9992125984252f),
                        PathNode.QuadTo(1153.0f, 242.9748031496063f, 1153.0f, 375.9763779527559f),
                        PathNode.Close,
                        PathNode.MoveTo(641.0f, 376.0f),
                        PathNode.VerticalTo(723.0f),
                        PathNode.QuadTo(641.0f, 731.1818181818181f, 645.0f, 734.590909090909f),
                        PathNode.QuadTo(649.0f, 738.0f, 657.0f, 738.0f),
                        PathNode.HorizontalTo(673.0f),
                        PathNode.QuadTo(681.0f, 738.0f, 685.0f, 734.590909090909f),
                        PathNode.QuadTo(689.0f, 731.1818181818181f, 689.0f, 723.0f),
                        PathNode.VerticalTo(400.0f),
                        PathNode.HorizontalTo(942.0f),
                        PathNode.QuadTo(950.0f, 400.0f, 954.0f, 395.7142857142857f),
                        PathNode.QuadTo(958.0f, 391.42857142857144f, 958.0f, 385.0f),
                        PathNode.VerticalTo(365.88235294117646f),
                        PathNode.QuadTo(958.0f, 359.0f, 954.0f, 355.0f),
                        PathNode.QuadTo(950.0f, 351.0f, 942.0f, 351.0f),
                        PathNode.HorizontalTo(659.0f),
                        PathNode.QuadTo(649.0f, 351.0f, 645.0f, 356.88235294117646f),
                        PathNode.QuadTo(641.0f, 362.7647058823529f, 641.0f, 376.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentLight!!
    }

private var _recentLight: ImageVector? = null

val MiuixIcons.Regular.Recent: ImageVector
    get() {
        if (_recentRegular != null) return _recentRegular!!
        _recentRegular = ImageVector.Builder(
            name = "Recent.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1198.8f,
            viewportHeight = 1198.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -65.60000000000002f, translationY = 974.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1164.0f, 376.0f),
                        PathNode.QuadTo(1164.0f, 511.0f, 1097.0f, 626.0f),
                        PathNode.QuadTo(1030.0f, 741.0f, 915.5f, 808.0f),
                        PathNode.QuadTo(801.0f, 875.0f, 665.0f, 875.0f),
                        PathNode.QuadTo(529.0f, 875.0f, 414.5f, 808.0f),
                        PathNode.QuadTo(300.0f, 741.0f, 233.0f, 626.0f),
                        PathNode.QuadTo(166.0f, 511.0f, 166.0f, 376.0f),
                        PathNode.QuadTo(166.0f, 240.0f, 233.0f, 125.5f),
                        PathNode.QuadTo(300.0f, 11.0f, 414.5f, -56.5f),
                        PathNode.QuadTo(529.0f, -124.0f, 665.0f, -124.0f),
                        PathNode.QuadTo(801.0f, -124.0f, 915.5f, -56.5f),
                        PathNode.QuadTo(1030.0f, 11.0f, 1097.0f, 125.5f),
                        PathNode.QuadTo(1164.0f, 240.0f, 1164.0f, 376.0f),
                        PathNode.Close,
                        PathNode.MoveTo(632.0f, 376.0f),
                        PathNode.VerticalTo(707.0f),
                        PathNode.QuadTo(632.0f, 719.0f, 637.5f, 724.0f),
                        PathNode.QuadTo(643.0f, 729.0f, 656.0f, 729.0f),
                        PathNode.HorizontalTo(674.0f),
                        PathNode.QuadTo(687.0f, 729.0f, 692.5f, 724.0f),
                        PathNode.QuadTo(698.0f, 719.0f, 698.0f, 707.0f),
                        PathNode.VerticalTo(409.0f),
                        PathNode.HorizontalTo(926.0f),
                        PathNode.QuadTo(937.0f, 409.0f, 942.5f, 403.0f),
                        PathNode.QuadTo(948.0f, 397.0f, 948.0f, 388.0f),
                        PathNode.VerticalTo(363.0f),
                        PathNode.QuadTo(948.0f, 354.0f, 942.5f, 348.0f),
                        PathNode.QuadTo(937.0f, 342.0f, 926.0f, 342.0f),
                        PathNode.HorizontalTo(655.0f),
                        PathNode.QuadTo(641.0f, 342.0f, 636.5f, 350.0f),
                        PathNode.QuadTo(632.0f, 358.0f, 632.0f, 376.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentRegular!!
    }

private var _recentRegular: ImageVector? = null

val MiuixIcons.Heavy.Recent: ImageVector
    get() {
        if (_recentHeavy != null) return _recentHeavy!!
        _recentHeavy = ImageVector.Builder(
            name = "Recent.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1237.2f,
            viewportHeight = 1237.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -46.39999999999998f, translationY = 994.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1180.0f, 376.0f),
                        PathNode.QuadTo(1180.0f, 515.068493150685f, 1111.0f, 633.5342465753424f),
                        PathNode.QuadTo(1042.0f, 752.0f, 923.7356164383561f, 821.5f),
                        PathNode.QuadTo(805.4712328767123f, 891.0f, 665.0f, 891.0f),
                        PathNode.QuadTo(524.5287671232877f, 891.0f, 406.26438356164385f, 821.5f),
                        PathNode.QuadTo(288.0f, 752.0f, 219.0f, 633.5342465753424f),
                        PathNode.QuadTo(150.0f, 515.068493150685f, 150.0f, 376.0f),
                        PathNode.QuadTo(150.0f, 235.648f, 219.1482965931864f, 117.484f),
                        PathNode.QuadTo(288.2965931863728f, -0.6799999999999997f, 406.4679358717435f, -70.34f),
                        PathNode.QuadTo(524.6392785571143f, -140.0f, 665.0f, -140.0f),
                        PathNode.QuadTo(805.3607214428857f, -140.0f, 923.5320641282565f, -70.34f),
                        PathNode.QuadTo(1041.7034068136272f, -0.6799999999999997f, 1110.8517034068136f, 117.484f),
                        PathNode.QuadTo(1180.0f, 235.648f, 1180.0f, 376.0f),
                        PathNode.Close,
                        PathNode.MoveTo(619.0f, 376.0f),
                        PathNode.VerticalTo(689.0f),
                        PathNode.QuadTo(619.0f, 703.0f, 625.4166666666667f, 710.0f),
                        PathNode.QuadTo(631.8333333333334f, 717.0f, 647.0f, 717.0f),
                        PathNode.HorizontalTo(683.0f),
                        PathNode.QuadTo(698.1666666666666f, 717.0f, 704.5833333333333f, 710.0f),
                        PathNode.QuadTo(711.0f, 703.0f, 711.0f, 689.0f),
                        PathNode.VerticalTo(422.0f),
                        PathNode.HorizontalTo(909.0f),
                        PathNode.QuadTo(922.5f, 422.0f, 929.25f, 415.0f),
                        PathNode.QuadTo(936.0f, 408.0f, 936.0f, 395.0f),
                        PathNode.VerticalTo(356.0f),
                        PathNode.QuadTo(936.0f, 343.0f, 929.25f, 336.0f),
                        PathNode.QuadTo(922.5f, 329.0f, 909.0f, 329.0f),
                        PathNode.HorizontalTo(655.0f),
                        PathNode.QuadTo(636.0f, 329.0f, 627.5f, 340.05882352941177f),
                        PathNode.QuadTo(619.0f, 351.11764705882354f, 619.0f, 376.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _recentHeavy!!
    }

private var _recentHeavy: ImageVector? = null
