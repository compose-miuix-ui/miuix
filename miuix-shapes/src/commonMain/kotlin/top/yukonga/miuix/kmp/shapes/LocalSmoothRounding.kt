// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shapes

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal to control whether components use G2-continuity smooth rounded corners
 * or standard rounded corners.
 *
 * When `true` (default), components use smooth shapes (e.g., [SmoothRoundedCornerShape]).
 * When `false`, components fall back to standard shapes (e.g., [RoundedCornerShape][androidx.compose.foundation.shape.RoundedCornerShape]).
 */
val LocalSmoothRounding = staticCompositionLocalOf { true }
