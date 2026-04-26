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

val MiuixIcons.ExpandMore: ImageVector
    get() = MiuixIcons.Regular.ExpandMore

val MiuixIcons.Light.ExpandMore: ImageVector
    get() {
        if (_expandmoreLight != null) return _expandmoreLight!!
        _expandmoreLight = ImageVector.Builder(
            name = "ExpandMore.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1088.4f,
            viewportHeight = 1088.4f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1088.4f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(152.7f, 650.7f),
                        PathNode.VerticalTo(930.3f),
                        PathNode.QuadTo(152.7f, 932.4f, 154.3f, 934.1f),
                        PathNode.QuadTo(156.0f, 935.7f, 158.1f, 935.7f),
                        PathNode.HorizontalTo(437.7f),
                        PathNode.QuadTo(445.7f, 935.7f, 450.7f, 941.0f),
                        PathNode.QuadTo(455.7f, 946.2f, 455.7f, 954.0f),
                        PathNode.VerticalTo(980.0f),
                        PathNode.QuadTo(455.7f, 987.7f, 450.7f, 992.7f),
                        PathNode.QuadTo(445.7f, 997.7f, 437.7f, 997.7f),
                        PathNode.HorizontalTo(130.7f),
                        PathNode.QuadTo(111.4f, 997.7f, 101.0f, 987.4f),
                        PathNode.QuadTo(90.7f, 977.0f, 90.7f, 957.7f),
                        PathNode.VerticalTo(650.7f),
                        PathNode.QuadTo(90.7f, 643.4f, 96.0f, 638.0f),
                        PathNode.QuadTo(101.4f, 632.7f, 108.7f, 632.7f),
                        PathNode.HorizontalTo(134.5f),
                        PathNode.QuadTo(142.2f, 632.7f, 147.5f, 638.0f),
                        PathNode.QuadTo(152.7f, 643.4f, 152.7f, 650.7f),
                        PathNode.Close,
                        PathNode.MoveTo(624.7f, 543.6f),
                        PathNode.QuadTo(624.7f, 577.7f, 600.8f, 601.2f),
                        PathNode.QuadTo(577.0f, 624.7f, 543.8f, 624.7f),
                        PathNode.QuadTo(509.7f, 624.7f, 486.2f, 601.3f),
                        PathNode.QuadTo(462.7f, 577.9f, 462.7f, 543.9f),
                        PathNode.QuadTo(462.7f, 510.7f, 486.1f, 486.7f),
                        PathNode.QuadTo(509.5f, 462.7f, 543.5f, 462.7f),
                        PathNode.QuadTo(576.7f, 462.7f, 600.7f, 486.6f),
                        PathNode.QuadTo(624.7f, 510.4f, 624.7f, 543.6f),
                        PathNode.Close,
                        PathNode.MoveTo(997.7f, 130.7f),
                        PathNode.VerticalTo(424.7f),
                        PathNode.QuadTo(997.7f, 432.7f, 992.7f, 437.7f),
                        PathNode.QuadTo(987.7f, 442.7f, 979.9f, 442.7f),
                        PathNode.HorizontalTo(953.7f),
                        PathNode.QuadTo(946.4f, 442.7f, 941.0f, 437.7f),
                        PathNode.QuadTo(935.7f, 432.7f, 935.7f, 424.7f),
                        PathNode.VerticalTo(158.7f),
                        PathNode.QuadTo(935.7f, 152.7f, 930.7f, 152.7f),
                        PathNode.HorizontalTo(649.7f),
                        PathNode.QuadTo(643.2f, 152.7f, 637.9f, 147.3f),
                        PathNode.QuadTo(632.7f, 141.8f, 632.7f, 135.0f),
                        PathNode.VerticalTo(109.2f),
                        PathNode.QuadTo(632.7f, 101.7f, 637.9f, 96.2f),
                        PathNode.QuadTo(643.2f, 90.7f, 649.7f, 90.7f),
                        PathNode.HorizontalTo(957.7f),
                        PathNode.QuadTo(977.0f, 90.7f, 987.4f, 101.0f),
                        PathNode.QuadTo(997.7f, 111.4f, 997.7f, 130.7f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandmoreLight!!
    }

private var _expandmoreLight: ImageVector? = null

val MiuixIcons.Normal.ExpandMore: ImageVector
    get() {
        if (_expandmoreNormal != null) return _expandmoreNormal!!
        _expandmoreNormal = ImageVector.Builder(
            name = "ExpandMore.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1111.5f,
            viewportHeight = 1111.5f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1111.5f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(173.9f, 667.8f),
                        PathNode.VerticalTo(932.5f),
                        PathNode.QuadTo(173.9f, 934.5f, 175.4f, 936.1f),
                        PathNode.QuadTo(177.0f, 937.6f, 179.0f, 937.6f),
                        PathNode.HorizontalTo(443.8f),
                        PathNode.QuadTo(453.8f, 937.6f, 460.9f, 944.4f),
                        PathNode.QuadTo(467.9f, 951.2f, 467.9f, 961.2f),
                        PathNode.VerticalTo(994.8f),
                        PathNode.QuadTo(467.9f, 1004.8f, 460.9f, 1011.8f),
                        PathNode.QuadTo(453.8f, 1018.9f, 443.8f, 1018.9f),
                        PathNode.HorizontalTo(146.4f),
                        PathNode.QuadTo(120.4f, 1018.9f, 106.5f, 1005.0f),
                        PathNode.QuadTo(92.6f, 991.1f, 92.6f, 965.1f),
                        PathNode.VerticalTo(667.8f),
                        PathNode.QuadTo(92.6f, 657.9f, 99.8f, 650.7f),
                        PathNode.QuadTo(107.0f, 643.6f, 116.8f, 643.6f),
                        PathNode.HorizontalTo(150.3f),
                        PathNode.QuadTo(160.3f, 643.6f, 167.1f, 650.7f),
                        PathNode.QuadTo(173.9f, 657.9f, 173.9f, 667.8f),
                        PathNode.Close,
                        PathNode.MoveTo(645.9f, 555.2f),
                        PathNode.QuadTo(645.9f, 593.4f, 619.2f, 619.6f),
                        PathNode.QuadTo(592.5f, 645.9f, 555.3f, 645.9f),
                        PathNode.QuadTo(517.1f, 645.9f, 490.9f, 619.7f),
                        PathNode.QuadTo(464.6f, 593.4f, 464.6f, 555.3f),
                        PathNode.QuadTo(464.6f, 518.1f, 490.8f, 491.4f),
                        PathNode.QuadTo(517.1f, 464.6f, 555.2f, 464.6f),
                        PathNode.QuadTo(592.4f, 464.6f, 619.1f, 491.3f),
                        PathNode.QuadTo(645.9f, 518.0f, 645.9f, 555.2f),
                        PathNode.Close,
                        PathNode.MoveTo(1018.9f, 146.4f),
                        PathNode.VerticalTo(439.7f),
                        PathNode.QuadTo(1018.9f, 449.8f, 1011.8f, 456.8f),
                        PathNode.QuadTo(1004.8f, 463.9f, 994.8f, 463.9f),
                        PathNode.HorizontalTo(961.1f),
                        PathNode.QuadTo(951.3f, 463.9f, 944.1f, 456.8f),
                        PathNode.QuadTo(936.9f, 449.8f, 936.9f, 439.7f),
                        PathNode.VerticalTo(179.2f),
                        PathNode.QuadTo(936.9f, 173.9f, 932.6f, 173.9f),
                        PathNode.HorizontalTo(666.8f),
                        PathNode.QuadTo(657.8f, 173.9f, 650.7f, 166.7f),
                        PathNode.QuadTo(643.6f, 159.5f, 643.6f, 150.5f),
                        PathNode.VerticalTo(116.3f),
                        PathNode.QuadTo(643.6f, 106.4f, 650.7f, 99.5f),
                        PathNode.QuadTo(657.8f, 92.6f, 666.8f, 92.6f),
                        PathNode.HorizontalTo(965.1f),
                        PathNode.QuadTo(991.1f, 92.6f, 1005.0f, 106.5f),
                        PathNode.QuadTo(1018.9f, 120.4f, 1018.9f, 146.4f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandmoreNormal!!
    }

private var _expandmoreNormal: ImageVector? = null

val MiuixIcons.Regular.ExpandMore: ImageVector
    get() {
        if (_expandmoreRegular != null) return _expandmoreRegular!!
        _expandmoreRegular = ImageVector.Builder(
            name = "ExpandMore.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1122.0f,
            viewportHeight = 1122.0f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1122.0f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(183.5f, 675.5f),
                        PathNode.VerticalTo(933.5f),
                        PathNode.QuadTo(183.5f, 935.5f, 185.0f, 937.0f),
                        PathNode.QuadTo(186.5f, 938.5f, 188.5f, 938.5f),
                        PathNode.HorizontalTo(446.5f),
                        PathNode.QuadTo(457.5f, 938.5f, 465.5f, 946.0f),
                        PathNode.QuadTo(473.5f, 953.5f, 473.5f, 964.5f),
                        PathNode.VerticalTo(1001.5f),
                        PathNode.QuadTo(473.5f, 1012.5f, 465.5f, 1020.5f),
                        PathNode.QuadTo(457.5f, 1028.5f, 446.5f, 1028.5f),
                        PathNode.HorizontalTo(153.5f),
                        PathNode.QuadTo(124.5f, 1028.5f, 109.0f, 1013.0f),
                        PathNode.QuadTo(93.5f, 997.5f, 93.5f, 968.5f),
                        PathNode.VerticalTo(675.5f),
                        PathNode.QuadTo(93.5f, 664.5f, 101.5f, 656.5f),
                        PathNode.QuadTo(109.5f, 648.5f, 120.5f, 648.5f),
                        PathNode.HorizontalTo(157.5f),
                        PathNode.QuadTo(168.5f, 648.5f, 176.0f, 656.5f),
                        PathNode.QuadTo(183.5f, 664.5f, 183.5f, 675.5f),
                        PathNode.Close,
                        PathNode.MoveTo(655.5f, 560.5f),
                        PathNode.QuadTo(655.5f, 600.5f, 627.5f, 628.0f),
                        PathNode.QuadTo(599.5f, 655.5f, 560.5f, 655.5f),
                        PathNode.QuadTo(520.5f, 655.5f, 493.0f, 628.0f),
                        PathNode.QuadTo(465.5f, 600.5f, 465.5f, 560.5f),
                        PathNode.QuadTo(465.5f, 521.5f, 493.0f, 493.5f),
                        PathNode.QuadTo(520.5f, 465.5f, 560.5f, 465.5f),
                        PathNode.QuadTo(599.5f, 465.5f, 627.5f, 493.5f),
                        PathNode.QuadTo(655.5f, 521.5f, 655.5f, 560.5f),
                        PathNode.Close,
                        PathNode.MoveTo(1028.5f, 153.5f),
                        PathNode.VerticalTo(446.5f),
                        PathNode.QuadTo(1028.5f, 457.5f, 1020.5f, 465.5f),
                        PathNode.QuadTo(1012.5f, 473.5f, 1001.5f, 473.5f),
                        PathNode.HorizontalTo(964.5f),
                        PathNode.QuadTo(953.5f, 473.5f, 945.5f, 465.5f),
                        PathNode.QuadTo(937.5f, 457.5f, 937.5f, 446.5f),
                        PathNode.VerticalTo(188.5f),
                        PathNode.QuadTo(937.5f, 183.5f, 933.5f, 183.5f),
                        PathNode.HorizontalTo(674.5f),
                        PathNode.QuadTo(664.5f, 183.5f, 656.5f, 175.5f),
                        PathNode.QuadTo(648.5f, 167.5f, 648.5f, 157.5f),
                        PathNode.VerticalTo(119.5f),
                        PathNode.QuadTo(648.5f, 108.5f, 656.5f, 101.0f),
                        PathNode.QuadTo(664.5f, 93.5f, 674.5f, 93.5f),
                        PathNode.HorizontalTo(968.5f),
                        PathNode.QuadTo(997.5f, 93.5f, 1013.0f, 109.0f),
                        PathNode.QuadTo(1028.5f, 124.5f, 1028.5f, 153.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandmoreRegular!!
    }

private var _expandmoreRegular: ImageVector? = null

val MiuixIcons.Medium.ExpandMore: ImageVector
    get() {
        if (_expandmoreMedium != null) return _expandmoreMedium!!
        _expandmoreMedium = ImageVector.Builder(
            name = "ExpandMore.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1141.8f,
            viewportHeight = 1141.8f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1141.8f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(201.6f, 685.4f),
                        PathNode.VerticalTo(935.1f),
                        PathNode.QuadTo(201.6f, 937.1f, 203.1f, 938.6f),
                        PathNode.QuadTo(204.6f, 940.1f, 206.6f, 940.1f),
                        PathNode.HorizontalTo(456.4f),
                        PathNode.QuadTo(470.3f, 940.1f, 481.0f, 950.3f),
                        PathNode.QuadTo(491.6f, 960.4f, 491.6f, 974.4f),
                        PathNode.VerticalTo(1011.4f),
                        PathNode.QuadTo(491.6f, 1025.9f, 481.3f, 1036.3f),
                        PathNode.QuadTo(470.9f, 1046.6f, 456.4f, 1046.6f),
                        PathNode.HorizontalTo(154.6f),
                        PathNode.QuadTo(127.3f, 1046.6f, 111.2f, 1030.8f),
                        PathNode.QuadTo(95.1f, 1015.0f, 95.1f, 987.8f),
                        PathNode.VerticalTo(685.4f),
                        PathNode.QuadTo(95.1f, 671.4f, 105.8f, 660.8f),
                        PathNode.QuadTo(116.4f, 650.1f, 130.4f, 650.1f),
                        PathNode.HorizontalTo(167.4f),
                        PathNode.QuadTo(181.3f, 650.1f, 191.5f, 660.5f),
                        PathNode.QuadTo(201.6f, 670.9f, 201.6f, 685.4f),
                        PathNode.Close,
                        PathNode.MoveTo(673.6f, 570.4f),
                        PathNode.QuadTo(673.6f, 613.3f, 643.3f, 643.5f),
                        PathNode.QuadTo(612.9f, 673.6f, 570.4f, 673.6f),
                        PathNode.QuadTo(527.4f, 673.6f, 497.3f, 643.5f),
                        PathNode.QuadTo(467.1f, 613.3f, 467.1f, 570.4f),
                        PathNode.QuadTo(467.1f, 527.9f, 497.3f, 497.5f),
                        PathNode.QuadTo(527.4f, 467.1f, 570.4f, 467.1f),
                        PathNode.QuadTo(612.3f, 467.1f, 643.0f, 497.8f),
                        PathNode.QuadTo(673.6f, 528.4f, 673.6f, 570.4f),
                        PathNode.Close,
                        PathNode.MoveTo(1046.6f, 154.0f),
                        PathNode.VerticalTo(456.4f),
                        PathNode.QuadTo(1046.6f, 470.9f, 1036.3f, 481.3f),
                        PathNode.QuadTo(1025.9f, 491.6f, 1011.4f, 491.6f),
                        PathNode.HorizontalTo(974.4f),
                        PathNode.QuadTo(960.4f, 491.6f, 949.8f, 481.3f),
                        PathNode.QuadTo(939.1f, 470.9f, 939.1f, 456.4f),
                        PathNode.VerticalTo(206.6f),
                        PathNode.QuadTo(939.1f, 201.6f, 935.1f, 201.6f),
                        PathNode.HorizontalTo(684.4f),
                        PathNode.QuadTo(670.9f, 201.6f, 660.5f, 191.3f),
                        PathNode.QuadTo(650.1f, 180.9f, 650.1f, 167.4f),
                        PathNode.VerticalTo(129.4f),
                        PathNode.QuadTo(650.1f, 115.4f, 660.5f, 105.3f),
                        PathNode.QuadTo(670.9f, 95.1f, 684.4f, 95.1f),
                        PathNode.HorizontalTo(987.8f),
                        PathNode.QuadTo(1015.0f, 95.7f, 1030.8f, 111.2f),
                        PathNode.QuadTo(1046.6f, 126.7f, 1046.6f, 154.0f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandmoreMedium!!
    }

private var _expandmoreMedium: ImageVector? = null

val MiuixIcons.Demibold.ExpandMore: ImageVector
    get() {
        if (_expandmoreDemibold != null) return _expandmoreDemibold!!
        _expandmoreDemibold = ImageVector.Builder(
            name = "ExpandMore.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1155.6f,
            viewportHeight = 1155.6f,
        ).apply {
            group(scaleY = -1.0f, translationY = 1155.6f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(214.3f, 692.3f),
                        PathNode.VerticalTo(936.3f),
                        PathNode.QuadTo(214.3f, 938.3f, 215.8f, 939.8f),
                        PathNode.QuadTo(217.3f, 941.3f, 219.3f, 941.3f),
                        PathNode.HorizontalTo(463.3f),
                        PathNode.QuadTo(479.3f, 941.3f, 491.8f, 953.3f),
                        PathNode.QuadTo(504.3f, 965.3f, 504.3f, 981.3f),
                        PathNode.VerticalTo(1018.3f),
                        PathNode.QuadTo(504.3f, 1035.3f, 492.3f, 1047.3f),
                        PathNode.QuadTo(480.3f, 1059.3f, 463.3f, 1059.3f),
                        PathNode.HorizontalTo(155.3f),
                        PathNode.QuadTo(129.3f, 1059.3f, 112.8f, 1043.3f),
                        PathNode.QuadTo(96.3f, 1027.3f, 96.3f, 1001.3f),
                        PathNode.VerticalTo(692.3f),
                        PathNode.QuadTo(96.3f, 676.3f, 108.8f, 663.8f),
                        PathNode.QuadTo(121.3f, 651.3f, 137.3f, 651.3f),
                        PathNode.HorizontalTo(174.3f),
                        PathNode.QuadTo(190.3f, 651.3f, 202.3f, 663.3f),
                        PathNode.QuadTo(214.3f, 675.3f, 214.3f, 692.3f),
                        PathNode.Close,
                        PathNode.MoveTo(686.3f, 577.3f),
                        PathNode.QuadTo(686.3f, 622.3f, 654.3f, 654.3f),
                        PathNode.QuadTo(622.3f, 686.3f, 577.3f, 686.3f),
                        PathNode.QuadTo(532.3f, 686.3f, 500.3f, 654.3f),
                        PathNode.QuadTo(468.3f, 622.3f, 468.3f, 577.3f),
                        PathNode.QuadTo(468.3f, 532.3f, 500.3f, 500.3f),
                        PathNode.QuadTo(532.3f, 468.3f, 577.3f, 468.3f),
                        PathNode.QuadTo(621.3f, 468.3f, 653.8f, 500.8f),
                        PathNode.QuadTo(686.3f, 533.3f, 686.3f, 577.3f),
                        PathNode.Close,
                        PathNode.MoveTo(1059.3f, 154.3f),
                        PathNode.VerticalTo(463.3f),
                        PathNode.QuadTo(1059.3f, 480.3f, 1047.3f, 492.3f),
                        PathNode.QuadTo(1035.3f, 504.3f, 1018.3f, 504.3f),
                        PathNode.HorizontalTo(981.3f),
                        PathNode.QuadTo(965.3f, 504.3f, 952.8f, 492.3f),
                        PathNode.QuadTo(940.3f, 480.3f, 940.3f, 463.3f),
                        PathNode.VerticalTo(219.3f),
                        PathNode.QuadTo(940.3f, 214.3f, 936.3f, 214.3f),
                        PathNode.HorizontalTo(691.3f),
                        PathNode.QuadTo(675.3f, 214.3f, 663.3f, 202.3f),
                        PathNode.QuadTo(651.3f, 190.3f, 651.3f, 174.3f),
                        PathNode.VerticalTo(136.3f),
                        PathNode.QuadTo(651.3f, 120.3f, 663.3f, 108.3f),
                        PathNode.QuadTo(675.3f, 96.3f, 691.3f, 96.3f),
                        PathNode.HorizontalTo(1001.3f),
                        PathNode.QuadTo(1027.3f, 97.3f, 1043.3f, 112.8f),
                        PathNode.QuadTo(1059.3f, 128.3f, 1059.3f, 154.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _expandmoreDemibold!!
    }

private var _expandmoreDemibold: ImageVector? = null
