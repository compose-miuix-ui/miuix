// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.internal

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal data class MiuixWindowContentDecoration(
    val inset: Dp = 0.dp,
    val cornerRadius: Dp = 0.dp,
)

internal val LocalMiuixWindowContentDecoration = compositionLocalOf {
    MiuixWindowContentDecoration()
}
