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

val MiuixIcons.ReplyAll: ImageVector
    get() = MiuixIcons.Regular.ReplyAll

val MiuixIcons.Light.ReplyAll: ImageVector
    get() {
        if (_replyallLight != null) return _replyallLight!!
        _replyallLight = ImageVector.Builder(
            name = "ReplyAll.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1293.6f,
            viewportHeight = 1293.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -18.200000000000045f, translationY = 1028.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1013.0f, 355.0f),
                        PathNode.QuadTo(1013.0f, 457.0f, 952.5f, 540.0f),
                        PathNode.QuadTo(892.0f, 623.0f, 790.5f, 670.5f),
                        PathNode.QuadTo(689.0f, 718.0f, 569.0f, 718.0f),
                        PathNode.QuadTo(457.0f, 718.0f, 355.0f, 672.5f),
                        PathNode.QuadTo(253.0f, 627.0f, 189.5f, 544.5f),
                        PathNode.QuadTo(126.0f, 462.0f, 126.0f, 355.0f),
                        PathNode.QuadTo(126.0f, 278.0f, 160.5f, 213.0f),
                        PathNode.QuadTo(195.0f, 148.0f, 252.0f, 101.0f),
                        PathNode.QuadTo(309.0f, 54.0f, 378.0f, 26.0f),
                        PathNode.QuadTo(392.0f, 21.0f, 397.0f, 10.5f),
                        PathNode.QuadTo(402.0f, 0.0f, 398.0f, -13.0f),
                        PathNode.LineTo(376.0f, -86.0f),
                        PathNode.QuadTo(374.0f, -91.0f, 378.0f, -94.0f),
                        PathNode.QuadTo(382.0f, -97.0f, 388.0f, -95.0f),
                        PathNode.LineTo(664.0f, -15.0f),
                        PathNode.QuadTo(823.0f, 31.0f, 918.0f, 118.0f),
                        PathNode.QuadTo(1013.0f, 205.0f, 1013.0f, 355.0f),
                        PathNode.Close,
                        PathNode.MoveTo(400.0f, 82.0f),
                        PathNode.QuadTo(308.0f, 118.0f, 246.5f, 189.5f),
                        PathNode.QuadTo(185.0f, 261.0f, 185.0f, 355.0f),
                        PathNode.QuadTo(185.0f, 445.0f, 241.0f, 514.0f),
                        PathNode.QuadTo(297.0f, 583.0f, 385.5f, 620.5f),
                        PathNode.QuadTo(474.0f, 658.0f, 569.0f, 658.0f),
                        PathNode.QuadTo(678.0f, 658.0f, 766.0f, 616.5f),
                        PathNode.QuadTo(854.0f, 575.0f, 903.5f, 505.0f),
                        PathNode.QuadTo(953.0f, 435.0f, 953.0f, 355.0f),
                        PathNode.QuadTo(953.0f, 226.0f, 869.5f, 153.0f),
                        PathNode.QuadTo(786.0f, 80.0f, 645.0f, 40.0f),
                        PathNode.LineTo(463.0f, -13.0f),
                        PathNode.QuadTo(459.0f, -14.0f, 456.5f, -12.0f),
                        PathNode.QuadTo(454.0f, -10.0f, 454.0f, -6.0f),
                        PathNode.QuadTo(458.0f, 26.0f, 442.0f, 48.5f),
                        PathNode.QuadTo(426.0f, 71.0f, 400.0f, 82.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1125.0f, 314.0f),
                        PathNode.QuadTo(1163.0f, 357.0f, 1183.5f, 408.5f),
                        PathNode.QuadTo(1204.0f, 460.0f, 1204.0f, 525.0f),
                        PathNode.QuadTo(1204.0f, 619.0f, 1149.0f, 695.5f),
                        PathNode.QuadTo(1094.0f, 772.0f, 1000.5f, 816.0f),
                        PathNode.QuadTo(907.0f, 860.0f, 796.0f, 860.0f),
                        PathNode.QuadTo(706.0f, 860.0f, 610.0f, 822.0f),
                        PathNode.QuadTo(606.0f, 821.0f, 605.0f, 817.0f),
                        PathNode.QuadTo(604.0f, 813.0f, 606.0f, 810.0f),
                        PathNode.QuadTo(608.0f, 807.0f, 613.0f, 807.0f),
                        PathNode.QuadTo(663.0f, 806.0f, 709.0f, 795.0f),
                        PathNode.QuadTo(716.0f, 793.0f, 732.0f, 796.0f),
                        PathNode.QuadTo(743.0f, 797.0f, 759.5f, 798.5f),
                        PathNode.QuadTo(776.0f, 800.0f, 796.0f, 800.0f),
                        PathNode.QuadTo(896.0f, 800.0f, 976.0f, 762.0f),
                        PathNode.QuadTo(1056.0f, 724.0f, 1100.5f, 660.5f),
                        PathNode.QuadTo(1145.0f, 597.0f, 1145.0f, 525.0f),
                        PathNode.QuadTo(1145.0f, 456.0f, 1112.0f, 400.0f),
                        PathNode.QuadTo(1109.0f, 391.0f, 1110.0f, 382.0f),
                        PathNode.QuadTo(1113.0f, 351.0f, 1110.0f, 322.0f),
                        PathNode.QuadTo(1109.0f, 311.0f, 1113.5f, 308.5f),
                        PathNode.QuadTo(1118.0f, 306.0f, 1125.0f, 314.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyallLight!!
    }

private var _replyallLight: ImageVector? = null

val MiuixIcons.Regular.ReplyAll: ImageVector
    get() {
        if (_replyallRegular != null) return _replyallRegular!!
        _replyallRegular = ImageVector.Builder(
            name = "ReplyAll.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1324.8f,
            viewportHeight = 1324.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -2.6000000000000227f, translationY = 1041.5666666666666f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1026.0f, 355.0f),
                        PathNode.QuadTo(1026.0f, 461.0f, 964.0f, 547.0f),
                        PathNode.QuadTo(902.0f, 633.0f, 797.0f, 682.0f),
                        PathNode.QuadTo(692.0f, 731.0f, 569.0f, 731.0f),
                        PathNode.QuadTo(454.0f, 731.0f, 349.0f, 684.0f),
                        PathNode.QuadTo(244.0f, 637.0f, 178.5f, 551.5f),
                        PathNode.QuadTo(113.0f, 466.0f, 113.0f, 355.0f),
                        PathNode.QuadTo(113.0f, 276.0f, 148.0f, 208.5f),
                        PathNode.QuadTo(183.0f, 141.0f, 242.5f, 92.0f),
                        PathNode.QuadTo(302.0f, 43.0f, 374.0f, 14.0f),
                        PathNode.QuadTo(381.0f, 12.0f, 384.0f, 5.0f),
                        PathNode.QuadTo(387.0f, -2.0f, 385.0f, -9.0f),
                        PathNode.LineTo(357.0f, -103.0f),
                        PathNode.QuadTo(355.0f, -108.0f, 359.5f, -112.0f),
                        PathNode.QuadTo(364.0f, -116.0f, 371.0f, -114.0f),
                        PathNode.LineTo(670.0f, -27.0f),
                        PathNode.QuadTo(830.0f, 20.0f, 928.0f, 110.0f),
                        PathNode.QuadTo(1026.0f, 200.0f, 1026.0f, 355.0f),
                        PathNode.Close,
                        PathNode.MoveTo(405.0f, 94.0f),
                        PathNode.QuadTo(315.0f, 130.0f, 256.5f, 197.5f),
                        PathNode.QuadTo(198.0f, 265.0f, 198.0f, 355.0f),
                        PathNode.QuadTo(198.0f, 442.0f, 252.5f, 508.0f),
                        PathNode.QuadTo(307.0f, 574.0f, 392.5f, 609.5f),
                        PathNode.QuadTo(478.0f, 645.0f, 569.0f, 645.0f),
                        PathNode.QuadTo(675.0f, 645.0f, 760.0f, 605.0f),
                        PathNode.QuadTo(845.0f, 565.0f, 892.5f, 498.0f),
                        PathNode.QuadTo(940.0f, 431.0f, 940.0f, 355.0f),
                        PathNode.QuadTo(940.0f, 231.0f, 859.5f, 161.5f),
                        PathNode.QuadTo(779.0f, 92.0f, 642.0f, 53.0f),
                        PathNode.LineTo(483.0f, 7.0f),
                        PathNode.QuadTo(479.0f, 6.0f, 475.0f, 8.0f),
                        PathNode.QuadTo(471.0f, 10.0f, 470.0f, 15.0f),
                        PathNode.QuadTo(466.0f, 42.0f, 448.0f, 63.0f),
                        PathNode.QuadTo(430.0f, 84.0f, 405.0f, 94.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1130.0f, 302.0f),
                        PathNode.QuadTo(1173.0f, 347.0f, 1195.0f, 401.0f),
                        PathNode.QuadTo(1217.0f, 455.0f, 1217.0f, 525.0f),
                        PathNode.QuadTo(1217.0f, 623.0f, 1160.0f, 702.5f),
                        PathNode.QuadTo(1103.0f, 782.0f, 1006.5f, 827.5f),
                        PathNode.QuadTo(910.0f, 873.0f, 796.0f, 873.0f),
                        PathNode.QuadTo(692.0f, 873.0f, 585.0f, 827.0f),
                        PathNode.QuadTo(580.0f, 825.0f, 578.5f, 820.0f),
                        PathNode.QuadTo(577.0f, 815.0f, 580.0f, 811.0f),
                        PathNode.QuadTo(583.0f, 807.0f, 589.0f, 807.0f),
                        PathNode.QuadTo(664.0f, 808.0f, 742.0f, 790.0f),
                        PathNode.QuadTo(750.0f, 788.0f, 753.0f, 788.0f),
                        PathNode.QuadTo(760.0f, 787.0f, 770.0f, 787.0f),
                        PathNode.QuadTo(780.0f, 787.0f, 796.0f, 787.0f),
                        PathNode.QuadTo(893.0f, 787.0f, 969.5f, 750.5f),
                        PathNode.QuadTo(1046.0f, 714.0f, 1089.0f, 653.5f),
                        PathNode.QuadTo(1132.0f, 593.0f, 1132.0f, 525.0f),
                        PathNode.QuadTo(1132.0f, 477.0f, 1116.0f, 433.0f),
                        PathNode.QuadTo(1111.0f, 418.0f, 1112.0f, 409.0f),
                        PathNode.QuadTo(1118.0f, 359.0f, 1111.0f, 314.0f),
                        PathNode.QuadTo(1109.0f, 299.0f, 1114.5f, 295.0f),
                        PathNode.QuadTo(1120.0f, 291.0f, 1130.0f, 302.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyallRegular!!
    }

private var _replyallRegular: ImageVector? = null

val MiuixIcons.Heavy.ReplyAll: ImageVector
    get() {
        if (_replyallHeavy != null) return _replyallHeavy!!
        _replyallHeavy = ImageVector.Builder(
            name = "ReplyAll.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1353.6f,
            viewportHeight = 1353.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 11.799999999999955f, translationY = 1055.6705263157894f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1038.0f, 355.0f),
                        PathNode.QuadTo(1038.0f, 465.0f, 974.0f, 553.5f),
                        PathNode.QuadTo(910.0f, 642.0f, 802.0f, 692.5f),
                        PathNode.QuadTo(694.0f, 743.0f, 569.0f, 743.0f),
                        PathNode.QuadTo(451.0f, 743.0f, 343.0f, 695.0f),
                        PathNode.QuadTo(235.0f, 647.0f, 168.0f, 558.5f),
                        PathNode.QuadTo(101.0f, 470.0f, 101.0f, 355.0f),
                        PathNode.QuadTo(101.0f, 272.0f, 138.5f, 202.0f),
                        PathNode.QuadTo(176.0f, 132.0f, 235.0f, 82.5f),
                        PathNode.QuadTo(294.0f, 33.0f, 361.0f, 7.0f),
                        PathNode.QuadTo(368.0f, 4.0f, 370.5f, -1.0f),
                        PathNode.QuadTo(373.0f, -6.0f, 371.0f, -14.0f),
                        PathNode.LineTo(346.0f, -100.0f),
                        PathNode.QuadTo(342.0f, -112.0f, 352.0f, -120.5f),
                        PathNode.QuadTo(362.0f, -129.0f, 375.0f, -125.0f),
                        PathNode.LineTo(676.0f, -38.0f),
                        PathNode.QuadTo(841.0f, 11.0f, 939.5f, 103.5f),
                        PathNode.QuadTo(1038.0f, 196.0f, 1038.0f, 355.0f),
                        PathNode.Close,
                        PathNode.MoveTo(410.0f, 105.0f),
                        PathNode.QuadTo(322.0f, 141.0f, 266.0f, 205.0f),
                        PathNode.QuadTo(210.0f, 269.0f, 210.0f, 355.0f),
                        PathNode.QuadTo(210.0f, 438.0f, 263.0f, 501.5f),
                        PathNode.QuadTo(316.0f, 565.0f, 399.0f, 599.0f),
                        PathNode.QuadTo(482.0f, 633.0f, 569.0f, 633.0f),
                        PathNode.QuadTo(672.0f, 633.0f, 754.0f, 594.5f),
                        PathNode.QuadTo(836.0f, 556.0f, 882.0f, 492.0f),
                        PathNode.QuadTo(928.0f, 428.0f, 928.0f, 355.0f),
                        PathNode.QuadTo(928.0f, 234.0f, 849.5f, 168.5f),
                        PathNode.QuadTo(771.0f, 103.0f, 636.0f, 64.0f),
                        PathNode.LineTo(491.0f, 22.0f),
                        PathNode.QuadTo(486.0f, 20.0f, 482.5f, 22.0f),
                        PathNode.QuadTo(479.0f, 24.0f, 478.0f, 29.0f),
                        PathNode.QuadTo(473.0f, 51.0f, 454.5f, 73.0f),
                        PathNode.QuadTo(436.0f, 95.0f, 410.0f, 105.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1152.0f, 309.0f),
                        PathNode.QuadTo(1190.0f, 352.0f, 1209.5f, 406.0f),
                        PathNode.QuadTo(1229.0f, 460.0f, 1229.0f, 525.0f),
                        PathNode.QuadTo(1229.0f, 628.0f, 1169.5f, 710.0f),
                        PathNode.QuadTo(1110.0f, 792.0f, 1012.0f, 838.5f),
                        PathNode.QuadTo(914.0f, 885.0f, 803.0f, 884.0f),
                        PathNode.QuadTo(701.0f, 884.0f, 616.0f, 849.0f),
                        PathNode.QuadTo(606.0f, 845.0f, 602.5f, 837.0f),
                        PathNode.QuadTo(599.0f, 829.0f, 604.0f, 821.5f),
                        PathNode.QuadTo(609.0f, 814.0f, 619.0f, 813.0f),
                        PathNode.QuadTo(706.0f, 810.0f, 775.0f, 789.0f),
                        PathNode.QuadTo(778.0f, 788.0f, 781.0f, 787.0f),
                        PathNode.QuadTo(790.0f, 784.0f, 801.5f, 781.5f),
                        PathNode.QuadTo(813.0f, 779.0f, 830.0f, 777.0f),
                        PathNode.QuadTo(906.0f, 772.0f, 972.0f, 737.0f),
                        PathNode.QuadTo(1038.0f, 702.0f, 1078.0f, 648.0f),
                        PathNode.QuadTo(1118.0f, 594.0f, 1120.0f, 534.0f),
                        PathNode.QuadTo(1121.0f, 486.0f, 1112.0f, 457.0f),
                        PathNode.QuadTo(1109.0f, 444.0f, 1112.0f, 428.0f),
                        PathNode.QuadTo(1120.0f, 372.0f, 1117.0f, 325.0f),
                        PathNode.QuadTo(1115.0f, 304.0f, 1126.5f, 298.5f),
                        PathNode.QuadTo(1138.0f, 293.0f, 1152.0f, 309.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyallHeavy!!
    }

private var _replyallHeavy: ImageVector? = null
