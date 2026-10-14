// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("ktlint:standard:function-naming", "FunctionName")

package top.yukonga.miuix.kmp.window.platform.linux

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer
import java.awt.Cursor
import java.awt.Toolkit

/**
 * Loads themed resize cursors using the same directional names as Linux window managers.
 * Falls back to AWT's predefined cursor if native theme loading fails.
 */
internal object LinuxResizeCursor {
    private val loader: CursorLoader? by lazy {
        try {
            CursorLoader()
        } catch (_: Throwable) {
            null
        }
    }

    fun get(type: Int): Cursor {
        val names = when (type) {
            Cursor.W_RESIZE_CURSOR -> listOf("ew-resize", "size_hor", "col-resize", "w-resize")
            Cursor.E_RESIZE_CURSOR -> listOf("ew-resize", "size_hor", "col-resize", "e-resize")
            Cursor.N_RESIZE_CURSOR -> listOf("ns-resize", "size_ver", "row-resize", "n-resize")
            Cursor.S_RESIZE_CURSOR -> listOf("ns-resize", "size_ver", "row-resize", "s-resize")
            Cursor.NW_RESIZE_CURSOR -> listOf("nwse-resize", "size_fdiag", "nw-resize", "top_left_corner")
            Cursor.SE_RESIZE_CURSOR -> listOf("nwse-resize", "size_fdiag", "se-resize", "bottom_right_corner")
            Cursor.NE_RESIZE_CURSOR -> listOf("nesw-resize", "size_bdiag", "ne-resize", "top_right_corner")
            Cursor.SW_RESIZE_CURSOR -> listOf("nesw-resize", "size_bdiag", "sw-resize", "bottom_left_corner")
            else -> return Cursor.getPredefinedCursor(type)
        }
        return try {
            names.firstNotNullOfOrNull { loader?.get(it) } ?: Cursor.getPredefinedCursor(type)
        } catch (_: Throwable) {
            Cursor.getPredefinedCursor(type)
        }
    }

    private class NamedCursor(name: String) : Cursor(name)

    private object CursorPDataSetter {
        private val setPDataMethod = runCatching {
            Cursor::class.java.getDeclaredMethod("setPData", Long::class.javaPrimitiveType).apply {
                trySetAccessible()
            }
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

        private val pDataOffset: Long by lazy {
            try {
                val unsafeObj = unsafe ?: return@lazy -1L
                val field = Cursor::class.java.getDeclaredField("pData")
                val method = unsafeObj.javaClass.getMethod("objectFieldOffset", java.lang.reflect.Field::class.java)
                (method.invoke(unsafeObj, field) as Number).toLong()
            } catch (_: Throwable) {
                -1L
            }
        }

        fun set(cursor: Cursor, handle: Long): Boolean {
            if (setPDataMethod != null) {
                try {
                    setPDataMethod.invoke(cursor, handle)
                    return true
                } catch (_: Throwable) {}
            }
            val u = unsafe
            if (pDataOffset >= 0L && u != null) {
                try {
                    val putLong = u.javaClass.getMethod(
                        "putLong",
                        Any::class.java,
                        Long::class.javaPrimitiveType,
                        Long::class.javaPrimitiveType,
                    )
                    putLong.invoke(u, cursor, pDataOffset, handle)
                    return true
                } catch (_: Throwable) {}
            }
            return false
        }

        fun getAwtDisplay(): Pointer? {
            val u = unsafe ?: return null
            try {
                val xToolkitClass = Class.forName("sun.awt.X11.XToolkit")
                val displayField = xToolkitClass.getDeclaredField("display")
                val staticFieldBase = u.javaClass.getMethod("staticFieldBase", java.lang.reflect.Field::class.java)
                val staticFieldOffset = u.javaClass.getMethod("staticFieldOffset", java.lang.reflect.Field::class.java)
                val getLong = u.javaClass.getMethod("getLong", Any::class.java, Long::class.javaPrimitiveType)
                val base = staticFieldBase.invoke(u, displayField)
                val offset = (staticFieldOffset.invoke(u, displayField) as Number).toLong()
                val dpyLong = (getLong.invoke(u, base, offset) as Number).toLong()
                if (dpyLong != 0L) return Pointer(dpyLong)
            } catch (_: Throwable) {}
            return null
        }
    }

