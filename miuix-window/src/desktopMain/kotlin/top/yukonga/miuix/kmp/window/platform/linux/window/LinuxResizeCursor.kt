// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.window

import com.sun.jna.Pointer
import com.sun.jna.platform.unix.X11
import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxX11
import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxXcursor
import java.awt.Cursor
import java.awt.Toolkit
import java.lang.reflect.Method

/**
 * Loads themed resize cursors using the same directional names as Linux window managers.
 * Native resources belong to AWT's cursor disposer, including cursors replaced after a theme change.
 */
internal object LinuxResizeCursor {
    private val bridge: X11CursorBridge? by lazy {
        try {
            if (Toolkit.getDefaultToolkit().javaClass.name == "sun.awt.X11.XToolkit") {
                X11CursorBridge()
            } else {
                null
            }
        } catch (_: Throwable) {
            null
        }
    }

    fun get(type: Int): Cursor {
        val candidates = when (type) {
            Cursor.W_RESIZE_CURSOR, Cursor.E_RESIZE_CURSOR -> listOf("ew-resize", "size_hor", "col-resize", "w-resize", "e-resize")
            Cursor.N_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR -> listOf("ns-resize", "size_ver", "row-resize", "n-resize", "s-resize")
            Cursor.NW_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR -> listOf("nwse-resize", "size_fdiag", "nw-resize", "se-resize")
            Cursor.NE_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR -> listOf("nesw-resize", "size_bdiag", "ne-resize", "sw-resize")
            else -> return Cursor.getPredefinedCursor(type)
        }
        return try {
            candidates.firstNotNullOfOrNull { bridge?.get(it) } ?: Cursor.getPredefinedCursor(type)
        } catch (_: Throwable) {
            Cursor.getPredefinedCursor(type)
        }
    }

    private class NamedCursor(
        name: String,
    ) : Cursor(name)

    private data class Theme(
        val name: String?,
        val size: Int,
    )

    private class X11CursorBridge {
        private val toolkit = Toolkit.getDefaultToolkit()
        private val xToolkit = toolkit.javaClass
        private val getDisplayMethod = runCatching { xToolkit.getMethod("getDisplay").accessible() }.getOrNull()
        private val awtLockMethod = runCatching {
            Class.forName("sun.awt.SunToolkit").getMethod("awtLock").accessible()
        }.getOrNull()
        private val awtUnlockMethod = runCatching {
            Class.forName("sun.awt.SunToolkit").getMethod("awtUnlock").accessible()
        }.getOrNull()
        private val setPDataMethod = runCatching {
            Cursor::class.java.getDeclaredMethod("setPData", Long::class.javaPrimitiveType).accessible()
        }.getOrNull()

        private val unsafe: Any? by lazy {
            try {
                val f = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe")
                f.isAccessible = true
                f.get(null)
            } catch (_: Throwable) {
                null
            }
        }

        private val cursorPDataOffset: Long by lazy {
            try {
                val u = unsafe ?: return@lazy -1L
                val field = Cursor::class.java.getDeclaredField("pData")
                val method = u.javaClass.getMethod("objectFieldOffset", java.lang.reflect.Field::class.java)
                (method.invoke(u, field) as Number).toLong()
            } catch (_: Throwable) {
                -1L
            }
        }

        private val x11 = LinuxX11.INSTANCE
        private val xcursor = LinuxXcursor.INSTANCE
        private var theme: Theme? = null
        private val cursors = mutableMapOf<String, Cursor?>()

        init {
            check(getDisplayMethod != null || unsafe != null) {
                "Cannot access AWT X11 display"
            }
        }

        @Synchronized
        fun get(name: String): Cursor? {
            val currentTheme = readTheme() ?: return null
            if (currentTheme != theme) {
                // AWT may still use old cursors; its disposer releases them when no longer referenced
                cursors.clear()
                theme = currentTheme
            }
            if (!cursors.containsKey(name)) {
                cursors[name] = load(name, currentTheme)
            }
            return cursors[name]
        }

