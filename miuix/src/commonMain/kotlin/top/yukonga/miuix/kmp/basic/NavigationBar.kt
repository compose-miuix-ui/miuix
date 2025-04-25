package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.Platform
import top.yukonga.miuix.kmp.utils.SmoothRoundedCornerShape
import top.yukonga.miuix.kmp.utils.platform

/**
 * A [NavigationBar] that with 2 to 5 items.
 *
 * @param items The items of the [NavigationBar].
 * @param selected The selected index of the [NavigationBar].
 * @param onClick The callback when the item of the [NavigationBar] is clicked.
 * @param modifier The modifier to be applied to the [NavigationBar].
 * @param color The color of the [NavigationBar].
 * @param showDivider Whether to show the divider line between the [NavigationBar] and the content.
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [NavigationBar].
 */
@Composable
fun NavigationBar(
    items: List<NavigationItem>,
    selected: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MiuixTheme.colorScheme.surfaceContainer,
    showDivider: Boolean = true,
    defaultWindowInsetsPadding: Boolean = true
) {
    require(items.size in 2..5) { "BottomBar must have between 2 and 5 items" }
    val captionBarBottomPadding by rememberUpdatedState(
        WindowInsets.captionBar.only(WindowInsetsSides.Bottom).asPaddingValues().calculateBottomPadding()
    )
    val animatedCaptionBarHeight by animateDpAsState(
        targetValue = if (captionBarBottomPadding > 0.dp) captionBarBottomPadding else 0.dp,
        animationSpec = tween(durationMillis = 300)
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color)
    ) {
        if (showDivider) {
            HorizontalDivider()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selected == index
                var isPressed by remember { mutableStateOf(false) }
                val tint by animateColorAsState(
                    targetValue = when {
                        isPressed -> if (isSelected) {
                            MiuixTheme.colorScheme.onSurfaceContainer.copy(alpha = 0.6f)
                        } else {
                            MiuixTheme.colorScheme.onSurfaceContainerVariant.copy(alpha = 0.6f)
                        }

                        isSelected -> MiuixTheme.colorScheme.onSurfaceContainer
                        else -> MiuixTheme.colorScheme.onSurfaceContainerVariant
                    }
                )
                val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                Column(
                    modifier = Modifier
                        .height(if (platform() != Platform.IOS) 64.dp else 48.dp)
                        .weight(1f / items.size)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    isPressed = true
                                    tryAwaitRelease()
                                    isPressed = false
                                },
                                onTap = { onClick(index) }
                            )
                        },
                    horizontalAlignment = CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(32.dp).padding(top = 6.dp),
                        imageVector = item.icon,
                        contentDescription = item.label,
                        colorFilter = ColorFilter.tint(tint)
                    )
                    Text(
                        modifier = Modifier.padding(bottom = if (platform() != Platform.IOS) 12.dp else 0.dp),
                        text = item.label,
                        color = tint,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = fontWeight
                    )
                }
            }
        }
        if (defaultWindowInsetsPadding) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + animatedCaptionBarHeight)
                    .pointerInput(Unit) { detectTapGestures { /* Do nothing to consume the click */ } }
            )
        }
    }
}

/**
 * A floating navigation bar that supports 2 to 5 items.
 *
 * @param items The list of items to display in the [FloatingNavigationBar].
 * @param selected The index of the currently selected item in the [FloatingNavigationBar].
 * @param onClick A callback function that is invoked when an item is clicked. It receives the selected item's index.
 * @param modifier A [Modifier] to be applied to the [FloatingNavigationBar] for additional customization.
 * @param color The background color of the [FloatingNavigationBar].
 * @param cornerRadius The corner radius of the [FloatingNavigationBar], used for rounded corners.
 * @param horizontalAlignment The alignment of the [FloatingNavigationBar] within its parent, typically used to center it horizontally.
 * @param horizontalOutSidePadding The horizontal padding to be applied outside the [FloatingNavigationBar].
 * @param shadowElevation The shadow elevation of the [FloatingNavigationBar].
 * @param showDivider Whether to show the divider line around the [FloatingNavigationBar].
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [FloatingNavigationBar].
 * @param mode The mode for displaying items in the [FloatingNavigationBar]. It can show icons, text or both.
 */
