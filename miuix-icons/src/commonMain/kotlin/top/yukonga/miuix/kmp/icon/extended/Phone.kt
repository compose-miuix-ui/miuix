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

val MiuixIcons.Phone: ImageVector
    get() = MiuixIcons.Regular.Phone

val MiuixIcons.Light.Phone: ImageVector
    get() {
        if (_phoneLight != null) return _phoneLight!!
        _phoneLight = ImageVector.Builder(
            name = "Phone.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1139.2519273371734f,
            viewportHeight = 1139.2519273371734f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -95.22627381479839f, translationY = 962.3186487711737f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(466.0f, 194.0f),
                PathNode.QuadTo(378.0f, 282.0f, 308.5f, 389.5f),
                PathNode.QuadTo(239.0f, 497.0f, 202.0f, 609.0f),
                PathNode.QuadTo(183.0f, 664.0f, 194.5f, 723.5f),
                PathNode.QuadTo(206.0f, 783.0f, 254.0f, 815.0f),
                PathNode.QuadTo(299.0f, 846.0f, 328.0f, 861.0f),
                PathNode.QuadTo(350.0f, 872.0f, 373.0f, 864.0f),
                PathNode.QuadTo(396.0f, 856.0f, 408.0f, 834.0f),
                PathNode.LineTo(512.0f, 655.0f),
                PathNode.QuadTo(520.0f, 640.0f, 519.0f, 624.0f),
                PathNode.QuadTo(518.0f, 608.0f, 511.0f, 594.0f),
                PathNode.LineTo(476.0f, 528.0f),
                PathNode.QuadTo(461.0f, 500.0f, 462.0f, 469.0f),
                PathNode.QuadTo(463.0f, 438.0f, 479.0f, 412.0f),
                PathNode.QuadTo(528.0f, 336.0f, 569.0f, 296.0f),
                PathNode.QuadTo(612.0f, 252.0f, 685.0f, 206.0f),
                PathNode.QuadTo(710.0f, 190.0f, 741.5f, 189.0f),
                PathNode.QuadTo(773.0f, 188.0f, 801.0f, 203.0f),
                PathNode.LineTo(867.0f, 239.0f),
                PathNode.QuadTo(881.0f, 246.0f, 897.0f, 246.5f),
                PathNode.QuadTo(913.0f, 247.0f, 928.0f, 239.0f),
                PathNode.LineTo(1107.0f, 135.0f),
                PathNode.QuadTo(1129.0f, 122.0f, 1136.5f, 99.0f),
                PathNode.QuadTo(1144.0f, 76.0f, 1133.0f, 55.0f),
                PathNode.QuadTo(1119.0f, 23.0f, 1089.0f, -19.0f),
                PathNode.QuadTo(1055.0f, -66.0f, 996.0f, -77.5f),
                PathNode.QuadTo(937.0f, -89.0f, 882.0f, -71.0f),
                PathNode.QuadTo(769.0f, -34.0f, 662.0f, 35.5f),
                PathNode.QuadTo(555.0f, 105.0f, 466.0f, 194.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _phoneLight!!
    }

private var _phoneLight: ImageVector? = null

val MiuixIcons.Regular.Phone: ImageVector
    get() {
        if (_phoneRegular != null) return _phoneRegular!!
        _phoneRegular = ImageVector.Builder(
            name = "Phone.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1162.3523809523808f,
            viewportHeight = 1162.3523809523808f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -83.600195928103f, translationY = 974.0293650793651f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(458.0f, 186.0f),
                PathNode.QuadTo(371.0f, 273.0f, 301.0f, 381.5f),
                PathNode.QuadTo(231.0f, 490.0f, 193.0f, 603.0f),
                PathNode.QuadTo(173.0f, 661.0f, 185.5f, 724.5f),
                PathNode.QuadTo(198.0f, 788.0f, 250.0f, 823.0f),
                PathNode.QuadTo(296.0f, 855.0f, 325.0f, 869.0f),
                PathNode.QuadTo(352.0f, 883.0f, 379.5f, 873.0f),
                PathNode.QuadTo(407.0f, 863.0f, 422.0f, 837.0f),
                PathNode.LineTo(525.0f, 659.0f),
                PathNode.QuadTo(535.0f, 641.0f, 534.0f, 621.5f),
                PathNode.QuadTo(533.0f, 602.0f, 524.0f, 586.0f),
                PathNode.LineTo(489.0f, 520.0f),
                PathNode.QuadTo(476.0f, 496.0f, 477.0f, 469.0f),
                PathNode.QuadTo(478.0f, 442.0f, 492.0f, 419.0f),
                PathNode.QuadTo(537.0f, 348.0f, 579.0f, 306.0f),
                PathNode.QuadTo(624.0f, 261.0f, 692.0f, 219.0f),
                PathNode.QuadTo(714.0f, 205.0f, 741.5f, 204.0f),
                PathNode.QuadTo(769.0f, 203.0f, 793.0f, 216.0f),
                PathNode.LineTo(859.0f, 252.0f),
                PathNode.QuadTo(875.0f, 260.0f, 895.0f, 261.0f),
                PathNode.QuadTo(915.0f, 262.0f, 932.0f, 252.0f),
                PathNode.LineTo(1110.0f, 149.0f),
                PathNode.QuadTo(1137.0f, 133.0f, 1145.5f, 105.5f),
                PathNode.QuadTo(1154.0f, 78.0f, 1141.0f, 52.0f),
                PathNode.QuadTo(1127.0f, 21.0f, 1096.0f, -23.0f),
                PathNode.QuadTo(1060.0f, -74.0f, 997.0f, -86.5f),
                PathNode.QuadTo(934.0f, -99.0f, 876.0f, -80.0f),
                PathNode.QuadTo(762.0f, -42.0f, 654.0f, 28.0f),
                PathNode.QuadTo(546.0f, 98.0f, 458.0f, 186.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _phoneRegular!!
    }

private var _phoneRegular: ImageVector? = null

val MiuixIcons.Heavy.Phone: ImageVector
    get() {
        if (_phoneHeavy != null) return _phoneHeavy!!
        _phoneHeavy = ImageVector.Builder(
            name = "Phone.Heavy",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1194.5315832649715f,
            viewportHeight = 1194.5315832649715f
        ).apply {
            group(scaleX = 1.0f, scaleY = -1.0f, translationX = -67.67309269893354f, translationY = 987.0379841458547f) {
            addPath(
                pathData = listOf(
                PathNode.MoveTo(447.0f, 173.0f),
                PathNode.QuadTo(360.0f, 260.0f, 289.0f, 369.0f),
                PathNode.QuadTo(218.0f, 478.0f, 180.0f, 592.0f),
                PathNode.QuadTo(159.0f, 655.0f, 172.5f, 723.5f),
                PathNode.QuadTo(186.0f, 792.0f, 244.0f, 831.0f),
                PathNode.QuadTo(296.0f, 867.0f, 320.0f, 878.0f),
                PathNode.QuadTo(353.0f, 894.0f, 387.5f, 882.5f),
                PathNode.QuadTo(422.0f, 871.0f, 440.0f, 839.0f),
                PathNode.LineTo(542.0f, 663.0f),
                PathNode.QuadTo(554.0f, 642.0f, 553.5f, 618.0f),
                PathNode.QuadTo(553.0f, 594.0f, 541.0f, 573.0f),
                PathNode.LineTo(506.0f, 508.0f),
                PathNode.QuadTo(496.0f, 488.0f, 496.5f, 466.0f),
                PathNode.QuadTo(497.0f, 444.0f, 509.0f, 425.0f),
                PathNode.QuadTo(548.0f, 363.0f, 592.0f, 317.0f),
                PathNode.QuadTo(638.0f, 271.0f, 701.0f, 233.0f),
                PathNode.QuadTo(720.0f, 222.0f, 742.0f, 221.5f),
                PathNode.QuadTo(764.0f, 221.0f, 783.0f, 231.0f),
                PathNode.LineTo(849.0f, 266.0f),
                PathNode.QuadTo(870.0f, 277.0f, 894.0f, 278.0f),
                PathNode.QuadTo(918.0f, 279.0f, 938.0f, 266.0f),
                PathNode.LineTo(1115.0f, 164.0f),
                PathNode.QuadTo(1148.0f, 145.0f, 1158.5f, 110.5f),
                PathNode.QuadTo(1169.0f, 76.0f, 1153.0f, 45.0f),
                PathNode.QuadTo(1136.0f, 8.0f, 1107.0f, -32.0f),
                PathNode.QuadTo(1067.0f, -88.0f, 999.0f, -102.0f),
                PathNode.QuadTo(931.0f, -116.0f, 868.0f, -96.0f),
                PathNode.QuadTo(753.0f, -57.0f, 644.0f, 13.5f),
                PathNode.QuadTo(535.0f, 84.0f, 447.0f, 173.0f),
                PathNode.Close,
            ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero
            )
                    }
        }.build()
        return _phoneHeavy!!
    }

private var _phoneHeavy: ImageVector? = null
