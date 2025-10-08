// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.RoundedCorner
import androidx.activity.BackEventCompat as AndroidBackEventCompat
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator
import kotlin.math.max
import kotlin.math.min

@Composable
@SuppressLint("ConfigurationScreenWidthHeight")
actual fun getWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val windowMetrics = remember(configuration, context) {
        WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context)
    }
    val widthPx = windowMetrics.bounds.width()
    val heightPx = windowMetrics.bounds.height()
    val windowSize by remember(widthPx, heightPx, configuration) {
        derivedStateOf {
            val screenWidthDp = configuration.screenWidthDp
            val screenHeightDp = configuration.screenHeightDp
            if (screenWidthDp > screenHeightDp)
                WindowSize(max(widthPx, heightPx), min(widthPx, heightPx))
            else
                WindowSize(min(widthPx, heightPx), max(widthPx, heightPx))
        }
    }
    return windowSize
}

actual fun platform(): Platform = Platform.Android

@Composable
actual fun getRoundedCorner(): Dp = getSystemCornerRadius()

// Represents a rounded corner of the display.
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
    val dp = (roundedCornerRadius / density).dp
    return if (dp <= 32.dp) 0.dp else dp
}

// from https://dev.mi.com/distribute/doc/details?pId=1631
@SuppressLint("DiscouragedApi")
fun getCornerRadiusBottom(context: Context): Int {
    val resourceId = context.resources.getIdentifier("rounded_corner_radius_bottom", "dimen", "android")
    return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun BackHandler(
    enabled: Boolean,
    onBack: () -> Unit
) {
    androidx.compose.ui.backhandler.BackHandler(enabled = enabled, onBack = onBack)
}

@Composable
actual fun PredictiveBackHandler(
    enabled: Boolean,
    onBackStarted: ((BackEventCompat) -> Unit)?,
    onBackProgressed: ((BackEventCompat) -> Unit)?,
    onBackCancelled: (() -> Unit)?,
    onBack: () -> Unit
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    
    val currentOnBackStarted by rememberUpdatedState(onBackStarted)
    val currentOnBackProgressed by rememberUpdatedState(onBackProgressed)
    val currentOnBackCancelled by rememberUpdatedState(onBackCancelled)
    val currentOnBack by rememberUpdatedState(onBack)
    val currentEnabled by rememberUpdatedState(enabled)
    
    DisposableEffect(backDispatcher) {
        val callback = object : OnBackPressedCallback(currentEnabled) {
            override fun handleOnBackStarted(backEvent: AndroidBackEventCompat) {
                currentOnBackStarted?.invoke(
                    BackEventCompat(
                        progress = backEvent.progress,
                        swipeEdge = backEvent.swipeEdge,
                        touchX = backEvent.touchX,
                        touchY = backEvent.touchY
                    )
                )
            }
            
            override fun handleOnBackProgressed(backEvent: AndroidBackEventCompat) {
                currentOnBackProgressed?.invoke(
                    BackEventCompat(
                        progress = backEvent.progress,
                        swipeEdge = backEvent.swipeEdge,
                        touchX = backEvent.touchX,
                        touchY = backEvent.touchY
                    )
                )
            }
            
            override fun handleOnBackCancelled() {
                currentOnBackCancelled?.invoke()
            }
            
            override fun handleOnBackPressed() {
                currentOnBack()
            }
        }
        
        backDispatcher?.addCallback(callback)
        
        onDispose {
            callback.remove()
        }
    }
    
    // Update enabled state when it changes
    DisposableEffect(enabled) {
        onDispose { }
    }
}