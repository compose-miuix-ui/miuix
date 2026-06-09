// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

/**
 * Pure mapping from a predictive-back / edge-swipe gesture progress to the
 * `animatedTop` target value, per spec §7.2.
 *
 * The mapping is strictly linear (1:1 with the finger): a fully completed
 * gesture (`progress == 1`) drives `animatedTop` exactly one step toward the
 * previous entry, i.e. `topIndex - 1`. This linearity is the whole point of the
 * single-driver model — no easing lives on the `finger -> animatedTop` axis, so
 * no inverse-transform is ever needed (contrast nav3's SeekableTransitionState,
 * which bakes easing into its fraction).
 *
 * @param topIndex index of the current top entry in the back stack (`lastIndex`).
 * @param progress raw gesture progress; clamped to `0f..1f`.
 * @return the `animatedTop` value the gesture should snap to.
 */
internal fun fingerTarget(topIndex: Int, progress: Float): Float = topIndex - progress.coerceIn(0f, 1f)
