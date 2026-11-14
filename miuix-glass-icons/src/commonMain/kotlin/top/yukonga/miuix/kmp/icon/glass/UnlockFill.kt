// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons


private fun buildUnlockFillIcon(
    name: String,
    outlinePathData: List<PathNode>,
    outlineTranslationX: Float,
    outlineTranslationY: Float,
): ImageVector = ImageVector.Builder(
    name = name,
    defaultWidth = 28.0f.dp,
    defaultHeight = 28.0f.dp,
    viewportWidth = 1333.3833f,
    viewportHeight = 1333.3833f,
    autoMirror = false,
).apply {
    group(scaleY = -1.0f, translationX = outlineTranslationX, translationY = outlineTranslationY) {
        addPath(
            pathData = outlinePathData,
            fill = SolidColor(Color.Black),
            fillAlpha = 1f,
            pathFillType = PathFillType.NonZero,
        )
    }
}.build()

val MiuixIcons.Glass.UnlockFill: ImageVector
    get() = MiuixIcons.Glass.Regular.UnlockFill

val MiuixIcons.Glass.Light.UnlockFill: ImageVector
    get() {
        if (_unlockFillLight != null) return _unlockFillLight!!
        _unlockFillLight = buildUnlockFillIcon(
            name = "UnlockFill.Glass.Light",
            outlinePathData = listOf(
                PathNode.MoveTo(906.3f, 62.2f),
                PathNode.QuadTo(928f, 72.8f, 943.9f, 88.7f),
                PathNode.QuadTo(959.9f, 104.7f, 970f, 125.9f),
                PathNode.QuadTo(980.7f, 147.1f, 983.2f, 178.9f),
                PathNode.QuadTo(985.7f, 210.8f, 985.7f, 286f),
                PathNode.LineTo(985.7f, 493.8f),
                PathNode.QuadTo(985.7f, 569.4f, 983.2f, 600.8f),
                PathNode.QuadTo(980.7f, 632.2f, 969.5f, 653.9f),
                PathNode.QuadTo(948.8f, 695.3f, 905.9f, 716.5f),
                PathNode.QuadTo(884.2f, 727.7f, 852.8f, 730.2f),
                PathNode.QuadTo(821.4f, 732.7f, 745.8f, 732.7f),
                PathNode.LineTo(405.1f, 732.7f),
                PathNode.QuadTo(390.7f, 732.7f, 385f, 738.7f),
                PathNode.QuadTo(379.4f, 744.7f, 379.4f, 759.8f),
                PathNode.LineTo(379.4f, 842.9f),
                PathNode.QuadTo(379.4f, 895.9f, 405.9f, 940.7f),
                PathNode.QuadTo(432.4f, 985.4f, 477f, 1011.9f),
                PathNode.QuadTo(521.5f, 1038.4f, 574.5f, 1038.4f),
                PathNode.QuadTo(621.4f, 1038.4f, 663.2f, 1017.9f),
                PathNode.QuadTo(704.9f, 997.4f, 733.1f, 957f),
                PathNode.QuadTo(761.3f, 916.5f, 768f, 860.1f),
                PathNode.QuadTo(770.1f, 849.4f, 775.4f, 844.7f),
                PathNode.QuadTo(780.7f, 840f, 793f, 840f),
                PathNode.LineTo(807.8f, 840f),
                PathNode.QuadTo(820f, 840f, 824.2f, 846.1f),
                PathNode.QuadTo(828.4f, 852.2f, 826.8f, 864f),
                PathNode.QuadTo(819.9f, 933.4f, 783.7f, 986.5f),
                PathNode.QuadTo(747.5f, 1039.7f, 692.7f, 1068.2f),
                PathNode.QuadTo(637.8f, 1096.8f, 574.5f, 1096.8f),
                PathNode.QuadTo(505.2f, 1096.8f, 446.9f, 1062.8f),
                PathNode.QuadTo(388.5f, 1028.9f, 354.6f, 970.5f),
                PathNode.QuadTo(320.7f, 912.2f, 320.7f, 842.9f),
                PathNode.LineTo(320.7f, 732.1f),
                PathNode.QuadTo(288.1f, 730.1f, 268.6f, 727f),
                PathNode.QuadTo(249.1f, 724f, 235.5f, 717f),
                PathNode.QuadTo(194.5f, 696.2f, 172.9f, 654.3f),
                PathNode.QuadTo(161.8f, 633.1f, 159.5f, 601.9f),
                PathNode.QuadTo(157.2f, 570.8f, 157.2f, 493.8f),
                PathNode.LineTo(157.2f, 286f),
                PathNode.QuadTo(157.2f, 210.8f, 159.7f, 178.9f),
                PathNode.QuadTo(162.2f, 147.1f, 172.9f, 125.9f),
                PathNode.QuadTo(194.1f, 83f, 235.5f, 62.2f),
                PathNode.QuadTo(257.2f, 51.1f, 289.3f, 48.6f),
                PathNode.QuadTo(321.4f, 46.1f, 397.1f, 46.1f),
                PathNode.LineTo(745.8f, 46.1f),
                PathNode.QuadTo(821.4f, 46.1f, 853.3f, 48.6f),
                PathNode.QuadTo(885.1f, 51.1f, 906.3f, 62.2f),
                PathNode.Close,
            ),
            outlineTranslationX = 95.2417f,
            outlineTranslationY = 1238.1417f,
        )
        return _unlockFillLight!!
    }

