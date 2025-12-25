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

val MiuixIcons.Cut: ImageVector
    get() = MiuixIcons.Regular.Cut

val MiuixIcons.Light.Cut: ImageVector
    get() {
        if (_cutLight != null) return _cutLight!!
        _cutLight = ImageVector.Builder(
            name = "Cut.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1176.0f,
            viewportHeight = 1176.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -79.0f, translationY = 962.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(531.0f, 124.0f),
                PathNode.QuadTo(531.0f, 172.0f, 508.0f, 209.0f),
                PathNode.LineTo(1147.0f, 618.0f),
                PathNode.QuadTo(1157.0f, 625.0f, 1157.0f, 630.0f),
                PathNode.QuadTo(1157.0f, 635.0f, 1147.0f, 642.0f),
                PathNode.LineTo(1128.0f, 657.0f),
                PathNode.QuadTo(1118.516129032258f, 664.0f, 1112.758064516129f, 663.5f),
                PathNode.QuadTo(1107.0f, 663.0f, 1097.0f, 657.0f),
                PathNode.LineTo(716.0f, 413.0f),
                PathNode.LineTo(520.0f, 563.0f),
                PathNode.QuadTo(531.0f, 598.0f, 531.0f, 623.6153846153846f),
                PathNode.QuadTo(531.0f, 672.0f, 507.1630434782609f, 712.3478260869565f),
                PathNode.QuadTo(483.32608695652175f, 752.695652173913f, 442.6630434782609f, 776.3478260869565f),
                PathNode.QuadTo(402.0f, 800.0f, 353.81151832460733f, 800.0f),
                PathNode.QuadTo(305.62303664921467f, 800.0f, 265.31151832460733f, 776.5f),
                PathNode.QuadTo(225.0f, 753.0f, 201.0f, 712.4763157894737f),
                PathNode.QuadTo(177.0f, 671.9526315789474f, 177.0f, 624.4421052631579f),
                PathNode.QuadTo(177.0f, 576.0f, 200.83695652173913f, 535.3369565217391f),
                PathNode.QuadTo(224.67391304347825f, 494.67391304347825f, 265.3369565217391f, 470.8369565217391f),
                PathNode.QuadTo(306.0f, 447.0f, 354.0f, 447.0f),
                PathNode.QuadTo(394.0f, 447.0f, 430.0f, 464.5f),
                PathNode.QuadTo(466.0f, 482.0f, 491.0f, 513.0f),
                PathNode.LineTo(663.0f, 379.0f),
                PathNode.LineTo(472.0f, 257.0f),
                PathNode.QuadTo(420.43697478991595f, 301.0f, 354.0f, 301.0f),
                PathNode.QuadTo(305.7410071942446f, 301.0f, 265.3705035971223f, 277.0f),
                PathNode.QuadTo(225.0f, 253.0f, 201.0f, 212.4763157894737f),
                PathNode.QuadTo(177.0f, 171.95263157894738f, 177.0f, 123.51052631578948f),
                PathNode.QuadTo(177.0f, 76.0f, 200.83695652173913f, 35.65217391304348f),
                PathNode.QuadTo(224.67391304347825f, -4.695652173913043f, 265.3369565217391f, -28.347826086956523f),
                PathNode.QuadTo(306.0f, -52.0f, 354.18848167539267f, -52.0f),
                PathNode.QuadTo(402.37696335078533f, -52.0f, 442.68848167539267f, -28.5f),
                PathNode.QuadTo(483.0f, -5.0f, 507.0f, 35.66304347826087f),
                PathNode.QuadTo(531.0f, 76.32608695652173f, 531.0f, 124.0f),
                PathNode.Close,
                PathNode.MoveTo(237.0f, 124.0f),
                PathNode.QuadTo(237.0f, 173.44761904761904f, 271.2094594594595f, 207.7238095238095f),
                PathNode.QuadTo(305.4189189189189f, 242.0f, 353.64864864864865f, 242.0f),
                PathNode.QuadTo(403.0f, 242.0f, 437.5f, 207.7238095238095f),
                PathNode.QuadTo(472.0f, 173.44761904761904f, 472.0f, 124.0f),
                PathNode.QuadTo(472.0f, 75.625f, 437.5f, 41.3125f),
                PathNode.QuadTo(403.0f, 7.0f, 354.0f, 7.0f),
                PathNode.QuadTo(305.0f, 7.0f, 271.0f, 41.3125f),
                PathNode.QuadTo(237.0f, 75.625f, 237.0f, 124.0f),
                PathNode.Close,
                PathNode.MoveTo(237.0f, 624.0f),
                PathNode.QuadTo(237.0f, 672.375f, 271.0f, 706.6875f),
                PathNode.QuadTo(305.0f, 741.0f, 354.0f, 741.0f),
                PathNode.QuadTo(403.0f, 741.0f, 437.5f, 706.5578231292517f),
                PathNode.QuadTo(472.0f, 672.1156462585034f, 472.0f, 623.5578231292517f),
                PathNode.QuadTo(472.0f, 575.0f, 437.5844594594595f, 540.5f),
                PathNode.QuadTo(403.1689189189189f, 506.0f, 353.52027027027026f, 506.0f),
                PathNode.QuadTo(305.0f, 506.0f, 271.0f, 540.2761904761905f),
                PathNode.QuadTo(237.0f, 574.552380952381f, 237.0f, 624.0f),
                PathNode.Close,
                PathNode.MoveTo(1131.0f, 92.0f),
                PathNode.LineTo(844.0f, 312.0f),
                PathNode.QuadTo(838.0f, 318.0f, 831.5f, 318.0f),
                PathNode.QuadTo(825.0f, 318.0f, 818.0f, 314.0f),
                PathNode.LineTo(793.68f, 299.0f),
                PathNode.QuadTo(786.0f, 294.22727272727275f, 786.0f, 289.1136363636364f),
                PathNode.QuadTo(786.0f, 284.0f, 795.0f, 277.0f),
                PathNode.LineTo(1079.0f, 57.0f),
                PathNode.QuadTo(1087.0f, 52.0f, 1092.5f, 51.5f),
                PathNode.QuadTo(1098.0f, 51.0f, 1105.0f, 55.0f),
                PathNode.LineTo(1129.9183673469388f, 72.30232558139535f),
                PathNode.QuadTo(1136.6530612244899f, 77.34883720930233f, 1137.3265306122448f, 81.67441860465117f),
                PathNode.QuadTo(1138.0f, 86.0f, 1131.0f, 92.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _cutLight!!
    }

private var _cutLight: ImageVector? = null

val MiuixIcons.Regular.Cut: ImageVector
    get() {
        if (_cutRegular != null) return _cutRegular!!
        _cutRegular = ImageVector.Builder(
            name = "Cut.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1203.0181818181818f,
            viewportHeight = 1203.0181818181818f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -63.74848484848485f, translationY = 975.5090909090909f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(544.0f, 124.0f),
                PathNode.QuadTo(544.0f, 169.0f, 526.0f, 205.0f),
                PathNode.LineTo(1150.0f, 604.0f),
                PathNode.QuadTo(1166.0f, 615.0f, 1166.5f, 621.0f),
                PathNode.QuadTo(1167.0f, 627.0f, 1151.0f, 639.0f),
                PathNode.LineTo(1124.0f, 659.0f),
                PathNode.QuadTo(1110.0f, 670.0f, 1101.5f, 669.5f),
                PathNode.QuadTo(1093.0f, 669.0f, 1077.0f, 659.0f),
                PathNode.LineTo(717.0f, 429.0f),
                PathNode.LineTo(536.0f, 568.0f),
                PathNode.QuadTo(544.0f, 597.0f, 544.0f, 624.0f),
                PathNode.QuadTo(544.0f, 675.0f, 518.5f, 718.5f),
                PathNode.QuadTo(493.0f, 762.0f, 449.5f, 787.5f),
                PathNode.QuadTo(406.0f, 813.0f, 354.0f, 813.0f),
                PathNode.QuadTo(302.0f, 813.0f, 258.5f, 787.5f),
                PathNode.QuadTo(215.0f, 762.0f, 189.5f, 718.5f),
                PathNode.QuadTo(164.0f, 675.0f, 164.0f, 624.0f),
                PathNode.QuadTo(164.0f, 572.0f, 189.5f, 528.5f),
                PathNode.QuadTo(215.0f, 485.0f, 258.5f, 459.5f),
                PathNode.QuadTo(302.0f, 434.0f, 354.0f, 434.0f),
                PathNode.QuadTo(394.0f, 434.0f, 430.0f, 450.0f),
                PathNode.QuadTo(466.0f, 466.0f, 493.0f, 494.0f),
                PathNode.LineTo(640.0f, 380.0f),
                PathNode.LineTo(473.0f, 273.0f),
                PathNode.QuadTo(421.0f, 314.0f, 354.0f, 314.0f),
                PathNode.QuadTo(302.0f, 314.0f, 258.5f, 288.5f),
                PathNode.QuadTo(215.0f, 263.0f, 189.5f, 219.5f),
                PathNode.QuadTo(164.0f, 176.0f, 164.0f, 124.0f),
                PathNode.QuadTo(164.0f, 73.0f, 189.5f, 29.5f),
                PathNode.QuadTo(215.0f, -14.0f, 258.5f, -39.5f),
                PathNode.QuadTo(302.0f, -65.0f, 354.0f, -65.0f),
                PathNode.QuadTo(406.0f, -65.0f, 449.5f, -39.5f),
                PathNode.QuadTo(493.0f, -14.0f, 518.5f, 29.5f),
                PathNode.QuadTo(544.0f, 73.0f, 544.0f, 124.0f),
                PathNode.Close,
                PathNode.MoveTo(250.0f, 124.0f),
                PathNode.QuadTo(250.0f, 168.0f, 280.5f, 198.5f),
                PathNode.QuadTo(311.0f, 229.0f, 354.0f, 229.0f),
                PathNode.QuadTo(398.0f, 229.0f, 428.5f, 198.5f),
                PathNode.QuadTo(459.0f, 168.0f, 459.0f, 124.0f),
                PathNode.QuadTo(459.0f, 81.0f, 428.0f, 50.5f),
                PathNode.QuadTo(397.0f, 20.0f, 354.0f, 20.0f),
                PathNode.QuadTo(311.0f, 20.0f, 280.5f, 50.5f),
                PathNode.QuadTo(250.0f, 81.0f, 250.0f, 124.0f),
                PathNode.Close,
                PathNode.MoveTo(250.0f, 624.0f),
                PathNode.QuadTo(250.0f, 667.0f, 280.5f, 697.5f),
                PathNode.QuadTo(311.0f, 728.0f, 354.0f, 728.0f),
                PathNode.QuadTo(397.0f, 728.0f, 428.0f, 697.5f),
                PathNode.QuadTo(459.0f, 667.0f, 459.0f, 624.0f),
                PathNode.QuadTo(459.0f, 581.0f, 428.5f, 550.0f),
                PathNode.QuadTo(398.0f, 519.0f, 354.0f, 519.0f),
                PathNode.QuadTo(311.0f, 519.0f, 280.5f, 549.5f),
                PathNode.QuadTo(250.0f, 580.0f, 250.0f, 624.0f),
                PathNode.Close,
                PathNode.MoveTo(1137.0f, 104.0f),
                PathNode.LineTo(865.0f, 314.0f),
                PathNode.QuadTo(855.0f, 322.0f, 847.0f, 322.0f),
                PathNode.QuadTo(839.0f, 322.0f, 829.0f, 316.0f),
                PathNode.LineTo(791.0f, 294.0f),
                PathNode.QuadTo(779.0f, 287.0f, 779.0f, 279.5f),
                PathNode.QuadTo(779.0f, 272.0f, 793.0f, 261.0f),
                PathNode.LineTo(1060.0f, 55.0f),
                PathNode.QuadTo(1071.0f, 46.0f, 1080.0f, 45.5f),
                PathNode.QuadTo(1089.0f, 45.0f, 1099.0f, 52.0f),
                PathNode.LineTo(1136.0f, 76.0f),
                PathNode.QuadTo(1146.0f, 83.0f, 1147.0f, 89.0f),
                PathNode.QuadTo(1148.0f, 95.0f, 1137.0f, 104.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _cutRegular!!
    }

private var _cutRegular: ImageVector? = null

val MiuixIcons.Heavy.Cut: ImageVector
    get() {
        if (_cutHeavy != null) return _cutHeavy!!
        _cutHeavy = ImageVector.Builder(
            name = "Cut.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1235.4139534883723f,
            viewportHeight = 1235.4139534883723f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -46.04883720930229f, translationY = 992.2069767441861f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(556.0f, 124.0f),
                PathNode.QuadTo(556.0f, 164.0f, 542.0f, 200.0f),
                PathNode.LineTo(1156.0f, 593.0f),
                PathNode.QuadTo(1178.0f, 607.0f, 1178.5f, 620.5f),
                PathNode.QuadTo(1179.0f, 634.0f, 1158.0f, 650.0f),
                PathNode.LineTo(1131.0f, 670.0f),
                PathNode.QuadTo(1113.0f, 683.0f, 1100.5f, 683.5f),
                PathNode.QuadTo(1088.0f, 684.0f, 1068.0f, 671.0f),
                PathNode.LineTo(717.0f, 446.0f),
                PathNode.LineTo(550.0f, 574.0f),
                PathNode.QuadTo(556.0f, 598.0f, 556.0f, 624.0f),
                PathNode.QuadTo(556.0f, 678.7777777777778f, 528.7086776859504f, 725.5f),
                PathNode.QuadTo(501.41735537190084f, 772.2222222222222f, 454.86157024793386f, 799.6111111111111f),
                PathNode.QuadTo(408.3057851239669f, 827.0f, 352.6528925619835f, 827.0f),
                PathNode.QuadTo(297.0f, 827.0f, 250.3478260869565f, 799.6521739130435f),
                PathNode.QuadTo(203.69565217391303f, 772.304347826087f, 176.3478260869565f, 725.6521739130435f),
                PathNode.QuadTo(149.0f, 679.0f, 149.0f, 624.402489626556f),
                PathNode.QuadTo(149.0f, 568.7344398340249f, 176.37894736842105f, 522.1659751037345f),
                PathNode.QuadTo(203.7578947368421f, 475.597510373444f, 250.46315789473684f, 448.298755186722f),
                PathNode.QuadTo(297.1684210526316f, 421.0f, 353.0f, 421.0f),
                PathNode.QuadTo(393.0f, 421.0f, 429.0f, 435.5f),
                PathNode.QuadTo(465.0f, 450.0f, 493.0f, 477.0f),
                PathNode.LineTo(615.0f, 381.0f),
                PathNode.LineTo(472.0f, 290.0f),
                PathNode.QuadTo(418.0f, 328.0f, 353.0f, 328.0f),
                PathNode.QuadTo(297.1684210526316f, 328.0f, 250.46315789473684f, 300.5954356846473f),
                PathNode.QuadTo(203.7578947368421f, 273.1908713692946f, 176.37894736842105f, 226.44190871369295f),
                PathNode.QuadTo(149.0f, 179.69294605809128f, 149.0f, 123.80912863070539f),
                PathNode.QuadTo(149.0f, 69.0f, 176.5f, 23.0f),
                PathNode.QuadTo(204.0f, -23.0f, 250.6294964028777f, -50.5f),
                PathNode.QuadTo(297.2589928057554f, -78.0f, 353.0f, -78.0f),
                PathNode.QuadTo(408.36690647482015f, -78.0f, 454.68345323741005f, -50.5f),
                PathNode.QuadTo(501.0f, -23.0f, 528.5f, 23.0f),
                PathNode.QuadTo(556.0f, 69.0f, 556.0f, 124.0f),
                PathNode.Close,
                PathNode.MoveTo(262.0f, 123.94594594594595f),
                PathNode.QuadTo(262.0f, 162.0f, 288.5844594594595f, 189.0f),
                PathNode.QuadTo(315.1689189189189f, 216.0f, 352.64864864864865f, 216.0f),
                PathNode.QuadTo(391.0f, 216.0f, 417.5f, 189.0f),
                PathNode.QuadTo(444.0f, 162.0f, 444.0f, 123.94594594594595f),
                PathNode.QuadTo(444.0f, 86.75675675675676f, 417.1333333333333f, 60.37837837837838f),
                PathNode.QuadTo(390.26666666666665f, 34.0f, 353.0f, 34.0f),
                PathNode.QuadTo(315.375f, 34.0f, 288.6875f, 60.37837837837838f),
                PathNode.QuadTo(262.0f, 86.75675675675676f, 262.0f, 123.94594594594595f),
                PathNode.Close,
                PathNode.MoveTo(262.0f, 624.0f),
                PathNode.QuadTo(262.0f, 661.625f, 288.6875f, 688.3125f),
                PathNode.QuadTo(315.375f, 715.0f, 353.0f, 715.0f),
                PathNode.QuadTo(391.0f, 715.0f, 417.5f, 688.4421768707483f),
                PathNode.QuadTo(444.0f, 661.8843537414966f, 444.0f, 624.4421768707483f),
                PathNode.QuadTo(444.0f, 587.0f, 417.56666666666666f, 560.0f),
                PathNode.QuadTo(391.1333333333333f, 533.0f, 353.0f, 533.0f),
                PathNode.QuadTo(315.375f, 533.0f, 288.6875f, 559.4333333333334f),
                PathNode.QuadTo(262.0f, 585.8666666666667f, 262.0f, 624.0f),
                PathNode.Close,
                PathNode.MoveTo(1146.0f, 115.0f),
                PathNode.LineTo(899.0f, 304.0f),
                PathNode.QuadTo(885.0f, 314.0f, 873.0f, 314.5f),
                PathNode.QuadTo(861.0f, 315.0f, 847.0f, 307.0f),
                PathNode.LineTo(809.0f, 285.0f),
                PathNode.QuadTo(790.0f, 275.0f, 789.5f, 260.0f),
                PathNode.QuadTo(789.0f, 245.0f, 810.0f, 229.0f),
                PathNode.LineTo(1052.0f, 44.0f),
                PathNode.QuadTo(1067.0f, 33.0f, 1080.0f, 32.0f),
                PathNode.QuadTo(1093.0f, 31.0f, 1107.0f, 41.0f),
                PathNode.LineTo(1144.0f, 65.0f),
                PathNode.QuadTo(1160.0f, 76.0f, 1161.0f, 89.5f),
                PathNode.QuadTo(1162.0f, 103.0f, 1146.0f, 115.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _cutHeavy!!
    }

private var _cutHeavy: ImageVector? = null
