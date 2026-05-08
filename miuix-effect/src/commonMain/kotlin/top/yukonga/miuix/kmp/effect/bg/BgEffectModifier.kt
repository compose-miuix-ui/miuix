// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.bg

import androidx.compose.animation.core.spring
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.effect.DeviceType
import top.yukonga.miuix.kmp.effect.bg.BgEffectNode.Companion.MIN_FRAME_DELTA_NANOS
import kotlin.math.floor

/**
 * Applies a GPU-accelerated animated background gradient effect to this [Modifier].
 *
 * This is the low-level modifier API for the background effect. Most callers should
 * use [top.yukonga.miuix.kmp.effect.BgEffectBackground] instead, which provides a
 * higher-level composable wrapper.
 *
 * The effect renders a multi-point gradient with Perlin noise perturbation over a
 * solid [surface] color. The gradient animates continuously when [playing] is `true`,
 * with control points drifting and colors cycling through three palettes.
 *
 * @param state The [BgEffectState] managing shader resources and color animation.
 * @param deviceType Device form factor affecting gradient preset selection.
 * @param isDarkTheme Whether dark theme is active, affecting color and brightness.
 * @param surface Base fill color drawn beneath the gradient.
 * @param effectBackground Whether to render the gradient overlay. When `false`, only
 *   the [surface] color is drawn. Useful for disabling the effect without removing the composable.
 * @param isFullSize `true` to use 80% of viewport height for gradient rendering;
 *   `false` to use 50%. Affects gradient density and visual prominence.
 * @param playing Whether the animation is actively running. When transitioning from
 *   `false` to `true`, animation resumes from the last paused time position.
 * @param alpha Lambda returning the current opacity of the gradient overlay.
 *   Called once per draw frame. Return `0f` to skip gradient rendering entirely.
 */
fun Modifier.bgEffect(
    state: BgEffectState,
    deviceType: DeviceType,
    isDarkTheme: Boolean,
    surface: Color,
    effectBackground: Boolean = true,
    isFullSize: Boolean = false,
    playing: Boolean = true,
    alpha: () -> Float = { 1f },
): Modifier = this then BgEffectElement(
    state = state,
    deviceType = deviceType,
    isDarkTheme = isDarkTheme,
    surface = surface,
    effectBackground = effectBackground,
    isFullSize = isFullSize,
    playing = playing,
    alpha = alpha,
)

/**
 * [ModifierNodeElement] that creates and updates [BgEffectNode] instances.
 *
 * Uses data-class equality to detect parameter changes and trigger node updates.
 * The [alpha] lambda is excluded from equality checks since it is a mutable callback.
 */
private data class BgEffectElement(
    val state: BgEffectState,
    val deviceType: DeviceType,
    val isDarkTheme: Boolean,
    val surface: Color,
    val effectBackground: Boolean,
    val isFullSize: Boolean,
    val playing: Boolean,
    val alpha: () -> Float,
) : ModifierNodeElement<BgEffectNode>() {

    override fun create(): BgEffectNode = BgEffectNode(
        state = state,
        deviceType = deviceType,
        isDarkTheme = isDarkTheme,
        surface = surface,
        effectBackground = effectBackground,
        isFullSize = isFullSize,
        playing = playing,
        alpha = alpha,
    )

    override fun update(node: BgEffectNode) {
        node.update(
            state = state,
            deviceType = deviceType,
            isDarkTheme = isDarkTheme,
            surface = surface,
            effectBackground = effectBackground,
            isFullSize = isFullSize,
            playing = playing,
            alpha = alpha,
        )
    }
}

/**
 * Compose [DrawModifierNode] that renders the background gradient effect.
 *
 * Manages two coroutine-based animation loops:
 * - **Frame animation** (`frameAnimJob`): Advances [animTime] each frame at 60 FPS,
 *   driving control-point oscillation and noise animation.
 * - **Color animation** (`colorAnimJob`): Progresses [BgEffectState.animatableColorStage]
 *   through the color palette cycle using spring physics.
 *
 * ### Lifecycle
 * - Animation starts on [onAttach] if [playing] is `true`.
 * - All jobs are cancelled on [onDetach] to prevent leaks.
 * - [update] handles toggling [playing] on/off, resuming from the last [animTime].
 *
 * ### Performance Notes
 * - The frame loop uses `withFrameNanos` and throttles to 60 FPS via [MIN_FRAME_DELTA_NANOS].
 * - `invalidateDraw()` is called each frame to trigger a redraw, but only when the
 *   animation is active.
 * - Color animation uses a spring spec with low stiffness (`35f`) for smooth transitions.
 */
