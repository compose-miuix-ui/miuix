// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shader.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner

internal actual val graphicsResourcesResetOnResume: Boolean = false

@Composable
internal actual fun graphicsResourceLifecycle(): Lifecycle? = LocalLifecycleOwner.current.lifecycle

actual fun CompositionLocalConsumerModifierNode.graphicsResourceLifecycle(): Lifecycle? = currentValueOf(LocalLifecycleOwner).lifecycle
