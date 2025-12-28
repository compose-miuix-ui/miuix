// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
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
 * @param modifier The modifier to be applied to the [BasicComponent].
 * @param title The title of the [BasicComponent].
 * @param titleColor The color of the title.
 * @param summary The summary of the [BasicComponent].
 * @param summaryColor The color of the summary.
 * @param leftAction The [Composable] content that on the left side of the [BasicComponent].
 * @param rightActions The [Composable] content on the right side of the [BasicComponent].
 * @param bottomAction The [Composable] content at the bottom of the [BasicComponent].
 * @param insideMargin The margin inside the [BasicComponent].
 * @param onClick The callback when the [BasicComponent] is clicked.
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [BasicComponent] is enabled.
 * @param interactionSource The [MutableInteractionSource] for the [BasicComponent].
 */
@Composable
fun BasicComponent(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    leftAction: @Composable (() -> Unit)? = null,
    rightActions: @Composable (RowScope.() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    onClick: (() -> Unit)? = null,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    BasicComponent(
        leftAction = leftAction,
        rightActions = rightActions,
        bottomAction = bottomAction,
        modifier = modifier,
        insideMargin = insideMargin,
        onClick = onClick,
        holdDownState = holdDownState,
        enabled = enabled,
        interactionSource = interactionSource,
    ) {
        if (title != null) {
            Text(
                text = title,
                fontSize = MiuixTheme.textStyles.headline1.fontSize,
                fontWeight = FontWeight.Medium,
                color = titleColor.color(enabled),
            )
        }
        if (summary != null) {
            Text(
                text = summary,
                fontSize = MiuixTheme.textStyles.body2.fontSize,
                color = summaryColor.color(enabled),
            )
        }
    }
}

/**
 * A basic component with Miuix style. Widely used in other extension components.
 *
 * @param modifier The modifier to be applied to the [BasicComponent].
 * @param leftAction The [Composable] content that on the left side of the [BasicComponent].
 * @param rightActions The [Composable] content on the right side of the [BasicComponent].
 * @param bottomAction The [Composable] content at the bottom of the [BasicComponent].
 * @param insideMargin The margin inside the [BasicComponent].
 * @param onClick The callback when the [BasicComponent] is clicked.
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [BasicComponent] is enabled.
 * @param interactionSource The [MutableInteractionSource] for the [BasicComponent].
 * @param content The content of the [BasicComponent].
 */
@Composable
fun BasicComponent(
    modifier: Modifier = Modifier,
    leftAction: @Composable (() -> Unit)? = null,
    rightActions: @Composable (RowScope.() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    onClick: (() -> Unit)? = null,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val indication = LocalIndication.current
    val currentOnClick by rememberUpdatedState(onClick)

    val holdDown = remember { mutableStateOf<HoldDownInteraction.HoldDown?>(null) }
    LaunchedEffect(holdDownState, interactionSource, indication) {
        if (holdDownState) {
            holdDown.value?.let { oldValue ->
                interactionSource.emit(HoldDownInteraction.Release(oldValue))
                holdDown.value = null
            }
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

    val clickableModifier = remember(enabled, interactionSource, indication, currentOnClick) {
        if (enabled && currentOnClick != null) {
            Modifier.clickable(
                interactionSource = interactionSource,
                indication = indication,
                onClick = currentOnClick!!,
            )
        } else {
            Modifier
        }
    }

    Column(
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
            .then(clickableModifier)
            .padding(insideMargin),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leftAction?.let { it() }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                content = content,
            )

            if (rightActions != null) {
                Spacer(modifier = Modifier.width(8.dp))
                rightActions()
            }
        }

        if (bottomAction != null) {
            Spacer(modifier = Modifier.height(8.dp))
            bottomAction()
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
        color: Color = MiuixTheme.colorScheme.onBackground,
        disabledColor: Color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
    ): BasicComponentColors = BasicComponentColors(
        color = color,
        disabledColor = disabledColor,
    )

    /**
     * The default color of the summary.
     */
    @Composable
    fun summaryColor(
        color: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        disabledColor: Color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
    ): BasicComponentColors = BasicComponentColors(
        color = color,
        disabledColor = disabledColor,
    )
}

@Immutable
data class BasicComponentColors(
    val color: Color,
    val disabledColor: Color,
) {
    @Stable
    internal fun color(enabled: Boolean): Color = if (enabled) color else disabledColor
}
