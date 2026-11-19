// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import LocalPlatformDensity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

val LocalContainerSize = compositionLocalOf<IntSize?> { null }

@Composable
fun shouldShowSplitPane(): Boolean {
    val containerSize = LocalContainerSize.current ?: LocalWindowInfo.current.containerSize
    val density = LocalPlatformDensity.current ?: LocalDensity.current
    return with(density) {
        val widthDp = containerSize.width.toDp()
        val heightDp = containerSize.height.toDp()
        val ratio = heightDp / widthDp
        widthDp >= 840.dp || (widthDp >= 600.dp && ratio < 1.2f)
    }
}

@Composable
fun shouldShowThreePanes(): Boolean {
    val containerSize = LocalContainerSize.current ?: LocalWindowInfo.current.containerSize
    val density = LocalPlatformDensity.current ?: LocalDensity.current
    return with(density) {
        val widthDp = containerSize.width.toDp()
        widthDp >= 1200.dp
    }
}

@Composable
fun shouldExpandNavigationRail(): Boolean {
    val containerSize = LocalContainerSize.current ?: LocalWindowInfo.current.containerSize
    val density = LocalPlatformDensity.current ?: LocalDensity.current
    return with(density) {
        val widthDp = containerSize.width.toDp()
        widthDp >= 1200.dp
    }
}
