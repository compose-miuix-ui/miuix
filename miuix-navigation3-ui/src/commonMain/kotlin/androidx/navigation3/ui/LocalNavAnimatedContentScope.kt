// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavEntry

/**
 * Local provider of [AnimatedContentScope] to [NavEntry.Content].
 *
 * This does not have a default value since the AnimatedContentScope is provided at runtime by
 * AnimatedContent.
 */
val LocalNavAnimatedContentScope: ProvidableCompositionLocal<AnimatedContentScope> =
    compositionLocalOf {
        // no default, we need an AnimatedContent to get the AnimatedContentScope
        throw IllegalStateException(
            "Unexpected access to LocalNavAnimatedContentScope. You should only " +
                "access LocalNavAnimatedContentScope inside a NavEntry passed " +
                "to NavDisplay.",
        )
    }
