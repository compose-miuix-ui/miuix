// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.lifecycle.Lifecycle

internal actual val graphicsResourcesResetOnResume: Boolean = false

@Composable
internal actual fun graphicsResourceLifecycle(): Lifecycle? = null

actual fun CompositionLocalConsumerModifierNode.graphicsResourceLifecycle(): Lifecycle? = null
