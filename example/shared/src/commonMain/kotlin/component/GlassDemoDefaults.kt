// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import top.yukonga.miuix.kmp.glass.GlassMaterial
import top.yukonga.miuix.kmp.glass.GlassMaterials
import top.yukonga.miuix.kmp.glass.GlassStroke
import top.yukonga.miuix.kmp.glass.GlassStrokes

/** Preview choices for the glass component demo. */
internal object GlassDemoDefaults {
    private val materials: List<Pair<String, GlassMaterial?>> = listOf(
        "Component default" to null,
        "Pured Thin Glass Light" to GlassMaterials.PuredThinGlassLight,
        "Pured Thin Glass Dark" to GlassMaterials.PuredThinGlassDark,
        "Popup View Glass Light" to GlassMaterials.PopupViewGlassLight,
        "Popup View Glass Dark" to GlassMaterials.PopupViewGlassDark,
        "Action Bar Mask Light" to GlassMaterials.ActionBarMaskLight,
        "Action Bar Mask Dark" to GlassMaterials.ActionBarMaskDark,
    )

    val MaterialNames: List<String> = materials.map { it.first }
    val StrokeNames: List<String> = listOf("None", "Small", "Middle", "Big")

    fun material(index: Int): GlassMaterial? = materials.getOrNull(index)?.second

    fun stroke(index: Int, isDark: Boolean): GlassStroke? = when (index) {
        1 -> if (isDark) GlassStrokes.SmallDark else GlassStrokes.SmallLight
        2 -> if (isDark) GlassStrokes.MiddleDark else GlassStrokes.MiddleLight
        3 -> if (isDark) GlassStrokes.BigDark else GlassStrokes.BigLight
        else -> null
    }
}
