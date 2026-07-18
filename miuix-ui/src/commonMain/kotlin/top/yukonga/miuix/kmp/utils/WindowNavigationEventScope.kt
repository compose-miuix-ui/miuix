// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Composable

/**
 * Rebinds back-event handling to the platform window hosting [content].
 *
 * On Android a separate platform window (such as a platform `Dialog`) receives system back events
 * through its own dispatcher only. A `LocalNavigationEventDispatcherOwner` explicitly provided in
 * the host composition is inherited across the window boundary and would register back handlers
 * on a dispatcher that never sees this window's events, so this scope re-resolves the owner from
 * the window's view tree; when no view-tree owner exists, the inherited value is kept. The
 * resolution matches the local's own host-default path, so behavior is unchanged when nothing was
 * explicitly provided.
 *
 * On Skiko platforms dialogs are layered inside the host window and this scope is a pass-through.
 *
 * The Window* components in this library apply it automatically; wrap the root of a custom
 * separate-window host whose content uses predictive-back handlers.
 */
@Composable
expect fun WindowNavigationEventScope(content: @Composable () -> Unit)
