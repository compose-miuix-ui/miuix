// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import kotlin.test.Test

private data object FocusRouteA : NavKey

private data object FocusRouteB : NavKey

/** A covered entry must not retain input focus after a push. */
@OptIn(ExperimentalTestApi::class)
class NavFocusTest {

    @Test
    fun pushingEntryClearsFocusFromCoveredEntry() = runComposeUiTest {
        val backStack = navBackStackOf(FocusRouteA)
        setContent {
            NavDisplay(
                backStack = backStack,
                transition = NavTransitions.None,
                effects = NavDisplayEffects.None,
            ) {
                entry<FocusRouteA> {
                    BasicTextField(
                        state = rememberTextFieldState(),
                        modifier = Modifier.size(120.dp).testTag("old-input"),
                    )
                }
                entry<FocusRouteB> { BasicTextField(state = rememberTextFieldState("new")) }
            }
        }
        onNodeWithTag("old-input").performClick()
        onNodeWithTag("old-input").assertIsFocused()

        backStack.add(FocusRouteB)
        waitForIdle()

        onNodeWithTag("old-input").assertIsNotFocused()
    }

    @Test
    fun newEntryCanRequestFocusAfterStackChange() = runComposeUiTest {
        val backStack = navBackStackOf(FocusRouteA)
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<FocusRouteA> {
                    BasicTextField(state = rememberTextFieldState())
                }
                entry<FocusRouteB> {
                    val requester = remember { FocusRequester() }
                    BasicTextField(
                        state = rememberTextFieldState(),
                        modifier = Modifier.size(120.dp).focusRequester(requester).testTag("new-input"),
                    )
                    LaunchedEffect(Unit) { requester.requestFocus() }
                }
            }
        }
        backStack.add(FocusRouteB)
        waitForIdle()

        onNodeWithTag("new-input").assertIsFocused()
    }
}
