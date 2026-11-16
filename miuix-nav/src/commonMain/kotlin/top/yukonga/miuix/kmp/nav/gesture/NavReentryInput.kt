// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import top.yukonga.miuix.kmp.nav.core.NavEntry
import top.yukonga.miuix.kmp.nav.core.transitionOrNull
import top.yukonga.miuix.kmp.nav.runtime.NavPresentation
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.internal.NavTransitionReentry

/** Catch source taps before a shrinking page's transformed controls can receive them. */
internal fun Modifier.navReentryInput(
    presentation: NavPresentation,
    indices: Map<Any, Int>,
    transition: NavTransition,
    coordinates: () -> LayoutCoordinates?,
): Modifier = pointerInput(presentation, indices, transition) {
    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)
        if (down.isConsumed) return@awaitEachGesture
        val topEntry = presentation.presented.maxWithOrNull(
            compareBy<NavEntry<*>> { indices[it.contentKey] ?: -1 }.thenBy { !it.presentation.isRemoving },
        ) ?: return@awaitEachGesture
        if (!topEntry.presentation.isRemoving) return@awaitEachGesture
        val reentry = (topEntry.transitionOrNull() ?: transition) as? NavTransitionReentry
            ?: return@awaitEachGesture
        val layout = coordinates()?.takeIf { it.isAttached } ?: return@awaitEachGesture
        val start = layout.localToWindow(down.position)
        val reopen = reentry.reentryAt(topEntry.key, start) ?: return@awaitEachGesture
        down.consume()
        do {
            val event = awaitPointerEvent(PointerEventPass.Initial)
            val change = event.changes.firstOrNull { it.id == down.id }
            val currentLayout = coordinates()?.takeIf { it.isAttached }
            if (change == null || currentLayout == null || event.changes.any { it.id != down.id && it.pressed }) {
                return@awaitEachGesture
            } else if ((currentLayout.localToWindow(change.position) - start).getDistance() > viewConfiguration.touchSlop || change.isConsumed) {
                // Leave this move unconsumed so scroll/drag recognizers can take ownership.
                return@awaitEachGesture
            }
            if (!change.pressed) {
                change.consume()
                reopen()
                break
            }
        } while (true)
    }
}
