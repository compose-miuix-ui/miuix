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

val MiuixIcons.Lock: ImageVector
    get() = MiuixIcons.Regular.Lock

val MiuixIcons.Light.Lock: ImageVector
    get() {
        if (_lockLight != null) return _lockLight!!
        _lockLight = ImageVector.Builder(
            name = "Lock.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1261.2f,
            viewportHeight = 1261.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -34.39999999999998f, translationY = 985.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(999.0f, -154.0f),
                        PathNode.QuadTo(1042.0f, -135.0f, 1064.0f, -91.0f),
                        PathNode.QuadTo(1074.0f, -70.0f, 1076.5f, -38.5f),
                        PathNode.QuadTo(1079.0f, -7.0f, 1079.0f, 69.0f),
                        PathNode.VerticalTo(277.0f),
                        PathNode.QuadTo(1079.0f, 354.0f, 1076.5f, 385.0f),
                        PathNode.QuadTo(1074.0f, 416.0f, 1063.0f, 437.0f),
                        PathNode.QuadTo(1041.0f, 480.0f, 999.0f, 500.0f),
                        PathNode.QuadTo(985.0f, 508.0f, 967.0f, 511.0f),
                        PathNode.QuadTo(949.0f, 514.0f, 919.0f, 515.0f),
                        PathNode.VerticalTo(626.0f),
                        PathNode.QuadTo(919.0f, 695.0f, 885.0f, 753.5f),
                        PathNode.QuadTo(851.0f, 812.0f, 792.5f, 846.0f),
                        PathNode.QuadTo(734.0f, 880.0f, 665.0f, 880.0f),
                        PathNode.QuadTo(596.0f, 880.0f, 537.5f, 846.0f),
                        PathNode.QuadTo(479.0f, 812.0f, 445.0f, 753.5f),
                        PathNode.QuadTo(411.0f, 695.0f, 411.0f, 626.0f),
                        PathNode.VerticalTo(515.0f),
                        PathNode.QuadTo(354.0f, 514.0f, 330.0f, 500.0f),
                        PathNode.QuadTo(310.0f, 490.0f, 294.0f, 474.0f),
                        PathNode.QuadTo(278.0f, 458.0f, 266.0f, 437.0f),
                        PathNode.QuadTo(256.0f, 416.0f, 253.5f, 385.0f),
                        PathNode.QuadTo(251.0f, 354.0f, 251.0f, 277.0f),
                        PathNode.VerticalTo(69.0f),
                        PathNode.QuadTo(251.0f, -7.0f, 253.5f, -38.5f),
                        PathNode.QuadTo(256.0f, -70.0f, 266.0f, -91.0f),
                        PathNode.QuadTo(290.0f, -135.0f, 330.0f, -154.0f),
                        PathNode.QuadTo(351.0f, -165.0f, 384.0f, -168.0f),
                        PathNode.QuadTo(417.0f, -171.0f, 491.0f, -171.0f),
                        PathNode.HorizontalTo(839.0f),
                        PathNode.QuadTo(911.0f, -171.0f, 944.5f, -168.0f),
                        PathNode.QuadTo(978.0f, -165.0f, 999.0f, -154.0f),
                        PathNode.Close,
                        PathNode.MoveTo(361.0f, -101.0f),
                        PathNode.QuadTo(333.0f, -88.0f, 319.0f, -60.0f),
                        PathNode.QuadTo(313.0f, -47.0f, 311.5f, -30.0f),
                        PathNode.QuadTo(310.0f, -13.0f, 310.0f, 22.0f),
                        PathNode.VerticalTo(33.0f),
                        PathNode.VerticalTo(314.0f),
                        PathNode.QuadTo(310.0f, 358.0f, 311.5f, 375.5f),
                        PathNode.QuadTo(313.0f, 393.0f, 320.0f, 406.0f),
                        PathNode.QuadTo(334.0f, 434.0f, 360.0f, 447.0f),
                        PathNode.QuadTo(375.0f, 454.0f, 395.0f, 456.0f),
                        PathNode.QuadTo(415.0f, 458.0f, 453.0f, 458.0f),
                        PathNode.HorizontalTo(876.0f),
                        PathNode.QuadTo(914.0f, 458.0f, 934.0f, 456.0f),
                        PathNode.QuadTo(954.0f, 454.0f, 968.0f, 447.0f),
                        PathNode.QuadTo(996.0f, 434.0f, 1010.0f, 405.0f),
                        PathNode.QuadTo(1016.0f, 392.0f, 1018.0f, 372.0f),
                        PathNode.QuadTo(1020.0f, 352.0f, 1020.0f, 314.0f),
                        PathNode.VerticalTo(33.0f),
                        PathNode.QuadTo(1020.0f, -9.0f, 1018.5f, -27.5f),
                        PathNode.QuadTo(1017.0f, -46.0f, 1011.0f, -60.0f),
                        PathNode.QuadTo(997.0f, -87.0f, 968.0f, -101.0f),
                        PathNode.QuadTo(954.0f, -109.0f, 935.5f, -110.5f),
                        PathNode.QuadTo(917.0f, -112.0f, 876.0f, -112.0f),
                        PathNode.HorizontalTo(453.0f),
                        PathNode.QuadTo(413.0f, -112.0f, 394.0f, -110.5f),
                        PathNode.QuadTo(375.0f, -109.0f, 361.0f, -101.0f),
                        PathNode.Close,
                        PathNode.MoveTo(665.0f, 822.0f),
                        PathNode.QuadTo(718.0f, 822.0f, 762.5f, 795.5f),
                        PathNode.QuadTo(807.0f, 769.0f, 833.0f, 724.0f),
                        PathNode.QuadTo(859.0f, 679.0f, 859.0f, 626.0f),
                        PathNode.VerticalTo(517.0f),
                        PathNode.HorizontalTo(470.0f),
                        PathNode.VerticalTo(626.0f),
                        PathNode.QuadTo(470.0f, 679.0f, 496.5f, 724.0f),
                        PathNode.QuadTo(523.0f, 769.0f, 567.5f, 795.5f),
                        PathNode.QuadTo(612.0f, 822.0f, 665.0f, 822.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _lockLight!!
    }

private var _lockLight: ImageVector? = null

val MiuixIcons.Regular.Lock: ImageVector
    get() {
        if (_lockRegular != null) return _lockRegular!!
        _lockRegular = ImageVector.Builder(
            name = "Lock.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1281.6f,
            viewportHeight = 1281.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -24.200000000000045f, translationY = 995.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1003.0f, -162.0f),
                        PathNode.QuadTo(1049.0f, -139.0f, 1072.0f, -93.0f),
                        PathNode.QuadTo(1084.0f, -70.0f, 1086.5f, -37.5f),
                        PathNode.QuadTo(1089.0f, -5.0f, 1089.0f, 72.0f),
                        PathNode.VerticalTo(278.0f),
                        PathNode.QuadTo(1089.0f, 355.0f, 1086.5f, 387.5f),
                        PathNode.QuadTo(1084.0f, 420.0f, 1072.0f, 443.0f),
                        PathNode.QuadTo(1049.0f, 488.0f, 1003.0f, 511.0f),
                        PathNode.QuadTo(989.0f, 518.0f, 972.0f, 521.5f),
                        PathNode.QuadTo(955.0f, 525.0f, 930.0f, 527.0f),
                        PathNode.VerticalTo(624.0f),
                        PathNode.QuadTo(930.0f, 696.0f, 894.5f, 757.0f),
                        PathNode.QuadTo(859.0f, 818.0f, 798.0f, 853.5f),
                        PathNode.QuadTo(737.0f, 889.0f, 665.0f, 889.0f),
                        PathNode.QuadTo(593.0f, 889.0f, 532.0f, 853.5f),
                        PathNode.QuadTo(471.0f, 818.0f, 435.5f, 757.0f),
                        PathNode.QuadTo(400.0f, 696.0f, 400.0f, 624.0f),
                        PathNode.VerticalTo(527.0f),
                        PathNode.QuadTo(351.0f, 524.0f, 326.0f, 511.0f),
                        PathNode.QuadTo(304.0f, 500.0f, 286.5f, 482.5f),
                        PathNode.QuadTo(269.0f, 465.0f, 258.0f, 443.0f),
                        PathNode.QuadTo(246.0f, 420.0f, 243.5f, 387.5f),
                        PathNode.QuadTo(241.0f, 355.0f, 241.0f, 278.0f),
                        PathNode.VerticalTo(72.0f),
                        PathNode.QuadTo(241.0f, -5.0f, 243.5f, -37.5f),
                        PathNode.QuadTo(246.0f, -70.0f, 258.0f, -93.0f),
                        PathNode.QuadTo(281.0f, -139.0f, 326.0f, -162.0f),
                        PathNode.QuadTo(349.0f, -174.0f, 382.0f, -176.5f),
                        PathNode.QuadTo(415.0f, -179.0f, 492.0f, -179.0f),
                        PathNode.HorizontalTo(838.0f),
                        PathNode.QuadTo(915.0f, -179.0f, 947.5f, -176.5f),
                        PathNode.QuadTo(980.0f, -174.0f, 1003.0f, -162.0f),
                        PathNode.Close,
                        PathNode.MoveTo(370.0f, -85.0f),
                        PathNode.QuadTo(347.0f, -73.0f, 335.0f, -50.0f),
                        PathNode.QuadTo(329.0f, -39.0f, 327.5f, -23.0f),
                        PathNode.QuadTo(326.0f, -7.0f, 326.0f, 25.0f),
                        PathNode.VerticalTo(36.0f),
                        PathNode.VerticalTo(314.0f),
                        PathNode.QuadTo(326.0f, 354.0f, 327.5f, 370.5f),
                        PathNode.QuadTo(329.0f, 387.0f, 335.0f, 399.0f),
                        PathNode.QuadTo(347.0f, 422.0f, 370.0f, 434.0f),
                        PathNode.QuadTo(382.0f, 440.0f, 399.0f, 441.5f),
                        PathNode.QuadTo(416.0f, 443.0f, 455.0f, 443.0f),
                        PathNode.HorizontalTo(874.0f),
                        PathNode.QuadTo(914.0f, 443.0f, 930.5f, 441.5f),
                        PathNode.QuadTo(947.0f, 440.0f, 959.0f, 434.0f),
                        PathNode.QuadTo(983.0f, 422.0f, 995.0f, 399.0f),
                        PathNode.QuadTo(1000.0f, 387.0f, 1001.5f, 370.5f),
                        PathNode.QuadTo(1003.0f, 354.0f, 1003.0f, 314.0f),
                        PathNode.VerticalTo(36.0f),
                        PathNode.QuadTo(1003.0f, -4.0f, 1002.0f, -21.0f),
                        PathNode.QuadTo(1001.0f, -38.0f, 995.0f, -50.0f),
                        PathNode.QuadTo(983.0f, -73.0f, 959.0f, -85.0f),
                        PathNode.QuadTo(947.0f, -91.0f, 930.5f, -92.5f),
                        PathNode.QuadTo(914.0f, -94.0f, 874.0f, -94.0f),
                        PathNode.HorizontalTo(455.0f),
                        PathNode.QuadTo(416.0f, -94.0f, 399.0f, -92.5f),
                        PathNode.QuadTo(382.0f, -91.0f, 370.0f, -85.0f),
                        PathNode.Close,
                        PathNode.MoveTo(665.0f, 804.0f),
                        PathNode.QuadTo(714.0f, 804.0f, 755.0f, 779.5f),
                        PathNode.QuadTo(796.0f, 755.0f, 820.0f, 714.0f),
                        PathNode.QuadTo(844.0f, 673.0f, 844.0f, 624.0f),
                        PathNode.VerticalTo(529.0f),
                        PathNode.HorizontalTo(485.0f),
                        PathNode.VerticalTo(624.0f),
                        PathNode.QuadTo(485.0f, 673.0f, 509.5f, 714.0f),
                        PathNode.QuadTo(534.0f, 755.0f, 575.0f, 779.5f),
                        PathNode.QuadTo(616.0f, 804.0f, 665.0f, 804.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _lockRegular!!
    }

private var _lockRegular: ImageVector? = null

val MiuixIcons.Heavy.Lock: ImageVector
    get() {
        if (_lockHeavy != null) return _lockHeavy!!
        _lockHeavy = ImageVector.Builder(
            name = "Lock.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1324.8f,
            viewportHeight = 1324.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -2.6000000000000227f, translationY = 1017.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1011.0f, -179.0f),
                        PathNode.QuadTo(1062.0f, -152.0f, 1089.0f, -101.0f),
                        PathNode.QuadTo(1101.0f, -76.0f, 1104.0f, -41.0f),
                        PathNode.QuadTo(1107.0f, -6.0f, 1107.0f, 72.0f),
                        PathNode.VerticalTo(278.0f),
                        PathNode.QuadTo(1107.0f, 356.0f, 1104.0f, 390.5f),
                        PathNode.QuadTo(1101.0f, 425.0f, 1089.0f, 451.0f),
                        PathNode.QuadTo(1062.0f, 502.0f, 1011.0f, 528.0f),
                        PathNode.QuadTo(999.0f, 534.0f, 984.0f, 538.0f),
                        PathNode.QuadTo(969.0f, 542.0f, 948.0f, 544.0f),
                        PathNode.VerticalTo(624.0f),
                        PathNode.QuadTo(948.0f, 701.0f, 910.0f, 766.0f),
                        PathNode.QuadTo(872.0f, 831.0f, 807.0f, 869.0f),
                        PathNode.QuadTo(742.0f, 907.0f, 665.0f, 907.0f),
                        PathNode.QuadTo(588.0f, 907.0f, 523.0f, 869.0f),
                        PathNode.QuadTo(458.0f, 831.0f, 420.0f, 766.0f),
                        PathNode.QuadTo(382.0f, 701.0f, 382.0f, 624.0f),
                        PathNode.VerticalTo(544.0f),
                        PathNode.QuadTo(339.0f, 539.0f, 317.0f, 527.0f),
                        PathNode.QuadTo(294.0f, 515.0f, 274.0f, 495.0f),
                        PathNode.QuadTo(254.0f, 475.0f, 241.0f, 451.0f),
                        PathNode.QuadTo(229.0f, 425.0f, 226.0f, 390.5f),
                        PathNode.QuadTo(223.0f, 356.0f, 223.0f, 278.0f),
                        PathNode.VerticalTo(72.0f),
                        PathNode.QuadTo(223.0f, -6.0f, 226.0f, -41.0f),
                        PathNode.QuadTo(229.0f, -76.0f, 241.0f, -101.0f),
                        PathNode.QuadTo(267.0f, -152.0f, 318.0f, -179.0f),
                        PathNode.QuadTo(344.0f, -191.0f, 379.0f, -194.0f),
                        PathNode.QuadTo(414.0f, -197.0f, 492.0f, -197.0f),
                        PathNode.HorizontalTo(838.0f),
                        PathNode.QuadTo(916.0f, -197.0f, 951.0f, -194.0f),
                        PathNode.QuadTo(986.0f, -191.0f, 1011.0f, -179.0f),
                        PathNode.Close,
                        PathNode.MoveTo(378.0f, -68.0f),
                        PathNode.QuadTo(361.0f, -59.0f, 352.0f, -42.0f),
                        PathNode.QuadTo(347.0f, -33.0f, 345.5f, -19.0f),
                        PathNode.QuadTo(344.0f, -5.0f, 344.0f, 25.0f),
                        PathNode.VerticalTo(36.0f),
                        PathNode.VerticalTo(314.0f),
                        PathNode.QuadTo(344.0f, 353.0f, 345.0f, 367.5f),
                        PathNode.QuadTo(346.0f, 382.0f, 351.0f, 390.0f),
                        PathNode.QuadTo(362.0f, 409.0f, 378.0f, 417.0f),
                        PathNode.QuadTo(388.0f, 423.0f, 402.5f, 424.0f),
                        PathNode.QuadTo(417.0f, 425.0f, 455.0f, 425.0f),
                        PathNode.HorizontalTo(874.0f),
                        PathNode.QuadTo(913.0f, 425.0f, 927.5f, 424.0f),
                        PathNode.QuadTo(942.0f, 423.0f, 951.0f, 417.0f),
                        PathNode.QuadTo(969.0f, 409.0f, 978.0f, 391.0f),
                        PathNode.QuadTo(983.0f, 382.0f, 984.0f, 367.0f),
                        PathNode.QuadTo(985.0f, 352.0f, 985.0f, 314.0f),
                        PathNode.VerticalTo(36.0f),
                        PathNode.QuadTo(985.0f, -3.0f, 984.0f, -17.5f),
                        PathNode.QuadTo(983.0f, -32.0f, 978.0f, -42.0f),
                        PathNode.QuadTo(970.0f, -59.0f, 951.0f, -68.0f),
                        PathNode.QuadTo(942.0f, -74.0f, 927.5f, -75.0f),
                        PathNode.QuadTo(913.0f, -76.0f, 874.0f, -76.0f),
                        PathNode.HorizontalTo(455.0f),
                        PathNode.QuadTo(417.0f, -76.0f, 402.5f, -75.0f),
                        PathNode.QuadTo(388.0f, -74.0f, 378.0f, -68.0f),
                        PathNode.Close,
                        PathNode.MoveTo(665.0f, 786.0f),
                        PathNode.QuadTo(709.0f, 786.0f, 746.0f, 764.0f),
                        PathNode.QuadTo(783.0f, 742.0f, 804.5f, 705.0f),
                        PathNode.QuadTo(826.0f, 668.0f, 826.0f, 624.0f),
                        PathNode.VerticalTo(547.0f),
                        PathNode.HorizontalTo(503.0f),
                        PathNode.VerticalTo(624.0f),
                        PathNode.QuadTo(503.0f, 668.0f, 525.0f, 705.0f),
                        PathNode.QuadTo(547.0f, 742.0f, 584.0f, 764.0f),
                        PathNode.QuadTo(621.0f, 786.0f, 665.0f, 786.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _lockHeavy!!
    }

private var _lockHeavy: ImageVector? = null
