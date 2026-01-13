// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/** Internal util to use compose ui's fastX iterator apis whenever possible */
internal fun <T> List<T>.fastAnyOrAny(predicate: (T) -> Boolean): Boolean = if (this is RandomAccess) {
    this.fastAny(predicate)
} else {
    @Suppress("ListIterator")
    this.any(predicate)
}

/** Helpers copied from compose:ui:ui-util to prevent adding dep on ui-util */
@Suppress("BanInlineOptIn")
@OptIn(ExperimentalContracts::class)
private inline fun <T> List<T>.fastForEach(action: (T) -> Unit) {
    contract { callsInPlace(action) }
    for (index in indices) {
        val item = get(index)
        action(item)
    }
}

@Suppress("BanInlineOptIn") // Treat Kotlin Contracts as non-experimental.
@OptIn(ExperimentalContracts::class)
private inline fun <T> List<T>.fastAny(predicate: (T) -> Boolean): Boolean {
    contract { callsInPlace(predicate) }
    fastForEach { if (predicate(it)) return true }
    return false
}
