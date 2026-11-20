// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.RearScreen: ImageVector
    get() = MiuixIcons.Glass.Regular.RearScreen

val MiuixIcons.Glass.Light.RearScreen: ImageVector
    get() {
        if (_rearScreenSurfaceLight != null) return _rearScreenSurfaceLight!!
        _rearScreenSurfaceLight = buildHyperOSIcon(
            name = "RearScreen.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(1018.1f, 780.3f),
                PathNode.QuadTo(994.4f, 826.2f, 950.4f, 848f),
                PathNode.QuadTo(928.2f, 859.1f, 895.1f, 861.9f),
                PathNode.QuadTo(861.9f, 864.7f, 779.9f, 864.7f),
                PathNode.HorizontalTo(550.1f),
                PathNode.QuadTo(469.1f, 864.7f, 435.4f, 861.9f),
                PathNode.QuadTo(401.8f, 859.1f, 379.6f, 848f),
                PathNode.QuadTo(335.6f, 826.2f, 311.9f, 780.3f),
                PathNode.QuadTo(300.8f, 757.6f, 298f, 724.4f),
                PathNode.QuadTo(295.2f, 691.2f, 295.2f, 610.2f),
                PathNode.VerticalTo(139.8f),
                PathNode.QuadTo(295.2f, 58.8f, 298f, 25.6f),
                PathNode.QuadTo(300.8f, -7.6f, 311.9f, -30.3f),
                PathNode.QuadTo(335.6f, -76.2f, 379.6f, -98f),
                PathNode.QuadTo(401.8f, -109.1f, 435.4f, -111.9f),
                PathNode.QuadTo(469.1f, -114.7f, 550.1f, -114.7f),
                PathNode.HorizontalTo(779.9f),
                PathNode.QuadTo(861.9f, -114.7f, 895.1f, -111.9f),
                PathNode.QuadTo(928.2f, -109.1f, 950.4f, -98f),
                PathNode.QuadTo(994.4f, -76.2f, 1018.1f, -30.3f),
                PathNode.QuadTo(1029.2f, -7.6f, 1032f, 25.6f),
                PathNode.QuadTo(1034.8f, 58.8f, 1034.8f, 139.8f),
                PathNode.VerticalTo(610.2f),
                PathNode.QuadTo(1034.8f, 691.2f, 1032f, 724.4f),
                PathNode.QuadTo(1029.2f, 757.6f, 1018.1f, 780.3f),
                PathNode.Close,
                PathNode.MoveTo(947.3f, 701.3f),
                PathNode.VerticalTo(568.2f),
                PathNode.QuadTo(947.3f, 537.4f, 925.2f, 515.3f),
                PathNode.QuadTo(903.1f, 493.2f, 871.9f, 493.2f),
                PathNode.HorizontalTo(457.7f),
                PathNode.QuadTo(426.4f, 493.2f, 404.3f, 515.3f),
                PathNode.QuadTo(382.2f, 537.4f, 382.2f, 568.2f),
                PathNode.VerticalTo(701.3f),
                PathNode.QuadTo(382.2f, 732.1f, 404.6f, 754.2f),
                PathNode.QuadTo(426.9f, 776.3f, 457.7f, 776.3f),
                PathNode.HorizontalTo(871.9f),
                PathNode.QuadTo(903.1f, 776.3f, 925.2f, 754.2f),
                PathNode.QuadTo(947.3f, 732.1f, 947.3f, 701.3f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _rearScreenSurfaceLight!!
    }

private var _rearScreenSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.RearScreen: ImageVector
    get() {
        if (_rearScreenSurfaceNormal != null) return _rearScreenSurfaceNormal!!
        _rearScreenSurfaceNormal = buildHyperOSIcon(
            name = "RearScreen.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(1024.2f, 782.2f),
                PathNode.QuadTo(999.6f, 830.2f, 952.9f, 853.5f),
                PathNode.QuadTo(929.4f, 865.2f, 895.4f, 868.2f),
                PathNode.QuadTo(861.3f, 871.1f, 779.3f, 871.1f),
                PathNode.HorizontalTo(550.7f),
                PathNode.QuadTo(469.7f, 871.1f, 435.1f, 868.2f),
                PathNode.QuadTo(400.6f, 865.2f, 377.1f, 853.5f),
                PathNode.QuadTo(330.4f, 830.2f, 305.8f, 782.2f),
                PathNode.QuadTo(294.1f, 757.9f, 291.1f, 723.8f),
                PathNode.QuadTo(288.2f, 689.7f, 288.2f, 608.7f),
                PathNode.VerticalTo(141.3f),
                PathNode.QuadTo(288.2f, 60.3f, 291.1f, 26.2f),
                PathNode.QuadTo(294.1f, -7.9f, 305.8f, -32.2f),
                PathNode.QuadTo(330.4f, -80.2f, 377.1f, -103.5f),
                PathNode.QuadTo(400.6f, -115.2f, 435.1f, -118.2f),
                PathNode.QuadTo(469.7f, -121.1f, 550.7f, -121.1f),
                PathNode.HorizontalTo(779.3f),
                PathNode.QuadTo(861.3f, -121.1f, 895.4f, -118.2f),
                PathNode.QuadTo(929.4f, -115.2f, 952.9f, -103.5f),
                PathNode.QuadTo(999.6f, -80.2f, 1024.2f, -32.2f),
                PathNode.QuadTo(1035.9f, -7.9f, 1038.9f, 26.2f),
                PathNode.QuadTo(1041.8f, 60.3f, 1041.8f, 141.3f),
                PathNode.VerticalTo(608.7f),
                PathNode.QuadTo(1041.8f, 689.7f, 1038.9f, 723.8f),
                PathNode.QuadTo(1035.9f, 757.9f, 1024.2f, 782.2f),
                PathNode.Close,
                PathNode.MoveTo(949.2f, 705.9f),
                PathNode.VerticalTo(563.9f),
                PathNode.QuadTo(949.2f, 534.4f, 927.8f, 513f),
                PathNode.QuadTo(906.5f, 491.7f, 876.8f, 491.7f),
                PathNode.HorizontalTo(453.1f),
                PathNode.QuadTo(423.4f, 491.7f, 402f, 513f),
                PathNode.QuadTo(380.7f, 534.4f, 380.7f, 563.9f),
                PathNode.VerticalTo(705.9f),
                PathNode.QuadTo(380.7f, 735.5f, 402.1f, 756.8f),
                PathNode.QuadTo(423.5f, 778.2f, 453.1f, 778.2f),
                PathNode.HorizontalTo(876.8f),
                PathNode.QuadTo(906.5f, 778.2f, 927.8f, 756.8f),
                PathNode.QuadTo(949.2f, 735.5f, 949.2f, 705.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _rearScreenSurfaceNormal!!
    }

private var _rearScreenSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.RearScreen: ImageVector
    get() {
        if (_rearScreenSurfaceRegular != null) return _rearScreenSurfaceRegular!!
        _rearScreenSurfaceRegular = buildHyperOSIcon(
            name = "RearScreen.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(1027f, 783f),
                PathNode.QuadTo(1002f, 832f, 954f, 856f),
                PathNode.QuadTo(930f, 868f, 895.5f, 871f),
                PathNode.QuadTo(861f, 874f, 779f, 874f),
                PathNode.HorizontalTo(551f),
                PathNode.QuadTo(470f, 874f, 435f, 871f),
                PathNode.QuadTo(400f, 868f, 376f, 856f),
                PathNode.QuadTo(328f, 832f, 303f, 783f),
                PathNode.QuadTo(291f, 758f, 288f, 723.5f),
                PathNode.QuadTo(285f, 689f, 285f, 608f),
                PathNode.VerticalTo(142f),
                PathNode.QuadTo(285f, 61f, 288f, 26.5f),
                PathNode.QuadTo(291f, -8f, 303f, -33f),
                PathNode.QuadTo(328f, -82f, 376f, -106f),
                PathNode.QuadTo(400f, -118f, 435f, -121f),
                PathNode.QuadTo(470f, -124f, 551f, -124f),
                PathNode.HorizontalTo(779f),
                PathNode.QuadTo(861f, -124f, 895.5f, -121f),
                PathNode.QuadTo(930f, -118f, 954f, -106f),
                PathNode.QuadTo(1002f, -82f, 1027f, -33f),
                PathNode.QuadTo(1039f, -8f, 1042f, 26.5f),
                PathNode.QuadTo(1045f, 61f, 1045f, 142f),
                PathNode.VerticalTo(608f),
                PathNode.QuadTo(1045f, 689f, 1042f, 723.5f),
                PathNode.QuadTo(1039f, 758f, 1027f, 783f),
                PathNode.Close,
                PathNode.MoveTo(950f, 708f),
                PathNode.VerticalTo(562f),
                PathNode.QuadTo(950f, 533f, 929f, 512f),
                PathNode.QuadTo(908f, 491f, 879f, 491f),
                PathNode.HorizontalTo(451f),
                PathNode.QuadTo(422f, 491f, 401f, 512f),
                PathNode.QuadTo(380f, 533f, 380f, 562f),
                PathNode.VerticalTo(708f),
                PathNode.QuadTo(380f, 737f, 401f, 758f),
                PathNode.QuadTo(422f, 779f, 451f, 779f),
                PathNode.HorizontalTo(879f),
                PathNode.QuadTo(908f, 779f, 929f, 758f),
                PathNode.QuadTo(950f, 737f, 950f, 708f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _rearScreenSurfaceRegular!!
    }

private var _rearScreenSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.RearScreen: ImageVector
    get() {
        if (_rearScreenSurfaceMedium != null) return _rearScreenSurfaceMedium!!
        _rearScreenSurfaceMedium = buildHyperOSIcon(
            name = "RearScreen.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(1032.1f, 784.4f),
                PathNode.QuadTo(1006.3f, 835f, 956.2f, 860.6f),
                PathNode.QuadTo(931.1f, 873.1f, 895.8f, 876.3f),
                PathNode.QuadTo(860.5f, 879.4f, 778.5f, 879.4f),
                PathNode.HorizontalTo(551.5f),
                PathNode.QuadTo(470.5f, 879.4f, 434.7f, 876.3f),
                PathNode.QuadTo(398.9f, 873.1f, 373.8f, 860.6f),
                PathNode.QuadTo(323.7f, 835f, 297.9f, 784.4f),
                PathNode.QuadTo(285.3f, 758.3f, 282.2f, 723.1f),
                PathNode.QuadTo(279.1f, 687.9f, 279.1f, 606.6f),
                PathNode.VerticalTo(143.4f),
                PathNode.QuadTo(279.1f, 62.1f, 282.2f, 26.9f),
                PathNode.QuadTo(285.3f, -8.3f, 297.9f, -34.4f),
                PathNode.QuadTo(323.7f, -85f, 373.8f, -110.6f),
                PathNode.QuadTo(398.9f, -123.1f, 434.7f, -126.3f),
                PathNode.QuadTo(470.5f, -129.4f, 551.5f, -129.4f),
                PathNode.HorizontalTo(778.5f),
                PathNode.QuadTo(860.5f, -129.4f, 895.8f, -126.3f),
                PathNode.QuadTo(931.1f, -123.1f, 956.2f, -110.6f),
                PathNode.QuadTo(1006.3f, -85f, 1032.1f, -34.4f),
                PathNode.QuadTo(1044.7f, -8.3f, 1047.8f, 26.9f),
                PathNode.QuadTo(1050.9f, 62.1f, 1050.9f, 143.4f),
                PathNode.VerticalTo(606.6f),
                PathNode.QuadTo(1050.9f, 687.9f, 1047.8f, 723.1f),
                PathNode.QuadTo(1044.7f, 758.3f, 1032.1f, 784.4f),
                PathNode.Close,
                PathNode.MoveTo(941.9f, 700.2f),
                PathNode.VerticalTo(569.8f),
                PathNode.QuadTo(941.9f, 539.5f, 921.7f, 519.3f),
                PathNode.QuadTo(901.5f, 499.1f, 871.2f, 499.1f),
                PathNode.HorizontalTo(458.8f),
                PathNode.QuadTo(428.5f, 499.1f, 408.3f, 519.3f),
                PathNode.QuadTo(388.1f, 539.5f, 388.1f, 569.8f),
                PathNode.VerticalTo(700.2f),
                PathNode.QuadTo(388.1f, 730.5f, 408.3f, 750.7f),
                PathNode.QuadTo(428.5f, 770.9f, 458.8f, 770.9f),
                PathNode.HorizontalTo(871.2f),
                PathNode.QuadTo(901.5f, 770.9f, 921.7f, 750.7f),
                PathNode.QuadTo(941.9f, 730.5f, 941.9f, 700.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _rearScreenSurfaceMedium!!
    }

private var _rearScreenSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.RearScreen: ImageVector
    get() {
        if (_rearScreenSurfaceDemibold != null) return _rearScreenSurfaceDemibold!!
        _rearScreenSurfaceDemibold = buildHyperOSIcon(
            name = "RearScreen.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(1035.7f, 785.3f),
                PathNode.QuadTo(1009.4f, 837.1f, 957.7f, 863.8f),
                PathNode.QuadTo(931.8f, 876.7f, 896f, 880f),
                PathNode.QuadTo(860.1f, 883.2f, 778.1f, 883.2f),
                PathNode.HorizontalTo(551.9f),
                PathNode.QuadTo(470.9f, 883.2f, 434.5f, 880f),
                PathNode.QuadTo(398.2f, 876.7f, 372.3f, 863.8f),
                PathNode.QuadTo(320.6f, 837.1f, 294.3f, 785.3f),
                PathNode.QuadTo(281.4f, 758.5f, 278.1f, 722.8f),
                PathNode.QuadTo(274.9f, 687.2f, 274.9f, 605.7f),
                PathNode.VerticalTo(144.3f),
                PathNode.QuadTo(274.9f, 62.8f, 278.1f, 27.2f),
                PathNode.QuadTo(281.4f, -8.5f, 294.3f, -35.3f),
                PathNode.QuadTo(320.6f, -87.1f, 372.3f, -113.8f),
                PathNode.QuadTo(398.2f, -126.7f, 434.5f, -130f),
                PathNode.QuadTo(470.9f, -133.2f, 551.9f, -133.2f),
                PathNode.HorizontalTo(778.1f),
                PathNode.QuadTo(860.1f, -133.2f, 896f, -130f),
                PathNode.QuadTo(931.8f, -126.7f, 957.7f, -113.8f),
                PathNode.QuadTo(1009.4f, -87.1f, 1035.7f, -35.3f),
                PathNode.QuadTo(1048.6f, -8.5f, 1051.9f, 27.2f),
                PathNode.QuadTo(1055.1f, 62.8f, 1055.1f, 144.3f),
                PathNode.VerticalTo(605.7f),
                PathNode.QuadTo(1055.1f, 687.2f, 1051.9f, 722.8f),
                PathNode.QuadTo(1048.6f, 758.5f, 1035.7f, 785.3f),
                PathNode.Close,
                PathNode.MoveTo(936.2f, 694.7f),
                PathNode.VerticalTo(575.3f),
                PathNode.QuadTo(936.2f, 544f, 916.6f, 524.4f),
                PathNode.QuadTo(897f, 504.8f, 865.7f, 504.8f),
                PathNode.HorizontalTo(464.3f),
                PathNode.QuadTo(433f, 504.8f, 413.4f, 524.4f),
                PathNode.QuadTo(393.8f, 544f, 393.8f, 575.3f),
                PathNode.VerticalTo(694.7f),
                PathNode.QuadTo(393.8f, 726f, 413.4f, 745.6f),
                PathNode.QuadTo(433f, 765.2f, 464.3f, 765.2f),
                PathNode.HorizontalTo(865.7f),
                PathNode.QuadTo(897f, 765.2f, 916.6f, 745.6f),
                PathNode.QuadTo(936.2f, 726f, 936.2f, 694.7f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _rearScreenSurfaceDemibold!!
    }

private var _rearScreenSurfaceDemibold: ImageVector? = null

