// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.internal

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class GraphicsResourceResetRegistryTest {
    @Test
    fun subscribersJoiningDuringResumeShareThePendingRefresh() {
        val owner = object : LifecycleOwner {
            override val lifecycle = LifecycleRegistry.createUnsafe(this)
        }
        val registry = GraphicsResourceResetRegistry()
        val lifecycle = owner.lifecycle
        lifecycle.currentState = Lifecycle.State.RESUMED

        val existing = mutableListOf<Any>()
        registry.observe(lifecycle) { existing.add(it) }
        assertTrue(existing.isEmpty())

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        val late = mutableListOf<Any>()
        registry.observe(lifecycle) { late.add(it) }
        val resume = existing.single()
        assertSame(resume, late.single())

        val beforeRefresh = registry.generation
        val delayed = assertNotNull(registry.refresh(late.single()))
        assertSame(delayed, registry.refresh(resume))
        assertEquals(beforeRefresh + 1, registry.generation)

        val afterRefresh = mutableListOf<Any>()
        registry.observe(lifecycle) { afterRefresh.add(it) }
        assertTrue(afterRefresh.isEmpty())
    }
}
