// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import kotlin.test.Test
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
