// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.x11

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.platform.unix.X11

@Suppress("ktlint:standard:function-naming", "FunctionName")
internal interface LinuxXcursor : Library {
    fun XcursorGetTheme(
        display: X11.Display,
    ): Pointer?

    fun XcursorGetDefaultSize(
        display: X11.Display,
    ): Int

    fun XcursorLibraryLoadImages(
        name: String,
        theme: String?,
        size: Int,
    ): Pointer?

    fun XcursorImagesLoadCursor(
        display: X11.Display,
        images: Pointer,
    ): NativeLong

    fun XcursorImagesDestroy(
        images: Pointer,
    )

    companion object {
        val INSTANCE: LinuxXcursor = Native.load("Xcursor", LinuxXcursor::class.java)
    }
}
