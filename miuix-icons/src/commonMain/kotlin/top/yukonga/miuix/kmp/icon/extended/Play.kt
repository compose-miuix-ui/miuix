// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.extended

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Play: ImageVector
    get() = MiuixIcons.Regular.Play

val MiuixIcons.Light.Play: ImageVector
    get() {
        if (_playLight != null) return _playLight!!
        _playLight = ImageVector.Builder(
            name = "Play.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1147.2f,
            viewportHeight = 1147.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1147.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(398.6f, 157.6f),
                        PathNode.LineTo(863.6f, 426.6f),
                        PathNode.QuadTo(928.6f, 463.6f, 954.6f, 482.6f),
                        PathNode.QuadTo(980.6f, 501.6f, 991.6f, 525.6f),
                        PathNode.QuadTo(1002.6f, 548.6f, 1002.6f, 574.1f),
                        PathNode.QuadTo(1002.6f, 599.6f, 991.6f, 621.6f),
                        PathNode.QuadTo(980.6f, 645.6f, 955.6f, 664.1f),
                        PathNode.QuadTo(930.6f, 682.6f, 863.6f, 720.6f),
                        PathNode.LineTo(398.6f, 989.6f),
                        PathNode.QuadTo(336.6f, 1025.6f, 305.6f, 1038.6f),
                        PathNode.QuadTo(274.6f, 1051.6f, 249.6f, 1049.6f),
                        PathNode.QuadTo(224.6f, 1047.6f, 203.1f, 1035.1f),
                        PathNode.QuadTo(181.6f, 1022.6f, 166.6f, 1001.6f),
                        PathNode.QuadTo(152.6f, 980.6f, 148.6f, 948.6f),
                        PathNode.QuadTo(144.6f, 916.6f, 144.6f, 843.6f),
                        PathNode.VerticalTo(305.6f),
                        PathNode.QuadTo(144.6f, 228.6f, 148.1f, 197.1f),
                        PathNode.QuadTo(151.6f, 165.6f, 166.6f, 143.6f),
                        PathNode.QuadTo(180.6f, 123.6f, 202.1f, 111.1f),
                        PathNode.QuadTo(223.6f, 98.6f, 247.6f, 97.6f),
                        PathNode.QuadTo(273.6f, 95.6f, 305.1f, 108.6f),
                        PathNode.QuadTo(336.6f, 121.6f, 398.6f, 157.6f),
                        PathNode.Close,
                        PathNode.MoveTo(216.6f, 180.6f),
                        PathNode.QuadTo(209.6f, 191.6f, 207.6f, 217.6f),
                        PathNode.QuadTo(205.6f, 243.6f, 205.6f, 305.6f),
                        PathNode.VerticalTo(843.6f),
                        PathNode.QuadTo(205.6f, 904.6f, 207.6f, 929.6f),
                        PathNode.QuadTo(209.6f, 954.6f, 215.6f, 964.6f),
                        PathNode.QuadTo(222.6f, 974.6f, 232.6f, 981.1f),
                        PathNode.QuadTo(242.6f, 987.6f, 255.6f, 988.6f),
                        PathNode.QuadTo(267.6f, 989.6f, 291.1f, 978.1f),
                        PathNode.QuadTo(314.6f, 966.6f, 367.6f, 936.6f),
                        PathNode.LineTo(834.6f, 666.6f),
                        PathNode.QuadTo(886.6f, 637.6f, 907.1f, 623.1f),
                        PathNode.QuadTo(927.6f, 608.6f, 934.6f, 597.6f),
                        PathNode.QuadTo(946.6f, 574.6f, 935.6f, 550.6f),
                        PathNode.QuadTo(929.6f, 539.6f, 908.1f, 524.6f),
                        PathNode.QuadTo(886.6f, 509.6f, 834.6f, 480.6f),
                        PathNode.LineTo(367.6f, 210.6f),
                        PathNode.QuadTo(313.6f, 179.6f, 290.6f, 169.1f),
                        PathNode.QuadTo(267.6f, 158.6f, 255.6f, 159.6f),
                        PathNode.QuadTo(226.6f, 161.6f, 216.6f, 180.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playLight!!
    }

private var _playLight: ImageVector? = null

val MiuixIcons.Normal.Play: ImageVector
    get() {
        if (_playNormal != null) return _playNormal!!
        _playNormal = ImageVector.Builder(
            name = "Play.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1162.0f,
            viewportHeight = 1162.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1162.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(413.2f, 160.2f),
                        PathNode.LineTo(874.1f, 427.1f),
                        PathNode.QuadTo(939.8f, 464.8f, 966.5f, 484.5f),
                        PathNode.QuadTo(993.2f, 504.2f, 1004.9f, 530.3f),
                        PathNode.QuadTo(1015.9f, 554.6f, 1015.9f, 581.9f),
                        PathNode.QuadTo(1015.9f, 609.1f, 1004.9f, 633.1f),
                        PathNode.QuadTo(993.2f, 658.5f, 966.8f, 678.4f),
                        PathNode.QuadTo(940.4f, 698.3f, 874.1f, 735.6f),
                        PathNode.LineTo(413.2f, 1002.5f),
                        PathNode.QuadTo(351.2f, 1038.5f, 318.9f, 1051.9f),
                        PathNode.QuadTo(286.5f, 1065.2f, 259.4f, 1062.5f),
                        PathNode.QuadTo(233.1f, 1060.5f, 209.8f, 1047.3f),
                        PathNode.QuadTo(186.6f, 1034.2f, 170.2f, 1011.8f),
                        PathNode.QuadTo(154.2f, 990.1f, 150.2f, 957.1f),
                        PathNode.QuadTo(146.2f, 924.0f, 146.2f, 849.0f),
                        PathNode.VerticalTo(315.8f),
                        PathNode.QuadTo(146.2f, 238.1f, 149.7f, 205.6f),
                        PathNode.QuadTo(153.2f, 173.0f, 169.6f, 149.6f),
                        PathNode.QuadTo(185.6f, 128.3f, 209.2f, 114.7f),
                        PathNode.QuadTo(232.7f, 101.2f, 258.1f, 99.5f),
                        PathNode.QuadTo(286.2f, 96.8f, 318.4f, 110.2f),
                        PathNode.QuadTo(350.6f, 123.5f, 413.2f, 160.2f),
                        PathNode.Close,
                        PathNode.MoveTo(236.1f, 197.6f),
                        PathNode.QuadTo(230.4f, 205.9f, 228.8f, 230.2f),
                        PathNode.QuadTo(227.1f, 254.5f, 227.1f, 315.8f),
                        PathNode.VerticalTo(849.0f),
                        PathNode.QuadTo(227.1f, 908.6f, 228.8f, 931.5f),
                        PathNode.QuadTo(230.4f, 954.5f, 235.1f, 962.4f),
                        PathNode.QuadTo(240.7f, 970.3f, 249.0f, 975.8f),
                        PathNode.QuadTo(257.2f, 981.3f, 267.5f, 982.3f),
                        PathNode.QuadTo(276.1f, 983.3f, 298.5f, 972.1f),
                        PathNode.QuadTo(321.0f, 961.0f, 371.9f, 932.3f),
                        PathNode.LineTo(834.8f, 665.1f),
                        PathNode.QuadTo(883.4f, 637.5f, 903.2f, 623.6f),
                        PathNode.QuadTo(923.0f, 609.8f, 928.6f, 600.9f),
                        PathNode.QuadTo(939.2f, 582.7f, 930.3f, 562.8f),
                        PathNode.QuadTo(925.7f, 553.9f, 905.6f, 539.9f),
                        PathNode.QuadTo(885.4f, 526.0f, 834.8f, 497.6f),
                        PathNode.LineTo(371.9f, 230.4f),
                        PathNode.QuadTo(319.3f, 200.1f, 298.4f, 189.9f),
                        PathNode.QuadTo(277.4f, 179.8f, 267.5f, 180.8f),
                        PathNode.QuadTo(245.4f, 182.8f, 236.1f, 197.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playNormal!!
    }

private var _playNormal: ImageVector? = null

val MiuixIcons.Regular.Play: ImageVector
    get() {
        if (_playRegular != null) return _playRegular!!
        _playRegular = ImageVector.Builder(
            name = "Play.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1168.8f,
            viewportHeight = 1168.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1168.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(419.9f, 161.4f),
                        PathNode.LineTo(878.9f, 427.4f),
                        PathNode.QuadTo(944.9f, 465.4f, 971.9f, 485.4f),
                        PathNode.QuadTo(998.9f, 505.4f, 1010.9f, 532.4f),
                        PathNode.QuadTo(1021.9f, 557.4f, 1021.9f, 585.4f),
                        PathNode.QuadTo(1021.9f, 613.4f, 1010.9f, 638.4f),
                        PathNode.QuadTo(998.9f, 664.4f, 971.9f, 684.9f),
                        PathNode.QuadTo(944.9f, 705.4f, 878.9f, 742.4f),
                        PathNode.LineTo(419.9f, 1008.4f),
                        PathNode.QuadTo(357.9f, 1044.4f, 324.9f, 1057.9f),
                        PathNode.QuadTo(291.9f, 1071.4f, 263.9f, 1068.4f),
                        PathNode.QuadTo(236.9f, 1066.4f, 212.9f, 1052.9f),
                        PathNode.QuadTo(188.9f, 1039.4f, 171.9f, 1016.4f),
                        PathNode.QuadTo(154.9f, 994.4f, 150.9f, 960.9f),
                        PathNode.QuadTo(146.9f, 927.4f, 146.9f, 851.4f),
                        PathNode.VerticalTo(320.4f),
                        PathNode.QuadTo(146.9f, 242.4f, 150.4f, 209.4f),
                        PathNode.QuadTo(153.9f, 176.4f, 170.9f, 152.4f),
                        PathNode.QuadTo(187.9f, 130.4f, 212.4f, 116.4f),
                        PathNode.QuadTo(236.9f, 102.4f, 262.9f, 100.4f),
                        PathNode.QuadTo(291.9f, 97.4f, 324.4f, 110.9f),
                        PathNode.QuadTo(356.9f, 124.4f, 419.9f, 161.4f),
                        PathNode.Close,
                        PathNode.MoveTo(244.9f, 205.4f),
                        PathNode.QuadTo(239.9f, 212.4f, 238.4f, 235.9f),
                        PathNode.QuadTo(236.9f, 259.4f, 236.9f, 320.4f),
                        PathNode.VerticalTo(851.4f),
                        PathNode.QuadTo(236.9f, 910.4f, 238.4f, 932.4f),
                        PathNode.QuadTo(239.9f, 954.4f, 243.9f, 961.4f),
                        PathNode.QuadTo(248.9f, 968.4f, 256.4f, 973.4f),
                        PathNode.QuadTo(263.9f, 978.4f, 272.9f, 979.4f),
                        PathNode.QuadTo(279.9f, 980.4f, 301.9f, 969.4f),
                        PathNode.QuadTo(323.9f, 958.4f, 373.9f, 930.4f),
                        PathNode.LineTo(834.9f, 664.4f),
                        PathNode.QuadTo(881.9f, 637.4f, 901.4f, 623.9f),
                        PathNode.QuadTo(920.9f, 610.4f, 925.9f, 602.4f),
                        PathNode.QuadTo(935.9f, 586.4f, 927.9f, 568.4f),
                        PathNode.QuadTo(923.9f, 560.4f, 904.4f, 546.9f),
                        PathNode.QuadTo(884.9f, 533.4f, 834.9f, 505.4f),
                        PathNode.LineTo(373.9f, 239.4f),
                        PathNode.QuadTo(321.9f, 209.4f, 301.9f, 199.4f),
                        PathNode.QuadTo(281.9f, 189.4f, 272.9f, 190.4f),
                        PathNode.QuadTo(253.9f, 192.4f, 244.9f, 205.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playRegular!!
    }

private var _playRegular: ImageVector? = null

val MiuixIcons.Medium.Play: ImageVector
    get() {
        if (_playMedium != null) return _playMedium!!
        _playMedium = ImageVector.Builder(
            name = "Play.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1188.6f,
            viewportHeight = 1188.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1188.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(433.9f, 164.8f),
                        PathNode.LineTo(892.9f, 430.8f),
                        PathNode.QuadTo(960.1f, 468.8f, 988.0f, 489.7f),
                        PathNode.QuadTo(1015.8f, 510.6f, 1028.4f, 538.8f),
                        PathNode.QuadTo(1039.4f, 565.5f, 1039.4f, 595.3f),
                        PathNode.QuadTo(1039.4f, 625.0f, 1028.4f, 651.8f),
                        PathNode.QuadTo(1015.8f, 679.6f, 988.0f, 700.4f),
                        PathNode.QuadTo(960.1f, 721.2f, 892.9f, 758.8f),
                        PathNode.LineTo(433.9f, 1024.8f),
                        PathNode.QuadTo(371.3f, 1061.3f, 337.4f, 1075.4f),
                        PathNode.QuadTo(303.5f, 1089.5f, 273.2f, 1086.5f),
                        PathNode.QuadTo(244.4f, 1083.9f, 219.0f, 1069.5f),
                        PathNode.QuadTo(193.5f, 1055.2f, 175.9f, 1031.0f),
                        PathNode.QuadTo(157.7f, 1007.2f, 153.4f, 972.5f),
                        PathNode.QuadTo(149.1f, 937.9f, 149.1f, 861.3f),
                        PathNode.VerticalTo(330.3f),
                        PathNode.QuadTo(149.1f, 251.7f, 153.2f, 217.5f),
                        PathNode.QuadTo(157.3f, 183.3f, 174.9f, 157.6f),
                        PathNode.QuadTo(193.1f, 134.4f, 218.8f, 119.5f),
                        PathNode.QuadTo(244.4f, 104.6f, 272.8f, 102.6f),
                        PathNode.QuadTo(303.5f, 99.0f, 336.9f, 113.1f),
                        PathNode.QuadTo(370.3f, 127.2f, 433.9f, 164.8f),
                        PathNode.Close,
                        PathNode.MoveTo(261.3f, 220.6f),
                        PathNode.QuadTo(257.4f, 226.4f, 256.2f, 251.7f),
                        PathNode.QuadTo(255.0f, 276.9f, 255.0f, 330.3f),
                        PathNode.VerticalTo(861.3f),
                        PathNode.QuadTo(255.0f, 913.8f, 256.2f, 937.9f),
                        PathNode.QuadTo(257.4f, 961.9f, 260.8f, 967.8f),
                        PathNode.QuadTo(264.1f, 973.6f, 270.4f, 977.4f),
                        PathNode.QuadTo(276.7f, 981.2f, 284.0f, 981.6f),
                        PathNode.QuadTo(289.8f, 982.0f, 313.0f, 970.2f),
                        PathNode.QuadTo(336.1f, 958.3f, 380.3f, 933.8f),
                        PathNode.LineTo(841.3f, 667.8f),
                        PathNode.QuadTo(883.5f, 643.2f, 904.5f, 629.4f),
                        PathNode.QuadTo(925.5f, 615.6f, 929.9f, 608.8f),
                        PathNode.QuadTo(937.5f, 595.7f, 931.3f, 581.8f),
                        PathNode.QuadTo(927.3f, 574.4f, 909.0f, 562.4f),
                        PathNode.QuadTo(890.7f, 550.3f, 841.3f, 521.8f),
                        PathNode.LineTo(380.3f, 255.8f),
                        PathNode.QuadTo(334.7f, 229.3f, 313.3f, 218.4f),
                        PathNode.QuadTo(291.8f, 207.5f, 284.5f, 207.9f),
                        PathNode.QuadTo(268.5f, 208.8f, 261.3f, 220.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playMedium!!
    }

private var _playMedium: ImageVector? = null

val MiuixIcons.Demibold.Play: ImageVector
    get() {
        if (_playDemibold != null) return _playDemibold!!
        _playDemibold = ImageVector.Builder(
            name = "Play.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1202.4f,
            viewportHeight = 1202.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1202.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(443.7f, 167.2f),
                        PathNode.LineTo(902.7f, 433.2f),
                        PathNode.QuadTo(970.7f, 471.2f, 999.2f, 492.7f),
                        PathNode.QuadTo(1027.7f, 514.2f, 1040.7f, 543.2f),
                        PathNode.QuadTo(1051.7f, 571.2f, 1051.7f, 602.2f),
                        PathNode.QuadTo(1051.7f, 633.2f, 1040.7f, 661.2f),
                        PathNode.QuadTo(1027.7f, 690.2f, 999.2f, 711.2f),
                        PathNode.QuadTo(970.7f, 732.2f, 902.7f, 770.2f),
                        PathNode.LineTo(443.7f, 1036.2f),
                        PathNode.QuadTo(380.7f, 1073.2f, 346.2f, 1087.7f),
                        PathNode.QuadTo(311.7f, 1102.2f, 279.7f, 1099.2f),
                        PathNode.QuadTo(249.7f, 1096.2f, 223.2f, 1081.2f),
                        PathNode.QuadTo(196.7f, 1066.2f, 178.7f, 1041.2f),
                        PathNode.QuadTo(159.7f, 1016.2f, 155.2f, 980.7f),
                        PathNode.QuadTo(150.7f, 945.2f, 150.7f, 868.2f),
                        PathNode.VerticalTo(337.2f),
                        PathNode.QuadTo(150.7f, 258.2f, 155.2f, 223.2f),
                        PathNode.QuadTo(159.7f, 188.2f, 177.7f, 161.2f),
                        PathNode.QuadTo(196.7f, 137.2f, 223.2f, 121.7f),
                        PathNode.QuadTo(249.7f, 106.2f, 279.7f, 104.2f),
                        PathNode.QuadTo(311.7f, 100.2f, 345.7f, 114.7f),
                        PathNode.QuadTo(379.7f, 129.2f, 443.7f, 167.2f),
                        PathNode.Close,
                        PathNode.MoveTo(272.7f, 231.2f),
                        PathNode.QuadTo(269.7f, 236.2f, 268.7f, 262.7f),
                        PathNode.QuadTo(267.7f, 289.2f, 267.7f, 337.2f),
                        PathNode.VerticalTo(868.2f),
                        PathNode.QuadTo(267.7f, 916.2f, 268.7f, 941.7f),
                        PathNode.QuadTo(269.7f, 967.2f, 272.7f, 972.2f),
                        PathNode.QuadTo(274.7f, 977.2f, 280.2f, 980.2f),
                        PathNode.QuadTo(285.7f, 983.2f, 291.7f, 983.2f),
                        PathNode.QuadTo(296.7f, 983.2f, 320.7f, 970.7f),
                        PathNode.QuadTo(344.7f, 958.2f, 384.7f, 936.2f),
                        PathNode.LineTo(845.7f, 670.2f),
                        PathNode.QuadTo(884.7f, 647.2f, 906.7f, 633.2f),
                        PathNode.QuadTo(928.7f, 619.2f, 932.7f, 613.2f),
                        PathNode.QuadTo(938.7f, 602.2f, 933.7f, 591.2f),
                        PathNode.QuadTo(929.7f, 584.2f, 912.2f, 573.2f),
                        PathNode.QuadTo(894.7f, 562.2f, 845.7f, 533.2f),
                        PathNode.LineTo(384.7f, 267.2f),
                        PathNode.QuadTo(343.7f, 243.2f, 321.2f, 231.7f),
                        PathNode.QuadTo(298.7f, 220.2f, 292.7f, 220.2f),
                        PathNode.QuadTo(278.7f, 220.2f, 272.7f, 231.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playDemibold!!
    }

private var _playDemibold: ImageVector? = null
