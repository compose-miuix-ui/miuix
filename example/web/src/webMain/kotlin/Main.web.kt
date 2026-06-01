// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ComposeViewport
import lazyfont.LazyWebFontFamily
import lazyfont.LocalLazyTextController
import lazyfont.loadLazyWebFontFamily
import lazyfont.queryParam

private const val DEFAULT_CSS_URL =
    "https://cdn-font.hyperos.mi.com/font/css?family=MiSans_VF:VF:Chinese_Simplify,Latin&display=swap"

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    platformHideLoading()
    val cssUrl = queryParam("cssUrl").ifBlank { DEFAULT_CSS_URL }
    ComposeViewport(viewportContainerId = "composeApp") {
        var insetTopPx by remember { mutableDoubleStateOf(0.0) }
        var insetBottomPx by remember { mutableDoubleStateOf(0.0) }
        var insetStartPx by remember { mutableDoubleStateOf(0.0) }
        var insetEndPx by remember { mutableDoubleStateOf(0.0) }

        LaunchedEffect(Unit) {
            insetTopPx = platformGetCssVar("--safe-area-inset-top")
            insetStartPx = platformGetCssVar("--safe-area-inset-left")
            insetEndPx = platformGetCssVar("--safe-area-inset-right")
            insetBottomPx = platformGetCssVar("--safe-area-inset-bottom")
        }

        val safePadding = PaddingValues(
            top = Dp(insetTopPx.toFloat()),
            bottom = Dp(insetBottomPx.toFloat()),
            start = Dp(insetStartPx.toFloat()),
            end = Dp(insetEndPx.toFloat()),
        )

        val scope = rememberCoroutineScope()
        var controller by remember { mutableStateOf<LazyWebFontFamily?>(null) }
        LaunchedEffect(cssUrl) {
            controller = if (cssUrl.isBlank()) null else loadLazyWebFontFamily(cssUrl, scope)
        }

        CompositionLocalProvider(LocalLazyTextController provides controller) {
            App(padding = safePadding)
        }
    }
}
