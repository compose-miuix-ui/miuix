// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.saveable.SaverScope
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

private val TestSaverScope: SaverScope = SaverScope { true }

class NavBackStackSaverTest {
    @Serializable
    private sealed interface Route : NavKey {
        @Serializable
        data object Home : Route

        @Serializable
        data class Detail(val id: String) : Route
    }

    @Test
    fun saver_roundTripsPolymorphicKeys() {
        val original: NavBackStack = navBackStackOf(Route.Home, Route.Detail("42"))

        // Drive the Saver's save/restore lambdas directly without a SaverScope-backed holder.
        val saved = with(NavBackStackSaver) { TestSaverScope.save(original) }
        checkNotNull(saved) { "Saver.save returned null" }
        val restored = NavBackStackSaver.restore(saved)
        checkNotNull(restored) { "Saver.restore returned null" }

        assertEquals(original.toList(), restored.toList())
    }

    @Test
    fun saver_roundTripsEmptyStack() {
        val original: NavBackStack = navBackStackOf()
        val saved = with(NavBackStackSaver) { TestSaverScope.save(original) }
        checkNotNull(saved)
        val restored = NavBackStackSaver.restore(saved)
        checkNotNull(restored)
        assertEquals(emptyList(), restored.toList())
    }
}
