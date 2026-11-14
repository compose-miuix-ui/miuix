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

val MiuixIcons.Glass.SecurityStatus: ImageVector
    get() = MiuixIcons.Glass.Regular.SecurityStatus

val MiuixIcons.Glass.Light.SecurityStatus: ImageVector
    get() {
        if (_securityStatusSurfaceLight != null) return _securityStatusSurfaceLight!!
        _securityStatusSurfaceLight = buildHyperOSIcon(
            name = "SecurityStatus.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(629.1f, 871f),
                PathNode.LineTo(361.1f, 754f),
                PathNode.QuadTo(316.6f, 734.4f, 298.2f, 724.7f),
                PathNode.QuadTo(279.9f, 714.9f, 267.8f, 703.8f),
                PathNode.QuadTo(244.6f, 680.6f, 234f, 649.3f),
                PathNode.QuadTo(229.4f, 634.7f, 228.9f, 615.6f),
                PathNode.QuadTo(228.4f, 596.6f, 229.4f, 560.8f),
                PathNode.QuadTo(230.4f, 555.3f, 230.4f, 544.4f),
                PathNode.QuadTo(241.4f, 352.3f, 249.4f, 280.9f),
                PathNode.QuadTo(256f, 213.7f, 302.8f, 142f),
                PathNode.QuadTo(349.7f, 70.3f, 434.3f, 2.2f),
                PathNode.QuadTo(518.9f, -65.9f, 635.6f, -122f),
                PathNode.QuadTo(664.6f, -135.8f, 693.6f, -122f),
                PathNode.QuadTo(810.2f, -65.9f, 895.3f, 2.2f),
                PathNode.QuadTo(980.4f, 70.3f, 1027.6f, 142f),
                PathNode.QuadTo(1074.7f, 213.7f, 1081.7f, 280.9f),
                PathNode.QuadTo(1088.7f, 352.8f, 1099.7f, 544.4f),
                PathNode.QuadTo(1102.7f, 591f, 1102.2f, 612.1f),
                PathNode.QuadTo(1101.7f, 633.2f, 1096.1f, 649.3f),
                PathNode.QuadTo(1085f, 682.6f, 1062.3f, 703.8f),
                PathNode.QuadTo(1051.8f, 714.3f, 1036.4f, 722.4f),
                PathNode.QuadTo(1021.1f, 730.4f, 982f, 749f),
                PathNode.QuadTo(978f, 750f, 970f, 754f),
                PathNode.LineTo(702f, 871f),
                PathNode.QuadTo(682.8f, 879.6f, 665.6f, 879.6f),
                PathNode.QuadTo(648.3f, 879.6f, 629.1f, 871f),
                PathNode.Close,
                PathNode.MoveTo(885.2f, 548.9f),
                PathNode.LineTo(894.9f, 538.2f),
                PathNode.QuadTo(900.2f, 532.4f, 900.2f, 526.1f),
                PathNode.QuadTo(900.2f, 519.7f, 894.9f, 514.3f),
                PathNode.LineTo(635.2f, 253.7f),
                PathNode.QuadTo(629.9f, 247.3f, 623f, 247.8f),
                PathNode.QuadTo(616.1f, 248.3f, 610.8f, 253.7f),
                PathNode.LineTo(471.1f, 393.3f),
                PathNode.QuadTo(465.8f, 398.7f, 465.8f, 405.1f),
                PathNode.QuadTo(465.8f, 411.4f, 471.1f, 417.2f),
                PathNode.LineTo(480.8f, 427.9f),
                PathNode.QuadTo(486.1f, 434.2f, 492.8f, 433.9f),
                PathNode.QuadTo(499.4f, 433.7f, 505.2f, 427.9f),
                PathNode.LineTo(623f, 310.6f),
                PathNode.LineTo(860.8f, 548.9f),
                PathNode.QuadTo(866.1f, 554.2f, 873f, 554.7f),
                PathNode.QuadTo(879.9f, 555.2f, 885.2f, 548.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.1587f,
            translationY = 1042f,
            autoMirror = false,
        )
        return _securityStatusSurfaceLight!!
    }

private var _securityStatusSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.SecurityStatus: ImageVector
    get() {
        if (_securityStatusSurfaceNormal != null) return _securityStatusSurfaceNormal!!
        _securityStatusSurfaceNormal = buildHyperOSIcon(
            name = "SecurityStatus.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(627f, 876.5f),
                PathNode.LineTo(359f, 759.5f),
                PathNode.QuadTo(314.1f, 739.6f, 295.3f, 729.7f),
                PathNode.QuadTo(276.5f, 719.8f, 263.8f, 708.1f),
                PathNode.QuadTo(239.4f, 683.6f, 228.5f, 651.2f),
                PathNode.QuadTo(223.6f, 635.6f, 223.1f, 617.6f),
                PathNode.QuadTo(222.6f, 599.6f, 223.6f, 559.6f),
                PathNode.QuadTo(224.6f, 554.4f, 224.6f, 544.1f),
                PathNode.QuadTo(235.6f, 351.4f, 243.6f, 280.3f),
                PathNode.QuadTo(250.5f, 211.8f, 297.8f, 139.3f),
                PathNode.QuadTo(345.1f, 66.7f, 430.3f, -2.1f),
                PathNode.QuadTo(515.5f, -70.8f, 633.1f, -127.5f),
                PathNode.QuadTo(664.9f, -142.8f, 696.6f, -127.5f),
                PathNode.QuadTo(814.2f, -70.8f, 899.9f, -2.1f),
                PathNode.QuadTo(985.6f, 66.7f, 1033.4f, 139.3f),
                PathNode.QuadTo(1081.1f, 211.8f, 1088.1f, 280.3f),
                PathNode.QuadTo(1095.1f, 351.6f, 1106.1f, 544.1f),
                PathNode.QuadTo(1109.1f, 591f, 1108.6f, 612.7f),
                PathNode.QuadTo(1108.1f, 634.4f, 1102.2f, 651.2f),
                PathNode.QuadTo(1090.5f, 685.6f, 1066.9f, 708.1f),
                PathNode.QuadTo(1056.1f, 718.9f, 1040.3f, 727.3f),
                PathNode.QuadTo(1024.5f, 735.6f, 984.7f, 754.5f),
                PathNode.QuadTo(980.7f, 755.5f, 972.7f, 759.5f),
                PathNode.LineTo(704.7f, 876.5f),
                PathNode.QuadTo(684.3f, 885.4f, 665.9f, 885.4f),
                PathNode.QuadTo(647.4f, 885.4f, 627f, 876.5f),
                PathNode.Close,
                PathNode.MoveTo(889.2f, 553.8f),
                PathNode.LineTo(899.8f, 542.2f),
                PathNode.QuadTo(906.9f, 534.9f, 906.9f, 526.4f),
                PathNode.QuadTo(906.9f, 517.8f, 899.8f, 510.7f),
                PathNode.LineTo(639.2f, 249.1f),
                PathNode.QuadTo(632f, 240.9f, 623f, 241.4f),
                PathNode.QuadTo(614f, 241.9f, 606.8f, 249.1f),
                PathNode.LineTo(466.2f, 389.7f),
                PathNode.QuadTo(459.1f, 396.8f, 459.1f, 405.4f),
                PathNode.QuadTo(459.1f, 413.9f, 466.2f, 421.2f),
                PathNode.LineTo(476.8f, 432.8f),
                PathNode.QuadTo(484f, 440.9f, 492.9f, 440.5f),
                PathNode.QuadTo(501.9f, 440.1f, 509.2f, 432.8f),
                PathNode.LineTo(623f, 319.1f),
                PathNode.LineTo(856.8f, 553.8f),
                PathNode.QuadTo(864f, 560.9f, 873f, 561.4f),
                PathNode.QuadTo(882f, 561.9f, 889.2f, 553.8f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 0.8532f,
            translationY = 1041.7708f,
            autoMirror = false,
        )
        return _securityStatusSurfaceNormal!!
    }

private var _securityStatusSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.SecurityStatus: ImageVector
    get() {
        if (_securityStatusSurfaceRegular != null) return _securityStatusSurfaceRegular!!
        _securityStatusSurfaceRegular = buildHyperOSIcon(
            name = "SecurityStatus.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(626f, 879f),
                PathNode.LineTo(358f, 762f),
                PathNode.QuadTo(313f, 742f, 294f, 732f),
                PathNode.QuadTo(275f, 722f, 262f, 710f),
                PathNode.QuadTo(237f, 685f, 226f, 652f),
                PathNode.QuadTo(221f, 636f, 220.5f, 618.5f),
                PathNode.QuadTo(220f, 601f, 221f, 559f),
                PathNode.QuadTo(222f, 554f, 222f, 544f),
                PathNode.QuadTo(233f, 351f, 241f, 280f),
                PathNode.QuadTo(248f, 211f, 295.5f, 138f),
                PathNode.QuadTo(343f, 65f, 428.5f, -4f),
                PathNode.QuadTo(514f, -73f, 632f, -130f),
                PathNode.QuadTo(665f, -146f, 698f, -130f),
                PathNode.QuadTo(816f, -73f, 902f, -4f),
                PathNode.QuadTo(988f, 65f, 1036f, 138f),
                PathNode.QuadTo(1084f, 211f, 1091f, 280f),
                PathNode.QuadTo(1098f, 351f, 1109f, 544f),
                PathNode.QuadTo(1112f, 591f, 1111.5f, 613f),
                PathNode.QuadTo(1111f, 635f, 1105f, 652f),
                PathNode.QuadTo(1093f, 687f, 1069f, 710f),
                PathNode.QuadTo(1058f, 721f, 1042f, 729.5f),
                PathNode.QuadTo(1026f, 738f, 986f, 757f),
                PathNode.QuadTo(982f, 758f, 974f, 762f),
                PathNode.LineTo(706f, 879f),
                PathNode.QuadTo(685f, 888f, 666f, 888f),
                PathNode.QuadTo(647f, 888f, 626f, 879f),
                PathNode.Close,
                PathNode.MoveTo(891f, 556f),
                PathNode.LineTo(902f, 544f),
                PathNode.QuadTo(910f, 536f, 910f, 526.5f),
                PathNode.QuadTo(910f, 517f, 902f, 509f),
                PathNode.LineTo(641f, 247f),
                PathNode.QuadTo(633f, 238f, 623f, 238.5f),
                PathNode.QuadTo(613f, 239f, 605f, 247f),
                PathNode.LineTo(464f, 388f),
                PathNode.QuadTo(456f, 396f, 456f, 405.5f),
                PathNode.QuadTo(456f, 415f, 464f, 423f),
                PathNode.LineTo(475f, 435f),
                PathNode.QuadTo(483f, 444f, 493f, 443.5f),
                PathNode.QuadTo(503f, 443f, 511f, 435f),
                PathNode.LineTo(623f, 323f),
                PathNode.LineTo(855f, 556f),
                PathNode.QuadTo(863f, 564f, 873f, 564.5f),
                PathNode.QuadTo(883f, 565f, 891f, 556f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 0.7143f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _securityStatusSurfaceRegular!!
    }

private var _securityStatusSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.SecurityStatus: ImageVector
    get() {
        if (_securityStatusSurfaceMedium != null) return _securityStatusSurfaceMedium!!
        _securityStatusSurfaceMedium = buildHyperOSIcon(
            name = "SecurityStatus.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(623.6f, 883.9f),
                PathNode.LineTo(355.6f, 766.9f),
                PathNode.QuadTo(310f, 746.6f, 290.8f, 736.5f),
                PathNode.QuadTo(271.5f, 726.3f, 257.9f, 713.8f),
                PathNode.QuadTo(232.1f, 688f, 220.6f, 653.6f),
                PathNode.QuadTo(215.6f, 637.1f, 215f, 619.2f),
                PathNode.QuadTo(214.3f, 601.3f, 215.3f, 559.3f),
                PathNode.QuadTo(216.3f, 554f, 216.3f, 543.7f),
                PathNode.QuadTo(227.3f, 350.2f, 235.3f, 279.5f),
                PathNode.QuadTo(242.3f, 209.4f, 290.2f, 135.6f),
                PathNode.QuadTo(338.1f, 61.8f, 424.4f, -7.9f),
                PathNode.QuadTo(510.8f, -77.6f, 629.3f, -134.9f),
                PathNode.QuadTo(664.7f, -151.9f, 700.2f, -134.9f),
                PathNode.QuadTo(818.7f, -77.6f, 905.4f, -8.1f),
                PathNode.QuadTo(992.1f, 61.5f, 1040.5f, 135.4f),
                PathNode.QuadTo(1088.9f, 209.4f, 1096.1f, 279.5f),
                PathNode.QuadTo(1103.1f, 350.2f, 1114.1f, 543.7f),
                PathNode.QuadTo(1117.1f, 590.7f, 1116.5f, 613.4f),
                PathNode.QuadTo(1115.9f, 636.1f, 1109.9f, 653.6f),
                PathNode.QuadTo(1097.6f, 690f, 1072.5f, 713.8f),
                PathNode.QuadTo(1061f, 725.3f, 1044.3f, 734.2f),
                PathNode.QuadTo(1027.6f, 743.1f, 987.9f, 761.9f),
                PathNode.QuadTo(983.9f, 762.9f, 975.9f, 766.9f),
                PathNode.LineTo(707.9f, 883.9f),
                PathNode.QuadTo(685.8f, 893.4f, 665.7f, 893.4f),
                PathNode.QuadTo(645.6f, 893.4f, 623.6f, 883.9f),
                PathNode.Close,
                PathNode.MoveTo(891.8f, 562.8f),
                PathNode.LineTo(908.5f, 544.8f),
                PathNode.QuadTo(917f, 536.3f, 917f, 526.4f),
                PathNode.QuadTo(917f, 516.5f, 908.5f, 507.9f),
                PathNode.LineTo(641.8f, 240.2f),
                PathNode.QuadTo(633.3f, 231f, 623.1f, 231.3f),
                PathNode.QuadTo(613f, 231.7f, 604.2f, 240.2f),
                PathNode.LineTo(457.5f, 386.9f),
                PathNode.QuadTo(449f, 395.5f, 449f, 405.4f),
                PathNode.QuadTo(449f, 415.3f, 457.5f, 423.8f),
                PathNode.LineTo(474.2f, 441.8f),
                PathNode.QuadTo(482.7f, 451.3f, 493f, 450.9f),
                PathNode.QuadTo(503.3f, 450.6f, 511.8f, 441.8f),
                PathNode.LineTo(623f, 330.6f),
                PathNode.LineTo(854.2f, 562.8f),
                PathNode.QuadTo(863f, 571.6f, 873.1f, 571.9f),
                PathNode.QuadTo(883.3f, 572.3f, 891.8f, 562.8f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.0048f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _securityStatusSurfaceMedium!!
    }

private var _securityStatusSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.SecurityStatus: ImageVector
    get() {
        if (_securityStatusSurfaceDemibold != null) return _securityStatusSurfaceDemibold!!
        _securityStatusSurfaceDemibold = buildHyperOSIcon(
            name = "SecurityStatus.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(621.9f, 887.3f),
                PathNode.LineTo(353.9f, 770.3f),
                PathNode.QuadTo(307.9f, 749.8f, 288.5f, 739.6f),
                PathNode.QuadTo(269f, 729.4f, 255.1f, 716.4f),
                PathNode.QuadTo(228.7f, 690.1f, 216.8f, 654.8f),
                PathNode.QuadTo(211.8f, 637.8f, 211.1f, 619.6f),
                PathNode.QuadTo(210.4f, 601.5f, 211.4f, 559.5f),
                PathNode.QuadTo(212.4f, 554f, 212.4f, 543.5f),
                PathNode.QuadTo(223.4f, 349.6f, 231.4f, 279.1f),
                PathNode.QuadTo(238.4f, 208.2f, 286.5f, 133.9f),
                PathNode.QuadTo(334.7f, 59.5f, 421.6f, -10.7f),
                PathNode.QuadTo(508.5f, -80.8f, 627.4f, -138.3f),
                PathNode.QuadTo(664.5f, -156.1f, 701.7f, -138.3f),
                PathNode.QuadTo(820.6f, -80.8f, 907.7f, -10.9f),
                PathNode.QuadTo(994.9f, 59f, 1043.6f, 133.6f),
                PathNode.QuadTo(1092.3f, 208.2f, 1099.7f, 279.1f),
                PathNode.QuadTo(1106.7f, 349.6f, 1117.7f, 543.5f),
                PathNode.QuadTo(1120.7f, 590.5f, 1120f, 613.7f),
                PathNode.QuadTo(1119.3f, 636.8f, 1113.3f, 654.8f),
                PathNode.QuadTo(1100.8f, 692.1f, 1075f, 716.4f),
                PathNode.QuadTo(1063.1f, 728.4f, 1045.9f, 737.5f),
                PathNode.QuadTo(1028.8f, 746.7f, 989.2f, 765.3f),
                PathNode.QuadTo(985.2f, 766.3f, 977.2f, 770.3f),
                PathNode.LineTo(709.2f, 887.3f),
                PathNode.QuadTo(686.4f, 897.2f, 665.5f, 897.2f),
                PathNode.QuadTo(644.7f, 897.2f, 621.9f, 887.3f),
                PathNode.Close,
                PathNode.MoveTo(892.4f, 567.5f),
                PathNode.LineTo(913f, 545.4f),
                PathNode.QuadTo(921.9f, 536.5f, 921.9f, 526.3f),
                PathNode.QuadTo(921.9f, 516.1f, 913f, 507.2f),
                PathNode.LineTo(642.4f, 235.5f),
                PathNode.QuadTo(633.5f, 226.1f, 623.2f, 226.3f),
                PathNode.QuadTo(613f, 226.6f, 603.6f, 235.5f),
                PathNode.LineTo(453f, 386.2f),
                PathNode.QuadTo(444.1f, 395.1f, 444.1f, 405.3f),
                PathNode.QuadTo(444.1f, 415.5f, 453f, 424.4f),
                PathNode.LineTo(473.6f, 446.5f),
                PathNode.QuadTo(482.5f, 456.4f, 493f, 456.1f),
                PathNode.QuadTo(503.5f, 455.9f, 512.4f, 446.5f),
                PathNode.LineTo(623f, 335.9f),
                PathNode.LineTo(853.6f, 567.5f),
                PathNode.QuadTo(863f, 576.9f, 873.2f, 577.1f),
                PathNode.QuadTo(883.5f, 577.4f, 892.4f, 567.5f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.2087f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _securityStatusSurfaceDemibold!!
    }

private var _securityStatusSurfaceDemibold: ImageVector? = null

