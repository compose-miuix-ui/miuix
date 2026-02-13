// Copyright 2026, compose-miuix-ui contributors
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
    minWidth: Dp = 80.dp,
    mode: NavigationDisplayMode = NavigationDisplayMode.IconAndText,
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
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            if (header != null) {
                header()
                Spacer(modifier = Modifier.height(24.dp))
            }
            CompositionLocalProvider(LocalNavigationDisplayMode provides mode) {
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
            .padding(vertical = 12.dp)
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (mode) {
            NavigationDisplayMode.IconAndText -> {
                Image(
                    modifier = Modifier.size(28.dp),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = fontWeight,
                )
            }

            NavigationDisplayMode.IconWithSelectedLabel -> {
                Image(
                    modifier = Modifier.size(28.dp),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
                if (selected) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = label,
                        color = tint,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = fontWeight,
                    )
                }
            }

            NavigationDisplayMode.TextOnly -> {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = label,
                    color = tint,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = fontWeight,
                )
            }

            else -> {
                Image(
                    modifier = Modifier.size(28.dp),
                    imageVector = icon,
                    contentDescription = label,
                    colorFilter = ColorFilter.tint(tint),
                )
            }
        }
    }
}
