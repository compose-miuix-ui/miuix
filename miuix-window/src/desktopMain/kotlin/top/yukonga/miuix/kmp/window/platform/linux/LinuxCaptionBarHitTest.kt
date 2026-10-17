// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.tooling.ComposeToolingApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import java.awt.Window

internal val LocalLinuxCaptionBarHitTest =
    staticCompositionLocalOf<LinuxCaptionBarHitTest?> { null }

/**
 * Layout geometry stays available even when a Dialog stops hover events reaching the caption.
 */
internal class LinuxCaptionBarHitTest {
    class Region {
        var bounds = Rect.Zero
    }

    val regions = mutableSetOf<Region>()
    var buttonsBounds = Rect.Zero

    @OptIn(ComposeToolingApi::class)
    fun contains(
        position: Offset,
        window: Window,
        isHovered: Boolean,
    ): Boolean {
        if (regions.none { it.bounds.contains(position) } || buttonsBounds.contains(position)) {
            return false
        }
        val owners = when (window) {
            is ComposeWindow -> window.semanticsOwners
            is ComposeDialog -> window.semanticsOwners
            else -> return isHovered
        }
        // Keep normal Compose hit testing for custom controls layered above WindowDraggableArea.
        // For a modal scene, use its public dialog semantics rather than changing Dialog itself.
        val dialog = owners.toList().asReversed().firstNotNullOfOrNull {
            findDialog(it.rootSemanticsNode)
        }
        if (dialog == null) return isHovered
        // Dialog's semantic root fills the window. Its direct layout children bound the content.
        return dialog.children.none { child ->
            var content = child.layoutInfo
            while (content.parentInfo != null && content.parentInfo !== dialog.layoutInfo) {
                content = content.parentInfo!!
            }
            content.isAttached && content.coordinates.boundsInWindow().contains(position)
        }
    }

    private fun findDialog(node: SemanticsNode): SemanticsNode? {
        if (node.config.contains(SemanticsProperties.IsDialog)) return node
        return node.children.firstNotNullOfOrNull(::findDialog)
    }
}

@Composable
internal fun Modifier.linuxCaptionBarHitTest(): Modifier {
    val hitTest = LocalLinuxCaptionBarHitTest.current ?: return this
    val region = remember(hitTest) { LinuxCaptionBarHitTest.Region() }
    DisposableEffect(hitTest, region) {
        hitTest.regions.add(region)
        onDispose { hitTest.regions.remove(region) }
    }
    return onGloballyPositioned { region.bounds = it.boundsInWindow() }
}
