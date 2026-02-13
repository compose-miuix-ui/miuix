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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.shapes.RoundedRectangle
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
    mode: NavigationDisplayMode = NavigationDisplayMode.IconAndText,
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
            CompositionLocalProvider(LocalNavigationDisplayMode provides mode) {
                content()
            }
        }
        if (defaultWindowInsetsPadding) {
            val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues()
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(navigationBarsPadding.calculateBottomPadding() + animatedCaptionBarHeight)
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
    val itemHeight = if (platform != Platform.IOS) 64.dp else 48.dp
    var isPressed by remember { mutableStateOf(false) }

    val onSurfaceContainerColor = MiuixTheme.colorScheme.onSurfaceContainer
    val onSurfaceContainerVariantColor = MiuixTheme.colorScheme.onSurfaceContainerVariant

    val tint = when {
        isPressed -> if (selected) {
            onSurfaceContainerColor.copy(alpha = 0.5f)
        } else {
            onSurfaceContainerVariantColor.copy(alpha = 0.5f)
        }

        selected -> onSurfaceContainerColor

        else -> onSurfaceContainerVariantColor
    }
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val mode = LocalNavigationDisplayMode.current

    Column(
        modifier = modifier
            .height(itemHeight)
            .weight(1f)
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
            },
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = if (mode == NavigationDisplayMode.IconAndText || mode == NavigationDisplayMode.IconWithSelectedLabel) Arrangement.Top else Arrangement.Center,
    ) {
        when (mode) {
            NavigationDisplayMode.IconAndText -> {
                Image(
                    modifier = Modifier.padding(top = 8.dp).size(26.dp),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                Text(
                    modifier = Modifier.padding(bottom = if (platform != Platform.IOS) 8.dp else 0.dp),
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = fontWeight,
                )
            }

            NavigationDisplayMode.IconWithSelectedLabel -> {
                val defaultPadding = (itemHeight - 26.dp) / 2
                val iconTopPadding by animateDpAsState(
                    targetValue = if (selected) 8.dp else defaultPadding,
                    animationSpec = tween(durationMillis = 300),
                    label = "iconTopPadding",
                )
                val textAlpha by animateFloatAsState(
                    targetValue = if (selected) 1f else 0f,
                    animationSpec = tween(durationMillis = 300),
                    label = "textAlpha",
                )

                Image(
                    modifier = Modifier.padding(top = iconTopPadding).size(26.dp),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = if (platform != Platform.IOS) 8.dp else 0.dp)
                        .alpha(textAlpha),
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = fontWeight,
                )
            }

            NavigationDisplayMode.TextOnly -> {
                Text(
                    modifier = Modifier.padding(vertical = if (platform != Platform.IOS) 8.dp else 0.dp),
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = fontWeight,
                )
            }

            else -> {
                Image(
                    modifier = Modifier.size(26.dp),
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
    horizontalOutSidePadding: Dp = 36.dp,
    shadowElevation: Dp = 1.dp,
    showDivider: Boolean = false,
    defaultWindowInsetsPadding: Boolean = true,
    mode: NavigationDisplayMode = NavigationDisplayMode.IconOnly,
    content: @Composable RowScope.() -> Unit,
) {
    val density = LocalDensity.current

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
                                shape = RoundedRectangle(cornerRadius),
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
                            shape = RoundedRectangle(cornerRadius),
                            clip = cornerRadius > 0.dp,
                        )
                    } else if (cornerRadius > 0.dp) {
                        Modifier.clip(RoundedRectangle(cornerRadius))
                    } else {
                        Modifier
                    },
                )
                .background(color)
                .then(modifier)
                .padding(horizontal = 12.dp)
                .align(horizontalAlignment)
                .pointerInput(Unit) {
                    detectTapGestures { /* Consume click */ }
                },
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CompositionLocalProvider(LocalNavigationDisplayMode provides mode) {
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

    val onSurfaceContainerColor = MiuixTheme.colorScheme.onSurfaceContainer
    val onSurfaceContainerVariantColor = MiuixTheme.colorScheme.onSurfaceContainerVariant

    val tint = when {
        isPressed -> if (selected) {
            onSurfaceContainerColor.copy(alpha = 0.6f)
        } else {
            onSurfaceContainerVariantColor.copy(alpha = 0.6f)
        }

        selected -> onSurfaceContainerColor

        else -> onSurfaceContainerVariantColor
    }
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val mode = LocalNavigationDisplayMode.current

    Column(
        modifier = modifier
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
            },
        horizontalAlignment = CenterHorizontally,
    ) {
        when (mode) {
            NavigationDisplayMode.IconAndText -> {
                Image(
                    modifier = Modifier.padding(top = 6.dp).size(24.dp),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                Box(
                    modifier = Modifier.padding(bottom = 6.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    // Invisible text for layout calculation (always bold)
                    Text(
                        modifier = Modifier.alpha(0f),
                        text = label,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold, // Always bold for layout
                    )
                    // Visible text
                    Text(
                        text = label,
                        color = tint,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = fontWeight,
                    )
                }
            }

            NavigationDisplayMode.IconWithSelectedLabel -> {
                if (selected) {
                    Image(
                        modifier = Modifier.padding(top = 6.dp).size(24.dp),
                        imageVector = icon,
                        contentDescription = label,
                        colorFilter = ColorFilter.tint(tint),
                    )
                    Box(
                        modifier = Modifier.padding(bottom = 6.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        // Invisible text for layout calculation (always bold)
                        Text(
                            modifier = Modifier.alpha(0f),
                            text = label,
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold, // Always bold for layout
                        )
                        // Visible text
                        Text(
                            text = label,
                            color = tint,
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            fontWeight = fontWeight,
                        )
                    }
                } else {
                    Image(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp).size(28.dp),
                        imageVector = icon,
                        contentDescription = label,
                        colorFilter = ColorFilter.tint(tint),
                    )
                }
            }

            NavigationDisplayMode.TextOnly -> {
                Box(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 2.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    // Invisible text for layout calculation
                    Text(
                        modifier = Modifier.alpha(0f),
                        text = label,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold, // Always bold for layout
                    )
                    // Visible text
                    Text(
                        text = label,
                        color = tint,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = fontWeight,
                    )
                }
            }

            else -> {
                Image(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp).size(28.dp),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
            }
        }
    }
}

/**
 * Defines the display mode for items in a NavigationBar.
 *
 * This controls whether to show both icon and text, icon only, or text only.
 */
enum class NavigationDisplayMode {
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
val LocalNavigationDisplayMode = compositionLocalOf { NavigationDisplayMode.IconAndText }

/**
 * The data class for [NavigationBar].
 *
 * @param label The label of the item.
 * @param icon The icon of the item.
 */
data class NavigationItem(
    val label: String,
    val icon: ImageVector,
)
