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

val MiuixIcons.Glass.Undo: ImageVector
    get() = MiuixIcons.Glass.Regular.Undo

val MiuixIcons.Glass.Light.Undo: ImageVector
    get() {
        if (_undoLight != null) return _undoLight!!
        _undoLight = ImageVector.Builder(
            name = "Undo.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(200.5f, 772.9f),
                        PathNode.LineTo(428.7f, 1000.7f),
                        PathNode.QuadTo(434.6f, 1006.6f, 435.7f, 1013.5f),
                        PathNode.QuadTo(436.8f, 1020.4f, 429.2f, 1027.9f),
                        PathNode.LineTo(415.1f, 1042.0f),
                        PathNode.QuadTo(408.1f, 1049.0f, 400.7f, 1048.5f),
                        PathNode.QuadTo(393.2f, 1047.9f, 387.9f, 1042.6f),
                        PathNode.LineTo(114.9f, 770.1f),
                        PathNode.QuadTo(102.0f, 757.7f, 102.3f, 743.2f),
                        PathNode.QuadTo(102.6f, 728.7f, 114.9f, 716.4f),
                        PathNode.LineTo(384.6f, 446.2f),
                        PathNode.QuadTo(393.2f, 437.6f, 400.4f, 436.7f),
                        PathNode.QuadTo(407.6f, 435.9f, 416.2f, 444.6f),
                        PathNode.LineTo(427.5f, 455.8f),
                        PathNode.QuadTo(436.7f, 465.0f, 435.8f, 472.5f),
                        PathNode.QuadTo(435.0f, 479.9f, 424.8f, 490.1f),
                        PathNode.LineTo(200.9f, 713.6f),
                        PathNode.LineTo(476.9f, 714.6f),
                        PathNode.LineTo(516.9f, 714.6f),
                        PathNode.QuadTo(639.0f, 714.6f, 693.7f, 710.6f),
                        PathNode.QuadTo(734.6f, 707.6f, 763.2f, 700.9f),
                        PathNode.QuadTo(791.8f, 694.1f, 815.1f, 682.2f),
                        PathNode.QuadTo(857.9f, 661.4f, 892.0f, 627.1f),
                        PathNode.QuadTo(926.0f, 592.8f, 947.9f, 550.0f),
                        PathNode.QuadTo(959.3f, 527.7f, 966.3f, 498.4f),
                        PathNode.QuadTo(973.2f, 469.0f, 976.8f, 428.6f),
                        PathNode.QuadTo(979.8f, 391.1f, 980.5f, 334.2f),
                        PathNode.QuadTo(981.2f, 277.2f, 981.2f, 211.2f),
                        PathNode.LineTo(981.2f, 115.2f),
                        PathNode.QuadTo(981.2f, 105.6f, 986.3f, 100.0f),
                        PathNode.QuadTo(991.3f, 94.4f, 1002.1f, 94.4f),
                        PathNode.LineTo(1018.0f, 94.4f),
                        PathNode.QuadTo(1030.5f, 94.4f, 1035.5f, 99.7f),
                        PathNode.QuadTo(1040.6f, 105.0f, 1040.6f, 115.2f),
                        PathNode.LineTo(1040.6f, 213.2f),
                        PathNode.QuadTo(1040.6f, 278.2f, 1039.6f, 336.6f),
                        PathNode.QuadTo(1038.6f, 394.9f, 1035.6f, 432.9f),
                        PathNode.QuadTo(1031.6f, 478.5f, 1023.5f, 512.9f),
                        PathNode.QuadTo(1015.5f, 547.4f, 1000.9f, 576.0f),
                        PathNode.QuadTo(974.2f, 628.2f, 933.8f, 668.7f),
                        PathNode.QuadTo(893.3f, 709.1f, 842.1f, 735.2f),
                        PathNode.QuadTo(812.9f, 749.8f, 778.5f, 757.9f),
                        PathNode.QuadTo(744.0f, 765.9f, 698.5f, 769.9f),
                        PathNode.QuadTo(641.5f, 773.9f, 478.3f, 773.9f),
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

val MiuixIcons.Glass.Normal.Undo: ImageVector
    get() {
        if (_undoNormal != null) return _undoNormal!!
        _undoNormal = ImageVector.Builder(
            name = "Undo.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(223.3f, 778.4f),
                        PathNode.LineTo(436.2f, 991.2f),
                        PathNode.QuadTo(444.3f, 999.2f, 446.0f, 1008.3f),
                        PathNode.QuadTo(447.7f, 1017.3f, 437.1f, 1027.9f),
                        PathNode.LineTo(419.6f, 1045.4f),
                        PathNode.QuadTo(409.9f, 1055.1f, 400.0f, 1054.3f),
                        PathNode.QuadTo(390.1f, 1053.4f, 382.9f, 1046.2f),
                        PathNode.LineTo(107.2f, 771.3f),
                        PathNode.QuadTo(92.2f, 756.4f, 92.6f, 739.2f),
                        PathNode.QuadTo(93.0f, 721.9f, 107.2f, 707.8f),
                        PathNode.LineTo(377.8f, 437.1f),
                        PathNode.QuadTo(390.1f, 424.7f, 399.6f, 423.4f),
                        PathNode.QuadTo(409.0f, 422.1f, 421.4f, 434.5f),
                        PathNode.LineTo(433.8f, 446.9f),
                        PathNode.QuadTo(447.0f, 460.1f, 445.7f, 470.0f),
                        PathNode.QuadTo(444.4f, 479.9f, 430.2f, 494.1f),
                        PathNode.LineTo(223.4f, 700.7f),
                        PathNode.LineTo(477.4f, 701.7f),
                        PathNode.LineTo(517.4f, 701.7f),
                        PathNode.QuadTo(634.7f, 701.7f, 693.0f, 697.7f),
                        PathNode.QuadTo(733.3f, 694.7f, 761.1f, 688.2f),
                        PathNode.QuadTo(789.0f, 681.6f, 811.4f, 670.3f),
                        PathNode.QuadTo(852.9f, 650.0f, 885.9f, 617.0f),
                        PathNode.QuadTo(918.9f, 583.9f, 940.2f, 542.4f),
                        PathNode.QuadTo(951.3f, 520.9f, 958.0f, 492.5f),
                        PathNode.QuadTo(964.6f, 464.1f, 968.5f, 424.0f),
                        PathNode.QuadTo(971.5f, 386.8f, 972.0f, 330.2f),
                        PathNode.QuadTo(972.6f, 273.6f, 972.6f, 207.6f),
                        PathNode.LineTo(972.6f, 117.1f),
                        PathNode.QuadTo(972.6f, 103.7f, 979.3f, 96.1f),
                        PathNode.QuadTo(986.1f, 88.5f, 1001.1f, 88.5f),
                        PathNode.LineTo(1019.2f, 88.5f),
                        PathNode.QuadTo(1036.8f, 88.5f, 1043.5f, 95.7f),
                        PathNode.QuadTo(1050.3f, 102.9f, 1050.3f, 117.1f),
                        PathNode.LineTo(1050.3f, 209.6f),
                        PathNode.QuadTo(1050.3f, 274.6f, 1049.3f, 333.4f),
                        PathNode.QuadTo(1048.3f, 392.1f, 1045.3f, 430.1f),
                        PathNode.QuadTo(1041.3f, 476.0f, 1032.9f, 511.5f),
                        PathNode.QuadTo(1024.6f, 547.0f, 1009.7f, 576.6f),
                        PathNode.QuadTo(982.1f, 630.1f, 940.6f, 671.6f),
                        PathNode.QuadTo(899.1f, 713.1f, 846.6f, 739.8f),
                        PathNode.QuadTo(816.2f, 754.7f, 780.7f, 763.0f),
                        PathNode.QuadTo(745.2f, 771.4f, 699.3f, 775.4f),
                        PathNode.QuadTo(642.3f, 779.4f, 478.6f, 779.4f),
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

val MiuixIcons.Glass.Regular.Undo: ImageVector
    get() {
        if (_undoRegular != null) return _undoRegular!!
        _undoRegular = ImageVector.Builder(
            name = "Undo.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(233.7f, 780.9f),
                        PathNode.LineTo(439.7f, 986.9f),
                        PathNode.QuadTo(448.7f, 995.9f, 450.7f, 1005.9f),
                        PathNode.QuadTo(452.7f, 1015.9f, 440.7f, 1027.9f),
                        PathNode.LineTo(421.7f, 1046.9f),
                        PathNode.QuadTo(410.7f, 1057.9f, 399.7f, 1056.9f),
                        PathNode.QuadTo(388.7f, 1055.9f, 380.7f, 1047.9f),
                        PathNode.LineTo(103.7f, 771.9f),
                        PathNode.QuadTo(87.7f, 755.9f, 88.2f, 737.4f),
                        PathNode.QuadTo(88.7f, 718.9f, 103.7f, 703.9f),
                        PathNode.LineTo(374.7f, 432.9f),
                        PathNode.QuadTo(388.7f, 418.9f, 399.2f, 417.4f),
                        PathNode.QuadTo(409.7f, 415.9f, 423.7f, 429.9f),
                        PathNode.LineTo(436.7f, 442.9f),
                        PathNode.QuadTo(451.7f, 457.9f, 450.2f, 468.9f),
                        PathNode.QuadTo(448.7f, 479.9f, 432.7f, 495.9f),
                        PathNode.LineTo(233.7f, 694.9f),
                        PathNode.LineTo(477.7f, 695.9f),
                        PathNode.LineTo(517.7f, 695.9f),
                        PathNode.QuadTo(632.7f, 695.9f, 692.7f, 691.9f),
                        PathNode.QuadTo(732.7f, 688.9f, 760.2f, 682.4f),
                        PathNode.QuadTo(787.7f, 675.9f, 809.7f, 664.9f),
                        PathNode.QuadTo(850.7f, 644.9f, 883.2f, 612.4f),
                        PathNode.QuadTo(915.7f, 579.9f, 936.7f, 538.9f),
                        PathNode.QuadTo(947.7f, 517.9f, 954.2f, 489.9f),
                        PathNode.QuadTo(960.7f, 461.9f, 964.7f, 421.9f),
                        PathNode.QuadTo(967.7f, 384.9f, 968.2f, 328.4f),
                        PathNode.QuadTo(968.7f, 271.9f, 968.7f, 205.9f),
                        PathNode.LineTo(968.7f, 117.9f),
                        PathNode.QuadTo(968.7f, 102.9f, 976.2f, 94.4f),
                        PathNode.QuadTo(983.7f, 85.9f, 1000.7f, 85.9f),
                        PathNode.LineTo(1019.7f, 85.9f),
                        PathNode.QuadTo(1039.7f, 85.9f, 1047.2f, 93.9f),
                        PathNode.QuadTo(1054.7f, 101.9f, 1054.7f, 117.9f),
                        PathNode.LineTo(1054.7f, 207.9f),
                        PathNode.QuadTo(1054.7f, 272.9f, 1053.7f, 331.9f),
                        PathNode.QuadTo(1052.7f, 390.9f, 1049.7f, 428.9f),
                        PathNode.QuadTo(1045.7f, 474.9f, 1037.2f, 510.9f),
                        PathNode.QuadTo(1028.7f, 546.9f, 1013.7f, 576.9f),
                        PathNode.QuadTo(985.7f, 630.9f, 943.7f, 672.9f),
                        PathNode.QuadTo(901.7f, 714.9f, 848.7f, 741.9f),
                        PathNode.QuadTo(817.7f, 756.9f, 781.7f, 765.4f),
                        PathNode.QuadTo(745.7f, 773.9f, 699.7f, 777.9f),
                        PathNode.QuadTo(642.7f, 781.9f, 478.7f, 781.9f),
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

val MiuixIcons.Glass.Medium.Undo: ImageVector
    get() {
        if (_undoMedium != null) return _undoMedium!!
        _undoMedium = ImageVector.Builder(
            name = "Undo.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(256.6f, 790.4f),
                        PathNode.LineTo(446.4f, 980.1f),
                        PathNode.QuadTo(458.3f, 992.1f, 460.1f, 1006.0f),
                        PathNode.QuadTo(461.8f, 1019.9f, 447.4f, 1034.7f),
                        PathNode.LineTo(428.4f, 1053.7f),
                        PathNode.QuadTo(414.7f, 1067.4f, 399.6f, 1066.4f),
                        PathNode.QuadTo(384.6f, 1065.4f, 373.9f, 1054.7f),
                        PathNode.LineTo(96.9f, 778.7f),
                        PathNode.QuadTo(78.4f, 759.9f, 78.8f, 737.5f),
                        PathNode.QuadTo(79.2f, 715.1f, 96.9f, 697.1f),
                        PathNode.LineTo(367.9f, 426.1f),
                        PathNode.QuadTo(385.6f, 408.6f, 400.1f, 407.9f),
                        PathNode.QuadTo(414.5f, 407.2f, 430.4f, 423.1f),
                        PathNode.LineTo(443.4f, 436.1f),
                        PathNode.QuadTo(460.8f, 453.8f, 459.6f, 468.8f),
                        PathNode.QuadTo(458.3f, 483.7f, 439.4f, 502.7f),
                        PathNode.LineTo(256.3f, 685.4f),
                        PathNode.LineTo(477.6f, 686.4f),
                        PathNode.LineTo(517.6f, 686.4f),
                        PathNode.QuadTo(632.6f, 686.4f, 691.8f, 682.4f),
                        PathNode.QuadTo(731.5f, 679.4f, 757.8f, 673.3f),
                        PathNode.QuadTo(784.1f, 667.2f, 805.3f, 656.5f),
                        PathNode.QuadTo(844.9f, 637.1f, 876.4f, 605.8f),
                        PathNode.QuadTo(907.8f, 574.5f, 928.2f, 534.6f),
                        PathNode.QuadTo(939.0f, 514.4f, 945.1f, 487.6f),
                        PathNode.QuadTo(951.2f, 460.8f, 955.2f, 421.1f),
                        PathNode.QuadTo(958.2f, 384.6f, 958.7f, 328.3f),
                        PathNode.QuadTo(959.2f, 271.9f, 959.2f, 205.9f),
                        PathNode.LineTo(959.2f, 117.9f),
                        PathNode.QuadTo(959.2f, 99.1f, 969.5f, 87.8f),
                        PathNode.QuadTo(979.8f, 76.4f, 1000.6f, 76.4f),
                        PathNode.LineTo(1019.6f, 76.4f),
                        PathNode.QuadTo(1043.7f, 76.4f, 1053.9f, 87.1f),
                        PathNode.QuadTo(1064.1f, 97.8f, 1064.1f, 117.9f),
                        PathNode.LineTo(1064.1f, 207.9f),
                        PathNode.QuadTo(1064.1f, 273.2f, 1063.1f, 332.3f),
                        PathNode.QuadTo(1062.1f, 391.4f, 1059.1f, 429.7f),
                        PathNode.QuadTo(1055.1f, 476.2f, 1046.3f, 513.2f),
                        PathNode.QuadTo(1037.5f, 550.1f, 1022.0f, 581.2f),
                        PathNode.QuadTo(993.5f, 636.3f, 950.2f, 679.5f),
                        PathNode.QuadTo(907.0f, 722.7f, 852.9f, 750.3f),
                        PathNode.QuadTo(821.1f, 765.8f, 784.0f, 774.6f),
                        PathNode.QuadTo(747.0f, 783.4f, 700.4f, 787.4f),
                        PathNode.QuadTo(642.9f, 791.4f, 478.6f, 791.4f),
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

val MiuixIcons.Glass.Demibold.Undo: ImageVector
    get() {
        if (_undoDemibold != null) return _undoDemibold!!
        _undoDemibold = ImageVector.Builder(
            name = "Undo.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = true,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(272.6f, 797.0f),
                        PathNode.LineTo(451.1f, 975.4f),
                        PathNode.QuadTo(465.1f, 989.5f, 466.6f, 1006.1f),
                        PathNode.QuadTo(468.2f, 1022.8f, 452.1f, 1039.4f),
                        PathNode.LineTo(433.1f, 1058.4f),
                        PathNode.QuadTo(417.5f, 1074.0f, 399.6f, 1073.0f),
                        PathNode.QuadTo(381.7f, 1072.0f, 369.1f, 1059.4f),
                        PathNode.LineTo(92.1f, 783.4f),
                        PathNode.QuadTo(71.9f, 762.8f, 72.2f, 737.6f),
                        PathNode.QuadTo(72.5f, 712.5f, 92.1f, 692.4f),
                        PathNode.LineTo(363.1f, 421.4f),
                        PathNode.QuadTo(383.5f, 401.4f, 400.7f, 401.3f),
                        PathNode.QuadTo(417.8f, 401.2f, 435.1f, 418.4f),
                        PathNode.LineTo(448.1f, 431.4f),
                        PathNode.QuadTo(467.2f, 451.0f, 466.1f, 468.7f),
                        PathNode.QuadTo(465.1f, 486.3f, 444.1f, 507.4f),
                        PathNode.LineTo(272.2f, 678.8f),
                        PathNode.LineTo(477.6f, 679.8f),
                        PathNode.LineTo(517.6f, 679.8f),
                        PathNode.QuadTo(632.6f, 679.8f, 691.2f, 675.8f),
                        PathNode.QuadTo(730.7f, 672.8f, 756.2f, 667.0f),
                        PathNode.QuadTo(781.6f, 661.2f, 802.2f, 650.7f),
                        PathNode.QuadTo(840.9f, 631.6f, 871.6f, 601.1f),
                        PathNode.QuadTo(902.2f, 570.7f, 922.3f, 531.5f),
                        PathNode.QuadTo(932.9f, 511.9f, 938.7f, 486.0f),
                        PathNode.QuadTo(944.5f, 460.1f, 948.5f, 420.5f),
                        PathNode.QuadTo(951.5f, 384.4f, 952.0f, 328.2f),
                        PathNode.QuadTo(952.5f, 271.9f, 952.5f, 205.9f),
                        PathNode.LineTo(952.5f, 117.9f),
                        PathNode.QuadTo(952.5f, 96.5f, 964.8f, 83.1f),
                        PathNode.QuadTo(977.1f, 69.8f, 1000.6f, 69.8f),
                        PathNode.LineTo(1019.6f, 69.8f),
                        PathNode.QuadTo(1046.5f, 69.8f, 1058.6f, 82.4f),
                        PathNode.QuadTo(1070.6f, 95.0f, 1070.6f, 117.9f),
                        PathNode.LineTo(1070.6f, 207.9f),
                        PathNode.QuadTo(1070.6f, 273.4f, 1069.6f, 332.6f),
                        PathNode.QuadTo(1068.6f, 391.8f, 1065.6f, 430.3f),
                        PathNode.QuadTo(1061.6f, 477.2f, 1052.7f, 514.8f),
                        PathNode.QuadTo(1043.7f, 552.4f, 1027.8f, 584.3f),
                        PathNode.QuadTo(998.9f, 640.1f, 954.8f, 684.2f),
                        PathNode.QuadTo(910.8f, 728.2f, 855.9f, 756.1f),
                        PathNode.QuadTo(823.5f, 772.1f, 785.7f, 781.0f),
                        PathNode.QuadTo(747.9f, 790.0f, 700.9f, 794.0f),
                        PathNode.QuadTo(643.0f, 798.0f, 478.6f, 798.0f),
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
