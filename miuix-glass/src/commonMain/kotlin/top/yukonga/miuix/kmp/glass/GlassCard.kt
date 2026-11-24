// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardColors
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

/**
 * A [Card] for content placed on a glass surface, such as a [GlassBottomSheet] or [GlassDialog].
 *
 * The card does not sample or blur the backdrop itself. It draws a translucent light fill so the
 * glass material underneath stays visible while the card reads as a raised group.
 *
 * This [GlassCard] does not handle input events.
 *
 * @param modifier The modifier to be applied to the [GlassCard].
 * @param cornerRadius The corner radius of the [GlassCard].
 * @param insideMargin The margin inside the [GlassCard].
 * @param colors [CardColors] that will be used to resolve the color(s) used for the [GlassCard].
 * @param content The [Composable] content of the [GlassCard].
 */
@Composable
@NonRestartableComposable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = GlassCardDefaults.CornerRadius,
    insideMargin: PaddingValues = GlassCardDefaults.InsideMargin,
    colors: CardColors = GlassCardDefaults.colors(),
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        cornerRadius = cornerRadius,
        insideMargin = insideMargin,
        colors = colors,
        content = content,
    )
}

/**
 * A [Card] for content placed on a glass surface, such as a [GlassBottomSheet] or [GlassDialog].
 *
 * The card does not sample or blur the backdrop itself. It draws a translucent light fill so the
 * glass material underneath stays visible while the card reads as a raised group.
 *
 * This [GlassCard] handles input events.
 *
 * @param modifier The modifier to be applied to the [GlassCard].
 * @param cornerRadius The corner radius of the [GlassCard].
 * @param insideMargin The margin inside the [GlassCard].
 * @param colors [CardColors] that will be used to resolve the color(s) used for the [GlassCard].
 * @param pressFeedbackType The press feedback type of the [GlassCard].
 * @param showIndication Whether to show indication of the [GlassCard].
 * @param holdDownState Whether the [GlassCard] is in a hold-down state.
 * @param onClick The callback to be invoked when the [GlassCard] is clicked.
 * @param onLongPress The callback to be invoked when the [GlassCard] is long pressed.
 * @param content The [Composable] content of the [GlassCard].
 */
@Composable
@NonRestartableComposable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = GlassCardDefaults.CornerRadius,
    insideMargin: PaddingValues = GlassCardDefaults.InsideMargin,
    colors: CardColors = GlassCardDefaults.colors(),
    pressFeedbackType: PressFeedbackType = PressFeedbackType.None,
    showIndication: Boolean = false,
    holdDownState: Boolean = false,
    onClick: (() -> Unit)? = null,
    onLongPress: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        cornerRadius = cornerRadius,
        insideMargin = insideMargin,
        colors = colors,
        pressFeedbackType = pressFeedbackType,
        showIndication = showIndication,
        holdDownState = holdDownState,
        onClick = onClick,
        onLongPress = onLongPress,
        content = content,
    )
}

/** Default values for [GlassCard]. */
object GlassCardDefaults {

    /** The default corner radius of the [GlassCard]. */
    val CornerRadius: Dp = 20.dp

    /** The default margin inside the [GlassCard]. */
    val InsideMargin: PaddingValues = CardDefaults.InsideMargin

    private const val LIGHT_ALPHA: Float = 0x8C / 255f
    private const val DARK_ALPHA: Float = 0x14 / 255f
    private const val DYNAMIC_LIGHT_ALPHA: Float = 0.62f
    private const val DYNAMIC_DARK_ALPHA: Float = 0.12f

    /** Translucent fill for the current theme. */
    @Composable
    @ReadOnlyComposable
    fun color(): Color {
        val scheme = MiuixTheme.colorScheme
        val isDark = scheme.background.luminance() < 0.5f
        return when {
            MiuixTheme.isDynamicColor -> if (isDark) {
                lerp(scheme.onSurfaceContainer, scheme.secondaryContainer, 0.30f).copy(alpha = DYNAMIC_DARK_ALPHA)
            } else {
                lerp(scheme.background, scheme.secondaryContainer, 0.32f).copy(alpha = DYNAMIC_LIGHT_ALPHA)
            }

            isDark -> scheme.onSurfaceContainer.copy(alpha = DARK_ALPHA)

            else -> scheme.surfaceContainer.copy(alpha = LIGHT_ALPHA)
        }
    }

    /** The default colors of the [GlassCard]. */
    @Composable
    fun colors(
        color: Color = color(),
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
    ): CardColors = CardDefaults.defaultColors(
        color = color,
        contentColor = contentColor,
    )
}
