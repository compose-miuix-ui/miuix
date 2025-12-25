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

val MiuixIcons.Messages: ImageVector
    get() = MiuixIcons.Regular.Messages

val MiuixIcons.Light.Messages: ImageVector
    get() {
        if (_messagesLight != null) return _messagesLight!!
        _messagesLight = ImageVector.Builder(
            name = "Messages.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1237.2f,
            viewportHeight = 1237.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -45.89999999999998f, translationY = 997.2f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(298.0f, 133.0f),
                PathNode.QuadTo(230.0f, 190.0f, 189.5f, 266.5f),
                PathNode.QuadTo(149.0f, 343.0f, 149.0f, 430.0f),
                PathNode.QuadTo(149.0f, 554.0f, 223.0f, 650.0f),
                PathNode.QuadTo(297.0f, 746.0f, 416.0f, 799.0f),
                PathNode.QuadTo(535.0f, 852.0f, 665.0f, 852.0f),
                PathNode.QuadTo(804.0f, 852.0f, 922.0f, 797.0f),
                PathNode.QuadTo(1040.0f, 742.0f, 1110.0f, 645.0f),
                PathNode.QuadTo(1180.0f, 548.0f, 1180.0f, 430.0f),
                PathNode.QuadTo(1180.0f, 306.0f, 1124.5f, 223.0f),
                PathNode.QuadTo(1069.0f, 140.0f, 977.0f, 83.0f),
                PathNode.QuadTo(921.0f, 50.0f, 875.5f, 32.5f),
                PathNode.QuadTo(830.0f, 15.0f, 752.0f, -8.0f),
                PathNode.LineTo(727.0f, -15.0f),
                PathNode.QuadTo(704.0f, -22.0f, 546.0f, -67.0f),
                PathNode.LineTo(453.0f, -94.0f),
                PathNode.QuadTo(446.0f, -96.0f, 443.0f, -93.0f),
                PathNode.QuadTo(440.0f, -90.0f, 442.0f, -83.0f),
                PathNode.LineTo(470.0f, 14.0f),
                PathNode.QuadTo(474.0f, 24.0f, 470.0f, 32.0f),
                PathNode.QuadTo(466.0f, 40.0f, 455.0f, 45.0f),
                PathNode.QuadTo(360.0f, 81.0f, 298.0f, 133.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _messagesLight!!
    }

private var _messagesLight: ImageVector? = null

val MiuixIcons.Regular.Messages: ImageVector
    get() {
        if (_messagesRegular != null) return _messagesRegular!!
        _messagesRegular = ImageVector.Builder(
            name = "Messages.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1263.6f,
            viewportHeight = 1263.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -32.700000000000045f, translationY = 1008.1999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(291.0f, 125.0f),
                PathNode.QuadTo(221.0f, 183.0f, 179.5f, 262.0f),
                PathNode.QuadTo(138.0f, 341.0f, 138.0f, 431.0f),
                PathNode.QuadTo(138.0f, 559.0f, 213.5f, 658.0f),
                PathNode.QuadTo(289.0f, 757.0f, 410.5f, 811.0f),
                PathNode.QuadTo(532.0f, 865.0f, 665.0f, 865.0f),
                PathNode.QuadTo(806.0f, 865.0f, 927.0f, 808.5f),
                PathNode.QuadTo(1048.0f, 752.0f, 1119.5f, 652.5f),
                PathNode.QuadTo(1191.0f, 553.0f, 1191.0f, 431.0f),
                PathNode.QuadTo(1191.0f, 304.0f, 1133.5f, 218.0f),
                PathNode.QuadTo(1076.0f, 132.0f, 982.0f, 74.0f),
                PathNode.QuadTo(926.0f, 40.0f, 880.0f, 22.5f),
                PathNode.QuadTo(834.0f, 5.0f, 756.0f, -18.0f),
                PathNode.LineTo(731.0f, -25.0f),
                PathNode.QuadTo(682.0f, -40.0f, 539.0f, -81.0f),
                PathNode.LineTo(431.0f, -112.0f),
                PathNode.QuadTo(426.0f, -113.0f, 422.0f, -109.0f),
                PathNode.QuadTo(418.0f, -105.0f, 420.0f, -100.0f),
                PathNode.LineTo(454.0f, 14.0f),
                PathNode.QuadTo(456.0f, 22.0f, 453.0f, 28.5f),
                PathNode.QuadTo(450.0f, 35.0f, 442.0f, 38.0f),
                PathNode.QuadTo(355.0f, 71.0f, 291.0f, 125.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _messagesRegular!!
    }

private var _messagesRegular: ImageVector? = null

val MiuixIcons.Heavy.Messages: ImageVector
    get() {
        if (_messagesHeavy != null) return _messagesHeavy!!
        _messagesHeavy = ImageVector.Builder(
            name = "Messages.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1290.0f,
            viewportHeight = 1290.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -19.5f, translationY = 1020.9666666666667f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(284.0f, 115.0f),
                PathNode.QuadTo(212.0f, 175.0f, 169.5f, 256.5f),
                PathNode.QuadTo(127.0f, 338.0f, 127.0f, 431.0f),
                PathNode.QuadTo(127.0f, 562.0f, 204.0f, 663.5f),
                PathNode.QuadTo(281.0f, 765.0f, 405.5f, 820.5f),
                PathNode.QuadTo(530.0f, 876.0f, 665.0f, 876.0f),
                PathNode.QuadTo(809.0f, 876.0f, 932.5f, 818.0f),
                PathNode.QuadTo(1056.0f, 760.0f, 1129.0f, 658.0f),
                PathNode.QuadTo(1202.0f, 556.0f, 1202.0f, 431.0f),
                PathNode.QuadTo(1202.0f, 300.0f, 1143.0f, 211.0f),
                PathNode.QuadTo(1084.0f, 122.0f, 988.0f, 63.0f),
                PathNode.QuadTo(930.0f, 29.0f, 882.5f, 10.5f),
                PathNode.QuadTo(835.0f, -8.0f, 760.0f, -29.0f),
                PathNode.LineTo(735.0f, -36.0f),
                PathNode.QuadTo(636.0f, -65.0f, 536.0f, -94.0f),
                PathNode.LineTo(437.0f, -123.0f),
                PathNode.QuadTo(423.0f, -127.0f, 413.0f, -116.0f),
                PathNode.QuadTo(403.0f, -105.0f, 407.0f, -91.0f),
                PathNode.LineTo(438.0f, 12.0f),
                PathNode.QuadTo(440.0f, 17.0f, 437.0f, 22.5f),
                PathNode.QuadTo(434.0f, 28.0f, 429.0f, 30.0f),
                PathNode.QuadTo(346.0f, 62.0f, 284.0f, 115.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _messagesHeavy!!
    }

private var _messagesHeavy: ImageVector? = null
