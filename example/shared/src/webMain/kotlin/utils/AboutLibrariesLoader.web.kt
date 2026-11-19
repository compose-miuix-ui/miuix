// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.shared.generated.resources.Res

@Composable
actual fun rememberAboutLibrariesJsonLoader(): suspend () -> String = remember {
    { Res.readBytes("files/aboutlibraries.json").decodeToString() }
}
