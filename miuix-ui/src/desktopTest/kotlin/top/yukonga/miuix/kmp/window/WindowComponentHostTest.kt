// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.MutableWindowInsets
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.window.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TooltipBox
import top.yukonga.miuix.kmp.basic.rememberTooltipState
import top.yukonga.miuix.kmp.layout.popupHeightLimit
import top.yukonga.miuix.kmp.overlay.OverlayCascadingListPopup
import top.yukonga.miuix.kmp.overlay.OverlayListPopup
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import top.yukonga.miuix.kmp.basic.PopupPositionProvider as ListPositionProvider

@OptIn(ExperimentalLayoutApi::class, ExperimentalTestApi::class)
class WindowComponentHostTest {
    @Test
    fun dialogLayerFollowsCustomFrameInsetsWhenTheyChange() = runComposeUiTest {
        val insets = MutableWindowInsets(WindowInsets(left = 8, top = 24, right = 12, bottom = 16))
        var windowSize = IntSize.Zero
        var layerBounds = IntRect.Zero
        var popupBounds: IntRect? = null

        setContent {
            windowSize = LocalWindowInfo.current.containerSize
            CompositionLocalProvider(LocalWindowComponentFrame provides WindowComponentFrame(insets)) {
                popupBounds = windowComponentBounds()
                WindowComponentHost(onDismissRequest = {}) {
                    Box(
                        Modifier.fillMaxSize().onGloballyPositioned {
                            layerBounds = IntRect(it.positionInWindow().round(), it.size)
                        },
                    )
                }
            }
        }

        runOnIdle {
            val expected = IntRect(8, 24, windowSize.width - 12, windowSize.height - 16)
            assertEquals(expected, layerBounds)
            assertEquals(expected, popupBounds)
            insets.insets = WindowInsets(0, 0, 0, 0)
        }
        runOnIdle {
            val expected = IntRect(IntOffset.Zero, windowSize)
            assertEquals(expected, layerBounds)
            assertEquals(expected, popupBounds)
        }
    }

    @Test
    fun narrowViewportTakesPriorityOverPopupHeightPreferences() {
        val margin = IntRect(0, 8, 0, 8)
        assertEquals(44, popupHeightLimit(60, margin, 60, 50, null))
        assertEquals(44, popupHeightLimit(60, margin, 60, 50, 200))
        assertEquals(0, popupHeightLimit(60, margin, 12, 50, null))
    }

    @Test
    fun captionTapCollapsesSubmenuBeforeDismissingMainMenu() = runComposeUiTest {
        var dismissals = 0
        val entries = listOf(DropdownEntry(items = listOf(DropdownItem(text = "Parent", children = listOf(DropdownItem(text = "Child"))))))
        setContent {
            MiuixTheme {
                CompositionLocalProvider(LocalWindowComponentFrame provides WindowComponentFrame(WindowInsets(top = 40))) {
                    Box(Modifier.size(40.dp)) {
                        WindowCascadingListPopup(show = true, entries = entries, onDismissRequest = { dismissals++ })
                    }
                }
            }
        }

        onNodeWithText("Parent").performClick()
        assertTrue(onAllNodesWithText("Child").fetchSemanticsNodes().isNotEmpty())
        onNode(isDialog()).performTouchInput {
            down(Offset(10f, 10f))
            up()
        }
        assertTrue(onAllNodesWithText("Child").fetchSemanticsNodes().isEmpty())
        runOnIdle { assertEquals(0, dismissals) }
        onNode(isDialog()).performTouchInput {
            down(Offset(10f, 10f))
            up()
        }
        runOnIdle { assertEquals(1, dismissals) }
    }

    @Test
    fun ordinaryOverlayKeepsExplicitHeightInNestedScaffold() = assertNestedOverlayHeight(cascading = false)

    @Test
    fun cascadingOverlayKeepsExplicitHeightInNestedScaffold() = assertNestedOverlayHeight(cascading = true)

