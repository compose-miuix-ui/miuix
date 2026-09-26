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

val MiuixIcons.Glass.Edit: ImageVector
    get() = MiuixIcons.Glass.Regular.Edit

val MiuixIcons.Glass.Light.Edit: ImageVector
    get() {
        if (_editLight != null) return _editLight!!
        _editLight = ImageVector.Builder(
            name = "Edit.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(889.6f, 122.7f),
                        PathNode.QuadTo(941.0f, 148.5f, 966.8f, 199.9f),
                        PathNode.QuadTo(979.9f, 226.1f, 983.2f, 263.9f),
                        PathNode.QuadTo(986.5f, 301.6f, 986.5f, 393.7f),
                        PathNode.LineTo(986.5f, 735.4f),
                        PathNode.QuadTo(986.5f, 744.5f, 981.6f, 748.7f),
                        PathNode.QuadTo(976.8f, 753.0f, 970.6f, 752.5f),
                        PathNode.QuadTo(964.5f, 752.0f, 960.3f, 747.8f),
                        PathNode.LineTo(940.4f, 727.4f),
                        PathNode.QuadTo(932.9f, 719.9f, 930.0f, 711.1f),
                        PathNode.QuadTo(927.1f, 702.4f, 927.1f, 690.0f),
                        PathNode.LineTo(927.1f, 350.7f),
                        PathNode.QuadTo(927.1f, 298.0f, 924.7f, 273.1f),
                        PathNode.QuadTo(922.3f, 248.1f, 913.8f, 229.9f),
                        PathNode.QuadTo(897.5f, 193.0f, 860.6f, 175.7f),
                        PathNode.QuadTo(841.8f, 166.8f, 817.1f, 164.6f),
                        PathNode.QuadTo(792.4f, 162.4f, 739.8f, 162.4f),
                        PathNode.LineTo(353.8f, 162.4f),
                        PathNode.QuadTo(302.1f, 162.4f, 276.7f, 164.8f),
                        PathNode.QuadTo(251.3f, 167.2f, 233.0f, 175.7f),
                        PathNode.QuadTo(198.6f, 191.6f, 178.8f, 229.5f),
                        PathNode.QuadTo(169.9f, 247.2f, 167.7f, 271.5f),
                        PathNode.QuadTo(165.5f, 295.8f, 165.5f, 350.7f),
                        PathNode.LineTo(165.5f, 752.7f),
                        PathNode.QuadTo(165.5f, 808.0f, 167.7f, 832.3f),
                        PathNode.QuadTo(169.9f, 856.6f, 178.8f, 874.9f),
                        PathNode.QuadTo(198.1f, 912.8f, 232.6f, 928.1f),
                        PathNode.QuadTo(250.8f, 936.6f, 276.0f, 938.8f),
                        PathNode.QuadTo(301.3f, 941.0f, 353.8f, 941.0f),
                        PathNode.LineTo(707.0f, 941.0f),
                        PathNode.QuadTo(732.7f, 941.0f, 744.7f, 952.5f),
                        PathNode.LineTo(765.8f, 972.5f),
                        PathNode.QuadTo(772.3f, 978.9f, 772.5f, 985.4f),
                        PathNode.QuadTo(772.7f, 991.8f, 767.6f, 996.1f),
                        PathNode.QuadTo(762.5f, 1000.4f, 755.0f, 1000.4f),
                        PathNode.LineTo(397.8f, 1000.4f),
                        PathNode.QuadTo(307.0f, 1000.4f, 268.4f, 997.1f),
                        PathNode.QuadTo(229.7f, 993.8f, 203.0f, 980.7f),
                        PathNode.QuadTo(152.1f, 954.9f, 125.8f, 903.5f),
                        PathNode.QuadTo(112.7f, 877.2f, 109.9f, 839.5f),
                        PathNode.QuadTo(107.1f, 801.8f, 107.1f, 709.7f),
                        PathNode.LineTo(107.1f, 393.7f),
                        PathNode.QuadTo(107.1f, 301.6f, 109.9f, 263.9f),
                        PathNode.QuadTo(112.7f, 226.1f, 125.8f, 199.9f),
                        PathNode.QuadTo(152.6f, 148.5f, 203.0f, 122.7f),
                        PathNode.QuadTo(229.3f, 109.6f, 267.3f, 106.3f),
                        PathNode.QuadTo(305.3f, 103.0f, 397.8f, 103.0f),
                        PathNode.LineTo(695.8f, 103.0f),
                        PathNode.QuadTo(787.9f, 103.0f, 825.6f, 106.3f),
                        PathNode.QuadTo(863.4f, 109.6f, 889.6f, 122.7f),
                        PathNode.Close,
                        PathNode.MoveTo(559.5f, 495.0f),
                        PathNode.LineTo(1026.6f, 963.1f),
                        PathNode.QuadTo(1036.5f, 973.0f, 1035.6f, 987.0f),
                        PathNode.QuadTo(1034.8f, 1001.0f, 1027.5f, 1008.4f),
                        PathNode.LineTo(1004.5f, 1031.4f),
                        PathNode.QuadTo(996.1f, 1039.2f, 981.6f, 1039.8f),
                        PathNode.QuadTo(967.1f, 1040.4f, 956.1f, 1029.4f),
                        PathNode.LineTo(491.1f, 563.4f),
                        PathNode.QuadTo(457.1f, 530.8f, 434.4f, 490.0f),
                        PathNode.LineTo(393.7f, 414.4f),
                        PathNode.QuadTo(390.7f, 408.8f, 392.4f, 403.9f),
                        PathNode.QuadTo(394.0f, 398.9f, 398.8f, 397.2f),
                        PathNode.QuadTo(403.5f, 395.6f, 408.6f, 398.1f),
                        PathNode.LineTo(485.1f, 439.2f),
                        PathNode.QuadTo(523.5f, 460.9f, 559.5f, 495.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editLight!!
    }

private var _editLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Edit: ImageVector
    get() {
        if (_editNormal != null) return _editNormal!!
        _editNormal = ImageVector.Builder(
            name = "Edit.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(894.1f, 114.6f),
                        PathNode.QuadTo(948.0f, 141.9f, 975.3f, 195.8f),
                        PathNode.QuadTo(989.0f, 223.2f, 992.4f, 262.2f),
                        PathNode.QuadTo(995.8f, 301.1f, 995.8f, 393.8f),
                        PathNode.LineTo(995.8f, 730.9f),
                        PathNode.QuadTo(995.8f, 743.4f, 989.2f, 749.2f),
                        PathNode.QuadTo(982.5f, 755.0f, 974.1f, 754.5f),
                        PathNode.QuadTo(965.6f, 754.0f, 960.2f, 748.6f),
                        PathNode.LineTo(935.4f, 722.9f),
                        PathNode.QuadTo(925.5f, 713.0f, 921.8f, 701.3f),
                        PathNode.QuadTo(918.2f, 689.7f, 918.2f, 672.8f),
                        PathNode.LineTo(918.2f, 350.8f),
                        PathNode.QuadTo(918.2f, 297.3f, 916.0f, 274.0f),
                        PathNode.QuadTo(913.9f, 250.7f, 905.8f, 234.0f),
                        PathNode.QuadTo(890.3f, 200.5f, 856.8f, 184.1f),
                        PathNode.QuadTo(839.3f, 175.8f, 816.1f, 173.7f),
                        PathNode.QuadTo(792.9f, 171.7f, 740.0f, 171.7f),
                        PathNode.LineTo(354.0f, 171.7f),
                        PathNode.QuadTo(301.4f, 171.7f, 277.7f, 173.8f),
                        PathNode.QuadTo(253.9f, 175.9f, 237.2f, 184.1f),
                        PathNode.QuadTo(205.8f, 199.4f, 187.3f, 233.9f),
                        PathNode.QuadTo(179.0f, 250.4f, 176.9f, 273.5f),
                        PathNode.QuadTo(174.8f, 296.6f, 174.8f, 350.8f),
                        PathNode.LineTo(174.8f, 752.8f),
                        PathNode.QuadTo(174.8f, 807.3f, 176.9f, 830.3f),
                        PathNode.QuadTo(179.0f, 853.4f, 187.3f, 870.8f),
                        PathNode.QuadTo(205.7f, 905.3f, 237.1f, 919.7f),
                        PathNode.QuadTo(253.8f, 927.9f, 277.5f, 929.9f),
                        PathNode.QuadTo(301.2f, 932.0f, 354.0f, 932.0f),
                        PathNode.LineTo(689.2f, 932.0f),
                        PathNode.QuadTo(724.0f, 932.0f, 738.8f, 945.9f),
                        PathNode.LineTo(766.0f, 971.4f),
                        PathNode.QuadTo(774.9f, 980.3f, 775.0f, 989.2f),
                        PathNode.QuadTo(775.0f, 998.1f, 767.9f, 1003.9f),
                        PathNode.QuadTo(760.8f, 1009.7f, 751.0f, 1009.7f),
                        PathNode.LineTo(398.0f, 1009.7f),
                        PathNode.QuadTo(305.7f, 1009.7f, 266.1f, 1006.2f),
                        PathNode.QuadTo(226.5f, 1002.8f, 199.0f, 989.1f),
                        PathNode.QuadTo(145.9f, 961.8f, 117.8f, 907.9f),
                        PathNode.QuadTo(104.0f, 880.4f, 101.1f, 841.5f),
                        PathNode.QuadTo(98.2f, 802.6f, 98.2f, 709.8f),
                        PathNode.LineTo(98.2f, 393.8f),
                        PathNode.QuadTo(98.2f, 301.1f, 101.1f, 262.2f),
                        PathNode.QuadTo(104.0f, 223.2f, 117.8f, 195.8f),
                        PathNode.QuadTo(146.1f, 141.9f, 199.0f, 114.6f),
                        PathNode.QuadTo(226.4f, 100.9f, 265.8f, 97.4f),
                        PathNode.QuadTo(305.2f, 94.0f, 398.0f, 94.0f),
                        PathNode.LineTo(696.0f, 94.0f),
                        PathNode.QuadTo(788.7f, 94.0f, 827.7f, 97.4f),
                        PathNode.QuadTo(866.6f, 100.9f, 894.1f, 114.6f),
                        PathNode.Close,
                        PathNode.MoveTo(566.1f, 488.8f),
                        PathNode.LineTo(1033.8f, 957.5f),
                        PathNode.QuadTo(1045.8f, 969.5f, 1044.6f, 987.6f),
                        PathNode.QuadTo(1043.3f, 1005.8f, 1034.1f, 1014.9f),
                        PathNode.LineTo(1011.1f, 1037.9f),
                        PathNode.QuadTo(1000.9f, 1047.9f, 982.3f, 1048.8f),
                        PathNode.QuadTo(963.7f, 1049.7f, 949.9f, 1035.9f),
                        PathNode.LineTo(484.9f, 569.9f),
                        PathNode.QuadTo(450.9f, 537.1f, 426.6f, 494.8f),
                        PathNode.LineTo(376.8f, 401.7f),
                        PathNode.QuadTo(373.8f, 395.8f, 376.4f, 389.8f),
                        PathNode.QuadTo(379.0f, 383.8f, 384.9f, 381.2f),
                        PathNode.QuadTo(390.8f, 378.6f, 396.6f, 381.5f),
                        PathNode.LineTo(489.9f, 431.4f),
                        PathNode.QuadTo(530.1f, 454.0f, 566.1f, 488.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editNormal!!
    }

private var _editNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Edit: ImageVector
    get() {
        if (_editRegular != null) return _editRegular!!
        _editRegular = ImageVector.Builder(
            name = "Edit.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(896.1f, 110.9f),
                        PathNode.QuadTo(951.1f, 138.9f, 979.1f, 193.9f),
                        PathNode.QuadTo(993.1f, 221.9f, 996.6f, 261.4f),
                        PathNode.QuadTo(1000.1f, 300.9f, 1000.1f, 393.9f),
                        PathNode.LineTo(1000.1f, 728.9f),
                        PathNode.QuadTo(1000.1f, 742.9f, 992.6f, 749.4f),
                        PathNode.QuadTo(985.1f, 755.9f, 975.6f, 755.4f),
                        PathNode.QuadTo(966.1f, 754.9f, 960.1f, 748.9f),
                        PathNode.LineTo(933.1f, 720.9f),
                        PathNode.QuadTo(922.1f, 709.9f, 918.1f, 696.9f),
                        PathNode.QuadTo(914.1f, 683.9f, 914.1f, 664.9f),
                        PathNode.LineTo(914.1f, 350.9f),
                        PathNode.QuadTo(914.1f, 296.9f, 912.1f, 274.4f),
                        PathNode.QuadTo(910.1f, 251.9f, 902.1f, 235.9f),
                        PathNode.QuadTo(887.1f, 203.9f, 855.1f, 187.9f),
                        PathNode.QuadTo(838.1f, 179.9f, 815.6f, 177.9f),
                        PathNode.QuadTo(793.1f, 175.9f, 740.1f, 175.9f),
                        PathNode.LineTo(354.1f, 175.9f),
                        PathNode.QuadTo(301.1f, 175.9f, 278.1f, 177.9f),
                        PathNode.QuadTo(255.1f, 179.9f, 239.1f, 187.9f),
                        PathNode.QuadTo(209.1f, 202.9f, 191.1f, 235.9f),
                        PathNode.QuadTo(183.1f, 251.9f, 181.1f, 274.4f),
                        PathNode.QuadTo(179.1f, 296.9f, 179.1f, 350.9f),
                        PathNode.LineTo(179.1f, 752.9f),
                        PathNode.QuadTo(179.1f, 806.9f, 181.1f, 829.4f),
                        PathNode.QuadTo(183.1f, 851.9f, 191.1f, 868.9f),
                        PathNode.QuadTo(209.1f, 901.9f, 239.1f, 915.9f),
                        PathNode.QuadTo(255.1f, 923.9f, 278.1f, 925.9f),
                        PathNode.QuadTo(301.1f, 927.9f, 354.1f, 927.9f),
                        PathNode.LineTo(681.1f, 927.9f),
                        PathNode.QuadTo(720.1f, 927.9f, 736.1f, 942.9f),
                        PathNode.LineTo(766.1f, 970.9f),
                        PathNode.QuadTo(776.1f, 980.9f, 776.1f, 990.9f),
                        PathNode.QuadTo(776.1f, 1000.9f, 768.1f, 1007.4f),
                        PathNode.QuadTo(760.1f, 1013.9f, 749.1f, 1013.9f),
                        PathNode.LineTo(398.1f, 1013.9f),
                        PathNode.QuadTo(305.1f, 1013.9f, 265.1f, 1010.4f),
                        PathNode.QuadTo(225.1f, 1006.9f, 197.1f, 992.9f),
                        PathNode.QuadTo(143.1f, 964.9f, 114.1f, 909.9f),
                        PathNode.QuadTo(100.1f, 881.9f, 97.1f, 842.4f),
                        PathNode.QuadTo(94.1f, 802.9f, 94.1f, 709.9f),
                        PathNode.LineTo(94.1f, 393.9f),
                        PathNode.QuadTo(94.1f, 300.9f, 97.1f, 261.4f),
                        PathNode.QuadTo(100.1f, 221.9f, 114.1f, 193.9f),
                        PathNode.QuadTo(143.1f, 138.9f, 197.1f, 110.9f),
                        PathNode.QuadTo(225.1f, 96.9f, 265.1f, 93.4f),
                        PathNode.QuadTo(305.1f, 89.9f, 398.1f, 89.9f),
                        PathNode.LineTo(696.1f, 89.9f),
                        PathNode.QuadTo(789.1f, 89.9f, 828.6f, 93.4f),
                        PathNode.QuadTo(868.1f, 96.9f, 896.1f, 110.9f),
                        PathNode.Close,
                        PathNode.MoveTo(569.1f, 485.9f),
                        PathNode.LineTo(1037.1f, 954.9f),
                        PathNode.QuadTo(1050.1f, 967.9f, 1048.6f, 987.9f),
                        PathNode.QuadTo(1047.1f, 1007.9f, 1037.1f, 1017.9f),
                        PathNode.LineTo(1014.1f, 1040.9f),
                        PathNode.QuadTo(1003.1f, 1051.9f, 982.6f, 1052.9f),
                        PathNode.QuadTo(962.1f, 1053.9f, 947.1f, 1038.9f),
                        PathNode.LineTo(482.1f, 572.9f),
                        PathNode.QuadTo(448.1f, 539.9f, 423.1f, 496.9f),
                        PathNode.LineTo(369.1f, 395.9f),
                        PathNode.QuadTo(366.1f, 389.9f, 369.1f, 383.4f),
                        PathNode.QuadTo(372.1f, 376.9f, 378.6f, 373.9f),
                        PathNode.QuadTo(385.1f, 370.9f, 391.1f, 373.9f),
                        PathNode.LineTo(492.1f, 427.9f),
                        PathNode.QuadTo(533.1f, 450.9f, 569.1f, 485.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editRegular!!
    }

private var _editRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Edit: ImageVector
    get() {
        if (_editMedium != null) return _editMedium!!
        _editMedium = ImageVector.Builder(
            name = "Edit.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(899.9f, 103.6f),
                        PathNode.QuadTo(957.1f, 133.0f, 986.4f, 190.1f),
                        PathNode.QuadTo(1000.9f, 219.5f, 1004.6f, 260.1f),
                        PathNode.QuadTo(1008.2f, 300.6f, 1008.2f, 393.9f),
                        PathNode.LineTo(1008.2f, 707.3f),
                        PathNode.QuadTo(1008.2f, 724.8f, 998.4f, 733.6f),
                        PathNode.QuadTo(988.6f, 742.4f, 975.7f, 741.9f),
                        PathNode.QuadTo(962.9f, 741.4f, 954.4f, 733.0f),
                        PathNode.LineTo(927.4f, 705.0f),
                        PathNode.QuadTo(915.4f, 692.9f, 910.7f, 678.1f),
                        PathNode.QuadTo(906.0f, 663.4f, 906.0f, 643.3f),
                        PathNode.LineTo(906.0f, 350.9f),
                        PathNode.QuadTo(906.0f, 297.4f, 904.1f, 275.9f),
                        PathNode.QuadTo(902.3f, 254.3f, 894.8f, 239.4f),
                        PathNode.QuadTo(880.9f, 209.8f, 851.6f, 195.2f),
                        PathNode.QuadTo(835.7f, 187.7f, 814.3f, 185.9f),
                        PathNode.QuadTo(792.8f, 184.0f, 740.1f, 184.0f),
                        PathNode.LineTo(354.1f, 184.0f),
                        PathNode.QuadTo(301.4f, 184.0f, 279.3f, 185.9f),
                        PathNode.QuadTo(257.3f, 187.7f, 242.6f, 195.2f),
                        PathNode.QuadTo(214.8f, 208.8f, 198.4f, 239.7f),
                        PathNode.QuadTo(190.9f, 254.3f, 189.1f, 275.9f),
                        PathNode.QuadTo(187.2f, 297.4f, 187.2f, 350.9f),
                        PathNode.LineTo(187.2f, 752.9f),
                        PathNode.QuadTo(187.2f, 806.4f, 189.1f, 827.9f),
                        PathNode.QuadTo(190.9f, 849.5f, 198.4f, 865.1f),
                        PathNode.QuadTo(214.5f, 895.4f, 242.6f, 908.6f),
                        PathNode.QuadTo(257.3f, 916.1f, 279.3f, 917.9f),
                        PathNode.QuadTo(301.4f, 919.8f, 354.1f, 919.8f),
                        PathNode.LineTo(654.4f, 919.8f),
                        PathNode.QuadTo(696.6f, 919.8f, 714.8f, 937.0f),
                        PathNode.LineTo(744.8f, 965.2f),
                        PathNode.QuadTo(757.5f, 977.7f, 757.5f, 990.9f),
                        PathNode.QuadTo(757.5f, 1004.1f, 747.2f, 1013.1f),
                        PathNode.QuadTo(736.9f, 1022.0f, 722.4f, 1022.0f),
                        PathNode.LineTo(398.1f, 1022.0f),
                        PathNode.QuadTo(304.6f, 1022.0f, 263.6f, 1018.4f),
                        PathNode.QuadTo(222.7f, 1014.7f, 193.3f, 1000.2f),
                        PathNode.QuadTo(137.2f, 970.8f, 106.8f, 913.7f),
                        PathNode.QuadTo(92.3f, 884.3f, 89.1f, 843.9f),
                        PathNode.QuadTo(86.0f, 803.4f, 86.0f, 709.9f),
                        PathNode.LineTo(86.0f, 393.9f),
                        PathNode.QuadTo(86.0f, 300.6f, 89.1f, 260.1f),
                        PathNode.QuadTo(92.3f, 219.5f, 106.8f, 190.1f),
                        PathNode.QuadTo(137.2f, 133.0f, 193.3f, 103.6f),
                        PathNode.QuadTo(222.7f, 89.1f, 263.6f, 85.4f),
                        PathNode.QuadTo(304.6f, 81.8f, 398.1f, 81.8f),
                        PathNode.LineTo(696.1f, 81.8f),
                        PathNode.QuadTo(789.7f, 81.8f, 830.1f, 85.4f),
                        PathNode.QuadTo(870.5f, 89.1f, 899.9f, 103.6f),
                        PathNode.Close,
                        PathNode.MoveTo(574.8f, 480.2f),
                        PathNode.LineTo(1042.8f, 949.2f),
                        PathNode.QuadTo(1058.2f, 964.7f, 1056.7f, 987.9f),
                        PathNode.QuadTo(1055.2f, 1011.1f, 1042.8f, 1023.6f),
                        PathNode.LineTo(1019.8f, 1046.6f),
                        PathNode.QuadTo(1006.4f, 1060.0f, 982.6f, 1061.0f),
                        PathNode.QuadTo(958.9f, 1062.0f, 941.4f, 1044.6f),
                        PathNode.LineTo(476.4f, 578.6f),
                        PathNode.QuadTo(441.4f, 544.5f, 416.1f, 501.0f),
                        PathNode.LineTo(362.1f, 399.7f),
                        PathNode.QuadTo(357.2f, 390.2f, 361.5f, 380.4f),
                        PathNode.QuadTo(365.9f, 370.7f, 375.6f, 366.3f),
                        PathNode.QuadTo(385.4f, 362.0f, 394.9f, 366.9f),
                        PathNode.LineTo(496.2f, 420.9f),
                        PathNode.QuadTo(538.5f, 444.7f, 574.8f, 480.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editMedium!!
    }

private var _editMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Edit: ImageVector
    get() {
        if (_editDemibold != null) return _editDemibold!!
        _editDemibold = ImageVector.Builder(
            name = "Edit.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(902.6f, 98.5f),
                        PathNode.QuadTo(961.2f, 128.8f, 991.5f, 187.5f),
                        PathNode.QuadTo(1006.4f, 217.8f, 1010.2f, 259.1f),
                        PathNode.QuadTo(1013.9f, 300.4f, 1013.9f, 393.9f),
                        PathNode.LineTo(1013.9f, 692.1f),
                        PathNode.QuadTo(1013.9f, 712.1f, 1002.5f, 722.5f),
                        PathNode.QuadTo(991.1f, 732.9f, 975.8f, 732.4f),
                        PathNode.QuadTo(960.6f, 731.9f, 950.5f, 721.8f),
                        PathNode.LineTo(923.5f, 693.8f),
                        PathNode.QuadTo(910.6f, 681.0f, 905.5f, 665.0f),
                        PathNode.QuadTo(900.3f, 649.0f, 900.3f, 628.1f),
                        PathNode.LineTo(900.3f, 350.9f),
                        PathNode.QuadTo(900.3f, 297.8f, 898.6f, 276.9f),
                        PathNode.QuadTo(896.8f, 256.0f, 889.7f, 241.9f),
                        PathNode.QuadTo(876.6f, 214.0f, 849.1f, 200.3f),
                        PathNode.QuadTo(834.0f, 193.2f, 813.3f, 191.5f),
                        PathNode.QuadTo(792.7f, 189.7f, 740.1f, 189.7f),
                        PathNode.LineTo(354.1f, 189.7f),
                        PathNode.QuadTo(301.6f, 189.7f, 280.2f, 191.5f),
                        PathNode.QuadTo(258.8f, 193.2f, 245.1f, 200.3f),
                        PathNode.QuadTo(218.8f, 213.0f, 203.5f, 242.3f),
                        PathNode.QuadTo(196.4f, 256.0f, 194.7f, 276.9f),
                        PathNode.QuadTo(192.9f, 297.8f, 192.9f, 350.9f),
                        PathNode.LineTo(192.9f, 752.9f),
                        PathNode.QuadTo(192.9f, 806.0f, 194.7f, 826.9f),
                        PathNode.QuadTo(196.4f, 847.8f, 203.5f, 862.5f),
                        PathNode.QuadTo(218.3f, 890.9f, 245.1f, 903.5f),
                        PathNode.QuadTo(258.8f, 910.6f, 280.2f, 912.3f),
                        PathNode.QuadTo(301.6f, 914.1f, 354.1f, 914.1f),
                        PathNode.LineTo(635.6f, 914.1f),
                        PathNode.QuadTo(680.1f, 914.1f, 699.8f, 932.8f),
                        PathNode.LineTo(729.8f, 961.3f),
                        PathNode.QuadTo(744.4f, 975.4f, 744.4f, 990.9f),
                        PathNode.QuadTo(744.4f, 1006.4f, 732.5f, 1017.1f),
                        PathNode.QuadTo(720.6f, 1027.7f, 703.6f, 1027.7f),
                        PathNode.LineTo(398.1f, 1027.7f),
                        PathNode.QuadTo(304.2f, 1027.7f, 262.6f, 1024.0f),
                        PathNode.QuadTo(221.0f, 1020.2f, 190.7f, 1005.3f),
                        PathNode.QuadTo(133.0f, 975.0f, 101.7f, 916.3f),
                        PathNode.QuadTo(86.8f, 886.0f, 83.6f, 844.9f),
                        PathNode.QuadTo(80.3f, 803.8f, 80.3f, 709.9f),
                        PathNode.LineTo(80.3f, 393.9f),
                        PathNode.QuadTo(80.3f, 300.4f, 83.6f, 259.1f),
                        PathNode.QuadTo(86.8f, 217.8f, 101.7f, 187.5f),
                        PathNode.QuadTo(133.0f, 128.8f, 190.7f, 98.5f),
                        PathNode.QuadTo(221.0f, 83.6f, 262.6f, 79.8f),
                        PathNode.QuadTo(304.2f, 76.1f, 398.1f, 76.1f),
                        PathNode.LineTo(696.1f, 76.1f),
                        PathNode.QuadTo(790.0f, 76.1f, 831.1f, 79.8f),
                        PathNode.QuadTo(872.3f, 83.6f, 902.6f, 98.5f),
                        PathNode.Close,
                        PathNode.MoveTo(578.8f, 476.3f),
                        PathNode.LineTo(1046.8f, 945.3f),
                        PathNode.QuadTo(1063.9f, 962.4f, 1062.4f, 987.9f),
                        PathNode.QuadTo(1060.9f, 1013.4f, 1046.8f, 1027.6f),
                        PathNode.LineTo(1023.8f, 1050.6f),
                        PathNode.QuadTo(1008.6f, 1065.7f, 982.6f, 1066.7f),
                        PathNode.QuadTo(956.6f, 1067.7f, 937.5f, 1048.6f),
                        PathNode.LineTo(472.5f, 582.6f),
                        PathNode.QuadTo(436.6f, 547.7f, 411.2f, 503.8f),
                        PathNode.LineTo(357.2f, 402.3f),
                        PathNode.QuadTo(351.0f, 390.4f, 356.3f, 378.3f),
                        PathNode.QuadTo(361.6f, 366.3f, 373.6f, 361.0f),
                        PathNode.QuadTo(385.6f, 355.7f, 397.6f, 362.0f),
                        PathNode.LineTo(499.0f, 416.0f),
                        PathNode.QuadTo(542.3f, 440.3f, 578.8f, 476.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _editDemibold!!
    }

private var _editDemibold: ImageVector? = null
