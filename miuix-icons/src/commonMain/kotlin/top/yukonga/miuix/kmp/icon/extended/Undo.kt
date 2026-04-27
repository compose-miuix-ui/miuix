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

val MiuixIcons.Undo: ImageVector
    get() = MiuixIcons.Regular.Undo

val MiuixIcons.Light.Undo: ImageVector
    get() {
        if (_undoLight != null) return _undoLight!!
        _undoLight = ImageVector.Builder(
            name = "Undo.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1146.0f,
            viewportHeight = 1146.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1146.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(700.0f, 770.5f),
                        PathNode.QuadTo(791.0f, 762.5f, 844.0f, 736.5f),
                        PathNode.QuadTo(895.0f, 710.5f, 935.5f, 669.5f),
                        PathNode.QuadTo(976.0f, 628.5f, 1003.0f, 576.5f),
                        PathNode.QuadTo(1029.0f, 524.5f, 1037.0f, 433.5f),
                        PathNode.QuadTo(1042.0f, 377.5f, 1042.0f, 214.5f),
                        PathNode.VerticalTo(116.5f),
                        PathNode.QuadTo(1042.0f, 106.5f, 1037.0f, 101.0f),
                        PathNode.QuadTo(1032.0f, 95.5f, 1020.0f, 95.5f),
                        PathNode.HorizontalTo(1004.0f),
                        PathNode.QuadTo(993.0f, 95.5f, 988.0f, 101.0f),
                        PathNode.QuadTo(983.0f, 106.5f, 983.0f, 116.5f),
                        PathNode.VerticalTo(212.5f),
                        PathNode.QuadTo(983.0f, 278.5f, 982.0f, 335.5f),
                        PathNode.QuadTo(981.0f, 392.5f, 978.0f, 429.5f),
                        PathNode.QuadTo(975.0f, 469.5f, 968.0f, 498.5f),
                        PathNode.QuadTo(961.0f, 527.5f, 950.0f, 550.5f),
                        PathNode.QuadTo(927.0f, 593.5f, 893.0f, 627.5f),
                        PathNode.QuadTo(859.0f, 661.5f, 817.0f, 683.5f),
                        PathNode.QuadTo(793.0f, 695.5f, 764.0f, 702.0f),
                        PathNode.QuadTo(735.0f, 708.5f, 695.0f, 711.5f),
                        PathNode.QuadTo(641.0f, 715.5f, 479.0f, 715.5f),
                        PathNode.LineTo(203.0f, 714.5f),
                        PathNode.LineTo(426.0f, 490.5f),
                        PathNode.QuadTo(436.0f, 481.5f, 437.0f, 473.5f),
                        PathNode.QuadTo(438.0f, 465.5f, 429.0f, 456.5f),
                        PathNode.LineTo(418.0f, 445.5f),
                        PathNode.QuadTo(409.0f, 436.5f, 402.0f, 437.5f),
                        PathNode.QuadTo(395.0f, 438.5f, 386.0f, 447.5f),
                        PathNode.LineTo(117.0f, 717.5f),
                        PathNode.QuadTo(104.0f, 729.5f, 104.0f, 744.0f),
                        PathNode.QuadTo(104.0f, 758.5f, 117.0f, 770.5f),
                        PathNode.LineTo(390.0f, 1043.5f),
                        PathNode.QuadTo(395.0f, 1048.5f, 402.5f, 1049.5f),
                        PathNode.QuadTo(410.0f, 1050.5f, 417.0f, 1042.5f),
                        PathNode.LineTo(431.0f, 1028.5f),
                        PathNode.QuadTo(439.0f, 1021.5f, 437.5f, 1014.5f),
                        PathNode.QuadTo(436.0f, 1007.5f, 430.0f, 1001.5f),
                        PathNode.LineTo(202.0f, 773.5f),
                        PathNode.LineTo(480.0f, 774.5f),
                        PathNode.QuadTo(643.0f, 774.5f, 700.0f, 770.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _undoLight!!
    }

private var _undoLight: ImageVector? = null

val MiuixIcons.Normal.Undo: ImageVector
    get() {
        if (_undoNormal != null) return _undoNormal!!
        _undoNormal = ImageVector.Builder(
            name = "Undo.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1160.0f,
            viewportHeight = 1160.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1160.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(708.0f, 783.4f),
                        PathNode.QuadTo(799.0f, 775.4f, 855.5f, 748.0f),
                        PathNode.QuadTo(907.9f, 721.3f, 949.4f, 679.6f),
                        PathNode.QuadTo(990.9f, 637.9f, 1018.6f, 584.5f),
                        PathNode.QuadTo(1046.0f, 529.8f, 1054.0f, 438.1f),
                        PathNode.QuadTo(1059.0f, 381.4f, 1059.0f, 217.7f),
                        PathNode.VerticalTo(125.2f),
                        PathNode.QuadTo(1059.0f, 111.1f, 1052.3f, 103.9f),
                        PathNode.QuadTo(1045.5f, 96.7f, 1028.0f, 96.7f),
                        PathNode.HorizontalTo(1010.0f),
                        PathNode.QuadTo(994.9f, 96.7f, 988.1f, 104.2f),
                        PathNode.QuadTo(981.4f, 111.8f, 981.4f, 125.2f),
                        PathNode.VerticalTo(215.7f),
                        PathNode.QuadTo(981.4f, 281.7f, 980.8f, 338.4f),
                        PathNode.QuadTo(980.1f, 395.0f, 977.1f, 432.0f),
                        PathNode.QuadTo(973.4f, 472.0f, 966.8f, 500.4f),
                        PathNode.QuadTo(960.1f, 528.7f, 949.1f, 550.3f),
                        PathNode.QuadTo(927.5f, 591.9f, 894.5f, 624.9f),
                        PathNode.QuadTo(861.5f, 657.9f, 820.2f, 678.5f),
                        PathNode.QuadTo(797.6f, 689.8f, 769.6f, 696.3f),
                        PathNode.QuadTo(741.7f, 702.8f, 701.7f, 705.8f),
                        PathNode.QuadTo(648.4f, 709.8f, 486.4f, 709.8f),
                        PathNode.LineTo(232.4f, 708.8f),
                        PathNode.LineTo(438.9f, 502.0f),
                        PathNode.QuadTo(453.0f, 488.2f, 454.3f, 478.1f),
                        PathNode.QuadTo(455.7f, 468.0f, 442.5f, 454.9f),
                        PathNode.LineTo(430.2f, 442.5f),
                        PathNode.QuadTo(417.7f, 430.1f, 408.3f, 431.4f),
                        PathNode.QuadTo(398.9f, 432.8f, 386.5f, 445.2f),
                        PathNode.LineTo(116.1f, 715.9f),
                        PathNode.QuadTo(101.7f, 730.0f, 101.4f, 747.2f),
                        PathNode.QuadTo(101.0f, 764.5f, 116.1f, 779.2f),
                        PathNode.LineTo(391.9f, 1054.3f),
                        PathNode.QuadTo(398.9f, 1061.4f, 408.8f, 1062.4f),
                        PathNode.QuadTo(418.7f, 1063.4f, 428.5f, 1053.3f),
                        PathNode.LineTo(445.9f, 1035.9f),
                        PathNode.QuadTo(456.7f, 1025.4f, 454.8f, 1016.4f),
                        PathNode.QuadTo(453.0f, 1007.3f, 444.9f, 999.2f),
                        PathNode.LineTo(232.0f, 786.4f),
                        PathNode.LineTo(487.4f, 787.4f),
                        PathNode.QuadTo(651.0f, 787.4f, 708.0f, 783.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _undoNormal!!
    }

private var _undoNormal: ImageVector? = null

val MiuixIcons.Regular.Undo: ImageVector
    get() {
        if (_undoRegular != null) return _undoRegular!!
        _undoRegular = ImageVector.Builder(
            name = "Undo.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1166.4f,
            viewportHeight = 1166.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1166.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(711.7f, 789.2f),
                        PathNode.QuadTo(802.7f, 781.2f, 860.7f, 753.2f),
                        PathNode.QuadTo(913.7f, 726.2f, 955.7f, 684.2f),
                        PathNode.QuadTo(997.7f, 642.2f, 1025.7f, 588.2f),
                        PathNode.QuadTo(1053.7f, 532.2f, 1061.7f, 440.2f),
                        PathNode.QuadTo(1066.7f, 383.2f, 1066.7f, 219.2f),
                        PathNode.VerticalTo(129.2f),
                        PathNode.QuadTo(1066.7f, 113.2f, 1059.2f, 105.2f),
                        PathNode.QuadTo(1051.7f, 97.2f, 1031.7f, 97.2f),
                        PathNode.HorizontalTo(1012.7f),
                        PathNode.QuadTo(995.7f, 97.2f, 988.2f, 105.7f),
                        PathNode.QuadTo(980.7f, 114.2f, 980.7f, 129.2f),
                        PathNode.VerticalTo(217.2f),
                        PathNode.QuadTo(980.7f, 283.2f, 980.2f, 339.7f),
                        PathNode.QuadTo(979.7f, 396.2f, 976.7f, 433.2f),
                        PathNode.QuadTo(972.7f, 473.2f, 966.2f, 501.2f),
                        PathNode.QuadTo(959.7f, 529.2f, 948.7f, 550.2f),
                        PathNode.QuadTo(927.7f, 591.2f, 895.2f, 623.7f),
                        PathNode.QuadTo(862.7f, 656.2f, 821.7f, 676.2f),
                        PathNode.QuadTo(799.7f, 687.2f, 772.2f, 693.7f),
                        PathNode.QuadTo(744.7f, 700.2f, 704.7f, 703.2f),
                        PathNode.QuadTo(651.7f, 707.2f, 489.7f, 707.2f),
                        PathNode.LineTo(245.7f, 706.2f),
                        PathNode.LineTo(444.7f, 507.2f),
                        PathNode.QuadTo(460.7f, 491.2f, 462.2f, 480.2f),
                        PathNode.QuadTo(463.7f, 469.2f, 448.7f, 454.2f),
                        PathNode.LineTo(435.7f, 441.2f),
                        PathNode.QuadTo(421.7f, 427.2f, 411.2f, 428.7f),
                        PathNode.QuadTo(400.7f, 430.2f, 386.7f, 444.2f),
                        PathNode.LineTo(115.7f, 715.2f),
                        PathNode.QuadTo(100.7f, 730.2f, 100.2f, 748.7f),
                        PathNode.QuadTo(99.7f, 767.2f, 115.7f, 783.2f),
                        PathNode.LineTo(392.7f, 1059.2f),
                        PathNode.QuadTo(400.7f, 1067.2f, 411.7f, 1068.2f),
                        PathNode.QuadTo(422.7f, 1069.2f, 433.7f, 1058.2f),
                        PathNode.LineTo(452.7f, 1039.2f),
                        PathNode.QuadTo(464.7f, 1027.2f, 462.7f, 1017.2f),
                        PathNode.QuadTo(460.7f, 1007.2f, 451.7f, 998.2f),
                        PathNode.LineTo(245.7f, 792.2f),
                        PathNode.LineTo(490.7f, 793.2f),
                        PathNode.QuadTo(654.7f, 793.2f, 711.7f, 789.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _undoRegular!!
    }

private var _undoRegular: ImageVector? = null

val MiuixIcons.Medium.Undo: ImageVector
    get() {
        if (_undoMedium != null) return _undoMedium!!
        _undoMedium = ImageVector.Builder(
            name = "Undo.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1189.0f,
            viewportHeight = 1189.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1189.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(723.3f, 809.9f),
                        PathNode.QuadTo(817.8f, 801.9f, 875.8f, 772.7f),
                        PathNode.QuadTo(930.0f, 745.1f, 973.2f, 702.0f),
                        PathNode.QuadTo(1016.3f, 658.8f, 1044.9f, 603.6f),
                        PathNode.QuadTo(1074.1f, 547.0f, 1082.1f, 452.1f),
                        PathNode.QuadTo(1087.1f, 395.1f, 1087.1f, 230.5f),
                        PathNode.VerticalTo(140.5f),
                        PathNode.QuadTo(1087.1f, 121.0f, 1076.7f, 110.0f),
                        PathNode.QuadTo(1066.2f, 99.1f, 1042.7f, 99.1f),
                        PathNode.HorizontalTo(1023.7f),
                        PathNode.QuadTo(1003.2f, 99.1f, 992.7f, 110.2f),
                        PathNode.QuadTo(982.3f, 121.4f, 982.3f, 140.5f),
                        PathNode.VerticalTo(228.5f),
                        PathNode.QuadTo(982.3f, 294.5f, 981.8f, 351.0f),
                        PathNode.QuadTo(981.3f, 407.5f, 978.3f, 443.9f),
                        PathNode.QuadTo(974.3f, 483.3f, 968.1f, 509.8f),
                        PathNode.QuadTo(961.9f, 536.4f, 951.5f, 557.4f),
                        PathNode.QuadTo(930.5f, 597.2f, 899.4f, 628.5f),
                        PathNode.QuadTo(868.4f, 659.8f, 828.6f, 679.3f),
                        PathNode.QuadTo(807.2f, 690.3f, 780.8f, 696.2f),
                        PathNode.QuadTo(754.5f, 702.1f, 715.1f, 705.1f),
                        PathNode.QuadTo(662.7f, 709.1f, 500.7f, 709.1f),
                        PathNode.LineTo(279.6f, 708.1f),
                        PathNode.LineTo(462.2f, 525.0f),
                        PathNode.QuadTo(481.1f, 506.0f, 482.6f, 491.2f),
                        PathNode.QuadTo(484.1f, 476.4f, 466.2f, 459.0f),
                        PathNode.LineTo(453.2f, 446.0f),
                        PathNode.QuadTo(438.0f, 430.3f, 423.4f, 430.9f),
                        PathNode.QuadTo(408.8f, 431.5f, 391.2f, 449.0f),
                        PathNode.LineTo(120.2f, 720.0f),
                        PathNode.QuadTo(102.3f, 738.0f, 102.1f, 760.3f),
                        PathNode.QuadTo(101.9f, 782.6f, 120.2f, 801.0f),
                        PathNode.LineTo(397.2f, 1077.0f),
                        PathNode.QuadTo(407.6f, 1087.9f, 422.7f, 1088.9f),
                        PathNode.QuadTo(437.8f, 1089.9f, 451.2f, 1076.0f),
                        PathNode.LineTo(470.2f, 1057.0f),
                        PathNode.QuadTo(484.5f, 1042.6f, 483.1f, 1028.8f),
                        PathNode.QuadTo(481.7f, 1015.0f, 469.2f, 1003.0f),
                        PathNode.LineTo(279.6f, 812.9f),
                        PathNode.LineTo(501.7f, 813.9f),
                        PathNode.QuadTo(665.7f, 813.9f, 723.3f, 809.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _undoMedium!!
    }

private var _undoMedium: ImageVector? = null

val MiuixIcons.Demibold.Undo: ImageVector
    get() {
        if (_undoDemibold != null) return _undoDemibold!!
        _undoDemibold = ImageVector.Builder(
            name = "Undo.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1204.8f,
            viewportHeight = 1204.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1204.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(731.4f, 824.4f),
                        PathNode.QuadTo(828.4f, 816.4f, 886.4f, 786.4f),
                        PathNode.QuadTo(941.4f, 758.4f, 985.4f, 714.4f),
                        PathNode.QuadTo(1029.4f, 670.4f, 1058.4f, 614.4f),
                        PathNode.QuadTo(1088.4f, 557.4f, 1096.4f, 460.4f),
                        PathNode.QuadTo(1101.4f, 403.4f, 1101.4f, 238.4f),
                        PathNode.VerticalTo(148.4f),
                        PathNode.QuadTo(1101.4f, 126.4f, 1088.9f, 113.4f),
                        PathNode.QuadTo(1076.4f, 100.4f, 1050.4f, 100.4f),
                        PathNode.HorizontalTo(1031.4f),
                        PathNode.QuadTo(1008.4f, 100.4f, 995.9f, 113.4f),
                        PathNode.QuadTo(983.4f, 126.4f, 983.4f, 148.4f),
                        PathNode.VerticalTo(236.4f),
                        PathNode.QuadTo(983.4f, 302.4f, 982.9f, 358.9f),
                        PathNode.QuadTo(982.4f, 415.4f, 979.4f, 451.4f),
                        PathNode.QuadTo(975.4f, 490.4f, 969.4f, 515.9f),
                        PathNode.QuadTo(963.4f, 541.4f, 953.4f, 562.4f),
                        PathNode.QuadTo(932.4f, 601.4f, 902.4f, 631.9f),
                        PathNode.QuadTo(872.4f, 662.4f, 833.4f, 681.4f),
                        PathNode.QuadTo(812.4f, 692.4f, 786.9f, 697.9f),
                        PathNode.QuadTo(761.4f, 703.4f, 722.4f, 706.4f),
                        PathNode.QuadTo(670.4f, 710.4f, 508.4f, 710.4f),
                        PathNode.LineTo(303.4f, 709.4f),
                        PathNode.LineTo(474.4f, 537.4f),
                        PathNode.QuadTo(495.4f, 516.4f, 496.9f, 498.9f),
                        PathNode.QuadTo(498.4f, 481.4f, 478.4f, 462.4f),
                        PathNode.LineTo(465.4f, 449.4f),
                        PathNode.QuadTo(449.4f, 432.4f, 431.9f, 432.4f),
                        PathNode.QuadTo(414.4f, 432.4f, 394.4f, 452.4f),
                        PathNode.LineTo(123.4f, 723.4f),
                        PathNode.QuadTo(103.4f, 743.4f, 103.4f, 768.4f),
                        PathNode.QuadTo(103.4f, 793.4f, 123.4f, 813.4f),
                        PathNode.LineTo(400.4f, 1089.4f),
                        PathNode.QuadTo(412.4f, 1102.4f, 430.4f, 1103.4f),
                        PathNode.QuadTo(448.4f, 1104.4f, 463.4f, 1088.4f),
                        PathNode.LineTo(482.4f, 1069.4f),
                        PathNode.QuadTo(498.4f, 1053.4f, 497.4f, 1036.9f),
                        PathNode.QuadTo(496.4f, 1020.4f, 481.4f, 1006.4f),
                        PathNode.LineTo(303.4f, 827.4f),
                        PathNode.LineTo(509.4f, 828.4f),
                        PathNode.QuadTo(673.4f, 828.4f, 731.4f, 824.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _undoDemibold!!
    }

private var _undoDemibold: ImageVector? = null
