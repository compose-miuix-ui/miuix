// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Back
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme

@Composable
fun BackNavigationIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val layoutDirection = LocalLayoutDirection.current
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.graphicsLayer {
                if (layoutDirection == LayoutDirection.Rtl) scaleX = -1f
            },
            imageVector = MiuixIcons.Back,
            contentDescription = null,
            tint = colorScheme.onBackground,
        )
    }
}
