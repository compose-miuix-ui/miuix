// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class NavChangeTest {
    @Test
    fun dataObjects_areSingletons() {
        assertEquals(NavChange.None, NavChange.None)
        assertEquals(NavChange.Push, NavChange.Push)
        assertEquals(NavChange.Pop, NavChange.Pop)
        assertEquals(NavChange.Replace, NavChange.Replace)
        assertEquals(NavChange.ReplaceAll, NavChange.ReplaceAll)
    }

    @Test
    fun multiPush_carriesCount_andEqualsByCount() {
        assertEquals(NavChange.MultiPush(3), NavChange.MultiPush(3))
        assertNotEquals(NavChange.MultiPush(3), NavChange.MultiPush(2))
        assertEquals(3, NavChange.MultiPush(3).count)
    }

    @Test
    fun multiPop_carriesCount_andEqualsByCount() {
        assertEquals(NavChange.MultiPop(4), NavChange.MultiPop(4))
        assertNotEquals(NavChange.MultiPop(4), NavChange.MultiPop(1))
        assertEquals(4, NavChange.MultiPop(4).count)
    }
}