@Composable
fun FloatingNavigationBar(
    items: List<NavigationItem>,
    selected: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MiuixTheme.colorScheme.surfaceContainer,
    cornerRadius: Dp = FloatingToolbarDefaults.CornerRadius,
    horizontalAlignment: Alignment.Horizontal = CenterHorizontally,
    horizontalOutSidePadding: Dp = 36.dp,
    shadowElevation: Dp = 1.dp,
    showDivider: Boolean = false,
    defaultWindowInsetsPadding: Boolean = true,
    mode: FloatingNavigationBarMode = FloatingNavigationBarMode.IconOnly,
) {
    require(items.size in 2..5) { "FloatingNavigationBar must have between 2 and 5 items" }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (horizontalAlignment == Alignment.Start) horizontalOutSidePadding else 0.dp,
                end = if (horizontalAlignment == Alignment.End) horizontalOutSidePadding else 0.dp,
            )
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = if (platform() != Platform.IOS) 36.dp else 30.dp)
                .then(
                    if (defaultWindowInsetsPadding) {
                        Modifier
                            .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Bottom))
                            .windowInsetsPadding(WindowInsets.captionBar.only(WindowInsetsSides.Bottom))
                            .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal))
                            .windowInsetsPadding(WindowInsets.navigationBars)
                    } else Modifier
                )
                .then(
                    if (showDivider) {
                        Modifier
                            .background(
                                color = MiuixTheme.colorScheme.dividerLine,
                                shape = SmoothRoundedCornerShape(cornerRadius)
                            )
                            .padding(0.75.dp)
                    } else Modifier
                )
                .then(
                    if (shadowElevation > 0.dp) {
                        Modifier.graphicsLayer(
                            shadowElevation = with(LocalDensity.current) { shadowElevation.toPx() },
                            shape = SmoothRoundedCornerShape(cornerRadius),
                            clip = cornerRadius > 0.dp
                        )
                    } else if (cornerRadius > 0.dp) {
                        Modifier.clip(SmoothRoundedCornerShape(cornerRadius))
                    } else {
                        Modifier
                    }
                )
                .background(color)
                .then(modifier)
                .padding(horizontal = 12.dp)
                .align(horizontalAlignment)
                .pointerInput(Unit) { detectTapGestures { /* Do nothing to consume the click */ } },
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selected == index
                var isPressed by remember { mutableStateOf(false) }
                val tint by animateColorAsState(
                    targetValue = when {
                        isPressed -> if (isSelected) {
                            MiuixTheme.colorScheme.onSurfaceContainer.copy(alpha = 0.6f)
                        } else {
                            MiuixTheme.colorScheme.onSurfaceContainerVariant.copy(alpha = 0.6f)
                        }

                        isSelected -> MiuixTheme.colorScheme.onSurfaceContainer
                        else -> MiuixTheme.colorScheme.onSurfaceContainerVariant
                    }
                )
                val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                Column(
                    modifier = Modifier
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    isPressed = true
                                    tryAwaitRelease()
                                    isPressed = false
                                },
                                onTap = { onClick(index) }
                            )
                        },
                    horizontalAlignment = CenterHorizontally
                ) {
                    if (mode == FloatingNavigationBarMode.IconAndText) {
                        Image(
                            modifier = Modifier.padding(top = 6.dp).size(24.dp),
                            imageVector = item.icon,
                            contentDescription = item.label,
                            colorFilter = ColorFilter.tint(tint)
                        )
                        Box(
                            modifier = Modifier.padding(bottom = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            // Invisible text for layout calculation (always bold)
                            Text(
                                modifier = Modifier.alpha(0f),
                                text = item.label,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold // Always bold for layout
                            )
                            // Visible text
                            Text(
                                text = item.label,
                                color = tint,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = fontWeight
                            )
                        }
                    } else if (mode == FloatingNavigationBarMode.TextOnly) {
                        Box(
                            modifier = Modifier.padding(vertical = 16.dp, horizontal = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            // Invisible text for layout calculation
                            Text(
                                modifier = Modifier.alpha(0f),
                                text = item.label,
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold // Always bold for layout
                            )
                            // Visible text
                            Text(
                                text = item.label,
                                color = tint,
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                fontWeight = fontWeight
                            )
                        }
                    } else {
                        Image(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp).size(28.dp),
                            imageVector = item.icon,
                            contentDescription = item.label,
                            colorFilter = ColorFilter.tint(tint)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Defines the display mode for items in a FloatingNavigationBar.
 *
 * This controls whether to show both icon and text, icon only, or text only.
 */
enum class FloatingNavigationBarMode {
    /** Show both icon and text. */
    IconAndText,

    /** Show icon only. */
    IconOnly,

    /** Show text only. */
    TextOnly
}

/**
 * The data class for [NavigationBar].
 *
 * @param label The label of the item.
 * @param icon The icon of the item.
 */
data class NavigationItem(
    val label: String,
    val icon: ImageVector
)
