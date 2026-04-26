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
            viewportHeight = 1237.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1237.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(252.1f, 373.6f),
                        PathNode.QuadTo(184.1f, 430.6f, 143.6f, 507.1f),
                        PathNode.QuadTo(103.1f, 583.6f, 103.1f, 670.6f),
                        PathNode.QuadTo(103.1f, 794.6f, 177.1f, 890.6f),
                        PathNode.QuadTo(251.1f, 986.6f, 370.1f, 1039.6f),
                        PathNode.QuadTo(489.1f, 1092.6f, 619.1f, 1092.6f),
                        PathNode.QuadTo(758.1f, 1092.6f, 876.1f, 1037.6f),
                        PathNode.QuadTo(994.1f, 982.6f, 1064.1f, 885.6f),
                        PathNode.QuadTo(1134.1f, 788.6f, 1134.1f, 670.6f),
                        PathNode.QuadTo(1134.1f, 546.6f, 1078.6f, 463.6f),
                        PathNode.QuadTo(1023.1f, 380.6f, 931.1f, 323.6f),
                        PathNode.QuadTo(875.1f, 290.6f, 829.6f, 273.1f),
                        PathNode.QuadTo(784.1f, 255.6f, 706.1f, 232.6f),
                        PathNode.LineTo(681.1f, 225.6f),
                        PathNode.QuadTo(658.1f, 218.6f, 500.1f, 173.6f),
                        PathNode.LineTo(407.1f, 146.6f),
                        PathNode.QuadTo(400.1f, 144.6f, 397.1f, 147.6f),
                        PathNode.QuadTo(394.1f, 150.6f, 396.1f, 157.6f),
                        PathNode.LineTo(424.1f, 254.6f),
                        PathNode.QuadTo(428.1f, 264.6f, 424.1f, 272.6f),
                        PathNode.QuadTo(420.1f, 280.6f, 409.1f, 285.6f),
                        PathNode.QuadTo(314.1f, 321.6f, 252.1f, 373.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _messagesLight!!
    }

private var _messagesLight: ImageVector? = null

