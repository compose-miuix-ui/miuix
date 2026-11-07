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

val MiuixIcons.Glass.Close: ImageVector
    get() = MiuixIcons.Glass.Regular.Close

val MiuixIcons.Glass.Light.Close: ImageVector
    get() {
        if (_closeLight != null) return _closeLight!!
        _closeLight = ImageVector.Builder(
            name = "Close.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(186.4f, 973.0f),
                        PathNode.LineTo(170.6f, 957.2f),
                        PathNode.QuadTo(163.8f, 950.3f, 163.8f, 943.4f),
                        PathNode.QuadTo(163.8f, 936.5f, 170.2f, 929.5f),
                        PathNode.LineTo(527.8f, 570.9f),
                        PathNode.LineTo(169.6f, 214.8f),
                        PathNode.QuadTo(162.8f, 207.9f, 162.8f, 200.7f),
                        PathNode.QuadTo(162.8f, 193.6f, 169.6f, 186.1f),
                        PathNode.LineTo(185.0f, 170.8f),
                        PathNode.QuadTo(192.3f, 163.5f, 199.5f, 163.5f),
                        PathNode.QuadTo(206.8f, 163.5f, 214.1f, 170.8f),
                        PathNode.LineTo(571.8f, 526.9f),
                        PathNode.LineTo(929.3f, 169.8f),
                        PathNode.QuadTo(943.2f, 155.9f, 957.1f, 169.8f),
                        PathNode.LineTo(972.9f, 185.6f),
                        PathNode.QuadTo(980.3f, 192.5f, 980.1f, 199.9f),
                        PathNode.QuadTo(979.9f, 207.3f, 972.9f, 213.8f),
                        PathNode.LineTo(615.8f, 570.9f),
                        PathNode.LineTo(972.3f, 929.5f),
                        PathNode.QuadTo(978.8f, 936.5f, 978.8f, 943.4f),
                        PathNode.QuadTo(978.8f, 950.3f, 971.9f, 957.2f),
                        PathNode.LineTo(956.1f, 973.0f),
                        PathNode.QuadTo(949.6f, 980.0f, 942.4f, 980.0f),
                        PathNode.QuadTo(935.2f, 980.0f, 928.8f, 973.0f),
                        PathNode.LineTo(571.8f, 614.9f),
                        PathNode.LineTo(213.8f, 973.0f),
                        PathNode.QuadTo(207.3f, 980.0f, 200.4f, 980.0f),
                        PathNode.QuadTo(193.4f, 980.0f, 186.4f, 973.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeLight!!
    }

private var _closeLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Close: ImageVector
    get() {
        if (_closeNormal != null) return _closeNormal!!
        _closeNormal = ImageVector.Builder(
            name = "Close.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(184.4f, 979.0f),
                        PathNode.LineTo(164.3f, 958.9f),
                        PathNode.QuadTo(155.3f, 949.9f, 155.3f, 940.5f),
                        PathNode.QuadTo(155.3f, 931.1f, 164.2f, 921.4f),
                        PathNode.LineTo(513.8f, 570.7f),
                        PathNode.LineTo(163.3f, 222.3f),
                        PathNode.QuadTo(154.3f, 213.2f, 154.3f, 203.8f),
                        PathNode.QuadTo(154.3f, 194.3f, 163.3f, 184.4f),
                        PathNode.LineTo(183.2f, 164.5f),
                        PathNode.QuadTo(192.4f, 155.4f, 202.2f, 155.4f),
                        PathNode.QuadTo(212.0f, 155.4f, 221.2f, 164.5f),
                        PathNode.LineTo(571.5f, 513.0f),
                        PathNode.LineTo(921.1f, 163.5f),
                        PathNode.QuadTo(939.9f, 144.7f, 958.7f, 163.5f),
                        PathNode.LineTo(978.8f, 183.6f),
                        PathNode.QuadTo(988.6f, 192.6f, 988.6f, 202.5f),
                        PathNode.QuadTo(988.5f, 212.4f, 978.8f, 221.3f),
                        PathNode.LineTo(629.3f, 570.7f),
                        PathNode.LineTo(977.9f, 921.4f),
                        PathNode.QuadTo(986.8f, 931.1f, 986.8f, 940.5f),
                        PathNode.QuadTo(986.8f, 949.9f, 977.8f, 958.9f),
                        PathNode.LineTo(957.7f, 979.0f),
                        PathNode.QuadTo(948.8f, 988.7f, 939.0f, 988.7f),
                        PathNode.QuadTo(929.2f, 988.7f, 920.3f, 979.0f),
                        PathNode.LineTo(571.5f, 628.5f),
                        PathNode.LineTo(221.8f, 979.0f),
                        PathNode.QuadTo(212.9f, 988.7f, 203.5f, 988.7f),
                        PathNode.QuadTo(194.1f, 988.7f, 184.4f, 979.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeNormal!!
    }

private var _closeNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Close: ImageVector
    get() {
        if (_closeRegular != null) return _closeRegular!!
        _closeRegular = ImageVector.Builder(
            name = "Close.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(183.4f, 981.7f),
                        PathNode.LineTo(161.4f, 959.7f),
                        PathNode.QuadTo(151.4f, 949.7f, 151.4f, 939.2f),
                        PathNode.QuadTo(151.4f, 928.7f, 161.4f, 917.7f),
                        PathNode.LineTo(507.4f, 570.7f),
                        PathNode.LineTo(160.4f, 225.7f),
                        PathNode.QuadTo(150.4f, 215.7f, 150.4f, 205.2f),
                        PathNode.QuadTo(150.4f, 194.7f, 160.4f, 183.7f),
                        PathNode.LineTo(182.4f, 161.7f),
                        PathNode.QuadTo(192.4f, 151.7f, 203.4f, 151.7f),
                        PathNode.QuadTo(214.4f, 151.7f, 224.4f, 161.7f),
                        PathNode.LineTo(571.4f, 506.7f),
                        PathNode.LineTo(917.4f, 160.7f),
                        PathNode.QuadTo(938.4f, 139.7f, 959.4f, 160.7f),
                        PathNode.LineTo(981.4f, 182.7f),
                        PathNode.QuadTo(992.4f, 192.7f, 992.4f, 203.7f),
                        PathNode.QuadTo(992.4f, 214.7f, 981.4f, 224.7f),
                        PathNode.LineTo(635.4f, 570.7f),
                        PathNode.LineTo(980.4f, 917.7f),
                        PathNode.QuadTo(990.4f, 928.7f, 990.4f, 939.2f),
                        PathNode.QuadTo(990.4f, 949.7f, 980.4f, 959.7f),
                        PathNode.LineTo(958.4f, 981.7f),
                        PathNode.QuadTo(948.4f, 992.7f, 937.4f, 992.7f),
                        PathNode.QuadTo(926.4f, 992.7f, 916.4f, 981.7f),
                        PathNode.LineTo(571.4f, 634.7f),
                        PathNode.LineTo(225.4f, 981.7f),
                        PathNode.QuadTo(215.4f, 992.7f, 204.9f, 992.7f),
                        PathNode.QuadTo(194.4f, 992.7f, 183.4f, 981.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeRegular!!
    }

private var _closeRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Close: ImageVector
    get() {
        if (_closeMedium != null) return _closeMedium!!
        _closeMedium = ImageVector.Builder(
            name = "Close.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(171.3f, 988.4f),
                        PathNode.LineTo(154.4f, 971.5f),
                        PathNode.QuadTo(138.7f, 955.9f, 138.7f, 941.6f),
                        PathNode.QuadTo(138.7f, 927.3f, 154.4f, 910.9f),
                        PathNode.LineTo(493.9f, 570.6f),
                        PathNode.LineTo(153.7f, 231.9f),
                        PathNode.QuadTo(138.5f, 216.5f, 138.4f, 201.6f),
                        PathNode.QuadTo(138.3f, 186.8f, 153.9f, 171.5f),
                        PathNode.LineTo(170.5f, 154.6f),
                        PathNode.QuadTo(186.5f, 138.7f, 201.5f, 139.2f),
                        PathNode.QuadTo(216.6f, 139.8f, 231.2f, 154.9f),
                        PathNode.LineTo(571.4f, 493.4f),
                        PathNode.LineTo(910.9f, 153.6f),
                        PathNode.QuadTo(941.4f, 123.7f, 970.0f, 152.3f),
                        PathNode.LineTo(989.0f, 171.3f),
                        PathNode.QuadTo(1004.9f, 186.7f, 1004.5f, 201.6f),
                        PathNode.QuadTo(1004.0f, 216.5f, 988.7f, 230.6f),
                        PathNode.LineTo(648.7f, 570.6f),
                        PathNode.LineTo(987.5f, 910.9f),
                        PathNode.QuadTo(1003.1f, 927.3f, 1002.9f, 941.7f),
                        PathNode.QuadTo(1002.6f, 956.1f, 987.5f, 971.5f),
                        PathNode.LineTo(970.3f, 988.7f),
                        PathNode.QuadTo(955.2f, 1004.0f, 940.3f, 1004.3f),
                        PathNode.QuadTo(925.3f, 1004.5f, 909.9f, 988.4f),
                        PathNode.LineTo(571.4f, 648.2f),
                        PathNode.LineTo(231.9f, 988.4f),
                        PathNode.QuadTo(216.5f, 1004.5f, 202.1f, 1004.5f),
                        PathNode.QuadTo(187.7f, 1004.5f, 171.3f, 988.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeMedium!!
    }

private var _closeMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Close: ImageVector
    get() {
        if (_closeDemibold != null) return _closeDemibold!!
        _closeDemibold = ImageVector.Builder(
            name = "Close.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(162.8f, 993.1f),
                        PathNode.LineTo(149.5f, 979.8f),
                        PathNode.QuadTo(129.9f, 960.2f, 129.9f, 943.3f),
                        PathNode.QuadTo(129.9f, 926.3f, 149.5f, 906.1f),
                        PathNode.LineTo(484.5f, 570.6f),
                        PathNode.LineTo(149.0f, 236.2f),
                        PathNode.QuadTo(130.3f, 217.0f, 130.0f, 199.2f),
                        PathNode.QuadTo(129.8f, 181.3f, 149.5f, 163.0f),
                        PathNode.LineTo(162.3f, 149.7f),
                        PathNode.QuadTo(182.4f, 129.6f, 200.3f, 130.5f),
                        PathNode.QuadTo(218.2f, 131.4f, 236.0f, 150.1f),
                        PathNode.LineTo(571.5f, 484.1f),
                        PathNode.LineTo(906.5f, 148.7f),
                        PathNode.QuadTo(943.5f, 112.5f, 977.4f, 146.4f),
                        PathNode.LineTo(994.4f, 163.3f),
                        PathNode.QuadTo(1013.6f, 182.5f, 1012.9f, 200.2f),
                        PathNode.QuadTo(1012.2f, 217.8f, 993.9f, 234.7f),
                        PathNode.LineTo(658.0f, 570.6f),
                        PathNode.LineTo(992.4f, 906.1f),
                        PathNode.QuadTo(1012.1f, 926.3f, 1011.6f, 943.5f),
                        PathNode.QuadTo(1011.2f, 960.7f, 992.4f, 979.8f),
                        PathNode.LineTo(978.7f, 993.6f),
                        PathNode.QuadTo(960.0f, 1011.9f, 942.3f, 1012.4f),
                        PathNode.QuadTo(924.7f, 1012.8f, 905.5f, 993.1f),
                        PathNode.LineTo(571.5f, 657.6f),
                        PathNode.LineTo(236.5f, 993.1f),
                        PathNode.QuadTo(217.3f, 1012.8f, 200.2f, 1012.8f),
                        PathNode.QuadTo(183.0f, 1012.8f, 162.8f, 993.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _closeDemibold!!
    }

private var _closeDemibold: ImageVector? = null
