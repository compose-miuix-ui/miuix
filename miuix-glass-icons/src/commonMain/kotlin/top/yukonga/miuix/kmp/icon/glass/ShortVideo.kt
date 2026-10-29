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

val MiuixIcons.Glass.ShortVideo: ImageVector
    get() = MiuixIcons.Glass.Regular.ShortVideo

val MiuixIcons.Glass.Light.ShortVideo: ImageVector
    get() {
        if (_shortVideoLight != null) return _shortVideoLight!!
        _shortVideoLight = ImageVector.Builder(
            name = "ShortVideo.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(534.2f, 114.0f),
                        PathNode.LineTo(821.2f, 281.0f),
                        PathNode.QuadTo(887.8f, 318.5f, 914.4f, 336.6f),
                        PathNode.QuadTo(941.1f, 354.8f, 954.8f, 374.0f),
                        PathNode.QuadTo(983.5f, 417.0f, 980.5f, 471.5f),
                        PathNode.QuadTo(979.0f, 495.2f, 965.0f, 524.0f),
                        PathNode.QuadTo(951.0f, 552.9f, 912.1f, 620.8f),
                        PathNode.QuadTo(910.1f, 624.9f, 910.9f, 627.9f),
                        PathNode.QuadTo(911.6f, 631.0f, 915.2f, 633.0f),
                        PathNode.LineTo(931.9f, 643.8f),
                        PathNode.QuadTo(957.2f, 661.3f, 969.2f, 671.1f),
                        PathNode.QuadTo(981.2f, 681.0f, 988.8f, 693.0f),
                        PathNode.QuadTo(1019.1f, 736.9f, 1014.5f, 790.5f),
                        PathNode.QuadTo(1013.0f, 814.3f, 999.6f, 841.5f),
                        PathNode.QuadTo(986.3f, 868.8f, 947.3f, 938.8f),
                        PathNode.QuadTo(906.8f, 1007.4f, 889.6f, 1032.3f),
                        PathNode.QuadTo(872.5f, 1057.2f, 853.3f, 1070.3f),
                        PathNode.QuadTo(831.6f, 1085.4f, 806.8f, 1091.8f),
                        PathNode.QuadTo(782.0f, 1098.1f, 755.8f, 1096.1f),
                        PathNode.QuadTo(731.5f, 1095.1f, 702.9f, 1081.0f),
                        PathNode.QuadTo(674.2f, 1067.0f, 608.5f, 1028.9f),
                        PathNode.LineTo(321.5f, 862.9f),
                        PathNode.QuadTo(257.9f, 826.8f, 230.0f, 807.9f),
                        PathNode.QuadTo(202.1f, 789.1f, 188.0f, 768.9f),
                        PathNode.QuadTo(159.2f, 726.4f, 162.2f, 671.3f),
                        PathNode.QuadTo(163.2f, 650.2f, 175.1f, 624.7f),
                        PathNode.QuadTo(187.1f, 599.2f, 217.8f, 544.9f),
                        PathNode.LineTo(230.1f, 522.6f),
                        PathNode.QuadTo(232.1f, 518.5f, 231.6f, 515.5f),
                        PathNode.QuadTo(231.1f, 512.4f, 227.5f, 510.4f),
                        PathNode.QuadTo(196.6f, 490.8f, 180.3f, 477.3f),
                        PathNode.QuadTo(164.0f, 463.9f, 154.0f, 449.9f),
                        PathNode.QuadTo(139.3f, 428.6f, 132.8f, 403.6f),
                        PathNode.QuadTo(126.2f, 378.5f, 128.2f, 353.3f),
                        PathNode.QuadTo(129.8f, 328.5f, 143.1f, 300.8f),
                        PathNode.QuadTo(156.4f, 273.1f, 195.4f, 205.1f),
                        PathNode.QuadTo(236.0f, 136.4f, 253.1f, 111.5f),
                        PathNode.QuadTo(270.2f, 86.6f, 289.4f, 73.5f),
                        PathNode.QuadTo(334.9f, 42.8f, 387.0f, 46.8f),
                        PathNode.QuadTo(410.8f, 48.3f, 438.5f, 61.6f),
                        PathNode.QuadTo(466.2f, 75.0f, 534.2f, 114.0f),
                        PathNode.Close,
                        PathNode.MoveTo(476.9f, 458.9f),
                        PathNode.LineTo(476.9f, 683.4f),
                        PathNode.QuadTo(476.9f, 695.5f, 483.5f, 703.6f),
                        PathNode.QuadTo(490.1f, 711.8f, 500.5f, 713.3f),
                        PathNode.QuadTo(510.9f, 714.9f, 521.0f, 708.9f),
                        PathNode.LineTo(715.8f, 597.3f),
                        PathNode.QuadTo(725.9f, 591.3f, 729.4f, 581.4f),
                        PathNode.QuadTo(732.9f, 571.4f, 729.4f, 561.5f),
                        PathNode.QuadTo(725.9f, 551.5f, 715.8f, 545.5f),
                        PathNode.LineTo(521.0f, 434.0f),
                        PathNode.QuadTo(510.9f, 428.0f, 500.5f, 429.5f),
                        PathNode.QuadTo(490.1f, 431.1f, 483.5f, 439.2f),
                        PathNode.QuadTo(476.9f, 447.3f, 476.9f, 458.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _shortVideoLight!!
    }

private var _shortVideoLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ShortVideo: ImageVector
    get() {
        if (_shortVideoNormal != null) return _shortVideoNormal!!
        _shortVideoNormal = ImageVector.Builder(
            name = "ShortVideo.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(538.8f, 106.0f),
                        PathNode.LineTo(825.8f, 273.0f),
                        PathNode.QuadTo(892.6f, 310.9f, 920.2f, 329.6f),
                        PathNode.QuadTo(947.8f, 348.3f, 962.4f, 368.8f),
                        PathNode.QuadTo(992.7f, 414.5f, 989.7f, 472.1f),
                        PathNode.QuadTo(987.8f, 496.7f, 973.8f, 526.0f),
                        PathNode.QuadTo(959.8f, 555.3f, 924.3f, 617.1f),
                        PathNode.QuadTo(922.3f, 621.8f, 923.2f, 625.2f),
                        PathNode.QuadTo(924.1f, 628.5f, 928.0f, 630.5f),
                        PathNode.LineTo(942.8f, 640.1f),
                        PathNode.QuadTo(964.5f, 655.2f, 976.5f, 665.5f),
                        PathNode.QuadTo(988.5f, 675.8f, 996.4f, 687.8f),
                        PathNode.QuadTo(1028.5f, 733.8f, 1023.7f, 791.1f),
                        PathNode.QuadTo(1021.8f, 816.4f, 1008.0f, 844.9f),
                        PathNode.QuadTo(994.2f, 873.3f, 955.2f, 943.3f),
                        PathNode.QuadTo(914.4f, 1012.9f, 896.6f, 1038.6f),
                        PathNode.QuadTo(878.9f, 1064.2f, 858.5f, 1077.9f),
                        PathNode.QuadTo(835.9f, 1093.7f, 809.2f, 1100.5f),
                        PathNode.QuadTo(782.6f, 1107.3f, 755.1f, 1105.3f),
                        PathNode.QuadTo(729.7f, 1104.3f, 700.1f, 1089.9f),
                        PathNode.QuadTo(670.5f, 1075.5f, 603.9f, 1036.8f),
                        PathNode.LineTo(316.9f, 870.8f),
                        PathNode.QuadTo(252.3f, 834.1f, 223.7f, 814.8f),
                        PathNode.QuadTo(195.0f, 795.5f, 180.3f, 774.1f),
                        PathNode.QuadTo(150.0f, 729.2f, 153.0f, 670.7f),
                        PathNode.QuadTo(154.0f, 649.0f, 165.6f, 623.5f),
                        PathNode.QuadTo(177.3f, 598.0f, 206.1f, 547.3f),
                        PathNode.LineTo(217.5f, 526.6f),
                        PathNode.QuadTo(219.5f, 521.9f, 219.0f, 518.5f),
                        PathNode.QuadTo(218.5f, 515.2f, 214.7f, 513.2f),
                        PathNode.QuadTo(187.1f, 495.3f, 171.7f, 482.2f),
                        PathNode.QuadTo(156.3f, 469.1f, 146.3f, 455.1f),
                        PathNode.QuadTo(130.7f, 432.6f, 123.9f, 405.9f),
                        PathNode.QuadTo(117.0f, 379.1f, 119.0f, 352.7f),
                        PathNode.QuadTo(120.9f, 326.4f, 134.7f, 297.4f),
                        PathNode.QuadTo(148.4f, 268.5f, 187.4f, 200.5f),
                        PathNode.QuadTo(228.3f, 130.9f, 246.0f, 105.3f),
                        PathNode.QuadTo(263.8f, 79.6f, 284.2f, 65.9f),
                        PathNode.QuadTo(332.1f, 33.6f, 387.6f, 37.6f),
                        PathNode.QuadTo(412.9f, 39.5f, 441.8f, 53.2f),
                        PathNode.QuadTo(470.8f, 67.0f, 538.8f, 106.0f),
                        PathNode.Close,
                        PathNode.MoveTo(471.3f, 453.1f),
                        PathNode.LineTo(471.3f, 688.9f),
                        PathNode.QuadTo(471.3f, 701.6f, 478.6f, 710.4f),
                        PathNode.QuadTo(485.8f, 719.1f, 496.9f, 720.9f),
                        PathNode.QuadTo(508.1f, 722.8f, 518.8f, 716.8f),
                        PathNode.LineTo(723.4f, 599.4f),
                        PathNode.QuadTo(734.1f, 593.4f, 737.6f, 582.4f),
                        PathNode.QuadTo(741.1f, 571.4f, 737.6f, 560.4f),
                        PathNode.QuadTo(734.1f, 549.4f, 723.4f, 543.4f),
                        PathNode.LineTo(518.8f, 426.0f),
                        PathNode.QuadTo(508.1f, 420.0f, 496.9f, 421.9f),
                        PathNode.QuadTo(485.8f, 423.8f, 478.6f, 432.5f),
                        PathNode.QuadTo(471.3f, 441.2f, 471.3f, 453.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _shortVideoNormal!!
    }

private var _shortVideoNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ShortVideo: ImageVector
    get() {
        if (_shortVideoRegular != null) return _shortVideoRegular!!
        _shortVideoRegular = ImageVector.Builder(
            name = "ShortVideo.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(540.8f, 102.4f),
                        PathNode.LineTo(827.8f, 269.4f),
                        PathNode.QuadTo(894.8f, 307.4f, 922.8f, 326.4f),
                        PathNode.QuadTo(950.8f, 345.4f, 965.8f, 366.4f),
                        PathNode.QuadTo(996.8f, 413.4f, 993.8f, 472.4f),
                        PathNode.QuadTo(991.8f, 497.4f, 977.8f, 526.9f),
                        PathNode.QuadTo(963.8f, 556.4f, 929.8f, 615.4f),
                        PathNode.QuadTo(927.8f, 620.4f, 928.8f, 623.9f),
                        PathNode.QuadTo(929.8f, 627.4f, 933.8f, 629.4f),
                        PathNode.LineTo(947.8f, 638.4f),
                        PathNode.QuadTo(967.8f, 652.4f, 979.8f, 662.9f),
                        PathNode.QuadTo(991.8f, 673.4f, 999.8f, 685.4f),
                        PathNode.QuadTo(1032.8f, 732.4f, 1027.8f, 791.4f),
                        PathNode.QuadTo(1025.8f, 817.4f, 1011.8f, 846.4f),
                        PathNode.QuadTo(997.8f, 875.4f, 958.8f, 945.4f),
                        PathNode.QuadTo(917.8f, 1015.4f, 899.8f, 1041.4f),
                        PathNode.QuadTo(881.8f, 1067.4f, 860.8f, 1081.4f),
                        PathNode.QuadTo(837.8f, 1097.4f, 810.3f, 1104.4f),
                        PathNode.QuadTo(782.8f, 1111.4f, 754.8f, 1109.4f),
                        PathNode.QuadTo(728.8f, 1108.4f, 698.8f, 1093.9f),
                        PathNode.QuadTo(668.8f, 1079.4f, 601.8f, 1040.4f),
                        PathNode.LineTo(314.8f, 874.4f),
                        PathNode.QuadTo(249.8f, 837.4f, 220.8f, 817.9f),
                        PathNode.QuadTo(191.8f, 798.4f, 176.8f, 776.4f),
                        PathNode.QuadTo(145.8f, 730.4f, 148.8f, 670.4f),
                        PathNode.QuadTo(149.8f, 648.4f, 161.3f, 622.9f),
                        PathNode.QuadTo(172.8f, 597.4f, 200.8f, 548.4f),
                        PathNode.LineTo(211.8f, 528.4f),
                        PathNode.QuadTo(213.8f, 523.4f, 213.3f, 519.9f),
                        PathNode.QuadTo(212.8f, 516.4f, 208.8f, 514.4f),
                        PathNode.QuadTo(182.8f, 497.4f, 167.8f, 484.4f),
                        PathNode.QuadTo(152.8f, 471.4f, 142.8f, 457.4f),
                        PathNode.QuadTo(126.8f, 434.4f, 119.8f, 406.9f),
                        PathNode.QuadTo(112.8f, 379.4f, 114.8f, 352.4f),
                        PathNode.QuadTo(116.8f, 325.4f, 130.8f, 295.9f),
                        PathNode.QuadTo(144.8f, 266.4f, 183.8f, 198.4f),
                        PathNode.QuadTo(224.8f, 128.4f, 242.8f, 102.4f),
                        PathNode.QuadTo(260.8f, 76.4f, 281.8f, 62.4f),
                        PathNode.QuadTo(330.8f, 29.4f, 387.8f, 33.4f),
                        PathNode.QuadTo(413.8f, 35.4f, 443.3f, 49.4f),
                        PathNode.QuadTo(472.8f, 63.4f, 540.8f, 102.4f),
                        PathNode.Close,
                        PathNode.MoveTo(468.8f, 450.4f),
                        PathNode.LineTo(468.8f, 691.4f),
                        PathNode.QuadTo(468.8f, 704.4f, 476.3f, 713.4f),
                        PathNode.QuadTo(483.8f, 722.4f, 495.3f, 724.4f),
                        PathNode.QuadTo(506.8f, 726.4f, 517.8f, 720.4f),
                        PathNode.LineTo(726.8f, 600.4f),
                        PathNode.QuadTo(737.8f, 594.4f, 741.3f, 582.9f),
                        PathNode.QuadTo(744.8f, 571.4f, 741.3f, 559.9f),
                        PathNode.QuadTo(737.8f, 548.4f, 726.8f, 542.4f),
                        PathNode.LineTo(517.8f, 422.4f),
                        PathNode.QuadTo(506.8f, 416.4f, 495.3f, 418.4f),
                        PathNode.QuadTo(483.8f, 420.4f, 476.3f, 429.4f),
                        PathNode.QuadTo(468.8f, 438.4f, 468.8f, 450.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _shortVideoRegular!!
    }

private var _shortVideoRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ShortVideo: ImageVector
    get() {
        if (_shortVideoMedium != null) return _shortVideoMedium!!
        _shortVideoMedium = ImageVector.Builder(
            name = "ShortVideo.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(544.9f, 95.4f),
                        PathNode.LineTo(831.9f, 262.4f),
                        PathNode.QuadTo(899.4f, 300.7f, 928.1f, 320.2f),
                        PathNode.QuadTo(956.8f, 339.7f, 972.6f, 361.8f),
                        PathNode.QuadTo(1004.9f, 411.5f, 1001.9f, 473.0f),
                        PathNode.QuadTo(999.7f, 499.3f, 985.4f, 529.6f),
                        PathNode.QuadTo(971.1f, 559.9f, 936.9f, 619.2f),
                        PathNode.QuadTo(936.2f, 622.6f, 936.3f, 622.3f),
                        PathNode.QuadTo(936.3f, 622.0f, 937.9f, 622.4f),
                        PathNode.LineTo(952.1f, 631.7f),
                        PathNode.QuadTo(972.7f, 645.9f, 985.2f, 657.0f),
                        PathNode.QuadTo(997.8f, 668.0f, 1006.6f, 680.8f),
                        PathNode.QuadTo(1040.9f, 730.0f, 1035.9f, 792.0f),
                        PathNode.QuadTo(1033.7f, 819.3f, 1019.3f, 849.4f),
                        PathNode.QuadTo(1004.9f, 879.5f, 965.9f, 949.5f),
                        PathNode.QuadTo(924.3f, 1020.0f, 905.9f, 1046.7f),
                        PathNode.QuadTo(887.5f, 1073.4f, 865.4f, 1088.2f),
                        PathNode.QuadTo(841.3f, 1104.7f, 812.5f, 1112.1f),
                        PathNode.QuadTo(783.6f, 1119.5f, 754.3f, 1117.5f),
                        PathNode.QuadTo(726.9f, 1116.5f, 696.1f, 1101.8f),
                        PathNode.QuadTo(665.3f, 1087.0f, 597.8f, 1047.5f),
                        PathNode.LineTo(310.8f, 881.5f),
                        PathNode.QuadTo(245.2f, 844.2f, 215.6f, 824.1f),
                        PathNode.QuadTo(185.9f, 804.1f, 170.1f, 781.0f),
                        PathNode.QuadTo(137.7f, 732.9f, 140.7f, 669.9f),
                        PathNode.QuadTo(141.7f, 646.8f, 153.5f, 620.6f),
                        PathNode.QuadTo(165.3f, 594.5f, 193.5f, 544.6f),
                        PathNode.LineTo(204.5f, 524.6f),
                        PathNode.QuadTo(205.4f, 521.3f, 205.9f, 521.5f),
                        PathNode.QuadTo(206.3f, 521.8f, 204.5f, 521.2f),
                        PathNode.QuadTo(178.0f, 503.9f, 162.3f, 490.4f),
                        PathNode.QuadTo(146.6f, 476.8f, 136.1f, 462.0f),
                        PathNode.QuadTo(119.5f, 437.7f, 112.1f, 408.8f),
                        PathNode.QuadTo(104.7f, 380.0f, 106.7f, 351.9f),
                        PathNode.QuadTo(109.0f, 323.8f, 123.4f, 293.1f),
                        PathNode.QuadTo(137.8f, 262.4f, 176.8f, 194.4f),
                        PathNode.QuadTo(218.3f, 123.8f, 236.7f, 97.2f),
                        PathNode.QuadTo(255.1f, 70.5f, 277.2f, 55.7f),
                        PathNode.QuadTo(328.7f, 21.3f, 388.4f, 25.3f),
                        PathNode.QuadTo(415.7f, 27.6f, 446.3f, 42.0f),
                        PathNode.QuadTo(476.9f, 56.4f, 544.9f, 95.4f),
                        PathNode.Close,
                        PathNode.MoveTo(464.5f, 450.4f),
                        PathNode.LineTo(464.5f, 691.4f),
                        PathNode.QuadTo(464.5f, 706.0f, 473.1f, 716.1f),
                        PathNode.QuadTo(481.7f, 726.2f, 494.6f, 728.6f),
                        PathNode.QuadTo(507.6f, 731.0f, 520.0f, 724.2f),
                        PathNode.LineTo(729.0f, 604.2f),
                        PathNode.QuadTo(741.3f, 597.4f, 745.5f, 584.4f),
                        PathNode.QuadTo(749.7f, 571.4f, 745.5f, 558.4f),
                        PathNode.QuadTo(741.3f, 545.5f, 729.0f, 538.6f),
                        PathNode.LineTo(520.0f, 418.6f),
                        PathNode.QuadTo(507.6f, 411.8f, 494.6f, 414.1f),
                        PathNode.QuadTo(481.7f, 416.4f, 473.1f, 426.6f),
                        PathNode.QuadTo(464.5f, 436.8f, 464.5f, 450.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _shortVideoMedium!!
    }

private var _shortVideoMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ShortVideo: ImageVector
    get() {
        if (_shortVideoDemibold != null) return _shortVideoDemibold!!
        _shortVideoDemibold = ImageVector.Builder(
            name = "ShortVideo.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(547.7f, 90.5f),
                        PathNode.LineTo(834.7f, 257.5f),
                        PathNode.QuadTo(902.6f, 295.9f, 931.8f, 315.9f),
                        PathNode.QuadTo(960.9f, 335.8f, 977.3f, 358.6f),
                        PathNode.QuadTo(1010.6f, 410.2f, 1007.6f, 473.3f),
                        PathNode.QuadTo(1005.1f, 500.6f, 990.7f, 531.5f),
                        PathNode.QuadTo(976.2f, 562.4f, 941.8f, 621.9f),
                        PathNode.QuadTo(942.1f, 624.1f, 941.5f, 621.2f),
                        PathNode.QuadTo(940.9f, 618.2f, 940.7f, 617.5f),
                        PathNode.LineTo(955.2f, 626.9f),
                        PathNode.QuadTo(976.1f, 641.4f, 989.0f, 652.8f),
                        PathNode.QuadTo(1001.9f, 664.2f, 1011.3f, 677.6f),
                        PathNode.QuadTo(1046.6f, 728.3f, 1041.6f, 792.3f),
                        PathNode.QuadTo(1039.1f, 820.6f, 1024.5f, 851.5f),
                        PathNode.QuadTo(1009.8f, 882.3f, 970.8f, 952.3f),
                        PathNode.QuadTo(928.9f, 1023.2f, 910.2f, 1050.4f),
                        PathNode.QuadTo(891.5f, 1077.5f, 868.6f, 1092.9f),
                        PathNode.QuadTo(843.8f, 1109.8f, 814.0f, 1117.5f),
                        PathNode.QuadTo(784.2f, 1125.2f, 753.9f, 1123.2f),
                        PathNode.QuadTo(725.6f, 1122.2f, 694.2f, 1107.3f),
                        PathNode.QuadTo(662.9f, 1092.3f, 594.9f, 1052.4f),
                        PathNode.LineTo(307.9f, 886.4f),
                        PathNode.QuadTo(242.0f, 848.9f, 211.9f, 828.5f),
                        PathNode.QuadTo(181.7f, 808.1f, 165.3f, 784.2f),
                        PathNode.QuadTo(132.0f, 734.6f, 135.0f, 669.5f),
                        PathNode.QuadTo(136.0f, 645.7f, 148.0f, 619.0f),
                        PathNode.QuadTo(160.0f, 592.4f, 188.4f, 542.0f),
                        PathNode.LineTo(199.4f, 522.0f),
                        PathNode.QuadTo(199.6f, 519.8f, 200.7f, 522.7f),
                        PathNode.QuadTo(201.8f, 525.6f, 201.5f, 525.9f),
                        PathNode.QuadTo(174.6f, 508.5f, 158.4f, 494.5f),
                        PathNode.QuadTo(142.3f, 480.6f, 131.3f, 465.2f),
                        PathNode.QuadTo(114.4f, 439.9f, 106.7f, 410.1f),
                        PathNode.QuadTo(99.0f, 380.3f, 101.0f, 351.5f),
                        PathNode.QuadTo(103.5f, 322.7f, 118.2f, 291.1f),
                        PathNode.QuadTo(132.9f, 259.5f, 171.9f, 191.5f),
                        PathNode.QuadTo(213.8f, 120.6f, 232.5f, 93.5f),
                        PathNode.QuadTo(251.2f, 66.3f, 274.0f, 50.9f),
                        PathNode.QuadTo(327.1f, 15.6f, 388.7f, 19.6f),
                        PathNode.QuadTo(417.0f, 22.1f, 448.4f, 36.8f),
                        PathNode.QuadTo(479.7f, 51.5f, 547.7f, 90.5f),
                        PathNode.Close,
                        PathNode.MoveTo(461.5f, 450.4f),
                        PathNode.LineTo(461.5f, 691.4f),
                        PathNode.QuadTo(461.5f, 707.2f, 470.8f, 718.0f),
                        PathNode.QuadTo(480.1f, 728.9f, 494.2f, 731.5f),
                        PathNode.QuadTo(508.2f, 734.2f, 521.5f, 726.9f),
                        PathNode.LineTo(730.5f, 606.9f),
                        PathNode.QuadTo(743.8f, 599.5f, 748.4f, 585.5f),
                        PathNode.QuadTo(753.1f, 571.4f, 748.4f, 557.4f),
                        PathNode.QuadTo(743.8f, 543.4f, 730.5f, 536.0f),
                        PathNode.LineTo(521.5f, 416.0f),
                        PathNode.QuadTo(508.2f, 408.6f, 494.2f, 411.1f),
                        PathNode.QuadTo(480.1f, 413.5f, 470.8f, 424.6f),
                        PathNode.QuadTo(461.5f, 435.7f, 461.5f, 450.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _shortVideoDemibold!!
    }

private var _shortVideoDemibold: ImageVector? = null
