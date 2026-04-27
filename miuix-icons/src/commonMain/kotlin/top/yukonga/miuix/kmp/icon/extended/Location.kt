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
            group(scaleY = -1.0f, translationY = 1290.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(680.0f, 310.5f),
                        PathNode.QuadTo(749.0f, 398.5f, 827.0f, 514.5f),
                        PathNode.QuadTo(905.0f, 630.5f, 944.0f, 721.5f),
                        PathNode.QuadTo(975.0f, 791.5f, 975.0f, 860.5f),
                        PathNode.QuadTo(975.0f, 948.5f, 932.0f, 1022.0f),
                        PathNode.QuadTo(889.0f, 1095.5f, 815.0f, 1139.0f),
                        PathNode.QuadTo(741.0f, 1182.5f, 654.0f, 1182.5f),
                        PathNode.QuadTo(566.0f, 1182.5f, 492.0f, 1139.0f),
                        PathNode.QuadTo(418.0f, 1095.5f, 374.5f, 1022.0f),
                        PathNode.QuadTo(331.0f, 948.5f, 331.0f, 860.5f),
                        PathNode.QuadTo(331.0f, 791.5f, 363.0f, 719.5f),
                        PathNode.QuadTo(400.0f, 630.5f, 478.5f, 516.0f),
                        PathNode.QuadTo(557.0f, 401.5f, 630.0f, 310.5f),
                        PathNode.QuadTo(639.0f, 297.5f, 654.5f, 297.5f),
                        PathNode.QuadTo(670.0f, 297.5f, 680.0f, 310.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1125.0f, 276.5f),
                        PathNode.QuadTo(1125.0f, 323.5f, 1056.0f, 363.5f),
                        PathNode.QuadTo(987.0f, 403.5f, 860.0f, 429.5f),
                        PathNode.QuadTo(851.0f, 431.5f, 846.0f, 429.5f),
                        PathNode.QuadTo(841.0f, 427.5f, 836.0f, 421.5f),
                        PathNode.LineTo(821.0f, 397.5f),
                        PathNode.QuadTo(816.0f, 391.5f, 818.0f, 386.5f),
                        PathNode.QuadTo(820.0f, 381.5f, 830.0f, 380.5f),
                        PathNode.QuadTo(933.0f, 362.5f, 993.5f, 335.5f),
                        PathNode.QuadTo(1054.0f, 308.5f, 1054.0f, 276.5f),
                        PathNode.QuadTo(1054.0f, 246.5f, 999.0f, 220.5f),
                        PathNode.QuadTo(944.0f, 194.5f, 850.5f, 179.0f),
                        PathNode.QuadTo(757.0f, 163.5f, 647.0f, 163.5f),
                        PathNode.QuadTo(536.0f, 163.5f, 442.0f, 179.0f),
                        PathNode.QuadTo(348.0f, 194.5f, 293.0f, 220.5f),
                        PathNode.QuadTo(238.0f, 246.5f, 238.0f, 276.5f),
                        PathNode.QuadTo(238.0f, 309.5f, 301.5f, 336.5f),
                        PathNode.QuadTo(365.0f, 363.5f, 480.0f, 380.5f),
                        PathNode.QuadTo(489.0f, 381.5f, 490.0f, 388.0f),
                        PathNode.QuadTo(491.0f, 394.5f, 485.0f, 402.5f),
                        PathNode.LineTo(470.0f, 421.5f),
                        PathNode.QuadTo(465.0f, 428.5f, 460.5f, 430.0f),
                        PathNode.QuadTo(456.0f, 431.5f, 445.0f, 429.5f),
                        PathNode.QuadTo(312.0f, 406.5f, 238.5f, 366.0f),
                        PathNode.QuadTo(165.0f, 325.5f, 165.0f, 276.5f),
                        PathNode.QuadTo(165.0f, 230.5f, 229.0f, 191.5f),
                        PathNode.QuadTo(293.0f, 152.5f, 403.5f, 130.0f),
                        PathNode.QuadTo(514.0f, 107.5f, 647.0f, 107.5f),
                        PathNode.QuadTo(780.0f, 107.5f, 889.5f, 130.0f),
                        PathNode.QuadTo(999.0f, 152.5f, 1062.0f, 191.5f),
                        PathNode.QuadTo(1125.0f, 230.5f, 1125.0f, 276.5f),
                        PathNode.Close,
                        PathNode.MoveTo(539.0f, 857.5f),
                        PathNode.QuadTo(539.0f, 888.5f, 554.5f, 915.0f),
                        PathNode.QuadTo(570.0f, 941.5f, 596.5f, 957.0f),
                        PathNode.QuadTo(623.0f, 972.5f, 654.0f, 972.5f),
                        PathNode.QuadTo(685.0f, 972.5f, 711.0f, 957.0f),
                        PathNode.QuadTo(737.0f, 941.5f, 753.0f, 915.0f),
                        PathNode.QuadTo(769.0f, 888.5f, 769.0f, 857.5f),
                        PathNode.QuadTo(769.0f, 826.5f, 753.5f, 800.0f),
                        PathNode.QuadTo(738.0f, 773.5f, 711.5f, 758.0f),
                        PathNode.QuadTo(685.0f, 742.5f, 654.0f, 742.5f),
                        PathNode.QuadTo(623.0f, 742.5f, 596.5f, 758.0f),
                        PathNode.QuadTo(570.0f, 773.5f, 554.5f, 800.0f),
                        PathNode.QuadTo(539.0f, 826.5f, 539.0f, 857.5f),
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

val MiuixIcons.Normal.Location: ImageVector
    get() {
        if (_locationNormal != null) return _locationNormal!!
        _locationNormal = ImageVector.Builder(
            name = "Location.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1304.8f,
            viewportHeight = 1304.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1304.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(693.6f, 314.5f),
                        PathNode.QuadTo(761.9f, 400.4f, 840.3f, 517.1f),
                        PathNode.QuadTo(918.6f, 633.8f, 957.6f, 724.8f),
                        PathNode.QuadTo(989.3f, 797.5f, 989.3f, 866.5f),
                        PathNode.QuadTo(989.3f, 955.9f, 945.3f, 1031.5f),
                        PathNode.QuadTo(901.2f, 1107.0f, 825.5f, 1151.6f),
                        PathNode.QuadTo(749.8f, 1196.1f, 660.7f, 1196.1f),
                        PathNode.QuadTo(570.7f, 1196.1f, 495.0f, 1151.6f),
                        PathNode.QuadTo(419.2f, 1107.0f, 374.7f, 1031.8f),
                        PathNode.QuadTo(330.2f, 956.6f, 330.2f, 866.5f),
                        PathNode.QuadTo(330.2f, 797.5f, 362.9f, 722.8f),
                        PathNode.QuadTo(399.9f, 634.5f, 478.0f, 520.0f),
                        PathNode.QuadTo(556.2f, 405.5f, 629.2f, 314.5f),
                        PathNode.QuadTo(641.6f, 298.0f, 661.6f, 298.0f),
                        PathNode.QuadTo(681.5f, 298.0f, 693.6f, 314.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1142.7f, 286.0f),
                        PathNode.QuadTo(1142.7f, 335.0f, 1071.7f, 377.1f),
                        PathNode.QuadTo(1000.6f, 419.2f, 877.7f, 443.8f),
                        PathNode.QuadTo(865.3f, 446.5f, 858.2f, 443.8f),
                        PathNode.QuadTo(851.2f, 441.1f, 845.5f, 433.0f),
                        PathNode.LineTo(823.6f, 400.1f),
                        PathNode.QuadTo(817.9f, 392.0f, 820.3f, 386.0f),
                        PathNode.QuadTo(822.6f, 380.0f, 835.4f, 378.3f),
                        PathNode.QuadTo(932.2f, 363.7f, 989.2f, 338.8f),
                        PathNode.QuadTo(1046.3f, 313.9f, 1046.3f, 286.0f),
                        PathNode.QuadTo(1046.3f, 258.0f, 993.4f, 234.5f),
                        PathNode.QuadTo(940.4f, 210.9f, 850.4f, 197.1f),
                        PathNode.QuadTo(760.3f, 183.3f, 653.7f, 183.3f),
                        PathNode.QuadTo(546.9f, 183.3f, 456.3f, 197.1f),
                        PathNode.QuadTo(365.7f, 210.9f, 312.8f, 234.5f),
                        PathNode.QuadTo(259.9f, 258.0f, 259.9f, 286.0f),
                        PathNode.QuadTo(259.9f, 315.5f, 320.3f, 340.5f),
                        PathNode.QuadTo(380.7f, 365.4f, 488.1f, 379.7f),
                        PathNode.QuadTo(499.9f, 380.7f, 500.9f, 388.9f),
                        PathNode.QuadTo(501.9f, 397.1f, 493.1f, 408.5f),
                        PathNode.LineTo(473.3f, 434.4f),
                        PathNode.QuadTo(466.9f, 443.5f, 460.7f, 445.3f),
                        PathNode.QuadTo(454.5f, 447.2f, 438.7f, 444.5f),
                        PathNode.QuadTo(311.9f, 422.2f, 237.0f, 379.6f),
                        PathNode.QuadTo(162.1f, 337.0f, 162.1f, 286.0f),
                        PathNode.QuadTo(162.1f, 237.9f, 227.8f, 197.2f),
                        PathNode.QuadTo(293.5f, 156.5f, 406.5f, 132.6f),
                        PathNode.QuadTo(519.4f, 108.7f, 653.7f, 108.7f),
                        PathNode.QuadTo(787.4f, 108.7f, 899.7f, 132.6f),
                        PathNode.QuadTo(1011.9f, 156.5f, 1077.3f, 197.2f),
                        PathNode.QuadTo(1142.7f, 237.9f, 1142.7f, 286.0f),
                        PathNode.Close,
                        PathNode.MoveTo(541.6f, 863.5f),
                        PathNode.QuadTo(541.6f, 895.9f, 557.8f, 923.1f),
                        PathNode.QuadTo(574.0f, 950.3f, 601.2f, 966.5f),
                        PathNode.QuadTo(628.4f, 982.7f, 660.7f, 982.7f),
                        PathNode.QuadTo(692.4f, 982.7f, 719.8f, 966.5f),
                        PathNode.QuadTo(747.2f, 950.3f, 763.5f, 922.8f),
                        PathNode.QuadTo(779.9f, 895.2f, 779.9f, 863.5f),
                        PathNode.QuadTo(779.9f, 831.2f, 763.7f, 804.0f),
                        PathNode.QuadTo(747.5f, 776.8f, 720.3f, 760.6f),
                        PathNode.QuadTo(693.1f, 744.4f, 660.7f, 744.4f),
                        PathNode.QuadTo(628.4f, 744.4f, 600.8f, 760.3f),
                        PathNode.QuadTo(573.3f, 776.1f, 557.5f, 803.6f),
                        PathNode.QuadTo(541.6f, 831.2f, 541.6f, 863.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationNormal!!
    }

private var _locationNormal: ImageVector? = null

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
            group(scaleY = -1.0f, translationY = 1311.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(699.8f, 316.3f),
                        PathNode.QuadTo(767.8f, 401.3f, 846.3f, 518.3f),
                        PathNode.QuadTo(924.8f, 635.3f, 963.8f, 726.3f),
                        PathNode.QuadTo(995.8f, 800.3f, 995.8f, 869.3f),
                        PathNode.QuadTo(995.8f, 959.3f, 951.3f, 1035.8f),
                        PathNode.QuadTo(906.8f, 1112.3f, 830.3f, 1157.3f),
                        PathNode.QuadTo(753.8f, 1202.3f, 663.8f, 1202.3f),
                        PathNode.QuadTo(572.8f, 1202.3f, 496.3f, 1157.3f),
                        PathNode.QuadTo(419.8f, 1112.3f, 374.8f, 1036.3f),
                        PathNode.QuadTo(329.8f, 960.3f, 329.8f, 869.3f),
                        PathNode.QuadTo(329.8f, 800.3f, 362.8f, 724.3f),
                        PathNode.QuadTo(399.8f, 636.3f, 477.8f, 521.8f),
                        PathNode.QuadTo(555.8f, 407.3f, 628.8f, 316.3f),
                        PathNode.QuadTo(642.8f, 298.3f, 664.8f, 298.3f),
                        PathNode.QuadTo(686.8f, 298.3f, 699.8f, 316.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1150.8f, 290.3f),
                        PathNode.QuadTo(1150.8f, 340.3f, 1078.8f, 383.3f),
                        PathNode.QuadTo(1006.8f, 426.3f, 885.8f, 450.3f),
                        PathNode.QuadTo(871.8f, 453.3f, 863.8f, 450.3f),
                        PathNode.QuadTo(855.8f, 447.3f, 849.8f, 438.3f),
                        PathNode.LineTo(824.8f, 401.3f),
                        PathNode.QuadTo(818.8f, 392.3f, 821.3f, 385.8f),
                        PathNode.QuadTo(823.8f, 379.3f, 837.8f, 377.3f),
                        PathNode.QuadTo(931.8f, 364.3f, 987.3f, 340.3f),
                        PathNode.QuadTo(1042.8f, 316.3f, 1042.8f, 290.3f),
                        PathNode.QuadTo(1042.8f, 263.3f, 990.8f, 240.8f),
                        PathNode.QuadTo(938.8f, 218.3f, 850.3f, 205.3f),
                        PathNode.QuadTo(761.8f, 192.3f, 656.8f, 192.3f),
                        PathNode.QuadTo(551.8f, 192.3f, 462.8f, 205.3f),
                        PathNode.QuadTo(373.8f, 218.3f, 321.8f, 240.8f),
                        PathNode.QuadTo(269.8f, 263.3f, 269.8f, 290.3f),
                        PathNode.QuadTo(269.8f, 318.3f, 328.8f, 342.3f),
                        PathNode.QuadTo(387.8f, 366.3f, 491.8f, 379.3f),
                        PathNode.QuadTo(504.8f, 380.3f, 505.8f, 389.3f),
                        PathNode.QuadTo(506.8f, 398.3f, 496.8f, 411.3f),
                        PathNode.LineTo(474.8f, 440.3f),
                        PathNode.QuadTo(467.8f, 450.3f, 460.8f, 452.3f),
                        PathNode.QuadTo(453.8f, 454.3f, 435.8f, 451.3f),
                        PathNode.QuadTo(311.8f, 429.3f, 236.3f, 385.8f),
                        PathNode.QuadTo(160.8f, 342.3f, 160.8f, 290.3f),
                        PathNode.QuadTo(160.8f, 241.3f, 227.3f, 199.8f),
                        PathNode.QuadTo(293.8f, 158.3f, 407.8f, 133.8f),
                        PathNode.QuadTo(521.8f, 109.3f, 656.8f, 109.3f),
                        PathNode.QuadTo(790.8f, 109.3f, 904.3f, 133.8f),
                        PathNode.QuadTo(1017.8f, 158.3f, 1084.3f, 199.8f),
                        PathNode.QuadTo(1150.8f, 241.3f, 1150.8f, 290.3f),
                        PathNode.Close,
                        PathNode.MoveTo(542.8f, 866.3f),
                        PathNode.QuadTo(542.8f, 899.3f, 559.3f, 926.8f),
                        PathNode.QuadTo(575.8f, 954.3f, 603.3f, 970.8f),
                        PathNode.QuadTo(630.8f, 987.3f, 663.8f, 987.3f),
                        PathNode.QuadTo(695.8f, 987.3f, 723.8f, 970.8f),
                        PathNode.QuadTo(751.8f, 954.3f, 768.3f, 926.3f),
                        PathNode.QuadTo(784.8f, 898.3f, 784.8f, 866.3f),
                        PathNode.QuadTo(784.8f, 833.3f, 768.3f, 805.8f),
                        PathNode.QuadTo(751.8f, 778.3f, 724.3f, 761.8f),
                        PathNode.QuadTo(696.8f, 745.3f, 663.8f, 745.3f),
                        PathNode.QuadTo(630.8f, 745.3f, 602.8f, 761.3f),
                        PathNode.QuadTo(574.8f, 777.3f, 558.8f, 805.3f),
                        PathNode.QuadTo(542.8f, 833.3f, 542.8f, 866.3f),
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

val MiuixIcons.Medium.Location: ImageVector
    get() {
        if (_locationMedium != null) return _locationMedium!!
        _locationMedium = ImageVector.Builder(
            name = "Location.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1331.4f,
            viewportHeight = 1331.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1331.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(716.2f, 321.5f),
                        PathNode.QuadTo(779.4f, 394.1f, 859.7f, 515.5f),
                        PathNode.QuadTo(940.0f, 636.9f, 981.3f, 732.7f),
                        PathNode.QuadTo(1013.9f, 809.0f, 1013.9f, 879.2f),
                        PathNode.QuadTo(1013.9f, 971.5f, 967.9f, 1049.8f),
                        PathNode.QuadTo(922.0f, 1128.1f, 843.7f, 1174.2f),
                        PathNode.QuadTo(765.4f, 1220.4f, 673.7f, 1220.4f),
                        PathNode.QuadTo(580.9f, 1220.4f, 502.4f, 1174.2f),
                        PathNode.QuadTo(423.8f, 1128.1f, 377.6f, 1050.0f),
                        PathNode.QuadTo(331.4f, 971.9f, 331.4f, 879.2f),
                        PathNode.QuadTo(331.4f, 809.6f, 365.0f, 730.7f),
                        PathNode.QuadTo(402.6f, 642.1f, 480.9f, 527.6f),
                        PathNode.QuadTo(559.2f, 413.1f, 632.2f, 321.5f),
                        PathNode.QuadTo(648.0f, 299.9f, 674.4f, 299.9f),
                        PathNode.QuadTo(700.8f, 299.9f, 716.2f, 321.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1168.9f, 300.2f),
                        PathNode.QuadTo(1168.9f, 353.1f, 1099.9f, 396.4f),
                        PathNode.QuadTo(1030.8f, 439.7f, 921.6f, 463.1f),
                        PathNode.QuadTo(905.2f, 466.7f, 894.9f, 462.8f),
                        PathNode.QuadTo(884.5f, 458.9f, 877.3f, 448.2f),
                        PathNode.LineTo(852.3f, 410.0f),
                        PathNode.QuadTo(844.0f, 396.9f, 848.2f, 387.2f),
                        PathNode.QuadTo(852.5f, 377.4f, 871.2f, 374.2f),
                        PathNode.QuadTo(946.4f, 363.0f, 995.4f, 341.9f),
                        PathNode.QuadTo(1044.4f, 320.9f, 1044.4f, 300.2f),
                        PathNode.QuadTo(1044.4f, 277.9f, 993.6f, 257.2f),
                        PathNode.QuadTo(942.8f, 236.4f, 856.1f, 223.4f),
                        PathNode.QuadTo(769.3f, 210.4f, 666.7f, 210.4f),
                        PathNode.QuadTo(564.0f, 210.4f, 476.8f, 223.4f),
                        PathNode.QuadTo(389.6f, 236.4f, 338.7f, 257.2f),
                        PathNode.QuadTo(287.9f, 277.9f, 287.9f, 300.2f),
                        PathNode.QuadTo(287.9f, 322.3f, 340.2f, 343.9f),
                        PathNode.QuadTo(392.4f, 365.6f, 479.9f, 377.4f),
                        PathNode.QuadTo(497.0f, 380.2f, 500.4f, 391.5f),
                        PathNode.QuadTo(503.7f, 402.9f, 491.4f, 420.0f),
                        PathNode.LineTo(468.2f, 450.8f),
                        PathNode.QuadTo(459.4f, 463.1f, 450.7f, 466.0f),
                        PathNode.QuadTo(441.9f, 468.9f, 421.6f, 464.7f),
                        PathNode.QuadTo(308.7f, 443.3f, 235.6f, 399.2f),
                        PathNode.QuadTo(162.4f, 355.1f, 162.4f, 300.2f),
                        PathNode.QuadTo(162.4f, 247.7f, 230.4f, 204.4f),
                        PathNode.QuadTo(298.4f, 161.1f, 414.2f, 136.0f),
                        PathNode.QuadTo(529.9f, 110.9f, 666.7f, 110.9f),
                        PathNode.QuadTo(802.4f, 110.9f, 918.0f, 136.0f),
                        PathNode.QuadTo(1033.6f, 161.1f, 1101.2f, 204.4f),
                        PathNode.QuadTo(1168.9f, 247.7f, 1168.9f, 300.2f),
                        PathNode.Close,
                        PathNode.MoveTo(546.8f, 876.2f),
                        PathNode.QuadTo(546.8f, 910.4f, 563.9f, 939.6f),
                        PathNode.QuadTo(581.0f, 968.9f, 609.9f, 986.0f),
                        PathNode.QuadTo(638.9f, 1003.1f, 673.7f, 1003.1f),
                        PathNode.QuadTo(707.4f, 1003.1f, 736.9f, 986.0f),
                        PathNode.QuadTo(766.4f, 968.9f, 783.5f, 939.4f),
                        PathNode.QuadTo(800.6f, 909.9f, 800.6f, 876.2f),
                        PathNode.QuadTo(800.6f, 841.4f, 783.5f, 812.4f),
                        PathNode.QuadTo(766.4f, 783.5f, 737.1f, 766.4f),
                        PathNode.QuadTo(707.9f, 749.3f, 673.7f, 749.3f),
                        PathNode.QuadTo(638.9f, 749.3f, 609.7f, 766.2f),
                        PathNode.QuadTo(580.6f, 783.1f, 563.7f, 812.2f),
                        PathNode.QuadTo(546.8f, 841.4f, 546.8f, 876.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationMedium!!
    }

private var _locationMedium: ImageVector? = null

val MiuixIcons.Demibold.Location: ImageVector
    get() {
        if (_locationDemibold != null) return _locationDemibold!!
        _locationDemibold = ImageVector.Builder(
            name = "Location.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1345.2f,
            viewportHeight = 1345.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1345.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(727.6f, 325.1f),
                        PathNode.QuadTo(787.6f, 389.1f, 869.1f, 513.6f),
                        PathNode.QuadTo(950.6f, 638.1f, 993.6f, 737.1f),
                        PathNode.QuadTo(1026.6f, 815.1f, 1026.6f, 886.1f),
                        PathNode.QuadTo(1026.6f, 980.1f, 979.6f, 1059.6f),
                        PathNode.QuadTo(932.6f, 1139.1f, 853.1f, 1186.1f),
                        PathNode.QuadTo(773.6f, 1233.1f, 680.6f, 1233.1f),
                        PathNode.QuadTo(586.6f, 1233.1f, 506.6f, 1186.1f),
                        PathNode.QuadTo(426.6f, 1139.1f, 379.6f, 1059.6f),
                        PathNode.QuadTo(332.6f, 980.1f, 332.6f, 886.1f),
                        PathNode.QuadTo(332.6f, 816.1f, 366.6f, 735.1f),
                        PathNode.QuadTo(404.6f, 646.1f, 483.1f, 531.6f),
                        PathNode.QuadTo(561.6f, 417.1f, 634.6f, 325.1f),
                        PathNode.QuadTo(651.6f, 301.1f, 681.1f, 301.1f),
                        PathNode.QuadTo(710.6f, 301.1f, 727.6f, 325.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1181.6f, 307.1f),
                        PathNode.QuadTo(1181.6f, 362.1f, 1114.6f, 405.6f),
                        PathNode.QuadTo(1047.6f, 449.1f, 946.6f, 472.1f),
                        PathNode.QuadTo(928.6f, 476.1f, 916.6f, 471.6f),
                        PathNode.QuadTo(904.6f, 467.1f, 896.6f, 455.1f),
                        PathNode.LineTo(871.6f, 416.1f),
                        PathNode.QuadTo(861.6f, 400.1f, 867.1f, 388.1f),
                        PathNode.QuadTo(872.6f, 376.1f, 894.6f, 372.1f),
                        PathNode.QuadTo(956.6f, 362.1f, 1001.1f, 343.1f),
                        PathNode.QuadTo(1045.6f, 324.1f, 1045.6f, 307.1f),
                        PathNode.QuadTo(1045.6f, 288.1f, 995.6f, 268.6f),
                        PathNode.QuadTo(945.6f, 249.1f, 860.1f, 236.1f),
                        PathNode.QuadTo(774.6f, 223.1f, 673.6f, 223.1f),
                        PathNode.QuadTo(572.6f, 223.1f, 486.6f, 236.1f),
                        PathNode.QuadTo(400.6f, 249.1f, 350.6f, 268.6f),
                        PathNode.QuadTo(300.6f, 288.1f, 300.6f, 307.1f),
                        PathNode.QuadTo(300.6f, 325.1f, 348.1f, 345.1f),
                        PathNode.QuadTo(395.6f, 365.1f, 471.6f, 376.1f),
                        PathNode.QuadTo(491.6f, 380.1f, 496.6f, 393.1f),
                        PathNode.QuadTo(501.6f, 406.1f, 487.6f, 426.1f),
                        PathNode.LineTo(463.6f, 458.1f),
                        PathNode.QuadTo(453.6f, 472.1f, 443.6f, 475.6f),
                        PathNode.QuadTo(433.6f, 479.1f, 411.6f, 474.1f),
                        PathNode.QuadTo(306.6f, 453.1f, 235.1f, 408.6f),
                        PathNode.QuadTo(163.6f, 364.1f, 163.6f, 307.1f),
                        PathNode.QuadTo(163.6f, 252.1f, 232.6f, 207.6f),
                        PathNode.QuadTo(301.6f, 163.1f, 418.6f, 137.6f),
                        PathNode.QuadTo(535.6f, 112.1f, 673.6f, 112.1f),
                        PathNode.QuadTo(810.6f, 112.1f, 927.6f, 137.6f),
                        PathNode.QuadTo(1044.6f, 163.1f, 1113.1f, 207.6f),
                        PathNode.QuadTo(1181.6f, 252.1f, 1181.6f, 307.1f),
                        PathNode.Close,
                        PathNode.MoveTo(549.6f, 883.1f),
                        PathNode.QuadTo(549.6f, 918.1f, 567.1f, 948.6f),
                        PathNode.QuadTo(584.6f, 979.1f, 614.6f, 996.6f),
                        PathNode.QuadTo(644.6f, 1014.1f, 680.6f, 1014.1f),
                        PathNode.QuadTo(715.6f, 1014.1f, 746.1f, 996.6f),
                        PathNode.QuadTo(776.6f, 979.1f, 794.1f, 948.6f),
                        PathNode.QuadTo(811.6f, 918.1f, 811.6f, 883.1f),
                        PathNode.QuadTo(811.6f, 847.1f, 794.1f, 817.1f),
                        PathNode.QuadTo(776.6f, 787.1f, 746.1f, 769.6f),
                        PathNode.QuadTo(715.6f, 752.1f, 680.6f, 752.1f),
                        PathNode.QuadTo(644.6f, 752.1f, 614.6f, 769.6f),
                        PathNode.QuadTo(584.6f, 787.1f, 567.1f, 817.1f),
                        PathNode.QuadTo(549.6f, 847.1f, 549.6f, 883.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _locationDemibold!!
    }

private var _locationDemibold: ImageVector? = null
