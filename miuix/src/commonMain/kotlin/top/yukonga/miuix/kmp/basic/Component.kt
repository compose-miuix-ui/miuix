// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.interfaces.HoldDownInteraction
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A basic component with Miuix style. Widely used in other extension components.
 *
 * @param title The title of the [BasicComponent].
 * @param titleColor The color of the title.
 * @param summary The summary of the [BasicComponent].
 * @param summaryColor The color of the summary.
 * @param leftAction The [Composable] content that on the left side of the [BasicComponent].
 * @param rightActions The [Composable] content on the right side of the [BasicComponent].
 * @param modifier The modifier to be applied to the [BasicComponent].
 * @param insideMargin The margin inside the [BasicComponent].
 * @param onClick The callback when the [BasicComponent] is clicked.
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [BasicComponent] is enabled.
 * @param interactionSource The [MutableInteractionSource] for the [BasicComponent].
 */
@Composable
fun BasicComponent(
    title: String? = null,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    leftAction: @Composable (() -> Unit)? = null,
    rightActions: @Composable (RowScope.() -> Unit)? = null,
    modifier: Modifier = Modifier,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    onClick: (() -> Unit)? = null,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val indication = LocalIndication.current

    val holdDown = remember { mutableStateOf<HoldDownInteraction.HoldDown?>(null) }
    LaunchedEffect(holdDownState) {
        if (holdDownState) {
            val interaction = HoldDownInteraction.HoldDown()
            holdDown.value = interaction
            interactionSource.emit(interaction)
        } else {
            holdDown.value?.let { oldValue ->
                interactionSource.emit(HoldDownInteraction.Release(oldValue))
                holdDown.value = null
            }
        }
    }

    val clickableModifier = remember(onClick, enabled, interactionSource, indication) {
        if (onClick != null && enabled) {
            Modifier.clickable(
                indication = indication,
                interactionSource = interactionSource,
                onClick = onClick
            )
        } else Modifier
    }

    Row(
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
            .then(clickableModifier)
            .padding(insideMargin),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left action (optional)
        leftAction?.let { it() }

        // Content
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            if (title != null) {
                Text(
                    text = title,
                    fontSize = MiuixTheme.textStyles.headline1.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = titleColor.color(enabled)
                )
            }
            if (summary != null) {
                Text(
                    text = summary,
                    fontSize = MiuixTheme.textStyles.body2.fontSize,
                    color = summaryColor.color(enabled)
                )
            }
        }

        if (rightActions != null) {
            // Add a Spacer for margin when rightActions are present
            Spacer(modifier = Modifier.width(8.dp))

            // Right actions (optional)
            rightActions()
        }
    }
}

object BasicComponentDefaults {

    /**
     * The default margin inside the [BasicComponent].
     */
    val InsideMargin = PaddingValues(16.dp)

    /**
     * The default color of the title.
     */
    @Composable
    fun titleColor(
        color: Color = MiuixTheme.colorScheme.onSurface,
        disabledColor: Color = MiuixTheme.colorScheme.disabledOnSecondaryVariant
    ): BasicComponentColors {
        return BasicComponentColors(
            color = color,
            disabledColor = disabledColor
        )
    }

    /**
     * The default color of the summary.
     */
    @Composable
    fun summaryColor(
        color: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        disabledColor: Color = MiuixTheme.colorScheme.disabledOnSecondaryVariant
    ): BasicComponentColors = BasicComponentColors(
        color = color,
        disabledColor = disabledColor
    )
}

@Immutable
class BasicComponentColors(
    private val color: Color,
    private val disabledColor: Color
) {
    @Stable
    internal fun color(enabled: Boolean): Color = if (enabled) color else disabledColor
}
