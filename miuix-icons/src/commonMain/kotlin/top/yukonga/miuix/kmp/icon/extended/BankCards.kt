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
            viewportWidth = 1202.3999999999999f,
            viewportHeight = 1202.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -63.80000000000007f, translationY = 970.1999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1094.0f, -3.0f),
                PathNode.QuadTo(1132.0f, 16.0f, 1152.0f, 55.0f),
                PathNode.QuadTo(1162.0f, 74.0f, 1164.0f, 102.0f),
                PathNode.QuadTo(1166.0f, 130.0f, 1166.0f, 200.0f),
                PathNode.VerticalTo(508.0f),
                PathNode.HorizontalTo(164.0f),
                PathNode.VerticalTo(200.0f),
                PathNode.QuadTo(164.0f, 130.0f, 166.0f, 102.0f),
                PathNode.QuadTo(168.0f, 74.0f, 178.0f, 55.0f),
                PathNode.QuadTo(198.0f, 16.0f, 236.0f, -3.0f),
                PathNode.QuadTo(255.0f, -12.0f, 283.0f, -14.0f),
                PathNode.QuadTo(311.0f, -16.0f, 380.0f, -16.0f),
                PathNode.HorizontalTo(950.0f),
                PathNode.QuadTo(1019.0f, -16.0f, 1047.0f, -14.0f),
                PathNode.QuadTo(1075.0f, -12.0f, 1094.0f, -3.0f),
                PathNode.Close,
                PathNode.MoveTo(802.0f, 150.0f),
                PathNode.VerticalTo(193.0f),
                PathNode.QuadTo(802.0f, 219.0f, 811.5f, 228.5f),
                PathNode.QuadTo(821.0f, 238.0f, 847.0f, 238.0f),
                PathNode.HorizontalTo(954.0f),
                PathNode.QuadTo(980.0f, 238.0f, 989.0f, 228.5f),
                PathNode.QuadTo(998.0f, 219.0f, 998.0f, 193.0f),
                PathNode.VerticalTo(150.0f),
                PathNode.QuadTo(998.0f, 123.0f, 989.0f, 113.5f),
                PathNode.QuadTo(980.0f, 104.0f, 954.0f, 104.0f),
                PathNode.HorizontalTo(847.0f),
                PathNode.QuadTo(821.0f, 104.0f, 811.5f, 113.5f),
                PathNode.QuadTo(802.0f, 123.0f, 802.0f, 150.0f),
                PathNode.Close,
                PathNode.MoveTo(1152.0f, 684.0f),
                PathNode.QuadTo(1131.0f, 723.0f, 1094.0f, 740.0f),
                PathNode.QuadTo(1075.0f, 750.0f, 1047.0f, 752.0f),
                PathNode.QuadTo(1019.0f, 754.0f, 950.0f, 754.0f),
                PathNode.HorizontalTo(380.0f),
                PathNode.QuadTo(311.0f, 754.0f, 283.0f, 752.0f),
                PathNode.QuadTo(255.0f, 750.0f, 236.0f, 740.0f),
                PathNode.QuadTo(199.0f, 723.0f, 178.0f, 684.0f),
                PathNode.QuadTo(170.0f, 667.0f, 167.0f, 640.5f),
                PathNode.QuadTo(164.0f, 614.0f, 164.0f, 557.0f),
                PathNode.HorizontalTo(1166.0f),
                PathNode.QuadTo(1166.0f, 614.0f, 1163.0f, 640.5f),
                PathNode.QuadTo(1160.0f, 667.0f, 1152.0f, 684.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _bankcardsLight!!
    }

private var _bankcardsLight: ImageVector? = null

