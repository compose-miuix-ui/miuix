// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.animation

// Adapted from Kyant0/AndroidLiquidGlass — https://github.com/Kyant0/AndroidLiquidGlass (Apache 2.0).

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * A touch-responsive radial highlight effect that follows the pointer position.
 *
 * Renders a subtle white overlay and a radial gradient spotlight centered on the
 * current touch position, with intensity driven by an animated press progress value.
 * The highlight fades in when the pointer is pressed and fades out when released.
 *
 * This effect is designed to be layered on top of translucent or blurred surfaces
 * (e.g., liquid glass navigation bars) to provide visual feedback for touch interactions.
 *
 * ### Rendering
 *
 * When [pressProgress] > 0, two overlays are drawn using [BlendMode.Plus]:
 * 1. A uniform white fill at `0.04 * progress` alpha.
 * 2. A radial gradient centered on the touch position, transitioning from
 *    `0.04 * progress` alpha at the center to transparent at the edge.
 *
 * ### Usage
 *
 * ```kotlin
 * val highlight = remember(animationScope) {
 *     InteractiveHighlight(
 *         animationScope = animationScope,
 *         position = { size, offset -> Offset(size.width / 2f, size.height / 2f) },
 *     )
 * }
 *
 * Box(
 *     modifier = Modifier
 *         .then(highlight.modifier)      // Drawing overlay
 *         .then(highlight.gestureModifier) // Touch handling
 * )
 * ```
 *
 * @param animationScope Coroutine scope used to animate the press progress.
 * @param position Optional lambda computing the highlight center from the layer
 *   size and raw pointer position. Defaults to using the raw pointer position directly.
 */
class InteractiveHighlight(
    private val animationScope: CoroutineScope,
    private val position: (size: Size, offset: Offset) -> Offset = { _, offset -> offset },
) {

    private val pressProgressAnimationSpec = spring(0.5f, 300f, 0.001f)

    private val pressProgressAnimation = Animatable(0f, 0.001f)
    private var pointerPosition by mutableStateOf(Offset.Zero)

    /**
     * The current press progress, in range `[0, 1]`.
     *
     * `0f` when no touch is active; `1f` when fully pressed. Drives the
     * intensity of the highlight overlay.
     */
    val pressProgress: Float get() = pressProgressAnimation.value

    /**
     * A [Modifier] that draws the highlight overlay on top of the content.
     *
     * Apply this modifier **after** `drawContent()` (i.e., in the modifier chain
     * position where overlays should appear above the component's content).
     */
    val modifier: Modifier = Modifier.drawWithContent {
        val progress = pressProgressAnimation.value
        if (progress > 0f) {
            drawRect(
                color = Color.White.copy(alpha = 0.04f * progress),
                blendMode = BlendMode.Plus,
            )
            val pos = position(size, pointerPosition)
            val radius = (size.minDimension * 1.2f).coerceAtLeast(1f)
            val center = Offset(
                x = pos.x.coerceIn(0f, size.width),
                y = pos.y.coerceIn(0f, size.height),
            )
            val spotColor = Color.White.copy(alpha = 0.04f * progress)
            drawRect(
                brush = Brush.radialGradient(
                    colorStops = arrayOf(
                        0.0f to spotColor,
                        0.5f to spotColor,
                        1.0f to Color.White.copy(alpha = 0f),
                    ),
                    center = center,
                    radius = radius,
                ),
                blendMode = BlendMode.Plus,
            )
        }
        drawContent()
    }

    /**
     * A [Modifier] that captures drag gestures to drive the press animation.
     *
     * Tracks pointer down/up events and animates [pressProgress] between 0 and 1
     * using spring physics. Update the pointer position on each drag event to
     * move the highlight center.
     */
    val gestureModifier: Modifier = Modifier.pointerInput(animationScope) {
        inspectDragGestures(
            onDragStart = { down ->
                pointerPosition = down.position
                animationScope.launch {
                    pressProgressAnimation.animateTo(1f, pressProgressAnimationSpec)
                }
            },
            onDragEnd = { release() },
            onDragCancel = { release() },
        ) { change, _ ->
            pointerPosition = change.position
        }
    }

    private fun release() {
        animationScope.launch {
            pressProgressAnimation.animateTo(0f, pressProgressAnimationSpec)
        }
    }
}
