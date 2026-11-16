// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import top.yukonga.miuix.kmp.nav.core.NavDisplayEffects
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope

/** Resolve stateful presets once per host, including branches of directional transitions. */
internal interface NavTransitionHostBinding {
    val needsSource: Boolean get() = false

    fun bind(context: NavTransitionHostContext, entryKey: NavKey?): NavTransition
}

internal class NavTransitionHostContext(val expansion: NavExpansionState, val effects: NavDisplayEffects) {
    private val transitions = mutableMapOf<Pair<NavTransition, NavKey?>, NavTransition>()

    fun resolve(transition: NavTransition, entryKey: NavKey? = null): NavTransition = if (transition is NavTransitionHostBinding) {
        transitions.getOrPut(transition to entryKey) { transition.bind(this, entryKey) }
    } else {
        transition
    }

    fun onEntryRemoved(key: NavKey) {
        expansion.onEntryRemoved(key)
        transitions.keys.removeAll { it.second == key }
    }
}

/** Resolves source taps while the destination is exiting. */
internal interface NavTransitionReentry {
    fun reentryAt(key: NavKey, positionInWindow: Offset): (() -> Unit)?
}

/** Internal entry rendering contract. Public navigation calls do not manage drawing or recordings. */
internal interface NavHostTransition {
    @Composable
    fun rememberRenderer(scope: NavTransitionScope): NavEntryRenderer?
}

internal interface NavEntryRenderer {
    /** Keep resources alive until the endpoint's draw traversal has completed. */
    val retainEntry: Boolean

    fun drawContent(scope: ContentDrawScope)
    suspend fun trackLifetime()
}
