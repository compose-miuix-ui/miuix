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

val MiuixIcons.Phone: ImageVector
    get() = MiuixIcons.Regular.Phone

val MiuixIcons.Light.Phone: ImageVector
    get() {
        if (_phoneLight != null) return _phoneLight!!
        _phoneLight = ImageVector.Builder(
            name = "Phone.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1153.2f,
            viewportHeight = 1153.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1153.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(379.1f, 379.1f),
                        PathNode.QuadTo(291.1f, 467.1f, 221.6f, 574.6f),
                        PathNode.QuadTo(152.1f, 682.1f, 115.1f, 794.1f),
                        PathNode.QuadTo(96.1f, 849.1f, 107.6f, 908.6f),
                        PathNode.QuadTo(119.1f, 968.1f, 167.1f, 1000.1f),
                        PathNode.QuadTo(212.1f, 1031.1f, 241.1f, 1046.1f),
                        PathNode.QuadTo(263.1f, 1057.1f, 286.1f, 1049.1f),
                        PathNode.QuadTo(309.1f, 1041.1f, 321.1f, 1019.1f),
                        PathNode.LineTo(425.1f, 840.1f),
                        PathNode.QuadTo(433.1f, 825.1f, 432.1f, 809.1f),
                        PathNode.QuadTo(431.1f, 793.1f, 424.1f, 779.1f),
                        PathNode.LineTo(389.1f, 713.1f),
                        PathNode.QuadTo(374.1f, 685.1f, 375.1f, 654.1f),
                        PathNode.QuadTo(376.1f, 623.1f, 392.1f, 597.1f),
                        PathNode.QuadTo(441.1f, 521.1f, 482.1f, 481.1f),
                        PathNode.QuadTo(525.1f, 437.1f, 598.1f, 391.1f),
                        PathNode.QuadTo(623.1f, 375.1f, 654.6f, 374.1f),
                        PathNode.QuadTo(686.1f, 373.1f, 714.1f, 388.1f),
                        PathNode.LineTo(780.1f, 424.1f),
                        PathNode.QuadTo(794.1f, 431.1f, 810.1f, 431.6f),
                        PathNode.QuadTo(826.1f, 432.1f, 841.1f, 424.1f),
                        PathNode.LineTo(1020.1f, 320.1f),
                        PathNode.QuadTo(1042.1f, 307.1f, 1049.6f, 284.1f),
                        PathNode.QuadTo(1057.1f, 261.1f, 1046.1f, 240.1f),
                        PathNode.QuadTo(1032.1f, 208.1f, 1002.1f, 166.1f),
                        PathNode.QuadTo(968.1f, 119.1f, 909.1f, 107.6f),
                        PathNode.QuadTo(850.1f, 96.1f, 795.1f, 114.1f),
                        PathNode.QuadTo(682.1f, 151.1f, 575.1f, 220.6f),
                        PathNode.QuadTo(468.1f, 290.1f, 379.1f, 379.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _phoneLight!!
    }

private var _phoneLight: ImageVector? = null

val MiuixIcons.Normal.Phone: ImageVector
    get() {
        if (_phoneNormal != null) return _phoneNormal!!
        _phoneNormal = ImageVector.Builder(
            name = "Phone.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1170.5f,
            viewportHeight = 1170.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1170.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(382.3f, 381.9f),
                        PathNode.QuadTo(294.9f, 469.2f, 225.1f, 577.4f),
                        PathNode.QuadTo(155.3f, 685.6f, 117.6f, 798.3f),
                        PathNode.QuadTo(97.9f, 855.4f, 110.1f, 917.6f),
                        PathNode.QuadTo(122.3f, 979.9f, 173.0f, 1013.9f),
                        PathNode.QuadTo(218.7f, 1045.6f, 247.7f, 1059.9f),
                        PathNode.QuadTo(273.1f, 1073.0f, 299.2f, 1063.6f),
                        PathNode.QuadTo(325.3f, 1054.2f, 339.4f, 1029.5f),
                        PathNode.LineTo(442.7f, 851.2f),
                        PathNode.QuadTo(452.1f, 834.1f, 451.1f, 815.7f),
                        PathNode.QuadTo(450.1f, 797.3f, 441.7f, 781.9f),
                        PathNode.LineTo(406.7f, 715.9f),
                        PathNode.QuadTo(393.1f, 690.7f, 394.1f, 662.4f),
                        PathNode.QuadTo(395.1f, 634.2f, 409.7f, 610.2f),
                        PathNode.QuadTo(455.9f, 537.7f, 497.6f, 496.3f),
                        PathNode.QuadTo(542.0f, 451.6f, 611.6f, 408.4f),
                        PathNode.QuadTo(634.5f, 393.7f, 663.3f, 392.7f),
                        PathNode.QuadTo(692.0f, 391.7f, 717.3f, 405.4f),
                        PathNode.LineTo(783.3f, 441.4f),
                        PathNode.QuadTo(798.6f, 449.0f, 817.4f, 449.9f),
                        PathNode.QuadTo(836.1f, 450.7f, 852.5f, 441.4f),
                        PathNode.LineTo(1030.8f, 338.0f),
                        PathNode.QuadTo(1056.3f, 323.0f, 1064.4f, 296.9f),
                        PathNode.QuadTo(1072.6f, 270.8f, 1060.3f, 246.4f),
                        PathNode.QuadTo(1046.3f, 215.0f, 1015.6f, 171.7f),
                        PathNode.QuadTo(980.2f, 121.9f, 918.4f, 109.7f),
                        PathNode.QuadTo(856.7f, 97.5f, 799.6f, 116.2f),
                        PathNode.QuadTo(685.9f, 153.9f, 578.3f, 223.8f),
                        PathNode.QuadTo(470.6f, 293.6f, 382.3f, 381.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _phoneNormal!!
    }

private var _phoneNormal: ImageVector? = null

val MiuixIcons.Regular.Phone: ImageVector
    get() {
        if (_phoneRegular != null) return _phoneRegular!!
        _phoneRegular = ImageVector.Builder(
            name = "Phone.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1178.4f,
            viewportHeight = 1178.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1178.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(383.7f, 383.2f),
                        PathNode.QuadTo(296.7f, 470.2f, 226.7f, 578.7f),
                        PathNode.QuadTo(156.7f, 687.2f, 118.7f, 800.2f),
                        PathNode.QuadTo(98.7f, 858.2f, 111.2f, 921.7f),
                        PathNode.QuadTo(123.7f, 985.2f, 175.7f, 1020.2f),
                        PathNode.QuadTo(221.7f, 1052.2f, 250.7f, 1066.2f),
                        PathNode.QuadTo(277.7f, 1080.2f, 305.2f, 1070.2f),
                        PathNode.QuadTo(332.7f, 1060.2f, 347.7f, 1034.2f),
                        PathNode.LineTo(450.7f, 856.2f),
                        PathNode.QuadTo(460.7f, 838.2f, 459.7f, 818.7f),
                        PathNode.QuadTo(458.7f, 799.2f, 449.7f, 783.2f),
                        PathNode.LineTo(414.7f, 717.2f),
                        PathNode.QuadTo(401.7f, 693.2f, 402.7f, 666.2f),
                        PathNode.QuadTo(403.7f, 639.2f, 417.7f, 616.2f),
                        PathNode.QuadTo(462.7f, 545.2f, 504.7f, 503.2f),
                        PathNode.QuadTo(549.7f, 458.2f, 617.7f, 416.2f),
                        PathNode.QuadTo(639.7f, 402.2f, 667.2f, 401.2f),
                        PathNode.QuadTo(694.7f, 400.2f, 718.7f, 413.2f),
                        PathNode.LineTo(784.7f, 449.2f),
                        PathNode.QuadTo(800.7f, 457.2f, 820.7f, 458.2f),
                        PathNode.QuadTo(840.7f, 459.2f, 857.7f, 449.2f),
                        PathNode.LineTo(1035.7f, 346.2f),
                        PathNode.QuadTo(1062.7f, 330.2f, 1071.2f, 302.7f),
                        PathNode.QuadTo(1079.7f, 275.2f, 1066.7f, 249.2f),
                        PathNode.QuadTo(1052.7f, 218.2f, 1021.7f, 174.2f),
                        PathNode.QuadTo(985.7f, 123.2f, 922.7f, 110.7f),
                        PathNode.QuadTo(859.7f, 98.2f, 801.7f, 117.2f),
                        PathNode.QuadTo(687.7f, 155.2f, 579.7f, 225.2f),
                        PathNode.QuadTo(471.7f, 295.2f, 383.7f, 383.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _phoneRegular!!
    }

private var _phoneRegular: ImageVector? = null

val MiuixIcons.Medium.Phone: ImageVector
    get() {
        if (_phoneMedium != null) return _phoneMedium!!
        _phoneMedium = ImageVector.Builder(
            name = "Phone.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1198.2f,
            viewportHeight = 1198.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1198.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(386.8f, 387.2f),
                        PathNode.QuadTo(299.8f, 474.2f, 229.2f, 583.0f),
                        PathNode.QuadTo(158.6f, 691.8f, 120.6f, 805.4f),
                        PathNode.QuadTo(100.1f, 866.3f, 113.1f, 932.8f),
                        PathNode.QuadTo(126.2f, 999.2f, 181.8f, 1036.6f),
                        PathNode.QuadTo(231.3f, 1070.9f, 257.3f, 1083.1f),
                        PathNode.QuadTo(287.9f, 1098.3f, 319.5f, 1087.4f),
                        PathNode.QuadTo(351.1f, 1076.6f, 367.9f, 1047.0f),
                        PathNode.LineTo(470.3f, 870.2f),
                        PathNode.QuadTo(481.5f, 850.4f, 480.8f, 828.3f),
                        PathNode.QuadTo(480.1f, 806.1f, 469.3f, 787.2f),
                        PathNode.LineTo(434.3f, 721.8f),
                        PathNode.QuadTo(423.1f, 700.1f, 423.8f, 676.1f),
                        PathNode.QuadTo(424.5f, 652.0f, 437.3f, 631.4f),
                        PathNode.QuadTo(478.8f, 565.7f, 521.9f, 521.3f),
                        PathNode.QuadTo(567.5f, 475.7f, 632.6f, 436.1f),
                        PathNode.QuadTo(652.8f, 423.8f, 677.1f, 423.1f),
                        PathNode.QuadTo(701.3f, 422.4f, 722.4f, 433.7f),
                        PathNode.LineTo(788.4f, 469.1f),
                        PathNode.QuadTo(807.3f, 478.8f, 829.7f, 479.8f),
                        PathNode.QuadTo(852.1f, 480.8f, 870.8f, 469.1f),
                        PathNode.LineTo(1048.2f, 366.7f),
                        PathNode.QuadTo(1078.8f, 348.9f, 1088.4f, 317.3f),
                        PathNode.QuadTo(1098.1f, 285.7f, 1083.3f, 256.7f),
                        PathNode.QuadTo(1067.6f, 222.2f, 1037.8f, 180.6f),
                        PathNode.QuadTo(999.4f, 126.6f, 933.5f, 113.2f),
                        PathNode.QuadTo(867.5f, 99.8f, 806.6f, 119.4f),
                        PathNode.QuadTo(692.0f, 158.0f, 583.4f, 228.3f),
                        PathNode.QuadTo(474.8f, 298.6f, 386.8f, 387.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _phoneMedium!!
    }

private var _phoneMedium: ImageVector? = null

val MiuixIcons.Demibold.Phone: ImageVector
    get() {
        if (_phoneDemibold != null) return _phoneDemibold!!
        _phoneDemibold = ImageVector.Builder(
            name = "Phone.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1212.0f,
            viewportHeight = 1212.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1212.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(389.0f, 390.0f),
                        PathNode.QuadTo(302.0f, 477.0f, 231.0f, 586.0f),
                        PathNode.QuadTo(160.0f, 695.0f, 122.0f, 809.0f),
                        PathNode.QuadTo(101.0f, 872.0f, 114.5f, 940.5f),
                        PathNode.QuadTo(128.0f, 1009.0f, 186.0f, 1048.0f),
                        PathNode.QuadTo(238.0f, 1084.0f, 262.0f, 1095.0f),
                        PathNode.QuadTo(295.0f, 1111.0f, 329.5f, 1099.5f),
                        PathNode.QuadTo(364.0f, 1088.0f, 382.0f, 1056.0f),
                        PathNode.LineTo(484.0f, 880.0f),
                        PathNode.QuadTo(496.0f, 859.0f, 495.5f, 835.0f),
                        PathNode.QuadTo(495.0f, 811.0f, 483.0f, 790.0f),
                        PathNode.LineTo(448.0f, 725.0f),
                        PathNode.QuadTo(438.0f, 705.0f, 438.5f, 683.0f),
                        PathNode.QuadTo(439.0f, 661.0f, 451.0f, 642.0f),
                        PathNode.QuadTo(490.0f, 580.0f, 534.0f, 534.0f),
                        PathNode.QuadTo(580.0f, 488.0f, 643.0f, 450.0f),
                        PathNode.QuadTo(662.0f, 439.0f, 684.0f, 438.5f),
                        PathNode.QuadTo(706.0f, 438.0f, 725.0f, 448.0f),
                        PathNode.LineTo(791.0f, 483.0f),
                        PathNode.QuadTo(812.0f, 494.0f, 836.0f, 495.0f),
                        PathNode.QuadTo(860.0f, 496.0f, 880.0f, 483.0f),
                        PathNode.LineTo(1057.0f, 381.0f),
                        PathNode.QuadTo(1090.0f, 362.0f, 1100.5f, 327.5f),
                        PathNode.QuadTo(1111.0f, 293.0f, 1095.0f, 262.0f),
                        PathNode.QuadTo(1078.0f, 225.0f, 1049.0f, 185.0f),
                        PathNode.QuadTo(1009.0f, 129.0f, 941.0f, 115.0f),
                        PathNode.QuadTo(873.0f, 101.0f, 810.0f, 121.0f),
                        PathNode.QuadTo(695.0f, 160.0f, 586.0f, 230.5f),
                        PathNode.QuadTo(477.0f, 301.0f, 389.0f, 390.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _phoneDemibold!!
    }

private var _phoneDemibold: ImageVector? = null
