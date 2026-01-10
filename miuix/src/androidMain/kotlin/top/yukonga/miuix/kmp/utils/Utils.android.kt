// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.RoundedCorner
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider

actual fun platform(): Platform = Platform.Android

@Composable
actual fun getRoundedCorner(): Dp = getSystemCornerRadius()

// Represents a rounded corner of the display.
@SuppressLint("NewApi")
@Composable
private fun getSystemCornerRadius(): Dp {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val insets = LocalView.current.rootWindowInsets

    val roundedCornerRadius = remember(context, insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            insets?.getRoundedCorner(RoundedCorner.POSITION_BOTTOM_LEFT)?.radius
                ?.takeIf { it > 0 }
                ?: getCornerRadiusBottom(context)
        } else {
            getCornerRadiusBottom(context)
        }
    }
    return (roundedCornerRadius / density).dp
}

// from https://dev.mi.com/distribute/doc/details?pId=1631
@SuppressLint("DiscouragedApi")
fun getCornerRadiusBottom(context: Context): Int {
    val resourceId = context.resources.getIdentifier("rounded_corner_radius_bottom", "dimen", "android")
    return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
}

@Composable
actual fun platformDialogProperties(): DialogProperties = DialogProperties(
    dismissOnBackPress = false,
    usePlatformDefaultWidth = false,
    decorFitsSystemWindows = false,
)

@SuppressLint("NewApi")
@Composable
actual fun RemovePlatformDialogDefaultEffects() {
    val parent = LocalView.current.parent
    DisposableEffect(parent) {
        val provider = parent as? DialogWindowProvider
        val window = provider?.window
        window?.setWindowAnimations(0)
        window?.setDimAmount(0f)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        onDispose { }
    }
}
