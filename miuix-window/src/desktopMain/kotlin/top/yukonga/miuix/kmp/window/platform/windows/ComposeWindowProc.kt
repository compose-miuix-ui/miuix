// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.awt.ComposeWindow
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinDef.HMENU
import com.sun.jna.platform.win32.WinDef.LPARAM
import com.sun.jna.platform.win32.WinDef.LRESULT
import com.sun.jna.platform.win32.WinDef.UINT
import com.sun.jna.platform.win32.WinDef.WPARAM
import com.sun.jna.platform.win32.WinUser
import com.sun.jna.platform.win32.WinUser.WM_SIZE
import com.sun.jna.platform.win32.WinUser.WS_SYSMENU
import org.jetbrains.skiko.SkiaLayer
import top.yukonga.miuix.kmp.window.WindowResizeEdge
import top.yukonga.miuix.kmp.window.internal.findSkiaLayer
import top.yukonga.miuix.kmp.window.internal.hwnd
import top.yukonga.miuix.kmp.window.internal.isUndecorated
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.MFS_DISABLED
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.MFS_ENABLED
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.MFT_STRING
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.MIIM_STATE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.SC_CLOSE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.SC_MOVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.SC_RESTORE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.SC_SIZE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.TPM_RETURNCMD
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WINT_MAX
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_ACTIVATE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_MOUSELEAVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCACTIVATE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCCALCSIZE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCHITTEST
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCMOUSELEAVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCMOUSEMOVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCRBUTTONUP
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_SETTINGCHANGE
import top.yukonga.miuix.kmp.window.platform.windows.structure.MENUITEMINFO
import java.awt.Window
import kotlin.math.roundToInt

