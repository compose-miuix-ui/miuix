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

val MiuixIcons.Recording: ImageVector
    get() = MiuixIcons.Regular.Recording

val MiuixIcons.Light.Recording: ImageVector
    get() {
        if (_recordingLight != null) return _recordingLight!!
        _recordingLight = ImageVector.Builder(
            name = "Recording.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1167.6f,
            viewportHeight = 1167.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -81.70000000000005f, translationY = 958.8f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1152.0f, 375.0f),
                PathNode.QuadTo(1152.0f, 507.0f, 1086.5f, 618.5f),
                PathNode.QuadTo(1021.0f, 730.0f, 909.5f, 795.5f),
                PathNode.QuadTo(798.0f, 861.0f, 666.24f, 861.0f),
                PathNode.QuadTo(533.504f, 861.0f, 421.752f, 795.5f),
                PathNode.QuadTo(310.0f, 730.0f, 244.5f, 618.5f),
                PathNode.QuadTo(179.0f, 507.0f, 179.0f, 375.0f),
                PathNode.QuadTo(179.0f, 243.0f, 244.5f, 131.5f),
                PathNode.QuadTo(310.0f, 20.0f, 421.752f, -45.5f),
                PathNode.QuadTo(533.504f, -111.0f, 666.24f, -111.0f),
                PathNode.QuadTo(798.0f, -111.0f, 909.5f, -45.5f),
                PathNode.QuadTo(1021.0f, 20.0f, 1086.5f, 131.5f),
                PathNode.QuadTo(1152.0f, 243.0f, 1152.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(367.0f, 220.0f),
                PathNode.VerticalTo(515.0f),
                PathNode.QuadTo(367.0f, 523.0f, 372.1f, 527.5f),
                PathNode.QuadTo(377.2f, 532.0f, 384.0f, 532.0f),
                PathNode.HorizontalTo(398.0f),
                PathNode.QuadTo(404.8f, 532.0f, 409.9f, 526.9f),
                PathNode.QuadTo(415.0f, 521.8f, 415.0f, 515.0f),
                PathNode.VerticalTo(220.0f),
                PathNode.QuadTo(415.0f, 214.0f, 409.9f, 209.0f),
                PathNode.QuadTo(404.8f, 204.0f, 398.0f, 204.0f),
                PathNode.HorizontalTo(384.0f),
                PathNode.QuadTo(377.2f, 204.0f, 372.1f, 209.0f),
                PathNode.QuadTo(367.0f, 214.0f, 367.0f, 220.0f),
                PathNode.Close,
                PathNode.MoveTo(505.0f, 260.0f),
                PathNode.VerticalTo(475.0f),
                PathNode.QuadTo(505.0f, 483.0f, 510.1f, 487.5f),
                PathNode.QuadTo(515.2f, 492.0f, 522.0f, 492.0f),
                PathNode.HorizontalTo(536.0f),
                PathNode.QuadTo(542.8f, 492.0f, 547.9f, 486.9f),
                PathNode.QuadTo(553.0f, 481.8f, 553.0f, 475.0f),
                PathNode.VerticalTo(260.0f),
                PathNode.QuadTo(553.0f, 254.0f, 547.9f, 249.0f),
                PathNode.QuadTo(542.8f, 244.0f, 536.0f, 244.0f),
                PathNode.HorizontalTo(522.0f),
                PathNode.QuadTo(515.2f, 244.0f, 510.1f, 249.0f),
                PathNode.QuadTo(505.0f, 254.0f, 505.0f, 260.0f),
                PathNode.Close,
                PathNode.MoveTo(779.0f, 204.0f),
                PathNode.VerticalTo(531.0f),
                PathNode.QuadTo(779.0f, 539.0f, 784.1f, 543.5f),
                PathNode.QuadTo(789.2f, 548.0f, 796.0f, 548.0f),
                PathNode.HorizontalTo(810.0f),
                PathNode.QuadTo(816.8f, 548.0f, 821.9f, 542.9f),
                PathNode.QuadTo(827.0f, 537.8f, 827.0f, 531.0f),
                PathNode.VerticalTo(204.0f),
                PathNode.QuadTo(827.0f, 198.0f, 821.9f, 193.0f),
                PathNode.QuadTo(816.8f, 188.0f, 810.0f, 188.0f),
                PathNode.HorizontalTo(796.0f),
                PathNode.QuadTo(789.2f, 188.0f, 784.1f, 193.0f),
                PathNode.QuadTo(779.0f, 198.0f, 779.0f, 204.0f),
                PathNode.Close,
                PathNode.MoveTo(641.0f, 109.0f),
                PathNode.VerticalTo(641.0f),
                PathNode.QuadTo(641.0f, 649.0f, 646.1f, 653.5f),
                PathNode.QuadTo(651.2f, 658.0f, 658.0f, 658.0f),
                PathNode.HorizontalTo(672.0f),
                PathNode.QuadTo(678.8f, 658.0f, 683.9f, 652.9f),
                PathNode.QuadTo(689.0f, 647.8f, 689.0f, 641.0f),
                PathNode.VerticalTo(109.0f),
                PathNode.QuadTo(689.0f, 103.0f, 683.9f, 98.0f),
                PathNode.QuadTo(678.8f, 93.0f, 672.0f, 93.0f),
                PathNode.HorizontalTo(658.0f),
                PathNode.QuadTo(651.2f, 93.0f, 646.1f, 98.0f),
                PathNode.QuadTo(641.0f, 103.0f, 641.0f, 109.0f),
                PathNode.Close,
                PathNode.MoveTo(917.0f, 304.0f),
                PathNode.VerticalTo(431.0f),
                PathNode.QuadTo(917.0f, 439.0f, 922.1f, 443.5f),
                PathNode.QuadTo(927.2f, 448.0f, 934.0f, 448.0f),
                PathNode.HorizontalTo(948.0f),
                PathNode.QuadTo(954.8f, 448.0f, 959.9f, 442.9f),
                PathNode.QuadTo(965.0f, 437.8f, 965.0f, 431.0f),
                PathNode.VerticalTo(304.0f),
                PathNode.QuadTo(965.0f, 298.0f, 959.9f, 293.0f),
                PathNode.QuadTo(954.8f, 288.0f, 948.0f, 288.0f),
                PathNode.HorizontalTo(934.0f),
                PathNode.QuadTo(927.2f, 288.0f, 922.1f, 293.0f),
                PathNode.QuadTo(917.0f, 298.0f, 917.0f, 304.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _recordingLight!!
    }

private var _recordingLight: ImageVector? = null

val MiuixIcons.Regular.Recording: ImageVector
    get() {
        if (_recordingRegular != null) return _recordingRegular!!
        _recordingRegular = ImageVector.Builder(
            name = "Recording.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1198.8f,
            viewportHeight = 1198.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -66.10000000000002f, translationY = 974.4f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1165.0f, 375.0f),
                PathNode.QuadTo(1165.0f, 510.0f, 1098.0f, 625.0f),
                PathNode.QuadTo(1031.0f, 740.0f, 916.0f, 807.0f),
                PathNode.QuadTo(801.0f, 874.0f, 666.0f, 874.0f),
                PathNode.QuadTo(530.0f, 874.0f, 415.5f, 807.0f),
                PathNode.QuadTo(301.0f, 740.0f, 233.5f, 625.0f),
                PathNode.QuadTo(166.0f, 510.0f, 166.0f, 375.0f),
                PathNode.QuadTo(166.0f, 240.0f, 233.5f, 125.0f),
                PathNode.QuadTo(301.0f, 10.0f, 415.5f, -57.0f),
                PathNode.QuadTo(530.0f, -124.0f, 666.0f, -124.0f),
                PathNode.QuadTo(801.0f, -124.0f, 916.0f, -57.0f),
                PathNode.QuadTo(1031.0f, 10.0f, 1098.0f, 125.0f),
                PathNode.QuadTo(1165.0f, 240.0f, 1165.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(358.0f, 219.0f),
                PathNode.VerticalTo(516.0f),
                PathNode.QuadTo(358.0f, 527.0f, 365.5f, 534.0f),
                PathNode.QuadTo(373.0f, 541.0f, 383.0f, 541.0f),
                PathNode.HorizontalTo(399.0f),
                PathNode.QuadTo(409.0f, 541.0f, 416.5f, 533.5f),
                PathNode.QuadTo(424.0f, 526.0f, 424.0f, 516.0f),
                PathNode.VerticalTo(219.0f),
                PathNode.QuadTo(424.0f, 210.0f, 416.5f, 202.5f),
                PathNode.QuadTo(409.0f, 195.0f, 399.0f, 195.0f),
                PathNode.HorizontalTo(383.0f),
                PathNode.QuadTo(373.0f, 195.0f, 365.5f, 202.5f),
                PathNode.QuadTo(358.0f, 210.0f, 358.0f, 219.0f),
                PathNode.Close,
                PathNode.MoveTo(496.0f, 259.0f),
                PathNode.VerticalTo(476.0f),
                PathNode.QuadTo(496.0f, 487.0f, 503.5f, 494.0f),
                PathNode.QuadTo(511.0f, 501.0f, 521.0f, 501.0f),
                PathNode.HorizontalTo(537.0f),
                PathNode.QuadTo(547.0f, 501.0f, 554.5f, 493.5f),
                PathNode.QuadTo(562.0f, 486.0f, 562.0f, 476.0f),
                PathNode.VerticalTo(259.0f),
                PathNode.QuadTo(562.0f, 250.0f, 554.5f, 242.5f),
                PathNode.QuadTo(547.0f, 235.0f, 537.0f, 235.0f),
                PathNode.HorizontalTo(521.0f),
                PathNode.QuadTo(511.0f, 235.0f, 503.5f, 242.5f),
                PathNode.QuadTo(496.0f, 250.0f, 496.0f, 259.0f),
                PathNode.Close,
                PathNode.MoveTo(770.0f, 203.0f),
                PathNode.VerticalTo(532.0f),
                PathNode.QuadTo(770.0f, 543.0f, 777.5f, 550.0f),
                PathNode.QuadTo(785.0f, 557.0f, 795.0f, 557.0f),
                PathNode.HorizontalTo(811.0f),
                PathNode.QuadTo(821.0f, 557.0f, 828.5f, 549.5f),
                PathNode.QuadTo(836.0f, 542.0f, 836.0f, 532.0f),
                PathNode.VerticalTo(203.0f),
                PathNode.QuadTo(836.0f, 194.0f, 828.5f, 186.5f),
                PathNode.QuadTo(821.0f, 179.0f, 811.0f, 179.0f),
                PathNode.HorizontalTo(795.0f),
                PathNode.QuadTo(785.0f, 179.0f, 777.5f, 186.5f),
                PathNode.QuadTo(770.0f, 194.0f, 770.0f, 203.0f),
                PathNode.Close,
                PathNode.MoveTo(632.0f, 108.0f),
                PathNode.VerticalTo(642.0f),
                PathNode.QuadTo(632.0f, 653.0f, 639.5f, 660.0f),
                PathNode.QuadTo(647.0f, 667.0f, 657.0f, 667.0f),
                PathNode.HorizontalTo(673.0f),
                PathNode.QuadTo(683.0f, 667.0f, 690.5f, 659.5f),
                PathNode.QuadTo(698.0f, 652.0f, 698.0f, 642.0f),
                PathNode.VerticalTo(108.0f),
                PathNode.QuadTo(698.0f, 99.0f, 690.5f, 91.5f),
                PathNode.QuadTo(683.0f, 84.0f, 673.0f, 84.0f),
                PathNode.HorizontalTo(657.0f),
                PathNode.QuadTo(647.0f, 84.0f, 639.5f, 91.5f),
                PathNode.QuadTo(632.0f, 99.0f, 632.0f, 108.0f),
                PathNode.Close,
                PathNode.MoveTo(908.0f, 303.0f),
                PathNode.VerticalTo(432.0f),
                PathNode.QuadTo(908.0f, 443.0f, 915.5f, 450.0f),
                PathNode.QuadTo(923.0f, 457.0f, 933.0f, 457.0f),
                PathNode.HorizontalTo(949.0f),
                PathNode.QuadTo(959.0f, 457.0f, 966.5f, 449.5f),
                PathNode.QuadTo(974.0f, 442.0f, 974.0f, 432.0f),
                PathNode.VerticalTo(303.0f),
                PathNode.QuadTo(974.0f, 294.0f, 966.5f, 286.5f),
                PathNode.QuadTo(959.0f, 279.0f, 949.0f, 279.0f),
                PathNode.HorizontalTo(933.0f),
                PathNode.QuadTo(923.0f, 279.0f, 915.5f, 286.5f),
                PathNode.QuadTo(908.0f, 294.0f, 908.0f, 303.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _recordingRegular!!
    }

private var _recordingRegular: ImageVector? = null

val MiuixIcons.Heavy.Recording: ImageVector
    get() {
        if (_recordingHeavy != null) return _recordingHeavy!!
        _recordingHeavy = ImageVector.Builder(
            name = "Recording.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1232.3999999999999f,
            viewportHeight = 1232.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -49.30000000000007f, translationY = 991.1999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1179.0f, 375.0f),
                PathNode.QuadTo(1179.0f, 514.0f, 1109.5f, 631.5f),
                PathNode.QuadTo(1040.0f, 749.0f, 922.5f, 818.5f),
                PathNode.QuadTo(805.0f, 888.0f, 666.0f, 888.0f),
                PathNode.QuadTo(527.0f, 888.0f, 409.0f, 818.5f),
                PathNode.QuadTo(291.0f, 749.0f, 221.5f, 631.5f),
                PathNode.QuadTo(152.0f, 514.0f, 152.0f, 375.0f),
                PathNode.QuadTo(152.0f, 236.0f, 221.5f, 118.5f),
                PathNode.QuadTo(291.0f, 1.0f, 409.0f, -68.5f),
                PathNode.QuadTo(527.0f, -138.0f, 666.0f, -138.0f),
                PathNode.QuadTo(805.0f, -138.0f, 922.5f, -68.5f),
                PathNode.QuadTo(1040.0f, 1.0f, 1109.5f, 118.5f),
                PathNode.QuadTo(1179.0f, 236.0f, 1179.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(337.0f, 219.0f),
                PathNode.VerticalTo(516.0f),
                PathNode.QuadTo(337.0f, 530.0f, 347.0f, 540.0f),
                PathNode.QuadTo(357.0f, 550.0f, 371.0f, 550.0f),
                PathNode.HorizontalTo(387.0f),
                PathNode.QuadTo(401.0f, 550.0f, 411.0f, 540.0f),
                PathNode.QuadTo(421.0f, 530.0f, 421.0f, 516.0f),
                PathNode.VerticalTo(219.0f),
                PathNode.QuadTo(421.0f, 206.0f, 410.5f, 196.0f),
                PathNode.QuadTo(400.0f, 186.0f, 387.0f, 186.0f),
                PathNode.HorizontalTo(371.0f),
                PathNode.QuadTo(357.0f, 186.0f, 347.0f, 196.0f),
                PathNode.QuadTo(337.0f, 206.0f, 337.0f, 219.0f),
                PathNode.Close,
                PathNode.MoveTo(480.0f, 259.0f),
                PathNode.VerticalTo(476.0f),
                PathNode.QuadTo(480.0f, 490.0f, 490.5f, 500.0f),
                PathNode.QuadTo(501.0f, 510.0f, 515.0f, 510.0f),
                PathNode.HorizontalTo(531.0f),
                PathNode.QuadTo(545.0f, 510.0f, 555.0f, 500.0f),
                PathNode.QuadTo(565.0f, 490.0f, 565.0f, 476.0f),
                PathNode.VerticalTo(259.0f),
                PathNode.QuadTo(565.0f, 246.0f, 554.5f, 236.0f),
                PathNode.QuadTo(544.0f, 226.0f, 531.0f, 226.0f),
                PathNode.HorizontalTo(515.0f),
                PathNode.QuadTo(501.0f, 226.0f, 490.5f, 236.0f),
                PathNode.QuadTo(480.0f, 246.0f, 480.0f, 259.0f),
                PathNode.Close,
                PathNode.MoveTo(766.0f, 203.0f),
                PathNode.VerticalTo(532.0f),
                PathNode.QuadTo(766.0f, 546.0f, 776.0f, 556.0f),
                PathNode.QuadTo(786.0f, 566.0f, 801.0f, 566.0f),
                PathNode.HorizontalTo(817.0f),
                PathNode.QuadTo(831.0f, 566.0f, 841.0f, 556.0f),
                PathNode.QuadTo(851.0f, 546.0f, 851.0f, 532.0f),
                PathNode.VerticalTo(203.0f),
                PathNode.QuadTo(851.0f, 190.0f, 840.5f, 180.0f),
                PathNode.QuadTo(830.0f, 170.0f, 817.0f, 170.0f),
                PathNode.HorizontalTo(801.0f),
                PathNode.QuadTo(786.0f, 170.0f, 776.0f, 180.0f),
                PathNode.QuadTo(766.0f, 190.0f, 766.0f, 203.0f),
                PathNode.Close,
                PathNode.MoveTo(623.0f, 108.0f),
                PathNode.VerticalTo(642.0f),
                PathNode.QuadTo(623.0f, 656.0f, 633.0f, 666.0f),
                PathNode.QuadTo(643.0f, 676.0f, 657.0f, 676.0f),
                PathNode.HorizontalTo(673.0f),
                PathNode.QuadTo(687.0f, 676.0f, 697.0f, 666.0f),
                PathNode.QuadTo(707.0f, 656.0f, 707.0f, 642.0f),
                PathNode.VerticalTo(108.0f),
                PathNode.QuadTo(707.0f, 95.0f, 696.5f, 85.0f),
                PathNode.QuadTo(686.0f, 75.0f, 673.0f, 75.0f),
                PathNode.HorizontalTo(657.0f),
                PathNode.QuadTo(643.0f, 75.0f, 633.0f, 85.0f),
                PathNode.QuadTo(623.0f, 95.0f, 623.0f, 108.0f),
                PathNode.Close,
                PathNode.MoveTo(910.0f, 303.0f),
                PathNode.VerticalTo(432.0f),
                PathNode.QuadTo(910.0f, 446.0f, 920.0f, 456.0f),
                PathNode.QuadTo(930.0f, 466.0f, 944.0f, 466.0f),
                PathNode.HorizontalTo(960.0f),
                PathNode.QuadTo(974.0f, 466.0f, 984.0f, 456.0f),
                PathNode.QuadTo(994.0f, 446.0f, 994.0f, 432.0f),
                PathNode.VerticalTo(303.0f),
                PathNode.QuadTo(994.0f, 290.0f, 983.5f, 280.0f),
                PathNode.QuadTo(973.0f, 270.0f, 960.0f, 270.0f),
                PathNode.HorizontalTo(944.0f),
                PathNode.QuadTo(930.0f, 270.0f, 920.0f, 280.0f),
                PathNode.QuadTo(910.0f, 290.0f, 910.0f, 303.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _recordingHeavy!!
    }

private var _recordingHeavy: ImageVector? = null
