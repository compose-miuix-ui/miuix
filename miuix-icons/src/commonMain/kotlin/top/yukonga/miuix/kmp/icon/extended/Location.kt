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

val MiuixIcons.Location: ImageVector
    get() = MiuixIcons.Regular.Location

val MiuixIcons.Light.Location: ImageVector
    get() {
        if (_locationLight != null) return _locationLight!!
        _locationLight = ImageVector.Builder(
            name = "Location.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1290.0f,
            viewportHeight = 1290.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -20.0f, translationY = 1020.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(700.0f, 41.0f),
                        PathNode.QuadTo(769.0f, 129.0f, 847.0f, 245.0f),
                        PathNode.QuadTo(925.0f, 361.0f, 964.0f, 452.0f),
                        PathNode.QuadTo(995.0f, 522.0f, 995.0f, 591.0f),
                        PathNode.QuadTo(995.0f, 679.0f, 952.0f, 752.5f),
                        PathNode.QuadTo(909.0f, 826.0f, 835.0f, 869.5f),
                        PathNode.QuadTo(761.0f, 913.0f, 674.0f, 913.0f),
                        PathNode.QuadTo(586.0f, 913.0f, 512.0f, 869.5f),
                        PathNode.QuadTo(438.0f, 826.0f, 394.5f, 752.5f),
                        PathNode.QuadTo(351.0f, 679.0f, 351.0f, 591.0f),
                        PathNode.QuadTo(351.0f, 522.0f, 383.0f, 450.0f),
                        PathNode.QuadTo(420.0f, 361.0f, 498.5f, 246.5f),
                        PathNode.QuadTo(577.0f, 132.0f, 650.0f, 41.0f),
                        PathNode.QuadTo(659.0f, 28.0f, 674.5f, 28.0f),
                        PathNode.QuadTo(690.0f, 28.0f, 700.0f, 41.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1145.0f, 7.0f),
                        PathNode.QuadTo(1145.0f, 54.0f, 1076.0f, 94.0f),
                        PathNode.QuadTo(1007.0f, 134.0f, 880.0f, 160.0f),
                        PathNode.QuadTo(871.0f, 162.0f, 866.0f, 160.0f),
                        PathNode.QuadTo(861.0f, 158.0f, 856.0f, 152.0f),
                        PathNode.LineTo(841.0f, 128.0f),
                        PathNode.QuadTo(836.0f, 122.0f, 838.0f, 117.0f),
                        PathNode.QuadTo(840.0f, 112.0f, 850.0f, 111.0f),
                        PathNode.QuadTo(953.0f, 93.0f, 1013.5f, 66.0f),
                        PathNode.QuadTo(1074.0f, 39.0f, 1074.0f, 7.0f),
                        PathNode.QuadTo(1074.0f, -23.0f, 1019.0f, -49.0f),
                        PathNode.QuadTo(964.0f, -75.0f, 870.5f, -90.5f),
                        PathNode.QuadTo(777.0f, -106.0f, 667.0f, -106.0f),
                        PathNode.QuadTo(556.0f, -106.0f, 462.0f, -90.5f),
                        PathNode.QuadTo(368.0f, -75.0f, 313.0f, -49.0f),
                        PathNode.QuadTo(258.0f, -23.0f, 258.0f, 7.0f),
                        PathNode.QuadTo(258.0f, 40.0f, 321.5f, 67.0f),
                        PathNode.QuadTo(385.0f, 94.0f, 500.0f, 111.0f),
                        PathNode.QuadTo(509.0f, 112.0f, 510.0f, 118.5f),
                        PathNode.QuadTo(511.0f, 125.0f, 505.0f, 133.0f),
                        PathNode.LineTo(490.0f, 152.0f),
                        PathNode.QuadTo(485.0f, 159.0f, 480.5f, 160.5f),
                        PathNode.QuadTo(476.0f, 162.0f, 465.0f, 160.0f),
                        PathNode.QuadTo(332.0f, 137.0f, 258.5f, 96.5f),
                        PathNode.QuadTo(185.0f, 56.0f, 185.0f, 7.0f),
                        PathNode.QuadTo(185.0f, -39.0f, 249.0f, -78.0f),
                        PathNode.QuadTo(313.0f, -117.0f, 423.5f, -139.5f),
                        PathNode.QuadTo(534.0f, -162.0f, 667.0f, -162.0f),
                        PathNode.QuadTo(800.0f, -162.0f, 909.5f, -139.5f),
                        PathNode.QuadTo(1019.0f, -117.0f, 1082.0f, -78.0f),
                        PathNode.QuadTo(1145.0f, -39.0f, 1145.0f, 7.0f),
                        PathNode.Close,
                        PathNode.MoveTo(559.0f, 588.0f),
                        PathNode.QuadTo(559.0f, 619.0f, 574.5f, 645.5f),
                        PathNode.QuadTo(590.0f, 672.0f, 616.5f, 687.5f),
                        PathNode.QuadTo(643.0f, 703.0f, 674.0f, 703.0f),
                        PathNode.QuadTo(705.0f, 703.0f, 731.0f, 687.5f),
                        PathNode.QuadTo(757.0f, 672.0f, 773.0f, 645.5f),
                        PathNode.QuadTo(789.0f, 619.0f, 789.0f, 588.0f),
                        PathNode.QuadTo(789.0f, 557.0f, 773.5f, 530.5f),
                        PathNode.QuadTo(758.0f, 504.0f, 731.5f, 488.5f),
                        PathNode.QuadTo(705.0f, 473.0f, 674.0f, 473.0f),
                        PathNode.QuadTo(643.0f, 473.0f, 616.5f, 488.5f),
                        PathNode.QuadTo(590.0f, 504.0f, 574.5f, 530.5f),
                        PathNode.QuadTo(559.0f, 557.0f, 559.0f, 588.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationLight!!
    }

private var _locationLight: ImageVector? = null

val MiuixIcons.Regular.Location: ImageVector
    get() {
        if (_locationRegular != null) return _locationRegular!!
        _locationRegular = ImageVector.Builder(
            name = "Location.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1311.6f,
            viewportHeight = 1311.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -9.200000000000045f, translationY = 1031.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(709.0f, 36.0f),
                        PathNode.QuadTo(777.0f, 121.0f, 855.5f, 238.0f),
                        PathNode.QuadTo(934.0f, 355.0f, 973.0f, 446.0f),
                        PathNode.QuadTo(1005.0f, 520.0f, 1005.0f, 589.0f),
                        PathNode.QuadTo(1005.0f, 679.0f, 960.5f, 755.5f),
                        PathNode.QuadTo(916.0f, 832.0f, 839.5f, 877.0f),
                        PathNode.QuadTo(763.0f, 922.0f, 673.0f, 922.0f),
                        PathNode.QuadTo(582.0f, 922.0f, 505.5f, 877.0f),
                        PathNode.QuadTo(429.0f, 832.0f, 384.0f, 756.0f),
                        PathNode.QuadTo(339.0f, 680.0f, 339.0f, 589.0f),
                        PathNode.QuadTo(339.0f, 520.0f, 372.0f, 444.0f),
                        PathNode.QuadTo(409.0f, 356.0f, 487.0f, 241.5f),
                        PathNode.QuadTo(565.0f, 127.0f, 638.0f, 36.0f),
                        PathNode.QuadTo(652.0f, 18.0f, 674.0f, 18.0f),
                        PathNode.QuadTo(696.0f, 18.0f, 709.0f, 36.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1160.0f, 10.0f),
                        PathNode.QuadTo(1160.0f, 60.0f, 1088.0f, 103.0f),
                        PathNode.QuadTo(1016.0f, 146.0f, 895.0f, 170.0f),
                        PathNode.QuadTo(881.0f, 173.0f, 873.0f, 170.0f),
                        PathNode.QuadTo(865.0f, 167.0f, 859.0f, 158.0f),
                        PathNode.LineTo(834.0f, 121.0f),
                        PathNode.QuadTo(828.0f, 112.0f, 830.5f, 105.5f),
                        PathNode.QuadTo(833.0f, 99.0f, 847.0f, 97.0f),
                        PathNode.QuadTo(941.0f, 84.0f, 996.5f, 60.0f),
                        PathNode.QuadTo(1052.0f, 36.0f, 1052.0f, 10.0f),
                        PathNode.QuadTo(1052.0f, -17.0f, 1000.0f, -39.5f),
                        PathNode.QuadTo(948.0f, -62.0f, 859.5f, -75.0f),
                        PathNode.QuadTo(771.0f, -88.0f, 666.0f, -88.0f),
                        PathNode.QuadTo(561.0f, -88.0f, 472.0f, -75.0f),
                        PathNode.QuadTo(383.0f, -62.0f, 331.0f, -39.5f),
                        PathNode.QuadTo(279.0f, -17.0f, 279.0f, 10.0f),
                        PathNode.QuadTo(279.0f, 38.0f, 338.0f, 62.0f),
                        PathNode.QuadTo(397.0f, 86.0f, 501.0f, 99.0f),
                        PathNode.QuadTo(514.0f, 100.0f, 515.0f, 109.0f),
                        PathNode.QuadTo(516.0f, 118.0f, 506.0f, 131.0f),
                        PathNode.LineTo(484.0f, 160.0f),
                        PathNode.QuadTo(477.0f, 170.0f, 470.0f, 172.0f),
                        PathNode.QuadTo(463.0f, 174.0f, 445.0f, 171.0f),
                        PathNode.QuadTo(321.0f, 149.0f, 245.5f, 105.5f),
                        PathNode.QuadTo(170.0f, 62.0f, 170.0f, 10.0f),
                        PathNode.QuadTo(170.0f, -39.0f, 236.5f, -80.5f),
                        PathNode.QuadTo(303.0f, -122.0f, 417.0f, -146.5f),
                        PathNode.QuadTo(531.0f, -171.0f, 666.0f, -171.0f),
                        PathNode.QuadTo(800.0f, -171.0f, 913.5f, -146.5f),
                        PathNode.QuadTo(1027.0f, -122.0f, 1093.5f, -80.5f),
                        PathNode.QuadTo(1160.0f, -39.0f, 1160.0f, 10.0f),
                        PathNode.Close,
                        PathNode.MoveTo(552.0f, 586.0f),
                        PathNode.QuadTo(552.0f, 619.0f, 568.5f, 646.5f),
                        PathNode.QuadTo(585.0f, 674.0f, 612.5f, 690.5f),
                        PathNode.QuadTo(640.0f, 707.0f, 673.0f, 707.0f),
                        PathNode.QuadTo(705.0f, 707.0f, 733.0f, 690.5f),
                        PathNode.QuadTo(761.0f, 674.0f, 777.5f, 646.0f),
                        PathNode.QuadTo(794.0f, 618.0f, 794.0f, 586.0f),
                        PathNode.QuadTo(794.0f, 553.0f, 777.5f, 525.5f),
                        PathNode.QuadTo(761.0f, 498.0f, 733.5f, 481.5f),
                        PathNode.QuadTo(706.0f, 465.0f, 673.0f, 465.0f),
                        PathNode.QuadTo(640.0f, 465.0f, 612.0f, 481.0f),
                        PathNode.QuadTo(584.0f, 497.0f, 568.0f, 525.0f),
                        PathNode.QuadTo(552.0f, 553.0f, 552.0f, 586.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationRegular!!
    }

private var _locationRegular: ImageVector? = null

val MiuixIcons.Heavy.Location: ImageVector
    get() {
        if (_locationHeavy != null) return _locationHeavy!!
        _locationHeavy = ImageVector.Builder(
            name = "Location.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1345.2f,
            viewportHeight = 1345.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 7.600000000000023f, translationY = 1048.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(720.0f, 28.0f),
                        PathNode.QuadTo(780.0f, 92.0f, 861.5f, 216.5f),
                        PathNode.QuadTo(943.0f, 341.0f, 986.0f, 440.0f),
                        PathNode.QuadTo(1019.0f, 518.0f, 1019.0f, 589.0f),
                        PathNode.QuadTo(1019.0f, 683.0f, 972.0f, 762.5f),
                        PathNode.QuadTo(925.0f, 842.0f, 845.5f, 889.0f),
                        PathNode.QuadTo(766.0f, 936.0f, 673.0f, 936.0f),
                        PathNode.QuadTo(579.0f, 936.0f, 499.0f, 889.0f),
                        PathNode.QuadTo(419.0f, 842.0f, 372.0f, 762.5f),
                        PathNode.QuadTo(325.0f, 683.0f, 325.0f, 589.0f),
                        PathNode.QuadTo(325.0f, 519.0f, 359.0f, 438.0f),
                        PathNode.QuadTo(397.0f, 349.0f, 475.5f, 234.5f),
                        PathNode.QuadTo(554.0f, 120.0f, 627.0f, 28.0f),
                        PathNode.QuadTo(644.0f, 4.0f, 673.5f, 4.0f),
                        PathNode.QuadTo(703.0f, 4.0f, 720.0f, 28.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1174.0f, 10.0f),
                        PathNode.QuadTo(1174.0f, 65.0f, 1107.0f, 108.5f),
                        PathNode.QuadTo(1040.0f, 152.0f, 939.0f, 175.0f),
                        PathNode.QuadTo(921.0f, 179.0f, 909.0f, 174.5f),
                        PathNode.QuadTo(897.0f, 170.0f, 889.0f, 158.0f),
                        PathNode.LineTo(864.0f, 119.0f),
                        PathNode.QuadTo(854.0f, 103.0f, 859.5f, 91.0f),
                        PathNode.QuadTo(865.0f, 79.0f, 887.0f, 75.0f),
                        PathNode.QuadTo(949.0f, 65.0f, 993.5f, 46.0f),
                        PathNode.QuadTo(1038.0f, 27.0f, 1038.0f, 10.0f),
                        PathNode.QuadTo(1038.0f, -9.0f, 988.0f, -28.5f),
                        PathNode.QuadTo(938.0f, -48.0f, 852.5f, -61.0f),
                        PathNode.QuadTo(767.0f, -74.0f, 666.0f, -74.0f),
                        PathNode.QuadTo(565.0f, -74.0f, 479.0f, -61.0f),
                        PathNode.QuadTo(393.0f, -48.0f, 343.0f, -28.5f),
                        PathNode.QuadTo(293.0f, -9.0f, 293.0f, 10.0f),
                        PathNode.QuadTo(293.0f, 28.0f, 340.5f, 48.0f),
                        PathNode.QuadTo(388.0f, 68.0f, 464.0f, 79.0f),
                        PathNode.QuadTo(484.0f, 83.0f, 489.0f, 96.0f),
                        PathNode.QuadTo(494.0f, 109.0f, 480.0f, 129.0f),
                        PathNode.LineTo(456.0f, 161.0f),
                        PathNode.QuadTo(446.0f, 175.0f, 436.0f, 178.5f),
                        PathNode.QuadTo(426.0f, 182.0f, 404.0f, 177.0f),
                        PathNode.QuadTo(299.0f, 156.0f, 227.5f, 111.5f),
                        PathNode.QuadTo(156.0f, 67.0f, 156.0f, 10.0f),
                        PathNode.QuadTo(156.0f, -45.0f, 225.0f, -89.5f),
                        PathNode.QuadTo(294.0f, -134.0f, 411.0f, -159.5f),
                        PathNode.QuadTo(528.0f, -185.0f, 666.0f, -185.0f),
                        PathNode.QuadTo(803.0f, -185.0f, 920.0f, -159.5f),
                        PathNode.QuadTo(1037.0f, -134.0f, 1105.5f, -89.5f),
                        PathNode.QuadTo(1174.0f, -45.0f, 1174.0f, 10.0f),
                        PathNode.Close,
                        PathNode.MoveTo(542.0f, 586.0f),
                        PathNode.QuadTo(542.0f, 621.0f, 559.5f, 651.5f),
                        PathNode.QuadTo(577.0f, 682.0f, 607.0f, 699.5f),
                        PathNode.QuadTo(637.0f, 717.0f, 673.0f, 717.0f),
                        PathNode.QuadTo(708.0f, 717.0f, 738.5f, 699.5f),
                        PathNode.QuadTo(769.0f, 682.0f, 786.5f, 651.5f),
                        PathNode.QuadTo(804.0f, 621.0f, 804.0f, 586.0f),
                        PathNode.QuadTo(804.0f, 550.0f, 786.5f, 520.0f),
                        PathNode.QuadTo(769.0f, 490.0f, 738.5f, 472.5f),
                        PathNode.QuadTo(708.0f, 455.0f, 673.0f, 455.0f),
                        PathNode.QuadTo(637.0f, 455.0f, 607.0f, 472.5f),
                        PathNode.QuadTo(577.0f, 490.0f, 559.5f, 520.0f),
                        PathNode.QuadTo(542.0f, 550.0f, 542.0f, 586.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationHeavy!!
    }

private var _locationHeavy: ImageVector? = null
