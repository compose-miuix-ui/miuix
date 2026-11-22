// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.x11

import com.sun.jna.Memory
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.platform.unix.X11
import com.sun.jna.ptr.IntByReference
import java.awt.Toolkit

/**
 * Owns access to AWT's X connection and preserves its event selections across XI2 sessions.
 * All native access is serialized with AWT's own lock.
 */
internal object LinuxAwtInput {
    private val bridge by lazy { runCatching { Bridge() }.getOrNull() }

    val isAvailable: Boolean
        get() = bridge != null

    /**
     * JBR selects touch events on native children. Its canvas listener must yield before
     * SaltUI can reject a contact to the WM; the returned lease restores only those touch bits.
     */
    fun suspendTouch(
        canvas: Long,
    ): AutoCloseable? = bridge?.suspendTouch(canvas)

    fun releasePointer() = checkNotNull(bridge).release()

    private class Bridge {
        private val toolkit = Toolkit.getDefaultToolkit().javaClass.also {
            check(it.name == "sun.awt.X11.XToolkit")
        }
        private val getDisplay = toolkit.getMethod("getDisplay").apply { check(trySetAccessible()) }
        private val lock = toolkit.getMethod("awtLock").apply { check(trySetAccessible()) }
        private val unlock = toolkit.getMethod("awtUnlock").apply { check(trySetAccessible()) }
        private val ungrab = Class.forName("sun.awt.X11.XBaseWindow")
            .getMethod("ungrabInput")
            .apply { check(trySetAccessible()) }

        private val lookupWindow =
            toolkit.getDeclaredMethod("windowToXWindow", Long::class.javaPrimitiveType)
                .apply { check(trySetAccessible()) }

        private fun display() = X11.Display().apply {
            pointer = Pointer(getDisplay.invoke(null) as Long)
        }

        private fun <T> locked(
            block: (X11.Display) -> T,
        ): T {
            lock.invoke(null)
            try {
                return block(display())
            } finally {
                unlock.invoke(null)
            }
        }

        fun suspendTouch(
            canvas: Long,
        ): AutoCloseable? = locked { display ->
            if (lookupWindow.invoke(null, canvas) == null) return@locked null
            val xid = X11.Window(canvas)
            val saved = selectedEvents(display, xid).filter { mask ->
                mask.length > 2 && mask.mask.getByte(2).toInt() and TOUCH_BITS != 0
            }
            try {
                for (mask in saved) {
                    val updated = copyMask(mask)
                    val bits = updated.mask.getByte(2).toInt() and TOUCH_BITS.inv()
                    updated.mask.setByte(2, bits.toByte())
                    LinuxXInput2.instance.XISelectEvents(display, xid, updated, 1)
                }
                LinuxX11.INSTANCE.XSync(display, false)
            } catch (failure: Throwable) {
                restoreTouch(display, canvas, saved)
                throw failure
            }
            AutoCloseable { locked { restoreTouch(it, canvas, saved) } }
        }

        private fun selectedEvents(
            display: X11.Display,
            window: X11.Window,
        ): List<LinuxXInput2.EventMask> {
            val count = IntByReference()
            val data = LinuxXInput2.instance.XIGetSelectedEvents(display, window, count)
                ?: return emptyList()
            try {
                val size = LinuxXInput2.EventMask(0).size()
                return (0 until count.value).map { index ->
                    copyMask(LinuxXInput2.EventMask(data.share(index.toLong() * size)))
                }
            } finally {
                LinuxX11.INSTANCE.XFree(data)
            }
        }

        private fun copyMask(
            original: LinuxXInput2.EventMask,
        ) = LinuxXInput2.EventMask(original.device).apply {
            length = maxOf(4, original.length)
            mask = Memory(length.toLong()).apply {
                clear()
                if (original.length > 0) {
                    write(0, original.mask.getByteArray(0, original.length), 0, original.length)
                }
            }
        }

        private fun restoreTouch(
            display: X11.Display,
            window: Long,
            saved: List<LinuxXInput2.EventMask>,
        ) {
            // AWT may already have destroyed this peer while the reader was shutting down.
            if (lookupWindow.invoke(null, window) == null) return
            val xid = X11.Window(window)
            val current = selectedEvents(display, xid).associateBy { it.device }
            for (mask in saved) {
                val restored = current[mask.device] ?: LinuxXInput2.EventMask(mask.device)
                val bits = restored.mask.getByte(2).toInt() or (
                    mask.mask.getByte(2)
                        .toInt() and TOUCH_BITS
                    )
                restored.mask.setByte(2, bits.toByte())
                LinuxXInput2.instance.XISelectEvents(display, xid, restored, 1)
            }
            LinuxX11.INSTANCE.XSync(display, false)
        }

        private companion object {
            const val TOUCH_BITS = (1 shl 18 - 16) or (1 shl 19 - 16) or (1 shl 20 - 16)
        }

        fun release() = locked { display ->
            ungrab.invoke(null)
            // Release the implicit server grab even if AWT already cleared its local record.
            LinuxX11.INSTANCE.XUngrabPointer(display, NativeLong(0))
            LinuxX11.INSTANCE.XSync(display, false)
        }
    }
}
