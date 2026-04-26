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

val MiuixIcons.Send: ImageVector
    get() = MiuixIcons.Regular.Send

val MiuixIcons.Light.Send: ImageVector
    get() {
        if (_sendLight != null) return _sendLight!!
        _sendLight = ImageVector.Builder(
            name = "Send.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1112.4f,
            viewportHeight = 1112.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1112.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(951.7f, 182.7f),
                        PathNode.LineTo(1016.7f, 929.7f),
                        PathNode.QuadTo(1019.7f, 961.7f, 1003.7f, 984.2f),
                        PathNode.QuadTo(987.7f, 1006.7f, 959.7f, 1012.2f),
                        PathNode.QuadTo(931.7f, 1017.7f, 902.7f, 1003.7f),
                        PathNode.LineTo(149.7f, 647.7f),
                        PathNode.QuadTo(117.7f, 632.7f, 105.2f, 600.7f),
                        PathNode.QuadTo(92.7f, 568.7f, 103.2f, 535.2f),
                        PathNode.QuadTo(113.7f, 501.7f, 144.7f, 484.7f),
                        PathNode.LineTo(349.7f, 374.7f),
                        PathNode.QuadTo(362.7f, 367.7f, 367.7f, 359.2f),
                        PathNode.QuadTo(372.7f, 350.7f, 372.7f, 336.7f),
                        PathNode.LineTo(373.7f, 162.7f),
                        PathNode.QuadTo(373.7f, 137.7f, 390.2f, 120.2f),
                        PathNode.QuadTo(406.7f, 102.7f, 431.2f, 100.7f),
                        PathNode.QuadTo(455.7f, 98.7f, 477.7f, 114.7f),
                        PathNode.LineTo(588.7f, 200.7f),
                        PathNode.QuadTo(600.7f, 209.7f, 612.2f, 211.2f),
                        PathNode.QuadTo(623.7f, 212.7f, 636.7f, 205.7f),
                        PathNode.LineTo(817.7f, 109.7f),
                        PathNode.QuadTo(845.7f, 94.7f, 876.2f, 100.7f),
                        PathNode.QuadTo(906.7f, 106.7f, 927.7f, 128.7f),
                        PathNode.QuadTo(948.7f, 150.7f, 951.7f, 182.7f),
                        PathNode.Close,
                        PathNode.MoveTo(563.7f, 256.7f),
                        PathNode.LineTo(464.7f, 176.7f),
                        PathNode.QuadTo(450.7f, 165.7f, 441.7f, 170.2f),
                        PathNode.QuadTo(432.7f, 174.7f, 432.7f, 189.7f),
                        PathNode.VerticalTo(346.7f),
                        PathNode.QuadTo(432.7f, 371.7f, 421.7f, 389.7f),
                        PathNode.QuadTo(410.7f, 407.7f, 389.7f, 418.7f),
                        PathNode.LineTo(176.7f, 532.7f),
                        PathNode.QuadTo(162.7f, 539.7f, 158.2f, 553.2f),
                        PathNode.QuadTo(153.7f, 566.7f, 159.2f, 579.2f),
                        PathNode.QuadTo(164.7f, 591.7f, 177.7f, 597.7f),
                        PathNode.LineTo(907.7f, 938.7f),
                        PathNode.LineTo(528.7f, 498.7f),
                        PathNode.QuadTo(523.7f, 493.7f, 524.2f, 486.2f),
                        PathNode.QuadTo(524.7f, 478.7f, 530.7f, 473.7f),
                        PathNode.LineTo(549.7f, 459.7f),
                        PathNode.QuadTo(554.7f, 455.7f, 561.7f, 456.2f),
                        PathNode.QuadTo(568.7f, 456.7f, 573.7f, 461.7f),
                        PathNode.LineTo(955.7f, 903.7f),
                        PathNode.LineTo(892.7f, 199.7f),
                        PathNode.QuadTo(890.7f, 177.7f, 875.2f, 168.2f),
                        PathNode.QuadTo(859.7f, 158.7f, 839.7f, 168.7f),
                        PathNode.LineTo(653.7f, 264.7f),
                        PathNode.QuadTo(630.7f, 276.7f, 606.7f, 274.7f),
                        PathNode.QuadTo(582.7f, 272.7f, 563.7f, 256.7f),
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

val MiuixIcons.Normal.Send: ImageVector
    get() {
        if (_sendNormal != null) return _sendNormal!!
        _sendNormal = ImageVector.Builder(
            name = "Send.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1137.1f,
            viewportHeight = 1137.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1137.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(972.3f, 192.3f),
                        PathNode.LineTo(1038.7f, 930.4f),
                        PathNode.QuadTo(1042.4f, 967.2f, 1022.3f, 995.5f),
                        PathNode.QuadTo(1002.1f, 1023.9f, 967.9f, 1032.5f),
                        PathNode.QuadTo(933.8f, 1041.1f, 900.6f, 1025.0f),
                        PathNode.LineTo(157.3f, 666.9f),
                        PathNode.QuadTo(121.8f, 649.9f, 108.3f, 614.8f),
                        PathNode.QuadTo(94.8f, 579.7f, 107.0f, 543.4f),
                        PathNode.QuadTo(119.2f, 507.2f, 153.6f, 488.1f),
                        PathNode.LineTo(355.9f, 379.5f),
                        PathNode.QuadTo(366.1f, 373.9f, 371.1f, 365.0f),
                        PathNode.QuadTo(376.1f, 356.2f, 376.1f, 344.3f),
                        PathNode.LineTo(376.4f, 173.7f),
                        PathNode.QuadTo(376.4f, 145.3f, 395.4f, 125.4f),
                        PathNode.QuadTo(414.3f, 105.4f, 442.2f, 102.8f),
                        PathNode.QuadTo(470.1f, 100.1f, 493.5f, 117.4f),
                        PathNode.LineTo(603.8f, 202.8f),
                        PathNode.QuadTo(613.1f, 210.4f, 624.2f, 211.2f),
                        PathNode.QuadTo(635.4f, 212.0f, 646.3f, 206.4f),
                        PathNode.LineTo(825.3f, 112.4f),
                        PathNode.QuadTo(856.0f, 96.1f, 889.3f, 102.4f),
                        PathNode.QuadTo(922.5f, 108.8f, 945.9f, 133.2f),
                        PathNode.QuadTo(969.3f, 157.6f, 972.3f, 192.3f),
                        PathNode.Close,
                        PathNode.MoveTo(569.9f, 274.6f),
                        PathNode.LineTo(474.3f, 196.6f),
                        PathNode.QuadTo(467.2f, 190.4f, 460.6f, 193.9f),
                        PathNode.QuadTo(454.0f, 197.4f, 454.0f, 206.2f),
                        PathNode.VerticalTo(357.7f),
                        PathNode.QuadTo(454.0f, 383.4f, 440.9f, 404.8f),
                        PathNode.QuadTo(427.9f, 426.3f, 405.5f, 437.9f),
                        PathNode.LineTo(191.8f, 551.9f),
                        PathNode.QuadTo(181.3f, 556.9f, 177.8f, 566.9f),
                        PathNode.QuadTo(174.3f, 577.0f, 178.4f, 586.8f),
                        PathNode.QuadTo(182.6f, 596.5f, 192.1f, 601.1f),
                        PathNode.LineTo(894.6f, 933.9f),
                        PathNode.LineTo(530.1f, 514.5f),
                        PathNode.QuadTo(523.7f, 507.4f, 524.5f, 497.2f),
                        PathNode.QuadTo(525.4f, 486.9f, 533.4f, 480.6f),
                        PathNode.LineTo(557.3f, 462.4f),
                        PathNode.QuadTo(564.3f, 457.1f, 573.7f, 457.9f),
                        PathNode.QuadTo(583.1f, 458.8f, 589.5f, 465.8f),
                        PathNode.LineTo(957.8f, 888.6f),
                        PathNode.LineTo(895.4f, 210.7f),
                        PathNode.QuadTo(894.1f, 194.9f, 881.7f, 187.8f),
                        PathNode.QuadTo(869.3f, 180.7f, 855.5f, 187.9f),
                        PathNode.LineTo(669.5f, 283.9f),
                        PathNode.QuadTo(645.1f, 296.6f, 618.4f, 294.3f),
                        PathNode.QuadTo(591.6f, 291.9f, 569.9f, 274.6f),
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

val MiuixIcons.Regular.Send: ImageVector
    get() {
        if (_sendRegular != null) return _sendRegular!!
        _sendRegular = ImageVector.Builder(
            name = "Send.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1148.4f,
            viewportHeight = 1148.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1148.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(981.7f, 196.7f),
                        PathNode.LineTo(1048.7f, 930.7f),
                        PathNode.QuadTo(1052.7f, 969.7f, 1030.7f, 1000.7f),
                        PathNode.QuadTo(1008.7f, 1031.7f, 971.7f, 1041.7f),
                        PathNode.QuadTo(934.7f, 1051.7f, 899.7f, 1034.7f),
                        PathNode.LineTo(160.7f, 675.7f),
                        PathNode.QuadTo(123.7f, 657.7f, 109.7f, 621.2f),
                        PathNode.QuadTo(95.7f, 584.7f, 108.7f, 547.2f),
                        PathNode.QuadTo(121.7f, 509.7f, 157.7f, 489.7f),
                        PathNode.LineTo(358.7f, 381.7f),
                        PathNode.QuadTo(367.7f, 376.7f, 372.7f, 367.7f),
                        PathNode.QuadTo(377.7f, 358.7f, 377.7f, 347.7f),
                        PathNode.VerticalTo(178.7f),
                        PathNode.QuadTo(377.7f, 148.7f, 397.7f, 127.7f),
                        PathNode.QuadTo(417.7f, 106.7f, 447.2f, 103.7f),
                        PathNode.QuadTo(476.7f, 100.7f, 500.7f, 118.7f),
                        PathNode.LineTo(610.7f, 203.7f),
                        PathNode.QuadTo(618.7f, 210.7f, 629.7f, 211.2f),
                        PathNode.QuadTo(640.7f, 211.7f, 650.7f, 206.7f),
                        PathNode.LineTo(828.7f, 113.7f),
                        PathNode.QuadTo(860.7f, 96.7f, 895.2f, 103.2f),
                        PathNode.QuadTo(929.7f, 109.7f, 954.2f, 135.2f),
                        PathNode.QuadTo(978.7f, 160.7f, 981.7f, 196.7f),
                        PathNode.Close,
                        PathNode.MoveTo(572.7f, 282.7f),
                        PathNode.LineTo(478.7f, 205.7f),
                        PathNode.QuadTo(474.7f, 201.7f, 469.2f, 204.7f),
                        PathNode.QuadTo(463.7f, 207.7f, 463.7f, 213.7f),
                        PathNode.VerticalTo(362.7f),
                        PathNode.QuadTo(463.7f, 388.7f, 449.7f, 411.7f),
                        PathNode.QuadTo(435.7f, 434.7f, 412.7f, 446.7f),
                        PathNode.LineTo(198.7f, 560.7f),
                        PathNode.QuadTo(189.7f, 564.7f, 186.7f, 573.2f),
                        PathNode.QuadTo(183.7f, 581.7f, 187.2f, 590.2f),
                        PathNode.QuadTo(190.7f, 598.7f, 198.7f, 602.7f),
                        PathNode.LineTo(888.7f, 931.7f),
                        PathNode.LineTo(530.7f, 521.7f),
                        PathNode.QuadTo(523.7f, 513.7f, 524.7f, 502.2f),
                        PathNode.QuadTo(525.7f, 490.7f, 534.7f, 483.7f),
                        PathNode.LineTo(560.7f, 463.7f),
                        PathNode.QuadTo(568.7f, 457.7f, 579.2f, 458.7f),
                        PathNode.QuadTo(589.7f, 459.7f, 596.7f, 467.7f),
                        PathNode.LineTo(958.7f, 881.7f),
                        PathNode.LineTo(896.7f, 215.7f),
                        PathNode.QuadTo(895.7f, 202.7f, 884.7f, 196.7f),
                        PathNode.QuadTo(873.7f, 190.7f, 862.7f, 196.7f),
                        PathNode.LineTo(676.7f, 292.7f),
                        PathNode.QuadTo(651.7f, 305.7f, 623.7f, 303.2f),
                        PathNode.QuadTo(595.7f, 300.7f, 572.7f, 282.7f),
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

val MiuixIcons.Medium.Send: ImageVector
    get() {
        if (_sendMedium != null) return _sendMedium!!
        _sendMedium = ImageVector.Builder(
            name = "Send.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1166.8f,
            viewportHeight = 1166.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1166.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1000.3f, 206.5f),
                        PathNode.LineTo(1065.5f, 940.5f),
                        PathNode.QuadTo(1069.5f, 983.0f, 1046.6f, 1015.2f),
                        PathNode.QuadTo(1023.8f, 1047.3f, 984.7f, 1057.6f),
                        PathNode.QuadTo(945.6f, 1067.9f, 907.7f, 1049.8f),
                        PathNode.LineTo(167.5f, 693.7f),
                        PathNode.QuadTo(127.6f, 674.5f, 112.4f, 635.1f),
                        PathNode.QuadTo(97.2f, 595.6f, 111.1f, 554.9f),
                        PathNode.QuadTo(125.0f, 514.2f, 164.5f, 493.0f),
                        PathNode.LineTo(364.9f, 385.0f),
                        PathNode.QuadTo(372.2f, 381.2f, 376.0f, 374.2f),
                        PathNode.QuadTo(379.8f, 367.3f, 379.8f, 358.1f),
                        PathNode.VerticalTo(189.1f),
                        PathNode.QuadTo(379.8f, 155.5f, 402.2f, 132.5f),
                        PathNode.QuadTo(424.5f, 109.4f, 457.0f, 105.8f),
                        PathNode.QuadTo(489.4f, 102.2f, 515.8f, 122.6f),
                        PathNode.LineTo(626.3f, 208.2f),
                        PathNode.QuadTo(632.6f, 213.4f, 640.9f, 213.9f),
                        PathNode.QuadTo(649.3f, 214.4f, 657.5f, 210.0f),
                        PathNode.LineTo(835.5f, 117.0f),
                        PathNode.QuadTo(869.9f, 98.8f, 907.0f, 105.9f),
                        PathNode.QuadTo(944.2f, 113.0f, 970.4f, 140.3f),
                        PathNode.QuadTo(996.7f, 167.5f, 1000.3f, 206.5f),
                        PathNode.Close,
                        PathNode.MoveTo(577.8f, 299.5f),
                        PathNode.LineTo(496.1f, 232.5f),
                        PathNode.QuadTo(491.5f, 228.5f, 486.9f, 230.9f),
                        PathNode.QuadTo(482.3f, 233.3f, 482.3f, 239.9f),
                        PathNode.VerticalTo(373.1f),
                        PathNode.QuadTo(482.3f, 401.4f, 467.1f, 426.5f),
                        PathNode.QuadTo(451.9f, 451.5f, 426.6f, 464.7f),
                        PathNode.LineTo(218.5f, 575.8f),
                        PathNode.QuadTo(211.2f, 579.2f, 208.5f, 585.6f),
                        PathNode.QuadTo(205.8f, 592.1f, 208.4f, 598.8f),
                        PathNode.QuadTo(211.1f, 605.5f, 217.9f, 608.9f),
                        PathNode.LineTo(881.4f, 929.1f),
                        PathNode.LineTo(539.9f, 543.2f),
                        PathNode.QuadTo(530.5f, 532.3f, 531.8f, 517.6f),
                        PathNode.QuadTo(533.1f, 502.8f, 545.1f, 493.5f),
                        PathNode.LineTo(571.1f, 473.5f),
                        PathNode.QuadTo(582.0f, 465.1f, 595.4f, 466.7f),
                        PathNode.QuadTo(608.9f, 468.3f, 617.6f, 478.1f),
                        PathNode.LineTo(963.2f, 875.0f),
                        PathNode.LineTo(899.4f, 232.5f),
                        PathNode.QuadTo(898.4f, 221.9f, 890.1f, 216.8f),
                        PathNode.QuadTo(881.7f, 211.6f, 872.5f, 216.5f),
                        PathNode.LineTo(690.6f, 310.7f),
                        PathNode.QuadTo(663.2f, 324.9f, 632.6f, 322.1f),
                        PathNode.QuadTo(601.9f, 319.3f, 577.8f, 299.5f),
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

val MiuixIcons.Demibold.Send: ImageVector
    get() {
        if (_sendDemibold != null) return _sendDemibold!!
        _sendDemibold = ImageVector.Builder(
            name = "Send.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1179.6f,
            viewportHeight = 1179.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1179.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1013.3f, 213.3f),
                        PathNode.LineTo(1077.3f, 947.3f),
                        PathNode.QuadTo(1081.3f, 992.3f, 1057.8f, 1025.3f),
                        PathNode.QuadTo(1034.3f, 1058.3f, 993.8f, 1068.8f),
                        PathNode.QuadTo(953.3f, 1079.3f, 913.3f, 1060.3f),
                        PathNode.LineTo(172.3f, 706.3f),
                        PathNode.QuadTo(130.3f, 686.3f, 114.3f, 644.8f),
                        PathNode.QuadTo(98.3f, 603.3f, 112.8f, 560.3f),
                        PathNode.QuadTo(127.3f, 517.3f, 169.3f, 495.3f),
                        PathNode.LineTo(369.3f, 387.3f),
                        PathNode.QuadTo(375.3f, 384.3f, 378.3f, 378.8f),
                        PathNode.QuadTo(381.3f, 373.3f, 381.3f, 365.3f),
                        PathNode.VerticalTo(196.3f),
                        PathNode.QuadTo(381.3f, 160.3f, 405.3f, 135.8f),
                        PathNode.QuadTo(429.3f, 111.3f, 463.8f, 107.3f),
                        PathNode.QuadTo(498.3f, 103.3f, 526.3f, 125.3f),
                        PathNode.LineTo(637.3f, 211.3f),
                        PathNode.QuadTo(642.3f, 215.3f, 648.8f, 215.8f),
                        PathNode.QuadTo(655.3f, 216.3f, 662.3f, 212.3f),
                        PathNode.LineTo(840.3f, 119.3f),
                        PathNode.QuadTo(876.3f, 100.3f, 915.3f, 107.8f),
                        PathNode.QuadTo(954.3f, 115.3f, 981.8f, 143.8f),
                        PathNode.QuadTo(1009.3f, 172.3f, 1013.3f, 213.3f),
                        PathNode.Close,
                        PathNode.MoveTo(581.3f, 311.3f),
                        PathNode.LineTo(508.3f, 251.3f),
                        PathNode.QuadTo(503.3f, 247.3f, 499.3f, 249.3f),
                        PathNode.QuadTo(495.3f, 251.3f, 495.3f, 258.3f),
                        PathNode.VerticalTo(380.3f),
                        PathNode.QuadTo(495.3f, 410.3f, 479.3f, 436.8f),
                        PathNode.QuadTo(463.3f, 463.3f, 436.3f, 477.3f),
                        PathNode.LineTo(232.3f, 586.3f),
                        PathNode.QuadTo(226.3f, 589.3f, 223.8f, 594.3f),
                        PathNode.QuadTo(221.3f, 599.3f, 223.3f, 604.8f),
                        PathNode.QuadTo(225.3f, 610.3f, 231.3f, 613.3f),
                        PathNode.LineTo(876.3f, 927.3f),
                        PathNode.LineTo(546.3f, 558.3f),
                        PathNode.QuadTo(535.3f, 545.3f, 536.8f, 528.3f),
                        PathNode.QuadTo(538.3f, 511.3f, 552.3f, 500.3f),
                        PathNode.LineTo(578.3f, 480.3f),
                        PathNode.QuadTo(591.3f, 470.3f, 606.8f, 472.3f),
                        PathNode.QuadTo(622.3f, 474.3f, 632.3f, 485.3f),
                        PathNode.LineTo(966.3f, 870.3f),
                        PathNode.LineTo(901.3f, 244.3f),
                        PathNode.QuadTo(900.3f, 235.3f, 893.8f, 230.8f),
                        PathNode.QuadTo(887.3f, 226.3f, 879.3f, 230.3f),
                        PathNode.LineTo(700.3f, 323.3f),
                        PathNode.QuadTo(671.3f, 338.3f, 638.8f, 335.3f),
                        PathNode.QuadTo(606.3f, 332.3f, 581.3f, 311.3f),
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
