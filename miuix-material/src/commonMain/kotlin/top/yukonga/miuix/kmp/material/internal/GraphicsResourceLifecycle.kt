// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

internal val graphicsResourceResetRegistry = GraphicsResourceResetRegistry()

internal val graphicsResourceGeneration: Long
    get() = graphicsResourceResetRegistry.generation

/** Implementation shared by Miuix modules. */
@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
annotation class InternalGraphicsResourceApi

/** Internal device check shared by Miuix graphics components. */
@InternalGraphicsResourceApi
expect val graphicsResourcesResetOnResume: Boolean

/**
 * Internal implementation shared by Miuix graphics nodes; not a supported public API.
 * Advances the shared cache once for the delayed HWUI refresh of the current resume.
 */
@OptIn(InternalGraphicsResourceApi::class)
private fun refreshGraphicsResourceGeneration(generation: Any): Any? = if (graphicsResourcesResetOnResume) {
    graphicsResourceResetRegistry.refresh(generation)
} else {
    null
}

/** Waits for the delayed HWUI refresh while the host is still resumed. */
@InternalGraphicsResourceApi
suspend fun awaitGraphicsResourceRefresh(lifecycle: Lifecycle, generation: Any): Any? {
    // HWUI can discard resources after the first resumed frame.
    withFrameNanos { }
    withFrameNanos { }
    return if (lifecycle.currentState == Lifecycle.State.RESUMED) refreshGraphicsResourceGeneration(generation) else null
}

/** Host lifecycle used for graphics resources; Android always resolves the Activity lifecycle. */
@Composable
internal expect fun graphicsResourceLifecycle(): Lifecycle?

/** Internal implementation shared by Miuix graphics modifier nodes. */
@InternalGraphicsResourceApi
expect fun CompositionLocalConsumerModifierNode.graphicsResourceLifecycle(): Lifecycle?

/**
 * Internal implementation shared by Miuix graphics components; not a supported public API.
 *
 * Observes Android graphics resource resets with one observer per [lifecycle]. After a pause
 * or a subscription that starts in [Lifecycle.State.STARTED], the next resume synchronously
 * supplies the same identity token to every subscriber,
 * including subscribers that join before the delayed refresh completes.
 * Initial lifecycle catch-up does not reset resources. Other platforms do not subscribe.
 *
 * Call this function and its returned, idempotent cleanup function on the UI thread.
 */
@InternalGraphicsResourceApi
fun observeGraphicsResourceResets(lifecycle: Lifecycle, onReset: (Any) -> Unit): () -> Unit = if (graphicsResourcesResetOnResume) {
    graphicsResourceResetRegistry.observe(lifecycle, onReset)
} else {
    {}
}

/**
 * Internal implementation shared by Miuix graphics components; not a supported public API.
 *
 * Returns a new identity token when Android graphics resources must be recreated. The token is
 * scoped to the host Activity lifecycle and remains null until its first resource reset.
 */
@Composable
@InternalGraphicsResourceApi
fun rememberGraphicsResourceGeneration(): Any? {
    if (!graphicsResourcesResetOnResume) return null

    val lifecycle = graphicsResourceLifecycle() ?: return null
    val generation = remember(lifecycle) { mutableStateOf<Any?>(null) }
    DisposableEffect(lifecycle) {
        val stopObserving = observeGraphicsResourceResets(lifecycle) { generation.value = it }
        onDispose { stopObserving() }
    }
    val resume = generation.value
    val delayed = remember(lifecycle, resume) { mutableStateOf<Any?>(null) }
    LaunchedEffect(lifecycle, resume) {
        if (resume != null) {
            delayed.value = awaitGraphicsResourceRefresh(lifecycle, resume)
        }
    }
    return delayed.value ?: resume
}

internal class GraphicsResourceResetRegistry {
    private val entries = mutableMapOf<Lifecycle, Entry>()
    var generation: Long = 0
        private set

    fun refresh(resumeGeneration: Any): Any? {
        val resume = resumeGeneration as? Resume ?: return null
        if (!resume.active) return null
        return resume.delayedGeneration ?: Any().also {
            resume.delayedGeneration = it
            generation++
        }
    }

    fun observe(lifecycle: Lifecycle, onReset: (Any) -> Unit): () -> Unit {
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) return {}

        val entry = entries.getOrPut(lifecycle) { Entry(lifecycle) }
        val listener = Listener(onReset)
        entry.listeners.add(listener)
        if (!entry.observing) {
            // Register the entry before addObserver, which synchronously catches up its state.
            entry.observing = true
            lifecycle.addObserver(entry.observer)
        } else {
            entry.pendingResume?.let(onReset)
        }

        return {
            if (listener.active) {
                listener.active = false
                entry.listeners.remove(listener)
                // Keep tracking the host until destruction, even with no attached nodes.
                // Shared shader caches may outlive all of their current consumers.
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

        // STARTED can mean that the Activity paused before the first observer was registered.
        private var paused = lifecycle.currentState == Lifecycle.State.STARTED
        private var resume: Resume? = null
        val pendingResume: Any?
            get() = resume?.takeIf { it.active && it.delayedGeneration == null }

        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    paused = true
                    resume?.active = false
                }

                Lifecycle.Event.ON_RESUME -> {
                    if (paused) {
                        paused = false
                        val token = Resume()
                        resume = token
                        generation++
                        // A callback can add or remove subscriptions during dispatch.
                        listeners.toList().forEach { listener ->
                            if (listener.active) listener.onReset(token)
                        }
                    }
                }

                Lifecycle.Event.ON_DESTROY -> {
                    resume?.active = false
                    listeners.forEach { it.active = false }
                    listeners.clear()
                    removeEntry(this)
                }

                else -> Unit
            }
        }
    }

    private class Resume {
        var active = true
        var delayedGeneration: Any? = null
    }

    private class Listener(val onReset: (Any) -> Unit) {
        var active = true
    }
}
