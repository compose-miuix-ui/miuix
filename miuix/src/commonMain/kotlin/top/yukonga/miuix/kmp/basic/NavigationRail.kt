// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A [NavigationRail] that is suitable for wide screens.
 *
 * @param modifier The modifier to be applied to the [NavigationRail].
 * @param header The header of the [NavigationRail], usually a [FloatingActionButton] or a logo.
 * @param color The color of the [NavigationRail].
 * @param showDivider Whether to show the divider line between the [NavigationRail] and the content.
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [NavigationRail].
 * @param minWidth The minimum width of the [NavigationRail].
 * @param content The content of the [NavigationRail], usually [NavigationRailItem]s.
 */
@Composable
fun NavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    color: Color = MiuixTheme.colorScheme.surface,
    showDivider: Boolean = true,
    defaultWindowInsetsPadding: Boolean = true,
    minWidth: Dp = NavigationRailDefaults.MinWidth,
    mode: NavigationRailDisplayMode = NavigationRailDisplayMode.IconAndText,
    content: @Composable ColumnScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier
                        .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Vertical))
                        .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Start))
                        .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Start))
                } else {
                    Modifier
                },
            )
            .background(color),
    ) {
        Column(
            modifier = Modifier
                .width(minWidth)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(vertical = NavigationRailDefaults.VerticalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            if (header != null) {
                header()
                Spacer(modifier = Modifier.height(NavigationRailDefaults.HeaderSpacing))
            }
            CompositionLocalProvider(LocalNavigationRailDisplayMode provides mode) {
                content()
            }
        }
        if (showDivider) {
            VerticalDivider()
        }
    }
}

/**
 * A [NavigationRailItem] that is suitable for [NavigationRail].
 *
 * @param selected Whether the item is selected.
 * @param onClick The callback when the item is clicked.
 * @param icon The icon of the item.
 * @param label The label of the item.
 * @param modifier The modifier to be applied to the [NavigationRailItem].
 * @param enabled Whether the item is enabled.
 */
@Composable
fun NavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    var isPressed by remember { mutableStateOf(false) }

    val onSurfaceContainerColor = MiuixTheme.colorScheme.onSurfaceContainer
    val tint = when {
        isPressed -> if (selected) {
            onSurfaceContainerColor.copy(alpha = NavigationRailDefaults.SelectedPressedAlpha)
        } else {
            onSurfaceContainerColor.copy(alpha = NavigationRailDefaults.UnselectedPressedAlpha)
        }

        selected -> onSurfaceContainerColor

        else -> onSurfaceContainerColor.copy(NavigationRailDefaults.UnselectedAlpha)
    }
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val mode = LocalNavigationRailDisplayMode.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (enabled) {
                            isPressed = true
                            tryAwaitRelease()
                            isPressed = false
                        }
                    },
                    onTap = { if (enabled) onClick() },
                )
            }
            .padding(vertical = NavigationRailDefaults.ItemVerticalPadding)
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (mode) {
            NavigationRailDisplayMode.IconAndText -> {
                Image(
                    modifier = Modifier.size(NavigationRailDefaults.IconSize),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                Spacer(modifier = Modifier.height(NavigationRailDefaults.IconTextSpacing))
                Text(
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = NavigationRailDefaults.LabelFontSize,
                    fontWeight = fontWeight,
                )
            }

            NavigationRailDisplayMode.IconWithSelectedLabel -> {
                Image(
                    modifier = Modifier.size(NavigationRailDefaults.IconSize),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                if (selected) {
                    Spacer(modifier = Modifier.height(NavigationRailDefaults.IconTextSpacing))
                    Text(
                        text = label,
                        color = tint,
                        textAlign = TextAlign.Center,
                        fontSize = NavigationRailDefaults.LabelFontSize,
                        fontWeight = fontWeight,
                    )
                }
            }

            NavigationRailDisplayMode.TextOnly -> {
                Text(
                    modifier = Modifier.padding(vertical = NavigationRailDefaults.TextOnlyVerticalPadding),
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = NavigationRailDefaults.TextOnlyFontSize,
                    fontWeight = fontWeight,
                )
            }

            else -> {
                Image(
                    modifier = Modifier.size(NavigationRailDefaults.IconSize),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
            }
        }
    }
}

/** Contains default values used by [NavigationRail] and [NavigationRailItem]. */
object NavigationRailDefaults {
    /** The default minimum width of the [NavigationRail]. */
    val MinWidth = 80.dp

    /** The default vertical padding of the [NavigationRail] content. */
    val VerticalPadding = 24.dp

    /** The default spacing after the header. */
    val HeaderSpacing = 24.dp

    /** The default icon size. */
    val IconSize = 28.dp

    /** The default spacing between icon and text. */
    val IconTextSpacing = 4.dp

    /** The default vertical padding for each item. */
    val ItemVerticalPadding = 12.dp

    /** The default label font size. */
    val LabelFontSize = 12.sp

    /** The font size in [NavigationRailDisplayMode.TextOnly] mode. */
    val TextOnlyFontSize = 14.sp

    /** The vertical padding in [NavigationRailDisplayMode.TextOnly] mode. */
    val TextOnlyVerticalPadding = 4.dp

    /** The alpha value for the selected item when pressed. */
    val SelectedPressedAlpha = 0.5f

    /** The alpha value for an unselected item when pressed. */
    val UnselectedPressedAlpha = 0.6f

    /** The alpha value for an unselected item. */
    val UnselectedAlpha = 0.4f
}

/**
 * Defines the display mode for items in a [NavigationRail].
 *
 * This controls whether to show both icon and text, icon only, text only,
 * or icon with text only when selected.
 */
enum class NavigationRailDisplayMode {
    /** Show both icon and text. */
    IconAndText,

    /** Show icon only. */
    IconOnly,

    /** Show text only. */
    TextOnly,

    /** Show icon always, show text only when selected. */
    IconWithSelectedLabel,
}

/**
 * A composition local to control the display mode for items in a [NavigationRail].
 */
val LocalNavigationRailDisplayMode = compositionLocalOf { NavigationRailDisplayMode.IconAndText }
