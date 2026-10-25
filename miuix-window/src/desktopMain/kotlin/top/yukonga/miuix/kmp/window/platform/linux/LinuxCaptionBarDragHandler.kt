// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import java.awt.Frame
import java.awt.Point
import java.awt.Window
import java.awt.event.MouseEvent

/**
 * Handles caption gestures before a modal Compose scene can consume them.
 * Native X11 movement retains window-manager snapping, with an AWT fallback.
 */

@Composable
internal fun LinuxCaptionBarDragHandler(
    window: Window,
    hitTest: LinuxCaptionBarHitTest,
    isMoveable: Boolean,
    isHitTestInCaptionBar: Boolean,
    canDrag: Boolean = true,
    canMaximize: Boolean = false,
) {
    val currentIsMoveable by rememberUpdatedState(isMoveable)
    val currentIsHitTestInCaptionBar by rememberUpdatedState(isHitTestInCaptionBar)
    val currentCanDrag by rememberUpdatedState(canDrag)
    val currentCanMaximize by rememberUpdatedState(canMaximize)

    DisposableEffect(window, hitTest) {
        val fallbackMover = LinuxFallbackWindowMover(window)
        val uninstallFallback = fallbackMover.install()
        var useNativeDrag = true
        val handler = object : LinuxWindowMouseInput.Handler {
            private var pressed = false
            private var suppressClick = false
            private var pendingDragEvent: MouseEvent? = null

            private fun startMover(startEvent: MouseEvent, currentEvent: MouseEvent) {
                if (useNativeDrag && LinuxX11WindowMover.tryMove(window, startEvent)) return
                useNativeDrag = false
                fallbackMover.startDrag(
                    start = Point(startEvent.xOnScreen, startEvent.yOnScreen),
                    current = Point(currentEvent.xOnScreen, currentEvent.yOnScreen),
                )
            }

            override fun onMouseEvent(event: MouseEvent): Boolean {
                when (event.id) {
                    MouseEvent.MOUSE_PRESSED -> {
                        if (event.button != MouseEvent.BUTTON1) return pressed
                        cancel()
                        if (!currentIsMoveable || !currentCanDrag) return false
                        if (!hitTest.contains(
                                position = LinuxWindowMouseInput.positionInContent(event, window),
                                window = window,
                                isHovered = currentIsHitTestInCaptionBar,
                            )
                        ) {
                            return false
                        }
                        pressed = true
                        pendingDragEvent = event
                        return true
                    }

                    MouseEvent.MOUSE_DRAGGED -> {
                        if (!pressed) return false
                        pendingDragEvent?.let { startEvent ->
                            pendingDragEvent = null
                            startMover(startEvent, event)
                        }
                        fallbackMover.updateDrag(Point(event.xOnScreen, event.yOnScreen))
                        return true
                    }

                    MouseEvent.MOUSE_RELEASED -> {
                        if (!pressed) return false
                        if (event.button == MouseEvent.BUTTON1) {
                            pressed = false
                            pendingDragEvent = null
                            fallbackMover.endDrag()
                            suppressClick = true
                        }
                        return true
                    }

                    MouseEvent.MOUSE_CLICKED -> {
                        if (event.button != MouseEvent.BUTTON1 || !suppressClick) return false
                        suppressClick = false
                        if (event.clickCount == 2 && currentCanDrag && currentCanMaximize) {
                            (window as? Frame)?.let { frame ->
                                frame.extendedState =
                                    if (frame.extendedState == Frame.MAXIMIZED_BOTH) {
                                        Frame.NORMAL
                                    } else {
                                        Frame.MAXIMIZED_BOTH
                                    }
                            }
                        }
                        return true
                    }
                }
                return false
            }

            override fun cancel() {
                pressed = false
                suppressClick = false
                pendingDragEvent = null
                fallbackMover.endDrag()
            }
        }
        val unregister = LinuxWindowMouseInput.register(window, handler)
        onDispose {
            unregister()
            uninstallFallback()
        }
    }
}
