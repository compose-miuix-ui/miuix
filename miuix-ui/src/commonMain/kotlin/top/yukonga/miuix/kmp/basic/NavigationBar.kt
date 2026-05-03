// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.Platform
import top.yukonga.miuix.kmp.utils.platform

/**
 * A [NavigationBar] that with 2 to 5 items.
 *
 * @param modifier The modifier to be applied to the [NavigationBar].
 * @param color The color of the [NavigationBar].
 * @param showDivider Whether to show the divider line between the [NavigationBar] and the content.
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [NavigationBar].
 * @param mode The mode for displaying items in the [NavigationBar]. It can show icons, text or both.
 * @param content The content of the [NavigationBar], usually [NavigationBarItem]s.
 */
@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    color: Color = MiuixTheme.colorScheme.surface,
    showDivider: Boolean = true,
    defaultWindowInsetsPadding: Boolean = true,
    mode: NavigationBarDisplayMode = NavigationBarDisplayMode.IconAndText,
    content: @Composable RowScope.() -> Unit,
) {
    val captionBarPaddings = WindowInsets.captionBar.only(WindowInsetsSides.Bottom).asPaddingValues()
    val captionBarBottomPaddingValue = captionBarPaddings.calculateBottomPadding()

    val animatedCaptionBarHeight by animateDpAsState(
        targetValue = if (captionBarBottomPaddingValue > 0.dp) captionBarBottomPaddingValue else 0.dp,
        animationSpec = tween(durationMillis = 300),
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color),
    ) {
        if (showDivider) {
            HorizontalDivider()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CompositionLocalProvider(LocalNavigationBarDisplayMode provides mode) {
                content()
            }
        }
        if (defaultWindowInsetsPadding) {
            val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .layout { measurable, constraints ->
                        val totalHeight = (navigationBarsPadding.calculateBottomPadding() + animatedCaptionBarHeight).roundToPx()
                        val fixedConstraints = constraints.copy(minHeight = totalHeight, maxHeight = totalHeight)
                        val placeable = measurable.measure(fixedConstraints)
                        layout(placeable.width, totalHeight) {
                            placeable.placeRelative(0, 0)
                        }
                    }
                    .pointerInput(Unit) { detectTapGestures { /* Do nothing to consume the click */ } },
            )
        }
    }
}

/**
 * A [NavigationBarItem] that is suitable for [NavigationBar].
 *
 * @param selected Whether the item is selected.
 * @param onClick The callback when the item is clicked.
 * @param icon The icon of the item.
 * @param label The label of the item.
 * @param modifier The modifier to be applied to the [NavigationBarItem].
 * @param enabled Whether the item is enabled.
 */
