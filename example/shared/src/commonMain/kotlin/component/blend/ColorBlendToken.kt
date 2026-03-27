// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.blend

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlendMode

object ColorBlendToken {
    // Info Series
    val Info_Extra_Thin_Light = listOf(
        BlendColorEntry(Color(0x3BB0B0B1), BlendMode.PLUS_DARKER),
    )

    val Info_Extra_Thin_Dark = listOf(
        BlendColorEntry(Color(0x3BB0B0B1), BlendMode.PLUS_LIGHTER),
    )

    val Info_Thin_Light = listOf(
        BlendColorEntry(Color(0x801E1E1E), BlendMode.PLUS_LIGHTER),
    )

    val Info_Thin_Dark = listOf(
        BlendColorEntry(Color(0x801E1E1E), BlendMode.PLUS_DARKER),
    )

    val Info_Regular_Light = listOf(
        BlendColorEntry(Color(0xB3141414), BlendMode.PLUS_LIGHTER),
    )

    val Info_Regular_Dark = listOf(
        BlendColorEntry(Color(0xB3141414), BlendMode.PLUS_DARKER),
    )

    val Info_Thick_Light = listOf(
        BlendColorEntry(Color(0xFF9A9A9A), BlendMode.PLUS_LIGHTER),
    )

    val Info_Thick_Dark = listOf(
        BlendColorEntry(Color(0xFF9A9A9A), BlendMode.PLUS_DARKER),
    )

    // Info Colored
    val Info_Colored_Regular = listOf(
        BlendColorEntry(Color(0xFF9C27B0), BlendMode.COLOR_DODGE),
        BlendColorEntry(Color(0x0FFFFFFF), BlendMode.PLUS_LIGHTER),
    )

    // Colored Series
    val Colored_Extra_Thin_Light = listOf(
        BlendColorEntry(Color(0x7F040404), BlendMode.OVERLAY),
        BlendColorEntry(Color(0x26F1F1F1), BlendMode.COLOR_DODGE),
        BlendColorEntry(Color(0x1AC8C8C8), BlendMode.SRC_OVER),
    )

    val Colored_Extra_Thin_Dark = listOf(
        BlendColorEntry(Color(0x6A4A4A4A), BlendMode.COLOR_BURN),
        BlendColorEntry(Color(0x2E525252), BlendMode.SRC_OVER),
    )

    val Colored_Thin_Light = listOf(
        BlendColorEntry(Color(0x991C1C1C), BlendMode.OVERLAY),
        BlendColorEntry(Color(0x802B2B2B), BlendMode.SOFT_LIGHT),
    )

    val Colored_Thin_Dark = listOf(
        BlendColorEntry(Color(0x1A9C9C9C), BlendMode.COLOR_BURN),
        BlendColorEntry(Color(0x337A7A7A), BlendMode.PLUS_DARKER),
    )

    val Colored_Regular_Light = listOf(
        BlendColorEntry(Color(0x803F3F3F), BlendMode.OVERLAY),
        BlendColorEntry(Color(0x1CE6E6E6), BlendMode.PLUS_LIGHTER),
    )

    val Colored_Regular_Dark = listOf(
        BlendColorEntry(Color(0x70000000), BlendMode.OVERLAY),
        BlendColorEntry(Color(0x14000000), BlendMode.SRC_OVER),
    )

    val Colored_Thick_Light = listOf(
        BlendColorEntry(Color(0xE6BDBDBD), BlendMode.OVERLAY),
        BlendColorEntry(Color(0x992B2B2B), BlendMode.COLOR_DODGE),
        BlendColorEntry(Color(0x339C9C9C), BlendMode.SRC_OVER),
    )

    val Colored_Thick_Dark = listOf(
        BlendColorEntry(Color(0x667A7A7A), BlendMode.COLOR_BURN),
        BlendColorEntry(Color(0x33747474), BlendMode.OVERLAY),
        BlendColorEntry(Color(0x322B2B2B), BlendMode.SRC_OVER),
    )

    val Colored_Extra_Thick_Light = listOf(
        BlendColorEntry(Color(0x4DA9A9A9), BlendMode.PLUS_LIGHTER),
        BlendColorEntry(Color(0x6BC0C0C0), BlendMode.COLOR_DODGE),
    )

    val Colored_Extra_Thick_Dark = listOf(
        BlendColorEntry(Color(0x667A7A7A), BlendMode.PLUS_DARKER),
        BlendColorEntry(Color(0x619C9C9C), BlendMode.COLOR_BURN),
    )

    // Pured Series
    val Pured_Extra_Thin_Light = listOf(
        BlendColorEntry(Color(0x7F040404), BlendMode.COLOR_BURN),
        BlendColorEntry(Color(0x5EFFFFFF), BlendMode.PLUS_LIGHTER),
        BlendColorEntry(Color(0x24FF2424), BlendMode.SRC_OVER),
    )

