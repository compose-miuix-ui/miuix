// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.internal

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.window.LocalMiuixWindowInfo
import top.yukonga.miuix.kmp.window.LocalWindowComponentFrame
import top.yukonga.miuix.kmp.window.WindowComponentFrame

@Immutable
internal data class MiuixWindowContentDecoration(
    val inset: Dp = 0.dp,
    val cornerRadius: Dp = 0.dp,
)

internal val LocalMiuixWindowContentDecoration = compositionLocalOf {
    MiuixWindowContentDecoration()
}

@Composable
internal fun ProvideWindowComponentFrame(
    clientInsets: WindowInsets,
    cornerRadius: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    val captionHeight = LocalMiuixWindowInfo.current.captionBarHeight
    val frame = remember(clientInsets, captionHeight, cornerRadius) {
        WindowComponentFrame(
            insets = clientInsets.add(WindowInsets(top = captionHeight)),
            topCornerRadius = if (captionHeight == 0.dp) cornerRadius else 0.dp,
            bottomCornerRadius = cornerRadius,
        )
    }
    CompositionLocalProvider(LocalWindowComponentFrame provides frame, content = content)
}
