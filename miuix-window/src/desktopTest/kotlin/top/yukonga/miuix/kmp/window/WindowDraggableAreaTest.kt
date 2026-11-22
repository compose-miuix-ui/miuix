// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import androidx.compose.ui.window.LocalWindowExceptionHandlerFactory
import androidx.compose.ui.window.WindowExceptionHandler
import androidx.compose.ui.window.WindowExceptionHandlerFactory
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.util.concurrent.atomic.AtomicReference
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class, ExperimentalComposeUiApi::class)
class WindowDraggableAreaTest {
    @Test
    fun secondRegionInWindowIsRejected() {
        assertDuplicateIsRejected(inDialog = false)
    }

    @Test
    fun secondRegionInChildDialogIsRejected() {
        assertDuplicateIsRejected(inDialog = true)
    }

    private fun assertDuplicateIsRejected(inDialog: Boolean) {
        val windowFailure = AtomicReference<Throwable>()
        val exceptionHandlerFactory = WindowExceptionHandlerFactory {
            WindowExceptionHandler { throwable ->
                windowFailure.compareAndSet(null, throwable)
            }
        }
        runDesktopComposeUiTest {
            var duplicate by mutableStateOf(false)
            setContent {
                CompositionLocalProvider(
                    LocalWindowExceptionHandlerFactory provides exceptionHandlerFactory,
                ) {
                    MiuixTheme {
                        MiuixWindow(
                            onCloseRequest = {},
                            title = "WindowDraggableArea registration parent",
                        ) {
                            if (inDialog) {
                                WindowDraggableArea()
                                MiuixDialogWindow(
                                    onCloseRequest = {},
                                    title = "WindowDraggableArea registration dialog",
                                ) {
                                    DuplicateCaptionContent(duplicate)
                                }
                            } else {
                                DuplicateCaptionContent(duplicate)
                            }
                        }
                    }
                }
            }

            onNodeWithTag("caption").assertIsDisplayed()
            runOnIdle { duplicate = true }
            waitUntil(timeoutMillis = 5_000) { windowFailure.get() != null }
        }
        val error = assertIs<IllegalStateException>(windowFailure.get())
        assertTrue(
            error.message.orEmpty().contains("Only one WindowDraggableArea"),
            error.stackTraceToString(),
        )
    }

    @Test
    fun parentWindowAndChildDialogHaveIndependentRegions() {
        val windowFailure = AtomicReference<Throwable>()
        val exceptionHandlerFactory = WindowExceptionHandlerFactory {
            WindowExceptionHandler { throwable ->
                windowFailure.compareAndSet(null, throwable)
            }
        }
        runDesktopComposeUiTest {
            setContent {
                CompositionLocalProvider(
                    LocalWindowExceptionHandlerFactory provides exceptionHandlerFactory,
                ) {
                    MiuixTheme {
                        MiuixWindow(
                            onCloseRequest = {},
                            title = "Independent parent",
                        ) {
                            WindowDraggableArea(Modifier.testTag("parent-caption"))
                            MiuixDialogWindow(
                                onCloseRequest = {},
                                title = "Independent child",
                            ) {
                                WindowDraggableArea(Modifier.testTag("child-caption"))
                            }
                        }
                    }
                }
            }

            onNodeWithTag("parent-caption").assertIsDisplayed()
            onNodeWithTag("child-caption").assertIsDisplayed()
            runOnIdle {}
        }
        assertNull(windowFailure.get())
    }

    @Test
    fun regionCanBeRemovedAndReRegistered() {
        val windowFailure = AtomicReference<Throwable>()
        val exceptionHandlerFactory = WindowExceptionHandlerFactory {
            WindowExceptionHandler { throwable ->
                windowFailure.compareAndSet(null, throwable)
            }
        }
        runDesktopComposeUiTest {
            var registered by mutableStateOf(true)
            setContent {
                CompositionLocalProvider(
                    LocalWindowExceptionHandlerFactory provides exceptionHandlerFactory,
                ) {
                    MiuixTheme {
                        MiuixWindow(
                            onCloseRequest = {},
                            title = "Lifecycle window",
                        ) {
                            if (registered) {
                                WindowDraggableArea(Modifier.testTag("caption"))
                            }
                        }
                    }
                }
            }

            onNodeWithTag("caption").assertIsDisplayed()
            runOnIdle { registered = false }
            waitForIdle()
            waitUntil(timeoutMillis = 5_000) {
                onAllNodesWithTag("caption").fetchSemanticsNodes().isEmpty()
            }
            onNodeWithTag("caption").assertDoesNotExist()
            runOnIdle { registered = true }
            waitUntil(timeoutMillis = 5_000) {
                onAllNodesWithTag("caption").fetchSemanticsNodes().size == 1
            }
            onNodeWithTag("caption").assertIsDisplayed()
        }
        assertNull(windowFailure.get())
    }
}

@Composable
private fun DuplicateCaptionContent(duplicate: Boolean) {
    Box(Modifier.fillMaxSize()) {
        WindowDraggableArea(Modifier.testTag("caption"))
        if (duplicate) {
            Box { WindowDraggableArea() }
        }
    }
}
