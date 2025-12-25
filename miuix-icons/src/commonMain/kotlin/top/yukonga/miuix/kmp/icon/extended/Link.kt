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

val MiuixIcons.Link: ImageVector
    get() = MiuixIcons.Regular.Link

val MiuixIcons.Light.Link: ImageVector
    get() {
        if (_linkLight != null) return _linkLight!!
        _linkLight = ImageVector.Builder(
            name = "Link.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1140.15223880597f,
            viewportHeight = 1140.15223880597f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -94.48731343283589f, translationY = 945.576119402985f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(639.0f, 186.0f),
                PathNode.QuadTo(646.0f, 183.0f, 652.5f, 185.0f),
                PathNode.QuadTo(659.0f, 187.0f, 664.0f, 193.0f),
                PathNode.LineTo(678.0f, 210.0f),
                PathNode.QuadTo(683.0f, 217.0f, 681.5f, 224.0f),
                PathNode.QuadTo(680.0f, 231.0f, 671.0f, 234.0f),
                PathNode.QuadTo(637.0f, 247.0f, 616.5f, 258.5f),
                PathNode.QuadTo(596.0f, 270.0f, 577.0f, 289.0f),
                PathNode.QuadTo(541.0f, 325.0f, 528.5f, 374.0f),
                PathNode.QuadTo(516.0f, 423.0f, 529.0f, 472.0f),
                PathNode.QuadTo(542.0f, 521.0f, 578.0f, 557.0f),
                PathNode.LineTo(756.0f, 736.0f),
                PathNode.QuadTo(793.0f, 772.0f, 842.0f, 785.0f),
                PathNode.QuadTo(891.0f, 798.0f, 940.0f, 785.0f),
                PathNode.QuadTo(989.0f, 772.0f, 1025.0f, 736.0f),
                PathNode.QuadTo(1062.0f, 700.0f, 1075.0f, 650.5f),
                PathNode.QuadTo(1088.0f, 601.0f, 1075.0f, 552.0f),
                PathNode.QuadTo(1062.0f, 503.0f, 1025.0f, 467.0f),
                PathNode.LineTo(936.0f, 382.0f),
                PathNode.QuadTo(930.0f, 376.0f, 930.0f, 367.5f),
                PathNode.QuadTo(930.0f, 359.0f, 935.0f, 354.0f),
                PathNode.LineTo(947.0f, 342.0f),
                PathNode.QuadTo(954.0f, 335.0f, 962.5f, 335.0f),
                PathNode.QuadTo(971.0f, 335.0f, 978.0f, 342.0f),
                PathNode.LineTo(1066.0f, 426.0f),
                PathNode.QuadTo(1115.0f, 473.0f, 1131.5f, 536.5f),
                PathNode.QuadTo(1148.0f, 600.0f, 1131.0f, 664.5f),
                PathNode.QuadTo(1114.0f, 729.0f, 1066.0f, 777.0f),
                PathNode.QuadTo(1018.0f, 825.0f, 954.5f, 841.5f),
                PathNode.QuadTo(891.0f, 858.0f, 827.5f, 841.5f),
                PathNode.QuadTo(764.0f, 825.0f, 716.0f, 777.0f),
                PathNode.LineTo(537.0f, 598.0f),
                PathNode.QuadTo(489.0f, 550.0f, 472.5f, 486.0f),
                PathNode.QuadTo(456.0f, 422.0f, 472.5f, 358.5f),
                PathNode.QuadTo(489.0f, 295.0f, 537.0f, 247.0f),
                PathNode.QuadTo(558.0f, 227.0f, 582.0f, 212.5f),
                PathNode.QuadTo(606.0f, 198.0f, 639.0f, 186.0f),
                PathNode.Close,
                PathNode.MoveTo(614.0f, -26.0f),
                PathNode.LineTo(793.0f, 153.0f),
                PathNode.QuadTo(840.0f, 200.0f, 856.5f, 264.0f),
                PathNode.QuadTo(873.0f, 328.0f, 856.5f, 391.5f),
                PathNode.QuadTo(840.0f, 455.0f, 793.0f, 503.0f),
                PathNode.QuadTo(761.0f, 534.0f, 707.0f, 558.0f),
                PathNode.QuadTo(700.0f, 561.0f, 693.5f, 560.5f),
                PathNode.QuadTo(687.0f, 560.0f, 681.0f, 554.0f),
                PathNode.LineTo(669.0f, 540.0f),
                PathNode.QuadTo(661.0f, 531.0f, 663.5f, 522.5f),
                PathNode.QuadTo(666.0f, 514.0f, 677.0f, 509.0f),
                PathNode.LineTo(688.0f, 504.0f),
                PathNode.QuadTo(729.0f, 485.0f, 751.0f, 461.0f),
                PathNode.QuadTo(787.0f, 425.0f, 800.0f, 376.0f),
                PathNode.QuadTo(813.0f, 327.0f, 800.0f, 278.5f),
                PathNode.QuadTo(787.0f, 230.0f, 751.0f, 194.0f),
                PathNode.LineTo(573.0f, 15.0f),
                PathNode.QuadTo(537.0f, -22.0f, 487.5f, -35.0f),
                PathNode.QuadTo(438.0f, -48.0f, 389.0f, -35.0f),
                PathNode.QuadTo(340.0f, -22.0f, 304.0f, 15.0f),
                PathNode.QuadTo(268.0f, 51.0f, 255.0f, 100.0f),
                PathNode.QuadTo(242.0f, 149.0f, 254.5f, 198.0f),
                PathNode.QuadTo(267.0f, 247.0f, 304.0f, 284.0f),
                PathNode.LineTo(380.0f, 358.0f),
                PathNode.QuadTo(391.0f, 368.0f, 391.0f, 376.5f),
                PathNode.QuadTo(391.0f, 385.0f, 384.0f, 392.0f),
                PathNode.LineTo(372.0f, 404.0f),
                PathNode.QuadTo(365.0f, 410.0f, 357.5f, 410.0f),
                PathNode.QuadTo(350.0f, 410.0f, 342.0f, 402.0f),
                PathNode.LineTo(263.0f, 324.0f),
                PathNode.QuadTo(215.0f, 277.0f, 198.0f, 213.5f),
                PathNode.QuadTo(181.0f, 150.0f, 198.0f, 86.0f),
                PathNode.QuadTo(215.0f, 22.0f, 263.0f, -26.0f),
                PathNode.QuadTo(311.0f, -74.0f, 375.0f, -90.5f),
                PathNode.QuadTo(439.0f, -107.0f, 502.5f, -90.5f),
                PathNode.QuadTo(566.0f, -74.0f, 614.0f, -26.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _linkLight!!
    }

private var _linkLight: ImageVector? = null

val MiuixIcons.Regular.Link: ImageVector
    get() {
        if (_linkRegular != null) return _linkRegular!!
        _linkRegular = ImageVector.Builder(
            name = "Link.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1159.2f,
            viewportHeight = 1159.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -84.89999999999998f, translationY = 955.1f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(623.0f, 179.0f),
                PathNode.QuadTo(634.0f, 175.0f, 643.5f, 177.5f),
                PathNode.QuadTo(653.0f, 180.0f, 660.0f, 189.0f),
                PathNode.LineTo(679.0f, 212.0f),
                PathNode.QuadTo(688.0f, 222.0f, 685.5f, 233.0f),
                PathNode.QuadTo(683.0f, 244.0f, 670.0f, 249.0f),
                PathNode.QuadTo(641.0f, 261.0f, 623.0f, 271.5f),
                PathNode.QuadTo(605.0f, 282.0f, 588.0f, 299.0f),
                PathNode.QuadTo(555.0f, 332.0f, 543.0f, 377.5f),
                PathNode.QuadTo(531.0f, 423.0f, 543.0f, 468.0f),
                PathNode.QuadTo(555.0f, 513.0f, 588.0f, 546.0f),
                PathNode.LineTo(765.0f, 723.0f),
                PathNode.QuadTo(799.0f, 756.0f, 844.0f, 768.0f),
                PathNode.QuadTo(889.0f, 780.0f, 934.0f, 768.0f),
                PathNode.QuadTo(979.0f, 756.0f, 1012.0f, 723.0f),
                PathNode.QuadTo(1046.0f, 690.0f, 1058.0f, 645.0f),
                PathNode.QuadTo(1070.0f, 600.0f, 1058.0f, 555.0f),
                PathNode.QuadTo(1046.0f, 510.0f, 1012.0f, 476.0f),
                PathNode.LineTo(935.0f, 398.0f),
                PathNode.QuadTo(926.0f, 389.0f, 925.5f, 376.5f),
                PathNode.QuadTo(925.0f, 364.0f, 933.0f, 356.0f),
                PathNode.LineTo(948.0f, 341.0f),
                PathNode.QuadTo(960.0f, 329.0f, 973.0f, 329.5f),
                PathNode.QuadTo(986.0f, 330.0f, 997.0f, 341.0f),
                PathNode.LineTo(1072.0f, 416.0f),
                PathNode.QuadTo(1122.0f, 466.0f, 1139.0f, 532.5f),
                PathNode.QuadTo(1156.0f, 599.0f, 1139.0f, 666.0f),
                PathNode.QuadTo(1122.0f, 733.0f, 1072.0f, 783.0f),
                PathNode.QuadTo(1022.0f, 833.0f, 955.5f, 850.0f),
                PathNode.QuadTo(889.0f, 867.0f, 822.5f, 850.0f),
                PathNode.QuadTo(756.0f, 833.0f, 706.0f, 783.0f),
                PathNode.LineTo(529.0f, 606.0f),
                PathNode.QuadTo(479.0f, 556.0f, 461.5f, 489.0f),
                PathNode.QuadTo(444.0f, 422.0f, 461.5f, 355.5f),
                PathNode.QuadTo(479.0f, 289.0f, 529.0f, 239.0f),
                PathNode.QuadTo(549.0f, 219.0f, 572.5f, 204.0f),
                PathNode.QuadTo(596.0f, 189.0f, 623.0f, 179.0f),
                PathNode.Close,
                PathNode.MoveTo(624.0f, -32.0f),
                PathNode.LineTo(801.0f, 145.0f),
                PathNode.QuadTo(851.0f, 195.0f, 868.0f, 261.5f),
                PathNode.QuadTo(885.0f, 328.0f, 868.0f, 394.5f),
                PathNode.QuadTo(851.0f, 461.0f, 801.0f, 511.0f),
                PathNode.QuadTo(768.0f, 544.0f, 723.0f, 565.0f),
                PathNode.QuadTo(714.0f, 569.0f, 704.0f, 568.5f),
                PathNode.QuadTo(694.0f, 568.0f, 685.0f, 558.0f),
                PathNode.LineTo(669.0f, 541.0f),
                PathNode.QuadTo(656.0f, 527.0f, 660.0f, 514.0f),
                PathNode.QuadTo(664.0f, 501.0f, 682.0f, 492.0f),
                PathNode.LineTo(688.0f, 489.0f),
                PathNode.QuadTo(721.0f, 472.0f, 741.0f, 451.0f),
                PathNode.QuadTo(774.0f, 418.0f, 786.0f, 373.0f),
                PathNode.QuadTo(798.0f, 328.0f, 786.0f, 283.0f),
                PathNode.QuadTo(774.0f, 238.0f, 741.0f, 205.0f),
                PathNode.LineTo(564.0f, 28.0f),
                PathNode.QuadTo(531.0f, -6.0f, 485.5f, -18.0f),
                PathNode.QuadTo(440.0f, -30.0f, 395.0f, -18.0f),
                PathNode.QuadTo(350.0f, -6.0f, 317.0f, 28.0f),
                PathNode.QuadTo(284.0f, 61.0f, 272.0f, 106.0f),
                PathNode.QuadTo(260.0f, 151.0f, 272.0f, 196.0f),
                PathNode.QuadTo(284.0f, 241.0f, 317.0f, 275.0f),
                PathNode.LineTo(380.0f, 337.0f),
                PathNode.QuadTo(396.0f, 353.0f, 396.5f, 365.5f),
                PathNode.QuadTo(397.0f, 378.0f, 386.0f, 389.0f),
                PathNode.LineTo(372.0f, 404.0f),
                PathNode.QuadTo(362.0f, 413.0f, 349.5f, 413.5f),
                PathNode.QuadTo(337.0f, 414.0f, 325.0f, 402.0f),
                PathNode.LineTo(257.0f, 334.0f),
                PathNode.QuadTo(207.0f, 284.0f, 190.0f, 217.5f),
                PathNode.QuadTo(173.0f, 151.0f, 190.0f, 84.5f),
                PathNode.QuadTo(207.0f, 18.0f, 257.0f, -32.0f),
                PathNode.QuadTo(307.0f, -82.0f, 374.0f, -99.0f),
                PathNode.QuadTo(441.0f, -116.0f, 507.5f, -99.0f),
                PathNode.QuadTo(574.0f, -82.0f, 624.0f, -32.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _linkRegular!!
    }

private var _linkRegular: ImageVector? = null

val MiuixIcons.Heavy.Link: ImageVector
    get() {
        if (_linkHeavy != null) return _linkHeavy!!
        _linkHeavy = ImageVector.Builder(
            name = "Link.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1219.2f,
            viewportHeight = 1219.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -54.8366438356164f, translationY = 985.6f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(591.0f, 194.0f),
                PathNode.QuadTo(606.0f, 188.0f, 620.5f, 192.0f),
                PathNode.QuadTo(635.0f, 196.0f, 646.0f, 209.0f),
                PathNode.LineTo(665.0f, 231.0f),
                PathNode.QuadTo(680.0f, 247.0f, 675.5f, 265.0f),
                PathNode.QuadTo(671.0f, 283.0f, 651.0f, 293.0f),
                PathNode.QuadTo(633.0f, 301.0f, 619.5f, 309.5f),
                PathNode.QuadTo(606.0f, 318.0f, 593.0f, 330.0f),
                PathNode.QuadTo(565.0f, 355.0f, 556.0f, 394.0f),
                PathNode.QuadTo(547.0f, 433.0f, 558.0f, 473.5f),
                PathNode.QuadTo(569.0f, 514.0f, 599.0f, 544.0f),
                PathNode.LineTo(776.0f, 721.0f),
                PathNode.QuadTo(807.0f, 752.0f, 847.5f, 762.5f),
                PathNode.QuadTo(888.0f, 773.0f, 928.5f, 762.5f),
                PathNode.QuadTo(969.0f, 752.0f, 1000.0f, 721.0f),
                PathNode.QuadTo(1031.0f, 690.0f, 1042.0f, 649.5f),
                PathNode.QuadTo(1053.0f, 609.0f, 1042.0f, 568.5f),
                PathNode.QuadTo(1031.0f, 528.0f, 1000.0f, 497.0f),
                PathNode.LineTo(943.0f, 439.0f),
                PathNode.QuadTo(929.0f, 426.0f, 928.5f, 407.0f),
                PathNode.QuadTo(928.0f, 388.0f, 941.0f, 374.0f),
                PathNode.LineTo(956.0f, 359.0f),
                PathNode.QuadTo(972.0f, 343.0f, 991.5f, 343.0f),
                PathNode.QuadTo(1011.0f, 343.0f, 1028.0f, 359.0f),
                PathNode.LineTo(1083.0f, 414.0f),
                PathNode.QuadTo(1136.0f, 465.0f, 1154.5f, 536.0f),
                PathNode.QuadTo(1173.0f, 607.0f, 1154.5f, 678.5f),
                PathNode.QuadTo(1136.0f, 750.0f, 1083.0f, 804.0f),
                PathNode.QuadTo(1030.0f, 857.0f, 959.0f, 875.0f),
                PathNode.QuadTo(888.0f, 893.0f, 817.5f, 875.0f),
                PathNode.QuadTo(747.0f, 857.0f, 694.0f, 804.0f),
                PathNode.LineTo(517.0f, 627.0f),
                PathNode.QuadTo(464.0f, 574.0f, 445.0f, 503.0f),
                PathNode.QuadTo(426.0f, 432.0f, 443.5f, 363.5f),
                PathNode.QuadTo(461.0f, 295.0f, 511.0f, 247.0f),
                PathNode.QuadTo(533.0f, 226.0f, 551.5f, 213.5f),
                PathNode.QuadTo(570.0f, 201.0f, 591.0f, 194.0f),
                PathNode.Close,
                PathNode.MoveTo(635.0f, -52.0f),
                PathNode.LineTo(812.0f, 125.0f),
                PathNode.QuadTo(865.0f, 178.0f, 883.0f, 248.5f),
                PathNode.QuadTo(901.0f, 319.0f, 883.0f, 390.0f),
                PathNode.QuadTo(865.0f, 461.0f, 812.0f, 514.0f),
                PathNode.QuadTo(781.0f, 544.0f, 749.0f, 559.0f),
                PathNode.QuadTo(738.0f, 566.0f, 722.0f, 563.5f),
                PathNode.QuadTo(706.0f, 561.0f, 693.0f, 547.0f),
                PathNode.LineTo(677.0f, 530.0f),
                PathNode.QuadTo(659.0f, 510.0f, 663.0f, 492.5f),
                PathNode.QuadTo(667.0f, 475.0f, 690.0f, 459.0f),
                PathNode.LineTo(694.0f, 456.0f),
                PathNode.QuadTo(717.0f, 444.0f, 729.0f, 431.0f),
                PathNode.QuadTo(760.0f, 400.0f, 770.5f, 359.5f),
                PathNode.QuadTo(781.0f, 319.0f, 770.5f, 278.5f),
                PathNode.QuadTo(760.0f, 238.0f, 729.0f, 208.0f),
                PathNode.LineTo(552.0f, 31.0f),
                PathNode.QuadTo(522.0f, 0.0f, 481.0f, -11.0f),
                PathNode.QuadTo(440.0f, -22.0f, 399.0f, -11.0f),
                PathNode.QuadTo(358.0f, 0.0f, 328.0f, 31.0f),
                PathNode.QuadTo(297.0f, 61.0f, 286.5f, 101.5f),
                PathNode.QuadTo(276.0f, 142.0f, 286.5f, 183.0f),
                PathNode.QuadTo(297.0f, 224.0f, 328.0f, 255.0f),
                PathNode.LineTo(371.0f, 298.0f),
                PathNode.QuadTo(392.0f, 319.0f, 392.5f, 337.5f),
                PathNode.QuadTo(393.0f, 356.0f, 377.0f, 372.0f),
                PathNode.LineTo(363.0f, 388.0f),
                PathNode.QuadTo(349.0f, 402.0f, 329.0f, 402.5f),
                PathNode.QuadTo(309.0f, 403.0f, 293.0f, 386.0f),
                PathNode.LineTo(245.0f, 337.0f),
                PathNode.QuadTo(193.0f, 284.0f, 174.5f, 213.0f),
                PathNode.QuadTo(156.0f, 142.0f, 174.0f, 71.5f),
                PathNode.QuadTo(192.0f, 1.0f, 245.0f, -52.0f),
                PathNode.QuadTo(298.0f, -105.0f, 369.0f, -123.0f),
                PathNode.QuadTo(440.0f, -141.0f, 511.0f, -123.0f),
                PathNode.QuadTo(582.0f, -105.0f, 635.0f, -52.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _linkHeavy!!
    }

private var _linkHeavy: ImageVector? = null
