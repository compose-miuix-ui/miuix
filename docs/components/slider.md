# Slider

`Slider` is a basic interactive component in Miuix used for selecting values within a continuous range. Users can adjust values by dragging the slider, making it suitable for scenarios such as volume adjustment, brightness control, and progress display.

Miuix also provides `VerticalSlider` for vertical orientation and `RangeSlider` for selecting a range of values.

<div style="position: relative; max-width: 700px; height: 200px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=slider" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.VerticalSlider
import top.yukonga.miuix.kmp.basic.RangeSlider
```

## Basic Usage

### Slider

```kotlin
var sliderValue by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = sliderValue,
    onValueChange = { sliderValue = it }
)
```

### VerticalSlider

```kotlin
var sliderValue by remember { mutableFloatStateOf(0.5f) }

VerticalSlider(
    value = sliderValue,
    onValueChange = { sliderValue = it },
    modifier = Modifier.height(200.dp)
)
```

### RangeSlider

```kotlin
var rangeValue by remember { mutableStateOf(0.2f..0.8f) }

RangeSlider(
    value = rangeValue,
    onValueChange = { rangeValue = it }
)
```

## Component States

### Disabled State

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = value,
    onValueChange = { value = it },
    enabled = false
)
```

## Haptic Feedback

Slider supports haptic feedback, which can be customized through the `hapticEffect` parameter. See [SliderHapticEffect](../components/slider#sliderhapticeffect) for details.

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = value,
    onValueChange = { value = it },
    hapticEffect = SliderHapticEffect.Step
)
```

## Properties

### Slider Properties

| Property Name         | Type                              | Description                                                                                                                                                                    | Default Value                      | Required |
| --------------------- | --------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------- | -------- |
| value                 | Float                             | Current value of the slider. If outside of valueRange provided, value will be coerced to this range                                                                            | -                                  | Yes      |
| onValueChange         | (Float) -> Unit                   | Callback when value changes                                                                                                                                                    | -                                  | Yes      |
| modifier              | Modifier                          | Modifier applied to the slider                                                                                                                                                 | Modifier                           | No       |
| enabled               | Boolean                           | Whether the slider is enabled                                                                                                                                                  | true                               | No       |
| valueRange            | ClosedFloatingPointRange\<Float\> | Range of values that this slider can take. The passed value will be coerced to this range                                                                                      | 0f..1f                             | No       |
| steps                 | Int                               | Amount of discrete allowable values. If 0, the slider will behave continuously. Must not be negative                                                                           | 0                                  | No       |
| onValueChangeFinished | (() -> Unit)?                     | Called when value change has ended                                                                                                                                             | null                               | No       |
| reverseDirection      | Boolean                           | Controls the direction of slider. When false, increases left to right. When true, increases right to left (RTL)                                                                | false                              | No       |
| height                | Dp                                | Height of the slider                                                                                                                                                           | SliderDefaults.MinHeight           | No       |
| colors                | SliderColors                      | Color configuration of the slider                                                                                                                                              | SliderDefaults.sliderColors()      | No       |
| effect                | Boolean                           | Whether to show special effects                                                                                                                                                | false                              | No       |
| hapticEffect          | SliderDefaults.SliderHapticEffect | Type of haptic feedback                                                                                                                                                        | SliderDefaults.DefaultHapticEffect | No       |
| showKeyPoints         | Boolean                           | Whether to show key point indicators on the slider. Only works when keyPoints is not null                                                                                      | false                              | No       |
| keyPoints             | List\<Float\>?                    | Custom key point values to display on the slider. If null, uses step positions from steps parameter. Values should be within valueRange                                        | null                               | No       |
| magnetThreshold       | Float                             | Magnetic snap threshold as a fraction (0.0 to 1.0). When slider value is within this distance from a key point, it will snap to that point. Only applies when keyPoints is set | 0.02f                              | No       |

### VerticalSlider Properties

| Property Name         | Type                              | Description                                                                                                                                                                    | Default Value                      | Required |
| --------------------- | --------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------- | -------- |
| value                 | Float                             | Current value of the slider                                                                                                                                                    | -                                  | Yes      |
| onValueChange         | (Float) -> Unit                   | Callback when value changes                                                                                                                                                    | -                                  | Yes      |
| modifier              | Modifier                          | Modifier applied to the slider                                                                                                                                                 | Modifier                           | No       |
| enabled               | Boolean                           | Whether the slider is enabled                                                                                                                                                  | true                               | No       |
| valueRange            | ClosedFloatingPointRange\<Float\> | Range of values that this slider can take                                                                                                                                      | 0f..1f                             | No       |
| steps                 | Int                               | Amount of discrete allowable values                                                                                                                                            | 0                                  | No       |
| onValueChangeFinished | (() -> Unit)?                     | Called when value change has ended                                                                                                                                             | null                               | No       |
| reverseDirection      | Boolean                           | Controls the direction of slider. When false, increases bottom to top. When true, increases top to bottom                                                                      | false                              | No       |
| width                 | Dp                                | Width of the vertical slider                                                                                                                                                   | SliderDefaults.MinHeight           | No       |
| colors                | SliderColors                      | Color configuration of the slider                                                                                                                                              | SliderDefaults.sliderColors()      | No       |
| effect                | Boolean                           | Whether to show special effects                                                                                                                                                | false                              | No       |
| hapticEffect          | SliderDefaults.SliderHapticEffect | Type of haptic feedback                                                                                                                                                        | SliderDefaults.DefaultHapticEffect | No       |
| showKeyPoints         | Boolean                           | Whether to show key point indicators on the slider. Only works when keyPoints is not null                                                                                      | false                              | No       |
| keyPoints             | List\<Float\>?                    | Custom key point values to display on the slider. If null, uses step positions from steps parameter. Values should be within valueRange                                        | null                               | No       |
| magnetThreshold       | Float                             | Magnetic snap threshold as a fraction (0.0 to 1.0). When slider value is within this distance from a key point, it will snap to that point. Only applies when keyPoints is set | 0.02f                              | No       |

### RangeSlider Properties

| Property Name         | Type                                        | Description                                                                                                                                                                    | Default Value                      | Required |
| --------------------- | ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------- | -------- |
| value                 | ClosedFloatingPointRange\<Float\>           | Current values of the RangeSlider. If either value is outside of valueRange, it will be coerced                                                                                | -                                  | Yes      |
| onValueChange         | (ClosedFloatingPointRange\<Float\>) -> Unit | Lambda in which values should be updated                                                                                                                                       | -                                  | Yes      |
| modifier              | Modifier                                    | Modifier applied to the slider                                                                                                                                                 | Modifier                           | No       |
| enabled               | Boolean                                     | Whether the slider is enabled                                                                                                                                                  | true                               | No       |
| valueRange            | ClosedFloatingPointRange\<Float\>           | Range of values that Range Slider values can take                                                                                                                              | 0f..1f                             | No       |
| steps                 | Int                                         | Amount of discrete allowable values                                                                                                                                            | 0                                  | No       |
| onValueChangeFinished | (() -> Unit)?                               | Called when value change has ended                                                                                                                                             | null                               | No       |
| height                | Dp                                          | Height of the slider                                                                                                                                                           | SliderDefaults.MinHeight           | No       |
| colors                | SliderColors                                | Color configuration of the slider                                                                                                                                              | SliderDefaults.sliderColors()      | No       |
| effect                | Boolean                                     | Whether to show special effects                                                                                                                                                | false                              | No       |
| hapticEffect          | SliderDefaults.SliderHapticEffect           | Type of haptic feedback                                                                                                                                                        | SliderDefaults.DefaultHapticEffect | No       |
| showKeyPoints         | Boolean                                     | Whether to show key point indicators on the slider. Only works when keyPoints is not null                                                                                      | false                              | No       |
| keyPoints             | List\<Float\>?                              | Custom key point values to display on the slider. If null, uses step positions from steps parameter. Values should be within valueRange                                        | null                               | No       |
| magnetThreshold       | Float                                       | Magnetic snap threshold as a fraction (0.0 to 1.0). When slider value is within this distance from a key point, it will snap to that point. Only applies when keyPoints is set | 0.02f                              | No       |

### SliderDefaults Object

The SliderDefaults object provides default configurations for the Slider component.

#### Constants

| Constant Name       | Type               | Default Value           | Description                  |
| ------------------- | ------------------ | ----------------------- | ---------------------------- |
| MinHeight           | Dp                 | 30.dp                   | Default height of the slider |
| DefaultHapticEffect | SliderHapticEffect | SliderHapticEffect.Edge | Default haptic feedback type |

### SliderHapticEffect

| Value | Description                  |
| ----- | ---------------------------- |
| None  | No haptic feedback           |
| Edge  | Haptic feedback at edges     |
| Step  | Haptic feedback at each step |

#### Methods

| Method Name    | Return Type  | Description                         |
| -------------- | ------------ | ----------------------------------- |
| sliderColors() | SliderColors | Creates default color configuration |

### SliderColors Class

| Property Name           | Type  | Description                       |
| ----------------------- | ----- | --------------------------------- |
| foregroundColor         | Color | Foreground color of the slider    |
| disabledForegroundColor | Color | Foreground color when disabled    |
| backgroundColor         | Color | Background color of the slider    |
| keyPointColor           | Color | Color of the key point indicators |

## Advanced Usage

### Custom Value Range

```kotlin
var temperature by remember { mutableFloatStateOf(25f) }

