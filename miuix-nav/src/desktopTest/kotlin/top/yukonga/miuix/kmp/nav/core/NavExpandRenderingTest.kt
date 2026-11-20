// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigationevent.DirectNavigationEventInput
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import top.yukonga.miuix.kmp.nav.transition.NavTransitionAnchor
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import top.yukonga.miuix.kmp.nav.transition.internal.LocalNavExpansionState
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpansionState
import top.yukonga.miuix.kmp.nav.transition.internal.navExpandFrame
import top.yukonga.miuix.kmp.nav.transition.navTransitionSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNotSame
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

private data object ExpandRoot : NavKey

private data class ExpandDetail(val id: Int) : NavKey

@OptIn(ExperimentalTestApi::class)
class NavExpandRenderingTest {
    @Test
    fun switchingThePolicyEnablesSourcesAndARealPressSelectsTheRightBinding() = runComposeUiTest {
        val stack = navBackStackOf(ExpandRoot)
        val detail = ExpandDetail(1)
        val preset = mutableStateOf(NavTransitions.MiuixDefault)
        var expansion: NavExpansionState? = null
        setContent {
            NavDisplay(stack, effects = NavDisplayEffects.None, transition = preset.value) {
                entry<ExpandRoot> {
                    expansion = LocalNavExpansionState.current
                    Row {
                        for ((tag, color) in listOf("left" to Color.Red, "right" to Color.Green)) {
                            Box(
                                Modifier.size(80.dp).testTag(tag)
                                    .navTransitionSource(detail, containerColor = color)
                                    .background(color)
                                    .clickable { stack.add(detail) },
                            )
                        }
                    }
                }
                entry<ExpandDetail> { Box(Modifier.fillMaxSize().background(Color.Green)) }
            }
        }
        onNodeWithTag("right").performTouchInput { click() }
        waitForIdle()
        val state = assertNotNull(expansion)
        assertEquals(detail, stack.last())
        assertNull(state.anchorState(detail).value)
        runOnIdle { stack.removeLastOrNull() }
        waitForIdle()

        runOnIdle { preset.value = NavTransitions.Expansion }
        waitForIdle()
        onNodeWithTag("right").performTouchInput { click() }
        waitForIdle()
        val anchor = assertNotNull(state.anchorState(detail).value)
        assertEquals(Color.Green, anchor.containerColor)
        assertNotNull(anchor.source?.layer)
        runOnIdle {
            stack.removeLastOrNull()
            preset.value = NavTransitions.MiuixDefault
        }
        waitForIdle()
        assertNull(state.anchorState(detail).value)
    }

    @Test
    fun coveredSourceStopsDrawingAndAReplacementForTheSameRouteDoesNotBecomeTheLandingTarget() = runComposeUiTest {
        val stack = navBackStackOf(ExpandRoot)
        val detail = ExpandDetail(1)
        val present = mutableStateOf(true)
        val color = mutableStateOf(Color.Green)
        var expansion: NavExpansionState? = null
        var detailScope: LiveNavTransitionScope? = null
        var sourceDraws = 0
        setContent {
            NavDisplay(stack, effects = NavDisplayEffects.None, transition = NavTransitions.expansion(Color.Blue)) {
                entry<ExpandRoot> {
                    expansion = LocalNavExpansionState.current
                    Box(Modifier.fillMaxSize()) {
                        if (present.value) {
                            Box(
                                Modifier.size(80.dp)
                                    .navTransitionSource(detail, containerColor = Color.Green)
                                    .drawWithContent {
                                        sourceDraws++
                                        drawRect(color.value)
                                        drawContent()
                                    },
                            )
                        } else {
                            Box(Modifier.padding(start = 120.dp).size(80.dp).navTransitionSource(detail).background(Color.Red))
                        }
                    }
                }
                entry<ExpandDetail> {
                    detailScope = LocalNavTransitionScope.current as LiveNavTransitionScope
                    Box(Modifier.fillMaxSize().background(Color.Blue))
                }
            }
        }
        waitForIdle()
        runOnIdle { stack.add(detail) }
        waitForIdle()
        val anchor = assertNotNull(assertNotNull(expansion).anchorState(detail).value)
        val original = assertNotNull(anchor.source)
        val drawsAtRest = sourceDraws
        runOnIdle { color.value = Color.Red }
        waitForIdle()
        assertEquals(drawsAtRest, sourceDraws)

        runOnIdle { present.value = false }
        waitForIdle()
        assertFalse(original.hasLivePlacement)
        assertSame(original, anchor.source)

        mainClock.autoAdvance = false
        runOnIdle { stack.removeLastOrNull() }
        mainClock.advanceTimeBy(96)
        waitForIdle()
        runOnIdle {
            val scope = assertNotNull(detailScope)
            assertTrue(scope.isReturningExpansion)
            assertNull(scope.expansionReturnBounds(anchor))
        }
        mainClock.autoAdvance = true
        waitForIdle()
        assertEquals(ExpandRoot, stack.last())
        assertNull(original.layer)
    }

