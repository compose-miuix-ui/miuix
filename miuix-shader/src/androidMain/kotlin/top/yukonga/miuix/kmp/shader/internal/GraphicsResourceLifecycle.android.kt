// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shader.internal

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

internal actual val graphicsResourcesResetOnResume: Boolean = true

@Composable
internal actual fun graphicsResourceLifecycle(): Lifecycle? {
    val context = LocalContext.current
    return remember(context) { context.activityLifecycle() }
}

actual fun CompositionLocalConsumerModifierNode.graphicsResourceLifecycle(): Lifecycle? = currentValueOf(LocalContext).activityLifecycle()

private fun Context.activityLifecycle(): Lifecycle? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return (context as? LifecycleOwner)?.lifecycle
        val baseContext = context.baseContext
        if (baseContext === context) return null
        context = baseContext
    }
    return null
}
