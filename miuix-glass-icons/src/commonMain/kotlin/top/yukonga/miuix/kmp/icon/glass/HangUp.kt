// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.HangUp: ImageVector
    get() = MiuixIcons.Glass.Regular.HangUp

val MiuixIcons.Glass.Light.HangUp: ImageVector
    get() {
        if (_hangUpSurfaceLight != null) return _hangUpSurfaceLight!!
        _hangUpSurfaceLight = buildHyperOSIcon(
            name = "HangUp.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(1259.2f, 362.6f),
                PathNode.QuadTo(1271.4f, 420.9f, 1238.6f, 465.7f),
                PathNode.QuadTo(1205.8f, 510.4f, 1150.7f, 538.7f),
                PathNode.QuadTo(1043.2f, 592.2f, 916.3f, 619.4f),
                PathNode.QuadTo(789.3f, 646.7f, 665.9f, 647.6f),
                PathNode.QuadTo(538.4f, 647f, 412f, 620.1f),
                PathNode.QuadTo(285.6f, 593.1f, 178.1f, 538.2f),
                PathNode.QuadTo(117.4f, 508.4f, 89.8f, 464.7f),
                PathNode.QuadTo(62.1f, 420.9f, 71.2f, 360.8f),
                PathNode.QuadTo(76.2f, 332.7f, 85.1f, 299.2f),
                PathNode.QuadTo(87.1f, 293.1f, 88.7f, 286.6f),
                PathNode.QuadTo(96.8f, 260.9f, 119.2f, 249.8f),
                PathNode.QuadTo(141.6f, 238.8f, 164.1f, 244.9f),
                PathNode.LineTo(366.9f, 298.2f),
                PathNode.QuadTo(382.9f, 302.4f, 393.4f, 314.5f),
                PathNode.QuadTo(404f, 326.6f, 409.1f, 342.3f),
                PathNode.LineTo(432f, 414.7f),
                PathNode.QuadTo(439.9f, 444.7f, 463.5f, 465.9f),
                PathNode.QuadTo(487.1f, 487.1f, 518.6f, 494.4f),
                PathNode.QuadTo(602f, 513.8f, 665f, 514.8f),
                PathNode.QuadTo(736f, 512.9f, 811.2f, 494.4f),
                PathNode.QuadTo(841.8f, 487.6f, 864.6f, 466.2f),
                PathNode.QuadTo(887.4f, 444.8f, 896.3f, 414.8f),
                PathNode.LineTo(918.3f, 341.4f),
                PathNode.QuadTo(923.4f, 326.1f, 934.9f, 313.6f),
                PathNode.QuadTo(946.4f, 301f, 961.9f, 297.3f),
                PathNode.LineTo(1161.8f, 244f),
                PathNode.QuadTo(1186.4f, 237.3f, 1207.9f, 247.8f),
                PathNode.QuadTo(1229.4f, 258.3f, 1238.8f, 283.1f),
                PathNode.QuadTo(1248.8f, 314.2f, 1259.2f, 362.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 0.9152f,
            translationY = 1111.1499f,
            autoMirror = false,
        )
        return _hangUpSurfaceLight!!
    }

private var _hangUpSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.HangUp: ImageVector
    get() {
        if (_hangUpSurfaceNormal != null) return _hangUpSurfaceNormal!!
        _hangUpSurfaceNormal = buildHyperOSIcon(
            name = "HangUp.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(1260.4f, 362.9f),
                PathNode.QuadTo(1273.9f, 425.8f, 1240.6f, 469.3f),
                PathNode.QuadTo(1207.3f, 512.9f, 1148.8f, 542.3f),
                PathNode.QuadTo(1041.7f, 596.2f, 915.1f, 623.3f),
                PathNode.QuadTo(788.4f, 650.3f, 665.3f, 650.6f),
                PathNode.QuadTo(538.1f, 649.7f, 412f, 623.1f),
                PathNode.QuadTo(285.9f, 596.5f, 178.7f, 542.2f),
                PathNode.QuadTo(111.6f, 508.1f, 85.8f, 465.6f),
                PathNode.QuadTo(60f, 423f, 69.7f, 359.6f),
                PathNode.QuadTo(74.7f, 330.8f, 83f, 303.2f),
                PathNode.QuadTo(85f, 296.5f, 86.8f, 289.6f),
                PathNode.QuadTo(95.6f, 260.3f, 121.5f, 247.5f),
                PathNode.QuadTo(147.4f, 234.8f, 173f, 241.5f),
                PathNode.LineTo(371.8f, 293.9f),
                PathNode.QuadTo(390.5f, 299.4f, 402.8f, 313.1f),
                PathNode.QuadTo(415f, 326.9f, 420.7f, 344.2f),
                PathNode.LineTo(443f, 415.6f),
                PathNode.QuadTo(450.3f, 442.8f, 471.7f, 462.5f),
                PathNode.QuadTo(493.2f, 482.2f, 521.6f, 488.6f),
                PathNode.QuadTo(599.3f, 507.1f, 665f, 508.1f),
                PathNode.QuadTo(730.5f, 506.8f, 806.9f, 488.6f),
                PathNode.QuadTo(835.1f, 482.4f, 855.6f, 463f),
                PathNode.QuadTo(876.1f, 443.6f, 884.4f, 416.3f),
                PathNode.LineTo(906.4f, 343.9f),
                PathNode.QuadTo(912.1f, 326.7f, 925f, 312.5f),
                PathNode.QuadTo(937.9f, 298.3f, 955.8f, 293.7f),
                PathNode.LineTo(1152.3f, 241.3f),
                PathNode.QuadTo(1180.6f, 233.7f, 1204.9f, 245.5f),
                PathNode.QuadTo(1229.1f, 257.4f, 1240.3f, 286.5f),
                PathNode.QuadTo(1250.3f, 315.4f, 1260.4f, 362.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 0.9926f,
            translationY = 1111.1195f,
            autoMirror = false,
        )
        return _hangUpSurfaceNormal!!
    }

private var _hangUpSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.HangUp: ImageVector
    get() {
        if (_hangUpSurfaceRegular != null) return _hangUpSurfaceRegular!!
        _hangUpSurfaceRegular = buildHyperOSIcon(
            name = "HangUp.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(1261f, 363f),
                PathNode.QuadTo(1275f, 428f, 1241.5f, 471f),
                PathNode.QuadTo(1208f, 514f, 1148f, 544f),
                PathNode.QuadTo(1041f, 598f, 914.5f, 625f),
                PathNode.QuadTo(788f, 652f, 665f, 652f),
                PathNode.QuadTo(538f, 651f, 412f, 624.5f),
                PathNode.QuadTo(286f, 598f, 179f, 544f),
                PathNode.QuadTo(109f, 508f, 84f, 466f),
                PathNode.QuadTo(59f, 424f, 69f, 359f),
                PathNode.QuadTo(74f, 330f, 82f, 305f),
                PathNode.QuadTo(84f, 298f, 86f, 291f),
                PathNode.QuadTo(95f, 260f, 122.5f, 246.5f),
                PathNode.QuadTo(150f, 233f, 177f, 240f),
                PathNode.LineTo(374f, 292f),
                PathNode.QuadTo(394f, 298f, 407f, 312.5f),
                PathNode.QuadTo(420f, 327f, 426f, 345f),
                PathNode.LineTo(448f, 416f),
                PathNode.QuadTo(455f, 442f, 475.5f, 461f),
                PathNode.QuadTo(496f, 480f, 523f, 486f),
                PathNode.QuadTo(598f, 504f, 665f, 505f),
                PathNode.QuadTo(728f, 504f, 805f, 486f),
                PathNode.QuadTo(832f, 480f, 851.5f, 461.5f),
                PathNode.QuadTo(871f, 443f, 879f, 417f),
                PathNode.LineTo(901f, 345f),
                PathNode.QuadTo(907f, 327f, 920.5f, 312f),
                PathNode.QuadTo(934f, 297f, 953f, 292f),
                PathNode.LineTo(1148f, 240f),
                PathNode.QuadTo(1178f, 232f, 1203.5f, 244.5f),
                PathNode.QuadTo(1229f, 257f, 1241f, 288f),
                PathNode.QuadTo(1251f, 316f, 1261f, 363f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.0321f,
            translationY = 1111.1057f,
            autoMirror = false,
        )
        return _hangUpSurfaceRegular!!
    }

private var _hangUpSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.HangUp: ImageVector
    get() {
        if (_hangUpSurfaceMedium != null) return _hangUpSurfaceMedium!!
        _hangUpSurfaceMedium = buildHyperOSIcon(
            name = "HangUp.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(1262.6f, 357.1f),
                PathNode.QuadTo(1278.8f, 427.2f, 1246.6f, 471.4f),
                PathNode.QuadTo(1214.5f, 515.6f, 1152.6f, 547f),
                PathNode.QuadTo(1045.9f, 601.2f, 917.3f, 629.2f),
                PathNode.QuadTo(788.8f, 657.1f, 666.1f, 657.7f),
                PathNode.QuadTo(540.4f, 657.5f, 411.9f, 630.6f),
                PathNode.QuadTo(283.3f, 603.7f, 176f, 550.2f),
                PathNode.QuadTo(107.4f, 515.3f, 81f, 471.7f),
                PathNode.QuadTo(54.7f, 428.1f, 66.8f, 357.9f),
                PathNode.QuadTo(72.9f, 325.7f, 80.4f, 302.3f),
                PathNode.QuadTo(82.1f, 295.6f, 84.6f, 287.2f),
                PathNode.QuadTo(93.6f, 255.7f, 120.3f, 242.4f),
                PathNode.QuadTo(147f, 229.2f, 175.6f, 236.8f),
                PathNode.LineTo(381f, 289.8f),
                PathNode.QuadTo(402.1f, 295.6f, 415.5f, 309.9f),
                PathNode.QuadTo(428.9f, 324.3f, 435.2f, 343.9f),
                PathNode.LineTo(458.3f, 417.4f),
                PathNode.QuadTo(464.7f, 440.9f, 483.3f, 457.9f),
                PathNode.QuadTo(501.9f, 474.9f, 526.8f, 480.3f),
                PathNode.QuadTo(604.5f, 498.3f, 665.3f, 498.8f),
                PathNode.QuadTo(727.7f, 497.5f, 802f, 480.1f),
                PathNode.QuadTo(826.6f, 474.6f, 844.3f, 457.6f),
                PathNode.QuadTo(862.1f, 440.6f, 869.5f, 416.7f),
                PathNode.LineTo(892.1f, 342.3f),
                PathNode.QuadTo(898.4f, 322.7f, 912f, 308.1f),
                PathNode.QuadTo(925.6f, 293.5f, 946f, 287.9f),
                PathNode.LineTo(1150.4f, 233.5f),
                PathNode.QuadTo(1181f, 225f, 1205.5f, 237.9f),
                PathNode.QuadTo(1230.1f, 250.8f, 1242.4f, 282.9f),
                PathNode.QuadTo(1251.5f, 308.4f, 1262.6f, 357.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.1538f,
            translationY = 1110.5607f,
            autoMirror = false,
        )
        return _hangUpSurfaceMedium!!
    }

private var _hangUpSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.HangUp: ImageVector
    get() {
        if (_hangUpSurfaceDemibold != null) return _hangUpSurfaceDemibold!!
        _hangUpSurfaceDemibold = buildHyperOSIcon(
            name = "HangUp.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(1263.8f, 352.9f),
                PathNode.QuadTo(1281.4f, 426.6f, 1250.2f, 471.7f),
                PathNode.QuadTo(1219f, 516.8f, 1155.8f, 549.1f),
                PathNode.QuadTo(1049.3f, 603.5f, 919.3f, 632.1f),
                PathNode.QuadTo(789.4f, 660.7f, 666.8f, 661.6f),
                PathNode.QuadTo(542.1f, 662f, 411.8f, 634.8f),
                PathNode.QuadTo(281.4f, 607.6f, 173.9f, 554.6f),
                PathNode.QuadTo(106.2f, 520.4f, 78.9f, 475.6f),
                PathNode.QuadTo(51.6f, 430.9f, 65.3f, 357.2f),
                PathNode.QuadTo(72.2f, 322.6f, 79.2f, 300.4f),
                PathNode.QuadTo(80.8f, 293.9f, 83.7f, 284.6f),
                PathNode.QuadTo(92.7f, 252.6f, 118.8f, 239.6f),
                PathNode.QuadTo(144.9f, 226.6f, 174.7f, 234.5f),
                PathNode.LineTo(385.9f, 288.3f),
                PathNode.QuadTo(407.8f, 293.9f, 421.5f, 308.1f),
                PathNode.QuadTo(435.2f, 322.4f, 441.6f, 343.2f),
                PathNode.LineTo(465.5f, 418.3f),
                PathNode.QuadTo(471.5f, 440.2f, 488.8f, 455.7f),
                PathNode.QuadTo(506.1f, 471.3f, 529.4f, 476.4f),
                PathNode.QuadTo(609f, 494.4f, 665.5f, 494.4f),
                PathNode.QuadTo(727.5f, 493f, 799.9f, 475.9f),
                PathNode.QuadTo(822.8f, 470.8f, 839.3f, 454.8f),
                PathNode.QuadTo(855.8f, 438.9f, 862.9f, 416.5f),
                PathNode.LineTo(885.8f, 340.4f),
                PathNode.QuadTo(892.3f, 319.6f, 906f, 305.3f),
                PathNode.QuadTo(919.8f, 291f, 941.1f, 285.1f),
                PathNode.LineTo(1152.1f, 229f),
                PathNode.QuadTo(1183.1f, 220.1f, 1206.9f, 233.2f),
                PathNode.QuadTo(1230.8f, 246.4f, 1243.3f, 279.3f),
                PathNode.QuadTo(1251.9f, 303.1f, 1263.8f, 352.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.2124f,
            translationY = 1110.181f,
            autoMirror = false,
        )
        return _hangUpSurfaceDemibold!!
    }

private var _hangUpSurfaceDemibold: ImageVector? = null

