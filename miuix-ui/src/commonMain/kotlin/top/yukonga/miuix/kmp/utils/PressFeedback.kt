// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.PointerInputModifierNode
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.interfaces.HoldDownInteraction

private val PressFeedbackReferenceSide = 160.dp

private fun pressFeedbackSizeRatio(width: Int, height: Int, density: Density): Float = with(density) {
    maxOf(1f, maxOf(width, height) / PressFeedbackReferenceSide.toPx())
}

private fun pressFeedbackStrength(sizeRatio: Float): Float = 2f / (1f + sizeRatio)

/**
 * Scales the surface uniformly when pressed. [sinkAmount] is the target scale for compact
 * surfaces; larger surfaces scale less to limit how far their edges move.
 */
@Stable
data class SinkFeedback(
    val sinkAmount: Float = 0.94f,
    val animationSpec: AnimationSpec<Float> = spring(0.8f, 600f),
) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = SinkFeedbackNode(interactionSource, sinkAmount, animationSpec)

    private class SinkFeedbackNode(
        private val interactionSource: InteractionSource,
        private val sinkAmount: Float,
        private val animationSpec: AnimationSpec<Float>,
    ) : Modifier.Node(),
        LayoutModifierNode {

        private val animatedScale = Animatable(1f)
        private var isPressed = false
        private var isHoldDown = false

        private fun updateState() {
            val target = if (isPressed || isHoldDown) sinkAmount else 1f
            coroutineScope.launch { animatedScale.animateTo(target, animationSpec) }
        }

        override fun onAttach() {
            coroutineScope.launch {
                interactionSource.interactions.collect { interaction: Interaction ->
                    when (interaction) {
                        is PressInteraction.Press -> isPressed = true
                        is PressInteraction.Release, is PressInteraction.Cancel -> isPressed = false
                        is HoldDownInteraction.HoldDown -> isHoldDown = true
                        is HoldDownInteraction.Release -> isHoldDown = false
                        else -> return@collect
                    }
                    updateState()
                }
            }
        }

        override fun MeasureScope.measure(
            measurable: Measurable,
            constraints: Constraints,
        ): MeasureResult {
            val placeable = measurable.measure(constraints)
            val scaleStrength = pressFeedbackStrength(pressFeedbackSizeRatio(placeable.width, placeable.height, this))
            val layerBlock: GraphicsLayerScope.() -> Unit = {
                val scale = 1f + (animatedScale.value - 1f) * scaleStrength
                scaleX = scale
                scaleY = scale
            }
            return layout(placeable.width, placeable.height) {
                placeable.placeWithLayer(0, 0, layerBlock = layerBlock)
            }
        }
    }
}

/**
 * Tilts the surface toward the press. [tiltAmount] sets the angle in degrees for compact
 * surfaces; larger surfaces use less rotation and a longer camera distance.
 */
@Stable
data class TiltFeedback(
    val tiltAmount: Float = 8f,
    val animationSpec: AnimationSpec<Float> = spring(0.6f, 400f),
) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = TiltFeedbackNode(interactionSource, tiltAmount, animationSpec)

    private class TiltFeedbackNode(
        private val interactionSource: InteractionSource,
        private val tiltAmount: Float,
        private val animationSpec: AnimationSpec<Float>,
    ) : Modifier.Node(),
        LayoutModifierNode,
        PointerInputModifierNode {

        private var transformOrigin: TransformOrigin = TransformOrigin.Center
        private var targetX = 0f
        private var targetY = 0f
        private val animatedTiltX = Animatable(0f)
        private val animatedTiltY = Animatable(0f)
        private var isPressed = false
        private var isHoldDown = false

        private fun updateState() {
            if (isPressed || isHoldDown) {
                coroutineScope.launch { animatedTiltX.animateTo(targetX, animationSpec) }
                coroutineScope.launch { animatedTiltY.animateTo(targetY, animationSpec) }
            } else {
                coroutineScope.launch { animatedTiltX.animateTo(0f, animationSpec) }
                coroutineScope.launch { animatedTiltY.animateTo(0f, animationSpec) }
            }
        }

        override fun onAttach() {
            coroutineScope.launch {
                interactionSource.interactions.collect { interaction: Interaction ->
                    when (interaction) {
                        is PressInteraction.Press -> isPressed = true
                        is PressInteraction.Release, is PressInteraction.Cancel -> isPressed = false
                        is HoldDownInteraction.HoldDown -> isHoldDown = true
                        is HoldDownInteraction.Release -> isHoldDown = false
                        else -> return@collect
                    }
                    updateState()
                }
            }
        }

        override fun onPointerEvent(
            pointerEvent: PointerEvent,
            pass: PointerEventPass,
            bounds: IntSize,
        ) {
            if (pass != PointerEventPass.Main) return
            if (pointerEvent.type == PointerEventType.Press) {
                val offset = pointerEvent.changes.first().position

                transformOrigin = TransformOrigin(
                    pivotFractionX = if (offset.x < bounds.width / 2f) 1f else 0f,
                    pivotFractionY = if (offset.y < bounds.height / 2f) 1f else 0f,
                )

                targetX = if (offset.y < bounds.height / 2f) tiltAmount else -tiltAmount
                targetY = if (offset.x < bounds.width / 2f) -tiltAmount else tiltAmount
            }
        }

        override fun onCancelPointerInput() {
            transformOrigin = TransformOrigin.Center
            targetX = 0f
            targetY = 0f
        }

        override fun MeasureScope.measure(
            measurable: Measurable,
            constraints: Constraints,
        ): MeasureResult {
            val placeable = measurable.measure(constraints)
            val sizeRatio = pressFeedbackSizeRatio(placeable.width, placeable.height, this)
            val angleScale = pressFeedbackStrength(sizeRatio)
            val perspectiveDistance = 12f * density * sizeRatio
            val layerBlock: GraphicsLayerScope.() -> Unit = {
                rotationX = animatedTiltX.value * angleScale
                rotationY = animatedTiltY.value * angleScale
                cameraDistance = perspectiveDistance
                transformOrigin = this@TiltFeedbackNode.transformOrigin
            }
            return layout(placeable.width, placeable.height) {
                placeable.placeWithLayer(0, 0, layerBlock = layerBlock)
            }
        }
    }
}

/**
 * The type of visual feedback to apply when the component is pressed.
 */
enum class PressFeedbackType {
    /** No feedback effect. */
    None,

    /** Sinks slightly when pressed. */
    Sink,

    /** Tilts based on touch position when pressed. */
    Tilt,
}
