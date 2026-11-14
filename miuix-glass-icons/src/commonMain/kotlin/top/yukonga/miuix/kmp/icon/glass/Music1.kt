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

val MiuixIcons.Glass.Music1: ImageVector
    get() = MiuixIcons.Glass.Regular.Music1

val MiuixIcons.Glass.Light.Music1: ImageVector
    get() {
        if (_musicSurfaceLight != null) return _musicSurfaceLight!!
        _musicSurfaceLight = buildHyperOSIcon(
            name = "Music1.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(484.8f, 840.7f),
                PathNode.QuadTo(470f, 841.2f, 463.2f, 829.7f),
                PathNode.QuadTo(456.4f, 818.2f, 463.1f, 805f),
                PathNode.LineTo(721.7f, 326.8f),
                PathNode.QuadTo(725.6f, 320.4f, 721.8f, 315.9f),
                PathNode.QuadTo(718.1f, 311.4f, 711.3f, 312.9f),
                PathNode.QuadTo(689.4f, 318.6f, 673.6f, 320.9f),
                PathNode.QuadTo(657.7f, 323.3f, 641f, 323.3f),
                PathNode.QuadTo(574.9f, 323.3f, 518.5f, 293.2f),
                PathNode.QuadTo(462.1f, 263.1f, 429.3f, 211.7f),
                PathNode.QuadTo(396.4f, 160.3f, 396.4f, 99.7f),
                PathNode.QuadTo(396.4f, 39f, 429.3f, -12.4f),
                PathNode.QuadTo(462.1f, -63.8f, 518.5f, -93.9f),
                PathNode.QuadTo(574.9f, -124f, 641f, -124f),
                PathNode.QuadTo(717.2f, -124f, 779.1f, -84.9f),
                PathNode.QuadTo(841f, -45.8f, 868.6f, 16.9f),
                PathNode.QuadTo(887.1f, 56.6f, 887.1f, 99.7f),
                PathNode.QuadTo(887.1f, 130.8f, 878.3f, 156.2f),
                PathNode.QuadTo(869.6f, 181.7f, 851.6f, 213f),
                PathNode.LineTo(627.1f, 630f),
                PathNode.QuadTo(661.9f, 646.4f, 699.3f, 670.6f),
                PathNode.QuadTo(736.8f, 694.8f, 775.4f, 728.4f),
                PathNode.QuadTo(815.1f, 764f, 850.6f, 819.4f),
                PathNode.QuadTo(886.1f, 874.8f, 912.1f, 929.9f),
                PathNode.QuadTo(915.4f, 937.1f, 910.9f, 941.3f),
                PathNode.QuadTo(906.4f, 945.6f, 899.2f, 941.7f),
                PathNode.QuadTo(869.4f, 924.3f, 830.4f, 905.1f),
                PathNode.QuadTo(791.3f, 885.9f, 752.9f, 871.6f),
                PathNode.QuadTo(706f, 854.1f, 630.1f, 846.4f),
                PathNode.QuadTo(554.2f, 838.7f, 484.8f, 840.7f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 11.6798f,
            translationY = 1076.4321f,
            autoMirror = false,
        )
        return _musicSurfaceLight!!
    }

private var _musicSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Music1: ImageVector
    get() {
        if (_musicSurfaceNormal != null) return _musicSurfaceNormal!!
        _musicSurfaceNormal = buildHyperOSIcon(
            name = "Music1.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(483.6f, 841.6f),
                PathNode.QuadTo(467.3f, 842.4f, 458.9f, 828.2f),
                PathNode.QuadTo(450.6f, 813.9f, 458.2f, 799.5f),
                PathNode.LineTo(708.8f, 336.6f),
                PathNode.QuadTo(712.1f, 331.1f, 708.5f, 326.6f),
                PathNode.QuadTo(705f, 322.1f, 699.4f, 323.3f),
                PathNode.QuadTo(683.6f, 327.1f, 669.7f, 328.9f),
                PathNode.QuadTo(655.8f, 330.7f, 641f, 330.7f),
                PathNode.QuadTo(574.3f, 330.7f, 517.1f, 299.9f),
                PathNode.QuadTo(460f, 269.2f, 426.7f, 217.1f),
                PathNode.QuadTo(393.4f, 164.9f, 393.4f, 103.3f),
                PathNode.QuadTo(393.4f, 41.7f, 426.7f, -10.4f),
                PathNode.QuadTo(460f, -62.6f, 517.1f, -93.3f),
                PathNode.QuadTo(574.3f, -124f, 641f, -124f),
                PathNode.QuadTo(718.4f, -124f, 781.1f, -84.3f),
                PathNode.QuadTo(843.7f, -44.6f, 871.6f, 19f),
                PathNode.QuadTo(890.5f, 59.6f, 890.5f, 103.3f),
                PathNode.QuadTo(890.5f, 135.1f, 881.5f, 161.6f),
                PathNode.QuadTo(872.6f, 188.1f, 854.6f, 218.5f),
                PathNode.LineTo(636f, 624.5f),
                PathNode.QuadTo(669.5f, 640.6f, 705.3f, 664.3f),
                PathNode.QuadTo(741.1f, 688.1f, 777.9f, 719.9f),
                PathNode.QuadTo(821.2f, 758.5f, 859.5f, 820.2f),
                PathNode.QuadTo(897.7f, 881.8f, 923.7f, 940.3f),
                PathNode.QuadTo(926.1f, 946f, 921.6f, 950f),
                PathNode.QuadTo(917.1f, 954.1f, 911.4f, 950.8f),
                PathNode.QuadTo(880.1f, 931.7f, 836.7f, 909.8f),
                PathNode.QuadTo(793.2f, 888f, 749.5f, 871.9f),
                PathNode.QuadTo(703.3f, 854.7f, 628f, 847.2f),
                PathNode.QuadTo(552.7f, 839.6f, 483.6f, 841.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 7.6893f,
            translationY = 1080.8141f,
            autoMirror = false,
        )
        return _musicSurfaceNormal!!
    }

private var _musicSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Music1: ImageVector
    get() {
        if (_musicSurfaceRegular != null) return _musicSurfaceRegular!!
        _musicSurfaceRegular = buildHyperOSIcon(
            name = "Music1.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(483f, 842f),
                PathNode.QuadTo(466f, 843f, 457f, 827.5f),
                PathNode.QuadTo(448f, 812f, 456f, 797f),
                PathNode.LineTo(703f, 341f),
                PathNode.QuadTo(706f, 336f, 702.5f, 331.5f),
                PathNode.QuadTo(699f, 327f, 694f, 328f),
                PathNode.QuadTo(681f, 331f, 668f, 332.5f),
                PathNode.QuadTo(655f, 334f, 641f, 334f),
                PathNode.QuadTo(574f, 334f, 516.5f, 303f),
                PathNode.QuadTo(459f, 272f, 425.5f, 219.5f),
                PathNode.QuadTo(392f, 167f, 392f, 105f),
                PathNode.QuadTo(392f, 43f, 425.5f, -9.5f),
                PathNode.QuadTo(459f, -62f, 516.5f, -93f),
                PathNode.QuadTo(574f, -124f, 641f, -124f),
                PathNode.QuadTo(719f, -124f, 782f, -84f),
                PathNode.QuadTo(845f, -44f, 873f, 20f),
                PathNode.QuadTo(892f, 61f, 892f, 105f),
                PathNode.QuadTo(892f, 137f, 883f, 164f),
                PathNode.QuadTo(874f, 191f, 856f, 221f),
                PathNode.LineTo(640f, 622f),
                PathNode.QuadTo(673f, 638f, 708f, 661.5f),
                PathNode.QuadTo(743f, 685f, 779f, 716f),
                PathNode.QuadTo(824f, 756f, 863.5f, 820.5f),
                PathNode.QuadTo(903f, 885f, 929f, 945f),
                PathNode.QuadTo(931f, 950f, 926.5f, 954f),
                PathNode.QuadTo(922f, 958f, 917f, 955f),
                PathNode.QuadTo(885f, 935f, 839.5f, 912f),
                PathNode.QuadTo(794f, 889f, 748f, 872f),
                PathNode.QuadTo(702f, 855f, 627f, 847.5f),
                PathNode.QuadTo(552f, 840f, 483f, 842f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 5.859f,
            translationY = 1082.8095f,
            autoMirror = false,
        )
        return _musicSurfaceRegular!!
    }

private var _musicSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Music1: ImageVector
    get() {
        if (_musicSurfaceMedium != null) return _musicSurfaceMedium!!
        _musicSurfaceMedium = buildHyperOSIcon(
            name = "Music1.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(487.6f, 846.1f),
                PathNode.QuadTo(462.5f, 846.5f, 451.5f, 828.4f),
                PathNode.QuadTo(440.4f, 810.4f, 451.9f, 788.6f),
                PathNode.LineTo(688.1f, 352.6f),
                PathNode.QuadTo(691.4f, 347.1f, 688f, 342.4f),
                PathNode.QuadTo(684.7f, 337.8f, 678.9f, 338.5f),
                PathNode.QuadTo(667.5f, 340.7f, 657.1f, 341.7f),
                PathNode.QuadTo(646.6f, 342.6f, 634.5f, 342.6f),
                PathNode.QuadTo(567.5f, 341f, 510.6f, 309.1f),
                PathNode.QuadTo(453.6f, 277.1f, 420.4f, 224.1f),
                PathNode.QuadTo(387.1f, 171.1f, 387.1f, 108.5f),
                PathNode.QuadTo(387.1f, 44.9f, 421.3f, -8.8f),
                PathNode.QuadTo(455.5f, -62.5f, 514.2f, -94.2f),
                PathNode.QuadTo(572.9f, -125.9f, 641.3f, -125.9f),
                PathNode.QuadTo(720.6f, -125.9f, 785f, -85.1f),
                PathNode.QuadTo(849.3f, -44.3f, 878.1f, 21.1f),
                PathNode.QuadTo(897.7f, 63.7f, 897.7f, 108.5f),
                PathNode.QuadTo(897.7f, 140.8f, 888.5f, 168.1f),
                PathNode.QuadTo(879.4f, 195.3f, 860.9f, 227.2f),
                PathNode.LineTo(651.1f, 616.9f),
                PathNode.QuadTo(684.4f, 633.9f, 717.1f, 656.1f),
                PathNode.QuadTo(749.8f, 678.2f, 783.1f, 707.4f),
                PathNode.QuadTo(833.2f, 752.8f, 873.9f, 821.6f),
                PathNode.QuadTo(914.6f, 890.4f, 940.4f, 955f),
                PathNode.QuadTo(943.2f, 961.9f, 938.1f, 966f),
                PathNode.QuadTo(933.1f, 970.2f, 926.5f, 966.1f),
                PathNode.QuadTo(888.2f, 942f, 840.3f, 917.3f),
                PathNode.QuadTo(792.4f, 892.5f, 744.8f, 874.7f),
                PathNode.QuadTo(701.7f, 858.8f, 631.3f, 851.7f),
                PathNode.QuadTo(560.9f, 844.6f, 487.6f, 846.1f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 2.4204f,
            translationY = 1087.7747f,
            autoMirror = false,
        )
        return _musicSurfaceMedium!!
    }

private var _musicSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Music1: ImageVector
    get() {
        if (_musicSurfaceDemibold != null) return _musicSurfaceDemibold!!
        _musicSurfaceDemibold = buildHyperOSIcon(
            name = "Music1.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(490.8f, 848.9f),
                PathNode.QuadTo(460f, 849f, 447.6f, 829.1f),
                PathNode.QuadTo(435.1f, 809.2f, 449.1f, 782.8f),
                PathNode.LineTo(677.7f, 360.8f),
                PathNode.QuadTo(681.2f, 354.8f, 677.9f, 350.1f),
                PathNode.QuadTo(674.6f, 345.4f, 668.3f, 345.9f),
                PathNode.QuadTo(658f, 347.5f, 649.4f, 348.1f),
                PathNode.QuadTo(640.8f, 348.7f, 630f, 348.7f),
                PathNode.QuadTo(563f, 345.9f, 506.4f, 313.3f),
                PathNode.QuadTo(449.8f, 280.7f, 416.8f, 227.3f),
                PathNode.QuadTo(383.7f, 173.9f, 383.7f, 111f),
                PathNode.QuadTo(383.7f, 46.2f, 418.4f, -8.4f),
                PathNode.QuadTo(453f, -62.9f, 512.6f, -95.1f),
                PathNode.QuadTo(572.2f, -127.2f, 641.5f, -127.2f),
                PathNode.QuadTo(721.8f, -127.2f, 787.1f, -85.8f),
                PathNode.QuadTo(852.4f, -44.5f, 881.7f, 21.8f),
                PathNode.QuadTo(901.6f, 65.6f, 901.6f, 111f),
                PathNode.QuadTo(901.6f, 143.4f, 892.4f, 170.9f),
                PathNode.QuadTo(883.2f, 198.4f, 864.3f, 231.6f),
                PathNode.LineTo(658.8f, 613.3f),
                PathNode.QuadTo(692.3f, 631.1f, 723.4f, 652.3f),
                PathNode.QuadTo(754.5f, 673.5f, 785.9f, 701.3f),
                PathNode.QuadTo(839.6f, 750.5f, 881.2f, 822.3f),
                PathNode.QuadTo(922.8f, 894.2f, 948.3f, 962f),
                PathNode.QuadTo(951.7f, 970.2f, 946.3f, 974.4f),
                PathNode.QuadTo(940.8f, 978.7f, 933.1f, 973.8f),
                PathNode.QuadTo(890.5f, 946.9f, 840.9f, 921f),
                PathNode.QuadTo(791.2f, 895f, 742.5f, 876.6f),
                PathNode.QuadTo(701.5f, 861.4f, 634.4f, 854.6f),
                PathNode.QuadTo(567.2f, 847.8f, 490.8f, 848.9f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 0.0043f,
            translationY = 1091.2683f,
            autoMirror = false,
        )
        return _musicSurfaceDemibold!!
    }

private var _musicSurfaceDemibold: ImageVector? = null

