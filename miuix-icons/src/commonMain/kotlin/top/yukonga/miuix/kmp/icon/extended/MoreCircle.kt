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

val MiuixIcons.MoreCircle: ImageVector
    get() = MiuixIcons.Regular.MoreCircle

val MiuixIcons.Light.MoreCircle: ImageVector
    get() {
        if (_morecircleLight != null) return _morecircleLight!!
        _morecircleLight = ImageVector.Builder(
            name = "MoreCircle.Light",
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
                        PathNode.MoveTo(159.5f, 596.5f),
                        PathNode.QuadTo(159.5f, 715.9f, 218.5f, 816.2f),
                        PathNode.QuadTo(277.5f, 916.5f, 378.0f, 975.5f),
                        PathNode.QuadTo(478.5f, 1034.5f, 598.0f, 1034.5f),
                        PathNode.QuadTo(716.5f, 1034.5f, 816.8f, 975.7f),
                        PathNode.QuadTo(917.2f, 916.8f, 976.3f, 816.2f),
                        PathNode.QuadTo(1035.5f, 715.5f, 1035.5f, 596.5f),
                        PathNode.QuadTo(1035.5f, 477.5f, 976.3f, 376.8f),
                        PathNode.QuadTo(917.2f, 276.2f, 816.8f, 217.3f),
                        PathNode.QuadTo(716.5f, 158.5f, 598.0f, 158.5f),
                        PathNode.QuadTo(478.5f, 158.5f, 378.0f, 217.5f),
                        PathNode.QuadTo(277.5f, 276.5f, 218.5f, 376.8f),
                        PathNode.QuadTo(159.5f, 477.1f, 159.5f, 596.5f),
                        PathNode.Close,
                        PathNode.MoveTo(428.5f, 596.2f),
                        PathNode.QuadTo(428.5f, 613.5f, 416.3f, 626.0f),
                        PathNode.QuadTo(404.1f, 638.5f, 387.3f, 638.5f),
                        PathNode.QuadTo(370.5f, 638.5f, 358.0f, 626.2f),
                        PathNode.QuadTo(345.5f, 614.0f, 345.5f, 596.4f),
                        PathNode.QuadTo(345.5f, 579.5f, 358.0f, 567.5f),
                        PathNode.QuadTo(370.5f, 555.5f, 387.5f, 555.5f),
                        PathNode.QuadTo(404.5f, 555.5f, 416.5f, 567.6f),
                        PathNode.QuadTo(428.5f, 579.6f, 428.5f, 596.2f),
                        PathNode.Close,
                        PathNode.MoveTo(638.5f, 596.2f),
                        PathNode.QuadTo(638.5f, 613.5f, 626.3f, 626.0f),
                        PathNode.QuadTo(614.1f, 638.5f, 597.3f, 638.5f),
                        PathNode.QuadTo(580.5f, 638.5f, 568.0f, 626.2f),
                        PathNode.QuadTo(555.5f, 614.0f, 555.5f, 596.4f),
                        PathNode.QuadTo(555.5f, 579.5f, 568.0f, 567.5f),
                        PathNode.QuadTo(580.5f, 555.5f, 597.5f, 555.5f),
                        PathNode.QuadTo(614.5f, 555.5f, 626.5f, 567.6f),
                        PathNode.QuadTo(638.5f, 579.6f, 638.5f, 596.2f),
                        PathNode.Close,
                        PathNode.MoveTo(848.5f, 596.2f),
                        PathNode.QuadTo(848.5f, 613.5f, 836.4f, 626.0f),
                        PathNode.QuadTo(824.4f, 638.5f, 807.8f, 638.5f),
                        PathNode.QuadTo(790.5f, 638.5f, 778.0f, 626.0f),
                        PathNode.QuadTo(765.5f, 613.5f, 765.5f, 596.5f),
                        PathNode.QuadTo(765.5f, 579.5f, 777.6f, 567.5f),
                        PathNode.QuadTo(789.7f, 555.5f, 807.1f, 555.5f),
                        PathNode.QuadTo(824.5f, 555.5f, 836.5f, 567.6f),
                        PathNode.QuadTo(848.5f, 579.6f, 848.5f, 596.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _morecircleLight!!
    }

private var _morecircleLight: ImageVector? = null

val MiuixIcons.Normal.MoreCircle: ImageVector
    get() {
        if (_morecircleNormal != null) return _morecircleNormal!!
        _morecircleNormal = ImageVector.Builder(
            name = "MoreCircle.Normal",
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
                        PathNode.MoveTo(179.2f, 607.2f),
                        PathNode.QuadTo(179.2f, 724.3f, 236.8f, 822.7f),
                        PathNode.QuadTo(294.4f, 921.0f, 392.8f, 978.7f),
                        PathNode.QuadTo(491.3f, 1036.3f, 608.4f, 1036.3f),
                        PathNode.QuadTo(724.5f, 1036.3f, 822.9f, 978.7f),
                        PathNode.QuadTo(921.2f, 921.1f, 979.3f, 822.6f),
                        PathNode.QuadTo(1037.3f, 724.2f, 1037.3f, 607.2f),
                        PathNode.QuadTo(1037.3f, 490.3f, 979.3f, 391.8f),
                        PathNode.QuadTo(921.2f, 293.3f, 822.9f, 235.7f),
                        PathNode.QuadTo(724.5f, 178.2f, 608.4f, 178.2f),
                        PathNode.QuadTo(491.3f, 178.2f, 392.8f, 235.8f),
                        PathNode.QuadTo(294.4f, 293.4f, 236.8f, 391.8f),
                        PathNode.QuadTo(179.2f, 490.2f, 179.2f, 607.2f),
                        PathNode.Close,
                        PathNode.MoveTo(448.2f, 607.1f),
                        PathNode.QuadTo(448.2f, 628.4f, 433.3f, 643.3f),
                        PathNode.QuadTo(418.5f, 658.2f, 398.2f, 658.2f),
                        PathNode.QuadTo(377.8f, 658.2f, 362.5f, 643.3f),
                        PathNode.QuadTo(347.3f, 628.5f, 347.3f, 607.2f),
                        PathNode.QuadTo(347.3f, 586.8f, 362.2f, 572.0f),
                        PathNode.QuadTo(377.1f, 557.3f, 398.2f, 557.3f),
                        PathNode.QuadTo(419.4f, 557.3f, 433.8f, 572.1f),
                        PathNode.QuadTo(448.2f, 586.8f, 448.2f, 607.1f),
                        PathNode.Close,
                        PathNode.MoveTo(658.2f, 607.1f),
                        PathNode.QuadTo(658.2f, 628.4f, 643.3f, 643.3f),
                        PathNode.QuadTo(628.5f, 658.2f, 608.2f, 658.2f),
                        PathNode.QuadTo(587.8f, 658.2f, 572.5f, 643.3f),
                        PathNode.QuadTo(557.3f, 628.5f, 557.3f, 607.2f),
                        PathNode.QuadTo(557.3f, 586.8f, 572.2f, 572.0f),
                        PathNode.QuadTo(587.1f, 557.3f, 608.2f, 557.3f),
                        PathNode.QuadTo(629.4f, 557.3f, 643.8f, 572.1f),
                        PathNode.QuadTo(658.2f, 586.8f, 658.2f, 607.1f),
                        PathNode.Close,
                        PathNode.MoveTo(868.2f, 607.1f),
                        PathNode.QuadTo(868.2f, 628.4f, 853.4f, 643.3f),
                        PathNode.QuadTo(838.6f, 658.2f, 818.3f, 658.2f),
                        PathNode.QuadTo(797.1f, 658.2f, 782.2f, 643.3f),
                        PathNode.QuadTo(767.3f, 628.4f, 767.3f, 607.2f),
                        PathNode.QuadTo(767.3f, 586.1f, 782.1f, 571.7f),
                        PathNode.QuadTo(796.9f, 557.3f, 818.1f, 557.3f),
                        PathNode.QuadTo(839.4f, 557.3f, 853.8f, 572.1f),
                        PathNode.QuadTo(868.2f, 586.8f, 868.2f, 607.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _morecircleNormal!!
    }

private var _morecircleNormal: ImageVector? = null

val MiuixIcons.Regular.MoreCircle: ImageVector
    get() {
        if (_morecircleRegular != null) return _morecircleRegular!!
        _morecircleRegular = ImageVector.Builder(
            name = "MoreCircle.Regular",
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
                        PathNode.QuadTo(1038.1f, 496.1f, 980.6f, 398.6f),
                        PathNode.QuadTo(923.1f, 301.1f, 825.6f, 244.1f),
                        PathNode.QuadTo(728.1f, 187.1f, 613.1f, 187.1f),
                        PathNode.QuadTo(497.1f, 187.1f, 399.6f, 244.1f),
                        PathNode.QuadTo(302.1f, 301.1f, 245.1f, 398.6f),
                        PathNode.QuadTo(188.1f, 496.1f, 188.1f, 612.1f),
                        PathNode.Close,
                        PathNode.MoveTo(457.1f, 612.1f),
                        PathNode.QuadTo(457.1f, 635.1f, 441.1f, 651.1f),
                        PathNode.QuadTo(425.1f, 667.1f, 403.1f, 667.1f),
                        PathNode.QuadTo(381.1f, 667.1f, 364.6f, 651.1f),
                        PathNode.QuadTo(348.1f, 635.1f, 348.1f, 612.1f),
                        PathNode.QuadTo(348.1f, 590.1f, 364.1f, 574.1f),
                        PathNode.QuadTo(380.1f, 558.1f, 403.1f, 558.1f),
                        PathNode.QuadTo(426.1f, 558.1f, 441.6f, 574.1f),
                        PathNode.QuadTo(457.1f, 590.1f, 457.1f, 612.1f),
                        PathNode.Close,
                        PathNode.MoveTo(667.1f, 612.1f),
                        PathNode.QuadTo(667.1f, 635.1f, 651.1f, 651.1f),
                        PathNode.QuadTo(635.1f, 667.1f, 613.1f, 667.1f),
                        PathNode.QuadTo(591.1f, 667.1f, 574.6f, 651.1f),
                        PathNode.QuadTo(558.1f, 635.1f, 558.1f, 612.1f),
                        PathNode.QuadTo(558.1f, 590.1f, 574.1f, 574.1f),
                        PathNode.QuadTo(590.1f, 558.1f, 613.1f, 558.1f),
                        PathNode.QuadTo(636.1f, 558.1f, 651.6f, 574.1f),
                        PathNode.QuadTo(667.1f, 590.1f, 667.1f, 612.1f),
                        PathNode.Close,
                        PathNode.MoveTo(877.1f, 612.1f),
                        PathNode.QuadTo(877.1f, 635.1f, 861.1f, 651.1f),
                        PathNode.QuadTo(845.1f, 667.1f, 823.1f, 667.1f),
                        PathNode.QuadTo(800.1f, 667.1f, 784.1f, 651.1f),
                        PathNode.QuadTo(768.1f, 635.1f, 768.1f, 612.1f),
                        PathNode.QuadTo(768.1f, 589.1f, 784.1f, 573.6f),
                        PathNode.QuadTo(800.1f, 558.1f, 823.1f, 558.1f),
                        PathNode.QuadTo(846.1f, 558.1f, 861.6f, 574.1f),
                        PathNode.QuadTo(877.1f, 590.1f, 877.1f, 612.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _morecircleRegular!!
    }

private var _morecircleRegular: ImageVector? = null

val MiuixIcons.Medium.MoreCircle: ImageVector
    get() {
        if (_morecircleMedium != null) return _morecircleMedium!!
        _morecircleMedium = ImageVector.Builder(
            name = "MoreCircle.Medium",
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
                        PathNode.MoveTo(206.2f, 621.9f),
                        PathNode.QuadTo(206.2f, 735.6f, 262.1f, 831.3f),
                        PathNode.QuadTo(318.0f, 926.9f, 413.6f, 982.8f),
                        PathNode.QuadTo(509.2f, 1038.7f, 623.0f, 1038.7f),
                        PathNode.QuadTo(736.2f, 1038.7f, 831.7f, 982.9f),
                        PathNode.QuadTo(927.1f, 927.0f, 983.4f, 831.4f),
                        PathNode.QuadTo(1039.7f, 735.8f, 1039.7f, 622.1f),
                        PathNode.QuadTo(1039.7f, 508.3f, 983.4f, 412.7f),
                        PathNode.QuadTo(927.1f, 317.0f, 831.7f, 261.1f),
                        PathNode.QuadTo(736.2f, 205.2f, 623.3f, 205.2f),
                        PathNode.QuadTo(509.3f, 205.2f, 413.7f, 261.1f),
                        PathNode.QuadTo(318.0f, 317.0f, 262.1f, 412.6f),
                        PathNode.QuadTo(206.2f, 508.2f, 206.2f, 621.9f),
                        PathNode.Close,
                        PathNode.MoveTo(475.2f, 622.0f),
                        PathNode.QuadTo(475.2f, 648.5f, 456.6f, 666.9f),
                        PathNode.QuadTo(437.9f, 685.2f, 412.7f, 685.2f),
                        PathNode.QuadTo(387.5f, 685.2f, 368.6f, 666.6f),
                        PathNode.QuadTo(349.7f, 647.9f, 349.7f, 621.9f),
                        PathNode.QuadTo(349.7f, 597.0f, 368.1f, 578.4f),
                        PathNode.QuadTo(386.5f, 559.7f, 413.0f, 559.7f),
                        PathNode.QuadTo(438.9f, 559.7f, 457.1f, 578.2f),
                        PathNode.QuadTo(475.2f, 596.6f, 475.2f, 622.0f),
                        PathNode.Close,
                        PathNode.MoveTo(685.2f, 622.0f),
                        PathNode.QuadTo(685.2f, 648.5f, 666.6f, 666.9f),
                        PathNode.QuadTo(647.9f, 685.2f, 622.7f, 685.2f),
                        PathNode.QuadTo(597.5f, 685.2f, 578.6f, 666.6f),
                        PathNode.QuadTo(559.7f, 647.9f, 559.7f, 621.9f),
                        PathNode.QuadTo(559.7f, 597.0f, 578.1f, 578.4f),
                        PathNode.QuadTo(596.5f, 559.7f, 623.0f, 559.7f),
                        PathNode.QuadTo(648.9f, 559.7f, 667.1f, 578.2f),
                        PathNode.QuadTo(685.2f, 596.6f, 685.2f, 622.0f),
                        PathNode.Close,
                        PathNode.MoveTo(895.2f, 622.0f),
                        PathNode.QuadTo(895.2f, 648.5f, 876.6f, 666.9f),
                        PathNode.QuadTo(857.9f, 685.2f, 832.7f, 685.2f),
                        PathNode.QuadTo(806.4f, 685.2f, 788.1f, 666.6f),
                        PathNode.QuadTo(769.7f, 647.9f, 769.7f, 622.0f),
                        PathNode.QuadTo(769.7f, 596.0f, 788.1f, 577.9f),
                        PathNode.QuadTo(806.5f, 559.7f, 833.0f, 559.7f),
                        PathNode.QuadTo(858.9f, 559.7f, 877.1f, 578.2f),
                        PathNode.QuadTo(895.2f, 596.6f, 895.2f, 622.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _morecircleMedium!!
    }

private var _morecircleMedium: ImageVector? = null

val MiuixIcons.Demibold.MoreCircle: ImageVector
    get() {
        if (_morecircleDemibold != null) return _morecircleDemibold!!
        _morecircleDemibold = ImageVector.Builder(
            name = "MoreCircle.Demibold",
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
                        PathNode.MoveTo(218.9f, 628.8f),
                        PathNode.QuadTo(218.9f, 740.9f, 274.0f, 835.2f),
                        PathNode.QuadTo(329.1f, 929.6f, 423.4f, 984.7f),
                        PathNode.QuadTo(517.7f, 1039.9f, 629.9f, 1039.9f),
                        PathNode.QuadTo(741.9f, 1039.9f, 835.9f, 984.8f),
                        PathNode.QuadTo(930.0f, 929.7f, 985.4f, 835.4f),
                        PathNode.QuadTo(1040.9f, 741.2f, 1040.9f, 629.0f),
                        PathNode.QuadTo(1040.9f, 516.9f, 985.4f, 422.6f),
                        PathNode.QuadTo(930.0f, 328.2f, 835.9f, 273.1f),
                        PathNode.QuadTo(741.9f, 217.9f, 630.4f, 217.9f),
                        PathNode.QuadTo(517.9f, 217.9f, 423.6f, 273.0f),
                        PathNode.QuadTo(329.2f, 328.1f, 274.1f, 422.4f),
                        PathNode.QuadTo(218.9f, 516.6f, 218.9f, 628.8f),
                        PathNode.Close,
                        PathNode.MoveTo(487.9f, 628.9f),
                        PathNode.QuadTo(487.9f, 657.9f, 467.4f, 677.9f),
                        PathNode.QuadTo(446.9f, 697.9f, 419.5f, 697.9f),
                        PathNode.QuadTo(392.0f, 697.9f, 371.5f, 677.4f),
                        PathNode.QuadTo(350.9f, 656.9f, 350.9f, 628.8f),
                        PathNode.QuadTo(350.9f, 601.9f, 371.0f, 581.4f),
                        PathNode.QuadTo(391.0f, 560.9f, 419.9f, 560.9f),
                        PathNode.QuadTo(447.9f, 560.9f, 467.9f, 581.1f),
                        PathNode.QuadTo(487.9f, 601.2f, 487.9f, 628.9f),
                        PathNode.Close,
                        PathNode.MoveTo(697.9f, 628.9f),
                        PathNode.QuadTo(697.9f, 657.9f, 677.4f, 677.9f),
                        PathNode.QuadTo(656.9f, 697.9f, 629.5f, 697.9f),
                        PathNode.QuadTo(602.0f, 697.9f, 581.5f, 677.4f),
                        PathNode.QuadTo(560.9f, 656.9f, 560.9f, 628.8f),
                        PathNode.QuadTo(560.9f, 601.9f, 581.0f, 581.4f),
                        PathNode.QuadTo(601.0f, 560.9f, 629.9f, 560.9f),
                        PathNode.QuadTo(657.9f, 560.9f, 677.9f, 581.1f),
                        PathNode.QuadTo(697.9f, 601.2f, 697.9f, 628.9f),
                        PathNode.Close,
                        PathNode.MoveTo(907.9f, 628.9f),
                        PathNode.QuadTo(907.9f, 657.9f, 887.4f, 677.9f),
                        PathNode.QuadTo(866.9f, 697.9f, 839.5f, 697.9f),
                        PathNode.QuadTo(810.8f, 697.9f, 790.8f, 677.4f),
                        PathNode.QuadTo(770.9f, 656.9f, 770.9f, 628.9f),
                        PathNode.QuadTo(770.9f, 600.9f, 791.0f, 580.9f),
                        PathNode.QuadTo(811.0f, 560.9f, 839.9f, 560.9f),
                        PathNode.QuadTo(867.9f, 560.9f, 887.9f, 581.1f),
                        PathNode.QuadTo(907.9f, 601.2f, 907.9f, 628.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _morecircleDemibold!!
    }

private var _morecircleDemibold: ImageVector? = null
