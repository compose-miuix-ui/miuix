// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.blend

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode

/**
 * Predefined blur color blend configurations for use with `BlurColors.blendColors`.
 *
 * Provides 30+ curated blend token lists organized by visual series and weight,
 * for both light and dark themes. Each token list is a `List<BlendColorEntry>` that
 * can be passed directly to the blur system's color blending pipeline.
 *
 * ### Series
 *
 * - **Info**: Neutral grey blends for informational surfaces. Uses `PlusDarker`/
 *   `PlusLighter` for subtle depth.
 * - **Colored**: Tinted blends with `Overlay`, `SoftLight`, `ColorDodge`, `ColorBurn`
 *   modes for rich, colorful surfaces.
 * - **Pured**: High-contrast blends using `ColorBurn`, `PlusLighter`, `HardLight` for
 *   bold, saturated surfaces with accent colors.
 * - **Overlay**: Luminosity-based blends for translucent overlay surfaces.
 *
 * ### Weights
 *
 * Each series (where applicable) provides five weights:
 * - `Extra_Thin` — Barely visible tint, minimal opacity.
 * - `Thin` — Light tint, subtle presence.
 * - `Regular` — Standard weight, balanced opacity.
 * - `Thick` — Strong presence, higher opacity.
 * - `Extra_Thick` — Maximum weight, near-opaque.
 *
 * ### Usage
 *
 * ```kotlin
 * val colors = BlurColors(
 *     blendColors = if (isDark) {
 *         ColorBlendToken.Colored_Regular_Dark
 *     } else {
 *         ColorBlendToken.Colored_Regular_Light
 *     },
 * )
 *
 * Modifier.textureBlur(
 *     backdrop = backdrop,
 *     shape = RoundedCornerShape(16.dp),
 *     colors = colors,
 * )
 * ```
 */
object ColorBlendToken {

    // ── Info Series ──────────────────────────────────────────────────────

    /** Info Extra Thin, light theme. Neutral grey with PlusDarker blend. */
    val Info_Extra_Thin_Light = listOf(
        BlendColorEntry(Color(0x3BB0B0B1), BlurBlendMode.PlusDarker),
    )

    /** Info Extra Thin, dark theme. Neutral grey with PlusLighter blend. */
    val Info_Extra_Thin_Dark = listOf(
        BlendColorEntry(Color(0x3BB0B0B1), BlurBlendMode.PlusLighter),
    )

    /** Info Thin, light theme. Medium grey with PlusLighter blend. */
    val Info_Thin_Light = listOf(
        BlendColorEntry(Color(0x801E1E1E), BlurBlendMode.PlusLighter),
    )

    /** Info Thin, dark theme. Medium grey with PlusDarker blend. */
    val Info_Thin_Dark = listOf(
        BlendColorEntry(Color(0x801E1E1E), BlurBlendMode.PlusDarker),
    )

    /** Info Regular, light theme. Dark grey with PlusLighter blend. */
    val Info_Regular_Light = listOf(
        BlendColorEntry(Color(0xB3141414), BlurBlendMode.PlusLighter),
    )

    /** Info Regular, dark theme. Dark grey with PlusDarker blend. */
    val Info_Regular_Dark = listOf(
        BlendColorEntry(Color(0xB3141414), BlurBlendMode.PlusDarker),
    )

    /** Info Thick, light theme. Near-white grey with PlusLighter blend. */
    val Info_Thick_Light = listOf(
        BlendColorEntry(Color(0xFF9A9A9A), BlurBlendMode.PlusLighter),
    )

    /** Info Thick, dark theme. Near-white grey with PlusDarker blend. */
    val Info_Thick_Dark = listOf(
        BlendColorEntry(Color(0xFF9A9A9A), BlurBlendMode.PlusDarker),
    )

    // ── Info Colored ─────────────────────────────────────────────────────

    /** Info Colored Regular. Purple accent with ColorDodge + PlusLighter blend. */
    val Info_Colored_Regular = listOf(
        BlendColorEntry(Color(0xFF9C27B0), BlurBlendMode.ColorDodge),
        BlendColorEntry(Color(0x0FFFFFFF), BlurBlendMode.PlusLighter),
    )

    // ── Colored Series ───────────────────────────────────────────────────

