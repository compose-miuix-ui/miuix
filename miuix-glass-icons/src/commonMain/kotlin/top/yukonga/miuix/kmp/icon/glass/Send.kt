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

val MiuixIcons.Glass.Send: ImageVector
    get() = MiuixIcons.Glass.Regular.Send

val MiuixIcons.Glass.Light.Send: ImageVector
    get() {
        if (_sendLight != null) return _sendLight!!
        _sendLight = ImageVector.Builder(
            name = "Send.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(965.1f, 197.3f),
                        PathNode.LineTo(1030.8f, 944.6f),
                        PathNode.QuadTo(1033.9f, 976.5f, 1017.9f, 998.8f),
                        PathNode.QuadTo(1001.9f, 1021.2f, 973.8f, 1026.9f),
                        PathNode.QuadTo(945.7f, 1032.7f, 916.4f, 1018.8f),
                        PathNode.LineTo(163.2f, 662.9f),
                        PathNode.QuadTo(131.1f, 647.6f, 118.4f, 615.3f),
                        PathNode.QuadTo(105.8f, 583.0f, 116.5f, 550.0f),
                        PathNode.QuadTo(127.3f, 516.9f, 158.9f, 499.6f),
                        PathNode.LineTo(363.9f, 389.4f),
                        PathNode.QuadTo(376.9f, 382.6f, 381.9f, 374.0f),
                        PathNode.QuadTo(386.9f, 365.5f, 386.9f, 351.4f),
                        PathNode.LineTo(387.3f, 177.9f),
                        PathNode.QuadTo(387.3f, 152.8f, 403.8f, 135.4f),
                        PathNode.QuadTo(420.2f, 117.9f, 445.0f, 115.8f),
                        PathNode.QuadTo(469.9f, 113.7f, 491.2f, 129.9f),
                        PathNode.LineTo(602.5f, 215.4f),
                        PathNode.QuadTo(614.1f, 224.6f, 625.8f, 225.8f),
                        PathNode.QuadTo(637.4f, 226.9f, 650.5f, 220.2f),
                        PathNode.LineTo(831.7f, 124.9f),
                        PathNode.QuadTo(859.7f, 110.2f, 889.9f, 115.8f),
                        PathNode.QuadTo(920.2f, 121.4f, 941.4f, 143.5f),
                        PathNode.QuadTo(962.5f, 165.7f, 965.1f, 197.3f),
                        PathNode.Close,
                        PathNode.MoveTo(577.9f, 271.7f),
                        PathNode.LineTo(478.1f, 191.6f),
                        PathNode.QuadTo(464.8f, 180.5f, 455.7f, 184.6f),
                        PathNode.QuadTo(446.7f, 188.7f, 446.7f, 204.0f),
                        PathNode.LineTo(446.7f, 361.9f),
                        PathNode.QuadTo(446.7f, 386.2f, 435.3f, 404.5f),
                        PathNode.QuadTo(424.0f, 422.8f, 403.2f, 433.9f),
                        PathNode.LineTo(190.5f, 547.9f),
                        PathNode.QuadTo(177.1f, 554.6f, 172.3f, 567.8f),
                        PathNode.QuadTo(167.5f, 580.9f, 172.8f, 593.7f),
                        PathNode.QuadTo(178.1f, 606.4f, 191.0f, 612.6f),
                        PathNode.LineTo(921.0f, 953.2f),
                        PathNode.LineTo(542.1f, 513.8f),
                        PathNode.QuadTo(537.3f, 508.5f, 538.1f, 501.0f),
                        PathNode.QuadTo(538.9f, 493.5f, 544.8f, 488.7f),
                        PathNode.LineTo(563.2f, 474.5f),
                        PathNode.QuadTo(568.5f, 470.3f, 575.7f, 470.6f),
                        PathNode.QuadTo(582.9f, 470.9f, 587.7f, 476.7f),
                        PathNode.LineTo(969.2f, 918.3f),
                        PathNode.LineTo(906.8f, 214.0f),
                        PathNode.QuadTo(905.3f, 193.0f, 889.0f, 183.0f),
                        PathNode.QuadTo(872.7f, 173.0f, 853.2f, 183.5f),
                        PathNode.LineTo(667.2f, 279.9f),
                        PathNode.QuadTo(645.3f, 291.6f, 621.5f, 289.5f),
                        PathNode.QuadTo(597.8f, 287.5f, 577.9f, 271.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sendLight!!
    }

private var _sendLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Send: ImageVector
    get() {
        if (_sendNormal != null) return _sendNormal!!
        _sendNormal = ImageVector.Builder(
            name = "Send.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(973.5f, 195.5f),
                        PathNode.LineTo(1040.0f, 933.7f),
                        PathNode.QuadTo(1043.8f, 970.5f, 1023.6f, 998.8f),
                        PathNode.QuadTo(1003.5f, 1027.1f, 969.3f, 1035.7f),
                        PathNode.QuadTo(935.1f, 1044.4f, 901.9f, 1028.4f),
                        PathNode.LineTo(158.4f, 670.4f),
                        PathNode.QuadTo(123.0f, 653.2f, 109.4f, 618.0f),
                        PathNode.QuadTo(95.8f, 582.8f, 108.1f, 546.7f),
                        PathNode.QuadTo(120.4f, 510.6f, 155.0f, 491.5f),
                        PathNode.LineTo(357.3f, 382.8f),
                        PathNode.QuadTo(367.5f, 377.2f, 372.5f, 368.3f),
                        PathNode.QuadTo(377.5f, 359.5f, 377.5f, 347.5f),
                        PathNode.LineTo(377.6f, 177.1f),
                        PathNode.QuadTo(377.6f, 148.6f, 396.5f, 128.8f),
                        PathNode.QuadTo(415.4f, 108.9f, 443.5f, 106.1f),
                        PathNode.QuadTo(471.5f, 103.4f, 494.7f, 120.9f),
                        PathNode.LineTo(605.1f, 206.0f),
                        PathNode.QuadTo(614.2f, 213.7f, 625.4f, 214.4f),
                        PathNode.QuadTo(636.6f, 215.1f, 647.6f, 209.6f),
                        PathNode.LineTo(826.6f, 115.9f),
                        PathNode.QuadTo(857.3f, 99.6f, 890.5f, 105.8f),
                        PathNode.QuadTo(923.7f, 112.0f, 947.1f, 136.5f),
                        PathNode.QuadTo(970.6f, 160.9f, 973.5f, 195.5f),
                        PathNode.Close,
                        PathNode.MoveTo(571.3f, 277.9f),
                        PathNode.LineTo(475.5f, 199.9f),
                        PathNode.QuadTo(468.5f, 193.7f, 461.9f, 197.1f),
                        PathNode.QuadTo(455.3f, 200.4f, 455.3f, 209.3f),
                        PathNode.LineTo(455.3f, 361.1f),
                        PathNode.QuadTo(455.3f, 386.6f, 442.1f, 408.1f),
                        PathNode.QuadTo(429.0f, 429.6f, 406.7f, 441.4f),
                        PathNode.LineTo(193.1f, 555.4f),
                        PathNode.QuadTo(182.7f, 560.2f, 179.1f, 570.2f),
                        PathNode.QuadTo(175.6f, 580.1f, 179.6f, 589.9f),
                        PathNode.QuadTo(183.7f, 599.8f, 193.2f, 604.5f),
                        PathNode.LineTo(895.7f, 937.1f),
                        PathNode.LineTo(531.2f, 517.9f),
                        PathNode.QuadTo(524.9f, 510.7f, 525.8f, 500.5f),
                        PathNode.QuadTo(526.8f, 490.2f, 534.8f, 483.9f),
                        PathNode.LineTo(558.4f, 465.7f),
                        PathNode.QuadTo(565.6f, 460.3f, 575.0f, 461.1f),
                        PathNode.QuadTo(584.5f, 461.9f, 590.8f, 469.2f),
                        PathNode.LineTo(958.9f, 891.8f),
                        PathNode.LineTo(896.8f, 213.8f),
                        PathNode.QuadTo(895.6f, 198.3f, 883.0f, 191.1f),
                        PathNode.QuadTo(870.3f, 183.8f, 856.7f, 191.2f),
                        PathNode.LineTo(670.7f, 287.4f),
                        PathNode.QuadTo(646.6f, 299.9f, 620.0f, 297.6f),
                        PathNode.QuadTo(593.3f, 295.2f, 571.3f, 277.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sendNormal!!
    }

private var _sendNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Send: ImageVector
    get() {
        if (_sendRegular != null) return _sendRegular!!
        _sendRegular = ImageVector.Builder(
            name = "Send.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(977.3f, 194.7f),
                        PathNode.LineTo(1044.3f, 928.7f),
                        PathNode.QuadTo(1048.3f, 967.7f, 1026.3f, 998.7f),
                        PathNode.QuadTo(1004.3f, 1029.7f, 967.3f, 1039.7f),
                        PathNode.QuadTo(930.3f, 1049.7f, 895.3f, 1032.7f),
                        PathNode.LineTo(156.3f, 673.7f),
                        PathNode.QuadTo(119.3f, 655.7f, 105.3f, 619.2f),
                        PathNode.QuadTo(91.3f, 582.7f, 104.3f, 545.2f),
                        PathNode.QuadTo(117.3f, 507.7f, 153.3f, 487.7f),
                        PathNode.LineTo(354.3f, 379.7f),
                        PathNode.QuadTo(363.3f, 374.7f, 368.3f, 365.7f),
                        PathNode.QuadTo(373.3f, 356.7f, 373.3f, 345.7f),
                        PathNode.LineTo(373.3f, 176.7f),
                        PathNode.QuadTo(373.3f, 146.7f, 393.3f, 125.7f),
                        PathNode.QuadTo(413.3f, 104.7f, 442.8f, 101.7f),
                        PathNode.QuadTo(472.3f, 98.7f, 496.3f, 116.7f),
                        PathNode.LineTo(606.3f, 201.7f),
                        PathNode.QuadTo(614.3f, 208.7f, 625.3f, 209.2f),
                        PathNode.QuadTo(636.3f, 209.7f, 646.3f, 204.7f),
                        PathNode.LineTo(824.3f, 111.7f),
                        PathNode.QuadTo(856.3f, 94.7f, 890.8f, 101.2f),
                        PathNode.QuadTo(925.3f, 107.7f, 949.8f, 133.2f),
                        PathNode.QuadTo(974.3f, 158.7f, 977.3f, 194.7f),
                        PathNode.Close,
                        PathNode.MoveTo(568.3f, 280.7f),
                        PathNode.LineTo(474.3f, 203.7f),
                        PathNode.QuadTo(470.3f, 199.7f, 464.8f, 202.7f),
                        PathNode.QuadTo(459.3f, 205.7f, 459.3f, 211.7f),
                        PathNode.LineTo(459.3f, 360.7f),
                        PathNode.QuadTo(459.3f, 386.7f, 445.3f, 409.7f),
                        PathNode.QuadTo(431.3f, 432.7f, 408.3f, 444.7f),
                        PathNode.LineTo(194.3f, 558.7f),
                        PathNode.QuadTo(185.3f, 562.7f, 182.3f, 571.2f),
                        PathNode.QuadTo(179.3f, 579.7f, 182.8f, 588.2f),
                        PathNode.QuadTo(186.3f, 596.7f, 194.3f, 600.7f),
                        PathNode.LineTo(884.3f, 929.7f),
                        PathNode.LineTo(526.3f, 519.7f),
                        PathNode.QuadTo(519.3f, 511.7f, 520.3f, 500.2f),
                        PathNode.QuadTo(521.3f, 488.7f, 530.3f, 481.7f),
                        PathNode.LineTo(556.3f, 461.7f),
                        PathNode.QuadTo(564.3f, 455.7f, 574.8f, 456.7f),
                        PathNode.QuadTo(585.3f, 457.7f, 592.3f, 465.7f),
                        PathNode.LineTo(954.3f, 879.7f),
                        PathNode.LineTo(892.3f, 213.7f),
                        PathNode.QuadTo(891.3f, 200.7f, 880.3f, 194.7f),
                        PathNode.QuadTo(869.3f, 188.7f, 858.3f, 194.7f),
                        PathNode.LineTo(672.3f, 290.7f),
                        PathNode.QuadTo(647.3f, 303.7f, 619.3f, 301.2f),
                        PathNode.QuadTo(591.3f, 298.7f, 568.3f, 280.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sendRegular!!
    }

private var _sendRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Send: ImageVector
    get() {
        if (_sendMedium != null) return _sendMedium!!
        _sendMedium = ImageVector.Builder(
            name = "Send.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(986.1f, 195.2f),
                        PathNode.LineTo(1051.7f, 929.2f),
                        PathNode.QuadTo(1055.7f, 971.4f, 1032.6f, 1003.9f),
                        PathNode.QuadTo(1009.6f, 1036.4f, 970.5f, 1046.5f),
                        PathNode.QuadTo(931.5f, 1056.7f, 893.5f, 1038.6f),
                        PathNode.LineTo(153.4f, 682.3f),
                        PathNode.QuadTo(113.5f, 663.2f, 98.4f, 623.7f),
                        PathNode.QuadTo(83.3f, 584.3f, 97.3f, 543.8f),
                        PathNode.QuadTo(111.2f, 503.3f, 150.2f, 482.0f),
                        PathNode.LineTo(350.9f, 374.0f),
                        PathNode.QuadTo(358.0f, 370.0f, 361.9f, 362.9f),
                        PathNode.QuadTo(365.8f, 355.8f, 365.8f, 347.0f),
                        PathNode.LineTo(365.8f, 178.0f),
                        PathNode.QuadTo(365.8f, 144.5f, 388.0f, 121.3f),
                        PathNode.QuadTo(410.2f, 98.2f, 442.8f, 94.8f),
                        PathNode.QuadTo(475.4f, 91.3f, 501.8f, 111.5f),
                        PathNode.LineTo(612.1f, 196.8f),
                        PathNode.QuadTo(618.2f, 202.2f, 626.8f, 202.4f),
                        PathNode.QuadTo(635.3f, 202.6f, 643.2f, 198.7f),
                        PathNode.LineTo(821.2f, 105.7f),
                        PathNode.QuadTo(855.9f, 87.6f, 893.1f, 94.7f),
                        PathNode.QuadTo(930.3f, 101.7f, 956.5f, 129.1f),
                        PathNode.QuadTo(982.8f, 156.5f, 986.1f, 195.2f),
                        PathNode.Close,
                        PathNode.MoveTo(563.8f, 288.2f),
                        PathNode.LineTo(482.0f, 221.2f),
                        PathNode.QuadTo(477.7f, 216.9f, 472.9f, 219.5f),
                        PathNode.QuadTo(468.1f, 222.1f, 468.1f, 228.4f),
                        PathNode.LineTo(468.1f, 362.0f),
                        PathNode.QuadTo(468.1f, 390.4f, 453.0f, 415.3f),
                        PathNode.QuadTo(437.9f, 440.2f, 412.7f, 453.3f),
                        PathNode.LineTo(204.1f, 564.3f),
                        PathNode.QuadTo(196.8f, 567.5f, 194.2f, 574.3f),
                        PathNode.QuadTo(191.6f, 581.0f, 194.4f, 587.6f),
                        PathNode.QuadTo(197.2f, 594.2f, 203.9f, 597.4f),
                        PathNode.LineTo(867.1f, 917.8f),
                        PathNode.LineTo(525.9f, 531.8f),
                        PathNode.QuadTo(516.4f, 521.1f, 517.7f, 506.1f),
                        PathNode.QuadTo(519.0f, 491.1f, 531.0f, 481.9f),
                        PathNode.LineTo(557.0f, 461.9f),
                        PathNode.QuadTo(567.7f, 454.0f, 581.3f, 455.4f),
                        PathNode.QuadTo(594.9f, 456.8f, 603.8f, 467.0f),
                        PathNode.LineTo(949.0f, 863.7f),
                        PathNode.LineTo(885.4f, 221.2f),
                        PathNode.QuadTo(884.4f, 210.4f, 876.0f, 205.6f),
                        PathNode.QuadTo(867.5f, 200.8f, 858.4f, 205.5f),
                        PathNode.LineTo(676.7f, 299.3f),
                        PathNode.QuadTo(649.6f, 313.4f, 619.0f, 310.6f),
                        PathNode.QuadTo(588.4f, 307.8f, 563.8f, 288.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sendMedium!!
    }

private var _sendMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Send: ImageVector
    get() {
        if (_sendDemibold != null) return _sendDemibold!!
        _sendDemibold = ImageVector.Builder(
            name = "Send.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(992.2f, 195.5f),
                        PathNode.LineTo(1056.9f, 929.5f),
                        PathNode.QuadTo(1060.9f, 974.0f, 1037.1f, 1007.5f),
                        PathNode.QuadTo(1013.3f, 1041.1f, 972.8f, 1051.3f),
                        PathNode.QuadTo(932.4f, 1061.5f, 892.3f, 1042.7f),
                        PathNode.LineTo(151.5f, 688.3f),
                        PathNode.QuadTo(109.4f, 668.4f, 93.6f, 626.9f),
                        PathNode.QuadTo(77.7f, 585.3f, 92.4f, 542.8f),
                        PathNode.QuadTo(107.0f, 500.2f, 148.0f, 477.9f),
                        PathNode.LineTo(348.6f, 369.9f),
                        PathNode.QuadTo(354.3f, 366.8f, 357.5f, 361.0f),
                        PathNode.QuadTo(360.7f, 355.2f, 360.7f, 347.9f),
                        PathNode.LineTo(360.7f, 178.9f),
                        PathNode.QuadTo(360.7f, 142.9f, 384.3f, 118.2f),
                        PathNode.QuadTo(408.0f, 93.6f, 442.8f, 89.9f),
                        PathNode.QuadTo(477.6f, 86.2f, 505.7f, 107.9f),
                        PathNode.LineTo(616.2f, 193.3f),
                        PathNode.QuadTo(621.0f, 197.6f, 627.8f, 197.6f),
                        PathNode.QuadTo(634.7f, 197.6f, 641.0f, 194.5f),
                        PathNode.LineTo(819.0f, 101.5f),
                        PathNode.QuadTo(855.6f, 82.6f, 894.7f, 90.1f),
                        PathNode.QuadTo(933.8f, 97.5f, 961.3f, 126.2f),
                        PathNode.QuadTo(988.8f, 154.9f, 992.2f, 195.5f),
                        PathNode.Close,
                        PathNode.MoveTo(560.7f, 293.4f),
                        PathNode.LineTo(487.4f, 233.4f),
                        PathNode.QuadTo(482.9f, 229.0f, 478.6f, 231.3f),
                        PathNode.QuadTo(474.2f, 233.6f, 474.2f, 240.1f),
                        PathNode.LineTo(474.2f, 362.9f),
                        PathNode.QuadTo(474.2f, 393.0f, 458.4f, 419.2f),
                        PathNode.QuadTo(442.6f, 445.4f, 415.9f, 459.3f),
                        PathNode.LineTo(211.1f, 568.2f),
                        PathNode.QuadTo(204.8f, 570.9f, 202.5f, 576.4f),
                        PathNode.QuadTo(200.2f, 581.9f, 202.6f, 587.2f),
                        PathNode.QuadTo(204.9f, 592.4f, 210.6f, 595.1f),
                        PathNode.LineTo(855.1f, 909.4f),
                        PathNode.LineTo(525.6f, 540.3f),
                        PathNode.QuadTo(514.5f, 527.7f, 515.9f, 510.2f),
                        PathNode.QuadTo(517.4f, 492.7f, 531.4f, 482.0f),
                        PathNode.LineTo(557.4f, 462.0f),
                        PathNode.QuadTo(570.0f, 452.8f, 585.8f, 454.5f),
                        PathNode.QuadTo(601.6f, 456.2f, 611.8f, 467.9f),
                        PathNode.LineTo(945.3f, 852.5f),
                        PathNode.LineTo(880.6f, 226.4f),
                        PathNode.QuadTo(879.6f, 217.1f, 872.9f, 213.2f),
                        PathNode.QuadTo(866.3f, 209.3f, 858.5f, 213.0f),
                        PathNode.LineTo(679.9f, 305.3f),
                        PathNode.QuadTo(651.2f, 320.1f, 618.8f, 317.2f),
                        PathNode.QuadTo(586.5f, 314.2f, 560.7f, 293.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _sendDemibold!!
    }

private var _sendDemibold: ImageVector? = null
