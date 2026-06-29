// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Skiko targets have no OS-level screen corner to match, so no rounding is applied. */
@Composable
actual fun rememberNavSystemCornerRadius(): Dp = 0.dp