    /** Colored Extra Thin, light theme. */
    val Colored_Extra_Thin_Light = listOf(
        BlendColorEntry(Color(0x7F040404), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0x26F1F1F1), BlurBlendMode.ColorDodge),
        BlendColorEntry(Color(0x1AC8C8C8), BlurBlendMode.SrcOver),
    )

    /** Colored Extra Thin, dark theme. */
    val Colored_Extra_Thin_Dark = listOf(
        BlendColorEntry(Color(0x6A4A4A4A), BlurBlendMode.ColorBurn),
        BlendColorEntry(Color(0x2E525252), BlurBlendMode.SrcOver),
    )

    /** Colored Thin, light theme. */
    val Colored_Thin_Light = listOf(
        BlendColorEntry(Color(0x991C1C1C), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0x802B2B2B), BlurBlendMode.SoftLight),
    )

    /** Colored Thin, dark theme. */
    val Colored_Thin_Dark = listOf(
        BlendColorEntry(Color(0x1A9C9C9C), BlurBlendMode.ColorBurn),
        BlendColorEntry(Color(0x337A7A7A), BlurBlendMode.PlusDarker),
    )

    /** Colored Regular, light theme. */
    val Colored_Regular_Light = listOf(
        BlendColorEntry(Color(0x803F3F3F), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0x1CE6E6E6), BlurBlendMode.PlusLighter),
    )

    /** Colored Regular, dark theme. */
    val Colored_Regular_Dark = listOf(
        BlendColorEntry(Color(0x70000000), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0x14000000), BlurBlendMode.SrcOver),
    )

    /** Colored Thick, light theme. */
    val Colored_Thick_Light = listOf(
        BlendColorEntry(Color(0xE6BDBDBD), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0x992B2B2B), BlurBlendMode.ColorDodge),
        BlendColorEntry(Color(0x339C9C9C), BlurBlendMode.SrcOver),
    )

    /** Colored Thick, dark theme. */
    val Colored_Thick_Dark = listOf(
        BlendColorEntry(Color(0x667A7A7A), BlurBlendMode.ColorBurn),
        BlendColorEntry(Color(0x33747474), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0x322B2B2B), BlurBlendMode.SrcOver),
    )

    /** Colored Extra Thick, light theme. */
    val Colored_Extra_Thick_Light = listOf(
        BlendColorEntry(Color(0x4DA9A9A9), BlurBlendMode.PlusLighter),
        BlendColorEntry(Color(0x6BC0C0C0), BlurBlendMode.ColorDodge),
    )

    /** Colored Extra Thick, dark theme. */
    val Colored_Extra_Thick_Dark = listOf(
        BlendColorEntry(Color(0x667A7A7A), BlurBlendMode.PlusDarker),
        BlendColorEntry(Color(0x619C9C9C), BlurBlendMode.ColorBurn),
    )

    // ── Pured Series ─────────────────────────────────────────────────────

    /** Pured Extra Thin, light theme. */
    val Pured_Extra_Thin_Light = listOf(
        BlendColorEntry(Color(0x7F040404), BlurBlendMode.ColorBurn),
        BlendColorEntry(Color(0x5EFFFFFF), BlurBlendMode.PlusLighter),
        BlendColorEntry(Color(0x24FF2424), BlurBlendMode.SrcOver),
    )

    /** Pured Extra Thin, dark theme. */
    val Pured_Extra_Thin_Dark = listOf(
        BlendColorEntry(Color(0xE6E6E6E6), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0x999C9C9C), BlurBlendMode.SrcOver),
    )

    /** Pured Thin, light theme. */
    val Pured_Thin_Light = listOf(
        BlendColorEntry(Color(0x307A7A7A), BlurBlendMode.PlusLighter),
        BlendColorEntry(Color(0x5EFFFFFF), BlurBlendMode.PlusLighter),
        BlendColorEntry(Color(0x66FF6666), BlurBlendMode.SrcOver),
    )

    /** Pured Thin, dark theme. */
    val Pured_Thin_Dark = listOf(
        BlendColorEntry(Color(0x969C9C9C), BlurBlendMode.PlusDarker),
        BlendColorEntry(Color(0x66000000), BlurBlendMode.SrcOver),
    )

    /** Pured Regular, light theme. */
    val Pured_Regular_Light = listOf(
        BlendColorEntry(Color(0x340034F9), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0xB3FFFFFF), BlurBlendMode.HardLight),
    )

    /** Pured Regular, dark theme. */
    val Pured_Regular_Dark = listOf(
        BlendColorEntry(Color(0x75000000), BlurBlendMode.ColorBurn),
        BlendColorEntry(Color(0x52000000), BlurBlendMode.SrcOver),
    )

    /** Pured Thick, light theme. */
    val Pured_Thick_Light = listOf(
        BlendColorEntry(Color(0x4D000000), BlurBlendMode.Overlay),
        BlendColorEntry(Color(0x80000000), BlurBlendMode.SrcOver),
    )

    /** Pured Thick, dark theme. */
    val Pured_Thick_Dark = listOf(
        BlendColorEntry(Color(0x4C000000), BlurBlendMode.ColorBurn),
        BlendColorEntry(Color(0x80030303), BlurBlendMode.SrcOver),
    )

    /** Pured Extra Thick, light theme. */
    val Pured_Extra_Thick_Light = listOf(
        BlendColorEntry(Color(0x66FF6666), BlurBlendMode.PlusLighter),
        BlendColorEntry(Color(0x999C9C9C), BlurBlendMode.SrcOver),
    )

    /** Pured Extra Thick, dark theme. */
    val Pured_Extra_Thick_Dark = listOf(
        BlendColorEntry(Color(0x999C9C9C), BlurBlendMode.Luminosity),
        BlendColorEntry(Color(0x54525252), BlurBlendMode.PlusLighter),
    )

    // ── Overlay Series ───────────────────────────────────────────────────

    /** Overlay Extra Thin, light theme. */
    val Overlay_Extra_Thin_Light = listOf(
        BlendColorEntry(Color(0x0F7A7A7A), BlurBlendMode.Luminosity),
    )

    /** Overlay Extra Thin, dark theme. */
    val Overlay_Extra_Thin_Dark = listOf(
        BlendColorEntry(Color(0x757A7A7A), BlurBlendMode.Luminosity),
    )

    /** Overlay Thin, light theme. */
    val Overlay_Thin_Light = listOf(
        BlendColorEntry(Color(0x4DA9A9A9), BlurBlendMode.Luminosity),
        BlendColorEntry(Color(0x1A9C9C9C), BlurBlendMode.PlusDarker),
    )

    /** Overlay Regular, light theme. */
    val Overlay_Regular_Light = listOf(
        BlendColorEntry(Color(0x4DA9A9A9), BlurBlendMode.Luminosity),
        BlendColorEntry(Color(0x1A2B2B2B), BlurBlendMode.PlusDarker),
    )

    /** Overlay Thick, light theme. */
    val Overlay_Thick_Light = listOf(
        BlendColorEntry(Color(0xA8A8A8A8), BlurBlendMode.Luminosity),
        BlendColorEntry(Color(0xFF9A9A9A), BlurBlendMode.Overlay),
    )

    /** Overlay Thick, dark theme. */
    val Overlay_Thick_Dark = listOf(
        BlendColorEntry(Color(0x66A8A8A8), BlurBlendMode.Luminosity),
        BlendColorEntry(Color(0x999C9C9C), BlurBlendMode.PlusDarker),
    )

    /** Overlay Extra Thick, light theme. */
    val Overlay_Extra_Thick_Light = listOf(
        BlendColorEntry(Color(0x99A8A8A8), BlurBlendMode.Luminosity),
        BlendColorEntry(Color(0x4C000000), BlurBlendMode.ColorBurn),
    )

    // ── Legacy (Deprecated) ──────────────────────────────────────────────

    /** @deprecated Use [Colored_Extra_Thick_Light] or [Pured_Extra_Thick_Light] instead. */
    val ExtraHeavy_Light = listOf(
        BlendColorEntry(Color(0x8F040404), BlurBlendMode.ColorDodge),
        BlendColorEntry(Color(0xA3A3A3A3), BlurBlendMode.SrcOver),
    )

    /** @deprecated Use [Colored_Extra_Thick_Dark] or [Pured_Extra_Thick_Dark] instead. */
    val ExtraHeavy_Dark = listOf(
        BlendColorEntry(Color(0x757A7A7A), BlurBlendMode.ColorBurn),
        BlendColorEntry(Color(0x88888888), BlurBlendMode.SrcOver),
        BlendColorEntry(Color(0x0B000000), BlurBlendMode.SrcOver),
    )

    /** @deprecated Use [Colored_Thick_Light] or [Pured_Thick_Light] instead. */
    val Heavy_Light = listOf(
        BlendColorEntry(Color(0x949C9C9C), BlurBlendMode.ColorDodge),
        BlendColorEntry(Color(0x999C9C9C), BlurBlendMode.SrcOver),
    )

    /** @deprecated Use [Colored_Thick_Dark] or [Pured_Thick_Dark] instead. */
    val Heavy_Dark = listOf(
        BlendColorEntry(Color(0x7F040404), BlurBlendMode.ColorBurn),
        BlendColorEntry(Color(0xB3B3B3B3), BlurBlendMode.SrcOver),
    )
}
