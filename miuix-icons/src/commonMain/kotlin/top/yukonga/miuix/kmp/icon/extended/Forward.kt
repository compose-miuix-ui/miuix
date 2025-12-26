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

val MiuixIcons.Forward: ImageVector
    get() = MiuixIcons.Regular.Forward

val MiuixIcons.Light.Forward: ImageVector
    get() {
        if (_forwardLight != null) return _forwardLight!!
        _forwardLight = ImageVector.Builder(
            name = "Forward.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1110.0f,
            viewportHeight = 1110.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -109.5f, translationY = 930.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(958.0f, -67.0f),
                        PathNode.QuadTo(1009.0f, -41.0f, 1035.0f, 10.0f),
                        PathNode.QuadTo(1048.0f, 36.0f, 1051.0f, 74.0f),
                        PathNode.QuadTo(1054.0f, 112.0f, 1054.0f, 204.0f),
                        PathNode.VerticalTo(321.0f),
                        PathNode.QuadTo(1054.0f, 330.0f, 1049.5f, 335.5f),
                        PathNode.QuadTo(1045.0f, 341.0f, 1033.0f, 341.0f),
                        PathNode.HorizontalTo(1016.0f),
                        PathNode.QuadTo(1004.0f, 341.0f, 999.5f, 335.5f),
                        PathNode.QuadTo(995.0f, 330.0f, 995.0f, 321.0f),
                        PathNode.VerticalTo(161.0f),
                        PathNode.QuadTo(995.0f, 110.0f, 992.5f, 84.0f),
                        PathNode.QuadTo(990.0f, 58.0f, 982.0f, 40.0f),
                        PathNode.QuadTo(965.0f, 3.0f, 928.0f, -14.0f),
                        PathNode.QuadTo(910.0f, -23.0f, 885.5f, -25.0f),
                        PathNode.QuadTo(861.0f, -27.0f, 807.0f, -27.0f),
                        PathNode.HorizontalTo(450.0f),
                        PathNode.QuadTo(395.0f, -27.0f, 370.5f, -25.0f),
                        PathNode.QuadTo(346.0f, -23.0f, 328.0f, -14.0f),
                        PathNode.QuadTo(292.0f, 3.0f, 275.0f, 40.0f),
                        PathNode.QuadTo(267.0f, 58.0f, 264.5f, 84.0f),
                        PathNode.QuadTo(262.0f, 110.0f, 262.0f, 161.0f),
                        PathNode.VerticalTo(516.0f),
                        PathNode.QuadTo(262.0f, 570.0f, 264.0f, 594.5f),
                        PathNode.QuadTo(266.0f, 619.0f, 275.0f, 637.0f),
                        PathNode.QuadTo(292.0f, 673.0f, 328.0f, 691.0f),
                        PathNode.QuadTo(346.0f, 700.0f, 370.5f, 702.0f),
                        PathNode.QuadTo(395.0f, 704.0f, 450.0f, 704.0f),
                        PathNode.HorizontalTo(605.0f),
                        PathNode.QuadTo(613.0f, 704.0f, 618.5f, 709.0f),
                        PathNode.QuadTo(624.0f, 714.0f, 624.0f, 724.0f),
                        PathNode.VerticalTo(743.0f),
                        PathNode.QuadTo(624.0f, 753.0f, 618.5f, 758.0f),
                        PathNode.QuadTo(613.0f, 763.0f, 605.0f, 763.0f),
                        PathNode.HorizontalTo(493.0f),
                        PathNode.QuadTo(401.0f, 763.0f, 363.0f, 760.0f),
                        PathNode.QuadTo(325.0f, 757.0f, 299.0f, 744.0f),
                        PathNode.QuadTo(248.0f, 717.0f, 222.0f, 667.0f),
                        PathNode.QuadTo(208.0f, 641.0f, 205.0f, 603.0f),
                        PathNode.QuadTo(202.0f, 565.0f, 202.0f, 472.0f),
                        PathNode.VerticalTo(204.0f),
                        PathNode.QuadTo(202.0f, 115.0f, 205.5f, 76.0f),
                        PathNode.QuadTo(209.0f, 37.0f, 222.0f, 10.0f),
                        PathNode.QuadTo(247.0f, -41.0f, 299.0f, -67.0f),
                        PathNode.QuadTo(325.0f, -80.0f, 363.0f, -83.0f),
                        PathNode.QuadTo(401.0f, -86.0f, 493.0f, -86.0f),
                        PathNode.HorizontalTo(763.0f),
                        PathNode.QuadTo(856.0f, -86.0f, 894.0f, -83.0f),
                        PathNode.QuadTo(932.0f, -80.0f, 958.0f, -67.0f),
                        PathNode.Close,
                        PathNode.MoveTo(592.0f, 261.0f),
                        PathNode.LineTo(1068.0f, 735.0f),
                        PathNode.VerticalTo(511.0f),
                        PathNode.QuadTo(1068.0f, 502.0f, 1072.5f, 497.0f),
                        PathNode.QuadTo(1077.0f, 492.0f, 1085.0f, 492.0f),
                        PathNode.HorizontalTo(1109.0f),
                        PathNode.QuadTo(1118.0f, 492.0f, 1122.5f, 497.0f),
                        PathNode.QuadTo(1127.0f, 502.0f, 1127.0f, 511.0f),
                        PathNode.VerticalTo(794.0f),
                        PathNode.QuadTo(1127.0f, 816.0f, 1117.5f, 826.0f),
                        PathNode.QuadTo(1108.0f, 836.0f, 1086.0f, 836.0f),
                        PathNode.HorizontalTo(800.0f),
                        PathNode.QuadTo(793.0f, 836.0f, 788.0f, 831.5f),
                        PathNode.QuadTo(783.0f, 827.0f, 783.0f, 819.0f),
                        PathNode.VerticalTo(795.0f),
                        PathNode.QuadTo(783.0f, 786.0f, 787.5f, 781.5f),
                        PathNode.QuadTo(792.0f, 777.0f, 800.0f, 777.0f),
                        PathNode.HorizontalTo(1026.0f),
                        PathNode.LineTo(552.0f, 304.0f),
                        PathNode.QuadTo(546.0f, 298.0f, 545.5f, 290.5f),
                        PathNode.QuadTo(545.0f, 283.0f, 551.0f, 277.0f),
                        PathNode.LineTo(566.0f, 262.0f),
                        PathNode.QuadTo(572.0f, 255.0f, 579.0f, 255.0f),
                        PathNode.QuadTo(586.0f, 255.0f, 592.0f, 261.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _forwardLight!!
    }

private var _forwardLight: ImageVector? = null

val MiuixIcons.Regular.Forward: ImageVector
    get() {
        if (_forwardRegular != null) return _forwardRegular!!
        _forwardRegular = ImageVector.Builder(
            name = "Forward.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1141.2f,
            viewportHeight = 1141.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -93.89999999999998f, translationY = 945.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(964.0f, -79.0f),
                        PathNode.QuadTo(1017.0f, -52.0f, 1047.0f, 4.0f),
                        PathNode.QuadTo(1061.0f, 32.0f, 1064.0f, 71.5f),
                        PathNode.QuadTo(1067.0f, 111.0f, 1067.0f, 204.0f),
                        PathNode.VerticalTo(315.0f),
                        PathNode.QuadTo(1067.0f, 329.0f, 1060.5f, 337.0f),
                        PathNode.QuadTo(1054.0f, 345.0f, 1036.0f, 345.0f),
                        PathNode.HorizontalTo(1014.0f),
                        PathNode.QuadTo(995.0f, 345.0f, 988.5f, 337.0f),
                        PathNode.QuadTo(982.0f, 329.0f, 982.0f, 315.0f),
                        PathNode.VerticalTo(161.0f),
                        PathNode.QuadTo(982.0f, 108.0f, 980.0f, 85.0f),
                        PathNode.QuadTo(978.0f, 62.0f, 970.0f, 46.0f),
                        PathNode.QuadTo(955.0f, 13.0f, 922.0f, -2.0f),
                        PathNode.QuadTo(906.0f, -10.0f, 883.5f, -12.0f),
                        PathNode.QuadTo(861.0f, -14.0f, 807.0f, -14.0f),
                        PathNode.HorizontalTo(450.0f),
                        PathNode.QuadTo(396.0f, -14.0f, 373.5f, -12.0f),
                        PathNode.QuadTo(351.0f, -10.0f, 334.0f, -2.0f),
                        PathNode.QuadTo(302.0f, 14.0f, 287.0f, 46.0f),
                        PathNode.QuadTo(279.0f, 62.0f, 277.0f, 85.0f),
                        PathNode.QuadTo(275.0f, 108.0f, 275.0f, 161.0f),
                        PathNode.VerticalTo(516.0f),
                        PathNode.QuadTo(275.0f, 570.0f, 277.0f, 592.5f),
                        PathNode.QuadTo(279.0f, 615.0f, 287.0f, 631.0f),
                        PathNode.QuadTo(301.0f, 661.0f, 334.0f, 679.0f),
                        PathNode.QuadTo(351.0f, 687.0f, 373.5f, 689.0f),
                        PathNode.QuadTo(396.0f, 691.0f, 450.0f, 691.0f),
                        PathNode.HorizontalTo(600.0f),
                        PathNode.QuadTo(613.0f, 691.0f, 620.5f, 698.0f),
                        PathNode.QuadTo(628.0f, 705.0f, 628.0f, 720.0f),
                        PathNode.VerticalTo(747.0f),
                        PathNode.QuadTo(628.0f, 762.0f, 620.5f, 769.0f),
                        PathNode.QuadTo(613.0f, 776.0f, 600.0f, 776.0f),
                        PathNode.HorizontalTo(493.0f),
                        PathNode.QuadTo(400.0f, 776.0f, 360.5f, 773.0f),
                        PathNode.QuadTo(321.0f, 770.0f, 293.0f, 756.0f),
                        PathNode.QuadTo(238.0f, 727.0f, 210.0f, 673.0f),
                        PathNode.QuadTo(196.0f, 645.0f, 192.5f, 605.5f),
                        PathNode.QuadTo(189.0f, 566.0f, 189.0f, 472.0f),
                        PathNode.VerticalTo(204.0f),
                        PathNode.QuadTo(189.0f, 111.0f, 192.5f, 71.5f),
                        PathNode.QuadTo(196.0f, 32.0f, 210.0f, 4.0f),
                        PathNode.QuadTo(239.0f, -52.0f, 293.0f, -79.0f),
                        PathNode.QuadTo(321.0f, -93.0f, 360.5f, -96.0f),
                        PathNode.QuadTo(400.0f, -99.0f, 493.0f, -99.0f),
                        PathNode.HorizontalTo(763.0f),
                        PathNode.QuadTo(857.0f, -99.0f, 896.5f, -96.0f),
                        PathNode.QuadTo(936.0f, -93.0f, 964.0f, -79.0f),
                        PathNode.Close,
                        PathNode.MoveTo(604.0f, 253.0f),
                        PathNode.LineTo(1055.0f, 703.0f),
                        PathNode.VerticalTo(515.0f),
                        PathNode.QuadTo(1055.0f, 501.0f, 1061.0f, 494.5f),
                        PathNode.QuadTo(1067.0f, 488.0f, 1080.0f, 488.0f),
                        PathNode.HorizontalTo(1114.0f),
                        PathNode.QuadTo(1127.0f, 488.0f, 1133.5f, 494.5f),
                        PathNode.QuadTo(1140.0f, 501.0f, 1140.0f, 515.0f),
                        PathNode.VerticalTo(794.0f),
                        PathNode.QuadTo(1140.0f, 822.0f, 1127.0f, 835.5f),
                        PathNode.QuadTo(1114.0f, 849.0f, 1086.0f, 849.0f),
                        PathNode.HorizontalTo(804.0f),
                        PathNode.QuadTo(793.0f, 849.0f, 786.0f, 842.5f),
                        PathNode.QuadTo(779.0f, 836.0f, 779.0f, 824.0f),
                        PathNode.VerticalTo(790.0f),
                        PathNode.QuadTo(779.0f, 777.0f, 785.5f, 770.5f),
                        PathNode.QuadTo(792.0f, 764.0f, 804.0f, 764.0f),
                        PathNode.HorizontalTo(994.0f),
                        PathNode.LineTo(546.0f, 316.0f),
                        PathNode.QuadTo(536.0f, 306.0f, 535.0f, 295.5f),
                        PathNode.QuadTo(534.0f, 285.0f, 544.0f, 275.0f),
                        PathNode.LineTo(565.0f, 254.0f),
                        PathNode.QuadTo(575.0f, 244.0f, 585.0f, 244.5f),
                        PathNode.QuadTo(595.0f, 245.0f, 604.0f, 253.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _forwardRegular!!
    }

private var _forwardRegular: ImageVector? = null

val MiuixIcons.Heavy.Forward: ImageVector
    get() {
        if (_forwardHeavy != null) return _forwardHeavy!!
        _forwardHeavy = ImageVector.Builder(
            name = "Forward.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1174.8f,
            viewportHeight = 1174.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -77.10000000000002f, translationY = 962.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(970.0f, -91.0f),
                        PathNode.QuadTo(1027.0f, -62.0f, 1059.0f, -2.0f),
                        PathNode.QuadTo(1074.0f, 28.0f, 1077.5f, 69.5f),
                        PathNode.QuadTo(1081.0f, 111.0f, 1081.0f, 204.0f),
                        PathNode.VerticalTo(297.0f),
                        PathNode.QuadTo(1081.0f, 316.0f, 1070.5f, 328.0f),
                        PathNode.QuadTo(1060.0f, 340.0f, 1036.0f, 340.0f),
                        PathNode.HorizontalTo(1014.0f),
                        PathNode.QuadTo(990.0f, 340.0f, 979.0f, 328.0f),
                        PathNode.QuadTo(968.0f, 316.0f, 968.0f, 297.0f),
                        PathNode.VerticalTo(161.0f),
                        PathNode.QuadTo(968.0f, 108.0f, 966.5f, 87.0f),
                        PathNode.QuadTo(965.0f, 66.0f, 958.0f, 52.0f),
                        PathNode.QuadTo(944.0f, 24.0f, 916.0f, 10.0f),
                        PathNode.QuadTo(902.0f, 3.0f, 881.0f, 1.5f),
                        PathNode.QuadTo(860.0f, 0.0f, 807.0f, 0.0f),
                        PathNode.HorizontalTo(450.0f),
                        PathNode.QuadTo(397.0f, 0.0f, 376.0f, 1.5f),
                        PathNode.QuadTo(355.0f, 3.0f, 340.0f, 10.0f),
                        PathNode.QuadTo(313.0f, 24.0f, 299.0f, 52.0f),
                        PathNode.QuadTo(292.0f, 66.0f, 290.5f, 87.0f),
                        PathNode.QuadTo(289.0f, 108.0f, 289.0f, 161.0f),
                        PathNode.VerticalTo(516.0f),
                        PathNode.QuadTo(289.0f, 569.0f, 290.5f, 590.0f),
                        PathNode.QuadTo(292.0f, 611.0f, 299.0f, 625.0f),
                        PathNode.QuadTo(312.0f, 652.0f, 340.0f, 667.0f),
                        PathNode.QuadTo(355.0f, 674.0f, 376.0f, 675.5f),
                        PathNode.QuadTo(397.0f, 677.0f, 450.0f, 677.0f),
                        PathNode.HorizontalTo(577.0f),
                        PathNode.QuadTo(595.0f, 677.0f, 607.0f, 688.0f),
                        PathNode.QuadTo(619.0f, 699.0f, 619.0f, 720.0f),
                        PathNode.VerticalTo(747.0f),
                        PathNode.QuadTo(619.0f, 768.0f, 607.0f, 779.0f),
                        PathNode.QuadTo(595.0f, 790.0f, 577.0f, 790.0f),
                        PathNode.HorizontalTo(493.0f),
                        PathNode.QuadTo(400.0f, 790.0f, 358.5f, 786.5f),
                        PathNode.QuadTo(317.0f, 783.0f, 287.0f, 768.0f),
                        PathNode.QuadTo(227.0f, 737.0f, 198.0f, 679.0f),
                        PathNode.QuadTo(182.0f, 649.0f, 178.5f, 607.5f),
                        PathNode.QuadTo(175.0f, 566.0f, 175.0f, 472.0f),
                        PathNode.VerticalTo(204.0f),
                        PathNode.QuadTo(175.0f, 111.0f, 178.5f, 69.5f),
                        PathNode.QuadTo(182.0f, 28.0f, 198.0f, -2.0f),
                        PathNode.QuadTo(228.0f, -62.0f, 287.0f, -91.0f),
                        PathNode.QuadTo(317.0f, -106.0f, 358.5f, -109.5f),
                        PathNode.QuadTo(400.0f, -113.0f, 493.0f, -113.0f),
                        PathNode.HorizontalTo(763.0f),
                        PathNode.QuadTo(857.0f, -113.0f, 898.5f, -109.5f),
                        PathNode.QuadTo(940.0f, -106.0f, 970.0f, -91.0f),
                        PathNode.Close,
                        PathNode.MoveTo(614.0f, 243.0f),
                        PathNode.LineTo(1041.0f, 670.0f),
                        PathNode.VerticalTo(515.0f),
                        PathNode.QuadTo(1041.0f, 495.0f, 1051.0f, 484.5f),
                        PathNode.QuadTo(1061.0f, 474.0f, 1080.0f, 474.0f),
                        PathNode.HorizontalTo(1114.0f),
                        PathNode.QuadTo(1133.0f, 474.0f, 1143.5f, 484.5f),
                        PathNode.QuadTo(1154.0f, 495.0f, 1154.0f, 515.0f),
                        PathNode.VerticalTo(794.0f),
                        PathNode.QuadTo(1154.0f, 828.0f, 1137.0f, 845.5f),
                        PathNode.QuadTo(1120.0f, 863.0f, 1086.0f, 863.0f),
                        PathNode.HorizontalTo(804.0f),
                        PathNode.QuadTo(787.0f, 863.0f, 776.0f, 852.0f),
                        PathNode.QuadTo(765.0f, 841.0f, 765.0f, 824.0f),
                        PathNode.VerticalTo(790.0f),
                        PathNode.QuadTo(765.0f, 772.0f, 776.0f, 761.0f),
                        PathNode.QuadTo(787.0f, 750.0f, 804.0f, 750.0f),
                        PathNode.HorizontalTo(961.0f),
                        PathNode.LineTo(536.0f, 326.0f),
                        PathNode.QuadTo(523.0f, 312.0f, 521.5f, 295.5f),
                        PathNode.QuadTo(520.0f, 279.0f, 534.0f, 265.0f),
                        PathNode.LineTo(555.0f, 244.0f),
                        PathNode.QuadTo(569.0f, 230.0f, 585.0f, 230.5f),
                        PathNode.QuadTo(601.0f, 231.0f, 614.0f, 243.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _forwardHeavy!!
    }

private var _forwardHeavy: ImageVector? = null
