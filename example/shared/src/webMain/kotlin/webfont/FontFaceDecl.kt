// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package webfont

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

/** One `@font-face` subset reduced to what the preloader needs: where to fetch it and how to tag it. */
internal data class FontFaceDecl(
    val weight: FontWeight,
    val style: FontStyle,
    val url: String,
)

internal fun parseCssFontFaces(css: String, baseUrl: String? = null): List<FontFaceDecl> {
    val out = mutableListOf<FontFaceDecl>()
    val blockRegex = Regex("""@font-face\s*\{([^}]*)\}""", RegexOption.IGNORE_CASE)
    for (match in blockRegex.findAll(css)) {
        val body = match.groupValues[1]
        val weight = parseWeight(pickKey(body, "font-weight"))
        val style = parseStyle(pickKey(body, "font-style"))
        val url = pickFontUrl(pickKey(body, "src"), baseUrl) ?: continue
        out += FontFaceDecl(weight, style, url)
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
    // Variable font ranges like "1 999" or "100 900": collapse to Normal so Skia
    // picks the regular instance instead of an extreme axis value.
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
        val isTtfOrOtf = format == "truetype" || format == "opentype" || ext == "ttf" || ext == "otf"
        if (isTtfOrOtf) return url
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
