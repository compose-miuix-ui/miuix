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

val MiuixIcons.Glass.Notifications: ImageVector
    get() = MiuixIcons.Glass.Regular.Notifications

val MiuixIcons.Glass.Light.Notifications: ImageVector
    get() {
        if (_notificationsLight != null) return _notificationsLight!!
        _notificationsLight = ImageVector.Builder(
            name = "Notifications.Glass.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(990.6f, 265.0f),
                        PathNode.QuadTo(997.9f, 270.8f, 1001.8f, 279.3f),
                        PathNode.QuadTo(1005.8f, 287.7f, 1005.2f, 296.6f),
                        PathNode.QuadTo(1004.7f, 305.9f, 993.2f, 320.5f),
                        PathNode.QuadTo(981.7f, 335.0f, 957.8f, 360.9f),
                        PathNode.LineTo(943.8f, 376.5f),
                        PathNode.QuadTo(905.5f, 416.8f, 883.3f, 468.8f),
                        PathNode.QuadTo(861.2f, 520.7f, 858.2f, 576.5f),
                        PathNode.LineTo(845.2f, 797.4f),
                        PathNode.QuadTo(841.2f, 868.8f, 803.2f, 927.9f),
                        PathNode.QuadTo(765.2f, 987.0f, 704.1f, 1021.5f),
                        PathNode.QuadTo(643.0f, 1055.9f, 571.6f, 1055.9f),
                        PathNode.QuadTo(500.1f, 1055.9f, 438.5f, 1021.5f),
                        PathNode.QuadTo(376.9f, 987.0f, 339.0f, 927.4f),
                        PathNode.QuadTo(301.0f, 867.8f, 296.5f, 796.4f),
                        PathNode.LineTo(282.5f, 566.5f),
                        PathNode.QuadTo(279.5f, 515.3f, 257.3f, 465.5f),
                        PathNode.QuadTo(235.1f, 415.8f, 198.8f, 376.5f),
                        PathNode.LineTo(164.6f, 339.4f),
                        PathNode.QuadTo(151.0f, 324.7f, 144.7f, 314.4f),
                        PathNode.QuadTo(138.4f, 304.0f, 137.8f, 296.2f),
                        PathNode.QuadTo(136.7f, 287.7f, 140.6f, 279.3f),
                        PathNode.QuadTo(144.6f, 270.8f, 152.5f, 265.0f),
                        PathNode.QuadTo(160.2f, 259.8f, 172.2f, 258.0f),
                        PathNode.QuadTo(184.1f, 256.3f, 209.6f, 255.7f),
                        PathNode.LineTo(937.0f, 255.7f),
                        PathNode.QuadTo(960.2f, 256.3f, 972.0f, 258.0f),
                        PathNode.QuadTo(983.8f, 259.8f, 990.6f, 265.0f),
                        PathNode.Close,
                        PathNode.MoveTo(236.1f, 330.5f),
                        PathNode.QuadTo(290.5f, 390.4f, 314.6f, 450.2f),
                        PathNode.QuadTo(338.8f, 510.0f, 342.2f, 572.3f),
                        PathNode.LineTo(355.2f, 794.2f),
                        PathNode.QuadTo(358.2f, 849.8f, 388.2f, 896.2f),
                        PathNode.QuadTo(418.2f, 942.6f, 466.6f, 970.1f),
                        PathNode.QuadTo(515.0f, 997.6f, 571.6f, 997.6f),
                        PathNode.QuadTo(627.7f, 997.6f, 675.8f, 970.3f),
                        PathNode.QuadTo(723.9f, 943.0f, 753.7f, 896.4f),
                        PathNode.QuadTo(783.5f, 849.8f, 785.9f, 794.2f),
                        PathNode.LineTo(798.9f, 573.3f),
                        PathNode.QuadTo(802.5f, 510.5f, 826.8f, 451.2f),
                        PathNode.QuadTo(851.1f, 391.9f, 905.6f, 330.8f),
                        PathNode.QuadTo(912.6f, 323.2f, 910.3f, 318.6f),
                        PathNode.QuadTo(908.0f, 314.0f, 898.0f, 314.0f),
                        PathNode.LineTo(242.5f, 314.0f),
                        PathNode.QuadTo(232.7f, 314.0f, 230.8f, 318.4f),
                        PathNode.QuadTo(229.0f, 322.7f, 236.1f, 330.5f),
                        PathNode.Close,
                        PathNode.MoveTo(701.8f, 168.5f),
                        PathNode.QuadTo(705.0f, 175.3f, 701.8f, 179.8f),
                        PathNode.QuadTo(698.6f, 184.3f, 690.7f, 184.3f),
                        PathNode.LineTo(450.3f, 184.3f),
                        PathNode.QuadTo(443.0f, 184.3f, 440.1f, 179.5f),
                        PathNode.QuadTo(437.1f, 174.8f, 440.8f, 168.0f),
                        PathNode.QuadTo(460.9f, 129.3f, 495.3f, 108.1f),
                        PathNode.QuadTo(529.8f, 86.9f, 571.6f, 86.9f),
                        PathNode.QuadTo(612.8f, 86.9f, 647.5f, 108.6f),
                        PathNode.QuadTo(682.2f, 130.3f, 701.8f, 168.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notificationsLight!!
    }

private var _notificationsLight: ImageVector? = null

val MiuixIcons.Glass.Normal.Notifications: ImageVector
    get() {
        if (_notificationsNormal != null) return _notificationsNormal!!
        _notificationsNormal = ImageVector.Builder(
            name = "Notifications.Glass.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(996.4f, 264.3f),
                        PathNode.QuadTo(1005.6f, 271.6f, 1010.6f, 282.5f),
                        PathNode.QuadTo(1015.6f, 293.4f, 1014.8f, 304.4f),
                        PathNode.QuadTo(1013.9f, 315.6f, 1002.4f, 330.4f),
                        PathNode.QuadTo(990.9f, 345.3f, 964.9f, 373.3f),
                        PathNode.LineTo(950.9f, 389.2f),
                        PathNode.QuadTo(913.5f, 428.6f, 892.1f, 478.8f),
                        PathNode.QuadTo(870.8f, 529.1f, 867.8f, 583.7f),
                        PathNode.LineTo(854.8f, 798.4f),
                        PathNode.QuadTo(850.8f, 872.3f, 811.4f, 933.4f),
                        PathNode.QuadTo(772.0f, 994.5f, 708.9f, 1030.0f),
                        PathNode.QuadTo(645.8f, 1065.6f, 571.9f, 1065.6f),
                        PathNode.QuadTo(498.1f, 1065.6f, 434.5f, 1030.0f),
                        PathNode.QuadTo(370.9f, 994.5f, 331.9f, 932.9f),
                        PathNode.QuadTo(292.8f, 871.3f, 288.0f, 797.4f),
                        PathNode.LineTo(274.0f, 573.7f),
                        PathNode.QuadTo(271.0f, 524.0f, 249.3f, 475.8f),
                        PathNode.QuadTo(227.6f, 427.6f, 192.1f, 389.2f),
                        PathNode.LineTo(162.2f, 356.9f),
                        PathNode.QuadTo(145.6f, 338.6f, 137.4f, 326.4f),
                        PathNode.QuadTo(129.3f, 314.3f, 128.4f, 304.2f),
                        PathNode.QuadTo(126.7f, 293.4f, 131.7f, 282.5f),
                        PathNode.QuadTo(136.7f, 271.6f, 146.7f, 264.3f),
                        PathNode.QuadTo(156.0f, 257.8f, 169.0f, 255.9f),
                        PathNode.QuadTo(182.1f, 254.0f, 209.9f, 253.1f),
                        PathNode.LineTo(934.3f, 253.1f),
                        PathNode.QuadTo(961.5f, 254.0f, 974.8f, 255.9f),
                        PathNode.QuadTo(988.1f, 257.8f, 996.4f, 264.3f),
                        PathNode.Close,
                        PathNode.MoveTo(258.8f, 348.7f),
                        PathNode.QuadTo(304.0f, 402.4f, 326.3f, 460.0f),
                        PathNode.QuadTo(348.6f, 517.5f, 351.8f, 578.2f),
                        PathNode.LineTo(364.8f, 794.0f),
                        PathNode.QuadTo(367.8f, 847.8f, 396.4f, 892.2f),
                        PathNode.QuadTo(425.0f, 936.6f, 471.4f, 962.8f),
                        PathNode.QuadTo(517.8f, 988.9f, 571.9f, 988.9f),
                        PathNode.QuadTo(625.9f, 988.9f, 671.9f, 962.8f),
                        PathNode.QuadTo(717.9f, 936.8f, 746.4f, 892.3f),
                        PathNode.QuadTo(775.0f, 847.8f, 777.1f, 794.0f),
                        PathNode.LineTo(790.1f, 579.2f),
                        PathNode.QuadTo(794.0f, 517.7f, 816.0f, 461.0f),
                        PathNode.QuadTo(838.1f, 404.3f, 883.9f, 348.1f),
                        PathNode.QuadTo(890.9f, 339.5f, 888.5f, 334.6f),
                        PathNode.QuadTo(886.1f, 329.8f, 876.1f, 329.8f),
                        PathNode.LineTo(267.0f, 329.8f),
                        PathNode.QuadTo(255.7f, 329.8f, 253.4f, 334.6f),
                        PathNode.QuadTo(251.1f, 339.4f, 258.8f, 348.7f),
                        PathNode.Close,
                        PathNode.MoveTo(706.1f, 161.9f),
                        PathNode.QuadTo(710.6f, 170.2f, 706.1f, 176.1f),
                        PathNode.QuadTo(701.7f, 182.0f, 691.7f, 182.0f),
                        PathNode.LineTo(449.5f, 182.0f),
                        PathNode.QuadTo(440.3f, 182.0f, 436.3f, 176.0f),
                        PathNode.QuadTo(432.3f, 170.1f, 436.9f, 161.8f),
                        PathNode.QuadTo(457.6f, 121.5f, 493.1f, 99.4f),
                        PathNode.QuadTo(528.6f, 77.3f, 571.9f, 77.3f),
                        PathNode.QuadTo(614.4f, 77.3f, 650.3f, 99.9f),
                        PathNode.QuadTo(686.3f, 122.5f, 706.1f, 161.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notificationsNormal!!
    }

private var _notificationsNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.Notifications: ImageVector
    get() {
        if (_notificationsRegular != null) return _notificationsRegular!!
        _notificationsRegular = ImageVector.Builder(
            name = "Notifications.Glass.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(999.1f, 263.9f),
                        PathNode.QuadTo(1009.1f, 271.9f, 1014.6f, 283.9f),
                        PathNode.QuadTo(1020.1f, 295.9f, 1019.1f, 307.9f),
                        PathNode.QuadTo(1018.1f, 319.9f, 1006.6f, 334.9f),
                        PathNode.QuadTo(995.1f, 349.9f, 968.1f, 378.9f),
                        PathNode.LineTo(954.1f, 394.9f),
                        PathNode.QuadTo(917.1f, 433.9f, 896.1f, 483.4f),
                        PathNode.QuadTo(875.1f, 532.9f, 872.1f, 586.9f),
                        PathNode.LineTo(859.1f, 798.9f),
                        PathNode.QuadTo(855.1f, 873.9f, 815.1f, 935.9f),
                        PathNode.QuadTo(775.1f, 997.9f, 711.1f, 1033.9f),
                        PathNode.QuadTo(647.1f, 1069.9f, 572.1f, 1069.9f),
                        PathNode.QuadTo(497.1f, 1069.9f, 432.6f, 1033.9f),
                        PathNode.QuadTo(368.1f, 997.9f, 328.6f, 935.4f),
                        PathNode.QuadTo(289.1f, 872.9f, 284.1f, 797.9f),
                        PathNode.LineTo(270.1f, 576.9f),
                        PathNode.QuadTo(267.1f, 527.9f, 245.6f, 480.4f),
                        PathNode.QuadTo(224.1f, 432.9f, 189.1f, 394.9f),
                        PathNode.LineTo(161.1f, 364.9f),
                        PathNode.QuadTo(143.1f, 344.9f, 134.1f, 331.9f),
                        PathNode.QuadTo(125.1f, 318.9f, 124.1f, 307.9f),
                        PathNode.QuadTo(122.1f, 295.9f, 127.6f, 283.9f),
                        PathNode.QuadTo(133.1f, 271.9f, 144.1f, 263.9f),
                        PathNode.QuadTo(154.1f, 256.9f, 167.6f, 254.9f),
                        PathNode.QuadTo(181.1f, 252.9f, 210.1f, 251.9f),
                        PathNode.LineTo(933.1f, 251.9f),
                        PathNode.QuadTo(962.1f, 252.9f, 976.1f, 254.9f),
                        PathNode.QuadTo(990.1f, 256.9f, 999.1f, 263.9f),
                        PathNode.Close,
                        PathNode.MoveTo(269.1f, 356.9f),
                        PathNode.QuadTo(310.1f, 407.9f, 331.6f, 464.4f),
                        PathNode.QuadTo(353.1f, 520.9f, 356.1f, 580.9f),
                        PathNode.LineTo(369.1f, 793.9f),
                        PathNode.QuadTo(372.1f, 846.9f, 400.1f, 890.4f),
                        PathNode.QuadTo(428.1f, 933.9f, 473.6f, 959.4f),
                        PathNode.QuadTo(519.1f, 984.9f, 572.1f, 984.9f),
                        PathNode.QuadTo(625.1f, 984.9f, 670.1f, 959.4f),
                        PathNode.QuadTo(715.1f, 933.9f, 743.1f, 890.4f),
                        PathNode.QuadTo(771.1f, 846.9f, 773.1f, 793.9f),
                        PathNode.LineTo(786.1f, 581.9f),
                        PathNode.QuadTo(790.1f, 520.9f, 811.1f, 465.4f),
                        PathNode.QuadTo(832.1f, 409.9f, 874.1f, 355.9f),
                        PathNode.QuadTo(881.1f, 346.9f, 878.6f, 341.9f),
                        PathNode.QuadTo(876.1f, 336.9f, 866.1f, 336.9f),
                        PathNode.LineTo(278.1f, 336.9f),
                        PathNode.QuadTo(266.1f, 336.9f, 263.6f, 341.9f),
                        PathNode.QuadTo(261.1f, 346.9f, 269.1f, 356.9f),
                        PathNode.Close,
                        PathNode.MoveTo(708.1f, 158.9f),
                        PathNode.QuadTo(713.1f, 167.9f, 708.1f, 174.4f),
                        PathNode.QuadTo(703.1f, 180.9f, 692.1f, 180.9f),
                        PathNode.LineTo(449.1f, 180.9f),
                        PathNode.QuadTo(439.1f, 180.9f, 434.6f, 174.4f),
                        PathNode.QuadTo(430.1f, 167.9f, 435.1f, 158.9f),
                        PathNode.QuadTo(456.1f, 117.9f, 492.1f, 95.4f),
                        PathNode.QuadTo(528.1f, 72.9f, 572.1f, 72.9f),
                        PathNode.QuadTo(615.1f, 72.9f, 651.6f, 95.9f),
                        PathNode.QuadTo(688.1f, 118.9f, 708.1f, 158.9f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notificationsRegular!!
    }

private var _notificationsRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.Notifications: ImageVector
    get() {
        if (_notificationsMedium != null) return _notificationsMedium!!
        _notificationsMedium = ImageVector.Builder(
            name = "Notifications.Glass.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1003.9f, 268.4f),
                        PathNode.QuadTo(1016.1f, 278.0f, 1022.3f, 291.9f),
                        PathNode.QuadTo(1028.4f, 305.8f, 1027.2f, 319.7f),
                        PathNode.QuadTo(1025.9f, 333.3f, 1014.3f, 349.0f),
                        PathNode.QuadTo(1002.6f, 364.7f, 974.5f, 394.7f),
                        PathNode.LineTo(960.0f, 411.3f),
                        PathNode.QuadTo(923.8f, 449.5f, 903.4f, 497.6f),
                        PathNode.QuadTo(882.9f, 545.8f, 879.9f, 598.4f),
                        PathNode.LineTo(867.2f, 801.0f),
                        PathNode.QuadTo(862.9f, 878.1f, 821.8f, 941.9f),
                        PathNode.QuadTo(780.7f, 1005.6f, 715.0f, 1042.6f),
                        PathNode.QuadTo(649.2f, 1079.5f, 572.1f, 1079.5f),
                        PathNode.QuadTo(494.9f, 1079.5f, 428.8f, 1042.6f),
                        PathNode.QuadTo(362.7f, 1005.6f, 321.9f, 941.4f),
                        PathNode.QuadTo(281.2f, 877.1f, 276.0f, 800.0f),
                        PathNode.LineTo(262.0f, 588.4f),
                        PathNode.QuadTo(258.7f, 540.5f, 237.9f, 494.3f),
                        PathNode.QuadTo(217.0f, 448.2f, 183.1f, 411.3f),
                        PathNode.LineTo(155.1f, 381.3f),
                        PathNode.QuadTo(136.0f, 360.2f, 126.6f, 346.3f),
                        PathNode.QuadTo(117.2f, 332.3f, 116.0f, 320.0f),
                        PathNode.QuadTo(114.0f, 305.8f, 120.3f, 291.8f),
                        PathNode.QuadTo(126.6f, 277.7f, 139.2f, 268.4f),
                        PathNode.QuadTo(150.5f, 260.3f, 165.3f, 258.0f),
                        PathNode.QuadTo(180.0f, 255.8f, 210.1f, 255.0f),
                        PathNode.LineTo(933.1f, 255.0f),
                        PathNode.QuadTo(963.4f, 255.8f, 978.6f, 258.0f),
                        PathNode.QuadTo(993.8f, 260.3f, 1003.9f, 268.4f),
                        PathNode.Close,
                        PathNode.MoveTo(284.5f, 375.7f),
                        PathNode.QuadTo(321.7f, 425.1f, 341.6f, 479.6f),
                        PathNode.QuadTo(361.4f, 534.0f, 364.4f, 591.3f),
                        PathNode.LineTo(377.2f, 794.9f),
                        PathNode.QuadTo(380.2f, 845.7f, 407.1f, 887.6f),
                        PathNode.QuadTo(434.0f, 929.5f, 477.6f, 953.9f),
                        PathNode.QuadTo(521.2f, 978.3f, 572.1f, 978.3f),
                        PathNode.QuadTo(622.9f, 978.3f, 666.0f, 953.9f),
                        PathNode.QuadTo(709.1f, 929.5f, 735.9f, 887.6f),
                        PathNode.QuadTo(762.7f, 845.7f, 765.0f, 794.9f),
                        PathNode.LineTo(777.7f, 592.3f),
                        PathNode.QuadTo(781.7f, 534.0f, 801.1f, 480.4f),
                        PathNode.QuadTo(820.4f, 426.8f, 858.7f, 375.0f),
                        PathNode.QuadTo(865.1f, 366.3f, 862.5f, 361.1f),
                        PathNode.QuadTo(859.8f, 356.0f, 849.8f, 356.0f),
                        PathNode.LineTo(293.7f, 356.0f),
                        PathNode.QuadTo(282.3f, 356.0f, 279.8f, 361.1f),
                        PathNode.QuadTo(277.3f, 366.3f, 284.5f, 375.7f),
                        PathNode.Close,
                        PathNode.MoveTo(712.9f, 151.5f),
                        PathNode.QuadTo(719.3f, 163.2f, 712.7f, 172.3f),
                        PathNode.QuadTo(706.0f, 181.3f, 692.1f, 181.3f),
                        PathNode.LineTo(449.1f, 181.3f),
                        PathNode.QuadTo(436.1f, 181.3f, 430.0f, 172.3f),
                        PathNode.QuadTo(423.8f, 163.2f, 430.2f, 151.5f),
                        PathNode.QuadTo(452.0f, 109.1f, 489.2f, 86.2f),
                        PathNode.QuadTo(526.4f, 63.3f, 572.1f, 63.3f),
                        PathNode.QuadTo(616.7f, 63.3f, 654.4f, 86.7f),
                        PathNode.QuadTo(692.1f, 110.1f, 712.9f, 151.5f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notificationsMedium!!
    }

private var _notificationsMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.Notifications: ImageVector
    get() {
        if (_notificationsDemibold != null) return _notificationsDemibold!!
        _notificationsDemibold = ImageVector.Builder(
            name = "Notifications.Glass.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 1142.9f,
            viewportHeight = 1142.9f,
            autoMirror = false,
        ).apply {
            group(scaleY = -1.0f, translationX = 0.0f, translationY = 1142.9f) {
                addPath(
                    pathData = listOf(
                        PathNode.MoveTo(1007.3f, 271.5f),
                        PathNode.QuadTo(1021.0f, 282.3f, 1027.6f, 297.5f),
                        PathNode.QuadTo(1034.3f, 312.7f, 1032.8f, 327.9f),
                        PathNode.QuadTo(1031.3f, 342.7f, 1019.6f, 358.8f),
                        PathNode.QuadTo(1007.9f, 375.0f, 979.0f, 405.8f),
                        PathNode.LineTo(964.1f, 422.7f),
                        PathNode.QuadTo(928.5f, 460.3f, 908.4f, 507.6f),
                        PathNode.QuadTo(888.3f, 554.8f, 885.3f, 606.5f),
                        PathNode.LineTo(872.8f, 802.4f),
                        PathNode.QuadTo(868.3f, 881.1f, 826.5f, 946.0f),
                        PathNode.QuadTo(784.7f, 1011.0f, 717.7f, 1048.6f),
                        PathNode.QuadTo(650.7f, 1086.2f, 572.0f, 1086.2f),
                        PathNode.QuadTo(493.3f, 1086.2f, 426.1f, 1048.6f),
                        PathNode.QuadTo(358.8f, 1011.0f, 317.3f, 945.5f),
                        PathNode.QuadTo(275.7f, 880.1f, 270.2f, 801.4f),
                        PathNode.LineTo(256.2f, 596.5f),
                        PathNode.QuadTo(252.8f, 549.3f, 232.4f, 504.1f),
                        PathNode.QuadTo(212.1f, 458.9f, 178.9f, 422.7f),
                        PathNode.LineTo(150.9f, 392.7f),
                        PathNode.QuadTo(131.1f, 370.9f, 121.4f, 356.3f),
                        PathNode.QuadTo(111.7f, 341.7f, 110.2f, 328.4f),
                        PathNode.QuadTo(108.2f, 312.7f, 115.1f, 297.3f),
                        PathNode.QuadTo(122.0f, 281.8f, 135.8f, 271.5f),
                        PathNode.QuadTo(148.0f, 262.7f, 163.6f, 260.2f),
                        PathNode.QuadTo(179.2f, 257.8f, 210.0f, 257.2f),
                        PathNode.LineTo(933.0f, 257.2f),
                        PathNode.QuadTo(964.3f, 257.8f, 980.4f, 260.2f),
                        PathNode.QuadTo(996.5f, 262.7f, 1007.3f, 271.5f),
                        PathNode.Close,
                        PathNode.MoveTo(295.2f, 388.9f),
                        PathNode.QuadTo(329.8f, 437.1f, 348.5f, 490.2f),
                        PathNode.QuadTo(367.3f, 543.2f, 370.3f, 598.6f),
                        PathNode.LineTo(382.8f, 795.5f),
                        PathNode.QuadTo(385.8f, 844.9f, 412.0f, 885.6f),
                        PathNode.QuadTo(438.1f, 926.3f, 480.4f, 950.0f),
                        PathNode.QuadTo(522.7f, 973.7f, 572.0f, 973.7f),
                        PathNode.QuadTo(621.3f, 973.7f, 663.1f, 950.0f),
                        PathNode.QuadTo(704.9f, 926.3f, 730.8f, 885.6f),
                        PathNode.QuadTo(756.8f, 844.9f, 759.2f, 795.5f),
                        PathNode.LineTo(771.8f, 599.6f),
                        PathNode.QuadTo(775.8f, 543.2f, 794.0f, 490.9f),
                        PathNode.QuadTo(812.3f, 438.6f, 847.8f, 388.3f),
                        PathNode.QuadTo(853.9f, 379.8f, 851.2f, 374.6f),
                        PathNode.QuadTo(848.5f, 369.3f, 838.5f, 369.3f),
                        PathNode.LineTo(304.7f, 369.3f),
                        PathNode.QuadTo(293.6f, 369.3f, 291.1f, 374.6f),
                        PathNode.QuadTo(288.6f, 379.8f, 295.2f, 388.9f),
                        PathNode.Close,
                        PathNode.MoveTo(716.3f, 146.3f),
                        PathNode.QuadTo(723.6f, 159.9f, 715.8f, 170.8f),
                        PathNode.QuadTo(708.1f, 181.6f, 692.0f, 181.6f),
                        PathNode.LineTo(449.0f, 181.6f),
                        PathNode.QuadTo(434.0f, 181.6f, 426.7f, 170.8f),
                        PathNode.QuadTo(419.5f, 159.9f, 426.8f, 146.3f),
                        PathNode.QuadTo(449.1f, 103.0f, 487.2f, 79.8f),
                        PathNode.QuadTo(525.3f, 56.6f, 572.0f, 56.6f),
                        PathNode.QuadTo(617.8f, 56.6f, 656.3f, 80.3f),
                        PathNode.QuadTo(694.9f, 104.0f, 716.3f, 146.3f),
                        PathNode.Close,
                    ),
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1f,
                    pathFillType = PathFillType.NonZero,
                )
            }
        }.build()
        return _notificationsDemibold!!
    }

private var _notificationsDemibold: ImageVector? = null