private var _unlockFillLight: ImageVector? = null

val MiuixIcons.Glass.Normal.UnlockFill: ImageVector
    get() {
        if (_unlockFillNormal != null) return _unlockFillNormal!!
        _unlockFillNormal = buildUnlockFillIcon(
            name = "UnlockFill.Glass.Normal",
            outlinePathData = listOf(
                PathNode.MoveTo(908.5f, 56.9f),
                PathNode.QuadTo(931f, 67.7f, 948.1f, 84.7f),
                PathNode.QuadTo(965.1f, 101.7f, 975.8f, 124.2f),
                PathNode.QuadTo(987.4f, 146.6f, 989.9f, 178.9f),
                PathNode.QuadTo(992.4f, 211.2f, 992.4f, 287.7f),
                PathNode.LineTo(992.4f, 494.2f),
                PathNode.QuadTo(992.4f, 570.8f, 989.9f, 603f),
                PathNode.QuadTo(987.4f, 635.1f, 975.7f, 657.7f),
                PathNode.QuadTo(953.3f, 701.6f, 908.3f, 724f),
                PathNode.QuadTo(885.7f, 735.7f, 853.6f, 738.2f),
                PathNode.QuadTo(821.4f, 740.7f, 744.8f, 740.7f),
                PathNode.LineTo(422.5f, 740.7f),
                PathNode.QuadTo(402.9f, 740.7f, 395.3f, 748.1f),
                PathNode.QuadTo(387.7f, 755.5f, 387.7f, 774f),
                PathNode.LineTo(387.7f, 841.2f),
                PathNode.QuadTo(387.7f, 891.4f, 412.8f, 933.6f),
                PathNode.QuadTo(437.9f, 975.8f, 480f, 1000.9f),
                PathNode.QuadTo(522.2f, 1026.1f, 572.4f, 1026.1f),
                PathNode.QuadTo(615.9f, 1026.1f, 654.7f, 1006.9f),
                PathNode.QuadTo(693.6f, 987.8f, 720.3f, 951.8f),
                PathNode.QuadTo(747f, 915.8f, 754.5f, 867.9f),
                PathNode.QuadTo(757.3f, 853.6f, 764.1f, 847.6f),
                PathNode.QuadTo(770.9f, 841.7f, 787.8f, 841.7f),
                PathNode.LineTo(806.8f, 841.7f),
                PathNode.QuadTo(823f, 841.7f, 828.5f, 849.8f),
                PathNode.QuadTo(833.9f, 857.9f, 831.3f, 873.9f),
                PathNode.QuadTo(822.3f, 940.3f, 785.2f, 992.7f),
                PathNode.QuadTo(748.2f, 1045f, 692.5f, 1073.9f),
                PathNode.QuadTo(636.8f, 1102.7f, 572.4f, 1102.7f),
                PathNode.QuadTo(501.2f, 1102.7f, 441.1f, 1067.7f),
                PathNode.QuadTo(380.9f, 1032.7f, 345.9f, 972.5f),
                PathNode.QuadTo(310.9f, 912.4f, 310.9f, 841.2f),
                PathNode.LineTo(310.9f, 739.9f),
                PathNode.QuadTo(283.5f, 737.9f, 265.4f, 734.5f),
                PathNode.QuadTo(247.3f, 731.2f, 233.4f, 724.2f),
                PathNode.QuadTo(189.7f, 701.9f, 167.1f, 657.8f),
                PathNode.QuadTo(155.3f, 635.4f, 152.9f, 603.3f),
                PathNode.QuadTo(150.5f, 571.2f, 150.5f, 494.2f),
                PathNode.LineTo(150.5f, 287.7f),
                PathNode.QuadTo(150.5f, 211.2f, 153f, 178.9f),
                PathNode.QuadTo(155.5f, 146.6f, 167.1f, 124.2f),
                PathNode.QuadTo(189.5f, 79.2f, 233.4f, 56.9f),
                PathNode.QuadTo(256f, 45.1f, 288.7f, 42.6f),
                PathNode.QuadTo(321.4f, 40.1f, 398f, 40.1f),
                PathNode.LineTo(744.8f, 40.1f),
                PathNode.QuadTo(821.4f, 40.1f, 853.7f, 42.6f),
                PathNode.QuadTo(886f, 45.1f, 908.5f, 56.9f),
                PathNode.Close,
            ),
            outlineTranslationX = 95.2417f,
            outlineTranslationY = 1238.1417f,
        )
        return _unlockFillNormal!!
    }

