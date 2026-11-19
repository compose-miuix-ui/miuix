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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
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
 * @param progress A lambda returning the current progress value between 0.0f and 1.0f.
 * @param modifier The modifier to be applied to the indicator.
 * @param colors The colors used for the indicator.
 * @param height The height of the indicator.
 */
@Composable
fun LinearProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    colors: ProgressIndicatorColors = ProgressIndicatorDefaults.progressIndicatorColors(),
    height: Dp = ProgressIndicatorDefaults.DefaultLinearProgressIndicatorHeight,
) {
    val currentBackgroundColor = colors.backgroundColor()
    val currentForegroundColor = colors.foregroundColor(true)

    Spacer(
        modifier = modifier
            .semantics {
                progressBarRangeInfo = ProgressBarRangeInfo(progress().coerceIn(0f, 1f), 0f..1f)
            }
            .fillMaxWidth()
            .height(height)
            .drawWithCache {
                val cornerRadius = CornerRadius(this.size.height / 2f)
                val minWidth = cornerRadius.x * 2f
                onDrawBehind {
                    drawRoundRect(
                        color = currentBackgroundColor,
                        size = this.size,
                        cornerRadius = cornerRadius,
                    )
                    val progressValue = progress().coerceIn(0f, 1f)
                    val progressWidth = minWidth + (this.size.width - minWidth) * progressValue
                    drawRoundRect(
                        color = currentForegroundColor,
                        size = Size(progressWidth, this.size.height),
                        cornerRadius = cornerRadius,
                    )
                }
            },
    )
}

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
    if (progress == null) {
        val currentBackgroundColor = colors.backgroundColor()
        val currentForegroundColor = colors.foregroundColor(true)
        val transition = rememberInfiniteTransition()
        val animatedValue = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1250, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
        )

        Spacer(
            modifier = modifier
                .semantics { progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate }
                .fillMaxWidth()
                .height(height)
                .drawWithCache {
                    val cornerRadius = CornerRadius(this.size.height / 2f)
                    onDrawBehind {
                        drawRoundRect(
                            color = currentBackgroundColor,
                            size = this.size,
                            cornerRadius = cornerRadius,
                        )
                        val value = animatedValue.value
                        for (i in 0 until 3) {
                            drawIndeterminateSegment(value, i, currentForegroundColor, cornerRadius)
                        }
                    }
                },
        )
    } else {
        val currentProgress = rememberUpdatedState(progress)
        val progressProvider = remember<() -> Float>(currentProgress) { { currentProgress.value } }
        LinearProgressIndicator(
            progress = progressProvider,
            modifier = modifier,
            colors = colors,
            height = height,
        )
    }
}

/**
 * A [CircularProgressIndicator] with Miuix style.
 *
 * @param progress A lambda returning the current progress value between 0.0f and 1.0f.
 * @param modifier The modifier to be applied to the indicator.
 * @param colors The colors used for the indicator.
 * @param strokeWidth The width of the circular stroke.
 * @param size The size (diameter) of the circular indicator.
 */
@Composable
fun CircularProgressIndicator(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    colors: ProgressIndicatorColors = ProgressIndicatorDefaults.progressIndicatorColors(),
    strokeWidth: Dp = ProgressIndicatorDefaults.DefaultCircularProgressIndicatorStrokeWidth,
    size: Dp = ProgressIndicatorDefaults.DefaultCircularProgressIndicatorSize,
) {
    val currentBackgroundColor = colors.backgroundColor()
    val currentForegroundColor = colors.foregroundColor(true)

    Spacer(
        modifier = modifier
            .semantics {
                progressBarRangeInfo = ProgressBarRangeInfo(progress().coerceIn(0f, 1f), 0f..1f)
            }
            .size(size)
            .drawWithCache {
                val strokeWidthPx = strokeWidth.toPx()
                val bgStroke = Stroke(width = strokeWidthPx)
                val fgStroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                val radius = (this.size.minDimension - strokeWidthPx) / 2f
                val center = Offset(this.size.width / 2f, this.size.height / 2f)
                val arcTopLeft = Offset(center.x - radius, center.y - radius)
                val arcSize = Size(2f * radius, 2f * radius)
                onDrawBehind {
                    drawCircle(
                        color = currentBackgroundColor,
                        radius = radius,
                        center = center,
                        style = bgStroke,
                    )
                    val progressValue = progress().coerceIn(0f, 1f)
                    val minSweepAngle = 0.1f
                    val sweepAngle = minSweepAngle + (360f - minSweepAngle) * progressValue
                    drawArc(
                        color = currentForegroundColor,
                        startAngle = -90f,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = arcTopLeft,
                        size = arcSize,
                        style = fgStroke,
                    )
                }
            },
    )
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
    if (progress == null) {
        val currentBackgroundColor = colors.backgroundColor()
        val currentForegroundColor = colors.foregroundColor(true)
        val transition = rememberInfiniteTransition()

        val rotationAnim = transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
        )
        val sweepAnim = transition.animateFloat(
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

        Spacer(
            modifier = modifier
                .semantics { progressBarRangeInfo = ProgressBarRangeInfo.Indeterminate }
                .size(size)
                .drawWithCache {
                    val strokeWidthPx = strokeWidth.toPx()
                    val bgStroke = Stroke(width = strokeWidthPx)
                    val fgStroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
                    val radius = (this.size.minDimension - strokeWidthPx) / 2f
                    val center = Offset(this.size.width / 2f, this.size.height / 2f)
                    val arcTopLeft = Offset(center.x - radius, center.y - radius)
                    val arcSize = Size(2f * radius, 2f * radius)
                    onDrawBehind {
                        drawCircle(
                            color = currentBackgroundColor,
                            radius = radius,
                            center = center,
                            style = bgStroke,
                        )
                        drawArc(
                            color = currentForegroundColor,
                            startAngle = rotationAnim.value,
                            sweepAngle = sweepAnim.value,
                            useCenter = false,
                            topLeft = arcTopLeft,
                            size = arcSize,
                            style = fgStroke,
                        )
                    }
                },
        )
    } else {
        val currentProgress = rememberUpdatedState(progress)
        val progressProvider = remember<() -> Float>(currentProgress) { { currentProgress.value } }
        CircularProgressIndicator(
            progress = progressProvider,
            modifier = modifier,
            colors = colors,
            strokeWidth = strokeWidth,
            size = size,
        )
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
    val rotation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    Spacer(
        modifier = modifier
            .size(size)
            .drawWithCache {
                val strokeWidthPx = strokeWidth.toPx()
                val orbitingDotSizePx = orbitingDotSize.toPx()
                val bgStroke = Stroke(strokeWidthPx, cap = StrokeCap.Round)
                val center = Offset(this.size.width / 2f, this.size.height / 2f)
                val radius = (this.size.minDimension - strokeWidthPx) / 2f
                val orbitRadius = radius - 2f * orbitingDotSizePx
                onDrawBehind {
                    drawCircle(
                        color = color,
                        radius = radius,
                        center = center,
                        style = bgStroke,
                    )
                    val angle = rotation.value * (PI / 180.0).toFloat()
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
            },
    )
}

/**
 * Draws a single animated segment for the indeterminate linear progress indicator.
 */
private fun DrawScope.drawIndeterminateSegment(
    animatedValue: Float,
    segmentIndex: Int,
    color: Color,
    cornerRadius: CornerRadius,
) {
    val position = animatedValue - segmentIndex * (0.45f + 0.55f)
    val adjustedPos = (position % 1f + 1f) % 1f

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
