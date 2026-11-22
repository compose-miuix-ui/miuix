// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassNavigationColorTest {
    @Test
    fun lightNavigationFollowsRuntimeAccentChanges() = assertAccentChanges(dark = false)

    @Test
    fun darkNavigationFollowsRuntimeAccentChanges() = assertAccentChanges(dark = true)

    @Test
    fun explicitNavigationColoursSurviveRuntimeAccentChanges() = assertAccentChanges(dark = false, override = true)

    @Test
    fun lightNavigationStaysMonochromeUntilDynamicColoursAreEnabled() = assertAccentChanges(dark = false, dynamicInitially = false)

    @Test
    fun darkNavigationStaysMonochromeUntilDynamicColoursAreEnabled() = assertAccentChanges(dark = true, dynamicInitially = false)

    private fun assertAccentChanges(dark: Boolean, override: Boolean = false, dynamicInitially: Boolean = true) = runComposeUiTest {
        val accent = mutableStateOf(Color.Red)
        val dynamic = mutableStateOf(dynamicInitially)
        val colours = if (dark) darkColorScheme() else lightColorScheme()
        setContent {
            DialogTestHost {
                val mode = when {
                    dynamic.value && dark -> ColorSchemeMode.MonetDark
                    dynamic.value -> ColorSchemeMode.MonetLight
                    dark -> ColorSchemeMode.Dark
                    else -> ColorSchemeMode.Light
                }
                val controller = remember(mode) { ThemeController(colorSchemeMode = mode, keyColor = Color.Red) }
                MiuixTheme(controller = controller) {
                    // Explicit palettes must retain the enclosing controller's dynamic-colour mode.
                    MiuixTheme(colors = colours.copy(primary = accent.value)) {
                        Box(Modifier.size(360.dp, 120.dp)) {
                            if (override) {
                                GlassNavigationBar(
                                    items = listOf(
                                        GlassNavigationItem(MiuixIcons.Basic.Search, "Home"),
                                        GlassNavigationItem(MiuixIcons.Basic.Search, "History"),
                                    ),
                                    selectedIndex = 0,
                                    onSelect = {},
                                    backdrop = null,
                                    defaultWindowInsetsPadding = false,
                                    stroke = null,
                                    shadow = null,
                                    selectedColor = Color.Magenta,
                                    indicatorColor = Color.Green,
                                    indicatorPressedColor = Color.Blue,
                                )
                            } else {
                                GlassNavigationBar(
                                    items = listOf(
                                        GlassNavigationItem(MiuixIcons.Basic.Search, "Home"),
                                        GlassNavigationItem(MiuixIcons.Basic.Search, "History"),
                                    ),
                                    selectedIndex = 0,
                                    onSelect = {},
                                    backdrop = null,
                                    defaultWindowInsetsPadding = false,
                                    stroke = null,
                                    shadow = null,
                                )
                            }
                        }
                    }
                }
            }
        }
        fun assertAppearance(pressed: Boolean) {
            val fill = if (override) {
                if (pressed) Color.Blue else Color.Green
            } else {
                val tint = when {
                    dynamic.value -> accent.value
                    dark -> Color.White
                    else -> Color.Black
                }
                tint.copy(
                    alpha = if (pressed) {
                        if (dark) 0.26f else 0.16f
                    } else {
                        if (dark) 0.12f else 0.06f
                    },
                )
            }
            val background = fill.compositeOver(colours.surfaceContainer)
            val expectedTint = when {
                override -> Color.Magenta
                dynamic.value -> accent.value
                else -> colours.onBackground
            }.compositeOver(background)
            val icon = onNodeWithContentDescription("Home").captureToImage().toPixelMap()
            val hasAccent = (0 until icon.height).any { y ->
                (0 until icon.width).any { x ->
                    val actual = icon[x, y]
                    abs(actual.red - expectedTint.red) < 0.02f &&
                        abs(actual.green - expectedTint.green) < 0.02f &&
                        abs(actual.blue - expectedTint.blue) < 0.02f
                }
            }
            if (!pressed) assertTrue(hasAccent, "Selected content must use the dynamic accent, monochrome colour or explicit override")
            val bounds = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
            val pixels = onRoot().captureToImage().toPixelMap()
            assertColorNear(
                background,
                pixels[(bounds.center.x + bounds.width * 0.25f).roundToInt(), with(density) { 12.dp.roundToPx() }],
            )
            onNodeWithContentDescription("Home").assertIsSelected()
        }
        assertAppearance(pressed = false)
        runOnIdle { accent.value = Color.Green }
        assertAppearance(pressed = false)
        onNodeWithContentDescription("Home").performTouchInput { down(center) }
        assertAppearance(pressed = true)
        onRoot().performTouchInput { up() }
        runOnIdle { dynamic.value = !dynamic.value }
        assertAppearance(pressed = false)
        onNodeWithContentDescription("Home").performTouchInput { down(center) }
        assertAppearance(pressed = true)
        onRoot().performTouchInput { up() }
    }
}