    val Pured_Extra_Thin_Dark = listOf(
        BlendColorEntry(Color(0xE6E6E6E6), BlendMode.OVERLAY),
        BlendColorEntry(Color(0x999C9C9C), BlendMode.SRC_OVER),
    )

    val Pured_Thin_Light = listOf(
        BlendColorEntry(Color(0x307A7A7A), BlendMode.PLUS_LIGHTER),
        BlendColorEntry(Color(0x5EFFFFFF), BlendMode.PLUS_LIGHTER),
        BlendColorEntry(Color(0x66FF6666), BlendMode.SRC_OVER),
    )

    val Pured_Thin_Dark = listOf(
        BlendColorEntry(Color(0x969C9C9C), BlendMode.PLUS_DARKER),
        BlendColorEntry(Color(0x66000000), BlendMode.SRC_OVER),
    )

    val Pured_Regular_Light = listOf(
        BlendColorEntry(Color(0x340034F9), BlendMode.OVERLAY),
        BlendColorEntry(Color(0xB3FFFFFF), BlendMode.HARD_LIGHT),
    )

    val Pured_Regular_Dark = listOf(
        BlendColorEntry(Color(0x75000000), BlendMode.COLOR_BURN),
        BlendColorEntry(Color(0x52000000), BlendMode.SRC_OVER),
    )

    val Pured_Thick_Light = listOf(
        BlendColorEntry(Color(0x4D000000), BlendMode.OVERLAY),
        BlendColorEntry(Color(0x80000000), BlendMode.SRC_OVER),
    )

    val Pured_Thick_Dark = listOf(
        BlendColorEntry(Color(0x4C000000), BlendMode.COLOR_BURN),
        BlendColorEntry(Color(0x80030303), BlendMode.SRC_OVER),
    )

    val Pured_Extra_Thick_Light = listOf(
        BlendColorEntry(Color(0x66FF6666), BlendMode.PLUS_LIGHTER),
        BlendColorEntry(Color(0x999C9C9C), BlendMode.SRC_OVER),
    )

    val Pured_Extra_Thick_Dark = listOf(
        BlendColorEntry(Color(0x999C9C9C), BlendMode.LUMINOSITY),
        BlendColorEntry(Color(0x54525252), BlendMode.PLUS_LIGHTER),
    )

    // Overlay Series
    val Overlay_Extra_Thin_Light = listOf(
        BlendColorEntry(Color(0x0F7A7A7A), BlendMode.LUMINOSITY),
    )

    val Overlay_Extra_Thin_Dark = listOf(
        BlendColorEntry(Color(0x757A7A7A), BlendMode.LUMINOSITY),
    )

    val Overlay_Thin_Light = listOf(
        BlendColorEntry(Color(0x4DA9A9A9), BlendMode.LUMINOSITY),
        BlendColorEntry(Color(0x1A9C9C9C), BlendMode.PLUS_DARKER),
    )

    val Overlay_Regular_Light = listOf(
        BlendColorEntry(Color(0x4DA9A9A9), BlendMode.LUMINOSITY),
        BlendColorEntry(Color(0x1A2B2B2B), BlendMode.PLUS_DARKER),
    )

    val Overlay_Thick_Light = listOf(
        BlendColorEntry(Color(0xA8A8A8A8), BlendMode.LUMINOSITY),
        BlendColorEntry(Color(0xFF9A9A9A), BlendMode.OVERLAY),
    )

    val Overlay_Thick_Dark = listOf(
        BlendColorEntry(Color(0x66A8A8A8), BlendMode.LUMINOSITY),
        BlendColorEntry(Color(0x999C9C9C), BlendMode.PLUS_DARKER),
    )

    val Overlay_Extra_Thick_Light = listOf(
        BlendColorEntry(Color(0x99A8A8A8), BlendMode.LUMINOSITY),
        BlendColorEntry(Color(0x4C000000), BlendMode.COLOR_BURN),
    )

    // Deprecated
    val ExtraHeavy_Light = listOf(
        BlendColorEntry(Color(0x8F040404), BlendMode.COLOR_DODGE),
        BlendColorEntry(Color(0xA3A3A3A3), BlendMode.SRC_OVER),
    )

    val ExtraHeavy_Dark = listOf(
        BlendColorEntry(Color(0x757A7A7A), BlendMode.COLOR_BURN),
        BlendColorEntry(Color(0x88888888), BlendMode.SRC_OVER),
        BlendColorEntry(Color(0x0B000000), BlendMode.SRC_OVER),
    )

    val Heavy_Light = listOf(
        BlendColorEntry(Color(0x949C9C9C), BlendMode.COLOR_DODGE),
        BlendColorEntry(Color(0x999C9C9C), BlendMode.SRC_OVER),
    )

    val Heavy_Dark = listOf(
        BlendColorEntry(Color(0x7F040404), BlendMode.COLOR_BURN),
        BlendColorEntry(Color(0xB3B3B3B3), BlendMode.SRC_OVER),
    )
}
