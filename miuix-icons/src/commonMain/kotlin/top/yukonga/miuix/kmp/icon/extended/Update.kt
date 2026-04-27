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

val MiuixIcons.Update: ImageVector
    get() = MiuixIcons.Regular.Update

val MiuixIcons.Light.Update: ImageVector
    get() {
        if (_updateLight != null) return _updateLight!!
        _updateLight = ImageVector.Builder(
            name = "Update.Light",
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
                        PathNode.MoveTo(627.5f, 355.5f),
                        PathNode.VerticalTo(751.5f),
                        PathNode.LineTo(778.5f, 600.5f),
                        PathNode.QuadTo(783.5f, 594.5f, 791.0f, 594.0f),
                        PathNode.QuadTo(798.5f, 593.5f, 804.5f, 600.5f),
                        PathNode.LineTo(819.6f, 615.6f),
                        PathNode.QuadTo(826.5f, 622.5f, 825.5f, 629.6f),
                        PathNode.QuadTo(824.4f, 636.8f, 819.5f, 641.5f),
                        PathNode.LineTo(623.5f, 838.5f),
                        PathNode.QuadTo(612.5f, 850.5f, 598.0f, 850.5f),
                        PathNode.QuadTo(583.5f, 850.5f, 572.5f, 839.5f),
                        PathNode.LineTo(375.5f, 641.5f),
                        PathNode.QuadTo(370.2f, 636.2f, 369.8f, 629.8f),
                        PathNode.QuadTo(369.5f, 623.5f, 375.7f, 616.5f),
                        PathNode.LineTo(389.6f, 601.2f),
                        PathNode.QuadTo(396.5f, 593.5f, 404.0f, 594.0f),
                        PathNode.QuadTo(411.5f, 594.5f, 416.5f, 600.5f),
                        PathNode.LineTo(567.5f, 751.5f),
                        PathNode.VerticalTo(355.5f),
                        PathNode.QuadTo(567.5f, 347.5f, 572.6f, 342.0f),
                        PathNode.QuadTo(577.7f, 336.5f, 586.5f, 336.5f),
                        PathNode.HorizontalTo(607.5f),
                        PathNode.QuadTo(617.5f, 336.5f, 622.5f, 342.0f),
                        PathNode.QuadTo(627.5f, 347.5f, 627.5f, 355.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _updateLight!!
    }

private var _updateLight: ImageVector? = null

val MiuixIcons.Normal.Update: ImageVector
    get() {
        if (_updateNormal != null) return _updateNormal!!
        _updateNormal = ImageVector.Builder(
            name = "Update.Normal",
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
                        PathNode.MoveTo(647.2f, 369.0f),
                        PathNode.VerticalTo(740.2f),
                        PathNode.LineTo(781.0f, 606.4f),
                        PathNode.QuadTo(788.0f, 599.0f, 797.6f, 597.9f),
                        PathNode.QuadTo(807.2f, 596.7f, 815.9f, 605.7f),
                        PathNode.LineTo(835.8f, 625.6f),
                        PathNode.QuadTo(844.8f, 634.6f, 843.4f, 644.0f),
                        PathNode.QuadTo(842.1f, 653.5f, 835.7f, 659.8f),
                        PathNode.LineTo(640.4f, 856.1f),
                        PathNode.QuadTo(626.7f, 870.2f, 608.7f, 870.5f),
                        PathNode.QuadTo(590.8f, 870.9f, 577.7f, 857.8f),
                        PathNode.LineTo(380.7f, 659.8f),
                        PathNode.QuadTo(373.6f, 652.6f, 373.1f, 644.1f),
                        PathNode.QuadTo(372.7f, 635.6f, 380.8f, 626.5f),
                        PathNode.LineTo(398.9f, 606.6f),
                        PathNode.QuadTo(407.9f, 596.7f, 417.8f, 597.5f),
                        PathNode.QuadTo(427.7f, 598.4f, 435.5f, 606.4f),
                        PathNode.LineTo(569.3f, 740.2f),
                        PathNode.VerticalTo(369.0f),
                        PathNode.QuadTo(569.3f, 356.9f, 576.0f, 349.6f),
                        PathNode.QuadTo(582.8f, 342.4f, 594.5f, 342.4f),
                        PathNode.HorizontalTo(620.3f),
                        PathNode.QuadTo(633.7f, 342.4f, 640.4f, 349.6f),
                        PathNode.QuadTo(647.2f, 356.9f, 647.2f, 369.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _updateNormal!!
    }

private var _updateNormal: ImageVector? = null

val MiuixIcons.Regular.Update: ImageVector
    get() {
        if (_updateRegular != null) return _updateRegular!!
        _updateRegular = ImageVector.Builder(
            name = "Update.Regular",
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
                        PathNode.MoveTo(656.1f, 375.1f),
                        PathNode.VerticalTo(735.1f),
                        PathNode.LineTo(782.1f, 609.1f),
                        PathNode.QuadTo(790.1f, 601.1f, 800.6f, 599.6f),
                        PathNode.QuadTo(811.1f, 598.1f, 821.1f, 608.1f),
                        PathNode.LineTo(843.1f, 630.1f),
                        PathNode.QuadTo(853.1f, 640.1f, 851.6f, 650.6f),
                        PathNode.QuadTo(850.1f, 661.1f, 843.1f, 668.1f),
                        PathNode.LineTo(648.1f, 864.1f),
                        PathNode.QuadTo(633.1f, 879.1f, 613.6f, 879.6f),
                        PathNode.QuadTo(594.1f, 880.1f, 580.1f, 866.1f),
                        PathNode.LineTo(383.1f, 668.1f),
                        PathNode.QuadTo(375.1f, 660.1f, 374.6f, 650.6f),
                        PathNode.QuadTo(374.1f, 641.1f, 383.1f, 631.1f),
                        PathNode.LineTo(403.1f, 609.1f),
                        PathNode.QuadTo(413.1f, 598.1f, 424.1f, 599.1f),
                        PathNode.QuadTo(435.1f, 600.1f, 444.1f, 609.1f),
                        PathNode.LineTo(570.1f, 735.1f),
                        PathNode.VerticalTo(375.1f),
                        PathNode.QuadTo(570.1f, 361.1f, 577.6f, 353.1f),
                        PathNode.QuadTo(585.1f, 345.1f, 598.1f, 345.1f),
                        PathNode.HorizontalTo(626.1f),
                        PathNode.QuadTo(641.1f, 345.1f, 648.6f, 353.1f),
                        PathNode.QuadTo(656.1f, 361.1f, 656.1f, 375.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _updateRegular!!
    }

private var _updateRegular: ImageVector? = null

val MiuixIcons.Medium.Update: ImageVector
    get() {
        if (_updateMedium != null) return _updateMedium!!
        _updateMedium = ImageVector.Builder(
            name = "Update.Medium",
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
                        PathNode.QuadTo(736.6f, 1038.7f, 832.4f, 982.9f),
                        PathNode.QuadTo(928.1f, 927.1f, 983.9f, 831.4f),
                        PathNode.QuadTo(1039.7f, 735.6f, 1039.7f, 622.0f),
                        PathNode.QuadTo(1039.7f, 508.7f, 983.9f, 413.3f),
                        PathNode.QuadTo(928.1f, 317.9f, 832.4f, 261.5f),
                        PathNode.QuadTo(736.6f, 205.2f, 623.0f, 205.2f),
                        PathNode.QuadTo(509.3f, 205.2f, 413.6f, 261.5f),
                        PathNode.QuadTo(317.9f, 317.9f, 262.0f, 413.3f),
                        PathNode.QuadTo(206.2f, 508.7f, 206.2f, 622.0f),
                        PathNode.Close,
                        PathNode.MoveTo(674.2f, 389.1f),
                        PathNode.VerticalTo(725.6f),
                        PathNode.LineTo(786.1f, 613.1f),
                        PathNode.QuadTo(796.5f, 602.7f, 810.5f, 601.5f),
                        PathNode.QuadTo(824.5f, 600.3f, 836.9f, 612.1f),
                        PathNode.LineTo(858.9f, 634.1f),
                        PathNode.QuadTo(871.2f, 647.0f, 869.7f, 660.5f),
                        PathNode.QuadTo(868.2f, 673.9f, 858.9f, 683.9f),
                        PathNode.LineTo(663.9f, 879.9f),
                        PathNode.QuadTo(646.5f, 897.2f, 623.2f, 897.4f),
                        PathNode.QuadTo(599.9f, 897.6f, 584.1f, 881.9f),
                        PathNode.LineTo(387.1f, 683.9f),
                        PathNode.QuadTo(376.7f, 673.5f, 376.2f, 660.5f),
                        PathNode.QuadTo(375.7f, 647.5f, 387.1f, 635.7f),
                        PathNode.LineTo(407.1f, 613.7f),
                        PathNode.QuadTo(419.5f, 600.3f, 434.0f, 601.3f),
                        PathNode.QuadTo(448.5f, 602.3f, 459.9f, 613.1f),
                        PathNode.LineTo(571.7f, 725.6f),
                        PathNode.VerticalTo(389.1f),
                        PathNode.QuadTo(571.7f, 371.6f, 581.6f, 361.2f),
                        PathNode.QuadTo(591.5f, 350.9f, 608.0f, 350.9f),
                        PathNode.HorizontalTo(636.0f),
                        PathNode.QuadTo(653.9f, 350.9f, 664.1f, 361.5f),
                        PathNode.QuadTo(674.2f, 372.2f, 674.2f, 389.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _updateMedium!!
    }

private var _updateMedium: ImageVector? = null

val MiuixIcons.Demibold.Update: ImageVector
    get() {
        if (_updateDemibold != null) return _updateDemibold!!
        _updateDemibold = ImageVector.Builder(
            name = "Update.Demibold",
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
                        PathNode.QuadTo(741.9f, 1039.9f, 836.4f, 984.9f),
                        PathNode.QuadTo(930.9f, 929.9f, 985.9f, 835.4f),
                        PathNode.QuadTo(1040.9f, 740.9f, 1040.9f, 628.9f),
                        PathNode.QuadTo(1040.9f, 516.9f, 985.9f, 422.9f),
                        PathNode.QuadTo(930.9f, 328.9f, 836.4f, 273.4f),
                        PathNode.QuadTo(741.9f, 217.9f, 629.9f, 217.9f),
                        PathNode.QuadTo(517.9f, 217.9f, 423.4f, 273.4f),
                        PathNode.QuadTo(328.9f, 328.9f, 273.9f, 422.9f),
                        PathNode.QuadTo(218.9f, 516.9f, 218.9f, 628.9f),
                        PathNode.Close,
                        PathNode.MoveTo(686.9f, 398.9f),
                        PathNode.VerticalTo(718.9f),
                        PathNode.LineTo(788.9f, 615.9f),
                        PathNode.QuadTo(800.9f, 603.9f, 817.4f, 602.9f),
                        PathNode.QuadTo(833.9f, 601.9f, 847.9f, 614.9f),
                        PathNode.LineTo(869.9f, 636.9f),
                        PathNode.QuadTo(883.9f, 651.9f, 882.4f, 667.4f),
                        PathNode.QuadTo(880.9f, 682.9f, 869.9f, 694.9f),
                        PathNode.LineTo(674.9f, 890.9f),
                        PathNode.QuadTo(655.9f, 909.9f, 629.9f, 909.9f),
                        PathNode.QuadTo(603.9f, 909.9f, 586.9f, 892.9f),
                        PathNode.LineTo(389.9f, 694.9f),
                        PathNode.QuadTo(377.9f, 682.9f, 377.4f, 667.4f),
                        PathNode.QuadTo(376.9f, 651.9f, 389.9f, 638.9f),
                        PathNode.LineTo(409.9f, 616.9f),
                        PathNode.QuadTo(423.9f, 601.9f, 440.9f, 602.9f),
                        PathNode.QuadTo(457.9f, 603.9f, 470.9f, 615.9f),
                        PathNode.LineTo(572.9f, 718.9f),
                        PathNode.VerticalTo(398.9f),
                        PathNode.QuadTo(572.9f, 378.9f, 584.4f, 366.9f),
                        PathNode.QuadTo(595.9f, 354.9f, 614.9f, 354.9f),
                        PathNode.HorizontalTo(642.9f),
                        PathNode.QuadTo(662.9f, 354.9f, 674.9f, 367.4f),
                        PathNode.QuadTo(686.9f, 379.9f, 686.9f, 398.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _updateDemibold!!
    }

private var _updateDemibold: ImageVector? = null
