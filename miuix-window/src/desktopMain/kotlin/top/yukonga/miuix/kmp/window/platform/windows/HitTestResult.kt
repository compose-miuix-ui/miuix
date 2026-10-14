// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

import com.sun.jna.platform.win32.WinDef.LRESULT
import top.yukonga.miuix.kmp.window.WindowResizeEdge

internal enum class HitTestResult(
    val value: Int,
) {
    HTERROR(-2),
    HTTRANSPARENT(-1),
    HTNOWHERE(0),
    HTCLIENT(1),
    HTCAPTION(2),
    HTSYSMENU(3),
    HTGROWBOX(4),
    HTMENU(5),
    HTHSCROLL(6),
    HTVSCROLL(7),
    HTMINBUTTON(8),
    HTMAXBUTTON(9),
    HTLEFT(10),
    HTRIGHT(11),
    HTTOP(12),
    HTTOPLEFT(13),
    HTTOPRIGHT(14),
    HTBOTTOM(15),
    HTBOTTOMLEFT(16),
    HTBOTTOMRIGHT(17),
    HTBORDER(18),
    HTCLOSE(20),
    HTHELP(21),
    ;

    fun toLRESULT(): LRESULT = LRESULT(value.toLong())
}

internal fun HitTestResult.toWindowResizeEdge(): WindowResizeEdge = when (this) {
    HitTestResult.HTLEFT -> WindowResizeEdge.Left
    HitTestResult.HTRIGHT -> WindowResizeEdge.Right
    HitTestResult.HTTOP -> WindowResizeEdge.Top
    HitTestResult.HTBOTTOM -> WindowResizeEdge.Bottom
    HitTestResult.HTTOPLEFT -> WindowResizeEdge.TopLeft
    HitTestResult.HTTOPRIGHT -> WindowResizeEdge.TopRight
    HitTestResult.HTBOTTOMLEFT -> WindowResizeEdge.BottomLeft
    HitTestResult.HTBOTTOMRIGHT -> WindowResizeEdge.BottomRight
    else -> WindowResizeEdge.None
}
