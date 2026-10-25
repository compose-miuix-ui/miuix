// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import top.yukonga.miuix.kmp.window.internal.DesktopOS
import top.yukonga.miuix.kmp.window.internal.isIgnorableComposeDisposalException
import top.yukonga.miuix.kmp.window.platform.linux.LinuxWindowBackend
import top.yukonga.miuix.kmp.window.platform.windows.HitTestResult
import top.yukonga.miuix.kmp.window.platform.windows.toWindowResizeEdge
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class, ExperimentalComposeUiApi::class)
class MiuixWindowTest {

    @Test
    fun testWindowPropertiesDefaultValues() {
        val properties = MiuixWindowProperties<java.awt.Window>(
            captionBarHeight = 44.dp,
            captionButtonHeight = 44.dp,
            captionButtonsVisible = true,
            captionButtonIsDarkTheme = false,
            backgroundType = MiuixWindowBackgroundType.None,
            backgroundIsDarkTheme = false,
        )
        assertEquals(44.dp, properties.captionBarHeight)
        assertEquals(44.dp, properties.captionButtonHeight)
        assertTrue(properties.captionButtonsVisible)
        assertEquals(MiuixWindowBackgroundType.None, properties.backgroundType)
        assertTrue(properties.backgroundType.isSupported())
    }

    @Test
    fun testWindowPropertiesDefaultNullDarkTheme() {
        val properties = MiuixWindowProperties<java.awt.Window>()
        kotlin.test.assertNull(properties.captionButtonIsDarkTheme)
        kotlin.test.assertNull(properties.backgroundIsDarkTheme)
    }

    @Test
    fun testDesktopOsDetection() {
        val os = DesktopOS.current
        assertNotNull(os)
    }

    @Test
    fun testDefaultCaptionBarHeight() = runDesktopComposeUiTest {
        setContent {
            val expected = when {
                DesktopOS.isMacOS() -> 40.dp
                else -> 32.dp
            }
            assertEquals(expected, defaultCaptionBarHeight())
        }
    }

    @Test
    fun testHitTestResultToWindowResizeEdge() {
        assertEquals(WindowResizeEdge.Left, HitTestResult.HTLEFT.toWindowResizeEdge())
        assertEquals(WindowResizeEdge.Right, HitTestResult.HTRIGHT.toWindowResizeEdge())
        assertEquals(WindowResizeEdge.Top, HitTestResult.HTTOP.toWindowResizeEdge())
        assertEquals(WindowResizeEdge.Bottom, HitTestResult.HTBOTTOM.toWindowResizeEdge())
        assertEquals(WindowResizeEdge.TopLeft, HitTestResult.HTTOPLEFT.toWindowResizeEdge())
        assertEquals(WindowResizeEdge.TopRight, HitTestResult.HTTOPRIGHT.toWindowResizeEdge())
        assertEquals(WindowResizeEdge.BottomLeft, HitTestResult.HTBOTTOMLEFT.toWindowResizeEdge())
        assertEquals(WindowResizeEdge.BottomRight, HitTestResult.HTBOTTOMRIGHT.toWindowResizeEdge())
        assertEquals(WindowResizeEdge.None, HitTestResult.HTCLIENT.toWindowResizeEdge())
    }

    @Test
    fun testLinuxWindowBackendDetection() {
        val backend = LinuxWindowBackend.current
        assertNotNull(backend)
    }

