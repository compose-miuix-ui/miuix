// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package lazyfont

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * Platform-provided controller for lazy remote font loading. Non-web platforms leave
 * [LocalLazyTextController] null, so [LazyText] degrades to a plain Miuix [Text] call.
 */
interface LazyTextController {
    /** Codepoint-keyed lookup of the FontFamily that covers it, or null if not yet loaded. */
    fun fontFamilyForCodepoint(cp: Int): FontFamily?

    /** Register a string so its missing glyphs trigger subset fetches. */
    fun requestText(text: String)

    /** Snapshot-backed counter that bumps whenever a new font becomes available. */
    val revision: Int
}

val LocalLazyTextController = staticCompositionLocalOf<LazyTextController?> { null }

@Composable
fun LazyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = MiuixTheme.textStyles.main,
) {
    val controller = LocalLazyTextController.current
    if (controller == null) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            onTextLayout = onTextLayout,
            style = style,
        )
        return
    }
    LaunchedEffect(text) { controller.requestText(text) }
    val annotated = remember(text, controller.revision) {
        buildSegmented(text, controller)
    }
    Text(
        text = annotated,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout ?: {},
        style = style,
    )
}

private fun buildSegmented(text: String, controller: LazyTextController): AnnotatedString = buildAnnotatedString {
    if (text.isEmpty()) return@buildAnnotatedString
    var segStart = 0
    var segFamily: FontFamily? = null
    var firstChar = true
    var i = 0
    while (i < text.length) {
        val cp = text.codePointAtCompat(i)
        val charLen = if (cp >= 0x10000) 2 else 1
        val f = controller.fontFamilyForCodepoint(cp)
        if (firstChar) {
            segFamily = f
            firstChar = false
        } else if (f != segFamily) {
            emitSegment(text, segStart, i, segFamily)
            segStart = i
            segFamily = f
        }
        i += charLen
    }
    emitSegment(text, segStart, text.length, segFamily)
}

private fun AnnotatedString.Builder.emitSegment(
    text: String,
    start: Int,
    end: Int,
    family: FontFamily?,
) {
    if (start >= end) return
    val slice = text.substring(start, end)
    if (family != null) {
        withStyle(SpanStyle(fontFamily = family)) { append(slice) }
    } else {
        append(slice)
    }
}

internal fun String.codePointAtCompat(index: Int): Int {
    val high = this[index]
    if (high.isHighSurrogate() && index + 1 < length) {
        val low = this[index + 1]
        if (low.isLowSurrogate()) {
            return ((high.code - 0xD800) shl 10) + (low.code - 0xDC00) + 0x10000
        }
    }
    return high.code
}
