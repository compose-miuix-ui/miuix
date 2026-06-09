// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

/**
 * The render role of a navigation entry, derived from its relative depth.
 *
 * - [Top]: at the target top of the stack (relative depth ≈ 0), fully presented.
 * - [Incoming]: above the top, sliding in from the leading edge (-1 < d < 0, not removing).
 * - [Outgoing]: above the top, sliding out toward the leading edge (-1 < d < 0, removing).
 * - [Covered]: below the top, covered by an upper layer (0 < d).
 *
 * Order and names are contract-fixed (design spec §6.1).
 */
public enum class NavRole { Top, Incoming, Outgoing, Covered }