    @Test
    fun testLinuxResizeCursor() {
        val wCursor = top.yukonga.miuix.kmp.window.platform.linux.window.LinuxResizeCursor.get(java.awt.Cursor.W_RESIZE_CURSOR)
        val eCursor = top.yukonga.miuix.kmp.window.platform.linux.window.LinuxResizeCursor.get(java.awt.Cursor.E_RESIZE_CURSOR)
        val nCursor = top.yukonga.miuix.kmp.window.platform.linux.window.LinuxResizeCursor.get(java.awt.Cursor.N_RESIZE_CURSOR)
        val sCursor = top.yukonga.miuix.kmp.window.platform.linux.window.LinuxResizeCursor.get(java.awt.Cursor.S_RESIZE_CURSOR)
        val nwCursor = top.yukonga.miuix.kmp.window.platform.linux.window.LinuxResizeCursor.get(java.awt.Cursor.NW_RESIZE_CURSOR)
        val seCursor = top.yukonga.miuix.kmp.window.platform.linux.window.LinuxResizeCursor.get(java.awt.Cursor.SE_RESIZE_CURSOR)
        val neCursor = top.yukonga.miuix.kmp.window.platform.linux.window.LinuxResizeCursor.get(java.awt.Cursor.NE_RESIZE_CURSOR)
        val swCursor = top.yukonga.miuix.kmp.window.platform.linux.window.LinuxResizeCursor.get(java.awt.Cursor.SW_RESIZE_CURSOR)

        val fallbackW = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.W_RESIZE_CURSOR).name
        val fallbackE = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.E_RESIZE_CURSOR).name
        val fallbackN = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.N_RESIZE_CURSOR).name
        val fallbackS = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.S_RESIZE_CURSOR).name
        val fallbackNW = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.NW_RESIZE_CURSOR).name
        val fallbackSE = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.SE_RESIZE_CURSOR).name
        val fallbackNE = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.NE_RESIZE_CURSOR).name
        val fallbackSW = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.SW_RESIZE_CURSOR).name

        assertTrue(wCursor.name in listOf("ew-resize", "size_hor", "col-resize", fallbackW))
        assertTrue(eCursor.name in listOf("ew-resize", "size_hor", "col-resize", fallbackE))
        assertTrue(nCursor.name in listOf("ns-resize", "size_ver", "row-resize", fallbackN))
        assertTrue(sCursor.name in listOf("ns-resize", "size_ver", "row-resize", fallbackS))
        assertTrue(nwCursor.name in listOf("nwse-resize", "size_fdiag", fallbackNW))
        assertTrue(seCursor.name in listOf("nwse-resize", "size_fdiag", fallbackSE))
        assertTrue(neCursor.name in listOf("nesw-resize", "size_bdiag", fallbackNE))
        assertTrue(swCursor.name in listOf("nesw-resize", "size_bdiag", fallbackSW))
    }

    @Test
    fun testWindowAndDialogBackgroundsFollowThemeSurface() = runDesktopComposeUiTest {
        val backgrounds = listOf(
            lightColorScheme().surface,
            darkColorScheme().surface,
            Color(0xFFFFF0F5),
            Color(0xFF201520),
            Color(0xFFF0FFF5),
            Color(0xFF152015),
        )
        var background by mutableStateOf(backgrounds.first())
        setContent {
            MiuixTheme(colors = lightColorScheme(surface = background)) {
                Row {
                    WindowBackgroundBox(
                        transparent = false,
                        backgroundType = MiuixWindowBackgroundType.None,
                        modifier = Modifier.size(40.dp).testTag("windowBackground"),
                    ) {}
                    DialogBackgroundBox(
                        transparent = false,
                        backgroundType = MiuixWindowBackgroundType.None,
                        modifier = Modifier.size(40.dp).testTag("dialogBackground"),
                    ) {}
                }
            }
        }

        for (expected in backgrounds) {
            runOnIdle { background = expected }
            for (tag in listOf("windowBackground", "dialogBackground")) {
                val pixels = onNodeWithTag(tag).captureToImage().toPixelMap()
                assertEquals(expected.toArgb(), pixels[pixels.width / 2, pixels.height / 2].toArgb())
            }
        }
    }

    @Test
    fun testIsIgnorableComposeDisposalException() {
        val direct = IllegalArgumentException("RootNodeOwner is already disposed")
        assertTrue(isIgnorableComposeDisposalException(direct))

        val wrapped = RuntimeException("Wrapped error", direct)
        assertTrue(isIgnorableComposeDisposalException(wrapped))

        val otherMessage = IllegalArgumentException("Other message")
        assertFalse(isIgnorableComposeDisposalException(otherMessage))

        val otherType = IllegalStateException("RootNodeOwner is already disposed")
        assertFalse(isIgnorableComposeDisposalException(otherType))

        val npe = NullPointerException()
        assertFalse(isIgnorableComposeDisposalException(npe))
    }

    @Test
    fun testWindowDraggableAreaSingleActiveRegistration() = runDesktopComposeUiTest {
        val registration = WindowDraggableAreaRegistration()
        var showFirst by mutableStateOf(true)
        var showSecond by mutableStateOf(false)

        setContent {
            CompositionLocalProvider(
                LocalWindowDraggableAreaRegistration provides registration,
                LocalMiuixWindowProperties provides MiuixWindowProperties.default(),
                LocalMiuixWindowInfo provides MiuixWindowInfo(captionBarHeight = 32.dp),
            ) {
                if (showFirst) {
                    WindowDraggableArea()
                }
                if (showSecond) {
                    WindowDraggableArea()
                }
            }
        }

        assertTrue(registration.isRegistered)
        runOnIdle { showFirst = false }
        waitForIdle()
        assertFalse(registration.isRegistered)

        runOnIdle { showSecond = true }
        waitForIdle()
        assertTrue(registration.isRegistered)
    }

    @Test
    fun testWindowDraggableAreaDuplicateRejected() = runDesktopComposeUiTest {
        val registration = WindowDraggableAreaRegistration()
        var duplicate by mutableStateOf(false)

        setContent {
            CompositionLocalProvider(
                LocalWindowDraggableAreaRegistration provides registration,
                LocalMiuixWindowProperties provides MiuixWindowProperties.default(),
                LocalMiuixWindowInfo provides MiuixWindowInfo(captionBarHeight = 32.dp),
            ) {
                WindowDraggableArea()
                if (duplicate) {
                    WindowDraggableArea()
                }
            }
        }

        assertTrue(registration.isRegistered)

        runOnIdle { duplicate = true }
        val error = assertFailsWith<IllegalStateException> {
            waitForIdle()
        }
        assertTrue(error.message.orEmpty().contains("Only one WindowDraggableArea"))
    }

    @Test
    fun testWindowDraggableAreaRequiresRegistration() = runDesktopComposeUiTest {
        val error = assertFailsWith<IllegalStateException> {
            setContent {
                WindowDraggableArea()
            }
        }
        assertTrue(error.message.orEmpty().contains("WindowDraggableArea requires content hosted by a MiuixWindow or MiuixDialogWindow."))
    }
}
