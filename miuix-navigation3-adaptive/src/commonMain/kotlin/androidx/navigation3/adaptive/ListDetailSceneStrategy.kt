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
class ListDetailSceneStrategy<T : Any>(
    private val listPane: (@Composable () -> Unit)?,
    private val detailPane: (@Composable () -> Unit)?,
    private val extraPane: (@Composable () -> Unit)?,
    private val detailPlaceholder: (@Composable () -> Unit)?,
) : SceneStrategy<T> {

    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
        if (entries.isEmpty()) return null

        val fallbackListContent: (@Composable () -> Unit)? = entries.firstOrNull()?.let { { it.Content() } }
        val fallbackDetailContent: (@Composable () -> Unit)? = entries.getOrNull(1)?.let { { it.Content() } }
        val fallbackExtraContent: (@Composable () -> Unit)? = entries.getOrNull(2)?.let { { it.Content() } }

        return ThreePaneScaffoldScene(
            key = entries.last().contentKey,
            previousEntries = entries.dropLast(1),
            entries = entries,
            type = ThreePaneScaffoldType.ListDetail,
            listPaneContent = listPane ?: fallbackListContent,
            detailPaneContent = detailPane ?: fallbackDetailContent,
            extraPaneContent = extraPane ?: fallbackExtraContent,
            detailPlaceholder = detailPlaceholder,
            backBehavior = BackNavigationBehavior.PopLast,
        )
    }
}

class ListDetailSceneStrategyBuilder<T : Any> {
    var listPane: (@Composable () -> Unit)? = null
    var detailPane: (@Composable () -> Unit)? = null
    var extraPane: (@Composable () -> Unit)? = null
    var detailPlaceholder: (@Composable () -> Unit)? = null

    fun listPane(content: @Composable () -> Unit) {
        listPane = content
    }

    fun detailPane(content: @Composable () -> Unit) {
        detailPane = content
    }

    fun extraPane(content: @Composable () -> Unit) {
        extraPane = content
    }

    fun detailPlaceholder(content: @Composable () -> Unit) {
        detailPlaceholder = content
    }
}

@Composable
fun <T : Any> rememberListDetailSceneStrategy(
    builder: ListDetailSceneStrategyBuilder<T>.() -> Unit,
): ListDetailSceneStrategy<T> {
    val b = ListDetailSceneStrategyBuilder<T>().apply(builder)
    return remember(b.listPane, b.detailPane, b.extraPane, b.detailPlaceholder) {
        ListDetailSceneStrategy(
            listPane = b.listPane,
            detailPane = b.detailPane,
            extraPane = b.extraPane,
            detailPlaceholder = b.detailPlaceholder,
        )
    }
}
