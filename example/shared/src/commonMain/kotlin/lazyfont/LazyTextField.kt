// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package lazyfont

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.TextFieldColors
import top.yukonga.miuix.kmp.basic.TextFieldDefaults
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A drop-in wrapper around the Miuix [TextField] (String overload) that feeds the typed text into
 * [LazyTextController] so the matching font subsets are fetched on demand, then overlays the loaded
 * font families per codepoint through a [VisualTransformation].
 *
 * Using a [VisualTransformation] (instead of rebuilding the field) is what keeps the cursor and
 * focus intact: only the *displayed* glyphs are restyled, never the stored value. When a new subset
 * finishes loading, [LazyTextController.revision] bumps, a fresh transformation instance is built,
 * and the field re-shapes to pick up the newly available glyphs.
 *
 * On non-web platforms [LocalLazyTextController] is null, so this degrades to a plain Miuix
 * [TextField] call with no added behavior.
 */
@Composable
fun LazyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    insideMargin: DpSize = TextFieldDefaults.InsideMargin,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    cornerRadius: Dp = TextFieldDefaults.CornerRadius,
    label: String = "",
    useLabelAsPlaceholder: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MiuixTheme.textStyles.main,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val controller = LocalLazyTextController.current
    if (controller == null) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            insideMargin = insideMargin,
            colors = colors,
            cornerRadius = cornerRadius,
            label = label,
            useLabelAsPlaceholder = useLabelAsPlaceholder,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
        )
        return
    }
    LaunchedEffect(value) { controller.requestText(value) }
    val effectiveTransformation = remember(controller.revision, visualTransformation) {
        lazyFontVisualTransformation(controller, visualTransformation)
    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        insideMargin = insideMargin,
        colors = colors,
        cornerRadius = cornerRadius,
        label = label,
        useLabelAsPlaceholder = useLabelAsPlaceholder,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = effectiveTransformation,
    )
}

/**
 * [TextFieldValue] overload of [LazyTextField]. Identical lazy-font behavior to the String overload:
 * the loaded subsets are overlaid through a [VisualTransformation], so the cursor and selection
 * carried by [TextFieldValue] stay intact.
 */
@Composable
fun LazyTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    insideMargin: DpSize = TextFieldDefaults.InsideMargin,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    cornerRadius: Dp = TextFieldDefaults.CornerRadius,
    label: String = "",
    useLabelAsPlaceholder: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MiuixTheme.textStyles.main,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val controller = LocalLazyTextController.current
    if (controller == null) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            insideMargin = insideMargin,
            colors = colors,
            cornerRadius = cornerRadius,
            label = label,
            useLabelAsPlaceholder = useLabelAsPlaceholder,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
        )
        return
    }
    LaunchedEffect(value.text) { controller.requestText(value.text) }
    val effectiveTransformation = remember(controller.revision, visualTransformation) {
        lazyFontVisualTransformation(controller, visualTransformation)
    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        insideMargin = insideMargin,
        colors = colors,
        cornerRadius = cornerRadius,
        label = label,
        useLabelAsPlaceholder = useLabelAsPlaceholder,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = effectiveTransformation,
    )
}

/**
 * [TextFieldState] overload of [LazyTextField]. A state-based field exposes no [VisualTransformation]
 * to restyle glyphs per run, so this feeds the typed text into [LazyTextController] (to fetch the
 * subsets), [preloads] each resulting family into the shared resolver so it joins the app-wide glyph
 * fallback, and then nudges [textStyle] by an imperceptible letter-spacing epsilon. A state-based
 * field only re-shapes when a layout input changes; toggling that epsilon is the cheapest invisible
 * way to force the re-shape that reveals the just-registered glyphs, without recreating the field —
 * so focus and cursor are preserved.
 *
 * [preloads]: androidx.compose.ui.text.font.FontFamily.Resolver.preload
 */
@Composable
fun LazyTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    insideMargin: DpSize = TextFieldDefaults.InsideMargin,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
    cornerRadius: Dp = TextFieldDefaults.CornerRadius,
    label: String = "",
    useLabelAsPlaceholder: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    inputTransformation: InputTransformation? = null,
    textStyle: TextStyle = MiuixTheme.textStyles.main,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    outputTransformation: OutputTransformation? = null,
) {
    val controller = LocalLazyTextController.current
    if (controller == null) {
        TextField(
            state = state,
            modifier = modifier,
            insideMargin = insideMargin,
            colors = colors,
            cornerRadius = cornerRadius,
            label = label,
            useLabelAsPlaceholder = useLabelAsPlaceholder,
            enabled = enabled,
            readOnly = readOnly,
            inputTransformation = inputTransformation,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            onKeyboardAction = onKeyboardAction,
            lineLimits = lineLimits,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            outputTransformation = outputTransformation,
        )
        return
    }
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val registeredRevision = remember { mutableIntStateOf(0) }
    val preloaded = remember { mutableSetOf<FontFamily>() }
    LaunchedEffect(state) {
        snapshotFlow { state.text.toString() }.collect { controller.requestText(it) }
    }
    LaunchedEffect(state, fontFamilyResolver) {
        snapshotFlow { controller.revision to state.text.toString() }.collect { (_, text) ->
            var registeredNew = false
            distinctLoadedFamilies(text, controller).forEach { family ->
                if (preloaded.add(family)) {
                    runCatching { fontFamilyResolver.preload(family) }
                    registeredNew = true
                }
            }
            if (registeredNew) registeredRevision.intValue++
        }
    }
    val effectiveTextStyle = remember(textStyle, registeredRevision.intValue) {
        if (registeredRevision.intValue % 2 == 0) {
            textStyle
        } else {
            textStyle.copy(letterSpacing = textStyle.letterSpacing.reshapeNudge())
        }
    }
    TextField(
        state = state,
        modifier = modifier,
        insideMargin = insideMargin,
        colors = colors,
        cornerRadius = cornerRadius,
        label = label,
        useLabelAsPlaceholder = useLabelAsPlaceholder,
        enabled = enabled,
        readOnly = readOnly,
        inputTransformation = inputTransformation,
        textStyle = effectiveTextStyle,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        outputTransformation = outputTransformation,
    )
}

