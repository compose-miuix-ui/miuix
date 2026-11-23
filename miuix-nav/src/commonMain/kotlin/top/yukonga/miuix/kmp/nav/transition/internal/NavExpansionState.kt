// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
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

    fun reconnectPlacedSource(binding: NavExpansionBinding) {
        val sourceKey = binding.sourceKey ?: return
        val anchor = anchors[binding.destination]?.value ?: return
        if (anchor.sourceOrigin != binding.origin || anchor.sourceKey != sourceKey) return
        val previous = anchor.source ?: return
        if (previous === binding.source || previous.hasLivePlacement || !binding.source.hasLivePlacement) return
        if (bindings.any { it !== binding && it.origin == binding.origin && it.destination == binding.destination && it.sourceKey == sourceKey }) return
        anchor.reconnectSource(binding.source)
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
                    binding.captureAnchor()?.let {
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

    fun reentryAt(key: NavKey, host: LayoutCoordinates, positionInWindow: Offset): ((Offset) -> Unit)? {
        val anchor = anchors[key]?.value ?: return null
        val source = anchor.source ?: return null
        val originStack = backStack.toList()
        val origin = originStack.lastOrNull() ?: return null
        if (key in originStack) return null
        val binding = bindings.firstOrNull { it.source === source && it.origin == origin && it.destination == key } ?: return null
        if (!source.containsVisiblePoint(host, positionInWindow, binding.corners)) return null
        return reopen@{ releaseInWindow ->
            if (binding !in bindings || backStack.toList() != originStack || !source.containsVisiblePoint(host, releaseInWindow, binding.corners)) {
                return@reopen
            }
            // A valid press can outlive the exiting entry and its retained drawing.
            val current = anchors[key]?.value
            when {
                current === anchor -> anchor.updateOrigin()
                current == null -> binding.captureAnchor()?.let { anchorState(key).value = it }
                else -> return@reopen
            }
            backStack.add(key)
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
    val sourceKey: String? = null,
) {
    fun captureAnchor(): NavTransitionAnchor? = source.capture(cornerRadius, containerColor, corners)?.also {
        it.sourceOrigin = origin
        it.sourceKey = sourceKey
    }
}
