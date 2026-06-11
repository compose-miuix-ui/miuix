// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlin.random.Random

/**
 * Display-level registry of per-entry [ViewModelStore]s, keyed by entry content key.
 *
 * Scoping the per-entry stores here — instead of in each entry host's composition — ties ViewModel
 * lifetime to back-stack membership rather than composition presence: an entry culled out of the
 * visible window (`relativeDepth > opaqueDepth`) keeps its ViewModels, and on platforms with a
 * retained parent [ViewModelStoreOwner] (Android) the registry itself survives configuration
 * changes inside the parent's store.
 *
 * A store is cleared exactly once: when its entry permanently leaves the back stack (the layout's
 * finished-leaving pass calls [clearStore]) or when the registry itself is dropped ([onCleared]
 * from the parent store, or the no-parent fallback's disposal).
 */
internal class NavEntryViewModelStores : ViewModel() {
    private val stores = mutableMapOf<Any, ViewModelStore>()

    /** Returns the store for [contentKey], creating it on first use. */
    fun storeFor(contentKey: Any): ViewModelStore = stores.getOrPut(contentKey) { ViewModelStore() }

    /** Clears and drops the store for [contentKey]; no-op when absent. */
    fun clearStore(contentKey: Any) {
        stores.remove(contentKey)?.clear()
    }

    /** Clears every store; used by [onCleared] and the no-parent fallback disposal. */
    fun clearAll() {
        stores.values.forEach { it.clear() }
        stores.clear()
    }

    override fun onCleared() {
        clearAll()
    }
}

private val navStoresFactory = viewModelFactory { initializer { NavEntryViewModelStores() } }

/**
 * No-parent fallback holder: without a parent [ViewModelStoreOwner] there is nothing retained to
 * park the registry in, so it lives in the display's composition and clears all remaining stores
 * when the display itself leaves (entries removed earlier are already cleared individually).
 */
private class DisplayScopedStores : RememberObserver {
    val stores = NavEntryViewModelStores()

    override fun onRemembered() = Unit

    override fun onForgotten() = stores.clearAll()

    override fun onAbandoned() = stores.clearAll()
}

/**
 * Remembers the display's [NavEntryViewModelStores] registry.
 *
 * With a parent [LocalViewModelStoreOwner] present the registry is a [ViewModel] in the parent's
 * store under a per-display saveable key, so it survives both depth culling (it is not tied to any
 * entry host) and, on Android, configuration changes. The per-display key keeps two displays under
 * one parent from sharing or cross-clearing per-entry stores. Without a parent owner the registry
 * falls back to a composition-scoped instance cleared when the display leaves.
 */
@Composable
internal fun rememberNavEntryViewModelStores(): NavEntryViewModelStores {
    val parent = LocalViewModelStoreOwner.current
    return if (parent != null) {
        val registryKey = rememberSaveable { "miuix-nav-entry-stores:${Random.nextLong().toString(16)}" }
        remember(parent, registryKey) {
            ViewModelProvider.create(parent, navStoresFactory)[registryKey, NavEntryViewModelStores::class]
        }
    } else {
        remember { DisplayScopedStores() }.stores
    }
}

/**
 * Per-entry [ViewModelStoreOwner]: a plain view over the display registry's store for one entry.
 * Deliberately NOT a [RememberObserver] — leaving the composition (depth cull) must not clear the
 * store; only the registry does, on permanent back-stack removal.
 */
@Stable
internal class NavEntryViewModelStoreOwner(
    override val viewModelStore: ViewModelStore,
) : ViewModelStoreOwner

/**
 * Remembers the [NavEntryViewModelStoreOwner] for the entry identified by [contentKey], backed by
 * the display-level [stores] registry.
 */
@Composable
internal fun rememberNavEntryViewModelStoreOwner(
    stores: NavEntryViewModelStores,
    contentKey: Any,
): NavEntryViewModelStoreOwner = remember(stores, contentKey) {
    NavEntryViewModelStoreOwner(stores.storeFor(contentKey))
}

/**
 * Provides [owner] as the [LocalViewModelStoreOwner] for [content], scoping `viewModel()` lookups
 * inside an entry to that entry's [ViewModelStore].
 *
 * @param owner the per-entry owner from [rememberNavEntryViewModelStoreOwner].
 * @param content the entry body that resolves ViewModels.
 */
@Composable
internal fun ProvideNavEntryViewModelStore(
    owner: NavEntryViewModelStoreOwner,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalViewModelStoreOwner provides owner, content = content)
}
