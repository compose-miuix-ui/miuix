// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import top.yukonga.miuix.kmp.window.CaptionButtonsAlign
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonWidth
import java.util.concurrent.TimeUnit

internal enum class LinuxCaptionButtonType {
    Minimize,
    Maximize,
    Close,
}

internal enum class LinuxCaptionIconPack {
    Gnome,
    Kde,
}

internal data class LinuxSystemDecoration(
    val buttons: List<LinuxCaptionButtonType>,
    val alignment: CaptionButtonsAlign,
    val iconPack: LinuxCaptionIconPack,
    val iconTheme: String? = null,
) {
    val buttonsWidth = MiuixCaptionButtonWidth * buttons.size

    companion object {
        val Default = LinuxSystemDecoration(
            buttons = listOf(
                LinuxCaptionButtonType.Minimize,
                LinuxCaptionButtonType.Maximize,
                LinuxCaptionButtonType.Close,
            ),
            alignment = CaptionButtonsAlign.End,
            iconPack = LinuxCaptionIconPack.Gnome,
            iconTheme = null,
        )
    }
}

internal object LinuxSystemDecorationAdapter {
    private const val GSETTINGS = "gsettings"
    private const val BUTTON_LAYOUT_SCHEMA = "org.gnome.desktop.wm.preferences"
    private const val BUTTON_LAYOUT_KEY = "button-layout"
    private const val INTERFACE_SCHEMA = "org.gnome.desktop.interface"
    private const val ICON_THEME_KEY = "icon-theme"
    private const val COMMAND_TIMEOUT_MS = 1000L

    @Volatile
    private var cached: LinuxSystemDecoration? = null

    suspend fun load(): LinuxSystemDecoration = withContext(Dispatchers.IO) {
        cached ?: synchronized(this@LinuxSystemDecorationAdapter) {
            cached ?: readFromDesktop().also { cached = it }
        }
    }

    internal fun parse(
        buttonLayout: String?,
        desktop: String? = null,
        iconTheme: String? = null,
    ): LinuxSystemDecoration {
        val parts = buttonLayout
            ?.trim()
            ?.trim('\'')
            ?.split(':', limit = 2)
            .orEmpty()
        val leading = parseButtons(parts.getOrNull(0))
        val trailing = parseButtons(parts.getOrNull(1))
        val buttons = (leading + trailing).distinct()
        val parsedButtons = buttons.ifEmpty { LinuxSystemDecoration.Default.buttons }
        val alignment = if (leading.isEmpty() || trailing.isNotEmpty()) {
            CaptionButtonsAlign.End
        } else {
            CaptionButtonsAlign.Start
        }

        return LinuxSystemDecoration(
            buttons = parsedButtons,
            alignment = alignment,
            iconPack = resolveIconPack(desktop, iconTheme),
            iconTheme = iconTheme,
        )
    }

    internal fun resolveIconPack(desktop: String?, iconTheme: String?): LinuxCaptionIconPack {
        val theme = iconTheme.orEmpty().lowercase()
        return when {
            theme.startsWith("breeze") -> LinuxCaptionIconPack.Kde

            theme.startsWith("adwaita") || theme.startsWith("yaru") -> LinuxCaptionIconPack.Gnome

            desktop.orEmpty().split(':').any { it.equals("kde", ignoreCase = true) || it.equals("plasma", ignoreCase = true) } ->
                LinuxCaptionIconPack.Kde

            else -> LinuxCaptionIconPack.Gnome
        }
    }

    private fun readFromDesktop(): LinuxSystemDecoration {
        val desktop = System.getenv("XDG_CURRENT_DESKTOP").orEmpty()
        val desktopNames = desktop.split(':').map(String::lowercase)
        val supportedDesktop = desktopNames.any { it == "gnome" || it == "kde" || it == "plasma" || it == "ubuntu" }
        val buttonLayout = if (supportedDesktop) {
            readGSettings(BUTTON_LAYOUT_SCHEMA, BUTTON_LAYOUT_KEY)
        } else {
            null
        }
        val iconTheme = if (supportedDesktop) {
            readGSettings(INTERFACE_SCHEMA, ICON_THEME_KEY)
        } else {
            null
        }
        return parse(buttonLayout, desktop, iconTheme)
    }

    private fun parseButtons(value: String?): List<LinuxCaptionButtonType> = value
        .orEmpty()
        .split(',')
        .mapNotNull { token ->
            when (token.trim().lowercase()) {
                "minimize" -> LinuxCaptionButtonType.Minimize
                "maximize" -> LinuxCaptionButtonType.Maximize
                "close" -> LinuxCaptionButtonType.Close
                else -> null
            }
        }
        .distinct()

    private fun readGSettings(schema: String, key: String): String? {
        val process = runCatching {
            ProcessBuilder(GSETTINGS, "get", schema, key)
                .redirectErrorStream(true)
                .start()
        }.getOrNull() ?: return null

        return runCatching {
            if (!process.waitFor(COMMAND_TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                process.destroyForcibly()
                return null
            }
            if (process.exitValue() != 0) return null
            process.inputStream.bufferedReader().use { it.readText().trim().trim('\'') }
        }.getOrNull()?.takeIf(String::isNotEmpty)
    }
}

@Composable
internal fun rememberLinuxSystemDecoration(): LinuxSystemDecoration {
    var decoration by remember { mutableStateOf(LinuxSystemDecoration.Default) }

    LaunchedEffect(Unit) {
        decoration = LinuxSystemDecorationAdapter.load()
    }

    return decoration
}
