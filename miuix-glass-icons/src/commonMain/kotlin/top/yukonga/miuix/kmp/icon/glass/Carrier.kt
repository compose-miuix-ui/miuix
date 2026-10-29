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

val MiuixIcons.Glass.Carrier: ImageVector
    get() = MiuixIcons.Glass.Regular.Carrier

val MiuixIcons.Glass.Light.Carrier: ImageVector
    get() {
        if (_carrierLight != null) return _carrierLight!!
        _carrierLight = ImageVector.Builder(
            name = "Carrier.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(921.0f, 126.7f),
                        PathNode.QuadTo(968.0f, 152.5f, 988.8f, 200.6f),
                        PathNode.QuadTo(998.4f, 225.4f, 997.7f, 261.8f),
                        PathNode.QuadTo(997.0f, 298.3f, 988.4f, 385.8f),
                        PathNode.LineTo(946.0f, 807.4f),
                        PathNode.QuadTo(939.0f, 878.4f, 933.4f, 909.8f),
                        PathNode.QuadTo(927.9f, 941.3f, 915.8f, 961.5f),
                        PathNode.QuadTo(892.9f, 999.0f, 851.0f, 1019.7f),
                        PathNode.QuadTo(829.8f, 1029.8f, 799.5f, 1032.1f),
                        PathNode.QuadTo(769.3f, 1034.4f, 695.3f, 1034.4f),
                        PathNode.LineTo(447.5f, 1034.4f),
                        PathNode.QuadTo(373.5f, 1034.4f, 343.3f, 1032.1f),
                        PathNode.QuadTo(313.1f, 1029.8f, 291.9f, 1019.7f),
                        PathNode.QuadTo(250.0f, 999.0f, 227.1f, 961.5f),
                        PathNode.QuadTo(215.0f, 941.3f, 209.4f, 909.8f),
                        PathNode.QuadTo(203.9f, 878.4f, 196.9f, 807.4f),
                        PathNode.LineTo(154.4f, 385.8f),
                        PathNode.QuadTo(145.9f, 298.3f, 145.1f, 261.8f),
                        PathNode.QuadTo(144.4f, 225.4f, 155.1f, 200.6f),
                        PathNode.QuadTo(173.9f, 152.9f, 221.9f, 126.7f),
                        PathNode.QuadTo(244.6f, 114.0f, 281.5f, 111.3f),
                        PathNode.QuadTo(318.4f, 108.5f, 406.1f, 108.5f),
                        PathNode.LineTo(736.8f, 108.5f),
                        PathNode.QuadTo(824.4f, 108.5f, 861.3f, 111.3f),
                        PathNode.QuadTo(898.2f, 114.0f, 921.0f, 126.7f),
                        PathNode.Close,
                        PathNode.MoveTo(373.5f, 839.3f),
                        PathNode.LineTo(373.5f, 894.5f),
                        PathNode.QuadTo(373.5f, 901.9f, 378.0f, 906.7f),
                        PathNode.QuadTo(382.5f, 911.5f, 390.5f, 911.5f),
                        PathNode.LineTo(404.6f, 911.5f),
                        PathNode.QuadTo(413.2f, 911.5f, 417.7f, 907.0f),
                        PathNode.QuadTo(422.2f, 902.5f, 422.2f, 894.5f),
                        PathNode.LineTo(422.2f, 839.3f),
                        PathNode.QuadTo(422.2f, 799.6f, 442.0f, 765.4f),
                        PathNode.QuadTo(461.9f, 731.2f, 496.3f, 711.1f),
                        PathNode.QuadTo(530.8f, 691.0f, 571.4f, 691.0f),
                        PathNode.QuadTo(612.1f, 691.0f, 646.5f, 711.1f),
                        PathNode.QuadTo(681.0f, 731.2f, 700.8f, 765.4f),
                        PathNode.QuadTo(720.6f, 799.6f, 720.6f, 839.3f),
                        PathNode.LineTo(720.6f, 894.5f),
                        PathNode.QuadTo(720.6f, 902.5f, 725.1f, 907.0f),
                        PathNode.QuadTo(729.6f, 911.5f, 738.2f, 911.5f),
                        PathNode.LineTo(751.8f, 911.5f),
                        PathNode.QuadTo(760.3f, 911.5f, 764.8f, 907.0f),
                        PathNode.QuadTo(769.3f, 902.5f, 769.3f, 894.5f),
                        PathNode.LineTo(769.3f, 839.3f),
                        PathNode.QuadTo(769.3f, 785.5f, 742.7f, 740.3f),
                        PathNode.QuadTo(716.1f, 695.0f, 670.6f, 668.4f),
                        PathNode.QuadTo(625.2f, 641.8f, 571.4f, 641.8f),
                        PathNode.QuadTo(517.6f, 641.8f, 472.2f, 668.4f),
                        PathNode.QuadTo(426.8f, 695.0f, 400.1f, 740.3f),
                        PathNode.QuadTo(373.5f, 785.5f, 373.5f, 839.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierLight!!
    }

private var _carrierLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Carrier: ImageVector
    get() {
        if (_carrierNormal != null) return _carrierNormal!!
        _carrierNormal = ImageVector.Builder(
            name = "Carrier.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(924.0f, 120.0f),
                        PathNode.QuadTo(973.8f, 147.3f, 996.1f, 198.8f),
                        PathNode.QuadTo(1006.7f, 225.1f, 1006.1f, 262.6f),
                        PathNode.QuadTo(1005.5f, 300.1f, 996.7f, 388.0f),
                        PathNode.LineTo(954.5f, 807.1f),
                        PathNode.QuadTo(947.5f, 878.1f, 941.7f, 910.6f),
                        PathNode.QuadTo(935.8f, 943.1f, 923.1f, 964.5f),
                        PathNode.QuadTo(898.1f, 1005.2f, 854.0f, 1026.7f),
                        PathNode.QuadTo(831.6f, 1037.5f, 800.1f, 1039.9f),
                        PathNode.QuadTo(768.7f, 1042.3f, 694.7f, 1042.3f),
                        PathNode.LineTo(448.1f, 1042.3f),
                        PathNode.QuadTo(374.1f, 1042.3f, 342.7f, 1039.9f),
                        PathNode.QuadTo(311.3f, 1037.5f, 288.8f, 1026.7f),
                        PathNode.QuadTo(244.8f, 1005.2f, 219.8f, 964.5f),
                        PathNode.QuadTo(207.0f, 943.1f, 201.2f, 910.6f),
                        PathNode.QuadTo(195.3f, 878.1f, 188.3f, 807.1f),
                        PathNode.LineTo(146.2f, 388.0f),
                        PathNode.QuadTo(137.3f, 300.1f, 136.7f, 262.6f),
                        PathNode.QuadTo(136.2f, 225.1f, 147.8f, 198.8f),
                        PathNode.QuadTo(168.1f, 147.4f, 218.8f, 120.0f),
                        PathNode.QuadTo(243.1f, 106.4f, 280.8f, 103.5f),
                        PathNode.QuadTo(318.4f, 100.5f, 407.0f, 100.5f),
                        PathNode.LineTo(735.8f, 100.5f),
                        PathNode.QuadTo(824.4f, 100.5f, 862.1f, 103.5f),
                        PathNode.QuadTo(899.7f, 106.4f, 924.0f, 120.0f),
                        PathNode.Close,
                        PathNode.MoveTo(368.7f, 838.3f),
                        PathNode.LineTo(368.7f, 886.5f),
                        PathNode.QuadTo(368.7f, 896.4f, 374.5f, 902.7f),
                        PathNode.QuadTo(380.4f, 909.0f, 391.1f, 909.0f),
                        PathNode.LineTo(405.9f, 909.0f),
                        PathNode.QuadTo(417.5f, 909.0f, 423.4f, 903.2f),
                        PathNode.QuadTo(429.2f, 897.3f, 429.2f, 886.5f),
                        PathNode.LineTo(429.2f, 838.3f),
                        PathNode.QuadTo(429.2f, 800.5f, 448.1f, 767.8f),
                        PathNode.QuadTo(467.1f, 735.1f, 499.8f, 716.1f),
                        PathNode.QuadTo(532.6f, 697.2f, 571.4f, 697.2f),
                        PathNode.QuadTo(610.3f, 697.2f, 643.0f, 716.1f),
                        PathNode.QuadTo(675.8f, 735.1f, 694.7f, 767.8f),
                        PathNode.QuadTo(713.6f, 800.5f, 713.6f, 838.3f),
                        PathNode.LineTo(713.6f, 886.5f),
                        PathNode.QuadTo(713.6f, 897.3f, 719.5f, 903.2f),
                        PathNode.QuadTo(725.4f, 909.0f, 737.0f, 909.0f),
                        PathNode.LineTo(750.8f, 909.0f),
                        PathNode.QuadTo(762.5f, 909.0f, 768.3f, 903.2f),
                        PathNode.QuadTo(774.2f, 897.3f, 774.2f, 886.5f),
                        PathNode.LineTo(774.2f, 838.3f),
                        PathNode.QuadTo(774.2f, 783.0f, 747.0f, 736.6f),
                        PathNode.QuadTo(719.8f, 690.2f, 673.2f, 662.9f),
                        PathNode.QuadTo(626.7f, 635.7f, 571.4f, 635.7f),
                        PathNode.QuadTo(516.1f, 635.7f, 469.6f, 662.9f),
                        PathNode.QuadTo(423.1f, 690.2f, 395.9f, 736.6f),
                        PathNode.QuadTo(368.7f, 783.0f, 368.7f, 838.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierNormal!!
    }

private var _carrierNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Carrier: ImageVector
    get() {
        if (_carrierRegular != null) return _carrierRegular!!
        _carrierRegular = ImageVector.Builder(
            name = "Carrier.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(925.4f, 116.9f),
                        PathNode.QuadTo(976.4f, 144.9f, 999.4f, 197.9f),
                        PathNode.QuadTo(1010.4f, 224.9f, 1009.9f, 262.9f),
                        PathNode.QuadTo(1009.4f, 300.9f, 1000.4f, 388.9f),
                        PathNode.LineTo(958.4f, 806.9f),
                        PathNode.QuadTo(951.4f, 877.9f, 945.4f, 910.9f),
                        PathNode.QuadTo(939.4f, 943.9f, 926.4f, 965.9f),
                        PathNode.QuadTo(900.4f, 1007.9f, 855.4f, 1029.9f),
                        PathNode.QuadTo(832.4f, 1040.9f, 800.4f, 1043.4f),
                        PathNode.QuadTo(768.4f, 1045.9f, 694.4f, 1045.9f),
                        PathNode.LineTo(448.4f, 1045.9f),
                        PathNode.QuadTo(374.4f, 1045.9f, 342.4f, 1043.4f),
                        PathNode.QuadTo(310.4f, 1040.9f, 287.4f, 1029.9f),
                        PathNode.QuadTo(242.4f, 1007.9f, 216.4f, 965.9f),
                        PathNode.QuadTo(203.4f, 943.9f, 197.4f, 910.9f),
                        PathNode.QuadTo(191.4f, 877.9f, 184.4f, 806.9f),
                        PathNode.LineTo(142.4f, 388.9f),
                        PathNode.QuadTo(133.4f, 300.9f, 132.9f, 262.9f),
                        PathNode.QuadTo(132.4f, 224.9f, 144.4f, 197.9f),
                        PathNode.QuadTo(165.4f, 144.9f, 217.4f, 116.9f),
                        PathNode.QuadTo(242.4f, 102.9f, 280.4f, 99.9f),
                        PathNode.QuadTo(318.4f, 96.9f, 407.4f, 96.9f),
                        PathNode.LineTo(735.4f, 96.9f),
                        PathNode.QuadTo(824.4f, 96.9f, 862.4f, 99.9f),
                        PathNode.QuadTo(900.4f, 102.9f, 925.4f, 116.9f),
                        PathNode.Close,
                        PathNode.MoveTo(366.4f, 837.9f),
                        PathNode.LineTo(366.4f, 882.9f),
                        PathNode.QuadTo(366.4f, 893.9f, 372.9f, 900.9f),
                        PathNode.QuadTo(379.4f, 907.9f, 391.4f, 907.9f),
                        PathNode.LineTo(406.4f, 907.9f),
                        PathNode.QuadTo(419.4f, 907.9f, 425.9f, 901.4f),
                        PathNode.QuadTo(432.4f, 894.9f, 432.4f, 882.9f),
                        PathNode.LineTo(432.4f, 837.9f),
                        PathNode.QuadTo(432.4f, 800.9f, 450.9f, 768.9f),
                        PathNode.QuadTo(469.4f, 736.9f, 501.4f, 718.4f),
                        PathNode.QuadTo(533.4f, 699.9f, 571.4f, 699.9f),
                        PathNode.QuadTo(609.4f, 699.9f, 641.4f, 718.4f),
                        PathNode.QuadTo(673.4f, 736.9f, 691.9f, 768.9f),
                        PathNode.QuadTo(710.4f, 800.9f, 710.4f, 837.9f),
                        PathNode.LineTo(710.4f, 882.9f),
                        PathNode.QuadTo(710.4f, 894.9f, 716.9f, 901.4f),
                        PathNode.QuadTo(723.4f, 907.9f, 736.4f, 907.9f),
                        PathNode.LineTo(750.4f, 907.9f),
                        PathNode.QuadTo(763.4f, 907.9f, 769.9f, 901.4f),
                        PathNode.QuadTo(776.4f, 894.9f, 776.4f, 882.9f),
                        PathNode.LineTo(776.4f, 837.9f),
                        PathNode.QuadTo(776.4f, 781.9f, 748.9f, 734.9f),
                        PathNode.QuadTo(721.4f, 687.9f, 674.4f, 660.4f),
                        PathNode.QuadTo(627.4f, 632.9f, 571.4f, 632.9f),
                        PathNode.QuadTo(515.4f, 632.9f, 468.4f, 660.4f),
                        PathNode.QuadTo(421.4f, 687.9f, 393.9f, 734.9f),
                        PathNode.QuadTo(366.4f, 781.9f, 366.4f, 837.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierRegular!!
    }

private var _carrierRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Carrier: ImageVector
    get() {
        if (_carrierMedium != null) return _carrierMedium!!
        _carrierMedium = ImageVector.Builder(
            name = "Carrier.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(930.0f, 108.6f),
                        PathNode.QuadTo(983.9f, 138.4f, 1008.0f, 194.1f),
                        PathNode.QuadTo(1019.8f, 222.8f, 1019.3f, 261.8f),
                        PathNode.QuadTo(1018.8f, 300.9f, 1009.8f, 389.7f),
                        PathNode.LineTo(967.8f, 807.7f),
                        PathNode.QuadTo(960.8f, 879.3f, 954.5f, 913.4f),
                        PathNode.QuadTo(948.3f, 947.4f, 934.5f, 970.8f),
                        PathNode.QuadTo(907.4f, 1015.2f, 859.4f, 1038.6f),
                        PathNode.QuadTo(834.8f, 1050.1f, 801.8f, 1052.8f),
                        PathNode.QuadTo(768.9f, 1055.4f, 694.4f, 1055.4f),
                        PathNode.LineTo(448.4f, 1055.4f),
                        PathNode.QuadTo(373.8f, 1055.4f, 340.9f, 1052.8f),
                        PathNode.QuadTo(307.9f, 1050.1f, 283.3f, 1038.6f),
                        PathNode.QuadTo(235.3f, 1015.2f, 208.2f, 970.8f),
                        PathNode.QuadTo(194.4f, 947.4f, 188.2f, 913.4f),
                        PathNode.QuadTo(181.9f, 879.3f, 174.9f, 807.7f),
                        PathNode.LineTo(132.9f, 389.7f),
                        PathNode.QuadTo(123.9f, 300.9f, 123.5f, 261.8f),
                        PathNode.QuadTo(123.2f, 222.8f, 135.7f, 194.4f),
                        PathNode.QuadTo(157.8f, 138.4f, 212.8f, 108.6f),
                        PathNode.QuadTo(239.4f, 94.0f, 278.5f, 90.7f),
                        PathNode.QuadTo(317.5f, 87.5f, 407.4f, 87.5f),
                        PathNode.LineTo(735.4f, 87.5f),
                        PathNode.QuadTo(825.2f, 87.5f, 864.2f, 90.7f),
                        PathNode.QuadTo(903.3f, 94.0f, 930.0f, 108.6f),
                        PathNode.Close,
                        PathNode.MoveTo(358.2f, 833.1f),
                        PathNode.LineTo(358.2f, 880.2f),
                        PathNode.QuadTo(358.2f, 894.2f, 366.1f, 902.4f),
                        PathNode.QuadTo(374.0f, 910.6f, 388.7f, 910.6f),
                        PathNode.LineTo(409.3f, 910.6f),
                        PathNode.QuadTo(424.8f, 910.6f, 432.6f, 902.8f),
                        PathNode.QuadTo(440.5f, 894.9f, 440.5f, 880.2f),
                        PathNode.LineTo(440.5f, 833.1f),
                        PathNode.QuadTo(440.5f, 800.1f, 457.9f, 771.1f),
                        PathNode.QuadTo(475.3f, 742.1f, 505.5f, 724.9f),
                        PathNode.QuadTo(535.8f, 707.8f, 571.4f, 707.8f),
                        PathNode.QuadTo(606.9f, 707.8f, 637.2f, 724.9f),
                        PathNode.QuadTo(667.4f, 742.1f, 684.8f, 771.1f),
                        PathNode.QuadTo(702.2f, 800.1f, 702.2f, 833.1f),
                        PathNode.LineTo(702.2f, 880.2f),
                        PathNode.QuadTo(702.2f, 894.9f, 710.1f, 902.8f),
                        PathNode.QuadTo(718.0f, 910.6f, 733.4f, 910.6f),
                        PathNode.LineTo(753.3f, 910.6f),
                        PathNode.QuadTo(768.8f, 910.6f, 776.6f, 902.8f),
                        PathNode.QuadTo(784.5f, 894.9f, 784.5f, 880.2f),
                        PathNode.LineTo(784.5f, 833.1f),
                        PathNode.QuadTo(784.5f, 777.6f, 755.7f, 729.8f),
                        PathNode.QuadTo(727.0f, 682.0f, 678.1f, 653.5f),
                        PathNode.QuadTo(629.2f, 625.1f, 571.4f, 625.1f),
                        PathNode.QuadTo(513.5f, 625.1f, 464.6f, 653.5f),
                        PathNode.QuadTo(415.7f, 682.0f, 387.0f, 729.8f),
                        PathNode.QuadTo(358.2f, 777.6f, 358.2f, 833.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierMedium!!
    }

private var _carrierMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Carrier: ImageVector
    get() {
        if (_carrierDemibold != null) return _carrierDemibold!!
        _carrierDemibold = ImageVector.Builder(
            name = "Carrier.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(933.1f, 102.7f),
                        PathNode.QuadTo(989.2f, 133.9f, 1014.0f, 191.5f),
                        PathNode.QuadTo(1026.4f, 221.3f, 1025.9f, 261.1f),
                        PathNode.QuadTo(1025.4f, 300.9f, 1016.4f, 390.3f),
                        PathNode.LineTo(974.4f, 808.3f),
                        PathNode.QuadTo(967.4f, 880.2f, 960.9f, 915.1f),
                        PathNode.QuadTo(954.5f, 949.9f, 940.1f, 974.2f),
                        PathNode.QuadTo(912.3f, 1020.3f, 862.2f, 1044.6f),
                        PathNode.QuadTo(836.4f, 1056.6f, 802.8f, 1059.3f),
                        PathNode.QuadTo(769.2f, 1062.0f, 694.3f, 1062.0f),
                        PathNode.LineTo(448.3f, 1062.0f),
                        PathNode.QuadTo(373.4f, 1062.0f, 339.8f, 1059.3f),
                        PathNode.QuadTo(306.2f, 1056.6f, 280.4f, 1044.6f),
                        PathNode.QuadTo(230.4f, 1020.3f, 202.5f, 974.2f),
                        PathNode.QuadTo(188.1f, 949.9f, 181.7f, 915.1f),
                        PathNode.QuadTo(175.2f, 880.2f, 168.2f, 808.3f),
                        PathNode.LineTo(126.2f, 390.3f),
                        PathNode.QuadTo(117.2f, 300.9f, 117.0f, 261.1f),
                        PathNode.QuadTo(116.7f, 221.3f, 129.6f, 192.0f),
                        PathNode.QuadTo(152.4f, 133.9f, 209.5f, 102.7f),
                        PathNode.QuadTo(237.3f, 87.8f, 277.1f, 84.3f),
                        PathNode.QuadTo(316.9f, 80.8f, 407.3f, 80.8f),
                        PathNode.LineTo(735.3f, 80.8f),
                        PathNode.QuadTo(825.7f, 80.8f, 865.5f, 84.3f),
                        PathNode.QuadTo(905.4f, 87.8f, 933.1f, 102.7f),
                        PathNode.Close,
                        PathNode.MoveTo(352.5f, 829.7f),
                        PathNode.LineTo(352.5f, 878.3f),
                        PathNode.QuadTo(352.5f, 894.4f, 361.3f, 903.5f),
                        PathNode.QuadTo(370.1f, 912.5f, 386.7f, 912.5f),
                        PathNode.LineTo(411.4f, 912.5f),
                        PathNode.QuadTo(428.5f, 912.5f, 437.3f, 903.7f),
                        PathNode.QuadTo(446.1f, 894.9f, 446.1f, 878.3f),
                        PathNode.LineTo(446.1f, 829.7f),
                        PathNode.QuadTo(446.1f, 799.6f, 462.8f, 772.6f),
                        PathNode.QuadTo(479.4f, 745.7f, 508.4f, 729.5f),
                        PathNode.QuadTo(537.4f, 713.3f, 571.3f, 713.3f),
                        PathNode.QuadTo(605.2f, 713.3f, 634.2f, 729.5f),
                        PathNode.QuadTo(663.2f, 745.7f, 679.9f, 772.6f),
                        PathNode.QuadTo(696.5f, 799.6f, 696.5f, 829.7f),
                        PathNode.LineTo(696.5f, 878.3f),
                        PathNode.QuadTo(696.5f, 894.9f, 705.3f, 903.7f),
                        PathNode.QuadTo(714.1f, 912.5f, 731.3f, 912.5f),
                        PathNode.LineTo(755.4f, 912.5f),
                        PathNode.QuadTo(772.5f, 912.5f, 781.3f, 903.7f),
                        PathNode.QuadTo(790.1f, 894.9f, 790.1f, 878.3f),
                        PathNode.LineTo(790.1f, 829.7f),
                        PathNode.QuadTo(790.1f, 774.6f, 760.5f, 726.2f),
                        PathNode.QuadTo(731.0f, 677.8f, 680.7f, 648.7f),
                        PathNode.QuadTo(630.5f, 619.6f, 571.3f, 619.6f),
                        PathNode.QuadTo(512.1f, 619.6f, 461.9f, 648.7f),
                        PathNode.QuadTo(411.7f, 677.8f, 382.1f, 726.2f),
                        PathNode.QuadTo(352.5f, 774.6f, 352.5f, 829.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _carrierDemibold!!
    }

private var _carrierDemibold: ImageVector? = null
