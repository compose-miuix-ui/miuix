// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("ktlint:standard:property-naming")

package top.yukonga.miuix.kmp.window.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.FontLoadResult
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.window.LocalMiuixWindowProperties
import top.yukonga.miuix.kmp.window.internal.DesktopOS

val MiuixCaptionButtonWidth = 46.dp

internal const val CaptionButtonMinimizeIconGlyph = '\uE921'
internal const val CaptionButtonMaximizeIconGlyph = '\uE922'
internal const val CaptionButtonRestoreIconGlyph = '\uE923'
internal const val CaptionButtonCloseIconGlyph = '\uE8BB'

@OptIn(ExperimentalTextApi::class)
@Composable
fun rememberWindowsCaptionBarFontIconFamily(): State<FontFamily?> {
    val fontIconFamily = remember { mutableStateOf<FontFamily?>(null) }
    val fontFamilyResolver = LocalFontFamilyResolver.current
    LaunchedEffect(fontFamilyResolver) {
        if (!DesktopOS.isWindows()) return@LaunchedEffect
        fontIconFamily.value = sequenceOf("Segoe Fluent Icons", "Segoe MDL2 Assets")
            .firstNotNullOfOrNull { name ->
                val fontFamily = FontFamily(name)
                runCatching {
                    val result = fontFamilyResolver.resolve(fontFamily).value as? FontLoadResult
                    if (result?.typeface != null && result.typeface?.familyName == name) {
                        fontFamily
                    } else {
                        null
                    }
                }.getOrNull()
            }
    }
    return fontIconFamily
}

@Composable
fun MiuixCaptionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    width: Dp = MiuixCaptionButtonWidth,
    height: Dp = 32.dp,
    isClose: Boolean = false,
    contentDescription: String? = null,
    content: @Composable (iconColor: Color) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDark = LocalMiuixWindowProperties.current.captionButtonIsDarkTheme == true

    val backgroundColor = when {
        !enabled -> Color.Transparent
        isClose && isPressed -> Color(0xFFC62828)
        isClose && isHovered -> Color(0xFFE53935)
        isPressed -> if (isDark) Color.White.copy(alpha = 0.16f) else Color.Black.copy(alpha = 0.12f)
        isHovered -> if (isDark) Color.White.copy(alpha = 0.08f) else Color.Black.copy(alpha = 0.06f)
        else -> Color.Transparent
    }

    val iconColor = when {
        !enabled -> if (isDark) Color.White.copy(alpha = 0.38f) else Color.Black.copy(alpha = 0.38f)
        isClose && (isHovered || isPressed) -> Color.White
        else -> if (isDark) Color.White else Color(0xFF1F1F1F)
    }
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClickLabel = contentDescription,
                onClick = onClick,
            )
            .semantics {
                if (contentDescription != null) {
                    this.contentDescription = contentDescription
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        content(iconColor)
    }
}