Column {
    Text("Temperature: ${temperature.roundToInt()}°C")
    Slider(
        value = temperature,
        onValueChange = { temperature = it },
        valueRange = 16f..32f
    )
}
```

### Discrete Steps

```kotlin
var rating by remember { mutableFloatStateOf(3f) }

Column {
    Text("Rating: ${rating.roundToInt()}/5")
    Slider(
        value = rating,
        onValueChange = { rating = it },
        valueRange = 1f..5f,
        steps = 3  // Creates 5 discrete values: 1, 2, 3, 4, 5
    )
}
```

### Custom Colors

```kotlin
var volume by remember { mutableFloatStateOf(0.7f) }

Slider(
    value = volume,
    onValueChange = { volume = it },
    colors = SliderDefaults.sliderColors(
        foregroundColor = Color.Red,
        backgroundColor = Color.LightGray
    )
)
```

### Custom Height and Effects

```kotlin
var brightness by remember { mutableFloatStateOf(0.8f) }

Slider(
    value = brightness,
    onValueChange = { brightness = it },
    height = 40.dp,
    effect = true
)
```

### Slider with Haptic Feedback

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = value,
    onValueChange = { value = it },
    hapticEffect = SliderDefaults.SliderHapticEffect.Step
)
```

### VerticalSlider with Reverse Direction

