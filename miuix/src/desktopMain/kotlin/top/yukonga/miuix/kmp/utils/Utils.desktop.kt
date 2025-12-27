// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

actual fun platform(): Platform = Platform.Desktop

@Composable
actual fun getRoundedCorner(): Dp = 0.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun platformDialogProperties(): DialogProperties = DialogProperties(
    dismissOnBackPress = false,
    usePlatformDefaultWidth = false,
    scrimColor = Color.Transparent,
)

@Composable
actual fun RemovePlatformDialogDefaultEffects() {
}
