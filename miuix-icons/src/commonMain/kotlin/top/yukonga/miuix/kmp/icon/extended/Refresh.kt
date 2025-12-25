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

val MiuixIcons.Refresh: ImageVector
    get() = MiuixIcons.Regular.Refresh

val MiuixIcons.Light.Refresh: ImageVector
    get() {
        if (_refreshLight != null) return _refreshLight!!
        _refreshLight = ImageVector.Builder(
            name = "Refresh.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1163.9239980207817f,
            viewportHeight = 1163.9239980207817f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -82.5380009896092f, translationY = 941.6745175655616f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1051.0f, -18.0f),
                PathNode.LineTo(1028.0f, 11.0f),
                PathNode.QuadTo(1022.0f, 17.0f, 1024.0f, 25.0f),
                PathNode.QuadTo(1026.0f, 33.0f, 1038.0f, 49.0f),
                PathNode.QuadTo(1101.0f, 133.0f, 1129.0f, 228.0f),
                PathNode.QuadTo(1157.0f, 323.0f, 1146.0f, 419.5f),
                PathNode.QuadTo(1135.0f, 516.0f, 1085.0f, 602.0f),
                PathNode.QuadTo(1030.0f, 696.0f, 943.5f, 757.0f),
                PathNode.QuadTo(857.0f, 818.0f, 751.0f, 837.0f),
                PathNode.QuadTo(645.0f, 856.0f, 535.0f, 828.0f),
                PathNode.QuadTo(526.0f, 826.0f, 522.5f, 818.0f),
                PathNode.QuadTo(519.0f, 810.0f, 523.0f, 803.0f),
                PathNode.LineTo(535.0f, 783.0f),
                PathNode.QuadTo(539.0f, 775.0f, 544.0f, 773.0f),
                PathNode.QuadTo(549.0f, 771.0f, 556.0f, 773.0f),
                PathNode.QuadTo(652.0f, 795.0f, 744.0f, 777.5f),
                PathNode.QuadTo(836.0f, 760.0f, 911.5f, 707.0f),
                PathNode.QuadTo(987.0f, 654.0f, 1034.0f, 573.0f),
                PathNode.QuadTo(1078.0f, 497.0f, 1088.5f, 413.0f),
                PathNode.QuadTo(1099.0f, 329.0f, 1074.5f, 245.5f),
                PathNode.QuadTo(1050.0f, 162.0f, 993.0f, 87.0f),
                PathNode.QuadTo(986.0f, 79.0f, 981.5f, 78.5f),
                PathNode.QuadTo(977.0f, 78.0f, 972.0f, 84.0f),
                PathNode.LineTo(952.0f, 109.0f),
                PathNode.QuadTo(948.0f, 114.0f, 942.0f, 115.5f),
                PathNode.QuadTo(936.0f, 117.0f, 930.5f, 113.0f),
                PathNode.QuadTo(925.0f, 109.0f, 923.0f, 100.0f),
                PathNode.LineTo(889.0f, -27.0f),
                PathNode.QuadTo(887.0f, -36.0f, 891.5f, -42.0f),
                PathNode.QuadTo(896.0f, -48.0f, 905.0f, -48.0f),
                PathNode.LineTo(1040.0f, -44.0f),
                PathNode.QuadTo(1047.0f, -44.0f, 1051.0f, -39.5f),
                PathNode.QuadTo(1055.0f, -35.0f, 1055.0f, -29.0f),
                PathNode.QuadTo(1055.0f, -23.0f, 1051.0f, -18.0f),
                PathNode.Close,
                PathNode.MoveTo(791.0f, -88.0f),
                PathNode.LineTo(780.0f, -67.0f),
                PathNode.QuadTo(773.0f, -54.0f, 757.0f, -58.0f),
                PathNode.QuadTo(663.0f, -76.0f, 574.5f, -56.5f),
                PathNode.QuadTo(486.0f, -37.0f, 414.0f, 15.0f),
                PathNode.QuadTo(342.0f, 67.0f, 296.0f, 146.0f),
                PathNode.QuadTo(253.0f, 221.0f, 242.0f, 305.0f),
                PathNode.QuadTo(231.0f, 389.0f, 254.0f, 471.5f),
                PathNode.QuadTo(277.0f, 554.0f, 334.0f, 625.0f),
                PathNode.QuadTo(341.0f, 633.0f, 347.5f, 633.5f),
                PathNode.QuadTo(354.0f, 634.0f, 359.0f, 628.0f),
                PathNode.LineTo(381.0f, 602.0f),
                PathNode.QuadTo(388.0f, 594.0f, 396.5f, 596.0f),
                PathNode.QuadTo(405.0f, 598.0f, 408.0f, 608.0f),
                PathNode.LineTo(443.0f, 735.0f),
                PathNode.QuadTo(445.0f, 747.0f, 441.0f, 753.5f),
                PathNode.QuadTo(437.0f, 760.0f, 427.0f, 760.0f),
                PathNode.LineTo(291.0f, 756.0f),
                PathNode.QuadTo(285.0f, 756.0f, 281.0f, 751.5f),
                PathNode.QuadTo(277.0f, 747.0f, 277.0f, 741.5f),
                PathNode.QuadTo(277.0f, 736.0f, 280.0f, 732.0f),
                PathNode.LineTo(299.0f, 706.0f),
                PathNode.QuadTo(305.0f, 697.0f, 304.0f, 689.5f),
                PathNode.QuadTo(303.0f, 682.0f, 293.0f, 670.0f),
                PathNode.QuadTo(228.0f, 589.0f, 200.0f, 493.5f),
                PathNode.QuadTo(172.0f, 398.0f, 183.0f, 301.0f),
                PathNode.QuadTo(194.0f, 204.0f, 245.0f, 117.0f),
                PathNode.QuadTo(298.0f, 26.0f, 381.5f, -34.0f),
                PathNode.QuadTo(465.0f, -94.0f, 567.5f, -115.0f),
                PathNode.QuadTo(670.0f, -136.0f, 778.0f, -114.0f),
                PathNode.QuadTo(789.0f, -112.0f, 792.0f, -104.0f),
                PathNode.QuadTo(795.0f, -96.0f, 791.0f, -88.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _refreshLight!!
    }

private var _refreshLight: ImageVector? = null

val MiuixIcons.Regular.Refresh: ImageVector
    get() {
        if (_refreshRegular != null) return _refreshRegular!!
        _refreshRegular = ImageVector.Builder(
            name = "Refresh.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1197.4260869565217f,
            viewportHeight = 1197.4260869565217f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -65.8095556331541f, translationY = 958.1188405797102f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1077.0f, -8.0f),
                PathNode.LineTo(1056.0f, 19.0f),
                PathNode.QuadTo(1050.0f, 27.0f, 1050.0f, 34.5f),
                PathNode.QuadTo(1050.0f, 42.0f, 1057.0f, 51.0f),
                PathNode.QuadTo(1118.0f, 132.0f, 1144.5f, 228.0f),
                PathNode.QuadTo(1171.0f, 324.0f, 1159.5f, 422.5f),
                PathNode.QuadTo(1148.0f, 521.0f, 1096.0f, 609.0f),
                PathNode.QuadTo(1040.0f, 705.0f, 951.0f, 767.5f),
                PathNode.QuadTo(862.0f, 830.0f, 754.5f, 850.0f),
                PathNode.QuadTo(647.0f, 870.0f, 537.0f, 842.0f),
                PathNode.QuadTo(523.0f, 838.0f, 518.0f, 826.5f),
                PathNode.QuadTo(513.0f, 815.0f, 519.0f, 804.0f),
                PathNode.LineTo(535.0f, 777.0f),
                PathNode.QuadTo(541.0f, 765.0f, 548.0f, 762.5f),
                PathNode.QuadTo(555.0f, 760.0f, 567.0f, 762.0f),
                PathNode.QuadTo(657.0f, 782.0f, 744.5f, 764.5f),
                PathNode.QuadTo(832.0f, 747.0f, 904.5f, 696.0f),
                PathNode.QuadTo(977.0f, 645.0f, 1022.0f, 566.0f),
                PathNode.QuadTo(1064.0f, 494.0f, 1074.5f, 413.5f),
                PathNode.QuadTo(1085.0f, 333.0f, 1064.5f, 255.5f),
                PathNode.QuadTo(1044.0f, 178.0f, 997.0f, 114.0f),
                PathNode.QuadTo(994.0f, 110.0f, 989.5f, 110.0f),
                PathNode.QuadTo(985.0f, 110.0f, 981.0f, 115.0f),
                PathNode.LineTo(965.0f, 135.0f),
                PathNode.QuadTo(959.0f, 142.0f, 950.0f, 144.0f),
                PathNode.QuadTo(941.0f, 146.0f, 933.0f, 141.0f),
                PathNode.QuadTo(925.0f, 136.0f, 921.0f, 122.0f),
                PathNode.LineTo(883.0f, -21.0f),
                PathNode.QuadTo(880.0f, -33.0f, 886.5f, -42.0f),
                PathNode.QuadTo(893.0f, -51.0f, 906.0f, -51.0f),
                PathNode.LineTo(1060.0f, -47.0f),
                PathNode.QuadTo(1071.0f, -47.0f, 1077.0f, -40.5f),
                PathNode.QuadTo(1083.0f, -34.0f, 1083.0f, -24.5f),
                PathNode.QuadTo(1083.0f, -15.0f, 1077.0f, -8.0f),
                PathNode.Close,
                PathNode.MoveTo(796.0f, -87.0f),
                PathNode.LineTo(780.0f, -59.0f),
                PathNode.QuadTo(772.0f, -41.0f, 745.0f, -46.0f),
                PathNode.QuadTo(658.0f, -62.0f, 574.0f, -43.0f),
                PathNode.QuadTo(490.0f, -24.0f, 420.5f, 26.5f),
                PathNode.QuadTo(351.0f, 77.0f, 307.0f, 153.0f),
                PathNode.QuadTo(266.0f, 224.0f, 255.0f, 303.5f),
                PathNode.QuadTo(244.0f, 383.0f, 263.5f, 460.0f),
                PathNode.QuadTo(283.0f, 537.0f, 329.0f, 601.0f),
                PathNode.QuadTo(333.0f, 606.0f, 339.5f, 606.0f),
                PathNode.QuadTo(346.0f, 606.0f, 350.0f, 601.0f),
                PathNode.LineTo(369.0f, 578.0f),
                PathNode.QuadTo(379.0f, 567.0f, 392.0f, 570.0f),
                PathNode.QuadTo(405.0f, 573.0f, 409.0f, 587.0f),
                PathNode.LineTo(448.0f, 729.0f),
                PathNode.QuadTo(453.0f, 746.0f, 447.0f, 756.5f),
                PathNode.QuadTo(441.0f, 767.0f, 426.0f, 766.0f),
                PathNode.LineTo(271.0f, 762.0f),
                PathNode.QuadTo(261.0f, 762.0f, 255.5f, 755.5f),
                PathNode.QuadTo(250.0f, 749.0f, 249.5f, 740.5f),
                PathNode.QuadTo(249.0f, 732.0f, 253.0f, 726.0f),
                PathNode.LineTo(271.0f, 701.0f),
                PathNode.QuadTo(278.0f, 692.0f, 278.5f, 684.5f),
                PathNode.QuadTo(279.0f, 677.0f, 274.0f, 670.0f),
                PathNode.QuadTo(212.0f, 590.0f, 185.0f, 494.0f),
                PathNode.QuadTo(158.0f, 398.0f, 169.5f, 298.5f),
                PathNode.QuadTo(181.0f, 199.0f, 233.0f, 110.0f),
                PathNode.QuadTo(287.0f, 16.0f, 374.0f, -45.5f),
                PathNode.QuadTo(461.0f, -107.0f, 565.5f, -129.0f),
                PathNode.QuadTo(670.0f, -151.0f, 776.0f, -127.0f),
                PathNode.QuadTo(792.0f, -123.0f, 797.5f, -110.5f),
                PathNode.QuadTo(803.0f, -98.0f, 796.0f, -87.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _refreshRegular!!
    }

private var _refreshRegular: ImageVector? = null

val MiuixIcons.Heavy.Refresh: ImageVector
    get() {
        if (_refreshHeavy != null) return _refreshHeavy!!
        _refreshHeavy = ImageVector.Builder(
            name = "Refresh.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1229.8924412665986f,
            viewportHeight = 1229.8924412665986f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -50.10931435374255f, translationY = 974.3786772216547f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1096.0f, 11.0f),
                PathNode.LineTo(1079.0f, 34.0f),
                PathNode.QuadTo(1074.0f, 39.0f, 1074.0f, 45.0f),
                PathNode.QuadTo(1074.0f, 51.0f, 1079.0f, 58.0f),
                PathNode.QuadTo(1135.0f, 137.0f, 1160.0f, 232.0f),
                PathNode.QuadTo(1185.0f, 327.0f, 1173.0f, 426.5f),
                PathNode.QuadTo(1161.0f, 526.0f, 1108.0f, 616.0f),
                PathNode.QuadTo(1051.0f, 714.0f, 963.5f, 777.0f),
                PathNode.QuadTo(876.0f, 840.0f, 772.0f, 861.5f),
                PathNode.QuadTo(668.0f, 883.0f, 562.0f, 860.0f),
                PathNode.QuadTo(541.0f, 856.0f, 533.5f, 840.0f),
                PathNode.QuadTo(526.0f, 824.0f, 535.0f, 807.0f),
                PathNode.LineTo(551.0f, 775.0f),
                PathNode.QuadTo(559.0f, 759.0f, 570.0f, 755.0f),
                PathNode.QuadTo(581.0f, 751.0f, 598.0f, 753.0f),
                PathNode.QuadTo(674.0f, 768.0f, 753.0f, 750.0f),
                PathNode.QuadTo(832.0f, 732.0f, 900.0f, 683.0f),
                PathNode.QuadTo(968.0f, 634.0f, 1011.0f, 559.0f),
                PathNode.QuadTo(1051.0f, 490.0f, 1061.0f, 416.0f),
                PathNode.QuadTo(1071.0f, 342.0f, 1055.0f, 273.0f),
                PathNode.QuadTo(1039.0f, 204.0f, 1003.0f, 149.0f),
                PathNode.QuadTo(1000.0f, 144.0f, 996.5f, 144.0f),
                PathNode.QuadTo(993.0f, 144.0f, 989.0f, 148.0f),
                PathNode.LineTo(977.0f, 162.0f),
                PathNode.QuadTo(969.0f, 170.0f, 957.0f, 171.0f),
                PathNode.QuadTo(945.0f, 172.0f, 933.5f, 163.0f),
                PathNode.QuadTo(922.0f, 154.0f, 917.0f, 136.0f),
                PathNode.LineTo(878.0f, -7.0f),
                PathNode.QuadTo(873.0f, -25.0f, 884.0f, -40.0f),
                PathNode.QuadTo(895.0f, -55.0f, 915.0f, -54.0f),
                PathNode.LineTo(1069.0f, -50.0f),
                PathNode.QuadTo(1086.0f, -50.0f, 1095.5f, -39.5f),
                PathNode.QuadTo(1105.0f, -29.0f, 1105.0f, -14.5f),
                PathNode.QuadTo(1105.0f, 0.0f, 1096.0f, 11.0f),
                PathNode.Close,
                PathNode.MoveTo(782.0f, -87.0f),
                PathNode.LineTo(765.0f, -54.0f),
                PathNode.QuadTo(754.0f, -28.0f, 715.0f, -34.0f),
                PathNode.QuadTo(642.0f, -46.0f, 567.0f, -27.5f),
                PathNode.QuadTo(492.0f, -9.0f, 426.5f, 39.0f),
                PathNode.QuadTo(361.0f, 87.0f, 319.0f, 160.0f),
                PathNode.QuadTo(280.0f, 228.0f, 269.5f, 301.0f),
                PathNode.QuadTo(259.0f, 374.0f, 274.0f, 442.0f),
                PathNode.QuadTo(289.0f, 510.0f, 324.0f, 566.0f),
                PathNode.QuadTo(328.0f, 572.0f, 333.0f, 573.0f),
                PathNode.QuadTo(338.0f, 574.0f, 342.0f, 569.0f),
                PathNode.LineTo(356.0f, 553.0f),
                PathNode.QuadTo(370.0f, 538.0f, 390.0f, 543.0f),
                PathNode.QuadTo(410.0f, 548.0f, 416.0f, 570.0f),
                PathNode.LineTo(455.0f, 712.0f),
                PathNode.QuadTo(461.0f, 736.0f, 451.0f, 752.0f),
                PathNode.QuadTo(441.0f, 768.0f, 419.0f, 767.0f),
                PathNode.LineTo(264.0f, 763.0f),
                PathNode.QuadTo(249.0f, 762.0f, 239.5f, 752.5f),
                PathNode.QuadTo(230.0f, 743.0f, 229.0f, 729.5f),
                PathNode.QuadTo(228.0f, 716.0f, 235.0f, 705.0f),
                PathNode.LineTo(249.0f, 686.0f),
                PathNode.QuadTo(255.0f, 678.0f, 255.0f, 671.0f),
                PathNode.QuadTo(255.0f, 664.0f, 248.0f, 654.0f),
                PathNode.QuadTo(193.0f, 580.0f, 169.0f, 486.5f),
                PathNode.QuadTo(145.0f, 393.0f, 157.5f, 293.0f),
                PathNode.QuadTo(170.0f, 193.0f, 222.0f, 103.0f),
                PathNode.QuadTo(277.0f, 8.0f, 364.5f, -55.0f),
                PathNode.QuadTo(452.0f, -118.0f, 553.0f, -141.0f),
                PathNode.QuadTo(654.0f, -164.0f, 751.0f, -143.0f),
                PathNode.QuadTo(774.0f, -138.0f, 782.5f, -121.5f),
                PathNode.QuadTo(791.0f, -105.0f, 782.0f, -87.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _refreshHeavy!!
    }

private var _refreshHeavy: ImageVector? = null
