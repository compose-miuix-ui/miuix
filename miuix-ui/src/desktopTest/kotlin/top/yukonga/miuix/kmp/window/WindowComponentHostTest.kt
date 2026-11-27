// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.MutableWindowInsets
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.window.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TooltipBox
import top.yukonga.miuix.kmp.basic.rememberTooltipState
import top.yukonga.miuix.kmp.basic.resolveLocalTransformOrigin
import top.yukonga.miuix.kmp.basic.resolvePopupLayoutPosition
import top.yukonga.miuix.kmp.layout.computeSecondaryRect
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

    @Test
    fun overlayListPopupScrollsWithinOffsetNestedScaffoldAndWindowSafeArea() = runComposeUiTest {
        val frameBottomInset = 120
        val hostTop = 40
        var containerHeight = 0
        setContent {
            containerHeight = LocalWindowInfo.current.containerSize.height
            MiuixTheme {
                CompositionLocalProvider(
                    LocalDensity provides Density(1f),
                    LocalWindowComponentFrame provides WindowComponentFrame(WindowInsets(bottom = frameBottomInset)),
                ) {
                    Scaffold(contentWindowInsets = WindowInsets(0)) {
                        Scaffold(
                            modifier = Modifier.offset(y = hostTop.dp).size(260.dp, 400.dp),
                            contentWindowInsets = WindowInsets(0),
                        ) {
                            Box(Modifier.size(24.dp)) {
                                OverlayListPopup(
                                    show = true,
                                    maxHeight = null,
                                    renderInRootScaffold = false,
                                ) {
                                    ListPopupColumn {
                                        repeat(20) { idx ->
                                            Box(Modifier.size(200.dp, 44.dp)) {
                                                Text("Entry $idx")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        waitForIdle()
        val viewport = onNode(hasScrollAction()).fetchSemanticsNode().boundsInRoot
        val expectedTop = hostTop + 8f
        val expectedBottom = minOf(hostTop + 400f, containerHeight - frameBottomInset.toFloat()) - 8f
        assertTrue(viewport.top >= expectedTop - 1f, "Expected top >= $expectedTop, was ${viewport.top}")
        assertTrue(viewport.bottom <= expectedBottom + 1f, "Expected bottom <= $expectedBottom, was ${viewport.bottom}")
        onNodeWithText("Entry 19").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun overlayListPopupExplicitMaxHeightClampsToSafeViewport() = runComposeUiTest {
        var popupHeight = 0
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
        setContent {
            MiuixTheme {
                CompositionLocalProvider(LocalDensity provides Density(1f)) {
                    Scaffold(contentWindowInsets = WindowInsets(0)) {
                        Scaffold(
                            modifier = Modifier.size(240.dp, 100.dp),
                            contentWindowInsets = WindowInsets(0),
                        ) {
                            Box(Modifier.size(24.dp)) {
                                OverlayListPopup(
                                    show = true,
                                    popupPositionProvider = provider,
                                    maxHeight = 400.dp,
                                    renderInRootScaffold = false,
                                ) {
                                    Box(Modifier.size(200.dp, 300.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        runOnIdle {
            assertEquals(100, popupHeight)
        }
    }

    @Test
    fun dropdownPositionProviderClampsSymmetricallyAndNeverPushesAboveTop() {
        val provider = ListPopupDefaults.dropdownPositionProvider(verticalMargin = 8.dp, horizontalMargin = 12.dp)
        val window = IntRect(left = 20, top = 40, right = 320, bottom = 240)
        val margin = IntRect(left = 12, top = 8, right = 12, bottom = 8)

        // Left clamping respects left margin symmetrically with right margin.
        val leftOffset = provider.calculatePosition(
            anchorBounds = IntRect(0, 60, 40, 80),
            windowBounds = window,
            layoutDirection = LayoutDirection.Ltr,
            popupContentSize = IntSize(120, 80),
            popupMargin = margin,
            alignment = ListPositionProvider.Align.End,
        )
        assertEquals(32, leftOffset.x)

        // Oversized height stays pinned at windowBounds.top instead of pushing into status bar.
        val oversizedOffset = provider.calculatePosition(
            anchorBounds = IntRect(100, 120, 180, 140),
            windowBounds = window,
            layoutDirection = LayoutDirection.Ltr,
            popupContentSize = IntSize(120, 260),
            popupMargin = margin,
            alignment = ListPositionProvider.Align.Start,
        )
        assertEquals(window.top, oversizedOffset.y)
    }

    @Test
    fun computeSecondaryRectRespectsPopupMargins() {
        val window = IntRect(left = 10, top = 20, right = 310, bottom = 220)
        val margin = IntRect(left = 12, top = 8, right = 12, bottom = 8)
        val bottomRightRect = computeSecondaryRect(
            anchor = IntRect(left = 250, top = 190, right = 305, bottom = 218),
            secondarySize = IntSize(100, 100),
            windowBounds = window,
            layoutDirection = LayoutDirection.Ltr,
            popupMargin = margin,
        )
        assertEquals(window.right - margin.right, bottomRightRect.right)
        assertEquals(window.bottom - margin.bottom, bottomRightRect.bottom)

        val topLeftRect = computeSecondaryRect(
            anchor = IntRect(left = 12, top = 22, right = 60, bottom = 50),
            secondarySize = IntSize(100, 80),
            windowBounds = window,
            layoutDirection = LayoutDirection.Ltr,
            popupMargin = margin,
        )
        assertEquals(window.left + margin.left, topLeftRect.left)
        assertEquals(window.top + margin.top, topLeftRect.top)
    }

    @Test
    fun resolvePopupLayoutPositionTracksContinuousAnchorPivot() {
        // Strictly below anchor: top edge pivot.
        val below = resolvePopupLayoutPosition(
            parentBounds = IntRect(280, 80, 360, 120),
            popupContentSize = IntSize(200, 240),
            calculatedOffset = IntOffset(160, 128),
        )
        assertEquals(0f, below.pivotFractionY, 0.001f)
        assertEquals(1f, below.pivotFractionX, 0.001f)
        assertTrue(below.showBelow)

        // Strictly above anchor: bottom edge pivot.
        val above = resolvePopupLayoutPosition(
            parentBounds = IntRect(280, 480, 360, 520),
            popupContentSize = IntSize(200, 240),
            calculatedOffset = IntOffset(160, 232),
        )
        assertEquals(1f, above.pivotFractionY, 0.001f)
        assertTrue(above.showAbove)

        // Landscape clamped popup overlapping anchor at 25% height: continuous pivot on anchor row.
        val upperOverlap = resolvePopupLayoutPosition(
            parentBounds = IntRect(280, 120, 360, 160),
            popupContentSize = IntSize(200, 400),
            calculatedOffset = IntOffset(160, 40),
        )
        assertEquals(0.25f, upperOverlap.pivotFractionY, 0.001f)
        assertEquals(1f, upperOverlap.pivotFractionX, 0.001f)
        assertEquals(0.25f, resolveLocalTransformOrigin(upperOverlap).pivotFractionY, 0.001f)

        // Landscape clamped popup overlapping anchor at 80% height.
        val lowerOverlap = resolvePopupLayoutPosition(
            parentBounds = IntRect(280, 340, 360, 380),
            popupContentSize = IntSize(200, 400),
            calculatedOffset = IntOffset(160, 40),
        )
        assertEquals(0.80f, lowerOverlap.pivotFractionY, 0.001f)
        assertEquals(0.80f, resolveLocalTransformOrigin(lowerOverlap).pivotFractionY, 0.001f)

        // Horizontally straddled narrow anchor at 40% width: continuous X pivot.
        val centeredAnchor = resolvePopupLayoutPosition(
            parentBounds = IntRect(160, 120, 200, 160),
            popupContentSize = IntSize(200, 400),
            calculatedOffset = IntOffset(100, 40),
        )
        assertEquals(0.40f, centeredAnchor.pivotFractionX, 0.001f)
        assertEquals(0.40f, resolveLocalTransformOrigin(centeredAnchor).pivotFractionX, 0.001f)
    }
}
