// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.scene

import androidx.navigation3.runtime.NavEntry

/**
 * A specific scene to render 1 or more [NavEntry] instances as an overlay.
 *
 * It is expected that the [content] is rendered in one or more separate windows (e.g., a dialog,
 * popup window, etc.) that are visible above any additional [Scene] instances calculated from the
 * [overlaidEntries].
 *
 * When processing [overlaidEntries], expect processing of each [SceneStrategy] to restart from the
 * first strategy. This may result in multiple instances of the same [OverlayScene] to be shown
 * simultaneously, making a unique [key] even more important.
 */
interface OverlayScene<T : Any> : Scene<T> {

    /**
     * The [androidx.navigation3.runtime.NavEntry]s that should be handled by another [Scene] that
     * sits below this Scene.
     *
     * This *must* always be a non-empty list to correctly display entries below the overlay.
     */
    val overlaidEntries: List<NavEntry<T>>
}
