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

// Mirrors the real-world trigger: same-named data classes in different packages/modules. Kotlin
// data class toString() omits the qualifier, so both print "Card(id=N)" yet never compare equal —
// they pass the equality-based duplicate guard while collapsing to one saveable-state slot.
private object FeatureA {
    data class Card(val id: Int) : NavKey
}

private object FeatureB {
    data class Card(val id: Int) : NavKey
}

private data class HostRoute(val tag: Int) : NavKey

private data class IntDetail(val id: Int) : NavKey

private data class StringDetail(val id: String) : NavKey

/**
 * Distinct-by-equals contentKeys whose toString() collides must be rejected at reconcile time with
 * an actionable message. Without the guard the failure surfaces as androidx's misattributed
 * "Key was used multiple times" crash when the colliders are co-composed (adjacent push, replace),
 * or as silent rememberSaveable state bleed / cross-removal when they are not (deep culled entry).
 */
@OptIn(ExperimentalTestApi::class)
class NavSaveableKeyCollisionTest {

    @Test
    fun adjacentToStringCollisionFailsFastWithActionableMessage() = runComposeUiTest {
        val backStack = navBackStackOf(FeatureA.Card(1))
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<FeatureA.Card> { BasicText("a-card-${it.id}") }
                entry<FeatureB.Card> { BasicText("b-card-${it.id}") }
            }
        }
        onNodeWithText("a-card-1").assertExists()

        val failure = assertFailsWith<IllegalArgumentException> {
            backStack.add(FeatureB.Card(1))
            waitForIdle()
        }
        assertContains(checkNotNull(failure.message), "saveable-state key")
        assertContains(checkNotNull(failure.message), "Card(id=1)")
        assertContains(checkNotNull(failure.message), "contentKey")
    }

    @Test
    fun separatedToStringCollisionFailsFastInsteadOfSilentStateBleed() = runComposeUiTest {
        // The colliding pair ends up three apart: the deep entry is culled from composition with
        // its state saved, so without the guard the new collider silently restores it (bleed) —
        // no crash would ever surface.
        val backStack = navBackStackOf(FeatureA.Card(7), HostRoute(1), HostRoute(2))
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<FeatureA.Card> { BasicText("a-card-${it.id}") }
                entry<FeatureB.Card> { BasicText("b-card-${it.id}") }
                entry<HostRoute> { BasicText("host-${it.tag}") }
            }
        }
        onNodeWithText("host-2").assertExists()

        val failure = assertFailsWith<IllegalArgumentException> {
            backStack.add(FeatureB.Card(7))
            waitForIdle()
        }
        assertContains(checkNotNull(failure.message), "saveable-state key")
        assertContains(checkNotNull(failure.message), "Card(id=7)")
    }

    @Test
    fun collisionWithEntryStillAnimatingOutFailsFast() = runComposeUiTest {
        // Same-frame replace: the leaving entry is no longer on the back stack but is still
        // presented (animating out), so its saveable slot is still live — a collider entering in
        // the same reconcile must be rejected by the presented-set arm of the guard.
        val backStack = navBackStackOf(HostRoute(0), FeatureA.Card(3))
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<FeatureA.Card> { BasicText("a-card-${it.id}") }
                entry<FeatureB.Card> { BasicText("b-card-${it.id}") }
                entry<HostRoute> { BasicText("host-${it.tag}") }
            }
        }
        onNodeWithText("a-card-3").assertExists()

        val failure = assertFailsWith<IllegalArgumentException> {
            backStack.removeLastOrNull()
            backStack.add(FeatureB.Card(3))
            waitForIdle()
        }
        assertContains(checkNotNull(failure.message), "saveable-state key")
        assertContains(checkNotNull(failure.message), "Card(id=3)")
    }

    @Test
    fun factoryKeysCollidingAcrossTypesAreRejected() = runComposeUiTest {
        // The reviewer's literal example, reachable through the public factory escape hatch:
        // Int 1 and String "1" are unequal contentKeys that both stringify to "1".
        val backStack = navBackStackOf(IntDetail(1))
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<IntDetail>(contentKey = { it.id }) { BasicText("int-${it.id}") }
                entry<StringDetail>(contentKey = { it.id }) { BasicText("string-${it.id}") }
            }
        }
        onNodeWithText("int-1").assertExists()

        val failure = assertFailsWith<IllegalArgumentException> {
            backStack.add(StringDetail("1"))
            waitForIdle()
        }
        assertContains(checkNotNull(failure.message), "saveable-state key")
        assertContains(checkNotNull(failure.message), "contentKey")
    }
}
