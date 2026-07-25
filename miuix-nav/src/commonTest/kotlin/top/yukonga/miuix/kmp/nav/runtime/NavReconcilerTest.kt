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

    // --- navReconcile ---

    @Test
    fun reconcile_identical_isNone() {
        assertEquals(NavChange.None, navReconcile(listOf("a", "b"), listOf("a", "b")))
    }

    @Test
    fun reconcile_bothEmpty_isNone() {
        assertEquals(NavChange.None, navReconcile(emptyList(), emptyList()))
    }

    @Test
    fun reconcile_singlePush() {
        assertEquals(NavChange.Push, navReconcile(listOf("a"), listOf("a", "b")))
    }

    @Test
    fun reconcile_pushFromEmpty_isPush() {
        assertEquals(NavChange.Push, navReconcile(emptyList(), listOf("a")))
    }

    @Test
    fun reconcile_multiPush() {
        assertEquals(NavChange.MultiPush(2), navReconcile(listOf("a"), listOf("a", "b", "c")))
        assertEquals(NavChange.MultiPush(3), navReconcile(listOf("a"), listOf("a", "b", "c", "d")))
    }

    @Test
    fun reconcile_singlePop() {
        assertEquals(NavChange.Pop, navReconcile(listOf("a", "b"), listOf("a")))
    }

    @Test
    fun reconcile_multiPop() {
        assertEquals(NavChange.MultiPop(2), navReconcile(listOf("a", "b", "c"), listOf("a")))
        assertEquals(NavChange.MultiPop(3), navReconcile(listOf("a", "b", "c", "d"), listOf("a")))
    }

    @Test
    fun reconcile_popToEmpty_isMultiPopOrPop() {
        // Removing the only element: removed == 1, added == 0 -> Pop.
        assertEquals(NavChange.Pop, navReconcile(listOf("a"), emptyList()))
        // Removing all of several: removed > 1 -> MultiPop.
        assertEquals(NavChange.MultiPop(2), navReconcile(listOf("a", "b"), emptyList()))
    }

    @Test
    fun reconcile_topReplace_isReplace() {
        // common == new.size - 1 && removed == 1 && added == 1.
        assertEquals(NavChange.Replace, navReconcile(listOf("a", "b"), listOf("a", "c")))
    }

    @Test
    fun reconcile_topReplaceWithDeeperStack_isReplace() {
        assertEquals(NavChange.Replace, navReconcile(listOf("a", "b", "c"), listOf("a", "b", "d")))
    }

    @Test
    fun reconcile_replaceRootKeepsNothingCommon_isReplaceAll() {
        // First element differs -> common == 0.
        assertEquals(NavChange.ReplaceAll, navReconcile(listOf("a", "b"), listOf("x", "y")))
    }

    @Test
    fun reconcile_mixedAddRemove_notSingleTop_isReplaceAll() {
        // common == 1, removed == 2, added == 2 -> not a single top Replace.
        assertEquals(NavChange.ReplaceAll, navReconcile(listOf("a", "b", "c"), listOf("a", "x", "y")))
    }

    @Test
    fun reconcile_replaceTopWhenAddedMoreThanOne_isReplaceAll() {
        // common == 1, removed == 1, added == 2 -> not Replace (added != 1).
        assertEquals(NavChange.ReplaceAll, navReconcile(listOf("a", "b"), listOf("a", "x", "y")))
    }

    @Test
    fun reconcile_replaceWholeStackToSingle_isReplaceAll() {
        // common == 0, removed == 2, added == 1.
        assertEquals(NavChange.ReplaceAll, navReconcile(listOf("a", "b"), listOf("z")))
    }

    @Test
    fun reconcile_singleRootSwap_isReplace() {
        // common == 0 == new.size - 1, removed == 1, added == 1 -> a one-for-one swap of the lone
        // root is classified as Replace (in-place top swap with no layer below), not ReplaceAll.
        assertEquals(NavChange.Replace, navReconcile(listOf("a"), listOf("b")))
    }
}
