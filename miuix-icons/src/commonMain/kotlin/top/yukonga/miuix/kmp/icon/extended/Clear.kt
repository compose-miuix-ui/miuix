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

val MiuixIcons.Clear: ImageVector
    get() = MiuixIcons.Regular.Clear

val MiuixIcons.Light.Clear: ImageVector
    get() {
        if (_clearLight != null) return _clearLight!!
        _clearLight = ImageVector.Builder(
            name = "Clear.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1132.8f,
            viewportHeight = 1132.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1132.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(639.9f, 288.4f),
                        PathNode.LineTo(984.9f, 633.4f),
                        PathNode.QuadTo(1013.9f, 662.4f, 1013.9f, 701.9f),
                        PathNode.QuadTo(1013.9f, 741.4f, 984.9f, 769.4f),
                        PathNode.LineTo(749.9f, 1006.4f),
                        PathNode.QuadTo(730.9f, 1025.4f, 705.9f, 1031.9f),
                        PathNode.QuadTo(680.9f, 1038.4f, 656.4f, 1031.9f),
                        PathNode.QuadTo(631.9f, 1025.4f, 612.9f, 1006.4f),
                        PathNode.LineTo(161.9f, 555.4f),
                        PathNode.QuadTo(142.9f, 536.4f, 135.9f, 511.9f),
                        PathNode.QuadTo(128.9f, 487.4f, 135.9f, 463.4f),
                        PathNode.QuadTo(142.9f, 439.4f, 161.9f, 420.4f),
                        PathNode.LineTo(293.9f, 288.4f),
                        PathNode.QuadTo(307.9f, 275.4f, 325.4f, 267.9f),
                        PathNode.QuadTo(342.9f, 260.4f, 361.9f, 260.4f),
                        PathNode.HorizontalTo(572.9f),
                        PathNode.QuadTo(591.9f, 260.4f, 608.4f, 266.9f),
                        PathNode.QuadTo(624.9f, 273.4f, 639.9f, 288.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1012.9f, 111.4f),
                        PathNode.VerticalTo(135.4f),
                        PathNode.QuadTo(1012.9f, 142.4f, 1007.4f, 147.4f),
                        PathNode.QuadTo(1001.9f, 152.4f, 994.9f, 152.4f),
                        PathNode.HorizontalTo(136.9f),
                        PathNode.QuadTo(128.9f, 152.4f, 123.9f, 147.4f),
                        PathNode.QuadTo(118.9f, 142.4f, 118.9f, 135.4f),
                        PathNode.VerticalTo(111.4f),
                        PathNode.QuadTo(118.9f, 103.4f, 123.9f, 98.9f),
                        PathNode.QuadTo(128.9f, 94.4f, 136.9f, 94.4f),
                        PathNode.HorizontalTo(994.9f),
                        PathNode.QuadTo(1002.9f, 94.4f, 1007.9f, 99.4f),
                        PathNode.QuadTo(1012.9f, 104.4f, 1012.9f, 111.4f),
                        PathNode.Close,
                        PathNode.MoveTo(328.9f, 337.4f),
                        PathNode.LineTo(218.9f, 446.4f),
                        PathNode.QuadTo(197.9f, 467.4f, 197.9f, 487.4f),
                        PathNode.QuadTo(197.9f, 507.4f, 218.9f, 529.4f),
                        PathNode.LineTo(333.9f, 643.4f),
                        PathNode.QuadTo(342.9f, 652.4f, 350.9f, 643.4f),
                        PathNode.LineTo(623.9f, 371.4f),
                        PathNode.QuadTo(632.9f, 362.4f, 622.9f, 354.4f),
                        PathNode.LineTo(602.9f, 335.4f),
                        PathNode.QuadTo(585.9f, 319.4f, 565.9f, 319.4f),
                        PathNode.HorizontalTo(371.9f),
                        PathNode.QuadTo(345.9f, 319.4f, 328.9f, 337.4f),
                        PathNode.Close,
                        PathNode.MoveTo(664.9f, 413.4f),
                        PathNode.LineTo(392.9f, 685.4f),
                        PathNode.QuadTo(385.9f, 692.4f, 392.9f, 701.4f),
                        PathNode.LineTo(639.9f, 949.4f),
                        PathNode.QuadTo(660.9f, 970.4f, 681.4f, 970.4f),
                        PathNode.QuadTo(701.9f, 970.4f, 721.9f, 949.4f),
                        PathNode.LineTo(928.9f, 742.4f),
                        PathNode.QuadTo(948.9f, 723.4f, 948.9f, 701.9f),
                        PathNode.QuadTo(948.9f, 680.4f, 928.9f, 660.4f),
                        PathNode.LineTo(679.9f, 413.4f),
                        PathNode.QuadTo(675.9f, 409.4f, 671.9f, 409.4f),
                        PathNode.QuadTo(667.9f, 409.4f, 664.9f, 413.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearLight!!
    }

private var _clearLight: ImageVector? = null

val MiuixIcons.Normal.Clear: ImageVector
    get() {
        if (_clearNormal != null) return _clearNormal!!
        _clearNormal = ImageVector.Builder(
            name = "Clear.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1154.2f,
            viewportHeight = 1154.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1154.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(652.3f, 292.9f),
                        PathNode.LineTo(997.3f, 637.9f),
                        PathNode.QuadTo(1029.1f, 669.0f, 1029.1f, 712.6f),
                        PathNode.QuadTo(1029.1f, 756.2f, 997.3f, 787.0f),
                        PathNode.LineTo(762.3f, 1024.0f),
                        PathNode.QuadTo(742.0f, 1044.4f, 714.6f, 1051.2f),
                        PathNode.QuadTo(687.2f, 1058.1f, 660.2f, 1051.2f),
                        PathNode.QuadTo(633.3f, 1044.4f, 613.0f, 1024.0f),
                        PathNode.LineTo(162.0f, 573.0f),
                        PathNode.QuadTo(141.6f, 552.6f, 134.6f, 525.7f),
                        PathNode.QuadTo(127.6f, 498.8f, 134.6f, 472.1f),
                        PathNode.QuadTo(141.6f, 445.3f, 162.0f, 424.9f),
                        PathNode.LineTo(294.0f, 292.9f),
                        PathNode.QuadTo(308.7f, 278.6f, 327.9f, 270.4f),
                        PathNode.QuadTo(347.1f, 262.2f, 368.2f, 262.2f),
                        PathNode.HorizontalTo(578.5f),
                        PathNode.QuadTo(599.5f, 262.2f, 618.4f, 270.1f),
                        PathNode.QuadTo(637.3f, 277.9f, 652.3f, 292.9f),
                        PathNode.Close,
                        PathNode.MoveTo(1019.2f, 119.4f),
                        PathNode.VerticalTo(149.6f),
                        PathNode.QuadTo(1019.2f, 159.3f, 1011.9f, 166.0f),
                        PathNode.QuadTo(1004.7f, 172.8f, 995.0f, 172.8f),
                        PathNode.HorizontalTo(149.3f),
                        PathNode.QuadTo(138.6f, 172.8f, 131.9f, 166.0f),
                        PathNode.QuadTo(125.2f, 159.3f, 125.2f, 149.6f),
                        PathNode.VerticalTo(119.4f),
                        PathNode.QuadTo(125.2f, 109.3f, 131.9f, 102.8f),
                        PathNode.QuadTo(138.6f, 96.2f, 149.3f, 96.2f),
                        PathNode.HorizontalTo(995.0f),
                        PathNode.QuadTo(1005.0f, 96.2f, 1012.1f, 102.9f),
                        PathNode.QuadTo(1019.2f, 109.6f, 1019.2f, 119.4f),
                        PathNode.Close,
                        PathNode.MoveTo(341.3f, 355.0f),
                        PathNode.LineTo(233.4f, 462.6f),
                        PathNode.QuadTo(217.2f, 478.8f, 217.2f, 498.8f),
                        PathNode.QuadTo(217.2f, 518.8f, 233.4f, 535.3f),
                        PathNode.LineTo(340.8f, 641.8f),
                        PathNode.QuadTo(349.8f, 650.8f, 357.8f, 641.8f),
                        PathNode.LineTo(617.1f, 382.8f),
                        PathNode.QuadTo(626.1f, 374.5f, 616.8f, 365.8f),
                        PathNode.LineTo(604.3f, 354.4f),
                        PathNode.QuadTo(587.3f, 339.8f, 569.4f, 339.8f),
                        PathNode.HorizontalTo(378.2f),
                        PathNode.QuadTo(356.3f, 339.8f, 341.3f, 355.0f),
                        PathNode.Close,
                        PathNode.MoveTo(670.5f, 437.9f),
                        PathNode.LineTo(412.2f, 696.1f),
                        PathNode.QuadTo(404.5f, 703.8f, 412.2f, 712.8f),
                        PathNode.LineTo(651.0f, 952.6f),
                        PathNode.QuadTo(667.2f, 968.8f, 687.7f, 968.8f),
                        PathNode.QuadTo(708.2f, 968.8f, 723.3f, 952.6f),
                        PathNode.LineTo(927.6f, 748.3f),
                        PathNode.QuadTo(943.5f, 733.4f, 943.5f, 713.0f),
                        PathNode.QuadTo(943.5f, 692.5f, 927.6f, 676.6f),
                        PathNode.LineTo(686.8f, 437.9f),
                        PathNode.QuadTo(682.8f, 433.9f, 678.5f, 433.9f),
                        PathNode.QuadTo(674.2f, 433.9f, 670.5f, 437.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearNormal!!
    }

private var _clearNormal: ImageVector? = null

val MiuixIcons.Regular.Clear: ImageVector
    get() {
        if (_clearRegular != null) return _clearRegular!!
        _clearRegular = ImageVector.Builder(
            name = "Clear.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1164.0f,
            viewportHeight = 1164.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1164.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(658.5f, 295.0f),
                        PathNode.LineTo(1003.5f, 640.0f),
                        PathNode.QuadTo(1036.5f, 672.0f, 1036.5f, 717.5f),
                        PathNode.QuadTo(1036.5f, 763.0f, 1003.5f, 795.0f),
                        PathNode.LineTo(768.5f, 1032.0f),
                        PathNode.QuadTo(747.5f, 1053.0f, 719.0f, 1060.0f),
                        PathNode.QuadTo(690.5f, 1067.0f, 662.5f, 1060.0f),
                        PathNode.QuadTo(634.5f, 1053.0f, 613.5f, 1032.0f),
                        PathNode.LineTo(162.5f, 581.0f),
                        PathNode.QuadTo(141.5f, 560.0f, 134.5f, 532.0f),
                        PathNode.QuadTo(127.5f, 504.0f, 134.5f, 476.0f),
                        PathNode.QuadTo(141.5f, 448.0f, 162.5f, 427.0f),
                        PathNode.LineTo(294.5f, 295.0f),
                        PathNode.QuadTo(309.5f, 280.0f, 329.5f, 271.5f),
                        PathNode.QuadTo(349.5f, 263.0f, 371.5f, 263.0f),
                        PathNode.HorizontalTo(581.5f),
                        PathNode.QuadTo(603.5f, 263.0f, 623.5f, 271.5f),
                        PathNode.QuadTo(643.5f, 280.0f, 658.5f, 295.0f),
                        PathNode.Close,
                        PathNode.MoveTo(1022.5f, 123.0f),
                        PathNode.VerticalTo(156.0f),
                        PathNode.QuadTo(1022.5f, 167.0f, 1014.5f, 174.5f),
                        PathNode.QuadTo(1006.5f, 182.0f, 995.5f, 182.0f),
                        PathNode.HorizontalTo(155.5f),
                        PathNode.QuadTo(143.5f, 182.0f, 136.0f, 174.5f),
                        PathNode.QuadTo(128.5f, 167.0f, 128.5f, 156.0f),
                        PathNode.VerticalTo(123.0f),
                        PathNode.QuadTo(128.5f, 112.0f, 136.0f, 104.5f),
                        PathNode.QuadTo(143.5f, 97.0f, 155.5f, 97.0f),
                        PathNode.HorizontalTo(995.5f),
                        PathNode.QuadTo(1006.5f, 97.0f, 1014.5f, 104.5f),
                        PathNode.QuadTo(1022.5f, 112.0f, 1022.5f, 123.0f),
                        PathNode.Close,
                        PathNode.MoveTo(347.5f, 363.0f),
                        PathNode.LineTo(240.5f, 470.0f),
                        PathNode.QuadTo(226.5f, 484.0f, 226.5f, 504.0f),
                        PathNode.QuadTo(226.5f, 524.0f, 240.5f, 538.0f),
                        PathNode.LineTo(344.5f, 641.0f),
                        PathNode.QuadTo(353.5f, 650.0f, 361.5f, 641.0f),
                        PathNode.LineTo(614.5f, 388.0f),
                        PathNode.QuadTo(623.5f, 380.0f, 614.5f, 371.0f),
                        PathNode.LineTo(605.5f, 363.0f),
                        PathNode.QuadTo(588.5f, 349.0f, 571.5f, 349.0f),
                        PathNode.HorizontalTo(381.5f),
                        PathNode.QuadTo(361.5f, 349.0f, 347.5f, 363.0f),
                        PathNode.Close,
                        PathNode.MoveTo(673.5f, 449.0f),
                        PathNode.LineTo(421.5f, 701.0f),
                        PathNode.QuadTo(413.5f, 709.0f, 421.5f, 718.0f),
                        PathNode.LineTo(656.5f, 954.0f),
                        PathNode.QuadTo(670.5f, 968.0f, 691.0f, 968.0f),
                        PathNode.QuadTo(711.5f, 968.0f, 724.5f, 954.0f),
                        PathNode.LineTo(927.5f, 751.0f),
                        PathNode.QuadTo(941.5f, 738.0f, 941.5f, 718.0f),
                        PathNode.QuadTo(941.5f, 698.0f, 927.5f, 684.0f),
                        PathNode.LineTo(690.5f, 449.0f),
                        PathNode.QuadTo(686.5f, 445.0f, 682.0f, 445.0f),
                        PathNode.QuadTo(677.5f, 445.0f, 673.5f, 449.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearRegular!!
    }

private var _clearRegular: ImageVector? = null

val MiuixIcons.Medium.Clear: ImageVector
    get() {
        if (_clearMedium != null) return _clearMedium!!
        _clearMedium = ImageVector.Builder(
            name = "Clear.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1190.1f,
            viewportHeight = 1190.1f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1190.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(677.4f, 304.8f),
                        PathNode.LineTo(1022.4f, 649.8f),
                        PathNode.QuadTo(1057.8f, 684.2f, 1057.8f, 732.9f),
                        PathNode.QuadTo(1057.8f, 781.6f, 1022.4f, 816.0f),
                        PathNode.LineTo(787.4f, 1053.0f),
                        PathNode.QuadTo(764.7f, 1075.8f, 734.1f, 1083.4f),
                        PathNode.QuadTo(703.6f, 1090.9f, 673.2f, 1083.4f),
                        PathNode.QuadTo(642.9f, 1075.8f, 620.7f, 1053.0f),
                        PathNode.LineTo(169.7f, 602.0f),
                        PathNode.QuadTo(147.5f, 579.8f, 139.9f, 549.5f),
                        PathNode.QuadTo(132.3f, 519.1f, 139.9f, 489.1f),
                        PathNode.QuadTo(147.5f, 459.0f, 169.7f, 436.8f),
                        PathNode.LineTo(301.7f, 304.8f),
                        PathNode.QuadTo(317.9f, 288.6f, 339.3f, 279.6f),
                        PathNode.QuadTo(360.8f, 270.5f, 384.6f, 270.5f),
                        PathNode.HorizontalTo(594.6f),
                        PathNode.QuadTo(618.3f, 270.5f, 639.8f, 279.6f),
                        PathNode.QuadTo(661.3f, 288.6f, 677.4f, 304.8f),
                        PathNode.Close,
                        PathNode.MoveTo(1043.8f, 133.4f),
                        PathNode.VerticalTo(166.4f),
                        PathNode.QuadTo(1043.8f, 180.4f, 1033.1f, 190.5f),
                        PathNode.QuadTo(1022.5f, 200.6f, 1008.6f, 200.6f),
                        PathNode.HorizontalTo(168.6f),
                        PathNode.QuadTo(153.6f, 200.6f, 143.5f, 190.5f),
                        PathNode.QuadTo(133.3f, 180.4f, 133.3f, 166.4f),
                        PathNode.VerticalTo(133.4f),
                        PathNode.QuadTo(133.3f, 118.9f, 143.5f, 109.0f),
                        PathNode.QuadTo(153.6f, 99.2f, 168.6f, 99.2f),
                        PathNode.HorizontalTo(1008.6f),
                        PathNode.QuadTo(1023.1f, 99.2f, 1033.4f, 109.3f),
                        PathNode.QuadTo(1043.8f, 119.5f, 1043.8f, 133.4f),
                        PathNode.Close,
                        PathNode.MoveTo(364.7f, 385.8f),
                        PathNode.LineTo(259.4f, 491.0f),
                        PathNode.QuadTo(247.8f, 502.6f, 247.8f, 519.4f),
                        PathNode.QuadTo(247.8f, 536.2f, 259.4f, 547.8f),
                        PathNode.LineTo(357.6f, 644.9f),
                        PathNode.QuadTo(366.6f, 653.9f, 374.6f, 644.9f),
                        PathNode.LineTo(616.4f, 403.1f),
                        PathNode.QuadTo(623.6f, 396.3f, 616.4f, 389.1f),
                        PathNode.LineTo(609.1f, 382.8f),
                        PathNode.QuadTo(595.7f, 372.4f, 581.6f, 372.4f),
                        PathNode.HorizontalTo(397.5f),
                        PathNode.QuadTo(378.1f, 372.4f, 364.7f, 385.8f),
                        PathNode.Close,
                        PathNode.MoveTo(684.2f, 478.2f),
                        PathNode.LineTo(445.7f, 716.7f),
                        PathNode.QuadTo(437.7f, 724.7f, 445.7f, 733.7f),
                        PathNode.LineTo(675.4f, 963.8f),
                        PathNode.QuadTo(687.1f, 975.5f, 703.8f, 975.5f),
                        PathNode.QuadTo(720.4f, 975.5f, 731.7f, 963.8f),
                        PathNode.LineTo(934.7f, 760.8f),
                        PathNode.QuadTo(946.3f, 749.6f, 946.3f, 733.1f),
                        PathNode.QuadTo(946.3f, 716.6f, 934.7f, 705.0f),
                        PathNode.LineTo(705.3f, 477.6f),
                        PathNode.QuadTo(700.1f, 472.5f, 694.8f, 472.8f),
                        PathNode.QuadTo(689.4f, 473.1f, 684.2f, 478.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearMedium!!
    }

private var _clearMedium: ImageVector? = null

val MiuixIcons.Demibold.Clear: ImageVector
    get() {
        if (_clearDemibold != null) return _clearDemibold!!
        _clearDemibold = ImageVector.Builder(
            name = "Clear.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1208.4f,
            viewportHeight = 1208.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1208.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(690.7f, 311.7f),
                        PathNode.LineTo(1035.7f, 656.7f),
                        PathNode.QuadTo(1072.7f, 692.7f, 1072.7f, 743.7f),
                        PathNode.QuadTo(1072.7f, 794.7f, 1035.7f, 830.7f),
                        PathNode.LineTo(800.7f, 1067.7f),
                        PathNode.QuadTo(776.7f, 1091.7f, 744.7f, 1099.7f),
                        PathNode.QuadTo(712.7f, 1107.7f, 680.7f, 1099.7f),
                        PathNode.QuadTo(648.7f, 1091.7f, 625.7f, 1067.7f),
                        PathNode.LineTo(174.7f, 616.7f),
                        PathNode.QuadTo(151.7f, 593.7f, 143.7f, 561.7f),
                        PathNode.QuadTo(135.7f, 529.7f, 143.7f, 498.2f),
                        PathNode.QuadTo(151.7f, 466.7f, 174.7f, 443.7f),
                        PathNode.LineTo(306.7f, 311.7f),
                        PathNode.QuadTo(323.7f, 294.7f, 346.2f, 285.2f),
                        PathNode.QuadTo(368.7f, 275.7f, 393.7f, 275.7f),
                        PathNode.HorizontalTo(603.7f),
                        PathNode.QuadTo(628.7f, 275.7f, 651.2f, 285.2f),
                        PathNode.QuadTo(673.7f, 294.7f, 690.7f, 311.7f),
                        PathNode.Close,
                        PathNode.MoveTo(1058.7f, 140.7f),
                        PathNode.VerticalTo(173.7f),
                        PathNode.QuadTo(1058.7f, 189.7f, 1046.2f, 201.7f),
                        PathNode.QuadTo(1033.7f, 213.7f, 1017.7f, 213.7f),
                        PathNode.HorizontalTo(177.7f),
                        PathNode.QuadTo(160.7f, 213.7f, 148.7f, 201.7f),
                        PathNode.QuadTo(136.7f, 189.7f, 136.7f, 173.7f),
                        PathNode.VerticalTo(140.7f),
                        PathNode.QuadTo(136.7f, 123.7f, 148.7f, 112.2f),
                        PathNode.QuadTo(160.7f, 100.7f, 177.7f, 100.7f),
                        PathNode.HorizontalTo(1017.7f),
                        PathNode.QuadTo(1034.7f, 100.7f, 1046.7f, 112.7f),
                        PathNode.QuadTo(1058.7f, 124.7f, 1058.7f, 140.7f),
                        PathNode.Close,
                        PathNode.MoveTo(376.7f, 401.7f),
                        PathNode.LineTo(272.7f, 505.7f),
                        PathNode.QuadTo(262.7f, 515.7f, 262.7f, 530.2f),
                        PathNode.QuadTo(262.7f, 544.7f, 272.7f, 554.7f),
                        PathNode.LineTo(366.7f, 647.7f),
                        PathNode.QuadTo(375.7f, 656.7f, 383.7f, 647.7f),
                        PathNode.LineTo(617.7f, 413.7f),
                        PathNode.QuadTo(623.7f, 407.7f, 617.7f, 401.7f),
                        PathNode.LineTo(611.7f, 396.7f),
                        PathNode.QuadTo(600.7f, 388.7f, 588.7f, 388.7f),
                        PathNode.HorizontalTo(408.7f),
                        PathNode.QuadTo(389.7f, 388.7f, 376.7f, 401.7f),
                        PathNode.Close,
                        PathNode.MoveTo(691.7f, 498.7f),
                        PathNode.LineTo(462.7f, 727.7f),
                        PathNode.QuadTo(454.7f, 735.7f, 462.7f, 744.7f),
                        PathNode.LineTo(688.7f, 970.7f),
                        PathNode.QuadTo(698.7f, 980.7f, 712.7f, 980.7f),
                        PathNode.QuadTo(726.7f, 980.7f, 736.7f, 970.7f),
                        PathNode.LineTo(939.7f, 767.7f),
                        PathNode.QuadTo(949.7f, 757.7f, 949.7f, 743.7f),
                        PathNode.QuadTo(949.7f, 729.7f, 939.7f, 719.7f),
                        PathNode.LineTo(715.7f, 497.7f),
                        PathNode.QuadTo(709.7f, 491.7f, 703.7f, 492.2f),
                        PathNode.QuadTo(697.7f, 492.7f, 691.7f, 498.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _clearDemibold!!
    }

private var _clearDemibold: ImageVector? = null
