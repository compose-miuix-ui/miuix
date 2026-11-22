// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows
import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers
import com.sun.jna.platform.win32.WinDef.HWND
import com.sun.jna.platform.win32.WinDef.LPARAM
import com.sun.jna.platform.win32.WinDef.WPARAM
import top.yukonga.miuix.kmp.window.internal.findSkiaLayer
import top.yukonga.miuix.kmp.window.internal.hwnd
import top.yukonga.miuix.kmp.window.platform.input.DesktopTouchScene
import top.yukonga.miuix.kmp.window.platform.input.TouchPointerState
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_MOUSELEAVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_MOUSEMOVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCMOUSELEAVE
import top.yukonga.miuix.kmp.window.platform.windows.WinUserConst.WM_NCMOUSEMOVE
import top.yukonga.miuix.kmp.window.platform.windows.structure.POINTER_INFO
import java.awt.Component
import java.awt.Window
import java.awt.event.HierarchyEvent
import java.awt.event.HierarchyListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.SwingUtilities

@OptIn(InternalComposeUiApi::class)
internal class WindowsTouchInput private constructor(
    private val window: Window,
    private val canvas: Component,
    private val hwnd: HWND,
    private val target: DesktopTouchScene,
) {
    private val acceptedPointers = mutableMapOf<Int, WindowsTouchEvent>()
    private val pointerState = TouchPointerState()

    private var captionPointerId: Int? = null

    val isCaptionDragInProgress: Boolean
        get() = captionPointerId != null

    @Volatile
    private var disposed = false

    private val windowListener = object : WindowAdapter() {
        override fun windowLostFocus(event: WindowEvent) = cancelOnEdt()
    }

    private val hierarchyListener = HierarchyListener { event ->
        if (event.changeFlags and HierarchyEvent.SHOWING_CHANGED.toLong() != 0L &&
            !canvas.isShowing
        ) {
            cancelOnEdt()
        }
    }

    init {
        window.addWindowFocusListener(windowListener)
        canvas.addHierarchyListener(hierarchyListener)
    }

    fun handleMessage(
        message: Int,
        wParam: WPARAM,
        lParam: LPARAM,
    ): Boolean {
        if (disposed) return false
        val id = wParam.toInt() and 0xFFFF
        if (id == captionPointerId && (
                message == WM_POINTERUPDATE || message == WM_POINTERUP ||
                    message == WM_NCPOINTERUPDATE || message == WM_NCPOINTERUP
                )
        ) {
            val released = message == WM_POINTERUP || message == WM_NCPOINTERUP
            sendCaptionPointer(
                message = if (released) WM_NCPOINTERUP else WM_NCPOINTERUPDATE,
                id = id,
                position = lParam,
            )
            if (released) captionPointerId = null
            return true
        }
        return when (message) {
            WM_POINTERDOWN, WM_POINTERUPDATE, WM_POINTERUP -> onPointer(message, id)

            WM_NCPOINTERDOWN -> {
                val hit = wParam.toInt() ushr 16
                if (hit == HitTestResult.HTCAPTION.value ||
                    hit == HitTestResult.HTMINBUTTON.value ||
                    hit == HitTestResult.HTMAXBUTTON.value ||
                    hit == HitTestResult.HTCLOSE.value
                ) {
                    onPointer(WM_POINTERDOWN, id)
                } else {
                    false
                }
            }

            WM_NCPOINTERUPDATE -> onPointer(WM_POINTERUPDATE, id)

            WM_NCPOINTERUP -> onPointer(WM_POINTERUP, id)

            WM_POINTERCAPTURECHANGED, WM_POINTERLEAVE -> {
                if (captionPointerId == id) captionPointerId = null
                if (acceptedPointers.remove(id) != null) {
                    dispatch { cancelOnEdt() }
                    true
                } else {
                    false
                }
            }

            WM_MOUSEMOVE, WM_NCMOUSEMOVE, WM_MOUSELEAVE, WM_NCMOUSELEAVE ->
                acceptedPointers.isNotEmpty()

            WM_CANCELMODE -> {
                captionPointerId = null
                dispatch { cancelOnEdt() }
                false
            }

            WM_NCDESTROY -> {
                disposed = true
                captionPointerId = null
                acceptedPointers.clear()
                SwingUtilities.invokeLater {
                    cancelOnEdt()
                    window.removeWindowFocusListener(windowListener)
                    canvas.removeHierarchyListener(hierarchyListener)
                }
                false
            }

            else -> false
        }
    }

    private fun onPointer(message: Int, id: Int): Boolean {
        val nativeEvent = acceptedPointers[id]
        val accepted = nativeEvent != null
        if (!accepted && message != WM_POINTERDOWN) return false

        val info = POINTER_INFO()
        if (!User32Ex.INSTANCE.GetPointerInfo(id, info)) {
            if (accepted) {
                if (message == WM_POINTERUP) acceptedPointers.remove(id)
                dispatch { cancelOnEdt() }
            }
            return accepted
        }
        if (info.pointerType != PT_TOUCH) return false
        if (info.pointerFlags and POINTER_FLAG_CANCELED != 0 ||
            (message != WM_POINTERUP && info.pointerFlags and POINTER_FLAG_INCONTACT == 0)
        ) {
            if (accepted) {
                if (message == WM_POINTERUP) acceptedPointers.remove(id)
                dispatch { cancelOnEdt() }
            }
            return accepted
        }

        if (message == WM_POINTERUPDATE && nativeEvent?.isCaptionDragRequested == true &&
            info.pointerFlags and POINTER_FLAG_PRIMARY != 0 &&
            window.isEnabled && window.findSkiaLayer()?.fullscreen != true
        ) {
            acceptedPointers.remove(id)
            dispatch { cancelOnEdt() }
            captionPointerId = id
            sendCaptionPointer(
                message = WM_NCPOINTERDOWN,
                id = id,
                position = LPARAM(
                    (
                        (info.ptPixelLocation.y shl 16) or
                            (info.ptPixelLocation.x and 0xFFFF)
                        ).toLong(),
                ),
            )
            return true
        }

        val point = info.ptPixelLocation
        if (!User32Ex.INSTANCE.ScreenToClient(hwnd, point)) {
            if (accepted) {
                if (message == WM_POINTERUP) acceptedPointers.remove(id)
                dispatch { cancelOnEdt() }
            }
            return accepted
        }

        val touchEvent = nativeEvent ?: WindowsTouchEvent()
        if (message == WM_POINTERDOWN) acceptedPointers[id] = touchEvent
        if (message == WM_POINTERUP) acceptedPointers.remove(id)
        val position = Offset(point.x.toFloat(), point.y.toFloat())
        val eventType = when (message) {
            WM_POINTERDOWN -> PointerEventType.Press
            WM_POINTERUP -> PointerEventType.Release
            else -> PointerEventType.Move
        }
        val timeMillis = System.nanoTime() / 1_000_000
        val modifiers = PointerKeyboardModifiers(
            isShiftPressed = keyPressed(0x10),
            isCtrlPressed = keyPressed(0x11),
            isAltPressed = keyPressed(0x12),
            isMetaPressed = keyPressed(0x5B) || keyPressed(0x5C),
        )
        dispatch {
            if (!canvas.isShowing || !window.isEnabled || target.isDisposed) {
                cancelOnEdt()
            } else {
                val pointers = pointerState.update(
                    id.toLong(),
                    target.positionInScene(canvas, position),
                    eventType,
                )
                if (pointers != null) {
                    if (eventType == PointerEventType.Press) canvas.requestFocusInWindow()
                    target.scene.sendPointerEvent(
                        eventType = eventType,
                        pointers = pointers,
                        keyboardModifiers = modifiers,
                        timeMillis = timeMillis,
                        nativeEvent = touchEvent,
                    )
                }
            }
        }
        return true
    }

    private fun sendCaptionPointer(
        message: Int,
        id: Int,
        position: LPARAM,
    ) {
        User32Ex.INSTANCE.DefWindowProc(
            window.hwnd,
            message,
            WPARAM((id or (HitTestResult.HTCAPTION.value shl 16)).toLong()),
            position,
        )
    }

    private fun keyPressed(key: Int): Boolean = User32Ex.INSTANCE.GetKeyState(key).toInt() < 0

    private fun dispatch(action: () -> Unit) {
        SwingUtilities.invokeLater {
            if (!disposed) {
                runCatching {
                    action()
                }
            }
        }
    }

    private fun cancelOnEdt() {
        if (pointerState.clear() && !target.isDisposed) target.scene.cancelPointerInput()
    }

    companion object {
        fun create(window: Window, canvas: Component, hwnd: HWND): WindowsTouchInput? = DesktopTouchScene.create(window)?.let { WindowsTouchInput(window, canvas, hwnd, it) }

        private const val WM_NCPOINTERUPDATE = 0x0241
        private const val WM_NCPOINTERDOWN = 0x0242
        private const val WM_NCPOINTERUP = 0x0243
        private const val WM_POINTERUPDATE = 0x0245
        private const val WM_POINTERDOWN = 0x0246
        private const val WM_POINTERUP = 0x0247
        private const val WM_POINTERLEAVE = 0x024A
        private const val WM_POINTERCAPTURECHANGED = 0x024C
        private const val WM_CANCELMODE = 0x001F
        private const val WM_NCDESTROY = 0x0082
        private const val PT_TOUCH = 2
        private const val POINTER_FLAG_INCONTACT = 0x00000004
        private const val POINTER_FLAG_PRIMARY = 0x00002000
        private const val POINTER_FLAG_CANCELED = 0x00008000
    }
}
