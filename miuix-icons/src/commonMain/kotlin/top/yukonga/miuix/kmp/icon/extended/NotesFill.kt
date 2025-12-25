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

val MiuixIcons.NotesFill: ImageVector
    get() = MiuixIcons.Regular.NotesFill

val MiuixIcons.Light.NotesFill: ImageVector
    get() {
        if (_notesfillLight != null) return _notesfillLight!!
        _notesfillLight = ImageVector.Builder(
            name = "NotesFill.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1206.0f,
            viewportHeight = 1206.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -62.0f, translationY = 981.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1001.0f, -104.0f),
                PathNode.QuadTo(1052.0f, -79.0f, 1078.0f, -27.0f),
                PathNode.QuadTo(1091.0f, -2.0f, 1094.0f, 36.5f),
                PathNode.QuadTo(1097.0f, 75.0f, 1097.0f, 167.0f),
                PathNode.VerticalTo(743.0f),
                PathNode.QuadTo(1097.0f, 789.0f, 1096.0f, 807.0f),
                PathNode.QuadTo(1095.0f, 825.0f, 1089.0f, 837.0f),
                PathNode.QuadTo(1077.0f, 860.0f, 1053.0f, 872.0f),
                PathNode.QuadTo(1042.0f, 878.0f, 1024.0f, 879.5f),
                PathNode.QuadTo(1006.0f, 881.0f, 960.0f, 881.0f),
                PathNode.HorizontalTo(524.0f),
                PathNode.QuadTo(431.0f, 881.0f, 392.5f, 878.0f),
                PathNode.QuadTo(354.0f, 875.0f, 329.0f, 861.0f),
                PathNode.QuadTo(278.0f, 836.0f, 252.0f, 784.0f),
                PathNode.QuadTo(239.0f, 759.0f, 236.0f, 720.5f),
                PathNode.QuadTo(233.0f, 682.0f, 233.0f, 590.0f),
                PathNode.VerticalTo(14.0f),
                PathNode.QuadTo(233.0f, -32.0f, 234.0f, -50.0f),
                PathNode.QuadTo(235.0f, -68.0f, 241.0f, -80.0f),
                PathNode.QuadTo(254.0f, -105.0f, 277.0f, -115.0f),
                PathNode.QuadTo(288.0f, -121.0f, 306.0f, -122.5f),
                PathNode.QuadTo(324.0f, -124.0f, 370.0f, -124.0f),
                PathNode.HorizontalTo(806.0f),
                PathNode.QuadTo(899.0f, -124.0f, 937.5f, -121.0f),
                PathNode.QuadTo(976.0f, -118.0f, 1001.0f, -104.0f),
                PathNode.Close,
                PathNode.MoveTo(421.0f, 373.0f),
                PathNode.VerticalTo(384.0f),
                PathNode.QuadTo(421.0f, 403.0f, 439.0f, 403.0f),
                PathNode.HorizontalTo(892.0f),
                PathNode.QuadTo(909.0f, 403.0f, 909.0f, 384.0f),
                PathNode.VerticalTo(373.0f),
                PathNode.QuadTo(909.0f, 363.0f, 905.0f, 358.0f),
                PathNode.QuadTo(901.0f, 353.0f, 891.0f, 353.0f),
                PathNode.HorizontalTo(439.0f),
                PathNode.QuadTo(430.0f, 353.0f, 425.5f, 358.0f),
                PathNode.QuadTo(421.0f, 363.0f, 421.0f, 373.0f),
                PathNode.Close,
                PathNode.MoveTo(421.0f, 134.0f),
                PathNode.VerticalTo(146.0f),
                PathNode.QuadTo(421.0f, 164.0f, 439.0f, 164.0f),
                PathNode.HorizontalTo(704.0f),
                PathNode.QuadTo(721.0f, 164.0f, 721.0f, 146.0f),
                PathNode.VerticalTo(134.0f),
                PathNode.QuadTo(721.0f, 125.0f, 716.5f, 120.0f),
                PathNode.QuadTo(712.0f, 115.0f, 703.0f, 115.0f),
                PathNode.HorizontalTo(439.0f),
                PathNode.QuadTo(430.0f, 115.0f, 425.5f, 119.5f),
                PathNode.QuadTo(421.0f, 124.0f, 421.0f, 134.0f),
                PathNode.Close,
                PathNode.MoveTo(421.0f, 611.0f),
                PathNode.VerticalTo(623.0f),
                PathNode.QuadTo(421.0f, 641.0f, 439.0f, 641.0f),
                PathNode.HorizontalTo(892.0f),
                PathNode.QuadTo(909.0f, 641.0f, 909.0f, 623.0f),
                PathNode.VerticalTo(611.0f),
                PathNode.QuadTo(909.0f, 602.0f, 904.5f, 597.0f),
                PathNode.QuadTo(900.0f, 592.0f, 891.0f, 592.0f),
                PathNode.HorizontalTo(439.0f),
                PathNode.QuadTo(430.0f, 592.0f, 425.5f, 596.5f),
                PathNode.QuadTo(421.0f, 601.0f, 421.0f, 611.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _notesfillLight!!
    }

private var _notesfillLight: ImageVector? = null

val MiuixIcons.Regular.NotesFill: ImageVector
    get() {
        if (_notesfillRegular != null) return _notesfillRegular!!
        _notesfillRegular = ImageVector.Builder(
            name = "NotesFill.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1232.3999999999999f,
            viewportHeight = 1232.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -48.80000000000007f, translationY = 994.6999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1005.0f, -114.0f),
                PathNode.QuadTo(1060.0f, -86.0f, 1088.0f, -31.0f),
                PathNode.QuadTo(1102.0f, -4.0f, 1105.5f, 36.0f),
                PathNode.QuadTo(1109.0f, 76.0f, 1109.0f, 169.0f),
                PathNode.VerticalTo(740.0f),
                PathNode.QuadTo(1109.0f, 787.0f, 1107.5f, 806.5f),
                PathNode.QuadTo(1106.0f, 826.0f, 1099.0f, 840.0f),
                PathNode.QuadTo(1086.0f, 866.0f, 1057.0f, 882.0f),
                PathNode.QuadTo(1043.0f, 889.0f, 1023.5f, 890.5f),
                PathNode.QuadTo(1004.0f, 892.0f, 957.0f, 892.0f),
                PathNode.HorizontalTo(525.0f),
                PathNode.QuadTo(432.0f, 892.0f, 392.0f, 888.5f),
                PathNode.QuadTo(352.0f, 885.0f, 325.0f, 871.0f),
                PathNode.QuadTo(270.0f, 843.0f, 242.0f, 788.0f),
                PathNode.QuadTo(228.0f, 760.0f, 224.5f, 720.5f),
                PathNode.QuadTo(221.0f, 681.0f, 221.0f, 588.0f),
                PathNode.VerticalTo(17.0f),
                PathNode.QuadTo(221.0f, -30.0f, 222.5f, -49.5f),
                PathNode.QuadTo(224.0f, -69.0f, 231.0f, -83.0f),
                PathNode.QuadTo(245.0f, -111.0f, 273.0f, -125.0f),
                PathNode.QuadTo(287.0f, -132.0f, 307.0f, -133.5f),
                PathNode.QuadTo(327.0f, -135.0f, 373.0f, -135.0f),
                PathNode.HorizontalTo(805.0f),
                PathNode.QuadTo(898.0f, -135.0f, 938.0f, -131.5f),
                PathNode.QuadTo(978.0f, -128.0f, 1005.0f, -114.0f),
                PathNode.Close,
                PathNode.MoveTo(427.0f, 374.0f),
                PathNode.VerticalTo(384.0f),
                PathNode.QuadTo(427.0f, 412.0f, 454.0f, 412.0f),
                PathNode.HorizontalTo(877.0f),
                PathNode.QuadTo(903.0f, 412.0f, 903.0f, 384.0f),
                PathNode.VerticalTo(374.0f),
                PathNode.QuadTo(903.0f, 359.0f, 897.0f, 352.0f),
                PathNode.QuadTo(891.0f, 345.0f, 876.0f, 345.0f),
                PathNode.HorizontalTo(454.0f),
                PathNode.QuadTo(440.0f, 345.0f, 433.5f, 352.0f),
                PathNode.QuadTo(427.0f, 359.0f, 427.0f, 374.0f),
                PathNode.Close,
                PathNode.MoveTo(427.0f, 137.0f),
                PathNode.VerticalTo(147.0f),
                PathNode.QuadTo(427.0f, 175.0f, 454.0f, 175.0f),
                PathNode.HorizontalTo(691.0f),
                PathNode.QuadTo(717.0f, 175.0f, 717.0f, 147.0f),
                PathNode.VerticalTo(137.0f),
                PathNode.QuadTo(717.0f, 122.0f, 711.0f, 115.0f),
                PathNode.QuadTo(705.0f, 108.0f, 690.0f, 108.0f),
                PathNode.HorizontalTo(454.0f),
                PathNode.QuadTo(440.0f, 108.0f, 433.5f, 115.0f),
                PathNode.QuadTo(427.0f, 122.0f, 427.0f, 137.0f),
                PathNode.Close,
                PathNode.MoveTo(427.0f, 610.0f),
                PathNode.VerticalTo(620.0f),
                PathNode.QuadTo(427.0f, 648.0f, 454.0f, 648.0f),
                PathNode.HorizontalTo(877.0f),
                PathNode.QuadTo(903.0f, 648.0f, 903.0f, 620.0f),
                PathNode.VerticalTo(610.0f),
                PathNode.QuadTo(903.0f, 595.0f, 897.0f, 588.0f),
                PathNode.QuadTo(891.0f, 581.0f, 876.0f, 581.0f),
                PathNode.HorizontalTo(454.0f),
                PathNode.QuadTo(440.0f, 581.0f, 433.5f, 588.0f),
                PathNode.QuadTo(427.0f, 595.0f, 427.0f, 610.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _notesfillRegular!!
    }

private var _notesfillRegular: ImageVector? = null

val MiuixIcons.Heavy.NotesFill: ImageVector
    get() {
        if (_notesfillHeavy != null) return _notesfillHeavy!!
        _notesfillHeavy = ImageVector.Builder(
            name = "NotesFill.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1268.3999999999999f,
            viewportHeight = 1268.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -30.800000000000068f, translationY = 1012.6999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1011.0f, -127.0f),
                PathNode.QuadTo(1072.0f, -96.0f, 1101.0f, -36.0f),
                PathNode.QuadTo(1117.0f, -6.0f, 1120.5f, 35.5f),
                PathNode.QuadTo(1124.0f, 77.0f, 1124.0f, 171.0f),
                PathNode.VerticalTo(737.0f),
                PathNode.QuadTo(1124.0f, 784.0f, 1122.0f, 806.0f),
                PathNode.QuadTo(1120.0f, 828.0f, 1112.0f, 845.0f),
                PathNode.QuadTo(1098.0f, 875.0f, 1063.0f, 894.0f),
                PathNode.QuadTo(1046.0f, 903.0f, 1024.0f, 905.0f),
                PathNode.QuadTo(1002.0f, 907.0f, 954.0f, 907.0f),
                PathNode.HorizontalTo(526.0f),
                PathNode.QuadTo(433.0f, 907.0f, 391.0f, 903.5f),
                PathNode.QuadTo(349.0f, 900.0f, 319.0f, 884.0f),
                PathNode.QuadTo(258.0f, 853.0f, 229.0f, 793.0f),
                PathNode.QuadTo(213.0f, 762.0f, 209.5f, 720.5f),
                PathNode.QuadTo(206.0f, 679.0f, 206.0f, 586.0f),
                PathNode.VerticalTo(20.0f),
                PathNode.QuadTo(206.0f, -27.0f, 208.0f, -49.0f),
                PathNode.QuadTo(210.0f, -71.0f, 218.0f, -88.0f),
                PathNode.QuadTo(233.0f, -119.0f, 267.0f, -138.0f),
                PathNode.QuadTo(284.0f, -146.0f, 306.5f, -148.0f),
                PathNode.QuadTo(329.0f, -150.0f, 376.0f, -150.0f),
                PathNode.HorizontalTo(804.0f),
                PathNode.QuadTo(897.0f, -150.0f, 939.0f, -146.5f),
                PathNode.QuadTo(981.0f, -143.0f, 1011.0f, -127.0f),
                PathNode.Close,
                PathNode.MoveTo(430.0f, 370.0f),
                PathNode.VerticalTo(387.0f),
                PathNode.QuadTo(430.0f, 424.0f, 467.0f, 424.0f),
                PathNode.HorizontalTo(859.0f),
                PathNode.QuadTo(895.0f, 424.0f, 895.0f, 387.0f),
                PathNode.VerticalTo(370.0f),
                PathNode.QuadTo(895.0f, 352.0f, 886.0f, 342.5f),
                PathNode.QuadTo(877.0f, 333.0f, 859.0f, 333.0f),
                PathNode.HorizontalTo(467.0f),
                PathNode.QuadTo(449.0f, 333.0f, 439.5f, 342.0f),
                PathNode.QuadTo(430.0f, 351.0f, 430.0f, 370.0f),
                PathNode.Close,
                PathNode.MoveTo(430.0f, 136.0f),
                PathNode.VerticalTo(152.0f),
                PathNode.QuadTo(430.0f, 189.0f, 467.0f, 189.0f),
                PathNode.HorizontalTo(674.0f),
                PathNode.QuadTo(711.0f, 189.0f, 711.0f, 152.0f),
                PathNode.VerticalTo(136.0f),
                PathNode.QuadTo(711.0f, 117.0f, 702.0f, 107.5f),
                PathNode.QuadTo(693.0f, 98.0f, 674.0f, 98.0f),
                PathNode.HorizontalTo(467.0f),
                PathNode.QuadTo(449.0f, 98.0f, 439.5f, 107.5f),
                PathNode.QuadTo(430.0f, 117.0f, 430.0f, 136.0f),
                PathNode.Close,
                PathNode.MoveTo(430.0f, 604.0f),
                PathNode.VerticalTo(621.0f),
                PathNode.QuadTo(430.0f, 658.0f, 467.0f, 658.0f),
                PathNode.HorizontalTo(859.0f),
                PathNode.QuadTo(895.0f, 658.0f, 895.0f, 621.0f),
                PathNode.VerticalTo(604.0f),
                PathNode.QuadTo(895.0f, 586.0f, 886.0f, 576.5f),
                PathNode.QuadTo(877.0f, 567.0f, 859.0f, 567.0f),
                PathNode.HorizontalTo(467.0f),
                PathNode.QuadTo(449.0f, 567.0f, 439.5f, 576.0f),
                PathNode.QuadTo(430.0f, 585.0f, 430.0f, 604.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _notesfillHeavy!!
    }

private var _notesfillHeavy: ImageVector? = null