        private fun readTheme(): Theme? {
            // A new connection reads current X resources instead of Xcursor's cached Display settings
            val display = x11.XOpenDisplay(null) ?: return null
            return try {
                val desktopName = toolkit.getDesktopProperty("gnome.Gtk/CursorThemeName") as? String
                val desktopSize = toolkit.getDesktopProperty("gnome.Gtk/CursorThemeSize") as? Int
                Theme(
                    name = System.getenv("XCURSOR_THEME")?.takeIf { it.isNotEmpty() }
                        ?: desktopName?.takeIf { it.isNotEmpty() }
                        ?: xcursor.XcursorGetTheme(display)?.getString(0),
                    size = System.getenv("XCURSOR_SIZE")?.toIntOrNull()?.takeIf { it > 0 }
                        ?: desktopSize?.takeIf { it > 0 }
                        ?: xcursor.XcursorGetDefaultSize(display),
                )
            } finally {
                x11.XCloseDisplay(display)
            }
        }

        private fun getAwtDisplayPointer(): Pointer? {
            if (getDisplayMethod != null) {
                val handle = getDisplayMethod.invoke(null) as? Long ?: 0L
                if (handle != 0L) return Pointer(handle)
            }
            val u = unsafe ?: return null
            return try {
                val displayField = xToolkit.getDeclaredField("display")
                val staticFieldBase = u.javaClass.getMethod("staticFieldBase", java.lang.reflect.Field::class.java)
                val staticFieldOffset = u.javaClass.getMethod("staticFieldOffset", java.lang.reflect.Field::class.java)
                val getLong = u.javaClass.getMethod("getLong", Any::class.java, Long::class.javaPrimitiveType)
                val base = staticFieldBase.invoke(u, displayField)
                val offset = (staticFieldOffset.invoke(u, displayField) as Number).toLong()
                val dpy = (getLong.invoke(u, base, offset) as Number).toLong()
                if (dpy != 0L) Pointer(dpy) else null
            } catch (_: Throwable) {
                null
            }
        }

        private fun setCursorPData(cursor: Cursor, handle: Long): Boolean {
            if (setPDataMethod != null) {
                try {
                    setPDataMethod.invoke(cursor, handle)
                    return true
                } catch (_: Throwable) {}
            }
            val u = unsafe
            if (cursorPDataOffset >= 0L && u != null) {
                try {
                    val putLong = u.javaClass.getMethod(
                        "putLong",
                        Any::class.java,
                        Long::class.javaPrimitiveType,
                        Long::class.javaPrimitiveType,
                    )
                    putLong.invoke(u, cursor, cursorPDataOffset, handle)
                    return true
                } catch (_: Throwable) {}
            }
            return false
        }

        private fun load(
            name: String,
            theme: Theme,
        ): Cursor? {
            val images = xcursor.XcursorLibraryLoadImages(name, theme.name, theme.size) ?: return null
            try {
                awtLockMethod?.invoke(null)
                try {
                    val display = X11.Display().apply {
                        pointer = getAwtDisplayPointer() ?: return null
                    }
                    val handle = xcursor.XcursorImagesLoadCursor(display, images)
                    if (handle.toLong() == 0L) return null
                    try {
                        val cursor = NamedCursor(name)
                        if (setCursorPData(cursor, handle.toLong())) {
                            return cursor
                        } else {
                            x11.XFreeCursor(display, handle)
                            return null
                        }
                    } catch (t: Throwable) {
                        x11.XFreeCursor(display, handle)
                        throw t
                    }
                } finally {
                    awtUnlockMethod?.invoke(null)
                }
            } finally {
                xcursor.XcursorImagesDestroy(images)
            }
        }
    }

    private fun Method.accessible(): Method = apply {
        check(trySetAccessible()) { "AWT cursor access requires java.desktop module opens" }
    }
}
