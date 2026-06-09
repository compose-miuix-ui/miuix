// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private data class K(val name: String) : NavKey

private fun stackOf(vararg keys: NavKey): SnapshotStateList<NavKey> =
    mutableStateListOf<NavKey>().apply { addAll(keys) }

class NavControllerTest {
    @Test
    fun push_appends() {
        val c = NavController(stackOf(K("home")))
        c.push(K("detail"))
        assertEquals(listOf(K("home"), K("detail")), c.backStack.toList())
    }

    @Test
    fun pop_removesTop_andReturnsTrue() {
        val c = NavController(stackOf(K("home"), K("detail")))
        assertTrue(c.pop())
        assertEquals(listOf(K("home")), c.backStack.toList())
    }

    @Test
    fun pop_keepsRoot_andReturnsFalse() {
        val c = NavController(stackOf(K("home")))
        assertFalse(c.pop())
        assertEquals(listOf(K("home")), c.backStack.toList())
    }

    @Test
    fun replace_swapsTop() {
        val c = NavController(stackOf(K("home"), K("a")))
        c.replace(K("b"))
        assertEquals(listOf(K("home"), K("b")), c.backStack.toList())
    }

    @Test
    fun replace_onEmpty_adds() {
        val c = NavController(stackOf())
        c.replace(K("only"))
        assertEquals(listOf(K("only")), c.backStack.toList())
    }

    @Test
    fun popUntil_stopsAtPredicate() {
        val c = NavController(stackOf(K("home"), K("a"), K("b"), K("c")))
        c.popUntil { it == K("a") }
        assertEquals(listOf(K("home"), K("a")), c.backStack.toList())
    }

    @Test
    fun popUntil_keepsRootWhenPredicateNeverMatches() {
        val c = NavController(stackOf(K("home"), K("a"), K("b")))
        c.popUntil { it == K("nope") }
        assertEquals(listOf(K("home")), c.backStack.toList())
    }
}
