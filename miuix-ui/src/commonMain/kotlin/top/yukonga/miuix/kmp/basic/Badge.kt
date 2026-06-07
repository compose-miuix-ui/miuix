// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.HorizontalRuler
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.VerticalRuler
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastFirst
import top.yukonga.miuix.kmp.theme.LocalContentColor
import top.yukonga.miuix.kmp.theme.LocalTextStyles
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * Miuix badge box.
 *
 * A badge represents dynamic information such as a number of pending requests in a navigation bar.
 *
 * Badges can be icon only or contain short text.
 *
 * A common use case is to display a badge with navigation bar items.
 *
 * @param badge the badge to be displayed - typically a [Badge]
 * @param modifier the [Modifier] to be applied to this BadgedBox
 * @param content the anchor to which this badge will be positioned
 */
@Composable
fun BadgedBox(
    badge: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Layout(
        modifier = modifier,
        content = {
            Box(
                modifier = Modifier.layoutId("anchor"),
                contentAlignment = Alignment.Center,
                content = content,
            )
            Box(modifier = Modifier.layoutId("badge"), content = badge)
        },
    ) { measurables, constraints ->
        val badgePlaceable =
            measurables
                .fastFirst { it.layoutId == "badge" }
                .measure(
                    // Measure with loose constraints for height as we don't want the text to take
                    // up more
                    // space than it needs.
                    constraints.copy(minHeight = 0),
                )

        val anchorPlaceable = measurables.fastFirst { it.layoutId == "anchor" }.measure(constraints)

        val firstBaseline = anchorPlaceable[FirstBaseline]
        val lastBaseline = anchorPlaceable[LastBaseline]
        val totalWidth = anchorPlaceable.width
        val totalHeight = anchorPlaceable.height

        layout(
            width = totalWidth,
            height = totalHeight,
            // Provide custom baselines based only on the anchor content to avoid default baseline
            // calculations from including by any badge content.
            alignmentLines = mapOf(FirstBaseline to firstBaseline, LastBaseline to lastBaseline),
        ) {
            // Use the width of the badge to infer whether it has any content (based on radius used
            // in [Badge]) and determine its horizontal offset.
            val hasContent = badgePlaceable.width > (BadgeDefaults.Size.roundToPx())
            val badgeHorizontalOffset =
                if (hasContent) BadgeWithContentHorizontalOffset else BadgeOffset
            val badgeVerticalOffset =
                if (hasContent) BadgeWithContentVerticalOffset else BadgeOffset

            anchorPlaceable.placeRelative(0, 0)

            // Desired Badge placement
            val badgeX =
                minOf(
                    anchorPlaceable.width - badgeHorizontalOffset.roundToPx(),
                    BadgeEndRuler.current(Float.POSITIVE_INFINITY).toInt() - badgePlaceable.width,
                )

            val badgeY =
                maxOf(
                    -badgePlaceable.height + badgeVerticalOffset.roundToPx(),
                    BadgeTopRuler.current(Float.NEGATIVE_INFINITY).toInt(),
                )

            badgePlaceable.placeRelative(badgeX, badgeY)
        }
    }
}

/**
 * A badge represents dynamic information such as a number of pending requests in a navigation bar.
 *
 * Badges can be icon only or contain short text.
 *
 * See [BadgedBox] for a top level layout that will properly place the badge relative to content
 * such as text or an icon.
 *
 * @param modifier the [Modifier] to be applied to this badge
 * @param containerColor the color used for the background of this badge
 * @param contentColor the preferred color for content inside this badge
 * @param content optional content to be rendered inside this badge
 */
@Composable
fun Badge(
    modifier: Modifier = Modifier,
    containerColor: Color = BadgeDefaults.containerColor,
    contentColor: Color = BadgeDefaults.contentColor,
    content: @Composable (RowScope.() -> Unit)? = null,
) {
    val size = if (content != null) BadgeDefaults.LargeSize else BadgeDefaults.Size
    val shape = CircleShape

    // Draw badge container.
    Row(
        modifier =
        modifier
            .defaultMinSize(minWidth = size, minHeight = size)
            .background(color = containerColor, shape = shape)
            .then(
                if (content != null) {
                    Modifier.padding(horizontal = BadgeWithContentHorizontalPadding)
                } else {
                    Modifier
                },
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (content != null) {
            // Not using Surface composable because it blocks touch propagation behind it.
            // Center the single-line label within an explicit line height so the digits are
            // vertically centered consistently across platforms (footnote2 alone carries no
            // line-height style, leaving placement to platform-dependent font metrics).
            val style = MiuixTheme.textStyles.footnote2.copy(
                lineHeight = 16.sp,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None,
                ),
            )
            ProvideContentColorTextStyle(
                contentColor = contentColor,
                textStyle = style,
                content = { content() },
            )
        }
    }
}

/** Default values used for [Badge] implementations. */
object BadgeDefaults {
    /** Default size of an icon-only badge (no content). */
    val Size = 6.dp

    /** Default size of a badge containing content. */
    val LargeSize = 16.dp

    /** Default container color for a badge. */
    val containerColor: Color
        @Composable get() = MiuixTheme.colorScheme.error

    /** Default content color for a badge. */
    val contentColor: Color
        @Composable get() = MiuixTheme.colorScheme.onError
}

/**
 * Provides [contentColor] as [LocalContentColor] and merges [textStyle] into the ambient text style
 * for [content], so a bare [Text] inside the badge picks up the badge label styling automatically.
 */
@Composable
private fun ProvideContentColorTextStyle(
    contentColor: Color,
    textStyle: TextStyle,
    content: @Composable () -> Unit,
) {
    val textStyles = LocalTextStyles.current
    val badgeTextStyles = remember(textStyles, textStyle) {
        textStyles.copy(main = textStyles.main.merge(textStyle))
    }
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyles provides badgeTextStyles,
        content = content,
    )
}

/*@VisibleForTesting*/
// Leading and trailing text padding when a badge is displaying text that is too long to fit in
// a circular badge, e.g. if badge number is greater than 9.
internal val BadgeWithContentHorizontalPadding = 4.dp

/*@VisibleForTesting*/
// Offsets for badge when there is short or long content
// Horizontally align start/end of text badge 12.dp from the top end corner of its anchor
// Vertical overlap with anchor is 14.dp
internal val BadgeWithContentHorizontalOffset = 12.dp
internal val BadgeWithContentVerticalOffset = 14.dp

/*@VisibleForTesting*/
// Offsets for badge when there is no content
// Horizontally align start/end of icon only badge 6.dp from the end/start edge of anchor
// Vertical overlap with anchor is 6.dp
internal val BadgeOffset = 6.dp

internal val BadgeTopRuler = HorizontalRuler()
internal val BadgeEndRuler = VerticalRuler()

internal fun Modifier.badgeBounds() = this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(
        width = placeable.width,
        height = placeable.height,
        rulers = {
            // use provides instead of provideRelative cause we will place relative
            // in the badge code
            BadgeEndRuler provides coordinates.size.width.toFloat()
            BadgeTopRuler provides 0f
        },
    ) {
        placeable.place(0, 0)
    }
}
