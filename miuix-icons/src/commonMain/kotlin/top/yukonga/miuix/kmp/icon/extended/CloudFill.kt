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

val MiuixIcons.CloudFill: ImageVector
    get() = MiuixIcons.Regular.CloudFill

val MiuixIcons.Light.CloudFill: ImageVector
    get() {
        if (_cloudfillLight != null) return _cloudfillLight!!
        _cloudfillLight = ImageVector.Builder(
            name = "CloudFill.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1332.0f,
            viewportHeight = 1332.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1332.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1221.0f, 531.0f),
                        PathNode.QuadTo(1221.0f, 607.0f, 1183.5f, 670.5f),
                        PathNode.QuadTo(1146.0f, 734.0f, 1082.5f, 771.5f),
                        PathNode.QuadTo(1019.0f, 809.0f, 944.0f, 809.0f),
                        PathNode.QuadTo(866.0f, 809.0f, 800.5f, 774.5f),
                        PathNode.QuadTo(735.0f, 740.0f, 687.0f, 671.0f),
                        PathNode.QuadTo(682.0f, 663.0f, 676.0f, 662.0f),
                        PathNode.QuadTo(670.0f, 661.0f, 663.0f, 665.0f),
                        PathNode.LineTo(650.0f, 671.0f),
                        PathNode.QuadTo(643.0f, 674.0f, 641.5f, 680.5f),
                        PathNode.QuadTo(640.0f, 687.0f, 644.0f, 694.0f),
                        PathNode.QuadTo(688.0f, 760.0f, 751.0f, 802.0f),
                        PathNode.QuadTo(814.0f, 844.0f, 890.0f, 854.0f),
                        PathNode.QuadTo(880.0f, 917.0f, 842.0f, 968.0f),
                        PathNode.QuadTo(804.0f, 1019.0f, 747.5f, 1048.5f),
                        PathNode.QuadTo(691.0f, 1078.0f, 626.0f, 1078.0f),
                        PathNode.QuadTo(553.0f, 1078.0f, 490.5f, 1041.5f),
                        PathNode.QuadTo(428.0f, 1005.0f, 391.5f, 943.0f),
                        PathNode.QuadTo(355.0f, 881.0f, 355.0f, 808.0f),
                        PathNode.QuadTo(355.0f, 802.0f, 357.0f, 782.0f),
                        PathNode.LineTo(359.0f, 762.0f),
                        PathNode.QuadTo(292.0f, 762.0f, 235.0f, 728.0f),
                        PathNode.QuadTo(178.0f, 694.0f, 144.5f, 635.5f),
                        PathNode.QuadTo(111.0f, 577.0f, 111.0f, 508.0f),
                        PathNode.QuadTo(111.0f, 440.0f, 144.0f, 381.5f),
                        PathNode.QuadTo(177.0f, 323.0f, 232.5f, 288.5f),
                        PathNode.QuadTo(288.0f, 254.0f, 353.0f, 254.0f),
                        PathNode.HorizontalTo(944.0f),
                        PathNode.QuadTo(1019.0f, 254.0f, 1082.5f, 291.5f),
                        PathNode.QuadTo(1146.0f, 329.0f, 1183.5f, 392.5f),
                        PathNode.QuadTo(1221.0f, 456.0f, 1221.0f, 531.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudfillLight!!
    }

private var _cloudfillLight: ImageVector? = null

val MiuixIcons.Normal.CloudFill: ImageVector
    get() {
        if (_cloudfillNormal != null) return _cloudfillNormal!!
        _cloudfillNormal = ImageVector.Builder(
            name = "CloudFill.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1348.5f,
            viewportHeight = 1348.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1348.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1236.1f, 539.9f),
                        PathNode.QuadTo(1236.1f, 618.0f, 1197.6f, 683.9f),
                        PathNode.QuadTo(1159.1f, 749.8f, 1093.5f, 788.3f),
                        PathNode.QuadTo(1027.9f, 826.9f, 950.2f, 826.9f),
                        PathNode.QuadTo(876.3f, 826.9f, 811.8f, 792.4f),
                        PathNode.QuadTo(747.4f, 757.9f, 702.1f, 693.0f),
                        PathNode.QuadTo(695.1f, 682.2f, 687.7f, 680.6f),
                        PathNode.QuadTo(680.3f, 678.9f, 669.9f, 684.2f),
                        PathNode.LineTo(655.5f, 691.6f),
                        PathNode.QuadTo(647.1f, 696.0f, 644.6f, 704.6f),
                        PathNode.QuadTo(642.1f, 713.1f, 648.1f, 721.5f),
                        PathNode.QuadTo(692.8f, 790.2f, 758.6f, 832.6f),
                        PathNode.QuadTo(824.3f, 874.9f, 903.1f, 884.9f),
                        PathNode.QuadTo(888.2f, 945.2f, 848.9f, 992.8f),
                        PathNode.QuadTo(809.5f, 1040.3f, 753.7f, 1067.4f),
                        PathNode.QuadTo(697.9f, 1094.5f, 634.2f, 1094.5f),
                        PathNode.QuadTo(558.5f, 1094.5f, 494.3f, 1057.0f),
                        PathNode.QuadTo(430.1f, 1019.4f, 392.5f, 955.4f),
                        PathNode.QuadTo(355.0f, 891.3f, 355.0f, 815.6f),
                        PathNode.QuadTo(355.0f, 804.1f, 356.3f, 793.0f),
                        PathNode.LineTo(357.6f, 779.2f),
                        PathNode.QuadTo(290.6f, 776.4f, 234.3f, 740.7f),
                        PathNode.QuadTo(178.0f, 705.0f, 145.2f, 645.8f),
                        PathNode.QuadTo(112.4f, 586.6f, 112.4f, 516.9f),
                        PathNode.QuadTo(112.4f, 446.9f, 146.4f, 386.3f),
                        PathNode.QuadTo(180.4f, 325.8f, 238.3f, 289.9f),
                        PathNode.QuadTo(296.2f, 254.0f, 363.3f, 254.0f),
                        PathNode.HorizontalTo(950.2f),
                        PathNode.QuadTo(1027.9f, 254.0f, 1093.5f, 292.5f),
                        PathNode.QuadTo(1159.1f, 331.1f, 1197.6f, 396.6f),
                        PathNode.QuadTo(1236.1f, 462.2f, 1236.1f, 539.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudfillNormal!!
    }

private var _cloudfillNormal: ImageVector? = null

val MiuixIcons.Regular.CloudFill: ImageVector
    get() {
        if (_cloudfillRegular != null) return _cloudfillRegular!!
        _cloudfillRegular = ImageVector.Builder(
            name = "CloudFill.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1356.0f,
            viewportHeight = 1356.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1356.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1243.0f, 544.0f),
                        PathNode.QuadTo(1243.0f, 623.0f, 1204.0f, 690.0f),
                        PathNode.QuadTo(1165.0f, 757.0f, 1098.5f, 796.0f),
                        PathNode.QuadTo(1032.0f, 835.0f, 953.0f, 835.0f),
                        PathNode.QuadTo(881.0f, 835.0f, 817.0f, 800.5f),
                        PathNode.QuadTo(753.0f, 766.0f, 709.0f, 703.0f),
                        PathNode.QuadTo(701.0f, 691.0f, 693.0f, 689.0f),
                        PathNode.QuadTo(685.0f, 687.0f, 673.0f, 693.0f),
                        PathNode.LineTo(658.0f, 701.0f),
                        PathNode.QuadTo(649.0f, 706.0f, 646.0f, 715.5f),
                        PathNode.QuadTo(643.0f, 725.0f, 650.0f, 734.0f),
                        PathNode.QuadTo(695.0f, 804.0f, 762.0f, 846.5f),
                        PathNode.QuadTo(829.0f, 889.0f, 909.0f, 899.0f),
                        PathNode.QuadTo(892.0f, 958.0f, 852.0f, 1004.0f),
                        PathNode.QuadTo(812.0f, 1050.0f, 756.5f, 1076.0f),
                        PathNode.QuadTo(701.0f, 1102.0f, 638.0f, 1102.0f),
                        PathNode.QuadTo(561.0f, 1102.0f, 496.0f, 1064.0f),
                        PathNode.QuadTo(431.0f, 1026.0f, 393.0f, 961.0f),
                        PathNode.QuadTo(355.0f, 896.0f, 355.0f, 819.0f),
                        PathNode.QuadTo(355.0f, 805.0f, 356.0f, 798.0f),
                        PathNode.LineTo(357.0f, 787.0f),
                        PathNode.QuadTo(290.0f, 783.0f, 234.0f, 746.5f),
                        PathNode.QuadTo(178.0f, 710.0f, 145.5f, 650.5f),
                        PathNode.QuadTo(113.0f, 591.0f, 113.0f, 521.0f),
                        PathNode.QuadTo(113.0f, 450.0f, 147.5f, 388.5f),
                        PathNode.QuadTo(182.0f, 327.0f, 241.0f, 290.5f),
                        PathNode.QuadTo(300.0f, 254.0f, 368.0f, 254.0f),
                        PathNode.HorizontalTo(953.0f),
                        PathNode.QuadTo(1032.0f, 254.0f, 1098.5f, 293.0f),
                        PathNode.QuadTo(1165.0f, 332.0f, 1204.0f, 398.5f),
                        PathNode.QuadTo(1243.0f, 465.0f, 1243.0f, 544.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudfillRegular!!
    }

private var _cloudfillRegular: ImageVector? = null

val MiuixIcons.Medium.CloudFill: ImageVector
    get() {
        if (_cloudfillMedium != null) return _cloudfillMedium!!
        _cloudfillMedium = ImageVector.Builder(
            name = "CloudFill.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1371.5f,
            viewportHeight = 1371.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1371.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1257.2f, 552.4f),
                        PathNode.QuadTo(1257.2f, 633.1f, 1217.9f, 701.3f),
                        PathNode.QuadTo(1178.6f, 769.5f, 1111.6f, 809.1f),
                        PathNode.QuadTo(1044.5f, 848.6f, 964.9f, 848.6f),
                        PathNode.QuadTo(892.3f, 848.1f, 828.9f, 814.1f),
                        PathNode.QuadTo(765.5f, 780.2f, 721.5f, 717.2f),
                        PathNode.QuadTo(712.3f, 704.1f, 703.1f, 701.8f),
                        PathNode.QuadTo(693.9f, 699.5f, 680.8f, 706.6f),
                        PathNode.LineTo(662.8f, 716.4f),
                        PathNode.QuadTo(652.1f, 723.2f, 648.8f, 734.4f),
                        PathNode.QuadTo(645.5f, 745.7f, 653.1f, 756.5f),
                        PathNode.QuadTo(699.2f, 827.1f, 767.4f, 870.1f),
                        PathNode.QuadTo(835.6f, 913.2f, 916.8f, 923.2f),
                        PathNode.QuadTo(898.0f, 979.9f, 857.7f, 1023.8f),
                        PathNode.QuadTo(817.4f, 1067.8f, 762.5f, 1092.3f),
                        PathNode.QuadTo(707.6f, 1116.8f, 645.8f, 1116.8f),
                        PathNode.QuadTo(568.2f, 1116.8f, 502.3f, 1078.8f),
                        PathNode.QuadTo(436.4f, 1040.8f, 396.9f, 976.1f),
                        PathNode.QuadTo(357.5f, 911.4f, 355.7f, 834.4f),
                        PathNode.QuadTo(355.7f, 822.8f, 356.1f, 813.4f),
                        PathNode.LineTo(356.5f, 802.4f),
                        PathNode.QuadTo(289.5f, 796.6f, 234.1f, 758.7f),
                        PathNode.QuadTo(178.7f, 720.7f, 146.5f, 660.3f),
                        PathNode.QuadTo(114.3f, 599.9f, 114.3f, 529.4f),
                        PathNode.QuadTo(114.3f, 456.0f, 150.0f, 392.7f),
                        PathNode.QuadTo(185.6f, 329.5f, 246.4f, 292.1f),
                        PathNode.QuadTo(307.2f, 254.7f, 377.5f, 254.7f),
                        PathNode.HorizontalTo(959.6f),
                        PathNode.QuadTo(1040.4f, 254.7f, 1108.6f, 294.9f),
                        PathNode.QuadTo(1176.9f, 335.1f, 1217.1f, 403.3f),
                        PathNode.QuadTo(1257.2f, 471.6f, 1257.2f, 552.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudfillMedium!!
    }

private var _cloudfillMedium: ImageVector? = null

val MiuixIcons.Demibold.CloudFill: ImageVector
    get() {
        if (_cloudfillDemibold != null) return _cloudfillDemibold!!
        _cloudfillDemibold = ImageVector.Builder(
            name = "CloudFill.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1382.4f,
            viewportHeight = 1382.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1382.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1267.2f, 558.2f),
                        PathNode.QuadTo(1267.2f, 640.2f, 1227.7f, 709.2f),
                        PathNode.QuadTo(1188.2f, 778.2f, 1120.7f, 818.2f),
                        PathNode.QuadTo(1053.2f, 858.2f, 973.2f, 858.2f),
                        PathNode.QuadTo(900.2f, 857.2f, 837.2f, 823.7f),
                        PathNode.QuadTo(774.2f, 790.2f, 730.2f, 727.2f),
                        PathNode.QuadTo(720.2f, 713.2f, 710.2f, 710.7f),
                        PathNode.QuadTo(700.2f, 708.2f, 686.2f, 716.2f),
                        PathNode.LineTo(666.2f, 727.2f),
                        PathNode.QuadTo(654.2f, 735.2f, 650.7f, 747.7f),
                        PathNode.QuadTo(647.2f, 760.2f, 655.2f, 772.2f),
                        PathNode.QuadTo(702.2f, 843.2f, 771.2f, 886.7f),
                        PathNode.QuadTo(840.2f, 930.2f, 922.2f, 940.2f),
                        PathNode.QuadTo(902.2f, 995.2f, 861.7f, 1037.7f),
                        PathNode.QuadTo(821.2f, 1080.2f, 766.7f, 1103.7f),
                        PathNode.QuadTo(712.2f, 1127.2f, 651.2f, 1127.2f),
                        PathNode.QuadTo(573.2f, 1127.2f, 506.7f, 1089.2f),
                        PathNode.QuadTo(440.2f, 1051.2f, 399.7f, 986.7f),
                        PathNode.QuadTo(359.2f, 922.2f, 356.2f, 845.2f),
                        PathNode.QuadTo(356.2f, 835.2f, 356.2f, 824.2f),
                        PathNode.VerticalTo(813.2f),
                        PathNode.QuadTo(289.2f, 806.2f, 234.2f, 767.2f),
                        PathNode.QuadTo(179.2f, 728.2f, 147.2f, 667.2f),
                        PathNode.QuadTo(115.2f, 606.2f, 115.2f, 535.2f),
                        PathNode.QuadTo(115.2f, 460.2f, 151.7f, 395.7f),
                        PathNode.QuadTo(188.2f, 331.2f, 250.2f, 293.2f),
                        PathNode.QuadTo(312.2f, 255.2f, 384.2f, 255.2f),
                        PathNode.HorizontalTo(964.2f),
                        PathNode.QuadTo(1046.2f, 255.2f, 1115.7f, 296.2f),
                        PathNode.QuadTo(1185.2f, 337.2f, 1226.2f, 406.7f),
                        PathNode.QuadTo(1267.2f, 476.2f, 1267.2f, 558.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _cloudfillDemibold!!
    }

private var _cloudfillDemibold: ImageVector? = null
