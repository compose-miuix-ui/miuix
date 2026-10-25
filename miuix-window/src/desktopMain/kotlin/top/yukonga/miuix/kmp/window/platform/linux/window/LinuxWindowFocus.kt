// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.window

import com.sun.jna.Memory
import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.platform.unix.X11
import com.sun.jna.ptr.IntByReference
import com.sun.jna.ptr.NativeLongByReference
import com.sun.jna.ptr.PointerByReference
import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxX11
import java.awt.AWTEvent
import java.awt.Component
import java.awt.Toolkit
import java.awt.Window
import java.awt.event.AWTEventListener
import java.awt.event.MouseEvent
import javax.swing.SwingUtilities

/**
 * Requests window-manager activation with the X11 input time shared by mouse and touch presses.
 */
internal class LinuxWindowFocus private constructor(
    private val window: Window,
    private val x11: LinuxX11,
    private val display: X11.Display,
) : AutoCloseable {
    private val userTime = x11.XInternAtom(
        display,
        "_NET_WM_USER_TIME",
        false,
    )
    private val activeWindow = x11.XInternAtom(
        display,
        "_NET_ACTIVE_WINDOW",
        false,
    )
    private val listener = AWTEventListener { event ->
        if (event.id == MouseEvent.MOUSE_PRESSED) {
            val component = event.source as? Component ?: return@AWTEventListener
            onPointerPress(component)
        }
    }

    /**
     * Requests activation for a pointer press in this window, leaving the decision to the WM.
     *
     * @param timestamp The original X11 server time. When omitted, reads the time JBR recorded
     * on the source's native child window. JVM and Compose event clocks cannot be used here.
     */
    fun onPointerPress(
        component: Component,
        timestamp: Long? = null,
    ) {
        if (SwingUtilities.getWindowAncestor(component) !== window ||
            !window.isShowing || !window.isEnabled || !window.isFocusableWindow
        ) {
            return
        }
        try {
            val inputTime = timestamp ?: run {
                val nativeComponent = generateSequence(component) { it.parent }
                    .firstOrNull { !it.isLightweight } ?: return
                if (!nativeComponent.isDisplayable) return
                val source = X11.Window(Native.getComponentID(nativeComponent))
                readUserTime(source) ?: return
            }
            if (inputTime == 0L) return
            val target = X11.Window(Native.getComponentID(window))

            // JBR records mouse input on its child window; XI2 touch supplies the server time
            // directly. Publish either source on the top-level window read by the WM.
            Memory(NativeLong.SIZE.toLong()).use { data ->
                data.setNativeLong(0, NativeLong(inputTime))
                x11.XChangeProperty(
                    display,
                    target,
                    userTime,
                    X11.XA_CARDINAL,
                    32,
                    X11.PropModeReplace,
                    data,
                    1,
                )
            }

            // AWT's focus request may have been refused. Always ask the WM with this press's
            // time, including during the asynchronous FocusIn/FocusOut handoff.
            requestActivation(target, inputTime)
            x11.XFlush(display)
        } catch (exception: Exception) {
            log.log(
                System.Logger.Level.WARNING,
                "Failed to update the Linux window input time",
                exception,
            )
        }
    }

    private fun readUserTime(
        source: X11.Window,
    ): Long? {
        val type = X11.AtomByReference()
        val format = IntByReference()
        val count = NativeLongByReference()
        val remaining = NativeLongByReference()
        val data = PointerByReference()
        val result = x11.XGetWindowProperty(
            display,
            source,
            userTime,
            NativeLong(0),
            NativeLong(1),
            false,
            X11.XA_CARDINAL,
            type,
            format,
            count,
            remaining,
            data,
        )
        try {
            if (result != X11.Success || type.value != X11.XA_CARDINAL ||
                format.value != 32 || count.value.toLong() != 1L
            ) {
                return null
            }
            return data.value?.getNativeLong(0)?.toLong()?.and(0xffffffffL)
                ?.takeIf { it != 0L }
        } finally {
            data.value?.let { x11.XFree(it) }
        }
    }

    private fun requestActivation(
        target: X11.Window,
        timestamp: Long,
    ) {
        val event = X11.XEvent()
        event.setType(X11.XClientMessageEvent::class.java)
        event.xclient.apply {
            type = X11.ClientMessage
            this.display = this@LinuxWindowFocus.display
            window = target
            message_type = activeWindow
            format = 32
            data.setType("l")
            data.l[0] = NativeLong(1) // 普通应用请求，保留窗口管理器的焦点防抢占策略
            data.l[1] = NativeLong(timestamp)
            data.l[2] = NativeLong(0)
            data.l[3] = NativeLong(0)
            data.l[4] = NativeLong(0)
        }
        x11.XSendEvent(
            display,
            x11.XDefaultRootWindow(display),
            0,
            NativeLong((X11.SubstructureRedirectMask or X11.SubstructureNotifyMask).toLong()),
            event,
        )
    }

    override fun close() {
        Toolkit.getDefaultToolkit().removeAWTEventListener(listener)
        x11.XCloseDisplay(display)
    }

    companion object {
        private val log = System.getLogger(LinuxWindowFocus::class.java.name)

        fun install(
            window: Window,
        ): LinuxWindowFocus? {
            if (Toolkit.getDefaultToolkit().javaClass.name != "sun.awt.X11.XToolkit") return null
            return try {
                val x11 = LinuxX11.INSTANCE
                val display = x11.XOpenDisplay(null) ?: return null
                try {
                    LinuxWindowFocus(
                        window,
                        x11,
                        display,
                    ).also {
                        Toolkit.getDefaultToolkit().addAWTEventListener(
                            it.listener,
                            AWTEvent.MOUSE_EVENT_MASK,
                        )
                    }
                } catch (failure: Throwable) {
                    x11.XCloseDisplay(display)
                    throw failure
                }
            } catch (exception: Exception) {
                log.log(
                    System.Logger.Level.WARNING,
                    "Failed to install Linux window focus handling",
                    exception,
                )
                null
            } catch (error: LinkageError) {
                log.log(
                    System.Logger.Level.WARNING,
                    "X11 window focus handling is unavailable",
                    error,
                )
                null
            }
        }
    }
}
