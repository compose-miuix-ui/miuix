# RangeSliderPreference

`RangeSliderPreference` is a preference component in Miuix that combines a title/summary with a range slider control. The range slider is placed in the bottom action area of the `BasicComponent`, making it ideal for settings screens where users need to select a range of values such as price filters, frequency bands, or dual-threshold controls.

## Import

```kotlin
import top.yukonga.miuix.kmp.preference.RangeSliderPreference
```

## Basic Usage

```kotlin
var rangeValue by remember { mutableStateOf(0.2f..0.8f) }

RangeSliderPreference(
    value = rangeValue,
    onValueChange = { rangeValue = it },
    title = "Range Selection"
)
```

## With Summary

```kotlin
var priceRange by remember { mutableStateOf(100f..500f) }

RangeSliderPreference(
    value = priceRange,
    onValueChange = { priceRange = it },
    title = "Price Range",
    summary = "$${priceRange.start.roundToInt()} - $${priceRange.endInclusive.roundToInt()}",
    valueRange = 0f..1000f
)
```

## Component States

### Disabled State

```kotlin
var rangeValue by remember { mutableStateOf(0.3f..0.7f) }

RangeSliderPreference(
    value = rangeValue,
    onValueChange = { rangeValue = it },
    title = "Disabled Range",
    summary = "This range slider is currently unavailable",
    enabled = false
)
```

## Properties

### RangeSliderPreference Properties

| Property Name         | Type                                        | Description                                                                                                                                                                    | Default Value                      | Required |
| --------------------- | ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------- | -------- |
| value                 | ClosedFloatingPointRange\<Float\>           | Current values of the range slider. If either value is outside of valueRange, it will be coerced                                                                              | -                                  | Yes      |
| onValueChange         | (ClosedFloatingPointRange\<Float\>) -> Unit | Lambda in which values should be updated                                                                                                                                       | -                                  | Yes      |
| title                 | String?                                     | Title of the preference                                                                                                                                                        | null                               | No       |
| modifier              | Modifier                                    | Modifier applied to the component                                                                                                                                              | Modifier                           | No       |
| titleColor            | BasicComponentColors                        | Title text color configuration                                                                                                                                                 | BasicComponentDefaults.titleColor() | No       |
| summary               | String?                                     | Summary description                                                                                                                                                            | null                               | No       |
| summaryColor          | BasicComponentColors                        | Summary text color configuration                                                                                                                                               | BasicComponentDefaults.summaryColor() | No       |
| startAction           | @Composable (() -> Unit)?                   | Custom start side content                                                                                                                                                      | null                               | No       |
| valueText             | String?                                     | Current slider value text displayed in the end area with summary-style formatting. Rendered inside the Row layout with center-vertical alignment and weight                     | null                               | No       |
| endActions            | @Composable (RowScope.() -> Unit)?          | Custom end side content, rendered after valueText within the same Row                                                                                                          | null                               | No       |
| bottomAction          | @Composable (() -> Unit)?                   | Custom content at the top of the bottom area, above the range slider                                                                                                           | null                               | No       |
| onClick               | (() -> Unit)?                               | Callback triggered on click. When non-null, an arrow icon is displayed in the end area                                                                                        | null                               | No       |
| holdDownState         | Boolean                                     | Whether the component is in the pressed state                                                                                                                                  | false                              | No       |
| enabled               | Boolean                                     | Whether the preference is enabled                                                                                                                                              | true                               | No       |
| valueRange            | ClosedFloatingPointRange\<Float\>           | Range of values that range slider values can take. Passed value will be coerced to this range                                                                                  | 0f..1f                             | No       |
| steps                 | Int                                         | Amount of discrete allowable values. If 0, the slider will behave continuously. Must not be negative                                                                           | 0                                  | No       |
| onValueChangeFinished | (() -> Unit)?                               | Called when value change has ended                                                                                                                                              | null                               | No       |
| sliderHeight          | Dp                                          | Height of the range slider                                                                                                                                                     | SliderDefaults.MinHeight           | No       |
| sliderColors          | SliderColors                                | Color configuration of the range slider                                                                                                                                        | SliderDefaults.sliderColors()      | No       |
| hapticEffect          | SliderDefaults.SliderHapticEffect           | Type of haptic feedback                                                                                                                                                        | SliderDefaults.DefaultHapticEffect | No       |
| showKeyPoints         | Boolean                                     | Whether to show key point indicators on the slider. Only works when keyPoints is not null                                                                                      | false                              | No       |
| keyPoints             | List\<Float\>?                              | Custom key point values to display on the slider. If null, uses step positions from steps parameter. Values should be within valueRange                                        | null                               | No       |
| magnetThreshold       | Float                                       | Magnetic snap threshold as a fraction (0.0 to 1.0). When slider value is within this distance from a key point, it will snap to that point. Only applies when keyPoints is set | 0.02f                              | No       |
| insideMargin          | PaddingValues                               | Internal content padding                                                                                                                                                       | BasicComponentDefaults.InsideMargin | No       |

## Advanced Usage

### Price Range Filter

```kotlin
var priceRange by remember { mutableStateOf(100f..500f) }

RangeSliderPreference(
    value = priceRange,
    onValueChange = { priceRange = it },
    title = "Price Filter",
    summary = "$${priceRange.start.roundToInt()} - $${priceRange.endInclusive.roundToInt()}",
    valueRange = 0f..1000f,
    steps = 99
)
```

### With Start Icon

```kotlin
var frequencyRange by remember { mutableStateOf(20f..20000f) }

RangeSliderPreference(
    value = frequencyRange,
    onValueChange = { frequencyRange = it },
    title = "Frequency Range",
    summary = "${frequencyRange.start.roundToInt()}Hz - ${frequencyRange.endInclusive.roundToInt()}Hz",
    startAction = {
        Icon(
            imageVector = MiuixIcons.Basic.Audio,
            contentDescription = "Frequency Icon",
            tint = MiuixTheme.colorScheme.onBackground,
            modifier = Modifier.padding(end = 16.dp)
        )
    },
    valueRange = 20f..20000f,
    showKeyPoints = true,
    keyPoints = listOf(20f, 100f, 1000f, 5000f, 10000f, 20000f)
)
```

### With Custom Key Points

```kotlin
var range by remember { mutableStateOf(20f..80f) }

RangeSliderPreference(
    value = range,
    onValueChange = { range = it },
    title = "Custom Range",
    summary = "${range.start.roundToInt()}% - ${range.endInclusive.roundToInt()}%",
    valueRange = 0f..100f,
    showKeyPoints = true,
    keyPoints = listOf(0f, 20f, 40f, 60f, 80f, 100f),
    magnetThreshold = 0.03f
)
```
