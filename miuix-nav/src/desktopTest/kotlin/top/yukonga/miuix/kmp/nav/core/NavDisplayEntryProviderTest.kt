// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test

private data object StaleHomeRoute : NavKey

/**
 * The entry-registration DSL re-materializes into a new provider whenever its captures change
 * (`remember(content)` in the public overload). Entries surviving on the back stack must pick up
 * the rebuilt registration — content and metadata — instead of staying pinned to whatever the DSL
 * captured when the key was first pushed.
 */
@OptIn(ExperimentalTestApi::class)
class NavDisplayEntryProviderTest {

    @Test
    fun survivingEntryRendersRefreshedContentWhenProviderChanges() = runComposeUiTest {
        var alt by mutableStateOf(false)
        val backStack = navBackStackOf(StaleHomeRoute)
        setContent {
            val suffix = if (alt) "alt" else "orig"
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<StaleHomeRoute> { BasicText("home-$suffix") }
            }
        }
        onNodeWithText("home-orig").assertExists()

        alt = true
        waitForIdle()
        onNodeWithText("home-alt").assertExists()
    }
}
