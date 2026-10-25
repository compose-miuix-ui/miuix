// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ComposeViewport
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.withFontFamily
import webfont.installOnDemandWebFonts
import webfont.queryParam

private const val DEFAULT_CSS_URL =
    "https://i02.appmifile.com/i18n/fonts/MiSans/vf/index.css"

@OptIn(ExperimentalComposeUiApi::class, InternalComposeApi::class)
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

        // Preload core Latin subsets for English/numbers and fetch CJK subsets on-demand via WebUnresolvedSymbolsRegistry.
        val fontFamilyResolver = LocalFontFamilyResolver.current
        val scope = rememberCoroutineScope()
        var miSansFamily by remember { mutableStateOf<FontFamily?>(null) }
        DisposableEffect(cssUrl, fontFamilyResolver, scope) {
            val handle = installOnDemandWebFonts(
                cssUrl = cssUrl,
                resolver = fontFamilyResolver,
                scope = scope,
                onLatinFamilyLoaded = { family ->
                    miSansFamily = family
                },
            )
            onDispose {
                handle.close()
            }
        }

        val baseTextStyles = MiuixTheme.textStyles
        val textStyles = remember(miSansFamily, baseTextStyles) {
            miSansFamily?.let { baseTextStyles.withFontFamily(it) } ?: baseTextStyles
        }

        LaunchedEffect(Unit) {
            withFrameNanos {} // let the first frame paint behind the overlay
            platformHideLoading()
        }
        App(padding = safePadding, textStyles = textStyles)
    }
}
