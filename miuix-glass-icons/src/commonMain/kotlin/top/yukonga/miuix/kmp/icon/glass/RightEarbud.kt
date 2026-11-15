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

val MiuixIcons.Glass.RightEarbud: ImageVector
    get() = MiuixIcons.Glass.Regular.RightEarbud

val MiuixIcons.Glass.Light.RightEarbud: ImageVector
    get() {
        if (_rightEarbudSurfaceLight != null) return _rightEarbudSurfaceLight!!
        _rightEarbudSurfaceLight = buildHyperOSIcon(
            name = "RightEarbud.Glass.Light",
            pathData = listOf(
                PathNode.MoveTo(128.9f, 470.1f),
                PathNode.QuadTo(140.4f, 444.1f, 149.9f, 427.6f),
                PathNode.QuadTo(159.4f, 411f, 177.4f, 388f),
                PathNode.QuadTo(214.6f, 337.3f, 291.4f, 337f),
                PathNode.QuadTo(368.3f, 336.7f, 456f, 371.6f),
                PathNode.VerticalTo(1.9f),
                PathNode.QuadTo(456f, -23.1f, 469.3f, -36.2f),
                PathNode.QuadTo(482.6f, -49.2f, 507.1f, -49.2f),
                PathNode.HorizontalTo(548f),
                PathNode.QuadTo(573f, -49.2f, 586.1f, -36.2f),
                PathNode.QuadTo(599.1f, -23.1f, 599.1f, 1.9f),
                PathNode.VerticalTo(399f),
                PathNode.QuadTo(599.1f, 425.8f, 601.6f, 439.8f),
                PathNode.QuadTo(604f, 453.8f, 609.9f, 466.9f),
                PathNode.QuadTo(615.9f, 480.1f, 634.3f, 512.4f),
                PathNode.QuadTo(664f, 565.2f, 663.7f, 623.6f),
                PathNode.QuadTo(663.4f, 681.9f, 635.8f, 729.7f),
                PathNode.QuadTo(605.7f, 782f, 546.6f, 808.4f),
                PathNode.QuadTo(487.6f, 834.8f, 415.8f, 829.3f),
                PathNode.QuadTo(344f, 823.8f, 277.8f, 785.7f),
                PathNode.QuadTo(168.6f, 722.6f, 124.6f, 643.6f),
                PathNode.QuadTo(80.7f, 564.7f, 128.9f, 470.1f),
                PathNode.Close,
                PathNode.MoveTo(173.6f, 600.1f),
                PathNode.QuadTo(184f, 595.1f, 200.7f, 575.4f),
                PathNode.QuadTo(217.4f, 555.7f, 237.9f, 512.2f),
                PathNode.QuadTo(260.4f, 465f, 262f, 438.2f),
                PathNode.QuadTo(263.6f, 411.4f, 259.2f, 394f),
                PathNode.QuadTo(245.4f, 399.3f, 225.6f, 417.6f),
                PathNode.QuadTo(205.7f, 435.8f, 186.1f, 479.4f),
                PathNode.QuadTo(168.8f, 517.2f, 164.9f, 543.1f),
                PathNode.QuadTo(161f, 568.9f, 164f, 582.1f),
                PathNode.QuadTo(167f, 595.3f, 171.1f, 601f),
                PathNode.Close,
                PathNode.MoveTo(397.6f, 570.8f),
                PathNode.QuadTo(378.8f, 559.7f, 357.7f, 565f),
                PathNode.QuadTo(336.7f, 570.3f, 326f, 589.1f),
                PathNode.QuadTo(315.3f, 607.9f, 321.2f, 628.7f),
                PathNode.QuadTo(327f, 649.6f, 345.2f, 660.2f),
                PathNode.QuadTo(363.6f, 671.3f, 384.4f, 665.5f),
                PathNode.QuadTo(405.2f, 659.7f, 415.9f, 641.9f),
                PathNode.QuadTo(426.6f, 623.1f, 421.4f, 602.3f),
                PathNode.QuadTo(416.3f, 581.4f, 397.6f, 570.8f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 282.9749f,
            translationY = 1057.1686f,
            autoMirror = false,
        )
        return _rightEarbudSurfaceLight!!
    }

private var _rightEarbudSurfaceLight: ImageVector? = null

val MiuixIcons.Glass.Normal.RightEarbud: ImageVector
    get() {
        if (_rightEarbudSurfaceNormal != null) return _rightEarbudSurfaceNormal!!
        _rightEarbudSurfaceNormal = buildHyperOSIcon(
            name = "RightEarbud.Glass.Normal",
            pathData = listOf(
                PathNode.MoveTo(122.8f, 468f),
                PathNode.QuadTo(134.6f, 442f, 144.1f, 425.1f),
                PathNode.QuadTo(153.6f, 408.3f, 171.6f, 385.3f),
                PathNode.QuadTo(209.4f, 333.7f, 284.3f, 331.5f),
                PathNode.QuadTo(359.2f, 329.3f, 445f, 360.9f),
                PathNode.VerticalTo(6.8f),
                PathNode.QuadTo(445f, -21f, 459.8f, -35.7f),
                PathNode.QuadTo(474.6f, -50.4f, 502.2f, -50.4f),
                PathNode.HorizontalTo(548f),
                PathNode.QuadTo(575.7f, -50.4f, 590.5f, -35.7f),
                PathNode.QuadTo(605.2f, -21f, 605.2f, 6.8f),
                PathNode.VerticalTo(401.7f),
                PathNode.QuadTo(605.2f, 427.3f, 607.4f, 439.9f),
                PathNode.QuadTo(609.5f, 452.6f, 615.1f, 465.3f),
                PathNode.QuadTo(620.8f, 478f, 638.9f, 509.4f),
                PathNode.QuadTo(669.5f, 563.7f, 669.1f, 623.9f),
                PathNode.QuadTo(668.6f, 684f, 640.1f, 733.3f),
                PathNode.QuadTo(609.3f, 787.5f, 548.6f, 814.7f),
                PathNode.QuadTo(487.9f, 841.8f, 414.6f, 836.3f),
                PathNode.QuadTo(341.3f, 830.8f, 273.8f, 792.1f),
                PathNode.QuadTo(163.4f, 728.4f, 118.3f, 647f),
                PathNode.QuadTo(73.3f, 565.6f, 122.8f, 468f),
                PathNode.Close,
                PathNode.MoveTo(173.9f, 606.2f),
                PathNode.QuadTo(184f, 604f, 201.9f, 584.4f),
                PathNode.QuadTo(219.9f, 564.8f, 242.8f, 516.2f),
                PathNode.QuadTo(268.4f, 462.3f, 268.9f, 433.9f),
                PathNode.QuadTo(269.4f, 405.6f, 263.2f, 391.3f),
                PathNode.QuadTo(250.6f, 392.9f, 228.6f, 411f),
                PathNode.QuadTo(206.6f, 429.1f, 184f, 479.1f),
                PathNode.QuadTo(164.8f, 521.2f, 161.5f, 548.9f),
                PathNode.QuadTo(158.3f, 576.5f, 162.6f, 589.6f),
                PathNode.QuadTo(167f, 602.7f, 171.7f, 606.5f),
                PathNode.Close,
                PathNode.MoveTo(408.9f, 569.6f),
                PathNode.QuadTo(388.6f, 557.8f, 365.8f, 563.6f),
                PathNode.QuadTo(343.1f, 569.4f, 331.5f, 589.7f),
                PathNode.QuadTo(319.9f, 610f, 326.2f, 632.7f),
                PathNode.QuadTo(332.5f, 655.4f, 351.9f, 666.9f),
                PathNode.QuadTo(372.1f, 678.7f, 394.8f, 672.4f),
                PathNode.QuadTo(417.4f, 666.1f, 429f, 646.8f),
                PathNode.QuadTo(440.6f, 626.5f, 434.9f, 603.8f),
                PathNode.QuadTo(429.2f, 581.1f, 408.9f, 569.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 283.6809f,
            translationY = 1060.0602f,
            autoMirror = false,
        )
        return _rightEarbudSurfaceNormal!!
    }

private var _rightEarbudSurfaceNormal: ImageVector? = null

val MiuixIcons.Glass.Regular.RightEarbud: ImageVector
    get() {
        if (_rightEarbudSurfaceRegular != null) return _rightEarbudSurfaceRegular!!
        _rightEarbudSurfaceRegular = buildHyperOSIcon(
            name = "RightEarbud.Glass.Regular",
            pathData = listOf(
                PathNode.MoveTo(120f, 467f),
                PathNode.QuadTo(132f, 441f, 141.5f, 424f),
                PathNode.QuadTo(151f, 407f, 169f, 384f),
                PathNode.QuadTo(207f, 332f, 281f, 329f),
                PathNode.QuadTo(355f, 326f, 440f, 356f),
                PathNode.VerticalTo(9f),
                PathNode.QuadTo(440f, -20f, 455.5f, -35.5f),
                PathNode.QuadTo(471f, -51f, 500f, -51f),
                PathNode.HorizontalTo(548f),
                PathNode.QuadTo(577f, -51f, 592.5f, -35.5f),
                PathNode.QuadTo(608f, -20f, 608f, 9f),
                PathNode.VerticalTo(403f),
                PathNode.QuadTo(608f, 428f, 610f, 440f),
                PathNode.QuadTo(612f, 452f, 617.5f, 464.5f),
                PathNode.QuadTo(623f, 477f, 641f, 508f),
                PathNode.QuadTo(672f, 563f, 671.5f, 624f),
                PathNode.QuadTo(671f, 685f, 642f, 735f),
                PathNode.QuadTo(611f, 790f, 549.5f, 817.5f),
                PathNode.QuadTo(488f, 845f, 414f, 839.5f),
                PathNode.QuadTo(340f, 834f, 272f, 795f),
                PathNode.QuadTo(161f, 731f, 115.5f, 648.5f),
                PathNode.QuadTo(70f, 566f, 120f, 467f),
                PathNode.Close,
                PathNode.MoveTo(174f, 609f),
                PathNode.QuadTo(184f, 608f, 202.5f, 588.5f),
                PathNode.QuadTo(221f, 569f, 245f, 518f),
                PathNode.QuadTo(272f, 461f, 272f, 432f),
                PathNode.QuadTo(272f, 403f, 265f, 390f),
                PathNode.QuadTo(253f, 390f, 230f, 408f),
                PathNode.QuadTo(207f, 426f, 183f, 479f),
                PathNode.QuadTo(163f, 523f, 160f, 551.5f),
                PathNode.QuadTo(157f, 580f, 162f, 593f),
                PathNode.QuadTo(167f, 606f, 172f, 609f),
                PathNode.Close,
                PathNode.MoveTo(414f, 569f),
                PathNode.QuadTo(393f, 557f, 369.5f, 563f),
                PathNode.QuadTo(346f, 569f, 334f, 590f),
                PathNode.QuadTo(322f, 611f, 328.5f, 634.5f),
                PathNode.QuadTo(335f, 658f, 355f, 670f),
                PathNode.QuadTo(376f, 682f, 399.5f, 675.5f),
                PathNode.QuadTo(423f, 669f, 435f, 649f),
                PathNode.QuadTo(447f, 628f, 441f, 604.5f),
                PathNode.QuadTo(435f, 581f, 414f, 569f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 284.0017f,
            translationY = 1061.375f,
            autoMirror = false,
        )
        return _rightEarbudSurfaceRegular!!
    }

private var _rightEarbudSurfaceRegular: ImageVector? = null

val MiuixIcons.Glass.Medium.RightEarbud: ImageVector
    get() {
        if (_rightEarbudSurfaceMedium != null) return _rightEarbudSurfaceMedium!!
        _rightEarbudSurfaceMedium = buildHyperOSIcon(
            name = "RightEarbud.Glass.Medium",
            pathData = listOf(
                PathNode.MoveTo(116.8f, 464.8f),
                PathNode.QuadTo(128.5f, 439.1f, 138.3f, 421.8f),
                PathNode.QuadTo(148f, 404.6f, 166.3f, 381f),
                PathNode.QuadTo(204.8f, 328.5f, 278.3f, 324.3f),
                PathNode.QuadTo(351.8f, 320.1f, 436.2f, 348.7f),
                PathNode.VerticalTo(9.3f),
                PathNode.QuadTo(436.2f, -22.2f, 453.3f, -39.1f),
                PathNode.QuadTo(470.5f, -56.1f, 501.6f, -56.1f),
                PathNode.HorizontalTo(549.6f),
                PathNode.QuadTo(581.1f, -56.1f, 598f, -39.1f),
                PathNode.QuadTo(615f, -22.2f, 615f, 9.3f),
                PathNode.VerticalTo(403.3f),
                PathNode.QuadTo(615f, 428f, 616.8f, 439.2f),
                PathNode.QuadTo(618.5f, 450.4f, 624f, 462.6f),
                PathNode.QuadTo(629.5f, 474.8f, 647.2f, 505.6f),
                PathNode.QuadTo(679f, 561.9f, 678.4f, 624.4f),
                PathNode.QuadTo(677.8f, 686.9f, 648.2f, 738f),
                PathNode.QuadTo(616.4f, 794.3f, 553.7f, 822.5f),
                PathNode.QuadTo(491f, 850.7f, 415.6f, 845.2f),
                PathNode.QuadTo(340.3f, 839.7f, 270.9f, 799.9f),
                PathNode.QuadTo(158.6f, 735.1f, 112f, 650.5f),
                PathNode.QuadTo(65.4f, 566f, 116.8f, 464.8f),
                PathNode.Close,
                PathNode.MoveTo(174f, 614.7f),
                PathNode.QuadTo(185.6f, 613.9f, 205.5f, 594.7f),
                PathNode.QuadTo(225.3f, 575.5f, 251.5f, 520.4f),
                PathNode.QuadTo(279f, 462.9f, 278.9f, 432.3f),
                PathNode.QuadTo(278.8f, 401.6f, 269.9f, 384.9f),
                PathNode.QuadTo(253.5f, 385.4f, 228.8f, 404.5f),
                PathNode.QuadTo(204f, 423.6f, 179.8f, 477.1f),
                PathNode.QuadTo(159.5f, 522.2f, 157f, 552.3f),
                PathNode.QuadTo(154.6f, 582.4f, 160.6f, 596.9f),
                PathNode.QuadTo(166.7f, 611.4f, 172f, 614.7f),
                PathNode.Close,
                PathNode.MoveTo(418.3f, 564.7f),
                PathNode.QuadTo(395.4f, 551.3f, 369.8f, 558f),
                PathNode.QuadTo(344.1f, 564.7f, 331f, 587.6f),
                PathNode.QuadTo(317.9f, 610.5f, 325f, 636.1f),
                PathNode.QuadTo(332f, 661.8f, 353.9f, 674.9f),
                PathNode.QuadTo(376.8f, 687.9f, 402.5f, 680.9f),
                PathNode.QuadTo(428.1f, 673.9f, 441.2f, 652f),
                PathNode.QuadTo(454.6f, 629.1f, 447.9f, 603.4f),
                PathNode.QuadTo(441.2f, 577.8f, 418.3f, 564.7f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 282.5493f,
            translationY = 1061.6361f,
            autoMirror = false,
        )
        return _rightEarbudSurfaceMedium!!
    }

private var _rightEarbudSurfaceMedium: ImageVector? = null

val MiuixIcons.Glass.Demibold.RightEarbud: ImageVector
    get() {
        if (_rightEarbudSurfaceDemibold != null) return _rightEarbudSurfaceDemibold!!
        _rightEarbudSurfaceDemibold = buildHyperOSIcon(
            name = "RightEarbud.Glass.Demibold",
            pathData = listOf(
                PathNode.MoveTo(114.5f, 463.3f),
                PathNode.QuadTo(126f, 437.8f, 136f, 420.3f),
                PathNode.QuadTo(145.9f, 402.9f, 164.4f, 378.9f),
                PathNode.QuadTo(203.3f, 326f, 276.4f, 321f),
                PathNode.QuadTo(349.5f, 315.9f, 433.6f, 343.6f),
                PathNode.VerticalTo(9.5f),
                PathNode.QuadTo(433.6f, -23.7f, 451.8f, -41.7f),
                PathNode.QuadTo(470.1f, -59.7f, 502.8f, -59.7f),
                PathNode.HorizontalTo(550.8f),
                PathNode.QuadTo(583.9f, -59.7f, 601.9f, -41.7f),
                PathNode.QuadTo(619.9f, -23.7f, 619.9f, 9.5f),
                PathNode.VerticalTo(403.5f),
                PathNode.QuadTo(619.9f, 428f, 621.5f, 438.6f),
                PathNode.QuadTo(623f, 449.2f, 628.5f, 461.3f),
                PathNode.QuadTo(634f, 473.3f, 651.6f, 503.9f),
                PathNode.QuadTo(683.9f, 561.2f, 683.2f, 624.7f),
                PathNode.QuadTo(682.5f, 688.2f, 652.6f, 740.1f),
                PathNode.QuadTo(620.2f, 797.4f, 556.6f, 826f),
                PathNode.QuadTo(493.1f, 854.6f, 416.8f, 849.1f),
                PathNode.QuadTo(340.5f, 843.6f, 270.2f, 803.3f),
                PathNode.QuadTo(156.9f, 737.9f, 109.5f, 651.9f),
                PathNode.QuadTo(62.2f, 566f, 114.5f, 463.3f),
                PathNode.Close,
                PathNode.MoveTo(174f, 618.6f),
                PathNode.QuadTo(186.8f, 618.1f, 207.6f, 599.1f),
                PathNode.QuadTo(228.4f, 580f, 256f, 522.1f),
                PathNode.QuadTo(283.9f, 464.2f, 283.7f, 432.5f),
                PathNode.QuadTo(283.5f, 400.7f, 273.3f, 381.3f),
                PathNode.QuadTo(253.9f, 382.2f, 227.9f, 402f),
                PathNode.QuadTo(201.9f, 421.9f, 177.5f, 475.8f),
                PathNode.QuadTo(157f, 521.6f, 154.9f, 552.9f),
                PathNode.QuadTo(152.9f, 584.1f, 159.7f, 599.7f),
                PathNode.QuadTo(166.5f, 615.2f, 172f, 618.6f),
                PathNode.Close,
                PathNode.MoveTo(421.4f, 561.6f),
                PathNode.QuadTo(397.1f, 547.4f, 370f, 554.5f),
                PathNode.QuadTo(342.8f, 561.6f, 328.9f, 585.9f),
                PathNode.QuadTo(315.1f, 610.1f, 322.5f, 637.3f),
                PathNode.QuadTo(329.9f, 664.4f, 353.2f, 678.3f),
                PathNode.QuadTo(377.4f, 692.1f, 404.6f, 684.7f),
                PathNode.QuadTo(431.7f, 677.3f, 445.6f, 654.1f),
                PathNode.QuadTo(459.9f, 629.8f, 452.7f, 602.7f),
                PathNode.QuadTo(445.6f, 575.5f, 421.4f, 561.6f),
                PathNode.Close,
            ),
            scale = 1f,
            translationX = 281.5323f,
            translationY = 1061.819f,
            autoMirror = false,
        )
        return _rightEarbudSurfaceDemibold!!
    }

private var _rightEarbudSurfaceDemibold: ImageVector? = null

