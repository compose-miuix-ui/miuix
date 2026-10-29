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

val MiuixIcons.Glass.Movie: ImageVector
    get() = MiuixIcons.Glass.Regular.Movie

val MiuixIcons.Glass.Light.Movie: ImageVector
    get() {
        if (_movieLight != null) return _movieLight!!
        _movieLight = ImageVector.Builder(
            name = "Movie.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(985.2f, 287.7f),
                        PathNode.QuadTo(1022.7f, 307.5f, 1042.4f, 344.9f),
                        PathNode.QuadTo(1051.5f, 363.7f, 1053.8f, 391.7f),
                        PathNode.QuadTo(1056.1f, 419.6f, 1056.1f, 488.7f),
                        PathNode.LineTo(1056.1f, 809.5f),
                        PathNode.QuadTo(1056.1f, 878.0f, 1053.8f, 906.3f),
                        PathNode.QuadTo(1051.5f, 934.5f, 1042.4f, 953.7f),
                        PathNode.QuadTo(1023.7f, 989.7f, 985.2f, 1009.5f),
                        PathNode.QuadTo(966.4f, 1018.6f, 938.5f, 1020.9f),
                        PathNode.QuadTo(910.5f, 1023.2f, 841.4f, 1023.2f),
                        PathNode.LineTo(301.4f, 1023.2f),
                        PathNode.QuadTo(232.3f, 1023.2f, 204.4f, 1020.9f),
                        PathNode.QuadTo(176.4f, 1018.6f, 157.7f, 1009.5f),
                        PathNode.QuadTo(119.2f, 989.7f, 100.4f, 953.7f),
                        PathNode.QuadTo(91.3f, 934.5f, 89.0f, 906.3f),
                        PathNode.QuadTo(86.8f, 878.0f, 86.8f, 809.5f),
                        PathNode.LineTo(86.8f, 488.7f),
                        PathNode.QuadTo(86.8f, 419.6f, 89.0f, 391.7f),
                        PathNode.QuadTo(91.3f, 363.7f, 100.4f, 344.9f),
                        PathNode.QuadTo(120.2f, 307.5f, 157.7f, 287.7f),
                        PathNode.QuadTo(176.4f, 278.6f, 204.4f, 276.3f),
                        PathNode.QuadTo(232.3f, 274.0f, 301.4f, 274.0f),
                        PathNode.LineTo(841.4f, 274.0f),
                        PathNode.QuadTo(910.5f, 274.0f, 938.5f, 276.3f),
                        PathNode.QuadTo(966.4f, 278.6f, 985.2f, 287.7f),
                        PathNode.Close,
                        PathNode.MoveTo(830.4f, 183.5f),
                        PathNode.QuadTo(830.4f, 190.4f, 825.7f, 195.2f),
                        PathNode.QuadTo(820.9f, 199.9f, 814.0f, 199.9f),
                        PathNode.LineTo(328.9f, 199.9f),
                        PathNode.QuadTo(322.0f, 199.9f, 317.2f, 195.2f),
                        PathNode.QuadTo(312.4f, 190.4f, 312.4f, 183.0f),
                        PathNode.QuadTo(313.8f, 156.5f, 332.2f, 138.1f),
                        PathNode.QuadTo(350.5f, 119.7f, 376.5f, 119.7f),
                        PathNode.LineTo(766.3f, 119.7f),
                        PathNode.QuadTo(792.3f, 119.7f, 810.7f, 138.1f),
                        PathNode.QuadTo(829.1f, 156.5f, 830.4f, 183.5f),
                        PathNode.Close,
                        PathNode.MoveTo(454.5f, 493.9f),
                        PathNode.QuadTo(450.9f, 499.6f, 450.1f, 512.8f),
                        PathNode.QuadTo(449.3f, 526.0f, 449.3f, 553.6f),
                        PathNode.LineTo(449.3f, 743.6f),
                        PathNode.QuadTo(449.3f, 771.2f, 450.1f, 784.6f),
                        PathNode.QuadTo(450.9f, 798.0f, 454.5f, 803.3f),
                        PathNode.QuadTo(462.9f, 815.2f, 477.8f, 815.7f),
                        PathNode.QuadTo(484.0f, 816.3f, 495.8f, 811.0f),
                        PathNode.QuadTo(507.5f, 805.8f, 532.0f, 792.0f),
                        PathNode.LineTo(697.0f, 696.0f),
                        PathNode.LineTo(710.0f, 688.0f),
                        PathNode.QuadTo(726.3f, 678.4f, 734.8f, 672.3f),
                        PathNode.QuadTo(743.3f, 666.3f, 745.4f, 661.0f),
                        PathNode.QuadTo(752.1f, 648.6f, 745.4f, 636.2f),
                        PathNode.QuadTo(743.3f, 630.9f, 734.8f, 624.9f),
                        PathNode.QuadTo(726.3f, 618.8f, 710.0f, 609.2f),
                        PathNode.LineTo(697.0f, 601.2f),
                        PathNode.LineTo(532.0f, 506.2f),
                        PathNode.QuadTo(512.1f, 494.4f, 498.3f, 487.9f),
                        PathNode.QuadTo(484.5f, 481.4f, 477.8f, 481.5f),
                        PathNode.QuadTo(462.9f, 481.6f, 454.5f, 493.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _movieLight!!
    }

private var _movieLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Movie: ImageVector
    get() {
        if (_movieNormal != null) return _movieNormal!!
        _movieNormal = ImageVector.Builder(
            name = "Movie.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(989.5f, 289.2f),
                        PathNode.QuadTo(1029.4f, 310.5f, 1050.7f, 350.4f),
                        PathNode.QuadTo(1060.4f, 370.7f, 1062.8f, 399.7f),
                        PathNode.QuadTo(1065.3f, 428.8f, 1065.3f, 498.5f),
                        PathNode.LineTo(1065.3f, 807.0f),
                        PathNode.QuadTo(1065.3f, 875.9f, 1062.8f, 905.3f),
                        PathNode.QuadTo(1060.4f, 934.8f, 1050.7f, 955.2f),
                        PathNode.QuadTo(1030.4f, 994.0f, 989.5f, 1015.3f),
                        PathNode.QuadTo(969.2f, 1025.0f, 940.2f, 1027.4f),
                        PathNode.QuadTo(911.2f, 1029.9f, 841.4f, 1029.9f),
                        PathNode.LineTo(301.4f, 1029.9f),
                        PathNode.QuadTo(231.7f, 1029.9f, 202.7f, 1027.4f),
                        PathNode.QuadTo(173.7f, 1025.0f, 153.4f, 1015.3f),
                        PathNode.QuadTo(112.5f, 994.0f, 92.2f, 955.2f),
                        PathNode.QuadTo(82.5f, 934.8f, 80.0f, 905.3f),
                        PathNode.QuadTo(77.6f, 875.9f, 77.6f, 807.0f),
                        PathNode.LineTo(77.6f, 498.5f),
                        PathNode.QuadTo(77.6f, 428.8f, 80.0f, 399.7f),
                        PathNode.QuadTo(82.5f, 370.7f, 92.2f, 350.4f),
                        PathNode.QuadTo(113.5f, 310.5f, 153.4f, 289.2f),
                        PathNode.QuadTo(173.7f, 279.5f, 202.7f, 277.1f),
                        PathNode.QuadTo(231.7f, 274.7f, 301.4f, 274.7f),
                        PathNode.LineTo(841.4f, 274.7f),
                        PathNode.QuadTo(911.2f, 274.7f, 940.2f, 277.1f),
                        PathNode.QuadTo(969.2f, 279.5f, 989.5f, 289.2f),
                        PathNode.Close,
                        PathNode.MoveTo(830.4f, 183.8f),
                        PathNode.QuadTo(830.4f, 192.8f, 824.1f, 199.1f),
                        PathNode.QuadTo(817.8f, 205.4f, 808.8f, 205.4f),
                        PathNode.LineTo(334.1f, 205.4f),
                        PathNode.QuadTo(325.0f, 205.4f, 318.7f, 199.1f),
                        PathNode.QuadTo(312.4f, 192.8f, 312.4f, 183.7f),
                        PathNode.QuadTo(312.8f, 154.0f, 333.4f, 133.5f),
                        PathNode.QuadTo(353.9f, 113.0f, 382.7f, 113.0f),
                        PathNode.LineTo(760.2f, 113.0f),
                        PathNode.QuadTo(789.0f, 113.0f, 809.5f, 133.5f),
                        PathNode.QuadTo(830.0f, 154.0f, 830.4f, 183.8f),
                        PathNode.Close,
                        PathNode.MoveTo(449.7f, 493.9f),
                        PathNode.QuadTo(445.1f, 500.5f, 444.1f, 513.6f),
                        PathNode.QuadTo(443.2f, 526.7f, 443.2f, 557.3f),
                        PathNode.LineTo(443.2f, 747.3f),
                        PathNode.QuadTo(443.2f, 777.9f, 444.1f, 791.0f),
                        PathNode.QuadTo(445.1f, 804.2f, 449.7f, 810.6f),
                        PathNode.QuadTo(459.8f, 824.6f, 476.8f, 825.5f),
                        PathNode.QuadTo(484.3f, 826.3f, 496.2f, 821.3f),
                        PathNode.QuadTo(508.2f, 816.2f, 535.0f, 800.9f),
                        PathNode.LineTo(700.0f, 704.9f),
                        PathNode.LineTo(713.0f, 696.9f),
                        PathNode.QuadTo(731.2f, 686.3f, 739.7f, 680.0f),
                        PathNode.QuadTo(748.2f, 673.6f, 750.9f, 667.2f),
                        PathNode.QuadTo(758.5f, 652.3f, 750.9f, 637.4f),
                        PathNode.QuadTo(748.2f, 630.9f, 739.7f, 624.6f),
                        PathNode.QuadTo(731.2f, 618.2f, 713.0f, 607.6f),
                        PathNode.LineTo(700.0f, 599.6f),
                        PathNode.LineTo(535.0f, 504.6f),
                        PathNode.QuadTo(513.0f, 491.3f, 499.1f, 484.8f),
                        PathNode.QuadTo(485.2f, 478.3f, 476.8f, 479.0f),
                        PathNode.QuadTo(459.8f, 479.8f, 449.7f, 493.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _movieNormal!!
    }

private var _movieNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Movie: ImageVector
    get() {
        if (_movieRegular != null) return _movieRegular!!
        _movieRegular = ImageVector.Builder(
            name = "Movie.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(991.4f, 289.9f),
                        PathNode.QuadTo(1032.4f, 311.9f, 1054.4f, 352.9f),
                        PathNode.QuadTo(1064.4f, 373.9f, 1066.9f, 403.4f),
                        PathNode.QuadTo(1069.4f, 432.9f, 1069.4f, 502.9f),
                        PathNode.LineTo(1069.4f, 805.9f),
                        PathNode.QuadTo(1069.4f, 874.9f, 1066.9f, 904.9f),
                        PathNode.QuadTo(1064.4f, 934.9f, 1054.4f, 955.9f),
                        PathNode.QuadTo(1033.4f, 995.9f, 991.4f, 1017.9f),
                        PathNode.QuadTo(970.4f, 1027.9f, 940.9f, 1030.4f),
                        PathNode.QuadTo(911.4f, 1032.9f, 841.4f, 1032.9f),
                        PathNode.LineTo(301.4f, 1032.9f),
                        PathNode.QuadTo(231.4f, 1032.9f, 201.9f, 1030.4f),
                        PathNode.QuadTo(172.4f, 1027.9f, 151.4f, 1017.9f),
                        PathNode.QuadTo(109.4f, 995.9f, 88.4f, 955.9f),
                        PathNode.QuadTo(78.4f, 934.9f, 75.9f, 904.9f),
                        PathNode.QuadTo(73.4f, 874.9f, 73.4f, 805.9f),
                        PathNode.LineTo(73.4f, 502.9f),
                        PathNode.QuadTo(73.4f, 432.9f, 75.9f, 403.4f),
                        PathNode.QuadTo(78.4f, 373.9f, 88.4f, 352.9f),
                        PathNode.QuadTo(110.4f, 311.9f, 151.4f, 289.9f),
                        PathNode.QuadTo(172.4f, 279.9f, 201.9f, 277.4f),
                        PathNode.QuadTo(231.4f, 274.9f, 301.4f, 274.9f),
                        PathNode.LineTo(841.4f, 274.9f),
                        PathNode.QuadTo(911.4f, 274.9f, 940.9f, 277.4f),
                        PathNode.QuadTo(970.4f, 279.9f, 991.4f, 289.9f),
                        PathNode.Close,
                        PathNode.MoveTo(830.4f, 183.9f),
                        PathNode.QuadTo(830.4f, 193.9f, 823.4f, 200.9f),
                        PathNode.QuadTo(816.4f, 207.9f, 806.4f, 207.9f),
                        PathNode.LineTo(336.4f, 207.9f),
                        PathNode.QuadTo(326.4f, 207.9f, 319.4f, 200.9f),
                        PathNode.QuadTo(312.4f, 193.9f, 312.4f, 183.9f),
                        PathNode.QuadTo(312.4f, 152.9f, 333.9f, 131.4f),
                        PathNode.QuadTo(355.4f, 109.9f, 385.4f, 109.9f),
                        PathNode.LineTo(757.4f, 109.9f),
                        PathNode.QuadTo(787.4f, 109.9f, 808.9f, 131.4f),
                        PathNode.QuadTo(830.4f, 152.9f, 830.4f, 183.9f),
                        PathNode.Close,
                        PathNode.MoveTo(447.4f, 493.9f),
                        PathNode.QuadTo(442.4f, 500.9f, 441.4f, 513.9f),
                        PathNode.QuadTo(440.4f, 526.9f, 440.4f, 558.9f),
                        PathNode.LineTo(440.4f, 748.9f),
                        PathNode.QuadTo(440.4f, 780.9f, 441.4f, 793.9f),
                        PathNode.QuadTo(442.4f, 806.9f, 447.4f, 813.9f),
                        PathNode.QuadTo(458.4f, 828.9f, 476.4f, 829.9f),
                        PathNode.QuadTo(484.4f, 830.9f, 496.4f, 825.9f),
                        PathNode.QuadTo(508.4f, 820.9f, 536.4f, 804.9f),
                        PathNode.LineTo(701.4f, 708.9f),
                        PathNode.LineTo(714.4f, 700.9f),
                        PathNode.QuadTo(733.4f, 689.9f, 741.9f, 683.4f),
                        PathNode.QuadTo(750.4f, 676.9f, 753.4f, 669.9f),
                        PathNode.QuadTo(761.4f, 653.9f, 753.4f, 637.9f),
                        PathNode.QuadTo(750.4f, 630.9f, 741.9f, 624.4f),
                        PathNode.QuadTo(733.4f, 617.9f, 714.4f, 606.9f),
                        PathNode.LineTo(701.4f, 598.9f),
                        PathNode.LineTo(536.4f, 503.9f),
                        PathNode.QuadTo(513.4f, 489.9f, 499.4f, 483.4f),
                        PathNode.QuadTo(485.4f, 476.9f, 476.4f, 477.9f),
                        PathNode.QuadTo(458.4f, 478.9f, 447.4f, 493.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _movieRegular!!
    }

private var _movieRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Movie: ImageVector
    get() {
        if (_movieMedium != null) return _movieMedium!!
        _movieMedium = ImageVector.Builder(
            name = "Movie.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(994.1f, 286.4f),
                        PathNode.QuadTo(1037.0f, 309.2f, 1059.8f, 352.1f),
                        PathNode.QuadTo(1070.4f, 373.9f, 1072.9f, 404.2f),
                        PathNode.QuadTo(1075.4f, 434.6f, 1075.4f, 504.8f),
                        PathNode.LineTo(1075.4f, 806.7f),
                        PathNode.QuadTo(1075.4f, 876.3f, 1072.9f, 906.8f),
                        PathNode.QuadTo(1070.4f, 937.4f, 1059.8f, 959.4f),
                        PathNode.QuadTo(1037.5f, 1001.6f, 994.1f, 1024.1f),
                        PathNode.QuadTo(972.3f, 1034.7f, 942.0f, 1037.2f),
                        PathNode.QuadTo(911.7f, 1039.7f, 841.4f, 1039.7f),
                        PathNode.LineTo(301.4f, 1039.7f),
                        PathNode.QuadTo(231.2f, 1039.7f, 200.8f, 1037.2f),
                        PathNode.QuadTo(170.5f, 1034.7f, 148.7f, 1024.1f),
                        PathNode.QuadTo(105.4f, 1001.6f, 83.0f, 959.4f),
                        PathNode.QuadTo(72.5f, 937.4f, 70.0f, 906.8f),
                        PathNode.QuadTo(67.5f, 876.3f, 67.5f, 806.7f),
                        PathNode.LineTo(67.5f, 504.8f),
                        PathNode.QuadTo(67.5f, 434.6f, 70.0f, 404.2f),
                        PathNode.QuadTo(72.5f, 373.9f, 83.0f, 352.1f),
                        PathNode.QuadTo(105.8f, 309.2f, 148.7f, 286.4f),
                        PathNode.QuadTo(170.5f, 275.9f, 200.8f, 273.4f),
                        PathNode.QuadTo(231.2f, 270.9f, 301.4f, 270.9f),
                        PathNode.LineTo(841.4f, 270.9f),
                        PathNode.QuadTo(911.7f, 270.9f, 942.0f, 273.4f),
                        PathNode.QuadTo(972.3f, 275.9f, 994.1f, 286.4f),
                        PathNode.Close,
                        PathNode.MoveTo(833.7f, 180.4f),
                        PathNode.QuadTo(833.7f, 191.8f, 825.7f, 199.7f),
                        PathNode.QuadTo(817.8f, 207.7f, 806.4f, 207.7f),
                        PathNode.LineTo(336.4f, 207.7f),
                        PathNode.QuadTo(325.1f, 207.7f, 317.1f, 199.7f),
                        PathNode.QuadTo(309.2f, 191.8f, 309.2f, 180.4f),
                        PathNode.QuadTo(309.2f, 148.1f, 331.6f, 125.6f),
                        PathNode.QuadTo(354.1f, 103.2f, 385.4f, 103.2f),
                        PathNode.LineTo(757.4f, 103.2f),
                        PathNode.QuadTo(788.8f, 103.2f, 811.2f, 125.6f),
                        PathNode.QuadTo(833.7f, 148.1f, 833.7f, 180.4f),
                        PathNode.Close,
                        PathNode.MoveTo(444.7f, 493.7f),
                        PathNode.QuadTo(439.5f, 501.2f, 438.3f, 514.7f),
                        PathNode.QuadTo(437.2f, 528.3f, 437.2f, 560.6f),
                        PathNode.LineTo(437.2f, 750.6f),
                        PathNode.QuadTo(437.2f, 782.8f, 438.3f, 796.4f),
                        PathNode.QuadTo(439.5f, 809.9f, 444.7f, 817.4f),
                        PathNode.QuadTo(456.8f, 833.5f, 476.2f, 834.8f),
                        PathNode.QuadTo(484.7f, 836.1f, 497.4f, 830.7f),
                        PathNode.QuadTo(510.1f, 825.3f, 538.1f, 809.3f),
                        PathNode.LineTo(703.1f, 713.3f),
                        PathNode.LineTo(716.3f, 705.3f),
                        PathNode.QuadTo(735.6f, 694.3f, 744.4f, 687.5f),
                        PathNode.QuadTo(753.1f, 680.7f, 756.4f, 672.9f),
                        PathNode.QuadTo(764.9f, 655.6f, 756.4f, 638.2f),
                        PathNode.QuadTo(753.1f, 630.4f, 744.4f, 623.6f),
                        PathNode.QuadTo(735.6f, 616.8f, 716.3f, 605.8f),
                        PathNode.LineTo(703.1f, 597.8f),
                        PathNode.LineTo(538.1f, 502.8f),
                        PathNode.QuadTo(515.3f, 488.8f, 500.5f, 481.9f),
                        PathNode.QuadTo(485.7f, 475.0f, 476.2f, 476.3f),
                        PathNode.QuadTo(456.8f, 477.6f, 444.7f, 493.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _movieMedium!!
    }

private var _movieMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Movie: ImageVector
    get() {
        if (_movieDemibold != null) return _movieDemibold!!
        _movieDemibold = ImageVector.Builder(
            name = "Movie.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(996.0f, 284.0f),
                        PathNode.QuadTo(1040.2f, 307.3f, 1063.6f, 351.6f),
                        PathNode.QuadTo(1074.5f, 373.9f, 1077.0f, 404.8f),
                        PathNode.QuadTo(1079.5f, 435.7f, 1079.5f, 506.1f),
                        PathNode.LineTo(1079.5f, 807.3f),
                        PathNode.QuadTo(1079.5f, 877.2f, 1077.0f, 908.1f),
                        PathNode.QuadTo(1074.5f, 939.1f, 1063.6f, 961.9f),
                        PathNode.QuadTo(1040.3f, 1005.6f, 996.0f, 1028.5f),
                        PathNode.QuadTo(973.6f, 1039.4f, 942.8f, 1041.9f),
                        PathNode.QuadTo(911.9f, 1044.4f, 841.4f, 1044.4f),
                        PathNode.LineTo(301.4f, 1044.4f),
                        PathNode.QuadTo(231.0f, 1044.4f, 200.1f, 1041.9f),
                        PathNode.QuadTo(169.2f, 1039.4f, 146.8f, 1028.5f),
                        PathNode.QuadTo(102.5f, 1005.6f, 79.2f, 961.9f),
                        PathNode.QuadTo(68.3f, 939.1f, 65.8f, 908.1f),
                        PathNode.QuadTo(63.3f, 877.2f, 63.3f, 807.3f),
                        PathNode.LineTo(63.3f, 506.1f),
                        PathNode.QuadTo(63.3f, 435.7f, 65.8f, 404.8f),
                        PathNode.QuadTo(68.3f, 373.9f, 79.2f, 351.6f),
                        PathNode.QuadTo(102.6f, 307.3f, 146.8f, 284.0f),
                        PathNode.QuadTo(169.2f, 273.0f, 200.1f, 270.5f),
                        PathNode.QuadTo(231.0f, 268.0f, 301.4f, 268.0f),
                        PathNode.LineTo(841.4f, 268.0f),
                        PathNode.QuadTo(911.9f, 268.0f, 942.8f, 270.5f),
                        PathNode.QuadTo(973.6f, 273.0f, 996.0f, 284.0f),
                        PathNode.Close,
                        PathNode.MoveTo(835.9f, 178.0f),
                        PathNode.QuadTo(835.9f, 190.3f, 827.3f, 198.9f),
                        PathNode.QuadTo(818.7f, 207.5f, 806.4f, 207.5f),
                        PathNode.LineTo(336.4f, 207.5f),
                        PathNode.QuadTo(324.1f, 207.5f, 315.5f, 198.9f),
                        PathNode.QuadTo(306.9f, 190.3f, 306.9f, 178.0f),
                        PathNode.QuadTo(306.9f, 144.7f, 330.0f, 121.5f),
                        PathNode.QuadTo(353.1f, 98.4f, 385.4f, 98.4f),
                        PathNode.LineTo(757.4f, 98.4f),
                        PathNode.QuadTo(789.7f, 98.4f, 812.8f, 121.5f),
                        PathNode.QuadTo(835.9f, 144.7f, 835.9f, 178.0f),
                        PathNode.Close,
                        PathNode.MoveTo(442.8f, 493.5f),
                        PathNode.QuadTo(437.4f, 501.4f, 436.1f, 515.3f),
                        PathNode.QuadTo(434.9f, 529.2f, 434.9f, 561.7f),
                        PathNode.LineTo(434.9f, 751.7f),
                        PathNode.QuadTo(434.9f, 784.1f, 436.1f, 798.1f),
                        PathNode.QuadTo(437.4f, 812.0f, 442.8f, 819.9f),
                        PathNode.QuadTo(455.7f, 836.7f, 476.0f, 838.2f),
                        PathNode.QuadTo(484.9f, 839.7f, 498.0f, 834.0f),
                        PathNode.QuadTo(511.2f, 828.3f, 539.2f, 812.3f),
                        PathNode.LineTo(704.2f, 716.3f),
                        PathNode.LineTo(717.6f, 708.3f),
                        PathNode.QuadTo(737.1f, 697.3f, 746.1f, 690.3f),
                        PathNode.QuadTo(755.0f, 683.4f, 758.5f, 675.0f),
                        PathNode.QuadTo(767.4f, 656.7f, 758.5f, 638.4f),
                        PathNode.QuadTo(755.0f, 630.0f, 746.1f, 623.1f),
                        PathNode.QuadTo(737.1f, 616.1f, 717.6f, 605.1f),
                        PathNode.LineTo(704.2f, 597.1f),
                        PathNode.LineTo(539.2f, 502.1f),
                        PathNode.QuadTo(516.6f, 488.1f, 501.3f, 480.9f),
                        PathNode.QuadTo(485.9f, 473.7f, 476.0f, 475.2f),
                        PathNode.QuadTo(455.7f, 476.6f, 442.8f, 493.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _movieDemibold!!
    }

private var _movieDemibold: ImageVector? = null
