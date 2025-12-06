// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A [FloatingToolbar] that renders its content in a Card, arranged either horizontally or vertically.
 * The actual placement on screen is handled by the parent, typically Scaffold.
 *
 * @param modifier The modifier to be applied to the [FloatingToolbar].
 * @param color Background color of the [FloatingToolbar].
 * @param cornerRadius Corner radius of the [FloatingToolbar].
 * @param outSidePadding Padding outside the [FloatingToolbar].
 * @param shadowElevation The shadow elevation of the [FloatingToolbar].
 * @param showDivider Whether to show the divider line around the [FloatingToolbar].
 * @param content The [Composable] content of the [FloatingToolbar].
 */
@Composable
@NonRestartableComposable
fun FloatingToolbar(
    modifier: Modifier = Modifier,
    color: Color = FloatingToolbarDefaults.defaultColor(),
    cornerRadius: Dp = FloatingToolbarDefaults.CornerRadius,
    outSidePadding: PaddingValues = FloatingToolbarDefaults.OutSidePadding,
    shadowElevation: Dp = 4.dp,
    showDivider: Boolean = false,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val roundedCornerShape = remember(cornerRadius) { ContinuousRoundedRectangle(cornerRadius) }
    val dividerColor = MiuixTheme.colorScheme.dividerLine

    val clipRequired by remember(cornerRadius) { derivedStateOf { cornerRadius > 0.dp } }
    val layerOrClipModifier = remember(shadowElevation, clipRequired, roundedCornerShape, density) {
        when {
            shadowElevation > 0.dp -> Modifier.graphicsLayer(
                shadowElevation = with(density) { shadowElevation.toPx() },
                shape = roundedCornerShape,
                clip = clipRequired
            )

            clipRequired -> Modifier.clip(roundedCornerShape)
            else -> Modifier
        }
    }
    val dividerModifier = remember(showDivider, roundedCornerShape, dividerColor) {
        if (showDivider) {
            Modifier
                .background(
                    color = dividerColor,
                    shape = roundedCornerShape
                )
                .padding(0.75.dp)
        } else Modifier
    }

    Box(
        modifier = modifier
            .padding(outSidePadding)
            .then(dividerModifier)
            .then(layerOrClipModifier)
            .background(color = color)
            .pointerInput(Unit) { detectTapGestures { /* consume click */ } }
    ) {
        content()
    }
}

object FloatingToolbarDefaults {

    /**
     * Default corner radius of the [FloatingToolbar].
     */
    val CornerRadius = 50.dp

    /**
     * Default color of the [FloatingToolbar].
     */
    @Composable
    fun defaultColor() = MiuixTheme.colorScheme.surfaceContainer

    /**
     * Default padding outside the [FloatingToolbar].
     */
    val OutSidePadding = PaddingValues(12.dp, 8.dp)
}
