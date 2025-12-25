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

val MiuixIcons.ContactsBook: ImageVector
    get() = MiuixIcons.Regular.ContactsBook

val MiuixIcons.Light.ContactsBook: ImageVector
    get() {
        if (_contactsbookLight != null) return _contactsbookLight!!
        _contactsbookLight = ImageVector.Builder(
            name = "ContactsBook.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1194.0f,
            viewportHeight = 1194.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -67.5f, translationY = 972.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1162.0f, 374.7592954990215f),
                PathNode.QuadTo(1162.0f, 510.49510763209395f, 1095.0f, 624.747553816047f),
                PathNode.QuadTo(1028.0f, 739.0f, 914.19921875f, 806.0f),
                PathNode.QuadTo(800.3984375f, 873.0f, 665.19921875f, 873.0f),
                PathNode.QuadTo(530.0f, 873.0f, 415.5f, 806.0f),
                PathNode.QuadTo(301.0f, 739.0f, 234.0f, 624.747553816047f),
                PathNode.QuadTo(167.0f, 510.49510763209395f, 167.0f, 374.7592954990215f),
                PathNode.QuadTo(167.0f, 240.0f, 233.35040431266847f, 126.14516129032258f),
                PathNode.QuadTo(299.70080862533695f, 12.29032258064516f, 414.3504043126685f, -54.85483870967742f),
                PathNode.QuadTo(529.0f, -122.0f, 665.1867704280156f, -122.0f),
                PathNode.QuadTo(801.3735408560311f, -122.0f, 915.1867704280155f, -55.0f),
                PathNode.QuadTo(1029.0f, 12.0f, 1095.5f, 126.0f),
                PathNode.QuadTo(1162.0f, 240.0f, 1162.0f, 374.7592954990215f),
                PathNode.Close,
                PathNode.MoveTo(369.0f, 51.0f),
                PathNode.QuadTo(429.0f, 139.0f, 506.23636363636365f, 186.0f),
                PathNode.QuadTo(583.4727272727273f, 233.0f, 665.0f, 233.0f),
                PathNode.QuadTo(747.0f, 233.0f, 823.5f, 186.0f),
                PathNode.QuadTo(900.0f, 139.0f, 959.0f, 51.0f),
                PathNode.QuadTo(901.0f, -3.0f, 824.7583892617449f, -33.0f),
                PathNode.QuadTo(748.51677852349f, -63.0f, 664.7583892617449f, -63.0f),
                PathNode.QuadTo(581.0f, -63.0f, 505.21000000000004f, -33.116504854368934f),
                PathNode.QuadTo(429.42f, -3.233009708737864f, 369.0f, 51.0f),
                PathNode.Close,
                PathNode.MoveTo(227.0f, 375.0f),
                PathNode.QuadTo(227.0f, 494.35691318327974f, 286.0f, 594.6784565916398f),
                PathNode.QuadTo(345.0f, 695.0f, 445.47535211267603f, 754.0f),
                PathNode.QuadTo(545.9507042253521f, 813.0f, 665.4906103286385f, 813.0f),
                PathNode.QuadTo(784.0f, 813.0f, 884.3306451612902f, 754.1553398058252f),
                PathNode.QuadTo(984.6612903225806f, 695.3106796116505f, 1043.8306451612902f, 594.6553398058252f),
                PathNode.QuadTo(1103.0f, 494.0f, 1103.0f, 375.0f),
                PathNode.QuadTo(1103.0f, 296.29411764705884f, 1076.5f, 224.14705882352942f),
                PathNode.QuadTo(1050.0f, 152.0f, 1001.0f, 95.0f),
                PathNode.QuadTo(935.0f, 189.0f, 848.0f, 240.5f),
                PathNode.QuadTo(761.0f, 292.0f, 665.0f, 292.0f),
                PathNode.QuadTo(568.0f, 292.0f, 481.5f, 240.5f),
                PathNode.QuadTo(395.0f, 189.0f, 329.0f, 95.0f),
                PathNode.QuadTo(280.0f, 152.0f, 253.5f, 224.14705882352942f),
                PathNode.QuadTo(227.0f, 296.29411764705884f, 227.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(836.0f, 527.8297872340426f),
                PathNode.QuadTo(836.0f, 574.0f, 812.9148936170213f, 613.3518518518518f),
                PathNode.QuadTo(789.8297872340426f, 652.7037037037037f, 750.5851063829787f, 675.8518518518518f),
                PathNode.QuadTo(711.3404255319149f, 699.0f, 665.1702127659574f, 699.0f),
                PathNode.QuadTo(619.0f, 699.0f, 579.6481481481482f, 675.9148936170213f),
                PathNode.QuadTo(540.2962962962963f, 652.8297872340426f, 517.1481481481482f, 613.5851063829787f),
                PathNode.QuadTo(494.0f, 574.3404255319149f, 494.0f, 528.1702127659574f),
                PathNode.QuadTo(494.0f, 482.0f, 517.0851063829787f, 442.64814814814815f),
                PathNode.QuadTo(540.1702127659574f, 403.2962962962963f, 579.4148936170213f, 380.14814814814815f),
                PathNode.QuadTo(618.6595744680851f, 357.0f, 664.8297872340426f, 357.0f),
                PathNode.QuadTo(711.0f, 357.0f, 750.3518518518518f, 380.0851063829787f),
                PathNode.QuadTo(789.7037037037037f, 403.17021276595744f, 812.8518518518518f, 442.4148936170213f),
                PathNode.QuadTo(836.0f, 481.6595744680851f, 836.0f, 527.8297872340426f),
                PathNode.Close,
                PathNode.MoveTo(556.0f, 528.179104477612f),
                PathNode.QuadTo(556.0f, 573.0f, 587.6044776119403f, 605.0f),
                PathNode.QuadTo(619.2089552238806f, 637.0f, 665.179104477612f, 637.0f),
                PathNode.QuadTo(710.0f, 637.0f, 742.0f, 604.8208955223881f),
                PathNode.QuadTo(774.0f, 572.6417910447761f, 774.0f, 527.820895522388f),
                PathNode.QuadTo(774.0f, 483.0f, 741.8518518518518f, 451.0f),
                PathNode.QuadTo(709.7037037037037f, 419.0f, 664.925925925926f, 419.0f),
                PathNode.QuadTo(619.0f, 419.0f, 587.5f, 451.17910447761193f),
                PathNode.QuadTo(556.0f, 483.35820895522386f, 556.0f, 528.179104477612f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactsbookLight!!
    }

private var _contactsbookLight: ImageVector? = null

val MiuixIcons.Regular.ContactsBook: ImageVector
    get() {
        if (_contactsbookRegular != null) return _contactsbookRegular!!
        _contactsbookRegular = ImageVector.Builder(
            name = "ContactsBook.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1225.2f,
            viewportHeight = 1225.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -51.89999999999998f, translationY = 988.1f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1175.0f, 375.0f),
                PathNode.QuadTo(1175.0f, 514.0f, 1106.5f, 631.0f),
                PathNode.QuadTo(1038.0f, 748.0f, 921.0f, 817.0f),
                PathNode.QuadTo(804.0f, 886.0f, 665.0f, 886.0f),
                PathNode.QuadTo(526.0f, 886.0f, 409.0f, 817.0f),
                PathNode.QuadTo(292.0f, 748.0f, 223.0f, 631.0f),
                PathNode.QuadTo(154.0f, 514.0f, 154.0f, 375.0f),
                PathNode.QuadTo(154.0f, 237.0f, 222.0f, 120.0f),
                PathNode.QuadTo(290.0f, 3.0f, 407.5f, -66.0f),
                PathNode.QuadTo(525.0f, -135.0f, 665.0f, -135.0f),
                PathNode.QuadTo(805.0f, -135.0f, 922.0f, -66.5f),
                PathNode.QuadTo(1039.0f, 2.0f, 1107.0f, 119.5f),
                PathNode.QuadTo(1175.0f, 237.0f, 1175.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(387.0f, 53.0f),
                PathNode.QuadTo(445.0f, 134.0f, 517.0f, 177.0f),
                PathNode.QuadTo(589.0f, 220.0f, 665.0f, 220.0f),
                PathNode.QuadTo(741.0f, 220.0f, 813.0f, 177.0f),
                PathNode.QuadTo(885.0f, 134.0f, 942.0f, 53.0f),
                PathNode.QuadTo(885.0f, 4.0f, 814.0f, -23.0f),
                PathNode.QuadTo(743.0f, -50.0f, 665.0f, -50.0f),
                PathNode.QuadTo(587.0f, -50.0f, 515.5f, -23.0f),
                PathNode.QuadTo(444.0f, 4.0f, 387.0f, 53.0f),
                PathNode.Close,
                PathNode.MoveTo(240.0f, 375.0f),
                PathNode.QuadTo(240.0f, 491.0f, 297.0f, 588.5f),
                PathNode.QuadTo(354.0f, 686.0f, 451.5f, 743.0f),
                PathNode.QuadTo(549.0f, 800.0f, 665.0f, 800.0f),
                PathNode.QuadTo(780.0f, 800.0f, 877.5f, 743.0f),
                PathNode.QuadTo(975.0f, 686.0f, 1032.5f, 588.5f),
                PathNode.QuadTo(1090.0f, 491.0f, 1090.0f, 375.0f),
                PathNode.QuadTo(1090.0f, 303.0f, 1067.0f, 237.0f),
                PathNode.QuadTo(1044.0f, 171.0f, 1002.0f, 117.0f),
                PathNode.QuadTo(935.0f, 206.0f, 848.0f, 255.5f),
                PathNode.QuadTo(761.0f, 305.0f, 665.0f, 305.0f),
                PathNode.QuadTo(568.0f, 305.0f, 481.5f, 255.5f),
                PathNode.QuadTo(395.0f, 206.0f, 328.0f, 117.0f),
                PathNode.QuadTo(286.0f, 171.0f, 263.0f, 237.0f),
                PathNode.QuadTo(240.0f, 303.0f, 240.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(850.0f, 528.0f),
                PathNode.QuadTo(850.0f, 578.0f, 825.0f, 620.5f),
                PathNode.QuadTo(800.0f, 663.0f, 757.5f, 688.0f),
                PathNode.QuadTo(715.0f, 713.0f, 665.0f, 713.0f),
                PathNode.QuadTo(615.0f, 713.0f, 572.5f, 688.0f),
                PathNode.QuadTo(530.0f, 663.0f, 505.0f, 620.5f),
                PathNode.QuadTo(480.0f, 578.0f, 480.0f, 528.0f),
                PathNode.QuadTo(480.0f, 478.0f, 505.0f, 435.5f),
                PathNode.QuadTo(530.0f, 393.0f, 572.5f, 368.0f),
                PathNode.QuadTo(615.0f, 343.0f, 665.0f, 343.0f),
                PathNode.QuadTo(715.0f, 343.0f, 757.5f, 368.0f),
                PathNode.QuadTo(800.0f, 393.0f, 825.0f, 435.5f),
                PathNode.QuadTo(850.0f, 478.0f, 850.0f, 528.0f),
                PathNode.Close,
                PathNode.MoveTo(570.0f, 528.0f),
                PathNode.QuadTo(570.0f, 567.0f, 597.5f, 595.0f),
                PathNode.QuadTo(625.0f, 623.0f, 665.0f, 623.0f),
                PathNode.QuadTo(704.0f, 623.0f, 732.0f, 595.0f),
                PathNode.QuadTo(760.0f, 567.0f, 760.0f, 528.0f),
                PathNode.QuadTo(760.0f, 489.0f, 732.0f, 461.0f),
                PathNode.QuadTo(704.0f, 433.0f, 665.0f, 433.0f),
                PathNode.QuadTo(625.0f, 433.0f, 597.5f, 461.0f),
                PathNode.QuadTo(570.0f, 489.0f, 570.0f, 528.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactsbookRegular!!
    }

private var _contactsbookRegular: ImageVector? = null

val MiuixIcons.Heavy.ContactsBook: ImageVector
    get() {
        if (_contactsbookHeavy != null) return _contactsbookHeavy!!
        _contactsbookHeavy = ImageVector.Builder(
            name = "ContactsBook.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1258.8f,
            viewportHeight = 1258.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -35.10000000000002f, translationY = 1004.9f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1189.0f, 375.0f),
                PathNode.QuadTo(1189.0f, 517.0f, 1118.4690026954177f, 637.4596774193549f),
                PathNode.QuadTo(1047.9380053908355f, 757.9193548387096f, 927.4690026954178f, 828.9596774193549f),
                PathNode.QuadTo(807.0f, 900.0f, 665.0f, 900.0f),
                PathNode.QuadTo(523.0f, 900.0f, 402.5403225806451f, 828.9596774193549f),
                PathNode.QuadTo(282.0806451612903f, 757.9193548387096f, 211.04032258064515f, 637.4596774193549f),
                PathNode.QuadTo(140.0f, 517.0f, 140.0f, 375.0f),
                PathNode.QuadTo(140.0f, 233.21176470588236f, 209.86301369863014f, 113.0f),
                PathNode.QuadTo(279.7260273972603f, -7.211764705882352f, 400.44520547945206f, -78.10588235294118f),
                PathNode.QuadTo(521.1643835616438f, -149.0f, 665.0f, -149.0f),
                PathNode.QuadTo(808.0f, -149.0f, 928.4783783783784f, -78.61960784313726f),
                PathNode.QuadTo(1048.9567567567567f, -8.239215686274509f, 1118.9783783783782f, 112.48627450980392f),
                PathNode.QuadTo(1189.0f, 233.21176470588236f, 1189.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(403.0f, 55.0f),
                PathNode.QuadTo(457.84158415841586f, 129.69461077844312f, 525.9207920792079f, 169.34730538922156f),
                PathNode.QuadTo(594.0f, 209.0f, 665.0f, 209.0f),
                PathNode.QuadTo(736.0f, 209.0f, 804.0f, 169.5f),
                PathNode.QuadTo(872.0f, 130.0f, 927.0f, 55.0f),
                PathNode.QuadTo(873.086642599278f, 10.281553398058252f, 805.9314079422383f, -14.359223300970875f),
                PathNode.QuadTo(738.7761732851985f, -39.0f, 665.0f, -39.0f),
                PathNode.QuadTo(591.4892086330935f, -39.0f, 524.1043165467626f, -14.359223300970875f),
                PathNode.QuadTo(456.71942446043164f, 10.281553398058252f, 403.0f, 55.0f),
                PathNode.Close,
                PathNode.MoveTo(251.0f, 375.0f),
                PathNode.QuadTo(251.0f, 487.99764705882353f, 306.52222222222224f, 582.9741176470588f),
                PathNode.QuadTo(362.0444444444444f, 677.9505882352942f, 457.01666666666665f, 733.4752941176471f),
                PathNode.QuadTo(551.9888888888889f, 789.0f, 664.9814814814815f, 789.0f),
                PathNode.QuadTo(777.0f, 789.0f, 871.983870967742f, 733.4752941176471f),
                PathNode.QuadTo(966.9677419354839f, 677.9505882352942f, 1022.983870967742f, 582.9741176470588f),
                PathNode.QuadTo(1079.0f, 487.99764705882353f, 1079.0f, 375.0f),
                PathNode.QuadTo(1079.0f, 309.0f, 1059.0f, 247.5f),
                PathNode.QuadTo(1039.0f, 186.0f, 1002.0f, 135.0f),
                PathNode.QuadTo(934.0f, 221.0f, 847.3222222222222f, 268.5f),
                PathNode.QuadTo(760.6444444444444f, 316.0f, 665.0f, 316.0f),
                PathNode.QuadTo(568.3592592592593f, 316.0f, 482.17962962962963f, 268.5f),
                PathNode.QuadTo(396.0f, 221.0f, 328.0f, 135.0f),
                PathNode.QuadTo(291.0f, 186.0f, 271.0f, 247.14705882352942f),
                PathNode.QuadTo(251.0f, 308.29411764705884f, 251.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(856.0f, 534.0f),
                PathNode.QuadTo(856.0f, 586.0f, 830.1891891891892f, 629.4444444444445f),
                PathNode.QuadTo(804.3783783783783f, 672.8888888888889f, 760.5f, 698.4444444444445f),
                PathNode.QuadTo(716.6216216216217f, 724.0f, 665.0f, 724.0f),
                PathNode.QuadTo(613.3783783783783f, 724.0f, 569.5f, 698.4444444444445f),
                PathNode.QuadTo(525.6216216216217f, 672.8888888888889f, 499.81081081081084f, 629.4444444444445f),
                PathNode.QuadTo(474.0f, 586.0f, 474.0f, 534.0f),
                PathNode.QuadTo(474.0f, 482.0f, 500.0f, 438.5f),
                PathNode.QuadTo(526.0f, 395.0f, 569.5f, 369.0f),
                PathNode.QuadTo(613.0f, 343.0f, 665.0f, 343.0f),
                PathNode.QuadTo(717.0f, 343.0f, 760.5f, 369.0f),
                PathNode.QuadTo(804.0f, 395.0f, 830.0f, 438.5f),
                PathNode.QuadTo(856.0f, 482.0f, 856.0f, 534.0f),
                PathNode.Close,
                PathNode.MoveTo(582.0f, 534.0f),
                PathNode.QuadTo(582.0f, 569.0f, 606.5f, 593.0f),
                PathNode.QuadTo(631.0f, 617.0f, 665.0f, 617.0f),
                PathNode.QuadTo(700.0f, 617.0f, 724.0f, 593.0f),
                PathNode.QuadTo(748.0f, 569.0f, 748.0f, 534.5f),
                PathNode.QuadTo(748.0f, 500.0f, 724.0f, 475.5f),
                PathNode.QuadTo(700.0f, 451.0f, 665.0f, 451.0f),
                PathNode.QuadTo(631.0f, 451.0f, 606.5f, 475.4631578947368f),
                PathNode.QuadTo(582.0f, 499.9263157894737f, 582.0f, 534.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactsbookHeavy!!
    }

private var _contactsbookHeavy: ImageVector? = null
