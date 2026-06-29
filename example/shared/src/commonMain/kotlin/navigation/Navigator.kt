// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package navigation

import top.yukonga.miuix.kmp.nav.core.NavBackStack
import top.yukonga.miuix.kmp.nav.core.NavKey

/**
 * Simple navigation helper that owns a miuix-nav back stack.
 * Supports push/replace/pop/popUntil over the shared [NavBackStack].
 */
class Navigator(
    val backStack: NavBackStack,
) {
    /**
     * Push a key onto the back stack.
     */
    fun push(key: NavKey) {
        backStack.add(key)
    }

    /**
     * Replace the top key, or push if the stack is empty.
     */
    fun replace(key: NavKey) {
        if (backStack.isNotEmpty()) {
            backStack[backStack.lastIndex] = key
        } else {
            backStack.add(key)
        }
    }

    /**
     * Pop the top key if present.
     */
    fun pop() {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    /**
     * Pop until predicate matches the top key.
     */
    fun popUntil(predicate: (NavKey) -> Boolean) {
        while (backStack.size > 1 && !predicate(backStack.last())) {
            backStack.removeAt(backStack.lastIndex)
        }
    }

    fun current() = backStack.lastOrNull()

    fun backStackSize() = backStack.size
}
