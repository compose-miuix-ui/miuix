// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import top.yukonga.miuix.kmp.nav.transition.internal.LocalNavExpansionState
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpansionState
import top.yukonga.miuix.kmp.nav.transition.navTransitionSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

private data object HomeRoute : NavKey

private data object DetailRoute : NavKey

/**
 * Recomposition-count tests for [NavDisplay]: per-frame changes of the driving float must stay
 * inside deferred `graphicsLayer { }` reads and never restart an entry host's composition scope.
 *
 * The counting transition exploits the composition/draw split of the transition contract:
 * `transformEntry` runs exactly once per host composition, while the `graphicsLayer` block it
 * installs runs once per drawn frame. Comparing the two deltas across a programmatic push tells
 * apart "animates via deferred reads" (draw count ~ frame count, composition count ~ threshold
 * crossings) from "recomposes every frame" (both counts ~ frame count).
 */
@OptIn(ExperimentalTestApi::class)
class NavDisplayRecompositionTest {

    @Test
    fun expansionSourcesSkipUnrelatedParentUpdatesAndPagesSkipAnimationFrames() = runComposeUiTest {
        val stack = navBackStackOf(HomeRoute)
        val unrelated = mutableIntStateOf(0)
        var parentCompositions = 0
        var sourceCompositions = 0
        var destinationKeyLookups = 0
        var homeCompositions = 0
        var detailCompositions = 0
        var expansionState: NavExpansionState? = null
        var homeScope: NavTransitionScope? = null
        val onSourceComposed: () -> Unit = { sourceCompositions++ }
        setContent {
            NavDisplay(stack, transition = NavTransitions.expansion(Color.Blue), effects = NavDisplayEffects.None) {
                entry<HomeRoute> {
                    homeScope = LocalNavTransitionScope.current
                    expansionState = LocalNavExpansionState.current
                    SideEffect { homeCompositions++ }
                    Box(Modifier.fillMaxSize()) {
                        SourceParent({ parentCompositions++ }, onSourceComposed, unrelated.intValue)
                    }
                }
                entry<DetailRoute>(contentKey = {
                    destinationKeyLookups++
                    it
                }) {
                    SideEffect { detailCompositions++ }
                    Box(Modifier.fillMaxSize().background(Color.Blue))
                }
            }
        }
        waitForIdle()
        val sourceAtRest = sourceCompositions
        val parentAtRest = parentCompositions
        val initialScope = homeScope
        runOnIdle { unrelated.intValue++ }
        waitForIdle()
        assertEquals(parentAtRest + 1, parentCompositions)
        assertEquals(sourceAtRest, sourceCompositions)
        assertEquals(0, destinationKeyLookups, "source policy must not build an off-stack entry")
        val homeAtRest = homeCompositions

        mainClock.autoAdvance = false
        runOnIdle { stack.add(DetailRoute) }
        mainClock.advanceTimeBy(96)
        waitForIdle()
        assertNotNull(assertNotNull(expansionState).anchorState(DetailRoute).value?.source?.layer)
        assertEquals(1, detailCompositions)
        mainClock.advanceTimeBy(800)
        waitForIdle()
        assertEquals(1, detailCompositions)
        assertEquals(homeAtRest, homeCompositions)
        assertSame(initialScope, homeScope)

        runOnIdle { stack.removeLastOrNull() }
        mainClock.advanceTimeBy(800)
        waitForIdle()
        assertEquals(1, detailCompositions)
        assertEquals(homeAtRest, homeCompositions)
        assertEquals(sourceAtRest, sourceCompositions)
        assertSame(initialScope, homeScope)
    }

    @Test
    fun programmaticPushRecomposesHostsOnlyOnThresholdCrossings() = runComposeUiTest {
        mainClock.autoAdvance = false
        var hostCompositions = 0
        var drawEvaluations = 0
        val countingTransition = NavTransition { scope ->
            hostCompositions++
            graphicsLayer {
                drawEvaluations++
                translationX = scope.relativeDepth.coerceIn(-1f, 0f) * size.width
            }
        }
        val backStack = navBackStackOf(HomeRoute)
        setContent {
            NavDisplay(
                backStack = backStack,
                transition = countingTransition,
                effects = NavDisplayEffects.None,
            ) {
                entry<HomeRoute> { Box(Modifier.fillMaxSize()) }
                entry<DetailRoute> { Box(Modifier.fillMaxSize()) }
            }
        }
        mainClock.advanceTimeBy(96)
        val hostBaseline = hostCompositions
        val drawBaseline = drawEvaluations

        backStack.add(DetailRoute)
        // Spans the full 500ms programmatic settle plus margin.
        mainClock.advanceTimeBy(800)

        val hostDelta = hostCompositions - hostBaseline
        val drawDelta = drawEvaluations - drawBaseline
        assertTrue(
            drawDelta >= 20,
            "the push must animate through deferred draw reads (drawEvaluations delta = $drawDelta)",
        )
        assertTrue(
            hostDelta <= 12,
            "entry hosts must recompose only on depth-threshold crossings, not per frame " +
                "(composition delta = $hostDelta across $drawDelta drawn frames)",
        )
    }
}

@Composable
private fun SourceParent(onComposed: () -> Unit, onSourceComposed: () -> Unit, unrelated: Int) {
    SideEffect { onComposed() }
    Box {
        Box(Modifier.size(unrelated.dp))
        CountedSource(
            onSourceComposed,
            Modifier.size(64.dp).navTransitionSource(DetailRoute, containerColor = Color.Green),
        )
    }
}

@Composable
private fun CountedSource(onComposed: () -> Unit, modifier: Modifier = Modifier) {
    SideEffect { onComposed() }
    Box(modifier.background(Color.Green))
}
