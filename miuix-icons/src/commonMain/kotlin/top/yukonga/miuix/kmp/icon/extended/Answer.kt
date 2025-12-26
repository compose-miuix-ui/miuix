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

val MiuixIcons.Answer: ImageVector
    get() = MiuixIcons.Regular.Answer

val MiuixIcons.Light.Answer: ImageVector
    get() {
        if (_answerLight != null) return _answerLight!!
        _answerLight = ImageVector.Builder(
            name = "Answer.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1194.0f,
            viewportHeight = 1194.0f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -67.5f, translationY = 972.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1162.0f, 375.0f),
                        PathNode.QuadTo(1162.0f, 510.0f, 1095.0f, 624.5f),
                        PathNode.QuadTo(1028.0f, 739.0f, 914.0f, 806.0f),
                        PathNode.QuadTo(800.0f, 873.0f, 665.0f, 873.0f),
                        PathNode.QuadTo(530.0f, 873.0f, 415.5f, 806.0f),
                        PathNode.QuadTo(301.0f, 739.0f, 234.0f, 624.5f),
                        PathNode.QuadTo(167.0f, 510.0f, 167.0f, 375.0f),
                        PathNode.QuadTo(167.0f, 240.0f, 234.0f, 126.0f),
                        PathNode.QuadTo(301.0f, 12.0f, 415.5f, -55.0f),
                        PathNode.QuadTo(530.0f, -122.0f, 665.0f, -122.0f),
                        PathNode.QuadTo(800.0f, -122.0f, 914.0f, -55.0f),
                        PathNode.QuadTo(1028.0f, 12.0f, 1095.0f, 126.0f),
                        PathNode.QuadTo(1162.0f, 240.0f, 1162.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(227.0f, 375.0f),
                        PathNode.QuadTo(227.0f, 494.0f, 286.0f, 594.5f),
                        PathNode.QuadTo(345.0f, 695.0f, 445.5f, 754.0f),
                        PathNode.QuadTo(546.0f, 813.0f, 665.0f, 813.0f),
                        PathNode.QuadTo(784.0f, 813.0f, 884.5f, 754.0f),
                        PathNode.QuadTo(985.0f, 695.0f, 1044.0f, 594.5f),
                        PathNode.QuadTo(1103.0f, 494.0f, 1103.0f, 375.0f),
                        PathNode.QuadTo(1103.0f, 256.0f, 1044.0f, 155.5f),
                        PathNode.QuadTo(985.0f, 55.0f, 884.5f, -4.0f),
                        PathNode.QuadTo(784.0f, -63.0f, 665.0f, -63.0f),
                        PathNode.QuadTo(546.0f, -63.0f, 445.5f, -4.0f),
                        PathNode.QuadTo(345.0f, 55.0f, 286.0f, 155.5f),
                        PathNode.QuadTo(227.0f, 256.0f, 227.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(895.0f, 236.0f),
                        PathNode.LineTo(800.0f, 291.0f),
                        PathNode.QuadTo(794.0f, 295.0f, 786.5f, 295.0f),
                        PathNode.QuadTo(779.0f, 295.0f, 773.0f, 291.0f),
                        PathNode.LineTo(738.0f, 272.0f),
                        PathNode.QuadTo(722.0f, 264.0f, 704.5f, 264.5f),
                        PathNode.QuadTo(687.0f, 265.0f, 673.0f, 274.0f),
                        PathNode.QuadTo(632.0f, 297.0f, 610.0f, 322.0f),
                        PathNode.QuadTo(593.0f, 340.0f, 563.0f, 383.0f),
                        PathNode.QuadTo(555.0f, 398.0f, 554.0f, 416.0f),
                        PathNode.QuadTo(553.0f, 434.0f, 561.0f, 448.0f),
                        PathNode.LineTo(581.0f, 483.0f),
                        PathNode.QuadTo(585.0f, 491.0f, 584.5f, 497.0f),
                        PathNode.QuadTo(584.0f, 503.0f, 580.0f, 511.0f),
                        PathNode.LineTo(526.0f, 605.0f),
                        PathNode.QuadTo(521.0f, 614.0f, 511.0f, 617.5f),
                        PathNode.QuadTo(501.0f, 621.0f, 491.0f, 616.0f),
                        PathNode.LineTo(454.0f, 594.0f),
                        PathNode.QuadTo(431.0f, 580.0f, 424.0f, 551.0f),
                        PathNode.QuadTo(417.0f, 522.0f, 427.0f, 491.0f),
                        PathNode.QuadTo(447.0f, 432.0f, 482.5f, 376.5f),
                        PathNode.QuadTo(518.0f, 321.0f, 564.0f, 275.0f),
                        PathNode.QuadTo(610.0f, 230.0f, 665.5f, 193.5f),
                        PathNode.QuadTo(721.0f, 157.0f, 780.0f, 137.0f),
                        PathNode.QuadTo(806.0f, 128.0f, 836.5f, 133.5f),
                        PathNode.QuadTo(867.0f, 139.0f, 880.0f, 161.0f),
                        PathNode.LineTo(904.0f, 199.0f),
                        PathNode.QuadTo(910.0f, 208.0f, 907.0f, 219.5f),
                        PathNode.QuadTo(904.0f, 231.0f, 895.0f, 236.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerLight!!
    }

private var _answerLight: ImageVector? = null

val MiuixIcons.Regular.Answer: ImageVector
    get() {
        if (_answerRegular != null) return _answerRegular!!
        _answerRegular = ImageVector.Builder(
            name = "Answer.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.2f,
            viewportHeight = 1225.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -51.89999999999998f, translationY = 988.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1175.0f, 375.0f),
                        PathNode.QuadTo(1175.0f, 514.0f, 1106.5f, 631.0f),
                        PathNode.QuadTo(1038.0f, 748.0f, 921.0f, 817.0f),
                        PathNode.QuadTo(804.0f, 886.0f, 665.0f, 886.0f),
                        PathNode.QuadTo(526.0f, 886.0f, 409.0f, 817.0f),
                        PathNode.QuadTo(292.0f, 748.0f, 223.0f, 631.0f),
                        PathNode.QuadTo(154.0f, 514.0f, 154.0f, 375.0f),
                        PathNode.QuadTo(154.0f, 236.0f, 223.0f, 119.0f),
                        PathNode.QuadTo(292.0f, 2.0f, 409.0f, -66.5f),
                        PathNode.QuadTo(526.0f, -135.0f, 665.0f, -135.0f),
                        PathNode.QuadTo(804.0f, -135.0f, 921.0f, -66.5f),
                        PathNode.QuadTo(1038.0f, 2.0f, 1106.5f, 119.0f),
                        PathNode.QuadTo(1175.0f, 236.0f, 1175.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(240.0f, 375.0f),
                        PathNode.QuadTo(240.0f, 491.0f, 297.0f, 588.5f),
                        PathNode.QuadTo(354.0f, 686.0f, 451.5f, 743.0f),
                        PathNode.QuadTo(549.0f, 800.0f, 665.0f, 800.0f),
                        PathNode.QuadTo(780.0f, 800.0f, 877.5f, 743.0f),
                        PathNode.QuadTo(975.0f, 686.0f, 1032.5f, 588.5f),
                        PathNode.QuadTo(1090.0f, 491.0f, 1090.0f, 375.0f),
                        PathNode.QuadTo(1090.0f, 260.0f, 1032.5f, 162.5f),
                        PathNode.QuadTo(975.0f, 65.0f, 877.5f, 7.5f),
                        PathNode.QuadTo(780.0f, -50.0f, 665.0f, -50.0f),
                        PathNode.QuadTo(549.0f, -50.0f, 451.5f, 7.5f),
                        PathNode.QuadTo(354.0f, 65.0f, 297.0f, 162.5f),
                        PathNode.QuadTo(240.0f, 260.0f, 240.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(899.0f, 244.0f),
                        PathNode.LineTo(804.0f, 299.0f),
                        PathNode.QuadTo(797.0f, 304.0f, 787.0f, 304.0f),
                        PathNode.QuadTo(777.0f, 304.0f, 769.0f, 299.0f),
                        PathNode.LineTo(734.0f, 280.0f),
                        PathNode.QuadTo(721.0f, 273.0f, 706.0f, 273.5f),
                        PathNode.QuadTo(691.0f, 274.0f, 677.0f, 282.0f),
                        PathNode.QuadTo(636.0f, 307.0f, 617.0f, 328.0f),
                        PathNode.QuadTo(594.0f, 352.0f, 571.0f, 388.0f),
                        PathNode.QuadTo(563.0f, 401.0f, 562.5f, 416.0f),
                        PathNode.QuadTo(562.0f, 431.0f, 569.0f, 444.0f),
                        PathNode.LineTo(588.0f, 479.0f),
                        PathNode.QuadTo(593.0f, 488.0f, 593.0f, 496.5f),
                        PathNode.QuadTo(593.0f, 505.0f, 588.0f, 515.0f),
                        PathNode.LineTo(534.0f, 609.0f),
                        PathNode.QuadTo(527.0f, 621.0f, 513.0f, 626.0f),
                        PathNode.QuadTo(499.0f, 631.0f, 487.0f, 624.0f),
                        PathNode.LineTo(450.0f, 602.0f),
                        PathNode.QuadTo(423.0f, 586.0f, 415.5f, 554.0f),
                        PathNode.QuadTo(408.0f, 522.0f, 419.0f, 488.0f),
                        PathNode.QuadTo(439.0f, 429.0f, 475.5f, 372.0f),
                        PathNode.QuadTo(512.0f, 315.0f, 558.0f, 269.0f),
                        PathNode.QuadTo(604.0f, 223.0f, 660.5f, 186.0f),
                        PathNode.QuadTo(717.0f, 149.0f, 777.0f, 129.0f),
                        PathNode.QuadTo(806.0f, 119.0f, 839.0f, 125.0f),
                        PathNode.QuadTo(872.0f, 131.0f, 888.0f, 156.0f),
                        PathNode.LineTo(913.0f, 196.0f),
                        PathNode.QuadTo(920.0f, 208.0f, 915.5f, 222.5f),
                        PathNode.QuadTo(911.0f, 237.0f, 899.0f, 244.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerRegular!!
    }

private var _answerRegular: ImageVector? = null

val MiuixIcons.Heavy.Answer: ImageVector
    get() {
        if (_answerHeavy != null) return _answerHeavy!!
        _answerHeavy = ImageVector.Builder(
            name = "Answer.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1258.8f,
            viewportHeight = 1258.8f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -35.10000000000002f, translationY = 1004.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1189.0f, 375.0f),
                        PathNode.QuadTo(1189.0f, 517.0f, 1118.5f, 637.5f),
                        PathNode.QuadTo(1048.0f, 758.0f, 927.5f, 829.0f),
                        PathNode.QuadTo(807.0f, 900.0f, 665.0f, 900.0f),
                        PathNode.QuadTo(523.0f, 900.0f, 402.5f, 829.0f),
                        PathNode.QuadTo(282.0f, 758.0f, 211.0f, 637.5f),
                        PathNode.QuadTo(140.0f, 517.0f, 140.0f, 375.0f),
                        PathNode.QuadTo(140.0f, 233.0f, 211.0f, 112.5f),
                        PathNode.QuadTo(282.0f, -8.0f, 402.5f, -78.5f),
                        PathNode.QuadTo(523.0f, -149.0f, 665.0f, -149.0f),
                        PathNode.QuadTo(807.0f, -149.0f, 927.5f, -78.5f),
                        PathNode.QuadTo(1048.0f, -8.0f, 1118.5f, 112.5f),
                        PathNode.QuadTo(1189.0f, 233.0f, 1189.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(254.0f, 375.0f),
                        PathNode.QuadTo(254.0f, 487.0f, 309.0f, 581.5f),
                        PathNode.QuadTo(364.0f, 676.0f, 458.5f, 731.0f),
                        PathNode.QuadTo(553.0f, 786.0f, 665.0f, 786.0f),
                        PathNode.QuadTo(777.0f, 786.0f, 871.0f, 731.0f),
                        PathNode.QuadTo(965.0f, 676.0f, 1020.5f, 581.5f),
                        PathNode.QuadTo(1076.0f, 487.0f, 1076.0f, 375.0f),
                        PathNode.QuadTo(1076.0f, 263.0f, 1020.5f, 169.0f),
                        PathNode.QuadTo(965.0f, 75.0f, 871.0f, 19.5f),
                        PathNode.QuadTo(777.0f, -36.0f, 665.0f, -36.0f),
                        PathNode.QuadTo(553.0f, -36.0f, 458.5f, 19.5f),
                        PathNode.QuadTo(364.0f, 75.0f, 309.0f, 169.0f),
                        PathNode.QuadTo(254.0f, 263.0f, 254.0f, 375.0f),
                        PathNode.Close,
                        PathNode.MoveTo(904.0f, 252.0f),
                        PathNode.LineTo(809.0f, 307.0f),
                        PathNode.QuadTo(799.0f, 313.0f, 786.5f, 313.0f),
                        PathNode.QuadTo(774.0f, 313.0f, 764.0f, 307.0f),
                        PathNode.LineTo(729.0f, 288.0f),
                        PathNode.QuadTo(719.0f, 282.0f, 706.0f, 282.5f),
                        PathNode.QuadTo(693.0f, 283.0f, 682.0f, 290.0f),
                        PathNode.QuadTo(641.0f, 314.0f, 624.0f, 334.0f),
                        PathNode.QuadTo(598.0f, 362.0f, 579.0f, 393.0f),
                        PathNode.QuadTo(573.0f, 404.0f, 572.5f, 417.0f),
                        PathNode.QuadTo(572.0f, 430.0f, 577.0f, 440.0f),
                        PathNode.LineTo(596.0f, 474.0f),
                        PathNode.QuadTo(603.0f, 485.0f, 603.0f, 496.5f),
                        PathNode.QuadTo(603.0f, 508.0f, 596.0f, 520.0f),
                        PathNode.LineTo(542.0f, 614.0f),
                        PathNode.QuadTo(533.0f, 630.0f, 515.0f, 635.0f),
                        PathNode.QuadTo(497.0f, 640.0f, 482.0f, 632.0f),
                        PathNode.LineTo(445.0f, 610.0f),
                        PathNode.QuadTo(416.0f, 592.0f, 407.0f, 556.5f),
                        PathNode.QuadTo(398.0f, 521.0f, 410.0f, 485.0f),
                        PathNode.QuadTo(430.0f, 424.0f, 468.0f, 366.5f),
                        PathNode.QuadTo(506.0f, 309.0f, 552.0f, 263.0f),
                        PathNode.QuadTo(597.0f, 217.0f, 655.0f, 179.0f),
                        PathNode.QuadTo(713.0f, 141.0f, 774.0f, 120.0f),
                        PathNode.QuadTo(806.0f, 109.0f, 842.0f, 116.0f),
                        PathNode.QuadTo(878.0f, 123.0f, 896.0f, 151.0f),
                        PathNode.LineTo(921.0f, 191.0f),
                        PathNode.QuadTo(930.0f, 206.0f, 924.5f, 224.5f),
                        PathNode.QuadTo(919.0f, 243.0f, 904.0f, 252.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerHeavy!!
    }

private var _answerHeavy: ImageVector? = null
