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

val MiuixIcons.ContactsCircle: ImageVector
    get() = MiuixIcons.Regular.ContactsCircle

val MiuixIcons.Light.ContactsCircle: ImageVector
    get() {
        if (_contactscircleLight != null) return _contactscircleLight!!
        _contactscircleLight = ImageVector.Builder(
            name = "ContactsCircle.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1168.8f,
            viewportHeight = 1168.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1168.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1071.4f, 584.1f),
                        PathNode.QuadTo(1071.4f, 716.9f, 1006.0f, 828.6f),
                        PathNode.QuadTo(940.6f, 940.2f, 828.3f, 1005.6f),
                        PathNode.QuadTo(716.0f, 1070.9f, 584.2f, 1070.9f),
                        PathNode.QuadTo(452.4f, 1070.9f, 340.2f, 1005.5f),
                        PathNode.QuadTo(228.1f, 940.1f, 162.7f, 828.3f),
                        PathNode.QuadTo(97.4f, 716.5f, 97.4f, 583.7f),
                        PathNode.QuadTo(97.4f, 451.9f, 162.8f, 340.2f),
                        PathNode.QuadTo(228.2f, 228.6f, 340.5f, 163.2f),
                        PathNode.QuadTo(452.8f, 97.9f, 584.6f, 97.9f),
                        PathNode.QuadTo(716.4f, 97.9f, 828.6f, 163.3f),
                        PathNode.QuadTo(940.7f, 228.7f, 1006.1f, 340.5f),
                        PathNode.QuadTo(1071.4f, 452.3f, 1071.4f, 584.1f),
                        PathNode.Close,
                        PathNode.MoveTo(843.4f, 181.9f),
                        PathNode.QuadTo(874.4f, 198.9f, 901.4f, 223.9f),
                        PathNode.QuadTo(928.4f, 248.9f, 942.4f, 273.9f),
                        PathNode.QuadTo(948.4f, 283.9f, 945.4f, 288.9f),
                        PathNode.QuadTo(872.4f, 391.9f, 779.9f, 450.9f),
                        PathNode.QuadTo(687.4f, 509.9f, 584.4f, 509.9f),
                        PathNode.QuadTo(481.4f, 509.9f, 388.4f, 450.9f),
                        PathNode.QuadTo(295.4f, 391.9f, 220.4f, 285.8f),
                        PathNode.QuadTo(219.4f, 282.9f, 225.4f, 276.9f),
                        PathNode.QuadTo(225.4f, 276.9f, 229.4f, 272.9f),
                        PathNode.QuadTo(266.4f, 214.9f, 326.4f, 181.9f),
                        PathNode.QuadTo(363.4f, 163.9f, 420.4f, 152.9f),
                        PathNode.QuadTo(477.4f, 141.9f, 546.4f, 141.9f),
                        PathNode.HorizontalTo(622.4f),
                        PathNode.QuadTo(693.4f, 141.9f, 749.9f, 152.9f),
                        PathNode.QuadTo(806.4f, 163.9f, 843.4f, 181.9f),
                        PathNode.Close,
                        PathNode.MoveTo(160.4f, 583.7f),
                        PathNode.QuadTo(160.4f, 698.9f, 217.4f, 796.4f),
                        PathNode.QuadTo(274.4f, 893.9f, 371.9f, 950.9f),
                        PathNode.QuadTo(469.4f, 1007.9f, 584.4f, 1007.9f),
                        PathNode.QuadTo(699.4f, 1007.9f, 796.9f, 950.9f),
                        PathNode.QuadTo(894.4f, 893.9f, 951.4f, 796.4f),
                        PathNode.QuadTo(1008.4f, 698.9f, 1008.4f, 583.7f),
                        PathNode.QuadTo(1008.4f, 468.6f, 951.4f, 371.7f),
                        PathNode.QuadTo(894.4f, 274.9f, 796.9f, 217.9f),
                        PathNode.QuadTo(699.4f, 160.9f, 584.4f, 160.9f),
                        PathNode.QuadTo(469.4f, 160.9f, 371.9f, 217.9f),
                        PathNode.QuadTo(274.4f, 274.9f, 217.4f, 371.7f),
                        PathNode.QuadTo(160.4f, 468.6f, 160.4f, 583.7f),
                        PathNode.Close,
                        PathNode.MoveTo(736.4f, 725.6f),
                        PathNode.QuadTo(736.4f, 767.3f, 716.0f, 802.3f),
                        PathNode.QuadTo(695.5f, 837.2f, 660.5f, 857.5f),
                        PathNode.QuadTo(625.4f, 877.9f, 584.4f, 877.9f),
                        PathNode.QuadTo(543.4f, 877.9f, 508.3f, 857.5f),
                        PathNode.QuadTo(473.3f, 837.2f, 452.8f, 802.3f),
                        PathNode.QuadTo(432.4f, 767.3f, 432.4f, 725.6f),
                        PathNode.QuadTo(432.4f, 684.9f, 452.9f, 650.4f),
                        PathNode.QuadTo(473.4f, 615.9f, 508.4f, 595.4f),
                        PathNode.QuadTo(543.4f, 574.9f, 584.4f, 574.9f),
                        PathNode.QuadTo(625.4f, 574.9f, 660.4f, 595.4f),
                        PathNode.QuadTo(695.4f, 615.9f, 715.9f, 650.4f),
                        PathNode.QuadTo(736.4f, 684.9f, 736.4f, 725.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactscircleLight!!
    }

private var _contactscircleLight: ImageVector? = null

val MiuixIcons.Normal.ContactsCircle: ImageVector
    get() {
        if (_contactscircleNormal != null) return _contactscircleNormal!!
        _contactscircleNormal = ImageVector.Builder(
            name = "ContactsCircle.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1188.6f,
            viewportHeight = 1188.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1188.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1089.5f, 593.9f),
                        PathNode.QuadTo(1089.5f, 728.9f, 1023.0f, 842.5f),
                        PathNode.QuadTo(956.5f, 956.1f, 842.4f, 1022.6f),
                        PathNode.QuadTo(728.2f, 1089.0f, 594.2f, 1089.0f),
                        PathNode.QuadTo(460.2f, 1089.0f, 346.1f, 1022.5f),
                        PathNode.QuadTo(232.0f, 956.0f, 165.5f, 842.4f),
                        PathNode.QuadTo(99.0f, 728.7f, 99.0f, 593.7f),
                        PathNode.QuadTo(99.0f, 459.7f, 165.6f, 346.1f),
                        PathNode.QuadTo(232.1f, 232.5f, 346.2f, 166.0f),
                        PathNode.QuadTo(460.4f, 99.5f, 594.4f, 99.5f),
                        PathNode.QuadTo(728.4f, 99.5f, 842.5f, 166.1f),
                        PathNode.QuadTo(956.6f, 232.6f, 1023.1f, 346.2f),
                        PathNode.QuadTo(1089.5f, 459.9f, 1089.5f, 593.9f),
                        PathNode.Close,
                        PathNode.MoveTo(862.9f, 190.4f),
                        PathNode.QuadTo(893.9f, 207.4f, 919.5f, 232.4f),
                        PathNode.QuadTo(945.2f, 257.4f, 960.5f, 286.5f),
                        PathNode.QuadTo(963.8f, 293.8f, 960.8f, 298.1f),
                        PathNode.QuadTo(886.4f, 405.2f, 792.2f, 465.3f),
                        PathNode.QuadTo(698.0f, 525.3f, 594.3f, 525.3f),
                        PathNode.QuadTo(490.6f, 525.3f, 396.2f, 465.3f),
                        PathNode.QuadTo(301.9f, 405.2f, 226.9f, 297.1f),
                        PathNode.QuadTo(225.9f, 294.2f, 227.0f, 289.5f),
                        PathNode.QuadTo(227.7f, 288.9f, 229.0f, 286.2f),
                        PathNode.QuadTo(263.2f, 224.1f, 326.0f, 190.4f),
                        PathNode.QuadTo(360.2f, 173.1f, 410.0f, 167.3f),
                        PathNode.QuadTo(459.8f, 161.4f, 556.3f, 161.4f),
                        PathNode.HorizontalTo(632.3f),
                        PathNode.QuadTo(729.4f, 161.4f, 779.0f, 167.3f),
                        PathNode.QuadTo(828.7f, 173.1f, 862.9f, 190.4f),
                        PathNode.Close,
                        PathNode.MoveTo(184.0f, 593.7f),
                        PathNode.QuadTo(184.0f, 705.4f, 239.3f, 799.4f),
                        PathNode.QuadTo(294.6f, 893.5f, 388.7f, 948.8f),
                        PathNode.QuadTo(482.7f, 1004.0f, 594.3f, 1004.0f),
                        PathNode.QuadTo(705.9f, 1004.0f, 799.9f, 948.8f),
                        PathNode.QuadTo(894.0f, 893.5f, 949.3f, 799.4f),
                        PathNode.QuadTo(1004.5f, 705.4f, 1004.5f, 593.7f),
                        PathNode.QuadTo(1004.5f, 482.1f, 949.3f, 388.3f),
                        PathNode.QuadTo(894.0f, 294.4f, 799.9f, 239.5f),
                        PathNode.QuadTo(705.9f, 184.5f, 594.3f, 184.5f),
                        PathNode.QuadTo(482.7f, 184.5f, 388.7f, 239.5f),
                        PathNode.QuadTo(294.6f, 294.4f, 239.3f, 388.3f),
                        PathNode.QuadTo(184.0f, 482.1f, 184.0f, 593.7f),
                        PathNode.Close,
                        PathNode.MoveTo(749.7f, 735.0f),
                        PathNode.QuadTo(749.7f, 777.6f, 728.9f, 813.3f),
                        PathNode.QuadTo(708.1f, 848.9f, 672.4f, 869.7f),
                        PathNode.QuadTo(636.7f, 890.5f, 594.3f, 890.5f),
                        PathNode.QuadTo(551.9f, 890.5f, 516.2f, 869.7f),
                        PathNode.QuadTo(480.5f, 848.9f, 459.7f, 813.3f),
                        PathNode.QuadTo(438.9f, 777.6f, 438.9f, 735.0f),
                        PathNode.QuadTo(438.9f, 693.4f, 459.7f, 657.5f),
                        PathNode.QuadTo(480.5f, 621.7f, 516.2f, 600.8f),
                        PathNode.QuadTo(551.9f, 580.0f, 594.3f, 580.0f),
                        PathNode.QuadTo(636.7f, 580.0f, 672.4f, 600.8f),
                        PathNode.QuadTo(708.0f, 621.7f, 728.9f, 657.5f),
                        PathNode.QuadTo(749.7f, 693.4f, 749.7f, 735.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactscircleNormal!!
    }

private var _contactscircleNormal: ImageVector? = null

val MiuixIcons.Regular.ContactsCircle: ImageVector
    get() {
        if (_contactscircleRegular != null) return _contactscircleRegular!!
        _contactscircleRegular = ImageVector.Builder(
            name = "ContactsCircle.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1197.6f,
            viewportHeight = 1197.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1197.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1097.8f, 598.3f),
                        PathNode.QuadTo(1097.8f, 734.3f, 1030.8f, 848.8f),
                        PathNode.QuadTo(963.8f, 963.3f, 848.8f, 1030.3f),
                        PathNode.QuadTo(733.8f, 1097.3f, 598.8f, 1097.3f),
                        PathNode.QuadTo(463.8f, 1097.3f, 348.8f, 1030.3f),
                        PathNode.QuadTo(233.8f, 963.3f, 166.8f, 848.8f),
                        PathNode.QuadTo(99.8f, 734.3f, 99.8f, 598.3f),
                        PathNode.QuadTo(99.8f, 463.3f, 166.8f, 348.8f),
                        PathNode.QuadTo(233.8f, 234.3f, 348.8f, 167.3f),
                        PathNode.QuadTo(463.8f, 100.3f, 598.8f, 100.3f),
                        PathNode.QuadTo(733.8f, 100.3f, 848.8f, 167.3f),
                        PathNode.QuadTo(963.8f, 234.3f, 1030.8f, 348.8f),
                        PathNode.QuadTo(1097.8f, 463.3f, 1097.8f, 598.3f),
                        PathNode.Close,
                        PathNode.MoveTo(871.8f, 194.3f),
                        PathNode.QuadTo(902.8f, 211.3f, 927.8f, 236.3f),
                        PathNode.QuadTo(952.8f, 261.3f, 968.8f, 292.3f),
                        PathNode.QuadTo(970.8f, 298.3f, 967.8f, 302.3f),
                        PathNode.QuadTo(892.8f, 411.3f, 797.8f, 471.8f),
                        PathNode.QuadTo(702.8f, 532.3f, 598.8f, 532.3f),
                        PathNode.QuadTo(494.8f, 532.3f, 399.8f, 471.8f),
                        PathNode.QuadTo(304.8f, 411.3f, 229.8f, 302.3f),
                        PathNode.QuadTo(228.8f, 299.3f, 227.8f, 295.3f),
                        PathNode.QuadTo(228.8f, 294.3f, 228.8f, 292.3f),
                        PathNode.QuadTo(261.8f, 228.3f, 325.8f, 194.3f),
                        PathNode.QuadTo(358.8f, 177.3f, 405.3f, 173.8f),
                        PathNode.QuadTo(451.8f, 170.3f, 560.8f, 170.3f),
                        PathNode.HorizontalTo(636.8f),
                        PathNode.QuadTo(745.8f, 170.3f, 792.3f, 173.8f),
                        PathNode.QuadTo(838.8f, 177.3f, 871.8f, 194.3f),
                        PathNode.Close,
                        PathNode.MoveTo(194.8f, 598.3f),
                        PathNode.QuadTo(194.8f, 708.3f, 249.3f, 800.8f),
                        PathNode.QuadTo(303.8f, 893.3f, 396.3f, 947.8f),
                        PathNode.QuadTo(488.8f, 1002.3f, 598.8f, 1002.3f),
                        PathNode.QuadTo(708.8f, 1002.3f, 801.3f, 947.8f),
                        PathNode.QuadTo(893.8f, 893.3f, 948.3f, 800.8f),
                        PathNode.QuadTo(1002.8f, 708.3f, 1002.8f, 598.3f),
                        PathNode.QuadTo(1002.8f, 488.3f, 948.3f, 395.8f),
                        PathNode.QuadTo(893.8f, 303.3f, 801.3f, 249.3f),
                        PathNode.QuadTo(708.8f, 195.3f, 598.8f, 195.3f),
                        PathNode.QuadTo(488.8f, 195.3f, 396.3f, 249.3f),
                        PathNode.QuadTo(303.8f, 303.3f, 249.3f, 395.8f),
                        PathNode.QuadTo(194.8f, 488.3f, 194.8f, 598.3f),
                        PathNode.Close,
                        PathNode.MoveTo(755.8f, 739.3f),
                        PathNode.QuadTo(755.8f, 782.3f, 734.8f, 818.3f),
                        PathNode.QuadTo(713.8f, 854.3f, 677.8f, 875.3f),
                        PathNode.QuadTo(641.8f, 896.3f, 598.8f, 896.3f),
                        PathNode.QuadTo(555.8f, 896.3f, 519.8f, 875.3f),
                        PathNode.QuadTo(483.8f, 854.3f, 462.8f, 818.3f),
                        PathNode.QuadTo(441.8f, 782.3f, 441.8f, 739.3f),
                        PathNode.QuadTo(441.8f, 697.3f, 462.8f, 660.8f),
                        PathNode.QuadTo(483.8f, 624.3f, 519.8f, 603.3f),
                        PathNode.QuadTo(555.8f, 582.3f, 598.8f, 582.3f),
                        PathNode.QuadTo(641.8f, 582.3f, 677.8f, 603.3f),
                        PathNode.QuadTo(713.8f, 624.3f, 734.8f, 660.8f),
                        PathNode.QuadTo(755.8f, 697.3f, 755.8f, 739.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactscircleRegular!!
    }

private var _contactscircleRegular: ImageVector? = null

val MiuixIcons.Medium.ContactsCircle: ImageVector
    get() {
        if (_contactscircleMedium != null) return _contactscircleMedium!!
        _contactscircleMedium = ImageVector.Builder(
            name = "ContactsCircle.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1213.1f,
            viewportHeight = 1213.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1213.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1112.0f, 606.1f),
                        PathNode.QuadTo(1112.0f, 743.8f, 1044.2f, 859.8f),
                        PathNode.QuadTo(976.3f, 975.8f, 859.8f, 1043.7f),
                        PathNode.QuadTo(743.3f, 1111.5f, 606.6f, 1111.5f),
                        PathNode.QuadTo(469.8f, 1111.5f, 353.3f, 1043.7f),
                        PathNode.QuadTo(236.8f, 975.8f, 169.0f, 859.8f),
                        PathNode.QuadTo(101.1f, 743.8f, 101.1f, 606.1f),
                        PathNode.QuadTo(101.1f, 469.3f, 169.0f, 353.3f),
                        PathNode.QuadTo(236.8f, 237.3f, 353.3f, 169.5f),
                        PathNode.QuadTo(469.8f, 101.6f, 606.6f, 101.6f),
                        PathNode.QuadTo(743.3f, 101.6f, 859.8f, 169.5f),
                        PathNode.QuadTo(976.3f, 237.3f, 1044.2f, 353.3f),
                        PathNode.QuadTo(1112.0f, 469.3f, 1112.0f, 606.1f),
                        PathNode.Close,
                        PathNode.MoveTo(879.6f, 200.9f),
                        PathNode.QuadTo(911.2f, 217.3f, 936.4f, 242.9f),
                        PathNode.QuadTo(961.7f, 268.5f, 978.3f, 300.1f),
                        PathNode.QuadTo(981.5f, 308.4f, 976.7f, 313.6f),
                        PathNode.QuadTo(901.2f, 422.6f, 806.2f, 483.4f),
                        PathNode.QuadTo(711.2f, 544.2f, 606.6f, 544.2f),
                        PathNode.QuadTo(502.0f, 544.2f, 407.0f, 483.4f),
                        PathNode.QuadTo(312.0f, 422.6f, 236.4f, 313.6f),
                        PathNode.QuadTo(233.0f, 309.4f, 233.8f, 304.2f),
                        PathNode.QuadTo(234.8f, 302.8f, 234.8f, 300.1f),
                        PathNode.QuadTo(269.0f, 234.9f, 333.4f, 200.9f),
                        PathNode.QuadTo(366.6f, 183.9f, 413.1f, 180.1f),
                        PathNode.QuadTo(459.6f, 176.3f, 568.6f, 176.3f),
                        PathNode.HorizontalTo(644.6f),
                        PathNode.QuadTo(753.6f, 176.3f, 800.1f, 180.1f),
                        PathNode.QuadTo(846.6f, 183.9f, 879.6f, 200.9f),
                        PathNode.Close,
                        PathNode.MoveTo(213.7f, 606.1f),
                        PathNode.QuadTo(213.7f, 712.5f, 266.8f, 802.6f),
                        PathNode.QuadTo(319.9f, 892.7f, 410.0f, 945.8f),
                        PathNode.QuadTo(500.1f, 998.9f, 606.6f, 998.9f),
                        PathNode.QuadTo(713.0f, 998.9f, 803.1f, 945.8f),
                        PathNode.QuadTo(893.2f, 892.7f, 946.3f, 802.6f),
                        PathNode.QuadTo(999.4f, 712.5f, 999.4f, 606.1f),
                        PathNode.QuadTo(999.4f, 499.6f, 946.3f, 409.5f),
                        PathNode.QuadTo(893.2f, 319.4f, 803.1f, 266.8f),
                        PathNode.QuadTo(713.0f, 214.2f, 606.6f, 214.2f),
                        PathNode.QuadTo(500.1f, 214.2f, 410.0f, 266.8f),
                        PathNode.QuadTo(319.9f, 319.4f, 266.8f, 409.5f),
                        PathNode.QuadTo(213.7f, 499.6f, 213.7f, 606.1f),
                        PathNode.Close,
                        PathNode.MoveTo(767.1f, 746.2f),
                        PathNode.QuadTo(767.1f, 790.2f, 745.5f, 827.0f),
                        PathNode.QuadTo(723.9f, 863.8f, 687.2f, 885.1f),
                        PathNode.QuadTo(650.4f, 906.4f, 606.6f, 906.4f),
                        PathNode.QuadTo(562.7f, 906.4f, 525.9f, 885.1f),
                        PathNode.QuadTo(489.2f, 863.8f, 467.6f, 827.0f),
                        PathNode.QuadTo(446.0f, 790.2f, 446.0f, 746.2f),
                        PathNode.QuadTo(446.0f, 703.3f, 467.6f, 666.5f),
                        PathNode.QuadTo(489.2f, 629.7f, 525.9f, 608.1f),
                        PathNode.QuadTo(562.7f, 586.5f, 606.6f, 586.5f),
                        PathNode.QuadTo(650.4f, 586.5f, 687.2f, 608.1f),
                        PathNode.QuadTo(723.9f, 629.7f, 745.5f, 666.5f),
                        PathNode.QuadTo(767.1f, 703.3f, 767.1f, 746.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactscircleMedium!!
    }

private var _contactscircleMedium: ImageVector? = null

val MiuixIcons.Demibold.ContactsCircle: ImageVector
    get() {
        if (_contactscircleDemibold != null) return _contactscircleDemibold!!
        _contactscircleDemibold = ImageVector.Builder(
            name = "ContactsCircle.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1224.0f,
            viewportHeight = 1224.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1224.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1122.0f, 611.5f),
                        PathNode.QuadTo(1122.0f, 750.5f, 1053.5f, 867.5f),
                        PathNode.QuadTo(985.0f, 984.5f, 867.5f, 1053.0f),
                        PathNode.QuadTo(750.0f, 1121.5f, 612.0f, 1121.5f),
                        PathNode.QuadTo(474.0f, 1121.5f, 356.5f, 1053.0f),
                        PathNode.QuadTo(238.9f, 984.5f, 170.5f, 867.5f),
                        PathNode.QuadTo(102.0f, 750.5f, 102.0f, 611.5f),
                        PathNode.QuadTo(102.0f, 473.5f, 170.5f, 356.5f),
                        PathNode.QuadTo(239.0f, 239.5f, 356.5f, 171.0f),
                        PathNode.QuadTo(474.0f, 102.5f, 612.0f, 102.5f),
                        PathNode.QuadTo(750.0f, 102.5f, 867.5f, 171.0f),
                        PathNode.QuadTo(985.1f, 239.5f, 1053.5f, 356.5f),
                        PathNode.QuadTo(1122.0f, 473.5f, 1122.0f, 611.5f),
                        PathNode.Close,
                        PathNode.MoveTo(885.0f, 205.5f),
                        PathNode.QuadTo(917.0f, 221.5f, 942.5f, 247.5f),
                        PathNode.QuadTo(968.0f, 273.5f, 985.0f, 305.5f),
                        PathNode.QuadTo(989.0f, 315.5f, 983.0f, 321.5f),
                        PathNode.QuadTo(907.0f, 430.5f, 812.0f, 491.5f),
                        PathNode.QuadTo(717.0f, 552.5f, 612.0f, 552.5f),
                        PathNode.QuadTo(507.0f, 552.5f, 412.0f, 491.5f),
                        PathNode.QuadTo(317.0f, 430.5f, 241.0f, 321.5f),
                        PathNode.QuadTo(236.0f, 316.5f, 238.0f, 310.5f),
                        PathNode.QuadTo(239.0f, 308.8f, 239.0f, 305.5f),
                        PathNode.QuadTo(274.0f, 239.5f, 338.7f, 205.5f),
                        PathNode.QuadTo(372.0f, 188.5f, 418.5f, 184.5f),
                        PathNode.QuadTo(465.0f, 180.5f, 574.0f, 180.5f),
                        PathNode.HorizontalTo(650.0f),
                        PathNode.QuadTo(759.0f, 180.5f, 805.5f, 184.5f),
                        PathNode.QuadTo(852.0f, 188.5f, 885.0f, 205.5f),
                        PathNode.Close,
                        PathNode.MoveTo(227.0f, 611.5f),
                        PathNode.QuadTo(227.0f, 715.5f, 279.1f, 803.9f),
                        PathNode.QuadTo(331.2f, 892.3f, 419.6f, 944.4f),
                        PathNode.QuadTo(508.0f, 996.5f, 612.0f, 996.5f),
                        PathNode.QuadTo(716.0f, 996.5f, 804.4f, 944.4f),
                        PathNode.QuadTo(892.8f, 892.3f, 944.9f, 803.9f),
                        PathNode.QuadTo(997.0f, 715.5f, 997.0f, 611.5f),
                        PathNode.QuadTo(997.0f, 507.5f, 944.9f, 419.1f),
                        PathNode.QuadTo(892.8f, 330.7f, 804.4f, 279.1f),
                        PathNode.QuadTo(716.0f, 227.5f, 612.0f, 227.5f),
                        PathNode.QuadTo(508.0f, 227.5f, 419.6f, 279.1f),
                        PathNode.QuadTo(331.2f, 330.7f, 279.1f, 419.1f),
                        PathNode.QuadTo(227.0f, 507.5f, 227.0f, 611.5f),
                        PathNode.Close,
                        PathNode.MoveTo(775.0f, 751.1f),
                        PathNode.QuadTo(775.0f, 795.7f, 753.0f, 833.1f),
                        PathNode.QuadTo(731.0f, 870.5f, 693.7f, 892.0f),
                        PathNode.QuadTo(656.5f, 913.5f, 612.0f, 913.5f),
                        PathNode.QuadTo(567.5f, 913.5f, 530.3f, 892.0f),
                        PathNode.QuadTo(493.0f, 870.5f, 471.0f, 833.1f),
                        PathNode.QuadTo(449.0f, 795.7f, 449.0f, 751.1f),
                        PathNode.QuadTo(449.0f, 707.5f, 471.0f, 670.5f),
                        PathNode.QuadTo(493.0f, 633.5f, 530.3f, 611.5f),
                        PathNode.QuadTo(567.5f, 589.5f, 612.0f, 589.5f),
                        PathNode.QuadTo(656.5f, 589.5f, 693.7f, 611.5f),
                        PathNode.QuadTo(731.0f, 633.5f, 753.0f, 670.5f),
                        PathNode.QuadTo(775.0f, 707.5f, 775.0f, 751.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactscircleDemibold!!
    }

private var _contactscircleDemibold: ImageVector? = null
