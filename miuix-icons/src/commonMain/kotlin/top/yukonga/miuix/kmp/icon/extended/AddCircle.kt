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

val MiuixIcons.AddCircle: ImageVector
    get() = MiuixIcons.Regular.AddCircle

val MiuixIcons.Light.AddCircle: ImageVector
    get() {
        if (_addcircleLight != null) return _addcircleLight!!
        _addcircleLight = ImageVector.Builder(
            name = "AddCircle.Light",
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
                        PathNode.MoveTo(626.5f, 352.5f),
                        PathNode.VerticalTo(841.5f),
                        PathNode.QuadTo(626.5f, 848.5f, 621.6f, 854.0f),
                        PathNode.QuadTo(616.7f, 859.5f, 606.2f, 859.5f),
                        PathNode.HorizontalTo(586.6f),
                        PathNode.QuadTo(577.5f, 859.5f, 572.5f, 854.2f),
                        PathNode.QuadTo(567.5f, 848.8f, 567.5f, 841.5f),
                        PathNode.VerticalTo(352.5f),
                        PathNode.QuadTo(567.5f, 343.4f, 573.1f, 338.4f),
                        PathNode.QuadTo(578.7f, 333.5f, 587.9f, 333.5f),
                        PathNode.HorizontalTo(607.5f),
                        PathNode.QuadTo(615.5f, 333.5f, 621.0f, 338.4f),
                        PathNode.QuadTo(626.5f, 343.4f, 626.5f, 352.5f),
                        PathNode.Close,
                        PathNode.MoveTo(841.5f, 626.5f),
                        PathNode.HorizontalTo(352.5f),
                        PathNode.QuadTo(345.5f, 626.5f, 340.0f, 621.6f),
                        PathNode.QuadTo(334.5f, 616.7f, 334.5f, 606.2f),
                        PathNode.VerticalTo(586.6f),
                        PathNode.QuadTo(334.5f, 577.5f, 339.8f, 572.5f),
                        PathNode.QuadTo(345.2f, 567.5f, 352.5f, 567.5f),
                        PathNode.HorizontalTo(841.5f),
                        PathNode.QuadTo(850.6f, 567.5f, 855.6f, 573.1f),
                        PathNode.QuadTo(860.5f, 578.7f, 860.5f, 587.9f),
                        PathNode.VerticalTo(607.5f),
                        PathNode.QuadTo(860.5f, 615.5f, 855.6f, 621.0f),
                        PathNode.QuadTo(850.6f, 626.5f, 841.5f, 626.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addcircleLight!!
    }

private var _addcircleLight: ImageVector? = null

val MiuixIcons.Normal.AddCircle: ImageVector
    get() {
        if (_addcircleNormal != null) return _addcircleNormal!!
        _addcircleNormal = ImageVector.Builder(
            name = "AddCircle.Normal",
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
                        PathNode.MoveTo(646.2f, 366.0f),
                        PathNode.VerticalTo(848.8f),
                        PathNode.QuadTo(646.2f, 859.2f, 639.8f, 866.1f),
                        PathNode.QuadTo(633.5f, 873.0f, 619.9f, 873.0f),
                        PathNode.HorizontalTo(594.5f),
                        PathNode.QuadTo(582.7f, 873.0f, 576.0f, 865.8f),
                        PathNode.QuadTo(569.3f, 858.6f, 569.3f, 848.8f),
                        PathNode.VerticalTo(366.0f),
                        PathNode.QuadTo(569.3f, 354.2f, 576.5f, 347.8f),
                        PathNode.QuadTo(583.8f, 341.5f, 595.6f, 341.5f),
                        PathNode.HorizontalTo(621.0f),
                        PathNode.QuadTo(632.4f, 341.5f, 639.3f, 347.8f),
                        PathNode.QuadTo(646.2f, 354.2f, 646.2f, 366.0f),
                        PathNode.Close,
                        PathNode.MoveTo(849.5f, 646.2f),
                        PathNode.HorizontalTo(366.7f),
                        PathNode.QuadTo(356.2f, 646.2f, 349.4f, 639.8f),
                        PathNode.QuadTo(342.5f, 633.5f, 342.5f, 619.9f),
                        PathNode.VerticalTo(594.5f),
                        PathNode.QuadTo(342.5f, 582.7f, 349.6f, 576.0f),
                        PathNode.QuadTo(356.8f, 569.3f, 366.7f, 569.3f),
                        PathNode.HorizontalTo(849.5f),
                        PathNode.QuadTo(861.3f, 569.3f, 867.6f, 576.5f),
                        PathNode.QuadTo(874.0f, 583.8f, 874.0f, 595.6f),
                        PathNode.VerticalTo(621.0f),
                        PathNode.QuadTo(874.0f, 632.4f, 867.6f, 639.3f),
                        PathNode.QuadTo(861.3f, 646.2f, 849.5f, 646.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addcircleNormal!!
    }

private var _addcircleNormal: ImageVector? = null

val MiuixIcons.Regular.AddCircle: ImageVector
    get() {
        if (_addcircleRegular != null) return _addcircleRegular!!
        _addcircleRegular = ImageVector.Builder(
            name = "AddCircle.Regular",
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
                        PathNode.MoveTo(655.1f, 372.1f),
                        PathNode.VerticalTo(852.1f),
                        PathNode.QuadTo(655.1f, 864.1f, 648.1f, 871.6f),
                        PathNode.QuadTo(641.1f, 879.1f, 626.1f, 879.1f),
                        PathNode.HorizontalTo(598.1f),
                        PathNode.QuadTo(585.1f, 879.1f, 577.6f, 871.1f),
                        PathNode.QuadTo(570.1f, 863.1f, 570.1f, 852.1f),
                        PathNode.VerticalTo(372.1f),
                        PathNode.QuadTo(570.1f, 359.1f, 578.1f, 352.1f),
                        PathNode.QuadTo(586.1f, 345.1f, 599.1f, 345.1f),
                        PathNode.HorizontalTo(627.1f),
                        PathNode.QuadTo(640.1f, 345.1f, 647.6f, 352.1f),
                        PathNode.QuadTo(655.1f, 359.1f, 655.1f, 372.1f),
                        PathNode.Close,
                        PathNode.MoveTo(853.1f, 655.1f),
                        PathNode.HorizontalTo(373.1f),
                        PathNode.QuadTo(361.1f, 655.1f, 353.6f, 648.1f),
                        PathNode.QuadTo(346.1f, 641.1f, 346.1f, 626.1f),
                        PathNode.VerticalTo(598.1f),
                        PathNode.QuadTo(346.1f, 585.1f, 354.1f, 577.6f),
                        PathNode.QuadTo(362.1f, 570.1f, 373.1f, 570.1f),
                        PathNode.HorizontalTo(853.1f),
                        PathNode.QuadTo(866.1f, 570.1f, 873.1f, 578.1f),
                        PathNode.QuadTo(880.1f, 586.1f, 880.1f, 599.1f),
                        PathNode.VerticalTo(627.1f),
                        PathNode.QuadTo(880.1f, 640.1f, 873.1f, 647.6f),
                        PathNode.QuadTo(866.1f, 655.1f, 853.1f, 655.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addcircleRegular!!
    }

private var _addcircleRegular: ImageVector? = null

val MiuixIcons.Medium.AddCircle: ImageVector
    get() {
        if (_addcircleMedium != null) return _addcircleMedium!!
        _addcircleMedium = ImageVector.Builder(
            name = "AddCircle.Medium",
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
                        PathNode.MoveTo(673.2f, 387.3f),
                        PathNode.VerticalTo(856.7f),
                        PathNode.QuadTo(673.2f, 872.3f, 663.9f, 882.1f),
                        PathNode.QuadTo(654.5f, 891.9f, 636.0f, 891.9f),
                        PathNode.HorizontalTo(608.0f),
                        PathNode.QuadTo(591.2f, 891.9f, 581.5f, 881.5f),
                        PathNode.QuadTo(571.7f, 871.0f, 571.7f, 856.7f),
                        PathNode.VerticalTo(387.3f),
                        PathNode.QuadTo(571.7f, 371.3f, 582.4f, 361.7f),
                        PathNode.QuadTo(593.0f, 352.0f, 609.0f, 352.0f),
                        PathNode.HorizontalTo(637.0f),
                        PathNode.QuadTo(652.9f, 352.0f, 663.1f, 361.7f),
                        PathNode.QuadTo(673.2f, 371.3f, 673.2f, 387.3f),
                        PathNode.Close,
                        PathNode.MoveTo(857.7f, 673.2f),
                        PathNode.HorizontalTo(388.3f),
                        PathNode.QuadTo(372.6f, 673.2f, 362.8f, 663.9f),
                        PathNode.QuadTo(353.0f, 654.5f, 353.0f, 636.0f),
                        PathNode.VerticalTo(608.0f),
                        PathNode.QuadTo(353.0f, 591.2f, 363.5f, 581.5f),
                        PathNode.QuadTo(373.9f, 571.7f, 388.3f, 571.7f),
                        PathNode.HorizontalTo(857.7f),
                        PathNode.QuadTo(873.6f, 571.7f, 883.3f, 582.4f),
                        PathNode.QuadTo(892.9f, 593.0f, 892.9f, 609.0f),
                        PathNode.VerticalTo(637.0f),
                        PathNode.QuadTo(892.9f, 652.9f, 883.3f, 663.1f),
                        PathNode.QuadTo(873.6f, 673.2f, 857.7f, 673.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addcircleMedium!!
    }

private var _addcircleMedium: ImageVector? = null

val MiuixIcons.Demibold.AddCircle: ImageVector
    get() {
        if (_addcircleDemibold != null) return _addcircleDemibold!!
        _addcircleDemibold = ImageVector.Builder(
            name = "AddCircle.Demibold",
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
                        PathNode.MoveTo(685.9f, 397.9f),
                        PathNode.VerticalTo(859.9f),
                        PathNode.QuadTo(685.9f, 878.1f, 674.9f, 889.5f),
                        PathNode.QuadTo(663.9f, 900.9f, 642.9f, 900.9f),
                        PathNode.HorizontalTo(614.9f),
                        PathNode.QuadTo(595.4f, 900.9f, 584.1f, 888.8f),
                        PathNode.QuadTo(572.9f, 876.6f, 572.9f, 859.9f),
                        PathNode.VerticalTo(397.9f),
                        PathNode.QuadTo(572.9f, 379.9f, 585.4f, 368.4f),
                        PathNode.QuadTo(597.9f, 356.9f, 615.9f, 356.9f),
                        PathNode.HorizontalTo(643.9f),
                        PathNode.QuadTo(661.9f, 356.9f, 673.9f, 368.4f),
                        PathNode.QuadTo(685.9f, 379.9f, 685.9f, 397.9f),
                        PathNode.Close,
                        PathNode.MoveTo(860.9f, 685.9f),
                        PathNode.HorizontalTo(398.9f),
                        PathNode.QuadTo(380.7f, 685.9f, 369.3f, 674.9f),
                        PathNode.QuadTo(357.9f, 663.9f, 357.9f, 642.9f),
                        PathNode.VerticalTo(614.9f),
                        PathNode.QuadTo(357.9f, 595.4f, 370.0f, 584.1f),
                        PathNode.QuadTo(382.2f, 572.9f, 398.9f, 572.9f),
                        PathNode.HorizontalTo(860.9f),
                        PathNode.QuadTo(878.9f, 572.9f, 890.4f, 585.4f),
                        PathNode.QuadTo(901.9f, 597.9f, 901.9f, 615.9f),
                        PathNode.VerticalTo(643.9f),
                        PathNode.QuadTo(901.9f, 661.9f, 890.4f, 673.9f),
                        PathNode.QuadTo(878.9f, 685.9f, 860.9f, 685.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _addcircleDemibold!!
    }

private var _addcircleDemibold: ImageVector? = null
