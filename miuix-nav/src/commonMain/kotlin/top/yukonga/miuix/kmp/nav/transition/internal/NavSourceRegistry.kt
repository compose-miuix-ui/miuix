// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.GraphicsContext
import top.yukonga.miuix.kmp.nav.transition.NavTransitionSource

internal val LocalNavSourceRegistry = staticCompositionLocalOf<NavSourceRegistry?> { null }

/** Rebind a depth-culled entry's restored source to its retained recording and anchors. */
internal class NavSourceRegistry {
    private val sources = mutableMapOf<String, NavTransitionSource>()

    fun acquire(id: String, graphicsContext: GraphicsContext): NavTransitionSource = sources.getOrPut(id) {
        NavTransitionSource(graphicsContext) { source ->
            if (sources[id] === source) sources.remove(id)
        }
    }
}