val MiuixIcons.Normal.Messages: ImageVector
    get() {
        if (_messagesNormal != null) return _messagesNormal!!
        _messagesNormal = ImageVector.Builder(
            name = "Messages.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1255.3f,
            viewportHeight = 1255.3f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1255.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(256.4f, 378.5f),
                        PathNode.QuadTo(187.0f, 436.2f, 145.8f, 514.5f),
                        PathNode.QuadTo(104.6f, 592.7f, 104.6f, 681.7f),
                        PathNode.QuadTo(104.6f, 808.5f, 179.6f, 906.5f),
                        PathNode.QuadTo(254.7f, 1004.6f, 375.4f, 1058.3f),
                        PathNode.QuadTo(496.1f, 1112.0f, 628.2f, 1112.0f),
                        PathNode.QuadTo(768.5f, 1112.0f, 888.6f, 1056.0f),
                        PathNode.QuadTo(1008.7f, 999.9f, 1079.7f, 901.2f),
                        PathNode.QuadTo(1150.7f, 802.5f, 1150.7f, 681.7f),
                        PathNode.QuadTo(1150.7f, 555.7f, 1093.9f, 470.6f),
                        PathNode.QuadTo(1037.0f, 385.5f, 943.6f, 327.9f),
                        PathNode.QuadTo(887.6f, 294.2f, 841.8f, 276.7f),
                        PathNode.QuadTo(795.9f, 259.2f, 717.9f, 236.2f),
                        PathNode.LineTo(692.9f, 229.2f),
                        PathNode.QuadTo(652.0f, 216.7f, 504.4f, 174.4f),
                        PathNode.LineTo(401.0f, 144.7f),
                        PathNode.QuadTo(395.4f, 143.4f, 391.7f, 147.0f),
                        PathNode.QuadTo(388.0f, 150.7f, 390.0f, 156.4f),
                        PathNode.LineTo(422.2f, 265.0f),
                        PathNode.QuadTo(424.8f, 273.7f, 421.5f, 280.6f),
                        PathNode.QuadTo(418.2f, 287.6f, 409.2f, 291.2f),
                        PathNode.QuadTo(319.7f, 325.2f, 256.4f, 378.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _messagesNormal!!
    }

private var _messagesNormal: ImageVector? = null

val MiuixIcons.Regular.Messages: ImageVector
    get() {
        if (_messagesRegular != null) return _messagesRegular!!
        _messagesRegular = ImageVector.Builder(
            name = "Messages.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1263.6f,
            viewportHeight = 1263.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1263.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(258.3f, 380.8f),
                        PathNode.QuadTo(188.3f, 438.8f, 146.8f, 517.8f),
                        PathNode.QuadTo(105.3f, 596.8f, 105.3f, 686.8f),
                        PathNode.QuadTo(105.3f, 814.8f, 180.8f, 913.8f),
                        PathNode.QuadTo(256.3f, 1012.8f, 377.8f, 1066.8f),
                        PathNode.QuadTo(499.3f, 1120.8f, 632.3f, 1120.8f),
                        PathNode.QuadTo(773.3f, 1120.8f, 894.3f, 1064.3f),
                        PathNode.QuadTo(1015.3f, 1007.8f, 1086.8f, 908.3f),
                        PathNode.QuadTo(1158.3f, 808.8f, 1158.3f, 686.8f),
                        PathNode.QuadTo(1158.3f, 559.8f, 1100.8f, 473.8f),
                        PathNode.QuadTo(1043.3f, 387.8f, 949.3f, 329.8f),
                        PathNode.QuadTo(893.3f, 295.8f, 847.3f, 278.3f),
                        PathNode.QuadTo(801.3f, 260.8f, 723.3f, 237.8f),
                        PathNode.LineTo(698.3f, 230.8f),
                        PathNode.QuadTo(649.3f, 215.8f, 506.3f, 174.8f),
                        PathNode.LineTo(398.3f, 143.8f),
                        PathNode.QuadTo(393.3f, 142.8f, 389.3f, 146.8f),
                        PathNode.QuadTo(385.3f, 150.8f, 387.3f, 155.8f),
                        PathNode.LineTo(421.3f, 269.8f),
                        PathNode.QuadTo(423.3f, 277.8f, 420.3f, 284.3f),
                        PathNode.QuadTo(417.3f, 290.8f, 409.3f, 293.8f),
                        PathNode.QuadTo(322.3f, 326.8f, 258.3f, 380.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _messagesRegular!!
    }

private var _messagesRegular: ImageVector? = null

val MiuixIcons.Medium.Messages: ImageVector
    get() {
        if (_messagesMedium != null) return _messagesMedium!!
        _messagesMedium = ImageVector.Builder(
            name = "Messages.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1279.1f,
            viewportHeight = 1279.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1279.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(261.9f, 383.6f),
                        PathNode.QuadTo(190.8f, 442.7f, 148.7f, 523.2f),
                        PathNode.QuadTo(106.6f, 603.7f, 106.6f, 695.4f),
                        PathNode.QuadTo(106.6f, 825.2f, 183.0f, 925.7f),
                        PathNode.QuadTo(259.4f, 1026.2f, 382.6f, 1081.0f),
                        PathNode.QuadTo(505.9f, 1135.9f, 640.1f, 1135.9f),
                        PathNode.QuadTo(782.8f, 1135.9f, 905.3f, 1078.5f),
                        PathNode.QuadTo(1027.8f, 1021.2f, 1100.2f, 920.2f),
                        PathNode.QuadTo(1172.5f, 819.2f, 1172.5f, 695.4f),
                        PathNode.QuadTo(1172.5f, 566.1f, 1114.2f, 478.3f),
                        PathNode.QuadTo(1055.8f, 390.6f, 960.6f, 332.0f),
                        PathNode.QuadTo(903.4f, 298.0f, 856.5f, 279.9f),
                        PathNode.QuadTo(809.7f, 261.8f, 733.4f, 240.0f),
                        PathNode.LineTo(708.4f, 233.0f),
                        PathNode.QuadTo(630.0f, 209.7f, 512.3f, 175.8f),
                        PathNode.LineTo(409.6f, 146.0f),
                        PathNode.QuadTo(399.3f, 143.2f, 391.8f, 151.3f),
                        PathNode.QuadTo(384.2f, 159.4f, 387.4f, 169.7f),
                        PathNode.LineTo(419.7f, 277.3f),
                        PathNode.QuadTo(421.7f, 283.5f, 418.7f, 289.4f),
                        PathNode.QuadTo(415.7f, 295.3f, 409.4f, 297.7f),
                        PathNode.QuadTo(324.8f, 330.2f, 261.9f, 383.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _messagesMedium!!
    }

private var _messagesMedium: ImageVector? = null

val MiuixIcons.Demibold.Messages: ImageVector
    get() {
        if (_messagesDemibold != null) return _messagesDemibold!!
        _messagesDemibold = ImageVector.Builder(
            name = "Messages.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1290.0f,
            viewportHeight = 1290.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1290.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(264.5f, 385.5f),
                        PathNode.QuadTo(192.5f, 445.5f, 150.0f, 527.0f),
                        PathNode.QuadTo(107.5f, 608.5f, 107.5f, 701.5f),
                        PathNode.QuadTo(107.5f, 832.5f, 184.5f, 934.0f),
                        PathNode.QuadTo(261.5f, 1035.5f, 386.0f, 1091.0f),
                        PathNode.QuadTo(510.5f, 1146.5f, 645.5f, 1146.5f),
                        PathNode.QuadTo(789.5f, 1146.5f, 913.0f, 1088.5f),
                        PathNode.QuadTo(1036.5f, 1030.5f, 1109.5f, 928.5f),
                        PathNode.QuadTo(1182.5f, 826.5f, 1182.5f, 701.5f),
                        PathNode.QuadTo(1182.5f, 570.5f, 1123.5f, 481.5f),
                        PathNode.QuadTo(1064.5f, 392.5f, 968.5f, 333.5f),
                        PathNode.QuadTo(910.5f, 299.5f, 863.0f, 281.0f),
                        PathNode.QuadTo(815.5f, 262.5f, 740.5f, 241.5f),
                        PathNode.LineTo(715.5f, 234.5f),
                        PathNode.QuadTo(616.5f, 205.5f, 516.5f, 176.5f),
                        PathNode.LineTo(417.5f, 147.5f),
                        PathNode.QuadTo(403.5f, 143.5f, 393.5f, 154.5f),
                        PathNode.QuadTo(383.5f, 165.5f, 387.5f, 179.5f),
                        PathNode.LineTo(418.5f, 282.5f),
                        PathNode.QuadTo(420.5f, 287.5f, 417.5f, 293.0f),
                        PathNode.QuadTo(414.5f, 298.5f, 409.5f, 300.5f),
                        PathNode.QuadTo(326.5f, 332.5f, 264.5f, 385.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _messagesDemibold!!
    }

private var _messagesDemibold: ImageVector? = null