```kotlin
var volume by remember { mutableFloatStateOf(0.5f) }

VerticalSlider(
    value = volume,
    onValueChange = { volume = it },
    modifier = Modifier.height(200.dp),
    reverseDirection = true  // Top to bottom
)
```

### RangeSlider for Price Filter

```kotlin
var priceRange by remember { mutableStateOf(100f..500f) }

Column {
    Text("Price: $${priceRange.start.roundToInt()} - $${priceRange.endInclusive.roundToInt()}")
    RangeSlider(
        value = priceRange,
        onValueChange = { priceRange = it },
        valueRange = 0f..1000f,
        steps = 99  // 101 discrete values from 0 to 1000
    )
}
```

### Complete Example with Value Change Callback

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }
var finalValue by remember { mutableFloatStateOf(0.5f) }

Column {
    Text("Current: $value")
    Text("Final: $finalValue")
    Slider(
        value = value,
        onValueChange = { value = it },
        onValueChangeFinished = { finalValue = value },
        valueRange = 0f..100f,
        steps = 99
    )
}
```

### Slider with Custom Key Points

```kotlin
var value by remember { mutableFloatStateOf(50f) }

Column {
    Text("Value: ${value.roundToInt()}")
    Slider(
        value = value,
        onValueChange = { value = it },
        valueRange = 0f..100f,
        showKeyPoints = true,
        keyPoints = listOf(0f, 25f, 50f, 75f, 100f),
        magnetThreshold = 0.05f,  // 5% snap threshold
        hapticEffect = SliderDefaults.SliderHapticEffect.Step
    )
}
```

### RangeSlider with Key Points

```kotlin
var range by remember { mutableStateOf(20f..80f) }

Column {
    Text("Range: ${range.start.roundToInt()} - ${range.endInclusive.roundToInt()}")
    RangeSlider(
        value = range,
        onValueChange = { range = it },
        valueRange = 0f..100f,
        showKeyPoints = true,
        keyPoints = listOf(0f, 20f, 40f, 60f, 80f, 100f),
        magnetThreshold = 0.03f
    )
}
```
