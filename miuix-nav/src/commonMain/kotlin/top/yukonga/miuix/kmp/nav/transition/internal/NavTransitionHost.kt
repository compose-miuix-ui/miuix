// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import top.yukonga.miuix.kmp.nav.core.NavDisplayEffects
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope

/** Resolve stateful presets once per host, including branches of directional transitions. */
internal interface NavTransitionHostBinding {
    val needsSource: Boolean get() = false

    fun bind(context: NavTransitionHostContext, entryKey: NavKey?): NavTransition
}

internal class NavTransitionHostContext(val expansion: NavExpansionState, private val currentEffects: () -> NavDisplayEffects) {
    val effects: NavDisplayEffects get() = currentEffects()
    private val transitions = mutableMapOf<NavKey, BoundTransition>()

    fun resolve(transition: NavTransition, entryKey: NavKey? = null): NavTransition = if (transition is NavTransitionHostBinding) transition.bind(this, entryKey) else transition

    fun resolveEntry(transition: NavTransition, key: NavKey): NavTransition {
        if (transition !is NavTransitionHostBinding) {
            transitions.remove(key)
            return transition
        }
        val current = transitions[key]
        if (current != null && current.preset == transition) return current.bound
        return resolve(transition, key).also { transitions[key] = BoundTransition(transition, it) }
    }

    fun onEntryRemoved(key: NavKey) {
        expansion.onEntryRemoved(key)
        transitions.remove(key)
    }

    private class BoundTransition(val preset: NavTransition, val bound: NavTransition)
}

/** Resolves source taps while the destination is exiting. */
internal interface NavTransitionReentry {
    fun reentryAt(key: NavKey, host: LayoutCoordinates, positionInWindow: Offset): ((Offset) -> Unit)?
}

/** Internal entry rendering contract. Public navigation calls do not manage drawing or recordings. */
internal interface NavHostTransition {
    @Composable
    fun rememberRenderer(scope: NavTransitionScope): NavEntryRenderer?
}

internal interface NavEntryRenderer {
    /** Keep resources alive until the endpoint's draw traversal has completed. */
    val retainEntry: Boolean

    fun GraphicsLayerScope.transformPage() {}

    fun drawContent(scope: ContentDrawScope)
    suspend fun trackLifetime()
}
