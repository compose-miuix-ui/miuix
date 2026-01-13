// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope

@Immutable
class SupportingPaneSceneStrategy<T : Any>(
    private val mainPane: (@Composable () -> Unit)?,
    private val supportingPane: (@Composable () -> Unit)?,
    private val extraPane: (@Composable () -> Unit)?,
) : SceneStrategy<T> {

    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
        if (entries.isEmpty()) return null

        val fallbackMainContent: (@Composable () -> Unit)? = entries.firstOrNull()?.let { { it.Content() } }
        val fallbackSupportingContent: (@Composable () -> Unit)? = entries.getOrNull(1)?.let { { it.Content() } }
        val fallbackExtraContent: (@Composable () -> Unit)? = entries.getOrNull(2)?.let { { it.Content() } }

        return ThreePaneScaffoldScene(
            key = entries.last().contentKey,
            previousEntries = entries.dropLast(1),
            entries = entries,
            type = ThreePaneScaffoldType.SupportingPane,
            mainPaneContent = mainPane ?: fallbackMainContent,
            supportingPaneContent = supportingPane ?: fallbackSupportingContent,
            extraPaneContent = extraPane ?: fallbackExtraContent,
            backBehavior = BackNavigationBehavior.PopLast,
        )
    }
}

class SupportingPaneSceneStrategyBuilder<T : Any> {
    var mainPane: (@Composable () -> Unit)? = null
    var supportingPane: (@Composable () -> Unit)? = null
    var extraPane: (@Composable () -> Unit)? = null

    fun mainPane(content: @Composable () -> Unit) {
        mainPane = content
    }

    fun supportingPane(content: @Composable () -> Unit) {
        supportingPane = content
    }

    fun extraPane(content: @Composable () -> Unit) {
        extraPane = content
    }
}

@Composable
fun <T : Any> rememberSupportingPaneSceneStrategy(
    builder: SupportingPaneSceneStrategyBuilder<T>.() -> Unit,
): SupportingPaneSceneStrategy<T> {
    val b = SupportingPaneSceneStrategyBuilder<T>().apply(builder)
    return remember(b.mainPane, b.supportingPane, b.extraPane) {
        SupportingPaneSceneStrategy(
            mainPane = b.mainPane,
            supportingPane = b.supportingPane,
            extraPane = b.extraPane,
        )
    }
}
