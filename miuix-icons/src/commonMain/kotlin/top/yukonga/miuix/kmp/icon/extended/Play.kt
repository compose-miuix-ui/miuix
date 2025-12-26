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

val MiuixIcons.Play: ImageVector
    get() = MiuixIcons.Regular.Play

val MiuixIcons.Light.Play: ImageVector
    get() {
        if (_playLight != null) return _playLight!!
        _playLight = ImageVector.Builder(
            name = "Play.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1143.04f,
            viewportHeight = 1143.04f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -114.48000000000002f, translationY = 946.52f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(511.0f, -41.0f),
                        PathNode.LineTo(976.0f, 228.0f),
                        PathNode.QuadTo(1041.0f, 265.0f, 1067.0f, 284.0f),
                        PathNode.QuadTo(1093.0f, 303.0f, 1104.0f, 327.0f),
                        PathNode.QuadTo(1115.0f, 350.0f, 1115.0f, 375.5f),
                        PathNode.QuadTo(1115.0f, 401.0f, 1104.0f, 423.0f),
                        PathNode.QuadTo(1093.0f, 447.0f, 1068.0f, 465.5f),
                        PathNode.QuadTo(1043.0f, 484.0f, 976.0f, 522.0f),
                        PathNode.LineTo(511.0f, 791.0f),
                        PathNode.QuadTo(449.0f, 827.0f, 418.0f, 840.0f),
                        PathNode.QuadTo(387.0f, 853.0f, 362.0f, 851.0f),
                        PathNode.QuadTo(337.0f, 849.0f, 315.5f, 836.5f),
                        PathNode.QuadTo(294.0f, 824.0f, 279.0f, 803.0f),
                        PathNode.QuadTo(265.0f, 782.0f, 261.0f, 750.0f),
                        PathNode.QuadTo(257.0f, 718.0f, 257.0f, 645.0f),
                        PathNode.VerticalTo(107.0f),
                        PathNode.QuadTo(257.0f, 30.0f, 260.5f, -1.5f),
                        PathNode.QuadTo(264.0f, -33.0f, 279.0f, -55.0f),
                        PathNode.QuadTo(293.0f, -75.0f, 314.5f, -87.5f),
                        PathNode.QuadTo(336.0f, -100.0f, 360.0f, -101.0f),
                        PathNode.QuadTo(386.0f, -103.0f, 417.5f, -90.0f),
                        PathNode.QuadTo(449.0f, -77.0f, 511.0f, -41.0f),
                        PathNode.Close,
                        PathNode.MoveTo(329.0f, -18.0f),
                        PathNode.QuadTo(322.0f, -7.0f, 320.0f, 19.0f),
                        PathNode.QuadTo(318.0f, 45.0f, 318.0f, 107.0f),
                        PathNode.VerticalTo(645.0f),
                        PathNode.QuadTo(318.0f, 706.0f, 320.0f, 731.0f),
                        PathNode.QuadTo(322.0f, 756.0f, 328.0f, 766.0f),
                        PathNode.QuadTo(335.0f, 776.0f, 345.0f, 782.5f),
                        PathNode.QuadTo(355.0f, 789.0f, 368.0f, 790.0f),
                        PathNode.QuadTo(380.0f, 791.0f, 403.5f, 779.5f),
                        PathNode.QuadTo(427.0f, 768.0f, 480.0f, 738.0f),
                        PathNode.LineTo(947.0f, 468.0f),
                        PathNode.QuadTo(999.0f, 439.0f, 1019.5f, 424.5f),
                        PathNode.QuadTo(1040.0f, 410.0f, 1047.0f, 399.0f),
                        PathNode.QuadTo(1059.0f, 376.0f, 1048.0f, 352.0f),
                        PathNode.QuadTo(1042.0f, 341.0f, 1020.5f, 326.0f),
                        PathNode.QuadTo(999.0f, 311.0f, 947.0f, 282.0f),
                        PathNode.LineTo(480.0f, 12.0f),
                        PathNode.QuadTo(426.0f, -19.0f, 403.0f, -29.5f),
                        PathNode.QuadTo(380.0f, -40.0f, 368.0f, -39.0f),
                        PathNode.QuadTo(339.0f, -37.0f, 329.0f, -18.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playLight!!
    }

private var _playLight: ImageVector? = null

val MiuixIcons.Regular.Play: ImageVector
    get() {
        if (_playRegular != null) return _playRegular!!
        _playRegular = ImageVector.Builder(
            name = "Play.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1162.9090909090908f,
            viewportHeight = 1162.9090909090908f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -104.04545454545462f, translationY = 956.4545454545454f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(521.0f, -48.0f),
                        PathNode.LineTo(980.0f, 218.0f),
                        PathNode.QuadTo(1046.0f, 256.0f, 1073.0f, 276.0f),
                        PathNode.QuadTo(1100.0f, 296.0f, 1112.0f, 323.0f),
                        PathNode.QuadTo(1123.0f, 348.0f, 1123.0f, 376.0f),
                        PathNode.QuadTo(1123.0f, 404.0f, 1112.0f, 429.0f),
                        PathNode.QuadTo(1100.0f, 455.0f, 1073.0f, 475.5f),
                        PathNode.QuadTo(1046.0f, 496.0f, 980.0f, 533.0f),
                        PathNode.LineTo(521.0f, 799.0f),
                        PathNode.QuadTo(459.0f, 835.0f, 426.0f, 848.5f),
                        PathNode.QuadTo(393.0f, 862.0f, 365.0f, 859.0f),
                        PathNode.QuadTo(338.0f, 857.0f, 314.0f, 843.5f),
                        PathNode.QuadTo(290.0f, 830.0f, 273.0f, 807.0f),
                        PathNode.QuadTo(256.0f, 785.0f, 252.0f, 751.5f),
                        PathNode.QuadTo(248.0f, 718.0f, 248.0f, 642.0f),
                        PathNode.VerticalTo(111.0f),
                        PathNode.QuadTo(248.0f, 33.0f, 251.5f, 0.0f),
                        PathNode.QuadTo(255.0f, -33.0f, 272.0f, -57.0f),
                        PathNode.QuadTo(289.0f, -79.0f, 313.5f, -93.0f),
                        PathNode.QuadTo(338.0f, -107.0f, 364.0f, -109.0f),
                        PathNode.QuadTo(393.0f, -112.0f, 425.5f, -98.5f),
                        PathNode.QuadTo(458.0f, -85.0f, 521.0f, -48.0f),
                        PathNode.Close,
                        PathNode.MoveTo(346.0f, -4.0f),
                        PathNode.QuadTo(341.0f, 3.0f, 339.5f, 26.5f),
                        PathNode.QuadTo(338.0f, 50.0f, 338.0f, 111.0f),
                        PathNode.VerticalTo(642.0f),
                        PathNode.QuadTo(338.0f, 701.0f, 339.5f, 723.0f),
                        PathNode.QuadTo(341.0f, 745.0f, 345.0f, 752.0f),
                        PathNode.QuadTo(350.0f, 759.0f, 357.5f, 764.0f),
                        PathNode.QuadTo(365.0f, 769.0f, 374.0f, 770.0f),
                        PathNode.QuadTo(381.0f, 771.0f, 403.0f, 760.0f),
                        PathNode.QuadTo(425.0f, 749.0f, 475.0f, 721.0f),
                        PathNode.LineTo(936.0f, 455.0f),
                        PathNode.QuadTo(983.0f, 428.0f, 1002.5f, 414.5f),
                        PathNode.QuadTo(1022.0f, 401.0f, 1027.0f, 393.0f),
                        PathNode.QuadTo(1037.0f, 377.0f, 1029.0f, 359.0f),
                        PathNode.QuadTo(1025.0f, 351.0f, 1005.5f, 337.5f),
                        PathNode.QuadTo(986.0f, 324.0f, 936.0f, 296.0f),
                        PathNode.LineTo(475.0f, 30.0f),
                        PathNode.QuadTo(423.0f, 0.0f, 403.0f, -10.0f),
                        PathNode.QuadTo(383.0f, -20.0f, 374.0f, -19.0f),
                        PathNode.QuadTo(355.0f, -17.0f, 346.0f, -4.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playRegular!!
    }

private var _playRegular: ImageVector? = null

val MiuixIcons.Heavy.Play: ImageVector
    get() {
        if (_playHeavy != null) return _playHeavy!!
        _playHeavy = ImageVector.Builder(
            name = "Play.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1195.6549806949806f,
            viewportHeight = 1195.6549806949806f,
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -86.67250965250969f, translationY = 973.1522007722008f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(527.0f, -59.0f),
                        PathNode.LineTo(986.0f, 207.0f),
                        PathNode.QuadTo(1054.0f, 245.0f, 1082.5f, 266.5f),
                        PathNode.QuadTo(1111.0f, 288.0f, 1124.0f, 317.0f),
                        PathNode.QuadTo(1135.0f, 345.0f, 1135.0f, 376.0f),
                        PathNode.QuadTo(1135.0f, 407.0f, 1124.0f, 435.0f),
                        PathNode.QuadTo(1111.0f, 464.0f, 1082.5f, 485.0f),
                        PathNode.QuadTo(1054.0f, 506.0f, 986.0f, 544.0f),
                        PathNode.LineTo(527.0f, 810.0f),
                        PathNode.QuadTo(464.0f, 847.0f, 429.5f, 861.5f),
                        PathNode.QuadTo(395.0f, 876.0f, 363.0f, 873.0f),
                        PathNode.QuadTo(333.0f, 870.0f, 306.5f, 855.0f),
                        PathNode.QuadTo(280.0f, 840.0f, 262.0f, 815.0f),
                        PathNode.QuadTo(243.0f, 790.0f, 238.5f, 754.5f),
                        PathNode.QuadTo(234.0f, 719.0f, 234.0f, 642.0f),
                        PathNode.VerticalTo(111.0f),
                        PathNode.QuadTo(234.0f, 32.0f, 238.5f, -3.0f),
                        PathNode.QuadTo(243.0f, -38.0f, 261.0f, -65.0f),
                        PathNode.QuadTo(280.0f, -89.0f, 306.5f, -104.5f),
                        PathNode.QuadTo(333.0f, -120.0f, 363.0f, -122.0f),
                        PathNode.QuadTo(395.0f, -126.0f, 429.0f, -111.5f),
                        PathNode.QuadTo(463.0f, -97.0f, 527.0f, -59.0f),
                        PathNode.Close,
                        PathNode.MoveTo(356.0f, 5.0f),
                        PathNode.QuadTo(353.0f, 10.0f, 352.0f, 36.5f),
                        PathNode.QuadTo(351.0f, 63.0f, 351.0f, 111.0f),
                        PathNode.VerticalTo(642.0f),
                        PathNode.QuadTo(351.0f, 690.0f, 352.0f, 715.5f),
                        PathNode.QuadTo(353.0f, 741.0f, 356.0f, 746.0f),
                        PathNode.QuadTo(358.0f, 751.0f, 363.5f, 754.0f),
                        PathNode.QuadTo(369.0f, 757.0f, 375.0f, 757.0f),
                        PathNode.QuadTo(380.0f, 757.0f, 404.0f, 744.5f),
                        PathNode.QuadTo(428.0f, 732.0f, 468.0f, 710.0f),
                        PathNode.LineTo(929.0f, 444.0f),
                        PathNode.QuadTo(968.0f, 421.0f, 990.0f, 407.0f),
                        PathNode.QuadTo(1012.0f, 393.0f, 1016.0f, 387.0f),
                        PathNode.QuadTo(1022.0f, 376.0f, 1017.0f, 365.0f),
                        PathNode.QuadTo(1013.0f, 358.0f, 995.5f, 347.0f),
                        PathNode.QuadTo(978.0f, 336.0f, 929.0f, 307.0f),
                        PathNode.LineTo(468.0f, 41.0f),
                        PathNode.QuadTo(427.0f, 17.0f, 404.5f, 5.5f),
                        PathNode.QuadTo(382.0f, -6.0f, 376.0f, -6.0f),
                        PathNode.QuadTo(362.0f, -6.0f, 356.0f, 5.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _playHeavy!!
    }

private var _playHeavy: ImageVector? = null
