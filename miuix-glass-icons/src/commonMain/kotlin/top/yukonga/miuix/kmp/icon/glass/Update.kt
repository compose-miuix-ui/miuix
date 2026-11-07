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

val MiuixIcons.Glass.Update: ImageVector
    get() = MiuixIcons.Glass.Regular.Update

val MiuixIcons.Glass.Light.Update: ImageVector
    get() {
        if (_updateLight != null) return _updateLight!!
        _updateLight = ImageVector.Builder(
            name = "Update.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1068.6f, 570.9f),
                        PathNode.QuadTo(1068.6f, 706.4f, 1001.9f, 820.5f),
                        PathNode.QuadTo(935.2f, 934.6f, 821.3f, 1001.6f),
                        PathNode.QuadTo(707.4f, 1068.6f, 571.9f, 1068.6f),
                        PathNode.QuadTo(436.5f, 1068.6f, 322.4f, 1001.6f),
                        PathNode.QuadTo(208.3f, 934.6f, 141.3f, 820.5f),
                        PathNode.QuadTo(74.3f, 706.4f, 74.3f, 570.9f),
                        PathNode.QuadTo(74.3f, 435.5f, 141.3f, 321.6f),
                        PathNode.QuadTo(208.3f, 207.7f, 322.4f, 141.0f),
                        PathNode.QuadTo(436.5f, 74.3f, 571.9f, 74.3f),
                        PathNode.QuadTo(707.4f, 74.3f, 821.3f, 141.0f),
                        PathNode.QuadTo(935.2f, 207.7f, 1001.9f, 321.6f),
                        PathNode.QuadTo(1068.6f, 435.5f, 1068.6f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(133.6f, 570.9f),
                        PathNode.QuadTo(133.6f, 690.0f, 192.6f, 790.7f),
                        PathNode.QuadTo(251.6f, 891.3f, 352.2f, 950.3f),
                        PathNode.QuadTo(452.8f, 1009.3f, 571.9f, 1009.3f),
                        PathNode.QuadTo(691.0f, 1009.3f, 791.7f, 950.3f),
                        PathNode.QuadTo(892.3f, 891.3f, 951.3f, 790.7f),
                        PathNode.QuadTo(1010.3f, 690.0f, 1010.3f, 570.9f),
                        PathNode.QuadTo(1010.3f, 452.4f, 951.3f, 351.8f),
                        PathNode.QuadTo(892.3f, 251.2f, 791.7f, 191.9f),
                        PathNode.QuadTo(691.0f, 132.6f, 571.9f, 132.6f),
                        PathNode.QuadTo(452.8f, 132.6f, 352.2f, 191.9f),
                        PathNode.QuadTo(251.6f, 251.2f, 192.6f, 351.8f),
                        PathNode.QuadTo(133.6f, 452.4f, 133.6f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(601.6f, 330.4f),
                        PathNode.LineTo(601.6f, 725.9f),
                        PathNode.LineTo(752.5f, 575.0f),
                        PathNode.QuadTo(758.3f, 569.3f, 765.2f, 568.7f),
                        PathNode.QuadTo(772.2f, 568.0f, 778.6f, 574.5f),
                        PathNode.LineTo(794.4f, 590.3f),
                        PathNode.QuadTo(800.8f, 596.7f, 800.0f, 603.7f),
                        PathNode.QuadTo(799.2f, 610.6f, 794.4f, 615.8f),
                        PathNode.LineTo(598.0f, 813.2f),
                        PathNode.QuadTo(586.6f, 825.0f, 572.7f, 825.3f),
                        PathNode.QuadTo(558.7f, 825.6f, 547.4f, 814.3f),
                        PathNode.LineTo(349.9f, 616.3f),
                        PathNode.QuadTo(344.6f, 610.9f, 344.3f, 604.5f),
                        PathNode.QuadTo(344.0f, 598.2f, 349.9f, 591.3f),
                        PathNode.LineTo(364.2f, 575.5f),
                        PathNode.QuadTo(370.6f, 568.5f, 378.0f, 568.8f),
                        PathNode.QuadTo(385.5f, 569.2f, 391.4f, 575.0f),
                        PathNode.LineTo(542.3f, 725.9f),
                        PathNode.LineTo(542.3f, 330.4f),
                        PathNode.QuadTo(542.3f, 321.3f, 547.3f, 315.9f),
                        PathNode.QuadTo(552.4f, 310.6f, 560.9f, 310.6f),
                        PathNode.LineTo(581.8f, 310.6f),
                        PathNode.QuadTo(591.5f, 310.6f, 596.5f, 315.9f),
                        PathNode.QuadTo(601.6f, 321.3f, 601.6f, 330.4f),
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

val MiuixIcons.Glass.Normal.Update: ImageVector
    get() {
        if (_updateNormal != null) return _updateNormal!!
        _updateNormal = ImageVector.Builder(
            name = "Update.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1077.8f, 570.9f),
                        PathNode.QuadTo(1077.8f, 708.8f, 1009.8f, 824.9f),
                        PathNode.QuadTo(941.9f, 941.0f, 825.8f, 1009.4f),
                        PathNode.QuadTo(709.8f, 1077.8f, 571.9f, 1077.8f),
                        PathNode.QuadTo(434.0f, 1077.8f, 317.9f, 1009.4f),
                        PathNode.QuadTo(201.8f, 941.0f, 133.5f, 824.9f),
                        PathNode.QuadTo(65.1f, 708.8f, 65.1f, 570.9f),
                        PathNode.QuadTo(65.1f, 433.0f, 133.5f, 317.0f),
                        PathNode.QuadTo(201.8f, 201.0f, 317.9f, 133.0f),
                        PathNode.QuadTo(434.0f, 65.1f, 571.9f, 65.1f),
                        PathNode.QuadTo(709.8f, 65.1f, 825.8f, 133.0f),
                        PathNode.QuadTo(941.9f, 201.0f, 1009.8f, 317.0f),
                        PathNode.QuadTo(1077.8f, 433.0f, 1077.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(142.8f, 570.9f),
                        PathNode.QuadTo(142.8f, 687.9f, 200.4f, 786.4f),
                        PathNode.QuadTo(258.0f, 884.8f, 356.5f, 942.5f),
                        PathNode.QuadTo(455.0f, 1000.1f, 571.9f, 1000.1f),
                        PathNode.QuadTo(688.9f, 1000.1f, 787.4f, 942.5f),
                        PathNode.QuadTo(885.8f, 884.8f, 943.5f, 786.4f),
                        PathNode.QuadTo(1001.1f, 687.9f, 1001.1f, 570.9f),
                        PathNode.QuadTo(1001.1f, 454.8f, 943.5f, 356.3f),
                        PathNode.QuadTo(885.8f, 257.9f, 787.4f, 199.8f),
                        PathNode.QuadTo(688.9f, 141.8f, 571.9f, 141.8f),
                        PathNode.QuadTo(455.0f, 141.8f, 356.5f, 199.8f),
                        PathNode.QuadTo(258.0f, 257.9f, 200.4f, 356.3f),
                        PathNode.QuadTo(142.8f, 454.8f, 142.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(610.8f, 332.8f),
                        PathNode.LineTo(610.8f, 703.9f),
                        PathNode.LineTo(744.5f, 570.2f),
                        PathNode.QuadTo(751.8f, 562.8f, 761.2f, 561.6f),
                        PathNode.QuadTo(770.6f, 560.4f, 779.5f, 569.3f),
                        PathNode.LineTo(799.6f, 589.3f),
                        PathNode.QuadTo(808.5f, 598.2f, 807.2f, 607.6f),
                        PathNode.QuadTo(805.9f, 617.0f, 799.6f, 623.5f),
                        PathNode.LineTo(604.2f, 819.9f),
                        PathNode.QuadTo(590.3f, 833.9f, 572.5f, 834.3f),
                        PathNode.QuadTo(554.7f, 834.8f, 541.6f, 821.6f),
                        PathNode.LineTo(344.4f, 623.6f),
                        PathNode.QuadTo(337.3f, 616.4f, 336.8f, 607.9f),
                        PathNode.QuadTo(336.4f, 599.4f, 344.4f, 590.3f),
                        PathNode.LineTo(362.6f, 570.3f),
                        PathNode.QuadTo(371.5f, 560.5f, 381.4f, 561.3f),
                        PathNode.QuadTo(391.3f, 562.1f, 399.3f, 570.2f),
                        PathNode.LineTo(533.1f, 703.9f),
                        PathNode.LineTo(533.1f, 332.8f),
                        PathNode.QuadTo(533.1f, 320.3f, 539.8f, 313.2f),
                        PathNode.QuadTo(546.6f, 306.0f, 558.2f, 306.0f),
                        PathNode.LineTo(584.0f, 306.0f),
                        PathNode.QuadTo(597.3f, 306.0f, 604.0f, 313.2f),
                        PathNode.QuadTo(610.8f, 320.3f, 610.8f, 332.8f),
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

val MiuixIcons.Glass.Regular.Update: ImageVector
    get() {
        if (_updateRegular != null) return _updateRegular!!
        _updateRegular = ImageVector.Builder(
            name = "Update.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1081.9f, 570.9f),
                        PathNode.QuadTo(1081.9f, 709.9f, 1013.4f, 826.9f),
                        PathNode.QuadTo(944.9f, 943.9f, 827.9f, 1012.9f),
                        PathNode.QuadTo(710.9f, 1081.9f, 571.9f, 1081.9f),
                        PathNode.QuadTo(432.9f, 1081.9f, 315.9f, 1012.9f),
                        PathNode.QuadTo(198.9f, 943.9f, 129.9f, 826.9f),
                        PathNode.QuadTo(60.9f, 709.9f, 60.9f, 570.9f),
                        PathNode.QuadTo(60.9f, 431.9f, 129.9f, 314.9f),
                        PathNode.QuadTo(198.9f, 197.9f, 315.9f, 129.4f),
                        PathNode.QuadTo(432.9f, 60.9f, 571.9f, 60.9f),
                        PathNode.QuadTo(710.9f, 60.9f, 827.9f, 129.4f),
                        PathNode.QuadTo(944.9f, 197.9f, 1013.4f, 314.9f),
                        PathNode.QuadTo(1081.9f, 431.9f, 1081.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(146.9f, 570.9f),
                        PathNode.QuadTo(146.9f, 686.9f, 203.9f, 784.4f),
                        PathNode.QuadTo(260.9f, 881.9f, 358.4f, 938.9f),
                        PathNode.QuadTo(455.9f, 995.9f, 571.9f, 995.9f),
                        PathNode.QuadTo(687.9f, 995.9f, 785.4f, 938.9f),
                        PathNode.QuadTo(882.9f, 881.9f, 939.9f, 784.4f),
                        PathNode.QuadTo(996.9f, 686.9f, 996.9f, 570.9f),
                        PathNode.QuadTo(996.9f, 455.9f, 939.9f, 358.4f),
                        PathNode.QuadTo(882.9f, 260.9f, 785.4f, 203.4f),
                        PathNode.QuadTo(687.9f, 145.9f, 571.9f, 145.9f),
                        PathNode.QuadTo(455.9f, 145.9f, 358.4f, 203.4f),
                        PathNode.QuadTo(260.9f, 260.9f, 203.9f, 358.4f),
                        PathNode.QuadTo(146.9f, 455.9f, 146.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(614.9f, 333.9f),
                        PathNode.LineTo(614.9f, 693.9f),
                        PathNode.LineTo(740.9f, 567.9f),
                        PathNode.QuadTo(748.9f, 559.9f, 759.4f, 558.4f),
                        PathNode.QuadTo(769.9f, 556.9f, 779.9f, 566.9f),
                        PathNode.LineTo(801.9f, 588.9f),
                        PathNode.QuadTo(811.9f, 598.9f, 810.4f, 609.4f),
                        PathNode.QuadTo(808.9f, 619.9f, 801.9f, 626.9f),
                        PathNode.LineTo(606.9f, 822.9f),
                        PathNode.QuadTo(591.9f, 837.9f, 572.4f, 838.4f),
                        PathNode.QuadTo(552.9f, 838.9f, 538.9f, 824.9f),
                        PathNode.LineTo(341.9f, 626.9f),
                        PathNode.QuadTo(333.9f, 618.9f, 333.4f, 609.4f),
                        PathNode.QuadTo(332.9f, 599.9f, 341.9f, 589.9f),
                        PathNode.LineTo(361.9f, 567.9f),
                        PathNode.QuadTo(371.9f, 556.9f, 382.9f, 557.9f),
                        PathNode.QuadTo(393.9f, 558.9f, 402.9f, 567.9f),
                        PathNode.LineTo(528.9f, 693.9f),
                        PathNode.LineTo(528.9f, 333.9f),
                        PathNode.QuadTo(528.9f, 319.9f, 536.4f, 311.9f),
                        PathNode.QuadTo(543.9f, 303.9f, 556.9f, 303.9f),
                        PathNode.LineTo(584.9f, 303.9f),
                        PathNode.QuadTo(599.9f, 303.9f, 607.4f, 311.9f),
                        PathNode.QuadTo(614.9f, 319.9f, 614.9f, 333.9f),
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

val MiuixIcons.Glass.Medium.Update: ImageVector
    get() {
        if (_updateMedium != null) return _updateMedium!!
        _updateMedium = ImageVector.Builder(
            name = "Update.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1090.0f, 570.9f),
                        PathNode.QuadTo(1090.0f, 712.1f, 1020.5f, 831.0f),
                        PathNode.QuadTo(950.9f, 949.8f, 832.0f, 1019.9f),
                        PathNode.QuadTo(713.2f, 1090.0f, 572.0f, 1090.0f),
                        PathNode.QuadTo(430.8f, 1090.0f, 311.9f, 1019.9f),
                        PathNode.QuadTo(193.0f, 949.9f, 122.9f, 831.0f),
                        PathNode.QuadTo(52.8f, 712.2f, 52.8f, 571.0f),
                        PathNode.QuadTo(52.8f, 429.8f, 122.9f, 310.9f),
                        PathNode.QuadTo(193.0f, 192.0f, 311.8f, 122.4f),
                        PathNode.QuadTo(430.7f, 52.8f, 571.9f, 52.8f),
                        PathNode.QuadTo(713.1f, 52.8f, 832.0f, 122.4f),
                        PathNode.QuadTo(950.8f, 192.0f, 1020.4f, 310.8f),
                        PathNode.QuadTo(1090.0f, 429.7f, 1090.0f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(155.0f, 570.9f),
                        PathNode.QuadTo(155.0f, 684.8f, 211.0f, 780.4f),
                        PathNode.QuadTo(266.9f, 876.0f, 362.5f, 931.9f),
                        PathNode.QuadTo(458.2f, 987.8f, 572.0f, 987.8f),
                        PathNode.QuadTo(685.8f, 987.8f, 781.4f, 931.9f),
                        PathNode.QuadTo(877.0f, 876.0f, 932.9f, 780.3f),
                        PathNode.QuadTo(988.8f, 684.7f, 988.8f, 570.9f),
                        PathNode.QuadTo(988.8f, 458.1f, 932.9f, 362.3f),
                        PathNode.QuadTo(877.0f, 266.6f, 781.3f, 210.3f),
                        PathNode.QuadTo(685.7f, 154.0f, 571.9f, 154.0f),
                        PathNode.QuadTo(458.1f, 154.0f, 362.5f, 210.3f),
                        PathNode.QuadTo(266.9f, 266.6f, 211.0f, 362.3f),
                        PathNode.QuadTo(155.0f, 458.0f, 155.0f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(623.0f, 338.0f),
                        PathNode.LineTo(623.0f, 674.5f),
                        PathNode.LineTo(735.3f, 562.3f),
                        PathNode.QuadTo(745.7f, 551.8f, 759.4f, 550.3f),
                        PathNode.QuadTo(773.2f, 548.8f, 785.6f, 561.3f),
                        PathNode.LineTo(807.6f, 583.3f),
                        PathNode.QuadTo(820.0f, 595.7f, 818.5f, 609.4f),
                        PathNode.QuadTo(817.0f, 623.2f, 807.6f, 632.6f),
                        PathNode.LineTo(612.6f, 828.6f),
                        PathNode.QuadTo(595.4f, 846.0f, 572.4f, 846.5f),
                        PathNode.QuadTo(549.4f, 847.0f, 533.3f, 830.6f),
                        PathNode.LineTo(336.3f, 632.6f),
                        PathNode.QuadTo(325.8f, 622.2f, 325.3f, 609.4f),
                        PathNode.QuadTo(324.8f, 596.7f, 336.0f, 584.5f),
                        PathNode.LineTo(356.0f, 562.5f),
                        PathNode.QuadTo(368.4f, 548.8f, 382.8f, 549.8f),
                        PathNode.QuadTo(397.2f, 550.8f, 408.6f, 562.3f),
                        PathNode.LineTo(520.8f, 674.5f),
                        PathNode.LineTo(520.8f, 338.0f),
                        PathNode.QuadTo(520.8f, 320.7f, 530.6f, 310.3f),
                        PathNode.QuadTo(540.4f, 299.9f, 556.9f, 299.9f),
                        PathNode.LineTo(584.9f, 299.9f),
                        PathNode.QuadTo(603.2f, 299.9f, 613.1f, 310.3f),
                        PathNode.QuadTo(623.0f, 320.7f, 623.0f, 338.0f),
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

val MiuixIcons.Glass.Demibold.Update: ImageVector
    get() {
        if (_updateDemibold != null) return _updateDemibold!!
        _updateDemibold = ImageVector.Builder(
            name = "Update.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1095.7f, 570.9f),
                        PathNode.QuadTo(1095.7f, 713.6f, 1025.4f, 833.8f),
                        PathNode.QuadTo(955.0f, 954.0f, 834.9f, 1024.8f),
                        PathNode.QuadTo(714.7f, 1095.7f, 572.0f, 1095.7f),
                        PathNode.QuadTo(429.3f, 1095.7f, 309.1f, 1024.9f),
                        PathNode.QuadTo(188.9f, 954.0f, 118.0f, 833.9f),
                        PathNode.QuadTo(47.1f, 713.7f, 47.1f, 571.0f),
                        PathNode.QuadTo(47.1f, 428.3f, 118.0f, 308.1f),
                        PathNode.QuadTo(188.9f, 187.9f, 309.0f, 117.5f),
                        PathNode.QuadTo(429.1f, 47.1f, 571.9f, 47.1f),
                        PathNode.QuadTo(714.6f, 47.1f, 834.8f, 117.5f),
                        PathNode.QuadTo(955.0f, 187.8f, 1025.3f, 308.0f),
                        PathNode.QuadTo(1095.7f, 428.1f, 1095.7f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(160.7f, 570.9f),
                        PathNode.QuadTo(160.7f, 683.3f, 215.9f, 777.6f),
                        PathNode.QuadTo(271.0f, 871.9f, 365.4f, 927.0f),
                        PathNode.QuadTo(459.7f, 982.1f, 572.0f, 982.1f),
                        PathNode.QuadTo(684.3f, 982.1f, 778.6f, 927.0f),
                        PathNode.QuadTo(872.9f, 871.8f, 928.0f, 777.5f),
                        PathNode.QuadTo(983.1f, 683.1f, 983.1f, 570.9f),
                        PathNode.QuadTo(983.1f, 459.6f, 928.0f, 365.1f),
                        PathNode.QuadTo(872.8f, 270.6f, 778.5f, 215.1f),
                        PathNode.QuadTo(684.1f, 159.7f, 571.9f, 159.7f),
                        PathNode.QuadTo(459.6f, 159.7f, 365.3f, 215.1f),
                        PathNode.QuadTo(271.0f, 270.6f, 215.9f, 365.0f),
                        PathNode.QuadTo(160.7f, 459.5f, 160.7f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(628.7f, 340.8f),
                        PathNode.LineTo(628.7f, 660.8f),
                        PathNode.LineTo(731.3f, 558.3f),
                        PathNode.QuadTo(743.4f, 546.1f, 759.4f, 544.6f),
                        PathNode.QuadTo(775.4f, 543.1f, 789.6f, 557.3f),
                        PathNode.LineTo(811.6f, 579.3f),
                        PathNode.QuadTo(825.7f, 593.4f, 824.2f, 609.4f),
                        PathNode.QuadTo(822.7f, 625.4f, 811.6f, 636.6f),
                        PathNode.LineTo(616.6f, 832.6f),
                        PathNode.QuadTo(597.9f, 851.7f, 572.4f, 852.2f),
                        PathNode.QuadTo(547.0f, 852.7f, 529.3f, 834.6f),
                        PathNode.LineTo(332.3f, 636.6f),
                        PathNode.QuadTo(320.1f, 624.4f, 319.6f, 609.4f),
                        PathNode.QuadTo(319.1f, 594.4f, 331.8f, 580.7f),
                        PathNode.LineTo(351.8f, 558.7f),
                        PathNode.QuadTo(366.0f, 543.1f, 382.7f, 544.1f),
                        PathNode.QuadTo(399.4f, 545.1f, 412.6f, 558.3f),
                        PathNode.LineTo(515.1f, 660.8f),
                        PathNode.LineTo(515.1f, 340.8f),
                        PathNode.QuadTo(515.1f, 321.3f, 526.5f, 309.2f),
                        PathNode.QuadTo(538.0f, 297.0f, 556.9f, 297.0f),
                        PathNode.LineTo(584.9f, 297.0f),
                        PathNode.QuadTo(605.4f, 297.0f, 617.1f, 309.2f),
                        PathNode.QuadTo(628.7f, 321.3f, 628.7f, 340.8f),
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
