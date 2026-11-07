// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import LocalAppState
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Back
import top.yukonga.miuix.kmp.icon.glass.ChevronBackward
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme

@Composable
fun BackNavigationIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val layoutDirection = LocalLayoutDirection.current
    val glassStyle = LocalAppState.current.topAppBarStyle == 1
    val button: @Composable (@Composable () -> Unit) -> Unit = if (glassStyle) {
        { content -> GlassIconButton(onClick = onClick, modifier = modifier, content = content) }
    } else {
        { content -> IconButton(modifier = modifier, onClick = onClick, content = content) }
    }
    button {
        Icon(
            modifier = Modifier
                .then(if (glassStyle) Modifier.size(24.dp) else Modifier)
                .graphicsLayer {
                    if (layoutDirection == LayoutDirection.Rtl) scaleX = -1f
                },
            imageVector = if (glassStyle) MiuixIcons.Glass.ChevronBackward else MiuixIcons.Back,
            contentDescription = "Back",
            tint = if (glassStyle) colorScheme.onSurface else colorScheme.onBackground,
        )
    }
}
