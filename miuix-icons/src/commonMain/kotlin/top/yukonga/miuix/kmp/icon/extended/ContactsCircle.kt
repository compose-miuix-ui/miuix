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

val MiuixIcons.ContactsCircle: ImageVector
    get() = MiuixIcons.Regular.ContactsCircle

val MiuixIcons.Light.ContactsCircle: ImageVector
    get() {
        if (_contactscircleLight != null) return _contactscircleLight!!
        _contactscircleLight = ImageVector.Builder(
            name = "ContactsCircle.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1168.8f,
            viewportHeight = 1168.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -80.60000000000002f, translationY = 958.9f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1152.0f, 374.217665615142f),
                PathNode.QuadTo(1152.0f, 507.0f, 1086.5851735015772f, 618.6611570247934f),
                PathNode.QuadTo(1021.1703470031546f, 730.3223140495868f, 908.8911671924291f, 795.6611570247934f),
                PathNode.QuadTo(796.6119873817034f, 861.0f, 664.8059936908518f, 861.0f),
                PathNode.QuadTo(533.0f, 861.0f, 420.8434065934066f, 795.5851735015773f),
                PathNode.QuadTo(308.6868131868132f, 730.1703470031546f, 243.3434065934066f, 618.3793375394322f),
                PathNode.QuadTo(178.0f, 506.5883280757098f, 178.0f, 373.8059936908517f),
                PathNode.QuadTo(178.0f, 242.0f, 243.4148264984227f, 130.3388429752066f),
                PathNode.QuadTo(308.8296529968454f, 18.677685950413224f, 421.108832807571f, -46.66115702479339f),
                PathNode.QuadTo(533.3880126182966f, -112.0f, 665.1940063091482f, -112.0f),
                PathNode.QuadTo(797.0f, -112.0f, 909.1565934065934f, -46.58517350157729f),
                PathNode.QuadTo(1021.3131868131868f, 18.829652996845425f, 1086.6565934065934f, 130.62066246056781f),
                PathNode.QuadTo(1152.0f, 242.41167192429023f, 1152.0f, 374.217665615142f),
                PathNode.Close,
                PathNode.MoveTo(924.0f, -28.0f),
                PathNode.QuadTo(955.0f, -11.0f, 982.0f, 14.0f),
                PathNode.QuadTo(1009.0f, 39.0f, 1023.0f, 64.0f),
                PathNode.QuadTo(1029.0f, 74.0f, 1026.0f, 79.0f),
                PathNode.QuadTo(953.0f, 182.0f, 860.5f, 241.0f),
                PathNode.QuadTo(768.0f, 300.0f, 665.0f, 300.0f),
                PathNode.QuadTo(562.0f, 300.0f, 469.0f, 241.0f),
                PathNode.QuadTo(376.0f, 182.0f, 301.0f, 75.91964285714286f),
                PathNode.QuadTo(300.0f, 73.0f, 306.0f, 67.0f),
                PathNode.QuadTo(306.0f, 67.0f, 310.0f, 63.0f),
                PathNode.QuadTo(347.0f, 5.0f, 407.0f, -28.0f),
                PathNode.QuadTo(444.0f, -46.0f, 501.0f, -57.0f),
                PathNode.QuadTo(558.0f, -68.0f, 627.0f, -68.0f),
                PathNode.HorizontalTo(703.0f),
                PathNode.QuadTo(774.0f, -68.0f, 830.5f, -57.0f),
                PathNode.QuadTo(887.0f, -46.0f, 924.0f, -28.0f),
                PathNode.Close,
                PathNode.MoveTo(241.0f, 373.8395061728395f),
                PathNode.QuadTo(241.0f, 489.0f, 298.0f, 586.5f),
                PathNode.QuadTo(355.0f, 684.0f, 452.5f, 741.0f),
                PathNode.QuadTo(550.0f, 798.0f, 665.0f, 798.0f),
                PathNode.QuadTo(780.0f, 798.0f, 877.5f, 741.0f),
                PathNode.QuadTo(975.0f, 684.0f, 1032.0f, 586.5f),
                PathNode.QuadTo(1089.0f, 489.0f, 1089.0f, 373.8395061728395f),
                PathNode.QuadTo(1089.0f, 258.679012345679f, 1032.0f, 161.8395061728395f),
                PathNode.QuadTo(975.0f, 65.0f, 877.5f, 8.0f),
                PathNode.QuadTo(780.0f, -49.0f, 665.0f, -49.0f),
                PathNode.QuadTo(550.0f, -49.0f, 452.5f, 8.0f),
                PathNode.QuadTo(355.0f, 65.0f, 298.0f, 161.8395061728395f),
                PathNode.QuadTo(241.0f, 258.679012345679f, 241.0f, 373.8395061728395f),
                PathNode.Close,
                PathNode.MoveTo(817.0f, 515.7336683417085f),
                PathNode.QuadTo(817.0f, 557.4371859296482f, 796.5526315789473f, 592.3517587939698f),
                PathNode.QuadTo(776.1052631578947f, 627.2663316582915f, 741.0526315789473f, 647.6331658291458f),
                PathNode.QuadTo(706.0f, 668.0f, 665.0f, 668.0f),
                PathNode.QuadTo(624.0f, 668.0f, 588.9473684210527f, 647.6331658291458f),
                PathNode.QuadTo(553.8947368421053f, 627.2663316582915f, 533.4473684210527f, 592.3517587939698f),
                PathNode.QuadTo(513.0f, 557.4371859296482f, 513.0f, 515.7336683417085f),
                PathNode.QuadTo(513.0f, 475.0f, 533.5f, 440.5f),
                PathNode.QuadTo(554.0f, 406.0f, 589.0f, 385.5f),
                PathNode.QuadTo(624.0f, 365.0f, 665.0f, 365.0f),
                PathNode.QuadTo(706.0f, 365.0f, 741.0f, 385.5f),
                PathNode.QuadTo(776.0f, 406.0f, 796.5f, 440.5f),
                PathNode.QuadTo(817.0f, 475.0f, 817.0f, 515.7336683417085f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactscircleLight!!
    }

private var _contactscircleLight: ImageVector? = null

val MiuixIcons.Regular.ContactsCircle: ImageVector
    get() {
        if (_contactscircleRegular != null) return _contactscircleRegular!!
        _contactscircleRegular = ImageVector.Builder(
            name = "ContactsCircle.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1197.6f,
            viewportHeight = 1197.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -66.20000000000005f, translationY = 973.3f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1164.0f, 374.0f),
                PathNode.QuadTo(1164.0f, 510.0f, 1097.0f, 624.5f),
                PathNode.QuadTo(1030.0f, 739.0f, 915.0f, 806.0f),
                PathNode.QuadTo(800.0f, 873.0f, 665.0f, 873.0f),
                PathNode.QuadTo(530.0f, 873.0f, 415.0f, 806.0f),
                PathNode.QuadTo(300.0f, 739.0f, 233.0f, 624.5f),
                PathNode.QuadTo(166.0f, 510.0f, 166.0f, 374.0f),
                PathNode.QuadTo(166.0f, 239.0f, 233.0f, 124.5f),
                PathNode.QuadTo(300.0f, 10.0f, 415.0f, -57.0f),
                PathNode.QuadTo(530.0f, -124.0f, 665.0f, -124.0f),
                PathNode.QuadTo(800.0f, -124.0f, 915.0f, -57.0f),
                PathNode.QuadTo(1030.0f, 10.0f, 1097.0f, 124.5f),
                PathNode.QuadTo(1164.0f, 239.0f, 1164.0f, 374.0f),
                PathNode.Close,
                PathNode.MoveTo(938.0f, -30.0f),
                PathNode.QuadTo(969.0f, -13.0f, 994.0f, 12.0f),
                PathNode.QuadTo(1019.0f, 37.0f, 1035.0f, 68.0f),
                PathNode.QuadTo(1037.0f, 74.0f, 1034.0f, 78.0f),
                PathNode.QuadTo(959.0f, 187.0f, 864.0f, 247.5f),
                PathNode.QuadTo(769.0f, 308.0f, 665.0f, 308.0f),
                PathNode.QuadTo(561.0f, 308.0f, 466.0f, 247.5f),
                PathNode.QuadTo(371.0f, 187.0f, 296.0f, 78.0f),
                PathNode.QuadTo(295.0f, 75.0f, 294.0f, 71.0f),
                PathNode.QuadTo(295.0f, 70.0f, 295.0f, 68.0f),
                PathNode.QuadTo(328.0f, 4.0f, 392.0f, -30.0f),
                PathNode.QuadTo(425.0f, -47.0f, 471.5f, -50.5f),
                PathNode.QuadTo(518.0f, -54.0f, 627.0f, -54.0f),
                PathNode.HorizontalTo(703.0f),
                PathNode.QuadTo(812.0f, -54.0f, 858.5f, -50.5f),
                PathNode.QuadTo(905.0f, -47.0f, 938.0f, -30.0f),
                PathNode.Close,
                PathNode.MoveTo(261.0f, 374.0f),
                PathNode.QuadTo(261.0f, 484.0f, 315.5f, 576.5f),
                PathNode.QuadTo(370.0f, 669.0f, 462.5f, 723.5f),
                PathNode.QuadTo(555.0f, 778.0f, 665.0f, 778.0f),
                PathNode.QuadTo(775.0f, 778.0f, 867.5f, 723.5f),
                PathNode.QuadTo(960.0f, 669.0f, 1014.5f, 576.5f),
                PathNode.QuadTo(1069.0f, 484.0f, 1069.0f, 374.0f),
                PathNode.QuadTo(1069.0f, 264.0f, 1014.5f, 171.5f),
                PathNode.QuadTo(960.0f, 79.0f, 867.5f, 25.0f),
                PathNode.QuadTo(775.0f, -29.0f, 665.0f, -29.0f),
                PathNode.QuadTo(555.0f, -29.0f, 462.5f, 25.0f),
                PathNode.QuadTo(370.0f, 79.0f, 315.5f, 171.5f),
                PathNode.QuadTo(261.0f, 264.0f, 261.0f, 374.0f),
                PathNode.Close,
                PathNode.MoveTo(822.0f, 515.0f),
                PathNode.QuadTo(822.0f, 558.0f, 801.0f, 594.0f),
                PathNode.QuadTo(780.0f, 630.0f, 744.0f, 651.0f),
                PathNode.QuadTo(708.0f, 672.0f, 665.0f, 672.0f),
                PathNode.QuadTo(622.0f, 672.0f, 586.0f, 651.0f),
                PathNode.QuadTo(550.0f, 630.0f, 529.0f, 594.0f),
                PathNode.QuadTo(508.0f, 558.0f, 508.0f, 515.0f),
                PathNode.QuadTo(508.0f, 473.0f, 529.0f, 436.5f),
                PathNode.QuadTo(550.0f, 400.0f, 586.0f, 379.0f),
                PathNode.QuadTo(622.0f, 358.0f, 665.0f, 358.0f),
                PathNode.QuadTo(708.0f, 358.0f, 744.0f, 379.0f),
                PathNode.QuadTo(780.0f, 400.0f, 801.0f, 436.5f),
                PathNode.QuadTo(822.0f, 473.0f, 822.0f, 515.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactscircleRegular!!
    }

private var _contactscircleRegular: ImageVector? = null

val MiuixIcons.Heavy.ContactsCircle: ImageVector
    get() {
        if (_contactscircleHeavy != null) return _contactscircleHeavy!!
        _contactscircleHeavy = ImageVector.Builder(
            name = "ContactsCircle.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1224.0f,
            viewportHeight = 1224.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -53.0f, translationY = 986.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1175.0f, 373.9968454258675f),
                PathNode.QuadTo(1175.0f, 513.0f, 1106.5205047318614f, 630.0234159779613f),
                PathNode.QuadTo(1038.0410094637225f, 747.0468319559228f, 920.5015772870663f, 815.5234159779613f),
                PathNode.QuadTo(802.9621451104101f, 884.0f, 664.9810725552051f, 884.0f),
                PathNode.QuadTo(527.0f, 884.0f, 409.4725274725275f, 815.5205047318611f),
                PathNode.QuadTo(291.94505494505495f, 747.0410094637224f, 223.47252747252747f, 630.01261829653f),
                PathNode.QuadTo(155.0f, 512.9842271293376f, 155.0f, 373.98107255520506f),
                PathNode.QuadTo(155.0f, 236.0f, 223.4794952681388f, 118.97658402203857f),
                PathNode.QuadTo(291.9589905362776f, 1.9531680440771346f, 409.49842271293375f, -66.52341597796143f),
                PathNode.QuadTo(527.0378548895899f, -135.0f, 665.0189274447949f, -135.0f),
                PathNode.QuadTo(803.0f, -135.0f, 920.5274725274726f, -66.5205047318612f),
                PathNode.QuadTo(1038.0549450549452f, 1.9589905362776037f, 1106.5274725274726f, 118.98738170347004f),
                PathNode.QuadTo(1175.0f, 236.01577287066246f, 1175.0f, 373.9968454258675f),
                PathNode.Close,
                PathNode.MoveTo(938.0f, -32.0f),
                PathNode.QuadTo(970.0f, -16.0f, 995.5f, 10.0f),
                PathNode.QuadTo(1021.0f, 36.0f, 1038.0f, 68.0f),
                PathNode.QuadTo(1042.0f, 78.0f, 1036.0f, 84.0f),
                PathNode.QuadTo(960.0f, 193.0f, 865.0f, 254.0f),
                PathNode.QuadTo(770.0f, 315.0f, 665.0f, 315.0f),
                PathNode.QuadTo(560.0f, 315.0f, 465.0f, 254.0f),
                PathNode.QuadTo(370.0f, 193.0f, 294.0f, 84.0f),
                PathNode.QuadTo(289.0f, 79.0f, 291.0f, 73.0f),
                PathNode.QuadTo(292.0f, 71.33333333333333f, 292.0f, 68.0f),
                PathNode.QuadTo(327.0f, 2.0f, 391.659793814433f, -32.0f),
                PathNode.QuadTo(425.0f, -49.0f, 471.5f, -53.0f),
                PathNode.QuadTo(518.0f, -57.0f, 627.0f, -57.0f),
                PathNode.HorizontalTo(703.0f),
                PathNode.QuadTo(812.0f, -57.0f, 858.5f, -53.0f),
                PathNode.QuadTo(905.0f, -49.0f, 938.0f, -32.0f),
                PathNode.Close,
                PathNode.MoveTo(280.0f, 374.0f),
                PathNode.QuadTo(280.0f, 478.0f, 332.0901360544218f, 566.4098639455783f),
                PathNode.QuadTo(384.18027210884355f, 654.8197278911565f, 472.5901360544218f, 706.9098639455783f),
                PathNode.QuadTo(561.0f, 759.0f, 665.0f, 759.0f),
                PathNode.QuadTo(769.0f, 759.0f, 857.4098639455783f, 706.9098639455783f),
                PathNode.QuadTo(945.8197278911565f, 654.8197278911565f, 997.9098639455783f, 566.4098639455783f),
                PathNode.QuadTo(1050.0f, 478.0f, 1050.0f, 374.0f),
                PathNode.QuadTo(1050.0f, 270.0f, 997.9098639455783f, 181.60409556313994f),
                PathNode.QuadTo(945.8197278911565f, 93.20819112627987f, 857.4098639455783f, 41.604095563139936f),
                PathNode.QuadTo(769.0f, -10.0f, 665.0f, -10.0f),
                PathNode.QuadTo(561.0f, -10.0f, 472.5901360544218f, 41.604095563139936f),
                PathNode.QuadTo(384.18027210884355f, 93.20819112627987f, 332.0901360544218f, 181.60409556313994f),
                PathNode.QuadTo(280.0f, 270.0f, 280.0f, 374.0f),
                PathNode.Close,
                PathNode.MoveTo(828.0f, 513.6050955414013f),
                PathNode.QuadTo(828.0f, 558.2484076433121f, 806.0f, 595.624203821656f),
                PathNode.QuadTo(784.0f, 633.0f, 746.7478260869566f, 654.5f),
                PathNode.QuadTo(709.4956521739131f, 676.0f, 665.0f, 676.0f),
                PathNode.QuadTo(620.5043478260869f, 676.0f, 583.2521739130434f, 654.5f),
                PathNode.QuadTo(546.0f, 633.0f, 524.0f, 595.624203821656f),
                PathNode.QuadTo(502.0f, 558.2484076433121f, 502.0f, 513.6050955414013f),
                PathNode.QuadTo(502.0f, 470.0f, 524.0f, 433.0f),
                PathNode.QuadTo(546.0f, 396.0f, 583.2521739130434f, 374.0f),
                PathNode.QuadTo(620.5043478260869f, 352.0f, 665.0f, 352.0f),
                PathNode.QuadTo(709.4956521739131f, 352.0f, 746.7478260869566f, 374.0f),
                PathNode.QuadTo(784.0f, 396.0f, 806.0f, 433.0f),
                PathNode.QuadTo(828.0f, 470.0f, 828.0f, 513.6050955414013f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactscircleHeavy!!
    }

private var _contactscircleHeavy: ImageVector? = null
