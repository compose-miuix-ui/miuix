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

val MiuixIcons.Glass.BankCards: ImageVector
    get() = MiuixIcons.Glass.Regular.BankCards

val MiuixIcons.Glass.Light.BankCards: ImageVector
    get() {
        if (_bankCardsLight != null) return _bankCardsLight!!
        _bankCardsLight = ImageVector.Builder(
            name = "BankCards.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1000.8f, 199.9f),
                        PathNode.QuadTo(1038.7f, 219.2f, 1058.9f, 257.5f),
                        PathNode.QuadTo(1068.0f, 276.3f, 1070.3f, 304.5f),
                        PathNode.QuadTo(1072.5f, 332.7f, 1072.5f, 402.7f),
                        PathNode.LineTo(1072.5f, 710.2f),
                        PathNode.LineTo(70.3f, 710.2f),
                        PathNode.LineTo(70.3f, 402.7f),
                        PathNode.QuadTo(70.3f, 332.7f, 72.6f, 304.5f),
                        PathNode.QuadTo(74.9f, 276.3f, 84.0f, 257.5f),
                        PathNode.QuadTo(104.2f, 219.2f, 142.1f, 199.9f),
                        PathNode.QuadTo(160.9f, 190.8f, 189.0f, 188.5f),
                        PathNode.QuadTo(217.2f, 186.2f, 286.8f, 186.2f),
                        PathNode.LineTo(856.1f, 186.2f),
                        PathNode.QuadTo(925.7f, 186.2f, 953.8f, 188.5f),
                        PathNode.QuadTo(982.0f, 190.8f, 1000.8f, 199.9f),
                        PathNode.Close,
                        PathNode.MoveTo(708.1f, 352.2f),
                        PathNode.LineTo(708.1f, 395.7f),
                        PathNode.QuadTo(708.1f, 421.9f, 717.8f, 431.1f),
                        PathNode.QuadTo(727.5f, 440.3f, 753.2f, 440.3f),
                        PathNode.LineTo(860.1f, 440.3f),
                        PathNode.QuadTo(885.8f, 440.3f, 895.3f, 431.1f),
                        PathNode.QuadTo(904.8f, 421.9f, 904.8f, 395.7f),
                        PathNode.LineTo(904.8f, 352.2f),
                        PathNode.QuadTo(904.8f, 325.5f, 895.3f, 315.8f),
                        PathNode.QuadTo(885.8f, 306.1f, 860.1f, 306.1f),
                        PathNode.LineTo(753.2f, 306.1f),
                        PathNode.QuadTo(727.5f, 306.1f, 717.8f, 315.8f),
                        PathNode.QuadTo(708.1f, 325.5f, 708.1f, 352.2f),
                        PathNode.Close,
                        PathNode.MoveTo(1058.4f, 886.3f),
                        PathNode.QuadTo(1038.7f, 924.2f, 1000.8f, 943.0f),
                        PathNode.QuadTo(982.0f, 952.1f, 953.8f, 954.4f),
                        PathNode.QuadTo(925.7f, 956.7f, 856.1f, 956.7f),
                        PathNode.LineTo(286.8f, 956.7f),
                        PathNode.QuadTo(217.2f, 956.7f, 189.0f, 954.4f),
                        PathNode.QuadTo(160.9f, 952.1f, 142.1f, 943.0f),
                        PathNode.QuadTo(104.2f, 924.2f, 84.4f, 886.3f),
                        PathNode.QuadTo(76.3f, 870.0f, 73.5f, 843.7f),
                        PathNode.QuadTo(70.8f, 817.3f, 70.3f, 759.8f),
                        PathNode.LineTo(1072.5f, 759.8f),
                        PathNode.QuadTo(1072.1f, 817.3f, 1069.3f, 843.7f),
                        PathNode.QuadTo(1066.5f, 870.0f, 1058.4f, 886.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankCardsLight!!
    }

private var _bankCardsLight: ImageVector? = null

val MiuixIcons.Glass.Normal.BankCards: ImageVector
    get() {
        if (_bankCardsNormal != null) return _bankCardsNormal!!
        _bankCardsNormal = ImageVector.Builder(
            name = "BankCards.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1002.6f, 194.1f),
                        PathNode.QuadTo(1042.6f, 215.2f, 1064.1f, 255.4f),
                        PathNode.QuadTo(1073.8f, 275.7f, 1076.2f, 304.8f),
                        PathNode.QuadTo(1078.7f, 333.9f, 1078.7f, 403.9f),
                        PathNode.LineTo(1078.7f, 703.5f),
                        PathNode.LineTo(64.2f, 703.5f),
                        PathNode.LineTo(64.2f, 403.9f),
                        PathNode.QuadTo(64.2f, 333.9f, 66.6f, 304.8f),
                        PathNode.QuadTo(69.1f, 275.7f, 78.8f, 255.4f),
                        PathNode.QuadTo(100.2f, 215.2f, 140.3f, 194.1f),
                        PathNode.QuadTo(160.6f, 184.3f, 189.7f, 181.9f),
                        PathNode.QuadTo(218.7f, 179.5f, 288.6f, 179.5f),
                        PathNode.LineTo(854.3f, 179.5f),
                        PathNode.QuadTo(924.1f, 179.5f, 953.2f, 181.9f),
                        PathNode.QuadTo(982.3f, 184.3f, 1002.6f, 194.1f),
                        PathNode.Close,
                        PathNode.MoveTo(698.0f, 353.7f),
                        PathNode.LineTo(698.0f, 396.9f),
                        PathNode.QuadTo(698.0f, 427.1f, 710.3f, 438.9f),
                        PathNode.QuadTo(722.7f, 450.7f, 752.0f, 450.7f),
                        PathNode.LineTo(858.3f, 450.7f),
                        PathNode.QuadTo(887.6f, 450.7f, 899.8f, 438.9f),
                        PathNode.QuadTo(912.1f, 427.1f, 912.1f, 396.9f),
                        PathNode.LineTo(912.1f, 353.7f),
                        PathNode.QuadTo(912.1f, 323.4f, 899.8f, 311.1f),
                        PathNode.QuadTo(887.6f, 298.8f, 858.3f, 298.8f),
                        PathNode.LineTo(752.0f, 298.8f),
                        PathNode.QuadTo(722.7f, 298.8f, 710.3f, 311.1f),
                        PathNode.QuadTo(698.0f, 323.4f, 698.0f, 353.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1063.9f, 888.5f),
                        PathNode.QuadTo(1042.6f, 928.5f, 1002.6f, 948.8f),
                        PathNode.QuadTo(982.3f, 958.5f, 953.2f, 960.9f),
                        PathNode.QuadTo(924.1f, 963.4f, 854.3f, 963.4f),
                        PathNode.LineTo(288.6f, 963.4f),
                        PathNode.QuadTo(218.7f, 963.4f, 189.7f, 960.9f),
                        PathNode.QuadTo(160.6f, 958.5f, 140.3f, 948.8f),
                        PathNode.QuadTo(100.2f, 928.5f, 78.9f, 888.5f),
                        PathNode.QuadTo(70.2f, 870.3f, 67.3f, 843.5f),
                        PathNode.QuadTo(64.3f, 816.7f, 64.2f, 764.3f),
                        PathNode.LineTo(1078.7f, 764.3f),
                        PathNode.QuadTo(1078.5f, 816.7f, 1075.6f, 843.5f),
                        PathNode.QuadTo(1072.7f, 870.3f, 1063.9f, 888.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankCardsNormal!!
    }

private var _bankCardsNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.BankCards: ImageVector
    get() {
        if (_bankCardsRegular != null) return _bankCardsRegular!!
        _bankCardsRegular = ImageVector.Builder(
            name = "BankCards.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1003.4f, 191.4f),
                        PathNode.QuadTo(1044.4f, 213.4f, 1066.4f, 254.4f),
                        PathNode.QuadTo(1076.4f, 275.4f, 1078.9f, 304.9f),
                        PathNode.QuadTo(1081.4f, 334.4f, 1081.4f, 404.4f),
                        PathNode.LineTo(1081.4f, 700.4f),
                        PathNode.LineTo(61.4f, 700.4f),
                        PathNode.LineTo(61.4f, 404.4f),
                        PathNode.QuadTo(61.4f, 334.4f, 63.9f, 304.9f),
                        PathNode.QuadTo(66.4f, 275.4f, 76.4f, 254.4f),
                        PathNode.QuadTo(98.4f, 213.4f, 139.4f, 191.4f),
                        PathNode.QuadTo(160.4f, 181.4f, 189.9f, 178.9f),
                        PathNode.QuadTo(219.4f, 176.4f, 289.4f, 176.4f),
                        PathNode.LineTo(853.4f, 176.4f),
                        PathNode.QuadTo(923.4f, 176.4f, 952.9f, 178.9f),
                        PathNode.QuadTo(982.4f, 181.4f, 1003.4f, 191.4f),
                        PathNode.Close,
                        PathNode.MoveTo(693.4f, 354.4f),
                        PathNode.LineTo(693.4f, 397.4f),
                        PathNode.QuadTo(693.4f, 429.4f, 706.9f, 442.4f),
                        PathNode.QuadTo(720.4f, 455.4f, 751.4f, 455.4f),
                        PathNode.LineTo(857.4f, 455.4f),
                        PathNode.QuadTo(888.4f, 455.4f, 901.9f, 442.4f),
                        PathNode.QuadTo(915.4f, 429.4f, 915.4f, 397.4f),
                        PathNode.LineTo(915.4f, 354.4f),
                        PathNode.QuadTo(915.4f, 322.4f, 901.9f, 308.9f),
                        PathNode.QuadTo(888.4f, 295.4f, 857.4f, 295.4f),
                        PathNode.LineTo(751.4f, 295.4f),
                        PathNode.QuadTo(720.4f, 295.4f, 706.9f, 308.9f),
                        PathNode.QuadTo(693.4f, 322.4f, 693.4f, 354.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1066.4f, 889.4f),
                        PathNode.QuadTo(1044.4f, 930.4f, 1003.4f, 951.4f),
                        PathNode.QuadTo(982.4f, 961.4f, 952.9f, 963.9f),
                        PathNode.QuadTo(923.4f, 966.4f, 853.4f, 966.4f),
                        PathNode.LineTo(289.4f, 966.4f),
                        PathNode.QuadTo(219.4f, 966.4f, 189.9f, 963.9f),
                        PathNode.QuadTo(160.4f, 961.4f, 139.4f, 951.4f),
                        PathNode.QuadTo(98.4f, 930.4f, 76.4f, 889.4f),
                        PathNode.QuadTo(67.4f, 870.4f, 64.4f, 843.4f),
                        PathNode.QuadTo(61.4f, 816.4f, 61.4f, 766.4f),
                        PathNode.LineTo(1081.4f, 766.4f),
                        PathNode.QuadTo(1081.4f, 816.4f, 1078.4f, 843.4f),
                        PathNode.QuadTo(1075.4f, 870.4f, 1066.4f, 889.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankCardsRegular!!
    }

private var _bankCardsRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.BankCards: ImageVector
    get() {
        if (_bankCardsMedium != null) return _bankCardsMedium!!
        _bankCardsMedium = ImageVector.Builder(
            name = "BankCards.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1004.8f, 186.3f),
                        PathNode.QuadTo(1047.9f, 209.4f, 1071.0f, 252.5f),
                        PathNode.QuadTo(1081.6f, 274.3f, 1084.1f, 304.4f),
                        PathNode.QuadTo(1086.6f, 334.4f, 1086.6f, 405.2f),
                        PathNode.LineTo(1086.6f, 695.6f),
                        PathNode.LineTo(56.3f, 695.6f),
                        PathNode.LineTo(56.3f, 405.2f),
                        PathNode.QuadTo(56.3f, 334.4f, 58.8f, 304.4f),
                        PathNode.QuadTo(61.3f, 274.3f, 71.8f, 252.5f),
                        PathNode.QuadTo(94.9f, 209.4f, 138.1f, 186.3f),
                        PathNode.QuadTo(160.2f, 175.8f, 190.5f, 173.1f),
                        PathNode.QuadTo(220.8f, 170.5f, 291.1f, 170.5f),
                        PathNode.LineTo(851.8f, 170.5f),
                        PathNode.QuadTo(922.1f, 170.5f, 952.4f, 173.1f),
                        PathNode.QuadTo(982.7f, 175.8f, 1004.8f, 186.3f),
                        PathNode.Close,
                        PathNode.MoveTo(684.8f, 355.5f),
                        PathNode.LineTo(684.8f, 398.2f),
                        PathNode.QuadTo(684.8f, 433.5f, 700.6f, 448.8f),
                        PathNode.QuadTo(716.4f, 464.1f, 750.3f, 464.1f),
                        PathNode.LineTo(855.8f, 464.1f),
                        PathNode.QuadTo(890.1f, 464.1f, 905.8f, 448.8f),
                        PathNode.QuadTo(921.6f, 433.5f, 921.6f, 398.2f),
                        PathNode.LineTo(921.6f, 355.5f),
                        PathNode.QuadTo(921.6f, 320.3f, 905.8f, 304.6f),
                        PathNode.QuadTo(890.1f, 288.9f, 855.8f, 288.9f),
                        PathNode.LineTo(750.3f, 288.9f),
                        PathNode.QuadTo(716.4f, 288.9f, 700.6f, 304.6f),
                        PathNode.QuadTo(684.8f, 320.3f, 684.8f, 355.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1070.8f, 891.3f),
                        PathNode.QuadTo(1047.9f, 934.2f, 1004.8f, 956.6f),
                        PathNode.QuadTo(982.7f, 967.1f, 952.4f, 969.7f),
                        PathNode.QuadTo(922.1f, 972.4f, 851.8f, 972.4f),
                        PathNode.LineTo(291.1f, 972.4f),
                        PathNode.QuadTo(220.8f, 972.4f, 190.5f, 969.7f),
                        PathNode.QuadTo(160.2f, 967.1f, 138.1f, 956.6f),
                        PathNode.QuadTo(94.9f, 934.2f, 72.1f, 891.3f),
                        PathNode.QuadTo(62.0f, 870.7f, 59.0f, 843.3f),
                        PathNode.QuadTo(56.0f, 815.9f, 56.3f, 770.2f),
                        PathNode.LineTo(1086.6f, 770.2f),
                        PathNode.QuadTo(1086.8f, 815.9f, 1083.8f, 843.3f),
                        PathNode.QuadTo(1080.8f, 870.7f, 1070.8f, 891.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankCardsMedium!!
    }

private var _bankCardsMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.BankCards: ImageVector
    get() {
        if (_bankCardsDemibold != null) return _bankCardsDemibold!!
        _bankCardsDemibold = ImageVector.Builder(
            name = "BankCards.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1005.7f, 182.7f),
                        PathNode.QuadTo(1050.4f, 206.5f, 1074.2f, 251.2f),
                        PathNode.QuadTo(1085.2f, 273.6f, 1087.7f, 304.0f),
                        PathNode.QuadTo(1090.2f, 334.4f, 1090.2f, 405.8f),
                        PathNode.LineTo(1090.2f, 692.2f),
                        PathNode.LineTo(52.7f, 692.2f),
                        PathNode.LineTo(52.7f, 405.8f),
                        PathNode.QuadTo(52.7f, 334.4f, 55.2f, 304.0f),
                        PathNode.QuadTo(57.7f, 273.6f, 68.6f, 251.2f),
                        PathNode.QuadTo(92.5f, 206.5f, 137.1f, 182.7f),
                        PathNode.QuadTo(160.0f, 171.8f, 190.8f, 169.0f),
                        PathNode.QuadTo(221.7f, 166.3f, 292.2f, 166.3f),
                        PathNode.LineTo(850.7f, 166.3f),
                        PathNode.QuadTo(921.1f, 166.3f, 952.0f, 169.0f),
                        PathNode.QuadTo(982.9f, 171.8f, 1005.7f, 182.7f),
                        PathNode.Close,
                        PathNode.MoveTo(678.7f, 356.3f),
                        PathNode.LineTo(678.7f, 398.8f),
                        PathNode.QuadTo(678.7f, 436.3f, 696.1f, 453.2f),
                        PathNode.QuadTo(713.5f, 470.1f, 749.6f, 470.1f),
                        PathNode.LineTo(854.7f, 470.1f),
                        PathNode.QuadTo(891.2f, 470.1f, 908.6f, 453.2f),
                        PathNode.QuadTo(926.0f, 436.3f, 926.0f, 398.8f),
                        PathNode.LineTo(926.0f, 356.3f),
                        PathNode.QuadTo(926.0f, 318.8f, 908.6f, 301.6f),
                        PathNode.QuadTo(891.2f, 284.4f, 854.7f, 284.4f),
                        PathNode.LineTo(749.6f, 284.4f),
                        PathNode.QuadTo(713.5f, 284.4f, 696.1f, 301.6f),
                        PathNode.QuadTo(678.7f, 318.8f, 678.7f, 356.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1073.8f, 892.6f),
                        PathNode.QuadTo(1050.4f, 936.9f, 1005.7f, 960.2f),
                        PathNode.QuadTo(982.9f, 971.1f, 952.0f, 973.8f),
                        PathNode.QuadTo(921.1f, 976.5f, 850.7f, 976.5f),
                        PathNode.LineTo(292.2f, 976.5f),
                        PathNode.QuadTo(221.7f, 976.5f, 190.8f, 973.8f),
                        PathNode.QuadTo(160.0f, 971.1f, 137.1f, 960.2f),
                        PathNode.QuadTo(92.5f, 936.9f, 69.1f, 892.6f),
                        PathNode.QuadTo(58.2f, 870.9f, 55.2f, 843.2f),
                        PathNode.QuadTo(52.2f, 815.5f, 52.7f, 772.9f),
                        PathNode.LineTo(1090.2f, 772.9f),
                        PathNode.QuadTo(1090.6f, 815.5f, 1087.6f, 843.2f),
                        PathNode.QuadTo(1084.6f, 870.9f, 1073.8f, 892.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _bankCardsDemibold!!
    }

private var _bankCardsDemibold: ImageVector? = null
