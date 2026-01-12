// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.ui

import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable

@Composable
actual fun isInMultiWindowMode(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val activity = LocalActivity.current
        activity?.isInMultiWindowMode == true
    } else {
        false
    }
}
