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
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
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
        var useNativeDrag = true
        var restoreWidth = window.width
        val handler = object : LinuxWindowMouseInput.Handler {
            private var pressed = false
            private var suppressClick = false
            private var pendingDragEvent: MouseEvent? = null
            private var latestDragEvent: MouseEvent? = null
            private var restoreDragOffset: Point? = null

            fun onWindowResized() {
                if ((window as? Frame)?.extendedState == Frame.MAXIMIZED_BOTH) return
                restoreWidth = window.width
                val offset = restoreDragOffset ?: return
                val event = latestDragEvent ?: return
                restoreDragOffset = null
                // Wait for the native restore to complete before positioning the restored window.
                // Moving it while maximized overwrites the window manager's saved geometry.
                window.setLocation(event.xOnScreen - offset.x, event.yOnScreen - offset.y)
                startMover(event)
            }

            private fun startMover(event: MouseEvent) {
                if (useNativeDrag && LinuxX11WindowMover.tryMove(window, event)) return
                useNativeDrag = false
                fallbackMover.startDrag(event)
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
                        latestDragEvent = event
                        if (restoreDragOffset != null) return true
                        pendingDragEvent?.let { startEvent ->
                            pendingDragEvent = null
                            val frame = window as? Frame
                            if (frame?.extendedState == Frame.MAXIMIZED_BOTH) {
                                val localX = startEvent.xOnScreen - window.x
                                val localY = startEvent.yOnScreen - window.y
                                restoreDragOffset = Point(
                                    (restoreWidth * localX.toFloat() / window.width).toInt(),
                                    localY,
                                )
                                frame.extendedState = Frame.NORMAL
                                return true
                            }
                            startMover(startEvent)
                        }
                        fallbackMover.updateDrag(event)
                        return true
                    }

                    MouseEvent.MOUSE_RELEASED -> {
                        if (!pressed) return false
                        if (event.button == MouseEvent.BUTTON1) {
                            pressed = false
                            pendingDragEvent = null
                            latestDragEvent = null
                            restoreDragOffset = null
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
                latestDragEvent = null
                restoreDragOffset = null
                fallbackMover.endDrag()
            }
        }
        val componentListener = object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent) {
                handler.onWindowResized()
            }
        }
        window.addComponentListener(componentListener)
        val unregister = LinuxWindowMouseInput.register(window, handler)
        onDispose {
            unregister()
            window.removeComponentListener(componentListener)
        }
    }
}
