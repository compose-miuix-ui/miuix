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

val MiuixIcons.Glass.ExpandMore: ImageVector
    get() = MiuixIcons.Glass.Regular.ExpandMore

val MiuixIcons.Glass.Light.ExpandMore: ImageVector
    get() {
        if (_expandMoreLineLight != null) return _expandMoreLineLight!!
        _expandMoreLineLight = buildHyperOSIcon(
            name = "ExpandMore.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(337.1f, 271.4f),
                PathNode.LineTo(323.6f, 256.9f),
                PathNode.QuadTo(317.1f, 250f, 317.4f, 242.8f),
                PathNode.QuadTo(317.7f, 235.6f, 324.1f, 229.1f),
                PathNode.LineTo(635.9f, -82.7f),
                PathNode.QuadTo(650f, -96.8f, 664.6f, -96.8f),
                PathNode.QuadTo(679.1f, -96.8f, 693.2f, -82.7f),
                PathNode.LineTo(1006f, 230.1f),
                PathNode.QuadTo(1012.9f, 237f, 1012.7f, 243.9f),
                PathNode.QuadTo(1012.4f, 250.9f, 1005.4f, 257.9f),
                PathNode.LineTo(990.9f, 271.4f),
                PathNode.QuadTo(984.4f, 277.9f, 977f, 277.6f),
                PathNode.QuadTo(969.6f, 277.3f, 963.1f, 270.9f),
                PathNode.LineTo(677.3f, -14.9f),
                PathNode.QuadTo(671f, -21.2f, 664.8f, -21.5f),
                PathNode.QuadTo(658.7f, -21.8f, 651.8f, -14.9f),
                PathNode.LineTo(364.9f, 272f),
                PathNode.QuadTo(358.4f, 278.4f, 351f, 278.7f),
                PathNode.QuadTo(343.6f, 278.9f, 337.1f, 271.4f),
                PathNode.Close,
                PathNode.MoveTo(323.6f, 493.1f),
                PathNode.LineTo(337.1f, 478.6f),
                PathNode.QuadTo(343.6f, 471.1f, 351f, 471.3f),
                PathNode.QuadTo(358.4f, 471.6f, 364.9f, 478f),
                PathNode.LineTo(651.8f, 764.9f),
                PathNode.QuadTo(658.7f, 771.8f, 664.8f, 771.5f),
                PathNode.QuadTo(671f, 771.2f, 677.3f, 764.9f),
                PathNode.LineTo(963.1f, 479.1f),
                PathNode.QuadTo(969.6f, 472.7f, 977f, 472.4f),
                PathNode.QuadTo(984.4f, 472.1f, 990.9f, 478.6f),
                PathNode.LineTo(1005.4f, 492.1f),
                PathNode.QuadTo(1012.4f, 499.1f, 1012.7f, 506.1f),
                PathNode.QuadTo(1012.9f, 513f, 1006f, 519.9f),
                PathNode.LineTo(693.2f, 832.7f),
                PathNode.QuadTo(679.1f, 846.8f, 664.6f, 846.8f),
                PathNode.QuadTo(650f, 846.8f, 635.9f, 832.7f),
                PathNode.LineTo(324.1f, 520.9f),
                PathNode.QuadTo(317.7f, 514.4f, 317.4f, 507.2f),
                PathNode.QuadTo(317.1f, 500f, 323.6f, 493.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6411f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _expandMoreLineLight!!
    }

private var _expandMoreLineLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ExpandMore: ImageVector
    get() {
        if (_expandMoreLineNormal != null) return _expandMoreLineNormal!!
        _expandMoreLineNormal = buildHyperOSIcon(
            name = "ExpandMore.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(332.2f, 279.4f),
                PathNode.LineTo(315.6f, 261.8f),
                PathNode.QuadTo(306.7f, 252.7f, 307.2f, 242.9f),
                PathNode.QuadTo(307.6f, 233.1f, 316.5f, 224.2f),
                PathNode.LineTo(629.8f, -89.1f),
                PathNode.QuadTo(647.3f, -106.6f, 664.9f, -106.6f),
                PathNode.QuadTo(682.5f, -106.6f, 699.9f, -89.1f),
                PathNode.LineTo(1014.2f, 225.2f),
                PathNode.QuadTo(1023.3f, 234.3f, 1023.2f, 243.6f),
                PathNode.QuadTo(1023.1f, 253f, 1013.4f, 262.8f),
                PathNode.LineTo(995.8f, 279.4f),
                PathNode.QuadTo(986.9f, 288.3f, 977f, 287.8f),
                PathNode.QuadTo(967.1f, 287.4f, 958.2f, 278.5f),
                PathNode.LineTo(681.9f, 2.2f),
                PathNode.QuadTo(673.7f, -5.9f, 665.3f, -6.4f),
                PathNode.QuadTo(656.8f, -6.8f, 647.8f, 2.2f),
                PathNode.LineTo(369.8f, 280.2f),
                PathNode.QuadTo(360.9f, 289.1f, 351f, 289.2f),
                PathNode.QuadTo(341.1f, 289.3f, 332.2f, 279.4f),
                PathNode.Close,
                PathNode.MoveTo(315.6f, 488.2f),
                PathNode.LineTo(332.2f, 470.6f),
                PathNode.QuadTo(341.1f, 460.7f, 351f, 460.8f),
                PathNode.QuadTo(360.9f, 460.9f, 369.8f, 469.8f),
                PathNode.LineTo(647.8f, 747.8f),
                PathNode.QuadTo(656.8f, 756.8f, 665.3f, 756.4f),
                PathNode.QuadTo(673.7f, 755.9f, 681.9f, 747.8f),
                PathNode.LineTo(958.2f, 471.5f),
                PathNode.QuadTo(967.1f, 462.6f, 977f, 462.2f),
                PathNode.QuadTo(986.9f, 461.7f, 995.8f, 470.6f),
                PathNode.LineTo(1013.4f, 487.2f),
                PathNode.QuadTo(1023.1f, 497f, 1023.2f, 506.4f),
                PathNode.QuadTo(1023.3f, 515.7f, 1014.2f, 524.8f),
                PathNode.LineTo(699.9f, 839.1f),
                PathNode.QuadTo(682.5f, 856.6f, 664.9f, 856.6f),
                PathNode.QuadTo(647.3f, 856.6f, 629.8f, 839.1f),
                PathNode.LineTo(316.5f, 525.8f),
                PathNode.QuadTo(307.6f, 516.9f, 307.2f, 507.1f),
                PathNode.QuadTo(306.7f, 497.3f, 315.6f, 488.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.4958f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _expandMoreLineNormal!!
    }

private var _expandMoreLineNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ExpandMore: ImageVector
    get() {
        if (_expandMoreLineRegular != null) return _expandMoreLineRegular!!
        _expandMoreLineRegular = buildHyperOSIcon(
            name = "ExpandMore.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(330f, 283f),
                PathNode.LineTo(312f, 264f),
                PathNode.QuadTo(302f, 254f, 302.5f, 243f),
                PathNode.QuadTo(303f, 232f, 313f, 222f),
                PathNode.LineTo(627f, -92f),
                PathNode.QuadTo(646f, -111f, 665f, -111f),
                PathNode.QuadTo(684f, -111f, 703f, -92f),
                PathNode.LineTo(1018f, 223f),
                PathNode.QuadTo(1028f, 233f, 1028f, 243.5f),
                PathNode.QuadTo(1028f, 254f, 1017f, 265f),
                PathNode.LineTo(998f, 283f),
                PathNode.QuadTo(988f, 293f, 977f, 292.5f),
                PathNode.QuadTo(966f, 292f, 956f, 282f),
                PathNode.LineTo(684f, 10f),
                PathNode.QuadTo(675f, 1f, 665.5f, 0.5f),
                PathNode.QuadTo(656f, 0f, 646f, 10f),
                PathNode.LineTo(372f, 284f),
                PathNode.QuadTo(362f, 294f, 351f, 294f),
                PathNode.QuadTo(340f, 294f, 330f, 283f),
                PathNode.Close,
                PathNode.MoveTo(312f, 486f),
                PathNode.LineTo(330f, 467f),
                PathNode.QuadTo(340f, 456f, 351f, 456f),
                PathNode.QuadTo(362f, 456f, 372f, 466f),
                PathNode.LineTo(646f, 740f),
                PathNode.QuadTo(656f, 750f, 665.5f, 749.5f),
                PathNode.QuadTo(675f, 749f, 684f, 740f),
                PathNode.LineTo(956f, 468f),
                PathNode.QuadTo(966f, 458f, 977f, 457.5f),
                PathNode.QuadTo(988f, 457f, 998f, 467f),
                PathNode.LineTo(1017f, 485f),
                PathNode.QuadTo(1028f, 496f, 1028f, 506.5f),
                PathNode.QuadTo(1028f, 517f, 1018f, 527f),
                PathNode.LineTo(703f, 842f),
                PathNode.QuadTo(684f, 861f, 665f, 861f),
                PathNode.QuadTo(646f, 861f, 627f, 842f),
                PathNode.LineTo(313f, 528f),
                PathNode.QuadTo(303f, 518f, 302.5f, 507f),
                PathNode.QuadTo(302f, 496f, 312f, 486f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.4286f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _expandMoreLineRegular!!
    }

private var _expandMoreLineRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ExpandMore: ImageVector
    get() {
        if (_expandMoreLineMedium != null) return _expandMoreLineMedium!!
        _expandMoreLineMedium = buildHyperOSIcon(
            name = "ExpandMore.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(327.8f, 292.7f),
                PathNode.LineTo(302.5f, 266.2f),
                PathNode.QuadTo(291.7f, 255.1f, 292.2f, 243f),
                PathNode.QuadTo(292.7f, 230.9f, 303.5f, 220.1f),
                PathNode.LineTo(625.9f, -102.3f),
                PathNode.QuadTo(645.2f, -121.5f, 665f, -121.5f),
                PathNode.QuadTo(684.8f, -121.5f, 704.1f, -102.3f),
                PathNode.LineTo(1027.5f, 221.1f),
                PathNode.QuadTo(1038.5f, 232.2f, 1038.4f, 243.9f),
                PathNode.QuadTo(1038.3f, 255.6f, 1026.5f, 267.2f),
                PathNode.LineTo(999.9f, 292.2f),
                PathNode.QuadTo(988.8f, 303f, 976.6f, 302.5f),
                PathNode.QuadTo(964.4f, 302f, 953.6f, 291.2f),
                PathNode.LineTo(686.2f, 23.8f),
                PathNode.QuadTo(676.1f, 13.7f, 665.5f, 13.3f),
                PathNode.QuadTo(654.9f, 13f, 643.8f, 23.8f),
                PathNode.LineTo(374.2f, 293.5f),
                PathNode.QuadTo(363.1f, 304.5f, 350.9f, 304.5f),
                PathNode.QuadTo(338.6f, 304.5f, 327.8f, 292.7f),
                PathNode.Close,
                PathNode.MoveTo(302.5f, 483.8f),
                PathNode.LineTo(327.8f, 457.3f),
                PathNode.QuadTo(338.6f, 445.5f, 350.9f, 445.5f),
                PathNode.QuadTo(363.1f, 445.5f, 374.2f, 456.5f),
                PathNode.LineTo(643.8f, 726.2f),
                PathNode.QuadTo(654.9f, 737.3f, 665.5f, 736.9f),
                PathNode.QuadTo(676.1f, 736.6f, 686.2f, 726.2f),
                PathNode.LineTo(953.6f, 458.8f),
                PathNode.QuadTo(964.4f, 448f, 976.6f, 447.5f),
                PathNode.QuadTo(988.8f, 447f, 999.9f, 457.8f),
                PathNode.LineTo(1026.5f, 482.8f),
                PathNode.QuadTo(1038.3f, 494.4f, 1038.4f, 506.1f),
                PathNode.QuadTo(1038.5f, 517.8f, 1027.5f, 528.9f),
                PathNode.LineTo(704.1f, 852.3f),
                PathNode.QuadTo(684.8f, 871.5f, 665f, 871.5f),
                PathNode.QuadTo(645.2f, 871.5f, 625.9f, 852.3f),
                PathNode.LineTo(303.5f, 529.9f),
                PathNode.QuadTo(292.7f, 519.1f, 292.2f, 507f),
                PathNode.QuadTo(291.7f, 494.9f, 302.5f, 483.8f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.3593f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _expandMoreLineMedium!!
    }

private var _expandMoreLineMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ExpandMore: ImageVector
    get() {
        if (_expandMoreLineDemibold != null) return _expandMoreLineDemibold!!
        _expandMoreLineDemibold = buildHyperOSIcon(
            name = "ExpandMore.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(326.3f, 299.5f),
                PathNode.LineTo(295.9f, 267.7f),
                PathNode.QuadTo(284.5f, 255.8f, 285f, 243f),
                PathNode.QuadTo(285.5f, 230.2f, 296.9f, 218.8f),
                PathNode.LineTo(625.2f, -109.5f),
                PathNode.QuadTo(644.6f, -128.9f, 665f, -128.9f),
                PathNode.QuadTo(685.4f, -128.9f, 704.8f, -109.5f),
                PathNode.LineTo(1034.1f, 219.8f),
                PathNode.QuadTo(1045.9f, 231.6f, 1045.7f, 244.2f),
                PathNode.QuadTo(1045.5f, 256.8f, 1033.1f, 268.7f),
                PathNode.LineTo(1001.2f, 298.6f),
                PathNode.QuadTo(989.4f, 310f, 976.3f, 309.5f),
                PathNode.QuadTo(963.2f, 309f, 951.9f, 297.6f),
                PathNode.LineTo(687.7f, 33.4f),
                PathNode.QuadTo(676.8f, 22.6f, 665.5f, 22.3f),
                PathNode.QuadTo(654.2f, 22.1f, 642.3f, 33.4f),
                PathNode.LineTo(375.7f, 300.1f),
                PathNode.QuadTo(363.8f, 311.9f, 350.8f, 311.9f),
                PathNode.QuadTo(337.7f, 311.9f, 326.3f, 299.5f),
                PathNode.Close,
                PathNode.MoveTo(295.9f, 482.3f),
                PathNode.LineTo(326.3f, 450.5f),
                PathNode.QuadTo(337.7f, 438.1f, 350.8f, 438.1f),
                PathNode.QuadTo(363.8f, 438.1f, 375.7f, 449.9f),
                PathNode.LineTo(642.3f, 716.6f),
                PathNode.QuadTo(654.2f, 728.4f, 665.5f, 728.1f),
                PathNode.QuadTo(676.8f, 727.9f, 687.7f, 716.6f),
                PathNode.LineTo(951.9f, 452.4f),
                PathNode.QuadTo(963.2f, 441f, 976.3f, 440.5f),
                PathNode.QuadTo(989.4f, 440f, 1001.2f, 451.4f),
                PathNode.LineTo(1033.1f, 481.3f),
                PathNode.QuadTo(1045.5f, 493.2f, 1045.7f, 505.8f),
                PathNode.QuadTo(1045.9f, 518.4f, 1034.1f, 530.2f),
                PathNode.LineTo(704.8f, 859.5f),
                PathNode.QuadTo(685.4f, 878.9f, 665f, 878.9f),
                PathNode.QuadTo(644.6f, 878.9f, 625.2f, 859.5f),
                PathNode.LineTo(296.9f, 531.2f),
                PathNode.QuadTo(285.5f, 519.8f, 285f, 507f),
                PathNode.QuadTo(284.5f, 494.2f, 295.9f, 482.3f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.3101f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _expandMoreLineDemibold!!
    }

private var _expandMoreLineDemibold: ImageVector? = null

