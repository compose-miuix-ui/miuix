// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("ktlint:standard:class-naming", "ClassName", "PropertyName")

package top.yukonga.miuix.kmp.window.platform.windows.structure

import com.sun.jna.Structure
import com.sun.jna.platform.win32.WinDef.HWND
import com.sun.jna.platform.win32.WinDef.POINT
import com.sun.jna.platform.win32.WinNT.HANDLE

@Structure.FieldOrder(
    "pointerType",
    "pointerId",
    "frameId",
    "pointerFlags",
    "sourceDevice",
    "hwndTarget",
    "ptPixelLocation",
    "ptHimetricLocation",
    "ptPixelLocationRaw",
    "ptHimetricLocationRaw",
    "dwTime",
    "historyCount",
    "InputData",
    "dwKeyStates",
    "PerformanceCount",
    "ButtonChangeType",
)
internal class POINTER_INFO : Structure() {
    @JvmField var pointerType: Int = 0

    @JvmField var pointerId: Int = 0

    @JvmField var frameId: Int = 0

    @JvmField var pointerFlags: Int = 0

    @JvmField var sourceDevice: HANDLE? = null

    @JvmField var hwndTarget: HWND? = null

    @JvmField var ptPixelLocation: POINT = POINT()

    @JvmField var ptHimetricLocation: POINT = POINT()

    @JvmField var ptPixelLocationRaw: POINT = POINT()

    @JvmField var ptHimetricLocationRaw: POINT = POINT()

    @JvmField var dwTime: Int = 0

    @JvmField var historyCount: Int = 0

    @JvmField var InputData: Int = 0

    @JvmField var dwKeyStates: Int = 0

    @JvmField var PerformanceCount: Long = 0

    @JvmField var ButtonChangeType: Int = 0
}
