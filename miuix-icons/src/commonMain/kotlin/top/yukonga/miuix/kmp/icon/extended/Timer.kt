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

val MiuixIcons.Timer: ImageVector
    get() = MiuixIcons.Regular.Timer

val MiuixIcons.Light.Timer: ImageVector
    get() {
        if (_timerLight != null) return _timerLight!!
        _timerLight = ImageVector.Builder(
            name = "Timer.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1272.0f,
            viewportHeight = 1272.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1272.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(321.0f, 133.0f),
                        PathNode.QuadTo(305.0f, 152.0f, 304.0f, 177.5f),
                        PathNode.QuadTo(303.0f, 203.0f, 311.0f, 263.0f),
                        PathNode.QuadTo(333.0f, 428.0f, 451.0f, 532.0f),
                        PathNode.QuadTo(496.0f, 571.0f, 512.5f, 587.0f),
                        PathNode.QuadTo(529.0f, 603.0f, 533.0f, 615.0f),
                        PathNode.QuadTo(538.0f, 628.0f, 538.0f, 636.0f),
                        PathNode.QuadTo(538.0f, 644.0f, 533.0f, 658.0f),
                        PathNode.QuadTo(529.0f, 669.0f, 511.0f, 686.0f),
                        PathNode.QuadTo(493.0f, 703.0f, 451.0f, 740.0f),
                        PathNode.QuadTo(333.0f, 843.0f, 311.0f, 1009.0f),
                        PathNode.QuadTo(303.0f, 1068.0f, 304.0f, 1093.5f),
                        PathNode.QuadTo(305.0f, 1119.0f, 321.0f, 1138.0f),
                        PathNode.QuadTo(337.0f, 1157.0f, 366.0f, 1161.5f),
                        PathNode.QuadTo(395.0f, 1166.0f, 469.0f, 1166.0f),
                        PathNode.HorizontalTo(803.0f),
                        PathNode.QuadTo(876.0f, 1166.0f, 905.0f, 1161.5f),
                        PathNode.QuadTo(934.0f, 1157.0f, 950.0f, 1138.0f),
                        PathNode.QuadTo(967.0f, 1120.0f, 968.0f, 1095.5f),
                        PathNode.QuadTo(969.0f, 1071.0f, 960.0f, 1009.0f),
                        PathNode.QuadTo(939.0f, 844.0f, 822.0f, 740.0f),
                        PathNode.LineTo(802.0f, 723.0f),
                        PathNode.QuadTo(769.0f, 693.0f, 755.5f, 679.5f),
                        PathNode.QuadTo(742.0f, 666.0f, 739.0f, 656.0f),
                        PathNode.QuadTo(735.0f, 643.0f, 735.0f, 635.5f),
                        PathNode.QuadTo(735.0f, 628.0f, 739.0f, 615.0f),
                        PathNode.QuadTo(743.0f, 605.0f, 757.5f, 590.0f),
                        PathNode.QuadTo(772.0f, 575.0f, 808.0f, 544.0f),
                        PathNode.LineTo(822.0f, 532.0f),
                        PathNode.QuadTo(939.0f, 428.0f, 960.0f, 263.0f),
                        PathNode.QuadTo(969.0f, 201.0f, 968.0f, 176.5f),
                        PathNode.QuadTo(967.0f, 152.0f, 950.0f, 133.0f),
                        PathNode.QuadTo(934.0f, 115.0f, 905.0f, 110.5f),
                        PathNode.QuadTo(876.0f, 106.0f, 803.0f, 106.0f),
                        PathNode.HorizontalTo(469.0f),
                        PathNode.QuadTo(395.0f, 106.0f, 366.0f, 110.5f),
                        PathNode.QuadTo(337.0f, 115.0f, 321.0f, 133.0f),
                        PathNode.Close,
                        PathNode.MoveTo(882.0f, 965.0f),
                        PathNode.QuadTo(888.0f, 991.0f, 880.0f, 1003.0f),
                        PathNode.QuadTo(872.0f, 1015.0f, 847.0f, 1015.0f),
                        PathNode.HorizontalTo(420.0f),
                        PathNode.QuadTo(398.0f, 1015.0f, 391.0f, 1002.0f),
                        PathNode.QuadTo(384.0f, 989.0f, 390.0f, 964.0f),
                        PathNode.QuadTo(411.0f, 883.0f, 465.5f, 818.0f),
                        PathNode.QuadTo(520.0f, 753.0f, 596.0f, 712.0f),
                        PathNode.QuadTo(621.0f, 699.0f, 636.5f, 699.0f),
                        PathNode.QuadTo(652.0f, 699.0f, 677.0f, 712.0f),
                        PathNode.QuadTo(753.0f, 754.0f, 807.5f, 819.5f),
                        PathNode.QuadTo(862.0f, 885.0f, 882.0f, 965.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerLight!!
    }

private var _timerLight: ImageVector? = null

val MiuixIcons.Normal.Timer: ImageVector
    get() {
        if (_timerNormal != null) return _timerNormal!!
        _timerNormal = ImageVector.Builder(
            name = "Timer.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1290.1f,
            viewportHeight = 1290.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1290.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(324.2f, 137.9f),
                        PathNode.QuadTo(306.2f, 159.7f, 304.5f, 186.9f),
                        PathNode.QuadTo(302.8f, 214.1f, 311.5f, 275.5f),
                        PathNode.QuadTo(334.9f, 444.6f, 454.2f, 549.3f),
                        PathNode.QuadTo(497.9f, 587.6f, 513.7f, 602.9f),
                        PathNode.QuadTo(529.5f, 618.3f, 532.8f, 627.5f),
                        PathNode.QuadTo(537.1f, 639.1f, 537.1f, 645.1f),
                        PathNode.QuadTo(537.1f, 651.0f, 532.8f, 662.9f),
                        PathNode.QuadTo(529.5f, 671.9f, 512.2f, 688.5f),
                        PathNode.QuadTo(494.9f, 705.2f, 454.2f, 740.8f),
                        PathNode.QuadTo(334.9f, 844.5f, 311.5f, 1014.6f),
                        PathNode.QuadTo(302.8f, 1075.0f, 304.5f, 1102.2f),
                        PathNode.QuadTo(306.2f, 1129.4f, 324.2f, 1151.2f),
                        PathNode.QuadTo(342.3f, 1172.9f, 373.0f, 1177.8f),
                        PathNode.QuadTo(403.7f, 1182.6f, 479.1f, 1182.6f),
                        PathNode.HorizontalTo(811.7f),
                        PathNode.QuadTo(886.1f, 1182.6f, 916.8f, 1177.8f),
                        PathNode.QuadTo(947.5f, 1172.9f, 965.6f, 1151.2f),
                        PathNode.QuadTo(984.7f, 1130.4f, 986.0f, 1103.9f),
                        PathNode.QuadTo(987.4f, 1077.3f, 978.4f, 1014.6f),
                        PathNode.QuadTo(956.0f, 845.5f, 837.6f, 740.8f),
                        PathNode.LineTo(818.3f, 724.5f),
                        PathNode.QuadTo(791.5f, 700.0f, 775.9f, 684.8f),
                        PathNode.QuadTo(760.4f, 669.6f, 758.0f, 661.6f),
                        PathNode.QuadTo(754.0f, 650.7f, 754.0f, 644.9f),
                        PathNode.QuadTo(754.0f, 639.1f, 758.0f, 627.5f),
                        PathNode.QuadTo(761.4f, 618.9f, 775.5f, 604.9f),
                        PathNode.QuadTo(789.7f, 590.9f, 824.3f, 561.3f),
                        PathNode.LineTo(837.6f, 549.3f),
                        PathNode.QuadTo(956.0f, 444.6f, 978.4f, 275.5f),
                        PathNode.QuadTo(987.4f, 212.8f, 986.0f, 186.3f),
                        PathNode.QuadTo(984.7f, 159.7f, 965.6f, 137.9f),
                        PathNode.QuadTo(947.5f, 117.2f, 916.8f, 112.4f),
                        PathNode.QuadTo(886.1f, 107.5f, 811.7f, 107.5f),
                        PathNode.HorizontalTo(479.1f),
                        PathNode.QuadTo(403.7f, 107.5f, 373.0f, 112.4f),
                        PathNode.QuadTo(342.3f, 117.2f, 324.2f, 137.9f),
                        PathNode.Close,
                        PathNode.MoveTo(881.1f, 968.6f),
                        PathNode.QuadTo(887.1f, 990.4f, 878.8f, 1002.1f),
                        PathNode.QuadTo(870.4f, 1013.8f, 848.2f, 1013.8f),
                        PathNode.HorizontalTo(440.4f),
                        PathNode.QuadTo(418.4f, 1013.8f, 411.1f, 1002.1f),
                        PathNode.QuadTo(403.7f, 990.5f, 409.0f, 970.3f),
                        PathNode.QuadTo(430.0f, 892.1f, 483.2f, 829.1f),
                        PathNode.QuadTo(536.3f, 766.2f, 609.5f, 727.3f),
                        PathNode.QuadTo(632.5f, 715.6f, 645.9f, 715.6f),
                        PathNode.QuadTo(659.4f, 715.6f, 682.3f, 727.3f),
                        PathNode.QuadTo(754.9f, 765.8f, 807.6f, 829.6f),
                        PathNode.QuadTo(860.4f, 893.4f, 881.1f, 968.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerNormal!!
    }

private var _timerNormal: ImageVector? = null

val MiuixIcons.Regular.Timer: ImageVector
    get() {
        if (_timerRegular != null) return _timerRegular!!
        _timerRegular = ImageVector.Builder(
            name = "Timer.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1298.4f,
            viewportHeight = 1298.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1298.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(325.7f, 140.2f),
                        PathNode.QuadTo(306.7f, 163.2f, 304.7f, 191.2f),
                        PathNode.QuadTo(302.7f, 219.2f, 311.7f, 281.2f),
                        PathNode.QuadTo(335.7f, 452.2f, 455.7f, 557.2f),
                        PathNode.QuadTo(498.7f, 595.2f, 514.2f, 610.2f),
                        PathNode.QuadTo(529.7f, 625.2f, 532.7f, 633.2f),
                        PathNode.QuadTo(536.7f, 644.2f, 536.7f, 649.2f),
                        PathNode.QuadTo(536.7f, 654.2f, 532.7f, 665.2f),
                        PathNode.QuadTo(529.7f, 673.2f, 512.7f, 689.7f),
                        PathNode.QuadTo(495.7f, 706.2f, 455.7f, 741.2f),
                        PathNode.QuadTo(335.7f, 845.2f, 311.7f, 1017.2f),
                        PathNode.QuadTo(302.7f, 1078.2f, 304.7f, 1106.2f),
                        PathNode.QuadTo(306.7f, 1134.2f, 325.7f, 1157.2f),
                        PathNode.QuadTo(344.7f, 1180.2f, 376.2f, 1185.2f),
                        PathNode.QuadTo(407.7f, 1190.2f, 483.7f, 1190.2f),
                        PathNode.HorizontalTo(815.7f),
                        PathNode.QuadTo(890.7f, 1190.2f, 922.2f, 1185.2f),
                        PathNode.QuadTo(953.7f, 1180.2f, 972.7f, 1157.2f),
                        PathNode.QuadTo(992.7f, 1135.2f, 994.2f, 1107.7f),
                        PathNode.QuadTo(995.7f, 1080.2f, 986.7f, 1017.2f),
                        PathNode.QuadTo(963.7f, 846.2f, 844.7f, 741.2f),
                        PathNode.LineTo(825.7f, 725.2f),
                        PathNode.QuadTo(801.7f, 703.2f, 785.2f, 687.2f),
                        PathNode.QuadTo(768.7f, 671.2f, 766.7f, 664.2f),
                        PathNode.QuadTo(762.7f, 654.2f, 762.7f, 649.2f),
                        PathNode.QuadTo(762.7f, 644.2f, 766.7f, 633.2f),
                        PathNode.QuadTo(769.7f, 625.2f, 783.7f, 611.7f),
                        PathNode.QuadTo(797.7f, 598.2f, 831.7f, 569.2f),
                        PathNode.LineTo(844.7f, 557.2f),
                        PathNode.QuadTo(963.7f, 452.2f, 986.7f, 281.2f),
                        PathNode.QuadTo(995.7f, 218.2f, 994.2f, 190.7f),
                        PathNode.QuadTo(992.7f, 163.2f, 972.7f, 140.2f),
                        PathNode.QuadTo(953.7f, 118.2f, 922.2f, 113.2f),
                        PathNode.QuadTo(890.7f, 108.2f, 815.7f, 108.2f),
                        PathNode.HorizontalTo(483.7f),
                        PathNode.QuadTo(407.7f, 108.2f, 376.2f, 113.2f),
                        PathNode.QuadTo(344.7f, 118.2f, 325.7f, 140.2f),
                        PathNode.Close,
                        PathNode.MoveTo(880.7f, 970.2f),
                        PathNode.QuadTo(886.7f, 990.2f, 878.2f, 1001.7f),
                        PathNode.QuadTo(869.7f, 1013.2f, 848.7f, 1013.2f),
                        PathNode.HorizontalTo(449.7f),
                        PathNode.QuadTo(427.7f, 1013.2f, 420.2f, 1002.2f),
                        PathNode.QuadTo(412.7f, 991.2f, 417.7f, 973.2f),
                        PathNode.QuadTo(438.7f, 896.2f, 491.2f, 834.2f),
                        PathNode.QuadTo(543.7f, 772.2f, 615.7f, 734.2f),
                        PathNode.QuadTo(637.7f, 723.2f, 650.2f, 723.2f),
                        PathNode.QuadTo(662.7f, 723.2f, 684.7f, 734.2f),
                        PathNode.QuadTo(755.7f, 771.2f, 807.7f, 834.2f),
                        PathNode.QuadTo(859.7f, 897.2f, 880.7f, 970.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerRegular!!
    }

private var _timerRegular: ImageVector? = null

val MiuixIcons.Medium.Timer: ImageVector
    get() {
        if (_timerMedium != null) return _timerMedium!!
        _timerMedium = ImageVector.Builder(
            name = "Timer.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1313.9f,
            viewportHeight = 1313.9f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1313.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(327.9f, 144.4f),
                        PathNode.QuadTo(307.1f, 169.8f, 305.1f, 199.6f),
                        PathNode.QuadTo(303.1f, 229.3f, 311.5f, 292.5f),
                        PathNode.QuadTo(336.7f, 466.4f, 457.9f, 572.0f),
                        PathNode.QuadTo(494.4f, 603.6f, 512.0f, 620.3f),
                        PathNode.QuadTo(529.5f, 637.1f, 531.9f, 643.9f),
                        PathNode.QuadTo(534.8f, 652.0f, 534.8f, 656.4f),
                        PathNode.QuadTo(534.8f, 660.8f, 531.9f, 669.4f),
                        PathNode.QuadTo(530.1f, 675.7f, 511.3f, 693.3f),
                        PathNode.QuadTo(492.6f, 711.0f, 457.9f, 741.9f),
                        PathNode.QuadTo(336.7f, 845.9f, 311.5f, 1021.4f),
                        PathNode.QuadTo(303.1f, 1083.6f, 305.1f, 1113.4f),
                        PathNode.QuadTo(307.1f, 1143.1f, 327.9f, 1168.5f),
                        PathNode.QuadTo(348.6f, 1193.3f, 381.9f, 1198.8f),
                        PathNode.QuadTo(415.2f, 1204.4f, 491.8f, 1204.4f),
                        PathNode.HorizontalTo(822.6f),
                        PathNode.QuadTo(898.2f, 1204.4f, 931.1f, 1198.8f),
                        PathNode.QuadTo(964.1f, 1193.3f, 985.5f, 1168.5f),
                        PathNode.QuadTo(1007.2f, 1144.1f, 1009.0f, 1114.3f),
                        PathNode.QuadTo(1010.8f, 1084.4f, 1001.8f, 1021.4f),
                        PathNode.QuadTo(978.2f, 847.5f, 857.5f, 741.9f),
                        PathNode.LineTo(839.1f, 725.9f),
                        PathNode.QuadTo(815.1f, 705.1f, 799.7f, 690.3f),
                        PathNode.QuadTo(784.4f, 675.4f, 782.4f, 669.0f),
                        PathNode.QuadTo(779.6f, 660.8f, 779.6f, 656.4f),
                        PathNode.QuadTo(779.6f, 652.0f, 782.4f, 643.9f),
                        PathNode.QuadTo(784.2f, 638.3f, 799.7f, 623.6f),
                        PathNode.QuadTo(815.2f, 608.9f, 844.5f, 584.0f),
                        PathNode.LineTo(857.5f, 572.0f),
                        PathNode.QuadTo(977.6f, 467.0f, 1001.8f, 292.5f),
                        PathNode.QuadTo(1010.8f, 229.5f, 1009.0f, 199.6f),
                        PathNode.QuadTo(1007.2f, 169.8f, 985.5f, 144.4f),
                        PathNode.QuadTo(964.1f, 120.7f, 931.1f, 115.1f),
                        PathNode.QuadTo(898.2f, 109.5f, 822.6f, 109.5f),
                        PathNode.HorizontalTo(491.8f),
                        PathNode.QuadTo(415.2f, 109.5f, 382.2f, 115.1f),
                        PathNode.QuadTo(349.2f, 120.7f, 327.9f, 144.4f),
                        PathNode.Close,
                        PathNode.MoveTo(884.6f, 977.4f),
                        PathNode.QuadTo(890.1f, 995.6f, 882.4f, 1005.9f),
                        PathNode.QuadTo(874.8f, 1016.3f, 855.0f, 1016.3f),
                        PathNode.HorizontalTo(458.3f),
                        PathNode.QuadTo(438.1f, 1016.3f, 431.2f, 1006.4f),
                        PathNode.QuadTo(424.3f, 996.6f, 428.7f, 980.4f),
                        PathNode.QuadTo(449.7f, 904.0f, 501.3f, 842.6f),
                        PathNode.QuadTo(552.9f, 781.1f, 624.9f, 743.7f),
                        PathNode.QuadTo(645.8f, 733.3f, 657.7f, 733.3f),
                        PathNode.QuadTo(669.6f, 733.3f, 690.4f, 743.7f),
                        PathNode.QuadTo(760.8f, 780.7f, 812.2f, 842.8f),
                        PathNode.QuadTo(863.6f, 905.0f, 884.6f, 977.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerMedium!!
    }

private var _timerMedium: ImageVector? = null

val MiuixIcons.Demibold.Timer: ImageVector
    get() {
        if (_timerDemibold != null) return _timerDemibold!!
        _timerDemibold = ImageVector.Builder(
            name = "Timer.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1324.8f,
            viewportHeight = 1324.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1324.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(329.4f, 147.4f),
                        PathNode.QuadTo(307.4f, 174.4f, 305.4f, 205.4f),
                        PathNode.QuadTo(303.4f, 236.4f, 311.4f, 300.4f),
                        PathNode.QuadTo(337.4f, 476.4f, 459.4f, 582.4f),
                        PathNode.QuadTo(491.4f, 609.4f, 510.4f, 627.4f),
                        PathNode.QuadTo(529.4f, 645.4f, 531.4f, 651.4f),
                        PathNode.QuadTo(533.4f, 657.4f, 533.4f, 661.4f),
                        PathNode.QuadTo(533.4f, 665.4f, 531.4f, 672.4f),
                        PathNode.QuadTo(530.4f, 677.4f, 510.4f, 695.9f),
                        PathNode.QuadTo(490.4f, 714.4f, 459.4f, 742.4f),
                        PathNode.QuadTo(337.4f, 846.4f, 311.4f, 1024.4f),
                        PathNode.QuadTo(303.4f, 1087.4f, 305.4f, 1118.4f),
                        PathNode.QuadTo(307.4f, 1149.4f, 329.4f, 1176.4f),
                        PathNode.QuadTo(351.4f, 1202.4f, 385.9f, 1208.4f),
                        PathNode.QuadTo(420.4f, 1214.4f, 497.4f, 1214.4f),
                        PathNode.HorizontalTo(827.4f),
                        PathNode.QuadTo(903.4f, 1214.4f, 937.4f, 1208.4f),
                        PathNode.QuadTo(971.4f, 1202.4f, 994.4f, 1176.4f),
                        PathNode.QuadTo(1017.4f, 1150.4f, 1019.4f, 1118.9f),
                        PathNode.QuadTo(1021.4f, 1087.4f, 1012.4f, 1024.4f),
                        PathNode.QuadTo(988.4f, 848.4f, 866.4f, 742.4f),
                        PathNode.LineTo(848.4f, 726.4f),
                        PathNode.QuadTo(824.4f, 706.4f, 809.9f, 692.4f),
                        PathNode.QuadTo(795.4f, 678.4f, 793.4f, 672.4f),
                        PathNode.QuadTo(791.4f, 665.4f, 791.4f, 661.4f),
                        PathNode.QuadTo(791.4f, 657.4f, 793.4f, 651.4f),
                        PathNode.QuadTo(794.4f, 647.4f, 810.9f, 631.9f),
                        PathNode.QuadTo(827.4f, 616.4f, 853.4f, 594.4f),
                        PathNode.LineTo(866.4f, 582.4f),
                        PathNode.QuadTo(987.4f, 477.4f, 1012.4f, 300.4f),
                        PathNode.QuadTo(1021.4f, 237.4f, 1019.4f, 205.9f),
                        PathNode.QuadTo(1017.4f, 174.4f, 994.4f, 147.4f),
                        PathNode.QuadTo(971.4f, 122.4f, 937.4f, 116.4f),
                        PathNode.QuadTo(903.4f, 110.4f, 827.4f, 110.4f),
                        PathNode.HorizontalTo(497.4f),
                        PathNode.QuadTo(420.4f, 110.4f, 386.4f, 116.4f),
                        PathNode.QuadTo(352.4f, 122.4f, 329.4f, 147.4f),
                        PathNode.Close,
                        PathNode.MoveTo(887.4f, 982.4f),
                        PathNode.QuadTo(892.4f, 999.4f, 885.4f, 1008.9f),
                        PathNode.QuadTo(878.4f, 1018.4f, 859.4f, 1018.4f),
                        PathNode.HorizontalTo(464.4f),
                        PathNode.QuadTo(445.4f, 1018.4f, 438.9f, 1009.4f),
                        PathNode.QuadTo(432.4f, 1000.4f, 436.4f, 985.4f),
                        PathNode.QuadTo(457.4f, 909.4f, 508.4f, 848.4f),
                        PathNode.QuadTo(559.4f, 787.4f, 631.4f, 750.4f),
                        PathNode.QuadTo(651.4f, 740.4f, 662.9f, 740.4f),
                        PathNode.QuadTo(674.4f, 740.4f, 694.4f, 750.4f),
                        PathNode.QuadTo(764.4f, 787.4f, 815.4f, 848.9f),
                        PathNode.QuadTo(866.4f, 910.4f, 887.4f, 982.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _timerDemibold!!
    }

private var _timerDemibold: ImageVector? = null
