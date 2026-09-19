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

val MiuixIcons.Glass.Answer: ImageVector
    get() = MiuixIcons.Glass.Regular.Answer

val MiuixIcons.Glass.Light.Answer: ImageVector
    get() {
        if (_answerLight != null) return _answerLight!!
        _answerLight = ImageVector.Builder(
            name = "Answer.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1068.6f, 570.9f),
                        PathNode.QuadTo(1068.6f, 706.4f, 1001.9f, 820.5f),
                        PathNode.QuadTo(935.2f, 934.6f, 821.3f, 1001.6f),
                        PathNode.QuadTo(707.4f, 1068.6f, 571.9f, 1068.6f),
                        PathNode.QuadTo(436.5f, 1068.6f, 322.4f, 1001.6f),
                        PathNode.QuadTo(208.3f, 934.6f, 141.3f, 820.5f),
                        PathNode.QuadTo(74.3f, 706.4f, 74.3f, 570.9f),
                        PathNode.QuadTo(74.3f, 435.5f, 141.3f, 321.6f),
                        PathNode.QuadTo(208.3f, 207.7f, 322.4f, 141.0f),
                        PathNode.QuadTo(436.5f, 74.3f, 571.9f, 74.3f),
                        PathNode.QuadTo(707.4f, 74.3f, 821.3f, 141.0f),
                        PathNode.QuadTo(935.2f, 207.7f, 1001.9f, 321.6f),
                        PathNode.QuadTo(1068.6f, 435.5f, 1068.6f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(133.6f, 570.9f),
                        PathNode.QuadTo(133.6f, 690.0f, 192.6f, 790.7f),
                        PathNode.QuadTo(251.6f, 891.3f, 352.2f, 950.3f),
                        PathNode.QuadTo(452.8f, 1009.3f, 571.9f, 1009.3f),
                        PathNode.QuadTo(690.5f, 1009.3f, 791.1f, 950.3f),
                        PathNode.QuadTo(891.7f, 891.3f, 951.0f, 790.7f),
                        PathNode.QuadTo(1010.3f, 690.0f, 1010.3f, 570.9f),
                        PathNode.QuadTo(1010.3f, 452.4f, 951.0f, 351.8f),
                        PathNode.QuadTo(891.7f, 251.2f, 791.1f, 191.9f),
                        PathNode.QuadTo(690.5f, 132.6f, 571.9f, 132.6f),
                        PathNode.QuadTo(452.8f, 132.6f, 352.2f, 191.9f),
                        PathNode.QuadTo(251.6f, 251.2f, 192.6f, 351.8f),
                        PathNode.QuadTo(133.6f, 452.4f, 133.6f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(801.5f, 432.4f),
                        PathNode.LineTo(706.5f, 487.4f),
                        PathNode.QuadTo(701.3f, 491.0f, 693.7f, 491.0f),
                        PathNode.QuadTo(686.2f, 491.0f, 680.4f, 487.4f),
                        PathNode.LineTo(645.4f, 467.9f),
                        PathNode.QuadTo(630.2f, 459.6f, 612.5f, 460.3f),
                        PathNode.QuadTo(594.8f, 461.0f, 579.5f, 470.4f),
                        PathNode.QuadTo(538.5f, 494.9f, 517.3f, 518.2f),
                        PathNode.QuadTo(496.5f, 539.9f, 470.4f, 579.0f),
                        PathNode.QuadTo(461.0f, 594.3f, 460.3f, 611.9f),
                        PathNode.QuadTo(459.6f, 629.6f, 467.9f, 643.9f),
                        PathNode.LineTo(487.8f, 679.4f),
                        PathNode.QuadTo(491.5f, 686.6f, 491.5f, 692.7f),
                        PathNode.QuadTo(491.5f, 698.7f, 486.9f, 706.9f),
                        PathNode.LineTo(433.4f, 800.5f),
                        PathNode.QuadTo(428.2f, 809.8f, 417.9f, 813.7f),
                        PathNode.QuadTo(407.7f, 817.6f, 398.4f, 812.4f),
                        PathNode.LineTo(361.4f, 790.4f),
                        PathNode.QuadTo(337.5f, 776.2f, 330.9f, 747.0f),
                        PathNode.QuadTo(324.3f, 717.9f, 334.4f, 686.6f),
                        PathNode.QuadTo(353.9f, 628.5f, 389.8f, 572.8f),
                        PathNode.QuadTo(425.6f, 517.2f, 471.2f, 471.2f),
                        PathNode.QuadTo(517.2f, 425.6f, 572.5f, 389.5f),
                        PathNode.QuadTo(627.9f, 353.4f, 686.6f, 333.4f),
                        PathNode.QuadTo(713.4f, 324.3f, 743.5f, 329.6f),
                        PathNode.QuadTo(773.6f, 334.9f, 787.4f, 356.8f),
                        PathNode.LineTo(811.0f, 395.0f),
                        PathNode.QuadTo(816.7f, 404.4f, 813.8f, 415.5f),
                        PathNode.QuadTo(810.8f, 426.7f, 801.5f, 432.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerLight!!
    }

private var _answerLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Answer: ImageVector
    get() {
        if (_answerNormal != null) return _answerNormal!!
        _answerNormal = ImageVector.Builder(
            name = "Answer.Glass.Normal",
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
                        PathNode.QuadTo(65.1f, 708.8f, 65.1f, 570.9f),
                        PathNode.QuadTo(65.1f, 433.0f, 133.5f, 317.0f),
                        PathNode.QuadTo(201.8f, 201.0f, 317.9f, 133.0f),
                        PathNode.QuadTo(434.0f, 65.1f, 571.9f, 65.1f),
                        PathNode.QuadTo(709.8f, 65.1f, 825.8f, 133.0f),
                        PathNode.QuadTo(941.9f, 201.0f, 1009.8f, 317.0f),
                        PathNode.QuadTo(1077.8f, 433.0f, 1077.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(142.8f, 570.9f),
                        PathNode.QuadTo(142.8f, 687.9f, 200.4f, 786.4f),
                        PathNode.QuadTo(258.0f, 884.8f, 356.5f, 942.5f),
                        PathNode.QuadTo(455.0f, 1000.1f, 571.9f, 1000.1f),
                        PathNode.QuadTo(688.0f, 1000.1f, 786.5f, 942.5f),
                        PathNode.QuadTo(885.0f, 884.8f, 943.0f, 786.4f),
                        PathNode.QuadTo(1001.1f, 687.9f, 1001.1f, 570.9f),
                        PathNode.QuadTo(1001.1f, 454.8f, 943.0f, 356.3f),
                        PathNode.QuadTo(885.0f, 257.9f, 786.5f, 199.8f),
                        PathNode.QuadTo(688.0f, 141.8f, 571.9f, 141.8f),
                        PathNode.QuadTo(455.0f, 141.8f, 356.5f, 199.8f),
                        PathNode.QuadTo(258.0f, 257.9f, 200.4f, 356.3f),
                        PathNode.QuadTo(142.8f, 454.8f, 142.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(804.5f, 437.6f),
                        PathNode.LineTo(709.5f, 492.6f),
                        PathNode.QuadTo(703.1f, 497.2f, 693.9f, 497.2f),
                        PathNode.QuadTo(684.6f, 497.2f, 677.3f, 492.6f),
                        PathNode.LineTo(642.3f, 473.4f),
                        PathNode.QuadTo(628.6f, 466.0f, 612.8f, 466.6f),
                        PathNode.QuadTo(597.0f, 467.2f, 582.5f, 475.6f),
                        PathNode.QuadTo(541.5f, 500.4f, 521.8f, 522.1f),
                        PathNode.QuadTo(499.5f, 545.4f, 475.6f, 582.4f),
                        PathNode.QuadTo(467.2f, 596.1f, 466.6f, 611.9f),
                        PathNode.QuadTo(466.0f, 627.8f, 473.4f, 641.2f),
                        PathNode.LineTo(492.7f, 676.3f),
                        PathNode.QuadTo(497.3f, 684.8f, 497.3f, 692.5f),
                        PathNode.QuadTo(497.3f, 700.2f, 492.4f, 709.7f),
                        PathNode.LineTo(438.6f, 803.5f),
                        PathNode.QuadTo(432.1f, 814.7f, 419.3f, 819.4f),
                        PathNode.QuadTo(406.5f, 824.0f, 395.3f, 817.6f),
                        PathNode.LineTo(358.3f, 795.6f),
                        PathNode.QuadTo(332.3f, 780.1f, 325.1f, 749.0f),
                        PathNode.QuadTo(317.8f, 717.9f, 328.6f, 684.8f),
                        PathNode.QuadTo(348.4f, 626.0f, 384.7f, 569.5f),
                        PathNode.QuadTo(421.0f, 512.9f, 466.9f, 466.9f),
                        PathNode.QuadTo(512.9f, 421.0f, 569.0f, 384.3f),
                        PathNode.QuadTo(625.2f, 347.6f, 684.8f, 327.6f),
                        PathNode.QuadTo(713.1f, 317.8f, 745.2f, 323.6f),
                        PathNode.QuadTo(777.3f, 329.4f, 792.6f, 353.5f),
                        PathNode.LineTo(817.2f, 392.9f),
                        PathNode.QuadTo(823.7f, 404.1f, 819.7f, 417.5f),
                        PathNode.QuadTo(815.7f, 431.0f, 804.5f, 437.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerNormal!!
    }

private var _answerNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Answer: ImageVector
    get() {
        if (_answerRegular != null) return _answerRegular!!
        _answerRegular = ImageVector.Builder(
            name = "Answer.Glass.Regular",
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
                        PathNode.QuadTo(996.9f, 455.9f, 939.4f, 358.4f),
                        PathNode.QuadTo(881.9f, 260.9f, 784.4f, 203.4f),
                        PathNode.QuadTo(686.9f, 145.9f, 571.9f, 145.9f),
                        PathNode.QuadTo(455.9f, 145.9f, 358.4f, 203.4f),
                        PathNode.QuadTo(260.9f, 260.9f, 203.9f, 358.4f),
                        PathNode.QuadTo(146.9f, 455.9f, 146.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(805.9f, 439.9f),
                        PathNode.LineTo(710.9f, 494.9f),
                        PathNode.QuadTo(703.9f, 499.9f, 693.9f, 499.9f),
                        PathNode.QuadTo(683.9f, 499.9f, 675.9f, 494.9f),
                        PathNode.LineTo(640.9f, 475.9f),
                        PathNode.QuadTo(627.9f, 468.9f, 612.9f, 469.4f),
                        PathNode.QuadTo(597.9f, 469.9f, 583.9f, 477.9f),
                        PathNode.QuadTo(542.9f, 502.9f, 523.9f, 523.9f),
                        PathNode.QuadTo(500.9f, 547.9f, 477.9f, 583.9f),
                        PathNode.QuadTo(469.9f, 596.9f, 469.4f, 611.9f),
                        PathNode.QuadTo(468.9f, 626.9f, 475.9f, 639.9f),
                        PathNode.LineTo(494.9f, 674.9f),
                        PathNode.QuadTo(499.9f, 683.9f, 499.9f, 692.4f),
                        PathNode.QuadTo(499.9f, 700.9f, 494.9f, 710.9f),
                        PathNode.LineTo(440.9f, 804.9f),
                        PathNode.QuadTo(433.9f, 816.9f, 419.9f, 821.9f),
                        PathNode.QuadTo(405.9f, 826.9f, 393.9f, 819.9f),
                        PathNode.LineTo(356.9f, 797.9f),
                        PathNode.QuadTo(329.9f, 781.9f, 322.4f, 749.9f),
                        PathNode.QuadTo(314.9f, 717.9f, 325.9f, 683.9f),
                        PathNode.QuadTo(345.9f, 624.9f, 382.4f, 567.9f),
                        PathNode.QuadTo(418.9f, 510.9f, 464.9f, 464.9f),
                        PathNode.QuadTo(510.9f, 418.9f, 567.4f, 381.9f),
                        PathNode.QuadTo(623.9f, 344.9f, 683.9f, 324.9f),
                        PathNode.QuadTo(712.9f, 314.9f, 745.9f, 320.9f),
                        PathNode.QuadTo(778.9f, 326.9f, 794.9f, 351.9f),
                        PathNode.LineTo(819.9f, 391.9f),
                        PathNode.QuadTo(826.9f, 403.9f, 822.4f, 418.4f),
                        PathNode.QuadTo(817.9f, 432.9f, 805.9f, 439.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerRegular!!
    }

private var _answerRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Answer: ImageVector
    get() {
        if (_answerMedium != null) return _answerMedium!!
        _answerMedium = ImageVector.Builder(
            name = "Answer.Glass.Medium",
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
                        PathNode.QuadTo(950.9f, 949.9f, 832.0f, 1020.0f),
                        PathNode.QuadTo(713.1f, 1090.0f, 571.9f, 1090.0f),
                        PathNode.QuadTo(430.8f, 1090.0f, 311.9f, 1020.0f),
                        PathNode.QuadTo(193.0f, 949.9f, 122.9f, 831.0f),
                        PathNode.QuadTo(52.8f, 712.1f, 52.8f, 570.9f),
                        PathNode.QuadTo(52.8f, 429.8f, 122.9f, 310.9f),
                        PathNode.QuadTo(193.0f, 192.0f, 311.9f, 122.4f),
                        PathNode.QuadTo(430.8f, 52.8f, 571.9f, 52.8f),
                        PathNode.QuadTo(713.1f, 52.8f, 832.0f, 122.4f),
                        PathNode.QuadTo(950.9f, 192.0f, 1020.5f, 310.9f),
                        PathNode.QuadTo(1090.0f, 429.8f, 1090.0f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(155.0f, 570.9f),
                        PathNode.QuadTo(155.0f, 684.8f, 211.0f, 780.4f),
                        PathNode.QuadTo(266.9f, 876.0f, 362.5f, 931.9f),
                        PathNode.QuadTo(458.1f, 987.8f, 571.9f, 987.8f),
                        PathNode.QuadTo(684.8f, 987.8f, 780.5f, 931.9f),
                        PathNode.QuadTo(876.3f, 876.0f, 932.5f, 780.4f),
                        PathNode.QuadTo(988.8f, 684.8f, 988.8f, 570.9f),
                        PathNode.QuadTo(988.8f, 458.1f, 932.5f, 362.3f),
                        PathNode.QuadTo(876.3f, 266.6f, 780.5f, 210.3f),
                        PathNode.QuadTo(684.8f, 154.0f, 571.9f, 154.0f),
                        PathNode.QuadTo(458.1f, 154.0f, 362.5f, 210.3f),
                        PathNode.QuadTo(266.9f, 266.6f, 211.0f, 362.3f),
                        PathNode.QuadTo(155.0f, 458.1f, 155.0f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(808.6f, 444.5f),
                        PathNode.LineTo(713.6f, 499.5f),
                        PathNode.QuadTo(705.3f, 505.3f, 693.8f, 505.3f),
                        PathNode.QuadTo(682.3f, 505.3f, 673.2f, 499.5f),
                        PathNode.LineTo(638.2f, 480.8f),
                        PathNode.QuadTo(626.6f, 474.3f, 613.1f, 474.8f),
                        PathNode.QuadTo(599.6f, 475.3f, 586.6f, 482.5f),
                        PathNode.QuadTo(545.6f, 507.8f, 528.0f, 527.4f),
                        PathNode.QuadTo(504.4f, 552.3f, 482.5f, 586.9f),
                        PathNode.QuadTo(475.3f, 598.6f, 475.0f, 612.2f),
                        PathNode.QuadTo(474.6f, 625.8f, 480.8f, 637.5f),
                        PathNode.LineTo(499.5f, 672.2f),
                        PathNode.QuadTo(505.3f, 682.6f, 505.3f, 692.6f),
                        PathNode.QuadTo(505.3f, 702.6f, 499.5f, 713.6f),
                        PathNode.LineTo(445.5f, 807.6f),
                        PathNode.QuadTo(437.4f, 821.8f, 421.1f, 827.2f),
                        PathNode.QuadTo(404.8f, 832.6f, 391.2f, 824.5f),
                        PathNode.LineTo(354.2f, 802.5f),
                        PathNode.QuadTo(325.3f, 785.4f, 317.3f, 751.7f),
                        PathNode.QuadTo(309.3f, 717.9f, 320.8f, 682.3f),
                        PathNode.QuadTo(340.8f, 622.5f, 377.8f, 564.8f),
                        PathNode.QuadTo(414.9f, 507.1f, 461.1f, 461.1f),
                        PathNode.QuadTo(507.1f, 414.9f, 564.5f, 377.5f),
                        PathNode.QuadTo(621.8f, 340.1f, 682.3f, 319.8f),
                        PathNode.QuadTo(712.7f, 309.3f, 747.4f, 315.8f),
                        PathNode.QuadTo(782.2f, 322.3f, 799.5f, 349.0f),
                        PathNode.LineTo(824.5f, 389.0f),
                        PathNode.QuadTo(832.6f, 402.6f, 827.7f, 419.4f),
                        PathNode.QuadTo(822.8f, 436.2f, 808.6f, 444.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerMedium!!
    }

private var _answerMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Answer: ImageVector
    get() {
        if (_answerDemibold != null) return _answerDemibold!!
        _answerDemibold = ImageVector.Builder(
            name = "Answer.Glass.Demibold",
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
                        PathNode.QuadTo(955.0f, 954.0f, 834.8f, 1024.9f),
                        PathNode.QuadTo(714.6f, 1095.7f, 571.9f, 1095.7f),
                        PathNode.QuadTo(429.3f, 1095.7f, 309.0f, 1024.9f),
                        PathNode.QuadTo(188.8f, 954.0f, 118.0f, 833.8f),
                        PathNode.QuadTo(47.1f, 713.6f, 47.1f, 570.9f),
                        PathNode.QuadTo(47.1f, 428.3f, 118.0f, 308.0f),
                        PathNode.QuadTo(188.8f, 187.8f, 309.0f, 117.5f),
                        PathNode.QuadTo(429.3f, 47.1f, 571.9f, 47.1f),
                        PathNode.QuadTo(714.6f, 47.1f, 834.8f, 117.5f),
                        PathNode.QuadTo(955.0f, 187.8f, 1025.4f, 308.0f),
                        PathNode.QuadTo(1095.7f, 428.3f, 1095.7f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(160.7f, 570.9f),
                        PathNode.QuadTo(160.7f, 683.3f, 215.9f, 777.5f),
                        PathNode.QuadTo(271.0f, 871.8f, 365.3f, 927.0f),
                        PathNode.QuadTo(459.6f, 982.1f, 571.9f, 982.1f),
                        PathNode.QuadTo(683.3f, 982.1f, 777.8f, 927.0f),
                        PathNode.QuadTo(872.3f, 871.8f, 927.7f, 777.5f),
                        PathNode.QuadTo(983.1f, 683.3f, 983.1f, 570.9f),
                        PathNode.QuadTo(983.1f, 459.6f, 927.7f, 365.1f),
                        PathNode.QuadTo(872.3f, 270.6f, 777.8f, 215.1f),
                        PathNode.QuadTo(683.3f, 159.7f, 571.9f, 159.7f),
                        PathNode.QuadTo(459.6f, 159.7f, 365.3f, 215.1f),
                        PathNode.QuadTo(271.0f, 270.6f, 215.9f, 365.1f),
                        PathNode.QuadTo(160.7f, 459.6f, 160.7f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(810.5f, 447.7f),
                        PathNode.LineTo(715.5f, 502.7f),
                        PathNode.QuadTo(706.2f, 509.1f, 693.7f, 509.1f),
                        PathNode.QuadTo(681.2f, 509.1f, 671.3f, 502.7f),
                        PathNode.LineTo(636.3f, 484.2f),
                        PathNode.QuadTo(625.6f, 478.1f, 613.2f, 478.6f),
                        PathNode.QuadTo(600.7f, 479.1f, 588.5f, 485.7f),
                        PathNode.QuadTo(547.5f, 511.2f, 530.8f, 529.9f),
                        PathNode.QuadTo(506.9f, 555.3f, 485.7f, 589.0f),
                        PathNode.QuadTo(479.1f, 599.7f, 478.8f, 612.4f),
                        PathNode.QuadTo(478.6f, 625.1f, 484.2f, 635.8f),
                        PathNode.LineTo(502.7f, 670.3f),
                        PathNode.QuadTo(509.1f, 681.6f, 509.1f, 692.7f),
                        PathNode.QuadTo(509.1f, 703.7f, 502.7f, 715.5f),
                        PathNode.LineTo(448.7f, 809.5f),
                        PathNode.QuadTo(439.9f, 825.2f, 422.0f, 830.9f),
                        PathNode.QuadTo(404.1f, 836.6f, 389.3f, 827.7f),
                        PathNode.LineTo(352.3f, 805.7f),
                        PathNode.QuadTo(322.1f, 787.9f, 313.7f, 752.9f),
                        PathNode.QuadTo(305.3f, 717.9f, 317.2f, 681.2f),
                        PathNode.QuadTo(337.2f, 620.8f, 374.6f, 562.6f),
                        PathNode.QuadTo(412.0f, 504.5f, 458.5f, 458.5f),
                        PathNode.QuadTo(504.5f, 412.0f, 562.4f, 374.3f),
                        PathNode.QuadTo(620.3f, 336.7f, 681.2f, 316.2f),
                        PathNode.QuadTo(712.5f, 305.3f, 748.5f, 312.2f),
                        PathNode.QuadTo(784.4f, 319.1f, 802.7f, 346.9f),
                        PathNode.LineTo(827.7f, 386.9f),
                        PathNode.QuadTo(836.6f, 401.6f, 831.4f, 420.0f),
                        PathNode.QuadTo(826.2f, 438.4f, 810.5f, 447.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerDemibold!!
    }

private var _answerDemibold: ImageVector? = null
