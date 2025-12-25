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

val MiuixIcons.Timer: ImageVector
    get() = MiuixIcons.Regular.Timer

val MiuixIcons.Light.Timer: ImageVector
    get() {
        if (_timerLight != null) return _timerLight!!
        _timerLight = ImageVector.Builder(
            name = "Timer.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1272.0f,
            viewportHeight = 1272.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -28.99444444444447f, translationY = 1012.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(350.0f, -127.0f),
                PathNode.QuadTo(334.0f, -108.0f, 333.0f, -82.5f),
                PathNode.QuadTo(332.0f, -57.0f, 340.0f, 3.0f),
                PathNode.QuadTo(362.0f, 168.0f, 480.0f, 272.0f),
                PathNode.QuadTo(525.0f, 311.0f, 541.5f, 327.0f),
                PathNode.QuadTo(558.0f, 343.0f, 562.0f, 355.0f),
                PathNode.QuadTo(567.0f, 368.0f, 567.0f, 376.0f),
                PathNode.QuadTo(567.0f, 384.0f, 562.0f, 398.0f),
                PathNode.QuadTo(558.0f, 409.0f, 540.0f, 426.0f),
                PathNode.QuadTo(522.0f, 443.0f, 480.0f, 480.0f),
                PathNode.QuadTo(362.0f, 583.0f, 340.0f, 749.0f),
                PathNode.QuadTo(332.0f, 808.0f, 333.0f, 833.5f),
                PathNode.QuadTo(334.0f, 859.0f, 350.0f, 878.0f),
                PathNode.QuadTo(366.0f, 897.0f, 395.0f, 901.5f),
                PathNode.QuadTo(424.0f, 906.0f, 498.0f, 906.0f),
                PathNode.HorizontalTo(832.0f),
                PathNode.QuadTo(905.0f, 906.0f, 934.0f, 901.5f),
                PathNode.QuadTo(963.0f, 897.0f, 979.0f, 878.0f),
                PathNode.QuadTo(996.0f, 860.0f, 997.0f, 835.5f),
                PathNode.QuadTo(998.0f, 811.0f, 989.0f, 749.0f),
                PathNode.QuadTo(968.0f, 584.0f, 851.0f, 480.0f),
                PathNode.LineTo(831.0f, 463.0f),
                PathNode.QuadTo(798.0f, 433.0f, 784.5f, 419.5f),
                PathNode.QuadTo(771.0f, 406.0f, 768.0f, 396.0f),
                PathNode.QuadTo(764.0f, 383.0f, 764.0f, 375.5f),
                PathNode.QuadTo(764.0f, 368.0f, 768.0f, 355.0f),
                PathNode.QuadTo(772.0f, 345.0f, 786.5f, 330.0f),
                PathNode.QuadTo(801.0f, 315.0f, 837.0f, 284.0f),
                PathNode.LineTo(851.0f, 272.0f),
                PathNode.QuadTo(968.0f, 168.0f, 989.0f, 3.0f),
                PathNode.QuadTo(998.0f, -59.0f, 997.0f, -83.5f),
                PathNode.QuadTo(996.0f, -108.0f, 979.0f, -127.0f),
                PathNode.QuadTo(963.0f, -145.0f, 934.0f, -149.5f),
                PathNode.QuadTo(905.0f, -154.0f, 832.0f, -154.0f),
                PathNode.HorizontalTo(498.0f),
                PathNode.QuadTo(424.0f, -154.0f, 395.0f, -149.5f),
                PathNode.QuadTo(366.0f, -145.0f, 350.0f, -127.0f),
                PathNode.Close,
                PathNode.MoveTo(911.0f, 705.0f),
                PathNode.QuadTo(917.0f, 731.0f, 909.0f, 743.0f),
                PathNode.QuadTo(901.0f, 755.0f, 876.0f, 755.0f),
                PathNode.HorizontalTo(449.0f),
                PathNode.QuadTo(427.0f, 755.0f, 420.0f, 742.0f),
                PathNode.QuadTo(413.0f, 729.0f, 419.0f, 704.0f),
                PathNode.QuadTo(440.0f, 623.0f, 494.5f, 558.0f),
                PathNode.QuadTo(549.0f, 493.0f, 625.0f, 452.0f),
                PathNode.QuadTo(650.0f, 439.0f, 665.5f, 439.0f),
                PathNode.QuadTo(681.0f, 439.0f, 706.0f, 452.0f),
                PathNode.QuadTo(782.0f, 494.0f, 836.5f, 559.5f),
                PathNode.QuadTo(891.0f, 625.0f, 911.0f, 705.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _timerLight!!
    }

private var _timerLight: ImageVector? = null

val MiuixIcons.Regular.Timer: ImageVector
    get() {
        if (_timerRegular != null) return _timerRegular!!
        _timerRegular = ImageVector.Builder(
            name = "Timer.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1298.3999999999999f,
            viewportHeight = 1298.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -15.475324675324714f, translationY = 1025.1999999999998f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(341.0f, -133.0f),
                PathNode.QuadTo(322.0f, -110.0f, 320.0f, -82.0f),
                PathNode.QuadTo(318.0f, -54.0f, 327.0f, 8.0f),
                PathNode.QuadTo(351.0f, 179.0f, 471.0f, 284.0f),
                PathNode.QuadTo(514.0f, 322.0f, 529.5f, 337.0f),
                PathNode.QuadTo(545.0f, 352.0f, 548.0f, 360.0f),
                PathNode.QuadTo(552.0f, 371.0f, 552.0f, 376.0f),
                PathNode.QuadTo(552.0f, 381.0f, 548.0f, 392.0f),
                PathNode.QuadTo(545.0f, 400.0f, 528.0f, 416.5f),
                PathNode.QuadTo(511.0f, 433.0f, 471.0f, 468.0f),
                PathNode.QuadTo(351.0f, 572.0f, 327.0f, 744.0f),
                PathNode.QuadTo(318.0f, 805.0f, 320.0f, 833.0f),
                PathNode.QuadTo(322.0f, 861.0f, 341.0f, 884.0f),
                PathNode.QuadTo(360.0f, 907.0f, 391.5f, 912.0f),
                PathNode.QuadTo(423.0f, 917.0f, 499.0f, 917.0f),
                PathNode.HorizontalTo(831.0f),
                PathNode.QuadTo(906.0f, 917.0f, 937.5f, 912.0f),
                PathNode.QuadTo(969.0f, 907.0f, 988.0f, 884.0f),
                PathNode.QuadTo(1008.0f, 862.0f, 1009.5f, 834.5f),
                PathNode.QuadTo(1011.0f, 807.0f, 1002.0f, 744.0f),
                PathNode.QuadTo(979.0f, 573.0f, 860.0f, 468.0f),
                PathNode.LineTo(841.0f, 452.0f),
                PathNode.QuadTo(817.0f, 430.0f, 800.5f, 414.0f),
                PathNode.QuadTo(784.0f, 398.0f, 782.0f, 391.0f),
                PathNode.QuadTo(778.0f, 381.0f, 778.0f, 376.0f),
                PathNode.QuadTo(778.0f, 371.0f, 782.0f, 360.0f),
                PathNode.QuadTo(785.0f, 352.0f, 799.0f, 338.5f),
                PathNode.QuadTo(813.0f, 325.0f, 847.0f, 296.0f),
                PathNode.LineTo(860.0f, 284.0f),
                PathNode.QuadTo(979.0f, 179.0f, 1002.0f, 8.0f),
                PathNode.QuadTo(1011.0f, -55.0f, 1009.5f, -82.5f),
                PathNode.QuadTo(1008.0f, -110.0f, 988.0f, -133.0f),
                PathNode.QuadTo(969.0f, -155.0f, 937.5f, -160.0f),
                PathNode.QuadTo(906.0f, -165.0f, 831.0f, -165.0f),
                PathNode.HorizontalTo(499.0f),
                PathNode.QuadTo(423.0f, -165.0f, 391.5f, -160.0f),
                PathNode.QuadTo(360.0f, -155.0f, 341.0f, -133.0f),
                PathNode.Close,
                PathNode.MoveTo(896.0f, 697.0f),
                PathNode.QuadTo(902.0f, 717.0f, 893.5f, 728.5f),
                PathNode.QuadTo(885.0f, 740.0f, 864.0f, 740.0f),
                PathNode.HorizontalTo(465.0f),
                PathNode.QuadTo(443.0f, 740.0f, 435.5f, 729.0f),
                PathNode.QuadTo(428.0f, 718.0f, 433.0f, 700.0f),
                PathNode.QuadTo(454.0f, 623.0f, 506.5f, 561.0f),
                PathNode.QuadTo(559.0f, 499.0f, 631.0f, 461.0f),
                PathNode.QuadTo(653.0f, 450.0f, 665.5f, 450.0f),
                PathNode.QuadTo(678.0f, 450.0f, 700.0f, 461.0f),
                PathNode.QuadTo(771.0f, 498.0f, 823.0f, 561.0f),
                PathNode.QuadTo(875.0f, 624.0f, 896.0f, 697.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _timerRegular!!
    }

private var _timerRegular: ImageVector? = null

val MiuixIcons.Heavy.Timer: ImageVector
    get() {
        if (_timerHeavy != null) return _timerHeavy!!
        _timerHeavy = ImageVector.Builder(
            name = "Timer.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1324.8f,
            viewportHeight = 1324.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -2.581818181818221f, translationY = 1038.4f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(332.0f, -139.0f),
                PathNode.QuadTo(310.0f, -112.0f, 308.0f, -81.0f),
                PathNode.QuadTo(306.0f, -50.0f, 314.0f, 14.0f),
                PathNode.QuadTo(340.0f, 190.0f, 462.0f, 296.0f),
                PathNode.QuadTo(494.0f, 323.0f, 513.0f, 341.0f),
                PathNode.QuadTo(532.0f, 359.0f, 534.0f, 365.0f),
                PathNode.QuadTo(536.0f, 371.0f, 536.0f, 375.0f),
                PathNode.QuadTo(536.0f, 379.0f, 534.0f, 386.0f),
                PathNode.QuadTo(533.0f, 391.0f, 513.0f, 409.5f),
                PathNode.QuadTo(493.0f, 428.0f, 462.0f, 456.0f),
                PathNode.QuadTo(340.0f, 560.0f, 314.0f, 738.0f),
                PathNode.QuadTo(306.0f, 801.0f, 308.0f, 832.0f),
                PathNode.QuadTo(310.0f, 863.0f, 332.0f, 890.0f),
                PathNode.QuadTo(354.0f, 916.0f, 388.5f, 922.0f),
                PathNode.QuadTo(423.0f, 928.0f, 500.0f, 928.0f),
                PathNode.HorizontalTo(830.0f),
                PathNode.QuadTo(906.0f, 928.0f, 940.0f, 922.0f),
                PathNode.QuadTo(974.0f, 916.0f, 997.0f, 890.0f),
                PathNode.QuadTo(1020.0f, 864.0f, 1022.0f, 832.5f),
                PathNode.QuadTo(1024.0f, 801.0f, 1015.0f, 738.0f),
                PathNode.QuadTo(991.0f, 562.0f, 869.0f, 456.0f),
                PathNode.LineTo(851.0f, 440.0f),
                PathNode.QuadTo(827.0f, 420.0f, 812.5f, 406.0f),
                PathNode.QuadTo(798.0f, 392.0f, 796.0f, 386.0f),
                PathNode.QuadTo(794.0f, 379.0f, 794.0f, 375.0f),
                PathNode.QuadTo(794.0f, 371.0f, 796.0f, 365.0f),
                PathNode.QuadTo(797.0f, 361.0f, 813.5f, 345.5f),
                PathNode.QuadTo(830.0f, 330.0f, 856.0f, 308.0f),
                PathNode.LineTo(869.0f, 296.0f),
                PathNode.QuadTo(990.0f, 191.0f, 1015.0f, 14.0f),
                PathNode.QuadTo(1024.0f, -49.0f, 1022.0f, -80.5f),
                PathNode.QuadTo(1020.0f, -112.0f, 997.0f, -139.0f),
                PathNode.QuadTo(974.0f, -164.0f, 940.0f, -170.0f),
                PathNode.QuadTo(906.0f, -176.0f, 830.0f, -176.0f),
                PathNode.HorizontalTo(500.0f),
                PathNode.QuadTo(423.0f, -176.0f, 389.0f, -170.0f),
                PathNode.QuadTo(355.0f, -164.0f, 332.0f, -139.0f),
                PathNode.Close,
                PathNode.MoveTo(890.0f, 696.0f),
                PathNode.QuadTo(895.0f, 713.0f, 888.0f, 722.5f),
                PathNode.QuadTo(881.0f, 732.0f, 862.0f, 732.0f),
                PathNode.HorizontalTo(467.0f),
                PathNode.QuadTo(448.0f, 732.0f, 441.5f, 723.0f),
                PathNode.QuadTo(435.0f, 714.0f, 439.0f, 699.0f),
                PathNode.QuadTo(460.0f, 623.0f, 511.0f, 562.0f),
                PathNode.QuadTo(562.0f, 501.0f, 634.0f, 464.0f),
                PathNode.QuadTo(654.0f, 454.0f, 665.5f, 454.0f),
                PathNode.QuadTo(677.0f, 454.0f, 697.0f, 464.0f),
                PathNode.QuadTo(767.0f, 501.0f, 818.0f, 562.5f),
                PathNode.QuadTo(869.0f, 624.0f, 890.0f, 696.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _timerHeavy!!
    }

private var _timerHeavy: ImageVector? = null
