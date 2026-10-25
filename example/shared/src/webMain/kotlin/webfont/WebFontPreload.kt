// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package webfont

import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.text.UnresolvedSymbolsRegistry
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.platform.WebUnresolvedSymbolsRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

@OptIn(InternalComposeApi::class)
class OnDemandWebFontManager(
    val cssUrl: String,
    private val resolver: FontFamily.Resolver,
    private val scope: CoroutineScope,
    private val onLatinFamilyLoaded: ((FontFamily) -> Unit)? = null,
) : UnresolvedSymbolsRegistry.Listener,
    AutoCloseable {

    private var decls: List<FontFaceDecl>? = null
    private val pendingCodepoints = mutableSetOf<Int>()
    private val downloadedUrls = mutableSetOf<String>()
    private val inFlightUrls = mutableSetOf<String>()
    private val failedUrls = mutableSetOf<String>()
    private val semaphore = Semaphore(permits = 4)

    init {
        scope.launch {
            if (cssUrl.isBlank()) return@launch
            val css = fetchTextOrNull(cssUrl)
            if (css != null) {
                val parsed = parseCssFontFaces(css, baseUrl = cssUrl)
                decls = parsed
                preloadLatinSubsets()
                if (pendingCodepoints.isNotEmpty()) {
                    val batch = pendingCodepoints.toSet()
                    pendingCodepoints.clear()
                    handleUnresolved(batch)
                }
            } else {
                consoleWarn("[webfont] Failed to fetch font CSS: $cssUrl")
            }
        }
    }

    private fun preloadLatinSubsets() {
        scope.launch {
            val bytes = fetchBytesOrNull("misans_latin.woff2")
            if (bytes != null) {
                val family = createVariableFontFamily(
                    url = "misans-latin",
                    bytes = bytes,
                    style = androidx.compose.ui.text.font.FontStyle.Normal,
                )
                if (family != null) {
                    runCatching { resolver.preload(family) }
                    onLatinFamilyLoaded?.invoke(family)
                    WebUnresolvedSymbolsRegistry.onNewFontInstalled()
                }
            }
        }
    }

    override fun onUnresolvedCodepoints(codepoints: Set<Int>) {
        val currentDecls = decls
        if (currentDecls == null) {
            pendingCodepoints.addAll(codepoints)
            return
        }
        handleUnresolved(codepoints)
    }

    private fun handleUnresolved(codepoints: Set<Int>) {
        val currentDecls = decls ?: return
        val needed = mutableListOf<FontFaceDecl>()

        for (decl in currentDecls) {
            if (decl.url in downloadedUrls || decl.url in inFlightUrls || decl.url in failedUrls) {
                continue
            }
            for (cp in codepoints) {
                if (decl.covers(cp)) {
                    needed.add(decl)
                    break
                }
            }
        }

        if (needed.isEmpty()) return

        for (decl in needed) {
            inFlightUrls.add(decl.url)
            scope.launch {
                semaphore.withPermit {
                    val bytes = fetchBytesOrNull(decl.url)
                    inFlightUrls.remove(decl.url)
                    if (bytes != null) {
                        downloadedUrls.add(decl.url)
                        val family = createVariableFontFamily(decl.url, bytes, decl.style)
                        if (family != null) {
                            runCatching { resolver.preload(family) }
                            WebUnresolvedSymbolsRegistry.onNewFontInstalled()
                        } else {
                            failedUrls.add(decl.url)
                            consoleWarn("[webfont] Skia rejected font bytes for ${decl.url}")
                        }
                    } else {
                        failedUrls.add(decl.url)
                    }
                }
            }
        }
    }

    override fun close() {
        WebUnresolvedSymbolsRegistry.removeListener(this)
    }
}

@OptIn(InternalComposeApi::class)
fun installOnDemandWebFonts(
    cssUrl: String,
    resolver: FontFamily.Resolver,
    scope: CoroutineScope,
    onLatinFamilyLoaded: ((FontFamily) -> Unit)? = null,
): AutoCloseable {
    val manager = OnDemandWebFontManager(cssUrl, resolver, scope, onLatinFamilyLoaded)
    WebUnresolvedSymbolsRegistry.addListener(manager)
    return manager
}

private val miSansFullWeights = listOf(
    FontWeight.W400 to 330, // MiSans Regular (default regular weight)
    FontWeight.W500 to 430, // MiSans Medium
    FontWeight.W600 to 570, // MiSans Semibold
    FontWeight.W700 to 660, // MiSans Bold
    FontWeight.W800 to 700, // MiSans Heavy
    FontWeight.W900 to 700, // MiSans Heavy
    FontWeight.W300 to 250, // MiSans Light
    FontWeight.W200 to 200, // MiSans ExtraLight
    FontWeight.W100 to 150, // MiSans Thin
)

private fun createVariableFontFamily(url: String, bytes: ByteArray, style: androidx.compose.ui.text.font.FontStyle): FontFamily? = runCatching {
    val fonts = miSansFullWeights.map { (fw, wght) ->
        Font(
            identity = "$url-$wght",
            getData = { bytes },
            weight = fw,
            style = style,
            variationSettings = FontVariation.Settings(FontVariation.weight(wght)),
        )
    }
    FontFamily(fonts)
}.getOrNull()
