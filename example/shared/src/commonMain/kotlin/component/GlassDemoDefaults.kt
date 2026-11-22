// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassEdgeStrokes
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassMaterials

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

    fun stroke(index: Int, isDark: Boolean): EdgeStroke? = when (index) {
        1 -> if (isDark) GlassEdgeStrokes.SmallDark else GlassEdgeStrokes.SmallLight
        2 -> if (isDark) GlassEdgeStrokes.MiddleDark else GlassEdgeStrokes.MiddleLight
        3 -> if (isDark) GlassEdgeStrokes.BigDark else GlassEdgeStrokes.BigLight
        else -> null
    }
}
