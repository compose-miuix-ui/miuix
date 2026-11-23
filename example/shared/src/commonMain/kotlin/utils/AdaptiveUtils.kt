// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import LocalPlatformDensity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@Composable
fun shouldShowSplitPane(): Boolean {
    val windowInfo = LocalWindowInfo.current
    val density = LocalPlatformDensity.current ?: LocalDensity.current
    return remember(windowInfo, density) {
        computedStateOf {
            val containerSize = windowInfo.containerSize
            if (containerSize.width <= 0) return@computedStateOf false
            with(density) {
                val widthDp = containerSize.width.toDp()
                val heightDp = containerSize.height.toDp()
                val ratio = heightDp / widthDp
                widthDp >= 840.dp || (widthDp >= 600.dp && ratio < 1.2f)
            }
        }
    }.value
}

@Composable
fun shouldShowThreePanes(): Boolean {
    val windowInfo = LocalWindowInfo.current
    val density = LocalPlatformDensity.current ?: LocalDensity.current
    return remember(windowInfo, density) {
        computedStateOf {
            val containerSize = windowInfo.containerSize
            with(density) {
                val widthDp = containerSize.width.toDp()
                widthDp >= 1200.dp
            }
        }
    }.value
}

@Composable
fun shouldExpandNavigationRail(): Boolean {
    val windowInfo = LocalWindowInfo.current
    val density = LocalPlatformDensity.current ?: LocalDensity.current
    return remember(windowInfo, density) {
        computedStateOf {
            val containerSize = windowInfo.containerSize
            with(density) {
                val widthDp = containerSize.width.toDp()
                widthDp >= 1200.dp
            }
        }
    }.value
}

@Composable
expect fun isInMultiWindowMode(): Boolean
