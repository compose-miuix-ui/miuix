// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import top.yukonga.miuix.kmp.shared.generated.resources.Res

@Composable
actual fun rememberAboutLibrariesJsonLoader(): suspend () -> String {
    val context = LocalContext.current.applicationContext
    return remember(context) {
        {
            runCatching {
                context.assets.open("aboutlibraries.json").bufferedReader().use { it.readText() }
            }.getOrElse {
                Res.readBytes("files/aboutlibraries.json").decodeToString()
            }
        }
    }
}
