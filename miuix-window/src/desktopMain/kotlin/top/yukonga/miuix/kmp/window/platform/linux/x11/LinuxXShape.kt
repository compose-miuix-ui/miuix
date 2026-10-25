// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.x11

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.platform.unix.X11

/**
 * JNA bindings for the X11 Shape extension in libXext.
 */
@Suppress("FunctionName")
internal interface LinuxXShape : Library {
    fun XShapeCombineRectangles(
        display: X11.Display,
        window: X11.Window,
        destKind: Int,
        xOffset: Int,
        yOffset: Int,
        rectangles: Pointer,
        rectangleCount: Int,
        operation: Int,
        ordering: Int,
    )

    companion object {
        const val SHAPE_INPUT = 2
        const val SHAPE_SET = 0
        const val UNSORTED = 0

        val INSTANCE: LinuxXShape = Native.load("Xext", LinuxXShape::class.java)
    }
}
