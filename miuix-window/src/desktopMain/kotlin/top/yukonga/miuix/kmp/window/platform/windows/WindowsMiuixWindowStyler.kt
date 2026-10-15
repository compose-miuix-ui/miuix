// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

import androidx.compose.foundation.layout.WindowInsets
import com.sun.jna.platform.win32.WinUser
import com.sun.jna.ptr.IntByReference
import top.yukonga.miuix.kmp.window.MiuixWindowBackgroundType
import top.yukonga.miuix.kmp.window.WindowResizeEdge
import top.yukonga.miuix.kmp.window.internal.DesktopOS
import top.yukonga.miuix.kmp.window.internal.WindowStyler
import top.yukonga.miuix.kmp.window.internal.hwnd
import top.yukonga.miuix.kmp.window.internal.isUndecorated
import top.yukonga.miuix.kmp.window.platform.windows.structure.WindowMargins
import java.awt.Window

internal class WindowsMiuixWindowStyler(
    window: Window,
    private val hitTest: (Float, Float) -> HitTestResult,
    private val onWindowInsetUpdate: (WindowInsets) -> Unit,
    private val onResizeEdgeChange: (WindowResizeEdge) -> Unit,
) : WindowStyler {
    private val hwnd = window.hwnd

    private val composeWindowProc = ComposeWindowProc(
        window = window,
        hitTest = hitTest,
        onWindowInsetUpdate = { windowClientInsets ->
            val left = windowClientInsets.leftVal
            val top = windowClientInsets.topVal
            val right = windowClientInsets.rightVal
            val bottom = windowClientInsets.bottomVal
            val windowInsets = if (left == 2 && top == 2 && right == 2 && bottom == 2) {
                WindowInsets()
            } else {
                WindowInsets(
                    left = left,
                    top = top - 2,
                    right = right,
                    bottom = bottom,
                )
            }
            onWindowInsetUpdate(windowInsets)
        },
        onResizeEdgeChange = onResizeEdgeChange,
    )

    init {
        val isDecorated = !window.isUndecorated

        if (isDecorated) {
            User32Ex.INSTANCE.updateWindowStyle(hwnd) { oldStyle ->
                oldStyle and WinUser.WS_SYSMENU.inv()
            }
        }

        val os = DesktopOS.current
        if (os is DesktopOS.Windows) {
            if (os.buildNumber >= DesktopOS.Windows.WINDOWS_11_21H2) {
                Dwmapi.INSTANCE.DwmSetWindowAttribute(
                    hwnd = hwnd,
                    attribute = DwmWindowAttribute.DWMWA_CAPTION_COLOR.value,
                    value = IntByReference((0xFFFFFFFE).toInt()),
                    valueSize = 4,
                )
            }
        }
    }

    override fun updateIsResizable(value: Boolean) {
        composeWindowProc.isResizable = value
    }

    override fun updateBackground(type: MiuixWindowBackgroundType, isDarkTheme: Boolean) {
        val os = DesktopOS.current
        if (os is DesktopOS.Windows) {
            if (os.buildNumber >= DesktopOS.Windows.WINDOWS_11_21H2) {
                Dwmapi.INSTANCE.DwmSetWindowAttribute(
                    hwnd = hwnd,
                    attribute = DwmWindowAttribute.DWMWA_USE_IMMERSIVE_DARK_MODE.value,
                    value = IntByReference(if (isDarkTheme) 1 else 0),
                    valueSize = 4,
                )
            }

            when {
                os.buildNumber >= DesktopOS.Windows.WINDOWS_11_22H2 -> {
                    Dwmapi.INSTANCE.DwmSetWindowAttribute(
                        hwnd = hwnd,
                        attribute = DwmWindowAttribute.DWMWA_SYSTEMBACKDROP_TYPE.value,
                        value = IntByReference(
                            when (type) {
                                MiuixWindowBackgroundType.None -> 1
                                MiuixWindowBackgroundType.Mica -> 2
                                MiuixWindowBackgroundType.Acrylic -> 3
                                MiuixWindowBackgroundType.MicaAlt -> 4
                                MiuixWindowBackgroundType.Vibrancy -> 1
                            },
                        ),
                        valueSize = 4,
                    )
                }

                os.buildNumber >= DesktopOS.Windows.WINDOWS_11_21H2 -> {
                    Dwmapi.INSTANCE.DwmSetWindowAttribute(
                        hwnd = hwnd,
                        attribute = DwmWindowAttribute.DWMWA_MICA_EFFECT.value,
                        value = IntByReference(
                            if (type == MiuixWindowBackgroundType.Mica) 1 else 0,
                        ),
                        valueSize = 4,
                    )
                }
            }
        }
    }

    fun updateBorderAndShadow(value: Boolean) {
        val pMarInset = if (value) {
            WindowMargins.ByReference()
        } else {
            WindowMargins.ByReference().apply {
                leftBorderWidth = 0
                rightBorderWidth = 0
                topBorderHeight = 0
                bottomBorderHeight = 0
            }
        }
        Dwmapi.INSTANCE.DwmExtendFrameIntoClientArea(hwnd, pMarInset)
    }

    fun updateIsToolWindow(value: Boolean) {
        User32Ex.INSTANCE.updateWindowExStyle(hwnd) { oldStyle ->
            if (value) {
                oldStyle or WinUserConst.WS_EX_TOOLWINDOW
            } else {
                oldStyle and WinUserConst.WS_EX_TOOLWINDOW.inv()
            }
        }
    }
}
