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

val MiuixIcons.Glass.Mic: ImageVector
    get() = MiuixIcons.Glass.Regular.Mic

val MiuixIcons.Glass.Light.Mic: ImageVector
    get() {
        if (_micLight != null) return _micLight!!
        _micLight = ImageVector.Builder(
            name = "Mic.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(995.7f, 626.9f),
                        PathNode.LineTo(995.7f, 680.8f),
                        PathNode.QuadTo(995.7f, 701.1f, 975.9f, 701.1f),
                        PathNode.LineTo(953.4f, 701.1f),
                        PathNode.QuadTo(943.7f, 701.1f, 938.9f, 695.5f),
                        PathNode.QuadTo(934.2f, 689.9f, 934.2f, 680.8f),
                        PathNode.LineTo(934.2f, 626.9f),
                        PathNode.QuadTo(934.2f, 528.3f, 885.2f, 445.0f),
                        PathNode.QuadTo(836.3f, 361.6f, 753.0f, 312.6f),
                        PathNode.QuadTo(669.8f, 263.7f, 571.9f, 263.7f),
                        PathNode.QuadTo(473.4f, 263.7f, 390.0f, 312.6f),
                        PathNode.QuadTo(306.7f, 361.6f, 257.7f, 445.0f),
                        PathNode.QuadTo(208.7f, 528.3f, 208.7f, 626.9f),
                        PathNode.LineTo(208.7f, 681.3f),
                        PathNode.QuadTo(208.7f, 701.1f, 187.8f, 701.1f),
                        PathNode.LineTo(168.6f, 701.1f),
                        PathNode.QuadTo(147.2f, 701.1f, 147.2f, 681.3f),
                        PathNode.LineTo(147.2f, 626.9f),
                        PathNode.QuadTo(147.2f, 510.9f, 204.2f, 413.7f),
                        PathNode.QuadTo(261.2f, 316.5f, 358.5f, 259.8f),
                        PathNode.QuadTo(455.9f, 203.1f, 572.1f, 203.1f),
                        PathNode.QuadTo(687.4f, 203.1f, 784.5f, 259.8f),
                        PathNode.QuadTo(881.7f, 316.5f, 938.7f, 414.0f),
                        PathNode.QuadTo(995.7f, 511.4f, 995.7f, 626.9f),
                        PathNode.Close,
                        PathNode.MoveTo(600.6f, 33.4f),
                        PathNode.LineTo(600.6f, 239.9f),
                        PathNode.LineTo(542.3f, 239.9f),
                        PathNode.LineTo(542.3f, 33.4f),
                        PathNode.QuadTo(542.3f, 26.1f, 547.7f, 20.7f),
                        PathNode.QuadTo(553.1f, 15.3f, 559.8f, 15.3f),
                        PathNode.LineTo(582.9f, 15.3f),
                        PathNode.QuadTo(589.9f, 15.3f, 595.3f, 20.7f),
                        PathNode.QuadTo(600.6f, 26.1f, 600.6f, 33.4f),
                        PathNode.Close,
                        PathNode.MoveTo(824.6f, 630.9f),
                        PathNode.LineTo(824.6f, 875.9f),
                        PathNode.QuadTo(824.6f, 944.2f, 790.6f, 1002.2f),
                        PathNode.QuadTo(756.6f, 1060.1f, 698.5f, 1093.8f),
                        PathNode.QuadTo(640.5f, 1127.5f, 571.9f, 1127.5f),
                        PathNode.QuadTo(502.9f, 1127.5f, 444.9f, 1093.8f),
                        PathNode.QuadTo(386.8f, 1060.1f, 353.0f, 1002.2f),
                        PathNode.QuadTo(319.3f, 944.3f, 319.3f, 875.9f),
                        PathNode.LineTo(319.3f, 630.9f),
                        PathNode.QuadTo(319.3f, 562.5f, 353.0f, 504.1f),
                        PathNode.QuadTo(386.8f, 445.6f, 444.9f, 411.9f),
                        PathNode.QuadTo(502.9f, 378.2f, 572.1f, 378.2f),
                        PathNode.QuadTo(640.4f, 378.2f, 698.5f, 412.2f),
                        PathNode.QuadTo(756.6f, 446.2f, 790.6f, 504.3f),
                        PathNode.QuadTo(824.6f, 562.3f, 824.6f, 630.9f),
                        PathNode.Close,
                        PathNode.MoveTo(377.6f, 630.9f),
                        PathNode.LineTo(377.6f, 875.9f),
                        PathNode.QuadTo(377.6f, 928.5f, 403.9f, 972.6f),
                        PathNode.QuadTo(430.2f, 1016.6f, 474.7f, 1042.4f),
                        PathNode.QuadTo(519.3f, 1068.2f, 571.9f, 1068.2f),
                        PathNode.QuadTo(624.5f, 1068.2f, 668.5f, 1042.4f),
                        PathNode.QuadTo(712.6f, 1016.6f, 738.9f, 972.6f),
                        PathNode.QuadTo(765.3f, 928.5f, 765.3f, 875.9f),
                        PathNode.LineTo(765.3f, 630.9f),
                        PathNode.QuadTo(765.3f, 578.2f, 739.0f, 533.7f),
                        PathNode.QuadTo(712.6f, 489.1f, 668.6f, 463.3f),
                        PathNode.QuadTo(624.6f, 437.5f, 572.0f, 437.5f),
                        PathNode.QuadTo(519.4f, 437.5f, 474.8f, 463.3f),
                        PathNode.QuadTo(430.2f, 489.1f, 403.9f, 533.7f),
                        PathNode.QuadTo(377.6f, 578.2f, 377.6f, 630.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _micLight!!
    }

private var _micLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Mic: ImageVector
    get() {
        if (_micNormal != null) return _micNormal!!
        _micNormal = ImageVector.Builder(
            name = "Mic.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1005.5f, 623.8f),
                        PathNode.LineTo(1005.5f, 671.6f),
                        PathNode.QuadTo(1005.5f, 699.3f, 978.7f, 699.3f),
                        PathNode.LineTo(950.3f, 699.3f),
                        PathNode.QuadTo(937.0f, 699.3f, 930.7f, 691.7f),
                        PathNode.QuadTo(924.4f, 684.1f, 924.4f, 671.6f),
                        PathNode.LineTo(924.4f, 623.8f),
                        PathNode.QuadTo(924.4f, 527.7f, 876.8f, 446.7f),
                        PathNode.QuadTo(829.2f, 365.6f, 748.2f, 318.0f),
                        PathNode.QuadTo(667.1f, 270.4f, 571.9f, 270.4f),
                        PathNode.QuadTo(475.8f, 270.4f, 394.8f, 318.0f),
                        PathNode.QuadTo(313.7f, 365.6f, 266.1f, 446.7f),
                        PathNode.QuadTo(218.5f, 527.7f, 218.5f, 623.8f),
                        PathNode.LineTo(218.5f, 672.5f),
                        PathNode.QuadTo(218.5f, 699.3f, 190.0f, 699.3f),
                        PathNode.LineTo(166.8f, 699.3f),
                        PathNode.QuadTo(137.4f, 699.3f, 137.4f, 672.5f),
                        PathNode.LineTo(137.4f, 623.8f),
                        PathNode.QuadTo(137.4f, 505.1f, 195.7f, 405.6f),
                        PathNode.QuadTo(254.1f, 306.2f, 353.6f, 248.2f),
                        PathNode.QuadTo(453.2f, 190.3f, 572.0f, 190.3f),
                        PathNode.QuadTo(689.8f, 190.3f, 789.3f, 248.2f),
                        PathNode.QuadTo(888.7f, 306.2f, 947.1f, 406.0f),
                        PathNode.QuadTo(1005.5f, 505.9f, 1005.5f, 623.8f),
                        PathNode.Close,
                        PathNode.MoveTo(609.8f, 33.4f),
                        PathNode.LineTo(609.8f, 236.8f),
                        PathNode.LineTo(533.1f, 236.8f),
                        PathNode.LineTo(533.1f, 33.4f),
                        PathNode.QuadTo(533.1f, 23.6f, 540.3f, 16.4f),
                        PathNode.QuadTo(547.5f, 9.2f, 556.5f, 9.2f),
                        PathNode.LineTo(586.4f, 9.2f),
                        PathNode.QuadTo(595.4f, 9.2f, 602.6f, 16.4f),
                        PathNode.QuadTo(609.8f, 23.6f, 609.8f, 33.4f),
                        PathNode.Close,
                        PathNode.MoveTo(833.8f, 627.8f),
                        PathNode.LineTo(833.8f, 872.8f),
                        PathNode.QuadTo(833.8f, 943.7f, 798.4f, 1003.7f),
                        PathNode.QuadTo(763.0f, 1063.8f, 702.9f, 1098.7f),
                        PathNode.QuadTo(642.8f, 1133.7f, 571.9f, 1133.7f),
                        PathNode.QuadTo(500.2f, 1133.7f, 440.1f, 1098.7f),
                        PathNode.QuadTo(380.0f, 1063.8f, 345.1f, 1003.7f),
                        PathNode.QuadTo(310.1f, 943.7f, 310.1f, 872.8f),
                        PathNode.LineTo(310.1f, 627.8f),
                        PathNode.QuadTo(310.1f, 556.9f, 345.1f, 496.4f),
                        PathNode.QuadTo(380.0f, 435.9f, 440.1f, 400.9f),
                        PathNode.QuadTo(500.2f, 366.0f, 572.0f, 366.0f),
                        PathNode.QuadTo(642.8f, 366.0f, 702.9f, 401.4f),
                        PathNode.QuadTo(763.0f, 436.7f, 798.4f, 496.8f),
                        PathNode.QuadTo(833.8f, 556.9f, 833.8f, 627.8f),
                        PathNode.Close,
                        PathNode.MoveTo(386.8f, 627.8f),
                        PathNode.LineTo(386.8f, 872.8f),
                        PathNode.QuadTo(386.8f, 923.0f, 411.8f, 964.9f),
                        PathNode.QuadTo(436.9f, 1006.9f, 479.3f, 1031.4f),
                        PathNode.QuadTo(521.8f, 1056.0f, 571.9f, 1056.0f),
                        PathNode.QuadTo(622.0f, 1056.0f, 664.0f, 1031.4f),
                        PathNode.QuadTo(706.0f, 1006.9f, 731.0f, 964.9f),
                        PathNode.QuadTo(756.1f, 923.0f, 756.1f, 872.8f),
                        PathNode.LineTo(756.1f, 627.8f),
                        PathNode.QuadTo(756.1f, 577.7f, 731.0f, 535.2f),
                        PathNode.QuadTo(706.0f, 492.8f, 664.0f, 468.2f),
                        PathNode.QuadTo(622.1f, 443.7f, 571.9f, 443.7f),
                        PathNode.QuadTo(521.8f, 443.7f, 479.4f, 468.2f),
                        PathNode.QuadTo(436.9f, 492.8f, 411.8f, 535.2f),
                        PathNode.QuadTo(386.8f, 577.7f, 386.8f, 627.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _micNormal!!
    }

private var _micNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Mic: ImageVector
    get() {
        if (_micRegular != null) return _micRegular!!
        _micRegular = ImageVector.Builder(
            name = "Mic.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1009.9f, 622.4f),
                        PathNode.LineTo(1009.9f, 667.4f),
                        PathNode.QuadTo(1009.9f, 698.4f, 979.9f, 698.4f),
                        PathNode.LineTo(948.9f, 698.4f),
                        PathNode.QuadTo(933.9f, 698.4f, 926.9f, 689.9f),
                        PathNode.QuadTo(919.9f, 681.4f, 919.9f, 667.4f),
                        PathNode.LineTo(919.9f, 622.4f),
                        PathNode.QuadTo(919.9f, 527.4f, 872.9f, 447.4f),
                        PathNode.QuadTo(825.9f, 367.4f, 745.9f, 320.4f),
                        PathNode.QuadTo(665.9f, 273.4f, 571.9f, 273.4f),
                        PathNode.QuadTo(476.9f, 273.4f, 396.9f, 320.4f),
                        PathNode.QuadTo(316.9f, 367.4f, 269.9f, 447.4f),
                        PathNode.QuadTo(222.9f, 527.4f, 222.9f, 622.4f),
                        PathNode.LineTo(222.9f, 668.4f),
                        PathNode.QuadTo(222.9f, 698.4f, 190.9f, 698.4f),
                        PathNode.LineTo(165.9f, 698.4f),
                        PathNode.QuadTo(132.9f, 698.4f, 132.9f, 668.4f),
                        PathNode.LineTo(132.9f, 622.4f),
                        PathNode.QuadTo(132.9f, 502.4f, 191.9f, 401.9f),
                        PathNode.QuadTo(250.9f, 301.4f, 351.4f, 242.9f),
                        PathNode.QuadTo(451.9f, 184.4f, 571.9f, 184.4f),
                        PathNode.QuadTo(690.9f, 184.4f, 791.4f, 242.9f),
                        PathNode.QuadTo(891.9f, 301.4f, 950.9f, 402.4f),
                        PathNode.QuadTo(1009.9f, 503.4f, 1009.9f, 622.4f),
                        PathNode.Close,
                        PathNode.MoveTo(613.9f, 33.4f),
                        PathNode.LineTo(613.9f, 235.4f),
                        PathNode.LineTo(528.9f, 235.4f),
                        PathNode.LineTo(528.9f, 33.4f),
                        PathNode.QuadTo(528.9f, 22.4f, 536.9f, 14.4f),
                        PathNode.QuadTo(544.9f, 6.4f, 554.9f, 6.4f),
                        PathNode.LineTo(587.9f, 6.4f),
                        PathNode.QuadTo(597.9f, 6.4f, 605.9f, 14.4f),
                        PathNode.QuadTo(613.9f, 22.4f, 613.9f, 33.4f),
                        PathNode.Close,
                        PathNode.MoveTo(837.9f, 626.4f),
                        PathNode.LineTo(837.9f, 871.4f),
                        PathNode.QuadTo(837.9f, 943.4f, 801.9f, 1004.4f),
                        PathNode.QuadTo(765.9f, 1065.4f, 704.9f, 1100.9f),
                        PathNode.QuadTo(643.9f, 1136.4f, 571.9f, 1136.4f),
                        PathNode.QuadTo(498.9f, 1136.4f, 437.9f, 1100.9f),
                        PathNode.QuadTo(376.9f, 1065.4f, 341.4f, 1004.4f),
                        PathNode.QuadTo(305.9f, 943.4f, 305.9f, 871.4f),
                        PathNode.LineTo(305.9f, 626.4f),
                        PathNode.QuadTo(305.9f, 554.4f, 341.4f, 492.9f),
                        PathNode.QuadTo(376.9f, 431.4f, 437.9f, 395.9f),
                        PathNode.QuadTo(498.9f, 360.4f, 571.9f, 360.4f),
                        PathNode.QuadTo(643.9f, 360.4f, 704.9f, 396.4f),
                        PathNode.QuadTo(765.9f, 432.4f, 801.9f, 493.4f),
                        PathNode.QuadTo(837.9f, 554.4f, 837.9f, 626.4f),
                        PathNode.Close,
                        PathNode.MoveTo(390.9f, 626.4f),
                        PathNode.LineTo(390.9f, 871.4f),
                        PathNode.QuadTo(390.9f, 920.4f, 415.4f, 961.4f),
                        PathNode.QuadTo(439.9f, 1002.4f, 481.4f, 1026.4f),
                        PathNode.QuadTo(522.9f, 1050.4f, 571.9f, 1050.4f),
                        PathNode.QuadTo(620.9f, 1050.4f, 661.9f, 1026.4f),
                        PathNode.QuadTo(702.9f, 1002.4f, 727.4f, 961.4f),
                        PathNode.QuadTo(751.9f, 920.4f, 751.9f, 871.4f),
                        PathNode.LineTo(751.9f, 626.4f),
                        PathNode.QuadTo(751.9f, 577.4f, 727.4f, 535.9f),
                        PathNode.QuadTo(702.9f, 494.4f, 661.9f, 470.4f),
                        PathNode.QuadTo(620.9f, 446.4f, 571.9f, 446.4f),
                        PathNode.QuadTo(522.9f, 446.4f, 481.4f, 470.4f),
                        PathNode.QuadTo(439.9f, 494.4f, 415.4f, 535.9f),
                        PathNode.QuadTo(390.9f, 577.4f, 390.9f, 626.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _micRegular!!
    }

private var _micRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Mic: ImageVector
    get() {
        if (_micMedium != null) return _micMedium!!
        _micMedium = ImageVector.Builder(
            name = "Mic.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1021.0f, 619.7f),
                        PathNode.LineTo(1021.0f, 669.9f),
                        PathNode.QuadTo(1021.0f, 703.3f, 988.3f, 703.3f),
                        PathNode.LineTo(947.3f, 703.3f),
                        PathNode.QuadTo(931.2f, 703.3f, 923.3f, 694.4f),
                        PathNode.QuadTo(915.3f, 685.5f, 915.3f, 669.9f),
                        PathNode.LineTo(915.3f, 619.7f),
                        PathNode.QuadTo(915.3f, 527.2f, 869.4f, 449.5f),
                        PathNode.QuadTo(823.5f, 371.8f, 744.7f, 326.1f),
                        PathNode.QuadTo(665.9f, 280.5f, 572.1f, 280.5f),
                        PathNode.QuadTo(477.2f, 280.5f, 398.3f, 326.1f),
                        PathNode.QuadTo(319.4f, 371.8f, 273.4f, 449.5f),
                        PathNode.QuadTo(227.5f, 527.2f, 227.5f, 619.7f),
                        PathNode.LineTo(227.5f, 670.6f),
                        PathNode.QuadTo(227.5f, 703.3f, 193.4f, 703.3f),
                        PathNode.LineTo(156.7f, 703.3f),
                        PathNode.QuadTo(121.8f, 703.3f, 121.8f, 670.6f),
                        PathNode.LineTo(121.8f, 619.7f),
                        PathNode.QuadTo(121.8f, 498.2f, 181.9f, 396.4f),
                        PathNode.QuadTo(242.0f, 294.7f, 345.1f, 235.4f),
                        PathNode.QuadTo(448.1f, 176.1f, 571.9f, 176.1f),
                        PathNode.QuadTo(695.0f, 176.1f, 797.9f, 235.4f),
                        PathNode.QuadTo(900.8f, 294.7f, 960.9f, 396.8f),
                        PathNode.QuadTo(1021.0f, 498.8f, 1021.0f, 619.7f),
                        PathNode.Close,
                        PathNode.MoveTo(621.5f, 32.1f),
                        PathNode.LineTo(621.5f, 245.2f),
                        PathNode.LineTo(521.1f, 245.2f),
                        PathNode.LineTo(521.1f, 32.1f),
                        PathNode.QuadTo(521.1f, 18.6f, 529.8f, 10.1f),
                        PathNode.QuadTo(538.4f, 1.6f, 550.8f, 1.6f),
                        PathNode.LineTo(591.7f, 1.6f),
                        PathNode.QuadTo(604.4f, 1.6f, 613.0f, 10.1f),
                        PathNode.QuadTo(621.5f, 18.6f, 621.5f, 32.1f),
                        PathNode.Close,
                        PathNode.MoveTo(844.1f, 631.6f),
                        PathNode.LineTo(844.1f, 869.8f),
                        PathNode.QuadTo(844.1f, 943.5f, 807.3f, 1006.0f),
                        PathNode.QuadTo(770.5f, 1068.4f, 708.0f, 1104.8f),
                        PathNode.QuadTo(645.5f, 1141.3f, 571.7f, 1141.3f),
                        PathNode.QuadTo(496.9f, 1141.3f, 434.5f, 1104.8f),
                        PathNode.QuadTo(372.1f, 1068.4f, 335.6f, 1006.0f),
                        PathNode.QuadTo(299.2f, 943.5f, 299.2f, 869.8f),
                        PathNode.LineTo(299.2f, 631.6f),
                        PathNode.QuadTo(299.2f, 557.7f, 335.5f, 494.7f),
                        PathNode.QuadTo(371.9f, 431.6f, 434.3f, 395.2f),
                        PathNode.QuadTo(496.8f, 358.8f, 571.6f, 358.8f),
                        PathNode.QuadTo(645.4f, 358.8f, 708.0f, 395.8f),
                        PathNode.QuadTo(770.5f, 432.7f, 807.3f, 495.2f),
                        PathNode.QuadTo(844.1f, 557.8f, 844.1f, 631.6f),
                        PathNode.Close,
                        PathNode.MoveTo(399.6f, 631.6f),
                        PathNode.LineTo(399.6f, 869.8f),
                        PathNode.QuadTo(399.6f, 916.4f, 422.9f, 955.3f),
                        PathNode.QuadTo(446.2f, 994.3f, 485.6f, 1017.1f),
                        PathNode.QuadTo(525.1f, 1039.9f, 571.6f, 1039.9f),
                        PathNode.QuadTo(618.2f, 1039.9f, 657.2f, 1017.1f),
                        PathNode.QuadTo(696.2f, 994.3f, 719.5f, 955.3f),
                        PathNode.QuadTo(742.7f, 916.4f, 742.7f, 869.8f),
                        PathNode.LineTo(742.7f, 631.6f),
                        PathNode.QuadTo(742.7f, 585.0f, 719.5f, 545.5f),
                        PathNode.QuadTo(696.2f, 506.0f, 657.2f, 483.1f),
                        PathNode.QuadTo(618.2f, 460.2f, 571.7f, 460.2f),
                        PathNode.QuadTo(525.1f, 460.2f, 485.6f, 483.1f),
                        PathNode.QuadTo(446.2f, 505.9f, 422.9f, 545.5f),
                        PathNode.QuadTo(399.6f, 585.0f, 399.6f, 631.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _micMedium!!
    }

private var _micMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Mic: ImageVector
    get() {
        if (_micDemibold != null) return _micDemibold!!
        _micDemibold = ImageVector.Builder(
            name = "Mic.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1028.8f, 617.8f),
                        PathNode.LineTo(1028.8f, 671.6f),
                        PathNode.QuadTo(1028.8f, 706.7f, 994.2f, 706.7f),
                        PathNode.LineTo(946.2f, 706.7f),
                        PathNode.QuadTo(929.3f, 706.7f, 920.7f, 697.5f),
                        PathNode.QuadTo(912.1f, 688.3f, 912.1f, 671.6f),
                        PathNode.LineTo(912.1f, 617.8f),
                        PathNode.QuadTo(912.1f, 527.0f, 867.0f, 450.9f),
                        PathNode.QuadTo(821.8f, 374.8f, 743.9f, 330.1f),
                        PathNode.QuadTo(665.9f, 285.4f, 572.2f, 285.4f),
                        PathNode.QuadTo(477.4f, 285.4f, 399.2f, 330.1f),
                        PathNode.QuadTo(321.1f, 374.8f, 275.9f, 450.9f),
                        PathNode.QuadTo(230.7f, 527.0f, 230.7f, 617.8f),
                        PathNode.LineTo(230.7f, 672.1f),
                        PathNode.QuadTo(230.7f, 706.7f, 195.1f, 706.7f),
                        PathNode.LineTo(150.3f, 706.7f),
                        PathNode.QuadTo(114.1f, 706.7f, 114.1f, 672.1f),
                        PathNode.LineTo(114.1f, 617.8f),
                        PathNode.QuadTo(114.1f, 495.3f, 174.9f, 392.6f),
                        PathNode.QuadTo(235.8f, 289.9f, 340.6f, 230.1f),
                        PathNode.QuadTo(445.5f, 170.2f, 571.9f, 170.2f),
                        PathNode.QuadTo(697.8f, 170.2f, 802.5f, 230.1f),
                        PathNode.QuadTo(907.1f, 289.9f, 967.9f, 392.8f),
                        PathNode.QuadTo(1028.8f, 495.6f, 1028.8f, 617.8f),
                        PathNode.Close,
                        PathNode.MoveTo(626.8f, 31.1f),
                        PathNode.LineTo(626.8f, 252.0f),
                        PathNode.LineTo(515.6f, 252.0f),
                        PathNode.LineTo(515.6f, 31.1f),
                        PathNode.QuadTo(515.6f, 16.0f, 524.8f, 7.1f),
                        PathNode.QuadTo(533.9f, -1.8f, 548.0f, -1.8f),
                        PathNode.LineTo(594.4f, -1.8f),
                        PathNode.QuadTo(609.0f, -1.8f, 617.9f, 7.1f),
                        PathNode.QuadTo(626.8f, 16.0f, 626.8f, 31.1f),
                        PathNode.Close,
                        PathNode.MoveTo(848.5f, 635.2f),
                        PathNode.LineTo(848.5f, 868.7f),
                        PathNode.QuadTo(848.5f, 943.6f, 811.1f, 1007.0f),
                        PathNode.QuadTo(773.7f, 1070.5f, 710.1f, 1107.6f),
                        PathNode.QuadTo(646.5f, 1144.7f, 571.5f, 1144.7f),
                        PathNode.QuadTo(495.5f, 1144.7f, 432.1f, 1107.6f),
                        PathNode.QuadTo(368.7f, 1070.5f, 331.5f, 1007.0f),
                        PathNode.QuadTo(294.4f, 943.6f, 294.4f, 868.7f),
                        PathNode.LineTo(294.4f, 635.2f),
                        PathNode.QuadTo(294.4f, 560.0f, 331.4f, 495.9f),
                        PathNode.QuadTo(368.3f, 431.7f, 431.8f, 394.7f),
                        PathNode.QuadTo(495.3f, 357.7f, 571.4f, 357.7f),
                        PathNode.QuadTo(646.5f, 357.7f, 710.1f, 395.3f),
                        PathNode.QuadTo(773.7f, 432.9f, 811.1f, 496.5f),
                        PathNode.QuadTo(848.5f, 560.1f, 848.5f, 635.2f),
                        PathNode.Close,
                        PathNode.MoveTo(405.6f, 635.2f),
                        PathNode.LineTo(405.6f, 868.7f),
                        PathNode.QuadTo(405.6f, 913.5f, 428.1f, 951.0f),
                        PathNode.QuadTo(450.5f, 988.6f, 488.5f, 1010.5f),
                        PathNode.QuadTo(526.6f, 1032.5f, 571.4f, 1032.5f),
                        PathNode.QuadTo(616.3f, 1032.5f, 653.9f, 1010.5f),
                        PathNode.QuadTo(691.4f, 988.6f, 713.9f, 951.0f),
                        PathNode.QuadTo(736.3f, 913.5f, 736.3f, 868.7f),
                        PathNode.LineTo(736.3f, 635.2f),
                        PathNode.QuadTo(736.3f, 590.3f, 713.9f, 552.2f),
                        PathNode.QuadTo(691.4f, 514.2f, 653.9f, 492.0f),
                        PathNode.QuadTo(616.3f, 469.9f, 571.5f, 469.9f),
                        PathNode.QuadTo(526.6f, 469.9f, 488.6f, 491.9f),
                        PathNode.QuadTo(450.5f, 514.0f, 428.1f, 552.1f),
                        PathNode.QuadTo(405.6f, 590.3f, 405.6f, 635.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _micDemibold!!
    }

private var _micDemibold: ImageVector? = null
