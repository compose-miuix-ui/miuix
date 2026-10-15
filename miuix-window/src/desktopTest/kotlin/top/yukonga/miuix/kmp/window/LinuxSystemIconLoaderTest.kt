// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import org.jetbrains.skia.Image
import top.yukonga.miuix.kmp.window.internal.DesktopOS
import top.yukonga.miuix.kmp.window.platform.linux.LinuxCaptionButtonType
import top.yukonga.miuix.kmp.window.platform.linux.LinuxCaptionIconPack
import top.yukonga.miuix.kmp.window.platform.linux.LinuxSystemCaptionButton
import top.yukonga.miuix.kmp.window.platform.linux.LinuxSystemIconLoader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class LinuxSystemIconLoaderTest {
    @Test
    fun resolvesCurrentThemeBeforeFallbackAndUsesRestoreWhenMaximized() = withIcons { root, loader ->
        val minimize = root.resolve("custom/actions/16/window-minimize.svg").writeSvg("<path d=\"M8 12 2 6h12z\"/>")
        val restore = root.resolve("custom/actions/16/window-restore.svg").writeSvg("<rect width=\"8\" height=\"8\"/>")
        root.resolve("breeze/actions/16/window-minimize.svg").writeSvg("<rect width=\"16\" height=\"1\"/>")

        assertEquals(minimize, loader.findIconPath(LinuxCaptionIconPack.Kde, "custom", LinuxCaptionButtonType.Minimize, false))
        assertEquals(restore, loader.findIconPath(LinuxCaptionIconPack.Kde, "custom", LinuxCaptionButtonType.Maximize, true))
    }

    @Test
    fun followsThemeInheritanceAndDeclaredDirectoriesWithCycles() = withIcons { root, loader ->
        root.resolve("custom").createDirectories().resolve("index.theme").writeText("[Icon Theme]\nInherits=parent\n")
        root.resolve("parent").createDirectories().resolve("index.theme")
            .writeText("[Icon Theme]\nDirectories=24x24/ui\nInherits=custom\n")
        val close = root.resolve("parent/24x24/ui/window-close-symbolic.svg").writeSvg("<circle cx=\"8\" cy=\"8\" r=\"4\"/>")

        assertEquals(close, loader.findIconPath(LinuxCaptionIconPack.Kde, "custom", LinuxCaptionButtonType.Close, false))
        assertNull(loader.findIconPath(LinuxCaptionIconPack.Kde, "custom", LinuxCaptionButtonType.Minimize, false))
    }

    @Test
    fun rendersViewBoxOnlySvgWithTransparentBackgroundAtDisplayScale() = withIcons { root, loader ->
        root.resolve("breeze/actions/16/window-minimize.svg")
            .writeSvg("<path fill=\"currentColor\" d=\"M8 12 2 6h12z\"/>")
        val bitmap = assertNotNull(loader.load(LinuxCaptionIconPack.Kde, "breeze", LinuxCaptionButtonType.Minimize, false, 32))
        val pixels = bitmap.toPixelMap()

        assertEquals(32, bitmap.width)
        assertEquals(32, bitmap.height)
        assertEquals(0f, pixels[0, 0].alpha)
        assertTrue(pixels[16, 16].alpha > 0.9f)
        assertEquals(0f, pixels[16, 6].alpha)
    }

    @Test
    fun missingAndMalformedSvgUseFallback() = withIcons { root, loader ->
        assertNull(loader.load(LinuxCaptionIconPack.Kde, "missing", LinuxCaptionButtonType.Close, false, 16))
        root.resolve("broken/actions/16/window-close.svg").apply {
            parent.createDirectories()
            writeText("invalid SVG")
        }
        assertNull(loader.load(LinuxCaptionIconPack.Kde, "broken", LinuxCaptionButtonType.Close, false, 16))
    }

    @Test
    fun captionRendersLoadedGlyphAndSwitchesToRestore() = withIcons { root, loader ->
        root.resolve("fixture/actions/16/window-maximize.svg").writeSvg("<rect x=\"1\" y=\"1\" width=\"14\" height=\"14\"/>")
        root.resolve("fixture/actions/16/window-restore.svg").writeSvg("<rect x=\"1\" y=\"1\" width=\"14\" height=\"3\"/>")
        val maximized = mutableStateOf(false)
        runDesktopComposeUiTest {
            setContent {
                LinuxSystemCaptionButton(
                    type = LinuxCaptionButtonType.Maximize,
                    onClick = {},
                    iconPack = LinuxCaptionIconPack.Kde,
                    modifier = Modifier.testTag("caption"),
                    iconTheme = "fixture",
                    maximized = maximized.value,
                    iconLoader = loader,
                )
            }
            fun centerIsOpaque(): Boolean {
                val pixels = onNodeWithTag("caption").captureToImage().toPixelMap()
                // The filled fixture differs from both the old outline and the restore fixture.
                return pixels[pixels.width / 2, pixels.height / 2].alpha > 0.9f
            }
            waitUntil(timeoutMillis = 5000) { centerIsOpaque() }
            runOnIdle { maximized.value = true }
            waitUntil(timeoutMillis = 5000) {
                val pixels = onNodeWithTag("caption").captureToImage().toPixelMap()
                val scale = pixels.width / 46f
                val topStripe = ((pixels.height - 16f * scale) / 2f + 2f * scale).toInt()
                !centerIsOpaque() && pixels[pixels.width / 2, topStripe].alpha > 0.9f
            }
        }
    }

    @Test
    fun installedBreezeGlyphsRenderWhenAvailable() {
        if (!DesktopOS.isLinux()) return
        val directory = Paths.get("/usr/share/icons/breeze")
        if (!Files.isDirectory(directory)) return
        val output = Paths.get("build/reports/linux-caption-icons").createDirectories()
        val loader = LinuxSystemIconLoader(listOf(directory.parent))
        for ((type, maximized) in listOf(
            LinuxCaptionButtonType.Minimize to false,
            LinuxCaptionButtonType.Maximize to false,
            LinuxCaptionButtonType.Maximize to true,
            LinuxCaptionButtonType.Close to false,
        )) {
            val path = assertNotNull(loader.findIconPath(LinuxCaptionIconPack.Kde, "breeze", type, maximized))
            val bitmap = assertNotNull(loader.load(LinuxCaptionIconPack.Kde, "breeze", type, maximized, 32))
            val pixels = IntArray(bitmap.width * bitmap.height).also { bitmap.readPixels(it) }
            assertTrue(pixels.any { it ushr 24 > 0 }, "Empty glyph from $path")
            assertTrue(pixels.any { it ushr 24 == 0 }, "Opaque background in $path")
            val name = if (maximized) "Restore" else type.name
            Image.makeFromBitmap(bitmap.asSkiaBitmap()).use { image ->
                image.encodeToData()!!.use { data -> Files.write(output.resolve("$name.png"), data.bytes) }
            }
        }
    }

    private fun Path.writeSvg(content: String): Path {
        parent.createDirectories()
        writeText("<svg viewBox=\"0 0 16 16\" xmlns=\"http://www.w3.org/2000/svg\">$content</svg>")
        return this
    }

    private fun withIcons(block: (Path, LinuxSystemIconLoader) -> Unit) {
        val root = Files.createTempDirectory("miuix-caption-icons")
        try {
            block(root, LinuxSystemIconLoader(listOf(root)))
        } finally {
            root.toFile().deleteRecursively()
        }
    }
}
