// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import top.yukonga.miuix.kmp.utils.RemovePlatformDialogDefaultEffects
import top.yukonga.miuix.kmp.utils.WindowNavigationEventScope
import top.yukonga.miuix.kmp.utils.platformDialogProperties

/** Geometry supplied by a custom window for the layers that render outside its content tree. */
@Stable
class WindowComponentFrame(
    val insets: WindowInsets,
    val topCornerRadius: Dp = 0.dp,
    val bottomCornerRadius: Dp = 0.dp,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WindowComponentFrame) return false
        return insets == other.insets &&
            topCornerRadius == other.topCornerRadius &&
            bottomCornerRadius == other.bottomCornerRadius
    }

    override fun hashCode(): Int {
        var result = insets.hashCode()
        result = 31 * result + topCornerRadius.hashCode()
        result = 31 * result + bottomCornerRadius.hashCode()
        return result
    }
}

/** The window component viewport. Ordinary platform windows need no override. */
val LocalWindowComponentFrame = compositionLocalOf<WindowComponentFrame?> { null }

@Composable
@ReadOnlyComposable
internal fun windowComponentBounds(): IntRect? {
    val insets = LocalWindowComponentFrame.current?.insets ?: return null
    return windowInsetsBounds(
        insets = insets,
        containerSize = LocalWindowInfo.current.containerSize,
        density = LocalDensity.current,
        layoutDirection = LocalLayoutDirection.current,
    )
}

internal fun windowInsetsBounds(
    insets: WindowInsets,
    containerSize: IntSize,
    density: Density,
    layoutDirection: LayoutDirection,
): IntRect {
    val width = containerSize.width.coerceAtLeast(0)
    val height = containerSize.height.coerceAtLeast(0)
    val left = insets.getLeft(density, layoutDirection).coerceIn(0, width)
    val top = insets.getTop(density).coerceIn(0, height)
    return IntRect(
        left = left,
        top = top,
        right = (width - insets.getRight(density, layoutDirection)).coerceIn(left, width),
        bottom = (height - insets.getBottom(density)).coerceIn(top, height),
    )
}

internal fun intersectIntRect(a: IntRect, b: IntRect): IntRect {
    val left = maxOf(a.left, b.left)
    val top = maxOf(a.top, b.top)
    return IntRect(
        left = left,
        top = top,
        right = minOf(a.right, b.right).coerceAtLeast(left),
        bottom = minOf(a.bottom, b.bottom).coerceAtLeast(top),
    )
}

@Composable
internal fun WindowComponentHost(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    val frame = LocalWindowComponentFrame.current
    val currentOnDismiss = rememberUpdatedState(onDismissRequest)
    Dialog(onDismissRequest = onDismissRequest, properties = platformDialogProperties()) {
        RemovePlatformDialogDefaultEffects()
        WindowNavigationEventScope {
            val modifier = if (frame == null) {
                Modifier
            } else {
                val shape = remember(frame.topCornerRadius, frame.bottomCornerRadius) {
                    RoundedCornerShape(
                        topStart = frame.topCornerRadius,
                        topEnd = frame.topCornerRadius,
                        bottomStart = frame.bottomCornerRadius,
                        bottomEnd = frame.bottomCornerRadius,
                    )
                }
                Modifier
                    .fillMaxSize()
                    // Catch taps in the frame padding; content handles and consumes its own taps.
                    .pointerInput(Unit) { detectTapGestures { currentOnDismiss.value() } }
                    .padding(frame.insets.asPaddingValues())
                    .clip(shape)
            }
            Box(modifier = modifier, propagateMinConstraints = true) { content() }
        }
    }
}
