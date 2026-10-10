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

val MiuixIcons.Glass.Play: ImageVector
    get() = MiuixIcons.Glass.Regular.Play

val MiuixIcons.Glass.Light.Play: ImageVector
    get() {
        if (_playLight != null) return _playLight!!
        _playLight = ImageVector.Builder(
            name = "Play.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(396.9f, 155.1f),
                        PathNode.LineTo(862.2f, 424.6f),
                        PathNode.QuadTo(927.3f, 461.8f, 952.7f, 480.6f),
                        PathNode.QuadTo(978.2f, 499.5f, 989.7f, 523.9f),
                        PathNode.QuadTo(1000.3f, 546.6f, 1000.3f, 572.0f),
                        PathNode.QuadTo(1000.3f, 597.3f, 989.7f, 619.6f),
                        PathNode.QuadTo(978.2f, 643.4f, 952.9f, 662.4f),
                        PathNode.QuadTo(927.7f, 681.3f, 862.2f, 718.3f),
                        PathNode.LineTo(396.9f, 987.9f),
                        PathNode.QuadTo(334.5f, 1023.9f, 303.9f, 1036.7f),
                        PathNode.QuadTo(273.4f, 1049.5f, 247.6f, 1047.4f),
                        PathNode.QuadTo(222.8f, 1045.4f, 201.3f, 1033.3f),
                        PathNode.QuadTo(179.7f, 1021.1f, 164.9f, 999.9f),
                        PathNode.QuadTo(150.2f, 979.2f, 146.4f, 947.0f),
                        PathNode.QuadTo(142.6f, 914.9f, 142.6f, 841.5f),
                        PathNode.LineTo(142.6f, 303.4f),
                        PathNode.QuadTo(142.6f, 226.8f, 145.9f, 195.1f),
                        PathNode.QuadTo(149.2f, 163.4f, 164.4f, 141.6f),
                        PathNode.QuadTo(179.2f, 121.4f, 200.5f, 109.2f),
                        PathNode.QuadTo(221.9f, 97.0f, 246.2f, 95.4f),
                        PathNode.QuadTo(272.5f, 93.3f, 304.1f, 106.4f),
                        PathNode.QuadTo(335.7f, 119.4f, 396.9f, 155.1f),
                        PathNode.Close,
                        PathNode.MoveTo(214.4f, 178.6f),
                        PathNode.QuadTo(208.0f, 189.2f, 206.1f, 214.7f),
                        PathNode.QuadTo(204.2f, 240.2f, 204.2f, 303.4f),
                        PathNode.LineTo(204.2f, 841.5f),
                        PathNode.QuadTo(204.2f, 904.1f, 206.1f, 928.5f),
                        PathNode.QuadTo(208.0f, 953.0f, 213.8f, 962.6f),
                        PathNode.QuadTo(220.2f, 972.3f, 230.5f, 978.9f),
                        PathNode.QuadTo(240.9f, 985.4f, 253.9f, 986.4f),
                        PathNode.QuadTo(265.4f, 987.4f, 288.7f, 976.2f),
                        PathNode.QuadTo(312.0f, 965.0f, 365.6f, 934.3f),
                        PathNode.LineTo(832.8f, 664.8f),
                        PathNode.QuadTo(885.6f, 634.6f, 906.0f, 620.5f),
                        PathNode.QuadTo(926.4f, 606.3f, 932.3f, 595.2f),
                        PathNode.QuadTo(944.9f, 572.5f, 933.4f, 548.8f),
                        PathNode.QuadTo(928.0f, 537.6f, 906.8f, 522.8f),
                        PathNode.QuadTo(885.5f, 508.0f, 832.4f, 478.2f),
                        PathNode.LineTo(365.6f, 208.6f),
                        PathNode.QuadTo(312.3f, 177.8f, 289.4f, 166.9f),
                        PathNode.QuadTo(266.5f, 156.0f, 253.9f, 157.0f),
                        PathNode.QuadTo(241.4f, 158.0f, 230.5f, 163.5f),
                        PathNode.QuadTo(219.7f, 169.0f, 214.4f, 178.6f),
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

val MiuixIcons.Glass.Normal.Play: ImageVector
    get() {
        if (_playNormal != null) return _playNormal!!
        _playNormal = ImageVector.Builder(
            name = "Play.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(403.8f, 150.5f),
                        PathNode.LineTo(864.7f, 417.6f),
                        PathNode.QuadTo(930.5f, 455.3f, 957.0f, 475.0f),
                        PathNode.QuadTo(983.5f, 494.7f, 995.4f, 520.8f),
                        PathNode.QuadTo(1006.2f, 545.1f, 1006.2f, 572.3f),
                        PathNode.QuadTo(1006.2f, 599.5f, 995.4f, 623.6f),
                        PathNode.QuadTo(983.5f, 648.9f, 957.1f, 668.9f),
                        PathNode.QuadTo(930.6f, 689.0f, 864.7f, 726.0f),
                        PathNode.LineTo(403.8f, 993.1f),
                        PathNode.QuadTo(341.7f, 1029.1f, 309.4f, 1042.4f),
                        PathNode.QuadTo(277.2f, 1055.6f, 249.9f, 1052.9f),
                        PathNode.QuadTo(223.6f, 1050.9f, 200.3f, 1037.8f),
                        PathNode.QuadTo(177.1f, 1024.8f, 160.8f, 1002.3f),
                        PathNode.QuadTo(144.5f, 980.7f, 140.6f, 947.6f),
                        PathNode.QuadTo(136.6f, 914.6f, 136.6f, 839.4f),
                        PathNode.LineTo(136.6f, 306.2f),
                        PathNode.QuadTo(136.6f, 228.6f, 140.1f, 196.0f),
                        PathNode.QuadTo(143.5f, 163.4f, 159.9f, 140.1f),
                        PathNode.QuadTo(176.2f, 118.7f, 199.8f, 105.2f),
                        PathNode.QuadTo(223.3f, 91.8f, 248.7f, 89.9f),
                        PathNode.QuadTo(276.9f, 87.2f, 309.1f, 100.6f),
                        PathNode.QuadTo(341.4f, 113.9f, 403.8f, 150.5f),
                        PathNode.Close,
                        PathNode.MoveTo(226.4f, 188.1f),
                        PathNode.QuadTo(221.0f, 196.2f, 219.4f, 220.4f),
                        PathNode.QuadTo(217.7f, 244.5f, 217.7f, 306.2f),
                        PathNode.LineTo(217.7f, 839.4f),
                        PathNode.QuadTo(217.7f, 899.5f, 219.4f, 922.3f),
                        PathNode.QuadTo(221.0f, 945.0f, 225.6f, 952.9f),
                        PathNode.QuadTo(231.0f, 960.7f, 239.4f, 966.2f),
                        PathNode.QuadTo(247.8f, 971.7f, 258.1f, 972.7f),
                        PathNode.QuadTo(266.4f, 973.7f, 288.9f, 962.6f),
                        PathNode.QuadTo(311.3f, 951.5f, 362.4f, 922.7f),
                        PathNode.LineTo(825.3f, 655.6f),
                        PathNode.QuadTo(874.1f, 627.6f, 893.9f, 613.9f),
                        PathNode.QuadTo(913.7f, 600.2f, 919.0f, 591.2f),
                        PathNode.QuadTo(929.8f, 573.1f, 920.7f, 553.3f),
                        PathNode.QuadTo(916.3f, 544.4f, 896.2f, 530.5f),
                        PathNode.QuadTo(876.2f, 516.5f, 825.2f, 488.0f),
                        PathNode.LineTo(362.4f, 220.9f),
                        PathNode.QuadTo(310.0f, 190.6f, 289.1f, 180.3f),
                        PathNode.QuadTo(268.2f, 170.0f, 258.1f, 171.0f),
                        PathNode.QuadTo(247.9f, 172.0f, 239.4f, 176.2f),
                        PathNode.QuadTo(230.9f, 180.3f, 226.4f, 188.1f),
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

val MiuixIcons.Glass.Regular.Play: ImageVector
    get() {
        if (_playRegular != null) return _playRegular!!
        _playRegular = ImageVector.Builder(
            name = "Play.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(406.9f, 148.4f),
                        PathNode.LineTo(865.9f, 414.4f),
                        PathNode.QuadTo(931.9f, 452.4f, 958.9f, 472.4f),
                        PathNode.QuadTo(985.9f, 492.4f, 997.9f, 519.4f),
                        PathNode.QuadTo(1008.9f, 544.4f, 1008.9f, 572.4f),
                        PathNode.QuadTo(1008.9f, 600.4f, 997.9f, 625.4f),
                        PathNode.QuadTo(985.9f, 651.4f, 958.9f, 671.9f),
                        PathNode.QuadTo(931.9f, 692.4f, 865.9f, 729.4f),
                        PathNode.LineTo(406.9f, 995.4f),
                        PathNode.QuadTo(344.9f, 1031.4f, 311.9f, 1044.9f),
                        PathNode.QuadTo(278.9f, 1058.4f, 250.9f, 1055.4f),
                        PathNode.QuadTo(223.9f, 1053.4f, 199.9f, 1039.9f),
                        PathNode.QuadTo(175.9f, 1026.4f, 158.9f, 1003.4f),
                        PathNode.QuadTo(141.9f, 981.4f, 137.9f, 947.9f),
                        PathNode.QuadTo(133.9f, 914.4f, 133.9f, 838.4f),
                        PathNode.LineTo(133.9f, 307.4f),
                        PathNode.QuadTo(133.9f, 229.4f, 137.4f, 196.4f),
                        PathNode.QuadTo(140.9f, 163.4f, 157.9f, 139.4f),
                        PathNode.QuadTo(174.9f, 117.4f, 199.4f, 103.4f),
                        PathNode.QuadTo(223.9f, 89.4f, 249.9f, 87.4f),
                        PathNode.QuadTo(278.9f, 84.4f, 311.4f, 97.9f),
                        PathNode.QuadTo(343.9f, 111.4f, 406.9f, 148.4f),
                        PathNode.Close,
                        PathNode.MoveTo(231.9f, 192.4f),
                        PathNode.QuadTo(226.9f, 199.4f, 225.4f, 222.9f),
                        PathNode.QuadTo(223.9f, 246.4f, 223.9f, 307.4f),
                        PathNode.LineTo(223.9f, 838.4f),
                        PathNode.QuadTo(223.9f, 897.4f, 225.4f, 919.4f),
                        PathNode.QuadTo(226.9f, 941.4f, 230.9f, 948.4f),
                        PathNode.QuadTo(235.9f, 955.4f, 243.4f, 960.4f),
                        PathNode.QuadTo(250.9f, 965.4f, 259.9f, 966.4f),
                        PathNode.QuadTo(266.9f, 967.4f, 288.9f, 956.4f),
                        PathNode.QuadTo(310.9f, 945.4f, 360.9f, 917.4f),
                        PathNode.LineTo(821.9f, 651.4f),
                        PathNode.QuadTo(868.9f, 624.4f, 888.4f, 610.9f),
                        PathNode.QuadTo(907.9f, 597.4f, 912.9f, 589.4f),
                        PathNode.QuadTo(922.9f, 573.4f, 914.9f, 555.4f),
                        PathNode.QuadTo(910.9f, 547.4f, 891.4f, 533.9f),
                        PathNode.QuadTo(871.9f, 520.4f, 821.9f, 492.4f),
                        PathNode.LineTo(360.9f, 226.4f),
                        PathNode.QuadTo(308.9f, 196.4f, 288.9f, 186.4f),
                        PathNode.QuadTo(268.9f, 176.4f, 259.9f, 177.4f),
                        PathNode.QuadTo(250.9f, 178.4f, 243.4f, 181.9f),
                        PathNode.QuadTo(235.9f, 185.4f, 231.9f, 192.4f),
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

val MiuixIcons.Glass.Medium.Play: ImageVector
    get() {
        if (_playMedium != null) return _playMedium!!
        _playMedium = ImageVector.Builder(
            name = "Play.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(411.0f, 141.4f),
                        PathNode.LineTo(869.7f, 407.4f),
                        PathNode.QuadTo(936.8f, 445.9f, 964.6f, 466.8f),
                        PathNode.QuadTo(992.4f, 487.6f, 1005.2f, 515.9f),
                        PathNode.QuadTo(1016.8f, 542.5f, 1016.8f, 572.2f),
                        PathNode.QuadTo(1016.8f, 601.8f, 1005.2f, 628.4f),
                        PathNode.QuadTo(992.4f, 656.0f, 964.6f, 677.2f),
                        PathNode.QuadTo(936.8f, 698.4f, 869.7f, 735.9f),
                        PathNode.LineTo(411.0f, 1001.9f),
                        PathNode.QuadTo(347.4f, 1038.7f, 313.7f, 1052.6f),
                        PathNode.QuadTo(280.0f, 1066.5f, 250.1f, 1063.5f),
                        PathNode.QuadTo(221.2f, 1061.0f, 195.7f, 1046.6f),
                        PathNode.QuadTo(170.3f, 1032.1f, 152.4f, 1007.8f),
                        PathNode.QuadTo(134.6f, 984.1f, 130.4f, 949.6f),
                        PathNode.QuadTo(126.1f, 915.0f, 126.1f, 838.2f),
                        PathNode.LineTo(126.1f, 307.2f),
                        PathNode.QuadTo(126.1f, 228.6f, 130.0f, 194.3f),
                        PathNode.QuadTo(133.9f, 159.9f, 151.7f, 134.6f),
                        PathNode.QuadTo(169.5f, 111.5f, 195.5f, 96.5f),
                        PathNode.QuadTo(221.5f, 81.6f, 249.4f, 79.3f),
                        PathNode.QuadTo(280.0f, 76.3f, 313.5f, 90.1f),
                        PathNode.QuadTo(346.9f, 103.9f, 411.0f, 141.4f),
                        PathNode.Close,
                        PathNode.MoveTo(238.1f, 197.6f),
                        PathNode.QuadTo(234.2f, 204.3f, 233.0f, 227.3f),
                        PathNode.QuadTo(231.8f, 250.2f, 231.8f, 307.2f),
                        PathNode.LineTo(231.8f, 838.2f),
                        PathNode.QuadTo(231.8f, 894.2f, 233.0f, 916.1f),
                        PathNode.QuadTo(234.2f, 937.9f, 237.4f, 944.4f),
                        PathNode.QuadTo(241.3f, 950.0f, 247.3f, 953.9f),
                        PathNode.QuadTo(253.4f, 957.9f, 261.0f, 958.3f),
                        PathNode.QuadTo(267.7f, 958.5f, 289.1f, 947.6f),
                        PathNode.QuadTo(310.4f, 936.8f, 356.9f, 910.4f),
                        PathNode.LineTo(817.9f, 644.4f),
                        PathNode.QuadTo(862.4f, 618.8f, 882.3f, 605.5f),
                        PathNode.QuadTo(902.3f, 592.3f, 906.4f, 585.6f),
                        PathNode.QuadTo(914.8f, 572.6f, 907.9f, 558.4f),
                        PathNode.QuadTo(904.2f, 551.5f, 884.5f, 538.4f),
                        PathNode.QuadTo(864.9f, 525.3f, 818.1f, 498.9f),
                        PathNode.LineTo(356.9f, 232.9f),
                        PathNode.QuadTo(307.8f, 204.5f, 288.4f, 194.5f),
                        PathNode.QuadTo(268.9f, 184.5f, 261.6f, 185.0f),
                        PathNode.QuadTo(253.9f, 185.5f, 247.6f, 188.4f),
                        PathNode.QuadTo(241.3f, 191.4f, 238.1f, 197.6f),
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

val MiuixIcons.Glass.Demibold.Play: ImageVector
    get() {
        if (_playDemibold != null) return _playDemibold!!
        _playDemibold = ImageVector.Builder(
            name = "Play.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(413.8f, 136.5f),
                        PathNode.LineTo(872.4f, 402.5f),
                        PathNode.QuadTo(940.2f, 441.4f, 968.6f, 462.8f),
                        PathNode.QuadTo(997.0f, 484.2f, 1010.3f, 513.5f),
                        PathNode.QuadTo(1022.3f, 541.2f, 1022.3f, 572.0f),
                        PathNode.QuadTo(1022.3f, 602.7f, 1010.3f, 630.5f),
                        PathNode.QuadTo(997.0f, 659.2f, 968.6f, 680.9f),
                        PathNode.QuadTo(940.2f, 702.5f, 872.4f, 740.5f),
                        PathNode.LineTo(413.8f, 1006.5f),
                        PathNode.QuadTo(349.1f, 1043.8f, 314.9f, 1058.0f),
                        PathNode.QuadTo(280.8f, 1072.2f, 249.6f, 1069.2f),
                        PathNode.QuadTo(219.3f, 1066.3f, 192.8f, 1051.2f),
                        PathNode.QuadTo(166.3f, 1036.1f, 147.9f, 1010.8f),
                        PathNode.QuadTo(129.5f, 986.0f, 125.1f, 950.7f),
                        PathNode.QuadTo(120.6f, 915.4f, 120.6f, 838.0f),
                        PathNode.LineTo(120.6f, 307.0f),
                        PathNode.QuadTo(120.6f, 228.1f, 124.8f, 192.8f),
                        PathNode.QuadTo(129.0f, 157.5f, 147.4f, 131.2f),
                        PathNode.QuadTo(165.7f, 107.3f, 192.8f, 91.7f),
                        PathNode.QuadTo(219.8f, 76.1f, 249.0f, 73.6f),
                        PathNode.QuadTo(280.8f, 70.6f, 314.9f, 84.6f),
                        PathNode.QuadTo(349.0f, 98.6f, 413.8f, 136.5f),
                        PathNode.Close,
                        PathNode.MoveTo(242.5f, 201.2f),
                        PathNode.QuadTo(239.3f, 207.7f, 238.3f, 230.3f),
                        PathNode.QuadTo(237.3f, 252.9f, 237.3f, 307.0f),
                        PathNode.LineTo(237.3f, 838.0f),
                        PathNode.QuadTo(237.3f, 891.9f, 238.3f, 913.7f),
                        PathNode.QuadTo(239.3f, 935.5f, 242.0f, 941.5f),
                        PathNode.QuadTo(245.1f, 946.2f, 250.1f, 949.4f),
                        PathNode.QuadTo(255.1f, 952.6f, 261.8f, 952.6f),
                        PathNode.QuadTo(268.3f, 952.3f, 289.2f, 941.5f),
                        PathNode.QuadTo(310.0f, 930.7f, 354.0f, 905.5f),
                        PathNode.LineTo(815.0f, 639.5f),
                        PathNode.QuadTo(857.9f, 614.8f, 878.1f, 601.7f),
                        PathNode.QuadTo(898.3f, 588.7f, 901.9f, 583.0f),
                        PathNode.QuadTo(909.1f, 572.1f, 903.0f, 560.5f),
                        PathNode.QuadTo(899.4f, 554.3f, 879.7f, 541.5f),
                        PathNode.QuadTo(860.0f, 528.7f, 815.5f, 503.5f),
                        PathNode.LineTo(354.0f, 237.5f),
                        PathNode.QuadTo(307.1f, 210.2f, 288.0f, 200.2f),
                        PathNode.QuadTo(268.9f, 190.2f, 262.7f, 190.3f),
                        PathNode.QuadTo(256.0f, 190.4f, 250.6f, 193.0f),
                        PathNode.QuadTo(245.1f, 195.5f, 242.5f, 201.2f),
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
