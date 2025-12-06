// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.theme.LocalContentColor
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A [Surface] component with Miuix style.
 *
 * @param modifier The modifier to be applied to the [Surface].
 * @param shape The shape of the [Surface].
 * @param color The color of the [Surface].
 * @param contentColor The content color of the [Surface].
 * @param border The border of the [Surface].
 * @param shadowElevation The shadow elevation of the [Surface].
 * @param content The [Composable] content of the [Surface].
 */
@Composable
@NonRestartableComposable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MiuixTheme.colorScheme.surface,
    contentColor: Color = MiuixTheme.colorScheme.onSurface,
    border: BorderStroke? = null,
    shadowElevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val shadowElevationPx by remember(density, shadowElevation) {
        derivedStateOf { with(density) { shadowElevation.toPx() } }
    }
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape,
                    backgroundColor = color,
                    border = border,
                    shadowElevation = shadowElevationPx
                )
                .semantics(mergeDescendants = false) {
                    isTraversalGroup = true
                },
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

/**
 * A [Surface] component with Miuix style.
 *
 * @param onClick The callback when the [Surface] is clicked.
 * @param modifier The modifier to be applied to the [Surface].
 * @param enabled Whether the [Surface] is enabled.
 * @param shape The shape of the [Surface].
 * @param color The color of the [Surface].
 * @param contentColor The content color of the [Surface].
 * @param border The border of the [Surface].
 * @param shadowElevation The shadow elevation of the [Surface].
 * @param content The [Composable] content of the [Surface].
 */
@Composable
@NonRestartableComposable
fun Surface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = MiuixTheme.colorScheme.surface,
    contentColor: Color = MiuixTheme.colorScheme.onSurface,
    border: BorderStroke? = null,
    shadowElevation: Dp = 0.dp,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val currentOnClick by rememberUpdatedState(onClick)
    val density = LocalDensity.current
    val shadowElevationPx by remember(density, shadowElevation) {
        derivedStateOf { with(density) { shadowElevation.toPx() } }
    }
    val indication = LocalIndication.current
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape,
                    backgroundColor = color,
                    border = border,
                    shadowElevation = shadowElevationPx
                )
                .then(
                    remember(enabled, interactionSource, indication, currentOnClick) {
                        Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = indication,
                            enabled = enabled,
                            onClick = currentOnClick,
                        )
                    }
                ),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

@Stable
private fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke?,
    shadowElevation: Float,
) =
    this.then(
        if (shadowElevation > 0f) {
            Modifier.graphicsLayer(
                shadowElevation = shadowElevation,
                shape = shape,
                clip = false
            )
        } else {
            Modifier
        }
    )
        .then(if (border != null) Modifier.border(border, shape) else Modifier)
        .background(color = backgroundColor, shape = shape)
        .clip(shape)
