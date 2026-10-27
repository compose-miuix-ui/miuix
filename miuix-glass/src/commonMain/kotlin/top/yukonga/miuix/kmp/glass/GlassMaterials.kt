// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/** Predefined glass materials. */
object GlassMaterials {

/** Light material for floating bars. */
    @Stable
    val PuredThinGlassLight: GlassMaterial = GlassMaterial(
        blurRadius = 20.dp,
        first = GlassColorLayer(Color(0x05000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.SoftLight),
        third = GlassColorLayer(Color(0x66FFFFFF), GlassColorBlendMode.HardLight),
    )

/** Dark material for floating bars. */
    @Stable
    val PuredThinGlassDark: GlassMaterial = GlassMaterial(
        blurRadius = 20.dp,
        first = GlassColorLayer(Color(0x1A000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x66565656), GlassColorBlendMode.Luminosity),
        third = GlassColorLayer(Color(0x993F3F3F), GlassColorBlendMode.Overlay),
    )

/** Light popup material with a 60dp blur and three colour layers. */
    @Stable
    val PopupViewGlassLight: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x05000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.SoftLight),
        third = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.HardLight),
    )

/** Dark popup material with a 60dp blur and three colour layers. */
    @Stable
    val PopupViewGlassDark: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x1A000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x996C6C6C), GlassColorBlendMode.Luminosity),
        third = GlassColorLayer(Color(0x99060606), GlassColorBlendMode.SoftLight),
    )

/** Light dialog material with three colour layers applied in order. */
    @Stable
    val DialogGlassLight: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x05000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.SoftLight),
        third = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.HardLight),
    )

/** Dark dialog material with three colour layers applied in order. */
    @Stable
    val DialogGlassDark: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x1A000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x996C6C6C), GlassColorBlendMode.Luminosity),
        third = GlassColorLayer(Color(0x99060606), GlassColorBlendMode.SoftLight),
    )

/** Light underlay material for action bars. */
    @Stable
    val ActionBarMaskLight: GlassMaterial = GlassMaterial(
        blurRadius = 40.dp,
        first = GlassColorLayer(Color(0x33F9F9F9), GlassColorBlendMode.Overlay),
        second = GlassColorLayer(Color(0xB3FFFFFF), GlassColorBlendMode.HardLight),
    )

/** Dark underlay material for action bars. */
    @Stable
    val ActionBarMaskDark: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x75000000), GlassColorBlendMode.ColorBurn),
        second = GlassColorLayer(Color(0x52000000), GlassColorBlendMode.SrcOver),
    )

/** Selects a material from a light/dark theme pair. */
    @Stable
    fun forTheme(isDark: Boolean, light: GlassMaterial, dark: GlassMaterial): GlassMaterial = if (isDark) dark else light

/** Returns the predefined light or dark material for floating bars. */
    @Stable
    fun puredThinGlass(isDark: Boolean): GlassMaterial = forTheme(isDark, PuredThinGlassLight, PuredThinGlassDark)

/** Returns the predefined light or dark popup material. */
    @Stable
    fun popupViewGlass(isDark: Boolean): GlassMaterial = forTheme(isDark, PopupViewGlassLight, PopupViewGlassDark)

/** Returns the predefined light or dark dialog material. */
    @Stable
    fun dialogGlass(isDark: Boolean): GlassMaterial = forTheme(isDark, DialogGlassLight, DialogGlassDark)

/** Returns the predefined light or dark action-bar underlay material. */
    @Stable
    fun actionBarMask(isDark: Boolean): GlassMaterial = forTheme(isDark, ActionBarMaskLight, ActionBarMaskDark)
}