val MiuixIcons.Regular.BankCards: ImageVector
    get() {
        if (_bankcardsRegular != null) return _bankcardsRegular!!
        _bankcardsRegular = ImageVector.Builder(
            name = "BankCards.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1224.0f,
            viewportHeight = 1224.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -53.0f, translationY = 984.0f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1097.0f, -8.0f),
                PathNode.QuadTo(1138.0f, 14.0f, 1160.0f, 55.0f),
                PathNode.QuadTo(1170.0f, 76.0f, 1172.5f, 105.5f),
                PathNode.QuadTo(1175.0f, 135.0f, 1175.0f, 205.0f),
                PathNode.VerticalTo(501.0f),
                PathNode.HorizontalTo(155.0f),
                PathNode.VerticalTo(205.0f),
                PathNode.QuadTo(155.0f, 135.0f, 157.5f, 105.5f),
                PathNode.QuadTo(160.0f, 76.0f, 170.0f, 55.0f),
                PathNode.QuadTo(192.0f, 14.0f, 233.0f, -8.0f),
                PathNode.QuadTo(254.0f, -18.0f, 283.5f, -20.5f),
                PathNode.QuadTo(313.0f, -23.0f, 383.0f, -23.0f),
                PathNode.HorizontalTo(947.0f),
                PathNode.QuadTo(1017.0f, -23.0f, 1046.5f, -20.5f),
                PathNode.QuadTo(1076.0f, -18.0f, 1097.0f, -8.0f),
                PathNode.Close,
                PathNode.MoveTo(787.0f, 155.0f),
                PathNode.VerticalTo(198.0f),
                PathNode.QuadTo(787.0f, 230.0f, 800.5f, 243.0f),
                PathNode.QuadTo(814.0f, 256.0f, 845.0f, 256.0f),
                PathNode.HorizontalTo(951.0f),
                PathNode.QuadTo(982.0f, 256.0f, 995.5f, 243.0f),
                PathNode.QuadTo(1009.0f, 230.0f, 1009.0f, 198.0f),
                PathNode.VerticalTo(155.0f),
                PathNode.QuadTo(1009.0f, 123.0f, 995.5f, 109.5f),
                PathNode.QuadTo(982.0f, 96.0f, 951.0f, 96.0f),
                PathNode.HorizontalTo(845.0f),
                PathNode.QuadTo(814.0f, 96.0f, 800.5f, 109.5f),
                PathNode.QuadTo(787.0f, 123.0f, 787.0f, 155.0f),
                PathNode.Close,
                PathNode.MoveTo(1160.0f, 690.0f),
                PathNode.QuadTo(1138.0f, 731.0f, 1097.0f, 752.0f),
                PathNode.QuadTo(1076.0f, 762.0f, 1046.5f, 764.5f),
                PathNode.QuadTo(1017.0f, 767.0f, 947.0f, 767.0f),
                PathNode.HorizontalTo(383.0f),
                PathNode.QuadTo(313.0f, 767.0f, 283.5f, 764.5f),
                PathNode.QuadTo(254.0f, 762.0f, 233.0f, 752.0f),
                PathNode.QuadTo(192.0f, 731.0f, 170.0f, 690.0f),
                PathNode.QuadTo(161.0f, 671.0f, 158.0f, 644.0f),
                PathNode.QuadTo(155.0f, 617.0f, 155.0f, 567.0f),
                PathNode.HorizontalTo(1175.0f),
                PathNode.QuadTo(1175.0f, 617.0f, 1172.0f, 644.0f),
                PathNode.QuadTo(1169.0f, 671.0f, 1160.0f, 690.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _bankcardsRegular!!
    }

private var _bankcardsRegular: ImageVector? = null

val MiuixIcons.Heavy.BankCards: ImageVector
    get() {
        if (_bankcardsHeavy != null) return _bankcardsHeavy!!
        _bankcardsHeavy = ImageVector.Builder(
            name = "BankCards.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1245.6f,
            viewportHeight = 1245.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -42.200000000000045f, translationY = 994.8f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1099.0f, -17.0f),
                PathNode.QuadTo(1144.0f, 7.0f, 1168.0f, 52.0f),
                PathNode.QuadTo(1179.0f, 75.0f, 1181.5f, 105.5f),
                PathNode.QuadTo(1184.0f, 136.0f, 1184.0f, 206.0f),
                PathNode.VerticalTo(493.0f),
                PathNode.HorizontalTo(146.0f),
                PathNode.VerticalTo(206.0f),
                PathNode.QuadTo(146.0f, 136.0f, 148.5f, 105.5f),
                PathNode.QuadTo(151.0f, 75.0f, 162.0f, 52.0f),
                PathNode.QuadTo(186.0f, 7.0f, 231.0f, -17.0f),
                PathNode.QuadTo(253.0f, -28.0f, 284.0f, -30.5f),
                PathNode.QuadTo(315.0f, -33.0f, 386.0f, -33.0f),
                PathNode.HorizontalTo(944.0f),
                PathNode.QuadTo(1015.0f, -33.0f, 1046.0f, -30.5f),
                PathNode.QuadTo(1077.0f, -28.0f, 1099.0f, -17.0f),
                PathNode.Close,
                PathNode.MoveTo(772.0f, 157.0f),
                PathNode.VerticalTo(199.0f),
                PathNode.QuadTo(772.0f, 237.0f, 789.5f, 254.0f),
                PathNode.QuadTo(807.0f, 271.0f, 843.0f, 271.0f),
                PathNode.HorizontalTo(948.0f),
                PathNode.QuadTo(985.0f, 271.0f, 1002.5f, 254.0f),
                PathNode.QuadTo(1020.0f, 237.0f, 1020.0f, 199.0f),
                PathNode.VerticalTo(157.0f),
                PathNode.QuadTo(1020.0f, 119.0f, 1002.5f, 102.0f),
                PathNode.QuadTo(985.0f, 85.0f, 948.0f, 85.0f),
                PathNode.HorizontalTo(843.0f),
                PathNode.QuadTo(807.0f, 85.0f, 789.5f, 102.0f),
                PathNode.QuadTo(772.0f, 119.0f, 772.0f, 157.0f),
                PathNode.Close,
                PathNode.MoveTo(1167.0f, 693.0f),
                PathNode.QuadTo(1144.0f, 737.0f, 1099.0f, 761.0f),
                PathNode.QuadTo(1077.0f, 772.0f, 1046.0f, 774.5f),
                PathNode.QuadTo(1015.0f, 777.0f, 944.0f, 777.0f),
                PathNode.HorizontalTo(386.0f),
                PathNode.QuadTo(315.0f, 777.0f, 284.0f, 774.5f),
                PathNode.QuadTo(253.0f, 772.0f, 231.0f, 761.0f),
                PathNode.QuadTo(186.0f, 737.0f, 163.0f, 693.0f),
                PathNode.QuadTo(152.0f, 672.0f, 149.0f, 645.0f),
                PathNode.QuadTo(146.0f, 618.0f, 146.0f, 573.0f),
                PathNode.HorizontalTo(1184.0f),
                PathNode.QuadTo(1184.0f, 618.0f, 1181.0f, 645.0f),
                PathNode.QuadTo(1178.0f, 672.0f, 1167.0f, 693.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _bankcardsHeavy!!
    }

private var _bankcardsHeavy: ImageVector? = null
