// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowRight
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A arrow with a title and a summary.
 *
 * @param title The title of the [SuperArrow].
 * @param titleColor The color of the title.
 * @param summary The summary of the [SuperArrow].
 * @param summaryColor The color of the summary.
 * @param leftAction The [Composable] content that on the left side of the [SuperArrow].
 * @param rightActions The [Composable] content on the right side of the [SuperArrow].
 * @param bottomAction The [Composable] content at the bottom of the [SuperArrow].
 * @param modifier The modifier to be applied to the [SuperArrow].
 * @param insideMargin The margin inside the [SuperArrow].
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [SuperArrow] is clickable.
 */
@Composable
@NonRestartableComposable
fun SuperArrow(
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    leftAction: @Composable (() -> Unit)? = null,
    rightActions: @Composable RowScope.() -> Unit = {},
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    onClick: (() -> Unit)? = null,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
) {
    BasicComponent(
        modifier = modifier,
        insideMargin = insideMargin,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        leftAction = leftAction,
        rightActions = {
            SuperArrowRightActions(
                rightActions = rightActions,
                enabled = enabled,
            )
        },
        bottomAction = bottomAction,
        onClick = onClick,
        holdDownState = holdDownState,
        enabled = enabled,
    )
}

@Composable
private fun RowScope.SuperArrowRightActions(
    rightActions: @Composable RowScope.() -> Unit,
    enabled: Boolean,
) {
    rightActions()
    val actionColors = SuperArrowDefaults.rightActionColors()
    val tintFilter by remember(enabled, actionColors) {
        derivedStateOf { ColorFilter.tint(actionColors.color(enabled = enabled)) }
    }
    val layoutDirection = LocalLayoutDirection.current
    Image(
        modifier = Modifier
            .size(width = 10.dp, height = 16.dp)
            .graphicsLayer {
                scaleX = if (layoutDirection == LayoutDirection.Rtl) -1f else 1f
            },
        imageVector = MiuixIcons.Basic.ArrowRight,
        contentDescription = null,
        colorFilter = tintFilter,
    )
}

object SuperArrowDefaults {
    /**
     * The default color of the arrow.
     */
    @Composable
    fun rightActionColors() = RightActionColors(
        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
        disabledColor = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
    )
}

@Immutable
data class RightActionColors(
    private val color: Color,
    private val disabledColor: Color,
) {
    @Stable
    internal fun color(enabled: Boolean): Color = if (enabled) color else disabledColor
}
