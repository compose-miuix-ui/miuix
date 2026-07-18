// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test

private data class CoarseDetail(val id: String, val tab: String) : NavKey

private data class SharedRouteA(val id: String) : NavKey

private data class SharedRouteB(val id: String) : NavKey

/**
 * A coarse `contentKey` (e.g. `{ it.id }`) makes reconcile reuse the surviving [NavEntry] when a
 * back-stack slot is replaced under the same content key. The reused instance must adopt the fresh
 * route key together with the content lambda: rendering the old key would pin the screen to stale
 * route arguments (same route type) or invoke the new type's lambda with the old type's key and
 * throw a ClassCastException (content-key collision across route types).
 */
@OptIn(ExperimentalTestApi::class)
class NavEntryAdoptionTest {

    @Test
    fun replaceUnderSameContentKeyRefreshesRouteArguments() = runComposeUiTest {
        val backStack = navBackStackOf(CoarseDetail(id = "1", tab = "info"))
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<CoarseDetail>(contentKey = { it.id }) { BasicText("detail-${it.id}-${it.tab}") }
            }
        }
        onNodeWithText("detail-1-info").assertExists()

        // Same contentKey ("1"), different route arguments: the entry instance is reused and must
        // render the new key, not the first-push capture.
        backStack[0] = CoarseDetail(id = "1", tab = "reviews")
        waitForIdle()
        onNodeWithText("detail-1-reviews").assertExists()
        onNodeWithText("detail-1-info").assertDoesNotExist()
    }

    @Test
    fun crossTypeReplaceUnderSameContentKeySwapsKeyAndContentAtomically() = runComposeUiTest {
        val backStack = navBackStackOf(SharedRouteA("7"))
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<SharedRouteA>(contentKey = { "shared-${it.id}" }) { BasicText("a-${it.id}") }
                entry<SharedRouteB>(contentKey = { "shared-${it.id}" }) { BasicText("b-${it.id}") }
            }
        }
        onNodeWithText("a-7").assertExists()

        // Both route types resolve to contentKey "shared-7": the NavEntry built for SharedRouteA is
        // reused for SharedRouteB. Adopting the lambda without the key would call SharedRouteB's
        // content with a SharedRouteA key and crash with a ClassCastException.
        backStack[0] = SharedRouteB("7")
        waitForIdle()
        onNodeWithText("b-7").assertExists()
        onNodeWithText("a-7").assertDoesNotExist()
    }
}
