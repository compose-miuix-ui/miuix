# RadioButton

`RadioButton` is a basic selection component in Miuix, supporting two states: selected and unselected. When selected, it displays an animated checkmark indicator. When unselected, no indicator is shown. It is suitable for single selection scenarios where only one option can be chosen from a group.

<div style="position: relative; height: 220px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=radioButton" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.RadioButton
```

## Basic Usage

The RadioButton component is typically used within a mutually exclusive group:

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }

Column {
    RadioButtonPreference(
        title = "Option A",
        selected = selectedIndex == 0,
        onClick = { selectedIndex = 0 }
    )
    RadioButtonPreference(
        title = "Option B",
        selected = selectedIndex == 1,
        onClick = { selectedIndex = 1 }
    )
}
```

## Component States

### Disabled State

```kotlin
RadioButton(
    selected = true,
    onClick = null,
    enabled = false
)
```

## Properties

### RadioButton Properties

| Property Name | Type                | Description                                                        | Default Value                             | Required |
| ------------- | ------------------- | ------------------------------------------------------------------ | ----------------------------------------- | -------- |
| selected      | Boolean             | Whether the RadioButton is currently selected                      | -                                         | Yes      |
| onClick       | (() -> Unit)?       | Callback when the RadioButton is clicked; `null` = non-interactive | -                                         | Yes      |
| modifier      | Modifier            | Modifier applied to the RadioButton                                | Modifier                                  | No       |
| colors        | RadioButtonColors   | Color configuration for the RadioButton                            | RadioButtonDefaults.radioButtonColors()   | No       |
| enabled       | Boolean             | Whether the RadioButton is interactive                             | true                                      | No       |

### RadioButtonDefaults Object

The RadioButtonDefaults object provides default color configurations for the RadioButton component.

#### Methods

| Method Name         | Type              | Description                                  |
| ------------------- | ----------------- | -------------------------------------------- |
| radioButtonColors() | RadioButtonColors | Creates default color config for radio button |

### RadioButtonColors Class

| Property Name         | Type  | Description                                     |
| --------------------- | ----- | ----------------------------------------------- |
| selectedColor         | Color | Checkmark color when selected                   |
| disabledSelectedColor | Color | Checkmark color when disabled and selected      |

## Advanced Usage

### Custom Colors

```kotlin
var selected by remember { mutableStateOf(false) }

RadioButton(
    selected = selected,
    onClick = { selected = !selected },
    colors = RadioButtonDefaults.radioButtonColors(
        selectedColor = Color.Red
    )
)
```

### Using in Lists

```kotlin
val options = listOf("Option A", "Option B", "Option C")
var selectedIndex by remember { mutableIntStateOf(0) }

Card {
    options.forEachIndexed { index, option ->
        RadioButtonPreference(
            title = option,
            selected = selectedIndex == index,
            onClick = { selectedIndex = index }
        )
    }
}
```
