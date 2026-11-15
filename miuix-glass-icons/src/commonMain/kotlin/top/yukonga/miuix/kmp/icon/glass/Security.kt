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

val MiuixIcons.Glass.Security: ImageVector
    get() = MiuixIcons.Glass.Regular.Security

val MiuixIcons.Glass.Light.Security: ImageVector
    get() {
        if (_securitySurfaceLight != null) return _securitySurfaceLight!!
        _securitySurfaceLight = buildHyperOSIcon(
            name = "Security.Glass.Light",
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
                PathNode.MoveTo(679.6f, 620.6f),
                PathNode.VerticalTo(447.9f),
                PathNode.HorizontalTo(808f),
                PathNode.QuadTo(818.9f, 447.9f, 823f, 441.7f),
                PathNode.QuadTo(827.1f, 435.4f, 823f, 425.1f),
                PathNode.LineTo(708f, 157.2f),
                PathNode.QuadTo(703.8f, 146.2f, 696.7f, 143.3f),
                PathNode.QuadTo(689.7f, 140.4f, 684.4f, 146.2f),
                PathNode.QuadTo(679.1f, 152f, 679.1f, 163.6f),
                PathNode.VerticalTo(335.8f),
                PathNode.HorizontalTo(551.9f),
                PathNode.QuadTo(539.9f, 335.8f, 535.2f, 342.6f),
                PathNode.QuadTo(530.6f, 349.3f, 535.2f, 360.8f),
                PathNode.LineTo(650.7f, 626.9f),
                PathNode.QuadTo(654.9f, 637.9f, 661.9f, 640.8f),
                PathNode.QuadTo(669f, 643.7f, 674.3f, 637.9f),
                PathNode.QuadTo(679.6f, 632.1f, 679.6f, 620.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.1587f,
            translationY = 1042f,
            autoMirror = false,
        )
        return _securitySurfaceLight!!
    }

private var _securitySurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Security: ImageVector
    get() {
        if (_securitySurfaceNormal != null) return _securitySurfaceNormal!!
        _securitySurfaceNormal = buildHyperOSIcon(
            name = "Security.Glass.Normal",
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
                PathNode.MoveTo(679.9f, 626.4f),
                PathNode.VerticalTo(450f),
                PathNode.HorizontalTo(816.2f),
                PathNode.QuadTo(829.3f, 450f, 834f, 442.6f),
                PathNode.QuadTo(838.7f, 435.1f, 834f, 423f),
                PathNode.LineTo(716.2f, 147.4f),
                PathNode.QuadTo(710.8f, 133.7f, 702.1f, 130f),
                PathNode.QuadTo(693.3f, 126.4f, 686.5f, 133.7f),
                PathNode.QuadTo(679.7f, 141f, 679.7f, 155.6f),
                PathNode.VerticalTo(331.8f),
                PathNode.HorizontalTo(545.8f),
                PathNode.QuadTo(531f, 331.8f, 525.4f, 340.1f),
                PathNode.QuadTo(519.9f, 348.4f, 525.4f, 362.3f),
                PathNode.LineTo(643.3f, 634.5f),
                PathNode.QuadTo(648.8f, 648.3f, 657.5f, 651.9f),
                PathNode.QuadTo(666.3f, 655.6f, 673.1f, 648.3f),
                PathNode.QuadTo(679.9f, 641f, 679.9f, 626.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 0.8532f,
            translationY = 1041.7708f,
            autoMirror = false,
        )
        return _securitySurfaceNormal!!
    }

private var _securitySurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Security: ImageVector
    get() {
        if (_securitySurfaceRegular != null) return _securitySurfaceRegular!!
        _securitySurfaceRegular = buildHyperOSIcon(
            name = "Security.Glass.Regular",
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
                PathNode.MoveTo(680f, 629f),
                PathNode.VerticalTo(451f),
                PathNode.HorizontalTo(820f),
                PathNode.QuadTo(834f, 451f, 839f, 443f),
                PathNode.QuadTo(844f, 435f, 839f, 422f),
                PathNode.LineTo(720f, 143f),
                PathNode.QuadTo(714f, 128f, 704.5f, 124f),
                PathNode.QuadTo(695f, 120f, 687.5f, 128f),
                PathNode.QuadTo(680f, 136f, 680f, 152f),
                PathNode.VerticalTo(330f),
                PathNode.HorizontalTo(543f),
                PathNode.QuadTo(527f, 330f, 521f, 339f),
                PathNode.QuadTo(515f, 348f, 521f, 363f),
                PathNode.LineTo(640f, 638f),
                PathNode.QuadTo(646f, 653f, 655.5f, 657f),
                PathNode.QuadTo(665f, 661f, 672.5f, 653f),
                PathNode.QuadTo(680f, 645f, 680f, 629f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 0.7143f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _securitySurfaceRegular!!
    }

private var _securitySurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Security: ImageVector
    get() {
        if (_securitySurfaceMedium != null) return _securitySurfaceMedium!!
        _securitySurfaceMedium = buildHyperOSIcon(
            name = "Security.Glass.Medium",
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
                PathNode.MoveTo(679.7f, 630.4f),
                PathNode.VerticalTo(456.4f),
                PathNode.HorizontalTo(826.2f),
                PathNode.QuadTo(841.6f, 456.4f, 847.4f, 447.5f),
                PathNode.QuadTo(853.2f, 438.5f, 847.4f, 424.2f),
                PathNode.LineTo(726.2f, 140f),
                PathNode.QuadTo(719.1f, 122.6f, 708f, 117.8f),
                PathNode.QuadTo(696.9f, 113f, 688.3f, 122.2f),
                PathNode.QuadTo(679.7f, 131.4f, 679.7f, 150.1f),
                PathNode.VerticalTo(324.6f),
                PathNode.HorizontalTo(534.4f),
                PathNode.QuadTo(517.3f, 324.6f, 510.9f, 334.3f),
                PathNode.QuadTo(504.5f, 343.9f, 511f, 359.8f),
                PathNode.LineTo(633.2f, 640.4f),
                PathNode.QuadTo(640.6f, 658.1f, 651.6f, 662.8f),
                PathNode.QuadTo(662.6f, 667.5f, 671.1f, 658.4f),
                PathNode.QuadTo(679.7f, 649.3f, 679.7f, 630.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.0048f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _securitySurfaceMedium!!
    }

private var _securitySurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Security: ImageVector
    get() {
        if (_securitySurfaceDemibold != null) return _securitySurfaceDemibold!!
        _securitySurfaceDemibold = buildHyperOSIcon(
            name = "Security.Glass.Demibold",
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
                PathNode.MoveTo(679.5f, 631.3f),
                PathNode.VerticalTo(460.2f),
                PathNode.HorizontalTo(830.6f),
                PathNode.QuadTo(846.9f, 460.2f, 853.2f, 450.6f),
                PathNode.QuadTo(859.6f, 441f, 853.2f, 425.7f),
                PathNode.LineTo(730.6f, 137.9f),
                PathNode.QuadTo(722.7f, 118.8f, 710.5f, 113.4f),
                PathNode.QuadTo(698.2f, 108.1f, 688.9f, 118.1f),
                PathNode.QuadTo(679.5f, 128.2f, 679.5f, 148.8f),
                PathNode.VerticalTo(320.8f),
                PathNode.HorizontalTo(528.3f),
                PathNode.QuadTo(510.5f, 320.8f, 503.8f, 331f),
                PathNode.QuadTo(497.1f, 341.1f, 504f, 357.5f),
                PathNode.LineTo(628.5f, 642.1f),
                PathNode.QuadTo(636.8f, 661.7f, 648.8f, 666.9f),
                PathNode.QuadTo(660.9f, 672f, 670.2f, 662.2f),
                PathNode.QuadTo(679.5f, 652.4f, 679.5f, 631.3f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.2087f,
            translationY = 1041.6667f,
            autoMirror = false,
        )
        return _securitySurfaceDemibold!!
    }

private var _securitySurfaceDemibold: ImageVector? = null

