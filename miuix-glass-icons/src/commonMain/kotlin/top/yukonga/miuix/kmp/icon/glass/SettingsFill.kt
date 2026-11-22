// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.SettingsFill: ImageVector
    get() = MiuixIcons.Glass.Regular.SettingsFill

val MiuixIcons.Glass.Light.SettingsFill: ImageVector
    get() {
        if (_appSettingsSurfaceLight != null) return _appSettingsSurfaceLight!!
        _appSettingsSurfaceLight = buildHyperOSIcon(
            name = "SettingsFill.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(511.6f, 375f),
                PathNode.QuadTo(511.6f, 333.3f, 532.2f, 298.1f),
                PathNode.QuadTo(552.8f, 262.8f, 588.1f, 242.2f),
                PathNode.QuadTo(623.3f, 221.6f, 665f, 221.6f),
                PathNode.QuadTo(706.7f, 221.6f, 741.9f, 242.2f),
                PathNode.QuadTo(777.2f, 262.8f, 797.8f, 298.1f),
                PathNode.QuadTo(818.4f, 333.3f, 818.4f, 375f),
                PathNode.QuadTo(818.4f, 416.7f, 797.8f, 451.9f),
                PathNode.QuadTo(777.2f, 487.2f, 741.9f, 507.8f),
                PathNode.QuadTo(706.7f, 528.4f, 665f, 528.4f),
                PathNode.QuadTo(623.3f, 528.4f, 588.1f, 507.8f),
                PathNode.QuadTo(552.8f, 487.2f, 532.2f, 451.9f),
                PathNode.QuadTo(511.6f, 416.7f, 511.6f, 375f),
                PathNode.Close,
                PathNode.MoveTo(216.9f, 546f),
                PathNode.VerticalTo(204f),
                PathNode.QuadTo(216.9f, 160.6f, 233.6f, 131.9f),
                PathNode.QuadTo(250.3f, 103.3f, 286.8f, 83.1f),
                PathNode.LineTo(594.2f, -90.3f),
                PathNode.QuadTo(631.1f, -111.1f, 665f, -111.1f),
                PathNode.QuadTo(698.9f, -111.1f, 735.8f, -90.3f),
                PathNode.LineTo(1043.2f, 83.1f),
                PathNode.QuadTo(1079.7f, 103.3f, 1096.4f, 131.9f),
                PathNode.QuadTo(1113.1f, 160.6f, 1113.1f, 204f),
                PathNode.VerticalTo(546f),
                PathNode.QuadTo(1113.1f, 589.4f, 1096.4f, 618.1f),
                PathNode.QuadTo(1079.7f, 646.7f, 1043.2f, 666.9f),
                PathNode.LineTo(735.8f, 840.3f),
                PathNode.QuadTo(698.9f, 861.1f, 665f, 861.1f),
                PathNode.QuadTo(631.1f, 861.1f, 594.2f, 840.3f),
                PathNode.LineTo(286.8f, 666.9f),
                PathNode.QuadTo(250.3f, 646.7f, 233.6f, 618.1f),
                PathNode.QuadTo(216.9f, 589.4f, 216.9f, 546f),
                PathNode.Close,
                PathNode.MoveTo(905.6f, 375f),
                PathNode.QuadTo(905.6f, 309.2f, 873.2f, 254.2f),
                PathNode.QuadTo(840.8f, 199.2f, 785.8f, 167.1f),
                PathNode.QuadTo(730.8f, 134.9f, 665f, 134.9f),
                PathNode.QuadTo(599.7f, 134.9f, 544.4f, 167.1f),
                PathNode.QuadTo(489.2f, 199.2f, 457.1f, 254.4f),
                PathNode.QuadTo(424.9f, 309.7f, 424.9f, 375f),
                PathNode.QuadTo(424.9f, 440.8f, 457.1f, 495.8f),
                PathNode.QuadTo(489.2f, 550.8f, 544.2f, 583.2f),
                PathNode.QuadTo(599.2f, 615.6f, 665f, 615.6f),
                PathNode.QuadTo(730.8f, 615.6f, 786f, 583.4f),
                PathNode.QuadTo(841.2f, 551.2f, 873.4f, 496f),
                PathNode.QuadTo(905.6f, 440.8f, 905.6f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _appSettingsSurfaceLight!!
    }

private var _appSettingsSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.SettingsFill: ImageVector
    get() {
        if (_appSettingsSurfaceNormal != null) return _appSettingsSurfaceNormal!!
        _appSettingsSurfaceNormal = buildHyperOSIcon(
            name = "SettingsFill.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(517.4f, 375f),
                PathNode.QuadTo(517.4f, 335.2f, 537.2f, 301.1f),
                PathNode.QuadTo(557.1f, 267.1f, 591.1f, 247.2f),
                PathNode.QuadTo(625.2f, 227.4f, 665f, 227.4f),
                PathNode.QuadTo(704.8f, 227.4f, 738.9f, 247.2f),
                PathNode.QuadTo(772.9f, 267.1f, 792.8f, 301.1f),
                PathNode.QuadTo(812.6f, 335.2f, 812.6f, 375f),
                PathNode.QuadTo(812.6f, 414.8f, 792.8f, 448.9f),
                PathNode.QuadTo(772.9f, 482.9f, 738.9f, 502.8f),
                PathNode.QuadTo(704.8f, 522.6f, 665f, 522.6f),
                PathNode.QuadTo(625.2f, 522.6f, 591.1f, 502.8f),
                PathNode.QuadTo(557.1f, 482.9f, 537.2f, 448.9f),
                PathNode.QuadTo(517.4f, 414.8f, 517.4f, 375f),
                PathNode.Close,
                PathNode.MoveTo(208f, 546f),
                PathNode.VerticalTo(204f),
                PathNode.QuadTo(208f, 158.1f, 226f, 127.5f),
                PathNode.QuadTo(243.9f, 96.9f, 282.8f, 75.5f),
                PathNode.LineTo(589.9f, -97.7f),
                PathNode.QuadTo(629f, -120f, 665f, -120f),
                PathNode.QuadTo(701f, -120f, 740.1f, -97.7f),
                PathNode.LineTo(1047.2f, 75.5f),
                PathNode.QuadTo(1086.1f, 96.9f, 1104f, 127.5f),
                PathNode.QuadTo(1122f, 158.1f, 1122f, 204f),
                PathNode.VerticalTo(546f),
                PathNode.QuadTo(1122f, 591.9f, 1104f, 622.5f),
                PathNode.QuadTo(1086.1f, 653.1f, 1047.2f, 674.5f),
                PathNode.LineTo(740.1f, 847.7f),
                PathNode.QuadTo(701f, 870f, 665f, 870f),
                PathNode.QuadTo(629f, 870f, 589.9f, 847.7f),
                PathNode.LineTo(282.8f, 674.5f),
                PathNode.QuadTo(243.9f, 653.1f, 226f, 622.5f),
                PathNode.QuadTo(208f, 591.9f, 208f, 546f),
                PathNode.Close,
                PathNode.MoveTo(911.4f, 375f),
                PathNode.QuadTo(911.4f, 307.7f, 878.2f, 251.3f),
                PathNode.QuadTo(845.1f, 194.9f, 788.7f, 161.9f),
                PathNode.QuadTo(732.3f, 128.8f, 665f, 128.8f),
                PathNode.QuadTo(597.8f, 128.8f, 541.4f, 161.9f),
                PathNode.QuadTo(484.9f, 194.9f, 451.9f, 251.4f),
                PathNode.QuadTo(418.8f, 307.8f, 418.8f, 375f),
                PathNode.QuadTo(418.8f, 442.3f, 451.9f, 498.7f),
                PathNode.QuadTo(484.9f, 555.1f, 541.3f, 588.2f),
                PathNode.QuadTo(597.7f, 621.4f, 665f, 621.4f),
                PathNode.QuadTo(732.3f, 621.4f, 788.7f, 588.3f),
                PathNode.QuadTo(845.2f, 555.2f, 878.3f, 498.7f),
                PathNode.QuadTo(911.4f, 442.3f, 911.4f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _appSettingsSurfaceNormal!!
    }

private var _appSettingsSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.SettingsFill: ImageVector
    get() {
        if (_appSettingsSurfaceRegular != null) return _appSettingsSurfaceRegular!!
        _appSettingsSurfaceRegular = buildHyperOSIcon(
            name = "SettingsFill.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(520f, 375f),
                PathNode.QuadTo(520f, 336f, 539.5f, 302.5f),
                PathNode.QuadTo(559f, 269f, 592.5f, 249.5f),
                PathNode.QuadTo(626f, 230f, 665f, 230f),
                PathNode.QuadTo(704f, 230f, 737.5f, 249.5f),
                PathNode.QuadTo(771f, 269f, 790.5f, 302.5f),
                PathNode.QuadTo(810f, 336f, 810f, 375f),
                PathNode.QuadTo(810f, 414f, 790.5f, 447.5f),
                PathNode.QuadTo(771f, 481f, 737.5f, 500.5f),
                PathNode.QuadTo(704f, 520f, 665f, 520f),
                PathNode.QuadTo(626f, 520f, 592.5f, 500.5f),
                PathNode.QuadTo(559f, 481f, 539.5f, 447.5f),
                PathNode.QuadTo(520f, 414f, 520f, 375f),
                PathNode.Close,
                PathNode.MoveTo(204f, 546f),
                PathNode.VerticalTo(204f),
                PathNode.QuadTo(204f, 157f, 222.5f, 125.5f),
                PathNode.QuadTo(241f, 94f, 281f, 72f),
                PathNode.LineTo(588f, -101f),
                PathNode.QuadTo(628f, -124f, 665f, -124f),
                PathNode.QuadTo(702f, -124f, 742f, -101f),
                PathNode.LineTo(1049f, 72f),
                PathNode.QuadTo(1089f, 94f, 1107.5f, 125.5f),
                PathNode.QuadTo(1126f, 157f, 1126f, 204f),
                PathNode.VerticalTo(546f),
                PathNode.QuadTo(1126f, 593f, 1107.5f, 624.5f),
                PathNode.QuadTo(1089f, 656f, 1049f, 678f),
                PathNode.LineTo(742f, 851f),
                PathNode.QuadTo(702f, 874f, 665f, 874f),
                PathNode.QuadTo(628f, 874f, 588f, 851f),
                PathNode.LineTo(281f, 678f),
                PathNode.QuadTo(241f, 656f, 222.5f, 624.5f),
                PathNode.QuadTo(204f, 593f, 204f, 546f),
                PathNode.Close,
                PathNode.MoveTo(914f, 375f),
                PathNode.QuadTo(914f, 307f, 880.5f, 250f),
                PathNode.QuadTo(847f, 193f, 790f, 159.5f),
                PathNode.QuadTo(733f, 126f, 665f, 126f),
                PathNode.QuadTo(597f, 126f, 540f, 159.5f),
                PathNode.QuadTo(483f, 193f, 449.5f, 250f),
                PathNode.QuadTo(416f, 307f, 416f, 375f),
                PathNode.QuadTo(416f, 443f, 449.5f, 500f),
                PathNode.QuadTo(483f, 557f, 540f, 590.5f),
                PathNode.QuadTo(597f, 624f, 665f, 624f),
                PathNode.QuadTo(733f, 624f, 790f, 590.5f),
                PathNode.QuadTo(847f, 557f, 880.5f, 500f),
                PathNode.QuadTo(914f, 443f, 914f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _appSettingsSurfaceRegular!!
    }

private var _appSettingsSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.SettingsFill: ImageVector
    get() {
        if (_appSettingsSurfaceMedium != null) return _appSettingsSurfaceMedium!!
        _appSettingsSurfaceMedium = buildHyperOSIcon(
            name = "SettingsFill.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(526.5f, 374.9f),
                PathNode.QuadTo(526.5f, 337.6f, 545f, 305.6f),
                PathNode.QuadTo(563.6f, 273.6f, 595.7f, 255f),
                PathNode.QuadTo(627.7f, 236.5f, 665.1f, 236.5f),
                PathNode.QuadTo(702.4f, 236.5f, 734.4f, 255f),
                PathNode.QuadTo(766.4f, 273.6f, 785f, 305.7f),
                PathNode.QuadTo(803.5f, 337.7f, 803.5f, 375.1f),
                PathNode.QuadTo(803.5f, 412.4f, 785f, 444.4f),
                PathNode.QuadTo(766.4f, 476.4f, 734.3f, 495f),
                PathNode.QuadTo(702.3f, 513.5f, 664.9f, 513.5f),
                PathNode.QuadTo(627.6f, 513.5f, 595.6f, 495f),
                PathNode.QuadTo(563.6f, 476.4f, 545f, 444.3f),
                PathNode.QuadTo(526.5f, 412.3f, 526.5f, 374.9f),
                PathNode.Close,
                PathNode.MoveTo(197.8f, 545.2f),
                PathNode.VerticalTo(204.8f),
                PathNode.QuadTo(197.8f, 155.9f, 217.2f, 122.5f),
                PathNode.QuadTo(236.7f, 89.1f, 278.6f, 66.1f),
                PathNode.LineTo(584.2f, -106.1f),
                PathNode.QuadTo(625.8f, -129.9f, 665f, -129.9f),
                PathNode.QuadTo(704.2f, -129.9f, 745.8f, -106.1f),
                PathNode.LineTo(1051.4f, 66.1f),
                PathNode.QuadTo(1093.3f, 89.1f, 1112.8f, 122.5f),
                PathNode.QuadTo(1132.2f, 155.9f, 1132.2f, 204.8f),
                PathNode.VerticalTo(545.2f),
                PathNode.QuadTo(1132.2f, 594.1f, 1112.8f, 627.5f),
                PathNode.QuadTo(1093.3f, 660.9f, 1051.4f, 683.9f),
                PathNode.LineTo(745.8f, 856.1f),
                PathNode.QuadTo(704.2f, 879.9f, 665f, 879.9f),
                PathNode.QuadTo(625.8f, 879.9f, 584.2f, 856.1f),
                PathNode.LineTo(278.6f, 683.9f),
                PathNode.QuadTo(236.7f, 660.9f, 217.2f, 627.5f),
                PathNode.QuadTo(197.8f, 594.1f, 197.8f, 545.2f),
                PathNode.Close,
                PathNode.MoveTo(918.3f, 375f),
                PathNode.QuadTo(918.3f, 305.8f, 884.2f, 247.8f),
                PathNode.QuadTo(850.2f, 189.8f, 792.2f, 155.8f),
                PathNode.QuadTo(734.2f, 121.7f, 665f, 121.7f),
                PathNode.QuadTo(595.8f, 121.7f, 537.8f, 155.8f),
                PathNode.QuadTo(479.8f, 189.8f, 445.8f, 247.8f),
                PathNode.QuadTo(411.7f, 305.8f, 411.7f, 375f),
                PathNode.QuadTo(411.7f, 444.2f, 445.8f, 502.2f),
                PathNode.QuadTo(479.8f, 560.2f, 537.8f, 594.2f),
                PathNode.QuadTo(595.8f, 628.3f, 665f, 628.3f),
                PathNode.QuadTo(734.2f, 628.3f, 792.2f, 594.2f),
                PathNode.QuadTo(850.2f, 560.2f, 884.2f, 502.2f),
                PathNode.QuadTo(918.3f, 444.2f, 918.3f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _appSettingsSurfaceMedium!!
    }

private var _appSettingsSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.SettingsFill: ImageVector
    get() {
        if (_appSettingsSurfaceDemibold != null) return _appSettingsSurfaceDemibold!!
        _appSettingsSurfaceDemibold = buildHyperOSIcon(
            name = "SettingsFill.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(531f, 374.9f),
                PathNode.QuadTo(531f, 338.8f, 548.9f, 307.8f),
                PathNode.QuadTo(566.8f, 276.8f, 597.9f, 258.9f),
                PathNode.QuadTo(628.9f, 241f, 665.1f, 241f),
                PathNode.QuadTo(701.2f, 241f, 732.2f, 258.9f),
                PathNode.QuadTo(763.2f, 276.8f, 781.1f, 307.9f),
                PathNode.QuadTo(799f, 338.9f, 799f, 375.1f),
                PathNode.QuadTo(799f, 411.2f, 781.1f, 442.2f),
                PathNode.QuadTo(763.2f, 473.2f, 732.1f, 491.1f),
                PathNode.QuadTo(701.1f, 509f, 664.9f, 509f),
                PathNode.QuadTo(628.8f, 509f, 597.8f, 491.1f),
                PathNode.QuadTo(566.8f, 473.2f, 548.9f, 442.1f),
                PathNode.QuadTo(531f, 411.1f, 531f, 374.9f),
                PathNode.Close,
                PathNode.MoveTo(193.4f, 544.6f),
                PathNode.VerticalTo(205.4f),
                PathNode.QuadTo(193.4f, 155.2f, 213.5f, 120.4f),
                PathNode.QuadTo(233.6f, 85.7f, 276.9f, 61.9f),
                PathNode.LineTo(581.6f, -109.7f),
                PathNode.QuadTo(624.3f, -134.1f, 665f, -134.1f),
                PathNode.QuadTo(705.7f, -134.1f, 748.4f, -109.7f),
                PathNode.LineTo(1053.1f, 61.9f),
                PathNode.QuadTo(1096.4f, 85.7f, 1116.5f, 120.4f),
                PathNode.QuadTo(1136.6f, 155.2f, 1136.6f, 205.4f),
                PathNode.VerticalTo(544.6f),
                PathNode.QuadTo(1136.6f, 594.8f, 1116.5f, 629.6f),
                PathNode.QuadTo(1096.4f, 664.3f, 1053.1f, 688.1f),
                PathNode.LineTo(748.4f, 859.7f),
                PathNode.QuadTo(705.7f, 884.1f, 665f, 884.1f),
                PathNode.QuadTo(624.3f, 884.1f, 581.6f, 859.7f),
                PathNode.LineTo(276.9f, 688.1f),
                PathNode.QuadTo(233.6f, 664.3f, 213.5f, 629.6f),
                PathNode.QuadTo(193.4f, 594.8f, 193.4f, 544.6f),
                PathNode.Close,
                PathNode.MoveTo(921.4f, 375f),
                PathNode.QuadTo(921.4f, 305f, 886.9f, 246.3f),
                PathNode.QuadTo(852.4f, 187.6f, 793.7f, 153.1f),
                PathNode.QuadTo(735f, 118.6f, 665f, 118.6f),
                PathNode.QuadTo(595f, 118.6f, 536.3f, 153.1f),
                PathNode.QuadTo(477.6f, 187.6f, 443.1f, 246.3f),
                PathNode.QuadTo(408.6f, 305f, 408.6f, 375f),
                PathNode.QuadTo(408.6f, 445f, 443.1f, 503.7f),
                PathNode.QuadTo(477.6f, 562.4f, 536.3f, 596.9f),
                PathNode.QuadTo(595f, 631.4f, 665f, 631.4f),
                PathNode.QuadTo(735f, 631.4f, 793.7f, 596.9f),
                PathNode.QuadTo(852.4f, 562.4f, 886.9f, 503.7f),
                PathNode.QuadTo(921.4f, 445f, 921.4f, 375f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _appSettingsSurfaceDemibold!!
    }

private var _appSettingsSurfaceDemibold: ImageVector? = null

