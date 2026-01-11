// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.ui

import androidx.compose.runtime.Composable

/**
 * Returns whether the current device is in multi-window mode.
 */
@Composable
expect fun isInMultiWindowMode(): Boolean
