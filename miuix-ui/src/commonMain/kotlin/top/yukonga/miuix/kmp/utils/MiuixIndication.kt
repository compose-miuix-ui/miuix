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
import androidx.compose.ui.util.fastCoerceIn
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.folmeSpring
import top.yukonga.miuix.kmp.interfaces.HoldDownInteraction

private const val HOVER_ALPHA_DELTA = 0.06f
private const val FOCUS_ALPHA_DELTA = 0.08f
private const val PRESS_ALPHA_DELTA = 20f / 255f
private const val HOLD_DOWN_ALPHA_DELTA = 20f / 255f
private const val PRESS_RELEASE_HOLD_MS = 200L

private val PressEnterSpring: SpringSpec<Float> = folmeSpring(damping = 0.99f, response = 0.15f)
private val PressExitSpring: SpringSpec<Float> = folmeSpring(damping = 0.99f, response = 0.30f)
private val HoverEnterSpring: SpringSpec<Float> = folmeSpring(damping = 1.0f, response = 0.6f)
private val HoverExitSpring: SpringSpec<Float> = folmeSpring(damping = 0.96f, response = 0.2f)

/**
 * Miuix default [Indication] that draws a rectangular overlay when pressed.
 */
@Immutable
class MiuixIndication(
    private val color: Color = Color.Black,
    private val pressAlpha: Float = PRESS_ALPHA_DELTA,
    private val hoverAlpha: Float = HOVER_ALPHA_DELTA,
    private val focusAlpha: Float = FOCUS_ALPHA_DELTA,
) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = MiuixIndicationInstance(interactionSource, color, pressAlpha, hoverAlpha, focusAlpha)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MiuixIndication) return false
        return color == other.color &&
            pressAlpha == other.pressAlpha &&
            hoverAlpha == other.hoverAlpha &&
            focusAlpha == other.focusAlpha
    }

    override fun hashCode(): Int {
        var result = color.hashCode()
        result = 31 * result + pressAlpha.hashCode()
        result = 31 * result + hoverAlpha.hashCode()
        result = 31 * result + focusAlpha.hashCode()
        return result
    }

    private class MiuixIndicationInstance(
        private val interactionSource: InteractionSource,
        private val color: Color,
        private val pressAlpha: Float,
        private val hoverAlpha: Float,
        private val focusAlpha: Float,
    ) : Modifier.Node(),
        DrawModifierNode {
        private var isPressed = false
        private var isHovered = false
        private var isFocused = false
        private var isHoldDown = false
        private var animationJob: Job? = null
        private var releaseHoldJob: Job? = null
        private var pressToken: Long = 0L
        private val animatedAlpha = Animatable(0f)
        private fun targetAlpha(): Float {
            var targetAlpha = 0.0f
            if (isHovered) targetAlpha += hoverAlpha
            if (isFocused) targetAlpha += focusAlpha
            if (isPressed && !isHoldDown) targetAlpha += pressAlpha
            if (isHoldDown) targetAlpha += HOLD_DOWN_ALPHA_DELTA
            return targetAlpha.fastCoerceIn(0f, 1f)
        }

        private fun animateOverlay(spring: SpringSpec<Float>) {
            val target = targetAlpha()
            animationJob?.cancel()
            animationJob =
                coroutineScope.launch {
                    animatedAlpha.animateTo(targetValue = target, animationSpec = spring)
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
                        is PressInteraction.Press -> {
                            pressToken++
                            releaseHoldJob?.cancel()
                            isPressed = true
                        }

                        is PressInteraction.Release -> {
                            if (isPressed) {
                                val token = pressToken
                                releaseHoldJob?.cancel()
                                releaseHoldJob =
                                    coroutineScope.launch {
                                        delay(PRESS_RELEASE_HOLD_MS)
                                        if (pressToken == token && isPressed) {
                                            isPressed = false
                                            animateOverlay(PressExitSpring)
                                        }
                                    }
                            }
                            return@collect
                        }

                        is PressInteraction.Cancel -> {
                            pressToken++
                            releaseHoldJob?.cancel()
                            isPressed = false
                        }

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
            val alpha = animatedAlpha.value
            if (alpha > 0f) {
                drawRect(color = color, alpha = alpha, size = size)
            }
            drawContent()
        }
    }
}
