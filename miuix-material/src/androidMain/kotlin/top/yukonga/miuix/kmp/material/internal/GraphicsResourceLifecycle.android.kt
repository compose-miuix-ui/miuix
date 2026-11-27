// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material.internal

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

@InternalGraphicsResourceApi
actual val graphicsResourcesResetOnResume: Boolean by lazy {
    Build.MANUFACTURER.equals("Xiaomi", ignoreCase = true) &&
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
        Build.SOC_MANUFACTURER.equals("MediaTek", ignoreCase = true)
}

@Composable
@OptIn(InternalGraphicsResourceApi::class)
internal actual fun graphicsResourceLifecycle(): Lifecycle? {
    if (!graphicsResourcesResetOnResume) return null
    val context = LocalContext.current
    return remember(context) { context.activityLifecycle() }
}

@InternalGraphicsResourceApi
actual fun CompositionLocalConsumerModifierNode.graphicsResourceLifecycle(): Lifecycle? = if (graphicsResourcesResetOnResume) currentValueOf(LocalContext).activityLifecycle() else null

private fun Context.activityLifecycle(): Lifecycle? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return (context as? LifecycleOwner)?.lifecycle
        val baseContext = context.baseContext ?: return null
        if (baseContext === context) return null
        context = baseContext
    }
    return null
}
