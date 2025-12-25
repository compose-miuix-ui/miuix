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

val MiuixIcons.Settings: ImageVector
    get() = MiuixIcons.Regular.Settings

val MiuixIcons.Light.Settings: ImageVector
    get() {
        if (_settingsLight != null) return _settingsLight!!
        _settingsLight = ImageVector.Builder(
            name = "Settings.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1201.8f,
            viewportHeight = 1201.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -64.10000000000002f, translationY = 977.15f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(686.0f, -122.0f),
                PathNode.QuadTo(695.0f, -121.0f, 706.5f, -115.0f),
                PathNode.QuadTo(718.0f, -109.0f, 733.0f, -100.0f),
                PathNode.LineTo(751.0f, -90.0f),
                PathNode.LineTo(1032.0f, 68.0f),
                PathNode.QuadTo(1056.0f, 81.0f, 1071.5f, 91.0f),
                PathNode.QuadTo(1087.0f, 101.0f, 1094.6666666666667f, 108.93103448275862f),
                PathNode.QuadTo(1110.0f, 124.0f, 1118.0f, 147.0f),
                PathNode.QuadTo(1121.0f, 159.0f, 1122.0f, 177.0f),
                PathNode.QuadTo(1123.0f, 195.0f, 1123.0f, 222.0f),
                PathNode.VerticalTo(531.0f),
                PathNode.QuadTo(1123.0f, 557.0f, 1122.0f, 575.0f),
                PathNode.QuadTo(1121.0f, 593.0f, 1117.3636363636363f, 604.6571428571428f),
                PathNode.QuadTo(1111.0f, 627.0f, 1096.0f, 643.0f),
                PathNode.QuadTo(1088.0f, 651.0f, 1075.5f, 659.0f),
                PathNode.QuadTo(1063.0f, 667.0f, 1044.0f, 677.0f),
                PathNode.LineTo(1032.0f, 684.0f),
                PathNode.LineTo(751.0f, 843.0f),
                PathNode.QuadTo(724.0f, 859.0f, 710.5f, 865.5f),
                PathNode.QuadTo(697.0f, 872.0f, 686.3333333333334f, 874.0f),
                PathNode.QuadTo(665.0f, 880.0f, 643.6666666666666f, 874.0f),
                PathNode.QuadTo(633.0f, 872.0f, 619.5f, 865.5f),
                PathNode.QuadTo(606.0f, 859.0f, 579.0f, 843.0f),
                PathNode.LineTo(298.0f, 684.0f),
                PathNode.QuadTo(289.0f, 679.0f, 279.0f, 673.0f),
                PathNode.QuadTo(264.0f, 665.0f, 252.5f, 657.5f),
                PathNode.QuadTo(241.0f, 650.0f, 234.0f, 643.0f),
                PathNode.QuadTo(218.0f, 624.0f, 213.0f, 605.0f),
                PathNode.QuadTo(209.0f, 593.0f, 208.0f, 575.0f),
                PathNode.QuadTo(207.0f, 557.0f, 207.0f, 531.0f),
                PathNode.VerticalTo(222.0f),
                PathNode.QuadTo(207.0f, 195.0f, 208.0f, 177.0f),
                PathNode.QuadTo(209.0f, 159.0f, 213.0f, 147.0f),
                PathNode.QuadTo(219.0f, 125.0f, 234.92307692307693f, 108.71428571428572f),
                PathNode.QuadTo(242.0f, 101.0f, 255.70212765957447f, 92.62068965517241f),
                PathNode.QuadTo(269.40425531914894f, 84.24137931034483f, 288.0f, 74.0f),
                PathNode.LineTo(298.0f, 68.0f),
                PathNode.LineTo(579.0f, -90.0f),
                PathNode.LineTo(597.0f, -100.0f),
                PathNode.QuadTo(612.0f, -109.0f, 623.5f, -115.0f),
                PathNode.QuadTo(635.0f, -121.0f, 643.7931034482758f, -122.0f),
                PathNode.QuadTo(664.8965517241379f, -127.0f, 686.0f, -122.0f),
                PathNode.Close,
                PathNode.MoveTo(267.0f, 194.0f),
                PathNode.VerticalTo(564.0f),
                PathNode.QuadTo(267.0f, 585.0f, 274.0f, 596.5f),
                PathNode.QuadTo(281.0f, 608.0f, 301.0f, 620.0f),
                PathNode.LineTo(632.0f, 806.0f),
                PathNode.QuadTo(651.0f, 816.0f, 664.0f, 816.5f),
                PathNode.QuadTo(677.0f, 817.0f, 695.0f, 807.0f),
                PathNode.LineTo(1024.0f, 623.0f),
                PathNode.QuadTo(1049.0f, 609.0f, 1056.5f, 595.0f),
                PathNode.QuadTo(1064.0f, 581.0f, 1064.0f, 551.3095238095239f),
                PathNode.VerticalTo(194.0f),
                PathNode.QuadTo(1064.0f, 172.0f, 1058.0f, 159.5f),
                PathNode.QuadTo(1052.0f, 147.0f, 1037.0f, 138.0f),
                PathNode.LineTo(702.0f, -51.0f),
                PathNode.QuadTo(679.9772727272727f, -64.0f, 665.4886363636364f, -64.0f),
                PathNode.QuadTo(651.0f, -64.0f, 628.0f, -51.0f),
                PathNode.LineTo(304.0f, 131.0f),
                PathNode.QuadTo(282.0f, 144.0f, 274.5f, 156.5f),
                PathNode.QuadTo(267.0f, 169.0f, 267.0f, 194.0f),
                PathNode.Close,
                PathNode.MoveTo(851.0f, 375.88235294117646f),
                PathNode.QuadTo(851.0f, 427.0f, 825.9133858267717f, 469.3620689655172f),
                PathNode.QuadTo(800.8267716535433f, 511.7241379310345f, 758.5511811023623f, 536.8620689655172f),
                PathNode.QuadTo(716.2755905511812f, 562.0f, 665.1732283464567f, 562.0f),
                PathNode.QuadTo(615.0f, 562.0f, 572.1506849315069f, 536.9133858267717f),
                PathNode.QuadTo(529.3013698630137f, 511.8267716535433f, 504.1506849315069f, 469.5511811023622f),
                PathNode.QuadTo(479.0f, 427.2755905511811f, 479.0f, 376.1732283464567f),
                PathNode.QuadTo(479.0f, 326.0f, 504.09411764705885f, 283.1506849315068f),
                PathNode.QuadTo(529.1882352941177f, 240.3013698630137f, 571.9411764705883f, 215.15068493150685f),
                PathNode.QuadTo(614.6941176470589f, 190.0f, 664.8823529411765f, 190.0f),
                PathNode.QuadTo(716.0f, 190.0f, 758.3620689655172f, 215.09411764705882f),
                PathNode.QuadTo(800.7241379310345f, 240.18823529411765f, 825.8620689655172f, 282.94117647058823f),
                PathNode.QuadTo(851.0f, 325.6941176470588f, 851.0f, 375.88235294117646f),
                PathNode.Close,
                PathNode.MoveTo(539.0f, 376.0880503144654f),
                PathNode.QuadTo(539.0f, 429.0f, 576.1509433962265f, 465.5f),
                PathNode.QuadTo(613.3018867924528f, 502.0f, 665.0880503144654f, 502.0f),
                PathNode.QuadTo(718.0f, 502.0f, 754.5f, 465.38607594936707f),
                PathNode.QuadTo(791.0f, 428.7721518987342f, 791.0f, 375.82278481012656f),
                PathNode.QuadTo(791.0f, 324.0f, 754.3860759493671f, 287.0f),
                PathNode.QuadTo(717.7721518987341f, 250.0f, 664.8227848101266f, 250.0f),
                PathNode.QuadTo(613.0f, 250.0f, 576.0f, 287.1509433962264f),
                PathNode.QuadTo(539.0f, 324.3018867924528f, 539.0f, 376.0880503144654f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _settingsLight!!
    }

private var _settingsLight: ImageVector? = null

val MiuixIcons.Regular.Settings: ImageVector
    get() {
        if (_settingsRegular != null) return _settingsRegular!!
        _settingsRegular = ImageVector.Builder(
            name = "Settings.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1233.0f,
            viewportHeight = 1233.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -48.5f, translationY = 992.75f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(689.0f, -135.0f),
                PathNode.QuadTo(699.0f, -133.0f, 712.0f, -127.0f),
                PathNode.QuadTo(725.0f, -121.0f, 740.0f, -112.0f),
                PathNode.LineTo(758.0f, -102.0f),
                PathNode.LineTo(1039.0f, 56.0f),
                PathNode.QuadTo(1069.0f, 73.0f, 1082.5f, 81.5f),
                PathNode.QuadTo(1096.0f, 90.0f, 1105.0f, 100.0f),
                PathNode.QuadTo(1123.0f, 119.0f, 1130.0f, 143.0f),
                PathNode.QuadTo(1134.0f, 155.0f, 1135.0f, 175.0f),
                PathNode.QuadTo(1136.0f, 195.0f, 1136.0f, 222.0f),
                PathNode.VerticalTo(531.0f),
                PathNode.QuadTo(1136.0f, 558.0f, 1135.0f, 577.5f),
                PathNode.QuadTo(1134.0f, 597.0f, 1130.0f, 609.0f),
                PathNode.QuadTo(1123.0f, 632.0f, 1105.0f, 653.0f),
                PathNode.QuadTo(1097.0f, 662.0f, 1083.5f, 670.5f),
                PathNode.QuadTo(1070.0f, 679.0f, 1051.0f, 689.0f),
                PathNode.LineTo(1039.0f, 696.0f),
                PathNode.LineTo(758.0f, 855.0f),
                PathNode.QuadTo(736.0f, 868.0f, 718.5f, 876.5f),
                PathNode.QuadTo(701.0f, 885.0f, 689.0f, 887.0f),
                PathNode.QuadTo(665.0f, 893.0f, 641.0f, 887.0f),
                PathNode.QuadTo(629.0f, 885.0f, 611.5f, 876.5f),
                PathNode.QuadTo(594.0f, 868.0f, 572.0f, 855.0f),
                PathNode.LineTo(291.0f, 696.0f),
                PathNode.QuadTo(284.0f, 692.0f, 276.0f, 688.0f),
                PathNode.QuadTo(259.0f, 678.0f, 245.5f, 669.5f),
                PathNode.QuadTo(232.0f, 661.0f, 225.0f, 653.0f),
                PathNode.QuadTo(207.0f, 632.0f, 200.0f, 609.0f),
                PathNode.QuadTo(196.0f, 597.0f, 195.0f, 577.5f),
                PathNode.QuadTo(194.0f, 558.0f, 194.0f, 531.0f),
                PathNode.VerticalTo(222.0f),
                PathNode.QuadTo(194.0f, 195.0f, 195.0f, 175.0f),
                PathNode.QuadTo(196.0f, 155.0f, 200.0f, 143.0f),
                PathNode.QuadTo(207.0f, 119.0f, 225.0f, 100.0f),
                PathNode.QuadTo(233.0f, 91.0f, 247.0f, 82.0f),
                PathNode.QuadTo(261.0f, 73.0f, 280.0f, 62.0f),
                PathNode.LineTo(291.0f, 56.0f),
                PathNode.LineTo(572.0f, -102.0f),
                PathNode.LineTo(590.0f, -112.0f),
                PathNode.QuadTo(605.0f, -121.0f, 618.0f, -127.0f),
                PathNode.QuadTo(631.0f, -133.0f, 641.0f, -135.0f),
                PathNode.QuadTo(665.0f, -140.0f, 689.0f, -135.0f),
                PathNode.Close,
                PathNode.MoveTo(280.0f, 195.0f),
                PathNode.VerticalTo(564.0f),
                PathNode.QuadTo(280.0f, 578.0f, 286.5f, 588.5f),
                PathNode.QuadTo(293.0f, 599.0f, 308.0f, 608.0f),
                PathNode.LineTo(633.0f, 791.0f),
                PathNode.QuadTo(652.0f, 802.0f, 663.5f, 802.5f),
                PathNode.QuadTo(675.0f, 803.0f, 692.0f, 794.0f),
                PathNode.LineTo(1012.0f, 614.0f),
                PathNode.QuadTo(1037.0f, 600.0f, 1044.0f, 588.0f),
                PathNode.QuadTo(1051.0f, 576.0f, 1051.0f, 547.0f),
                PathNode.VerticalTo(198.0f),
                PathNode.QuadTo(1051.0f, 179.0f, 1046.0f, 167.5f),
                PathNode.QuadTo(1041.0f, 156.0f, 1027.0f, 148.0f),
                PathNode.LineTo(697.0f, -39.0f),
                PathNode.QuadTo(678.0f, -50.0f, 665.5f, -50.0f),
                PathNode.QuadTo(653.0f, -50.0f, 633.0f, -39.0f),
                PathNode.LineTo(311.0f, 143.0f),
                PathNode.QuadTo(293.0f, 153.0f, 286.5f, 163.5f),
                PathNode.QuadTo(280.0f, 174.0f, 280.0f, 195.0f),
                PathNode.Close,
                PathNode.MoveTo(865.0f, 376.0f),
                PathNode.QuadTo(865.0f, 431.0f, 838.0f, 476.5f),
                PathNode.QuadTo(811.0f, 522.0f, 765.5f, 549.0f),
                PathNode.QuadTo(720.0f, 576.0f, 665.0f, 576.0f),
                PathNode.QuadTo(611.0f, 576.0f, 565.0f, 549.0f),
                PathNode.QuadTo(519.0f, 522.0f, 492.0f, 476.5f),
                PathNode.QuadTo(465.0f, 431.0f, 465.0f, 376.0f),
                PathNode.QuadTo(465.0f, 322.0f, 492.0f, 276.0f),
                PathNode.QuadTo(519.0f, 230.0f, 565.0f, 203.0f),
                PathNode.QuadTo(611.0f, 176.0f, 665.0f, 176.0f),
                PathNode.QuadTo(720.0f, 176.0f, 765.5f, 203.0f),
                PathNode.QuadTo(811.0f, 230.0f, 838.0f, 276.0f),
                PathNode.QuadTo(865.0f, 322.0f, 865.0f, 376.0f),
                PathNode.Close,
                PathNode.MoveTo(553.0f, 376.0f),
                PathNode.QuadTo(553.0f, 423.0f, 586.0f, 455.5f),
                PathNode.QuadTo(619.0f, 488.0f, 665.0f, 488.0f),
                PathNode.QuadTo(712.0f, 488.0f, 744.5f, 455.5f),
                PathNode.QuadTo(777.0f, 423.0f, 777.0f, 376.0f),
                PathNode.QuadTo(777.0f, 330.0f, 744.5f, 297.0f),
                PathNode.QuadTo(712.0f, 264.0f, 665.0f, 264.0f),
                PathNode.QuadTo(619.0f, 264.0f, 586.0f, 297.0f),
                PathNode.QuadTo(553.0f, 330.0f, 553.0f, 376.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _settingsRegular!!
    }

private var _settingsRegular: ImageVector? = null

val MiuixIcons.Heavy.Settings: ImageVector
    get() {
        if (_settingsHeavy != null) return _settingsHeavy!!
        _settingsHeavy = ImageVector.Builder(
            name = "Settings.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1266.0f,
            viewportHeight = 1266.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -32.0f, translationY = 1009.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(692.0f, -148.0f),
                PathNode.QuadTo(703.0f, -146.0f, 717.0f, -139.5f),
                PathNode.QuadTo(731.0f, -133.0f, 747.0f, -124.0f),
                PathNode.LineTo(765.0f, -114.0f),
                PathNode.LineTo(1046.0f, 44.0f),
                PathNode.QuadTo(1077.0f, 61.0f, 1091.0f, 70.5f),
                PathNode.QuadTo(1105.0f, 80.0f, 1115.0f, 91.0f),
                PathNode.QuadTo(1134.0f, 111.0f, 1143.0f, 139.0f),
                PathNode.QuadTo(1148.0f, 153.0f, 1149.0f, 169.5f),
                PathNode.QuadTo(1150.0f, 186.0f, 1150.0f, 222.0f),
                PathNode.VerticalTo(531.0f),
                PathNode.QuadTo(1150.0f, 567.0f, 1149.0f, 583.0f),
                PathNode.QuadTo(1148.0f, 599.0f, 1143.0f, 613.0f),
                PathNode.QuadTo(1134.0f, 640.0f, 1115.0f, 662.0f),
                PathNode.QuadTo(1107.0f, 672.0f, 1094.0f, 680.0f),
                PathNode.QuadTo(1081.0f, 688.0f, 1058.0f, 701.0f),
                PathNode.LineTo(1046.0f, 708.0f),
                PathNode.LineTo(765.0f, 867.0f),
                PathNode.QuadTo(734.0f, 885.0f, 720.0f, 891.5f),
                PathNode.QuadTo(706.0f, 898.0f, 692.0f, 900.0f),
                PathNode.QuadTo(665.0f, 908.0f, 638.0f, 900.0f),
                PathNode.QuadTo(624.0f, 898.0f, 610.0f, 891.5f),
                PathNode.QuadTo(596.0f, 885.0f, 565.0f, 867.0f),
                PathNode.LineTo(284.0f, 708.0f),
                PathNode.QuadTo(275.0f, 703.0f, 265.0f, 697.0f),
                PathNode.QuadTo(249.0f, 688.0f, 235.5f, 679.5f),
                PathNode.QuadTo(222.0f, 671.0f, 215.0f, 662.0f),
                PathNode.QuadTo(196.0f, 640.0f, 187.0f, 613.0f),
                PathNode.QuadTo(182.0f, 599.0f, 181.0f, 583.0f),
                PathNode.QuadTo(180.0f, 567.0f, 180.0f, 531.0f),
                PathNode.VerticalTo(222.0f),
                PathNode.QuadTo(180.0f, 186.0f, 181.0f, 169.5f),
                PathNode.QuadTo(182.0f, 153.0f, 187.0f, 139.0f),
                PathNode.QuadTo(196.0f, 111.0f, 215.0f, 91.0f),
                PathNode.QuadTo(224.0f, 81.0f, 237.0f, 72.0f),
                PathNode.QuadTo(250.0f, 63.0f, 273.0f, 50.0f),
                PathNode.LineTo(284.0f, 44.0f),
                PathNode.LineTo(565.0f, -114.0f),
                PathNode.LineTo(583.0f, -124.0f),
                PathNode.QuadTo(599.0f, -133.0f, 613.0f, -139.5f),
                PathNode.QuadTo(627.0f, -146.0f, 638.0f, -148.0f),
                PathNode.QuadTo(665.0f, -154.0f, 692.0f, -148.0f),
                PathNode.Close,
                PathNode.MoveTo(294.0f, 197.0f),
                PathNode.VerticalTo(561.0f),
                PathNode.QuadTo(294.0f, 574.0f, 298.5f, 582.0f),
                PathNode.QuadTo(303.0f, 590.0f, 315.0f, 596.0f),
                PathNode.LineTo(640.0f, 779.0f),
                PathNode.QuadTo(656.0f, 788.0f, 664.5f, 788.5f),
                PathNode.QuadTo(673.0f, 789.0f, 687.0f, 781.0f),
                PathNode.LineTo(1005.0f, 602.0f),
                PathNode.QuadTo(1027.0f, 590.0f, 1032.0f, 581.0f),
                PathNode.QuadTo(1037.0f, 572.0f, 1037.0f, 547.0f),
                PathNode.VerticalTo(198.0f),
                PathNode.QuadTo(1037.0f, 183.0f, 1033.5f, 174.5f),
                PathNode.QuadTo(1030.0f, 166.0f, 1020.0f, 160.0f),
                PathNode.LineTo(691.0f, -27.0f),
                PathNode.QuadTo(674.0f, -36.0f, 664.5f, -36.0f),
                PathNode.QuadTo(655.0f, -36.0f, 639.0f, -26.0f),
                PathNode.LineTo(318.0f, 155.0f),
                PathNode.QuadTo(303.0f, 163.0f, 298.5f, 171.0f),
                PathNode.QuadTo(294.0f, 179.0f, 294.0f, 197.0f),
                PathNode.Close,
                PathNode.MoveTo(879.0f, 376.0f),
                PathNode.QuadTo(879.0f, 434.0f, 850.0f, 483.0f),
                PathNode.QuadTo(821.0f, 532.0f, 772.0f, 561.0f),
                PathNode.QuadTo(723.0f, 590.0f, 665.0f, 590.0f),
                PathNode.QuadTo(607.0f, 590.0f, 558.0f, 561.5f),
                PathNode.QuadTo(509.0f, 533.0f, 480.0f, 483.5f),
                PathNode.QuadTo(451.0f, 434.0f, 451.0f, 376.0f),
                PathNode.QuadTo(451.0f, 318.0f, 480.0f, 269.0f),
                PathNode.QuadTo(509.0f, 220.0f, 558.0f, 191.0f),
                PathNode.QuadTo(607.0f, 162.0f, 665.0f, 162.0f),
                PathNode.QuadTo(723.0f, 162.0f, 772.5f, 191.0f),
                PathNode.QuadTo(822.0f, 220.0f, 850.5f, 269.0f),
                PathNode.QuadTo(879.0f, 318.0f, 879.0f, 376.0f),
                PathNode.Close,
                PathNode.MoveTo(567.0f, 376.0f),
                PathNode.QuadTo(567.0f, 417.0f, 595.5f, 445.5f),
                PathNode.QuadTo(624.0f, 474.0f, 665.0f, 474.0f),
                PathNode.QuadTo(706.0f, 474.0f, 734.5f, 445.5f),
                PathNode.QuadTo(763.0f, 417.0f, 763.0f, 376.0f),
                PathNode.QuadTo(763.0f, 335.0f, 734.5f, 306.5f),
                PathNode.QuadTo(706.0f, 278.0f, 665.0f, 278.0f),
                PathNode.QuadTo(624.0f, 278.0f, 595.5f, 306.5f),
                PathNode.QuadTo(567.0f, 335.0f, 567.0f, 376.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _settingsHeavy!!
    }

private var _settingsHeavy: ImageVector? = null
