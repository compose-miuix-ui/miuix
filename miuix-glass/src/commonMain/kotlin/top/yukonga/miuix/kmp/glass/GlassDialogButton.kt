// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.ButtonColors
import top.yukonga.miuix.kmp.theme.LocalContentColor
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Visual roles for dialog actions. */
enum class GlassDialogButtonRole {
    Normal,
    Primary,
    Destructive,
}

/**
 * A dialog action button for use over [glassDialogSurface].
 *
 * Normal and destructive actions use a translucent tint; primary actions use the theme's primary
 * fill. The button adds no backdrop blur. Press feedback overlays both the fill and content.
 *
 * @param onClick Called when the enabled action is clicked.
 * @param modifier Modifier applied to the action.
 * @param enabled Whether the action accepts input.
 * @param role The tonal, primary or destructive treatment.
 * @param cornerRadius Radius shared by the fill, content clipping and press feedback.
 * @param minWidth Minimum action width; the dialog's action row usually supplies its width.
 * @param minHeight Minimum action height.
 * @param colors Enabled and disabled fill/content colors.
 * @param pressedColor Foreground overlay while pressed.
 * @param insideMargin Padding around the action content.
 * @param interactionSource Optional source for observing or driving interactions.
 * @param content Centered action content. Use MiuixTheme.textStyles.button for text.
 */
@Composable
fun GlassDialogButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    role: GlassDialogButtonRole = GlassDialogButtonRole.Normal,
    cornerRadius: Dp = GlassDialogButtonDefaults.CornerRadius,
    minWidth: Dp = GlassDialogButtonDefaults.MinWidth,
    minHeight: Dp = GlassDialogButtonDefaults.MinHeight,
    colors: ButtonColors = GlassDialogButtonDefaults.buttonColors(role),
    pressedColor: Color = GlassDialogButtonDefaults.pressedColor(),
    insideMargin: PaddingValues = GlassDialogButtonDefaults.InsideMargin,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val shape = remember(cornerRadius) { RoundedCornerShape(cornerRadius) }
    val indication = remember(pressedColor) { DialogButtonIndication(pressedColor) }
    val containerColor = if (enabled) colors.color else colors.disabledColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Row(
            modifier = modifier
                .clip(shape)
                .background(containerColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = if (enabled) indication else null,
                    enabled = enabled,
                    role = Role.Button,
                    onClick = onClick,
                )
                .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
                .padding(insideMargin),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

object GlassDialogButtonDefaults {
    val CornerRadius = 16.dp
    val MinWidth = 0.dp
    val MinHeight = 50.dp
    val InsideMargin = PaddingValues(horizontal = 24.dp)

    /** Enabled and disabled action colors for the current theme. */
    @Composable
    fun buttonColors(role: GlassDialogButtonRole = GlassDialogButtonRole.Normal): ButtonColors {
        val scheme = MiuixTheme.colorScheme
        val isDark = scheme.background.luminance() < 0.5f
        val tonal = scheme.onBackground.copy(alpha = if (isDark) 36f / 255f else 15f / 255f)
        val color = if (role == GlassDialogButtonRole.Primary) scheme.primary else tonal
        val disabledColor = if (role == GlassDialogButtonRole.Primary) scheme.primary.copy(alpha = 0.3f) else tonal
        val contentColor = when (role) {
            GlassDialogButtonRole.Normal -> scheme.onBackground.copy(alpha = if (isDark) 0.8f else 0.9f)
            GlassDialogButtonRole.Primary -> scheme.onPrimary
            GlassDialogButtonRole.Destructive -> scheme.error
        }
        val disabledContentColor = when (role) {
            GlassDialogButtonRole.Normal -> scheme.onBackground.copy(alpha = 0.38f)
            GlassDialogButtonRole.Primary -> scheme.onPrimary.copy(alpha = if (isDark) 77f / 255f else 1f)
            GlassDialogButtonRole.Destructive -> scheme.error.copy(alpha = 0.3f)
        }
        return remember(color, disabledColor, contentColor, disabledContentColor) {
            ButtonColors(color, disabledColor, contentColor, disabledContentColor)
        }
    }

    /** Press overlay color for the current theme. */
    @Composable
    fun pressedColor(): Color {
        val scheme = MiuixTheme.colorScheme
        return scheme.onBackground.copy(alpha = if (scheme.background.luminance() < 0.5f) 36f / 255f else 31f / 255f)
    }
}

@Immutable
private data class DialogButtonIndication(val color: Color) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = DialogButtonIndicationNode(interactionSource, color)
}

private class DialogButtonIndicationNode(
    private val interactionSource: InteractionSource,
    private val color: Color,
) : Modifier.Node(),
    DrawModifierNode {
    private val presses = mutableSetOf<PressInteraction.Press>()

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> presses.add(interaction)
                    is PressInteraction.Release -> presses.remove(interaction.press)
                    is PressInteraction.Cancel -> presses.remove(interaction.press)
                    else -> return@collect
                }
                invalidateDraw()
            }
        }
    }

    override fun onDetach() {
        presses.clear()
    }

    override fun ContentDrawScope.draw() {
        drawContent()
        if (presses.isNotEmpty()) drawRect(color)
    }
}
