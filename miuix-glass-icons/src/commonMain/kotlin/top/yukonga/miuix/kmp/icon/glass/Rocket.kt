// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.Rocket: ImageVector
    get() = MiuixIcons.Glass.Regular.Rocket

val MiuixIcons.Glass.Light.Rocket: ImageVector
    get() {
        if (_rocketLight != null) return _rocketLight!!
        _rocketLight = ImageVector.Builder(
            name = "Rocket.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(806.9f, 218.9f),
                        PathNode.QuadTo(815.2f, 221.9f, 820.9f, 229.6f),
                        PathNode.QuadTo(825.5f, 235.6f, 831.4f, 248.9f),
                        PathNode.LineTo(841.7f, 273.4f),
                        PathNode.QuadTo(850.9f, 296.6f, 866.2f, 306.3f),
                        PathNode.QuadTo(881.5f, 315.9f, 908.4f, 315.9f),
                        PathNode.LineTo(982.4f, 315.9f),
                        PathNode.QuadTo(1003.1f, 315.9f, 1014.6f, 327.2f),
                        PathNode.QuadTo(1026.1f, 338.4f, 1026.1f, 359.6f),
                        PathNode.QuadTo(1026.1f, 424.5f, 1001.4f, 473.8f),
                        PathNode.QuadTo(998.1f, 479.5f, 989.9f, 494.9f),
                        PathNode.LineTo(928.9f, 598.9f),
                        PathNode.QuadTo(904.4f, 641.3f, 894.0f, 670.0f),
                        PathNode.QuadTo(883.5f, 698.7f, 866.5f, 773.2f),
                        PathNode.QuadTo(844.3f, 869.0f, 799.6f, 943.3f),
                        PathNode.QuadTo(754.9f, 1017.6f, 695.9f, 1058.8f),
                        PathNode.QuadTo(636.9f, 1099.9f, 572.4f, 1099.9f),
                        PathNode.QuadTo(508.0f, 1099.9f, 449.0f, 1058.8f),
                        PathNode.QuadTo(390.0f, 1017.6f, 345.5f, 944.0f),
                        PathNode.QuadTo(301.1f, 870.5f, 279.3f, 776.2f),
                        PathNode.QuadTo(262.8f, 705.3f, 252.1f, 675.5f),
                        PathNode.QuadTo(241.4f, 645.8f, 217.0f, 603.9f),
                        PathNode.LineTo(153.0f, 494.9f),
                        PathNode.LineTo(141.4f, 473.8f),
                        PathNode.QuadTo(116.8f, 424.5f, 116.8f, 359.6f),
                        PathNode.QuadTo(116.8f, 338.4f, 127.8f, 327.2f),
                        PathNode.QuadTo(138.8f, 315.9f, 160.4f, 315.9f),
                        PathNode.LineTo(237.3f, 315.9f),
                        PathNode.QuadTo(263.9f, 315.9f, 279.7f, 305.3f),
                        PathNode.QuadTo(295.4f, 294.6f, 305.3f, 269.0f),
                        PathNode.LineTo(313.4f, 248.9f),
                        PathNode.QuadTo(319.8f, 234.3f, 324.9f, 228.3f),
                        PathNode.QuadTo(330.1f, 221.5f, 338.4f, 218.5f),
                        PathNode.QuadTo(344.7f, 215.9f, 365.2f, 215.9f),
                        PathNode.LineTo(781.4f, 215.9f),
                        PathNode.QuadTo(799.8f, 215.9f, 806.9f, 218.9f),
                        PathNode.Close,
                        PathNode.MoveTo(719.9f, 134.7f),
                        PathNode.QuadTo(725.5f, 144.5f, 722.4f, 150.7f),
                        PathNode.QuadTo(719.2f, 156.9f, 709.4f, 156.9f),
                        PathNode.LineTo(433.4f, 156.9f),
                        PathNode.QuadTo(423.7f, 156.9f, 420.8f, 151.2f),
                        PathNode.QuadTo(417.9f, 145.5f, 423.0f, 134.7f),
                        PathNode.QuadTo(445.5f, 91.6f, 484.7f, 67.3f),
                        PathNode.QuadTo(523.8f, 42.9f, 571.4f, 42.9f),
                        PathNode.QuadTo(618.5f, 42.9f, 657.7f, 67.3f),
                        PathNode.QuadTo(696.9f, 91.6f, 719.9f, 134.7f),
                        PathNode.Close,
                        PathNode.MoveTo(485.8f, 675.3f),
                        PathNode.LineTo(485.8f, 770.3f),
                        PathNode.QuadTo(485.8f, 806.3f, 510.9f, 831.1f),
                        PathNode.QuadTo(536.0f, 855.9f, 571.4f, 855.9f),
                        PathNode.QuadTo(606.9f, 855.9f, 632.0f, 831.1f),
                        PathNode.QuadTo(657.1f, 806.3f, 657.1f, 770.3f),
                        PathNode.LineTo(657.1f, 675.3f),
                        PathNode.QuadTo(657.1f, 639.8f, 632.0f, 614.7f),
                        PathNode.QuadTo(606.9f, 589.6f, 571.4f, 589.6f),
                        PathNode.QuadTo(536.0f, 589.6f, 510.9f, 614.7f),
                        PathNode.QuadTo(485.8f, 639.8f, 485.8f, 675.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _rocketLight!!
    }

private var _rocketLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Rocket: ImageVector
    get() {
        if (_rocketNormal != null) return _rocketNormal!!
        _rocketNormal = ImageVector.Builder(
            name = "Rocket.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(809.3f, 217.6f),
                        PathNode.QuadTo(819.5f, 220.6f, 826.1f, 229.1f),
                        PathNode.QuadTo(831.7f, 235.1f, 839.7f, 253.1f),
                        PathNode.LineTo(851.1f, 279.9f),
                        PathNode.QuadTo(858.8f, 298.9f, 871.9f, 306.7f),
                        PathNode.QuadTo(884.9f, 314.6f, 908.4f, 314.6f),
                        PathNode.LineTo(982.4f, 314.6f),
                        PathNode.QuadTo(1006.8f, 314.6f, 1021.0f, 328.4f),
                        PathNode.QuadTo(1035.3f, 342.2f, 1035.3f, 367.4f),
                        PathNode.QuadTo(1035.3f, 434.4f, 1009.7f, 485.6f),
                        PathNode.QuadTo(1004.5f, 494.9f, 997.8f, 507.3f),
                        PathNode.LineTo(936.8f, 611.3f),
                        PathNode.QuadTo(912.7f, 652.7f, 902.5f, 679.9f),
                        PathNode.QuadTo(892.4f, 707.1f, 875.4f, 778.5f),
                        PathNode.QuadTo(852.0f, 876.5f, 806.0f, 952.3f),
                        PathNode.QuadTo(760.1f, 1028.1f, 699.7f, 1070.2f),
                        PathNode.QuadTo(639.3f, 1112.3f, 572.4f, 1112.3f),
                        PathNode.QuadTo(505.5f, 1112.3f, 445.2f, 1070.2f),
                        PathNode.QuadTo(384.8f, 1028.1f, 339.3f, 952.9f),
                        PathNode.QuadTo(293.8f, 877.7f, 270.5f, 781.5f),
                        PathNode.QuadTo(253.6f, 714.0f, 243.4f, 685.8f),
                        PathNode.QuadTo(233.2f, 657.6f, 209.0f, 616.3f),
                        PathNode.LineTo(145.0f, 507.3f),
                        PathNode.LineTo(133.2f, 485.6f),
                        PathNode.QuadTo(107.6f, 434.4f, 107.6f, 367.4f),
                        PathNode.QuadTo(107.6f, 342.2f, 121.3f, 328.4f),
                        PathNode.QuadTo(135.1f, 314.6f, 160.4f, 314.6f),
                        PathNode.LineTo(236.7f, 314.6f),
                        PathNode.QuadTo(260.8f, 314.6f, 274.0f, 305.7f),
                        PathNode.QuadTo(287.2f, 296.9f, 296.5f, 273.8f),
                        PathNode.LineTo(305.2f, 253.1f),
                        PathNode.QuadTo(313.3f, 234.7f, 319.1f, 228.7f),
                        PathNode.QuadTo(325.5f, 220.4f, 335.7f, 217.4f),
                        PathNode.QuadTo(343.1f, 214.6f, 366.7f, 214.6f),
                        PathNode.LineTo(778.7f, 214.6f),
                        PathNode.QuadTo(801.6f, 214.6f, 809.3f, 217.6f),
                        PathNode.Close,
                        PathNode.MoveTo(722.3f, 123.9f),
                        PathNode.QuadTo(728.9f, 135.2f, 724.8f, 142.6f),
                        PathNode.QuadTo(720.7f, 150.1f, 709.4f, 150.1f),
                        PathNode.LineTo(433.4f, 150.1f),
                        PathNode.QuadTo(422.1f, 150.1f, 418.5f, 143.1f),
                        PathNode.QuadTo(414.8f, 136.2f, 420.5f, 123.9f),
                        PathNode.QuadTo(443.4f, 80.1f, 483.1f, 55.3f),
                        PathNode.QuadTo(522.8f, 30.6f, 571.4f, 30.6f),
                        PathNode.QuadTo(619.2f, 30.6f, 659.2f, 55.3f),
                        PathNode.QuadTo(699.3f, 80.1f, 722.3f, 123.9f),
                        PathNode.Close,
                        PathNode.MoveTo(479.3f, 678.5f),
                        PathNode.LineTo(479.3f, 773.5f),
                        PathNode.QuadTo(479.3f, 812.2f, 506.4f, 838.9f),
                        PathNode.QuadTo(533.5f, 865.6f, 571.4f, 865.6f),
                        PathNode.QuadTo(609.3f, 865.6f, 636.4f, 838.9f),
                        PathNode.QuadTo(663.5f, 812.2f, 663.5f, 773.5f),
                        PathNode.LineTo(663.5f, 678.5f),
                        PathNode.QuadTo(663.5f, 640.6f, 636.4f, 613.5f),
                        PathNode.QuadTo(609.3f, 586.4f, 571.4f, 586.4f),
                        PathNode.QuadTo(533.5f, 586.4f, 506.4f, 613.5f),
                        PathNode.QuadTo(479.3f, 640.6f, 479.3f, 678.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _rocketNormal!!
    }

private var _rocketNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Rocket: ImageVector
    get() {
        if (_rocketRegular != null) return _rocketRegular!!
        _rocketRegular = ImageVector.Builder(
            name = "Rocket.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(810.4f, 216.9f),
                        PathNode.QuadTo(821.4f, 219.9f, 828.4f, 228.9f),
                        PathNode.QuadTo(834.4f, 234.9f, 843.4f, 254.9f),
                        PathNode.LineTo(855.4f, 282.9f),
                        PathNode.QuadTo(862.4f, 299.9f, 874.4f, 306.9f),
                        PathNode.QuadTo(886.4f, 313.9f, 908.4f, 313.9f),
                        PathNode.LineTo(982.4f, 313.9f),
                        PathNode.QuadTo(1008.4f, 313.9f, 1023.9f, 328.9f),
                        PathNode.QuadTo(1039.4f, 343.9f, 1039.4f, 370.9f),
                        PathNode.QuadTo(1039.4f, 438.9f, 1013.4f, 490.9f),
                        PathNode.QuadTo(1007.4f, 501.9f, 1001.4f, 512.9f),
                        PathNode.LineTo(940.4f, 616.9f),
                        PathNode.QuadTo(916.4f, 657.9f, 906.4f, 684.4f),
                        PathNode.QuadTo(896.4f, 710.9f, 879.4f, 780.9f),
                        PathNode.QuadTo(855.4f, 879.9f, 808.9f, 956.4f),
                        PathNode.QuadTo(762.4f, 1032.9f, 701.4f, 1075.4f),
                        PathNode.QuadTo(640.4f, 1117.9f, 572.4f, 1117.9f),
                        PathNode.QuadTo(504.4f, 1117.9f, 443.4f, 1075.4f),
                        PathNode.QuadTo(382.4f, 1032.9f, 336.4f, 956.9f),
                        PathNode.QuadTo(290.4f, 880.9f, 266.4f, 783.9f),
                        PathNode.QuadTo(249.4f, 717.9f, 239.4f, 690.4f),
                        PathNode.QuadTo(229.4f, 662.9f, 205.4f, 621.9f),
                        PathNode.LineTo(141.4f, 512.9f),
                        PathNode.LineTo(129.4f, 490.9f),
                        PathNode.QuadTo(103.4f, 438.9f, 103.4f, 370.9f),
                        PathNode.QuadTo(103.4f, 343.9f, 118.4f, 328.9f),
                        PathNode.QuadTo(133.4f, 313.9f, 160.4f, 313.9f),
                        PathNode.LineTo(236.4f, 313.9f),
                        PathNode.QuadTo(259.4f, 313.9f, 271.4f, 305.9f),
                        PathNode.QuadTo(283.4f, 297.9f, 292.4f, 275.9f),
                        PathNode.LineTo(301.4f, 254.9f),
                        PathNode.QuadTo(310.4f, 234.9f, 316.4f, 228.9f),
                        PathNode.QuadTo(323.4f, 219.9f, 334.4f, 216.9f),
                        PathNode.QuadTo(342.4f, 213.9f, 367.4f, 213.9f),
                        PathNode.LineTo(777.4f, 213.9f),
                        PathNode.QuadTo(802.4f, 213.9f, 810.4f, 216.9f),
                        PathNode.Close,
                        PathNode.MoveTo(723.4f, 118.9f),
                        PathNode.QuadTo(730.4f, 130.9f, 725.9f, 138.9f),
                        PathNode.QuadTo(721.4f, 146.9f, 709.4f, 146.9f),
                        PathNode.LineTo(433.4f, 146.9f),
                        PathNode.QuadTo(421.4f, 146.9f, 417.4f, 139.4f),
                        PathNode.QuadTo(413.4f, 131.9f, 419.4f, 118.9f),
                        PathNode.QuadTo(442.4f, 74.9f, 482.4f, 49.9f),
                        PathNode.QuadTo(522.4f, 24.9f, 571.4f, 24.9f),
                        PathNode.QuadTo(619.4f, 24.9f, 659.9f, 49.9f),
                        PathNode.QuadTo(700.4f, 74.9f, 723.4f, 118.9f),
                        PathNode.Close,
                        PathNode.MoveTo(476.4f, 679.9f),
                        PathNode.LineTo(476.4f, 774.9f),
                        PathNode.QuadTo(476.4f, 814.9f, 504.4f, 842.4f),
                        PathNode.QuadTo(532.4f, 869.9f, 571.4f, 869.9f),
                        PathNode.QuadTo(610.4f, 869.9f, 638.4f, 842.4f),
                        PathNode.QuadTo(666.4f, 814.9f, 666.4f, 774.9f),
                        PathNode.LineTo(666.4f, 679.9f),
                        PathNode.QuadTo(666.4f, 640.9f, 638.4f, 612.9f),
                        PathNode.QuadTo(610.4f, 584.9f, 571.4f, 584.9f),
                        PathNode.QuadTo(532.4f, 584.9f, 504.4f, 612.9f),
                        PathNode.QuadTo(476.4f, 640.9f, 476.4f, 679.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _rocketRegular!!
    }

private var _rocketRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Rocket: ImageVector
    get() {
        if (_rocketMedium != null) return _rocketMedium!!
        _rocketMedium = ImageVector.Builder(
            name = "Rocket.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(812.9f, 221.7f),
                        PathNode.QuadTo(826.0f, 225.5f, 834.6f, 236.1f),
                        PathNode.QuadTo(841.2f, 242.9f, 850.7f, 264.3f),
                        PathNode.LineTo(862.2f, 290.6f),
                        PathNode.QuadTo(868.6f, 306.3f, 878.8f, 312.3f),
                        PathNode.QuadTo(888.9f, 318.4f, 909.0f, 318.4f),
                        PathNode.LineTo(982.4f, 318.4f),
                        PathNode.QuadTo(1011.7f, 318.4f, 1029.6f, 335.7f),
                        PathNode.QuadTo(1047.5f, 353.0f, 1047.5f, 383.5f),
                        PathNode.QuadTo(1047.5f, 453.4f, 1020.7f, 507.0f),
                        PathNode.QuadTo(1013.9f, 519.4f, 1007.9f, 529.5f),
                        PathNode.LineTo(947.5f, 628.1f),
                        PathNode.QuadTo(924.0f, 666.4f, 913.5f, 693.3f),
                        PathNode.QuadTo(902.9f, 720.3f, 887.3f, 783.8f),
                        PathNode.QuadTo(862.7f, 883.0f, 815.3f, 960.3f),
                        PathNode.QuadTo(767.8f, 1037.7f, 705.2f, 1081.0f),
                        PathNode.QuadTo(642.6f, 1124.3f, 572.4f, 1124.3f),
                        PathNode.QuadTo(502.3f, 1124.3f, 439.8f, 1081.1f),
                        PathNode.QuadTo(377.3f, 1037.9f, 330.2f, 961.1f),
                        PathNode.QuadTo(283.1f, 884.3f, 258.6f, 786.8f),
                        PathNode.QuadTo(243.2f, 727.5f, 232.4f, 699.6f),
                        PathNode.QuadTo(221.6f, 671.7f, 198.1f, 633.1f),
                        PathNode.LineTo(134.7f, 529.5f),
                        PathNode.LineTo(122.1f, 507.0f),
                        PathNode.QuadTo(95.3f, 453.4f, 95.3f, 383.5f),
                        PathNode.QuadTo(95.3f, 353.3f, 112.6f, 335.8f),
                        PathNode.QuadTo(129.9f, 318.4f, 160.4f, 318.4f),
                        PathNode.LineTo(235.9f, 318.4f),
                        PathNode.QuadTo(256.5f, 318.4f, 266.7f, 311.6f),
                        PathNode.QuadTo(276.9f, 304.8f, 285.1f, 285.3f),
                        PathNode.LineTo(294.1f, 264.3f),
                        PathNode.QuadTo(303.7f, 242.9f, 310.2f, 236.1f),
                        PathNode.QuadTo(318.8f, 225.5f, 332.0f, 221.7f),
                        PathNode.QuadTo(341.9f, 218.4f, 367.4f, 218.4f),
                        PathNode.LineTo(777.4f, 218.4f),
                        PathNode.QuadTo(803.0f, 218.4f, 812.9f, 221.7f),
                        PathNode.Close,
                        PathNode.MoveTo(727.2f, 114.7f),
                        PathNode.QuadTo(735.3f, 128.9f, 729.6f, 139.1f),
                        PathNode.QuadTo(723.9f, 149.2f, 709.4f, 149.2f),
                        PathNode.LineTo(433.4f, 149.2f),
                        PathNode.QuadTo(419.0f, 149.2f, 413.6f, 139.7f),
                        PathNode.QuadTo(408.3f, 130.2f, 415.6f, 115.0f),
                        PathNode.QuadTo(438.9f, 70.2f, 480.1f, 44.4f),
                        PathNode.QuadTo(521.3f, 18.6f, 571.4f, 18.6f),
                        PathNode.QuadTo(620.8f, 18.6f, 662.2f, 44.3f),
                        PathNode.QuadTo(703.7f, 69.9f, 727.2f, 114.7f),
                        PathNode.Close,
                        PathNode.MoveTo(471.0f, 681.7f),
                        PathNode.LineTo(471.0f, 773.2f),
                        PathNode.QuadTo(471.0f, 815.3f, 500.6f, 844.5f),
                        PathNode.QuadTo(530.3f, 873.6f, 571.4f, 873.6f),
                        PathNode.QuadTo(612.6f, 873.6f, 642.2f, 844.5f),
                        PathNode.QuadTo(671.8f, 815.3f, 671.8f, 773.2f),
                        PathNode.LineTo(671.8f, 681.7f),
                        PathNode.QuadTo(671.8f, 640.5f, 642.2f, 610.9f),
                        PathNode.QuadTo(612.6f, 581.3f, 571.4f, 581.3f),
                        PathNode.QuadTo(530.3f, 581.3f, 500.6f, 610.9f),
                        PathNode.QuadTo(471.0f, 640.5f, 471.0f, 681.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _rocketMedium!!
    }

private var _rocketMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Rocket: ImageVector
    get() {
        if (_rocketDemibold != null) return _rocketDemibold!!
        _rocketDemibold = ImageVector.Builder(
            name = "Rocket.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(814.6f, 225.0f),
                        PathNode.QuadTo(829.2f, 229.3f, 839.0f, 241.1f),
                        PathNode.QuadTo(845.9f, 248.5f, 855.8f, 270.8f),
                        PathNode.LineTo(866.9f, 296.0f),
                        PathNode.QuadTo(873.0f, 310.7f, 881.8f, 316.1f),
                        PathNode.QuadTo(890.6f, 321.5f, 909.3f, 321.5f),
                        PathNode.LineTo(982.4f, 321.5f),
                        PathNode.QuadTo(1013.9f, 321.5f, 1033.6f, 340.4f),
                        PathNode.QuadTo(1053.2f, 359.3f, 1053.2f, 392.3f),
                        PathNode.QuadTo(1053.2f, 463.5f, 1025.8f, 518.3f),
                        PathNode.QuadTo(1018.5f, 531.6f, 1012.5f, 541.2f),
                        PathNode.LineTo(952.4f, 636.0f),
                        PathNode.QuadTo(929.3f, 672.4f, 918.4f, 699.6f),
                        PathNode.QuadTo(907.5f, 726.8f, 892.8f, 785.8f),
                        PathNode.QuadTo(867.8f, 885.2f, 819.7f, 963.1f),
                        PathNode.QuadTo(771.6f, 1041.0f, 707.9f, 1084.8f),
                        PathNode.QuadTo(644.1f, 1128.7f, 572.4f, 1128.7f),
                        PathNode.QuadTo(500.8f, 1128.7f, 437.2f, 1085.1f),
                        PathNode.QuadTo(373.7f, 1041.4f, 325.9f, 964.1f),
                        PathNode.QuadTo(278.0f, 886.7f, 253.1f, 788.8f),
                        PathNode.QuadTo(238.9f, 734.2f, 227.5f, 706.1f),
                        PathNode.QuadTo(216.1f, 677.9f, 193.0f, 641.0f),
                        PathNode.LineTo(129.9f, 541.2f),
                        PathNode.LineTo(117.0f, 518.3f),
                        PathNode.QuadTo(89.6f, 463.5f, 89.6f, 392.3f),
                        PathNode.QuadTo(89.6f, 359.8f, 108.5f, 340.6f),
                        PathNode.QuadTo(127.5f, 321.5f, 160.4f, 321.5f),
                        PathNode.LineTo(235.5f, 321.5f),
                        PathNode.QuadTo(254.4f, 321.5f, 263.4f, 315.6f),
                        PathNode.QuadTo(272.4f, 309.6f, 280.0f, 291.8f),
                        PathNode.LineTo(289.0f, 270.8f),
                        PathNode.QuadTo(298.9f, 248.5f, 305.9f, 241.1f),
                        PathNode.QuadTo(315.6f, 229.3f, 330.3f, 225.0f),
                        PathNode.QuadTo(341.5f, 221.5f, 367.4f, 221.5f),
                        PathNode.LineTo(777.4f, 221.5f),
                        PathNode.QuadTo(803.3f, 221.5f, 814.6f, 225.0f),
                        PathNode.Close,
                        PathNode.MoveTo(729.9f, 111.8f),
                        PathNode.QuadTo(738.7f, 127.5f, 732.1f, 139.2f),
                        PathNode.QuadTo(725.6f, 150.8f, 709.4f, 150.8f),
                        PathNode.LineTo(433.4f, 150.8f),
                        PathNode.QuadTo(417.3f, 150.8f, 411.0f, 139.9f),
                        PathNode.QuadTo(404.7f, 128.9f, 413.0f, 112.3f),
                        PathNode.QuadTo(436.5f, 66.9f, 478.5f, 40.5f),
                        PathNode.QuadTo(520.6f, 14.1f, 571.4f, 14.1f),
                        PathNode.QuadTo(621.7f, 14.1f, 663.8f, 40.3f),
                        PathNode.QuadTo(705.9f, 66.4f, 729.9f, 111.8f),
                        PathNode.Close,
                        PathNode.MoveTo(467.2f, 682.9f),
                        PathNode.LineTo(467.2f, 771.9f),
                        PathNode.QuadTo(467.2f, 815.6f, 498.0f, 845.9f),
                        PathNode.QuadTo(528.8f, 876.1f, 571.4f, 876.1f),
                        PathNode.QuadTo(614.1f, 876.1f, 644.9f, 845.9f),
                        PathNode.QuadTo(675.6f, 815.6f, 675.6f, 771.9f),
                        PathNode.LineTo(675.6f, 682.9f),
                        PathNode.QuadTo(675.6f, 640.2f, 644.9f, 609.5f),
                        PathNode.QuadTo(614.1f, 578.7f, 571.4f, 578.7f),
                        PathNode.QuadTo(528.8f, 578.7f, 498.0f, 609.5f),
                        PathNode.QuadTo(467.2f, 640.2f, 467.2f, 682.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _rocketDemibold!!
    }

private var _rocketDemibold: ImageVector? = null
