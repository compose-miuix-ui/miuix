// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun isInMultiWindowMode(): Boolean {
    val context = LocalContext.current
    val activity = context.findActivity()
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        activity?.isInMultiWindowMode == true
    } else {
        false
    }
}

private fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}
