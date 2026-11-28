// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.preference

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import top.yukonga.miuix.kmp.icon.symbol.Next
import top.yukonga.miuix.kmp.symbol.SymbolWeights
import top.yukonga.miuix.kmp.symbol.rememberSymbolPainter
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * An arrow with a title and a summary.
 *
 * @param title The title of the [ArrowPreference].
 * @param titleColor The color of the title.
 * @param summary The summary of the [ArrowPreference].
 * @param summaryColor The color of the summary.
 * @param startAction The [Composable] content on the start side of the [ArrowPreference].
 * @param endActions The [Composable] content on the end side of the [ArrowPreference].
 * @param bottomAction The [Composable] content at the bottom of the [ArrowPreference].
 * @param modifier The modifier to be applied to the [ArrowPreference].
 * @param insideMargin The margin inside the [ArrowPreference].
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [ArrowPreference] is clickable.
 */
@Composable
fun ArrowPreference(
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    startAction: @Composable (() -> Unit)? = null,
    endActions: @Composable RowScope.() -> Unit = EmptyEndActions,
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
        startAction = startAction,
        endActions = {
            if (endActions !== EmptyEndActions) {
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f, fill = false),
                ) {
                    endActions()
                }
            }
            ArrowPreferenceEndAction(
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
private fun RowScope.ArrowPreferenceEndAction(
    enabled: Boolean,
) {
    val actionColors = ArrowPreferenceDefaults.endActionColors()
    val tintFilter = remember(enabled, actionColors) {
        ColorFilter.tint(actionColors.color(enabled = enabled))
    }
    val layoutDirection = LocalLayoutDirection.current
    val flipModifier = if (layoutDirection == LayoutDirection.Rtl) RtlFlipModifier else Modifier
    Image(
        modifier = Modifier
            .size(width = 10.dp, height = 16.dp)
            .align(Alignment.CenterVertically)
            .wrapContentSize(unbounded = true)
            .size(width = 12.dp, height = 32.dp)
            .then(flipModifier),
        painter = rememberSymbolPainter(MiuixIcons.Symbol.Next, weight = SymbolWeights.Light),
        contentDescription = null,
        colorFilter = tintFilter,
    )
}

private val EmptyEndActions: @Composable RowScope.() -> Unit = {}
private val RtlFlipModifier = Modifier.graphicsLayer { scaleX = -1f }

object ArrowPreferenceDefaults {
    /**
     * The default color of the arrow.
     */
    @Composable
    fun endActionColors(): EndActionColors {
        val color = MiuixTheme.colorScheme.onSurfaceVariantActions
        val disabledColor = MiuixTheme.colorScheme.disabledOnSecondaryVariant
        return remember(color, disabledColor) {
            EndActionColors(
                color = color,
                disabledColor = disabledColor,
            )
        }
    }
}

@Immutable
data class EndActionColors(
    private val color: Color,
    private val disabledColor: Color,
) {
    @Stable
    internal fun color(enabled: Boolean): Color = if (enabled) color else disabledColor
}
