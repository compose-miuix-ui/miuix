// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.utils.MiuixIndication

/**
 * The Miuix theme that provides color and text styles for the Miuix components.
 * This theme supports dynamic color schemes through the [ThemeController].
 *
 * @param controller The [ThemeController] that controls the current color scheme.
 * @param textStyles The text styles for the Miuix components.
 * @param content The content of the Miuix theme.
 */
@Composable
fun MiuixTheme(
    controller: ThemeController,
    textStyles: TextStyles = MiuixTheme.textStyles,
    content: @Composable () -> Unit
) {
    val rawColors = controller.currentColors()

    val colors = remember(rawColors) {
        rawColors.copy().apply { updateColorsFrom(this) }
    }

    val miuixTextStyles = remember(textStyles, colors.onBackground) {
        textStyles.copy().apply { updateColorsFrom(colors.onBackground) }
    }
    val miuixIndication = remember(colors.onBackground) {
        MiuixIndication(color = colors.onBackground)
    }
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTextStyles provides miuixTextStyles,
        LocalIndication provides miuixIndication,
        LocalColorSchemeMode provides controller.colorSchemeMode
    ) {
        content()
    }
}

/**
 * The Miuix theme that provides color and text styles for the Miuix components.
 * This theme uses the provided [colors] and [textStyles].
 *
 * @param colors The color scheme for the Miuix components.
 * @param textStyles The text styles for the Miuix components.
 * @param content The content of the Miuix theme.
 */
@Composable
fun MiuixTheme(
    colors: Colors = MiuixTheme.colorScheme,
    textStyles: TextStyles = MiuixTheme.textStyles,
    content: @Composable () -> Unit
) {
    val miuixColors = remember(colors) {
        colors.copy().apply { updateColorsFrom(colors) }
    }
    val miuixTextStyles = remember(textStyles, colors.onBackground) {
        textStyles.copy().apply { updateColorsFrom(colors.onBackground) }
    }
    val miuixIndication = remember(colors.onBackground) {
        MiuixIndication(color = colors.onBackground)
    }
    CompositionLocalProvider(
        LocalColors provides miuixColors,
        LocalTextStyles provides miuixTextStyles,
        LocalIndication provides miuixIndication,
    ) {
        content()
    }
}

object MiuixTheme {
    val colorScheme: Colors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val textStyles: TextStyles
        @Composable @ReadOnlyComposable get() = LocalTextStyles.current

    val colorSchemeMode: ColorSchemeMode?
        @Composable @ReadOnlyComposable get() = LocalColorSchemeMode.current

    val isDynamicColor: Boolean
        @Composable @ReadOnlyComposable get() = when (colorSchemeMode) {
            ColorSchemeMode.MonetSystem,
            ColorSchemeMode.MonetLight,
            ColorSchemeMode.MonetDark -> true

            else -> false
        }
}

