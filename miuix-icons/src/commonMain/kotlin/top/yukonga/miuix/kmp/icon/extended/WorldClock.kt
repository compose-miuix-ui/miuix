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

val MiuixIcons.WorldClock: ImageVector
    get() = MiuixIcons.Regular.WorldClock

val MiuixIcons.Light.WorldClock: ImageVector
    get() {
        if (_worldclockLight != null) return _worldclockLight!!
        _worldclockLight = ImageVector.Builder(
            name = "WorldClock.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1171.2f,
            viewportHeight = 1171.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -79.39999999999998f, translationY = 960.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1153.0f, 375.4870848708487f),
                        PathNode.QuadTo(1153.0f, 507.0f, 1087.4771653543307f, 619.4725274725274f),
                        PathNode.QuadTo(1021.9543307086615f, 731.945054945055f, 909.9787401574804f, 797.4725274725274f),
                        PathNode.QuadTo(798.0031496062992f, 863.0f, 665.0015748031497f, 863.0f),
                        PathNode.QuadTo(532.0f, 863.0f, 420.02341597796146f, 797.4769539078156f),
                        PathNode.QuadTo(308.04683195592287f, 731.9539078156313f, 242.52341597796143f, 619.4889779559119f),
                        PathNode.QuadTo(177.0f, 507.02404809619236f, 177.0f, 375.0f),
                        PathNode.QuadTo(177.0f, 243.0f, 242.5228346456693f, 130.70798898071627f),
                        PathNode.QuadTo(308.0456692913386f, 18.415977961432507f, 420.0212598425197f, -47.29201101928375f),
                        PathNode.QuadTo(531.9968503937008f, -113.0f, 664.9984251968503f, -113.0f),
                        PathNode.QuadTo(798.0f, -113.0f, 909.9765840220387f, -47.29201101928375f),
                        PathNode.QuadTo(1021.9531680440772f, 18.415977961432507f, 1087.4765840220387f, 130.70798898071627f),
                        PathNode.QuadTo(1153.0f, 243.0f, 1153.0f, 375.4870848708487f),
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
        return _worldclockLight!!
    }

private var _worldclockLight: ImageVector? = null

val MiuixIcons.Regular.WorldClock: ImageVector
    get() {
        if (_worldclockRegular != null) return _worldclockRegular!!
        _worldclockRegular = ImageVector.Builder(
            name = "WorldClock.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1197.6f,
            viewportHeight = 1197.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -66.20000000000005f, translationY = 973.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1164.0f, 375.0f),
                        PathNode.QuadTo(1164.0f, 510.0f, 1097.0f, 625.0f),
                        PathNode.QuadTo(1030.0f, 740.0f, 915.5f, 807.0f),
                        PathNode.QuadTo(801.0f, 874.0f, 665.0f, 874.0f),
                        PathNode.QuadTo(529.0f, 874.0f, 414.5f, 807.0f),
                        PathNode.QuadTo(300.0f, 740.0f, 233.0f, 625.0f),
                        PathNode.QuadTo(166.0f, 510.0f, 166.0f, 375.0f),
                        PathNode.QuadTo(166.0f, 239.0f, 233.0f, 124.5f),
                        PathNode.QuadTo(300.0f, 10.0f, 414.5f, -57.0f),
                        PathNode.QuadTo(529.0f, -124.0f, 665.0f, -124.0f),
                        PathNode.QuadTo(801.0f, -124.0f, 915.5f, -57.0f),
                        PathNode.QuadTo(1030.0f, 10.0f, 1097.0f, 124.5f),
                        PathNode.QuadTo(1164.0f, 239.0f, 1164.0f, 375.0f),
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
        return _worldclockRegular!!
    }

private var _worldclockRegular: ImageVector? = null

val MiuixIcons.Heavy.WorldClock: ImageVector
    get() {
        if (_worldclockHeavy != null) return _worldclockHeavy!!
        _worldclockHeavy = ImageVector.Builder(
            name = "WorldClock.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1236.0f,
            viewportHeight = 1236.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -47.0f, translationY = 993.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1180.0f, 375.0f),
                        PathNode.QuadTo(1180.0f, 515.0f, 1111.0f, 633.0f),
                        PathNode.QuadTo(1042.0f, 751.0f, 923.5f, 820.5f),
                        PathNode.QuadTo(805.0f, 890.0f, 665.0f, 890.0f),
                        PathNode.QuadTo(525.0f, 890.0f, 406.5f, 820.5f),
                        PathNode.QuadTo(288.0f, 751.0f, 219.0f, 633.0f),
                        PathNode.QuadTo(150.0f, 515.0f, 150.0f, 375.0f),
                        PathNode.QuadTo(150.0f, 235.0f, 219.0f, 117.0f),
                        PathNode.QuadTo(288.0f, -1.0f, 406.5f, -70.5f),
                        PathNode.QuadTo(525.0f, -140.0f, 665.0f, -140.0f),
                        PathNode.QuadTo(805.0f, -140.0f, 923.5f, -70.5f),
                        PathNode.QuadTo(1042.0f, -1.0f, 1111.0f, 117.0f),
                        PathNode.QuadTo(1180.0f, 235.0f, 1180.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(619.0f, 376.0f),
                        PathNode.VerticalTo(689.0f),
                        PathNode.QuadTo(619.0f, 703.0f, 625.5f, 710.0f),
                        PathNode.QuadTo(632.0f, 717.0f, 647.0f, 717.0f),
                        PathNode.HorizontalTo(683.0f),
                        PathNode.QuadTo(698.0f, 717.0f, 704.5f, 710.0f),
                        PathNode.QuadTo(711.0f, 703.0f, 711.0f, 689.0f),
                        PathNode.VerticalTo(422.0f),
                        PathNode.HorizontalTo(909.0f),
                        PathNode.QuadTo(922.0f, 422.0f, 929.0f, 415.0f),
                        PathNode.QuadTo(936.0f, 408.0f, 936.0f, 395.0f),
                        PathNode.VerticalTo(356.0f),
                        PathNode.QuadTo(936.0f, 343.0f, 929.0f, 336.0f),
                        PathNode.QuadTo(922.0f, 329.0f, 909.0f, 329.0f),
                        PathNode.HorizontalTo(655.0f),
                        PathNode.QuadTo(636.0f, 329.0f, 627.5f, 340.0f),
                        PathNode.QuadTo(619.0f, 351.0f, 619.0f, 376.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _worldclockHeavy!!
    }

private var _worldclockHeavy: ImageVector? = null
