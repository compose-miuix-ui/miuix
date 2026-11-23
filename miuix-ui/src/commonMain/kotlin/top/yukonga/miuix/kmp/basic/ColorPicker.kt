// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceAtLeast
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.fastRoundToInt
import top.yukonga.miuix.kmp.color.api.toHsv
import top.yukonga.miuix.kmp.color.api.toOkLab
import top.yukonga.miuix.kmp.color.api.toOkLch
import top.yukonga.miuix.kmp.color.core.Transforms
import top.yukonga.miuix.kmp.color.space.Hsv
import top.yukonga.miuix.kmp.color.space.OkHsv
import top.yukonga.miuix.kmp.color.space.OkLab
import top.yukonga.miuix.kmp.color.space.OkLch
import kotlin.math.ceil
import kotlin.math.min

/**
 * A [ColorPicker] component with Miuix style that supports multiple color spaces.
 *
 * @param color The color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param modifier The modifier to be applied to the color picker.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 * @param colorSpace The color space to use for the picker.
 */
@Composable
fun ColorPicker(
    color: Color,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    colorSpace: ColorSpace = ColorSpace.HSV,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    when (colorSpace) {
        ColorSpace.OKHSV -> {
            OkHsvColorPicker(
                color = color,
                onColorChanged = currentOnColorChanged,
                showPreview = showPreview,
                hapticEffect = hapticEffect,
                modifier = modifier,
            )
        }

        ColorSpace.OKLAB -> {
            OkLabColorPicker(
                color = color,
                onColorChanged = currentOnColorChanged,
                showPreview = showPreview,
                hapticEffect = hapticEffect,
                modifier = modifier,
            )
        }

        ColorSpace.OKLCH -> {
            OkLchColorPicker(
                color = color,
                onColorChanged = currentOnColorChanged,
                showPreview = showPreview,
                hapticEffect = hapticEffect,
                modifier = modifier,
            )
        }

        else -> {
            HsvColorPicker(
                color = color,
                onColorChanged = currentOnColorChanged,
                showPreview = showPreview,
                hapticEffect = hapticEffect,
                modifier = modifier,
            )
        }
    }
}

/**
 * A [HsvColorPicker] component with Miuix style using HSV color space.
 *
 * @param color The color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param modifier The modifier to be applied to the color picker.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 */
@Composable
fun HsvColorPicker(
    color: Color,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    val currentExternalColor by rememberUpdatedState(color)
    val capsuleShape = CircleShape

    // Initialize basic values, execute only once.
    val hsv = remember { color.toHsv() }
    var currentHue by remember { mutableFloatStateOf(hsv.h) }
    var currentSaturation by remember { mutableFloatStateOf(hsv.s / 100f) }
    var currentValue by remember { mutableFloatStateOf(hsv.v / 100f) }
    var currentAlpha by remember { mutableFloatStateOf(color.alpha) }

    var lastAppliedExternalColorArgb by remember {
        mutableStateOf(color.toArgb())
    }
    val selectedColorState = remember {
        derivedStateOf {
            Hsv(
                h = currentHue,
                s = currentSaturation * 100f,
                v = currentValue * 100f,
            ).toColor(currentAlpha)
        }
    }

    SideEffect {
        val externalArgb = color.toArgb()
        val internalArgb = selectedColorState.value.toArgb()

        if (
            externalArgb != lastAppliedExternalColorArgb &&
            externalArgb != internalArgb
        ) {
            lastAppliedExternalColorArgb = externalArgb
            val nextHsv = color.toHsv()
            currentHue = nextHsv.h
            currentSaturation = nextHsv.s / 100f
            currentValue = nextHsv.v / 100f
            currentAlpha = color.alpha
        }
    }

    val notifyUserColorChanged = remember {
        {
            val newColor = selectedColorState.value
            if (newColor.toArgb() != currentExternalColor.toArgb()) {
                currentOnColorChanged(newColor)
            }
        }
    }
    val hueProvider = remember { { currentHue / 360f } }
    val onHueChanged = remember {
        { newHue: Float ->
            currentHue = newHue * 360f
            notifyUserColorChanged()
        }
    }
    val saturationProvider = remember { { currentSaturation } }
    val onSaturationChanged = remember {
        { it: Float ->
            currentSaturation = it
            notifyUserColorChanged()
        }
    }
    val valueProvider = remember { { currentValue } }
    val onValueChanged = remember {
        { it: Float ->
            currentValue = it
            notifyUserColorChanged()
        }
    }
    val alphaProvider = remember { { currentAlpha } }
    val onAlphaChanged = remember {
        { it: Float ->
            currentAlpha = it
            notifyUserColorChanged()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Color preview
        if (showPreview) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .clip(capsuleShape)
                    .drawBehind { drawRect(selectedColorState.value) },
            )
        }

        // Hue selection
        HsvHueSliderImpl(
            hueNormalizedProvider = hueProvider,
            onHueChanged = onHueChanged,
            hapticEffect = hapticEffect,
        )

        // Saturation selection
        HsvSaturationSliderImpl(
            currentHue = currentHue,
            saturationProvider = saturationProvider,
            onSaturationChanged = onSaturationChanged,
            hapticEffect = hapticEffect,
        )

        // Value selection
        HsvValueSliderImpl(
            currentHue = currentHue,
            currentSaturation = currentSaturation,
            valueProvider = valueProvider,
            onValueChanged = onValueChanged,
            hapticEffect = hapticEffect,
        )

        // Alpha selection
        HsvAlphaSliderImpl(
            currentHue = currentHue,
            currentSaturation = currentSaturation,
            currentValue = currentValue,
            alphaProvider = alphaProvider,
            onAlphaChanged = onAlphaChanged,
            hapticEffect = hapticEffect,
        )
    }
}

