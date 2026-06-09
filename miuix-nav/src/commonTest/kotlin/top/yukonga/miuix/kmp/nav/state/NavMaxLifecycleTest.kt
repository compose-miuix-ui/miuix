// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.state

import androidx.lifecycle.Lifecycle
import top.yukonga.miuix.kmp.nav.core.NavPresentationState
import top.yukonga.miuix.kmp.nav.transition.NavRole
import kotlin.test.Test
import kotlin.test.assertEquals

class NavMaxLifecycleTest {
    // navMaxLifecycleFor reads only presentation.isRemoving; role is irrelevant to the mapping,
    // so a fixed Top role is used and only isRemoving is varied per case.
    private fun presentation(isRemoving: Boolean): NavPresentationState = NavPresentationState(role = NavRole.Top, isRemoving = isRemoving)

    @Test
    fun topSteadyStateIsResumed() {
        assertEquals(Lifecycle.State.RESUMED, navMaxLifecycleFor(presentation(isRemoving = false), d = 0f))
    }

    @Test
    fun topSettlingFromIncomingIsResumed() {
        // d in (-0.5, 0.5) and not removing -> still treated as top.
        assertEquals(Lifecycle.State.RESUMED, navMaxLifecycleFor(presentation(isRemoving = false), d = -0.2f))
        assertEquals(Lifecycle.State.RESUMED, navMaxLifecycleFor(presentation(isRemoving = false), d = 0.4f))
    }

    @Test
    fun coveredLayerIsStarted() {
        assertEquals(Lifecycle.State.STARTED, navMaxLifecycleFor(presentation(isRemoving = false), d = 1f))
        assertEquals(Lifecycle.State.STARTED, navMaxLifecycleFor(presentation(isRemoving = false), d = 0.5f))
    }

    @Test
    fun incomingAboveTopIsStarted() {
        // Entering from the leading edge (-1 < d < -0.5), not removing.
        assertEquals(Lifecycle.State.STARTED, navMaxLifecycleFor(presentation(isRemoving = false), d = -0.8f))
    }

    @Test
    fun removingNearTopIsCreated() {
        // Being popped: still near the top window but marked removing -> CREATED.
        assertEquals(Lifecycle.State.CREATED, navMaxLifecycleFor(presentation(isRemoving = true), d = 0f))
        assertEquals(Lifecycle.State.CREATED, navMaxLifecycleFor(presentation(isRemoving = true), d = -0.4f))
    }

    @Test
    fun fullyExitedIsDestroyed() {
        assertEquals(Lifecycle.State.DESTROYED, navMaxLifecycleFor(presentation(isRemoving = true), d = -1f))
        assertEquals(Lifecycle.State.DESTROYED, navMaxLifecycleFor(presentation(isRemoving = false), d = -1.5f))
    }
}