    private fun assertNestedOverlayHeight(cascading: Boolean) = runComposeUiTest {
        var popupHeight = 0
        var hostHeight = 0
        val defaultProvider = ListPopupDefaults.DropdownPositionProvider
        val provider = object : ListPositionProvider by defaultProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowBounds: IntRect,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize,
                popupMargin: IntRect,
                alignment: ListPositionProvider.Align,
            ): IntOffset {
                popupHeight = popupContentSize.height
                return defaultProvider.calculatePosition(anchorBounds, windowBounds, layoutDirection, popupContentSize, popupMargin, alignment)
            }
        }
        val entries = listOf(DropdownEntry(items = List(4) { DropdownItem(text = "Item $it") }))
        setContent {
            MiuixTheme {
                Scaffold(contentWindowInsets = WindowInsets(0)) {
                    Scaffold(
                        modifier = Modifier.size(240.dp, 60.dp).onGloballyPositioned { hostHeight = it.size.height },
                        contentWindowInsets = WindowInsets(0),
                    ) {
                        Box(Modifier.size(24.dp)) {
                            if (cascading) {
                                OverlayCascadingListPopup(
                                    show = true,
                                    entries = entries,
                                    onDismissRequest = {},
                                    popupPositionProvider = provider,
                                    maxHeight = 60.dp,
                                    renderInRootScaffold = false,
                                )
                            } else {
                                OverlayListPopup(
                                    show = true,
                                    popupPositionProvider = provider,
                                    maxHeight = 60.dp,
                                    renderInRootScaffold = false,
                                ) {
                                    Box(Modifier.size(200.dp, 120.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        runOnIdle {
            assertTrue(hostHeight > 0)
            assertEquals(hostHeight, popupHeight)
        }
    }

    @Test
    fun tooltipStaysInsideCustomFrameEvenWithCustomPositionProvider() = runComposeUiTest {
        var tooltipBounds = IntRect.Zero
        var providerAnchor = IntRect.Zero
        var providerWindowSize = IntSize.Zero
        var actualWindowSize = IntSize.Zero
        setContent {
            actualWindowSize = LocalWindowInfo.current.containerSize
            CompositionLocalProvider(LocalWindowComponentFrame provides WindowComponentFrame(WindowInsets(left = 16, top = 40))) {
                TooltipBox(
                    positionProvider = object : PopupPositionProvider {
                        override fun calculatePosition(
                            anchorBounds: IntRect,
                            windowSize: IntSize,
                            layoutDirection: LayoutDirection,
                            popupContentSize: IntSize,
                        ): IntOffset {
                            providerAnchor = anchorBounds
                            providerWindowSize = windowSize
                            return IntOffset.Zero
                        }
                    },
                    tooltip = {
                        Box(
                            Modifier.size(48.dp).onGloballyPositioned {
                                tooltipBounds = IntRect(it.positionInWindow().round(), it.size)
                            },
                        )
                    },
                    state = rememberTooltipState(initialIsVisible = true),
                    enableUserInput = false,
                ) {
                    Box(Modifier.size(24.dp))
                }
            }
        }
        runOnIdle {
            assertEquals(IntOffset.Zero, providerAnchor.topLeft)
            assertEquals(actualWindowSize, providerWindowSize)
            assertEquals(IntOffset(16, 40), tooltipBounds.topLeft)
        }
    }

    @Test
    fun windowComponentFrameUsesStructuralEquality() {
        val insets = WindowInsets(left = 8, top = 24, right = 12, bottom = 16)
        val first = WindowComponentFrame(insets, topCornerRadius = 4.dp, bottomCornerRadius = 12.dp)
        val same = WindowComponentFrame(insets, topCornerRadius = 4.dp, bottomCornerRadius = 12.dp)
        val different = WindowComponentFrame(insets, topCornerRadius = 0.dp, bottomCornerRadius = 12.dp)
        assertEquals(first, same)
        assertEquals(first.hashCode(), same.hashCode())
        assertTrue(first != different)
    }

    @Test
    fun cascadingOverlaySubmenuRespectsNestedScaffoldMaxHeight() = runComposeUiTest {
        var hostHeight = 0f
        var lastChildClicked = false
        val children = List(10) { idx ->
            DropdownItem(
                text = "Child $idx",
                onClick = {
                    if (idx == 9) lastChildClicked = true
                },
            )
        }
        val entries = listOf(DropdownEntry(items = listOf(DropdownItem(text = "Parent", children = children))))
        setContent {
            MiuixTheme {
                Scaffold(contentWindowInsets = WindowInsets(0)) {
                    Scaffold(
                        modifier = Modifier.size(240.dp, 120.dp).onGloballyPositioned { hostHeight = it.size.height.toFloat() },
                        contentWindowInsets = WindowInsets(0),
                    ) {
                        Box(Modifier.size(24.dp)) {
                            OverlayCascadingListPopup(
                                show = true,
                                entries = entries,
                                onDismissRequest = {},
                                maxHeight = null,
                                renderInRootScaffold = false,
                            )
                        }
                    }
                }
            }
        }

        onNodeWithText("Parent").performClick()
        waitForIdle()
        val scrollableHeights = onAllNodes(hasScrollAction()).fetchSemanticsNodes().map { it.boundsInRoot.height }
        assertTrue(scrollableHeights.isNotEmpty())
        assertTrue(scrollableHeights.all { it <= hostHeight })
        onAllNodesWithText("Child 9").onFirst().performScrollTo().performClick()
        runOnIdle { assertTrue(lastChildClicked) }
    }
}
