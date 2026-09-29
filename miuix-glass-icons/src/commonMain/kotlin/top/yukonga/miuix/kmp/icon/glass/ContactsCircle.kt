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

val MiuixIcons.Glass.ContactsCircle: ImageVector
    get() = MiuixIcons.Glass.Regular.ContactsCircle

val MiuixIcons.Glass.Light.ContactsCircle: ImageVector
    get() {
        if (_contactsCircleLight != null) return _contactsCircleLight!!
        _contactsCircleLight = ImageVector.Builder(
            name = "ContactsCircle.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1058.9f, 571.0f),
                        PathNode.QuadTo(1058.9f, 703.8f, 993.4f, 815.7f),
                        PathNode.QuadTo(928.0f, 927.5f, 815.6f, 992.9f),
                        PathNode.QuadTo(703.3f, 1058.4f, 571.4f, 1058.4f),
                        PathNode.QuadTo(439.5f, 1058.4f, 327.2f, 992.9f),
                        PathNode.QuadTo(214.9f, 927.5f, 149.4f, 815.6f),
                        PathNode.QuadTo(84.0f, 703.8f, 84.0f, 570.9f),
                        PathNode.QuadTo(84.0f, 439.0f, 149.4f, 327.2f),
                        PathNode.QuadTo(214.9f, 215.4f, 327.2f, 149.9f),
                        PathNode.QuadTo(439.6f, 84.5f, 571.4f, 84.5f),
                        PathNode.QuadTo(703.3f, 84.5f, 815.6f, 149.9f),
                        PathNode.QuadTo(928.0f, 215.4f, 993.4f, 327.2f),
                        PathNode.QuadTo(1058.9f, 439.1f, 1058.9f, 571.0f),
                        PathNode.Close,
                        PathNode.MoveTo(830.2f, 169.2f),
                        PathNode.QuadTo(861.2f, 186.6f, 888.4f, 210.9f),
                        PathNode.QuadTo(915.7f, 235.3f, 929.9f, 260.5f),
                        PathNode.QuadTo(932.2f, 264.3f, 933.0f, 269.3f),
                        PathNode.QuadTo(933.9f, 274.3f, 932.9f, 276.2f),
                        PathNode.QuadTo(859.2f, 379.9f, 766.4f, 438.7f),
                        PathNode.QuadTo(673.7f, 497.4f, 571.3f, 497.4f),
                        PathNode.QuadTo(468.9f, 497.4f, 375.4f, 438.0f),
                        PathNode.QuadTo(281.9f, 378.6f, 207.8f, 273.2f),
                        PathNode.QuadTo(206.2f, 270.0f, 211.1f, 265.8f),
                        PathNode.QuadTo(216.1f, 261.6f, 216.5f, 259.6f),
                        PathNode.QuadTo(253.5f, 202.3f, 313.5f, 169.2f),
                        PathNode.QuadTo(351.9f, 149.9f, 403.9f, 139.3f),
                        PathNode.QuadTo(456.0f, 128.7f, 533.0f, 128.7f),
                        PathNode.LineTo(609.9f, 128.7f),
                        PathNode.QuadTo(690.0f, 128.7f, 739.8f, 138.9f),
                        PathNode.QuadTo(789.7f, 149.0f, 830.2f, 169.2f),
                        PathNode.Close,
                        PathNode.MoveTo(147.9f, 570.9f),
                        PathNode.QuadTo(147.9f, 685.8f, 205.1f, 782.9f),
                        PathNode.QuadTo(262.3f, 880.0f, 359.4f, 937.3f),
                        PathNode.QuadTo(456.5f, 994.5f, 571.4f, 994.5f),
                        PathNode.QuadTo(686.3f, 994.5f, 783.4f, 937.3f),
                        PathNode.QuadTo(880.5f, 880.0f, 937.8f, 782.9f),
                        PathNode.QuadTo(995.0f, 685.8f, 995.0f, 570.9f),
                        PathNode.QuadTo(995.0f, 456.0f, 937.8f, 358.9f),
                        PathNode.QuadTo(880.7f, 261.8f, 783.7f, 205.1f),
                        PathNode.QuadTo(686.7f, 148.4f, 571.4f, 148.4f),
                        PathNode.QuadTo(456.1f, 148.4f, 359.1f, 205.1f),
                        PathNode.QuadTo(262.1f, 261.8f, 205.0f, 358.9f),
                        PathNode.QuadTo(147.9f, 456.0f, 147.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(723.1f, 713.3f),
                        PathNode.QuadTo(723.1f, 754.5f, 702.8f, 789.4f),
                        PathNode.QuadTo(682.4f, 824.2f, 647.5f, 844.6f),
                        PathNode.QuadTo(612.7f, 864.9f, 571.4f, 864.9f),
                        PathNode.QuadTo(530.2f, 864.9f, 495.3f, 844.6f),
                        PathNode.QuadTo(460.5f, 824.2f, 440.1f, 789.4f),
                        PathNode.QuadTo(419.8f, 754.5f, 419.8f, 713.1f),
                        PathNode.QuadTo(419.8f, 672.6f, 440.1f, 637.4f),
                        PathNode.QuadTo(460.4f, 602.3f, 495.3f, 581.9f),
                        PathNode.QuadTo(530.2f, 561.6f, 571.4f, 561.6f),
                        PathNode.QuadTo(612.7f, 561.6f, 647.5f, 581.9f),
                        PathNode.QuadTo(682.4f, 602.2f, 702.8f, 637.4f),
                        PathNode.QuadTo(723.1f, 672.7f, 723.1f, 713.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsCircleLight!!
    }

private var _contactsCircleLight: ImageVector? = null

val MiuixIcons.Glass.Normal.ContactsCircle: ImageVector
    get() {
        if (_contactsCircleNormal != null) return _contactsCircleNormal!!
        _contactsCircleNormal = ImageVector.Builder(
            name = "ContactsCircle.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1066.8f, 570.9f),
                        PathNode.QuadTo(1066.8f, 706.0f, 1000.3f, 819.6f),
                        PathNode.QuadTo(933.8f, 933.3f, 819.6f, 999.8f),
                        PathNode.QuadTo(705.4f, 1066.3f, 571.4f, 1066.3f),
                        PathNode.QuadTo(437.4f, 1066.3f, 323.2f, 999.8f),
                        PathNode.QuadTo(209.1f, 933.3f, 142.6f, 819.6f),
                        PathNode.QuadTo(76.0f, 705.9f, 76.0f, 570.9f),
                        PathNode.QuadTo(76.0f, 436.9f, 142.6f, 323.2f),
                        PathNode.QuadTo(209.1f, 209.6f, 323.2f, 143.1f),
                        PathNode.QuadTo(437.4f, 76.5f, 571.4f, 76.5f),
                        PathNode.QuadTo(705.5f, 76.5f, 819.6f, 143.1f),
                        PathNode.QuadTo(933.8f, 209.6f, 1000.3f, 323.2f),
                        PathNode.QuadTo(1066.8f, 436.9f, 1066.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(840.0f, 167.6f),
                        PathNode.QuadTo(871.0f, 184.8f, 896.7f, 209.6f),
                        PathNode.QuadTo(922.4f, 234.3f, 937.8f, 263.5f),
                        PathNode.QuadTo(939.2f, 266.1f, 939.2f, 269.7f),
                        PathNode.QuadTo(939.1f, 273.3f, 938.1f, 275.3f),
                        PathNode.QuadTo(863.5f, 382.7f, 769.2f, 442.6f),
                        PathNode.QuadTo(674.9f, 502.6f, 571.4f, 502.6f),
                        PathNode.QuadTo(467.9f, 502.6f, 373.4f, 442.4f),
                        PathNode.QuadTo(278.8f, 382.3f, 204.1f, 274.4f),
                        PathNode.QuadTo(202.2f, 270.7f, 203.8f, 268.0f),
                        PathNode.QuadTo(206.0f, 265.3f, 206.2f, 263.3f),
                        PathNode.QuadTo(240.4f, 201.3f, 303.2f, 167.6f),
                        PathNode.QuadTo(337.8f, 149.9f, 386.1f, 144.2f),
                        PathNode.QuadTo(434.3f, 138.5f, 533.3f, 138.5f),
                        PathNode.LineTo(609.6f, 138.5f),
                        PathNode.QuadTo(709.5f, 138.5f, 757.1f, 144.1f),
                        PathNode.QuadTo(804.6f, 149.7f, 840.0f, 167.6f),
                        PathNode.Close,
                        PathNode.MoveTo(161.3f, 570.9f),
                        PathNode.QuadTo(161.3f, 682.5f, 216.7f, 776.4f),
                        PathNode.QuadTo(272.0f, 870.3f, 366.0f, 925.7f),
                        PathNode.QuadTo(459.9f, 981.0f, 571.4f, 981.0f),
                        PathNode.QuadTo(683.0f, 981.0f, 776.9f, 925.7f),
                        PathNode.QuadTo(870.8f, 870.3f, 926.2f, 776.4f),
                        PathNode.QuadTo(981.5f, 682.5f, 981.5f, 570.9f),
                        PathNode.QuadTo(981.5f, 459.4f, 926.2f, 365.5f),
                        PathNode.QuadTo(870.9f, 271.5f, 777.0f, 216.7f),
                        PathNode.QuadTo(683.1f, 161.8f, 571.4f, 161.8f),
                        PathNode.QuadTo(459.8f, 161.8f, 365.9f, 216.7f),
                        PathNode.QuadTo(272.0f, 271.5f, 216.6f, 365.5f),
                        PathNode.QuadTo(161.3f, 459.4f, 161.3f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(726.8f, 712.3f),
                        PathNode.QuadTo(726.8f, 754.8f, 706.0f, 790.4f),
                        PathNode.QuadTo(685.2f, 826.1f, 649.5f, 846.9f),
                        PathNode.QuadTo(613.9f, 867.7f, 571.4f, 867.7f),
                        PathNode.QuadTo(529.0f, 867.7f, 493.3f, 846.9f),
                        PathNode.QuadTo(457.7f, 826.1f, 436.9f, 790.4f),
                        PathNode.QuadTo(416.1f, 754.8f, 416.1f, 712.3f),
                        PathNode.QuadTo(416.1f, 670.8f, 436.9f, 634.7f),
                        PathNode.QuadTo(457.7f, 598.6f, 493.3f, 577.8f),
                        PathNode.QuadTo(529.0f, 557.0f, 571.4f, 557.0f),
                        PathNode.QuadTo(613.9f, 557.0f, 649.5f, 577.8f),
                        PathNode.QuadTo(685.2f, 598.6f, 706.0f, 634.7f),
                        PathNode.QuadTo(726.8f, 670.8f, 726.8f, 712.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsCircleNormal!!
    }

private var _contactsCircleNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.ContactsCircle: ImageVector
    get() {
        if (_contactsCircleRegular != null) return _contactsCircleRegular!!
        _contactsCircleRegular = ImageVector.Builder(
            name = "ContactsCircle.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1070.4f, 570.9f),
                        PathNode.QuadTo(1070.4f, 706.9f, 1003.4f, 821.4f),
                        PathNode.QuadTo(936.4f, 935.9f, 821.4f, 1002.9f),
                        PathNode.QuadTo(706.4f, 1069.9f, 571.4f, 1069.9f),
                        PathNode.QuadTo(436.4f, 1069.9f, 321.4f, 1002.9f),
                        PathNode.QuadTo(206.4f, 935.9f, 139.4f, 821.4f),
                        PathNode.QuadTo(72.4f, 706.9f, 72.4f, 570.9f),
                        PathNode.QuadTo(72.4f, 435.9f, 139.4f, 321.4f),
                        PathNode.QuadTo(206.4f, 206.9f, 321.4f, 139.9f),
                        PathNode.QuadTo(436.4f, 72.9f, 571.4f, 72.9f),
                        PathNode.QuadTo(706.4f, 72.9f, 821.4f, 139.9f),
                        PathNode.QuadTo(936.4f, 206.9f, 1003.4f, 321.4f),
                        PathNode.QuadTo(1070.4f, 435.9f, 1070.4f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(844.4f, 166.9f),
                        PathNode.QuadTo(875.4f, 183.9f, 900.4f, 208.9f),
                        PathNode.QuadTo(925.4f, 233.9f, 941.4f, 264.9f),
                        PathNode.QuadTo(942.4f, 266.9f, 941.9f, 269.9f),
                        PathNode.QuadTo(941.4f, 272.9f, 940.4f, 274.9f),
                        PathNode.QuadTo(865.4f, 383.9f, 770.4f, 444.4f),
                        PathNode.QuadTo(675.4f, 504.9f, 571.4f, 504.9f),
                        PathNode.QuadTo(467.4f, 504.9f, 372.4f, 444.4f),
                        PathNode.QuadTo(277.4f, 383.9f, 202.4f, 274.9f),
                        PathNode.QuadTo(200.4f, 270.9f, 200.4f, 268.9f),
                        PathNode.QuadTo(201.4f, 266.9f, 201.4f, 264.9f),
                        PathNode.QuadTo(234.4f, 200.9f, 298.4f, 166.9f),
                        PathNode.QuadTo(331.4f, 149.9f, 377.9f, 146.4f),
                        PathNode.QuadTo(424.4f, 142.9f, 533.4f, 142.9f),
                        PathNode.LineTo(609.4f, 142.9f),
                        PathNode.QuadTo(718.4f, 142.9f, 764.9f, 146.4f),
                        PathNode.QuadTo(811.4f, 149.9f, 844.4f, 166.9f),
                        PathNode.Close,
                        PathNode.MoveTo(167.4f, 570.9f),
                        PathNode.QuadTo(167.4f, 680.9f, 221.9f, 773.4f),
                        PathNode.QuadTo(276.4f, 865.9f, 368.9f, 920.4f),
                        PathNode.QuadTo(461.4f, 974.9f, 571.4f, 974.9f),
                        PathNode.QuadTo(681.4f, 974.9f, 773.9f, 920.4f),
                        PathNode.QuadTo(866.4f, 865.9f, 920.9f, 773.4f),
                        PathNode.QuadTo(975.4f, 680.9f, 975.4f, 570.9f),
                        PathNode.QuadTo(975.4f, 460.9f, 920.9f, 368.4f),
                        PathNode.QuadTo(866.4f, 275.9f, 773.9f, 221.9f),
                        PathNode.QuadTo(681.4f, 167.9f, 571.4f, 167.9f),
                        PathNode.QuadTo(461.4f, 167.9f, 368.9f, 221.9f),
                        PathNode.QuadTo(276.4f, 275.9f, 221.9f, 368.4f),
                        PathNode.QuadTo(167.4f, 460.9f, 167.4f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(728.4f, 711.9f),
                        PathNode.QuadTo(728.4f, 754.9f, 707.4f, 790.9f),
                        PathNode.QuadTo(686.4f, 826.9f, 650.4f, 847.9f),
                        PathNode.QuadTo(614.4f, 868.9f, 571.4f, 868.9f),
                        PathNode.QuadTo(528.4f, 868.9f, 492.4f, 847.9f),
                        PathNode.QuadTo(456.4f, 826.9f, 435.4f, 790.9f),
                        PathNode.QuadTo(414.4f, 754.9f, 414.4f, 711.9f),
                        PathNode.QuadTo(414.4f, 669.9f, 435.4f, 633.4f),
                        PathNode.QuadTo(456.4f, 596.9f, 492.4f, 575.9f),
                        PathNode.QuadTo(528.4f, 554.9f, 571.4f, 554.9f),
                        PathNode.QuadTo(614.4f, 554.9f, 650.4f, 575.9f),
                        PathNode.QuadTo(686.4f, 596.9f, 707.4f, 633.4f),
                        PathNode.QuadTo(728.4f, 669.9f, 728.4f, 711.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsCircleRegular!!
    }

private var _contactsCircleRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.ContactsCircle: ImageVector
    get() {
        if (_contactsCircleMedium != null) return _contactsCircleMedium!!
        _contactsCircleMedium = ImageVector.Builder(
            name = "ContactsCircle.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1077.2f, 570.9f),
                        PathNode.QuadTo(1077.2f, 708.6f, 1009.3f, 824.7f),
                        PathNode.QuadTo(941.4f, 940.8f, 824.8f, 1008.7f),
                        PathNode.QuadTo(708.2f, 1076.7f, 571.4f, 1076.7f),
                        PathNode.QuadTo(434.5f, 1076.7f, 318.0f, 1008.7f),
                        PathNode.QuadTo(201.5f, 940.8f, 133.6f, 824.7f),
                        PathNode.QuadTo(65.7f, 708.6f, 65.7f, 570.9f),
                        PathNode.QuadTo(65.7f, 434.1f, 133.6f, 318.0f),
                        PathNode.QuadTo(201.5f, 202.0f, 318.1f, 134.1f),
                        PathNode.QuadTo(434.6f, 66.2f, 571.5f, 66.2f),
                        PathNode.QuadTo(708.3f, 66.2f, 824.9f, 134.1f),
                        PathNode.QuadTo(941.4f, 202.0f, 1009.3f, 318.0f),
                        PathNode.QuadTo(1077.2f, 434.1f, 1077.2f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(844.7f, 165.6f),
                        PathNode.QuadTo(876.0f, 182.6f, 901.4f, 208.0f),
                        PathNode.QuadTo(926.8f, 233.4f, 943.1f, 264.9f),
                        PathNode.QuadTo(944.3f, 268.0f, 944.0f, 272.0f),
                        PathNode.QuadTo(943.6f, 275.9f, 941.8f, 278.7f),
                        PathNode.QuadTo(866.5f, 387.7f, 771.2f, 448.5f),
                        PathNode.QuadTo(676.0f, 509.3f, 571.4f, 509.3f),
                        PathNode.QuadTo(466.9f, 509.3f, 371.6f, 448.5f),
                        PathNode.QuadTo(276.3f, 387.7f, 201.1f, 278.7f),
                        PathNode.QuadTo(198.5f, 274.4f, 198.5f, 270.6f),
                        PathNode.QuadTo(199.5f, 267.7f, 199.8f, 264.9f),
                        PathNode.QuadTo(233.3f, 200.0f, 298.2f, 165.6f),
                        PathNode.QuadTo(331.4f, 148.6f, 378.2f, 144.9f),
                        PathNode.QuadTo(425.0f, 141.3f, 533.6f, 141.3f),
                        PathNode.LineTo(609.3f, 141.3f),
                        PathNode.QuadTo(717.9f, 141.3f, 764.7f, 144.9f),
                        PathNode.QuadTo(811.4f, 148.6f, 844.7f, 165.6f),
                        PathNode.Close,
                        PathNode.MoveTo(178.8f, 570.9f),
                        PathNode.QuadTo(178.8f, 677.7f, 231.7f, 767.8f),
                        PathNode.QuadTo(284.5f, 857.8f, 374.6f, 910.7f),
                        PathNode.QuadTo(464.7f, 963.6f, 571.4f, 963.6f),
                        PathNode.QuadTo(678.2f, 963.6f, 768.3f, 910.7f),
                        PathNode.QuadTo(858.3f, 857.8f, 911.2f, 767.8f),
                        PathNode.QuadTo(964.1f, 677.7f, 964.1f, 570.9f),
                        PathNode.QuadTo(964.1f, 464.2f, 911.2f, 374.2f),
                        PathNode.QuadTo(858.3f, 284.3f, 768.3f, 231.8f),
                        PathNode.QuadTo(678.2f, 179.3f, 571.4f, 179.3f),
                        PathNode.QuadTo(464.7f, 179.3f, 374.6f, 231.8f),
                        PathNode.QuadTo(284.5f, 284.3f, 231.7f, 374.2f),
                        PathNode.QuadTo(178.8f, 464.1f, 178.8f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(731.7f, 711.1f),
                        PathNode.QuadTo(731.7f, 755.0f, 710.1f, 791.8f),
                        PathNode.QuadTo(688.6f, 828.6f, 651.9f, 850.0f),
                        PathNode.QuadTo(615.2f, 871.4f, 571.4f, 871.4f),
                        PathNode.QuadTo(527.6f, 871.4f, 490.9f, 850.0f),
                        PathNode.QuadTo(454.3f, 828.6f, 432.7f, 791.8f),
                        PathNode.QuadTo(411.2f, 755.1f, 411.2f, 711.2f),
                        PathNode.QuadTo(411.2f, 668.3f, 432.7f, 631.3f),
                        PathNode.QuadTo(454.3f, 594.2f, 490.9f, 572.7f),
                        PathNode.QuadTo(527.6f, 551.1f, 571.4f, 551.1f),
                        PathNode.QuadTo(615.2f, 551.1f, 651.9f, 572.7f),
                        PathNode.QuadTo(688.6f, 594.2f, 710.1f, 631.3f),
                        PathNode.QuadTo(731.7f, 668.4f, 731.7f, 711.1f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsCircleMedium!!
    }

private var _contactsCircleMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.ContactsCircle: ImageVector
    get() {
        if (_contactsCircleDemibold != null) return _contactsCircleDemibold!!
        _contactsCircleDemibold = ImageVector.Builder(
            name = "ContactsCircle.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1081.9f, 570.9f),
                        PathNode.QuadTo(1081.9f, 709.7f, 1013.4f, 826.9f),
                        PathNode.QuadTo(944.8f, 944.2f, 827.1f, 1012.8f),
                        PathNode.QuadTo(709.5f, 1081.4f, 571.3f, 1081.4f),
                        PathNode.QuadTo(433.2f, 1081.4f, 315.6f, 1012.8f),
                        PathNode.QuadTo(198.0f, 944.2f, 129.5f, 826.9f),
                        PathNode.QuadTo(60.9f, 709.7f, 60.9f, 570.9f),
                        PathNode.QuadTo(60.9f, 432.8f, 129.5f, 315.7f),
                        PathNode.QuadTo(198.0f, 198.5f, 315.7f, 130.0f),
                        PathNode.QuadTo(433.4f, 61.4f, 571.5f, 61.4f),
                        PathNode.QuadTo(709.6f, 61.4f, 827.3f, 130.0f),
                        PathNode.QuadTo(944.9f, 198.5f, 1013.4f, 315.7f),
                        PathNode.QuadTo(1081.9f, 432.8f, 1081.9f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(844.9f, 164.6f),
                        PathNode.QuadTo(876.3f, 181.6f, 902.0f, 207.3f),
                        PathNode.QuadTo(927.7f, 233.0f, 944.2f, 264.9f),
                        PathNode.QuadTo(945.6f, 268.8f, 945.4f, 273.4f),
                        PathNode.QuadTo(945.1f, 278.0f, 942.7f, 281.4f),
                        PathNode.QuadTo(867.3f, 390.4f, 771.8f, 451.3f),
                        PathNode.QuadTo(676.3f, 512.3f, 571.4f, 512.3f),
                        PathNode.QuadTo(466.5f, 512.3f, 371.1f, 451.3f),
                        PathNode.QuadTo(275.6f, 390.4f, 200.1f, 281.4f),
                        PathNode.QuadTo(197.2f, 276.9f, 197.2f, 271.7f),
                        PathNode.QuadTo(198.2f, 268.3f, 198.7f, 264.9f),
                        PathNode.QuadTo(232.5f, 199.4f, 298.0f, 164.6f),
                        PathNode.QuadTo(331.4f, 147.6f, 378.4f, 143.9f),
                        PathNode.QuadTo(425.3f, 140.2f, 533.7f, 140.2f),
                        PathNode.LineTo(609.2f, 140.2f),
                        PathNode.QuadTo(717.5f, 140.2f, 764.5f, 143.9f),
                        PathNode.QuadTo(811.4f, 147.6f, 844.9f, 164.6f),
                        PathNode.Close,
                        PathNode.MoveTo(186.7f, 570.9f),
                        PathNode.QuadTo(186.7f, 675.4f, 238.5f, 763.8f),
                        PathNode.QuadTo(290.2f, 852.1f, 378.6f, 903.9f),
                        PathNode.QuadTo(466.9f, 955.6f, 571.4f, 955.6f),
                        PathNode.QuadTo(675.9f, 955.6f, 764.3f, 903.9f),
                        PathNode.QuadTo(852.6f, 852.1f, 904.4f, 763.8f),
                        PathNode.QuadTo(956.1f, 675.4f, 956.1f, 570.9f),
                        PathNode.QuadTo(956.1f, 466.4f, 904.4f, 378.3f),
                        PathNode.QuadTo(852.6f, 290.2f, 764.3f, 238.7f),
                        PathNode.QuadTo(675.9f, 187.2f, 571.4f, 187.2f),
                        PathNode.QuadTo(466.9f, 187.2f, 378.6f, 238.7f),
                        PathNode.QuadTo(290.2f, 290.2f, 238.5f, 378.2f),
                        PathNode.QuadTo(186.7f, 466.2f, 186.7f, 570.9f),
                        PathNode.Close,
                        PathNode.MoveTo(733.9f, 710.6f),
                        PathNode.QuadTo(733.9f, 755.1f, 712.0f, 792.4f),
                        PathNode.QuadTo(690.1f, 829.7f, 653.0f, 851.4f),
                        PathNode.QuadTo(615.8f, 873.1f, 571.4f, 873.1f),
                        PathNode.QuadTo(527.1f, 873.1f, 489.9f, 851.4f),
                        PathNode.QuadTo(452.8f, 829.7f, 430.8f, 792.4f),
                        PathNode.QuadTo(408.9f, 755.2f, 408.9f, 710.6f),
                        PathNode.QuadTo(408.9f, 667.2f, 430.8f, 629.8f),
                        PathNode.QuadTo(452.8f, 592.3f, 489.9f, 570.4f),
                        PathNode.QuadTo(527.1f, 548.5f, 571.4f, 548.5f),
                        PathNode.QuadTo(615.8f, 548.5f, 653.0f, 570.4f),
                        PathNode.QuadTo(690.1f, 592.3f, 712.0f, 629.9f),
                        PathNode.QuadTo(733.9f, 667.4f, 733.9f, 710.6f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _contactsCircleDemibold!!
    }

private var _contactsCircleDemibold: ImageVector? = null
