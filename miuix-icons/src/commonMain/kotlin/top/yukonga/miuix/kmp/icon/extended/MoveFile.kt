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

val MiuixIcons.MoveFile: ImageVector
    get() = MiuixIcons.Regular.MoveFile

val MiuixIcons.Light.MoveFile: ImageVector
    get() {
        if (_movefileLight != null) return _movefileLight!!
        _movefileLight = ImageVector.Builder(
            name = "MoveFile.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1261.2f,
            viewportHeight = 1261.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -33.89999999999998f, translationY = 998.6f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(963.0f, -61.0f),
                PathNode.LineTo(1181.0f, 158.0f),
                PathNode.QuadTo(1190.0f, 167.0f, 1190.0f, 178.0f),
                PathNode.QuadTo(1190.0f, 189.0f, 1181.0f, 198.0f),
                PathNode.LineTo(961.0f, 417.0f),
                PathNode.QuadTo(957.0f, 422.0f, 950.5f, 422.0f),
                PathNode.QuadTo(944.0f, 422.0f, 939.0f, 417.0f),
                PathNode.LineTo(920.0f, 398.0f),
                PathNode.QuadTo(915.0f, 393.0f, 915.0f, 386.5f),
                PathNode.QuadTo(915.0f, 380.0f, 920.0f, 376.0f),
                PathNode.LineTo(1088.0f, 208.0f),
                PathNode.HorizontalTo(640.0f),
                PathNode.QuadTo(633.0f, 208.0f, 629.0f, 203.0f),
                PathNode.QuadTo(625.0f, 198.0f, 625.0f, 191.0f),
                PathNode.VerticalTo(165.0f),
                PathNode.QuadTo(625.0f, 158.0f, 629.0f, 153.0f),
                PathNode.QuadTo(633.0f, 148.0f, 640.0f, 148.0f),
                PathNode.HorizontalTo(1088.0f),
                PathNode.LineTo(921.0f, -20.0f),
                PathNode.QuadTo(917.0f, -25.0f, 917.0f, -31.5f),
                PathNode.QuadTo(917.0f, -38.0f, 921.0f, -42.0f),
                PathNode.LineTo(940.0f, -61.0f),
                PathNode.QuadTo(945.0f, -66.0f, 951.5f, -66.0f),
                PathNode.QuadTo(958.0f, -66.0f, 963.0f, -61.0f),
                PathNode.Close,
                PathNode.MoveTo(821.0f, -61.0f),
                PathNode.VerticalTo(-37.0f),
                PathNode.QuadTo(821.0f, -28.0f, 816.0f, -23.5f),
                PathNode.QuadTo(811.0f, -19.0f, 801.0f, -19.0f),
                PathNode.HorizontalTo(386.0f),
                PathNode.QuadTo(332.0f, -19.0f, 307.0f, -17.0f),
                PathNode.QuadTo(282.0f, -15.0f, 265.0f, -6.0f),
                PathNode.QuadTo(229.0f, 11.0f, 211.0f, 48.0f),
                PathNode.QuadTo(201.0f, 66.0f, 199.0f, 90.5f),
                PathNode.QuadTo(197.0f, 115.0f, 197.0f, 169.0f),
                PathNode.VerticalTo(568.0f),
                PathNode.QuadTo(197.0f, 622.0f, 199.0f, 646.5f),
                PathNode.QuadTo(201.0f, 671.0f, 211.0f, 689.0f),
                PathNode.QuadTo(229.0f, 726.0f, 265.0f, 742.0f),
                PathNode.QuadTo(282.0f, 751.0f, 307.0f, 753.0f),
                PathNode.QuadTo(332.0f, 755.0f, 386.0f, 755.0f),
                PathNode.HorizontalTo(428.0f),
                PathNode.QuadTo(457.0f, 755.0f, 466.0f, 753.0f),
                PathNode.QuadTo(486.0f, 747.0f, 494.0f, 741.0f),
                PathNode.QuadTo(504.0f, 734.0f, 521.0f, 715.0f),
                PathNode.QuadTo(548.0f, 683.0f, 568.0f, 668.0f),
                PathNode.QuadTo(594.0f, 653.0f, 610.0f, 650.0f),
                PathNode.QuadTo(620.0f, 647.0f, 636.5f, 646.5f),
                PathNode.QuadTo(653.0f, 646.0f, 676.0f, 646.0f),
                PathNode.HorizontalTo(916.0f),
                PathNode.QuadTo(965.0f, 646.0f, 991.5f, 620.0f),
                PathNode.QuadTo(1018.0f, 594.0f, 1022.0f, 528.0f),
                PathNode.QuadTo(1022.0f, 525.0f, 1023.0f, 524.0f),
                PathNode.QuadTo(1024.0f, 513.0f, 1026.5f, 505.0f),
                PathNode.QuadTo(1029.0f, 497.0f, 1035.0f, 492.0f),
                PathNode.LineTo(1072.0f, 461.0f),
                PathNode.QuadTo(1078.0f, 456.0f, 1084.0f, 459.0f),
                PathNode.QuadTo(1090.0f, 462.0f, 1090.0f, 468.0f),
                PathNode.QuadTo(1090.0f, 508.0f, 1086.0f, 547.0f),
                PathNode.QuadTo(1079.0f, 621.0f, 1045.0f, 659.0f),
                PathNode.QuadTo(1011.0f, 697.0f, 935.0f, 703.0f),
                PathNode.QuadTo(903.0f, 705.0f, 841.0f, 705.0f),
                PathNode.HorizontalTo(662.0f),
                PathNode.QuadTo(633.0f, 705.0f, 624.0f, 707.0f),
                PathNode.QuadTo(610.0f, 710.0f, 596.0f, 719.0f),
                PathNode.QuadTo(588.0f, 725.0f, 564.0f, 752.0f),
                PathNode.QuadTo(539.0f, 780.0f, 523.0f, 791.0f),
                PathNode.QuadTo(506.0f, 804.0f, 477.0f, 811.0f),
                PathNode.QuadTo(467.0f, 814.0f, 452.0f, 814.5f),
                PathNode.QuadTo(437.0f, 815.0f, 416.0f, 815.0f),
                PathNode.QuadTo(335.0f, 815.0f, 301.0f, 812.5f),
                PathNode.QuadTo(267.0f, 810.0f, 244.0f, 800.0f),
                PathNode.QuadTo(214.0f, 787.0f, 190.5f, 763.5f),
                PathNode.QuadTo(167.0f, 740.0f, 153.0f, 709.0f),
                PathNode.QuadTo(143.0f, 685.0f, 141.0f, 652.0f),
                PathNode.QuadTo(139.0f, 619.0f, 139.0f, 538.0f),
                PathNode.VerticalTo(212.0f),
                PathNode.QuadTo(139.0f, 120.0f, 142.0f, 82.0f),
                PathNode.QuadTo(145.0f, 44.0f, 158.0f, 18.0f),
                PathNode.QuadTo(181.0f, -31.0f, 235.0f, -59.0f),
                PathNode.QuadTo(261.0f, -73.0f, 299.0f, -76.0f),
                PathNode.QuadTo(337.0f, -79.0f, 429.0f, -79.0f),
                PathNode.HorizontalTo(801.0f),
                PathNode.QuadTo(811.0f, -79.0f, 816.0f, -74.0f),
                PathNode.QuadTo(821.0f, -69.0f, 821.0f, -61.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _movefileLight!!
    }

private var _movefileLight: ImageVector? = null

val MiuixIcons.Regular.MoveFile: ImageVector
    get() {
        if (_movefileRegular != null) return _movefileRegular!!
        _movefileRegular = ImageVector.Builder(
            name = "MoveFile.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1293.6f,
            viewportHeight = 1293.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -18.200000000000045f, translationY = 1014.8f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(977.0f, -65.0f),
                PathNode.LineTo(1193.0f, 151.0f),
                PathNode.QuadTo(1204.0f, 162.0f, 1204.0f, 178.0f),
                PathNode.QuadTo(1204.0f, 194.0f, 1193.0f, 205.0f),
                PathNode.LineTo(976.0f, 421.0f),
                PathNode.QuadTo(969.0f, 429.0f, 959.5f, 429.0f),
                PathNode.QuadTo(950.0f, 429.0f, 943.0f, 421.0f),
                PathNode.LineTo(916.0f, 394.0f),
                PathNode.QuadTo(909.0f, 387.0f, 909.0f, 377.5f),
                PathNode.QuadTo(909.0f, 368.0f, 916.0f, 361.0f),
                PathNode.LineTo(1056.0f, 221.0f),
                PathNode.HorizontalTo(648.0f),
                PathNode.QuadTo(638.0f, 221.0f, 631.5f, 214.0f),
                PathNode.QuadTo(625.0f, 207.0f, 625.0f, 197.0f),
                PathNode.VerticalTo(159.0f),
                PathNode.QuadTo(625.0f, 149.0f, 631.5f, 142.0f),
                PathNode.QuadTo(638.0f, 135.0f, 648.0f, 135.0f),
                PathNode.HorizontalTo(1056.0f),
                PathNode.LineTo(917.0f, -5.0f),
                PathNode.QuadTo(910.0f, -12.0f, 910.0f, -21.5f),
                PathNode.QuadTo(910.0f, -31.0f, 917.0f, -38.0f),
                PathNode.LineTo(944.0f, -65.0f),
                PathNode.QuadTo(951.0f, -72.0f, 960.5f, -72.0f),
                PathNode.QuadTo(970.0f, -72.0f, 977.0f, -65.0f),
                PathNode.Close,
                PathNode.MoveTo(821.0f, -66.0f),
                PathNode.VerticalTo(-32.0f),
                PathNode.QuadTo(821.0f, -19.0f, 814.0f, -12.5f),
                PathNode.QuadTo(807.0f, -6.0f, 793.0f, -6.0f),
                PathNode.HorizontalTo(386.0f),
                PathNode.QuadTo(333.0f, -6.0f, 310.0f, -4.0f),
                PathNode.QuadTo(287.0f, -2.0f, 271.0f, 6.0f),
                PathNode.QuadTo(238.0f, 21.0f, 223.0f, 54.0f),
                PathNode.QuadTo(215.0f, 70.0f, 213.0f, 93.0f),
                PathNode.QuadTo(211.0f, 116.0f, 211.0f, 169.0f),
                PathNode.VerticalTo(568.0f),
                PathNode.QuadTo(211.0f, 621.0f, 213.0f, 643.5f),
                PathNode.QuadTo(215.0f, 666.0f, 223.0f, 683.0f),
                PathNode.QuadTo(239.0f, 715.0f, 271.0f, 730.0f),
                PathNode.QuadTo(287.0f, 738.0f, 310.0f, 740.0f),
                PathNode.QuadTo(333.0f, 742.0f, 386.0f, 742.0f),
                PathNode.HorizontalTo(430.0f),
                PathNode.QuadTo(457.0f, 742.0f, 465.0f, 740.0f),
                PathNode.QuadTo(479.0f, 737.0f, 488.0f, 730.0f),
                PathNode.QuadTo(496.0f, 724.0f, 513.0f, 706.0f),
                PathNode.QuadTo(546.0f, 668.0f, 563.0f, 657.0f),
                PathNode.QuadTo(586.0f, 643.0f, 609.0f, 637.0f),
                PathNode.QuadTo(620.0f, 634.0f, 637.5f, 633.5f),
                PathNode.QuadTo(655.0f, 633.0f, 678.0f, 633.0f),
                PathNode.HorizontalTo(914.0f),
                PathNode.QuadTo(948.0f, 633.0f, 972.0f, 614.0f),
                PathNode.QuadTo(996.0f, 595.0f, 1003.0f, 559.0f),
                PathNode.QuadTo(1004.0f, 556.0f, 1004.0f, 553.0f),
                PathNode.QuadTo(1007.0f, 534.0f, 1010.5f, 522.5f),
                PathNode.QuadTo(1014.0f, 511.0f, 1023.0f, 503.0f),
                PathNode.LineTo(1080.0f, 453.0f),
                PathNode.QuadTo(1087.0f, 447.0f, 1095.5f, 451.0f),
                PathNode.QuadTo(1104.0f, 455.0f, 1104.0f, 462.0f),
                PathNode.QuadTo(1104.0f, 502.0f, 1100.0f, 549.0f),
                PathNode.QuadTo(1092.0f, 627.0f, 1055.0f, 668.0f),
                PathNode.QuadTo(1018.0f, 709.0f, 936.0f, 716.0f),
                PathNode.QuadTo(904.0f, 718.0f, 841.0f, 718.0f),
                PathNode.HorizontalTo(661.0f),
                PathNode.QuadTo(634.0f, 718.0f, 626.0f, 720.0f),
                PathNode.QuadTo(613.0f, 723.0f, 603.0f, 730.0f),
                PathNode.QuadTo(596.0f, 735.0f, 573.0f, 761.0f),
                PathNode.QuadTo(548.0f, 790.0f, 530.0f, 802.0f),
                PathNode.QuadTo(509.0f, 817.0f, 479.0f, 824.0f),
                PathNode.QuadTo(469.0f, 827.0f, 453.0f, 827.5f),
                PathNode.QuadTo(437.0f, 828.0f, 415.0f, 828.0f),
                PathNode.QuadTo(334.0f, 828.0f, 299.0f, 825.5f),
                PathNode.QuadTo(264.0f, 823.0f, 239.0f, 812.0f),
                PathNode.QuadTo(207.0f, 798.0f, 181.5f, 772.5f),
                PathNode.QuadTo(156.0f, 747.0f, 141.0f, 714.0f),
                PathNode.QuadTo(131.0f, 689.0f, 128.5f, 654.0f),
                PathNode.QuadTo(126.0f, 619.0f, 126.0f, 538.0f),
                PathNode.VerticalTo(212.0f),
                PathNode.QuadTo(126.0f, 119.0f, 129.0f, 79.5f),
                PathNode.QuadTo(132.0f, 40.0f, 146.0f, 12.0f),
                PathNode.QuadTo(173.0f, -42.0f, 229.0f, -71.0f),
                PathNode.QuadTo(257.0f, -85.0f, 296.5f, -88.5f),
                PathNode.QuadTo(336.0f, -92.0f, 429.0f, -92.0f),
                PathNode.HorizontalTo(793.0f),
                PathNode.QuadTo(806.0f, -92.0f, 813.5f, -85.0f),
                PathNode.QuadTo(821.0f, -78.0f, 821.0f, -66.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _movefileRegular!!
    }

private var _movefileRegular: ImageVector? = null

val MiuixIcons.Heavy.MoveFile: ImageVector
    get() {
        if (_movefileHeavy != null) return _movefileHeavy!!
        _movefileHeavy = ImageVector.Builder(
            name = "MoveFile.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1328.3999999999999f,
            viewportHeight = 1328.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -1.3000000000000682f, translationY = 1032.1999999999998f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(987.0f, -76.0f),
                PathNode.LineTo(1203.0f, 140.0f),
                PathNode.QuadTo(1219.0f, 155.0f, 1219.0f, 176.5f),
                PathNode.QuadTo(1219.0f, 198.0f, 1203.0f, 213.0f),
                PathNode.LineTo(987.0f, 429.0f),
                PathNode.QuadTo(976.0f, 440.0f, 960.0f, 440.0f),
                PathNode.QuadTo(944.0f, 440.0f, 933.0f, 429.0f),
                PathNode.LineTo(907.0f, 402.0f),
                PathNode.QuadTo(896.0f, 392.0f, 896.0f, 376.5f),
                PathNode.QuadTo(896.0f, 361.0f, 907.0f, 350.0f),
                PathNode.LineTo(1024.0f, 233.0f),
                PathNode.HorizontalTo(648.0f),
                PathNode.QuadTo(633.0f, 233.0f, 622.5f, 222.0f),
                PathNode.QuadTo(612.0f, 211.0f, 612.0f, 195.0f),
                PathNode.VerticalTo(157.0f),
                PathNode.QuadTo(612.0f, 142.0f, 622.5f, 130.5f),
                PathNode.QuadTo(633.0f, 119.0f, 648.0f, 119.0f),
                PathNode.HorizontalTo(1024.0f),
                PathNode.LineTo(908.0f, 3.0f),
                PathNode.QuadTo(897.0f, -8.0f, 897.0f, -23.5f),
                PathNode.QuadTo(897.0f, -39.0f, 908.0f, -49.0f),
                PathNode.LineTo(935.0f, -76.0f),
                PathNode.QuadTo(946.0f, -87.0f, 961.5f, -87.0f),
                PathNode.QuadTo(977.0f, -87.0f, 987.0f, -76.0f),
                PathNode.Close,
                PathNode.MoveTo(816.0f, -66.0f),
                PathNode.VerticalTo(-32.0f),
                PathNode.QuadTo(816.0f, -13.0f, 805.0f, -2.5f),
                PathNode.QuadTo(794.0f, 8.0f, 775.0f, 8.0f),
                PathNode.HorizontalTo(386.0f),
                PathNode.QuadTo(333.0f, 8.0f, 311.5f, 9.5f),
                PathNode.QuadTo(290.0f, 11.0f, 277.0f, 18.0f),
                PathNode.QuadTo(249.0f, 31.0f, 235.0f, 60.0f),
                PathNode.QuadTo(227.0f, 74.0f, 225.5f, 95.0f),
                PathNode.QuadTo(224.0f, 116.0f, 224.0f, 169.0f),
                PathNode.VerticalTo(568.0f),
                PathNode.QuadTo(224.0f, 621.0f, 225.5f, 641.5f),
                PathNode.QuadTo(227.0f, 662.0f, 235.0f, 677.0f),
                PathNode.QuadTo(250.0f, 705.0f, 277.0f, 718.0f),
                PathNode.QuadTo(290.0f, 725.0f, 311.5f, 726.5f),
                PathNode.QuadTo(333.0f, 728.0f, 386.0f, 728.0f),
                PathNode.HorizontalTo(430.0f),
                PathNode.QuadTo(455.0f, 728.0f, 461.0f, 727.0f),
                PathNode.QuadTo(473.0f, 724.0f, 480.0f, 719.0f),
                PathNode.QuadTo(480.0f, 719.0f, 502.0f, 697.0f),
                PathNode.QuadTo(537.0f, 657.0f, 555.0f, 646.0f),
                PathNode.QuadTo(580.0f, 630.0f, 605.0f, 624.0f),
                PathNode.QuadTo(617.0f, 621.0f, 635.5f, 620.0f),
                PathNode.QuadTo(654.0f, 619.0f, 678.0f, 619.0f),
                PathNode.HorizontalTo(920.0f),
                PathNode.QuadTo(949.0f, 619.0f, 967.0f, 606.0f),
                PathNode.QuadTo(985.0f, 593.0f, 989.0f, 566.0f),
                PathNode.QuadTo(989.0f, 563.0f, 990.0f, 562.0f),
                PathNode.QuadTo(992.0f, 548.0f, 995.5f, 539.0f),
                PathNode.QuadTo(999.0f, 530.0f, 1007.0f, 522.0f),
                PathNode.LineTo(1078.0f, 459.0f),
                PathNode.QuadTo(1092.0f, 446.0f, 1103.5f, 451.5f),
                PathNode.QuadTo(1115.0f, 457.0f, 1116.0f, 472.0f),
                PathNode.QuadTo(1116.0f, 511.0f, 1113.0f, 560.0f),
                PathNode.QuadTo(1108.0f, 635.0f, 1066.5f, 678.5f),
                PathNode.QuadTo(1025.0f, 722.0f, 936.0f, 730.0f),
                PathNode.QuadTo(904.0f, 732.0f, 841.0f, 732.0f),
                PathNode.HorizontalTo(661.0f),
                PathNode.QuadTo(635.0f, 732.0f, 629.0f, 733.0f),
                PathNode.QuadTo(618.0f, 736.0f, 610.0f, 741.0f),
                PathNode.QuadTo(606.0f, 744.0f, 583.0f, 770.0f),
                PathNode.QuadTo(560.0f, 798.0f, 538.0f, 813.0f),
                PathNode.QuadTo(513.0f, 830.0f, 482.0f, 837.0f),
                PathNode.QuadTo(470.0f, 840.0f, 453.5f, 841.0f),
                PathNode.QuadTo(437.0f, 842.0f, 415.0f, 842.0f),
                PathNode.QuadTo(333.0f, 842.0f, 296.5f, 839.0f),
                PathNode.QuadTo(260.0f, 836.0f, 233.0f, 824.0f),
                PathNode.QuadTo(198.0f, 809.0f, 171.0f, 782.0f),
                PathNode.QuadTo(144.0f, 755.0f, 128.0f, 720.0f),
                PathNode.QuadTo(117.0f, 693.0f, 114.5f, 656.5f),
                PathNode.QuadTo(112.0f, 620.0f, 112.0f, 538.0f),
                PathNode.VerticalTo(212.0f),
                PathNode.QuadTo(112.0f, 119.0f, 115.5f, 77.5f),
                PathNode.QuadTo(119.0f, 36.0f, 133.0f, 6.0f),
                PathNode.QuadTo(163.0f, -53.0f, 222.0f, -83.0f),
                PathNode.QuadTo(252.0f, -99.0f, 293.5f, -102.5f),
                PathNode.QuadTo(335.0f, -106.0f, 429.0f, -106.0f),
                PathNode.HorizontalTo(775.0f),
                PathNode.QuadTo(794.0f, -106.0f, 805.0f, -95.0f),
                PathNode.QuadTo(816.0f, -84.0f, 816.0f, -66.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _movefileHeavy!!
    }

private var _movefileHeavy: ImageVector? = null
