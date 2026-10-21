// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import com.sun.jna.Memory
import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.platform.unix.X11
import com.sun.jna.ptr.IntByReference
import com.sun.jna.ptr.NativeLongByReference
import com.sun.jna.ptr.PointerByReference
import java.awt.AWTEvent
import java.awt.Component
import java.awt.Toolkit
import java.awt.Window
import java.awt.event.AWTEventListener
import java.awt.event.MouseEvent
import javax.swing.SwingUtilities

/**
 * 将 AWT 原生子窗口的输入时间同步到顶层窗口，供窗口管理器判断用户主动激活
 */
internal class LinuxWindowFocus private constructor(
    private val window: Window,
    private val x11: X11,
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
            try {
                handleInput(event)
            } catch (exception: Exception) {
                log.log(
                    System.Logger.Level.WARNING,
                    "Failed to update the Linux window input time",
                    exception,
                )
            }
        }
    }

    private fun handleInput(event: AWTEvent) {
        val component = event.source as? Component ?: return
        if (SwingUtilities.getWindowAncestor(component) !== window ||
            !window.isShowing || !window.isEnabled || !window.isFocusableWindow
        ) {
            return
        }

        val nativeComponent = generateSequence(component) { it.parent }
            .firstOrNull { !it.isLightweight } ?: return
        if (!nativeComponent.isDisplayable) return
        val source = X11.Window(Native.getComponentID(nativeComponent))
        val target = X11.Window(Native.getComponentID(window))
        val timestamp = readUserTime(source) ?: return

        // JBR 将 Skia Canvas 的点击时间写在子窗口上，KWin 只读取顶层窗口
        // 使用 X11 原始事件时间，不能用 MouseEvent.when 的 Unix 时间或当前时间代替
        Memory(NativeLong.SIZE.toLong()).use { data ->
            data.setNativeLong(0, NativeLong(timestamp))
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

        // AWT 的直接焦点请求可能已被拒绝，携带本次点击时间重新交由 WM 决定
        // 即使 AWT 暂时报告已聚焦也需要发送，避免 FocusIn/FocusOut 异步交接的竞态
        requestActivation(target, timestamp)
        x11.XFlush(display)
    }

    private fun readUserTime(source: X11.Window): Long? {
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

    private fun requestActivation(target: X11.Window, timestamp: Long) {
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

        fun install(window: Window): LinuxWindowFocus? {
            if (Toolkit.getDefaultToolkit().javaClass.name != "sun.awt.X11.XToolkit") return null
            return try {
                val x11 = X11.INSTANCE
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