private var _unlockFillNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.UnlockFill: ImageVector
    get() {
        if (_unlockFillRegular != null) return _unlockFillRegular!!
        _unlockFillRegular = buildUnlockFillIcon(
            name = "UnlockFill.Glass.Regular",
            outlinePathData = listOf(
                PathNode.MoveTo(909.4f, 54.4f),
                PathNode.QuadTo(932.4f, 65.4f, 949.9f, 82.9f),
                PathNode.QuadTo(967.4f, 100.4f, 978.4f, 123.4f),
                PathNode.QuadTo(990.4f, 146.4f, 992.9f, 178.9f),
                PathNode.QuadTo(995.4f, 211.4f, 995.4f, 288.4f),
                PathNode.LineTo(995.4f, 494.4f),
                PathNode.QuadTo(995.4f, 571.4f, 992.9f, 603.9f),
                PathNode.QuadTo(990.4f, 636.4f, 978.4f, 659.4f),
                PathNode.QuadTo(955.4f, 704.4f, 909.4f, 727.4f),
                PathNode.QuadTo(886.4f, 739.4f, 853.9f, 741.9f),
                PathNode.QuadTo(821.4f, 744.4f, 744.4f, 744.4f),
                PathNode.LineTo(430.4f, 744.4f),
                PathNode.QuadTo(408.4f, 744.4f, 399.9f, 752.4f),
                PathNode.QuadTo(391.4f, 760.4f, 391.4f, 780.4f),
                PathNode.LineTo(391.4f, 840.4f),
                PathNode.QuadTo(391.4f, 889.4f, 415.9f, 930.4f),
                PathNode.QuadTo(440.4f, 971.4f, 481.4f, 995.9f),
                PathNode.QuadTo(522.4f, 1020.4f, 571.4f, 1020.4f),
                PathNode.QuadTo(613.4f, 1020.4f, 650.9f, 1001.9f),
                PathNode.QuadTo(688.4f, 983.4f, 714.4f, 949.4f),
                PathNode.QuadTo(740.4f, 915.4f, 748.4f, 871.4f),
                PathNode.QuadTo(751.4f, 855.4f, 758.9f, 848.9f),
                PathNode.QuadTo(766.4f, 842.4f, 785.4f, 842.4f),
                PathNode.LineTo(806.4f, 842.4f),
                PathNode.QuadTo(824.4f, 842.4f, 830.4f, 851.4f),
                PathNode.QuadTo(836.4f, 860.4f, 833.4f, 878.4f),
                PathNode.QuadTo(823.4f, 943.4f, 785.9f, 995.4f),
                PathNode.QuadTo(748.4f, 1047.4f, 692.4f, 1076.4f),
                PathNode.QuadTo(636.4f, 1105.4f, 571.4f, 1105.4f),
                PathNode.QuadTo(499.4f, 1105.4f, 438.4f, 1069.9f),
                PathNode.QuadTo(377.4f, 1034.4f, 341.9f, 973.4f),
                PathNode.QuadTo(306.4f, 912.4f, 306.4f, 840.4f),
                PathNode.LineTo(306.4f, 743.4f),
                PathNode.QuadTo(281.4f, 741.4f, 263.9f, 737.9f),
                PathNode.QuadTo(246.4f, 734.4f, 232.4f, 727.4f),
                PathNode.QuadTo(187.4f, 704.4f, 164.4f, 659.4f),
                PathNode.QuadTo(152.4f, 636.4f, 149.9f, 603.9f),
                PathNode.QuadTo(147.4f, 571.4f, 147.4f, 494.4f),
                PathNode.LineTo(147.4f, 288.4f),
                PathNode.QuadTo(147.4f, 211.4f, 149.9f, 178.9f),
                PathNode.QuadTo(152.4f, 146.4f, 164.4f, 123.4f),
                PathNode.QuadTo(187.4f, 77.4f, 232.4f, 54.4f),
                PathNode.QuadTo(255.4f, 42.4f, 288.4f, 39.9f),
                PathNode.QuadTo(321.4f, 37.4f, 398.4f, 37.4f),
                PathNode.LineTo(744.4f, 37.4f),
                PathNode.QuadTo(821.4f, 37.4f, 853.9f, 39.9f),
                PathNode.QuadTo(886.4f, 42.4f, 909.4f, 54.4f),
                PathNode.Close,
            ),
            outlineTranslationX = 95.2417f,
            outlineTranslationY = 1238.1417f,
        )
        return _unlockFillRegular!!
    }

