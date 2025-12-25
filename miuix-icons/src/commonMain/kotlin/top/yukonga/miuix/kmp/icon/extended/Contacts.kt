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

val MiuixIcons.Contacts: ImageVector
    get() = MiuixIcons.Regular.Contacts

val MiuixIcons.Light.Contacts: ImageVector
    get() {
        if (_contactsLight != null) return _contactsLight!!
        _contactsLight = ImageVector.Builder(
            name = "Contacts.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1155.6f,
            viewportHeight = 1155.6f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -86.70000000000005f, translationY = 940.8f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1111.0f, -109.0f),
                PathNode.QuadTo(1131.0f, -99.0f, 1138.0f, -80.0f),
                PathNode.QuadTo(1143.0f, -70.0f, 1144.5f, -53.5f),
                PathNode.QuadTo(1146.0f, -37.0f, 1146.0f, 0.0f),
                PathNode.QuadTo(1146.0f, 45.0f, 1144.0f, 69.0f),
                PathNode.QuadTo(1142.0f, 93.0f, 1135.0f, 113.0f),
                PathNode.QuadTo(1129.0f, 131.0f, 1114.0f, 151.0f),
                PathNode.QuadTo(1099.0f, 171.0f, 1083.0f, 183.0f),
                PathNode.QuadTo(1065.0f, 197.0f, 1048.0f, 205.5f),
                PathNode.QuadTo(1031.0f, 214.0f, 1001.0f, 224.0f),
                PathNode.QuadTo(834.0f, 279.0f, 665.0f, 279.0f),
                PathNode.QuadTo(494.0f, 279.0f, 329.0f, 224.0f),
                PathNode.QuadTo(297.0f, 213.0f, 280.0f, 205.0f),
                PathNode.QuadTo(263.0f, 197.0f, 245.0f, 184.0f),
                PathNode.QuadTo(230.0f, 172.0f, 215.0f, 151.5f),
                PathNode.QuadTo(200.0f, 131.0f, 193.0f, 113.0f),
                PathNode.QuadTo(187.0f, 93.0f, 185.0f, 70.0f),
                PathNode.QuadTo(183.0f, 47.0f, 183.0f, 0.0f),
                PathNode.QuadTo(183.0f, -37.0f, 184.5f, -53.5f),
                PathNode.QuadTo(186.0f, -70.0f, 191.0f, -80.0f),
                PathNode.QuadTo(199.0f, -100.0f, 219.0f, -109.0f),
                PathNode.QuadTo(229.0f, -115.0f, 244.5f, -116.0f),
                PathNode.QuadTo(260.0f, -117.0f, 298.0f, -117.0f),
                PathNode.HorizontalTo(1031.0f),
                PathNode.QuadTo(1069.0f, -117.0f, 1084.5f, -116.0f),
                PathNode.QuadTo(1100.0f, -115.0f, 1111.0f, -109.0f),
                PathNode.Close,
                PathNode.MoveTo(242.0f, -26.0f),
                PathNode.VerticalTo(10.0f),
                PathNode.QuadTo(242.0f, 43.0f, 243.5f, 59.5f),
                PathNode.QuadTo(245.0f, 76.0f, 250.0f, 90.0f),
                PathNode.QuadTo(262.0f, 121.0f, 283.0f, 136.0f),
                PathNode.QuadTo(294.0f, 146.0f, 307.0f, 152.0f),
                PathNode.QuadTo(320.0f, 158.0f, 345.0f, 166.0f),
                PathNode.QuadTo(497.0f, 220.0f, 659.0f, 220.0f),
                PathNode.QuadTo(824.0f, 220.0f, 989.0f, 160.0f),
                PathNode.LineTo(999.0f, 156.0f),
                PathNode.QuadTo(1014.0f, 150.0f, 1026.5f, 143.5f),
                PathNode.QuadTo(1039.0f, 137.0f, 1048.0f, 129.0f),
                PathNode.QuadTo(1071.0f, 109.0f, 1079.0f, 82.0f),
                PathNode.QuadTo(1084.0f, 68.0f, 1085.5f, 51.0f),
                PathNode.QuadTo(1087.0f, 34.0f, 1087.0f, 5.0f),
                PathNode.VerticalTo(-27.0f),
                PathNode.QuadTo(1087.0f, -44.0f, 1081.0f, -50.5f),
                PathNode.QuadTo(1075.0f, -57.0f, 1059.0f, -57.0f),
                PathNode.HorizontalTo(272.0f),
                PathNode.QuadTo(255.0f, -57.0f, 248.5f, -50.5f),
                PathNode.QuadTo(242.0f, -44.0f, 242.0f, -26.0f),
                PathNode.Close,
                PathNode.MoveTo(900.0f, 607.0f),
                PathNode.QuadTo(900.0f, 671.0f, 868.0f, 725.0f),
                PathNode.QuadTo(836.0f, 779.0f, 782.0f, 811.0f),
                PathNode.QuadTo(728.0f, 843.0f, 665.0f, 843.0f),
                PathNode.QuadTo(601.0f, 843.0f, 547.0f, 811.0f),
                PathNode.QuadTo(493.0f, 779.0f, 461.0f, 725.0f),
                PathNode.QuadTo(429.0f, 671.0f, 429.0f, 607.0f),
                PathNode.QuadTo(429.0f, 543.0f, 460.5f, 489.0f),
                PathNode.QuadTo(492.0f, 435.0f, 546.5f, 403.5f),
                PathNode.QuadTo(601.0f, 372.0f, 665.0f, 372.0f),
                PathNode.QuadTo(729.0f, 372.0f, 783.0f, 403.5f),
                PathNode.QuadTo(837.0f, 435.0f, 868.5f, 489.0f),
                PathNode.QuadTo(900.0f, 543.0f, 900.0f, 607.0f),
                PathNode.Close,
                PathNode.MoveTo(488.0f, 607.0f),
                PathNode.QuadTo(488.0f, 655.0f, 512.0f, 695.5f),
                PathNode.QuadTo(536.0f, 736.0f, 576.5f, 759.5f),
                PathNode.QuadTo(617.0f, 783.0f, 665.0f, 783.0f),
                PathNode.QuadTo(712.0f, 783.0f, 752.5f, 759.5f),
                PathNode.QuadTo(793.0f, 736.0f, 817.0f, 695.5f),
                PathNode.QuadTo(841.0f, 655.0f, 841.0f, 607.0f),
                PathNode.QuadTo(841.0f, 559.0f, 817.0f, 518.5f),
                PathNode.QuadTo(793.0f, 478.0f, 752.5f, 454.5f),
                PathNode.QuadTo(712.0f, 431.0f, 665.0f, 431.0f),
                PathNode.QuadTo(617.0f, 431.0f, 576.5f, 454.5f),
                PathNode.QuadTo(536.0f, 478.0f, 512.0f, 518.5f),
                PathNode.QuadTo(488.0f, 559.0f, 488.0f, 607.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactsLight!!
    }

private var _contactsLight: ImageVector? = null

val MiuixIcons.Regular.Contacts: ImageVector
    get() {
        if (_contactsRegular != null) return _contactsRegular!!
        _contactsRegular = ImageVector.Builder(
            name = "Contacts.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1186.8f,
            viewportHeight = 1186.8f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -71.10000000000002f, translationY = 956.4f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1116.0f, -121.0f),
                PathNode.QuadTo(1140.0f, -108.0f, 1150.0f, -85.0f),
                PathNode.QuadTo(1156.0f, -73.0f, 1157.5f, -56.5f),
                PathNode.QuadTo(1159.0f, -40.0f, 1159.0f, 0.0f),
                PathNode.QuadTo(1159.0f, 51.0f, 1157.5f, 73.5f),
                PathNode.QuadTo(1156.0f, 96.0f, 1148.0f, 117.0f),
                PathNode.QuadTo(1141.0f, 137.0f, 1125.0f, 159.5f),
                PathNode.QuadTo(1109.0f, 182.0f, 1092.0f, 195.0f),
                PathNode.QuadTo(1075.0f, 209.0f, 1058.0f, 216.5f),
                PathNode.QuadTo(1041.0f, 224.0f, 1005.0f, 236.0f),
                PathNode.QuadTo(839.0f, 292.0f, 665.0f, 292.0f),
                PathNode.QuadTo(490.0f, 292.0f, 325.0f, 236.0f),
                PathNode.QuadTo(288.0f, 224.0f, 271.0f, 216.5f),
                PathNode.QuadTo(254.0f, 209.0f, 237.0f, 195.0f),
                PathNode.QuadTo(220.0f, 181.0f, 204.0f, 159.0f),
                PathNode.QuadTo(188.0f, 137.0f, 181.0f, 117.0f),
                PathNode.QuadTo(173.0f, 96.0f, 171.5f, 73.5f),
                PathNode.QuadTo(170.0f, 51.0f, 170.0f, 0.0f),
                PathNode.QuadTo(170.0f, -40.0f, 171.5f, -56.5f),
                PathNode.QuadTo(173.0f, -73.0f, 179.0f, -85.0f),
                PathNode.QuadTo(189.0f, -108.0f, 213.0f, -121.0f),
                PathNode.QuadTo(225.0f, -127.0f, 242.0f, -128.5f),
                PathNode.QuadTo(259.0f, -130.0f, 298.0f, -130.0f),
                PathNode.HorizontalTo(1031.0f),
                PathNode.QuadTo(1070.0f, -130.0f, 1087.0f, -128.5f),
                PathNode.QuadTo(1104.0f, -127.0f, 1116.0f, -121.0f),
                PathNode.Close,
                PathNode.MoveTo(255.0f, -14.0f),
                PathNode.VerticalTo(10.0f),
                PathNode.QuadTo(255.0f, 42.0f, 256.0f, 56.5f),
                PathNode.QuadTo(257.0f, 71.0f, 262.0f, 84.0f),
                PathNode.QuadTo(273.0f, 110.0f, 291.0f, 125.0f),
                PathNode.QuadTo(302.0f, 134.0f, 314.0f, 140.0f),
                PathNode.QuadTo(326.0f, 146.0f, 349.0f, 153.0f),
                PathNode.QuadTo(494.0f, 207.0f, 655.0f, 207.0f),
                PathNode.QuadTo(829.0f, 207.0f, 984.0f, 145.0f),
                PathNode.LineTo(994.0f, 141.0f),
                PathNode.QuadTo(1009.0f, 135.0f, 1020.0f, 129.0f),
                PathNode.QuadTo(1031.0f, 123.0f, 1039.0f, 116.0f),
                PathNode.QuadTo(1059.0f, 98.0f, 1067.0f, 74.0f),
                PathNode.QuadTo(1072.0f, 62.0f, 1073.0f, 48.0f),
                PathNode.QuadTo(1074.0f, 34.0f, 1074.0f, 3.0f),
                PathNode.VerticalTo(-14.0f),
                PathNode.QuadTo(1074.0f, -34.0f, 1068.5f, -39.0f),
                PathNode.QuadTo(1063.0f, -44.0f, 1043.0f, -44.0f),
                PathNode.HorizontalTo(286.0f),
                PathNode.QuadTo(266.0f, -44.0f, 260.5f, -39.0f),
                PathNode.QuadTo(255.0f, -34.0f, 255.0f, -14.0f),
                PathNode.Close,
                PathNode.MoveTo(913.0f, 607.0f),
                PathNode.QuadTo(913.0f, 674.0f, 879.5f, 731.5f),
                PathNode.QuadTo(846.0f, 789.0f, 789.0f, 822.5f),
                PathNode.QuadTo(732.0f, 856.0f, 665.0f, 856.0f),
                PathNode.QuadTo(597.0f, 856.0f, 540.0f, 822.5f),
                PathNode.QuadTo(483.0f, 789.0f, 449.5f, 731.5f),
                PathNode.QuadTo(416.0f, 674.0f, 416.0f, 607.0f),
                PathNode.QuadTo(416.0f, 540.0f, 449.5f, 483.0f),
                PathNode.QuadTo(483.0f, 426.0f, 540.0f, 392.5f),
                PathNode.QuadTo(597.0f, 359.0f, 665.0f, 359.0f),
                PathNode.QuadTo(732.0f, 359.0f, 789.0f, 392.5f),
                PathNode.QuadTo(846.0f, 426.0f, 879.5f, 483.0f),
                PathNode.QuadTo(913.0f, 540.0f, 913.0f, 607.0f),
                PathNode.Close,
                PathNode.MoveTo(501.0f, 607.0f),
                PathNode.QuadTo(501.0f, 651.0f, 523.0f, 688.5f),
                PathNode.QuadTo(545.0f, 726.0f, 583.0f, 748.0f),
                PathNode.QuadTo(621.0f, 770.0f, 665.0f, 770.0f),
                PathNode.QuadTo(709.0f, 770.0f, 746.5f, 748.0f),
                PathNode.QuadTo(784.0f, 726.0f, 806.0f, 688.5f),
                PathNode.QuadTo(828.0f, 651.0f, 828.0f, 607.0f),
                PathNode.QuadTo(828.0f, 563.0f, 806.0f, 525.5f),
                PathNode.QuadTo(784.0f, 488.0f, 746.5f, 466.0f),
                PathNode.QuadTo(709.0f, 444.0f, 665.0f, 444.0f),
                PathNode.QuadTo(621.0f, 444.0f, 583.0f, 466.0f),
                PathNode.QuadTo(545.0f, 488.0f, 523.0f, 525.5f),
                PathNode.QuadTo(501.0f, 563.0f, 501.0f, 607.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactsRegular!!
    }

private var _contactsRegular: ImageVector? = null

val MiuixIcons.Heavy.Contacts: ImageVector
    get() {
        if (_contactsHeavy != null) return _contactsHeavy!!
        _contactsHeavy = ImageVector.Builder(
            name = "Contacts.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1220.3999999999999f,
            viewportHeight = 1220.3999999999999f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -54.30000000000007f, translationY = 975.1999999999999f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(1122.0f, -133.0f),
                PathNode.QuadTo(1149.0f, -119.0f, 1162.0f, -91.0f),
                PathNode.QuadTo(1170.0f, -77.0f, 1171.5f, -59.0f),
                PathNode.QuadTo(1173.0f, -41.0f, 1173.0f, 0.0f),
                PathNode.QuadTo(1173.0f, 42.0f, 1171.0f, 66.5f),
                PathNode.QuadTo(1169.0f, 91.0f, 1161.0f, 113.0f),
                PathNode.QuadTo(1154.0f, 134.0f, 1137.0f, 158.0f),
                PathNode.QuadTo(1120.0f, 182.0f, 1101.0f, 197.0f),
                PathNode.QuadTo(1082.0f, 211.0f, 1064.0f, 219.5f),
                PathNode.QuadTo(1046.0f, 228.0f, 1010.0f, 240.0f),
                PathNode.QuadTo(842.0f, 297.0f, 665.0f, 297.0f),
                PathNode.QuadTo(486.0f, 297.0f, 320.0f, 240.0f),
                PathNode.QuadTo(284.0f, 228.0f, 265.5f, 219.5f),
                PathNode.QuadTo(247.0f, 211.0f, 228.0f, 197.0f),
                PathNode.QuadTo(209.0f, 183.0f, 192.5f, 159.5f),
                PathNode.QuadTo(176.0f, 136.0f, 168.0f, 113.0f),
                PathNode.QuadTo(160.0f, 91.0f, 158.0f, 66.5f),
                PathNode.QuadTo(156.0f, 42.0f, 156.0f, 0.0f),
                PathNode.QuadTo(156.0f, -41.0f, 157.5f, -59.0f),
                PathNode.QuadTo(159.0f, -77.0f, 167.0f, -91.0f),
                PathNode.QuadTo(180.0f, -119.0f, 207.0f, -133.0f),
                PathNode.QuadTo(221.0f, -140.0f, 240.0f, -141.5f),
                PathNode.QuadTo(259.0f, -143.0f, 298.0f, -143.0f),
                PathNode.HorizontalTo(1031.0f),
                PathNode.QuadTo(1074.0f, -143.0f, 1091.0f, -141.5f),
                PathNode.QuadTo(1108.0f, -140.0f, 1122.0f, -133.0f),
                PathNode.Close,
                PathNode.MoveTo(269.0f, -11.0f),
                PathNode.VerticalTo(10.0f),
                PathNode.QuadTo(269.0f, 29.0f, 270.0f, 44.0f),
                PathNode.QuadTo(271.0f, 59.0f, 275.0f, 70.0f),
                PathNode.QuadTo(284.0f, 93.0f, 300.0f, 106.0f),
                PathNode.QuadTo(309.0f, 114.0f, 320.0f, 119.0f),
                PathNode.QuadTo(331.0f, 124.0f, 354.0f, 132.0f),
                PathNode.QuadTo(495.0f, 184.0f, 655.0f, 184.0f),
                PathNode.QuadTo(828.0f, 184.0f, 979.0f, 123.0f),
                PathNode.LineTo(988.0f, 119.0f),
                PathNode.QuadTo(1002.0f, 113.0f, 1012.0f, 108.0f),
                PathNode.QuadTo(1022.0f, 103.0f, 1030.0f, 97.0f),
                PathNode.QuadTo(1047.0f, 82.0f, 1055.0f, 61.0f),
                PathNode.QuadTo(1058.0f, 51.0f, 1059.0f, 36.0f),
                PathNode.QuadTo(1060.0f, 21.0f, 1060.0f, 3.0f),
                PathNode.VerticalTo(-11.0f),
                PathNode.QuadTo(1060.0f, -23.0f, 1056.5f, -26.5f),
                PathNode.QuadTo(1053.0f, -30.0f, 1041.0f, -30.0f),
                PathNode.HorizontalTo(288.0f),
                PathNode.QuadTo(276.0f, -30.0f, 272.5f, -26.5f),
                PathNode.QuadTo(269.0f, -23.0f, 269.0f, -11.0f),
                PathNode.Close,
                PathNode.MoveTo(926.0f, 612.0f),
                PathNode.QuadTo(926.0f, 682.0f, 891.0f, 742.0f),
                PathNode.QuadTo(856.0f, 802.0f, 796.0f, 837.5f),
                PathNode.QuadTo(736.0f, 873.0f, 665.0f, 873.0f),
                PathNode.QuadTo(594.0f, 873.0f, 533.5f, 837.5f),
                PathNode.QuadTo(473.0f, 802.0f, 438.0f, 742.0f),
                PathNode.QuadTo(403.0f, 682.0f, 403.0f, 612.0f),
                PathNode.QuadTo(403.0f, 541.0f, 438.0f, 481.0f),
                PathNode.QuadTo(473.0f, 421.0f, 533.5f, 386.0f),
                PathNode.QuadTo(594.0f, 351.0f, 665.0f, 351.0f),
                PathNode.QuadTo(736.0f, 351.0f, 796.0f, 386.0f),
                PathNode.QuadTo(856.0f, 421.0f, 891.0f, 481.0f),
                PathNode.QuadTo(926.0f, 541.0f, 926.0f, 612.0f),
                PathNode.Close,
                PathNode.MoveTo(514.0f, 612.0f),
                PathNode.QuadTo(514.0f, 652.0f, 534.5f, 686.5f),
                PathNode.QuadTo(555.0f, 721.0f, 589.5f, 741.5f),
                PathNode.QuadTo(624.0f, 762.0f, 665.0f, 762.0f),
                PathNode.QuadTo(705.0f, 762.0f, 739.5f, 741.5f),
                PathNode.QuadTo(774.0f, 721.0f, 794.5f, 686.5f),
                PathNode.QuadTo(815.0f, 652.0f, 815.0f, 612.0f),
                PathNode.QuadTo(815.0f, 571.0f, 794.5f, 536.5f),
                PathNode.QuadTo(774.0f, 502.0f, 739.5f, 481.5f),
                PathNode.QuadTo(705.0f, 461.0f, 665.0f, 461.0f),
                PathNode.QuadTo(624.0f, 461.0f, 589.5f, 481.5f),
                PathNode.QuadTo(555.0f, 502.0f, 534.5f, 536.5f),
                PathNode.QuadTo(514.0f, 571.0f, 514.0f, 612.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _contactsHeavy!!
    }

private var _contactsHeavy: ImageVector? = null
