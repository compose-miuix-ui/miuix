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

val MiuixIcons.BankCards: ImageVector
    get() = MiuixIcons.Regular.BankCards

val MiuixIcons.Light.BankCards: ImageVector
    get() {
        if (_bankcardsLight != null) return _bankcardsLight!!
        _bankcardsLight = ImageVector.Builder(
            name = "BankCards.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1202.4f,
            viewportHeight = 1202.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1202.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1030.2f, 229.2f),
                        PathNode.QuadTo(1068.2f, 248.2f, 1088.2f, 287.2f),
                        PathNode.QuadTo(1098.2f, 306.2f, 1100.2f, 334.2f),
                        PathNode.QuadTo(1102.2f, 362.2f, 1102.2f, 432.2f),
                        PathNode.VerticalTo(740.2f),
                        PathNode.HorizontalTo(100.2f),
                        PathNode.VerticalTo(432.2f),
                        PathNode.QuadTo(100.2f, 362.2f, 102.2f, 334.2f),
                        PathNode.QuadTo(104.2f, 306.2f, 114.2f, 287.2f),
                        PathNode.QuadTo(134.2f, 248.2f, 172.2f, 229.2f),
                        PathNode.QuadTo(191.2f, 220.2f, 219.2f, 218.2f),
                        PathNode.QuadTo(247.2f, 216.2f, 316.2f, 216.2f),
                        PathNode.HorizontalTo(886.2f),
                        PathNode.QuadTo(955.2f, 216.2f, 983.2f, 218.2f),
                        PathNode.QuadTo(1011.2f, 220.2f, 1030.2f, 229.2f),
                        PathNode.Close,
                        PathNode.MoveTo(738.2f, 382.2f),
                        PathNode.VerticalTo(425.2f),
                        PathNode.QuadTo(738.2f, 451.2f, 747.7f, 460.7f),
                        PathNode.QuadTo(757.2f, 470.2f, 783.2f, 470.2f),
                        PathNode.HorizontalTo(890.2f),
                        PathNode.QuadTo(916.2f, 470.2f, 925.2f, 460.7f),
                        PathNode.QuadTo(934.2f, 451.2f, 934.2f, 425.2f),
                        PathNode.VerticalTo(382.2f),
                        PathNode.QuadTo(934.2f, 355.2f, 925.2f, 345.7f),
                        PathNode.QuadTo(916.2f, 336.2f, 890.2f, 336.2f),
                        PathNode.HorizontalTo(783.2f),
                        PathNode.QuadTo(757.2f, 336.2f, 747.7f, 345.7f),
                        PathNode.QuadTo(738.2f, 355.2f, 738.2f, 382.2f),
                        PathNode.Close,
                        PathNode.MoveTo(1088.2f, 916.2f),
                        PathNode.QuadTo(1067.2f, 955.2f, 1030.2f, 972.2f),
                        PathNode.QuadTo(1011.2f, 982.2f, 983.2f, 984.2f),
                        PathNode.QuadTo(955.2f, 986.2f, 886.2f, 986.2f),
                        PathNode.HorizontalTo(316.2f),
                        PathNode.QuadTo(247.2f, 986.2f, 219.2f, 984.2f),
                        PathNode.QuadTo(191.2f, 982.2f, 172.2f, 972.2f),
                        PathNode.QuadTo(135.2f, 955.2f, 114.2f, 916.2f),
                        PathNode.QuadTo(106.2f, 899.2f, 103.2f, 872.7f),
                        PathNode.QuadTo(100.2f, 846.2f, 100.2f, 789.2f),
                        PathNode.HorizontalTo(1102.2f),
                        PathNode.QuadTo(1102.2f, 846.2f, 1099.2f, 872.7f),
                        PathNode.QuadTo(1096.2f, 899.2f, 1088.2f, 916.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankcardsLight!!
    }

private var _bankcardsLight: ImageVector? = null

val MiuixIcons.Normal.BankCards: ImageVector
    get() {
        if (_bankcardsNormal != null) return _bankcardsNormal!!
        _bankcardsNormal = ImageVector.Builder(
            name = "BankCards.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1217.2f,
            viewportHeight = 1217.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1217.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1039.7f, 231.1f),
                        PathNode.QuadTo(1079.8f, 252.2f, 1101.1f, 292.6f),
                        PathNode.QuadTo(1111.1f, 312.9f, 1113.5f, 342.0f),
                        PathNode.QuadTo(1115.8f, 371.0f, 1115.8f, 441.0f),
                        PathNode.VerticalTo(740.8f),
                        PathNode.HorizontalTo(101.4f),
                        PathNode.VerticalTo(441.0f),
                        PathNode.QuadTo(101.4f, 371.0f, 103.8f, 342.0f),
                        PathNode.QuadTo(106.1f, 312.9f, 116.1f, 292.6f),
                        PathNode.QuadTo(137.5f, 252.2f, 177.6f, 231.1f),
                        PathNode.QuadTo(197.9f, 221.4f, 227.0f, 219.1f),
                        PathNode.QuadTo(256.0f, 216.8f, 325.7f, 216.8f),
                        PathNode.HorizontalTo(891.6f),
                        PathNode.QuadTo(961.2f, 216.8f, 990.3f, 219.1f),
                        PathNode.QuadTo(1019.3f, 221.4f, 1039.7f, 231.1f),
                        PathNode.Close,
                        PathNode.MoveTo(735.3f, 391.0f),
                        PathNode.VerticalTo(434.0f),
                        PathNode.QuadTo(735.3f, 464.1f, 747.6f, 476.0f),
                        PathNode.QuadTo(759.8f, 487.9f, 789.2f, 487.9f),
                        PathNode.HorizontalTo(895.6f),
                        PathNode.QuadTo(925.0f, 487.9f, 937.1f, 476.0f),
                        PathNode.QuadTo(949.2f, 464.1f, 949.2f, 434.0f),
                        PathNode.VerticalTo(391.0f),
                        PathNode.QuadTo(949.2f, 360.6f, 937.1f, 348.3f),
                        PathNode.QuadTo(925.0f, 336.1f, 895.6f, 336.1f),
                        PathNode.HorizontalTo(789.2f),
                        PathNode.QuadTo(759.8f, 336.1f, 747.6f, 348.3f),
                        PathNode.QuadTo(735.3f, 360.6f, 735.3f, 391.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1101.1f, 925.7f),
                        PathNode.QuadTo(1079.4f, 966.1f, 1039.7f, 985.8f),
                        PathNode.QuadTo(1019.3f, 995.8f, 990.3f, 998.2f),
                        PathNode.QuadTo(961.2f, 1000.5f, 891.6f, 1000.5f),
                        PathNode.HorizontalTo(325.7f),
                        PathNode.QuadTo(256.0f, 1000.5f, 227.0f, 998.2f),
                        PathNode.QuadTo(197.9f, 995.8f, 177.6f, 985.8f),
                        PathNode.QuadTo(137.8f, 966.1f, 116.1f, 925.7f),
                        PathNode.QuadTo(107.4f, 907.3f, 104.4f, 880.5f),
                        PathNode.QuadTo(101.4f, 853.6f, 101.4f, 801.4f),
                        PathNode.HorizontalTo(1115.8f),
                        PathNode.QuadTo(1115.8f, 853.6f, 1112.8f, 880.5f),
                        PathNode.QuadTo(1109.8f, 907.3f, 1101.1f, 925.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankcardsNormal!!
    }

private var _bankcardsNormal: ImageVector? = null

val MiuixIcons.Regular.BankCards: ImageVector
    get() {
        if (_bankcardsRegular != null) return _bankcardsRegular!!
        _bankcardsRegular = ImageVector.Builder(
            name = "BankCards.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1224.0f,
            viewportHeight = 1224.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1224.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1044.0f, 232.0f),
                        PathNode.QuadTo(1085.0f, 254.0f, 1107.0f, 295.0f),
                        PathNode.QuadTo(1117.0f, 316.0f, 1119.5f, 345.5f),
                        PathNode.QuadTo(1122.0f, 375.0f, 1122.0f, 445.0f),
                        PathNode.VerticalTo(741.0f),
                        PathNode.HorizontalTo(102.0f),
                        PathNode.VerticalTo(445.0f),
                        PathNode.QuadTo(102.0f, 375.0f, 104.5f, 345.5f),
                        PathNode.QuadTo(107.0f, 316.0f, 117.0f, 295.0f),
                        PathNode.QuadTo(139.0f, 254.0f, 180.0f, 232.0f),
                        PathNode.QuadTo(201.0f, 222.0f, 230.5f, 219.5f),
                        PathNode.QuadTo(260.0f, 217.0f, 330.0f, 217.0f),
                        PathNode.HorizontalTo(894.0f),
                        PathNode.QuadTo(964.0f, 217.0f, 993.5f, 219.5f),
                        PathNode.QuadTo(1023.0f, 222.0f, 1044.0f, 232.0f),
                        PathNode.Close,
                        PathNode.MoveTo(734.0f, 395.0f),
                        PathNode.VerticalTo(438.0f),
                        PathNode.QuadTo(734.0f, 470.0f, 747.5f, 483.0f),
                        PathNode.QuadTo(761.0f, 496.0f, 792.0f, 496.0f),
                        PathNode.HorizontalTo(898.0f),
                        PathNode.QuadTo(929.0f, 496.0f, 942.5f, 483.0f),
                        PathNode.QuadTo(956.0f, 470.0f, 956.0f, 438.0f),
                        PathNode.VerticalTo(395.0f),
                        PathNode.QuadTo(956.0f, 363.0f, 942.5f, 349.5f),
                        PathNode.QuadTo(929.0f, 336.0f, 898.0f, 336.0f),
                        PathNode.HorizontalTo(792.0f),
                        PathNode.QuadTo(761.0f, 336.0f, 747.5f, 349.5f),
                        PathNode.QuadTo(734.0f, 363.0f, 734.0f, 395.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1107.0f, 930.0f),
                        PathNode.QuadTo(1085.0f, 971.0f, 1044.0f, 992.0f),
                        PathNode.QuadTo(1023.0f, 1002.0f, 993.5f, 1004.5f),
                        PathNode.QuadTo(964.0f, 1007.0f, 894.0f, 1007.0f),
                        PathNode.HorizontalTo(330.0f),
                        PathNode.QuadTo(260.0f, 1007.0f, 230.5f, 1004.5f),
                        PathNode.QuadTo(201.0f, 1002.0f, 180.0f, 992.0f),
                        PathNode.QuadTo(139.0f, 971.0f, 117.0f, 930.0f),
                        PathNode.QuadTo(108.0f, 911.0f, 105.0f, 884.0f),
                        PathNode.QuadTo(102.0f, 857.0f, 102.0f, 807.0f),
                        PathNode.HorizontalTo(1122.0f),
                        PathNode.QuadTo(1122.0f, 857.0f, 1119.0f, 884.0f),
                        PathNode.QuadTo(1116.0f, 911.0f, 1107.0f, 930.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankcardsRegular!!
    }

private var _bankcardsRegular: ImageVector? = null

val MiuixIcons.Medium.BankCards: ImageVector
    get() {
        if (_bankcardsMedium != null) return _bankcardsMedium!!
        _bankcardsMedium = ImageVector.Builder(
            name = "BankCards.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1236.7f,
            viewportHeight = 1236.7f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1236.7f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1051.5f, 233.1f),
                        PathNode.QuadTo(1094.9f, 256.2f, 1118.1f, 299.6f),
                        PathNode.QuadTo(1128.6f, 321.8f, 1131.1f, 351.9f),
                        PathNode.QuadTo(1133.6f, 381.9f, 1133.6f, 451.9f),
                        PathNode.VerticalTo(742.6f),
                        PathNode.HorizontalTo(103.1f),
                        PathNode.VerticalTo(451.9f),
                        PathNode.QuadTo(103.1f, 381.9f, 105.6f, 351.9f),
                        PathNode.QuadTo(108.1f, 321.8f, 118.6f, 299.6f),
                        PathNode.QuadTo(141.8f, 256.2f, 185.2f, 233.1f),
                        PathNode.QuadTo(206.8f, 222.5f, 237.1f, 220.0f),
                        PathNode.QuadTo(267.5f, 217.5f, 338.1f, 217.5f),
                        PathNode.HorizontalTo(898.6f),
                        PathNode.QuadTo(969.2f, 217.5f, 999.6f, 220.0f),
                        PathNode.QuadTo(1029.9f, 222.5f, 1051.5f, 233.1f),
                        PathNode.Close,
                        PathNode.MoveTo(731.5f, 402.5f),
                        PathNode.VerticalTo(444.9f),
                        PathNode.QuadTo(731.5f, 480.5f, 747.4f, 495.8f),
                        PathNode.QuadTo(763.2f, 511.2f, 797.2f, 511.2f),
                        PathNode.HorizontalTo(902.6f),
                        PathNode.QuadTo(937.1f, 511.2f, 953.0f, 495.8f),
                        PathNode.QuadTo(968.8f, 480.5f, 968.8f, 444.9f),
                        PathNode.VerticalTo(402.5f),
                        PathNode.QuadTo(968.8f, 367.0f, 953.0f, 351.4f),
                        PathNode.QuadTo(937.1f, 335.9f, 902.6f, 335.9f),
                        PathNode.HorizontalTo(797.2f),
                        PathNode.QuadTo(763.2f, 335.9f, 747.4f, 351.4f),
                        PathNode.QuadTo(731.5f, 367.0f, 731.5f, 402.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1117.5f, 938.1f),
                        PathNode.QuadTo(1094.9f, 980.9f, 1051.5f, 1003.6f),
                        PathNode.QuadTo(1029.9f, 1014.2f, 999.6f, 1016.7f),
                        PathNode.QuadTo(969.2f, 1019.2f, 898.6f, 1019.2f),
                        PathNode.HorizontalTo(338.1f),
                        PathNode.QuadTo(267.5f, 1019.2f, 237.1f, 1016.7f),
                        PathNode.QuadTo(206.8f, 1014.2f, 185.2f, 1003.6f),
                        PathNode.QuadTo(141.8f, 980.9f, 119.2f, 938.1f),
                        PathNode.QuadTo(109.1f, 917.9f, 106.1f, 890.9f),
                        PathNode.QuadTo(103.1f, 863.9f, 103.1f, 816.9f),
                        PathNode.HorizontalTo(1133.6f),
                        PathNode.QuadTo(1133.6f, 863.9f, 1130.6f, 890.9f),
                        PathNode.QuadTo(1127.6f, 917.9f, 1117.5f, 938.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankcardsMedium!!
    }

private var _bankcardsMedium: ImageVector? = null

val MiuixIcons.Demibold.BankCards: ImageVector
    get() {
        if (_bankcardsDemibold != null) return _bankcardsDemibold!!
        _bankcardsDemibold = ImageVector.Builder(
            name = "BankCards.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1245.6f,
            viewportHeight = 1245.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1245.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1056.8f, 233.8f),
                        PathNode.QuadTo(1101.8f, 257.8f, 1125.8f, 302.8f),
                        PathNode.QuadTo(1136.8f, 325.8f, 1139.3f, 356.3f),
                        PathNode.QuadTo(1141.8f, 386.8f, 1141.8f, 456.8f),
                        PathNode.VerticalTo(743.8f),
                        PathNode.HorizontalTo(103.8f),
                        PathNode.VerticalTo(456.8f),
                        PathNode.QuadTo(103.8f, 386.8f, 106.3f, 356.3f),
                        PathNode.QuadTo(108.8f, 325.8f, 119.8f, 302.8f),
                        PathNode.QuadTo(143.8f, 257.8f, 188.8f, 233.8f),
                        PathNode.QuadTo(210.8f, 222.8f, 241.8f, 220.3f),
                        PathNode.QuadTo(272.8f, 217.8f, 343.8f, 217.8f),
                        PathNode.HorizontalTo(901.8f),
                        PathNode.QuadTo(972.8f, 217.8f, 1003.8f, 220.3f),
                        PathNode.QuadTo(1034.8f, 222.8f, 1056.8f, 233.8f),
                        PathNode.Close,
                        PathNode.MoveTo(729.8f, 407.8f),
                        PathNode.VerticalTo(449.8f),
                        PathNode.QuadTo(729.8f, 487.8f, 747.3f, 504.8f),
                        PathNode.QuadTo(764.8f, 521.8f, 800.8f, 521.8f),
                        PathNode.HorizontalTo(905.8f),
                        PathNode.QuadTo(942.8f, 521.8f, 960.3f, 504.8f),
                        PathNode.QuadTo(977.8f, 487.8f, 977.8f, 449.8f),
                        PathNode.VerticalTo(407.8f),
                        PathNode.QuadTo(977.8f, 369.8f, 960.3f, 352.8f),
                        PathNode.QuadTo(942.8f, 335.8f, 905.8f, 335.8f),
                        PathNode.HorizontalTo(800.8f),
                        PathNode.QuadTo(764.8f, 335.8f, 747.3f, 352.8f),
                        PathNode.QuadTo(729.8f, 369.8f, 729.8f, 407.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1124.8f, 943.8f),
                        PathNode.QuadTo(1101.8f, 987.8f, 1056.8f, 1011.8f),
                        PathNode.QuadTo(1034.8f, 1022.8f, 1003.8f, 1025.3f),
                        PathNode.QuadTo(972.8f, 1027.8f, 901.8f, 1027.8f),
                        PathNode.HorizontalTo(343.8f),
                        PathNode.QuadTo(272.8f, 1027.8f, 241.8f, 1025.3f),
                        PathNode.QuadTo(210.8f, 1022.8f, 188.8f, 1011.8f),
                        PathNode.QuadTo(143.8f, 987.8f, 120.8f, 943.8f),
                        PathNode.QuadTo(109.8f, 922.8f, 106.8f, 895.8f),
                        PathNode.QuadTo(103.8f, 868.8f, 103.8f, 823.8f),
                        PathNode.HorizontalTo(1141.8f),
                        PathNode.QuadTo(1141.8f, 868.8f, 1138.8f, 895.8f),
                        PathNode.QuadTo(1135.8f, 922.8f, 1124.8f, 943.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankcardsDemibold!!
    }

private var _bankcardsDemibold: ImageVector? = null
