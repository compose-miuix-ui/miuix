package com.example.miuixdemo.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController

/**
 * 全局主题模式，0=跟随系统，1=浅色，2=深色，3=Monet 动态取色
 */
val LocalThemeMode = compositionLocalOf { 0 }

val LocalOnThemeModeChange = compositionLocalOf<((Int) -> Unit)?> { null }

/**
 * 应用主题，通过 [ThemeController] 与 [ColorSchemeMode] 实现明暗切换与 Monet 动态取色。
 */
@Composable
fun MiuixDemoTheme(
    themeMode: Int = 0,
    content: @Composable () -> Unit,
) {
    val controller = remember(themeMode) {
        when (themeMode) {
            1 -> ThemeController(ColorSchemeMode.Light)
            2 -> ThemeController(ColorSchemeMode.Dark)
            3 -> ThemeController(
                colorSchemeMode = ColorSchemeMode.MonetSystem,
                keyColor = KeyColorBlue,
            )

            else -> ThemeController(ColorSchemeMode.System)
        }
    }
    MiuixTheme(controller = controller, content = content)
}

val KeyColorBlue = Color(0xFF3482FF)
val KeyColorGreen = Color(0xFF36D167)
val KeyColorPurple = Color(0xFF7C4DFF)
val KeyColorOrange = Color(0xFFFF5722)
