# SliderPreference

`SliderPreference` is a preference component in Miuix that combines a title/summary with a slider control. The slider is placed in the bottom action area of the `BasicComponent`, making it ideal for settings screens where users need to adjust values such as volume, brightness, or font size.

## Import

```kotlin
import top.yukonga.miuix.kmp.preference.SliderPreference
```

## Basic Usage

```kotlin
var sliderValue by remember { mutableFloatStateOf(0.5f) }

SliderPreference(
    value = sliderValue,
    onValueChange = { sliderValue = it },
    title = "Volume"
)
```

## With Summary

```kotlin
var brightness by remember { mutableFloatStateOf(0.8f) }

SliderPreference(
    value = brightness,
    onValueChange = { brightness = it },
    title = "Brightness",
    summary = "Adjust screen brightness level"
)
```

## Component States

### Disabled State

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }

SliderPreference(
    value = value,
    onValueChange = { value = it },
    title = "Disabled Slider",
    summary = "This slider is currently unavailable",
    enabled = false
)
```

## Properties

### SliderPreference Properties

| Property Name         | Type                              | Description                                                                                                                                                                    | Default Value                      | Required |
| --------------------- | --------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------- | -------- |
| value                 | Float                             | Current value of the slider. If outside of valueRange provided, value will be coerced to this range                                                                            | -                                  | Yes      |
| onValueChange         | (Float) -> Unit                   | Callback when value changes                                                                                                                                                    | -                                  | Yes      |
| title                 | String?                           | Title of the preference                                                                                                                                                        | null                               | No       |
| modifier              | Modifier                          | Modifier applied to the component                                                                                                                                              | Modifier                           | No       |
| titleColor            | BasicComponentColors              | Title text color configuration                                                                                                                                                 | BasicComponentDefaults.titleColor() | No       |
| summary               | String?                           | Summary description                                                                                                                                                            | null                               | No       |
| summaryColor          | BasicComponentColors              | Summary text color configuration                                                                                                                                               | BasicComponentDefaults.summaryColor() | No       |
| startAction           | @Composable (() -> Unit)?         | Custom start side content                                                                                                                                                      | null                               | No       |
| valueText             | String?                           | Current slider value text displayed in the end area with summary-style formatting. Rendered inside the Row layout with center-vertical alignment and weight                     | null                               | No       |
| endActions            | @Composable (RowScope.() -> Unit)?| Custom end side content, rendered after valueText within the same Row                                                                                                          | null                               | No       |
| bottomAction          | @Composable (() -> Unit)?         | Custom content at the top of the bottom area, above the slider                                                                                                                 | null                               | No       |
| onClick               | (() -> Unit)?                     | Callback triggered on click. When non-null, an arrow icon is displayed in the end area                                                                                        | null                               | No       |
| holdDownState         | Boolean                           | Whether the component is in the pressed state                                                                                                                                  | false                              | No       |
| enabled               | Boolean                           | Whether the preference is enabled                                                                                                                                              | true                               | No       |
| valueRange            | ClosedFloatingPointRange\<Float\> | Range of values that this slider can take. The passed value will be coerced to this range                                                                                      | 0f..1f                             | No       |
| steps                 | Int                               | Amount of discrete allowable values. If 0, the slider will behave continuously. Must not be negative                                                                           | 0                                  | No       |
| onValueChangeFinished | (() -> Unit)?                     | Called when value change has ended                                                                                                                                              | null                               | No       |
| reverseDirection      | Boolean                           | Controls the direction of slider. When false (default), follows LayoutDirection (L-R in LTR, R-L in RTL). When true, reverses the direction.                                   | false                              | No       |
| sliderHeight          | Dp                                | Height of the slider                                                                                                                                                           | SliderDefaults.MinHeight           | No       |
| sliderColors          | SliderColors                      | Color configuration of the slider                                                                                                                                              | SliderDefaults.sliderColors()      | No       |
| hapticEffect          | SliderDefaults.SliderHapticEffect | Type of haptic feedback                                                                                                                                                        | SliderDefaults.DefaultHapticEffect | No       |
| showKeyPoints         | Boolean                           | Whether to show key point indicators on the slider. Only works when keyPoints is not null                                                                                      | false                              | No       |
| keyPoints             | List\<Float\>?                    | Custom key point values to display on the slider. If null, uses step positions from steps parameter. Values should be within valueRange                                        | null                               | No       |
| magnetThreshold       | Float                             | Magnetic snap threshold as a fraction (0.0 to 1.0). When slider value is within this distance from a key point, it will snap to that point. Only applies when keyPoints is set | 0.02f                              | No       |
| insideMargin          | PaddingValues                     | Internal content padding                                                                                                                                                       | BasicComponentDefaults.InsideMargin | No       |

## Advanced Usage

### Custom Value Range with Steps

```kotlin
var temperature by remember { mutableFloatStateOf(25f) }

SliderPreference(
    value = temperature,
    onValueChange = { temperature = it },
    title = "Temperature",
    summary = "Current: ${temperature.roundToInt()}°C",
    valueRange = 16f..32f,
    steps = 15,
    hapticEffect = SliderDefaults.SliderHapticEffect.Step
)
```

### With Start Icon

```kotlin
var volume by remember { mutableFloatStateOf(0.7f) }

SliderPreference(
    value = volume,
    onValueChange = { volume = it },
    title = "Volume",
    summary = "${(volume * 100).roundToInt()}%",
    startAction = {
        Icon(
            imageVector = MiuixIcons.Basic.Audio,
            contentDescription = "Volume Icon",
            tint = MiuixTheme.colorScheme.onBackground,
            modifier = Modifier.padding(end = 16.dp)
        )
    },
    valueRange = 0f..1f
)
```

### With Custom Key Points

```kotlin
var fontSize by remember { mutableFloatStateOf(14f) }

SliderPreference(
    value = fontSize,
    onValueChange = { fontSize = it },
    title = "Font Size",
    summary = "Current: ${fontSize.roundToInt()}sp",
    valueRange = 10f..24f,
    showKeyPoints = true,
    keyPoints = listOf(10f, 12f, 14f, 16f, 18f, 20f, 24f),
    magnetThreshold = 0.05f,
    hapticEffect = SliderDefaults.SliderHapticEffect.Step
)
```
