# NumberPicker

`NumberPicker` is a basic interactive component in Miuix used for selecting a value from a range of numbers by vertical scrolling. The selected item is centered and highlighted, while surrounding items fade out and scale down. Supports infinite scrolling with the `wrapAround` parameter.

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=numberPicker" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.NumberPicker
```

## Basic Usage

```kotlin
var value by remember { mutableIntStateOf(5) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 0..10
)
```

## Component States

### Disabled State

```kotlin
var value by remember { mutableIntStateOf(5) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 0..10,
    enabled = false
)
```

### Infinite Scrolling

When `wrapAround` is enabled, the picker wraps from the last item back to the first, allowing continuous scrolling.

```kotlin
var value by remember { mutableIntStateOf(0) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 0..23,
    wrapAround = true
)
```

## Properties

### NumberPicker Properties

| Property Name    | Type               | Description                                                              | Default Value              | Required |
| ---------------- | ------------------ | ------------------------------------------------------------------------ | -------------------------- | -------- |
| value            | Int                | Current selected value. If outside range, it will be coerced             | -                          | Yes      |
| onValueChange    | (Int) -> Unit      | Callback invoked when the selected value changes                         | -                          | Yes      |
| modifier         | Modifier           | Modifier applied to the picker                                           | Modifier                   | No       |
| enabled          | Boolean            | Whether the picker is enabled for user interaction                       | true                       | No       |
| range            | IntRange           | The range of selectable values                                           | 0..10                      | No       |
| label            | (Int) -> String    | Converts a value to its display string                                   | { it.toString() }          | No       |
| visibleItemCount | Int                | Number of visible items. Must be odd and at least 3                      | 5                          | No       |
| wrapAround       | Boolean            | Whether the picker wraps around (infinite scrolling)                     | false                      | No       |
| colors           | NumberPickerColors | Color configuration of the picker                                        | NumberPickerDefaults.colors() | No       |
| textStyle        | TextStyle          | Text style for picker items                                              | MiuixTheme.textStyles.title1 | No       |
| itemHeight       | Dp                 | The height of each item in the picker                                    | NumberPickerDefaults.ItemHeight | No       |

### NumberPickerDefaults Object

The NumberPickerDefaults object provides default configurations for the NumberPicker component.

#### Properties

| Property Name | Type | Description                     | Default Value |
| ------------- | ---- | ------------------------------- | ------------- |
| ItemHeight    | Dp   | Default height of each item     | 45.dp         |

#### Methods

| Method Name | Return Type        | Description                         |
| ----------- | ------------------ | ----------------------------------- |
| colors()    | NumberPickerColors | Creates default color configuration |

### NumberPickerColors Class

| Property Name              | Type  | Description                                  |
| -------------------------- | ----- | -------------------------------------------- |
| selectedTextColor          | Color | Text color for the selected (center) item    |
| unselectedTextColor        | Color | Text color for unselected items              |
| disabledSelectedTextColor  | Color | Selected text color when disabled            |
| disabledUnselectedTextColor| Color | Unselected text color when disabled          |

## Advanced Usage

### Custom Label Format

```kotlin
var hour by remember { mutableIntStateOf(9) }

NumberPicker(
    value = hour,
    onValueChange = { hour = it },
    range = 0..23,
    label = { it.toString().padStart(2, '0') }  // "00", "01", ... "23"
)
```

### Time Picker

Combine two NumberPickers to create a time picker.

```kotlin
var hour by remember { mutableIntStateOf(16) }
var minute by remember { mutableIntStateOf(30) }

Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
) {
    NumberPicker(
        value = hour,
        onValueChange = { hour = it },
        range = 0..23,
        label = { it.toString().padStart(2, '0') },
        wrapAround = true,
        modifier = Modifier.weight(1f),
    )
    Text(
        text = ":",
        fontWeight = FontWeight.Bold,
    )
    NumberPicker(
        value = minute,
        onValueChange = { minute = it },
        range = 0..59,
        label = { it.toString().padStart(2, '0') },
        wrapAround = true,
        modifier = Modifier.weight(1f),
    )
}
```

### Custom Visible Item Count

```kotlin
var value by remember { mutableIntStateOf(5) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 1..100,
    visibleItemCount = 3
)
```

### Custom Colors

```kotlin
var value by remember { mutableIntStateOf(5) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 0..10,
    colors = NumberPickerDefaults.colors(
        selectedTextColor = Color.Red,
        unselectedTextColor = Color.Gray
    )
)
```
