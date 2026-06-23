// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ComposeViewport
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import webfont.preloadWebFonts
import webfont.queryParam

private const val DEFAULT_CSS_URL =
    "https://cdn-font.hyperos.mi.com/font/css?family=MiSans_VF:VF:Chinese_Simplify&display=swap"

// Safety cap on how long the loading overlay waits for fonts; afterwards it hides and any remaining
// subsets keep downloading in the background.
private const val LOADING_FONT_TIMEOUT_MS = 10_000L

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
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

        // Register each CJK subset with the shared resolver so the whole app picks the glyphs up
        // through the global font fallback (no per-text wrappers), while feeding download progress
        // into the overlay's font bar. Runs in an outer scope, so it survives the overlay's timeout.
        val fontFamilyResolver = LocalFontFamilyResolver.current
        val scope = rememberCoroutineScope()
        LaunchedEffect(cssUrl, fontFamilyResolver) {
            withFrameNanos {} // let the first frame paint behind the overlay
            val preload = scope.launch {
                preloadWebFonts(cssUrl, fontFamilyResolver) { done, total ->
                    platformSetFontProgress(done, total)
                }
            }
            withTimeoutOrNull(LOADING_FONT_TIMEOUT_MS) { preload.join() }
            platformHideLoading()
        }

        App(padding = safePadding)
    }
}