private var _unlockFillRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.UnlockFill: ImageVector
    get() {
        if (_unlockFillMedium != null) return _unlockFillMedium!!
        _unlockFillMedium = buildUnlockFillIcon(
            name = "UnlockFill.Glass.Medium",
            outlinePathData = listOf(
                PathNode.MoveTo(912.4f, 47.7f),
                PathNode.QuadTo(936.5f, 59.2f, 955.2f, 78.1f),
                PathNode.QuadTo(973.9f, 96.9f, 985.7f, 121f),
                PathNode.QuadTo(998.3f, 145.3f, 1001f, 179.1f),
                PathNode.QuadTo(1003.8f, 212.8f, 1003.8f, 290.1f),
                PathNode.LineTo(1003.8f, 495f),
                PathNode.QuadTo(1003.8f, 572f, 1001f, 605.6f),
                PathNode.QuadTo(998.3f, 639.1f, 985.7f, 663.8f),
                PathNode.QuadTo(961.4f, 711.7f, 912.4f, 736.1f),
                PathNode.QuadTo(888.1f, 748.6f, 854.5f, 751.4f),
                PathNode.QuadTo(820.9f, 754.2f, 743.6f, 754.2f),
                PathNode.LineTo(436.4f, 754.2f),
                PathNode.QuadTo(417.6f, 754.2f, 410.3f, 761.1f),
                PathNode.QuadTo(403f, 768f, 403f, 785.8f),
                PathNode.LineTo(403f, 839.1f),
                PathNode.QuadTo(403f, 884.8f, 426.1f, 923.3f),
                PathNode.QuadTo(449.1f, 961.7f, 487.6f, 984.6f),
                PathNode.QuadTo(526.2f, 1007.5f, 572.5f, 1007.5f),
                PathNode.QuadTo(612.1f, 1007.5f, 646.1f, 991.7f),
                PathNode.QuadTo(680.1f, 975.9f, 703.2f, 947f),
                PathNode.QuadTo(726.4f, 918.1f, 734.1f, 880.9f),
                PathNode.QuadTo(737.6f, 864.9f, 747.4f, 858.3f),
                PathNode.QuadTo(757.2f, 851.6f, 779.8f, 851.6f),
                PathNode.LineTo(805.3f, 851.6f),
                PathNode.QuadTo(827.4f, 851.6f, 835.7f, 862.5f),
                PathNode.QuadTo(844f, 873.4f, 841f, 893f),
                PathNode.QuadTo(831.5f, 954f, 793.5f, 1004.6f),
                PathNode.QuadTo(755.5f, 1055.3f, 697.6f, 1084.3f),
                PathNode.QuadTo(639.7f, 1113.3f, 572.5f, 1113.3f),
                PathNode.QuadTo(497.3f, 1113.3f, 434.1f, 1076.6f),
                PathNode.QuadTo(370.9f, 1039.8f, 334.1f, 976.8f),
                PathNode.QuadTo(297.2f, 913.8f, 297.2f, 839.1f),
                PathNode.LineTo(297.2f, 752.1f),
                PathNode.QuadTo(274.9f, 750.1f, 258.8f, 746.4f),
                PathNode.QuadTo(242.6f, 742.8f, 229.5f, 736.1f),
                PathNode.QuadTo(181.5f, 711.7f, 157.1f, 663.8f),
                PathNode.QuadTo(144.6f, 639.1f, 141.8f, 605.6f),
                PathNode.QuadTo(139.1f, 572f, 139.1f, 495f),
                PathNode.LineTo(139.1f, 290.1f),
                PathNode.QuadTo(139.1f, 212.8f, 141.8f, 179.1f),
                PathNode.QuadTo(144.6f, 145.3f, 157.1f, 121f),
                PathNode.QuadTo(181.5f, 72.6f, 229.5f, 47.7f),
                PathNode.QuadTo(254.1f, 35.1f, 288.2f, 32.4f),
                PathNode.QuadTo(322.2f, 29.6f, 399.2f, 29.6f),
                PathNode.LineTo(743.6f, 29.6f),
                PathNode.QuadTo(820.9f, 29.6f, 854.5f, 32.4f),
                PathNode.QuadTo(888.1f, 35.1f, 912.4f, 47.7f),
                PathNode.Close,
            ),
            outlineTranslationX = 95.2417f,
            outlineTranslationY = 1238.1417f,
        )
        return _unlockFillMedium!!
    }

