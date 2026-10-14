// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.ui.geometry.Offset
import top.yukonga.miuix.kmp.window.internal.findSkiaLayer
import java.awt.AWTEvent
import java.awt.Component
import java.awt.EventQueue
import java.awt.Toolkit
import java.awt.Window
import java.awt.event.MouseEvent
import java.awt.event.WindowEvent
import javax.swing.SwingUtilities

/**
 * Routes non-client mouse gestures before Compose dispatches them to a modal scene.
 * Consuming a MouseEvent in an AWT listener alone does not stop other listeners receiving it.
 */
internal object LinuxWindowMouseInput {
    interface Handler {
        fun onMouseEvent(event: MouseEvent): Boolean

        fun afterMouseEvent(event: MouseEvent) {}

        fun cancel() {}
    }

    private data class Registration(
        val handler: Handler,
        val priority: Int,
    )

    private val handlers = mutableMapOf<Window, MutableList<Registration>>()
    private var queue: WindowEventQueue? = null

    fun register(
        window: Window,
        handler: Handler,
        priority: Int = 0,
    ): () -> Unit {
        check(EventQueue.isDispatchThread())
        if (queue == null) {
            queue = WindowEventQueue().also {
                Toolkit.getDefaultToolkit().systemEventQueue.push(it)
            }
        }
        val registration = Registration(handler, priority)
        handlers.getOrPut(window) { mutableListOf() }.apply {
            add(registration)
            sortByDescending { it.priority }
        }
        return {
            check(EventQueue.isDispatchThread())
            handler.cancel()
            handlers[window]?.let {
                it.remove(registration)
                if (it.isEmpty()) handlers.remove(window)
            }
            if (handlers.isEmpty()) queue?.uninstallIfCurrent()
        }
    }

    fun positionInContent(event: MouseEvent, window: Window): Offset {
        // Compose coordinates start at its canvas, which can be inset by AWT decoration.
        val canvas = window.findSkiaLayer()?.canvas ?: window
        val point = SwingUtilities.convertPoint(event.component, event.point, canvas)
        val transform = window.graphicsConfiguration.defaultTransform
        return Offset(
            (point.x * transform.scaleX).toFloat(),
            (point.y * transform.scaleY).toFloat(),
        )
    }

    fun positionInWindow(event: MouseEvent, window: Window): Offset {
        val transform = window.graphicsConfiguration.defaultTransform
        return Offset(
            ((event.xOnScreen - window.x) * transform.scaleX).toFloat(),
            ((event.yOnScreen - window.y) * transform.scaleY).toFloat(),
        )
    }

    private class WindowEventQueue : EventQueue() {
        override fun dispatchEvent(event: AWTEvent) {
            if (event is WindowEvent &&
                (
                    event.id == WindowEvent.WINDOW_DEACTIVATED ||
                        event.id == WindowEvent.WINDOW_CLOSED
                    )
            ) {
                handlers[event.window]?.toList()?.forEach { it.handler.cancel() }
            }
            val mouseEvent = event as? MouseEvent
            val component = mouseEvent?.source as? Component
            val window = component as? Window ?: component?.let(SwingUtilities::getWindowAncestor)
            val registrations = handlers[window]?.toList()
            try {
                if (mouseEvent == null || window == null || registrations.isNullOrEmpty()) {
                    super.dispatchEvent(event)
                    return
                }
                if (!window.isShowing || !window.isEnabled) {
                    registrations.forEach { it.handler.cancel() }
                    super.dispatchEvent(event)
                    return
                }
                if (registrations.any { it.handler.onMouseEvent(mouseEvent) }) {
                    mouseEvent.consume()
                } else {
                    super.dispatchEvent(event)
                }
                registrations.forEach { it.handler.afterMouseEvent(mouseEvent) }
            } catch (e: IllegalArgumentException) {
                if (e.message?.contains("RootNodeOwner is already disposed") == true) {
                    return
                }
                throw e
            }
        }

        fun uninstallIfCurrent() {
            // Do not pop an event queue installed above ours by another library.
            if (Toolkit.getDefaultToolkit().systemEventQueue === this) {
                pop()
                queue = null
            }
        }
    }
}
