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

val MiuixIcons.Replace: ImageVector
    get() = MiuixIcons.Regular.Replace

val MiuixIcons.Light.Replace: ImageVector
    get() {
        if (_replaceLight != null) return _replaceLight!!
        _replaceLight = ImageVector.Builder(
            name = "Replace.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1264.235294117647f,
            viewportHeight = 1264.235294117647f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -33.05901960784311f, translationY = 1007.3529411764706f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(262.0f, 725.0f),
                PathNode.LineTo(389.0f, 853.0f),
                PathNode.QuadTo(396.0f, 861.0f, 396.5f, 868.0f),
                PathNode.QuadTo(397.0f, 875.0f, 389.0f, 883.0f),
                PathNode.LineTo(377.0f, 895.0f),
                PathNode.QuadTo(370.0f, 902.0f, 363.0f, 902.0f),
                PathNode.QuadTo(356.0f, 902.0f, 350.0f, 896.0f),
                PathNode.LineTo(175.0f, 722.0f),
                PathNode.QuadTo(163.0f, 710.0f, 163.0f, 697.0f),
                PathNode.QuadTo(163.0f, 684.0f, 174.0f, 672.0f),
                PathNode.LineTo(351.0f, 494.0f),
                PathNode.QuadTo(357.0f, 488.0f, 364.5f, 488.5f),
                PathNode.QuadTo(372.0f, 489.0f, 378.0f, 496.0f),
                PathNode.LineTo(391.0f, 510.0f),
                PathNode.QuadTo(398.0f, 517.0f, 397.5f, 524.0f),
                PathNode.QuadTo(397.0f, 531.0f, 389.0f, 539.0f),
                PathNode.LineTo(262.0f, 667.0f),
                PathNode.LineTo(813.0f, 669.0f),
                PathNode.QuadTo(887.0f, 669.0f, 951.0f, 635.0f),
                PathNode.QuadTo(1015.0f, 601.0f, 1056.5f, 539.5f),
                PathNode.QuadTo(1098.0f, 478.0f, 1107.0f, 400.0f),
                PathNode.QuadTo(1108.0f, 387.0f, 1113.0f, 382.0f),
                PathNode.QuadTo(1118.0f, 377.0f, 1129.0f, 377.0f),
                PathNode.HorizontalTo(1146.0f),
                PathNode.QuadTo(1157.0f, 377.0f, 1162.0f, 382.5f),
                PathNode.QuadTo(1167.0f, 388.0f, 1166.0f, 398.0f),
                PathNode.QuadTo(1157.0f, 494.0f, 1107.5f, 569.5f),
                PathNode.QuadTo(1058.0f, 645.0f, 980.5f, 686.0f),
                PathNode.QuadTo(903.0f, 727.0f, 813.0f, 727.0f),
                PathNode.Close,
                PathNode.MoveTo(223.0f, 346.0f),
                PathNode.QuadTo(221.0f, 362.0f, 216.0f, 367.5f),
                PathNode.QuadTo(211.0f, 373.0f, 198.0f, 373.0f),
                PathNode.HorizontalTo(182.0f),
                PathNode.QuadTo(172.0f, 373.0f, 167.0f, 366.5f),
                PathNode.QuadTo(162.0f, 360.0f, 163.0f, 350.0f),
                PathNode.QuadTo(173.0f, 255.0f, 222.5f, 180.5f),
                PathNode.QuadTo(272.0f, 106.0f, 349.0f, 65.0f),
                PathNode.QuadTo(426.0f, 24.0f, 515.0f, 24.0f),
                PathNode.LineTo(1069.0f, 26.0f),
                PathNode.LineTo(940.0f, -101.0f),
                PathNode.QuadTo(932.0f, -110.0f, 931.5f, -117.0f),
                PathNode.QuadTo(931.0f, -124.0f, 939.0f, -131.0f),
                PathNode.LineTo(952.0f, -144.0f),
                PathNode.QuadTo(959.0f, -152.0f, 966.5f, -151.5f),
                PathNode.QuadTo(974.0f, -151.0f, 982.0f, -144.0f),
                PathNode.LineTo(1156.0f, 31.0f),
                PathNode.QuadTo(1168.0f, 43.0f, 1167.5f, 56.0f),
                PathNode.QuadTo(1167.0f, 69.0f, 1156.0f, 80.0f),
                PathNode.LineTo(980.0f, 255.0f),
                PathNode.QuadTo(972.0f, 262.0f, 965.0f, 261.5f),
                PathNode.QuadTo(958.0f, 261.0f, 951.0f, 254.0f),
                PathNode.LineTo(942.0f, 245.0f),
                PathNode.QuadTo(933.0f, 236.0f, 932.5f, 229.0f),
                PathNode.QuadTo(932.0f, 222.0f, 940.0f, 213.0f),
                PathNode.LineTo(1068.0f, 84.0f),
                PathNode.LineTo(515.0f, 82.0f),
                PathNode.QuadTo(442.0f, 82.0f, 379.0f, 115.5f),
                PathNode.QuadTo(316.0f, 149.0f, 274.5f, 209.5f),
                PathNode.QuadTo(233.0f, 270.0f, 223.0f, 346.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _replaceLight!!
    }

private var _replaceLight: ImageVector? = null

val MiuixIcons.Regular.Replace: ImageVector
    get() {
        if (_replaceRegular != null) return _replaceRegular!!
        _replaceRegular = ImageVector.Builder(
            name = "Replace.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1288.8555789473685f,
            viewportHeight = 1288.8555789473685f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -19.901622291021653f, translationY = 1018.930947368421f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(294.0f, 733.0f),
                PathNode.LineTo(398.0f, 838.0f),
                PathNode.QuadTo(410.0f, 851.0f, 410.5f, 862.0f),
                PathNode.QuadTo(411.0f, 873.0f, 399.0f, 885.0f),
                PathNode.LineTo(384.0f, 900.0f),
                PathNode.QuadTo(373.0f, 911.0f, 362.5f, 911.5f),
                PathNode.QuadTo(352.0f, 912.0f, 343.0f, 903.0f),
                PathNode.LineTo(165.0f, 726.0f),
                PathNode.QuadTo(149.0f, 710.0f, 149.0f, 691.0f),
                PathNode.QuadTo(149.0f, 672.0f, 164.0f, 657.0f),
                PathNode.LineTo(343.0f, 478.0f),
                PathNode.QuadTo(352.0f, 469.0f, 363.0f, 469.5f),
                PathNode.QuadTo(374.0f, 470.0f, 384.0f, 481.0f),
                PathNode.LineTo(401.0f, 499.0f),
                PathNode.QuadTo(412.0f, 510.0f, 410.5f, 520.5f),
                PathNode.QuadTo(409.0f, 531.0f, 397.0f, 543.0f),
                PathNode.LineTo(294.0f, 648.0f),
                PathNode.LineTo(813.0f, 650.0f),
                PathNode.QuadTo(883.0f, 650.0f, 944.0f, 618.0f),
                PathNode.QuadTo(1005.0f, 586.0f, 1044.5f, 529.5f),
                PathNode.QuadTo(1084.0f, 473.0f, 1093.0f, 403.0f),
                PathNode.QuadTo(1096.0f, 382.0f, 1103.0f, 375.0f),
                PathNode.QuadTo(1110.0f, 368.0f, 1126.0f, 368.0f),
                PathNode.HorizontalTo(1149.0f),
                PathNode.QuadTo(1166.0f, 368.0f, 1173.5f, 376.0f),
                PathNode.QuadTo(1181.0f, 384.0f, 1179.0f, 398.0f),
                PathNode.QuadTo(1171.0f, 496.0f, 1119.0f, 573.0f),
                PathNode.QuadTo(1067.0f, 650.0f, 986.0f, 692.5f),
                PathNode.QuadTo(905.0f, 735.0f, 813.0f, 735.0f),
                PathNode.Close,
                PathNode.MoveTo(236.0f, 338.0f),
                PathNode.QuadTo(233.0f, 364.0f, 226.5f, 372.5f),
                PathNode.QuadTo(220.0f, 381.0f, 198.0f, 381.0f),
                PathNode.HorizontalTo(179.0f),
                PathNode.QuadTo(163.0f, 381.0f, 155.0f, 371.5f),
                PathNode.QuadTo(147.0f, 362.0f, 149.0f, 346.0f),
                PathNode.QuadTo(159.0f, 252.0f, 210.5f, 176.0f),
                PathNode.QuadTo(262.0f, 100.0f, 342.0f, 57.0f),
                PathNode.QuadTo(422.0f, 14.0f, 515.0f, 14.0f),
                PathNode.LineTo(1035.0f, 16.0f),
                PathNode.LineTo(931.0f, -88.0f),
                PathNode.QuadTo(918.0f, -101.0f, 917.5f, -112.0f),
                PathNode.QuadTo(917.0f, -123.0f, 929.0f, -135.0f),
                PathNode.LineTo(945.0f, -151.0f),
                PathNode.QuadTo(957.0f, -163.0f, 967.5f, -162.5f),
                PathNode.QuadTo(978.0f, -162.0f, 990.0f, -150.0f),
                PathNode.LineTo(1165.0f, 25.0f),
                PathNode.QuadTo(1181.0f, 41.0f, 1180.0f, 60.0f),
                PathNode.QuadTo(1179.0f, 79.0f, 1165.0f, 93.0f),
                PathNode.LineTo(988.0f, 269.0f),
                PathNode.QuadTo(977.0f, 280.0f, 966.5f, 279.5f),
                PathNode.QuadTo(956.0f, 279.0f, 944.0f, 267.0f),
                PathNode.LineTo(934.0f, 257.0f),
                PathNode.QuadTo(919.0f, 242.0f, 917.5f, 231.5f),
                PathNode.QuadTo(916.0f, 221.0f, 928.0f, 209.0f),
                PathNode.LineTo(1035.0f, 101.0f),
                PathNode.LineTo(515.0f, 99.0f),
                PathNode.QuadTo(445.0f, 99.0f, 385.0f, 130.5f),
                PathNode.QuadTo(325.0f, 162.0f, 285.5f, 216.5f),
                PathNode.QuadTo(246.0f, 271.0f, 236.0f, 338.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _replaceRegular!!
    }

private var _replaceRegular: ImageVector? = null

val MiuixIcons.Heavy.Replace: ImageVector
    get() {
        if (_replaceHeavy != null) return _replaceHeavy!!
        _replaceHeavy = ImageVector.Builder(
            name = "Replace.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1339.238871473354f,
            viewportHeight = 1339.238871473354f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 5.2346394984325f, translationY = 1045.1204806687565f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(328.0f, 754.0f),
                PathNode.LineTo(409.0f, 832.0f),
                PathNode.QuadTo(426.0f, 849.0f, 426.5f, 866.5f),
                PathNode.QuadTo(427.0f, 884.0f, 410.0f, 902.0f),
                PathNode.LineTo(395.0f, 917.0f),
                PathNode.QuadTo(379.0f, 933.0f, 362.5f, 933.5f),
                PathNode.QuadTo(346.0f, 934.0f, 332.0f, 920.0f),
                PathNode.LineTo(154.0f, 743.0f),
                PathNode.QuadTo(133.0f, 722.0f, 133.0f, 696.5f),
                PathNode.QuadTo(133.0f, 671.0f, 153.0f, 651.0f),
                PathNode.LineTo(332.0f, 472.0f),
                PathNode.QuadTo(345.0f, 458.0f, 363.0f, 458.5f),
                PathNode.QuadTo(381.0f, 459.0f, 396.0f, 475.0f),
                PathNode.LineTo(412.0f, 493.0f),
                PathNode.QuadTo(428.0f, 508.0f, 427.0f, 526.0f),
                PathNode.QuadTo(426.0f, 544.0f, 408.0f, 560.0f),
                PathNode.LineTo(328.0f, 637.0f),
                PathNode.LineTo(818.0f, 639.0f),
                PathNode.QuadTo(884.0f, 639.0f, 940.0f, 611.5f),
                PathNode.QuadTo(996.0f, 584.0f, 1032.5f, 534.5f),
                PathNode.QuadTo(1069.0f, 485.0f, 1077.0f, 421.0f),
                PathNode.QuadTo(1081.0f, 394.0f, 1092.5f, 383.0f),
                PathNode.QuadTo(1104.0f, 372.0f, 1126.0f, 372.0f),
                PathNode.HorizontalTo(1149.0f),
                PathNode.QuadTo(1173.0f, 372.0f, 1185.0f, 385.0f),
                PathNode.QuadTo(1197.0f, 398.0f, 1195.0f, 420.0f),
                PathNode.QuadTo(1186.0f, 519.0f, 1133.0f, 595.5f),
                PathNode.QuadTo(1080.0f, 672.0f, 997.0f, 714.0f),
                PathNode.QuadTo(914.0f, 756.0f, 818.0f, 756.0f),
                PathNode.Close,
                PathNode.MoveTo(253.0f, 322.0f),
                PathNode.QuadTo(248.0f, 354.0f, 237.0f, 366.5f),
                PathNode.QuadTo(226.0f, 379.0f, 198.0f, 379.0f),
                PathNode.HorizontalTo(179.0f),
                PathNode.QuadTo(156.0f, 379.0f, 143.5f, 364.5f),
                PathNode.QuadTo(131.0f, 350.0f, 133.0f, 327.0f),
                PathNode.QuadTo(143.0f, 231.0f, 195.5f, 154.5f),
                PathNode.QuadTo(248.0f, 78.0f, 330.5f, 36.0f),
                PathNode.QuadTo(413.0f, -6.0f, 510.0f, -6.0f),
                PathNode.LineTo(999.0f, -4.0f),
                PathNode.LineTo(920.0f, -81.0f),
                PathNode.QuadTo(901.0f, -98.0f, 900.5f, -115.5f),
                PathNode.QuadTo(900.0f, -133.0f, 918.0f, -151.0f),
                PathNode.LineTo(934.0f, -167.0f),
                PathNode.QuadTo(950.0f, -183.0f, 967.5f, -182.5f),
                PathNode.QuadTo(985.0f, -182.0f, 1001.0f, -166.0f),
                PathNode.LineTo(1176.0f, 9.0f),
                PathNode.QuadTo(1197.0f, 30.0f, 1196.0f, 56.0f),
                PathNode.QuadTo(1195.0f, 82.0f, 1176.0f, 100.0f),
                PathNode.LineTo(999.0f, 276.0f),
                PathNode.QuadTo(983.0f, 291.0f, 966.0f, 290.5f),
                PathNode.QuadTo(949.0f, 290.0f, 933.0f, 274.0f),
                PathNode.LineTo(923.0f, 264.0f),
                PathNode.QuadTo(903.0f, 245.0f, 901.5f, 227.5f),
                PathNode.QuadTo(900.0f, 210.0f, 917.0f, 194.0f),
                PathNode.LineTo(999.0f, 113.0f),
                PathNode.LineTo(510.0f, 111.0f),
                PathNode.QuadTo(445.0f, 111.0f, 389.5f, 138.0f),
                PathNode.QuadTo(334.0f, 165.0f, 298.0f, 213.0f),
                PathNode.QuadTo(262.0f, 261.0f, 253.0f, 322.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _replaceHeavy!!
    }

private var _replaceHeavy: ImageVector? = null
