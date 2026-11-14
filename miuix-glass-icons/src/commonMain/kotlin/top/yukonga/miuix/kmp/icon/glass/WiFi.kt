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

val MiuixIcons.Glass.WiFi: ImageVector
    get() = MiuixIcons.Glass.Regular.WiFi

val MiuixIcons.Glass.Light.WiFi: ImageVector
    get() {
        if (_wiFiLight != null) return _wiFiLight!!
        _wiFiLight = buildHyperOSIcon(
            name = "WiFi.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(692.8f, -12.8f),
                PathNode.LineTo(810.3f, 108.9f),
                PathNode.QuadTo(821.2f, 120.8f, 820.7f, 132.2f),
                PathNode.QuadTo(820.2f, 143.6f, 808.3f, 152.9f),
                PathNode.QuadTo(777.6f, 173.8f, 741.4f, 184.9f),
                PathNode.QuadTo(705.3f, 196.1f, 667f, 196.1f),
                PathNode.QuadTo(626.1f, 196.1f, 589.3f, 184.2f),
                PathNode.QuadTo(552.4f, 172.2f, 520.7f, 149.9f),
                PathNode.QuadTo(507.8f, 140.6f, 507.3f, 129.1f),
                PathNode.QuadTo(506.8f, 117.7f, 517.7f, 105.8f),
                PathNode.LineTo(634.7f, -12.8f),
                PathNode.QuadTo(649f, -27.7f, 663.9f, -27.4f),
                PathNode.QuadTo(678.9f, -27.1f, 692.8f, -12.8f),
                PathNode.Close,
                PathNode.MoveTo(415.3f, 256.7f),
                PathNode.QuadTo(468f, 299f, 532.2f, 322.2f),
                PathNode.QuadTo(596.3f, 345.3f, 667f, 345.3f),
                PathNode.QuadTo(736.2f, 345.3f, 799.9f, 322.7f),
                PathNode.QuadTo(863.6f, 300f, 916.2f, 258.7f),
                PathNode.QuadTo(940.1f, 239.4f, 959.8f, 261.2f),
                PathNode.LineTo(1014.9f, 320.3f),
                PathNode.QuadTo(1025.2f, 331.2f, 1024.7f, 342.9f),
                PathNode.QuadTo(1024.2f, 354.6f, 1012.3f, 364.4f),
                PathNode.QuadTo(940.7f, 423.1f, 852.4f, 455.3f),
                PathNode.QuadTo(764.2f, 487.4f, 667f, 487.4f),
                PathNode.QuadTo(568.2f, 487.4f, 478.3f, 454.1f),
                PathNode.QuadTo(388.3f, 420.7f, 316.7f, 360.4f),
                PathNode.QuadTo(304.8f, 350.6f, 304.5f, 338.9f),
                PathNode.QuadTo(304.2f, 327.2f, 315.1f, 316.3f),
                PathNode.LineTo(371.8f, 258.7f),
                PathNode.QuadTo(391.9f, 237.4f, 415.3f, 256.7f),
                PathNode.Close,
                PathNode.MoveTo(1162.2f, 471.2f),
                PathNode.LineTo(1218.9f, 528.9f),
                PathNode.QuadTo(1229.8f, 539.8f, 1229.3f, 551.2f),
                PathNode.QuadTo(1228.8f, 562.6f, 1217.3f, 572.4f),
                PathNode.QuadTo(1103.9f, 671.1f, 962.7f, 725.2f),
                PathNode.QuadTo(821.6f, 779.3f, 667f, 779.3f),
                PathNode.QuadTo(510.9f, 779.3f, 368.8f, 724.4f),
                PathNode.QuadTo(226.7f, 669.6f, 112.7f, 570.4f),
                PathNode.QuadTo(101.2f, 560.1f, 100.7f, 548.4f),
                PathNode.QuadTo(100.2f, 536.8f, 111.1f, 525.9f),
                PathNode.LineTo(167.2f, 468.2f),
                PathNode.QuadTo(188.4f, 446.4f, 211.8f, 467.2f),
                PathNode.QuadTo(305.9f, 548f, 422.7f, 592.6f),
                PathNode.QuadTo(539.4f, 637.2f, 667f, 637.2f),
                PathNode.QuadTo(793.6f, 637.2f, 909.1f, 593.1f),
                PathNode.QuadTo(1024.7f, 549f, 1118.2f, 469.7f),
                PathNode.QuadTo(1141f, 449.4f, 1162.2f, 471.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1042.6364f,
            autoMirror = false,
        )
        return _wiFiLight!!
    }

private var _wiFiLight: ImageVector? = null

val MiuixIcons.Glass.Normal.WiFi: ImageVector
    get() {
        if (_wiFiNormal != null) return _wiFiNormal!!
        _wiFiNormal = buildHyperOSIcon(
            name = "WiFi.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(697.1f, -17.1f),
                PathNode.LineTo(817.7f, 108.3f),
                PathNode.QuadTo(830.7f, 122.3f, 830.2f, 135.8f),
                PathNode.QuadTo(829.7f, 149.4f, 815.7f, 160.5f),
                PathNode.QuadTo(783.4f, 183.6f, 745.3f, 195.6f),
                PathNode.QuadTo(707.2f, 207.7f, 667f, 207.7f),
                PathNode.QuadTo(624f, 207.7f, 585.3f, 194.7f),
                PathNode.QuadTo(546.6f, 181.7f, 513.3f, 157.5f),
                PathNode.QuadTo(498.3f, 146.4f, 497.8f, 132.5f),
                PathNode.QuadTo(497.3f, 118.6f, 510.3f, 104.6f),
                PathNode.LineTo(630.1f, -17.1f),
                PathNode.QuadTo(646.3f, -34.1f, 663.6f, -33.7f),
                PathNode.QuadTo(681f, -33.2f, 697.1f, -17.1f),
                PathNode.Close,
                PathNode.MoveTo(422.7f, 249.3f),
                PathNode.QuadTo(473.5f, 290.8f, 535.8f, 313.5f),
                PathNode.QuadTo(598.2f, 336.2f, 667f, 336.2f),
                PathNode.QuadTo(734.7f, 336.2f, 796.5f, 314f),
                PathNode.QuadTo(858.4f, 291.8f, 909.2f, 251.3f),
                PathNode.QuadTo(938f, 228.1f, 961.3f, 254.2f),
                PathNode.LineTo(1019.8f, 316.7f),
                PathNode.QuadTo(1031.9f, 329.7f, 1031.4f, 343.7f),
                PathNode.QuadTo(1030.9f, 357.6f, 1016.9f, 369.6f),
                PathNode.QuadTo(944.3f, 429.2f, 854.9f, 462.3f),
                PathNode.QuadTo(765.4f, 495.4f, 667f, 495.4f),
                PathNode.QuadTo(566.7f, 495.4f, 475.7f, 461.2f),
                PathNode.QuadTo(384.7f, 427.1f, 312.1f, 365.6f),
                PathNode.QuadTo(298.1f, 353.6f, 297.6f, 339.7f),
                PathNode.QuadTo(297.2f, 325.7f, 310.2f, 312.7f),
                PathNode.LineTo(370.6f, 251.3f),
                PathNode.QuadTo(394f, 226.1f, 422.7f, 249.3f),
                PathNode.Close,
                PathNode.MoveTo(1160.7f, 461.4f),
                PathNode.LineTo(1221f, 522.8f),
                PathNode.QuadTo(1234.1f, 535.8f, 1233.6f, 549.3f),
                PathNode.QuadTo(1233.1f, 562.9f, 1219.2f, 574.9f),
                PathNode.QuadTo(1106f, 674.5f, 963.9f, 729.2f),
                PathNode.QuadTo(821.9f, 783.9f, 667f, 783.9f),
                PathNode.QuadTo(510.3f, 783.9f, 367.6f, 728.3f),
                PathNode.QuadTo(224.8f, 672.6f, 110.8f, 572.9f),
                PathNode.QuadTo(96.9f, 560.7f, 96.4f, 546.8f),
                PathNode.QuadTo(95.9f, 532.8f, 109f, 519.8f),
                PathNode.LineTo(168.4f, 458.4f),
                PathNode.QuadTo(193.6f, 432.4f, 221.6f, 457.4f),
                PathNode.QuadTo(313.5f, 537f, 427.7f, 580.8f),
                PathNode.QuadTo(541.9f, 624.7f, 667f, 624.7f),
                PathNode.QuadTo(791.1f, 624.7f, 904.2f, 581.3f),
                PathNode.QuadTo(1017.3f, 538f, 1108.4f, 459.6f),
                PathNode.QuadTo(1135.5f, 435.4f, 1160.7f, 461.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.7934f,
            autoMirror = false,
        )
        return _wiFiNormal!!
    }

private var _wiFiNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.WiFi: ImageVector
    get() {
        if (_wiFiRegular != null) return _wiFiRegular!!
        _wiFiRegular = buildHyperOSIcon(
            name = "WiFi.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(699f, -19f),
                PathNode.LineTo(821f, 108f),
                PathNode.QuadTo(835f, 123f, 834.5f, 137.5f),
                PathNode.QuadTo(834f, 152f, 819f, 164f),
                PathNode.QuadTo(786f, 188f, 747f, 200.5f),
                PathNode.QuadTo(708f, 213f, 667f, 213f),
                PathNode.QuadTo(623f, 213f, 583.5f, 199.5f),
                PathNode.QuadTo(544f, 186f, 510f, 161f),
                PathNode.QuadTo(494f, 149f, 493.5f, 134f),
                PathNode.QuadTo(493f, 119f, 507f, 104f),
                PathNode.LineTo(628f, -19f),
                PathNode.QuadTo(645f, -37f, 663.5f, -36.5f),
                PathNode.QuadTo(682f, -36f, 699f, -19f),
                PathNode.Close,
                PathNode.MoveTo(426f, 246f),
                PathNode.QuadTo(476f, 287f, 537.5f, 309.5f),
                PathNode.QuadTo(599f, 332f, 667f, 332f),
                PathNode.QuadTo(734f, 332f, 795f, 310f),
                PathNode.QuadTo(856f, 288f, 906f, 248f),
                PathNode.QuadTo(937f, 223f, 962f, 251f),
                PathNode.LineTo(1022f, 315f),
                PathNode.QuadTo(1035f, 329f, 1034.5f, 344f),
                PathNode.QuadTo(1034f, 359f, 1019f, 372f),
                PathNode.QuadTo(946f, 432f, 856f, 465.5f),
                PathNode.QuadTo(766f, 499f, 667f, 499f),
                PathNode.QuadTo(566f, 499f, 474.5f, 464.5f),
                PathNode.QuadTo(383f, 430f, 310f, 368f),
                PathNode.QuadTo(295f, 355f, 294.5f, 340f),
                PathNode.QuadTo(294f, 325f, 308f, 311f),
                PathNode.LineTo(370f, 248f),
                PathNode.QuadTo(395f, 221f, 426f, 246f),
                PathNode.Close,
                PathNode.MoveTo(1160f, 457f),
                PathNode.LineTo(1222f, 520f),
                PathNode.QuadTo(1236f, 534f, 1235.5f, 548.5f),
                PathNode.QuadTo(1235f, 563f, 1220f, 576f),
                PathNode.QuadTo(1107f, 676f, 964.5f, 731f),
                PathNode.QuadTo(822f, 786f, 667f, 786f),
                PathNode.QuadTo(510f, 786f, 367f, 730f),
                PathNode.QuadTo(224f, 674f, 110f, 574f),
                PathNode.QuadTo(95f, 561f, 94.5f, 546f),
                PathNode.QuadTo(94f, 531f, 108f, 517f),
                PathNode.LineTo(169f, 454f),
                PathNode.QuadTo(196f, 426f, 226f, 453f),
                PathNode.QuadTo(317f, 532f, 430f, 575.5f),
                PathNode.QuadTo(543f, 619f, 667f, 619f),
                PathNode.QuadTo(790f, 619f, 902f, 576f),
                PathNode.QuadTo(1014f, 533f, 1104f, 455f),
                PathNode.QuadTo(1133f, 429f, 1160f, 457f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1041.4099f,
            autoMirror = false,
        )
        return _wiFiRegular!!
    }

private var _wiFiRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.WiFi: ImageVector
    get() {
        if (_wiFiMedium != null) return _wiFiMedium!!
        _wiFiMedium = buildHyperOSIcon(
            name = "WiFi.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(699f, -25.8f),
                PathNode.LineTo(830.2f, 110.4f),
                PathNode.QuadTo(843.9f, 125.2f, 843.7f, 139.8f),
                PathNode.QuadTo(843.5f, 154.4f, 829f, 166.4f),
                PathNode.QuadTo(794.6f, 193.4f, 753.1f, 207.8f),
                PathNode.QuadTo(711.5f, 222.2f, 667f, 222.2f),
                PathNode.QuadTo(619.8f, 222.2f, 577.4f, 206.8f),
                PathNode.QuadTo(535.1f, 191.4f, 499.7f, 163.4f),
                PathNode.QuadTo(484.3f, 151.4f, 484f, 136.6f),
                PathNode.QuadTo(483.8f, 121.7f, 497.8f, 106.7f),
                PathNode.LineTo(628.3f, -25.8f),
                PathNode.QuadTo(645.3f, -43.8f, 663.6f, -43.3f),
                PathNode.QuadTo(682f, -42.8f, 699f, -25.8f),
                PathNode.Close,
                PathNode.MoveTo(428.7f, 237.1f),
                PathNode.QuadTo(477.4f, 278.9f, 538.3f, 301.7f),
                PathNode.QuadTo(599.3f, 324.4f, 667f, 324.4f),
                PathNode.QuadTo(733.5f, 324.4f, 793.9f, 302f),
                PathNode.QuadTo(854.4f, 279.6f, 903.3f, 238.8f),
                PathNode.QuadTo(934.3f, 213f, 959.6f, 241.3f),
                PathNode.LineTo(1028.8f, 315.5f),
                PathNode.QuadTo(1041.8f, 329.8f, 1041.4f, 344.7f),
                PathNode.QuadTo(1041f, 359.5f, 1026.3f, 372.5f),
                PathNode.QuadTo(952.5f, 435.2f, 860.5f, 470.2f),
                PathNode.QuadTo(768.4f, 505.2f, 667f, 505.2f),
                PathNode.QuadTo(563.3f, 505.2f, 469.8f, 469.1f),
                PathNode.QuadTo(376.2f, 433f, 302.4f, 368f),
                PathNode.QuadTo(288f, 355f, 287.6f, 340.1f),
                PathNode.QuadTo(287.2f, 325.3f, 301f, 311.3f),
                PathNode.LineTo(372.4f, 238.3f),
                PathNode.QuadTo(398f, 210.7f, 428.7f, 237.1f),
                PathNode.Close,
                PathNode.MoveTo(1155.4f, 445.4f),
                PathNode.LineTo(1226.9f, 518.1f),
                PathNode.QuadTo(1240.9f, 532.1f, 1240.5f, 546.7f),
                PathNode.QuadTo(1240.1f, 561.4f, 1225.4f, 574.4f),
                PathNode.QuadTo(1112.1f, 676.5f, 968.1f, 732.9f),
                PathNode.QuadTo(824.2f, 789.2f, 667f, 789.2f),
                PathNode.QuadTo(508.1f, 789.2f, 363.5f, 731.9f),
                PathNode.QuadTo(218.9f, 674.5f, 104.6f, 572.1f),
                PathNode.QuadTo(89.9f, 559.1f, 89.5f, 544.2f),
                PathNode.QuadTo(89.1f, 529.4f, 103.1f, 515.4f),
                PathNode.LineTo(173.9f, 442.4f),
                PathNode.QuadTo(201.1f, 414.4f, 230.6f, 441.6f),
                PathNode.QuadTo(320.2f, 521.2f, 432f, 564.8f),
                PathNode.QuadTo(543.8f, 608.5f, 667f, 608.5f),
                PathNode.QuadTo(789.2f, 608.5f, 900f, 565.3f),
                PathNode.QuadTo(1010.8f, 522.2f, 1099.4f, 443.6f),
                PathNode.QuadTo(1128.1f, 417.4f, 1155.4f, 445.4f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1039.6532f,
            autoMirror = false,
        )
        return _wiFiMedium!!
    }

private var _wiFiMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.WiFi: ImageVector
    get() {
        if (_wiFiDemibold != null) return _wiFiDemibold!!
        _wiFiDemibold = buildHyperOSIcon(
            name = "WiFi.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(699f, -30.5f),
                PathNode.LineTo(836.6f, 112.1f),
                PathNode.QuadTo(850.2f, 126.7f, 850.1f, 141.4f),
                PathNode.QuadTo(850.1f, 156.1f, 836f, 168.1f),
                PathNode.QuadTo(800.7f, 197.2f, 757.3f, 212.9f),
                PathNode.QuadTo(714f, 228.6f, 667f, 228.6f),
                PathNode.QuadTo(617.5f, 228.6f, 573.2f, 211.9f),
                PathNode.QuadTo(528.8f, 195.2f, 492.5f, 165.1f),
                PathNode.QuadTo(477.5f, 153.1f, 477.4f, 138.4f),
                PathNode.QuadTo(477.4f, 123.6f, 491.4f, 108.6f),
                PathNode.LineTo(628.5f, -30.5f),
                PathNode.QuadTo(645.5f, -48.5f, 663.7f, -48f),
                PathNode.QuadTo(682f, -47.5f, 699f, -30.5f),
                PathNode.Close,
                PathNode.MoveTo(430.6f, 230.8f),
                PathNode.QuadTo(478.3f, 273.2f, 538.9f, 296.2f),
                PathNode.QuadTo(599.5f, 319.1f, 667f, 319.1f),
                PathNode.QuadTo(733.1f, 319.1f, 793.2f, 296.4f),
                PathNode.QuadTo(853.2f, 273.8f, 901.4f, 232.4f),
                PathNode.QuadTo(932.4f, 206f, 957.9f, 234.5f),
                PathNode.LineTo(1033.5f, 315.9f),
                PathNode.QuadTo(1046.5f, 330.4f, 1046.2f, 345.1f),
                PathNode.QuadTo(1045.9f, 359.9f, 1031.4f, 372.9f),
                PathNode.QuadTo(957f, 437.5f, 863.6f, 473.5f),
                PathNode.QuadTo(770.1f, 509.6f, 667f, 509.6f),
                PathNode.QuadTo(561.4f, 509.6f, 466.5f, 472.3f),
                PathNode.QuadTo(371.5f, 435.1f, 297.1f, 368f),
                PathNode.QuadTo(283.1f, 355f, 282.8f, 340.2f),
                PathNode.QuadTo(282.5f, 325.5f, 296.1f, 311.5f),
                PathNode.LineTo(374.1f, 231.5f),
                PathNode.QuadTo(400.1f, 203.5f, 430.6f, 230.8f),
                PathNode.Close,
                PathNode.MoveTo(1152.2f, 437.2f),
                PathNode.LineTo(1230.3f, 516.8f),
                PathNode.QuadTo(1244.3f, 530.8f, 1244f, 545.5f),
                PathNode.QuadTo(1243.7f, 560.2f, 1229.2f, 573.2f),
                PathNode.QuadTo(1115.7f, 676.9f, 970.7f, 734.2f),
                PathNode.QuadTo(825.7f, 791.5f, 667f, 791.5f),
                PathNode.QuadTo(506.8f, 791.5f, 361f, 733.2f),
                PathNode.QuadTo(215.3f, 674.9f, 100.8f, 570.8f),
                PathNode.QuadTo(86.3f, 557.8f, 86f, 543f),
                PathNode.QuadTo(85.7f, 528.2f, 99.7f, 514.2f),
                PathNode.LineTo(177.3f, 434.2f),
                PathNode.QuadTo(204.7f, 406.2f, 233.8f, 433.7f),
                PathNode.QuadTo(322.5f, 513.6f, 433.4f, 557.4f),
                PathNode.QuadTo(544.4f, 601.1f, 667f, 601.1f),
                PathNode.QuadTo(788.6f, 601.1f, 898.6f, 557.9f),
                PathNode.QuadTo(1008.5f, 514.6f, 1096.2f, 435.7f),
                PathNode.QuadTo(1124.7f, 409.2f, 1152.2f, 437.2f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1038.4233f,
            autoMirror = false,
        )
        return _wiFiDemibold!!
    }

private var _wiFiDemibold: ImageVector? = null

