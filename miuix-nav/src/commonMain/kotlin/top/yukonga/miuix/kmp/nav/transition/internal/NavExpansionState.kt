// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.nav.core.NavBackStack
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.transition.NavTransitionAnchor
import top.yukonga.miuix.kmp.nav.transition.NavTransitionCorners
import top.yukonga.miuix.kmp.nav.transition.NavTransitionSource

internal val LocalNavExpansionState = staticCompositionLocalOf<NavExpansionState?> { null }

/** One host owns route bindings and captures; callers keep using their normal navigator. */
internal class NavExpansionState(private val backStack: NavBackStack, private val sourcePolicy: (NavKey) -> Boolean) {
    private var previousKeys: List<NavKey> = backStack.toList()
    private val anchors = mutableMapOf<NavKey, MutableState<NavTransitionAnchor?>>()
    private val bindings = mutableSetOf<NavExpansionBinding>()
    private var activated: NavExpansionBinding? = null

    fun usesExpansion(key: NavKey): Boolean = sourcePolicy(key)

    fun bind(binding: NavExpansionBinding) {
        bindings.add(binding)
    }

    fun unbind(binding: NavExpansionBinding) {
        bindings.remove(binding)
        if (activated === binding) activated = null
    }

    fun activate(binding: NavExpansionBinding) {
        activated = binding
    }

    fun deactivate(binding: NavExpansionBinding) {
        if (activated !== binding) return
        // The click may have changed the stack before NavDisplay recomposes. Capture that
        // navigation first, then discard the press so canceled gestures cannot select a source.
        val keys = backStack.toList()
        if (keys != previousKeys) prepare(keys)
        activated = null
    }

    /** Capture before reconciliation can cull the page holding the clicked component. */
    fun prepare(keys: List<NavKey>) {
        if (keys == previousKeys) return
        val origin = previousKeys.lastOrNull()
        keys.forEach { destination ->
            if (destination in previousKeys) return@forEach
            if (!usesExpansion(destination)) return@forEach
            val exiting = anchors[destination]?.value
            if (exiting != null) {
                exiting.updateOrigin()
            } else {
                val binding = activated?.takeIf {
                    it.origin in previousKeys && it.destination == destination && it.source.hasLivePlacement
                } ?: bindings.singleOrNull { it.origin == origin && it.destination == destination && it.source.hasLivePlacement }
                if (binding != null) {
                    binding.source.capture(binding.cornerRadius, binding.containerColor, binding.corners)?.let {
                        anchorState(destination).value = it
                    }
                }
            }
        }
        previousKeys = keys
        activated = null
    }

    fun anchorState(key: NavKey): MutableState<NavTransitionAnchor?> = anchors.getOrPut(key) { mutableStateOf(null) }

    fun onEntryRemoved(key: NavKey) {
        if (key !in backStack) {
            anchors.remove(key)?.let { slot ->
                val anchor = slot.value
                slot.value = null
                anchor?.release()
            }
        }
    }

    fun reentryAt(key: NavKey, positionInWindow: Offset): (() -> Unit)? {
        val slot = anchors[key] ?: return null
        val anchor = slot.value ?: return null
        val bounds = anchor.source?.currentBounds ?: return null
        if (key in backStack || !bounds.contains(positionInWindow) || anchor.source?.contains(positionInWindow, anchor.corners) != true) return null
        val origin = backStack.lastOrNull()
        return {
            if (slot.value === anchor && backStack.lastOrNull() == origin && key !in backStack && anchor.source?.hasLivePlacement == true) {
                anchor.updateOrigin()
                backStack.add(key)
            }
        }
    }

    fun dispose() {
        anchors.values.forEach { it.value?.release() }
        anchors.clear()
        bindings.clear()
        activated = null
    }
}

internal class NavExpansionBinding(
    val origin: NavKey,
    val destination: NavKey,
    val source: NavTransitionSource,
    var cornerRadius: Dp,
    var containerColor: Color,
    var corners: NavTransitionCorners,
)
