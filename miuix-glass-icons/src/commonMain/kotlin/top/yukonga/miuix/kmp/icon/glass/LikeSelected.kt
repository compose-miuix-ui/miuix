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

val MiuixIcons.Glass.LikeSelected: ImageVector
    get() = MiuixIcons.Glass.Regular.LikeSelected

val MiuixIcons.Glass.Light.LikeSelected: ImageVector
    get() {
        if (_likeSelectedSurfaceLight != null) return _likeSelectedSurfaceLight!!
        _likeSelectedSurfaceLight = buildHyperOSIcon(
            name = "LikeSelected.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(520f, 777.7f),
                PathNode.LineTo(422.2f, 477.8f),
                PathNode.QuadTo(420.4f, 470.8f, 419.9f, 466.4f),
                PathNode.VerticalTo(-52.4f),
                PathNode.QuadTo(419.9f, -57.8f, 423.3f, -60.9f),
                PathNode.QuadTo(426.7f, -64.1f, 432f, -64.1f),
                PathNode.HorizontalTo(843.4f),
                PathNode.QuadTo(920.4f, -64.1f, 974.8f, -23.1f),
                PathNode.QuadTo(1029.2f, 18f, 1053.8f, 92.6f),
                PathNode.LineTo(1158.6f, 418.1f),
                PathNode.QuadTo(1173.8f, 465.6f, 1161.1f, 506.1f),
                PathNode.QuadTo(1148.3f, 546.7f, 1112.6f, 570.7f),
                PathNode.QuadTo(1076.9f, 594.7f, 1027f, 594.7f),
                PathNode.HorizontalTo(699.8f),
                PathNode.QuadTo(696.4f, 594.7f, 694f, 596.8f),
                PathNode.QuadTo(691.6f, 599f, 692.6f, 601.9f),
                PathNode.LineTo(712.1f, 729.6f),
                PathNode.QuadTo(718.2f, 765.9f, 710.1f, 795.5f),
                PathNode.QuadTo(701.9f, 825.1f, 680.5f, 842.9f),
                PathNode.QuadTo(659.1f, 860.7f, 626.1f, 860.7f),
                PathNode.QuadTo(588.1f, 860.7f, 560.7f, 838.4f),
                PathNode.QuadTo(533.2f, 816.1f, 520f, 777.7f),
                PathNode.Close,
                PathNode.MoveTo(181.1f, 391.8f),
                PathNode.VerticalTo(15.2f),
                PathNode.QuadTo(181.1f, -19.6f, 203.4f, -41.8f),
                PathNode.QuadTo(225.7f, -64.1f, 260.4f, -64.1f),
                PathNode.HorizontalTo(342.2f),
                PathNode.QuadTo(348f, -64.1f, 351.2f, -60.9f),
                PathNode.QuadTo(354.3f, -57.8f, 354.3f, -52.4f),
                PathNode.VerticalTo(459.4f),
                PathNode.QuadTo(354.3f, 464.8f, 351.2f, 468.2f),
                PathNode.QuadTo(348f, 471.7f, 342.2f, 471.7f),
                PathNode.HorizontalTo(260.4f),
                PathNode.QuadTo(237.8f, 471.7f, 219.4f, 461.4f),
                PathNode.QuadTo(201.1f, 451.1f, 191.1f, 433.1f),
                PathNode.QuadTo(181.1f, 415f, 181.1f, 391.8f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -7.3129f,
            translationY = 1064.9444f,
            autoMirror = false,
        )
        return _likeSelectedSurfaceLight!!
    }

private var _likeSelectedSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.LikeSelected: ImageVector
    get() {
        if (_likeSelectedSurfaceNormal != null) return _likeSelectedSurfaceNormal!!
        _likeSelectedSurfaceNormal = buildHyperOSIcon(
            name = "LikeSelected.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(522.7f, 781.3f),
                PathNode.LineTo(423.4f, 487.6f),
                PathNode.QuadTo(422.9f, 483.3f, 422f, 479.9f),
                PathNode.VerticalTo(-63.1f),
                PathNode.QuadTo(422f, -67.6f, 424.8f, -70.3f),
                PathNode.QuadTo(427.6f, -73f, 432f, -73f),
                PathNode.HorizontalTo(854.1f),
                PathNode.QuadTo(925.6f, -73f, 980.8f, -31.6f),
                PathNode.QuadTo(1035.9f, 9.8f, 1058.1f, 79.1f),
                PathNode.LineTo(1167.1f, 427f),
                PathNode.QuadTo(1180.8f, 471.4f, 1166.9f, 512.2f),
                PathNode.QuadTo(1152.9f, 553.1f, 1117.3f, 578.5f),
                PathNode.QuadTo(1081.8f, 603.8f, 1035.2f, 603.8f),
                PathNode.HorizontalTo(717.8f),
                PathNode.QuadTo(715.4f, 603.8f, 713.2f, 605.5f),
                PathNode.QuadTo(711.1f, 607.2f, 712.1f, 609.5f),
                PathNode.LineTo(732f, 729.9f),
                PathNode.QuadTo(738.7f, 768f, 729.6f, 799.6f),
                PathNode.QuadTo(720.5f, 831.2f, 697f, 850.5f),
                PathNode.QuadTo(673.5f, 869.8f, 637.7f, 869.8f),
                PathNode.QuadTo(597f, 869.8f, 567.1f, 846f),
                PathNode.QuadTo(537.2f, 822.2f, 522.7f, 781.3f),
                PathNode.Close,
                PathNode.MoveTo(165.2f, 396.1f),
                PathNode.VerticalTo(13.7f),
                PathNode.QuadTo(165.2f, -22.6f, 190.4f, -47.8f),
                PathNode.QuadTo(215.6f, -73f, 251.9f, -73f),
                PathNode.HorizontalTo(332.4f),
                PathNode.QuadTo(337f, -73f, 339.7f, -70.3f),
                PathNode.QuadTo(342.4f, -67.6f, 342.4f, -63.1f),
                PathNode.VerticalTo(472.9f),
                PathNode.QuadTo(342.4f, 477.3f, 339.7f, 480.4f),
                PathNode.QuadTo(337f, 483.6f, 332.4f, 483.6f),
                PathNode.HorizontalTo(251.9f),
                PathNode.QuadTo(228.3f, 483.6f, 208.1f, 471.8f),
                PathNode.QuadTo(188f, 460f, 176.6f, 440.2f),
                PathNode.QuadTo(165.2f, 420.5f, 165.2f, 396.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -2.893f,
            translationY = 1065.0972f,
            autoMirror = false,
        )
        return _likeSelectedSurfaceNormal!!
    }

private var _likeSelectedSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.LikeSelected: ImageVector
    get() {
        if (_likeSelectedSurfaceRegular != null) return _likeSelectedSurfaceRegular!!
        _likeSelectedSurfaceRegular = buildHyperOSIcon(
            name = "LikeSelected.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(524f, 783f),
                PathNode.LineTo(424f, 492f),
                PathNode.QuadTo(424f, 489f, 423f, 486f),
                PathNode.VerticalTo(-68f),
                PathNode.QuadTo(423f, -72f, 425.5f, -74.5f),
                PathNode.QuadTo(428f, -77f, 432f, -77f),
                PathNode.HorizontalTo(859f),
                PathNode.QuadTo(928f, -77f, 983.5f, -35.5f),
                PathNode.QuadTo(1039f, 6f, 1060f, 73f),
                PathNode.LineTo(1171f, 431f),
                PathNode.QuadTo(1184f, 474f, 1169.5f, 515f),
                PathNode.QuadTo(1155f, 556f, 1119.5f, 582f),
                PathNode.QuadTo(1084f, 608f, 1039f, 608f),
                PathNode.HorizontalTo(726f),
                PathNode.QuadTo(724f, 608f, 722f, 609.5f),
                PathNode.QuadTo(720f, 611f, 721f, 613f),
                PathNode.LineTo(741f, 730f),
                PathNode.QuadTo(748f, 769f, 738.5f, 801.5f),
                PathNode.QuadTo(729f, 834f, 704.5f, 854f),
                PathNode.QuadTo(680f, 874f, 643f, 874f),
                PathNode.QuadTo(601f, 874f, 570f, 849.5f),
                PathNode.QuadTo(539f, 825f, 524f, 783f),
                PathNode.Close,
                PathNode.MoveTo(158f, 398f),
                PathNode.VerticalTo(13f),
                PathNode.QuadTo(158f, -24f, 184.5f, -50.5f),
                PathNode.QuadTo(211f, -77f, 248f, -77f),
                PathNode.HorizontalTo(328f),
                PathNode.QuadTo(332f, -77f, 334.5f, -74.5f),
                PathNode.QuadTo(337f, -72f, 337f, -68f),
                PathNode.VerticalTo(479f),
                PathNode.QuadTo(337f, 483f, 334.5f, 486f),
                PathNode.QuadTo(332f, 489f, 328f, 489f),
                PathNode.HorizontalTo(248f),
                PathNode.QuadTo(224f, 489f, 203f, 476.5f),
                PathNode.QuadTo(182f, 464f, 170f, 443.5f),
                PathNode.QuadTo(158f, 423f, 158f, 398f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -0.9061f,
            translationY = 1065.1667f,
            autoMirror = false,
        )
        return _likeSelectedSurfaceRegular!!
    }

private var _likeSelectedSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.LikeSelected: ImageVector
    get() {
        if (_likeSelectedSurfaceMedium != null) return _likeSelectedSurfaceMedium!!
        _likeSelectedSurfaceMedium = buildHyperOSIcon(
            name = "LikeSelected.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(527.2f, 786f),
                PathNode.LineTo(427.2f, 495f),
                PathNode.QuadTo(426.4f, 490.4f, 425.7f, 486.3f),
                PathNode.VerticalTo(-72.9f),
                PathNode.QuadTo(425.7f, -78.5f, 428.9f, -81.7f),
                PathNode.QuadTo(432.1f, -84.8f, 437.7f, -84.8f),
                PathNode.HorizontalTo(869.8f),
                PathNode.QuadTo(941.5f, -84.8f, 999.2f, -41.7f),
                PathNode.QuadTo(1056.8f, 1.4f, 1078.6f, 70.8f),
                PathNode.LineTo(1189.6f, 428.8f),
                PathNode.QuadTo(1203.5f, 474.5f, 1188f, 518f),
                PathNode.QuadTo(1172.6f, 561.4f, 1135f, 588.9f),
                PathNode.QuadTo(1097.5f, 616.4f, 1049.8f, 616.4f),
                PathNode.HorizontalTo(747.9f),
                PathNode.QuadTo(745.1f, 616.4f, 742.9f, 618.3f),
                PathNode.QuadTo(740.8f, 620.2f, 741.8f, 623f),
                PathNode.LineTo(759.9f, 728.9f),
                PathNode.QuadTo(767.2f, 769.8f, 756.9f, 804.6f),
                PathNode.QuadTo(746.6f, 839.4f, 720.2f, 860.9f),
                PathNode.QuadTo(693.8f, 882.4f, 653.8f, 882.4f),
                PathNode.QuadTo(609.1f, 882.4f, 575.9f, 856.3f),
                PathNode.QuadTo(542.8f, 830.1f, 527.2f, 786f),
                PathNode.Close,
                PathNode.MoveTo(139.1f, 398.3f),
                PathNode.VerticalTo(13.3f),
                PathNode.QuadTo(139.1f, -27.2f, 168f, -56f),
                PathNode.QuadTo(196.9f, -84.8f, 237.2f, -84.8f),
                PathNode.HorizontalTo(322.3f),
                PathNode.QuadTo(327.9f, -84.8f, 331.1f, -81.7f),
                PathNode.QuadTo(334.3f, -78.5f, 334.3f, -72.9f),
                PathNode.VerticalTo(484.7f),
                PathNode.QuadTo(334.3f, 490.3f, 331.1f, 493.8f),
                PathNode.QuadTo(327.9f, 497.4f, 322.3f, 497.4f),
                PathNode.HorizontalTo(237.2f),
                PathNode.QuadTo(211f, 497.4f, 188.3f, 483.8f),
                PathNode.QuadTo(165.5f, 470.2f, 152.3f, 447.7f),
                PathNode.QuadTo(139.1f, 425.2f, 139.1f, 398.3f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -0.9579f,
            translationY = 1065.4369f,
            autoMirror = false,
        )
        return _likeSelectedSurfaceMedium!!
    }

private var _likeSelectedSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.LikeSelected: ImageVector
    get() {
        if (_likeSelectedSurfaceDemibold != null) return _likeSelectedSurfaceDemibold!!
        _likeSelectedSurfaceDemibold = buildHyperOSIcon(
            name = "LikeSelected.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(529.5f, 788.1f),
                PathNode.LineTo(429.5f, 497.1f),
                PathNode.QuadTo(428.1f, 491.3f, 427.6f, 486.5f),
                PathNode.VerticalTo(-76.3f),
                PathNode.QuadTo(427.6f, -83f, 431.2f, -86.7f),
                PathNode.QuadTo(434.9f, -90.3f, 441.6f, -90.3f),
                PathNode.HorizontalTo(877.4f),
                PathNode.QuadTo(951f, -90.3f, 1010.1f, -46.1f),
                PathNode.QuadTo(1069.3f, -1.8f, 1091.7f, 69.3f),
                PathNode.LineTo(1202.7f, 427.3f),
                PathNode.QuadTo(1217.1f, 474.9f, 1201f, 520.1f),
                PathNode.QuadTo(1184.9f, 565.2f, 1145.9f, 593.7f),
                PathNode.QuadTo(1107f, 622.2f, 1057.4f, 622.2f),
                PathNode.HorizontalTo(763.2f),
                PathNode.QuadTo(759.8f, 622.2f, 757.6f, 624.4f),
                PathNode.QuadTo(755.4f, 626.6f, 756.4f, 630f),
                PathNode.LineTo(773.2f, 728.2f),
                PathNode.QuadTo(780.6f, 770.4f, 769.7f, 806.8f),
                PathNode.QuadTo(758.9f, 843.2f, 731.1f, 865.7f),
                PathNode.QuadTo(703.4f, 888.2f, 661.4f, 888.2f),
                PathNode.QuadTo(614.8f, 888.2f, 580.1f, 861f),
                PathNode.QuadTo(545.4f, 833.7f, 529.5f, 788.1f),
                PathNode.Close,
                PathNode.MoveTo(125.8f, 398.5f),
                PathNode.VerticalTo(13.5f),
                PathNode.QuadTo(125.8f, -29.5f, 156.5f, -59.9f),
                PathNode.QuadTo(187.1f, -90.3f, 229.6f, -90.3f),
                PathNode.HorizontalTo(318.4f),
                PathNode.QuadTo(325.1f, -90.3f, 328.8f, -86.7f),
                PathNode.QuadTo(332.4f, -83f, 332.4f, -76.3f),
                PathNode.VerticalTo(488.6f),
                PathNode.QuadTo(332.4f, 495.4f, 328.8f, 499.3f),
                PathNode.QuadTo(325.1f, 503.2f, 318.4f, 503.2f),
                PathNode.HorizontalTo(229.6f),
                PathNode.QuadTo(201.9f, 503.2f, 178f, 488.9f),
                PathNode.QuadTo(154f, 474.6f, 139.9f, 450.6f),
                PathNode.QuadTo(125.8f, 426.7f, 125.8f, 398.5f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = -0.9943f,
            translationY = 1065.6261f,
            autoMirror = false,
        )
        return _likeSelectedSurfaceDemibold!!
    }

private var _likeSelectedSurfaceDemibold: ImageVector? = null

