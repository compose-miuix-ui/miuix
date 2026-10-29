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

val MiuixIcons.Glass.Phone: ImageVector
    get() = MiuixIcons.Glass.Regular.Phone

val MiuixIcons.Glass.Light.Phone: ImageVector
    get() {
        if (_phoneLight != null) return _phoneLight!!
        _phoneLight = ImageVector.Builder(
            name = "Phone.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(995.4f, 159.7f),
                        PathNode.QuadTo(1024.6f, 201.0f, 1039.5f, 233.3f),
                        PathNode.QuadTo(1050.3f, 254.9f, 1043.3f, 277.5f),
                        PathNode.QuadTo(1036.4f, 300.1f, 1013.8f, 313.9f),
                        PathNode.LineTo(834.0f, 417.8f),
                        PathNode.QuadTo(820.2f, 426.0f, 803.5f, 425.2f),
                        PathNode.QuadTo(786.8f, 424.5f, 773.5f, 417.3f),
                        PathNode.LineTo(707.0f, 381.3f),
                        PathNode.QuadTo(679.5f, 366.6f, 648.2f, 367.6f),
                        PathNode.QuadTo(616.9f, 368.6f, 591.4f, 384.8f),
                        PathNode.QuadTo(519.8f, 429.0f, 475.3f, 474.5f),
                        PathNode.QuadTo(432.8f, 516.0f, 385.6f, 590.6f),
                        PathNode.QuadTo(369.4f, 617.1f, 368.4f, 648.1f),
                        PathNode.QuadTo(367.4f, 679.1f, 382.2f, 706.2f),
                        PathNode.LineTo(417.2f, 772.7f),
                        PathNode.QuadTo(424.8f, 786.5f, 425.8f, 802.6f),
                        PathNode.QuadTo(426.8f, 818.8f, 418.6f, 833.2f),
                        PathNode.LineTo(314.7f, 1013.0f),
                        PathNode.QuadTo(301.9f, 1035.0f, 279.3f, 1043.0f),
                        PathNode.QuadTo(256.7f, 1051.0f, 234.6f, 1039.7f),
                        PathNode.QuadTo(208.3f, 1027.0f, 160.5f, 994.1f),
                        PathNode.QuadTo(128.2f, 972.3f, 112.5f, 936.8f),
                        PathNode.QuadTo(96.8f, 901.2f, 96.8f, 861.7f),
                        PathNode.QuadTo(96.8f, 822.1f, 108.4f, 787.9f),
                        PathNode.QuadTo(145.9f, 674.9f, 215.7f, 567.3f),
                        PathNode.QuadTo(285.5f, 459.7f, 372.5f, 372.7f),
                        PathNode.QuadTo(460.5f, 284.7f, 567.8f, 214.9f),
                        PathNode.QuadTo(675.2f, 145.1f, 788.7f, 107.6f),
                        PathNode.QuadTo(843.6f, 89.5f, 902.8f, 101.1f),
                        PathNode.QuadTo(962.0f, 112.7f, 995.4f, 159.7f),
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

val MiuixIcons.Glass.Normal.Phone: ImageVector
    get() {
        if (_phoneNormal != null) return _phoneNormal!!
        _phoneNormal = ImageVector.Builder(
            name = "Phone.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1000.3f, 156.9f),
                        PathNode.QuadTo(1030.7f, 200.0f, 1045.0f, 231.4f),
                        PathNode.QuadTo(1057.3f, 256.1f, 1049.3f, 282.0f),
                        PathNode.QuadTo(1041.3f, 308.0f, 1015.7f, 323.3f),
                        PathNode.LineTo(837.1f, 426.6f),
                        PathNode.QuadTo(821.1f, 436.0f, 802.1f, 435.1f),
                        PathNode.QuadTo(783.2f, 434.2f, 768.0f, 426.4f),
                        PathNode.LineTo(701.9f, 390.4f),
                        PathNode.QuadTo(676.7f, 376.9f, 648.1f, 377.9f),
                        PathNode.QuadTo(619.4f, 378.9f, 596.3f, 393.6f),
                        PathNode.QuadTo(527.2f, 436.3f, 482.3f, 481.4f),
                        PathNode.QuadTo(440.2f, 523.3f, 394.5f, 595.4f),
                        PathNode.QuadTo(379.8f, 619.5f, 378.8f, 647.7f),
                        PathNode.QuadTo(377.8f, 676.0f, 391.3f, 701.0f),
                        PathNode.LineTo(426.3f, 767.1f),
                        PathNode.QuadTo(434.9f, 782.4f, 435.9f, 800.9f),
                        PathNode.QuadTo(436.9f, 819.3f, 427.5f, 836.2f),
                        PathNode.LineTo(324.2f, 1014.8f),
                        PathNode.QuadTo(309.9f, 1039.5f, 283.9f, 1048.9f),
                        PathNode.QuadTo(257.9f, 1058.3f, 232.5f, 1045.1f),
                        PathNode.QuadTo(204.3f, 1031.5f, 157.7f, 999.2f),
                        PathNode.QuadTo(123.6f, 975.9f, 107.0f, 938.7f),
                        PathNode.QuadTo(90.4f, 901.5f, 90.4f, 860.2f),
                        PathNode.QuadTo(90.4f, 819.0f, 102.3f, 783.6f),
                        PathNode.QuadTo(140.1f, 670.6f, 210.1f, 562.3f),
                        PathNode.QuadTo(280.0f, 454.1f, 367.0f, 367.1f),
                        PathNode.QuadTo(455.0f, 279.1f, 562.8f, 209.2f),
                        PathNode.QuadTo(670.6f, 139.2f, 784.4f, 101.4f),
                        PathNode.QuadTo(841.5f, 82.7f, 903.3f, 94.9f),
                        PathNode.QuadTo(965.1f, 107.1f, 1000.3f, 156.9f),
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

val MiuixIcons.Glass.Regular.Phone: ImageVector
    get() {
        if (_phoneRegular != null) return _phoneRegular!!
        _phoneRegular = ImageVector.Builder(
            name = "Phone.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1002.5f, 155.6f),
                        PathNode.QuadTo(1033.5f, 199.6f, 1047.5f, 230.6f),
                        PathNode.QuadTo(1060.5f, 256.6f, 1052.0f, 284.1f),
                        PathNode.QuadTo(1043.5f, 311.6f, 1016.5f, 327.6f),
                        PathNode.LineTo(838.5f, 430.6f),
                        PathNode.QuadTo(821.5f, 440.6f, 801.5f, 439.6f),
                        PathNode.QuadTo(781.5f, 438.6f, 765.5f, 430.6f),
                        PathNode.LineTo(699.5f, 394.6f),
                        PathNode.QuadTo(675.5f, 381.6f, 648.0f, 382.6f),
                        PathNode.QuadTo(620.5f, 383.6f, 598.5f, 397.6f),
                        PathNode.QuadTo(530.5f, 439.6f, 485.5f, 484.6f),
                        PathNode.QuadTo(443.5f, 526.6f, 398.5f, 597.6f),
                        PathNode.QuadTo(384.5f, 620.6f, 383.5f, 647.6f),
                        PathNode.QuadTo(382.5f, 674.6f, 395.5f, 698.6f),
                        PathNode.LineTo(430.5f, 764.6f),
                        PathNode.QuadTo(439.5f, 780.6f, 440.5f, 800.1f),
                        PathNode.QuadTo(441.5f, 819.6f, 431.5f, 837.6f),
                        PathNode.LineTo(328.5f, 1015.6f),
                        PathNode.QuadTo(313.5f, 1041.6f, 286.0f, 1051.6f),
                        PathNode.QuadTo(258.5f, 1061.6f, 231.5f, 1047.6f),
                        PathNode.QuadTo(202.5f, 1033.6f, 156.5f, 1001.6f),
                        PathNode.QuadTo(121.5f, 977.6f, 104.5f, 939.6f),
                        PathNode.QuadTo(87.5f, 901.6f, 87.5f, 859.6f),
                        PathNode.QuadTo(87.5f, 817.6f, 99.5f, 781.6f),
                        PathNode.QuadTo(137.5f, 668.6f, 207.5f, 560.1f),
                        PathNode.QuadTo(277.5f, 451.6f, 364.5f, 364.6f),
                        PathNode.QuadTo(452.5f, 276.6f, 560.5f, 206.6f),
                        PathNode.QuadTo(668.5f, 136.6f, 782.5f, 98.6f),
                        PathNode.QuadTo(840.5f, 79.6f, 903.5f, 92.1f),
                        PathNode.QuadTo(966.5f, 104.6f, 1002.5f, 155.6f),
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

val MiuixIcons.Glass.Medium.Phone: ImageVector
    get() {
        if (_phoneMedium != null) return _phoneMedium!!
        _phoneMedium = ImageVector.Builder(
            name = "Phone.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1009.1f, 151.8f),
                        PathNode.QuadTo(1039.5f, 194.7f, 1054.6f, 227.9f),
                        PathNode.QuadTo(1069.2f, 257.4f, 1059.5f, 288.8f),
                        PathNode.QuadTo(1049.8f, 320.2f, 1019.5f, 338.1f),
                        PathNode.LineTo(842.4f, 440.3f),
                        PathNode.QuadTo(823.5f, 451.6f, 800.9f, 450.9f),
                        PathNode.QuadTo(778.3f, 450.2f, 759.6f, 440.6f),
                        PathNode.LineTo(693.9f, 404.8f),
                        PathNode.QuadTo(672.6f, 393.4f, 648.2f, 394.3f),
                        PathNode.QuadTo(623.8f, 395.2f, 604.0f, 407.6f),
                        PathNode.QuadTo(538.7f, 447.4f, 493.4f, 492.4f),
                        PathNode.QuadTo(450.6f, 536.0f, 408.6f, 603.0f),
                        PathNode.QuadTo(395.9f, 623.5f, 395.1f, 647.6f),
                        PathNode.QuadTo(394.2f, 671.6f, 405.8f, 692.9f),
                        PathNode.LineTo(440.6f, 758.6f),
                        PathNode.QuadTo(450.9f, 777.3f, 451.8f, 799.4f),
                        PathNode.QuadTo(452.7f, 821.4f, 441.6f, 841.3f),
                        PathNode.LineTo(339.1f, 1018.5f),
                        PathNode.QuadTo(322.2f, 1048.3f, 290.5f, 1059.3f),
                        PathNode.QuadTo(258.8f, 1070.2f, 228.6f, 1054.6f),
                        PathNode.QuadTo(199.6f, 1040.3f, 152.8f, 1008.0f),
                        PathNode.QuadTo(115.4f, 982.4f, 97.4f, 942.4f),
                        PathNode.QuadTo(79.5f, 902.4f, 79.5f, 858.5f),
                        PathNode.QuadTo(79.5f, 814.6f, 92.0f, 777.0f),
                        PathNode.QuadTo(130.3f, 663.1f, 200.6f, 554.2f),
                        PathNode.QuadTo(270.8f, 445.3f, 358.4f, 358.3f),
                        PathNode.QuadTo(446.4f, 269.8f, 554.9f, 199.5f),
                        PathNode.QuadTo(663.4f, 129.3f, 778.0f, 91.0f),
                        PathNode.QuadTo(838.7f, 71.2f, 904.7f, 84.4f),
                        PathNode.QuadTo(970.6f, 97.5f, 1009.1f, 151.8f),
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

val MiuixIcons.Glass.Demibold.Phone: ImageVector
    get() {
        if (_phoneDemibold != null) return _phoneDemibold!!
        _phoneDemibold = ImageVector.Builder(
            name = "Phone.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1013.7f, 149.1f),
                        PathNode.QuadTo(1043.7f, 191.3f, 1059.6f, 225.9f),
                        PathNode.QuadTo(1075.3f, 257.9f, 1064.8f, 292.1f),
                        PathNode.QuadTo(1054.2f, 326.2f, 1021.7f, 345.5f),
                        PathNode.LineTo(845.1f, 447.1f),
                        PathNode.QuadTo(824.8f, 459.4f, 800.5f, 458.8f),
                        PathNode.QuadTo(776.1f, 458.3f, 755.5f, 447.5f),
                        PathNode.LineTo(690.0f, 412.0f),
                        PathNode.QuadTo(670.6f, 401.8f, 648.4f, 402.5f),
                        PathNode.QuadTo(626.1f, 403.3f, 607.8f, 414.5f),
                        PathNode.QuadTo(544.4f, 452.9f, 499.0f, 497.9f),
                        PathNode.QuadTo(455.6f, 542.6f, 415.6f, 606.7f),
                        PathNode.QuadTo(403.9f, 625.6f, 403.2f, 647.5f),
                        PathNode.QuadTo(402.4f, 669.5f, 413.1f, 688.9f),
                        PathNode.LineTo(447.6f, 754.4f),
                        PathNode.QuadTo(458.9f, 775.0f, 459.7f, 798.9f),
                        PathNode.QuadTo(460.5f, 822.8f, 448.6f, 844.0f),
                        PathNode.LineTo(346.6f, 1020.6f),
                        PathNode.QuadTo(328.3f, 1053.0f, 293.7f, 1064.6f),
                        PathNode.QuadTo(259.1f, 1076.2f, 226.6f, 1059.5f),
                        PathNode.QuadTo(197.6f, 1045.0f, 150.2f, 1012.6f),
                        PathNode.QuadTo(111.1f, 985.8f, 92.5f, 944.4f),
                        PathNode.QuadTo(73.8f, 902.9f, 73.8f, 857.7f),
                        PathNode.QuadTo(73.8f, 812.5f, 86.8f, 773.7f),
                        PathNode.QuadTo(125.2f, 659.4f, 195.7f, 550.2f),
                        PathNode.QuadTo(266.1f, 441.0f, 354.1f, 354.0f),
                        PathNode.QuadTo(442.1f, 265.1f, 551.0f, 194.6f),
                        PathNode.QuadTo(659.9f, 124.1f, 774.8f, 85.7f),
                        PathNode.QuadTo(837.4f, 65.3f, 905.5f, 78.9f),
                        PathNode.QuadTo(973.5f, 92.6f, 1013.7f, 149.1f),
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
