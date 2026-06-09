// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import top.yukonga.miuix.kmp.nav.transition.NavRole

/**
 * Internal rendering unit of the navigation runtime.
 *
 * One [NavEntry] is produced per back-stack key by the [NavEntryBuilder] DSL. It is the unit of
 * diffing (via [contentKey]), of [androidx.compose.runtime.movableContentOf] identity, and of
 * per-entry state scoping (saveable state / lifecycle / view-model).
 *
 * @param K the concrete [NavKey] subtype this entry renders.
 * @property key the navigation key this entry was created for.
 * @property contentKey value-stable identity used for diffing and for keeping composition identity
 *   stable across recompositions and reorderings. Defaults to [key] itself when the DSL caller does
 *   not supply one.
 * @property metadata arbitrary per-entry data (e.g. a per-route transition override is stored here
 *   by the DSL). Empty by default.
 * @property content the composable that renders this entry, receiving the typed [key].
 */
internal class NavEntry<K : NavKey>(
    val key: K,
    val contentKey: Any,
    val metadata: Map<String, Any>,
    val content: @Composable (K) -> Unit,
) {
    /**
     * Per-entry presentation snapshot maintained by the reconciler (role + removal flag). Read by
     * the renderer to pick the governing [top.yukonga.miuix.kmp.nav.transition.NavTransition].
     */
    var presentation: NavPresentationState by mutableStateOf(
        NavPresentationState(role = NavRole.Top, isRemoving = false),
    )

    /** Renders this entry's content with its typed key. */
    @Composable
    fun Content() {
        content(key)
    }
}

/**
 * Render-state snapshot of a single [NavEntry].
 *
 * @property role the entry's current [NavRole] (Top / Incoming / Outgoing / Covered).
 * @property isRemoving true while the entry has been removed from the back stack but is still
 *   animating out (relative depth has not yet reached the unload threshold).
 */
internal data class NavPresentationState(
    val role: NavRole,
    val isRemoving: Boolean,
)
