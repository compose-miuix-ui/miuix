// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("ktlint:standard:function-naming", "FunctionName")

package top.yukonga.miuix.kmp.window.platform.windows

import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.BaseTSD.LONG_PTR
import com.sun.jna.platform.win32.User32
import com.sun.jna.platform.win32.WinDef.HMENU
import com.sun.jna.platform.win32.WinDef.HWND
import com.sun.jna.platform.win32.WinDef.LPARAM
import com.sun.jna.platform.win32.WinDef.LRESULT
import com.sun.jna.platform.win32.WinDef.POINT
import com.sun.jna.platform.win32.WinDef.RECT
import com.sun.jna.platform.win32.WinDef.UINT
import com.sun.jna.platform.win32.WinDef.WPARAM
import com.sun.jna.platform.win32.WinUser
import com.sun.jna.platform.win32.WinUser.WindowProc
import com.sun.jna.win32.W32APIOptions
import top.yukonga.miuix.kmp.window.platform.windows.structure.MENUITEMINFO
import top.yukonga.miuix.kmp.window.platform.windows.structure.POINTER_INFO
import top.yukonga.miuix.kmp.window.platform.windows.structure.TRACKMOUSEEVENT

internal interface User32Ex : User32 {
    fun SetWindowLongPtr(hWnd: HWND, nIndex: Int, wndProc: WindowProc): LONG_PTR

    fun CallWindowProc(
        proc: LONG_PTR,
        hWnd: HWND,
        uMsg: Int,
        wParam: WPARAM,
        lParam: LPARAM,
    ): LRESULT

    fun GetSystemMetricsForDpi(nIndex: Int, dpi: UINT): Int

    fun GetDpiForWindow(hWnd: HWND): UINT

    fun ScreenToClient(hWnd: HWND, lpPoint: POINT): Boolean

    fun GetPointerInfo(pointerId: Int, pointerInfo: POINTER_INFO): Boolean

    fun GetKeyState(virtualKey: Int): Short

    fun GetSystemMenu(hWnd: HWND, bRevert: Boolean): HMENU?

    fun SetMenuItemInfo(
        hMenu: HMENU,
        item: Int,
        fByPosition: Boolean,
        lpmii: MENUITEMINFO.ByReference,
    ): Boolean

    fun TrackPopupMenu(
        hMenu: HMENU,
        uFlags: Int,
        x: Int,
        y: Int,
        nReserved: Int,
        hWnd: HWND,
        prcRect: RECT?,
    ): Int

    fun SetMenuDefaultItem(hMenu: HMENU, uItem: Int, fByPos: Boolean): Boolean

    fun TrackMouseEvent(lpEventTrack: TRACKMOUSEEVENT.ByReference): Boolean

    companion object {
        val INSTANCE: User32Ex = Native.load(
            "user32",
            User32Ex::class.java,
            W32APIOptions.DEFAULT_OPTIONS,
        )
    }
}

internal fun User32.isWindowInMaximized(hWnd: HWND): Boolean {
    val placement = WinUser.WINDOWPLACEMENT()
    return GetWindowPlacement(hWnd, placement).booleanValue() &&
        placement.showCmd == WinUser.SW_SHOWMAXIMIZED
}

internal fun User32.updateWindowStyle(hWnd: HWND, styleBlock: (oldStyle: Int) -> Int) {
    val oldStyle = GetWindowLong(hWnd, WinUser.GWL_STYLE)
    SetWindowLongPtr(hWnd, WinUser.GWL_STYLE, Pointer(styleBlock(oldStyle).toLong()))
}

internal fun User32.updateWindowExStyle(hWnd: HWND, exStyleBlock: (oldExStyle: Int) -> Int) {
    val oldExStyle = GetWindowLong(hWnd, WinUser.GWL_EXSTYLE)
    SetWindowLongPtr(hWnd, WinUser.GWL_EXSTYLE, Pointer(exStyleBlock(oldExStyle).toLong()))
}
