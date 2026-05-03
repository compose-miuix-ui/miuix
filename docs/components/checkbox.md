# Checkbox

`Checkbox` is a basic selection component in Miuix, supporting three states: checked, unchecked, and indeterminate. It provides an interactive selection control with animation effects, suitable for multiple selection scenarios and enabling/disabling configuration items.

<div style="position: relative; height: 100px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=checkbox" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.Checkbox
import androidx.compose.ui.state.ToggleableState
```

## Basic Usage

The Checkbox component can be used to create selectable options:

```kotlin
var state by remember { mutableStateOf(ToggleableState.Off) }

Checkbox(
    state = state,
    onClick = { state = if (state == ToggleableState.On) ToggleableState.Off else ToggleableState.On }
)
```

## Component States

### Disabled State

```kotlin
Checkbox(
    state = ToggleableState.Off,
    onClick = null,
    enabled = false
)
```

### Indeterminate State

The indeterminate state is typically used to represent a parent checkbox where only some child checkboxes are selected. When indeterminate, a horizontal dash is shown instead of a checkmark.

```kotlin
var parentState by remember { mutableStateOf(ToggleableState.Indeterminate) }
val childStates = remember { mutableStateListOf(true, false, true) }

// Cycle: Off → Indeterminate → On → Off
Checkbox(
    state = parentState,
    onClick = {
        parentState = when (parentState) {
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Off
        }
    }
)
```

## Properties

### Checkbox Properties

| Property Name | Type                | Description                                                    | Default Value                     | Required |
| ------------- | ------------------- | -------------------------------------------------------------- | --------------------------------- | -------- |
| state         | ToggleableState     | The current state of the Checkbox (On, Off, or Indeterminate)  | -                                 | Yes      |
| onClick       | (() -> Unit)?       | Callback when the Checkbox is clicked; `null` = non-interactive | -                                | Yes      |
| modifier      | Modifier            | Modifier applied to the Checkbox                               | Modifier                          | No       |
| colors        | CheckboxColors      | Color configuration for the Checkbox                           | CheckboxDefaults.checkboxColors() | No       |
| enabled       | Boolean             | Whether the Checkbox is interactive                            | true                              | No       |

### CheckboxDefaults Object

The CheckboxDefaults object provides default color configurations for the Checkbox component.

#### Methods

| Method Name      | Type           | Description                               |
| ---------------- | -------------- | ----------------------------------------- |
| checkboxColors() | CheckboxColors | Creates default color config for checkbox |

### CheckboxColors Class

| Property Name                    | Type  | Description                                  |
| -------------------------------- | ----- | -------------------------------------------- |
| checkedForegroundColor           | Color | Foreground color when checked (checkmark)    |
| uncheckedForegroundColor         | Color | Foreground color when unchecked              |
| disabledCheckedForegroundColor   | Color | Foreground color when disabled and checked   |
| disabledUncheckedForegroundColor | Color | Foreground color when disabled and unchecked |
| checkedBackgroundColor           | Color | Background color when checked                |
| uncheckedBackgroundColor         | Color | Background color when unchecked              |
| disabledCheckedBackgroundColor   | Color | Background color when disabled and checked   |
| disabledUncheckedBackgroundColor | Color | Background color when disabled and unchecked |

> The indeterminate state shares the same colors as the checked state.

## Advanced Usage

### Custom Colors

```kotlin
var state by remember { mutableStateOf(ToggleableState.Off) }

Checkbox(
    state = state,
    onClick = { state = if (state == ToggleableState.On) ToggleableState.Off else ToggleableState.On },
    colors = CheckboxDefaults.checkboxColors(
        checkedBackgroundColor = Color.Red,
        checkedForegroundColor = Color.White
    )
)
```

### Parent-Child Checkboxes (Indeterminate Pattern)

```kotlin
val childStates = remember { mutableStateListOf(false, true, false) }
val parentState by remember {
    derivedStateOf {
        when {
            childStates.all { it } -> ToggleableState.On
            childStates.none { it } -> ToggleableState.Off
            else -> ToggleableState.Indeterminate
        }
    }
}

Column {
    Checkbox(
        state = parentState,
        onClick = {
            val newValue = parentState != ToggleableState.On
            childStates.indices.forEach { childStates[it] = newValue }
        }
    )
    childStates.forEachIndexed { index, checked ->
        Checkbox(
            state = ToggleableState(checked),
            onClick = { childStates[index] = !childStates[index] }
        )
    }
}
```

### Using with Text

```kotlin
var state by remember { mutableStateOf(ToggleableState.Off) }

Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(16.dp)
) {
    Checkbox(
        state = state,
        onClick = { state = if (state == ToggleableState.On) ToggleableState.Off else ToggleableState.On }
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = "Accept Terms and Privacy Policy")
}
```

### Using in Lists

```kotlin
val options = listOf("Option A", "Option B", "Option C")
val checkedStates = remember { mutableStateListOf(false, true, false) }

LazyColumn {
    items(options.size) { index ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                state = ToggleableState(checkedStates[index]),
                onClick = { checkedStates[index] = !checkedStates[index] }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = options[index])
        }
    }
}
```
