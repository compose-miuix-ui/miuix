// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package webfont

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Eagerly downloads every subset declared by the web-font [cssUrl] and registers each one with the
 * shared [FontFamily.Resolver]. The preloaded typefaces join the app-wide glyph fallback chain, so
 * every text composable renders CJK automatically, with no per-call wrappers.
 *
 * Fonts are fetched over plain HTTP and pushed into the resolver up front: no lazy per-glyph
 * machinery, and no local-font permission prompt. The set is fetched once; the browser HTTP cache
 * keeps repeat visits cheap.
 *
 * [onProgress] reports `(completed, total)` subset counts so the caller can drive a loading bar: it
 * fires once with the total known before any download starts, then after each subset settles —
 * whether it loaded or failed, so the count always reaches the total.
 */
suspend fun preloadWebFonts(
    cssUrl: String,
    resolver: FontFamily.Resolver,
    onProgress: (completed: Int, total: Int) -> Unit = { _, _ -> },
) {
    if (cssUrl.isBlank()) {
        onProgress(0, 0)
        return
    }
    val css = fetchTextOrNull(cssUrl)
    if (css == null) {
        onProgress(0, 0)
        return
    }
    val decls = parseCssFontFaces(css, baseUrl = cssUrl).distinctBy { it.url }
    onProgress(0, decls.size)
    if (decls.isEmpty()) return
    var completed = 0
    coroutineScope {
        for (decl in decls) {
            launch {
                val bytes = fetchBytesOrNull(decl.url)
                val font = bytes?.let { data ->
                    runCatching {
                        Font(identity = decl.url, getData = { data }, weight = decl.weight, style = decl.style)
                    }.getOrNull()
                }
                if (bytes != null && font == null) {
                    consoleWarn("[webfont] Skia rejected font bytes for ${decl.url}")
                }
                if (font != null) {
                    runCatching { resolver.preload(FontFamily(font)) }
                }
                // Count settled subsets (loaded or failed) so the bar always reaches the total.
                completed++
                onProgress(completed, decls.size)
            }
        }
    }
}
