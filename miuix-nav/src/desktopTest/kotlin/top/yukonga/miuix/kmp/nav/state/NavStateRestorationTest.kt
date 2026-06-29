// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.state

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.LocalSaveableStateRegistry
import androidx.compose.runtime.saveable.SaveableStateRegistry
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlinx.serialization.Serializable
import top.yukonga.miuix.kmp.nav.core.NavBackStack
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavDisplayEffects
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.core.rememberNavBackStack
import kotlin.test.Test

@Serializable
private sealed interface RestoreRoute : NavKey

@Serializable
private data object RestoreHome : RestoreRoute

@Serializable
private data class RestoreDetail(val id: Int) : RestoreRoute

@Serializable
private data class RestoreProfile(val name: String) : RestoreRoute

@Composable
private fun CounterContent(tag: String) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    BasicText(
        "$tag-$count",
        modifier = Modifier
            .fillMaxSize()
            .clickable { count++ },
    )
}

/**
 * Hand-rolled stand-in for `StateRestorationTester`, whose skiko `actual` is an unimplemented
 * `TODO()` stub in CMP 1.11.1. Replicates the same emulation: capture the registry's saved map
 * while content is live, dispose the content subtree, then recompose it against a fresh registry
 * seeded with the captured values.
 */
private class RestorationHarness {
    var emitContent by mutableStateOf(true)
    var registry by mutableStateOf(SaveableStateRegistry(restoredValues = null, canBeSaved = { true }))

    @Composable
    fun Host(content: @Composable () -> Unit) {
        if (emitContent) {
            CompositionLocalProvider(LocalSaveableStateRegistry provides registry) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalTestApi::class)
private fun ComposeUiTest.emulateSaveAndRestore(harness: RestorationHarness) {
    val saved = harness.registry.performSave()
    harness.emitContent = false
    waitForIdle()
    harness.registry = SaveableStateRegistry(restoredValues = saved, canBeSaved = { true })
    harness.emitContent = true
    waitForIdle()
}

/**
 * End-to-end save/restore across a mixed route hierarchy: the back stack round-trips through the
 * reflection-free polymorphic saver while each entry's `rememberSaveable` round-trips through
 * [NavSaveableStateHolder]. At restore time the stack holds all three slot states at once — the
 * top entry (live), the covered neighbor (composed within the visible window), and the deep entry
 * (culled from composition with its state saved) — so a regression in any of the three paths
 * shows up as a `-0` counter below.
 */
@OptIn(ExperimentalTestApi::class)
class NavStateRestorationTest {

    @Test
    fun rememberSaveableRestoresAcrossMixedRouteHierarchy() = runComposeUiTest {
        val harness = RestorationHarness()
        var stack: NavBackStack? = null
        setContent {
            harness.Host {
                val backStack = rememberNavBackStack<RestoreRoute>(RestoreHome)
                stack = backStack
                NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                    entry<RestoreHome> { CounterContent("home") }
                    entry<RestoreDetail> { CounterContent("detail-${it.id}") }
                    entry<RestoreProfile> { CounterContent("profile-${it.name}") }
                }
            }
        }
        onNodeWithText("home-0").performClick()
        waitForIdle()
        onNodeWithText("home-1").assertExists()

        checkNotNull(stack).add(RestoreDetail(42))
        waitForIdle()
        onNodeWithText("detail-42-0").performClick()
        waitForIdle()
        onNodeWithText("detail-42-1").assertExists()

        checkNotNull(stack).add(RestoreProfile("yu"))
        waitForIdle()
        onNodeWithText("profile-yu-0").performClick()
        waitForIdle()
        onNodeWithText("profile-yu-1").assertExists()

        emulateSaveAndRestore(harness)

        // Top entry state restored; the restored stack instance is re-captured by setContent.
        onNodeWithText("profile-yu-1").assertExists()

        checkNotNull(stack).removeLastOrNull()
        waitForIdle()
        onNodeWithText("detail-42-1").assertExists()

        checkNotNull(stack).removeLastOrNull()
        waitForIdle()
        onNodeWithText("home-1").assertExists()
    }
}
