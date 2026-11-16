// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.mutableStateMapOf
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
    private val anchors = mutableStateMapOf<NavKey, NavTransitionAnchor>()
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
        if (bindings.isEmpty() && anchors.isEmpty()) {
            previousKeys = keys
            activated = null
            return
        }
        val origin = previousKeys.lastOrNull()
        keys.forEach { destination ->
            if (destination in previousKeys) return@forEach
            if (!usesExpansion(destination)) return@forEach
            val exiting = anchors[destination]
            if (exiting != null) {
                exiting.updateOrigin()
            } else {
                val binding = activated?.takeIf {
                    it.origin in previousKeys && it.destination == destination && it.source.hasLivePlacement
                } ?: bindings.singleOrNull { it.origin == origin && it.destination == destination && it.source.hasLivePlacement }
                if (binding != null) {
                    binding.source.capture(binding.cornerRadius, binding.containerColor, binding.corners)?.let { anchors[destination] = it }
                }
            }
        }
        previousKeys = keys
        activated = null
    }

    fun anchor(key: NavKey?): NavTransitionAnchor? = anchors[key]

    fun onEntryRemoved(key: NavKey) {
        if (key !in backStack) anchors.remove(key)?.release()
    }

    fun reentryAt(key: NavKey, positionInWindow: Offset): (() -> Unit)? {
        val anchor = anchors[key] ?: return null
        val bounds = anchor.source?.currentBounds ?: return null
        if (key in backStack || !bounds.contains(positionInWindow) || anchor.source?.contains(positionInWindow, anchor.corners) != true) return null
        val origin = backStack.lastOrNull()
        return {
            if (anchors[key] === anchor && backStack.lastOrNull() == origin && key !in backStack && anchor.source?.hasLivePlacement == true) {
                anchor.updateOrigin()
                anchor.retain()
                anchors[key] = anchor
                backStack.add(key)
            }
        }
    }

    fun dispose() {
        anchors.values.forEach { it.release() }
        anchors.clear()
        bindings.clear()
        activated = null
    }
}

internal class NavExpansionBinding(
    val origin: NavKey,
    val destination: NavKey,
    val source: NavTransitionSource,
    val cornerRadius: Dp,
    val containerColor: Color,
    val corners: NavTransitionCorners,
)
