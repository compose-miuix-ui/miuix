// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("ktlint:standard:function-naming", "FunctionName")

package top.yukonga.miuix.kmp.window.platform.windows

import com.sun.jna.Native
import com.sun.jna.PointerType
import com.sun.jna.platform.win32.WinDef.HWND
import com.sun.jna.platform.win32.WinNT.HRESULT
import com.sun.jna.win32.StdCallLibrary
import com.sun.jna.win32.W32APIOptions
import top.yukonga.miuix.kmp.window.platform.windows.structure.WindowMargins

internal interface Dwmapi : StdCallLibrary {
    fun DwmExtendFrameIntoClientArea(
        hWnd: HWND,
        pMarInset: WindowMargins.ByReference,
    ): HRESULT

    fun DwmSetWindowAttribute(
        hwnd: HWND,
        attribute: Int,
        value: PointerType?,
        valueSize: Int,
    ): HRESULT

    companion object {
        val INSTANCE: Dwmapi = Native.load(
            "dwmapi",
            Dwmapi::class.java,
            W32APIOptions.DEFAULT_OPTIONS,
        )
    }
}
