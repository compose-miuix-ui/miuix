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

val MiuixIcons.Glass.Promotions: ImageVector
    get() = MiuixIcons.Glass.Regular.Promotions

val MiuixIcons.Glass.Light.Promotions: ImageVector
    get() {
        if (_promotionsLight != null) return _promotionsLight!!
        _promotionsLight = ImageVector.Builder(
            name = "Promotions.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1102.0f, 668.4f),
                        PathNode.QuadTo(1102.0f, 777.7f, 1082.9f, 869.6f),
                        PathNode.QuadTo(1063.9f, 961.5f, 1032.8f, 1015.2f),
                        PathNode.QuadTo(1001.8f, 1068.8f, 967.5f, 1068.8f),
                        PathNode.QuadTo(957.5f, 1068.8f, 949.9f, 1065.3f),
                        PathNode.QuadTo(931.0f, 1057.7f, 897.7f, 1043.9f),
                        PathNode.QuadTo(822.2f, 1010.0f, 747.3f, 973.3f),
                        PathNode.QuadTo(625.0f, 913.9f, 496.5f, 885.4f),
                        PathNode.QuadTo(488.8f, 872.8f, 479.0f, 841.0f),
                        PathNode.QuadTo(460.0f, 770.0f, 460.0f, 665.7f),
                        PathNode.QuadTo(460.0f, 561.4f, 480.0f, 491.9f),
                        PathNode.QuadTo(483.5f, 478.0f, 488.2f, 467.3f),
                        PathNode.QuadTo(492.9f, 456.6f, 497.5f, 448.4f),
                        PathNode.QuadTo(606.7f, 424.0f, 719.9f, 372.6f),
                        PathNode.QuadTo(819.3f, 325.8f, 871.7f, 302.5f),
                        PathNode.QuadTo(924.0f, 279.2f, 954.1f, 269.7f),
                        PathNode.QuadTo(958.3f, 268.1f, 967.5f, 268.1f),
                        PathNode.QuadTo(1001.8f, 268.1f, 1032.8f, 321.7f),
                        PathNode.QuadTo(1063.9f, 375.3f, 1082.9f, 467.3f),
                        PathNode.QuadTo(1102.0f, 559.2f, 1102.0f, 668.4f),
                        PathNode.Close,
                        PathNode.MoveTo(513.7f, 164.0f),
                        PathNode.LineTo(433.3f, 467.3f),
                        PathNode.QuadTo(420.3f, 516.4f, 414.1f, 587.6f),
                        PathNode.QuadTo(407.9f, 658.8f, 412.8f, 736.1f),
                        PathNode.QuadTo(417.7f, 813.4f, 436.0f, 877.7f),
                        PathNode.LineTo(253.1f, 877.7f),
                        PathNode.QuadTo(195.8f, 877.7f, 147.0f, 849.0f),
                        PathNode.QuadTo(98.2f, 820.3f, 69.5f, 771.5f),
                        PathNode.QuadTo(40.9f, 722.8f, 40.9f, 665.4f),
                        PathNode.QuadTo(40.9f, 607.1f, 69.5f, 558.3f),
                        PathNode.QuadTo(98.2f, 509.6f, 147.0f, 480.9f),
                        PathNode.QuadTo(195.8f, 452.2f, 253.1f, 452.2f),
                        PathNode.LineTo(294.2f, 452.2f),
                        PathNode.LineTo(374.7f, 128.8f),
                        PathNode.QuadTo(382.3f, 100.1f, 407.6f, 84.8f),
                        PathNode.QuadTo(432.9f, 69.4f, 461.5f, 76.1f),
                        PathNode.QuadTo(491.1f, 84.2f, 506.2f, 109.5f),
                        PathNode.QuadTo(521.3f, 134.9f, 513.7f, 164.0f),
                        PathNode.Close,
                        PathNode.MoveTo(891.0f, 669.4f),
                        PathNode.QuadTo(891.0f, 757.4f, 899.9f, 829.0f),
                        PathNode.QuadTo(908.8f, 900.6f, 926.1f, 942.1f),
                        PathNode.QuadTo(943.4f, 983.6f, 967.1f, 983.6f),
                        PathNode.QuadTo(988.0f, 983.6f, 1005.3f, 940.3f),
                        PathNode.QuadTo(1022.7f, 897.0f, 1032.4f, 824.6f),
                        PathNode.QuadTo(1042.2f, 752.1f, 1042.2f, 669.4f),
                        PathNode.QuadTo(1042.2f, 585.8f, 1032.4f, 513.3f),
                        PathNode.QuadTo(1022.7f, 440.9f, 1005.3f, 397.6f),
                        PathNode.QuadTo(988.0f, 354.3f, 967.1f, 354.3f),
                        PathNode.QuadTo(943.4f, 354.3f, 926.1f, 395.6f),
                        PathNode.QuadTo(908.8f, 436.9f, 899.9f, 508.7f),
                        PathNode.QuadTo(891.0f, 580.4f, 891.0f, 669.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsLight!!
    }

private var _promotionsLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Promotions: ImageVector
    get() {
        if (_promotionsNormal != null) return _promotionsNormal!!
        _promotionsNormal = ImageVector.Builder(
            name = "Promotions.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1120.2f, 667.2f),
                        PathNode.QuadTo(1120.2f, 777.7f, 1100.8f, 870.7f),
                        PathNode.QuadTo(1081.4f, 963.7f, 1048.7f, 1017.9f),
                        PathNode.QuadTo(1016.0f, 1072.1f, 977.8f, 1072.1f),
                        PathNode.QuadTo(967.8f, 1072.1f, 959.2f, 1069.0f),
                        PathNode.QuadTo(943.7f, 1063.2f, 905.7f, 1047.9f),
                        PathNode.QuadTo(833.4f, 1014.6f, 756.3f, 977.0f),
                        PathNode.QuadTo(634.9f, 917.9f, 506.8f, 889.7f),
                        PathNode.QuadTo(497.5f, 876.1f, 486.2f, 840.1f),
                        PathNode.QuadTo(467.2f, 769.1f, 467.2f, 664.6f),
                        PathNode.QuadTo(467.2f, 560.2f, 487.2f, 490.4f),
                        PathNode.QuadTo(491.0f, 474.3f, 496.6f, 462.4f),
                        PathNode.QuadTo(502.2f, 450.4f, 507.8f, 441.7f),
                        PathNode.QuadTo(617.5f, 417.6f, 729.2f, 366.4f),
                        PathNode.QuadTo(825.6f, 320.9f, 878.4f, 297.5f),
                        PathNode.QuadTo(931.2f, 274.0f, 961.9f, 264.2f),
                        PathNode.QuadTo(967.3f, 262.3f, 977.8f, 262.3f),
                        PathNode.QuadTo(1016.0f, 262.3f, 1048.7f, 316.5f),
                        PathNode.QuadTo(1081.4f, 370.7f, 1100.8f, 463.8f),
                        PathNode.QuadTo(1120.2f, 556.8f, 1120.2f, 667.2f),
                        PathNode.Close,
                        PathNode.MoveTo(508.0f, 171.3f),
                        PathNode.LineTo(428.6f, 471.0f),
                        PathNode.QuadTo(415.6f, 520.7f, 409.5f, 592.8f),
                        PathNode.QuadTo(403.4f, 664.9f, 409.1f, 742.7f),
                        PathNode.QuadTo(414.7f, 820.5f, 434.9f, 883.2f),
                        PathNode.LineTo(241.6f, 883.2f),
                        PathNode.QuadTo(182.5f, 883.2f, 132.2f, 853.6f),
                        PathNode.QuadTo(81.9f, 824.0f, 52.3f, 773.7f),
                        PathNode.QuadTo(22.7f, 723.4f, 22.7f, 664.2f),
                        PathNode.QuadTo(22.7f, 604.0f, 52.3f, 553.7f),
                        PathNode.QuadTo(81.9f, 503.4f, 132.2f, 473.9f),
                        PathNode.QuadTo(182.5f, 444.3f, 241.6f, 444.3f),
                        PathNode.LineTo(275.1f, 444.3f),
                        PathNode.LineTo(352.5f, 132.1f),
                        PathNode.QuadTo(361.1f, 99.8f, 389.3f, 82.6f),
                        PathNode.QuadTo(417.4f, 65.5f, 449.8f, 73.0f),
                        PathNode.QuadTo(482.4f, 81.8f, 499.5f, 110.3f),
                        PathNode.QuadTo(516.6f, 138.9f, 508.0f, 171.3f),
                        PathNode.Close,
                        PathNode.MoveTo(911.9f, 668.2f),
                        PathNode.QuadTo(911.9f, 750.7f, 920.2f, 819.8f),
                        PathNode.QuadTo(928.5f, 888.9f, 943.5f, 929.1f),
                        PathNode.QuadTo(958.6f, 969.2f, 977.6f, 969.2f),
                        PathNode.QuadTo(995.2f, 969.2f, 1010.2f, 928.5f),
                        PathNode.QuadTo(1025.2f, 887.8f, 1033.8f, 818.4f),
                        PathNode.QuadTo(1042.4f, 749.0f, 1042.4f, 668.2f),
                        PathNode.QuadTo(1042.4f, 586.4f, 1033.8f, 517.0f),
                        PathNode.QuadTo(1025.2f, 447.6f, 1010.2f, 406.9f),
                        PathNode.QuadTo(995.2f, 366.2f, 977.6f, 366.2f),
                        PathNode.QuadTo(958.6f, 366.2f, 943.5f, 406.3f),
                        PathNode.QuadTo(928.5f, 446.4f, 920.2f, 515.5f),
                        PathNode.QuadTo(911.9f, 584.7f, 911.9f, 668.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsNormal!!
    }

private var _promotionsNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Promotions: ImageVector
    get() {
        if (_promotionsRegular != null) return _promotionsRegular!!
        _promotionsRegular = ImageVector.Builder(
            name = "Promotions.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1128.4f, 666.7f),
                        PathNode.QuadTo(1128.4f, 777.7f, 1108.9f, 871.2f),
                        PathNode.QuadTo(1089.4f, 964.7f, 1055.9f, 1019.2f),
                        PathNode.QuadTo(1022.4f, 1073.7f, 982.4f, 1073.7f),
                        PathNode.QuadTo(972.4f, 1073.7f, 963.4f, 1070.7f),
                        PathNode.QuadTo(949.4f, 1065.7f, 909.4f, 1049.7f),
                        PathNode.QuadTo(838.4f, 1016.7f, 760.4f, 978.7f),
                        PathNode.QuadTo(639.4f, 919.7f, 511.4f, 891.7f),
                        PathNode.QuadTo(501.4f, 877.7f, 489.4f, 839.7f),
                        PathNode.QuadTo(470.4f, 768.7f, 470.4f, 664.2f),
                        PathNode.QuadTo(470.4f, 559.7f, 490.4f, 489.7f),
                        PathNode.QuadTo(494.4f, 472.7f, 500.4f, 460.2f),
                        PathNode.QuadTo(506.4f, 447.7f, 512.4f, 438.7f),
                        PathNode.QuadTo(622.4f, 414.7f, 733.4f, 363.7f),
                        PathNode.QuadTo(828.4f, 318.7f, 881.4f, 295.2f),
                        PathNode.QuadTo(934.4f, 271.7f, 965.4f, 261.7f),
                        PathNode.QuadTo(971.4f, 259.7f, 982.4f, 259.7f),
                        PathNode.QuadTo(1022.4f, 259.7f, 1055.9f, 314.2f),
                        PathNode.QuadTo(1089.4f, 368.7f, 1108.9f, 462.2f),
                        PathNode.QuadTo(1128.4f, 555.7f, 1128.4f, 666.7f),
                        PathNode.Close,
                        PathNode.MoveTo(505.4f, 174.7f),
                        PathNode.LineTo(426.4f, 472.7f),
                        PathNode.QuadTo(413.4f, 522.7f, 407.4f, 595.2f),
                        PathNode.QuadTo(401.4f, 667.7f, 407.4f, 745.7f),
                        PathNode.QuadTo(413.4f, 823.7f, 434.4f, 885.7f),
                        PathNode.LineTo(236.4f, 885.7f),
                        PathNode.QuadTo(176.4f, 885.7f, 125.4f, 855.7f),
                        PathNode.QuadTo(74.4f, 825.7f, 44.4f, 774.7f),
                        PathNode.QuadTo(14.4f, 723.7f, 14.4f, 663.7f),
                        PathNode.QuadTo(14.4f, 602.7f, 44.4f, 551.7f),
                        PathNode.QuadTo(74.4f, 500.7f, 125.4f, 470.7f),
                        PathNode.QuadTo(176.4f, 440.7f, 236.4f, 440.7f),
                        PathNode.LineTo(266.4f, 440.7f),
                        PathNode.LineTo(342.4f, 133.7f),
                        PathNode.QuadTo(351.4f, 99.7f, 380.9f, 81.7f),
                        PathNode.QuadTo(410.4f, 63.7f, 444.4f, 71.7f),
                        PathNode.QuadTo(478.4f, 80.7f, 496.4f, 110.7f),
                        PathNode.QuadTo(514.4f, 140.7f, 505.4f, 174.7f),
                        PathNode.Close,
                        PathNode.MoveTo(921.4f, 667.7f),
                        PathNode.QuadTo(921.4f, 747.7f, 929.4f, 815.7f),
                        PathNode.QuadTo(937.4f, 883.7f, 951.4f, 923.2f),
                        PathNode.QuadTo(965.4f, 962.7f, 982.4f, 962.7f),
                        PathNode.QuadTo(998.4f, 962.7f, 1012.4f, 923.2f),
                        PathNode.QuadTo(1026.4f, 883.7f, 1034.4f, 815.7f),
                        PathNode.QuadTo(1042.4f, 747.7f, 1042.4f, 667.7f),
                        PathNode.QuadTo(1042.4f, 586.7f, 1034.4f, 518.7f),
                        PathNode.QuadTo(1026.4f, 450.7f, 1012.4f, 411.2f),
                        PathNode.QuadTo(998.4f, 371.7f, 982.4f, 371.7f),
                        PathNode.QuadTo(965.4f, 371.7f, 951.4f, 411.2f),
                        PathNode.QuadTo(937.4f, 450.7f, 929.4f, 518.7f),
                        PathNode.QuadTo(921.4f, 586.7f, 921.4f, 667.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsRegular!!
    }

private var _promotionsRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Promotions: ImageVector
    get() {
        if (_promotionsMedium != null) return _promotionsMedium!!
        _promotionsMedium = ImageVector.Builder(
            name = "Promotions.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1145.3f, 666.7f),
                        PathNode.QuadTo(1145.3f, 779.5f, 1125.1f, 874.8f),
                        PathNode.QuadTo(1105.0f, 970.1f, 1069.7f, 1025.9f),
                        PathNode.QuadTo(1034.5f, 1081.8f, 991.2f, 1081.8f),
                        PathNode.QuadTo(980.7f, 1081.8f, 969.8f, 1078.5f),
                        PathNode.QuadTo(952.0f, 1072.4f, 915.2f, 1057.2f),
                        PathNode.QuadTo(852.3f, 1028.3f, 765.7f, 985.9f),
                        PathNode.QuadTo(645.5f, 927.2f, 515.9f, 898.9f),
                        PathNode.QuadTo(503.5f, 884.1f, 490.4f, 841.8f),
                        PathNode.QuadTo(471.1f, 770.0f, 471.1f, 664.2f),
                        PathNode.QuadTo(471.1f, 558.3f, 491.4f, 487.5f),
                        PathNode.QuadTo(496.2f, 468.6f, 503.1f, 454.5f),
                        PathNode.QuadTo(510.1f, 440.4f, 516.9f, 431.4f),
                        PathNode.QuadTo(626.9f, 407.4f, 738.7f, 356.4f),
                        PathNode.QuadTo(834.0f, 311.4f, 887.2f, 287.6f),
                        PathNode.QuadTo(940.5f, 263.8f, 971.8f, 253.8f),
                        PathNode.QuadTo(979.4f, 251.5f, 991.2f, 251.5f),
                        PathNode.QuadTo(1034.5f, 251.5f, 1069.7f, 307.4f),
                        PathNode.QuadTo(1105.0f, 363.2f, 1125.1f, 458.5f),
                        PathNode.QuadTo(1145.3f, 553.8f, 1145.3f, 666.7f),
                        PathNode.Close,
                        PathNode.MoveTo(504.5f, 177.1f),
                        PathNode.LineTo(425.5f, 474.8f),
                        PathNode.QuadTo(412.2f, 525.9f, 406.3f, 600.3f),
                        PathNode.QuadTo(400.5f, 674.7f, 407.6f, 753.8f),
                        PathNode.QuadTo(414.6f, 832.8f, 437.5f, 893.8f),
                        PathNode.LineTo(227.6f, 893.8f),
                        PathNode.QuadTo(165.5f, 893.8f, 112.6f, 862.7f),
                        PathNode.QuadTo(59.7f, 831.6f, 28.6f, 778.7f),
                        PathNode.QuadTo(-2.5f, 725.8f, -2.5f, 663.7f),
                        PathNode.QuadTo(-2.5f, 600.8f, 28.6f, 547.7f),
                        PathNode.QuadTo(59.7f, 494.7f, 112.5f, 463.6f),
                        PathNode.QuadTo(165.2f, 432.5f, 227.6f, 432.5f),
                        PathNode.LineTo(251.4f, 432.5f),
                        PathNode.LineTo(325.8f, 131.8f),
                        PathNode.QuadTo(335.6f, 94.5f, 368.0f, 74.8f),
                        PathNode.QuadTo(400.3f, 55.0f, 437.5f, 63.8f),
                        PathNode.QuadTo(474.8f, 73.6f, 494.5f, 106.6f),
                        PathNode.QuadTo(514.3f, 139.6f, 504.5f, 177.1f),
                        PathNode.Close,
                        PathNode.MoveTo(939.7f, 667.9f),
                        PathNode.QuadTo(939.7f, 747.1f, 947.0f, 813.4f),
                        PathNode.QuadTo(954.3f, 879.6f, 966.7f, 917.9f),
                        PathNode.QuadTo(979.1f, 956.2f, 993.9f, 956.2f),
                        PathNode.QuadTo(1007.8f, 956.2f, 1020.3f, 918.0f),
                        PathNode.QuadTo(1032.8f, 879.9f, 1040.1f, 813.6f),
                        PathNode.QuadTo(1047.4f, 747.4f, 1047.4f, 667.9f),
                        PathNode.QuadTo(1047.4f, 587.7f, 1040.2f, 521.4f),
                        PathNode.QuadTo(1033.1f, 455.0f, 1020.7f, 416.8f),
                        PathNode.QuadTo(1008.3f, 378.7f, 994.2f, 378.7f),
                        PathNode.QuadTo(979.6f, 378.7f, 967.0f, 417.0f),
                        PathNode.QuadTo(954.3f, 455.2f, 947.0f, 521.6f),
                        PathNode.QuadTo(939.7f, 588.0f, 939.7f, 667.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsMedium!!
    }

private var _promotionsMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Promotions: ImageVector
    get() {
        if (_promotionsDemibold != null) return _promotionsDemibold!!
        _promotionsDemibold = ImageVector.Builder(
            name = "Promotions.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1157.1f, 666.6f),
                        PathNode.QuadTo(1157.1f, 780.9f, 1136.5f, 877.4f),
                        PathNode.QuadTo(1115.8f, 973.8f, 1079.4f, 1030.6f),
                        PathNode.QuadTo(1042.9f, 1087.4f, 997.4f, 1087.4f),
                        PathNode.QuadTo(986.4f, 1087.4f, 974.2f, 1084.0f),
                        PathNode.QuadTo(953.8f, 1077.1f, 919.3f, 1062.5f),
                        PathNode.QuadTo(862.1f, 1036.4f, 769.4f, 991.1f),
                        PathNode.QuadTo(649.8f, 932.5f, 519.0f, 904.1f),
                        PathNode.QuadTo(504.9f, 888.7f, 491.0f, 843.3f),
                        PathNode.QuadTo(471.6f, 770.9f, 471.6f, 664.1f),
                        PathNode.QuadTo(471.6f, 557.3f, 492.0f, 486.0f),
                        PathNode.QuadTo(497.4f, 465.8f, 505.0f, 450.5f),
                        PathNode.QuadTo(512.6f, 435.2f, 520.0f, 426.2f),
                        PathNode.QuadTo(630.0f, 402.2f, 742.4f, 351.2f),
                        PathNode.QuadTo(837.8f, 306.2f, 891.3f, 282.3f),
                        PathNode.QuadTo(944.8f, 258.3f, 976.2f, 248.3f),
                        PathNode.QuadTo(985.0f, 245.9f, 997.4f, 245.9f),
                        PathNode.QuadTo(1042.9f, 245.9f, 1079.4f, 302.7f),
                        PathNode.QuadTo(1115.8f, 359.5f, 1136.5f, 455.9f),
                        PathNode.QuadTo(1157.1f, 552.4f, 1157.1f, 666.6f),
                        PathNode.Close,
                        PathNode.MoveTo(503.8f, 178.8f),
                        PathNode.LineTo(424.8f, 476.3f),
                        PathNode.QuadTo(411.4f, 528.2f, 405.6f, 603.9f),
                        PathNode.QuadTo(399.8f, 679.6f, 407.7f, 759.4f),
                        PathNode.QuadTo(415.5f, 839.3f, 439.7f, 899.4f),
                        PathNode.LineTo(221.5f, 899.4f),
                        PathNode.QuadTo(157.8f, 899.4f, 103.6f, 867.6f),
                        PathNode.QuadTo(49.4f, 835.8f, 17.5f, 781.5f),
                        PathNode.QuadTo(-14.3f, 727.3f, -14.3f, 663.6f),
                        PathNode.QuadTo(-14.3f, 599.4f, 17.5f, 545.0f),
                        PathNode.QuadTo(49.4f, 490.5f, 103.4f, 458.7f),
                        PathNode.QuadTo(157.4f, 426.9f, 221.5f, 426.9f),
                        PathNode.LineTo(240.9f, 426.9f),
                        PathNode.LineTo(314.2f, 130.4f),
                        PathNode.QuadTo(324.5f, 90.9f, 358.9f, 69.9f),
                        PathNode.QuadTo(393.2f, 48.9f, 432.7f, 58.3f),
                        PathNode.QuadTo(472.2f, 68.7f, 493.2f, 103.8f),
                        PathNode.QuadTo(514.2f, 138.8f, 503.8f, 178.8f),
                        PathNode.Close,
                        PathNode.MoveTo(952.4f, 668.1f),
                        PathNode.QuadTo(952.4f, 746.7f, 959.3f, 811.7f),
                        PathNode.QuadTo(966.1f, 876.8f, 977.4f, 914.2f),
                        PathNode.QuadTo(988.6f, 951.6f, 1002.0f, 951.6f),
                        PathNode.QuadTo(1014.3f, 951.6f, 1025.8f, 914.4f),
                        PathNode.QuadTo(1037.2f, 877.2f, 1044.1f, 812.2f),
                        PathNode.QuadTo(1050.9f, 747.2f, 1050.9f, 668.1f),
                        PathNode.QuadTo(1050.9f, 588.5f, 1044.3f, 523.2f),
                        PathNode.QuadTo(1037.7f, 458.0f, 1026.4f, 420.8f),
                        PathNode.QuadTo(1015.2f, 383.6f, 1002.4f, 383.6f),
                        PathNode.QuadTo(989.6f, 383.6f, 977.8f, 421.0f),
                        PathNode.QuadTo(966.1f, 458.5f, 959.3f, 523.7f),
                        PathNode.QuadTo(952.4f, 588.9f, 952.4f, 668.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _promotionsDemibold!!
    }

private var _promotionsDemibold: ImageVector? = null