/**
 * A [HsvHueSlider] component for selecting the hue of a color using pure HSV colors.
 *
 * @param currentHue The current hue value (0-360).
 * @param onHueChanged The callback to be called when the hue changes (0-1).
 * @param hapticEffect The haptic effect of the [HsvHueSlider].
 */
@Composable
fun HsvHueSlider(
    currentHue: Float,
    onHueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentValue by rememberUpdatedState(currentHue / 360f)
    val hueNormalizedProvider = remember { { currentValue } }
    HsvHueSliderImpl(
        hueNormalizedProvider = hueNormalizedProvider,
        onHueChanged = onHueChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun HsvHueSliderImpl(
    hueNormalizedProvider: () -> Float,
    onHueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val hsvHueColors = remember {
        Transforms.generateHsvHueColors()
    }

    ColorSlider(
        valueProvider = hueNormalizedProvider,
        onValueChanged = onHueChanged,
        drawBrushColors = hsvHueColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [HsvSaturationSlider] component for selecting the saturation of a color.
 *
 * @param currentHue The current hue value.
 * @param currentSaturation The current saturation value.
 * @param onSaturationChanged The callback to be called when the saturation changes.
 * @param hapticEffect The haptic effect of the [HsvSaturationSlider].
 */
@Composable
fun HsvSaturationSlider(
    currentHue: Float,
    currentSaturation: Float,
    onSaturationChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentValue by rememberUpdatedState(currentSaturation)
    val saturationProvider = remember { { currentValue } }
    HsvSaturationSliderImpl(
        currentHue = currentHue,
        saturationProvider = saturationProvider,
        onSaturationChanged = onSaturationChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun HsvSaturationSliderImpl(
    currentHue: Float,
    saturationProvider: () -> Float,
    onSaturationChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val saturationColors = remember(currentHue) {
        listOf(
            Hsv(currentHue, 0f, 100f).toColor(1f),
            Hsv(currentHue, 100f, 100f).toColor(1f),
        )
    }
    ColorSlider(
        valueProvider = saturationProvider,
        onValueChanged = onSaturationChanged,
        drawBrushColors = saturationColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [HsvValueSlider] component for selecting the value/brightness of a color.
 *
 * @param currentHue The current hue value.
 * @param currentSaturation The current saturation value.
 * @param currentValue The current value value.
 * @param onValueChanged The callback to be called when the value changes.
 * @param hapticEffect The haptic effect of the [HsvValueSlider].
 */
@Composable
fun HsvValueSlider(
    currentHue: Float,
    currentSaturation: Float,
    currentValue: Float,
    onValueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentValue)
    val valueProvider = remember { { currentVal } }
    HsvValueSliderImpl(
        currentHue = currentHue,
        currentSaturation = currentSaturation,
        valueProvider = valueProvider,
        onValueChanged = onValueChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun HsvValueSliderImpl(
    currentHue: Float,
    currentSaturation: Float,
    valueProvider: () -> Float,
    onValueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val valueColors = remember(currentHue, currentSaturation) {
        listOf(Color.Black, Hsv(currentHue, currentSaturation * 100f, 100f).toColor())
    }
    ColorSlider(
        valueProvider = valueProvider,
        onValueChanged = onValueChanged,
        drawBrushColors = valueColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [HsvAlphaSlider] component for selecting the alpha of a color.
 *
 * @param currentHue The current hue value.
 * @param currentSaturation The current saturation value.
 * @param currentValue The current value value.
 * @param currentAlpha The current alpha value.
 * @param onAlphaChanged The callback to be called when the alpha changes.
 * @param hapticEffect The haptic effect of the [HsvAlphaSlider].
 */
@Composable
fun HsvAlphaSlider(
    currentHue: Float,
    currentSaturation: Float,
    currentValue: Float,
    currentAlpha: Float,
    onAlphaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentAlpha)
    val alphaProvider = remember { { currentVal } }
    HsvAlphaSliderImpl(
        currentHue = currentHue,
        currentSaturation = currentSaturation,
        currentValue = currentValue,
        alphaProvider = alphaProvider,
        onAlphaChanged = onAlphaChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
internal fun HsvAlphaSliderImpl(
    currentHue: Float,
    currentSaturation: Float,
    currentValue: Float,
    alphaProvider: () -> Float,
    onAlphaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val alphaColors = remember(currentHue, currentSaturation, currentValue) {
        val baseColor = Hsv(currentHue, currentSaturation * 100f, currentValue * 100f).toColor()
        listOf(baseColor.copy(alpha = 0f), baseColor.copy(alpha = 1f))
    }

    ColorSlider(
        valueProvider = alphaProvider,
        onValueChanged = onAlphaChanged,
        drawBrushColors = alphaColors,
        modifier = Modifier
            .fillMaxWidth()
            .drawCheckerboard(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkHsvColorPicker] component with Miuix style using OkHSV color space based on OkLab.
 * OkHSV provides better perceptual uniformity than traditional HSV.
 *
 * @param color The color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param modifier The modifier to be applied to the color picker.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 */
@Composable
fun OkHsvColorPicker(
    color: Color,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    val currentExternalColor by rememberUpdatedState(color)
    val capsuleShape = CircleShape

    // Initialize basic values, execute only once.
    val okhsv = remember { Transforms.colorToOkhsv(color) }
    var currentH by remember { mutableFloatStateOf(okhsv[0]) }
    var currentS by remember { mutableFloatStateOf(okhsv[1]) }
    var currentV by remember { mutableFloatStateOf(okhsv[2]) }
    var currentAlpha by remember { mutableFloatStateOf(color.alpha) }

    var lastAppliedExternalColorArgb by remember {
        mutableStateOf(color.toArgb())
    }
    val selectedColorState = remember {
        derivedStateOf {
            OkHsv(
                h = currentH,
                s = currentS,
                v = currentV,
            ).toColor(currentAlpha)
        }
    }

    SideEffect {
        val externalArgb = color.toArgb()
        val internalArgb = selectedColorState.value.toArgb()

        if (
            externalArgb != lastAppliedExternalColorArgb &&
            externalArgb != internalArgb
        ) {
            lastAppliedExternalColorArgb = externalArgb
            val nextOkhsv = Transforms.colorToOkhsv(color)
            currentH = nextOkhsv[0]
            currentS = nextOkhsv[1]
            currentV = nextOkhsv[2]
            currentAlpha = color.alpha
        }
    }

    val notifyUserColorChanged = remember {
        {
            val newColor = selectedColorState.value
            if (newColor.toArgb() != currentExternalColor.toArgb()) {
                currentOnColorChanged(newColor)
            }
        }
    }
    val hueProvider = remember { { currentH } }
    val onHueChanged = remember {
        { it: Float ->
            currentH = it
            notifyUserColorChanged()
        }
    }
    val saturationProvider = remember { { currentS } }
    val onSaturationChanged = remember {
        { it: Float ->
            currentS = it
            notifyUserColorChanged()
        }
    }
    val valueProvider = remember { { currentV } }
    val onValueChanged = remember {
        { it: Float ->
            currentV = it
            notifyUserColorChanged()
        }
    }
    val alphaProvider = remember { { currentAlpha } }
    val onAlphaChanged = remember {
        { it: Float ->
            currentAlpha = it
            notifyUserColorChanged()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Color preview
        if (showPreview) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .clip(capsuleShape)
                    .drawBehind { drawRect(selectedColorState.value) },
            )
        }

        // Hue selection (OkHSV)
        OkHsvHueSliderImpl(
            hueProvider = hueProvider,
            onHueChanged = onHueChanged,
            hapticEffect = hapticEffect,
        )

        // Saturation selection (OkHSV)
        OkHsvSaturationSliderImpl(
            currentH = currentH,
            saturationProvider = saturationProvider,
            onSaturationChanged = onSaturationChanged,
            hapticEffect = hapticEffect,
        )

        // Value selection (OkHSV)
        OkHsvValueSliderImpl(
            currentH = currentH,
            currentS = currentS,
            valueProvider = valueProvider,
            onValueChanged = onValueChanged,
            hapticEffect = hapticEffect,
        )

        // Alpha selection (OkHSV)
        OkHsvAlphaSliderImpl(
            currentH = currentH,
            currentS = currentS,
            currentV = currentV,
            alphaProvider = alphaProvider,
            onAlphaChanged = onAlphaChanged,
            hapticEffect = hapticEffect,
        )
    }
}

/**
 * A [OkHsvHueSlider] component for selecting the hue of a color using OkHSV color space.
 *
 * @param currentH The current hue value (0-1).
 * @param onHueChanged The callback to be called when the hue changes.
 * @param hapticEffect The haptic effect of the [OkHsvHueSlider].
 */
@Composable
fun OkHsvHueSlider(
    currentH: Float,
    onHueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentH)
    val hueProvider = remember { { currentVal } }
    OkHsvHueSliderImpl(
        hueProvider = hueProvider,
        onHueChanged = onHueChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkHsvHueSliderImpl(
    hueProvider: () -> Float,
    onHueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val okHsvHueColors = remember {
        Transforms.generateOkHsvHueColors()
    }

    ColorSlider(
        valueProvider = hueProvider,
        onValueChanged = onHueChanged,
        drawBrushColors = okHsvHueColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkHsvSaturationSlider] component for selecting the saturation of a color using OkHSV.
 *
 * @param currentH The current hue value.
 * @param currentS The current saturation value.
 * @param onSaturationChanged The callback to be called when the saturation changes.
 * @param hapticEffect The haptic effect of the [OkHsvSaturationSlider].
 */
@Composable
fun OkHsvSaturationSlider(
    currentH: Float,
    currentS: Float,
    onSaturationChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentS)
    val saturationProvider = remember { { currentVal } }
    OkHsvSaturationSliderImpl(
        currentH = currentH,
        saturationProvider = saturationProvider,
        onSaturationChanged = onSaturationChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkHsvSaturationSliderImpl(
    currentH: Float,
    saturationProvider: () -> Float,
    onSaturationChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val saturationColors = remember(currentH) {
        listOf(
            Transforms.okhsvToColor(currentH, 0f, 1f, 1f),
            Transforms.okhsvToColor(currentH, 1f, 1f, 1f),
        )
    }

    ColorSlider(
        valueProvider = saturationProvider,
        onValueChanged = onSaturationChanged,
        drawBrushColors = saturationColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkHsvValueSlider] component for selecting the value/brightness of a color using OkHSV.
 *
 * @param currentH The current hue value.
 * @param currentS The current saturation value.
 * @param currentV The current value value.
 * @param onValueChanged The callback to be called when the value changes.
 * @param hapticEffect The haptic effect of the [OkHsvValueSlider].
 */
@Composable
fun OkHsvValueSlider(
    currentH: Float,
    currentS: Float,
    currentV: Float,
    onValueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentV)
    val valueProvider = remember { { currentVal } }
    OkHsvValueSliderImpl(
        currentH = currentH,
        currentS = currentS,
        valueProvider = valueProvider,
        onValueChanged = onValueChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkHsvValueSliderImpl(
    currentH: Float,
    currentS: Float,
    valueProvider: () -> Float,
    onValueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val valueColors = remember(currentH, currentS) {
        listOf(
            Transforms.okhsvToColor(currentH, currentS, 0f, 1f),
            Transforms.okhsvToColor(currentH, currentS, 1f, 1f),
        )
    }

    ColorSlider(
        valueProvider = valueProvider,
        onValueChanged = onValueChanged,
        drawBrushColors = valueColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkHsvAlphaSlider] component for selecting the alpha of a color using OkHSV.
 *
 * @param currentH The current hue value.
 * @param currentS The current saturation value.
 * @param currentV The current value value.
 * @param currentAlpha The current alpha value.
 * @param onAlphaChanged The callback to be called when the alpha changes.
 * @param hapticEffect The haptic effect of the [OkHsvAlphaSlider].
 */
@Composable
fun OkHsvAlphaSlider(
    currentH: Float,
    currentS: Float,
    currentV: Float,
    currentAlpha: Float,
    onAlphaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentAlpha)
    val alphaProvider = remember { { currentVal } }
    OkHsvAlphaSliderImpl(
        currentH = currentH,
        currentS = currentS,
        currentV = currentV,
        alphaProvider = alphaProvider,
        onAlphaChanged = onAlphaChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkHsvAlphaSliderImpl(
    currentH: Float,
    currentS: Float,
    currentV: Float,
    alphaProvider: () -> Float,
    onAlphaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val alphaColors = remember(currentH, currentS, currentV) {
        val baseColor = Transforms.okhsvToColor(currentH, currentS, currentV)
        listOf(baseColor.copy(alpha = 0f), baseColor.copy(alpha = 1f))
    }

    ColorSlider(
        valueProvider = alphaProvider,
        onValueChanged = onAlphaChanged,
        drawBrushColors = alphaColors,
        modifier = Modifier
            .fillMaxWidth()
            .drawCheckerboard(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkLabColorPicker] component with Miuix style using OkLab color space.
 *
 * @param color The color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param modifier The modifier to be applied to the color picker.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 */
@Composable
fun OkLabColorPicker(
    color: Color,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    val currentExternalColor by rememberUpdatedState(color)
    val capsuleShape = CircleShape

    // Initialize basic values, execute only once.
    val ok = remember { color.toOkLab() }
    var currentL by remember { mutableFloatStateOf(ok.l / 100f) }
    var currentA by remember { mutableFloatStateOf(((ok.a / 100f) * 0.4f)) }
    var currentB by remember { mutableFloatStateOf(((ok.b / 100f) * 0.4f)) }
    var currentAlpha by remember { mutableFloatStateOf(color.alpha) }

    var lastAppliedExternalColorArgb by remember {
        mutableStateOf(color.toArgb())
    }
    val selectedColorState = remember {
        derivedStateOf {
            OkLab(
                l = currentL * 100f,
                a = (currentA / 0.4f) * 100f,
                b = (currentB / 0.4f) * 100f,
            ).toColor(currentAlpha)
        }
    }

    SideEffect {
        val externalArgb = color.toArgb()
        val internalArgb = selectedColorState.value.toArgb()

        if (
            externalArgb != lastAppliedExternalColorArgb &&
            externalArgb != internalArgb
        ) {
            lastAppliedExternalColorArgb = externalArgb
            val nextOk = color.toOkLab()
            currentL = nextOk.l / 100f
            currentA = (nextOk.a / 100f) * 0.4f
            currentB = (nextOk.b / 100f) * 0.4f
            currentAlpha = color.alpha
        }
    }

    val notifyUserColorChanged = remember {
        {
            val newColor = selectedColorState.value
            if (newColor.toArgb() != currentExternalColor.toArgb()) {
                currentOnColorChanged(newColor)
            }
        }
    }
    val lProvider = remember { { currentL } }
    val onLChanged = remember {
        { it: Float ->
            currentL = it
            notifyUserColorChanged()
        }
    }
    val aNormalizedProvider = remember { { (currentA + 0.3f) / 0.6f } }
    val onAChanged = remember {
        { it: Float ->
            currentA = it
            notifyUserColorChanged()
        }
    }
    val bNormalizedProvider = remember { { (currentB + 0.3f) / 0.6f } }
    val onBChanged = remember {
        { it: Float ->
            currentB = it
            notifyUserColorChanged()
        }
    }
    val alphaProvider = remember { { currentAlpha } }
    val onAlphaChanged = remember {
        { it: Float ->
            currentAlpha = it
            notifyUserColorChanged()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Color preview
        if (showPreview) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .clip(capsuleShape)
                    .drawBehind { drawRect(selectedColorState.value) },
            )
        }

        // Lightness selection
        OkLabLightnessSliderImpl(
            currentA = currentA,
            currentB = currentB,
            lProvider = lProvider,
            onLightnessChanged = onLChanged,
            hapticEffect = hapticEffect,
        )

        // A channel selection (green-red axis)
        OkLabAChannelSliderImpl(
            currentL = currentL,
            currentB = currentB,
            aNormalizedProvider = aNormalizedProvider,
            onAChanged = onAChanged,
            hapticEffect = hapticEffect,
        )

        // B channel selection (blue-yellow axis)
        OkLabBChannelSliderImpl(
            currentL = currentL,
            currentA = currentA,
            bNormalizedProvider = bNormalizedProvider,
            onBChanged = onBChanged,
            hapticEffect = hapticEffect,
        )

        // Alpha selection
        OkLabAlphaSliderImpl(
            currentL = currentL,
            currentA = currentA,
            currentB = currentB,
            alphaProvider = alphaProvider,
            onAlphaChanged = onAlphaChanged,
            hapticEffect = hapticEffect,
        )
    }
}

/**
 * A [OkLchColorPicker] component with Miuix style using OkLch color space.
 *
 * @param color The color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param modifier The modifier to be applied to the color picker.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 */
@Composable
fun OkLchColorPicker(
    color: Color,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    val currentExternalColor by rememberUpdatedState(color)
    val capsuleShape = CircleShape

    // Initialize basic values, execute only once.
    val oklch = remember { color.toOkLch() }
    var currentL by remember { mutableFloatStateOf(oklch.l / 100f) } // 0..1
    var currentC by remember { mutableFloatStateOf(oklch.c / 100f) } // proportion 0..1 (scaled to 0..0.4 internally)
    var currentH by remember { mutableFloatStateOf(oklch.h / 360f) } // normalized 0..1 (scaled to 360)
    var currentAlpha by remember { mutableFloatStateOf(color.alpha) }

    var lastAppliedExternalColorArgb by remember {
        mutableStateOf(color.toArgb())
    }
    val selectedColorState = remember {
        derivedStateOf {
            OkLch(
                l = currentL * 100f,
                c = currentC * 100f,
                h = currentH * 360f,
            ).toColor(currentAlpha)
        }
    }

    SideEffect {
        val externalArgb = color.toArgb()
        val internalArgb = selectedColorState.value.toArgb()

        if (
            externalArgb != lastAppliedExternalColorArgb &&
            externalArgb != internalArgb
        ) {
            lastAppliedExternalColorArgb = externalArgb
            val nextOklch = color.toOkLch()
            currentL = nextOklch.l / 100f
            currentC = nextOklch.c / 100f
            currentH = nextOklch.h / 360f
            currentAlpha = color.alpha
        }
    }

    val notifyUserColorChanged = remember {
        {
            val newColor = selectedColorState.value
            if (newColor.toArgb() != currentExternalColor.toArgb()) {
                currentOnColorChanged(newColor)
            }
        }
    }
    val hueProvider = remember { { currentH } }
    val onHueChanged = remember {
        { it: Float ->
            currentH = it
            notifyUserColorChanged()
        }
    }
    val lightnessProvider = remember { { currentL } }
    val onLightnessChanged = remember {
        { it: Float ->
            currentL = it
            notifyUserColorChanged()
        }
    }
    val chromaProvider = remember { { currentC } }
    val onChromaChanged = remember {
        { it: Float ->
            currentC = it
            notifyUserColorChanged()
        }
    }
    val alphaProvider = remember { { currentAlpha } }
    val onAlphaChanged = remember {
        { it: Float ->
            currentAlpha = it
            notifyUserColorChanged()
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Color preview
        if (showPreview) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .clip(capsuleShape)
                    .drawBehind { drawRect(selectedColorState.value) },
            )
        }

        // Hue selection
        OkLchHueSliderImpl(
            currentL = currentL,
            currentC = currentC,
            hueProvider = hueProvider,
            onHueChanged = onHueChanged,
            hapticEffect = hapticEffect,
        )

        // Lightness selection
        OkLchLightnessSliderImpl(
            currentC = currentC,
            currentH = currentH,
            lightnessProvider = lightnessProvider,
            onLightnessChanged = onLightnessChanged,
            hapticEffect = hapticEffect,
        )

        // Chroma selection
        OkLchChromaSliderImpl(
            currentL = currentL,
            currentH = currentH,
            chromaProvider = chromaProvider,
            onChromaChanged = onChromaChanged,
            hapticEffect = hapticEffect,
        )

        // Alpha selection
        OkLchAlphaSliderImpl(
            currentL = currentL,
            currentC = currentC,
            currentH = currentH,
            alphaProvider = alphaProvider,
            onAlphaChanged = onAlphaChanged,
            hapticEffect = hapticEffect,
        )
    }
}

@Composable
fun OkLchLightnessSlider(
    currentL: Float,
    currentC: Float,
    currentH: Float,
    onLightnessChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentL)
    val lightnessProvider = remember { { currentVal } }
    OkLchLightnessSliderImpl(
        currentC = currentC,
        currentH = currentH,
        lightnessProvider = lightnessProvider,
        onLightnessChanged = onLightnessChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkLchLightnessSliderImpl(
    currentC: Float,
    currentH: Float,
    lightnessProvider: () -> Float,
    onLightnessChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val hDeg = currentH * 360f
    val cInternal = currentC * 0.4f
    val colors = remember(currentC, currentH) {
        listOf(
            Transforms.oklchToColor(0f, cInternal, hDeg, 1f),
            Transforms.oklchToColor(1f, cInternal, hDeg, 1f),
        )
    }

    ColorSlider(
        valueProvider = lightnessProvider,
        onValueChanged = onLightnessChanged,
        drawBrushColors = colors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

@Composable
fun OkLchChromaSlider(
    currentL: Float,
    currentC: Float,
    currentH: Float,
    onChromaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentC)
    val chromaProvider = remember { { currentVal } }
    OkLchChromaSliderImpl(
        currentL = currentL,
        currentH = currentH,
        chromaProvider = chromaProvider,
        onChromaChanged = onChromaChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkLchChromaSliderImpl(
    currentL: Float,
    currentH: Float,
    chromaProvider: () -> Float,
    onChromaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val hDeg = currentH * 360f
    val colors = remember(currentL, currentH) {
        listOf(
            Transforms.oklchToColor(currentL, 0f, hDeg, 1f),
            Transforms.oklchToColor(currentL, 0.4f, hDeg, 1f),
        )
    }

    ColorSlider(
        valueProvider = chromaProvider,
        onValueChanged = onChromaChanged,
        drawBrushColors = colors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

@Composable
fun OkLchHueSlider(
    currentL: Float,
    currentC: Float,
    currentH: Float,
    onHueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentH)
    val hueProvider = remember { { currentVal } }
    OkLchHueSliderImpl(
        currentL = currentL,
        currentC = currentC,
        hueProvider = hueProvider,
        onHueChanged = onHueChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkLchHueSliderImpl(
    currentL: Float,
    currentC: Float,
    hueProvider: () -> Float,
    onHueChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val colors = remember(currentL, currentC) {
        Transforms.generateOkLchHueColors(currentL, currentC)
    }

    ColorSlider(
        valueProvider = hueProvider,
        onValueChanged = onHueChanged,
        drawBrushColors = colors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

@Composable
fun OkLchAlphaSlider(
    currentL: Float,
    currentC: Float,
    currentH: Float,
    currentAlpha: Float,
    onAlphaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentAlpha)
    val alphaProvider = remember { { currentVal } }
    OkLchAlphaSliderImpl(
        currentL = currentL,
        currentC = currentC,
        currentH = currentH,
        alphaProvider = alphaProvider,
        onAlphaChanged = onAlphaChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkLchAlphaSliderImpl(
    currentL: Float,
    currentC: Float,
    currentH: Float,
    alphaProvider: () -> Float,
    onAlphaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val hDeg = currentH * 360f
    val cInternal = currentC * 0.4f
    val colors = remember(currentL, currentC, currentH) {
        val opaque = Transforms.oklchToColor(currentL, cInternal, hDeg, 1f)
        val transparent = Color(opaque.red, opaque.green, opaque.blue, 0f)
        listOf(transparent, opaque)
    }

    ColorSlider(
        valueProvider = alphaProvider,
        onValueChanged = onAlphaChanged,
        drawBrushColors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .drawCheckerboard(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkLabLightnessSlider] component for selecting the lightness (L) of a color in OkLab space.
 */
@Composable
fun OkLabLightnessSlider(
    currentL: Float,
    currentA: Float,
    currentB: Float,
    onLightnessChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentL)
    val lProvider = remember { { currentVal } }
    OkLabLightnessSliderImpl(
        currentA = currentA,
        currentB = currentB,
        lProvider = lProvider,
        onLightnessChanged = onLightnessChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkLabLightnessSliderImpl(
    currentA: Float,
    currentB: Float,
    lProvider: () -> Float,
    onLightnessChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val lightnessColors = remember(currentA, currentB) {
        val steps = 7
        (0..steps).map { i ->
            val l = i.toFloat() / steps.toFloat()
            OkLab(
                l = l * 100f,
                a = (currentA / 0.4f) * 100f,
                b = (currentB / 0.4f) * 100f,
            ).toColor()
        }
    }
    ColorSlider(
        valueProvider = lProvider,
        onValueChanged = onLightnessChanged,
        drawBrushColors = lightnessColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkLabAChannelSlider] component for selecting the A channel (green-red axis) of a color in OkLab space.
 */
@Composable
fun OkLabAChannelSlider(
    currentL: Float,
    currentA: Float,
    currentB: Float,
    onAChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState((currentA + 0.3f) / 0.6f)
    val aNormalizedProvider = remember { { currentVal } }
    OkLabAChannelSliderImpl(
        currentL = currentL,
        currentB = currentB,
        aNormalizedProvider = aNormalizedProvider,
        onAChanged = onAChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkLabAChannelSliderImpl(
    currentL: Float,
    currentB: Float,
    aNormalizedProvider: () -> Float,
    onAChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val currentOnAChanged by rememberUpdatedState(onAChanged)
    val aChannelColors = remember(currentL, currentB) {
        val minA = -0.3f
        val maxA = 0.3f
        val steps = 8
        (0..steps).map { i ->
            val a = minA + (maxA - minA) * i.toFloat() / steps.toFloat()
            OkLab(
                l = currentL * 100f,
                a = (a / 0.4f) * 100f,
                b = (currentB / 0.4f) * 100f,
            ).toColor()
        }
    }
    val onNormalizedChanged = remember {
        { normalizedValue: Float ->
            currentOnAChanged(normalizedValue * 0.6f - 0.3f)
        }
    }
    ColorSlider(
        valueProvider = aNormalizedProvider,
        onValueChanged = onNormalizedChanged,
        drawBrushColors = aChannelColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkLabBChannelSlider] component for selecting the B channel (blue-yellow axis) of a color in OkLab space.
 */
@Composable
fun OkLabBChannelSlider(
    currentL: Float,
    currentA: Float,
    currentB: Float,
    onBChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState((currentB + 0.3f) / 0.6f)
    val bNormalizedProvider = remember { { currentVal } }
    OkLabBChannelSliderImpl(
        currentL = currentL,
        currentA = currentA,
        bNormalizedProvider = bNormalizedProvider,
        onBChanged = onBChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkLabBChannelSliderImpl(
    currentL: Float,
    currentA: Float,
    bNormalizedProvider: () -> Float,
    onBChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val currentOnBChanged by rememberUpdatedState(onBChanged)
    val bChannelColors = remember(currentL, currentA) {
        val minB = -0.3f
        val maxB = 0.3f
        val steps = 8
        (0..steps).map { i ->
            val b = minB + (maxB - minB) * i.toFloat() / steps.toFloat()
            OkLab(
                l = currentL * 100f,
                a = (currentA / 0.4f) * 100f,
                b = (b / 0.4f) * 100f,
            ).toColor()
        }
    }
    val onNormalizedChanged = remember {
        { normalizedValue: Float ->
            currentOnBChanged(normalizedValue * 0.6f - 0.3f)
        }
    }
    ColorSlider(
        valueProvider = bNormalizedProvider,
        onValueChanged = onNormalizedChanged,
        drawBrushColors = bChannelColors,
        modifier = Modifier.fillMaxWidth(),
        hapticEffect = hapticEffect,
    )
}

/**
 * A [OkLabAlphaSlider] component for selecting the alpha of a color in OkLab space.
 */
@Composable
fun OkLabAlphaSlider(
    currentL: Float,
    currentA: Float,
    currentB: Float,
    currentAlpha: Float,
    onAlphaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val currentVal by rememberUpdatedState(currentAlpha)
    val alphaProvider = remember { { currentVal } }
    OkLabAlphaSliderImpl(
        currentL = currentL,
        currentA = currentA,
        currentB = currentB,
        alphaProvider = alphaProvider,
        onAlphaChanged = onAlphaChanged,
        hapticEffect = hapticEffect,
    )
}

@Composable
private fun OkLabAlphaSliderImpl(
    currentL: Float,
    currentA: Float,
    currentB: Float,
    alphaProvider: () -> Float,
    onAlphaChanged: (Float) -> Unit,
    hapticEffect: SliderDefaults.SliderHapticEffect,
) {
    val alphaColors = remember(currentL, currentA, currentB) {
        val baseColor = OkLab(
            l = currentL * 100f,
            a = (currentA / 0.4f) * 100f,
            b = (currentB / 0.4f) * 100f,
        ).toColor()
        listOf(baseColor.copy(alpha = 0f), baseColor.copy(alpha = 1f))
    }

    ColorSlider(
        valueProvider = alphaProvider,
        onValueChanged = onAlphaChanged,
        drawBrushColors = alphaColors,
        modifier = Modifier.fillMaxWidth()
            .drawCheckerboard(),
        hapticEffect = hapticEffect,
    )
}

fun Modifier.drawCheckerboard(
    cellSizeDp: Dp = 3.dp,
    lightColor: Color = Color(0xFFCCCCCC),
    darkColor: Color = Color(0xFFAAAAAA),
): Modifier = this.then(CheckerboardElement(cellSizeDp, lightColor, darkColor))

private data class CheckerboardElement(
    val cellSizeDp: Dp,
    val lightColor: Color,
    val darkColor: Color,
) : ModifierNodeElement<CheckerboardNode>() {
    override fun create(): CheckerboardNode = CheckerboardNode(cellSizeDp, lightColor, darkColor)

    override fun update(node: CheckerboardNode) {
        node.update(cellSizeDp, lightColor, darkColor)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "drawCheckerboard"
        properties["cellSizeDp"] = cellSizeDp
        properties["lightColor"] = lightColor
        properties["darkColor"] = darkColor
    }
}

private class CheckerboardNode(
    private var cellSizeDp: Dp,
    private var lightColor: Color,
    private var darkColor: Color,
) : Modifier.Node(),
    DrawModifierNode {
    private var cachedWidth = Float.NaN
    private var cachedHeight = Float.NaN
    private var cachedDensity = Float.NaN
    private var cachedCellPx = Float.NaN
    private val darkPath = Path()

    fun update(cellSizeDp: Dp, lightColor: Color, darkColor: Color) {
        val geometryChanged = this.cellSizeDp != cellSizeDp
        val colorChanged = this.lightColor != lightColor || this.darkColor != darkColor
        this.cellSizeDp = cellSizeDp
        this.lightColor = lightColor
        this.darkColor = darkColor
        if (geometryChanged) {
            cachedCellPx = Float.NaN
            invalidateDraw()
        } else if (colorChanged) {
            invalidateDraw()
        }
    }

    override fun ContentDrawScope.draw() {
        val cell = cellSizeDp.toPx().fastCoerceAtLeast(1f)
        if (cachedWidth != size.width || cachedHeight != size.height || cachedDensity != density || cachedCellPx != cell) {
            cachedWidth = size.width
            cachedHeight = size.height
            cachedDensity = density
            cachedCellPx = cell
            darkPath.reset()
            val rows = ceil(size.height / cell).toInt().fastCoerceAtLeast(1)
            var y = 0f
            for (row in 0 until rows) {
                var x = if ((row and 1) == 0) cell else 0f
                val bottom = min(y + cell, size.height)
                while (x < size.width) {
                    darkPath.addRect(Rect(x, y, min(x + cell, size.width), bottom))
                    x += cell * 2f
                }
                y += cell
            }
        }
        drawRect(color = lightColor)
        drawPath(path = darkPath, color = darkColor)
        drawContent()
    }
}

/**
 * Generic slider component for color selection.
 */
@Composable
private fun ColorSlider(
    valueProvider: () -> Float,
    onValueChanged: (Float) -> Unit,
    drawBrushColors: List<Color>,
    modifier: Modifier = Modifier,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val onValueChangedState = rememberUpdatedState(onValueChanged)
    val currentValueProvider by rememberUpdatedState(valueProvider)
    val capsuleShape = CircleShape
    val density = LocalDensity.current
    val indicatorSizeDp = 20.dp
    val sliderHeightDp = 26.dp
    val sliderHeightPx = with(density) { sliderHeightDp.toPx() }
    val hapticFeedback = LocalHapticFeedback.current
    val hapticState = remember { SliderHapticState() }
    var dragOffset by remember { mutableFloatStateOf(0f) }
    var sliderWidthPxState by remember { mutableFloatStateOf(0f) }
    val sliderWidthPxProvider = remember { { sliderWidthPxState } }
    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl

    Box(
        modifier = Modifier
            .clip(capsuleShape)
            .then(modifier)
            .height(sliderHeightDp)
            .drawWithCache {
                val widthPx = size.width
                val halfSliderHeightPx = sliderHeightDp.toPx() / 2f
                val gradientBrush = Brush.horizontalGradient(
                    colors = if (isRtl) drawBrushColors.reversed() else drawBrushColors,
                    startX = halfSliderHeightPx,
                    endX = widthPx - halfSliderHeightPx,
                    tileMode = TileMode.Clamp,
                )
                val borderStroke = Stroke(width = 0.5.dp.toPx())
                val borderColor = Color.Gray.copy(0.1f)

                onDrawBehind {
                    drawRect(brush = gradientBrush)
                    drawRect(
                        color = borderColor,
                        style = borderStroke,
                    )
                }
            }
            .onSizeChanged { sliderWidthPxState = it.width.toFloat() }
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    val d = if (isRtl) -delta else delta
                    dragOffset += d
                    val newValue = handleSliderInteraction(
                        dragOffset,
                        sliderWidthPxState,
                        sliderHeightPx,
                    ).fastCoerceIn(0f, 1f)
                    onValueChangedState.value(newValue)
                    hapticState.handleHapticFeedback(newValue, UnitRange, hapticEffect, hapticFeedback)
                },
                onDragStarted = { offset ->
                    val x = if (isRtl) sliderWidthPxState - offset.x else offset.x
                    dragOffset = x
                    val newValue = handleSliderInteraction(x, sliderWidthPxState, sliderHeightPx)
                    onValueChangedState.value(newValue)
                    hapticState.reset(newValue, UnitRange, currentValueProvider())
                },
            ),
    ) {
        SliderIndicator(
            modifier = Modifier.align(Alignment.CenterStart),
            valueProvider = valueProvider,
            sliderWidthPxProvider = sliderWidthPxProvider,
            sliderSizePx = sliderHeightPx,
            indicatorSize = indicatorSizeDp,
        )
    }
}

@Composable
private fun SliderIndicator(
    valueProvider: () -> Float,
    sliderWidthPxProvider: () -> Float,
    sliderSizePx: Float,
    indicatorSize: Dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                alpha = if (sliderWidthPxProvider() > 0f) 1f else 0f
            }
            .offset {
                val sliderWidthPx = sliderWidthPxProvider()
                val effectiveWidthPx = (sliderWidthPx - sliderSizePx).fastCoerceAtLeast(0f)
                val indicatorPositionPx = (valueProvider().fastCoerceIn(0f, 1f) * effectiveWidthPx) + (sliderSizePx / 2)
                IntOffset((indicatorPositionPx - indicatorSize.toPx() / 2f).fastRoundToInt(), 0)
            }
            .size(indicatorSize)
            .drawWithCache {
                val strokeWidth = 6.dp.toPx()
                val halfStroke = strokeWidth / 2f
                val glowSpread = 2.dp.toPx()
                val glowColor = Color.Black.copy(alpha = 0.25f)

                val ringCenterRadius = (size.minDimension / 2f) - halfStroke
                val gradientRadius = ringCenterRadius + halfStroke + glowSpread
                val indicatorStroke = Stroke(width = strokeWidth)

                val glowBrush = Brush.radialGradient(
                    colorStops = arrayOf(
                        ((ringCenterRadius - halfStroke - glowSpread).fastCoerceAtLeast(0f) / gradientRadius) to Color.Transparent,
                        ((ringCenterRadius - halfStroke) / gradientRadius) to glowColor,
                        ((ringCenterRadius + halfStroke) / gradientRadius) to glowColor,
                        ((ringCenterRadius + halfStroke + glowSpread) / gradientRadius) to Color.Transparent,
                    ),
                    radius = gradientRadius,
                )

                onDrawBehind {
                    drawCircle(
                        brush = glowBrush,
                        radius = gradientRadius,
                    )

                    drawCircle(
                        color = Color.White,
                        radius = ringCenterRadius,
                        style = indicatorStroke,
                    )
                }
            },
    )
}

/**
 * Handle slider interaction and calculate new value.
 */
private fun handleSliderInteraction(
    positionX: Float,
    totalWidth: Float,
    sliderSizePx: Float,
): Float {
    val sliderHalfSizePx = sliderSizePx / 2
    val effectiveWidth = totalWidth - sliderSizePx
    if (effectiveWidth <= 0f) return 0f
    val constrainedX = positionX.fastCoerceIn(sliderHalfSizePx, totalWidth - sliderHalfSizePx)
    val newPosition = (constrainedX - sliderHalfSizePx) / effectiveWidth
    return newPosition.fastCoerceIn(0f, 1f)
}

private val UnitRange = 0f..1f

enum class ColorSpace {
    HSV,
    OKHSV,
    OKLAB,
    OKLCH,
}
