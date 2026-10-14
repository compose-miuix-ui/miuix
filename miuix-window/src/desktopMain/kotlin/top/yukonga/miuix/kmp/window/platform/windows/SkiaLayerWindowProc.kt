// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinDef.HWND
import com.sun.jna.platform.win32.WinDef.LRESULT
import com.sun.jna.platform.win32.WinDef.POINT
import org.jetbrains.skiko.SkiaLayer
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_LBUTTONDOWN
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_LBUTTONUP
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_MOUSELEAVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_MOUSEMOVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCHITTEST
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCLBUTTONDOWN
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCLBUTTONUP
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCMOUSEMOVE
import top.yukonga.miuix.kmp.window.platform.windows.structure.TRACKMOUSEEVENT
import java.awt.Window

internal class SkiaLayerWindowProc(
    window: Window,
    skiaLayer: SkiaLayer,
    private val hitTest: (x: Float, y: Float) -> HitTestResult,
    private val onMouseLeave: () -> Unit,
) : BasicWindowProc(HWND(skiaLayer.canvas.let(Native::getComponentPointer))) {
    private val skiaLayerHwnd = HWND(Pointer(skiaLayer.windowHandle))
    private var hitResult = HitTestResult.HTCLIENT
    private var isTrackingMouseLeave = false
    private val touchInput = WindowsTouchInput.create(window, skiaLayer.canvas, originalHwnd)

    val isCaptionDragInProgress: Boolean
        get() = touchInput?.isCaptionDragInProgress == true

    fun handleTouchMessage(
        message: Int,
        wParam: WinDef.WPARAM,
        lParam: WinDef.LPARAM,
    ): Boolean = touchInput?.handleMessage(message, wParam, lParam) == true

    override fun callback(
        hwnd: HWND,
        uMsg: Int,
        wParam: WinDef.WPARAM,
        lParam: WinDef.LPARAM,
    ): LRESULT {
        if (handleTouchMessage(uMsg, wParam, lParam)) {
            if (uMsg == WM_MOUSELEAVE) {
                isTrackingMouseLeave = false
                onMouseLeave()
            }
            return LRESULT(0)
        }
        return when (uMsg) {
            WM_NCHITTEST -> {
                if (isCaptionDragInProgress) return HitTestResult.HTTRANSPARENT.toLRESULT()
                val x = lParam.x
                val y = lParam.y

                val point = POINT(x, y)
                User32Ex.INSTANCE.ScreenToClient(skiaLayerHwnd, point)
                hitResult = hitTest(point.x.toFloat(), point.y.toFloat())

                when (hitResult) {
                    HitTestResult.HTCLIENT,
                    HitTestResult.HTMINBUTTON,
                    HitTestResult.HTMAXBUTTON,
                    HitTestResult.HTCLOSE,
                    -> hitResult.toLRESULT()

                    else -> HitTestResult.HTTRANSPARENT.toLRESULT()
                }
            }

            WM_MOUSEMOVE -> {
                if (!isTrackingMouseLeave) {
                    val tme = TRACKMOUSEEVENT.ByReference().apply {
                        cbSize = WinDef.DWORD(size().toLong())
                        dwFlags = WinDef.DWORD(TRACKMOUSEEVENT.TME_LEAVE.toLong())
                        hwndTrack = hwnd
                    }
                    User32Ex.INSTANCE.TrackMouseEvent(tme)
                    isTrackingMouseLeave = true
                }
                super.callback(hwnd, uMsg, wParam, lParam)
            }

            WM_MOUSELEAVE -> {
                isTrackingMouseLeave = false
                onMouseLeave()
                super.callback(hwnd, uMsg, wParam, lParam)
            }

            WM_NCMOUSEMOVE -> {
                when (hitResult) {
                    HitTestResult.HTCLIENT,
                    HitTestResult.HTCAPTION,
                    HitTestResult.HTMINBUTTON,
                    HitTestResult.HTMAXBUTTON,
                    HitTestResult.HTCLOSE,
                    -> {
                        val clientParam = clientLParam(lParam)
                        User32Ex.INSTANCE.SendMessage(originalHwnd, WM_MOUSEMOVE, WinDef.WPARAM(0L), clientParam)
                    }

                    else -> {}
                }
                HitTestResult.HTNOWHERE.toLRESULT()
            }

            WM_NCLBUTTONDOWN -> {
                val clientParam = clientLParam(lParam)
                User32Ex.INSTANCE.SendMessage(originalHwnd, WM_LBUTTONDOWN, WinDef.WPARAM(0x0001L), clientParam)
                HitTestResult.HTNOWHERE.toLRESULT()
            }

            WM_NCLBUTTONUP -> {
                val clientParam = clientLParam(lParam)
                User32Ex.INSTANCE.SendMessage(originalHwnd, WM_LBUTTONUP, WinDef.WPARAM(0L), clientParam)
                HitTestResult.HTNOWHERE.toLRESULT()
            }

            else -> super.callback(hwnd, uMsg, wParam, lParam)
        }
    }

    private fun clientLParam(lParam: WinDef.LPARAM): WinDef.LPARAM {
        val point = POINT(lParam.x, lParam.y)
        User32Ex.INSTANCE.ScreenToClient(skiaLayerHwnd, point)
        val clientX = point.x and 0xFFFF
        val clientY = point.y and 0xFFFF
        return WinDef.LPARAM(((clientY shl 16) or clientX).toLong())
    }
}

private val WinDef.LPARAM.x: Int
    get() = ((this.toInt() and 0xFFFF) shl 16) shr 16

private val WinDef.LPARAM.y: Int
    get() = this.toInt() shr 16