@Composable
fun RowScope.NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val platform = platform()
    val itemHeight = if (platform != Platform.IOS) NavigationBarDefaults.ItemHeight else NavigationBarDefaults.ItemHeightIOS
    var isPressed by remember { mutableStateOf(false) }
    val currentOnClick by rememberUpdatedState(onClick)
    val currentEnabled by rememberUpdatedState(enabled)

    val onSurfaceContainerColor = MiuixTheme.colorScheme.onSurfaceContainer
    val tint = when {
        isPressed -> if (selected) {
            onSurfaceContainerColor.copy(alpha = NavigationBarDefaults.SelectedPressedAlpha)
        } else {
            onSurfaceContainerColor.copy(alpha = NavigationBarDefaults.UnselectedPressedAlpha)
        }

        selected -> onSurfaceContainerColor

        else -> onSurfaceContainerColor.copy(NavigationBarDefaults.UnselectedAlpha)
    }
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val mode = LocalNavigationBarDisplayMode.current

    Column(
        modifier = modifier
            .height(itemHeight)
            .weight(1f)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (currentEnabled) {
                            isPressed = true
                            tryAwaitRelease()
                            isPressed = false
                        }
                    },
                    onTap = { if (currentEnabled) currentOnClick() },
                )
            },
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = if (mode == NavigationBarDisplayMode.IconAndText || mode == NavigationBarDisplayMode.IconWithSelectedLabel) Arrangement.Top else Arrangement.Center,
    ) {
        when (mode) {
            NavigationBarDisplayMode.IconAndText -> {
                Image(
                    modifier = Modifier.padding(top = NavigationBarDefaults.IconTopPadding).size(NavigationBarDefaults.IconSize),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                Text(
                    modifier = Modifier.padding(bottom = if (platform != Platform.IOS) NavigationBarDefaults.BottomPadding else 0.dp),
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = NavigationBarDefaults.LabelFontSize,
                    fontWeight = fontWeight,
                )
            }

            NavigationBarDisplayMode.IconWithSelectedLabel -> {
                val defaultPadding = (itemHeight - NavigationBarDefaults.IconSize) / 2
                val iconTopPadding by animateDpAsState(
                    targetValue = if (selected) NavigationBarDefaults.IconTopPadding else defaultPadding,
                    animationSpec = tween(durationMillis = 300),
                    label = "iconTopPadding",
                )
                val textAlpha by animateFloatAsState(
                    targetValue = if (selected) 1f else 0f,
                    animationSpec = tween(durationMillis = 300),
                    label = "textAlpha",
                )

                Image(
                    modifier = Modifier
                        .layout { measurable, constraints ->
                            val topPaddingPx = iconTopPadding.roundToPx()
                            val placeable = measurable.measure(constraints.offset(vertical = -topPaddingPx))
                            layout(placeable.width, placeable.height + topPaddingPx) {
                                placeable.placeRelative(0, topPaddingPx)
                            }
                        }
                        .size(NavigationBarDefaults.IconSize),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = if (platform != Platform.IOS) NavigationBarDefaults.BottomPadding else 0.dp)
                        .graphicsLayer { alpha = textAlpha },
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = NavigationBarDefaults.LabelFontSize,
                    fontWeight = fontWeight,
                )
            }

            NavigationBarDisplayMode.TextOnly -> {
                Text(
                    modifier = Modifier
                        .padding(vertical = if (platform != Platform.IOS) NavigationBarDefaults.BottomPadding else 0.dp),
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = FloatingNavigationBarDefaults.TextFontSize,
                    fontWeight = fontWeight,
                )
            }

            else -> {
                Image(
                    modifier = Modifier.size(NavigationBarDefaults.IconSize),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
            }
        }
    }
}

/**
 * A floating navigation bar that supports 2 to 5 items.
 *
 * @param modifier A [Modifier] to be applied to the [FloatingNavigationBar] for additional customization.
 * @param color The background color of the [FloatingNavigationBar].
 * @param cornerRadius The corner radius of the [FloatingNavigationBar], used for rounded corners.
 * @param horizontalAlignment The alignment of the [FloatingNavigationBar] within its parent, typically used to center it horizontally.
 * @param horizontalOutSidePadding The horizontal padding to be applied outside the [FloatingNavigationBar].
 * @param shadowElevation The shadow elevation of the [FloatingNavigationBar].
 * @param showDivider Whether to show the divider line around the [FloatingNavigationBar].
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [FloatingNavigationBar].
 * @param mode The mode for displaying items in the [FloatingNavigationBar]. It can show icons, text or both.
 * @param content The content of the [FloatingNavigationBar], usually [FloatingNavigationBarItem]s.
 */
@Composable
fun FloatingNavigationBar(
    modifier: Modifier = Modifier,
    color: Color = MiuixTheme.colorScheme.surfaceContainer,
    cornerRadius: Dp = FloatingToolbarDefaults.CornerRadius,
    horizontalAlignment: Alignment.Horizontal = CenterHorizontally,
    horizontalOutSidePadding: Dp = FloatingNavigationBarDefaults.HorizontalOutSidePadding,
    shadowElevation: Dp = FloatingNavigationBarDefaults.ShadowElevation,
    showDivider: Boolean = false,
    defaultWindowInsetsPadding: Boolean = true,
    mode: FloatingNavigationBarDisplayMode = FloatingNavigationBarDisplayMode.IconOnly,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val shape = RoundedCornerShape(cornerRadius)

    val platform = platform()
    val bottomPaddingValue = when (platform) {
        Platform.IOS -> 8.dp

        Platform.Android -> {
            val navBarBottomPadding =
                WindowInsets.navigationBars.only(WindowInsetsSides.Bottom).asPaddingValues().calculateBottomPadding()
            if (navBarBottomPadding != 0.dp) 8.dp + navBarBottomPadding else 36.dp
        }

        else -> 36.dp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (horizontalAlignment == Alignment.Start) horizontalOutSidePadding else 0.dp,
                end = if (horizontalAlignment == Alignment.End) horizontalOutSidePadding else 0.dp,
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = bottomPaddingValue)
                .then(
                    if (defaultWindowInsetsPadding) {
                        Modifier
                            .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Bottom))
                            .windowInsetsPadding(WindowInsets.captionBar.only(WindowInsetsSides.Bottom))
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    } else {
                        Modifier
                    },
                )
                .then(
                    if (showDivider) {
                        Modifier
                            .background(
                                color = MiuixTheme.colorScheme.dividerLine,
                                shape = shape,
                            )
                            .padding(0.75.dp)
                    } else {
                        Modifier
                    },
                )
                .then(
                    if (shadowElevation > 0.dp) {
                        Modifier.graphicsLayer(
                            shadowElevation = with(density) { shadowElevation.toPx() },
                            shape = shape,
                            clip = cornerRadius > 0.dp,
                        )
                    } else if (cornerRadius > 0.dp) {
                        Modifier.clip(shape)
                    } else {
                        Modifier
                    },
                )
                .background(color)
                .then(modifier)
                .padding(horizontal = FloatingNavigationBarDefaults.HorizontalPadding)
                .align(horizontalAlignment)
                .pointerInput(Unit) {
                    detectTapGestures { /* Consume click */ }
                },
            horizontalArrangement = Arrangement.spacedBy(FloatingNavigationBarDefaults.ItemSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CompositionLocalProvider(LocalFloatingNavigationBarDisplayMode provides mode) {
                content()
            }
        }
    }
}

