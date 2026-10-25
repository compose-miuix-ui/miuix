// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.window.internal.DesktopOS
import top.yukonga.miuix.kmp.window.platform.linux.input.linuxCaptionBarInput
import top.yukonga.miuix.kmp.window.platform.windows.windowsCaptionBarTouchInput

/**
 * A spacer placed between title bar background content and clickable components.
 * It coordinates hit-testing for window drag operations without blocking underlying or overlay clickable elements.
 *
 * Each window may have **zero or one** active [WindowDraggableArea]. A second instance in the same
 * window throws [IllegalStateException], even when the instances are in different layout branches.
 * Child [MiuixWindow] and [MiuixDialogWindow] instances have independent registrations and may each
 * have their own region. Leaving the composition releases the registration for that window.
 *
 * Keep this configuration outside `AnimatedContent`, `Crossfade`, or overlapping
 * `AnimatedVisibility` branches. Outgoing content still occupies its registration until it
 * leaves the composition, so incoming and outgoing regions count as two active instances.
 *
 * @throws IllegalStateException If used outside a MiuixWindow/MiuixDialogWindow or more than once in the same window.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WindowDraggableArea(
    modifier: Modifier = Modifier,
) {
    val registration = LocalWindowDraggableAreaRegistration.current
    val windowInfo = LocalMiuixWindowInfo.current
    val isHitTestInCaptionBarState = LocalIsHitTestInCaptionBarState.current
    val properties = LocalMiuixWindowProperties.current

    // Register during apply: abandoned recompositions must not claim the slot. The keys
    // release the old window resources if this content changes window scope.
    DisposableEffect(registration, isHitTestInCaptionBarState) {
        check(!registration.isRegistered) {
            "Only one WindowDraggableArea may be active in a MiuixWindow scope. " +
                "Popup, Dialog, and non-Miuix child windows inherit the host's " +
                "registration instead of creating their own."
        }
        registration.isRegistered = true
        onDispose {
            registration.isRegistered = false
            isHitTestInCaptionBarState.value = false
        }
    }

    if (windowInfo.captionBarHeight <= 0.dp) return
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(windowInfo.captionBarHeight)
            .run {
                when {
                    DesktopOS.isWindows() -> windowsCaptionBarTouchInput(properties.moveable)
                    DesktopOS.isLinux() -> linuxCaptionBarInput()
                    else -> this
                }
            }
            .onPointerEvent(PointerEventType.Enter) {
                isHitTestInCaptionBarState.value = true
            }
            .onPointerEvent(PointerEventType.Exit) {
                isHitTestInCaptionBarState.value = false
            },
    )
}

// Each Miuix window provides a fresh registration inside its native window content, so child
// windows do not inherit their parent's registration. This is not observable composition state.
internal class WindowDraggableAreaRegistration {
    var isRegistered = false
}

internal val LocalWindowDraggableAreaRegistration =
    staticCompositionLocalOf<WindowDraggableAreaRegistration> {
        error("WindowDraggableArea requires content hosted by a MiuixWindow or MiuixDialogWindow.")
    }
