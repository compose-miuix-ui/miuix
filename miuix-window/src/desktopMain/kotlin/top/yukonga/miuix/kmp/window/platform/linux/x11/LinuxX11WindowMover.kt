// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.x11

import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.platform.unix.X11
import com.sun.jna.ptr.IntByReference
import com.sun.jna.ptr.NativeLongByReference
import com.sun.jna.ptr.PointerByReference
import java.awt.Frame
import java.awt.Point
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
     * Qt's XCB touch handoff is supported by KWin and Openbox. Check the actual WM and
     * protocol before leaving a touch undecided; session environment variables are not enough.
     */
    fun supportsTouchMove(
        display: X11.Display,
        window: X11.Window,
    ): Boolean {
        if (!LinuxAwtInput.isAvailable) return false
        val x11 = LinuxX11.INSTANCE
        val root = queryRootWindow(x11, display, window) ?: return false
        val wm = readProperty(
            display,
            root,
            "_NET_SUPPORTING_WM_CHECK",
            X11.XA_WINDOW,
            32,
        ) { data, count ->
            if (count == 1) data.getNativeLong(0).toLong() else null
        } ?: return false

        val utf8 = x11.XInternAtom(display, "UTF8_STRING", false)
        val name = readProperty(
            display,
            X11.Window(wm),
            "_NET_WM_NAME",
            utf8,
            8,
        ) { data, count ->
            String(data.getByteArray(0, count), Charsets.UTF_8).lowercase()
        }

        if (name != "kwin" && name != "openbox") return false
        val moveresize = x11.XInternAtom(display, "_NET_WM_MOVERESIZE", false).toLong()
        return readProperty(
            display,
            root,
            "_NET_SUPPORTED",
            X11.XA_ATOM,
            32,
        ) { data, count ->
            (0 until count).any {
                data.getNativeLong(it.toLong() * NativeLong.SIZE).toLong() == moveresize
            }
        } == true
    }

    private fun <T> readProperty(
        display: X11.Display,
        window: X11.Window,
        name: String,
        expectedType: X11.Atom,
        expectedFormat: Int,
        read: (Pointer, Int) -> T,
    ): T? {
        val x11 = LinuxX11.INSTANCE
        val type = X11.AtomByReference()
        val format = IntByReference()
        val count = NativeLongByReference()
        val remaining = NativeLongByReference()
        val data = PointerByReference()
        val result = x11.XGetWindowProperty(
            display,
            window,
            x11.XInternAtom(display, name, false),
            NativeLong(0),
            NativeLong(4096),
            false,
            expectedType,
            type,
            format,
            count,
            remaining,
            data,
        )

        try {
            if (result != X11.Success || type.value != expectedType ||
                format.value != expectedFormat || remaining.value.toLong() != 0L
            ) {
                return null
            }
            return data.value?.let { read(it, count.value.toInt()) }
        } finally {
            data.value?.let { x11.XFree(it) }
        }
    }

    /**
     * Acquires the connection and validates the request before touch ownership is handed off.
     * The caller owns the returned move and must close it on release or cancellation.
     */
    fun prepare(
        window: Window,
        start: Point,
        current: Point,
        fromTouch: Boolean = false,
    ): Move? {
        if (!window.isDisplayable || !LinuxAwtInput.isAvailable) return null
        val x11 = try {
            LinuxX11.INSTANCE
        } catch (_: UnsatisfiedLinkError) {
            return null
        }

        val display = x11.XOpenDisplay(null) ?: return null
        var prepared: Move? = null
        try {
            val handle = Native.getComponentID(window)
            if (handle == 0L) return null
            val target = X11.Window(handle)
            val root = queryRootWindow(x11, display, target) ?: return null
            // The WM restores from the original caption press. For an ordinary touch move,
            // rebase at handoff so motion consumed by touch slop does not jump the window.
            val maximized = (window as? Frame)?.extendedState == Frame.MAXIMIZED_BOTH
            val anchor = if (fromTouch && !maximized) current else start
            val position = rootPosition(
                display = display,
                target = target,
                window = window,
                screenPosition = anchor,
                fromTouch = fromTouch,
            ) ?: return null
            val atom = x11.XInternAtom(display, "_NET_WM_MOVERESIZE", false)
            prepared = Move(
                window = window,
                display = display,
                target = target,
                root = root,
                atom = atom,
                position = position,
                fromTouch = fromTouch,
            )
            return prepared
        } catch (_: Exception) {
            return null
        } finally {
            if (prepared == null) x11.XCloseDisplay(display)
        }
    }

    private fun rootPosition(
        display: X11.Display,
        target: X11.Window,
        window: Window,
        screenPosition: Point,
        fromTouch: Boolean,
    ): Point? {
        val rootX = IntByReference()
        val rootY = IntByReference()
        val localX = IntByReference()
        val localY = IntByReference()
        val buttons = IntByReference()
        val found = LinuxX11.INSTANCE.XQueryPointer(
            display,
            target,
            X11.WindowByReference(),
            X11.WindowByReference(),
            rootX,
            rootY,
            localX,
            localY,
            buttons,
        )

        if (!found || (!fromTouch && buttons.value and X11.Button1Mask == 0)) return null

        // Anchor at the event in root pixels, independently of queued pointer motion and HiDPI.
        val scale = window.graphicsConfiguration.defaultTransform
        return Point(
            rootX.value - localX.value + ((screenPosition.x - window.x) * scale.scaleX).toInt(),
            rootY.value - localY.value + ((screenPosition.y - window.y) * scale.scaleY).toInt(),
        )
    }

    /**
     * A prepared WM request. Start and cancellation share one X connection to preserve ordering.
     * A successful start means the request was sent, not an acknowledgement from the WM.
     */
    class Move(
        private val window: Window,
        private val display: X11.Display,
        private val target: X11.Window,
        private val root: X11.Window,
        private val atom: X11.Atom,
        private val position: Point,
        private val fromTouch: Boolean,
    ) : AutoCloseable {
        var started = false
            private set
        private var closed = false

        fun start(): Boolean {
            if (closed || started || !window.isDisplayable) return false
            return try {
                if (Native.getComponentID(window) != target.toLong()) return false
                LinuxAwtInput.releasePointer()
                started = send(NET_WM_MOVERESIZE_MOVE)
                started
            } catch (_: Exception) {
                false
            }
        }

        private fun send(
            direction: Long,
        ): Boolean {
            val event = buildMoveResizeEvent(
                display = display,
                xWindow = target,
                atom = atom,
                mouseX = position.x,
                mouseY = position.y,
                // RejectTouch can clear Button1. RawTouchEnd ends this buttonless WM move.
                button = if (fromTouch) 0 else MouseEvent.BUTTON1,
                direction = direction,
            )
            val sent = LinuxX11.INSTANCE.XSendEvent(
                display,
                root,
                0,
                NativeLong((X11.SubstructureRedirectMask or X11.SubstructureNotifyMask).toLong()),
                event,
            )
            LinuxX11.INSTANCE.XFlush(display)
            return sent != 0
        }

        /**
         * Releases this connection but lets the WM finish the drag. Cancelling an active move
         * makes the WM restore the window's start position.
         */
        fun detach() {
            started = false
            close()
        }

        override fun close() {
            if (closed) return
            closed = true
            try {
                if (started) send(11L)
            } finally {
                started = false
                LinuxX11.INSTANCE.XCloseDisplay(display)
            }
        }
    }

    private fun queryRootWindow(
        x11: LinuxX11,
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
        button: Int,
        direction: Long = NET_WM_MOVERESIZE_MOVE,
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
        clientMsg.data.l[2] = NativeLong(direction)
        clientMsg.data.l[3] = NativeLong(button.toLong())
        // Source indication (Normal application)
        clientMsg.data.l[4] = NativeLong(1)

        return event
    }
}
