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

val MiuixIcons.Glass.AddCircle: ImageVector
    get() = MiuixIcons.Glass.Regular.AddCircle

val MiuixIcons.Glass.Light.AddCircle: ImageVector
    get() {
        if (_addCircleLight != null) return _addCircleLight!!
        _addCircleLight = ImageVector.Builder(
            name = "AddCircle.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1068.6f, 571.0f),
                        PathNode.QuadTo(1068.6f, 706.4f, 1001.9f, 820.5f),
                        PathNode.QuadTo(935.2f, 934.6f, 821.2f, 1001.6f),
                        PathNode.QuadTo(707.2f, 1068.6f, 571.9f, 1068.6f),
                        PathNode.QuadTo(436.5f, 1068.6f, 322.4f, 1001.6f),
                        PathNode.QuadTo(208.3f, 934.6f, 141.3f, 820.5f),
                        PathNode.QuadTo(74.3f, 706.5f, 74.3f, 571.0f),
                        PathNode.QuadTo(74.3f, 435.5f, 141.3f, 321.6f),
                        PathNode.QuadTo(208.3f, 207.7f, 322.3f, 141.0f),
                        PathNode.QuadTo(436.4f, 74.3f, 571.9f, 74.3f),
                        PathNode.QuadTo(707.4f, 74.3f, 821.3f, 141.0f),
                        PathNode.QuadTo(935.2f, 207.7f, 1001.9f, 321.7f),
                        PathNode.QuadTo(1068.6f, 435.6f, 1068.6f, 571.0f),
                        PathNode.Close,
                        PathNode.MoveTo(133.6f, 570.9f),
                        PathNode.QuadTo(133.6f, 690.0f, 192.6f, 790.7f),
                        PathNode.QuadTo(251.6f, 891.3f, 352.2f, 950.3f),
                        PathNode.QuadTo(452.8f, 1009.3f, 571.9f, 1009.3f),
                        PathNode.QuadTo(690.6f, 1009.3f, 791.1f, 950.3f),
                        PathNode.QuadTo(891.7f, 891.3f, 951.0f, 790.7f),
                        PathNode.QuadTo(1010.3f, 690.0f, 1010.3f, 570.9f),
                        PathNode.QuadTo(1010.3f, 451.8f, 951.0f, 351.2f),
                        PathNode.QuadTo(891.7f, 250.6f, 791.1f, 191.6f),
                        PathNode.QuadTo(690.6f, 132.6f, 571.9f, 132.6f),
                        PathNode.QuadTo(452.8f, 132.6f, 352.2f, 191.6f),
                        PathNode.QuadTo(251.6f, 250.6f, 192.6f, 351.2f),
                        PathNode.QuadTo(133.6f, 451.8f, 133.6f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(600.6f, 326.5f),
                        PathNode.LineTo(600.6f, 815.8f),
                        PathNode.QuadTo(600.6f, 823.9f, 595.8f, 828.9f),
                        PathNode.QuadTo(591.0f, 833.9f, 581.4f, 833.9f),
                        PathNode.LineTo(560.9f, 833.9f),
                        PathNode.QuadTo(552.3f, 833.9f, 547.3f, 828.6f),
                        PathNode.QuadTo(542.3f, 823.2f, 542.3f, 815.8f),
                        PathNode.LineTo(542.3f, 326.5f),
                        PathNode.QuadTo(542.3f, 317.8f, 547.6f, 313.1f),
                        PathNode.QuadTo(552.9f, 308.4f, 561.5f, 308.4f),
                        PathNode.LineTo(581.9f, 308.4f),
                        PathNode.QuadTo(590.6f, 308.4f, 595.6f, 313.1f),
                        PathNode.QuadTo(600.6f, 317.8f, 600.6f, 326.5f),
                        PathNode.Close,
                        PathNode.MoveTo(816.4f, 600.6f),
                        PathNode.LineTo(327.0f, 600.6f),
                        PathNode.QuadTo(319.0f, 600.6f, 314.0f, 595.8f),
                        PathNode.QuadTo(308.9f, 591.0f, 308.9f, 581.4f),
                        PathNode.LineTo(308.9f, 560.9f),
                        PathNode.QuadTo(308.9f, 552.3f, 314.3f, 547.3f),
                        PathNode.QuadTo(319.7f, 542.3f, 327.0f, 542.3f),
                        PathNode.LineTo(816.4f, 542.3f),
                        PathNode.QuadTo(825.1f, 542.3f, 829.8f, 547.6f),
                        PathNode.QuadTo(834.5f, 552.9f, 834.5f, 561.5f),
                        PathNode.LineTo(834.5f, 581.9f),
                        PathNode.QuadTo(834.5f, 590.6f, 829.8f, 595.6f),
                        PathNode.QuadTo(825.1f, 600.6f, 816.4f, 600.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addCircleLight!!
    }

private var _addCircleLight: ImageVector? = null

val MiuixIcons.Glass.Normal.AddCircle: ImageVector
    get() {
        if (_addCircleNormal != null) return _addCircleNormal!!
        _addCircleNormal = ImageVector.Builder(
            name = "AddCircle.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1077.8f, 570.9f),
                        PathNode.QuadTo(1077.8f, 708.8f, 1009.8f, 824.9f),
                        PathNode.QuadTo(941.9f, 941.0f, 825.8f, 1009.4f),
                        PathNode.QuadTo(709.8f, 1077.8f, 571.9f, 1077.8f),
                        PathNode.QuadTo(434.0f, 1077.8f, 317.9f, 1009.4f),
                        PathNode.QuadTo(201.8f, 941.0f, 133.5f, 824.9f),
                        PathNode.QuadTo(65.1f, 708.9f, 65.1f, 570.9f),
                        PathNode.QuadTo(65.1f, 433.0f, 133.5f, 317.0f),
                        PathNode.QuadTo(201.8f, 201.0f, 317.9f, 133.0f),
                        PathNode.QuadTo(434.0f, 65.1f, 571.9f, 65.1f),
                        PathNode.QuadTo(709.8f, 65.1f, 825.9f, 133.0f),
                        PathNode.QuadTo(941.9f, 201.0f, 1009.8f, 317.0f),
                        PathNode.QuadTo(1077.8f, 433.1f, 1077.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(142.8f, 570.9f),
                        PathNode.QuadTo(142.8f, 687.9f, 200.4f, 786.4f),
                        PathNode.QuadTo(258.0f, 884.8f, 356.5f, 942.5f),
                        PathNode.QuadTo(455.0f, 1000.1f, 571.9f, 1000.1f),
                        PathNode.QuadTo(688.1f, 1000.1f, 786.5f, 942.5f),
                        PathNode.QuadTo(885.0f, 884.8f, 943.0f, 786.4f),
                        PathNode.QuadTo(1001.1f, 687.9f, 1001.1f, 570.9f),
                        PathNode.QuadTo(1001.1f, 454.0f, 943.0f, 355.5f),
                        PathNode.QuadTo(885.0f, 257.0f, 786.5f, 199.4f),
                        PathNode.QuadTo(688.1f, 141.8f, 571.9f, 141.8f),
                        PathNode.QuadTo(455.0f, 141.8f, 356.5f, 199.4f),
                        PathNode.QuadTo(258.0f, 257.0f, 200.4f, 355.5f),
                        PathNode.QuadTo(142.8f, 454.0f, 142.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(609.8f, 329.5f),
                        PathNode.LineTo(609.8f, 812.5f),
                        PathNode.QuadTo(609.8f, 823.2f, 603.5f, 830.0f),
                        PathNode.QuadTo(597.2f, 836.7f, 583.8f, 836.7f),
                        PathNode.LineTo(558.2f, 836.7f),
                        PathNode.QuadTo(546.5f, 836.7f, 539.8f, 829.5f),
                        PathNode.QuadTo(533.1f, 822.3f, 533.1f, 812.5f),
                        PathNode.LineTo(533.1f, 329.5f),
                        PathNode.QuadTo(533.1f, 317.9f, 540.3f, 311.6f),
                        PathNode.QuadTo(547.4f, 305.3f, 559.0f, 305.3f),
                        PathNode.LineTo(584.7f, 305.3f),
                        PathNode.QuadTo(596.3f, 305.3f, 603.0f, 311.6f),
                        PathNode.QuadTo(609.8f, 317.9f, 609.8f, 329.5f),
                        PathNode.Close,
                        PathNode.MoveTo(813.3f, 609.8f),
                        PathNode.LineTo(330.4f, 609.8f),
                        PathNode.QuadTo(319.6f, 609.8f, 312.9f, 603.5f),
                        PathNode.QuadTo(306.2f, 597.2f, 306.2f, 583.8f),
                        PathNode.LineTo(306.2f, 558.2f),
                        PathNode.QuadTo(306.2f, 546.5f, 313.4f, 539.8f),
                        PathNode.QuadTo(320.5f, 533.1f, 330.4f, 533.1f),
                        PathNode.LineTo(813.3f, 533.1f),
                        PathNode.QuadTo(825.0f, 533.1f, 831.3f, 540.3f),
                        PathNode.QuadTo(837.5f, 547.4f, 837.5f, 559.0f),
                        PathNode.LineTo(837.5f, 584.7f),
                        PathNode.QuadTo(837.5f, 596.3f, 831.3f, 603.0f),
                        PathNode.QuadTo(825.0f, 609.8f, 813.3f, 609.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addCircleNormal!!
    }

private var _addCircleNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.AddCircle: ImageVector
    get() {
        if (_addCircleRegular != null) return _addCircleRegular!!
        _addCircleRegular = ImageVector.Builder(
            name = "AddCircle.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1081.9f, 570.9f),
                        PathNode.QuadTo(1081.9f, 709.9f, 1013.4f, 826.9f),
                        PathNode.QuadTo(944.9f, 943.9f, 827.9f, 1012.9f),
                        PathNode.QuadTo(710.9f, 1081.9f, 571.9f, 1081.9f),
                        PathNode.QuadTo(432.9f, 1081.9f, 315.9f, 1012.9f),
                        PathNode.QuadTo(198.9f, 943.9f, 129.9f, 826.9f),
                        PathNode.QuadTo(60.9f, 709.9f, 60.9f, 570.9f),
                        PathNode.QuadTo(60.9f, 431.9f, 129.9f, 314.9f),
                        PathNode.QuadTo(198.9f, 197.9f, 315.9f, 129.4f),
                        PathNode.QuadTo(432.9f, 60.9f, 571.9f, 60.9f),
                        PathNode.QuadTo(710.9f, 60.9f, 827.9f, 129.4f),
                        PathNode.QuadTo(944.9f, 197.9f, 1013.4f, 314.9f),
                        PathNode.QuadTo(1081.9f, 431.9f, 1081.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(146.9f, 570.9f),
                        PathNode.QuadTo(146.9f, 686.9f, 203.9f, 784.4f),
                        PathNode.QuadTo(260.9f, 881.9f, 358.4f, 938.9f),
                        PathNode.QuadTo(455.9f, 995.9f, 571.9f, 995.9f),
                        PathNode.QuadTo(686.9f, 995.9f, 784.4f, 938.9f),
                        PathNode.QuadTo(881.9f, 881.9f, 939.4f, 784.4f),
                        PathNode.QuadTo(996.9f, 686.9f, 996.9f, 570.9f),
                        PathNode.QuadTo(996.9f, 454.9f, 939.4f, 357.4f),
                        PathNode.QuadTo(881.9f, 259.9f, 784.4f, 202.9f),
                        PathNode.QuadTo(686.9f, 145.9f, 571.9f, 145.9f),
                        PathNode.QuadTo(455.9f, 145.9f, 358.4f, 202.9f),
                        PathNode.QuadTo(260.9f, 259.9f, 203.9f, 357.4f),
                        PathNode.QuadTo(146.9f, 454.9f, 146.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(613.9f, 330.9f),
                        PathNode.LineTo(613.9f, 810.9f),
                        PathNode.QuadTo(613.9f, 822.9f, 606.9f, 830.4f),
                        PathNode.QuadTo(599.9f, 837.9f, 584.9f, 837.9f),
                        PathNode.LineTo(556.9f, 837.9f),
                        PathNode.QuadTo(543.9f, 837.9f, 536.4f, 829.9f),
                        PathNode.QuadTo(528.9f, 821.9f, 528.9f, 810.9f),
                        PathNode.LineTo(528.9f, 330.9f),
                        PathNode.QuadTo(528.9f, 317.9f, 536.9f, 310.9f),
                        PathNode.QuadTo(544.9f, 303.9f, 557.9f, 303.9f),
                        PathNode.LineTo(585.9f, 303.9f),
                        PathNode.QuadTo(598.9f, 303.9f, 606.4f, 310.9f),
                        PathNode.QuadTo(613.9f, 317.9f, 613.9f, 330.9f),
                        PathNode.Close,
                        PathNode.MoveTo(811.9f, 613.9f),
                        PathNode.LineTo(331.9f, 613.9f),
                        PathNode.QuadTo(319.9f, 613.9f, 312.4f, 606.9f),
                        PathNode.QuadTo(304.9f, 599.9f, 304.9f, 584.9f),
                        PathNode.LineTo(304.9f, 556.9f),
                        PathNode.QuadTo(304.9f, 543.9f, 312.9f, 536.4f),
                        PathNode.QuadTo(320.9f, 528.9f, 331.9f, 528.9f),
                        PathNode.LineTo(811.9f, 528.9f),
                        PathNode.QuadTo(824.9f, 528.9f, 831.9f, 536.9f),
                        PathNode.QuadTo(838.9f, 544.9f, 838.9f, 557.9f),
                        PathNode.LineTo(838.9f, 585.9f),
                        PathNode.QuadTo(838.9f, 598.9f, 831.9f, 606.4f),
                        PathNode.QuadTo(824.9f, 613.9f, 811.9f, 613.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addCircleRegular!!
    }

private var _addCircleRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.AddCircle: ImageVector
    get() {
        if (_addCircleMedium != null) return _addCircleMedium!!
        _addCircleMedium = ImageVector.Builder(
            name = "AddCircle.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1090.0f, 570.9f),
                        PathNode.QuadTo(1090.0f, 712.1f, 1020.5f, 831.0f),
                        PathNode.QuadTo(950.9f, 949.8f, 832.0f, 1019.9f),
                        PathNode.QuadTo(713.2f, 1090.0f, 572.0f, 1090.0f),
                        PathNode.QuadTo(430.8f, 1090.0f, 311.9f, 1019.9f),
                        PathNode.QuadTo(193.0f, 949.9f, 122.9f, 831.0f),
                        PathNode.QuadTo(52.8f, 712.2f, 52.8f, 571.0f),
                        PathNode.QuadTo(52.8f, 429.8f, 122.9f, 310.9f),
                        PathNode.QuadTo(193.0f, 192.0f, 311.8f, 122.4f),
                        PathNode.QuadTo(430.7f, 52.8f, 571.9f, 52.8f),
                        PathNode.QuadTo(713.1f, 52.8f, 832.0f, 122.4f),
                        PathNode.QuadTo(950.8f, 192.0f, 1020.4f, 310.8f),
                        PathNode.QuadTo(1090.0f, 429.7f, 1090.0f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(155.0f, 571.0f),
                        PathNode.QuadTo(155.0f, 684.8f, 211.0f, 780.4f),
                        PathNode.QuadTo(266.9f, 876.0f, 362.5f, 931.9f),
                        PathNode.QuadTo(458.2f, 987.8f, 572.0f, 987.8f),
                        PathNode.QuadTo(684.8f, 987.8f, 780.5f, 931.9f),
                        PathNode.QuadTo(876.3f, 876.0f, 932.5f, 780.3f),
                        PathNode.QuadTo(988.8f, 684.7f, 988.8f, 570.9f),
                        PathNode.QuadTo(988.8f, 457.1f, 932.5f, 361.5f),
                        PathNode.QuadTo(876.3f, 265.9f, 780.5f, 210.0f),
                        PathNode.QuadTo(684.8f, 154.0f, 572.0f, 154.0f),
                        PathNode.QuadTo(458.1f, 154.0f, 362.5f, 210.0f),
                        PathNode.QuadTo(266.8f, 265.9f, 210.9f, 361.5f),
                        PathNode.QuadTo(155.0f, 457.2f, 155.0f, 571.0f),
                        PathNode.Close,
                        PathNode.MoveTo(622.0f, 336.3f),
                        PathNode.LineTo(622.0f, 805.5f),
                        PathNode.QuadTo(622.0f, 820.8f, 612.7f, 830.7f),
                        PathNode.QuadTo(603.4f, 840.6f, 584.9f, 840.6f),
                        PathNode.LineTo(556.9f, 840.6f),
                        PathNode.QuadTo(540.7f, 840.6f, 530.8f, 830.2f),
                        PathNode.QuadTo(520.8f, 819.8f, 520.8f, 805.5f),
                        PathNode.LineTo(520.8f, 336.3f),
                        PathNode.QuadTo(520.8f, 320.1f, 531.3f, 310.7f),
                        PathNode.QuadTo(541.7f, 301.2f, 557.9f, 301.2f),
                        PathNode.LineTo(585.9f, 301.2f),
                        PathNode.QuadTo(602.2f, 301.2f, 612.1f, 310.7f),
                        PathNode.QuadTo(622.0f, 320.1f, 622.0f, 336.3f),
                        PathNode.Close,
                        PathNode.MoveTo(806.5f, 622.0f),
                        PathNode.LineTo(337.3f, 622.0f),
                        PathNode.QuadTo(322.1f, 622.0f, 312.2f, 612.7f),
                        PathNode.QuadTo(302.2f, 603.4f, 302.2f, 584.9f),
                        PathNode.LineTo(302.2f, 556.9f),
                        PathNode.QuadTo(302.2f, 540.7f, 312.6f, 530.8f),
                        PathNode.QuadTo(323.0f, 520.8f, 337.3f, 520.8f),
                        PathNode.LineTo(806.5f, 520.8f),
                        PathNode.QuadTo(822.8f, 520.8f, 832.2f, 531.3f),
                        PathNode.QuadTo(841.6f, 541.7f, 841.6f, 557.9f),
                        PathNode.LineTo(841.6f, 585.9f),
                        PathNode.QuadTo(841.6f, 602.2f, 832.2f, 612.1f),
                        PathNode.QuadTo(822.8f, 622.0f, 806.5f, 622.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addCircleMedium!!
    }

private var _addCircleMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.AddCircle: ImageVector
    get() {
        if (_addCircleDemibold != null) return _addCircleDemibold!!
        _addCircleDemibold = ImageVector.Builder(
            name = "AddCircle.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1095.7f, 570.9f),
                        PathNode.QuadTo(1095.7f, 713.6f, 1025.4f, 833.8f),
                        PathNode.QuadTo(955.0f, 954.0f, 834.9f, 1024.8f),
                        PathNode.QuadTo(714.7f, 1095.7f, 572.0f, 1095.7f),
                        PathNode.QuadTo(429.3f, 1095.7f, 309.1f, 1024.9f),
                        PathNode.QuadTo(188.9f, 954.0f, 118.0f, 833.9f),
                        PathNode.QuadTo(47.1f, 713.7f, 47.1f, 571.0f),
                        PathNode.QuadTo(47.1f, 428.3f, 118.0f, 308.1f),
                        PathNode.QuadTo(188.9f, 187.9f, 309.0f, 117.5f),
                        PathNode.QuadTo(429.1f, 47.1f, 571.9f, 47.1f),
                        PathNode.QuadTo(714.6f, 47.1f, 834.8f, 117.5f),
                        PathNode.QuadTo(955.0f, 187.8f, 1025.3f, 308.0f),
                        PathNode.QuadTo(1095.7f, 428.1f, 1095.7f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(160.7f, 571.0f),
                        PathNode.QuadTo(160.7f, 683.3f, 215.9f, 777.6f),
                        PathNode.QuadTo(271.0f, 871.9f, 365.4f, 927.0f),
                        PathNode.QuadTo(459.7f, 982.1f, 572.0f, 982.1f),
                        PathNode.QuadTo(683.3f, 982.1f, 777.8f, 927.0f),
                        PathNode.QuadTo(872.3f, 871.8f, 927.7f, 777.5f),
                        PathNode.QuadTo(983.1f, 683.1f, 983.1f, 570.9f),
                        PathNode.QuadTo(983.1f, 458.6f, 927.7f, 364.3f),
                        PathNode.QuadTo(872.3f, 270.0f, 777.8f, 214.9f),
                        PathNode.QuadTo(683.4f, 159.7f, 572.0f, 159.7f),
                        PathNode.QuadTo(459.6f, 159.7f, 365.3f, 214.9f),
                        PathNode.QuadTo(271.0f, 270.0f, 215.8f, 364.4f),
                        PathNode.QuadTo(160.7f, 458.7f, 160.7f, 571.0f),
                        PathNode.Close,
                        PathNode.MoveTo(627.7f, 340.1f),
                        PathNode.LineTo(627.7f, 801.7f),
                        PathNode.QuadTo(627.7f, 819.3f, 616.8f, 830.9f),
                        PathNode.QuadTo(605.9f, 842.5f, 584.9f, 842.5f),
                        PathNode.LineTo(556.9f, 842.5f),
                        PathNode.QuadTo(538.4f, 842.5f, 526.8f, 830.4f),
                        PathNode.QuadTo(515.1f, 818.4f, 515.1f, 801.7f),
                        PathNode.LineTo(515.1f, 340.1f),
                        PathNode.QuadTo(515.1f, 321.6f, 527.3f, 310.5f),
                        PathNode.QuadTo(539.4f, 299.3f, 557.9f, 299.3f),
                        PathNode.LineTo(585.9f, 299.3f),
                        PathNode.QuadTo(604.4f, 299.3f, 616.1f, 310.5f),
                        PathNode.QuadTo(627.7f, 321.6f, 627.7f, 340.1f),
                        PathNode.Close,
                        PathNode.MoveTo(802.7f, 627.7f),
                        PathNode.LineTo(341.1f, 627.7f),
                        PathNode.QuadTo(323.6f, 627.7f, 312.0f, 616.8f),
                        PathNode.QuadTo(300.3f, 605.9f, 300.3f, 584.9f),
                        PathNode.LineTo(300.3f, 556.9f),
                        PathNode.QuadTo(300.3f, 538.4f, 312.4f, 526.8f),
                        PathNode.QuadTo(324.5f, 515.1f, 341.1f, 515.1f),
                        PathNode.LineTo(802.7f, 515.1f),
                        PathNode.QuadTo(821.3f, 515.1f, 832.4f, 527.3f),
                        PathNode.QuadTo(843.5f, 539.4f, 843.5f, 557.9f),
                        PathNode.LineTo(843.5f, 585.9f),
                        PathNode.QuadTo(843.5f, 604.4f, 832.4f, 616.1f),
                        PathNode.QuadTo(821.3f, 627.7f, 802.7f, 627.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addCircleDemibold!!
    }

private var _addCircleDemibold: ImageVector? = null
