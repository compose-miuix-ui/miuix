// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.folmeSpring
import top.yukonga.miuix.kmp.interfaces.HoldDownInteraction

private const val HOVER_ALPHA_DELTA = 0.06f
private const val FOCUS_ALPHA_DELTA = 0.08f
private const val PRESS_ALPHA_DELTA = 0.10f
private const val HOLD_DOWN_ALPHA_DELTA = 0.10f

private val PressEnterSpring: SpringSpec<Float> = folmeSpring(damping = 1.0f, response = 0.2f)
private val PressExitSpring: SpringSpec<Float> = folmeSpring(damping = 0.95f, response = 0.35f)
private val HoverEnterSpring: SpringSpec<Float> = folmeSpring(damping = 1.0f, response = 0.6f)
private val HoverExitSpring: SpringSpec<Float> = folmeSpring(damping = 0.96f, response = 0.2f)

/**
 * Miuix default [Indication] that draws a rectangular overlay when pressed.
 */
@Immutable
class MiuixIndication(
    private val color: Color = Color.Black,
) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = MiuixIndicationInstance(interactionSource, color)

    override fun hashCode(): Int = color.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MiuixIndication) return false
        if (color != other.color) return false
        return true
    }

    private class MiuixIndicationInstance(
        private val interactionSource: InteractionSource,
        private val color: Color,
    ) : Modifier.Node(),
        DrawModifierNode {
        private var isPressed = false
        private var isHovered = false
        private var isFocused = false
        private var isHoldDown = false
        private val animatedAlpha = Animatable(0f)
        private var pressedAnimation: Job? = null
        private var restingAnimation: Job? = null

        private fun targetAlpha(): Float {
            var targetAlpha = 0.0f
            if (isHovered) targetAlpha += HOVER_ALPHA_DELTA
            if (isFocused) targetAlpha += FOCUS_ALPHA_DELTA
            if (isPressed && !isHoldDown) targetAlpha += PRESS_ALPHA_DELTA
            if (isHoldDown) targetAlpha += HOLD_DOWN_ALPHA_DELTA
            return targetAlpha
        }

        private fun animateOverlay(spring: SpringSpec<Float>) {
            val target = targetAlpha()
            if (target == 0f) {
                restingAnimation?.cancel()
                restingAnimation =
                    coroutineScope.launch {
                        pressedAnimation?.join()
                        animatedAlpha.animateTo(targetValue = target, animationSpec = spring)
                    }
            } else {
                pressedAnimation?.cancel()
                restingAnimation?.cancel()
                pressedAnimation =
                    coroutineScope.launch {
                        animatedAlpha.animateTo(targetValue = target, animationSpec = spring)
                    }
            }
        }

        override fun onAttach() {
            coroutineScope.launch {
                interactionSource.interactions.collect { interaction ->
                    val previousPressed = isPressed
                    val previousHovered = isHovered
                    val previousFocused = isFocused
                    val previousHoldDown = isHoldDown

                    when (interaction) {
                        is PressInteraction.Press -> isPressed = true
                        is PressInteraction.Release, is PressInteraction.Cancel -> isPressed = false
                        is HoverInteraction.Enter -> isHovered = true
                        is HoverInteraction.Exit -> isHovered = false
                        is FocusInteraction.Focus -> isFocused = true
                        is FocusInteraction.Unfocus -> isFocused = false
                        is HoldDownInteraction.HoldDown -> isHoldDown = true
                        is HoldDownInteraction.Release -> isHoldDown = false
                        else -> return@collect
                    }

                    val spring =
                        when {
                            previousPressed != isPressed -> if (isPressed) PressEnterSpring else PressExitSpring
                            previousHoldDown != isHoldDown -> if (isHoldDown) PressEnterSpring else PressExitSpring
                            previousHovered != isHovered -> if (isHovered) HoverEnterSpring else HoverExitSpring
                            previousFocused != isFocused -> if (isFocused) HoverEnterSpring else HoverExitSpring
                            else -> return@collect
                        }
                    animateOverlay(spring)
                }
            }
        }

        override fun ContentDrawScope.draw() {
            drawContent()
            val alpha = animatedAlpha.value
            if (alpha > 0f) {
                drawRect(color = color, alpha = alpha, size = size)
            }
        }
    }
}
