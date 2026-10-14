// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.window.internal.DesktopOS
import top.yukonga.miuix.kmp.window.platform.macos.MacOSWindowMetrics
import java.awt.Window

@Composable
fun defaultCaptionBarHeight(): Dp = when {
    DesktopOS.isMacOS() -> MacOSWindowMetrics.defaultCaptionBarHeight
    else -> 32.dp
}

/**
 * Properties for configuring [MiuixWindow] and [MiuixDialogWindow].
 */
@Stable
data class MiuixWindowProperties<T : Window>(
    val minSize: DpSize = DpSize.Zero,
    val onVisibleChange: (T, Boolean) -> Unit = { _, _ -> },
    val onResizeEdgeChange: (T, WindowResizeEdge) -> Unit = { _, _ -> },
    val captionBarHeight: Dp = 32.dp,
    val captionButtonsVisible: Boolean = true,
    val captionButtonHeight: Dp = captionBarHeight,
    val captionButtonIsDarkTheme: Boolean? = null,
    val backgroundType: MiuixWindowBackgroundType = MiuixWindowBackgroundType.None,
    val backgroundIsDarkTheme: Boolean? = null,
    val extraDisplayScale: Float = 1.0f,
    val extraFontScale: Float = 1.0f,
    val minimizeButtonEnabled: Boolean = true,
    val maximizeOrRestoreButtonEnabled: Boolean = true,
    val moveable: Boolean = true,
) {
    companion object {
        @Composable
        fun <T : Window> default(
            minSize: DpSize = DpSize.Zero,
            onVisibleChange: (T, Boolean) -> Unit = { _, _ -> },
            onResizeEdgeChange: (T, WindowResizeEdge) -> Unit = { _, _ -> },
            captionBarHeight: Dp = defaultCaptionBarHeight(),
            captionButtonsVisible: Boolean = true,
            captionButtonHeight: Dp = captionBarHeight,
            captionButtonIsDarkTheme: Boolean? = null,
            backgroundType: MiuixWindowBackgroundType = MiuixWindowBackgroundType.None,
            backgroundIsDarkTheme: Boolean? = null,
            extraDisplayScale: Float = 1.0f,
            extraFontScale: Float = 1.0f,
            minimizeButtonEnabled: Boolean = true,
            maximizeOrRestoreButtonEnabled: Boolean = true,
            moveable: Boolean = true,
        ): MiuixWindowProperties<T> = MiuixWindowProperties(
            minSize = minSize,
            onVisibleChange = onVisibleChange,
            onResizeEdgeChange = onResizeEdgeChange,
            captionBarHeight = captionBarHeight,
            captionButtonsVisible = captionButtonsVisible,
            captionButtonHeight = captionButtonHeight,
            captionButtonIsDarkTheme = captionButtonIsDarkTheme,
            backgroundType = backgroundType,
            backgroundIsDarkTheme = backgroundIsDarkTheme,
            extraDisplayScale = extraDisplayScale,
            extraFontScale = extraFontScale,
            minimizeButtonEnabled = minimizeButtonEnabled,
            maximizeOrRestoreButtonEnabled = maximizeOrRestoreButtonEnabled,
            moveable = moveable,
        )
    }
}

val LocalMiuixWindowProperties = staticCompositionLocalOf {
    MiuixWindowProperties<Window>()
}
