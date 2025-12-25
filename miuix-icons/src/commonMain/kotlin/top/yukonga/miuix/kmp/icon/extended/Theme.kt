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

val MiuixIcons.Theme: ImageVector
    get() = MiuixIcons.Regular.Theme

val MiuixIcons.Light.Theme: ImageVector
    get() {
        if (_themeLight != null) return _themeLight!!
        _themeLight = ImageVector.Builder(
            name = "Theme.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1231.2f,
            viewportHeight = 1231.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -48.875f, translationY = 991.6f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(795.0f, 15.0f),
                PathNode.LineTo(771.0f, 200.0f),
                PathNode.QuadTo(768.0f, 222.0f, 781.0f, 241.0f),
                PathNode.QuadTo(794.0f, 260.0f, 816.0f, 265.0f),
                PathNode.LineTo(940.0f, 295.0f),
                PathNode.LineTo(952.0f, 298.0f),
                PathNode.QuadTo(963.0f, 301.0f, 973.0f, 303.5f),
                PathNode.QuadTo(983.0f, 306.0f, 992.0f, 310.0f),
                PathNode.QuadTo(1017.0f, 318.0f, 1032.0f, 337.0f),
                PathNode.QuadTo(1047.0f, 354.0f, 1051.0f, 382.0f),
                PathNode.QuadTo(1054.0f, 397.0f, 1054.0f, 414.0f),
                PathNode.LineTo(1056.0f, 436.0f),
                PathNode.LineTo(1071.0f, 706.0f),
                PathNode.QuadTo(1075.0f, 762.0f, 1074.0f, 779.0f),
                PathNode.QuadTo(1070.0f, 816.0f, 1056.0f, 838.0f),
                PathNode.QuadTo(1038.0f, 856.0f, 1038.0f, 856.0f),
                PathNode.QuadTo(1017.0f, 876.0f, 982.0f, 881.0f),
                PathNode.QuadTo(962.0f, 884.0f, 912.0f, 886.0f),
                PathNode.HorizontalTo(909.0f),
                PathNode.QuadTo(818.0f, 889.0f, 663.0f, 889.0f),
                PathNode.QuadTo(540.0f, 889.0f, 420.0f, 885.0f),
                PathNode.HorizontalTo(417.0f),
                PathNode.QuadTo(367.0f, 883.0f, 347.0f, 880.0f),
                PathNode.QuadTo(315.0f, 877.0f, 291.0f, 856.0f),
                PathNode.QuadTo(280.0f, 848.0f, 274.0f, 837.0f),
                PathNode.QuadTo(257.0f, 815.0f, 255.0f, 778.0f),
                PathNode.QuadTo(254.0f, 751.0f, 257.0f, 704.0f),
                PathNode.LineTo(276.0f, 432.0f),
                PathNode.QuadTo(277.0f, 403.0f, 282.0f, 379.0f),
                PathNode.QuadTo(285.0f, 353.0f, 300.0f, 337.0f),
                PathNode.QuadTo(316.0f, 318.0f, 339.0f, 310.0f),
                PathNode.QuadTo(353.0f, 305.0f, 389.0f, 295.0f),
                PathNode.LineTo(510.0f, 265.0f),
                PathNode.QuadTo(531.0f, 260.0f, 544.0f, 241.0f),
                PathNode.QuadTo(557.0f, 222.0f, 554.0f, 200.0f),
                PathNode.LineTo(529.0f, 15.0f),
                PathNode.QuadTo(524.0f, -25.0f, 540.5f, -60.0f),
                PathNode.QuadTo(557.0f, -95.0f, 589.5f, -116.0f),
                PathNode.QuadTo(622.0f, -137.0f, 662.0f, -137.0f),
                PathNode.QuadTo(702.0f, -137.0f, 734.5f, -116.0f),
                PathNode.QuadTo(767.0f, -95.0f, 783.5f, -60.0f),
                PathNode.QuadTo(800.0f, -25.0f, 795.0f, 15.0f),
                PathNode.Close,
                PathNode.MoveTo(588.0f, 7.0f),
                PathNode.LineTo(613.0f, 193.0f),
                PathNode.QuadTo(619.0f, 238.0f, 593.5f, 275.5f),
                PathNode.QuadTo(568.0f, 313.0f, 524.0f, 324.0f),
                PathNode.LineTo(406.0f, 354.0f),
                PathNode.QuadTo(382.0f, 361.0f, 358.0f, 368.0f),
                PathNode.QuadTo(351.0f, 370.0f, 347.0f, 375.5f),
                PathNode.QuadTo(343.0f, 381.0f, 341.0f, 391.0f),
                PathNode.QuadTo(339.0f, 400.0f, 334.0f, 457.0f),
                PathNode.LineTo(997.0f, 458.0f),
                PathNode.QuadTo(996.0f, 433.0f, 992.0f, 392.0f),
                PathNode.QuadTo(990.0f, 382.0f, 986.0f, 376.5f),
                PathNode.QuadTo(982.0f, 371.0f, 973.0f, 368.0f),
                PathNode.QuadTo(965.0f, 365.0f, 923.0f, 354.0f),
                PathNode.LineTo(802.0f, 324.0f),
                PathNode.QuadTo(758.0f, 313.0f, 732.0f, 275.5f),
                PathNode.QuadTo(706.0f, 238.0f, 712.0f, 192.0f),
                PathNode.LineTo(736.0f, 7.0f),
                PathNode.QuadTo(740.0f, -26.0f, 717.5f, -51.5f),
                PathNode.QuadTo(695.0f, -77.0f, 662.0f, -77.0f),
                PathNode.QuadTo(629.0f, -77.0f, 606.5f, -51.5f),
                PathNode.QuadTo(584.0f, -26.0f, 588.0f, 7.0f),
                PathNode.Close,
                PathNode.MoveTo(315.0f, 775.0f),
                PathNode.QuadTo(317.0f, 798.0f, 326.5f, 807.5f),
                PathNode.QuadTo(336.0f, 817.0f, 357.0f, 821.0f),
                PathNode.QuadTo(376.0f, 823.0f, 423.0f, 825.0f),
                PathNode.QuadTo(541.0f, 829.0f, 663.0f, 829.0f),
                PathNode.QuadTo(816.0f, 829.0f, 906.0f, 826.0f),
                PathNode.QuadTo(953.0f, 824.0f, 973.0f, 822.0f),
                PathNode.QuadTo(994.0f, 818.0f, 1004.5f, 805.0f),
                PathNode.QuadTo(1015.0f, 792.0f, 1014.0f, 770.0f),
                PathNode.LineTo(1011.0f, 707.0f),
                PathNode.LineTo(999.0f, 518.0f),
                PathNode.HorizontalTo(330.0f),
                PathNode.LineTo(317.0f, 706.0f),
                PathNode.QuadTo(314.0f, 757.0f, 315.0f, 775.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _themeLight!!
    }

private var _themeLight: ImageVector? = null

val MiuixIcons.Regular.Theme: ImageVector
    get() {
        if (_themeRegular != null) return _themeRegular!!
        _themeRegular = ImageVector.Builder(
            name = "Theme.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1264.8f,
            viewportHeight = 1264.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -32.075000000000045f, translationY = 1008.4f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(809.0f, 17.0f),
                PathNode.LineTo(785.0f, 202.0f),
                PathNode.QuadTo(783.0f, 219.0f, 792.5f, 233.0f),
                PathNode.QuadTo(802.0f, 247.0f, 819.0f, 252.0f),
                PathNode.LineTo(943.0f, 282.0f),
                PathNode.LineTo(955.0f, 285.0f),
                PathNode.QuadTo(966.0f, 288.0f, 977.0f, 290.5f),
                PathNode.QuadTo(988.0f, 293.0f, 997.0f, 297.0f),
                PathNode.QuadTo(1027.0f, 308.0f, 1043.0f, 328.0f),
                PathNode.QuadTo(1060.0f, 349.0f, 1065.0f, 380.0f),
                PathNode.QuadTo(1068.0f, 395.0f, 1068.0f, 413.0f),
                PathNode.LineTo(1070.0f, 435.0f),
                PathNode.LineTo(1085.0f, 705.0f),
                PathNode.QuadTo(1089.0f, 761.0f, 1088.0f, 780.0f),
                PathNode.QuadTo(1084.0f, 820.0f, 1067.0f, 846.0f),
                PathNode.QuadTo(1055.0f, 861.0f, 1047.0f, 867.0f),
                PathNode.QuadTo(1024.0f, 888.0f, 984.0f, 895.0f),
                PathNode.QuadTo(963.0f, 898.0f, 912.0f, 900.0f),
                PathNode.HorizontalTo(909.0f),
                PathNode.QuadTo(818.0f, 903.0f, 663.0f, 903.0f),
                PathNode.QuadTo(540.0f, 903.0f, 420.0f, 899.0f),
                PathNode.HorizontalTo(417.0f),
                PathNode.QuadTo(366.0f, 897.0f, 345.0f, 894.0f),
                PathNode.QuadTo(308.0f, 889.0f, 282.0f, 867.0f),
                PathNode.QuadTo(268.0f, 855.0f, 262.0f, 845.0f),
                PathNode.QuadTo(243.0f, 818.0f, 241.0f, 778.0f),
                PathNode.QuadTo(240.0f, 751.0f, 243.0f, 703.0f),
                PathNode.LineTo(262.0f, 431.0f),
                PathNode.QuadTo(264.0f, 401.0f, 268.0f, 377.0f),
                PathNode.QuadTo(273.0f, 346.0f, 289.0f, 328.0f),
                PathNode.QuadTo(307.0f, 308.0f, 335.0f, 297.0f),
                PathNode.QuadTo(352.0f, 291.0f, 386.0f, 282.0f),
                PathNode.LineTo(506.0f, 252.0f),
                PathNode.QuadTo(523.0f, 247.0f, 532.5f, 233.0f),
                PathNode.QuadTo(542.0f, 219.0f, 540.0f, 202.0f),
                PathNode.LineTo(515.0f, 17.0f),
                PathNode.QuadTo(509.0f, -27.0f, 527.5f, -65.5f),
                PathNode.QuadTo(546.0f, -104.0f, 582.0f, -127.5f),
                PathNode.QuadTo(618.0f, -151.0f, 662.0f, -151.0f),
                PathNode.QuadTo(706.0f, -151.0f, 742.0f, -127.5f),
                PathNode.QuadTo(778.0f, -104.0f, 796.5f, -65.5f),
                PathNode.QuadTo(815.0f, -27.0f, 809.0f, 17.0f),
                PathNode.Close,
                PathNode.MoveTo(602.0f, 5.0f),
                PathNode.LineTo(627.0f, 191.0f),
                PathNode.QuadTo(634.0f, 241.0f, 605.0f, 283.0f),
                PathNode.QuadTo(576.0f, 325.0f, 527.0f, 337.0f),
                PathNode.LineTo(410.0f, 367.0f),
                PathNode.QuadTo(377.0f, 376.0f, 363.0f, 381.0f),
                PathNode.QuadTo(359.0f, 383.0f, 357.0f, 385.5f),
                PathNode.QuadTo(355.0f, 388.0f, 354.0f, 394.0f),
                PathNode.QuadTo(352.0f, 402.0f, 349.0f, 443.0f),
                PathNode.LineTo(982.0f, 444.0f),
                PathNode.QuadTo(981.0f, 416.0f, 978.0f, 394.0f),
                PathNode.QuadTo(977.0f, 388.0f, 975.0f, 385.0f),
                PathNode.QuadTo(973.0f, 382.0f, 969.0f, 381.0f),
                PathNode.QuadTo(959.0f, 377.0f, 920.0f, 367.0f),
                PathNode.LineTo(799.0f, 337.0f),
                PathNode.QuadTo(749.0f, 325.0f, 720.0f, 283.0f),
                PathNode.QuadTo(691.0f, 241.0f, 698.0f, 190.0f),
                PathNode.LineTo(722.0f, 5.0f),
                PathNode.QuadTo(725.0f, -22.0f, 707.0f, -42.5f),
                PathNode.QuadTo(689.0f, -63.0f, 662.0f, -63.0f),
                PathNode.QuadTo(635.0f, -63.0f, 617.0f, -42.5f),
                PathNode.QuadTo(599.0f, -22.0f, 602.0f, 5.0f),
                PathNode.Close,
                PathNode.MoveTo(329.0f, 774.0f),
                PathNode.QuadTo(331.0f, 791.0f, 336.5f, 797.5f),
                PathNode.QuadTo(342.0f, 804.0f, 359.0f, 807.0f),
                PathNode.QuadTo(377.0f, 810.0f, 423.0f, 811.0f),
                PathNode.QuadTo(541.0f, 815.0f, 663.0f, 815.0f),
                PathNode.QuadTo(816.0f, 815.0f, 906.0f, 812.0f),
                PathNode.QuadTo(952.0f, 811.0f, 971.0f, 808.0f),
                PathNode.QuadTo(986.0f, 806.0f, 993.5f, 797.0f),
                PathNode.QuadTo(1001.0f, 788.0f, 1000.0f, 770.0f),
                PathNode.LineTo(997.0f, 708.0f),
                PathNode.LineTo(986.0f, 532.0f),
                PathNode.HorizontalTo(343.0f),
                PathNode.LineTo(331.0f, 707.0f),
                PathNode.QuadTo(328.0f, 757.0f, 329.0f, 774.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _themeRegular!!
    }

private var _themeRegular: ImageVector? = null

val MiuixIcons.Heavy.Theme: ImageVector
    get() {
        if (_themeHeavy != null) return _themeHeavy!!
        _themeHeavy = ImageVector.Builder(
            name = "Theme.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1288.8f,
            viewportHeight = 1288.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -20.075000000000045f, translationY = 1020.4f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(819.0f, 18.0f),
                PathNode.LineTo(795.0f, 203.0f),
                PathNode.QuadTo(794.0f, 216.0f, 801.0f, 227.0f),
                PathNode.QuadTo(808.0f, 238.0f, 821.0f, 242.0f),
                PathNode.LineTo(945.0f, 272.0f),
                PathNode.LineTo(957.0f, 275.0f),
                PathNode.QuadTo(969.0f, 278.0f, 981.0f, 281.0f),
                PathNode.QuadTo(993.0f, 284.0f, 1001.0f, 288.0f),
                PathNode.QuadTo(1033.0f, 300.0f, 1051.0f, 322.0f),
                PathNode.QuadTo(1070.0f, 345.0f, 1075.0f, 378.0f),
                PathNode.QuadTo(1078.0f, 393.0f, 1078.0f, 411.0f),
                PathNode.LineTo(1080.0f, 434.0f),
                PathNode.LineTo(1095.0f, 704.0f),
                PathNode.QuadTo(1099.0f, 761.0f, 1098.0f, 781.0f),
                PathNode.QuadTo(1094.0f, 823.0f, 1075.0f, 852.0f),
                PathNode.QuadTo(1062.0f, 867.0f, 1053.0f, 875.0f),
                PathNode.QuadTo(1028.0f, 898.0f, 985.0f, 905.0f),
                PathNode.QuadTo(965.0f, 908.0f, 912.0f, 910.0f),
                PathNode.HorizontalTo(909.0f),
                PathNode.QuadTo(818.0f, 913.0f, 663.0f, 913.0f),
                PathNode.QuadTo(540.0f, 913.0f, 420.0f, 909.0f),
                PathNode.HorizontalTo(417.0f),
                PathNode.QuadTo(364.0f, 907.0f, 344.0f, 904.0f),
                PathNode.QuadTo(305.0f, 899.0f, 276.0f, 875.0f),
                PathNode.QuadTo(261.0f, 862.0f, 253.0f, 851.0f),
                PathNode.QuadTo(233.0f, 822.0f, 231.0f, 778.0f),
                PathNode.QuadTo(230.0f, 751.0f, 233.0f, 702.0f),
                PathNode.LineTo(252.0f, 430.0f),
                PathNode.QuadTo(253.0f, 401.0f, 258.0f, 375.0f),
                PathNode.QuadTo(264.0f, 342.0f, 281.0f, 322.0f),
                PathNode.QuadTo(301.0f, 299.0f, 331.0f, 288.0f),
                PathNode.QuadTo(349.0f, 281.0f, 384.0f, 272.0f),
                PathNode.LineTo(503.0f, 242.0f),
                PathNode.QuadTo(516.0f, 238.0f, 524.0f, 227.5f),
                PathNode.QuadTo(532.0f, 217.0f, 530.0f, 203.0f),
                PathNode.LineTo(505.0f, 18.0f),
                PathNode.QuadTo(499.0f, -29.0f, 518.5f, -70.0f),
                PathNode.QuadTo(538.0f, -111.0f, 576.5f, -136.0f),
                PathNode.QuadTo(615.0f, -161.0f, 662.0f, -161.0f),
                PathNode.QuadTo(709.0f, -161.0f, 747.5f, -136.0f),
                PathNode.QuadTo(786.0f, -111.0f, 805.5f, -70.0f),
                PathNode.QuadTo(825.0f, -29.0f, 819.0f, 18.0f),
                PathNode.Close,
                PathNode.MoveTo(612.0f, 4.0f),
                PathNode.LineTo(637.0f, 190.0f),
                PathNode.QuadTo(644.0f, 244.0f, 613.0f, 289.0f),
                PathNode.QuadTo(582.0f, 334.0f, 529.0f, 347.0f),
                PathNode.LineTo(413.0f, 377.0f),
                PathNode.QuadTo(386.0f, 383.0f, 372.0f, 389.0f),
                PathNode.QuadTo(369.0f, 390.0f, 367.0f, 392.0f),
                PathNode.QuadTo(365.0f, 394.0f, 364.0f, 399.0f),
                PathNode.QuadTo(362.0f, 408.0f, 360.0f, 434.0f),
                PathNode.LineTo(971.0f, 435.0f),
                PathNode.QuadTo(970.0f, 418.0f, 968.0f, 400.0f),
                PathNode.QuadTo(967.0f, 395.0f, 965.5f, 392.5f),
                PathNode.QuadTo(964.0f, 390.0f, 960.0f, 388.0f),
                PathNode.QuadTo(939.0f, 383.0f, 918.0f, 377.0f),
                PathNode.LineTo(797.0f, 347.0f),
                PathNode.QuadTo(744.0f, 334.0f, 712.5f, 289.0f),
                PathNode.QuadTo(681.0f, 244.0f, 688.0f, 189.0f),
                PathNode.LineTo(712.0f, 4.0f),
                PathNode.QuadTo(714.0f, -19.0f, 699.0f, -36.0f),
                PathNode.QuadTo(684.0f, -53.0f, 662.0f, -53.0f),
                PathNode.QuadTo(640.0f, -53.0f, 625.0f, -36.0f),
                PathNode.QuadTo(610.0f, -19.0f, 612.0f, 4.0f),
                PathNode.Close,
                PathNode.MoveTo(339.0f, 773.0f),
                PathNode.QuadTo(341.0f, 786.0f, 344.5f, 790.5f),
                PathNode.QuadTo(348.0f, 795.0f, 360.0f, 797.0f),
                PathNode.QuadTo(378.0f, 800.0f, 423.0f, 801.0f),
                PathNode.QuadTo(541.0f, 805.0f, 663.0f, 805.0f),
                PathNode.QuadTo(816.0f, 805.0f, 906.0f, 802.0f),
                PathNode.QuadTo(951.0f, 801.0f, 970.0f, 798.0f),
                PathNode.QuadTo(981.0f, 797.0f, 985.5f, 790.5f),
                PathNode.QuadTo(990.0f, 784.0f, 990.0f, 770.0f),
                PathNode.LineTo(987.0f, 708.0f),
                PathNode.LineTo(977.0f, 540.0f),
                PathNode.HorizontalTo(352.0f),
                PathNode.LineTo(341.0f, 708.0f),
                PathNode.QuadTo(338.0f, 757.0f, 339.0f, 773.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _themeHeavy!!
    }

private var _themeHeavy: ImageVector? = null
