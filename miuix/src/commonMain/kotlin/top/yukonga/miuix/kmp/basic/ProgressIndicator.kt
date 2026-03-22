// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * A [LinearProgressIndicator] with Miuix style.
 *
 * @param modifier The modifier to be applied to the indicator.
 * @param progress The current progress value between 0.0f and 1.0f, or null for indeterminate state.
 * @param colors The colors used for the indicator.
 * @param height The height of the indicator.
 */
@Composable
fun LinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    colors: ProgressIndicatorColors = ProgressIndicatorDefaults.progressIndicatorColors(),
    height: Dp = ProgressIndicatorDefaults.DefaultLinearProgressIndicatorHeight,
) {
    val currentBackgroundColor = colors.backgroundColor()
    val currentForegroundColor = colors.foregroundColor(true)

    if (progress == null) {
        val transition = rememberInfiniteTransition()
        val animatedValue by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1250, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
        )

        Canvas(
            modifier = modifier
                .semantics { progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate }
                .fillMaxWidth()
                .height(height),
        ) {
            drawRoundRect(
                color = currentBackgroundColor,
                size = size,
                cornerRadius = CornerRadius(size.height / 2),
            )

            for (i in 0 until 3) {
                drawIndeterminateSegment(animatedValue, i, currentForegroundColor)
            }
        }
    } else {
        val progressValue = progress.coerceIn(0f, 1f)

        Canvas(
            modifier = modifier
                .semantics { progressBarRangeInfo = ProgressBarRangeInfo(progressValue, 0f..1f) }
                .fillMaxWidth()
                .height(height),
        ) {
            val cornerRadius = size.height / 2

            drawRoundRect(
                color = currentBackgroundColor,
                size = size,
                cornerRadius = CornerRadius(cornerRadius),
            )

            val minWidth = cornerRadius * 2
            val progressWidth = minWidth + (size.width - minWidth) * progressValue

            drawRoundRect(
                color = currentForegroundColor,
                size = Size(progressWidth, size.height),
                cornerRadius = CornerRadius(cornerRadius),
            )
        }
    }
}

/**
 * A [CircularProgressIndicator] with Miuix style.
 *
 * @param modifier The modifier to be applied to the indicator.
 * @param progress The current progress value between 0.0f and 1.0f, or null for indeterminate state.
 * @param colors The colors used for the indicator.
 * @param strokeWidth The width of the circular stroke.
 * @param size The size (diameter) of the circular indicator.
 */
@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    colors: ProgressIndicatorColors = ProgressIndicatorDefaults.progressIndicatorColors(),
    strokeWidth: Dp = ProgressIndicatorDefaults.DefaultCircularProgressIndicatorStrokeWidth,
    size: Dp = ProgressIndicatorDefaults.DefaultCircularProgressIndicatorSize,
) {
    val currentBackgroundColor = colors.backgroundColor()
    val currentForegroundColor = colors.foregroundColor(true)

    if (progress == null) {
        val transition = rememberInfiniteTransition()

        val rotationAnim by transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
        )
        val sweepAnim by transition.animateFloat(
            initialValue = 30f,
            targetValue = 120f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 1600
                    120f at 800 using LinearEasing
                    30f at 1600 using LinearEasing
                },
                repeatMode = RepeatMode.Restart,
            ),
        )

        Canvas(
            modifier = modifier
                .semantics { progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate }
                .size(size),
        ) {
            val strokeWidthPx = strokeWidth.toPx()
            val radius = (size.toPx() - strokeWidthPx) / 2
            val center = Offset(size.toPx() / 2, size.toPx() / 2)

            drawCircularBackground(currentBackgroundColor, radius, center, strokeWidthPx)

            drawArc(
                color = currentForegroundColor,
                startAngle = rotationAnim,
                sweepAngle = sweepAnim,
                useCenter = false,
                topLeft = Offset(strokeWidthPx / 2, strokeWidthPx / 2),
                size = Size(2 * radius, 2 * radius),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
            )
        }
    } else {
        val progressValue = progress.coerceIn(0f, 1f)

        Canvas(
            modifier = modifier
                .semantics { progressBarRangeInfo = ProgressBarRangeInfo(progressValue, 0f..1f) }
                .size(size),
        ) {
            val strokeWidthPx = strokeWidth.toPx()
            val radius = (size.toPx() - strokeWidthPx) / 2
            val center = Offset(size.toPx() / 2, size.toPx() / 2)

            drawCircularBackground(currentBackgroundColor, radius, center, strokeWidthPx)

            val minSweepAngle = 0.1f
            val sweepAngle = minSweepAngle + (360f - minSweepAngle) * progressValue

            drawArc(
                color = currentForegroundColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(strokeWidthPx / 2, strokeWidthPx / 2),
                size = Size(2 * radius, 2 * radius),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
            )
        }
    }
}