internal class ComposeWindowProc(
    window: Window,
    private val hitTest: (x: Float, y: Float) -> HitTestResult,
    private val onWindowInsetUpdate: (WindowClientInsets) -> Unit,
    private val onResizeEdgeChange: (WindowResizeEdge) -> Unit,
) : BasicWindowProc(window.hwnd) {
    private val skiaLayer: SkiaLayer = window.findSkiaLayer()!!

    private val undecoratedResizerThickness =
        when (window) {
            is ComposeWindow -> window.undecoratedResizerThickness
            is ComposeDialog -> window.undecoratedResizerThickness
            else -> error("Unsupported window type")
        }

    private var hitResult = HitTestResult.HTCLIENT

    private var dpi = UINT(0)
    private var width = 0
    private var height = 0
    private var frameX = 0
    private var frameY = 0
    private var edgeX = 0
    private var edgeY = 0
    private var padding = 0

    private var isMaximized = User32Ex.INSTANCE.isWindowInMaximized(originalHwnd)

    var isResizable = true

    val skiaLayerProc = SkiaLayerWindowProc(
        window = window,
        skiaLayer = skiaLayer,
        hitTest = { x, y ->
            updateWindowInfo()
            val density = dpi.toInt() / 96f
            val horizontalPadding =
                if (window.isUndecorated) {
                    (undecoratedResizerThickness.value * density).roundToInt()
                } else {
                    frameX
                }
            val verticalPadding =
                if (window.isUndecorated) {
                    (undecoratedResizerThickness.value * density).roundToInt()
                } else {
                    frameY
                }

            hitResult = when {
                isMaximized || !isResizable || skiaLayer.fullscreen -> hitTest(x, y)

                else -> {
                    val hitTestResizeEdge = tryHitTestResizeEdge(
                        x = x,
                        y = y,
                        horizontalPadding = horizontalPadding,
                        verticalPadding = verticalPadding,
                    )

                    if (hitTestResizeEdge != null) {
                        onResizeEdgeChange(hitTestResizeEdge.toWindowResizeEdge())
                    } else {
                        onResizeEdgeChange(WindowResizeEdge.None)
                    }

                    if (window.isUndecorated) {
                        if (hitTestResizeEdge != null) {
                            HitTestResult.HTCLIENT
                        } else {
                            hitTest(x, y)
                        }
                    } else {
                        hitTestResizeEdge ?: hitTest(x, y)
                    }
                }
            }
            hitResult
        },
        onMouseLeave = {
            onResizeEdgeChange(WindowResizeEdge.None)
        },
    )

    override fun callback(
        hwnd: WinDef.HWND,
        uMsg: Int,
        wParam: WPARAM,
        lParam: LPARAM,
    ): LRESULT {
        if (skiaLayerProc.handleTouchMessage(uMsg, wParam, lParam)) return LRESULT(0)
        return handleWindowMessage(
            hwnd = hwnd,
            uMsg = uMsg,
            wParam = wParam,
            lParam = lParam,
        )
    }

    private fun handleWindowMessage(
        hwnd: WinDef.HWND,
        uMsg: Int,
        wParam: WPARAM,
        lParam: LPARAM,
    ): LRESULT = when (uMsg) {
        WM_NCCALCSIZE -> {
            if (wParam.toInt() == 0) {
                super.callback(hwnd, uMsg, wParam, lParam)
            } else {
                dpi = User32Ex.INSTANCE.GetDpiForWindow(hwnd)
                frameX = User32Ex.INSTANCE.GetSystemMetricsForDpi(WinUser.SM_CXFRAME, dpi)
                frameY = User32Ex.INSTANCE.GetSystemMetricsForDpi(WinUser.SM_CYFRAME, dpi)
                edgeX = User32Ex.INSTANCE.GetSystemMetricsForDpi(WinUser.SM_CXEDGE, dpi)
                edgeY = User32Ex.INSTANCE.GetSystemMetricsForDpi(WinUser.SM_CYEDGE, dpi)
                padding = User32Ex.INSTANCE.GetSystemMetricsForDpi(WinUser.SM_CXPADDEDBORDER, dpi)
                isMaximized = User32Ex.INSTANCE.isWindowInMaximized(hwnd)

                onWindowInsetUpdate(
                    WindowClientInsets(
                        leftVal = if (isMaximized) frameX + padding else edgeX,
                        rightVal = if (isMaximized) frameX + padding else edgeX,
                        topVal = if (isMaximized) frameY + padding else edgeY,
                        bottomVal = if (isMaximized) frameY + padding else edgeY,
                    ),
                )
                val params = Pointer(lParam.toLong())
                val clientRects = params.getByteArray(0, WinDef.RECT().size() * 3)
                try {
                    super.callback(hwnd, uMsg, wParam, lParam)
                } finally {
                    params.write(0, clientRects, 0, clientRects.size)
                }
                LRESULT(0)
            }
        }

        WM_NCHITTEST -> if (skiaLayerProc.isCaptionDragInProgress) {
            HitTestResult.HTCAPTION.toLRESULT()
        } else {
            hitResult.toLRESULT()
        }

        WM_NCACTIVATE -> {
            User32Ex.INSTANCE.CallWindowProc(
                originalWindowProc,
                hwnd,
                uMsg,
                wParam,
                LPARAM(-1),
            )
        }

        WM_SIZE -> {
            width = lParam.toInt() and 0xFFFF
            height = (lParam.toInt() shr 16) and 0xFFFF
            User32Ex.INSTANCE.CallWindowProc(
                originalWindowProc,
                hwnd,
                uMsg,
                wParam,
                lParam,
            )
        }

        WM_NCRBUTTONUP -> {
            if (wParam.toInt() == HitTestResult.HTCAPTION.value) {
                val user32 = User32Ex.INSTANCE
                val oldStyle = user32.GetWindowLong(hwnd, WinUser.GWL_STYLE)
                user32.SetWindowLong(hwnd, WinUser.GWL_STYLE, oldStyle or WS_SYSMENU)
                val menu = user32.GetSystemMenu(hwnd, false)
                user32.SetWindowLong(hwnd, WinUser.GWL_STYLE, oldStyle)
                isMaximized = user32.isWindowInMaximized(hwnd)
                if (menu != null) {
                    val menuItemInfo = MENUITEMINFO.ByReference().apply {
                        cbSize = this.size()
                        fMask = MIIM_STATE
                        fType = MFT_STRING
                    }
                    updateMenuItemInfo(menu, menuItemInfo, SC_RESTORE, isMaximized)
                    updateMenuItemInfo(menu, menuItemInfo, SC_MOVE, !isMaximized)
                    updateMenuItemInfo(menu, menuItemInfo, SC_SIZE, !isMaximized)
                    updateMenuItemInfo(menu, menuItemInfo, WinUser.SC_MINIMIZE, true)
                    updateMenuItemInfo(menu, menuItemInfo, WinUser.SC_MAXIMIZE, !isMaximized)
                    updateMenuItemInfo(menu, menuItemInfo, SC_CLOSE, true)
                    user32.SetMenuDefaultItem(menu, WINT_MAX, false)
                    val lParamValue = lParam.toInt()
                    val x = lParamValue.toShort().toInt()
                    val y = (lParamValue shr 16).toShort().toInt()
                    val ret = user32.TrackPopupMenu(menu, TPM_RETURNCMD, x, y, 0, hwnd, null)
                    if (ret != 0) {
                        user32.PostMessage(
                            hwnd,
                            WinUser.WM_SYSCOMMAND,
                            WPARAM(ret.toLong()),
                            LPARAM(0),
                        )
                    }
                }
            }
            User32Ex.INSTANCE.CallWindowProc(
                originalWindowProc,
                hwnd,
                uMsg,
                wParam,
                lParam,
            )
        }

        WM_SETTINGCHANGE -> {
            User32Ex.INSTANCE.CallWindowProc(
                originalWindowProc,
                hwnd,
                uMsg,
                wParam,
                lParam,
            )
        }

        WM_ACTIVATE -> {
            super.callback(hwnd, uMsg, wParam, lParam)
        }

        WM_MOUSELEAVE,
        WM_NCMOUSELEAVE,
        -> {
            onResizeEdgeChange(WindowResizeEdge.None)
            super.callback(hwnd, uMsg, wParam, lParam)
        }

        WM_NCMOUSEMOVE -> {
            User32Ex.INSTANCE.PostMessage(skiaLayerProc.originalHwnd, uMsg, wParam, lParam)
            super.callback(hwnd, uMsg, wParam, lParam)
        }

        else -> {
            super.callback(hwnd, uMsg, wParam, lParam)
        }
    }

    private fun updateWindowInfo() {
        dpi = User32Ex.INSTANCE.GetDpiForWindow(originalHwnd)
        frameX = User32Ex.INSTANCE.GetSystemMetricsForDpi(WinUser.SM_CXFRAME, dpi)
        frameY = User32Ex.INSTANCE.GetSystemMetricsForDpi(WinUser.SM_CYFRAME, dpi)

        val rect = WinDef.RECT()
        if (User32Ex.INSTANCE.GetWindowRect(originalHwnd, rect)) {
            rect.read()
            width = rect.right - rect.left
            height = rect.bottom - rect.top
        }
        rect.clear()
    }

    private fun updateMenuItemInfo(
        menu: HMENU,
        menuItemInfo: MENUITEMINFO.ByReference,
        item: Int,
        enabled: Boolean,
    ) {
        menuItemInfo.fState = if (enabled) MFS_ENABLED else MFS_DISABLED
        User32Ex.INSTANCE.SetMenuItemInfo(menu, item, false, menuItemInfo)
    }

    private fun tryHitTestResizeEdge(
        x: Float,
        y: Float,
        horizontalPadding: Int,
        verticalPadding: Int,
    ): HitTestResult? {
        val isLeft = x <= horizontalPadding
        val isRight = x >= width - horizontalPadding

        val isTop = y <= verticalPadding
        val isBottom = y >= height - verticalPadding

        return when {
            isTop && isLeft -> HitTestResult.HTTOPLEFT
            isTop && isRight -> HitTestResult.HTTOPRIGHT
            isBottom && isLeft -> HitTestResult.HTBOTTOMLEFT
            isBottom && isRight -> HitTestResult.HTBOTTOMRIGHT
            isTop -> HitTestResult.HTTOP
            isBottom -> HitTestResult.HTBOTTOM
            isLeft -> HitTestResult.HTLEFT
            isRight -> HitTestResult.HTRIGHT
            else -> null
        }
    }
}
