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

val MiuixIcons.Reply: ImageVector
    get() = MiuixIcons.Regular.Reply

val MiuixIcons.Light.Reply: ImageVector
    get() {
        if (_replyLight != null) return _replyLight!!
        _replyLight = ImageVector.Builder(
            name = "Reply.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1231.2f,
            viewportHeight = 1231.2f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1231.2f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1128.6f, 667.1f),
                        PathNode.QuadTo(1128.6f, 785.1f, 1059.0f, 881.2f),
                        PathNode.QuadTo(989.3f, 977.4f, 871.5f, 1032.2f),
                        PathNode.QuadTo(753.6f, 1087.1f, 615.4f, 1087.1f),
                        PathNode.QuadTo(487.0f, 1087.1f, 368.3f, 1034.8f),
                        PathNode.QuadTo(249.5f, 982.6f, 176.1f, 886.8f),
                        PathNode.QuadTo(102.6f, 791.0f, 102.6f, 667.1f),
                        PathNode.QuadTo(102.6f, 581.1f, 141.8f, 506.2f),
                        PathNode.QuadTo(181.0f, 431.2f, 247.8f, 375.2f),
                        PathNode.QuadTo(314.6f, 319.1f, 397.6f, 286.1f),
                        PathNode.QuadTo(410.6f, 282.1f, 416.1f, 270.6f),
                        PathNode.QuadTo(421.6f, 259.1f, 417.6f, 246.1f),
                        PathNode.LineTo(388.6f, 154.1f),
                        PathNode.QuadTo(386.6f, 149.1f, 390.0f, 146.6f),
                        PathNode.QuadTo(393.5f, 144.1f, 398.6f, 145.1f),
                        PathNode.LineTo(720.6f, 237.1f),
                        PathNode.QuadTo(908.6f, 291.1f, 1018.6f, 392.6f),
                        PathNode.QuadTo(1128.6f, 494.1f, 1128.6f, 667.1f),
                        PathNode.Close,
                        PathNode.MoveTo(419.3f, 344.8f),
                        PathNode.QuadTo(349.8f, 371.2f, 292.2f, 418.6f),
                        PathNode.QuadTo(234.6f, 466.1f, 200.1f, 529.8f),
                        PathNode.QuadTo(165.6f, 593.5f, 165.6f, 667.1f),
                        PathNode.QuadTo(165.6f, 773.3f, 230.6f, 854.9f),
                        PathNode.QuadTo(295.7f, 936.6f, 399.4f, 980.9f),
                        PathNode.QuadTo(503.1f, 1025.1f, 615.6f, 1025.1f),
                        PathNode.QuadTo(743.6f, 1025.1f, 846.6f, 976.1f),
                        PathNode.QuadTo(949.6f, 927.1f, 1007.6f, 844.7f),
                        PathNode.QuadTo(1065.6f, 762.3f, 1065.6f, 667.4f),
                        PathNode.QuadTo(1065.6f, 562.1f, 1018.6f, 490.1f),
                        PathNode.QuadTo(971.6f, 418.1f, 892.6f, 373.1f),
                        PathNode.QuadTo(813.6f, 328.1f, 702.6f, 296.1f),
                        PathNode.LineTo(475.6f, 230.1f),
                        PathNode.QuadTo(485.6f, 269.1f, 470.1f, 300.1f),
                        PathNode.QuadTo(454.6f, 331.1f, 419.3f, 344.8f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyLight!!
    }

private var _replyLight: ImageVector? = null

val MiuixIcons.Normal.Reply: ImageVector
    get() {
        if (_replyNormal != null) return _replyNormal!!
        _replyNormal = ImageVector.Builder(
            name = "Reply.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1254.3f,
            viewportHeight = 1254.3f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1254.3f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1149.8f, 680.7f),
                        PathNode.QuadTo(1149.8f, 802.1f, 1078.9f, 900.3f),
                        PathNode.QuadTo(1007.9f, 998.4f, 887.9f, 1054.3f),
                        PathNode.QuadTo(767.9f, 1110.3f, 627.1f, 1110.3f),
                        PathNode.QuadTo(496.2f, 1110.3f, 375.2f, 1056.9f),
                        PathNode.QuadTo(254.3f, 1003.4f, 179.4f, 905.4f),
                        PathNode.QuadTo(104.5f, 807.4f, 104.5f, 680.7f),
                        PathNode.QuadTo(104.5f, 592.0f, 144.6f, 515.3f),
                        PathNode.QuadTo(184.7f, 438.6f, 253.0f, 381.2f),
                        PathNode.QuadTo(321.3f, 323.8f, 405.7f, 290.8f),
                        PathNode.QuadTo(414.6f, 288.1f, 418.7f, 280.1f),
                        PathNode.QuadTo(422.8f, 272.0f, 420.2f, 262.5f),
                        PathNode.LineTo(387.8f, 155.3f),
                        PathNode.QuadTo(385.8f, 150.3f, 389.6f, 147.1f),
                        PathNode.QuadTo(393.4f, 144.0f, 399.1f, 145.0f),
                        PathNode.LineTo(737.6f, 242.5f),
                        PathNode.QuadTo(927.0f, 297.1f, 1038.4f, 400.4f),
                        PathNode.QuadTo(1149.8f, 503.6f, 1149.8f, 680.7f),
                        PathNode.Close,
                        PathNode.MoveTo(434.5f, 367.6f),
                        PathNode.QuadTo(366.7f, 393.0f, 310.6f, 438.8f),
                        PathNode.QuadTo(254.4f, 484.5f, 220.6f, 546.7f),
                        PathNode.QuadTo(186.8f, 608.9f, 186.8f, 680.7f),
                        PathNode.QuadTo(186.8f, 784.0f, 250.4f, 863.5f),
                        PathNode.QuadTo(314.0f, 943.0f, 415.6f, 986.0f),
                        PathNode.QuadTo(517.1f, 1029.1f, 627.2f, 1029.1f),
                        PathNode.QuadTo(752.4f, 1029.1f, 853.0f, 981.1f),
                        PathNode.QuadTo(953.6f, 933.1f, 1010.6f, 853.1f),
                        PathNode.QuadTo(1067.5f, 773.0f, 1067.5f, 680.8f),
                        PathNode.QuadTo(1067.5f, 578.5f, 1021.2f, 507.8f),
                        PathNode.QuadTo(974.9f, 437.2f, 898.0f, 393.9f),
                        PathNode.QuadTo(821.0f, 350.6f, 714.1f, 320.0f),
                        PathNode.LineTo(500.9f, 258.1f),
                        PathNode.QuadTo(505.4f, 294.4f, 487.1f, 324.4f),
                        PathNode.QuadTo(468.9f, 354.3f, 434.5f, 367.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyNormal!!
    }

private var _replyNormal: ImageVector? = null

val MiuixIcons.Regular.Reply: ImageVector
    get() {
        if (_replyRegular != null) return _replyRegular!!
        _replyRegular = ImageVector.Builder(
            name = "Reply.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1264.8f,
            viewportHeight = 1264.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1264.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1159.4f, 686.9f),
                        PathNode.QuadTo(1159.4f, 809.9f, 1087.9f, 908.9f),
                        PathNode.QuadTo(1016.4f, 1007.9f, 895.4f, 1064.4f),
                        PathNode.QuadTo(774.4f, 1120.9f, 632.4f, 1120.9f),
                        PathNode.QuadTo(500.4f, 1120.9f, 378.4f, 1066.9f),
                        PathNode.QuadTo(256.4f, 1012.9f, 180.9f, 913.9f),
                        PathNode.QuadTo(105.4f, 814.9f, 105.4f, 686.9f),
                        PathNode.QuadTo(105.4f, 596.9f, 145.9f, 519.4f),
                        PathNode.QuadTo(186.4f, 441.9f, 255.4f, 383.9f),
                        PathNode.QuadTo(324.4f, 325.9f, 409.4f, 292.9f),
                        PathNode.QuadTo(416.4f, 290.9f, 419.9f, 284.4f),
                        PathNode.QuadTo(423.4f, 277.9f, 421.4f, 269.9f),
                        PathNode.LineTo(387.4f, 155.9f),
                        PathNode.QuadTo(385.4f, 150.9f, 389.4f, 147.4f),
                        PathNode.QuadTo(393.4f, 143.9f, 399.4f, 144.9f),
                        PathNode.LineTo(745.4f, 244.9f),
                        PathNode.QuadTo(935.4f, 299.9f, 1047.4f, 403.9f),
                        PathNode.QuadTo(1159.4f, 507.9f, 1159.4f, 686.9f),
                        PathNode.Close,
                        PathNode.MoveTo(441.4f, 377.9f),
                        PathNode.QuadTo(374.4f, 402.9f, 318.9f, 447.9f),
                        PathNode.QuadTo(263.4f, 492.9f, 229.9f, 554.4f),
                        PathNode.QuadTo(196.4f, 615.9f, 196.4f, 686.9f),
                        PathNode.QuadTo(196.4f, 788.9f, 259.4f, 867.4f),
                        PathNode.QuadTo(322.4f, 945.9f, 422.9f, 988.4f),
                        PathNode.QuadTo(523.4f, 1030.9f, 632.4f, 1030.9f),
                        PathNode.QuadTo(756.4f, 1030.9f, 855.9f, 983.4f),
                        PathNode.QuadTo(955.4f, 935.9f, 1011.9f, 856.9f),
                        PathNode.QuadTo(1068.4f, 777.9f, 1068.4f, 686.9f),
                        PathNode.QuadTo(1068.4f, 585.9f, 1022.4f, 515.9f),
                        PathNode.QuadTo(976.4f, 445.9f, 900.4f, 403.4f),
                        PathNode.QuadTo(824.4f, 360.9f, 719.4f, 330.9f),
                        PathNode.LineTo(512.4f, 270.9f),
                        PathNode.QuadTo(514.4f, 305.9f, 494.9f, 335.4f),
                        PathNode.QuadTo(475.4f, 364.9f, 441.4f, 377.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyRegular!!
    }

private var _replyRegular: ImageVector? = null

val MiuixIcons.Medium.Reply: ImageVector
    get() {
        if (_replyMedium != null) return _replyMedium!!
        _replyMedium = ImageVector.Builder(
            name = "Reply.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1284.6f,
            viewportHeight = 1284.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1284.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1177.5f, 697.4f),
                        PathNode.QuadTo(1177.5f, 822.7f, 1104.5f, 923.8f),
                        PathNode.QuadTo(1031.6f, 1024.8f, 908.8f, 1082.2f),
                        PathNode.QuadTo(786.0f, 1139.6f, 642.3f, 1139.6f),
                        PathNode.QuadTo(508.5f, 1139.6f, 384.8f, 1084.7f),
                        PathNode.QuadTo(261.0f, 1029.8f, 184.0f, 928.8f),
                        PathNode.QuadTo(107.0f, 827.7f, 107.0f, 697.4f),
                        PathNode.QuadTo(107.0f, 606.2f, 147.5f, 527.8f),
                        PathNode.QuadTo(188.0f, 449.4f, 257.0f, 390.5f),
                        PathNode.QuadTo(326.0f, 331.7f, 412.2f, 297.5f),
                        PathNode.QuadTo(418.0f, 295.5f, 421.0f, 290.2f),
                        PathNode.QuadTo(423.9f, 284.8f, 421.9f, 277.4f),
                        PathNode.LineTo(389.6f, 168.7f),
                        PathNode.QuadTo(386.5f, 159.0f, 393.7f, 152.0f),
                        PathNode.QuadTo(400.9f, 145.0f, 411.6f, 147.7f),
                        PathNode.LineTo(758.2f, 247.7f),
                        PathNode.QuadTo(950.0f, 303.3f, 1063.8f, 409.7f),
                        PathNode.QuadTo(1177.5f, 516.0f, 1177.5f, 697.4f),
                        PathNode.Close,
                        PathNode.MoveTo(454.2f, 396.0f),
                        PathNode.QuadTo(389.0f, 420.4f, 334.7f, 464.5f),
                        PathNode.QuadTo(280.3f, 508.7f, 247.4f, 568.4f),
                        PathNode.QuadTo(214.5f, 628.1f, 214.5f, 697.4f),
                        PathNode.QuadTo(214.5f, 797.0f, 276.0f, 873.5f),
                        PathNode.QuadTo(337.6f, 949.9f, 436.3f, 991.5f),
                        PathNode.QuadTo(535.0f, 1033.1f, 642.3f, 1033.1f),
                        PathNode.QuadTo(764.5f, 1033.1f, 862.3f, 986.8f),
                        PathNode.QuadTo(960.0f, 940.5f, 1015.0f, 863.3f),
                        PathNode.QuadTo(1070.0f, 786.0f, 1070.0f, 697.4f),
                        PathNode.QuadTo(1070.0f, 597.5f, 1024.3f, 529.3f),
                        PathNode.QuadTo(978.6f, 461.1f, 902.6f, 419.5f),
                        PathNode.QuadTo(826.6f, 377.8f, 720.5f, 347.3f),
                        PathNode.LineTo(529.3f, 292.0f),
                        PathNode.QuadTo(529.0f, 326.4f, 508.3f, 354.7f),
                        PathNode.QuadTo(487.6f, 383.0f, 454.2f, 396.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyMedium!!
    }

private var _replyMedium: ImageVector? = null

val MiuixIcons.Demibold.Reply: ImageVector
    get() {
        if (_replyDemibold != null) return _replyDemibold!!
        _replyDemibold = ImageVector.Builder(
            name = "Reply.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1298.4f,
            viewportHeight = 1298.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1298.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1190.2f, 704.7f),
                        PathNode.QuadTo(1190.2f, 831.7f, 1116.2f, 934.2f),
                        PathNode.QuadTo(1042.2f, 1036.7f, 918.2f, 1094.7f),
                        PathNode.QuadTo(794.2f, 1152.7f, 649.2f, 1152.7f),
                        PathNode.QuadTo(514.2f, 1152.7f, 389.2f, 1097.2f),
                        PathNode.QuadTo(264.2f, 1041.7f, 186.2f, 939.2f),
                        PathNode.QuadTo(108.2f, 836.7f, 108.2f, 704.7f),
                        PathNode.QuadTo(108.2f, 612.7f, 148.7f, 533.7f),
                        PathNode.QuadTo(189.2f, 454.7f, 258.2f, 395.2f),
                        PathNode.QuadTo(327.2f, 335.7f, 414.2f, 300.7f),
                        PathNode.QuadTo(419.2f, 298.7f, 421.7f, 294.2f),
                        PathNode.QuadTo(424.2f, 289.7f, 422.2f, 282.7f),
                        PathNode.LineTo(391.2f, 177.7f),
                        PathNode.QuadTo(387.2f, 164.7f, 396.7f, 155.2f),
                        PathNode.QuadTo(406.2f, 145.7f, 420.2f, 149.7f),
                        PathNode.LineTo(767.2f, 249.7f),
                        PathNode.QuadTo(960.2f, 305.7f, 1075.2f, 413.7f),
                        PathNode.QuadTo(1190.2f, 521.7f, 1190.2f, 704.7f),
                        PathNode.Close,
                        PathNode.MoveTo(463.2f, 408.7f),
                        PathNode.QuadTo(399.2f, 432.7f, 345.7f, 476.2f),
                        PathNode.QuadTo(292.2f, 519.7f, 259.7f, 578.2f),
                        PathNode.QuadTo(227.2f, 636.7f, 227.2f, 704.7f),
                        PathNode.QuadTo(227.2f, 802.7f, 287.7f, 877.7f),
                        PathNode.QuadTo(348.2f, 952.7f, 445.7f, 993.7f),
                        PathNode.QuadTo(543.2f, 1034.7f, 649.2f, 1034.7f),
                        PathNode.QuadTo(770.2f, 1034.7f, 866.7f, 989.2f),
                        PathNode.QuadTo(963.2f, 943.7f, 1017.2f, 867.7f),
                        PathNode.QuadTo(1071.2f, 791.7f, 1071.2f, 704.7f),
                        PathNode.QuadTo(1071.2f, 605.7f, 1025.7f, 538.7f),
                        PathNode.QuadTo(980.2f, 471.7f, 904.2f, 430.7f),
                        PathNode.QuadTo(828.2f, 389.7f, 721.2f, 358.7f),
                        PathNode.LineTo(541.2f, 306.7f),
                        PathNode.QuadTo(539.2f, 340.7f, 517.7f, 368.2f),
                        PathNode.QuadTo(496.2f, 395.7f, 463.2f, 408.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _replyDemibold!!
    }

private var _replyDemibold: ImageVector? = null
