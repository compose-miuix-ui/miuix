// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth

/**
 * Lays out a glass navigation capsule and an independent trailing action.
 *
 * The action's measured width and [spacing] are reserved before measuring [content], so both fit
 * within the same constraints and are centered as a group. A null [trailingAction] reserves no
 * space. Placement follows the current layout direction.
 *
 * @param modifier Modifier applied to the combined layout.
 * @param spacing Space between the capsule and the action.
 * @param trailingAction Optional independent action, such as an icon button.
 * @param content Navigation capsule, excluding outer margins and system bar insets.
 */
@Composable
internal fun GlassNavigationBarLayout(
    modifier: Modifier = Modifier,
    spacing: Dp = GlassNavigationBarDefaults.ActionSpacing,
    trailingAction: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = {
            Box(propagateMinConstraints = true) { content() }
            if (trailingAction != null) {
                Box { trailingAction() }
            }
        },
    ) { measurables, constraints ->
        val action = measurables.getOrNull(1)?.measure(constraints.copy(minWidth = 0, minHeight = 0))
        val actionWidth = action?.width ?: 0
        val gap = if (action != null) {
            spacing.roundToPx().coerceIn(0, (constraints.maxWidth - actionWidth).coerceAtLeast(0))
        } else {
            0
        }
        val bar = measurables[0].measure(
            constraints.copy(
                minWidth = (constraints.minWidth - actionWidth - gap).coerceAtLeast(0),
                maxWidth = if (constraints.hasBoundedWidth) {
                    (constraints.maxWidth - actionWidth - gap).coerceAtLeast(0)
                } else {
                    constraints.maxWidth
                },
            ),
        )
        val groupWidth = bar.width + gap + actionWidth
        val width = constraints.constrainWidth(groupWidth)
        val height = constraints.constrainHeight(maxOf(bar.height, action?.height ?: 0))
        layout(width, height) {
            val start = (width - groupWidth) / 2
            bar.placeRelative(start, (height - bar.height) / 2)
            action?.placeRelative(start + bar.width + gap, (height - action.height) / 2)
        }
    }
}
