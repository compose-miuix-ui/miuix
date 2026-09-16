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

val MiuixIcons.Glass.Messages: ImageVector
    get() = MiuixIcons.Glass.Regular.Messages

val MiuixIcons.Glass.Light.Messages: ImageVector
    get() {
        if (_messagesLight != null) return _messagesLight!!
        _messagesLight = ImageVector.Builder(
            name = "Messages.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(377.4f, 207.4f),
                        PathNode.LineTo(348.7f, 110.3f),
                        PathNode.QuadTo(347.3f, 106.0f, 348.3f, 102.7f),
                        PathNode.QuadTo(349.4f, 99.4f, 352.6f, 98.6f),
                        PathNode.QuadTo(355.8f, 97.8f, 360.2f, 98.8f),
                        PathNode.LineTo(457.0f, 126.2f),
                        PathNode.QuadTo(508.3f, 141.1f, 547.3f, 152.2f),
                        PathNode.QuadTo(586.4f, 163.3f, 633.9f, 177.8f),
                        PathNode.QuadTo(647.4f, 181.2f, 660.3f, 185.2f),
                        PathNode.QuadTo(738.3f, 208.2f, 783.4f, 225.3f),
                        PathNode.QuadTo(828.5f, 242.3f, 883.6f, 275.9f),
                        PathNode.QuadTo(975.8f, 333.0f, 1031.3f, 416.1f),
                        PathNode.QuadTo(1086.8f, 499.2f, 1086.8f, 622.6f),
                        PathNode.QuadTo(1086.8f, 741.1f, 1017.1f, 837.7f),
                        PathNode.QuadTo(947.4f, 934.3f, 829.0f, 989.5f),
                        PathNode.QuadTo(710.7f, 1044.6f, 571.9f, 1044.6f),
                        PathNode.QuadTo(441.6f, 1044.6f, 322.5f, 992.0f),
                        PathNode.QuadTo(203.5f, 939.3f, 129.8f, 843.0f),
                        PathNode.QuadTo(56.0f, 746.6f, 56.0f, 622.6f),
                        PathNode.QuadTo(56.0f, 535.8f, 96.2f, 459.2f),
                        PathNode.QuadTo(136.4f, 382.6f, 204.6f, 326.0f),
                        PathNode.QuadTo(236.6f, 299.4f, 275.5f, 277.6f),
                        PathNode.QuadTo(314.5f, 255.9f, 361.8f, 237.6f),
                        PathNode.QuadTo(372.9f, 233.3f, 376.6f, 225.7f),
                        PathNode.QuadTo(380.3f, 218.1f, 377.4f, 207.4f),
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

val MiuixIcons.Glass.Normal.Messages: ImageVector
    get() {
        if (_messagesNormal != null) return _messagesNormal!!
        _messagesNormal = ImageVector.Builder(
            name = "Messages.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(366.1f, 208.6f),
                        PathNode.LineTo(333.7f, 99.9f),
                        PathNode.QuadTo(332.6f, 96.5f, 334.0f, 93.0f),
                        PathNode.QuadTo(335.3f, 89.6f, 338.4f, 88.3f),
                        PathNode.QuadTo(341.5f, 87.0f, 344.9f, 88.0f),
                        PathNode.LineTo(449.4f, 117.9f),
                        PathNode.QuadTo(499.1f, 132.2f, 545.3f, 145.3f),
                        PathNode.QuadTo(591.6f, 158.4f, 636.7f, 172.5f),
                        PathNode.QuadTo(649.8f, 175.6f, 662.1f, 179.6f),
                        PathNode.QuadTo(740.1f, 202.6f, 785.8f, 220.0f),
                        PathNode.QuadTo(831.5f, 237.4f, 887.3f, 271.2f),
                        PathNode.QuadTo(980.7f, 329.0f, 1037.6f, 414.0f),
                        PathNode.QuadTo(1094.5f, 499.1f, 1094.5f, 625.0f),
                        PathNode.QuadTo(1094.5f, 745.9f, 1023.5f, 844.5f),
                        PathNode.QuadTo(952.6f, 943.1f, 832.4f, 999.2f),
                        PathNode.QuadTo(712.2f, 1055.3f, 571.9f, 1055.3f),
                        PathNode.QuadTo(439.8f, 1055.3f, 319.0f, 1001.7f),
                        PathNode.QuadTo(198.3f, 948.1f, 123.3f, 850.0f),
                        PathNode.QuadTo(48.4f, 751.8f, 48.4f, 625.0f),
                        PathNode.QuadTo(48.4f, 536.0f, 89.5f, 457.8f),
                        PathNode.QuadTo(130.6f, 379.5f, 200.0f, 322.0f),
                        PathNode.QuadTo(232.0f, 295.1f, 270.7f, 273.2f),
                        PathNode.QuadTo(309.3f, 251.2f, 353.0f, 234.5f),
                        PathNode.QuadTo(361.9f, 231.1f, 365.1f, 224.3f),
                        PathNode.QuadTo(368.3f, 217.4f, 366.1f, 208.6f),
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

val MiuixIcons.Glass.Regular.Messages: ImageVector
    get() {
        if (_messagesRegular != null) return _messagesRegular!!
        _messagesRegular = ImageVector.Builder(
            name = "Messages.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(360.9f, 209.1f),
                        PathNode.LineTo(326.9f, 95.1f),
                        PathNode.QuadTo(325.9f, 92.1f, 327.4f, 88.6f),
                        PathNode.QuadTo(328.9f, 85.1f, 331.9f, 83.6f),
                        PathNode.QuadTo(334.9f, 82.1f, 337.9f, 83.1f),
                        PathNode.LineTo(445.9f, 114.1f),
                        PathNode.QuadTo(494.9f, 128.1f, 544.4f, 142.1f),
                        PathNode.QuadTo(593.9f, 156.1f, 637.9f, 170.1f),
                        PathNode.QuadTo(650.9f, 173.1f, 662.9f, 177.1f),
                        PathNode.QuadTo(740.9f, 200.1f, 786.9f, 217.6f),
                        PathNode.QuadTo(832.9f, 235.1f, 888.9f, 269.1f),
                        PathNode.QuadTo(982.9f, 327.1f, 1040.4f, 413.1f),
                        PathNode.QuadTo(1097.9f, 499.1f, 1097.9f, 626.1f),
                        PathNode.QuadTo(1097.9f, 748.1f, 1026.4f, 847.6f),
                        PathNode.QuadTo(954.9f, 947.1f, 833.9f, 1003.6f),
                        PathNode.QuadTo(712.9f, 1060.1f, 571.9f, 1060.1f),
                        PathNode.QuadTo(438.9f, 1060.1f, 317.4f, 1006.1f),
                        PathNode.QuadTo(195.9f, 952.1f, 120.4f, 853.1f),
                        PathNode.QuadTo(44.9f, 754.1f, 44.9f, 626.1f),
                        PathNode.QuadTo(44.9f, 536.1f, 86.4f, 457.1f),
                        PathNode.QuadTo(127.9f, 378.1f, 197.9f, 320.1f),
                        PathNode.QuadTo(229.9f, 293.1f, 268.4f, 271.1f),
                        PathNode.QuadTo(306.9f, 249.1f, 348.9f, 233.1f),
                        PathNode.QuadTo(356.9f, 230.1f, 359.9f, 223.6f),
                        PathNode.QuadTo(362.9f, 217.1f, 360.9f, 209.1f),
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

val MiuixIcons.Glass.Medium.Messages: ImageVector
    get() {
        if (_messagesMedium != null) return _messagesMedium!!
        _messagesMedium = ImageVector.Builder(
            name = "Messages.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(351.5f, 208.0f),
                        PathNode.LineTo(319.4f, 100.2f),
                        PathNode.QuadTo(317.3f, 93.7f, 320.1f, 87.2f),
                        PathNode.QuadTo(323.0f, 80.8f, 329.1f, 77.8f),
                        PathNode.QuadTo(335.2f, 74.8f, 341.7f, 76.9f),
                        PathNode.LineTo(449.2f, 107.6f),
                        PathNode.QuadTo(497.6f, 121.6f, 547.0f, 135.5f),
                        PathNode.QuadTo(596.4f, 149.3f, 640.1f, 163.3f),
                        PathNode.QuadTo(653.1f, 166.3f, 665.4f, 170.3f),
                        PathNode.QuadTo(743.1f, 193.1f, 789.4f, 210.8f),
                        PathNode.QuadTo(835.6f, 228.6f, 892.2f, 262.9f),
                        PathNode.QuadTo(987.3f, 321.4f, 1045.8f, 409.0f),
                        PathNode.QuadTo(1104.4f, 496.7f, 1104.4f, 625.8f),
                        PathNode.QuadTo(1104.4f, 750.0f, 1032.0f, 851.1f),
                        PathNode.QuadTo(959.5f, 952.2f, 837.0f, 1009.5f),
                        PathNode.QuadTo(714.6f, 1066.8f, 571.9f, 1066.8f),
                        PathNode.QuadTo(437.6f, 1066.8f, 314.6f, 1012.0f),
                        PathNode.QuadTo(191.6f, 957.2f, 115.0f, 856.6f),
                        PathNode.QuadTo(38.4f, 756.0f, 38.4f, 625.8f),
                        PathNode.QuadTo(38.4f, 534.2f, 80.6f, 453.7f),
                        PathNode.QuadTo(122.8f, 373.2f, 193.9f, 314.4f),
                        PathNode.QuadTo(225.9f, 287.4f, 264.2f, 265.3f),
                        PathNode.QuadTo(302.6f, 243.1f, 341.1f, 228.2f),
                        PathNode.QuadTo(348.0f, 225.8f, 350.6f, 220.2f),
                        PathNode.QuadTo(353.2f, 214.7f, 351.5f, 208.0f),
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

val MiuixIcons.Glass.Demibold.Messages: ImageVector
    get() {
        if (_messagesDemibold != null) return _messagesDemibold!!
        _messagesDemibold = ImageVector.Builder(
            name = "Messages.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(344.8f, 207.2f),
                        PathNode.LineTo(314.1f, 103.8f),
                        PathNode.QuadTo(311.2f, 94.8f, 315.0f, 86.3f),
                        PathNode.QuadTo(318.8f, 77.7f, 327.1f, 73.7f),
                        PathNode.QuadTo(335.4f, 69.6f, 344.4f, 72.5f),
                        PathNode.LineTo(451.4f, 103.0f),
                        PathNode.QuadTo(499.5f, 117.0f, 548.8f, 130.8f),
                        PathNode.QuadTo(598.1f, 144.6f, 641.6f, 158.6f),
                        PathNode.QuadTo(654.6f, 161.6f, 667.1f, 165.6f),
                        PathNode.QuadTo(744.6f, 188.1f, 791.1f, 206.1f),
                        PathNode.QuadTo(837.5f, 224.0f, 894.4f, 258.5f),
                        PathNode.QuadTo(990.3f, 317.4f, 1049.6f, 406.2f),
                        PathNode.QuadTo(1109.0f, 494.9f, 1109.0f, 625.6f),
                        PathNode.QuadTo(1109.0f, 751.3f, 1035.8f, 853.5f),
                        PathNode.QuadTo(962.7f, 955.8f, 839.2f, 1013.7f),
                        PathNode.QuadTo(715.7f, 1071.5f, 571.9f, 1071.5f),
                        PathNode.QuadTo(436.6f, 1071.5f, 312.6f, 1016.2f),
                        PathNode.QuadTo(188.6f, 960.8f, 111.2f, 859.0f),
                        PathNode.QuadTo(33.9f, 757.3f, 33.9f, 625.6f),
                        PathNode.QuadTo(33.9f, 532.8f, 76.5f, 451.3f),
                        PathNode.QuadTo(119.2f, 369.8f, 191.0f, 310.4f),
                        PathNode.QuadTo(223.0f, 283.4f, 261.3f, 261.2f),
                        PathNode.QuadTo(299.6f, 238.9f, 335.6f, 224.8f),
                        PathNode.QuadTo(341.8f, 222.7f, 344.1f, 217.8f),
                        PathNode.QuadTo(346.4f, 212.9f, 344.8f, 207.2f),
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
