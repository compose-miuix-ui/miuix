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

val MiuixIcons.Undo: ImageVector
    get() = MiuixIcons.Regular.Undo

val MiuixIcons.Light.Undo: ImageVector
    get() {
        if (_undoLight != null) return _undoLight!!
        _undoLight = ImageVector.Builder(
            name = "Undo.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1144.9333333333332f,
            viewportHeight = 1144.9333333333332f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -10.533333333333417f, translationY = 999.5222222222221f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(710.0f, 625.0f),
                PathNode.QuadTo(801.0f, 617.0f, 854.0f, 591.0f),
                PathNode.QuadTo(905.0f, 565.0f, 945.5f, 524.0f),
                PathNode.QuadTo(986.0f, 483.0f, 1013.0f, 431.0f),
                PathNode.QuadTo(1039.0f, 379.0f, 1047.0f, 288.0f),
                PathNode.QuadTo(1052.0f, 232.0f, 1052.0f, 69.0f),
                PathNode.VerticalTo(-29.0f),
                PathNode.QuadTo(1052.0f, -39.0f, 1047.0f, -44.5f),
                PathNode.QuadTo(1042.0f, -50.0f, 1030.0f, -50.0f),
                PathNode.HorizontalTo(1014.0f),
                PathNode.QuadTo(1003.0f, -50.0f, 998.0f, -44.5f),
                PathNode.QuadTo(993.0f, -39.0f, 993.0f, -29.0f),
                PathNode.VerticalTo(67.0f),
                PathNode.QuadTo(993.0f, 133.0f, 992.0f, 190.0f),
                PathNode.QuadTo(991.0f, 247.0f, 988.0f, 284.0f),
                PathNode.QuadTo(985.0f, 324.0f, 978.0f, 353.0f),
                PathNode.QuadTo(971.0f, 382.0f, 960.0f, 405.0f),
                PathNode.QuadTo(937.0f, 448.0f, 903.0f, 482.0f),
                PathNode.QuadTo(869.0f, 516.0f, 827.0f, 538.0f),
                PathNode.QuadTo(803.0f, 550.0f, 774.0f, 556.5f),
                PathNode.QuadTo(745.0f, 563.0f, 705.0f, 566.0f),
                PathNode.QuadTo(651.0f, 570.0f, 489.0f, 570.0f),
                PathNode.LineTo(213.0f, 569.0f),
                PathNode.LineTo(436.0f, 345.0f),
                PathNode.QuadTo(446.0f, 336.0f, 447.0f, 328.0f),
                PathNode.QuadTo(448.0f, 320.0f, 439.0f, 311.0f),
                PathNode.LineTo(428.0f, 300.0f),
                PathNode.QuadTo(419.0f, 291.0f, 412.0f, 292.0f),
                PathNode.QuadTo(405.0f, 293.0f, 396.0f, 302.0f),
                PathNode.LineTo(127.0f, 572.0f),
                PathNode.QuadTo(114.0f, 584.0f, 114.0f, 598.5f),
                PathNode.QuadTo(114.0f, 613.0f, 127.0f, 625.0f),
                PathNode.LineTo(400.0f, 898.0f),
                PathNode.QuadTo(405.0f, 903.0f, 412.5f, 904.0f),
                PathNode.QuadTo(420.0f, 905.0f, 427.0f, 897.0f),
                PathNode.LineTo(441.0f, 883.0f),
                PathNode.QuadTo(449.0f, 876.0f, 447.5f, 869.0f),
                PathNode.QuadTo(446.0f, 862.0f, 440.0f, 856.0f),
                PathNode.LineTo(212.0f, 628.0f),
                PathNode.LineTo(490.0f, 629.0f),
                PathNode.QuadTo(653.0f, 629.0f, 710.0f, 625.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _undoLight!!
    }

private var _undoLight: ImageVector? = null

val MiuixIcons.Regular.Undo: ImageVector
    get() {
        if (_undoRegular != null) return _undoRegular!!
        _undoRegular = ImageVector.Builder(
            name = "Undo.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1165.3f,
            viewportHeight = 1165.3f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -0.09242424242425784f, translationY = 1015.1916666666666f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(711.0f, 639.0f),
                PathNode.QuadTo(802.0f, 631.0f, 860.0f, 603.0f),
                PathNode.QuadTo(913.0f, 576.0f, 955.0f, 534.0f),
                PathNode.QuadTo(997.0f, 492.0f, 1025.0f, 438.0f),
                PathNode.QuadTo(1053.0f, 382.0f, 1061.0f, 290.0f),
                PathNode.QuadTo(1066.0f, 233.0f, 1066.0f, 69.0f),
                PathNode.VerticalTo(-21.0f),
                PathNode.QuadTo(1066.0f, -37.0f, 1058.5f, -45.0f),
                PathNode.QuadTo(1051.0f, -53.0f, 1031.0f, -53.0f),
                PathNode.HorizontalTo(1012.0f),
                PathNode.QuadTo(995.0f, -53.0f, 987.5f, -44.5f),
                PathNode.QuadTo(980.0f, -36.0f, 980.0f, -21.0f),
                PathNode.VerticalTo(67.0f),
                PathNode.QuadTo(980.0f, 133.0f, 979.5f, 189.5f),
                PathNode.QuadTo(979.0f, 246.0f, 976.0f, 283.0f),
                PathNode.QuadTo(972.0f, 323.0f, 965.5f, 351.0f),
                PathNode.QuadTo(959.0f, 379.0f, 948.0f, 400.0f),
                PathNode.QuadTo(927.0f, 441.0f, 894.5f, 473.5f),
                PathNode.QuadTo(862.0f, 506.0f, 821.0f, 526.0f),
                PathNode.QuadTo(799.0f, 537.0f, 771.5f, 543.5f),
                PathNode.QuadTo(744.0f, 550.0f, 704.0f, 553.0f),
                PathNode.QuadTo(651.0f, 557.0f, 489.0f, 557.0f),
                PathNode.LineTo(245.0f, 556.0f),
                PathNode.LineTo(444.0f, 357.0f),
                PathNode.QuadTo(460.0f, 341.0f, 461.5f, 330.0f),
                PathNode.QuadTo(463.0f, 319.0f, 448.0f, 304.0f),
                PathNode.LineTo(435.0f, 291.0f),
                PathNode.QuadTo(421.0f, 277.0f, 410.5f, 278.5f),
                PathNode.QuadTo(400.0f, 280.0f, 386.0f, 294.0f),
                PathNode.LineTo(115.0f, 565.0f),
                PathNode.QuadTo(100.0f, 580.0f, 99.5f, 598.5f),
                PathNode.QuadTo(99.0f, 617.0f, 115.0f, 633.0f),
                PathNode.LineTo(392.0f, 909.0f),
                PathNode.QuadTo(400.0f, 917.0f, 411.0f, 918.0f),
                PathNode.QuadTo(422.0f, 919.0f, 433.0f, 908.0f),
                PathNode.LineTo(452.0f, 889.0f),
                PathNode.QuadTo(464.0f, 877.0f, 462.0f, 867.0f),
                PathNode.QuadTo(460.0f, 857.0f, 451.0f, 848.0f),
                PathNode.LineTo(245.0f, 642.0f),
                PathNode.LineTo(490.0f, 643.0f),
                PathNode.QuadTo(654.0f, 643.0f, 711.0f, 639.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _undoRegular!!
    }

private var _undoRegular: ImageVector? = null

val MiuixIcons.Heavy.Undo: ImageVector
    get() {
        if (_undoHeavy != null) return _undoHeavy!!
        _undoHeavy = ImageVector.Builder(
            name = "Undo.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1203.670588235294f,
            viewportHeight = 1203.670588235294f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 18.83529411764698f, translationY = 1034.3647058823528f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(712.0f, 655.0f),
                PathNode.QuadTo(809.0f, 647.0f, 867.0f, 617.0f),
                PathNode.QuadTo(922.0f, 589.0f, 966.0f, 545.0f),
                PathNode.QuadTo(1010.0f, 501.0f, 1039.0f, 445.0f),
                PathNode.QuadTo(1069.0f, 388.0f, 1077.0f, 291.0f),
                PathNode.QuadTo(1082.0f, 234.0f, 1082.0f, 69.0f),
                PathNode.VerticalTo(-21.0f),
                PathNode.QuadTo(1082.0f, -43.0f, 1069.5f, -56.0f),
                PathNode.QuadTo(1057.0f, -69.0f, 1031.0f, -69.0f),
                PathNode.HorizontalTo(1012.0f),
                PathNode.QuadTo(989.0f, -69.0f, 976.5f, -56.0f),
                PathNode.QuadTo(964.0f, -43.0f, 964.0f, -21.0f),
                PathNode.VerticalTo(67.0f),
                PathNode.QuadTo(964.0f, 133.0f, 963.5f, 189.5f),
                PathNode.QuadTo(963.0f, 246.0f, 960.0f, 282.0f),
                PathNode.QuadTo(956.0f, 321.0f, 950.0f, 346.5f),
                PathNode.QuadTo(944.0f, 372.0f, 934.0f, 393.0f),
                PathNode.QuadTo(913.0f, 432.0f, 883.0f, 462.5f),
                PathNode.QuadTo(853.0f, 493.0f, 814.0f, 512.0f),
                PathNode.QuadTo(793.0f, 523.0f, 767.5f, 528.5f),
                PathNode.QuadTo(742.0f, 534.0f, 703.0f, 537.0f),
                PathNode.QuadTo(651.0f, 541.0f, 489.0f, 541.0f),
                PathNode.LineTo(284.0f, 540.0f),
                PathNode.LineTo(455.0f, 368.0f),
                PathNode.QuadTo(476.0f, 347.0f, 477.5f, 329.5f),
                PathNode.QuadTo(479.0f, 312.0f, 459.0f, 293.0f),
                PathNode.LineTo(446.0f, 280.0f),
                PathNode.QuadTo(430.0f, 263.0f, 412.5f, 263.0f),
                PathNode.QuadTo(395.0f, 263.0f, 375.0f, 283.0f),
                PathNode.LineTo(104.0f, 554.0f),
                PathNode.QuadTo(84.0f, 574.0f, 84.0f, 599.0f),
                PathNode.QuadTo(84.0f, 624.0f, 104.0f, 644.0f),
                PathNode.LineTo(381.0f, 920.0f),
                PathNode.QuadTo(393.0f, 933.0f, 411.0f, 934.0f),
                PathNode.QuadTo(429.0f, 935.0f, 444.0f, 919.0f),
                PathNode.LineTo(463.0f, 900.0f),
                PathNode.QuadTo(479.0f, 884.0f, 478.0f, 867.5f),
                PathNode.QuadTo(477.0f, 851.0f, 462.0f, 837.0f),
                PathNode.LineTo(284.0f, 658.0f),
                PathNode.LineTo(490.0f, 659.0f),
                PathNode.QuadTo(654.0f, 659.0f, 712.0f, 655.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _undoHeavy!!
    }

private var _undoHeavy: ImageVector? = null
