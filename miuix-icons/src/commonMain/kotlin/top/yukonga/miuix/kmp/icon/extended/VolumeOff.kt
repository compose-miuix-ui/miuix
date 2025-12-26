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

val MiuixIcons.VolumeOff: ImageVector
    get() = MiuixIcons.Regular.VolumeOff

val MiuixIcons.Light.VolumeOff: ImageVector
    get() {
        if (_volumeoffLight != null) return _volumeoffLight!!
        _volumeoffLight = ImageVector.Builder(
            name = "VolumeOff.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1158.5066666666667f,
            viewportHeight = 1158.5066666666667f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -85.51589743589739f, translationY = 951.7422222222223f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1092.0f, 43.0f),
                        PathNode.LineTo(314.0f, 822.0f),
                        PathNode.QuadTo(307.0f, 829.0f, 299.5f, 829.5f),
                        PathNode.QuadTo(292.0f, 830.0f, 285.0f, 824.0f),
                        PathNode.LineTo(271.0f, 811.0f),
                        PathNode.QuadTo(264.0f, 805.0f, 264.5f, 797.5f),
                        PathNode.QuadTo(265.0f, 790.0f, 272.0f, 782.0f),
                        PathNode.LineTo(1053.0f, 1.0f),
                        PathNode.QuadTo(1060.0f, -5.0f, 1067.0f, -6.0f),
                        PathNode.QuadTo(1074.0f, -7.0f, 1081.0f, 1.0f),
                        PathNode.LineTo(1095.0f, 14.0f),
                        PathNode.QuadTo(1101.0f, 21.0f, 1100.5f, 28.0f),
                        PathNode.QuadTo(1100.0f, 35.0f, 1092.0f, 43.0f),
                        PathNode.Close,
                        PathNode.MoveTo(899.0f, -98.0f),
                        PathNode.QuadTo(905.0f, -91.0f, 906.0f, -79.5f),
                        PathNode.QuadTo(907.0f, -68.0f, 907.0f, -40.0f),
                        PathNode.VerticalTo(15.0f),
                        PathNode.LineTo(855.0f, 67.0f),
                        PathNode.QuadTo(853.0f, 70.0f, 851.0f, 68.5f),
                        PathNode.QuadTo(849.0f, 67.0f, 849.0f, 64.0f),
                        PathNode.VerticalTo(-24.0f),
                        PathNode.LineTo(683.0f, 110.0f),
                        PathNode.LineTo(669.0f, 121.0f),
                        PathNode.QuadTo(646.0f, 139.0f, 639.0f, 143.0f),
                        PathNode.QuadTo(626.0f, 152.0f, 605.0f, 155.0f),
                        PathNode.QuadTo(586.0f, 158.0f, 548.0f, 158.0f),
                        PathNode.HorizontalTo(393.0f),
                        PathNode.QuadTo(363.0f, 158.0f, 350.0f, 159.0f),
                        PathNode.QuadTo(337.0f, 160.0f, 327.0f, 166.0f),
                        PathNode.QuadTo(306.0f, 177.0f, 297.0f, 196.0f),
                        PathNode.QuadTo(291.0f, 207.0f, 289.5f, 220.5f),
                        PathNode.QuadTo(288.0f, 234.0f, 288.0f, 262.0f),
                        PathNode.VerticalTo(482.0f),
                        PathNode.QuadTo(288.0f, 511.0f, 289.0f, 524.5f),
                        PathNode.QuadTo(290.0f, 538.0f, 295.0f, 549.0f),
                        PathNode.QuadTo(305.0f, 567.0f, 326.0f, 578.0f),
                        PathNode.QuadTo(335.0f, 584.0f, 346.0f, 586.0f),
                        PathNode.LineTo(301.0f, 630.0f),
                        PathNode.QuadTo(277.0f, 618.0f, 264.5f, 604.5f),
                        PathNode.QuadTo(252.0f, 591.0f, 244.0f, 575.0f),
                        PathNode.QuadTo(234.0f, 555.0f, 231.5f, 526.0f),
                        PathNode.QuadTo(229.0f, 497.0f, 229.0f, 431.0f),
                        PathNode.VerticalTo(313.0f),
                        PathNode.QuadTo(229.0f, 245.0f, 231.5f, 217.0f),
                        PathNode.QuadTo(234.0f, 189.0f, 244.0f, 170.0f),
                        PathNode.QuadTo(263.0f, 131.0f, 300.0f, 113.0f),
                        PathNode.QuadTo(319.0f, 103.0f, 347.5f, 100.5f),
                        PathNode.QuadTo(376.0f, 98.0f, 445.0f, 98.0f),
                        PathNode.HorizontalTo(563.0f),
                        PathNode.QuadTo(588.0f, 98.0f, 603.5f, 92.5f),
                        PathNode.QuadTo(619.0f, 87.0f, 637.0f, 72.0f),
                        PathNode.LineTo(820.0f, -77.0f),
                        PathNode.LineTo(829.0f, -84.0f),
                        PathNode.QuadTo(844.0f, -95.0f, 855.0f, -102.5f),
                        PathNode.QuadTo(866.0f, -110.0f, 873.0f, -110.0f),
                        PathNode.QuadTo(881.0f, -111.0f, 888.0f, -107.5f),
                        PathNode.QuadTo(895.0f, -104.0f, 899.0f, -98.0f),
                        PathNode.Close,
                        PathNode.MoveTo(900.0f, 842.0f),
                        PathNode.QuadTo(895.0f, 848.0f, 888.0f, 852.0f),
                        PathNode.QuadTo(881.0f, 856.0f, 872.0f, 855.0f),
                        PathNode.QuadTo(865.0f, 854.0f, 854.5f, 847.0f),
                        PathNode.QuadTo(844.0f, 840.0f, 821.0f, 822.0f),
                        PathNode.QuadTo(818.0f, 819.0f, 814.0f, 816.0f),
                        PathNode.QuadTo(810.0f, 813.0f, 806.0f, 810.0f),
                        PathNode.LineTo(613.0f, 658.0f),
                        PathNode.LineTo(656.0f, 615.0f),
                        PathNode.LineTo(849.0f, 770.0f),
                        PathNode.VerticalTo(418.0f),
                        PathNode.LineTo(907.0f, 359.0f),
                        PathNode.VerticalTo(762.0f),
                        PathNode.QuadTo(907.0f, 806.0f, 906.0f, 821.0f),
                        PathNode.QuadTo(905.0f, 836.0f, 900.0f, 842.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _volumeoffLight!!
    }

private var _volumeoffLight: ImageVector? = null

val MiuixIcons.Regular.VolumeOff: ImageVector
    get() {
        if (_volumeoffRegular != null) return _volumeoffRegular!!
        _volumeoffRegular = ImageVector.Builder(
            name = "VolumeOff.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1191.6f,
            viewportHeight = 1191.6f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -68.74545454545455f, translationY = 968.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1100.0f, 55.0f),
                        PathNode.LineTo(328.0f, 828.0f),
                        PathNode.QuadTo(316.0f, 840.0f, 305.5f, 840.5f),
                        PathNode.QuadTo(295.0f, 841.0f, 285.0f, 832.0f),
                        PathNode.LineTo(265.0f, 813.0f),
                        PathNode.QuadTo(255.0f, 804.0f, 255.5f, 792.5f),
                        PathNode.QuadTo(256.0f, 781.0f, 267.0f, 770.0f),
                        PathNode.LineTo(1043.0f, -6.0f),
                        PathNode.QuadTo(1052.0f, -15.0f, 1062.5f, -16.5f),
                        PathNode.QuadTo(1073.0f, -18.0f, 1084.0f, -6.0f),
                        PathNode.LineTo(1103.0f, 13.0f),
                        PathNode.QuadTo(1113.0f, 23.0f, 1112.0f, 33.5f),
                        PathNode.QuadTo(1111.0f, 44.0f, 1100.0f, 55.0f),
                        PathNode.Close,
                        PathNode.MoveTo(911.0f, -106.0f),
                        PathNode.QuadTo(919.0f, -96.0f, 920.5f, -78.5f),
                        PathNode.QuadTo(922.0f, -61.0f, 922.0f, -17.0f),
                        PathNode.VerticalTo(-3.0f),
                        PathNode.LineTo(844.0f, 76.0f),
                        PathNode.QuadTo(841.0f, 79.0f, 839.0f, 77.5f),
                        PathNode.QuadTo(837.0f, 76.0f, 837.0f, 72.0f),
                        PathNode.VerticalTo(4.0f),
                        PathNode.LineTo(693.0f, 120.0f),
                        PathNode.LineTo(677.0f, 133.0f),
                        PathNode.QuadTo(659.0f, 149.0f, 647.0f, 155.0f),
                        PathNode.QuadTo(631.0f, 164.0f, 609.0f, 168.0f),
                        PathNode.QuadTo(588.0f, 171.0f, 549.0f, 171.0f),
                        PathNode.HorizontalTo(394.0f),
                        PathNode.QuadTo(366.0f, 171.0f, 354.5f, 172.0f),
                        PathNode.QuadTo(343.0f, 173.0f, 335.0f, 178.0f),
                        PathNode.QuadTo(317.0f, 186.0f, 310.0f, 202.0f),
                        PathNode.QuadTo(305.0f, 211.0f, 304.0f, 222.5f),
                        PathNode.QuadTo(303.0f, 234.0f, 303.0f, 262.0f),
                        PathNode.VerticalTo(482.0f),
                        PathNode.QuadTo(303.0f, 504.0f, 304.0f, 519.0f),
                        PathNode.QuadTo(305.0f, 534.0f, 308.0f, 543.0f),
                        PathNode.QuadTo(317.0f, 559.0f, 335.0f, 567.0f),
                        PathNode.QuadTo(343.0f, 570.0f, 351.0f, 573.0f),
                        PathNode.LineTo(285.0f, 638.0f),
                        PathNode.QuadTo(268.0f, 627.0f, 255.0f, 613.0f),
                        PathNode.QuadTo(242.0f, 599.0f, 233.0f, 581.0f),
                        PathNode.QuadTo(222.0f, 560.0f, 219.5f, 530.5f),
                        PathNode.QuadTo(217.0f, 501.0f, 217.0f, 431.0f),
                        PathNode.VerticalTo(313.0f),
                        PathNode.QuadTo(217.0f, 244.0f, 219.5f, 214.5f),
                        PathNode.QuadTo(222.0f, 185.0f, 234.0f, 164.0f),
                        PathNode.QuadTo(256.0f, 119.0f, 296.0f, 101.0f),
                        PathNode.QuadTo(317.0f, 90.0f, 346.5f, 87.5f),
                        PathNode.QuadTo(376.0f, 85.0f, 446.0f, 85.0f),
                        PathNode.HorizontalTo(564.0f),
                        PathNode.QuadTo(585.0f, 85.0f, 603.0f, 77.5f),
                        PathNode.QuadTo(621.0f, 70.0f, 637.0f, 56.0f),
                        PathNode.LineTo(799.0f, -76.0f),
                        PathNode.LineTo(815.0f, -88.0f),
                        PathNode.QuadTo(839.0f, -108.0f, 851.5f, -116.0f),
                        PathNode.QuadTo(864.0f, -124.0f, 874.0f, -124.0f),
                        PathNode.QuadTo(885.0f, -124.0f, 895.0f, -119.0f),
                        PathNode.QuadTo(905.0f, -114.0f, 911.0f, -106.0f),
                        PathNode.Close,
                        PathNode.MoveTo(911.0f, 851.0f),
                        PathNode.QuadTo(905.0f, 859.0f, 895.0f, 864.0f),
                        PathNode.QuadTo(885.0f, 869.0f, 874.0f, 869.0f),
                        PathNode.QuadTo(864.0f, 869.0f, 851.5f, 861.0f),
                        PathNode.QuadTo(839.0f, 853.0f, 815.0f, 833.0f),
                        PathNode.QuadTo(811.0f, 831.0f, 807.0f, 827.5f),
                        PathNode.QuadTo(803.0f, 824.0f, 799.0f, 821.0f),
                        PathNode.LineTo(604.0f, 666.0f),
                        PathNode.LineTo(666.0f, 605.0f),
                        PathNode.LineTo(837.0f, 742.0f),
                        PathNode.VerticalTo(434.0f),
                        PathNode.LineTo(922.0f, 348.0f),
                        PathNode.VerticalTo(762.0f),
                        PathNode.QuadTo(922.0f, 806.0f, 920.5f, 824.5f),
                        PathNode.QuadTo(919.0f, 843.0f, 911.0f, 851.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _volumeoffRegular!!
    }

private var _volumeoffRegular: ImageVector? = null

val MiuixIcons.Heavy.VolumeOff: ImageVector
    get() {
        if (_volumeoffHeavy != null) return _volumeoffHeavy!!
        _volumeoffHeavy = ImageVector.Builder(
            name = "VolumeOff.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.2f,
            viewportHeight = 1225.2f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -51.93333333333328f, translationY = 985.1f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1110.0f, 65.0f),
                        PathNode.LineTo(338.0f, 838.0f),
                        PathNode.QuadTo(323.0f, 853.0f, 306.5f, 854.0f),
                        PathNode.QuadTo(290.0f, 855.0f, 275.0f, 842.0f),
                        PathNode.LineTo(256.0f, 823.0f),
                        PathNode.QuadTo(241.0f, 810.0f, 241.5f, 793.0f),
                        PathNode.QuadTo(242.0f, 776.0f, 257.0f, 760.0f),
                        PathNode.LineTo(1033.0f, -16.0f),
                        PathNode.QuadTo(1046.0f, -29.0f, 1062.5f, -30.5f),
                        PathNode.QuadTo(1079.0f, -32.0f, 1094.0f, -16.0f),
                        PathNode.LineTo(1113.0f, 3.0f),
                        PathNode.QuadTo(1127.0f, 18.0f, 1126.0f, 34.0f),
                        PathNode.QuadTo(1125.0f, 50.0f, 1110.0f, 65.0f),
                        PathNode.Close,
                        PathNode.MoveTo(922.0f, -114.0f),
                        PathNode.QuadTo(931.0f, -102.0f, 933.5f, -84.5f),
                        PathNode.QuadTo(936.0f, -67.0f, 936.0f, -37.0f),
                        PathNode.VerticalTo(-19.0f),
                        PathNode.LineTo(833.0f, 85.0f),
                        PathNode.QuadTo(829.0f, 89.0f, 826.0f, 87.0f),
                        PathNode.QuadTo(823.0f, 85.0f, 823.0f, 80.0f),
                        PathNode.VerticalTo(33.0f),
                        PathNode.LineTo(702.0f, 131.0f),
                        PathNode.LineTo(687.0f, 143.0f),
                        PathNode.QuadTo(664.0f, 161.0f, 654.0f, 167.0f),
                        PathNode.QuadTo(641.0f, 175.0f, 611.0f, 182.0f),
                        PathNode.QuadTo(589.0f, 185.0f, 549.0f, 185.0f),
                        PathNode.HorizontalTo(394.0f),
                        PathNode.QuadTo(373.0f, 185.0f, 360.0f, 186.0f),
                        PathNode.QuadTo(347.0f, 187.0f, 341.0f, 190.0f),
                        PathNode.QuadTo(328.0f, 196.0f, 322.0f, 208.0f),
                        PathNode.QuadTo(319.0f, 215.0f, 318.0f, 228.0f),
                        PathNode.QuadTo(317.0f, 241.0f, 317.0f, 262.0f),
                        PathNode.VerticalTo(482.0f),
                        PathNode.QuadTo(317.0f, 503.0f, 317.5f, 516.0f),
                        PathNode.QuadTo(318.0f, 529.0f, 321.0f, 537.0f),
                        PathNode.QuadTo(327.0f, 548.0f, 340.0f, 555.0f),
                        PathNode.QuadTo(342.0f, 557.0f, 353.0f, 561.0f),
                        PathNode.LineTo(268.0f, 643.0f),
                        PathNode.QuadTo(254.0f, 634.0f, 242.0f, 619.5f),
                        PathNode.QuadTo(230.0f, 605.0f, 221.0f, 587.0f),
                        PathNode.QuadTo(209.0f, 564.0f, 206.0f, 533.0f),
                        PathNode.QuadTo(203.0f, 502.0f, 203.0f, 431.0f),
                        PathNode.VerticalTo(313.0f),
                        PathNode.QuadTo(203.0f, 243.0f, 206.0f, 212.0f),
                        PathNode.QuadTo(209.0f, 181.0f, 222.0f, 158.0f),
                        PathNode.QuadTo(246.0f, 109.0f, 290.0f, 89.0f),
                        PathNode.QuadTo(313.0f, 77.0f, 344.0f, 74.0f),
                        PathNode.QuadTo(375.0f, 71.0f, 446.0f, 71.0f),
                        PathNode.HorizontalTo(564.0f),
                        PathNode.QuadTo(583.0f, 71.0f, 598.5f, 64.5f),
                        PathNode.QuadTo(614.0f, 58.0f, 628.0f, 45.0f),
                        PathNode.LineTo(790.0f, -87.0f),
                        PathNode.LineTo(806.0f, -99.0f),
                        PathNode.QuadTo(834.0f, -121.0f, 848.0f, -129.5f),
                        PathNode.QuadTo(862.0f, -138.0f, 874.0f, -138.0f),
                        PathNode.QuadTo(888.0f, -138.0f, 901.0f, -131.5f),
                        PathNode.QuadTo(914.0f, -125.0f, 922.0f, -114.0f),
                        PathNode.Close,
                        PathNode.MoveTo(922.0f, 860.0f),
                        PathNode.QuadTo(914.0f, 871.0f, 901.0f, 877.0f),
                        PathNode.QuadTo(888.0f, 883.0f, 874.0f, 883.0f),
                        PathNode.QuadTo(862.0f, 883.0f, 848.0f, 874.5f),
                        PathNode.QuadTo(834.0f, 866.0f, 806.0f, 844.0f),
                        PathNode.QuadTo(801.0f, 840.0f, 798.0f, 838.0f),
                        PathNode.QuadTo(795.0f, 836.0f, 790.0f, 832.0f),
                        PathNode.LineTo(600.0f, 683.0f),
                        PathNode.LineTo(682.0f, 602.0f),
                        PathNode.LineTo(823.0f, 714.0f),
                        PathNode.VerticalTo(460.0f),
                        PathNode.LineTo(936.0f, 346.0f),
                        PathNode.VerticalTo(762.0f),
                        PathNode.QuadTo(936.0f, 810.0f, 934.0f, 829.5f),
                        PathNode.QuadTo(932.0f, 849.0f, 922.0f, 860.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _volumeoffHeavy!!
    }

private var _volumeoffHeavy: ImageVector? = null
