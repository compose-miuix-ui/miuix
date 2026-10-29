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

val MiuixIcons.Glass.CloudFill: ImageVector
    get() = MiuixIcons.Glass.Regular.CloudFill

val MiuixIcons.Glass.Light.CloudFill: ImageVector
    get() {
        if (_cloudFillLight != null) return _cloudFillLight!!
        _cloudFillLight = ImageVector.Builder(
            name = "CloudFill.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1126.2f, 436.1f),
                        PathNode.QuadTo(1126.2f, 511.5f, 1088.8f, 575.7f),
                        PathNode.QuadTo(1051.3f, 639.8f, 987.7f, 677.0f),
                        PathNode.QuadTo(924.1f, 714.2f, 849.1f, 714.2f),
                        PathNode.QuadTo(771.3f, 714.2f, 706.0f, 679.7f),
                        PathNode.QuadTo(640.7f, 645.2f, 592.7f, 576.9f),
                        PathNode.QuadTo(587.3f, 568.9f, 581.8f, 567.5f),
                        PathNode.QuadTo(576.2f, 566.2f, 568.2f, 570.0f),
                        PathNode.LineTo(555.0f, 576.7f),
                        PathNode.QuadTo(548.7f, 580.3f, 546.8f, 586.5f),
                        PathNode.QuadTo(544.9f, 592.7f, 549.2f, 599.0f),
                        PathNode.QuadTo(593.3f, 665.9f, 656.5f, 707.3f),
                        PathNode.QuadTo(719.8f, 748.6f, 795.3f, 759.1f),
                        PathNode.QuadTo(785.0f, 822.5f, 747.2f, 873.9f),
                        PathNode.QuadTo(709.4f, 925.2f, 652.8f, 954.5f),
                        PathNode.QuadTo(596.2f, 983.9f, 531.0f, 983.9f),
                        PathNode.QuadTo(457.5f, 983.9f, 395.4f, 947.7f),
                        PathNode.QuadTo(333.3f, 911.4f, 297.1f, 849.3f),
                        PathNode.QuadTo(260.9f, 787.2f, 260.9f, 713.8f),
                        PathNode.QuadTo(260.9f, 706.0f, 264.2f, 667.1f),
                        PathNode.QuadTo(197.2f, 667.1f, 140.3f, 633.0f),
                        PathNode.QuadTo(83.4f, 599.0f, 50.0f, 540.6f),
                        PathNode.QuadTo(16.7f, 482.2f, 16.7f, 413.1f),
                        PathNode.QuadTo(16.7f, 345.2f, 49.6f, 286.8f),
                        PathNode.QuadTo(82.5f, 228.4f, 138.4f, 193.7f),
                        PathNode.QuadTo(194.3f, 159.0f, 258.8f, 159.0f),
                        PathNode.LineTo(849.1f, 159.0f),
                        PathNode.QuadTo(924.1f, 159.0f, 987.7f, 196.4f),
                        PathNode.QuadTo(1051.3f, 233.9f, 1088.8f, 297.5f),
                        PathNode.QuadTo(1126.2f, 361.1f, 1126.2f, 436.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudFillLight!!
    }

private var _cloudFillLight: ImageVector? = null

val MiuixIcons.Glass.Normal.CloudFill: ImageVector
    get() {
        if (_cloudFillNormal != null) return _cloudFillNormal!!
        _cloudFillNormal = ImageVector.Builder(
            name = "CloudFill.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1133.2f, 437.0f),
                        PathNode.QuadTo(1133.2f, 514.9f, 1094.7f, 581.0f),
                        PathNode.QuadTo(1056.2f, 647.1f, 990.6f, 685.5f),
                        PathNode.QuadTo(925.0f, 724.0f, 847.3f, 724.0f),
                        PathNode.QuadTo(773.5f, 724.0f, 709.0f, 689.5f),
                        PathNode.QuadTo(644.6f, 655.0f, 599.4f, 590.3f),
                        PathNode.QuadTo(592.2f, 579.6f, 585.0f, 577.8f),
                        PathNode.QuadTo(577.7f, 576.0f, 567.0f, 581.3f),
                        PathNode.LineTo(552.5f, 588.9f),
                        PathNode.QuadTo(544.4f, 593.5f, 541.7f, 601.9f),
                        PathNode.QuadTo(539.1f, 610.4f, 545.2f, 618.5f),
                        PathNode.QuadTo(590.0f, 687.6f, 655.8f, 729.7f),
                        PathNode.QuadTo(721.6f, 771.9f, 800.2f, 782.0f),
                        PathNode.QuadTo(785.3f, 842.4f, 746.0f, 890.1f),
                        PathNode.QuadTo(706.7f, 937.7f, 650.8f, 964.8f),
                        PathNode.QuadTo(595.0f, 991.8f, 531.3f, 991.8f),
                        PathNode.QuadTo(455.4f, 991.8f, 391.3f, 954.4f),
                        PathNode.QuadTo(327.2f, 916.9f, 289.8f, 852.8f),
                        PathNode.QuadTo(252.3f, 788.7f, 252.3f, 712.8f),
                        PathNode.QuadTo(252.3f, 703.5f, 254.7f, 676.3f),
                        PathNode.QuadTo(187.7f, 673.5f, 131.5f, 637.8f),
                        PathNode.QuadTo(75.2f, 602.0f, 42.4f, 542.9f),
                        PathNode.QuadTo(9.6f, 483.7f, 9.6f, 414.0f),
                        PathNode.QuadTo(9.6f, 344.0f, 43.6f, 283.5f),
                        PathNode.QuadTo(77.7f, 222.9f, 135.7f, 187.0f),
                        PathNode.QuadTo(193.7f, 151.0f, 260.6f, 151.0f),
                        PathNode.LineTo(847.3f, 151.0f),
                        PathNode.QuadTo(925.0f, 151.0f, 990.6f, 189.6f),
                        PathNode.QuadTo(1056.2f, 228.1f, 1094.7f, 293.7f),
                        PathNode.QuadTo(1133.2f, 359.3f, 1133.2f, 437.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudFillNormal!!
    }

private var _cloudFillNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.CloudFill: ImageVector
    get() {
        if (_cloudFillRegular != null) return _cloudFillRegular!!
        _cloudFillRegular = ImageVector.Builder(
            name = "CloudFill.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1136.4f, 437.4f),
                        PathNode.QuadTo(1136.4f, 516.4f, 1097.4f, 583.4f),
                        PathNode.QuadTo(1058.4f, 650.4f, 991.9f, 689.4f),
                        PathNode.QuadTo(925.4f, 728.4f, 846.4f, 728.4f),
                        PathNode.QuadTo(774.4f, 728.4f, 710.4f, 693.9f),
                        PathNode.QuadTo(646.4f, 659.4f, 602.4f, 596.4f),
                        PathNode.QuadTo(594.4f, 584.4f, 586.4f, 582.4f),
                        PathNode.QuadTo(578.4f, 580.4f, 566.4f, 586.4f),
                        PathNode.LineTo(551.4f, 594.4f),
                        PathNode.QuadTo(542.4f, 599.4f, 539.4f, 608.9f),
                        PathNode.QuadTo(536.4f, 618.4f, 543.4f, 627.4f),
                        PathNode.QuadTo(588.4f, 697.4f, 655.4f, 739.9f),
                        PathNode.QuadTo(722.4f, 782.4f, 802.4f, 792.4f),
                        PathNode.QuadTo(785.4f, 851.4f, 745.4f, 897.4f),
                        PathNode.QuadTo(705.4f, 943.4f, 649.9f, 969.4f),
                        PathNode.QuadTo(594.4f, 995.4f, 531.4f, 995.4f),
                        PathNode.QuadTo(454.4f, 995.4f, 389.4f, 957.4f),
                        PathNode.QuadTo(324.4f, 919.4f, 286.4f, 854.4f),
                        PathNode.QuadTo(248.4f, 789.4f, 248.4f, 712.4f),
                        PathNode.QuadTo(248.4f, 702.4f, 250.4f, 680.4f),
                        PathNode.QuadTo(183.4f, 676.4f, 127.4f, 639.9f),
                        PathNode.QuadTo(71.4f, 603.4f, 38.9f, 543.9f),
                        PathNode.QuadTo(6.4f, 484.4f, 6.4f, 414.4f),
                        PathNode.QuadTo(6.4f, 343.4f, 40.9f, 281.9f),
                        PathNode.QuadTo(75.4f, 220.4f, 134.4f, 183.9f),
                        PathNode.QuadTo(193.4f, 147.4f, 261.4f, 147.4f),
                        PathNode.LineTo(846.4f, 147.4f),
                        PathNode.QuadTo(925.4f, 147.4f, 991.9f, 186.4f),
                        PathNode.QuadTo(1058.4f, 225.4f, 1097.4f, 291.9f),
                        PathNode.QuadTo(1136.4f, 358.4f, 1136.4f, 437.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudFillRegular!!
    }

private var _cloudFillRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.CloudFill: ImageVector
    get() {
        if (_cloudFillMedium != null) return _cloudFillMedium!!
        _cloudFillMedium = ImageVector.Builder(
            name = "CloudFill.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1142.6f, 438.2f),
                        PathNode.QuadTo(1142.6f, 518.6f, 1103.4f, 586.8f),
                        PathNode.QuadTo(1064.1f, 655.0f, 997.1f, 694.7f),
                        PathNode.QuadTo(930.0f, 734.4f, 850.8f, 734.1f),
                        PathNode.QuadTo(778.2f, 733.6f, 714.6f, 699.6f),
                        PathNode.QuadTo(651.0f, 665.6f, 607.3f, 603.2f),
                        PathNode.QuadTo(598.2f, 589.8f, 589.0f, 587.4f),
                        PathNode.QuadTo(579.8f, 585.0f, 566.7f, 592.1f),
                        PathNode.LineTo(548.5f, 602.3f),
                        PathNode.QuadTo(537.8f, 608.6f, 534.4f, 620.0f),
                        PathNode.QuadTo(531.0f, 631.4f, 538.8f, 642.0f),
                        PathNode.QuadTo(584.9f, 712.8f, 653.3f, 756.0f),
                        PathNode.QuadTo(721.6f, 799.2f, 802.7f, 808.6f),
                        PathNode.QuadTo(783.8f, 865.2f, 743.5f, 909.2f),
                        PathNode.QuadTo(703.3f, 953.2f, 648.4f, 977.8f),
                        PathNode.QuadTo(593.6f, 1002.5f, 531.7f, 1002.5f),
                        PathNode.QuadTo(453.9f, 1002.5f, 388.1f, 964.6f),
                        PathNode.QuadTo(322.3f, 926.7f, 282.8f, 862.0f),
                        PathNode.QuadTo(243.3f, 797.3f, 241.1f, 720.0f),
                        PathNode.QuadTo(241.1f, 708.6f, 242.3f, 688.3f),
                        PathNode.QuadTo(175.3f, 682.1f, 119.9f, 644.1f),
                        PathNode.QuadTo(64.4f, 606.1f, 32.3f, 545.8f),
                        PathNode.QuadTo(0.2f, 485.5f, 0.2f, 415.2f),
                        PathNode.QuadTo(0.2f, 342.1f, 35.8f, 278.8f),
                        PathNode.QuadTo(71.4f, 215.6f, 132.0f, 178.0f),
                        PathNode.QuadTo(192.6f, 140.4f, 263.1f, 140.4f),
                        PathNode.LineTo(845.1f, 140.4f),
                        PathNode.QuadTo(926.0f, 140.4f, 994.2f, 180.5f),
                        PathNode.QuadTo(1062.5f, 220.6f, 1102.6f, 288.8f),
                        PathNode.QuadTo(1142.6f, 357.1f, 1142.6f, 438.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudFillMedium!!
    }

private var _cloudFillMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.CloudFill: ImageVector
    get() {
        if (_cloudFillDemibold != null) return _cloudFillDemibold!!
        _cloudFillDemibold = ImageVector.Builder(
            name = "CloudFill.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1147.0f, 438.8f),
                        PathNode.QuadTo(1147.0f, 520.1f, 1107.5f, 589.2f),
                        PathNode.QuadTo(1068.1f, 658.2f, 1000.7f, 698.4f),
                        PathNode.QuadTo(933.2f, 738.5f, 853.8f, 738.1f),
                        PathNode.QuadTo(780.9f, 737.2f, 717.6f, 703.6f),
                        PathNode.QuadTo(654.2f, 670.0f, 610.7f, 607.9f),
                        PathNode.QuadTo(600.9f, 593.6f, 590.8f, 590.9f),
                        PathNode.QuadTo(580.7f, 588.2f, 566.9f, 596.1f),
                        PathNode.LineTo(546.4f, 607.8f),
                        PathNode.QuadTo(534.6f, 615.1f, 530.9f, 627.8f),
                        PathNode.QuadTo(527.2f, 640.5f, 535.6f, 652.2f),
                        PathNode.QuadTo(582.5f, 723.6f, 651.8f, 767.3f),
                        PathNode.QuadTo(721.1f, 810.9f, 802.9f, 820.0f),
                        PathNode.QuadTo(782.7f, 874.9f, 742.2f, 917.4f),
                        PathNode.QuadTo(701.8f, 960.0f, 647.4f, 983.7f),
                        PathNode.QuadTo(593.1f, 1007.4f, 531.9f, 1007.4f),
                        PathNode.QuadTo(453.5f, 1007.4f, 387.1f, 969.6f),
                        PathNode.QuadTo(320.8f, 931.8f, 280.2f, 867.3f),
                        PathNode.QuadTo(239.7f, 802.8f, 236.0f, 725.3f),
                        PathNode.QuadTo(236.0f, 713.0f, 236.6f, 693.8f),
                        PathNode.QuadTo(169.6f, 686.1f, 114.6f, 647.1f),
                        PathNode.QuadTo(59.5f, 608.0f, 27.7f, 547.1f),
                        PathNode.QuadTo(-4.1f, 486.3f, -4.1f, 415.8f),
                        PathNode.QuadTo(-4.1f, 341.1f, 32.2f, 276.6f),
                        PathNode.QuadTo(68.5f, 212.2f, 130.3f, 173.8f),
                        PathNode.QuadTo(192.1f, 135.5f, 264.2f, 135.5f),
                        PathNode.LineTo(844.1f, 135.5f),
                        PathNode.QuadTo(926.3f, 135.5f, 995.8f, 176.3f),
                        PathNode.QuadTo(1065.3f, 217.2f, 1106.2f, 286.6f),
                        PathNode.QuadTo(1147.0f, 356.1f, 1147.0f, 438.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudFillDemibold!!
    }

private var _cloudFillDemibold: ImageVector? = null
