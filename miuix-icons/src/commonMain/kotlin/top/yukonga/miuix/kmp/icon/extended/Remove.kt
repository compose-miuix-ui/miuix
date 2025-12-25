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

val MiuixIcons.Remove: ImageVector
    get() = MiuixIcons.Regular.Remove

val MiuixIcons.Light.Remove: ImageVector
    get() {
        if (_removeLight != null) return _removeLight!!
        _removeLight = ImageVector.Builder(
            name = "Remove.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1280.3999999999999f,
            viewportHeight = 1280.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -25.300000000000068f, translationY = 1016.1999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(724.0f, -70.0f),
                PathNode.QuadTo(774.0f, -43.0f, 798.0f, 3.0f),
                PathNode.QuadTo(812.0f, 30.0f, 815.0f, 67.0f),
                PathNode.QuadTo(818.0f, 104.0f, 818.0f, 184.0f),
                PathNode.VerticalTo(218.0f),
                PathNode.QuadTo(818.0f, 225.0f, 813.5f, 230.5f),
                PathNode.QuadTo(809.0f, 236.0f, 800.0f, 236.0f),
                PathNode.HorizontalTo(778.0f),
                PathNode.QuadTo(768.0f, 236.0f, 763.5f, 231.0f),
                PathNode.QuadTo(759.0f, 226.0f, 759.0f, 218.0f),
                PathNode.VerticalTo(138.0f),
                PathNode.QuadTo(759.0f, 97.0f, 756.5f, 73.5f),
                PathNode.QuadTo(754.0f, 50.0f, 745.0f, 33.0f),
                PathNode.QuadTo(725.0f, -2.0f, 694.0f, -18.0f),
                PathNode.QuadTo(675.0f, -28.0f, 650.5f, -30.0f),
                PathNode.QuadTo(626.0f, -32.0f, 569.0f, -32.0f),
                PathNode.HorizontalTo(382.0f),
                PathNode.QuadTo(325.0f, -32.0f, 299.5f, -30.0f),
                PathNode.QuadTo(274.0f, -28.0f, 256.0f, -18.0f),
                PathNode.QuadTo(225.0f, -3.0f, 205.0f, 33.0f),
                PathNode.QuadTo(195.0f, 51.0f, 193.0f, 76.5f),
                PathNode.QuadTo(191.0f, 102.0f, 191.0f, 158.0f),
                PathNode.VerticalTo(594.0f),
                PathNode.QuadTo(191.0f, 650.0f, 193.0f, 675.5f),
                PathNode.QuadTo(195.0f, 701.0f, 205.0f, 719.0f),
                PathNode.QuadTo(225.0f, 755.0f, 256.0f, 770.0f),
                PathNode.QuadTo(274.0f, 780.0f, 299.5f, 782.0f),
                PathNode.QuadTo(325.0f, 784.0f, 382.0f, 784.0f),
                PathNode.HorizontalTo(569.0f),
                PathNode.QuadTo(626.0f, 784.0f, 650.5f, 782.0f),
                PathNode.QuadTo(675.0f, 780.0f, 694.0f, 770.0f),
                PathNode.QuadTo(725.0f, 754.0f, 745.0f, 719.0f),
                PathNode.QuadTo(754.0f, 702.0f, 756.5f, 678.5f),
                PathNode.QuadTo(759.0f, 655.0f, 759.0f, 614.0f),
                PathNode.VerticalTo(535.0f),
                PathNode.QuadTo(759.0f, 527.0f, 764.0f, 522.0f),
                PathNode.QuadTo(769.0f, 517.0f, 778.0f, 517.0f),
                PathNode.HorizontalTo(799.0f),
                PathNode.QuadTo(807.0f, 517.0f, 812.5f, 522.0f),
                PathNode.QuadTo(818.0f, 527.0f, 818.0f, 535.0f),
                PathNode.VerticalTo(571.0f),
                PathNode.QuadTo(818.0f, 651.0f, 815.0f, 687.0f),
                PathNode.QuadTo(812.0f, 723.0f, 798.0f, 749.0f),
                PathNode.QuadTo(773.0f, 797.0f, 724.0f, 822.0f),
                PathNode.QuadTo(697.0f, 836.0f, 658.0f, 839.5f),
                PathNode.QuadTo(619.0f, 843.0f, 524.0f, 843.0f),
                PathNode.HorizontalTo(427.0f),
                PathNode.QuadTo(332.0f, 843.0f, 292.5f, 839.5f),
                PathNode.QuadTo(253.0f, 836.0f, 226.0f, 822.0f),
                PathNode.QuadTo(177.0f, 797.0f, 152.0f, 749.0f),
                PathNode.QuadTo(138.0f, 723.0f, 135.0f, 684.0f),
                PathNode.QuadTo(132.0f, 645.0f, 132.0f, 549.0f),
                PathNode.VerticalTo(204.0f),
                PathNode.QuadTo(132.0f, 109.0f, 135.5f, 69.5f),
                PathNode.QuadTo(139.0f, 30.0f, 153.0f, 3.0f),
                PathNode.QuadTo(176.0f, -42.0f, 226.0f, -70.0f),
                PathNode.QuadTo(253.0f, -84.0f, 292.5f, -87.5f),
                PathNode.QuadTo(332.0f, -91.0f, 427.0f, -91.0f),
                PathNode.HorizontalTo(522.0f),
                PathNode.QuadTo(619.0f, -91.0f, 658.0f, -87.5f),
                PathNode.QuadTo(697.0f, -84.0f, 724.0f, -70.0f),
                PathNode.Close,
                PathNode.MoveTo(443.0f, 388.0f),
                PathNode.VerticalTo(365.0f),
                PathNode.QuadTo(443.0f, 358.0f, 448.5f, 352.5f),
                PathNode.QuadTo(454.0f, 347.0f, 461.0f, 347.0f),
                PathNode.HorizontalTo(1097.0f),
                PathNode.LineTo(932.0f, 181.0f),
                PathNode.QuadTo(926.0f, 176.0f, 925.0f, 169.0f),
                PathNode.QuadTo(924.0f, 162.0f, 931.0f, 155.0f),
                PathNode.LineTo(944.0f, 141.0f),
                PathNode.QuadTo(952.0f, 133.0f, 960.0f, 133.5f),
                PathNode.QuadTo(968.0f, 134.0f, 973.0f, 139.0f),
                PathNode.LineTo(1186.0f, 353.0f),
                PathNode.QuadTo(1199.0f, 366.0f, 1199.0f, 376.5f),
                PathNode.QuadTo(1199.0f, 387.0f, 1187.0f, 398.0f),
                PathNode.LineTo(972.0f, 613.0f),
                PathNode.QuadTo(967.0f, 618.0f, 960.0f, 619.0f),
                PathNode.QuadTo(953.0f, 620.0f, 946.0f, 613.0f),
                PathNode.LineTo(931.0f, 598.0f),
                PathNode.QuadTo(925.0f, 592.0f, 925.0f, 585.5f),
                PathNode.QuadTo(925.0f, 579.0f, 930.0f, 573.0f),
                PathNode.LineTo(1096.0f, 406.0f),
                PathNode.HorizontalTo(461.0f),
                PathNode.QuadTo(454.0f, 406.0f, 448.5f, 401.0f),
                PathNode.QuadTo(443.0f, 396.0f, 443.0f, 388.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _removeLight!!
    }

private var _removeLight: ImageVector? = null

val MiuixIcons.Regular.Remove: ImageVector
    get() {
        if (_removeRegular != null) return _removeRegular!!
        _removeRegular = ImageVector.Builder(
            name = "Remove.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1312.2162162162163f,
            viewportHeight = 1312.2162162162163f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -9.648648648648646f, translationY = 1032.1081081081081f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(731.0f, -82.0f),
                PathNode.QuadTo(783.0f, -53.0f, 810.0f, -3.0f),
                PathNode.QuadTo(825.0f, 26.0f, 828.5f, 64.5f),
                PathNode.QuadTo(832.0f, 103.0f, 832.0f, 184.0f),
                PathNode.VerticalTo(209.0f),
                PathNode.QuadTo(832.0f, 220.0f, 824.5f, 228.0f),
                PathNode.QuadTo(817.0f, 236.0f, 804.0f, 236.0f),
                PathNode.HorizontalTo(775.0f),
                PathNode.QuadTo(760.0f, 236.0f, 753.0f, 228.5f),
                PathNode.QuadTo(746.0f, 221.0f, 746.0f, 209.0f),
                PathNode.VerticalTo(138.0f),
                PathNode.QuadTo(746.0f, 98.0f, 744.0f, 76.5f),
                PathNode.QuadTo(742.0f, 55.0f, 734.0f, 39.0f),
                PathNode.QuadTo(717.0f, 9.0f, 688.0f, -6.0f),
                PathNode.QuadTo(671.0f, -15.0f, 648.0f, -17.0f),
                PathNode.QuadTo(625.0f, -19.0f, 569.0f, -19.0f),
                PathNode.HorizontalTo(382.0f),
                PathNode.QuadTo(326.0f, -19.0f, 303.0f, -17.0f),
                PathNode.QuadTo(280.0f, -15.0f, 263.0f, -6.0f),
                PathNode.QuadTo(235.0f, 8.0f, 217.0f, 39.0f),
                PathNode.QuadTo(209.0f, 55.0f, 207.0f, 79.0f),
                PathNode.QuadTo(205.0f, 103.0f, 205.0f, 158.0f),
                PathNode.VerticalTo(594.0f),
                PathNode.QuadTo(205.0f, 649.0f, 207.0f, 673.0f),
                PathNode.QuadTo(209.0f, 697.0f, 217.0f, 713.0f),
                PathNode.QuadTo(235.0f, 744.0f, 263.0f, 758.0f),
                PathNode.QuadTo(280.0f, 767.0f, 303.0f, 769.0f),
                PathNode.QuadTo(326.0f, 771.0f, 382.0f, 771.0f),
                PathNode.HorizontalTo(569.0f),
                PathNode.QuadTo(625.0f, 771.0f, 648.0f, 769.0f),
                PathNode.QuadTo(671.0f, 767.0f, 688.0f, 758.0f),
                PathNode.QuadTo(717.0f, 743.0f, 734.0f, 713.0f),
                PathNode.QuadTo(742.0f, 697.0f, 744.0f, 675.5f),
                PathNode.QuadTo(746.0f, 654.0f, 746.0f, 614.0f),
                PathNode.VerticalTo(544.0f),
                PathNode.QuadTo(746.0f, 531.0f, 753.5f, 524.0f),
                PathNode.QuadTo(761.0f, 517.0f, 774.0f, 517.0f),
                PathNode.HorizontalTo(803.0f),
                PathNode.QuadTo(816.0f, 517.0f, 824.0f, 524.0f),
                PathNode.QuadTo(832.0f, 531.0f, 832.0f, 544.0f),
                PathNode.VerticalTo(571.0f),
                PathNode.QuadTo(832.0f, 651.0f, 828.5f, 689.0f),
                PathNode.QuadTo(825.0f, 727.0f, 810.0f, 755.0f),
                PathNode.QuadTo(782.0f, 808.0f, 731.0f, 834.0f),
                PathNode.QuadTo(702.0f, 849.0f, 661.0f, 852.5f),
                PathNode.QuadTo(620.0f, 856.0f, 524.0f, 856.0f),
                PathNode.HorizontalTo(427.0f),
                PathNode.QuadTo(331.0f, 856.0f, 290.0f, 852.5f),
                PathNode.QuadTo(249.0f, 849.0f, 220.0f, 834.0f),
                PathNode.QuadTo(169.0f, 808.0f, 141.0f, 755.0f),
                PathNode.QuadTo(126.0f, 727.0f, 122.5f, 686.0f),
                PathNode.QuadTo(119.0f, 645.0f, 119.0f, 549.0f),
                PathNode.VerticalTo(204.0f),
                PathNode.QuadTo(119.0f, 108.0f, 122.5f, 67.0f),
                PathNode.QuadTo(126.0f, 26.0f, 141.0f, -3.0f),
                PathNode.QuadTo(168.0f, -53.0f, 220.0f, -82.0f),
                PathNode.QuadTo(249.0f, -97.0f, 290.0f, -100.5f),
                PathNode.QuadTo(331.0f, -104.0f, 427.0f, -104.0f),
                PathNode.HorizontalTo(522.0f),
                PathNode.QuadTo(620.0f, -104.0f, 661.0f, -100.5f),
                PathNode.QuadTo(702.0f, -97.0f, 731.0f, -82.0f),
                PathNode.Close,
                PathNode.MoveTo(443.0f, 392.0f),
                PathNode.VerticalTo(360.0f),
                PathNode.QuadTo(443.0f, 349.0f, 451.0f, 341.5f),
                PathNode.QuadTo(459.0f, 334.0f, 470.0f, 334.0f),
                PathNode.HorizontalTo(1065.0f),
                PathNode.LineTo(929.0f, 197.0f),
                PathNode.QuadTo(921.0f, 189.0f, 919.5f, 178.5f),
                PathNode.QuadTo(918.0f, 168.0f, 928.0f, 158.0f),
                PathNode.LineTo(945.0f, 140.0f),
                PathNode.QuadTo(957.0f, 127.0f, 969.0f, 127.5f),
                PathNode.QuadTo(981.0f, 128.0f, 989.0f, 136.0f),
                PathNode.LineTo(1195.0f, 343.0f),
                PathNode.QuadTo(1213.0f, 360.0f, 1212.5f, 376.5f),
                PathNode.QuadTo(1212.0f, 393.0f, 1196.0f, 408.0f),
                PathNode.LineTo(988.0f, 616.0f),
                PathNode.QuadTo(980.0f, 624.0f, 969.5f, 625.5f),
                PathNode.QuadTo(959.0f, 627.0f, 949.0f, 617.0f),
                PathNode.LineTo(928.0f, 596.0f),
                PathNode.QuadTo(918.0f, 586.0f, 918.5f, 576.0f),
                PathNode.QuadTo(919.0f, 566.0f, 926.0f, 558.0f),
                PathNode.LineTo(1064.0f, 419.0f),
                PathNode.HorizontalTo(470.0f),
                PathNode.QuadTo(459.0f, 419.0f, 451.0f, 411.0f),
                PathNode.QuadTo(443.0f, 403.0f, 443.0f, 392.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _removeRegular!!
    }

private var _removeRegular: ImageVector? = null

val MiuixIcons.Heavy.Remove: ImageVector
    get() {
        if (_removeHeavy != null) return _removeHeavy!!
        _removeHeavy = ImageVector.Builder(
            name = "Remove.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1361.4139534883723f,
            viewportHeight = 1361.4139534883723f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 15.451162790697708f, translationY = 1056.7069767441862f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(731.0f, -94.0f),
                PathNode.QuadTo(786.0f, -64.0f, 816.0f, -9.0f),
                PathNode.QuadTo(831.0f, 21.0f, 835.0f, 59.5f),
                PathNode.QuadTo(839.0f, 98.0f, 839.0f, 166.0f),
                PathNode.VerticalTo(181.0f),
                PathNode.QuadTo(839.0f, 198.0f, 827.5f, 210.0f),
                PathNode.QuadTo(816.0f, 222.0f, 797.0f, 222.0f),
                PathNode.HorizontalTo(768.0f),
                PathNode.QuadTo(747.0f, 222.0f, 736.0f, 210.5f),
                PathNode.QuadTo(725.0f, 199.0f, 725.0f, 181.0f),
                PathNode.VerticalTo(129.0f),
                PathNode.QuadTo(725.0f, 97.0f, 723.5f, 78.5f),
                PathNode.QuadTo(722.0f, 60.0f, 715.0f, 45.0f),
                PathNode.QuadTo(699.0f, 20.0f, 675.0f, 6.0f),
                PathNode.QuadTo(660.0f, -2.0f, 638.5f, -3.5f),
                PathNode.QuadTo(617.0f, -5.0f, 562.0f, -5.0f),
                PathNode.HorizontalTo(375.0f),
                PathNode.QuadTo(321.0f, -5.0f, 299.0f, -3.5f),
                PathNode.QuadTo(277.0f, -2.0f, 263.0f, 6.0f),
                PathNode.QuadTo(237.0f, 19.0f, 222.0f, 45.0f),
                PathNode.QuadTo(215.0f, 59.0f, 213.5f, 81.5f),
                PathNode.QuadTo(212.0f, 104.0f, 212.0f, 158.0f),
                PathNode.VerticalTo(594.0f),
                PathNode.QuadTo(212.0f, 649.0f, 213.5f, 671.0f),
                PathNode.QuadTo(215.0f, 693.0f, 222.0f, 707.0f),
                PathNode.QuadTo(237.0f, 733.0f, 263.0f, 746.0f),
                PathNode.QuadTo(277.0f, 754.0f, 299.0f, 755.5f),
                PathNode.QuadTo(321.0f, 757.0f, 375.0f, 757.0f),
                PathNode.HorizontalTo(562.0f),
                PathNode.QuadTo(617.0f, 757.0f, 638.5f, 755.5f),
                PathNode.QuadTo(660.0f, 754.0f, 675.0f, 746.0f),
                PathNode.QuadTo(699.0f, 732.0f, 715.0f, 707.0f),
                PathNode.QuadTo(722.0f, 692.0f, 723.5f, 674.0f),
                PathNode.QuadTo(725.0f, 656.0f, 725.0f, 623.0f),
                PathNode.VerticalTo(572.0f),
                PathNode.QuadTo(725.0f, 554.0f, 737.0f, 542.5f),
                PathNode.QuadTo(749.0f, 531.0f, 767.0f, 531.0f),
                PathNode.HorizontalTo(796.0f),
                PathNode.QuadTo(814.0f, 531.0f, 826.5f, 542.5f),
                PathNode.QuadTo(839.0f, 554.0f, 839.0f, 572.0f),
                PathNode.VerticalTo(589.0f),
                PathNode.QuadTo(839.0f, 657.0f, 835.0f, 694.0f),
                PathNode.QuadTo(831.0f, 731.0f, 815.0f, 761.0f),
                PathNode.QuadTo(786.0f, 818.0f, 731.0f, 846.0f),
                PathNode.QuadTo(699.0f, 862.0f, 656.5f, 866.0f),
                PathNode.QuadTo(614.0f, 870.0f, 517.0f, 870.0f),
                PathNode.HorizontalTo(429.0f),
                PathNode.QuadTo(325.0f, 870.0f, 281.5f, 866.5f),
                PathNode.QuadTo(238.0f, 863.0f, 207.0f, 846.0f),
                PathNode.QuadTo(151.0f, 818.0f, 122.0f, 761.0f),
                PathNode.QuadTo(106.0f, 731.0f, 102.0f, 688.5f),
                PathNode.QuadTo(98.0f, 646.0f, 98.0f, 549.0f),
                PathNode.VerticalTo(204.0f),
                PathNode.QuadTo(98.0f, 107.0f, 102.0f, 64.5f),
                PathNode.QuadTo(106.0f, 22.0f, 122.0f, -9.0f),
                PathNode.QuadTo(150.0f, -64.0f, 207.0f, -94.0f),
                PathNode.QuadTo(238.0f, -110.0f, 280.5f, -114.0f),
                PathNode.QuadTo(323.0f, -118.0f, 420.0f, -118.0f),
                PathNode.HorizontalTo(515.0f),
                PathNode.QuadTo(614.0f, -118.0f, 656.5f, -114.0f),
                PathNode.QuadTo(699.0f, -110.0f, 731.0f, -94.0f),
                PathNode.Close,
                PathNode.MoveTo(445.0f, 392.0f),
                PathNode.VerticalTo(360.0f),
                PathNode.QuadTo(445.0f, 343.0f, 457.0f, 331.5f),
                PathNode.QuadTo(469.0f, 320.0f, 486.0f, 320.0f),
                PathNode.HorizontalTo(1042.0f),
                PathNode.LineTo(927.0f, 206.0f),
                PathNode.QuadTo(914.0f, 193.0f, 912.5f, 177.5f),
                PathNode.QuadTo(911.0f, 162.0f, 925.0f, 147.0f),
                PathNode.LineTo(942.0f, 130.0f),
                PathNode.QuadTo(957.0f, 113.0f, 975.0f, 113.0f),
                PathNode.QuadTo(993.0f, 113.0f, 1006.0f, 125.0f),
                PathNode.LineTo(1211.0f, 333.0f),
                PathNode.QuadTo(1232.0f, 353.0f, 1232.5f, 375.5f),
                PathNode.QuadTo(1233.0f, 398.0f, 1212.0f, 418.0f),
                PathNode.LineTo(1005.0f, 627.0f),
                PathNode.QuadTo(992.0f, 639.0f, 976.5f, 640.5f),
                PathNode.QuadTo(961.0f, 642.0f, 947.0f, 628.0f),
                PathNode.LineTo(926.0f, 607.0f),
                PathNode.QuadTo(912.0f, 593.0f, 912.0f, 577.0f),
                PathNode.QuadTo(912.0f, 561.0f, 923.0f, 549.0f),
                PathNode.LineTo(1041.0f, 433.0f),
                PathNode.HorizontalTo(486.0f),
                PathNode.QuadTo(469.0f, 433.0f, 457.0f, 421.0f),
                PathNode.QuadTo(445.0f, 409.0f, 445.0f, 392.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _removeHeavy!!
    }

private var _removeHeavy: ImageVector? = null
