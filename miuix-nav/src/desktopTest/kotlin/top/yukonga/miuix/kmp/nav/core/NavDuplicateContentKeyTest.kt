// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFailsWith

private data class DupDetail(val id: String) : NavKey

/**
 * Duplicate content keys on the back stack are rejected fast with an actionable message.
 * Without the guard a duplicate push is undefined behavior today: the presentation folds both
 * stack positions into one instance, the index teleport drives a transient relativeDepth of -1,
 * and the UI wedges with a lifecycle DESTROYED->STARTED IllegalStateException (or a
 * SaveableStateHolder "Key was used multiple times" crash for a same-frame double push).
 */
@OptIn(ExperimentalTestApi::class)
class NavDuplicateContentKeyTest {

    @Test
    fun duplicateContentKeyFailsFastWithActionableMessage() = runComposeUiTest {
        val backStack = navBackStackOf(DupDetail("42"))
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<DupDetail> { BasicText("detail-${it.id}") }
            }
        }
        onNodeWithText("detail-42").assertExists()

        val failure = assertFailsWith<IllegalArgumentException> {
            backStack.add(DupDetail("42"))
            waitForIdle()
        }
        assertContains(checkNotNull(failure.message), "DupDetail(id=42)")
        assertContains(checkNotNull(failure.message), "contentKey")
    }

    @Test
    fun contentKeyFactoryDerivesPerInstanceKeys() = runComposeUiTest {
        val backStack = navBackStackOf(DupDetail("1"))
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<DupDetail>(contentKey = { "dup-detail-${it.id}" }) { BasicText("detail-${it.id}") }
            }
        }
        onNodeWithText("detail-1").assertExists()

        // Distinct instances map to distinct factory keys: both entries coexist on the stack.
        backStack.add(DupDetail("2"))
        waitForIdle()
        onNodeWithText("detail-2").assertExists()
    }
}