private var _unlockFillMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.UnlockFill: ImageVector
    get() {
        if (_unlockFillDemibold != null) return _unlockFillDemibold!!
        _unlockFillDemibold = buildUnlockFillIcon(
            name = "UnlockFill.Glass.Demibold",
            outlinePathData = listOf(
                PathNode.MoveTo(914.5f, 42.9f),
                PathNode.QuadTo(939.3f, 54.9f, 958.9f, 74.7f),
                PathNode.QuadTo(978.5f, 94.5f, 990.8f, 119.3f),
                PathNode.QuadTo(1003.8f, 144.6f, 1006.7f, 179.2f),
                PathNode.QuadTo(1009.7f, 213.7f, 1009.7f, 291.2f),
                PathNode.LineTo(1009.7f, 495.3f),
                PathNode.QuadTo(1009.7f, 572.3f, 1006.7f, 606.7f),
                PathNode.QuadTo(1003.8f, 641f, 990.8f, 666.8f),
                PathNode.QuadTo(965.5f, 716.8f, 914.5f, 742.1f),
                PathNode.QuadTo(889.2f, 755.1f, 854.8f, 758f),
                PathNode.QuadTo(820.5f, 761f, 743.1f, 761f),
                PathNode.LineTo(440.5f, 761f),
                PathNode.QuadTo(424.1f, 761f, 417.6f, 767.1f),
                PathNode.QuadTo(411.2f, 773.3f, 411.2f, 789.6f),
                PathNode.LineTo(411.2f, 838.1f),
                PathNode.QuadTo(411.2f, 881.6f, 433.2f, 918.3f),
                PathNode.QuadTo(455.1f, 954.9f, 492f, 976.6f),
                PathNode.QuadTo(528.9f, 998.4f, 573.3f, 998.4f),
                PathNode.QuadTo(611.1f, 998.4f, 642.7f, 984.5f),
                PathNode.QuadTo(674.2f, 970.6f, 695.4f, 945.3f),
                PathNode.QuadTo(716.5f, 920f, 724.1f, 887.5f),
                PathNode.QuadTo(728f, 871.5f, 739.4f, 864.8f),
                PathNode.QuadTo(750.8f, 858.1f, 775.8f, 858.1f),
                PathNode.LineTo(804.6f, 858.1f),
                PathNode.QuadTo(829.5f, 858.1f, 839.4f, 870.3f),
                PathNode.QuadTo(849.3f, 882.5f, 846.3f, 903.2f),
                PathNode.QuadTo(837.2f, 961.3f, 798.8f, 1011.1f),
                PathNode.QuadTo(760.4f, 1060.8f, 701.2f, 1089.8f),
                PathNode.QuadTo(641.9f, 1118.8f, 573.3f, 1118.8f),
                PathNode.QuadTo(495.8f, 1118.8f, 431.1f, 1081.2f),
                PathNode.QuadTo(366.4f, 1043.6f, 328.6f, 979.2f),
                PathNode.QuadTo(290.8f, 914.7f, 290.8f, 838.1f),
                PathNode.LineTo(290.8f, 758.1f),
                PathNode.QuadTo(270.4f, 756.1f, 255.2f, 752.4f),
                PathNode.QuadTo(240f, 748.7f, 227.4f, 742.1f),
                PathNode.QuadTo(177.3f, 716.8f, 152f, 666.8f),
                PathNode.QuadTo(139.1f, 641f, 136.1f, 606.7f),
                PathNode.QuadTo(133.2f, 572.3f, 133.2f, 495.3f),
                PathNode.LineTo(133.2f, 291.2f),
                PathNode.QuadTo(133.2f, 213.7f, 136.1f, 179.2f),
                PathNode.QuadTo(139.1f, 144.6f, 152f, 119.3f),
                PathNode.QuadTo(177.3f, 69.2f, 227.4f, 42.9f),
                PathNode.QuadTo(253.1f, 30f, 288f, 27.1f),
                PathNode.QuadTo(322.8f, 24.1f, 399.8f, 24.1f),
                PathNode.LineTo(743.1f, 24.1f),
                PathNode.QuadTo(820.5f, 24.1f, 854.8f, 27.1f),
                PathNode.QuadTo(889.2f, 30f, 914.5f, 42.9f),
                PathNode.Close,
            ),
            outlineTranslationX = 95.2417f,
            outlineTranslationY = 1238.1417f,
        )
        return _unlockFillDemibold!!
    }

private var _unlockFillDemibold: ImageVector? = null

