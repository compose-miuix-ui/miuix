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

val MiuixIcons.Clear: ImageVector
    get() = MiuixIcons.Regular.Clear

val MiuixIcons.Light.Clear: ImageVector
    get() {
        if (_clearLight != null) return _clearLight!!
        _clearLight = ImageVector.Builder(
            name = "Clear.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1128.8999999999999f,
            viewportHeight = 1128.8999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -95.05000000000007f, translationY = 929.8249999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(733.0f, 89.0f),
                PathNode.LineTo(1078.0f, 434.0f),
                PathNode.QuadTo(1107.0f, 463.0f, 1107.0f, 502.5f),
                PathNode.QuadTo(1107.0f, 542.0f, 1078.0f, 570.0f),
                PathNode.LineTo(843.0f, 807.0f),
                PathNode.QuadTo(824.0f, 826.0f, 799.0f, 832.5f),
                PathNode.QuadTo(774.0f, 839.0f, 749.5f, 832.5f),
                PathNode.QuadTo(725.0f, 826.0f, 706.0f, 807.0f),
                PathNode.LineTo(255.0f, 356.0f),
                PathNode.QuadTo(236.0f, 337.0f, 229.0f, 312.5f),
                PathNode.QuadTo(222.0f, 288.0f, 229.0f, 264.0f),
                PathNode.QuadTo(236.0f, 240.0f, 255.0f, 221.0f),
                PathNode.LineTo(387.0f, 89.0f),
                PathNode.QuadTo(401.0f, 76.0f, 418.5f, 68.5f),
                PathNode.QuadTo(436.0f, 61.0f, 455.0f, 61.0f),
                PathNode.HorizontalTo(666.0f),
                PathNode.QuadTo(685.0f, 61.0f, 701.5f, 67.5f),
                PathNode.QuadTo(718.0f, 74.0f, 733.0f, 89.0f),
                PathNode.Close,
                PathNode.MoveTo(1106.0f, -88.0f),
                PathNode.VerticalTo(-64.0f),
                PathNode.QuadTo(1106.0f, -57.0f, 1100.5f, -52.0f),
                PathNode.QuadTo(1095.0f, -47.0f, 1088.0f, -47.0f),
                PathNode.HorizontalTo(230.0f),
                PathNode.QuadTo(222.0f, -47.0f, 217.0f, -52.0f),
                PathNode.QuadTo(212.0f, -57.0f, 212.0f, -64.0f),
                PathNode.VerticalTo(-88.0f),
                PathNode.QuadTo(212.0f, -96.0f, 217.0f, -100.5f),
                PathNode.QuadTo(222.0f, -105.0f, 230.0f, -105.0f),
                PathNode.HorizontalTo(1088.0f),
                PathNode.QuadTo(1096.0f, -105.0f, 1101.0f, -100.0f),
                PathNode.QuadTo(1106.0f, -95.0f, 1106.0f, -88.0f),
                PathNode.Close,
                PathNode.MoveTo(422.0f, 138.0f),
                PathNode.LineTo(312.0f, 247.0f),
                PathNode.QuadTo(291.0f, 268.0f, 291.0f, 288.0f),
                PathNode.QuadTo(291.0f, 308.0f, 312.0f, 330.0f),
                PathNode.LineTo(427.0f, 444.0f),
                PathNode.QuadTo(436.0f, 453.0f, 444.0f, 444.0f),
                PathNode.LineTo(717.0f, 172.0f),
                PathNode.QuadTo(726.0f, 163.0f, 716.0f, 155.0f),
                PathNode.LineTo(696.0f, 136.0f),
                PathNode.QuadTo(679.0f, 120.0f, 659.0f, 120.0f),
                PathNode.HorizontalTo(465.0f),
                PathNode.QuadTo(439.0f, 120.0f, 422.0f, 138.0f),
                PathNode.Close,
                PathNode.MoveTo(758.0f, 214.0f),
                PathNode.LineTo(486.0f, 486.0f),
                PathNode.QuadTo(479.0f, 493.0f, 486.0f, 502.0f),
                PathNode.LineTo(733.0f, 750.0f),
                PathNode.QuadTo(754.0f, 771.0f, 774.5f, 771.0f),
                PathNode.QuadTo(795.0f, 771.0f, 815.0f, 750.0f),
                PathNode.LineTo(1022.0f, 543.0f),
                PathNode.QuadTo(1042.0f, 524.0f, 1042.0f, 502.5f),
                PathNode.QuadTo(1042.0f, 481.0f, 1022.0f, 461.0f),
                PathNode.LineTo(773.0f, 214.0f),
                PathNode.QuadTo(769.0f, 210.0f, 765.0f, 210.0f),
                PathNode.QuadTo(761.0f, 210.0f, 758.0f, 214.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _clearLight!!
    }

private var _clearLight: ImageVector? = null

val MiuixIcons.Regular.Clear: ImageVector
    get() {
        if (_clearRegular != null) return _clearRegular!!
        _clearRegular = ImageVector.Builder(
            name = "Clear.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1159.8f,
            viewportHeight = 1159.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -85.10000000000002f, translationY = 945.15f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(741.0f, 80.0f),
                PathNode.LineTo(1086.0f, 425.0f),
                PathNode.QuadTo(1119.0f, 457.0f, 1119.0f, 502.5f),
                PathNode.QuadTo(1119.0f, 548.0f, 1086.0f, 580.0f),
                PathNode.LineTo(851.0f, 817.0f),
                PathNode.QuadTo(830.0f, 838.0f, 801.5f, 845.0f),
                PathNode.QuadTo(773.0f, 852.0f, 745.0f, 845.0f),
                PathNode.QuadTo(717.0f, 838.0f, 696.0f, 817.0f),
                PathNode.LineTo(245.0f, 366.0f),
                PathNode.QuadTo(224.0f, 345.0f, 217.0f, 317.0f),
                PathNode.QuadTo(210.0f, 289.0f, 217.0f, 261.0f),
                PathNode.QuadTo(224.0f, 233.0f, 245.0f, 212.0f),
                PathNode.LineTo(377.0f, 80.0f),
                PathNode.QuadTo(392.0f, 65.0f, 412.0f, 56.5f),
                PathNode.QuadTo(432.0f, 48.0f, 454.0f, 48.0f),
                PathNode.HorizontalTo(664.0f),
                PathNode.QuadTo(686.0f, 48.0f, 706.0f, 56.5f),
                PathNode.QuadTo(726.0f, 65.0f, 741.0f, 80.0f),
                PathNode.Close,
                PathNode.MoveTo(1105.0f, -92.0f),
                PathNode.VerticalTo(-59.0f),
                PathNode.QuadTo(1105.0f, -48.0f, 1097.0f, -40.5f),
                PathNode.QuadTo(1089.0f, -33.0f, 1078.0f, -33.0f),
                PathNode.HorizontalTo(238.0f),
                PathNode.QuadTo(226.0f, -33.0f, 218.5f, -40.5f),
                PathNode.QuadTo(211.0f, -48.0f, 211.0f, -59.0f),
                PathNode.VerticalTo(-92.0f),
                PathNode.QuadTo(211.0f, -103.0f, 218.5f, -110.5f),
                PathNode.QuadTo(226.0f, -118.0f, 238.0f, -118.0f),
                PathNode.HorizontalTo(1078.0f),
                PathNode.QuadTo(1089.0f, -118.0f, 1097.0f, -110.5f),
                PathNode.QuadTo(1105.0f, -103.0f, 1105.0f, -92.0f),
                PathNode.Close,
                PathNode.MoveTo(430.0f, 148.0f),
                PathNode.LineTo(323.0f, 255.0f),
                PathNode.QuadTo(309.0f, 269.0f, 309.0f, 289.0f),
                PathNode.QuadTo(309.0f, 309.0f, 323.0f, 323.0f),
                PathNode.LineTo(427.0f, 426.0f),
                PathNode.QuadTo(436.0f, 435.0f, 444.0f, 426.0f),
                PathNode.LineTo(697.0f, 173.0f),
                PathNode.QuadTo(706.0f, 165.0f, 697.0f, 156.0f),
                PathNode.LineTo(688.0f, 148.0f),
                PathNode.QuadTo(671.0f, 134.0f, 654.0f, 134.0f),
                PathNode.HorizontalTo(464.0f),
                PathNode.QuadTo(444.0f, 134.0f, 430.0f, 148.0f),
                PathNode.Close,
                PathNode.MoveTo(756.0f, 234.0f),
                PathNode.LineTo(504.0f, 486.0f),
                PathNode.QuadTo(496.0f, 494.0f, 504.0f, 503.0f),
                PathNode.LineTo(739.0f, 739.0f),
                PathNode.QuadTo(753.0f, 753.0f, 773.5f, 753.0f),
                PathNode.QuadTo(794.0f, 753.0f, 807.0f, 739.0f),
                PathNode.LineTo(1010.0f, 536.0f),
                PathNode.QuadTo(1024.0f, 523.0f, 1024.0f, 503.0f),
                PathNode.QuadTo(1024.0f, 483.0f, 1010.0f, 469.0f),
                PathNode.LineTo(773.0f, 234.0f),
                PathNode.QuadTo(769.0f, 230.0f, 764.5f, 230.0f),
                PathNode.QuadTo(760.0f, 230.0f, 756.0f, 234.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _clearRegular!!
    }

private var _clearRegular: ImageVector? = null

val MiuixIcons.Heavy.Clear: ImageVector
    get() {
        if (_clearHeavy != null) return _clearHeavy!!
        _clearHeavy = ImageVector.Builder(
            name = "Clear.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1203.6f,
            viewportHeight = 1203.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -63.200000000000045f, translationY = 967.3f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(751.0f, 75.0f),
                PathNode.LineTo(1096.0f, 420.0f),
                PathNode.QuadTo(1133.0f, 456.0f, 1133.0f, 507.0f),
                PathNode.QuadTo(1133.0f, 558.0f, 1096.0f, 594.0f),
                PathNode.LineTo(861.0f, 831.0f),
                PathNode.QuadTo(837.0f, 855.0f, 805.0f, 863.0f),
                PathNode.QuadTo(773.0f, 871.0f, 741.0f, 863.0f),
                PathNode.QuadTo(709.0f, 855.0f, 686.0f, 831.0f),
                PathNode.LineTo(235.0f, 380.0f),
                PathNode.QuadTo(212.0f, 357.0f, 204.0f, 325.0f),
                PathNode.QuadTo(196.0f, 293.0f, 204.0f, 261.5f),
                PathNode.QuadTo(212.0f, 230.0f, 235.0f, 207.0f),
                PathNode.LineTo(367.0f, 75.0f),
                PathNode.QuadTo(384.0f, 58.0f, 406.5f, 48.5f),
                PathNode.QuadTo(429.0f, 39.0f, 454.0f, 39.0f),
                PathNode.HorizontalTo(664.0f),
                PathNode.QuadTo(689.0f, 39.0f, 711.5f, 48.5f),
                PathNode.QuadTo(734.0f, 58.0f, 751.0f, 75.0f),
                PathNode.Close,
                PathNode.MoveTo(1119.0f, -96.0f),
                PathNode.VerticalTo(-63.0f),
                PathNode.QuadTo(1119.0f, -47.0f, 1106.5f, -35.0f),
                PathNode.QuadTo(1094.0f, -23.0f, 1078.0f, -23.0f),
                PathNode.HorizontalTo(238.0f),
                PathNode.QuadTo(221.0f, -23.0f, 209.0f, -35.0f),
                PathNode.QuadTo(197.0f, -47.0f, 197.0f, -63.0f),
                PathNode.VerticalTo(-96.0f),
                PathNode.QuadTo(197.0f, -113.0f, 209.0f, -124.5f),
                PathNode.QuadTo(221.0f, -136.0f, 238.0f, -136.0f),
                PathNode.HorizontalTo(1078.0f),
                PathNode.QuadTo(1095.0f, -136.0f, 1107.0f, -124.0f),
                PathNode.QuadTo(1119.0f, -112.0f, 1119.0f, -96.0f),
                PathNode.Close,
                PathNode.MoveTo(437.0f, 165.0f),
                PathNode.LineTo(333.0f, 269.0f),
                PathNode.QuadTo(323.0f, 279.0f, 323.0f, 293.5f),
                PathNode.QuadTo(323.0f, 308.0f, 333.0f, 318.0f),
                PathNode.LineTo(427.0f, 411.0f),
                PathNode.QuadTo(436.0f, 420.0f, 444.0f, 411.0f),
                PathNode.LineTo(678.0f, 177.0f),
                PathNode.QuadTo(684.0f, 171.0f, 678.0f, 165.0f),
                PathNode.LineTo(672.0f, 160.0f),
                PathNode.QuadTo(661.0f, 152.0f, 649.0f, 152.0f),
                PathNode.HorizontalTo(469.0f),
                PathNode.QuadTo(450.0f, 152.0f, 437.0f, 165.0f),
                PathNode.Close,
                PathNode.MoveTo(752.0f, 262.0f),
                PathNode.LineTo(523.0f, 491.0f),
                PathNode.QuadTo(515.0f, 499.0f, 523.0f, 508.0f),
                PathNode.LineTo(749.0f, 734.0f),
                PathNode.QuadTo(759.0f, 744.0f, 773.0f, 744.0f),
                PathNode.QuadTo(787.0f, 744.0f, 797.0f, 734.0f),
                PathNode.LineTo(1000.0f, 531.0f),
                PathNode.QuadTo(1010.0f, 521.0f, 1010.0f, 507.0f),
                PathNode.QuadTo(1010.0f, 493.0f, 1000.0f, 483.0f),
                PathNode.LineTo(776.0f, 261.0f),
                PathNode.QuadTo(770.0f, 255.0f, 764.0f, 255.5f),
                PathNode.QuadTo(758.0f, 256.0f, 752.0f, 262.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _clearHeavy!!
    }

private var _clearHeavy: ImageVector? = null
