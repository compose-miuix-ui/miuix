// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/** The stock materials. */
object GlassMaterials {

    /** `internal-pured-thin-glass`, light: the body of the floating bars. */
    @Stable
    val PuredThinGlassLight: GlassMaterial = GlassMaterial(
        blurRadius = 20.dp,
        first = GlassColorLayer(Color(0x05000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.SoftLight),
        third = GlassColorLayer(Color(0x66FFFFFF), GlassColorBlendMode.HardLight),
    )

    /** `internal-pured-thin-glass`, dark. */
    @Stable
    val PuredThinGlassDark: GlassMaterial = GlassMaterial(
        blurRadius = 20.dp,
        first = GlassColorLayer(Color(0x1A000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x66565656), GlassColorBlendMode.Luminosity),
        third = GlassColorLayer(Color(0x993F3F3F), GlassColorBlendMode.Overlay),
    )

    /** Popup material, light: 60dp blur with its own three colour layers. */
    @Stable
    val PopupViewGlassLight: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x05000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.SoftLight),
        third = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.HardLight),
    )

    /** Popup material, dark: 60dp blur with its own three colour layers. */
    @Stable
    val PopupViewGlassDark: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x1A000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x996C6C6C), GlassColorBlendMode.Luminosity),
        third = GlassColorLayer(Color(0x99060606), GlassColorBlendMode.SoftLight),
    )

    /** Dialog blur/blend material, light. The three layers are applied in declaration order. */
    @Stable
    val DialogGlassLight: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x05000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.SoftLight),
        third = GlassColorLayer(Color(0x99FFFFFF), GlassColorBlendMode.HardLight),
    )

    /** Dialog blur/blend material, dark. */
    @Stable
    val DialogGlassDark: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x1A000000), GlassColorBlendMode.PlusDarker),
        second = GlassColorLayer(Color(0x996C6C6C), GlassColorBlendMode.Luminosity),
        third = GlassColorLayer(Color(0x99060606), GlassColorBlendMode.SoftLight),
    )

    /** The action bar's `mask-pured-regular`, light. */
    @Stable
    val ActionBarMaskLight: GlassMaterial = GlassMaterial(
        blurRadius = 40.dp,
        first = GlassColorLayer(Color(0x33F9F9F9), GlassColorBlendMode.Overlay),
        second = GlassColorLayer(Color(0xB3FFFFFF), GlassColorBlendMode.HardLight),
    )

    /** The action bar's `mask-pured-regular`, dark. */
    @Stable
    val ActionBarMaskDark: GlassMaterial = GlassMaterial(
        blurRadius = 60.dp,
        first = GlassColorLayer(Color(0x75000000), GlassColorBlendMode.ColorBurn),
        second = GlassColorLayer(Color(0x52000000), GlassColorBlendMode.SrcOver),
    )

    /** Picks the half of a theme pair that belongs to the current theme. */
    @Stable
    fun forTheme(isDark: Boolean, light: GlassMaterial, dark: GlassMaterial): GlassMaterial = if (isDark) dark else light

    /** [PuredThinGlassLight] or [PuredThinGlassDark], for the current theme. */
    @Stable
    fun puredThinGlass(isDark: Boolean): GlassMaterial = forTheme(isDark, PuredThinGlassLight, PuredThinGlassDark)

    /** [PopupViewGlassLight] or [PopupViewGlassDark], for the current theme. */
    @Stable
    fun popupViewGlass(isDark: Boolean): GlassMaterial = forTheme(isDark, PopupViewGlassLight, PopupViewGlassDark)

    /** [DialogGlassLight] or [DialogGlassDark], for the current theme. */
    @Stable
    fun dialogGlass(isDark: Boolean): GlassMaterial = forTheme(isDark, DialogGlassLight, DialogGlassDark)

    /** [ActionBarMaskLight] or [ActionBarMaskDark], for the current theme. */
    @Stable
    fun actionBarMask(isDark: Boolean): GlassMaterial = forTheme(isDark, ActionBarMaskLight, ActionBarMaskDark)
}
