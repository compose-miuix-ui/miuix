// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.state

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.enableSavedStateHandles
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.compose.LocalSavedStateRegistryOwner
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavDisplayEffects
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.core.navBackStackOf
import kotlin.test.Test
import kotlin.test.assertEquals
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
private class SavedStateTrackedViewModel(val handle: SavedStateHandle) : ViewModel()

private val savedStateTrackedFactory = viewModelFactory {
    initializer {
        SavedStateTrackedViewModel(createSavedStateHandle())
    }
}

private class TestSavedStateRegistryOwner :
    SavedStateRegistryOwner,
    ViewModelStoreOwner {
    override val viewModelStore = ViewModelStore()
    private val lifecycleRegistry = LifecycleRegistry(this)
    private val controller = SavedStateRegistryController.create(this)

    init {
        controller.performAttach()
        enableSavedStateHandles()
        controller.performRestore(null)
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    override val lifecycle: Lifecycle get() = lifecycleRegistry
    override val savedStateRegistry: SavedStateRegistry get() = controller.savedStateRegistry
}

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

    @Test
    fun navEntryProvidesHasDefaultViewModelProviderFactoryAndSavedStateExtras() = runComposeUiTest {
        val backStack = navBackStackOf(StoreRouteA)
        val hostRegistryOwner = TestSavedStateRegistryOwner()
        var entryStoreOwner: ViewModelStoreOwner? = null
        var createdVm: SavedStateTrackedViewModel? = null

        setContent {
            CompositionLocalProvider(LocalSavedStateRegistryOwner provides hostRegistryOwner) {
                NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                    entry<StoreRouteA> {
                        val owner = checkNotNull(LocalViewModelStoreOwner.current)
                        entryStoreOwner = owner
                        createdVm = remember(owner) {
                            ViewModelProvider.create(owner, savedStateTrackedFactory)[SavedStateTrackedViewModel::class]
                        }
                        BasicText("page-a")
                    }
                }
            }
        }

        onNodeWithText("page-a").assertExists()
        val storeOwner = checkNotNull(entryStoreOwner)
        assertTrue(storeOwner is HasDefaultViewModelProviderFactory, "entry store owner must implement HasDefaultViewModelProviderFactory")
        assertTrue(storeOwner is SavedStateRegistryOwner, "entry store owner must implement SavedStateRegistryOwner")

        val extras = (storeOwner as HasDefaultViewModelProviderFactory).defaultViewModelCreationExtras
        assertSame(storeOwner, extras[VIEW_MODEL_STORE_OWNER_KEY], "VIEW_MODEL_STORE_OWNER_KEY must match entry store owner")
        assertSame(storeOwner, extras[SAVED_STATE_REGISTRY_OWNER_KEY], "SAVED_STATE_REGISTRY_OWNER_KEY must match entry store owner")

        val vm = checkNotNull(createdVm)
        vm.handle["test_arg"] = "saved_value"
        assertEquals("saved_value", vm.handle["test_arg"])
    }

    @Test
    fun navEntryProvidesDefaultExtrasWithoutHostSavedStateRegistry() = runComposeUiTest {
        val backStack = navBackStackOf(StoreRouteA)
        var entryStoreOwner: ViewModelStoreOwner? = null

        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<StoreRouteA> {
                    entryStoreOwner = LocalViewModelStoreOwner.current
                    BasicText("page-a")
                }
            }
        }

        onNodeWithText("page-a").assertExists()
        val storeOwner = checkNotNull(entryStoreOwner)
        assertTrue(storeOwner is HasDefaultViewModelProviderFactory, "entry store owner must implement HasDefaultViewModelProviderFactory")

        val extras = (storeOwner as HasDefaultViewModelProviderFactory).defaultViewModelCreationExtras
        assertSame(storeOwner, extras[VIEW_MODEL_STORE_OWNER_KEY], "VIEW_MODEL_STORE_OWNER_KEY must match entry store owner")
    }
}
