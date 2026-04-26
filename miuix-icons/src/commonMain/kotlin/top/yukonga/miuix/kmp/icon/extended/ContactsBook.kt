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

val MiuixIcons.ContactsBook: ImageVector
    get() = MiuixIcons.Regular.ContactsBook

val MiuixIcons.Light.ContactsBook: ImageVector
    get() {
        if (_contactsbookLight != null) return _contactsbookLight!!
        _contactsbookLight = ImageVector.Builder(
            name = "ContactsBook.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1194.0f,
            viewportHeight = 1194.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1194.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1094.5f, 596.3f),
                        PathNode.QuadTo(1094.5f, 732.0f, 1027.5f, 846.2f),
                        PathNode.QuadTo(960.5f, 960.5f, 846.7f, 1027.5f),
                        PathNode.QuadTo(732.9f, 1094.5f, 597.7f, 1094.5f),
                        PathNode.QuadTo(462.5f, 1094.5f, 348.0f, 1027.5f),
                        PathNode.QuadTo(233.5f, 960.5f, 166.5f, 846.2f),
                        PathNode.QuadTo(99.5f, 732.0f, 99.5f, 596.3f),
                        PathNode.QuadTo(99.5f, 461.5f, 165.9f, 347.6f),
                        PathNode.QuadTo(232.2f, 233.8f, 346.9f, 166.6f),
                        PathNode.QuadTo(461.5f, 99.5f, 597.7f, 99.5f),
                        PathNode.QuadTo(733.9f, 99.5f, 847.7f, 166.5f),
                        PathNode.QuadTo(961.5f, 233.5f, 1028.0f, 347.5f),
                        PathNode.QuadTo(1094.5f, 461.5f, 1094.5f, 596.3f),
                        PathNode.Close,
                        PathNode.MoveTo(301.5f, 272.5f),
                        PathNode.QuadTo(361.5f, 360.5f, 438.7f, 407.5f),
                        PathNode.QuadTo(516.0f, 454.5f, 597.5f, 454.5f),
                        PathNode.QuadTo(679.5f, 454.5f, 756.0f, 407.5f),
                        PathNode.QuadTo(832.5f, 360.5f, 891.5f, 272.5f),
                        PathNode.QuadTo(833.5f, 218.5f, 757.3f, 188.5f),
                        PathNode.QuadTo(681.0f, 158.5f, 597.3f, 158.5f),
                        PathNode.QuadTo(513.5f, 158.5f, 437.7f, 188.4f),
                        PathNode.QuadTo(361.9f, 218.3f, 301.5f, 272.5f),
                        PathNode.Close,
                        PathNode.MoveTo(159.5f, 596.5f),
                        PathNode.QuadTo(159.5f, 715.9f, 218.5f, 816.2f),
                        PathNode.QuadTo(277.5f, 916.5f, 378.0f, 975.5f),
                        PathNode.QuadTo(478.5f, 1034.5f, 598.0f, 1034.5f),
                        PathNode.QuadTo(716.5f, 1034.5f, 816.8f, 975.7f),
                        PathNode.QuadTo(917.2f, 916.8f, 976.3f, 816.2f),
                        PathNode.QuadTo(1035.5f, 715.5f, 1035.5f, 596.5f),
                        PathNode.QuadTo(1035.5f, 517.8f, 1009.0f, 445.6f),
                        PathNode.QuadTo(982.5f, 373.5f, 933.5f, 316.5f),
                        PathNode.QuadTo(867.5f, 410.5f, 780.5f, 462.0f),
                        PathNode.QuadTo(693.5f, 513.5f, 597.5f, 513.5f),
                        PathNode.QuadTo(500.5f, 513.5f, 414.0f, 462.0f),
                        PathNode.QuadTo(327.5f, 410.5f, 261.5f, 316.5f),
                        PathNode.QuadTo(212.5f, 373.5f, 186.0f, 445.6f),
                        PathNode.QuadTo(159.5f, 517.8f, 159.5f, 596.5f),
                        PathNode.Close,
                        PathNode.MoveTo(768.5f, 749.3f),
                        PathNode.QuadTo(768.5f, 795.5f, 745.4f, 834.9f),
                        PathNode.QuadTo(722.3f, 874.2f, 683.1f, 897.4f),
                        PathNode.QuadTo(643.8f, 920.5f, 597.7f, 920.5f),
                        PathNode.QuadTo(551.5f, 920.5f, 512.1f, 897.4f),
                        PathNode.QuadTo(472.8f, 874.3f, 449.6f, 835.1f),
                        PathNode.QuadTo(426.5f, 795.8f, 426.5f, 749.7f),
                        PathNode.QuadTo(426.5f, 703.5f, 449.6f, 664.1f),
                        PathNode.QuadTo(472.7f, 624.8f, 511.9f, 601.6f),
                        PathNode.QuadTo(551.2f, 578.5f, 597.3f, 578.5f),
                        PathNode.QuadTo(643.5f, 578.5f, 682.9f, 601.6f),
                        PathNode.QuadTo(722.2f, 624.7f, 745.4f, 663.9f),
                        PathNode.QuadTo(768.5f, 703.2f, 768.5f, 749.3f),
                        PathNode.Close,
                        PathNode.MoveTo(488.5f, 749.7f),
                        PathNode.QuadTo(488.5f, 794.5f, 520.1f, 826.5f),
                        PathNode.QuadTo(551.7f, 858.5f, 597.7f, 858.5f),
                        PathNode.QuadTo(642.5f, 858.5f, 674.5f, 826.3f),
                        PathNode.QuadTo(706.5f, 794.1f, 706.5f, 749.3f),
                        PathNode.QuadTo(706.5f, 704.5f, 674.4f, 672.5f),
                        PathNode.QuadTo(642.2f, 640.5f, 597.4f, 640.5f),
                        PathNode.QuadTo(551.5f, 640.5f, 520.0f, 672.7f),
                        PathNode.QuadTo(488.5f, 704.9f, 488.5f, 749.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsbookLight!!
    }

private var _contactsbookLight: ImageVector? = null

val MiuixIcons.Normal.ContactsBook: ImageVector
    get() {
        if (_contactsbookNormal != null) return _contactsbookNormal!!
        _contactsbookNormal = ImageVector.Builder(
            name = "ContactsBook.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1215.5f,
            viewportHeight = 1215.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1215.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1114.2f, 607.1f),
                        PathNode.QuadTo(1114.2f, 745.1f, 1046.1f, 861.3f),
                        PathNode.QuadTo(978.1f, 977.4f, 862.1f, 1045.8f),
                        PathNode.QuadTo(746.1f, 1114.2f, 608.3f, 1114.2f),
                        PathNode.QuadTo(470.5f, 1114.2f, 354.3f, 1045.8f),
                        PathNode.QuadTo(238.0f, 977.4f, 169.7f, 861.3f),
                        PathNode.QuadTo(101.3f, 745.1f, 101.3f, 607.1f),
                        PathNode.QuadTo(101.3f, 470.2f, 168.8f, 354.1f),
                        PathNode.QuadTo(236.3f, 238.1f, 352.9f, 169.7f),
                        PathNode.QuadTo(469.5f, 101.3f, 608.3f, 101.3f),
                        PathNode.QuadTo(747.1f, 101.3f, 863.1f, 169.3f),
                        PathNode.QuadTo(979.1f, 237.4f, 1046.6f, 353.8f),
                        PathNode.QuadTo(1114.2f, 470.2f, 1114.2f, 607.1f),
                        PathNode.Close,
                        PathNode.MoveTo(324.6f, 284.6f),
                        PathNode.QuadTo(383.2f, 367.8f, 456.9f, 412.0f),
                        PathNode.QuadTo(530.5f, 456.3f, 608.2f, 456.3f),
                        PathNode.QuadTo(686.1f, 456.3f, 759.5f, 412.0f),
                        PathNode.QuadTo(832.9f, 367.8f, 890.5f, 284.6f),
                        PathNode.QuadTo(833.2f, 234.0f, 760.6f, 206.1f),
                        PathNode.QuadTo(687.9f, 178.2f, 608.1f, 178.2f),
                        PathNode.QuadTo(528.4f, 178.2f, 455.5f, 206.1f),
                        PathNode.QuadTo(382.7f, 234.0f, 324.6f, 284.6f),
                        PathNode.Close,
                        PathNode.MoveTo(179.2f, 607.2f),
                        PathNode.QuadTo(179.2f, 724.3f, 236.8f, 822.7f),
                        PathNode.QuadTo(294.4f, 921.0f, 392.8f, 978.7f),
                        PathNode.QuadTo(491.3f, 1036.3f, 608.4f, 1036.3f),
                        PathNode.QuadTo(724.5f, 1036.3f, 822.9f, 978.7f),
                        PathNode.QuadTo(921.2f, 921.1f, 979.3f, 822.6f),
                        PathNode.QuadTo(1037.3f, 724.2f, 1037.3f, 607.2f),
                        PathNode.QuadTo(1037.3f, 533.1f, 1013.2f, 465.2f),
                        PathNode.QuadTo(989.1f, 397.3f, 944.9f, 342.4f),
                        PathNode.QuadTo(878.2f, 432.9f, 791.2f, 483.0f),
                        PathNode.QuadTo(704.2f, 533.2f, 608.2f, 533.2f),
                        PathNode.QuadTo(511.2f, 533.2f, 424.7f, 483.0f),
                        PathNode.QuadTo(338.2f, 432.9f, 271.5f, 342.4f),
                        PathNode.QuadTo(227.4f, 397.3f, 203.3f, 465.2f),
                        PathNode.QuadTo(179.2f, 533.1f, 179.2f, 607.2f),
                        PathNode.Close,
                        PathNode.MoveTo(788.9f, 760.2f),
                        PathNode.QuadTo(788.9f, 809.0f, 764.4f, 850.5f),
                        PathNode.QuadTo(740.0f, 892.0f, 698.6f, 916.4f),
                        PathNode.QuadTo(657.1f, 940.9f, 608.3f, 940.9f),
                        PathNode.QuadTo(559.5f, 940.9f, 518.0f, 916.4f),
                        PathNode.QuadTo(476.4f, 892.0f, 452.0f, 850.6f),
                        PathNode.QuadTo(427.6f, 809.1f, 427.6f, 760.3f),
                        PathNode.QuadTo(427.6f, 711.5f, 452.0f, 670.0f),
                        PathNode.QuadTo(476.4f, 628.4f, 517.9f, 604.0f),
                        PathNode.QuadTo(559.4f, 579.6f, 608.2f, 579.6f),
                        PathNode.QuadTo(657.0f, 579.6f, 698.5f, 604.0f),
                        PathNode.QuadTo(740.0f, 628.4f, 764.4f, 669.9f),
                        PathNode.QuadTo(788.9f, 711.4f, 788.9f, 760.2f),
                        PathNode.Close,
                        PathNode.MoveTo(508.9f, 760.3f),
                        PathNode.QuadTo(508.9f, 801.1f, 537.6f, 830.4f),
                        PathNode.QuadTo(566.4f, 859.6f, 608.3f, 859.6f),
                        PathNode.QuadTo(649.1f, 859.6f, 678.4f, 830.3f),
                        PathNode.QuadTo(707.6f, 801.0f, 707.6f, 760.2f),
                        PathNode.QuadTo(707.6f, 719.4f, 678.3f, 690.1f),
                        PathNode.QuadTo(649.0f, 660.9f, 608.2f, 660.9f),
                        PathNode.QuadTo(566.4f, 660.9f, 537.6f, 690.2f),
                        PathNode.QuadTo(508.9f, 719.5f, 508.9f, 760.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsbookNormal!!
    }

private var _contactsbookNormal: ImageVector? = null

val MiuixIcons.Regular.ContactsBook: ImageVector
    get() {
        if (_contactsbookRegular != null) return _contactsbookRegular!!
        _contactsbookRegular = ImageVector.Builder(
            name = "ContactsBook.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.2f,
            viewportHeight = 1225.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1225.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1123.1f, 612.1f),
                        PathNode.QuadTo(1123.1f, 751.1f, 1054.6f, 868.1f),
                        PathNode.QuadTo(986.1f, 985.1f, 869.1f, 1054.1f),
                        PathNode.QuadTo(752.1f, 1123.1f, 613.1f, 1123.1f),
                        PathNode.QuadTo(474.1f, 1123.1f, 357.1f, 1054.1f),
                        PathNode.QuadTo(240.1f, 985.1f, 171.1f, 868.1f),
                        PathNode.QuadTo(102.1f, 751.1f, 102.1f, 612.1f),
                        PathNode.QuadTo(102.1f, 474.1f, 170.1f, 357.1f),
                        PathNode.QuadTo(238.1f, 240.1f, 355.6f, 171.1f),
                        PathNode.QuadTo(473.1f, 102.1f, 613.1f, 102.1f),
                        PathNode.QuadTo(753.1f, 102.1f, 870.1f, 170.6f),
                        PathNode.QuadTo(987.1f, 239.1f, 1055.1f, 356.6f),
                        PathNode.QuadTo(1123.1f, 474.1f, 1123.1f, 612.1f),
                        PathNode.Close,
                        PathNode.MoveTo(335.1f, 290.1f),
                        PathNode.QuadTo(393.1f, 371.1f, 465.1f, 414.1f),
                        PathNode.QuadTo(537.1f, 457.1f, 613.1f, 457.1f),
                        PathNode.QuadTo(689.1f, 457.1f, 761.1f, 414.1f),
                        PathNode.QuadTo(833.1f, 371.1f, 890.1f, 290.1f),
                        PathNode.QuadTo(833.1f, 241.1f, 762.1f, 214.1f),
                        PathNode.QuadTo(691.1f, 187.1f, 613.1f, 187.1f),
                        PathNode.QuadTo(535.1f, 187.1f, 463.6f, 214.1f),
                        PathNode.QuadTo(392.1f, 241.1f, 335.1f, 290.1f),
                        PathNode.Close,
                        PathNode.MoveTo(188.1f, 612.1f),
                        PathNode.QuadTo(188.1f, 728.1f, 245.1f, 825.6f),
                        PathNode.QuadTo(302.1f, 923.1f, 399.6f, 980.1f),
                        PathNode.QuadTo(497.1f, 1037.1f, 613.1f, 1037.1f),
                        PathNode.QuadTo(728.1f, 1037.1f, 825.6f, 980.1f),
                        PathNode.QuadTo(923.1f, 923.1f, 980.6f, 825.6f),
                        PathNode.QuadTo(1038.1f, 728.1f, 1038.1f, 612.1f),
                        PathNode.QuadTo(1038.1f, 540.1f, 1015.1f, 474.1f),
                        PathNode.QuadTo(992.1f, 408.1f, 950.1f, 354.1f),
                        PathNode.QuadTo(883.1f, 443.1f, 796.1f, 492.6f),
                        PathNode.QuadTo(709.1f, 542.1f, 613.1f, 542.1f),
                        PathNode.QuadTo(516.1f, 542.1f, 429.6f, 492.6f),
                        PathNode.QuadTo(343.1f, 443.1f, 276.1f, 354.1f),
                        PathNode.QuadTo(234.1f, 408.1f, 211.1f, 474.1f),
                        PathNode.QuadTo(188.1f, 540.1f, 188.1f, 612.1f),
                        PathNode.Close,
                        PathNode.MoveTo(798.1f, 765.1f),
                        PathNode.QuadTo(798.1f, 815.1f, 773.1f, 857.6f),
                        PathNode.QuadTo(748.1f, 900.1f, 705.6f, 925.1f),
                        PathNode.QuadTo(663.1f, 950.1f, 613.1f, 950.1f),
                        PathNode.QuadTo(563.1f, 950.1f, 520.6f, 925.1f),
                        PathNode.QuadTo(478.1f, 900.1f, 453.1f, 857.6f),
                        PathNode.QuadTo(428.1f, 815.1f, 428.1f, 765.1f),
                        PathNode.QuadTo(428.1f, 715.1f, 453.1f, 672.6f),
                        PathNode.QuadTo(478.1f, 630.1f, 520.6f, 605.1f),
                        PathNode.QuadTo(563.1f, 580.1f, 613.1f, 580.1f),
                        PathNode.QuadTo(663.1f, 580.1f, 705.6f, 605.1f),
                        PathNode.QuadTo(748.1f, 630.1f, 773.1f, 672.6f),
                        PathNode.QuadTo(798.1f, 715.1f, 798.1f, 765.1f),
                        PathNode.Close,
                        PathNode.MoveTo(518.1f, 765.1f),
                        PathNode.QuadTo(518.1f, 804.1f, 545.6f, 832.1f),
                        PathNode.QuadTo(573.1f, 860.1f, 613.1f, 860.1f),
                        PathNode.QuadTo(652.1f, 860.1f, 680.1f, 832.1f),
                        PathNode.QuadTo(708.1f, 804.1f, 708.1f, 765.1f),
                        PathNode.QuadTo(708.1f, 726.1f, 680.1f, 698.1f),
                        PathNode.QuadTo(652.1f, 670.1f, 613.1f, 670.1f),
                        PathNode.QuadTo(573.1f, 670.1f, 545.6f, 698.1f),
                        PathNode.QuadTo(518.1f, 726.1f, 518.1f, 765.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsbookRegular!!
    }

private var _contactsbookRegular: ImageVector? = null

val MiuixIcons.Medium.ContactsBook: ImageVector
    get() {
        if (_contactsbookMedium != null) return _contactsbookMedium!!
        _contactsbookMedium = ImageVector.Builder(
            name = "ContactsBook.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1245.0f,
            viewportHeight = 1245.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1245.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1141.2f, 622.0f),
                        PathNode.QuadTo(1141.2f, 762.7f, 1071.5f, 881.8f),
                        PathNode.QuadTo(1001.8f, 1000.8f, 882.8f, 1071.0f),
                        PathNode.QuadTo(763.7f, 1141.2f, 623.0f, 1141.2f),
                        PathNode.QuadTo(482.2f, 1141.2f, 363.2f, 1071.0f),
                        PathNode.QuadTo(244.1f, 1000.8f, 173.9f, 881.8f),
                        PathNode.QuadTo(103.7f, 762.7f, 103.7f, 622.0f),
                        PathNode.QuadTo(103.7f, 481.8f, 172.8f, 362.9f),
                        PathNode.QuadTo(241.9f, 244.0f, 361.3f, 173.9f),
                        PathNode.QuadTo(480.7f, 103.7f, 623.0f, 103.7f),
                        PathNode.QuadTo(764.7f, 103.7f, 883.8f, 173.4f),
                        PathNode.QuadTo(1002.8f, 243.0f, 1072.0f, 362.4f),
                        PathNode.QuadTo(1141.2f, 481.8f, 1141.2f, 622.0f),
                        PathNode.Close,
                        PathNode.MoveTo(354.4f, 301.2f),
                        PathNode.QuadTo(410.5f, 378.5f, 480.2f, 419.5f),
                        PathNode.QuadTo(549.9f, 460.5f, 623.0f, 460.5f),
                        PathNode.QuadTo(696.0f, 460.5f, 765.7f, 419.6f),
                        PathNode.QuadTo(835.3f, 378.6f, 891.2f, 301.2f),
                        PathNode.QuadTo(836.0f, 254.7f, 767.2f, 229.1f),
                        PathNode.QuadTo(698.5f, 203.5f, 623.0f, 203.5f),
                        PathNode.QuadTo(547.6f, 203.5f, 478.5f, 229.1f),
                        PathNode.QuadTo(409.5f, 254.7f, 354.4f, 301.2f),
                        PathNode.Close,
                        PathNode.MoveTo(204.5f, 622.0f),
                        PathNode.QuadTo(204.5f, 736.2f, 260.6f, 832.2f),
                        PathNode.QuadTo(316.7f, 928.2f, 412.7f, 984.4f),
                        PathNode.QuadTo(508.7f, 1040.5f, 623.0f, 1040.5f),
                        PathNode.QuadTo(736.2f, 1040.5f, 832.2f, 984.4f),
                        PathNode.QuadTo(928.3f, 928.2f, 984.9f, 832.2f),
                        PathNode.QuadTo(1041.5f, 736.2f, 1041.5f, 622.0f),
                        PathNode.QuadTo(1041.5f, 553.5f, 1020.3f, 490.2f),
                        PathNode.QuadTo(999.0f, 426.8f, 960.0f, 374.6f),
                        PathNode.QuadTo(892.4f, 461.8f, 805.6f, 510.1f),
                        PathNode.QuadTo(718.8f, 558.5f, 623.0f, 558.5f),
                        PathNode.QuadTo(526.2f, 558.5f, 439.9f, 510.1f),
                        PathNode.QuadTo(353.6f, 461.8f, 286.0f, 374.6f),
                        PathNode.QuadTo(246.9f, 426.8f, 225.7f, 490.0f),
                        PathNode.QuadTo(204.5f, 553.1f, 204.5f, 622.0f),
                        PathNode.Close,
                        PathNode.MoveTo(811.5f, 778.5f),
                        PathNode.QuadTo(811.5f, 829.7f, 786.0f, 872.7f),
                        PathNode.QuadTo(760.6f, 915.8f, 717.2f, 941.1f),
                        PathNode.QuadTo(673.9f, 966.5f, 623.0f, 966.5f),
                        PathNode.QuadTo(572.0f, 966.5f, 528.7f, 941.1f),
                        PathNode.QuadTo(485.4f, 915.8f, 459.9f, 872.7f),
                        PathNode.QuadTo(434.5f, 829.7f, 434.5f, 778.5f),
                        PathNode.QuadTo(434.5f, 727.3f, 460.0f, 684.2f),
                        PathNode.QuadTo(485.6f, 641.2f, 528.7f, 615.6f),
                        PathNode.QuadTo(571.8f, 590.0f, 623.0f, 590.0f),
                        PathNode.QuadTo(674.2f, 590.0f, 717.2f, 615.6f),
                        PathNode.QuadTo(760.3f, 641.2f, 785.9f, 684.2f),
                        PathNode.QuadTo(811.5f, 727.3f, 811.5f, 778.5f),
                        PathNode.Close,
                        PathNode.MoveTo(535.0f, 778.5f),
                        PathNode.QuadTo(535.0f, 815.2f, 560.8f, 840.8f),
                        PathNode.QuadTo(586.5f, 866.5f, 623.0f, 866.5f),
                        PathNode.QuadTo(659.6f, 866.5f, 685.3f, 840.8f),
                        PathNode.QuadTo(710.9f, 815.2f, 710.9f, 778.8f),
                        PathNode.QuadTo(710.9f, 742.5f, 685.3f, 716.5f),
                        PathNode.QuadTo(659.6f, 690.6f, 623.0f, 690.6f),
                        PathNode.QuadTo(586.5f, 690.6f, 560.8f, 716.5f),
                        PathNode.QuadTo(535.0f, 742.4f, 535.0f, 778.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsbookMedium!!
    }

private var _contactsbookMedium: ImageVector? = null

val MiuixIcons.Demibold.ContactsBook: ImageVector
    get() {
        if (_contactsbookDemibold != null) return _contactsbookDemibold!!
        _contactsbookDemibold = ImageVector.Builder(
            name = "ContactsBook.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1258.8f,
            viewportHeight = 1258.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1258.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1153.9f, 628.9f),
                        PathNode.QuadTo(1153.9f, 770.9f, 1083.4f, 891.4f),
                        PathNode.QuadTo(1012.8f, 1011.8f, 892.4f, 1082.9f),
                        PathNode.QuadTo(771.9f, 1153.9f, 629.9f, 1153.9f),
                        PathNode.QuadTo(487.9f, 1153.9f, 367.4f, 1082.9f),
                        PathNode.QuadTo(247.0f, 1011.8f, 175.9f, 891.4f),
                        PathNode.QuadTo(104.9f, 770.9f, 104.9f, 628.9f),
                        PathNode.QuadTo(104.9f, 487.1f, 174.8f, 366.9f),
                        PathNode.QuadTo(244.6f, 246.7f, 365.3f, 175.8f),
                        PathNode.QuadTo(486.1f, 104.9f, 629.9f, 104.9f),
                        PathNode.QuadTo(772.9f, 104.9f, 893.4f, 175.3f),
                        PathNode.QuadTo(1013.9f, 245.7f, 1083.9f, 366.4f),
                        PathNode.QuadTo(1153.9f, 487.1f, 1153.9f, 628.9f),
                        PathNode.Close,
                        PathNode.MoveTo(367.9f, 308.9f),
                        PathNode.QuadTo(422.7f, 383.6f, 490.8f, 423.2f),
                        PathNode.QuadTo(558.9f, 462.9f, 629.9f, 462.9f),
                        PathNode.QuadTo(700.9f, 462.9f, 768.9f, 423.4f),
                        PathNode.QuadTo(836.9f, 383.9f, 891.9f, 308.9f),
                        PathNode.QuadTo(838.0f, 264.2f, 770.8f, 239.5f),
                        PathNode.QuadTo(703.7f, 214.9f, 629.9f, 214.9f),
                        PathNode.QuadTo(556.4f, 214.9f, 489.0f, 239.5f),
                        PathNode.QuadTo(421.6f, 264.2f, 367.9f, 308.9f),
                        PathNode.Close,
                        PathNode.MoveTo(215.9f, 628.9f),
                        PathNode.QuadTo(215.9f, 741.9f, 271.4f, 836.9f),
                        PathNode.QuadTo(326.9f, 931.9f, 421.9f, 987.4f),
                        PathNode.QuadTo(516.9f, 1042.9f, 629.9f, 1042.9f),
                        PathNode.QuadTo(741.9f, 1042.9f, 836.9f, 987.4f),
                        PathNode.QuadTo(931.9f, 931.9f, 987.9f, 836.9f),
                        PathNode.QuadTo(1043.9f, 741.9f, 1043.9f, 628.9f),
                        PathNode.QuadTo(1043.9f, 562.9f, 1023.9f, 501.4f),
                        PathNode.QuadTo(1003.9f, 439.9f, 966.9f, 388.9f),
                        PathNode.QuadTo(898.9f, 474.9f, 812.2f, 522.4f),
                        PathNode.QuadTo(725.5f, 569.9f, 629.9f, 569.9f),
                        PathNode.QuadTo(533.3f, 569.9f, 447.1f, 522.4f),
                        PathNode.QuadTo(360.9f, 474.9f, 292.9f, 388.9f),
                        PathNode.QuadTo(255.9f, 439.9f, 235.9f, 501.0f),
                        PathNode.QuadTo(215.9f, 562.2f, 215.9f, 628.9f),
                        PathNode.Close,
                        PathNode.MoveTo(820.9f, 787.9f),
                        PathNode.QuadTo(820.9f, 839.9f, 795.1f, 883.3f),
                        PathNode.QuadTo(769.3f, 926.8f, 725.4f, 952.3f),
                        PathNode.QuadTo(681.5f, 977.9f, 629.9f, 977.9f),
                        PathNode.QuadTo(578.3f, 977.9f, 534.4f, 952.3f),
                        PathNode.QuadTo(490.5f, 926.8f, 464.7f, 883.3f),
                        PathNode.QuadTo(438.9f, 839.9f, 438.9f, 787.9f),
                        PathNode.QuadTo(438.9f, 735.9f, 464.9f, 692.4f),
                        PathNode.QuadTo(490.9f, 648.9f, 534.4f, 622.9f),
                        PathNode.QuadTo(577.9f, 596.9f, 629.9f, 596.9f),
                        PathNode.QuadTo(681.9f, 596.9f, 725.4f, 622.9f),
                        PathNode.QuadTo(768.9f, 648.9f, 794.9f, 692.4f),
                        PathNode.QuadTo(820.9f, 735.9f, 820.9f, 787.9f),
                        PathNode.Close,
                        PathNode.MoveTo(546.9f, 787.9f),
                        PathNode.QuadTo(546.9f, 822.9f, 571.4f, 846.9f),
                        PathNode.QuadTo(595.9f, 870.9f, 629.9f, 870.9f),
                        PathNode.QuadTo(664.9f, 870.9f, 688.9f, 846.9f),
                        PathNode.QuadTo(712.9f, 822.9f, 712.9f, 788.4f),
                        PathNode.QuadTo(712.9f, 753.9f, 688.9f, 729.4f),
                        PathNode.QuadTo(664.9f, 704.9f, 629.9f, 704.9f),
                        PathNode.QuadTo(595.9f, 704.9f, 571.4f, 729.4f),
                        PathNode.QuadTo(546.9f, 753.8f, 546.9f, 787.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsbookDemibold!!
    }

private var _contactsbookDemibold: ImageVector? = null
