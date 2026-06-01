// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package lazyfont

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.Font as ComposeFont

class LazyWebFontFamily internal constructor(
    private val decls: List<FontFaceDecl>,
    private val scope: CoroutineScope,
) : LazyTextController {
    private val loadedFonts = mutableStateListOf<ComposeFont>()
    private val loadedDecls = mutableSetOf<FontFaceDecl>()
    private val familyByUrl = mutableStateMapOf<String, FontFamily>()
    private val inFlightUrls = mutableSetOf<String>()
    private val failedUrls = mutableSetOf<String>()
    private val processedTexts = mutableSetOf<String>()

    override val revision: Int get() = loadedFonts.size

    override fun fontFamilyForCodepoint(cp: Int): FontFamily? {
        val decl = findCoveringDecl(cp) ?: return null
        return familyByUrl[decl.url]
    }

    override fun requestText(text: String) {
        if (text.isEmpty() || !processedTexts.add(text) || decls.isEmpty()) return
        val toFetch = LinkedHashMap<String, FontFaceDecl>()
        var i = 0
        while (i < text.length) {
            val cp = text.codePointAtCompat(i)
            i += if (cp >= 0x10000) 2 else 1
            if (isCovered(cp)) continue
            val decl = findCoveringDecl(cp) ?: continue
            if (decl.url in inFlightUrls || decl.url in failedUrls) continue
            if (decl.url !in toFetch) toFetch[decl.url] = decl
        }
        for ((url, decl) in toFetch) {
            inFlightUrls.add(url)
            scope.launch {
                val bytes = fetchBytesOrNull(url)
                inFlightUrls.remove(url)
                if (bytes == null) {
                    failedUrls.add(url)
                    return@launch
                }
                val font = runCatching {
                    Font(identity = url, getData = { bytes }, weight = decl.weight, style = decl.style)
                }.getOrNull()
                if (font == null) {
                    failedUrls.add(url)
                    consoleWarn("[lazyfont] Skia rejected font bytes for $url")
                } else {
                    loadedFonts.add(font)
                    loadedDecls.add(decl)
                    familyByUrl[url] = FontFamily(font)
                }
            }
        }
    }

    private fun isCovered(cp: Int): Boolean {
        for (decl in loadedDecls) {
            for (range in decl.ranges) {
                if (cp in range) return true
            }
        }
        return false
    }

    private fun findCoveringDecl(cp: Int): FontFaceDecl? {
        for (decl in decls) {
            for (range in decl.ranges) {
                if (cp in range) return decl
            }
        }
        return null
    }
}

suspend fun loadLazyWebFontFamily(cssUrl: String, scope: CoroutineScope): LazyWebFontFamily? {
    val css = fetchTextOrNull(cssUrl) ?: return null
    val decls = parseCssFontFaces(css, baseUrl = cssUrl)
    if (decls.isEmpty()) return null
    return LazyWebFontFamily(decls, scope)
}
