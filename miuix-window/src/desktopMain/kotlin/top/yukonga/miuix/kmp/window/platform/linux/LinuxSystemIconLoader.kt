// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Data
import org.jetbrains.skia.Surface
import org.jetbrains.skia.svg.SVGDOM
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ConcurrentHashMap

/** Loads installed caption glyphs; Compose supplies the foreground color using their alpha mask. */
internal class LinuxSystemIconLoader(
    private val iconDirectories: List<Path> = defaultIconDirectories(),
) {
    private data class CacheKey(val path: Path, val pixelSize: Int)

    private val cache = ConcurrentHashMap<CacheKey, ImageBitmap>()

    fun load(
        iconPack: LinuxCaptionIconPack,
        iconTheme: String?,
        type: LinuxCaptionButtonType,
        maximized: Boolean,
        pixelSize: Int,
    ): ImageBitmap? {
        val path = findIconPath(iconPack, iconTheme, type, maximized) ?: return null
        val key = CacheKey(path, pixelSize)
        return cache[key] ?: try {
            rasterize(path, pixelSize).also { cache.putIfAbsent(key, it) }
        } catch (exception: Exception) {
            System.getLogger("MiuixWindow").log(
                System.Logger.Level.DEBUG,
                "Unable to load Linux caption icon: $path",
                exception,
            )
            null
        }
    }

    internal fun findIconPath(
        iconPack: LinuxCaptionIconPack,
        iconTheme: String?,
        type: LinuxCaptionButtonType,
        maximized: Boolean,
    ): Path? {
        val stem = when (type) {
            LinuxCaptionButtonType.Minimize -> "window-minimize"
            LinuxCaptionButtonType.Maximize -> if (maximized) "window-restore" else "window-maximize"
            LinuxCaptionButtonType.Close -> "window-close"
        }
        val visited = mutableSetOf<String>()
        fun findInTheme(theme: String): Path? {
            if (visited.size >= 16 || !isThemeName(theme) || !visited.add(theme)) return null
            val directories = mutableSetOf("actions/16", "16x16/actions", "symbolic/ui", "symbolic/actions", "scalable/actions")
            val inherited = mutableSetOf<String>()
            for (root in iconDirectories) {
                val index = root.resolve(theme).resolve("index.theme")
                readThemeIndex(index).forEach { (key, values) ->
                    when (key) {
                        "Directories", "ScaledDirectories" -> directories.addAll(values.filter(::isRelativeDirectory))
                        "Inherits" -> inherited.addAll(values)
                    }
                }
            }
            for (root in iconDirectories) {
                for (directory in directories) {
                    for (name in listOf("$stem.svg", "$stem-symbolic.svg")) {
                        val path = root.resolve(theme).resolve(directory).resolve(name)
                        if (Files.isRegularFile(path)) return path
                    }
                }
            }
            return inherited.firstNotNullOfOrNull(::findInTheme)
        }

        val theme = iconTheme?.trim()?.trim('\'')?.takeIf(::isThemeName)
        val fallback = if (iconPack == LinuxCaptionIconPack.Kde) "breeze" else "Adwaita"
        return listOfNotNull(theme, theme?.lowercase(), fallback, "hicolor")
            .firstNotNullOfOrNull(::findInTheme)
    }

    private fun rasterize(path: Path, pixelSize: Int): ImageBitmap = Data.makeFromBytes(Files.readAllBytes(path)).use { data ->
        SVGDOM(data).use { svg ->
            checkNotNull(svg.root).use {
                // Breeze icons often specify only viewBox, without explicit width/height.
                svg.setContainerSize(pixelSize.toFloat(), pixelSize.toFloat())
                Surface.makeRasterN32Premul(pixelSize, pixelSize).use { surface ->
                    surface.canvas.clear(org.jetbrains.skia.Color.TRANSPARENT)
                    svg.render(surface.canvas)
                    surface.makeImageSnapshot().use { image -> image.toComposeImageBitmap() }
                }
            }
        }
    }

    private fun readThemeIndex(path: Path): Map<String, List<String>> = try {
        var inIconTheme = false
        buildMap {
            if (Files.isRegularFile(path)) {
                Files.readAllLines(path).forEach { line ->
                    val trimmed = line.trim()
                    if (trimmed.startsWith('[')) {
                        inIconTheme = trimmed == "[Icon Theme]"
                    } else if (inIconTheme && !trimmed.startsWith('#') && '=' in trimmed) {
                        val (key, value) = trimmed.split('=', limit = 2)
                        put(key.trim(), value.split(',').map(String::trim).filter(String::isNotEmpty))
                    }
                }
            }
        }
    } catch (_: Exception) {
        emptyMap()
    }

    companion object {
        val Default = LinuxSystemIconLoader()

        private fun isThemeName(value: String): Boolean = value.isNotBlank() &&
            value != "." && value != ".." && '/' !in value && '\\' !in value

        private fun isRelativeDirectory(value: String): Boolean = isThemeName(value.replace('/', '-')) &&
            !value.startsWith('/') && value.split('/').none { it == ".." }

        private fun defaultIconDirectories(): List<Path> {
            val userHome = Paths.get(System.getProperty("user.home"))
            val dataHome = System.getenv("XDG_DATA_HOME")?.takeIf(String::isNotBlank)
                ?.let(Paths::get) ?: userHome.resolve(".local/share")
            val dataDirectories = System.getenv("XDG_DATA_DIRS")?.takeIf(String::isNotBlank)
                ?: "/usr/local/share:/usr/share"
            return buildList {
                add(dataHome.resolve("icons"))
                add(userHome.resolve(".icons"))
                dataDirectories.split(':').filter(String::isNotBlank).forEach { add(Paths.get(it).resolve("icons")) }
            }.distinct()
        }
    }
}
