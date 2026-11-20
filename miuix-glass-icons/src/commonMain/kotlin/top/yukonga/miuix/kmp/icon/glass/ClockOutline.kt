// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.ClockOutline: ImageVector
    get() = MiuixIcons.Glass.Regular.ClockOutline

val MiuixIcons.Glass.Light.ClockOutline: ImageVector
    get() {
        if (_waitingSurfaceLight != null) return _waitingSurfaceLight!!
        _waitingSurfaceLight = buildHyperOSIcon(
            name = "ClockOutline.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(635.3f, 683.9f),
                PathNode.VerticalTo(350.4f),
                PathNode.QuadTo(635.3f, 340.3f, 641.1f, 334.8f),
                PathNode.QuadTo(646.9f, 329.3f, 656.4f, 329.3f),
                PathNode.HorizontalTo(933.9f),
                PathNode.QuadTo(943.7f, 329.3f, 949.4f, 335.1f),
                PathNode.QuadTo(955f, 340.9f, 955f, 350.4f),
                PathNode.VerticalTo(366.6f),
                PathNode.QuadTo(955f, 376.4f, 949.4f, 382f),
                PathNode.QuadTo(943.7f, 387.7f, 933.9f, 387.7f),
                PathNode.HorizontalTo(694.7f),
                PathNode.VerticalTo(683.9f),
                PathNode.QuadTo(694.7f, 693.7f, 689f, 699.4f),
                PathNode.QuadTo(683.4f, 705f, 673.6f, 705f),
                PathNode.HorizontalTo(656.4f),
                PathNode.QuadTo(646.6f, 705f, 641f, 699.7f),
                PathNode.QuadTo(635.3f, 694.4f, 635.3f, 683.9f),
                PathNode.Close,
                PathNode.MoveTo(1161.7f, 375.1f),
                PathNode.QuadTo(1161.7f, 510.4f, 1094.9f, 624.6f),
                PathNode.QuadTo(1028.2f, 738.7f, 914.3f, 805.7f),
                PathNode.QuadTo(800.3f, 872.7f, 664.9f, 872.7f),
                PathNode.QuadTo(529.6f, 872.7f, 415.4f, 805.7f),
                PathNode.QuadTo(301.3f, 738.7f, 234.3f, 624.6f),
                PathNode.QuadTo(167.3f, 510.6f, 167.3f, 375.1f),
                PathNode.QuadTo(167.3f, 239.6f, 234.3f, 125.7f),
                PathNode.QuadTo(301.3f, 11.8f, 415.4f, -54.9f),
                PathNode.QuadTo(529.4f, -121.7f, 664.9f, -121.7f),
                PathNode.QuadTo(800.4f, -121.7f, 914.4f, -54.9f),
                PathNode.QuadTo(1028.3f, 11.8f, 1095f, 125.7f),
                PathNode.QuadTo(1161.7f, 239.7f, 1161.7f, 375.1f),
                PathNode.Close,
                PathNode.MoveTo(226.7f, 375f),
                PathNode.QuadTo(226.7f, 494.1f, 285.7f, 594.7f),
                PathNode.QuadTo(344.7f, 695.3f, 445.3f, 754.3f),
                PathNode.QuadTo(545.9f, 813.3f, 665f, 813.3f),
                PathNode.QuadTo(783.6f, 813.3f, 884.2f, 754.3f),
                PathNode.QuadTo(984.8f, 695.3f, 1044.1f, 594.7f),
                PathNode.QuadTo(1103.3f, 494.1f, 1103.3f, 375f),
                PathNode.QuadTo(1103.3f, 255.9f, 1044.1f, 155.3f),
                PathNode.QuadTo(984.8f, 54.7f, 884.2f, -4.3f),
                PathNode.QuadTo(783.6f, -63.3f, 665f, -63.3f),
                PathNode.QuadTo(545.9f, -63.3f, 445.3f, -4.3f),
                PathNode.QuadTo(344.7f, 54.7f, 285.7f, 155.3f),
                PathNode.QuadTo(226.7f, 255.9f, 226.7f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.1667f,
            translationY = 1042.1667f,
            autoMirror = false,
        )
        return _waitingSurfaceLight!!
    }

private var _waitingSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ClockOutline: ImageVector
    get() {
        if (_waitingSurfaceNormal != null) return _waitingSurfaceNormal!!
        _waitingSurfaceNormal = buildHyperOSIcon(
            name = "ClockOutline.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(626.2f, 677.8f),
                PathNode.VerticalTo(347.4f),
                PathNode.QuadTo(626.2f, 333.9f, 633.5f, 327f),
                PathNode.QuadTo(640.8f, 320.2f, 653.4f, 320.2f),
                PathNode.HorizontalTo(927.8f),
                PathNode.QuadTo(940.5f, 320.2f, 947.7f, 327.5f),
                PathNode.QuadTo(955f, 334.8f, 955f, 347.4f),
                PathNode.VerticalTo(369.6f),
                PathNode.QuadTo(955f, 382.3f, 947.7f, 389.6f),
                PathNode.QuadTo(940.5f, 396.8f, 927.8f, 396.8f),
                PathNode.HorizontalTo(703.8f),
                PathNode.VerticalTo(677.8f),
                PathNode.QuadTo(703.8f, 690.5f, 696.6f, 697.7f),
                PathNode.QuadTo(689.3f, 705f, 676.6f, 705f),
                PathNode.HorizontalTo(653.4f),
                PathNode.QuadTo(640.7f, 705f, 633.4f, 698.2f),
                PathNode.QuadTo(626.2f, 691.4f, 626.2f, 677.8f),
                PathNode.Close,
                PathNode.MoveTo(1170.8f, 375f),
                PathNode.QuadTo(1170.8f, 512.9f, 1102.9f, 629f),
                PathNode.QuadTo(1034.9f, 745.1f, 918.9f, 813.5f),
                PathNode.QuadTo(802.8f, 881.8f, 665f, 881.8f),
                PathNode.QuadTo(527.1f, 881.8f, 411f, 813.5f),
                PathNode.QuadTo(294.9f, 745.1f, 226.5f, 629f),
                PathNode.QuadTo(158.2f, 512.9f, 158.2f, 375f),
                PathNode.QuadTo(158.2f, 237.1f, 226.5f, 121.1f),
                PathNode.QuadTo(294.9f, 5.1f, 411f, -62.9f),
                PathNode.QuadTo(527.1f, -130.8f, 665f, -130.8f),
                PathNode.QuadTo(802.9f, -130.8f, 918.9f, -62.9f),
                PathNode.QuadTo(1035f, 5.1f, 1102.9f, 121.1f),
                PathNode.QuadTo(1170.8f, 237.1f, 1170.8f, 375f),
                PathNode.Close,
                PathNode.MoveTo(235.8f, 375f),
                PathNode.QuadTo(235.8f, 492f, 293.5f, 590.4f),
                PathNode.QuadTo(351.1f, 688.9f, 449.6f, 746.5f),
                PathNode.QuadTo(548f, 804.2f, 665f, 804.2f),
                PathNode.QuadTo(781.1f, 804.2f, 879.6f, 746.5f),
                PathNode.QuadTo(978.1f, 688.9f, 1036.1f, 590.4f),
                PathNode.QuadTo(1094.2f, 492f, 1094.2f, 375f),
                PathNode.QuadTo(1094.2f, 258f, 1036.1f, 159.6f),
                PathNode.QuadTo(978.1f, 61.1f, 879.6f, 3.5f),
                PathNode.QuadTo(781.1f, -54.2f, 665f, -54.2f),
                PathNode.QuadTo(548f, -54.2f, 449.6f, 3.5f),
                PathNode.QuadTo(351.1f, 61.1f, 293.5f, 159.6f),
                PathNode.QuadTo(235.8f, 258f, 235.8f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.1667f,
            translationY = 1042.1667f,
            autoMirror = false,
        )
        return _waitingSurfaceNormal!!
    }

private var _waitingSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ClockOutline: ImageVector
    get() {
        if (_waitingSurfaceRegular != null) return _waitingSurfaceRegular!!
        _waitingSurfaceRegular = buildHyperOSIcon(
            name = "ClockOutline.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(622f, 675f),
                PathNode.VerticalTo(346f),
                PathNode.QuadTo(622f, 331f, 630f, 323.5f),
                PathNode.QuadTo(638f, 316f, 652f, 316f),
                PathNode.HorizontalTo(925f),
                PathNode.QuadTo(939f, 316f, 947f, 324f),
                PathNode.QuadTo(955f, 332f, 955f, 346f),
                PathNode.VerticalTo(371f),
                PathNode.QuadTo(955f, 385f, 947f, 393f),
                PathNode.QuadTo(939f, 401f, 925f, 401f),
                PathNode.HorizontalTo(708f),
                PathNode.VerticalTo(675f),
                PathNode.QuadTo(708f, 689f, 700f, 697f),
                PathNode.QuadTo(692f, 705f, 678f, 705f),
                PathNode.HorizontalTo(652f),
                PathNode.QuadTo(638f, 705f, 630f, 697.5f),
                PathNode.QuadTo(622f, 690f, 622f, 675f),
                PathNode.Close,
                PathNode.MoveTo(1175f, 375f),
                PathNode.QuadTo(1175f, 514f, 1106.5f, 631f),
                PathNode.QuadTo(1038f, 748f, 921f, 817f),
                PathNode.QuadTo(804f, 886f, 665f, 886f),
                PathNode.QuadTo(526f, 886f, 409f, 817f),
                PathNode.QuadTo(292f, 748f, 223f, 631f),
                PathNode.QuadTo(154f, 514f, 154f, 375f),
                PathNode.QuadTo(154f, 236f, 223f, 119f),
                PathNode.QuadTo(292f, 2f, 409f, -66.5f),
                PathNode.QuadTo(526f, -135f, 665f, -135f),
                PathNode.QuadTo(804f, -135f, 921f, -66.5f),
                PathNode.QuadTo(1038f, 2f, 1106.5f, 119f),
                PathNode.QuadTo(1175f, 236f, 1175f, 375f),
                PathNode.Close,
                PathNode.MoveTo(240f, 375f),
                PathNode.QuadTo(240f, 491f, 297f, 588.5f),
                PathNode.QuadTo(354f, 686f, 451.5f, 743f),
                PathNode.QuadTo(549f, 800f, 665f, 800f),
                PathNode.QuadTo(780f, 800f, 877.5f, 743f),
                PathNode.QuadTo(975f, 686f, 1032.5f, 588.5f),
                PathNode.QuadTo(1090f, 491f, 1090f, 375f),
                PathNode.QuadTo(1090f, 259f, 1032.5f, 161.5f),
                PathNode.QuadTo(975f, 64f, 877.5f, 7f),
                PathNode.QuadTo(780f, -50f, 665f, -50f),
                PathNode.QuadTo(549f, -50f, 451.5f, 7f),
                PathNode.QuadTo(354f, 64f, 297f, 161.5f),
                PathNode.QuadTo(240f, 259f, 240f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.1667f,
            translationY = 1042.1667f,
            autoMirror = false,
        )
        return _waitingSurfaceRegular!!
    }

private var _waitingSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ClockOutline: ImageVector
    get() {
        if (_waitingSurfaceMedium != null) return _waitingSurfaceMedium!!
        _waitingSurfaceMedium = buildHyperOSIcon(
            name = "ClockOutline.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(613.9f, 672.3f),
                PathNode.VerticalTo(343.3f),
                PathNode.QuadTo(613.9f, 325.6f, 623.2f, 316.7f),
                PathNode.QuadTo(632.6f, 307.9f, 649.3f, 307.9f),
                PathNode.HorizontalTo(922.3f),
                PathNode.QuadTo(939f, 307.9f, 948.4f, 317.2f),
                PathNode.QuadTo(957.7f, 326.6f, 957.7f, 343.3f),
                PathNode.VerticalTo(373.7f),
                PathNode.QuadTo(957.7f, 390.4f, 948.4f, 399.8f),
                PathNode.QuadTo(939f, 409.1f, 922.3f, 409.1f),
                PathNode.HorizontalTo(716.1f),
                PathNode.VerticalTo(672.3f),
                PathNode.QuadTo(716.1f, 689f, 706.8f, 698.4f),
                PathNode.QuadTo(697.4f, 707.7f, 680.6f, 707.7f),
                PathNode.HorizontalTo(649.4f),
                PathNode.QuadTo(632.6f, 707.7f, 623.2f, 698.9f),
                PathNode.QuadTo(613.9f, 690f, 613.9f, 672.3f),
                PathNode.Close,
                PathNode.MoveTo(1183.1f, 375f),
                PathNode.QuadTo(1183.1f, 516.2f, 1113.5f, 635f),
                PathNode.QuadTo(1043.9f, 753.9f, 925.1f, 824f),
                PathNode.QuadTo(806.2f, 894.1f, 665f, 894.1f),
                PathNode.QuadTo(523.8f, 894.1f, 405f, 824f),
                PathNode.QuadTo(286.1f, 753.9f, 216f, 635.1f),
                PathNode.QuadTo(145.9f, 516.2f, 145.9f, 375f),
                PathNode.QuadTo(145.9f, 233.8f, 216f, 115f),
                PathNode.QuadTo(286.1f, -3.9f, 404.9f, -73.5f),
                PathNode.QuadTo(523.8f, -143.1f, 665f, -143.1f),
                PathNode.QuadTo(806.2f, -143.1f, 925f, -73.5f),
                PathNode.QuadTo(1043.9f, -3.9f, 1113.5f, 114.9f),
                PathNode.QuadTo(1183.1f, 233.8f, 1183.1f, 375f),
                PathNode.Close,
                PathNode.MoveTo(248.1f, 375f),
                PathNode.QuadTo(248.1f, 488.8f, 304f, 584.5f),
                PathNode.QuadTo(359.9f, 680.1f, 455.6f, 736f),
                PathNode.QuadTo(551.2f, 791.9f, 665f, 791.9f),
                PathNode.QuadTo(777.8f, 791.9f, 873.6f, 736f),
                PathNode.QuadTo(969.3f, 680.1f, 1025.6f, 584.4f),
                PathNode.QuadTo(1081.9f, 488.8f, 1081.9f, 375f),
                PathNode.QuadTo(1081.9f, 261.2f, 1025.6f, 165.6f),
                PathNode.QuadTo(969.3f, 69.9f, 873.6f, 14f),
                PathNode.QuadTo(777.9f, -41.9f, 665f, -41.9f),
                PathNode.QuadTo(551.2f, -41.9f, 455.5f, 14f),
                PathNode.QuadTo(359.9f, 69.9f, 304f, 165.6f),
                PathNode.QuadTo(248.1f, 261.2f, 248.1f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.1667f,
            translationY = 1042.1667f,
            autoMirror = false,
        )
        return _waitingSurfaceMedium!!
    }

private var _waitingSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ClockOutline: ImageVector
    get() {
        if (_waitingSurfaceDemibold != null) return _waitingSurfaceDemibold!!
        _waitingSurfaceDemibold = buildHyperOSIcon(
            name = "ClockOutline.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(608.2f, 670.4f),
                PathNode.VerticalTo(341.4f),
                PathNode.QuadTo(608.2f, 321.8f, 618.5f, 312f),
                PathNode.QuadTo(628.8f, 302.2f, 647.4f, 302.2f),
                PathNode.HorizontalTo(920.4f),
                PathNode.QuadTo(939f, 302.2f, 949.3f, 312.5f),
                PathNode.QuadTo(959.6f, 322.8f, 959.6f, 341.4f),
                PathNode.VerticalTo(375.6f),
                PathNode.QuadTo(959.6f, 394.2f, 949.3f, 404.5f),
                PathNode.QuadTo(939f, 414.8f, 920.4f, 414.8f),
                PathNode.HorizontalTo(721.8f),
                PathNode.VerticalTo(670.4f),
                PathNode.QuadTo(721.8f, 689f, 711.5f, 699.3f),
                PathNode.QuadTo(701.2f, 709.6f, 682.4f, 709.6f),
                PathNode.HorizontalTo(647.6f),
                PathNode.QuadTo(628.8f, 709.6f, 618.5f, 699.8f),
                PathNode.QuadTo(608.2f, 690f, 608.2f, 670.4f),
                PathNode.Close,
                PathNode.MoveTo(1188.8f, 374.9f),
                PathNode.QuadTo(1188.8f, 517.7f, 1118.4f, 637.9f),
                PathNode.QuadTo(1048.1f, 758f, 928f, 828.9f),
                PathNode.QuadTo(807.8f, 899.8f, 665.1f, 899.8f),
                PathNode.QuadTo(522.3f, 899.8f, 402.1f, 828.9f),
                PathNode.QuadTo(282f, 758.1f, 211.1f, 637.9f),
                PathNode.QuadTo(140.2f, 517.8f, 140.2f, 375.1f),
                PathNode.QuadTo(140.2f, 232.3f, 211.1f, 112.1f),
                PathNode.QuadTo(281.9f, -8.1f, 402.1f, -78.4f),
                PathNode.QuadTo(522.2f, -148.8f, 664.9f, -148.8f),
                PathNode.QuadTo(807.7f, -148.8f, 927.9f, -78.4f),
                PathNode.QuadTo(1048.1f, -8.1f, 1118.4f, 112f),
                PathNode.QuadTo(1188.8f, 232.2f, 1188.8f, 374.9f),
                PathNode.Close,
                PathNode.MoveTo(253.8f, 375.1f),
                PathNode.QuadTo(253.8f, 487.3f, 308.9f, 581.6f),
                PathNode.QuadTo(364.1f, 675.9f, 458.4f, 731.1f),
                PathNode.QuadTo(552.8f, 786.2f, 665f, 786.2f),
                PathNode.QuadTo(776.3f, 786.2f, 870.8f, 731.1f),
                PathNode.QuadTo(965.4f, 675.9f, 1020.8f, 581.5f),
                PathNode.QuadTo(1076.2f, 487.2f, 1076.2f, 374.9f),
                PathNode.QuadTo(1076.2f, 262.7f, 1020.8f, 168.4f),
                PathNode.QuadTo(965.4f, 74.1f, 870.9f, 18.9f),
                PathNode.QuadTo(776.5f, -36.2f, 665f, -36.2f),
                PathNode.QuadTo(552.7f, -36.2f, 458.4f, 18.9f),
                PathNode.QuadTo(364.1f, 74.1f, 308.9f, 168.5f),
                PathNode.QuadTo(253.8f, 262.8f, 253.8f, 375.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.1667f,
            translationY = 1042.1667f,
            autoMirror = false,
        )
        return _waitingSurfaceDemibold!!
    }

private var _waitingSurfaceDemibold: ImageVector? = null

