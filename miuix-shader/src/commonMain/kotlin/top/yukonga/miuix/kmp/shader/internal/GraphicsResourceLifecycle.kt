// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shader.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

private val graphicsResourceResetRegistry = GraphicsResourceResetRegistry()

internal expect val graphicsResourcesResetOnResume: Boolean

/**
 * Internal implementation shared by Miuix graphics components; not a supported public API.
 *
 * Observes Android graphics resource resets with one observer per [lifecycle]. After an observed
 * pause, the next resume synchronously supplies the same identity token to every subscriber.
 * Initial lifecycle catch-up does not reset resources. Other platforms do not subscribe.
 *
 * Call this function and its returned, idempotent cleanup function on the UI thread.
 */
fun observeGraphicsResourceResets(lifecycle: Lifecycle, onReset: (Any) -> Unit): () -> Unit = if (graphicsResourcesResetOnResume) {
    graphicsResourceResetRegistry.observe(lifecycle, onReset)
} else {
    {}
}

/**
 * Internal implementation shared by Miuix graphics components; not a supported public API.
 *
 * Returns a new identity token when Android graphics resources must be recreated. The token is
 * scoped to the current lifecycle and remains null until its first resource reset.
 */
@Composable
fun rememberGraphicsResourceGeneration(): Any? {
    if (!graphicsResourcesResetOnResume) return null

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val generation = remember(lifecycle) { mutableStateOf<Any?>(null) }
    DisposableEffect(lifecycle) {
        val stopObserving = observeGraphicsResourceResets(lifecycle) { generation.value = it }
        onDispose { stopObserving() }
    }
    return generation.value
}

internal class GraphicsResourceResetRegistry {
    private val entries = mutableMapOf<Lifecycle, Entry>()

    fun observe(lifecycle: Lifecycle, onReset: (Any) -> Unit): () -> Unit {
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) return {}

        val entry = entries.getOrPut(lifecycle) { Entry(lifecycle) }
        val listener = Listener(onReset)
        entry.listeners.add(listener)
        if (!entry.observing) {
            // Register the entry before addObserver, which synchronously catches up its state.
            entry.observing = true
            lifecycle.addObserver(entry.observer)
        }

        return {
            if (listener.active) {
                listener.active = false
                entry.listeners.remove(listener)
                if (entry.listeners.isEmpty()) removeEntry(entry)
            }
        }
    }

    private fun removeEntry(entry: Entry) {
        if (entries[entry.lifecycle] !== entry) return

        entries.remove(entry.lifecycle)
        entry.observing = false
        entry.lifecycle.removeObserver(entry.observer)
    }

    private inner class Entry(val lifecycle: Lifecycle) {
        val listeners = mutableListOf<Listener>()
        var observing = false
        private var paused = false

        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> paused = true

                Lifecycle.Event.ON_RESUME -> {
                    if (paused) {
                        paused = false
                        val generation = Any()
                        // A callback can add or remove subscriptions during dispatch.
                        listeners.toList().forEach { listener ->
                            if (listener.active) listener.onReset(generation)
                        }
                    }
                }

                Lifecycle.Event.ON_DESTROY -> {
                    listeners.forEach { it.active = false }
                    listeners.clear()
                    removeEntry(this)
                }

                else -> Unit
            }
        }
    }

    private class Listener(val onReset: (Any) -> Unit) {
        var active = true
    }
}
