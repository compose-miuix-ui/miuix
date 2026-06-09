// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import kotlin.test.Test
import kotlin.test.assertEquals

class NavReconcilerTest {
    // --- commonPrefixLength ---

    @Test
    fun commonPrefix_identicalLists() {
        assertEquals(3, commonPrefixLength(listOf("a", "b", "c"), listOf("a", "b", "c")))
    }

    @Test
    fun commonPrefix_emptyEither() {
        assertEquals(0, commonPrefixLength(emptyList(), listOf("a")))
        assertEquals(0, commonPrefixLength(listOf("a"), emptyList()))
        assertEquals(0, commonPrefixLength(emptyList(), emptyList()))
    }

    @Test
    fun commonPrefix_sharedPrefixThenDiverge() {
        assertEquals(2, commonPrefixLength(listOf("a", "b", "x"), listOf("a", "b", "y")))
    }

    @Test
    fun commonPrefix_oneIsPrefixOfOther() {
        assertEquals(2, commonPrefixLength(listOf("a", "b"), listOf("a", "b", "c", "d")))
        assertEquals(2, commonPrefixLength(listOf("a", "b", "c", "d"), listOf("a", "b")))
    }

    @Test
    fun commonPrefix_firstElementDiffers() {
        assertEquals(0, commonPrefixLength(listOf("x", "b", "c"), listOf("a", "b", "c")))
    }

    @Test
    fun commonPrefix_usesEqualityNotIdentity() {
        // Distinct String instances that are value-equal must count as a match.
        val a1 = StringBuilder("a").toString()
        val a2 = StringBuilder("a").toString()
        assertEquals(1, commonPrefixLength(listOf<Any>(a1, "b"), listOf<Any>(a2, "c")))
    }
}
