// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mocharealm.gaze.capsule.ContinuousCapsule
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
 * @param initialColor The initial color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 * @param colorSpace The color space to use for the picker.
 * @param modifier The modifier to be applied to the color picker.
 */
@Composable
fun ColorPicker(
    initialColor: Color,
    onColorChanged: (Color) -> Unit,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    colorSpace: ColorSpace = ColorSpace.HSV,
    modifier: Modifier = Modifier,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    when (colorSpace) {
        ColorSpace.OKHSV -> {
            OkHsvColorPicker(
                initialColor = initialColor,
                onColorChanged = currentOnColorChanged,
                showPreview = showPreview,
                hapticEffect = hapticEffect,
                modifier = modifier,
            )
        }

        ColorSpace.OKLAB -> {
            OkLabColorPicker(
                initialColor = initialColor,
                onColorChanged = currentOnColorChanged,
                showPreview = showPreview,
                hapticEffect = hapticEffect,
                modifier = modifier,
            )
        }

        ColorSpace.OKLCH -> {
            OkLchColorPicker(
                initialColor = initialColor,
                onColorChanged = currentOnColorChanged,
                showPreview = showPreview,
                hapticEffect = hapticEffect,
                modifier = modifier,
            )
        }

        else -> {
            HsvColorPicker(
                initialColor = initialColor,
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
 * @param initialColor The initial color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 * @param modifier The modifier to be applied to the color picker.
 */
@Composable
fun HsvColorPicker(
    initialColor: Color,
    onColorChanged: (Color) -> Unit,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    modifier: Modifier = Modifier,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    var initialSetup by remember { mutableStateOf(true) }
    var currentHue by remember { mutableStateOf(0f) }
    var currentSaturation by remember { mutableStateOf(0f) }
    var currentValue by remember { mutableStateOf(0f) }
    var currentAlpha by remember { mutableStateOf(1f) }

    val selectedColor = remember(currentHue, currentSaturation, currentValue, currentAlpha) {
        Hsv(
            h = currentHue.toDouble(),
            s = (currentSaturation * 100.0),
            v = (currentValue * 100.0),
        ).toColor(currentAlpha)
    }

    LaunchedEffect(initialColor) {
        if (initialSetup) {
            val hsv = initialColor.toHsv()
            currentHue = hsv.h.toFloat()
            currentSaturation = (hsv.s / 100.0).toFloat()
            currentValue = (hsv.v / 100.0).toFloat()
            currentAlpha = initialColor.alpha
            initialSetup = false
        }
    }

    LaunchedEffect(selectedColor) {
        if (!initialSetup) {
            currentOnColorChanged(selectedColor)
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
                    .clip(ContinuousCapsule)
                    .background(selectedColor),
            )
        }

        // Hue selection
        HsvHueSlider(
            currentHue = currentHue,
            onHueChanged = { newHue -> currentHue = newHue * 360f },
            hapticEffect = hapticEffect,
        )

        // Saturation selection
        HsvSaturationSlider(
            currentHue = currentHue,
            currentSaturation = currentSaturation,
            onSaturationChanged = { currentSaturation = it },
            hapticEffect = hapticEffect,
        )

        // Value selection
        HsvValueSlider(
            currentHue = currentHue,
            currentSaturation = currentSaturation,
            currentValue = currentValue,
            onValueChanged = { currentValue = it },
            hapticEffect = hapticEffect,
        )

        // Alpha selection
        HsvAlphaSlider(
            currentHue = currentHue,
            currentSaturation = currentSaturation,
            currentValue = currentValue,
            currentAlpha = currentAlpha,
            onAlphaChanged = { currentAlpha = it },
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
    val hsvHueColors = remember {
        Transforms.generateHsvHueColors()
    }

    ColorSlider(
        value = currentHue / 360f,
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
    val saturationColors = remember(currentHue) {
        listOf(
            Hsv(currentHue.toDouble(), 0.0, 100.0).toColor(1f),
            Hsv(currentHue.toDouble(), 100.0, 100.0).toColor(1f),
        )
    }
    ColorSlider(
        value = currentSaturation,
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
    val valueColors = remember(currentHue, currentSaturation) {
        listOf(Color.Black, Hsv(currentHue.toDouble(), (currentSaturation * 100.0), 100.0).toColor())
    }
    ColorSlider(
        value = currentValue,
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
    val alphaColors = remember(currentHue, currentSaturation, currentValue) {
        val baseColor = Hsv(currentHue.toDouble(), (currentSaturation * 100.0), (currentValue * 100.0)).toColor()
        listOf(baseColor.copy(alpha = 0f), baseColor.copy(alpha = 1f))
    }

    ColorSlider(
        value = currentAlpha,
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
 * @param initialColor The initial color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 * @param modifier The modifier to be applied to the color picker.
 */
@Composable
fun OkHsvColorPicker(
    initialColor: Color,
    onColorChanged: (Color) -> Unit,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    modifier: Modifier = Modifier,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    var initialSetup by remember { mutableStateOf(true) }
    var currentH by remember { mutableStateOf(0f) }
    var currentS by remember { mutableStateOf(0f) }
    var currentV by remember { mutableStateOf(0f) }
    var currentAlpha by remember { mutableStateOf(1f) }

    val selectedColor = remember(currentH, currentS, currentV, currentAlpha) {
        OkHsv(
            h = currentH,
            s = currentS,
            v = currentV,
        ).toColor(currentAlpha)
    }

    LaunchedEffect(initialColor) {
        if (initialSetup) {
            val okhsv = Transforms.colorToOkhsv(initialColor)
            currentH = okhsv[0]
            currentS = okhsv[1]
            currentV = okhsv[2]
            currentAlpha = initialColor.alpha
            initialSetup = false
        }
    }

    LaunchedEffect(selectedColor) {
        if (!initialSetup) {
            currentOnColorChanged(selectedColor)
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
                    .clip(ContinuousCapsule)
                    .background(selectedColor),
            )
        }

        // Hue selection (OkHSV)
        OkHsvHueSlider(
            currentH = currentH,
            onHueChanged = { currentH = it },
            hapticEffect = hapticEffect,
        )

        // Saturation selection (OkHSV)
        OkHsvSaturationSlider(
            currentH = currentH,
            currentS = currentS,
            onSaturationChanged = { currentS = it },
            hapticEffect = hapticEffect,
        )

        // Value selection (OkHSV)
        OkHsvValueSlider(
            currentH = currentH,
            currentS = currentS,
            currentV = currentV,
            onValueChanged = { currentV = it },
            hapticEffect = hapticEffect,
        )

        // Alpha selection (OkHSV)
        OkHsvAlphaSlider(
            currentH = currentH,
            currentS = currentS,
            currentV = currentV,
            currentAlpha = currentAlpha,
            onAlphaChanged = { currentAlpha = it },
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
    val okHsvHueColors = remember {
        Transforms.generateOkHsvHueColors()
    }

    ColorSlider(
        value = currentH,
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
    val saturationColors = remember(currentH) {
        listOf(
            Transforms.okhsvToColor(currentH, 0f, 1f, 1f),
            Transforms.okhsvToColor(currentH, 1f, 1f, 1f),
        )
    }

    ColorSlider(
        value = currentS,
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
    val valueColors = remember(currentH, currentS) {
        listOf(
            Transforms.okhsvToColor(currentH, currentS, 0f, 1f),
            Transforms.okhsvToColor(currentH, currentS, 1f, 1f),
        )
    }

    ColorSlider(
        value = currentV,
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
    val alphaColors = remember(currentH, currentS, currentV) {
        val baseColor = Transforms.okhsvToColor(currentH, currentS, currentV)
        listOf(baseColor.copy(alpha = 0f), baseColor.copy(alpha = 1f))
    }

    ColorSlider(
        value = currentAlpha,
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
 * @param initialColor The initial color of the picker.
 * @param onColorChanged The callback to be called when the color changes.
 * @param showPreview Whether to show a preview of the selected color.
 * @param hapticEffect The haptic effect of the [ColorSlider].
 * @param modifier The modifier to be applied to the color picker.
 */
@Composable
fun OkLabColorPicker(
    initialColor: Color,
    onColorChanged: (Color) -> Unit,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    modifier: Modifier = Modifier,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    var initialSetup by remember { mutableStateOf(true) }
    var currentL by remember { mutableStateOf(0f) }
    var currentA by remember { mutableStateOf(0f) }
    var currentB by remember { mutableStateOf(0f) }
    var currentAlpha by remember { mutableStateOf(1f) }

    val selectedColor = remember(currentL, currentA, currentB, currentAlpha) {
        OkLab(
            l = (currentL * 100.0),
            a = (currentA / 0.4 * 100.0),
            b = (currentB / 0.4 * 100.0),
        ).toColor(currentAlpha)
    }

    LaunchedEffect(initialColor) {
        if (initialSetup) {
            val ok = initialColor.toOkLab()
            currentL = (ok.l / 100.0).toFloat()
            currentA = ((ok.a / 100.0) * 0.4).toFloat()
            currentB = ((ok.b / 100.0) * 0.4).toFloat()
            currentAlpha = initialColor.alpha
            initialSetup = false
        }
    }

    LaunchedEffect(selectedColor) {
        if (!initialSetup) {
            currentOnColorChanged(selectedColor)
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
                    .clip(ContinuousCapsule)
                    .background(selectedColor),
            )
        }

        // Lightness selection
        OkLabLightnessSlider(
            currentL = currentL,
            currentA = currentA,
            currentB = currentB,
            onLightnessChanged = { currentL = it },
            hapticEffect = hapticEffect,
        )

        // A channel selection (green-red axis)
        OkLabAChannelSlider(
            currentL = currentL,
            currentA = currentA,
            currentB = currentB,
            onAChanged = { currentA = it },
            hapticEffect = hapticEffect,
        )

        // B channel selection (blue-yellow axis)
        OkLabBChannelSlider(
            currentL = currentL,
            currentA = currentA,
            currentB = currentB,
            onBChanged = { currentB = it },
            hapticEffect = hapticEffect,
        )

        // Alpha selection
        OkLabAlphaSlider(
            currentL = currentL,
            currentA = currentA,
            currentB = currentB,
            currentAlpha = currentAlpha,
            onAlphaChanged = { currentAlpha = it },
            hapticEffect = hapticEffect,
        )
    }
}

/**
 * A [OkLchColorPicker] component using OkLCH color space with perceptual adjustments.
 * OkLCH provides intuitive control with Lightness, Chroma, and Hue.
 */
@Composable
fun OkLchColorPicker(
    initialColor: Color,
    onColorChanged: (Color) -> Unit,
    showPreview: Boolean = true,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    modifier: Modifier = Modifier,
) {
    val currentOnColorChanged by rememberUpdatedState(onColorChanged)
    var initialSetup by remember { mutableStateOf(true) }
    var currentL by remember { mutableStateOf(0f) } // 0..1
    var currentC by remember { mutableStateOf(0f) } // proportion 0..1 (scaled to 0..0.4 internally)
    var currentH by remember { mutableStateOf(0f) } // normalized 0..1 (scaled to 360)
    var currentAlpha by remember { mutableStateOf(1f) }

    val selectedColor = remember(currentL, currentC, currentH, currentAlpha) {
        OkLch(
            l = (currentL * 100.0),
            c = (currentC * 100.0),
            h = (currentH * 360.0),
        ).toColor(currentAlpha)
    }

    LaunchedEffect(initialColor) {
        if (initialSetup) {
            val oklch = initialColor.toOkLch()
            currentL = (oklch.l / 100.0).toFloat()
            currentC = (oklch.c / 100.0).toFloat()
            currentH = (oklch.h / 360.0).toFloat()
            currentAlpha = initialColor.alpha
            initialSetup = false
        }
    }

    LaunchedEffect(selectedColor) {
        if (!initialSetup) {
            currentOnColorChanged(selectedColor)
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
                    .clip(ContinuousCapsule)
                    .background(selectedColor),
            )
        }

        // Hue selection
        OkLchHueSlider(
            currentL = currentL,
            currentC = currentC,
            currentH = currentH,
            onHueChanged = { currentH = it },
            hapticEffect = hapticEffect,
        )

        // Lightness selection
        OkLchLightnessSlider(
            currentL = currentL,
            currentC = currentC,
            currentH = currentH,
            onLightnessChanged = { currentL = it },
            hapticEffect = hapticEffect,
        )

        // Chroma selection
        OkLchChromaSlider(
            currentL = currentL,
            currentC = currentC,
            currentH = currentH,
            onChromaChanged = { currentC = it },
            hapticEffect = hapticEffect,
        )

        // Alpha selection
        OkLchAlphaSlider(
            currentL = currentL,
            currentC = currentC,
            currentH = currentH,
            currentAlpha = currentAlpha,
            onAlphaChanged = { currentAlpha = it },
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
    val hDeg = currentH * 360f
    val cInternal = currentC * 0.4f
    val colors = remember(currentC, currentH) {
        listOf(
            Transforms.oklchToColor(0f, cInternal, hDeg, 1f),
            Transforms.oklchToColor(1f, cInternal, hDeg, 1f),
        )
    }

    ColorSlider(
        value = currentL,
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
    val hDeg = currentH * 360f
    val colors = remember(currentL, currentH) {
        listOf(
            Transforms.oklchToColor(currentL, 0f, hDeg, 1f),
            Transforms.oklchToColor(currentL, 0.4f, hDeg, 1f),
        )
    }

    ColorSlider(
        value = currentC,
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
    val colors = remember(currentL, currentC) {
        Transforms.generateOkLchHueColors(currentL, currentC)
    }

    ColorSlider(
        value = currentH,
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
    val hDeg = currentH * 360f
    val cInternal = currentC * 0.4f
    val colors = remember(currentL, currentC, currentH) {
        val opaque = Transforms.oklchToColor(currentL, cInternal, hDeg, 1f)
        val transparent = Color(opaque.red, opaque.green, opaque.blue, 0f)
        listOf(transparent, opaque)
    }

    ColorSlider(
        value = currentAlpha,
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
    val lightnessColors = remember(currentA, currentB) {
        val steps = 7
        (0..steps).map { i ->
            val l = i.toFloat() / steps.toFloat()
            OkLab(
                l = l * 100.0,
                a = (currentA / 0.4 * 100.0),
                b = (currentB / 0.4 * 100.0),
            ).toColor()
        }
    }
    ColorSlider(
        value = currentL,
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
    val aChannelColors = remember(currentL, currentB) {
        val minA = -0.3f
        val maxA = 0.3f
        val steps = 8
        (0..steps).map { i ->
            val a = minA + (maxA - minA) * i.toFloat() / steps.toFloat()
            OkLab(
                l = currentL * 100.0,
                a = (a / 0.4 * 100.0),
                b = (currentB / 0.4 * 100.0),
            ).toColor()
        }
    }
    ColorSlider(
        value = (currentA + 0.3f) / 0.6f,
        onValueChanged = { normalizedValue ->
            onAChanged(normalizedValue * 0.6f - 0.3f)
        },
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
    val bChannelColors = remember(currentL, currentA) {
        val minB = -0.3f
        val maxB = 0.3f
        val steps = 8
        (0..steps).map { i ->
            val b = minB + (maxB - minB) * i.toFloat() / steps.toFloat()
            OkLab(
                l = currentL * 100.0,
                a = (currentA / 0.4 * 100.0),
                b = (b / 0.4 * 100.0),
            ).toColor()
        }
    }
    ColorSlider(
        value = (currentB + 0.3f) / 0.6f,
        onValueChanged = { normalizedValue ->
            onBChanged(normalizedValue * 0.6f - 0.3f)
        },
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
    val alphaColors = remember(currentL, currentA, currentB) {
        val baseColor = OkLab(
            l = currentL * 100.0,
            a = (currentA / 0.4 * 100.0),
            b = (currentB / 0.4 * 100.0),
        ).toColor()
        listOf(baseColor.copy(alpha = 0f), baseColor.copy(alpha = 1f))
    }

    ColorSlider(
        value = currentAlpha,
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
): Modifier = this.then(
    Modifier.drawWithCache {
        val cell = cellSizeDp.toPx().coerceAtLeast(1f)
        val rows = ceil(size.height / cell).toInt().coerceAtLeast(1)

        val darkPath = Path().apply {
            var y = 0f
            for (row in 0 until rows) {
                var x = if ((row and 1) == 0) cell else 0f
                while (x < size.width) {
                    addRect(
                        Rect(
                            x,
                            y,
                            min(x + cell, size.width),
                            min(y + cell, size.height),
                        ),
                    )
                    x += cell * 2f
                }
                y += cell
            }
        }

        onDrawBehind {
            drawRect(color = lightColor)
            drawPath(path = darkPath, color = darkColor)
        }
    },
)

/**
 * Generic slider component for color selection.
 */
@Composable
private fun ColorSlider(
    value: Float,
    onValueChanged: (Float) -> Unit,
    drawBrushColors: List<Color>,
    modifier: Modifier = Modifier,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
) {
    val onValueChangedState = rememberUpdatedState(onValueChanged)
    val density = LocalDensity.current
    val indicatorSizeDp = 20.dp
    val sliderHeightDp = 26.dp
    val sliderHeightPx = with(density) { sliderHeightDp.toPx() }
    val hapticFeedback = LocalHapticFeedback.current
    val hapticState = remember { SliderHapticState() }

    BoxWithConstraints(
        modifier = Modifier
            .clip(ContinuousCapsule)
            .then(modifier)
            .height(sliderHeightDp)
            .drawWithCache {
                val widthPx = size.width
                val halfSliderHeightPx = sliderHeightDp.toPx() / 2f
                val gradientBrush = Brush.horizontalGradient(
                    colors = drawBrushColors,
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
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { offset ->
                        val newValue =
                            handleSliderInteraction(offset.x, size.width.toFloat(), sliderHeightPx)
                        onValueChangedState.value(newValue)
                        hapticState.reset(newValue)
                    },
                    onHorizontalDrag = { change, _ ->
                        change.consume()
                        val newValue = handleSliderInteraction(
                            change.position.x,
                            size.width.toFloat(),
                            sliderHeightPx,
                        ).coerceIn(0f, 1f)
                        onValueChangedState.value(newValue)
                        hapticState.handleHapticFeedback(newValue, 0f..1f, hapticEffect, hapticFeedback)
                    },
                )
            },
    ) {
        SliderIndicator(
            modifier = Modifier.align(Alignment.CenterStart),
            value = value,
            sliderWidth = maxWidth,
            sliderSizePx = sliderHeightPx,
            indicatorSize = indicatorSizeDp,
        )
    }
}

@Composable
private fun SliderIndicator(
    modifier: Modifier = Modifier,
    value: Float,
    sliderWidth: Dp,
    sliderSizePx: Float,
    indicatorSize: Dp,
) {
    val density = LocalDensity.current
    val indicatorOffsetXDp = with(density) {
        val sliderWidthPx = sliderWidth.toPx()
        val effectiveWidthPx = sliderWidthPx - sliderSizePx
        val indicatorPositionPx = (value * effectiveWidthPx) + (sliderSizePx / 2)
        indicatorPositionPx.toDp() - (indicatorSize / 2)
    }

    Box(
        modifier = modifier
            .offset(x = indicatorOffsetXDp)
            .size(indicatorSize)
            .drawWithCache {
                val strokeWidth = 6.dp.toPx()
                val halfStroke = strokeWidth / 2f
                val glowSpread = 2.dp.toPx()
                val glowColor = Color.Black.copy(alpha = 0.25f)

                val ringCenterRadius = (size.minDimension / 2f) - halfStroke
                val gradientRadius = ringCenterRadius + halfStroke + glowSpread

                val glowBrush = Brush.radialGradient(
                    colorStops = listOf(
                        ((ringCenterRadius - halfStroke - glowSpread).coerceAtLeast(0f) / gradientRadius) to Color.Transparent,
                        ((ringCenterRadius - halfStroke) / gradientRadius) to glowColor,
                        ((ringCenterRadius + halfStroke) / gradientRadius) to glowColor,
                        ((ringCenterRadius + halfStroke + glowSpread) / gradientRadius) to Color.Transparent,
                    ).toTypedArray(),
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
                        style = Stroke(width = strokeWidth),
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
    val constrainedX = positionX.coerceIn(sliderHalfSizePx, totalWidth - sliderHalfSizePx)
    val newPosition = (constrainedX - sliderHalfSizePx) / effectiveWidth
    return newPosition.coerceIn(0f, 1f)
}

enum class ColorSpace {
    HSV,
    OKHSV,
    OKLAB,
    OKLCH,
}