    private data class Theme(
        val name: String?,
        val size: Int,
    )

    private class CursorLoader {
        private val toolkit = Toolkit.getDefaultToolkit()
        private val x11 = runCatching { Native.load("X11", Xlib::class.java) }.getOrNull()
        private val xcursor = Native.load("Xcursor", Xcursor::class.java)
        private var persistentDisplay: Pointer? = null
        private var theme: Theme? = null
        private val cursors = mutableMapOf<String, Cursor?>()

        @Synchronized
        fun get(name: String): Cursor? {
            val currentTheme = readTheme()
            if (currentTheme != theme) {
                cursors.clear()
                theme = currentTheme
            }
            if (!cursors.containsKey(name)) {
                cursors[name] = load(name, currentTheme)
            }
            return cursors[name]
        }

        private fun getDisplay(): Pointer? {
            CursorPDataSetter.getAwtDisplay()?.let { return it }
            if (persistentDisplay == null) {
                persistentDisplay = runCatching { x11?.XOpenDisplay(null) }.getOrNull()
            }
            return persistentDisplay
        }

        private fun readTheme(): Theme {
            val desktopName = toolkit.getDesktopProperty("gnome.Gtk/CursorThemeName") as? String
            val desktopSize = toolkit.getDesktopProperty("gnome.Gtk/CursorThemeSize") as? Int
            val envTheme = System.getenv("XCURSOR_THEME")?.takeIf { it.isNotEmpty() }
            val envSize = System.getenv("XCURSOR_SIZE")?.toIntOrNull()?.takeIf { it > 0 }

            val display = getDisplay()

            val themeName = envTheme
                ?: desktopName?.takeIf { it.isNotEmpty() }
                ?: display?.let { runCatching { xcursor.XcursorGetTheme(it)?.getString(0) }.getOrNull() }
            val themeSize = envSize
                ?: desktopSize?.takeIf { it > 0 }
                ?: display?.let { runCatching { xcursor.XcursorGetDefaultSize(it) }.getOrNull() }
                ?: 24
            return Theme(name = themeName, size = themeSize)
        }

        private fun load(name: String, theme: Theme): Cursor? {
            val images = xcursor.XcursorLibraryLoadImages(name, theme.name, theme.size) ?: return null
            try {
                val display = getDisplay() ?: return null
                val handle = runCatching { xcursor.XcursorImagesLoadCursor(display, images) }.getOrDefault(0L)
                if (handle == 0L) return null
                val namedCursor = NamedCursor(name)
                if (CursorPDataSetter.set(namedCursor, handle)) {
                    return namedCursor
                }
                runCatching { x11?.XFreeCursor(display, handle) }
                return null
            } catch (_: Throwable) {
                return null
            } finally {
                runCatching { xcursor.XcursorImagesDestroy(images) }
            }
        }
    }

    private interface Xlib : Library {
        fun XOpenDisplay(name: String?): Pointer?
        fun XCloseDisplay(display: Pointer): Int
        fun XFreeCursor(display: Pointer, cursor: Long): Int
    }

    private interface Xcursor : Library {
        fun XcursorGetTheme(display: Pointer): Pointer?
        fun XcursorGetDefaultSize(display: Pointer): Int
        fun XcursorLibraryLoadImages(name: String, theme: String?, size: Int): Pointer?
        fun XcursorImagesLoadCursor(display: Pointer, images: Pointer): Long
        fun XcursorImagesDestroy(images: Pointer)
    }
}
