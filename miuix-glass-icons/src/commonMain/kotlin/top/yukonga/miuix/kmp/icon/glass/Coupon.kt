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

val MiuixIcons.Glass.Coupon: ImageVector
    get() = MiuixIcons.Glass.Regular.Coupon

val MiuixIcons.Glass.Light.Coupon: ImageVector
    get() {
        if (_couponLight != null) return _couponLight!!
        _couponLight = ImageVector.Builder(
            name = "Coupon.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1017.2f, 185.4f),
                        PathNode.QuadTo(1055.7f, 203.2f, 1073.4f, 241.7f),
                        PathNode.QuadTo(1083.5f, 260.0f, 1085.8f, 288.4f),
                        PathNode.QuadTo(1088.1f, 316.9f, 1088.1f, 385.4f),
                        PathNode.LineTo(1088.1f, 432.8f),
                        PathNode.QuadTo(1088.1f, 436.9f, 1086.0f, 438.9f),
                        PathNode.QuadTo(1084.0f, 441.0f, 1079.9f, 441.4f),
                        PathNode.QuadTo(1032.5f, 447.4f, 1000.8f, 484.3f),
                        PathNode.QuadTo(969.1f, 521.2f, 969.1f, 571.4f),
                        PathNode.QuadTo(969.1f, 619.7f, 1000.8f, 656.8f),
                        PathNode.QuadTo(1032.5f, 694.0f, 1079.9f, 702.0f),
                        PathNode.QuadTo(1084.0f, 702.4f, 1086.0f, 704.5f),
                        PathNode.QuadTo(1088.1f, 706.5f, 1088.1f, 710.7f),
                        PathNode.LineTo(1088.1f, 757.4f),
                        PathNode.QuadTo(1088.1f, 826.0f, 1085.8f, 853.7f),
                        PathNode.QuadTo(1083.5f, 881.4f, 1073.4f, 900.2f),
                        PathNode.QuadTo(1055.7f, 937.7f, 1017.2f, 957.4f),
                        PathNode.QuadTo(998.4f, 967.5f, 970.3f, 969.8f),
                        PathNode.QuadTo(942.1f, 972.1f, 873.4f, 972.1f),
                        PathNode.LineTo(269.4f, 972.1f),
                        PathNode.QuadTo(201.3f, 972.1f, 173.4f, 969.8f),
                        PathNode.QuadTo(145.4f, 967.5f, 126.7f, 957.4f),
                        PathNode.QuadTo(107.4f, 947.8f, 93.0f, 933.3f),
                        PathNode.QuadTo(78.5f, 918.9f, 69.4f, 900.2f),
                        PathNode.QuadTo(59.3f, 881.4f, 57.0f, 853.7f),
                        PathNode.QuadTo(54.8f, 826.0f, 54.8f, 757.4f),
                        PathNode.LineTo(54.8f, 710.7f),
                        PathNode.QuadTo(54.8f, 706.5f, 56.8f, 704.5f),
                        PathNode.QuadTo(58.9f, 702.4f, 63.0f, 702.0f),
                        PathNode.QuadTo(110.3f, 694.0f, 142.0f, 656.8f),
                        PathNode.QuadTo(173.8f, 619.7f, 173.8f, 571.4f),
                        PathNode.QuadTo(173.8f, 521.2f, 142.0f, 484.3f),
                        PathNode.QuadTo(110.3f, 447.4f, 63.0f, 441.4f),
                        PathNode.QuadTo(58.9f, 441.0f, 56.8f, 438.9f),
                        PathNode.QuadTo(54.8f, 436.9f, 54.8f, 432.8f),
                        PathNode.LineTo(54.8f, 385.4f),
                        PathNode.QuadTo(54.8f, 316.9f, 57.0f, 288.4f),
                        PathNode.QuadTo(59.3f, 260.0f, 69.4f, 241.7f),
                        PathNode.QuadTo(78.1f, 223.4f, 93.0f, 209.0f),
                        PathNode.QuadTo(108.0f, 194.5f, 126.7f, 185.4f),
                        PathNode.QuadTo(145.4f, 175.3f, 173.4f, 173.0f),
                        PathNode.QuadTo(201.3f, 170.8f, 269.4f, 170.8f),
                        PathNode.LineTo(873.4f, 170.8f),
                        PathNode.QuadTo(942.5f, 170.8f, 970.7f, 173.0f),
                        PathNode.QuadTo(998.9f, 175.3f, 1017.2f, 185.4f),
                        PathNode.Close,
                        PathNode.MoveTo(744.3f, 396.3f),
                        PathNode.LineTo(744.3f, 744.4f),
                        PathNode.QuadTo(744.3f, 753.0f, 748.3f, 757.2f),
                        PathNode.QuadTo(752.2f, 761.4f, 761.3f, 761.4f),
                        PathNode.LineTo(776.5f, 761.4f),
                        PathNode.QuadTo(786.2f, 761.4f, 789.9f, 757.2f),
                        PathNode.QuadTo(793.5f, 753.0f, 793.5f, 744.4f),
                        PathNode.LineTo(793.5f, 396.3f),
                        PathNode.QuadTo(793.5f, 388.9f, 788.8f, 384.7f),
                        PathNode.QuadTo(784.0f, 380.4f, 776.5f, 380.4f),
                        PathNode.LineTo(761.3f, 380.4f),
                        PathNode.QuadTo(752.8f, 380.4f, 748.5f, 384.4f),
                        PathNode.QuadTo(744.3f, 388.3f, 744.3f, 396.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _couponLight!!
    }

private var _couponLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Coupon: ImageVector
    get() {
        if (_couponNormal != null) return _couponNormal!!
        _couponNormal = ImageVector.Builder(
            name = "Coupon.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1021.5f, 177.2f),
                        PathNode.QuadTo(1062.4f, 196.5f, 1081.7f, 237.4f),
                        PathNode.QuadTo(1092.4f, 257.5f, 1094.8f, 287.1f),
                        PathNode.QuadTo(1097.3f, 316.6f, 1097.3f, 385.4f),
                        PathNode.LineTo(1097.3f, 440.1f),
                        PathNode.QuadTo(1097.3f, 444.8f, 1094.9f, 447.2f),
                        PathNode.QuadTo(1092.5f, 449.5f, 1087.8f, 449.7f),
                        PathNode.QuadTo(1041.4f, 452.9f, 1009.8f, 487.8f),
                        PathNode.QuadTo(978.3f, 522.7f, 978.3f, 571.4f),
                        PathNode.QuadTo(978.3f, 618.1f, 1009.8f, 653.5f),
                        PathNode.QuadTo(1041.4f, 688.8f, 1087.8f, 694.0f),
                        PathNode.QuadTo(1092.5f, 694.2f, 1094.9f, 696.5f),
                        PathNode.QuadTo(1097.3f, 698.9f, 1097.3f, 703.6f),
                        PathNode.LineTo(1097.3f, 757.4f),
                        PathNode.QuadTo(1097.3f, 826.3f, 1094.8f, 855.2f),
                        PathNode.QuadTo(1092.4f, 884.2f, 1081.7f, 904.5f),
                        PathNode.QuadTo(1062.4f, 944.4f, 1021.5f, 965.7f),
                        PathNode.QuadTo(1001.2f, 976.4f, 972.1f, 978.8f),
                        PathNode.QuadTo(943.0f, 981.3f, 873.4f, 981.3f),
                        PathNode.LineTo(269.4f, 981.3f),
                        PathNode.QuadTo(200.7f, 981.3f, 171.7f, 978.8f),
                        PathNode.QuadTo(142.7f, 976.4f, 122.4f, 965.7f),
                        PathNode.QuadTo(101.9f, 955.1f, 86.4f, 939.6f),
                        PathNode.QuadTo(70.9f, 924.1f, 61.2f, 904.5f),
                        PathNode.QuadTo(50.5f, 884.2f, 48.0f, 855.2f),
                        PathNode.QuadTo(45.6f, 826.3f, 45.6f, 757.4f),
                        PathNode.LineTo(45.6f, 703.6f),
                        PathNode.QuadTo(45.6f, 698.9f, 48.0f, 696.5f),
                        PathNode.QuadTo(50.3f, 694.2f, 55.0f, 694.0f),
                        PathNode.QuadTo(101.5f, 688.8f, 133.0f, 653.5f),
                        PathNode.QuadTo(164.6f, 618.1f, 164.6f, 571.4f),
                        PathNode.QuadTo(164.6f, 522.7f, 133.0f, 487.8f),
                        PathNode.QuadTo(101.5f, 452.9f, 55.0f, 449.7f),
                        PathNode.QuadTo(50.3f, 449.5f, 48.0f, 447.2f),
                        PathNode.QuadTo(45.6f, 444.8f, 45.6f, 440.1f),
                        PathNode.LineTo(45.6f, 385.4f),
                        PathNode.QuadTo(45.6f, 316.6f, 48.0f, 287.1f),
                        PathNode.QuadTo(50.5f, 257.5f, 61.2f, 237.4f),
                        PathNode.QuadTo(70.8f, 217.9f, 86.8f, 202.4f),
                        PathNode.QuadTo(102.8f, 186.9f, 122.4f, 177.2f),
                        PathNode.QuadTo(142.7f, 166.5f, 171.7f, 164.0f),
                        PathNode.QuadTo(200.7f, 161.6f, 269.4f, 161.6f),
                        PathNode.LineTo(873.4f, 161.6f),
                        PathNode.QuadTo(943.2f, 161.6f, 972.2f, 164.0f),
                        PathNode.QuadTo(1001.3f, 166.5f, 1021.5f, 177.2f),
                        PathNode.Close,
                        PathNode.MoveTo(738.2f, 401.2f),
                        PathNode.LineTo(738.2f, 738.9f),
                        PathNode.QuadTo(738.2f, 750.5f, 743.2f, 756.0f),
                        PathNode.QuadTo(748.2f, 761.4f, 760.7f, 761.4f),
                        PathNode.LineTo(777.2f, 761.4f),
                        PathNode.QuadTo(790.5f, 761.4f, 795.1f, 756.0f),
                        PathNode.QuadTo(799.7f, 750.5f, 799.7f, 738.9f),
                        PathNode.LineTo(799.7f, 401.2f),
                        PathNode.QuadTo(799.7f, 391.3f, 793.3f, 385.9f),
                        PathNode.QuadTo(787.0f, 380.4f, 777.2f, 380.4f),
                        PathNode.LineTo(760.7f, 380.4f),
                        PathNode.QuadTo(749.1f, 380.4f, 743.7f, 385.4f),
                        PathNode.QuadTo(738.2f, 390.5f, 738.2f, 401.2f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _couponNormal!!
    }

private var _couponNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Coupon: ImageVector
    get() {
        if (_couponRegular != null) return _couponRegular!!
        _couponRegular = ImageVector.Builder(
            name = "Coupon.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1023.4f, 173.4f),
                        PathNode.QuadTo(1065.4f, 193.4f, 1085.4f, 235.4f),
                        PathNode.QuadTo(1096.4f, 256.4f, 1098.9f, 286.4f),
                        PathNode.QuadTo(1101.4f, 316.4f, 1101.4f, 385.4f),
                        PathNode.LineTo(1101.4f, 443.4f),
                        PathNode.QuadTo(1101.4f, 448.4f, 1098.9f, 450.9f),
                        PathNode.QuadTo(1096.4f, 453.4f, 1091.4f, 453.4f),
                        PathNode.QuadTo(1045.4f, 455.4f, 1013.9f, 489.4f),
                        PathNode.QuadTo(982.4f, 523.4f, 982.4f, 571.4f),
                        PathNode.QuadTo(982.4f, 617.4f, 1013.9f, 651.9f),
                        PathNode.QuadTo(1045.4f, 686.4f, 1091.4f, 690.4f),
                        PathNode.QuadTo(1096.4f, 690.4f, 1098.9f, 692.9f),
                        PathNode.QuadTo(1101.4f, 695.4f, 1101.4f, 700.4f),
                        PathNode.LineTo(1101.4f, 757.4f),
                        PathNode.QuadTo(1101.4f, 826.4f, 1098.9f, 855.9f),
                        PathNode.QuadTo(1096.4f, 885.4f, 1085.4f, 906.4f),
                        PathNode.QuadTo(1065.4f, 947.4f, 1023.4f, 969.4f),
                        PathNode.QuadTo(1002.4f, 980.4f, 972.9f, 982.9f),
                        PathNode.QuadTo(943.4f, 985.4f, 873.4f, 985.4f),
                        PathNode.LineTo(269.4f, 985.4f),
                        PathNode.QuadTo(200.4f, 985.4f, 170.9f, 982.9f),
                        PathNode.QuadTo(141.4f, 980.4f, 120.4f, 969.4f),
                        PathNode.QuadTo(99.4f, 958.4f, 83.4f, 942.4f),
                        PathNode.QuadTo(67.4f, 926.4f, 57.4f, 906.4f),
                        PathNode.QuadTo(46.4f, 885.4f, 43.9f, 855.9f),
                        PathNode.QuadTo(41.4f, 826.4f, 41.4f, 757.4f),
                        PathNode.LineTo(41.4f, 700.4f),
                        PathNode.QuadTo(41.4f, 695.4f, 43.9f, 692.9f),
                        PathNode.QuadTo(46.4f, 690.4f, 51.4f, 690.4f),
                        PathNode.QuadTo(97.4f, 686.4f, 128.9f, 651.9f),
                        PathNode.QuadTo(160.4f, 617.4f, 160.4f, 571.4f),
                        PathNode.QuadTo(160.4f, 523.4f, 128.9f, 489.4f),
                        PathNode.QuadTo(97.4f, 455.4f, 51.4f, 453.4f),
                        PathNode.QuadTo(46.4f, 453.4f, 43.9f, 450.9f),
                        PathNode.QuadTo(41.4f, 448.4f, 41.4f, 443.4f),
                        PathNode.LineTo(41.4f, 385.4f),
                        PathNode.QuadTo(41.4f, 316.4f, 43.9f, 286.4f),
                        PathNode.QuadTo(46.4f, 256.4f, 57.4f, 235.4f),
                        PathNode.QuadTo(67.4f, 215.4f, 83.9f, 199.4f),
                        PathNode.QuadTo(100.4f, 183.4f, 120.4f, 173.4f),
                        PathNode.QuadTo(141.4f, 162.4f, 170.9f, 159.9f),
                        PathNode.QuadTo(200.4f, 157.4f, 269.4f, 157.4f),
                        PathNode.LineTo(873.4f, 157.4f),
                        PathNode.QuadTo(943.4f, 157.4f, 972.9f, 159.9f),
                        PathNode.QuadTo(1002.4f, 162.4f, 1023.4f, 173.4f),
                        PathNode.Close,
                        PathNode.MoveTo(735.4f, 403.4f),
                        PathNode.LineTo(735.4f, 736.4f),
                        PathNode.QuadTo(735.4f, 749.4f, 740.9f, 755.4f),
                        PathNode.QuadTo(746.4f, 761.4f, 760.4f, 761.4f),
                        PathNode.LineTo(777.4f, 761.4f),
                        PathNode.QuadTo(792.4f, 761.4f, 797.4f, 755.4f),
                        PathNode.QuadTo(802.4f, 749.4f, 802.4f, 736.4f),
                        PathNode.LineTo(802.4f, 403.4f),
                        PathNode.QuadTo(802.4f, 392.4f, 795.4f, 386.4f),
                        PathNode.QuadTo(788.4f, 380.4f, 777.4f, 380.4f),
                        PathNode.LineTo(760.4f, 380.4f),
                        PathNode.QuadTo(747.4f, 380.4f, 741.4f, 385.9f),
                        PathNode.QuadTo(735.4f, 391.4f, 735.4f, 403.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _couponRegular!!
    }

private var _couponRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Coupon: ImageVector
    get() {
        if (_couponMedium != null) return _couponMedium!!
        _couponMedium = ImageVector.Builder(
            name = "Coupon.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1026.9f, 166.7f),
                        PathNode.QuadTo(1070.8f, 188.0f, 1092.2f, 231.9f),
                        PathNode.QuadTo(1103.7f, 254.0f, 1106.4f, 284.9f),
                        PathNode.QuadTo(1109.0f, 315.9f, 1109.0f, 385.4f),
                        PathNode.LineTo(1109.0f, 446.7f),
                        PathNode.QuadTo(1109.0f, 454.1f, 1105.0f, 457.6f),
                        PathNode.QuadTo(1101.0f, 461.0f, 1093.1f, 461.0f),
                        PathNode.QuadTo(1049.8f, 462.2f, 1019.9f, 494.2f),
                        PathNode.QuadTo(990.0f, 526.1f, 990.0f, 571.4f),
                        PathNode.QuadTo(990.0f, 615.0f, 1020.0f, 647.5f),
                        PathNode.QuadTo(1050.0f, 679.9f, 1093.3f, 682.9f),
                        PathNode.QuadTo(1101.3f, 683.1f, 1105.1f, 686.6f),
                        PathNode.QuadTo(1109.0f, 690.0f, 1109.0f, 697.5f),
                        PathNode.LineTo(1109.0f, 757.4f),
                        PathNode.QuadTo(1109.0f, 827.0f, 1106.4f, 857.4f),
                        PathNode.QuadTo(1103.7f, 887.9f, 1092.2f, 909.9f),
                        PathNode.QuadTo(1071.1f, 953.1f, 1026.9f, 976.2f),
                        PathNode.QuadTo(1004.9f, 987.7f, 974.3f, 990.4f),
                        PathNode.QuadTo(943.7f, 993.0f, 873.4f, 993.0f),
                        PathNode.LineTo(269.4f, 993.0f),
                        PathNode.QuadTo(199.9f, 993.0f, 169.4f, 990.4f),
                        PathNode.QuadTo(139.0f, 987.7f, 116.9f, 976.2f),
                        PathNode.QuadTo(95.1f, 964.9f, 78.2f, 948.0f),
                        PathNode.QuadTo(61.2f, 931.0f, 50.7f, 909.9f),
                        PathNode.QuadTo(39.1f, 887.9f, 36.5f, 857.4f),
                        PathNode.QuadTo(33.9f, 827.0f, 33.9f, 757.4f),
                        PathNode.LineTo(33.9f, 697.5f),
                        PathNode.QuadTo(33.9f, 690.0f, 37.7f, 686.6f),
                        PathNode.QuadTo(41.6f, 683.1f, 49.5f, 682.9f),
                        PathNode.QuadTo(92.8f, 679.9f, 122.8f, 647.5f),
                        PathNode.QuadTo(152.9f, 615.0f, 152.9f, 571.4f),
                        PathNode.QuadTo(152.9f, 526.1f, 123.0f, 494.2f),
                        PathNode.QuadTo(93.1f, 462.2f, 49.8f, 461.0f),
                        PathNode.QuadTo(41.8f, 461.0f, 37.8f, 457.6f),
                        PathNode.QuadTo(33.9f, 454.1f, 33.9f, 446.7f),
                        PathNode.LineTo(33.9f, 385.4f),
                        PathNode.QuadTo(33.9f, 315.9f, 36.5f, 284.9f),
                        PathNode.QuadTo(39.1f, 254.0f, 50.7f, 231.9f),
                        PathNode.QuadTo(61.2f, 210.8f, 78.7f, 193.9f),
                        PathNode.QuadTo(96.1f, 176.9f, 116.9f, 166.7f),
                        PathNode.QuadTo(139.0f, 155.1f, 169.4f, 152.5f),
                        PathNode.QuadTo(199.9f, 149.9f, 269.4f, 149.9f),
                        PathNode.LineTo(873.4f, 149.9f),
                        PathNode.QuadTo(943.7f, 149.9f, 974.3f, 152.5f),
                        PathNode.QuadTo(1004.9f, 155.1f, 1026.9f, 166.7f),
                        PathNode.Close,
                        PathNode.MoveTo(727.3f, 408.8f),
                        PathNode.LineTo(727.3f, 731.0f),
                        PathNode.QuadTo(727.3f, 747.3f, 735.3f, 755.7f),
                        PathNode.QuadTo(743.2f, 764.1f, 760.4f, 764.1f),
                        PathNode.LineTo(777.4f, 764.1f),
                        PathNode.QuadTo(795.4f, 764.1f, 803.0f, 756.0f),
                        PathNode.QuadTo(810.5f, 747.8f, 810.5f, 731.0f),
                        PathNode.LineTo(810.5f, 408.8f),
                        PathNode.QuadTo(810.5f, 394.3f, 801.2f, 386.0f),
                        PathNode.QuadTo(791.9f, 377.7f, 777.4f, 377.7f),
                        PathNode.LineTo(760.4f, 377.7f),
                        PathNode.QuadTo(743.9f, 377.7f, 735.6f, 385.7f),
                        PathNode.QuadTo(727.3f, 393.6f, 727.3f, 408.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _couponMedium!!
    }

private var _couponMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Coupon: ImageVector
    get() {
        if (_couponDemibold != null) return _couponDemibold!!
        _couponDemibold = ImageVector.Builder(
            name = "Coupon.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1029.4f, 161.9f),
                        PathNode.QuadTo(1074.6f, 184.2f, 1096.9f, 229.5f),
                        PathNode.QuadTo(1108.8f, 252.3f, 1111.6f, 283.9f),
                        PathNode.QuadTo(1114.3f, 315.5f, 1114.3f, 385.4f),
                        PathNode.LineTo(1114.3f, 448.9f),
                        PathNode.QuadTo(1114.3f, 458.1f, 1109.3f, 462.2f),
                        PathNode.QuadTo(1104.2f, 466.3f, 1094.2f, 466.3f),
                        PathNode.QuadTo(1052.8f, 466.9f, 1024.0f, 497.5f),
                        PathNode.QuadTo(995.3f, 528.0f, 995.3f, 571.4f),
                        PathNode.QuadTo(995.3f, 613.3f, 1024.3f, 644.3f),
                        PathNode.QuadTo(1053.2f, 675.4f, 1094.6f, 677.6f),
                        PathNode.QuadTo(1104.7f, 678.0f, 1109.5f, 682.1f),
                        PathNode.QuadTo(1114.3f, 686.2f, 1114.3f, 695.4f),
                        PathNode.LineTo(1114.3f, 757.4f),
                        PathNode.QuadTo(1114.3f, 827.3f, 1111.6f, 858.5f),
                        PathNode.QuadTo(1108.8f, 889.6f, 1096.9f, 912.4f),
                        PathNode.QuadTo(1075.1f, 957.1f, 1029.4f, 980.9f),
                        PathNode.QuadTo(1006.6f, 992.8f, 975.2f, 995.6f),
                        PathNode.QuadTo(943.9f, 998.3f, 873.4f, 998.3f),
                        PathNode.LineTo(269.4f, 998.3f),
                        PathNode.QuadTo(199.5f, 998.3f, 168.4f, 995.6f),
                        PathNode.QuadTo(137.3f, 992.8f, 114.5f, 980.9f),
                        PathNode.QuadTo(92.1f, 969.5f, 74.5f, 951.8f),
                        PathNode.QuadTo(56.9f, 934.2f, 45.9f, 912.4f),
                        PathNode.QuadTo(34.0f, 889.6f, 31.3f, 858.5f),
                        PathNode.QuadTo(28.6f, 827.3f, 28.6f, 757.4f),
                        PathNode.LineTo(28.6f, 695.4f),
                        PathNode.QuadTo(28.6f, 686.2f, 33.4f, 682.1f),
                        PathNode.QuadTo(38.2f, 678.0f, 48.2f, 677.6f),
                        PathNode.QuadTo(89.6f, 675.4f, 118.6f, 644.3f),
                        PathNode.QuadTo(147.6f, 613.3f, 147.6f, 571.4f),
                        PathNode.QuadTo(147.6f, 528.0f, 118.8f, 497.5f),
                        PathNode.QuadTo(90.1f, 466.9f, 48.7f, 466.3f),
                        PathNode.QuadTo(38.6f, 466.3f, 33.6f, 462.2f),
                        PathNode.QuadTo(28.6f, 458.1f, 28.6f, 448.9f),
                        PathNode.LineTo(28.6f, 385.4f),
                        PathNode.QuadTo(28.6f, 315.5f, 31.3f, 283.9f),
                        PathNode.QuadTo(34.0f, 252.3f, 45.9f, 229.5f),
                        PathNode.QuadTo(56.9f, 207.6f, 75.0f, 190.0f),
                        PathNode.QuadTo(93.1f, 172.4f, 114.5f, 161.9f),
                        PathNode.QuadTo(137.3f, 150.0f, 168.4f, 147.3f),
                        PathNode.QuadTo(199.5f, 144.6f, 269.4f, 144.6f),
                        PathNode.LineTo(873.4f, 144.6f),
                        PathNode.QuadTo(943.9f, 144.6f, 975.2f, 147.3f),
                        PathNode.QuadTo(1006.6f, 150.0f, 1029.4f, 161.9f),
                        PathNode.Close,
                        PathNode.MoveTo(721.6f, 412.6f),
                        PathNode.LineTo(721.6f, 727.2f),
                        PathNode.QuadTo(721.6f, 745.8f, 731.3f, 755.9f),
                        PathNode.QuadTo(740.9f, 766.0f, 760.4f, 766.0f),
                        PathNode.LineTo(777.4f, 766.0f),
                        PathNode.QuadTo(797.5f, 766.0f, 806.8f, 756.3f),
                        PathNode.QuadTo(816.2f, 746.7f, 816.2f, 727.2f),
                        PathNode.LineTo(816.2f, 412.6f),
                        PathNode.QuadTo(816.2f, 395.6f, 805.3f, 385.7f),
                        PathNode.QuadTo(794.4f, 375.8f, 777.4f, 375.8f),
                        PathNode.LineTo(760.4f, 375.8f),
                        PathNode.QuadTo(741.5f, 375.8f, 731.5f, 385.5f),
                        PathNode.QuadTo(721.6f, 395.1f, 721.6f, 412.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _couponDemibold!!
    }

private var _couponDemibold: ImageVector? = null
