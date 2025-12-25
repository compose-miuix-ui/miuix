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

val MiuixIcons.Pin: ImageVector
    get() = MiuixIcons.Regular.Pin

val MiuixIcons.Light.Pin: ImageVector
    get() {
        if (_pinLight != null) return _pinLight!!
        _pinLight = ImageVector.Builder(
            name = "Pin.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1260.5181818181816f,
            viewportHeight = 1260.5181818181816f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -39.60202020202033f, translationY = 1018.2249999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(519.0f, 188.0f),
                PathNode.LineTo(734.0f, -27.0f),
                PathNode.QuadTo(745.0f, -38.0f, 753.0f, -44.5f),
                PathNode.QuadTo(761.0f, -51.0f, 768.0f, -54.0f),
                PathNode.QuadTo(779.0f, -58.0f, 792.5f, -56.0f),
                PathNode.QuadTo(806.0f, -54.0f, 815.0f, -47.0f),
                PathNode.QuadTo(819.0f, -44.0f, 823.0f, -38.5f),
                PathNode.QuadTo(827.0f, -33.0f, 832.0f, -24.0f),
                PathNode.LineTo(835.0f, -19.0f),
                PathNode.QuadTo(873.0f, 49.0f, 870.0f, 120.0f),
                PathNode.QuadTo(867.0f, 191.0f, 835.0f, 281.0f),
                PathNode.QuadTo(830.0f, 297.0f, 832.0f, 312.0f),
                PathNode.QuadTo(834.0f, 327.0f, 844.0f, 338.0f),
                PathNode.LineTo(945.0f, 452.0f),
                PathNode.QuadTo(951.0f, 459.0f, 958.0f, 461.0f),
                PathNode.QuadTo(965.0f, 463.0f, 973.0f, 461.0f),
                PathNode.LineTo(1098.0f, 427.0f),
                PathNode.QuadTo(1114.0f, 422.0f, 1130.5f, 425.0f),
                PathNode.QuadTo(1147.0f, 428.0f, 1159.0f, 440.0f),
                PathNode.LineTo(1175.0f, 456.0f),
                PathNode.QuadTo(1193.0f, 474.0f, 1194.0f, 498.5f),
                PathNode.QuadTo(1195.0f, 523.0f, 1178.0f, 541.0f),
                PathNode.LineTo(824.0f, 895.0f),
                PathNode.QuadTo(803.0f, 915.0f, 780.5f, 913.0f),
                PathNode.QuadTo(758.0f, 911.0f, 739.0f, 893.0f),
                PathNode.LineTo(722.0f, 876.0f),
                PathNode.QuadTo(710.0f, 863.0f, 706.5f, 847.0f),
                PathNode.QuadTo(703.0f, 831.0f, 709.0f, 815.0f),
                PathNode.LineTo(743.0f, 692.0f),
                PathNode.QuadTo(746.0f, 682.0f, 744.5f, 675.0f),
                PathNode.QuadTo(743.0f, 668.0f, 734.0f, 662.0f),
                PathNode.LineTo(614.0f, 558.0f),
                PathNode.QuadTo(594.0f, 541.0f, 570.0f, 551.0f),
                PathNode.QuadTo(475.0f, 587.0f, 402.0f, 590.5f),
                PathNode.QuadTo(329.0f, 594.0f, 262.0f, 552.0f),
                PathNode.QuadTo(240.0f, 538.0f, 234.0f, 530.0f),
                PathNode.QuadTo(227.0f, 521.0f, 226.0f, 507.0f),
                PathNode.QuadTo(225.0f, 493.0f, 230.0f, 483.0f),
                PathNode.QuadTo(234.0f, 473.0f, 254.0f, 453.0f),
                PathNode.LineTo(471.0f, 237.0f),
                PathNode.LineTo(194.0f, -40.0f),
                PathNode.QuadTo(180.0f, -54.0f, 169.0f, -76.0f),
                PathNode.LineTo(147.0f, -122.0f),
                PathNode.QuadTo(145.0f, -127.0f, 146.0f, -131.5f),
                PathNode.QuadTo(147.0f, -136.0f, 151.5f, -137.0f),
                PathNode.QuadTo(156.0f, -138.0f, 162.0f, -135.0f),
                PathNode.LineTo(206.0f, -113.0f),
                PathNode.QuadTo(227.0f, -104.0f, 243.0f, -88.0f),
                PathNode.Close,
                PathNode.MoveTo(337.0f, 521.0f),
                PathNode.QuadTo(406.0f, 544.0f, 495.0f, 513.0f),
                PathNode.LineTo(518.0f, 505.0f),
                PathNode.QuadTo(543.0f, 496.0f, 561.0f, 491.0f),
                PathNode.QuadTo(579.0f, 486.0f, 588.0f, 486.0f),
                PathNode.QuadTo(601.0f, 485.0f, 609.5f, 487.0f),
                PathNode.QuadTo(618.0f, 489.0f, 630.0f, 495.0f),
                PathNode.QuadTo(643.0f, 501.0f, 677.0f, 532.0f),
                PathNode.LineTo(688.0f, 541.0f),
                PathNode.LineTo(797.0f, 634.0f),
                PathNode.QuadTo(807.0f, 643.0f, 810.0f, 655.5f),
                PathNode.QuadTo(813.0f, 668.0f, 809.0f, 684.0f),
                PathNode.LineTo(774.0f, 812.0f),
                PathNode.QuadTo(772.0f, 818.0f, 776.0f, 826.0f),
                PathNode.QuadTo(780.0f, 834.0f, 787.0f, 837.0f),
                PathNode.QuadTo(794.0f, 840.0f, 800.0f, 833.0f),
                PathNode.LineTo(1117.0f, 517.0f),
                PathNode.QuadTo(1123.0f, 512.0f, 1114.0f, 500.5f),
                PathNode.QuadTo(1105.0f, 489.0f, 1097.0f, 491.0f),
                PathNode.LineTo(966.0f, 527.0f),
                PathNode.QuadTo(952.0f, 531.0f, 938.5f, 528.0f),
                PathNode.QuadTo(925.0f, 525.0f, 916.0f, 515.0f),
                PathNode.LineTo(824.0f, 410.0f),
                PathNode.LineTo(814.0f, 399.0f),
                PathNode.QuadTo(800.0f, 384.0f, 790.5f, 373.0f),
                PathNode.QuadTo(781.0f, 362.0f, 777.0f, 355.0f),
                PathNode.QuadTo(770.0f, 340.0f, 767.5f, 331.0f),
                PathNode.QuadTo(765.0f, 322.0f, 766.0f, 308.0f),
                PathNode.QuadTo(767.0f, 293.0f, 786.0f, 239.0f),
                PathNode.QuadTo(790.0f, 228.0f, 794.0f, 216.0f),
                PathNode.QuadTo(809.0f, 173.0f, 811.5f, 132.0f),
                PathNode.QuadTo(814.0f, 91.0f, 801.0f, 53.0f),
                PathNode.QuadTo(794.0f, 32.0f, 785.5f, 26.5f),
                PathNode.QuadTo(777.0f, 21.0f, 767.0f, 31.0f),
                PathNode.LineTo(312.0f, 487.0f),
                PathNode.QuadTo(302.0f, 496.0f, 307.5f, 504.0f),
                PathNode.QuadTo(313.0f, 512.0f, 337.0f, 521.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _pinLight!!
    }

private var _pinLight: ImageVector? = null

val MiuixIcons.Regular.Pin: ImageVector
    get() {
        if (_pinRegular != null) return _pinRegular!!
        _pinRegular = ImageVector.Builder(
            name = "Pin.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1305.3482517482516f,
            viewportHeight = 1305.3482517482516f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -12.349683649683698f, translationY = 1035.9328671328672f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(520.0f, 170.0f),
                PathNode.LineTo(726.0f, -36.0f),
                PathNode.QuadTo(737.0f, -47.0f, 746.0f, -55.0f),
                PathNode.QuadTo(755.0f, -63.0f, 763.0f, -66.0f),
                PathNode.QuadTo(777.0f, -71.0f, 795.0f, -68.5f),
                PathNode.QuadTo(813.0f, -66.0f, 824.0f, -57.0f),
                PathNode.QuadTo(830.0f, -53.0f, 834.5f, -46.5f),
                PathNode.QuadTo(839.0f, -40.0f, 844.0f, -31.0f),
                PathNode.LineTo(847.0f, -26.0f),
                PathNode.QuadTo(887.0f, 45.0f, 883.5f, 118.5f),
                PathNode.QuadTo(880.0f, 192.0f, 847.0f, 285.0f),
                PathNode.QuadTo(843.0f, 297.0f, 845.0f, 309.0f),
                PathNode.QuadTo(847.0f, 321.0f, 855.0f, 330.0f),
                PathNode.LineTo(952.0f, 439.0f),
                PathNode.QuadTo(956.0f, 445.0f, 963.5f, 446.5f),
                PathNode.QuadTo(971.0f, 448.0f, 978.0f, 446.0f),
                PathNode.LineTo(1094.0f, 415.0f),
                PathNode.QuadTo(1113.0f, 408.0f, 1133.5f, 412.5f),
                PathNode.QuadTo(1154.0f, 417.0f, 1169.0f, 431.0f),
                PathNode.LineTo(1185.0f, 448.0f),
                PathNode.QuadTo(1206.0f, 468.0f, 1207.0f, 499.0f),
                PathNode.QuadTo(1208.0f, 530.0f, 1188.0f, 551.0f),
                PathNode.LineTo(834.0f, 905.0f),
                PathNode.QuadTo(809.0f, 929.0f, 781.0f, 927.0f),
                PathNode.QuadTo(753.0f, 925.0f, 731.0f, 902.0f),
                PathNode.LineTo(714.0f, 885.0f),
                PathNode.QuadTo(699.0f, 871.0f, 694.5f, 851.0f),
                PathNode.QuadTo(690.0f, 831.0f, 697.0f, 811.0f),
                PathNode.LineTo(729.0f, 695.0f),
                PathNode.QuadTo(731.0f, 688.0f, 729.5f, 680.5f),
                PathNode.QuadTo(728.0f, 673.0f, 722.0f, 669.0f),
                PathNode.LineTo(606.0f, 569.0f),
                PathNode.QuadTo(593.0f, 557.0f, 576.0f, 564.0f),
                PathNode.QuadTo(478.0f, 601.0f, 402.0f, 604.5f),
                PathNode.QuadTo(326.0f, 608.0f, 256.0f, 564.0f),
                PathNode.QuadTo(233.0f, 550.0f, 226.0f, 542.0f),
                PathNode.QuadTo(216.0f, 529.0f, 214.0f, 510.0f),
                PathNode.QuadTo(212.0f, 491.0f, 219.0f, 476.0f),
                PathNode.QuadTo(223.0f, 467.0f, 246.0f, 444.0f),
                PathNode.LineTo(453.0f, 237.0f),
                PathNode.LineTo(186.0f, -30.0f),
                PathNode.QuadTo(169.0f, -48.0f, 158.0f, -70.0f),
                PathNode.LineTo(124.0f, -140.0f),
                PathNode.QuadTo(122.0f, -146.0f, 124.0f, -151.5f),
                PathNode.QuadTo(126.0f, -157.0f, 131.5f, -159.5f),
                PathNode.QuadTo(137.0f, -162.0f, 143.0f, -159.0f),
                PathNode.LineTo(213.0f, -125.0f),
                PathNode.QuadTo(236.0f, -115.0f, 253.0f, -97.0f),
                PathNode.Close,
                PathNode.MoveTo(342.0f, 509.0f),
                PathNode.QuadTo(406.0f, 531.0f, 491.0f, 501.0f),
                PathNode.LineTo(514.0f, 493.0f),
                PathNode.QuadTo(540.0f, 484.0f, 559.5f, 479.0f),
                PathNode.QuadTo(579.0f, 474.0f, 589.0f, 474.0f),
                PathNode.QuadTo(604.0f, 473.0f, 614.0f, 475.0f),
                PathNode.QuadTo(624.0f, 477.0f, 637.0f, 484.0f),
                PathNode.QuadTo(652.0f, 491.0f, 687.0f, 523.0f),
                PathNode.LineTo(698.0f, 533.0f),
                PathNode.LineTo(807.0f, 624.0f),
                PathNode.QuadTo(820.0f, 636.0f, 824.0f, 652.5f),
                PathNode.QuadTo(828.0f, 669.0f, 823.0f, 688.0f),
                PathNode.LineTo(793.0f, 799.0f),
                PathNode.QuadTo(791.0f, 804.0f, 792.5f, 809.0f),
                PathNode.QuadTo(794.0f, 814.0f, 798.0f, 814.5f),
                PathNode.QuadTo(802.0f, 815.0f, 808.0f, 809.0f),
                PathNode.LineTo(1092.0f, 525.0f),
                PathNode.QuadTo(1098.0f, 520.0f, 1094.0f, 514.0f),
                PathNode.QuadTo(1090.0f, 508.0f, 1082.0f, 510.0f),
                PathNode.LineTo(970.0f, 541.0f),
                PathNode.QuadTo(952.0f, 546.0f, 935.5f, 542.0f),
                PathNode.QuadTo(919.0f, 538.0f, 907.0f, 525.0f),
                PathNode.LineTo(815.0f, 420.0f),
                PathNode.LineTo(805.0f, 409.0f),
                PathNode.QuadTo(790.0f, 394.0f, 780.0f, 382.5f),
                PathNode.QuadTo(770.0f, 371.0f, 766.0f, 362.0f),
                PathNode.QuadTo(758.0f, 347.0f, 755.5f, 335.5f),
                PathNode.QuadTo(753.0f, 324.0f, 754.0f, 308.0f),
                PathNode.QuadTo(755.0f, 289.0f, 774.0f, 235.0f),
                PathNode.QuadTo(778.0f, 224.0f, 782.0f, 212.0f),
                PathNode.QuadTo(797.0f, 170.0f, 800.0f, 133.5f),
                PathNode.QuadTo(803.0f, 97.0f, 791.0f, 60.0f),
                PathNode.QuadTo(788.0f, 52.0f, 781.0f, 49.5f),
                PathNode.QuadTo(774.0f, 47.0f, 767.0f, 54.0f),
                PathNode.LineTo(338.0f, 484.0f),
                PathNode.QuadTo(330.0f, 492.0f, 332.0f, 499.0f),
                PathNode.QuadTo(334.0f, 506.0f, 342.0f, 509.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _pinRegular!!
    }

private var _pinRegular: ImageVector? = null

val MiuixIcons.Heavy.Pin: ImageVector
    get() {
        if (_pinHeavy != null) return _pinHeavy!!
        _pinHeavy = ImageVector.Builder(
            name = "Pin.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1338.16018735363f,
            viewportHeight = 1338.16018735363f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 3.8758831504992344f, translationY = 1052.2182669789227f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(520.0f, 151.0f),
                PathNode.LineTo(716.0f, -46.0f),
                PathNode.QuadTo(728.0f, -58.0f, 738.0f, -66.5f),
                PathNode.QuadTo(748.0f, -75.0f, 758.0f, -79.0f),
                PathNode.QuadTo(776.0f, -86.0f, 797.5f, -82.5f),
                PathNode.QuadTo(819.0f, -79.0f, 832.0f, -68.0f),
                PathNode.QuadTo(839.0f, -63.0f, 845.0f, -55.5f),
                PathNode.QuadTo(851.0f, -48.0f, 856.0f, -38.0f),
                PathNode.LineTo(859.0f, -33.0f),
                PathNode.QuadTo(901.0f, 41.0f, 897.5f, 118.0f),
                PathNode.QuadTo(894.0f, 195.0f, 860.0f, 290.0f),
                PathNode.QuadTo(857.0f, 298.0f, 858.5f, 306.5f),
                PathNode.QuadTo(860.0f, 315.0f, 865.0f, 321.0f),
                PathNode.LineTo(959.0f, 427.0f),
                PathNode.QuadTo(963.0f, 431.0f, 968.5f, 432.5f),
                PathNode.QuadTo(974.0f, 434.0f, 980.0f, 432.0f),
                PathNode.LineTo(1090.0f, 402.0f),
                PathNode.QuadTo(1113.0f, 393.0f, 1137.0f, 398.5f),
                PathNode.QuadTo(1161.0f, 404.0f, 1179.0f, 421.0f),
                PathNode.LineTo(1195.0f, 438.0f),
                PathNode.QuadTo(1220.0f, 462.0f, 1221.0f, 499.0f),
                PathNode.QuadTo(1222.0f, 536.0f, 1198.0f, 561.0f),
                PathNode.LineTo(844.0f, 915.0f),
                PathNode.QuadTo(814.0f, 943.0f, 780.0f, 940.5f),
                PathNode.QuadTo(746.0f, 938.0f, 721.0f, 912.0f),
                PathNode.LineTo(704.0f, 895.0f),
                PathNode.QuadTo(687.0f, 878.0f, 681.5f, 853.5f),
                PathNode.QuadTo(676.0f, 829.0f, 684.0f, 807.0f),
                PathNode.LineTo(715.0f, 695.0f),
                PathNode.QuadTo(717.0f, 690.0f, 716.0f, 685.0f),
                PathNode.QuadTo(715.0f, 680.0f, 711.0f, 677.0f),
                PathNode.LineTo(599.0f, 581.0f),
                PathNode.QuadTo(589.0f, 574.0f, 577.0f, 578.0f),
                PathNode.QuadTo(479.0f, 615.0f, 400.5f, 618.5f),
                PathNode.QuadTo(322.0f, 622.0f, 249.0f, 576.0f),
                PathNode.QuadTo(224.0f, 561.0f, 215.0f, 551.0f),
                PathNode.QuadTo(202.0f, 535.0f, 200.0f, 511.5f),
                PathNode.QuadTo(198.0f, 488.0f, 207.0f, 470.0f),
                PathNode.QuadTo(213.0f, 457.0f, 236.0f, 434.0f),
                PathNode.LineTo(434.0f, 237.0f),
                PathNode.LineTo(176.0f, -20.0f),
                PathNode.QuadTo(157.0f, -39.0f, 146.0f, -64.0f),
                PathNode.LineTo(112.0f, -134.0f),
                PathNode.QuadTo(107.0f, -146.0f, 111.5f, -157.0f),
                PathNode.QuadTo(116.0f, -168.0f, 126.5f, -172.5f),
                PathNode.QuadTo(137.0f, -177.0f, 149.0f, -171.0f),
                PathNode.LineTo(219.0f, -137.0f),
                PathNode.QuadTo(244.0f, -126.0f, 263.0f, -107.0f),
                PathNode.Close,
                PathNode.MoveTo(362.0f, 498.0f),
                PathNode.QuadTo(413.0f, 513.0f, 486.0f, 488.0f),
                PathNode.LineTo(509.0f, 480.0f),
                PathNode.QuadTo(536.0f, 471.0f, 556.5f, 465.5f),
                PathNode.QuadTo(577.0f, 460.0f, 589.0f, 460.0f),
                PathNode.QuadTo(605.0f, 459.0f, 616.5f, 461.5f),
                PathNode.QuadTo(628.0f, 464.0f, 643.0f, 472.0f),
                PathNode.QuadTo(662.0f, 481.0f, 697.0f, 513.0f),
                PathNode.LineTo(708.0f, 523.0f),
                PathNode.LineTo(816.0f, 614.0f),
                PathNode.QuadTo(832.0f, 628.0f, 837.5f, 648.5f),
                PathNode.QuadTo(843.0f, 669.0f, 836.0f, 692.0f),
                PathNode.LineTo(816.0f, 764.0f),
                PathNode.QuadTo(815.0f, 768.0f, 816.0f, 771.5f),
                PathNode.QuadTo(817.0f, 775.0f, 819.5f, 775.0f),
                PathNode.QuadTo(822.0f, 775.0f, 827.0f, 770.0f),
                PathNode.LineTo(1054.0f, 544.0f),
                PathNode.QuadTo(1059.0f, 540.0f, 1057.0f, 535.5f),
                PathNode.QuadTo(1055.0f, 531.0f, 1048.0f, 533.0f),
                PathNode.LineTo(974.0f, 554.0f),
                PathNode.QuadTo(952.0f, 561.0f, 931.5f, 555.5f),
                PathNode.QuadTo(911.0f, 550.0f, 897.0f, 535.0f),
                PathNode.LineTo(805.0f, 429.0f),
                PathNode.LineTo(795.0f, 419.0f),
                PathNode.QuadTo(781.0f, 403.0f, 770.0f, 390.0f),
                PathNode.QuadTo(759.0f, 377.0f, 754.0f, 368.0f),
                PathNode.QuadTo(746.0f, 352.0f, 742.5f, 338.5f),
                PathNode.QuadTo(739.0f, 325.0f, 740.0f, 307.0f),
                PathNode.QuadTo(741.0f, 289.0f, 761.0f, 231.0f),
                PathNode.QuadTo(763.0f, 223.0f, 769.0f, 207.0f),
                PathNode.QuadTo(782.0f, 169.0f, 785.0f, 137.5f),
                PathNode.QuadTo(788.0f, 106.0f, 781.0f, 78.0f),
                PathNode.QuadTo(779.0f, 72.0f, 774.5f, 71.0f),
                PathNode.QuadTo(770.0f, 70.0f, 764.0f, 76.0f),
                PathNode.LineTo(360.0f, 481.0f),
                PathNode.QuadTo(353.0f, 487.0f, 354.5f, 491.5f),
                PathNode.QuadTo(356.0f, 496.0f, 362.0f, 498.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _pinHeavy!!
    }

private var _pinHeavy: ImageVector? = null
