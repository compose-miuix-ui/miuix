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

val MiuixIcons.Glass.Show: ImageVector
    get() = MiuixIcons.Glass.Regular.Show

val MiuixIcons.Glass.Light.Show: ImageVector
    get() {
        if (_showLight != null) return _showLight!!
        _showLight = ImageVector.Builder(
            name = "Show.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1122.9f, 525.8f),
                        PathNode.QuadTo(1132.5f, 547.5f, 1132.5f, 571.4f),
                        PathNode.QuadTo(1132.5f, 595.3f, 1122.9f, 617.0f),
                        PathNode.QuadTo(1076.3f, 720.3f, 989.9f, 801.4f),
                        PathNode.QuadTo(903.5f, 882.5f, 794.3f, 927.8f),
                        PathNode.QuadTo(685.1f, 973.2f, 571.6f, 973.2f),
                        PathNode.QuadTo(457.2f, 973.2f, 348.3f, 927.7f),
                        PathNode.QuadTo(239.3f, 882.3f, 153.0f, 801.3f),
                        PathNode.QuadTo(66.8f, 720.3f, 20.4f, 616.6f),
                        PathNode.QuadTo(10.3f, 594.8f, 10.3f, 571.4f),
                        PathNode.QuadTo(10.3f, 548.0f, 20.4f, 526.3f),
                        PathNode.QuadTo(67.0f, 422.6f, 153.7f, 341.5f),
                        PathNode.QuadTo(240.3f, 260.4f, 349.5f, 215.0f),
                        PathNode.QuadTo(458.8f, 169.7f, 571.4f, 169.7f),
                        PathNode.QuadTo(684.1f, 169.7f, 793.0f, 215.0f),
                        PathNode.QuadTo(902.0f, 260.4f, 988.9f, 341.5f),
                        PathNode.QuadTo(1075.8f, 422.6f, 1122.9f, 525.8f),
                        PathNode.Close,
                        PathNode.MoveTo(73.0f, 551.2f),
                        PathNode.QuadTo(69.1f, 560.7f, 69.1f, 571.7f),
                        PathNode.QuadTo(69.1f, 582.6f, 73.0f, 592.3f),
                        PathNode.QuadTo(115.8f, 685.4f, 193.7f, 758.3f),
                        PathNode.QuadTo(271.7f, 831.3f, 370.4f, 872.3f),
                        PathNode.QuadTo(469.1f, 913.4f, 571.4f, 913.4f),
                        PathNode.QuadTo(673.8f, 913.4f, 772.5f, 872.3f),
                        PathNode.QuadTo(871.2f, 831.3f, 949.1f, 758.0f),
                        PathNode.QuadTo(1027.0f, 684.8f, 1069.3f, 592.3f),
                        PathNode.QuadTo(1073.2f, 582.7f, 1073.2f, 571.8f),
                        PathNode.QuadTo(1073.2f, 560.8f, 1069.3f, 551.2f),
                        PathNode.QuadTo(1026.9f, 458.1f, 948.2f, 384.6f),
                        PathNode.QuadTo(869.5f, 311.2f, 770.7f, 269.9f),
                        PathNode.QuadTo(671.8f, 228.5f, 571.4f, 228.5f),
                        PathNode.QuadTo(470.1f, 228.5f, 371.3f, 269.9f),
                        PathNode.QuadTo(272.6f, 311.2f, 194.0f, 384.6f),
                        PathNode.QuadTo(115.4f, 458.1f, 73.0f, 551.2f),
                        PathNode.Close,
                        PathNode.MoveTo(785.9f, 570.7f),
                        PathNode.QuadTo(785.9f, 629.8f, 757.0f, 679.2f),
                        PathNode.QuadTo(728.2f, 728.5f, 679.0f, 757.4f),
                        PathNode.QuadTo(629.8f, 786.4f, 571.4f, 786.4f),
                        PathNode.QuadTo(513.1f, 786.4f, 463.9f, 757.4f),
                        PathNode.QuadTo(414.7f, 728.5f, 385.8f, 679.1f),
                        PathNode.QuadTo(357.0f, 629.8f, 357.0f, 570.9f),
                        PathNode.QuadTo(357.0f, 512.6f, 385.9f, 463.5f),
                        PathNode.QuadTo(414.9f, 414.4f, 464.0f, 385.4f),
                        PathNode.QuadTo(513.1f, 356.5f, 571.4f, 356.5f),
                        PathNode.QuadTo(629.8f, 356.5f, 678.9f, 385.4f),
                        PathNode.QuadTo(728.0f, 414.4f, 756.9f, 463.5f),
                        PathNode.QuadTo(785.9f, 512.6f, 785.9f, 570.7f),
                        PathNode.Close,
                        PathNode.MoveTo(415.8f, 571.0f),
                        PathNode.QuadTo(415.8f, 612.9f, 436.7f, 648.8f),
                        PathNode.QuadTo(457.7f, 684.7f, 493.6f, 705.6f),
                        PathNode.QuadTo(529.5f, 726.6f, 571.5f, 726.6f),
                        PathNode.QuadTo(613.4f, 726.6f, 649.3f, 705.6f),
                        PathNode.QuadTo(685.2f, 684.6f, 706.1f, 648.7f),
                        PathNode.QuadTo(727.1f, 612.9f, 727.1f, 570.9f),
                        PathNode.QuadTo(727.1f, 528.9f, 706.1f, 493.1f),
                        PathNode.QuadTo(685.1f, 457.2f, 649.2f, 436.2f),
                        PathNode.QuadTo(613.4f, 415.3f, 571.4f, 415.3f),
                        PathNode.QuadTo(529.4f, 415.3f, 493.6f, 436.2f),
                        PathNode.QuadTo(457.7f, 457.2f, 436.7f, 493.1f),
                        PathNode.QuadTo(415.8f, 529.0f, 415.8f, 571.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showLight!!
    }

private var _showLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Show: ImageVector
    get() {
        if (_showNormal != null) return _showNormal!!
        _showNormal = ImageVector.Builder(
            name = "Show.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1128.1f, 522.5f),
                        PathNode.QuadTo(1138.7f, 545.7f, 1138.7f, 571.4f),
                        PathNode.QuadTo(1138.7f, 597.1f, 1128.1f, 620.4f),
                        PathNode.QuadTo(1081.2f, 724.8f, 994.1f, 806.6f),
                        PathNode.QuadTo(906.9f, 888.3f, 796.5f, 934.1f),
                        PathNode.QuadTo(686.0f, 979.9f, 571.5f, 979.9f),
                        PathNode.QuadTo(456.0f, 979.9f, 346.0f, 934.0f),
                        PathNode.QuadTo(236.0f, 888.2f, 148.8f, 806.5f),
                        PathNode.QuadTo(61.7f, 724.9f, 14.9f, 620.3f),
                        PathNode.QuadTo(4.2f, 597.0f, 4.2f, 571.4f),
                        PathNode.QuadTo(4.2f, 545.9f, 14.9f, 522.6f),
                        PathNode.QuadTo(61.8f, 418.0f, 149.4f, 336.3f),
                        PathNode.QuadTo(237.0f, 254.6f, 347.4f, 208.8f),
                        PathNode.QuadTo(457.8f, 163.0f, 571.4f, 163.0f),
                        PathNode.QuadTo(685.0f, 163.0f, 795.0f, 208.8f),
                        PathNode.QuadTo(905.0f, 254.6f, 992.7f, 336.3f),
                        PathNode.QuadTo(1080.3f, 418.0f, 1128.1f, 522.5f),
                        PathNode.Close,
                        PathNode.MoveTo(84.3f, 555.1f),
                        PathNode.QuadTo(81.0f, 562.9f, 81.0f, 571.8f),
                        PathNode.QuadTo(81.0f, 580.8f, 84.3f, 588.6f),
                        PathNode.QuadTo(125.8f, 679.6f, 202.1f, 750.8f),
                        PathNode.QuadTo(278.4f, 822.1f, 374.9f, 862.1f),
                        PathNode.QuadTo(471.4f, 902.1f, 571.4f, 902.1f),
                        PathNode.QuadTo(671.5f, 902.1f, 768.0f, 862.1f),
                        PathNode.QuadTo(864.5f, 822.1f, 940.4f, 750.4f),
                        PathNode.QuadTo(1016.3f, 678.7f, 1057.7f, 588.6f),
                        PathNode.QuadTo(1061.0f, 580.8f, 1061.0f, 571.9f),
                        PathNode.QuadTo(1061.0f, 563.0f, 1057.7f, 555.1f),
                        PathNode.QuadTo(1016.3f, 464.2f, 939.4f, 392.4f),
                        PathNode.QuadTo(862.6f, 320.6f, 766.0f, 280.2f),
                        PathNode.QuadTo(669.5f, 239.8f, 571.4f, 239.8f),
                        PathNode.QuadTo(472.4f, 239.8f, 375.9f, 280.2f),
                        PathNode.QuadTo(279.3f, 320.6f, 202.5f, 392.4f),
                        PathNode.QuadTo(125.7f, 464.2f, 84.3f, 555.1f),
                        PathNode.Close,
                        PathNode.MoveTo(793.8f, 570.9f),
                        PathNode.QuadTo(793.8f, 632.0f, 763.8f, 683.1f),
                        PathNode.QuadTo(733.8f, 734.3f, 682.7f, 764.3f),
                        PathNode.QuadTo(631.6f, 794.3f, 571.4f, 794.3f),
                        PathNode.QuadTo(511.3f, 794.3f, 460.1f, 764.3f),
                        PathNode.QuadTo(409.0f, 734.3f, 379.0f, 683.1f),
                        PathNode.QuadTo(349.0f, 631.9f, 349.0f, 570.9f),
                        PathNode.QuadTo(349.0f, 510.8f, 379.1f, 459.7f),
                        PathNode.QuadTo(409.1f, 408.6f, 460.2f, 378.6f),
                        PathNode.QuadTo(511.3f, 348.5f, 571.4f, 348.5f),
                        PathNode.QuadTo(631.6f, 348.5f, 682.7f, 378.6f),
                        PathNode.QuadTo(733.8f, 408.6f, 763.8f, 459.7f),
                        PathNode.QuadTo(793.8f, 510.8f, 793.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(425.8f, 570.9f),
                        PathNode.QuadTo(425.8f, 610.2f, 445.5f, 643.7f),
                        PathNode.QuadTo(465.1f, 677.3f, 498.6f, 696.9f),
                        PathNode.QuadTo(532.2f, 716.5f, 571.4f, 716.5f),
                        PathNode.QuadTo(610.7f, 716.5f, 644.2f, 696.9f),
                        PathNode.QuadTo(677.8f, 677.3f, 697.4f, 643.7f),
                        PathNode.QuadTo(717.0f, 610.2f, 717.0f, 570.9f),
                        PathNode.QuadTo(717.0f, 531.7f, 697.4f, 498.1f),
                        PathNode.QuadTo(677.8f, 464.6f, 644.2f, 445.0f),
                        PathNode.QuadTo(610.7f, 425.3f, 571.4f, 425.3f),
                        PathNode.QuadTo(532.2f, 425.3f, 498.6f, 445.0f),
                        PathNode.QuadTo(465.1f, 464.6f, 445.5f, 498.1f),
                        PathNode.QuadTo(425.8f, 531.7f, 425.8f, 570.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showNormal!!
    }

private var _showNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Show: ImageVector
    get() {
        if (_showRegular != null) return _showRegular!!
        _showRegular = ImageVector.Builder(
            name = "Show.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1130.4f, 520.9f),
                        PathNode.QuadTo(1141.4f, 544.9f, 1141.4f, 571.4f),
                        PathNode.QuadTo(1141.4f, 597.9f, 1130.4f, 621.9f),
                        PathNode.QuadTo(1083.4f, 726.9f, 995.9f, 808.9f),
                        PathNode.QuadTo(908.4f, 890.9f, 797.4f, 936.9f),
                        PathNode.QuadTo(686.4f, 982.9f, 571.4f, 982.9f),
                        PathNode.QuadTo(455.4f, 982.9f, 344.9f, 936.9f),
                        PathNode.QuadTo(234.4f, 890.9f, 146.9f, 808.9f),
                        PathNode.QuadTo(59.4f, 726.9f, 12.4f, 621.9f),
                        PathNode.QuadTo(1.4f, 597.9f, 1.4f, 571.4f),
                        PathNode.QuadTo(1.4f, 544.9f, 12.4f, 520.9f),
                        PathNode.QuadTo(59.4f, 415.9f, 147.4f, 333.9f),
                        PathNode.QuadTo(235.4f, 251.9f, 346.4f, 205.9f),
                        PathNode.QuadTo(457.4f, 159.9f, 571.4f, 159.9f),
                        PathNode.QuadTo(685.4f, 159.9f, 795.9f, 205.9f),
                        PathNode.QuadTo(906.4f, 251.9f, 994.4f, 333.9f),
                        PathNode.QuadTo(1082.4f, 415.9f, 1130.4f, 520.9f),
                        PathNode.Close,
                        PathNode.MoveTo(89.4f, 556.9f),
                        PathNode.QuadTo(86.4f, 563.9f, 86.4f, 571.9f),
                        PathNode.QuadTo(86.4f, 579.9f, 89.4f, 586.9f),
                        PathNode.QuadTo(130.4f, 676.9f, 205.9f, 747.4f),
                        PathNode.QuadTo(281.4f, 817.9f, 376.9f, 857.4f),
                        PathNode.QuadTo(472.4f, 896.9f, 571.4f, 896.9f),
                        PathNode.QuadTo(670.4f, 896.9f, 765.9f, 857.4f),
                        PathNode.QuadTo(861.4f, 817.9f, 936.4f, 746.9f),
                        PathNode.QuadTo(1011.4f, 675.9f, 1052.4f, 586.9f),
                        PathNode.QuadTo(1055.4f, 579.9f, 1055.4f, 571.9f),
                        PathNode.QuadTo(1055.4f, 563.9f, 1052.4f, 556.9f),
                        PathNode.QuadTo(1011.4f, 466.9f, 935.4f, 395.9f),
                        PathNode.QuadTo(859.4f, 324.9f, 763.9f, 284.9f),
                        PathNode.QuadTo(668.4f, 244.9f, 571.4f, 244.9f),
                        PathNode.QuadTo(473.4f, 244.9f, 377.9f, 284.9f),
                        PathNode.QuadTo(282.4f, 324.9f, 206.4f, 395.9f),
                        PathNode.QuadTo(130.4f, 466.9f, 89.4f, 556.9f),
                        PathNode.Close,
                        PathNode.MoveTo(797.4f, 570.9f),
                        PathNode.QuadTo(797.4f, 632.9f, 766.9f, 684.9f),
                        PathNode.QuadTo(736.4f, 736.9f, 684.4f, 767.4f),
                        PathNode.QuadTo(632.4f, 797.9f, 571.4f, 797.9f),
                        PathNode.QuadTo(510.4f, 797.9f, 458.4f, 767.4f),
                        PathNode.QuadTo(406.4f, 736.9f, 375.9f, 684.9f),
                        PathNode.QuadTo(345.4f, 632.9f, 345.4f, 570.9f),
                        PathNode.QuadTo(345.4f, 509.9f, 375.9f, 457.9f),
                        PathNode.QuadTo(406.4f, 405.9f, 458.4f, 375.4f),
                        PathNode.QuadTo(510.4f, 344.9f, 571.4f, 344.9f),
                        PathNode.QuadTo(632.4f, 344.9f, 684.4f, 375.4f),
                        PathNode.QuadTo(736.4f, 405.9f, 766.9f, 457.9f),
                        PathNode.QuadTo(797.4f, 509.9f, 797.4f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(430.4f, 570.9f),
                        PathNode.QuadTo(430.4f, 608.9f, 449.4f, 641.4f),
                        PathNode.QuadTo(468.4f, 673.9f, 500.9f, 692.9f),
                        PathNode.QuadTo(533.4f, 711.9f, 571.4f, 711.9f),
                        PathNode.QuadTo(609.4f, 711.9f, 641.9f, 692.9f),
                        PathNode.QuadTo(674.4f, 673.9f, 693.4f, 641.4f),
                        PathNode.QuadTo(712.4f, 608.9f, 712.4f, 570.9f),
                        PathNode.QuadTo(712.4f, 532.9f, 693.4f, 500.4f),
                        PathNode.QuadTo(674.4f, 467.9f, 641.9f, 448.9f),
                        PathNode.QuadTo(609.4f, 429.9f, 571.4f, 429.9f),
                        PathNode.QuadTo(533.4f, 429.9f, 500.9f, 448.9f),
                        PathNode.QuadTo(468.4f, 467.9f, 449.4f, 500.4f),
                        PathNode.QuadTo(430.4f, 532.9f, 430.4f, 570.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showRegular!!
    }

private var _showRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Show: ImageVector
    get() {
        if (_showMedium != null) return _showMedium!!
        _showMedium = ImageVector.Builder(
            name = "Show.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1135.8f, 517.4f),
                        PathNode.QuadTo(1147.6f, 543.1f, 1147.6f, 571.5f),
                        PathNode.QuadTo(1147.6f, 599.8f, 1135.8f, 625.4f),
                        PathNode.QuadTo(1088.4f, 731.5f, 1000.0f, 814.3f),
                        PathNode.QuadTo(911.7f, 897.1f, 799.7f, 943.6f),
                        PathNode.QuadTo(687.8f, 990.0f, 571.5f, 990.0f),
                        PathNode.QuadTo(454.3f, 990.0f, 342.6f, 943.6f),
                        PathNode.QuadTo(230.9f, 897.1f, 142.7f, 814.3f),
                        PathNode.QuadTo(54.6f, 731.5f, 7.0f, 625.4f),
                        PathNode.QuadTo(-4.8f, 599.8f, -4.8f, 571.4f),
                        PathNode.QuadTo(-4.8f, 543.0f, 7.0f, 517.4f),
                        PathNode.QuadTo(54.6f, 411.3f, 143.2f, 328.5f),
                        PathNode.QuadTo(231.9f, 245.7f, 344.0f, 199.3f),
                        PathNode.QuadTo(456.1f, 152.9f, 571.4f, 152.9f),
                        PathNode.QuadTo(686.8f, 152.9f, 798.3f, 199.3f),
                        PathNode.QuadTo(909.9f, 245.8f, 998.7f, 328.6f),
                        PathNode.QuadTo(1087.6f, 411.3f, 1135.8f, 517.4f),
                        PathNode.Close,
                        PathNode.MoveTo(100.5f, 560.7f),
                        PathNode.QuadTo(98.3f, 566.1f, 98.3f, 571.9f),
                        PathNode.QuadTo(98.3f, 577.8f, 100.5f, 583.1f),
                        PathNode.QuadTo(140.6f, 670.9f, 214.4f, 739.7f),
                        PathNode.QuadTo(288.2f, 808.5f, 381.5f, 847.2f),
                        PathNode.QuadTo(474.7f, 885.8f, 571.4f, 885.8f),
                        PathNode.QuadTo(668.1f, 885.8f, 761.4f, 847.2f),
                        PathNode.QuadTo(854.7f, 808.5f, 928.1f, 739.4f),
                        PathNode.QuadTo(1001.4f, 670.3f, 1041.3f, 583.1f),
                        PathNode.QuadTo(1043.5f, 577.8f, 1043.5f, 571.9f),
                        PathNode.QuadTo(1043.5f, 566.1f, 1041.3f, 560.7f),
                        PathNode.QuadTo(1001.3f, 472.8f, 927.0f, 403.5f),
                        PathNode.QuadTo(852.7f, 334.1f, 759.4f, 295.1f),
                        PathNode.QuadTo(666.2f, 256.0f, 571.5f, 256.0f),
                        PathNode.QuadTo(475.9f, 256.0f, 382.5f, 295.1f),
                        PathNode.QuadTo(289.2f, 334.1f, 214.9f, 403.5f),
                        PathNode.QuadTo(140.6f, 472.8f, 100.5f, 560.7f),
                        PathNode.Close,
                        PathNode.MoveTo(803.1f, 570.8f),
                        PathNode.QuadTo(803.1f, 634.4f, 771.9f, 687.7f),
                        PathNode.QuadTo(740.8f, 741.0f, 687.4f, 772.3f),
                        PathNode.QuadTo(634.0f, 803.6f, 571.4f, 803.6f),
                        PathNode.QuadTo(508.8f, 803.6f, 455.5f, 772.3f),
                        PathNode.QuadTo(402.1f, 741.0f, 370.9f, 687.7f),
                        PathNode.QuadTo(339.8f, 634.4f, 339.8f, 570.8f),
                        PathNode.QuadTo(339.8f, 508.3f, 370.9f, 455.0f),
                        PathNode.QuadTo(402.1f, 401.6f, 455.5f, 370.4f),
                        PathNode.QuadTo(508.8f, 339.3f, 571.4f, 339.3f),
                        PathNode.QuadTo(634.1f, 339.3f, 687.4f, 370.4f),
                        PathNode.QuadTo(740.8f, 401.6f, 771.9f, 455.0f),
                        PathNode.QuadTo(803.1f, 508.3f, 803.1f, 570.8f),
                        PathNode.Close,
                        PathNode.MoveTo(438.0f, 570.9f),
                        PathNode.QuadTo(438.0f, 606.8f, 456.1f, 637.5f),
                        PathNode.QuadTo(474.1f, 668.3f, 504.8f, 686.3f),
                        PathNode.QuadTo(535.5f, 704.4f, 571.4f, 704.4f),
                        PathNode.QuadTo(607.3f, 704.4f, 638.0f, 686.3f),
                        PathNode.QuadTo(668.8f, 668.3f, 686.8f, 637.6f),
                        PathNode.QuadTo(704.9f, 606.9f, 704.9f, 571.0f),
                        PathNode.QuadTo(704.9f, 535.1f, 686.8f, 504.3f),
                        PathNode.QuadTo(668.8f, 473.6f, 638.1f, 455.6f),
                        PathNode.QuadTo(607.4f, 437.5f, 571.5f, 437.5f),
                        PathNode.QuadTo(535.6f, 437.5f, 504.8f, 455.6f),
                        PathNode.QuadTo(474.1f, 473.6f, 456.1f, 504.3f),
                        PathNode.QuadTo(438.0f, 535.0f, 438.0f, 570.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showMedium!!
    }

private var _showMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Show: ImageVector
    get() {
        if (_showDemibold != null) return _showDemibold!!
        _showDemibold = ImageVector.Builder(
            name = "Show.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1139.6f, 515.0f),
                        PathNode.QuadTo(1152.0f, 541.8f, 1152.0f, 571.5f),
                        PathNode.QuadTo(1152.0f, 601.1f, 1139.6f, 627.9f),
                        PathNode.QuadTo(1091.8f, 734.7f, 1002.9f, 818.1f),
                        PathNode.QuadTo(913.9f, 901.5f, 801.3f, 948.2f),
                        PathNode.QuadTo(688.7f, 994.9f, 571.6f, 994.9f),
                        PathNode.QuadTo(453.5f, 994.9f, 341.0f, 948.2f),
                        PathNode.QuadTo(228.5f, 901.5f, 139.8f, 818.1f),
                        PathNode.QuadTo(51.2f, 734.7f, 3.2f, 627.9f),
                        PathNode.QuadTo(-9.1f, 601.0f, -9.1f, 571.4f),
                        PathNode.QuadTo(-9.1f, 541.7f, 3.2f, 515.0f),
                        PathNode.QuadTo(51.2f, 408.1f, 140.3f, 324.7f),
                        PathNode.QuadTo(229.5f, 241.4f, 342.3f, 194.7f),
                        PathNode.QuadTo(455.1f, 148.0f, 571.4f, 148.0f),
                        PathNode.QuadTo(687.7f, 148.0f, 800.0f, 194.7f),
                        PathNode.QuadTo(912.3f, 241.5f, 1001.7f, 324.8f),
                        PathNode.QuadTo(1091.2f, 408.1f, 1139.6f, 515.0f),
                        PathNode.Close,
                        PathNode.MoveTo(108.3f, 563.4f),
                        PathNode.QuadTo(106.6f, 567.6f, 106.6f, 571.9f),
                        PathNode.QuadTo(106.6f, 576.3f, 108.3f, 580.5f),
                        PathNode.QuadTo(147.7f, 666.7f, 220.3f, 734.3f),
                        PathNode.QuadTo(292.9f, 801.8f, 384.6f, 840.0f),
                        PathNode.QuadTo(476.3f, 878.1f, 571.4f, 878.1f),
                        PathNode.QuadTo(666.5f, 878.1f, 758.2f, 840.0f),
                        PathNode.QuadTo(849.9f, 801.8f, 922.2f, 734.1f),
                        PathNode.QuadTo(994.4f, 666.3f, 1033.6f, 580.5f),
                        PathNode.QuadTo(1035.2f, 576.3f, 1035.2f, 571.9f),
                        PathNode.QuadTo(1035.2f, 567.6f, 1033.6f, 563.4f),
                        PathNode.QuadTo(994.2f, 476.9f, 921.0f, 408.7f),
                        PathNode.QuadTo(847.9f, 340.6f, 756.3f, 302.2f),
                        PathNode.QuadTo(664.7f, 263.8f, 571.6f, 263.8f),
                        PathNode.QuadTo(477.6f, 263.8f, 385.7f, 302.2f),
                        PathNode.QuadTo(293.9f, 340.6f, 220.8f, 408.8f),
                        PathNode.QuadTo(147.7f, 476.9f, 108.3f, 563.4f),
                        PathNode.Close,
                        PathNode.MoveTo(807.1f, 570.8f),
                        PathNode.QuadTo(807.1f, 635.4f, 775.4f, 689.6f),
                        PathNode.QuadTo(743.8f, 743.8f, 689.5f, 775.7f),
                        PathNode.QuadTo(635.1f, 807.6f, 571.4f, 807.6f),
                        PathNode.QuadTo(507.7f, 807.6f, 453.4f, 775.7f),
                        PathNode.QuadTo(399.1f, 743.8f, 367.4f, 689.6f),
                        PathNode.QuadTo(335.8f, 635.4f, 335.8f, 570.8f),
                        PathNode.QuadTo(335.8f, 507.2f, 367.4f, 452.9f),
                        PathNode.QuadTo(399.1f, 398.6f, 453.4f, 366.9f),
                        PathNode.QuadTo(507.7f, 335.3f, 571.5f, 335.3f),
                        PathNode.QuadTo(635.2f, 335.3f, 689.5f, 366.9f),
                        PathNode.QuadTo(743.8f, 398.6f, 775.4f, 452.9f),
                        PathNode.QuadTo(807.1f, 507.2f, 807.1f, 570.8f),
                        PathNode.Close,
                        PathNode.MoveTo(443.3f, 570.8f),
                        PathNode.QuadTo(443.3f, 605.3f, 460.7f, 634.8f),
                        PathNode.QuadTo(478.1f, 664.3f, 507.5f, 681.7f),
                        PathNode.QuadTo(536.9f, 699.1f, 571.3f, 699.1f),
                        PathNode.QuadTo(605.8f, 699.1f, 635.3f, 681.7f),
                        PathNode.QuadTo(664.8f, 664.3f, 682.2f, 634.9f),
                        PathNode.QuadTo(699.6f, 605.4f, 699.6f, 571.0f),
                        PathNode.QuadTo(699.6f, 536.6f, 682.2f, 507.1f),
                        PathNode.QuadTo(664.8f, 477.6f, 635.4f, 460.2f),
                        PathNode.QuadTo(605.9f, 442.8f, 571.5f, 442.8f),
                        PathNode.QuadTo(537.1f, 442.8f, 507.6f, 460.2f),
                        PathNode.QuadTo(478.1f, 477.6f, 460.7f, 507.0f),
                        PathNode.QuadTo(443.3f, 536.4f, 443.3f, 570.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _showDemibold!!
    }

private var _showDemibold: ImageVector? = null
