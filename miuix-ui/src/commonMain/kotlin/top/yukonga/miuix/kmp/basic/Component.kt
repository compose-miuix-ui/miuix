// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.interfaces.HoldDownObserver
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A basic component with Miuix style. Widely used in other extension components.
 *
 * @param modifier The modifier to be applied to the [BasicComponent].
 * @param title The title of the [BasicComponent].
 * @param titleColor The color of the title.
 * @param summary The summary of the [BasicComponent].
 * @param summaryColor The color of the summary.
 * @param startAction The [Composable] content on the start side of the [BasicComponent].
 * @param endActions The [Composable] content on the end side of the [BasicComponent].
 * @param bottomAction The [Composable] content at the bottom of the [BasicComponent].
 * @param insideMargin The margin inside the [BasicComponent].
 * @param onClick The callback when the [BasicComponent] is clicked.
 * @param onClickLabel Optional label describing the click action for accessibility services.
 * @param role The semantic [Role] of the [BasicComponent] for accessibility services.
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [BasicComponent] is enabled.
 * @param interactionSource The [MutableInteractionSource] for the [BasicComponent].
 *   The value should remain null or non-null for the lifetime of this component;
 *   switching across recompositions will allocate a new internal source and lose pending interactions.
 */
@Composable
fun BasicComponent(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    startAction: @Composable (() -> Unit)? = null,
    endActions: @Composable (RowScope.() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    onClick: (() -> Unit)? = null,
    onClickLabel: String? = null,
    role: Role? = null,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
    BasicComponent(
        startAction = startAction,
        endActions = endActions,
        bottomAction = bottomAction,
        modifier = modifier,
        insideMargin = insideMargin,
        onClick = onClick,
        onClickLabel = onClickLabel,
        role = role,
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
 * @param startAction The [Composable] content on the start side of the [BasicComponent].
 * @param endActions The [Composable] content on the end side of the [BasicComponent].
 * @param bottomAction The [Composable] content at the bottom of the [BasicComponent].
 * @param insideMargin The margin inside the [BasicComponent].
 * @param onClick The callback when the [BasicComponent] is clicked.
 * @param onClickLabel Optional label describing the click action for accessibility services.
 * @param role The semantic [Role] of the [BasicComponent] for accessibility services.
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [BasicComponent] is enabled.
 * @param interactionSource The [MutableInteractionSource] for the [BasicComponent].
 *   The value should remain null or non-null for the lifetime of this component;
 *   switching across recompositions will allocate a new internal source and lose pending interactions.
 * @param content The content of the [BasicComponent].
 */
@Composable
fun BasicComponent(
    modifier: Modifier = Modifier,
    startAction: @Composable (() -> Unit)? = null,
    endActions: @Composable (RowScope.() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    onClick: (() -> Unit)? = null,
    onClickLabel: String? = null,
    role: Role? = null,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val currentOnClick by rememberUpdatedState(onClick)

    HoldDownObserver(holdDownState, interactionSource)

    val clickableModifier = remember(enabled, onClick != null, interactionSource, role, onClickLabel) {
        if (enabled && onClick != null) {
            Modifier.clickable(
                interactionSource = interactionSource,
                onClickLabel = onClickLabel,
                role = role,
                onClick = { currentOnClick?.invoke() },
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
        verticalArrangement = Arrangement.Center,
    ) {
        if (startAction == null && endActions == null) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                content = content,
            )
        } else {
            Layout(
                content = {
                    startAction?.let {
                        Column(
                            modifier = Modifier.layoutId("start"),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                        ) { it() }
                    }
                    Column(
                        modifier = Modifier.layoutId("center"),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                        content = content,
                    )
                    endActions?.let {
                        Column(
                            modifier = Modifier.layoutId("end"),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End,
                        ) {
                            Row { it() }
                        }
                    }
                },
            ) { measurables, constraints ->
                val spacerPx = 8.dp.roundToPx()

                val startMeasurable = measurables.firstOrNull { it.layoutId == "start" }
                val centerMeasurable = measurables.first { it.layoutId == "center" }
                val endMeasurable = measurables.firstOrNull { it.layoutId == "end" }

                val maxWidth = constraints.maxWidth
                val maxHeight = constraints.maxHeight

                val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

                val startPlaceable = startMeasurable?.measure(looseConstraints)
                val startWidth = startPlaceable?.width ?: 0
                val startSpacerWidth = if (startWidth > 0) spacerPx else 0
                val widthAfterStart = (maxWidth - startWidth - startSpacerWidth).coerceAtLeast(0)

                val endIntrinsicWidth = endMeasurable?.maxIntrinsicWidth(maxHeight) ?: 0
                val endHardCap = (widthAfterStart - spacerPx).coerceAtLeast(0) * 6 / 10
                val endTargetWidth = endIntrinsicWidth.coerceAtMost(endHardCap)
                val endPlaceable = endMeasurable?.measure(
                    looseConstraints.copy(maxWidth = endTargetWidth),
                )
                val endActualWidth = endPlaceable?.width ?: 0
                val endSpacerWidth = if (endActualWidth > 0) spacerPx else 0

                val widthForCenter = (widthAfterStart - endActualWidth - endSpacerWidth).coerceAtLeast(0)
                val centerPlaceable = centerMeasurable.measure(
                    looseConstraints.copy(maxWidth = widthForCenter),
                )

                val startHeight = startPlaceable?.height ?: 0
                val endHeight = endPlaceable?.height ?: 0
                val rowHeight = maxOf(startHeight, centerPlaceable.height, endHeight)
                val layoutHeight = rowHeight
                    .coerceIn(constraints.minHeight, maxHeight.takeIf { it != Constraints.Infinity } ?: rowHeight)

                layout(width = maxWidth, height = layoutHeight) {
                    val startTop = (rowHeight - startHeight).coerceAtLeast(0) / 2
                    val centerTop = (rowHeight - centerPlaceable.height) / 2
                    val endTop = (rowHeight - endHeight).coerceAtLeast(0) / 2

                    startPlaceable?.placeRelative(0, startTop)

                    val centerX = startWidth + startSpacerWidth
                    centerPlaceable.placeRelative(centerX, centerTop)

                    endPlaceable?.let {
                        val endX = maxWidth - it.width
                        it.placeRelative(endX, endTop)
                    }
                }
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
    ): BasicComponentColors = remember(color, disabledColor) {
        BasicComponentColors(
            color = color,
            disabledColor = disabledColor,
        )
    }

    /**
     * The default color of the summary.
     */
    @Composable
    fun summaryColor(
        color: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        disabledColor: Color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
    ): BasicComponentColors = remember(color, disabledColor) {
        BasicComponentColors(
            color = color,
            disabledColor = disabledColor,
        )
    }
}

@Immutable
data class BasicComponentColors(
    val color: Color,
    val disabledColor: Color,
) {
    @Stable
    internal fun color(enabled: Boolean): Color = if (enabled) color else disabledColor
}
