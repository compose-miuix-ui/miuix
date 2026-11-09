// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

/** Shared by attached nodes; lifecycle observation and access happen on the UI thread. */
internal class BackdropResumeGeneration private constructor(private val lifecycle: Lifecycle) {
    var immediate: Any = Any()
        private set
    var delayed: Any = Any()
        private set

    private var references = 0
    private val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            immediate = Any()
            delayed = Any()
        }
    }

    fun release() {
        if (--references == 0) {
            lifecycle.removeObserver(observer)
            generations.remove(lifecycle)
        }
    }

    companion object {
        private val generations = mutableMapOf<Lifecycle, BackdropResumeGeneration>()

        fun acquire(lifecycle: Lifecycle): BackdropResumeGeneration {
            val generation = generations.getOrPut(lifecycle) {
                BackdropResumeGeneration(lifecycle).also {
                    // Register before node observers so every node sees the same resume tokens.
                    lifecycle.addObserver(it.observer)
                }
            }
            generation.references++
            return generation
        }
    }
}
