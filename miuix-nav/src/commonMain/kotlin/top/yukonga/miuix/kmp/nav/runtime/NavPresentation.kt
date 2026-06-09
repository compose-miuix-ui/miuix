// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import top.yukonga.miuix.kmp.nav.transition.NavRole

/** Epsilon around zero within which an entry is treated as the steady-state top. */
private const val TOP_EPSILON = 1e-3f

/**
 * Relative depth of an entry: `animatedTop - entryIndex` (design spec §4.1).
 *
 * The single shared driver `animatedTop` converges to `new.lastIndex`; every entry's visuals are
 * a pure function of this value. The name `relativeDepth` (alias `d`) is contract-fixed.
 */
internal fun relativeDepth(animatedTop: Float, entryIndex: Int): Float = animatedTop - entryIndex

/**
 * Maps a [relativeDepth] to a [NavRole] (design spec §4.1 / §6.1).
 *
 * - `|d| ≤ TOP_EPSILON` -> [NavRole.Top] (steady-state top, jitter-tolerant);
 * - `d > 0` -> [NavRole.Covered] (below the top, possibly culled by [isVisibleAt]);
 * - `-1 < d < 0` -> [NavRole.Outgoing] when [isRemoving], else [NavRole.Incoming].
 *
 * Boundary ownership: the transition between layers `i` and `i-1` is governed by the upper
 * entry `i`; this classification feeds NavDisplay's choice of the governing transition (§4.3).
 */
internal fun roleFor(relativeDepth: Float, isRemoving: Boolean): NavRole = when {
    relativeDepth > TOP_EPSILON -> NavRole.Covered
    relativeDepth < -TOP_EPSILON -> if (isRemoving) NavRole.Outgoing else NavRole.Incoming
    else -> NavRole.Top
}

/**
 * Visibility window: an entry renders iff `-1 < relativeDepth <= opaqueDepth`.
 *
 * Entries with `d <= -1` are unloaded (fully past the leading edge); entries with
 * `d > opaqueDepth` are culled (fully occluded by an upper opaque layer). Modal-style
 * transitions raise [opaqueDepth] above 1 to keep the lower layer visible.
 */
internal fun isVisibleAt(relativeDepth: Float, opaqueDepth: Float): Boolean = relativeDepth > -1f && relativeDepth <= opaqueDepth