    @Test
    fun recycledLazyListSourceReturnsWithTheCenteredFadeAndKeepsTheScrollPosition() = runComposeUiTest {
        val stack = navBackStackOf(ExpandRoot)
        val detail = ExpandDetail(1)
        val listState = LazyListState()
        var expansion: NavExpansionState? = null
        var detailScope: LiveNavTransitionScope? = null
        setContent {
            NavDisplay(stack, effects = NavDisplayEffects.None, transition = NavTransitions.expansion(Color.Blue)) {
                entry<ExpandRoot> {
                    expansion = LocalNavExpansionState.current
                    LazyColumn(state = listState, modifier = Modifier.fillMaxSize().background(Color.Red)) {
                        items(120) { index ->
                            if (index == 0) {
                                Box(
                                    Modifier.size(80.dp).testTag("lazy-source")
                                        .navTransitionSource(detail, containerColor = Color.Green)
                                        .background(Color.Green)
                                        .clickable { stack.add(detail) },
                                )
                            } else {
                                Spacer(Modifier.height(56.dp))
                            }
                        }
                    }
                }
                entry<ExpandDetail> {
                    detailScope = LocalNavTransitionScope.current as LiveNavTransitionScope
                    Box(Modifier.fillMaxSize().background(Color.Blue))
                }
            }
        }
        onNodeWithTag("lazy-source").performTouchInput { click() }
        waitForIdle()
        val anchor = assertNotNull(assertNotNull(expansion).anchorState(detail).value)
        val source = assertNotNull(anchor.source)
        assertTrue(source.hasLivePlacement)

        runOnIdle { listState.requestScrollToItem(100) }
        waitForIdle()
        assertEquals(100, listState.firstVisibleItemIndex)
        assertNull(source.coordinates, "LazyColumn must recycle the actual source node")

        mainClock.autoAdvance = false
        runOnIdle { stack.removeLastOrNull() }
        mainClock.advanceTimeUntil(400) { assertNotNull(detailScope).relativeDepth <= -0.5f }
        waitForIdle()
        runOnIdle {
            val scope = assertNotNull(detailScope)
            assertTrue(scope.isReturningExpansion)
            assertNull(scope.expansionReturnBounds(anchor))
        }
        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Red, pixels[1, pixels.height / 2])
        assertTrue(pixels[pixels.width / 2, pixels.height / 2].blue > 0f, "the departing page must still be drawn at the center")

