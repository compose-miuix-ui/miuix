// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package webfont

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

internal data class FontFaceDecl(
    val weight: FontWeight,
    val style: FontStyle,
    val url: String,
    val ranges: List<IntRange> = emptyList(),
) {
    fun covers(codePoint: Int): Boolean {
        if (ranges.isEmpty()) return false
        var low = 0
        var high = ranges.size - 1
        while (low <= high) {
            val mid = (low + high) ushr 1
            val r = ranges[mid]
            if (codePoint < r.first) {
                high = mid - 1
            } else if (codePoint > r.last) {
                low = mid + 1
            } else {
                return true
            }
        }
        return false
    }
}

internal fun parseCssFontFaces(css: String, baseUrl: String? = null): List<FontFaceDecl> {
    val out = mutableListOf<FontFaceDecl>()
    val blockRegex = Regex("""@font-face\s*\{([^}]*)\}""", RegexOption.IGNORE_CASE)
    for (match in blockRegex.findAll(css)) {
        val body = match.groupValues[1]
        val weight = parseWeight(pickKey(body, "font-weight"))
        val style = parseStyle(pickKey(body, "font-style"))
        val url = pickFontUrl(pickKey(body, "src"), baseUrl) ?: continue
        val ranges = parseUnicodeRanges(pickKey(body, "unicode-range"))
        out += FontFaceDecl(weight, style, url, ranges)
    }
    return out
}

private fun pickKey(block: String, key: String): String? {
    val r = Regex("""(?:^|[\s;])$key\s*:\s*([^;]+?)\s*(?:;|$)""", RegexOption.IGNORE_CASE)
    return r.find(block)?.groupValues?.get(1)
}

private fun parseWeight(raw: String?): FontWeight {
    if (raw == null) return FontWeight.Normal
    val tokens = raw.trim().lowercase().split(Regex("\\s+"))
    if (tokens.size >= 2 && tokens[0].toIntOrNull() != null && tokens[1].toIntOrNull() != null) {
        return FontWeight.Normal
    }
    val first = tokens.firstOrNull() ?: return FontWeight.Normal
    return when (first) {
        "normal" -> FontWeight.Normal
        "bold" -> FontWeight.Bold
        else -> first.toIntOrNull()?.let { FontWeight(it.coerceIn(1, 1000)) } ?: FontWeight.Normal
    }
}

private fun parseStyle(raw: String?): FontStyle = if (raw?.trim()?.lowercase() == "italic") FontStyle.Italic else FontStyle.Normal

private fun parseUnicodeRanges(raw: String?): List<IntRange> {
    if (raw == null) return emptyList()
    val out = mutableListOf<IntRange>()
    val tokens = raw.split(',')
    for (token in tokens) {
        val trimmed = token.trim()
        if (trimmed.isEmpty()) continue
        val hex = if (trimmed.startsWith("U+", ignoreCase = true)) trimmed.substring(2) else trimmed
        val dashIdx = hex.indexOf('-')
        if (dashIdx >= 0) {
            val start = hex.substring(0, dashIdx).trim().toIntOrNull(16)
            val end = hex.substring(dashIdx + 1).trim().toIntOrNull(16)
            if (start != null && end != null) {
                out.add(start..end)
            }
        } else {
            val point = hex.toIntOrNull(16)
            if (point != null) {
                out.add(point..point)
            }
        }
    }
    return out
}

private fun pickFontUrl(srcRaw: String?, baseUrl: String?): String? {
    if (srcRaw == null) return null
    val entryRegex = Regex(
        """url\(\s*['"]?([^'")]+)['"]?\s*\)\s*(?:format\(\s*['"]?([^'")]+)['"]?\s*\))?""",
        RegexOption.IGNORE_CASE,
    )
    for (m in entryRegex.findAll(srcRaw)) {
        val rawUrl = m.groupValues[1].trim()
        val format = m.groupValues.getOrNull(2)?.trim()?.lowercase().orEmpty()
        val url = resolveUrl(rawUrl, baseUrl)
        val ext = url.substringBefore('?').substringAfterLast('.', "").lowercase()
        val isSupportedFont = format in setOf("woff2", "woff", "truetype", "opentype") ||
            ext in setOf("woff2", "woff", "ttf", "otf")
        if (isSupportedFont) return url
    }
    return null
}

private fun resolveUrl(href: String, baseUrl: String?): String {
    if (baseUrl.isNullOrEmpty()) return href
    if (href.startsWith("http://") || href.startsWith("https://") || href.startsWith("//") || href.startsWith("data:")) {
        return href
    }
    val protoEnd = baseUrl.indexOf("://").let { if (it >= 0) it + 3 else 0 }
    val pathStart = baseUrl.indexOf('/', protoEnd).let { if (it >= 0) it else baseUrl.length }
    val schemeHost = baseUrl.substring(0, pathStart)
    return if (href.startsWith('/')) {
        schemeHost + href
    } else {
        val basePath = baseUrl.substringBeforeLast('/', schemeHost)
        "$basePath/$href"
    }
}
