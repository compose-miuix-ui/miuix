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
            group(scaleY = -1.0f, translationY = 1194.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1094.5f, 596.5f),
                        PathNode.QuadTo(1094.5f, 731.5f, 1027.5f, 846.0f),
                        PathNode.QuadTo(960.5f, 960.5f, 846.5f, 1027.5f),
                        PathNode.QuadTo(732.5f, 1094.5f, 597.5f, 1094.5f),
                        PathNode.QuadTo(462.5f, 1094.5f, 348.0f, 1027.5f),
                        PathNode.QuadTo(233.5f, 960.5f, 166.5f, 846.0f),
                        PathNode.QuadTo(99.5f, 731.5f, 99.5f, 596.5f),
                        PathNode.QuadTo(99.5f, 461.5f, 166.5f, 347.5f),
                        PathNode.QuadTo(233.5f, 233.5f, 348.0f, 166.5f),
                        PathNode.QuadTo(462.5f, 99.5f, 597.5f, 99.5f),
                        PathNode.QuadTo(732.5f, 99.5f, 846.5f, 166.5f),
                        PathNode.QuadTo(960.5f, 233.5f, 1027.5f, 347.5f),
                        PathNode.QuadTo(1094.5f, 461.5f, 1094.5f, 596.5f),
                        PathNode.Close,
                        PathNode.MoveTo(159.5f, 596.5f),
                        PathNode.QuadTo(159.5f, 715.5f, 218.5f, 816.0f),
                        PathNode.QuadTo(277.5f, 916.5f, 378.0f, 975.5f),
                        PathNode.QuadTo(478.5f, 1034.5f, 597.5f, 1034.5f),
                        PathNode.QuadTo(716.5f, 1034.5f, 817.0f, 975.5f),
                        PathNode.QuadTo(917.5f, 916.5f, 976.5f, 816.0f),
                        PathNode.QuadTo(1035.5f, 715.5f, 1035.5f, 596.5f),
                        PathNode.QuadTo(1035.5f, 477.5f, 976.5f, 377.0f),
                        PathNode.QuadTo(917.5f, 276.5f, 817.0f, 217.5f),
                        PathNode.QuadTo(716.5f, 158.5f, 597.5f, 158.5f),
                        PathNode.QuadTo(478.5f, 158.5f, 378.0f, 217.5f),
                        PathNode.QuadTo(277.5f, 276.5f, 218.5f, 377.0f),
                        PathNode.QuadTo(159.5f, 477.5f, 159.5f, 596.5f),
                        PathNode.Close,
                        PathNode.MoveTo(827.5f, 457.5f),
                        PathNode.LineTo(732.5f, 512.5f),
                        PathNode.QuadTo(726.5f, 516.5f, 719.0f, 516.5f),
                        PathNode.QuadTo(711.5f, 516.5f, 705.5f, 512.5f),
                        PathNode.LineTo(670.5f, 493.5f),
                        PathNode.QuadTo(654.5f, 485.5f, 637.0f, 486.0f),
                        PathNode.QuadTo(619.5f, 486.5f, 605.5f, 495.5f),
                        PathNode.QuadTo(564.5f, 518.5f, 542.5f, 543.5f),
                        PathNode.QuadTo(525.5f, 561.5f, 495.5f, 604.5f),
                        PathNode.QuadTo(487.5f, 619.5f, 486.5f, 637.5f),
                        PathNode.QuadTo(485.5f, 655.5f, 493.5f, 669.5f),
                        PathNode.LineTo(513.5f, 704.5f),
                        PathNode.QuadTo(517.5f, 712.5f, 517.0f, 718.5f),
                        PathNode.QuadTo(516.5f, 724.5f, 512.5f, 732.5f),
                        PathNode.LineTo(458.5f, 826.5f),
                        PathNode.QuadTo(453.5f, 835.5f, 443.5f, 839.0f),
                        PathNode.QuadTo(433.5f, 842.5f, 423.5f, 837.5f),
                        PathNode.LineTo(386.5f, 815.5f),
                        PathNode.QuadTo(363.5f, 801.5f, 356.5f, 772.5f),
                        PathNode.QuadTo(349.5f, 743.5f, 359.5f, 712.5f),
                        PathNode.QuadTo(379.5f, 653.5f, 415.0f, 598.0f),
                        PathNode.QuadTo(450.5f, 542.5f, 496.5f, 496.5f),
                        PathNode.QuadTo(542.5f, 451.5f, 598.0f, 415.0f),
                        PathNode.QuadTo(653.5f, 378.5f, 712.5f, 358.5f),
                        PathNode.QuadTo(738.5f, 349.5f, 769.0f, 355.0f),
                        PathNode.QuadTo(799.5f, 360.5f, 812.5f, 382.5f),
                        PathNode.LineTo(836.5f, 420.5f),
                        PathNode.QuadTo(842.5f, 429.5f, 839.5f, 441.0f),
                        PathNode.QuadTo(836.5f, 452.5f, 827.5f, 457.5f),
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

