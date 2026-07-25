// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import top.yukonga.miuix.kmp.nav.transition.NavMotion
import top.yukonga.miuix.kmp.nav.transition.NavSettleSpec
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition
import kotlin.test.Test
import kotlin.test.assertTrue

private data object MotionRouteA : NavKey

private data object MotionRouteB : NavKey

/**
 * Settle physics must come from the governing transition of the topmost presented entry: a
 * per-route motion override (a fast 64ms programmatic tween) settles a push far sooner than the
 * default 500ms curve would.
 */
@OptIn(ExperimentalTestApi::class)
class NavMotionResolutionTest {

    @Test
    fun perRouteMotionOverride_governsProgrammaticSettle() = runComposeUiTest {
        mainClock.autoAdvance = false
        val backStack = navBackStackOf(MotionRouteA)
        var lastTopDepth = Float.NaN
        val fastTransition = navGraphicsTransition(
            motion = NavMotion(programmatic = NavSettleSpec.Tween(64, LinearEasing)),
        ) { scope ->
            val d = scope.relativeDepth
            // Boundary ownership runs this block for the covered layer below too (0 < d <= 1);
            // sample only the entering top layer so the assertion reads route B's own depth.
            if (d <= 0f) lastTopDepth = d
            translationX = (-d).coerceIn(0f, 1f) * scope.layoutSize.width
        }
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<MotionRouteA> { Box(Modifier.fillMaxSize()) }
                entry<MotionRouteB>(transition = fastTransition) { Box(Modifier.fillMaxSize()) }
            }
        }
        mainClock.advanceTimeByFrame()
        backStack.add(MotionRouteB)
        // 200ms after the push: the 64ms override tween is long settled, while the default
        // 500ms curve would still be mid-flight (depth ≈ -0.16 at this point).
        mainClock.advanceTimeBy(200)
        assertTrue(
            lastTopDepth > -0.01f && lastTopDepth <= 0f,
            "route override motion must have settled by 200ms, top depth was $lastTopDepth",
        )
    }
}
