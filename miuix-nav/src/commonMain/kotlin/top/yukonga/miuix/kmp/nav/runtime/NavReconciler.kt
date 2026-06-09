// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

/**
 * Length of the longest common prefix of [old] and [new] compared by element equality.
 *
 * Both lists are `contentKey` lists; equality (not identity) decides a match so that value-stable
 * keys recompose-survive correctly.
 */
internal fun commonPrefixLength(old: List<Any>, new: List<Any>): Int {
    val limit = minOf(old.size, new.size)
    var i = 0
    while (i < limit && old[i] == new[i]) i++
    return i
}
