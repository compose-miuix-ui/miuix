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

val MiuixIcons.Search: ImageVector
    get() = MiuixIcons.Regular.Search

val MiuixIcons.Light.Search: ImageVector
    get() {
        if (_searchLight != null) return _searchLight!!
        _searchLight = ImageVector.Builder(
            name = "Search.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1187.616f,
            viewportHeight = 1187.616f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -68.19200000000001f, translationY = 968.968f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(800.0f, 160.0f),
                        PathNode.QuadTo(812.0f, 168.0f, 818.5f, 167.5f),
                        PathNode.QuadTo(825.0f, 167.0f, 834.0f, 158.0f),
                        PathNode.LineTo(1103.0f, -112.0f),
                        PathNode.QuadTo(1110.21875f, -119.33333333333333f, 1117.109375f, -119.66666666666666f),
                        PathNode.QuadTo(1124.0f, -120.0f, 1132.0f, -112.0f),
                        PathNode.LineTo(1146.0f, -98.0f),
                        PathNode.QuadTo(1153.0f, -90.25806451612902f, 1153.0f, -84.12903225806451f),
                        PathNode.QuadTo(1153.0f, -78.0f, 1146.0f, -71.0f),
                        PathNode.LineTo(874.0f, 202.0f),
                        PathNode.QuadTo(866.0f, 210.44444444444446f, 866.5f, 215.72222222222223f),
                        PathNode.QuadTo(867.0f, 221.0f, 875.0f, 233.0f),
                        PathNode.QuadTo(919.0f, 292.0f, 938.5f, 351.5f),
                        PathNode.QuadTo(958.0f, 411.0f, 958.0f, 476.0f),
                        PathNode.QuadTo(958.0f, 582.8308457711443f, 905.2089552238806f, 673.4900497512438f),
                        PathNode.QuadTo(852.4179104477612f, 764.1492537313433f, 761.9888059701493f, 817.0746268656717f),
                        PathNode.QuadTo(671.5597014925373f, 870.0f, 565.0f, 870.0f),
                        PathNode.QuadTo(458.16915422885575f, 870.0f, 367.50995024875624f, 817.0746268656717f),
                        PathNode.QuadTo(276.85074626865674f, 764.1492537313433f, 223.92537313432837f, 673.4900497512438f),
                        PathNode.QuadTo(171.0f, 582.8308457711443f, 171.0f, 476.0f),
                        PathNode.QuadTo(171.0f, 369.44029850746267f, 223.92537313432837f, 279.01119402985074f),
                        PathNode.QuadTo(276.85074626865674f, 188.5820895522388f, 367.50995024875624f, 135.7910447761194f),
                        PathNode.QuadTo(458.16915422885575f, 83.0f, 565.0f, 83.0f),
                        PathNode.QuadTo(627.0f, 83.0f, 684.0f, 101.5f),
                        PathNode.QuadTo(741.0f, 120.0f, 800.0f, 160.0f),
                        PathNode.Close,
                        PathNode.MoveTo(230.0f, 476.0f),
                        PathNode.QuadTo(230.0f, 566.8832807570977f, 274.9255583126551f, 644.0283911671925f),
                        PathNode.QuadTo(319.85111662531017f, 721.1735015772871f, 397.01736972704714f, 766.0867507886435f),
                        PathNode.QuadTo(474.1836228287841f, 811.0f, 565.091811414392f, 811.0f),
                        PathNode.QuadTo(656.0f, 811.0f, 732.5978260869565f, 766.0867507886435f),
                        PathNode.QuadTo(809.195652173913f, 721.1735015772871f, 854.0978260869565f, 644.0283911671925f),
                        PathNode.QuadTo(899.0f, 566.8832807570977f, 899.0f, 476.0f),
                        PathNode.QuadTo(899.0f, 385.38801261829656f, 854.068407960199f, 308.47318611987384f),
                        PathNode.QuadTo(809.136815920398f, 231.5583596214511f, 732.4888059701493f, 186.77917981072557f),
                        PathNode.QuadTo(655.8407960199005f, 142.0f, 564.9203980099503f, 142.0f),
                        PathNode.QuadTo(474.0f, 142.0f, 396.8917748917749f, 186.77917981072557f),
                        PathNode.QuadTo(319.7835497835498f, 231.5583596214511f, 274.8917748917749f, 308.47318611987384f),
                        PathNode.QuadTo(230.0f, 385.38801261829656f, 230.0f, 476.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchLight!!
    }

private var _searchLight: ImageVector? = null

val MiuixIcons.Regular.Search: ImageVector
    get() {
        if (_searchRegular != null) return _searchRegular!!
        _searchRegular = ImageVector.Builder(
            name = "Search.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1204.224f,
            viewportHeight = 1204.224f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -58.388000000000034f, translationY = 977.352f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(785.0f, 139.0f),
                        PathNode.QuadTo(805.0f, 151.0f, 813.0f, 150.5f),
                        PathNode.QuadTo(821.0f, 150.0f, 835.0f, 136.0f),
                        PathNode.LineTo(1086.0f, -115.0f),
                        PathNode.QuadTo(1097.0f, -126.0f, 1107.5f, -126.5f),
                        PathNode.QuadTo(1118.0f, -127.0f, 1130.0f, -115.0f),
                        PathNode.LineTo(1147.0f, -98.0f),
                        PathNode.QuadTo(1159.0f, -86.0f, 1159.0f, -76.5f),
                        PathNode.QuadTo(1159.0f, -67.0f, 1147.0f, -55.0f),
                        PathNode.LineTo(893.0f, 200.0f),
                        PathNode.QuadTo(881.0f, 212.0f, 881.5f, 219.5f),
                        PathNode.QuadTo(882.0f, 227.0f, 895.0f, 246.0f),
                        PathNode.QuadTo(929.0f, 295.0f, 947.5f, 353.5f),
                        PathNode.QuadTo(966.0f, 412.0f, 966.0f, 475.0f),
                        PathNode.QuadTo(966.0f, 584.0f, 912.0f, 676.5f),
                        PathNode.QuadTo(858.0f, 769.0f, 765.5f, 823.0f),
                        PathNode.QuadTo(673.0f, 877.0f, 564.0f, 877.0f),
                        PathNode.QuadTo(455.0f, 877.0f, 362.5f, 823.0f),
                        PathNode.QuadTo(270.0f, 769.0f, 216.0f, 676.5f),
                        PathNode.QuadTo(162.0f, 584.0f, 162.0f, 475.0f),
                        PathNode.QuadTo(162.0f, 366.0f, 216.0f, 273.5f),
                        PathNode.QuadTo(270.0f, 181.0f, 362.5f, 127.0f),
                        PathNode.QuadTo(455.0f, 73.0f, 564.0f, 73.0f),
                        PathNode.QuadTo(624.0f, 73.0f, 680.5f, 90.0f),
                        PathNode.QuadTo(737.0f, 107.0f, 785.0f, 139.0f),
                        PathNode.Close,
                        PathNode.MoveTo(247.0f, 475.0f),
                        PathNode.QuadTo(247.0f, 561.0f, 289.5f, 634.0f),
                        PathNode.QuadTo(332.0f, 707.0f, 405.0f, 749.5f),
                        PathNode.QuadTo(478.0f, 792.0f, 564.0f, 792.0f),
                        PathNode.QuadTo(650.0f, 792.0f, 722.5f, 749.5f),
                        PathNode.QuadTo(795.0f, 707.0f, 837.5f, 634.0f),
                        PathNode.QuadTo(880.0f, 561.0f, 880.0f, 475.0f),
                        PathNode.QuadTo(880.0f, 389.0f, 837.5f, 316.0f),
                        PathNode.QuadTo(795.0f, 243.0f, 722.5f, 200.5f),
                        PathNode.QuadTo(650.0f, 158.0f, 564.0f, 158.0f),
                        PathNode.QuadTo(478.0f, 158.0f, 405.0f, 200.5f),
                        PathNode.QuadTo(332.0f, 243.0f, 289.5f, 316.0f),
                        PathNode.QuadTo(247.0f, 389.0f, 247.0f, 475.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchRegular!!
    }

private var _searchRegular: ImageVector? = null

val MiuixIcons.Heavy.Search: ImageVector
    get() {
        if (_searchHeavy != null) return _searchHeavy!!
        _searchHeavy = ImageVector.Builder(
            name = "Search.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1242.6171428571429f,
            viewportHeight = 1242.6171428571429f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -39.44818532818533f, translationY = 996.5514285714286f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(794.0f, 125.0f),
                        PathNode.QuadTo(806.0f, 133.0f, 811.0f, 133.0f),
                        PathNode.QuadTo(816.0f, 133.0f, 824.0f, 125.0f),
                        PathNode.LineTo(1075.0f, -126.0f),
                        PathNode.QuadTo(1091.0f, -142.0f, 1107.5f, -142.5f),
                        PathNode.QuadTo(1124.0f, -143.0f, 1141.0f, -126.0f),
                        PathNode.LineTo(1158.0f, -109.0f),
                        PathNode.QuadTo(1175.0f, -93.0f, 1175.5f, -77.0f),
                        PathNode.QuadTo(1176.0f, -61.0f, 1158.0f, -44.0f),
                        PathNode.LineTo(904.0f, 211.0f),
                        PathNode.QuadTo(898.0f, 217.0f, 899.0f, 221.5f),
                        PathNode.QuadTo(900.0f, 226.0f, 908.0f, 237.0f),
                        PathNode.QuadTo(944.0f, 288.0f, 963.0f, 348.92906574394465f),
                        PathNode.QuadTo(982.0f, 409.8581314878893f, 982.0f, 475.47404844290656f),
                        PathNode.QuadTo(982.0f, 589.0f, 925.9726962457338f, 684.9726962457338f),
                        PathNode.QuadTo(869.9453924914676f, 780.9453924914676f, 773.9726962457338f, 836.9726962457338f),
                        PathNode.QuadTo(678.0f, 893.0f, 564.0f, 893.0f),
                        PathNode.QuadTo(450.0f, 893.0f, 354.0273037542662f, 836.9726962457338f),
                        PathNode.QuadTo(258.0546075085324f, 780.9453924914676f, 202.0273037542662f, 684.9726962457338f),
                        PathNode.QuadTo(146.0f, 589.0f, 146.0f, 475.0f),
                        PathNode.QuadTo(146.0f, 361.0f, 202.1038961038961f, 265.0273037542662f),
                        PathNode.QuadTo(258.2077922077922f, 169.05460750853243f, 354.3116883116883f, 113.02730375426621f),
                        PathNode.QuadTo(450.4155844155844f, 57.0f, 563.6623376623377f, 57.0f),
                        PathNode.QuadTo(626.0f, 57.0f, 684.9565217391305f, 74.51515151515152f),
                        PathNode.QuadTo(743.9130434782609f, 92.03030303030303f, 794.0f, 125.0f),
                        PathNode.Close,
                        PathNode.MoveTo(263.0f, 475.0f),
                        PathNode.QuadTo(263.0f, 556.6593059936909f, 303.3548895899054f, 625.97476340694f),
                        PathNode.QuadTo(343.7097791798107f, 695.2902208201892f, 413.02523659305996f, 735.6451104100946f),
                        PathNode.QuadTo(482.34069400630915f, 776.0f, 564.0f, 776.0f),
                        PathNode.QuadTo(645.6455696202531f, 776.0f, 714.4746835443038f, 735.6451104100946f),
                        PathNode.QuadTo(783.3037974683544f, 695.2902208201892f, 823.6518987341772f, 625.97476340694f),
                        PathNode.QuadTo(864.0f, 556.6593059936909f, 864.0f, 475.0f),
                        PathNode.QuadTo(864.0f, 393.34069400630915f, 823.6518987341772f, 324.02523659305996f),
                        PathNode.QuadTo(783.3037974683544f, 254.70977917981074f, 714.4746835443038f, 214.35488958990538f),
                        PathNode.QuadTo(645.6455696202531f, 174.0f, 564.0f, 174.0f),
                        PathNode.QuadTo(482.34069400630915f, 174.0f, 413.02523659305996f, 214.35488958990538f),
                        PathNode.QuadTo(343.7097791798107f, 254.70977917981074f, 303.3548895899054f, 324.02523659305996f),
                        PathNode.QuadTo(263.0f, 393.34069400630915f, 263.0f, 475.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _searchHeavy!!
    }

private var _searchHeavy: ImageVector? = null
