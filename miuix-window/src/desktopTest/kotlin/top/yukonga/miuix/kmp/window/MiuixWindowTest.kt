// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.window.internal.DesktopOS
import top.yukonga.miuix.kmp.window.platform.linux.LinuxWindowBackend
import top.yukonga.miuix.kmp.window.platform.windows.HitTestResult
import top.yukonga.miuix.kmp.window.platform.windows.toWindowResizeEdge
import kotlin.test.Test
import kotlin.test.assertEquals
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
                DesktopOS.isMacOS() -> 52.dp
                DesktopOS.isLinux() -> 32.dp
                else -> 40.dp
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
        val wCursor = top.yukonga.miuix.kmp.window.platform.linux.LinuxResizeCursor.get(java.awt.Cursor.W_RESIZE_CURSOR)
        val eCursor = top.yukonga.miuix.kmp.window.platform.linux.LinuxResizeCursor.get(java.awt.Cursor.E_RESIZE_CURSOR)
        val nCursor = top.yukonga.miuix.kmp.window.platform.linux.LinuxResizeCursor.get(java.awt.Cursor.N_RESIZE_CURSOR)
        val sCursor = top.yukonga.miuix.kmp.window.platform.linux.LinuxResizeCursor.get(java.awt.Cursor.S_RESIZE_CURSOR)
        val nwCursor = top.yukonga.miuix.kmp.window.platform.linux.LinuxResizeCursor.get(java.awt.Cursor.NW_RESIZE_CURSOR)
        val seCursor = top.yukonga.miuix.kmp.window.platform.linux.LinuxResizeCursor.get(java.awt.Cursor.SE_RESIZE_CURSOR)
        val neCursor = top.yukonga.miuix.kmp.window.platform.linux.LinuxResizeCursor.get(java.awt.Cursor.NE_RESIZE_CURSOR)
        val swCursor = top.yukonga.miuix.kmp.window.platform.linux.LinuxResizeCursor.get(java.awt.Cursor.SW_RESIZE_CURSOR)

        assertEquals("ew-resize", wCursor.name)
        assertEquals("ew-resize", eCursor.name)
        assertEquals("ns-resize", nCursor.name)
        assertEquals("ns-resize", sCursor.name)
        assertEquals("nwse-resize", nwCursor.name)
        assertEquals("nwse-resize", seCursor.name)
        assertEquals("nesw-resize", neCursor.name)
        assertEquals("nesw-resize", swCursor.name)
    }

    @Test
    fun testMiuixWindowDisplaysContent() = runDesktopComposeUiTest {
        setContent {
            MiuixTheme {
                MiuixWindow(
                    onCloseRequest = {},
                    state = rememberWindowState(size = DpSize(400.dp, 300.dp)),
                    title = "Test Window",
                ) {
                    Text("Hello Miuix Window")
                }
            }
        }

        onNodeWithText("Hello Miuix Window").assertIsDisplayed()
    }

    @Test
    fun testMiuixWindowFullscreenHidesCaptionBar() = runDesktopComposeUiTest {
        var captionBarHeight = 40.dp
        setContent {
            MiuixTheme {
                MiuixWindow(
                    onCloseRequest = {},
                    state = rememberWindowState(
                        placement = androidx.compose.ui.window.WindowPlacement.Fullscreen,
                        size = DpSize(400.dp, 300.dp),
                    ),
                    title = "Fullscreen Window",
                ) {
                    captionBarHeight = LocalMiuixWindowInfo.current.captionBarHeight
                    Text("Fullscreen Content")
                }
            }
        }

        onNodeWithText("Fullscreen Content").assertIsDisplayed()
        assertEquals(0.dp, captionBarHeight)
    }
}
