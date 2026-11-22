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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntRect
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
internal fun windowComponentBounds(): IntRect? {
    val insets = LocalWindowComponentFrame.current?.insets ?: return null
    val density = LocalDensity.current
    val direction = LocalLayoutDirection.current
    val size = LocalWindowInfo.current.containerSize
    val left = insets.getLeft(density, direction).coerceIn(0, size.width.coerceAtLeast(0))
    val top = insets.getTop(density).coerceIn(0, size.height.coerceAtLeast(0))
    return IntRect(
        left = left,
        top = top,
        right = (size.width - insets.getRight(density, direction)).coerceAtLeast(left),
        bottom = (size.height - insets.getBottom(density)).coerceAtLeast(top),
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
