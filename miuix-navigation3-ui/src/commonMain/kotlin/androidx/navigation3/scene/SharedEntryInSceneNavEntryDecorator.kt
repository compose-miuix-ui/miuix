// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.scene

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.ui.LocalNavAnimatedContentScope

/** Returns a [SharedEntryInSceneNavEntryDecorator] that is remembered across recompositions. */
@Composable
internal fun <T : Any> rememberSharedEntryInSceneNavEntryDecorator(
    sharedTransitionScope: SharedTransitionScope,
): SharedEntryInSceneNavEntryDecorator<T> = remember(sharedTransitionScope) { SharedEntryInSceneNavEntryDecorator(sharedTransitionScope) }

/**
 * A [NavEntryDecorator] that wraps each entry in a [Modifier.sharedElement] to allow nav displays
 * to animate arbitrarily place entries in different places in the composable call hierarchy.
 *
 * This should be wrapped around the [SceneSetupNavEntryDecorator].
 */
internal class SharedEntryInSceneNavEntryDecorator<T : Any>(
    sharedTransitionScope: SharedTransitionScope,
) : NavEntryDecorator<T>(
    decorate = { entry ->
        with(sharedTransitionScope) {
            Box(
                Modifier.sharedElement(
                    rememberSharedContentState(entry.contentKey),
                    animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                ),
            ) {
                entry.Content()
            }
        }
    },
)