private class BgEffectNode(
    private var state: BgEffectState,
    private var deviceType: DeviceType,
    private var isDarkTheme: Boolean,
    private var surface: Color,
    private var effectBackground: Boolean,
    private var isFullSize: Boolean,
    private var playing: Boolean,
    private var alpha: () -> Float,
) : Modifier.Node(),
    DrawModifierNode {

    private var frameAnimJob: Job? = null
    private var colorAnimJob: Job? = null
    private var animTime: Float = 0f
    private var startOffset: Float = 0f

    override fun onAttach() {
        if (playing) startAnimation()
    }

    override fun onDetach() {
        frameAnimJob?.cancel()
        frameAnimJob = null
        colorAnimJob?.cancel()
        colorAnimJob = null
    }

    /**
     * Updates all mutable parameters and manages animation lifecycle.
     *
     * When [playing] transitions:
     * - `false → true`: Starts both animation loops, resuming from [animTime].
     * - `true → false`: Cancels both animation loops. The last [animTime] is preserved.
     *
     * Always calls [invalidateDraw] to reflect parameter changes.
     */
    fun update(
        state: BgEffectState,
        deviceType: DeviceType,
        isDarkTheme: Boolean,
        surface: Color,
        effectBackground: Boolean,
        isFullSize: Boolean,
        playing: Boolean,
        alpha: () -> Float,
    ) {
        this.state = state
        this.deviceType = deviceType
        this.isDarkTheme = isDarkTheme
        this.surface = surface
        this.effectBackground = effectBackground
        this.isFullSize = isFullSize
        this.alpha = alpha

        if (this.playing != playing) {
            this.playing = playing
            if (playing) {
                startAnimation()
            } else {
                frameAnimJob?.cancel()
                frameAnimJob = null
                colorAnimJob?.cancel()
                colorAnimJob = null
            }
        }
        invalidateDraw()
    }

    /**
     * Starts the frame animation loop and, if applicable, the color animation loop.
     *
     * The frame loop captures the current frame timestamp as [origin] and advances
     * [animTime] from [startOffset], ensuring smooth resume after pause.
     */
    private fun startAnimation() {
        frameAnimJob?.cancel()
        startOffset = animTime
        frameAnimJob = coroutineScope.launch {
            val origin = withFrameNanos { it }
            var lastEmit = origin
            while (isActive) {
                val now = withFrameNanos { it }
                if (now - lastEmit < MIN_FRAME_DELTA_NANOS) continue
                lastEmit = now
                animTime = startOffset + (now - origin) / 1_000_000_000f
                invalidateDraw()
            }
        }

        startColorAnimation()
    }

    /**
     * Starts the color palette interpolation animation.
     *
     * Only runs if the current preset has distinct color palettes (i.e., at least one
     * of [colors1], [colors2], [colors3] differs). For presets with identical palettes
     * (common in OS2), this is a no-op — the static colors are used directly.
     *
     * Uses spring physics (`dampingRatio=0.9, stiffness=35`) for organic-feeling
     * color transitions that avoid mechanical linear interpolation.
     */
    private fun startColorAnimation() {
        val preset = BgEffectConfig.get(deviceType, isDarkTheme, state.isOs3Effect)
        val animatesColors = preset.colors1 !== preset.colors2 || preset.colors2 !== preset.colors3
        if (!animatesColors) return

        colorAnimJob?.cancel()
        colorAnimJob = coroutineScope.launch {
            val animatable = state.animatableColorStage
            var targetStage = floor(animatable.value) + 1f
            while (isActive) {
                delay((preset.colorInterpPeriod * HALF_CYCLE_MS).toLong())
                animatable.animateTo(
                    targetValue = targetStage,
                    animationSpec = COLOR_SPRING_SPEC,
                )
                targetStage += 1f
                invalidateDraw()
            }
        }
    }

    override fun ContentDrawScope.draw() {
        drawRect(surface)
        if (effectBackground) {
            val alphaValue = alpha()
            if (alphaValue > 0f) {
                val painter = state.painter
                val preset = BgEffectConfig.get(deviceType, isDarkTheme, state.isOs3Effect)
                val drawHeight = if (isFullSize) size.height * FULL_SIZE_RATIO else size.height * HALF_SIZE_RATIO

                painter.updateResolution(size.width, size.height)
                painter.updateBoundIfNeeded(drawHeight, size.height, size.width)
                painter.updatePresetIfNeeded(deviceType, isDarkTheme)
                painter.updateColors(preset, state.colorStage)
                painter.updateAnimTime(animTime)
                painter.updatePointsAnim(animTime, preset)

                drawRect(painter.brush, alpha = alphaValue)
            }
        }
        drawContent()
    }

    companion object {
        /** Minimum frame interval in nanoseconds (~16.67ms = 60 FPS). */
        private const val MIN_FRAME_DELTA_NANOS = 1_000_000_000L / 60L

        /** Multiplier converting colorInterpPeriod (seconds) to delay milliseconds. */
        private const val HALF_CYCLE_MS = 500f

        /** Fraction of viewport height used for gradient rendering in full-size mode. */
        private const val FULL_SIZE_RATIO = 0.8f

        /** Fraction of viewport height used for gradient rendering in half-size mode. */
        private const val HALF_SIZE_RATIO = 0.5f

        /** Spring animation spec for color palette transitions. */
        private val COLOR_SPRING_SPEC = spring<Float>(dampingRatio = 0.9f, stiffness = 35f)
    }
}
