// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows.structure

import com.sun.jna.Structure
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinDef.DWORD

@Structure.FieldOrder("cbSize", "dwFlags", "hwndTrack", "dwHoverTime")
internal open class TRACKMOUSEEVENT : Structure() {
    @JvmField var cbSize: DWORD = DWORD(0)

    @JvmField var dwFlags: DWORD = DWORD(0)

    @JvmField var hwndTrack: WinDef.HWND? = null

    @JvmField var dwHoverTime: DWORD = DWORD(0)

    class ByReference :
        TRACKMOUSEEVENT(),
        Structure.ByReference

    companion object {
        const val TME_CANCEL = 0x80000000.toInt()
        const val TME_HOVER = 0x00000001
        const val TME_LEAVE = 0x00000002
        const val TME_NONCLIENT = 0x00000010
    }
}