        mainClock.autoAdvance = true
        waitForIdle()
        assertEquals(ExpandRoot, stack.last())
        assertEquals(100, listState.firstVisibleItemIndex)
    }

    @Test
    fun predictiveReturnKeepsCenteredFallbackWhenAClippedSourceBecomesVisibleMidGesture() = runComposeUiTest {
        val owner = object : NavigationEventDispatcherOwner {
            override val navigationEventDispatcher = NavigationEventDispatcher()
        }
        val input = DirectNavigationEventInput().also { owner.navigationEventDispatcher.addInput(it) }
        val stack = navBackStackOf(ExpandRoot)
        val detail = ExpandDetail(1)
        val offscreen = mutableStateOf(false)
        var expansion: NavExpansionState? = null
        var detailScope: LiveNavTransitionScope? = null
        setContent {
            CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner) {
                NavDisplay(stack, effects = NavDisplayEffects.None, transition = NavTransitions.expansion(Color.Blue)) {
                    entry<ExpandRoot> {
                        expansion = LocalNavExpansionState.current
                        Box(Modifier.fillMaxSize()) {
                            Box(Modifier.size(100.dp).clipToBounds()) {
                                Box(
                                    Modifier.offset(y = if (offscreen.value) 120.dp else 0.dp).size(80.dp)
                                        .navTransitionSource(detail, containerColor = Color.Green)
                                        .background(Color.Green),
                                )
                            }
                        }
                    }
                    entry<ExpandDetail> {
                        detailScope = LocalNavTransitionScope.current as LiveNavTransitionScope
                        Box(Modifier.fillMaxSize().background(Color.Blue))
                    }
                }
            }
        }
        waitForIdle()
        runOnIdle { stack.add(detail) }
        waitForIdle()
        val anchor = assertNotNull(assertNotNull(expansion).anchorState(detail).value)
        val scope = assertNotNull(detailScope)
        runOnIdle { offscreen.value = true }
        waitForIdle()
        runOnIdle {
            assertTrue(assertNotNull(anchor.source).hasLivePlacement)
            assertNotNull(scope.boundsInHost(assertNotNull(anchor.source?.coordinates)))
            assertNull(anchor.visibleSourceBounds(scope))
            input.backStarted(NavigationEvent(progress = 0f, frameTimeMillis = 1000L))
            input.backProgressed(NavigationEvent(progress = 0.5f, frameTimeMillis = 1100L))
        }
        waitForIdle()
        runOnIdle {
            assertTrue(scope.relativeDepth < 0f)
            assertNull(scope.expansionReturnBounds(anchor))
            offscreen.value = false
        }
        waitForIdle()
        runOnIdle {
            assertNotNull(anchor.visibleSourceBounds(scope))
            assertNull(scope.expansionReturnBounds(anchor))
            input.backCancelled()
        }
        waitForIdle()
        runOnIdle {
            input.backStarted(NavigationEvent(progress = 0f, frameTimeMillis = 2000L))
            input.backProgressed(NavigationEvent(progress = 0.5f, frameTimeMillis = 2100L))
        }
        waitForIdle()
        runOnIdle {
            assertNotNull(scope.expansionReturnBounds(anchor))
            input.backCompleted()
        }
        waitForIdle()
        assertEquals(ExpandRoot, stack.last())
    }

    @Test
    fun interruptedExitIsDisposedBeforeItsReplacementIsPresented() = runComposeUiTest {
        val first = ExpandDetail(1)
        val second = ExpandDetail(2)
        val stack = navBackStackOf(ExpandRoot)
        var firstOwner: LifecycleOwner? = null
        var stateAtReplacement: Lifecycle.State? = null
        setContent {
            NavDisplay(stack, effects = NavDisplayEffects.None) {
                entry<ExpandRoot> { Box(Modifier.fillMaxSize()) }
                entry<ExpandDetail>(transition = NavTransitions.Expansion) { route ->
                    if (route == first) firstOwner = LocalLifecycleOwner.current
                    if (route == second) {
                        SideEffect {
                            if (stateAtReplacement == null) stateAtReplacement = firstOwner?.lifecycle?.currentState
                        }
                    }
                    Box(Modifier.fillMaxSize().background(Color.Blue))
                }
            }
        }
        waitForIdle()
        runOnIdle { stack.add(first) }
        waitForIdle()
        mainClock.autoAdvance = false
        runOnIdle { stack.removeLastOrNull() }
        mainClock.advanceTimeBy(96)
        waitForIdle()
        runOnIdle { stack.add(second) }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertEquals(Lifecycle.State.DESTROYED, stateAtReplacement)
        assertEquals(listOf<NavKey>(ExpandRoot, second), stack.toList())
        mainClock.autoAdvance = true
        waitForIdle()
    }

    @Test
    fun componentSourceCanReopenAfterItsDestinationIsRemoved() = runComposeUiTest {
        val stack = navBackStackOf(ExpandRoot)
        val detail = ExpandDetail(1)
        var owner: LifecycleOwner? = null
        setContent {
            NavDisplay(stack, effects = NavDisplayEffects.None, transition = NavTransitions.expansion(Color.Blue)) {
                entry<ExpandRoot> {
                    Box(Modifier.size(100.dp).navTransitionSource(detail, containerColor = Color.Green).background(Color.Green))
                }
                entry<ExpandDetail> {
                    owner = LocalLifecycleOwner.current
                    Box(Modifier.fillMaxSize().background(Color.Blue))
                }
            }
        }
        waitForIdle()
        runOnIdle { stack.add(detail) }
        waitForIdle()
        val previousOwner = assertNotNull(owner)
        assertEquals(Lifecycle.State.RESUMED, previousOwner.lifecycle.currentState)
        runOnIdle { stack.removeLastOrNull() }
        waitForIdle()
        assertEquals(Lifecycle.State.DESTROYED, previousOwner.lifecycle.currentState)
        runOnIdle { stack.add(detail) }
        waitForIdle()
        assertEquals(listOf<NavKey>(ExpandRoot, detail), stack.toList())
        assertNotSame(previousOwner, owner)
        assertEquals(Lifecycle.State.RESUMED, assertNotNull(owner).lifecycle.currentState)
    }

    @Test
    fun scaledPaneConvertsWindowAnchorsInsteadOfOnlySubtractingTheOrigin() = runComposeUiTest {
        val stack = navBackStackOf(ExpandRoot)
        val source = NavTransitionAnchor(Rect(100f, 316f, 310f, 412f), 0.dp)
        val transition = NavTransitions.expandFrom(Color.Blue, cornerRadius = 0.dp) { source }
        var detailScope: NavTransitionScope? = null
        setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                Box(Modifier.size(420.dp, 760.dp).padding(start = 40.dp, top = 60.dp, end = 20.dp, bottom = 20.dp)) {
                    NavDisplay(
                        stack,
                        modifier = Modifier.fillMaxSize().graphicsLayer {
                            scaleX = 0.75f
                            scaleY = 0.8f
                            transformOrigin = TransformOrigin(0f, 0f)
                        },
                        effects = NavDisplayEffects.None,
                    ) {
                        entry<ExpandRoot> { Box(Modifier.fillMaxSize().background(Color.Green)) }
                        entry<ExpandDetail>(transition = transition) {
                            detailScope = LocalNavTransitionScope.current
                            Box(Modifier.fillMaxSize().background(Color.Blue))
                        }
                    }
                }
            }
        }
        waitForIdle()
        runOnIdle { stack.add(ExpandDetail(1)) }
        waitForIdle()
        val scope = assertNotNull(detailScope)
        val local = scope.windowToLocal(source.bounds.topLeft)
        assertEquals(80f, local.x, 0.001f)
        assertEquals(320f, local.y, 0.001f)
        val frame = navExpandFrame(
            source, scope.layoutSize, scope.layoutPositionInWindow, 0f, 0.85f,
            0f, 0f, scope.density, scope::windowToLocal,
        )
        assertEquals(80f, frame.bounds.left, 0.001f)
        assertEquals(320f, frame.bounds.top, 0.001f)
        assertEquals(280f, frame.bounds.width, 0.001f)
        assertEquals(120f, frame.bounds.height, 0.001f)
    }

    @Test
    fun stackedDestinationsKeepTheirKeysThroughPredictiveCancelAndPop() = runComposeUiTest {
        val dispatcher = NavigationEventDispatcher()
        val owner = object : NavigationEventDispatcherOwner {
            override val navigationEventDispatcher = dispatcher
        }
        val input = DirectNavigationEventInput().also { dispatcher.addInput(it) }
        val first = ExpandDetail(1)
        val second = ExpandDetail(2)
        val stack = navBackStackOf(ExpandRoot)
        val anchors = mapOf(
            first to NavTransitionAnchor(Rect(20f, 100f, 300f, 220f)),
            second to NavTransitionAnchor(Rect(40f, 300f, 280f, 450f)),
        )
        val observed = mutableSetOf<NavKey?>()
        val transition = NavTransitions.expandFrom(Color.Blue, targetCornerRadius = 48.dp) { scope ->
            observed.add(scope.entryKey)
            anchors[scope.entryKey]
        }
        val scopes = mutableMapOf<NavKey, NavTransitionScope>()
        setContent {
            CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner) {
                NavDisplay(stack, effects = NavDisplayEffects.None) {
                    entry<ExpandRoot> { Box(Modifier.fillMaxSize().background(Color.Green)) }
                    entry<ExpandDetail>(contentKey = { "detail-${it.id}" }, transition = transition) { route ->
                        scopes[route] = LocalNavTransitionScope.current
                        Box(Modifier.fillMaxSize().background(Color.Blue))
                    }
                }
            }
        }
        waitForIdle()
        runOnIdle { stack.add(first) }
        waitForIdle()
        assertFalse(assertNotNull(scopes[first]).isRunning)
        assertEquals(Color.Blue, onRoot().captureToImage().toPixelMap()[2, 2], "a settled page must release the transition's rounded clip")
        runOnIdle { stack.add(second) }
        waitForIdle()
        assertEquals(setOf<NavKey?>(first, second), observed)

        runOnIdle {
            input.backStarted(NavigationEvent(progress = 0f, frameTimeMillis = 1000L))
            input.backProgressed(NavigationEvent(progress = 0.4f, frameTimeMillis = 1100L))
        }
        waitForIdle()
        assertTrue(assertNotNull(scopes[second]).isRunning)
        assertTrue(assertNotNull(scopes[second]).relativeDepth < 0f)
        runOnIdle { input.backCancelled() }
        waitForIdle()
        assertEquals(listOf<NavKey>(ExpandRoot, first, second), stack.toList())
        assertFalse(assertNotNull(scopes[second]).isRunning)
        assertEquals(0f, assertNotNull(scopes[second]).relativeDepth, 0.001f)

        observed.clear()
        runOnIdle {
            input.backStarted(NavigationEvent(progress = 0f, frameTimeMillis = 2000L))
            input.backProgressed(NavigationEvent(progress = 0.7f, frameTimeMillis = 2100L))
            input.backCompleted()
        }
        waitForIdle()
        assertEquals(listOf<NavKey>(ExpandRoot, first), stack.toList())
        assertTrue(second in observed, "the leaving route must retain its own anchor lookup key")
        assertEquals(first, assertNotNull(scopes[first]).entryKey)
        assertEquals(Color.Blue, onRoot().captureToImage().toPixelMap()[2, 2], "returning to a settled page must not retain a rounded clip")
        runOnIdle { stack.removeLastOrNull() }
        waitForIdle()
        assertEquals(listOf<NavKey>(ExpandRoot), stack.toList())
    }
}