/**
 * A [InfiniteProgressIndicator] with Miuix style.
 * The indicator is a circular indicator with an orbiting dot.
 *
 * @param modifier The modifier to be applied to the indicator.
 * @param color The color of the indicator.
 * @param size The size (diameter) of the circular indicator.
 * @param strokeWidth The width of the circular stroke.
 * @param orbitingDotSize The size of the orbiting dot.
 */
@Composable
fun InfiniteProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    size: Dp = ProgressIndicatorDefaults.DefaultInfiniteProgressIndicatorSize,
    strokeWidth: Dp = ProgressIndicatorDefaults.DefaultInfiniteProgressIndicatorStrokeWidth,
    orbitingDotSize: Dp = ProgressIndicatorDefaults.DefaultInfiniteProgressIndicatorOrbitingDotSize,
) {
    val transition = rememberInfiniteTransition()
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    Canvas(
        modifier = modifier.size(size),
    ) {
        val strokeWidthPx = strokeWidth.toPx()
        val orbitingDotSizePx = orbitingDotSize.toPx()
        val center = Offset(this.size.width / 2, this.size.height / 2)
        val radius = (size.toPx() - strokeWidthPx) / 2

        drawCircle(
            color = color,
            radius = radius,
            center = center,
            style = Stroke(strokeWidthPx, cap = StrokeCap.Round),
        )

        val orbitRadius = radius - 2 * orbitingDotSizePx
        val angle = rotation * (PI / 180.0).toFloat()
        val dotCenter = center + Offset(
            x = orbitRadius * cos(angle),
            y = orbitRadius * sin(angle),
        )

        drawCircle(
            color = color,
            radius = orbitingDotSizePx,
            center = dotCenter,
        )
    }
}

/**
 * Draws a single animated segment for the indeterminate linear progress indicator.
 */
private fun DrawScope.drawIndeterminateSegment(
    animatedValue: Float,
    segmentIndex: Int,
    color: Color,
) {
    val position = animatedValue - segmentIndex * (0.45f + 0.55f)
    val adjustedPos = (position % 1f + 1f) % 1f
    val cornerRadius = CornerRadius(size.height / 2)

    if (adjustedPos < 1f - 0.45f) {
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * adjustedPos, 0f),
            size = Size(size.width * 0.45f, size.height),
            cornerRadius = cornerRadius,
        )
    } else {
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * adjustedPos, 0f),
            size = Size(size.width * (1f - adjustedPos), size.height),
            cornerRadius = cornerRadius,
        )
        val remainingWidth = adjustedPos + 0.45f - 1f
        if (remainingWidth > 0) {
            drawRoundRect(
                color = color,
                size = Size(size.width * remainingWidth, size.height),
                cornerRadius = cornerRadius,
            )
        }
    }
}

/**
 * Draws a circular background ring used by [CircularProgressIndicator].
 */
private fun DrawScope.drawCircularBackground(
    color: Color,
    radius: Float,
    center: Offset,
    strokeWidthPx: Float,
) {
    drawCircle(
        color = color,
        radius = radius,
        center = center,
        style = Stroke(width = strokeWidthPx),
    )
}

object ProgressIndicatorDefaults {
    /** The default height of [LinearProgressIndicator]. */
    val DefaultLinearProgressIndicatorHeight = 6.dp

    /** The default stroke width of [CircularProgressIndicator]. */
    val DefaultCircularProgressIndicatorStrokeWidth = 4.dp

    /** The default size of [CircularProgressIndicator]. */
    val DefaultCircularProgressIndicatorSize = 30.dp

    /** The default stroke width of [InfiniteProgressIndicator]. */
    val DefaultInfiniteProgressIndicatorStrokeWidth = 2.dp

    /** The default radius width of the orbiting dot in [InfiniteProgressIndicator]. */
    val DefaultInfiniteProgressIndicatorOrbitingDotSize = 2.dp

    /** The default size of [InfiniteProgressIndicator]. */
    val DefaultInfiniteProgressIndicatorSize = 20.dp

    /**
     * The default [ProgressIndicatorColors] used by [LinearProgressIndicator] and [CircularProgressIndicator].
     */
    @Composable
    fun progressIndicatorColors(
        foregroundColor: Color = MiuixTheme.colorScheme.primary,
        disabledForegroundColor: Color = MiuixTheme.colorScheme.disabledPrimarySlider,
        backgroundColor: Color = MiuixTheme.colorScheme.secondaryContainer,
    ): ProgressIndicatorColors = remember(foregroundColor, disabledForegroundColor, backgroundColor) {
        ProgressIndicatorColors(
            foregroundColor = foregroundColor,
            disabledForegroundColor = disabledForegroundColor,
            backgroundColor = backgroundColor,
        )
    }
}

@Immutable
data class ProgressIndicatorColors(
    private val foregroundColor: Color,
    private val disabledForegroundColor: Color,
    private val backgroundColor: Color,
) {
    @Stable
    internal fun foregroundColor(enabled: Boolean): Color = if (enabled) foregroundColor else disabledForegroundColor

    @Stable
    internal fun backgroundColor(): Color = backgroundColor
}