/**
 * Wrapper around the Miuix search-bar [InputField]. Like the [TextFieldState] overload of
 * [LazyTextField], this field has no per-run styling hook, so it feeds the query into
 * [LazyTextController], preloads the resulting families into the shared fallback, and nudges
 * [textStyle] by an imperceptible epsilon to force the field to re-shape once a subset registers.
 * Non-web platforms pass straight through to the plain [InputField].
 */
@Composable
fun LazyInputField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    enabled: Boolean = true,
    textStyle: TextStyle? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
) {
    val controller = LocalLazyTextController.current
    if (controller == null) {
        InputField(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = modifier,
            label = label,
            enabled = enabled,
            textStyle = textStyle,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            interactionSource = interactionSource,
        )
        return
    }
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val registeredRevision = remember { mutableIntStateOf(0) }
    val preloaded = remember { mutableSetOf<FontFamily>() }
    LaunchedEffect(query) { controller.requestText(query) }
    LaunchedEffect(query, fontFamilyResolver) {
        snapshotFlow { controller.revision }.collect {
            var registeredNew = false
            distinctLoadedFamilies(query, controller).forEach { family ->
                if (preloaded.add(family)) {
                    runCatching { fontFamilyResolver.preload(family) }
                    registeredNew = true
                }
            }
            if (registeredNew) registeredRevision.intValue++
        }
    }
    val effectiveTextStyle = remember(textStyle, registeredRevision.intValue) {
        if (registeredRevision.intValue % 2 == 0) {
            textStyle
        } else {
            (textStyle ?: TextStyle.Default).copy(
                letterSpacing = (textStyle?.letterSpacing ?: TextUnit.Unspecified).reshapeNudge(),
            )
        }
    }
    InputField(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier,
        label = label,
        enabled = enabled,
        textStyle = effectiveTextStyle,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        interactionSource = interactionSource,
    )
}

/**
 * Wraps [inner] so that, after its own filtering, every codepoint covered by a loaded subset gets a
 * [SpanStyle] carrying that subset's [FontFamily]. Offsets are untouched, so the cursor mapping
 * stays identity-compatible with whatever [inner] produced.
 */
private fun lazyFontVisualTransformation(
    controller: LazyTextController,
    inner: VisualTransformation,
): VisualTransformation = VisualTransformation { original ->
    val transformed = inner.filter(original)
    val styled = applyLazyFontSpans(transformed.text, controller)
    TransformedText(styled, transformed.offsetMapping)
}

/**
 * Returns this letter-spacing offset by a sub-pixel epsilon, preserving its unit. Used purely to
 * produce a layout-distinct [TextStyle] that forces a state-based field to re-shape; the shift is
 * far below one pixel, so it is visually a no-op (Miuix text styles leave letterSpacing unspecified).
 */
private fun TextUnit.reshapeNudge(): TextUnit = when {
    isUnspecified -> 0.01.sp
    type == TextUnitType.Em -> TextUnit(value + 0.0005f, TextUnitType.Em)
    else -> TextUnit(value + 0.01f, TextUnitType.Sp)
}

/** Distinct, already-loaded families covering [text]'s codepoints, in first-seen order. */
private fun distinctLoadedFamilies(text: String, controller: LazyTextController): List<FontFamily> {
    if (text.isEmpty()) return emptyList()
    val families = LinkedHashSet<FontFamily>()
    var i = 0
    while (i < text.length) {
        val cp = text.codePointAtCompat(i)
        i += if (cp >= 0x10000) 2 else 1
        controller.fontFamilyForCodepoint(cp)?.let { families.add(it) }
    }
    return families.toList()
}

/** Overlays per-run font families on [text] without disturbing its existing spans or length. */
private fun applyLazyFontSpans(text: AnnotatedString, controller: LazyTextController): AnnotatedString {
    val raw = text.text
    if (raw.isEmpty()) return text
    return buildAnnotatedString {
        append(text)
        var runStart = 0
        var runFamily: FontFamily? = null
        var firstChar = true
        var i = 0
        while (i < raw.length) {
            val cp = raw.codePointAtCompat(i)
            val charLen = if (cp >= 0x10000) 2 else 1
            val family = controller.fontFamilyForCodepoint(cp)
            if (firstChar) {
                runFamily = family
                firstChar = false
            } else if (family !== runFamily) {
                if (runFamily != null) addStyle(SpanStyle(fontFamily = runFamily), runStart, i)
                runStart = i
                runFamily = family
            }
            i += charLen
        }
        if (!firstChar && runFamily != null) {
            addStyle(SpanStyle(fontFamily = runFamily), runStart, raw.length)
        }
    }
}
