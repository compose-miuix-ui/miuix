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

val MiuixIcons.Glass.MoreCircle: ImageVector
    get() = MiuixIcons.Glass.Regular.MoreCircle

val MiuixIcons.Glass.Light.MoreCircle: ImageVector
    get() {
        if (_moreCircleLight != null) return _moreCircleLight!!
        _moreCircleLight = ImageVector.Builder(
            name = "MoreCircle.Glass.Light",
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
                        PathNode.MoveTo(402.6f, 570.9f),
                        PathNode.QuadTo(402.6f, 588.2f, 390.5f, 600.4f),
                        PathNode.QuadTo(378.5f, 612.6f, 361.9f, 612.6f),
                        PathNode.QuadTo(345.3f, 612.6f, 332.8f, 600.6f),
                        PathNode.QuadTo(320.3f, 588.6f, 320.3f, 571.0f),
                        PathNode.QuadTo(320.3f, 554.3f, 332.4f, 542.3f),
                        PathNode.QuadTo(344.6f, 530.3f, 362.1f, 530.3f),
                        PathNode.QuadTo(379.6f, 530.3f, 391.1f, 542.3f),
                        PathNode.QuadTo(402.6f, 554.3f, 402.6f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(612.6f, 570.9f),
                        PathNode.QuadTo(612.6f, 588.2f, 600.5f, 600.4f),
                        PathNode.QuadTo(588.5f, 612.6f, 571.9f, 612.6f),
                        PathNode.QuadTo(555.3f, 612.6f, 542.8f, 600.6f),
                        PathNode.QuadTo(530.3f, 588.6f, 530.3f, 571.0f),
                        PathNode.QuadTo(530.3f, 554.3f, 542.4f, 542.3f),
                        PathNode.QuadTo(554.6f, 530.3f, 572.1f, 530.3f),
                        PathNode.QuadTo(589.6f, 530.3f, 601.1f, 542.3f),
                        PathNode.QuadTo(612.6f, 554.3f, 612.6f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(822.6f, 570.9f),
                        PathNode.QuadTo(822.6f, 588.2f, 810.6f, 600.4f),
                        PathNode.QuadTo(798.5f, 612.6f, 782.0f, 612.6f),
                        PathNode.QuadTo(764.7f, 612.6f, 752.5f, 600.6f),
                        PathNode.QuadTo(740.3f, 588.6f, 740.3f, 570.9f),
                        PathNode.QuadTo(740.3f, 553.3f, 752.3f, 541.8f),
                        PathNode.QuadTo(764.3f, 530.3f, 781.9f, 530.3f),
                        PathNode.QuadTo(799.6f, 530.3f, 811.1f, 542.3f),
                        PathNode.QuadTo(822.6f, 554.3f, 822.6f, 570.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreCircleLight!!
    }

private var _moreCircleLight: ImageVector? = null

val MiuixIcons.Glass.Normal.MoreCircle: ImageVector
    get() {
        if (_moreCircleNormal != null) return _moreCircleNormal!!
        _moreCircleNormal = ImageVector.Builder(
            name = "MoreCircle.Glass.Normal",
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
                        PathNode.MoveTo(411.8f, 570.9f),
                        PathNode.QuadTo(411.8f, 592.1f, 397.0f, 606.9f),
                        PathNode.QuadTo(382.2f, 621.8f, 361.9f, 621.8f),
                        PathNode.QuadTo(341.6f, 621.8f, 326.3f, 607.0f),
                        PathNode.QuadTo(311.1f, 592.3f, 311.1f, 571.0f),
                        PathNode.QuadTo(311.1f, 550.6f, 325.9f, 535.8f),
                        PathNode.QuadTo(340.7f, 521.1f, 362.0f, 521.1f),
                        PathNode.QuadTo(383.3f, 521.1f, 397.5f, 535.9f),
                        PathNode.QuadTo(411.8f, 550.6f, 411.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(621.8f, 570.9f),
                        PathNode.QuadTo(621.8f, 592.1f, 607.0f, 606.9f),
                        PathNode.QuadTo(592.2f, 621.8f, 571.9f, 621.8f),
                        PathNode.QuadTo(551.6f, 621.8f, 536.3f, 607.0f),
                        PathNode.QuadTo(521.1f, 592.3f, 521.1f, 571.0f),
                        PathNode.QuadTo(521.1f, 550.6f, 535.9f, 535.8f),
                        PathNode.QuadTo(550.7f, 521.1f, 572.0f, 521.1f),
                        PathNode.QuadTo(593.3f, 521.1f, 607.5f, 535.9f),
                        PathNode.QuadTo(621.8f, 550.6f, 621.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(831.8f, 570.9f),
                        PathNode.QuadTo(831.8f, 592.1f, 817.0f, 606.9f),
                        PathNode.QuadTo(802.2f, 621.8f, 782.0f, 621.8f),
                        PathNode.QuadTo(760.7f, 621.8f, 745.9f, 607.0f),
                        PathNode.QuadTo(731.1f, 592.3f, 731.1f, 570.9f),
                        PathNode.QuadTo(731.1f, 549.6f, 745.8f, 535.3f),
                        PathNode.QuadTo(760.6f, 521.1f, 781.9f, 521.1f),
                        PathNode.QuadTo(803.3f, 521.1f, 817.5f, 535.9f),
                        PathNode.QuadTo(831.8f, 550.6f, 831.8f, 570.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreCircleNormal!!
    }

private var _moreCircleNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.MoreCircle: ImageVector
    get() {
        if (_moreCircleRegular != null) return _moreCircleRegular!!
        _moreCircleRegular = ImageVector.Builder(
            name = "MoreCircle.Glass.Regular",
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
                        PathNode.MoveTo(415.9f, 570.9f),
                        PathNode.QuadTo(415.9f, 593.9f, 399.9f, 609.9f),
                        PathNode.QuadTo(383.9f, 625.9f, 361.9f, 625.9f),
                        PathNode.QuadTo(339.9f, 625.9f, 323.4f, 609.9f),
                        PathNode.QuadTo(306.9f, 593.9f, 306.9f, 570.9f),
                        PathNode.QuadTo(306.9f, 548.9f, 322.9f, 532.9f),
                        PathNode.QuadTo(338.9f, 516.9f, 361.9f, 516.9f),
                        PathNode.QuadTo(384.9f, 516.9f, 400.4f, 532.9f),
                        PathNode.QuadTo(415.9f, 548.9f, 415.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(625.9f, 570.9f),
                        PathNode.QuadTo(625.9f, 593.9f, 609.9f, 609.9f),
                        PathNode.QuadTo(593.9f, 625.9f, 571.9f, 625.9f),
                        PathNode.QuadTo(549.9f, 625.9f, 533.4f, 609.9f),
                        PathNode.QuadTo(516.9f, 593.9f, 516.9f, 570.9f),
                        PathNode.QuadTo(516.9f, 548.9f, 532.9f, 532.9f),
                        PathNode.QuadTo(548.9f, 516.9f, 571.9f, 516.9f),
                        PathNode.QuadTo(594.9f, 516.9f, 610.4f, 532.9f),
                        PathNode.QuadTo(625.9f, 548.9f, 625.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(835.9f, 570.9f),
                        PathNode.QuadTo(835.9f, 593.9f, 819.9f, 609.9f),
                        PathNode.QuadTo(803.9f, 625.9f, 781.9f, 625.9f),
                        PathNode.QuadTo(758.9f, 625.9f, 742.9f, 609.9f),
                        PathNode.QuadTo(726.9f, 593.9f, 726.9f, 570.9f),
                        PathNode.QuadTo(726.9f, 547.9f, 742.9f, 532.4f),
                        PathNode.QuadTo(758.9f, 516.9f, 781.9f, 516.9f),
                        PathNode.QuadTo(804.9f, 516.9f, 820.4f, 532.9f),
                        PathNode.QuadTo(835.9f, 548.9f, 835.9f, 570.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreCircleRegular!!
    }

private var _moreCircleRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.MoreCircle: ImageVector
    get() {
        if (_moreCircleMedium != null) return _moreCircleMedium!!
        _moreCircleMedium = ImageVector.Builder(
            name = "MoreCircle.Glass.Medium",
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
                        PathNode.MoveTo(424.0f, 571.0f),
                        PathNode.QuadTo(424.0f, 597.4f, 405.5f, 615.7f),
                        PathNode.QuadTo(386.9f, 634.0f, 361.8f, 634.0f),
                        PathNode.QuadTo(336.7f, 634.0f, 317.8f, 615.6f),
                        PathNode.QuadTo(298.8f, 597.2f, 298.8f, 571.0f),
                        PathNode.QuadTo(298.8f, 546.0f, 317.1f, 527.4f),
                        PathNode.QuadTo(335.5f, 508.8f, 361.8f, 508.8f),
                        PathNode.QuadTo(388.2f, 508.8f, 406.1f, 527.2f),
                        PathNode.QuadTo(424.0f, 545.7f, 424.0f, 571.0f),
                        PathNode.Close,
                        PathNode.MoveTo(634.0f, 571.0f),
                        PathNode.QuadTo(634.0f, 597.4f, 615.5f, 615.7f),
                        PathNode.QuadTo(596.9f, 634.0f, 571.8f, 634.0f),
                        PathNode.QuadTo(546.7f, 634.0f, 527.8f, 615.6f),
                        PathNode.QuadTo(508.8f, 597.2f, 508.8f, 571.0f),
                        PathNode.QuadTo(508.8f, 546.0f, 527.1f, 527.4f),
                        PathNode.QuadTo(545.5f, 508.8f, 571.8f, 508.8f),
                        PathNode.QuadTo(598.2f, 508.8f, 616.1f, 527.2f),
                        PathNode.QuadTo(634.0f, 545.7f, 634.0f, 571.0f),
                        PathNode.Close,
                        PathNode.MoveTo(844.0f, 571.0f),
                        PathNode.QuadTo(844.0f, 597.4f, 825.5f, 615.7f),
                        PathNode.QuadTo(806.9f, 634.0f, 781.9f, 634.0f),
                        PathNode.QuadTo(755.5f, 634.0f, 737.2f, 615.6f),
                        PathNode.QuadTo(718.8f, 597.2f, 718.8f, 571.1f),
                        PathNode.QuadTo(718.8f, 545.0f, 737.0f, 526.9f),
                        PathNode.QuadTo(755.1f, 508.8f, 781.9f, 508.8f),
                        PathNode.QuadTo(808.2f, 508.8f, 826.1f, 527.2f),
                        PathNode.QuadTo(844.0f, 545.7f, 844.0f, 571.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreCircleMedium!!
    }

private var _moreCircleMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.MoreCircle: ImageVector
    get() {
        if (_moreCircleDemibold != null) return _moreCircleDemibold!!
        _moreCircleDemibold = ImageVector.Builder(
            name = "MoreCircle.Glass.Demibold",
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
                        PathNode.MoveTo(429.7f, 571.0f),
                        PathNode.QuadTo(429.7f, 599.9f, 409.3f, 619.8f),
                        PathNode.QuadTo(389.0f, 639.7f, 361.7f, 639.7f),
                        PathNode.QuadTo(334.4f, 639.7f, 313.8f, 619.6f),
                        PathNode.QuadTo(293.1f, 599.4f, 293.1f, 571.0f),
                        PathNode.QuadTo(293.1f, 543.9f, 313.1f, 523.5f),
                        PathNode.QuadTo(333.1f, 503.1f, 361.8f, 503.1f),
                        PathNode.QuadTo(390.4f, 503.1f, 410.1f, 523.2f),
                        PathNode.QuadTo(429.7f, 543.4f, 429.7f, 571.0f),
                        PathNode.Close,
                        PathNode.MoveTo(639.7f, 571.0f),
                        PathNode.QuadTo(639.7f, 599.9f, 619.3f, 619.8f),
                        PathNode.QuadTo(599.0f, 639.7f, 571.7f, 639.7f),
                        PathNode.QuadTo(544.4f, 639.7f, 523.8f, 619.6f),
                        PathNode.QuadTo(503.1f, 599.4f, 503.1f, 571.0f),
                        PathNode.QuadTo(503.1f, 543.9f, 523.1f, 523.5f),
                        PathNode.QuadTo(543.1f, 503.1f, 571.8f, 503.1f),
                        PathNode.QuadTo(600.4f, 503.1f, 620.1f, 523.2f),
                        PathNode.QuadTo(639.7f, 543.4f, 639.7f, 571.0f),
                        PathNode.Close,
                        PathNode.MoveTo(849.7f, 571.0f),
                        PathNode.QuadTo(849.7f, 599.9f, 829.3f, 619.8f),
                        PathNode.QuadTo(809.0f, 639.7f, 781.9f, 639.7f),
                        PathNode.QuadTo(753.2f, 639.7f, 733.2f, 619.6f),
                        PathNode.QuadTo(713.1f, 599.4f, 713.1f, 571.2f),
                        PathNode.QuadTo(713.1f, 542.9f, 732.8f, 523.0f),
                        PathNode.QuadTo(752.5f, 503.1f, 781.9f, 503.1f),
                        PathNode.QuadTo(810.4f, 503.1f, 830.1f, 523.2f),
                        PathNode.QuadTo(849.7f, 543.4f, 849.7f, 571.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _moreCircleDemibold!!
    }

private var _moreCircleDemibold: ImageVector? = null
