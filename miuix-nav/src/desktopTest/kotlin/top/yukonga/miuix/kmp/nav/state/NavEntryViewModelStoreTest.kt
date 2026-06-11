// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.state

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavDisplayEffects
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.core.navBackStackOf
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertSame
import kotlin.test.assertTrue

private data object StoreRouteA : NavKey

private data object StoreRouteB : NavKey

private data object StoreRouteC : NavKey

private class TrackedViewModel : ViewModel() {
    var cleared = false
        private set

    override fun onCleared() {
        cleared = true
    }
}

private val trackedFactory = viewModelFactory { initializer { TrackedViewModel() } }

/**
 * An entry's ViewModelStore must be scoped to back-stack membership, not to composition presence:
 * depth culling (relativeDepth > opaqueDepth removes the host from composition while the entry is
 * still on the stack) must not clear it, while permanently leaving the stack must.
 */
@OptIn(ExperimentalTestApi::class)
class NavEntryViewModelStoreTest {

    @Test
    fun viewModelSurvivesDepthCullingWhileEntryStaysOnBackStack() = runComposeUiTest {
        val backStack = navBackStackOf(StoreRouteA)
        val stores = mutableListOf<ViewModelStore>()
        var vm: TrackedViewModel? = null
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<StoreRouteA> {
                    val owner = checkNotNull(LocalViewModelStoreOwner.current)
                    vm = remember(owner) {
                        stores.add(owner.viewModelStore)
                        ViewModelProvider.create(owner, trackedFactory)[TrackedViewModel::class]
                    }
                    BasicText("page-a")
                }
                entry<StoreRouteB> { BasicText("page-b") }
                entry<StoreRouteC> { BasicText("page-c") }
            }
        }
        onNodeWithText("page-a").assertExists()
        val initialVm = checkNotNull(vm)

        // Push twice: at depth 2 the bottom entry falls out of the visible window (default
        // opaqueDepth = 1) and its host leaves composition while StoreRouteA stays on the stack.
        backStack.add(StoreRouteB)
        waitForIdle()
        backStack.add(StoreRouteC)
        waitForIdle()

        assertFalse(initialVm.cleared, "ViewModel of an in-stack entry must survive depth culling")

        // Pop back down to the bottom entry: it re-enters composition and must see the same store.
        backStack.removeAt(backStack.lastIndex)
        waitForIdle()
        backStack.removeAt(backStack.lastIndex)
        waitForIdle()
        onNodeWithText("page-a").assertExists()

        assertSame(stores.first(), stores.last(), "re-entering composition must reuse the same ViewModelStore")
        assertFalse(checkNotNull(vm).cleared, "ViewModel must stay alive across cull + restore")
    }

    @Test
    fun viewModelClearedWhenEntryPermanentlyLeavesBackStack() = runComposeUiTest {
        val backStack = navBackStackOf(StoreRouteA, StoreRouteB)
        var vm: TrackedViewModel? = null
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<StoreRouteA> { BasicText("page-a") }
                entry<StoreRouteB> {
                    val owner = checkNotNull(LocalViewModelStoreOwner.current)
                    vm = remember { ViewModelProvider.create(owner, trackedFactory)[TrackedViewModel::class] }
                    BasicText("page-b")
                }
            }
        }
        onNodeWithText("page-b").assertExists()
        val tracked = checkNotNull(vm)

        backStack.removeAt(backStack.lastIndex)
        waitForIdle()
        onNodeWithText("page-a").assertExists()

        assertTrue(tracked.cleared, "ViewModel must be cleared once the entry permanently leaves the stack")
    }
}
