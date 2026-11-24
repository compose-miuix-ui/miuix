// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.Microphone: ImageVector
    get() = MiuixIcons.Glass.Regular.Microphone

val MiuixIcons.Glass.Light.Microphone: ImageVector
    get() {
        if (_microphoneLight != null) return _microphoneLight!!
        _microphoneLight = buildHyperOSIcon(
            name = "Microphone.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(664.9f, 187.3f),
                PathNode.QuadTo(735.4f, 187.3f, 795.3f, 222.1f),
                PathNode.QuadTo(855.3f, 256.8f, 890f, 316.7f),
                PathNode.QuadTo(924.7f, 376.6f, 924.7f, 447f),
                PathNode.VerticalTo(661f),
                PathNode.QuadTo(924.7f, 731.4f, 889.9f, 790.8f),
                PathNode.QuadTo(855.2f, 850.2f, 795.3f, 885f),
                PathNode.QuadTo(735.4f, 919.7f, 665f, 919.7f),
                PathNode.QuadTo(594.6f, 919.7f, 534.9f, 884.9f),
                PathNode.QuadTo(475.3f, 850.2f, 440.3f, 790.8f),
                PathNode.QuadTo(405.3f, 731.4f, 405.3f, 661f),
                PathNode.VerticalTo(447f),
                PathNode.QuadTo(405.3f, 376.6f, 440.3f, 316.7f),
                PathNode.QuadTo(475.3f, 256.8f, 534.9f, 222.1f),
                PathNode.QuadTo(594.4f, 187.3f, 664.9f, 187.3f),
                PathNode.Close,
                PathNode.MoveTo(635.3f, 17.9f),
                PathNode.VerticalTo(-161.8f),
                PathNode.QuadTo(635.3f, -177.1f, 650.5f, -177.1f),
                PathNode.HorizontalTo(679.5f),
                PathNode.QuadTo(694.7f, -177.1f, 694.7f, -161.8f),
                PathNode.VerticalTo(17.9f),
                PathNode.QuadTo(804.8f, 24.3f, 895.5f, 83.1f),
                PathNode.QuadTo(986.2f, 141.9f, 1038.9f, 236.8f),
                PathNode.QuadTo(1091.7f, 331.8f, 1091.7f, 443f),
                PathNode.VerticalTo(495.7f),
                PathNode.QuadTo(1091.7f, 511f, 1076.5f, 511f),
                PathNode.HorizontalTo(1047.7f),
                PathNode.QuadTo(1040.8f, 511f, 1036.6f, 507.2f),
                PathNode.QuadTo(1032.3f, 503.3f, 1032.3f, 495.7f),
                PathNode.VerticalTo(443f),
                PathNode.QuadTo(1032.3f, 343.4f, 983.1f, 258.8f),
                PathNode.QuadTo(933.8f, 174.2f, 849.2f, 125f),
                PathNode.QuadTo(764.7f, 75.7f, 665f, 75.7f),
                PathNode.QuadTo(565.4f, 75.7f, 480.9f, 125f),
                PathNode.QuadTo(396.3f, 174.2f, 347f, 258.8f),
                PathNode.QuadTo(297.7f, 343.4f, 297.7f, 443f),
                PathNode.VerticalTo(495.7f),
                PathNode.QuadTo(297.7f, 503.3f, 293.8f, 507.2f),
                PathNode.QuadTo(290f, 511f, 282.3f, 511f),
                PathNode.HorizontalTo(253.5f),
                PathNode.QuadTo(238.3f, 511f, 238.3f, 495.7f),
                PathNode.VerticalTo(443f),
                PathNode.QuadTo(238.3f, 331.8f, 291.1f, 236.8f),
                PathNode.QuadTo(343.8f, 141.9f, 434.5f, 83.1f),
                PathNode.QuadTo(525.2f, 24.3f, 635.3f, 17.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1037.9442f,
            autoMirror = false,
        )
        return _microphoneLight!!
    }

private var _microphoneLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Microphone: ImageVector
    get() {
        if (_microphoneNormal != null) return _microphoneNormal!!
        _microphoneNormal = buildHyperOSIcon(
            name = "Microphone.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(665f, 178.2f),
                PathNode.QuadTo(737.9f, 178.2f, 799.9f, 214.1f),
                PathNode.QuadTo(862f, 250.1f, 897.9f, 312.1f),
                PathNode.QuadTo(933.8f, 374.1f, 933.8f, 447f),
                PathNode.VerticalTo(661f),
                PathNode.QuadTo(933.8f, 733.9f, 897.9f, 795.4f),
                PathNode.QuadTo(861.9f, 857f, 799.9f, 892.9f),
                PathNode.QuadTo(737.9f, 928.8f, 665f, 928.8f),
                PathNode.QuadTo(592.1f, 928.8f, 530.5f, 892.9f),
                PathNode.QuadTo(468.9f, 856.9f, 432.5f, 795.4f),
                PathNode.QuadTo(396.2f, 733.9f, 396.2f, 661f),
                PathNode.VerticalTo(447f),
                PathNode.QuadTo(396.2f, 374.1f, 432.5f, 312.1f),
                PathNode.QuadTo(468.9f, 250.1f, 530.5f, 214.1f),
                PathNode.QuadTo(592.1f, 178.2f, 665f, 178.2f),
                PathNode.Close,
                PathNode.MoveTo(626.2f, 9f),
                PathNode.VerticalTo(-157.8f),
                PathNode.QuadTo(626.2f, -177.7f, 646f, -177.7f),
                PathNode.HorizontalTo(684f),
                PathNode.QuadTo(703.8f, -177.7f, 703.8f, -157.8f),
                PathNode.VerticalTo(9f),
                PathNode.QuadTo(814.6f, 17.9f, 905.1f, 78.2f),
                PathNode.QuadTo(995.7f, 138.5f, 1048.3f, 234.5f),
                PathNode.QuadTo(1100.8f, 330.6f, 1100.8f, 443f),
                PathNode.VerticalTo(491.1f),
                PathNode.QuadTo(1100.8f, 511f, 1081f, 511f),
                PathNode.HorizontalTo(1043.1f),
                PathNode.QuadTo(1034.1f, 511f, 1028.6f, 506f),
                PathNode.QuadTo(1023.2f, 501f, 1023.2f, 491.1f),
                PathNode.VerticalTo(443f),
                PathNode.QuadTo(1023.2f, 345.9f, 975.1f, 263.4f),
                PathNode.QuadTo(927.1f, 181f, 844.6f, 132.9f),
                PathNode.QuadTo(762.1f, 84.8f, 665f, 84.8f),
                PathNode.QuadTo(567.9f, 84.8f, 485.4f, 132.9f),
                PathNode.QuadTo(403f, 181f, 354.9f, 263.4f),
                PathNode.QuadTo(306.8f, 345.9f, 306.8f, 443f),
                PathNode.VerticalTo(491.1f),
                PathNode.QuadTo(306.8f, 501f, 301.9f, 506f),
                PathNode.QuadTo(296.9f, 511f, 286.9f, 511f),
                PathNode.HorizontalTo(249f),
                PathNode.QuadTo(229.2f, 511f, 229.2f, 491.1f),
                PathNode.VerticalTo(443f),
                PathNode.QuadTo(229.2f, 330.6f, 281.7f, 234.5f),
                PathNode.QuadTo(334.3f, 138.5f, 424.9f, 78.2f),
                PathNode.QuadTo(515.4f, 17.9f, 626.2f, 9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1042.2218f,
            autoMirror = false,
        )
        return _microphoneNormal!!
    }

private var _microphoneNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Microphone: ImageVector
    get() {
        if (_microphoneRegular != null) return _microphoneRegular!!
        _microphoneRegular = buildHyperOSIcon(
            name = "Microphone.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(665f, 174f),
                PathNode.QuadTo(739f, 174f, 802f, 210.5f),
                PathNode.QuadTo(865f, 247f, 901.5f, 310f),
                PathNode.QuadTo(938f, 373f, 938f, 447f),
                PathNode.VerticalTo(661f),
                PathNode.QuadTo(938f, 735f, 901.5f, 797.5f),
                PathNode.QuadTo(865f, 860f, 802f, 896.5f),
                PathNode.QuadTo(739f, 933f, 665f, 933f),
                PathNode.QuadTo(591f, 933f, 528.5f, 896.5f),
                PathNode.QuadTo(466f, 860f, 429f, 797.5f),
                PathNode.QuadTo(392f, 735f, 392f, 661f),
                PathNode.VerticalTo(447f),
                PathNode.QuadTo(392f, 373f, 429f, 310f),
                PathNode.QuadTo(466f, 247f, 528.5f, 210.5f),
                PathNode.QuadTo(591f, 174f, 665f, 174f),
                PathNode.Close,
                PathNode.MoveTo(622f, 5f),
                PathNode.VerticalTo(-156f),
                PathNode.QuadTo(622f, -178f, 644f, -178f),
                PathNode.HorizontalTo(686f),
                PathNode.QuadTo(708f, -178f, 708f, -156f),
                PathNode.VerticalTo(5f),
                PathNode.QuadTo(819f, 15f, 909.5f, 76f),
                PathNode.QuadTo(1000f, 137f, 1052.5f, 233.5f),
                PathNode.QuadTo(1105f, 330f, 1105f, 443f),
                PathNode.VerticalTo(489f),
                PathNode.QuadTo(1105f, 511f, 1083f, 511f),
                PathNode.HorizontalTo(1041f),
                PathNode.QuadTo(1031f, 511f, 1025f, 505.5f),
                PathNode.QuadTo(1019f, 500f, 1019f, 489f),
                PathNode.VerticalTo(443f),
                PathNode.QuadTo(1019f, 347f, 971.5f, 265.5f),
                PathNode.QuadTo(924f, 184f, 842.5f, 136.5f),
                PathNode.QuadTo(761f, 89f, 665f, 89f),
                PathNode.QuadTo(569f, 89f, 487.5f, 136.5f),
                PathNode.QuadTo(406f, 184f, 358.5f, 265.5f),
                PathNode.QuadTo(311f, 347f, 311f, 443f),
                PathNode.VerticalTo(489f),
                PathNode.QuadTo(311f, 500f, 305.5f, 505.5f),
                PathNode.QuadTo(300f, 511f, 289f, 511f),
                PathNode.HorizontalTo(247f),
                PathNode.QuadTo(225f, 511f, 225f, 489f),
                PathNode.VerticalTo(443f),
                PathNode.QuadTo(225f, 330f, 277.5f, 233.5f),
                PathNode.QuadTo(330f, 137f, 420.5f, 76f),
                PathNode.QuadTo(511f, 15f, 622f, 5f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.6667f,
            translationY = 1044.1667f,
            autoMirror = false,
        )
        return _microphoneRegular!!
    }

private var _microphoneRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Microphone: ImageVector
    get() {
        if (_microphoneMedium != null) return _microphoneMedium!!
        _microphoneMedium = buildHyperOSIcon(
            name = "Microphone.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(664.7f, 174f),
                PathNode.QuadTo(740.4f, 174f, 804.4f, 211.3f),
                PathNode.QuadTo(868.5f, 248.6f, 905.8f, 312.7f),
                PathNode.QuadTo(943.1f, 376.8f, 943.1f, 452.4f),
                PathNode.VerticalTo(661f),
                PathNode.QuadTo(943.1f, 736.4f, 905.8f, 800.1f),
                PathNode.QuadTo(868.5f, 863.8f, 804.4f, 901.1f),
                PathNode.QuadTo(740.4f, 938.4f, 664.7f, 938.4f),
                PathNode.QuadTo(589.4f, 938.4f, 525.5f, 901.1f),
                PathNode.QuadTo(461.7f, 863.8f, 424f, 800.1f),
                PathNode.QuadTo(386.3f, 736.4f, 386.3f, 661f),
                PathNode.VerticalTo(452.4f),
                PathNode.QuadTo(386.3f, 376.8f, 424f, 312.7f),
                PathNode.QuadTo(461.7f, 248.6f, 525.5f, 211.3f),
                PathNode.QuadTo(589.4f, 174f, 664.7f, 174f),
                PathNode.Close,
                PathNode.MoveTo(613.6f, -6.1f),
                PathNode.VerticalTo(-159.2f),
                PathNode.QuadTo(613.6f, -186.1f, 640.5f, -186.1f),
                PathNode.HorizontalTo(689f),
                PathNode.QuadTo(715.8f, -186.1f, 715.8f, -159.2f),
                PathNode.VerticalTo(-6.1f),
                PathNode.QuadTo(827.9f, 5.8f, 920.3f, 67.2f),
                PathNode.QuadTo(1012.7f, 128.6f, 1067.5f, 226.5f),
                PathNode.QuadTo(1122.3f, 324.3f, 1123.6f, 441.9f),
                PathNode.LineTo(1124.2f, 486.8f),
                PathNode.QuadTo(1124.7f, 514f, 1097.6f, 514f),
                PathNode.HorizontalTo(1048.8f),
                PathNode.QuadTo(1036.4f, 514f, 1029.2f, 507.1f),
                PathNode.QuadTo(1022f, 500.3f, 1021.7f, 487.1f),
                PathNode.LineTo(1021.4f, 441.9f),
                PathNode.QuadTo(1020.9f, 344f, 972.2f, 264f),
                PathNode.QuadTo(923.5f, 184f, 841.6f, 138.5f),
                PathNode.QuadTo(759.6f, 93.1f, 664.7f, 93.1f),
                PathNode.QuadTo(570.4f, 93.1f, 488.6f, 138.8f),
                PathNode.QuadTo(406.8f, 184.5f, 358.1f, 263.7f),
                PathNode.QuadTo(309.4f, 342.9f, 308.6f, 437.9f),
                PathNode.LineTo(308f, 487.4f),
                PathNode.QuadTo(307.8f, 500.5f, 300.9f, 507.3f),
                PathNode.QuadTo(294.1f, 514f, 280.9f, 514f),
                PathNode.HorizontalTo(232.7f),
                PathNode.QuadTo(205.8f, 514f, 206.1f, 487.1f),
                PathNode.LineTo(206.4f, 437.9f),
                PathNode.QuadTo(206.9f, 323.2f, 261.6f, 226.1f),
                PathNode.QuadTo(316.2f, 128.9f, 409f, 67.4f),
                PathNode.QuadTo(501.8f, 5.8f, 613.6f, -6.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.5276f,
            translationY = 1042.8153f,
            autoMirror = false,
        )
        return _microphoneMedium!!
    }

private var _microphoneMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Microphone: ImageVector
    get() {
        if (_microphoneDemibold != null) return _microphoneDemibold!!
        _microphoneDemibold = buildHyperOSIcon(
            name = "Microphone.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(664.5f, 174f),
                PathNode.QuadTo(741.3f, 174f, 806.1f, 211.9f),
                PathNode.QuadTo(871f, 249.8f, 908.9f, 314.6f),
                PathNode.QuadTo(946.7f, 379.4f, 946.7f, 456.2f),
                PathNode.VerticalTo(661f),
                PathNode.QuadTo(946.7f, 737.3f, 908.9f, 801.9f),
                PathNode.QuadTo(871f, 866.4f, 806.1f, 904.3f),
                PathNode.QuadTo(741.3f, 942.2f, 664.5f, 942.2f),
                PathNode.QuadTo(588.2f, 942.2f, 523.4f, 904.3f),
                PathNode.QuadTo(458.6f, 866.4f, 420.5f, 801.9f),
                PathNode.QuadTo(382.4f, 737.3f, 382.4f, 661f),
                PathNode.VerticalTo(456.2f),
                PathNode.QuadTo(382.4f, 379.4f, 420.5f, 314.6f),
                PathNode.QuadTo(458.6f, 249.8f, 523.4f, 211.9f),
                PathNode.QuadTo(588.2f, 174f, 664.5f, 174f),
                PathNode.Close,
                PathNode.MoveTo(607.8f, -13.8f),
                PathNode.VerticalTo(-161.5f),
                PathNode.QuadTo(607.8f, -191.8f, 638f, -191.8f),
                PathNode.HorizontalTo(691.1f),
                PathNode.QuadTo(721.3f, -191.8f, 721.3f, -161.5f),
                PathNode.VerticalTo(-13.8f),
                PathNode.QuadTo(834.2f, -0.6f, 927.9f, 61.1f),
                PathNode.QuadTo(1021.6f, 122.8f, 1078f, 221.6f),
                PathNode.QuadTo(1134.4f, 320.4f, 1136.7f, 441.2f),
                PathNode.LineTo(1137.6f, 485.3f),
                PathNode.QuadTo(1138.5f, 516.1f, 1107.8f, 516.1f),
                PathNode.HorizontalTo(1054.3f),
                PathNode.QuadTo(1040.2f, 516.1f, 1032.1f, 508.3f),
                PathNode.QuadTo(1024.1f, 500.5f, 1023.6f, 485.8f),
                PathNode.LineTo(1023.1f, 441.2f),
                PathNode.QuadTo(1022.2f, 341.9f, 972.6f, 263f),
                PathNode.QuadTo(923.1f, 184f, 840.9f, 139.9f),
                PathNode.QuadTo(758.7f, 95.9f, 664.5f, 95.9f),
                PathNode.QuadTo(571.3f, 95.9f, 489.3f, 140.4f),
                PathNode.QuadTo(407.4f, 184.9f, 357.8f, 262.5f),
                PathNode.QuadTo(308.2f, 340.1f, 306.9f, 434.3f),
                PathNode.LineTo(305.9f, 486.2f),
                PathNode.QuadTo(305.5f, 500.9f, 297.7f, 508.5f),
                PathNode.QuadTo(289.9f, 516.1f, 275.2f, 516.1f),
                PathNode.HorizontalTo(222.6f),
                PathNode.QuadTo(192.4f, 516.1f, 192.8f, 485.8f),
                PathNode.LineTo(193.3f, 434.3f),
                PathNode.QuadTo(194.2f, 318.5f, 250.4f, 220.9f),
                PathNode.QuadTo(306.6f, 123.2f, 401f, 61.3f),
                PathNode.QuadTo(495.4f, -0.6f, 607.8f, -13.8f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 1.427f,
            translationY = 1041.8693f,
            autoMirror = false,
        )
        return _microphoneDemibold!!
    }

private var _microphoneDemibold: ImageVector? = null