/**
 * A [FloatingNavigationBarItem] that is suitable for [FloatingNavigationBar].
 *
 * @param selected Whether the item is selected.
 * @param onClick The callback when the item is clicked.
 * @param icon The icon of the item.
 * @param label The label of the item.
 * @param modifier The modifier to be applied to the [FloatingNavigationBarItem].
 * @param enabled Whether the item is enabled.
 */
@Composable
fun FloatingNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    var isPressed by remember { mutableStateOf(false) }
    val currentOnClick by rememberUpdatedState(onClick)
    val currentEnabled by rememberUpdatedState(enabled)

    val onSurfaceContainerColor = MiuixTheme.colorScheme.onSurfaceContainer
    val tint = when {
        isPressed -> if (selected) {
            onSurfaceContainerColor.copy(alpha = FloatingNavigationBarDefaults.SelectedPressedAlpha)
        } else {
            onSurfaceContainerColor.copy(alpha = FloatingNavigationBarDefaults.UnselectedPressedAlpha)
        }

        selected -> onSurfaceContainerColor

        else -> onSurfaceContainerColor.copy(FloatingNavigationBarDefaults.UnselectedAlpha)
    }
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val mode = LocalFloatingNavigationBarDisplayMode.current

    Column(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if (currentEnabled) {
                            isPressed = true
                            tryAwaitRelease()
                            isPressed = false
                        }
                    },
                    onTap = { if (currentEnabled) currentOnClick() },
                )
            },
        horizontalAlignment = CenterHorizontally,
    ) {
        when (mode) {
            FloatingNavigationBarDisplayMode.IconAndText -> {
                Image(
                    modifier = Modifier.padding(top = FloatingNavigationBarDefaults.VerticalPadding).size(FloatingNavigationBarDefaults.IconSize),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                Box(
                    modifier = Modifier.padding(bottom = FloatingNavigationBarDefaults.VerticalPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    // Invisible text for layout calculation (always bold)
                    Text(
                        modifier = Modifier.alpha(0f),
                        text = label,
                        textAlign = TextAlign.Center,
                        fontSize = FloatingNavigationBarDefaults.LabelFontSize,
                        fontWeight = FontWeight.Bold, // Always bold for layout
                    )
                    // Visible text
                    Text(
                        text = label,
                        color = tint,
                        textAlign = TextAlign.Center,
                        fontSize = FloatingNavigationBarDefaults.LabelFontSize,
                        fontWeight = fontWeight,
                    )
                }
            }

            FloatingNavigationBarDisplayMode.TextOnly -> {
                Box(
                    modifier = Modifier.padding(vertical = FloatingNavigationBarDefaults.TextVerticalPadding, horizontal = FloatingNavigationBarDefaults.TextHorizontalPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    // Invisible text for layout calculation
                    Text(
                        modifier = Modifier.alpha(0f),
                        text = label,
                        textAlign = TextAlign.Center,
                        fontSize = FloatingNavigationBarDefaults.TextFontSize,
                        fontWeight = FontWeight.Bold, // Always bold for layout
                    )
                    // Visible text
                    Text(
                        text = label,
                        color = tint,
                        textAlign = TextAlign.Center,
                        fontSize = FloatingNavigationBarDefaults.TextFontSize,
                        fontWeight = fontWeight,
                    )
                }
            }

            FloatingNavigationBarDisplayMode.IconOnly -> {
                Image(
                    modifier = Modifier.padding(vertical = FloatingNavigationBarDefaults.IconOnlyPadding, horizontal = FloatingNavigationBarDefaults.IconOnlyPadding).size(FloatingNavigationBarDefaults.IconOnlySize),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
            }
        }
    }
}

/** Contains default values used by [NavigationBar] and [NavigationBarItem]. */
object NavigationBarDefaults {
    /** The default item height on non-iOS platforms. */
    val ItemHeight = 64.dp

    /** The default item height on iOS. */
    val ItemHeightIOS = 48.dp

    /** The default icon size. */
    val IconSize = 26.dp

    /** The default label font size. */
    val LabelFontSize = 12.sp

    /** The default top padding for the icon. */
    val IconTopPadding = 8.dp

    /** The default bottom padding for the label. */
    val BottomPadding = 8.dp

    /** The alpha value for the selected item when pressed. */
    val SelectedPressedAlpha = 0.5f

    /** The alpha value for an unselected item when pressed. */
    val UnselectedPressedAlpha = 0.6f

    /** The alpha value for an unselected item. */
    val UnselectedAlpha = 0.4f
}

/** Contains default values used by [FloatingNavigationBar] and [FloatingNavigationBarItem]. */
object FloatingNavigationBarDefaults {
    /** The default horizontal outside padding. */
    val HorizontalOutSidePadding = 36.dp

    /** The default shadow elevation. */
    val ShadowElevation = 1.dp

    /** The default horizontal padding inside the bar. */
    val HorizontalPadding = 12.dp

    /** The default spacing between items. */
    val ItemSpacing = 12.dp

    /** The icon size in [FloatingNavigationBarDisplayMode.IconAndText] mode. */
    val IconSize = 24.dp

    /** The label font size in [FloatingNavigationBarDisplayMode.IconAndText] mode. */
    val LabelFontSize = 12.sp

    /** The vertical padding in [FloatingNavigationBarDisplayMode.IconAndText] mode. */
    val VerticalPadding = 6.dp

    /** The vertical padding in [FloatingNavigationBarDisplayMode.TextOnly] mode. */
    val TextVerticalPadding = 16.dp

    /** The horizontal padding in [FloatingNavigationBarDisplayMode.TextOnly] mode. */
    val TextHorizontalPadding = 2.dp

    /** The font size in [FloatingNavigationBarDisplayMode.TextOnly] mode. */
    val TextFontSize = 14.sp

    /** The icon size in [FloatingNavigationBarDisplayMode.IconOnly] mode. */
    val IconOnlySize = 28.dp

    /** The padding in [FloatingNavigationBarDisplayMode.IconOnly] mode. */
    val IconOnlyPadding = 10.dp

    /** The alpha value for the selected item when pressed. */
    val SelectedPressedAlpha = 0.5f

    /** The alpha value for an unselected item when pressed. */
    val UnselectedPressedAlpha = 0.6f

    /** The alpha value for an unselected item. */
    val UnselectedAlpha = 0.4f
}

/**
 * Defines the display mode for items in a NavigationBar.
 *
 * This controls whether to show both icon and text, icon only, or text only.
 */
enum class NavigationBarDisplayMode {
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
 * A composition local to control the display mode for items in a NavigationBar.
 */
val LocalNavigationBarDisplayMode = compositionLocalOf { NavigationBarDisplayMode.IconAndText }

/**
 * Defines the display mode for items in a [FloatingNavigationBar].
 *
 * This controls whether to show both icon and text, icon only, or text only.
 */
enum class FloatingNavigationBarDisplayMode {
    /** Show both icon and text. */
    IconAndText,

    /** Show icon only. */
    IconOnly,

    /** Show text only. */
    TextOnly,
}

/**
 * A composition local to control the display mode for items in a [FloatingNavigationBar].
 */
val LocalFloatingNavigationBarDisplayMode = compositionLocalOf { FloatingNavigationBarDisplayMode.IconOnly }

/**
 * The data class for [NavigationBar].
 *
 * @param label The label of the item.
 * @param icon The icon of the item.
 */
@Immutable
data class NavigationItem(
    val label: String,
    val icon: ImageVector,
)
