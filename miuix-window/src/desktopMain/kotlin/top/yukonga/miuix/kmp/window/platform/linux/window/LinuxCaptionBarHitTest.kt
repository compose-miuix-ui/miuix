// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.window

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.tooling.ComposeToolingApi
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import java.awt.Window

internal val LocalLinuxCaptionBarHitTest =
    staticCompositionLocalOf<LinuxCaptionBarHitTest?> { null }

/**
 * Shared mouse/touch policy for caption geometry, window buttons and modal content.
 * Layout geometry stays available even when a Dialog intercepts caption hover and press events.
 */
internal class LinuxCaptionBarHitTest {
    enum class Result {
        Caption,
        Excluded,
        RequiresComposeHitTest,
    }

    class Region {
        var bounds = Rect.Zero
    }

    val regions = mutableSetOf<Region>()
    var buttonsBounds = Rect.Zero

    @OptIn(ComposeToolingApi::class)
    fun hitTest(
        position: Offset,
        window: Window,
    ): Result {
        if (regions.none { it.bounds.contains(position) } || buttonsBounds.contains(position)) {
            return Result.Excluded
        }
        val owners = when (window) {
            is ComposeWindow -> window.semanticsOwners
            is ComposeDialog -> window.semanticsOwners.drop(1)
            else -> return Result.RequiresComposeHitTest
        }
        // Keep normal Compose hit testing for custom controls layered above CaptionBarHitTest.
        // For a modal scene, use its public dialog semantics rather than changing Dialog itself.
        val dialog = owners.toList().asReversed().firstNotNullOfOrNull {
            findDialog(it.rootSemanticsNode)
        }
        if (dialog == null) return Result.RequiresComposeHitTest
        // Dialog's semantic root fills the window. Its direct layout children bound the content.
        val isDialogContent = dialog.children.any { child ->
            var content = child.layoutInfo
            while (content.parentInfo != null && content.parentInfo !== dialog.layoutInfo) {
                content = content.parentInfo!!
            }
            content.isAttached && content.coordinates.boundsInWindow().contains(position)
        }
        return if (isDialogContent) Result.Excluded else Result.Caption
    }

    private fun findDialog(
        node: SemanticsNode,
    ): SemanticsNode? {
        if (node.config.contains(SemanticsProperties.IsDialog)) return node
        return node.children.firstNotNullOfOrNull(::findDialog)
    }
}
