// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.state

import androidx.lifecycle.Lifecycle
import top.yukonga.miuix.kmp.nav.core.NavPresentationState

/**
 * Maps an entry's presentation snapshot (its `isRemoving` flag) and continuous relative depth to
 * the maximum [Lifecycle.State] that entry's [androidx.lifecycle.LifecycleOwner] may reach, per
 * design doc §5.3:
 *
 * - top steady state -> [Lifecycle.State.RESUMED]
 * - covered layer    -> [Lifecycle.State.STARTED]
 * - being removed     -> [Lifecycle.State.CREATED]
 * - fully exited      -> [Lifecycle.State.DESTROYED]
 *
 * The `0.5` threshold splits "top" from "covered": a settled top sits at `d = 0` (RESUMED) while
 * the first covered layer sits at `d = 1` (STARTED). An incoming entry above the top
 * (`-1 < d < -0.5`, not removing) is STARTED while it slides in. Anything with `d <= -1` has left
 * the presentation window and is DESTROYED.
 *
 * @param presentation the entry's render-state snapshot; only [NavPresentationState.isRemoving] is
 *   read by this mapping (role does not affect the lifecycle ceiling).
 * @param d the entry's relative depth (`animatedTop - entryIndex`).
 * @return the lifecycle ceiling for this entry.
 */
internal fun navMaxLifecycleFor(presentation: NavPresentationState, d: Float): Lifecycle.State = when {
    d <= -1f -> Lifecycle.State.DESTROYED
    presentation.isRemoving -> Lifecycle.State.CREATED
    d < -0.5f -> Lifecycle.State.STARTED
    d < 0.5f -> Lifecycle.State.RESUMED
    else -> Lifecycle.State.STARTED
}
