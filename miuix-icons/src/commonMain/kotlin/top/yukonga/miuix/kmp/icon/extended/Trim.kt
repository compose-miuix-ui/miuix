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

val MiuixIcons.Trim: ImageVector
    get() = MiuixIcons.Regular.Trim

val MiuixIcons.Light.Trim: ImageVector
    get() {
        if (_trimLight != null) return _trimLight!!
        _trimLight = ImageVector.Builder(
            name = "Trim.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1125.0461538461539f,
            viewportHeight = 1125.0461538461539f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -101.97692307692307f, translationY = 935.2923076923078f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(550.0f, 81.41379310344827f),
                PathNode.QuadTo(550.0f, 114.0f, 536.0f, 147.0f),
                PathNode.LineTo(665.0f, 349.0f),
                PathNode.LineTo(793.0f, 148.0f),
                PathNode.QuadTo(779.0f, 115.0f, 779.0f, 81.46857142857142f),
                PathNode.QuadTo(779.0f, 33.034285714285716f, 803.0f, -7.482857142857142f),
                PathNode.QuadTo(827.0f, -48.0f, 867.3115183246073f, -72.0f),
                PathNode.QuadTo(907.6230366492147f, -96.0f, 955.8115183246073f, -96.0f),
                PathNode.QuadTo(1004.0f, -96.0f, 1044.663043478261f, -72.16304347826087f),
                PathNode.QuadTo(1085.3260869565217f, -48.326086956521735f, 1109.163043478261f, -7.663043478260867f),
                PathNode.QuadTo(1133.0f, 33.0f, 1133.0f, 81.18848167539267f),
                PathNode.QuadTo(1133.0f, 129.37696335078533f, 1109.0f, 169.68848167539267f),
                PathNode.QuadTo(1085.0f, 210.0f, 1044.6294964028777f, 234.0f),
                PathNode.QuadTo(1004.2589928057554f, 258.0f, 956.0f, 258.0f),
                PathNode.QuadTo(919.0f, 258.0f, 886.0f, 243.5f),
                PathNode.QuadTo(853.0f, 229.0f, 828.0f, 203.0f),
                PathNode.LineTo(427.0f, 832.0f),
                PathNode.QuadTo(423.0f, 837.0f, 418.5f, 837.5f),
                PathNode.QuadTo(414.0f, 838.0f, 411.0f, 833.0f),
                PathNode.LineTo(390.0f, 805.0f),
                PathNode.QuadTo(386.0f, 799.24f, 386.0f, 793.12f),
                PathNode.QuadTo(386.0f, 787.0f, 390.0f, 781.0f),
                PathNode.LineTo(630.0f, 404.0f),
                PathNode.LineTo(501.0f, 201.0f),
                PathNode.QuadTo(477.0f, 228.0f, 444.0f, 243.0f),
                PathNode.QuadTo(411.0f, 258.0f, 373.0f, 258.0f),
                PathNode.QuadTo(324.7410071942446f, 258.0f, 284.3705035971223f, 234.0f),
                PathNode.QuadTo(244.0f, 210.0f, 220.0f, 169.68848167539267f),
                PathNode.QuadTo(196.0f, 129.37696335078533f, 196.0f, 81.18848167539267f),
                PathNode.QuadTo(196.0f, 33.0f, 219.83695652173913f, -7.663043478260867f),
                PathNode.QuadTo(243.67391304347825f, -48.326086956521735f, 284.3369565217391f, -72.16304347826087f),
                PathNode.QuadTo(325.0f, -96.0f, 373.18848167539267f, -96.0f),
                PathNode.QuadTo(421.37696335078533f, -96.0f, 461.68848167539267f, -72.0f),
                PathNode.QuadTo(502.0f, -48.0f, 526.0f, -7.5f),
                PathNode.QuadTo(550.0f, 33.0f, 550.0f, 81.41379310344827f),
                PathNode.Close,
                PathNode.MoveTo(255.0f, 81.0f),
                PathNode.QuadTo(255.0f, 130.44761904761904f, 289.5f, 164.7238095238095f),
                PathNode.QuadTo(324.0f, 199.0f, 373.0f, 199.0f),
                PathNode.QuadTo(422.0f, 199.0f, 456.0f, 164.7238095238095f),
                PathNode.QuadTo(490.0f, 130.44761904761904f, 490.0f, 81.0f),
                PathNode.QuadTo(490.0f, 32.625f, 456.0f, -1.6875f),
                PathNode.QuadTo(422.0f, -36.0f, 373.0f, -36.0f),
                PathNode.QuadTo(324.0f, -36.0f, 289.5f, -1.6875f),
                PathNode.QuadTo(255.0f, 32.625f, 255.0f, 81.0f),
                PathNode.Close,
                PathNode.MoveTo(839.0f, 81.0f),
                PathNode.QuadTo(839.0f, 130.44761904761904f, 873.2094594594595f, 164.7238095238095f),
                PathNode.QuadTo(907.418918918919f, 199.0f, 955.6486486486486f, 199.0f),
                PathNode.QuadTo(1005.0f, 199.0f, 1039.5f, 164.7238095238095f),
                PathNode.QuadTo(1074.0f, 130.44761904761904f, 1074.0f, 81.0f),
                PathNode.QuadTo(1074.0f, 32.625f, 1039.5f, -1.6875f),
                PathNode.QuadTo(1005.0f, -36.0f, 956.0f, -36.0f),
                PathNode.QuadTo(907.0f, -36.0f, 873.0f, -1.6875f),
                PathNode.QuadTo(839.0f, 32.625f, 839.0f, 81.0f),
                PathNode.Close,
                PathNode.MoveTo(944.0f, 813.0f),
                PathNode.LineTo(924.0f, 836.0f),
                PathNode.QuadTo(919.0f, 842.0f, 914.0f, 841.5f),
                PathNode.QuadTo(909.0f, 841.0f, 905.0f, 834.0f),
                PathNode.LineTo(715.0f, 537.0f),
                PathNode.QuadTo(711.0f, 531.0f, 711.5f, 525.0f),
                PathNode.QuadTo(712.0f, 519.0f, 717.0f, 514.0f),
                PathNode.LineTo(737.0f, 487.0f),
                PathNode.QuadTo(740.8235294117648f, 482.0f, 745.4117647058824f, 482.5f),
                PathNode.QuadTo(750.0f, 483.0f, 754.0f, 489.0f),
                PathNode.LineTo(946.0f, 788.0f),
                PathNode.QuadTo(950.0f, 795.0f, 949.4f, 800.4f),
                PathNode.QuadTo(948.8f, 805.8f, 944.0f, 813.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _trimLight!!
    }

private var _trimLight: ImageVector? = null

val MiuixIcons.Regular.Trim: ImageVector
    get() {
        if (_trimRegular != null) return _trimRegular!!
        _trimRegular = ImageVector.Builder(
            name = "Trim.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1155.6f,
            viewportHeight = 1155.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -86.70000000000005f, translationY = 950.3f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(563.0f, 81.0f),
                PathNode.QuadTo(563.0f, 116.0f, 551.0f, 146.0f),
                PathNode.LineTo(665.0f, 324.0f),
                PathNode.LineTo(778.0f, 147.0f),
                PathNode.QuadTo(766.0f, 117.0f, 766.0f, 81.0f),
                PathNode.QuadTo(766.0f, 29.0f, 791.5f, -14.5f),
                PathNode.QuadTo(817.0f, -58.0f, 860.5f, -83.5f),
                PathNode.QuadTo(904.0f, -109.0f, 956.0f, -109.0f),
                PathNode.QuadTo(1008.0f, -109.0f, 1051.5f, -83.5f),
                PathNode.QuadTo(1095.0f, -58.0f, 1120.5f, -14.5f),
                PathNode.QuadTo(1146.0f, 29.0f, 1146.0f, 81.0f),
                PathNode.QuadTo(1146.0f, 133.0f, 1120.5f, 176.5f),
                PathNode.QuadTo(1095.0f, 220.0f, 1051.5f, 245.5f),
                PathNode.QuadTo(1008.0f, 271.0f, 956.0f, 271.0f),
                PathNode.QuadTo(921.0f, 271.0f, 888.5f, 258.5f),
                PathNode.QuadTo(856.0f, 246.0f, 830.0f, 224.0f),
                PathNode.LineTo(433.0f, 847.0f),
                PathNode.QuadTo(429.0f, 854.0f, 422.5f, 854.0f),
                PathNode.QuadTo(416.0f, 854.0f, 412.0f, 849.0f),
                PathNode.LineTo(380.0f, 805.0f),
                PathNode.QuadTo(374.0f, 797.0f, 374.0f, 788.5f),
                PathNode.QuadTo(374.0f, 780.0f, 380.0f, 771.0f),
                PathNode.LineTo(614.0f, 404.0f),
                PathNode.LineTo(499.0f, 223.0f),
                PathNode.QuadTo(474.0f, 246.0f, 441.5f, 258.5f),
                PathNode.QuadTo(409.0f, 271.0f, 373.0f, 271.0f),
                PathNode.QuadTo(321.0f, 271.0f, 277.5f, 245.5f),
                PathNode.QuadTo(234.0f, 220.0f, 208.5f, 176.5f),
                PathNode.QuadTo(183.0f, 133.0f, 183.0f, 81.0f),
                PathNode.QuadTo(183.0f, 29.0f, 208.5f, -14.5f),
                PathNode.QuadTo(234.0f, -58.0f, 277.5f, -83.5f),
                PathNode.QuadTo(321.0f, -109.0f, 373.0f, -109.0f),
                PathNode.QuadTo(425.0f, -109.0f, 468.5f, -83.5f),
                PathNode.QuadTo(512.0f, -58.0f, 537.5f, -14.5f),
                PathNode.QuadTo(563.0f, 29.0f, 563.0f, 81.0f),
                PathNode.Close,
                PathNode.MoveTo(268.0f, 81.0f),
                PathNode.QuadTo(268.0f, 125.0f, 299.0f, 155.5f),
                PathNode.QuadTo(330.0f, 186.0f, 373.0f, 186.0f),
                PathNode.QuadTo(416.0f, 186.0f, 446.5f, 155.5f),
                PathNode.QuadTo(477.0f, 125.0f, 477.0f, 81.0f),
                PathNode.QuadTo(477.0f, 38.0f, 446.5f, 7.5f),
                PathNode.QuadTo(416.0f, -23.0f, 373.0f, -23.0f),
                PathNode.QuadTo(330.0f, -23.0f, 299.0f, 7.5f),
                PathNode.QuadTo(268.0f, 38.0f, 268.0f, 81.0f),
                PathNode.Close,
                PathNode.MoveTo(852.0f, 81.0f),
                PathNode.QuadTo(852.0f, 125.0f, 882.5f, 155.5f),
                PathNode.QuadTo(913.0f, 186.0f, 956.0f, 186.0f),
                PathNode.QuadTo(1000.0f, 186.0f, 1030.5f, 155.5f),
                PathNode.QuadTo(1061.0f, 125.0f, 1061.0f, 81.0f),
                PathNode.QuadTo(1061.0f, 38.0f, 1030.0f, 7.5f),
                PathNode.QuadTo(999.0f, -23.0f, 956.0f, -23.0f),
                PathNode.QuadTo(913.0f, -23.0f, 882.5f, 7.5f),
                PathNode.QuadTo(852.0f, 38.0f, 852.0f, 81.0f),
                PathNode.Close,
                PathNode.MoveTo(950.0f, 810.0f),
                PathNode.LineTo(921.0f, 845.0f),
                PathNode.QuadTo(915.0f, 853.0f, 908.0f, 853.0f),
                PathNode.QuadTo(901.0f, 853.0f, 895.0f, 842.0f),
                PathNode.LineTo(707.0f, 549.0f),
                PathNode.QuadTo(702.0f, 542.0f, 703.0f, 533.5f),
                PathNode.QuadTo(704.0f, 525.0f, 710.0f, 516.0f),
                PathNode.LineTo(740.0f, 476.0f),
                PathNode.QuadTo(745.0f, 469.0f, 751.0f, 469.5f),
                PathNode.QuadTo(757.0f, 470.0f, 762.0f, 477.0f),
                PathNode.LineTo(953.0f, 775.0f),
                PathNode.QuadTo(960.0f, 785.0f, 959.0f, 792.5f),
                PathNode.QuadTo(958.0f, 800.0f, 950.0f, 810.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _trimRegular!!
    }

private var _trimRegular: ImageVector? = null

val MiuixIcons.Heavy.Trim: ImageVector
    get() {
        if (_trimHeavy != null) return _trimHeavy!!
        _trimHeavy = ImageVector.Builder(
            name = "Trim.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1189.2f,
            viewportHeight = 1189.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -69.89999999999998f, translationY = 966.8608695652174f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(577.0f, 81.0f),
                PathNode.QuadTo(577.0f, 114.0f, 566.0f, 144.0f),
                PathNode.LineTo(665.0f, 298.0f),
                PathNode.LineTo(763.0f, 145.0f),
                PathNode.QuadTo(752.0f, 114.0f, 752.0f, 81.0f),
                PathNode.QuadTo(752.0f, 26.0f, 779.5f, -21.0f),
                PathNode.QuadTo(807.0f, -68.0f, 854.0f, -95.5f),
                PathNode.QuadTo(901.0f, -123.0f, 956.0f, -123.0f),
                PathNode.QuadTo(1011.0f, -123.0f, 1058.0f, -95.5f),
                PathNode.QuadTo(1105.0f, -68.0f, 1132.5f, -21.0f),
                PathNode.QuadTo(1160.0f, 26.0f, 1160.0f, 81.0f),
                PathNode.QuadTo(1160.0f, 136.0f, 1132.5f, 183.0f),
                PathNode.QuadTo(1105.0f, 230.0f, 1058.0f, 257.5f),
                PathNode.QuadTo(1011.0f, 285.0f, 956.0f, 285.0f),
                PathNode.QuadTo(923.0f, 285.0f, 891.5f, 274.0f),
                PathNode.QuadTo(860.0f, 263.0f, 834.0f, 244.0f),
                PathNode.LineTo(444.0f, 854.0f),
                PathNode.QuadTo(436.0f, 867.0f, 422.5f, 867.5f),
                PathNode.QuadTo(409.0f, 868.0f, 401.0f, 857.0f),
                PathNode.LineTo(369.0f, 813.0f),
                PathNode.QuadTo(360.0f, 802.0f, 360.0f, 789.0f),
                PathNode.QuadTo(360.0f, 776.0f, 369.0f, 764.0f),
                PathNode.LineTo(597.0f, 404.0f),
                PathNode.LineTo(495.0f, 243.0f),
                PathNode.QuadTo(470.0f, 263.0f, 438.5f, 274.0f),
                PathNode.QuadTo(407.0f, 285.0f, 373.0f, 285.0f),
                PathNode.QuadTo(318.0f, 285.0f, 271.0f, 257.5f),
                PathNode.QuadTo(224.0f, 230.0f, 196.5f, 183.0f),
                PathNode.QuadTo(169.0f, 136.0f, 169.0f, 81.0f),
                PathNode.QuadTo(169.0f, 26.0f, 196.5f, -21.0f),
                PathNode.QuadTo(224.0f, -68.0f, 271.0f, -95.5f),
                PathNode.QuadTo(318.0f, -123.0f, 373.0f, -123.0f),
                PathNode.QuadTo(428.0f, -123.0f, 475.0f, -95.5f),
                PathNode.QuadTo(522.0f, -68.0f, 549.5f, -21.0f),
                PathNode.QuadTo(577.0f, 26.0f, 577.0f, 81.0f),
                PathNode.Close,
                PathNode.MoveTo(282.0f, 81.0f),
                PathNode.QuadTo(282.0f, 119.0f, 308.5f, 145.5f),
                PathNode.QuadTo(335.0f, 172.0f, 373.0f, 172.0f),
                PathNode.QuadTo(411.0f, 172.0f, 437.0f, 145.5f),
                PathNode.QuadTo(463.0f, 119.0f, 463.0f, 81.0f),
                PathNode.QuadTo(463.0f, 44.0f, 437.0f, 17.5f),
                PathNode.QuadTo(411.0f, -9.0f, 373.0f, -9.0f),
                PathNode.QuadTo(335.0f, -9.0f, 308.5f, 17.0f),
                PathNode.QuadTo(282.0f, 43.0f, 282.0f, 81.0f),
                PathNode.Close,
                PathNode.MoveTo(866.0f, 81.0f),
                PathNode.QuadTo(866.0f, 119.0f, 892.0f, 145.5f),
                PathNode.QuadTo(918.0f, 172.0f, 956.0f, 172.0f),
                PathNode.QuadTo(994.0f, 172.0f, 1020.5f, 145.5f),
                PathNode.QuadTo(1047.0f, 119.0f, 1047.0f, 81.0f),
                PathNode.QuadTo(1047.0f, 43.0f, 1020.5f, 17.0f),
                PathNode.QuadTo(994.0f, -9.0f, 956.0f, -9.0f),
                PathNode.QuadTo(919.0f, -9.0f, 892.5f, 17.5f),
                PathNode.QuadTo(866.0f, 44.0f, 866.0f, 81.0f),
                PathNode.Close,
                PathNode.MoveTo(961.0f, 819.0f),
                PathNode.LineTo(932.0f, 854.0f),
                PathNode.QuadTo(921.0f, 867.0f, 907.5f, 866.0f),
                PathNode.QuadTo(894.0f, 865.0f, 884.0f, 849.0f),
                PathNode.LineTo(718.0f, 590.0f),
                PathNode.QuadTo(710.0f, 579.0f, 711.0f, 566.0f),
                PathNode.QuadTo(712.0f, 553.0f, 721.0f, 541.0f),
                PathNode.LineTo(751.0f, 501.0f),
                PathNode.QuadTo(761.0f, 488.0f, 774.0f, 488.5f),
                PathNode.QuadTo(787.0f, 489.0f, 796.0f, 503.0f),
                PathNode.LineTo(964.0f, 768.0f),
                PathNode.QuadTo(973.0f, 782.0f, 972.5f, 793.5f),
                PathNode.QuadTo(972.0f, 805.0f, 961.0f, 819.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _trimHeavy!!
    }

private var _trimHeavy: ImageVector? = null
