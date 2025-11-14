// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0
package top.yukonga.miuix.kmp.theme

import androidx.compose.runtime.Composable

@Composable
expect fun platformDynamicColors(dark: Boolean): Colors?
