// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.scene

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.navigation3.fastAnyOrAny
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator

/**
 * A [NavEntryDecorator] that sets a [LocalLifecycleOwner] for each entry that sets the maximum
 * [Lifecycle.State] of that entry to [Lifecycle.State.CREATED] once that entry leaves the
 * [entries]. Entries that are on the back stack are unchanged (they can go to
 * [Lifecycle.State.RESUMED]).
 *
 * @param entries the current back stack of [NavEntry] instances.
 */
@Composable
internal fun <T : Any> rememberBackStackAwareLifecycleNavEntryDecorator(
    entries: List<NavEntry<T>>,
): NavEntryDecorator<T> {
    val updatedEntries by rememberUpdatedState(entries)
    return NavEntryDecorator { entry ->
        val isInBackStack = updatedEntries.fastAnyOrAny { it.contentKey == entry.contentKey }
        val maxLifecycle = if (isInBackStack) Lifecycle.State.RESUMED else Lifecycle.State.CREATED
        val owner = rememberLifecycleOwner(maxLifecycle = maxLifecycle)
        CompositionLocalProvider(LocalLifecycleOwner provides owner) { entry.Content() }
    }
}
