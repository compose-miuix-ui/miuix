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

val MiuixIcons.Edit: ImageVector
    get() = MiuixIcons.Regular.Edit

val MiuixIcons.Light.Edit: ImageVector
    get() {
        if (_editLight != null) return _editLight!!
        _editLight = ImageVector.Builder(
            name = "Edit.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1124.4f,
            viewportHeight = 1124.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1124.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(857.7f, 145.4f),
                        PathNode.QuadTo(905.5f, 169.8f, 929.9f, 217.6f),
                        PathNode.QuadTo(942.9f, 242.0f, 945.7f, 277.4f),
                        PathNode.QuadTo(948.6f, 312.8f, 948.6f, 398.0f),
                        PathNode.VerticalTo(713.2f),
                        PathNode.QuadTo(948.6f, 722.3f, 943.5f, 726.4f),
                        PathNode.QuadTo(938.5f, 730.6f, 932.0f, 730.1f),
                        PathNode.QuadTo(925.5f, 729.6f, 921.5f, 725.6f),
                        PathNode.LineTo(902.6f, 705.8f),
                        PathNode.QuadTo(894.7f, 698.6f, 891.7f, 689.7f),
                        PathNode.QuadTo(888.8f, 680.7f, 888.8f, 667.7f),
                        PathNode.VerticalTo(358.3f),
                        PathNode.QuadTo(888.8f, 309.8f, 886.6f, 286.8f),
                        PathNode.QuadTo(884.3f, 263.7f, 876.9f, 247.4f),
                        PathNode.QuadTo(861.6f, 214.0f, 828.9f, 198.4f),
                        PathNode.QuadTo(812.5f, 190.2f, 790.1f, 188.4f),
                        PathNode.QuadTo(767.8f, 186.5f, 718.0f, 186.5f),
                        PathNode.HorizontalTo(361.0f),
                        PathNode.QuadTo(313.5f, 186.5f, 290.0f, 188.8f),
                        PathNode.QuadTo(266.4f, 191.0f, 250.1f, 198.4f),
                        PathNode.QuadTo(219.3f, 213.0f, 201.1f, 247.4f),
                        PathNode.QuadTo(193.0f, 263.7f, 191.1f, 285.6f),
                        PathNode.QuadTo(189.3f, 307.6f, 189.3f, 358.3f),
                        PathNode.VerticalTo(730.1f),
                        PathNode.QuadTo(189.3f, 780.8f, 191.1f, 803.1f),
                        PathNode.QuadTo(193.0f, 825.4f, 201.1f, 841.9f),
                        PathNode.QuadTo(219.3f, 877.0f, 250.1f, 889.9f),
                        PathNode.QuadTo(266.4f, 898.1f, 288.8f, 899.9f),
                        PathNode.QuadTo(311.2f, 901.8f, 361.0f, 901.8f),
                        PathNode.HorizontalTo(683.2f),
                        PathNode.QuadTo(708.6f, 901.8f, 721.1f, 913.4f),
                        PathNode.LineTo(742.1f, 933.2f),
                        PathNode.QuadTo(749.0f, 940.2f, 749.0f, 946.7f),
                        PathNode.QuadTo(749.0f, 953.3f, 743.9f, 957.4f),
                        PathNode.QuadTo(738.8f, 961.5f, 730.9f, 961.5f),
                        PathNode.HorizontalTo(401.7f),
                        PathNode.QuadTo(318.0f, 961.5f, 281.7f, 958.3f),
                        PathNode.QuadTo(245.5f, 955.1f, 220.4f, 942.9f),
                        PathNode.QuadTo(172.0f, 917.8f, 148.2f, 870.7f),
                        PathNode.QuadTo(136.0f, 846.3f, 133.2f, 810.9f),
                        PathNode.QuadTo(130.5f, 775.5f, 130.5f, 690.3f),
                        PathNode.VerticalTo(398.0f),
                        PathNode.QuadTo(130.5f, 312.8f, 133.2f, 277.4f),
                        PathNode.QuadTo(136.0f, 242.0f, 148.2f, 217.6f),
                        PathNode.QuadTo(173.5f, 169.8f, 220.4f, 145.4f),
                        PathNode.QuadTo(244.7f, 132.5f, 280.2f, 129.6f),
                        PathNode.QuadTo(315.7f, 126.8f, 401.7f, 126.8f),
                        PathNode.HorizontalTo(677.3f),
                        PathNode.QuadTo(762.6f, 126.8f, 797.9f, 129.6f),
                        PathNode.QuadTo(833.3f, 132.5f, 857.7f, 145.4f),
                        PathNode.Close,
                        PathNode.MoveTo(553.0f, 490.0f),
                        PathNode.LineTo(985.1f, 923.0f),
                        PathNode.QuadTo(994.1f, 932.7f, 993.4f, 946.6f),
                        PathNode.QuadTo(992.8f, 960.6f, 985.8f, 968.3f),
                        PathNode.LineTo(964.6f, 989.6f),
                        PathNode.QuadTo(956.7f, 997.4f, 942.3f, 997.6f),
                        PathNode.QuadTo(927.9f, 997.8f, 916.3f, 987.7f),
                        PathNode.LineTo(486.2f, 556.7f),
                        PathNode.QuadTo(455.6f, 526.2f, 433.9f, 488.0f),
                        PathNode.LineTo(393.9f, 414.3f),
                        PathNode.QuadTo(391.1f, 408.8f, 392.8f, 403.9f),
                        PathNode.QuadTo(394.4f, 399.1f, 399.3f, 397.4f),
                        PathNode.QuadTo(404.1f, 395.8f, 408.9f, 397.8f),
                        PathNode.LineTo(483.3f, 438.6f),
                        PathNode.QuadTo(519.0f, 458.4f, 553.0f, 490.0f),
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

val MiuixIcons.Normal.Edit: ImageVector
    get() {
        if (_editNormal != null) return _editNormal!!
        _editNormal = ImageVector.Builder(
            name = "Edit.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1146.7f,
            viewportHeight = 1146.7f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1146.7f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(872.2f, 148.6f),
                        PathNode.QuadTo(922.7f, 174.3f, 948.4f, 224.8f),
                        PathNode.QuadTo(961.3f, 250.5f, 964.5f, 286.9f),
                        PathNode.QuadTo(967.7f, 323.3f, 967.7f, 409.1f),
                        PathNode.VerticalTo(718.9f),
                        PathNode.QuadTo(967.7f, 731.5f, 961.0f, 737.3f),
                        PathNode.QuadTo(954.2f, 743.2f, 945.7f, 742.7f),
                        PathNode.QuadTo(937.1f, 742.2f, 931.7f, 736.8f),
                        PathNode.LineTo(907.3f, 711.5f),
                        PathNode.QuadTo(897.3f, 701.6f, 893.7f, 689.9f),
                        PathNode.QuadTo(890.1f, 678.2f, 890.1f, 661.0f),
                        PathNode.VerticalTo(369.4f),
                        PathNode.QuadTo(890.1f, 319.7f, 888.2f, 298.7f),
                        PathNode.QuadTo(886.3f, 277.7f, 878.9f, 262.8f),
                        PathNode.QuadTo(865.0f, 232.9f, 835.2f, 218.1f),
                        PathNode.QuadTo(819.4f, 210.6f, 798.5f, 208.8f),
                        PathNode.QuadTo(777.6f, 206.9f, 728.6f, 206.9f),
                        PathNode.HorizontalTo(372.1f),
                        PathNode.QuadTo(323.3f, 206.9f, 301.9f, 208.8f),
                        PathNode.QuadTo(280.4f, 210.7f, 265.5f, 218.1f),
                        PathNode.QuadTo(237.6f, 232.0f, 220.8f, 262.8f),
                        PathNode.QuadTo(213.4f, 277.7f, 211.5f, 298.6f),
                        PathNode.QuadTo(209.7f, 319.5f, 209.7f, 369.4f),
                        PathNode.VerticalTo(740.6f),
                        PathNode.QuadTo(209.7f, 790.5f, 211.5f, 811.4f),
                        PathNode.QuadTo(213.4f, 832.3f, 220.8f, 848.1f),
                        PathNode.QuadTo(237.6f, 879.0f, 265.5f, 891.9f),
                        PathNode.QuadTo(280.4f, 899.4f, 301.8f, 901.2f),
                        PathNode.QuadTo(323.1f, 903.1f, 372.1f, 903.1f),
                        PathNode.HorizontalTo(675.8f),
                        PathNode.QuadTo(710.9f, 903.1f, 725.5f, 916.7f),
                        PathNode.LineTo(752.6f, 942.0f),
                        PathNode.QuadTo(761.6f, 951.0f, 761.6f, 960.0f),
                        PathNode.QuadTo(761.6f, 969.0f, 754.4f, 974.9f),
                        PathNode.QuadTo(747.2f, 980.7f, 737.3f, 980.7f),
                        PathNode.HorizontalTo(412.8f),
                        PathNode.QuadTo(327.1f, 980.7f, 290.2f, 977.5f),
                        PathNode.QuadTo(253.4f, 974.2f, 227.6f, 961.4f),
                        PathNode.QuadTo(177.9f, 935.6f, 151.3f, 885.1f),
                        PathNode.QuadTo(138.5f, 859.4f, 135.7f, 823.0f),
                        PathNode.QuadTo(133.0f, 786.7f, 133.0f, 700.9f),
                        PathNode.VerticalTo(409.1f),
                        PathNode.QuadTo(133.0f, 323.3f, 135.7f, 286.9f),
                        PathNode.QuadTo(138.5f, 250.5f, 151.3f, 224.8f),
                        PathNode.QuadTo(178.0f, 174.3f, 227.6f, 148.6f),
                        PathNode.QuadTo(253.3f, 135.7f, 290.1f, 132.5f),
                        PathNode.QuadTo(326.9f, 129.3f, 412.8f, 129.3f),
                        PathNode.HorizontalTo(687.9f),
                        PathNode.QuadTo(773.7f, 129.3f, 810.1f, 132.5f),
                        PathNode.QuadTo(846.5f, 135.7f, 872.2f, 148.6f),
                        PathNode.Close,
                        PathNode.MoveTo(570.1f, 494.7f),
                        PathNode.LineTo(1002.1f, 927.6f),
                        PathNode.QuadTo(1013.8f, 939.5f, 1012.5f, 957.5f),
                        PathNode.QuadTo(1011.2f, 975.6f, 1002.2f, 984.7f),
                        PathNode.LineTo(980.9f, 1005.9f),
                        PathNode.QuadTo(971.0f, 1015.8f, 952.5f, 1016.7f),
                        PathNode.QuadTo(934.0f, 1017.6f, 920.3f, 1004.1f),
                        PathNode.LineTo(490.9f, 573.8f),
                        PathNode.QuadTo(459.6f, 543.3f, 436.7f, 503.7f),
                        PathNode.LineTo(387.7f, 412.2f),
                        PathNode.QuadTo(384.9f, 406.7f, 387.6f, 400.8f),
                        PathNode.QuadTo(390.3f, 394.9f, 396.2f, 392.2f),
                        PathNode.QuadTo(402.0f, 389.6f, 407.5f, 392.3f),
                        PathNode.LineTo(499.1f, 441.3f),
                        PathNode.QuadTo(536.7f, 462.4f, 570.1f, 494.7f),
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

val MiuixIcons.Regular.Edit: ImageVector
    get() {
        if (_editRegular != null) return _editRegular!!
        _editRegular = ImageVector.Builder(
            name = "Edit.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1156.8f,
            viewportHeight = 1156.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1156.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(878.7f, 150.2f),
                        PathNode.QuadTo(930.7f, 176.3f, 956.8f, 228.3f),
                        PathNode.QuadTo(970.2f, 254.6f, 973.5f, 291.5f),
                        PathNode.QuadTo(976.7f, 328.5f, 976.7f, 414.2f),
                        PathNode.VerticalTo(713.7f),
                        PathNode.QuadTo(976.7f, 728.2f, 968.8f, 735.2f),
                        PathNode.QuadTo(960.8f, 742.2f, 950.5f, 741.8f),
                        PathNode.QuadTo(940.2f, 741.3f, 933.7f, 734.7f),
                        PathNode.LineTo(908.8f, 708.9f),
                        PathNode.QuadTo(898.1f, 698.3f, 894.2f, 685.5f),
                        PathNode.QuadTo(890.2f, 672.8f, 890.2f, 654.7f),
                        PathNode.VerticalTo(374.6f),
                        PathNode.QuadTo(890.2f, 325.0f, 888.5f, 304.7f),
                        PathNode.QuadTo(886.8f, 284.3f, 879.7f, 270.0f),
                        PathNode.QuadTo(866.1f, 241.6f, 837.9f, 227.3f),
                        PathNode.QuadTo(822.7f, 220.2f, 802.5f, 218.5f),
                        PathNode.QuadTo(782.3f, 216.7f, 733.4f, 216.7f),
                        PathNode.HorizontalTo(377.4f),
                        PathNode.QuadTo(328.5f, 216.7f, 307.8f, 218.5f),
                        PathNode.QuadTo(287.1f, 220.2f, 272.9f, 227.3f),
                        PathNode.QuadTo(246.0f, 240.9f, 230.1f, 270.0f),
                        PathNode.QuadTo(223.0f, 284.3f, 221.3f, 304.7f),
                        PathNode.QuadTo(219.6f, 325.0f, 219.6f, 374.6f),
                        PathNode.VerticalTo(745.3f),
                        PathNode.QuadTo(219.6f, 794.8f, 221.3f, 815.2f),
                        PathNode.QuadTo(223.0f, 835.6f, 230.1f, 850.8f),
                        PathNode.QuadTo(246.0f, 879.9f, 272.9f, 892.5f),
                        PathNode.QuadTo(287.1f, 899.7f, 307.8f, 901.4f),
                        PathNode.QuadTo(328.5f, 903.1f, 377.4f, 903.1f),
                        PathNode.HorizontalTo(667.5f),
                        PathNode.QuadTo(704.3f, 903.1f, 720.5f, 918.0f),
                        PathNode.LineTo(748.2f, 943.8f),
                        PathNode.QuadTo(758.7f, 954.3f, 758.5f, 964.9f),
                        PathNode.QuadTo(758.4f, 975.5f, 750.0f, 982.5f),
                        PathNode.QuadTo(741.6f, 989.5f, 730.2f, 989.5f),
                        PathNode.HorizontalTo(418.0f),
                        PathNode.QuadTo(332.0f, 989.5f, 294.7f, 986.3f),
                        PathNode.QuadTo(257.4f, 983.1f, 231.1f, 969.6f),
                        PathNode.QuadTo(180.3f, 943.6f, 153.0f, 891.6f),
                        PathNode.QuadTo(139.9f, 865.3f, 137.0f, 828.5f),
                        PathNode.QuadTo(134.1f, 791.7f, 134.1f, 705.6f),
                        PathNode.VerticalTo(414.2f),
                        PathNode.QuadTo(134.1f, 328.5f, 137.0f, 291.5f),
                        PathNode.QuadTo(139.9f, 254.6f, 153.0f, 228.3f),
                        PathNode.QuadTo(180.3f, 176.3f, 231.1f, 150.2f),
                        PathNode.QuadTo(257.4f, 136.8f, 294.7f, 133.5f),
                        PathNode.QuadTo(332.0f, 130.3f, 418.0f, 130.3f),
                        PathNode.HorizontalTo(692.8f),
                        PathNode.QuadTo(778.8f, 130.3f, 815.6f, 133.5f),
                        PathNode.QuadTo(852.4f, 136.8f, 878.7f, 150.2f),
                        PathNode.Close,
                        PathNode.MoveTo(578.2f, 496.5f),
                        PathNode.LineTo(1009.8f, 929.0f),
                        PathNode.QuadTo(1022.5f, 942.1f, 1021.3f, 961.9f),
                        PathNode.QuadTo(1020.0f, 981.7f, 1009.8f, 992.2f),
                        PathNode.LineTo(988.6f, 1013.4f),
                        PathNode.QuadTo(977.2f, 1024.6f, 957.0f, 1025.5f),
                        PathNode.QuadTo(936.8f, 1026.4f, 921.7f, 1011.6f),
                        PathNode.LineTo(492.9f, 581.9f),
                        PathNode.QuadTo(461.3f, 550.9f, 438.0f, 511.0f),
                        PathNode.LineTo(388.2f, 417.6f),
                        PathNode.QuadTo(384.7f, 410.6f, 387.9f, 403.1f),
                        PathNode.QuadTo(391.2f, 395.8f, 398.6f, 392.5f),
                        PathNode.QuadTo(406.0f, 389.2f, 413.0f, 392.7f),
                        PathNode.LineTo(506.4f, 442.5f),
                        PathNode.QuadTo(545.3f, 464.5f, 578.2f, 496.5f),
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

val MiuixIcons.Medium.Edit: ImageVector
    get() {
        if (_editMedium != null) return _editMedium!!
        _editMedium = ImageVector.Builder(
            name = "Edit.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1176.6f,
            viewportHeight = 1176.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1176.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(892.0f, 153.5f),
                        PathNode.QuadTo(946.9f, 180.2f, 973.5f, 235.1f),
                        PathNode.QuadTo(988.1f, 262.5f, 991.3f, 300.6f),
                        PathNode.QuadTo(994.6f, 338.7f, 994.6f, 424.3f),
                        PathNode.VerticalTo(701.4f),
                        PathNode.QuadTo(994.6f, 719.4f, 984.3f, 728.8f),
                        PathNode.QuadTo(974.0f, 738.2f, 960.1f, 737.7f),
                        PathNode.QuadTo(946.3f, 737.3f, 937.4f, 728.3f),
                        PathNode.LineTo(912.5f, 702.5f),
                        PathNode.QuadTo(900.7f, 690.7f, 896.2f, 676.2f),
                        PathNode.QuadTo(891.6f, 661.7f, 891.6f, 642.5f),
                        PathNode.VerticalTo(384.7f),
                        PathNode.QuadTo(891.6f, 335.8f, 890.2f, 316.4f),
                        PathNode.QuadTo(888.8f, 296.9f, 882.3f, 283.9f),
                        PathNode.QuadTo(869.3f, 257.8f, 844.1f, 244.8f),
                        PathNode.QuadTo(830.1f, 238.3f, 811.1f, 236.8f),
                        PathNode.QuadTo(792.1f, 235.4f, 743.3f, 235.4f),
                        PathNode.HorizontalTo(387.8f),
                        PathNode.QuadTo(339.0f, 235.4f, 319.5f, 236.8f),
                        PathNode.QuadTo(300.0f, 238.3f, 287.0f, 244.8f),
                        PathNode.QuadTo(261.9f, 257.8f, 247.9f, 283.9f),
                        PathNode.QuadTo(241.4f, 296.9f, 240.0f, 316.4f),
                        PathNode.QuadTo(238.5f, 335.8f, 238.5f, 384.7f),
                        PathNode.VerticalTo(754.9f),
                        PathNode.QuadTo(238.5f, 803.8f, 240.0f, 823.2f),
                        PathNode.QuadTo(241.4f, 842.7f, 247.9f, 856.6f),
                        PathNode.QuadTo(261.9f, 882.8f, 287.0f, 894.8f),
                        PathNode.QuadTo(300.0f, 901.3f, 319.5f, 902.8f),
                        PathNode.QuadTo(339.0f, 904.2f, 387.8f, 904.2f),
                        PathNode.HorizontalTo(650.8f),
                        PathNode.QuadTo(689.3f, 904.2f, 709.1f, 921.4f),
                        PathNode.LineTo(736.7f, 947.2f),
                        PathNode.QuadTo(750.1f, 960.6f, 749.7f, 974.5f),
                        PathNode.QuadTo(749.3f, 988.4f, 738.5f, 997.7f),
                        PathNode.QuadTo(727.8f, 1007.1f, 713.4f, 1007.1f),
                        PathNode.HorizontalTo(428.3f),
                        PathNode.QuadTo(341.9f, 1007.1f, 303.7f, 1003.9f),
                        PathNode.QuadTo(265.6f, 1000.7f, 238.2f, 986.1f),
                        PathNode.QuadTo(185.0f, 959.5f, 156.6f, 904.6f),
                        PathNode.QuadTo(142.9f, 877.1f, 139.7f, 839.4f),
                        PathNode.QuadTo(136.5f, 801.8f, 136.5f, 715.3f),
                        PathNode.VerticalTo(424.3f),
                        PathNode.QuadTo(136.5f, 338.7f, 139.7f, 300.6f),
                        PathNode.QuadTo(142.9f, 262.5f, 156.6f, 235.1f),
                        PathNode.QuadTo(185.0f, 180.2f, 238.2f, 153.5f),
                        PathNode.QuadTo(265.6f, 138.9f, 303.7f, 135.7f),
                        PathNode.QuadTo(341.9f, 132.5f, 428.3f, 132.5f),
                        PathNode.HorizontalTo(702.8f),
                        PathNode.QuadTo(789.3f, 132.5f, 826.9f, 135.7f),
                        PathNode.QuadTo(864.5f, 138.9f, 892.0f, 153.5f),
                        PathNode.Close,
                        PathNode.MoveTo(594.3f, 500.5f),
                        PathNode.LineTo(1025.3f, 932.4f),
                        PathNode.QuadTo(1039.8f, 947.8f, 1038.8f, 970.9f),
                        PathNode.QuadTo(1037.8f, 994.0f, 1025.3f, 1007.4f),
                        PathNode.LineTo(1004.1f, 1028.6f),
                        PathNode.QuadTo(989.7f, 1042.1f, 966.6f, 1043.0f),
                        PathNode.QuadTo(943.5f, 1043.9f, 925.4f, 1026.7f),
                        PathNode.LineTo(497.2f, 597.6f),
                        PathNode.QuadTo(465.1f, 565.5f, 441.2f, 525.1f),
                        PathNode.LineTo(391.5f, 431.2f),
                        PathNode.QuadTo(386.2f, 420.6f, 390.6f, 410.0f),
                        PathNode.QuadTo(395.1f, 399.3f, 405.7f, 394.9f),
                        PathNode.QuadTo(416.4f, 390.4f, 427.0f, 395.7f),
                        PathNode.LineTo(520.8f, 445.4f),
                        PathNode.QuadTo(562.0f, 469.2f, 594.3f, 500.5f),
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

val MiuixIcons.Demibold.Edit: ImageVector
    get() {
        if (_editDemibold != null) return _editDemibold!!
        _editDemibold = ImageVector.Builder(
            name = "Edit.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1190.4f,
            viewportHeight = 1190.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1190.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(901.3f, 155.9f),
                        PathNode.QuadTo(958.3f, 182.9f, 985.3f, 239.8f),
                        PathNode.QuadTo(1000.7f, 268.1f, 1003.9f, 307.0f),
                        PathNode.QuadTo(1007.1f, 345.8f, 1007.1f, 431.4f),
                        PathNode.VerticalTo(692.9f),
                        PathNode.QuadTo(1007.1f, 713.3f, 995.1f, 724.3f),
                        PathNode.QuadTo(983.2f, 735.3f, 966.9f, 734.9f),
                        PathNode.QuadTo(950.6f, 734.4f, 940.0f, 723.9f),
                        PathNode.LineTo(915.2f, 698.1f),
                        PathNode.QuadTo(902.5f, 685.5f, 897.6f, 669.7f),
                        PathNode.QuadTo(892.7f, 654.0f, 892.7f, 634.0f),
                        PathNode.VerticalTo(391.8f),
                        PathNode.QuadTo(892.7f, 343.4f, 891.5f, 324.6f),
                        PathNode.QuadTo(890.2f, 305.8f, 884.1f, 293.6f),
                        PathNode.QuadTo(871.6f, 269.2f, 848.5f, 257.1f),
                        PathNode.QuadTo(835.4f, 251.0f, 817.2f, 249.7f),
                        PathNode.QuadTo(799.0f, 248.5f, 750.2f, 248.5f),
                        PathNode.HorizontalTo(395.1f),
                        PathNode.QuadTo(346.4f, 248.5f, 327.7f, 249.7f),
                        PathNode.QuadTo(309.1f, 251.0f, 296.9f, 257.1f),
                        PathNode.QuadTo(273.1f, 269.5f, 260.4f, 293.6f),
                        PathNode.QuadTo(254.3f, 305.8f, 253.1f, 324.6f),
                        PathNode.QuadTo(251.8f, 343.4f, 251.8f, 391.8f),
                        PathNode.VerticalTo(761.6f),
                        PathNode.QuadTo(251.8f, 810.1f, 253.1f, 828.9f),
                        PathNode.QuadTo(254.3f, 847.7f, 260.4f, 860.7f),
                        PathNode.QuadTo(273.1f, 884.8f, 296.9f, 896.5f),
                        PathNode.QuadTo(309.1f, 902.6f, 327.7f, 903.8f),
                        PathNode.QuadTo(346.4f, 905.0f, 395.1f, 905.0f),
                        PathNode.HorizontalTo(639.2f),
                        PathNode.QuadTo(678.8f, 905.0f, 701.1f, 923.8f),
                        PathNode.LineTo(728.7f, 949.6f),
                        PathNode.QuadTo(744.3f, 965.1f, 743.6f, 981.2f),
                        PathNode.QuadTo(742.9f, 997.4f, 730.6f, 1008.4f),
                        PathNode.QuadTo(718.2f, 1019.4f, 701.7f, 1019.4f),
                        PathNode.HorizontalTo(435.6f),
                        PathNode.QuadTo(348.8f, 1019.4f, 310.1f, 1016.2f),
                        PathNode.QuadTo(271.4f, 1013.0f, 243.2f, 997.6f),
                        PathNode.QuadTo(188.4f, 970.5f, 159.2f, 913.7f),
                        PathNode.QuadTo(145.1f, 885.4f, 141.7f, 847.1f),
                        PathNode.QuadTo(138.3f, 808.9f, 138.3f, 722.1f),
                        PathNode.VerticalTo(431.4f),
                        PathNode.QuadTo(138.3f, 345.8f, 141.7f, 307.0f),
                        PathNode.QuadTo(145.1f, 268.1f, 159.2f, 239.8f),
                        PathNode.QuadTo(188.4f, 182.9f, 243.2f, 155.9f),
                        PathNode.QuadTo(271.4f, 140.5f, 310.1f, 137.3f),
                        PathNode.QuadTo(348.8f, 134.1f, 435.6f, 134.1f),
                        PathNode.HorizontalTo(709.7f),
                        PathNode.QuadTo(796.6f, 134.1f, 834.9f, 137.3f),
                        PathNode.QuadTo(873.1f, 140.5f, 901.3f, 155.9f),
                        PathNode.Close,
                        PathNode.MoveTo(605.5f, 503.4f),
                        PathNode.LineTo(1036.1f, 934.8f),
                        PathNode.QuadTo(1051.9f, 951.8f, 1051.1f, 977.2f),
                        PathNode.QuadTo(1050.3f, 1002.6f, 1036.1f, 1018.0f),
                        PathNode.LineTo(1014.9f, 1039.2f),
                        PathNode.QuadTo(998.5f, 1054.4f, 973.3f, 1055.3f),
                        PathNode.QuadTo(948.2f, 1056.2f, 928.0f, 1037.3f),
                        PathNode.LineTo(500.3f, 608.7f),
                        PathNode.QuadTo(467.7f, 575.8f, 443.5f, 535.0f),
                        PathNode.LineTo(393.8f, 440.8f),
                        PathNode.QuadTo(387.2f, 427.7f, 392.6f, 414.8f),
                        PathNode.QuadTo(397.8f, 401.9f, 410.8f, 396.6f),
                        PathNode.QuadTo(423.6f, 391.3f, 436.8f, 397.9f),
                        PathNode.LineTo(530.9f, 447.5f),
                        PathNode.QuadTo(573.7f, 472.5f, 605.5f, 503.4f),
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
