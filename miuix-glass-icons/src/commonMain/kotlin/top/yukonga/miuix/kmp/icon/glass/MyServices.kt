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

val MiuixIcons.Glass.MyServices: ImageVector
    get() = MiuixIcons.Glass.Regular.MyServices

val MiuixIcons.Glass.Light.MyServices: ImageVector
    get() {
        if (_myServicesSurfaceLight != null) return _myServicesSurfaceLight!!
        _myServicesSurfaceLight = buildHyperOSIcon(
            name = "MyServices.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(687.1f, 672.4f),
                PathNode.LineTo(519.7f, 504.4f),
                PathNode.QuadTo(513.2f, 498f, 505.5f, 498f),
                PathNode.QuadTo(497.8f, 498f, 490.8f, 504.4f),
                PathNode.LineTo(485.1f, 510.1f),
                PathNode.QuadTo(478.7f, 516.6f, 478.7f, 524.3f),
                PathNode.QuadTo(478.7f, 532f, 485.7f, 539f),
                PathNode.LineTo(629.3f, 683.2f),
                PathNode.QuadTo(570.1f, 736.2f, 497.7f, 749.4f),
                PathNode.QuadTo(425.3f, 762.6f, 356.8f, 740.6f),
                PathNode.QuadTo(288.3f, 718.7f, 240.3f, 670.7f),
                PathNode.QuadTo(185f, 615.3f, 165.9f, 541.7f),
                PathNode.QuadTo(146.8f, 468f, 165.9f, 394.8f),
                PathNode.QuadTo(185f, 321.7f, 240.3f, 266.3f),
                PathNode.LineTo(513.3f, -5.7f),
                PathNode.QuadTo(562.2f, -55.1f, 583.2f, -73.3f),
                PathNode.QuadTo(604.2f, -91.4f, 625f, -98.1f),
                PathNode.QuadTo(646.9f, -104.7f, 665f, -104.7f),
                PathNode.QuadTo(683.7f, -104.7f, 705f, -98.1f),
                PathNode.QuadTo(725.8f, -91.4f, 746.8f, -73.3f),
                PathNode.QuadTo(767.8f, -55.1f, 816.7f, -5.7f),
                PathNode.LineTo(1089.7f, 266.3f),
                PathNode.QuadTo(1145f, 321.7f, 1164.1f, 395.3f),
                PathNode.QuadTo(1183.2f, 469f, 1164.1f, 542.2f),
                PathNode.QuadTo(1145f, 615.3f, 1089.7f, 670.7f),
                PathNode.QuadTo(1036.3f, 723.6f, 964.2f, 744.4f),
                PathNode.QuadTo(892.1f, 765.3f, 818.4f, 747.7f),
                PathNode.QuadTo(744.7f, 730f, 687.1f, 672.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 992.2142f,
            autoMirror = false,
        )
        return _myServicesSurfaceLight!!
    }

private var _myServicesSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.MyServices: ImageVector
    get() {
        if (_myServicesSurfaceNormal != null) return _myServicesSurfaceNormal!!
        _myServicesSurfaceNormal = buildHyperOSIcon(
            name = "MyServices.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(679.5f, 677.6f),
                PathNode.LineTo(515.1f, 512.4f),
                PathNode.QuadTo(506.2f, 503.5f, 495.9f, 503.5f),
                PathNode.QuadTo(485.6f, 503.5f, 475.8f, 512.4f),
                PathNode.LineTo(472f, 516.2f),
                PathNode.QuadTo(463.1f, 525.1f, 463.1f, 535.4f),
                PathNode.QuadTo(463.1f, 545.7f, 472.8f, 555.5f),
                PathNode.LineTo(620.2f, 703.7f),
                PathNode.QuadTo(562.5f, 748.4f, 492.1f, 759.8f),
                PathNode.QuadTo(421.7f, 771.1f, 353.2f, 749.5f),
                PathNode.QuadTo(284.7f, 727.8f, 233.9f, 677.1f),
                PathNode.QuadTo(176.8f, 619.9f, 157f, 544f),
                PathNode.QuadTo(137.3f, 468f, 157f, 392.5f),
                PathNode.QuadTo(176.8f, 317.1f, 233.9f, 259.9f),
                PathNode.LineTo(506.9f, -12.1f),
                PathNode.QuadTo(555.2f, -61.2f, 577.6f, -80.3f),
                PathNode.QuadTo(599.9f, -99.4f, 622.3f, -107f),
                PathNode.QuadTo(646.3f, -113.8f, 665f, -113.8f),
                PathNode.QuadTo(684.6f, -113.8f, 707.7f, -107f),
                PathNode.QuadTo(730.1f, -99.4f, 752.4f, -80.3f),
                PathNode.QuadTo(774.8f, -61.2f, 823.1f, -12.1f),
                PathNode.LineTo(1096.1f, 259.9f),
                PathNode.QuadTo(1153.2f, 317.1f, 1173f, 393f),
                PathNode.QuadTo(1192.7f, 469f, 1173f, 544.5f),
                PathNode.QuadTo(1153.2f, 619.9f, 1096.1f, 677.1f),
                PathNode.QuadTo(1040.9f, 732.1f, 966.8f, 753.8f),
                PathNode.QuadTo(892.7f, 775.4f, 816.4f, 756.8f),
                PathNode.QuadTo(740.1f, 738.2f, 679.5f, 677.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 992.4581f,
            autoMirror = false,
        )
        return _myServicesSurfaceNormal!!
    }

private var _myServicesSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.MyServices: ImageVector
    get() {
        if (_myServicesSurfaceRegular != null) return _myServicesSurfaceRegular!!
        _myServicesSurfaceRegular = buildHyperOSIcon(
            name = "MyServices.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(676f, 680f),
                PathNode.LineTo(513f, 516f),
                PathNode.QuadTo(503f, 506f, 491.5f, 506f),
                PathNode.QuadTo(480f, 506f, 469f, 516f),
                PathNode.LineTo(466f, 519f),
                PathNode.QuadTo(456f, 529f, 456f, 540.5f),
                PathNode.QuadTo(456f, 552f, 467f, 563f),
                PathNode.LineTo(616f, 713f),
                PathNode.QuadTo(559f, 754f, 489.5f, 764.5f),
                PathNode.QuadTo(420f, 775f, 351.5f, 753.5f),
                PathNode.QuadTo(283f, 732f, 231f, 680f),
                PathNode.QuadTo(173f, 622f, 153f, 545f),
                PathNode.QuadTo(133f, 468f, 153f, 391.5f),
                PathNode.QuadTo(173f, 315f, 231f, 257f),
                PathNode.LineTo(504f, -15f),
                PathNode.QuadTo(552f, -64f, 575f, -83.5f),
                PathNode.QuadTo(598f, -103f, 621f, -111f),
                PathNode.QuadTo(646f, -118f, 665f, -118f),
                PathNode.QuadTo(685f, -118f, 709f, -111f),
                PathNode.QuadTo(732f, -103f, 755f, -83.5f),
                PathNode.QuadTo(778f, -64f, 826f, -15f),
                PathNode.LineTo(1099f, 257f),
                PathNode.QuadTo(1157f, 315f, 1177f, 392f),
                PathNode.QuadTo(1197f, 469f, 1177f, 545.5f),
                PathNode.QuadTo(1157f, 622f, 1099f, 680f),
                PathNode.QuadTo(1043f, 736f, 968f, 758f),
                PathNode.QuadTo(893f, 780f, 815.5f, 761f),
                PathNode.QuadTo(738f, 742f, 676f, 680f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 992.5691f,
            autoMirror = false,
        )
        return _myServicesSurfaceRegular!!
    }

private var _myServicesSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.MyServices: ImageVector
    get() {
        if (_myServicesSurfaceMedium != null) return _myServicesSurfaceMedium!!
        _myServicesSurfaceMedium = buildHyperOSIcon(
            name = "MyServices.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(688.2f, 684.1f),
                PathNode.LineTo(519.5f, 514.6f),
                PathNode.QuadTo(506.5f, 501.7f, 492.6f, 501.4f),
                PathNode.QuadTo(478.6f, 501.1f, 465.8f, 513.3f),
                PathNode.LineTo(463f, 516f),
                PathNode.QuadTo(451.1f, 527.9f, 451.7f, 541.9f),
                PathNode.QuadTo(452.2f, 555.8f, 465.6f, 569.2f),
                PathNode.LineTo(615.5f, 719.8f),
                PathNode.QuadTo(559.5f, 758.1f, 490.4f, 768.1f),
                PathNode.QuadTo(421.4f, 778.2f, 352.2f, 756.6f),
                PathNode.QuadTo(283f, 735f, 229.9f, 681.9f),
                PathNode.QuadTo(171.1f, 623.1f, 150.8f, 545f),
                PathNode.QuadTo(130.6f, 466.9f, 150.8f, 389.1f),
                PathNode.QuadTo(171.1f, 311.2f, 229.9f, 252.4f),
                PathNode.LineTo(500.2f, -16.9f),
                PathNode.QuadTo(547.9f, -65.6f, 571.5f, -85.5f),
                PathNode.QuadTo(595f, -105.4f, 619.1f, -113.7f),
                PathNode.QuadTo(645.5f, -121f, 665f, -121f),
                PathNode.QuadTo(685.3f, -121f, 710.9f, -113.7f),
                PathNode.QuadTo(735f, -105.4f, 758.5f, -85.5f),
                PathNode.QuadTo(782.1f, -65.6f, 829.8f, -16.9f),
                PathNode.LineTo(1100.1f, 252.4f),
                PathNode.QuadTo(1158.9f, 311.2f, 1179.2f, 389.4f),
                PathNode.QuadTo(1199.4f, 467.6f, 1179.2f, 545.4f),
                PathNode.QuadTo(1158.9f, 623.1f, 1100.1f, 681.9f),
                PathNode.QuadTo(1046.8f, 735.2f, 974.5f, 758f),
                PathNode.QuadTo(902.2f, 780.8f, 826.3f, 763.6f),
                PathNode.QuadTo(750.4f, 746.3f, 688.2f, 684.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 991.8602f,
            autoMirror = false,
        )
        return _myServicesSurfaceMedium!!
    }

private var _myServicesSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.MyServices: ImageVector
    get() {
        if (_myServicesSurfaceDemibold != null) return _myServicesSurfaceDemibold!!
        _myServicesSurfaceDemibold = buildHyperOSIcon(
            name = "MyServices.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(696.7f, 686.9f),
                PathNode.LineTo(524f, 513.7f),
                PathNode.QuadTo(509f, 498.6f, 493.3f, 498.2f),
                PathNode.QuadTo(477.7f, 497.7f, 463.5f, 511.4f),
                PathNode.LineTo(460.9f, 513.9f),
                PathNode.QuadTo(447.7f, 527.2f, 448.6f, 542.8f),
                PathNode.QuadTo(449.6f, 558.4f, 464.7f, 573.6f),
                PathNode.LineTo(615.1f, 724.5f),
                PathNode.QuadTo(559.9f, 760.9f, 491.1f, 770.7f),
                PathNode.QuadTo(422.3f, 780.5f, 352.6f, 758.8f),
                PathNode.QuadTo(283f, 737.1f, 229.2f, 683.2f),
                PathNode.QuadTo(169.8f, 623.8f, 149.3f, 545f),
                PathNode.QuadTo(128.9f, 466.2f, 149.3f, 387.4f),
                PathNode.QuadTo(169.8f, 308.6f, 229.2f, 249.2f),
                PathNode.LineTo(497.6f, -18.2f),
                PathNode.QuadTo(545.1f, -66.8f, 569f, -86.9f),
                PathNode.QuadTo(592.9f, -107.1f, 617.8f, -115.6f),
                PathNode.QuadTo(645.1f, -123.1f, 665f, -123.1f),
                PathNode.QuadTo(685.5f, -123.1f, 712.2f, -115.6f),
                PathNode.QuadTo(737.1f, -107.1f, 761f, -86.9f),
                PathNode.QuadTo(784.9f, -66.8f, 832.4f, -18.2f),
                PathNode.LineTo(1100.8f, 249.2f),
                PathNode.QuadTo(1160.2f, 308.6f, 1180.7f, 387.6f),
                PathNode.QuadTo(1201.1f, 466.7f, 1180.7f, 545.3f),
                PathNode.QuadTo(1160.2f, 623.8f, 1100.8f, 683.2f),
                PathNode.QuadTo(1049.4f, 734.6f, 979f, 758f),
                PathNode.QuadTo(908.6f, 781.4f, 833.9f, 765.4f),
                PathNode.QuadTo(759.1f, 749.4f, 696.7f, 686.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 992.0168f,
            autoMirror = false,
        )
        return _myServicesSurfaceDemibold!!
    }

private var _myServicesSurfaceDemibold: ImageVector? = null

