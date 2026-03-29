// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.RoundedCorner
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun isInMultiWindowMode(): Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    val activity = LocalActivity.current
    activity?.isInMultiWindowMode == true
} else {
    false
}

@Composable
actual fun getRoundedCorner(): Dp = getSystemCornerRadius()

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
private fun getCornerRadiusBottom(context: Context): Int {
    val resourceId = context.resources.getIdentifier("rounded_corner_radius_bottom", "dimen", "android")
    return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
}
