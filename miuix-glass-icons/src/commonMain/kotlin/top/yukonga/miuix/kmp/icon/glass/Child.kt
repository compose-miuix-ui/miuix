// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.glass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Glass.Child: ImageVector
    get() = MiuixIcons.Glass.Regular.Child

val MiuixIcons.Glass.Light.Child: ImageVector
    get() {
        if (_childLight != null) return _childLight!!
        _childLight = ImageVector.Builder(
            name = "Child.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(122.1f, 410.2f),
                        PathNode.QuadTo(74.4f, 430.5f, 44.6f, 474.3f),
                        PathNode.QuadTo(14.8f, 518.2f, 14.8f, 571.9f),
                        PathNode.QuadTo(14.8f, 625.7f, 44.6f, 669.5f),
                        PathNode.QuadTo(74.4f, 713.4f, 122.1f, 733.7f),
                        PathNode.QuadTo(154.3f, 825.8f, 221.0f, 897.7f),
                        PathNode.QuadTo(287.8f, 969.5f, 378.6f, 1009.5f),
                        PathNode.QuadTo(469.4f, 1049.6f, 571.5f, 1049.6f),
                        PathNode.QuadTo(673.7f, 1049.6f, 764.4f, 1009.5f),
                        PathNode.QuadTo(855.1f, 969.5f, 921.8f, 897.7f),
                        PathNode.QuadTo(988.5f, 825.8f, 1020.8f, 733.7f),
                        PathNode.QuadTo(1068.4f, 713.4f, 1098.3f, 669.5f),
                        PathNode.QuadTo(1128.1f, 625.7f, 1128.1f, 571.9f),
                        PathNode.QuadTo(1128.1f, 518.2f, 1098.3f, 474.3f),
                        PathNode.QuadTo(1068.4f, 430.5f, 1020.8f, 410.2f),
                        PathNode.QuadTo(988.5f, 318.0f, 921.8f, 245.9f),
                        PathNode.QuadTo(855.1f, 173.8f, 764.3f, 133.5f),
                        PathNode.QuadTo(673.4f, 93.3f, 571.3f, 93.3f),
                        PathNode.QuadTo(469.2f, 93.3f, 378.5f, 133.5f),
                        PathNode.QuadTo(287.8f, 173.8f, 221.0f, 245.9f),
                        PathNode.QuadTo(154.3f, 318.0f, 122.1f, 410.2f),
                        PathNode.Close,
                        PathNode.MoveTo(771.8f, 371.6f),
                        PathNode.QuadTo(779.1f, 379.5f, 779.1f, 390.7f),
                        PathNode.QuadTo(779.1f, 401.9f, 771.8f, 409.4f),
                        PathNode.QuadTo(764.4f, 417.6f, 753.4f, 417.6f),
                        PathNode.QuadTo(742.4f, 417.6f, 734.1f, 409.3f),
                        PathNode.QuadTo(690.3f, 364.7f, 630.9f, 349.1f),
                        PathNode.QuadTo(571.4f, 333.6f, 512.0f, 349.1f),
                        PathNode.QuadTo(452.6f, 364.7f, 408.8f, 409.3f),
                        PathNode.QuadTo(401.4f, 417.6f, 390.4f, 417.6f),
                        PathNode.QuadTo(379.4f, 417.6f, 371.2f, 409.4f),
                        PathNode.QuadTo(363.8f, 401.9f, 363.8f, 390.7f),
                        PathNode.QuadTo(363.8f, 379.5f, 371.1f, 371.6f),
                        PathNode.QuadTo(426.3f, 317.2f, 498.8f, 298.2f),
                        PathNode.QuadTo(571.4f, 279.3f, 644.5f, 298.2f),
                        PathNode.QuadTo(717.6f, 317.2f, 771.8f, 371.6f),
                        PathNode.Close,
                        PathNode.MoveTo(442.1f, 620.8f),
                        PathNode.QuadTo(442.1f, 649.9f, 423.3f, 670.3f),
                        PathNode.QuadTo(404.6f, 690.6f, 378.4f, 690.6f),
                        PathNode.QuadTo(352.3f, 690.6f, 333.5f, 670.3f),
                        PathNode.QuadTo(314.8f, 649.9f, 314.8f, 620.8f),
                        PathNode.QuadTo(314.8f, 592.5f, 333.5f, 572.4f),
                        PathNode.QuadTo(352.3f, 552.3f, 378.4f, 552.3f),
                        PathNode.QuadTo(404.6f, 552.3f, 423.3f, 572.4f),
                        PathNode.QuadTo(442.1f, 592.5f, 442.1f, 620.8f),
                        PathNode.Close,
                        PathNode.MoveTo(828.1f, 620.8f),
                        PathNode.QuadTo(828.1f, 649.9f, 809.3f, 670.3f),
                        PathNode.QuadTo(790.6f, 690.6f, 764.4f, 690.6f),
                        PathNode.QuadTo(738.3f, 690.6f, 719.5f, 670.3f),
                        PathNode.QuadTo(700.8f, 649.9f, 700.8f, 620.8f),
                        PathNode.QuadTo(700.8f, 592.5f, 719.5f, 572.4f),
                        PathNode.QuadTo(738.3f, 552.3f, 764.4f, 552.3f),
                        PathNode.QuadTo(790.6f, 552.3f, 809.3f, 572.4f),
                        PathNode.QuadTo(828.1f, 592.5f, 828.1f, 620.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _childLight!!
    }

private var _childLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Child: ImageVector
    get() {
        if (_childNormal != null) return _childNormal!!
        _childNormal = ImageVector.Builder(
            name = "Child.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(114.8f, 403.1f),
                        PathNode.QuadTo(66.2f, 425.3f, 35.9f, 471.0f),
                        PathNode.QuadTo(5.6f, 516.6f, 5.6f, 571.9f),
                        PathNode.QuadTo(5.6f, 627.2f, 35.9f, 672.9f),
                        PathNode.QuadTo(66.2f, 718.6f, 114.8f, 740.7f),
                        PathNode.QuadTo(148.2f, 833.5f, 216.2f, 905.7f),
                        PathNode.QuadTo(284.1f, 978.0f, 376.1f, 1018.4f),
                        PathNode.QuadTo(468.0f, 1058.8f, 571.5f, 1058.8f),
                        PathNode.QuadTo(674.9f, 1058.8f, 766.8f, 1018.4f),
                        PathNode.QuadTo(858.8f, 978.0f, 926.7f, 905.7f),
                        PathNode.QuadTo(994.7f, 833.5f, 1028.1f, 740.7f),
                        PathNode.QuadTo(1076.7f, 718.6f, 1107.0f, 672.9f),
                        PathNode.QuadTo(1137.3f, 627.2f, 1137.3f, 571.9f),
                        PathNode.QuadTo(1137.3f, 516.6f, 1107.0f, 471.0f),
                        PathNode.QuadTo(1076.7f, 425.3f, 1028.1f, 403.1f),
                        PathNode.QuadTo(994.7f, 310.4f, 926.7f, 237.7f),
                        PathNode.QuadTo(858.8f, 165.0f, 766.8f, 124.5f),
                        PathNode.QuadTo(674.8f, 84.1f, 571.4f, 84.1f),
                        PathNode.QuadTo(468.0f, 84.1f, 376.0f, 124.5f),
                        PathNode.QuadTo(284.1f, 165.0f, 216.2f, 237.7f),
                        PathNode.QuadTo(148.2f, 310.4f, 114.8f, 403.1f),
                        PathNode.Close,
                        PathNode.MoveTo(776.3f, 367.0f),
                        PathNode.QuadTo(785.5f, 377.0f, 785.5f, 390.9f),
                        PathNode.QuadTo(785.5f, 404.7f, 776.3f, 413.9f),
                        PathNode.QuadTo(767.2f, 424.0f, 753.4f, 424.0f),
                        PathNode.QuadTo(739.7f, 424.0f, 729.5f, 413.8f),
                        PathNode.QuadTo(687.0f, 370.4f, 629.2f, 355.2f),
                        PathNode.QuadTo(571.4f, 340.0f, 513.7f, 355.2f),
                        PathNode.QuadTo(455.9f, 370.4f, 413.3f, 413.8f),
                        PathNode.QuadTo(404.2f, 424.0f, 390.4f, 424.0f),
                        PathNode.QuadTo(376.7f, 424.0f, 366.6f, 413.9f),
                        PathNode.QuadTo(357.3f, 404.7f, 357.3f, 390.9f),
                        PathNode.QuadTo(357.3f, 377.0f, 366.5f, 367.0f),
                        PathNode.QuadTo(422.9f, 311.5f, 497.2f, 292.2f),
                        PathNode.QuadTo(571.4f, 272.8f, 646.2f, 292.2f),
                        PathNode.QuadTo(720.9f, 311.5f, 776.3f, 367.0f),
                        PathNode.Close,
                        PathNode.MoveTo(448.5f, 620.9f),
                        PathNode.QuadTo(448.5f, 652.7f, 427.9f, 674.8f),
                        PathNode.QuadTo(407.2f, 697.0f, 378.4f, 697.0f),
                        PathNode.QuadTo(349.6f, 697.0f, 329.0f, 674.8f),
                        PathNode.QuadTo(308.3f, 652.7f, 308.3f, 620.9f),
                        PathNode.QuadTo(308.3f, 590.0f, 329.0f, 567.9f),
                        PathNode.QuadTo(349.6f, 545.8f, 378.4f, 545.8f),
                        PathNode.QuadTo(407.2f, 545.8f, 427.9f, 567.9f),
                        PathNode.QuadTo(448.5f, 590.0f, 448.5f, 620.9f),
                        PathNode.Close,
                        PathNode.MoveTo(834.5f, 620.9f),
                        PathNode.QuadTo(834.5f, 652.7f, 813.9f, 674.8f),
                        PathNode.QuadTo(793.2f, 697.0f, 764.4f, 697.0f),
                        PathNode.QuadTo(735.6f, 697.0f, 715.0f, 674.8f),
                        PathNode.QuadTo(694.3f, 652.7f, 694.3f, 620.9f),
                        PathNode.QuadTo(694.3f, 590.0f, 715.0f, 567.9f),
                        PathNode.QuadTo(735.6f, 545.8f, 764.4f, 545.8f),
                        PathNode.QuadTo(793.2f, 545.8f, 813.9f, 567.9f),
                        PathNode.QuadTo(834.5f, 590.0f, 834.5f, 620.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _childNormal!!
    }

private var _childNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Child: ImageVector
    get() {
        if (_childRegular != null) return _childRegular!!
        _childRegular = ImageVector.Builder(
            name = "Child.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(111.4f, 399.9f),
                        PathNode.QuadTo(62.4f, 422.9f, 31.9f, 469.4f),
                        PathNode.QuadTo(1.4f, 515.9f, 1.4f, 571.9f),
                        PathNode.QuadTo(1.4f, 627.9f, 31.9f, 674.4f),
                        PathNode.QuadTo(62.4f, 720.9f, 111.4f, 743.9f),
                        PathNode.QuadTo(145.4f, 836.9f, 213.9f, 909.4f),
                        PathNode.QuadTo(282.4f, 981.9f, 374.9f, 1022.4f),
                        PathNode.QuadTo(467.4f, 1062.9f, 571.4f, 1062.9f),
                        PathNode.QuadTo(675.4f, 1062.9f, 767.9f, 1022.4f),
                        PathNode.QuadTo(860.4f, 981.9f, 928.9f, 909.4f),
                        PathNode.QuadTo(997.4f, 836.9f, 1031.4f, 743.9f),
                        PathNode.QuadTo(1080.4f, 720.9f, 1110.9f, 674.4f),
                        PathNode.QuadTo(1141.4f, 627.9f, 1141.4f, 571.9f),
                        PathNode.QuadTo(1141.4f, 515.9f, 1110.9f, 469.4f),
                        PathNode.QuadTo(1080.4f, 422.9f, 1031.4f, 399.9f),
                        PathNode.QuadTo(997.4f, 306.9f, 928.9f, 233.9f),
                        PathNode.QuadTo(860.4f, 160.9f, 767.9f, 120.4f),
                        PathNode.QuadTo(675.4f, 79.9f, 571.4f, 79.9f),
                        PathNode.QuadTo(467.4f, 79.9f, 374.9f, 120.4f),
                        PathNode.QuadTo(282.4f, 160.9f, 213.9f, 233.9f),
                        PathNode.QuadTo(145.4f, 306.9f, 111.4f, 399.9f),
                        PathNode.Close,
                        PathNode.MoveTo(778.4f, 364.9f),
                        PathNode.QuadTo(788.4f, 375.9f, 788.4f, 390.9f),
                        PathNode.QuadTo(788.4f, 405.9f, 778.4f, 415.9f),
                        PathNode.QuadTo(768.4f, 426.9f, 753.4f, 426.9f),
                        PathNode.QuadTo(738.4f, 426.9f, 727.4f, 415.9f),
                        PathNode.QuadTo(685.4f, 372.9f, 628.4f, 357.9f),
                        PathNode.QuadTo(571.4f, 342.9f, 514.4f, 357.9f),
                        PathNode.QuadTo(457.4f, 372.9f, 415.4f, 415.9f),
                        PathNode.QuadTo(405.4f, 426.9f, 390.4f, 426.9f),
                        PathNode.QuadTo(375.4f, 426.9f, 364.4f, 415.9f),
                        PathNode.QuadTo(354.4f, 405.9f, 354.4f, 390.9f),
                        PathNode.QuadTo(354.4f, 375.9f, 364.4f, 364.9f),
                        PathNode.QuadTo(421.4f, 308.9f, 496.4f, 289.4f),
                        PathNode.QuadTo(571.4f, 269.9f, 646.9f, 289.4f),
                        PathNode.QuadTo(722.4f, 308.9f, 778.4f, 364.9f),
                        PathNode.Close,
                        PathNode.MoveTo(451.4f, 620.9f),
                        PathNode.QuadTo(451.4f, 653.9f, 429.9f, 676.9f),
                        PathNode.QuadTo(408.4f, 699.9f, 378.4f, 699.9f),
                        PathNode.QuadTo(348.4f, 699.9f, 326.9f, 676.9f),
                        PathNode.QuadTo(305.4f, 653.9f, 305.4f, 620.9f),
                        PathNode.QuadTo(305.4f, 588.9f, 326.9f, 565.9f),
                        PathNode.QuadTo(348.4f, 542.9f, 378.4f, 542.9f),
                        PathNode.QuadTo(408.4f, 542.9f, 429.9f, 565.9f),
                        PathNode.QuadTo(451.4f, 588.9f, 451.4f, 620.9f),
                        PathNode.Close,
                        PathNode.MoveTo(837.4f, 620.9f),
                        PathNode.QuadTo(837.4f, 653.9f, 815.9f, 676.9f),
                        PathNode.QuadTo(794.4f, 699.9f, 764.4f, 699.9f),
                        PathNode.QuadTo(734.4f, 699.9f, 712.9f, 676.9f),
                        PathNode.QuadTo(691.4f, 653.9f, 691.4f, 620.9f),
                        PathNode.QuadTo(691.4f, 588.9f, 712.9f, 565.9f),
                        PathNode.QuadTo(734.4f, 542.9f, 764.4f, 542.9f),
                        PathNode.QuadTo(794.4f, 542.9f, 815.9f, 565.9f),
                        PathNode.QuadTo(837.4f, 588.9f, 837.4f, 620.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _childRegular!!
    }

private var _childRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Child: ImageVector
    get() {
        if (_childMedium != null) return _childMedium!!
        _childMedium = ImageVector.Builder(
            name = "Child.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(104.9f, 394.0f),
                        PathNode.QuadTo(54.9f, 418.3f, 24.1f, 466.3f),
                        PathNode.QuadTo(-6.7f, 514.2f, -6.7f, 571.9f),
                        PathNode.QuadTo(-6.7f, 629.7f, 24.1f, 677.6f),
                        PathNode.QuadTo(54.9f, 725.5f, 104.9f, 749.9f),
                        PathNode.QuadTo(140.0f, 843.7f, 209.6f, 916.6f),
                        PathNode.QuadTo(279.2f, 989.5f, 372.7f, 1030.3f),
                        PathNode.QuadTo(466.2f, 1071.0f, 571.4f, 1071.0f),
                        PathNode.QuadTo(676.5f, 1071.0f, 770.1f, 1030.3f),
                        PathNode.QuadTo(863.7f, 989.5f, 933.3f, 916.6f),
                        PathNode.QuadTo(1002.8f, 843.7f, 1037.9f, 749.9f),
                        PathNode.QuadTo(1088.0f, 725.5f, 1118.8f, 677.6f),
                        PathNode.QuadTo(1149.5f, 629.7f, 1149.5f, 571.9f),
                        PathNode.QuadTo(1149.5f, 514.2f, 1118.8f, 466.3f),
                        PathNode.QuadTo(1088.0f, 418.3f, 1037.9f, 394.0f),
                        PathNode.QuadTo(1002.8f, 300.2f, 933.3f, 226.8f),
                        PathNode.QuadTo(863.7f, 153.4f, 770.2f, 112.6f),
                        PathNode.QuadTo(676.6f, 71.8f, 571.5f, 71.8f),
                        PathNode.QuadTo(466.3f, 71.8f, 372.8f, 112.6f),
                        PathNode.QuadTo(279.2f, 153.4f, 209.6f, 226.8f),
                        PathNode.QuadTo(140.0f, 300.2f, 104.9f, 394.0f),
                        PathNode.Close,
                        PathNode.MoveTo(780.3f, 363.0f),
                        PathNode.QuadTo(791.1f, 374.8f, 791.1f, 390.9f),
                        PathNode.QuadTo(791.1f, 407.0f, 780.4f, 417.8f),
                        PathNode.QuadTo(769.6f, 429.6f, 753.5f, 429.6f),
                        PathNode.QuadTo(737.3f, 429.6f, 725.5f, 417.8f),
                        PathNode.QuadTo(684.0f, 375.5f, 627.7f, 360.7f),
                        PathNode.QuadTo(571.4f, 345.9f, 515.1f, 360.7f),
                        PathNode.QuadTo(458.8f, 375.5f, 417.3f, 417.8f),
                        PathNode.QuadTo(406.6f, 429.6f, 390.5f, 429.6f),
                        PathNode.QuadTo(374.3f, 429.6f, 362.5f, 417.8f),
                        PathNode.QuadTo(351.7f, 407.1f, 351.7f, 391.0f),
                        PathNode.QuadTo(351.7f, 374.8f, 362.5f, 363.0f),
                        PathNode.QuadTo(420.1f, 306.4f, 495.7f, 286.7f),
                        PathNode.QuadTo(571.4f, 267.0f, 647.6f, 286.7f),
                        PathNode.QuadTo(723.8f, 306.4f, 780.3f, 363.0f),
                        PathNode.Close,
                        PathNode.MoveTo(454.1f, 620.9f),
                        PathNode.QuadTo(454.1f, 655.0f, 431.8f, 678.8f),
                        PathNode.QuadTo(409.6f, 702.6f, 378.4f, 702.6f),
                        PathNode.QuadTo(347.3f, 702.6f, 325.0f, 678.8f),
                        PathNode.QuadTo(302.7f, 655.1f, 302.7f, 620.9f),
                        PathNode.QuadTo(302.7f, 587.8f, 325.0f, 564.0f),
                        PathNode.QuadTo(347.3f, 540.2f, 378.4f, 540.2f),
                        PathNode.QuadTo(409.5f, 540.2f, 431.8f, 564.0f),
                        PathNode.QuadTo(454.1f, 587.8f, 454.1f, 620.9f),
                        PathNode.Close,
                        PathNode.MoveTo(840.1f, 620.9f),
                        PathNode.QuadTo(840.1f, 655.0f, 817.8f, 678.8f),
                        PathNode.QuadTo(795.6f, 702.6f, 764.4f, 702.6f),
                        PathNode.QuadTo(733.3f, 702.6f, 711.0f, 678.8f),
                        PathNode.QuadTo(688.7f, 655.1f, 688.7f, 620.9f),
                        PathNode.QuadTo(688.7f, 587.8f, 711.0f, 564.0f),
                        PathNode.QuadTo(733.3f, 540.2f, 764.4f, 540.2f),
                        PathNode.QuadTo(795.5f, 540.2f, 817.8f, 564.0f),
                        PathNode.QuadTo(840.1f, 587.8f, 840.1f, 620.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _childMedium!!
    }

private var _childMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Child: ImageVector
    get() {
        if (_childDemibold != null) return _childDemibold!!
        _childDemibold = ImageVector.Builder(
            name = "Child.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(100.4f, 389.8f),
                        PathNode.QuadTo(49.6f, 415.1f, 18.6f, 464.1f),
                        PathNode.QuadTo(-12.4f, 513.0f, -12.4f, 571.9f),
                        PathNode.QuadTo(-12.4f, 630.9f, 18.6f, 679.8f),
                        PathNode.QuadTo(49.6f, 728.7f, 100.4f, 754.0f),
                        PathNode.QuadTo(136.2f, 848.4f, 206.6f, 921.6f),
                        PathNode.QuadTo(276.9f, 994.8f, 371.1f, 1035.8f),
                        PathNode.QuadTo(465.4f, 1076.7f, 571.3f, 1076.7f),
                        PathNode.QuadTo(677.3f, 1076.7f, 771.6f, 1035.8f),
                        PathNode.QuadTo(865.9f, 994.8f, 936.3f, 921.6f),
                        PathNode.QuadTo(1006.6f, 848.4f, 1042.5f, 754.0f),
                        PathNode.QuadTo(1093.3f, 728.7f, 1124.3f, 679.8f),
                        PathNode.QuadTo(1155.2f, 630.9f, 1155.2f, 571.9f),
                        PathNode.QuadTo(1155.2f, 513.0f, 1124.3f, 464.1f),
                        PathNode.QuadTo(1093.3f, 415.1f, 1042.5f, 389.8f),
                        PathNode.QuadTo(1006.6f, 295.4f, 936.3f, 221.8f),
                        PathNode.QuadTo(865.9f, 148.1f, 771.7f, 107.1f),
                        PathNode.QuadTo(677.5f, 66.1f, 571.5f, 66.1f),
                        PathNode.QuadTo(465.6f, 66.1f, 371.3f, 107.1f),
                        PathNode.QuadTo(276.9f, 148.1f, 206.6f, 221.8f),
                        PathNode.QuadTo(136.2f, 295.4f, 100.4f, 389.8f),
                        PathNode.Close,
                        PathNode.MoveTo(781.6f, 361.7f),
                        PathNode.QuadTo(793.0f, 374.1f, 793.0f, 390.9f),
                        PathNode.QuadTo(793.0f, 407.8f, 781.7f, 419.1f),
                        PathNode.QuadTo(770.5f, 431.5f, 753.5f, 431.5f),
                        PathNode.QuadTo(736.6f, 431.5f, 724.2f, 419.1f),
                        PathNode.QuadTo(683.1f, 377.2f, 627.3f, 362.6f),
                        PathNode.QuadTo(571.4f, 348.0f, 515.6f, 362.6f),
                        PathNode.QuadTo(459.8f, 377.2f, 418.6f, 419.1f),
                        PathNode.QuadTo(407.4f, 431.5f, 390.5f, 431.5f),
                        PathNode.QuadTo(373.6f, 431.5f, 361.1f, 419.1f),
                        PathNode.QuadTo(349.8f, 407.9f, 349.8f, 391.0f),
                        PathNode.QuadTo(349.8f, 374.1f, 361.2f, 361.7f),
                        PathNode.QuadTo(419.1f, 304.6f, 495.3f, 284.8f),
                        PathNode.QuadTo(571.4f, 264.9f, 648.1f, 284.8f),
                        PathNode.QuadTo(724.8f, 304.6f, 781.6f, 361.7f),
                        PathNode.Close,
                        PathNode.MoveTo(456.0f, 620.9f),
                        PathNode.QuadTo(456.0f, 655.8f, 433.2f, 680.1f),
                        PathNode.QuadTo(410.3f, 704.5f, 378.5f, 704.5f),
                        PathNode.QuadTo(346.6f, 704.5f, 323.7f, 680.2f),
                        PathNode.QuadTo(300.8f, 655.9f, 300.8f, 620.9f),
                        PathNode.QuadTo(300.8f, 587.1f, 323.7f, 562.7f),
                        PathNode.QuadTo(346.5f, 538.3f, 378.4f, 538.3f),
                        PathNode.QuadTo(410.3f, 538.3f, 433.1f, 562.7f),
                        PathNode.QuadTo(456.0f, 587.0f, 456.0f, 620.9f),
                        PathNode.Close,
                        PathNode.MoveTo(842.0f, 620.9f),
                        PathNode.QuadTo(842.0f, 655.8f, 819.2f, 680.1f),
                        PathNode.QuadTo(796.3f, 704.5f, 764.5f, 704.5f),
                        PathNode.QuadTo(732.6f, 704.5f, 709.7f, 680.2f),
                        PathNode.QuadTo(686.8f, 655.9f, 686.8f, 620.9f),
                        PathNode.QuadTo(686.8f, 587.1f, 709.7f, 562.7f),
                        PathNode.QuadTo(732.5f, 538.3f, 764.4f, 538.3f),
                        PathNode.QuadTo(796.3f, 538.3f, 819.1f, 562.7f),
                        PathNode.QuadTo(842.0f, 587.0f, 842.0f, 620.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _childDemibold!!
    }

private var _childDemibold: ImageVector? = null