val MiuixIcons.Normal.Answer: ImageVector
    get() {
        if (_answerNormal != null) return _answerNormal!!
        _answerNormal = ImageVector.Builder(
            name = "Answer.Normal",
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
                        PathNode.QuadTo(978.1f, 977.4f, 862.0f, 1045.8f),
                        PathNode.QuadTo(746.0f, 1114.2f, 608.2f, 1114.2f),
                        PathNode.QuadTo(470.5f, 1114.2f, 354.3f, 1045.8f),
                        PathNode.QuadTo(238.0f, 977.4f, 169.7f, 861.2f),
                        PathNode.QuadTo(101.3f, 745.0f, 101.3f, 607.2f),
                        PathNode.QuadTo(101.3f, 469.5f, 169.7f, 353.4f),
                        PathNode.QuadTo(238.0f, 237.4f, 354.3f, 169.3f),
                        PathNode.QuadTo(470.5f, 101.3f, 608.2f, 101.3f),
                        PathNode.QuadTo(746.0f, 101.3f, 862.0f, 169.3f),
                        PathNode.QuadTo(978.1f, 237.4f, 1046.1f, 353.4f),
                        PathNode.QuadTo(1114.2f, 469.5f, 1114.2f, 607.2f),
                        PathNode.Close,
                        PathNode.MoveTo(179.2f, 607.2f),
                        PathNode.QuadTo(179.2f, 724.2f, 236.8f, 822.6f),
                        PathNode.QuadTo(294.4f, 921.0f, 392.9f, 978.7f),
                        PathNode.QuadTo(491.3f, 1036.3f, 608.2f, 1036.3f),
                        PathNode.QuadTo(724.5f, 1036.3f, 822.9f, 978.7f),
                        PathNode.QuadTo(921.4f, 921.0f, 979.3f, 822.6f),
                        PathNode.QuadTo(1037.3f, 724.2f, 1037.3f, 607.2f),
                        PathNode.QuadTo(1037.3f, 491.0f, 979.3f, 392.5f),
                        PathNode.QuadTo(921.4f, 294.1f, 822.9f, 236.1f),
                        PathNode.QuadTo(724.5f, 178.2f, 608.2f, 178.2f),
                        PathNode.QuadTo(491.3f, 178.2f, 392.9f, 236.1f),
                        PathNode.QuadTo(294.4f, 294.1f, 236.8f, 392.5f),
                        PathNode.QuadTo(179.2f, 491.0f, 179.2f, 607.2f),
                        PathNode.Close,
                        PathNode.MoveTo(841.0f, 473.7f),
                        PathNode.LineTo(746.0f, 528.7f),
                        PathNode.QuadTo(739.3f, 533.4f, 730.1f, 533.4f),
                        PathNode.QuadTo(720.9f, 533.4f, 713.5f, 528.7f),
                        PathNode.LineTo(678.5f, 509.7f),
                        PathNode.QuadTo(664.5f, 502.4f, 648.8f, 502.9f),
                        PathNode.QuadTo(633.0f, 503.4f, 619.0f, 511.7f),
                        PathNode.QuadTo(578.0f, 536.1f, 558.0f, 558.4f),
                        PathNode.QuadTo(536.9f, 580.5f, 511.7f, 618.7f),
                        PathNode.QuadTo(503.7f, 632.3f, 503.1f, 648.2f),
                        PathNode.QuadTo(502.4f, 664.2f, 509.7f, 677.5f),
                        PathNode.LineTo(529.0f, 712.5f),
                        PathNode.QuadTo(533.7f, 721.2f, 533.6f, 728.9f),
                        PathNode.QuadTo(533.4f, 736.6f, 528.7f, 746.0f),
                        PathNode.LineTo(474.7f, 840.0f),
                        PathNode.QuadTo(468.4f, 851.0f, 455.6f, 855.6f),
                        PathNode.QuadTo(442.9f, 860.1f, 431.5f, 853.7f),
                        PathNode.LineTo(394.5f, 831.7f),
                        PathNode.QuadTo(368.7f, 816.4f, 361.4f, 785.3f),
                        PathNode.QuadTo(354.0f, 754.2f, 364.7f, 721.2f),
                        PathNode.QuadTo(384.7f, 662.2f, 420.9f, 605.6f),
                        PathNode.QuadTo(457.1f, 549.1f, 503.1f, 503.1f),
                        PathNode.QuadTo(549.1f, 457.4f, 605.3f, 420.6f),
                        PathNode.QuadTo(661.5f, 383.7f, 721.2f, 363.7f),
                        PathNode.QuadTo(749.2f, 354.0f, 781.4f, 359.9f),
                        PathNode.QuadTo(813.7f, 365.7f, 828.7f, 389.8f),
                        PathNode.LineTo(853.4f, 429.2f),
                        PathNode.QuadTo(860.1f, 440.2f, 856.1f, 453.8f),
                        PathNode.QuadTo(852.0f, 467.4f, 841.0f, 473.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerNormal!!
    }

private var _answerNormal: ImageVector? = null

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
                        PathNode.QuadTo(728.1f, 1037.1f, 825.6f, 980.1f),
                        PathNode.QuadTo(923.1f, 923.1f, 980.6f, 825.6f),
                        PathNode.QuadTo(1038.1f, 728.1f, 1038.1f, 612.1f),
                        PathNode.QuadTo(1038.1f, 497.1f, 980.6f, 399.6f),
                        PathNode.QuadTo(923.1f, 302.1f, 825.6f, 244.6f),
                        PathNode.QuadTo(728.1f, 187.1f, 613.1f, 187.1f),
                        PathNode.QuadTo(497.1f, 187.1f, 399.6f, 244.6f),
                        PathNode.QuadTo(302.1f, 302.1f, 245.1f, 399.6f),
                        PathNode.QuadTo(188.1f, 497.1f, 188.1f, 612.1f),
                        PathNode.Close,
                        PathNode.MoveTo(847.1f, 481.1f),
                        PathNode.LineTo(752.1f, 536.1f),
                        PathNode.QuadTo(745.1f, 541.1f, 735.1f, 541.1f),
                        PathNode.QuadTo(725.1f, 541.1f, 717.1f, 536.1f),
                        PathNode.LineTo(682.1f, 517.1f),
                        PathNode.QuadTo(669.1f, 510.1f, 654.1f, 510.6f),
                        PathNode.QuadTo(639.1f, 511.1f, 625.1f, 519.1f),
                        PathNode.QuadTo(584.1f, 544.1f, 565.1f, 565.1f),
                        PathNode.QuadTo(542.1f, 589.1f, 519.1f, 625.1f),
                        PathNode.QuadTo(511.1f, 638.1f, 510.6f, 653.1f),
                        PathNode.QuadTo(510.1f, 668.1f, 517.1f, 681.1f),
                        PathNode.LineTo(536.1f, 716.1f),
                        PathNode.QuadTo(541.1f, 725.1f, 541.1f, 733.6f),
                        PathNode.QuadTo(541.1f, 742.1f, 536.1f, 752.1f),
                        PathNode.LineTo(482.1f, 846.1f),
                        PathNode.QuadTo(475.1f, 858.1f, 461.1f, 863.1f),
                        PathNode.QuadTo(447.1f, 868.1f, 435.1f, 861.1f),
                        PathNode.LineTo(398.1f, 839.1f),
                        PathNode.QuadTo(371.1f, 823.1f, 363.6f, 791.1f),
                        PathNode.QuadTo(356.1f, 759.1f, 367.1f, 725.1f),
                        PathNode.QuadTo(387.1f, 666.1f, 423.6f, 609.1f),
                        PathNode.QuadTo(460.1f, 552.1f, 506.1f, 506.1f),
                        PathNode.QuadTo(552.1f, 460.1f, 608.6f, 423.1f),
                        PathNode.QuadTo(665.1f, 386.1f, 725.1f, 366.1f),
                        PathNode.QuadTo(754.1f, 356.1f, 787.1f, 362.1f),
                        PathNode.QuadTo(820.1f, 368.1f, 836.1f, 393.1f),
                        PathNode.LineTo(861.1f, 433.1f),
                        PathNode.QuadTo(868.1f, 445.1f, 863.6f, 459.6f),
                        PathNode.QuadTo(859.1f, 474.1f, 847.1f, 481.1f),
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

val MiuixIcons.Medium.Answer: ImageVector
    get() {
        if (_answerMedium != null) return _answerMedium!!
        _answerMedium = ImageVector.Builder(
            name = "Answer.Medium",
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
                        PathNode.QuadTo(1001.9f, 1000.9f, 882.8f, 1071.0f),
                        PathNode.QuadTo(763.7f, 1141.2f, 623.0f, 1141.2f),
                        PathNode.QuadTo(482.2f, 1141.2f, 363.2f, 1071.0f),
                        PathNode.QuadTo(244.1f, 1000.9f, 173.9f, 881.8f),
                        PathNode.QuadTo(103.7f, 762.7f, 103.7f, 622.0f),
                        PathNode.QuadTo(103.7f, 481.2f, 173.9f, 362.2f),
                        PathNode.QuadTo(244.1f, 243.1f, 363.2f, 173.4f),
                        PathNode.QuadTo(482.2f, 103.7f, 623.0f, 103.7f),
                        PathNode.QuadTo(763.7f, 103.7f, 882.8f, 173.4f),
                        PathNode.QuadTo(1001.9f, 243.1f, 1071.5f, 362.2f),
                        PathNode.QuadTo(1141.2f, 481.2f, 1141.2f, 622.0f),
                        PathNode.Close,
                        PathNode.MoveTo(206.2f, 622.0f),
                        PathNode.QuadTo(206.2f, 735.6f, 262.0f, 831.4f),
                        PathNode.QuadTo(317.9f, 927.1f, 413.6f, 982.9f),
                        PathNode.QuadTo(509.3f, 1038.7f, 623.0f, 1038.7f),
                        PathNode.QuadTo(736.2f, 1038.7f, 831.7f, 982.9f),
                        PathNode.QuadTo(927.1f, 927.1f, 983.4f, 831.4f),
                        PathNode.QuadTo(1039.7f, 735.6f, 1039.7f, 622.0f),
                        PathNode.QuadTo(1039.7f, 508.7f, 983.4f, 413.3f),
                        PathNode.QuadTo(927.1f, 317.9f, 831.7f, 261.5f),
                        PathNode.QuadTo(736.2f, 205.2f, 623.0f, 205.2f),
                        PathNode.QuadTo(509.3f, 205.2f, 413.6f, 261.5f),
                        PathNode.QuadTo(317.9f, 317.9f, 262.0f, 413.3f),
                        PathNode.QuadTo(206.2f, 508.7f, 206.2f, 622.0f),
                        PathNode.Close,
                        PathNode.MoveTo(859.9f, 495.7f),
                        PathNode.LineTo(764.9f, 550.7f),
                        PathNode.QuadTo(756.2f, 556.3f, 744.7f, 556.3f),
                        PathNode.QuadTo(733.2f, 556.3f, 724.0f, 550.7f),
                        PathNode.LineTo(689.0f, 531.7f),
                        PathNode.QuadTo(677.8f, 525.3f, 664.0f, 525.8f),
                        PathNode.QuadTo(650.2f, 526.3f, 637.9f, 533.7f),
                        PathNode.QuadTo(596.9f, 558.1f, 579.1f, 578.5f),
                        PathNode.QuadTo(554.3f, 604.9f, 533.7f, 637.9f),
                        PathNode.QuadTo(526.9f, 649.7f, 526.4f, 663.6f),
                        PathNode.QuadTo(525.9f, 677.4f, 531.7f, 688.6f),
                        PathNode.LineTo(550.7f, 723.0f),
                        PathNode.QuadTo(556.9f, 733.2f, 556.9f, 743.5f),
                        PathNode.QuadTo(556.9f, 753.7f, 550.7f, 764.9f),
                        PathNode.LineTo(496.7f, 858.9f),
                        PathNode.QuadTo(488.5f, 873.3f, 472.2f, 878.3f),
                        PathNode.QuadTo(455.8f, 883.3f, 442.0f, 875.7f),
                        PathNode.LineTo(405.0f, 853.7f),
                        PathNode.QuadTo(376.9f, 836.5f, 368.5f, 802.5f),
                        PathNode.QuadTo(360.1f, 768.4f, 371.7f, 733.2f),
                        PathNode.QuadTo(391.7f, 673.0f, 429.1f, 615.7f),
                        PathNode.QuadTo(466.5f, 558.5f, 512.5f, 512.5f),
                        PathNode.QuadTo(557.9f, 466.5f, 615.2f, 428.9f),
                        PathNode.QuadTo(672.6f, 391.3f, 733.2f, 370.7f),
                        PathNode.QuadTo(764.0f, 360.1f, 798.7f, 366.7f),
                        PathNode.QuadTo(833.5f, 373.3f, 850.7f, 400.0f),
                        PathNode.LineTo(875.7f, 440.0f),
                        PathNode.QuadTo(883.9f, 453.8f, 878.8f, 470.7f),
                        PathNode.QuadTo(873.7f, 487.5f, 859.9f, 495.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerMedium!!
    }

private var _answerMedium: ImageVector? = null

val MiuixIcons.Demibold.Answer: ImageVector
    get() {
        if (_answerDemibold != null) return _answerDemibold!!
        _answerDemibold = ImageVector.Builder(
            name = "Answer.Demibold",
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
                        PathNode.QuadTo(1012.9f, 1011.9f, 892.4f, 1082.9f),
                        PathNode.QuadTo(771.9f, 1153.9f, 629.9f, 1153.9f),
                        PathNode.QuadTo(487.9f, 1153.9f, 367.4f, 1082.9f),
                        PathNode.QuadTo(246.9f, 1011.9f, 175.9f, 891.4f),
                        PathNode.QuadTo(104.9f, 770.9f, 104.9f, 628.9f),
                        PathNode.QuadTo(104.9f, 486.9f, 175.9f, 366.4f),
                        PathNode.QuadTo(246.9f, 245.9f, 367.4f, 175.4f),
                        PathNode.QuadTo(487.9f, 104.9f, 629.9f, 104.9f),
                        PathNode.QuadTo(771.9f, 104.9f, 892.4f, 175.4f),
                        PathNode.QuadTo(1012.9f, 245.9f, 1083.4f, 366.4f),
                        PathNode.QuadTo(1153.9f, 486.9f, 1153.9f, 628.9f),
                        PathNode.Close,
                        PathNode.MoveTo(218.9f, 628.9f),
                        PathNode.QuadTo(218.9f, 740.9f, 273.9f, 835.4f),
                        PathNode.QuadTo(328.9f, 929.9f, 423.4f, 984.9f),
                        PathNode.QuadTo(517.9f, 1039.9f, 629.9f, 1039.9f),
                        PathNode.QuadTo(741.9f, 1039.9f, 835.9f, 984.9f),
                        PathNode.QuadTo(929.9f, 929.9f, 985.4f, 835.4f),
                        PathNode.QuadTo(1040.9f, 740.9f, 1040.9f, 628.9f),
                        PathNode.QuadTo(1040.9f, 516.9f, 985.4f, 422.9f),
                        PathNode.QuadTo(929.9f, 328.9f, 835.9f, 273.4f),
                        PathNode.QuadTo(741.9f, 217.9f, 629.9f, 217.9f),
                        PathNode.QuadTo(517.9f, 217.9f, 423.4f, 273.4f),
                        PathNode.QuadTo(328.9f, 328.9f, 273.9f, 422.9f),
                        PathNode.QuadTo(218.9f, 516.9f, 218.9f, 628.9f),
                        PathNode.Close,
                        PathNode.MoveTo(868.9f, 505.9f),
                        PathNode.LineTo(773.9f, 560.9f),
                        PathNode.QuadTo(763.9f, 566.9f, 751.4f, 566.9f),
                        PathNode.QuadTo(738.9f, 566.9f, 728.9f, 560.9f),
                        PathNode.LineTo(693.9f, 541.9f),
                        PathNode.QuadTo(683.9f, 535.9f, 670.9f, 536.4f),
                        PathNode.QuadTo(657.9f, 536.9f, 646.9f, 543.9f),
                        PathNode.QuadTo(605.9f, 567.9f, 588.9f, 587.9f),
                        PathNode.QuadTo(562.9f, 615.9f, 543.9f, 646.9f),
                        PathNode.QuadTo(537.9f, 657.9f, 537.4f, 670.9f),
                        PathNode.QuadTo(536.9f, 683.9f, 541.9f, 693.9f),
                        PathNode.LineTo(560.9f, 727.9f),
                        PathNode.QuadTo(567.9f, 738.9f, 567.9f, 750.4f),
                        PathNode.QuadTo(567.9f, 761.9f, 560.9f, 773.9f),
                        PathNode.LineTo(506.9f, 867.9f),
                        PathNode.QuadTo(497.9f, 883.9f, 479.9f, 888.9f),
                        PathNode.QuadTo(461.9f, 893.9f, 446.9f, 885.9f),
                        PathNode.LineTo(409.9f, 863.9f),
                        PathNode.QuadTo(380.9f, 845.9f, 371.9f, 810.4f),
                        PathNode.QuadTo(362.9f, 774.9f, 374.9f, 738.9f),
                        PathNode.QuadTo(394.9f, 677.9f, 432.9f, 620.4f),
                        PathNode.QuadTo(470.9f, 562.9f, 516.9f, 516.9f),
                        PathNode.QuadTo(561.9f, 470.9f, 619.9f, 432.9f),
                        PathNode.QuadTo(677.9f, 394.9f, 738.9f, 373.9f),
                        PathNode.QuadTo(770.9f, 362.9f, 806.9f, 369.9f),
                        PathNode.QuadTo(842.9f, 376.9f, 860.9f, 404.9f),
                        PathNode.LineTo(885.9f, 444.9f),
                        PathNode.QuadTo(894.9f, 459.9f, 889.4f, 478.4f),
                        PathNode.QuadTo(883.9f, 496.9f, 868.9f, 505.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _answerDemibold!!
    }

private var _answerDemibold: ImageVector? = null
