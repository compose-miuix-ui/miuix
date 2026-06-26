// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder

/**
 * Per-entry [androidx.compose.runtime.saveable.rememberSaveable] scoping for miuix-nav.
 *
 * Wraps a single [SaveableStateHolder] and namespaces each navigation entry's saved state by its
 * value-stable `contentKey`. While an entry is presented its `rememberSaveable` state is preserved
 * across recomposition and configuration/process restore; once the entry fully leaves the
 * presentation set (relativeDepth `d <= -1`) the host must call [removeState] to release its slot.
 *
 * This is the runtime counterpart of design doc §5.3:
 * `rememberSaveableStateHolder()` + per-`contentKey` `SaveableStateProvider`, with
 * `removeState(contentKey)` on exit.
 */
@Stable
internal class NavSaveableStateHolder(
    private val holder: SaveableStateHolder,
) {
    /**
     * Renders [content] inside a `SaveableStateProvider` keyed by [contentKey], so that any
     * `rememberSaveable` reads/writes performed by [content] are isolated to this entry.
     *
     * @param contentKey the value-stable identity of the entry (`NavEntry.contentKey`).
     * @param content the entry body to render within the saveable scope.
     */
    @Composable
    fun EntryStateContent(
        contentKey: Any,
        content: @Composable () -> Unit,
    ) {
        holder.SaveableStateProvider(navSaveableKey(contentKey), content)
    }

    /**
     * Releases the saved-state slot associated with [contentKey]. Must be called once an entry has
     * fully exited (relativeDepth `d <= -1`) so its retained state is not restored on a later push
     * of a different entry that happens to reuse the key value.
     *
     * @param contentKey the value-stable identity of the entry whose state should be dropped.
     */
    fun removeState(contentKey: Any) {
        holder.removeState(navSaveableKey(contentKey))
    }
}

/**
 * Maps a `contentKey` to a Bundle-storable [String] key for [SaveableStateHolder].
 *
 * `SaveableStateProvider` requires its key to be a type the platform can persist (on Android, only
 * Bundle-storable types — a raw `@Serializable` route object such as `Route.Home` is NOT one and
 * throws `IllegalArgumentException`). Keying by [toString] therefore demands two things of every
 * contentKey beyond the equality-uniqueness the back stack already enforces:
 * - **string-unique**: distinct-by-equals keys must not print one string. Kotlin data class
 *   [toString] omits the package, so same-named route classes in different packages DO collide;
 *   the reconcile-time guard in `NavDisplay` rejects any such collision (across the stack and
 *   entries still animating out) before the slots can fold together.
 * - **value-stable**: the string must survive configuration changes and process death. data
 *   classes / data objects derive [toString] from value fields and qualify; a class keeping the
 *   default identity `toString` (`pkg.Cls@1a2b3c`) is unique in-session — no runtime check can
 *   flag it — but resolves to a NEW string after process restore, silently resetting the entry's
 *   `rememberSaveable` state. Documented in the entry DSL and the guide.
 *
 * Used for both saving and removing so the slot identity is consistent, and shared with the
 * reconcile-time guard so the validation and the actual slot key can never drift apart.
 */
internal fun navSaveableKey(contentKey: Any): String = contentKey.toString()

/**
 * Remembers a [NavSaveableStateHolder] backed by a single [rememberSaveableStateHolder].
 *
 * One holder is shared by all entries of a `NavDisplay`; each entry is namespaced by its
 * `contentKey` via [NavSaveableStateHolder.EntryStateContent].
 */
@Composable
internal fun rememberNavSaveableStateHolder(): NavSaveableStateHolder {
    val holder = rememberSaveableStateHolder()
    return remember(holder) { NavSaveableStateHolder(holder) }
}
