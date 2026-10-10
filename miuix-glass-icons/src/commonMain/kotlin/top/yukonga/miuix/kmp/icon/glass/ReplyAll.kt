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

val MiuixIcons.Glass.ReplyAll: ImageVector
    get() = MiuixIcons.Glass.Regular.ReplyAll

val MiuixIcons.Glass.Light.ReplyAll: ImageVector
    get() {
        if (_replyAllLight != null) return _replyAllLight!!
        _replyAllLight = ImageVector.Builder(
            name = "ReplyAll.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(919.1f, 544.6f),
                        PathNode.QuadTo(919.1f, 646.6f, 858.9f, 729.5f),
                        PathNode.QuadTo(798.7f, 812.4f, 696.8f, 859.9f),
                        PathNode.QuadTo(594.9f, 907.3f, 475.4f, 907.3f),
                        PathNode.QuadTo(363.5f, 907.3f, 261.7f, 861.9f),
                        PathNode.QuadTo(159.8f, 816.4f, 96.3f, 733.8f),
                        PathNode.QuadTo(32.8f, 651.2f, 32.8f, 544.6f),
                        PathNode.QuadTo(32.8f, 467.9f, 67.1f, 402.8f),
                        PathNode.QuadTo(101.4f, 337.7f, 158.7f, 290.7f),
                        PathNode.QuadTo(216.0f, 243.7f, 284.9f, 216.1f),
                        PathNode.QuadTo(298.5f, 211.0f, 303.3f, 200.4f),
                        PathNode.QuadTo(308.1f, 189.9f, 304.3f, 176.6f),
                        PathNode.LineTo(282.5f, 104.0f),
                        PathNode.QuadTo(281.0f, 99.0f, 284.6f, 95.6f),
                        PathNode.QuadTo(288.2f, 92.3f, 294.3f, 94.3f),
                        PathNode.LineTo(570.7f, 174.6f),
                        PathNode.QuadTo(729.3f, 221.2f, 824.2f, 308.1f),
                        PathNode.QuadTo(919.1f, 395.0f, 919.1f, 544.6f),
                        PathNode.Close,
                        PathNode.MoveTo(306.5f, 271.2f),
                        PathNode.QuadTo(213.9f, 307.6f, 152.5f, 379.1f),
                        PathNode.QuadTo(91.1f, 450.6f, 91.1f, 544.6f),
                        PathNode.QuadTo(91.1f, 635.2f, 147.2f, 704.3f),
                        PathNode.QuadTo(203.2f, 773.4f, 291.8f, 810.7f),
                        PathNode.QuadTo(380.4f, 848.0f, 475.4f, 848.0f),
                        PathNode.QuadTo(585.0f, 848.0f, 672.9f, 806.2f),
                        PathNode.QuadTo(760.8f, 764.4f, 810.3f, 694.5f),
                        PathNode.QuadTo(859.8f, 624.6f, 859.8f, 544.6f),
                        PathNode.QuadTo(859.8f, 456.1f, 818.4f, 393.9f),
                        PathNode.QuadTo(777.1f, 331.7f, 709.7f, 293.5f),
                        PathNode.QuadTo(642.2f, 255.2f, 551.1f, 229.3f),
                        PathNode.LineTo(369.0f, 177.1f),
                        PathNode.QuadTo(365.9f, 176.1f, 363.2f, 177.6f),
                        PathNode.QuadTo(360.5f, 179.2f, 360.4f, 183.7f),
                        PathNode.QuadTo(363.5f, 216.5f, 348.0f, 238.6f),
                        PathNode.QuadTo(332.4f, 260.7f, 306.5f, 271.2f),
                        PathNode.Close,
                        PathNode.MoveTo(1031.1f, 503.2f),
                        PathNode.QuadTo(1069.2f, 546.9f, 1089.7f, 598.2f),
                        PathNode.QuadTo(1110.1f, 649.5f, 1110.1f, 714.6f),
                        PathNode.QuadTo(1110.1f, 808.6f, 1055.1f, 885.0f),
                        PathNode.QuadTo(1000.1f, 961.4f, 906.5f, 1005.4f),
                        PathNode.QuadTo(812.9f, 1049.3f, 702.4f, 1049.3f),
                        PathNode.QuadTo(611.8f, 1049.3f, 516.3f, 1011.7f),
                        PathNode.QuadTo(512.2f, 1010.2f, 510.9f, 1006.5f),
                        PathNode.QuadTo(509.7f, 1002.9f, 512.0f, 999.5f),
                        PathNode.QuadTo(514.3f, 996.2f, 519.0f, 996.2f),
                        PathNode.QuadTo(566.4f, 995.9f, 615.5f, 984.5f),
                        PathNode.QuadTo(624.2f, 983.0f, 632.1f, 984.7f),
                        PathNode.QuadTo(657.4f, 990.0f, 702.4f, 990.0f),
                        PathNode.QuadTo(803.0f, 990.0f, 882.6f, 951.7f),
                        PathNode.QuadTo(962.2f, 913.4f, 1007.0f, 850.0f),
                        PathNode.QuadTo(1051.8f, 786.6f, 1051.8f, 714.6f),
                        PathNode.QuadTo(1051.8f, 646.6f, 1018.9f, 589.3f),
                        PathNode.QuadTo(1014.8f, 579.2f, 1016.2f, 571.5f),
                        PathNode.QuadTo(1018.3f, 555.0f, 1018.3f, 541.1f),
                        PathNode.QuadTo(1018.3f, 527.2f, 1016.5f, 511.2f),
                        PathNode.QuadTo(1016.0f, 504.9f, 1018.0f, 501.1f),
                        PathNode.QuadTo(1020.0f, 497.4f, 1023.7f, 497.9f),
                        PathNode.QuadTo(1027.3f, 498.4f, 1031.1f, 503.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyAllLight!!
    }

private var _replyAllLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ReplyAll: ImageVector
    get() {
        if (_replyAllNormal != null) return _replyAllNormal!!
        _replyAllNormal = ImageVector.Builder(
            name = "ReplyAll.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(928.3f, 546.4f),
                        PathNode.QuadTo(928.3f, 651.2f, 866.8f, 736.2f),
                        PathNode.QuadTo(805.4f, 821.2f, 701.3f, 869.8f),
                        PathNode.QuadTo(597.3f, 918.3f, 475.4f, 918.3f),
                        PathNode.QuadTo(361.4f, 918.3f, 257.4f, 871.8f),
                        PathNode.QuadTo(153.3f, 825.2f, 88.5f, 740.6f),
                        PathNode.QuadTo(23.6f, 656.1f, 23.6f, 546.4f),
                        PathNode.QuadTo(23.6f, 468.1f, 58.4f, 401.4f),
                        PathNode.QuadTo(93.2f, 334.7f, 152.0f, 286.3f),
                        PathNode.QuadTo(210.8f, 237.9f, 281.8f, 209.3f),
                        PathNode.QuadTo(290.9f, 206.4f, 294.5f, 198.2f),
                        PathNode.QuadTo(298.0f, 190.1f, 295.5f, 181.2f),
                        PathNode.LineTo(269.4f, 93.9f),
                        PathNode.QuadTo(267.5f, 88.9f, 271.8f, 85.1f),
                        PathNode.QuadTo(276.0f, 81.3f, 282.7f, 83.3f),
                        PathNode.LineTo(574.6f, 168.2f),
                        PathNode.QuadTo(734.2f, 215.1f, 831.2f, 304.1f),
                        PathNode.QuadTo(928.3f, 393.1f, 928.3f, 546.4f),
                        PathNode.Close,
                        PathNode.MoveTo(309.9f, 281.6f),
                        PathNode.QuadTo(219.1f, 317.7f, 159.7f, 386.4f),
                        PathNode.QuadTo(100.3f, 455.2f, 100.3f, 546.4f),
                        PathNode.QuadTo(100.3f, 634.6f, 155.2f, 701.5f),
                        PathNode.QuadTo(210.2f, 768.5f, 296.7f, 804.6f),
                        PathNode.QuadTo(383.2f, 840.6f, 475.4f, 840.6f),
                        PathNode.QuadTo(582.5f, 840.6f, 668.4f, 800.1f),
                        PathNode.QuadTo(754.3f, 759.5f, 802.5f, 691.6f),
                        PathNode.QuadTo(850.6f, 623.7f, 850.6f, 546.4f),
                        PathNode.QuadTo(850.6f, 460.3f, 810.2f, 400.0f),
                        PathNode.QuadTo(769.8f, 339.7f, 704.0f, 302.6f),
                        PathNode.QuadTo(638.2f, 265.6f, 549.3f, 240.3f),
                        PathNode.LineTo(383.0f, 192.3f),
                        PathNode.QuadTo(379.3f, 191.3f, 375.7f, 193.2f),
                        PathNode.QuadTo(372.1f, 195.1f, 371.4f, 199.9f),
                        PathNode.QuadTo(369.7f, 228.7f, 352.4f, 250.1f),
                        PathNode.QuadTo(335.2f, 271.4f, 309.9f, 281.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1034.8f, 497.1f),
                        PathNode.QuadTo(1076.2f, 541.6f, 1097.7f, 594.8f),
                        PathNode.QuadTo(1119.3f, 648.0f, 1119.3f, 716.4f),
                        PathNode.QuadTo(1119.3f, 813.2f, 1062.9f, 891.7f),
                        PathNode.QuadTo(1006.5f, 970.2f, 910.9f, 1015.3f),
                        PathNode.QuadTo(815.3f, 1060.3f, 702.4f, 1060.3f),
                        PathNode.QuadTo(602.6f, 1060.3f, 499.2f, 1016.9f),
                        PathNode.QuadTo(494.5f, 1015.1f, 493.1f, 1010.5f),
                        PathNode.QuadTo(491.6f, 1005.9f, 494.4f, 1002.1f),
                        PathNode.QuadTo(497.2f, 998.3f, 502.8f, 998.3f),
                        PathNode.QuadTo(569.2f, 998.9f, 638.1f, 983.0f),
                        PathNode.QuadTo(647.7f, 981.1f, 652.3f, 981.7f),
                        PathNode.QuadTo(665.7f, 982.6f, 702.4f, 982.6f),
                        PathNode.QuadTo(800.5f, 982.6f, 878.0f, 945.6f),
                        PathNode.QuadTo(955.5f, 908.5f, 999.0f, 847.1f),
                        PathNode.QuadTo(1042.6f, 785.7f, 1042.6f, 716.4f),
                        PathNode.QuadTo(1042.6f, 662.2f, 1021.3f, 614.0f),
                        PathNode.QuadTo(1016.6f, 600.5f, 1017.7f, 592.0f),
                        PathNode.QuadTo(1020.5f, 569.6f, 1020.5f, 549.5f),
                        PathNode.QuadTo(1020.5f, 529.3f, 1017.2f, 507.8f),
                        PathNode.QuadTo(1016.3f, 499.6f, 1018.3f, 494.7f),
                        PathNode.QuadTo(1020.3f, 489.7f, 1024.9f, 490.2f),
                        PathNode.QuadTo(1029.5f, 490.7f, 1034.8f, 497.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyAllNormal!!
    }

private var _replyAllNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ReplyAll: ImageVector
    get() {
        if (_replyAllRegular != null) return _replyAllRegular!!
        _replyAllRegular = ImageVector.Builder(
            name = "ReplyAll.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(932.4f, 547.3f),
                        PathNode.QuadTo(932.4f, 653.3f, 870.4f, 739.3f),
                        PathNode.QuadTo(808.4f, 825.3f, 703.4f, 874.3f),
                        PathNode.QuadTo(598.4f, 923.3f, 475.4f, 923.3f),
                        PathNode.QuadTo(360.4f, 923.3f, 255.4f, 876.3f),
                        PathNode.QuadTo(150.4f, 829.3f, 84.9f, 743.8f),
                        PathNode.QuadTo(19.4f, 658.3f, 19.4f, 547.3f),
                        PathNode.QuadTo(19.4f, 468.3f, 54.4f, 400.8f),
                        PathNode.QuadTo(89.4f, 333.3f, 148.9f, 284.3f),
                        PathNode.QuadTo(208.4f, 235.3f, 280.4f, 206.3f),
                        PathNode.QuadTo(287.4f, 204.3f, 290.4f, 197.3f),
                        PathNode.QuadTo(293.4f, 190.3f, 291.4f, 183.3f),
                        PathNode.LineTo(263.4f, 89.3f),
                        PathNode.QuadTo(261.4f, 84.3f, 265.9f, 80.3f),
                        PathNode.QuadTo(270.4f, 76.3f, 277.4f, 78.3f),
                        PathNode.LineTo(576.4f, 165.3f),
                        PathNode.QuadTo(736.4f, 212.3f, 834.4f, 302.3f),
                        PathNode.QuadTo(932.4f, 392.3f, 932.4f, 547.3f),
                        PathNode.Close,
                        PathNode.MoveTo(311.4f, 286.3f),
                        PathNode.QuadTo(221.4f, 322.3f, 162.9f, 389.8f),
                        PathNode.QuadTo(104.4f, 457.3f, 104.4f, 547.3f),
                        PathNode.QuadTo(104.4f, 634.3f, 158.9f, 700.3f),
                        PathNode.QuadTo(213.4f, 766.3f, 298.9f, 801.8f),
                        PathNode.QuadTo(384.4f, 837.3f, 475.4f, 837.3f),
                        PathNode.QuadTo(581.4f, 837.3f, 666.4f, 797.3f),
                        PathNode.QuadTo(751.4f, 757.3f, 798.9f, 690.3f),
                        PathNode.QuadTo(846.4f, 623.3f, 846.4f, 547.3f),
                        PathNode.QuadTo(846.4f, 462.3f, 806.4f, 402.8f),
                        PathNode.QuadTo(766.4f, 343.3f, 701.4f, 306.8f),
                        PathNode.QuadTo(636.4f, 270.3f, 548.4f, 245.3f),
                        PathNode.LineTo(389.4f, 199.3f),
                        PathNode.QuadTo(385.4f, 198.3f, 381.4f, 200.3f),
                        PathNode.QuadTo(377.4f, 202.3f, 376.4f, 207.3f),
                        PathNode.QuadTo(372.4f, 234.3f, 354.4f, 255.3f),
                        PathNode.QuadTo(336.4f, 276.3f, 311.4f, 286.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1036.4f, 494.3f),
                        PathNode.QuadTo(1079.4f, 539.3f, 1101.4f, 593.3f),
                        PathNode.QuadTo(1123.4f, 647.3f, 1123.4f, 717.3f),
                        PathNode.QuadTo(1123.4f, 815.3f, 1066.4f, 894.8f),
                        PathNode.QuadTo(1009.4f, 974.3f, 912.9f, 1019.8f),
                        PathNode.QuadTo(816.4f, 1065.3f, 702.4f, 1065.3f),
                        PathNode.QuadTo(598.4f, 1065.3f, 491.4f, 1019.3f),
                        PathNode.QuadTo(486.4f, 1017.3f, 484.9f, 1012.3f),
                        PathNode.QuadTo(483.4f, 1007.3f, 486.4f, 1003.3f),
                        PathNode.QuadTo(489.4f, 999.3f, 495.4f, 999.3f),
                        PathNode.QuadTo(570.4f, 1000.3f, 648.4f, 982.3f),
                        PathNode.QuadTo(658.4f, 980.3f, 661.4f, 980.3f),
                        PathNode.QuadTo(669.4f, 979.3f, 702.4f, 979.3f),
                        PathNode.QuadTo(799.4f, 979.3f, 875.9f, 942.8f),
                        PathNode.QuadTo(952.4f, 906.3f, 995.4f, 845.8f),
                        PathNode.QuadTo(1038.4f, 785.3f, 1038.4f, 717.3f),
                        PathNode.QuadTo(1038.4f, 669.3f, 1022.4f, 625.3f),
                        PathNode.QuadTo(1017.4f, 610.3f, 1018.4f, 601.3f),
                        PathNode.QuadTo(1021.4f, 576.3f, 1021.4f, 553.3f),
                        PathNode.QuadTo(1021.4f, 530.3f, 1017.4f, 506.3f),
                        PathNode.QuadTo(1016.4f, 497.3f, 1018.4f, 491.8f),
                        PathNode.QuadTo(1020.4f, 486.3f, 1025.4f, 486.8f),
                        PathNode.QuadTo(1030.4f, 487.3f, 1036.4f, 494.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyAllRegular!!
    }

private var _replyAllRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ReplyAll: ImageVector
    get() {
        if (_replyAllMedium != null) return _replyAllMedium!!
        _replyAllMedium = ImageVector.Builder(
            name = "ReplyAll.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(939.5f, 547.5f),
                        PathNode.QuadTo(939.5f, 655.6f, 876.2f, 743.3f),
                        PathNode.QuadTo(813.0f, 830.9f, 706.4f, 880.7f),
                        PathNode.QuadTo(599.8f, 930.5f, 475.4f, 930.5f),
                        PathNode.QuadTo(358.8f, 930.5f, 252.2f, 882.8f),
                        PathNode.QuadTo(145.6f, 835.2f, 79.0f, 747.9f),
                        PathNode.QuadTo(12.4f, 660.6f, 12.4f, 547.5f),
                        PathNode.QuadTo(12.4f, 466.0f, 48.8f, 397.1f),
                        PathNode.QuadTo(85.1f, 328.1f, 144.6f, 278.9f),
                        PathNode.QuadTo(204.1f, 229.8f, 272.9f, 202.2f),
                        PathNode.QuadTo(280.1f, 199.9f, 282.7f, 193.7f),
                        PathNode.QuadTo(285.3f, 187.5f, 283.3f, 180.5f),
                        PathNode.LineTo(256.7f, 91.4f),
                        PathNode.QuadTo(253.6f, 82.0f, 261.2f, 75.2f),
                        PathNode.QuadTo(268.8f, 68.4f, 279.6f, 71.7f),
                        PathNode.LineTo(579.9f, 159.3f),
                        PathNode.QuadTo(742.4f, 207.1f, 840.9f, 298.7f),
                        PathNode.QuadTo(939.5f, 390.3f, 939.5f, 547.5f),
                        PathNode.Close,
                        PathNode.MoveTo(314.1f, 293.0f),
                        PathNode.QuadTo(225.5f, 328.7f, 168.5f, 394.4f),
                        PathNode.QuadTo(111.5f, 460.2f, 111.5f, 547.5f),
                        PathNode.QuadTo(111.5f, 632.3f, 165.1f, 696.8f),
                        PathNode.QuadTo(218.8f, 761.3f, 302.8f, 795.9f),
                        PathNode.QuadTo(386.9f, 830.5f, 475.4f, 830.5f),
                        PathNode.QuadTo(579.5f, 830.5f, 662.9f, 791.4f),
                        PathNode.QuadTo(746.3f, 752.3f, 792.8f, 687.0f),
                        PathNode.QuadTo(839.4f, 621.6f, 839.4f, 547.5f),
                        PathNode.QuadTo(839.4f, 463.6f, 799.9f, 405.5f),
                        PathNode.QuadTo(760.5f, 347.5f, 696.4f, 312.2f),
                        PathNode.QuadTo(632.4f, 277.0f, 544.9f, 252.0f),
                        PathNode.LineTo(394.3f, 208.1f),
                        PathNode.QuadTo(389.8f, 206.9f, 385.9f, 208.7f),
                        PathNode.QuadTo(382.0f, 210.6f, 381.0f, 215.9f),
                        PathNode.QuadTo(377.0f, 240.2f, 358.3f, 261.4f),
                        PathNode.QuadTo(339.7f, 282.7f, 314.1f, 293.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1049.1f, 498.8f),
                        PathNode.QuadTo(1089.2f, 542.2f, 1109.8f, 596.0f),
                        PathNode.QuadTo(1130.5f, 649.9f, 1130.5f, 717.7f),
                        PathNode.QuadTo(1130.5f, 818.5f, 1072.0f, 899.4f),
                        PathNode.QuadTo(1013.5f, 980.4f, 916.0f, 1026.5f),
                        PathNode.QuadTo(818.6f, 1072.5f, 706.8f, 1072.2f),
                        PathNode.QuadTo(604.6f, 1072.2f, 509.5f, 1032.5f),
                        PathNode.QuadTo(501.3f, 1029.4f, 499.0f, 1022.3f),
                        PathNode.QuadTo(496.7f, 1015.3f, 500.8f, 1009.4f),
                        PathNode.QuadTo(504.8f, 1003.5f, 513.3f, 1003.3f),
                        PathNode.QuadTo(594.2f, 1001.3f, 667.9f, 981.7f),
                        PathNode.QuadTo(677.9f, 979.1f, 682.5f, 978.0f),
                        PathNode.QuadTo(694.3f, 974.6f, 722.7f, 973.5f),
                        PathNode.QuadTo(807.8f, 970.3f, 877.7f, 935.0f),
                        PathNode.QuadTo(947.6f, 899.7f, 988.7f, 843.0f),
                        PathNode.QuadTo(1029.8f, 786.3f, 1031.1f, 722.9f),
                        PathNode.QuadTo(1031.9f, 680.0f, 1020.3f, 639.5f),
                        PathNode.QuadTo(1016.6f, 624.5f, 1018.4f, 612.6f),
                        PathNode.QuadTo(1021.7f, 588.9f, 1022.6f, 563.2f),
                        PathNode.QuadTo(1023.6f, 537.5f, 1020.7f, 512.7f),
                        PathNode.QuadTo(1019.9f, 501.3f, 1023.7f, 494.7f),
                        PathNode.QuadTo(1027.5f, 488.1f, 1034.5f, 489.0f),
                        PathNode.QuadTo(1041.5f, 489.9f, 1049.1f, 498.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyAllMedium!!
    }

private var _replyAllMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ReplyAll: ImageVector
    get() {
        if (_replyAllDemibold != null) return _replyAllDemibold!!
        _replyAllDemibold = ImageVector.Builder(
            name = "ReplyAll.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(944.4f, 547.6f),
                        PathNode.QuadTo(944.4f, 657.3f, 880.3f, 746.1f),
                        PathNode.QuadTo(816.2f, 834.8f, 708.5f, 885.2f),
                        PathNode.QuadTo(600.7f, 935.6f, 475.4f, 935.6f),
                        PathNode.QuadTo(357.7f, 935.6f, 249.9f, 887.4f),
                        PathNode.QuadTo(142.2f, 839.3f, 74.8f, 750.8f),
                        PathNode.QuadTo(7.5f, 662.3f, 7.5f, 547.6f),
                        PathNode.QuadTo(7.5f, 464.5f, 44.8f, 394.5f),
                        PathNode.QuadTo(82.1f, 324.4f, 141.6f, 275.2f),
                        PathNode.QuadTo(201.1f, 226.0f, 267.6f, 199.3f),
                        PathNode.QuadTo(275.0f, 196.8f, 277.3f, 191.2f),
                        PathNode.QuadTo(279.6f, 185.6f, 277.6f, 178.6f),
                        PathNode.LineTo(251.9f, 92.8f),
                        PathNode.QuadTo(248.1f, 80.5f, 257.9f, 71.7f),
                        PathNode.QuadTo(267.7f, 62.8f, 281.1f, 67.1f),
                        PathNode.LineTo(582.4f, 155.1f),
                        PathNode.QuadTo(746.5f, 203.4f, 845.5f, 296.2f),
                        PathNode.QuadTo(944.4f, 389.0f, 944.4f, 547.6f),
                        PathNode.Close,
                        PathNode.MoveTo(316.0f, 297.7f),
                        PathNode.QuadTo(228.3f, 333.2f, 172.3f, 397.7f),
                        PathNode.QuadTo(116.4f, 462.2f, 116.4f, 547.6f),
                        PathNode.QuadTo(116.4f, 631.0f, 169.5f, 694.4f),
                        PathNode.QuadTo(222.6f, 757.9f, 305.6f, 791.8f),
                        PathNode.QuadTo(388.6f, 825.7f, 475.4f, 825.7f),
                        PathNode.QuadTo(578.2f, 825.7f, 660.5f, 787.3f),
                        PathNode.QuadTo(742.7f, 748.9f, 788.6f, 684.7f),
                        PathNode.QuadTo(834.5f, 620.4f, 834.5f, 547.6f),
                        PathNode.QuadTo(834.5f, 464.5f, 795.4f, 407.5f),
                        PathNode.QuadTo(756.3f, 350.5f, 692.9f, 316.1f),
                        PathNode.QuadTo(629.5f, 281.7f, 542.5f, 256.7f),
                        PathNode.LineTo(397.7f, 214.3f),
                        PathNode.QuadTo(392.8f, 212.9f, 389.0f, 214.6f),
                        PathNode.QuadTo(385.2f, 216.4f, 384.2f, 221.9f),
                        PathNode.QuadTo(380.2f, 244.3f, 361.1f, 265.7f),
                        PathNode.QuadTo(341.9f, 287.2f, 316.0f, 297.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1058.0f, 502.0f),
                        PathNode.QuadTo(1096.0f, 544.2f, 1115.7f, 598.0f),
                        PathNode.QuadTo(1135.4f, 651.8f, 1135.4f, 718.1f),
                        PathNode.QuadTo(1135.4f, 820.7f, 1075.8f, 902.7f),
                        PathNode.QuadTo(1016.3f, 984.7f, 918.2f, 1031.2f),
                        PathNode.QuadTo(820.1f, 1077.6f, 709.8f, 1077.1f),
                        PathNode.QuadTo(609.0f, 1077.1f, 522.2f, 1041.7f),
                        PathNode.QuadTo(511.7f, 1037.8f, 508.8f, 1029.4f),
                        PathNode.QuadTo(505.9f, 1021.0f, 510.8f, 1013.7f),
                        PathNode.QuadTo(515.6f, 1006.5f, 525.8f, 1006.1f),
                        PathNode.QuadTo(610.9f, 1002.0f, 681.5f, 981.3f),
                        PathNode.QuadTo(691.5f, 978.3f, 697.3f, 976.5f),
                        PathNode.QuadTo(711.7f, 971.4f, 736.9f, 969.5f),
                        PathNode.QuadTo(813.7f, 964.0f, 878.9f, 929.6f),
                        PathNode.QuadTo(944.2f, 895.1f, 983.9f, 841.1f),
                        PathNode.QuadTo(1023.7f, 787.0f, 1026.0f, 726.8f),
                        PathNode.QuadTo(1027.4f, 687.5f, 1018.8f, 649.5f),
                        PathNode.QuadTo(1016.1f, 634.5f, 1018.4f, 620.5f),
                        PathNode.QuadTo(1021.9f, 597.8f, 1023.5f, 570.2f),
                        PathNode.QuadTo(1025.1f, 542.6f, 1022.9f, 517.2f),
                        PathNode.QuadTo(1022.4f, 504.1f, 1027.4f, 496.7f),
                        PathNode.QuadTo(1032.4f, 489.4f, 1040.8f, 490.6f),
                        PathNode.QuadTo(1049.3f, 491.8f, 1058.0f, 502.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyAllDemibold!!
    }

private var _replyAllDemibold: ImageVector? = null
