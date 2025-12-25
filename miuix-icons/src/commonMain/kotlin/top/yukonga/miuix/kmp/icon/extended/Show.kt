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

val MiuixIcons.Show: ImageVector
    get() = MiuixIcons.Regular.Show

val MiuixIcons.Light.Show: ImageVector
    get() {
        if (_showLight != null) return _showLight!!
        _showLight = ImageVector.Builder(
            name = "Show.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1346.3999999999999f,
            viewportHeight = 1346.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 8.199999999999932f, translationY = 1048.6999999999998f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1216.0f, 330.0f),
                PathNode.QuadTo(1226.0f, 351.5064935064935f, 1226.0f, 375.25324675324674f),
                PathNode.QuadTo(1226.0f, 399.0f, 1216.0f, 421.0f),
                PathNode.QuadTo(1170.0f, 524.0f, 1083.5f, 605.0f),
                PathNode.QuadTo(997.0f, 686.0f, 888.0f, 731.5f),
                PathNode.QuadTo(779.0f, 777.0f, 665.4935064935065f, 777.0f),
                PathNode.QuadTo(551.0f, 777.0f, 442.17424242424244f, 731.5390625f),
                PathNode.QuadTo(333.3484848484849f, 686.078125f, 247.17424242424244f, 605.0390625f),
                PathNode.QuadTo(161.0f, 524.0f, 114.0f, 421.0f),
                PathNode.QuadTo(104.0f, 399.4935064935065f, 104.0f, 375.74675324675326f),
                PathNode.QuadTo(104.0f, 352.0f, 114.0f, 330.0f),
                PathNode.QuadTo(161.0f, 227.0f, 247.45226130653268f, 145.9609375f),
                PathNode.QuadTo(333.90452261306535f, 64.921875f, 442.9522613065327f, 19.4609375f),
                PathNode.QuadTo(552.0f, -26.0f, 665.0f, -26.0f),
                PathNode.QuadTo(778.0f, -26.0f, 886.829974811083f, 19.4609375f),
                PathNode.QuadTo(995.6599496221662f, 64.921875f, 1082.329974811083f, 145.9609375f),
                PathNode.QuadTo(1169.0f, 227.0f, 1216.0f, 330.0f),
                PathNode.Close,
                PathNode.MoveTo(167.0f, 355.0f),
                PathNode.QuadTo(158.0f, 376.0f, 167.0f, 396.0f),
                PathNode.QuadTo(210.0f, 489.0f, 287.8768518518518f, 562.0636363636363f),
                PathNode.QuadTo(365.7537037037037f, 635.1272727272727f, 464.26018518518515f, 676.0636363636363f),
                PathNode.QuadTo(562.7666666666667f, 717.0f, 664.8833333333333f, 717.0f),
                PathNode.QuadTo(767.0f, 717.0f, 866.0f, 676.0f),
                PathNode.QuadTo(965.0f, 635.0f, 1043.0f, 562.0f),
                PathNode.QuadTo(1121.0f, 489.0f, 1163.0f, 396.0f),
                PathNode.QuadTo(1171.0f, 376.0f, 1163.0f, 355.0f),
                PathNode.QuadTo(1120.5129533678758f, 261.8965517241379f, 1041.7564766839378f, 188.44827586206895f),
                PathNode.QuadTo(963.0f, 115.0f, 864.2836787564767f, 74.0f),
                PathNode.QuadTo(765.5673575129533f, 33.0f, 665.300518134715f, 33.0f),
                PathNode.QuadTo(564.0f, 33.0f, 465.0f, 74.0f),
                PathNode.QuadTo(366.0f, 115.0f, 287.6373056994819f, 188.44827586206895f),
                PathNode.QuadTo(209.27461139896374f, 261.8965517241379f, 167.0f, 355.0f),
                PathNode.Close,
                PathNode.MoveTo(879.0f, 374.520325203252f),
                PathNode.QuadTo(879.0f, 434.0f, 850.3484848484849f, 483.1636363636364f),
                PathNode.QuadTo(821.6969696969697f, 532.3272727272728f, 772.8484848484849f, 561.1636363636364f),
                PathNode.QuadTo(724.0f, 590.0f, 665.0f, 590.0f),
                PathNode.QuadTo(606.0f, 590.0f, 557.1515151515151f, 561.1123348017621f),
                PathNode.QuadTo(508.3030303030303f, 532.2246696035243f, 479.6515151515151f, 482.97356828193836f),
                PathNode.QuadTo(451.0f, 433.72246696035245f, 451.0f, 375.0f),
                PathNode.QuadTo(451.0f, 316.0f, 479.6515151515151f, 267.1515151515151f),
                PathNode.QuadTo(508.3030303030303f, 218.3030303030303f, 557.1515151515151f, 189.65151515151516f),
                PathNode.QuadTo(606.0f, 161.0f, 665.0f, 161.0f),
                PathNode.QuadTo(724.0f, 161.0f, 772.8484848484849f, 189.65151515151516f),
                PathNode.QuadTo(821.6969696969697f, 218.3030303030303f, 850.3484848484849f, 267.1515151515151f),
                PathNode.QuadTo(879.0f, 316.0f, 879.0f, 374.520325203252f),
                PathNode.Close,
                PathNode.MoveTo(509.0f, 374.9664804469274f),
                PathNode.QuadTo(509.0f, 417.0f, 530.0167597765363f, 452.97087378640776f),
                PathNode.QuadTo(551.0335195530727f, 488.94174757281553f, 586.9832402234638f, 509.97087378640776f),
                PathNode.QuadTo(622.9329608938548f, 531.0f, 664.9664804469273f, 531.0f),
                PathNode.QuadTo(707.0f, 531.0f, 742.9708737864078f, 509.98324022346367f),
                PathNode.QuadTo(778.9417475728155f, 488.9664804469274f, 799.9708737864078f, 453.01675977653633f),
                PathNode.QuadTo(821.0f, 417.0670391061453f, 821.0f, 375.0335195530726f),
                PathNode.QuadTo(821.0f, 333.0f, 799.9832402234637f, 297.02912621359224f),
                PathNode.QuadTo(778.9664804469273f, 261.05825242718447f, 743.0167597765362f, 240.02912621359224f),
                PathNode.QuadTo(707.0670391061452f, 219.0f, 665.0335195530727f, 219.0f),
                PathNode.QuadTo(623.0f, 219.0f, 587.0291262135922f, 240.0167597765363f),
                PathNode.QuadTo(551.0582524271845f, 261.0335195530726f, 530.0291262135922f, 296.98324022346367f),
                PathNode.QuadTo(509.0f, 332.9329608938547f, 509.0f, 374.9664804469274f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _showLight!!
    }

private var _showLight: ImageVector? = null

val MiuixIcons.Regular.Show: ImageVector
    get() {
        if (_showRegular != null) return _showRegular!!
        _showRegular = ImageVector.Builder(
            name = "Show.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1368.0f,
            viewportHeight = 1368.0f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 19.0f, translationY = 1059.5f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1224.0f, 325.0f),
                PathNode.QuadTo(1235.0f, 349.0f, 1235.0f, 375.5f),
                PathNode.QuadTo(1235.0f, 402.0f, 1224.0f, 426.0f),
                PathNode.QuadTo(1177.0f, 531.0f, 1089.5f, 613.0f),
                PathNode.QuadTo(1002.0f, 695.0f, 891.0f, 741.0f),
                PathNode.QuadTo(780.0f, 787.0f, 665.0f, 787.0f),
                PathNode.QuadTo(549.0f, 787.0f, 438.5f, 741.0f),
                PathNode.QuadTo(328.0f, 695.0f, 240.5f, 613.0f),
                PathNode.QuadTo(153.0f, 531.0f, 106.0f, 426.0f),
                PathNode.QuadTo(95.0f, 402.0f, 95.0f, 375.5f),
                PathNode.QuadTo(95.0f, 349.0f, 106.0f, 325.0f),
                PathNode.QuadTo(153.0f, 220.0f, 241.0f, 138.0f),
                PathNode.QuadTo(329.0f, 56.0f, 440.0f, 10.0f),
                PathNode.QuadTo(551.0f, -36.0f, 665.0f, -36.0f),
                PathNode.QuadTo(779.0f, -36.0f, 889.5f, 10.0f),
                PathNode.QuadTo(1000.0f, 56.0f, 1088.0f, 138.0f),
                PathNode.QuadTo(1176.0f, 220.0f, 1224.0f, 325.0f),
                PathNode.Close,
                PathNode.MoveTo(183.0f, 361.0f),
                PathNode.QuadTo(177.0f, 375.0f, 183.0f, 391.0f),
                PathNode.QuadTo(224.0f, 481.0f, 299.5f, 551.5f),
                PathNode.QuadTo(375.0f, 622.0f, 470.5f, 661.5f),
                PathNode.QuadTo(566.0f, 701.0f, 665.0f, 701.0f),
                PathNode.QuadTo(764.0f, 701.0f, 859.5f, 661.5f),
                PathNode.QuadTo(955.0f, 622.0f, 1030.0f, 551.0f),
                PathNode.QuadTo(1105.0f, 480.0f, 1146.0f, 391.0f),
                PathNode.QuadTo(1152.0f, 375.0f, 1146.0f, 361.0f),
                PathNode.QuadTo(1105.0f, 271.0f, 1029.0f, 200.0f),
                PathNode.QuadTo(953.0f, 129.0f, 857.5f, 89.0f),
                PathNode.QuadTo(762.0f, 49.0f, 665.0f, 49.0f),
                PathNode.QuadTo(567.0f, 49.0f, 471.5f, 89.0f),
                PathNode.QuadTo(376.0f, 129.0f, 300.0f, 200.0f),
                PathNode.QuadTo(224.0f, 271.0f, 183.0f, 361.0f),
                PathNode.Close,
                PathNode.MoveTo(891.0f, 375.0f),
                PathNode.QuadTo(891.0f, 437.0f, 860.5f, 489.0f),
                PathNode.QuadTo(830.0f, 541.0f, 778.0f, 571.5f),
                PathNode.QuadTo(726.0f, 602.0f, 665.0f, 602.0f),
                PathNode.QuadTo(604.0f, 602.0f, 552.0f, 571.5f),
                PathNode.QuadTo(500.0f, 541.0f, 469.5f, 489.0f),
                PathNode.QuadTo(439.0f, 437.0f, 439.0f, 375.0f),
                PathNode.QuadTo(439.0f, 314.0f, 469.5f, 262.0f),
                PathNode.QuadTo(500.0f, 210.0f, 552.0f, 179.5f),
                PathNode.QuadTo(604.0f, 149.0f, 665.0f, 149.0f),
                PathNode.QuadTo(726.0f, 149.0f, 778.0f, 179.5f),
                PathNode.QuadTo(830.0f, 210.0f, 860.5f, 262.0f),
                PathNode.QuadTo(891.0f, 314.0f, 891.0f, 375.0f),
                PathNode.Close,
                PathNode.MoveTo(524.0f, 375.0f),
                PathNode.QuadTo(524.0f, 413.0f, 543.0f, 445.5f),
                PathNode.QuadTo(562.0f, 478.0f, 594.5f, 497.0f),
                PathNode.QuadTo(627.0f, 516.0f, 665.0f, 516.0f),
                PathNode.QuadTo(703.0f, 516.0f, 735.5f, 497.0f),
                PathNode.QuadTo(768.0f, 478.0f, 787.0f, 445.5f),
                PathNode.QuadTo(806.0f, 413.0f, 806.0f, 375.0f),
                PathNode.QuadTo(806.0f, 337.0f, 787.0f, 304.5f),
                PathNode.QuadTo(768.0f, 272.0f, 735.5f, 253.0f),
                PathNode.QuadTo(703.0f, 234.0f, 665.0f, 234.0f),
                PathNode.QuadTo(627.0f, 234.0f, 594.5f, 253.0f),
                PathNode.QuadTo(562.0f, 272.0f, 543.0f, 304.5f),
                PathNode.QuadTo(524.0f, 337.0f, 524.0f, 375.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _showRegular!!
    }

private var _showRegular: ImageVector? = null

val MiuixIcons.Heavy.Show: ImageVector
    get() {
        if (_showHeavy != null) return _showHeavy!!
        _showHeavy = ImageVector.Builder(
            name = "Show.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1393.2f,
            viewportHeight = 1393.2f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = 32.10000000000002f, translationY = 1072.1f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1233.0f, 319.0f),
                PathNode.QuadTo(1245.0f, 345.8051948051948f, 1245.0f, 375.4025974025974f),
                PathNode.QuadTo(1245.0f, 405.0f, 1233.0f, 432.0f),
                PathNode.QuadTo(1185.1531531531532f, 538.9516728624535f, 1096.0765765765766f, 622.4758364312268f),
                PathNode.QuadTo(1007.0f, 706.0f, 894.2847682119206f, 752.5f),
                PathNode.QuadTo(781.5695364238411f, 799.0f, 664.7924944812362f, 799.0f),
                PathNode.QuadTo(547.0f, 799.0f, 434.5f, 752.5f),
                PathNode.QuadTo(322.0f, 706.0f, 233.31756756756755f, 622.4758364312268f),
                PathNode.QuadTo(144.63513513513513f, 538.9516728624535f, 97.0f, 432.0f),
                PathNode.QuadTo(84.0f, 405.1948051948052f, 84.0f, 375.5974025974026f),
                PathNode.QuadTo(84.0f, 346.0f, 97.0f, 319.0f),
                PathNode.QuadTo(144.6322869955157f, 212.04832713754647f, 233.81614349775785f, 128.52416356877325f),
                PathNode.QuadTo(323.0f, 45.0f, 435.97333333333336f, -1.5f),
                PathNode.QuadTo(548.9466666666667f, -48.0f, 664.9733333333334f, -48.0f),
                PathNode.QuadTo(781.0f, -48.0f, 893.448362720403f, -1.28125f),
                PathNode.QuadTo(1005.896725440806f, 45.4375f, 1095.448362720403f, 128.71875f),
                PathNode.QuadTo(1185.0f, 212.0f, 1233.0f, 319.0f),
                PathNode.Close,
                PathNode.MoveTo(202.0f, 367.0f),
                PathNode.QuadTo(199.0f, 375.0f, 202.0f, 385.0f),
                PathNode.QuadTo(242.0f, 471.0f, 314.42407407407404f, 538.6159090909091f),
                PathNode.QuadTo(386.84814814814814f, 606.2318181818182f, 478.45740740740746f, 644.1159090909091f),
                PathNode.QuadTo(570.0666666666667f, 682.0f, 665.0333333333333f, 682.0f),
                PathNode.QuadTo(760.0f, 682.0f, 851.5f, 644.0f),
                PathNode.QuadTo(943.0f, 606.0f, 1015.5f, 538.5f),
                PathNode.QuadTo(1088.0f, 471.0f, 1127.0f, 385.0f),
                PathNode.QuadTo(1130.0f, 376.0f, 1127.0f, 367.0f),
                PathNode.QuadTo(1087.0f, 281.0f, 1014.5f, 213.0f),
                PathNode.QuadTo(942.0f, 145.0f, 850.2111398963731f, 106.5f),
                PathNode.QuadTo(758.4222797927462f, 68.0f, 665.1917098445596f, 68.0f),
                PathNode.QuadTo(571.0f, 68.0f, 479.3979591836735f, 106.37837837837837f),
                PathNode.QuadTo(387.7959183673469f, 144.75675675675674f, 314.8979591836735f, 212.8783783783784f),
                PathNode.QuadTo(242.0f, 281.0f, 202.0f, 367.0f),
                PathNode.Close,
                PathNode.MoveTo(901.0f, 374.68722466960355f),
                PathNode.QuadTo(901.0f, 439.4185022026432f, 869.5f, 493.70925110132157f),
                PathNode.QuadTo(838.0f, 548.0f, 783.4690265486726f, 580.0f),
                PathNode.QuadTo(728.9380530973451f, 612.0f, 664.9690265486726f, 612.0f),
                PathNode.QuadTo(601.0f, 612.0f, 546.5f, 580.0f),
                PathNode.QuadTo(492.0f, 548.0f, 460.5f, 493.70925110132157f),
                PathNode.QuadTo(429.0f, 439.4185022026432f, 429.0f, 374.68722466960355f),
                PathNode.QuadTo(429.0f, 311.0f, 460.5f, 256.5f),
                PathNode.QuadTo(492.0f, 202.0f, 546.5309734513274f, 170.5f),
                PathNode.QuadTo(601.0619469026549f, 139.0f, 665.0309734513274f, 139.0f),
                PathNode.QuadTo(729.0f, 139.0f, 783.5f, 170.5f),
                PathNode.QuadTo(838.0f, 202.0f, 869.5f, 256.5f),
                PathNode.QuadTo(901.0f, 311.0f, 901.0f, 374.68722466960355f),
                PathNode.Close,
                PathNode.MoveTo(537.0f, 375.2340425531915f),
                PathNode.QuadTo(537.0f, 410.0f, 554.1553398058252f, 439.34466019417476f),
                PathNode.QuadTo(571.3106796116505f, 468.68932038834953f, 600.6553398058252f, 485.84466019417476f),
                PathNode.QuadTo(630.0f, 503.0f, 664.7659574468086f, 503.0f),
                PathNode.QuadTo(699.531914893617f, 503.0f, 729.2659574468084f, 486.0f),
                PathNode.QuadTo(759.0f, 469.0f, 776.0f, 439.2659574468085f),
                PathNode.QuadTo(793.0f, 409.531914893617f, 793.0f, 374.7659574468085f),
                PathNode.QuadTo(793.0f, 340.0f, 775.8446601941748f, 310.65533980582524f),
                PathNode.QuadTo(758.6893203883495f, 281.31067961165047f, 729.3446601941748f, 264.15533980582524f),
                PathNode.QuadTo(700.0f, 247.0f, 665.2340425531914f, 247.0f),
                PathNode.QuadTo(630.468085106383f, 247.0f, 600.7340425531916f, 264.0f),
                PathNode.QuadTo(571.0f, 281.0f, 554.0f, 310.7340425531915f),
                PathNode.QuadTo(537.0f, 340.468085106383f, 537.0f, 375.2340425531915f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _showHeavy!!
    }

private var _showHeavy: ImageVector? = null
