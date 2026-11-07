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

val MiuixIcons.Glass.Menu: ImageVector
    get() = MiuixIcons.Glass.Regular.Menu

val MiuixIcons.Glass.Light.Menu: ImageVector
    get() {
        if (_menuLight != null) return _menuLight!!
        _menuLight = ImageVector.Builder(
            name = "Menu.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1022.4f, 897.7f),
                        PathNode.LineTo(1022.4f, 919.2f),
                        PathNode.QuadTo(1022.4f, 928.8f, 1016.8f, 933.3f),
                        PathNode.QuadTo(1011.2f, 937.8f, 1002.1f, 937.8f),
                        PathNode.LineTo(140.8f, 937.8f),
                        PathNode.QuadTo(132.2f, 937.8f, 126.3f, 933.0f),
                        PathNode.QuadTo(120.4f, 928.3f, 120.4f, 919.7f),
                        PathNode.LineTo(120.4f, 898.3f),
                        PathNode.QuadTo(120.4f, 888.6f, 126.0f, 883.8f),
                        PathNode.QuadTo(131.7f, 879.0f, 140.8f, 879.0f),
                        PathNode.LineTo(1002.1f, 879.0f),
                        PathNode.QuadTo(1011.2f, 879.0f, 1016.8f, 883.5f),
                        PathNode.QuadTo(1022.4f, 888.0f, 1022.4f, 897.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 223.7f),
                        PathNode.LineTo(1022.4f, 245.2f),
                        PathNode.QuadTo(1022.4f, 254.8f, 1016.8f, 259.3f),
                        PathNode.QuadTo(1011.2f, 263.8f, 1002.1f, 263.8f),
                        PathNode.LineTo(140.8f, 263.8f),
                        PathNode.QuadTo(132.2f, 263.8f, 126.3f, 259.0f),
                        PathNode.QuadTo(120.4f, 254.3f, 120.4f, 245.7f),
                        PathNode.LineTo(120.4f, 224.3f),
                        PathNode.QuadTo(120.4f, 214.6f, 126.0f, 209.8f),
                        PathNode.QuadTo(131.7f, 205.0f, 140.8f, 205.0f),
                        PathNode.LineTo(1002.1f, 205.0f),
                        PathNode.QuadTo(1011.2f, 205.0f, 1016.8f, 209.5f),
                        PathNode.QuadTo(1022.4f, 214.0f, 1022.4f, 223.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 560.3f),
                        PathNode.LineTo(1022.4f, 581.7f),
                        PathNode.QuadTo(1022.4f, 591.4f, 1016.8f, 595.9f),
                        PathNode.QuadTo(1011.2f, 600.4f, 1002.1f, 600.4f),
                        PathNode.LineTo(140.8f, 600.4f),
                        PathNode.QuadTo(132.2f, 600.4f, 126.3f, 595.6f),
                        PathNode.QuadTo(120.4f, 590.8f, 120.4f, 582.3f),
                        PathNode.LineTo(120.4f, 560.8f),
                        PathNode.QuadTo(120.4f, 551.2f, 126.0f, 546.4f),
                        PathNode.QuadTo(131.7f, 541.6f, 140.8f, 541.6f),
                        PathNode.LineTo(1002.1f, 541.6f),
                        PathNode.QuadTo(1011.2f, 541.6f, 1016.8f, 546.1f),
                        PathNode.QuadTo(1022.4f, 550.6f, 1022.4f, 560.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _menuLight!!
    }

private var _menuLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Menu: ImageVector
    get() {
        if (_menuNormal != null) return _menuNormal!!
        _menuNormal = ImageVector.Builder(
            name = "Menu.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1022.4f, 895.1f),
                        PathNode.LineTo(1022.4f, 921.7f),
                        PathNode.QuadTo(1022.4f, 935.1f, 1014.8f, 941.0f),
                        PathNode.QuadTo(1007.2f, 946.8f, 994.8f, 946.8f),
                        PathNode.LineTo(148.1f, 946.8f),
                        PathNode.QuadTo(136.5f, 946.8f, 128.5f, 940.5f),
                        PathNode.QuadTo(120.4f, 934.2f, 120.4f, 922.6f),
                        PathNode.LineTo(120.4f, 896.0f),
                        PathNode.QuadTo(120.4f, 882.6f, 128.0f, 876.3f),
                        PathNode.QuadTo(135.6f, 870.0f, 148.1f, 870.0f),
                        PathNode.LineTo(994.8f, 870.0f),
                        PathNode.QuadTo(1007.2f, 870.0f, 1014.8f, 875.9f),
                        PathNode.QuadTo(1022.4f, 881.8f, 1022.4f, 895.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 221.1f),
                        PathNode.LineTo(1022.4f, 247.7f),
                        PathNode.QuadTo(1022.4f, 261.1f, 1014.8f, 267.0f),
                        PathNode.QuadTo(1007.2f, 272.8f, 994.8f, 272.8f),
                        PathNode.LineTo(148.1f, 272.8f),
                        PathNode.QuadTo(136.5f, 272.8f, 128.5f, 266.5f),
                        PathNode.QuadTo(120.4f, 260.2f, 120.4f, 248.6f),
                        PathNode.LineTo(120.4f, 222.0f),
                        PathNode.QuadTo(120.4f, 208.6f, 128.0f, 202.3f),
                        PathNode.QuadTo(135.6f, 196.0f, 148.1f, 196.0f),
                        PathNode.LineTo(994.8f, 196.0f),
                        PathNode.QuadTo(1007.2f, 196.0f, 1014.8f, 201.9f),
                        PathNode.QuadTo(1022.4f, 207.8f, 1022.4f, 221.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 558.0f),
                        PathNode.LineTo(1022.4f, 584.6f),
                        PathNode.QuadTo(1022.4f, 597.9f, 1014.8f, 603.8f),
                        PathNode.QuadTo(1007.2f, 609.7f, 994.8f, 609.7f),
                        PathNode.LineTo(148.1f, 609.7f),
                        PathNode.QuadTo(136.5f, 609.7f, 128.5f, 603.4f),
                        PathNode.QuadTo(120.4f, 597.1f, 120.4f, 585.5f),
                        PathNode.LineTo(120.4f, 558.8f),
                        PathNode.QuadTo(120.4f, 545.5f, 128.0f, 539.2f),
                        PathNode.QuadTo(135.6f, 532.9f, 148.1f, 532.9f),
                        PathNode.LineTo(994.8f, 532.9f),
                        PathNode.QuadTo(1007.2f, 532.9f, 1014.8f, 538.8f),
                        PathNode.QuadTo(1022.4f, 544.6f, 1022.4f, 558.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _menuNormal!!
    }

private var _menuNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Menu: ImageVector
    get() {
        if (_menuRegular != null) return _menuRegular!!
        _menuRegular = ImageVector.Builder(
            name = "Menu.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1022.4f, 893.9f),
                        PathNode.LineTo(1022.4f, 922.9f),
                        PathNode.QuadTo(1022.4f, 937.9f, 1013.9f, 944.4f),
                        PathNode.QuadTo(1005.4f, 950.9f, 991.4f, 950.9f),
                        PathNode.LineTo(151.4f, 950.9f),
                        PathNode.QuadTo(138.4f, 950.9f, 129.4f, 943.9f),
                        PathNode.QuadTo(120.4f, 936.9f, 120.4f, 923.9f),
                        PathNode.LineTo(120.4f, 894.9f),
                        PathNode.QuadTo(120.4f, 879.9f, 128.9f, 872.9f),
                        PathNode.QuadTo(137.4f, 865.9f, 151.4f, 865.9f),
                        PathNode.LineTo(991.4f, 865.9f),
                        PathNode.QuadTo(1005.4f, 865.9f, 1013.9f, 872.4f),
                        PathNode.QuadTo(1022.4f, 878.9f, 1022.4f, 893.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 219.9f),
                        PathNode.LineTo(1022.4f, 248.9f),
                        PathNode.QuadTo(1022.4f, 263.9f, 1013.9f, 270.4f),
                        PathNode.QuadTo(1005.4f, 276.9f, 991.4f, 276.9f),
                        PathNode.LineTo(151.4f, 276.9f),
                        PathNode.QuadTo(138.4f, 276.9f, 129.4f, 269.9f),
                        PathNode.QuadTo(120.4f, 262.9f, 120.4f, 249.9f),
                        PathNode.LineTo(120.4f, 220.9f),
                        PathNode.QuadTo(120.4f, 205.9f, 128.9f, 198.9f),
                        PathNode.QuadTo(137.4f, 191.9f, 151.4f, 191.9f),
                        PathNode.LineTo(991.4f, 191.9f),
                        PathNode.QuadTo(1005.4f, 191.9f, 1013.9f, 198.4f),
                        PathNode.QuadTo(1022.4f, 204.9f, 1022.4f, 219.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.4f, 556.9f),
                        PathNode.LineTo(1022.4f, 585.9f),
                        PathNode.QuadTo(1022.4f, 600.9f, 1013.9f, 607.4f),
                        PathNode.QuadTo(1005.4f, 613.9f, 991.4f, 613.9f),
                        PathNode.LineTo(151.4f, 613.9f),
                        PathNode.QuadTo(138.4f, 613.9f, 129.4f, 606.9f),
                        PathNode.QuadTo(120.4f, 599.9f, 120.4f, 586.9f),
                        PathNode.LineTo(120.4f, 557.9f),
                        PathNode.QuadTo(120.4f, 542.9f, 128.9f, 535.9f),
                        PathNode.QuadTo(137.4f, 528.9f, 151.4f, 528.9f),
                        PathNode.LineTo(991.4f, 528.9f),
                        PathNode.QuadTo(1005.4f, 528.9f, 1013.9f, 535.4f),
                        PathNode.QuadTo(1022.4f, 541.9f, 1022.4f, 556.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _menuRegular!!
    }

private var _menuRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Menu: ImageVector
    get() {
        if (_menuMedium != null) return _menuMedium!!
        _menuMedium = ImageVector.Builder(
            name = "Menu.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1028.6f, 891.0f),
                        PathNode.LineTo(1028.6f, 923.7f),
                        PathNode.QuadTo(1028.6f, 941.2f, 1018.4f, 949.6f),
                        PathNode.QuadTo(1008.1f, 958.0f, 991.4f, 958.0f),
                        PathNode.LineTo(151.4f, 958.0f),
                        PathNode.QuadTo(135.5f, 958.0f, 124.8f, 948.9f),
                        PathNode.QuadTo(114.2f, 939.9f, 114.2f, 924.7f),
                        PathNode.LineTo(114.2f, 892.0f),
                        PathNode.QuadTo(114.2f, 874.8f, 124.3f, 865.8f),
                        PathNode.QuadTo(134.5f, 856.7f, 151.4f, 856.7f),
                        PathNode.LineTo(991.4f, 856.7f),
                        PathNode.QuadTo(1008.1f, 856.7f, 1018.4f, 865.1f),
                        PathNode.QuadTo(1028.6f, 873.5f, 1028.6f, 891.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1028.6f, 219.1f),
                        PathNode.LineTo(1028.6f, 251.9f),
                        PathNode.QuadTo(1028.6f, 269.3f, 1018.4f, 277.7f),
                        PathNode.QuadTo(1008.1f, 286.1f, 991.4f, 286.1f),
                        PathNode.LineTo(151.4f, 286.1f),
                        PathNode.QuadTo(135.5f, 286.1f, 124.8f, 277.1f),
                        PathNode.QuadTo(114.2f, 268.1f, 114.2f, 252.9f),
                        PathNode.LineTo(114.2f, 220.1f),
                        PathNode.QuadTo(114.2f, 203.0f, 124.3f, 193.9f),
                        PathNode.QuadTo(134.5f, 184.9f, 151.4f, 184.9f),
                        PathNode.LineTo(991.4f, 184.9f),
                        PathNode.QuadTo(1008.1f, 184.9f, 1018.4f, 193.3f),
                        PathNode.QuadTo(1028.6f, 201.7f, 1028.6f, 219.1f),
                        PathNode.Close,
                        PathNode.MoveTo(1028.6f, 555.0f),
                        PathNode.LineTo(1028.6f, 587.8f),
                        PathNode.QuadTo(1028.6f, 605.3f, 1018.4f, 613.6f),
                        PathNode.QuadTo(1008.1f, 622.0f, 991.4f, 622.0f),
                        PathNode.LineTo(151.4f, 622.0f),
                        PathNode.QuadTo(135.5f, 622.0f, 124.8f, 613.0f),
                        PathNode.QuadTo(114.2f, 604.0f, 114.2f, 588.8f),
                        PathNode.LineTo(114.2f, 556.0f),
                        PathNode.QuadTo(114.2f, 538.9f, 124.3f, 529.8f),
                        PathNode.QuadTo(134.5f, 520.8f, 151.4f, 520.8f),
                        PathNode.LineTo(991.4f, 520.8f),
                        PathNode.QuadTo(1008.1f, 520.8f, 1018.4f, 529.2f),
                        PathNode.QuadTo(1028.6f, 537.6f, 1028.6f, 555.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _menuMedium!!
    }

private var _menuMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Menu: ImageVector
    get() {
        if (_menuDemibold != null) return _menuDemibold!!
        _menuDemibold = ImageVector.Builder(
            name = "Menu.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1033.0f, 888.9f),
                        PathNode.LineTo(1033.0f, 924.3f),
                        PathNode.QuadTo(1033.0f, 943.4f, 1021.5f, 953.2f),
                        PathNode.QuadTo(1010.0f, 962.9f, 991.4f, 962.9f),
                        PathNode.LineTo(151.4f, 962.9f),
                        PathNode.QuadTo(133.4f, 962.9f, 121.6f, 952.4f),
                        PathNode.QuadTo(109.9f, 942.0f, 109.9f, 925.3f),
                        PathNode.LineTo(109.9f, 889.9f),
                        PathNode.QuadTo(109.9f, 871.2f, 121.1f, 860.8f),
                        PathNode.QuadTo(132.4f, 850.3f, 151.4f, 850.3f),
                        PathNode.LineTo(991.4f, 850.3f),
                        PathNode.QuadTo(1010.0f, 850.3f, 1021.5f, 860.0f),
                        PathNode.QuadTo(1033.0f, 869.7f, 1033.0f, 888.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1033.0f, 218.6f),
                        PathNode.LineTo(1033.0f, 254.0f),
                        PathNode.QuadTo(1033.0f, 273.1f, 1021.5f, 282.8f),
                        PathNode.QuadTo(1010.0f, 292.6f, 991.4f, 292.6f),
                        PathNode.LineTo(151.4f, 292.6f),
                        PathNode.QuadTo(133.4f, 292.6f, 121.6f, 282.1f),
                        PathNode.QuadTo(109.9f, 271.7f, 109.9f, 255.0f),
                        PathNode.LineTo(109.9f, 219.6f),
                        PathNode.QuadTo(109.9f, 200.9f, 121.1f, 190.4f),
                        PathNode.QuadTo(132.4f, 180.0f, 151.4f, 180.0f),
                        PathNode.LineTo(991.4f, 180.0f),
                        PathNode.QuadTo(1010.0f, 180.0f, 1021.5f, 189.7f),
                        PathNode.QuadTo(1033.0f, 199.4f, 1033.0f, 218.6f),
                        PathNode.Close,
                        PathNode.MoveTo(1033.0f, 553.7f),
                        PathNode.LineTo(1033.0f, 589.1f),
                        PathNode.QuadTo(1033.0f, 608.3f, 1021.5f, 618.0f),
                        PathNode.QuadTo(1010.0f, 627.7f, 991.4f, 627.7f),
                        PathNode.LineTo(151.4f, 627.7f),
                        PathNode.QuadTo(133.4f, 627.7f, 121.6f, 617.3f),
                        PathNode.QuadTo(109.9f, 606.8f, 109.9f, 590.1f),
                        PathNode.LineTo(109.9f, 554.7f),
                        PathNode.QuadTo(109.9f, 536.0f, 121.1f, 525.6f),
                        PathNode.QuadTo(132.4f, 515.1f, 151.4f, 515.1f),
                        PathNode.LineTo(991.4f, 515.1f),
                        PathNode.QuadTo(1010.0f, 515.1f, 1021.5f, 524.9f),
                        PathNode.QuadTo(1033.0f, 534.6f, 1033.0f, 553.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _menuDemibold!!
    }

private var _menuDemibold: ImageVector? = null
