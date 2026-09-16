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

val MiuixIcons.Glass.Timer: ImageVector
    get() = MiuixIcons.Glass.Regular.Timer

val MiuixIcons.Glass.Light.Timer: ImageVector
    get() {
        if (_timerLight != null) return _timerLight!!
        _timerLight = ImageVector.Builder(
            name = "Timer.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(885.9f, 68.2f),
                        PathNode.QuadTo(902.4f, 87.7f, 903.4f, 112.3f),
                        PathNode.QuadTo(904.5f, 136.9f, 895.9f, 198.1f),
                        PathNode.QuadTo(885.4f, 281.3f, 851.0f, 348.1f),
                        PathNode.QuadTo(816.6f, 414.9f, 757.9f, 467.0f),
                        PathNode.LineTo(744.5f, 479.0f),
                        PathNode.QuadTo(706.9f, 512.0f, 692.7f, 525.9f),
                        PathNode.QuadTo(678.5f, 539.9f, 675.0f, 550.1f),
                        PathNode.QuadTo(670.2f, 563.8f, 670.2f, 571.4f),
                        PathNode.QuadTo(670.2f, 579.1f, 675.0f, 591.8f),
                        PathNode.QuadTo(679.5f, 605.9f, 710.3f, 633.7f),
                        PathNode.LineTo(757.9f, 675.9f),
                        PathNode.QuadTo(816.6f, 728.0f, 851.0f, 794.5f),
                        PathNode.QuadTo(885.4f, 861.0f, 895.9f, 944.8f),
                        PathNode.QuadTo(904.5f, 1006.0f, 903.4f, 1030.6f),
                        PathNode.QuadTo(902.4f, 1055.2f, 885.9f, 1073.7f),
                        PathNode.QuadTo(875.2f, 1087.0f, 857.6f, 1092.9f),
                        PathNode.QuadTo(840.0f, 1098.8f, 815.2f, 1100.0f),
                        PathNode.QuadTo(790.4f, 1101.3f, 739.2f, 1101.3f),
                        PathNode.LineTo(404.5f, 1101.3f),
                        PathNode.QuadTo(352.8f, 1101.3f, 327.7f, 1100.0f),
                        PathNode.QuadTo(302.6f, 1098.8f, 285.3f, 1092.9f),
                        PathNode.QuadTo(267.9f, 1087.0f, 257.2f, 1073.7f),
                        PathNode.QuadTo(240.8f, 1054.2f, 239.5f, 1029.1f),
                        PathNode.QuadTo(238.2f, 1004.0f, 246.7f, 944.8f),
                        PathNode.QuadTo(258.3f, 860.5f, 292.9f, 793.8f),
                        PathNode.QuadTo(327.6f, 727.0f, 386.7f, 675.9f),
                        PathNode.QuadTo(430.3f, 637.8f, 447.5f, 620.8f),
                        PathNode.QuadTo(464.7f, 603.9f, 468.6f, 593.2f),
                        PathNode.QuadTo(473.5f, 579.1f, 473.5f, 571.4f),
                        PathNode.QuadTo(473.5f, 563.8f, 468.6f, 550.1f),
                        PathNode.QuadTo(465.7f, 541.4f, 453.2f, 527.7f),
                        PathNode.QuadTo(440.6f, 514.0f, 417.0f, 493.3f),
                        PathNode.LineTo(386.7f, 467.0f),
                        PathNode.QuadTo(327.6f, 414.9f, 292.9f, 348.1f),
                        PathNode.QuadTo(258.3f, 281.3f, 246.7f, 198.1f),
                        PathNode.QuadTo(238.2f, 137.9f, 239.5f, 112.8f),
                        PathNode.QuadTo(240.8f, 87.7f, 257.2f, 68.2f),
                        PathNode.QuadTo(267.9f, 55.9f, 285.3f, 50.0f),
                        PathNode.QuadTo(302.6f, 44.1f, 327.7f, 42.8f),
                        PathNode.QuadTo(352.8f, 41.5f, 404.5f, 41.5f),
                        PathNode.LineTo(739.2f, 41.5f),
                        PathNode.QuadTo(790.4f, 41.5f, 815.2f, 42.8f),
                        PathNode.QuadTo(840.0f, 44.1f, 857.6f, 50.0f),
                        PathNode.QuadTo(875.2f, 55.9f, 885.9f, 68.2f),
                        PathNode.Close,
                        PathNode.MoveTo(532.0f, 647.1f),
                        PathNode.QuadTo(456.0f, 688.2f, 401.3f, 752.9f),
                        PathNode.QuadTo(346.6f, 817.5f, 325.6f, 899.4f),
                        PathNode.QuadTo(319.3f, 924.5f, 326.3f, 937.3f),
                        PathNode.QuadTo(333.4f, 950.1f, 356.3f, 950.1f),
                        PathNode.LineTo(783.3f, 950.1f),
                        PathNode.QuadTo(807.4f, 950.1f, 815.7f, 938.2f),
                        PathNode.QuadTo(823.9f, 926.2f, 817.5f, 900.4f),
                        PathNode.QuadTo(797.8f, 820.8f, 743.4f, 754.7f),
                        PathNode.QuadTo(688.9f, 688.5f, 612.6f, 647.1f),
                        PathNode.QuadTo(587.9f, 633.9f, 572.5f, 633.9f),
                        PathNode.QuadTo(557.2f, 633.9f, 532.0f, 647.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerLight!!
    }

private var _timerLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Timer: ImageVector
    get() {
        if (_timerNormal != null) return _timerNormal!!
        _timerNormal = ImageVector.Builder(
            name = "Timer.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(892.0f, 64.2f),
                        PathNode.QuadTo(910.9f, 86.1f, 912.2f, 112.7f),
                        PathNode.QuadTo(913.6f, 139.3f, 904.7f, 201.8f),
                        PathNode.QuadTo(893.9f, 286.2f, 858.7f, 354.5f),
                        PathNode.QuadTo(823.6f, 422.8f, 764.0f, 475.5f),
                        PathNode.LineTo(750.9f, 487.5f),
                        PathNode.QuadTo(715.7f, 517.8f, 701.7f, 531.4f),
                        PathNode.QuadTo(687.6f, 545.1f, 684.5f, 553.8f),
                        PathNode.QuadTo(680.2f, 565.6f, 680.2f, 571.4f),
                        PathNode.QuadTo(680.2f, 577.3f, 684.5f, 588.1f),
                        PathNode.QuadTo(688.6f, 600.1f, 718.2f, 626.6f),
                        PathNode.LineTo(764.0f, 667.3f),
                        PathNode.QuadTo(823.6f, 720.0f, 858.7f, 787.9f),
                        PathNode.QuadTo(893.9f, 855.8f, 904.7f, 941.1f),
                        PathNode.QuadTo(913.6f, 1003.5f, 912.2f, 1030.1f),
                        PathNode.QuadTo(910.9f, 1056.7f, 892.0f, 1077.6f),
                        PathNode.QuadTo(879.7f, 1092.8f, 860.5f, 1099.4f),
                        PathNode.QuadTo(841.2f, 1106.1f, 815.9f, 1107.5f),
                        PathNode.QuadTo(790.6f, 1109.0f, 738.2f, 1109.0f),
                        PathNode.LineTo(405.4f, 1109.0f),
                        PathNode.QuadTo(352.8f, 1109.0f, 327.1f, 1107.5f),
                        PathNode.QuadTo(301.3f, 1106.1f, 282.2f, 1099.4f),
                        PathNode.QuadTo(263.0f, 1092.8f, 250.7f, 1077.6f),
                        PathNode.QuadTo(232.5f, 1055.7f, 230.7f, 1028.6f),
                        PathNode.QuadTo(228.9f, 1001.5f, 237.8f, 941.1f),
                        PathNode.QuadTo(249.7f, 855.7f, 285.2f, 787.3f),
                        PathNode.QuadTo(320.8f, 719.0f, 380.6f, 667.3f),
                        PathNode.QuadTo(421.7f, 631.3f, 438.7f, 614.7f),
                        PathNode.QuadTo(455.8f, 598.1f, 459.1f, 589.2f),
                        PathNode.QuadTo(463.4f, 577.3f, 463.4f, 571.4f),
                        PathNode.QuadTo(463.4f, 565.6f, 459.1f, 553.8f),
                        PathNode.QuadTo(456.8f, 546.9f, 443.9f, 533.4f),
                        PathNode.QuadTo(431.1f, 519.8f, 410.0f, 501.0f),
                        PathNode.LineTo(380.6f, 475.5f),
                        PathNode.QuadTo(320.8f, 422.8f, 285.2f, 354.5f),
                        PathNode.QuadTo(249.7f, 286.2f, 237.8f, 201.8f),
                        PathNode.QuadTo(228.9f, 140.3f, 230.7f, 113.2f),
                        PathNode.QuadTo(232.5f, 86.1f, 250.7f, 64.2f),
                        PathNode.QuadTo(263.0f, 50.1f, 282.2f, 43.4f),
                        PathNode.QuadTo(301.3f, 36.8f, 327.1f, 35.3f),
                        PathNode.QuadTo(352.8f, 33.9f, 405.4f, 33.9f),
                        PathNode.LineTo(738.2f, 33.9f),
                        PathNode.QuadTo(790.6f, 33.9f, 815.9f, 35.3f),
                        PathNode.QuadTo(841.2f, 36.8f, 860.5f, 43.4f),
                        PathNode.QuadTo(879.7f, 50.1f, 892.0f, 64.2f),
                        PathNode.Close,
                        PathNode.MoveTo(536.0f, 653.5f),
                        PathNode.QuadTo(462.7f, 692.5f, 409.5f, 755.3f),
                        PathNode.QuadTo(356.3f, 818.2f, 335.3f, 896.7f),
                        PathNode.QuadTo(329.9f, 916.9f, 337.3f, 928.5f),
                        PathNode.QuadTo(344.6f, 940.0f, 366.9f, 940.0f),
                        PathNode.LineTo(774.7f, 940.0f),
                        PathNode.QuadTo(796.6f, 940.0f, 805.1f, 928.4f),
                        PathNode.QuadTo(813.5f, 916.7f, 807.4f, 894.9f),
                        PathNode.QuadTo(786.8f, 819.8f, 734.0f, 755.9f),
                        PathNode.QuadTo(681.2f, 691.9f, 608.6f, 653.5f),
                        PathNode.QuadTo(585.7f, 641.8f, 572.3f, 641.8f),
                        PathNode.QuadTo(558.9f, 641.8f, 536.0f, 653.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerNormal!!
    }

private var _timerNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Timer: ImageVector
    get() {
        if (_timerRegular != null) return _timerRegular!!
        _timerRegular = ImageVector.Builder(
            name = "Timer.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(894.8f, 62.4f),
                        PathNode.QuadTo(914.8f, 85.4f, 916.3f, 112.9f),
                        PathNode.QuadTo(917.8f, 140.4f, 908.8f, 203.4f),
                        PathNode.QuadTo(897.8f, 288.4f, 862.3f, 357.4f),
                        PathNode.QuadTo(826.8f, 426.4f, 766.8f, 479.4f),
                        PathNode.LineTo(753.8f, 491.4f),
                        PathNode.QuadTo(719.8f, 520.4f, 705.8f, 533.9f),
                        PathNode.QuadTo(691.8f, 547.4f, 688.8f, 555.4f),
                        PathNode.QuadTo(684.8f, 566.4f, 684.8f, 571.4f),
                        PathNode.QuadTo(684.8f, 576.4f, 688.8f, 586.4f),
                        PathNode.QuadTo(692.8f, 597.4f, 721.8f, 623.4f),
                        PathNode.LineTo(766.8f, 663.4f),
                        PathNode.QuadTo(826.8f, 716.4f, 862.3f, 784.9f),
                        PathNode.QuadTo(897.8f, 853.4f, 908.8f, 939.4f),
                        PathNode.QuadTo(917.8f, 1002.4f, 916.3f, 1029.9f),
                        PathNode.QuadTo(914.8f, 1057.4f, 894.8f, 1079.4f),
                        PathNode.QuadTo(881.8f, 1095.4f, 861.8f, 1102.4f),
                        PathNode.QuadTo(841.8f, 1109.4f, 816.3f, 1110.9f),
                        PathNode.QuadTo(790.8f, 1112.4f, 737.8f, 1112.4f),
                        PathNode.LineTo(405.8f, 1112.4f),
                        PathNode.QuadTo(352.8f, 1112.4f, 326.8f, 1110.9f),
                        PathNode.QuadTo(300.8f, 1109.4f, 280.8f, 1102.4f),
                        PathNode.QuadTo(260.8f, 1095.4f, 247.8f, 1079.4f),
                        PathNode.QuadTo(228.8f, 1056.4f, 226.8f, 1028.4f),
                        PathNode.QuadTo(224.8f, 1000.4f, 233.8f, 939.4f),
                        PathNode.QuadTo(245.8f, 853.4f, 281.8f, 784.4f),
                        PathNode.QuadTo(317.8f, 715.4f, 377.8f, 663.4f),
                        PathNode.QuadTo(417.8f, 628.4f, 434.8f, 611.9f),
                        PathNode.QuadTo(451.8f, 595.4f, 454.8f, 587.4f),
                        PathNode.QuadTo(458.8f, 576.4f, 458.8f, 571.4f),
                        PathNode.QuadTo(458.8f, 566.4f, 454.8f, 555.4f),
                        PathNode.QuadTo(452.8f, 549.4f, 439.8f, 535.9f),
                        PathNode.QuadTo(426.8f, 522.4f, 406.8f, 504.4f),
                        PathNode.LineTo(377.8f, 479.4f),
                        PathNode.QuadTo(317.8f, 426.4f, 281.8f, 357.4f),
                        PathNode.QuadTo(245.8f, 288.4f, 233.8f, 203.4f),
                        PathNode.QuadTo(224.8f, 141.4f, 226.8f, 113.4f),
                        PathNode.QuadTo(228.8f, 85.4f, 247.8f, 62.4f),
                        PathNode.QuadTo(260.8f, 47.4f, 280.8f, 40.4f),
                        PathNode.QuadTo(300.8f, 33.4f, 326.8f, 31.9f),
                        PathNode.QuadTo(352.8f, 30.4f, 405.8f, 30.4f),
                        PathNode.LineTo(737.8f, 30.4f),
                        PathNode.QuadTo(790.8f, 30.4f, 816.3f, 31.9f),
                        PathNode.QuadTo(841.8f, 33.4f, 861.8f, 40.4f),
                        PathNode.QuadTo(881.8f, 47.4f, 894.8f, 62.4f),
                        PathNode.Close,
                        PathNode.MoveTo(537.8f, 656.4f),
                        PathNode.QuadTo(465.8f, 694.4f, 413.3f, 756.4f),
                        PathNode.QuadTo(360.8f, 818.4f, 339.8f, 895.4f),
                        PathNode.QuadTo(334.8f, 913.4f, 342.3f, 924.4f),
                        PathNode.QuadTo(349.8f, 935.4f, 371.8f, 935.4f),
                        PathNode.LineTo(770.8f, 935.4f),
                        PathNode.QuadTo(791.8f, 935.4f, 800.3f, 923.9f),
                        PathNode.QuadTo(808.8f, 912.4f, 802.8f, 892.4f),
                        PathNode.QuadTo(781.8f, 819.4f, 729.8f, 756.4f),
                        PathNode.QuadTo(677.8f, 693.4f, 606.8f, 656.4f),
                        PathNode.QuadTo(584.8f, 645.4f, 572.3f, 645.4f),
                        PathNode.QuadTo(559.8f, 645.4f, 537.8f, 656.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerRegular!!
    }

private var _timerRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Timer: ImageVector
    get() {
        if (_timerMedium != null) return _timerMedium!!
        _timerMedium = ImageVector.Builder(
            name = "Timer.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(899.8f, 58.9f),
                        PathNode.QuadTo(921.7f, 84.1f, 923.5f, 113.7f),
                        PathNode.QuadTo(925.2f, 143.4f, 916.2f, 206.7f),
                        PathNode.QuadTo(905.0f, 293.0f, 868.9f, 363.1f),
                        PathNode.QuadTo(832.9f, 433.2f, 771.8f, 486.7f),
                        PathNode.LineTo(759.1f, 498.5f),
                        PathNode.QuadTo(727.5f, 525.6f, 713.6f, 538.7f),
                        PathNode.QuadTo(699.8f, 551.8f, 696.8f, 558.4f),
                        PathNode.QuadTo(693.6f, 567.2f, 693.6f, 571.4f),
                        PathNode.QuadTo(693.6f, 575.6f, 696.8f, 583.5f),
                        PathNode.QuadTo(700.0f, 592.6f, 727.9f, 617.5f),
                        PathNode.LineTo(771.8f, 656.1f),
                        PathNode.QuadTo(832.9f, 709.7f, 868.9f, 779.4f),
                        PathNode.QuadTo(905.0f, 849.1f, 916.2f, 936.2f),
                        PathNode.QuadTo(925.2f, 999.5f, 923.5f, 1029.0f),
                        PathNode.QuadTo(921.7f, 1058.5f, 899.8f, 1082.9f),
                        PathNode.QuadTo(885.5f, 1100.3f, 864.2f, 1108.0f),
                        PathNode.QuadTo(843.0f, 1115.6f, 816.7f, 1117.3f),
                        PathNode.QuadTo(790.4f, 1118.9f, 736.9f, 1118.9f),
                        PathNode.LineTo(406.5f, 1118.9f),
                        PathNode.QuadTo(352.7f, 1118.9f, 326.0f, 1117.3f),
                        PathNode.QuadTo(299.3f, 1115.6f, 278.0f, 1108.0f),
                        PathNode.QuadTo(256.6f, 1100.3f, 242.3f, 1082.9f),
                        PathNode.QuadTo(221.6f, 1057.8f, 219.5f, 1028.0f),
                        PathNode.QuadTo(217.4f, 998.3f, 226.1f, 936.2f),
                        PathNode.QuadTo(238.4f, 849.1f, 275.1f, 778.9f),
                        PathNode.QuadTo(311.7f, 708.7f, 372.5f, 656.1f),
                        PathNode.QuadTo(409.8f, 623.3f, 426.6f, 607.2f),
                        PathNode.QuadTo(443.3f, 591.1f, 446.6f, 584.2f),
                        PathNode.QuadTo(449.8f, 575.6f, 449.8f, 571.4f),
                        PathNode.QuadTo(449.8f, 567.2f, 446.6f, 558.4f),
                        PathNode.QuadTo(444.8f, 553.2f, 432.5f, 540.9f),
                        PathNode.QuadTo(420.2f, 528.6f, 401.0f, 511.5f),
                        PathNode.LineTo(372.5f, 486.7f),
                        PathNode.QuadTo(311.7f, 433.2f, 275.1f, 363.0f),
                        PathNode.QuadTo(238.4f, 292.8f, 226.1f, 206.7f),
                        PathNode.QuadTo(217.4f, 143.6f, 219.5f, 113.8f),
                        PathNode.QuadTo(221.6f, 84.1f, 242.3f, 58.9f),
                        PathNode.QuadTo(256.6f, 42.3f, 278.0f, 34.8f),
                        PathNode.QuadTo(299.3f, 27.2f, 326.0f, 25.6f),
                        PathNode.QuadTo(352.7f, 23.9f, 406.5f, 23.9f),
                        PathNode.LineTo(736.9f, 23.9f),
                        PathNode.QuadTo(790.4f, 23.9f, 816.7f, 25.6f),
                        PathNode.QuadTo(843.0f, 27.2f, 864.2f, 34.8f),
                        PathNode.QuadTo(885.5f, 42.3f, 899.8f, 58.9f),
                        PathNode.Close,
                        PathNode.MoveTo(539.3f, 658.3f),
                        PathNode.QuadTo(467.8f, 695.8f, 416.1f, 757.1f),
                        PathNode.QuadTo(364.5f, 818.4f, 343.5f, 894.6f),
                        PathNode.QuadTo(338.7f, 911.3f, 345.6f, 921.1f),
                        PathNode.QuadTo(352.4f, 930.8f, 372.8f, 930.8f),
                        PathNode.LineTo(769.6f, 930.8f),
                        PathNode.QuadTo(789.2f, 930.8f, 796.9f, 920.4f),
                        PathNode.QuadTo(804.6f, 910.0f, 798.9f, 891.6f),
                        PathNode.QuadTo(778.2f, 819.4f, 726.7f, 757.2f),
                        PathNode.QuadTo(675.2f, 695.1f, 605.1f, 658.3f),
                        PathNode.QuadTo(583.9f, 647.6f, 572.2f, 647.6f),
                        PathNode.QuadTo(560.5f, 647.6f, 539.3f, 658.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerMedium!!
    }

private var _timerMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Timer: ImageVector
    get() {
        if (_timerDemibold != null) return _timerDemibold!!
        _timerDemibold = ImageVector.Builder(
            name = "Timer.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(903.3f, 56.5f),
                        PathNode.QuadTo(926.6f, 83.1f, 928.5f, 114.3f),
                        PathNode.QuadTo(930.5f, 145.5f, 921.5f, 208.9f),
                        PathNode.QuadTo(910.0f, 296.2f, 873.6f, 367.1f),
                        PathNode.QuadTo(837.2f, 437.9f, 775.3f, 491.8f),
                        PathNode.LineTo(762.8f, 503.4f),
                        PathNode.QuadTo(732.9f, 529.2f, 719.2f, 542.0f),
                        PathNode.QuadTo(705.4f, 554.8f, 702.4f, 560.5f),
                        PathNode.QuadTo(699.8f, 567.8f, 699.8f, 571.4f),
                        PathNode.QuadTo(699.8f, 575.1f, 702.4f, 581.4f),
                        PathNode.QuadTo(705.0f, 589.2f, 732.2f, 613.3f),
                        PathNode.LineTo(775.3f, 651.0f),
                        PathNode.QuadTo(837.2f, 704.9f, 873.6f, 775.5f),
                        PathNode.QuadTo(910.0f, 846.1f, 921.5f, 933.9f),
                        PathNode.QuadTo(930.5f, 997.4f, 928.5f, 1028.3f),
                        PathNode.QuadTo(926.6f, 1059.3f, 903.3f, 1085.4f),
                        PathNode.QuadTo(888.1f, 1103.7f, 866.0f, 1111.8f),
                        PathNode.QuadTo(843.9f, 1120.0f, 817.0f, 1121.7f),
                        PathNode.QuadTo(790.2f, 1123.5f, 736.2f, 1123.5f),
                        PathNode.LineTo(407.0f, 1123.5f),
                        PathNode.QuadTo(352.6f, 1123.5f, 325.5f, 1121.7f),
                        PathNode.QuadTo(298.3f, 1120.0f, 276.0f, 1111.8f),
                        PathNode.QuadTo(253.7f, 1103.7f, 238.4f, 1085.4f),
                        PathNode.QuadTo(216.7f, 1058.7f, 214.4f, 1027.7f),
                        PathNode.QuadTo(212.2f, 996.8f, 220.8f, 933.9f),
                        PathNode.QuadTo(233.2f, 846.1f, 270.4f, 775.0f),
                        PathNode.QuadTo(307.5f, 703.9f, 368.9f, 651.0f),
                        PathNode.QuadTo(404.3f, 619.7f, 420.8f, 603.9f),
                        PathNode.QuadTo(437.4f, 588.1f, 440.8f, 581.9f),
                        PathNode.QuadTo(443.5f, 575.1f, 443.5f, 571.4f),
                        PathNode.QuadTo(443.5f, 567.8f, 440.8f, 560.5f),
                        PathNode.QuadTo(439.3f, 555.9f, 427.4f, 544.4f),
                        PathNode.QuadTo(415.6f, 533.0f, 397.0f, 516.4f),
                        PathNode.LineTo(368.9f, 491.8f),
                        PathNode.QuadTo(307.5f, 437.9f, 270.4f, 366.8f),
                        PathNode.QuadTo(233.2f, 295.8f, 220.8f, 208.9f),
                        PathNode.QuadTo(212.2f, 145.1f, 214.4f, 114.1f),
                        PathNode.QuadTo(216.7f, 83.1f, 238.4f, 56.5f),
                        PathNode.QuadTo(253.7f, 38.7f, 276.0f, 30.8f),
                        PathNode.QuadTo(298.3f, 22.9f, 325.5f, 21.1f),
                        PathNode.QuadTo(352.6f, 19.4f, 407.0f, 19.4f),
                        PathNode.LineTo(736.2f, 19.4f),
                        PathNode.QuadTo(790.2f, 19.4f, 817.0f, 21.1f),
                        PathNode.QuadTo(843.9f, 22.9f, 866.0f, 30.8f),
                        PathNode.QuadTo(888.1f, 38.7f, 903.3f, 56.5f),
                        PathNode.Close,
                        PathNode.MoveTo(540.4f, 659.6f),
                        PathNode.QuadTo(469.3f, 696.7f, 418.2f, 757.6f),
                        PathNode.QuadTo(367.1f, 818.4f, 346.1f, 894.1f),
                        PathNode.QuadTo(341.5f, 909.8f, 347.9f, 918.7f),
                        PathNode.QuadTo(354.2f, 927.6f, 373.5f, 927.6f),
                        PathNode.LineTo(768.8f, 927.6f),
                        PathNode.QuadTo(787.5f, 927.6f, 794.6f, 918.0f),
                        PathNode.QuadTo(801.7f, 908.3f, 796.2f, 891.1f),
                        PathNode.QuadTo(775.6f, 819.4f, 724.6f, 757.8f),
                        PathNode.QuadTo(673.5f, 696.2f, 603.9f, 659.6f),
                        PathNode.QuadTo(583.2f, 649.1f, 572.1f, 649.1f),
                        PathNode.QuadTo(561.0f, 649.1f, 540.4f, 659.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerDemibold!!
    }

private var _timerDemibold: ImageVector? = null
