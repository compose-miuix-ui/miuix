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

val MiuixIcons.SearchDevice: ImageVector
    get() = MiuixIcons.Regular.SearchDevice

val MiuixIcons.Light.SearchDevice: ImageVector
    get() {
        if (_searchdeviceLight != null) return _searchdeviceLight!!
        _searchdeviceLight = ImageVector.Builder(
            name = "SearchDevice.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1193.0717647058823f,
            viewportHeight = 1193.0717647058823f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -68.32735294117651f, translationY = 971.1608823529411f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1017.0f, 23.0f),
                PathNode.QuadTo(1090.0f, 97.0f, 1128.0f, 194.5f),
                PathNode.QuadTo(1166.0f, 292.0f, 1161.5f, 398.0f),
                PathNode.QuadTo(1157.0f, 504.0f, 1107.0f, 603.0f),
                PathNode.QuadTo(1103.0f, 611.0f, 1094.5f, 612.0f),
                PathNode.QuadTo(1086.0f, 613.0f, 1080.0f, 607.0f),
                PathNode.LineTo(1064.0f, 592.0f),
                PathNode.QuadTo(1058.0f, 586.0f, 1056.5f, 580.5f),
                PathNode.QuadTo(1055.0f, 575.0f, 1058.0f, 569.0f),
                PathNode.QuadTo(1100.0f, 481.0f, 1103.0f, 389.0f),
                PathNode.QuadTo(1106.0f, 297.0f, 1072.5f, 213.5f),
                PathNode.QuadTo(1039.0f, 130.0f, 974.0f, 66.0f),
                PathNode.QuadTo(890.0f, -19.0f, 777.0f, -48.5f),
                PathNode.QuadTo(664.0f, -78.0f, 551.5f, -48.5f),
                PathNode.QuadTo(439.0f, -19.0f, 355.0f, 66.0f),
                PathNode.QuadTo(270.0f, 150.0f, 240.5f, 263.0f),
                PathNode.QuadTo(211.0f, 376.0f, 240.5f, 488.5f),
                PathNode.QuadTo(270.0f, 601.0f, 355.0f, 685.0f),
                PathNode.QuadTo(436.0f, 766.0f, 543.5f, 796.5f),
                PathNode.QuadTo(651.0f, 827.0f, 759.5f, 803.0f),
                PathNode.QuadTo(868.0f, 779.0f, 953.0f, 704.0f),
                PathNode.LineTo(701.0f, 453.0f),
                PathNode.QuadTo(695.0f, 446.0f, 686.0f, 447.0f),
                PathNode.QuadTo(674.0f, 451.0f, 665.0f, 451.0f),
                PathNode.QuadTo(634.0f, 451.0f, 611.5f, 429.0f),
                PathNode.QuadTo(589.0f, 407.0f, 589.0f, 375.0f),
                PathNode.QuadTo(589.0f, 344.0f, 611.5f, 322.0f),
                PathNode.QuadTo(634.0f, 300.0f, 665.0f, 300.0f),
                PathNode.QuadTo(697.0f, 300.0f, 718.5f, 322.0f),
                PathNode.QuadTo(740.0f, 344.0f, 740.0f, 375.0f),
                PathNode.QuadTo(740.0f, 379.0f, 737.0f, 397.0f),
                PathNode.QuadTo(735.0f, 404.0f, 741.0f, 410.0f),
                PathNode.LineTo(1023.0f, 691.0f),
                PathNode.QuadTo(1029.0f, 698.0f, 1029.0f, 705.0f),
                PathNode.QuadTo(1029.0f, 712.0f, 1024.0f, 718.0f),
                PathNode.LineTo(1016.0f, 727.0f),
                PathNode.QuadTo(921.0f, 822.0f, 792.5f, 855.0f),
                PathNode.QuadTo(664.0f, 888.0f, 536.0f, 855.0f),
                PathNode.QuadTo(408.0f, 822.0f, 313.0f, 727.0f),
                PathNode.QuadTo(218.0f, 632.0f, 184.5f, 503.5f),
                PathNode.QuadTo(151.0f, 375.0f, 184.5f, 247.0f),
                PathNode.QuadTo(218.0f, 119.0f, 313.0f, 23.0f),
                PathNode.QuadTo(408.0f, -72.0f, 536.5f, -105.5f),
                PathNode.QuadTo(665.0f, -139.0f, 793.5f, -105.5f),
                PathNode.QuadTo(922.0f, -72.0f, 1017.0f, 23.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _searchdeviceLight!!
    }

private var _searchdeviceLight: ImageVector? = null

val MiuixIcons.Regular.SearchDevice: ImageVector
    get() {
        if (_searchdeviceRegular != null) return _searchdeviceRegular!!
        _searchdeviceRegular = ImageVector.Builder(
            name = "SearchDevice.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.3517647058825f,
            viewportHeight = 1225.3517647058825f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -52.13735294117646f, translationY = 987.3008823529412f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1026.0f, 14.0f),
                PathNode.QuadTo(1102.0f, 90.0f, 1140.5f, 190.0f),
                PathNode.QuadTo(1179.0f, 290.0f, 1175.0f, 397.5f),
                PathNode.QuadTo(1171.0f, 505.0f, 1121.0f, 603.0f),
                PathNode.QuadTo(1115.0f, 615.0f, 1102.5f, 616.5f),
                PathNode.QuadTo(1090.0f, 618.0f, 1081.0f, 609.0f),
                PathNode.LineTo(1059.0f, 588.0f),
                PathNode.QuadTo(1050.0f, 580.0f, 1048.5f, 571.5f),
                PathNode.QuadTo(1047.0f, 563.0f, 1050.0f, 555.0f),
                PathNode.QuadTo(1088.0f, 472.0f, 1090.5f, 385.0f),
                PathNode.QuadTo(1093.0f, 298.0f, 1060.5f, 218.0f),
                PathNode.QuadTo(1028.0f, 138.0f, 965.0f, 75.0f),
                PathNode.QuadTo(883.0f, -7.0f, 773.5f, -35.5f),
                PathNode.QuadTo(664.0f, -64.0f, 555.0f, -35.5f),
                PathNode.QuadTo(446.0f, -7.0f, 364.0f, 75.0f),
                PathNode.QuadTo(282.0f, 157.0f, 253.5f, 266.5f),
                PathNode.QuadTo(225.0f, 376.0f, 253.5f, 485.0f),
                PathNode.QuadTo(282.0f, 594.0f, 364.0f, 676.0f),
                PathNode.QuadTo(441.0f, 752.0f, 542.5f, 782.0f),
                PathNode.QuadTo(644.0f, 812.0f, 747.5f, 792.0f),
                PathNode.QuadTo(851.0f, 772.0f, 933.0f, 704.0f),
                PathNode.LineTo(698.0f, 469.0f),
                PathNode.QuadTo(690.0f, 461.0f, 681.0f, 462.0f),
                PathNode.QuadTo(673.0f, 463.0f, 665.0f, 464.0f),
                PathNode.QuadTo(628.0f, 464.0f, 602.0f, 438.0f),
                PathNode.QuadTo(576.0f, 412.0f, 576.0f, 375.0f),
                PathNode.QuadTo(576.0f, 338.0f, 602.0f, 312.5f),
                PathNode.QuadTo(628.0f, 287.0f, 665.0f, 287.0f),
                PathNode.QuadTo(702.0f, 287.0f, 727.5f, 312.5f),
                PathNode.QuadTo(753.0f, 338.0f, 753.0f, 375.0f),
                PathNode.QuadTo(753.0f, 383.0f, 751.0f, 393.0f),
                PathNode.QuadTo(749.0f, 399.0f, 757.0f, 407.0f),
                PathNode.LineTo(1037.0f, 687.0f),
                PathNode.QuadTo(1045.0f, 695.0f, 1045.0f, 704.5f),
                PathNode.QuadTo(1045.0f, 714.0f, 1039.0f, 722.0f),
                PathNode.LineTo(1026.0f, 736.0f),
                PathNode.QuadTo(928.0f, 834.0f, 796.5f, 868.0f),
                PathNode.QuadTo(665.0f, 902.0f, 533.5f, 868.0f),
                PathNode.QuadTo(402.0f, 834.0f, 304.0f, 736.0f),
                PathNode.QuadTo(206.0f, 638.0f, 171.5f, 506.5f),
                PathNode.QuadTo(137.0f, 375.0f, 171.5f, 243.5f),
                PathNode.QuadTo(206.0f, 112.0f, 304.0f, 14.0f),
                PathNode.QuadTo(402.0f, -84.0f, 533.5f, -118.5f),
                PathNode.QuadTo(665.0f, -153.0f, 796.5f, -118.5f),
                PathNode.QuadTo(928.0f, -84.0f, 1026.0f, 14.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _searchdeviceRegular!!
    }

private var _searchdeviceRegular: ImageVector? = null

val MiuixIcons.Heavy.SearchDevice: ImageVector
    get() {
        if (_searchdeviceHeavy != null) return _searchdeviceHeavy!!
        _searchdeviceHeavy = ImageVector.Builder(
            name = "SearchDevice.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1258.5591549295773f,
            viewportHeight = 1258.5591549295773f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -35.5705111876936f, translationY = 1004.3024647887323f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1044.0f, 14.0f),
                PathNode.QuadTo(1118.0f, 92.0f, 1155.0f, 192.0f),
                PathNode.QuadTo(1192.0f, 292.0f, 1189.0f, 393.0f),
                PathNode.QuadTo(1186.0f, 494.0f, 1145.0f, 576.0f),
                PathNode.QuadTo(1137.0f, 594.0f, 1122.0f, 597.5f),
                PathNode.QuadTo(1107.0f, 601.0f, 1095.0f, 589.0f),
                PathNode.LineTo(1063.0f, 561.0f),
                PathNode.QuadTo(1049.0f, 549.0f, 1047.5f, 539.0f),
                PathNode.QuadTo(1046.0f, 529.0f, 1050.0f, 515.0f),
                PathNode.QuadTo(1077.0f, 451.0f, 1077.0f, 376.0f),
                PathNode.QuadTo(1077.0f, 301.0f, 1048.5f, 226.5f),
                PathNode.QuadTo(1020.0f, 152.0f, 962.0f, 92.0f),
                PathNode.QuadTo(885.0f, 11.0f, 779.5f, -19.0f),
                PathNode.QuadTo(674.0f, -49.0f, 568.0f, -24.0f),
                PathNode.QuadTo(462.0f, 1.0f, 381.0f, 78.0f),
                PathNode.QuadTo(300.0f, 155.0f, 269.5f, 260.5f),
                PathNode.QuadTo(239.0f, 366.0f, 264.5f, 472.5f),
                PathNode.QuadTo(290.0f, 579.0f, 367.0f, 660.0f),
                PathNode.QuadTo(438.0f, 733.0f, 532.5f, 764.5f),
                PathNode.QuadTo(627.0f, 796.0f, 725.0f, 781.5f),
                PathNode.QuadTo(823.0f, 767.0f, 904.0f, 708.0f),
                PathNode.LineTo(686.0f, 479.0f),
                PathNode.QuadTo(683.0f, 476.0f, 680.0f, 477.0f),
                PathNode.QuadTo(675.0f, 478.0f, 663.0f, 478.0f),
                PathNode.QuadTo(620.0f, 476.0f, 590.5f, 445.5f),
                PathNode.QuadTo(561.0f, 415.0f, 562.0f, 372.0f),
                PathNode.QuadTo(564.0f, 330.0f, 594.5f, 301.5f),
                PathNode.QuadTo(625.0f, 273.0f, 668.0f, 274.0f),
                PathNode.QuadTo(710.0f, 275.0f, 739.0f, 305.5f),
                PathNode.QuadTo(768.0f, 336.0f, 767.0f, 378.0f),
                PathNode.QuadTo(767.0f, 389.0f, 765.0f, 395.0f),
                PathNode.QuadTo(764.0f, 399.0f, 766.0f, 401.0f),
                PathNode.LineTo(1039.0f, 687.0f),
                PathNode.QuadTo(1051.0f, 699.0f, 1050.5f, 714.0f),
                PathNode.QuadTo(1050.0f, 729.0f, 1040.0f, 741.0f),
                PathNode.LineTo(1026.0f, 755.0f),
                PathNode.QuadTo(923.0f, 853.0f, 787.0f, 885.0f),
                PathNode.QuadTo(651.0f, 917.0f, 517.0f, 878.0f),
                PathNode.QuadTo(383.0f, 839.0f, 285.0f, 736.0f),
                PathNode.QuadTo(187.0f, 633.0f, 155.0f, 497.5f),
                PathNode.QuadTo(123.0f, 362.0f, 161.5f, 228.0f),
                PathNode.QuadTo(200.0f, 94.0f, 304.0f, -4.0f),
                PathNode.QuadTo(407.0f, -102.0f, 543.0f, -134.5f),
                PathNode.QuadTo(679.0f, -167.0f, 813.0f, -128.5f),
                PathNode.QuadTo(947.0f, -90.0f, 1044.0f, 14.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _searchdeviceHeavy!!
    }

private var _searchdeviceHeavy: ImageVector? = null
