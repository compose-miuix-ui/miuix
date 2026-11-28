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
import kotlin.concurrent.Volatile

internal val graphicsResourceResetRegistry = GraphicsResourceResetRegistry()

/** Implementation shared by Miuix modules. */
@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
annotation class InternalGraphicsResourceApi

/** Generation of graphics resources shared by Miuix shader and effect caches. */
@InternalGraphicsResourceApi
val graphicsResourceGeneration: Long
    get() = graphicsResourceResetRegistry.generation

/** Internal device check shared by Miuix graphics components. */
@InternalGraphicsResourceApi
expect val graphicsResourcesResetOnResume: Boolean

/** Waits for the delayed HWUI refresh while the host is still resumed. */
@InternalGraphicsResourceApi
suspend fun awaitGraphicsResourceRefresh(lifecycle: Lifecycle, generation: Any): Any? {
    // HWUI can discard resources after the first resumed frame.
    withFrameNanos { }
    withFrameNanos { }
    // Advances the shared cache once for the delayed HWUI refresh of the current resume.
    return if (lifecycle.currentState == Lifecycle.State.RESUMED) graphicsResourceResetRegistry.refresh(generation) else null
}

/** Host lifecycle used for graphics resources. Android prefers the Activity to the view tree. */
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
 * Initial lifecycle catch-up does not reset resources. Only lifecycles returned by
 * [graphicsResourceLifecycle] need observing; it returns null where resets do not apply.
 *
 * Call this function and its returned cleanup function on the UI thread.
 */
@InternalGraphicsResourceApi
fun observeGraphicsResourceResets(lifecycle: Lifecycle, onReset: (Any) -> Unit): () -> Unit = graphicsResourceResetRegistry.observe(lifecycle, onReset)

/**
 * Internal implementation shared by Miuix graphics components; not a supported public API.
 *
 * Returns a new identity token when Android graphics resources must be recreated. The token is
 * scoped to the host lifecycle and remains null until its first resource reset.
 */
@Composable
@InternalGraphicsResourceApi
fun rememberGraphicsResourceGeneration(): Any? {
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

    // Lifecycle callbacks own the writes. Shader caches can read the generation on other threads.
    @Volatile
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

        val existing = entries[lifecycle]
        val entry = existing ?: Entry(lifecycle).also { entries[lifecycle] = it }
        entry.listeners.add(onReset)
        if (existing == null) {
            // addObserver synchronously catches up the lifecycle state, which never resets.
            lifecycle.addObserver(entry.observer)
        } else {
            entry.pendingResume?.let(onReset)
        }
        // Keep tracking the host until destruction, even with no attached nodes:
        // shared shader caches may outlive all of their current consumers.
        return { entry.listeners.remove(onReset) }
    }

    private inner class Entry(lifecycle: Lifecycle) {
        val listeners = mutableListOf<(Any) -> Unit>()

        // STARTED can mean that the Activity paused before the first observer was registered.
        private var paused = lifecycle.currentState == Lifecycle.State.STARTED
        private var resume: Resume? = null
        val pendingResume: Any?
            get() = resume?.takeIf { it.active && it.delayedGeneration == null }

        val observer: LifecycleEventObserver = LifecycleEventObserver { _, event ->
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
                        listeners.forEach { it(token) }
                    }
                }

                Lifecycle.Event.ON_DESTROY -> {
                    resume?.active = false
                    listeners.clear()
                    entries.remove(lifecycle)
                    lifecycle.removeObserver(observer)
                }

                else -> Unit
            }
        }
    }

    private class Resume {
        var active = true
        var delayedGeneration: Any? = null
    }
}
