// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.TextFieldColors
import top.yukonga.miuix.kmp.basic.TextFieldDefaults
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A [TextField] styled for translucent glass surfaces such as [GlassDialog] and
 * [GlassBottomSheet].
 *
 * Uses a translucent tonal fill and higher-contrast label color from
 * [GlassTextFieldDefaults.textFieldColors] so the input field remains legible over frosted glass.
 */
@Composable
fun GlassTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    insideMargin: DpSize = GlassTextFieldDefaults.InsideMargin,
    colors: TextFieldColors = GlassTextFieldDefaults.textFieldColors(),
    cornerRadius: Dp = GlassTextFieldDefaults.CornerRadius,
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
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = SolidColor(colors.borderColor),
    outputTransformation: OutputTransformation? = null,
    scrollState: ScrollState = rememberScrollState(),
) {
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
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        outputTransformation = outputTransformation,
        scrollState = scrollState,
    )
}

/**
 * A [TextField] styled for translucent glass surfaces such as [GlassDialog] and
 * [GlassBottomSheet].
 */
@Composable
fun GlassTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    insideMargin: DpSize = GlassTextFieldDefaults.InsideMargin,
    colors: TextFieldColors = GlassTextFieldDefaults.textFieldColors(),
    cornerRadius: Dp = GlassTextFieldDefaults.CornerRadius,
    label: String = "",
    useLabelAsPlaceholder: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MiuixTheme.textStyles.main,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = SolidColor(colors.borderColor),
) {
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
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
    )
}

/**
 * A [TextField] styled for translucent glass surfaces such as [GlassDialog] and
 * [GlassBottomSheet].
 */
@Composable
fun GlassTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    insideMargin: DpSize = GlassTextFieldDefaults.InsideMargin,
    colors: TextFieldColors = GlassTextFieldDefaults.textFieldColors(),
    cornerRadius: Dp = GlassTextFieldDefaults.CornerRadius,
    label: String = "",
    useLabelAsPlaceholder: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MiuixTheme.textStyles.main,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = SolidColor(colors.borderColor),
) {
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
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
    )
}

/** Contains default values used by [GlassTextField]. */
object GlassTextFieldDefaults {
    /** The default corner radius of the [GlassTextField]. */
    val CornerRadius = TextFieldDefaults.CornerRadius

    /** The default inside margin of the [GlassTextField]. */
    val InsideMargin = TextFieldDefaults.InsideMargin

    /**
     * Default colors used by [GlassTextField] over translucent glass surfaces.
     *
     * @param backgroundColor The background color of the [GlassTextField].
     * @param labelColor The color of the floating / inline label.
     * @param borderColor The color of the border when the [GlassTextField] is focused.
     */
    @Composable
    fun textFieldColors(
        backgroundColor: Color = defaultBackgroundColor(),
        labelColor: Color = defaultLabelColor(),
        borderColor: Color = MiuixTheme.colorScheme.primary,
    ): TextFieldColors = remember(backgroundColor, labelColor, borderColor) {
        TextFieldColors(
            backgroundColor = backgroundColor,
            labelColor = labelColor,
            borderColor = borderColor,
        )
    }

    @Composable
    private fun defaultBackgroundColor(): Color {
        val scheme = MiuixTheme.colorScheme
        val isDark = scheme.background.luminance() < 0.5f
        return scheme.onBackground.copy(alpha = if (isDark) 36f / 255f else 15f / 255f)
    }

    @Composable
    private fun defaultLabelColor(): Color {
        val scheme = MiuixTheme.colorScheme
        val isDark = scheme.background.luminance() < 0.5f
        return scheme.onBackground.copy(alpha = if (isDark) 0.5f else 0.45f)
    }
}
