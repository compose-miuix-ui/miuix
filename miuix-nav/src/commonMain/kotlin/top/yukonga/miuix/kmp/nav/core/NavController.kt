// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * Optional thin wrapper over a [SnapshotStateList] of [NavKey] (i.e. a `NavBackStack`).
 *
 * Holds no state of its own beyond the wrapped list; every operation mutates [backStack] directly.
 * Callers may equivalently operate the list without a controller.
 *
 * The result channel (navigateForResult/setResult/observeResult) is deferred past v1 core
 * (design spec §12) and intentionally absent from this signature.
 */
public class NavController(public val backStack: SnapshotStateList<NavKey>) {
    /** Pushes [key] onto the top of the stack. */
    public fun push(key: NavKey) {
        backStack.add(key)
    }

    /**
     * Removes the top entry if more than the root remains.
     *
     * @return `true` if an entry was popped, `false` if only the root remained.
     */
    public fun pop(): Boolean =
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
            true
        } else {
            false
        }

    /** Replaces the top entry with [key], or adds it if the stack is empty. */
    public fun replace(key: NavKey) {
        if (backStack.isNotEmpty()) {
            backStack[backStack.lastIndex] = key
        } else {
            backStack.add(key)
        }
    }

    /**
     * Pops entries until [predicate] matches the top, always keeping at least the root.
     */
    public fun popUntil(predicate: (NavKey) -> Boolean) {
        while (backStack.size > 1 && !predicate(backStack.last())) {
            backStack.removeAt(backStack.lastIndex)
        }
    }
}
