// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.navigationevent.DirectNavigationEventInput
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventHandler
import androidx.navigationevent.NavigationEventInfo
import kotlin.test.Test
import kotlin.test.assertEquals

class WindowNavigationEventBridgeTest {
    @Test
    fun forwardsCompleteAndCancelledBackSequences() {
        val targetDispatcher = NavigationEventDispatcher()
        val targetInput = DirectNavigationEventInput().also(targetDispatcher::addInput)
        val received = mutableListOf<String>()
        val targetHandler =
            object : NavigationEventHandler<NavigationEventInfo>(
                initialInfo = NavigationEventInfo.None,
                isBackEnabled = true,
                isForwardEnabled = false,
            ) {
                override fun onBackStarted(event: NavigationEvent) {
                    received += "start:${event.progress}"
                }

                override fun onBackProgressed(event: NavigationEvent) {
                    received += "progress:${event.progress}"
                }

                override fun onBackCancelled() {
                    received += "cancel"
                }

                override fun onBackCompleted() {
                    received += "complete"
                }
            }.also(targetDispatcher::addHandler)

        val sourceDispatcher = NavigationEventDispatcher()
        val sourceInput = DirectNavigationEventInput().also(sourceDispatcher::addInput)
        val forwardingHandler =
            ForwardingNavigationEventHandler(targetInput).also(sourceDispatcher::addHandler)

        sourceInput.backStarted(NavigationEvent(progress = 0f))
        sourceInput.backProgressed(NavigationEvent(progress = 0.4f))
        sourceInput.backCancelled()
        sourceInput.backStarted(NavigationEvent(progress = 0f))
        sourceInput.backProgressed(NavigationEvent(progress = 0.8f))
        sourceInput.backCompleted()

        assertEquals(
            listOf("start:0.0", "progress:0.4", "cancel", "start:0.0", "progress:0.8", "complete"),
            received,
        )

        forwardingHandler.remove()
        targetHandler.remove()
        sourceDispatcher.removeInput(sourceInput)
        targetDispatcher.removeInput(targetInput)
    }
}
