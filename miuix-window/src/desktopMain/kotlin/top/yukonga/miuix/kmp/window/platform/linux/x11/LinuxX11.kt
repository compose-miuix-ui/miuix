// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("unused")

package top.yukonga.miuix.kmp.window.platform.linux.x11

import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.Structure
import com.sun.jna.platform.unix.X11

/**
 * Shared Xlib binding, extending JNA with the functions used by Linux window and input support.
 */
@Suppress("ktlint:standard:function-naming", "FunctionName")
internal interface LinuxX11 : X11 {
    fun XUngrabPointer(
        display: X11.Display,
        time: NativeLong,
    ): Int

    fun XFreeCursor(
        display: X11.Display,
        cursor: NativeLong,
    ): Int

    fun XConnectionNumber(
        display: X11.Display,
    ): Int

    fun XGetEventData(
        display: X11.Display,
        cookie: EventCookie,
    ): Int

    fun XFreeEventData(
        display: X11.Display,
        cookie: EventCookie,
    )

    @Structure.FieldOrder(
        "type",
        "serial",
        "sendEvent",
        "display",
        "extension",
        "eventType",
        "cookie",
        "data",
    )
    class EventCookie(
        pointer: Pointer,
    ) : Structure(pointer) {
        @JvmField
        var type: Int = 0

        @JvmField
        var serial: NativeLong = NativeLong()

        @JvmField
        var sendEvent: Int = 0

        @JvmField
        var display: X11.Display? = null

        @JvmField
        var extension: Int = 0

        @JvmField
        var eventType: Int = 0

        @JvmField
        var cookie: Int = 0

        @JvmField
        var data: Pointer? = null

        init {
            read()
        }
    }

    companion object {
        val INSTANCE: LinuxX11 = Native.load("X11", LinuxX11::class.java)
    }
}
