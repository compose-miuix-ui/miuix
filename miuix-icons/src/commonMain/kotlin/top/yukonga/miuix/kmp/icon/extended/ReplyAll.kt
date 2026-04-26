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
            group(scaleY = -1.0f, translationY = 1293.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(994.8f, 620.3f),
                        PathNode.QuadTo(994.8f, 722.3f, 934.3f, 805.3f),
                        PathNode.QuadTo(873.8f, 888.3f, 772.3f, 935.8f),
                        PathNode.QuadTo(670.8f, 983.3f, 550.8f, 983.3f),
                        PathNode.QuadTo(438.8f, 983.3f, 336.8f, 937.8f),
                        PathNode.QuadTo(234.8f, 892.3f, 171.3f, 809.8f),
                        PathNode.QuadTo(107.8f, 727.3f, 107.8f, 620.3f),
                        PathNode.QuadTo(107.8f, 543.3f, 142.3f, 478.3f),
                        PathNode.QuadTo(176.8f, 413.3f, 233.8f, 366.3f),
                        PathNode.QuadTo(290.8f, 319.3f, 359.8f, 291.3f),
                        PathNode.QuadTo(373.8f, 286.3f, 378.8f, 275.8f),
                        PathNode.QuadTo(383.8f, 265.3f, 379.8f, 252.3f),
                        PathNode.LineTo(357.8f, 179.3f),
                        PathNode.QuadTo(355.8f, 174.3f, 359.8f, 171.3f),
                        PathNode.QuadTo(363.8f, 168.3f, 369.8f, 170.3f),
                        PathNode.LineTo(645.8f, 250.3f),
                        PathNode.QuadTo(804.8f, 296.3f, 899.8f, 383.3f),
                        PathNode.QuadTo(994.8f, 470.3f, 994.8f, 620.3f),
                        PathNode.Close,
                        PathNode.MoveTo(381.8f, 347.3f),
                        PathNode.QuadTo(289.8f, 383.3f, 228.3f, 454.8f),
                        PathNode.QuadTo(166.8f, 526.3f, 166.8f, 620.3f),
                        PathNode.QuadTo(166.8f, 710.3f, 222.8f, 779.3f),
                        PathNode.QuadTo(278.8f, 848.3f, 367.3f, 885.8f),
                        PathNode.QuadTo(455.8f, 923.3f, 550.8f, 923.3f),
                        PathNode.QuadTo(659.8f, 923.3f, 747.8f, 881.8f),
                        PathNode.QuadTo(835.8f, 840.3f, 885.3f, 770.3f),
                        PathNode.QuadTo(934.8f, 700.3f, 934.8f, 620.3f),
                        PathNode.QuadTo(934.8f, 491.3f, 851.3f, 418.3f),
                        PathNode.QuadTo(767.8f, 345.3f, 626.8f, 305.3f),
                        PathNode.LineTo(444.8f, 252.3f),
                        PathNode.QuadTo(440.8f, 251.3f, 438.3f, 253.3f),
                        PathNode.QuadTo(435.8f, 255.3f, 435.8f, 259.3f),
                        PathNode.QuadTo(439.8f, 291.3f, 423.8f, 313.8f),
                        PathNode.QuadTo(407.8f, 336.3f, 381.8f, 347.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1106.8f, 579.3f),
                        PathNode.QuadTo(1144.8f, 622.3f, 1165.3f, 673.8f),
                        PathNode.QuadTo(1185.8f, 725.3f, 1185.8f, 790.3f),
                        PathNode.QuadTo(1185.8f, 884.3f, 1130.8f, 960.8f),
                        PathNode.QuadTo(1075.8f, 1037.3f, 982.3f, 1081.3f),
                        PathNode.QuadTo(888.8f, 1125.3f, 777.8f, 1125.3f),
                        PathNode.QuadTo(687.8f, 1125.3f, 591.8f, 1087.3f),
                        PathNode.QuadTo(587.8f, 1086.3f, 586.8f, 1082.3f),
                        PathNode.QuadTo(585.8f, 1078.3f, 587.8f, 1075.3f),
                        PathNode.QuadTo(589.8f, 1072.3f, 594.8f, 1072.3f),
                        PathNode.QuadTo(644.8f, 1071.3f, 690.8f, 1060.3f),
                        PathNode.QuadTo(697.8f, 1058.3f, 713.8f, 1061.3f),
                        PathNode.QuadTo(724.8f, 1062.3f, 741.3f, 1063.8f),
                        PathNode.QuadTo(757.8f, 1065.3f, 777.8f, 1065.3f),
                        PathNode.QuadTo(877.8f, 1065.3f, 957.8f, 1027.3f),
                        PathNode.QuadTo(1037.8f, 989.3f, 1082.3f, 925.8f),
                        PathNode.QuadTo(1126.8f, 862.3f, 1126.8f, 790.3f),
                        PathNode.QuadTo(1126.8f, 721.3f, 1093.8f, 665.3f),
                        PathNode.QuadTo(1090.8f, 656.3f, 1091.8f, 647.3f),
                        PathNode.QuadTo(1094.8f, 616.3f, 1091.8f, 587.3f),
                        PathNode.QuadTo(1090.8f, 576.3f, 1095.3f, 573.8f),
                        PathNode.QuadTo(1099.8f, 571.3f, 1106.8f, 579.3f),
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

val MiuixIcons.Normal.ReplyAll: ImageVector
    get() {
        if (_replyallNormal != null) return _replyallNormal!!
        _replyallNormal = ImageVector.Builder(
            name = "ReplyAll.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1315.0f,
            viewportHeight = 1315.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1315.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1014.5f, 633.1f),
                        PathNode.QuadTo(1014.5f, 737.8f, 952.9f, 822.9f),
                        PathNode.QuadTo(891.4f, 908.0f, 787.5f, 956.5f),
                        PathNode.QuadTo(683.6f, 1005.0f, 561.5f, 1005.0f),
                        PathNode.QuadTo(447.5f, 1005.0f, 343.4f, 958.5f),
                        PathNode.QuadTo(239.3f, 912.0f, 174.5f, 827.4f),
                        PathNode.QuadTo(109.6f, 742.8f, 109.6f, 633.1f),
                        PathNode.QuadTo(109.6f, 554.7f, 144.4f, 488.0f),
                        PathNode.QuadTo(179.3f, 421.3f, 238.0f, 372.9f),
                        PathNode.QuadTo(296.7f, 324.5f, 367.8f, 295.8f),
                        PathNode.QuadTo(377.0f, 292.9f, 380.6f, 284.8f),
                        PathNode.QuadTo(384.2f, 276.7f, 381.6f, 267.8f),
                        PathNode.LineTo(355.5f, 180.4f),
                        PathNode.QuadTo(353.5f, 175.4f, 357.8f, 171.7f),
                        PathNode.QuadTo(362.1f, 168.0f, 368.8f, 170.0f),
                        PathNode.LineTo(660.6f, 254.8f),
                        PathNode.QuadTo(820.3f, 301.5f, 917.4f, 390.6f),
                        PathNode.QuadTo(1014.5f, 479.6f, 1014.5f, 633.1f),
                        PathNode.Close,
                        PathNode.MoveTo(396.0f, 368.3f),
                        PathNode.QuadTo(305.3f, 404.3f, 245.9f, 473.1f),
                        PathNode.QuadTo(186.5f, 541.8f, 186.5f, 633.1f),
                        PathNode.QuadTo(186.5f, 721.0f, 241.4f, 788.0f),
                        PathNode.QuadTo(296.4f, 854.9f, 382.8f, 891.0f),
                        PathNode.QuadTo(469.3f, 927.1f, 561.5f, 927.1f),
                        PathNode.QuadTo(668.5f, 927.1f, 754.4f, 886.7f),
                        PathNode.QuadTo(840.3f, 846.2f, 888.5f, 778.3f),
                        PathNode.QuadTo(936.6f, 710.3f, 936.6f, 633.1f),
                        PathNode.QuadTo(936.6f, 507.5f, 855.1f, 436.9f),
                        PathNode.QuadTo(773.7f, 366.3f, 635.5f, 327.0f),
                        PathNode.LineTo(469.3f, 278.8f),
                        PathNode.QuadTo(465.3f, 277.8f, 461.7f, 279.8f),
                        PathNode.QuadTo(458.2f, 281.8f, 457.5f, 286.5f),
                        PathNode.QuadTo(456.0f, 315.1f, 438.6f, 336.6f),
                        PathNode.QuadTo(421.3f, 358.0f, 396.0f, 368.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1121.0f, 583.8f),
                        PathNode.QuadTo(1162.4f, 628.2f, 1183.9f, 681.4f),
                        PathNode.QuadTo(1205.5f, 734.6f, 1205.5f, 803.1f),
                        PathNode.QuadTo(1205.5f, 899.8f, 1149.1f, 978.4f),
                        PathNode.QuadTo(1092.7f, 1057.0f, 997.1f, 1102.0f),
                        PathNode.QuadTo(901.6f, 1147.0f, 788.5f, 1147.0f),
                        PathNode.QuadTo(688.9f, 1147.0f, 585.3f, 1103.5f),
                        PathNode.QuadTo(580.6f, 1101.8f, 579.3f, 1097.2f),
                        PathNode.QuadTo(578.0f, 1092.5f, 580.6f, 1088.8f),
                        PathNode.QuadTo(583.3f, 1085.1f, 589.0f, 1085.1f),
                        PathNode.QuadTo(656.2f, 1085.5f, 724.2f, 1069.7f),
                        PathNode.QuadTo(731.9f, 1067.7f, 739.0f, 1068.6f),
                        PathNode.QuadTo(747.2f, 1068.2f, 759.2f, 1068.7f),
                        PathNode.QuadTo(771.3f, 1069.2f, 788.5f, 1069.2f),
                        PathNode.QuadTo(886.5f, 1069.2f, 964.1f, 1032.2f),
                        PathNode.QuadTo(1041.7f, 995.2f, 1085.1f, 933.8f),
                        PathNode.QuadTo(1128.6f, 872.3f, 1128.6f, 803.1f),
                        PathNode.QuadTo(1128.6f, 748.5f, 1107.3f, 700.8f),
                        PathNode.QuadTo(1102.9f, 687.6f, 1103.9f, 678.6f),
                        PathNode.QuadTo(1109.0f, 634.6f, 1103.2f, 594.6f),
                        PathNode.QuadTo(1101.5f, 580.8f, 1106.7f, 577.3f),
                        PathNode.QuadTo(1111.9f, 573.8f, 1121.0f, 583.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyallNormal!!
    }

private var _replyallNormal: ImageVector? = null

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
            group(scaleY = -1.0f, translationY = 1324.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1023.4f, 638.9f),
                        PathNode.QuadTo(1023.4f, 744.9f, 961.4f, 830.9f),
                        PathNode.QuadTo(899.4f, 916.9f, 794.4f, 965.9f),
                        PathNode.QuadTo(689.4f, 1014.9f, 566.4f, 1014.9f),
                        PathNode.QuadTo(451.4f, 1014.9f, 346.4f, 967.9f),
                        PathNode.QuadTo(241.4f, 920.9f, 175.9f, 835.4f),
                        PathNode.QuadTo(110.4f, 749.9f, 110.4f, 638.9f),
                        PathNode.QuadTo(110.4f, 559.9f, 145.4f, 492.4f),
                        PathNode.QuadTo(180.4f, 424.9f, 239.9f, 375.9f),
                        PathNode.QuadTo(299.4f, 326.9f, 371.4f, 297.9f),
                        PathNode.QuadTo(378.4f, 295.9f, 381.4f, 288.9f),
                        PathNode.QuadTo(384.4f, 281.9f, 382.4f, 274.9f),
                        PathNode.LineTo(354.4f, 180.9f),
                        PathNode.QuadTo(352.4f, 175.9f, 356.9f, 171.9f),
                        PathNode.QuadTo(361.4f, 167.9f, 368.4f, 169.9f),
                        PathNode.LineTo(667.4f, 256.9f),
                        PathNode.QuadTo(827.4f, 303.9f, 925.4f, 393.9f),
                        PathNode.QuadTo(1023.4f, 483.9f, 1023.4f, 638.9f),
                        PathNode.Close,
                        PathNode.MoveTo(402.4f, 377.9f),
                        PathNode.QuadTo(312.4f, 413.9f, 253.9f, 481.4f),
                        PathNode.QuadTo(195.4f, 548.9f, 195.4f, 638.9f),
                        PathNode.QuadTo(195.4f, 725.9f, 249.9f, 791.9f),
                        PathNode.QuadTo(304.4f, 857.9f, 389.9f, 893.4f),
                        PathNode.QuadTo(475.4f, 928.9f, 566.4f, 928.9f),
                        PathNode.QuadTo(672.4f, 928.9f, 757.4f, 888.9f),
                        PathNode.QuadTo(842.4f, 848.9f, 889.9f, 781.9f),
                        PathNode.QuadTo(937.4f, 714.9f, 937.4f, 638.9f),
                        PathNode.QuadTo(937.4f, 514.9f, 856.9f, 445.4f),
                        PathNode.QuadTo(776.4f, 375.9f, 639.4f, 336.9f),
                        PathNode.LineTo(480.4f, 290.9f),
                        PathNode.QuadTo(476.4f, 289.9f, 472.4f, 291.9f),
                        PathNode.QuadTo(468.4f, 293.9f, 467.4f, 298.9f),
                        PathNode.QuadTo(463.4f, 325.9f, 445.4f, 346.9f),
                        PathNode.QuadTo(427.4f, 367.9f, 402.4f, 377.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1127.4f, 585.9f),
                        PathNode.QuadTo(1170.4f, 630.9f, 1192.4f, 684.9f),
                        PathNode.QuadTo(1214.4f, 738.9f, 1214.4f, 808.9f),
                        PathNode.QuadTo(1214.4f, 906.9f, 1157.4f, 986.4f),
                        PathNode.QuadTo(1100.4f, 1065.9f, 1003.9f, 1111.4f),
                        PathNode.QuadTo(907.4f, 1156.9f, 793.4f, 1156.9f),
                        PathNode.QuadTo(689.4f, 1156.9f, 582.4f, 1110.9f),
                        PathNode.QuadTo(577.4f, 1108.9f, 575.9f, 1103.9f),
                        PathNode.QuadTo(574.4f, 1098.9f, 577.4f, 1094.9f),
                        PathNode.QuadTo(580.4f, 1090.9f, 586.4f, 1090.9f),
                        PathNode.QuadTo(661.4f, 1091.9f, 739.4f, 1073.9f),
                        PathNode.QuadTo(747.4f, 1071.9f, 750.4f, 1071.9f),
                        PathNode.QuadTo(757.4f, 1070.9f, 767.4f, 1070.9f),
                        PathNode.QuadTo(777.4f, 1070.9f, 793.4f, 1070.9f),
                        PathNode.QuadTo(890.4f, 1070.9f, 966.9f, 1034.4f),
                        PathNode.QuadTo(1043.4f, 997.9f, 1086.4f, 937.4f),
                        PathNode.QuadTo(1129.4f, 876.9f, 1129.4f, 808.9f),
                        PathNode.QuadTo(1129.4f, 760.9f, 1113.4f, 716.9f),
                        PathNode.QuadTo(1108.4f, 701.9f, 1109.4f, 692.9f),
                        PathNode.QuadTo(1115.4f, 642.9f, 1108.4f, 597.9f),
                        PathNode.QuadTo(1106.4f, 582.9f, 1111.9f, 578.9f),
                        PathNode.QuadTo(1117.4f, 574.9f, 1127.4f, 585.9f),
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

val MiuixIcons.Medium.ReplyAll: ImageVector
    get() {
        if (_replyallMedium != null) return _replyallMedium!!
        _replyallMedium = ImageVector.Builder(
            name = "ReplyAll.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1341.7f,
            viewportHeight = 1341.7f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1341.7f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1038.9f, 647.7f),
                        PathNode.QuadTo(1038.9f, 756.0f, 975.8f, 843.5f),
                        PathNode.QuadTo(912.6f, 931.0f, 805.8f, 980.8f),
                        PathNode.QuadTo(699.0f, 1030.7f, 574.9f, 1030.7f),
                        PathNode.QuadTo(458.1f, 1030.7f, 351.3f, 983.1f),
                        PathNode.QuadTo(244.6f, 935.5f, 178.2f, 848.3f),
                        PathNode.QuadTo(111.8f, 761.0f, 111.8f, 647.7f),
                        PathNode.QuadTo(111.8f, 566.3f, 148.3f, 497.3f),
                        PathNode.QuadTo(184.8f, 428.4f, 244.0f, 379.1f),
                        PathNode.QuadTo(303.2f, 329.8f, 372.2f, 302.5f),
                        PathNode.QuadTo(379.2f, 300.0f, 381.9f, 294.1f),
                        PathNode.QuadTo(384.6f, 288.3f, 382.6f, 280.7f),
                        PathNode.LineTo(356.4f, 191.4f),
                        PathNode.QuadTo(353.2f, 182.3f, 361.0f, 175.7f),
                        PathNode.QuadTo(368.7f, 169.0f, 379.2f, 172.2f),
                        PathNode.LineTo(679.4f, 259.2f),
                        PathNode.QuadTo(842.3f, 307.4f, 940.6f, 398.8f),
                        PathNode.QuadTo(1038.9f, 490.3f, 1038.9f, 647.7f),
                        PathNode.Close,
                        PathNode.MoveTo(413.8f, 393.1f),
                        PathNode.QuadTo(325.0f, 429.1f, 268.0f, 494.6f),
                        PathNode.QuadTo(210.9f, 560.0f, 210.9f, 647.7f),
                        PathNode.QuadTo(210.9f, 732.3f, 264.5f, 796.8f),
                        PathNode.QuadTo(318.2f, 861.4f, 402.2f, 896.0f),
                        PathNode.QuadTo(486.2f, 930.6f, 574.9f, 930.6f),
                        PathNode.QuadTo(679.1f, 930.6f, 762.3f, 891.5f),
                        PathNode.QuadTo(845.6f, 852.4f, 892.2f, 787.1f),
                        PathNode.QuadTo(938.8f, 721.9f, 938.8f, 647.7f),
                        PathNode.QuadTo(938.8f, 525.4f, 859.5f, 458.3f),
                        PathNode.QuadTo(780.2f, 391.1f, 644.3f, 352.1f),
                        PathNode.LineTo(493.6f, 308.5f),
                        PathNode.QuadTo(489.0f, 306.9f, 485.3f, 308.9f),
                        PathNode.QuadTo(481.6f, 310.9f, 480.6f, 315.9f),
                        PathNode.QuadTo(476.0f, 340.0f, 457.7f, 361.5f),
                        PathNode.QuadTo(439.4f, 383.1f, 413.8f, 393.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1148.8f, 598.8f),
                        PathNode.QuadTo(1188.9f, 642.6f, 1209.4f, 696.6f),
                        PathNode.QuadTo(1229.9f, 750.6f, 1229.9f, 817.7f),
                        PathNode.QuadTo(1229.9f, 918.6f, 1171.5f, 999.6f),
                        PathNode.QuadTo(1113.0f, 1080.5f, 1015.6f, 1126.6f),
                        PathNode.QuadTo(918.2f, 1172.7f, 806.0f, 1172.1f),
                        PathNode.QuadTo(703.2f, 1172.1f, 609.1f, 1132.6f),
                        PathNode.QuadTo(601.2f, 1129.4f, 598.5f, 1122.7f),
                        PathNode.QuadTo(595.8f, 1115.9f, 600.0f, 1109.8f),
                        PathNode.QuadTo(604.2f, 1103.8f, 612.5f, 1103.2f),
                        PathNode.QuadTo(694.6f, 1101.8f, 767.3f, 1082.1f),
                        PathNode.QuadTo(772.3f, 1080.7f, 775.3f, 1080.1f),
                        PathNode.QuadTo(783.5f, 1077.9f, 794.4f, 1076.4f),
                        PathNode.QuadTo(805.3f, 1075.0f, 821.9f, 1073.8f),
                        PathNode.QuadTo(906.5f, 1070.8f, 976.8f, 1035.2f),
                        PathNode.QuadTo(1047.2f, 999.6f, 1088.4f, 942.9f),
                        PathNode.QuadTo(1129.6f, 886.3f, 1130.8f, 823.0f),
                        PathNode.QuadTo(1131.4f, 775.0f, 1119.5f, 739.8f),
                        PathNode.QuadTo(1115.7f, 726.0f, 1117.9f, 712.8f),
                        PathNode.QuadTo(1125.0f, 659.3f, 1120.4f, 613.1f),
                        PathNode.QuadTo(1118.4f, 594.6f, 1127.4f, 589.7f),
                        PathNode.QuadTo(1136.5f, 584.8f, 1148.8f, 598.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyallMedium!!
    }

private var _replyallMedium: ImageVector? = null

val MiuixIcons.Demibold.ReplyAll: ImageVector
    get() {
        if (_replyallDemibold != null) return _replyallDemibold!!
        _replyallDemibold = ImageVector.Builder(
            name = "ReplyAll.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1353.6f,
            viewportHeight = 1353.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1353.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1049.8f, 653.8f),
                        PathNode.QuadTo(1049.8f, 763.8f, 985.8f, 852.3f),
                        PathNode.QuadTo(921.8f, 940.8f, 813.8f, 991.3f),
                        PathNode.QuadTo(705.8f, 1041.8f, 580.8f, 1041.8f),
                        PathNode.QuadTo(462.8f, 1041.8f, 354.8f, 993.8f),
                        PathNode.QuadTo(246.8f, 945.8f, 179.8f, 857.3f),
                        PathNode.QuadTo(112.8f, 768.8f, 112.8f, 653.8f),
                        PathNode.QuadTo(112.8f, 570.8f, 150.3f, 500.8f),
                        PathNode.QuadTo(187.8f, 430.8f, 246.8f, 381.3f),
                        PathNode.QuadTo(305.8f, 331.8f, 372.8f, 305.8f),
                        PathNode.QuadTo(379.8f, 302.8f, 382.3f, 297.8f),
                        PathNode.QuadTo(384.8f, 292.8f, 382.8f, 284.8f),
                        PathNode.LineTo(357.8f, 198.8f),
                        PathNode.QuadTo(353.8f, 186.8f, 363.8f, 178.3f),
                        PathNode.QuadTo(373.8f, 169.8f, 386.8f, 173.8f),
                        PathNode.LineTo(687.8f, 260.8f),
                        PathNode.QuadTo(852.8f, 309.8f, 951.3f, 402.3f),
                        PathNode.QuadTo(1049.8f, 494.8f, 1049.8f, 653.8f),
                        PathNode.Close,
                        PathNode.MoveTo(421.8f, 403.8f),
                        PathNode.QuadTo(333.8f, 439.8f, 277.8f, 503.8f),
                        PathNode.QuadTo(221.8f, 567.8f, 221.8f, 653.8f),
                        PathNode.QuadTo(221.8f, 736.8f, 274.8f, 800.3f),
                        PathNode.QuadTo(327.8f, 863.8f, 410.8f, 897.8f),
                        PathNode.QuadTo(493.8f, 931.8f, 580.8f, 931.8f),
                        PathNode.QuadTo(683.8f, 931.8f, 765.8f, 893.3f),
                        PathNode.QuadTo(847.8f, 854.8f, 893.8f, 790.8f),
                        PathNode.QuadTo(939.8f, 726.8f, 939.8f, 653.8f),
                        PathNode.QuadTo(939.8f, 532.8f, 861.3f, 467.3f),
                        PathNode.QuadTo(782.8f, 401.8f, 647.8f, 362.8f),
                        PathNode.LineTo(502.8f, 320.8f),
                        PathNode.QuadTo(497.8f, 318.8f, 494.3f, 320.8f),
                        PathNode.QuadTo(490.8f, 322.8f, 489.8f, 327.8f),
                        PathNode.QuadTo(484.8f, 349.8f, 466.3f, 371.8f),
                        PathNode.QuadTo(447.8f, 393.8f, 421.8f, 403.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1163.8f, 607.8f),
                        PathNode.QuadTo(1201.8f, 650.8f, 1221.3f, 704.8f),
                        PathNode.QuadTo(1240.8f, 758.8f, 1240.8f, 823.8f),
                        PathNode.QuadTo(1240.8f, 926.8f, 1181.3f, 1008.8f),
                        PathNode.QuadTo(1121.8f, 1090.8f, 1023.8f, 1137.3f),
                        PathNode.QuadTo(925.8f, 1183.8f, 814.8f, 1182.8f),
                        PathNode.QuadTo(712.8f, 1182.8f, 627.8f, 1147.8f),
                        PathNode.QuadTo(617.8f, 1143.8f, 614.3f, 1135.8f),
                        PathNode.QuadTo(610.8f, 1127.8f, 615.8f, 1120.3f),
                        PathNode.QuadTo(620.8f, 1112.8f, 630.8f, 1111.8f),
                        PathNode.QuadTo(717.8f, 1108.8f, 786.8f, 1087.8f),
                        PathNode.QuadTo(789.8f, 1086.8f, 792.8f, 1085.8f),
                        PathNode.QuadTo(801.8f, 1082.8f, 813.3f, 1080.3f),
                        PathNode.QuadTo(824.8f, 1077.8f, 841.8f, 1075.8f),
                        PathNode.QuadTo(917.8f, 1070.8f, 983.8f, 1035.8f),
                        PathNode.QuadTo(1049.8f, 1000.8f, 1089.8f, 946.8f),
                        PathNode.QuadTo(1129.8f, 892.8f, 1131.8f, 832.8f),
                        PathNode.QuadTo(1132.8f, 784.8f, 1123.8f, 755.8f),
                        PathNode.QuadTo(1120.8f, 742.8f, 1123.8f, 726.8f),
                        PathNode.QuadTo(1131.8f, 670.8f, 1128.8f, 623.8f),
                        PathNode.QuadTo(1126.8f, 602.8f, 1138.3f, 597.3f),
                        PathNode.QuadTo(1149.8f, 591.8f, 1163.8f, 607.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyallDemibold!!
    }

private var _replyallDemibold: ImageVector? = null
