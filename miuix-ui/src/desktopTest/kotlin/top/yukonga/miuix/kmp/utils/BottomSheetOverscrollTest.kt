// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.channels.Channel
import top.yukonga.miuix.kmp.layout.BottomSheetContent
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class BottomSheetOverscrollTest {
    @Test
    fun downwardEdgeDragDismissesSheetInsteadOfStretchingList() = assertDrag()

    @Test
    fun nonDismissibleSheetKeepsListOverscroll() = assertDrag(allowDismiss = false)

    @Test
    fun disabledNestedScrollKeepsListOverscroll() = assertDrag(enableNestedScroll = false)

    @Test
    fun dragStartingInsideListDoesNotBecomeSheetDismissal() = assertDrag(initialIndex = 2)

    @Test
    fun upwardBottomEdgeDragKeepsListOverscroll() = assertDrag(initialIndex = 9, upward = true)

    private fun assertDrag(
        allowDismiss: Boolean = true,
        enableNestedScroll: Boolean = true,
        initialIndex: Int = 0,
        upward: Boolean = false,
    ) = runComposeUiTest {
        val sheetOffset = Animatable(0f)
        var listOffset = 0f
        var dismissals = 0
        val shouldDismiss = allowDismiss && enableNestedScroll && initialIndex == 0 && !upward
        setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                MiuixTheme {
                    val channel = remember { Channel<Float>(Channel.CONFLATED) }
                    LaunchedEffect(channel) {
                        for (offset in channel) sheetOffset.snapTo(offset)
                    }
                    Box(Modifier.size(360.dp, 600.dp)) {
                        BottomSheetContent(
                            title = null,
                            backgroundColor = Color.White,
                            cornerRadius = 16.dp,
                            sheetMaxWidth = 360.dp,
                            outsideMargin = DpSize.Zero,
                            insideMargin = DpSize.Zero,
                            defaultWindowInsetsPadding = false,
                            dragHandleColor = Color.Gray,
                            allowDismiss = allowDismiss,
                            sheetHeightPx = remember { mutableIntStateOf(0) },
                            dragOffsetY = sheetOffset,
                            dimAlpha = remember { mutableFloatStateOf(1f) },
                            dragSnapChannel = channel,
                            onDismissRequest = { dismissals++ },
                            modifier = Modifier.graphicsLayer { translationY = sheetOffset.value },
                            enableNestedScroll = enableNestedScroll,
                            topInset = 0.dp,
                        ) {
                            LazyColumn(
                                state = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex),
                                modifier = Modifier.height(200.dp).testTag("sheet-list")
                                    .overScrollVertical(onOffsetChanged = { listOffset = it }),
                                overscrollEffect = null,
                            ) {
                                items(10) { Box(Modifier.size(300.dp, 40.dp)) }
                            }
                        }
                    }
                }
            }
        }
        onNodeWithTag("sheet-list").performTouchInput {
            down(Offset(centerX, if (upward) height * 0.9f else height * 0.1f))
        }
        repeat(12) {
            onRoot().performTouchInput { moveBy(Offset(0f, if (upward) -35f else 35f), delayMillis = 32) }
            waitForIdle()
        }
        runOnIdle {
            if (shouldDismiss) {
                assertTrue(sheetOffset.value > 150f, "The sheet owns the downward edge drag: ${sheetOffset.value}")
                assertEquals(0f, listOffset, 0.01f)
            } else {
                assertEquals(0f, sheetOffset.value, 0.01f)
                assertTrue(if (upward) listOffset < -1f else listOffset > 1f, "An ineligible sheet leaves overscroll to its list")
            }
        }
        onNodeWithTag("sheet-list").performTouchInput { up() }
        waitForIdle()
        assertEquals(if (shouldDismiss) 1 else 0, dismissals)
    }
}
