// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.platform.unix.X11
import com.sun.jna.ptr.IntByReference
import com.sun.jna.ptr.PointerByReference
import java.awt.Window
import java.awt.event.MouseEvent

/**
 * Native X11 window mover using `_NET_WM_MOVERESIZE` protocol.
 *
 * Delegates window dragging to the window manager, providing smooth movement
 * and native features like window snapping.
 */

internal object LinuxX11WindowMover {
    private const val NET_WM_MOVERESIZE_MOVE = 8L

    /**
     * Try to initiate a native X11 window move.
     *
     * @return `true` if the native move was successfully initiated, `false` otherwise
     * (e.g., on Wayland or if X11 calls fail).
     */
    fun tryMove(window: Window, startEvent: MouseEvent): Boolean {
        if (!window.isDisplayable) return false

        val x11: X11Ext
        try {
            x11 = X11Ext.INSTANCE
        } catch (_: UnsatisfiedLinkError) {
            return false
        }

        val display = x11.XOpenDisplay(null) ?: return false

        try {
            val windowId = Native.getComponentID(window)
            if (windowId == 0L) return false
            val xWindow = X11.Window(windowId)

            val rootWindow = queryRootWindow(x11, display, xWindow) ?: return false
            val netWmMoveresize = x11.XInternAtom(display, "_NET_WM_MOVERESIZE", false)
            if (netWmMoveresize == null || netWmMoveresize.toLong() == 0L) return false

            // EWMH requires X11 root pixels, not AWT's logical screen coordinates on HiDPI.
            val rootX = IntByReference()
            val rootY = IntByReference()
            val windowX = IntByReference()
            val windowY = IntByReference()
            if (!x11.XQueryPointer(
                    display,
                    xWindow,
                    X11.WindowByReference(),
                    X11.WindowByReference(),
                    rootX,
                    rootY,
                    windowX,
                    windowY,
                    IntByReference(),
                )
            ) {
                return false
            }

            // Anchor the operation at the initiating event. The pointer may already have moved
            // while AWT queued the event or JNA loaded X11, especially on the first drag.
            val position = LinuxWindowMouseInput.positionInWindow(startEvent, window)
            val event = buildMoveResizeEvent(
                display = display,
                xWindow = xWindow,
                atom = netWmMoveresize,
                mouseX = rootX.value - windowX.value + position.x.toInt(),
                mouseY = rootY.value - windowY.value + position.y.toInt(),
            )

            // Release AWT exclusive mouse lock to allow window manager takeover
            x11.XUngrabPointer(display, NativeLong(0))

            val mask = NativeLong(
                (X11.SubstructureRedirectMask or X11.SubstructureNotifyMask).toLong(),
            )
            x11.XSendEvent(display, rootWindow, 0, mask, event)
            x11.XFlush(display)

            return true
        } catch (_: Exception) {
            return false
        } finally {
            x11.XCloseDisplay(display)
        }
    }

    private fun queryRootWindow(
        x11: X11Ext,
        display: X11.Display,
        xWindow: X11.Window,
    ): X11.Window? {
        val rootRet = X11.WindowByReference()
        val parentRet = X11.WindowByReference()
        val childrenRet = PointerByReference()
        val numChildren = IntByReference()

        x11.XQueryTree(display, xWindow, rootRet, parentRet, childrenRet, numChildren)

        if (childrenRet.value != null) {
            x11.XFree(childrenRet.value)
        }

        return rootRet.value
    }

    private fun buildMoveResizeEvent(
        display: X11.Display,
        xWindow: X11.Window,
        atom: X11.Atom,
        mouseX: Int,
        mouseY: Int,
    ): X11.XEvent {
        val event = X11.XEvent()
        event.type = X11.ClientMessage
        event.setType(X11.XClientMessageEvent::class.java)

        val clientMsg = event.xclient
        clientMsg.type = X11.ClientMessage
        clientMsg.serial = NativeLong(0)
        clientMsg.send_event = 1
        clientMsg.display = display
        clientMsg.window = xWindow
        clientMsg.message_type = atom
        clientMsg.format = 32

        clientMsg.data.setType("l")
        // x_root
        clientMsg.data.l[0] = NativeLong(mouseX.toLong())
        // y_root
        clientMsg.data.l[1] = NativeLong(mouseY.toLong())
        // Direction
        clientMsg.data.l[2] = NativeLong(NET_WM_MOVERESIZE_MOVE)
        // Button (Left click)
        clientMsg.data.l[3] = NativeLong(1)
        // Source indication (Normal application)
        clientMsg.data.l[4] = NativeLong(1)

        return event
    }
}

/**
 * JNA's default X11 interface is missing some methods.
 *
 * This interface extends it to bind the missing XUngrabPointer function.
 */

@Suppress("FunctionName")
internal interface X11Ext : X11 {
    fun XUngrabPointer(display: X11.Display?, time: NativeLong?): Int

    companion object {
        val INSTANCE: X11Ext = Native.load("X11", X11Ext::class.java)
    }
}
