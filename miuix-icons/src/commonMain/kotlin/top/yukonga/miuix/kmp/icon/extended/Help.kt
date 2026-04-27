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

val MiuixIcons.Help: ImageVector
    get() = MiuixIcons.Regular.Help

val MiuixIcons.Light.Help: ImageVector
    get() {
        if (_helpLight != null) return _helpLight!!
        _helpLight = ImageVector.Builder(
            name = "Help.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1194.0f,
            viewportHeight = 1194.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1194.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1094.5f, 596.3f),
                        PathNode.QuadTo(1094.5f, 731.5f, 1027.5f, 846.0f),
                        PathNode.QuadTo(960.5f, 960.5f, 846.7f, 1027.5f),
                        PathNode.QuadTo(732.9f, 1094.5f, 597.7f, 1094.5f),
                        PathNode.QuadTo(462.5f, 1094.5f, 348.0f, 1027.5f),
                        PathNode.QuadTo(233.5f, 960.5f, 166.5f, 846.0f),
                        PathNode.QuadTo(99.5f, 731.5f, 99.5f, 596.3f),
                        PathNode.QuadTo(99.5f, 461.1f, 166.5f, 347.3f),
                        PathNode.QuadTo(233.5f, 233.5f, 348.0f, 166.5f),
                        PathNode.QuadTo(462.5f, 99.5f, 597.7f, 99.5f),
                        PathNode.QuadTo(732.9f, 99.5f, 846.7f, 166.5f),
                        PathNode.QuadTo(960.5f, 233.5f, 1027.5f, 347.3f),
                        PathNode.QuadTo(1094.5f, 461.1f, 1094.5f, 596.3f),
                        PathNode.Close,
                        PathNode.MoveTo(159.5f, 596.0f),
                        PathNode.QuadTo(159.5f, 715.5f, 218.5f, 816.0f),
                        PathNode.QuadTo(277.5f, 916.5f, 377.8f, 975.5f),
                        PathNode.QuadTo(478.1f, 1034.5f, 597.5f, 1034.5f),
                        PathNode.QuadTo(716.9f, 1034.5f, 817.2f, 975.5f),
                        PathNode.QuadTo(917.5f, 916.5f, 976.5f, 816.0f),
                        PathNode.QuadTo(1035.5f, 715.5f, 1035.5f, 596.0f),
                        PathNode.QuadTo(1035.5f, 477.5f, 976.7f, 377.2f),
                        PathNode.QuadTo(917.8f, 276.8f, 817.2f, 217.7f),
                        PathNode.QuadTo(716.5f, 158.5f, 597.5f, 158.5f),
                        PathNode.QuadTo(478.5f, 158.5f, 377.8f, 217.7f),
                        PathNode.QuadTo(277.2f, 276.8f, 218.3f, 377.2f),
                        PathNode.QuadTo(159.5f, 477.5f, 159.5f, 596.0f),
                        PathNode.Close,
                        PathNode.MoveTo(594.6f, 329.5f),
                        PathNode.QuadTo(611.5f, 329.5f, 623.5f, 342.0f),
                        PathNode.QuadTo(635.5f, 354.5f, 635.5f, 371.5f),
                        PathNode.QuadTo(635.5f, 388.5f, 623.4f, 401.0f),
                        PathNode.QuadTo(611.4f, 413.5f, 594.8f, 413.5f),
                        PathNode.QuadTo(577.5f, 413.5f, 565.0f, 401.0f),
                        PathNode.QuadTo(552.5f, 388.5f, 552.5f, 371.5f),
                        PathNode.QuadTo(552.5f, 354.5f, 564.8f, 342.0f),
                        PathNode.QuadTo(577.0f, 329.5f, 594.6f, 329.5f),
                        PathNode.Close,
                        PathNode.MoveTo(622.5f, 493.5f),
                        PathNode.QuadTo(630.5f, 528.5f, 645.0f, 552.5f),
                        PathNode.QuadTo(659.5f, 576.5f, 680.5f, 595.5f),
                        PathNode.LineTo(708.5f, 621.5f),
                        PathNode.QuadTo(755.5f, 666.5f, 755.5f, 727.5f),
                        PathNode.QuadTo(755.5f, 773.5f, 732.3f, 807.6f),
                        PathNode.QuadTo(709.0f, 841.6f, 673.3f, 859.6f),
                        PathNode.QuadTo(637.5f, 877.5f, 601.5f, 877.5f),
                        PathNode.QuadTo(556.5f, 877.5f, 520.5f, 856.5f),
                        PathNode.QuadTo(484.5f, 835.5f, 464.0f, 801.6f),
                        PathNode.QuadTo(443.5f, 767.7f, 443.5f, 730.5f),
                        PathNode.QuadTo(443.5f, 721.7f, 447.0f, 717.6f),
                        PathNode.QuadTo(450.5f, 713.5f, 458.2f, 713.5f),
                        PathNode.HorizontalTo(485.5f),
                        PathNode.QuadTo(494.5f, 713.5f, 498.4f, 718.4f),
                        PathNode.QuadTo(502.2f, 723.3f, 503.5f, 732.5f),
                        PathNode.QuadTo(506.5f, 776.5f, 536.5f, 797.5f),
                        PathNode.QuadTo(566.5f, 818.5f, 599.3f, 818.5f),
                        PathNode.QuadTo(634.5f, 818.5f, 665.0f, 795.1f),
                        PathNode.QuadTo(695.5f, 771.7f, 695.5f, 729.5f),
                        PathNode.QuadTo(695.5f, 693.5f, 670.5f, 669.5f),
                        PathNode.LineTo(629.5f, 629.5f),
                        PathNode.QuadTo(578.5f, 576.5f, 563.5f, 495.5f),
                        PathNode.QuadTo(562.5f, 488.5f, 566.6f, 484.0f),
                        PathNode.QuadTo(570.8f, 479.5f, 578.4f, 479.5f),
                        PathNode.HorizontalTo(603.2f),
                        PathNode.QuadTo(611.5f, 479.5f, 616.5f, 482.5f),
                        PathNode.QuadTo(621.5f, 485.5f, 622.5f, 493.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _helpLight!!
    }

private var _helpLight: ImageVector? = null

val MiuixIcons.Normal.Help: ImageVector
    get() {
        if (_helpNormal != null) return _helpNormal!!
        _helpNormal = ImageVector.Builder(
            name = "Help.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1215.5f,
            viewportHeight = 1215.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1215.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1114.2f, 607.2f),
                        PathNode.QuadTo(1114.2f, 745.0f, 1046.1f, 861.2f),
                        PathNode.QuadTo(978.1f, 977.4f, 862.1f, 1045.8f),
                        PathNode.QuadTo(746.1f, 1114.2f, 608.3f, 1114.2f),
                        PathNode.QuadTo(470.5f, 1114.2f, 354.3f, 1045.8f),
                        PathNode.QuadTo(238.0f, 977.4f, 169.7f, 861.2f),
                        PathNode.QuadTo(101.3f, 745.0f, 101.3f, 607.2f),
                        PathNode.QuadTo(101.3f, 469.4f, 169.7f, 353.4f),
                        PathNode.QuadTo(238.0f, 237.4f, 354.3f, 169.3f),
                        PathNode.QuadTo(470.5f, 101.3f, 608.3f, 101.3f),
                        PathNode.QuadTo(746.1f, 101.3f, 862.1f, 169.3f),
                        PathNode.QuadTo(978.1f, 237.4f, 1046.1f, 353.4f),
                        PathNode.QuadTo(1114.2f, 469.4f, 1114.2f, 607.2f),
                        PathNode.Close,
                        PathNode.MoveTo(179.2f, 607.1f),
                        PathNode.QuadTo(179.2f, 724.2f, 236.8f, 822.6f),
                        PathNode.QuadTo(294.4f, 921.0f, 392.8f, 978.7f),
                        PathNode.QuadTo(491.2f, 1036.3f, 608.2f, 1036.3f),
                        PathNode.QuadTo(725.3f, 1036.3f, 823.7f, 978.7f),
                        PathNode.QuadTo(922.0f, 921.0f, 979.7f, 822.6f),
                        PathNode.QuadTo(1037.3f, 724.2f, 1037.3f, 607.1f),
                        PathNode.QuadTo(1037.3f, 491.0f, 979.7f, 392.6f),
                        PathNode.QuadTo(922.1f, 294.2f, 823.6f, 236.2f),
                        PathNode.QuadTo(725.2f, 178.2f, 608.2f, 178.2f),
                        PathNode.QuadTo(491.3f, 178.2f, 392.8f, 236.2f),
                        PathNode.QuadTo(294.3f, 294.2f, 236.7f, 392.6f),
                        PathNode.QuadTo(179.2f, 491.0f, 179.2f, 607.1f),
                        PathNode.Close,
                        PathNode.MoveTo(605.3f, 331.3f),
                        PathNode.QuadTo(625.7f, 331.3f, 640.4f, 346.2f),
                        PathNode.QuadTo(655.2f, 361.1f, 655.2f, 382.2f),
                        PathNode.QuadTo(655.2f, 403.4f, 640.4f, 418.3f),
                        PathNode.QuadTo(625.6f, 433.2f, 605.3f, 433.2f),
                        PathNode.QuadTo(584.1f, 433.2f, 569.2f, 418.3f),
                        PathNode.QuadTo(554.3f, 403.4f, 554.3f, 382.2f),
                        PathNode.QuadTo(554.3f, 361.1f, 569.1f, 346.2f),
                        PathNode.QuadTo(583.9f, 331.3f, 605.3f, 331.3f),
                        PathNode.Close,
                        PathNode.MoveTo(643.5f, 508.4f),
                        PathNode.QuadTo(651.5f, 538.5f, 665.0f, 560.8f),
                        PathNode.QuadTo(678.5f, 583.1f, 696.7f, 599.4f),
                        PathNode.LineTo(725.4f, 625.4f),
                        PathNode.QuadTo(775.2f, 672.4f, 775.2f, 738.2f),
                        PathNode.QuadTo(775.2f, 787.0f, 750.7f, 823.1f),
                        PathNode.QuadTo(726.3f, 859.1f, 688.6f, 878.1f),
                        PathNode.QuadTo(651.0f, 897.2f, 612.2f, 897.2f),
                        PathNode.QuadTo(565.2f, 897.2f, 526.8f, 875.1f),
                        PathNode.QuadTo(488.4f, 853.1f, 466.8f, 818.1f),
                        PathNode.QuadTo(445.3f, 783.1f, 445.3f, 744.7f),
                        PathNode.QuadTo(445.3f, 733.0f, 449.8f, 727.6f),
                        PathNode.QuadTo(454.4f, 722.2f, 464.3f, 722.2f),
                        PathNode.HorizontalTo(499.7f),
                        PathNode.QuadTo(512.1f, 722.2f, 517.4f, 728.9f),
                        PathNode.QuadTo(522.8f, 735.5f, 524.5f, 748.0f),
                        PathNode.QuadTo(528.2f, 784.5f, 554.4f, 802.4f),
                        PathNode.QuadTo(580.7f, 820.3f, 610.2f, 820.3f),
                        PathNode.QuadTo(641.8f, 820.3f, 669.5f, 799.2f),
                        PathNode.QuadTo(697.3f, 778.1f, 697.3f, 740.2f),
                        PathNode.QuadTo(697.3f, 708.4f, 675.0f, 687.1f),
                        PathNode.LineTo(634.0f, 646.4f),
                        PathNode.QuadTo(581.0f, 592.7f, 566.0f, 511.7f),
                        PathNode.QuadTo(564.3f, 502.7f, 569.7f, 496.4f),
                        PathNode.QuadTo(575.1f, 490.2f, 585.1f, 490.2f),
                        PathNode.HorizontalTo(617.6f),
                        PathNode.QuadTo(628.4f, 490.2f, 634.8f, 494.3f),
                        PathNode.QuadTo(641.2f, 498.3f, 643.5f, 508.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _helpNormal!!
    }

private var _helpNormal: ImageVector? = null

val MiuixIcons.Regular.Help: ImageVector
    get() {
        if (_helpRegular != null) return _helpRegular!!
        _helpRegular = ImageVector.Builder(
            name = "Help.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.2f,
            viewportHeight = 1225.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1225.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1123.1f, 612.1f),
                        PathNode.QuadTo(1123.1f, 751.1f, 1054.6f, 868.1f),
                        PathNode.QuadTo(986.1f, 985.1f, 869.1f, 1054.1f),
                        PathNode.QuadTo(752.1f, 1123.1f, 613.1f, 1123.1f),
                        PathNode.QuadTo(474.1f, 1123.1f, 357.1f, 1054.1f),
                        PathNode.QuadTo(240.1f, 985.1f, 171.1f, 868.1f),
                        PathNode.QuadTo(102.1f, 751.1f, 102.1f, 612.1f),
                        PathNode.QuadTo(102.1f, 473.1f, 171.1f, 356.1f),
                        PathNode.QuadTo(240.1f, 239.1f, 357.1f, 170.6f),
                        PathNode.QuadTo(474.1f, 102.1f, 613.1f, 102.1f),
                        PathNode.QuadTo(752.1f, 102.1f, 869.1f, 170.6f),
                        PathNode.QuadTo(986.1f, 239.1f, 1054.6f, 356.1f),
                        PathNode.QuadTo(1123.1f, 473.1f, 1123.1f, 612.1f),
                        PathNode.Close,
                        PathNode.MoveTo(188.1f, 612.1f),
                        PathNode.QuadTo(188.1f, 728.1f, 245.1f, 825.6f),
                        PathNode.QuadTo(302.1f, 923.1f, 399.6f, 980.1f),
                        PathNode.QuadTo(497.1f, 1037.1f, 613.1f, 1037.1f),
                        PathNode.QuadTo(729.1f, 1037.1f, 826.6f, 980.1f),
                        PathNode.QuadTo(924.1f, 923.1f, 981.1f, 825.6f),
                        PathNode.QuadTo(1038.1f, 728.1f, 1038.1f, 612.1f),
                        PathNode.QuadTo(1038.1f, 497.1f, 981.1f, 399.6f),
                        PathNode.QuadTo(924.1f, 302.1f, 826.6f, 244.6f),
                        PathNode.QuadTo(729.1f, 187.1f, 613.1f, 187.1f),
                        PathNode.QuadTo(497.1f, 187.1f, 399.6f, 244.6f),
                        PathNode.QuadTo(302.1f, 302.1f, 245.1f, 399.6f),
                        PathNode.QuadTo(188.1f, 497.1f, 188.1f, 612.1f),
                        PathNode.Close,
                        PathNode.MoveTo(610.1f, 332.1f),
                        PathNode.QuadTo(632.1f, 332.1f, 648.1f, 348.1f),
                        PathNode.QuadTo(664.1f, 364.1f, 664.1f, 387.1f),
                        PathNode.QuadTo(664.1f, 410.1f, 648.1f, 426.1f),
                        PathNode.QuadTo(632.1f, 442.1f, 610.1f, 442.1f),
                        PathNode.QuadTo(587.1f, 442.1f, 571.1f, 426.1f),
                        PathNode.QuadTo(555.1f, 410.1f, 555.1f, 387.1f),
                        PathNode.QuadTo(555.1f, 364.1f, 571.1f, 348.1f),
                        PathNode.QuadTo(587.1f, 332.1f, 610.1f, 332.1f),
                        PathNode.Close,
                        PathNode.MoveTo(653.1f, 515.1f),
                        PathNode.QuadTo(661.1f, 543.1f, 674.1f, 564.6f),
                        PathNode.QuadTo(687.1f, 586.1f, 704.1f, 601.1f),
                        PathNode.LineTo(733.1f, 627.1f),
                        PathNode.QuadTo(784.1f, 675.1f, 784.1f, 743.1f),
                        PathNode.QuadTo(784.1f, 793.1f, 759.1f, 830.1f),
                        PathNode.QuadTo(734.1f, 867.1f, 695.6f, 886.6f),
                        PathNode.QuadTo(657.1f, 906.1f, 617.1f, 906.1f),
                        PathNode.QuadTo(569.1f, 906.1f, 529.6f, 883.6f),
                        PathNode.QuadTo(490.1f, 861.1f, 468.1f, 825.6f),
                        PathNode.QuadTo(446.1f, 790.1f, 446.1f, 751.1f),
                        PathNode.QuadTo(446.1f, 738.1f, 451.1f, 732.1f),
                        PathNode.QuadTo(456.1f, 726.1f, 467.1f, 726.1f),
                        PathNode.HorizontalTo(506.1f),
                        PathNode.QuadTo(520.1f, 726.1f, 526.1f, 733.6f),
                        PathNode.QuadTo(532.1f, 741.1f, 534.1f, 755.1f),
                        PathNode.QuadTo(538.1f, 788.1f, 562.6f, 804.6f),
                        PathNode.QuadTo(587.1f, 821.1f, 615.1f, 821.1f),
                        PathNode.QuadTo(645.1f, 821.1f, 671.6f, 801.1f),
                        PathNode.QuadTo(698.1f, 781.1f, 698.1f, 745.1f),
                        PathNode.QuadTo(698.1f, 715.1f, 677.1f, 695.1f),
                        PathNode.LineTo(636.1f, 654.1f),
                        PathNode.QuadTo(582.1f, 600.1f, 567.1f, 519.1f),
                        PathNode.QuadTo(565.1f, 509.1f, 571.1f, 502.1f),
                        PathNode.QuadTo(577.1f, 495.1f, 588.1f, 495.1f),
                        PathNode.HorizontalTo(624.1f),
                        PathNode.QuadTo(636.1f, 495.1f, 643.1f, 499.6f),
                        PathNode.QuadTo(650.1f, 504.1f, 653.1f, 515.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _helpRegular!!
    }

private var _helpRegular: ImageVector? = null

val MiuixIcons.Medium.Help: ImageVector
    get() {
        if (_helpMedium != null) return _helpMedium!!
        _helpMedium = ImageVector.Builder(
            name = "Help.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1245.0f,
            viewportHeight = 1245.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1245.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1141.2f, 622.0f),
                        PathNode.QuadTo(1141.2f, 762.7f, 1071.5f, 881.8f),
                        PathNode.QuadTo(1001.8f, 1000.8f, 882.8f, 1071.0f),
                        PathNode.QuadTo(763.7f, 1141.2f, 623.0f, 1141.2f),
                        PathNode.QuadTo(482.2f, 1141.2f, 363.2f, 1071.0f),
                        PathNode.QuadTo(244.1f, 1000.8f, 173.9f, 881.8f),
                        PathNode.QuadTo(103.7f, 762.7f, 103.7f, 622.0f),
                        PathNode.QuadTo(103.7f, 481.2f, 173.9f, 362.2f),
                        PathNode.QuadTo(244.1f, 243.1f, 363.2f, 173.4f),
                        PathNode.QuadTo(482.2f, 103.7f, 623.0f, 103.7f),
                        PathNode.QuadTo(763.7f, 103.7f, 882.8f, 173.4f),
                        PathNode.QuadTo(1001.8f, 243.1f, 1071.5f, 362.2f),
                        PathNode.QuadTo(1141.2f, 481.2f, 1141.2f, 622.0f),
                        PathNode.Close,
                        PathNode.MoveTo(206.2f, 621.7f),
                        PathNode.QuadTo(206.2f, 735.6f, 262.1f, 831.3f),
                        PathNode.QuadTo(318.0f, 926.9f, 413.6f, 982.8f),
                        PathNode.QuadTo(509.2f, 1038.7f, 622.9f, 1038.7f),
                        PathNode.QuadTo(736.6f, 1038.7f, 832.3f, 982.9f),
                        PathNode.QuadTo(927.9f, 927.0f, 983.8f, 831.3f),
                        PathNode.QuadTo(1039.7f, 735.7f, 1039.7f, 622.0f),
                        PathNode.QuadTo(1039.7f, 508.7f, 983.9f, 413.3f),
                        PathNode.QuadTo(928.0f, 317.8f, 832.4f, 261.5f),
                        PathNode.QuadTo(736.8f, 205.2f, 623.1f, 205.2f),
                        PathNode.QuadTo(509.3f, 205.2f, 413.7f, 261.5f),
                        PathNode.QuadTo(318.0f, 317.8f, 262.1f, 413.3f),
                        PathNode.QuadTo(206.2f, 508.7f, 206.2f, 621.7f),
                        PathNode.Close,
                        PathNode.MoveTo(620.0f, 333.7f),
                        PathNode.QuadTo(644.9f, 333.7f, 662.7f, 351.8f),
                        PathNode.QuadTo(680.5f, 369.9f, 680.5f, 395.2f),
                        PathNode.QuadTo(680.5f, 420.6f, 662.7f, 438.6f),
                        PathNode.QuadTo(644.9f, 456.7f, 620.0f, 456.7f),
                        PathNode.QuadTo(594.0f, 456.7f, 576.3f, 438.6f),
                        PathNode.QuadTo(558.5f, 420.6f, 558.5f, 395.2f),
                        PathNode.QuadTo(558.5f, 369.9f, 576.4f, 351.8f),
                        PathNode.QuadTo(594.3f, 333.7f, 620.0f, 333.7f),
                        PathNode.Close,
                        PathNode.MoveTo(670.0f, 528.5f),
                        PathNode.QuadTo(676.9f, 551.8f, 689.6f, 572.1f),
                        PathNode.QuadTo(702.3f, 592.5f, 718.1f, 606.9f),
                        PathNode.LineTo(747.1f, 632.9f),
                        PathNode.QuadTo(800.5f, 683.2f, 800.5f, 753.3f),
                        PathNode.QuadTo(800.5f, 804.7f, 774.6f, 843.2f),
                        PathNode.QuadTo(748.7f, 881.7f, 708.4f, 902.1f),
                        PathNode.QuadTo(668.2f, 922.5f, 626.7f, 922.5f),
                        PathNode.QuadTo(577.0f, 922.5f, 536.0f, 899.0f),
                        PathNode.QuadTo(495.1f, 875.6f, 472.3f, 838.6f),
                        PathNode.QuadTo(449.5f, 801.6f, 449.5f, 761.0f),
                        PathNode.QuadTo(449.5f, 745.6f, 456.3f, 739.0f),
                        PathNode.QuadTo(463.0f, 732.5f, 477.0f, 732.5f),
                        PathNode.HorizontalTo(516.0f),
                        PathNode.QuadTo(532.3f, 732.5f, 540.4f, 740.2f),
                        PathNode.QuadTo(548.5f, 748.0f, 550.5f, 764.4f),
                        PathNode.QuadTo(554.1f, 794.5f, 576.7f, 809.5f),
                        PathNode.QuadTo(599.2f, 824.5f, 625.0f, 824.5f),
                        PathNode.QuadTo(652.6f, 824.5f, 677.1f, 806.0f),
                        PathNode.QuadTo(701.5f, 787.5f, 701.5f, 754.9f),
                        PathNode.QuadTo(701.5f, 727.8f, 682.3f, 709.7f),
                        PathNode.LineTo(641.3f, 668.7f),
                        PathNode.QuadTo(585.5f, 611.7f, 571.1f, 533.1f),
                        PathNode.QuadTo(568.5f, 519.6f, 575.4f, 511.7f),
                        PathNode.QuadTo(582.3f, 503.8f, 596.2f, 503.8f),
                        PathNode.HorizontalTo(638.7f),
                        PathNode.QuadTo(651.9f, 503.8f, 659.2f, 509.5f),
                        PathNode.QuadTo(666.5f, 515.2f, 670.0f, 528.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _helpMedium!!
    }

private var _helpMedium: ImageVector? = null

val MiuixIcons.Demibold.Help: ImageVector
    get() {
        if (_helpDemibold != null) return _helpDemibold!!
        _helpDemibold = ImageVector.Builder(
            name = "Help.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1258.8f,
            viewportHeight = 1258.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1258.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1153.9f, 628.9f),
                        PathNode.QuadTo(1153.9f, 770.9f, 1083.4f, 891.4f),
                        PathNode.QuadTo(1012.8f, 1011.8f, 892.4f, 1082.9f),
                        PathNode.QuadTo(771.9f, 1153.9f, 629.9f, 1153.9f),
                        PathNode.QuadTo(487.9f, 1153.9f, 367.4f, 1082.9f),
                        PathNode.QuadTo(247.0f, 1011.8f, 175.9f, 891.4f),
                        PathNode.QuadTo(104.9f, 770.9f, 104.9f, 628.9f),
                        PathNode.QuadTo(104.9f, 486.9f, 175.9f, 366.4f),
                        PathNode.QuadTo(247.0f, 246.0f, 367.4f, 175.4f),
                        PathNode.QuadTo(487.9f, 104.9f, 629.9f, 104.9f),
                        PathNode.QuadTo(771.9f, 104.9f, 892.4f, 175.4f),
                        PathNode.QuadTo(1012.8f, 246.0f, 1083.4f, 366.4f),
                        PathNode.QuadTo(1153.9f, 486.9f, 1153.9f, 628.9f),
                        PathNode.Close,
                        PathNode.MoveTo(218.9f, 628.4f),
                        PathNode.QuadTo(218.9f, 740.9f, 274.0f, 835.2f),
                        PathNode.QuadTo(329.1f, 929.6f, 423.4f, 984.7f),
                        PathNode.QuadTo(517.6f, 1039.9f, 629.8f, 1039.9f),
                        PathNode.QuadTo(741.9f, 1039.9f, 836.2f, 984.8f),
                        PathNode.QuadTo(930.6f, 929.7f, 985.7f, 835.4f),
                        PathNode.QuadTo(1040.9f, 741.1f, 1040.9f, 628.9f),
                        PathNode.QuadTo(1040.9f, 516.9f, 985.8f, 422.9f),
                        PathNode.QuadTo(930.7f, 328.8f, 836.4f, 273.4f),
                        PathNode.QuadTo(742.2f, 217.9f, 630.0f, 217.9f),
                        PathNode.QuadTo(517.9f, 217.9f, 423.6f, 273.4f),
                        PathNode.QuadTo(329.2f, 328.8f, 274.1f, 422.9f),
                        PathNode.QuadTo(218.9f, 516.9f, 218.9f, 628.4f),
                        PathNode.Close,
                        PathNode.MoveTo(626.9f, 334.9f),
                        PathNode.QuadTo(653.9f, 334.9f, 672.9f, 354.4f),
                        PathNode.QuadTo(691.9f, 373.9f, 691.9f, 400.9f),
                        PathNode.QuadTo(691.9f, 427.9f, 672.9f, 447.4f),
                        PathNode.QuadTo(653.9f, 466.9f, 627.0f, 466.9f),
                        PathNode.QuadTo(598.9f, 466.9f, 579.9f, 447.4f),
                        PathNode.QuadTo(560.9f, 427.9f, 560.9f, 400.9f),
                        PathNode.QuadTo(560.9f, 373.9f, 580.1f, 354.4f),
                        PathNode.QuadTo(599.3f, 334.9f, 626.9f, 334.9f),
                        PathNode.Close,
                        PathNode.MoveTo(681.9f, 537.9f),
                        PathNode.QuadTo(687.9f, 557.9f, 700.4f, 577.4f),
                        PathNode.QuadTo(712.9f, 596.9f, 727.9f, 610.9f),
                        PathNode.LineTo(756.9f, 636.9f),
                        PathNode.QuadTo(811.9f, 688.9f, 811.9f, 760.4f),
                        PathNode.QuadTo(811.9f, 812.9f, 785.4f, 852.4f),
                        PathNode.QuadTo(758.9f, 891.9f, 717.4f, 912.9f),
                        PathNode.QuadTo(675.9f, 933.9f, 633.4f, 933.9f),
                        PathNode.QuadTo(582.5f, 933.9f, 540.5f, 909.8f),
                        PathNode.QuadTo(498.6f, 885.7f, 475.3f, 847.7f),
                        PathNode.QuadTo(451.9f, 809.7f, 451.9f, 767.9f),
                        PathNode.QuadTo(451.9f, 750.9f, 459.9f, 743.9f),
                        PathNode.QuadTo(467.9f, 736.9f, 483.9f, 736.9f),
                        PathNode.HorizontalTo(522.9f),
                        PathNode.QuadTo(540.9f, 736.9f, 550.4f, 744.9f),
                        PathNode.QuadTo(559.9f, 752.9f, 561.9f, 770.9f),
                        PathNode.QuadTo(565.4f, 798.9f, 586.5f, 812.9f),
                        PathNode.QuadTo(607.7f, 826.9f, 632.0f, 826.9f),
                        PathNode.QuadTo(657.9f, 826.9f, 680.9f, 809.4f),
                        PathNode.QuadTo(703.9f, 791.9f, 703.9f, 761.8f),
                        PathNode.QuadTo(703.9f, 736.6f, 685.9f, 719.9f),
                        PathNode.LineTo(644.9f, 678.9f),
                        PathNode.QuadTo(587.9f, 619.9f, 573.9f, 542.9f),
                        PathNode.QuadTo(570.9f, 526.9f, 578.4f, 518.4f),
                        PathNode.QuadTo(585.9f, 509.9f, 601.9f, 509.9f),
                        PathNode.HorizontalTo(648.9f),
                        PathNode.QuadTo(662.9f, 509.9f, 670.4f, 516.4f),
                        PathNode.QuadTo(677.9f, 522.9f, 681.9f, 537.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _helpDemibold!!
    }

private var _helpDemibold: ImageVector? = null
