// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.requireDensity
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min
/**
 * Returns the scale applied to a pressed surface.
 *
 * The inset is a fixed pixel amount rather than a proportion, so smaller surfaces scale more,
 * subject to a minimum scale.
 *
 * @param shorterSidePx Shorter surface side, in pixels.
 * @param density Pixels per dp.
 */
@Stable
fun glassPressScale(shorterSidePx: Float, density: Float): Float = if (shorterSidePx <= 0f) {
    1f
} else {
    max((shorterSidePx - GlassMotion.PRESS_INSET_DP * density) / shorterSidePx, GlassMotion.PRESS_SCALE_MIN)
}

/**
 * Applies press feedback to a tappable surface.
 *
 * @param interactionSource Interaction source that provides the press state.
 * @param enabled Whether press feedback is enabled. When false, this modifier is unchanged.
 */
@Composable
fun Modifier.glassPress(
    interactionSource: InteractionSource,
    enabled: Boolean = true,
): Modifier = if (!enabled) {
    this
} else {
    this then GlassPressElement(interactionSource = interactionSource, enabled = true)
}

private class GlassPressElement(
    val interactionSource: InteractionSource,
    val enabled: Boolean,
) : ModifierNodeElement<GlassPressNode>() {
    override fun create(): GlassPressNode = GlassPressNode(interactionSource, enabled)

    override fun update(node: GlassPressNode) {
        node.update(interactionSource, enabled)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "glassPress"
        properties["interactionSource"] = interactionSource
        properties["enabled"] = enabled
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GlassPressElement) return false
        return interactionSource == other.interactionSource && enabled == other.enabled
    }

    override fun hashCode(): Int = 31 * interactionSource.hashCode() + enabled.hashCode()
}

private class GlassPressNode(
    private var interactionSource: InteractionSource,
    private var enabled: Boolean,
) : Modifier.Node(),
    LayoutModifierNode {

    private val scaleAnim = Animatable(1f)
    private var shorterSidePx: Float = 0f
    private var isPressed: Boolean = false
    private var collectJob: Job? = null
    private var animJob: Job? = null

    override fun onAttach() {
        if (enabled) startCollecting()
    }

    override fun onDetach() {
        collectJob?.cancel()
        collectJob = null
        animJob?.cancel()
        animJob = null
        isPressed = false
    }

    fun update(newInteractionSource: InteractionSource, newEnabled: Boolean) {
        val sourceChanged = interactionSource !== newInteractionSource
        val enabledChanged = enabled != newEnabled
        interactionSource = newInteractionSource
        enabled = newEnabled
        if (!newEnabled) {
            collectJob?.cancel()
            collectJob = null
            if (isPressed) {
                isPressed = false
                animatePress(false)
            }
        } else if (isAttached && (sourceChanged || enabledChanged)) {
            if (isPressed) {
                isPressed = false
                animatePress(false)
            }
            startCollecting()
        }
    }

    private fun startCollecting() {
        collectJob?.cancel()
        collectJob = coroutineScope.launch {
            val pressInteractions = mutableListOf<PressInteraction.Press>()
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> pressInteractions.add(interaction)
                    is PressInteraction.Release -> pressInteractions.remove(interaction.press)
                    is PressInteraction.Cancel -> pressInteractions.remove(interaction.press)
                }
                val pressed = pressInteractions.isNotEmpty()
                if (pressed != isPressed) {
                    isPressed = pressed
                    animatePress(pressed)
                }
            }
        }
    }

    private fun animatePress(pressed: Boolean) {
        animJob?.cancel()
        animJob = coroutineScope.launch {
            val target = if (pressed) glassPressScale(shorterSidePx, requireDensity().density) else 1f
            val spec = if (pressed) GlassMotion.pressDown<Float>() else GlassMotion.pressUp<Float>()
            scaleAnim.animateTo(target, spec)
        }
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        val newShorterSide = min(placeable.width, placeable.height).toFloat()
        val sizeChanged = newShorterSide != shorterSidePx
        shorterSidePx = newShorterSide
        if (isAttached && isPressed && sizeChanged) {
            animatePress(true)
        }
        return layout(placeable.width, placeable.height) {
            if (enabled) {
                placeable.placeWithLayer(IntOffset.Zero) {
                    val s = scaleAnim.value
                    scaleX = s
                    scaleY = s
                }
            } else {
                placeable.place(IntOffset.Zero)
            }
        }
    }
}
