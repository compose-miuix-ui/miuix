// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.effect.bg.BgEffectBackground as BgEffectBackgroundImpl
import top.yukonga.miuix.kmp.effect.bg.DeviceType as DeviceTypeImpl

/**
 * Identifies the device form factor for effect configuration selection.
 *
 * Different device types receive distinct visual presets optimized for their
 * screen characteristics (size, pixel density, viewing distance).
 *
 * @see DeviceTypeImpl
 */
@Deprecated(
    message = "Moved to top.yukonga.miuix.kmp.effect.bg.DeviceType",
    replaceWith = ReplaceWith("DeviceType", "top.yukonga.miuix.kmp.effect.bg.DeviceType"),
)
typealias DeviceType = DeviceTypeImpl

/**
 * A composable that renders an animated gradient background effect.
 *
 * @see BgEffectBackgroundImpl
 */
@Deprecated(
    message = "Moved to top.yukonga.miuix.kmp.effect.bg.BgEffectBackground",
    replaceWith = ReplaceWith(
        "BgEffectBackground",
        "top.yukonga.miuix.kmp.effect.bg.BgEffectBackground",
    ),
)
@Composable
fun BgEffectBackground(
    dynamicBackground: Boolean,
    deviceType: DeviceTypeImpl,
    isDarkTheme: Boolean,
    surface: Color,
    modifier: Modifier = Modifier,
    bgModifier: Modifier = Modifier,
    isFullSize: Boolean = false,
    effectBackground: Boolean = true,
    isOs3Effect: Boolean = true,
    alpha: () -> Float = { 1f },
    content: @Composable (BoxScope.() -> Unit),
) {
    BgEffectBackgroundImpl(
        dynamicBackground = dynamicBackground,
        deviceType = deviceType,
        isDarkTheme = isDarkTheme,
        surface = surface,
        modifier = modifier,
        bgModifier = bgModifier,
        isFullSize = isFullSize,
        effectBackground = effectBackground,
        isOs3Effect = isOs3Effect,
        alpha = alpha,
        content = content,
    )
}
